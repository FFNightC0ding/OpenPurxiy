package dev.purxiy.modules.skins3d;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

final class Skins3DLoader {
   private static final Gson G = new GsonBuilder().setPrettyPrinting().create();
   private final Path e;
   private final Map<String, Skins3DLoader.SkinSource> F = new LinkedHashMap<>();

   Skins3DLoader(Path var1) {
      this.e = var1;
   }

   void load() throws IOException {
      if (Files.exists(this.e)) {
         try {
            JsonObject var1 = JsonParser.parseString(Files.readString(this.e, StandardCharsets.UTF_8)).getAsJsonObject();
            if (var1.get("version").getAsInt() != 1) {
               throw new IOException("Unsupported mask store version");
            }

            LinkedHashMap var2 = new LinkedHashMap();

            for (Entry var4 : var1.getAsJsonObject("worlds").entrySet()) {
               if (((String)var4.getKey()).matches("[0-9a-f]{64}")) {
                  Skins3DLoader.SkinSource var5 = (Skins3DLoader.SkinSource)G.fromJson((JsonElement)var4.getValue(), Skins3DLoader.SkinSource.class);
                  if (var5 != null && var5.valid()) {
                     if (var5.r != null) {
                        var5.r.snapshot();
                     }

                     var2.put((String)var4.getKey(), var5);
                  }
               }
            }

            this.F.clear();
            this.F.putAll(var2);
         } catch (RuntimeException var61) {
            throw new IOException("Invalid mask store", var61);
         }
      }
   }

   Skins3DLoader.SkinSource world(String var1) {
      return this.F.computeIfAbsent(var1, var0 -> new Skins3DLoader.SkinSource());
   }

   void save() throws IOException {
      JsonObject var1 = new JsonObject();
      var1.addProperty("version", 1);
      var1.add("worlds", G.toJsonTree(this.F));
      Files.createDirectories(this.e.getParent());
      Path var2 = this.e.resolveSibling(this.e.getFileName() + ".tmp");
      Files.writeString(var2, G.toJson(var1), StandardCharsets.UTF_8);

      try {
         Files.move(var2, this.e, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
      } catch (AtomicMoveNotSupportedException var4) {
         Files.move(var2, this.e, StandardCopyOption.REPLACE_EXISTING);
      }
   }

   static final class SkinSource {
      String A = "minecraft:overworld";
      Skins3DCamera r;

      boolean valid() {
         return this.A != null && this.A.matches("[a-z0-9_.-]+:[a-z0-9/._-]+") && (this.r == null || this.r.valid());
      }
   }
}
