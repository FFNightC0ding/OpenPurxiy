// Sourced By DevFFNightmare
package dev.purxiy.modules.auras;

final class AuraSupport3 implements AuraRenderer2.Listener {
   private static final int g = 512;
   private static final double T = 0.004;
   private static final double C = 4.0;
   static final float o = 1.1F;
   static final float u = 0.7F;
   private final double[] t = new double[512];
   private final double[] D = new double[512];
   private final double[] H = new double[512];
   private final double[] s = new double[512];
   private int L = -1;
   private int l;
   private double V;
   private double S;
   private double K;
   private float x = 1.8F;
   private float z = 1.0F;
   private boolean i;
   float k;
   boolean N;



   void follow(double var1, double var3, double var5, double var7, float var9, float var10, float var11) {
      long var12 = 76304877844059L;
      long var14 = var12 ^ 54498749369583L;
      if (this.l > 0) {
         int var16 = this.L;
         double var17 = var3 - this.D[var16];
         double var19 = var5 - this.H[var16];
         double var21 = var7 - this.s[var16];
         if (var17 * var17 + var19 * var19 + var21 * var21 > 16.0) {
            this.l = 0;
         }
      }

      if (this.l >= 2 && var1 - this.t[this.q(this.L, var14)] < 0.004) {
         this.u(this.L, var1, var3, var5, var7);
      } else {
         this.L = (this.L + 1) % 512;
         this.l = Math.min(this.l + 1, 512);
         this.u(this.L, var1, var3, var5, var7);
      }

      if (!this.i) {
         this.i = true;
         this.x = var9;
         this.z = var10;
      } else {
         float var23 = 1.0F - (float)Math.exp(-var11 * 10.0F);
         this.x = this.x + (var9 - this.x) * var23;
         this.z = this.z + (var10 - this.z) * var23;
      }
   }

   void come(boolean var1, float var2) {
      this.k = var1 ? Math.min(1.0F, this.k + var2 / 1.1F) : Math.max(0.0F, this.k - var2 / 0.7F);
   }

   void from(double var1, double var3, double var5) {
      this.V = var1;
      this.S = var3;
      this.K = var5;
   }

   private void u(int var1, double var2, double var4, double var6, double var8) {
      this.t[var1] = var2;
      this.D[var1] = var4;
      this.H[var1] = var6;
      this.s[var1] = var8;
   }

   private int q(int var1, long var2) {
      return (var1 - 1 + 512) % 512;
   }

   @Override
   public void feet(float var1, float[] var2) {
      if (this.l == 0) {
         var2[0] = var2[1] = var2[2] = 0.0F;
      } else {
         int var3 = this.L;
         double var4 = this.t[var3] - var1;
         if (!(var1 <= 0.0F) && this.l != 1 && !(var4 >= this.t[var3])) {
            int var6 = (this.L - this.l + 1 + 512) % 512;
            if (var4 <= this.t[var6]) {
               this.x(var6, var6, 0.0, var2);
            } else {
               int var7 = 0;
               int var8 = this.l - 1;

               while (var8 - var7 > 1) {
                  int var9 = var7 + var8 >>> 1;
                  if (this.t[(var6 + var9) % 512] <= var4) {
                     var7 = var9;
                  } else {
                     var8 = var9;
                  }
               }

               int var13 = (var6 + var7) % 512;
               int var10 = (var6 + var8) % 512;
               double var11 = this.t[var10] - this.t[var13];
               this.x(var13, var10, var11 > 1.0E-9 ? (var4 - this.t[var13]) / var11 : 1.0, var2);
            }
         } else {
            this.x(var3, var3, 0.0, var2);
         }
      }
   }

   private void x(int var1, int var2, double var3, float[] var5) {
      var5[0] = (float)(this.D[var1] + (this.D[var2] - this.D[var1]) * var3 - this.V);
      var5[1] = (float)(this.H[var1] + (this.H[var2] - this.H[var1]) * var3 - this.S);
      var5[2] = (float)(this.s[var1] + (this.s[var2] - this.s[var1]) * var3 - this.K);
   }

   @Override
   public float height() {
      return this.x;
   }

   @Override
   public float width() {
      return this.z;
   }

   void forget() {
      this.l = 0;
      this.L = -1;
   }

   double span() {
      return this.l < 2 ? 0.0 : this.t[this.L] - this.t[(this.L - this.l + 1 + 512) % 512];
   }

}
