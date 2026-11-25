package dev.purxiy.modules.knife;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class KnifeSupport2 {
   private static final float D = 22.0F;
   static final KnifeSupport2.Data[] H = new KnifeSupport2.Data[]{
      new KnifeSupport2.Data("down_left", KnifeSupport2.TypeU.p, 58.0F, 214.0F, 9.0F, 16.0F, 7.0F, 0.0F, 0.0F, 0.0F, 1.2F),
      new KnifeSupport2.Data("rise_right", KnifeSupport2.TypeU.p, 214.0F, 42.0F, 9.0F, 18.0F, 7.0F, -0.02F, -0.02F, 0.0F, 1.0F),
      new KnifeSupport2.Data("down_right", KnifeSupport2.TypeU.p, 128.0F, -32.0F, 9.0F, 16.0F, 7.0F, -0.03F, 0.01F, 0.0F, 0.9F),
      new KnifeSupport2.Data("rise_left", KnifeSupport2.TypeU.p, -34.0F, 138.0F, 9.0F, 18.0F, 7.0F, 0.02F, -0.03F, 0.0F, 0.8F),
      new KnifeSupport2.Data("across_left", KnifeSupport2.TypeU.r, 6.0F, 178.0F, 8.0F, 16.0F, 6.0F, 0.0F, 0.05F, 0.04F, 1.0F),
      new KnifeSupport2.Data("across_right", KnifeSupport2.TypeU.r, 176.0F, 4.0F, 8.0F, 14.0F, 6.0F, 0.0F, 0.04F, 0.04F, 0.8F),
      new KnifeSupport2.Data("overhead", KnifeSupport2.TypeU.M, 104.0F, -48.0F, 7.0F, 11.0F, 5.0F, -0.03F, 0.03F, 0.06F, 0.7F),
      new KnifeSupport2.Data("rising", KnifeSupport2.TypeU.M, -44.0F, 92.0F, 7.0F, -12.0F, 5.0F, -0.03F, 0.0F, 0.06F, 0.5F)
   };
   private static final int K = 12;
   public static final float u = 0.16F;
   public static final float M = 0.03F;
   private static final String[] b = new String[35];
   private static final long[] c = new long[]{
      5361766268576785802L,
      9142534127742980320L,
      -4865494519778270528L,
      8352701336078068686L,
      -2168021675136868465L,
      -8353153369704850684L,
      330505601409250799L,
      6249472537227143736L,
      -2023210001834164522L,
      3647066355998339339L,
      3584463939865401725L
   };

   private KnifeSupport2() {
   }

   static float arc(
      KnifeSupport2.TypeQ var0,
      KnifeSupport2.TypeU var1,
      float var2,
      float var3,
      float var4,
      float var5,
      Vector3f var6,
      Vector3f var7,
      float var8,
      float var9,
      float var10,
      KnifeSupport2.TypeE var11
   ) {
      int var12 = Math.max(2, (int)Math.ceil(Math.abs(var3 - var2) / 22.0F));
      float var13 = var0.S;
      float var14 = var13;
      Vector3f var15 = new Vector3f();
      Vector3f var16 = new Vector3f();
      Vector3f var17 = new Vector3f();
      Vector3f var18 = new Vector3f();
      float var19 = Math.signum(var3 - var2);

      for (int var20 = 1; var20 <= var12; var20++) {
         float var21 = (float)var20 / var12;
         float var22 = var2 + (var3 - var2) * var21;
         var18.set(var6).lerp(var7, var21);
         var1.round(var18, var8, var22, var15);
         var15.z = var15.z - var9 * (float)Math.sin(Math.PI * var21);
         var1.direction(var22, var4 + (var5 - var4) * var21, var16);
         var1.onward(var22, var17).mul(var19);
         float var23 = var13 + var10 * var11.time(var21);
         var0.at(var23, var15, var16, var17);
         if (Math.abs(var21 - 0.5F) < 0.5F / var12 + 1.0E-4F && var14 == var13) {
            var14 = var23;
         }
      }

      return var14;
   }

   public static KnifeTransform.TypeU flow(
      KnifeTransform.TypeH var0, Random var1, KnifeSupport2.TypeX var2, KnifeCurve var3, KnifeSupport2.TypeM var4, boolean var5
   ) {
      long var6 = 140185962395573L;
      long var8 = var6 ^ 86666621423361L;
      long var10 = var6 ^ 133727684682572L;
      KnifeSupport2.TypeR var12 = new KnifeSupport2.TypeR(var0);

      for (int var13 = 0; var13 < 12; var13++) {
         KnifeSupport2.Data var14 = var4.D;
         KnifeTransform.TypeU var15 = x(var12, var1, var10, var2, var3, var4, var5);
         if (clearance(var15, var12) >= 0.19F) {
            return var15;
         }

         var4.D = var14;
      }

      KnifeSupport2.TypeQ var16 = new KnifeSupport2.TypeQ(var12);
      var16.pose(0.0F, var3, false);
      float[] var17 = z(var16, var1, var8, var12);
      var16.pose(var16.S + var12.time(0.3F), var3, true);
      return var16.build("circle", -1.0F, -1.0F, -1.0F, var17[0], var17[1], var16.S, var12.e);
   }

   private static KnifeTransform.TypeU x(
      KnifeSupport2.TypeR var0, Random var1, long var2, KnifeSupport2.TypeX var4, KnifeCurve var5, KnifeSupport2.TypeM var6, boolean var7
   ) {
      long var8 = 41260814513885L;
      long var10 = 36734330688835L;
      long var12 = 33613741839472L;
      long var14 = 76902966526572L;
      short var16 = 17905;
      short var17 = 24064;
      int var18 = (int)(var14 << 48 >>> 48);
      long var19 = 92585190254805L;
      long var21 = 54619960162484L;
      long var23 = 94986951183890L;
      long var25 = 35039459157321L;
      long var27 = 114328799568913L;
      long var29 = 20289180849470L;
      KnifeUpdater2.ensureSourceClassInitialized();
      String var31 = l(var0, var1, var4, var23, var6, var7);
      var6.played(var31);
      KnifeSupport2.TypeQ var32 = new KnifeSupport2.TypeQ(var0);
      var32.pose(0.0F, var5, false);
      byte var34 = -1;
      switch (var31.hashCode()) {
         case -874427304:
            if (var31.equals("thrust")) {
               long var55 = 7561044153592L;
               int var62 = var55 < 0L ? -1 : 1;
               var14 = 7915670099058438413L;
               var34 = 6;
            }
            break;
         case -339967584:
            if (var31.equals("shoulder")) {
               var34 = 8;
            }
            break;
         case -318277445:
            if (var31.equals("present")) {
               long var54 = 7561044153592L;
               int var61 = var54 < 0L ? -1 : 1;
               var34 = 5;
            }
            break;
         case 98882:
            if (var31.equals("cut")) {
               long var53 = 7561044153593L;
               int var60 = var53 < 0L ? -1 : 1;
               var34 = 0;
            }
            break;
         case 3202466:
            if (var31.equals("high")) {
               long var52 = 7561044153593L;
               int var59 = var52 < 0L ? -1 : 1;
               var34 = 7;
            }
            break;
         case 3536962:
            if (var31.equals("spin")) {
               long var51 = 7561044153592L;
               int var581 = var51 < 0L ? -1 : 1;
               var34 = 2;
            }
            break;
         case 94843278:
            if (var31.equals("combo")) {
               long var50 = 7561044153593L;
               int var571 = var50 < 0L ? -1 : 1;
               var34 = 1;
            }
            break;
         case 96505999:
            if (var31.equals("eight")) {
               long var49 = 7561044153592L;
               int var561 = var49 < 0L ? -1 : 1;
               var34 = 3;
            }
            break;
         case 97520651:
            if (var31.equals("flick")) {
               long var35 = 7561044153593L;
               int var37 = var35 < 0L ? -1 : 1;
               var34 = 4;
            }
      }
      float[] var56 = switch (var34) {
         case 0 -> X(var8, var32, var1, var0, var6, 1);
         case 1 -> {
            int var36 = 2 + (var1.nextFloat() < 0.3F ? 1 : 0);
            yield X(var8, var32, var1, var0, var6, var36);
         }
         case 2 -> x(var32, var1, var12, var0, var4);
         case 3 -> B(var32, var16, var17, var1, var0, var18);
         case 4 -> L(var32, var19, var1, var0);
         case 5 -> F(var32, var1, var0, var25);
         case 6 -> W(var29, var32, var1, var0);
         case 7 -> n(var32, var27, var1, var0, var6);
         case 8 -> h(var10, var32, var1, var0);
         default -> z(var32, var1, var21, var0);
      };
      float var57 = var56[0];
      float var37 = var56[1];
      boolean var38 = var32.k < 0.99F;
      var32.left(var5.R[6], false);
      var32.pose(var32.S + var0.time(0.3F + 0.08F * var1.nextFloat()), var5, true);
      long var39 = 7561044153592L;
      int var58 = var39 < 0L ? -1 : 1;
      if (var38) {
         long var42 = 7561044153592L;
         int var42x = var42 < 0L ? -1 : 1;
         if (var5.R[6] > 0.5F) {
            var32.sound("grip", 0.55F, 1.0F);
         }
      }

      long var58x = 7561044153592L;
      int var66 = var58x < 0L ? -1 : 1;
      float var44 = var56.length > 2 ? var56[2] : -1.0F;
      long var45 = 7561044153593L;
      int var45x = var45 < 0L ? -1 : 1;
      float var47x = var56.length > 3 ? var56[3] : -1.0F;
      return var32.build(var31, -1.0F, var44, var47x, var57, var37, var32.S, var0.e);
   }

   private static String l(KnifeSupport2.TypeR var0, Random var1, KnifeSupport2.TypeX var2, long var3, KnifeSupport2.TypeM var5, boolean var6) {
      KnifeUpdater2.ensureSourceClassInitialized();
      String[] var7 = new String[]{"cut", "combo", "spin", "eight", "flick", "present", "thrust", "high", "shoulder", "circle"};

      float[] var8 = switch (var2) {
         case X -> new float[]{3.0F, 2.0F, 2.0F, 1.0F, 1.2F, 0.7F, 0.8F, 0.8F, 1.0F, 0.6F};
         case q -> new float[]{2.0F, 1.0F, 2.6F, 0.4F, 1.6F, 0.0F, 1.0F, 0.4F, 1.2F, 0.3F};
         case i -> new float[]{1.0F, 0.4F, 1.0F, 0.5F, 1.2F, 2.0F, 0.4F, 0.8F, 0.8F, 1.4F};
         case a -> new float[]{1.0F, 1.6F, 2.4F, 1.6F, 1.4F, 1.4F, 0.6F, 1.2F, 0.8F, 0.6F};
      };
      float[] var9 = new float[var7.length];
      float var10 = 0.0F;

      for (int var11 = 0; var11 < var7.length; var11++) {
         boolean var13;
         float var12;
         label144: {
            float var14 = var8[var11] * var0.weight(var7[var11], 1.0F);
            long var15 = 94986951183890L;
            int var15x = var15 < 0L ? -1 : 1;
            var12 = var14;
            if (var11 != 0) {
               long var18 = 94986951183889L;
               int var17 = var18 < 0L ? -1 : 1;
               long var20 = 94986951183890L;
               int var22 = var20 < 0L ? -1 : 1;
               if (var11 != 1) {
                  long var22x = 94986951183889L;
                  int var24 = var22x < 0L ? -1 : 1;
                  long var24x = 94986951183889L;
                  int var26 = var24x < 0L ? -1 : 1;
                  if (var11 != 3) {
                     long var26x = 94986951183890L;
                     int var28 = var26x < 0L ? -1 : 1;
                     long var28x = 94986951183889L;
                     int var30 = var28x < 0L ? -1 : 1;
                     if (var11 != 6) {
                        long var30x = 94986951183890L;
                        int var31 = var30x < 0L ? -1 : 1;
                        if (var11 != 7) {
                           var13 = false;
                           break label144;
                        }
                     }
                  }
               }
            }

            var13 = true;
         }

         if (!var6 && var13) {
            var12 = 0.0F;
         }

         int var35 = var5.recently(var7[var11]);
         float var16 = var12 * (var7[var11].equals(var5.z[0]) ? 0.15F : (var35 > 0 ? 0.6F : 1.0F));
         long var17 = 94986951183889L;
         var13 = (var17 < 0L ? -1 : 1) != 0;
         if (var35 >= 2) {
            var16 = 0.0F;
         }

         var9[var11] = var16;
         var10 += var16;
      }

      long var32 = 94986951183889L;
      int var33 = var32 < 0L ? -1 : 1;
      long var34 = 94986951183890L;
      int var361 = var34 < 0L ? -1 : 1;
      if (var10 <= 0.0F) {
         long var37 = 94986951183889L;
         int var45 = var37 < 0L ? -1 : 1;
         return var6 ? "cut" : "spin";
      } else {
         float var36x = var1.nextFloat() * var10;

         for (int var38 = 0; var38 < var7.length; var38++) {
            var36x -= var9[var38];
            long var19 = 94986951183890L;
            int var48 = var19 < 0L ? -1 : 1;
            if (var36x <= 0.0F) {
               return var7[var38];
            }
         }

         int var39 = var7.length - 1;
         long var40 = 94986951183890L;
         int var49 = var40 < 0L ? -1 : 1;
         return var7[var39];
      }
   }

   private static float[] X(long var0, KnifeSupport2.TypeQ var2, Random var3, KnifeSupport2.TypeR var4, KnifeSupport2.TypeM var5, int var6) {
      KnifeUpdater2.ensureSourceClassInitialized();
      float var7 = -1.0F;
      float var8 = -1.0F;
      float var9 = -1.0F;

      for (int var10 = 0; var10 < var6; var10++) {
         KnifeSupport2.Data var11 = next(var3, var4, var5, var2.aim(new Vector3f()), var10 == 0);
         float[] var12 = cut(var2, var3, var4, var11, false, var10 == 0, 1.0F);
         long var13 = 41260814513884L;
         int var15 = var13 < 0L ? -1 : 1;
         if (var10 == 0) {
            var7 = var12[0];
            var8 = var12[1];
         }

         var9 = var12[2];
      }

      float var16 = var2.S;
      D(var2, var4, 0.1F);
      return new float[]{var7, var16, var8, var9};
   }

   static KnifeSupport2.Data next(Random var0, KnifeSupport2.TypeR var1, KnifeSupport2.TypeM var2, Vector3f var3, boolean var4) {
      float[] var5 = new float[H.length];
      float var6 = 0.0F;
      float var7 = screenAngle(var3);

      for (int var8 = 0; var8 < H.length; var8++) {
         KnifeSupport2.Data var9 = H[var8];
         float var10 = var1.weight(var9);
         if (var9 == var2.D) {
            var10 *= 0.08F;
         }

         if (!Float.isNaN(var7) && !var4) {
            float var11 = screenAngle(var9.plane().direction(var9.from(), var9.tilt(), new Vector3f()));
            float var12 = Float.isNaN(var11) ? 90.0F : Math.abs(wrap(var11 - var7));
            var10 *= (float)Math.exp(-var12 * var12 / 6050.0F) + 0.04F;
         }

         var5[var8] = var10;
         var6 += var10;
      }

      float var13 = var0.nextFloat() * var6;

      for (int var14 = 0; var14 < H.length; var14++) {
         var13 -= var5[var14];
         if (var13 <= 0.0F) {
            var2.D = H[var14];
            return H[var14];
         }
      }

      var2.D = H[0];
      return H[0];
   }

   static float[] cut(KnifeSupport2.TypeQ var0, Random var1, KnifeSupport2.TypeR var2, KnifeSupport2.Data var3, boolean var4, boolean var5, float var6) {
      float var7 = var3.sense();
      float var8 = var3.from() + N(var1, var3.spread());
      float var9 = var3.to() + N(var1, var3.spread());
      float var10 = (var3.tilt() + N(var1, var3.tiltSpread())) * var2.G;
      float var11 = var2.Z * (0.92F + 0.16F * var1.nextFloat());
      Vector3f var12 = new Vector3f(var2.m).add(var3.x(), var3.y(), var3.z()).add(N(var1, 0.025F), N(var1, 0.02F), N(var1, 0.02F));
      Vector3f var13 = new Vector3f(var12).add(N(var1, 0.02F), N(var1, 0.02F), 0.0F);
      float var14 = var2.N * var11;
      float var15 = Math.abs(var9 - var8);
      float var16 = var2.time((var4 ? 0.125F : 0.19F) * (float)Math.sqrt(var15 / 150.0F) * (0.92F + 0.16F * var1.nextFloat())) / var6;
      float var17 = 10.0F + 6.0F * var1.nextFloat();
      Vector3f var18 = new Vector3f();
      Vector3f var19 = new Vector3f();
      Vector3f var20 = new Vector3f();
      float var21 = var8 - var7 * var17;
      var3.plane().round(var12, var14, var21, var18);
      var3.plane().direction(var21, var10, var19);
      var3.plane().onward(var21, var20).mul(var7);
      float var22 = var4 ? 0.0F : var0.S + var2.time(var5 ? 0.2F + 0.07F * var1.nextFloat() : 0.11F + 0.04F * var1.nextFloat()) / var6;
      var0.at(var22, var18, var19, var20);
      var0.sound("swish", var4 ? 1.0F : 0.5F + 0.2F * var1.nextFloat(), (var4 ? 1.0F : 1.06F) + N(var1, 0.06F));
      float var23 = arc(var0, var3.plane(), var21, var9, var10, var10 + N(var1, 4.0F), var12, var13, var14, 0.06F * var11, var16, KnifeSupport2.TypeE.F);
      var0.stop();
      return new float[]{var22, var22, var0.S + 0.015F, var23};
   }

   private static void D(KnifeSupport2.TypeQ var0, KnifeSupport2.TypeR var1, float var2) {
      Vector3f var3 = var0.hand(new Vector3f());
      Vector3f var4 = var0.aim(new Vector3f());
      Vector3f var5 = var0.lead(new Vector3f()).mul(var1.D);
      var3.fma(0.006F, var5);
      var4.fma(0.05F, var5).normalize();
      var0.at(var0.S + var1.time(var2), var3, var4, var5);
   }

   private static float[] x(KnifeSupport2.TypeQ var0, Random var1, long var2, KnifeSupport2.TypeR var4, KnifeSupport2.TypeX var5) {
      long var6 = 91495720675048L;
      KnifeUpdater2.ensureSourceClassInitialized();
      int var8 = v(var1, var6, var4.weight("spin.front", 1.0F), var4.weight("spin.side", 0.8F), var4.weight("spin.low", 0.5F));
      Vector3f var9;
      Vector3f var10;
      switch (var8) {
         case 0:
            var9 = new Vector3f(0.13F, -0.17F, -0.6F);
            var10 = new Vector3f(0.2F + N(var1, 0.12F), 0.1F + N(var1, 0.1F), 1.0F);
            long var11 = 33613741839471L;
            int var381 = var11 < 0L ? -1 : 1;
            break;
         case 1:
            var9 = new Vector3f(0.3F, -0.26F, -0.62F);
            var10 = new Vector3f(0.94F, N(var1, 0.12F), -0.34F + N(var1, 0.1F));
            long var14 = 33613741839472L;
            int var15 = var14 < 0L ? -1 : 1;
            break;
         default:
            var9 = new Vector3f(0.12F, -0.4F, -0.64F);
            var10 = new Vector3f(N(var1, 0.12F), 0.9F, 0.45F);
      }

      var9.add(N(var1, 0.025F), N(var1, 0.02F), N(var1, 0.02F)).mul(var4.Z, 1.0F, 1.0F);
      var10.normalize();
      float var38 = var1.nextBoolean() ? 1.0F : -1.0F;
      float var12 = (var5 == KnifeSupport2.TypeX.a || var1.nextFloat() < 0.35F) && var4.x >= 1.6F ? 2.0F : 1.0F;
      Vector3f var13 = var0.aim(new Vector3f());
      long var39 = 33613741839472L;
      int var17 = var39 < 0L ? -1 : 1;
      var13.fma(-var13.dot(var10), var10);
      long var17x = 33613741839472L;
      int var20 = var17x < 0L ? -1 : 1;
      if (var13.lengthSquared() < 0.05F) {
         var13.set(0.0F, 1.0F, 0.0F).fma(-var10.y, var10);
      }

      var13.normalize();
      Vector3f var20x = new Vector3f();
      Vector3f var21 = new Vector3f();
      Quaternionf var22 = new Quaternionf();
      var0.left(0.0F, false);
      var21.set(var10).cross(var13).mul(var38);
      float var23 = var0.S + var4.time(0.14F + 0.04F * var1.nextFloat());
      var0.at(var23, var9, var13, var21);
      float var24 = var4.x * (0.88F + 0.24F * var1.nextFloat());
      float var25 = var4.time(var12 / var24 * 1.18F);
      int var26 = (int)Math.ceil(var12 * 360.0F / 22.0F);
      Vector3f var27 = new Vector3f();
      float var28 = 0.012F + 0.01F * var1.nextFloat();

      for (int var29 = 1; var29 <= var26; var29++) {
         float var30 = (float)var29 / var26;
         long var31 = 33613741839472L;
         int var31x = var31 < 0L ? -1 : 1;
         float var33x = var38 * var12 * 360.0F * var30;
         long var34 = 33613741839471L;
         int var34x = var34 < 0L ? -1 : 1;
         var22.rotationAxis((float)Math.toRadians(var33x), var10);
         var22.transform(var20x.set(var13));
         var21.set(var10).cross(var20x).mul(var38);
         var27.set(var21).mul(-var28 * (float)Math.sin(Math.PI * var30)).add(var9);
         long var36x = 33613741839472L;
         int var36x = var36x < 0L ? -1 : 1;
         var0.at(var23 + var25 * KnifeSupport2.TypeE.k.time(var30), var27, var20x, var21);
         if (var29 % Math.max(1, var26 / (int)(var12 * 2.0F)) == 0) {
            var0.sound("swish", 0.32F + 0.1F * var1.nextFloat(), 1.18F + N(var1, 0.06F));
         }
      }

      var0.stop();
      float var40 = var0.S;
      D(var0, var4, 0.08F);
      return new float[]{var23, var40};
   }

   private static float[] B(KnifeSupport2.TypeQ var0, int var1, int var2, Random var3, KnifeSupport2.TypeR var4, int var5) {
      float var6 = var4.Z * (0.9F + 0.2F * var3.nextFloat());
      float var7 = var4.N * var6;
      float var8 = 0.07F * var6;
      Vector3f var9 = new Vector3f(var4.m).add(N(var3, 0.02F), 0.03F + N(var3, 0.02F), -0.02F);
      Vector3f var10 = new Vector3f(var9).add(var8, 0.02F, 0.0F);
      Vector3f var11 = new Vector3f(var9).add(-var8, 0.02F, 0.0F);
      Vector3f var12 = new Vector3f(var9).add(var8 * 0.6F, -0.05F, -0.02F);
      Vector3f var13 = new Vector3f(var9).add(-var8 * 0.6F, -0.05F, -0.02F);
      float var14 = 18.0F + N(var3, 5.0F);
      float var15 = var4.time(0.26F + 0.06F * var3.nextFloat());
      Vector3f var16 = new Vector3f();
      KnifeUpdater2.ensureSourceClassInitialized();
      Vector3f var17 = new Vector3f();
      Vector3f var18 = new Vector3f();
      KnifeSupport2.TypeU.p.round(var10, var7, 62.0F, var16);
      KnifeSupport2.TypeU.p.direction(62.0F, var14, var17);
      KnifeSupport2.TypeU.p.onward(62.0F, var18);
      float var19 = var0.S + var4.time(0.22F);
      var0.at(var19, var16, var17, var18);
      var0.sound("swish", 0.5F, 1.05F);
      int var20 = var3.nextFloat() < 0.4F ? 2 : 1;

      for (int var22 = 0; var22 < var20; var22++) {
         arc(
            var0,
            KnifeSupport2.TypeU.p,
            62.0F,
            212.0F,
            var14,
            var14,
            var10,
            var13,
            var7,
            0.05F * var6,
            var15,
            var22 == 0 ? KnifeSupport2.TypeE.n : KnifeSupport2.TypeE.f
         );
         var0.sound("swish", 0.45F, 1.0F);
         arc(var0, KnifeSupport2.TypeU.p, 212.0F, -32.0F, var14, var14, var13, var12, var7, 0.03F, var15 * 1.45F, KnifeSupport2.TypeE.n);
         var0.sound("swish", 0.45F, 1.1F);
         if (var22 < var20 - 1) {
            arc(var0, KnifeSupport2.TypeU.p, -32.0F, 62.0F, var14, var14, var12, var10, var7, 0.0F, var15 * 0.7F, KnifeSupport2.TypeE.N);
            var0.sound("swish", 0.45F, 1.05F);
         }
      }

      var0.stop();
      float var23 = var0.S;
      D(var0, var4, 0.08F);
      return new float[]{var19, var23, var19, var23};
   }

   private static float[] L(KnifeSupport2.TypeQ var0, long var1, Random var3, KnifeSupport2.TypeR var4) {
      KnifeUpdater2.ensureSourceClassInitialized();
      boolean var5 = var3.nextFloat() < 0.7F;
      float var6 = var5 ? 1.0F : -1.0F;
      float var7 = var4.Z;
      Vector3f var8 = new Vector3f();
      Vector3f var9 = new Vector3f();
      Vector3f var10 = new Vector3f();
      var0.left(0.0F, false);
      var8.set(0.15F + 0.03F * var6, -0.15F, -0.48F).mul(var7, 1.0F, 1.0F).add(N(var3, 0.02F), N(var3, 0.02F), 0.0F);
      float var11 = var5 ? 72.0F : 108.0F;
      KnifeSupport2.TypeU.p.direction(var11 + N(var3, 8.0F), -6.0F, var9);
      KnifeSupport2.TypeU.p.onward(var11, var10).mul(-var6);
      float var12 = var0.S + var4.time(0.17F + 0.05F * var3.nextFloat());
      var0.at(var12, var8, var9, var10);
      float var13 = var5 ? -42.0F + N(var3, 10.0F) : 222.0F + N(var3, 10.0F);
      Vector3f var14 = new Vector3f(var5 ? 0.25F : 0.02F, -0.31F, -0.53F).mul(var7, 1.0F, 1.0F);
      float var15 = arc(var0, KnifeSupport2.TypeU.p, var11, var13, -6.0F, 28.0F, var8, var14, 0.0F, 0.0F, var4.time(0.085F), KnifeSupport2.TypeE.f);
      var0.stop();
      var0.sound("flick", 0.7F, 1.0F + N(var3, 0.06F));
      float var16 = var0.S;
      Vector3f var17 = var0.hand(new Vector3f());
      Vector3f var18 = var0.aim(new Vector3f());
      Vector3f var19 = var0.lead(new Vector3f()).mul(var4.D);
      var18.fma(-0.12F, var19).normalize();
      var0.at(var0.S + var4.time(0.07F), var17, var18, var19);
      var0.at(var0.S + var4.time(0.22F + 0.1F * var3.nextFloat()), var17, var18, var19);
      return new float[]{var12, var0.S, var12, var16};
   }

   private static float[] F(KnifeSupport2.TypeQ var0, Random var1, KnifeSupport2.TypeR var2, long var3) {
      KnifeUpdater2.ensureSourceClassInitialized();
      Vector3f var5 = new Vector3f(0.13F, -0.2F, -0.46F).add(N(var1, 0.02F), N(var1, 0.02F), N(var1, 0.02F));
      Vector3f var6 = new Vector3f(-0.95F, 0.16F + N(var1, 0.08F), -0.3F + N(var1, 0.08F)).normalize();
      float var7 = var1.nextBoolean() ? 1.0F : -1.0F;
      Vector3f var8 = new Vector3f(0.0F, var7, 0.0F).fma(-var6.y * var7, var6).normalize();
      var0.left(0.0F, false);
      float var9 = var0.S + var2.time(0.3F);
      var0.at(var9, var5, var6, var8);
      var0.sound("swish", 0.3F, 1.25F);
      Quaternionf var10 = new Quaternionf();
      float var11 = (30.0F + 15.0F * var1.nextFloat()) * (var1.nextBoolean() ? 1 : -1);
      Vector3f var12 = new Vector3f();
      var10.rotationAxis((float)Math.toRadians(var11), var6).transform(var12.set(var8));
      var0.at(var0.S + var2.time(0.45F), new Vector3f(var5).add(0.0F, 0.008F, 0.0F), var6, var12);
      var0.fx("glint");
      var10.rotationAxis((float)Math.toRadians(-var11 * 0.5F), var6).transform(var12.set(var8));
      var0.at(var0.S + var2.time(0.42F), var5, var6, var12);
      return new float[]{var9, var0.S};
   }

   private static float[] W(long var0, KnifeSupport2.TypeQ var2, Random var3, KnifeSupport2.TypeR var4) {
      long var5 = 45846840131719L;
      KnifeUpdater2.ensureSourceClassInitialized();
      Vector3f var7 = new Vector3f(0.17F, -0.3F, -0.56F).add(N(var3, 0.02F), N(var3, 0.02F), 0.0F);
      Vector3f var8 = new Vector3f(0.09F, -0.25F, -0.8F).add(N(var3, 0.03F), N(var3, 0.02F), 0.0F);
      Vector3f var9 = new Vector3f(-0.6F + N(var3, 0.15F), 0.38F + N(var3, 0.1F), -2.6F);
      Vector3f var10 = new Vector3f(var9).sub(var7).normalize();
      Vector3f var11 = x(var10, var5, new Vector3f());
      float var12 = var2.S + var4.time(0.17F);
      var2.at(var12, var7, var10, var11);
      int var13 = var3.nextFloat() < 0.35F ? 2 : 1;
      float var14 = -1.0F;

      for (int var15 = 0; var15 < var13; var15++) {
         long var16 = 20289180849469L;
         int var18 = var16 < 0L ? -1 : 1;
         var2.at(var2.S + var4.time(0.09F), var8, var10, var11).stop();
         var2.sound("swish", 0.55F, 1.2F + N(var3, 0.05F));
         if (var14 < 0.0F) {
            var14 = var2.S;
         }

         var2.at(var2.S + var4.time(0.1F), new Vector3f(var8).add(0.0F, 0.0F, 0.012F), var10, var11);
         var2.at(var2.S + var4.time(0.16F), var7, var10, var11);
      }

      long var19 = 20289180849469L;
      int var17 = var19 < 0L ? -1 : 1;
      return new float[]{var12, var2.S};
   }

   private static float[] n(KnifeSupport2.TypeQ var0, long var1, Random var3, KnifeSupport2.TypeR var4, KnifeSupport2.TypeM var5) {
      Vector3f var6 = new Vector3f(0.22F, -0.06F, -0.42F).add(N(var3, 0.02F), N(var3, 0.02F), 0.0F);
      Vector3f var7 = new Vector3f(-0.18F, 0.8F, 0.55F).normalize();
      Vector3f var8 = new Vector3f(0.0F, 0.55F, -0.8F);
      float var9 = var0.S + var4.time(0.28F);
      var0.at(var9, var6, var7, var8);
      var0.at(var0.S + var4.time(0.2F + 0.15F * var3.nextFloat()), new Vector3f(var6).add(0.0F, 0.008F, 0.0F), var7, var8);
      KnifeSupport2.Data var10 = var4.first(H[6], H[0]);
      var5.D = var10;
      float[] var11 = cut(var0, var3, var4, var10, false, false, 0.9F);
      float var12 = var0.S;
      D(var0, var4, 0.1F);
      return new float[]{var9, var12, var11[1], var11[2]};
   }

   private static float[] h(long var0, KnifeSupport2.TypeQ var2, Random var3, KnifeSupport2.TypeR var4) {
      Vector3f var5 = new Vector3f(0.3F, -0.3F, -0.46F).add(N(var3, 0.015F), N(var3, 0.015F), 0.0F);
      Vector3f var6 = new Vector3f(0.06F + N(var3, 0.06F), 0.94F, 0.3F).normalize();
      Vector3f var7 = new Vector3f(-0.3F, 0.0F, -0.95F);
      var2.left(0.0F, false);
      float var8 = var2.S + var4.time(0.34F);
      var2.at(var8, var5, var6, var7);
      var2.sound("swish", 0.35F, 0.95F);
      var2.at(var2.S + var4.time(0.45F + 0.4F * var3.nextFloat()), new Vector3f(var5).add(0.0F, -0.006F, 0.0F), var6, var7);
      float var9 = var2.S;
      float var10 = (float)Math.toDegrees(Math.atan2(var6.y, -var6.z));
      Vector3f var11 = new Vector3f(var5).fma(-0.12F, KnifeSupport2.TypeU.M.round(new Vector3f(), 1.0F, var10, new Vector3f()));
      float var12 = 6.0F + N(var3, 8.0F);
      arc(
         var2,
         KnifeSupport2.TypeU.M,
         var10,
         var12,
         (float)Math.toDegrees(Math.asin(var6.x)),
         16.0F,
         var11,
         new Vector3f(0.13F, -0.31F, -0.52F),
         0.12F,
         0.03F,
         var4.time(0.3F),
         KnifeSupport2.TypeE.n
      );
      var2.sound("swish", 0.5F, 0.95F);
      var2.stop();
      return new float[]{var8, var2.S, var9, var2.S};
   }

   private static float[] z(KnifeSupport2.TypeQ var0, Random var1, long var2, KnifeSupport2.TypeR var4) {
      long var5 = 45846840131719L;
      KnifeUpdater2.ensureSourceClassInitialized();
      Vector3f var7 = var0.hand(new Vector3f()).add(0.0F, 0.02F, -0.03F);
      Vector3f var8 = new Vector3f(-0.3F, 0.62F, -0.72F).normalize();
      Vector3f var9 = new Vector3f(1.0F, 0.0F, 0.0F).fma(-var8.x, var8).normalize();
      Vector3f var10 = new Vector3f(var8).cross(var9).normalize();
      float var11 = 0.16F + 0.08F * var1.nextFloat();
      float var12 = var1.nextBoolean() ? 1.0F : -1.0F;
      float var13 = var1.nextFloat() < 0.5F ? 2.0F : 1.5F;
      Vector3f var14 = new Vector3f();
      Vector3f var15 = new Vector3f();
      float var16 = var0.S + var4.time(0.2F);
      var0.at(var16, var7, var8, x(var8, var5, var15));
      int var17 = (int)Math.ceil(var13 * 360.0F / 30.0F);
      float var18 = var4.time(var13 * 0.48F);

      for (int var19 = 1; var19 <= var17; var19++) {
         float var20 = (float)var19 / var17;
         float var21 = (float)Math.toRadians(var12 * var13 * 360.0F * var20);
         float var22 = Math.min(1.0F, Math.min(var20 * 4.0F, (1.0F - var20) * 4.0F + 0.25F));
         var14.set(var8).fma(var11 * var22 * (float)Math.cos(var21), var9).fma(var11 * var22 * (float)Math.sin(var21), var10).normalize();
         var0.at(var16 + var18 * KnifeSupport2.TypeE.n.time(var20), var7, var14, x(var14, var5, var15));
      }

      return new float[]{var16, var0.S};
   }

   public static KnifeTransform.TypeU attack(
      KnifeTransform.TypeH var0, Random var1, KnifeSupport2.TypeP var2, KnifeCurve var3, KnifeCurve var4, KnifeSupport2.TypeM var5
   ) {
      long var6 = 65603342308501L;
      long var8 = var6 ^ 35590551260367L;
      KnifeSupport2.TypeR var10 = new KnifeSupport2.TypeR(var0);
      KnifeTransform.TypeU var11 = null;
      float var12 = -1.0F;

      for (int var13 = 0; var13 < 12; var13++) {
         KnifeTransform.TypeU var14 = O(var10, var1, var13 == 0 ? var2 : KnifeSupport2.TypeP.v, var8, var3, var4, var5);
         float var15 = clearance(var14, var10);
         if (var15 >= 0.19F) {
            return var14;
         }

         if (var15 > var12) {
            var11 = var14;
            var12 = var15;
         }
      }

      return var11;
   }

   private static KnifeTransform.TypeU O(
      KnifeSupport2.TypeR var0, Random var1, KnifeSupport2.TypeP var2, long var3, KnifeCurve var5, KnifeCurve var6, KnifeSupport2.TypeM var7
   ) {
      KnifeUpdater2.ensureSourceClassInitialized();
      KnifeSupport2.TypeQ var8 = new KnifeSupport2.TypeQ(var0);
      Vector3f var9 = var5.rotation(new Quaternionf()).transform(new Vector3f(0.0F, 1.0F, 0.0F));
      KnifeSupport2.Data var10;
      if (var2 == KnifeSupport2.TypeP.H) {
         var10 = var0.first(H[6], H[0]);
         var7.D = var10;
      } else if (var2 == KnifeSupport2.TypeP.T) {
         var10 = var1.nextFloat() < 0.5F ? var0.first(H[4], H[1]) : var0.first(H[5], H[2]);
         var7.D = var10;
      } else {
         var10 = next(var1, var0, var7, var9, false);
      }

      float[] var11 = cut(var8, var1, var0, var10, true, false, var2 == KnifeSupport2.TypeP.H ? 0.85F : 1.0F);
      float var12 = var8.S;
      D(var8, var0, 0.11F);
      var8.pose(var8.S + var0.time(0.34F), var6, true);
      return var8.build(var10.name(), var11[3], var11[1], var11[2], 0.0F, var12, var12, 1.0F);
   }

   static float clearance(KnifeTransform.TypeU var0, KnifeSupport2.TypeR var1) {
      KnifeCurve var2 = new KnifeCurve();
      Quaternionf var3 = new Quaternionf();
      Vector3f var4 = new Vector3f();
      Vector3f var5 = new Vector3f();
      Vector3f var6 = new Vector3f();
      float var7 = 99.0F;

      for (float var8 = 0.0F; var8 <= var0.E + 1.0E-4F; var8 += 0.004166667F) {
         var0.at(var8, var2);
         var2.rotation(var3);
         var2.position(var4);

         for (Vector3f var10 : var1.X) {
            var3.transform(var5.set(var10)).add(var4);
            var5.sub(var4);
            float var11 = Math.clamp(-var4.dot(var5) / Math.max(1.0E-8F, var5.lengthSquared()), 0.0F, 1.0F);
            var6.set(var4).fma(var11, var5);
            if (var6.z < -0.02F) {
               var7 = Math.min(var7, var6.length());
            }
         }
      }

      return var7;
   }

   static float screenAngle(Vector3f var0) {
      return var0.x * var0.x + var0.y * var0.y < 0.04F ? Float.NaN : (float)Math.toDegrees(Math.atan2(var0.y, var0.x));
   }

   private static Vector3f x(Vector3f var0, long var1, Vector3f var3) {
      KnifeUpdater2.ensureSourceClassInitialized();
      long var4 = 45846840131719L;
      int var7 = var4 < 0L ? -1 : 1;
      var3.set(0.0F, -1.0F, 0.0F).fma(var0.y, var0);
      long var7x = 45846840131719L;
      int var8 = var7x < 0L ? -1 : 1;
      if (var3.lengthSquared() < 1.0E-6F) {
         var3.set(0.0F, 0.0F, -1.0F);
      }

      return var3.normalize();
   }

   static float wrap(float var0) {
      float var1 = var0 % 360.0F;
      return var1 > 180.0F ? var1 - 360.0F : (var1 < -180.0F ? var1 + 360.0F : var1);
   }

   private static float N(Random var0, float var1) {
      return (var0.nextFloat() * 2.0F - 1.0F) * var1;
   }

   private static int v(Random var0, long var1, float... var3) {
      float var4 = 0.0F;
      KnifeUpdater2.ensureSourceClassInitialized();

      for (float var8 : var3) {
         long var9 = 91495720675047L;
         int var11 = var9 < 0L ? -1 : 1;
         var4 += var8;
      }

      float var16 = var0.nextFloat();
      long var17 = 91495720675048L;
      int var15 = var17 < 0L ? -1 : 1;
      float var12 = var16 * var4;

      for (int var13 = 0; var13 < var3.length; var13++) {
         var12 -= var3[var13];
         long var14 = 91495720675048L;
         int var12x = var14 < 0L ? -1 : 1;
         if (var12 <= 0.0F) {
            return var13;
         }
      }

      long var18 = 91495720675047L;
      int var19 = var18 < 0L ? -1 : 1;
      return var3.length - 1;
   }

   record Data(String b, KnifeSupport2.TypeU i, float C, float G, float s, float q, float M, float j, float m, float Z, float t) {
      float sense() {
         return Math.signum(this.G - this.C);
      }

      public String name() {
         return this.b;
      }

      public KnifeSupport2.TypeU plane() {
         return this.i;
      }

      public float from() {
         return this.C;
      }

      public float to() {
         return this.G;
      }

      public float spread() {
         return this.s;
      }

      public float tilt() {
         return this.q;
      }

      public float tiltSpread() {
         return this.M;
      }

      public float x() {
         return this.j;
      }

      public float y() {
         return this.m;
      }

      public float z() {
         return this.Z;
      }

      public float weight() {
         return this.t;
      }

      public String stringState5() {
         return this.b;
      }

      public KnifeSupport2.TypeU knifeMotionPlanner_KnifeArcPlaneState1() {
         return this.i;
      }

      public float floatState16() {
         return this.C;
      }

      public float floatState28() {
         return this.G;
      }

      public float floatState39() {
         return this.s;
      }

      public float floatState48() {
         return this.q;
      }

      public float floatState27() {
         return this.M;
      }

      public float floatState38() {
         return this.j;
      }

      public float floatState59() {
         return this.m;
      }

      public float floatState43() {
         return this.Z;
      }

      public float floatState20() {
         return this.t;
      }
   }

   static enum TypeE {
      F,
      n,
      k,
      f,
      N;

      private static final long a = -2133095868335652840L;

      float speed(float var1) {
         return switch (this) {
            case F -> 0.22F + (float)Math.pow(Math.sin(Math.PI * Math.pow(var1, 0.85)), 1.2);
            case n -> 0.18F + (float)Math.sin(Math.PI * var1);
            case k -> Math.min(1.0F, Math.min(0.35F + 2.6F * var1, 0.35F + 2.6F * (1.0F - var1)));
            case f -> 0.15F + (float)Math.cos((Math.PI / 2) * var1);
            case N -> 0.15F + (float)Math.sin((Math.PI / 2) * var1);
         };
      }

      float time(float var1) {
         long var2 = 19731116460417L;
         long var4 = var2 ^ 83596725192184L;
         return this.s(var1, var4) / this.s(1.0F, var4);
      }

      private float s(float var1, long var2) {
         byte var4 = 24;
         float var5 = var1 / var4;
         KnifeUpdater2.ensureSourceClassInitialized();
         float var6 = 0.0F;

         for (int var7 = 0; var7 < var4; var7++) {
            long var8 = 103310659685497L;
            int var10 = var8 < 0L ? -1 : 1;
            var6 += var5 / this.speed((var7 + 0.5F) * var5);
         }

         long var11 = 103310659685496L;
         int var9 = var11 < 0L ? -1 : 1;
         return var6;
      }
   }

   public static final class TypeM {
      final String[] z = new String[3];
      KnifeSupport2.Data D;

      void played(String var1) {
         System.arraycopy(this.z, 0, this.z, 1, this.z.length - 1);
         this.z[0] = var1;
      }

      int recently(String var1) {
         int var2 = 0;

         for (String var6 : this.z) {
            if (var1.equals(var6)) {
               var2++;
            }
         }

         return var2;
      }
   }

   public static enum TypeP {
      v,
      H,
      T;
   }

   static final class TypeQ {
      final KnifeSupport2.TypeR w;
      final List<Float> T = new ArrayList<>();
      final List<float[]> O = new ArrayList<>();
      final List<Boolean> Q = new ArrayList<>();
      final List<Boolean> L = new ArrayList<>();
      final List<KnifeTransform.Data> p = new ArrayList<>();
      float S;
      float k = 1.0F;
      private final Vector3f j = new Vector3f();
      private final Vector3f C = new Vector3f();
      private static final String[] a = new String[]{"tLæec", "¿r"};
      private static final String[] b = new String[2];
      private static final long c = -6361344545359134714L;

      TypeQ(KnifeSupport2.TypeR var1) {
         this.w = var1;
      }

      KnifeSupport2.TypeQ at(float var1, Vector3f var2, Vector3f var3, Vector3f var4) {
         long var5 = 83186300969451L;
         long var7 = var5 ^ 42950872784071L;
         int var9 = (int)((var5 ^ 42950872784071L) >>> 48);
         int var10 = (int)((var5 ^ 42950872784071L) << 16 >>> 32);
         int var11 = (int)(var7 << 48 >>> 48);
         float[] var12 = new float[8];
         Vector3f var13 = new Vector3f(var3).normalize();
         this.j.set(var2).fma(this.w.W, var13);
         var12[0] = this.j.x;
         var12[1] = this.j.y;
         var12[2] = this.j.z;
         this.C.set(var4).mul(this.w.D);
         KnifeTransform.aimed(
            var12, new float[]{var13.x, var13.y, var13.z}, new float[]{this.C.x, this.C.y, this.C.z}, this.O.isEmpty() ? null : this.O.getLast()
         );
         return this.E(var1, (char)var9, var10, (short)var11, var12, false);
      }

      KnifeSupport2.TypeQ pose(float var1, KnifeCurve var2, boolean var3) {
         long var4 = 114621068778734L;
         long var6 = var4 ^ 4979385311682L;
         int var8 = (int)((var4 ^ 4979385311682L) >>> 48);
         int var9 = (int)((var4 ^ 4979385311682L) << 16 >>> 32);
         int var10 = (int)(var6 << 48 >>> 48);
         float[] var11 = (float[])var2.R.clone();
         if (!this.O.isEmpty()) {
            float[] var12 = this.O.getLast();
            var11[3] = KnifeTransform.nearest(var12[3], var11[3]);
            var11[5] = KnifeTransform.nearest(var12[5], var11[5]);
         }

         this.k = var11[6];
         return this.E(var1, (char)var8, var9, (short)var10, var11, var3);
      }

      private KnifeSupport2.TypeQ E(float var1, char var2, int var3, short var4, float[] var5, boolean var6) {
         KnifeUpdater2.ensureSourceClassInitialized();
         boolean var8 = this.T.isEmpty();
         int var9 = -17108;
         if (!var8 && var1 <= this.T.getLast() + 1.0E-4F) {
            var1 = this.T.getLast() + 0.001F;
         }

         boolean var7;
         label17: {
            var5[6] = this.k;
            this.T.add(var1);
            this.O.add(var5);
            boolean var10 = false;
            this.Q.add(false);
            if (var6) {
               var9 = this.O.size();
               var10 = false;
               if (var9 > 1) {
                  var7 = true;
                  break label17;
               }
            }

            var7 = false;
         }

         this.L.add(var7);
         this.S = var1;
         return this;
      }

      KnifeSupport2.TypeQ stop() {
         this.Q.set(this.Q.size() - 1, true);
         return this;
      }

      KnifeSupport2.TypeQ left(float var1, boolean var2) {
         this.k = var1;
         if (var2 && !this.O.isEmpty()) {
            this.O.getLast()[6] = var1;
         }

         return this;
      }

      KnifeSupport2.TypeQ sound(String var1, float var2, float var3) {
         this.p.add(new KnifeTransform.Data(this.S, "sound", var1, var2, var3));
         return this;
      }

      KnifeSupport2.TypeQ fx(String var1) {
         this.p.add(new KnifeTransform.Data(this.S, "fx", var1, 1.0F, 1.0F));
         return this;
      }

      Vector3f hand(Vector3f var1) {
         float[] var2 = this.O.getLast();
         Vector3f var3 = this.aim(new Vector3f());
         return var1.set(var2[0], var2[1], var2[2]).fma(-this.w.W, var3);
      }

      Vector3f aim(Vector3f var1) {
         float[] var2 = this.O.getLast();
         return KnifeCurve.rotation(var2[3], var2[4], var2[5], new Quaternionf()).transform(var1.set(0.0F, 1.0F, 0.0F));
      }

      Vector3f lead(Vector3f var1) {
         float[] var2 = this.O.getLast();
         return KnifeCurve.rotation(var2[3], var2[4], var2[5], new Quaternionf()).transform(var1.set(this.w.D, 0.0F, 0.0F));
      }

      KnifeTransform.TypeU build(String var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
         int var9 = this.T.size();
         float[] var10 = new float[var9];
         boolean[] var11 = new boolean[var9];
         boolean[] var12 = new boolean[var9];

         for (int var13 = 0; var13 < var9; var13++) {
            var10[var13] = this.T.get(var13);
            var11[var13] = this.Q.get(var13);
            var12[var13] = this.L.get(var13);
         }

         this.p.sort((var0, var1x) -> Float.compare(var0.time(), var1x.time()));
         float var14 = var10[var9 - 1];
         return new KnifeTransform.TypeU(
            var1, null, var14, var2, var3, var4, var5, var6, var7, var8, var10, this.O.toArray(new float[0][]), var11, var12, this.p
         );
      }
   }

   static final class TypeR {
      final KnifeTransform.TypeH T;
      final float A;
      final float Z;
      final float D;
      final float W;
      final float x;
      final float e;
      final float N;
      final float G;
      final Vector3f m;
      final List<Vector3f> X = new ArrayList<>();
      private static final String[] b = new String[19];

      TypeR(KnifeTransform.TypeH var1) {
         this.T = var1;
         this.A = var1.number("moves.tempo", 1.0F);
         this.Z = var1.number("moves.size", 1.0F);
         this.D = var1.number("moves.edge", 1.0F) < 0.0F ? -1.0F : 1.0F;
         this.W = var1.k * var1.M;
         this.x = var1.number("spin.speed", 2.2F);
         this.e = var1.number("moves.arc", 0.55F);
         this.N = var1.number("cut.radius", 0.17F);
         this.G = var1.number("cut.tilt", 1.0F);
         this.X.add(new Vector3f(0.0F, var1.number("end.tip", 0.75F), 0.0F).mul(var1.M));
         this.X.add(new Vector3f(0.0F, -var1.number("end.butt", 0.3F), 0.0F).mul(var1.M));
         if (var1.q.containsKey("end.guard")) {
            float var2 = var1.number("end.guard", 0.0F) * var1.M;
            this.X.add(new Vector3f(var2, 0.0F, 0.0F));
            this.X.add(new Vector3f(-var2, 0.0F, 0.0F));
         }

         if (var1.q.containsKey("end.side")) {
            this.X.add(new Vector3f(var1.number("end.side", 0.0F), var1.number("end.side.y", 0.0F), 0.0F).mul(var1.M));
         }

         this.m = new Vector3f(var1.number("cut.x", 0.1F), var1.number("cut.y", -0.31F), var1.number("cut.z", -0.5F));
      }

      float weight(String var1, float var2) {
         return this.T.number("w." + var1, var2);
      }

      float weight(KnifeSupport2.Data var1) {
         return var1.weight() * this.T.number("w.cut." + var1.name(), 1.0F);
      }

      KnifeSupport2.Data first(KnifeSupport2.Data... var1) {
         for (KnifeSupport2.Data var5 : var1) {
            if (this.weight(var5) > 0.0F) {
               return var5;
            }
         }

         return KnifeSupport2.H[0];
      }

      float time(float var1) {
         return var1 / this.A;
      }
   }

   static enum TypeU {
      p(1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, -1.0F),
      r(1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F),
      M(0.0F, 0.0F, -1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F);

      final Vector3f L;
      final Vector3f D;
      final Vector3f b;

      private TypeU(
         float nullxx,
         float nullxxx,
         float nullxxxx,
         float nullxxxxx,
         float nullxxxxxx,
         float nullxxxxxxx,
         float nullxxxxxxxx,
         float nullxxxxxxxxx,
         float nullxxxxxxxxxx
      ) {
         this.L = new Vector3f(nullxx, nullxxx, nullxxxx);
         this.D = new Vector3f(nullxxxxx, nullxxxxxx, nullxxxxxxx);
         this.b = new Vector3f(nullxxxxxxxx, nullxxxxxxxxx, nullxxxxxxxxxx);
      }

      Vector3f direction(float var1, float var2, Vector3f var3) {
         float var4 = (float)Math.toRadians(var1);
         float var5 = (float)Math.toRadians(var2);
         float var6 = (float)Math.cos(var5);
         return var3.set(this.L).mul((float)Math.cos(var4) * var6).fma((float)Math.sin(var4) * var6, this.D).fma((float)Math.sin(var5), this.b);
      }

      Vector3f onward(float var1, Vector3f var2) {
         float var3 = (float)Math.toRadians(var1);
         return var2.set(this.L).mul(-((float)Math.sin(var3))).fma((float)Math.cos(var3), this.D);
      }

      Vector3f round(Vector3f var1, float var2, float var3, Vector3f var4) {
         float var5 = (float)Math.toRadians(var3);
         return var4.set(var1).fma(var2 * (float)Math.cos(var5), this.L).fma(var2 * (float)Math.sin(var5), this.D);
      }
   }

   public static enum TypeX {
      X,
      q,
      i,
      a;
   }
}
