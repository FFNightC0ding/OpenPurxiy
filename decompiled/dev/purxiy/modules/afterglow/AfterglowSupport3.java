package dev.purxiy.modules.afterglow;

import net.minecraft.class_3532;

final class AfterglowSupport3 {
   static final float w = 0.3F;
   static final float d = 0.12F;
   private static final float F = 3.1F;

   private AfterglowSupport3() {
   }

   static int hash(int var0, int var1, int var2, int var3) {
      int var4 = var0 * -1918454973 + var1 * -669632447 + var2 * -887442657 + var3 * -1640531527;
      var4 ^= var4 >>> 16;
      var4 *= 2146121005;
      var4 ^= var4 >>> 15;
      var4 *= -2073254261;
      return var4 ^ var4 >>> 16;
   }

   static float random(int var0, int var1, int var2, int var3) {
      return (hash(var0, var1, var2, var3) >>> 8) / 1.6777215E7F;
   }

   static float noise(float var0, float var1, float var2, int var3) {
      int var4 = class_3532.method_15375(var0);
      int var5 = class_3532.method_15375(var1);
      int var6 = class_3532.method_15375(var2);
      float var7 = var0 - var4;
      float var8 = var1 - var5;
      float var9 = var2 - var6;
      float var10 = var7 * var7 * (3.0F - 2.0F * var7);
      float var11 = var8 * var8 * (3.0F - 2.0F * var8);
      float var12 = var9 * var9 * (3.0F - 2.0F * var9);
      float var13 = class_3532.method_16439(var10, random(var4, var5, var6, var3), random(var4 + 1, var5, var6, var3));
      float var14 = class_3532.method_16439(var10, random(var4, var5 + 1, var6, var3), random(var4 + 1, var5 + 1, var6, var3));
      float var15 = class_3532.method_16439(var10, random(var4, var5, var6 + 1, var3), random(var4 + 1, var5, var6 + 1, var3));
      float var16 = class_3532.method_16439(var10, random(var4, var5 + 1, var6 + 1, var3), random(var4 + 1, var5 + 1, var6 + 1, var3));
      return class_3532.method_16439(var12, class_3532.method_16439(var11, var13, var14), class_3532.method_16439(var11, var15, var16));
   }

   static float field(float var0, float var1, float var2, float var3, float var4, int var5) {
      float var6 = Math.clamp((var1 - var3) / Math.max(var4, 0.1F), 0.0F, 1.0F);
      float var7 = noise(var0 * 3.1F, var1 * 3.1F, var2 * 3.1F, var5);
      return Math.clamp((1.0F - var6) * 0.7F + var7 * 0.3F, 0.0F, 1.0F);
   }

   static void toBody(float var0, float var1, float var2, float var3, float var4, float[] var5) {
      float var6 = (float)Math.cos(var3);
      float var7 = (float)Math.sin(var3);
      float var8 = var0 * var6 - var2 * var7;
      float var9 = var0 * var7 + var2 * var6;
      float var10 = (float)Math.cos(var4);
      float var11 = (float)Math.sin(var4);
      var5[0] = var8 * var10 + var1 * var11;
      var5[1] = -var8 * var11 + var1 * var10;
      var5[2] = var9;
   }
}
