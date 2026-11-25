package dev.purxiy.modules.profile;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Random;

final class ProfileFactory {
   static final boolean c = true;
   private static final LocalDate F = LocalDate.of(2026, 9, 27);
   private static final long[] d = new long[]{
      -2511230367586225631L,
      -419131040538956975L,
      2918702347894323546L,
      6039937757129311889L,
      -1525187325789111274L,
      2294673710812973226L,
      -5812639300396025408L,
      4526936679331415218L,
      -7708988612846481883L,
      6560048767537021411L
   };

   private ProfileFactory() {
   }

   static LocalDate today() {
      return F;
   }

   static ProfileLoader create() {
      long var0 = 124684133651877L;
      long var2 = var0 ^ 73144181694277L;
      Random var4 = new Random(1927L);
      ProfileLoader var5 = new ProfileLoader();
      var5.T = "Player";
      var5.D = F.minusDays(73L);
      var5.w = true;
      var5.q = 4;
      ProfileLoader3 var6 = var5.E;
      var6.A = 348060.0;
      var6.j = 88400.0;
      var6.B = 61200.0;
      var6.u = 4100.0;
      var6.J = 38700.0;
      var6.y = 9300.0;
      var6.d = 12800.0;
      var6.E = 41870;
      var6.t = 29410;
      var6.n = 6120;
      var6.k = 43;
      var6.Y = 1288;
      var6.g = 97;
      var6.L = 540;

      for (int var7 = 73; var7 >= 0; var7--) {
         LocalDate var8 = F.minusDays(var7);
         boolean var9 = var8.getDayOfWeek() == DayOfWeek.SATURDAY || var8.getDayOfWeek() == DayOfWeek.SUNDAY;
         if (var7 <= 9 || !(var4.nextFloat() < (var9 ? 0.08F : 0.3F))) {
            double var10 = var9 ? 2.2 + var4.nextGaussian() * 1.0 : 1.05 + var4.nextGaussian() * 0.55;
            double var12 = var10 * (0.75 + 0.5 * (73 - var7) / 73.0);
            if (var7 >= 19 && var7 <= 23) {
               var12++;
            }

            if (var7 == 0) {
               var12 = 1.4;
            }

            var10 = Math.max(0.2, var12);
            ProfileLoader3 var14 = var5.day(var8);
            var14.A = var10 * 3600.0;
            double var15 = 1.1 + var4.nextFloat() * 1.4;
            double var17 = var14.A * var15;
            var14.j = var17 * 0.34;
            var14.B = var17 * 0.3;
            var14.J = var17 * (0.18 + var4.nextFloat() * 0.1);
            var14.u = var17 * 0.04;
            var14.y = var17 * 0.06;
            var14.d = var17 * 0.03;
            var14.E = (int)(var10 * (170 + var4.nextInt(160)));
            var14.t = (int)(var10 * (90 + var4.nextInt(150)));
            var14.n = (int)(var10 * (35 + var4.nextInt(70)));
            var14.k = var4.nextFloat() < 0.4F ? var4.nextInt(4) : 0;
            var14.Y = (int)(var10 * (6 + var4.nextInt(14)));
            var14.g = var4.nextFloat() < 0.5F ? 1 + var4.nextInt(2) : 0;
            var14.L = (int)(var10 * (2 + var4.nextInt(7)));
            long var19 = var8.atTime(19, 0).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
            String var21 = var4.nextFloat() < 0.55F ? "Hypixel" : (var4.nextFloat() < 0.6F ? "Purxiy SMP" : "Aurora");
            var5.addSession(new ProfileLoader.TypeQ(var19, var21, var14.copy()));
            ProfileLoader.TypeB var22 = var5.place(var21.toLowerCase(), var21);
            var22.v = var22.v + var14.A;
         }
      }

      var5.place("hypixel", "Hypixel").v += 72000.0;
      var5.H = 31.4;
      var5.n = c(var2, var4);
      return var5;
   }

   static ProfileLoader.TypeQ live(ProfileLoader var0) {
      ProfileLoader3 var1 = var0.c.get(F).copy();
      double var2 = var0.n == null ? 1.0 : var0.n.length() / Math.max(1.0, var1.distance());
      var1.j *= var2;
      var1.B *= var2;
      var1.u *= var2;
      var1.J *= var2;
      var1.y *= var2;
      var1.d *= var2;
      long var4 = F.atTime(20, 12).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      return new ProfileLoader.TypeQ(var4, "Purxiy SMP", var1);
   }

   private static ProfileLoader2 c(long var0, Random var2) {
      ProfileLoader.ensureSourceClassInitialized();
      long var3 = F.atTime(20, 12).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
      ProfileLoader2 var5 = new ProfileLoader2(var3, "Purxiy SMP");
      String var6 = "minecraft:overworld";
      double var7 = 214.0;
      double var9 = -96.0;
      double var11 = 0.6;
      int var13 = 0;

      for (int var14 = 0; var14 < 520; var14++) {
         var11 += Math.sin(var14 * 0.045) * 0.06 + var2.nextGaussian() * 0.05;
         long var15 = 57039271840479L;
         int var18 = var15 < 0L ? -1 : 1;
         double var18x = var14 < 260 ? 2.3 : (var14 < 330 ? 6.5 : 2.1);
         long var20 = 57039271840479L;
         int var22 = var20 < 0L ? -1 : 1;
         var22 = 18197;
         long var23 = 57039271840479L;
         int var36 = var23 < 0L ? -1 : 1;
         if (var14 >= 260) {
            long var25 = 57039271840479L;
            int var27 = var25 < 0L ? -1 : 1;
            if (var14 < 330) {
               var11 += (Math.atan2(-96.0 - var9, 214.0 - var7) - var11) * 0.04;
            }
         }

         long var36x = 57039271840479L;
         int var39 = var36x < 0L ? -1 : 1;
         if (var14 >= 330) {
            var11 += 0.028;
         }

         label96: {
            var7 += Math.cos(var11) * var18x;
            var9 += Math.sin(var11) * var18x;
            long var27 = 57039271840480L;
            int var30 = var27 < 0L ? -1 : 1;
            long var30x = 57039271840480L;
            int var30x = var30x < 0L ? -1 : 1;
            if (var14 >= 260) {
               long var32 = 57039271840480L;
               int var32x = var32 < 0L ? -1 : 1;
               if (var14 < 330) {
                  var22 = 1;
                  break label96;
               }
            }

            var22 = 2 + var2.nextInt(2);
         }

         var13 += var22;
         var5.add(var6, (float)var7, (float)var9, var13, false);
         long var41 = 57039271840479L;
         int var44 = var41 < 0L ? -1 : 1;
         if (var14 == 150) {
            var5.mark("death", var6, (float)var7, (float)var9, var13);
         }

         long var29 = 57039271840479L;
         int var46 = var29 < 0L ? -1 : 1;
         if (var14 == 90 || var14 == 420) {
            var5.mark("kill", var6, (float)var7, (float)var9, var13);
         }
      }

      long var34 = 57039271840480L;
      int var16 = var34 < 0L ? -1 : 1;
      return var5;
   }
}
