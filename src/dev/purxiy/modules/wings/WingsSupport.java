// Sourced By DevFFNightmare
package dev.purxiy.modules.wings;

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

public final class WingsSupport {
   private static final float y = 1.55F;
   private static final float c = 1.1F;
   static final float Y = 46.0F;
   private static final float J = 0.47F;
   private static final float GF = 27.0F;
   private static final float S = 6.0F;
   private static final float[] t = new float[]{27.0F, -27.0F, 0.0F, 70.0F, -70.0F, 115.0F, -115.0F, 180.0F};
   private static final float[] l = new float[]{6.0F, 24.0F, 42.0F};
   private static final double i = 0.9;
   private static final double G = 0.7;
   private static final double U = 0.2;
   private static final float D = 0.42F;
   private static final float f = 0.5F;
   private static final float b = 0.55F;
   private static final float X = 1.9F;
   private static final float Ga = -28.0F;
   private static final float F = 62.0F;
   private static final Easing h = a(0.42F, 0.0F, 0.12F, 1.0F);
   private static final Easing A = a(0.45F, 0.0F, 0.25F, 1.0F);
   private static boolean u;
   private static boolean k;
   
   private static class_5498 q;
   private static float r;
   private static float p;
   private static float z;
   private static float O;
   private static float w;
   private static long Q = 0L;
   private static float I;
   private static float W;
   private static boolean V;
   private static boolean x;
   private static boolean G6;
   private static class_243 R;
   private static float H;
   private static float T;
   private static float m;
   private static float M;
   private static float s;
   private static float g;
   private static float o;
   private static float P;
   private static float d;
   private static boolean Gx;
   private static float v;
   private static float B;
   private static float E;
   private static float Gs;
   private static float K;
   private static float L;
   private static float j;
   private static float a;
   private static float GV;
   private static float n;
   private static final double[] C = new double[]{0.0, 0.0, 0.0};
   private static float Z;
   private static float N;
   private static boolean e;
   private static final String ab = "faced %.1f, engaged %s, wanted %s, path %.3f, side %.0f, orbit %.1f/%.1f, zoom %.2f, room %.2f, at %.2f %.2f %.2f yaw %.1f pitch %.1f, frame %.2f/%.2f, game fov %.1f";
   private static final long bb = 2927053605594726412L;

   private WingsSupport() {
   }

   public static boolean active() {
      return k;
   }

   public static float progress() {
      return r;
   }

   public static boolean settled() {
      return k && u && r >= 0.999F;
   }

   public static class_243 position() {
      return new class_243(C[0], C[1], C[2]);
   }

   static void open() {
      long var0 = 9508609096113L;
      long var2 = var0 ^ 127032661147902L;
      class_310 var4 = class_310.method_1551();
      class_746 var5 = var4.field_1724;
      if (var5 != null && var4.field_1687 != null) {
         if (!k) {
            q = var4.field_1690.method_31044();
            class_4184 var6 = var4.field_1773.method_19418();
            float var7 = var4.method_61966().method_60637(false);
            R = var6.method_71156().method_1020(var5.method_30950(var7));
            H = var6.method_19330();
            T = var6.method_19329();
            V = !q.method_31034() && R.method_37267() > 1.0;
            G6 = false;
            var4.field_1690.method_31043(class_5498.field_26665);
            k = true;
            r = 0.0F;
            m = var5.method_36454();
            var5.method_5636(m);
            var5.field_6220 = m;
            var5.method_5847(m);
            var5.field_6259 = m;
            Q(var4.field_1687, var2, var5);
            Gs = 0.0F;
            B = 0.0F;
            K = 0.0F;
            E = 0.0F;
            j = 1.0F;
            L = 1.0F;
            a = -1.0F;
            n = 1.0F;
            Gx = false;
            Q = 0L;
         }

         u = true;
         P(1.0F, 1.55F);
      }
   }

   static void close(boolean var0) {
      if (k) {
         if (u) {
            B = class_3532.method_15393(B);
            Gs = class_3532.method_15393(Gs);
            a = -1.0F;
         }

         u = false;
         P(0.0F, var0 ? 0.55F : 1.1F);
      }
   }

   static void hurry() {
      if (k && !u && !(W >= 0.0F)) {
         float var0 = w - O;
         float var1 = 0.3F * Math.max(0.35F, r);
         if (var0 > var1) {
            p = r;
            z = 0.0F;
            O = 0.0F;
            w = var1;
         }
      }
   }

   static void reset() {
      if (k) {
         class_310 var0 = class_310.method_1551();
         if (q != null && var0.field_1690.method_31044() == class_5498.field_26665) {
            var0.field_1690.method_31043(q);
         }

         q = null;
         k = false;
         u = false;
         r = 0.0F;
         W = -1.0F;
      }
   }

   private static void P(float var0, float var1) {
      p = r;
      z = var0;
      O = 0.0F;
      float var2 = Math.abs(var0 - r);
      w = var1 * Math.max(0.35F, var2) / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   private static void H(long var0) {
      long var2 = 41748073003246L;
      WingsUpdater2.ensureSourceClassInitialized();
      long var4 = System.nanoTime();
      float var6 = Q == 0L ? 0.0F : Math.min(0.1F, (float)(var4 - Q) / 1.0E9F);
      Q = var4;
      I = var6;
      float var7;
      int var8 = (var7 = W - 0.0F) == 0.0F ? 0 : (var7 < 0.0F ? -1 : 1);
      long var9 = 24746426754356L;
      if (24746426754356L == 0L) {
         boolean var10000 = false;
      } else {
         int var31 = var9 < 0L ? -1 : 1;
      }

      if (var8 >= 0) {
         long var12 = 24746426754356L;
         if (24746426754356L == 0L) {
            boolean var32 = false;
         } else {
            int var33 = var12 < 0L ? -1 : 1;
         }

         r = W;
      } else {
         long var28 = 24746426754357L;
         if (24746426754357L == 0L) {
            boolean var34 = false;
         } else {
            int var35 = var28 < 0L ? -1 : 1;
         }

         O = Math.min(w, O + var6);
         float var14 = w <= 0.0F ? 1.0F : O / w;
         r = p + (z - p) * (z > p ? h : A).apply(var14);
      }

      float var29 = 1.0F - (float)Math.exp(-var6 * 9.0);
      long var13 = 24746426754356L;
      if (24746426754356L == 0L) {
         boolean var36 = false;
      } else {
         int var37 = var13 < 0L ? -1 : 1;
      }

      B = B + (Gs - B) * var29;
      float var15 = E + (K - E) * var29;
      long var16 = 24746426754356L;
      if (24746426754356L == 0L) {
         boolean var38 = false;
      } else {
         int var39 = var16 < 0L ? -1 : 1;
      }

      E = var15;
      L = L + (j - L) * (1.0F - (float)Math.exp(-var6 * 7.0));
      float var18 = Gx ? 1.0F - (float)Math.exp(-var6 * 6.0) : 1.0F;
      long var19 = 24746426754357L;
      if (24746426754357L == 0L) {
         boolean var40 = false;
      } else {
         int var41 = var19 < 0L ? -1 : 1;
      }

      g = g + (P - g) * var18;
      o = o + (d - o) * var18;
      Gx = true;
      long var22 = 24746426754356L;
      if (24746426754356L == 0L) {
         boolean var42 = false;
      } else {
         int var43 = var22 < 0L ? -1 : 1;
      }

      if (a >= 0.0F) {
         a += var6;
         float var24;
         var8 = (var24 = a - GV) == 0.0F ? 0 : (var24 < 0.0F ? -1 : 1);
         long var25 = 24746426754356L;
         if (24746426754356L == 0L) {
            boolean var44 = false;
         } else {
            int var45 = var25 < 0L ? -1 : 1;
         }

         if (var8 >= 0) {
            a = -1.0F;
         }
      }

      if (!u) {
         long var30 = 24746426754357L;
         if (24746426754357L == 0L) {
            boolean var46 = false;
         } else {
            int var47 = var30 < 0L ? -1 : 1;
         }

         if (r <= 5.0E-4F && O >= w && W < 0.0F) {
            X(var2);
         }
      }
   }

   private static void X(long var0) {
      WingsUpdater2.ensureSourceClassInitialized();
      class_310 var2 = class_310.method_1551();
      if (q != null && var2.field_1690.method_31044() == class_5498.field_26665) {
         var2.field_1690.method_31043(q);
      }

      if (q == class_5498.field_26664) {
         var2.field_1773.field_4012.method_3215(class_1268.field_5808);
         var2.field_1773.field_4012.method_3215(class_1268.field_5810);
      }

      q = null;
      k = false;
   }

   static void frame(float var0, float var1) {
      P = var0;
      d = var1;
   }

   static void drag(float var0, float var1) {
      Gs -= var0 * 0.32F;
      K = class_3532.method_15363(K + var1 * 0.24F, -28.0F - s, 62.0F - s);
      a = -1.0F;
   }

   static void zoom(double var0) {
      j = class_3532.method_15363(j * (float)Math.pow(0.88, var0), 0.55F, 1.9F);
   }

   static void resetView() {
      Gs = Math.round(Gs / 360.0F) * 360.0F;
      K = 0.0F;
      j = 1.0F;
      a = -1.0F;
   }

   static boolean moved() {
      return Math.abs(class_3532.method_15393(Gs)) > 0.5F || Math.abs(K) > 0.5F || Math.abs(j - 1.0F) > 0.01F;
   }

   static void overlook(float var0, float var1) {
      K = class_3532.method_15363(var0, -28.0F - s, 62.0F - s);
      j = class_3532.method_15363(var1, 0.55F, 1.9F);
      a = -1.0F;
   }

   static void turntable(float var0) {
      Gs = B = class_3532.method_15393(B);
      a = 0.0F;
      GV = var0 / Math.max(0.1F, MotionStyle.MENU.speed());
   }

   static boolean turning() {
      return a >= 0.0F;
   }

   static void hold(float var0) {
      W = var0 < 0.0F ? -1.0F : Math.min(1.0F, var0);
      if (W < 0.0F) {
         p = r;
         O = 0.0F;
         w = 0.4F;
      }
   }

   public static float fov(float var0) {
      long var1 = 130386598147555L;
      long var3 = var1 ^ 60033478212085L;
      if (!k) {
         return var0;
      } else {
         v = var0;
         return class_3532.method_16439(x(var3, r), var0, L());
      }
   }

   private static float L() {
      double var0 = Math.tan(Math.toRadians(23.0)) / Math.max(0.42F, n);
      return (float)Math.toDegrees(2.0 * Math.atan(var0));
   }

   public static boolean hides(class_1297 var0) {
      class_310 var1 = class_310.method_1551();
      if (k && var0 == var1.field_1724 && e) {
         class_243 var2 = var0.method_5836(var1.method_61966().method_60637(false));
         double var3 = C[0] - var2.field_1352;
         double var5 = C[1] - var2.field_1351;
         double var7 = C[2] - var2.field_1350;
         double var9 = 0.9 * Math.max(0.05, var0.method_17682() / 1.8);
         return var3 * var3 + var5 * var5 + var7 * var7 < var9 * var9;
      } else {
         return false;
      }
   }

   public static boolean place(class_4184 var0, class_1937 var1, class_1297 var2, float var3, double[] var4, float[] var5) {
      long var6 = 94957398933281L;
      long var8 = var6 ^ 77897365980545L;
      long var10 = (var6 ^ 77492079364512L) >>> 16;
      int var12 = (int)((var6 ^ 77492079364512L) << 48 >>> 48);
      long var13 = var6 ^ 71319138982420L;
      long var15 = var6 ^ 20115332698749L;
      class_310 var17 = class_310.method_1551();
      if (!k) {
         return false;
      } else if (var0 != var17.field_1773.method_19418()) {
         return false;
      } else if (var17.field_1724 != null && var2 == var17.field_1724 && var1 == var17.field_1687) {
         H(var13);
         if (!k) {
            return false;
         } else {
            class_746 var18 = var17.field_1724;
            float var19 = r;
            class_243 var20 = var18.method_30950(var3);
            float var21 = var18.method_17682() * ClientSupport3.bodyHeight(var18);
            class_243 var22 = var20.method_1031(0.0, var21 * 0.53, 0.0);
            float var23 = Easing.SMOOTHSTEP.apply(k(var10, (short)var12, (var19 - 0.72F) / 0.28F));
            float var24 = a >= 0.0F ? 360.0F * Easing.SINE_IN_OUT.apply(a / GV) : 0.0F;
            float var25 = m + M + (B + var24) * var23;
            float var26 = s + E * var23;
            float var27 = (float)(var21 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0)))) * L;
            class_243 var28 = class_243.method_1030(var26, var25 + 180.0F);
            class_243 var29 = var28.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
            class_243 var30 = var29.method_1036(var28);
            double var31 = Math.tan(Math.toRadians(23.0));
            double var33 = var31 * V(var17);
            class_243 var35 = var22.method_1020(var28.method_1021(var27))
               .method_1020(var29.method_1021(g * var27 * var33))
               .method_1020(var30.method_1021(o * var27 * var31));
            float var36 = w(var1, var18, var22, var35, var8);
            n = var36 < n ? var36 : n + (var36 - n) * (1.0F - (float)Math.exp(-I * 4.0));
            float var37 = Math.max(0.42F, n);
            class_243 var38 = var22.method_1020(var28.method_1021(var27 * var37))
               .method_1020(var29.method_1021(g * var27 * var33))
               .method_1020(var30.method_1021(o * var27 * var31));
            if (var37 > n + 0.001F || n < 0.999F) {
               float var39 = w(var1, var18, var22, var38, var8);
               if (var39 < 1.0F) {
                  var38 = var22.method_1019(var38.method_1020(var22).method_1021(var39));
               }
            }

            float var56 = (B + var24) * var23;
            if (V) {
               class_243 var57 = var20.method_1019(R).method_1020(var22);
               class_243 var58 = var38.method_1020(var22);
               double var59 = Math.atan2(var57.field_1350, var57.field_1352);
               double var60 = Math.atan2(var58.field_1350, var58.field_1352);
               double var61 = class_3532.method_15338(Math.toDegrees(var60 - var59)) * (float) (Math.PI / 180.0);
               double var62 = var59 + var61 * var19;
               double var63 = class_3532.method_16436(var19, var57.method_37267(), var58.method_37267());
               class_243 var64 = var22.method_1031(
                  Math.cos(var62) * var63, class_3532.method_16436(var19, var57.field_1351, var58.field_1351), Math.sin(var62) * var63
               );
               float var65 = w(var1, var18, var22, var64, var8);
               if (var65 < 1.0F) {
                  var64 = var22.method_1019(var64.method_1020(var22).method_1021(var65));
               }

               return d(var64, H + (class_3532.method_15393(m + M + 180.0F - H) + var56) * var19, class_3532.method_16439(var19, T, var26), var4, var5);
            } else {
               class_243 var40 = var18.method_5836(var3);
               float var41 = var18.method_5705(var3);
               float var42 = var18.method_5695(var3);
               class_243 var43 = class_243.method_1030(0.0F, var41);
               double var44 = var38.method_1022(var40);
               class_243 var46 = var40.method_1019(var43.method_1021(var44 * 0.42)).method_1031(0.0, var44 * 0.06, 0.0);
               class_243 var47 = var38.method_1020(var22);
               class_243 var48 = var38.method_1020(var47.method_1027() < 1.0E-6 ? class_243.field_1353 : var47.method_1029().method_1021(var44 * 0.15));
               if (!G6 && u) {
                  G6 = true;
                  x = f(var1, var18, var40, var46, var15, var48, var38);
               }

               if (x) {
                  var46 = var22.method_1019(var47.method_1021(0.35));
                  var48 = var22.method_1019(var47.method_1021(0.7));
               }

               float var49 = 1.0F - (float)Math.pow(1.0F - var19, 2.2);
               class_243 var50 = w(var40, var46, var48, var38, var49);
               float var51 = w(var1, var18, var22, var50, var8);
               if (var51 < 1.0F) {
                  var50 = var22.method_1019(var50.method_1020(var22).method_1021(var51));
               }

               float var52 = Easing.SINE_IN_OUT.apply(k(var10, (short)var12, (var19 - 0.02F) / 0.86F));
               float var53 = class_3532.method_15393(m + M + 180.0F - var41);
               if (var53 > 0.0F) {
                  var53 -= 360.0F;
               }

               float var54 = var41 + (var53 + var56) * var52;
               float var55 = class_3532.method_16439(var52, var42, var26);
               return d(var50, var54, var55, var4, var5);
            }
         }
      } else {
         reset();
         return false;
      }
   }

   private static boolean d(class_243 var0, float var1, float var2, double[] var3, float[] var4) {
      C[0] = var0.field_1352;
      C[1] = var0.field_1351;
      C[2] = var0.field_1350;
      Z = var1;
      N = var2;
      e = true;
      var3[0] = var0.field_1352;
      var3[1] = var0.field_1351;
      var3[2] = var0.field_1350;
      var4[0] = var1;
      var4[1] = var2;
      return true;
   }

   private static boolean f(class_1937 var0, class_1297 var1, class_243 var2, class_243 var3, long var4, class_243 var6, class_243 var7) {
      WingsUpdater2.ensureSourceClassInitialized();
      class_243 var8 = var2;

      for (int var9 = 1; var9 <= (int)bb; var9++) {
         class_243 var10 = w(var2, var3, var6, var7, 1.0F - (float)Math.pow(1.0F - var9 / 12.0F, 2.2));
         long var11 = 74867878309211L;
         if (74867878309211L == 0L) {
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

   private static float x(long var0, float var2) {
      long var3 = 270957013L;
      short var5 = 13953;
      return Easing.SMOOTHSTEP.apply(k(var3, (short)var5, var2 * 1.15F));
   }

   private static void Q(class_1937 var0, long var1, class_746 var3) {
      long var4 = 18159572368032L;
      class_310 var6 = class_310.method_1551();
      float var7 = var3.method_17682() * ClientSupport3.bodyHeight(var3);
      WingsUpdater2.ensureSourceClassInitialized();
      class_243 var8 = var3.method_73189().method_1031(0.0, var7 * 0.53, 0.0);
      float var9 = (float)(var7 * 1.12F / 0.47F / (2.0 * Math.tan(Math.toRadians(23.0))));
      double var10 = Math.tan(Math.toRadians(23.0));
      double var12 = var10 * V(var6);
      float[][] var14 = new float[l.length][t.length];

      for (int var15 = 0; var15 < l.length; var15++) {
         for (int var16 = 0; var16 < t.length; var16++) {
            class_243 var17 = g(var8, var3.method_36454() + t[var16], l[var15], var9 * 1.1F, var12, var10);
            long var18 = 135433168157006L;
            if (135433168157006L == 0L) {
               boolean var10000 = false;
            } else {
               int var53 = var18 < 0L ? -1 : 1;
            }

            var14[var15][var16] = w(var0, var3, var8, var17, var4);
         }

         long var41 = 135433168157006L;
         if (135433168157006L == 0L) {
            boolean var54 = false;
         } else {
            int var55 = var41 < 0L ? -1 : 1;
         }
      }

      for (float var47 : new float[]{0.92F, 0.5F}) {
         for (int var19 = 0; var19 < l.length; var19++) {
            long var20 = 135433168157006L;
            if (135433168157006L == 0L) {
               boolean var56 = false;
            } else {
               int var57 = var20 < 0L ? -1 : 1;
            }

            for (int var23 = 0; var23 < t.length; var23++) {
               long var24 = 135433168157007L;
               if (135433168157007L == 0L) {
                  boolean var58 = false;
               } else {
                  int var59 = var24 < 0L ? -1 : 1;
               }

               long var26 = 135433168157007L;
               if (135433168157007L == 0L) {
                  boolean var60 = false;
               } else {
                  int var61 = var26 < 0L ? -1 : 1;
               }

               float var29;
               int var30 = (var29 = Math.abs(t[var23]) - 90.0F) == 0.0F ? 0 : (var29 < 0.0F ? -1 : 1);
               long var31 = 135433168157006L;
               if (135433168157006L == 0L) {
                  boolean var62 = false;
               } else {
                  int var63 = var31 < 0L ? -1 : 1;
               }

               if (var30 <= 0) {
                  float[] var33 = var14[var19];
                  long var34 = 135433168157007L;
                  if (135433168157007L == 0L) {
                     boolean var64 = false;
                  } else {
                     int var65 = var34 < 0L ? -1 : 1;
                  }

                  float var36;
                  var30 = (var36 = var33[var23] - var47) == 0.0F ? 0 : (var36 < 0.0F ? -1 : 1);
                  long var37 = 135433168157006L;
                  if (135433168157006L == 0L) {
                     boolean var66 = false;
                  } else {
                     int var67 = var37 < 0L ? -1 : 1;
                  }

                  if (var30 >= 0) {
                     M = t[var23];
                     s = l[var19];
                     return;
                  }
               }
            }

            long var51 = 135433168157007L;
            if (135433168157007L == 0L) {
               boolean var68 = false;
            } else {
               int var69 = var51 < 0L ? -1 : 1;
            }
         }

         long var49 = 135433168157006L;
         if (135433168157006L == 0L) {
            boolean var70 = false;
         } else {
            int var71 = var49 < 0L ? -1 : 1;
         }
      }

      int var40 = 0;
      int var43 = 0;

      for (int var45 = 0; var45 < l.length; var45++) {
         long var48 = 135433168157007L;
         if (135433168157007L == 0L) {
            boolean var72 = false;
         } else {
            int var73 = var48 < 0L ? -1 : 1;
         }

         for (int var21 = 0; var21 < t.length; var21++) {
            long var22 = 135433168157007L;
            if (135433168157007L == 0L) {
               boolean var74 = false;
            } else {
               int var75 = var22 < 0L ? -1 : 1;
            }

            if (var14[var45][var21] > var14[var40][var43] + 0.05F) {
               var40 = var45;
               var43 = var21;
            }
         }

         long var50 = 135433168157007L;
         if (135433168157007L == 0L) {
            boolean var76 = false;
         } else {
            int var77 = var50 < 0L ? -1 : 1;
         }
      }

      long var46 = 135433168157006L;
      if (135433168157006L == 0L) {
         boolean var78 = false;
      } else {
         int var79 = var46 < 0L ? -1 : 1;
      }

      M = t[var43];
      s = l[var40];
   }

   private static class_243 g(class_243 var0, float var1, float var2, float var3, double var4, double var6) {
      class_243 var8 = class_243.method_1030(var2, var1 + 180.0F);
      class_243 var9 = var8.method_1036(new class_243(0.0, 1.0, 0.0)).method_1029();
      class_243 var10 = var9.method_1036(var8);
      return var0.method_1020(var8.method_1021(var3)).method_1020(var9.method_1021(g * var3 * var4)).method_1020(var10.method_1021(o * var3 * var6));
   }

   private static float w(class_1937 var0, class_1297 var1, class_243 var2, class_243 var3, long var4) {
      class_243 var6 = var3.method_1020(var2);
      WingsUpdater2.ensureSourceClassInitialized();
      double var7 = var6.method_1033();
      if (var7 < 1.0E-4) {
         return 1.0F;
      } else {
         double var9 = var7;

         for (int var11 = 0; var11 < 5; var11++) {
            long var18 = 18159572368031L;
            if (18159572368031L == 0L) {
               boolean var10000 = false;
            } else {
               int var30 = var18 < 0L ? -1 : 1;
            }

            double var12 = var11 == 1 ? 0.1 : (var11 == 2 ? -0.1 : 0.0);
            long var21 = 18159572368032L;
            if (18159572368032L == 0L) {
               boolean var31 = false;
            } else {
               int var32 = var21 < 0L ? -1 : 1;
            }

            double var14 = var11 == 3 ? 0.1 : (var11 == 4 ? -0.1 : 0.0);
            long var23 = 18159572368031L;
            if (18159572368031L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var23 < 0L ? -1 : 1;
            }

            double var16;
            label94: {
               if (var11 != 1) {
                  long var25 = 18159572368031L;
                  if (18159572368031L == 0L) {
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
            class_243 var28 = var3.method_1031(var12, var14, var16);
            class_3965 var22 = var0.method_17742(new class_3959(var20, var28, class_3960.field_23142, class_242.field_1348, var1));
            var23 = 18159572368031L;
            if (18159572368031L == 0L) {
               boolean var37 = false;
            } else {
               int var38 = var23 < 0L ? -1 : 1;
            }

            if (var22.method_17783() != class_240.field_1333) {
               var9 = Math.min(var9, var22.method_17784().method_1022(var20) - 0.2);
            }
         }

         long var27 = 18159572368031L;
         if (18159572368031L == 0L) {
            boolean var39 = false;
         } else {
            int var40 = var27 < 0L ? -1 : 1;
         }

         return (float)Math.clamp(var9 / var7, Math.min(1.0, 0.7 / var7), 1.0);
      }
   }

   private static double V(class_310 var0) {
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

   private static Easing a(float var0, float var1, float var2, float var3) {
      return Easing.bezier(var0, var1, var2, var3);
   }

   private static float k(long var0, short var2, float var3) {
      WingsUpdater2.ensureSourceClassInitialized();
      return var3 < 0.0F ? 0.0F : Math.min(var3, 1.0F);
   }

   static String describe() {
      return String.format(Locale.ROOT, ab, m, k, u, r, M, B, E, L, n, C[0], C[1], C[2], Z, N, g, o, v);
   }

}
