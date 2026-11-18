// Sourced By DevFFNightmare
package dev.purxiy.modules.halo;

import java.nio.charset.StandardCharsets;

final class HaloSupport7 {
   static final int T;
   static final int t;
   static final int W = 0;
   static final int q = 1;
   final float[] g = new float[256];
   final float[] e = new float[256];
   final float[] s = new float[256];
   final float[] k = new float[256];
   final float[] U = new float[256];
   final float[] S = new float[256];
   final float[] D = new float[256];
   final float[] o = new float[256];
   final float[] P = new float[256];
   final int[] h = new int[256];
   final int[] b = new int[256];
   int V;
   final int[] N = new int[48];
   final int[] A = new int[48];
   final int[] R = new int[48];
   final boolean[] w = new boolean[48];
   final float[] y = new float[48];
   int j;
   private int X = -1;

   void clear() {
      this.V = 0;
      this.j = 0;
      this.X = -1;
   }

   boolean begin(int var1, boolean var2, float var3) {
      if (this.j < 48 && this.V < 256) {
         this.X = this.j;
         this.N[this.X] = this.V;
         this.A[this.X] = 0;
         this.R[this.X] = var1;
         this.w[this.X] = var2;
         this.y[this.X] = var3;
         return true;
      } else {
         this.X = -1;
         return false;
      }
   }

   void end() {
      int var1 = this.X;
      this.X = -1;
      if (var1 >= 0) {
         int var2 = this.A[var1];
         if (var2 == 0 || this.R[var1] == 0 && var2 < 2) {
            this.V = this.N[var1];
         } else {
            this.j = var1 + 1;
         }
      }
   }

   void point(float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9, int var10, int var11) {
      if (this.X >= 0 && this.V < 256) {
         int var12 = this.V++;
         this.g[var12] = var1;
         this.e[var12] = var2;
         this.s[var12] = var3;
         this.k[var12] = var4;
         this.U[var12] = var5;
         this.S[var12] = var6;
         this.D[var12] = var7;
         this.o[var12] = var8;
         this.P[var12] = var9;
         this.h[var12] = var10;
         this.b[var12] = var11;
         this.A[this.X]++;
      }
   }

   static {
      long var0 = 37434447618968L;
      long[] var2 = new long[2];
      int var3 = 0;
      String var4 = ")@T¿âóâ\u0098Âÿ[Aâóã¨";
      int var5 = ")@T¿âóâ\u0098Âÿ[Aâóã¨".length();
      byte var6 = 0;

      do {
         int var7 = var6;
         var6 = (byte)(var6 + 8);
         byte[] var8 = var4.substring(var7, var6).getBytes(StandardCharsets.ISO_8859_1);
         var7 = var3++;
         long var9 = (
               (var8[0] & 255L) << 56
                  | (var8[1] & 255L) << 48
                  | (var8[2] & 255L) << 40
                  | (var8[3] & 255L) << 32
                  | (var8[4] & 255L) << 24
                  | (var8[5] & 255L) << 16
                  | (var8[6] & 255L) << 8
                  | var8[7] & 255L
            )
            ^ var0;
         var2[var7] = var9;
      } while (var6 < var5);

      T = (int)var2[0];
      t = (int)var2[1];
   }
}
