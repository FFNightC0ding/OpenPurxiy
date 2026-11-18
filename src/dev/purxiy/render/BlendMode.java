// Sourced By DevFFNightmare
package dev.purxiy.render;

public final class BlendMode {
   public static final int WHITE = -1;
   public static final int BLACK = -16777216;
   public static final int TRANSPARENT = 0;

   private BlendMode() {
   }

   public static int argb(int var0, int var1, int var2, int var3) {
      return clamp8(var0) << 24 | clamp8(var1) << 16 | clamp8(var2) << 8 | clamp8(var3);
   }

   public static int argb(float var0, float var1, float var2, float var3) {
      return argb(Math.round(var0 * 255.0F), Math.round(var1 * 255.0F), Math.round(var2 * 255.0F), Math.round(var3 * 255.0F));
   }

   public static int rgb(int var0) {
      return 0xFF000000 | var0;
   }

   public static int alpha(int var0) {
      return var0 >>> 24;
   }

   public static int red(int var0) {
      return var0 >> 16 & 0xFF;
   }

   public static int green(int var0) {
      return var0 >> 8 & 0xFF;
   }

   public static int blue(int var0) {
      return var0 & 0xFF;
   }

   public static int withAlpha(int var0, float var1) {
      return clamp8(Math.round(var1 * 255.0F)) << 24 | var0 & 16777215;
   }

   public static int fade(int var0, float var1) {
      if (var1 >= 1.0F) {
         return var0;
      } else {
         int var2 = Math.round((var0 >>> 24) * Math.max(0.0F, var1));
         return var2 << 24 | var0 & 16777215;
      }
   }

   public static int lerp(int var0, int var1, float var2) {
      if (var2 <= 0.0F) {
         return var0;
      } else if (var2 >= 1.0F) {
         return var1;
      } else {
         int var3 = Math.round(alpha(var0) + (alpha(var1) - alpha(var0)) * var2);
         int var4 = Math.round(red(var0) + (red(var1) - red(var0)) * var2);
         int var5 = Math.round(green(var0) + (green(var1) - green(var0)) * var2);
         int var6 = Math.round(blue(var0) + (blue(var1) - blue(var0)) * var2);
         return argb(var3, var4, var5, var6);
      }
   }

   public static int shade(int var0, float var1) {
      return var1 >= 0.0F ? lerp(var0, var0 | 16777215, var1) : lerp(var0, var0 & 0xFF000000, -var1);
   }

   static int toAbgr(int var0) {
      return var0 & -16711936 | var0 >> 16 & 0xFF | (var0 & 0xFF) << 16;
   }

   private static int clamp8(int var0) {
      return var0 < 0 ? 0 : Math.min(var0, 255);
   }
}
