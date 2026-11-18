// Sourced By DevFFNightmare
package dev.purxiy.modules.ripple;

import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import java.util.Locale;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_4184;
import net.minecraft.class_5498;
import net.minecraft.class_746;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;

public final class RippleSupport {
   private static final float K = 1.55F;
   private static final float w = 1.1F;
   static final float fJ = 46.0F;
   private static final float P = 0.47F;
   private static final float N = 27.0F;
   private static final float k = 6.0F;
   private static final float[] J = new float[]{27.0F, -27.0F, 0.0F, 70.0F, -70.0F, 115.0F, -115.0F, 180.0F};
   private static final float[] H = new float[]{6.0F, 24.0F, 42.0F};
   private static final double d = 0.9;
   private static final double L = 0.7;
   private static final double X = 0.2;
   private static final float Q = 0.42F;
   private static final float z = 0.5F;
   private static final float F = 0.55F;
   private static final float A = 1.9F;
   private static final float Z = -28.0F;
   private static final float q = 62.0F;
   private static final Easing C = p(0.42F, 0.0F, 0.12F, 1.0F);
   private static final Easing M = p(0.45F, 0.0F, 0.25F, 1.0F);
   private static boolean l;
   private static boolean fn;
   
   private static class_5498 W;
   private static float e;
   private static float m;
   private static float r;
   private static float h;
   private static float f5;
   private static long fX = 0L;
   private static float Y;
   private static float G;
   private static boolean I;
   private static boolean n;
   private static boolean B;
   private static class_243 c;
   private static float b;
   private static float V;
   private static float E;
   private static float p;
   private static float D;
   private static float O;
   private static float x;
   private static float S;
   private static float g;
   private static boolean R;
   private static float j;
   private static float f;
   private static float y;
   private static float U;
   private static float f_;
   private static float o;
   private static float i;
   private static float fC;
   private static float t;
   private static float a;
   private static final double[] v = new double[]{0.0, 0.0, 0.0};
   private static float s;
   private static float T;
   private static boolean u;
   private static final String ab = "faced %.1f, engaged %s, wanted %s, path %.3f, side %.0f, orbit %.1f/%.1f, zoom %.2f, room %.2f, at %.2f %.2f %.2f yaw %.1f pitch %.1f, frame %.2f/%.2f, game fov %.1f";
   private static final long bb = 4449831283979190284L;

   private RippleSupport() {
   }

   public static boolean active() {
      return fn;
   }

   public static float progress() {
      return e;
   }

   public static boolean settled() {
      return fn && l && e >= 0.999F;
   }

   public static class_243 position() {
      return new class_243(v[0], v[1], v[2]);
   }

   static void open() {
      long var0 = 74494182037609L;
      int var2 = (int)((var0 ^ 71380049649954L) >>> 48);
      long var3 = (var0 ^ 71380049649954L) << 16 >>> 16;
      class_310 var5 = class_310.method_1551();
      class_746 var6 = var5.field_1724;
      if (var6 != null && var5.field_1687 != null) {
         if (!fn) {
            W = var5.field_1690.method_31044();
            class_4184 var7 = var5.field_1773.method_19418();
            float var8 = var5.method_61966().method_60637(false);
            c = var7.method_71156().method_1020(var6.method_30950(var8));
            b = var7.method_19330();
            V = var7.method_19329();
            I = !W.method_31034() && c.method_37267() > 1.0;
            B = false;
            var5.field_1690.method_31043(class_5498.field_26665);
            fn = true;
            e = 0.0F;
            E = var6.method_36454();
            var6.method_5636(E);
            var6.field_6220 = E;
            var6.method_5847(E);
            var6.field_6259 = E;
            W((short)var2, var5.field_1687, var6, var3);
            U = 0.0F;
            f = 0.0F;
            f_ = 0.0F;
            y = 0.0F;
            i = 1.0F;
            o = 1.0F;
            fC = -1.0F;
            a = 1.0F;
            R = false;
            fX = 0L;
         }

         l = true;
         Q(1.0F, 1.55F);
      }
   }

   static void close(boolean var0) {
      if (fn) {
         if (l) {
            f = class_3532.method_15393(f);
            U = class_3532.method_15393(U);
            fC = -1.0F;
         }

         l = false;
         Q(0.0F, var0 ? 0.55F : 1.1F);
      }
   }

   static void hurry() {
      if (fn && !l && !(G >= 0.0F)) {
         float var0 = f5 - h;
         float var1 = 0.3F * Math.max(0.35F, e);
         if (var0 > var1) {
            m = e;
            r = 0.0F;
            h = 0.0F;
            f5 = var1;
         }
      }
   }

   static void reset() {
      if (fn) {
         class_310 var0 = class_310.method_1551();
         if (W != null && var0.field_1690.method_31044() == class_5498.field_26665) {
            var0.field_1690.method_31043(W);
         }

         W = null;
         fn = false;
         l = false;
         e = 0.0F;
         G = -1.0F;
      }
   }

   private static void Q(float var0, float var1) {
      m = e;
      r = var0;
      h = 0.0F;
      float var2 = Math.abs(var0 - e);
      f5 = var1 * Math.max(0.35F, var2) / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   private static void H(long var0) {
      long var2 = 54526435527437L;
      RippleRenderer.ensureSourceClassInitialized();
      long var4 = System.nanoTime();
      float var6 = fX == 0L ? 0.0F : Math.min(0.1F, (float)(var4 - fX) / 1.0E9F);
      long var7 = 43026098195820L;
      if (43026098195820L == 0L) {
         boolean var10000 = false;
      } else {
         int var31 = var7 < 0L ? -1 : 1;
      }

      fX = var4;
      Y = var6;
      long var10 = 43026098195821L;
      if (43026098195821L == 0L) {
         boolean var32 = false;
      } else {
         int var33 = var10 < 0L ? -1 : 1;
      }

      if (G >= 0.0F) {
         long var13 = 43026098195820L;
         if (43026098195820L == 0L) {
            boolean var34 = false;
         } else {
            int var35 = var13 < 0L ? -1 : 1;
         }

         e = G;
      } else {
         h = Math.min(f5, h + var6);
         float var29 = f5 <= 0.0F ? 1.0F : h / f5;
         e = m + (r - m) * (r > m ? C : M).apply(var29);
      }

      float var30 = 1.0F - (float)Math.exp(-var6 * 9.0);
      long var14 = 43026098195821L;
      if (43026098195821L == 0L) {
         boolean var36 = false;
      } else {
         int var37 = var14 < 0L ? -1 : 1;
      }

      f = f + (U - f) * var30;
      y = y + (f_ - y) * var30;
      float var16 = o;
      float var17 = (i - o) * (1.0F - (float)Math.exp(-var6 * 7.0));
      long var18 = 43026098195820L;
      if (43026098195820L == 0L) {
         boolean var38 = false;
      } else {
         int var39 = var18 < 0L ? -1 : 1;
      }

      o = var16 + var17;
      float var21 = R ? 1.0F - (float)Math.exp(-var6 * 6.0) : 1.0F;
      O = O + (S - O) * var21;
      long var22 = 43026098195820L;
      if (43026098195820L == 0L) {
         boolean var40 = false;
      } else {
         int var41 = var22 < 0L ? -1 : 1;
      }

      x = x + (g - x) * var21;
      R = true;
      long var24 = 43026098195820L;
      if (43026098195820L == 0L) {
         boolean var42 = false;
      } else {
         int var43 = var24 < 0L ? -1 : 1;
      }

      if (fC >= 0.0F) {
         fC += var6;
         float var26;
         int var9 = (var26 = fC - t) == 0.0F ? 0 : (var26 < 0.0F ? -1 : 1);
         long var27 = 43026098195820L;
         if (43026098195820L == 0L) {
            boolean var44 = false;
         } else {
            int var45 = var27 < 0L ? -1 : 1;
         }

         if (var9 >= 0) {
            fC = -1.0F;
         }
      }

      if (!l && e <= 5.0E-4F && h >= f5 && G < 0.0F) {
         a(var2);
      }
   }

   private static void a(long var0) {
      RippleRenderer.ensureSourceClassInitialized();
      class_310 var2 = class_310.method_1551();
      if (W != null && var2.field_1690.method_31044() == class_5498.field_26665) {
         var2.field_1690.method_31043(W);
      }

      if (W == class_5498.field_26664) {
         var2.field_1773.field_4012.method_3215(class_1268.field_5808);
         var2.field_1773.field_4012.method_3215(class_1268.field_5810);
      }

      W = null;
      fn = false;
   }

   static void frame(float var0, float var1) {
      S = var0;
      g = var1;
   }

   static void drag(float var0, float var1) {
      U -= var0 * 0.32F;
      f_ = class_3532.method_15363(f_ + var1 * 0.24F, -28.0F - D, 62.0F - D);
      fC = -1.0F;
   }

   static void zoom(double var0) {
      i = class_3532.method_15363(i * (float)Math.pow(0.88, var0), 0.55F, 1.9F);
   }

   static void resetView() {
      U = Math.round(U / 360.0F) * 360.0F;
      f_ = 0.0F;
      i = 1.0F;
      fC = -1.0F;
   }

   static boolean moved() {
      return Math.abs(class_3532.method_15393(U)) > 0.5F || Math.abs(f_) > 0.5F || Math.abs(i - 1.0F) > 0.01F;
   }

   static void overlook(float var0, float var1) {
      f_ = class_3532.method_15363(var0, -28.0F - D, 62.0F - D);
      i = class_3532.method_15363(var1, 0.55F, 1.9F);
      fC = -1.0F;
   }

   static void turntable(float var0) {
      U = f = class_3532.method_15393(f);
      fC = 0.0F;
      t = var0 / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   static boolean turning() {
      return fC >= 0.0F;
   }

   static void hold(float var0) {
      G = var0 < 0.0F ? -1.0F : Math.min(1.0F, var0);
      if (G < 0.0F) {
         m = e;
         h = 0.0F;
         f5 = 0.4F;
      }
   }

   public static float fov(float var0) {
      long var1 = 63604979334242L;
      long var3 = var1 ^ 41121706014903L;
      if (!fn) {
         return var0;
      } else {
         j = var0;
         return class_3532.method_16439(B(var3, e), var0, U());
      }
   }

   private static float U() {
      double var0 = Math.tan(Math.toRadians(23.0)) / Math.max(0.42F, a);
      return (float)Math.toDegrees(2.0 * Math.atan(var0));
   }

   public static boolean hides(class_1297 var0) {
      class_310 var1 = class_310.method_1551();
      if (fn && var0 == var1.field_1724 && u) {
         class_243 var2 = var0.method_5836(var1.method_61966().method_60637(false));
         double var3 = v[0] - var2.field_1352;
         double var5 = v[1] - var2.field_1351;
         double var7 = v[2] - var2.field_1350;
         double var9 = 0.9 * Math.max(0.05, var0.method_17682() / 1.8);
         return var3 * var3 + var5 * var5 + var7 * var7 < var9 * var9;
      } else {
         return false;
      }
   }

   public static boolean place(class_4184 var0, class_1937 var1, class_1297 var2, float var3, double[] var4, float[] var5) {
      long var6 = 45031246055979L;
      long var8 = var6 ^ 17409316505414L;
      long var10 = var6 ^ 87168847984399L;
      long var12 = var6 ^ 53060431095275L;
      long var14 = var6 ^ 46229816060340L;
      class_310 var16 = class_310.method_1551();
      if (!fn) {
         return false;
      } else if (var0 != var16.field_1773.method_19418()) {
         return false;
      } else if (var16.field_1724 != null && var2 == var16.field_1724 && var1 == var16.field_1687) {
         H(var8);
         if (!fn) {
            return false;
         } else {
            class_746 var17 = var16.field_1724;
            float var18 = e;
            class_243 var19 = var17.method_30950(var3);
            float var20 = var17.method_17682() * ClientSupport3.bodyHeight(var17);
            class_243 var21 = var19.method_1031(0.0, var20 * 0.53, 0.0);
            float var22 = Easing.SMOOTHSTEP.apply(N((var18 - 0.72F) / 0.28F, var12));
            float var23 = fC >= 0.0F ? 360.0F * Easing.SINE_IN_OUT.apply(fC / t) : 0.0F;
            float var24 = E + p + (f + var23) * var22;
            float var25 = D + y * var22;
            float var26 = (float)(var20 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0)))) * o;
            class_243 var27 = class_243.method_1030(var25, var24 + 180.0F);
            class_243 var28 = var27.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
            class_243 var29 = var28.method_1036(var27);
            double var30 = Math.tan(Math.toRadians(23.0));
            double var32 = var30 * Q(var16);
            class_243 var34 = var21.method_1020(var27.method_1021(var26))
               .method_1020(var28.method_1021(O * var26 * var32))
               .method_1020(var29.method_1021(x * var26 * var30));
            float var35 = z(var1, var14, var17, var21, var34);
            a = var35 < a ? var35 : a + (var35 - a) * (1.0F - (float)Math.exp(-Y * 4.0));
            float var36 = Math.max(0.42F, a);
            class_243 var37 = var21.method_1020(var27.method_1021(var26 * var36))
               .method_1020(var28.method_1021(O * var26 * var32))
               .method_1020(var29.method_1021(x * var26 * var30));
            if (var36 > a + 0.001F || a < 0.999F) {
               float var38 = z(var1, var14, var17, var21, var37);
               if (var38 < 1.0F) {
                  var37 = var21.method_1019(var37.method_1020(var21).method_1021(var38));
               }
            }

            float var55 = (f + var23) * var22;
            if (I) {
               class_243 var56 = var19.method_1019(c).method_1020(var21);
               class_243 var57 = var37.method_1020(var21);
               double var58 = Math.atan2(var56.field_1350, var56.field_1352);
               double var59 = Math.atan2(var57.field_1350, var57.field_1352);
               double var60 = class_3532.method_15338(Math.toDegrees(var59 - var58)) * (float) (Math.PI / 180.0);
               double var61 = var58 + var60 * var18;
               double var62 = class_3532.method_16436(var18, var56.method_37267(), var57.method_37267());
               class_243 var63 = var21.method_1031(
                  Math.cos(var61) * var62, class_3532.method_16436(var18, var56.field_1351, var57.field_1351), Math.sin(var61) * var62
               );
               float var64 = z(var1, var14, var17, var21, var63);
               if (var64 < 1.0F) {
                  var63 = var21.method_1019(var63.method_1020(var21).method_1021(var64));
               }

               return L(var63, b + (class_3532.method_15393(E + p + 180.0F - b) + var55) * var18, class_3532.method_16439(var18, V, var25), var4, var5);
            } else {
               class_243 var39 = var17.method_5836(var3);
               float var40 = var17.method_5705(var3);
               float var41 = var17.method_5695(var3);
               class_243 var42 = class_243.method_1030(0.0F, var40);
               double var43 = var37.method_1022(var39);
               class_243 var45 = var39.method_1019(var42.method_1021(var43 * 0.42)).method_1031(0.0, var43 * 0.06, 0.0);
               class_243 var46 = var37.method_1020(var21);
               class_243 var47 = var37.method_1020(var46.method_1027() < 1.0E-6 ? class_243.field_1353 : var46.method_1029().method_1021(var43 * 0.15));
               if (!B && l) {
                  B = true;
                  n = q(var1, var17, var39, var45, var10, var47, var37);
               }

               if (n) {
                  var45 = var21.method_1019(var46.method_1021(0.35));
                  var47 = var21.method_1019(var46.method_1021(0.7));
               }

               float var48 = 1.0F - (float)Math.pow(1.0F - var18, 2.2);
               class_243 var49 = I(var39, var45, var47, var37, var48);
               float var50 = z(var1, var14, var17, var21, var49);
               if (var50 < 1.0F) {
                  var49 = var21.method_1019(var49.method_1020(var21).method_1021(var50));
               }

               float var51 = Easing.SINE_IN_OUT.apply(N((var18 - 0.02F) / 0.86F, var12));
               float var52 = class_3532.method_15393(E + p + 180.0F - var40);
               if (var52 > 0.0F) {
                  var52 -= 360.0F;
               }

               float var53 = var40 + (var52 + var55) * var51;
               float var54 = class_3532.method_16439(var51, var41, var25);
               return L(var49, var53, var54, var4, var5);
            }
         }
      } else {
         reset();
         return false;
      }
   }

   private static boolean L(class_243 var0, float var1, float var2, double[] var3, float[] var4) {
      v[0] = var0.field_1352;
      v[1] = var0.field_1351;
      v[2] = var0.field_1350;
      s = var1;
      T = var2;
      u = true;
      var3[0] = var0.field_1352;
      var3[1] = var0.field_1351;
      var3[2] = var0.field_1350;
      var4[0] = var1;
      var4[1] = var2;
      return true;
   }

   private static boolean q(class_1937 var0, class_1297 var1, class_243 var2, class_243 var3, long var4, class_243 var6, class_243 var7) {
      RippleRenderer.ensureSourceClassInitialized();
      class_243 var8 = var2;

      for (int var9 = 1; var9 <= (int)bb; var9++) {
         class_243 var10 = I(var2, var3, var6, var7, 1.0F - (float)Math.pow(1.0F - var9 / 12.0F, 2.2));
         long var11 = 114019358014756L;
         if (114019358014756L == 0L) {
            boolean var10000 = false;
         } else {
            int var14 = var11 < 0L ? -1 : 1;
         }

         if (var0.method_17742(new class_3959(var8, var10, class_3960.field_23142, class_242.field_1348, var1)).method_17783() != class_240.field_1333) {
            return true;
         }

         var8 = var10;
      }

      return false;
   }

   private static float B(long var0, float var2) {
      long var3 = 27173172375488L;
      return Easing.SMOOTHSTEP.apply(N(var2 * 1.15F, var3));
   }

   private static void W(short var0, class_1937 var1, class_746 var2, long var3) {
      long var5 = 3487274563915L;
      long var7 = var5 ^ 2013826751188L;
      class_310 var9 = class_310.method_1551();
      float var10 = var2.method_17682() * ClientSupport3.bodyHeight(var2);
      RippleRenderer.ensureSourceClassInitialized();
      class_243 var11 = var2.method_73189().method_1031(0.0, var10 * 0.53, 0.0);
      float var12 = (float)(var10 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0))));
      double var13 = Math.tan(Math.toRadians(23.0));
      double var15 = var13 * Q(var9);
      float[][] var17 = new float[H.length][J.length];

      for (int var18 = 0; var18 < H.length; var18++) {
         for (int var19 = 0; var19 < J.length; var19++) {
            class_243 var20 = W(var11, var2.method_36454() + J[var19], H[var18], var12 * 1.1F, var15, var13);
            long var21 = 3487274563914L;
            if (3487274563914L == 0L) {
               boolean var10000 = false;
            } else {
               int var45 = var21 < 0L ? -1 : 1;
            }

            var17[var18][var19] = z(var1, var7, var2, var11, var20);
         }
      }

      for (float var38 : new float[]{0.92F, 0.5F}) {
         for (int var22 = 0; var22 < H.length; var22++) {
            for (int var23 = 0; var23 < J.length; var23++) {
               float var24 = Math.abs(J[var23]);
               boolean var25 = false;
               float var26;
               int var27 = (var26 = var24 - 90.0F) == 0.0F ? 0 : (var26 < 0.0F ? -1 : 1);
               boolean var28 = false;
               if (var27 <= 0) {
                  float[] var29 = var17[var22];
                  var25 = false;
                  float var30;
                  int var31 = (var30 = var29[var23] - var38) == 0.0F ? 0 : (var30 < 0.0F ? -1 : 1);
                  var28 = false;
                  if (var31 >= 0) {
                     p = J[var23];
                     D = H[var22];
                     return;
                  }
               }
            }
         }

         long var40 = 3487274563914L;
         if (3487274563914L == 0L) {
            boolean var46 = false;
         } else {
            int var47 = var40 < 0L ? -1 : 1;
         }
      }

      int var33 = 0;
      int var35 = 0;
      int var37 = 0;

      while (var37 < H.length) {
         long var39 = 3487274563915L;
         if (3487274563915L == 0L) {
            boolean var48 = false;
         } else {
            int var49 = var39 < 0L ? -1 : 1;
         }

         for (int var41 = 0; var41 < J.length; var41++) {
            if (var17[var37][var41] > var17[var33][var35] + 0.05F) {
               var33 = var37;
               var35 = var41;
            }
         }

         var37++;
         long var42 = 3487274563915L;
         if (3487274563915L == 0L) {
            boolean var50 = false;
         } else {
            int var51 = var42 < 0L ? -1 : 1;
         }
      }

      p = J[var35];
      D = H[var33];
   }

   private static class_243 W(class_243 var0, float var1, float var2, float var3, double var4, double var6) {
      class_243 var8 = class_243.method_1030(var2, var1 + 180.0F);
      class_243 var9 = var8.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
      class_243 var10 = var9.method_1036(var8);
      return var0.method_1020(var8.method_1021(var3)).method_1020(var9.method_1021(O * var3 * var4)).method_1020(var10.method_1021(x * var3 * var6));
   }

   private static float z(class_1937 var0, long var1, class_1297 var3, class_243 var4, class_243 var5) {
      class_243 var6 = var5.method_1020(var4);
      RippleRenderer.ensureSourceClassInitialized();
      double var7 = var6.method_1033();
      if (var7 < 1.0E-4) {
         return 1.0F;
      } else {
         double var9 = var7;

         for (int var11 = 0; var11 < 5; var11++) {
            long var18 = 3294519845791L;
            if (3294519845791L == 0L) {
               boolean var10000 = false;
            } else {
               int var30 = var18 < 0L ? -1 : 1;
            }

            double var12 = var11 == 1 ? 0.1 : (var11 == 2 ? -0.1 : 0.0);
            long var21 = 3294519845791L;
            if (3294519845791L == 0L) {
               boolean var31 = false;
            } else {
               int var32 = var21 < 0L ? -1 : 1;
            }

            double var14 = var11 == 3 ? 0.1 : (var11 == 4 ? -0.1 : 0.0);
            long var23 = 3294519845791L;
            if (3294519845791L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var23 < 0L ? -1 : 1;
            }

            double var16;
            label94: {
               if (var11 != 1) {
                  long var25 = 3294519845791L;
                  if (3294519845791L == 0L) {
                     boolean var35 = false;
                  } else {
                     int var36 = var25 < 0L ? -1 : 1;
                  }

                  if (var11 != 2) {
                     var16 = var11 == 3 ? -0.1 : 0.0;
                     break label94;
                  }
               }

               var16 = 0.1;
            }

            class_243 var20 = var4.method_1031(var12, var14, var16);
            class_243 var28 = var5.method_1031(var12, var14, var16);
            class_3965 var22 = var0.method_17742(new class_3959(var20, var28, class_3960.field_23142, class_242.field_1348, var3));
            var23 = 3294519845790L;
            if (3294519845790L == 0L) {
               boolean var37 = false;
            } else {
               int var38 = var23 < 0L ? -1 : 1;
            }

            if (var22.method_17783() != class_240.field_1333) {
               var9 = Math.min(var9, var22.method_17784().method_1022(var20) - 0.2);
            }
         }

         long var27 = 3294519845791L;
         if (3294519845791L == 0L) {
            boolean var39 = false;
         } else {
            int var40 = var27 < 0L ? -1 : 1;
         }

         return (float)Math.clamp(var9 / var7, Math.min(1.0, 0.7 / var7), 1.0);
      }
   }

   private static double Q(class_310 var0) {
      return Math.max(0.2, (double)var0.method_22683().method_4489() / Math.max(1, var0.method_22683().method_4506()));
   }

   private static class_243 I(class_243 var0, class_243 var1, class_243 var2, class_243 var3, float var4) {
      float var5 = 1.0F - var4;
      double var6 = var5 * var5 * var5;
      double var8 = 3.0F * var5 * var5 * var4;
      double var10 = 3.0F * var5 * var4 * var4;
      double var12 = var4 * var4 * var4;
      return new class_243(
         var0.field_1352 * var6 + var1.field_1352 * var8 + var2.field_1352 * var10 + var3.field_1352 * var12,
         var0.field_1351 * var6 + var1.field_1351 * var8 + var2.field_1351 * var10 + var3.field_1351 * var12,
         var0.field_1350 * var6 + var1.field_1350 * var8 + var2.field_1350 * var10 + var3.field_1350 * var12
      );
   }

   private static Easing p(float var0, float var1, float var2, float var3) {
      return Easing.bezier(var0, var1, var2, var3);
   }

   private static float N(float var0, long var1) {
      RippleRenderer.ensureSourceClassInitialized();
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   static String describe() {
      return String.format(Locale.ROOT, ab, E, fn, l, e, p, f, y, o, a, v[0], v[1], v[2], s, T, O, x, j);
   }

}
