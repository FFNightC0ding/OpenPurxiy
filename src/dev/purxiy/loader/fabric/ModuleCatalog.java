// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import net.fabricmc.loader.api.FabricLoader;

final class ModuleCatalog {
   private static final Map<String, ModuleCatalog.TypeC> saved = new HashMap<>();

   private ModuleCatalog() {
   }

   static boolean toggleable(JsonObject var0) {
      return !var0.has("toggleable") || var0.get("toggleable").getAsBoolean();
   }

   static boolean enabled(JsonObject var0) {
      if (!toggleable(var0)) {
         return true;
      } else {
         Boolean var1 = read(var0).enabled;
         return var1 != null
            ? var1
            : (var0.has("startup") ? var0.get("startup").getAsBoolean() : var0.has("onByDefault") && var0.get("onByDefault").getAsBoolean());
      }
   }

   static int key(JsonObject var0) {
      Integer var1 = read(var0).key;
      Integer var2 = keyValue(var0.get("defaultKey"));
      return var1 != null ? var1 : (var2 == null ? -1 : var2);
   }

   static boolean keyToggles(JsonObject var0) {
      return toggleable(var0) && (!var0.has("keybindToggles") || var0.get("keybindToggles").getAsBoolean());
   }

   static void enabled(JsonObject var0, boolean var1) throws IOException {
      write(var0, new ModuleCatalog.TypeC(var1, read(var0).key));
   }

   static void key(JsonObject var0, int var1) throws IOException {
      if (var1 != -1 && (var1 < 32 || var1 > 348)) {
         throw new IllegalArgumentException("Invalid keyboard binding");
      } else {
         write(var0, new ModuleCatalog.TypeC(read(var0).enabled, var1));
      }
   }

   private static ModuleCatalog.TypeC read(JsonObject var0) {
      String var1 = id(var0);
      return saved.computeIfAbsent(var1, var2 -> {
         Path var3 = file(var0);
         JsonObject var4;
         if (Files.isRegularFile(var3)) {
            var4 = readObject(var3, 4096);
         } else {
            JsonObject var5 = readObject(FabricLoader.getInstance().getConfigDir().resolve("purxiy/purxiy.json"), 2097152);
            JsonObject var6 = member(var5, "modules");
            var4 = member(var6, var1);
            if (!var4.has("settings") && var0.has("settingDefinitions") && !var0.getAsJsonArray("settingDefinitions").isEmpty()) {
               var4 = var4.deepCopy();
               if (Boolean.FALSE.equals(booleanValue(var4.get("enabled")))) {
                  var4.remove("enabled");
               }

               if (Integer.valueOf(-1).equals(keyValue(var4.get("key")))) {
                  var4.remove("key");
               }
            }
         }

         return new ModuleCatalog.TypeC(booleanValue(var4.get("enabled")), keyValue(var4.get("key")));
      });
   }

   private static Boolean booleanValue(JsonElement var0) {
      return var0 != null && var0.isJsonPrimitive() && var0.getAsJsonPrimitive().isBoolean() ? var0.getAsBoolean() : null;
   }

   private static Integer keyValue(JsonElement var0) {
      if (var0 != null && var0.isJsonPrimitive() && var0.getAsJsonPrimitive().isNumber()) {
         try {
            int var1 = var0.getAsBigDecimal().intValueExact();
            return var1 == -1 || var1 >= 32 && var1 <= 348 ? var1 : null;
         } catch (ArithmeticException | NumberFormatException var2) {
            return null;
         }
      } else {
         return null;
      }
   }

   private static JsonObject member(JsonObject var0, String var1) {
      return var0.has(var1) && var0.get(var1).isJsonObject() ? var0.getAsJsonObject(var1) : new JsonObject();
   }

   private static JsonObject readObject(Path var0, int var1) {
      try {
         JsonObject var4;
         try (InputStream var2 = Files.newInputStream(var0)) {
            byte[] var5 = var2.readNBytes(var1 + 1);
            if (var5.length > var1) {
               return new JsonObject();
            }

            JsonElement var6 = JsonParser.parseString(new String(var5, StandardCharsets.UTF_8));
            var4 = var6.isJsonObject() ? var6.getAsJsonObject() : new JsonObject();
         }

         return var4;
      } catch (IOException | JsonParseException var9) {
         return new JsonObject();
      }
   }

   private static void write(JsonObject var0, ModuleCatalog.TypeC var1) throws IOException {
      JsonObject var2 = new JsonObject();
      var2.addProperty("format", 1);
      if (var1.enabled != null) {
         var2.addProperty("enabled", var1.enabled);
      }

      if (var1.key != null) {
         var2.addProperty("key", var1.key);
      }

      Path var3 = file(var0);
      Files.createDirectories(var3.getParent());
      Path var4 = Files.createTempFile(var3.getParent(), "state-", ".tmp");

      try {
         Files.writeString(var4, var2.toString(), StandardCharsets.UTF_8);
         Files.move(var4, var3, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
         saved.put(id(var0), var1);
      } finally {
         Files.deleteIfExists(var4);
      }
   }

   private static String id(JsonObject var0) {
      String var1 = var0.get("id").getAsString();
      if (!var1.matches("[a-z][a-z0-9_-]{0,63}")) {
         throw new IllegalArgumentException("Invalid module identity");
      } else {
         return var1;
      }
   }

   private static Path file(JsonObject var0) {
      return FabricLoader.getInstance().getConfigDir().resolve("purxiy/loader/state").resolve(id(var0) + ".json");
   }

   private record TypeC(Boolean enabled, Integer key) {
   }
}
