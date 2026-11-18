// Sourced By DevFFNightmare
package dev.purxiy.modules.bokeh;

final class BokehSupport {
   static final float F = 1.428F;

   private BokehSupport() {
   }

   static float maxBlur(int var0) {
      return 0.036F * var0;
   }

   static float focalPixels(int var0, float var1) {
      return var0 * 0.5F * 1.428F * (float)Math.sqrt(Math.max(var1, 0.05F) / 1.428F);
   }

   static float aperture(float var0) {
      return 0.03F + 0.3F * (float)Math.pow(Math.max(var0, 0.0F), 1.6);
   }

   static float autoScale(float var0, int var1, float var2) {
      return 0.5F * aperture(var0) * focalPixels(var1, var2);
   }

   static float distanceBlur(float var0, int var1) {
      return maxBlur(var1) * (0.15F + 0.55F * var0);
   }

   static float tiltBlur(float var0, int var1) {
      return maxBlur(var1) * (0.35F + 0.65F * var0);
   }

   static int gridFactor(int var0) {
      return Math.clamp((long)Math.round(var0 / 520.0F), 2, 4);
   }

   static float coc(float var0, float var1, float var2, float var3, float var4) {
      float var5 = var0 * (var1 - 1.0F / var2);
      if (var5 < 0.0F) {
         var5 *= var3;
      }

      return Math.clamp(var5, -var4, var4);
   }
}
