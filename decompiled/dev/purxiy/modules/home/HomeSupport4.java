package dev.purxiy.modules.home;

final class HomeSupport4 {
   private final int[] m = new int[512];
   private final float[] B = new float[256];
   private final float[] o = new float[256];

   HomeSupport4(long var1) {
      int[] var3 = new int[256];
      int var4 = 0;

      while (var4 < 256) {
         var3[var4] = var4++;
      }

      long var5 = var1;

      for (int var7 = 255; var7 > 0; var7--) {
         var5 = mix(var5 + var7);
         int var8 = Math.floorMod(var5, var7 + 1);
         int var9 = var3[var7];
         var3[var7] = var3[var8];
         var3[var8] = var9;
      }

      for (int var10 = 0; var10 < 512; var10++) {
         this.m[var10] = var3[var10 & 0xFF];
      }

      for (int var11 = 0; var11 < 256; var11++) {
         double var12 = unit(mix(var1 * 31L + var11)) * Math.PI * 2.0;
         this.B[var11] = (float)Math.cos(var12);
         this.o[var11] = (float)Math.sin(var12);
      }
   }

   float noise(float var1, float var2) {
      long var3 = 24402512288871L;
      long var5 = var3 ^ 119556215438614L;
      int var7 = (int)((var3 ^ 119556215438614L) >>> 32);
      int var8 = (int)((var3 ^ 119556215438614L) << 32 >>> 48);
      int var9 = (int)(var5 << 48 >>> 48);
      int var10 = U(var7, (short)var8, (short)var9, var1);
      int var11 = U(var7, (short)var8, (short)var9, var2);
      float var12 = var1 - var10;
      float var13 = var2 - var11;
      int var14 = var10 & 0xFF;
      int var15 = var11 & 0xFF;
      float var16 = z(var12);
      float var17 = z(var13);
      float var18 = this.D(this.m[var14 + this.m[var15]], var12, var13);
      float var19 = this.D(this.m[var14 + 1 + this.m[var15]], var12 - 1.0F, var13);
      float var20 = this.D(this.m[var14 + this.m[var15 + 1]], var12, var13 - 1.0F);
      float var21 = this.D(this.m[var14 + 1 + this.m[var15 + 1]], var12 - 1.0F, var13 - 1.0F);
      float var22 = var18 + var16 * (var19 - var18);
      float var23 = var20 + var16 * (var21 - var20);
      return (var22 + var17 * (var23 - var22)) * 1.41F;
   }

   float fbm(float var1, float var2, int var3) {
      float var4 = 0.0F;
      float var5 = 1.0F;
      float var6 = 0.0F;
      float var7 = 1.0F;

      for (int var8 = 0; var8 < var3; var8++) {
         var4 += this.noise(var1 * var7 + var8 * 17.3F, var2 * var7 - var8 * 9.1F) * var5;
         var6 += var5;
         var5 *= 0.5F;
         var7 *= 2.03F;
      }

      return var4 / var6;
   }

   private float D(int var1, float var2, float var3) {
      return this.B[var1] * var2 + this.o[var1] * var3;
   }

   private static float z(float var0) {
      return var0 * var0 * var0 * (var0 * (var0 * 6.0F - 15.0F) + 10.0F);
   }

   private static int U(int var0, short var1, short var2, float var3) {
      HomeSupport2.ensureSourceClassInitialized();
      int var4 = (int)var3;
      return var3 < var4 ? var4 - 1 : var4;
   }

   static long mix(long var0) {
      var0 = (var0 ^ var0 >>> 30) * -4658895280553007687L;
      var0 = (var0 ^ var0 >>> 27) * -7723592293110705685L;
      return var0 ^ var0 >>> 31;
   }

   static float unit(long var0) {
      return (float)(mix(var0) >>> 40) / 1.6777216E7F;
   }

   static float hash(long var0, int var2, int var3, int var4, int var5) {
      return unit(var0 ^ var2 * -7046029254386353131L ^ var3 * -4417276706812531889L ^ var4 * 1609587929392839161L ^ var5 * 2870177450012600261L);
   }
}
