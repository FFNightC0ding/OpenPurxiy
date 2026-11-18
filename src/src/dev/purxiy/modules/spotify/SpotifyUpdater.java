// Sourced By DevFFNightmare
package dev.purxiy.modules.spotify;

public final class SpotifyUpdater {
   private static final double G = 400.0;
   private static final double Y = 4.0;
   private static final double O = 1500.0;
   private static final long h;
   private boolean E;
   private double D;
   private long A;
   private double F;
   private long M;
   private boolean j;
   private double N = Double.NaN;
   private long S;

   public void report(double var1, boolean var3, long var4) {
      if (!Double.isNaN(this.N)) {
         if (var4 < this.S && Math.abs(var1 - this.N) > 1500.0) {
            this.j = var3;
            return;
         }

         this.N = Double.NaN;
      }

      this.F = var1;
      this.M = var4;
      this.j = var3;
   }

   public void seek(double var1, long var3) {
      this.N = var1;
      this.S = var3 + 2500000000L;
      this.F = var1;
      this.M = var3;
      this.D = var1;
      this.A = var3;
      this.E = true;
   }

   public void reset() {
      this.E = false;
      this.N = Double.NaN;
   }

   public boolean playing() {
      return this.j;
   }

   public double update(long var1) {
      double var3 = this.F + (this.j ? Math.max(0L, var1 - this.M) / 1000000.0 : 0.0);
      if (this.E && this.j) {
         double var5 = Math.max(0L, var1 - this.A) / 1000000.0;
         double var7 = this.D + var5;
         double var9 = var3 - var7;
         if (Math.abs(var9) > 400.0) {
            this.D = var3;
         } else {
            double var11 = Math.min(1.0, 4.0 * var5 / 1000.0);
            this.D = Math.max(this.D, var7 + var9 * var11);
         }

         this.A = var1;
         return this.D;
      } else {
         this.E = true;
         this.D = var3;
         this.A = var1;
         return var3;
      }
   }

   static {
      long var0 = 21601037513190L;
      long var2 = 21603537127654L ^ var0;
      h = var2;
   }
}
