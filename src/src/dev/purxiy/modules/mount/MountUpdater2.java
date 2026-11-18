// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

public final class MountUpdater2 {
   private float O;
   private float g;
   private float J;
   private float b;
   private float W;
   private float F;
   private float i;
   private float o;
   private final float[] M = new float[4];
   private final float[] S = new float[4];
   private float c;
   private float B;

   public void reset(float var1) {
      this.O = this.g = Float.isFinite(var1) ? var1 : 0.0F;
      this.J = this.b = this.W = this.F = this.i = this.o = 0.0F;

      for (int var2 = 0; var2 < 4; var2++) {
         this.M[var2] = this.S[var2] = 0.0F;
      }

      this.c = this.B = 0.0F;
   }

   public void heading(float var1) {
      this.O = this.g = Float.isFinite(var1) ? var1 : 0.0F;
      this.J = this.b = 0.0F;
   }

   public void tick(float var1, double var2, double var4, float var6, boolean var7, boolean var8, MountFactory.Data7 var9) {
      long var10 = 62592228471561L;
      long var12 = var10 ^ 2482096831261L;
      int var14 = (int)((var10 ^ 2482096831261L) >>> 32);
      int var15 = (int)((var10 ^ 2482096831261L) << 32 >>> 48);
      int var16 = (int)(var12 << 48 >>> 48);
      if (Float.isFinite(var1) && !(var1 <= 0.0F)) {
         float var17 = Math.min(var1, 0.1F);
         this.g = this.O;
         this.b = this.J;
         this.F = this.W;
         this.B = this.c;
         System.arraycopy(this.M, 0, this.S, 0, 4);
         double var18 = Math.hypot(var2, var4);
         if (Double.isFinite(var18) && !(var18 > 8.0)) {
            this.W = Math.min(40.0F, (float)var18 / var17);
            double var20 = Math.toRadians(this.O);
            this.i = Math.clamp((float)(-Math.sin(var20) * var2 + Math.cos(var20) * var4), -40.0F * var17, 40.0F * var17);
            float var22 = var8 && Float.isFinite(var6) ? Math.clamp(var6, -1.0F, 1.0F) : 0.0F;
            this.J = this.J + (var22 - this.J) * (1.0F - (float)Math.exp(-var17 * 11.0F));
            if (Math.abs(this.J) < 1.0E-4F) {
               this.J = 0.0F;
            }

            float var23 = this.J * C(var9);
            this.o = (float)Math.atan(0.5 * Math.tan(var23));
            if (var7 && var8 && Math.abs(this.i) > 0.001F) {
               float var24 = Math.max(1.1F, N(var9, var14, var15, (char)var16));
               this.O = this.O - (float)Math.toDegrees(this.i * Math.cos(this.o) / var24 * Math.tan(var23));
            }

            for (int var26 = 0; var26 < 4; var26++) {
               MountFactory.Data6 var25 = var9.wheels()[var26];
               if (var25 != null) {
                  this.M[var26] = W(this.M[var26] + this.i / var25.radius());
               }
            }

            if (var9.crank() != null) {
               this.c = W(this.c + this.i / var9.wheels()[2].radius() * var9.crank().ratio());
            }
         } else {
            this.W = this.i = 0.0F;
         }
      }
   }

   private static float N(MountFactory.Data7 var0, int var1, int var2, char var3) {
      MountFactory.Data7.ensureSourceClassInitialized();
      return var0.bike() ? var0.wheelbase() * 1.25F : Math.min(var0.wheelbase(), 2.75F);
   }

   private static float C(MountFactory.Data7 var0) {
      return (float)Math.toRadians(var0.turnAngle());
   }

   private static float W(float var0) {
      float var1 = (float) (Math.PI * 2);
      return var0 % var1;
   }

   public MountUpdater2.Data input(float var1, float var2) {
      if (Float.isFinite(var1) && Float.isFinite(var2)) {
         double var3 = Math.toRadians(MountUpdater5.wrap(this.O - (float)Math.toDegrees(this.o) - var2));
         return new MountUpdater2.Data((float)(-Math.sin(var3) * var1), (float)(Math.cos(var3) * var1));
      } else {
         return new MountUpdater2.Data(0.0F, 0.0F);
      }
   }

   public float yaw(float var1) {
      return O(this.g, this.O, var1);
   }

   public float steering(float var1) {
      return O(this.b, this.J, var1);
   }

   public float speedKmh(float var1) {
      return O(this.F, this.W, var1) * 3.6F;
   }

   public float speed() {
      return this.W;
   }

   public float signedDistance() {
      return this.i;
   }

   public float spin(int var1, float var2) {
      long var3 = 77187113614353L;
      long var5 = var3 ^ 24700958497734L;
      return u(this.S[var1], var5, this.M[var1], var2);
   }

   public float crank(float var1) {
      long var2 = 87705264063961L;
      long var4 = var2 ^ 34638319559182L;
      return u(this.B, var4, this.c, var1);
   }

   public float angleRadians(MountFactory.Data7 var1, float var2) {
      return this.steering(var2) * (float)Math.toRadians(var1.steerVisual());
   }

   private static float O(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * Math.clamp(var2, 0.0F, 1.0F);
   }

   private static float u(float var0, long var1, float var3, float var4) {
      MountFactory.Data7.ensureSourceClassInitialized();
      float var5 = (float) (Math.PI * 2);
      float var6 = (var3 - var0) % var5;
      if (var6 > Math.PI) {
         var6 -= var5;
      }

      if (var6 < -Math.PI) {
         var6 += var5;
      }

      return var0 + var6 * Math.clamp(var4, 0.0F, 1.0F);
   }

   public record Data(float J, float b) {
      public float sideways() {
         return this.J;
      }

      public float forward() {
         return this.b;
      }

      public float floatState10() {
         return this.J;
      }

      public float floatState32() {
         return this.b;
      }
   }
}
