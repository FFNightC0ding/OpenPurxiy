// Sourced By DevFFNightmare
package dev.purxiy.modules.fakepay;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.class_2561;
import net.minecraft.class_2583;
import net.minecraft.class_5250;

final class FakePayCodec {
   private static String[] E;
   private static String[] f;
   private static int[] G;
   private static Pattern M;
   private static final double C = 10.0;
   private static final double e = 10.0;
   public static int g = 0;

   private FakePayCodec() {
   }

   static double parse( String var0) {
      long var1 = 122323283390996L;
      long var3 = var1 ^ 129802307831239L;
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
            for (int var6 = 0; var6 < E.length; var6++) {
               if (var5.endsWith(E[var6]) && var5.length() > E[var6].length()) {
                  double var7 = W(var5.substring(0, var5.length() - E[var6].length()), var3);
                  return var7 * Math.pow(10.0, G[var6]);
               }
            }

            return W(var5, var3);
         }
      }
   }

   private static double W(String var0, long var1) {
      try {
         double var3 = Double.parseDouble(var0);
         return Double.isFinite(var3) ? var3 : Double.NaN;
      } catch (NumberFormatException var5) {
         return Double.NaN;
      }
   }

   static String shortForm(double var0) {
      long var2 = 25760467840230L;
      long var4 = var2 ^ 3534168947652L;
      double var6 = Math.abs(var0);

      for (int var8 = 0; var8 < G.length; var8++) {
         double var9 = Math.pow(10.0, G[var8]);
         if (var6 >= var9) {
            double var11 = Math.round(var0 / var9 * 10.0) / 10.0;
            return k(var11, var4) + f[var8];
         }
      }

      return Long.toString(Math.round(var0));
   }

   private static String k(double var0, long var2) {
      return var0 == Math.rint(var0) ? Long.toString((long)var0) : Double.toString(var0);
   }

   static class_2561 replaceNumber(class_2561 var0, String var1) {
      long var2 = 67732182237931L;
      long var4 = var2 ^ 119554754339683L;
      return l(var0, var1, var4, false);
   }

   static class_2561 replaceValue(class_2561 var0, String var1) {
      long var2 = 48767260751121L;
      long var4 = var2 ^ 137974234780825L;
      return l(var0, var1, var4, true);
   }

   private static class_2561 l(class_2561 var0, String var1, long var2, boolean var4) {
      long var5 = 100665270967463L;
      ArrayList var7 = new ArrayList();
      ArrayList var8 = new ArrayList();
      var0.method_27658((var2x, var3) -> {
         var7.add(var2x);
         var8.add(var3);
         return Optional.empty();
      }, class_2583.field_24360);
      String var9 = String.join("", var8);
      int var10;
      int var11;
      if (var4) {
         var10 = g(var9, var5);
         var11 = var9.length();
      } else {
         Matcher var12 = M.matcher(var9);
         var10 = -1;

         for (var11 = -1; var12.find(); var11 = var12.end()) {
            int var13 = var12.start();
            long var14 = 89224221057415L;
            if (89224221057415L == 0L) {
               boolean var10000 = false;
            } else {
               int var41 = var14 < 0L ? -1 : 1;
            }

            var10 = var13;
         }
      }

      long var38 = 89224221057416L;
      if (89224221057416L == 0L) {
         boolean var42 = false;
      } else {
         int var43 = var38 < 0L ? -1 : 1;
      }

      if (var10 < 0) {
         return var0;
      } else {
         class_5250 var15 = class_2561.method_43473();
         int var16 = 0;
         boolean var17 = false;

         for (int var18 = 0; var18 < var8.size(); var18++) {
            String var19 = (String)var8.get(var18);
            class_2583 var20 = (class_2583)var7.get(var18);
            long var22 = 89224221057415L;
            if (89224221057415L == 0L) {
               boolean var44 = false;
            } else {
               int var45 = var22 < 0L ? -1 : 1;
            }

            int var25 = var16 + var19.length();
            int var26 = Math.min(var25, var10);
            long var27 = 89224221057415L;
            if (89224221057415L == 0L) {
               boolean var46 = false;
            } else {
               int var47 = var27 < 0L ? -1 : 1;
            }

            if (var26 > var16) {
               var15.method_10852(class_2561.method_43470(var19.substring(0, var26 - var16)).method_10862(var20));
            }

            if (!var17) {
               long var30 = 89224221057415L;
               if (89224221057415L == 0L) {
                  boolean var48 = false;
               } else {
                  int var49 = var30 < 0L ? -1 : 1;
               }

               long var32 = 89224221057415L;
               if (89224221057415L == 0L) {
                  boolean var50 = false;
               } else {
                  int var51 = var32 < 0L ? -1 : 1;
               }

               if (var10 >= var16) {
                  long var34 = 89224221057415L;
                  if (89224221057415L == 0L) {
                     boolean var52 = false;
                  } else {
                     int var53 = var34 < 0L ? -1 : 1;
                  }

                  long var36 = 89224221057415L;
                  if (89224221057415L == 0L) {
                     boolean var54 = false;
                  } else {
                     int var55 = var36 < 0L ? -1 : 1;
                  }

                  if (var10 < var25) {
                     var15.method_10852(class_2561.method_43470(var1).method_10862(var20));
                     var17 = true;
                  }
               }
            }

            int var40 = Math.max(var16, var11);
            long var31 = 89224221057415L;
            if (89224221057415L == 0L) {
               boolean var56 = false;
            } else {
               int var57 = var31 < 0L ? -1 : 1;
            }

            long var33 = 89224221057415L;
            if (89224221057415L == 0L) {
               boolean var58 = false;
            } else {
               int var59 = var33 < 0L ? -1 : 1;
            }

            if (var25 > var40) {
               var15.method_10852(class_2561.method_43470(var19.substring(var40 - var16)).method_10862(var20));
            }

            var16 = var25;
         }

         long var39 = 89224221057415L;
         if (89224221057415L == 0L) {
            boolean var60 = false;
         } else {
            int var61 = var39 < 0L ? -1 : 1;
         }

         if (!var17) {
            var15.method_10852(class_2561.method_43470(var1));
         }

         return var15;
      }
   }

   private static int g(String var0, long var1) {
      for (int var3 = 0; var3 < var0.length(); var3++) {
         char var4 = var0.charAt(var3);
         long var5 = 100665270967463L;
         if (100665270967463L == 0L) {
            boolean var10000 = false;
         } else {
            int var13 = var5 < 0L ? -1 : 1;
         }

         boolean var8 = Character.isDigit(var4);
         long var9 = 100665270967463L;
         if (100665270967463L == 0L) {
            boolean var14 = false;
         } else {
            int var15 = var9 < 0L ? -1 : 1;
         }

         if (var8) {
            return var3;
         }
      }

      long var12 = 100665270967463L;
      if (100665270967463L == 0L) {
         boolean var16 = false;
      } else {
         int var17 = var12 < 0L ? -1 : 1;
      }

      return -1;
   }

   public static void ensureSourceClassInitialized() {
   }

}
