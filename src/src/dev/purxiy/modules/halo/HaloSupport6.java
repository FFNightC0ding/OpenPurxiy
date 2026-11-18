// Sourced By DevFFNightmare
package dev.purxiy.modules.halo;

import dev.purxiy.loader.fabric.ClientServices;
import net.minecraft.class_11659;
import net.minecraft.class_12249;
import net.minecraft.class_1921;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_4608;

final class HaloSupport6 {
   private static final class_1921 R = class_12249.method_76014(ClientServices.id("purxiy:textures/halo/glow.png"));
   private static final class_4587 L = new class_4587();
   private static final float[] r = new float[256];
   private static final float[] x = new float[256];

   private static final Integer[] b = new Integer[3];

   private HaloSupport6() {
   }

   static void submit(HaloSupport7 var0, class_11659 var1, float var2) {
      if (var0.j != 0 && !(var2 <= 0.003F)) {
         var1.method_73483(L, R, (var2x, var3) -> {
            long var4 = 55657331494409L;
            long var6 = 98696005850373L;
            HaloDesign.ensureSourceClassInitialized();

            for (int var8 = 0; var8 < 2; var8++) {
               for (int var9 = 0; var9 < var0.j; var9++) {
                  long var10 = 46983236167830L;
                  if (46983236167830L == 0L) {
                     boolean var10000 = false;
                  } else {
                     int var31 = var10 < 0L ? -1 : 1;
                  }

                  long var13 = 46983236167831L;
                  if (46983236167831L == 0L) {
                     boolean var32 = false;
                  } else {
                     int var33 = var13 < 0L ? -1 : 1;
                  }

                  if (var0.R[var9] == 1) {
                     int var12 = var0.N[var9];
                     long var16 = 46983236167830L;
                     if (46983236167830L == 0L) {
                        boolean var34 = false;
                     } else {
                        int var35 = var16 < 0L ? -1 : 1;
                     }

                     boolean var19 = var8 == 1;
                     E(var4, var0, var12, var19, var2, var3);
                     long var25 = 46983236167831L;
                     if (46983236167831L == 0L) {
                        boolean var36 = false;
                     } else {
                        int var37 = var25 < 0L ? -1 : 1;
                     }
                  } else {
                     long var29 = 46983236167831L;
                     if (46983236167831L == 0L) {
                        boolean var38 = false;
                     } else {
                        int var39 = var29 < 0L ? -1 : 1;
                     }

                     boolean var30 = var8 == 1;
                     Z(var0, var9, var6, var30, var2, var3);
                  }
               }

               long var28 = 46983236167830L;
               if (46983236167830L == 0L) {
                  boolean var40 = false;
               } else {
                  int var41 = var28 < 0L ? -1 : 1;
               }
            }
         });
      }
   }

   private static void Z(HaloSupport7 var0, int var1, long var2, boolean var4, float var5, class_4588 var6) {
      long var7 = 15263027516141L;
      HaloDesign.ensureSourceClassInitialized();
      int var9 = var0.N[var1];
      int var10 = var0.A[var1];
      boolean var11 = var0.w[var1];

      for (int var12 = 0; var12 < var10; var12++) {
         long var13 = 98696005850373L;
         if (98696005850373L == 0L) {
            boolean var10000 = false;
         } else {
            int var38 = var13 < 0L ? -1 : 1;
         }

         long var16 = 98696005850373L;
         if (98696005850373L == 0L) {
            boolean var39 = false;
         } else {
            int var40 = var16 < 0L ? -1 : 1;
         }

         int var19 = var12 > 0 ? var12 - 1 : (var11 ? var10 - 1 : 0);
         long var20 = 98696005850373L;
         if (98696005850373L == 0L) {
            boolean var41 = false;
         } else {
            int var42 = var20 < 0L ? -1 : 1;
         }

         long var22 = 98696005850373L;
         if (98696005850373L == 0L) {
            boolean var43 = false;
         } else {
            int var44 = var22 < 0L ? -1 : 1;
         }

         int var24;
         if (var12 < var10 - 1) {
            var24 = var12 + 1;
         } else {
            long var25 = 98696005850372L;
            if (98696005850372L == 0L) {
               boolean var45 = false;
            } else {
               int var46 = var25 < 0L ? -1 : 1;
            }

            var24 = var11 ? 0 : var10 - 1;
         }

         float var26 = var0.g[var9 + var24] - var0.g[var9 + var19];
         float var27 = var0.e[var9 + var24] - var0.e[var9 + var19];
         float var28 = (float)Math.sqrt(var26 * var26 + var27 * var27);
         long var29 = 98696005850372L;
         if (98696005850372L == 0L) {
            boolean var47 = false;
         } else {
            int var48 = var29 < 0L ? -1 : 1;
         }

         r[var12] = var28 > 1.0E-6F ? -var27 / var28 : 0.0F;
         long var32 = 98696005850372L;
         if (98696005850372L == 0L) {
            boolean var49 = false;
         } else {
            int var50 = var32 < 0L ? -1 : 1;
         }

         x[var12] = var28 > 1.0E-6F ? var26 / var28 : 1.0F;
      }

      long var35 = 98696005850373L;
      if (98696005850373L == 0L) {
         boolean var51 = false;
      } else {
         int var52 = var35 < 0L ? -1 : 1;
      }

      long var36 = 98696005850373L;
      if (98696005850373L == 0L) {
         boolean var53 = false;
      } else {
         int var54 = var36 < 0L ? -1 : 1;
      }

      int var34 = var11 ? var10 : var10 - 1;

      for (int var37 = 0; var37 < var34; var37++) {
         int var21 = (var37 + 1) % var10;
         L(var0, var9 + var37, var9 + var21, var7, r[var37], x[var37], r[var21], x[var21], var4, var5, var6);
      }
   }

   private static void L(
      HaloSupport7 var0, int var1, int var2, long var3, float var5, float var6, float var7, float var8, boolean var9, float var10, class_4588 var11
   ) {
      long var12 = 31631318720864L;
      long var14 = 60676424141432L;
      long var16 = 138201419078976L;
      float var18 = D(var0, var1, var12, var9);
      float var19 = D(var0, var2, var12, var9);
      int var20 = L(var0, var1, var9, var10, var14);
      int var21 = L(var0, var2, var9, var10, var14);
      float var22 = var0.g[var1] - var5 * var18;
      float var23 = var0.e[var1] - var6 * var18;
      float var24 = var0.g[var1] + var5 * var18;
      float var25 = var0.e[var1] + var6 * var18;
      float var26 = var0.g[var2] - var7 * var19;
      float var27 = var0.e[var2] - var8 * var19;
      float var28 = var0.g[var2] + var7 * var19;
      float var29 = var0.e[var2] + var8 * var19;
      S(var11, var22, var23, var0.s[var1], var20, var16, 0.0F, 0.5F);
      S(var11, var24, var25, var0.s[var1], var20, var16, 1.0F, 0.5F);
      S(var11, var28, var29, var0.s[var2], var21, var16, 1.0F, 0.5F);
      S(var11, var26, var27, var0.s[var2], var21, var16, 0.0F, 0.5F);
      S(var11, var26, var27, var0.s[var2], var21, var16, 0.0F, 0.5F);
      S(var11, var28, var29, var0.s[var2], var21, var16, 1.0F, 0.5F);
      S(var11, var24, var25, var0.s[var1], var20, var16, 1.0F, 0.5F);
      S(var11, var22, var23, var0.s[var1], var20, var16, 0.0F, 0.5F);
   }

   private static void E(long var0, HaloSupport7 var2, int var3, boolean var4, float var5, class_4588 var6) {
      long var7 = 31631318720864L;
      long var9 = 60676424141432L;
      long var11 = 138201419078976L;
      float var13 = D(var2, var3, var7, var4) * (var4 ? 1.0F : 0.8F);
      int var14 = L(var2, var3, var4, var5, var9);
      float var15 = var2.g[var3];
      float var16 = var2.e[var3];
      float var17 = var2.s[var3];
      S(var6, var15 - var13, var16 - var13, var17, var14, var11, 0.0F, 0.0F);
      S(var6, var15 + var13, var16 - var13, var17, var14, var11, 1.0F, 0.0F);
      S(var6, var15 + var13, var16 + var13, var17, var14, var11, 1.0F, 1.0F);
      S(var6, var15 - var13, var16 + var13, var17, var14, var11, 0.0F, 1.0F);
      S(var6, var15 - var13, var16 + var13, var17, var14, var11, 0.0F, 1.0F);
      S(var6, var15 + var13, var16 + var13, var17, var14, var11, 1.0F, 1.0F);
      S(var6, var15 + var13, var16 - var13, var17, var14, var11, 1.0F, 0.0F);
      S(var6, var15 - var13, var16 - var13, var17, var14, var11, 0.0F, 0.0F);
   }

   private static float D(HaloSupport7 var0, int var1, long var2, boolean var4) {
      return var4 ? var0.k[var1] * 2.6F : var0.k[var1] + var0.U[var1] * 0.9F;
   }

   private static int L(HaloSupport7 var0, int var1, boolean var2, float var3, long var4) {
      long var6 = 24913398015170L;
      HaloDesign.ensureSourceClassInitialized();
      if (var2) {
         int var8 = HaloBuilder.TypeE.lerp(var0.h[var1], -1, 0.55F * var0.P[var1]);
         return V(var8, var6, Math.min(1.0F, var0.S[var1]) * var3);
      } else {
         return V(var0.b[var1], var6, Math.min(1.0F, var0.D[var1] * 1.1F) * var3);
      }
   }

   private static int V(int var0, long var1, float var3) {
      return Math.round(Math.clamp(var3, 0.0F, 1.0F) * 255.0F) << 24 | var0 & 16777215;
   }

   private static void S(class_4588 var0, float var1, float var2, float var3, int var4, long var5, float var7, float var8) {
      var0.method_23919(var1, var2, var3, var4, var7, var8, class_4608.field_21444, 15728880, 0.0F, 0.0F, 1.0F);
   }

}
