// Sourced By DevFFNightmare
package dev.purxiy.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.loader.fabric.ClientServices;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import net.fabricmc.loader.api.FabricLoader;

public final class IdentityPaths {
   private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
   public static final int WELCOME_VERSION = 1;
   static volatile int welcome;
   static volatile String photo = "";
   static float zoom = 1.0F;
   static float centreX = 0.5F;
   static float centreY = 0.5F;
   static boolean framed;
   private static boolean loaded;

   private IdentityPaths() {
   }

   private static Path file() {
      return FabricLoader.getInstance().getConfigDir().resolve("purxiy").resolve("identity.json");
   }

   public static boolean welcomeDone() {
      load();
      return welcome >= 1;
   }

   public static void setWelcomeDone(boolean var0) {
      load();
      welcome = var0 ? 1 : 0;
      save();
   }

   static synchronized void load() {
      if (!loaded) {
         loaded = true;
         Path var0 = file();
         if (Files.isRegularFile(var0)) {
            try (BufferedReader var1 = Files.newBufferedReader(var0, StandardCharsets.UTF_8)) {
               JsonObject var2 = JsonParser.parseReader(var1).getAsJsonObject();
               if (var2.has("welcome")) {
                  welcome = var2.get("welcome").getAsInt();
               }

               if (var2.has("photo") && var2.get("photo").isJsonObject()) {
                  JsonObject var3 = var2.getAsJsonObject("photo");
                  photo = var3.has("file") ? var3.get("file").getAsString() : "";
                  zoom = var3.has("zoom") ? var3.get("zoom").getAsFloat() : 1.0F;
                  centreX = var3.has("x") ? var3.get("x").getAsFloat() : 0.5F;
                  centreY = var3.has("y") ? var3.get("y").getAsFloat() : 0.5F;
                  framed = var3.has("framed") && var3.get("framed").getAsBoolean();
               }
            } catch (Exception var6) {
               ClientServices.LOGGER.warn("Could not read {}, starting over", var0, var6);
            }
         }
      }
   }

   static synchronized void save() {
      JsonObject var0 = new JsonObject();
      var0.addProperty("welcome", welcome);
      JsonObject var1 = new JsonObject();
      var1.addProperty("file", photo);
      var1.addProperty("zoom", Math.round(zoom * 1000.0F) / 1000.0);
      var1.addProperty("x", Math.round(centreX * 1000.0F) / 1000.0);
      var1.addProperty("y", Math.round(centreY * 1000.0F) / 1000.0);
      var1.addProperty("framed", framed);
      var0.add("photo", var1);
      Path var2 = file();

      try {
         Files.createDirectories(var2.getParent());
         Path var3 = var2.resolveSibling("identity.json.tmp");

         try (BufferedWriter var4 = Files.newBufferedWriter(var3, StandardCharsets.UTF_8)) {
            GSON.toJson(var0, var4);
         }

         try {
            Files.move(var3, var2, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
         } catch (AtomicMoveNotSupportedException var8) {
            Files.move(var3, var2, StandardCopyOption.REPLACE_EXISTING);
         }
      } catch (IOException | JsonIOException var10) {
         ClientServices.LOGGER.error("Could not save {}", var2, var10);
      }
   }
}
