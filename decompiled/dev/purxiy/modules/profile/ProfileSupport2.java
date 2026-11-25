package dev.purxiy.modules.profile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map.Entry;

public final class ProfileSupport2 {
   public static final double X = 60.0;
   private static final double a = 15.0;

   private ProfileSupport2() {
   }

   public static ProfileLoader3 lifetime(ProfileLoader var0) {
      ProfileLoader3 var1 = var0.E.copy();

      for (ProfileLoader3 var3 : var0.c.values()) {
         var1.add(var3);
      }

      return var1;
   }

   public static ProfileLoader3 range(ProfileLoader var0, LocalDate var1, int var2) {
      ProfileLoader3 var3 = new ProfileLoader3();

      for (ProfileLoader3 var5 : var0.c.subMap(var1.minusDays(var2 - 1L), true, var1, true).values()) {
         var3.add(var5);
      }

      return var3;
   }

   public static double[] series(ProfileLoader var0, ProfileKind var1, LocalDate var2, int var3) {
      double[] var4 = new double[var3];
      LocalDate var5 = var2.minusDays(var3 - 1L);

      for (Entry var7 : var0.c.subMap(var5, true, var2, true).entrySet()) {
         var4[(int)(((LocalDate)var7.getKey()).toEpochDay() - var5.toEpochDay())] = var1.of((ProfileLoader3)var7.getValue());
      }

      return var4;
   }

   public static int streak(ProfileLoader var0, LocalDate var1) {
      LocalDate var2 = active(var0, var1) ? var1 : var1.minusDays(1L);

      int var3;
      for (var3 = 0; active(var0, var2); var2 = var2.minusDays(1L)) {
         var3++;
      }

      return var3;
   }

   public static int bestStreak(ProfileLoader var0) {
      int var1 = 0;
      byte var2 = 0;
      LocalDate var3 = null;

      for (Entry var5 : var0.c.entrySet()) {
         if (!(((ProfileLoader3)var5.getValue()).A < 60.0)) {
            var2 = (byte)(var3 != null && ((LocalDate)var5.getKey()).equals(var3.plusDays(1L)) ? var2 + 1 : 1);
            var1 = Math.max(var1, var2);
            var3 = (LocalDate)var5.getKey();
         }
      }

      return var1;
   }

   public static boolean active(ProfileLoader var0, LocalDate var1) {
      ProfileLoader3 var2 = var0.c.get(var1);
      return var2 != null && var2.A >= 60.0;
   }

   public static int activeDays(ProfileLoader var0) {
      int var1 = 0;

      for (ProfileLoader3 var3 : var0.c.values()) {
         if (var3.A >= 60.0) {
            var1++;
         }
      }

      return var1;
   }

   public static Entry<LocalDate, ProfileLoader3> busiest(ProfileLoader var0, ProfileKind var1) {
      Entry var2 = null;

      for (Entry var4 : var0.c.entrySet()) {
         if (var1.of((ProfileLoader3)var4.getValue()) > 0.0
            && (var2 == null || var1.of((ProfileLoader3)var4.getValue()) > var1.of((ProfileLoader3)var2.getValue()))) {
            var2 = var4;
         }
      }

      return var2 != null && (var1 != ProfileKind.P || !(((ProfileLoader3)var2.getValue()).A < 60.0)) ? var2 : null;
   }

   public static ProfileLoader.TypeQ longest(List<ProfileLoader.TypeQ> var0, ProfileLoader.TypeQ var1) {
      ProfileLoader.TypeQ var2 = var1 != null && var1.Q.A > 0.0 ? var1 : null;

      for (ProfileLoader.TypeQ var4 : var0) {
         if (var2 == null || var4.Q.A > var2.Q.A) {
            var2 = var4;
         }
      }

      return var2 != null && var2.Q.A >= 60.0 ? var2 : null;
   }

   public static ProfileLoader.TypeB favourite(ProfileLoader var0) {
      ProfileLoader.TypeB var1 = null;

      for (ProfileLoader.TypeB var3 : var0.b.values()) {
         if (var1 == null || var3.v > var1.v) {
            var1 = var3;
         }
      }

      return var1 != null && var1.v > 0.0 ? var1 : null;
   }

   public static int level(double var0) {
      return (int)Math.floor(Math.sqrt(Math.max(0.0, var0) / 60.0 / 15.0)) + 1;
   }

   public static double levelStart(int var0) {
      double var1 = var0 - 1;
      return var1 * var1 * 15.0 * 60.0;
   }

   public static double levelProgress(double var0) {
      int var2 = level(var0);
      double var3 = levelStart(var2);
      double var5 = levelStart(var2 + 1);
      return (var0 - var3) / (var5 - var3);
   }

   public static ProfileSupport2.TypeK archetype(ProfileLoader3 var0) {
      ProfileSupport2.TypeK var1 = null;
      double var2 = 0.12;

      for (ProfileSupport2.TypeK var7 : ProfileSupport2.TypeK.values()) {
         double var8 = var7.score(var0);
         if (var8 > var2) {
            var2 = var8;
            var1 = var7;
         }
      }

      return var0.A < 600.0 ? null : var1;
   }

   public static enum TypeK {
      e("Fighter", "swords", "hits and takedowns"),
      A("Miner", "pickaxe", "blocks mined"),
      H("Builder", "hammer", "blocks placed"),
      p("Explorer", "compass", "blocks travelled"),
      F("Socialite", "message-circle", "messages sent");

      public final String M;
      public final String G;
      public final String C;

      private TypeK(String nullxx, String nullxxx, String nullxxxx) {
         this.M = nullxx;
         this.G = nullxxx;
         this.C = nullxxxx;
      }

      public double rate(ProfileLoader3 var1) {
         double var2 = Math.max(var1.A / 3600.0, 0.25);

         return switch (this) {
            case e -> (var1.n + 6.0 * var1.allKills()) / var2;
            case A -> var1.E / var2;
            case H -> var1.t / var2;
            case p -> var1.distance() / var2;
            case F -> var1.L / var2;
         };
      }

      double reference() {
         return switch (this) {
            case e -> 90.0;
            case A -> 260.0;
            case H -> 200.0;
            case p -> 3200.0;
            case F -> 12.0;
         };
      }

      public double score(ProfileLoader3 var1) {
         return 1.0 - Math.exp(-this.rate(var1) / this.reference());
      }
   }
}
