package dev.purxiy.modules.afterglow;

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

public final class AfterglowSupport4 {
   private static final float b = 1.55F;
   private static final float d = 1.1F;
   static final float F = 46.0F;
   private static final float W = 0.47F;
   private static final float a = 27.0F;
   private static final float J = 6.0F;
   private static final float[] sx = new float[]{27.0F, -27.0F, 0.0F, 70.0F, -70.0F, 115.0F, -115.0F, 180.0F};
   private static final float[] k = new float[]{6.0F, 24.0F, 42.0F};
   private static final double c = 0.9;
   private static final double M = 0.7;
   private static final double m = 0.2;
   private static final float j = 0.42F;
   private static final float R = 0.5F;
   private static final float I = 0.55F;
   private static final float Q = 1.9F;
   private static final float u = -28.0F;
   private static final float h = 62.0F;
   private static final Easing g = t(0.42F, 0.0F, 0.12F, 1.0F);
   private static final Easing s = t(0.45F, 0.0F, 0.25F, 1.0F);
   private static boolean t;
   private static boolean w;
   private static class_5498 H;
   private static float q;
   private static float sR;
   private static float E;
   private static float D;
   private static float Z;
   private static long sK = 0L;
   private static float z;
   private static float U;
   private static boolean o;
   private static boolean B;
   private static boolean p;
   private static class_243 S;
   private static float K;
   private static float N;
   private static float P;
   private static float sQ;
   private static float x;
   private static float sN;
   private static float i;
   private static float X;
   private static float se;
   private static boolean V;
   private static float Y;
   private static float n;
   private static float G;
   private static float f;
   private static float e;
   private static float r;
   private static float A;
   private static float l;
   private static float L;
   private static float O;
   private static final double[] v = new double[3];
   private static float y;
   private static float T;
   private static boolean C;
   private static final String ab = "faced %.1f, engaged %s, wanted %s, path %.3f, side %.0f, orbit %.1f/%.1f, zoom %.2f, room %.2f, at %.2f %.2f %.2f yaw %.1f pitch %.1f, frame %.2f/%.2f, game fov %.1f";
   private static final long bb = -8262301700543479796L;

   private AfterglowSupport4() {
   }

   public static boolean active() {
      return w;
   }

   public static float progress() {
      return q;
   }

   public static boolean settled() {
      return w && t && q >= 0.999F;
   }

   public static class_243 position() {
      return new class_243(v[0], v[1], v[2]);
   }

   static void open() {
      long var0 = 72744095277433L;
      int var2 = (int)((var0 ^ 69572858161537L) >>> 32);
      int var3 = (int)((var0 ^ 69572858161537L) << 32 >>> 32);
      class_310 var4 = class_310.method_1551();
      class_746 var5 = var4.field_1724;
      if (var5 != null && var4.field_1687 != null) {
         if (!w) {
            H = var4.field_1690.method_31044();
            class_4184 var6 = var4.field_1773.method_19418();
            float var7 = var4.method_61966().method_60637(false);
            S = var6.method_71156().method_1020(var5.method_30950(var7));
            K = var6.method_19330();
            N = var6.method_19329();
            o = !H.method_31034() && S.method_37267() > 1.0;
            p = false;
            var4.field_1690.method_31043(class_5498.field_26665);
            w = true;
            q = 0.0F;
            P = var5.method_36454();
            var5.method_5636(P);
            var5.field_6220 = P;
            var5.method_5847(P);
            var5.field_6259 = P;
            p(var4.field_1687, var2, var3, var5);
            f = 0.0F;
            n = 0.0F;
            e = 0.0F;
            G = 0.0F;
            A = 1.0F;
            r = 1.0F;
            l = -1.0F;
            O = 1.0F;
            V = false;
            sK = 0L;
         }

         t = true;
         S(1.0F, 1.55F);
      }
   }

   static void close(boolean var0) {
      if (w) {
         if (t) {
            n = class_3532.method_15393(n);
            f = class_3532.method_15393(f);
            l = -1.0F;
         }

         t = false;
         S(0.0F, var0 ? 0.55F : 1.1F);
      }
   }

   static void hurry() {
      if (w && !t && !(U >= 0.0F)) {
         float var0 = Z - D;
         float var1 = 0.3F * Math.max(0.35F, q);
         if (var0 > var1) {
            sR = q;
            E = 0.0F;
            D = 0.0F;
            Z = var1;
         }
      }
   }

   static void reset() {
      if (w) {
         class_310 var0 = class_310.method_1551();
         if (H != null && var0.field_1690.method_31044() == class_5498.field_26665) {
            var0.field_1690.method_31043(H);
         }

         H = null;
         w = false;
         t = false;
         q = 0.0F;
         U = -1.0F;
      }
   }

   private static void S(float var0, float var1) {
      sR = q;
      E = var0;
      D = 0.0F;
      float var2 = Math.abs(var0 - q);
      Z = var1 * Math.max(0.35F, var2) / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   private static void S(long var0) {
      long var2 = 7995941223781L;
      AfterglowRenderer.ensureSourceClassInitialized();
      long var4 = System.nanoTime();
      float var6 = sK == 0L ? 0.0F : Math.min(0.1F, (float)(var4 - sK) / 1.0E9F);
      long var7 = 131169554101615L;
      int var10 = var7 < 0L ? -1 : 1;
      sK = var4;
      long var10x = 131169554101615L;
      int var31 = var10x < 0L ? -1 : 1;
      z = var6;
      if (U >= 0.0F) {
         long var12 = 131169554101615L;
         int var14 = var12 < 0L ? -1 : 1;
         q = U;
      } else {
         long var30 = 131169554101615L;
         int var321 = var30 < 0L ? -1 : 1;
         D = Math.min(Z, D + var6);
         long var15 = 131169554101615L;
         int var15x = var15 < 0L ? -1 : 1;
         float var17 = Z <= 0.0F ? 1.0F : D / Z;
         q = sR + (E - sR) * (E > sR ? g : s).apply(var17);
      }

      float var31x = 1.0F - (float)Math.exp(-var6 * 9.0);
      float var13 = n + (f - n) * var31x;
      long var14 = 131169554101614L;
      int var36 = var14 < 0L ? -1 : 1;
      n = var13;
      G = G + (e - G) * var31x;
      float var16 = A - r;
      long var32 = 131169554101615L;
      int var20 = var32 < 0L ? -1 : 1;
      r = r + var16 * (1.0F - (float)Math.exp(-var6 * 7.0));
      float var20x = V ? 1.0F - (float)Math.exp(-var6 * 6.0) : 1.0F;
      sN = sN + (X - sN) * var20x;
      long var21 = 131169554101615L;
      int var21x = var21 < 0L ? -1 : 1;
      i = i + (se - i) * var20x;
      V = true;
      long var24x = 131169554101614L;
      int var33 = var24x < 0L ? -1 : 1;
      if (l >= 0.0F) {
         l += var6;
         float var26;
         int var27 = (var26 = l - L) == 0.0F ? 0 : (var26 < 0.0F ? -1 : 1);
         long var28 = 131169554101615L;
         int var27x = var28 < 0L ? -1 : 1;
         if (var27 >= 0) {
            l = -1.0F;
         }
      }

      if (!t) {
         long var33x = 131169554101614L;
         int var42 = var33x < 0L ? -1 : 1;
         if (q <= 5.0E-4F && D >= Z && U < 0.0F) {
            s(var2);
         }
      }
   }

   private static void s(long var0) {
      AfterglowRenderer.ensureSourceClassInitialized();
      class_310 var2 = class_310.method_1551();
      if (H != null && var2.field_1690.method_31044() == class_5498.field_26665) {
         var2.field_1690.method_31043(H);
      }

      if (H == class_5498.field_26664) {
         var2.field_1773.field_4012.method_3215(class_1268.field_5808);
         var2.field_1773.field_4012.method_3215(class_1268.field_5810);
      }

      H = null;
      w = false;
   }

   static void frame(float var0, float var1) {
      X = var0;
      se = var1;
   }

   static void drag(float var0, float var1) {
      f -= var0 * 0.32F;
      e = class_3532.method_15363(e + var1 * 0.24F, -28.0F - x, 62.0F - x);
      l = -1.0F;
   }

   static void zoom(double var0) {
      A = class_3532.method_15363(A * (float)Math.pow(0.88, var0), 0.55F, 1.9F);
   }

   static void resetView() {
      f = Math.round(f / 360.0F) * 360.0F;
      e = 0.0F;
      A = 1.0F;
      l = -1.0F;
   }

   static boolean moved() {
      return Math.abs(class_3532.method_15393(f)) > 0.5F || Math.abs(e) > 0.5F || Math.abs(A - 1.0F) > 0.01F;
   }

   static void overlook(float var0, float var1) {
      e = class_3532.method_15363(var0, -28.0F - x, 62.0F - x);
      A = class_3532.method_15363(var1, 0.55F, 1.9F);
      l = -1.0F;
   }

   static void turntable(float var0) {
      f = n = class_3532.method_15393(n);
      l = 0.0F;
      L = var0 / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   static boolean turning() {
      return l >= 0.0F;
   }

   static void hold(float var0) {
      U = var0 < 0.0F ? -1.0F : Math.min(1.0F, var0);
      if (U < 0.0F) {
         sR = q;
         D = 0.0F;
         Z = 0.4F;
      }
   }

   public static float fov(float var0) {
      long var1 = 46726915157566L;
      long var3 = var1 ^ 84243839372121L;
      if (!w) {
         return var0;
      } else {
         Y = var0;
         return class_3532.method_16439(Q(q, var3), var0, Y());
      }
   }

   private static float Y() {
      double var0 = Math.tan(Math.toRadians(23.0)) / Math.max(0.42F, O);
      return (float)Math.toDegrees(2.0 * Math.atan(var0));
   }

   public static boolean hides(class_1297 var0) {
      class_310 var1 = class_310.method_1551();
      if (w && var0 == var1.field_1724 && C) {
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
      long var6 = 117111196835921L;
      long var8 = var6 ^ 43350433200474L;
      int var10 = (int)((var6 ^ 43350433200474L) >>> 32);
      int var11 = (int)((var6 ^ 43350433200474L) << 32 >>> 48);
      int var12 = (int)(var8 << 48 >>> 48);
      long var13 = var6 ^ 24744429288947L;
      long var15 = var6 ^ 22075044812541L;
      long var17 = var6 ^ 32776403555646L;
      class_310 var19 = class_310.method_1551();
      if (!w) {
         return false;
      } else if (var0 != var19.field_1773.method_19418()) {
         return false;
      } else if (var19.field_1724 != null && var2 == var19.field_1724 && var1 == var19.field_1687) {
         S(var17);
         if (!w) {
            return false;
         } else {
            class_746 var20 = var19.field_1724;
            float var21 = q;
            class_243 var22 = var20.method_30950(var3);
            float var23 = var20.method_17682() * ClientSupport3.bodyHeight(var20);
            class_243 var24 = var22.method_1031(0.0, var23 * 0.53, 0.0);
            float var25 = Easing.SMOOTHSTEP.apply(Y(var13, (var21 - 0.72F) / 0.28F));
            float var26 = l >= 0.0F ? 360.0F * Easing.SINE_IN_OUT.apply(l / L) : 0.0F;
            float var27 = P + sQ + (n + var26) * var25;
            float var28 = x + G * var25;
            float var29 = (float)(var23 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0)))) * r;
            class_243 var30 = class_243.method_1030(var28, var27 + 180.0F);
            class_243 var31 = var30.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
            class_243 var32 = var31.method_1036(var30);
            double var33 = Math.tan(Math.toRadians(23.0));
            double var35 = var33 * p(var19);
            class_243 var37 = var24.method_1020(var30.method_1021(var29))
               .method_1020(var31.method_1021(sN * var29 * var35))
               .method_1020(var32.method_1021(i * var29 * var33));
            float var38 = S(var1, var15, var20, var24, var37);
            O = var38 < O ? var38 : O + (var38 - O) * (1.0F - (float)Math.exp(-z * 4.0));
            float var39 = Math.max(0.42F, O);
            class_243 var40 = var24.method_1020(var30.method_1021(var29 * var39))
               .method_1020(var31.method_1021(sN * var29 * var35))
               .method_1020(var32.method_1021(i * var29 * var33));
            if (var39 > O + 0.001F || O < 0.999F) {
               float var41 = S(var1, var15, var20, var24, var40);
               if (var41 < 1.0F) {
                  var40 = var24.method_1019(var40.method_1020(var24).method_1021(var41));
               }
            }

            float var58 = (n + var26) * var25;
            if (o) {
               class_243 var59 = var22.method_1019(S).method_1020(var24);
               class_243 var60 = var40.method_1020(var24);
               double var61 = Math.atan2(var59.field_1350, var59.field_1352);
               double var62 = Math.atan2(var60.field_1350, var60.field_1352);
               double var63 = class_3532.method_15338(Math.toDegrees(var62 - var61)) * (float) (Math.PI / 180.0);
               double var64 = var61 + var63 * var21;
               double var65 = class_3532.method_16436(var21, var59.method_37267(), var60.method_37267());
               class_243 var66 = var24.method_1031(
                  Math.cos(var64) * var65, class_3532.method_16436(var21, var59.field_1351, var60.field_1351), Math.sin(var64) * var65
               );
               float var67 = S(var1, var15, var20, var24, var66);
               if (var67 < 1.0F) {
                  var66 = var24.method_1019(var66.method_1020(var24).method_1021(var67));
               }

               return x(var66, K + (class_3532.method_15393(P + sQ + 180.0F - K) + var58) * var21, class_3532.method_16439(var21, N, var28), var4, var5);
            } else {
               class_243 var42 = var20.method_5836(var3);
               float var43 = var20.method_5705(var3);
               float var44 = var20.method_5695(var3);
               class_243 var45 = class_243.method_1030(0.0F, var43);
               double var46 = var40.method_1022(var42);
               class_243 var48 = var42.method_1019(var45.method_1021(var46 * 0.42)).method_1031(0.0, var46 * 0.06, 0.0);
               class_243 var49 = var40.method_1020(var24);
               class_243 var50 = var40.method_1020(var49.method_1027() < 1.0E-6 ? class_243.field_1353 : var49.method_1029().method_1021(var46 * 0.15));
               if (!p && t) {
                  p = true;
                  B = p(var1, var10, var20, var42, var48, var50, (char)var11, var40, (short)var12);
               }

               if (B) {
                  var48 = var24.method_1019(var49.method_1021(0.35));
                  var50 = var24.method_1019(var49.method_1021(0.7));
               }

               float var51 = 1.0F - (float)Math.pow(1.0F - var21, 2.2);
               class_243 var52 = B(var42, var48, var50, var40, var51);
               float var53 = S(var1, var15, var20, var24, var52);
               if (var53 < 1.0F) {
                  var52 = var24.method_1019(var52.method_1020(var24).method_1021(var53));
               }

               float var54 = Easing.SINE_IN_OUT.apply(Y(var13, (var21 - 0.02F) / 0.86F));
               float var55 = class_3532.method_15393(P + sQ + 180.0F - var43);
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
      v[0] = var0.field_1352;
      v[1] = var0.field_1351;
      v[2] = var0.field_1350;
      y = var1;
      T = var2;
      C = true;
      var3[0] = var0.field_1352;
      var3[1] = var0.field_1351;
      var3[2] = var0.field_1350;
      var4[0] = var1;
      var4[1] = var2;
      return true;
   }

   private static boolean p(class_1937 var0, int var1, class_1297 var2, class_243 var3, class_243 var4, class_243 var5, char var6, class_243 var7, short var8) {
      AfterglowRenderer.ensureSourceClassInitialized();
      class_243 var9 = var3;

      for (int var10 = 1; var10 <= 12; var10++) {
         class_243 var11 = B(var3, var4, var5, var7, 1.0F - (float)Math.pow(1.0F - var10 / 12.0F, 2.2));
         if (var0.method_17742(new class_3959(var9, var11, class_3960.field_23142, class_242.field_1348, var2)).method_17783() != class_240.field_1333) {
            return true;
         }

         var9 = var11;
      }

      return false;
   }

   private static float Q(float var0, long var1) {
      long var3 = 136349401456034L;
      return Easing.SMOOTHSTEP.apply(Y(var3, var0 * 1.15F));
   }

   private static void p(class_1937 var0, int var1, int var2, class_746 var3) {
      long var4 = 137918858021112L;
      long var6 = var4 ^ 4394036198996L;
      AfterglowRenderer.ensureSourceClassInitialized();
      class_310 var8 = class_310.method_1551();
      float var9 = var3.method_17682() * ClientSupport3.bodyHeight(var3);
      class_243 var10 = var3.method_73189().method_1031(0.0, var9 * 0.53, 0.0);
      float var11 = (float)(var9 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0))));
      double var12 = Math.tan(Math.toRadians(23.0));
      double var14 = var12 * p(var8);
      float[][] var16 = new float[k.length][sx.length];

      for (int var17 = 0; var17 < k.length; var17++) {
         for (int var18 = 0; var18 < sx.length; var18++) {
            class_243 var19 = P(var10, var3.method_36454() + sx[var18], k[var17], var11 * 1.1F, var14, var12);
            var16[var17][var18] = S(var0, var6, var3, var10, var19);
         }
      }

      for (float var20 : new float[]{0.92F, 0.5F}) {
         for (int var21 = 0; var21 < k.length; var21++) {
            for (int var22 = 0; var22 < sx.length; var22++) {
               float var23 = sx[var22];
               int var24 = 32111;
               float var25;
               int var26 = (var25 = Math.abs(var23) - 90.0F) == 0.0F ? 0 : (var25 < 0.0F ? -1 : 1);
               int var36 = 32111;
               if (var26 <= 0) {
                  float var27;
                  int var28 = (var27 = var16[var21][var22] - var20) == 0.0F ? 0 : (var27 < 0.0F ? -1 : 1);
                  var36 = -1131788040;
                  if (var28 >= 0) {
                     sQ = sx[var22];
                     x = k[var21];
                     return;
                  }
               }
            }
         }
      }

      int var30 = 0;
      int var32 = 0;

      for (int var34 = 0; var34 < k.length; var34++) {
         for (int var35 = 0; var35 < sx.length; var35++) {
            if (var16[var34][var35] > var16[var30][var32] + 0.05F) {
               var30 = var34;
               var32 = var35;
            }
         }
      }

      sQ = sx[var32];
      x = k[var30];
   }

   private static class_243 P(class_243 var0, float var1, float var2, float var3, double var4, double var6) {
      class_243 var8 = class_243.method_1030(var2, var1 + 180.0F);
      class_243 var9 = var8.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
      class_243 var10 = var9.method_1036(var8);
      return var0.method_1020(var8.method_1021(var3)).method_1020(var9.method_1021(sN * var3 * var4)).method_1020(var10.method_1021(i * var3 * var6));
   }

   private static float S(class_1937 var0, long var1, class_1297 var3, class_243 var4, class_243 var5) {
      AfterglowRenderer.ensureSourceClassInitialized();
      class_243 var6 = var5.method_1020(var4);
      double var7 = var6.method_1033();
      if (var7 < 1.0E-4) {
         return 1.0F;
      } else {
         double var9 = var7;

         for (int var11 = 0; var11 < 5; var11++) {
            double var12;
            double var14;
            double var16;
            label76: {
               long var18 = 139159834277548L;
               int var12x = var18 < 0L ? -1 : 1;
               var12 = var11 == 1 ? 0.1 : (var11 == 2 ? -0.1 : 0.0);
               long var21 = 139159834277548L;
               int var14x = var21 < 0L ? -1 : 1;
               var14 = var11 == 3 ? 0.1 : (var11 == 4 ? -0.1 : 0.0);
               long var23 = 139159834277547L;
               int var16x = var23 < 0L ? -1 : 1;
               if (var11 != 1) {
                  long var25 = 139159834277547L;
                  int var22 = var25 < 0L ? -1 : 1;
                  if (var11 != 2) {
                     var16 = var11 == 3 ? -0.1 : 0.0;
                     break label76;
                  }
               }

               var16 = 0.1;
            }

            class_243 var20 = var4.method_1031(var12, var14, var16);
            class_243 var28 = var5.method_1031(var12, var14, var16);
            class_3965 var22 = var0.method_17742(new class_3959(var20, var28, class_3960.field_23142, class_242.field_1348, var3));
            long var31 = 139159834277547L;
            int var271 = var31 < 0L ? -1 : 1;
            if (var22.method_17783() != class_240.field_1333) {
               var9 = Math.min(var9, var22.method_17784().method_1022(var20) - 0.2);
            }
         }

         double var27 = var9 / var7;
         long var13 = 139159834277547L;
         int var15 = var13 < 0L ? -1 : 1;
         return (float)Math.clamp(var27, Math.min(1.0, 0.7 / var7), 1.0);
      }
   }

   private static double p(class_310 var0) {
      return Math.max(0.2, (double)var0.method_22683().method_4489() / Math.max(1, var0.method_22683().method_4506()));
   }

   private static class_243 B(class_243 var0, class_243 var1, class_243 var2, class_243 var3, float var4) {
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

   private static Easing t(float var0, float var1, float var2, float var3) {
      return Easing.bezier(var0, var1, var2, var3);
   }

   private static float Y(long var0, float var2) {
      AfterglowRenderer.ensureSourceClassInitialized();
      return var2 < 0.0F ? 0.0F : Math.min(var2, 1.0F);
   }

   static String describe() {
      return String.format(
         Locale.ROOT,
         "faced %.1f, engaged %s, wanted %s, path %.3f, side %.0f, orbit %.1f/%.1f, zoom %.2f, room %.2f, at %.2f %.2f %.2f yaw %.1f pitch %.1f, frame %.2f/%.2f, game fov %.1f",
         P,
         w,
         t,
         q,
         sQ,
         n,
         G,
         r,
         O,
         v[0],
         v[1],
         v[2],
         y,
         T,
         sN,
         i,
         Y
      );
   }
}
