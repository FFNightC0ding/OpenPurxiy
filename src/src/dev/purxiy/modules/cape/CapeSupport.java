// Sourced By DevFFNightmare
package dev.purxiy.modules.cape;

import net.minecraft.class_10055;
import net.minecraft.class_11659;
import net.minecraft.class_12249;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4608;
import net.minecraft.class_572;
import net.minecraft.class_7833;
import net.minecraft.class_4587.class_4665;

public final class CapeSupport {
   private static final int u = 8;
   private static final float x = 2.0F;
   private static final float C = 0.004166667F;
   private static final float[] U = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
   private static final float[] a = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
   private static long z = 0L;
   private static float G;
   private static boolean J;
   private static final float[] F = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
   private static final float[] f = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
   private static final float[] E = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};
   private static final float[] R = new float[]{0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F};



   private CapeSupport() {
   }

   public static boolean active() {
      return CapeCache.X.flowing();
   }

   public static void submit(class_572<class_10055> var0, class_10055 var1, class_4587 var2, class_11659 var3, int var4, int var5) {
      long var6 = 23954421094631L;
      long var8 = var6 ^ 129900155911766L;
      long var10 = var6 ^ 108711533982044L;
      Y(var8, var1);
      F(var10);
      var0.method_17087(var1);
      var2.method_22903();
      var0.method_63512().method_22703(var2);
      var0.field_3391.method_22703(var2);
      var2.method_46416(0.0F, 0.0F, 0.125F);
      float var12 = (float)Math.toRadians(var1.field_53538 / 2.0F);
      var2.method_22907(class_7833.field_40718.rotation(var12));
      var2.method_22907(class_7833.field_40716.rotation(-var12));
      var3.method_73483(var2, class_12249.method_75982(CapeCache.J), (var1x, var2x) -> {
         long var3x = 125950881545273L;
         K(var1x, var3x, var2x, var4);
      });
      var3.method_73483(var2, class_12249.method_76014(CapeCache.l), (var1x, var2x) -> {
         long var3x = 125950881545273L;
         K(var1x, var3x, var2x, var4);
      });
      var2.method_22909();
   }

   private static void Y(long var0, class_10055 var2) {
      CapeModule.ensureSourceClassInitialized();
      long var3 = System.nanoTime();
      long var5 = 109873557972656L;
      if (109873557972656L == 0L) {
         boolean var10000 = false;
      } else {
         int var42 = var5 < 0L ? -1 : 1;
      }

      float var8 = z == 0L ? 0.0F : (float)(var3 - z) / 1.0E9F;
      long var9 = 109873557972657L;
      if (109873557972657L == 0L) {
         boolean var43 = false;
      } else {
         int var44 = var9 < 0L ? -1 : 1;
      }

      long var12 = 109873557972657L;
      if (109873557972657L == 0L) {
         boolean var45 = false;
      } else {
         int var46 = var12 < 0L ? -1 : 1;
      }

      if (z == 0L || !(var8 < 5.0E-4F)) {
         z = var3;
         float var14 = (float)Math.toRadians(6.0F + var2.field_53537 / 2.0F + var2.field_53536);
         if (J) {
            long var15 = 109873557972657L;
            if (109873557972657L == 0L) {
               boolean var47 = false;
            } else {
               int var48 = var15 < 0L ? -1 : 1;
            }

            if (!(var8 > 0.5F)) {
               float var17 = Math.clamp((var14 - 0.15F) / 0.9F, 0.0F, 1.0F);
               var8 = Math.min(var8, 0.1F);

               for (float var18 = var8; var18 > 1.0E-5F; var18 -= 0.004166667F) {
                  float var19 = Math.min(0.004166667F, var18);
                  G += var19;

                  for (int var20 = 0; var20 < 8; var20++) {
                     long var21 = 109873557972657L;
                     if (109873557972657L == 0L) {
                        boolean var53 = false;
                     } else {
                        int var54 = var21 < 0L ? -1 : 1;
                     }

                     float var23 = var20 / 7.0F;
                     float var24 = var20 == 0 ? var14 : U[var20 - 1];
                     float var25 = var14 * (0.96F + 0.28F * var23 * var17) * 0.45F + var24 * 0.55F;
                     float var26 = (0.012F + 0.07F * var17) * (float)Math.sin(G * (4.2F + 5.0F * var17) - var20 * 0.85F) * var23;
                     long var27 = 109873557972656L;
                     if (109873557972656L == 0L) {
                        boolean var55 = false;
                     } else {
                        int var56 = var27 < 0L ? -1 : 1;
                     }

                     float var30 = 0.018F * (float)Math.sin(G * 1.3F + var20 * 0.4F) * (1.0F - var17) * var23;
                     var25 += var26 + var30;
                     float var31 = 22.0F - 12.0F * var23;
                     float var32 = var31 * var31 * (var25 - U[var20]) - 1.44F * var31 * a[var20];
                     a[var20] = a[var20] + var32 * var19;
                     float var33 = U[var20];
                     long var34 = 109873557972657L;
                     if (109873557972657L == 0L) {
                        boolean var57 = false;
                     } else {
                        int var58 = var34 < 0L ? -1 : 1;
                     }

                     U[var20] = var33 + a[var20] * var19;
                     if (var20 > 0) {
                        U[var20] = Math.max(U[var20], U[var20 - 1] - 0.3F);
                     }

                     U[var20] = Math.clamp(U[var20], -0.15F, 1.5F);
                  }

                  long var40 = 109873557972656L;
                  if (109873557972656L == 0L) {
                     boolean var59 = false;
                  } else {
                     int var60 = var40 < 0L ? -1 : 1;
                  }
               }

               return;
            }
         }

         for (int var38 = 0; var38 < 8; var38++) {
            long var16 = 109873557972657L;
            if (109873557972657L == 0L) {
               boolean var49 = false;
            } else {
               int var50 = var16 < 0L ? -1 : 1;
            }

            U[var38] = var14;
            a[var38] = 0.0F;
         }

         long var39 = 109873557972657L;
         if (109873557972657L == 0L) {
            boolean var51 = false;
         } else {
            int var52 = var39 < 0L ? -1 : 1;
         }

         J = true;
      }
   }

   private static void F(long var0) {
      CapeModule.ensureSourceClassInitialized();
      F[0] = 0.0F;
      f[0] = 0.0F;

      for (int var2 = 0; var2 < 8; var2++) {
         F[var2 + 1] = F[var2] + 2.0F * (float)Math.cos(U[var2]);
         long var3 = 130937222277562L;
         if (130937222277562L == 0L) {
            boolean var10000 = false;
         } else {
            int var14 = var3 < 0L ? -1 : 1;
         }

         f[var2 + 1] = f[var2] + 2.0F * (float)Math.sin(U[var2]);
      }

      long var12 = 130937222277562L;
      if (130937222277562L == 0L) {
         boolean var15 = false;
      } else {
         int var16 = var12 < 0L ? -1 : 1;
      }

      int var5 = 0;

      while (true) {
         long var6 = 130937222277562L;
         if (130937222277562L == 0L) {
            boolean var17 = false;
         } else {
            int var18 = var6 < 0L ? -1 : 1;
         }

         if (var5 > 8) {
            long var13 = 130937222277563L;
            if (130937222277563L == 0L) {
               boolean var21 = false;
            } else {
               int var22 = var13 < 0L ? -1 : 1;
            }

            return;
         }

         long var9 = 130937222277562L;
         if (130937222277562L == 0L) {
            boolean var19 = false;
         } else {
            int var20 = var9 < 0L ? -1 : 1;
         }

         float var11 = var5 == 0 ? U[0] : (var5 == 8 ? U[7] : (U[var5 - 1] + U[var5]) / 2.0F);
         E[var5] = -((float)Math.sin(var11));
         R[var5] = (float)Math.cos(var11);
         var5++;
      }
   }

   private static void K(class_4665 var0, long var1, class_4588 var3, int var4) {
      long var5 = 2607850533064L;
      CapeModule.ensureSourceClassInitialized();

      for (int var7 = 0; var7 < 8; var7++) {
         int var8 = var7 + 1;
         float var9 = 1.0F + var7 * 2.0F;
         float var10 = 1.0F + var8 * 2.0F;
         long var11 = 125950881545273L;
         if (125950881545273L == 0L) {
            boolean var10000 = false;
         } else {
            int var15 = var11 < 0L ? -1 : 1;
         }

         s(var0, var3, var4, var7, var8, 1.0F, -5.0F, 11.0F, 5.0F, 1.0F, var9, 1.0F);
         s(var0, var3, var4, var7, var8, 0.0F, 5.0F, 22.0F, -5.0F, 12.0F, var9, -1.0F);
         Z(var0, var3, var4, var7, var8, 5.0F, 1.0F, 1.0F, 0.0F, 0.0F, var9, var10, 1.0F);
         Z(var0, var3, var4, var7, var8, -5.0F, 0.0F, 12.0F, 1.0F, 11.0F, var9, var10, -1.0F);
      }

      long var14 = 125950881545272L;
      if (125950881545272L == 0L) {
         boolean var16 = false;
      } else {
         int var17 = var14 < 0L ? -1 : 1;
      }

      k(var0, var3, var4, 0, 11.0F, 1.0F, var5, 1.0F, 0.0F, -1.0F);
      k(var0, var3, var4, 8, 21.0F, 11.0F, var5, 1.0F, 0.0F, 1.0F);
   }

   private static void s(
      class_4665 var0, class_4588 var1, int var2, int var3, int var4, float var5, float var6, float var7, float var8, float var9, float var10, float var11
   ) {
      float var12 = var10 + 2.0F;
      H(var0, var1, var2, var6, var3, var5, var7, var10, 0.0F, E[var3] * var11, R[var3] * var11);
      H(var0, var1, var2, var8, var3, var5, var9, var10, 0.0F, E[var3] * var11, R[var3] * var11);
      H(var0, var1, var2, var8, var4, var5, var9, var12, 0.0F, E[var4] * var11, R[var4] * var11);
      H(var0, var1, var2, var6, var4, var5, var7, var12, 0.0F, E[var4] * var11, R[var4] * var11);
   }

   private static void Z(
      class_4665 var0,
      class_4588 var1,
      int var2,
      int var3,
      int var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12
   ) {
      H(var0, var1, var2, var5, var3, var6, var7, var10, var12, 0.0F, 0.0F);
      H(var0, var1, var2, var5, var3, var8, var9, var10, var12, 0.0F, 0.0F);
      H(var0, var1, var2, var5, var4, var8, var9, var11, var12, 0.0F, 0.0F);
      H(var0, var1, var2, var5, var4, var6, var7, var11, var12, 0.0F, 0.0F);
   }

   private static void k(class_4665 var0, class_4588 var1, int var2, int var3, float var4, float var5, long var6, float var8, float var9, float var10) {
      int var11 = Math.min(var3, 7);
      CapeModule.ensureSourceClassInitialized();
      float var12 = (float)Math.cos(U[var11]) * var10;
      float var13 = (float)Math.sin(U[var11]) * var10;
      long var14 = 2607850533064L;
      if (2607850533064L == 0L) {
         boolean var10000 = false;
      } else {
         int var24 = var14 < 0L ? -1 : 1;
      }

      float var17;
      int var18 = (var17 = var10 - 0.0F) == 0.0F ? 0 : (var17 < 0.0F ? -1 : 1);
      long var19 = 2607850533064L;
      if (2607850533064L == 0L) {
         boolean var25 = false;
      } else {
         int var26 = var19 < 0L ? -1 : 1;
      }

      if (var18 < 0) {
         long var21 = 2607850533064L;
         if (2607850533064L == 0L) {
            boolean var27 = false;
         } else {
            int var28 = var21 < 0L ? -1 : 1;
         }

         H(var0, var1, var2, -5.0F, var3, 0.0F, var4, var9, 0.0F, var12, var13);
         H(var0, var1, var2, 5.0F, var3, 0.0F, var5, var9, 0.0F, var12, var13);
         H(var0, var1, var2, 5.0F, var3, 1.0F, var5, var8, 0.0F, var12, var13);
         H(var0, var1, var2, -5.0F, var3, 1.0F, var4, var8, 0.0F, var12, var13);
      } else {
         H(var0, var1, var2, -5.0F, var3, 1.0F, var4, var8, 0.0F, var12, var13);
         H(var0, var1, var2, 5.0F, var3, 1.0F, var5, var8, 0.0F, var12, var13);
         H(var0, var1, var2, 5.0F, var3, 0.0F, var5, var9, 0.0F, var12, var13);
         H(var0, var1, var2, -5.0F, var3, 0.0F, var4, var9, 0.0F, var12, var13);
      }
   }

   private static void H(
      class_4665 var0, class_4588 var1, int var2, float var3, int var4, float var5, float var6, float var7, float var8, float var9, float var10
   ) {
      float var11 = F[var4] + var5 * E[var4];
      float var12 = f[var4] + var5 * R[var4];
      var1.method_56824(var0, var3 / 16.0F, var11 / 16.0F, var12 / 16.0F)
         .method_39415(-1)
         .method_22913(var6 / 64.0F, var7 / 32.0F)
         .method_22922(class_4608.field_21444)
         .method_60803(var2)
         .method_60831(var0, var8, var9, var10);
   }

}
