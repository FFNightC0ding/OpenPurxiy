package dev.purxiy.modules.home;

import java.nio.ByteBuffer;
import org.lwjgl.system.MemoryUtil;

final class HomeCurve {
   static final int R = 40;
   static final int k = 255;
   static final int W = 254;
   static final int f = 253;
   static final int S = 252;
   static final int D = 251;
   static final int h = 250;
   static final int n = 249;
   static final int z = 248;
   static final int u = 247;
   static final int s = 0;
   static final int c = 1;
   static final int G = 2;
   static final int q = 3;
   static final int V = 4;
   static final int K = 5;
   private static final int[][] B = new int[][]{{0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}, {-1, 0, 0}, {1, 0, 0}};
   private static final int[][][] P = new int[][][]{
      {{0, 0, 1}, {0, 0, 0}, {1, 0, 0}, {1, 0, 1}},
      {{0, 1, 0}, {0, 1, 1}, {1, 1, 1}, {1, 1, 0}},
      {{1, 1, 0}, {1, 0, 0}, {0, 0, 0}, {0, 1, 0}},
      {{0, 1, 1}, {0, 0, 1}, {1, 0, 1}, {1, 1, 1}},
      {{0, 1, 0}, {0, 0, 0}, {0, 0, 1}, {0, 1, 1}},
      {{1, 1, 1}, {1, 0, 1}, {1, 0, 0}, {1, 1, 0}}
   };
   private static final float[][] t = new float[][]{{0.0F, 0.0F}, {0.0F, 1.0F}, {1.0F, 1.0F}, {1.0F, 0.0F}};
   static final float[] E = new float[]{0.5F, 1.0F, 0.8F, 0.8F, 0.6F, 0.6F};
   private static final float[] p = new float[]{0.56F, 0.71F, 0.85F, 1.0F};
   private ByteBuffer m;
   private int F;

   HomeCurve(int var1) {
      this.m = MemoryUtil.memAlloc(Math.max(64, var1) * 4 * 40);
   }

   void reset() {
      this.F = 0;
   }

   int vertices() {
      return this.F;
   }

   int quads() {
      return this.F / 4;
   }

   ByteBuffer data() {
      return this.m.slice(0, this.F * 40);
   }

   void free() {
      if (this.m != null) {
         MemoryUtil.memFree(this.m);
         this.m = null;
      }
   }

   private void z(int var1, int var2, char var3, char var4) {
      HomeGuiBridge.ensureSourceClassInitialized();
      int var5 = (this.F + var1) * 40;
      if (var5 > this.m.capacity()) {
         int var6 = this.m.capacity();

         while (var6 < var5) {
            var6 *= 2;
         }

         this.m = MemoryUtil.memRealloc(this.m, var6);
      }
   }

   void vertex(float var1, float var2, float var3, float var4, float var5, int var6, int var7, float var8, float var9, float var10, float var11) {
      long var12 = 39341734729626L;
      long var14 = var12 ^ 47005440932145L;
      int var16 = (int)((var12 ^ 47005440932145L) >>> 32);
      int var17 = (int)((var12 ^ 47005440932145L) << 32 >>> 48);
      int var18 = (int)(var14 << 48 >>> 48);
      this.z(1, var16, (char)var17, (char)var18);
      int var19 = this.F * 40;
      ByteBuffer var20 = this.m;
      var20.putFloat(var19, var1).putFloat(var19 + 4, var2).putFloat(var19 + 8, var3);
      var20.putFloat(var19 + 12, var4).putFloat(var19 + 16, var5);
      var20.put(var19 + 20, (byte)(var6 >> 16)).put(var19 + 21, (byte)(var6 >> 8)).put(var19 + 22, (byte)var6).put(var19 + 23, (byte)var7);
      var20.putFloat(var19 + 24, var8).putFloat(var19 + 28, var9).putFloat(var19 + 32, var10).putFloat(var19 + 36, var11);
      this.F++;
   }

   void face(
      int var1,
      int var2,
      int var3,
      int var4,
      float[] var5,
      int var6,
      boolean var7,
      HomeCurve.Listener var8,
      int var9,
      float var10,
      float var11,
      float var12,
      float var13
   ) {
      long var14 = 50870547552885L;
      long var16 = var14 ^ 43170871047390L;
      int var18 = (int)((var14 ^ 43170871047390L) >>> 32);
      int var19 = (int)((var14 ^ 43170871047390L) << 32 >>> 48);
      int var20 = (int)(var16 << 48 >>> 48);
      int[] var21 = B[var4];
      int var22 = var1 + var21[0];
      int var23 = var2 + var21[1];
      int var24 = var3 + var21[2];
      float[] var25 = new float[4];
      int[] var26 = new int[4];

      for (int var27 = 0; var27 < 4; var27++) {
         int[] var28 = P[var4][var27];
         int var29;
         byte var30;
         int var31;
         byte var32;
         int var33;
         int var34;
         if (var21[1] != 0) {
            var29 = var28[0] * 2 - 1;
            var30 = 0;
            var31 = 0;
            var32 = 0;
            var33 = 0;
            var34 = var28[2] * 2 - 1;
         } else if (var21[2] != 0) {
            var29 = var28[0] * 2 - 1;
            var30 = 0;
            var31 = 0;
            var32 = 0;
            var33 = var28[1] * 2 - 1;
            var34 = 0;
         } else {
            var29 = 0;
            var30 = 0;
            var31 = var28[2] * 2 - 1;
            var32 = 0;
            var33 = var28[1] * 2 - 1;
            var34 = 0;
         }

         boolean var35 = var8.at(var22 + var29, var23 + var30, var24 + var31);
         boolean var36 = var8.at(var22 + var32, var23 + var33, var24 + var34);
         boolean var37 = var8.at(var22 + var29 + var32, var23 + var30 + var33, var24 + var31 + var34);
         int var38 = var35 && var36 ? 0 : 3 - ((var35 ? 1 : 0) + (var36 ? 1 : 0) + (var37 ? 1 : 0));
         var26[var27] = var38;
         var25[var27] = p[var38] * E[var4];
      }

      int var39 = var26[0] + var26[2] < var26[1] + var26[3] ? 1 : 0;
      this.z(4, var18, (char)var19, (char)var20);

      for (int var40 = 0; var40 < 4; var40++) {
         int var41 = var39 + var40 & 3;
         int[] var42 = P[var4][var41];
         float[] var43 = t[var41];
         float var44 = var43[0];
         float var45 = var43[1];
         if (var7) {
            float var46 = var44;
            var44 = var45;
            var45 = 1.0F - var46;
         }

         this.vertex(
            var1 + var42[0],
            var2 + var42[1],
            var3 + var42[2],
            lerp(var5[0], var5[2], var44),
            lerp(var5[1], var5[3], var45),
            scale(var6, var25[var41]),
            var9,
            var10,
            var11,
            var12,
            var13
         );
      }
   }

   void cross(float var1, float var2, float var3, float var4, float[] var5, int var6, int var7, float var8) {
      this.cross(var1, var2, var3, var4, var5, var6, var7, var8, var2);
   }

   void cross(float var1, float var2, float var3, float var4, float[] var5, int var6, int var7, float var8, float var9) {
      long var10 = 13266045831192L;
      long var12 = var10 ^ 5596434097843L;
      int var14 = (int)((var10 ^ 5596434097843L) >>> 32);
      int var15 = (int)((var10 ^ 5596434097843L) << 32 >>> 48);
      int var16 = (int)(var12 << 48 >>> 48);
      float var17 = 0.06F;
      float var18 = 0.94F;
      float var19 = var1 + 0.5F;
      float var20 = var3 + 0.5F;
      this.z(8, var14, (char)var15, (char)var16);
      this.j(var1 + var17, var2, var3 + var17, var1 + var18, var2 + var4, var3 + var18, var5, var6, var7, var19, var9, var20, var8);
      this.j(var1 + var17, var2, var3 + var18, var1 + var18, var2 + var4, var3 + var17, var5, var6, var7, var19, var9, var20, var8);
   }

   private void j(
      float var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float[] var7,
      int var8,
      int var9,
      float var10,
      float var11,
      float var12,
      float var13
   ) {
      this.vertex(var1, var5, var3, var7[0], var7[1], var8, var9, var10, var11, var12, var13);
      this.vertex(var1, var2, var3, var7[0], var7[3], var8, var9, var10, var11, var12, var13);
      this.vertex(var4, var2, var6, var7[2], var7[3], var8, var9, var10, var11, var12, var13);
      this.vertex(var4, var5, var6, var7[2], var7[1], var8, var9, var10, var11, var12, var13);
   }

   void flat(float var1, float var2, float var3, float[] var4, int var5, int var6, int var7, float var8) {
      long var9 = 57245912483385L;
      long var11 = var9 ^ 67169576757394L;
      int var13 = (int)((var9 ^ 67169576757394L) >>> 32);
      int var14 = (int)((var9 ^ 67169576757394L) << 32 >>> 48);
      int var15 = (int)(var11 << 48 >>> 48);
      float[][] var16 = new float[][]{{0.0F, 0.0F}, {0.0F, 1.0F}, {1.0F, 1.0F}, {1.0F, 0.0F}};
      float[][] var17 = new float[][]{{var4[0], var4[1]}, {var4[0], var4[3]}, {var4[2], var4[3]}, {var4[2], var4[1]}};
      this.z(4, var13, (char)var14, (char)var15);

      for (int var18 = 0; var18 < 4; var18++) {
         float[] var19 = var16[var18];
         float[] var20 = var17[var18 + var6 & 3];
         this.vertex(var1 + var19[0], var2, var3 + var19[1], var20[0], var20[1], var5, var7, var1 + 0.5F, var2, var3 + 0.5F, var8);
      }
   }

   void box(float var1, float var2, float var3, float var4, float var5, float var6, int var7, int var8) {
      long var9 = 82221322186872L;
      long var11 = var9 ^ 74492386205395L;
      int var13 = (int)((var9 ^ 74492386205395L) >>> 32);
      int var14 = (int)((var9 ^ 74492386205395L) << 32 >>> 48);
      int var15 = (int)(var11 << 48 >>> 48);
      float[][] var16 = new float[][]{{var1, var2, var3}, {var4, var5, var6}};

      for (int var17 = 0; var17 < 6; var17++) {
         if ((var7 & 1 << var17) != 0) {
            int var18 = scale(16777215, E[var17] * 0.3F + 0.7F);
            this.z(4, var13, (char)var14, (char)var15);

            for (int var19 = 0; var19 < 4; var19++) {
               int[] var20 = P[var17][var19];
               this.vertex(var16[var20[0]][0], var16[var20[1]][1], var16[var20[2]][2], 0.0F, 0.0F, var18, var8, 0.0F, 0.0F, 0.0F, 0.0F);
            }
         }
      }
   }

   static float lerp(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   static int scale(int var0, float var1) {
      int var2 = Math.min(255, Math.round((var0 >> 16 & 0xFF) * var1));
      int var3 = Math.min(255, Math.round((var0 >> 8 & 0xFF) * var1));
      int var4 = Math.min(255, Math.round((var0 & 0xFF) * var1));
      return var2 << 16 | var3 << 8 | var4;
   }

   interface Listener {
      boolean at(int var1, int var2, int var3);
   }
}
