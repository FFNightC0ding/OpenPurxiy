package dev.purxiy.modules.trail;

import java.util.Arrays;

final class TrailSupport2 {
   static final int V = 0;
   static final int Q = 1;
   static final int b = 2;
   static final int r = 3;
   static final int K = 20;
   static final float E = 0.03F;
   static final float Z = 0.3F;
   static final float X = 0.5F;
   static final float n = 0.024F;
   static final float F = 0.02F;
   static final float m = 0.12F;
   static final float c = 1.35F;
   static final float L = 0.3F;
   private static final int T = 2050;
   private static final int P = 12300;
   private final float[] k = new float[2050];
   private final float[] z = new float[2050];
   private final float[] o = new float[2050];
   private final float[] l = new float[2050];
   private final float[] g = new float[2050];
   private final float[] f = new float[12300];
   private final float[] C = new float[12300];
   private final float[] j = new float[12300];
   private final float[] y = new float[12300];
   private final float[] D = new float[12300];
   private final float[] v = new float[12300];
   private final float[] A = new float[12300];
   private final float[] d = new float[12300];
   private final float[] M = new float[12300];
   private int e;
   private final float[] W = new float[12300];
   private final float[] i = new float[12300];
   private final float[] S = new float[12300];
   private final float[] R = new float[12300];
   private final float[] O = new float[12300];
   private final float[] J = new float[12300];
   private final float[] s = new float[12300];
   private final float[] p = new float[12300];
   private final float[] N = new float[20];
   private static final long[] a = new long[]{
      8159195015540138498L,
      7338859308592085506L,
      4013851255774285401L,
      -1730942819730555170L,
      4882125238869106551L,
      -2426933027224739586L,
      -5795642426332947884L,
      2177306422359076980L,
      -4508519866216303194L,
      5791541564364427420L,
      -4253130356936705915L,
      6075344153362308722L,
      -8259082921601776002L,
      -2566640031992249137L,
      5503640150257348378L,
      7768886306134008467L,
      -8298062381460462664L,
      -5722415646128152861L,
      -7234479934885778608L,
      184727377030605746L,
      -3436015219070812665L,
      -9166914077148171458L,
      6165145446987845174L,
      -3821625790433570717L,
      3182435671272046378L,
      5799430869129707663L,
      -1624683164720269525L,
      -1979363819627286385L,
      -2777820389809971505L,
      -7127449986735920436L,
      -3277381671465710182L,
      -4546165685887354174L,
      -2947095388819048726L,
      3775510393314139784L,
      -8097500092866868607L
   };

   int path(TrailSupport3 var1, double var2, TrailSupport2.TypeF var4, TrailSupport2.Listener var5) {
      long var6 = 92219220382884L;
      long var8 = var6 ^ 43167063138360L;
      if (var4.O != TrailStyle.T && var1.hasHead() && var1.count() != 0) {
         int var10 = 0;
         int var11 = var1.count();
         int var12 = 0;

         for (int var13 = 1; var13 <= var11; var13++) {
            if (var13 == var11 || var1.runStart(var13)) {
               boolean var14 = var13 == var11;
               var10 += this.e(var1, var12, var8, var13, var14, var2, var4, var5);
               var12 = var13;
            }
         }

         return var10;
      } else {
         return 0;
      }
   }

   private int e(TrailSupport3 var1, int var2, long var3, int var5, boolean var6, double var7, TrailSupport2.TypeF var9, TrailSupport2.Listener var10) {
      long var11 = 11102544506931L;
      long var13 = 132088908797553L;
      long var15 = 137559011884495L;
      long var17 = 103416972552516L;
      long var19 = 80270336637589L;
      short var21 = 18689;
      byte var22 = 100;
      int var23 = (int)(var19 << 40 >>> 40);
      TrailRenderer.ensureSourceClassInitialized();
      int var24 = var2;

      for (int var25 = var2 + 1; var25 < var5; var24 = var25++) {
         long var26 = 128221562123419L;
         int var52 = var26 < 0L ? -1 : 1;
         if (!(var7 - var1.time(var25) >= var9.T)) {
            break;
         }
      }

      long var51 = 128221562123420L;
      int var29 = var51 < 0L ? -1 : 1;
      var29 = 0;

      for (int var30 = var24; var30 < var5; var30++) {
         long var31 = 128221562123419L;
         int var31x = var31 < 0L ? -1 : 1;
         long var34x = 128221562123419L;
         int var331 = var34x < 0L ? -1 : 1;
         if (var29 >= 2049) {
            break;
         }

         var29 = this.z(var29, var1.x(var30), var1.y(var30), var1.z(var30), (float)((var7 - var1.time(var30)) / var9.T), (float)var1.odo(var30), var9);
      }

      long var52 = 128221562123419L;
      int var33 = var52 < 0L ? -1 : 1;
      long var33x = 128221562123420L;
      int var36 = var33x < 0L ? -1 : 1;
      if (var6) {
         long var36x = 128221562123419L;
         int var39 = var36x < 0L ? -1 : 1;
         var39 = var5 - 1;
         double var40 = var1.headX() - var1.x(var39);
         double var42 = var1.headY() - var1.y(var39);
         double var44 = var1.headZ() - var1.z(var39);
         double var46;
         int var32x = (var46 = var40 * var40 + var42 * var42 + var44 * var44 - 1.0E-6) == 0.0 ? 0 : (var46 < 0.0 ? -1 : 1);
         long var48 = 128221562123419L;
         int var46x = var48 < 0L ? -1 : 1;
         if (var32x > 0) {
            var29 = this.z(var29, var1.headX(), var1.headY(), var1.headZ(), (float)((var7 - var1.headTime()) / var9.T), (float)var1.headOdo(), var9);
         }
      }

      if (var29 >= 2 && !(this.l[var29 - 1] >= 1.0F)) {
         this.L(var29, var9, var11);
         this.b(var15);

         return switch (TrailSupport2.TypeP.A[var9.O.ordinal()]) {
            case 1 -> this.X(var9, var21, var22, var10, var23);
            case 2 -> this.a(var9, var10, var13);
            case 3 -> this.l(var9, var17, var10);
            case 4 -> 0;
            default -> throw new MatchException(null, null);
         };
      } else {
         return 0;
      }
   }

   private int z(int var1, double var2, double var4, double var6, float var8, float var9, TrailSupport2.TypeF var10) {
      this.k[var1] = (float)(var2 - var10.y);
      this.z[var1] = (float)(var4 - var10.x);
      this.o[var1] = (float)(var6 - var10.B);
      this.l[var1] = Math.min(var8, 1.0F);
      this.g[var1] = var9;
      return var1 + 1;
   }

   private void L(int var1, TrailSupport2.TypeF var2, long var3) {
      long var5 = 99745868874962L;
      TrailRenderer.ensureSourceClassInitialized();
      int var7 = 0;

      for (int var8 = 0; var8 < var1 - 1; var8++) {
         long var9 = 11102544506931L;
         int var12 = var9 < 0L ? -1 : 1;
         long var12x = 11102544506931L;
         int var14 = var12x < 0L ? -1 : 1;
         long var14x = 11102544506930L;
         int var16 = var14x < 0L ? -1 : 1;
         if (var7 >= 12292) {
            break;
         }

         var16 = var8 - 1;
         int var17 = var8 + 2;
         float var18 = var16 >= 0 ? this.k[var16] : 2.0F * this.k[var8] - this.k[var8 + 1];
         float var19 = var16 >= 0 ? this.z[var16] : 2.0F * this.z[var8] - this.z[var8 + 1];
         float var20 = var16 >= 0 ? this.o[var16] : 2.0F * this.o[var8] - this.o[var8 + 1];
         float var21 = var17 < var1 ? this.k[var17] : 2.0F * this.k[var8 + 1] - this.k[var8];
         float var22 = var17 < var1 ? this.z[var17] : 2.0F * this.z[var8 + 1] - this.z[var8];
         float var23 = var17 < var1 ? this.o[var17] : 2.0F * this.o[var8 + 1] - this.o[var8];
         float var24 = this.k[var8];
         float var25 = this.z[var8];
         float var26 = this.o[var8];
         float var27 = this.k[var8 + 1];
         float var28 = this.z[var8 + 1];
         float var29 = this.o[var8 + 1];
         float var30 = j(var5, var24 - var18, var25 - var19, var26 - var20, var27 - var24, var28 - var25, var29 - var26)
            + j(var5, var27 - var24, var28 - var25, var29 - var26, var21 - var27, var22 - var28, var23 - var29);
         int var31 = Math.clamp((long)((int)Math.ceil(var30 / 0.06F)), 1, 6);
         if (var2.O == TrailStyle.Y) {
            float var32 = Math.abs(this.g[var8 + 1] - this.g[var8]) * (float) (Math.PI * 2) / 1.35F;
            var31 = Math.max(var31, Math.clamp((long)((int)Math.ceil(var32 / 0.12F)), 1, 6));
         }

         float var44 = 0.0F;
         float var33 = var44 + u(var18, var19, var20, var24, var25, var26);
         float var34 = var33 + u(var24, var25, var26, var27, var28, var29);
         float var35 = var34 + u(var27, var28, var29, var21, var22, var23);

         for (int var36 = 0; var36 < var31; var36++) {
            float var37 = (float)var36 / var31;
            float var38 = var33 + (var34 - var33) * var37;
            long var39 = 11102544506930L;
            int var40 = var39 < 0L ? -1 : 1;
            this.f[var7] = centripetal(var18, var24, var27, var21, var44, var33, var34, var35, var38);
            this.C[var7] = centripetal(var19, var25, var28, var22, var44, var33, var34, var35, var38);
            this.j[var7] = centripetal(var20, var26, var29, var23, var44, var33, var34, var35, var38);
            this.y[var7] = this.l[var8] + (this.l[var8 + 1] - this.l[var8]) * var37;
            this.D[var7] = this.g[var8] + (this.g[var8 + 1] - this.g[var8]) * var37;
            var7++;
         }

         long var45 = 11102544506930L;
         int var50 = var45 < 0L ? -1 : 1;
      }

      this.f[var7] = this.k[var1 - 1];
      this.C[var7] = this.z[var1 - 1];
      long var42 = 11102544506931L;
      int var43 = var42 < 0L ? -1 : 1;
      this.j[var7] = this.o[var1 - 1];
      this.y[var7] = this.l[var1 - 1];
      this.D[var7] = this.g[var1 - 1];
      this.e = ++var7;
      this.v[var7 - 1] = 0.0F;

      for (int var11x = var7 - 2; var11x >= 0; var11x--) {
         this.v[var11x] = this.v[var11x + 1] + H(this.f[var11x], this.C[var11x], this.j[var11x], this.f[var11x + 1], this.C[var11x + 1], this.j[var11x + 1]);
      }

      long var43x = 11102544506931L;
      int var12x = var43x < 0L ? -1 : 1;
   }

   private static float u(float var0, float var1, float var2, float var3, float var4, float var5) {
      return Math.max((float)Math.sqrt(H(var0, var1, var2, var3, var4, var5)), 0.001F);
   }

   static float centripetal(float var0, float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = ((var5 - var8) * var0 + (var8 - var4) * var1) / (var5 - var4);
      float var10 = ((var6 - var8) * var1 + (var8 - var5) * var2) / (var6 - var5);
      float var11 = ((var7 - var8) * var2 + (var8 - var6) * var3) / (var7 - var6);
      float var12 = ((var6 - var8) * var9 + (var8 - var4) * var10) / (var6 - var4);
      float var13 = ((var7 - var8) * var10 + (var8 - var5) * var11) / (var7 - var5);
      return ((var6 - var8) * var12 + (var8 - var5) * var13) / (var6 - var5);
   }

   private void b(long var1) {
      TrailRenderer.ensureSourceClassInitialized();
      int var3 = this.e;

      for (int var4 = 0; var4 < var3; var4++) {
         int var5 = Math.max(var4 - 1, 0);
         int var6 = Math.min(var4 + 1, var3 - 1);
         float var7 = this.f[var6] - this.f[var5];
         float var8 = this.C[var6] - this.C[var5];
         float var9 = this.j[var6] - this.j[var5];
         float var10 = (float)Math.sqrt(var7 * var7 + var8 * var8 + var9 * var9);
         long var11 = 137559011884494L;
         int var14 = var11 < 0L ? -1 : 1;
         if (var10 < 1.0E-6F) {
            this.A[var4] = var4 > 0 ? this.A[var4 - 1] : 1.0F;
            this.d[var4] = var4 > 0 ? this.d[var4 - 1] : 0.0F;
            this.M[var4] = var4 > 0 ? this.M[var4 - 1] : 0.0F;
            long var14x = 137559011884495L;
            int var15 = var14x < 0L ? -1 : 1;
         } else {
            this.A[var4] = var7 / var10;
            this.d[var4] = var8 / var10;
            this.M[var4] = var9 / var10;
         }
      }
   }

   private int X(TrailSupport2.TypeF var1, int var2, byte var3, TrailSupport2.Listener var4, int var5) {
      long var6 = 80270336637589L;
      long var8 = var6 ^ 115216822931013L;
      int var10 = this.e;
      TrailRenderer.ensureSourceClassInitialized();

      for (int var11 = 0; var11 < var10; var11++) {
         float var12 = this.y[var11];
         float var13 = (float)Math.sqrt(1.0F - var12);
         this.W[var11] = this.f[var11];
         this.i[var11] = this.C[var11];
         this.S[var11] = this.j[var11];
         this.R[var11] = 0.03F * var1.E * var13;
         this.O[var11] = this.R[var11] + 0.3F * var1.E * (0.4F + 0.6F * var13);
         this.J[var11] = this.f(var11, var1);
      }

      return this.y(var4, var8, 0);
   }

   private int a(TrailSupport2.TypeF var1, TrailSupport2.Listener var2, long var3) {
      long var5 = 137906079197055L;
      long var7 = 69895903985402L;
      int var9 = this.e;
      TrailRenderer.ensureSourceClassInitialized();
      float var10 = 1.0F;
      float var11 = 0.0F;

      for (int var12 = 0; var12 < var9; var12++) {
         float var13 = -this.M[var12];
         long var14 = 132088908797552L;
         int var29 = var14 < 0L ? -1 : 1;
         float var17 = this.A[var12];
         float var18 = (float)Math.sqrt(var13 * var13 + var17 * var17);
         long var19 = 132088908797553L;
         int var21 = var19 < 0L ? -1 : 1;
         long var21x = 132088908797552L;
         int var33 = var21x < 0L ? -1 : 1;
         float var31;
         if (var18 > 0.2F) {
            var13 /= var18;
            var31 = var17 / var18;
            long var23 = 132088908797552L;
            int var24 = var23 < 0L ? -1 : 1;
            var10 = var13;
            var11 = var31;
         } else {
            var13 = var10;
            var31 = var11;
         }

         this.s[var12] = var13;
         this.p[var12] = var31;
         float var33x = (float)Math.sqrt(1.0F - this.y[var12]);
         this.O[var12] = 0.5F * var1.E * (0.45F + 0.55F * var33x);
         this.R[var12] = 0.024F * var1.E * (0.5F + 0.5F * var33x);
         this.J[var12] = this.f(var12, var1);
      }

      long var25 = 132088908797553L;
      int var15 = var25 < 0L ? -1 : 1;
      var15 = 0;

      for (int var16 = 0; var16 < var9 - 1; var16++) {
         boolean var29 = this.m(var16, var5);
         long var30 = 132088908797552L;
         int var32 = var30 < 0L ? -1 : 1;
         if (var29) {
            long var31 = 132088908797553L;
            int var211 = var31 < 0L ? -1 : 1;
         } else {
            var32 = var16 + 1;
            this.E(var2, var16, -1.0F, var7);
            this.E(var2, var32, -1.0F, var7);
            this.E(var2, var32, 1.0F, var7);
            this.E(var2, var16, 1.0F, var7);
            var15++;
         }
      }

      long var27 = 132088908797553L;
      int var34 = var27 < 0L ? -1 : 1;
      return var15;
   }

   private void E(TrailSupport2.Listener var1, int var2, float var3, long var4) {
      float[] var6 = this.N;
      float var7 = this.O[var2];
      var6[0] = this.f[var2] + this.s[var2] * var7 * var3;
      var6[1] = this.C[var2] + 0.02F;
      var6[2] = this.j[var2] + this.p[var2] * var7 * var3;
      var6[3] = 1.0F;
      var6[4] = var3;
      var6[5] = var7;
      var6[6] = this.R[var2];
      var6[7] = 0.0F;
      var6[8] = 0.0F;
      var6[9] = 0.0F;
      var6[10] = 0.0F;
      var6[11] = 0.0F;
      var6[12] = this.y[var2];
      var6[13] = this.v[var2];
      var6[14] = this.J[var2];
      var6[15] = this.D[var2];
      var6[16] = 0.0F;
      var6[17] = 0.0F;
      var6[18] = 0.0F;
      var6[19] = 0.0F;
      var1.vertex(var6, -1);
   }

   private int l(TrailSupport2.TypeF var1, long var2, TrailSupport2.Listener var4) {
      long var6 = 37157459408080L;
      int var8 = this.e;
      TrailRenderer.ensureSourceClassInitialized();
      int var9 = 0;

      for (int var10 = 0; var10 < 2; var10++) {
         float var11 = 1.0F;
         float var12 = 0.0F;

         for (int var13 = 0; var13 < var8; var13++) {
            float var14 = this.A[var13];
            float var15 = this.d[var13];
            float var16 = this.M[var13];
            float var17 = -var16;
            float var19 = (float)Math.sqrt(var17 * var17 + var14 * var14);
            long var20 = 103416972552516L;
            int var20x = var20 < 0L ? -1 : 1;
            long var23x = 103416972552516L;
            int var26 = var23x < 0L ? -1 : 1;
            long var26x = 103416972552516L;
            int var18 = var26x < 0L ? -1 : 1;
            float var18x;
            if (var19 > 0.2F) {
               long var28 = 103416972552515L;
               int var30 = var28 < 0L ? -1 : 1;
               var17 /= var19;
               var18x = var14 / var19;
               var11 = var17;
               var12 = var18x;
            } else {
               var17 = var11;
               var18x = var12;
            }

            float var53 = var15 * var18x;
            float var29 = var16 * var17 - var14 * var18x;
            float var30 = -var15 * var17;
            float var31 = (float)Math.sqrt(var53 * var53 + var29 * var29 + var30 * var30);
            long var32 = 103416972552515L;
            int var31x = var32 < 0L ? -1 : 1;
            if (var31 > 1.0E-6F) {
               var53 /= var31;
               var29 /= var31;
               var30 /= var31;
            }

            float var5;
            label72: {
               float var35x = this.y[var13];
               float var36 = smoothstep(0.0F, 0.9F, this.v[var13]) * (1.0F - 0.65F * var35x * var35x);
               float var37 = 0.12F * var1.E * var36;
               float var38 = this.D[var13] * (float) (Math.PI * 2) / 1.35F - var1.X * 1.1F + var10 * (float) Math.PI;
               float var39 = (float)Math.cos(var38);
               float var40 = (float)Math.sin(var38);
               float var41 = (var17 * var39 + var53 * var40) * var37;
               float var42 = var29 * var40 * var37;
               float var43 = (var18x * var39 + var30 * var40) * var37;
               this.W[var13] = this.f[var13] + var41;
               this.i[var13] = this.C[var13] + var42;
               this.S[var13] = this.j[var13] + var43;
               float var44 = (float)Math.sqrt(1.0F - var35x);
               this.R[var13] = 0.0216F * var1.E * var44;
               this.O[var13] = this.R[var13] + 0.22500001F * var1.E * (0.4F + 0.6F * var44);
               float var45 = (float)Math.sqrt(var41 * var41 + var42 * var42 + var43 * var43);
               float var46 = (float)Math.sqrt(this.f[var13] * this.f[var13] + this.C[var13] * this.C[var13] + this.j[var13] * this.j[var13]);
               long var47 = 103416972552516L;
               int var5x = var47 < 0L ? -1 : 1;
               if (var45 > 1.0E-5F) {
                  long var49 = 103416972552515L;
                  int var48 = var49 < 0L ? -1 : 1;
                  if (var46 > 1.0E-5F) {
                     var5 = -(var41 * this.f[var13] + var42 * this.C[var13] + var43 * this.j[var13]) / (var45 * var46);
                     break label72;
                  }
               }

               var5 = 0.0F;
            }

            this.J[var13] = this.f(var13, var1) * (0.8F + 0.2F * var5);
         }

         var9 += this.y(var4, var6, var10);
         long var51 = 103416972552515L;
         int var50 = var51 < 0L ? -1 : 1;
      }

      return var9;
   }

   private float f(int var1, TrailSupport2.TypeF var2) {
      return smoothstep(0.0F, Math.max(var2.b, 0.3F), this.v[var1]);
   }

   private boolean m(int var1, long var2) {
      TrailRenderer.ensureSourceClassInitialized();
      long var4 = 137906079197054L;
      int var7 = var4 < 0L ? -1 : 1;
      float var7x = this.y[var1];
      long var8 = 137906079197055L;
      int var9 = var8 < 0L ? -1 : 1;
      return var7x >= 1.0F && this.y[var1 + 1] >= 1.0F || this.J[var1] <= 0.0F && this.J[var1 + 1] <= 0.0F;
   }

   private int y(TrailSupport2.Listener var1, long var2, int var4) {
      long var5 = 137906079197055L;
      TrailRenderer.ensureSourceClassInitialized();
      int var7 = 0;
      float[] var8 = this.N;

      for (int var9 = 0; var9 < this.e - 1; var9++) {
         boolean var10 = this.m(var9, var5);
         long var11 = 37157459408080L;
         int var14 = var11 < 0L ? -1 : 1;
         if (var10) {
            long var20 = 37157459408080L;
            int var23 = var20 < 0L ? -1 : 1;
         } else {
            var14 = var9 + 1;
            var8[0] = this.W[var9];
            var8[1] = this.i[var9];
            var8[2] = this.S[var9];
            var8[3] = 0.0F;
            var8[4] = this.W[var14];
            var8[5] = this.i[var14];
            var8[6] = this.S[var14];
            var8[7] = var4;
            var8[8] = this.R[var9];
            var8[9] = this.R[var14];
            var8[10] = this.O[var9];
            var8[11] = this.O[var14];
            var8[12] = this.y[var9];
            var8[13] = this.y[var14];
            var8[14] = this.v[var9];
            var8[15] = this.v[var14];
            var8[16] = this.J[var9];
            var8[17] = this.J[var14];
            var8[18] = this.D[var9];
            var8[19] = this.D[var14];

            for (int var15 = 0; var15 < 4; var15++) {
               long var16 = 37157459408080L;
               int var17 = var16 < 0L ? -1 : 1;
               var1.vertex(var8, -1);
            }

            long var21 = 37157459408080L;
            int var161 = var21 < 0L ? -1 : 1;
            var7++;
         }
      }

      long var19 = 37157459408080L;
      int var19x = var19 < 0L ? -1 : 1;
      return var7;
   }

   int motes(TrailUpdater var1, TrailSupport2.TypeF var2, TrailSupport2.Listener var3) {
      float[] var4 = this.N;
      Arrays.fill(var4, 0.0F);

      for (int var5 = 0; var5 < var1.count(); var5++) {
         var4[0] = (float)(var1.x(var5) - var2.y);
         var4[1] = (float)(var1.y(var5) - var2.x);
         var4[2] = (float)(var1.z(var5) - var2.B);
         var4[3] = 2.0F;
         var4[4] = var1.size(var5);
         var4[5] = var1.phase(var5);
         var4[12] = var1.progress(var5);

         for (int var6 = 0; var6 < 4; var6++) {
            var3.vertex(var4, 0xFF000000 | var1.color(var5));
         }
      }

      return var1.count();
   }

   int orb(float var1, float var2, float var3, float var4, TrailSupport2.Listener var5) {
      float[] var6 = this.N;
      Arrays.fill(var6, 0.0F);
      var6[0] = var1;
      var6[1] = var2;
      var6[2] = var3;
      var6[3] = 3.0F;
      var6[4] = var4;

      for (int var7 = 0; var7 < 4; var7++) {
         var5.vertex(var6, -1);
      }

      return 1;
   }

   private static float H(float var0, float var1, float var2, float var3, float var4, float var5) {
      float var6 = var3 - var0;
      float var7 = var4 - var1;
      float var8 = var5 - var2;
      return (float)Math.sqrt(var6 * var6 + var7 * var7 + var8 * var8);
   }

   private static float j(long var0, float var2, float var3, float var4, float var5, float var6, float var7) {
      TrailRenderer.ensureSourceClassInitialized();
      float var8 = (float)Math.sqrt(var2 * var2 + var3 * var3 + var4 * var4);
      float var9 = (float)Math.sqrt(var5 * var5 + var6 * var6 + var7 * var7);
      if (!(var8 < 1.0E-6F)) {
         long var10 = 99745868874962L;
         int var13 = var10 < 0L ? -1 : 1;
         if (!(var9 < 1.0E-6F)) {
            float var13x = Math.clamp((var2 * var5 + var3 * var6 + var4 * var7) / (var8 * var9), -1.0F, 1.0F);
            return (float)Math.acos(var13x);
         }
      }

      return 0.0F;
   }

   static float smoothstep(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   interface Listener {
      void vertex(float[] var1, int var2);
   }

   static final class TypeF {
      TrailStyle O = TrailStyle.q;
      float T = 1.2F;
      float E = 1.0F;
      float z = 0.6F;
      float X;
      double y;
      double x;
      double B;
      float b = 0.3F;
   }

   static class TypeP {
      static final int[] A = new int[TrailStyle.values().length];

      static {
         try {
            A[TrailStyle.q.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
         }

         try {
            A[TrailStyle.D.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
         }

         try {
            A[TrailStyle.Y.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
         }

         try {
            A[TrailStyle.T.ordinal()] = 4;
         } catch (NoSuchFieldError var1) {
         }
      }
   }
}
