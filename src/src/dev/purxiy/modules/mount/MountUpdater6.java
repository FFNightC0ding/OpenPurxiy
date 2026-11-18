// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;

public final class MountUpdater6 {
   private static final float M = 12.0F;
   private static final float w = 11.0F;
   private static final float Y = 0.03F;
   private static final float L = 0.06F;
   private float W;
   private float v;
   private float s;
   private float l;
   private float j;
   private float y;
   private float K;
   private float q;
   private final float[] u = new float[4];
   private final float[] r = new float[4];
   private boolean U;
   private boolean d;

   public void reset() {
      this.d = false;
      this.U = false;
      this.W = this.v = this.s = this.l = this.j = this.y = this.K = this.q = 0.0F;

      for (int var1 = 0; var1 < 4; var1++) {
         this.u[var1] = this.r[var1] = 0.0F;
      }
   }

   public void tick(class_310 var1, MountFactory.Data7 var2, class_243 var3, float var4, double var5, boolean var7) {
      this.v = this.W;
      this.l = this.s;
      this.y = this.j;
      this.q = this.K;
      System.arraycopy(this.u, 0, this.r, 0, 4);
      float var8 = 0.05F;
      if (!this.d) {
         this.W = this.v = (float)var5;
         this.d = true;
      }

      double var9 = Math.toRadians(var4);
      double var11 = Math.cos(var9);
      double var13 = Math.sin(var9);
      float[] var15 = new float[4];
      boolean[] var16 = new boolean[4];
      float var17 = 0.0F;
      int var18 = 0;

      for (int var19 = 0; var19 < 4; var19++) {
         MountFactory.Data6 var20 = var2.wheels()[var19];
         if (var20 != null) {
            double var21 = var3.field_1352 + var11 * var20.center().x - var13 * var20.center().z;
            double var23 = var3.field_1350 + var13 * var20.center().x + var11 * var20.center().z;
            class_243 var25 = new class_243(var21, var5 + 1.15, var23);
            class_3965 var26 = var1.field_1687
               .method_17742(new class_3959(var25, var25.method_1031(0.0, -3.4, 0.0), class_3960.field_17558, class_242.field_1348, var1.field_1724));
            if (var26.method_17783() == class_240.field_1332) {
               var15[var19] = (float)var26.method_17784().field_1351;
               var16[var19] = true;
               var17 += var15[var19];
               var18++;
            }
         }
      }

      boolean var38 = var18 == 0;
      float var39 = var38 ? (float)var5 : var17 / var18;

      for (int var40 = 0; var40 < 4; var40++) {
         if (!var16[var40]) {
            var15[var40] = var39;
         }
      }

      boolean var41 = var2.wheels()[1] != null;
      float var22 = var41 ? (var15[0] + var15[1]) / 2.0F : var15[0];
      float var42 = var41 ? (var15[2] + var15[3]) / 2.0F : var15[2];
      float var24 = var41 ? (var15[0] + var15[2]) / 2.0F : var39;
      float var43 = var41 ? (var15[1] + var15[3]) / 2.0F : var39;
      float var44 = (var22 + var42) / 2.0F;
      boolean var27 = var38 || var7 && var5 - var44 > 2.4;
      float var28 = var27 ? (float)var5 : var44;
      if (var27) {
         this.W = var28;
         this.U = true;
      } else {
         this.W = this.W + (var28 - this.W) * (1.0F - (float)Math.exp(-var8 * 12.0F));
         if (Math.abs(var28 - this.W) < 0.002F) {
            this.W = var28;
         }

         if (this.U) {
            this.K = 1.0F;
            this.U = false;
         }
      }

      this.K = Math.max(0.0F, this.K - var8 / 0.34F);
      float var29 = var27 ? 0.0F : (float)Math.atan2(var22 - var42, var2.wheelbase());
      float var30 = !var27 && var41 ? (float)Math.atan2(var24 - var43, Math.max(0.5F, var2.track())) : 0.0F;
      var29 = Math.clamp(var29, -0.3F, 0.3F);
      var30 = Math.clamp(var30, -0.22F, 0.22F);
      float var31 = 1.0F - (float)Math.exp(-var8 * 11.0F);
      this.s = this.s + (var29 - this.s) * var31;
      this.j = this.j + (var30 - this.j) * var31;

      for (int var32 = 0; var32 < 4; var32++) {
         MountFactory.Data6 var33 = var2.wheels()[var32];
         if (var33 != null) {
            float var34 = var33.center().z / Math.max(0.5F, var2.wheelbase() / 2.0F);
            float var35 = var41 ? var33.center().x / Math.max(0.3F, var2.track() / 2.0F) : 0.0F;
            float var36 = var44 + (var22 - var42) / 2.0F * var34 + (var24 - var43) / 2.0F * var35;
            float var37 = var27 ? -0.06F : Math.clamp(var15[var32] - var36, -0.06F, 0.03F);
            this.u[var32] = this.u[var32] + (var37 - this.u[var32]) * (1.0F - (float)Math.exp(-var8 * 26.0F));
         }
      }
   }

   public float y(float var1) {
      return this.v + (this.W - this.v) * Math.clamp(var1, 0.0F, 1.0F);
   }

   public float pitch(float var1) {
      return this.l + (this.s - this.l) * Math.clamp(var1, 0.0F, 1.0F);
   }

   public float roll(float var1) {
      return this.y + (this.j - this.y) * Math.clamp(var1, 0.0F, 1.0F);
   }

   public float travel(int var1, float var2) {
      return this.r[var1] + (this.u[var1] - this.r[var1]) * Math.clamp(var2, 0.0F, 1.0F);
   }

   public float landing(float var1) {
      return this.q + (this.K - this.q) * Math.clamp(var1, 0.0F, 1.0F);
   }

   public boolean airborne() {
      return this.U;
   }

   public boolean ready() {
      return this.d;
   }
}
