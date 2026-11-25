package dev.purxiy.modules.mount;

public final class MountUpdater8 {
   private boolean f;
   private MountFactory.Data7 s;
   private int H = 1;
   private float G;
   private float w;
   private float l;
   private float L;
   private float k;
   private float y;
   private float A;
   private float B;
   private float E;
   private float J;
   private float D;
   private float u;
   private float b;
   private float q;
   private float p;
   private float M;
   private float a;
   private float v;
   private float C;
   private float n;
   private float h;
   private float T;
   private float j;
   private float N = -1.0F;
   private float x;
   private float V;
   private static final long c;

   public void enter(MountFactory.Data7 var1) {
      this.reset();
      this.s = var1;
      this.f = true;
   }

   public void leave() {
      this.f = false;
      this.N = -1.0F;
      this.l = 0.0F;
   }

   public void reset() {
      this.f = false;
      this.H = 1;
      this.G = this.w = this.l = this.L = this.k = this.y = this.A = this.B = this.E = this.J = this.D = 0.0F;
      this.u = this.b = this.q = this.p = this.M = this.a = this.v = this.C = this.n = 0.0F;
      this.h = this.T = this.j = this.x = this.V = 0.0F;
      this.N = -1.0F;
   }

   private int p(long var1) {
      MountFactory.Data7.ensureSourceClassInitialized();
      int var3;
      if (this.s == null) {
         var3 = 5;
      } else {
         switch (MountUpdater8.TypeW.X[this.s.kind().ordinal()]) {
            case 1:
               var3 = 1;
               break;
            case 2:
               var3 = 4;
               break;
            default:
               var3 = (int)c;
         }
      }

      return var3;
   }

   public void tick(float var1, boolean var2, MountFactory.Data7 var3, float var4, float var5, float var6, float var7, float var8, boolean var9, float var10) {
      long var11 = 132416352311647L;
      long var13 = var11 ^ 57169112478429L;
      long var15 = var11 ^ 126146596959583L;
      if (Float.isFinite(var1) && !(var1 <= 0.0F)) {
         float var17 = Math.min(0.1F, var1);
         this.f = var2;
         this.s = var3;
         this.a();
         float var18 = Float.isFinite(var4) ? Math.clamp(var4, -1.0F, 1.0F) : 0.0F;
         float var19 = Float.isFinite(var5) ? Math.clamp(var5, 0.0F, 40.0F) : 0.0F;
         float var20 = Float.isFinite(var7) ? Math.clamp(var7, -1.0F, 1.0F) : 0.0F;
         float var21 = Float.isFinite(var8) ? Math.clamp(var8, 0.4F, 5.0F) : 1.6F;
         this.h = Math.max(0.0F, this.h - var17);
         this.T = Math.max(0.0F, this.T - var17);
         this.j = Math.max(0.0F, this.j - var17);
         boolean var22 = var3.hasEngine();
         this.G = V(this.G, this.f ? 1.0F : 0.0F, var17 / (var22 ? 1.05F : 0.25F), var15);
         float var23 = this.f ? Math.abs(var18) : 0.0F;
         if (var23 > 0.01F && this.V != 0.0F && Math.signum(var18) != this.V) {
            this.l = 0.0F;
         }

         if (var23 > 0.01F) {
            this.V = Math.signum(var18);
         }

         if (this.f && var23 > 0.01F && this.G > 0.7F) {
            this.l = Math.min(1.0F, this.l + var17 / var21);
         } else {
            this.l = Math.max(0.0F, this.l - var17 * 2.8F);
         }

         this.w = V(this.w, var23 * D(this.G), var17, var23 > this.w ? 8.0F : 13.0F);
         boolean var24 = this.f && Math.abs(var6) > 0.15F && var18 != 0.0F && Math.signum(var18) != Math.signum(var6);
         this.D = V(this.D, var24 ? 1.0F : 0.0F, var17, var24 ? 16.0F : 11.0F);
         if (this.N >= 0.0F && this.f) {
            this.N += var17;
            float var25 = var3.kind() == MountFactory.TypeK.MOTORBIKE ? 2.4F : 1.15F;
            float var26 = this.N / var25;
            this.B = var26 < 0.26F ? D(var26 / 0.26F) : (var26 < 0.51F ? 1.0F : 1.0F - D((var26 - 0.51F) / 0.49F));
            if (var26 >= 1.0F) {
               this.B = 0.0F;
               this.N = -1.0F;
            }
         } else {
            this.B = V(this.B, 0.0F, var17, 12.0F);
            if (this.B < 0.001F) {
               this.B = 0.0F;
            }

            if (!this.f) {
               this.N = -1.0F;
            }
         }

         float var38 = 5.6F;
         float var39 = var19 / var38;
         float var27 = 0.14F + Math.min(1.0F, var39 / (0.22F + this.H * 0.16F)) * 0.68F + this.w * 0.14F;
         if (!var9 && this.f && var22 && this.G >= 0.99F && this.T <= 0.0F) {
            if (var27 > 0.88F && var39 > 0.12F && this.H < this.p(var13)) {
               this.b(this.H + 1);
            } else if (var27 < 0.32F && this.H > 1) {
               this.b(this.H - 1);
            }
         }

         float var28 = 1.0F - 0.38F * (this.h / 0.25F);
         var27 = (var27 * var28 + (var22 ? this.B * 0.72F : 0.0F)) * D(this.G);
         this.L = V(this.L, this.f ? Math.clamp(var27, 0.0F, 1.0F) : 0.0F, var17, 10.0F);
         float var29 = (var19 - this.x) / var17;
         this.x = var19;
         boolean var30 = var3.bike();
         float var31 = var30 ? 0.024F : 0.006F;
         float var32 = var30 ? 0.075F : 0.02F;
         this.k = V(
            this.k,
            this.f ? Math.clamp(-var29 * var31 * Math.signum(var6 == 0.0F ? 1.0F : var6), -var32, var32) + this.D * (var30 ? 0.05F : 0.012F) : 0.0F,
            var17,
            8.0F
         );
         float var33 = var20 * Math.min(1.0F, var39 * 1.25F);
         this.y = V(this.y, this.f && !var30 ? -var33 * 0.02F : 0.0F, var17, 7.0F);
         float var34 = 0.0F;
         if (this.f && var30) {
            float var35 = Math.max(1.6F, var3.wheelbase() * 1.25F / (float)Math.tan(Math.max(0.02F, Math.toRadians(var3.turnAngle()) * Math.abs(var20))));
            float var36 = (float)Math.atan2(var19 * var19, var35 * 9.8F);
            float var37 = var3.kind() == MountFactory.TypeK.MOTORBIKE ? 0.62F : 0.16F;
            var34 = Math.signum(var20) * Math.min(var37, var36) * (Math.abs(var20) > 0.02F ? 1 : 0);
         }

         this.A = V(this.A, var34, var17, 6.0F);
         float var41 = 170.0F;
         float var42 = 15.0F;
         this.J = this.J + (-var41 * this.E - var42 * this.J) * var17;
         this.J -= var10 * 2.2F * var17 * 20.0F;
         this.E = Math.clamp(this.E + this.J * var17, var30 ? -0.09F : -0.03F, var30 ? 0.06F : 0.025F);
      }
   }

   public boolean shift(int var1) {
      long var2 = 73252957674670L;
      long var4 = var2 ^ 9962806130476L;
      if (this.f && this.s != null && this.s.hasEngine() && !(this.G < 0.8F) && !(this.h > 0.0F) && var1 != 0) {
         int var6 = Math.clamp((long)(this.H + Integer.signum(var1)), 1, this.p(var4));
         if (var6 == this.H) {
            return false;
         } else {
            this.b(var6);
            return true;
         }
      } else {
         return false;
      }
   }

   private void b(int var1) {
      this.H = var1;
      this.h = 0.25F;
      this.T = 0.9F;
   }

   public boolean action() {
      if (this.f && !(this.G < 0.7F) && !(this.j > 0.0F)) {
         this.N = 0.0F;
         this.j = this.s != null && this.s.kind() == MountFactory.TypeK.MOTORBIKE ? 2.6F : 1.5F;
         return true;
      } else {
         return false;
      }
   }

   public float inputScale(boolean var1) {
      if (!this.f) {
         return 1.0F;
      } else {
         float var2;
         if (var1) {
            switch (this.H) {
               case 1:
                  var2 = 0.3F;
                  break;
               case 2:
                  var2 = 0.48F;
                  break;
               case 3:
                  var2 = 0.67F;
                  break;
               case 4:
                  var2 = 0.84F;
                  break;
               default:
                  var2 = 1.0F;
            }
         } else {
            var2 = 1.0F;
         }

         return Math.clamp(
            (0.045F + 0.955F * (float)Math.pow(D(this.l), 1.35)) * var2 * D((this.G - 0.55F) / 0.45F) * (1.0F - 0.32F * this.h / 0.25F), 0.0F, 1.0F
         );
      }
   }

   public int gear() {
      return this.H;
   }

   public float cooldown() {
      return this.j;
   }

   public float startup(float var1) {
      return X(this.u, this.G, var1);
   }

   public float throttle(float var1) {
      return X(this.b, this.w, var1);
   }

   public float rpm(float var1) {
      return X(this.q, this.L, var1);
   }

   public float pitch(float var1) {
      return X(this.p, this.k, var1);
   }

   public float bank(float var1) {
      return X(this.M, this.y, var1);
   }

   public float lean(float var1) {
      return X(this.a, this.A, var1);
   }

   public float stunt(float var1) {
      return X(this.v, this.B, var1);
   }

   public float heave(float var1) {
      return X(this.C, this.E, var1);
   }

   public float brake(float var1) {
      return X(this.n, this.D, var1);
   }

   private void a() {
      this.u = this.G;
      this.b = this.w;
      this.q = this.L;
      this.p = this.k;
      this.M = this.y;
      this.a = this.A;
      this.v = this.B;
      this.C = this.E;
      this.n = this.D;
   }

   private static float X(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * Math.clamp(var2, 0.0F, 1.0F);
   }

   private static float D(float var0) {
      var0 = Math.clamp(var0, 0.0F, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static float V(float var0, float var1, float var2, long var3) {
      MountFactory.Data7.ensureSourceClassInitialized();
      long var5 = 11907238833152L;
      int var7 = var5 < 0L ? -1 : 1;
      return var0 < var1 ? Math.min(var1, var0 + var2) : Math.max(var1, var0 - var2);
   }

   private static float V(float var0, float var1, float var2, float var3) {
      return var0 + (var1 - var0) * (1.0F - (float)Math.exp(-var2 * var3));
   }

   static {
      long var0 = 54659461489290L;
      long var2 = -8368948932251147636L ^ var0;
      c = var2;
   }

   static class TypeW {
      static final int[] X = new int[MountFactory.TypeK.values().length];

      static {
         try {
            X[MountFactory.TypeK.BICYCLE.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
         }

         try {
            X[MountFactory.TypeK.QUAD.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
         }
      }
   }
}
