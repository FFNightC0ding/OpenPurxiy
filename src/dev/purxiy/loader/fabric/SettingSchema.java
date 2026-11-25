package dev.purxiy.loader.fabric;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import dev.purxiy.loader.runtime.SettingValues;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedHashMap;
import java.util.Map;
import net.fabricmc.loader.api.FabricLoader;

final class SettingSchema {
   private static final int MAX_BYTES = 524288;
   private static final int MAX_LEGACY_BYTES = 2097152;

   private SettingSchema() {
   }

   static Map<String, String> read(JsonObject var0) {
      LinkedHashMap var1 = new LinkedHashMap();
      if (var0.has("settingDefinitions")) {
         for (JsonElement var3 : var0.getAsJsonArray("settingDefinitions")) {
            JsonObject var4 = var3.getAsJsonObject();
            JsonElement var5 = var4.get("default");
            if (var5 != null && !var5.isJsonNull()) {
               String var6 = var5.isJsonPrimitive() ? var5.getAsString() : var5.toString();
               var1.put(var4.get("id").getAsString(), SettingValues.normalize(var4, var6));
            }
         }
      }

      if (var0.has("settings")) {
         var0.getAsJsonObject("settings")
            .entrySet()
            .forEach(
               var1x -> var1.put(
                  (String)var1x.getKey(),
                  ((JsonElement)var1x.getValue()).isJsonPrimitive()
                     ? ((JsonElement)var1x.getValue()).getAsString()
                     : ((JsonElement)var1x.getValue()).toString()
               )
            );
      }

      if (!var0.has("settingDefinitions")) {
         return var1;
      } else {
         Path var10 = file(var0);
         JsonObject var11 = Files.isRegularFile(var10) ? readObject(var10, 524288) : legacySettings(var0);

         for (JsonElement var13 : var0.getAsJsonArray("settingDefinitions")) {
            JsonObject var14 = var13.getAsJsonObject();
            String var7 = var14.get("id").getAsString();
            var1.put(var7, SettingValues.normalize(var14, (String)var1.get(var7)));
            JsonElement var8 = var11.get(var7);
            if (var8 != null && !var8.isJsonNull()) {
               String var9 = var8.isJsonPrimitive() ? var8.getAsString() : var8.toString();
               if (accepts(var14, var9)) {
                  var1.put(var7, SettingValues.normalize(var14, var9));
               }
            }
         }

         return var1;
      }
   }

   private static JsonObject legacySettings(JsonObject var0) {
      JsonObject var1 = readObject(FabricLoader.getInstance().getConfigDir().resolve("purxiy/purxiy.json"), 2097152);
      JsonObject var2 = objectMember(var1, "modules");
      return objectMember(objectMember(var2, var0.get("id").getAsString()), "settings");
   }

   private static JsonObject objectMember(JsonObject var0, String var1) {
      JsonElement var2 = var0.get(var1);
      return var2 != null && var2.isJsonObject() ? var2.getAsJsonObject() : new JsonObject();
   }

   private static JsonObject readObject(Path var0, int var1) {
      if (!Files.isRegularFile(var0)) {
         return new JsonObject();
      } else {
         try {
            try (InputStream var2 = Files.newInputStream(var0)) {
               byte[] var4 = var2.readNBytes(var1 + 1);
               if (var4.length <= var1) {
                  JsonElement var5 = JsonParser.parseString(new String(var4, StandardCharsets.UTF_8));
                  if (var5.isJsonObject()) {
                     return var5.getAsJsonObject();
                  }
               }
            }

            return new JsonObject();
         } catch (IOException | JsonParseException var8) {
            return new JsonObject();
         }
      }
   }

   static boolean editable(JsonObject var0) {
      return SettingValues.supported(var0);
   }

   static boolean accepts(JsonObject var0, String var1) {
      return SettingValues.accepts(var0, var1);
   }

   static void save(JsonObject var0, Map<String, String> var1) throws IOException {
      LinkedHashMap var2 = new LinkedHashMap();

      for (JsonElement var4 : var0.getAsJsonArray("settingDefinitions")) {
         JsonObject var5 = var4.getAsJsonObject();
         String var6 = var5.get("id").getAsString();
         if (!SettingValues.supported(var5)) {
            throw new IllegalArgumentException(var5.get("name").getAsString());
         }

         String var7 = (String)var1.get(var6);
         if (!accepts(var5, var7)) {
            throw new IllegalArgumentException(var5.get("name").getAsString());
         }

         var2.put(var6, SettingValues.normalize(var5, var7));
      }

      byte[] var11 = new Gson().toJson(var2).getBytes(StandardCharsets.UTF_8);
      if (var11.length > 524288) {
         throw new IOException("Module settings too large");
      } else {
         Path var12 = file(var0);
         Files.createDirectories(var12.getParent());
         Path var13 = Files.createTempFile(var12.getParent(), "settings-", ".tmp");

         try {
            Files.write(var13, var11);
            Files.move(var13, var12, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
         } finally {
            Files.deleteIfExists(var13);
         }
      }
   }

   private static Path file(JsonObject var0) {
      String var1 = var0.get("id").getAsString();
      if (!var1.matches("[a-z][a-z0-9_-]{0,63}")) {
         throw new IllegalArgumentException("Invalid module id");
      } else {
         return FabricLoader.getInstance().getConfigDir().resolve("purxiy/loader/settings").resolve(var1 + ".json");
      }
   }
}
