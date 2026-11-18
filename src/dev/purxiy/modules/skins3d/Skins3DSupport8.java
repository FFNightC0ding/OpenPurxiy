// Sourced By DevFFNightmare
package dev.purxiy.modules.skins3d;

public final class Skins3DSupport8 implements Skins3DApi {
   private static final Skins3DAnimation.Data g = new Skins3DAnimation.Data(
      0.42F, 0.5F, 0.05F, 0.3F, 0.1F, 0.22F, 0.24F, 0.16F, 0.5F, 0.2F, -0.03F, 0.07F, 0.35F, 1.0F, 0.05F, 0.4F, 0.025F
   );
   private static final Skins3DAnimation.Data n = new Skins3DAnimation.Data(
      0.8F, 1.1F, 0.15F, 0.35F, 0.25F, 0.85F, 0.1F, 0.12F, 1.35F, 0.15F, 0.2F, 0.14F, 1.0F, 1.4F, 0.04F, 0.3F, 0.04F
   );
   private static final Skins3DAnimation.Data o = new Skins3DAnimation.Data(
      0.55F, 1.25F, 0.35F, 0.2F, 0.45F, 0.1F, 0.8F, 0.06F, 1.05F, 0.1F, 0.42F, 0.04F, 0.6F, 0.8F, 0.03F, 0.2F, 0.02F
   );
   private static final int L = 1;
   private static final int i;
   private final Skins3DCurve s = new Skins3DCurve();
   private final Skins3DCurve I = new Skins3DCurve();

   @Override
   public float cadence(float var1) {
      return Skins3DAnimation.mix(1.15F, 1.05F, var1);
   }

   @Override
   public int habits() {
      return 2;
   }

   @Override
   public float habitSeconds(int var1) {
      return var1 == 2 ? 2.6F : 3.4F;
   }

   @Override
   public void move(Skins3DCurve var1, Skins3DCurve var2, Skins3DSupport5 var3) {
      long var4 = 96194965299618L;
      long var6 = var4 ^ 64306246555150L;
      long var8 = var4 ^ 132353461841117L;
      int var10 = (int)((var4 ^ 132353461841117L) >>> 48);
      int var11 = (int)((var4 ^ 132353461841117L) << 16 >>> 32);
      int var12 = (int)(var8 << 48 >>> 48);
      long var13 = var4 ^ 126232980032696L;
      float var15 = var3.U;
      k(var1, var3, var13);
      if (var3.W > 0.0F) {
         this.s.set(var2);
         Skins3DAnimation.stride(this.s, var3, Skins3DAnimation.Data.mix(g, o, var15), n);
         var1.lerp(this.s, var3.W);
      }

      if (var3.f > 0.0F) {
         this.I.set(var1);
         n(var6, this.I, var3);
         var1.lerp(this.I, var3.f);
      }

      I((char)var10, var1, var11, var3, (char)var12);
      Skins3DAnimation.busyArms(var1, var2, var3);
   }

   private static void k(Skins3DCurve var0, Skins3DSupport5 var1, long var2) {
      SkinPreset.ensureSourceClassInitialized();
      float var4 = Skins3DAnimation.breath(var1, 3.4F);
      float var5 = var1.U;
      long var6 = 41454039952666L;
      if (41454039952666L == 0L) {
         boolean var10000 = false;
      } else {
         int var19 = var6 < 0L ? -1 : 1;
      }

      if (var1.h) {
         var0.s = Skins3DAnimation.mix(-0.12F, -0.75F, var5);
         var0.f = 0.0F;
         var0.e = Skins3DAnimation.mix(0.13F + 0.025F * var4, 0.05F, var5);
         var0.g = Skins3DAnimation.mix(0.25F, 1.05F, var5);
      }

      if (var1.r) {
         var0.n = Skins3DAnimation.mix(-0.12F, -0.75F, var5);
         var0.D = 0.0F;
         var0.X = -Skins3DAnimation.mix(0.13F + 0.025F * var4, 0.05F, var5);
         var0.Y = Skins3DAnimation.mix(0.25F, 1.05F, var5);
      }

      var0.y += 0.42F * var5;
      var0.v -= 0.42F * var5;
      var0.H += 0.45F * var5;
      var0.M += 0.45F * var5;
      var0.v += 0.02F * var4;
      long var9 = 41454039952666L;
      if (41454039952666L == 0L) {
         boolean var20 = false;
      } else {
         int var21 = var9 < 0L ? -1 : 1;
      }

      if (var1.k == 1) {
         long var11 = 41454039952665L;
         if (41454039952665L == 0L) {
            boolean var22 = false;
         } else {
            int var23 = var11 < 0L ? -1 : 1;
         }

         float var14 = var1.R;
         float var15 = (float)Math.sin(var14 * Math.PI * 2.0) * Skins3DAnimation.hold(var14, 0.15F, 0.15F);
         var0.L += 0.55F * var15;
         var0.v = var0.v - 0.06F * Math.abs(var15);
      } else {
         long var16 = 41454039952665L;
         if (41454039952665L == 0L) {
            boolean var24 = false;
         } else {
            int var25 = var16 < 0L ? -1 : 1;
         }

         if (var1.k == 2 && var1.h) {
            float var13 = var1.R;
            float var17 = Skins3DAnimation.hold(var13, 0.2F, 0.2F);
            float var18 = (float)(var13 * Math.PI * 2.0 * 3.0);
            var0.s = Skins3DAnimation.mix(var0.s, -0.62F + 0.1F * (float)Math.sin(var18), var17);
            var0.f = Skins3DAnimation.mix(var0.f, 0.35F, var17);
            var0.e = Skins3DAnimation.mix(var0.e, -0.05F + 0.08F * (float)Math.cos(var18), var17);
            var0.g = Skins3DAnimation.mix(var0.g, 1.05F, var17);
            var0.v += 0.12F * var17;
         }
      }
   }

   private static void n(long var0, Skins3DCurve var2, Skins3DSupport5 var3) {
      float var4 = var3.d;
      SkinPreset.ensureSourceClassInitialized();
      float var5 = (float)Math.sin(var3.V * 16.0F) * 0.14F;
      long var6 = 119852476397483L;
      if (119852476397483L == 0L) {
         boolean var10000 = false;
      } else {
         int var17 = var6 < 0L ? -1 : 1;
      }

      if (var3.h) {
         var2.s = -2.2F - 0.35F * (1.0F - var4) + var5;
         var2.f = 0.0F;
         var2.e = 0.55F;
         var2.g = 0.35F;
      }

      if (var3.r) {
         var2.n = -2.2F - 0.35F * (1.0F - var4) - var5;
         var2.D = 0.0F;
         var2.X = -0.55F;
         var2.Y = 0.35F;
      }

      float var9 = var3.j;
      long var10 = 119852476397483L;
      if (119852476397483L == 0L) {
         boolean var18 = false;
      } else {
         int var19 = var10 < 0L ? -1 : 1;
      }

      var2.d = var9 > 0.0F ? -0.45F * var4 : 0.15F;
      var2.q = var9 > 0.0F ? 0.15F : -0.45F * var4;
      long var13 = 119852476397484L;
      if (119852476397484L == 0L) {
         boolean var20 = false;
      } else {
         int var21 = var13 < 0L ? -1 : 1;
      }

      var2.H = var9 > 0.0F ? 0.8F * var4 + 0.2F : 0.3F;
      long var15 = 119852476397483L;
      if (119852476397483L == 0L) {
         boolean var22 = false;
      } else {
         int var23 = var15 < 0L ? -1 : 1;
      }

      var2.M = var9 > 0.0F ? 0.3F : 0.8F * var4 + 0.2F;
      var2.y = -0.08F;
      var2.v += 0.08F;
   }

   private static void I(char var0, Skins3DCurve var1, int var2, Skins3DSupport5 var3, char var4) {
      SkinPreset.ensureSourceClassInitialized();
      float var5 = var3.c;
      if (!(var5 <= 0.0F)) {
         var1.H += 0.55F * var5;
         var1.M += 0.55F * var5;
         var1.d -= 0.25F * var5;
         var1.q -= 0.25F * var5;
         var1.y += 0.12F * var5;
         var1.v -= 0.1F * var5;
         if (var1.e < 0.5F) {
            var1.e += 0.25F * var5;
         }

         if (var1.X > -0.5F) {
            var1.X -= 0.25F * var5;
         }
      }
   }

   @Override
   public void finish(Skins3DSupport3 var1, Skins3DSupport5 var2) {
      float var3 = Skins3DAnimation.breath(var2, 3.4F) * var2.idle();
      float var4 = (float)Math.sin(2.0F * var2.N - 0.6F) * var2.ground() * Skins3DAnimation.mix(0.012F, 0.03F, var2.H);
      var1.N[1] = var1.N[1] * (1.0F + 0.012F * var3 + var4);
      var1.x[1] = var1.x[1] * (1.0F + 0.02F * var3 + var4);
   }

   static {
      long var0 = 110483487360788L;
      long var2 = -8208532103273085162L ^ var0;
      i = (int)var2;
   }
}
