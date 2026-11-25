package dev.purxiy.modules.halo;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientBuilder;
import net.minecraft.class_10055;
import net.minecraft.class_11659;
import net.minecraft.class_243;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4050;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_591;
import net.minecraft.class_746;
import net.minecraft.class_8685;
import org.joml.Matrix4fc;

public final class HaloSceneRenderer {
   private static final HaloUpdater J = new HaloUpdater();
   private static final HaloMeasure j = new HaloMeasure();
   private static final HaloBuilder f = new HaloBuilder();
   private static final HaloBatch d = new HaloBatch();
   private static final HaloPipeline O = new HaloPipeline();
   private static final HaloBuilder.HaloBatchState c = new HaloBuilder.HaloBatchState();
   private static final HaloBuilder.HaloSegments F = new HaloBuilder.HaloSegments();
   private static final HaloGeometry.HaloGeometryData m = new HaloGeometry.HaloGeometryData();
   private static final HaloUpdater n = new HaloUpdater();
   private static final HaloMeasure E = new HaloMeasure();
   private static final HaloBuilder x = new HaloBuilder();
   private static final HaloBatch v = new HaloBatch();
   private static final HaloBuilder.HaloBatchState I = new HaloBuilder.HaloBatchState();
   private static final HaloBuilder.HaloSegments G = new HaloBuilder.HaloSegments();
   private static long X = 0L;
   private static final float P = 2.2F;
   private static boolean r;
   private static boolean o;
   private static boolean R;
   private static double S;
   private static double b;
   private static double k;
   private static float g;
   private static GpuBufferSlice Z;
   private static long K = 0L;
   private static class_2960 q;
   private static double t;
   private static boolean l;
   private static HaloDesign H;
   private static long a = 0L;
   private static HaloDesign p;
   private static boolean B;
   static HaloModule W;
   private static ModuleContext A;
   private static int D = 0;
   private static int U = 0;
   public static boolean Y;
   private static final String[] h = new String[4];
   private static final long i = 250000000L;

   private HaloSceneRenderer() {
   }

   public static HaloModule module() {
      return W;
   }

   public static boolean wears(class_10055 var0) {
      class_746 var1 = class_310.method_1551().field_1724;
      return (module().enabled() || J.visible())
         && var1 != null
         && var0.field_53528 == var1.method_5628()
         && !var0.field_53333
         && !var0.field_53542
         && !var0.method_62613(class_4050.field_18078);
   }

   public static HaloDesign shown() {
      if (p != null) {
         return p;
      } else {
         return H != null && System.nanoTime() < a ? H : module().selected();
      }
   }

   public static void tryOn(HaloDesign var0) {
      H = var0;
      a = System.nanoTime() + 400000000L;
   }

   static int colour() {
      HaloModule var0 = module();
      if (var0.F.enabled()) {
         class_746 var1 = class_310.method_1551().field_1724;
         class_8685 var2 = var1 != null ? var1.method_52814() : ClientBuilder.profile();
         class_2960 var3 = var2 != null ? var2.comp_1626().comp_3627() : q;
         int var4 = var3 == null ? 0 : HaloCache.of(var3);
         if (var4 != 0) {
            return var4;
         }
      }

      return var0.Q.get();
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2, GpuBufferSlice var3) {
      r = module().enabled() || J.visible();
      o = r;
      R = false;
      if (r) {
         m.D.set(var2).mul(var1);
         m.D.invert(m.s);
         g = var2.m11();
         class_243 var4 = var0.method_71156();
         S = var4.field_1352;
         b = var4.field_1351;
         k = var4.field_1350;
         Z = var3;
      }
   }

   static void measure(class_10055 var0, class_4587 var1, class_591 var2, class_11659 var3) {
      long var4 = 101225548877282L;
      int var6 = (int)((var4 ^ 67385678375596L) >>> 48);
      long var7 = (var4 ^ 67385678375596L) << 16 >>> 16;
      if (!o) {
         H((char)var6, var0, var1, var7, var2, var3);
      } else if (!R && !HaloShadowProbe.drawing()) {
         j.measure(var1, var2, var0);
         R = true;
         q = var0.field_53520.comp_1626().comp_3627();
         if (B) {
            ClientServices.LOGGER.info("Halo: head {}", j.describe());
         }
      }
   }

   private static void H(char var0, class_10055 var1, class_4587 var2, long var3, class_591 var5, class_11659 var6) {
      HaloModule var7;
      long var8;
      float var10;
      label21: {
         var7 = module();
         HaloDesign.ensureSourceClassInitialized();
         var8 = System.nanoTime();
         if (X != 0L) {
            long var11 = 107035342284621L;
            int var131 = var11 < 0L ? -1 : 1;
            if (var8 - X <= 250000000L) {
               var10 = (float)(var8 - X) / 1.0E9F;
               break label21;
            }
         }

         var10 = 0.0F;
      }

      X = var8;
      E.measure(var2, var5, var1);
      E.W = true;
      n.leanBack(-10.0F);
      n.update(var10, var7.enabled(), E, 0.0, 0.0, 0.0, var7.a.floatValue(), var7.i.enabled());
      if (n.visible()) {
         HaloDesign var12 = shown();
         G.H = colour();
         G.c = var7.w.floatValue();
         float var13 = n.place(E, var12, var7.b.floatValue() * 0.85F, var7.V.floatValue() * 0.4F, false, I, G);
         x.build(v, var12, I, G);
         HaloMeshSubmit.submit(v, var6, var13);
      }
   }

   public static void render() {
      boolean var0 = r;
      r = false;
      o = false;
      long var1 = System.nanoTime();
      float var3 = K == 0L ? 0.0F : (float)(var1 - K) / 1.0E9F;
      K = var1;
      HaloModule var4 = module();
      if (!var0) {
         l = false;
      } else {
         HaloMeasure var5 = R ? j : null;
         J.update(var3, var4.enabled(), var5, S + j.N, b + j.g, k + j.p, var4.a.floatValue(), var4.i.enabled());
         if (var5 != null && J.visible() && Z != null) {
            HaloDesign var6 = shown();
            F.H = colour();
            F.c = var4.w.floatValue();
            float var7 = J.place(var5, var6, var4.b.floatValue(), var4.V.floatValue(), var4.i.enabled(), c, F);
            float var8 = (float)Math.sqrt(c.U * c.U + c.c * c.c + c.y * c.y);
            var7 *= HaloBuilder.smooth(0.5F, 1.0F, var8);
            t = b + c.c + Math.abs(c.q) * (var6.b + 2.2F) * F.u * c.i + Math.sqrt(1.0F - Math.min(1.0F, c.q * c.q)) * 5.2F * F.u * c.i;
            l = true;
            f.build(d, var6, c, F);
            m.F = m.d = m.p = 0.0F;
            m.Q = c.U;
            m.E = c.c;
            m.I = c.y;
            m.a = var7;
            m.t = g * class_310.method_1551().method_1522().field_1481 * 0.5F;
            O.drawWorld(d, m, Z);
            if (B) {
               B = false;
               ClientServices.LOGGER
                  .info("Halo: {} at {} {} {}, axis {} {} {}, px {} half width {}, {}", new Object[]{var6.U, c.U, c.c, c.y, c.w, c.q, c.h, c.i, j.s, O.stats()});
            }
         } else {
            l = false;
         }
      }
   }

   public static void liftName(Object var0, class_10055 var1) {
      if (var0 == class_310.method_1551().field_1724 && l && var1.field_53338 != null && wears(var1)) {
         double var2 = var1.field_53326 + var1.field_53338.field_1351 + 0.3;
         double var4 = t - var2;
         if (var4 > 0.0) {
            var1.field_53338 = var1.field_53338.method_1031(0.0, var4 * J.presence(), 0.0);
         }
      }
   }

   public static void previewAppear() {
      J.appear();
   }

   public static void previewShine() {
      J.flourish();
   }

   public static void previewShiver() {
      J.shiver();
   }

   static void chosen() {
      J.flourish();
   }

   public static void install(HaloModule var0, ModuleContext var1) {
      W = var0;
      A = var1;
   }

   public static void metric(String var0) {
      if (A != null) {
         A.metric(var0, var0.equals("drawn") ? ++D : ++U);
      }
   }

   public static void uninstall() {
      HaloCache.release();
      d.clear();
      v.clear();
      Z = null;
      q = null;
      r = false;
      o = false;
      l = false;
      W = null;
      A = null;
      K = 0L;
   }
}
