// Sourced By DevFFNightmare
package dev.purxiy.modules.trail;

final class TrailSupport3 {
   static final int Q = 2048;
   static final double M = 0.08;
   static final double k = 8.0;
   static final int p = 96;
   private final double[] J = new double[2048];
   private final double[] U = new double[2048];
   private final double[] B = new double[2048];
   private final double[] X = new double[2048];
   private final double[] G = new double[2048];
   private final boolean[] T = new boolean[2048];
   private int e;
   private int R;
   private boolean Z;
   private double H;
   private double I;
   private double u;
   private double K;



   void follow(double var1, double var3, double var5, double var7) {
      long var9 = 68585477040804L;
      long var11 = var9 ^ 106323016293716L;
      long var13 = var9 ^ 59283430830583L;
      if (!this.Z) {
         this.Z = true;
         this.M(var1, var3, var5, var7);
         this.s(var1, var13, var3, var5, var7, 0.0, true);
      } else {
         double var15 = var1 - this.H;
         double var17 = var3 - this.I;
         double var19 = var5 - this.u;
         double var21 = Math.sqrt(var15 * var15 + var17 * var17 + var19 * var19);
         if (var21 > 8.0) {
            this.M(var1, var3, var5, var7);
            this.s(var1, var13, var3, var5, var7, this.odo(this.R - 1), true);
         } else {
            if (var21 > 1.0E-4) {
               this.M(var1, var3, var5, var7);
            }

            int var23 = this.p(this.R - 1, var11);
            double var24 = this.J[var23];
            double var26 = this.U[var23];
            double var28 = this.B[var23];
            double var30 = this.X[var23];
            double var32 = this.G[var23];
            double var34 = var1 - var24;
            double var36 = var3 - var26;
            double var38 = var5 - var28;
            double var40 = Math.sqrt(var34 * var34 + var36 * var36 + var38 * var38);
            if (!(var40 < 0.08)) {
               int var42 = (int)Math.min(var40 / 0.08, 96.0);
               double var43 = var42 == 96 ? var40 / var42 : 0.08;

               for (int var45 = 1; var45 <= var42; var45++) {
                  double var46 = var45 * var43 / var40;
                  this.s(
                     var24 + var34 * var46, var13, var26 + var36 * var46, var28 + var38 * var46, var30 + (var7 - var30) * var46, var32 + var45 * var43, false
                  );
               }
            }
         }
      }
   }

   void trim(double var1, double var3) {
      long var5 = 98030711779440L;
      long var7 = var5 ^ 8774628689792L;

      while (this.R > 1 && var1 - this.X[this.p(1, var7)] > var3) {
         this.e = (this.e + 1) % 2048;
         this.R--;
      }
   }

   boolean visible(double var1, double var3) {
      return this.Z && var1 - this.K < var3;
   }

   void clear() {
      this.e = 0;
      this.R = 0;
      this.Z = false;
   }

   private void M(double var1, double var3, double var5, double var7) {
      this.H = var1;
      this.I = var3;
      this.u = var5;
      this.K = var7;
   }

   private void s(double var1, long var3, double var5, double var7, double var9, double var11, boolean var13) {
      long var14 = 104262703052784L;
      TrailRenderer.ensureSourceClassInitialized();
      if (this.R == 2048) {
         this.e = (this.e + 1) % 2048;
         this.R--;
      }

      int var16 = this.p(this.R, var14);
      this.J[var16] = var1;
      this.U[var16] = var5;
      this.B[var16] = var7;
      this.X[var16] = var9;
      this.G[var16] = var11;
      this.T[var16] = var13 || this.R == 0;
      this.R++;
   }

   private int p(int var1, long var2) {
      return (this.e + var1) % 2048;
   }

   int count() {
      return this.R;
   }

   double x(int var1) {
      long var2 = 83878743276071L;
      long var4 = var2 ^ 20452765502935L;
      return this.J[this.p(var1, var4)];
   }

   double y(int var1) {
      long var2 = 4846896623305L;
      long var4 = var2 ^ 99759422298425L;
      return this.U[this.p(var1, var4)];
   }

   double z(int var1) {
      long var2 = 139016181792342L;
      long var4 = var2 ^ 35994900548006L;
      return this.B[this.p(var1, var4)];
   }

   double time(int var1) {
      long var2 = 72379071226676L;
      long var4 = var2 ^ 34117571089604L;
      return this.X[this.p(var1, var4)];
   }

   double odo(int var1) {
      long var2 = 23269438099263L;
      long var4 = var2 ^ 83538656852175L;
      return this.G[this.p(var1, var4)];
   }

   boolean runStart(int var1) {
      long var2 = 29584684942935L;
      long var4 = var2 ^ 75023243276711L;
      return this.T[this.p(var1, var4)];
   }

   boolean hasHead() {
      return this.Z;
   }

   double headX() {
      return this.H;
   }

   double headY() {
      return this.I;
   }

   double headZ() {
      return this.u;
   }

   double headTime() {
      return this.K;
   }

   double headOdo() {
      long var1 = 22556447135904L;
      long var3 = var1 ^ 81708940799824L;
      if (this.R == 0) {
         return 0.0;
      } else {
         int var5 = this.p(this.R - 1, var3);
         double var6 = this.H - this.J[var5];
         double var8 = this.I - this.U[var5];
         double var10 = this.u - this.B[var5];
         return this.G[var5] + Math.sqrt(var6 * var6 + var8 * var8 + var10 * var10);
      }
   }

}
