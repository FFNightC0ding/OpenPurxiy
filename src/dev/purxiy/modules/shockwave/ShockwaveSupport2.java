// Sourced By DevFFNightmare
package dev.purxiy.modules.shockwave;

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

public final class ShockwaveSupport2 {
   private static final float B = 1.55F;
   private static final float o = 1.1F;
   static final float G = 46.0F;
   private static final float Z = 0.47F;
   private static final float d = 27.0F;
   private static final float n = 6.0F;
   private static final float[] w = new float[]{27.0F, -27.0F, 0.0F, 70.0F, -70.0F, 115.0F, -115.0F, 180.0F};
   private static final float[] Y = new float[]{6.0F, 24.0F, 42.0F};
   private static final double l = 0.9;
   private static final double D = 0.7;
   private static final double u = 0.2;
   private static final float N = 0.42F;
   private static final float F = 0.5F;
   private static final float h = 0.55F;
   private static final float k = 1.9F;
   private static final float C = -28.0F;
   private static final float s = 62.0F;
   private static final Easing z = F(0.42F, 0.0F, 0.12F, 1.0F);
   private static final Easing v = F(0.45F, 0.0F, 0.25F, 1.0F);
   private static boolean y;
   private static boolean PY;
   
   private static class_5498 Pr;
   private static float Q;
   private static float P2;
   private static float H;
   private static float i;
   private static float a;
   private static long P4 = 0L;
   private static float A;
   private static float X;
   private static boolean q;
   private static boolean U;
   private static boolean R;
   private static class_243 P;
   private static float T;
   private static float I;
   private static float V;
   private static float t;
   private static float W;
   private static float PJ;
   private static float E;
   private static float J;
   private static float PO;
   private static boolean x;
   private static float m;
   private static float S;
   private static float O;
   private static float r;
   private static float e;
   private static float c;
   private static float K;
   private static float L;
   private static float M;
   private static float p;
   private static final double[] j = new double[]{0.0, 0.0, 0.0};
   private static float f;
   private static float b;
   private static boolean g;
   private static final String ab = "faced %.1f, engaged %s, wanted %s, path %.3f, side %.0f, orbit %.1f/%.1f, zoom %.2f, room %.2f, at %.2f %.2f %.2f yaw %.1f pitch %.1f, frame %.2f/%.2f, game fov %.1f";
   private static final long bb = 1604353310792351756L;

   private ShockwaveSupport2() {
   }

   public static boolean active() {
      return PY;
   }

   public static float progress() {
      return Q;
   }

   public static boolean settled() {
      return PY && y && Q >= 0.999F;
   }

   public static class_243 position() {
      return new class_243(j[0], j[1], j[2]);
   }

   static void open() {
      long var0 = 102064858343440L;
      long var2 = var0 ^ 105158458399188L;
      class_310 var4 = class_310.method_1551();
      class_746 var5 = var4.field_1724;
      if (var5 != null && var4.field_1687 != null) {
         if (!PY) {
            Pr = var4.field_1690.method_31044();
            class_4184 var6 = var4.field_1773.method_19418();
            float var7 = var4.method_61966().method_60637(false);
            P = var6.method_71156().method_1020(var5.method_30950(var7));
            T = var6.method_19330();
            I = var6.method_19329();
            q = !Pr.method_31034() && P.method_37267() > 1.0;
            R = false;
            var4.field_1690.method_31043(class_5498.field_26665);
            PY = true;
            Q = 0.0F;
            V = var5.method_36454();
            var5.method_5636(V);
            var5.field_6220 = V;
            var5.method_5847(V);
            var5.field_6259 = V;
            b(var2, var4.field_1687, var5);
            r = 0.0F;
            S = 0.0F;
            e = 0.0F;
            O = 0.0F;
            K = 1.0F;
            c = 1.0F;
            L = -1.0F;
            p = 1.0F;
            x = false;
            P4 = 0L;
         }

         y = true;
         U(1.0F, 1.55F);
      }
   }

   static void close(boolean var0) {
      if (PY) {
         if (y) {
            S = class_3532.method_15393(S);
            r = class_3532.method_15393(r);
            L = -1.0F;
         }

         y = false;
         U(0.0F, var0 ? 0.55F : 1.1F);
      }
   }

   static void hurry() {
      if (PY && !y && !(X >= 0.0F)) {
         float var0 = a - i;
         float var1 = 0.3F * Math.max(0.35F, Q);
         if (var0 > var1) {
            P2 = Q;
            H = 0.0F;
            i = 0.0F;
            a = var1;
         }
      }
   }

   static void reset() {
      if (PY) {
         class_310 var0 = class_310.method_1551();
         if (Pr != null && var0.field_1690.method_31044() == class_5498.field_26665) {
            var0.field_1690.method_31043(Pr);
         }

         Pr = null;
         PY = false;
         y = false;
         Q = 0.0F;
         X = -1.0F;
      }
   }

   private static void U(float var0, float var1) {
      P2 = Q;
      H = var0;
      i = 0.0F;
      float var2 = Math.abs(var0 - Q);
      a = var1 * Math.max(0.35F, var2) / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   private static void S(long var0) {
      long var2 = 124376884142618L;
      ShockwaveModule.ensureSourceClassInitialized();
      long var4 = System.nanoTime();
      float var6 = P4 == 0L ? 0.0F : Math.min(0.1F, (float)(var4 - P4) / 1.0E9F);
      P4 = var4;
      A = var6;
      float var7;
      int var8 = (var7 = X - 0.0F) == 0.0F ? 0 : (var7 < 0.0F ? -1 : 1);
      long var9 = 87769682435598L;
      if (87769682435598L == 0L) {
         boolean var10000 = false;
      } else {
         int var34 = var9 < 0L ? -1 : 1;
      }

      if (var8 >= 0) {
         long var12 = 87769682435597L;
         if (87769682435597L == 0L) {
            boolean var35 = false;
         } else {
            int var36 = var12 < 0L ? -1 : 1;
         }

         Q = X;
      } else {
         long var30 = 87769682435598L;
         if (87769682435598L == 0L) {
            boolean var37 = false;
         } else {
            int var38 = var30 < 0L ? -1 : 1;
         }

         i = Math.min(a, i + var6);
         float var14 = a <= 0.0F ? 1.0F : i / a;
         Q = P2 + (H - P2) * (H > P2 ? z : v).apply(var14);
      }

      float var31 = 1.0F - (float)Math.exp(-var6 * 9.0);
      float var13 = r - S;
      long var32 = 87769682435598L;
      if (87769682435598L == 0L) {
         boolean var39 = false;
      } else {
         int var40 = var32 < 0L ? -1 : 1;
      }

      S += var13 * var31;
      float var17 = (e - O) * var31;
      long var18 = 87769682435597L;
      if (87769682435597L == 0L) {
         boolean var41 = false;
      } else {
         int var42 = var18 < 0L ? -1 : 1;
      }

      O += var17;
      c = c + (K - c) * (1.0F - (float)Math.exp(-var6 * 7.0));
      float var20 = x ? 1.0F - (float)Math.exp(-var6 * 6.0) : 1.0F;
      float var21 = (J - PJ) * var20;
      long var22 = 87769682435598L;
      if (87769682435598L == 0L) {
         boolean var43 = false;
      } else {
         int var44 = var22 < 0L ? -1 : 1;
      }

      PJ += var21;
      E = E + (PO - E) * var20;
      x = true;
      long var24 = 87769682435597L;
      if (87769682435597L == 0L) {
         boolean var45 = false;
      } else {
         int var46 = var24 < 0L ? -1 : 1;
      }

      if (L >= 0.0F) {
         L += var6;
         float var26;
         var8 = (var26 = L - M) == 0.0F ? 0 : (var26 < 0.0F ? -1 : 1);
         long var27 = 87769682435597L;
         if (87769682435597L == 0L) {
            boolean var47 = false;
         } else {
            int var48 = var27 < 0L ? -1 : 1;
         }

         if (var8 >= 0) {
            L = -1.0F;
         }
      }

      if (!y) {
         long var33 = 87769682435598L;
         if (87769682435598L == 0L) {
            boolean var49 = false;
         } else {
            int var50 = var33 < 0L ? -1 : 1;
         }

         if (Q <= 5.0E-4F && i >= a && X < 0.0F) {
            w(var2);
         }
      }
   }

   private static void w(long var0) {
      ShockwaveModule.ensureSourceClassInitialized();
      class_310 var2 = class_310.method_1551();
      if (Pr != null && var2.field_1690.method_31044() == class_5498.field_26665) {
         var2.field_1690.method_31043(Pr);
      }

      if (Pr == class_5498.field_26664) {
         var2.field_1773.field_4012.method_3215(class_1268.field_5808);
         var2.field_1773.field_4012.method_3215(class_1268.field_5810);
      }

      Pr = null;
      PY = false;
   }

   static void frame(float var0, float var1) {
      J = var0;
      PO = var1;
   }

   static void drag(float var0, float var1) {
      r -= var0 * 0.32F;
      e = class_3532.method_15363(e + var1 * 0.24F, -28.0F - W, 62.0F - W);
      L = -1.0F;
   }

   static void zoom(double var0) {
      K = class_3532.method_15363(K * (float)Math.pow(0.88, var0), 0.55F, 1.9F);
   }

   static void resetView() {
      r = Math.round(r / 360.0F) * 360.0F;
      e = 0.0F;
      K = 1.0F;
      L = -1.0F;
   }

   static boolean moved() {
      return Math.abs(class_3532.method_15393(r)) > 0.5F || Math.abs(e) > 0.5F || Math.abs(K - 1.0F) > 0.01F;
   }

   static void overlook(float var0, float var1) {
      e = class_3532.method_15363(var0, -28.0F - W, 62.0F - W);
      K = class_3532.method_15363(var1, 0.55F, 1.9F);
      L = -1.0F;
   }

   static void turntable(float var0) {
      r = S = class_3532.method_15393(S);
      L = 0.0F;
      M = var0 / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   static boolean turning() {
      return L >= 0.0F;
   }

   static void hold(float var0) {
      X = var0 < 0.0F ? -1.0F : Math.min(1.0F, var0);
      if (X < 0.0F) {
         P2 = Q;
         i = 0.0F;
         a = 0.4F;
      }
   }

   public static float fov(float var0) {
      long var1 = 72640323947198L;
      int var3 = (int)((var1 ^ 117297718299561L) >>> 32);
      long var4 = (var1 ^ 117297718299561L) << 32 >>> 32;
      if (!PY) {
         return var0;
      } else {
         m = var0;
         return class_3532.method_16439(R(var3, Q, var4), var0, I());
      }
   }

   private static float I() {
      double var0 = Math.tan(Math.toRadians(23.0)) / Math.max(0.42F, p);
      return (float)Math.toDegrees(2.0 * Math.atan(var0));
   }

   public static boolean hides(class_1297 var0) {
      class_310 var1 = class_310.method_1551();
      if (PY && var0 == var1.field_1724 && g) {
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
      long var6 = 54706012798401L;
      long var8 = var6 ^ 94584346173744L;
      long var10 = var6 ^ 1028049394088L;
      long var12 = var6 ^ 20501095659558L;
      long var14 = var6 ^ 138616928723919L;
      class_310 var16 = class_310.method_1551();
      if (!PY) {
         return false;
      } else if (var0 != var16.field_1773.method_19418()) {
         return false;
      } else if (var16.field_1724 != null && var2 == var16.field_1724 && var1 == var16.field_1687) {
         S(var14);
         if (!PY) {
            return false;
         } else {
            class_746 var17 = var16.field_1724;
            float var18 = Q;
            class_243 var19 = var17.method_30950(var3);
            float var20 = var17.method_17682() * ClientSupport3.bodyHeight(var17);
            class_243 var21 = var19.method_1031(0.0, var20 * 0.53, 0.0);
            float var22 = Easing.SMOOTHSTEP.apply(i((var18 - 0.72F) / 0.28F, var8));
            float var23 = L >= 0.0F ? 360.0F * Easing.SINE_IN_OUT.apply(L / M) : 0.0F;
            float var24 = V + t + (S + var23) * var22;
            float var25 = W + O * var22;
            float var26 = (float)(var20 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0)))) * c;
            class_243 var27 = class_243.method_1030(var25, var24 + 180.0F);
            class_243 var28 = var27.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
            class_243 var29 = var28.method_1036(var27);
            double var30 = Math.tan(Math.toRadians(23.0));
            double var32 = var30 * r(var16);
            class_243 var34 = var21.method_1020(var27.method_1021(var26))
               .method_1020(var28.method_1021(PJ * var26 * var32))
               .method_1020(var29.method_1021(E * var26 * var30));
            float var35 = b(var1, var10, var17, var21, var34);
            p = var35 < p ? var35 : p + (var35 - p) * (1.0F - (float)Math.exp(-A * 4.0));
            float var36 = Math.max(0.42F, p);
            class_243 var37 = var21.method_1020(var27.method_1021(var26 * var36))
               .method_1020(var28.method_1021(PJ * var26 * var32))
               .method_1020(var29.method_1021(E * var26 * var30));
            if (var36 > p + 0.001F || p < 0.999F) {
               float var38 = b(var1, var10, var17, var21, var37);
               if (var38 < 1.0F) {
                  var37 = var21.method_1019(var37.method_1020(var21).method_1021(var38));
               }
            }

            float var55 = (S + var23) * var22;
            if (q) {
               class_243 var56 = var19.method_1019(P).method_1020(var21);
               class_243 var57 = var37.method_1020(var21);
               double var58 = Math.atan2(var56.field_1350, var56.field_1352);
               double var59 = Math.atan2(var57.field_1350, var57.field_1352);
               double var60 = class_3532.method_15338(Math.toDegrees(var59 - var58)) * (float) (Math.PI / 180.0);
               double var61 = var58 + var60 * var18;
               double var62 = class_3532.method_16436(var18, var56.method_37267(), var57.method_37267());
               class_243 var63 = var21.method_1031(
                  Math.cos(var61) * var62, class_3532.method_16436(var18, var56.field_1351, var57.field_1351), Math.sin(var61) * var62
               );
               float var64 = b(var1, var10, var17, var21, var63);
               if (var64 < 1.0F) {
                  var63 = var21.method_1019(var63.method_1020(var21).method_1021(var64));
               }

               return n(var63, T + (class_3532.method_15393(V + t + 180.0F - T) + var55) * var18, class_3532.method_16439(var18, I, var25), var4, var5);
            } else {
               class_243 var39 = var17.method_5836(var3);
               float var40 = var17.method_5705(var3);
               float var41 = var17.method_5695(var3);
               class_243 var42 = class_243.method_1030(0.0F, var40);
               double var43 = var37.method_1022(var39);
               class_243 var45 = var39.method_1019(var42.method_1021(var43 * 0.42)).method_1031(0.0, var43 * 0.06, 0.0);
               class_243 var46 = var37.method_1020(var21);
               class_243 var47 = var37.method_1020(var46.method_1027() < 1.0E-6 ? class_243.field_1353 : var46.method_1029().method_1021(var43 * 0.15));
               if (!R && y) {
                  R = true;
                  U = V(var1, var17, var12, var39, var45, var47, var37);
               }

               if (U) {
                  var45 = var21.method_1019(var46.method_1021(0.35));
                  var47 = var21.method_1019(var46.method_1021(0.7));
               }

               float var48 = 1.0F - (float)Math.pow(1.0F - var18, 2.2);
               class_243 var49 = Y(var39, var45, var47, var37, var48);
               float var50 = b(var1, var10, var17, var21, var49);
               if (var50 < 1.0F) {
                  var49 = var21.method_1019(var49.method_1020(var21).method_1021(var50));
               }

               float var51 = Easing.SINE_IN_OUT.apply(i((var18 - 0.02F) / 0.86F, var8));
               float var52 = class_3532.method_15393(V + t + 180.0F - var40);
               if (var52 > 0.0F) {
                  var52 -= 360.0F;
               }

               float var53 = var40 + (var52 + var55) * var51;
               float var54 = class_3532.method_16439(var51, var41, var25);
               return n(var49, var53, var54, var4, var5);
            }
         }
      } else {
         reset();
         return false;
      }
   }

   private static boolean n(class_243 var0, float var1, float var2, double[] var3, float[] var4) {
      j[0] = var0.field_1352;
      j[1] = var0.field_1351;
      j[2] = var0.field_1350;
      f = var1;
      b = var2;
      g = true;
      var3[0] = var0.field_1352;
      var3[1] = var0.field_1351;
      var3[2] = var0.field_1350;
      var4[0] = var1;
      var4[1] = var2;
      return true;
   }

   private static boolean V(class_1937 var0, class_1297 var1, long var2, class_243 var4, class_243 var5, class_243 var6, class_243 var7) {
      ShockwaveModule.ensureSourceClassInitialized();
      class_243 var8 = var4;

      for (int var9 = 1; var9 <= (int)bb; var9++) {
         class_243 var10 = Y(var4, var5, var6, var7, 1.0F - (float)Math.pow(1.0F - var9 / 12.0F, 2.2));
         long var11 = 38914365818342L;
         if (38914365818342L == 0L) {
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

   private static float R(int var0, float var1, long var2) {
      long var4 = 44798193008919L;
      long var6 = var4 ^ 87383161626086L;
      return Easing.SMOOTHSTEP.apply(i(var1 * 1.15F, var6));
   }

   private static void b(long var0, class_1937 var2, class_746 var3) {
      long var4 = 54075264689257L;
      class_310 var6 = class_310.method_1551();
      float var7 = var3.method_17682() * ClientSupport3.bodyHeight(var3);
      class_243 var8 = var3.method_73189().method_1031(0.0, var7 * 0.53, 0.0);
      float var9 = (float)(var7 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0))));
      double var10 = Math.tan(Math.toRadians(23.0));
      double var12 = var10 * r(var6);
      ShockwaveModule.ensureSourceClassInitialized();
      float[][] var14 = new float[Y.length][w.length];

      for (int var15 = 0; var15 < Y.length; var15++) {
         for (int var16 = 0; var16 < w.length; var16++) {
            long var17 = 3813111951812L;
            if (3813111951812L == 0L) {
               boolean var10000 = false;
            } else {
               int var53 = var17 < 0L ? -1 : 1;
            }

            class_243 var20 = l(var8, var3.method_36454() + w[var16], Y[var15], var9 * 1.1F, var12, var10);
            var14[var15][var16] = b(var2, var4, var3, var8, var20);
         }

         long var41 = 3813111951811L;
         if (3813111951811L == 0L) {
            boolean var54 = false;
         } else {
            int var55 = var41 < 0L ? -1 : 1;
         }
      }

      for (float var18 : new float[]{0.92F, 0.5F}) {
         int var19 = 0;

         while (var19 < Y.length) {
            long var49 = 3813111951812L;
            if (3813111951812L == 0L) {
               boolean var56 = false;
            } else {
               int var57 = var49 < 0L ? -1 : 1;
            }

            for (int var23 = 0; var23 < w.length; var23++) {
               float var24 = Math.abs(w[var23]);
               long var25 = 3813111951811L;
               if (3813111951811L == 0L) {
                  boolean var58 = false;
               } else {
                  int var59 = var25 < 0L ? -1 : 1;
               }

               float var27;
               int var28 = (var27 = var24 - 90.0F) == 0.0F ? 0 : (var27 < 0.0F ? -1 : 1);
               long var29 = 3813111951811L;
               if (3813111951811L == 0L) {
                  boolean var60 = false;
               } else {
                  int var61 = var29 < 0L ? -1 : 1;
               }

               if (var28 <= 0) {
                  float[] var31 = var14[var19];
                  long var32 = 3813111951812L;
                  if (3813111951812L == 0L) {
                     boolean var62 = false;
                  } else {
                     int var63 = var32 < 0L ? -1 : 1;
                  }

                  float var35;
                  int var36 = (var35 = var31[var23] - var18) == 0.0F ? 0 : (var35 < 0.0F ? -1 : 1);
                  long var37 = 3813111951812L;
                  if (3813111951812L == 0L) {
                     boolean var64 = false;
                  } else {
                     int var65 = var37 < 0L ? -1 : 1;
                  }

                  if (var36 >= 0) {
                     t = w[var23];
                     W = Y[var19];
                     return;
                  }
               }
            }

            var19++;
            long var52 = 3813111951811L;
            if (3813111951811L == 0L) {
               boolean var66 = false;
            } else {
               int var67 = var52 < 0L ? -1 : 1;
            }
         }

         long var50 = 3813111951812L;
         if (3813111951812L == 0L) {
            boolean var68 = false;
         } else {
            int var69 = var50 < 0L ? -1 : 1;
         }
      }

      int var40 = 0;
      int var43 = 0;
      int var45 = 0;

      while (var45 < Y.length) {
         long var46 = 3813111951811L;
         if (3813111951811L == 0L) {
            boolean var70 = false;
         } else {
            int var71 = var46 < 0L ? -1 : 1;
         }

         for (int var21 = 0; var21 < w.length; var21++) {
            if (var14[var45][var21] > var14[var40][var43] + 0.05F) {
               var40 = var45;
               var43 = var21;
            }
         }

         var45++;
         long var51 = 3813111951812L;
         if (3813111951812L == 0L) {
            boolean var72 = false;
         } else {
            int var73 = var51 < 0L ? -1 : 1;
         }
      }

      float var47 = w[var43];
      long var48 = 3813111951811L;
      if (3813111951811L == 0L) {
         boolean var74 = false;
      } else {
         int var75 = var48 < 0L ? -1 : 1;
      }

      t = var47;
      W = Y[var40];
   }

   private static class_243 l(class_243 var0, float var1, float var2, float var3, double var4, double var6) {
      class_243 var8 = class_243.method_1030(var2, var1 + 180.0F);
      class_243 var9 = var8.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
      class_243 var10 = var9.method_1036(var8);
      return var0.method_1020(var8.method_1021(var3)).method_1020(var9.method_1021(PJ * var3 * var4)).method_1020(var10.method_1021(E * var3 * var6));
   }

   private static float b(class_1937 var0, long var1, class_1297 var3, class_243 var4, class_243 var5) {
      class_243 var6 = var5.method_1020(var4);
      ShockwaveModule.ensureSourceClassInitialized();
      double var7 = var6.method_1033();
      if (var7 < 1.0E-4) {
         return 1.0F;
      } else {
         double var9 = var7;

         for (int var11 = 0; var11 < 5; var11++) {
            long var18 = 54075264689256L;
            if (54075264689256L == 0L) {
               boolean var10000 = false;
            } else {
               int var30 = var18 < 0L ? -1 : 1;
            }

            double var12 = var11 == 1 ? 0.1 : (var11 == 2 ? -0.1 : 0.0);
            long var21 = 54075264689257L;
            if (54075264689257L == 0L) {
               boolean var31 = false;
            } else {
               int var32 = var21 < 0L ? -1 : 1;
            }

            double var14 = var11 == 3 ? 0.1 : (var11 == 4 ? -0.1 : 0.0);
            long var23 = 54075264689257L;
            if (54075264689257L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var23 < 0L ? -1 : 1;
            }

            double var16;
            label94: {
               if (var11 != 1) {
                  long var25 = 54075264689256L;
                  if (54075264689256L == 0L) {
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
            var23 = 54075264689257L;
            if (54075264689257L == 0L) {
               boolean var37 = false;
            } else {
               int var38 = var23 < 0L ? -1 : 1;
            }

            if (var22.method_17783() != class_240.field_1333) {
               var9 = Math.min(var9, var22.method_17784().method_1022(var20) - 0.2);
            }
         }

         long var27 = 54075264689257L;
         if (54075264689257L == 0L) {
            boolean var39 = false;
         } else {
            int var40 = var27 < 0L ? -1 : 1;
         }

         return (float)Math.clamp(var9 / var7, Math.min(1.0, 0.7 / var7), 1.0);
      }
   }

   private static double r(class_310 var0) {
      return Math.max(0.2, (double)var0.method_22683().method_4489() / Math.max(1, var0.method_22683().method_4506()));
   }

   private static class_243 Y(class_243 var0, class_243 var1, class_243 var2, class_243 var3, float var4) {
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

   private static Easing F(float var0, float var1, float var2, float var3) {
      return Easing.bezier(var0, var1, var2, var3);
   }

   private static float i(float var0, long var1) {
      ShockwaveModule.ensureSourceClassInitialized();
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   static String describe() {
      return String.format(Locale.ROOT, ab, V, PY, y, Q, t, S, O, c, p, j[0], j[1], j[2], f, b, PJ, E, m);
   }

}
