// Sourced By DevFFNightmare
package dev.purxiy.modules.auras;

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

public final class AuraSupport2 {
   private static final float e = 1.55F;
   private static final float I = 1.1F;
   static final float C = 46.0F;
   private static final float p = 0.47F;
   private static final float Z = 27.0F;
   private static final float H = 6.0F;
   private static final float[] N = new float[]{27.0F, -27.0F, 0.0F, 70.0F, -70.0F, 115.0F, -115.0F, 180.0F};
   private static final float[] v = new float[]{6.0F, 24.0F, 42.0F};
   private static final double F = 0.9;
   private static final double m = 0.7;
   private static final double g = 0.2;
   private static final float Q = 0.42F;
   private static final float y = 0.5F;
   private static final float R = 0.55F;
   private static final float rd = 1.9F;
   private static final float U = -28.0F;
   private static final float S = 62.0F;
   private static final Easing V = p(0.42F, 0.0F, 0.12F, 1.0F);
   private static final Easing M = p(0.45F, 0.0F, 0.25F, 1.0F);
   private static boolean J;
   private static boolean W;
   
   private static class_5498 D;
   private static float A;
   private static float t;
   private static float d;
   private static float X;
   private static float P;
   private static long u = 0L;
   private static float rb;
   private static float G;
   private static boolean n;
   private static boolean l;
   private static boolean rV;
   private static class_243 E;
   private static float L;
   private static float K;
   private static float c;
   private static float T;
   private static float f;
   private static float k;
   private static float O;
   private static float w;
   private static float x;
   private static boolean a;
   private static float rQ;
   private static float j;
   private static float r;
   private static float i;
   private static float rE;
   private static float B;
   private static float h;
   private static float z;
   private static float o;
   private static float q;
   private static final double[] b = new double[]{0.0, 0.0, 0.0};
   private static float Y;
   private static float rD;
   private static boolean s;
   private static final String ab = "faced %.1f, engaged %s, wanted %s, path %.3f, side %.0f, orbit %.1f/%.1f, zoom %.2f, room %.2f, at %.2f %.2f %.2f yaw %.1f pitch %.1f, frame %.2f/%.2f, game fov %.1f";
   private static final long bb = 1613779256473550860L;

   private AuraSupport2() {
   }

   public static boolean active() {
      return W;
   }

   public static float progress() {
      return A;
   }

   public static boolean settled() {
      return W && J && A >= 0.999F;
   }

   public static class_243 position() {
      return new class_243(b[0], b[1], b[2]);
   }

   static void open() {
      long var0 = 62448567858517L;
      long var2 = var0 ^ 20682885922081L;
      class_310 var4 = class_310.method_1551();
      class_746 var5 = var4.field_1724;
      if (var5 != null && var4.field_1687 != null) {
         if (!W) {
            D = var4.field_1690.method_31044();
            class_4184 var6 = var4.field_1773.method_19418();
            float var7 = var4.method_61966().method_60637(false);
            E = var6.method_71156().method_1020(var5.method_30950(var7));
            L = var6.method_19330();
            K = var6.method_19329();
            n = !D.method_31034() && E.method_37267() > 1.0;
            rV = false;
            var4.field_1690.method_31043(class_5498.field_26665);
            W = true;
            A = 0.0F;
            c = var5.method_36454();
            var5.method_5636(c);
            var5.field_6220 = c;
            var5.method_5847(c);
            var5.field_6259 = c;
            L(var2, var4.field_1687, var5);
            i = 0.0F;
            j = 0.0F;
            rE = 0.0F;
            r = 0.0F;
            h = 1.0F;
            B = 1.0F;
            z = -1.0F;
            q = 1.0F;
            a = false;
            u = 0L;
         }

         J = true;
         T(1.0F, 1.55F);
      }
   }

   static void close(boolean var0) {
      if (W) {
         if (J) {
            j = class_3532.method_15393(j);
            i = class_3532.method_15393(i);
            z = -1.0F;
         }

         J = false;
         T(0.0F, var0 ? 0.55F : 1.1F);
      }
   }

   static void hurry() {
      if (W && !J && !(G >= 0.0F)) {
         float var0 = P - X;
         float var1 = 0.3F * Math.max(0.35F, A);
         if (var0 > var1) {
            t = A;
            d = 0.0F;
            X = 0.0F;
            P = var1;
         }
      }
   }

   static void reset() {
      if (W) {
         class_310 var0 = class_310.method_1551();
         if (D != null && var0.field_1690.method_31044() == class_5498.field_26665) {
            var0.field_1690.method_31043(D);
         }

         D = null;
         W = false;
         J = false;
         A = 0.0F;
         G = -1.0F;
      }
   }

   private static void T(float var0, float var1) {
      t = A;
      d = var0;
      X = 0.0F;
      float var2 = Math.abs(var0 - A);
      P = var1 * Math.max(0.35F, var2) / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   private static void B(long var0) {
      long var2 = 134944522726920L;
      AuraStyle.ensureSourceClassInitialized();
      long var4 = System.nanoTime();
      float var6 = u == 0L ? 0.0F : Math.min(0.1F, (float)(var4 - u) / 1.0E9F);
      u = var4;
      rb = var6;
      float var7;
      int var8 = (var7 = G - 0.0F) == 0.0F ? 0 : (var7 < 0.0F ? -1 : 1);
      long var9 = 134274528985939L;
      if (134274528985939L == 0L) {
         boolean var10000 = false;
      } else {
         int var31 = var9 < 0L ? -1 : 1;
      }

      if (var8 >= 0) {
         long var12 = 134274528985939L;
         if (134274528985939L == 0L) {
            boolean var32 = false;
         } else {
            int var33 = var12 < 0L ? -1 : 1;
         }

         A = G;
      } else {
         long var27 = 134274528985939L;
         if (134274528985939L == 0L) {
            boolean var34 = false;
         } else {
            int var35 = var27 < 0L ? -1 : 1;
         }

         X = Math.min(P, X + var6);
         float var15 = P <= 0.0F ? 1.0F : X / P;
         A = t + (d - t) * (d > t ? V : M).apply(var15);
      }

      float var28 = 1.0F - (float)Math.exp(-var6 * 9.0);
      long var13 = 134274528985939L;
      if (134274528985939L == 0L) {
         boolean var36 = false;
      } else {
         int var37 = var13 < 0L ? -1 : 1;
      }

      j = j + (i - j) * var28;
      r = r + (rE - r) * var28;
      long var29 = 134274528985939L;
      if (134274528985939L == 0L) {
         boolean var38 = false;
      } else {
         int var39 = var29 < 0L ? -1 : 1;
      }

      B = B + (h - B) * (1.0F - (float)Math.exp(-var6 * 7.0));
      float var17 = a ? 1.0F - (float)Math.exp(-var6 * 6.0) : 1.0F;
      long var18 = 134274528985939L;
      if (134274528985939L == 0L) {
         boolean var40 = false;
      } else {
         int var41 = var18 < 0L ? -1 : 1;
      }

      k = k + (w - k) * var17;
      O = O + (x - O) * var17;
      a = true;
      long var20 = 134274528985939L;
      if (134274528985939L == 0L) {
         boolean var42 = false;
      } else {
         int var43 = var20 < 0L ? -1 : 1;
      }

      if (z >= 0.0F) {
         z += var6;
         float var22;
         var8 = (var22 = z - o) == 0.0F ? 0 : (var22 < 0.0F ? -1 : 1);
         long var23 = 134274528985940L;
         if (134274528985940L == 0L) {
            boolean var44 = false;
         } else {
            int var45 = var23 < 0L ? -1 : 1;
         }

         if (var8 >= 0) {
            z = -1.0F;
         }
      }

      if (!J) {
         long var30 = 134274528985940L;
         if (134274528985940L == 0L) {
            boolean var46 = false;
         } else {
            int var47 = var30 < 0L ? -1 : 1;
         }

         if (A <= 5.0E-4F && X >= P && G < 0.0F) {
            g(var2);
         }
      }
   }

   private static void g(long var0) {
      AuraStyle.ensureSourceClassInitialized();
      class_310 var2 = class_310.method_1551();
      if (D != null && var2.field_1690.method_31044() == class_5498.field_26665) {
         var2.field_1690.method_31043(D);
      }

      if (D == class_5498.field_26664) {
         var2.field_1773.field_4012.method_3215(class_1268.field_5808);
         var2.field_1773.field_4012.method_3215(class_1268.field_5810);
      }

      D = null;
      W = false;
   }

   static void frame(float var0, float var1) {
      w = var0;
      x = var1;
   }

   static void drag(float var0, float var1) {
      i -= var0 * 0.32F;
      rE = class_3532.method_15363(rE + var1 * 0.24F, -28.0F - f, 62.0F - f);
      z = -1.0F;
   }

   static void zoom(double var0) {
      h = class_3532.method_15363(h * (float)Math.pow(0.88, var0), 0.55F, 1.9F);
   }

   static void resetView() {
      i = Math.round(i / 360.0F) * 360.0F;
      rE = 0.0F;
      h = 1.0F;
      z = -1.0F;
   }

   static boolean moved() {
      return Math.abs(class_3532.method_15393(i)) > 0.5F || Math.abs(rE) > 0.5F || Math.abs(h - 1.0F) > 0.01F;
   }

   static void overlook(float var0, float var1) {
      rE = class_3532.method_15363(var0, -28.0F - f, 62.0F - f);
      h = class_3532.method_15363(var1, 0.55F, 1.9F);
      z = -1.0F;
   }

   static void turntable(float var0) {
      i = j = class_3532.method_15393(j);
      z = 0.0F;
      o = var0 / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   static boolean turning() {
      return z >= 0.0F;
   }

   static void hold(float var0) {
      G = var0 < 0.0F ? -1.0F : Math.min(1.0F, var0);
      if (G < 0.0F) {
         t = A;
         X = 0.0F;
         P = 0.4F;
      }
   }

   public static float fov(float var0) {
      long var1 = 110842207047852L;
      long var3 = var1 ^ 121376634768844L;
      if (!W) {
         return var0;
      } else {
         rQ = var0;
         return class_3532.method_16439(t(var3, A), var0, B());
      }
   }

   private static float B() {
      double var0 = Math.tan(Math.toRadians(23.0)) / Math.max(0.42F, q);
      return (float)Math.toDegrees(2.0 * Math.atan(var0));
   }

   public static boolean hides(class_1297 var0) {
      class_310 var1 = class_310.method_1551();
      if (W && var0 == var1.field_1724 && s) {
         class_243 var2 = var0.method_5836(var1.method_61966().method_60637(false));
         double var3 = b[0] - var2.field_1352;
         double var5 = b[1] - var2.field_1351;
         double var7 = b[2] - var2.field_1350;
         double var9 = 0.9 * Math.max(0.05, var0.method_17682() / 1.8);
         return var3 * var3 + var5 * var5 + var7 * var7 < var9 * var9;
      } else {
         return false;
      }
   }

   public static boolean place(class_4184 var0, class_1937 var1, class_1297 var2, float var3, double[] var4, float[] var5) {
      long var6 = 20937958691387L;
      long var8 = var6 ^ 115542237991279L;
      long var10 = var6 ^ 53957137861335L;
      long var12 = var6 ^ 31139022007571L;
      long var14 = var6 ^ 49073960562520L;
      int var16 = (int)((var6 ^ 49073960562520L) >>> 48);
      int var17 = (int)((var6 ^ 49073960562520L) << 16 >>> 32);
      int var18 = (int)(var14 << 48 >>> 48);
      class_310 var19 = class_310.method_1551();
      if (!W) {
         return false;
      } else if (var0 != var19.field_1773.method_19418()) {
         return false;
      } else if (var19.field_1724 != null && var2 == var19.field_1724 && var1 == var19.field_1687) {
         B(var8);
         if (!W) {
            return false;
         } else {
            class_746 var20 = var19.field_1724;
            float var21 = A;
            class_243 var22 = var20.method_30950(var3);
            float var23 = var20.method_17682() * ClientSupport3.bodyHeight(var20);
            class_243 var24 = var22.method_1031(0.0, var23 * 0.53, 0.0);
            float var25 = Easing.SMOOTHSTEP.apply(k(var10, (var21 - 0.72F) / 0.28F));
            float var26 = z >= 0.0F ? 360.0F * Easing.SINE_IN_OUT.apply(z / o) : 0.0F;
            float var27 = c + T + (j + var26) * var25;
            float var28 = f + r * var25;
            float var29 = (float)(var23 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0)))) * B;
            class_243 var30 = class_243.method_1030(var28, var27 + 180.0F);
            class_243 var31 = var30.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
            class_243 var32 = var31.method_1036(var30);
            double var33 = Math.tan(Math.toRadians(23.0));
            double var35 = var33 * A(var19);
            class_243 var37 = var24.method_1020(var30.method_1021(var29))
               .method_1020(var31.method_1021(k * var29 * var35))
               .method_1020(var32.method_1021(O * var29 * var33));
            float var38 = X(var1, var20, var12, var24, var37);
            q = var38 < q ? var38 : q + (var38 - q) * (1.0F - (float)Math.exp(-rb * 4.0));
            float var39 = Math.max(0.42F, q);
            class_243 var40 = var24.method_1020(var30.method_1021(var29 * var39))
               .method_1020(var31.method_1021(k * var29 * var35))
               .method_1020(var32.method_1021(O * var29 * var33));
            if (var39 > q + 0.001F || q < 0.999F) {
               float var41 = X(var1, var20, var12, var24, var40);
               if (var41 < 1.0F) {
                  var40 = var24.method_1019(var40.method_1020(var24).method_1021(var41));
               }
            }

            float var58 = (j + var26) * var25;
            if (n) {
               class_243 var59 = var22.method_1019(E).method_1020(var24);
               class_243 var60 = var40.method_1020(var24);
               double var61 = Math.atan2(var59.field_1350, var59.field_1352);
               double var62 = Math.atan2(var60.field_1350, var60.field_1352);
               double var63 = class_3532.method_15338(Math.toDegrees(var62 - var61)) * (float) (Math.PI / 180.0);
               double var64 = var61 + var63 * var21;
               double var65 = class_3532.method_16436(var21, var59.method_37267(), var60.method_37267());
               class_243 var66 = var24.method_1031(
                  Math.cos(var64) * var65, class_3532.method_16436(var21, var59.field_1351, var60.field_1351), Math.sin(var64) * var65
               );
               float var67 = X(var1, var20, var12, var24, var66);
               if (var67 < 1.0F) {
                  var66 = var24.method_1019(var66.method_1020(var24).method_1021(var67));
               }

               return p(var66, L + (class_3532.method_15393(c + T + 180.0F - L) + var58) * var21, class_3532.method_16439(var21, K, var28), var4, var5);
            } else {
               class_243 var42 = var20.method_5836(var3);
               float var43 = var20.method_5705(var3);
               float var44 = var20.method_5695(var3);
               class_243 var45 = class_243.method_1030(0.0F, var43);
               double var46 = var40.method_1022(var42);
               class_243 var48 = var42.method_1019(var45.method_1021(var46 * 0.42)).method_1031(0.0, var46 * 0.06, 0.0);
               class_243 var49 = var40.method_1020(var24);
               class_243 var50 = var40.method_1020(var49.method_1027() < 1.0E-6 ? class_243.field_1353 : var49.method_1029().method_1021(var46 * 0.15));
               if (!rV && J) {
                  rV = true;
                  l = L(var1, (short)var16, var20, var42, var17, var48, (short)var18, var50, var40);
               }

               if (l) {
                  var48 = var24.method_1019(var49.method_1021(0.35));
                  var50 = var24.method_1019(var49.method_1021(0.7));
               }

               float var51 = 1.0F - (float)Math.pow(1.0F - var21, 2.2);
               class_243 var52 = n(var42, var48, var50, var40, var51);
               float var53 = X(var1, var20, var12, var24, var52);
               if (var53 < 1.0F) {
                  var52 = var24.method_1019(var52.method_1020(var24).method_1021(var53));
               }

               float var54 = Easing.SINE_IN_OUT.apply(k(var10, (var21 - 0.02F) / 0.86F));
               float var55 = class_3532.method_15393(c + T + 180.0F - var43);
               if (var55 > 0.0F) {
                  var55 -= 360.0F;
               }

               float var56 = var43 + (var55 + var58) * var54;
               float var57 = class_3532.method_16439(var54, var44, var28);
               return p(var52, var56, var57, var4, var5);
            }
         }
      } else {
         reset();
         return false;
      }
   }

   private static boolean p(class_243 var0, float var1, float var2, double[] var3, float[] var4) {
      b[0] = var0.field_1352;
      b[1] = var0.field_1351;
      b[2] = var0.field_1350;
      Y = var1;
      rD = var2;
      s = true;
      var3[0] = var0.field_1352;
      var3[1] = var0.field_1351;
      var3[2] = var0.field_1350;
      var4[0] = var1;
      var4[1] = var2;
      return true;
   }

   private static boolean L(class_1937 var0, short var1, class_1297 var2, class_243 var3, int var4, class_243 var5, short var6, class_243 var7, class_243 var8) {
      AuraStyle.ensureSourceClassInitialized();
      class_243 var9 = var3;

      for (int var10 = 1; var10 <= (int)bb; var10++) {
         class_243 var11 = n(var3, var5, var7, var8, 1.0F - (float)Math.pow(1.0F - var10 / 12.0F, 2.2));
         if (var0.method_17742(new class_3959(var9, var11, class_3960.field_23142, class_242.field_1348, var2)).method_17783() != class_240.field_1333) {
            return true;
         }

         var9 = var11;
      }

      return false;
   }

   private static float t(long var0, float var2) {
      long var3 = 37487005014252L;
      return Easing.SMOOTHSTEP.apply(k(var3, var2 * 1.15F));
   }

   private static void L(long var0, class_1937 var2, class_746 var3) {
      long var4 = 16874420010792L;
      class_310 var6 = class_310.method_1551();
      AuraStyle.ensureSourceClassInitialized();
      float var7 = var3.method_17682() * ClientSupport3.bodyHeight(var3);
      class_243 var8 = var3.method_73189().method_1031(0.0, var7 * 0.53, 0.0);
      float var9 = (float)(var7 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0))));
      double var10 = Math.tan(Math.toRadians(23.0));
      double var12 = var10 * A(var6);
      float[][] var14 = new float[v.length][N.length];

      for (int var15 = 0; var15 < v.length; var15++) {
         for (int var16 = 0; var16 < N.length; var16++) {
            long var17 = 46198491007091L;
            if (46198491007091L == 0L) {
               boolean var10000 = false;
            } else {
               int var56 = var17 < 0L ? -1 : 1;
            }

            class_243 var20 = f(var8, var3.method_36454() + N[var16], v[var15], var9 * 1.1F, var12, var10);
            var14[var15][var16] = X(var2, var3, var4, var8, var20);
         }

         long var43 = 46198491007092L;
         if (46198491007092L == 0L) {
            boolean var57 = false;
         } else {
            int var58 = var43 < 0L ? -1 : 1;
         }
      }

      for (float var18 : new float[]{0.92F, 0.5F}) {
         int var19 = 0;

         while (var19 < v.length) {
            long var50 = 46198491007092L;
            if (46198491007092L == 0L) {
               boolean var59 = false;
            } else {
               int var60 = var50 < 0L ? -1 : 1;
            }

            for (int var23 = 0; var23 < N.length; var23++) {
               float var24 = N[var23];
               long var25 = 46198491007091L;
               if (46198491007091L == 0L) {
                  boolean var61 = false;
               } else {
                  int var62 = var25 < 0L ? -1 : 1;
               }

               var24 = Math.abs(var24);
               long var27 = 46198491007091L;
               if (46198491007091L == 0L) {
                  boolean var63 = false;
               } else {
                  int var64 = var27 < 0L ? -1 : 1;
               }

               float var30;
               int var31 = (var30 = var24 - 90.0F) == 0.0F ? 0 : (var30 < 0.0F ? -1 : 1);
               long var32 = 46198491007092L;
               if (46198491007092L == 0L) {
                  boolean var65 = false;
               } else {
                  int var66 = var32 < 0L ? -1 : 1;
               }

               if (var31 <= 0) {
                  float[] var34 = var14[var19];
                  long var35 = 46198491007092L;
                  if (46198491007092L == 0L) {
                     boolean var67 = false;
                  } else {
                     int var68 = var35 < 0L ? -1 : 1;
                  }

                  float var37;
                  int var38 = (var37 = var34[var23] - var18) == 0.0F ? 0 : (var37 < 0.0F ? -1 : 1);
                  long var39 = 46198491007091L;
                  if (46198491007091L == 0L) {
                     boolean var69 = false;
                  } else {
                     int var70 = var39 < 0L ? -1 : 1;
                  }

                  if (var38 >= 0) {
                     T = N[var23];
                     f = v[var19];
                     return;
                  }
               }
            }

            var19++;
            long var53 = 46198491007092L;
            if (46198491007092L == 0L) {
               boolean var71 = false;
            } else {
               int var72 = var53 < 0L ? -1 : 1;
            }
         }

         long var51 = 46198491007092L;
         if (46198491007092L == 0L) {
            boolean var73 = false;
         } else {
            int var74 = var51 < 0L ? -1 : 1;
         }
      }

      int var42 = 0;
      int var45 = 0;
      int var47 = 0;

      while (var47 < v.length) {
         long var48 = 46198491007091L;
         if (46198491007091L == 0L) {
            boolean var75 = false;
         } else {
            int var76 = var48 < 0L ? -1 : 1;
         }

         for (int var21 = 0; var21 < N.length; var21++) {
            float[] var22 = var14[var47];
            long var54 = 46198491007091L;
            if (46198491007091L == 0L) {
               boolean var77 = false;
            } else {
               int var78 = var54 < 0L ? -1 : 1;
            }

            if (var22[var21] > var14[var42][var45] + 0.05F) {
               var42 = var47;
               var45 = var21;
            }
         }

         var47++;
         long var52 = 46198491007091L;
         if (46198491007091L == 0L) {
            boolean var79 = false;
         } else {
            int var80 = var52 < 0L ? -1 : 1;
         }
      }

      long var49 = 46198491007092L;
      if (46198491007092L == 0L) {
         boolean var81 = false;
      } else {
         int var82 = var49 < 0L ? -1 : 1;
      }

      T = N[var45];
      f = v[var42];
   }

   private static class_243 f(class_243 var0, float var1, float var2, float var3, double var4, double var6) {
      class_243 var8 = class_243.method_1030(var2, var1 + 180.0F);
      class_243 var9 = var8.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
      class_243 var10 = var9.method_1036(var8);
      return var0.method_1020(var8.method_1021(var3)).method_1020(var9.method_1021(k * var3 * var4)).method_1020(var10.method_1021(O * var3 * var6));
   }

   private static float X(class_1937 var0, class_1297 var1, long var2, class_243 var4, class_243 var5) {
      AuraStyle.ensureSourceClassInitialized();
      class_243 var6 = var5.method_1020(var4);
      double var7 = var6.method_1033();
      if (var7 < 1.0E-4) {
         return 1.0F;
      } else {
         double var9 = var7;

         for (int var11 = 0; var11 < 5; var11++) {
            long var18 = 16874420010791L;
            if (16874420010791L == 0L) {
               boolean var10000 = false;
            } else {
               int var30 = var18 < 0L ? -1 : 1;
            }

            double var12 = var11 == 1 ? 0.1 : (var11 == 2 ? -0.1 : 0.0);
            long var21 = 16874420010792L;
            if (16874420010792L == 0L) {
               boolean var31 = false;
            } else {
               int var32 = var21 < 0L ? -1 : 1;
            }

            double var14 = var11 == 3 ? 0.1 : (var11 == 4 ? -0.1 : 0.0);
            long var23 = 16874420010792L;
            if (16874420010792L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var23 < 0L ? -1 : 1;
            }

            double var16;
            label94: {
               if (var11 != 1) {
                  long var25 = 16874420010791L;
                  if (16874420010791L == 0L) {
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
            class_3965 var22 = var0.method_17742(new class_3959(var20, var28, class_3960.field_23142, class_242.field_1348, var1));
            var23 = 16874420010792L;
            if (16874420010792L == 0L) {
               boolean var37 = false;
            } else {
               int var38 = var23 < 0L ? -1 : 1;
            }

            if (var22.method_17783() != class_240.field_1333) {
               var9 = Math.min(var9, var22.method_17784().method_1022(var20) - 0.2);
            }
         }

         double var27 = var9 / var7;
         long var13 = 16874420010791L;
         if (16874420010791L == 0L) {
            boolean var39 = false;
         } else {
            int var40 = var13 < 0L ? -1 : 1;
         }

         return (float)Math.clamp(var27, Math.min(1.0, 0.7 / var7), 1.0);
      }
   }

   private static double A(class_310 var0) {
      return Math.max(0.2, (double)var0.method_22683().method_4489() / Math.max(1, var0.method_22683().method_4506()));
   }

   private static class_243 n(class_243 var0, class_243 var1, class_243 var2, class_243 var3, float var4) {
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

   private static float k(long var0, float var2) {
      AuraStyle.ensureSourceClassInitialized();
      return var2 < 0.0F ? 0.0F : Math.min(var2, 1.0F);
   }

   static String describe() {
      return String.format(Locale.ROOT, ab, c, W, J, A, T, j, r, B, q, b[0], b[1], b[2], Y, rD, k, O, rQ);
   }

}
