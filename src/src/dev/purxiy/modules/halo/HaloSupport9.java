// Sourced By DevFFNightmare
package dev.purxiy.modules.halo;

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

public final class HaloSupport9 {
   private static final float Z = 1.55F;
   private static final float Q = 1.1F;
   static final float u = 46.0F;
   private static final float f = 0.47F;
   private static final float C = 27.0F;
   private static final float Y = 6.0F;
   private static final float[] K = new float[]{27.0F, -27.0F, 0.0F, 70.0F, -70.0F, 115.0F, -115.0F, 180.0F};
   private static final float[] D = new float[]{6.0F, 24.0F, 42.0F};
   private static final double A = 0.9;
   private static final double T = 0.7;
   private static final double i = 0.2;
   private static final float d = 0.42F;
   private static final float wD = 0.5F;
   private static final float M = 0.55F;
   private static final float l = 1.9F;
   private static final float n = -28.0F;
   private static final float B = 62.0F;
   private static final Easing g = U(0.42F, 0.0F, 0.12F, 1.0F);
   private static final Easing e = U(0.45F, 0.0F, 0.25F, 1.0F);
   private static boolean V;
   private static boolean wE;
   
   private static class_5498 L;
   private static float q;
   private static float wQ;
   private static float E;
   private static float G;
   private static float v;
   private static long N = 0L;
   private static float S;
   private static float o;
   private static boolean wb;
   private static boolean t;
   private static boolean H;
   private static class_243 P;
   private static float J;
   private static float h;
   private static float wI;
   private static float O;
   private static float F;
   private static float W;
   private static float b;
   private static float wF;
   private static float R;
   private static boolean m;
   private static float X;
   private static float w;
   private static float a;
   private static float y;
   private static float I;
   private static float s;
   private static float x;
   private static float c;
   private static float z;
   private static float p;
   private static final double[] j = new double[]{0.0, 0.0, 0.0};
   private static float r;
   private static float U;
   private static boolean k;
   private static final String ab = "faced %.1f, engaged %s, wanted %s, path %.3f, side %.0f, orbit %.1f/%.1f, zoom %.2f, room %.2f, at %.2f %.2f %.2f yaw %.1f pitch %.1f, frame %.2f/%.2f, game fov %.1f";
   private static final long bb = -3845071185435951092L;

   private HaloSupport9() {
   }

   public static boolean active() {
      return wE;
   }

   public static float progress() {
      return q;
   }

   public static boolean settled() {
      return wE && V && q >= 0.999F;
   }

   public static class_243 position() {
      return new class_243(j[0], j[1], j[2]);
   }

   static void open() {
      long var0 = 85038248982398L;
      long var2 = var0 ^ 100186264590781L;
      int var4 = (int)((var0 ^ 100186264590781L) >>> 32);
      int var5 = (int)((var0 ^ 100186264590781L) << 32 >>> 48);
      int var6 = (int)(var2 << 48 >>> 48);
      class_310 var7 = class_310.method_1551();
      class_746 var8 = var7.field_1724;
      if (var8 != null && var7.field_1687 != null) {
         if (!wE) {
            L = var7.field_1690.method_31044();
            class_4184 var9 = var7.field_1773.method_19418();
            float var10 = var7.method_61966().method_60637(false);
            P = var9.method_71156().method_1020(var8.method_30950(var10));
            J = var9.method_19330();
            h = var9.method_19329();
            wb = !L.method_31034() && P.method_37267() > 1.0;
            H = false;
            var7.field_1690.method_31043(class_5498.field_26665);
            wE = true;
            q = 0.0F;
            wI = var8.method_36454();
            var8.method_5636(wI);
            var8.field_6220 = wI;
            var8.method_5847(wI);
            var8.field_6259 = wI;
            u(var4, var7.field_1687, var8, (short)var5, (char)var6);
            y = 0.0F;
            w = 0.0F;
            I = 0.0F;
            a = 0.0F;
            x = 1.0F;
            s = 1.0F;
            c = -1.0F;
            p = 1.0F;
            m = false;
            N = 0L;
         }

         V = true;
         z(1.0F, 1.55F);
      }
   }

   static void close(boolean var0) {
      if (wE) {
         if (V) {
            w = class_3532.method_15393(w);
            y = class_3532.method_15393(y);
            c = -1.0F;
         }

         V = false;
         z(0.0F, var0 ? 0.55F : 1.1F);
      }
   }

   static void hurry() {
      if (wE && !V && !(o >= 0.0F)) {
         float var0 = v - G;
         float var1 = 0.3F * Math.max(0.35F, q);
         if (var0 > var1) {
            wQ = q;
            E = 0.0F;
            G = 0.0F;
            v = var1;
         }
      }
   }

   static void reset() {
      if (wE) {
         class_310 var0 = class_310.method_1551();
         if (L != null && var0.field_1690.method_31044() == class_5498.field_26665) {
            var0.field_1690.method_31043(L);
         }

         L = null;
         wE = false;
         V = false;
         q = 0.0F;
         o = -1.0F;
      }
   }

   private static void z(float var0, float var1) {
      wQ = q;
      E = var0;
      G = 0.0F;
      float var2 = Math.abs(var0 - q);
      v = var1 * Math.max(0.35F, var2) / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   private static void X(long var0) {
      long var2 = 54132800181506L;
      HaloDesign.ensureSourceClassInitialized();
      long var4 = System.nanoTime();
      float var6 = N == 0L ? 0.0F : Math.min(0.1F, (float)(var4 - N) / 1.0E9F);
      long var7 = 136514330073396L;
      if (136514330073396L == 0L) {
         boolean var10000 = false;
      } else {
         int var34 = var7 < 0L ? -1 : 1;
      }

      N = var4;
      S = var6;
      long var10 = 136514330073397L;
      if (136514330073397L == 0L) {
         boolean var35 = false;
      } else {
         int var36 = var10 < 0L ? -1 : 1;
      }

      if (o >= 0.0F) {
         long var12 = 136514330073396L;
         if (136514330073396L == 0L) {
            boolean var37 = false;
         } else {
            int var38 = var12 < 0L ? -1 : 1;
         }

         q = o;
      } else {
         long var29 = 136514330073397L;
         if (136514330073397L == 0L) {
            boolean var39 = false;
         } else {
            int var40 = var29 < 0L ? -1 : 1;
         }

         G = Math.min(v, G + var6);
         long var14 = 136514330073396L;
         if (136514330073396L == 0L) {
            boolean var41 = false;
         } else {
            int var42 = var14 < 0L ? -1 : 1;
         }

         float var16 = v <= 0.0F ? 1.0F : G / v;
         q = wQ + (E - wQ) * (E > wQ ? g : e).apply(var16);
      }

      float var30 = 1.0F - (float)Math.exp(-var6 * 9.0);
      long var13 = 136514330073396L;
      if (136514330073396L == 0L) {
         boolean var43 = false;
      } else {
         int var44 = var13 < 0L ? -1 : 1;
      }

      w = w + (y - w) * var30;
      a = a + (I - a) * var30;
      float var15 = s;
      float var32 = (x - s) * (1.0F - (float)Math.exp(-var6 * 7.0));
      long var17 = 136514330073396L;
      if (136514330073396L == 0L) {
         boolean var45 = false;
      } else {
         int var46 = var17 < 0L ? -1 : 1;
      }

      s = var15 + var32;
      float var20 = m ? 1.0F - (float)Math.exp(-var6 * 6.0) : 1.0F;
      W = W + (wF - W) * var20;
      var15 = b + (R - b) * var20;
      long var21 = 136514330073397L;
      if (136514330073397L == 0L) {
         boolean var47 = false;
      } else {
         int var48 = var21 < 0L ? -1 : 1;
      }

      b = var15;
      m = true;
      long var23 = 136514330073397L;
      if (136514330073397L == 0L) {
         boolean var49 = false;
      } else {
         int var50 = var23 < 0L ? -1 : 1;
      }

      if (c >= 0.0F) {
         c += var6;
         float var25;
         int var26 = (var25 = c - z) == 0.0F ? 0 : (var25 < 0.0F ? -1 : 1);
         long var27 = 136514330073397L;
         if (136514330073397L == 0L) {
            boolean var51 = false;
         } else {
            int var52 = var27 < 0L ? -1 : 1;
         }

         if (var26 >= 0) {
            c = -1.0F;
         }
      }

      if (!V) {
         long var33 = 136514330073396L;
         if (136514330073396L == 0L) {
            boolean var53 = false;
         } else {
            int var54 = var33 < 0L ? -1 : 1;
         }

         if (q <= 5.0E-4F && G >= v && o < 0.0F) {
            M(var2);
         }
      }
   }

   private static void M(long var0) {
      HaloDesign.ensureSourceClassInitialized();
      class_310 var2 = class_310.method_1551();
      if (L != null && var2.field_1690.method_31044() == class_5498.field_26665) {
         var2.field_1690.method_31043(L);
      }

      if (L == class_5498.field_26664) {
         var2.field_1773.field_4012.method_3215(class_1268.field_5808);
         var2.field_1773.field_4012.method_3215(class_1268.field_5810);
      }

      L = null;
      wE = false;
   }

   static void frame(float var0, float var1) {
      wF = var0;
      R = var1;
   }

   static void drag(float var0, float var1) {
      y -= var0 * 0.32F;
      I = class_3532.method_15363(I + var1 * 0.24F, -28.0F - F, 62.0F - F);
      c = -1.0F;
   }

   static void zoom(double var0) {
      x = class_3532.method_15363(x * (float)Math.pow(0.88, var0), 0.55F, 1.9F);
   }

   static void resetView() {
      y = Math.round(y / 360.0F) * 360.0F;
      I = 0.0F;
      x = 1.0F;
      c = -1.0F;
   }

   static boolean moved() {
      return Math.abs(class_3532.method_15393(y)) > 0.5F || Math.abs(I) > 0.5F || Math.abs(x - 1.0F) > 0.01F;
   }

   static void overlook(float var0, float var1) {
      I = class_3532.method_15363(var0, -28.0F - F, 62.0F - F);
      x = class_3532.method_15363(var1, 0.55F, 1.9F);
      c = -1.0F;
   }

   static void turntable(float var0) {
      y = w = class_3532.method_15393(w);
      c = 0.0F;
      z = var0 / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   static boolean turning() {
      return c >= 0.0F;
   }

   static void hold(float var0) {
      o = var0 < 0.0F ? -1.0F : Math.min(1.0F, var0);
      if (o < 0.0F) {
         wQ = q;
         G = 0.0F;
         v = 0.4F;
      }
   }

   public static float fov(float var0) {
      long var1 = 21428610032983L;
      long var3 = var1 ^ 38821246430289L;
      if (!wE) {
         return var0;
      } else {
         X = var0;
         return class_3532.method_16439(C(q, var3), var0, Z());
      }
   }

   private static float Z() {
      double var0 = Math.tan(Math.toRadians(23.0)) / Math.max(0.42F, p);
      return (float)Math.toDegrees(2.0 * Math.atan(var0));
   }

   public static boolean hides(class_1297 var0) {
      class_310 var1 = class_310.method_1551();
      if (wE && var0 == var1.field_1724 && k) {
         class_243 var2 = var0.method_5836(var1.method_61966().method_60637(false));
         double var3 = j[0] - var2.field_1352;
         double var5 = j[1] - var2.field_1351;
         double var7 = j[2] - var2.field_1350;
         double var9 = 0.9 * Math.max(0.05, var0.method_17682() / 1.8);
         return var3 * var3 + var5 * var5 + var7 * var7 < var9 * var9;
      } else {
         return false;
      }
   }

   public static boolean place(class_4184 var0, class_1937 var1, class_1297 var2, float var3, double[] var4, float[] var5) {
      long var6 = 127296945714717L;
      long var8 = var6 ^ 109149664716648L;
      long var10 = (var6 ^ 126767633829296L) >>> 16;
      int var12 = (int)((var6 ^ 126767633829296L) << 48 >>> 48);
      long var13 = var6 ^ 17515297926952L;
      long var15 = var6 ^ 92983858381450L;
      class_310 var17 = class_310.method_1551();
      if (!wE) {
         return false;
      } else if (var0 != var17.field_1773.method_19418()) {
         return false;
      } else if (var17.field_1724 != null && var2 == var17.field_1724 && var1 == var17.field_1687) {
         X(var13);
         if (!wE) {
            return false;
         } else {
            class_746 var18 = var17.field_1724;
            float var19 = q;
            class_243 var20 = var18.method_30950(var3);
            float var21 = var18.method_17682() * ClientSupport3.bodyHeight(var18);
            class_243 var22 = var20.method_1031(0.0, var21 * 0.53, 0.0);
            float var23 = Easing.SMOOTHSTEP.apply(d((var19 - 0.72F) / 0.28F, var15));
            float var24 = c >= 0.0F ? 360.0F * Easing.SINE_IN_OUT.apply(c / z) : 0.0F;
            float var25 = wI + O + (w + var24) * var23;
            float var26 = F + a * var23;
            float var27 = (float)(var21 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0)))) * s;
            class_243 var28 = class_243.method_1030(var26, var25 + 180.0F);
            class_243 var29 = var28.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
            class_243 var30 = var29.method_1036(var28);
            double var31 = Math.tan(Math.toRadians(23.0));
            double var33 = var31 * v(var17);
            class_243 var35 = var22.method_1020(var28.method_1021(var27))
               .method_1020(var29.method_1021(W * var27 * var33))
               .method_1020(var30.method_1021(b * var27 * var31));
            float var36 = I(var1, var18, var22, var8, var35);
            p = var36 < p ? var36 : p + (var36 - p) * (1.0F - (float)Math.exp(-S * 4.0));
            float var37 = Math.max(0.42F, p);
            class_243 var38 = var22.method_1020(var28.method_1021(var27 * var37))
               .method_1020(var29.method_1021(W * var27 * var33))
               .method_1020(var30.method_1021(b * var27 * var31));
            if (var37 > p + 0.001F || p < 0.999F) {
               float var39 = I(var1, var18, var22, var8, var38);
               if (var39 < 1.0F) {
                  var38 = var22.method_1019(var38.method_1020(var22).method_1021(var39));
               }
            }

            float var56 = (w + var24) * var23;
            if (wb) {
               class_243 var57 = var20.method_1019(P).method_1020(var22);
               class_243 var58 = var38.method_1020(var22);
               double var59 = Math.atan2(var57.field_1350, var57.field_1352);
               double var60 = Math.atan2(var58.field_1350, var58.field_1352);
               double var61 = class_3532.method_15338(Math.toDegrees(var60 - var59)) * (float) (Math.PI / 180.0);
               double var62 = var59 + var61 * var19;
               double var63 = class_3532.method_16436(var19, var57.method_37267(), var58.method_37267());
               class_243 var64 = var22.method_1031(
                  Math.cos(var62) * var63, class_3532.method_16436(var19, var57.field_1351, var58.field_1351), Math.sin(var62) * var63
               );
               float var65 = I(var1, var18, var22, var8, var64);
               if (var65 < 1.0F) {
                  var64 = var22.method_1019(var64.method_1020(var22).method_1021(var65));
               }

               return Z(var64, J + (class_3532.method_15393(wI + O + 180.0F - J) + var56) * var19, class_3532.method_16439(var19, h, var26), var4, var5);
            } else {
               class_243 var40 = var18.method_5836(var3);
               float var41 = var18.method_5705(var3);
               float var42 = var18.method_5695(var3);
               class_243 var43 = class_243.method_1030(0.0F, var41);
               double var44 = var38.method_1022(var40);
               class_243 var46 = var40.method_1019(var43.method_1021(var44 * 0.42)).method_1031(0.0, var44 * 0.06, 0.0);
               class_243 var47 = var38.method_1020(var22);
               class_243 var48 = var38.method_1020(var47.method_1027() < 1.0E-6 ? class_243.field_1353 : var47.method_1029().method_1021(var44 * 0.15));
               if (!H && V) {
                  H = true;
                  t = O(var1, var18, var40, var10, var46, (short)var12, var48, var38);
               }

               if (t) {
                  var46 = var22.method_1019(var47.method_1021(0.35));
                  var48 = var22.method_1019(var47.method_1021(0.7));
               }

               float var49 = 1.0F - (float)Math.pow(1.0F - var19, 2.2);
               class_243 var50 = w(var40, var46, var48, var38, var49);
               float var51 = I(var1, var18, var22, var8, var50);
               if (var51 < 1.0F) {
                  var50 = var22.method_1019(var50.method_1020(var22).method_1021(var51));
               }

               float var52 = Easing.SINE_IN_OUT.apply(d((var19 - 0.02F) / 0.86F, var15));
               float var53 = class_3532.method_15393(wI + O + 180.0F - var41);
               if (var53 > 0.0F) {
                  var53 -= 360.0F;
               }

               float var54 = var41 + (var53 + var56) * var52;
               float var55 = class_3532.method_16439(var52, var42, var26);
               return Z(var50, var54, var55, var4, var5);
            }
         }
      } else {
         reset();
         return false;
      }
   }

   private static boolean Z(class_243 var0, float var1, float var2, double[] var3, float[] var4) {
      j[0] = var0.field_1352;
      j[1] = var0.field_1351;
      j[2] = var0.field_1350;
      r = var1;
      U = var2;
      k = true;
      var3[0] = var0.field_1352;
      var3[1] = var0.field_1351;
      var3[2] = var0.field_1350;
      var4[0] = var1;
      var4[1] = var2;
      return true;
   }

   private static boolean O(class_1937 var0, class_1297 var1, class_243 var2, long var3, class_243 var5, short var6, class_243 var7, class_243 var8) {
      HaloDesign.ensureSourceClassInitialized();
      class_243 var9 = var2;

      for (int var10 = 1; var10 <= (int)bb; var10++) {
         class_243 var11 = w(var2, var5, var7, var8, 1.0F - (float)Math.pow(1.0F - var10 / 12.0F, 2.2));
         long var12 = 9290482L;
         if (9290482L == 0L) {
            boolean var10000 = false;
         } else {
            int var15 = var12 < 0L ? -1 : 1;
         }

         if (var0.method_17742(new class_3959(var9, var11, class_3960.field_23142, class_242.field_1348, var1)).method_17783() != class_240.field_1333) {
            return true;
         }

         var9 = var11;
      }

      return false;
   }

   private static float C(float var0, long var1) {
      long var3 = 43258321548439L;
      return Easing.SMOOTHSTEP.apply(d(var0 * 1.15F, var3));
   }

   private static void u(int var0, class_1937 var1, class_746 var2, short var3, char var4) {
      long var5 = 24506750545603L;
      long var7 = var5 ^ 7465457287094L;
      class_310 var9 = class_310.method_1551();
      float var10 = var2.method_17682() * ClientSupport3.bodyHeight(var2);
      class_243 var11 = var2.method_73189().method_1031(0.0, var10 * 0.53, 0.0);
      HaloDesign.ensureSourceClassInitialized();
      float var12 = (float)(var10 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0))));
      double var13 = Math.tan(Math.toRadians(23.0));
      double var15 = var13 * v(var9);
      float[][] var17 = new float[D.length][K.length];

      for (int var18 = 0; var18 < D.length; var18++) {
         for (int var19 = 0; var19 < K.length; var19++) {
            class_243 var20 = L(var11, var2.method_36454() + K[var19], D[var18], var12 * 1.1F, var15, var13);
            var17[var18][var19] = I(var1, var2, var11, var7, var20);
         }
      }

      for (float var21 : new float[]{0.92F, 0.5F}) {
         for (int var22 = 0; var22 < D.length; var22++) {
            short var23 = 11971;

            for (int var24 = 0; var24 < K.length; var24++) {
               float var25 = K[var24];
               float var26;
               int var27 = (var26 = Math.abs(var25) - 90.0F) == 0.0F ? 0 : (var26 < 0.0F ? -1 : 1);
               var23 = 5705;
               if (var27 <= 0) {
                  float var28;
                  int var29 = (var28 = var17[var22][var24] - var21) == 0.0F ? 0 : (var28 < 0.0F ? -1 : 1);
                  var23 = -5079;
                  if (var29 >= 0) {
                     O = K[var24];
                     F = D[var22];
                     return;
                  }
               }
            }
         }
      }

      int var31 = 0;
      int var33 = 0;

      for (int var35 = 0; var35 < D.length; var35++) {
         for (int var36 = 0; var36 < K.length; var36++) {
            float[] var38 = var17[var35];
            if (var38[var36] > var17[var31][var33] + 0.05F) {
               var31 = var35;
               var33 = var36;
            }
         }
      }

      float var37 = K[var33];
      O = var37;
      F = D[var31];
   }

   private static class_243 L(class_243 var0, float var1, float var2, float var3, double var4, double var6) {
      class_243 var8 = class_243.method_1030(var2, var1 + 180.0F);
      class_243 var9 = var8.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
      class_243 var10 = var9.method_1036(var8);
      return var0.method_1020(var8.method_1021(var3)).method_1020(var9.method_1021(W * var3 * var4)).method_1020(var10.method_1021(b * var3 * var6));
   }

   private static float I(class_1937 var0, class_1297 var1, class_243 var2, long var3, class_243 var5) {
      HaloDesign.ensureSourceClassInitialized();
      class_243 var6 = var5.method_1020(var2);
      double var7 = var6.method_1033();
      if (var7 < 1.0E-4) {
         return 1.0F;
      } else {
         double var9 = var7;

         for (int var11 = 0; var11 < 5; var11++) {
            long var18 = 18158111215989L;
            if (18158111215989L == 0L) {
               boolean var10000 = false;
            } else {
               int var30 = var18 < 0L ? -1 : 1;
            }

            double var12 = var11 == 1 ? 0.1 : (var11 == 2 ? -0.1 : 0.0);
            long var21 = 18158111215989L;
            if (18158111215989L == 0L) {
               boolean var31 = false;
            } else {
               int var32 = var21 < 0L ? -1 : 1;
            }

            double var14 = var11 == 3 ? 0.1 : (var11 == 4 ? -0.1 : 0.0);
            long var23 = 18158111215988L;
            if (18158111215988L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var23 < 0L ? -1 : 1;
            }

            double var16;
            label94: {
               if (var11 != 1) {
                  long var25 = 18158111215988L;
                  if (18158111215988L == 0L) {
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

            class_243 var20 = var2.method_1031(var12, var14, var16);
            class_243 var28 = var5.method_1031(var12, var14, var16);
            class_3965 var22 = var0.method_17742(new class_3959(var20, var28, class_3960.field_23142, class_242.field_1348, var1));
            var23 = 18158111215989L;
            if (18158111215989L == 0L) {
               boolean var37 = false;
            } else {
               int var38 = var23 < 0L ? -1 : 1;
            }

            if (var22.method_17783() != class_240.field_1333) {
               var9 = Math.min(var9, var22.method_17784().method_1022(var20) - 0.2);
            }
         }

         double var27 = var9 / var7;
         long var13 = 18158111215989L;
         if (18158111215989L == 0L) {
            boolean var39 = false;
         } else {
            int var40 = var13 < 0L ? -1 : 1;
         }

         return (float)Math.clamp(var27, Math.min(1.0, 0.7 / var7), 1.0);
      }
   }

   private static double v(class_310 var0) {
      return Math.max(0.2, (double)var0.method_22683().method_4489() / Math.max(1, var0.method_22683().method_4506()));
   }

   private static class_243 w(class_243 var0, class_243 var1, class_243 var2, class_243 var3, float var4) {
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

   private static Easing U(float var0, float var1, float var2, float var3) {
      return Easing.bezier(var0, var1, var2, var3);
   }

   private static float d(float var0, long var1) {
      HaloDesign.ensureSourceClassInitialized();
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   static String describe() {
      return String.format(Locale.ROOT, ab, wI, wE, V, q, O, w, a, s, p, j[0], j[1], j[2], r, U, W, b, X);
   }

}
