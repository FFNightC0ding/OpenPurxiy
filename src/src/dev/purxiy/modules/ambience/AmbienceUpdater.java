// Sourced By DevFFNightmare
package dev.purxiy.modules.ambience;

import net.minecraft.class_1944;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_3532;
import net.minecraft.class_3610;
import net.minecraft.class_638;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_2902.class_2903;

final class AmbienceUpdater {
   static final int i = 2400;
   static final byte N = 0;
   static final byte b = 1;
   static final byte l = 2;
   static final byte g = 3;
   private static final float R = (float) (Math.PI * 2);
   private static final float n = 2.6F;
   private static final float Z = 1.8F;
   private static final float O = 3.0F;
   private static final float q = 0.08F;
   private static final float m = 12.0F;
   private static final float u = 0.05F;
   final double[] H = new double[2400];
   final double[] A = new double[2400];
   final double[] F = new double[2400];
   final float[] h9 = new float[2400];
   final float[] X = new float[2400];
   final float[] Q = new float[2400];
   final byte[] j = new byte[2400];
   final byte[] k = new byte[2400];
   private final boolean[] M = new boolean[2400];
   private final boolean[] hA = new boolean[2400];
   final float[] e = new float[2400];
   final float[] p = new float[2400];
   final float[] h = new float[2400];
   final float[] o = new float[2400];
   private final float[] hb = new float[2400];
   private final float[] G = new float[2400];
   private final float[] y = new float[2400];
   final float[] Y = new float[2400];
   final float[] r = new float[2400];
   final float[] d = new float[2400];
   final float[] T = new float[2400];
   final float[] h4 = new float[2400];
   final float[] S = new float[2400];
   final float[] hY = new float[2400];
   final float[] t = new float[2400];
   final float[] K = new float[2400];
   final float[] D = new float[2400];
   final float[] hC = new float[2400];
   final float[] s = new float[2400];
   final float[] z = new float[2400];
   final float[] L = new float[2400];
   final float[] x = new float[2400];
   private final float[] a = new float[2400];
   private final float[] c = new float[2400];
   private final float[] P = new float[2400];
   private final long[] h7 = new long[2400];
   private final float[] C = new float[2400];
   private final float[] U = new float[2400];
   private final boolean[] hT = new boolean[2400];
   private final float[] v = new float[2400];
   private final float[] I = new float[2400];
   private final float[] E = new float[2400];
   private final float[] hm = new float[2400];
   int W;
   private long J = -7046029254386353131L;
   private final class_2339 B = new class_2339();
   private float V;
   private float f;
   private float w;


   private static final long[] cb = new long[]{-2373471421456856733L, -3964685089153714785L};
   private static final Long[] db = new Long[2];

   void clear() {
      this.W = 0;
   }

   void scatter() {
      for (int var1 = 0; var1 < this.W; var1++) {
         if (!this.M[var1]) {
            this.j[var1] = 0;
            this.hb[var1] = 0.0F;
            this.hA[var1] = true;
         }
      }
   }

   void populate(AmbienceCatalog var1, int var2) {
      byte var3 = (byte)var1.index();
      int var4 = 0;

      for (int var5 = 0; var5 < this.W; var5++) {
         if (!this.M[var5] && this.k[var5] != var3) {
            this.M[var5] = true;
         }

         if (!this.M[var5]) {
            var4++;
         }
      }

      var2 = Math.min(var2, 2400);

      for (int var7 = this.W - 1; var7 >= 0 && var4 > var2; var7--) {
         if (!this.M[var7]) {
            this.M[var7] = true;
            var4--;
         }
      }

      while (var4 < var2 && this.W < 2400) {
         int var8 = this.W++;
         this.k[var8] = var3;
         this.j[var8] = 0;
         this.M[var8] = false;
         this.hA[var8] = true;
         this.hb[var8] = 0.0F;
         this.G[var8] = 0.0F;
         var4++;
      }
   }

   private void K(int var1, int var2, int var3) {
      AmbienceRegistry.ensureSourceClassInitialized();
      int var4 = --this.W;
      if (var1 != var4) {
         this.H[var1] = this.H[var4];
         this.A[var1] = this.A[var4];
         this.F[var1] = this.F[var4];
         this.h9[var1] = this.h9[var4];
         this.X[var1] = this.X[var4];
         this.Q[var1] = this.Q[var4];
         this.j[var1] = this.j[var4];
         this.k[var1] = this.k[var4];
         this.M[var1] = this.M[var4];
         this.hA[var1] = this.hA[var4];
         this.e[var1] = this.e[var4];
         this.p[var1] = this.p[var4];
         this.h[var1] = this.h[var4];
         this.o[var1] = this.o[var4];
         this.hb[var1] = this.hb[var4];
         this.G[var1] = this.G[var4];
         this.y[var1] = this.y[var4];
         this.Y[var1] = this.Y[var4];
         this.r[var1] = this.r[var4];
         this.d[var1] = this.d[var4];
         this.T[var1] = this.T[var4];
         this.h4[var1] = this.h4[var4];
         this.S[var1] = this.S[var4];
         this.hY[var1] = this.hY[var4];
         this.t[var1] = this.t[var4];
         this.K[var1] = this.K[var4];
         this.D[var1] = this.D[var4];
         this.hC[var1] = this.hC[var4];
         this.s[var1] = this.s[var4];
         this.z[var1] = this.z[var4];
         this.L[var1] = this.L[var4];
         this.x[var1] = this.x[var4];
         this.a[var1] = this.a[var4];
         this.c[var1] = this.c[var4];
         this.P[var1] = this.P[var4];
         this.h7[var1] = this.h7[var4];
         this.C[var1] = this.C[var4];
         this.U[var1] = this.U[var4];
         this.hT[var1] = this.hT[var4];
         this.v[var1] = this.v[var4];
         this.I[var1] = this.I[var4];
         this.E[var1] = this.E[var4];
         this.hm[var1] = this.hm[var4];
      }
   }

   void update(AmbienceUpdater.TypeV var1, float var2) {
      long var3 = 80508558751499L;
      long var5 = var3 ^ 76864534082463L;
      int var7 = (int)((var3 ^ 76864534082463L) >>> 48);
      int var8 = (int)((var3 ^ 76864534082463L) << 16 >>> 32);
      int var9 = (int)(var5 << 48 >>> 48);
      int var10 = (int)((var3 ^ 85250153293691L) >>> 32);
      int var11 = (int)((var3 ^ 85250153293691L) << 32 >>> 32);
      long var12 = var3 ^ 40888981155177L;
      long var14 = (var3 ^ 17238559427198L) >>> 8;
      int var16 = (int)((var3 ^ 17238559427198L) << 56 >>> 56);
      long var17 = var3 ^ 54946533142910L;
      long var19 = var3 ^ 83820389626636L;
      long var21 = var3 ^ 80433603446386L;
      float var23 = (float)Math.exp(-var2 * 1.6);
      float var24 = var2 / 0.7F;
      float var25 = var2 / 0.8F;
      float var26 = 1.0F - (float)Math.exp(-var2 * 2.5);
      float var27 = 1.0F - (float)Math.exp(-var2 * 9.0);

      for (int var28 = this.W - 1; var28 >= 0; var28--) {
         if (this.M[var28]) {
            this.G[var28] = this.G[var28] - var25;
            if (this.G[var28] <= 0.0F || this.j[var28] == 0) {
               this.K(var28, var10, var11);
               continue;
            }
         } else if (this.G[var28] < 1.0F) {
            this.G[var28] = Math.min(1.0F, this.G[var28] + var24);
         }

         AmbienceCatalog var29 = AmbienceCatalog.e.get(this.k[var28]);
         switch (this.j[var28]) {
            case 0:
               this.Y[var28] = 0.0F;
               this.hb[var28] = this.hb[var28] - var2;
               if (this.hb[var28] <= 0.0F && !this.X((char)var7, var28, var8, (char)var9, var29, var1)) {
                  this.hb[var28] = 0.4F + this.S(var21);
               }
               continue;
            case 1:
               this.G(var28, var29, var1, var2, var23, var12);
               break;
            case 2:
            case 3:
               this.K(var28, var29, var1, var2, var17, var27);
         }

         if (this.j[var28] == 0) {
            this.Y[var28] = 0.0F;
         } else if (!this.L(var19, var28, var1)) {
            this.Y[var28] = 0.0F;
         } else {
            this.Y[var28] = this.J(var28, var14, var29, var1, var26, (byte)var16);
         }
      }
   }

   private void G(int var1, AmbienceCatalog var2, AmbienceUpdater.TypeV var3, float var4, float var5, long var6) {
      long var8 = 1171855579L;
      char var10 = '캇';
      long var11 = 55859033003631L;
      short var13 = 18131;
      int var14 = 1577600698;
      long var15 = 7751356693291L;
      AmbienceRegistry.ensureSourceClassInitialized();
      this.e[var1] = this.e[var1] + var4;
      if (this.e[var1] >= this.p[var1]) {
         this.i(var8, var1, (char)var10);
      } else {
         float var17 = var3.P;
         float var18 = var3.o;
         long var19 = 118785528420961L;
         if (118785528420961L == 0L) {
            boolean var10000 = false;
         } else {
            int var59 = var19 < 0L ? -1 : 1;
         }

         this.hm[var1] = this.hm[var1] - var4;
         long var22 = 118785528420962L;
         if (118785528420962L == 0L) {
            boolean var60 = false;
         } else {
            int var61 = var22 < 0L ? -1 : 1;
         }

         if (this.hm[var1] <= 0.0F) {
            this.hm[var1] = 0.05F;
            float var24 = this.S[var1];
            float var25 = this.hY[var1];
            double var26 = this.H[var1];
            double var28 = this.A[var1];
            double var30 = this.F[var1];
            float var32 = 0.5F * class_3532.method_15374(var30 * 0.23 + var17 * 0.05)
               + 0.5F * class_3532.method_15362(var28 * 0.23 - var17 * 0.043)
               + 0.22F * class_3532.method_15374(var30 * 0.57 - var17 * 0.091)
               + 0.22F * class_3532.method_15362(var28 * 0.57 + var17 * 0.077);
            float var33 = 0.5F * class_3532.method_15374(var26 * 0.23 + var17 * 0.061)
               + 0.5F * class_3532.method_15362(var30 * 0.23 + var17 * 0.037)
               + 0.22F * class_3532.method_15374(var26 * 0.57 + var17 * 0.083)
               + 0.22F * class_3532.method_15362(var30 * 0.57 - var17 * 0.069);
            float var34 = 0.5F * class_3532.method_15374(var28 * 0.23 - var17 * 0.047)
               + 0.5F * class_3532.method_15362(var26 * 0.23 + var17 * 0.055)
               + 0.22F * class_3532.method_15374(var28 * 0.57 + var17 * 0.073)
               + 0.22F * class_3532.method_15362(var26 * 0.57 - var17 * 0.087);
            float var35 = var2.Y * (0.6F + 0.8F * this.T[var1]);
            float var36 = var32 * var2.o;
            long var37 = 118785528420961L;
            if (118785528420961L == 0L) {
               boolean var62 = false;
            } else {
               int var63 = var37 < 0L ? -1 : 1;
            }

            this.v[var1] = var36 + var35 * class_3532.method_15374(var24 + var17 * var25 * 0.7F);
            this.I[var1] = var33 * var2.o * (var2.V ? 0.35F : 0.6F) + var35 * 0.5F * class_3532.method_15374(var24 * 0.7F + var17 * var25 * 0.9F);
            this.E[var1] = var34 * var2.o + var35 * class_3532.method_15362(var24 * 1.3F + var17 * var25 * 0.6F);
         }

         float var40 = this.v[var1] + var3.Z * var2.f;
         float var41 = this.I[var1];
         float var43 = this.E[var1] + var3.W * var2.f;
         long var27 = 118785528420961L;
         if (118785528420961L == 0L) {
            boolean var64 = false;
         } else {
            int var65 = var27 < 0L ? -1 : 1;
         }

         if (var2.H >= 0.0F) {
            float var44 = 0.8F * this.T[var1];
            long var31 = 118785528420961L;
            if (118785528420961L == 0L) {
               boolean var66 = false;
            } else {
               int var67 = var31 < 0L ? -1 : 1;
            }

            var41 += var2.H * (0.6F + var44);
         } else {
            float var45 = -var2.H * (0.75F + 0.5F * this.T[var1]) * (1.0F + var3.z * 0.6F);
            if (var2.F == 2) {
               float var47 = 2.0F * (this.s[var1] * this.L[var1] + this.x[var1] * this.z[var1]);
               float var48 = 1.0F - 2.0F * (this.s[var1] * this.s[var1] + this.z[var1] * this.z[var1]);
               float var51 = 2.0F * (this.z[var1] * this.L[var1] - this.x[var1] * this.s[var1]);
               var45 *= 1.2F - 0.45F * Math.abs(var48);
               float var53 = Math.signum(var48) * 0.35F * var45;
               var40 += var47 * var53;
               var43 += var51 * var53;
               this.Y(var1, var4 * var18);
            }

            var41 -= var45;
         }

         long var46 = 118785528420961L;
         if (118785528420961L == 0L) {
            boolean var68 = false;
         } else {
            int var69 = var46 < 0L ? -1 : 1;
         }

         if (var2.F == 1 || var2.F == 3) {
            this.t[var1] = (this.t[var1] + this.K[var1] * var4 * var18) % (float) (Math.PI * 2);
         }

         if (!var2.V) {
            long var49 = 118785528420962L;
            if (118785528420962L == 0L) {
               boolean var70 = false;
            } else {
               int var71 = var49 < 0L ? -1 : 1;
            }

            if (var3.d > 0.0F && this.h4[var1] < 0.08F) {
               float var54 = this.A(var1, var3, var15);
               if (var54 > 0.0F) {
                  var40 += (this.V - var40) * var54;
                  var41 += (this.f - var41) * var54;
                  var43 += (this.w - var43) * var54;
                  this.e[var1] = this.e[var1] - var4 * var54;
               }
            }
         }

         if (var3.w) {
            this.x(var1, var3, var13, var14, var4);
         }

         float var50 = this.h9[var1] = this.h9[var1] * var5;
         float var52 = this.X[var1] = this.X[var1] * var5;
         float var55 = this.Q[var1] = this.Q[var1] * var5;
         float var56 = (var40 * var18 + var50) * var4;
         float var57 = (var41 * var18 + var52) * var4;
         float var58 = (var43 * var18 + var55) * var4;
         this.H[var1] = this.H[var1] + var56;
         this.A[var1] = this.A[var1] + var57;
         this.F[var1] = this.F[var1] + var58;
         this.k(var11, var1, var2, var3.Y, var56, var57, var58);
      }
   }

   private float A(int var1, AmbienceUpdater.TypeV var2, long var3) {
      AmbienceRegistry.ensureSourceClassInitialized();
      double var5 = this.H[var1] - var2.V;
      double var7 = this.F[var1] - var2.H;
      if (var5 * var5 + var7 * var7 > 144.0) {
         return 0.0F;
      } else {
         float var9 = this.T[var1];
         float var10 = this.S[var1];
         float var11 = var2.P;
         float var12 = var9 < 0.5F ? -0.22F : 0.22F;
         float var13 = var10 + var11 * var12;
         float var14 = 2.3F + 1.1F * var9;
         float var15 = class_3532.method_15362(var13);
         float var16 = class_3532.method_15374(var13);
         double var17 = var2.V + var14 * var15 - this.H[var1];
         double var19 = var2.l + 1.0 + 0.9 * var9 + 0.25 * class_3532.method_15374(var11 * 0.7F + var10) - this.A[var1];
         double var21 = var2.H + var14 * var16 - this.F[var1];
         float var23 = -var14 * var12 * var16 + (float)var17 * 0.5F;
         float var24 = (float)var19 * 0.5F;
         float var25 = var14 * var12 * var15 + (float)var21 * 0.5F;
         float var26 = (float)Math.sqrt(var23 * var23 + var24 * var24 + var25 * var25);
         long var27 = 7751356693290L;
         if (7751356693290L == 0L) {
            boolean var10000 = false;
         } else {
            int var31 = var27 < 0L ? -1 : 1;
         }

         float var30 = var26 > 0.9F ? 0.9F / var26 : 1.0F;
         this.V = var23 * var30;
         this.f = var24 * var30;
         this.w = var25 * var30;
         return var2.d;
      }
   }

   private void x(int var1, AmbienceUpdater.TypeV var2, int var3, int var4, float var5) {
      AmbienceRegistry.ensureSourceClassInitialized();

      for (int var6 = 0; var6 < var2.M; var6++) {
         double var7 = this.H[var1] - var2.y[var6];
         double var9 = this.F[var1] - var2.I[var6];
         int var11 = 18131;
         if (!(Math.abs(var7) > 1.8F)) {
            var11 = 1577600698;
            double var12;
            int var14 = (var12 = Math.abs(var9) - 1.8F) == 0.0 ? 0 : (var12 < 0.0 ? -1 : 1);
            int var15 = 18131;
            if (var14 <= 0) {
               double var16 = var2.F[var6];
               var15 = 1577600698;
               double var18 = var16 + var2.t[var6];
               double var20 = Math.min(Math.max(this.A[var1], Math.min(var16 + 0.2, var18)), var18);
               double var22 = this.A[var1] - var20;
               double var24 = var7 * var7 + var22 * var22 + var9 * var9;
               int var26 = 18131;
               if (!(var24 >= 3.2399998F)) {
                  double var27;
                  var14 = (var27 = var24 - 1.0E-6) == 0.0 ? 0 : (var27 < 0.0 ? -1 : 1);
                  var26 = 1577600698;
                  if (var14 >= 0) {
                     float var29 = (float)Math.sqrt(var24);
                     float var30 = 1.0F - var29 / 1.8F;
                     var30 *= var30;
                     float var31 = var2.C[var6];
                     float var32 = var2.r[var6];
                     float var33 = var2.L[var6];
                     float var34 = (float)Math.sqrt(var31 * var31 + var32 * var32 + var33 * var33);
                     float var35 = (0.8F + 2.6F * var34) * var30 * var5 / var29;
                     float var36 = 1.4F * var30 * var5;
                     float var37 = (this.T[var1] < 0.5F ? -0.9F : 0.9F) * var34 * var30 * var5 / var29;
                     this.h9[var1] = this.h9[var1] + ((float)var7 * var35 + var31 * var36 - (float)var9 * var37);
                     this.X[var1] = this.X[var1] + ((float)var22 * var35 * 0.6F + var32 * var36 * 0.5F);
                     this.Q[var1] = this.Q[var1] + ((float)var9 * var35 + var33 * var36 + (float)var7 * var37);
                  }
               }
            }
         }
      }

      float var38 = this.h9[var1] * this.h9[var1] + this.X[var1] * this.X[var1] + this.Q[var1] * this.Q[var1];
      if (var38 > 9.0F) {
         float var8 = 3.0F / (float)Math.sqrt(var38);
         this.h9[var1] = this.h9[var1] * var8;
         this.X[var1] = this.X[var1] * var8;
         this.Q[var1] = this.Q[var1] * var8;
      }
   }

   private void k(long var1, int var3, AmbienceCatalog var4, class_638 var5, float var6, float var7, float var8) {
      byte var9 = 0;
      long var10 = 43015559447739L;
      long var12 = 1171855579L;
      char var14 = '캇';
      long var15 = 76219461953194L;
      AmbienceRegistry.ensureSourceClassInitialized();
      this.k(var15, var3, var5);
      float var17 = this.C[var3];
      float var18 = this.U[var3];
      if (!Float.isNaN(var17) && !(this.A[var3] < var17) && !(this.A[var3] >= var18)) {
         double var19 = this.A[var3] - var7;
         double var21 = var18;
         long var23 = 55859033003630L;
         if (55859033003630L == 0L) {
            boolean var10000 = false;
         } else {
            int var43 = var23 < 0L ? -1 : 1;
         }

         double var26;
         int var28 = (var26 = var19 - (var21 - 0.001)) == 0.0 ? 0 : (var26 < 0.0 ? -1 : 1);
         long var29 = 55859033003631L;
         if (55859033003631L == 0L) {
            boolean var44 = false;
         } else {
            int var45 = var29 < 0L ? -1 : 1;
         }

         if (var28 >= 0) {
            long var32 = 55859033003631L;
            if (55859033003631L == 0L) {
               boolean var46 = false;
            } else {
               int var47 = var32 < 0L ? -1 : 1;
            }

            if (var4.V) {
               this.x(var3, var4, (byte)var9, var10, var18);
               long var35 = 55859033003631L;
               if (55859033003631L == 0L) {
                  boolean var48 = false;
               } else {
                  int var49 = var35 < 0L ? -1 : 1;
               }
            } else {
               this.A[var3] = var18 + 0.02;
               this.X[var3] = Math.max(this.X[var3], 0.08F);
            }
         } else if (var19 < var17) {
            long var40 = 55859033003630L;
            if (55859033003630L == 0L) {
               boolean var50 = false;
            } else {
               int var51 = var40 < 0L ? -1 : 1;
            }

            this.A[var3] = var17 - 0.02;
            this.X[var3] = Math.min(this.X[var3], 0.0F);
         } else {
            this.H[var3] = this.H[var3] - var6;
            long var41 = 55859033003630L;
            if (55859033003630L == 0L) {
               boolean var52 = false;
            } else {
               int var53 = var41 < 0L ? -1 : 1;
            }

            this.F[var3] = this.F[var3] - var8;
            this.h9[var3] = 0.0F;
            this.Q[var3] = 0.0F;
            this.k(var15, var3, var5);
            if (!Float.isNaN(this.C[var3])) {
               double var42 = this.A[var3];
               long var37 = 55859033003630L;
               if (55859033003630L == 0L) {
                  boolean var54 = false;
               } else {
                  int var55 = var37 < 0L ? -1 : 1;
               }

               if (var42 >= this.C[var3] && this.A[var3] < this.U[var3]) {
                  this.i(var12, var3, (char)var14);
               }
            }
         }
      }
   }

   private void x(int var1, AmbienceCatalog var2, byte var3, long var4, float var6) {
      long var7 = 43015559447739L;
      long var9 = var7 ^ 42884769461698L;
      AmbienceRegistry.ensureSourceClassInitialized();
      boolean var11 = this.hT[var1];
      this.A[var1] = var6 + (var2.F == 2 ? 0.012 : this.r[var1] * 0.6);
      this.h9[var1] = 0.0F;
      this.X[var1] = 0.0F;
      this.Q[var1] = 0.0F;
      float var12 = var2.J + (var2.y - var2.J) * this.S(var9);
      long var13 = 43015559447739L;
      if (43015559447739L == 0L) {
         boolean var10000 = false;
      } else {
         int var19 = var13 < 0L ? -1 : 1;
      }

      if (var11 && var2.F == 2) {
         long var16 = 43015559447739L;
         if (43015559447739L == 0L) {
            boolean var20 = false;
         } else {
            int var21 = var16 < 0L ? -1 : 1;
         }

         this.j[var1] = 3;
         var12 += 2.0F;
      } else {
         this.j[var1] = 2;
      }

      this.h[var1] = var12;
      this.o[var1] = var12;
   }

   private void K(int var1, AmbienceCatalog var2, AmbienceUpdater.TypeV var3, float var4, long var5, float var7) {
      long var8 = 1171855579L;
      char var10 = '캇';
      long var11 = 103330804472957L;
      long var13 = 76219461953194L;
      long var15 = 136169599353L;
      AmbienceRegistry.ensureSourceClassInitialized();
      this.h[var1] = this.h[var1] - var4;
      if (this.h[var1] <= 0.0F) {
         this.i(var8, var1, (char)var10);
      } else {
         long var17 = 132774092424821L;
         if (132774092424821L == 0L) {
            boolean var10000 = false;
         } else {
            int var42 = var17 < 0L ? -1 : 1;
         }

         if (var2.F == 2) {
            this.Q(var11, var1, var7);
            byte var20 = this.j[var1];
            long var21 = 132774092424821L;
            if (132774092424821L == 0L) {
               boolean var43 = false;
            } else {
               int var44 = var21 < 0L ? -1 : 1;
            }

            if (var20 == 3) {
               double var23 = this.H[var1];
               float var25 = var3.Z * 0.12F * var3.o;
               long var26 = 132774092424820L;
               if (132774092424820L == 0L) {
                  boolean var45 = false;
               } else {
                  int var46 = var26 < 0L ? -1 : 1;
               }

               this.H[var1] = var23 + var25 * var4;
               long var29 = 132774092424820L;
               if (132774092424820L == 0L) {
                  boolean var47 = false;
               } else {
                  int var48 = var29 < 0L ? -1 : 1;
               }

               this.F[var1] = this.F[var1] + var3.W * 0.12F * var3.o * var4;
               this.k(var13, var1, var3.Y);
               if (!this.hT[var1]) {
                  long var31 = 132774092424821L;
                  if (132774092424821L == 0L) {
                     boolean var49 = false;
                  } else {
                     int var50 = var31 < 0L ? -1 : 1;
                  }

                  this.j[var1] = 2;
               } else {
                  this.A[var1] = this.U[var1] + 0.012;
               }
            } else if (var3.w) {
               for (int var39 = 0; var39 < var3.M; var39++) {
                  double var24 = this.H[var1] - var3.y[var39];
                  double var40 = this.F[var1] - var3.I[var39];
                  double var28 = this.A[var1] - var3.F[var39];
                  float var30 = (float)Math.sqrt(var3.C[var39] * var3.C[var39] + var3.L[var39] * var3.L[var39]);
                  double var41 = var24 * var24 + var40 * var40;
                  long var33 = 132774092424821L;
                  if (132774092424821L == 0L) {
                     boolean var51 = false;
                  } else {
                     int var52 = var33 < 0L ? -1 : 1;
                  }

                  if (var30 > 1.2F && var41 < 0.8) {
                     long var36 = 132774092424821L;
                     if (132774092424821L == 0L) {
                        boolean var53 = false;
                     } else {
                        int var54 = var36 < 0L ? -1 : 1;
                     }

                     if (Math.abs(var28) < 0.7) {
                        float var38 = (float)Math.sqrt(Math.max(var41, 1.0E-4));
                        this.j[var1] = 1;
                        this.hm[var1] = 0.0F;
                        this.e[var1] = Math.min(this.e[var1], this.p[var1] * 0.5F);
                        this.h9[var1] = (float)var24 / var38 * 0.9F + var3.C[var39] * 0.35F;
                        this.X[var1] = 1.2F + 0.8F * this.S(var15);
                        this.Q[var1] = (float)var40 / var38 * 0.9F + var3.L[var39] * 0.35F;
                        this.A[var1] = this.A[var1] + 0.05;
                        return;
                     }
                  }
               }
            }
         }
      }
   }

   private boolean L(long var1, int var3, AmbienceUpdater.TypeV var4) {
      long var5 = 1171855579L;
      char var7 = '캇';
      long var8 = 35529169765756L;
      AmbienceRegistry.ensureSourceClassInitialized();
      double var10 = (this.H[var3] - var4.s) / var4.g;
      double var12 = (this.A[var3] - var4.h) / var4.n;
      double var14 = (this.F[var3] - var4.G) / var4.g;
      double var16 = var10 * var10 + var12 * var12 + var14 * var14;
      if (var16 <= 1.0) {
         return true;
      } else {
         byte var18 = this.j[var3];
         long var19 = 5511123026950L;
         if (5511123026950L == 0L) {
            boolean var10000 = false;
         } else {
            int var33 = var19 < 0L ? -1 : 1;
         }

         if (var18 == 1 && var16 < 2.25) {
            double var22 = -0.97 / Math.sqrt(var16);
            double var24 = var4.s + var10 * var22 * var4.g;
            double var26 = var4.h + var12 * var22 * var4.n;
            double var28 = var4.G + var14 * var22 * var4.g;
            long var30 = 5511123026950L;
            if (5511123026950L == 0L) {
               boolean var34 = false;
            } else {
               int var35 = var30 < 0L ? -1 : 1;
            }

            if (this.j(var4.Y, var24, var26, var28, var8, AmbienceCatalog.e.get(this.k[var3]).V)) {
               this.H[var3] = var24;
               this.A[var3] = var26;
               this.F[var3] = var28;
               this.h7[var3] = Long.MIN_VALUE;
               return true;
            }
         }

         this.i(var5, var3, (char)var7);
         return false;
      }
   }

   private float J(int var1, long var2, AmbienceCatalog var4, AmbienceUpdater.TypeV var5, float var6, byte var7) {
      AmbienceRegistry.ensureSourceClassInitialized();
      float var8 = this.G[var1];
      if (this.j[var1] == 1) {
         float var9 = this.e[var1];
         long var10 = 303155264956L;
         if (303155264956L == 0L) {
            boolean var10000 = false;
         } else {
            int var26 = var10 < 0L ? -1 : 1;
         }

         float var14 = this.p[var1];
         var8 *= smoothstep(0.0F, var4.O, var9) * (1.0F - smoothstep(var14 - var4.P, var14, var9));
      } else {
         var8 *= Math.min(1.0F, this.h[var1] / Math.min(1.5F, this.o[var1] * 0.6F));
      }

      double var25 = this.H[var1] - var5.s;
      double var11 = this.A[var1] - var5.h;
      double var13 = this.F[var1] - var5.G;
      double var15 = var25 / var5.g;
      double var17 = var11 / var5.n;
      double var19 = var13 / var5.g;
      float var21 = (float)Math.sqrt(var15 * var15 + var17 * var17 + var19 * var19);
      float var22 = (float)Math.sqrt(var25 * var25 + var11 * var11 + var13 * var13);
      var8 *= (1.0F - smoothstep(0.72F, 1.0F, var21)) * smoothstep(0.7F, 1.7F, var22);
      this.y[var1] = this.y[var1] + (this.a(var1, var5) - this.y[var1]) * var6;
      return var8 * this.y[var1];
   }

   private float a(int var1, AmbienceUpdater.TypeV var2) {
      float var3 = 1.0F - var2.c * this.D[var1] * (1.0F - var2.N);
      return Math.clamp((var3 - this.h4[var1]) / 0.12F + 0.5F, 0.0F, 1.0F);
   }

   private void i(long var1, int var3, char var4) {
      long var5 = 76798727278215L;
      long var7 = var5 ^ 76719600762878L;
      this.j[var3] = 0;
      this.hb[var3] = 0.2F + 1.2F * this.S(var7);
   }

   private boolean X(char var1, int var2, int var3, char var4, AmbienceCatalog var5, AmbienceUpdater.TypeV var6) {
      long var7 = 14090566386836L;
      long var9 = var7 ^ 84307277348976L;
      long var11 = var7 ^ 80825089532478L;
      long var13 = var7 ^ 48932306502120L;
      long var15 = var7 ^ 14083254613485L;
      float var17 = this.S(var15) * 2.0F - 1.0F;
      float var18 = this.S(var15) * (float) (Math.PI * 2);
      float var19 = (float)Math.sqrt(1.0F - var17 * var17);
      float var20 = 2.6F / var6.g;
      float var21 = var20 + (1.0F - var20) * this.S(var15);
      AmbienceRegistry.ensureSourceClassInitialized();
      double var22 = var6.s + var19 * class_3532.method_15362(var18) * var21 * var6.g;
      double var24 = var6.h + var17 * var21 * var6.n;
      double var26 = var6.G + var19 * class_3532.method_15374(var18) * var21 * var6.g;
      double var28 = var22 - var6.s;
      double var30 = var24 - var6.h;
      double var32 = var26 - var6.G;
      if (!(var28 * var28 + var30 * var30 + var32 * var32 < 6.7599993F) && this.j(var6.Y, var22, var24, var26, var13, var5.V)) {
         this.H[var2] = var22;
         this.A[var2] = var24;
         this.F[var2] = var26;
         this.h9[var2] = 0.0F;
         this.X[var2] = 0.0F;
         this.Q[var2] = 0.0F;
         this.p[var2] = var5.w + (var5.r - var5.w) * this.S(var15);
         this.e[var2] = this.hA[var2] ? this.S(var15) * this.p[var2] * 0.8F : 0.0F;
         boolean var34 = this.hA[var2];
         int var35 = 0;
         if (var34) {
            this.G[var2] = 0.0F;
            this.hA[var2] = false;
         }

         float var36 = var5.F == 0 && this.S(var15) < 0.08F ? 1.5F : 1.0F;
         float var37 = this.S(var15);
         var35 = 215004980;
         this.r[var2] = (var5.j + (var5.k - var5.j) * var37 * var37) * var36;
         this.d[var2] = (float)Math.pow(this.S(var15), 1.35);
         this.T[var2] = this.S(var15);
         this.h4[var2] = this.S(var15);
         this.S[var2] = this.S(var15) * (float) (Math.PI * 2);
         this.hY[var2] = 0.5F + 0.9F * this.S(var15);
         this.t[var2] = this.S(var15) * (float) (Math.PI * 2);
         this.K[var2] = (var5.s + (var5.B - var5.s) * this.S(var15)) * (this.S(var15) < 0.5F ? -1.0F : 1.0F);
         if (var5.F == 2) {
            this.t(var9, var2);
            float var38 = this.S(var15) * 2.0F - 1.0F;
            float var39 = (this.S(var15) * 2.0F - 1.0F) * 0.45F;
            float var40 = this.S(var15) * 2.0F - 1.0F;
            float var41 = Math.abs(this.K[var2]) / Math.max(1.0E-4F, (float)Math.sqrt(var38 * var38 + var39 * var39 + var40 * var40));
            this.a[var2] = var38 * var41;
            this.c[var2] = var39 * var41;
            this.P[var2] = var40 * var41;
         }

         this.j[var2] = 1;
         this.hm[var2] = 0.0F;
         this.h7[var2] = Long.MIN_VALUE;
         this.k(var11, var2, var6.Y);
         this.y[var2] = this.a(var2, var6);
         return true;
      } else {
         return false;
      }
   }

   private boolean j(class_638 var1, double var2, double var4, double var6, long var8, boolean var10) {
      AmbienceRegistry.ensureSourceClassInitialized();
      class_2339 var11 = this.B.method_10102(var2, var4, var6);
      long var12 = 35529169765755L;
      if (35529169765755L == 0L) {
         boolean var10000 = false;
      } else {
         int var21 = var12 < 0L ? -1 : 1;
      }

      if (var10 && var4 < var1.method_8624(class_2903.field_13197, var11.method_10263(), var11.method_10260())) {
         return false;
      } else {
         class_2680 var15 = var1.method_8320(var11);
         boolean var16 = var15.method_26227().method_15769();
         long var17 = 35529169765755L;
         if (35529169765755L == 0L) {
            boolean var22 = false;
         } else {
            int var23 = var17 < 0L ? -1 : 1;
         }

         if (!var16) {
            return false;
         } else {
            class_265 var20 = var15.method_26220(var1, var11);
            return var20.method_1110()
               || var4 < var11.method_10264() + var20.method_1091(class_2351.field_11052)
               || var4 >= var11.method_10264() + var20.method_1105(class_2351.field_11052);
         }
      }
   }

   private void k(long var1, int var3, class_638 var4) {
      AmbienceRegistry.ensureSourceClassInitialized();
      class_2339 var5 = this.B.method_10102(this.H[var3], this.A[var3], this.F[var3]);
      long var6 = var5.method_10063();
      long var8 = 76219461953193L;
      if (76219461953193L == 0L) {
         boolean var10000 = false;
      } else {
         int var18 = var8 < 0L ? -1 : 1;
      }

      if (var6 != this.h7[var3]) {
         this.h7[var3] = var6;
         class_2680 var11 = var4.method_8320(var5);
         class_265 var12 = var11.method_26220(var4, var5);
         class_3610 var13 = var11.method_26227();
         if (!var12.method_1110()) {
            long var14 = 76219461953194L;
            if (76219461953194L == 0L) {
               boolean var19 = false;
            } else {
               int var20 = var14 < 0L ? -1 : 1;
            }

            this.C[var3] = (float)(var5.method_10264() + var12.method_1091(class_2351.field_11052));
            this.U[var3] = (float)(var5.method_10264() + var12.method_1105(class_2351.field_11052));
            this.hT[var3] = false;
         } else if (!var13.method_15769()) {
            long var17 = 76219461953194L;
            if (76219461953194L == 0L) {
               boolean var21 = false;
            } else {
               int var22 = var17 < 0L ? -1 : 1;
            }

            this.C[var3] = var5.method_10264();
            this.U[var3] = var5.method_10264() + var13.method_15763(var4, var5);
            this.hT[var3] = true;
         } else {
            this.C[var3] = Float.NaN;
            this.U[var3] = Float.NaN;
            this.hT[var3] = false;
         }

         this.D[var3] = var4.method_8314(class_1944.field_9284, var5) / 15.0F;
         this.hC[var3] = var4.method_8314(class_1944.field_9282, var5) / 15.0F;
      }
   }

   private void t(long var1, int var3) {
      long var4 = 136169599353L;
      float var6 = this.S(var4);
      float var7 = this.S(var4) * (float) (Math.PI * 2);
      float var8 = this.S(var4) * (float) (Math.PI * 2);
      float var9 = (float)Math.sqrt(1.0F - var6);
      float var10 = (float)Math.sqrt(var6);
      this.s[var3] = var9 * class_3532.method_15374(var7);
      this.z[var3] = var9 * class_3532.method_15362(var7);
      this.L[var3] = var10 * class_3532.method_15374(var8);
      this.x[var3] = var10 * class_3532.method_15362(var8);
   }

   private void Y(int var1, float var2) {
      float var3 = this.s[var1];
      float var4 = this.z[var1];
      float var5 = this.L[var1];
      float var6 = this.x[var1];
      float var7 = this.a[var1] * 0.5F * var2;
      float var8 = this.c[var1] * 0.5F * var2;
      float var9 = this.P[var1] * 0.5F * var2;
      this.Y(
         var1,
         var3 + var6 * var7 + (var8 * var5 - var9 * var4),
         var4 + var6 * var8 + (var9 * var3 - var7 * var5),
         var5 + var6 * var9 + (var7 * var4 - var8 * var3),
         var6 - (var7 * var3 + var8 * var4 + var9 * var5)
      );
   }

   private void Q(long var1, int var3, float var4) {
      float var5 = this.T[var3] * (float) Math.PI;
      float var6 = 0.70710677F;
      float var7 = class_3532.method_15362(var5);
      float var8 = class_3532.method_15374(var5);
      AmbienceRegistry.ensureSourceClassInitialized();
      float var9 = -var6 * var7;
      float var10 = var6 * var8;
      float var11 = var6 * var8;
      float var12 = var6 * var7;
      float var13 = var9 * this.s[var3] + var10 * this.z[var3] + var11 * this.L[var3] + var12 * this.x[var3];
      if (var13 < 0.0F) {
         var9 = -var9;
         var10 = -var10;
         var11 = -var11;
         var12 = -var12;
      }

      if (!(Math.abs(var13) > 0.999999F)) {
         this.Y(
            var3,
            this.s[var3] + (var9 - this.s[var3]) * var4,
            this.z[var3] + (var10 - this.z[var3]) * var4,
            this.L[var3] + (var11 - this.L[var3]) * var4,
            this.x[var3] + (var12 - this.x[var3]) * var4
         );
      }
   }

   private void Y(int var1, float var2, float var3, float var4, float var5) {
      float var6 = (float)(1.0 / Math.sqrt(var2 * var2 + var3 * var3 + var4 * var4 + var5 * var5));
      this.s[var1] = var2 * var6;
      this.z[var1] = var3 * var6;
      this.L[var1] = var4 * var6;
      this.x[var1] = var5 * var6;
   }

   private float S(long var1) {
      long var3 = this.J;
      var3 ^= var3 << 13;
      var3 ^= var3 >>> 7;
      var3 ^= var3 << 17;
      this.J = var3;
      return (float)(var3 >>> 40) * 5.9604645E-8F;
   }

   static float smoothstep(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 6479) & 32767;
      if (db[var3] == null) {
         db[var3] = cb[var3] ^ var1;
      }

      return db[var3];
   }

   static final class TypeV {
      static final int T;
      class_638 Y;
      double s;
      double h;
      double G;
      float g;
      float n;
      float P;
      float o;
      float z;
      float c;
      float N;
      float Z;
      float W;
      boolean w;
      float d;
      double V;
      double l;
      double H;
      int M;
      final double[] y = new double[8];
      final double[] F = new double[8];
      final double[] I = new double[8];
      final float[] t = new float[8];
      final float[] C = new float[8];
      final float[] r = new float[8];
      final float[] L = new float[8];

      static {
         long var0 = 126790434910278L;
         long var2 = 5010579180603404366L ^ var0;
         T = (int)var2;
      }
   }
}
