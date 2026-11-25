package dev.purxiy.modules.halo;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.joml.Matrix4f;
import org.joml.Vector4f;

final class HaloSupport2 {
   static final int h = 64;
   static final int a = 288;
   static final int e = 15984;
   private final float[] H = new float[256];
   private final float[] A = new float[256];
   private final float[] K = new float[256];
   private final float[] l = new float[256];
   private final float[] G = new float[256];
   private final float[] B = new float[288];
   private final float[] O = new float[288];
   private final float[] X = new float[288];
   private final float[] y = new float[288];
   private final float[] k = new float[288];
   private final float[] v = new float[288];
   private final float[] E = new float[288];
   private final float[] i = new float[288];
   private final float[] V = new float[288];
   private final int[] R = new int[288];
   private final int[] q = new int[288];
   private int f;
   private final int[] T = new int[64];
   private final int[] s = new int[64];
   private final int[] C = new int[64];
   private final int[] n = new int[64];
   private final float[] m = new float[64];
   private final float[] z = new float[64];
   private final float[] P = new float[256];
   private int x;
   private float I;
   private float t;
   private float j;
   private float U;
   private final Vector4f r = new Vector4f();
   private final float[] D = new float[16];
   private static final String[] c = new String[3];
   private static final long[] d = new long[]{
      3017407089772578489L,
      1021725812940563989L,
      6219181533495568636L,
      -2797385404436610368L,
      2741014802626963107L,
      3676344710221373374L,
      405351510983151458L,
      8911194354916869986L,
      7209139561744051961L,
      5035004160241671739L
   };
   private static final Integer[] g = new Integer[10];

   boolean project(HaloSupport7 var1, HaloSupport2.TypeK var2) {
      long var3 = 136009527628767L;
      long var5 = var3 ^ 76705100132379L;
      long var7 = var3 ^ 22967582216164L;
      long var9 = var3 ^ 111283456570025L;
      long var11 = var3 ^ 129288359836725L;
      this.f = 0;
      this.x = 0;
      if (var1.j != 0 && !(var2.a <= 0.003F) && var2.Y > 0 && var2.c > 0) {
         float var13 = var2.Q - var2.F;
         float var14 = var2.E - var2.d;
         float var15 = var2.I - var2.p;

         for (int var16 = 0; var16 < var1.V; var16++) {
            this.r.set(var1.g[var16], var1.e[var16], var1.s[var16], 1.0F);
            var2.D.transform(this.r);
            if (this.r.w < 0.02F) {
               return false;
            }

            this.H[var16] = (this.r.x / this.r.w * 0.5F + 0.5F) * var2.Y;
            this.A[var16] = (this.r.y / this.r.w * 0.5F + 0.5F) * var2.c;
            this.l[var16] = var2.t / this.r.w;
            float var17 = var1.g[var16] - var2.F;
            float var18 = var1.e[var16] - var2.d;
            float var19 = var1.s[var16] - var2.p;
            this.K[var16] = (float)Math.sqrt(var17 * var17 + var18 * var18 + var19 * var19);
            this.G[var16] = (var1.g[var16] - var2.Q) * var13 + (var1.e[var16] - var2.E) * var14 + (var1.s[var16] - var2.I) * var15;
         }

         for (int var21 = 0; var21 < var1.j; var21++) {
            if (var1.R[var21] == 1) {
               if (this.p(var9, 1, var1.y[var21])) {
                  this.k(var5, var1, var1.N[var21]);
                  this.N(var11);
               }
            } else {
               this.z(var1, var21, var7);
            }
         }

         if (this.x == 0) {
            return false;
         } else {
            int var22 = 0;

            while (var22 < this.x) {
               this.n[var22] = var22++;
            }

            for (int var23 = 1; var23 < this.x; var23++) {
               int var24 = this.n[var23];
               float var25 = this.z[var24];

               int var20;
               for (var20 = var23 - 1; var20 >= 0 && this.z[this.n[var20]] < var25; var20--) {
                  this.n[var20 + 1] = this.n[var20];
               }

               this.n[var20 + 1] = var24;
            }

            this.I = Math.max(0.0F, this.I);
            this.t = Math.max(0.0F, this.t);
            this.j = Math.min((float)var2.Y, this.j);
            this.U = Math.min((float)var2.c, this.U);
            return this.j > this.I && this.U > this.t;
         }
      } else {
         return false;
      }
   }

   private void z(HaloSupport7 var1, int var2, long var3) {
      long var5;
      long var7;
      short var9;
      char var10;
      int var11;
      long var12;
      long var14;
      int var16;
      int var17;
      boolean var18;
      label169: {
         var5 = 87018894827874L;
         var7 = 68652429370308L;
         long var19 = 43266428050136L;
         var9 = 10073;
         var10 = '쀓';
         var11 = (int)(var19 << 48 >>> 48);
         var12 = 33557073453430L;
         var14 = 15553776742378L;
         HaloDesign.ensureSourceClassInitialized();
         var16 = var1.N[var2];
         var17 = var1.A[var2];
         if (var1.w[var2]) {
            long var21 = 122392089254970L;
            int var24 = var21 < 0L ? -1 : 1;
            if (var17 > 2) {
               var18 = true;
               break label169;
            }
         }

         var18 = false;
      }

      boolean var43 = var18;
      int var20 = 0;
      if (var18) {
         var20 = -1;

         for (int var44 = 0; var44 < var17; var44++) {
            long var22 = 122392089254971L;
            int var47 = var22 < 0L ? -1 : 1;
            var18 = r(var9, this.G[var16 + var44], var10, var11);
            long var25 = 122392089254970L;
            int var51 = var25 < 0L ? -1 : 1;
            if (var18 != r(var9, this.G[var16 + (var44 + var17 - 1) % var17], var10, var11)) {
               var20 = var44;
               long var27 = 122392089254971L;
               int var52 = var27 < 0L ? -1 : 1;
               break;
            }
         }

         long var45 = 122392089254970L;
         var18 = (var45 < 0L ? -1 : 1) != 0;
         long var23 = 122392089254970L;
         int var26 = var23 < 0L ? -1 : 1;
         if (var20 < 0) {
            if (this.p(var12, 0, 0.0F)) {
               for (int var49 = 0; var49 <= var17; var49++) {
                  this.k(var7, var1, var16 + var49 % var17);
               }

               long var50 = 122392089254971L;
               var18 = (var50 < 0L ? -1 : 1) != 0;
               this.N(var14);
            }

            return;
         }
      }

      long var46 = 122392089254971L;
      int var24 = var46 < 0L ? -1 : 1;
      var24 = var18 ? var17 : var17 - 1;
      int var47 = var16 + var20;
      boolean var26 = this.p(var12, 0, 0.0F);
      long var51 = 122392089254971L;
      int var56 = var51 < 0L ? -1 : 1;
      if (var26) {
         if (var18) {
            this.K(var1, var5, var16 + (var20 + var17 - 1) % var17, var47);
         }

         this.k(var7, var1, var47);

         for (int var29 = 1; var29 <= var24; var29++) {
            int var30 = var16 + (var20 + var29) % var17;
            long var31 = 122392089254970L;
            int var34 = var31 < 0L ? -1 : 1;
            var26 = r(var9, this.G[var47], var10, var11);
            long var34x = 122392089254970L;
            int var34x = var34x < 0L ? -1 : 1;
            if (var26 != r(var9, this.G[var30], var10, var11)) {
               long var36 = 122392089254971L;
               int var36x = var36 < 0L ? -1 : 1;
               this.K(var1, var5, var47, var30);
               this.N(var14);
               if (!this.p(var12, 0, 0.0F)) {
                  return;
               }

               this.K(var1, var5, var47, var30);
            }

            label142: {
               long var53x = 122392089254970L;
               int var63 = var53x < 0L ? -1 : 1;
               if (var43) {
                  long var38 = 122392089254971L;
                  int var38x = var38 < 0L ? -1 : 1;
                  if (var29 == var24) {
                     break label142;
                  }
               }

               this.k(var7, var1, var30);
            }

            var47 = var30;
         }

         long var52 = 122392089254970L;
         int var59 = var52 < 0L ? -1 : 1;
         this.N(var14);
      }
   }

   private static boolean r(int var0, float var1, int var2, int var3) {
      HaloDesign.ensureSourceClassInitialized();
      return var1 < 0.0F;
   }

   private boolean p(long var1, int var3, float var4) {
      HaloDesign.ensureSourceClassInitialized();
      long var5 = 33557073453430L;
      int var8 = var5 < 0L ? -1 : 1;
      if (this.x < 64 && this.f < 288) {
         var8 = this.x;
         this.T[var8] = this.f;
         this.s[var8] = 0;
         this.C[var8] = var3;
         this.m[var8] = var4;
         return true;
      } else {
         return false;
      }
   }

   private void N(long var1) {
      HaloDesign.ensureSourceClassInitialized();
      int var3 = this.x;
      int var4 = this.s[var3];
      long var5 = 15553776742377L;
      int var8 = var5 < 0L ? -1 : 1;
      long var8x = 15553776742377L;
      int var10 = var8x < 0L ? -1 : 1;
      if (var4 != 0) {
         var10 = this.C[var3];
         long var11 = 15553776742377L;
         int var14 = var11 < 0L ? -1 : 1;
         if (var10 != 0 || var4 >= 2) {
            float var14x = 0.0F;
            float var15 = Float.MAX_VALUE;
            float var16 = Float.MAX_VALUE;
            float var17 = -Float.MAX_VALUE;
            float var18 = -Float.MAX_VALUE;
            boolean var19 = this.C[var3] == 1 && this.m[var3] > 0.0F;

            for (int var20 = this.T[var3]; var20 < this.T[var3] + var4; var20++) {
               long var21 = 15553776742377L;
               int var21x = var21 < 0L ? -1 : 1;
               var14x += this.X[var20];
               float var24x = this.y[var20] + this.k[var20] * (var19 ? 1.4F : 1.0F) + 1.5F;
               var15 = Math.min(var15, this.B[var20] - var24x);
               var16 = Math.min(var16, this.O[var20] - var24x);
               var17 = Math.max(var17, this.B[var20] + var24x);
               var18 = Math.max(var18, this.O[var20] + var24x);
            }

            long var28 = 15553776742378L;
            int var22 = var28 < 0L ? -1 : 1;
            this.z[var3] = var14x / var4;
            this.P[var3 * 4] = var15;
            long var22x = 15553776742378L;
            int var22x = var22x < 0L ? -1 : 1;
            this.P[var3 * 4 + 1] = var16;
            this.P[var3 * 4 + 2] = var17;
            this.P[var3 * 4 + 3] = var18;
            long var29x = 15553776742377L;
            int var26 = var29x < 0L ? -1 : 1;
            if (this.x == 0) {
               long var26x = 15553776742377L;
               int var26x = var26x < 0L ? -1 : 1;
               this.I = var15;
               this.t = var16;
               this.j = var17;
               this.U = var18;
            } else {
               this.I = Math.min(this.I, var15);
               this.t = Math.min(this.t, var16);
               this.j = Math.max(this.j, var17);
               this.U = Math.max(this.U, var18);
            }

            this.x++;
            return;
         }
      }

      this.f = this.T[var3];
   }

   private void k(long var1, HaloSupport7 var3, int var4) {
      HaloDesign.ensureSourceClassInitialized();
      long var5 = 68652429370307L;
      int var8 = var5 < 0L ? -1 : 1;
      if (this.f < 288) {
         var8 = this.f++;
         float var9 = this.l[var4];
         this.B[var8] = this.H[var4];
         this.O[var8] = this.A[var4];
         this.X[var8] = this.K[var4];
         this.y[var8] = var3.k[var4] * var9;
         this.k[var8] = var3.U[var4] * var9;
         this.v[var8] = var3.S[var4];
         this.E[var8] = var3.D[var4];
         this.i[var8] = var3.o[var4];
         this.V[var8] = var3.P[var4];
         this.R[var8] = var3.h[var4];
         this.q[var8] = var3.b[var4];
         this.s[this.x]++;
      }
   }

   private void K(HaloSupport7 var1, long var2, int var4, int var5) {
      HaloDesign.ensureSourceClassInitialized();
      if (this.f < 288) {
         float var6 = this.G[var4];
         float var7 = this.G[var5];
         long var8 = 87018894827873L;
         int var11 = var8 < 0L ? -1 : 1;
         float var11x = Math.abs(var6 - var7);
         long var12 = 87018894827874L;
         int var14 = var12 < 0L ? -1 : 1;
         float var14x = var11x < 1.0E-9F ? 0.5F : Math.clamp(var6 / (var6 - var7), 0.0F, 1.0F);
         int var15 = this.f++;
         float var16 = A(this.l[var4], this.l[var5], var14x);
         this.B[var15] = A(this.H[var4], this.H[var5], var14x);
         this.O[var15] = A(this.A[var4], this.A[var5], var14x);
         this.X[var15] = A(this.K[var4], this.K[var5], var14x);
         this.y[var15] = A(var1.k[var4], var1.k[var5], var14x) * var16;
         this.k[var15] = A(var1.U[var4], var1.U[var5], var14x) * var16;
         this.v[var15] = A(var1.S[var4], var1.S[var5], var14x);
         this.E[var15] = A(var1.D[var4], var1.D[var5], var14x);
         this.i[var15] = A(var1.o[var4], var1.o[var5], var14x);
         this.V[var15] = A(var1.P[var4], var1.P[var5], var14x);
         this.R[var15] = var14x < 0.5F ? var1.h[var4] : var1.h[var5];
         this.q[var15] = var14x < 0.5F ? var1.b[var4] : var1.b[var5];
         this.s[this.x]++;
      }
   }

   private static float A(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   ByteBuffer pack(HaloSupport2.TypeK var1, ByteBuffer var2) {
      long var3 = 91345143891068L;
      long var5 = var3 ^ 14951714854033L;
      var2.order(ByteOrder.nativeOrder());
      var1.s.get(this.D);

      for (float var10 : this.D) {
         var2.putFloat(var10);
      }

      X(var2, var1.Y, var1.c, this.x, var1.R ? 1.0F : 0.0F);
      X(var2, this.I, this.t, this.j, this.U);
      X(var2, var1.Q - var1.F, var1.E - var1.d, var1.I - var1.p, Math.clamp(var1.a, 0.0F, 1.0F));

      for (int var11 = 0; var11 < 64; var11++) {
         if (var11 >= this.x) {
            X(var2, 0.0F, 0.0F, 0.0F, 0.0F);
            X(var2, 0.0F, 0.0F, 0.0F, 0.0F);
         } else {
            int var13 = this.n[var11];
            X(var2, this.T[var13], this.s[var13], this.C[var13], this.m[var13]);
            X(var2, this.P[var13 * 4], this.P[var13 * 4 + 1], this.P[var13 * 4 + 2], this.P[var13 * 4 + 3]);
         }
      }

      for (int var12 = 0; var12 < this.f; var12++) {
         X(var2, this.B[var12], this.O[var12], this.X[var12], this.y[var12]);
         X(var2, this.k[var12], this.v[var12], this.E[var12], this.i[var12]);
         X(var2, J(this.R[var12], var5), J(this.q[var12], var5), this.V[var12], 0.0F);
      }

      return var2.flip();
   }

   private static void X(ByteBuffer var0, float var1, float var2, float var3, float var4) {
      var0.putFloat(var1).putFloat(var2).putFloat(var3).putFloat(var4);
   }

   private static float J(int var0, long var1) {
      return (var0 >>> 16 & 0xFF) + (var0 >>> 8 & 0xFF) * 256.0F + (var0 & 0xFF) * 65536.0F;
   }

   String stats() {
      int var1 = this.x;
      int var2 = this.f;
      int var3 = Math.round(this.j - this.I);
      int var4 = Math.round(this.U - this.t);
      return var1 + " pieces, " + var2 + " points, " + var3 + "x" + var4 + " px";
   }

   static final class TypeK {
      final Matrix4f D = new Matrix4f();
      final Matrix4f s = new Matrix4f();
      float F;
      float d;
      float p;
      float t;
      int Y;
      int c;
      boolean R;
      float Q;
      float E;
      float I;
      float a = 1.0F;
   }
}
