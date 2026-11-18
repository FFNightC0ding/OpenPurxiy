// Sourced By DevFFNightmare
package dev.purxiy.modules.profile;

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

final class ProfileSupport3 {



   private ProfileSupport3() {
   }

   static ProfileSupport3.Data scan(Path var0, UUID var1) {
      long var2 = 117044824363602L;
      long var4 = var2 ^ 63933630350241L;
      ProfileLoader3 var6 = new ProfileLoader3();
      int var7 = 0;
      if (!Files.isDirectory(var0)) {
         return new ProfileSupport3.Data(var6, 0);
      } else {
         try (DirectoryStream<Path> var8 = Files.newDirectoryStream(var0, var0x -> Files.isDirectory(var0x))) {
            for (Path var10 : var8) {
               Path var11 = var10.resolve("stats").resolve(var1 + ".json");
               if (Files.isRegularFile(var11) && w(var11, var4, var6)) {
                  var7++;
               }
            }
         } catch (Exception var14) {
            ClientServices.LOGGER.warn("Profile: could not look through {} for statistics", var0, var14);
         }

         return new ProfileSupport3.Data(var6, var7);
      }
   }

   private static boolean w(Path var0, long var1, ProfileLoader3 var3) {
      long var4 = 126414103891090L;
      long var6 = 84161376838152L;
      ProfileLoader.ensureSourceClassInitialized();

      try {
         boolean var32;
         try (BufferedReader var8 = Files.newBufferedReader(var0, StandardCharsets.UTF_8)) {
            JsonObject var9 = JsonParser.parseReader(var8).getAsJsonObject().getAsJsonObject("stats");
            if (var9 == null) {
               return false;
            }

            ProfileLoader3 var10 = new ProfileLoader3();
            JsonObject var11 = D(var9, var4, "minecraft:custom");
            if (var11 != null) {
               var10.A = var10.A + f(var11, "play_time", var6) / 20.0;
               var10.j = var10.j
                  + (
                        f(var11, "walk_one_cm", var6)
                           + f(var11, "crouch_one_cm", var6)
                           + f(var11, "walk_on_water_one_cm", var6)
                           + f(var11, "walk_under_water_one_cm", var6)
                           + f(var11, "climb_one_cm", var6)
                     )
                     / 100.0;
               var10.B = var10.B + f(var11, "sprint_one_cm", var6) / 100.0;
               var10.u = var10.u + f(var11, "swim_one_cm", var6) / 100.0;
               var10.J = var10.J + f(var11, "aviate_one_cm", var6) / 100.0;
               var10.y = var10.y
                  + (
                        f(var11, "boat_one_cm", var6)
                           + f(var11, "horse_one_cm", var6)
                           + f(var11, "minecart_one_cm", var6)
                           + f(var11, "pig_one_cm", var6)
                           + f(var11, "strider_one_cm", var6)
                           + f(var11, "happy_ghast_one_cm", var6)
                           + f(var11, "nautilus_one_cm", var6)
                     )
                     / 100.0;
               var10.d = var10.d + f(var11, "fly_one_cm", var6) / 100.0;
               var10.Y = var10.Y + (int)f(var11, "mob_kills", var6);
               var10.k = var10.k + (int)f(var11, "player_kills", var6);
               var10.g = var10.g + (int)f(var11, "deaths", var6);
            }

            JsonObject var12 = D(var9, var4, "minecraft:mined");
            if (var12 != null) {
               for (Entry var14 : var12.entrySet()) {
                  var10.E = var10.E + ((JsonElement)var14.getValue()).getAsInt();
               }
            }

            JsonObject var29 = D(var9, var4, "minecraft:used");
            long var30 = 88331010331122L;
            if (88331010331122L == 0L) {
               boolean var10000 = false;
            } else {
               int var33 = var30 < 0L ? -1 : 1;
            }

            long var17 = 88331010331123L;
            if (88331010331123L == 0L) {
               boolean var34 = false;
            } else {
               int var35 = var17 < 0L ? -1 : 1;
            }

            if (var29 != null) {
               for (Entry var20 : var29.entrySet()) {
                  class_2960 var21 = class_2960.method_12829((String)var20.getKey());
                  if (var21 != null) {
                     var32 = class_7923.field_41178.method_63535(var21) instanceof class_1747;
                     long var23 = 88331010331123L;
                     if (88331010331123L == 0L) {
                        boolean var36 = false;
                     } else {
                        int var37 = var23 < 0L ? -1 : 1;
                     }

                     if (var32) {
                        var10.t = var10.t + ((JsonElement)var20.getValue()).getAsInt();
                     }
                  }
               }
            }

            long var31 = 88331010331122L;
            if (88331010331122L == 0L) {
               boolean var38 = false;
            } else {
               int var39 = var31 < 0L ? -1 : 1;
            }

            var3.add(var10);
            var32 = true;
         }

         return var32;
      } catch (Exception var27) {
         ClientServices.LOGGER.warn("Profile: skipping unreadable statistics {} ({})", var0, var27.getMessage());
         return false;
      }
   }

   
   private static JsonObject D(JsonObject var0, long var1, String var3) {
      ProfileLoader.ensureSourceClassInitialized();
      JsonElement var4 = var0.get(var3);
      long var5 = 126414103891090L;
      if (126414103891090L == 0L) {
         boolean var10000 = false;
      } else {
         int var8 = var5 < 0L ? -1 : 1;
      }

      return var4 != null && var4.isJsonObject() ? var4.getAsJsonObject() : null;
   }

   private static double f(JsonObject var0, String var1, long var2) {
      ProfileLoader.ensureSourceClassInitialized();
      JsonElement var4 = var0.get("minecraft:" + var1);
      return var4 != null && var4.isJsonPrimitive() ? Math.max(0.0, var4.getAsDouble()) : 0.0;
   }

record Data(ProfileLoader3 X, int x) {
      public ProfileLoader3 totals() {
         return this.X;
      }

      public int worlds() {
         return this.x;
      }

      public ProfileLoader3 gameplayTotalsState3() {
         return this.X;
      }

      public int integerState31() {
         return this.x;
      }
   }
}
