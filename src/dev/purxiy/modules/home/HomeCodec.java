package dev.purxiy.modules.home;

import java.util.SplittableRandom;

final class HomeCodec {
   static final int B = 0;
   static final int J = 1;
   static final int a = 2;
   static final int i = 3;
   private static final int e = 700;
   private final float[] L = new float[700];
   private final float[] h = new float[700];
   private final float[] f = new float[700];
   private final float[] j = new float[700];
   private final float[] g = new float[700];
   private final float[] r = new float[700];
   private final float[] E = new float[700];
   private final float[] n = new float[700];
   private final float[] C = new float[700];
   private final float[] t = new float[700];
   private final float[] D = new float[700];
   private final float[] u = new float[700];
   private final int[] T = new int[700];
   private final int[] m = new int[700];
   private final int[] Z = new int[700];
   private int K;
   private final SplittableRandom I = new SplittableRandom(24301L);
   private float R;
   private float A;

   int count() {
      return this.K;
   }

   void clear() {
      this.K = 0;
   }

   private int U(int var1, float var2, long var3, float var5, float var6, float var7, float var8) {
      HomeGuiBridge.ensureSourceClassInitialized();
      long var9 = 119229576952298L;
      int var12 = var9 < 0L ? -1 : 1;
      long var12x = 119229576952297L;
      int var15 = var12x < 0L ? -1 : 1;
      if (this.K >= 700) {
         return -1;
      } else {
         var15 = this.K++;
         this.T[var15] = var1;
         this.L[var15] = var2;
         this.h[var15] = var5;
         this.f[var15] = var6;
         this.j[var15] = 0.0F;
         this.g[var15] = 0.0F;
         this.r[var15] = 0.0F;
         this.E[var15] = 0.0F;
         this.n[var15] = var7;
         this.C[var15] = var8;
         this.t[var15] = this.I.nextFloat() * (float) (Math.PI * 2);
         this.D[var15] = (this.I.nextFloat() - 0.5F) * 2.4F;
         this.u[var15] = this.I.nextFloat() * (float) (Math.PI * 2);
         this.m[var15] = this.I.nextInt(12);
         this.Z[var15] = 16777215;
         return var15;
      }
   }

   void boneMeal(float var1, float var2, float var3, int var4, float var5) {
      long var6 = 120809088150458L;
      long var8 = var6 ^ 1719100265040L;

      for (int var10 = 0; var10 < var4; var10++) {
         int var11 = this.U(
            0,
            var1 + (this.I.nextFloat() - 0.5F) * var5,
            var8,
            var2 + this.I.nextFloat() * var5 * 0.8F,
            var3 + (this.I.nextFloat() - 0.5F) * var5,
            0.7F + this.I.nextFloat() * 0.6F,
            0.09F + this.I.nextFloat() * 0.06F
         );
         if (var11 >= 0) {
            this.g[var11] = 0.25F + this.I.nextFloat() * 0.35F;
            this.j[var11] = (this.I.nextFloat() - 0.5F) * 0.2F;
            this.r[var11] = (this.I.nextFloat() - 0.5F) * 0.2F;
         }
      }
   }

   void scatter(HomeSupport.HomeGeometry var1, int var2, int var3) {
      long var4 = 110327064429811L;
      int var6 = (int)((var4 ^ 47058953127166L) >>> 56);
      long var7 = (var4 ^ 47058953127166L) << 8 >>> 8;

      for (int var9 = 0; var9 < var2; var9++) {
         this.U(var1, var3, (byte)var6, 1.4F, var7);
      }
   }

   private void U(HomeSupport.HomeGeometry var1, int var2, byte var3, float var4, long var5) {
      long var7 = 86428722872333L;
      long var9 = var7 ^ 38392943868391L;
      HomeGuiBridge.ensureSourceClassInitialized();
      float var11 = var1.h;
      float var12 = var1.q + (this.I.nextFloat() - 0.5F) * var11 * 2.0F;
      float var13 = var1.G + (this.I.nextFloat() - 0.5F) * var11 * 2.0F;
      float var14 = var1.W - 0.5F - this.I.nextFloat() * 1.2F;
      boolean var15 = var1.cherry();
      int var16 = var15 ? 1 : 2;
      long var17 = 86428722872333L;
      int var20 = var17 < 0L ? -1 : 1;
      float var20x = 6.0F + this.I.nextFloat() * 4.0F;
      float var21 = var15 ? 0.085F : 0.1F;
      int var25 = this.U(var16, var12, var9, var14, var13, var20x, var21);
      if (var25 >= 0) {
         this.g[var25] = -0.35F - this.I.nextFloat() * 0.25F;
         this.j[var25] = (this.I.nextFloat() - 0.5F) * var4;
         this.r[var25] = (this.I.nextFloat() - 0.5F) * var4;
         this.Z[var25] = var15 ? 16777215 : var2;
      }
   }

   void update(float var1, HomeWorldState var2, HomeSupport var3, float var4, float var5, float var6) {
      long var7 = 13219045474758L;
      int var9 = (int)((var7 ^ 73248751600075L) >>> 56);
      long var10 = (var7 ^ 73248751600075L) << 8 >>> 8;
      long var12 = var7 ^ 106770375834389L;
      long var14 = var7 ^ 106057776379948L;
      if (var3 != null && var4 > 0.95F && !var3.m.isEmpty()) {
         this.R += var1 * 0.9F * var6;
         this.A += var1 * 0.35F * var6;
         int var16 = 0;

         while ((this.R >= 1.0F || this.A >= 1.0F) && var16++ < 8) {
            HomeSupport.HomeGeometry var17 = var3.m.get(this.I.nextInt(var3.m.size()));
            boolean var18 = var17.cherry();
            if (!var18 || !(this.R >= 1.0F)) {
               if (var18 || !(this.A >= 1.0F) || !(var17.h > 0.0F)) {
                  this.R = Math.min(this.R, 1.5F);
                  this.A = Math.min(this.A, 1.5F);
                  break;
               }

               this.A--;
               this.U(
                  var17,
                  HomeTerrain.wood(var17.x).equals("birch") ? 8431445 : (HomeTerrain.wood(var17.x).equals("spruce") ? 6396257 : 7842607),
                  (byte)var9,
                  0.4F,
                  var10
               );
            } else {
               this.R--;
               this.U(var17, 16777215, (byte)var9, 0.4F, var10);
            }
         }
      }

      int var25 = 0;

      for (int var26 = 0; var26 < this.K; var26++) {
         if (this.T[var26] == 3) {
            var25++;
         }
      }

      int var27 = (int)(70.0F * var5);

      for (int var28 = var25; var28 < var27 && var28 < var25 + 3; var28++) {
         float var19 = this.I.nextFloat() * (float) (Math.PI * 2);
         float var20 = 5.0F + this.I.nextFloat() * 26.0F;
         float var21 = (float)Math.cos(var19) * var20;
         float var22 = (float)Math.sin(var19) * var20;
         int var23 = Math.max(var2.heightAt((int)Math.floor(var21), (int)Math.floor(var22)), 63);
         int var24 = this.U(3, var21, var14, var23 + 1.2F + this.I.nextFloat() * 2.2F, var22, 7.0F + this.I.nextFloat() * 7.0F, 0.07F);
         if (var24 >= 0) {
            this.j[var24] = (this.I.nextFloat() - 0.5F) * 0.3F;
            this.r[var24] = (this.I.nextFloat() - 0.5F) * 0.3F;
         }
      }

      for (int var29 = 0; var29 < this.K; var29++) {
         this.E[var29] = this.E[var29] + var1;
         float var31 = this.E[var29];
         switch (this.T[var29]) {
            case 0:
               this.g[var29] = this.g[var29] * (float)Math.pow(0.4, var1);
               break;
            case 1:
            case 2:
               float var32 = (float)Math.sin(var31 * 1.9F + this.u[var29]);
               this.j[var29] = this.j[var29] + (var32 * 0.6F + 0.25F * var6 - this.j[var29]) * var1 * 1.5F;
               this.r[var29] = this.r[var29] + ((float)Math.cos(var31 * 1.4F + this.u[var29]) * 0.5F - this.r[var29]) * var1 * 1.5F;
               this.g[var29] = this.g[var29] + (-0.42F - this.g[var29]) * var1 * 2.0F;
               this.t[var29] = this.t[var29] + this.D[var29] * var1;
               int var33 = var2.heightAt((int)Math.floor(this.L[var29]), (int)Math.floor(this.f[var29]));
               float var34 = Math.max(var33 + 1.02F, 63.895F);
               if (this.h[var29] <= var34) {
                  this.h[var29] = var34;
                  this.j[var29] = this.g[var29] = this.r[var29] = 0.0F;
                  this.n[var29] = Math.min(this.n[var29], var31 + 1.2F);
               }
               break;
            case 3:
               this.j[var29] = this.j[var29] + ((float)Math.sin(var31 * 0.7F + this.u[var29]) * 0.35F - this.j[var29]) * var1;
               this.r[var29] = this.r[var29] + ((float)Math.cos(var31 * 0.6F + this.u[var29] * 1.7F) * 0.35F - this.r[var29]) * var1;
               this.g[var29] = (float)Math.sin(var31 * 0.9F + this.u[var29]) * 0.12F;
               if (var5 < 0.2F) {
                  this.n[var29] = Math.min(this.n[var29], var31 + 1.0F);
               }
         }

         this.L[var29] = this.L[var29] + this.j[var29] * var1;
         this.h[var29] = this.h[var29] + this.g[var29] * var1;
         this.f[var29] = this.f[var29] + this.r[var29] * var1;
      }

      int var30 = 0;

      while (var30 < this.K) {
         if (this.E[var30] >= this.n[var30]) {
            this.V(var30, var12);
         } else {
            var30++;
         }
      }
   }

   private void V(int var1, long var2) {
      HomeGuiBridge.ensureSourceClassInitialized();
      int var4 = --this.K;
      long var5 = 119978415127250L;
      int var7 = var5 < 0L ? -1 : 1;
      if (var1 != var4) {
         this.L[var1] = this.L[var4];
         this.h[var1] = this.h[var4];
         this.f[var1] = this.f[var4];
         this.j[var1] = this.j[var4];
         this.g[var1] = this.g[var4];
         this.r[var1] = this.r[var4];
         this.E[var1] = this.E[var4];
         this.n[var1] = this.n[var4];
         this.C[var1] = this.C[var4];
         this.t[var1] = this.t[var4];
         this.D[var1] = this.D[var4];
         this.u[var1] = this.u[var4];
         this.T[var1] = this.T[var4];
         this.m[var1] = this.m[var4];
         this.Z[var1] = this.Z[var4];
      }
   }

   void write(HomeCurve var1, HomeBlocks var2, float var3) {
      float[][] var4 = new float[][]{{-1.0F, 1.0F}, {-1.0F, -1.0F}, {1.0F, -1.0F}, {1.0F, 1.0F}};

      for (int var5 = 0; var5 < this.K; var5++) {
         float var6 = this.E[var5];
         float var7 = this.n[var5];
         float var8 = Math.min(1.0F, var6 / 0.25F) * Math.min(1.0F, (var7 - var6) / 0.8F);
         float var9 = this.C[var5];
         int var10 = this.Z[var5];
         float[] var11;
         switch (this.T[var5]) {
            case 0:
               var11 = var2.particle("glint");
               var8 = Math.min(1.0F, var6 / 0.1F) * Math.min(1.0F, (var7 - var6) / 0.4F);
               break;
            case 1:
               var11 = var2.particle("cherry_" + this.m[var5]);
               break;
            case 2:
               var11 = var2.particle("leaf_" + this.m[var5]);
               break;
            default:
               var11 = var2.particle("firefly");
               float var12 = 0.5F + 0.5F * (float)Math.sin(var3 * (1.1F + this.u[var5] * 0.3F) + this.u[var5] * 3.0F);
               var8 *= 0.25F + 0.75F * var12 * var12;
               var9 = -var9;
         }

         int var15 = Math.round(Math.max(0.0F, Math.min(1.0F, var8)) * 255.0F);
         if (var15 > 2) {
            float[][] var13 = new float[][]{{var11[0], var11[1]}, {var11[0], var11[3]}, {var11[2], var11[3]}, {var11[2], var11[1]}};

            for (int var14 = 0; var14 < 4; var14++) {
               var1.vertex(
                  this.L[var5], this.h[var5], this.f[var5], var13[var14][0], var13[var14][1], var10, var15, var4[var14][0], var4[var14][1], var9, this.t[var5]
               );
            }
         }
      }
   }
}
