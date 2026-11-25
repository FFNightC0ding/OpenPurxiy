package dev.purxiy.ui.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.loader.fabric.ClientServices;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import net.fabricmc.loader.api.FabricLoader;

final class ConfigLibraryLoader {
   private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();
   private static final int VERSION = 1;

   private static String token;

   private ConfigLibraryLoader() {
   }

   static Path folder() {
      return FabricLoader.getInstance().getConfigDir().resolve("purxiy").resolve("library");
   }

   static Path photos() {
      return folder().resolve("photos");
   }

   static Path discover() {
      return folder().resolve("discover");
   }

   static ConfigLibraryLoader.LibrarySnapshot load() {
      Path var0 = folder().resolve("library.json");
      ArrayList var1 = new ArrayList();
      ArrayList var2 = new ArrayList();
      ArrayList var3 = new ArrayList();
      String var4 = null;
      if (!Files.isRegularFile(var0)) {
         return new ConfigLibraryLoader.LibrarySnapshot(var1, var2, (String)null, var3);
      } else {
         try {
            JsonObject var5 = JsonParser.parseString(Files.readString(var0, StandardCharsets.UTF_8)).getAsJsonObject();

            for (JsonElement var7 : array(var5, "configs")) {
               try {
                  var1.add(read(var7.getAsJsonObject()));
               } catch (RuntimeException var10) {
                  ClientServices.LOGGER.warn("Skipping a config of the library that could not be read: {}", var10.toString());
               }
            }

            for (JsonElement var14 : array(var5, "shared")) {
               var2.add(var14.getAsString());
            }

            for (JsonElement var15 : array(var5, "unpublish")) {
               var3.add(var15.getAsString());
            }

            var4 = var5.has("active") && !var5.get("active").isJsonNull() ? var5.get("active").getAsString() : null;
         } catch (IOException | RuntimeException var11) {
            ClientServices.LOGGER.error("Could not read the config library {}", var0, var11);

            try {
               Files.copy(var0, var0.resolveSibling("library.unreadable.json"), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException var9) {
            }
         }

         return new ConfigLibraryLoader.LibrarySnapshot(var1, var2, var4, var3);
      }
   }

   static void save(List<ConfigLibraryStats> var0, List<ConfigLibraryStats> var1,  ConfigLibraryStats var2, List<String> var3) {
      JsonObject var4 = new JsonObject();
      var4.addProperty("version", 1);
      var4.addProperty("active", var2 == null ? null : var2.id);
      JsonArray var5 = new JsonArray();
      var1.forEach(var1x -> var5.add(var1x.id));
      var4.add("shared", var5);
      JsonArray var6 = new JsonArray();
      var3.forEach(var6::add);
      var4.add("unpublish", var6);
      JsonArray var7 = new JsonArray();
      var0.forEach(var1x -> var7.add(write(var1x)));
      var4.add("configs", var7);
      write(folder().resolve("library.json"), GSON.toJson(var4));
   }

   private static JsonObject write(ConfigLibraryStats var0) {
      JsonObject var1 = new JsonObject();
      var1.addProperty("id", var0.id);
      var1.addProperty("name", var0.name);
      var1.addProperty("author", var0.author);
      var1.addProperty("description", var0.description);
      var1.addProperty("hue", var0.hue);
      var1.addProperty("source", var0.source.name());
      var1.addProperty("created", var0.created);
      var1.addProperty("edited", var0.edited);
      JsonArray var2 = new JsonArray();
      Set var3 = var0.modules;
      var3.forEach(v -> var2.add((Boolean)v));
      var1.add("modules", var2);
      if (var0.settings != null) {
         var1.add("settings", var0.settings);
      }

      if (var0.photo != null) {
         var1.addProperty("photo", var0.photo);
      }

      if (var0.code != null) {
         var1.addProperty("code", var0.code);
         var1.addProperty("synced", var0.synced);
         if (var0.syncedPhoto != null) {
            var1.addProperty("syncedPhoto", var0.syncedPhoto);
         }

         var1.addProperty("likes", var0.likes);
         var1.addProperty("downloads", var0.downloads);
      }

      if (var0.origin != null) {
         var1.addProperty("origin", var0.origin);
      }

      return var1;
   }

   private static ConfigLibraryStats read(JsonObject var0) {
      ArrayList var1 = new ArrayList();

      for (JsonElement var3 : array(var0, "modules")) {
         var1.add(var3.getAsString());
      }

      ConfigLibraryStats.ConfigOrigin var10;
      try {
         var10 = ConfigLibraryStats.ConfigOrigin.valueOf(string(var0, "source", "SAVED"));
      } catch (IllegalArgumentException var9) {
         var10 = ConfigLibraryStats.ConfigOrigin.SAVED;
      }

      if (var10 == ConfigLibraryStats.ConfigOrigin.COMMUNITY) {
         var10 = ConfigLibraryStats.ConfigOrigin.ADDED;
      }

      long var11 = var0.has("created") ? var0.get("created").getAsLong() : System.currentTimeMillis();
      ConfigLibraryStats var5 = new ConfigLibraryStats(
         var0.get("id").getAsString(),
         string(var0, "name", "Config"),
         string(var0, "author", ""),
         string(var0, "description", ""),
         var1,
         new String[0],
         var10,
         var11,
         var0.has("hue") ? var0.get("hue").getAsFloat() : 0.5F
      );
      var5.edited = var0.has("edited") ? var0.get("edited").getAsLong() : var11;
      JsonObject var7;
      if (var0.get("settings") instanceof JsonObject var8) {
         var7 = var8;
      } else {
         var7 = null;
      }

      var5.settings = var7;
      var5.photo = var0.has("photo") ? var0.get("photo").getAsString() : null;
      var5.code = var0.has("code") ? var0.get("code").getAsString() : null;
      var5.synced = var0.has("synced") ? var0.get("synced").getAsLong() : 0L;
      var5.syncedPhoto = var0.has("syncedPhoto") ? var0.get("syncedPhoto").getAsString() : null;
      var5.likes = var0.has("likes") ? var0.get("likes").getAsInt() : 0;
      var5.downloads = var0.has("downloads") ? var0.get("downloads").getAsInt() : 0;
      var5.origin = var0.has("origin") ? var0.get("origin").getAsString() : null;
      return var5;
   }

   private static JsonArray array(JsonObject var0, String var1) {
      JsonArray var3;
      if (var0.get(var1) instanceof JsonArray var4) {
         var3 = var4;
      } else {
         var3 = new JsonArray();
      }

      return var3;
   }

   private static String string(JsonObject var0, String var1, String var2) {
      JsonElement var3 = var0.get(var1);
      return var3 != null && var3.isJsonPrimitive() ? var3.getAsString() : var2;
   }

   static String token() {
      if (token != null) {
         return token;
      } else {
         Path var0 = folder().resolve("publisher.key");

         try {
            if (Files.isRegularFile(var0)) {
               String var1 = Files.readString(var0, StandardCharsets.US_ASCII).strip();
               if (var1.matches("[\\w-]{32,128}")) {
                  token = var1;
                  return var1;
               }
            }
         } catch (IOException var2) {
            ClientServices.LOGGER.warn("Could not read {}: {}", var0, var2.getMessage());
         }

         byte[] var3 = new byte[32];
         new SecureRandom().nextBytes(var3);
         token = Base64.getUrlEncoder().withoutPadding().encodeToString(var3);
         write(var0, token);
         return token;
      }
   }

   static void write(Path var0, String var1) {
      try {
         Files.createDirectories(var0.getParent());
         Path var2 = var0.resolveSibling(var0.getFileName() + ".tmp");
         Files.writeString(var2, var1, StandardCharsets.UTF_8);

         try {
            Files.move(var2, var0, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
         } catch (AtomicMoveNotSupportedException var4) {
            Files.move(var2, var0, StandardCopyOption.REPLACE_EXISTING);
         }
      } catch (IOException var5) {
         ClientServices.LOGGER.error("Could not save {}", var0, var5);
      }
   }

   record LibrarySnapshot(List<ConfigLibraryStats> configs, List<String> shared,  String active, List<String> unpublish) {
   }
}
