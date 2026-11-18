// Sourced By DevFFNightmare
package dev.purxiy.anim;

@FunctionalInterface
public interface Easing {
   Easing LINEAR = var0 -> var0;
   Easing SMOOTHSTEP = var0 -> var0 * var0 * (3.0F - 2.0F * var0);
   Easing QUAD_OUT = var0 -> 1.0F - (1.0F - var0) * (1.0F - var0);
   Easing CUBIC_OUT = var0 -> 1.0F - cube(1.0F - var0);
   Easing CUBIC_IN_OUT = var0 -> var0 < 0.5F ? 4.0F * var0 * var0 * var0 : 1.0F - cube(-2.0F * var0 + 2.0F) / 2.0F;
   Easing QUINT_OUT = var0 -> 1.0F - (float)Math.pow(1.0F - var0, 5.0);
   Easing EXPO_OUT = var0 -> var0 >= 1.0F ? 1.0F : 1.0F - (float)Math.pow(2.0, -10.0F * var0);
   Easing EXPO_IN_OUT = var0 -> var0 <= 0.0F
      ? 0.0F
      : (var0 >= 1.0F ? 1.0F : (var0 < 0.5F ? (float)Math.pow(2.0, 20.0F * var0 - 10.0F) / 2.0F : (2.0F - (float)Math.pow(2.0, -20.0F * var0 + 10.0F)) / 2.0F));
   Easing SINE_IN_OUT = var0 -> (float)(-(Math.cos(Math.PI * var0) - 1.0) / 2.0);
   Easing BACK_OUT = var0 -> {
      float var1 = 1.70158F;
      float var2 = var1 + 1.0F;
      return 1.0F + var2 * cube(var0 - 1.0F) + var1 * (var0 - 1.0F) * (var0 - 1.0F);
   };
   Easing ELASTIC_OUT = var0 -> var0 <= 0.0F
      ? 0.0F
      : (var0 >= 1.0F ? 1.0F : (float)(Math.pow(2.0, -10.0F * var0) * Math.sin((var0 * 10.0F - 0.75) * (Math.PI * 2.0 / 3.0)) + 1.0));
   Easing EMPHASIZED = bezier(0.05F, 0.7F, 0.1F, 1.0F);
   Easing STANDARD = bezier(0.2F, 0.0F, 0.0F, 1.0F);
   Easing ACCELERATE = bezier(0.3F, 0.0F, 0.8F, 0.15F);

   float apply(float var1);

   static float clamp01(float var0) {
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   private static float cube(float var0) {
      return var0 * var0 * var0;
   }

   static Easing bezier(float var0, float var1, float var2, float var3) {
      return var4 -> {
         if (var4 <= 0.0F) {
            return 0.0F;
         } else if (var4 >= 1.0F) {
            return 1.0F;
         } else {
            float var5 = var4;

            for (int var6 = 0; var6 < 8; var6++) {
               float var7 = bezierPoint(var5, var0, var2) - var4;
               if (Math.abs(var7) < 1.0E-5F) {
                  return bezierPoint(var5, var1, var3);
               }

               float var8 = bezierSlope(var5, var0, var2);
               if (Math.abs(var8) < 1.0E-6F) {
                  break;
               }

               var5 -= var7 / var8;
            }

            float var11 = 0.0F;
            float var12 = 1.0F;
            var5 = var4;

            for (int var13 = 0; var13 < 24; var13++) {
               float var9 = bezierPoint(var5, var0, var2);
               if (Math.abs(var9 - var4) < 1.0E-5F) {
                  break;
               }

               if (var9 < var4) {
                  var11 = var5;
               } else {
                  var12 = var5;
               }

               var5 = (var11 + var12) * 0.5F;
            }

            return bezierPoint(var5, var1, var3);
         }
      };
   }

   private static float bezierPoint(float var0, float var1, float var2) {
      float var3 = 1.0F - var0;
      return 3.0F * var3 * var3 * var0 * var1 + 3.0F * var3 * var0 * var0 * var2 + var0 * var0 * var0;
   }

   private static float bezierSlope(float var0, float var1, float var2) {
      float var3 = 1.0F - var0;
      return 3.0F * var3 * var3 * var1 + 6.0F * var3 * var0 * (var2 - var1) + 3.0F * var0 * var0 * (1.0F - var2);
   }
}
