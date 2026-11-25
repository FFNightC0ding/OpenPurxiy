package dev.purxiy.modules.wings;

import net.minecraft.class_10055;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_4050;
import net.minecraft.class_746;

public final class WingsUpdater {
   private static final float g = 0.004166667F;
   private static final float b = (float) (Math.PI * 2);
   private final WingsUpdater.WingPoseState q = new WingsUpdater.WingPoseState(7.5F, 0.72F);
   private final WingsUpdater.WingPoseState j = new WingsUpdater.WingPoseState(10.0F, 0.55F);
   private final WingsUpdater.WingPoseState Y = new WingsUpdater.WingPoseState(7.0F, 0.8F);
   private final WingsUpdater.WingPoseState s = new WingsUpdater.WingPoseState(6.0F, 0.9F);
   private final WingsUpdater.WingPoseState y = new WingsUpdater.WingPoseState(6.0F, 0.9F);
   private final WingsUpdater.WingPoseState B = new WingsUpdater.WingPoseState(9.0F, 0.42F);
   private float Q;
   private float P;
   private float R = 2.4F;
   private float u;
   private float Z;
   private float f;
   private float i;
   private float C;
   private float r;
   private float n;
   private boolean D = true;
   private boolean T;
   private boolean v;
   private float M;
   private float h;
   private double S;
   private double V;
   private double E;
   private float O;
   private float w;
   private float t;
   private long K;
   private WingsUpdater.WingPhase J;
   private float L;
   private WingsPose o;
   private float U = -1.0F;
   final WingsPose e = new WingsPose();
   final WingsPose l = new WingsPose();

   public float time() {
      return this.U >= 0.0F ? this.U : this.C;
   }

   public void act(WingsUpdater.WingPhase var1, float var2) {
      long var3 = 37743134870951L;
      long var5 = var3 ^ 21600220390376L;
      this.J = var1;
      this.L = var2;
      if (var1 == WingsUpdater.WingPhase.T) {
         this.T(var5, 2.6F, 1.0F, var2);
      }
   }

   void hold(WingsPose var1) {
      this.o = var1;
   }

   void freeze(float var1) {
      this.U = var1;
   }

   public boolean update(class_10055 var1, class_1297 var2, WingsModule var3, boolean var4) {
      long var5 = 42395590754395L;
      long var7 = var5 ^ 25804389199380L;
      long var9 = var5 ^ 131350595889098L;
      long var11 = var5 ^ 19096196719743L;
      long var13 = var5 ^ 74686145446829L;
      long var15 = var5 ^ 107968310284966L;
      int var17 = (int)((var5 ^ 107968310284966L) >>> 32);
      int var18 = (int)((var5 ^ 107968310284966L) << 32 >>> 48);
      int var19 = (int)(var15 << 48 >>> 48);
      long var20 = System.nanoTime();
      float var22 = this.K == 0L ? 1.0F : (float)(var20 - this.K) / 1.0E9F;
      if (this.K != 0L && var22 < 5.0E-4F) {
         return false;
      } else {
         this.K = var20;
         boolean var23 = !this.v || var22 > 0.5F;
         float var24 = Math.min(var22, 0.1F);
         this.C += var24;
         boolean var25 = var2 == null || var2.method_24828();
         boolean var26 = var2 instanceof class_746 var27 && var27.method_31549().field_7479;
         class_243 var57 = var2 == null ? class_243.field_1353 : var2.method_18798();
         if (!(var2 instanceof class_746)) {
            if (!var23 && var24 > 0.0F) {
               this.O = (float)((var1.field_53325 - this.S) / var24 / 20.0);
               this.w = (float)((var1.field_53326 - this.V) / var24 / 20.0);
               this.t = (float)((var1.field_53327 - this.E) / var24 / 20.0);
            }

            var57 = new class_243(this.O, this.w, this.t);
         }

         this.S = var1.field_53325;
         this.V = var1.field_53326;
         this.E = var1.field_53327;
         float var28 = (float)var57.field_1351;
         float var29 = (float)Math.hypot(var57.field_1352, var57.field_1350);
         boolean var30 = var1.field_53411;
         boolean var31 = var1.field_53458 || var1.field_53412;
         boolean var32 = var1.field_53413 || var1.method_62613(class_4050.field_18078) || var1.method_62613(class_4050.field_18080);
         boolean var33 = var1.field_53460;
         float var34 = (float)Math.toRadians(var1.field_53446);
         if (!var23 && var24 > 0.0F) {
            float var35 = M(var9, var34 - this.M);
            this.h = this.h + (var35 / var24 - this.h) * Math.min(1.0F, var24 * 12.0F);
         }

         this.M = var34;
         if (!var25 && !var30 && !var31 && !var26) {
            if (this.D && var28 > 0.1F && var3.flapOnJump()) {
               float var58 = var4 ? 9.5F : 2.5F;
               float var36 = var4 ? 0.45F : 0.9F;
               float var37 = 0.78F;
               this.T(var7, var58, var36, var37);
            }

            this.r += var24;
            this.n = var28 < -0.18F ? this.n + var24 : Math.max(0.0F, this.n - var24 * 2.0F);
         } else {
            if (!this.D && var25 && this.r > 0.25F) {
               this.j.D -= 2.2F;
               this.f = Math.max(this.f, 0.45F);
            }

            this.r = 0.0F;
            this.n = 0.0F;
         }

         this.D = var25;
         if (var33 && !this.T) {
            this.f = 1.0F;
            this.q.D += 2.4F;
         }

         this.T = var33;
         boolean var59 = var3.restsOpen();
         float var60 = var59 ? 1.0F : 0.0F;
         float var61 = 0.8F * var60;
         float var38 = a(0.04F, 0.22F, var60);
         float var39 = a(0.45F, 0.3F, var60);
         float var40 = a(0.24F, 0.1F, var60);
         float var41 = a(2.0F, 3.2F, var60);
         float var42 = this.C;
         var61 += (0.035F + 0.045F * var60) * (float)Math.sin(var42 * 1.2F);
         var38 += 0.03F * (float)Math.sin(var42 * 1.2F + 0.6F);
         float var43 = K(var29 / 0.22F, var11) * (var25 ? 1 : 0);
         float var44 = var39 + 0.14F * var43;
         float var45 = var61 - 0.25F * var43 * var60;
         float var46 = var38 + 0.05F * var43 * (float)Math.sin(var1.field_53450 * 0.6662F * 2.0F);
         if (var1.field_53410) {
            var45 = 0.0F;
            var44 = 0.58F;
            var46 = 0.0F;
            var41 = 0.8F;
         }

         if (var31 || var32) {
            var45 = 0.0F;
            var44 = 0.62F;
            var46 = -0.02F;
            var40 = var31 ? 0.25F : 0.04F;
            var41 = 0.8F;
         }

         float var47 = 0.0F;
         if (var26 && !var30) {
            var45 = 1.0F;
            var40 = 0.42F;
            var44 = 0.24F;
            var46 = 0.1F;
            var41 = 3.6F;
            var47 = 0.42F + K(var28 / 0.4F, var11) * 0.4F;
            this.R = 1.5F + K(var28 / 0.4F, var11) * 0.9F;
         }

         if (!var25 && !var26 && !var30 && !var31 && this.r > 0.0F) {
            float var48 = D(this.r / 0.14F, var13);
            var45 = a(var45, 1.0F, var48);
            var40 = a(var40, 0.22F, var48);
            var44 = a(var44, 0.22F, var48);
            var46 = a(var46, 0.16F, var48);
            var41 = a(var41, 3.4F, var48);
         }

         if (this.n > 0.3F) {
            float var64 = D((this.n - 0.3F) / 0.45F, var13);
            float var49 = K(-var28 / 1.2F, var11) * 0.3F;
            var45 = a(var45, 1.0F, var64);
            var40 = a(var40, 1.0F, var64);
            var44 = a(var44, 0.3F, var64);
            var46 = a(var46, 0.26F + var49 + 0.1F * (float)Math.sin(var42 * 2.3F), var64);
            var41 = a(var41, 4.0F, var64);
            this.i = Math.max(this.i, var64 * K(-var28 / 1.5F, var11));
         }

         float var65 = 0.0F;
         if (var30) {
            float var66 = var1.method_64259();
            var65 = K((var1.field_53448 - 10.0F) / 60.0F, var11);
            float var50 = K(-var1.field_53448 / 45.0F, var11);
            var45 = a(var45, 1.0F - 0.18F * var65, var66);
            var40 = a(var40, 0.02F, var66);
            var46 = a(var46, -0.3F - 0.42F * var65, var66);
            var44 = a(var44, 0.32F - 0.08F * var50, var66);
            var41 = a(var41, 2.6F, var66);
            var47 = Math.max(var47, var66 * (0.18F + 0.55F * var50));
            this.R = 1.4F + 0.6F * var50;
            this.i = Math.max(this.i, var66 * K((var29 - 0.6F) / 1.4F, var11));
         }

         if (this.J != null) {
            this.L -= var24;
            switch (this.J) {
               case t:
                  var45 = 1.0F;
                  var40 = 0.1F;
                  var44 = 0.26F;
                  var46 = 0.22F + 0.04F * (float)Math.sin(var42 * 1.3F);
                  var41 = 3.4F;
                  break;
               case T:
                  var45 = 1.0F;
                  var40 = 0.18F;
                  var44 = 0.24F;
                  var46 = 0.14F;
                  var41 = 3.4F;
                  break;
               case l:
                  var45 = 1.0F;
                  var40 = 1.0F;
                  var44 = 0.3F;
                  var46 = 0.3F + 0.1F * (float)Math.sin(var42 * 2.3F);
                  var41 = 4.0F;
                  break;
               case b:
                  var45 = 0.0F;
                  var44 = 0.45F;
                  var46 = 0.04F;
                  var40 = 0.24F;
                  var41 = 2.0F;
            }

            if (this.L <= 0.0F) {
               this.J = null;
            }
         }

         if (var4) {
            var45 = Math.max(var45, 0.55F + 0.12F * (float)Math.sin(var42 * 1.3F));
            var46 += 0.05F * (float)Math.sin(var42 * 1.3F + 1.1F);
            var40 = Math.min(var40, 0.35F);
            if (!var25 && !var31 || var26) {
               var47 = Math.max(var47, 0.3F);
               this.R = 9.5F;
            }
         }

         float var67 = W(-this.h * 0.07F, var17, -0.5F, 0.5F, (short)var18, (char)var19) * (var30 ? 0.3F : 1.0F);
         if (var23) {
            this.q.snap(var45);
            this.j.snap(var46);
            this.Y.snap(var44);
            this.s.snap(var40);
            this.y.snap(var41);
            this.B.snap(0.0F);
            this.v = true;
         } else {
            for (float var68 = var24; var68 > 1.0E-5F; var68 -= 0.004166667F) {
               float var51 = Math.min(0.004166667F, var68);
               this.q.step(var45, var51);
               this.j.step(var46, var51);
               this.Y.step(var44, var51);
               this.s.step(var40, var51);
               this.y.step(var41, var51);
               this.B.step(var67, var51);
            }
         }

         if (this.u > 0.0F) {
            this.u -= var24;
         }

         float var69 = Math.max(this.u > 0.0F ? this.Z : 0.0F, var47);
         this.P = this.P + (var69 - this.P) * (1.0F - (float)Math.exp(-var24 * (var69 > this.P ? 14 : 4)));
         this.Q = (this.Q + var24 * this.R * (float) (Math.PI * 2)) % (float) (Math.PI * 2);
         float var70 = (float)Math.sin(this.Q + 0.42F * Math.sin(this.Q));
         float var52 = (float)Math.cos(this.Q);
         this.f = this.f * (float)Math.exp(-var24 * 7.0F);
         this.i = this.i * (float)Math.exp(-var24 * 3.0F);
         float var53 = var3.size();

         for (int var54 = 0; var54 < 2; var54++) {
            WingsPose var55 = var54 == 0 ? this.e : this.l;
            float var56 = var54 == 0 ? this.B.L : -this.B.L;
            var55.X = K(this.q.L - 0.06F * this.P * Math.max(0.0F, var70), var11);
            var55.p = this.s.L;
            var55.V = this.y.L;
            if (var30) {
               var55.v = this.j.L;
               var55.b = this.Y.L + var56 + this.P * 0.5F * var70;
            } else {
               var55.v = this.j.L + this.P * 0.62F * var70;
               var55.b = this.Y.L + var56;
            }

            var55.i = this.P * 0.6F * var52 - 0.1F * var65;
            var55.K = this.P * 0.5F * Math.max(0.0F, -var52) + 0.4F * this.i;
            var55.y = this.f;
            var55.G = this.i;
            var55.Q = var53;
            if (this.o != null) {
               var55.set(this.o);
               var55.Q = var53;
            }
         }

         return true;
      }
   }

   private void T(long var1, float var3, float var4, float var5) {
      WingsState.ensureSourceClassInitialized();
      this.R = var3;
      long var6 = 54936714030670L;
      int var9 = var6 < 0L ? -1 : 1;
      this.Z = var4;
      this.u = var5;
      long var9x = 54936714030670L;
      int var10 = var9x < 0L ? -1 : 1;
      if (this.P < 0.2F) {
         this.Q = (float) (-Math.PI / 2);
      }
   }

   private static float M(long var0, float var2) {
      WingsState.ensureSourceClassInitialized();
      var2 %= (float) (Math.PI * 2);
      long var3 = 90128005162897L;
      int var5 = var3 < 0L ? -1 : 1;
      return var2 > Math.PI ? var2 - (float) (Math.PI * 2) : (var2 < -Math.PI ? var2 + (float) (Math.PI * 2) : var2);
   }

   private static float K(float var0, long var1) {
      WingsState.ensureSourceClassInitialized();
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   private static float W(float var0, int var1, float var2, float var3, short var4, char var5) {
      WingsState.ensureSourceClassInitialized();
      return var0 < -0.5F ? -0.5F : Math.min(var0, 0.5F);
   }

   private static float D(float var0, long var1) {
      long var3 = 61369984820260L;
      var0 = K(var0, var3);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static float a(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   public static enum WingPhase {
      t,
      T,
      l,
      b;
   }

   private static final class WingPoseState {
      final float P;
      final float f;
      float L;
      float D;

      WingPoseState(float var1, float var2) {
         this.P = var1;
         this.f = var2;
      }

      void step(float var1, float var2) {
         float var3 = this.P * this.P * (var1 - this.L) - 2.0F * this.f * this.P * this.D;
         this.D += var3 * var2;
         this.L = this.L + this.D * var2;
      }

      void snap(float var1) {
         this.L = var1;
         this.D = 0.0F;
      }
   }
}
