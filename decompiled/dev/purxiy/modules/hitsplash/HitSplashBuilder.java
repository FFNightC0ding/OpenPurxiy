package dev.purxiy.modules.hitsplash;

import java.util.ArrayList;
import java.util.List;

final class HitSplashBuilder {
   static final int h = 256;
   static final int j = 4;
   static final int O = 2;
   static final int A = 1024;
   static final int v = 512;
   static final float a = 54.0F;
   static final float z = 12.0F;
   static final float V = 64.0F;
   static final int n = 4;
   static final int J = 4;
   private static final float[] H = new float[]{0.85F, 0.55F, 0.12F, 1.0F};
   static final int S = 5;
   private static final long[] b = new long[]{
      -1954421236119000465L,
      -228415212183247573L,
      1320944326601530096L,
      4285847941234015112L,
      5109380401983770137L,
      -4669665957000931109L,
      -7579165486768748537L,
      6719398526866821410L,
      -1576390168916938376L,
      5581708175817534937L,
      -2696176638497538234L,
      2237779822699441511L,
      -6316090467288591340L
   };
   private static final Integer[] c = new Integer[13];

   private HitSplashBuilder() {
   }

   static byte[][] bake() {
      long var0 = 81758281148054L;
      long var2 = var0 ^ 130518369542156L;
      long var4 = var0 ^ 1757064209223L;
      long var6 = var0 ^ 73836030599139L;
      long var8 = var0 ^ 101347774313349L;
      byte[] var10 = new byte[2097152];

      for (int var11 = 0; var11 < 8; var11++) {
         boolean var12 = var11 < 4;
         HitSplashBuilder.TypeJ var13 = var12
            ? I(var2, new HitSplashBuilder.TypeC(11 + var11))
            : l(var8, new HitSplashBuilder.TypeC(101 + var11 - 4), H[var11 - 4]);
         v(var13, new HitSplashBuilder.TypeC(977 + var11), var10, var4, var11 % 4 * 256, var11 / 4 * 256);
      }

      byte[][] var14 = new byte[5][];
      var14[0] = var10;

      for (int var15 = 1; var15 < 5; var15++) {
         var14[var15] = s(var14[var15 - 1], var6, 1024 >> var15 - 1, 512 >> var15 - 1);
      }

      return var14;
   }

   static float distance(boolean var0, int var1, float var2, float var3) {
      long var4 = 6056300715261L;
      long var6 = var4 ^ 63142191471207L;
      long var8 = var4 ^ 21943474391534L;
      HitSplashBuilder.TypeJ var10 = var0 ? I(var6, new HitSplashBuilder.TypeC(11 + var1)) : l(var8, new HitSplashBuilder.TypeC(101 + var1), H[var1]);
      return var10.distance(var2, var3);
   }

   private static void v(HitSplashBuilder.TypeJ var0, HitSplashBuilder.TypeC var1, byte[] var2, long var3, int var5, int var6) {
      long var7 = 65667792495006L;
      HitSplashRenderer2.ensureSourceClassInitialized();
      float[] var9 = b(var1, var7, 6);

      for (int var10 = 0; var10 < 256; var10++) {
         float var11 = (var10 + 0.5F - 128.0F) / 54.0F;

         for (int var12 = 0; var12 < 256; var12++) {
            float var13 = (var12 + 0.5F - 128.0F) / 54.0F;
            float var14 = var0.distance(var13, var11) * 54.0F;
            int var15 = ((var6 + var10) * 1024 + var5 + var12) * 4;
            long var16 = 83300026549713L;
            int var18 = var16 < 0L ? -1 : 1;
            var2[var15] = i(var14, 12.0F);
            var2[var15 + 1] = i(var14, 64.0F);
            var2[var15 + 2] = (byte)Math.round(var9[var10 * 256 + var12] * 255.0F);
            var2[var15 + 3] = -1;
         }

         long var19 = 83300026549713L;
         int var20 = var19 < 0L ? -1 : 1;
      }
   }

   private static byte i(float var0, float var1) {
      float var2 = 0.5F - var0 / (2.0F * var1);
      return (byte)Math.round(Math.clamp(var2, 0.0F, 1.0F) * 255.0F);
   }

   private static byte[] s(byte[] var0, long var1, int var3, int var4) {
      HitSplashRenderer2.ensureSourceClassInitialized();
      int var5 = var3 / 2;
      int var6 = var4 / 2;
      byte[] var7 = new byte[var5 * var6 * 4];

      for (int var8 = 0; var8 < var6; var8++) {
         for (int var9 = 0; var9 < var5; var9++) {
            long var10 = 10430851201396L;
            int var22 = var10 < 0L ? -1 : 1;

            for (int var13 = 0; var13 < 4; var13++) {
               byte var14 = var0[(2 * var8 * var3 + 2 * var9) * 4 + var13];
               long var15 = 10430851201396L;
               int var17 = var15 < 0L ? -1 : 1;
               var17 = var14 & 255;
               int var18 = var0[(2 * var8 * var3 + 2 * var9 + 1) * 4 + var13] & 255;
               int var19 = var0[((2 * var8 + 1) * var3 + 2 * var9) * 4 + var13] & 255;
               int var20 = var0[((2 * var8 + 1) * var3 + 2 * var9 + 1) * 4 + var13] & 255;
               var7[(var8 * var5 + var9) * 4 + var13] = (byte)((var17 + var18 + var19 + var20 + 2) / 4);
            }

            long var22x = 10430851201397L;
            int var23 = var22x < 0L ? -1 : 1;
         }

         long var21 = 10430851201396L;
         int var11 = var21 < 0L ? -1 : 1;
      }

      return var7;
   }

   private static float[] b(HitSplashBuilder.TypeC var0, long var1, int var3) {
      HitSplashRenderer2.ensureSourceClassInitialized();
      float[] var4 = new float[36];

      for (int var5 = 0; var5 < var4.length; var5++) {
         long var6 = 65667792495005L;
         int var9 = var6 < 0L ? -1 : 1;
         var4[var5] = var0.next();
      }

      short var24 = 10984;
      long var25 = 65667792495006L;
      int var25x = var25 < 0L ? -1 : 1;
      float[] var9 = new float[65536];

      for (int var10 = 0; var10 < 256; var10++) {
         float var11 = var10 * 6 / 256.0F;
         int var12 = (int)var11;
         float var13 = j(var11 - var12);

         for (int var14 = 0; var14 < 256; var14++) {
            long var15 = 65667792495005L;
            int var17 = var15 < 0L ? -1 : 1;
            float var17x = var14 * 6 / 256.0F;
            int var18 = (int)var17x;
            float var19 = j(var17x - var18);
            float var20 = var4[var12 % 6 * 6 + var18 % 6];
            float var21 = var4[var12 % 6 * 6 + (var18 + 1) % 6];
            float var22 = var4[(var12 + 1) % 6 * 6 + var18 % 6];
            float var23 = var4[(var12 + 1) % 6 * 6 + (var18 + 1) % 6];
            var9[var10 * 256 + var14] = (var20 + (var21 - var20) * var19) * (1.0F - var13) + (var22 + (var23 - var22) * var19) * var13;
         }

         long var26 = 65667792495005L;
         int var151 = var26 < 0L ? -1 : 1;
      }

      return var9;
   }

   private static float j(float var0) {
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static float F(HitSplashBuilder.TypeC var0, float var1, float var2, char var3, char var4, int var5) {
      HitSplashRenderer2.ensureSourceClassInitialized();
      float var6 = var0.next();
      return var6 < var1 ? Math.clamp(var0.gauss() * var2, -2.4F, 2.4F) : var0.range((float) -Math.PI, (float) Math.PI);
   }

   private static HitSplashBuilder.TypeJ I(long var0, HitSplashBuilder.TypeC var2) {
      long var3 = 26100836513534L;
      byte var5 = 0;
      short var6 = 6077;
      int var7 = (int)(var3 << 32 >>> 32);
      HitSplashRenderer2.ensureSourceClassInitialized();
      HitSplashBuilder.TypeJ var8 = new HitSplashBuilder.TypeJ();
      int var9 = (int)var2.range(6.0F, 9.0F);

      for (int var10 = 0; var10 < var9; var10++) {
         long var11 = 66999212541594L;
         int var36 = var11 < 0L ? -1 : 1;
         float var14 = var2.range((float) -Math.PI, (float) Math.PI);
         float var15 = var2.range(0.0F, 0.32F);
         var8.circle(var15 * A(var14) + 0.05F, var15 * g(var14), var2.range(0.26F, 0.42F), var10 == 0 ? -1.0F : 0.3F);
      }

      long var34 = 66999212541593L;
      int var13 = var34 < 0L ? -1 : 1;
      var13 = (int)var2.range(10.0F, 16.0F);

      for (int var35 = 0; var35 < var13; var35++) {
         float var37 = var2.range((float) -Math.PI, (float) Math.PI);
         float var16 = var2.range(0.38F, 0.5F);
         var8.circle(var16 * A(var37) + 0.05F, var16 * g(var37), var2.range(0.05F, 0.11F), 0.1F);
      }

      long var36 = 66999212541593L;
      int var17 = var36 < 0L ? -1 : 1;
      var17 = (int)var2.range(9.0F, 14.0F);

      for (int var18 = 0; var18 < var17; var18++) {
         float var19 = F(var2, 0.7F, 0.8F, (char)var5, (char)var6, var7);
         float var20 = A(var19);
         float var21 = g(var19);
         float var22 = 1.0F + 0.22F * var20;
         long var23 = 66999212541593L;
         int var23x = var23 < 0L ? -1 : 1;
         float var26 = var2.next();
         if (var26 < 0.4F) {
            float var27 = var2.range(0.95F, 1.4F) * var22;
            boolean var28 = var2.next() < 0.4F;
            float var29 = var27 - (var28 ? var2.range(0.1F, 0.18F) : 0.0F);
            var8.capsule(0.28F * var20, 0.28F * var21, var29 * var20, var29 * var21, var2.range(0.1F, 0.14F), var2.range(0.022F, 0.035F), 0.18F);
            float var30 = var2.range(0.05F, 0.085F);
            float var31 = var27 + var30 * 0.5F;
            var8.circle(var31 * var20, var31 * var21, var30, var28 ? 0.0F : 0.05F);
            long var32 = 66999212541593L;
            int var31x = var32 < 0L ? -1 : 1;
         } else if (var26 < 0.75F) {
            float var43 = var2.range(0.58F, 0.82F) * var22;
            long var46 = 66999212541593L;
            int var54 = var46 < 0L ? -1 : 1;
            var8.capsule(0.3F * var20, 0.3F * var21, var43 * var20, var43 * var21, var2.range(0.13F, 0.18F), var2.range(0.07F, 0.1F), 0.18F);
         } else {
            float var44 = var2.range(0.8F, 1.15F) * var22;
            var8.capsule(0.3F * var20, 0.3F * var21, var44 * var20, var44 * var21, var2.range(0.05F, 0.075F), var2.range(0.012F, 0.02F), 0.14F);
         }
      }

      float var38 = var2.range(14.0F, 24.0F);
      long var39 = 66999212541594L;
      int var40 = var39 < 0L ? -1 : 1;
      var40 = (int)var38;

      for (int var41 = 0; var41 < var40; var41++) {
         float var24 = F(var2, 0.65F, 0.75F, (char)var5, (char)var6, var7);
         float var25 = var2.range(1.0F, 1.85F) * (1.0F + 0.1F * A(var24));
         float var42 = var2.range(0.016F, 0.05F) * (var2.next() < 0.15F ? 1.6F : 1.0F);
         float var45 = A(var24);
         float var47 = g(var24);
         if (var2.next() < 0.4F) {
            float var48 = var2.range(0.05F, 0.14F);
            long var49 = 66999212541594L;
            int var56 = var49 < 0L ? -1 : 1;
            var8.capsule((var25 - var48) * var45, (var25 - var48) * var47, var25 * var45, var25 * var47, var42 * 0.45F, var42, 0.0F);
         } else {
            var8.circle(var25 * var45, var25 * var47, var42, 0.0F);
         }
      }

      return var8;
   }

   private static HitSplashBuilder.TypeJ l(long var0, HitSplashBuilder.TypeC var2, float var3) {
      long var4 = 26100836513534L;
      byte var6 = 0;
      short var7 = 6077;
      int var8 = (int)(var4 << 32 >>> 32);
      HitSplashBuilder.TypeJ var9 = new HitSplashBuilder.TypeJ();
      float var10 = 0.06F + 0.3F * var3;
      float var11 = var2.range(0.29F, 0.33F);
      float var12 = var11 * (0.86F - 0.14F * var3);
      HitSplashRenderer2.ensureSourceClassInitialized();
      var9.capsule(-var10, 0.0F, var10, var2.range(-0.03F, 0.03F), var11, var12, -1.0F);
      float var13 = var10 + var11 * 0.95F;
      float var14 = var11 * 0.95F;
      int var15 = (int)var2.range(8.0F, 13.0F);

      for (int var16 = 0; var16 < var15; var16++) {
         float var17 = var2.range((float) -Math.PI, (float) Math.PI);
         long var18 = 24700547241235L;
         int var43 = var18 < 0L ? -1 : 1;
         float var21 = A(var13, var14, var17) * 0.92F;
         var9.circle(var21 * A(var17), var21 * g(var17), var2.range(0.04F, 0.075F), 0.06F);
      }

      long var40 = 24700547241235L;
      int var19 = var40 < 0L ? -1 : 1;
      var19 = (int)var2.range(5.0F, 9.0F);

      for (int var20 = 0; var20 < var19; var20++) {
         long var42 = 24700547241234L;
         int var44 = var42 < 0L ? -1 : 1;
         float var24 = var3 > 0.4F ? F(var2, 0.75F, 0.95F, (char)var6, (char)var7, var8) : var2.range((float) -Math.PI, (float) Math.PI);
         float var25 = A(var24);
         float var26 = g(var24);
         float var27 = A(var13, var14, var24);
         float var28 = var27 + var2.range(0.05F, 0.18F) * (1.0F + 0.8F * var3 * Math.max(var25, 0.0F));
         long var29 = 24700547241235L;
         int var29x = var29 < 0L ? -1 : 1;
         var9.capsule(var27 * 0.75F * var25, var27 * 0.75F * var26, var28 * var25, var28 * var26, var2.range(0.055F, 0.08F), var2.range(0.014F, 0.022F), 0.1F);
         long var32 = 24700547241234L;
         int var56 = var32 < 0L ? -1 : 1;
         if (var2.next() < 0.35F) {
            float var35 = var2.range(0.02F, 0.034F);
            float var36 = var28 + var35 * 0.5F;
            var9.circle(var36 * var25, var36 * var26, var35, 0.025F);
         }
      }

      float var41 = var2.range(3.0F, 7.0F);
      long var43 = 24700547241234L;
      int var44 = var43 < 0L ? -1 : 1;
      var44 = (int)var41;

      for (int var45 = 0; var45 < var44; var45++) {
         long var47 = 24700547241234L;
         int var51 = var47 < 0L ? -1 : 1;
         float var49 = var3 > 0.4F ? F(var2, 0.7F, 0.9F, (char)var6, (char)var7, var8) : var2.range((float) -Math.PI, (float) Math.PI);
         float var30 = A(var13, var14, var49) + var2.range(0.1F, 0.32F);
         var9.circle(var30 * A(var49), var30 * g(var49), var2.range(0.016F, 0.034F), 0.0F);
      }

      long var46 = 24700547241234L;
      int var491 = var46 < 0L ? -1 : 1;
      long var48 = 24700547241234L;
      int var50 = var48 < 0L ? -1 : 1;
      if (var3 > 0.3F) {
         float var31 = var13 + var2.range(0.1F, 0.18F);
         float var51 = var2.range(-0.04F, 0.04F);
         float var33 = var2.range(0.07F, 0.1F) * var3;
         float var34 = var2.range(-0.14F, 0.14F);
         int var56 = (int)var2.range(1.0F, 4.0F);

         for (int var57 = 0; var57 < var56; var57++) {
            long var37 = 24700547241234L;
            int var39 = var37 < 0L ? -1 : 1;
            if (var57 == 0 && var2.next() < 0.5F) {
               var9.capsule(var13 - 0.05F, 0.0F, var31, var51, 0.035F, var33 * 0.6F, 0.04F);
            }

            var9.circle(var31, var51, var33, 0.0F);
            float var39x = var2.range(0.18F, 0.3F);
            var31 += var39x;
            var51 += var39x * var34 + var2.range(-0.03F, 0.03F);
            var33 *= var2.range(0.6F, 0.78F);
         }
      }

      var50 = (int)var2.range(3.0F, 8.0F);

      for (int var52 = 0; var52 < var50; var52++) {
         float var54 = var3 > 0.4F ? var2.range(-1.5F, 1.5F) : var2.range((float) -Math.PI, (float) Math.PI);
         float var55 = var2.range(0.7F, 1.35F);
         var9.circle(var55 * A(var54), var55 * g(var54), var2.range(0.012F, 0.028F), 0.0F);
      }

      long var53 = 24700547241234L;
      int var63 = var53 < 0L ? -1 : 1;
      return var9;
   }

   private static float A(float var0, float var1, float var2) {
      float var3 = A(var2) / var0;
      float var4 = g(var2) / var1;
      return 1.0F / (float)Math.sqrt(var3 * var3 + var4 * var4);
   }

   private static float A(float var0) {
      return (float)Math.cos(var0);
   }

   private static float g(float var0) {
      return (float)Math.sin(var0);
   }

   static float smin(float var0, float var1, float var2) {
      if (var2 <= 0.0F) {
         return Math.min(var0, var1);
      } else {
         float var3 = Math.max(var2 - Math.abs(var0 - var1), 0.0F) / var2;
         return Math.min(var0, var1) - var3 * var3 * var2 * 0.25F;
      }
   }

   static float unevenCapsule(float var0, float var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      var0 -= var2;
      var1 -= var3;
      var4 -= var2;
      var5 -= var3;
      float var8 = var4 * var4 + var5 * var5;
      float var9 = Math.abs((var0 * var5 - var1 * var4) / var8);
      float var10 = (var0 * var4 + var1 * var5) / var8;
      float var11 = var6 - var7;
      float var12 = (float)Math.sqrt(Math.max(var8 - var11 * var11, 1.0E-9F));
      float var13 = var12 * var10 - var11 * var9;
      float var14 = var12 * var9 + var11 * var10;
      float var15 = var9 * var9 + var10 * var10;
      if (var13 < 0.0F) {
         return (float)Math.sqrt(var8 * var15) - var6;
      } else {
         return var13 > var12 ? (float)Math.sqrt(Math.max(var8 * (var15 + 1.0F - 2.0F * var10), 0.0F)) - var7 : var14 - var6;
      }
   }

   static final class TypeC {
      private long a;
      private static final long[] b = new long[]{-8143262748555922732L, -5337042276857166612L, -5588622281435443083L};
      private static final Integer[] c = new Integer[3];
      private static final long[] d = new long[]{-3016848585277012197L, -8562259080230022144L};
      private static final Long[] e = new Long[2];

      TypeC(long var1) {
         long var3 = 39657276047853L;
         long var5 = var3 ^ 56525477069072L;
         int var7 = (int)((var3 ^ 56525477069072L) >>> 32);
         int var8 = (int)((var3 ^ 56525477069072L) << 32 >>> 48);
         int var9 = (int)(var5 << 48 >>> 48);
         this.a = f(var7, (char)var8, (short)var9, var1 ^ 7146057691288625177L);
      }

      float next() {
         long var1 = 140552743451582L;
         long var3 = var1 ^ 115093538421571L;
         int var5 = (int)((var1 ^ 115093538421571L) >>> 32);
         int var6 = (int)((var1 ^ 115093538421571L) << 32 >>> 48);
         int var7 = (int)(var3 << 48 >>> 48);
         this.a += -7046029254386353131L;
         return (float)(f(var5, (char)var6, (short)var7, this.a) >>> 40) / 1.6777216E7F;
      }

      float range(float var1, float var2) {
         return var1 + (var2 - var1) * this.next();
      }

      float gauss() {
         float var1 = Math.max(this.next(), 1.0E-7F);
         float var2 = this.next();
         return (float)(Math.sqrt(-2.0 * Math.log(var1)) * Math.cos((Math.PI * 2) * var2));
      }

      private static long f(int var0, char var1, short var2, long var3) {
         var3 = (var3 ^ var3 >>> 30) * -4658895280553007687L;
         var3 = (var3 ^ var3 >>> 27) * -7723592293110705685L;
         return var3 ^ var3 >>> 31;
      }

      private static long b(int var0, long var1) {
         int var3 = (var0 ^ (int)var1 ^ 18041) & 32767;
         if (e[var3] == null) {
            e[var3] = d[var3] ^ var1;
         }

         return e[var3];
      }
   }

   private static final class TypeJ {
      private final List<float[]> y = new ArrayList<>();

      void circle(float var1, float var2, float var3, float var4) {
         this.y.add(new float[]{0.0F, var1, var2, var3, var4, var1, var2, var3});
      }

      void capsule(float var1, float var2, float var3, float var4, float var5, float var6, float var7) {
         float var8 = (var1 + var3) * 0.5F;
         float var9 = (var2 + var4) * 0.5F;
         float var10 = (float)Math.hypot(var3 - var1, var4 - var2) * 0.5F + Math.max(var5, var6);
         this.y.add(new float[]{1.0F, var1, var2, var3, var4, var5, var6, var7, var8, var9, var10});
      }

      float distance(float var1, float var2) {
         float var3 = Float.MAX_VALUE;

         for (float[] var5 : this.y) {
            float var6 = var5[0] == 0.0F ? var5[4] : var5[7];
            float var7 = var5[0] == 0.0F ? var5[5] : var5[8];
            float var8 = var5[0] == 0.0F ? var5[6] : var5[9];
            float var9 = var5[0] == 0.0F ? var5[7] : var5[10];
            float var10 = (float)Math.hypot(var1 - var7, var2 - var8) - var9;
            if (var3 == Float.MAX_VALUE || !(var10 >= var3 + Math.max(var6, 0.0F))) {
               float var11 = var5[0] == 0.0F
                  ? (float)Math.hypot(var1 - var5[1], var2 - var5[2]) - var5[3]
                  : HitSplashBuilder.unevenCapsule(var1, var2, var5[1], var5[2], var5[3], var5[4], var5[5], var5[6]);
               var3 = var3 == Float.MAX_VALUE ? var11 : HitSplashBuilder.smin(var3, var11, var6);
            }
         }

         return var3;
      }
   }
}
