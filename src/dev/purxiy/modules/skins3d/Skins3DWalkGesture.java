package dev.purxiy.modules.skins3d;

import java.nio.charset.StandardCharsets;

public final class Skins3DWalkGesture implements Skins3DApi {
   private static final Skins3DAnimation.Data M;
   private static final Skins3DAnimation.Data t;
   private static final Skins3DAnimation.Data k;
   public static final int D = 1;
   public static final int G;
   public static final int P;
   private final Skins3DCurve E = new Skins3DCurve();
   private final Skins3DCurve O = new Skins3DCurve();

   @Override
   public float cadence(float var1) {
      return Skins3DAnimation.mix(1.1F, 1.3F, var1);
   }

   @Override
   public int habits() {
      return 3;
   }

   @Override
   public float habitSeconds(int var1) {
      return var1 == 1 ? 1.8F : (var1 == 2 ? 3.0F : 3.2F);
   }

   @Override
   public void move(Skins3DCurve var1, Skins3DCurve var2, Skins3DGroundPose var3) {
      long var4 = 96194965299618L;
      long var6 = var4 ^ 53094785050862L;
      long var8 = var4 ^ 8254587855507L;
      K(var1, var3, var6);
      if (var3.W > 0.0F) {
         this.E.set(var2);
         Skins3DAnimation.stride(this.E, var3, Skins3DAnimation.Data.mix(M, k, var3.U), t);
         float var10 = (float)Math.sin(var3.N);
         float var11 = (1.0F - var3.H) * (1.0F - var3.U);
         this.E.b -= 0.06F * var11;
         this.E.o += 0.06F * var11;
         this.E.t += 0.05F * var10 * var11;
         var1.lerp(this.E, var3.W);
      }

      if (var3.f > 0.0F) {
         this.O.set(var1);
         m(this.O, var8, var3);
         var1.lerp(this.O, var3.f);
      }

      float var12 = var3.c;
      if (var12 > 0.0F) {
         var1.H += 0.8F * var12;
         var1.M += 0.8F * var12;
         var1.d -= 0.35F * var12;
         var1.q -= 0.35F * var12;
         var1.b -= 0.1F * var12;
         var1.o += 0.1F * var12;
      }

      Skins3DAnimation.busyArms(var1, var2, var3);
   }

   private static void K(Skins3DCurve var0, Skins3DGroundPose var1, long var2) {
      SkinPreset.ensureSourceClassInitialized();
      float var4 = Skins3DAnimation.breath(var1, 3.2F);
      float var5 = var1.U;
      long var6 = 113486817462604L;
      int var9 = var6 < 0L ? -1 : 1;
      if (var1.h) {
         var0.s = Skins3DAnimation.mix(-0.38F, -0.85F, var5);
         var0.f = -0.32F;
         var0.e = -0.08F;
         var0.g = Skins3DAnimation.mix(0.85F, 1.1F, var5);
      }

      if (var1.r) {
         var0.n = Skins3DAnimation.mix(-0.38F, -0.85F, var5);
         var0.D = 0.32F;
         var0.X = 0.08F;
         var0.Y = Skins3DAnimation.mix(0.85F, 1.1F, var5);
      }

      var0.V += 0.55F * (1.0F - var5);
      var0.M += 0.28F * (1.0F - var5);
      var0.o -= 0.04F * (1.0F - var5);
      var0.t += 0.06F * (1.0F - var5) + 0.02F * var4;
      var0.v += 0.02F * var4;
      var0.H += 1.3F * var5;
      var0.M += 1.3F * var5;
      var0.d -= 0.6F * var5;
      var0.q -= 0.6F * var5;
      var0.b -= 0.14F * var5;
      var0.o += 0.14F * var5;
      var0.y += 0.25F * var5;
      var0.v -= 0.25F * var5;
      float var9x = var1.R;
      long var10 = 113486817462604L;
      int var17 = var10 < 0L ? -1 : 1;
      if (var1.k == 1) {
         float var12 = Skins3DAnimation.hold(var9x, 0.2F, 0.25F);
         float var13 = (float)Math.sin(var9x * Math.PI * 2.0 * 2.0);
         if (var1.h) {
            var0.s = Skins3DAnimation.mix(var0.s, -2.25F, var12);
            var0.f = Skins3DAnimation.mix(var0.f, -0.6F, var12);
            var0.e = Skins3DAnimation.mix(var0.e, -0.12F, var12);
            var0.g = Skins3DAnimation.mix(var0.g, 2.15F, var12);
         }

         long var14 = 113486817462603L;
         int var151 = var14 < 0L ? -1 : 1;
         if (var1.r) {
            var0.n = Skins3DAnimation.mix(var0.n, -2.25F, var12);
            var0.D = Skins3DAnimation.mix(var0.D, 0.6F, var12);
            var0.X = Skins3DAnimation.mix(var0.X, 0.12F, var12);
            var0.Y = Skins3DAnimation.mix(var0.Y, 2.15F, var12);
         }

         var0.t += (0.22F + 0.06F * var13) * var12;
         var0.v += 0.06F * var12;
      } else {
         long var17x = 113486817462603L;
         int var19 = var17x < 0L ? -1 : 1;
         if (var1.k == 2 && var1.h) {
            float var19x = Skins3DAnimation.hold(var9x, 0.2F, 0.2F);
            float var20 = (float)(var9x * Math.PI * 2.0 * 3.0);
            var0.s = Skins3DAnimation.mix(var0.s, -2.15F + 0.08F * (float)Math.sin(var20), var19x);
            var0.f = Skins3DAnimation.mix(var0.f, 0.1F, var19x);
            var0.e = Skins3DAnimation.mix(var0.e, 0.42F + 0.06F * (float)Math.cos(var20), var19x);
            var0.g = Skins3DAnimation.mix(var0.g, 1.95F, var19x);
            var0.t -= 0.15F * var19x;
         } else if (var1.k == 3) {
            float var18 = Skins3DAnimation.hold(var9x, 0.15F, 0.15F);
            float var15 = (float)Math.sin(var9x * Math.PI * 2.0 * 1.5);
            var0.V += 0.6F * var15 * var18;
            var0.t -= 0.12F * var15 * var18;
            var0.A += 0.04F * var15 * var18;
         }
      }
   }

   private static void m(Skins3DCurve var0, long var1, Skins3DGroundPose var3) {
      SkinPreset.ensureSourceClassInitialized();
      float var4 = var3.d;
      if (var3.h) {
         var0.s = -1.8F;
         var0.f = 0.0F;
         var0.e = 0.35F;
         var0.g = 0.85F;
      }

      if (var3.r) {
         var0.n = -1.8F;
         var0.D = 0.0F;
         var0.X = -0.35F;
         var0.Y = 0.85F;
      }

      var0.d = 0.3F;
      var0.q = 0.2F;
      var0.H = 1.55F * var4 + 0.5F;
      var0.M = 1.35F * var4 + 0.5F;
      var0.b = -0.05F;
      var0.o = 0.05F;
      var0.v -= 0.1F;
   }

   static {
      long var0 = 75320504920670L;
      long[] var2 = new long[2];
      int var3 = 0;
      String var4 = "¯\u0000\u0099Ôëðª]\u0097¡YBëðª\\";
      int var5 = "¯\u0000\u0099Ôëðª]\u0097¡YBëðª\\".length();
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

      P = (int)var2[0];
      G = (int)var2[1];
      M = new Skins3DAnimation.Data(0.55F, 0.75F, 0.06F, 0.3F, 0.08F, 0.35F, 0.05F, 0.17F, 0.3F, 0.2F, 0.0F, 0.06F, 0.45F, 1.0F, 0.035F, 0.7F, 0.02F);
      t = new Skins3DAnimation.Data(0.7F, 1.45F, 0.15F, 0.65F, 0.1F, 0.45F, 0.3F, 0.55F, 1.25F, 0.2F, 0.14F, 0.06F, 0.9F, 1.4F, 0.03F, 0.4F, 0.03F);
      k = new Skins3DAnimation.Data(0.4F, 0.9F, 0.9F, 0.25F, 0.1F, 0.15F, 0.5F, 0.05F, 1.0F, 0.1F, 0.25F, 0.03F, 0.3F, 1.0F, 0.02F, 0.3F, 0.0F);
   }
}
