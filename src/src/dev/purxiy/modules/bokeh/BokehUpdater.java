// Sourced By DevFFNightmare
package dev.purxiy.modules.bokeh;

import java.util.Arrays;

final class BokehUpdater {
   static final float R = 9.0F;
   static final float u = 0.3F;
   private float Y = -1.0F;
   private float w;

   static float dioptre(float var0) {
      return !(var0 < Float.POSITIVE_INFINITY) ? 0.0F : 1.0F / Math.max(var0, 0.3F);
   }

   float update(float var1, float var2, float var3) {
      if (this.Y < 0.0F) {
         return this.snap(var1);
      } else {
         float var4 = 9.0F * Math.max(var2, 0.05F);
         int var5 = Math.max(1, (int)Math.ceil(var3 * 240.0F));
         float var6 = var3 / var5;

         for (int var7 = 0; var7 < var5; var7++) {
            float var8 = var4 * var4 * (var1 - this.Y) - 2.0F * var4 * this.w;
            this.w += var8 * var6;
            this.Y = this.Y + this.w * var6;
         }

         if (this.Y < 0.0F) {
            this.Y = 0.0F;
            this.w = Math.max(this.w, 0.0F);
         }

         return this.Y;
      }
   }

   float snap(float var1) {
      this.Y = Math.max(var1, 0.0F);
      this.w = 0.0F;
      return this.Y;
   }

   float value() {
      return Math.max(this.Y, 0.0F);
   }

   static float median(float[] var0) {
      Arrays.sort(var0, 0, 5);
      return var0[2];
   }
}
