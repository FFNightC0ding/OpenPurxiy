// Sourced By DevFFNightmare
package dev.purxiy.modules.fakestats;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_5250;

final class FakeStatsCodec {
   private static String[] v;
   private static String[] G;
   private static int[] g;
   private static Pattern p;
   private static final double T = 10.0;
   private static final double J = 10.0;
   public static boolean Q;

   private FakeStatsCodec() {
   }

   static double parse( String var0) {
      long var1 = 86489153035873L;
      long var3 = var1 ^ 15397860594557L;
      if (var0 == null) {
         return Double.NaN;
      } else {
         String var5 = var0.strip().toLowerCase(Locale.ROOT).replace(",", "").replace("_", "").replace(" ", "");

         while (!var5.isEmpty() && (var5.charAt(0) == '$' || var5.charAt(0) == 8364 || var5.charAt(0) == 163)) {
            var5 = var5.substring(1);
         }

         if (var5.isEmpty()) {
            return Double.NaN;
         } else {
            for (int var6 = 0; var6 < v.length; var6++) {
               if (var5.endsWith(v[var6]) && var5.length() > v[var6].length()) {
                  double var7 = C(var5.substring(0, var5.length() - v[var6].length()), var3);
                  return var7 * Math.pow(10.0, g[var6]);
               }
            }

            return C(var5, var3);
         }
      }
   }

   private static double C(String var0, long var1) {
      try {
         double var3 = Double.parseDouble(var0);
         return Double.isFinite(var3) ? var3 : Double.NaN;
      } catch (NumberFormatException var5) {
         return Double.NaN;
      }
   }

   static String shortForm(double var0) {
      long var2 = 53156368501471L;
      long var4 = var2 ^ 15090523286945L;
      int var6 = (int)((var2 ^ 15090523286945L) >>> 56);
      int var7 = (int)((var2 ^ 15090523286945L) << 8 >>> 32);
      int var8 = (int)(var4 << 40 >>> 40);
      double var9 = Math.abs(var0);

      for (int var11 = 0; var11 < g.length; var11++) {
         double var12 = Math.pow(10.0, g[var11]);
         if (var9 >= var12) {
            double var14 = Math.round(var0 / var12 * 10.0) / 10.0;
            return h((byte)var6, var14, var7, var8) + G[var11];
         }
      }

      return Long.toString(Math.round(var0));
   }

   private static String h(byte var0, double var1, int var3, int var4) {
      return var1 == Math.rint(var1) ? Long.toString((long)var1) : Double.toString(var1);
   }

   static class_2561 replaceNumber(class_2561 var0, String var1) {
      long var2 = 113604136828634L;
      long var4 = var2 ^ 130654250765669L;
      int var6 = (int)((var2 ^ 130654250765669L) >>> 32);
      int var7 = (int)((var2 ^ 130654250765669L) << 32 >>> 48);
      int var8 = (int)(var4 << 48 >>> 48);
      return S(var0, var6, (short)var7, var1, (char)var8, false);
   }

   static class_2561 replaceValue(class_2561 var0, String var1) {
      long var2 = 136791688882355L;
      long var4 = var2 ^ 120875849014028L;
      int var6 = (int)((var2 ^ 120875849014028L) >>> 32);
      int var7 = (int)((var2 ^ 120875849014028L) << 32 >>> 48);
      int var8 = (int)(var4 << 48 >>> 48);
      return S(var0, var6, (short)var7, var1, (char)var8, true);
   }

   private static class_2561 S(class_2561 var0, int var1, short var2, String var3, char var4, boolean var5) {
      long var6 = 19270814462911L;
      int var8 = (int)((var6 ^ 69063657874284L) >>> 48);
      long var9 = (var6 ^ 69063657874284L) << 16 >>> 16;
      ArrayList var11 = new ArrayList();
      ArrayList var12 = new ArrayList();
      var0.method_27658((var2x, var3x) -> {
         var11.add(var2x);
         var12.add(var3x);
         return Optional.empty();
      }, class_2583.field_24360);
      String var13 = String.join("", var12);
      int var14;
      int var15;
      if (var5) {
         var14 = b((short)var8, var13, var9);
         var15 = var13.length();
      } else {
         Matcher var16 = p.matcher(var13);
         var14 = -1;

         for (var15 = -1; var16.find(); var15 = var16.end()) {
            var14 = var16.start();
         }
      }

      short var29 = 4486;
      if (var14 < 0) {
         return var0;
      } else {
         class_5250 var17 = class_2561.method_43473();
         int var18 = 0;
         boolean var19 = false;

         for (int var20 = 0; var20 < var12.size(); var20++) {
            String var21 = (String)var12.get(var20);
            class_2583 var22 = (class_2583)var11.get(var20);
            int var24 = var18 + var21.length();
            short var25 = 4486;
            int var26 = Math.min(var24, var14);
            short var27 = -10740;
            if (var26 > var18) {
               var17.method_10852(class_2561.method_43470(var21.substring(0, var26 - var18)).method_10862(var22));
            }

            if (!var19) {
               var27 = -10740;
               var27 = -10740;
               if (var14 >= var18) {
                  var27 = 4486;
                  var27 = -10740;
                  if (var14 < var24) {
                     var17.method_10852(class_2561.method_43470(var3).method_10862(var22));
                     var19 = true;
                  }
               }
            }

            int var28 = Math.max(var18, var15);
            var25 = -10740;
            if (var24 > var28) {
               var17.method_10852(class_2561.method_43470(var21.substring(var28 - var18)).method_10862(var22));
            }

            var18 = var24;
         }

         var29 = -10740;
         if (!var19) {
            var17.method_10852(class_2561.method_43470(var3));
         }

         return var17;
      }
   }

   private static int b(short var0, String var1, long var2) {
      for (int var4 = 0; var4 < var1.length(); var4++) {
         char var5 = var1.charAt(var4);
         boolean var6 = Character.isDigit(var5);
         long var7 = 52050520412370L;
         if (52050520412370L == 0L) {
            boolean var10000 = false;
         } else {
            int var11 = var7 < 0L ? -1 : 1;
         }

         if (var6) {
            return var4;
         }
      }

      long var10 = 52050520412371L;
      if (52050520412371L == 0L) {
         boolean var12 = false;
      } else {
         int var13 = var10 < 0L ? -1 : 1;
      }

      return -1;
   }

   public static void ensureSourceClassInitialized() {
   }

}
