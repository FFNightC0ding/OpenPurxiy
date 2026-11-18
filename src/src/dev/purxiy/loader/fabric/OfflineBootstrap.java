// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.loader.runtime.ModuleSource;
import dev.purxiy.loader.security.ModuleSpec;
import dev.purxiy.loader.security.ModuleVerifier;
import dev.purxiy.loader.security.VerifiedModule;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.time.Clock;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class OfflineBootstrap implements ModuleSource.Renewable {
   private final JsonObject index;

   public OfflineBootstrap() throws IOException {
      try (InputStream var1 = Objects.requireNonNull(this.getClass().getResourceAsStream("/purxiy-offline-index.json"))) {
         this.index = JsonParser.parseReader(new InputStreamReader(var1, StandardCharsets.UTF_8)).getAsJsonObject();
      }
   }

   public static void init() {
      try {
         try (InputStream var0 = Objects.requireNonNull(OfflineBootstrap.class.getResourceAsStream("/purxiy-loader.json"))) {
            JsonObject var1 = JsonParser.parseReader(new InputStreamReader(var0, StandardCharsets.UTF_8)).getAsJsonObject();
            PublicKey var2 = KeyFactory.getInstance("Ed25519")
               .generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(var1.get("packagePublicKey").getAsString())));
            ModuleState.active = new ModuleState(new ModuleVerifier(var2, Clock.systemUTC()), new OfflineBootstrap());
            LoaderMessages.catalog = var1.getAsJsonArray("modules");
            authorize();

            for (String var4 : List.of("dev.purxiy.onboarding.OnboardingRequest")) {
               Method var5 = Class.forName(var4).getDeclaredMethod("init");
               var5.setAccessible(true);
               var5.invoke(null);
            }

            System.out.println("[Purxiy Offline] Initialized 65 bundled modules; no account or integrity monitor created.");
         }
      } catch (Exception var8) {
         throw new IllegalStateException("Offline module initialization failed", var8);
      }
   }

   public static void authorize() {
      LoaderMessages.authorized = true;
      LoaderMessages.status = "Offline modules ready";
      if (LoaderMessages.catalog != null) {
         try {
            Field var0 = LoaderMessages.class.getDeclaredField("startupLoaded");
            var0.setAccessible(true);
            if (!var0.getBoolean(null)) {
               var0.setBoolean(null, true);
               Field var1 = LoaderMessages.class.getDeclaredField("startupQueue");
               var1.setAccessible(true);
               Field var2 = LoaderMessages.class.getDeclaredField("queuedStartup");
               var2.setAccessible(true);
               ArrayDeque var3 = (ArrayDeque)var1.get(null);
               Set var4 = (Set)var2.get(null);
               ArrayList<JsonObject> var5 = new ArrayList<>();
               LoaderMessages.catalog.forEach(var1x -> var5.add(var1x.getAsJsonObject()));
               List<String> var6 = List.of("sound", "interface", "motion", "keybinds", "profile", "main_menu");
               var5.sort(Comparator.comparingInt(var1x -> {
                  int var2x = var6.indexOf(var1x.get("id").getAsString());
                  return var2x < 0 ? var6.size() : var2x;
               }));

               for (JsonObject var8 : var5) {
                  if (ModuleCatalog.enabled(var8) && var4.add(var8.get("id").getAsString())) {
                     var3.addLast(var8);
                  }
               }
            }
         } catch (ReflectiveOperationException var9) {
            throw new IllegalStateException(var9);
         }
      }
   }

   private JsonObject entry(String var1, String var2) throws IOException {
      if (var1.matches("[a-z0-9_]+") && this.index.has(var1)) {
         JsonObject var3 = this.index.getAsJsonObject(var1);
         if (!var3.get("build").getAsString().equals(var2)) {
            throw new IOException("Bundled module build mismatch");
         } else {
            return var3;
         }
      } else {
         throw new IOException("Unknown bundled module");
      }
   }

   @Override
   public ModuleSource.Prepared fetch(String var1, String var2) throws Exception {
      JsonObject var3 = this.entry(var1, var2);

      byte[] var5;
      try (InputStream var4 = Objects.requireNonNull(this.getClass().getResourceAsStream("/purxiy-offline/" + var1 + ".jar"))) {
         var5 = var4.readNBytes(134217729);
      }

      if (var5.length <= 134217728 && HexFormat.of().formatHex(MessageDigest.getInstance("SHA-256").digest(var5)).equals(var3.get("sha256").getAsString())) {
         HashMap var6 = new HashMap();
         long var7 = 0L;
         ZipInputStream var9 = new ZipInputStream(new ByteArrayInputStream(var5));

         ZipEntry var10;
         try {
            while ((var10 = var9.getNextEntry()) != null) {
               if (!var10.isDirectory()) {
                  String var11 = var10.getName();
                  if (VerifiedModule.safePath(var11) && var6.size() < 10000) {
                     byte[] var12 = var9.readNBytes(33554433);
                     var7 += var12.length;
                     if (var12.length <= 33554432 && var7 <= 268435456L && var6.putIfAbsent(var11, var12) == null) {
                        continue;
                     }

                     throw new IOException("Invalid module entries");
                  }

                  throw new IOException("Unsafe module archive");
               }
            }
         } catch (Throwable var18) {
            try {
               var9.close();
            } catch (Throwable var15) {
               var18.addSuppressed(var15);
            }

            throw var18;
         }

         var9.close();
         if (var6.size() != var3.get("entries").getAsInt()) {
            throw new IOException("Incomplete module archive");
         } else {
            ArrayList var19 = new ArrayList();
            var3.getAsJsonArray("packages").forEach(var1x -> var19.add(var1x.getAsString()));
            ModuleSpec var20 = new ModuleSpec(var1, var2, var3.get("entrypoint").getAsString(), var19, Instant.parse("9999-12-31T23:59:59Z"));
            Constructor var13 = VerifiedModule.class.getDeclaredConstructor(ModuleSpec.class, Map.class);
            var13.setAccessible(true);
            VerifiedModule var14 = (VerifiedModule)var13.newInstance(var20, var6);
            System.out.println("[Purxiy Offline] Loaded " + var1 + " (" + var6.size() + " entries)");
            return new ModuleSource.Prepared(var14, Instant.MAX);
         }
      } else {
         throw new IOException("Bundled module integrity failure: " + var1);
      }
   }

   @Override
   public Instant renew(String var1, String var2) throws IOException {
      this.entry(var1, var2);
      return Instant.MAX;
   }
}
