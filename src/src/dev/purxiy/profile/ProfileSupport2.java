// Sourced By DevFFNightmare
package dev.purxiy.profile;

import dev.purxiy.i18n.Translations;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public final class ProfileSupport2 {
   private ProfileSupport2() {
   }

   public static String duration(double var0) {
      long var2 = Math.round(Math.max(0.0, var0) / 60.0);
      if (var2 < 60L) {
         return var2 + "m";
      } else {
         long var4 = var2 / 60L;
         if (var4 >= 100L) {
            return count(var4) + "h";
         } else {
            long var6 = var2 % 60L;
            return var6 == 0L ? var4 + "h" : var4 + "h " + var6 + "m";
         }
      }
   }

   public static String durationShort(double var0) {
      if (var0 < 3600.0) {
         return Math.round(var0 / 60.0) + "m";
      } else {
         double var2 = var0 / 3600.0;
         String var4 = var2 == Math.rint(var2) ? Long.toString(Math.round(var2)) : String.format(Locale.ROOT, "%.1f", var2);
         return var4 + "h";
      }
   }

   public static String distance(double var0) {
      if (var0 < 1000.0) {
         return Math.round(var0) + " m";
      } else {
         double var2 = var0 / 1000.0;
         String var4 = var2 < 100.0 ? String.format(Locale.ROOT, "%.1f", var2) : count(Math.round(var2));
         return var4 + " km";
      }
   }

   public static String distanceShort(double var0) {
      if (var0 < 1000.0) {
         long var5 = Math.round(var0);
         return var5 + (Translations.russian() ? " м" : "m");
      } else {
         double var2 = var0 / 1000.0;
         String var4 = var2 == Math.rint(var2) ? Long.toString(Math.round(var2)) : String.format(Locale.ROOT, "%.1f", var2);
         return var4 + "km";
      }
   }

   public static String count(long var0) {
      if (var0 < 10000L) {
         return String.format(Locale.ROOT, "%,d", var0);
      } else {
         return var0 < 1000000L ? trim(var0 / 1000.0) + "k" : trim(var0 / 1000000.0) + "M";
      }
   }

   public static String countShort(long var0) {
      return var0 < 1000L ? Long.toString(var0) : trim(var0 / 1000.0) + "k";
   }

   public static String speed(double var0) {
      return Math.round(var0 * 3.6) + " km/h";
   }

   public static String day(LocalDate var0) {
      String var1 = weekday(var0);
      return var1 + " " + var0.getDayOfMonth() + " " + month(var0);
   }

   public static String date(LocalDate var0) {
      int var1 = var0.getDayOfMonth();
      return var1 + " " + month(var0);
   }

   public static String longDate(LocalDate var0) {
      int var1 = var0.getDayOfMonth();
      return var1 + " " + month(var0) + " " + var0.getYear();
   }

   public static String weekday(LocalDate var0) {
      return var0.getDayOfWeek().getDisplayName(TextStyle.SHORT, Translations.locale());
   }

   public static String month(LocalDate var0) {
      return var0.getMonth().getDisplayName(TextStyle.SHORT, Translations.locale());
   }

   public static String percent(double var0) {
      long var2 = Math.round(var0 * 100.0);
      return (var2 > 0L ? "+" : (var2 < 0L ? "−" : "")) + Math.abs(var2) + "%";
   }

   private static String trim(double var0) {
      String var2 = String.format(Locale.ROOT, var0 < 100.0 ? "%.1f" : "%.0f", var0);
      return var2.endsWith(".0") ? var2.substring(0, var2.length() - 2) : var2;
   }
}
