package dev.purxiy.modules.jumpcircles;

import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
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

public final class JumpCirclesFrameView {
   private static final float V = 1.55F;
   private static final float W = 1.1F;
   static final float N = 46.0F;
   private static final float h = 0.47F;
   private static final float n = 27.0F;
   private static final float o = 6.0F;
   private static final float[] v = new float[]{27.0F, -27.0F, 0.0F, 70.0F, -70.0F, 115.0F, -115.0F, 180.0F};
   private static final float[] s = new float[]{6.0F, 24.0F, 42.0F};
   private static final double F = 0.9;
   private static final double k = 0.7;
   private static final double E = 0.2;
   private static final float P = 0.42F;
   private static final float H = 0.5F;
   private static final float u = 0.55F;
   private static final float A = 1.9F;
   private static final float g = -28.0F;
   private static final float t = 62.0F;
   private static final Easing G = u(0.42F, 0.0F, 0.12F, 1.0F);
   private static final Easing a = u(0.45F, 0.0F, 0.25F, 1.0F);
   private static boolean J;
   private static boolean L;
   private static class_5498 l;
   private static float y;
   private static float I;
   private static float U;
   private static float w;
   private static float bT;
   private static long j = 0L;
   private static float Q;
   private static float Z;
   private static boolean p;
   private static boolean X;
   private static boolean i;
   private static class_243 D;
   private static float e;
   private static float b;
   private static float x;
   private static float bj;
   private static float S;
   private static float Y;
   private static float f;
   private static float b9;
   private static float bM;
   private static boolean z;
   private static float c;
   private static float R;
   private static float C;
   private static float O;
   private static float d;
   private static float b6;
   private static float bE;
   private static float r;
   private static float m;
   private static float T;
   private static final double[] K = new double[]{0.0, 0.0, 0.0};
   private static float q;
   private static float M;
   private static boolean B;
   private static final String ab = "faced %.1f, engaged %s, wanted %s, path %.3f, side %.0f, orbit %.1f/%.1f, zoom %.2f, room %.2f, at %.2f %.2f %.2f yaw %.1f pitch %.1f, frame %.2f/%.2f, game fov %.1f";
   private static final long bb = -505666857903063028L;

   private JumpCirclesFrameView() {
   }

   public static boolean active() {
      return L;
   }

   public static float progress() {
      return y;
   }

   public static boolean settled() {
      return L && J && y >= 0.999F;
   }

   public static class_243 position() {
      return new class_243(K[0], K[1], K[2]);
   }

   static void open() {
      long var0 = 49243897049592L;
      int var2 = (int)((var0 ^ 10686103118787L) >>> 32);
      long var3 = (var0 ^ 10686103118787L) << 32 >>> 32;
      class_310 var5 = class_310.method_1551();
      class_746 var6 = var5.field_1724;
      if (var6 != null && var5.field_1687 != null) {
         if (!L) {
            l = var5.field_1690.method_31044();
            class_4184 var7 = var5.field_1773.method_19418();
            float var8 = var5.method_61966().method_60637(false);
            D = var7.method_71156().method_1020(var6.method_30950(var8));
            e = var7.method_19330();
            b = var7.method_19329();
            p = !l.method_31034() && D.method_37267() > 1.0;
            i = false;
            var5.field_1690.method_31043(class_5498.field_26665);
            L = true;
            y = 0.0F;
            x = var6.method_36454();
            var6.method_5636(x);
            var6.field_6220 = x;
            var6.method_5847(x);
            var6.field_6259 = x;
            B(var2, var5.field_1687, var6, var3);
            O = 0.0F;
            R = 0.0F;
            d = 0.0F;
            C = 0.0F;
            bE = 1.0F;
            b6 = 1.0F;
            r = -1.0F;
            T = 1.0F;
            z = false;
            j = 0L;
         }

         J = true;
         t(1.0F, 1.55F);
      }
   }

   static void close(boolean var0) {
      if (L) {
         if (J) {
            R = class_3532.method_15393(R);
            O = class_3532.method_15393(O);
            r = -1.0F;
         }

         J = false;
         t(0.0F, var0 ? 0.55F : 1.1F);
      }
   }

   static void hurry() {
      if (L && !J && !(Z >= 0.0F)) {
         float var0 = bT - w;
         float var1 = 0.3F * Math.max(0.35F, y);
         if (var0 > var1) {
            I = y;
            U = 0.0F;
            w = 0.0F;
            bT = var1;
         }
      }
   }

   static void reset() {
      if (L) {
         class_310 var0 = class_310.method_1551();
         if (l != null && var0.field_1690.method_31044() == class_5498.field_26665) {
            var0.field_1690.method_31043(l);
         }

         l = null;
         L = false;
         J = false;
         y = 0.0F;
         Z = -1.0F;
      }
   }

   private static void t(float var0, float var1) {
      I = y;
      U = var0;
      w = 0.0F;
      float var2 = Math.abs(var0 - y);
      bT = var1 * Math.max(0.35F, var2) / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   private static void S(long var0) {
      long var2 = 26933881342358L;
      JumpCirclesRenderPass.ensureSourceClassInitialized();
      long var4 = System.nanoTime();
      float var6 = j == 0L ? 0.0F : Math.min(0.1F, (float)(var4 - j) / 1.0E9F);
      long var7 = 102605263880799L;
      int var10 = var7 < 0L ? -1 : 1;
      j = var4;
      long var10x = 102605263880799L;
      int var29 = var10x < 0L ? -1 : 1;
      Q = var6;
      if (Z >= 0.0F) {
         long var12 = 102605263880799L;
         int var30 = var12 < 0L ? -1 : 1;
         y = Z;
      } else {
         long var28 = 102605263880800L;
         int var311 = var28 < 0L ? -1 : 1;
         w = Math.min(bT, w + var6);
         long var14 = 102605263880799L;
         int var31 = var14 < 0L ? -1 : 1;
         float var16 = bT <= 0.0F ? 1.0F : w / bT;
         y = I + (U - I) * (U > I ? G : a).apply(var16);
      }

      float var29x = 1.0F - (float)Math.exp(-var6 * 9.0);
      float var13 = R + (O - R) * var29x;
      long var30 = 102605263880799L;
      int var35 = var30 < 0L ? -1 : 1;
      R = var13;
      C = C + (d - C) * var29x;
      long var31 = 102605263880800L;
      int var19 = var31 < 0L ? -1 : 1;
      b6 = b6 + (bE - b6) * (1.0F - (float)Math.exp(-var6 * 7.0));
      float var19x = z ? 1.0F - (float)Math.exp(-var6 * 6.0) : 1.0F;
      Y = Y + (b9 - Y) * var19x;
      long var20 = 102605263880800L;
      int var20x = var20 < 0L ? -1 : 1;
      f = f + (bM - f) * var19x;
      z = true;
      long var22x = 102605263880799L;
      if (r >= 0.0F) {
         r += var6;
         float var24;
         int var25 = (var24 = r - m) == 0.0F ? 0 : (var24 < 0.0F ? -1 : 1);
         long var26 = 102605263880800L;
         int var26x = var26 < 0L ? -1 : 1;
         if (var25 >= 0) {
            r = -1.0F;
         }
      }

      if (!J) {
         long var32x = 102605263880799L;
         int var41 = var32x < 0L ? -1 : 1;
         if (y <= 5.0E-4F && w >= bT && Z < 0.0F) {
            Q(var2);
         }
      }
   }

   private static void Q(long var0) {
      JumpCirclesRenderPass.ensureSourceClassInitialized();
      class_310 var2 = class_310.method_1551();
      if (l != null && var2.field_1690.method_31044() == class_5498.field_26665) {
         var2.field_1690.method_31043(l);
      }

      if (l == class_5498.field_26664) {
         var2.field_1773.field_4012.method_3215(class_1268.field_5808);
         var2.field_1773.field_4012.method_3215(class_1268.field_5810);
      }

      l = null;
      L = false;
   }

   static void frame(float var0, float var1) {
      b9 = var0;
      bM = var1;
   }

   static void drag(float var0, float var1) {
      O -= var0 * 0.32F;
      d = class_3532.method_15363(d + var1 * 0.24F, -28.0F - S, 62.0F - S);
      r = -1.0F;
   }

   static void zoom(double var0) {
      bE = class_3532.method_15363(bE * (float)Math.pow(0.88, var0), 0.55F, 1.9F);
   }

   static void resetView() {
      O = Math.round(O / 360.0F) * 360.0F;
      d = 0.0F;
      bE = 1.0F;
      r = -1.0F;
   }

   static boolean moved() {
      return Math.abs(class_3532.method_15393(O)) > 0.5F || Math.abs(d) > 0.5F || Math.abs(bE - 1.0F) > 0.01F;
   }

   static void overlook(float var0, float var1) {
      d = class_3532.method_15363(var0, -28.0F - S, 62.0F - S);
      bE = class_3532.method_15363(var1, 0.55F, 1.9F);
      r = -1.0F;
   }

   static void turntable(float var0) {
      O = R = class_3532.method_15393(R);
      r = 0.0F;
      m = var0 / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   static boolean turning() {
      return r >= 0.0F;
   }

   static void hold(float var0) {
      Z = var0 < 0.0F ? -1.0F : Math.min(1.0F, var0);
      if (Z < 0.0F) {
         I = y;
         w = 0.0F;
         bT = 0.4F;
      }
   }

   public static float fov(float var0) {
      long var1 = 115939035570300L;
      long var3 = (var1 ^ 83035880962847L) >>> 16;
      int var5 = (int)((var1 ^ 83035880962847L) << 48 >>> 48);
      if (!L) {
         return var0;
      } else {
         c = var0;
         return class_3532.method_16439(R(y, var3, (char)var5), var0, A());
      }
   }

   private static float A() {
      double var0 = Math.tan(Math.toRadians(23.0)) / Math.max(0.42F, T);
      return (float)Math.toDegrees(2.0 * Math.atan(var0));
   }

   public static boolean hides(class_1297 var0) {
      class_310 var1 = class_310.method_1551();
      if (L && var0 == var1.field_1724 && B) {
         class_243 var2 = var0.method_5836(var1.method_61966().method_60637(false));
         double var3 = K[0] - var2.field_1352;
         double var5 = K[1] - var2.field_1351;
         double var7 = K[2] - var2.field_1350;
         double var9 = 0.9 * Math.max(0.05, var0.method_17682() / 1.8);
         return var3 * var3 + var5 * var5 + var7 * var7 < var9 * var9;
      } else {
         return false;
      }
   }

   public static boolean place(class_4184 var0, class_1937 var1, class_1297 var2, float var3, double[] var4, float[] var5) {
      long var6 = 57542931714384L;
      long var8 = var6 ^ 115467583705904L;
      long var10 = var6 ^ 42235516796179L;
      long var12 = var6 ^ 52618478219811L;
      int var14 = (int)((var6 ^ 138594795543763L) >>> 48);
      long var15 = (var6 ^ 138594795543763L) << 16 >>> 16;
      class_310 var17 = class_310.method_1551();
      if (!L) {
         return false;
      } else if (var0 != var17.field_1773.method_19418()) {
         return false;
      } else if (var17.field_1724 != null && var2 == var17.field_1724 && var1 == var17.field_1687) {
         S(var8);
         if (!L) {
            return false;
         } else {
            class_746 var18 = var17.field_1724;
            float var19 = y;
            class_243 var20 = var18.method_30950(var3);
            float var21 = var18.method_17682() * SkyPipelineRouter.bodyHeight(var18);
            class_243 var22 = var20.method_1031(0.0, var21 * 0.53, 0.0);
            float var23 = Easing.SMOOTHSTEP.apply(n(var12, (var19 - 0.72F) / 0.28F));
            float var24 = r >= 0.0F ? 360.0F * Easing.SINE_IN_OUT.apply(r / m) : 0.0F;
            float var25 = x + bj + (R + var24) * var23;
            float var26 = S + C * var23;
            float var27 = (float)(var21 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0)))) * b6;
            class_243 var28 = class_243.method_1030(var26, var25 + 180.0F);
            class_243 var29 = var28.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
            class_243 var30 = var29.method_1036(var28);
            double var31 = Math.tan(Math.toRadians(23.0));
            double var33 = var31 * X(var17);
            class_243 var35 = var22.method_1020(var28.method_1021(var27))
               .method_1020(var29.method_1021(Y * var27 * var33))
               .method_1020(var30.method_1021(f * var27 * var31));
            float var36 = u(var1, (char)var14, var18, var22, var35, var15);
            T = var36 < T ? var36 : T + (var36 - T) * (1.0F - (float)Math.exp(-Q * 4.0));
            float var37 = Math.max(0.42F, T);
            class_243 var38 = var22.method_1020(var28.method_1021(var27 * var37))
               .method_1020(var29.method_1021(Y * var27 * var33))
               .method_1020(var30.method_1021(f * var27 * var31));
            if (var37 > T + 0.001F || T < 0.999F) {
               float var39 = u(var1, (char)var14, var18, var22, var38, var15);
               if (var39 < 1.0F) {
                  var38 = var22.method_1019(var38.method_1020(var22).method_1021(var39));
               }
            }

            float var56 = (R + var24) * var23;
            if (p) {
               class_243 var57 = var20.method_1019(D).method_1020(var22);
               class_243 var58 = var38.method_1020(var22);
               double var59 = Math.atan2(var57.field_1350, var57.field_1352);
               double var60 = Math.atan2(var58.field_1350, var58.field_1352);
               double var61 = class_3532.method_15338(Math.toDegrees(var60 - var59)) * (float) (Math.PI / 180.0);
               double var62 = var59 + var61 * var19;
               double var63 = class_3532.method_16436(var19, var57.method_37267(), var58.method_37267());
               class_243 var64 = var22.method_1031(
                  Math.cos(var62) * var63, class_3532.method_16436(var19, var57.field_1351, var58.field_1351), Math.sin(var62) * var63
               );
               float var65 = u(var1, (char)var14, var18, var22, var64, var15);
               if (var65 < 1.0F) {
                  var64 = var22.method_1019(var64.method_1020(var22).method_1021(var65));
               }

               return b(var64, e + (class_3532.method_15393(x + bj + 180.0F - e) + var56) * var19, class_3532.method_16439(var19, b, var26), var4, var5);
            } else {
               class_243 var40 = var18.method_5836(var3);
               float var41 = var18.method_5705(var3);
               float var42 = var18.method_5695(var3);
               class_243 var43 = class_243.method_1030(0.0F, var41);
               double var44 = var38.method_1022(var40);
               class_243 var46 = var40.method_1019(var43.method_1021(var44 * 0.42)).method_1031(0.0, var44 * 0.06, 0.0);
               class_243 var47 = var38.method_1020(var22);
               class_243 var48 = var38.method_1020(var47.method_1027() < 1.0E-6 ? class_243.field_1353 : var47.method_1029().method_1021(var44 * 0.15));
               if (!i && J) {
                  i = true;
                  X = g(var1, var18, var40, var10, var46, var48, var38);
               }

               if (X) {
                  var46 = var22.method_1019(var47.method_1021(0.35));
                  var48 = var22.method_1019(var47.method_1021(0.7));
               }

               float var49 = 1.0F - (float)Math.pow(1.0F - var19, 2.2);
               class_243 var50 = N(var40, var46, var48, var38, var49);
               float var51 = u(var1, (char)var14, var18, var22, var50, var15);
               if (var51 < 1.0F) {
                  var50 = var22.method_1019(var50.method_1020(var22).method_1021(var51));
               }

               float var52 = Easing.SINE_IN_OUT.apply(n(var12, (var19 - 0.02F) / 0.86F));
               float var53 = class_3532.method_15393(x + bj + 180.0F - var41);
               if (var53 > 0.0F) {
                  var53 -= 360.0F;
               }

               float var54 = var41 + (var53 + var56) * var52;
               float var55 = class_3532.method_16439(var52, var42, var26);
               return b(var50, var54, var55, var4, var5);
            }
         }
      } else {
         reset();
         return false;
      }
   }

   private static boolean b(class_243 var0, float var1, float var2, double[] var3, float[] var4) {
      K[0] = var0.field_1352;
      K[1] = var0.field_1351;
      K[2] = var0.field_1350;
      q = var1;
      M = var2;
      B = true;
      var3[0] = var0.field_1352;
      var3[1] = var0.field_1351;
      var3[2] = var0.field_1350;
      var4[0] = var1;
      var4[1] = var2;
      return true;
   }

   private static boolean g(class_1937 var0, class_1297 var1, class_243 var2, long var3, class_243 var5, class_243 var6, class_243 var7) {
      JumpCirclesRenderPass.ensureSourceClassInitialized();
      class_243 var8 = var2;

      for (int var9 = 1; var9 <= 12; var9++) {
         class_243 var10 = N(var2, var5, var6, var7, 1.0F - (float)Math.pow(1.0F - var9 / 12.0F, 2.2));
         long var11 = 20050970455107L;
         int var13 = var11 < 0L ? -1 : 1;
         if (var0.method_17742(new class_3959(var8, var10, class_3960.field_23142, class_242.field_1348, var1)).method_17783() != class_240.field_1333) {
            return true;
         }

         var8 = var10;
      }

      return false;
   }

   private static float R(float var0, long var1, char var3) {
      long var4 = 38445950425955L;
      long var6 = var4 ^ 63194379139088L;
      return Easing.SMOOTHSTEP.apply(n(var6, var0 * 1.15F));
   }

   private static void B(int var0, class_1937 var1, class_746 var2, long var3) {
      long var5 = 41169167635003L;
      int var7 = (int)((var5 ^ 122224804863928L) >>> 48);
      long var8 = (var5 ^ 122224804863928L) << 16 >>> 16;
      JumpCirclesRenderPass.ensureSourceClassInitialized();
      class_310 var10 = class_310.method_1551();
      float var11 = var2.method_17682() * SkyPipelineRouter.bodyHeight(var2);
      class_243 var12 = var2.method_73189().method_1031(0.0, var11 * 0.53, 0.0);
      float var13 = (float)(var11 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0))));
      double var14 = Math.tan(Math.toRadians(23.0));
      double var16 = var14 * X(var10);
      float[][] var18 = new float[s.length][v.length];

      for (int var19 = 0; var19 < s.length; var19++) {
         for (int var20 = 0; var20 < v.length; var20++) {
            class_243 var21 = E(var12, var2.method_36454() + v[var20], s[var19], var13 * 1.1F, var16, var14);
            var18[var19][var20] = u(var1, (char)var7, var2, var12, var21, var8);
         }
      }

      for (float var22 : new float[]{0.92F, 0.5F}) {
         for (int var23 = 0; var23 < s.length; var23++) {
            long var24 = 1906102843L;
            int var27 = var24 < 0L ? -1 : 1;

            for (int var27x = 0; var27x < v.length; var27x++) {
               short var28 = 9585;
               float var29;
               int var30 = (var29 = Math.abs(v[var27x]) - 90.0F) == 0.0F ? 0 : (var29 < 0.0F ? -1 : 1);
               long var31 = 1906102843L;
               int var33 = var31 < 0L ? -1 : 1;
               if (var30 <= 0) {
                  float var33x;
                  var30 = (var33x = var18[var23][var27x] - var22) == 0.0F ? 0 : (var33x < 0.0F ? -1 : 1);
                  if (var30 >= 0) {
                     bj = v[var27x];
                     S = s[var23];
                     return;
                  }
               }
            }
         }

         long var42 = 1906102842L;
         int var25 = var42 < 0L ? -1 : 1;
      }

      int var35 = 0;
      int var37 = 0;

      for (int var39 = 0; var39 < s.length; var39++) {
         for (int var40 = 0; var40 < v.length; var40++) {
            float[] var43 = var18[var39];
            if (var43[var40] > var18[var35][var37] + 0.05F) {
               var35 = var39;
               var37 = var40;
            }
         }

         long var41 = 1906102843L;
         int var431 = var41 < 0L ? -1 : 1;
      }

      bj = v[var37];
      S = s[var35];
   }

   private static class_243 E(class_243 var0, float var1, float var2, float var3, double var4, double var6) {
      class_243 var8 = class_243.method_1030(var2, var1 + 180.0F);
      class_243 var9 = var8.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
      class_243 var10 = var9.method_1036(var8);
      return var0.method_1020(var8.method_1021(var3)).method_1020(var9.method_1021(Y * var3 * var4)).method_1020(var10.method_1021(f * var3 * var6));
   }

   private static float u(class_1937 var0, char var1, class_1297 var2, class_243 var3, class_243 var4, long var5) {
      class_243 var7 = var4.method_1020(var3);
      JumpCirclesRenderPass.ensureSourceClassInitialized();
      double var8 = var7.method_1033();
      if (var8 < 1.0E-4) {
         return 1.0F;
      } else {
         double var10 = var8;

         for (int var12 = 0; var12 < 5; var12++) {
            double var13;
            double var17;
            double var15;
            label51: {
               boolean var19 = false;
               var13 = var12 == 1 ? 0.1 : (var12 == 2 ? -0.1 : 0.0);
               long var20 = 81745568288131L;
               int var15x = var20 < 0L ? -1 : 1;
               var15 = var12 == 3 ? 0.1 : (var12 == 4 ? -0.1 : 0.0);
               boolean var22 = false;
               if (var12 != 1) {
                  var22 = false;
                  if (var12 != 2) {
                     var17 = var12 == 3 ? -0.1 : 0.0;
                     break label51;
                  }
               }

               var17 = 0.1;
            }

            class_243 var21 = var3.method_1031(var13, var15, var17);
            class_243 var25 = var4.method_1031(var13, var15, var17);
            class_3965 var23 = var0.method_17742(new class_3959(var21, var25, class_3960.field_23142, class_242.field_1348, var2));
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

   private static class_243 N(class_243 var0, class_243 var1, class_243 var2, class_243 var3, float var4) {
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

   private static Easing u(float var0, float var1, float var2, float var3) {
      return Easing.bezier(var0, var1, var2, var3);
   }

   private static float n(long var0, float var2) {
      JumpCirclesRenderPass.ensureSourceClassInitialized();
      return var2 < 0.0F ? 0.0F : Math.min(var2, 1.0F);
   }

   static String describe() {
      return String.format(
         Locale.ROOT,
         "faced %.1f, engaged %s, wanted %s, path %.3f, side %.0f, orbit %.1f/%.1f, zoom %.2f, room %.2f, at %.2f %.2f %.2f yaw %.1f pitch %.1f, frame %.2f/%.2f, game fov %.1f",
         x,
         L,
         J,
         y,
         bj,
         R,
         C,
         b6,
         T,
         K[0],
         K[1],
         K[2],
         q,
         M,
         Y,
         f,
         c
      );
   }
}
