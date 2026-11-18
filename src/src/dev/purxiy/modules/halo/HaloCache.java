// Sourced By DevFFNightmare
package dev.purxiy.modules.halo;

import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_1044;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3298;

final class HaloCache {
   private static final Map<class_2960, Integer> w = new ConcurrentHashMap<>();
   private static final int a = 0;
   private static final int K = 1;
   private static final int[][] V = new int[][]{
      {8, 0, 16, 8, 15},
      {0, 8, 8, 12, 10},
      {16, 8, 24, 12, 10},
      {24, 8, 32, 16, 12},
      {8, 8, 16, 10, 8},
      {32, 0, 64, 16, 16},
      {20, 20, 28, 32, 12},
      {20, 36, 28, 48, 12},
      {44, 20, 48, 32, 6},
      {44, 36, 48, 48, 6},
      {36, 52, 40, 64, 6},
      {52, 52, 56, 64, 6},
      {4, 20, 8, 32, 5},
      {4, 36, 8, 48, 5},
      {20, 52, 24, 64, 5},
      {4, 52, 8, 64, 5}
   };
   private static final boolean[] S = new boolean[]{
      false, false, false, false, true, false, false, false, true, false, true, false, false, false, false, false
   };
   private static final ExecutorService N = ClientServices.visualExecutor("Purxiy halo skin colour");





   private HaloCache() {
   }

   static int of(class_2960 var0) {
      long var1 = 109178309095425L;
      long var3 = var1 ^ 93018839104300L;
      int var5 = ClientBuilder.accent();
      if (var5 != 0) {
         return soften(var5);
      } else {
         Integer var6 = w.get(var0);
         if (var6 != null) {
            return var6 == 1 ? 0 : var6;
         } else {
            w.put(var0, 1);
            u(var3, var0);
            return 0;
         }
      }
   }

   private static void u(long var0, class_2960 var2) {
      HaloDesign.ensureSourceClassInitialized();

      class_1044 var3;
      try {
         var3 = class_310.method_1551().method_1531().method_4619(var2);
      } catch (RuntimeException var12) {
         w.put(var2, 0);
         return;
      }

      int[] var4 = null;
      if (var3 instanceof class_1043 var5) {
         class_1011 var6 = var5.method_4525();
         long var7 = 61390862158636L;
         if (61390862158636L == 0L) {
            boolean var10000 = false;
         } else {
            int var14 = var7 < 0L ? -1 : 1;
         }

         if (var6 != null) {
            long var10 = 61390862158637L;
            if (61390862158637L == 0L) {
               boolean var15 = false;
            } else {
               int var16 = var10 < 0L ? -1 : 1;
            }

            if (var6.method_4307() == 64 && var6.method_4323() == 64) {
               var4 = var6.method_61942();
            }
         }
      }

      int[] var13 = var4;
      CompletableFuture.<Integer>supplyAsync(() -> {
         long var2x = 99854505942591L;
         HaloDesign.ensureSourceClassInitialized();
         return analyse(var13 != null ? var13 : h(var2x, var2));
      }, N).whenComplete((var1, var2x) -> {
         HaloDesign.ensureSourceClassInitialized();
         if (var2x != null) {
            ClientServices.LOGGER.debug("Halo: no colour from skin {}: {}", var2, var2x.toString());
         }

         w.put(var2, var1 == null ? 0 : var1);
      });
   }

   
   private static int[] h(long var0, class_2960 var2) {
      HaloDesign.ensureSourceClassInitialized();
      Optional var3 = class_310.method_1551().method_1478().method_14486(var2);
      if (var3.isEmpty()) {
         return null;
      } else {
         try {
            int[] var10;
            try (
               InputStream var4 = ((class_3298)var3.get()).method_14482();
               class_1011 var5 = class_1011.method_4309(var4);
            ) {
               int var6 = var5.method_4307();
               long var7 = 99854505942590L;
               if (99854505942590L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var16 = var7 < 0L ? -1 : 1;
               }

               var10 = var6 == 64 && var5.method_4323() == 64 ? var5.method_61942() : null;
            }

            return var10;
         } catch (IOException var15) {
            return null;
         }
      }
   }

   static int analyse( int[] var0) {
      long var1 = 75929532623725L;
      long var3 = var1 ^ 87027485884340L;
      long var5 = var1 ^ 124432900297250L;
      int var7 = (int)((var1 ^ 124432900297250L) >>> 32);
      int var8 = (int)((var1 ^ 124432900297250L) << 32 >>> 48);
      int var9 = (int)(var5 << 48 >>> 48);
      if (var0 != null && var0.length >= 4096) {
         byte var10 = 36;
         float[] var11 = new float[var10];
         float[] var12 = new float[var10];
         float[] var13 = new float[var10];
         float[] var14 = new float[var10];
         float[] var15 = new float[3];

         for (int var16 = 0; var16 < V.length; var16++) {
            int[] var17 = V[var16];
            float var18 = var17[4] / 10.0F;

            for (int var19 = var17[1]; var19 < var17[3]; var19++) {
               for (int var20 = var17[0]; var20 < var17[2]; var20++) {
                  int var21 = var0[var19 * 64 + var20];
                  if (var21 >>> 24 >= 128) {
                     h(var21, var15, var3);
                     float var22 = (float)Math.hypot(var15[1], var15[2]);
                     if (!(var22 < 0.035F) && !(var15[0] < 0.2F)) {
                        float var23 = (float)Math.toDegrees(Math.atan2(var15[2], var15[1]));
                        if (var23 < 0.0F) {
                           var23 += 360.0F;
                        }

                        float var24 = var18 * Math.min(1.0F, (var22 - 0.035F) / 0.07F) * (var15[0] > 0.97F ? 0.4F : 1.0F);
                        if (S[var16] && var23 > 35.0F && var23 < 85.0F && var22 < 0.13F) {
                           var24 *= 0.15F;
                        }

                        int var25 = Math.min(var10 - 1, (int)(var23 / (360.0F / var10)));
                        var11[var25] += var24;
                        var12[var25] += var15[1] * var24;
                        var13[var25] += var15[2] * var24;
                        var14[var25] += var22 * var24;
                     }
                  }
               }
            }
         }

         int var26 = -1;
         float var27 = 0.0F;
         float var28 = 0.0F;

         for (int var29 = 0; var29 < var10; var29++) {
            var28 += var11[var29];
            float var31 = var11[var29] + 0.6F * (var11[(var29 + 1) % var10] + var11[(var29 + var10 - 1) % var10]);
            if (var31 > var27) {
               var27 = var31;
               var26 = var29;
            }
         }

         if (var26 >= 0 && !(var28 < 6.0F)) {
            float var30 = 0.0F;
            float var32 = 0.0F;
            float var33 = 0.0F;
            float var34 = 0.0F;

            for (int var35 = -1; var35 <= 1; var35++) {
               int var37 = (var26 + var35 + var10) % var10;
               var30 += var12[var37];
               var32 += var13[var37];
               var33 += var14[var37];
               var34 += var11[var37];
            }

            float var36 = (float)Math.atan2(var32 / var34, var30 / var34);
            float var38 = Math.clamp(var33 / var34 * 0.85F, 0.075F, 0.15F);
            return l(0.8F, var7, var8, var38, var36, (short)var9);
         } else {
            return 0;
         }
      } else {
         return 0;
      }
   }

   static int soften(int var0) {
      long var1 = 49973850782325L;
      long var3 = var1 ^ 43273459592876L;
      long var5 = var1 ^ 27857218454330L;
      int var7 = (int)((var1 ^ 27857218454330L) >>> 32);
      int var8 = (int)((var1 ^ 27857218454330L) << 32 >>> 48);
      int var9 = (int)(var5 << 48 >>> 48);
      float[] var10 = new float[3];
      h(var0, var10, var3);
      float var11 = (float)Math.hypot(var10[1], var10[2]);
      if (var11 < 0.03F) {
         return var0 | 0xFF000000;
      } else {
         float var12 = (float)Math.atan2(var10[2], var10[1]);
         return l(Math.clamp(var10[0], 0.74F, 0.84F), var7, var8, Math.clamp(var11 * 0.8F, 0.08F, 0.16F), var12, (short)var9);
      }
   }

   private static void h(int var0, float[] var1, long var2) {
      long var4 = 78830092905306L;
      double var6 = H(var4, (var0 >>> 16 & 0xFF) / 255.0);
      double var8 = H(var4, (var0 >>> 8 & 0xFF) / 255.0);
      double var10 = H(var4, (var0 & 0xFF) / 255.0);
      double var12 = Math.cbrt(0.4122214708 * var6 + 0.5363325363 * var8 + 0.0514459929 * var10);
      double var14 = Math.cbrt(0.2119034982 * var6 + 0.6806995451 * var8 + 0.1073969566 * var10);
      double var16 = Math.cbrt(0.0883024619 * var6 + 0.2817188376 * var8 + 0.6299787005 * var10);
      var1[0] = (float)(0.2104542553 * var12 + 0.793617785 * var14 - 0.0040720468 * var16);
      var1[1] = (float)(1.9779984951 * var12 - 2.428592205 * var14 + 0.4505937099 * var16);
      var1[2] = (float)(0.0259040371 * var12 + 0.7827717662 * var14 - 0.808675766 * var16);
   }

   private static int l(float var0, int var1, int var2, float var3, float var4, short var5) {
      long var6 = 57335431297359L;
      long var8 = var6 ^ 123889523760591L;
      HaloDesign.ensureSourceClassInitialized();

      for (int var10 = 0; var10 < 24; var10++) {
         double var17 = var3 * Math.cos(var4);
         double var19 = var3 * Math.sin(var4);
         double var21 = var0 + 0.3963377774 * var17 + 0.2158037573 * var19;
         double var23 = var0 - 0.1055613458 * var17 - 0.0638541728 * var19;
         double var25 = var0 - 0.0894841775 * var17 - 1.291485548 * var19;
         var21 = var21 * var21 * var21;
         var23 = var23 * var23 * var23;
         var25 = var25 * var25 * var25;
         double var11 = 4.0767416621 * var21 - 3.3077115913 * var23 + 0.2309699292 * var25;
         double var13 = -1.2684380046 * var21 + 2.6097574011 * var23 - 0.3413193965 * var25;
         double var15 = -0.0041960863 * var21 - 0.7034186147 * var23 + 1.707614701 * var25;
         double var27;
         int var29 = (var27 = var11 - -1.0E-4) == 0.0 ? 0 : (var27 < 0.0 ? -1 : 1);
         short var30 = 13349;
         if (var29 >= 0 && var11 <= 1.0001 && var13 >= -1.0E-4 && var13 <= 1.0001) {
            double var31;
            var29 = (var31 = var15 - -1.0E-4) == 0.0 ? 0 : (var31 < 0.0 ? -1 : 1);
            var30 = 13349;
            if (var29 >= 0 && var15 <= 1.0001) {
               return 0xFF000000 | z(var11, var8) << 16 | z(var13, var8) << 8 | z(var15, var8);
            }
         }

         var30 = -1713;
         var30 = 13349;
         if (var10 == 23) {
            return 0xFF000000 | z(var11, var8) << 16 | z(var13, var8) << 8 | z(var15, var8);
         }

         var3 *= 0.9F;
      }

      return -1;
   }

   private static double H(long var0, double var2) {
      HaloDesign.ensureSourceClassInitialized();
      long var4 = 78830092905305L;
      if (78830092905305L == 0L) {
         boolean var10000 = false;
      } else {
         int var7 = var4 < 0L ? -1 : 1;
      }

      return var2 <= 0.04045 ? var2 / 12.92 : Math.pow((var2 + 0.055) / 1.055, 2.4);
   }

   private static int z(double var0, long var2) {
      HaloDesign.ensureSourceClassInitialized();
      double var4 = Math.clamp(var0, 0.0, 1.0);
      long var6 = 75351796343936L;
      if (75351796343936L == 0L) {
         boolean var10000 = false;
      } else {
         int var11 = var6 < 0L ? -1 : 1;
      }

      double var9 = var4 <= 0.0031308 ? var4 * 12.92 : 1.055 * Math.pow(var4, 0.4166666666666667) - 0.055;
      return (int)Math.round(Math.clamp(var9, 0.0, 1.0) * 255.0);
   }

   static void release() {
      N.shutdown();

      try {
         if (!N.awaitTermination(5L, TimeUnit.SECONDS)) {
            N.shutdownNow();
            throw new IllegalStateException("Halo skin worker did not stop");
         }
      } catch (InterruptedException var1) {
         N.shutdownNow();
         Thread.currentThread().interrupt();
         throw new IllegalStateException("Halo skin worker interrupted");
      }

      w.clear();
   }

}
