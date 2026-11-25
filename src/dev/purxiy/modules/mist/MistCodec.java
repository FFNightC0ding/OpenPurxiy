package dev.purxiy.modules.mist;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Comparator;
import net.minecraft.class_1944;
import net.minecraft.class_2804;
import net.minecraft.class_3562;
import net.minecraft.class_4076;
import net.minecraft.class_638;

final class MistCodec {
   static final int n = 64;
   static final int h = 32;
   static final int T = 66;
   static final int v = 8;
   static final int u = 4;
   static final int t = 528;
   static final int L = 264;
   private static final int x = 8;
   private static final int y = 4;
   private static final int q = 24;
   private static final int K = 48;
   private static final float w = 720.0F;
   final byte[] j = new byte[139392];
   private final long[] C = new long[256];
   private final long[] r = new long[256];
   private static final long s = Long.MIN_VALUE;
   private int b;
   private int O;
   private int d;
   private int G;
   private boolean Z = true;
   private int m;
   private float p;
   private Object V;
   private static final int[] U = N(9269, 3600835060L);
   private static final long[] e = new long[]{-6981155841504757042L, -2842256956112036244L, -1622795388387479008L, -8853701888389642018L, 970432757423253440L};
   private static final Long[] f = new Long[5];

   MistCodec() {
      Arrays.fill(this.C, Long.MIN_VALUE);
   }

   void forget() {
      this.V = null;
      Arrays.fill(this.C, Long.MIN_VALUE);
      Arrays.fill(this.j, (byte)0);
      this.G = 0;
      this.Z = true;
   }

   boolean takeChanged() {
      boolean var1 = this.Z;
      this.Z = false;
      return var1;
   }

   boolean any() {
      return this.G > 0;
   }

   int cellX() {
      return this.b * 8;
   }

   int cellY() {
      return this.O * 8;
   }

   int cellZ() {
      return this.d * 8;
   }

   void update(class_638 var1, double var2, double var4, double var6, float var8) {
      this.update(var1, var1.method_22336().method_15562(class_1944.field_9282), var2, var4, var6, var8);
   }

   void update(Object var1, class_3562 var2, double var3, double var5, double var7) {
      this.update(var1, var2, var3, var5, var7, 0.016666668F);
   }

   void update(Object var1, class_3562 var2, double var3, double var5, double var7, float var9) {
      long var10 = 12519861868494L;
      long var12 = var10 ^ 102401874751968L;
      if (var1 != this.V) {
         this.forget();
         this.V = var1;
      }

      this.b = origin(var3, 8);
      this.O = origin(var5, 4);
      this.d = origin(var7, 8);
      int var14 = 0;

      for (int var15 = 0; var15 < U.length && var14 < 24; var15++) {
         int var16 = this.b + (U[var15] & 0xFF);
         int var17 = this.O + (U[var15] >> 8 & 0xFF);
         int var18 = this.d + (U[var15] >> 16);
         int var19 = slot(var16, var17, var18);
         long var20 = class_4076.method_18685(var16, var17, var18);
         if (this.C[var19] != var20) {
            this.I(var12, var2, var16, var17, var18, var19, var20, true);
            var14++;
         }
      }

      for (this.p = Math.min(this.p + var9 * 720.0F, 48.0F); this.p >= 1.0F; this.p--) {
         this.m = (this.m + 1) % U.length;
         int var29 = this.b + (U[this.m] & 0xFF);
         int var30 = this.O + (U[this.m] >> 8 & 0xFF);
         int var31 = this.d + (U[this.m] >> 16);
         int var32 = slot(var29, var30, var31);
         long var33 = class_4076.method_18685(var29, var30, var31);
         boolean var21 = this.C[var32] != var33;
         this.I(var12, var2, var29, var30, var31, var32, var33, var21);
      }
   }

   private void I(long var1, class_3562 var3, int var4, int var5, int var6, int var7, long var8, boolean var10) {
      long var11 = 32819454744699L;
      byte var13 = 0;
      int var14 = 500785137;
      int var15 = (int)(var11 << 48 >>> 48);
      long var16 = 140508677073929L;
      class_2804 var18 = var3.method_15544(class_4076.method_18676(var4, var5, var6));
      MistRenderPass.ensureSourceClassInitialized();
      int var19 = -1;
      byte[] var20 = null;
      long var21;
      if (var18 == null) {
         var21 = 0L;
         var19 = 0;
      } else {
         int var23 = var18.method_51380() ? 1 : 0;
         long var24 = 94838426248750L;
         int var27 = var24 < 0L ? -1 : 1;
         if (var23 != 0) {
            var23 = var18.method_12139(0, 0, 0);
            long var27x = 94838426248749L;
            int var29 = var27x < 0L ? -1 : 1;
            var19 = var23;
            var21 = var23 == 0 ? 0L : -7046029254386353131L * (var23 + 1);
         } else {
            var20 = var18.method_12137();
            var21 = o(var20, var16);
         }
      }

      long var63 = this.C[var7];
      long var25 = 94838426248749L;
      int var28 = var25 < 0L ? -1 : 1;
      boolean var28x = var63 != Long.MIN_VALUE && this.r[var7] != 0L;
      long var29 = 94838426248750L;
      int var64 = var29 < 0L ? -1 : 1;
      if (!var10 && this.C[var7] == var8) {
         long var32 = 94838426248749L;
         int var32x = var32 < 0L ? -1 : 1;
         if (this.r[var7] == var21) {
            return;
         }
      }

      this.C[var7] = var8;
      this.r[var7] = var21;
      boolean var64x = var21 != 0L;
      this.G += (var64x ? 1 : 0) - (var28x ? 1 : 0);
      if (var64x || var28x) {
         for (int var33 = 0; var33 < 8; var33++) {
            long var34 = 94838426248750L;
            int var37 = var34 < 0L ? -1 : 1;
            var37 = Math.floorMod(var5 * 8 + var33, 32);
            int var38 = var37 % 8 * 66;
            int var39 = var37 / 8 * 66;
            int var40 = 0;

            while (var40 < 8) {
               int var41 = var6 * 8 + var40;
               long var42 = 94838426248749L;
               int var45 = var42 < 0L ? -1 : 1;
               var45 = Math.floorMod(var41, 64);

               for (int var46 = 0; var46 < 8; var46++) {
                  int var47 = Math.floorMod(var4 * 8 + var46, 64);
                  long var48 = 94838426248749L;
                  int var51 = var48 < 0L ? -1 : 1;
                  if (var20 == null) {
                     var51 = var19 * 8;
                     long var68 = 94838426248750L;
                     int var54 = var68 < 0L ? -1 : 1;
                  } else {
                     var51 = 0;

                     for (int var52 = 0; var52 < 2; var52++) {
                        long var53 = 94838426248749L;
                        int var70 = var53 < 0L ? -1 : 1;

                        for (int var56 = 0; var56 < 2; var56++) {
                           long var57 = 94838426248750L;
                           int var60 = var57 < 0L ? -1 : 1;
                           var60 = var33 * 2 + var52 << 8 | var40 * 2 + var56 << 4 | var46 * 2;
                           int var61 = var20[var60 >> 1] & 255;
                           var51 += (var61 & 15) + (var61 >> 4);
                        }

                        long var70x = 94838426248749L;
                        int var52x = var70x < 0L ? -1 : 1;
                     }
                  }

                  long var69 = 94838426248750L;
                  int var72 = var69 < 0L ? -1 : 1;
                  byte var54 = (byte)Math.min(255, Math.round(var51 * 2.125F));
                  this.q(var38, var39, var13, var47, var45, var14, var15, var54);
               }

               var40++;
               long var67 = 94838426248750L;
               int var68 = var67 < 0L ? -1 : 1;
            }

            long var66 = 94838426248749L;
            int var40x = var66 < 0L ? -1 : 1;
         }

         long var65 = 94838426248749L;
         int var33 = var65 < 0L ? -1 : 1;
         this.Z = true;
      }
   }

   private void q(int var1, int var2, short var3, int var4, int var5, int var6, int var7, byte var8) {
      MistRenderPass.ensureSourceClassInitialized();
      this.j[(var2 + 1 + var5) * 528 + var1 + 1 + var4] = var8;
      if (var4 == 0) {
         this.j[(var2 + 1 + var5) * 528 + var1 + 1 + 64] = var8;
      }

      if (var5 == 0) {
         int var9 = var2 + 1 + 64;
         this.j[var9 * 528 + var1 + 1 + var4] = var8;
         if (var4 == 0) {
            this.j[(var2 + 1 + 64) * 528 + var1 + 1 + 64] = var8;
         }
      }
   }

   void encode(ByteBuffer var1) {
      var1.put(this.j);
   }

   static int origin(double var0, int var2) {
      int var3 = (int)Math.floor(var0);
      int var4 = class_4076.method_18675(var3);
      return var4 - var2 / 2 + ((var3 & 15) >= 8 ? 1 : 0);
   }

   static int slot(int var0, int var1, int var2) {
      return Math.floorMod(var0, 8) + Math.floorMod(var2, 8) * 8 + Math.floorMod(var1, 4) * 8 * 8;
   }

   private static long o(byte[] var0, long var1) {
      MistRenderPass.ensureSourceClassInitialized();
      long var3 = -3750763034362895579L;

      for (byte var5 = 0; var5 < var0.length; var5 = (byte)(var5 + 8)) {
         long var6 = 0L;

         for (int var8 = 0; var8 < 8; var8++) {
            long var9 = 140508677073928L;
            int var11 = var9 < 0L ? -1 : 1;
            var6 = var6 << 8 | var0[var5 + var8] & 255;
         }

         var3 = (var3 ^ var6) * 1099511628211L;
         long var13 = 140508677073929L;
         int var10 = var13 < 0L ? -1 : 1;
      }

      long var12 = 140508677073928L;
      int var7 = var12 < 0L ? -1 : 1;
      return var3 == 0L ? 1L : var3;
   }

   private static int[] N(int var0, long var1) {
      Integer[] var3 = new Integer[256];
      int var4 = 0;
      int var5 = 0;

      while (var5 < 4) {
         short var6 = 9269;
         int var7 = 0;

         while (var7 < 8) {
            long var8 = 3600835059L;
            int var10 = var8 < 0L ? -1 : 1;
            var10 = 0;

            while (var10 < 8) {
               var3[var4++] = var10 | var5 << 8 | var7 << 16;
               short var11 = 9269;
               var10++;
               long var12 = 3600835059L;
               int var141 = var12 < 0L ? -1 : 1;
            }

            var7++;
            long var18 = 3600835059L;
            int var13 = var18 < 0L ? -1 : 1;
         }

         var5++;
         long var16 = 3600835059L;
         int var20 = var16 < 0L ? -1 : 1;
      }

      Arrays.sort(var3, Comparator.comparingDouble(var0x -> {
         double var1x = (var0x & 0xFF) - 3.5;
         double var3x = (var0x >> 8 & 0xFF) - 1.5;
         double var5x = (var0x >> 16) - 3.5;
         return var1x * var1x + var3x * var3x * 4.0 + var5x * var5x;
      }));
      int[] var14 = new int[var3.length];

      for (int var15 = 0; var15 < var3.length; var15++) {
         long var17 = 3600835060L;
         int var21 = var17 < 0L ? -1 : 1;
         var14[var15] = var3[var15];
      }

      return var14;
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 15692) & 32767;
      if (f[var3] == null) {
         f[var3] = e[var3] ^ var1;
      }

      return f[var3];
   }
}
