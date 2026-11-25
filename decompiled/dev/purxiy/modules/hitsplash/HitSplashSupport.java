package dev.purxiy.modules.hitsplash;

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

public final class HitSplashSupport {
   private static final float V = 1.55F;
   private static final float i = 1.1F;
   static final float U = 46.0F;
   private static final float Q = 0.47F;
   private static final float f = 27.0F;
   private static final float J = 6.0F;
   private static final float[] X = new float[]{27.0F, -27.0F, 0.0F, 70.0F, -70.0F, 115.0F, -115.0F, 180.0F};
   private static final float[] M = new float[]{6.0F, 24.0F, 42.0F};
   private static final double H = 0.9;
   private static final double d = 0.7;
   private static final double c = 0.2;
   private static final float C = 0.42F;
   private static final float B = 0.5F;
   private static final float t = 0.55F;
   private static final float e = 1.9F;
   private static final float m = -28.0F;
   private static final float K = 62.0F;
   private static final Easing j = P(0.42F, 0.0F, 0.12F, 1.0F);
   private static final Easing v = P(0.45F, 0.0F, 0.25F, 1.0F);
   private static boolean h;
   private static boolean I;
   private static class_5498 G;
   private static float ny;
   private static float n;
   private static float o;
   private static float Z;
   private static float x;
   private static long nW = 0L;
   private static float O;
   private static float g;
   private static boolean Y;
   private static boolean r;
   private static boolean w;
   private static class_243 y;
   private static float L;
   private static float R;
   private static float q;
   private static float T;
   private static float N;
   private static float k;
   private static float nY;
   private static float p;
   private static float nU;
   private static boolean z;
   private static float A;
   private static float u;
   private static float W;
   private static float D;
   private static float P;
   private static float n5;
   private static float F;
   private static float b;
   private static float nA;
   private static float S;
   private static final double[] E = new double[]{0.0, 0.0, 0.0};
   private static float s;
   private static float a;
   private static boolean l;
   private static final String ab = "faced %.1f, engaged %s, wanted %s, path %.3f, side %.0f, orbit %.1f/%.1f, zoom %.2f, room %.2f, at %.2f %.2f %.2f yaw %.1f pitch %.1f, frame %.2f/%.2f, game fov %.1f";
   private static final long bb = 7496145965196247052L;

   private HitSplashSupport() {
   }

   public static boolean active() {
      return I;
   }

   public static float progress() {
      return ny;
   }

   public static boolean settled() {
      return I && h && ny >= 0.999F;
   }

   public static class_243 position() {
      return new class_243(E[0], E[1], E[2]);
   }

   static void open() {
      long var0 = 40574063358428L;
      long var2 = var0 ^ 133949540056905L;
      class_310 var4 = class_310.method_1551();
      class_746 var5 = var4.field_1724;
      if (var5 != null && var4.field_1687 != null) {
         if (!I) {
            G = var4.field_1690.method_31044();
            class_4184 var6 = var4.field_1773.method_19418();
            float var7 = var4.method_61966().method_60637(false);
            y = var6.method_71156().method_1020(var5.method_30950(var7));
            L = var6.method_19330();
            R = var6.method_19329();
            Y = !G.method_31034() && y.method_37267() > 1.0;
            w = false;
            var4.field_1690.method_31043(class_5498.field_26665);
            I = true;
            ny = 0.0F;
            q = var5.method_36454();
            var5.method_5636(q);
            var5.field_6220 = q;
            var5.method_5847(q);
            var5.field_6259 = q;
            y(var4.field_1687, var2, var5);
            D = 0.0F;
            u = 0.0F;
            P = 0.0F;
            W = 0.0F;
            F = 1.0F;
            n5 = 1.0F;
            b = -1.0F;
            S = 1.0F;
            z = false;
            nW = 0L;
         }

         h = true;
         d(1.0F, 1.55F);
      }
   }

   static void close(boolean var0) {
      if (I) {
         if (h) {
            u = class_3532.method_15393(u);
            D = class_3532.method_15393(D);
            b = -1.0F;
         }

         h = false;
         d(0.0F, var0 ? 0.55F : 1.1F);
      }
   }

   static void hurry() {
      if (I && !h && !(g >= 0.0F)) {
         float var0 = x - Z;
         float var1 = 0.3F * Math.max(0.35F, ny);
         if (var0 > var1) {
            n = ny;
            o = 0.0F;
            Z = 0.0F;
            x = var1;
         }
      }
   }

   static void reset() {
      if (I) {
         class_310 var0 = class_310.method_1551();
         if (G != null && var0.field_1690.method_31044() == class_5498.field_26665) {
            var0.field_1690.method_31043(G);
         }

         G = null;
         I = false;
         h = false;
         ny = 0.0F;
         g = -1.0F;
      }
   }

   private static void d(float var0, float var1) {
      n = ny;
      o = var0;
      Z = 0.0F;
      float var2 = Math.abs(var0 - ny);
      x = var1 * Math.max(0.35F, var2) / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   private static void m(long var0) {
      long var2 = 48387028582266L;
      HitSplashRenderer2.ensureSourceClassInitialized();
      long var4 = System.nanoTime();
      float var6 = nW == 0L ? 0.0F : Math.min(0.1F, (float)(var4 - nW) / 1.0E9F);
      nW = var4;
      long var7 = 31973599632311L;
      int var10 = var7 < 0L ? -1 : 1;
      O = var6;
      long var10x = 31973599632311L;
      int var29 = var10x < 0L ? -1 : 1;
      if (g >= 0.0F) {
         long var12 = 31973599632310L;
         int var15 = var12 < 0L ? -1 : 1;
         ny = g;
      } else {
         long var28 = 31973599632311L;
         int var311 = var28 < 0L ? -1 : 1;
         Z = Math.min(x, Z + var6);
         long var15 = 31973599632310L;
         int var15x = var15 < 0L ? -1 : 1;
         float var17x = x <= 0.0F ? 1.0F : Z / x;
         ny = n + (o - n) * (o > n ? j : v).apply(var17x);
      }

      float var29x = 1.0F - (float)Math.exp(-var6 * 9.0);
      long var13 = 31973599632310L;
      int var30 = var13 < 0L ? -1 : 1;
      u = u + (D - u) * var29x;
      W = W + (P - W) * var29x;
      long var30x = 31973599632310L;
      int var31 = var30x < 0L ? -1 : 1;
      n5 = n5 + (F - n5) * (1.0F - (float)Math.exp(-var6 * 7.0));
      float var31x = z ? 1.0F - (float)Math.exp(-var6 * 6.0) : 1.0F;
      k = k + (p - k) * var31x;
      float var18 = nU - nY;
      long var19 = 31973599632310L;
      int var22 = var19 < 0L ? -1 : 1;
      nY += var18 * var31x;
      z = true;
      long var22x = 31973599632311L;
      int var22x = var22x < 0L ? -1 : 1;
      if (b >= 0.0F) {
         b += var6;
         float var25;
         int var24 = (var25 = b - nA) == 0.0F ? 0 : (var25 < 0.0F ? -1 : 1);
         long var26 = 31973599632310L;
         int var26x = var26 < 0L ? -1 : 1;
         if (var24 >= 0) {
            b = -1.0F;
         }
      }

      if (!h) {
         long var32x = 31973599632311L;
         int var39 = var32x < 0L ? -1 : 1;
         if (ny <= 5.0E-4F && Z >= x && g < 0.0F) {
            j(var2);
         }
      }
   }

   private static void j(long var0) {
      HitSplashRenderer2.ensureSourceClassInitialized();
      class_310 var2 = class_310.method_1551();
      if (G != null && var2.field_1690.method_31044() == class_5498.field_26665) {
         var2.field_1690.method_31043(G);
      }

      if (G == class_5498.field_26664) {
         var2.field_1773.field_4012.method_3215(class_1268.field_5808);
         var2.field_1773.field_4012.method_3215(class_1268.field_5810);
      }

      G = null;
      I = false;
   }

   static void frame(float var0, float var1) {
      p = var0;
      nU = var1;
   }

   static void drag(float var0, float var1) {
      D -= var0 * 0.32F;
      P = class_3532.method_15363(P + var1 * 0.24F, -28.0F - N, 62.0F - N);
      b = -1.0F;
   }

   static void zoom(double var0) {
      F = class_3532.method_15363(F * (float)Math.pow(0.88, var0), 0.55F, 1.9F);
   }

   static void resetView() {
      D = Math.round(D / 360.0F) * 360.0F;
      P = 0.0F;
      F = 1.0F;
      b = -1.0F;
   }

   static boolean moved() {
      return Math.abs(class_3532.method_15393(D)) > 0.5F || Math.abs(P) > 0.5F || Math.abs(F - 1.0F) > 0.01F;
   }

   static void overlook(float var0, float var1) {
      P = class_3532.method_15363(var0, -28.0F - N, 62.0F - N);
      F = class_3532.method_15363(var1, 0.55F, 1.9F);
      b = -1.0F;
   }

   static void turntable(float var0) {
      D = u = class_3532.method_15393(u);
      b = 0.0F;
      nA = var0 / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   static boolean turning() {
      return b >= 0.0F;
   }

   static void hold(float var0) {
      g = var0 < 0.0F ? -1.0F : Math.min(1.0F, var0);
      if (g < 0.0F) {
         n = ny;
         Z = 0.0F;
         x = 0.4F;
      }
   }

   public static float fov(float var0) {
      long var1 = 69827760067994L;
      long var3 = var1 ^ 67329110759303L;
      if (!I) {
         return var0;
      } else {
         A = var0;
         return class_3532.method_16439(w(ny, var3), var0, R());
      }
   }

   private static float R() {
      double var0 = Math.tan(Math.toRadians(23.0)) / Math.max(0.42F, S);
      return (float)Math.toDegrees(2.0 * Math.atan(var0));
   }

   public static boolean hides(class_1297 var0) {
      class_310 var1 = class_310.method_1551();
      if (I && var0 == var1.field_1724 && l) {
         class_243 var2 = var0.method_5836(var1.method_61966().method_60637(false));
         double var3 = E[0] - var2.field_1352;
         double var5 = E[1] - var2.field_1351;
         double var7 = E[2] - var2.field_1350;
         double var9 = 0.9 * Math.max(0.05, var0.method_17682() / 1.8);
         return var3 * var3 + var5 * var5 + var7 * var7 < var9 * var9;
      } else {
         return false;
      }
   }

   public static boolean place(class_4184 var0, class_1937 var1, class_1297 var2, float var3, double[] var4, float[] var5) {
      long var6 = 33808197815072L;
      long var8 = var6 ^ 4037178249367L;
      long var10 = var6 ^ 114365011053255L;
      long var12 = var6 ^ 51756349362264L;
      int var14 = (int)((var6 ^ 51756349362264L) >>> 32);
      int var15 = (int)((var6 ^ 51756349362264L) << 32 >>> 48);
      int var16 = (int)(var12 << 48 >>> 48);
      long var17 = var6 ^ 5369897577622L;
      class_310 var19 = class_310.method_1551();
      if (!I) {
         return false;
      } else if (var0 != var19.field_1773.method_19418()) {
         return false;
      } else if (var19.field_1724 != null && var2 == var19.field_1724 && var1 == var19.field_1687) {
         m(var8);
         if (!I) {
            return false;
         } else {
            class_746 var20 = var19.field_1724;
            float var21 = ny;
            class_243 var22 = var20.method_30950(var3);
            float var23 = var20.method_17682() * ClientSupport3.bodyHeight(var20);
            class_243 var24 = var22.method_1031(0.0, var23 * 0.53, 0.0);
            float var25 = Easing.SMOOTHSTEP.apply(L(var17, (var21 - 0.72F) / 0.28F));
            float var26 = b >= 0.0F ? 360.0F * Easing.SINE_IN_OUT.apply(b / nA) : 0.0F;
            float var27 = q + T + (u + var26) * var25;
            float var28 = N + W * var25;
            float var29 = (float)(var23 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0)))) * n5;
            class_243 var30 = class_243.method_1030(var28, var27 + 180.0F);
            class_243 var31 = var30.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
            class_243 var32 = var31.method_1036(var30);
            double var33 = Math.tan(Math.toRadians(23.0));
            double var35 = var33 * I(var19);
            class_243 var37 = var24.method_1020(var30.method_1021(var29))
               .method_1020(var31.method_1021(k * var29 * var35))
               .method_1020(var32.method_1021(nY * var29 * var33));
            float var38 = n(var10, var1, var20, var24, var37);
            S = var38 < S ? var38 : S + (var38 - S) * (1.0F - (float)Math.exp(-O * 4.0));
            float var39 = Math.max(0.42F, S);
            class_243 var40 = var24.method_1020(var30.method_1021(var29 * var39))
               .method_1020(var31.method_1021(k * var29 * var35))
               .method_1020(var32.method_1021(nY * var29 * var33));
            if (var39 > S + 0.001F || S < 0.999F) {
               float var41 = n(var10, var1, var20, var24, var40);
               if (var41 < 1.0F) {
                  var40 = var24.method_1019(var40.method_1020(var24).method_1021(var41));
               }
            }

            float var58 = (u + var26) * var25;
            if (Y) {
               class_243 var59 = var22.method_1019(y).method_1020(var24);
               class_243 var60 = var40.method_1020(var24);
               double var61 = Math.atan2(var59.field_1350, var59.field_1352);
               double var62 = Math.atan2(var60.field_1350, var60.field_1352);
               double var63 = class_3532.method_15338(Math.toDegrees(var62 - var61)) * (float) (Math.PI / 180.0);
               double var64 = var61 + var63 * var21;
               double var65 = class_3532.method_16436(var21, var59.method_37267(), var60.method_37267());
               class_243 var66 = var24.method_1031(
                  Math.cos(var64) * var65, class_3532.method_16436(var21, var59.field_1351, var60.field_1351), Math.sin(var64) * var65
               );
               float var67 = n(var10, var1, var20, var24, var66);
               if (var67 < 1.0F) {
                  var66 = var24.method_1019(var66.method_1020(var24).method_1021(var67));
               }

               return B(var66, L + (class_3532.method_15393(q + T + 180.0F - L) + var58) * var21, class_3532.method_16439(var21, R, var28), var4, var5);
            } else {
               class_243 var42 = var20.method_5836(var3);
               float var43 = var20.method_5705(var3);
               float var44 = var20.method_5695(var3);
               class_243 var45 = class_243.method_1030(0.0F, var43);
               double var46 = var40.method_1022(var42);
               class_243 var48 = var42.method_1019(var45.method_1021(var46 * 0.42)).method_1031(0.0, var46 * 0.06, 0.0);
               class_243 var49 = var40.method_1020(var24);
               class_243 var50 = var40.method_1020(var49.method_1027() < 1.0E-6 ? class_243.field_1353 : var49.method_1029().method_1021(var46 * 0.15));
               if (!w && h) {
                  w = true;
                  r = h(var1, var20, var42, var14, var48, (short)var15, var50, var40, (short)var16);
               }

               if (r) {
                  var48 = var24.method_1019(var49.method_1021(0.35));
                  var50 = var24.method_1019(var49.method_1021(0.7));
               }

               float var51 = 1.0F - (float)Math.pow(1.0F - var21, 2.2);
               class_243 var52 = E(var42, var48, var50, var40, var51);
               float var53 = n(var10, var1, var20, var24, var52);
               if (var53 < 1.0F) {
                  var52 = var24.method_1019(var52.method_1020(var24).method_1021(var53));
               }

               float var54 = Easing.SINE_IN_OUT.apply(L(var17, (var21 - 0.02F) / 0.86F));
               float var55 = class_3532.method_15393(q + T + 180.0F - var43);
               if (var55 > 0.0F) {
                  var55 -= 360.0F;
               }

               float var56 = var43 + (var55 + var58) * var54;
               float var57 = class_3532.method_16439(var54, var44, var28);
               return B(var52, var56, var57, var4, var5);
            }
         }
      } else {
         reset();
         return false;
      }
   }

   private static boolean B(class_243 var0, float var1, float var2, double[] var3, float[] var4) {
      E[0] = var0.field_1352;
      E[1] = var0.field_1351;
      E[2] = var0.field_1350;
      s = var1;
      a = var2;
      l = true;
      var3[0] = var0.field_1352;
      var3[1] = var0.field_1351;
      var3[2] = var0.field_1350;
      var4[0] = var1;
      var4[1] = var2;
      return true;
   }

   private static boolean h(class_1937 var0, class_1297 var1, class_243 var2, int var3, class_243 var4, short var5, class_243 var6, class_243 var7, short var8) {
      HitSplashRenderer2.ensureSourceClassInitialized();
      class_243 var9 = var2;

      for (int var10 = 1; var10 <= 12; var10++) {
         class_243 var11 = E(var2, var4, var6, var7, 1.0F - (float)Math.pow(1.0F - var10 / 12.0F, 2.2));
         if (var0.method_17742(new class_3959(var9, var11, class_3960.field_23142, class_242.field_1348, var1)).method_17783() != class_240.field_1333) {
            return true;
         }

         var9 = var11;
      }

      return false;
   }

   private static float w(float var0, long var1) {
      long var3 = 28990279083958L;
      return Easing.SMOOTHSTEP.apply(L(var3, var0 * 1.15F));
   }

   private static void y(class_1937 var0, long var1, class_746 var3) {
      long var4 = 130550769959399L;
      class_310 var6 = class_310.method_1551();
      HitSplashRenderer2.ensureSourceClassInitialized();
      float var7 = var3.method_17682() * ClientSupport3.bodyHeight(var3);
      class_243 var8 = var3.method_73189().method_1031(0.0, var7 * 0.53, 0.0);
      float var9 = (float)(var7 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0))));
      double var10 = Math.tan(Math.toRadians(23.0));
      double var12 = var10 * I(var6);
      float[][] var14 = new float[M.length][X.length];

      for (int var15 = 0; var15 < M.length; var15++) {
         for (int var16 = 0; var16 < X.length; var16++) {
            class_243 var17 = L(var8, var3.method_36454() + X[var16], M[var15], var9 * 1.1F, var12, var10);
            long var18 = 102484033048213L;
            int var49 = var18 < 0L ? -1 : 1;
            var14[var15][var16] = n(var4, var0, var3, var8, var17);
         }

         long var39 = 102484033048212L;
         int var45 = var39 < 0L ? -1 : 1;
      }

      for (float var19 : new float[]{0.92F, 0.5F}) {
         int var20 = 0;

         while (var20 < M.length) {
            long var21 = 102484033048212L;
            int var50 = var21 < 0L ? -1 : 1;

            for (int var24 = 0; var24 < X.length; var24++) {
               long var25 = 102484033048213L;
               int var25x = var25 < 0L ? -1 : 1;
               float var28x = Math.abs(X[var24]);
               long var29 = 102484033048213L;
               int var28x = var29 < 0L ? -1 : 1;
               float var31x;
               int var32 = (var31x = var28x - 90.0F) == 0.0F ? 0 : (var31x < 0.0F ? -1 : 1);
               long var33 = 102484033048212L;
               int var32x = var33 < 0L ? -1 : 1;
               if (var32 <= 0) {
                  float var35x;
                  int var36 = (var35x = var14[var20][var24] - var19) == 0.0F ? 0 : (var35x < 0.0F ? -1 : 1);
                  long var37 = 102484033048212L;
                  int var36x = var37 < 0L ? -1 : 1;
                  if (var36 >= 0) {
                     T = X[var24];
                     N = M[var20];
                     return;
                  }
               }
            }

            var20++;
            long var50x = 102484033048212L;
            int var24x = var50x < 0L ? -1 : 1;
         }

         long var48 = 102484033048213L;
         int var56 = var48 < 0L ? -1 : 1;
      }

      int var41 = 0;
      int var43 = 0;

      for (int var45 = 0; var45 < M.length; var45++) {
         long var47 = 102484033048213L;
         int var51 = var47 < 0L ? -1 : 1;

         for (int var22 = 0; var22 < X.length; var22++) {
            if (var14[var45][var22] > var14[var41][var43] + 0.05F) {
               var41 = var45;
               var43 = var22;
            }
         }

         long var49 = 102484033048213L;
         int var57 = var49 < 0L ? -1 : 1;
      }

      T = X[var43];
      long var46 = 102484033048213L;
      int var48 = var46 < 0L ? -1 : 1;
      N = M[var41];
   }

   private static class_243 L(class_243 var0, float var1, float var2, float var3, double var4, double var6) {
      class_243 var8 = class_243.method_1030(var2, var1 + 180.0F);
      class_243 var9 = var8.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
      class_243 var10 = var9.method_1036(var8);
      return var0.method_1020(var8.method_1021(var3)).method_1020(var9.method_1021(k * var3 * var4)).method_1020(var10.method_1021(nY * var3 * var6));
   }

   private static float n(long var0, class_1937 var2, class_1297 var3, class_243 var4, class_243 var5) {
      HitSplashRenderer2.ensureSourceClassInitialized();
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
               long var18 = 130550769959399L;
               int var12x = var18 < 0L ? -1 : 1;
               var12 = var11 == 1 ? 0.1 : (var11 == 2 ? -0.1 : 0.0);
               long var21 = 130550769959398L;
               int var14x = var21 < 0L ? -1 : 1;
               var14 = var11 == 3 ? 0.1 : (var11 == 4 ? -0.1 : 0.0);
               long var23 = 130550769959399L;
               int var16x = var23 < 0L ? -1 : 1;
               if (var11 != 1) {
                  long var25 = 130550769959399L;
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
            class_3965 var22 = var2.method_17742(new class_3959(var20, var28, class_3960.field_23142, class_242.field_1348, var3));
            long var31 = 130550769959399L;
            int var271 = var31 < 0L ? -1 : 1;
            if (var22.method_17783() != class_240.field_1333) {
               var9 = Math.min(var9, var22.method_17784().method_1022(var20) - 0.2);
            }
         }

         long var27 = 130550769959399L;
         int var13 = var27 < 0L ? -1 : 1;
         return (float)Math.clamp(var9 / var7, Math.min(1.0, 0.7 / var7), 1.0);
      }
   }

   private static double I(class_310 var0) {
      return Math.max(0.2, (double)var0.method_22683().method_4489() / Math.max(1, var0.method_22683().method_4506()));
   }

   private static class_243 E(class_243 var0, class_243 var1, class_243 var2, class_243 var3, float var4) {
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

   private static Easing P(float var0, float var1, float var2, float var3) {
      return Easing.bezier(var0, var1, var2, var3);
   }

   private static float L(long var0, float var2) {
      HitSplashRenderer2.ensureSourceClassInitialized();
      return var2 < 0.0F ? 0.0F : Math.min(var2, 1.0F);
   }

   static String describe() {
      return String.format(
         Locale.ROOT,
         "faced %.1f, engaged %s, wanted %s, path %.3f, side %.0f, orbit %.1f/%.1f, zoom %.2f, room %.2f, at %.2f %.2f %.2f yaw %.1f pitch %.1f, frame %.2f/%.2f, game fov %.1f",
         q,
         I,
         h,
         ny,
         T,
         u,
         W,
         n5,
         S,
         E[0],
         E[1],
         E[2],
         s,
         a,
         k,
         nY,
         A
      );
   }
}
