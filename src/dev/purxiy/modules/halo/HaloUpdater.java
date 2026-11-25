package dev.purxiy.modules.halo;

import org.joml.Vector3f;

final class HaloUpdater {
   static final float Y = 4.0F;
   private static final float s = 9.0F;
   private float S = 9.0F;
   private static final float R = 1.0F;
   private static final float D = 0.55F;
   private float E;
   private float y = -1.0F;
   private float g;
   private float q;
   private float p;
   private float K;
   private final Vector3f r = new Vector3f(0.0F, 1.0F, 0.0F);
   private final Vector3f u = new Vector3f();
   private final Vector3f V = new Vector3f();
   private boolean A;
   private float f;
   private float z;
   private float Q;
   private float v;
   private boolean W;
   private int h;
   private float G;
   private double L;
   private double X;
   private double b;
   private boolean J;
   private float O;
   private float T;
   private final Vector3f H = new Vector3f();
   private final Vector3f t = new Vector3f();
   private final Vector3f d = new Vector3f();

   float time() {
      return this.E;
   }

   float presence() {
      return this.g;
   }

   boolean visible() {
      return this.g > 0.0F;
   }

   void update(float var1, boolean var2, HaloMeasure var3, double var4, double var6, double var8, float var10, boolean var11) {
      long var12 = 82663024230931L;
      long var14 = (var12 ^ 129027801444789L) >>> 8;
      int var16 = (int)((var12 ^ 129027801444789L) << 56 >>> 56);
      long var17 = var12 ^ 81193855626493L;
      float var19 = Math.clamp(var1, 0.0F, 0.1F);
      this.E = this.y >= 0.0F ? this.y : this.E + var19;
      boolean var20 = var3 != null && var3.x;
      float var21 = this.g;
      if (var2 && !var20) {
         this.g = Math.min(1.0F, this.g + var19 / 1.0F);
      } else if (!var20) {
         this.g = Math.max(0.0F, this.g - var19 / 0.55F);
      }

      if (var21 < 1.0F && this.g >= 1.0F) {
         this.q = 1.0F;
      }

      this.q = Math.max(0.0F, this.q - var19 / 0.9F);
      this.p = Math.max(0.0F, this.p - var19 / 1.1F);
      this.K = this.K + var19 * (0.1F + 5.0F * this.p * this.p);
      if (var20) {
         this.G += var19;
         if (this.G > 1.8F) {
            this.g = 0.0F;
         }
      } else {
         this.G = 0.0F;
      }

      if (var3 == null) {
         this.A = false;
         this.J = false;
      } else {
         this.t(var19, var4, var6, var8, var17);
         if (var3.Z && !this.W) {
            float var22 = (this.h++ & 1) == 0 ? 1.0F : -1.0F;
            this.Q += 2.0F * var22;
            this.v = this.v + 1.4F * (this.h % 3 == 0 ? -var22 : var22);
         }

         this.W = var3.Z;
         int var32 = Math.max(1, (int)Math.ceil(var19 / 0.008333334F));
         float var23 = var19 / var32;
         float var24 = 16.3F;
         float var25 = 0.18F;

         for (int var26 = 0; var26 < var32; var26++) {
            this.Q = this.Q + (-var24 * var24 * this.f - 2.0F * var25 * var24 * this.Q) * var23;
            this.v = this.v + (-var24 * var24 * this.z - 2.0F * var25 * var24 * this.v) * var23;
            this.f = this.f + this.Q * var23;
            this.z = this.z + this.v * var23;
         }

         this.G(var3, var14, (byte)var16, var10, var11);
         if (!this.A) {
            this.r.set(this.V);
            this.u.zero();
            this.A = true;
         } else {
            float var33 = 11.0F;
            float var27 = 0.55F;

            for (int var28 = 0; var28 < var32; var28++) {
               float var29 = var33 * var33 * (this.V.x - this.r.x) - 2.0F * var27 * var33 * this.u.x;
               float var30 = var33 * var33 * (this.V.y - this.r.y) - 2.0F * var27 * var33 * this.u.y;
               float var31 = var33 * var33 * (this.V.z - this.r.z) - 2.0F * var27 * var33 * this.u.z;
               this.u.add(var29 * var23, var30 * var23, var31 * var23);
               this.r.add(this.u.x * var23, this.u.y * var23, this.u.z * var23);
            }

            if (this.r.lengthSquared() < 1.0E-6F) {
               this.r.set(this.V);
            }

            this.r.normalize();
         }
      }
   }

   private void G(HaloMeasure var1, long var2, byte var4, float var5, boolean var6) {
      float var7 = 0.0F;
      HaloDesign.ensureSourceClassInitialized();
      float var8 = 1.0F;
      float var9 = 0.0F;
      byte var10 = -90;
      if (var1.n || var1.W) {
         var7 = var1.J;
         var8 = var1.j;
         var9 = var1.o;
      }

      float var11 = var5 * (!var1.l && !var1.c ? 1.0F : 0.35F);
      Vector3f var12 = this.V.set(var7 + (var1.f - var7) * var11, var8 + (var1.a - var8) * var11, var9 + (var1.w - var9) * var11);
      var10 = -90;
      long var13 = 268298909554L;
      int var15 = var13 < 0L ? -1 : 1;
      if (var12.lengthSquared() < 1.0E-4F) {
         var12.set(var7, var8, var9);
      }

      var12.normalize();
      Vector3f var15x = this.H.set(var1.K, var1.M, var1.r);
      long var16 = 268298909554L;
      int var19 = var16 < 0L ? -1 : 1;
      var15x.sub(var12.x * var15x.dot(var12), var12.y * var15x.dot(var12), var12.z * var15x.dot(var12));
      if (var15x.lengthSquared() < 1.0E-4F) {
         var15x.set(1.0F, 0.0F, 0.0F);
      }

      var15x.normalize();
      Vector3f var19x = var12.cross(var15x, this.t).normalize();
      float var20 = (float)Math.sqrt(this.O * this.O + this.T * this.T);
      float var21 = Math.min(var20 * 0.035F, 0.14F);
      long var22 = 268298909555L;
      int var25 = var22 < 0L ? -1 : 1;
      float var25x = var20 > 0.001F ? -this.O / var20 : 0.0F;
      long var26 = 268298909555L;
      int var26x = var26 < 0L ? -1 : 1;
      float var28x = var20 > 0.001F ? -this.T / var20 : 0.0F;
      float var29 = var6 ? 0.022F * (float)Math.sin(this.E * 0.83F) : 0.0F;
      float var30 = var6 ? 0.018F * (float)Math.sin(this.E * 0.61F + 1.7F) : 0.0F;
      float var31 = this.f + var29;
      float var32 = this.z + var30;
      var12.add(var15x.x * var31 + var19x.x * var32 + var25x * var21, var15x.y * var31 + var19x.y * var32, var15x.z * var31 + var19x.z * var32 + var28x * var21)
         .normalize();
   }

   private void t(float var1, double var2, double var4, double var6, long var8) {
      HaloDesign.ensureSourceClassInitialized();
      long var10 = 3255968870126L;
      int var13 = var10 < 0L ? -1 : 1;
      long var13x = 3255968870125L;
      int var16 = var13x < 0L ? -1 : 1;
      if (this.J && !(var1 <= 0.0F)) {
         double var16x = var2 - this.L;
         double var18 = var6 - this.b;
         long var20 = 3255968870125L;
         int var20x = var20 < 0L ? -1 : 1;
         this.L = var2;
         this.X = var4;
         long var22x = 3255968870126L;
         this.b = var6;
         if (var16x * var16x + var18 * var18 > 4.0) {
            this.O = this.T = 0.0F;
         } else {
            float var24x = 1.0F - (float)Math.exp(-var1 * 6.0F);
            this.O = this.O + ((float)(var16x / var1) - this.O) * var24x;
            this.T = this.T + ((float)(var18 / var1) - this.T) * var24x;
         }
      } else {
         this.J = true;
         this.L = var2;
         this.X = var4;
         this.b = var6;
         this.O = this.T = 0.0F;
      }
   }

   float place(HaloMeasure var1, HaloDesign var2, float var3, float var4, boolean var5, HaloBuilder.HaloBatchState var6, HaloBuilder.HaloSegments var7) {
      Vector3f var8 = this.d.set(this.r);
      Vector3f var9 = this.H.set(var1.K, var1.M, var1.r);
      var9.sub(var8.x * var9.dot(var8), var8.y * var9.dot(var8), var8.z * var9.dot(var8));
      if (var9.lengthSquared() < 1.0E-4F) {
         var9.set(Math.abs(var8.x) < 0.9F ? 1.0F : 0.0F, Math.abs(var8.x) < 0.9F ? 0.0F : 1.0F, 0.0F);
         var9.sub(var8.x * var9.dot(var8), var8.y * var9.dot(var8), var8.z * var9.dot(var8));
      }

      var9.normalize();
      Vector3f var10 = var8.cross(var9, this.t).normalize();
      float var11 = (float)Math.toRadians(this.S);
      float var12 = (float)Math.cos(var11);
      float var13 = (float)Math.sin(var11);
      float var14 = var8.x * var12 - var9.x * var13;
      float var15 = var8.y * var12 - var9.y * var13;
      float var16 = var8.z * var12 - var9.z * var13;
      var9.set(var9.x * var12 + var8.x * var13, var9.y * var12 + var8.y * var13, var9.z * var12 + var8.z * var13);
      var8.set(var14, var15, var16);
      if (var2.Q != 0.0F) {
         var11 = (float)Math.toRadians(var2.Q);
         var12 = (float)Math.cos(var11);
         var13 = (float)Math.sin(var11);
         var14 = var8.x * var12 + var10.x * var13;
         var15 = var8.y * var12 + var10.y * var13;
         var16 = var8.z * var12 + var10.z * var13;
         var10.set(var10.x * var12 - var8.x * var13, var10.y * var12 - var8.y * var13, var10.z * var12 - var8.z * var13);
         var8.set(var14, var15, var16);
      }

      var7.u = var3 * Math.clamp(var1.s * 1.12F / 5.2F, 1.0F, 1.45F);
      var11 = var5 ? 0.45F * (0.5F + 0.5F * (float)Math.sin(this.E * 1.55F)) + 0.1F * (0.5F + 0.5F * (float)Math.sin(this.E * 2.9F + 1.3F)) : 0.0F;
      var12 = 4.0F * var4 + (0.5F + var2.n) * var7.u + var11;
      var13 = var1.reach(var8.x, var8.y, var8.z) + var12 * var1.y;
      var14 = var1.N + var8.x * var13;
      float var17 = var1.g + var8.y * var13;
      var16 = var1.p + var8.z * var13;
      float var18 = 1.0F;
      if (this.G > 0.0F) {
         float var19 = Math.min(1.0F, this.G / 1.6F);
         var17 += (1.0F - (1.0F - var19) * (1.0F - var19)) * 15.0F * var1.y;
         var18 = 1.0F - HaloBuilder.smooth(0.7F, 1.7F, this.G);
      }

      var6.U = var14;
      var6.c = var17;
      var6.y = var16;
      var6.basis(var9.x, var9.y, var9.z, var8.x, var8.y, var8.z, var10.x, var10.y, var10.z);
      var6.i = var1.y;
      var6.a = this.K;
      var7.J = this.E;
      var7.T = this.g;
      var7.N = this.q;
      var7.v = this.p;
      return var18;
   }

   void leanBack(float var1) {
      this.S = var1;
   }

   void flourish() {
      this.p = 1.0F;
   }

   void shiver() {
      this.Q += 2.0F;
      this.v--;
   }

   void appear() {
      this.g = 0.0F;
   }

   void freeze(float var1) {
      this.y = var1;
      if (var1 >= 0.0F) {
         this.E = var1;
      }
   }
}
