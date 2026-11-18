// Sourced By DevFFNightmare
package dev.purxiy.modules.nametags;

import dev.purxiy.render.BlendMode;
import java.util.Arrays;

public final class NameTagsSupport2 {
   public static final float Y = 1.428148F;
   public static final float T = 10.0F;
   public static final float q = 0.6F;
   public static final float y = 1.5F;
   public static final double Z = 1.25;
   public static final float G = 0.46F;
   private static final String a = ".5";

   private NameTagsSupport2() {
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
      long var3 = 55084802019542L;
      long var5 = var3 ^ 114661973021893L;
      if (var1 <= 1.0E-6F) {
         return BlendMode.argb(1.0F, var2, var2, var2);
      } else {
         float var7 = var2 < 0.5F ? var2 * (1.0F + var1) : var2 + var1 - var2 * var1;
         float var8 = 2.0F * var2 - var7;
         return BlendMode.argb(1.0F, v(var8, var7, var5, var0 + 0.33333334F), v(var8, var7, var5, var0), v(var8, var7, var5, var0 - 0.33333334F));
      }
   }

   private static float v(float var0, float var1, long var2, float var4) {
      TagPainter.ensureSourceClassInitialized();
      float var5 = var4 < 0.0F ? var4 + 1.0F : (var4 > 1.0F ? var4 - 1.0F : var4);
      long var6 = 99307095409683L;
      if (99307095409683L == 0L) {
         boolean var10000 = false;
      } else {
         int var9 = var6 < 0L ? -1 : 1;
      }

      if (var5 < 0.16666667F) {
         return var0 + (var1 - var0) * 6.0F * var5;
      } else if (var5 < 0.5F) {
         return var1;
      } else {
         return var5 < 0.6666667F ? var0 + (var1 - var0) * (0.6666667F - var5) * 6.0F : var0;
      }
   }

   public static float lightness(int var0) {
      return (0.299F * BlendMode.red(var0) + 0.587F * BlendMode.green(var0) + 0.114F * BlendMode.blue(var0)) / 255.0F;
   }

   public static float contrast(int var0, int var1) {
      long var2 = 139928808042143L;
      long var4 = var2 ^ 80576484417341L;
      float var6 = I(var4, var0);
      float var7 = I(var4, var1);
      return (Math.max(var6, var7) + 0.05F) / (Math.min(var6, var7) + 0.05F);
   }

   private static float I(long var0, int var2) {
      long var3 = 66790606221657L;
      return 0.2126F * o(var3, BlendMode.red(var2)) + 0.7152F * o(var3, BlendMode.green(var2)) + 0.0722F * o(var3, BlendMode.blue(var2));
   }

   private static float o(long var0, int var2) {
      TagPainter.ensureSourceClassInitialized();
      float var3 = var2 / 255.0F;
      long var4 = 66790606221656L;
      if (66790606221656L == 0L) {
         boolean var10000 = false;
      } else {
         int var7 = var4 < 0L ? -1 : 1;
      }

      return var3 <= 0.04045F ? var3 / 12.92F : (float)Math.pow((var3 + 0.055F) / 1.055F, 2.4F);
   }

   public static int cluster(double[] var0, double[] var1, double[] var2, int var3, double var4, int[] var6) {
      long var7 = 58458539278274L;
      long var9 = var7 ^ 59940671534070L;
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
               int var21 = k(var9, var11, var15);
               int var22 = k(var9, var11, var16);
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
         int var18 = k(var9, var11, var25);
         if (var24[var18] < 0) {
            var24[var18] = var23++;
         }

         var6[var25] = var24[var18];
      }

      return var23;
   }

   private static int k(long var0, int[] var2, int var3) {
      TagPainter.ensureSourceClassInitialized();

      while (var2[var3] != var3) {
         var2[var3] = var2[var2[var3]];
         long var4 = 4049987996723L;
         if (4049987996723L == 0L) {
            boolean var10000 = false;
         } else {
            int var8 = var4 < 0L ? -1 : 1;
         }

         var3 = var2[var3];
      }

      long var7 = 4049987996724L;
      if (4049987996724L == 0L) {
         boolean var9 = false;
      } else {
         int var10 = var7 < 0L ? -1 : 1;
      }

      return var3;
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
