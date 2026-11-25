package dev.purxiy.loader.runtime;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.loader.api.ModuleEntrypoint;
import dev.purxiy.loader.fabric.OfflineBootstrap;
import dev.purxiy.loader.security.ModuleSpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

// Loads every module entrypoint the same way ModuleRuntime does, so a broken jar
// fails the build instead of switching itself off at runtime.
public final class ModuleEntrypointAudit {
   private static final Pattern ASSET = Pattern.compile(
      "assets/purxiy/[A-Za-z0-9_./-]+\\.(?:png|ogg|json|glsl|vsh|fsh|glb|gltf|bin|obj|mtl|txt|md|vxm|pgv|mcmeta)"
   );

   private ModuleEntrypointAudit() {
   }

   public static void main(String[] args) throws Exception {
      JsonObject index;
      try (InputStream stream = ModuleEntrypointAudit.class.getResourceAsStream("/purxiy-offline-index.json")) {
         if (stream == null) {
            throw new IllegalStateException("Missing purxiy-offline-index.json");
         }

         index = JsonParser.parseReader(new InputStreamReader(stream, StandardCharsets.UTF_8)).getAsJsonObject();
      }

      List<Map.Entry<String, JsonElement>> modules = new ArrayList<>(index.entrySet());
      modules.sort(Comparator.comparing(Map.Entry::getKey));
      OfflineBootstrap source = new OfflineBootstrap();
      List<String> failures = new ArrayList<>();
      List<String> missingResources = new ArrayList<>();
      int loaded = 0;
      int assetsChecked = 0;

      for (Map.Entry<String, JsonElement> module : modules) {
         String id = module.getKey();
         JsonObject meta = module.getValue().getAsJsonObject();

         try {
            assetsChecked += auditResources(id, missingResources);
            String entrypointName = meta.get("entrypoint").getAsString();
            try (ModuleSource.Prepared prepared = source.fetch(id, meta.get("build").getAsString())) {
               ModuleSpec spec = prepared.module().spec();
               try (MemoryModuleClassLoader loader = new MemoryModuleClassLoader(prepared.module(), ModuleEntrypoint.class.getClassLoader())) {
                  Class<?> type = Class.forName(entrypointName, true, loader);
                  if (!ModuleEntrypoint.class.isAssignableFrom(type)) {
                     throw new IllegalStateException(entrypointName + " does not implement ModuleEntrypoint");
                  }

                  type.getConstructor();
                  if (!spec.owns(entrypointName)) {
                     throw new IllegalStateException("Module spec does not own " + entrypointName);
                  }

                  loaded++;
               }
            }
         } catch (Throwable failure) {
            Throwable root = failure;
            while (root.getCause() != null && root.getCause() != root) {
               root = root.getCause();
            }

            failures.add(id + " -> " + meta.get("entrypoint").getAsString() + " :: " + root.getClass().getName()
               + (root.getMessage() == null ? "" : ": " + root.getMessage()));
         }
      }

      System.out.println("Module entrypoints: " + loaded + " loaded, " + failures.size() + " failed, " + modules.size() + " total");
      System.out.println("Module resources: " + assetsChecked + " referenced, " + missingResources.size() + " missing");
      for (String failure : missingResources) {
         System.out.println("  MISSING " + failure);
      }
      for (String failure : failures) {
         System.out.println("  FAIL " + failure);
      }

      if (!failures.isEmpty() || !missingResources.isEmpty()) {
         throw new IllegalStateException(
            failures.size() + " bundled module entrypoint(s) failed to load, "
               + missingResources.size() + " referenced module resource(s) missing"
         );
      }
   }

   // Rebuilding a module jar only emits classes, so its assets must still resolve
   // from the jar or from the main resource classpath.
   private static int auditResources(String id, List<String> missingResources) throws IOException {
      Set<String> entries = new HashSet<>();
      List<byte[]> classes = new ArrayList<>();
      try (InputStream stream = ModuleEntrypointAudit.class.getResourceAsStream("/purxiy-offline/" + id + ".jar")) {
         if (stream == null) {
            throw new IOException("Missing bundled jar for " + id);
         }

         try (ZipInputStream zip = new ZipInputStream(stream)) {
            ZipEntry entry;
            while ((entry = zip.getNextEntry()) != null) {
               if (!entry.isDirectory()) {
                  entries.add(entry.getName());
                  if (entry.getName().endsWith(".class")) {
                     classes.add(zip.readAllBytes());
                  }
               }
            }
         }
      }

      int checked = 0;
      Set<String> seen = new HashSet<>();
      for (byte[] bytes : classes) {
         Matcher matcher = ASSET.matcher(new String(bytes, StandardCharsets.ISO_8859_1));
         while (matcher.find()) {
            String path = matcher.group();
            if (!seen.add(path)) {
               continue;
            }

            checked++;
            if (!entries.contains(path) && ModuleEntrypointAudit.class.getResource("/" + path) == null) {
               missingResources.add(id + " -> " + path);
            }
         }
      }

      return checked;
   }
}
