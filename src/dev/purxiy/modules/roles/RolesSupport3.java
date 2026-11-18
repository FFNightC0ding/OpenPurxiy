// Sourced By DevFFNightmare
package dev.purxiy.modules.roles;

import dev.purxiy.render.BlendMode;
import java.util.Arrays;

public final class RolesSupport3 {
   public static final float m = 1.428148F;
   public static final float W = 10.0F;
   public static final float v = 0.6F;
   public static final float K = 1.5F;
   public static final double p = 1.25;
   public static final float M = 0.46F;
   private static final String a = ".5";

   private RolesSupport3() {
   }

   public static float scale(float var0, float var1, float var2) {
      float var3 = var1 / 1.428148F * 10.0F / Math.max(0.05F, var0);
      float var4 = 1.0F + (var3 - 1.0F) * clamp01(var2);
      return Math.max(0.6F, Math.min(1.5F, var4));
   }

   public static int readable(int var0, int var1) {
      int var2 = var0 | 0xFF000000;
      if (lightness(var2) < 0.46F) {
         float[] var3 = hsl(var2);
         float var4 = var3[1] * 0.9F;
         float var5 = var3[2];
         float var6 = 1.0F;

         for (int var7 = 0; var7 < 16; var7++) {
            float var8 = (var5 + var6) / 2.0F;
            if (lightness(fromHsl(var3[0], var4, var8)) < 0.46F) {
               var5 = var8;
            } else {
               var6 = var8;
            }
         }

         var2 = fromHsl(var3[0], var4, var6);
      }

      var2 = BlendMode.lerp(var2, var1, 0.12F);
      return var0 & 0xFF000000 | var2 & 16777215;
   }

   static float[] hsl(int var0) {
      float var1 = BlendMode.red(var0) / 255.0F;
      float var2 = BlendMode.green(var0) / 255.0F;
      float var3 = BlendMode.blue(var0) / 255.0F;
      float var4 = Math.max(var1, Math.max(var2, var3));
      float var5 = Math.min(var1, Math.min(var2, var3));
      float var6 = (var4 + var5) / 2.0F;
      float var7 = 0.0F;
      float var8 = 0.0F;
      float var9 = var4 - var5;
      if (var9 > 1.0E-6F) {
         var8 = var6 > 0.5F ? var9 / (2.0F - var4 - var5) : var9 / (var4 + var5);
         if (var4 == var1) {
            var7 = (var2 - var3) / var9 + (var2 < var3 ? 6 : 0);
         } else if (var4 == var2) {
            var7 = (var3 - var1) / var9 + 2.0F;
         } else {
            var7 = (var1 - var2) / var9 + 4.0F;
         }

         var7 /= 6.0F;
      }

      return new float[]{var7, var8, var6};
   }

   static int fromHsl(float var0, float var1, float var2) {
      long var3 = 129533424824456L;
      long var5 = (var3 ^ 18300240075493L) >>> 16;
      int var7 = (int)((var3 ^ 18300240075493L) << 48 >>> 48);
      if (var1 <= 1.0E-6F) {
         return BlendMode.argb(1.0F, var2, var2, var2);
      } else {
         float var8 = var2 < 0.5F ? var2 * (1.0F + var1) : var2 + var1 - var2 * var1;
         float var9 = 2.0F * var2 - var8;
         return BlendMode.argb(
            1.0F,
            a(var9, var5, var8, var0 + 0.33333334F, (char)var7),
            a(var9, var5, var8, var0, (char)var7),
            a(var9, var5, var8, var0 - 0.33333334F, (char)var7)
         );
      }
   }

   private static float a(float var0, long var1, float var3, float var4, char var5) {
      RolesRegistry.ensureSourceClassInitialized();
      char var6 = '\uea6d';
      float var7;
      if (var4 < 0.0F) {
         var7 = var4 + 1.0F;
      } else {
         var6 = '\uea6d';
         var7 = var4 > 1.0F ? var4 - 1.0F : var4;
      }

      long var8 = 1701544585L;
      if (1701544585L == 0L) {
         boolean var10000 = false;
      } else {
         int var11 = var8 < 0L ? -1 : 1;
      }

      if (var7 < 0.16666667F) {
         return var0 + (var3 - var0) * 6.0F * var7;
      } else if (var7 < 0.5F) {
         return var3;
      } else {
         return var7 < 0.6666667F ? var0 + (var3 - var0) * (0.6666667F - var7) * 6.0F : var0;
      }
   }

   public static float lightness(int var0) {
      return (0.299F * BlendMode.red(var0) + 0.587F * BlendMode.green(var0) + 0.114F * BlendMode.blue(var0)) / 255.0F;
   }

   public static float contrast(int var0, int var1) {
      long var2 = 116195996837158L;
      long var4 = var2 ^ 132082079563865L;
      float var6 = x(var0, var4);
      float var7 = x(var1, var4);
      return (Math.max(var6, var7) + 0.05F) / (Math.min(var6, var7) + 0.05F);
   }

   private static float x(int var0, long var1) {
      long var3 = 92174568698447L;
      return 0.2126F * b(var3, BlendMode.red(var0)) + 0.7152F * b(var3, BlendMode.green(var0)) + 0.0722F * b(var3, BlendMode.blue(var0));
   }

   private static float b(long var0, int var2) {
      RolesRegistry.ensureSourceClassInitialized();
      float var3 = var2 / 255.0F;
      long var4 = 92174568698446L;
      if (92174568698446L == 0L) {
         boolean var10000 = false;
      } else {
         int var7 = var4 < 0L ? -1 : 1;
      }

      return var3 <= 0.04045F ? var3 / 12.92F : (float)Math.pow((var3 + 0.055F) / 1.055F, 2.4F);
   }

   public static int cluster(double[] var0, double[] var1, double[] var2, int var3, double var4, int[] var6) {
      long var7 = 47405953971212L;
      long var9 = var7 ^ 42365584330439L;
      int[] var11 = new int[var3];
      int var12 = 0;

      while (var12 < var3) {
         var11[var12] = var12++;
      }

      double var13 = var4 * var4;

      for (int var15 = 0; var15 < var3; var15++) {
         for (int var16 = var15 + 1; var16 < var3; var16++) {
            double var17 = var0[var15] - var0[var16];
            double var19 = var2[var15] - var2[var16];
            if (var17 * var17 + var19 * var19 <= var13 && Math.abs(var1[var15] - var1[var16]) <= 1.0) {
               int var21 = W(var11, var15, var9);
               int var22 = W(var11, var16, var9);
               if (var21 != var22) {
                  var11[Math.max(var21, var22)] = Math.min(var21, var22);
               }
            }
         }
      }

      int var23 = 0;
      int[] var24 = new int[var3];
      Arrays.fill(var24, -1);

      for (int var25 = 0; var25 < var3; var25++) {
         int var18 = W(var11, var25, var9);
         if (var24[var18] < 0) {
            var24[var18] = var23++;
         }

         var6[var25] = var24[var18];
      }

      return var23;
   }

   private static int W(int[] var0, int var1, long var2) {
      RolesRegistry.ensureSourceClassInitialized();

      while (var0[var1] != var1) {
         var0[var1] = var0[var0[var1]];
         long var4 = 14935993170635L;
         if (14935993170635L == 0L) {
            boolean var10000 = false;
         } else {
            int var8 = var4 < 0L ? -1 : 1;
         }

         var1 = var0[var1];
      }

      long var7 = 14935993170635L;
      if (14935993170635L == 0L) {
         boolean var9 = false;
      } else {
         int var10 = var7 < 0L ? -1 : 1;
      }

      return var1;
   }

   public static String count(int var0) {
      if (var0 < 1000) {
         return Integer.toString(var0);
      } else if (var0 < 10000) {
         int var1 = Math.round(var0 / 100.0F);
         return var1 % 10 == 0 ? var1 / 10 + "k" : var1 / 10 + "." + var1 % 10 + "k";
      } else {
         return Math.round(var0 / 1000.0F) + "k";
      }
   }

   public static String hearts(float var0) {
      int var1 = Math.max(0, Math.round(var0));
      return var1 % 2 == 0 ? Integer.toString(var1 / 2) : var1 / 2 + a;
   }

   public static float clamp01(float var0) {
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

}
