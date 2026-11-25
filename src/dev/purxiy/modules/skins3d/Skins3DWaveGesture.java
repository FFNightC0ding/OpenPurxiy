package dev.purxiy.modules.skins3d;

import java.nio.charset.StandardCharsets;
import org.joml.Vector3f;

public final class Skins3DWaveGesture implements Skins3DApi {
   private static final Skins3DAnimation.Data x;
   private static final Skins3DAnimation.Data S;
   private static final Skins3DAnimation.Data u;
   private static final int Y = 1;
   private static final int N;
   private static final int i;
   private final Skins3DCurve V = new Skins3DCurve();
   private final Skins3DCurve A = new Skins3DCurve();
   private final Skins3DCurve k = new Skins3DCurve();
   private static final float h = 9.4F;
   private static final Vector3f Q;
   private static final Vector3f l;

   @Override
   public float cadence(float var1) {
      return Skins3DAnimation.mix(1.0F, 1.0F, var1);
   }

   @Override
   public int habits() {
      return 3;
   }

   @Override
   public float habitSeconds(int var1) {
      return var1 == 1 ? 2.6F : (var1 == 2 ? 2.4F : 3.0F);
   }

   @Override
   public void move(Skins3DCurve var1, Skins3DCurve var2, Skins3DGroundPose var3) {
      long var4 = 96194965299618L;
      long var6 = var4 ^ 5548986891695L;
      long var8 = var4 ^ 113688311726586L;
      long var10 = var4 ^ 85394569275599L;
      o(var8, var1, var3);
      if (var3.W > 0.0F) {
         this.V.set(var2);
         Skins3DAnimation.stride(this.V, var3, x, S);
         var1.lerp(this.V, var3.W * (1.0F - var3.U));
      }

      if (var3.U > 0.0F) {
         this.A.set(var2);
         Z(this.A, var3, var6);
         var1.lerp(this.A, var3.U * (1.0F - var3.f));
      }

      if (var3.f > 0.0F) {
         this.k.set(var1);
         M(var10, this.k, var3);
         var1.lerp(this.k, var3.f);
      }

      float var12 = var3.c;
      if (var12 > 0.0F) {
         var1.H += 1.4F * var12;
         var1.M += 1.2F * var12;
         var1.d -= 0.7F * var12;
         var1.q -= 0.45F * var12;
         var1.b += 0.2F * var12;
         var1.o -= 0.2F * var12;
         var1.y += 0.45F * var12;
         var1.v -= 0.4F * var12;
         if (var3.h) {
            var1.s = Skins3DAnimation.mix(var1.s, -0.85F, var12);
            var1.g = Skins3DAnimation.mix(var1.g, 0.15F, var12);
         }

         if (var3.r) {
            var1.n = Skins3DAnimation.mix(var1.n, 0.5F, var12);
            var1.X = Skins3DAnimation.mix(var1.X, -0.7F, var12);
            var1.Y = Skins3DAnimation.mix(var1.Y, 0.6F, var12);
         }
      }

      Skins3DPose.pose(var1, var3);
      Skins3DAnimation.busyArms(var1, var2, var3);
   }

   private static void o(long var0, Skins3DCurve var2, Skins3DGroundPose var3) {
      SkinPreset.ensureSourceClassInitialized();
      float var4 = Skins3DAnimation.breath(var3, 3.0F);
      float var5 = (float)Math.sin(var3.V * 0.9F);
      long var6 = 52893559545943L;
      int var9 = var6 < 0L ? -1 : 1;
      if (var3.h) {
         var2.s = -0.05F;
         var2.f = 0.0F;
         var2.e = 0.08F + 0.02F * var4;
         var2.g = 0.18F;
      }

      if (var3.r) {
         var2.n = -0.05F;
         var2.D = 0.0F;
         var2.X = -0.08F - 0.02F * var4;
         var2.Y = 0.18F;
      }

      var2.V += 0.45F * var5;
      var2.H = var2.H + 0.12F * Math.max(0.0F, -var5);
      var2.M = var2.M + 0.12F * Math.max(0.0F, var5);
      var2.v += 0.02F * var4;
      float var9x = var3.R;
      long var10 = 52893559545943L;
      int var12 = var10 < 0L ? -1 : 1;
      if (var3.k == 1 && var3.h) {
         float var16 = Skins3DAnimation.hold(var9x, 0.18F, 0.22F);
         float var13 = Skins3DAnimation.hold((var9x - 0.55F) / 0.18F, 0.3F, 0.5F);
         var2.s = Skins3DAnimation.mix(var2.s, -1.15F - 0.45F * var13, var16);
         var2.f = Skins3DAnimation.mix(var2.f, -0.55F + 0.3F * var13, var16);
         var2.e = Skins3DAnimation.mix(var2.e, 0.05F, var16);
         var2.g = Skins3DAnimation.mix(var2.g, 1.55F - 1.0F * var13, var16);
         var2.v += 0.32F * var16 * (1.0F - var13);
         var2.L -= 0.3F * var16 * (1.0F - var13);
      } else {
         long var12x = 52893559545944L;
         int var14 = var12x < 0L ? -1 : 1;
         if (var3.k == 2) {
            float var14x = Skins3DAnimation.hold(var9x, 0.15F, 0.15F);
            float var15 = (float)Math.sin(var9x * Math.PI * 2.0 * 1.5);
            var2.t += 0.32F * var15 * var14x;
            var2.v = var2.v + 0.12F * var14x * (float)Math.cos(var9x * Math.PI * 2.0 * 1.5);
         } else if (var3.k == 3) {
            var2.L = var2.L + 0.65F * (float)Math.sin(var9x * Math.PI * 2.0) * Skins3DAnimation.hold(var9x, 0.12F, 0.12F);
         }
      }
   }

   private static void Z(Skins3DCurve var0, Skins3DGroundPose var1, long var2) {
      float var4 = var1.W;
      float var5 = var1.N;
      float var6 = (float)Math.cos(var5);
      float var7 = Math.max(0.0F, (float)Math.sin(var5 + 0.3F));
      float var8 = Math.max(0.0F, -((float)Math.sin(var5 + 0.3F)));
      var0.y = 0.78F;
      SkinPreset.ensureSourceClassInitialized();
      var0.m = 0.14F;
      var0.v = var0.v - 0.78F - 0.18F;
      var0.L -= 0.14F;
      var0.d = -1.3F + u.legSwing() * var6 * var4 - u.lift() * var7 * var4;
      var0.q = -1.0F - u.legSwing() * var6 * var4 - u.lift() * var8 * var4;
      var0.b = 0.7F;
      var0.o = -0.7F;
      var0.w = 0.3F;
      var0.F = -0.3F;
      var0.H = 2.25F + u.knee() * var7 * var4;
      var0.M = 2.0F + u.knee() * var8 * var4;
      if (var1.h) {
         var0.s = -1.1F;
         var0.f = 0.15F;
         var0.e = -0.05F;
         var0.g = 0.2F;
      }

      if (var1.r) {
         var0.n = 0.75F - 0.25F * var6 * var4;
         var0.D = -0.2F;
         var0.X = -1.05F;
         var0.Y = 0.95F;
      }

      var0.h = u.bounce() * Math.abs((float)Math.sin(var5)) * var4;
      var0.A = u.roll() * (float)Math.sin(var5) * var4;
      var0.V = u.hips() * (float)Math.sin(var5) * var4;
   }

   @Override
   public void finish(Skins3DRestPose var1, Skins3DGroundPose var2) {
      float var3 = var2.U * (1.0F - var2.f) * (var2.h && var2.S == 0 ? 1 : 0);
      if (!(var3 <= 0.0F)) {
         float var4 = (float)Math.sin(var2.N) * var2.W;
         Q.set(-1.6F, 24.0F - var1.b[6] - 0.35F, -5.2F - 1.6F * var4);
         Skins3DSupport.reach(var1, 2, Q, 9.4F, l, var3);
      }
   }

   private static void M(long var0, Skins3DCurve var2, Skins3DGroundPose var3) {
      float var4 = var3.d;
      SkinPreset.ensureSourceClassInitialized();
      float var5 = 1.0F - var4;
      boolean var6 = var3.j > 0.0F;
      if (var3.h) {
         var2.s = Skins3DAnimation.mix(-1.45F, -0.9F, var5);
         var2.f = 0.0F;
         var2.e = Skins3DAnimation.mix(1.05F, 1.25F, var5);
         var2.g = 0.3F;
      }

      if (var3.r) {
         var2.n = Skins3DAnimation.mix(-1.45F, -0.9F, var5);
         var2.D = 0.0F;
         var2.X = -Skins3DAnimation.mix(1.05F, 1.25F, var5);
         var2.Y = 0.3F;
      }

      float var7 = Skins3DAnimation.mix(-1.2F, -0.5F, var5);
      float var8 = Skins3DAnimation.mix(1.6F, 0.9F, var5);
      float var9 = Skins3DAnimation.mix(0.45F, 0.15F, var5);
      float var10 = Skins3DAnimation.mix(0.45F, 0.6F, var5);
      var2.d = var6 ? var7 : var9;
      var2.H = var6 ? var8 : var10;
      var2.q = var6 ? var9 : var7;
      var2.M = var6 ? var10 : var8;
      var2.y = 0.12F;
      var2.v -= 0.12F;
   }

   static {
      long var0 = 49031206808092L;
      long[] var2 = new long[2];
      int var3 = 0;
      String var4 = "Ó\u0091\u0003Ð÷ª*\u001f\u0090îA\u0087÷ª*\u001e";
      int var5 = "Ó\u0091\u0003Ð÷ª*\u001f\u0090îA\u0087÷ª*\u001e".length();
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

      N = (int)var2[1];
      i = (int)var2[0];
      x = new Skins3DAnimation.Data(0.75F, 0.85F, 0.08F, 0.3F, 0.1F, 0.45F, 0.05F, 0.08F, 0.3F, 0.25F, 0.06F, 0.08F, 0.5F, 1.0F, 0.02F, 0.2F, 0.02F);
      S = new Skins3DAnimation.Data(1.05F, 1.55F, 0.18F, 0.4F, 0.3F, 1.0F, 0.15F, 0.1F, 1.35F, 0.25F, 0.32F, 0.16F, 1.0F, 1.3F, 0.02F, 0.1F, 0.02F);
      u = new Skins3DAnimation.Data(0.35F, 0.5F, 0.0F, 0.3F, 0.3F, 0.3F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.04F, 0.35F, 1.0F, 0.04F, 0.25F, 0.0F);
      Q = new Vector3f();
      l = new Vector3f(-1.0F, 0.2F, 0.15F);
   }
}
