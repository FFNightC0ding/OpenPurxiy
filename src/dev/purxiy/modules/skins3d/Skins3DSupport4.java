// Sourced By DevFFNightmare
package dev.purxiy.modules.skins3d;

public final class Skins3DSupport4 implements Skins3DApi {
   private static final Skins3DAnimation.Data e = new Skins3DAnimation.Data(
      0.65F, 0.9F, 0.1F, 0.3F, 0.15F, 0.55F, 0.1F, 0.12F, 0.45F, 0.4F, 0.05F, 0.08F, 0.55F, 1.0F, 0.03F, 0.2F, 0.02F
   );
   private static final Skins3DAnimation.Data x = new Skins3DAnimation.Data(
      1.0F, 1.5F, 0.2F, 0.55F, 0.3F, 1.2F, 0.15F, 0.32F, 1.3F, 0.35F, 0.22F, 0.17F, 1.2F, 1.3F, 0.03F, 0.15F, 0.03F
   );
   private static final Skins3DAnimation.Data q = new Skins3DAnimation.Data(
      0.5F, 1.1F, 1.0F, 0.2F, 0.2F, 0.25F, 0.4F, 0.2F, 0.8F, 0.1F, 0.3F, 0.05F, 0.4F, 1.0F, 0.02F, 0.1F, 0.0F
   );
   private static final int N = 1;
   private static final int v;
   private final Skins3DCurve A = new Skins3DCurve();
   private final Skins3DCurve r = new Skins3DCurve();

   @Override
   public float cadence(float var1) {
      return Skins3DAnimation.mix(1.1F, 1.0F, var1);
   }

   @Override
   public int habits() {
      return 2;
   }

   @Override
   public float habitSeconds(int var1) {
      return var1 == 1 ? 3.2F : 1.6F;
   }

   @Override
   public void move(Skins3DCurve var1, Skins3DCurve var2, Skins3DSupport5 var3) {
      long var4 = 96194965299618L;
      long var6 = var4 ^ 65715988599537L;
      int var8 = (int)((var4 ^ 65715988599537L) >>> 56);
      int var9 = (int)((var4 ^ 65715988599537L) << 8 >>> 32);
      int var10 = (int)(var6 << 40 >>> 40);
      long var11 = var4 ^ 68195505689293L;
      W((byte)var8, var9, var1, var10, var3);
      if (var3.W > 0.0F) {
         this.A.set(var2);
         Skins3DAnimation.stride(this.A, var3, Skins3DAnimation.Data.mix(e, q, var3.U), x);
         float var13 = (float)Math.cos(var3.N);
         float var14 = var3.H * (1.0F - var3.U);
         if (var3.h) {
            this.A.f = this.A.f - 0.38F * var14 * Math.max(0.0F, var13);
            this.A.e = this.A.e + 0.25F * var14 * Math.max(0.0F, -var13);
         }

         if (var3.r) {
            this.A.D = this.A.D + 0.38F * var14 * Math.max(0.0F, -var13);
            this.A.X = this.A.X - 0.25F * var14 * Math.max(0.0F, var13);
         }

         var1.lerp(this.A, var3.W);
      }

      if (var3.f > 0.0F) {
         this.r.set(var1);
         O(var11, this.r, var3);
         var1.lerp(this.r, var3.f);
      }

      float var15 = var3.c;
      if (var15 > 0.0F) {
         var1.H += 1.0F * var15;
         var1.M += 1.0F * var15;
         var1.d -= 0.45F * var15;
         var1.q -= 0.45F * var15;
         var1.y += 0.25F * var15;
         var1.v -= 0.2F * var15;
      }

      Skins3DAnimation.busyArms(var1, var2, var3);
   }

   private static void W(byte var0, int var1, Skins3DCurve var2, int var3, Skins3DSupport5 var4) {
      SkinPreset.ensureSourceClassInitialized();
      float var5 = var4.U;
      float var6 = Skins3DAnimation.breath(var4, 2.8F);
      if (var4.h) {
         var2.s = Skins3DAnimation.mix(-0.06F, -0.75F, var5);
         var2.f = 0.0F;
         var2.e = Skins3DAnimation.mix(0.1F + 0.02F * var6, 0.25F, var5);
         var2.g = Skins3DAnimation.mix(0.2F, 0.6F, var5);
      }

      int var7 = 9728851;
      if (var4.r) {
         var2.n = Skins3DAnimation.mix(-0.06F, -0.75F, var5);
         var2.D = 0.0F;
         var2.X = -Skins3DAnimation.mix(0.1F + 0.02F * var6, 0.25F, var5);
         var2.Y = Skins3DAnimation.mix(0.2F, 0.6F, var5);
      }

      var2.H = var2.H + Skins3DAnimation.mix(0.06F, 1.9F, var5);
      var2.M = var2.M + Skins3DAnimation.mix(0.06F, 1.9F, var5);
      var2.d -= 0.95F * var5;
      var2.q -= 0.95F * var5;
      var2.b += 0.12F * var5;
      var2.o -= 0.12F * var5;
      var2.y += 0.35F * var5;
      var2.v -= 0.35F * var5;
      var2.h += 0.15F * var6;
      var7 = 9728851;
      if (var4.k == 1) {
         float var8 = var4.R;
         float var9 = (float)Math.sin(var8 * Math.PI * 2.0) * Skins3DAnimation.hold(var8, 0.12F, 0.12F);
         var2.L += 0.6F * var9;
      } else {
         var7 = 7125392;
         if (var4.k == 2 && var4.h) {
            float var15 = var4.R;
            float var16 = Skins3DAnimation.hold(var15, 0.22F, 0.35F);
            var2.s = Skins3DAnimation.mix(var2.s, -2.95F, var16);
            var2.e = Skins3DAnimation.mix(var2.e, 0.15F, var16);
            var7 = 9728851;
            float var10 = Skins3DAnimation.mix(var2.g, 0.15F, var16);
            var7 = 9728851;
            var2.g = var10;
            if (var4.r) {
               var2.X = Skins3DAnimation.mix(var2.X, -0.35F, var16);
            }

            var2.h = var2.h + 1.2F * (float)Math.sin(Math.PI * Skins3DAnimation.clamp01((var15 - 0.15F) / 0.4F));
            var2.v -= 0.15F * var16;
         }
      }
   }

   private static void O(long var0, Skins3DCurve var2, Skins3DSupport5 var3) {
      SkinPreset.ensureSourceClassInitialized();
      float var4 = var3.d;
      float var5 = 1.0F - var4;
      boolean var6 = var3.j > 0.0F;
      float var9 = Skins3DAnimation.mix(-3.0F, -1.5F, var5);
      float var10 = Skins3DAnimation.mix(0.12F, 0.9F, var5);
      float var11 = Skins3DAnimation.mix(0.12F, 0.4F, var5);
      float var7 = Skins3DAnimation.mix(0.35F, -0.6F, var5);
      float var8 = Skins3DAnimation.mix(0.35F, 0.9F, var5);
      long var12 = 115977578099566L;
      if (115977578099566L == 0L) {
         boolean var10000 = false;
      } else {
         int var25 = var12 < 0L ? -1 : 1;
      }

      label108: {
         if (var6) {
            if (!var3.h) {
               break label108;
            }
         } else {
            long var15 = 115977578099567L;
            if (115977578099567L == 0L) {
               boolean var26 = false;
            } else {
               int var27 = var15 < 0L ? -1 : 1;
            }

            if (!var3.r) {
               break label108;
            }
         }

         long var23 = 115977578099567L;
         if (115977578099567L == 0L) {
            boolean var28 = false;
         } else {
            int var29 = var23 < 0L ? -1 : 1;
         }

         if (var6) {
            long var18 = 115977578099566L;
            if (115977578099566L == 0L) {
               boolean var30 = false;
            } else {
               int var31 = var18 < 0L ? -1 : 1;
            }

            var2.s = var9;
            var2.f = 0.0F;
            var2.e = var10;
            var2.g = var11;
         } else {
            var2.n = var9;
            var2.D = 0.0F;
            var2.X = -var10;
            var2.Y = var11;
         }
      }

      long var20 = 115977578099567L;
      if (115977578099567L == 0L) {
         boolean var32 = false;
      } else {
         int var33 = var20 < 0L ? -1 : 1;
      }

      if (var6 ? var3.r : var3.h) {
         if (var6) {
            var12 = 115977578099567L;
            if (115977578099567L == 0L) {
               boolean var34 = false;
            } else {
               int var35 = var12 < 0L ? -1 : 1;
            }

            var2.n = var7;
            var2.D = 0.0F;
            var2.X = -var8;
            var2.Y = 0.3F;
         } else {
            var2.s = var7;
            var2.f = 0.0F;
            var2.e = var8;
            var2.g = 0.3F;
         }
      }

      float var22 = Skins3DAnimation.mix(-1.1F, -0.2F, var5);
      float var13 = Skins3DAnimation.mix(1.45F, 0.35F, var5);
      float var14 = Skins3DAnimation.mix(0.25F, 0.05F, var5);
      float var24 = Skins3DAnimation.mix(0.25F, 0.3F, var5);
      var2.d = var6 ? var22 : var14;
      var2.H = var6 ? var13 : var24;
      var2.q = var6 ? var14 : var22;
      var2.M = var6 ? var24 : var13;
      var2.y = 0.05F * var4;
      var2.v -= 0.1F * var4;
   }

   static {
      long var0 = 26751515131477L;
      long var2 = 3129610175607897687L ^ var0;
      v = (int)var2;
   }
}
