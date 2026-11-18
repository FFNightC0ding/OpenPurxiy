// Sourced By DevFFNightmare
package dev.purxiy.modules.ripple;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.systems.RenderSystem.class_5590;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.assets.IrisCompat;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.RenderHooks;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import dev.purxiy.loader.fabric.mixin.OverlayTextureAccessor;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import it.unimi.dsi.fastutil.ints.Int2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Set;
import java.util.WeakHashMap;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.class_10017;
import net.minecraft.class_10042;
import net.minecraft.class_1043;
import net.minecraft.class_11659;
import net.minecraft.class_12137;
import net.minecraft.class_1297;
import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_238;
import net.minecraft.class_2394;
import net.minecraft.class_2398;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_287;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_3966;
import net.minecraft.class_4184;
import net.minecraft.class_4608;
import net.minecraft.class_638;
import net.minecraft.class_9799;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

import org.lwjgl.system.MemoryUtil;

public final class RippleRenderer2 {
   static final int M = 16;
   static final int w = 4;
   private static final int g = 9;
   private static final int P = 2512;
   static final float F = 0.6F;
   static final float vG = 0.42F;
   private static final double k = 1.0;
   private static final double Z = 0.3;
   private static final double d = 8.0;
   private static final double y = 96.0;
   private static final int T = -1291911168;
   private static final int u = 184;
   private static final RenderStateDataKey<RippleRenderer2.TypeV> b = RenderStateDataKey.create(ClientServices.label(() -> {
      int var0x = -676439240;
      int var1x = -676450716;
      int var2 = -676449461;
      return "purxiy:ripple";
   }));
   private static final Int2ObjectOpenHashMap<RippleRenderer2.TypeC> Y = new Int2ObjectOpenHashMap();
   private static final Int2DoubleOpenHashMap W = new Int2DoubleOpenHashMap();
   private static final RippleSupport2 O = new RippleSupport2();
   private static final RippleRenderer2.TypeC[] V = new RippleRenderer2.TypeC[16];
   private static final float[] z = new float[576];
   private static int s = -1;
   
   private static class_243 X;
   private static class_243 e = class_243.field_1353;
   private static double a = -10.0;
   private static long vX = 0L;
   private static boolean h;
   private static int m = 0;
   private static final Matrix4f B = new Matrix4f();
   private static final Matrix4f c = new Matrix4f();
   private static double vC;
   private static double J;
   private static double H;
   private static float q = 1.0F;
   
   private static GpuBufferSlice C;
   
   private static class_638 o;
   private static long j = 0L;
   private static float U;
   private static boolean K;
   private static final float[] I = new float[9];
   private static final float[] D = new float[9];
   private static float vP;
   private static boolean vk;
   private static int x = 0;
   
   private static GpuTexture E;
   
   private static GpuTextureView Q;
   private static int vd = 0;
   private static int vH = 0;
   
   private static GpuBuffer vE;
   
   private static GpuBuffer vf;
   
   private static ByteBuffer S;
   private static final class_9799 l = new class_9799(65536);
   
   private static String f;
   private static double vh = -1.0;
   private static boolean vx;
   private static double v;
   private static boolean A;
   
   private static GpuQuery N;
   private static double p;
   private static double v_;
   private static int t = 0;
   private static int G = 0;
   private static final Set<class_10017> i = Collections.newSetFromMap(new WeakHashMap<>());
   static RippleModule R;
   private static ModuleContext r;
   private static int L = 0;
   private static int v4 = 0;
   public static int n = 0;




   private static final long[] eb = new long[]{2534939682592711586L, 6729738655124202747L};
   private static final Long[] fb = new Long[2];

   private RippleRenderer2() {
   }

   static double clock() {
      return System.nanoTime() / 1.0E9;
   }

   static float life(RippleRenderer2.TypeF var0) {
      return (var0.F ? 0.42F : 0.6F) + linger() * 1.25F;
   }

   static float linger() {
      return R.q.floatValue();
   }

   private static double H(RippleRenderer2.TypeF var0, long var1, double var3) {
      RippleRenderer.ensureSourceClassInitialized();
      long var5 = 36208022551117L;
      if (36208022551117L == 0L) {
         boolean var10000 = false;
      } else {
         int var8 = var5 < 0L ? -1 : 1;
      }

      return vh >= 0.0 ? Math.min(vh, life(var0) - 0.001) : var3 - var0.i;
   }

   public static void onAttack(class_1297 var0) {
      class_310 var1 = class_310.method_1551();
      if (R.enabled() && var1.field_1724 != null) {
         s = var0.method_5628();
         X = var1.field_1765 instanceof class_3966 var2 && var2.method_17782() == var0 ? var2.method_17784() : null;
         e = var1.field_1724.method_5828(1.0F);
         a = clock();
      }
   }

   public static void onDamage( class_1297 var0, int var1, int var2, Optional<class_243> var3) {
      long var4 = 52082278000081L;
      long var6 = var4 ^ 36521980266761L;
      int var8 = (int)((var4 ^ 36521980266761L) >>> 32);
      int var9 = (int)((var4 ^ 36521980266761L) << 32 >>> 48);
      int var10 = (int)(var6 << 48 >>> 48);
      long var11 = var4 ^ 93255589271544L;
      class_310 var13 = class_310.method_1551();
      RippleModule var14 = R;
      if (var14.enabled() && var13.field_1687 != null && var13.field_1724 != null && var0 instanceof class_1309 var15 && !(var0 instanceof class_1531)) {
         double var16 = clock();
         boolean var18 = var1 == var13.field_1724.method_5628() && var0 != var13.field_1724;
         if (var18) {
            W.put(var0.method_5628(), var16);
         }

         if ((!var14.n.is("Your hits") || var18) && (var1 >= 0 || var2 >= 0 || !var3.isEmpty()) && !(var0.method_5858(var13.field_1724) > 9216.0)) {
            class_1297 var19 = var1 >= 0 ? var13.field_1687.method_8469(var1) : null;
            class_1297 var20 = var2 >= 0 ? var13.field_1687.method_8469(var2) : null;
            class_243 var21 = z(var15, var18, var19, var8, var9, (char)var10, var20, var3);
            if (var21 != null) {
               t(var11, var15, var21, var16);
            }
         }
      }
   }

   public static void onAnimate( class_1297 var0, int var1) {
      RippleModule var2 = R;
      if (var2.enabled() && var0 instanceof class_1309 && (var1 == 4 || var1 == 5)) {
         class_310 var3 = class_310.method_1551();
         double var4 = clock();
         RippleRenderer2.TypeC var6 = (RippleRenderer2.TypeC)Y.get(var0.method_5628());
         RippleRenderer2.TypeF var7 = var6 == null ? null : var6.latest();
         if (var7 != null && !(var4 - var7.i > 0.3)) {
            if (var1 == 4) {
               var7.F = true;
            }

            if (var2.o.enabled()) {
               class_1309 var8 = (class_1309)var0;
               class_243 var9 = D(var7, var8.method_23317(), var8.method_23318(), var8.method_23321(), u(var8.field_6283));
               boolean var10 = var0.method_5628() == s && var4 - a < 1.0;
               class_243 var11;
               if (var10) {
                  var11 = e;
               } else {
                  class_243 var12 = var8.method_5829().method_1005();
                  var11 = var12.method_1020(var3.field_1773.method_19418().method_71156()).method_1029();
               }

               RippleRenderer3.burst(
                  var9,
                  var11,
                  var8.method_5829().method_1005(),
                  var1 == 4,
                  var10 && var3.field_1724 != null ? (var3.field_1724.method_6068() == class_1306.field_6183 ? 1 : -1) : 0
               );
            }
         }
      }
   }

   public static void onEntityEvent( class_1297 var0, byte var1) {
      RippleModule var2 = R;
      class_310 var3 = class_310.method_1551();
      if (var1 == 3 && var2.enabled() && var2.H.enabled() && var0 instanceof class_1309 var4 && var4 != var3.field_1724 && W.containsKey(var4.method_5628())) {
         double var5 = W.remove(var4.method_5628());
         if (!(clock() - var5 > 8.0)) {
            class_238 var7 = var4.method_5829();
            RippleRenderer3.kill(
               var4.method_23317(),
               var7.field_1322,
               var4.method_23321(),
               (float)var7.method_17940(),
               (float)Math.max(var7.method_17939(), var7.method_17941()),
               var2.c.enabled()
            );
         }
      }
   }

   public static boolean replacesParticles(class_1297 var0, class_2394 var1) {
      RippleModule var2 = R;
      return var2.enabled() && var2.o.enabled() && (var1.method_10295() == class_2398.field_11205 || var1.method_10295() == class_2398.field_11208)
         ? var2.n.is("Everyone") || var0.method_5628() == s && clock() - a < 1.0
         : false;
   }

   public static void previewHit(class_1309 var0, class_243 var1, boolean var2) {
      long var3 = 102065635504588L;
      long var5 = var3 ^ 43270085985253L;
      class_238 var7 = var0.method_5829();
      class_243 var8 = new class_243(
         (var7.field_1323 + var7.field_1320) * 0.5, var7.field_1322 + var7.method_17940() * 0.62, (var7.field_1321 + var7.field_1324) * 0.5
      );
      class_243 var9 = var7.method_1014(0.05).method_992(var1, var8).orElse(b(var7, var1));
      double var10 = clock();
      s = var0.method_5628();
      X = var9;
      e = var8.method_1020(var1).method_1029();
      a = var10;
      t(var5, var0, var9, var10);
      if (var2) {
         onAnimate(var0, 4);
      }
   }

   public static void previewKill(class_1309 var0) {
      RippleModule var1 = R;
      if (var1.H.enabled()) {
         class_238 var2 = var0.method_5829();
         RippleRenderer3.kill(
            var0.method_23317(),
            var2.field_1322,
            var0.method_23321(),
            (float)var2.method_17940(),
            (float)Math.max(var2.method_17939(), var2.method_17941()),
            var1.c.enabled()
         );
      }
   }

   static void moduleChanged() {
      clear();
   }

   public static void clear() {
      Y.clear();
      W.clear();
      RippleRenderer3.clear();
      s = -1;
   }

   
   private static class_243 z(
      class_1309 var0, boolean var1,  class_1297 var2,  int var3, int var4, char var5, class_1297 var6, Optional<class_243> var7
   ) {
      RippleRenderer.ensureSourceClassInitialized();
      class_238 var8 = var0.method_5829();
      double var9 = clock();
      if (var1 && var0.method_5628() == s && var9 - a < 1.0 && X != null) {
         return b(var8.method_1014(0.05), X);
      } else if (var6 != null && var6 != var2) {
         class_243 var16 = var6.method_73189();
         class_243 var17 = var6.method_18798();
         if (var17.method_1027() > 1.0E-4) {
            class_243 var18 = var16.method_1020(var17.method_1029().method_1021(3.0));
            class_238 var19 = var8.method_1014(0.1);
            Optional var15 = var19.method_992(var18, var16.method_1019(var17.method_1029().method_1021(3.0)));
            if (var15.isPresent()) {
               return (class_243)var15.get();
            }
         }

         return b(var8, var16);
      } else if (var2 != null) {
         class_243 var11 = var2.method_33571();
         class_243 var12 = var2 == class_310.method_1551().field_1724 ? (e.method_1027() > 0.0 ? e : var2.method_5828(1.0F)) : var2.method_5828(1.0F);
         Optional var13 = var8.method_1014(0.15).method_992(var11, var11.method_1019(var12.method_1021(8.0)));
         if (var13.isPresent()) {
            return b(var8, (class_243)var13.get());
         } else {
            class_243 var14 = new class_243(
               var11.field_1352,
               Math.clamp(var11.field_1351, var8.field_1322 + var8.method_17940() * 0.3, var8.field_1325 - var8.method_17940() * 0.15),
               var11.field_1350
            );
            return b(var8, var14);
         }
      } else {
         return var7.<class_243>map(var1x -> b(var8, var1x)).orElse(null);
      }
   }

   private static class_243 b(class_238 var0, class_243 var1) {
      return new class_243(
         Math.clamp(var1.field_1352, var0.field_1323, var0.field_1320),
         Math.clamp(var1.field_1351, var0.field_1322, var0.field_1325),
         Math.clamp(var1.field_1350, var0.field_1321, var0.field_1324)
      );
   }

   private static void t(long var0, class_1309 var2, class_243 var3, double var4) {
      RippleRenderer.ensureSourceClassInitialized();
      RippleRenderer2.TypeC var6 = (RippleRenderer2.TypeC)Y.computeIfAbsent(var2.method_5628(), RippleRenderer2.TypeC::new);
      RippleRenderer2.TypeF var7 = new RippleRenderer2.TypeF(hash(var2.method_5628() * 31 + var6.T * 7 + (int)(var4 * 1000.0)));
      float var8 = u(var2.field_6283);
      double var9 = var3.field_1352 - var2.method_23317();
      double var11 = var3.field_1351 - var2.method_23318();
      double var13 = var3.field_1350 - var2.method_23321();
      float var15 = class_3532.method_15362(-var8);
      float var16 = class_3532.method_15374(-var8);
      var7.M = (float)(var9 * var15 + var13 * var16);
      var7.j = (float)var11;
      var7.W = (float)(-var9 * var16 + var13 * var15);
      var7.i = var4;
      class_238 var17 = var2.method_5829();
      double var18 = 0.0;

      for (int var20 = 0; var20 < 8; var20++) {
         long var21 = 135852431274536L;
         if (135852431274536L == 0L) {
            boolean var10000 = false;
         } else {
            int var31 = var21 < 0L ? -1 : 1;
         }

         double var24 = (var20 & 1) == 0 ? var17.field_1323 : var17.field_1320;
         double var26 = (var20 & 2) == 0 ? var17.field_1322 : var17.field_1325;
         double var28 = (var20 & 4) == 0 ? var17.field_1321 : var17.field_1324;
         var18 = Math.max(
            var18,
            Math.sqrt(
               (var24 - var3.field_1352) * (var24 - var3.field_1352)
                  + (var26 - var3.field_1351) * (var26 - var3.field_1351)
                  + (var28 - var3.field_1350) * (var28 - var3.field_1350)
            )
         );
      }

      var7.A = (float)var18 * 1.05F + 0.25F;
      long var30 = 135852431274537L;
      if (135852431274537L == 0L) {
         boolean var32 = false;
      } else {
         int var33 = var30 < 0L ? -1 : 1;
      }

      var6.add(var7);
   }

   private static float u(float var0) {
      return (180.0F - var0) * (float) (Math.PI / 180.0);
   }

   private static class_243 D(RippleRenderer2.TypeF var0, double var1, double var3, double var5, float var7) {
      float var8 = class_3532.method_15362(var7);
      float var9 = class_3532.method_15374(var7);
      return new class_243(var1 + var0.M * var8 + var0.W * var9, var3 + var0.j, var5 - var0.M * var9 + var0.W * var8);
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2, GpuBufferSlice var3) {
      long var4 = 108762979416093L;
      long var6 = var4 ^ 27238985010802L;
      long var8 = var4 ^ 72589453214288L;
      long var10 = var4 ^ 86507751700868L;
      if (!ModuleWidget.claimed("mirror-capture")) {
         long var12 = System.nanoTime();
         U = j != 0L && var12 - j < 500000000L ? (float)(var12 - j) / 1.0E9F : 0.0F;
         j = var12;
         vX++;
         m = 0;
         O.reset();
         class_310 var14 = class_310.method_1551();
         RippleModule var15 = R;
         if (var14.field_1687 != o) {
            o = var14.field_1687;
            clear();
         }

         l(var15.enabled(), var10);
         h = var15.enabled() && var14.field_1687 != null;
         if (!h) {
            if (!Y.isEmpty()) {
               clear();
            }

            RippleRenderer3.update(0.0F);
         } else {
            L(var15, var6);
            B.set(var2).mul(var1);
            B.invert(c);
            q = Math.abs(var2.m11());
            class_243 var16 = var0.method_71156();
            vC = var16.field_1352;
            J = var16.field_1351;
            H = var16.field_1350;
            C = var3;
            double var17 = clock();
            ObjectIterator var19 = Y.values().iterator();

            while (var19.hasNext()) {
               RippleRenderer2.TypeC var20 = (RippleRenderer2.TypeC)var19.next();
               int var21 = 0;

               for (int var22 = 0; var22 < var20.T; var22++) {
                  RippleRenderer2.TypeF var23 = var20.q[var22];
                  if (H(var23, var8, var17) < life(var23)) {
                     var20.q[var21++] = var23;
                  }
               }

               for (int var24 = var21; var24 < var20.T; var24++) {
                  var20.q[var24] = null;
               }

               var20.T = var21;
               if (var21 == 0) {
                  var19.remove();
               }
            }

            if (W.size() > 64) {
               W.int2DoubleEntrySet().removeIf(var2x -> {
                  RippleRenderer.ensureSourceClassInitialized();
                  return var17 - var2x.getDoubleValue() > 8.0;
               });
            }

            RippleRenderer3.update(vh >= 0.0 ? 0.0F : U);
            K = IrisCompat.inUse();
            if (K && (!Y.isEmpty() || RippleRenderer3.active())) {
               ClientSupport3.wantHandMask();
            }
         }
      }
   }

   private static void L(RippleModule var0, long var1) {
      long var3 = 9427504958044L;
      RippleRenderer.ensureSourceClassInitialized();
      RippleLoader var5 = RippleLoader.resolve(var0.J.get(), var0.K.argb());
      M(D, 0, var5.D, var3);
      M(D, 3, var5.v, var3);
      M(D, 6, var5.H, var3);
      float var6 = vk ? 1.0F - (float)Math.exp(-U * 6.0) : 1.0F;

      for (int var7 = 0; var7 < I.length; var7++) {
         long var8 = 134334913066607L;
         if (134334913066607L == 0L) {
            boolean var10000 = false;
         } else {
            int var12 = var8 < 0L ? -1 : 1;
         }

         I[var7] = I[var7] + (D[var7] - I[var7]) * var6;
      }

      vP = vP + (var0.F.floatValue() - vP) * var6;
      long var11 = 134334913066607L;
      if (134334913066607L == 0L) {
         boolean var13 = false;
      } else {
         int var14 = var11 < 0L ? -1 : 1;
      }

      vk = true;
   }

   static float[] palette() {
      return I;
   }

   static float glow() {
      return vP;
   }

   static float focal() {
      return q;
   }

   private static void l(boolean var0, long var1) {
      RippleRenderer.ensureSourceClassInitialized();
      int var3 = -1291911168;
      long var4 = 48683405001112L;
      if (48683405001112L == 0L) {
         boolean var10000 = false;
      } else {
         int var21 = var4 < 0L ? -1 : 1;
      }

      if (var0) {
         RippleModule var7 = R;
         int var8 = RippleLoader.resolve(var7.J.get(), var7.K.argb()).D;
         var3 = -1207959552 | var8 & 16777215;
      }

      if (var3 != x) {
         try {
            class_4608 var17 = class_310.method_1551().field_1773.method_22975();
            class_1043 var18 = ((OverlayTextureAccessor)var17).purxiy$texture();

            for (int var9 = 0; var9 < 8; var9++) {
               long var10 = 48683405001113L;
               if (48683405001113L == 0L) {
                  boolean var22 = false;
               } else {
                  int var23 = var10 < 0L ? -1 : 1;
               }

               for (int var13 = 0; var13 < 16; var13++) {
                  var18.method_4525().method_61941(var13, var9, var3);
                  long var14 = 48683405001113L;
                  if (48683405001113L == 0L) {
                     boolean var24 = false;
                  } else {
                     int var25 = var14 < 0L ? -1 : 1;
                  }
               }

               long var20 = 48683405001112L;
               if (48683405001112L == 0L) {
                  boolean var26 = false;
               } else {
                  int var27 = var20 < 0L ? -1 : 1;
               }
            }

            var18.method_4524();
            long var19 = 48683405001113L;
            if (48683405001113L == 0L) {
               boolean var28 = false;
            } else {
               int var29 = var19 < 0L ? -1 : 1;
            }

            x = var3;
         } catch (Throwable var16) {
            metric("draw_failures");
            x = var3;
            fail(var16);
         }
      }
   }

   public static void extract(class_1297 var0, class_10017 var1) {
      long var2 = 103239469024356L;
      long var4 = var2 ^ 137486629256745L;
      long var6 = var2 ^ 51828911636926L;
      if (h && !Y.isEmpty() && !var1.field_53333 && var1 instanceof class_10042 var8 && !ModuleWidget.claimed("mirror-capture")) {
         RippleRenderer2.TypeC var9 = (RippleRenderer2.TypeC)Y.get(var0.method_5628());
         if (var9 != null && var9.T != 0 && m < 16 && j(var6)) {
            try {
               int var10 = m++;
               V[var10] = var9;
               double var11 = clock();
               float var13 = u(var8.field_53446);
               int var14 = var10 * 9 * 4;
               z[var14] = var9.T;
               z[var14 + 1] = 1.0F;
               z[var14 + 2] = var1.field_53330;
               z[var14 + 3] = 0.0F;

               for (int var15 = 0; var15 < 4; var15++) {
                  int var16 = var14 + (1 + var15 * 2) * 4;
                  if (var15 >= var9.T) {
                     for (int var20 = 0; var20 < 8; var20++) {
                        z[var16 + var20] = 0.0F;
                     }
                  } else {
                     RippleRenderer2.TypeF var17 = var9.q[var15];
                     class_243 var18 = D(var17, var1.field_53325, var1.field_53326, var1.field_53327, var13);
                     z[var16] = (float)(var18.field_1352 - vC);
                     z[var16 + 1] = (float)(var18.field_1351 - J);
                     z[var16 + 2] = (float)(var18.field_1350 - H);
                     z[var16 + 3] = (float)H(var17, var4, var11);
                     z[var16 + 4] = var17.A;
                     z[var16 + 5] = var17.F ? 1.0F : 0.0F;
                     z[var16 + 6] = var17.F ? 0.42F : 0.6F;
                     z[var16 + 7] = var17.m;
                  }
               }

               var8.field_53460 = false;
               i.add(var1);
               ((net.fabricmc.fabric.api.client.rendering.v1.FabricRenderState)var1).setData(b, new RippleRenderer2.TypeV(vX, var10));
            } catch (Throwable var19) {
               metric("draw_failures");
               fail(var19);
            }
         }
      }
   }

   public static class_11659 collector(class_10017 var0, class_11659 var1) {
      RippleRenderer2.TypeV var2 = (RippleRenderer2.TypeV)((net.fabricmc.fabric.api.client.rendering.v1.FabricRenderState)var0).getData(b);
      return (class_11659)(var2 != null && var2.b == vX && h && !ModuleWidget.claimed("mirror-capture") && !RenderHooks.dissolving(var0)
         ? new Rippleclass_11659(var1, var1, O, var2.R)
         : var1);
   }

   public static void render() {
      long var0 = 74972732879338L;
      long var2 = var0 ^ 88088262527527L;
      int var4 = (int)((var0 ^ 88088262527527L) >>> 32);
      int var5 = (int)((var0 ^ 88088262527527L) << 32 >>> 40);
      int var6 = (int)(var2 << 56 >>> 56);
      boolean var7 = h;
      h = false;
      if (var7 && !ModuleWidget.claimed("mirror-capture")) {
         long var8 = System.nanoTime();
         GpuTextureView var10 = null;

         try {
            boolean var11 = m > 0 && O.vertices() > 0;
            if (K && (var11 || RippleRenderer3.active())) {
               var10 = ClientSupport3.handMask();
            }

            if (var11) {
               B(var4, var5, (byte)var6, var10);
            }
         } catch (Throwable var12) {
            metric("draw_failures");
            fail(var12);
         }

         RippleRenderer3.render(B, c, C, var10);
      }
   }

   private static void B( int var0, int var1, byte var2, GpuTextureView var3) {
      long var4 = 22206432459725L;
      long var6 = var4 ^ 11943453542064L;
      long var8 = var4 ^ 10127379509286L;
      long var10 = var4 ^ 69556468874799L;
      long var12 = var4 ^ 70319234648308L;
      long var14 = var4 ^ 113199277571607L;
      RippleRenderer.ensureSourceClassInitialized();
      class_310 var16 = class_310.method_1551();
      class_276 var17 = var16.method_1522();
      GpuTextureView var18 = var17.method_71639();
      GpuTextureView var19 = var17.method_71640();
      if (var18 != null && var19 != null && C != null && j(var14)) {
         GpuDevice var20 = RenderSystem.getDevice();
         CommandEncoder var21 = var20.createCommandEncoder();
         v(var20, var6, var17.field_1482, var17.field_1481);
         p(var20, var10);
         class_287 var22 = new class_287(l, class_5596.field_27382, class_290.field_1577);
         List var23 = O.runs();
         float[] var24 = O.data();
         int[] var25 = O.slots();
         int var26 = 0;
         int var27 = 0;

         for (RippleSupport2.Data var29 : (Iterable<RippleSupport2.Data>)(Object)(var23)) {
            for (int var30 = var29.first(); var30 < var29.first() + var29.count(); var30++) {
               int var31 = var30 * 8;
               var22.method_22912(var24[var31], var24[var31 + 1], var24[var31 + 2])
                  .method_22913(var24[var31 + 3], var24[var31 + 4])
                  .method_1336(var25[var30], 0, 0, 255)
                  .method_22914(var24[var31 + 5], var24[var31 + 6], var24[var31 + 7]);
            }

            var26 += var29.count();
            var27 = Math.max(var27, var29.count() / 4);
         }

         if (var26 != 0) {
            try (class_9801 var53 = var22.method_60800()) {
               int var55 = class_290.field_1577.getVertexSize();
               N(var20, (long)var26 * var55, var12);
               var21.writeToBuffer(vE.slice(0L, (long)var26 * var55), var53.method_60818());
            }

            int var54 = var17.field_1481;
            int var56 = var17.field_1482;
            G(var8, var21, var56, var54);
            class_5590 var57 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
            GpuBuffer var32 = var57.method_68274(var27 * 6);
            class_12137 var33 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            int var34 = 0;
            Object var35 = null;
            boolean var36 = false;

            try {
               var36 = true;

               try (RenderPass var37 = var21.createRenderPass(() -> "Purxiy ripple", var18, OptionalInt.empty(), Q, OptionalDouble.of(1.0))) {
                  RenderSystem.bindDefaultUniforms(var37);
                  var37.setUniform("Fog", C);
                  var37.setUniform("PurxiyRipple", vf);
                  var37.bindTexture("DepthSampler", var19, var33);
                  if (var3 != null) {
                     var37.bindTexture("HandMaskSampler", var3, var33);
                  }

                  var37.setVertexBuffer(0, vE);
                  var37.setIndexBuffer(var32, var57.method_31924());

                  for (int var38 = 0; var38 < 2; var38++) {
                     var37.setPipeline(var38 == 0 ? RippleSupport4.V : (var3 != null ? RippleSupport4.o : RippleSupport4.E));
                     int var39 = 0;

                     for (RippleSupport2.Data var41 : (Iterable<RippleSupport2.Data>)(Object)(var23)) {
                        var37.bindTexture("Sampler0", var41.texture().comp_5226(), var41.texture().comp_5227());
                        var37.drawIndexed(var39, 0, var41.count() / 4 * 6, 1);
                        metric("drawn");
                        var39 += var41.count();
                     }

                     var34 = 5670075;
                  }
               }
            } finally {
               if (var36 && var35 != null) {
                  var21.timerQueryEnd((GpuQuery)var35);
                  N = (GpuQuery)var35;
               }
            }

            if (var35 != null) {
               var21.timerQueryEnd((GpuQuery)var35);
               N = (GpuQuery)var35;
            }
         }
      }
   }

   private static void G(long var0, CommandEncoder var2, int var3, int var4) {
      RippleRenderer.ensureSourceClassInitialized();
      Std140Builder var5 = Std140Builder.intoBuffer(S.clear())
         .putMat4f(B)
         .putMat4f(c)
         .putVec4(var3, var4, (float)(clock() % 3600.0), vP)
         .putVec4(I[0], I[1], I[2], linger())
         .putVec4(I[3], I[4], I[5], 0.0F)
         .putVec4(I[6], I[7], I[8], 0.0F)
         .putVec4(Math.max(0.75F, var4 / 1080.0F), 0.0F, 0.0F, 0.0F);

      for (int var6 = 0; var6 < 144; var6++) {
         long var7 = 31918592560106L;
         if (31918592560106L == 0L) {
            boolean var10000 = false;
         } else {
            int var16 = var7 < 0L ? -1 : 1;
         }

         int var10 = var6 * 4;
         long var11 = 31918592560107L;
         if (31918592560107L == 0L) {
            boolean var17 = false;
         } else {
            int var18 = var11 < 0L ? -1 : 1;
         }

         if (var6 < m * 9) {
            var5.putVec4(z[var10], z[var10 + 1], z[var10 + 2], z[var10 + 3]);
            long var13 = 31918592560107L;
            if (31918592560107L == 0L) {
               boolean var19 = false;
            } else {
               int var20 = var13 < 0L ? -1 : 1;
            }
         } else {
            var5.putVec4(0.0F, 0.0F, 0.0F, 0.0F);
         }
      }

      long var15 = 31918592560107L;
      if (31918592560107L == 0L) {
         boolean var21 = false;
      } else {
         int var22 = var15 < 0L ? -1 : 1;
      }

      var2.writeToBuffer(vf.slice(), var5.get());
   }

   private static boolean j(long var0) {
      RippleRenderer.ensureSourceClassInitialized();
      GpuDevice var2 = RenderSystem.getDevice();
      if (var2.precompilePipeline(RippleSupport4.V).isValid()) {
         boolean var3 = var2.precompilePipeline(RippleSupport4.E).isValid();
         long var4 = 126195068749274L;
         if (126195068749274L == 0L) {
            boolean var10000 = false;
         } else {
            int var7 = var4 < 0L ? -1 : 1;
         }

         if (var3 && (!K || var2.precompilePipeline(RippleSupport4.o).isValid())) {
            return true;
         }
      }

      return false;
   }

   private static void v(GpuDevice var0, long var1, int var3, int var4) {
      RippleRenderer.ensureSourceClassInitialized();
      if (E != null) {
         long var5 = 34010164821373L;
         if (34010164821373L == 0L) {
            boolean var10000 = false;
         } else {
            int var8 = var5 < 0L ? -1 : 1;
         }

         if (vd == var3 && vH == var4) {
            return;
         }
      }

      if (E != null) {
         Q.close();
         E.close();
      }

      E = var0.createTexture(() -> "Purxiy ripple depth", 12, TextureFormat.DEPTH32, var3, var4, 1, 1);
      Q = var0.createTextureView(E);
      vd = var3;
      vH = var4;
   }

   private static void p(GpuDevice var0, long var1) {
      RippleRenderer.ensureSourceClassInitialized();
      if (vf == null) {
         vf = var0.createBuffer(ClientServices.label(() -> "Purxiy ripple uniforms"), 136, 2512L);
         S = MemoryUtil.memAlloc(2512);
      }
   }

   private static void N(GpuDevice var0, long var1, long var3) {
      RippleRenderer.ensureSourceClassInitialized();
      if (vE != null) {
         long var5;
         int var7 = (var5 = vE.size() - var1) == 0L ? 0 : (var5 < 0L ? -1 : 1);
         long var8 = 48130200711992L;
         if (48130200711992L == 0L) {
            boolean var10000 = false;
         } else {
            int var14 = var8 < 0L ? -1 : 1;
         }

         if (var7 >= 0) {
            return;
         }
      }

      long var11;
      for (var11 = 65536L; var11 < var1; var11 <<= 1) {
         long var12 = 48130200711992L;
         if (48130200711992L == 0L) {
            boolean var15 = false;
         } else {
            int var16 = var12 < 0L ? -1 : 1;
         }
      }

      long var13 = 48130200711992L;
      if (48130200711992L == 0L) {
         boolean var17 = false;
      } else {
         int var18 = var13 < 0L ? -1 : 1;
      }

      if (vE != null) {
         vE.close();
      }

      vE = var0.createBuffer(ClientServices.label(() -> "Purxiy ripple vertices"), 40, var11);
   }

   static void fail(Throwable var0) {
      String var1 = var0.toString();
      if (!var1.equals(f)) {
         f = var1;
         ClientServices.LOGGER.error("Purxiy ripple failed", var0);
      }
   }

   static float hash(int var0) {
      int var1 = var0 * -1640531527;
      var1 ^= var1 >>> 15;
      var1 *= -2048144789;
      var1 ^= var1 >>> 13;
      return (var1 & 65535) / 65536.0F;
   }

   private static void M(float[] var0, int var1, int var2, long var3) {
      var0[var1] = (var2 >> 16 & 0xFF) / 255.0F;
      var0[var1 + 1] = (var2 >> 8 & 0xFF) / 255.0F;
      var0[var1 + 2] = (var2 & 0xFF) / 255.0F;
   }

   private static void N(class_310 var0, boolean var1, boolean var2, long var3) {
      long var5 = 135852431274537L;
      RippleRenderer.ensureSourceClassInitialized();
      if (var0.field_1724 != null && var0.field_1687 != null) {
         class_1309 var7 = null;
         class_243 var8 = null;
         class_243 var9 = var0.field_1724.method_33571();
         if (var0.field_1765 instanceof class_3966 var10 && var10.method_17782() instanceof class_1309 var11) {
            var7 = var11;
            var8 = var10.method_17784();
         }

         if (var7 != null && var8 != null) {
            double var15 = clock();
            long var17 = 30209141705264L;
            if (30209141705264L == 0L) {
               boolean var10000 = false;
            } else {
               int var18 = var17 < 0L ? -1 : 1;
            }

            s = var7.method_5628();
            X = var8;
            e = var8.method_1020(var9).method_1029();
            a = var15;
            t(var5, var7, b(var7.method_5829().method_1014(0.05), var8), var15);
            if (var1) {
               onAnimate(var7, 4);
            }
         } else {
            ClientServices.LOGGER.info("Ripple: nothing to hit");
         }
      }
   }

   private static void x(long var0, class_310 var2, double var3) {
      long var5 = 30209141705265L;
      RippleRenderer.ensureSourceClassInitialized();
      if (var3 - v > 1.1) {
         long var7 = 47823316546748L;
         if (47823316546748L == 0L) {
            boolean var10000 = false;
         } else {
            int var13 = var7 < 0L ? -1 : 1;
         }

         if (var2.field_1765 instanceof class_3966) {
            v = var3;
            int var10 = (int)var3;
            long var11 = 47823316546748L;
            if (47823316546748L == 0L) {
               boolean var14 = false;
            } else {
               int var15 = var11 < 0L ? -1 : 1;
            }

            N(var2, var10 % 3 == 0, false, var5);
         }
      }
   }

   
   private static GpuQuery E(long var0, CommandEncoder var2) {
      RippleRenderer.ensureSourceClassInitialized();
      if (N != null) {
         OptionalLong var3 = N.getValue();
         long var4 = 79457133476123L;
         if (79457133476123L == 0L) {
            boolean var10000 = false;
         } else {
            int var10 = var4 < 0L ? -1 : 1;
         }

         if (var3.isEmpty()) {
            return null;
         }

         N.close();
         N = null;
         p = p + var3.getAsLong() / 1000000.0;
         int var7 = ++t;
         long var8 = 79457133476123L;
         if (79457133476123L == 0L) {
            boolean var11 = false;
         } else {
            int var12 = var8 < 0L ? -1 : 1;
         }

         if (var7 == 120) {
            ClientServices.LOGGER
               .info(
                  "Purxiy ripple: {} ms CPU, {} ms GPU per frame ({} vertices on average, {}x{})",
                  new Object[]{String.format(Locale.ROOT, "%.3f", v_ / t), String.format(Locale.ROOT, "%.3f", p / t), G / t, vd, vH}
               );
            v_ = 0.0;
            p = 0.0;
            G = 0;
            t = 0;
         }
      }

      return var2.timerQueryBegin();
   }

   private static void B(long var0) {
      RippleRenderer.ensureSourceClassInitialized();

      for (class_10017 var3 : i) {
         long var4 = 92679997362025L;
         if (92679997362025L == 0L) {
            boolean var10000 = false;
         } else {
            int var8 = var4 < 0L ? -1 : 1;
         }

         ((net.fabricmc.fabric.api.client.rendering.v1.FabricRenderState)var3).setData(b, null);
      }

      long var7 = 92679997362026L;
      if (92679997362026L == 0L) {
         boolean var9 = false;
      } else {
         int var10 = var7 < 0L ? -1 : 1;
      }

      i.clear();
   }

   public static void install(RippleModule var0, ModuleContext var1) {
      R = var0;
      r = var1;
   }

   public static void metric(String var0) {
      if (r != null) {
         r.metric(var0, var0.equals("drawn") ? ++L : ++v4);
      }
   }

   public static void uninstall() {
      long var0 = 72149872074327L;
      long var2 = var0 ^ 24000498291005L;
      int var4 = 0;

      try {
         if (Q != null) {
            Q.close();
         }
      } catch (Exception var123) {
         var4++;
      } finally {
         Q = null;
      }

      try {
         if (N != null) {
            N.close();
         }
      } catch (Exception var121) {
         var4++;
      } finally {
         N = null;
      }

      try {
         if (vE != null) {
            vE.close();
         }
      } catch (Exception var119) {
         var4++;
      } finally {
         vE = null;
      }

      try {
         if (vf != null) {
            vf.close();
         }
      } catch (Exception var117) {
         var4++;
      } finally {
         vf = null;
      }

      try {
         if (E != null) {
            E.close();
         }
      } catch (Exception var115) {
         var4++;
      } finally {
         E = null;
      }

      try {
         if (S != null) {
            MemoryUtil.memFree(S);
         }
      } catch (Exception var113) {
         var4++;
      } finally {
         S = null;
      }

      try {
         if (l != null) {
            l.close();
         }
      } catch (Exception var111) {
         var4++;
      } finally {
         ;
      }

      clear();
      B(var2);
      R = null;
      r = null;
      j = 0L;
      vk = false;
      if (var4 != 0) {
         throw new IllegalStateException("World visual GPU cleanup failed");
      }
   }

private static long b(int var0, long var1) {
      int var3 = (var0 ^ -335813726 ^ 23947) & 32767;
      if (fb[var3] == null) {
         fb[var3] = eb[var3] ^ 2534939682592777122L;
      }

      return fb[var3];
   }

static final class TypeC {
      final int X;
      final RippleRenderer2.TypeF[] q = new RippleRenderer2.TypeF[4];
      int T;

      TypeC(int var1) {
         this.X = var1;
      }

      void add(RippleRenderer2.TypeF var1) {
         if (this.T == 4) {
            System.arraycopy(this.q, 1, this.q, 0, 3);
            this.T--;
         }

         this.q[this.T++] = var1;
      }

      
      RippleRenderer2.TypeF latest() {
         return this.T == 0 ? null : this.q[this.T - 1];
      }
   }

   static final class TypeF {
      float M;
      float j;
      float W;
      double i;
      float A;
      boolean F;
      final float m;

      TypeF(float var1) {
         this.m = var1;
      }
   }

   private record TypeV(long b, int R) {
      public long frame() {
         return this.b;
      }

      public int slot() {
         return this.R;
      }

      public long longState51() {
         return this.b;
      }

      public int integerState41() {
         return this.R;
      }
   }
}
