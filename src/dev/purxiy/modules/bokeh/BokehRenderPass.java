package dev.purxiy.modules.bokeh;

import dev.purxiy.assets.IrisCompat;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3726;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_4184;
import net.minecraft.class_5636;
import net.minecraft.class_638;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;

public final class BokehRenderPass {
   private static final double C = 96.0;
   private static final float e = 0.016F;
   static final Matrix4f r = new Matrix4f();
   static final Vector3f O = new Vector3f(0.0F, 0.0F, -1.0F);
   private static final Vector3f s = new Vector3f();
   private static final Vector3f o = new Vector3f();
   static float l = 1.428F;
   private static double w;
   private static double Q;
   private static double J;
   private static boolean G;
   private static boolean W;
   private static class_1297 U;
   private static float u;
   private static boolean A;
   static boolean z;
   private static final BokehUpdater f = new BokehUpdater();
   static float x;
   static float D;
   static float d;
   static float T = 1.0F;
   static float p = 0.5F;
   static float R = 0.6F;
   static float L = 0.45F;
   static float h;
   static float b = 0.26F;
   static int Z = 0;
   static int i = 0;
   static float E;
   static float q = Float.POSITIVE_INFINITY;
   static float B;
   static double k;
   private static long a = 0L;
   private static boolean g;
   private static float j = -1.0F;
   private static boolean P;
   static int X = 0;
   private static String n;
   private static final float[] F = new float[5];
   static BokehModule M;
   private static ModuleContext V;
   private static int m = 0;
   private static int c = 0;
   public static int K = 0;

   private BokehRenderPass() {
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2) {
      if (!ModuleWidget.claimed("mirror-capture")) {
         A = x > 0.0F || M.enabled();
         if (A) {
            Matrix4f var3 = new Matrix4f(var2).mul(var1);
            var3.invert(r);
            Vector3fc var4 = var0.method_19335();
            O.set(var4).normalize();
            s.set(var0.method_35689()).normalize();
            o.set(var0.method_19336()).normalize();
            l = var2.m11();
            class_243 var5 = var0.method_71156();
            w = var5.field_1352;
            Q = var5.field_1351;
            J = var5.field_1350;
            G = var0.method_19333();
            U = var0.method_19331();
            W = var0.method_19334() != class_5636.field_27888;
            u = var0.method_55437();
            z = IrisCompat.inUse();
            if (z) {
               SkyPipelineRouter.wantHandMask();
            }
         }
      }
   }

   public static void render() {
      long var0 = 18845188650915L;
      long var2 = var0 ^ 66394574836673L;
      long var4 = var0 ^ 331892621796L;
      boolean var6 = A;
      A = false;
      class_310 var7 = class_310.method_1551();
      long var8 = System.nanoTime();
      float var10 = a == 0L ? 0.0F : Math.min((float)(var8 - a) / 1.0E9F, 0.1F);
      a = var8;
      class_638 var11 = var7.field_1687;
      if (var6 && var11 != null) {
         k += var10;
         m(var7, var11, var2, var10);
         N(var4, var10);
         if (!(x <= 0.001F)) {
            try {
               BokehRenderer.draw(var7);
            } catch (Throwable var14) {
               metric("draw_failures");
               String var13 = var14.toString();
               if (!var13.equals(n)) {
                  n = var13;
                  ClientServices.LOGGER.error("SourceByDevFFnightmare DeObfDeCompByGuideX bokeh failed", var14);
               }
            }
         }
      } else {
         a = 0L;
         if (!M.enabled()) {
            x = 0.0F;
         }
      }
   }

   private static void m(class_310 var0, class_638 var1, long var2, float var4) {
      long var5 = 93906258401704L;
      BokehModule var7 = M;
      float var8;
      if (j > 0.0F) {
         var8 = BokehUpdater.dioptre(j);
         q = j;
      } else if (var7.T.is("Distance")) {
         float var9 = var7.y.floatValue();
         long var10 = 49757427475553L;
         int var12 = var10 < 0L ? -1 : 1;
         var8 = BokehUpdater.dioptre(var9);
         q = var7.y.floatValue();
      } else {
         q = d(var5, var1);
         var8 = BokehUpdater.dioptre(q);
      }

      float var15;
      int var16 = (var15 = x - 0.0F) == 0.0F ? 0 : (var15 < 0.0F ? -1 : 1);
      long var11 = 49757427475553L;
      int var14 = var11 < 0L ? -1 : 1;
      if (var16 <= 0) {
         B = f.snap(var8);
      } else {
         float var14x = var7.E.floatValue();
         B = f.update(var8, var7.T.is("Auto") ? var14x : 1.0F, var4);
      }
   }

   private static float d(long var0, class_638 var2) {
      long var3 = 43973734601076L;
      long var5 = 56504077658651L;
      class_1297 var7 = U;
      if (G && var7 != null) {
         class_243 var19 = var7.method_30950(u).method_1031(0.0, var7.method_17682() * 0.6, 0.0);
         return (float)Math.max(f(var19), 0.3F);
      } else {
         class_243 var8 = new class_243(w, Q, J);
         float var9 = C(var3, var2, var8, var7);
         if (var9 < Float.POSITIVE_INFINITY) {
            return var9;
         } else {
            for (int var10 = 0; var10 < 5; var10++) {
               long var11 = 93906258401703L;
               int var14 = var11 < 0L ? -1 : 1;
               float var14x = var10 == 1 ? 0.016F : (var10 == 2 ? -0.016F : 0.0F);
               long var15 = 93906258401704L;
               int var17 = var15 < 0L ? -1 : 1;
               float var17x = var10 == 3 ? 0.016F : (var10 == 4 ? -0.016F : 0.0F);
               class_243 var18 = new class_243(O.x + s.x * var14x + o.x * var17x, O.y + s.y * var14x + o.y * var17x, O.z + s.z * var14x + o.z * var17x)
                  .method_1029();
               F[var10] = BokehUpdater.dioptre(s(var5, var2, var8, var18, var7));
            }

            long var20 = 93906258401703L;
            int var13 = var20 < 0L ? -1 : 1;
            float var13x = BokehUpdater.median(F);
            return var13x <= 0.0F ? Float.POSITIVE_INFINITY : 1.0F / var13x;
         }
      }
   }

   private static float s(long var0, class_638 var2, class_243 var3, class_243 var4, class_1297 var5) {
      class_243 var6 = var3.method_1019(var4.method_1021(96.0));
      class_242 var7 = W ? class_242.field_1348 : class_242.field_1347;
      class_3965 var8 = var5 != null
         ? var2.method_17742(new class_3959(var3, var6, class_3960.field_17559, var7, var5))
         : var2.method_17742(new class_3959(var3, var6, class_3960.field_17559, var7, class_3726.method_16194()));
      return var8.method_17783() != class_240.field_1332 ? Float.POSITIVE_INFINITY : (float)Math.max(f(var8.method_17784()), 0.3F);
   }

   private static float C(long var0, class_638 var2, class_243 var3, class_1297 var4) {
      long var5 = 56504077658651L;
      double var7 = 64.0;
      class_243 var9 = new class_243(O.x, O.y, O.z);
      class_243 var10 = var3.method_1019(var9.method_1021(var7));
      class_238 var11 = new class_238(var3, var10).method_1014(1.0);
      class_1297 var12 = var4 == null ? null : var4.method_5668();
      List<class_1297>var13 = var2.method_8333(var4, var11, var1 -> {
         long var2x = 9847778016325L;
         int var5x = var2x < 0L ? -1 : 1;
         if (!var1.method_7325()) {
            long var5xx = 9847778016325L;
            int var7x = var5xx < 0L ? -1 : 1;
            if (var1.method_5863()) {
               if (var1.method_5767()) {
                  long var7xx = 9847778016326L;
                  int var8 = var7xx < 0L ? -1 : 1;
                  if (!var1.method_5851()) {
                     return false;
                  }
               }

               if (var12 == null || var1.method_5668() != var12) {
                  return true;
               }
            }
         }

         return false;
      });
      double var14 = Double.POSITIVE_INFINITY;

      for (class_1297 var17 : var13) {
         class_238 var18 = var17.method_5829().method_1014(Math.max((double)var17.method_5871(), 0.05));
         boolean var19 = var18.method_1006(var3);
         long var20 = 43973734601076L;
         int var30 = var20 < 0L ? -1 : 1;
         if (var19) {
            long var23 = 43973734601075L;
            int var241 = var23 < 0L ? -1 : 1;
         } else {
            Optional var30x = var18.method_992(var3, var10);
            var19 = var30x.isPresent();
            long var24 = 43973734601075L;
            int var25 = var24 < 0L ? -1 : 1;
            if (var19) {
               var14 = Math.min(var14, f((class_243)var30x.get()));
            }
         }
      }

      long var26 = 43973734601076L;
      int var27 = var26 < 0L ? -1 : 1;
      if (var14 == Double.POSITIVE_INFINITY) {
         return Float.POSITIVE_INFINITY;
      } else {
         float var28 = s(var5, var2, var3, var9, var4);
         double var29 = var28;
         long var22 = 43973734601075L;
         int var33 = var22 < 0L ? -1 : 1;
         return var14 < var29 ? (float)Math.max(var14, 0.3F) : Float.POSITIVE_INFINITY;
      }
   }

   private static double f(class_243 var0) {
      return (var0.field_1352 - w) * O.x + (var0.field_1351 - Q) * O.y + (var0.field_1350 - J) * O.z;
   }

   private static void N(long var0, float var2) {
      BokehModule var3 = M;
      float var4 = g ? 1.0F - (float)Math.exp(-var2 * 6.0) : 1.0F;
      g = true;
      float var5 = var3.enabled() ? 1.0F : 0.0F;
      float var6 = var2 / 0.75F;
      long var7 = 19168347944518L;
      int var10 = var7 < 0L ? -1 : 1;
      x = var5 > x ? Math.min(var5, x + var6) : Math.max(var5, x - var6);
      float var10x = D;
      float var11 = var3.T.is("Tilt-shift") ? 1.0F : 0.0F;
      long var12 = 19168347944518L;
      int var15 = var12 < 0L ? -1 : 1;
      D = var10x + (var11 - D) * var4;
      d = d + ((var3.T.is("Distance") ? 1.0F : 0.0F) - d) * var4;
      long var15x = 19168347944518L;
      T = T + ((var3.T.is("Auto") && var3.D.get() ? 1.0F : 0.0F) - T) * var4;
      long var17x = 19168347944519L;
      int var19 = var17x < 0L ? -1 : 1;
      p = p + (var3.K.floatValue() - p) * var4;
      R = R + (var3.h.floatValue() - R) * var4;
      L = L + (var3.b.floatValue() - L) * var4;
      h = h + ((var3.Q.get() ? 1.0F : 0.0F) - h) * var4;
      b = b + (var3.L.floatValue() - b) * var4;
      var19 = Math.clamp((long)var3.t.index(), 0, BokehKind.values().length - 1);
      long var20 = 19168347944518L;
      int var20x = var20 < 0L ? -1 : 1;
      long var23x = 19168347944518L;
      int var22 = var23x < 0L ? -1 : 1;
      if (var19 != i) {
         Z = E < 0.5F ? Z : i;
         i = var19;
         E = Z == i ? 1.0F : 0.0F;
      }

      E = Math.min(1.0F, E + var2 / 0.35F);
      if (E >= 1.0F) {
         Z = i;
      }
   }

   static float presenceCurve() {
      float var0 = Math.clamp(x, 0.0F, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   public static void install(BokehModule var0, ModuleContext var1) {
      M = var0;
      V = var1;
   }

   public static void metric(String var0) {
      if (V != null) {
         V.metric(var0, var0.equals("drawn") ? ++m : ++c);
      }
   }

   public static void uninstall() {
      A = false;
      x = 0.0F;
      M = null;
      V = null;
      a = 0L;
      g = false;
   }

   public static void ensureSourceClassInitialized() {
   }
}
