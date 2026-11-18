// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import dev.purxiy.render.BlendMode;

public final class ClientSupport {
   private ClientSupport() {
   }

   public static float[] rgbToHsv(int var0) {
      float var1 = (var0 >>> 16 & 0xFF) / 255.0F;
      float var2 = (var0 >>> 8 & 0xFF) / 255.0F;
      float var3 = (var0 & 0xFF) / 255.0F;
      float var4 = Math.max(var1, Math.max(var2, var3));
      float var5 = Math.min(var1, Math.min(var2, var3));
      float var6 = var4 - var5;
      float var7 = 0.0F;
      if (var6 > 1.0E-6F) {
         if (var4 == var1) {
            var7 = (var2 - var3) / var6 % 6.0F;
         } else if (var4 == var2) {
            var7 = (var3 - var1) / var6 + 2.0F;
         } else {
            var7 = (var1 - var2) / var6 + 4.0F;
         }

         var7 /= 6.0F;
         if (var7 < 0.0F) {
            var7++;
         }
      }

      return new float[]{var7, var4 <= 0.0F ? 0.0F : var6 / var4, var4};
   }

   public static int hsvToRgb(float var0, float var1, float var2) {
      float var3 = var0 % 1.0F * 6.0F;
      int var4 = (int)Math.floor(var3);
      float var5 = var3 - var4;
      float var6 = var2 * (1.0F - var1);
      float var7 = var2 * (1.0F - var1 * var5);
      float var8 = var2 * (1.0F - var1 * (1.0F - var5));
      float var9;
      float var10;
      float var11;
      switch (var4 % 6) {
         case 0:
            var9 = var2;
            var10 = var8;
            var11 = var6;
            break;
         case 1:
            var9 = var7;
            var10 = var2;
            var11 = var6;
            break;
         case 2:
            var9 = var6;
            var10 = var2;
            var11 = var8;
            break;
         case 3:
            var9 = var6;
            var10 = var7;
            var11 = var2;
            break;
         case 4:
            var9 = var8;
            var10 = var6;
            var11 = var2;
            break;
         default:
            var9 = var2;
            var10 = var6;
            var11 = var7;
      }

      return BlendMode.argb(1.0F, var9, var10, var11);
   }
}
