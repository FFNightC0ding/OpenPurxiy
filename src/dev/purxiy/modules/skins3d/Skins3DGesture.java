package dev.purxiy.modules.skins3d;

public final class Skins3DGesture implements Skins3DApi {
   private static final Skins3DAnimation.Data Y = new Skins3DAnimation.Data(
      0.6F, 1.0F, 0.08F, 0.35F, 0.35F, 0.5F, 0.12F, 0.22F, 0.5F, 0.45F, 0.0F, 0.05F, 1.3F, 1.6F, 0.07F, 0.0F, 0.0F
   );
   private static final Skins3DAnimation.Data X = new Skins3DAnimation.Data(
      0.95F, 1.6F, 0.15F, 0.4F, 0.45F, 0.95F, 0.2F, 0.35F, 0.9F, 0.4F, 0.22F, 0.08F, 1.8F, 2.0F, 0.06F, 0.0F, 0.0F
   );
   private static final Skins3DAnimation.Data q = new Skins3DAnimation.Data(
      0.6F, 1.35F, 0.4F, 0.2F, 0.6F, 0.1F, 0.95F, 0.12F, 0.95F, 0.1F, 0.22F, 0.02F, 0.9F, 0.8F, 0.04F, 0.0F, 0.0F
   );
   private static final int m = 1;
   private static final int A;
   private static final float c = 7.1F;
   private final Skins3DCurve N = new Skins3DCurve();
   private final Skins3DCurve v = new Skins3DCurve();

   @Override
   public float cadence(float var1) {
      return Skins3DAnimation.mix(1.0F, 1.2F, var1);
   }

   @Override
   public int habits() {
      return 2;
   }

   @Override
   public float habitSeconds(int var1) {
      return var1 == 1 ? 2.4F : 2.0F;
   }

   @Override
   public void move(Skins3DCurve var1, Skins3DCurve var2, Skins3DGroundPose var3) {
      long var4 = 96194965299618L;
      long var6 = var4 ^ 85123936523044L;
      long var8 = var4 ^ 99730902223483L;
      J(var1, var3, var6);
      if (var3.W > 0.0F) {
         this.N.set(var2);
         Skins3DAnimation.stride(this.N, var3, Skins3DAnimation.Data.mix(Y, q, var3.U), X);
         var1.lerp(this.N, var3.W);
      }

      if (var3.f > 0.0F) {
         this.v.set(var1);
         K(this.v, var8, var3);
         var1.lerp(this.v, var3.f);
      }

      float var10 = var3.c;
      if (var10 > 0.0F) {
         var1.H += 0.9F * var10;
         var1.M += 0.9F * var10;
         var1.d -= 0.4F * var10;
         var1.q -= 0.4F * var10;
      }

      Skins3DAnimation.busyArms(var1, var2, var3);
   }

   private static void J(Skins3DCurve var0, Skins3DGroundPose var1, long var2) {
      SkinPreset.ensureSourceClassInitialized();
      float var4 = var1.U;
      float var5 = 0.5F + 0.5F * (float)Math.sin(var1.V * 2.0F * Math.PI * 1.3);
      long var6 = 28683255673478L;
      int var9 = var6 < 0L ? -1 : 1;
      if (var1.h) {
         var0.s = Skins3DAnimation.mix(-0.05F, -0.95F, var4);
         var0.f = 0.0F;
         var0.e = Skins3DAnimation.mix(0.2F + 0.04F * var5, 0.12F, var4);
         var0.g = Skins3DAnimation.mix(0.15F, 1.0F, var4);
      }

      if (var1.r) {
         var0.n = Skins3DAnimation.mix(-0.05F, -0.95F, var4);
         var0.D = 0.0F;
         var0.X = -Skins3DAnimation.mix(0.2F + 0.04F * var5, 0.12F, var4);
         var0.Y = Skins3DAnimation.mix(0.15F, 1.0F, var4);
      }

      var0.H = var0.H + Skins3DAnimation.mix(0.08F + 0.16F * var5, 0.7F, var4);
      var0.M = var0.M + Skins3DAnimation.mix(0.08F + 0.16F * var5, 0.7F, var4);
      var0.d -= 0.35F * var4;
      var0.q -= 0.35F * var4;
      var0.y += 0.22F * var4;
      long var9x = 28683255673478L;
      int var16 = var9x < 0L ? -1 : 1;
      if (var1.k == 1) {
         long var11 = 28683255673478L;
         int var13 = var11 < 0L ? -1 : 1;
         if (var1.h) {
            float var17 = var1.R;
            float var14 = Skins3DAnimation.hold(var17, 0.18F, 0.2F);
            float var15 = (float)Math.sin(var17 * Math.PI * 2.0 * 5.0);
            var0.s = Skins3DAnimation.mix(var0.s, -2.55F, var14);
            var0.e = Skins3DAnimation.mix(var0.e, 0.55F + 0.32F * var15, var14);
            var0.g = Skins3DAnimation.mix(var0.g, 0.35F + 0.15F * var15, var14);
            return;
         }
      }

      if (var1.k == 2) {
         float var16x = var1.R;
         float var12 = Skins3DAnimation.hold(var16x, 0.15F, 0.2F);
         float var13 = (float)Math.sin(var16x * Math.PI * 2.0 * 4.0);
         if (var1.h) {
            var0.s = Skins3DAnimation.mix(var0.s, -0.55F - 0.35F * var13, var12);
            var0.e = Skins3DAnimation.mix(var0.e, 0.35F, var12);
            var0.g = Skins3DAnimation.mix(var0.g, 1.7F, var12);
         }

         if (var1.r) {
            var0.n = Skins3DAnimation.mix(var0.n, -0.55F + 0.35F * var13, var12);
            var0.X = Skins3DAnimation.mix(var0.X, -0.35F, var12);
            var0.Y = Skins3DAnimation.mix(var0.Y, 1.7F, var12);
         }

         var0.h = var0.h + 0.8F * var12 * Math.abs(var13);
      }
   }

   private static void K(Skins3DCurve var0, long var1, Skins3DGroundPose var3) {
      SkinPreset.ensureSourceClassInitialized();
      float var4 = var3.d;
      if (var3.h) {
         var0.s = -2.7F;
         var0.f = 0.0F;
         var0.e = 0.6F;
         var0.g = 0.2F;
      }

      if (var3.r) {
         var0.n = -2.7F;
         var0.D = 0.0F;
         var0.X = -0.6F;
         var0.Y = 0.2F;
      }

      var0.d = -0.35F * var4;
      var0.q = -0.35F * var4;
      var0.H = 1.3F * var4 + 0.2F;
      var0.M = 1.1F * var4 + 0.2F;
      var0.b = 0.08F;
      var0.o = -0.08F;
   }

   @Override
   public void finish(Skins3DRestPose var1, Skins3DGroundPose var2) {
      float var3 = (float)Math.pow(Math.abs(Math.cos(var2.N)), 4.0) * var2.ground();
      float var4 = 0.04F * var3 - 0.02F * (1.0F - var3) * var2.ground() + 0.12F * var2.c - 0.04F * var2.f * var2.d;
      float var5 = 1.0F - var4;
      float var6 = 1.0F + var4 * 0.6F;
      var1.z[0] = var1.z[0] * var5;
      var1.N[0] = var1.N[0] * var6;
      var1.x[0] = var1.x[0] * var6;
      var1.b[0] = var1.b[0] + 7.1F * (1.0F - var5);
   }

   static {
      long var0 = 104298118023826L;
      long var2 = -6189464773994313072L ^ var0;
      A = (int)var2;
   }
}
