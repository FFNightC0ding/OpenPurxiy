// Sourced By DevFFNightmare
package dev.purxiy.modules.hand;

final class HandMovementSupport {
   static final float j = 0.2F;
   private static final int h;
   private final double[] b = new double[4];
   private final float[] Z = new float[4];
   private final SwingStyle[] s = new SwingStyle[4];
   private final float[] B = new float[4];
   private int u;
   private final SwingStyle.TypeJ G = new SwingStyle.TypeJ();

   void push(double var1, float var3, SwingStyle var4, float var5) {
      for (int var6 = 3; var6 > 0; var6--) {
         this.b[var6] = this.b[var6 - 1];
         this.Z[var6] = this.Z[var6 - 1];
         this.s[var6] = this.s[var6 - 1];
         this.B[var6] = this.B[var6 - 1];
      }

      this.b[0] = var1;
      this.Z[0] = var3;
      this.s[0] = var4;
      this.B[0] = var5;
      this.u = Math.min(4, this.u + 1);
   }

   double lastStart() {
      return this.u == 0 ? Double.NEGATIVE_INFINITY : this.b[0];
   }

   float lastDuration() {
      return this.u == 0 ? 0.0F : this.Z[0];
   }

   boolean within(double var1, float var3) {
      return this.u > 0 && var1 - this.b[0] < this.Z[0] + var3;
   }

   void evaluate(double var1, boolean var3, SwingStyle.TypeJ var4) {
      long var5 = 88990414937347L;
      long var7 = var5 ^ 93624102551717L;
      int var9 = 0;

      while (var9 < this.u && this.b[var9] > var1) {
         var9++;
      }

      if (var9 >= this.u) {
         var4.rest(45.0F);
      } else {
         float var10 = (float)((var1 - this.b[var9]) / this.Z[var9]);
         if (var10 >= 1.0F) {
            var4.rest(this.s[var9].restBase(var3));
         } else {
            this.W(var9, var10, var3, var4, var7);
            int var11 = var9 + 1;
            if (var11 < this.u) {
               float var12 = (float)((this.b[var9] - this.b[var11]) / this.Z[var11]);
               if (var12 < 1.0F) {
                  float var13 = Math.max(0.02F, 0.2F * this.Z[var9]);
                  float var14 = (float)((var1 - this.b[var9]) / var13);
                  if (var14 < 1.0F) {
                     this.W(var11, var12 + (1.0F - var12) * var14, var3, this.G, var7);
                     float var15 = var14 * var14 * (3.0F - 2.0F * var14);
                     var4.set(this.G.lerp(var4, var15));
                  }
               }
            }
         }
      }
   }

   private void W(int var1, float var2, boolean var3, SwingStyle.TypeJ var4, long var5) {
      SwingStyle.ensureSourceClassInitialized();
      long var7 = 6361877454245L;
      if (6361877454245L == 0L) {
         boolean var10000 = false;
      } else {
         int var14 = var7 < 0L ? -1 : 1;
      }

      if (var3) {
         SwingStyle var10 = this.s[var1];
         long var11 = 6361877454246L;
         if (6361877454246L == 0L) {
            boolean var15 = false;
         } else {
            int var16 = var11 < 0L ? -1 : 1;
         }

         var10.arm(var2, this.B[var1], var4);
      } else {
         this.s[var1].item(var2, this.B[var1], var4);
      }
   }

   static {
      long var0 = 108503762131110L;
      long var2 = -3837402581455152990L ^ var0;
      h = (int)var2;
   }
}
