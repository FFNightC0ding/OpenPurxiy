// Sourced By DevFFNightmare
package dev.purxiy.modules.fire;

import com.mojang.blaze3d.buffers.Std140Builder;
import java.util.SplittableRandom;

final class LowFireCodec {
   private static final int A = 96;
   private final float[] D = new float[96];
   private final float[] H = new float[96];
   private final float[] o = new float[96];
   private final float[] v = new float[96];
   private final float[] x = new float[96];
   private final float[] Z = new float[96];
   private final float[] X = new float[96];
   private final float[] Q = new float[96];
   private final float[] u = new float[96];
   private final float[] z = new float[96];
   private final float[] m = new float[96];
   private final float[] q = new float[96];
   private final float[] g = new float[96];
   private final SplittableRandom E = new SplittableRandom();
   private int b;
   private float M;
   private float s;



   int count() {
      return this.b;
   }

   void clear() {
      this.b = 0;
      this.M = 0.0F;
   }

   void update(float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      long var9 = 43985261923788L;
      long var11 = var9 ^ 137435844057961L;
      this.s += var1;
      this.M += var2 * var1;

      while (this.M >= 1.0F) {
         this.M--;
         this.k(var11, var3, var4, 1.0F);
      }

      for (int var13 = 0; var13 < this.b; var13++) {
         this.x[var13] = this.x[var13] + var1;
         float var14 = this.Q[var13];
         this.v[var13] = this.v[var13] + 0.035F * var1;
         float var15 = (float)Math.sin(this.x[var13] * (1.7F + 2.2F * this.u[var13]) + this.u[var13] * 40.0F) * 0.055F;
         this.o[var13] = this.o[var13] + (var15 - this.o[var13]) * (1.0F - (float)Math.exp(-var1 * 1.8F));
         float var16 = this.o[var13] + var5 * var14;
         float var17 = this.v[var13] + var6 * var14;
         this.D[var13] = this.D[var13] + (var16 * var1 + var7 * var14);
         this.H[var13] = this.H[var13] + (var17 * var1 + var8 * var14);
         float var18 = Math.max(var1, 0.004166667F);
         this.q[var13] = var16 + var7 * var14 / var18;
         this.g[var13] = var17 + var8 * var14 / var18;
         this.z[var13] = Math.max(0.0F, this.z[var13] - var1 * this.m[var13]);
      }

      for (int var19 = this.b - 1; var19 >= 0; var19--) {
         if (this.x[var19] >= this.Z[var19] || this.H[var19] > 1.15F || this.H[var19] < -0.1F || this.D[var19] < -0.08F || this.D[var19] > var3 + 0.08F) {
            this.Y(var19);
         }
      }
   }

   void burst(int var1, float var2, float var3, float var4) {
      long var5 = 60954275953190L;
      long var7 = var5 ^ 109463125404803L;

      for (int var9 = 0; var9 < var1; var9++) {
         this.k(var7, var2, var3, 1.0F + var4 * (0.6F + this.E.nextFloat() * 0.9F));
      }
   }

   void quench() {
      for (int var1 = 0; var1 < this.b; var1++) {
         this.Z[var1] = Math.min(this.Z[var1], this.x[var1] + 0.25F + 0.35F * this.u[var1]);
         this.z[var1] = this.z[var1] * 0.25F;
         this.v[var1] = this.v[var1] * 0.6F;
      }
   }

   private void k(long var1, float var3, float var4, float var5) {
      LowFireLoader.ensureSourceClassInitialized();
      long var6 = 93451429582500L;
      if (93451429582500L == 0L) {
         boolean var10000 = false;
      } else {
         int var12 = var6 < 0L ? -1 : 1;
      }

      if (this.b < 96) {
         int var9 = this.b++;
         SplittableRandom var10 = this.E;
         float var11 = 0.35F + 0.65F * var10.nextFloat();
         this.Q[var9] = var11;
         this.D[var9] = var10.nextFloat() * var3;
         this.H[var9] = var10.nextFloat() * Math.max(var4, 0.03F) * 0.55F;
         this.o[var9] = (var10.nextFloat() - 0.5F) * 0.05F;
         this.v[var9] = (0.09F + 0.15F * var10.nextFloat()) * (0.6F + 0.4F * var11) * var5;
         this.x[var9] = 0.0F;
         this.Z[var9] = 1.2F + 1.5F * var10.nextFloat();
         this.X[var9] = (1.3F + 2.1F * var10.nextFloat()) * (0.55F + 0.45F * var11);
         this.u[var9] = var10.nextFloat();
         this.z[var9] = 0.55F + 0.45F * var10.nextFloat();
         this.m[var9] = 0.25F + 0.3F * var10.nextFloat();
         this.q[var9] = this.o[var9];
         this.g[var9] = this.v[var9];
      }
   }

   private void Y(int var1) {
      int var2 = --this.b;
      this.D[var1] = this.D[var2];
      this.H[var1] = this.H[var2];
      this.o[var1] = this.o[var2];
      this.v[var1] = this.v[var2];
      this.x[var1] = this.x[var2];
      this.Z[var1] = this.Z[var2];
      this.X[var1] = this.X[var2];
      this.Q[var1] = this.Q[var2];
      this.u[var1] = this.u[var2];
      this.z[var1] = this.z[var2];
      this.m[var1] = this.m[var2];
      this.q[var1] = this.q[var2];
      this.g[var1] = this.g[var2];
   }

   void write(Std140Builder var1, float var2, float var3) {
      for (int var4 = 0; var4 < 96; var4++) {
         if (var4 >= this.b) {
            var1.putVec4(0.0F, 0.0F, 0.0F, 0.0F).putVec4(0.0F, 0.0F, 0.0F, 0.0F);
         } else {
            float var5 = this.x[var4] / this.Z[var4];
            float var6 = A(0.0F, 0.08F, var5) * (1.0F - A(0.55F, 1.0F, var5));
            float var7 = 0.78F + 0.22F * (float)Math.sin(this.s * (9.0F + 7.0F * this.u[var4]) + this.u[var4] * 23.0F);
            float var8 = var6 * var7 * (0.55F + 0.45F * this.Q[var4]);
            var1.putVec4(this.D[var4] * var2, this.H[var4] * var2, this.X[var4] * var3, var8);
            var1.putVec4(this.q[var4] * var2, this.g[var4] * var2, this.z[var4], this.u[var4]);
         }
      }
   }

   private static float A(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

}
