// Sourced By DevFFNightmare
package dev.purxiy.profile;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.loader.fabric.ClientServices;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;
import java.util.Map.Entry;
import net.minecraft.class_1747;
import net.minecraft.class_2960;
import net.minecraft.class_7923;

final class ProfileCodec {
   private ProfileCodec() {
   }

   static ProfileCodec.TypeK scan(Path var0, UUID var1) {
      ProfileLoader var2 = new ProfileLoader();
      int var3 = 0;
      if (!Files.isDirectory(var0)) {
         return new ProfileCodec.TypeK(var2, 0);
      } else {
         try (DirectoryStream var4 = Files.newDirectoryStream(var0, var0x -> Files.isDirectory(var0x))) {
            for (Path var6 : (Path)(var4)) {
               Path var7 = var6.resolve("stats").resolve(var1 + ".json");
               if (Files.isRegularFile(var7) && read(var7, var2)) {
                  var3++;
               }
            }
         } catch (Exception var10) {
            ClientServices.LOGGER.warn("Profile: could not look through {} for statistics", var0, var10);
         }

         return new ProfileCodec.TypeK(var2, var3);
      }
   }

   private static boolean read(Path var0, ProfileLoader var1) {
      try {
         boolean var4;
         try (BufferedReader var2 = Files.newBufferedReader(var0, StandardCharsets.UTF_8)) {
            JsonObject var5 = JsonParser.parseReader(var2).getAsJsonObject().getAsJsonObject("stats");
            if (var5 == null) {
               return false;
            }

            ProfileLoader var6 = new ProfileLoader();
            JsonObject var7 = object(var5, "minecraft:custom");
            if (var7 != null) {
               var6.play = var6.play + get(var7, "play_time") / 20.0;
               var6.foot = var6.foot
                  + (
                        get(var7, "walk_one_cm")
                           + get(var7, "crouch_one_cm")
                           + get(var7, "walk_on_water_one_cm")
                           + get(var7, "walk_under_water_one_cm")
                           + get(var7, "climb_one_cm")
                     )
                     / 100.0;
               var6.sprint = var6.sprint + get(var7, "sprint_one_cm") / 100.0;
               var6.swim = var6.swim + get(var7, "swim_one_cm") / 100.0;
               var6.glide = var6.glide + get(var7, "aviate_one_cm") / 100.0;
               var6.ride = var6.ride
                  + (
                        get(var7, "boat_one_cm")
                           + get(var7, "horse_one_cm")
                           + get(var7, "minecart_one_cm")
                           + get(var7, "pig_one_cm")
                           + get(var7, "strider_one_cm")
                           + get(var7, "happy_ghast_one_cm")
                           + get(var7, "nautilus_one_cm")
                     )
                     / 100.0;
               var6.fly = var6.fly + get(var7, "fly_one_cm") / 100.0;
               var6.mobKills = var6.mobKills + (int)get(var7, "mob_kills");
               var6.kills = var6.kills + (int)get(var7, "player_kills");
               var6.deaths = var6.deaths + (int)get(var7, "deaths");
            }

            JsonObject var8 = object(var5, "minecraft:mined");
            if (var8 != null) {
               for (Entry var10 : var8.entrySet()) {
                  var6.broken = var6.broken + ((JsonElement)var10.getValue()).getAsInt();
               }
            }

            JsonObject var16 = object(var5, "minecraft:used");
            if (var16 != null) {
               for (Entry var11 : var16.entrySet()) {
                  class_2960 var12 = class_2960.method_12829((String)var11.getKey());
                  if (var12 != null && class_7923.field_41178.method_63535(var12) instanceof class_1747) {
                     var6.placed = var6.placed + ((JsonElement)var11.getValue()).getAsInt();
                  }
               }
            }

            var1.add(var6);
            var4 = true;
         }

         return var4;
      } catch (Exception var15) {
         ClientServices.LOGGER.warn("Profile: skipping unreadable statistics {} ({})", var0, var15.getMessage());
         return false;
      }
   }

   
   private static JsonObject object(JsonObject var0, String var1) {
      JsonElement var2 = var0.get(var1);
      return var2 != null && var2.isJsonObject() ? var2.getAsJsonObject() : null;
   }

   private static double get(JsonObject var0, String var1) {
      JsonElement var2 = var0.get("minecraft:" + var1);
      return var2 != null && var2.isJsonPrimitive() ? Math.max(0.0, var2.getAsDouble()) : 0.0;
   }

   record TypeK(ProfileLoader totals, int worlds) {
   }
}
