package dev.purxiy.modules.spotify;

import java.nio.charset.StandardCharsets;

final class SpotifySupport {
   static final float N = -0.9F;
   static final float Q = 0.9F;
   static final float t = -0.94F;
   static final float i = 0.82F;
   static final float M = 0.72F;
   static final float P = 0.26F;
   static final float X = 0.45F;
   static final int S;
   static final int c;
   private final float[] m = new float[240];
   private int L;
   private final float[] u = new float[40];
   private int E;
   private float O = Float.NaN;
   private float K = Float.NaN;

   void clear() {
      this.L = 0;
      this.E = 0;
   }

   void obstacle(float var1, float var2, float var3, float var4, float var5) {
      if (this.L < 48 && !(var3 <= var1) && !(var4 <= var2) && !(var5 <= 0.0F)) {
         int var6 = this.L++ * 5;
         this.m[var6] = var1;
         this.m[var6 + 1] = var2;
         this.m[var6 + 2] = var3;
         this.m[var6 + 3] = var4;
         this.m[var6 + 4] = var5;
      }
   }

   void written(SpotifySupport.TypeU var1, float var2) {
      if (this.E < 8 && var1.valid() && !(var2 <= 0.0F)) {
         int var3 = this.E++ * 5;
         this.u[var3] = var1.b;
         this.u[var3 + 1] = var1.E;
         this.u[var3 + 2] = var1.T;
         this.u[var3 + 3] = var1.G;
         this.u[var3 + 4] = var2;
      }
   }

   void last(float var1, float var2) {
      this.O = var1;
      this.K = var2;
   }

   void forgetLast() {
      this.O = Float.NaN;
      this.K = Float.NaN;
   }

   boolean hasLast() {
      return !Float.isNaN(this.O);
   }

   float lastX() {
      return this.O;
   }

   int obstacleCount() {
      return this.L;
   }

   float score(SpotifySupport.TypeU var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      long var8 = 131700076933268L;
      long var10 = var8 ^ 64955115810981L;
      float var12 = Math.max(1.0E-4F, var1.area());
      float var13 = 1.0F;
      var13 -= 3.0F * (1.0F - inside(var1));
      var13 -= 1.5F * (1.0F - var1.overlap(-0.72F, -1.0F, 0.72F, 1.0F) / var12);
      float var14 = var13 - this.covered(var1);
      if (this.hasLast()) {
         float var15 = var1.centerX();
         float var16 = var1.centerY();
         float var17 = (float)Math.hypot(var15 - this.O, (var16 - this.K) * 0.8F);
         var14 += 0.5F * Math.min(1.0F, var17 / 0.55F);
         if (this.O < -0.12F && var15 > 0.05F || this.O > 0.12F && var15 < -0.05F) {
            var14 += 0.08F;
         }
      }

      var13 = var14 - 2.2F * (1.0F - v(var5, var10));
      var13 -= 1.0F * (1.0F - v(var6, var10));
      var13 -= (var2 < var3 ? 0.25F : 0.14F) * Math.abs(var2 - var3);
      var13 -= 5.0F * Math.max(0.0F, 0.26F - var4);
      float var24 = var13 - 0.3F * Math.abs(var1.centerX());
      float var25 = var1.T - var1.b;
      if (var25 > 1.6F) {
         var24 -= 2.0F * (var25 - 1.6F);
      }

      return var24 + var7;
   }

   float covered(SpotifySupport.TypeU var1) {
      float var2 = Math.max(1.0E-4F, var1.area());
      float var3 = 0.0F;

      for (int var4 = 0; var4 < this.L; var4++) {
         int var5 = var4 * 5;
         var3 += this.m[var5 + 4] * var1.overlap(this.m[var5], this.m[var5 + 1], this.m[var5 + 2], this.m[var5 + 3]) / var2;
      }

      for (int var6 = 0; var6 < this.E; var6++) {
         int var7 = var6 * 5;
         var3 += 4.0F * this.u[var7 + 4] * var1.overlap(this.u[var7], this.u[var7 + 1], this.u[var7 + 2], this.u[var7 + 3]) / var2;
      }

      return var3;
   }

   static float inside(SpotifySupport.TypeU var0) {
      float var1 = var0.area();
      return var1 <= 0.0F ? 0.0F : var0.overlap(-0.9F, -0.94F, 0.9F, 0.82F) / var1;
   }

   static float shown(SpotifySupport.TypeU var0) {
      float var1 = var0.area();
      return var1 <= 0.0F ? 0.0F : var0.overlap(-1.0F, -1.0F, 1.0F, 1.0F) / var1;
   }

   static float hash(long var0) {
      long var2 = var0 * -7046029254386353131L + 7146057691288625177L;
      var2 = (var2 ^ var2 >>> 30) * -4658895280553007687L;
      var2 = (var2 ^ var2 >>> 27) * -7723592293110705685L;
      var2 ^= var2 >>> 31;
      return (float)(var2 >>> 40) / 1.6777216E7F;
   }

   private static float v(float var0, long var1) {
      SpotifyCurve.ensureSourceClassInitialized();
      return var0 < 0.0F ? 0.0F : Math.min(1.0F, var0);
   }

   static {
      long var0 = 38307165106088L;
      long[] var2 = new long[2];
      int var3 = 0;
      String var4 = "\u0017ÄUð\u0014÷ï\u0098ºD{\u0093\u0014÷ï ";
      int var5 = "\u0017ÄUð\u0014÷ï\u0098ºD{\u0093\u0014÷ï ".length();
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

      c = (int)var2[1];
      S = (int)var2[0];
   }

   static final class TypeU {
      float b;
      float E;
      float T;
      float G;

      SpotifySupport.TypeU set(float var1, float var2, float var3, float var4) {
         this.b = var1;
         this.E = var2;
         this.T = var3;
         this.G = var4;
         return this;
      }

      SpotifySupport.TypeU set(SpotifySupport.TypeU var1) {
         return this.set(var1.b, var1.E, var1.T, var1.G);
      }

      SpotifySupport.TypeU empty() {
         return this.set(Float.MAX_VALUE, Float.MAX_VALUE, -Float.MAX_VALUE, -Float.MAX_VALUE);
      }

      SpotifySupport.TypeU add(float var1, float var2) {
         this.b = Math.min(this.b, var1);
         this.E = Math.min(this.E, var2);
         this.T = Math.max(this.T, var1);
         this.G = Math.max(this.G, var2);
         return this;
      }

      boolean valid() {
         return this.T > this.b && this.G > this.E;
      }

      float area() {
         return Math.max(0.0F, this.T - this.b) * Math.max(0.0F, this.G - this.E);
      }

      float centerX() {
         return (this.b + this.T) / 2.0F;
      }

      float centerY() {
         return (this.E + this.G) / 2.0F;
      }

      float overlap(float var1, float var2, float var3, float var4) {
         float var5 = Math.min(this.T, var3) - Math.max(this.b, var1);
         float var6 = Math.min(this.G, var4) - Math.max(this.E, var2);
         return var5 > 0.0F && var6 > 0.0F ? var5 * var6 : 0.0F;
      }

      boolean onScreen() {
         return this.T > -1.0F && this.b < 1.0F && this.G > -1.0F && this.E < 1.0F;
      }
   }
}
