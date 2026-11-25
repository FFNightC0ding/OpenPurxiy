package dev.purxiy.modules.skins3d;

final class Skins3DUpdater2 {
   static final double J = 0.205;
   static final double I = 0.27;
   static final double n = 0.075;
   double T;
   double L;
   double Z;
   double o;
   double K;
   double G;
   float E;
   float r;
   float M;
   boolean A;
   int b;
   transient double z;
   transient double v;
   transient double d;
   transient float t;
   transient float Q;
   transient float i;

   Skins3DUpdater2(double var1, double var3, double var5, double var7, double var9, double var11, float var13) {
      this.T = var1;
      this.L = var3;
      this.Z = var5;
      this.o = var7;
      this.K = var9;
      this.G = var11;
      this.E = var13;
      this.r = -0.6F;
      this.snapshot();
   }

   void snapshot() {
      this.z = this.T;
      this.v = this.L;
      this.d = this.Z;
      this.t = this.E;
      this.Q = this.r;
      this.i = this.M;
   }

   void tick(Skins3DUpdater2.Listener var1) {
      this.snapshot();
      if (this.A) {
         if (var1.move(this.T, this.L, this.Z, 0.0, -0.025, 0.0).y() > -0.024) {
            return;
         }

         this.A = false;
         this.b = 1;
      }

      this.K -= 0.035;
      Skins3DUpdater2.Data var2 = var1.move(this.T, this.L, this.Z, this.o, this.K, this.G);
      this.T = this.T + var2.x();
      this.L = this.L + var2.y();
      this.Z = this.Z + var2.z();
      boolean var3 = this.K < 0.0 && Math.abs(var2.y() - this.K) > 1.0E-7;
      if (Math.abs(var2.x() - this.o) > 1.0E-7) {
         this.o *= -0.14;
      }

      if (Math.abs(var2.z() - this.G) > 1.0E-7) {
         this.G *= -0.14;
      }

      if (Math.abs(var2.y() - this.K) > 1.0E-7) {
         this.K = var3 && this.b++ == 0 ? Math.min(0.075, -this.K * 0.15) : 0.0;
      }

      this.o *= var3 ? 0.5 : 0.98;
      this.K *= 0.98;
      this.G *= var3 ? 0.5 : 0.98;
      if (var3) {
         this.r *= 0.35F;
         this.M *= 0.35F;
         if (this.K == 0.0 && this.o * this.o + this.G * this.G < 1.0E-4) {
            this.A = true;
            this.o = this.K = this.G = 0.0;
            this.r = this.M = 0.0F;
         }
      } else {
         this.E += 0.12F;
         this.r += 0.18F;
         this.M += 0.11F;
      }
   }

   boolean valid() {
      long var1 = 43168555836342L;
      long var3 = var1 ^ 100724498179684L;
      return E(this.T, 3.0E7, var3)
         && E(this.L, 3.0E7, var3)
         && E(this.Z, 3.0E7, var3)
         && E(this.o, 4.0, var3)
         && E(this.K, 8.0, var3)
         && E(this.G, 4.0, var3)
         && Float.isFinite(this.E)
         && Float.isFinite(this.r)
         && Float.isFinite(this.M)
         && this.b >= 0;
   }

   private static boolean E(double var0, double var2, long var4) {
      SkinPreset.ensureSourceClassInitialized();
      return Double.isFinite(var0) && Math.abs(var0) <= var2;
   }

   record Data(double H, double P, double L) {
      public double x() {
         return this.H;
      }

      public double y() {
         return this.P;
      }

      public double z() {
         return this.L;
      }

      public double doubleState17() {
         return this.H;
      }

      public double doubleState40() {
         return this.P;
      }

      public double doubleState3() {
         return this.L;
      }
   }

   @FunctionalInterface
   interface Listener {
      Skins3DUpdater2.Data move(double var1, double var3, double var5, double var7, double var9, double var11);
   }
}
