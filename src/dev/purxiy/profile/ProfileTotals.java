package dev.purxiy.profile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map.Entry;

public final class ProfileTotals {
   public static final double ACTIVE_DAY = 60.0;
   private static final double LEVEL_MINUTES = 15.0;

   private ProfileTotals() {
   }

   public static ProfileLoader lifetime(ProfileDay var0) {
      ProfileLoader var1 = var0.baseline.copy();

      for (ProfileLoader var3 : var0.days.values()) {
         var1.add(var3);
      }

      return var1;
   }

   public static ProfileLoader range(ProfileDay var0, LocalDate var1, int var2) {
      ProfileLoader var3 = new ProfileLoader();

      for (ProfileLoader var5 : var0.days.subMap(var1.minusDays(var2 - 1L), true, var1, true).values()) {
         var3.add(var5);
      }

      return var3;
   }

   public static double[] series(ProfileDay var0, ProfileKind var1, LocalDate var2, int var3) {
      double[] var4 = new double[var3];
      LocalDate var5 = var2.minusDays(var3 - 1L);

      for (Entry var7 : var0.days.subMap(var5, true, var2, true).entrySet()) {
         var4[(int)(((LocalDate)var7.getKey()).toEpochDay() - var5.toEpochDay())] = var1.of((ProfileLoader)var7.getValue());
      }

      return var4;
   }

   public static int streak(ProfileDay var0, LocalDate var1) {
      LocalDate var2 = active(var0, var1) ? var1 : var1.minusDays(1L);

      int var3;
      for (var3 = 0; active(var0, var2); var2 = var2.minusDays(1L)) {
         var3++;
      }

      return var3;
   }

   public static int bestStreak(ProfileDay var0) {
      int var1 = 0;
      int var2 = 0;
      LocalDate var3 = null;

      for (Entry var5 : var0.days.entrySet()) {
         if (!(((ProfileLoader)var5.getValue()).play < 60.0)) {
            var2 = var3 != null && ((LocalDate)var5.getKey()).equals(var3.plusDays(1L)) ? var2 + 1 : 1;
            var1 = Math.max(var1, var2);
            var3 = (LocalDate)var5.getKey();
         }
      }

      return var1;
   }

   public static boolean active(ProfileDay var0, LocalDate var1) {
      ProfileLoader var2 = var0.days.get(var1);
      return var2 != null && var2.play >= 60.0;
   }

   public static int activeDays(ProfileDay var0) {
      int var1 = 0;

      for (ProfileLoader var3 : var0.days.values()) {
         if (var3.play >= 60.0) {
            var1++;
         }
      }

      return var1;
   }


   public static Entry<LocalDate, ProfileLoader> busiest(ProfileDay var0, ProfileKind var1) {
      Entry var2 = null;

      for (Entry var4 : var0.days.entrySet()) {
         if (var1.of((ProfileLoader)var4.getValue()) > 0.0
            && (var2 == null || var1.of((ProfileLoader)var4.getValue()) > var1.of((ProfileLoader)var2.getValue()))) {
            var2 = var4;
         }
      }

      return var2 == null || var1 == ProfileKind.PLAYTIME && ((ProfileLoader)var2.getValue()).play < 60.0 ? null : var2;
   }


   public static ProfileDay.DayCache longest(List<ProfileDay.DayCache> var0,  ProfileDay.DayCache var1) {
      ProfileDay.DayCache var2 = var1 != null && var1.totals.play > 0.0 ? var1 : null;

      for (ProfileDay.DayCache var4 : var0) {
         if (var2 == null || var4.totals.play > var2.totals.play) {
            var2 = var4;
         }
      }

      return var2 != null && var2.totals.play >= 60.0 ? var2 : null;
   }


   public static ProfileDay.DayRecord favourite(ProfileDay var0) {
      ProfileDay.DayRecord var1 = null;

      for (ProfileDay.DayRecord var3 : var0.places.values()) {
         if (var1 == null || var3.play > var1.play) {
            var1 = var3;
         }
      }

      return var1 != null && var1.play > 0.0 ? var1 : null;
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


   public static ProfileTotals.TotalKind archetype(ProfileLoader var0) {
      ProfileTotals.TotalKind var1 = null;
      double var2 = 0.12;

      for (ProfileTotals.TotalKind var7 : ProfileTotals.TotalKind.values()) {
         double var8 = var7.score(var0);
         if (var8 > var2) {
            var2 = var8;
            var1 = var7;
         }
      }

      return var0.play < 600.0 ? null : var1;
   }

   public static enum TotalKind {
      FIGHTER("Fighter", "swords", "hits and takedowns"),
      MINER("Miner", "pickaxe", "blocks mined"),
      BUILDER("Builder", "hammer", "blocks placed"),
      EXPLORER("Explorer", "compass", "blocks travelled"),
      SOCIAL("Socialite", "message-circle", "messages sent");

      public final String label;
      public final String icon;
      public final String unit;

      private TotalKind(String nullxx, String nullxxx, String nullxxxx) {
         this.label = nullxx;
         this.icon = nullxxx;
         this.unit = nullxxxx;
      }

      public double rate(ProfileLoader var1) {
         double var2 = Math.max(var1.play / 3600.0, 0.25);

         return switch (this) {
            case FIGHTER -> (var1.hits + 6.0 * var1.allKills()) / var2;
            case MINER -> var1.broken / var2;
            case BUILDER -> var1.placed / var2;
            case EXPLORER -> var1.distance() / var2;
            case SOCIAL -> var1.chat / var2;
         };
      }

      double reference() {
         return switch (this) {
            case FIGHTER -> 90.0;
            case MINER -> 260.0;
            case BUILDER -> 200.0;
            case EXPLORER -> 3200.0;
            case SOCIAL -> 12.0;
         };
      }

      public double score(ProfileLoader var1) {
         return 1.0 - Math.exp(-this.rate(var1) / this.reference());
      }

      private static ProfileTotals.TotalKind[] $values() {
         return new ProfileTotals.TotalKind[]{FIGHTER, MINER, BUILDER, EXPLORER, SOCIAL};
      }
   }
}
