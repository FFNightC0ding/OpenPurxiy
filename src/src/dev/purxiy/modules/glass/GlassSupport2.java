// Sourced By DevFFNightmare
package dev.purxiy.modules.glass;

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

public final class GlassSupport2 {
   private static final float U = 1.55F;
   private static final float K = 1.1F;
   static final float E = 46.0F;
   private static final float k = 0.47F;
   private static final float W = 27.0F;
   private static final float S = 6.0F;
   private static final float[] I = new float[]{27.0F, -27.0F, 0.0F, 70.0F, -70.0F, 115.0F, -115.0F, 180.0F};
   private static final float[] X = new float[]{6.0F, 24.0F, 42.0F};
   private static final double O = 0.9;
   private static final double V = 0.7;
   private static final double l = 0.2;
   private static final float r = 0.42F;
   private static final float x = 0.5F;
   private static final float m = 0.55F;
   private static final float q = 1.9F;
   private static final float J = -28.0F;
   private static final float a = 62.0F;
   private static final Easing Q = y(0.42F, 0.0F, 0.12F, 1.0F);
   private static final Easing v = y(0.45F, 0.0F, 0.25F, 1.0F);
   private static boolean e;
   private static boolean H;
   
   private static class_5498 t;
   private static float F;
   private static float Y;
   private static float u;
   private static float mu;
   private static float n;
   private static long g = 0L;
   private static float M;
   private static float R;
   private static boolean p;
   private static boolean Z;
   private static boolean h;
   private static class_243 c;
   private static float o;
   private static float d;
   private static float j;
   private static float mM;
   private static float D;
   private static float P;
   private static float mC;
   private static float mG;
   private static float f;
   private static boolean N;
   private static float w;
   private static float T;
   private static float s;
   private static float mj;
   private static float b;
   private static float z;
   private static float C;
   private static float y;
   private static float B;
   private static float i;
   private static final double[] L = new double[]{0.0, 0.0, 0.0};
   private static float mD;
   private static float A;
   private static boolean G;
   private static final String ab = "faced %.1f, engaged %s, wanted %s, path %.3f, side %.0f, orbit %.1f/%.1f, zoom %.2f, room %.2f, at %.2f %.2f %.2f yaw %.1f pitch %.1f, frame %.2f/%.2f, game fov %.1f";
   private static final long bb = -291604824581996532L;

   private GlassSupport2() {
   }

   public static boolean active() {
      return H;
   }

   public static float progress() {
      return F;
   }

   public static boolean settled() {
      return H && e && F >= 0.999F;
   }

   public static class_243 position() {
      return new class_243(L[0], L[1], L[2]);
   }

   static void open() {
      long var0 = 62548131145271L;
      long var2 = var0 ^ 39165909524594L;
      int var4 = (int)((var0 ^ 39165909524594L) >>> 48);
      int var5 = (int)((var0 ^ 39165909524594L) << 16 >>> 32);
      int var6 = (int)(var2 << 48 >>> 48);
      class_310 var7 = class_310.method_1551();
      class_746 var8 = var7.field_1724;
      if (var8 != null && var7.field_1687 != null) {
         if (!H) {
            t = var7.field_1690.method_31044();
            class_4184 var9 = var7.field_1773.method_19418();
            float var10 = var7.method_61966().method_60637(false);
            c = var9.method_71156().method_1020(var8.method_30950(var10));
            o = var9.method_19330();
            d = var9.method_19329();
            p = !t.method_31034() && c.method_37267() > 1.0;
            h = false;
            var7.field_1690.method_31043(class_5498.field_26665);
            H = true;
            F = 0.0F;
            j = var8.method_36454();
            var8.method_5636(j);
            var8.field_6220 = j;
            var8.method_5847(j);
            var8.field_6259 = j;
            i((char)var4, var5, var7.field_1687, var8, (char)var6);
            mj = 0.0F;
            T = 0.0F;
            b = 0.0F;
            s = 0.0F;
            C = 1.0F;
            z = 1.0F;
            y = -1.0F;
            i = 1.0F;
            N = false;
            g = 0L;
         }

         e = true;
         M(1.0F, 1.55F);
      }
   }

   static void close(boolean var0) {
      if (H) {
         if (e) {
            T = class_3532.method_15393(T);
            mj = class_3532.method_15393(mj);
            y = -1.0F;
         }

         e = false;
         M(0.0F, var0 ? 0.55F : 1.1F);
      }
   }

   static void hurry() {
      if (H && !e && !(R >= 0.0F)) {
         float var0 = n - mu;
         float var1 = 0.3F * Math.max(0.35F, F);
         if (var0 > var1) {
            Y = F;
            u = 0.0F;
            mu = 0.0F;
            n = var1;
         }
      }
   }

   static void reset() {
      if (H) {
         class_310 var0 = class_310.method_1551();
         if (t != null && var0.field_1690.method_31044() == class_5498.field_26665) {
            var0.field_1690.method_31043(t);
         }

         t = null;
         H = false;
         e = false;
         F = 0.0F;
         R = -1.0F;
      }
   }

   private static void M(float var0, float var1) {
      Y = F;
      u = var0;
      mu = 0.0F;
      float var2 = Math.abs(var0 - F);
      n = var1 * Math.max(0.35F, var2) / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   private static void G(long var0) {
      long var2 = 88168873835236L;
      short var4 = 20528;
      short var5 = 27239;
      int var6 = (int)(var2 << 48 >>> 48);
      GlassRenderer4.ensureSourceClassInitialized();
      long var7 = System.nanoTime();
      float var9 = g == 0L ? 0.0F : Math.min(0.1F, (float)(var7 - g) / 1.0E9F);
      long var10 = 6943533659552L;
      if (6943533659552L == 0L) {
         boolean var10000 = false;
      } else {
         int var40 = var10 < 0L ? -1 : 1;
      }

      g = var7;
      long var13 = 6943533659553L;
      if (6943533659553L == 0L) {
         boolean var41 = false;
      } else {
         int var42 = var13 < 0L ? -1 : 1;
      }

      M = var9;
      if (R >= 0.0F) {
         long var16 = 6943533659553L;
         if (6943533659553L == 0L) {
            boolean var43 = false;
         } else {
            int var44 = var16 < 0L ? -1 : 1;
         }

         F = R;
      } else {
         long var36 = 6943533659552L;
         if (6943533659552L == 0L) {
            boolean var45 = false;
         } else {
            int var46 = var36 < 0L ? -1 : 1;
         }

         mu = Math.min(n, mu + var9);
         long var19 = 6943533659553L;
         if (6943533659553L == 0L) {
            boolean var47 = false;
         } else {
            int var48 = var19 < 0L ? -1 : 1;
         }

         float var22 = n <= 0.0F ? 1.0F : mu / n;
         F = Y + (u - Y) * (u > Y ? Q : v).apply(var22);
      }

      float var37 = 1.0F - (float)Math.exp(-var9 * 9.0);
      long var17 = 6943533659553L;
      if (6943533659553L == 0L) {
         boolean var49 = false;
      } else {
         int var50 = var17 < 0L ? -1 : 1;
      }

      T = T + (mj - T) * var37;
      s = s + (b - s) * var37;
      long var20 = 6943533659552L;
      if (6943533659552L == 0L) {
         boolean var51 = false;
      } else {
         int var52 = var20 < 0L ? -1 : 1;
      }

      z = z + (C - z) * (1.0F - (float)Math.exp(-var9 * 7.0));
      float var23 = N ? 1.0F - (float)Math.exp(-var9 * 6.0) : 1.0F;
      long var24 = 6943533659552L;
      if (6943533659552L == 0L) {
         boolean var53 = false;
      } else {
         int var54 = var24 < 0L ? -1 : 1;
      }

      P = P + (mG - P) * var23;
      mC = mC + (f - mC) * var23;
      N = true;
      long var26 = 6943533659553L;
      if (6943533659553L == 0L) {
         boolean var55 = false;
      } else {
         int var56 = var26 < 0L ? -1 : 1;
      }

      if (y >= 0.0F) {
         y += var9;
         float var29;
         int var30 = (var29 = y - B) == 0.0F ? 0 : (var29 < 0.0F ? -1 : 1);
         long var31 = 6943533659552L;
         if (6943533659552L == 0L) {
            boolean var57 = false;
         } else {
            int var58 = var31 < 0L ? -1 : 1;
         }

         if (var30 >= 0) {
            y = -1.0F;
         }
      }

      if (!e) {
         long var38 = 6943533659552L;
         if (6943533659552L == 0L) {
            boolean var59 = false;
         } else {
            int var60 = var38 < 0L ? -1 : 1;
         }

         if (F <= 5.0E-4F && mu >= n) {
            float var39;
            int var32 = (var39 = R - 0.0F) == 0.0F ? 0 : (var39 < 0.0F ? -1 : 1);
            long var33 = 6943533659552L;
            if (6943533659552L == 0L) {
               boolean var61 = false;
            } else {
               int var62 = var33 < 0L ? -1 : 1;
            }

            if (var32 < 0) {
               t(var4, var5, var6);
            }
         }
      }
   }

   private static void t(int var0, int var1, int var2) {
      GlassRenderer4.ensureSourceClassInitialized();
      class_310 var3 = class_310.method_1551();
      if (t != null && var3.field_1690.method_31044() == class_5498.field_26665) {
         var3.field_1690.method_31043(t);
      }

      if (t == class_5498.field_26664) {
         var3.field_1773.field_4012.method_3215(class_1268.field_5808);
         var3.field_1773.field_4012.method_3215(class_1268.field_5810);
      }

      t = null;
      H = false;
   }

   static void frame(float var0, float var1) {
      mG = var0;
      f = var1;
   }

   static void drag(float var0, float var1) {
      mj -= var0 * 0.32F;
      b = class_3532.method_15363(b + var1 * 0.24F, -28.0F - D, 62.0F - D);
      y = -1.0F;
   }

   static void zoom(double var0) {
      C = class_3532.method_15363(C * (float)Math.pow(0.88, var0), 0.55F, 1.9F);
   }

   static void resetView() {
      mj = Math.round(mj / 360.0F) * 360.0F;
      b = 0.0F;
      C = 1.0F;
      y = -1.0F;
   }

   static boolean moved() {
      return Math.abs(class_3532.method_15393(mj)) > 0.5F || Math.abs(b) > 0.5F || Math.abs(C - 1.0F) > 0.01F;
   }

   static void overlook(float var0, float var1) {
      b = class_3532.method_15363(var0, -28.0F - D, 62.0F - D);
      C = class_3532.method_15363(var1, 0.55F, 1.9F);
      y = -1.0F;
   }

   static void turntable(float var0) {
      mj = T = class_3532.method_15393(T);
      y = 0.0F;
      B = var0 / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   static boolean turning() {
      return y >= 0.0F;
   }

   static void hold(float var0) {
      R = var0 < 0.0F ? -1.0F : Math.min(1.0F, var0);
      if (R < 0.0F) {
         Y = F;
         mu = 0.0F;
         n = 0.4F;
      }
   }

   public static float fov(float var0) {
      long var1 = 99481080807363L;
      long var3 = var1 ^ 63064515391732L;
      if (!H) {
         return var0;
      } else {
         w = var0;
         return class_3532.method_16439(e(F, var3), var0, z());
      }
   }

   private static float z() {
      double var0 = Math.tan(Math.toRadians(23.0)) / Math.max(0.42F, i);
      return (float)Math.toDegrees(2.0 * Math.atan(var0));
   }

   public static boolean hides(class_1297 var0) {
      class_310 var1 = class_310.method_1551();
      if (H && var0 == var1.field_1724 && G) {
         class_243 var2 = var0.method_5836(var1.method_61966().method_60637(false));
         double var3 = L[0] - var2.field_1352;
         double var5 = L[1] - var2.field_1351;
         double var7 = L[2] - var2.field_1350;
         double var9 = 0.9 * Math.max(0.05, var0.method_17682() / 1.8);
         return var3 * var3 + var5 * var5 + var7 * var7 < var9 * var9;
      } else {
         return false;
      }
   }

   public static boolean place(class_4184 var0, class_1937 var1, class_1297 var2, float var3, double[] var4, float[] var5) {
      long var6 = 115349011930633L;
      long var8 = var6 ^ 121738201066408L;
      long var10 = var6 ^ 108415083864041L;
      long var12 = var6 ^ 13049644225111L;
      long var14 = var6 ^ 116114018130170L;
      int var16 = (int)((var6 ^ 116114018130170L) >>> 48);
      int var17 = (int)((var6 ^ 116114018130170L) << 16 >>> 32);
      int var18 = (int)(var14 << 48 >>> 48);
      class_310 var19 = class_310.method_1551();
      if (!H) {
         return false;
      } else if (var0 != var19.field_1773.method_19418()) {
         return false;
      } else if (var19.field_1724 != null && var2 == var19.field_1724 && var1 == var19.field_1687) {
         G(var8);
         if (!H) {
            return false;
         } else {
            class_746 var20 = var19.field_1724;
            float var21 = F;
            class_243 var22 = var20.method_30950(var3);
            float var23 = var20.method_17682() * ClientSupport3.bodyHeight(var20);
            class_243 var24 = var22.method_1031(0.0, var23 * 0.53, 0.0);
            float var25 = Easing.SMOOTHSTEP.apply(N(var12, (var21 - 0.72F) / 0.28F));
            float var26 = y >= 0.0F ? 360.0F * Easing.SINE_IN_OUT.apply(y / B) : 0.0F;
            float var27 = j + mM + (T + var26) * var25;
            float var28 = D + s * var25;
            float var29 = (float)(var23 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0)))) * z;
            class_243 var30 = class_243.method_1030(var28, var27 + 180.0F);
            class_243 var31 = var30.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
            class_243 var32 = var31.method_1036(var30);
            double var33 = Math.tan(Math.toRadians(23.0));
            double var35 = var33 * X(var19);
            class_243 var37 = var24.method_1020(var30.method_1021(var29))
               .method_1020(var31.method_1021(P * var29 * var35))
               .method_1020(var32.method_1021(mC * var29 * var33));
            float var38 = v(var1, var20, (char)var16, var17, var24, var18, var37);
            i = var38 < i ? var38 : i + (var38 - i) * (1.0F - (float)Math.exp(-M * 4.0));
            float var39 = Math.max(0.42F, i);
            class_243 var40 = var24.method_1020(var30.method_1021(var29 * var39))
               .method_1020(var31.method_1021(P * var29 * var35))
               .method_1020(var32.method_1021(mC * var29 * var33));
            if (var39 > i + 0.001F || i < 0.999F) {
               float var41 = v(var1, var20, (char)var16, var17, var24, var18, var40);
               if (var41 < 1.0F) {
                  var40 = var24.method_1019(var40.method_1020(var24).method_1021(var41));
               }
            }

            float var58 = (T + var26) * var25;
            if (p) {
               class_243 var59 = var22.method_1019(c).method_1020(var24);
               class_243 var60 = var40.method_1020(var24);
               double var61 = Math.atan2(var59.field_1350, var59.field_1352);
               double var62 = Math.atan2(var60.field_1350, var60.field_1352);
               double var63 = class_3532.method_15338(Math.toDegrees(var62 - var61)) * (float) (Math.PI / 180.0);
               double var64 = var61 + var63 * var21;
               double var65 = class_3532.method_16436(var21, var59.method_37267(), var60.method_37267());
               class_243 var66 = var24.method_1031(
                  Math.cos(var64) * var65, class_3532.method_16436(var21, var59.field_1351, var60.field_1351), Math.sin(var64) * var65
               );
               float var67 = v(var1, var20, (char)var16, var17, var24, var18, var66);
               if (var67 < 1.0F) {
                  var66 = var24.method_1019(var66.method_1020(var24).method_1021(var67));
               }

               return x(var66, o + (class_3532.method_15393(j + mM + 180.0F - o) + var58) * var21, class_3532.method_16439(var21, d, var28), var4, var5);
            } else {
               class_243 var42 = var20.method_5836(var3);
               float var43 = var20.method_5705(var3);
               float var44 = var20.method_5695(var3);
               class_243 var45 = class_243.method_1030(0.0F, var43);
               double var46 = var40.method_1022(var42);
               class_243 var48 = var42.method_1019(var45.method_1021(var46 * 0.42)).method_1031(0.0, var46 * 0.06, 0.0);
               class_243 var49 = var40.method_1020(var24);
               class_243 var50 = var40.method_1020(var49.method_1027() < 1.0E-6 ? class_243.field_1353 : var49.method_1029().method_1021(var46 * 0.15));
               if (!h && e) {
                  h = true;
                  Z = K(var10, var1, var20, var42, var48, var50, var40);
               }

               if (Z) {
                  var48 = var24.method_1019(var49.method_1021(0.35));
                  var50 = var24.method_1019(var49.method_1021(0.7));
               }

               float var51 = 1.0F - (float)Math.pow(1.0F - var21, 2.2);
               class_243 var52 = I(var42, var48, var50, var40, var51);
               float var53 = v(var1, var20, (char)var16, var17, var24, var18, var52);
               if (var53 < 1.0F) {
                  var52 = var24.method_1019(var52.method_1020(var24).method_1021(var53));
               }

               float var54 = Easing.SINE_IN_OUT.apply(N(var12, (var21 - 0.02F) / 0.86F));
               float var55 = class_3532.method_15393(j + mM + 180.0F - var43);
               if (var55 > 0.0F) {
                  var55 -= 360.0F;
               }

               float var56 = var43 + (var55 + var58) * var54;
               float var57 = class_3532.method_16439(var54, var44, var28);
               return x(var52, var56, var57, var4, var5);
            }
         }
      } else {
         reset();
         return false;
      }
   }

   private static boolean x(class_243 var0, float var1, float var2, double[] var3, float[] var4) {
      L[0] = var0.field_1352;
      L[1] = var0.field_1351;
      L[2] = var0.field_1350;
      mD = var1;
      A = var2;
      G = true;
      var3[0] = var0.field_1352;
      var3[1] = var0.field_1351;
      var3[2] = var0.field_1350;
      var4[0] = var1;
      var4[1] = var2;
      return true;
   }

   private static boolean K(long var0, class_1937 var2, class_1297 var3, class_243 var4, class_243 var5, class_243 var6, class_243 var7) {
      class_243 var8 = var4;
      GlassRenderer4.ensureSourceClassInitialized();

      for (int var9 = 1; var9 <= (int)bb; var9++) {
         class_243 var10 = I(var4, var5, var6, var7, 1.0F - (float)Math.pow(1.0F - var9 / 12.0F, 2.2));
         long var11 = 11487199248863L;
         if (11487199248863L == 0L) {
            boolean var10000 = false;
         } else {
            int var14 = var11 < 0L ? -1 : 1;
         }

         if (var2.method_17742(new class_3959(var8, var10, class_3960.field_23142, class_242.field_1348, var3)).method_17783() != class_240.field_1333) {
            return true;
         }

         var8 = var10;
      }

      return false;
   }

   private static float e(float var0, long var1) {
      long var3 = 109086054616158L;
      return Easing.SMOOTHSTEP.apply(N(var3, var0 * 1.15F));
   }

   private static void i(char var0, int var1, class_1937 var2, class_746 var3, char var4) {
      long var5 = 30219813291589L;
      long var7 = var5 ^ 28648355709110L;
      int var9 = (int)((var5 ^ 28648355709110L) >>> 48);
      int var10 = (int)((var5 ^ 28648355709110L) << 16 >>> 32);
      int var11 = (int)(var7 << 48 >>> 48);
      GlassRenderer4.ensureSourceClassInitialized();
      class_310 var12 = class_310.method_1551();
      float var13 = var3.method_17682() * ClientSupport3.bodyHeight(var3);
      class_243 var14 = var3.method_73189().method_1031(0.0, var13 * 0.53, 0.0);
      float var15 = (float)(var13 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0))));
      double var16 = Math.tan(Math.toRadians(23.0));
      double var18 = var16 * X(var12);
      float[][] var20 = new float[X.length][I.length];

      for (int var21 = 0; var21 < X.length; var21++) {
         for (int var22 = 0; var22 < I.length; var22++) {
            class_243 var23 = z(var14, var3.method_36454() + I[var22], X[var21], var15 * 1.1F, var18, var16);
            var20[var21][var22] = v(var2, var3, (char)var9, var10, var14, var11, var23);
         }
      }

      for (float var24 : new float[]{0.92F, 0.5F}) {
         for (int var25 = 0; var25 < X.length; var25++) {
            for (int var26 = 0; var26 < I.length; var26++) {
               float var27 = Math.abs(I[var26]);
               float var28;
               int var29 = (var28 = var27 - 90.0F) == 0.0F ? 0 : (var28 < 0.0F ? -1 : 1);
               if (var29 <= 0) {
                  float var30;
                  int var31 = (var30 = var20[var25][var26] - var24) == 0.0F ? 0 : (var30 < 0.0F ? -1 : 1);
                  if (var31 >= 0) {
                     mM = I[var26];
                     D = X[var25];
                     return;
                  }
               }
            }
         }
      }

      int var33 = 0;
      int var35 = 0;

      for (int var37 = 0; var37 < X.length; var37++) {
         for (int var38 = 0; var38 < I.length; var38++) {
            if (var20[var37][var38] > var20[var33][var35] + 0.05F) {
               var33 = var37;
               var35 = var38;
            }
         }
      }

      mM = I[var35];
      D = X[var33];
   }

   private static class_243 z(class_243 var0, float var1, float var2, float var3, double var4, double var6) {
      class_243 var8 = class_243.method_1030(var2, var1 + 180.0F);
      class_243 var9 = var8.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
      class_243 var10 = var9.method_1036(var8);
      return var0.method_1020(var8.method_1021(var3)).method_1020(var9.method_1021(P * var3 * var4)).method_1020(var10.method_1021(mC * var3 * var6));
   }

   private static float v(class_1937 var0, class_1297 var1, char var2, int var3, class_243 var4, int var5, class_243 var6) {
      class_243 var7 = var6.method_1020(var4);
      GlassRenderer4.ensureSourceClassInitialized();
      double var8 = var7.method_1033();
      if (var8 < 1.0E-4) {
         return 1.0F;
      } else {
         double var10 = var8;

         for (int var12 = 0; var12 < 5; var12++) {
            double var13;
            double var15;
            double var17;
            label46: {
               int var19 = 18163;
               var13 = var12 == 1 ? 0.1 : (var12 == 2 ? -0.1 : 0.0);
               var19 = 24260140;
               var15 = var12 == 3 ? 0.1 : (var12 == 4 ? -0.1 : 0.0);
               int var20 = 0;
               if (var12 != 1) {
                  var20 = 24260140;
                  if (var12 != 2) {
                     var17 = var12 == 3 ? -0.1 : 0.0;
                     break label46;
                  }
               }

               var17 = 0.1;
            }

            class_243 var21 = var4.method_1031(var13, var15, var17);
            class_243 var22 = var6.method_1031(var13, var15, var17);
            class_3965 var23 = var0.method_17742(new class_3959(var21, var22, class_3960.field_23142, class_242.field_1348, var1));
            if (var23.method_17783() != class_240.field_1333) {
               var10 = Math.min(var10, var23.method_17784().method_1022(var21) - 0.2);
            }
         }

         return (float)Math.clamp(var10 / var8, Math.min(1.0, 0.7 / var8), 1.0);
      }
   }

   private static double X(class_310 var0) {
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

   private static Easing y(float var0, float var1, float var2, float var3) {
      return Easing.bezier(var0, var1, var2, var3);
   }

   private static float N(long var0, float var2) {
      GlassRenderer4.ensureSourceClassInitialized();
      return var2 < 0.0F ? 0.0F : Math.min(var2, 1.0F);
   }

   static String describe() {
      return String.format(Locale.ROOT, ab, j, H, e, F, mM, T, s, z, i, L[0], L[1], L[2], mD, A, P, mC, w);
   }

}
