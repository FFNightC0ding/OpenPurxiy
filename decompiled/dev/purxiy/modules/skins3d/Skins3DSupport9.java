package dev.purxiy.modules.skins3d;

import java.nio.charset.StandardCharsets;

public final class Skins3DSupport9 implements Skins3DApi {
   private static final Skins3DAnimation.Data M;
   private static final float R = 1.0F;
   private static final int i = 1;
   private static final int a;
   private static final int w;
   private final Skins3DCurve h = new Skins3DCurve();
   private final Skins3DCurve d = new Skins3DCurve();
   private final Skins3DCurve T = new Skins3DCurve();

   @Override
   public float cadence(float var1) {
      return Skins3DAnimation.mix(1.7F, 1.25F, var1);
   }

   @Override
   public int habits() {
      return 3;
   }

   @Override
   public float habitSeconds(int var1) {
      return var1 == 3 ? 1.4F : (var1 == 2 ? 3.0F : 2.6F);
   }

   @Override
   public void move(Skins3DCurve var1, Skins3DCurve var2, Skins3DSupport5 var3) {
      long var4 = 96194965299618L;
      long var6 = var4 ^ 21758962457112L;
      long var8 = var4 ^ 78448745866191L;
      long var10 = var4 ^ 129095650881455L;
      long var12 = var4 ^ 50495884199832L;
      P(var1, var3, var8);
      if (var3.W > 0.0F) {
         this.h.set(var2);
         Skins3DAnimation.stride(this.h, var3, M, M);
         C(var10, this.h, var3);
         if (var3.H > 0.0F) {
            this.d.set(var2);
            n(this.d, var12, var3);
            this.h.lerp(this.d, var3.H * (1.0F - var3.U));
         }

         var1.lerp(this.h, var3.W);
      }

      if (var3.f > 0.0F) {
         this.T.set(var1);
         W(this.T, var3, var6);
         var1.lerp(this.T, var3.f);
      }

      float var14 = var3.c;
      if (var14 > 0.0F) {
         var1.b += 0.3F * var14;
         var1.o -= 0.3F * var14;
         var1.y += 0.2F * var14;
         var1.v -= 0.2F * var14;
      }

      Skins3DAnimation.busyArms(var1, var2, var3);
   }

   private static void P(Skins3DCurve var0, Skins3DSupport5 var1, long var2) {
      long var4 = 37470617325069L;
      SkinPreset.ensureSourceClassInitialized();
      float var6 = Skins3DAnimation.breath(var1, 2.2F);
      if (var1.h) {
         var0.s = -0.45F - 0.04F * var6;
         var0.f = 0.0F;
         var0.e = 0.05F;
         var0.g = 0.0F;
      }

      if (var1.r) {
         var0.n = -0.45F - 0.04F * var6;
         var0.D = 0.0F;
         var0.X = -0.05F;
         var0.Y = 0.0F;
      }

      var0.v += 0.03F * var6;
      C(var4, var0, var1);
      long var7 = 17748534821484L;
      int var15 = var7 < 0L ? -1 : 1;
      if (var1.k == 1) {
         long var10 = 17748534821485L;
         int var16 = var10 < 0L ? -1 : 1;
         float var13 = var1.R;
         float var14 = Skins3DAnimation.hold(var13, 0.25F, 0.25F);
         var0.t += 0.38F * var14;
         var0.v -= 0.06F * var14;
      } else {
         long var15x = 17748534821484L;
         int var161 = var15x < 0L ? -1 : 1;
         if (var1.k == 2) {
            float var12 = var1.R;
            var0.L = var0.L + 0.65F * (float)Math.sin(var12 * Math.PI * 2.0) * Skins3DAnimation.hold(var12, 0.12F, 0.12F);
         } else if (var1.k == 3) {
            float var16x = var1.R;
            float var17 = Skins3DAnimation.hold(var16x, 0.18F, 0.3F);
            if (var1.h) {
               var0.s = Skins3DAnimation.mix(var0.s, -2.6F, var17);
               var0.e = Skins3DAnimation.mix(var0.e, 0.45F, var17);
            }

            if (var1.r) {
               var0.n = Skins3DAnimation.mix(var0.n, -2.6F, var17);
               var0.X = Skins3DAnimation.mix(var0.X, -0.45F, var17);
            }

            var0.h = var0.h + 2.2F * (float)Math.sin(Math.PI * Skins3DAnimation.clamp01((var16x - 0.12F) / 0.38F));
            var0.v -= 0.2F * var17;
         }
      }
   }

   private static void C(long var0, Skins3DCurve var2, Skins3DSupport5 var3) {
      SkinPreset.ensureSourceClassInitialized();
      float var4 = var3.U;
      long var5 = 37470617325068L;
      int var7 = var5 < 0L ? -1 : 1;
      if (!(var4 <= 0.0F)) {
         var2.y += 0.5F * var4;
         var2.v -= 0.45F * var4;
         if (var3.h) {
            var2.s = Skins3DAnimation.mix(var2.s, -0.55F, var4);
         }

         if (var3.r) {
            var2.n = Skins3DAnimation.mix(var2.n, -0.55F, var4);
         }

         var2.b += 0.45F * var4;
         var2.o -= 0.45F * var4;
      }
   }

   private static void n(Skins3DCurve var0, long var1, Skins3DSupport5 var3) {
      SkinPreset.ensureSourceClassInitialized();
      float var4 = var3.N;
      float var5 = 1.0F + 0.14F * (float)Math.sin(var4 + 1.2F);
      var0.y = var5;
      var0.m = 0.0F;
      var0.v = var0.v - var5 * 0.85F - 0.12F;
      var0.L *= 0.5F;
      if (var3.h) {
         var0.s = -var5 + 0.85F * (float)Math.sin(var4);
         var0.f = 0.0F;
         var0.e = 0.12F;
         var0.g = 0.0F;
      }

      if (var3.r) {
         var0.n = -var5 + 0.85F * (float)Math.sin(var4 - 0.35F);
         var0.D = 0.0F;
         var0.X = -0.12F;
         var0.Y = 0.0F;
      }

      var0.d = -0.1F + 0.9F * (float)Math.sin(var4 + 2.6F);
      var0.q = -0.1F + 0.9F * (float)Math.sin(var4 + 2.25F);
      var0.b = 0.05F;
      var0.o = -0.05F;
      var0.H = var0.M = 0.0F;
      var0.h = 1.6F * Math.max(0.0F, (float)Math.sin(var4 + 0.4F));
      var0.A = 0.0F;
      var0.V = 0.0F;
   }

   private static void W(Skins3DCurve var0, Skins3DSupport5 var1, long var2) {
      float var4 = var1.H * (1.0F - var1.U);
      float var5 = var1.d;
      SkinPreset.ensureSourceClassInitialized();
      float var6 = Skins3DAnimation.mix(-2.4F, -2.0F, var4);
      float var7 = Skins3DAnimation.mix(0.45F, 0.12F, var4);
      if (var1.h) {
         var0.s = var6;
         var0.f = 0.0F;
         var0.e = var7;
      }

      if (var1.r) {
         var0.n = var6;
         var0.D = 0.0F;
         var0.X = -var7;
      }

      float var8 = Skins3DAnimation.mix(-0.7F * var5, 1.0F, var4);
      var0.d = var8;
      var0.q = var8;
      var0.y = Skins3DAnimation.mix(0.05F, 0.85F, var4);
      var0.v = var0.v - var0.y * 0.85F;
      var0.h = 0.0F;
   }

   static {
      long var0 = 61086491751468L;
      long[] var2 = new long[2];
      int var3 = 0;
      String var4 = "îE\u0096dÎ¤4/:\u0007ÞlÎ¤4.";
      int var5 = "îE\u0096dÎ¤4/:\u0007ÞlÎ¤4.".length();
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

      a = (int)var2[1];
      w = (int)var2[0];
      M = new Skins3DAnimation.Data(0.5F, 0.0F, 0.0F, 0.0F, 0.15F, 0.22F, 0.55F, 0.12F, 0.0F, 0.0F, 0.1F, 0.06F, 0.6F, 1.2F, 0.09F, 0.25F, 0.04F);
   }
}
