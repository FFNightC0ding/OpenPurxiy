// Sourced By DevFFNightmare
package dev.purxiy.profile;

import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;

final class ProfileCurve {
   static final int LINE_FROM = -4611329;
   static final int LINE_TO = -1012489;
   static final int GLOW = -4907020;
   private static float[] tangents = new float[64];
   private static float[] normalX = new float[256];
   private static float[] normalY = new float[256];

   private ProfileCurve() {
   }

   static int smooth(float[] var0, float[] var1, int var2, int var3, float[] var4, float[] var5) {
      if (var2 <= 1) {
         if (var2 == 1) {
            var4[0] = var0[0];
            var5[0] = var1[0];
         }

         return var2;
      } else {
         if (tangents.length < var2) {
            tangents = new float[var2 * 2];
         }

         float[] var6 = tangents;

         for (int var7 = 0; var7 < var2; var7++) {
            float var8 = var7 > 0 ? (var1[var7] - var1[var7 - 1]) / (var0[var7] - var0[var7 - 1]) : Float.NaN;
            float var9 = var7 < var2 - 1 ? (var1[var7 + 1] - var1[var7]) / (var0[var7 + 1] - var0[var7]) : Float.NaN;
            if (Float.isNaN(var8)) {
               var6[var7] = var9;
            } else if (Float.isNaN(var9)) {
               var6[var7] = var8;
            } else {
               var6[var7] = var8 * var9 <= 0.0F ? 0.0F : (var8 + var9) / 2.0F;
            }
         }

         for (int var14 = 0; var14 < var2 - 1; var14++) {
            float var16 = (var1[var14 + 1] - var1[var14]) / (var0[var14 + 1] - var0[var14]);
            if (Math.abs(var16) < 1.0E-6F) {
               var6[var14] = 0.0F;
               var6[var14 + 1] = 0.0F;
            } else {
               float var18 = var6[var14] / var16;
               float var10 = var6[var14 + 1] / var16;
               float var11 = var18 * var18 + var10 * var10;
               if (var11 > 9.0F) {
                  float var12 = 3.0F / (float)Math.sqrt(var11);
                  var6[var14] = var12 * var18 * var16;
                  var6[var14 + 1] = var12 * var10 * var16;
               }
            }
         }

         int var15 = 0;

         for (int var17 = 0; var17 < var2 - 1; var17++) {
            float var19 = var0[var17 + 1] - var0[var17];

            for (int var20 = 0; var20 < var3; var20++) {
               float var21 = (float)var20 / var3;
               float var22 = var21 * var21;
               float var13 = var22 * var21;
               var4[var15] = var0[var17] + var21 * var19;
               var5[var15] = (2.0F * var13 - 3.0F * var22 + 1.0F) * var1[var17]
                  + (var13 - 2.0F * var22 + var21) * var19 * var6[var17]
                  + (-2.0F * var13 + 3.0F * var22) * var1[var17 + 1]
                  + (var13 - var22) * var19 * var6[var17 + 1];
               var15++;
            }
         }

         var4[var15] = var0[var2 - 1];
         var5[var15] = var1[var2 - 1];
         return var15 + 1;
      }
   }

   static void ribbon(Canvas var0, float[] var1, float[] var2, int var3, float var4, float var5, int var6, int var7, float var8, float var9) {
      if (var3 >= 2) {
         normals(var1, var2, var3);
         float var10 = var4 / 2.0F;
         float var11 = Math.max(0.001F, var9 - var8);

         for (int var12 = 0; var12 < var3 - 1; var12++) {
            int var13 = var12 + 1;
            int var14 = BlendMode.lerp(var6, var7, (var1[var12] - var8) / var11);
            int var15 = BlendMode.lerp(var6, var7, (var1[var13] - var8) / var11);
            int var16 = var14 & 16777215;
            int var17 = var15 & 16777215;
            float var18 = normalX[var12];
            float var19 = normalY[var12];
            float var20 = normalX[var13];
            float var21 = normalY[var13];
            band(var0, var1[var12], var2[var12], var1[var13], var2[var13], var18, var19, var20, var21, -var10 - var5, -var10, var16, var14, var17, var15);
            band(var0, var1[var12], var2[var12], var1[var13], var2[var13], var18, var19, var20, var21, -var10, var10, var14, var14, var15, var15);
            band(var0, var1[var12], var2[var12], var1[var13], var2[var13], var18, var19, var20, var21, var10, var10 + var5, var14, var16, var15, var17);
         }
      }
   }

   static void ribbon(Canvas var0, float[] var1, float[] var2, float[] var3, int var4, float var5, float var6, int var7, int var8) {
      if (var4 >= 2) {
         normals(var1, var2, var4);
         float var9 = var5 / 2.0F;

         for (int var10 = 0; var10 < var4 - 1; var10++) {
            int var11 = var10 + 1;
            int var12 = BlendMode.lerp(var7, var8, var3[var10]);
            int var13 = BlendMode.lerp(var7, var8, var3[var11]);
            int var14 = var12 & 16777215;
            int var15 = var13 & 16777215;
            float var16 = normalX[var10];
            float var17 = normalY[var10];
            float var18 = normalX[var11];
            float var19 = normalY[var11];
            band(var0, var1[var10], var2[var10], var1[var11], var2[var11], var16, var17, var18, var19, -var9 - var6, -var9, var14, var12, var15, var13);
            band(var0, var1[var10], var2[var10], var1[var11], var2[var11], var16, var17, var18, var19, -var9, var9, var12, var12, var13, var13);
            band(var0, var1[var10], var2[var10], var1[var11], var2[var11], var16, var17, var18, var19, var9, var9 + var6, var12, var14, var13, var15);
         }
      }
   }

   static int simplify(float[] var0, float[] var1, float[] var2, int var3, float var4) {
      if (var3 < 3) {
         return var3;
      } else {
         boolean[] var5 = new boolean[var3];
         var5[0] = var5[var3 - 1] = true;
         int[] var6 = new int[var3 * 2];
         int var7 = 0;
         var6[var7++] = 0;
         var6[var7++] = var3 - 1;
         float var8 = var4 * var4;

         while (var7 > 0) {
            int var9 = var6[--var7];
            int var10 = var6[--var7];
            float var11 = var0[var10];
            float var12 = var1[var10];
            float var13 = var0[var9] - var11;
            float var14 = var1[var9] - var12;
            float var15 = var13 * var13 + var14 * var14;
            int var16 = -1;
            float var17 = var8;

            for (int var18 = var10 + 1; var18 < var9; var18++) {
               float var19;
               if (var15 < 1.0E-6F) {
                  float var20 = var0[var18] - var11;
                  float var21 = var1[var18] - var12;
                  var19 = var20 * var20 + var21 * var21;
               } else {
                  float var30 = (var0[var18] - var11) * var14 - (var1[var18] - var12) * var13;
                  var19 = var30 * var30 / var15;
               }

               if (var19 > var17) {
                  var17 = var19;
                  var16 = var18;
               }
            }

            if (var16 >= 0) {
               var5[var16] = true;
               var6[var7++] = var10;
               var6[var7++] = var16;
               var6[var7++] = var16;
               var6[var7++] = var9;
            }
         }

         int var28 = 0;

         for (int var29 = 0; var29 < var3; var29++) {
            if (var5[var29]) {
               var0[var28] = var0[var29];
               var1[var28] = var1[var29];
               var2[var28] = var2[var29];
               var28++;
            }
         }

         return var28;
      }
   }

   static void area(Canvas var0, float[] var1, float[] var2, int var3, float var4, float var5, int var6, int var7) {
      float var8 = Math.max(0.001F, var5 - var4);

      for (int var9 = 0; var9 < var3 - 1; var9++) {
         int var10 = BlendMode.lerp(var6, var7, (var2[var9] - var4) / var8);
         int var11 = BlendMode.lerp(var6, var7, (var2[var9 + 1] - var4) / var8);
         var0.triangle(var1[var9], var2[var9], var1[var9 + 1], var2[var9 + 1], var1[var9 + 1], var5, var10, var11, var7);
         var0.triangle(var1[var9], var2[var9], var1[var9 + 1], var5, var1[var9], var5, var10, var7, var7);
      }
   }

   private static void band(
      Canvas var0,
      float var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      int var11,
      int var12,
      int var13,
      int var14
   ) {
      float var15 = var1 + var5 * var9;
      float var16 = var2 + var6 * var9;
      float var17 = var1 + var5 * var10;
      float var18 = var2 + var6 * var10;
      float var19 = var3 + var7 * var9;
      float var20 = var4 + var8 * var9;
      float var21 = var3 + var7 * var10;
      float var22 = var4 + var8 * var10;
      var0.triangle(var15, var16, var19, var20, var21, var22, var11, var13, var14);
      var0.triangle(var15, var16, var21, var22, var17, var18, var11, var14, var12);
   }

   private static void normals(float[] var0, float[] var1, int var2) {
      if (normalX.length < var2) {
         normalX = new float[var2 * 2];
         normalY = new float[var2 * 2];
      }

      for (int var3 = 0; var3 < var2; var3++) {
         int var4 = Math.max(0, var3 - 1);
         int var5 = Math.min(var2 - 1, var3 + 1);
         float var6 = var0[var5] - var0[var4];
         float var7 = var1[var5] - var1[var4];
         float var8 = (float)Math.sqrt(var6 * var6 + var7 * var7);
         if (var8 < 1.0E-5F) {
            normalX[var3] = var3 > 0 ? normalX[var3 - 1] : 0.0F;
            normalY[var3] = var3 > 0 ? normalY[var3 - 1] : 1.0F;
         } else {
            normalX[var3] = -var7 / var8;
            normalY[var3] = var6 / var8;
         }
      }
   }

   static float clamp01(float var0) {
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   static float smoothstep(float var0) {
      var0 = clamp01(var0);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   static float lerp(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   static double countUp(double var0, float var2) {
      float var3 = clamp01(var2);
      return var0 * (1.0 - Math.pow(1.0F - var3, 3.0));
   }
}
