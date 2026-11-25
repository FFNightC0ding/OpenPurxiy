package dev.purxiy.profile;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;

final class ProfileFactory {
   static final boolean LIVE_JOURNEY = true;
   private static final LocalDate TODAY = LocalDate.of(2026, 9, 27);

   private ProfileFactory() {
   }

   static LocalDate today() {
      return TODAY;
   }

   static ProfileDay create() {
      Random var0 = new Random(1927L);
      ProfileDay var1 = new ProfileDay();
      var1.name = "Player";
      var1.since = TODAY.minusDays(73L);
      var1.imported = true;
      var1.importedWorlds = 4;
      ProfileLoader var2 = var1.baseline;
      var2.play = 348060.0;
      var2.foot = 88400.0;
      var2.sprint = 61200.0;
      var2.swim = 4100.0;
      var2.glide = 38700.0;
      var2.ride = 9300.0;
      var2.fly = 12800.0;
      var2.broken = 41870;
      var2.placed = 29410;
      var2.hits = 6120;
      var2.kills = 43;
      var2.mobKills = 1288;
      var2.deaths = 97;
      var2.chat = 540;

      for (int var3 = 73; var3 >= 0; var3--) {
         LocalDate var4 = TODAY.minusDays(var3);
         boolean var5 = var4.getDayOfWeek() == DayOfWeek.SATURDAY || var4.getDayOfWeek() == DayOfWeek.SUNDAY;
         if (var3 <= 9 || !(var0.nextFloat() < (var5 ? 0.08F : 0.3F))) {
            double var6 = var5 ? 2.2 + var0.nextGaussian() * 1.0 : 1.05 + var0.nextGaussian() * 0.55;
            var6 *= 0.75 + 0.5 * (73 - var3) / 73.0;
            if (var3 >= 19 && var3 <= 23) {
               var6++;
            }

            if (var3 == 0) {
               var6 = 1.4;
            }

            var6 = Math.max(0.2, var6);
            ProfileLoader var8 = var1.day(var4);
            var8.play = var6 * 3600.0;
            double var9 = 1.1 + var0.nextFloat() * 1.4;
            double var11 = var8.play * var9;
            var8.foot = var11 * 0.34;
            var8.sprint = var11 * 0.3;
            var8.glide = var11 * (0.18 + var0.nextFloat() * 0.1);
            var8.swim = var11 * 0.04;
            var8.ride = var11 * 0.06;
            var8.fly = var11 * 0.03;
            var8.broken = (int)(var6 * (170 + var0.nextInt(160)));
            var8.placed = (int)(var6 * (90 + var0.nextInt(150)));
            var8.hits = (int)(var6 * (35 + var0.nextInt(70)));
            var8.kills = var0.nextFloat() < 0.4F ? var0.nextInt(4) : 0;
            var8.mobKills = (int)(var6 * (6 + var0.nextInt(14)));
            var8.deaths = var0.nextFloat() < 0.5F ? 1 + var0.nextInt(2) : 0;
            var8.chat = (int)(var6 * (2 + var0.nextInt(7)));
            long var13 = var4.atTime(19, 0).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            String var15 = var0.nextFloat() < 0.55F ? "Hypixel" : (var0.nextFloat() < 0.6F ? "SourceByDevFFnightmare DeObfDeCompByGuideX SMP" : "Aurora");
            var1.addSession(new ProfileDay.DayCache(var13, var15, var8.copy()));
            ProfileDay.DayRecord var16 = var1.place(var15.toLowerCase(), var15);
            var16.play = var16.play + var8.play;
         }
      }

      ProfileDay.DayRecord var17 = var1.place("hypixel", "Hypixel");
      var17.play += 72000.0;
      var1.topSpeed = 31.4;
      var1.journey = journey(var0);
      return var1;
   }

   static ProfileDay.DayCache live(ProfileDay var0) {
      ProfileLoader var1 = var0.days.get(TODAY).copy();
      double var2 = var0.journey == null ? 1.0 : var0.journey.length() / Math.max(1.0, var1.distance());
      var1.foot *= var2;
      var1.sprint *= var2;
      var1.swim *= var2;
      var1.glide *= var2;
      var1.ride *= var2;
      var1.fly *= var2;
      long var4 = TODAY.atTime(20, 12).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      return new ProfileDay.DayCache(var4, "SourceByDevFFnightmare DeObfDeCompByGuideX SMP", var1);
   }

   private static ProfileTrackCodec journey(Random var0) {
      long var1 = TODAY.atTime(20, 12).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      ProfileTrackCodec var3 = new ProfileTrackCodec(var1, "SourceByDevFFnightmare DeObfDeCompByGuideX SMP");
      String var4 = "minecraft:overworld";
      double var5 = 214.0;
      double var7 = -96.0;
      double var9 = 0.6;
      int var11 = 0;

      for (int var12 = 0; var12 < 520; var12++) {
         var9 += Math.sin(var12 * 0.045) * 0.06 + var0.nextGaussian() * 0.05;
         double var13 = var12 < 260 ? 2.3 : (var12 < 330 ? 6.5 : 2.1);
         if (var12 >= 260 && var12 < 330) {
            var9 += (Math.atan2(-96.0 - var7, 214.0 - var5) - var9) * 0.04;
         }

         if (var12 >= 330) {
            var9 += 0.028;
         }

         var5 += Math.cos(var9) * var13;
         var7 += Math.sin(var9) * var13;
         var11 += var12 >= 260 && var12 < 330 ? 1 : 2 + var0.nextInt(2);
         var3.add(var4, (float)var5, (float)var7, var11, false);
         if (var12 == 150) {
            var3.mark("death", var4, (float)var5, (float)var7, var11);
         }

         if (var12 == 90 || var12 == 420) {
            var3.mark("kill", var4, (float)var5, (float)var7, var11);
         }
      }

      return var3;
   }
}
