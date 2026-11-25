package dev.purxiy.modules.shockwave;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.assets.IrisCompat;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleState;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3726;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

public final class ShockwaveRenderPass {
   static final int t = 48;
   static final int A = 64;
   private static final double V = 0.6;
   private static final double B = 6.0;
   private static final double s = 1.6666666666666667;
   private static final double E = 48.0;
   static final List<ShockwaveRing.ShockwaveWave> T = new ArrayList<>();
   private static final ShockwaveUpdater f = new ShockwaveUpdater();
   private static final Int2ObjectMap<ShockwaveRenderPass.ShockwaveBatch> I = new Int2ObjectOpenHashMap();
   private static class_638 O;
   static double a;
   private static long k = 0L;
   private static boolean M;
   static final Matrix4f p = new Matrix4f();
   static final Matrix4f L = new Matrix4f();
   static float Y;
   static double r;
   static double G;
   static double j;
   static GpuBufferSlice W;
   static boolean K;
   static int v = 0;
   static int d = 0;
   static int l = 0;
   static final float[] i = new float[4096];
   static final float[] D = new float[4096];
   static final float[] q = new float[4096];
   static final float[] S = new float[4096];
   private static final float[] X = new float[4096];
   static int F = 64;
   static int C = 64;
   static int H = -1;
   static int u = -1;
   static float N;
   static float e;
   static boolean R;
   private static double b = -1.0;
   private static boolean o;
   private static double m;
   private static float P = 3.0F;
   private static boolean w;
   private static String Z;
   static ShockwaveModule Q;
   private static ModuleContext c;
   private static int U = 0;
   private static int x = 0;

   private ShockwaveRenderPass() {
   }

   public static void onLand(class_1297 var0, double var1) {
      class_310 var3 = class_310.method_1551();
      if (var0 == var3.field_1724 && Q.enabled() && !var0.method_7325()) {
         land(var0.method_23317(), var0.method_23318(), var0.method_23321(), var1);
      }
   }

   public static void onStep(class_1297 var0) {
      class_310 var1 = class_310.method_1551();
      if (var0 == var1.field_1724 && var0.method_24828() && Q.enabled() && Q.E.enabled() && !var0.method_7325()) {
         step(var0.method_23317(), var0.method_23318(), var0.method_23321());
      }
   }

   public static void onBlast(class_243 var0, float var1) {
      long var2 = 126265755271533L;
      long var4 = var2 ^ 101063816155944L;
      long var6 = var2 ^ 18974708158291L;
      class_310 var8 = class_310.method_1551();
      ShockwaveModule var9 = Q;
      if (var9.enabled()
         && var9.q.enabled()
         && var8.field_1687 != null
         && var8.field_1724 != null
         && !(var8.field_1724.method_73189().method_1025(var0) > 2304.0)) {
         double var10 = K(var8.field_1687, var0, var1 + 2.0, var4);
         if (!Double.isNaN(var10)) {
            float var12 = Math.clamp(var1 / 4.0F, 0.5F, 1.6F);
            X(
               new ShockwaveRing.ShockwaveWave(
                  var0.field_1352, var0.field_1350, var10, a, var9.N.floatValue() * (5.0F + 5.0F * var12), var9.f.floatValue() * (0.2F + 0.09F * var12), R(), 2
               ),
               var6
            );
         }
      }
   }

   static void land(double var0, double var2, double var4, double var6) {
      long var8 = 58210036226034L;
      long var10 = var8 ^ 96097633098188L;
      if (!(var6 < 0.6)) {
         ShockwaveModule var12 = Q;
         float var13 = (float)Math.clamp((var6 - 0.6) / 5.4, 0.0, 1.0);
         float var14 = var12.N.floatValue() * (3.4F + 7.0F * var13);
         float var15 = var12.f.floatValue() * (0.16F + 0.14F * var13);
         X(new ShockwaveRing.ShockwaveWave(var0, var4, var2, a, var14, var15, R(), 0), var10);
      }
   }

   static void step(double var0, double var2, double var4) {
      long var6 = 5875024227018L;
      long var8 = var6 ^ 112977226999028L;
      X(new ShockwaveRing.ShockwaveWave(var0, var4, var2, a, 1.25F, Q.f.floatValue() * 0.07F, R(), 1), var8);
   }

   public static void previewLand(class_1309 var0, double var1) {
      land(var0.method_23317(), var0.method_23318(), var0.method_23321(), var1);
   }

   public static void previewStep(class_1309 var0, double var1, double var3) {
      step(var0.method_23317() + var1, var0.method_23318(), var0.method_23321() + var3);
   }

   public static void previewBlast(class_1309 var0, double var1, double var3) {
      long var5 = 379048407224L;
      long var7 = var5 ^ 109741460913798L;
      ShockwaveModule var9 = Q;
      X(
         new ShockwaveRing.ShockwaveWave(
            var0.method_23317() + var1, var0.method_23321() + var3, var0.method_23318(), a, var9.N.floatValue() * 10.0F, var9.f.floatValue() * 0.29F, R(), 2
         ),
         var7
      );
   }

   private static void X(ShockwaveRing.ShockwaveWave var0, long var1) {
      ShockwaveModule.ensureSourceClassInitialized();
      long var3 = 109502032563773L;
      int var5 = var3 < 0L ? -1 : 1;
      if (T.size() >= 48) {
         T.removeFirst();
      }

      T.add(var0);
   }

   private static float R() {
      return ThreadLocalRandom.current().nextFloat();
   }

   private static double K(class_638 var0, class_243 var1, double var2, long var4) {
      ShockwaveModule.ensureSourceClassInitialized();
      class_3965 var6 = var0.method_17742(
         new class_3959(var1, var1.method_1031(0.0, -var2, 0.0), class_3960.field_17558, class_242.field_1347, class_3726.method_16194())
      );
      long var7 = 45338910069317L;
      int var9 = var7 < 0L ? -1 : 1;
      return var6.method_17783() == class_240.field_1332 && var6.method_17780() == class_2350.field_11036 ? var6.method_17784().field_1351 : Double.NaN;
   }

   private static void e(class_310 var0, long var1, class_638 var3) {
      long var4 = 13241300583765L;
      ShockwaveModule.ensureSourceClassInitialized();
      ShockwaveModule var6 = Q;
      if (var6.L.enabled() && var0.field_1724 != null) {
         int var7 = 0;
         int var8 = 0;

         for (class_742 var10 : var3.method_18456()) {
            long var11 = 39439787178482L;
            int var131 = var11 < 0L ? -1 : 1;
            if (var10 != var0.field_1724) {
               o(var4, var0.field_1724, var10, var6.E.enabled());
               var7++;
            }
         }

         var8 = I.size();
         long var15 = 39439787178483L;
         int var16 = var15 < 0L ? -1 : 1;
         if (var8 > var7) {
            I.values().removeIf(var0x -> {
               ShockwaveModule.ensureSourceClassInitialized();
               return !var0x.r;
            });
         }

         ObjectIterator var12 = I.values().iterator();

         while (var12.hasNext()) {
            ShockwaveRenderPass.ShockwaveBatch var13 = (ShockwaveRenderPass.ShockwaveBatch)var12.next();
            var13.r = false;
         }
      } else {
         I.clear();
      }
   }

   private static void o(long var0, class_746 var2, class_1309 var3, boolean var4) {
      ShockwaveModule.ensureSourceClassInitialized();
      ShockwaveRenderPass.ShockwaveBatch var5 = (ShockwaveRenderPass.ShockwaveBatch)I.get(var3.method_5628());
      long var6 = 13241300583764L;
      int var9 = var6 < 0L ? -1 : 1;
      long var9x = 13241300583764L;
      int var11 = var9x < 0L ? -1 : 1;
      if (var5 == null) {
         I.put(var3.method_5628(), new ShockwaveRenderPass.ShockwaveBatch(var3));
      } else {
         var5.r = true;
         if (var3.field_6012 != var5.s) {
            var5.s = var3.field_6012;
            double var11x = var3.method_23317();
            double var13 = var3.method_23318();
            double var15 = var3.method_23321();
            boolean var17 = var3.method_24828();
            boolean var18 = var3.method_5756(var2)
               || var3.method_7325()
               || var3.method_5765()
               || var3.method_5799()
               || var3.method_6128()
               || var3.method_5858(var2) > 2304.0;
            long var19 = 13241300583764L;
            int var34 = var19 < 0L ? -1 : 1;
            if (!var17) {
               var5.I = var5.X ? var13 : Math.max(var5.I, var13);
               long var22 = 13241300583765L;
               int var22x = var22 < 0L ? -1 : 1;
               var5.O = 0.0;
            } else if (!var5.X) {
               if (!var18) {
                  land(var11x, var13, var15, var5.I - var13);
               }

               long var33 = 13241300583765L;
               int var36 = var33 < 0L ? -1 : 1;
               var5.O = 0.0;
            } else {
               double var34x = var11x - var5.k;
               double var24 = var15 - var5.w;
               long var26 = 13241300583764L;
               int var26x = var26 < 0L ? -1 : 1;
               var5.O = var5.O + Math.sqrt(var34x * var34x + var24 * var24);
               long var28x = 13241300583764L;
               if (var5.O > 1.6666666666666667) {
                  var5.O--;
                  long var30x = 13241300583764L;
                  if (var4 && !var18) {
                     step(var11x, var13, var15);
                  }
               }
            }

            var5.X = var17;
            var5.k = var11x;
            var5.w = var15;
         }
      }
   }

   public static void clear() {
      T.clear();
      I.clear();
      f.clear();
      R = false;
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2, GpuBufferSlice var3) {
      M = Q.enabled();
      if (M) {
         p.set(var2).mul(var1);
         p.invert(L);
         Y = var2.m11();
         class_243 var4 = var0.method_71156();
         r = var4.field_1352;
         G = var4.field_1351;
         j = var4.field_1350;
         W = var3;
         K = IrisCompat.inUse();
         if (K && !T.isEmpty()) {
            SkyPipelineRouter.wantHandMask();
         }
      }
   }

   public static void render(boolean var0) {
      long var1 = 52077973816275L;
      long var3 = var1 ^ 13759257016864L;
      long var5 = var1 ^ 61610061672661L;
      if (var0 == M(var5) && !ModuleWidget.claimed("mirror-capture")) {
         boolean var7 = M;
         M = false;
         class_310 var8 = class_310.method_1551();
         class_638 var9 = var8.field_1687;
         if (var9 != O) {
            O = var9;
            clear();
         }

         if (var7 && var9 != null) {
            long var10 = System.nanoTime();
            float var12 = k == 0L ? 0.0F : Math.min((float)(var10 - k) / 1.0E9F, 0.1F);
            k = var10;
            if (b < 0.0 && !var8.method_1493()) {
               a += var12;
            }

            e(var8, var3, var9);
            double var13 = a;
            T.removeIf(var2 -> {
               ShockwaveModule.ensureSourceClassInitialized();
               return !var2.alive(var13);
            });
            if (T.isEmpty()) {
               R = false;
            } else {
               f.tick(var10);

               try {
                  build(var9, var13);
                  if (R) {
                     ShockwaveRenderer.draw(var8);
                  }
               } catch (Throwable var17) {
                  metric("draw_failures");
                  String var16 = var17.toString();
                  if (!var16.equals(Z)) {
                     Z = var16;
                     ClientServices.LOGGER.error("SourceByDevFFnightmare DeObfDeCompByGuideX shockwave failed", var17);
                  }
               }
            }
         } else {
            if (!T.isEmpty() || !I.isEmpty()) {
               clear();
            }

            k = 0L;
         }
      }
   }

   private static boolean M(long var0) {
      return ModuleState.moduleActive("cyberspace");
   }

   static void build(class_638 var0, double var1) {
      int var3 = (int)Math.floor(r);
      int var4 = (int)Math.floor(j);
      v = var3 - 32;
      d = var4 - 32;
      l = (int)Math.floor(G);

      for (int var5 = C; var5 <= u; var5++) {
         int var6 = var5 * 64;
         Arrays.fill(i, var6 + F, var6 + H + 1, Float.NaN);
         Arrays.fill(D, var6 + F, var6 + H + 1, 0.0F);
         Arrays.fill(q, var6 + F, var6 + H + 1, 0.0F);
         Arrays.fill(S, var6 + F, var6 + H + 1, 0.0F);
         Arrays.fill(X, var6 + F, var6 + H + 1, 0.0F);
      }

      C = 64;
      F = 64;
      u = -1;
      H = -1;

      for (ShockwaveRing.ShockwaveWave var27 : T) {
         float var7 = var27.extent() + 1.0F;
         int var8 = Math.max((int)Math.floor(var27.J - var7) - v, 0);
         int var9 = Math.min((int)Math.floor(var27.J + var7) - v, 63);
         int var10 = Math.max((int)Math.floor(var27.H - var7) - d, 0);
         int var11 = Math.min((int)Math.floor(var27.H + var7) - d, 63);
         if (var8 <= var9 && var10 <= var11) {
            F = Math.min(F, var8);
            C = Math.min(C, var10);
            H = Math.max(H, var9);
            u = Math.max(u, var11);

            for (int var12 = var10; var12 <= var11; var12++) {
               int var13 = d + var12;

               for (int var14 = var8; var14 <= var9; var14++) {
                  int var15 = v + var14;
                  int var16 = var12 * 64 + var14;
                  float var17 = i[var16];
                  if (Float.isNaN(var17)) {
                     var17 = f.top(var0, var15, var13, var27.o);
                     if (Float.isNaN(var17)) {
                        continue;
                     }

                     i[var16] = var17;
                  }

                  double var18 = var15 + 0.5;
                  double var20 = var13 + 0.5;
                  float var22 = ShockwaveRing.sink(var27, var18, var20, var17, var1);
                  float var23 = ShockwaveRing.shine(var27, var18, var20, var17, var1);
                  D[var16] = D[var16] + var22;
                  q[var16] = q[var16] + var23;
                  float var24 = Math.abs(var22) + var23 * 0.05F;
                  if (var24 > X[var16]) {
                     X[var16] = var24;
                     S[var16] = ShockwaveRing.turn(var27, var18, var20);
                  }
               }
            }
         }
      }

      N = 0.0F;
      e = 0.0F;
      R = false;
      if (H >= F) {
         for (int var26 = C; var26 <= u; var26++) {
            for (int var28 = F; var28 <= H; var28++) {
               int var29 = var26 * 64 + var28;
               float var30 = Math.clamp(D[var29], -0.25F, 0.45F);
               D[var29] = var30;
               q[var29] = Math.min(q[var29], 1.0F);
               N = Math.max(N, var30);
               e = Math.max(e, -var30);
               if (Math.abs(var30) > 0.0015F || q[var29] > 0.01F) {
                  R = true;
               }
            }
         }
      }
   }

   public static void install(ShockwaveModule var0, ModuleContext var1) {
      Q = var0;
      c = var1;
   }

   public static void metric(String var0) {
      if (c != null) {
         c.metric(var0, var0.equals("drawn") ? ++U : ++x);
      }
   }

   public static void uninstall() {
      clear();
      O = null;
      M = false;
      Q = null;
      c = null;
      k = 0L;
   }

   private static final class ShockwaveBatch {
      double k;
      double w;
      double I;
      double O;
      boolean X;
      int s;
      boolean r = true;

      ShockwaveBatch(class_1309 var1) {
         this.k = var1.method_23317();
         this.w = var1.method_23321();
         this.I = var1.method_23318();
         this.X = var1.method_24828();
         this.s = var1.field_6012;
      }
   }
}
