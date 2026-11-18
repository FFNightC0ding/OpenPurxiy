// Sourced By DevFFNightmare
package dev.purxiy.modules.zoom;

public final class ZoomWidget {
   public static final double A = 1.25;
   public static final double e = 32.0;
   private boolean n;
   private boolean c;
   private double Z = 4.0;
   private double C;
   private double v;
   public static boolean O;

   public boolean active() {
      return this.c;
   }

   public double factor() {
      return Math.exp(this.C);
   }

   public double targetFactor() {
      return this.Z;
   }

   public void press(boolean var1, double var2) {
      long var4 = 40494944915352L;
      long var6 = var4 ^ 41370374026186L;
      if (!this.n) {
         this.n = true;
         this.c = !var1 || !this.c;
         if (this.c) {
            this.Z = i(var6, var2);
         }
      }
   }

   public void release(boolean var1) {
      this.n = false;
      if (!var1) {
         this.c = false;
      }
   }

   public void cancel() {
      this.c = this.n = false;
   }

   public void reset() {
      this.cancel();
      this.C = this.v = 0.0;
   }

   public void scroll(double var1, double var3) {
      long var5 = 41337190915127L;
      long var7 = var5 ^ 40598919225445L;
      if (this.c && Double.isFinite(var1) && Double.isFinite(var3)) {
         this.Z = i(var7, this.Z * Math.exp(Math.max(-32.0, Math.min(32.0, var1 * Math.log1p(var3)))));
      }
   }

   public void advance(double var1, double var3) {
      double var5 = this.c ? Math.log(this.Z) : 0.0;
      if (var3 <= 0.0) {
         this.C = var5;
         this.v = 0.0;
      } else if (Double.isFinite(var1) && !(var1 <= 0.0)) {
         double var7 = 6.64 / var3;
         double var9 = this.C - var5;
         double var11 = this.v + var7 * var9;
         double var13 = Math.exp(-var7 * var1);
         this.C = var5 + (var9 + var11 * var1) * var13;
         this.v = (this.v - var7 * var11 * var1) * var13;
         if (this.C < 0.0 || this.C > Math.log(32.0)) {
            this.C = Math.max(0.0, Math.min(Math.log(32.0), this.C));
            this.v = 0.0;
         }

         if (Math.abs(this.C - var5) < 1.0E-5 && Math.abs(this.v) < 1.0E-4) {
            this.C = var5;
            this.v = 0.0;
         }
      }
   }

   public static float zoomFov(float var0, double var1) {
      return var1 <= 1.0 ? var0 : (float)Math.toDegrees(2.0 * Math.atan(Math.tan(Math.toRadians(var0) / 2.0) / var1));
   }

   private static double i(long var0, double var2) {
      ZoomRenderer.ensureSourceClassInitialized();
      return Double.isFinite(var2) ? Math.max(1.25, Math.min(32.0, var2)) : 4.0;
   }
}
