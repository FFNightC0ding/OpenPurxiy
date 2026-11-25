package dev.purxiy.modules.cyberspace;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.assets.IrisCompat;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.mixin.OutlineMiningAccess;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_12076;
import net.minecraft.class_2338;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3965;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_239.class_240;
import net.minecraft.class_2874.class_12326;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector4f;

public final class CyberspaceRenderPass {
   static final int t = 4;
   private static final float h = 18.0F;
   private static final float A = 1.5F;
   private static final float T = 26.0F;
   static final float ES = 72.0F;
   private static final float V = 0.42F;
   static final Matrix4f e = new Matrix4f();
   static final Matrix4f y = new Matrix4f();
   static final Vector3f N = new Vector3f();
   static final Vector3f O = new Vector3f();
   static float C;
   private static final Vector4f En = new Vector4f();
   static float EH;
   static double b;
   static double B;
   static double v;
   static GpuBufferSlice W;
   private static boolean Y;
   static boolean w;
   static float P;
   static float l;
   static boolean r = true;
   static double X;
   private static long Q = 0L;
   private static boolean s;
   static boolean c;
   static double x;
   static double p;
   static double j;
   static float U;
   static float d;
   static float F = 256.0F;
   static final double[] M = new double[16];
   private static double g;
   static float z;
   private static int L = 0;
   static boolean i;
   static int f = 0;
   static int H = 0;
   static int EP = 0;
   static float n;
   static float D;
   static float q = 0.15F;
   static float R = 0.7F;
   static float k = 1.0F;
   static float E = 1.0F;
   static float S = 0.0F;
   static float K = 1.0F;
   private static boolean a;
   private static class_638 o;
   private static String u;
   static CyberspaceModule I;
   private static ModuleContext J;
   private static int Z = 0;
   private static int m = 0;
   public static int G = 0;

   private CyberspaceRenderPass() {
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2, GpuBufferSlice var3) {
      if (!ModuleWidget.claimed("mirror-capture")) {
         Y = c || I.enabled();
         if (Y) {
            e.set(var2).mul(var1);
            e.invert(y);
            var1.getRow(0, En);
            N.set(En.x, En.y, En.z).normalize();
            var1.getRow(1, En);
            O.set(En.x, En.y, En.z).normalize();
            var1.getRow(2, En);
            C = (float)Math.atan2(-En.z, -En.x);
            EH = var2.m11();
            class_243 var4 = var0.method_71156();
            b = var4.field_1352;
            B = var4.field_1351;
            v = var4.field_1350;
            W = var3;
            w = IrisCompat.inUse();
            if (w) {
               SkyPipelineRouter.wantHandMask();
            }
         }
      }
   }

   public static boolean hidesClouds() {
      return c && !ModuleWidget.claimed("mirror-capture") && Math.min(U, F) > 0.6F * F && d < 0.6F * F;
   }

   public static void sky(class_12076 var0) {
      if (!ModuleWidget.claimed("mirror-capture")) {
         P = var0.field_63091;
         l = var0.field_64465;
         r = var0.field_64464 == class_12326.field_64386;
      }
   }

   public static void render() {
      long var0 = 120079520442452L;
      long var2 = var0 ^ 37062573480683L;
      long var4 = var0 ^ 120477983914942L;
      long var6 = var0 ^ 33329741386208L;
      long var8 = var0 ^ 112179897968310L;
      long var10 = var0 ^ 8526836037560L;
      long var12 = var0 ^ 133986659676187L;
      boolean var14 = Y;
      Y = false;
      class_310 var15 = class_310.method_1551();
      class_638 var16 = var15.field_1687;
      if (var16 != o) {
         o = var16;
         CyberspaceUpdater.clear();
         if (c && I.enabled()) {
            U = Float.MAX_VALUE;
            d = 0.0F;
         }

         y(var4);
      }

      long var17 = System.nanoTime();
      float var19 = Q == 0L ? 0.0F : Math.min((float)(var17 - Q) / 1.0E9F, 0.1F);
      Q = var17;
      if (var14 && var16 != null) {
         F = var15.field_1690.method_38521() * 16.0F + 48.0F;
         if (!var15.method_1493()) {
            X += var19;
            w(var19, var2);
            Z(var6, var19);
            CyberspaceModule var20 = I;
            boolean var21 = c && var20.enabled() && (Boolean)var20.R.get();
            CyberspaceUpdater.update(var16, b, B, v, C, var19, var21);
         }

         g(var8, var15.field_1724, var19);
         N(var10, var15);
         R(var12, var19);
         if (!c) {
            CyberspaceUpdater.clear();
         } else {
            try {
               CyberspaceRenderer.draw(var15);
            } catch (Throwable var221) {
               metric("draw_failures");
               String var23 = var221.toString();
               if (!var23.equals(u)) {
                  u = var23;
                  ClientServices.LOGGER.error("SourceByDevFFnightmare DeObfDeCompByGuideX cyberspace failed", var221);
               }
            }
         }
      } else {
         if (!I.enabled()) {
            c = false;
         }

         Q = 0L;
      }
   }

   private static void w(float var0, long var1) {
      boolean var3 = I.enabled();
      long var4 = 84127616332479L;
      int var15 = var4 < 0L ? -1 : 1;
      if (var3) {
         if (!c) {
            c = true;
            U();
            U = 0.0F;
            d = 0.0F;
            z = Math.max(z, 0.22F);
         } else {
            long var7 = 84127616332478L;
            int var9 = var7 < 0L ? -1 : 1;
            if (d > 0.0F) {
               long var10 = 84127616332478L;
               int var10x = var10 < 0L ? -1 : 1;
               d = e(d, var0);
            } else if (U < Float.MAX_VALUE) {
               long var16 = 84127616332479L;
               int var21 = var16 < 0L ? -1 : 1;
               U = B(U, var0);
               if (U >= F) {
                  U = Float.MAX_VALUE;
               }
            }
         }
      } else if (c) {
         if (U < F) {
            U = e(U, var0);
            long var14 = 84127616332479L;
            int var18 = var14 < 0L ? -1 : 1;
            float var17;
            int var11 = (var17 = U - 0.0F) == 0.0F ? 0 : (var17 < 0.0F ? -1 : 1);
            long var12 = 84127616332478L;
            int var12x = var12 < 0L ? -1 : 1;
            if (var11 <= 0) {
               c = false;
            }
         } else {
            if (d <= 0.0F) {
               U();
            }

            d = B(d, var0);
            float var15x;
            int var8 = (var15x = d - F) == 0.0F ? 0 : (var15x < 0.0F ? -1 : 1);
            long var9 = 84127616332479L;
            int var23 = var9 < 0L ? -1 : 1;
            if (var8 >= 0) {
               c = false;
               d = 0.0F;
            }
         }
      }
   }

   private static float B(float var0, float var1) {
      return var0 + (18.0F + 1.5F * var0) * var1;
   }

   private static float e(float var0, float var1) {
      float var2 = Math.min(var0, F);
      return Math.max(0.0F, var2 - (18.0F + 1.5F * var2) * var1);
   }

   private static void U() {
      x = b;
      p = B;
      j = v;
   }

   private static void Z(long var0, float var2) {
      CyberspaceModule var3 = I;

      for (int var4 = 0; var4 < 4; var4++) {
         int var5 = var4 * 4;
         long var6 = 126882432186804L;
         int var9 = var6 < 0L ? -1 : 1;
         var5 += 3;
         long var9x = 126882432186804L;
         int var11 = var9x < 0L ? -1 : 1;
         if (M[var5] >= 0.0) {
            M[var4 * 4 + 3] = M[var4 * 4 + 3] + 26.0F * var2;
            long var11x = 126882432186803L;
            int var12 = var11x < 0L ? -1 : 1;
            if (M[var4 * 4 + 3] > 72.0) {
               M[var4 * 4 + 3] = -1.0;
            }
         }
      }

      long var16 = 126882432186803L;
      int var16x = var16 < 0L ? -1 : 1;
      boolean var8 = var3.enabled() && d <= 0.0F && U >= F;
      long var17 = 126882432186803L;
      int var18 = var17 < 0L ? -1 : 1;
      long var18x = 126882432186804L;
      int var13 = var18x < 0L ? -1 : 1;
      if (var8 && (Boolean)var3.X.get()) {
         long var13x = 126882432186803L;
         g += var2;
         if (g >= (Double)var3.P.get()) {
            g = 0.0;
            scan(b, B - 1.2, v);
         }
      } else {
         g = 0.0;
      }
   }

   static void scan(double var0, double var2, double var4) {
      int var6 = 0;
      double var7 = -2.0;

      for (int var9 = 0; var9 < 4; var9++) {
         double var10 = M[var9 * 4 + 3];
         if (var10 < 0.0) {
            var6 = var9;
            break;
         }

         if (var10 > var7) {
            var7 = var10;
            var6 = var9;
         }
      }

      M[var6 * 4] = var0;
      M[var6 * 4 + 1] = var2;
      M[var6 * 4 + 2] = var4;
      M[var6 * 4 + 3] = 0.0;
      class_638 var12 = class_310.method_1551().field_1687;
      if (var12 != null && (Boolean)I.R.get()) {
         CyberspaceUpdater.burst(var12, var0, var2 + 0.6, var4);
      }
   }

   private static void y(long var0) {
      int var2 = 0;

      while (var2 < 4) {
         long var3 = 707701117930L;
         int var6 = var3 < 0L ? -1 : 1;
         M[var2 * 4 + 3] = -1.0;
         var2++;
         long var6x = 707701117930L;
         int var7 = var6x < 0L ? -1 : 1;
      }

      g = 0.0;
   }

   private static void g(long var0, class_746 var2, float var3) {
      z = Math.max(0.0F, z - var3 / 0.42F);
      int var4 = var2 == null ? 0 : var2.field_6235;
      long var5 = 12304474246881L;
      class_2338 var8;
      long var8x = 12304474246882L;
      int var9 = var8x < 0L ? -1 : 1;
      if (var4 > L && I.enabled() && (Boolean)I.b.get()) {
         z = 1.0F;
      }

      L = var4;
   }

   private static void N(long var0, class_310 var2) {
      class_239 var3 = var2.field_1765;
      boolean var4 = i;
      i = var3 instanceof class_3965 var5 && var3.method_17783() == class_240.field_1332 && !var2.field_1690.field_1842;
      long var19 = 117611205582827L;
      class_2338 var8;
      if (!i) {
         n = 0.0F;
      } else {
         label51: {
            var8 = ((class_3965)var3).method_17777();
            long var9 = 117611205582827L;
            int var20 = var9 < 0L ? -1 : 1;
            if (var4) {
               long var12 = 117611205582828L;
               int var12x = var12 < 0L ? -1 : 1;
               if (var8.method_10263() == f) {
                  long var14x = 117611205582828L;
                  int var16 = var14x < 0L ? -1 : 1;
                  if (var8.method_10264() == H) {
                     var16 = var8.method_10260();
                     long var17 = 117611205582827L;
                     int var17x = var17 < 0L ? -1 : 1;
                     if (var16 == EP) {
                        break label51;
                     }
                  }
               }
            }

            D = 0.0F;
         }

         f = var8.method_10263();
         H = var8.method_10264();
         EP = var8.method_10260();
         n = 0.0F;
         if (var2.field_1761 != null) {
            OutlineMiningAccess var20 = (OutlineMiningAccess)var2.field_1761;
            if (var8.equals(var20.purxiy$destroyBlockPos())) {
               n = Math.max(0.0F, Math.min(var20.purxiy$destroyProgress(), 1.0F));
            }
         }
      }
   }

   private static void R(long var0, float var2) {
      CyberspaceModule var3 = I;
      float var4 = a ? 1.0F - (float)Math.exp(-var2 * 6.0) : 1.0F;
      a = true;
      long var5 = 22995295279182L;
      q = q + (var3.j.floatValue() - q) * var4;
      R = R + (var3.S.floatValue() - R) * var4;
      float var8x = k;
      float var9 = var3.L.get() ? 1.0F : 0.0F;
      long var10 = 22995295279183L;
      int var13 = var10 < 0L ? -1 : 1;
      k = var8x + (var9 - k) * var4;
      float var17 = E;
      var9 = var3.e.get() ? 1.0F : 0.0F;
      long var13x = 22995295279182L;
      E = var17 + (var9 - E) * var4;
      float var18 = S;
      var9 = var3.H.is("Porcelain") ? 1.0F : 0.0F;
      long var15x = 22995295279183L;
      S = var18 + (var9 - S) * var4;
      K = K + ((var3.R.get() ? 1.0F : 0.0F) - K) * var4;
      D += var2;
   }

   public static void install(CyberspaceModule var0, ModuleContext var1) {
      I = var0;
      J = var1;
   }

   public static void metric(String var0) {
      if (J != null) {
         J.metric(var0, var0.equals("drawn") ? ++Z : ++m);
      }
   }

   public static void uninstall() {
      long var0 = 73610797078678L;
      long var2 = var0 ^ 72937657813884L;
      CyberspaceUpdater.clear();
      y(var2);
      o = null;
      Y = false;
      c = false;
      I = null;
      J = null;
      Q = 0L;
      a = false;
   }

   public static void ensureSourceClassInitialized() {
   }
}
