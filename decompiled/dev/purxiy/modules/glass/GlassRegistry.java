package dev.purxiy.modules.glass;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.RenderHooks;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import net.fabricmc.fabric.api.client.rendering.v1.FabricRenderState;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.class_10017;
import net.minecraft.class_11659;
import net.minecraft.class_11890;
import net.minecraft.class_12206;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1533;
import net.minecraft.class_1542;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_591;
import net.minecraft.class_630;
import net.minecraft.class_638;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector4f;

public final class GlassRegistry {
   static final int o = 64;
   private static final int B = 192;
   private static final RenderStateDataKey<GlassRegistry.TypeU> A = RenderStateDataKey.create(ClientServices.label(() -> {
      int var0x = -857169936;
      int var1x = 857147069;
      int var2 = 857154476;
      return "purxiy:glass";
   }));
   private static final GlassSupport R = new GlassSupport();
   private static final GlassSupport C = new GlassSupport();
   private static final Set<class_630> m = Collections.newSetFromMap(new WeakHashMap<>());
   private static final float k = 0.32F;
   private static final float d = 0.07F;
   private static final GlassSupport4 S = new GlassSupport4();
   private static final GlassSupport4 W = new GlassSupport4();
   private static final float[] O = new float[512];
   private static final float[] I = new float[8];
   private static final float[] u = new float[6];
   private static int h = 1;
   private static int t = 0;
   private static long z = 0L;
   private static boolean l;
   private static boolean Y;
   private static int G = 0;
   private static class_638 x;
   private static long f = 0L;
   private static float y;
   private static final Matrix4f N = new Matrix4f();
   private static final Matrix4f v = new Matrix4f();
   private static double M;
   private static double L;
   private static double j;
   private static GpuBufferSlice a;
   private static GpuTextureView D;
   private static final float[] X = new float[]{1.0F, 1.0F, 1.0F};
   private static final float[] F = new float[3];
   private static float q;
   private static float T;
   private static float i;
   private static boolean r;
   private static final Vector3f b = new Vector3f(1.0F, 0.0F, 0.0F);
   private static final Vector3f K = new Vector3f(0.0F, 1.0F, 0.0F);
   private static final Vector3f H = new Vector3f(0.0F, 0.0F, 1.0F);
   static double c = -1.0;
   private static boolean Z = true;
   static float w = -1.0F;
   private static boolean U;
   private static String E;
   private static final Set<class_10017> s = Collections.newSetFromMap(new WeakHashMap<>());
   static GlassModule V;
   private static ModuleContext P;
   private static int g = 0;
   private static int Q = 0;
   public static int p = 0;
   private static final String[] n = new String[9];

   private GlassRegistry() {
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2, GpuBufferSlice var3) {
      long var4 = 39747609375578L;
      long var6 = var4 ^ 3917437497807L;
      int var8 = (int)((var4 ^ 3917437497807L) >>> 32);
      int var9 = (int)((var4 ^ 3917437497807L) << 32 >>> 48);
      int var10 = (int)(var6 << 48 >>> 48);
      long var11 = var4 ^ 108072603861502L;
      if (!ModuleWidget.claimed("mirror-capture")) {
         long var13 = System.nanoTime();
         y = f != 0L && var13 - f < 500000000L ? (float)(var13 - f) / 1.0E9F : 0.0F;
         f = var13;
         z++;
         R.reset();
         D = null;
         h = 1;
         t = 0;
         G = 0;
         class_310 var15 = class_310.method_1551();
         GlassModule var16 = V;
         if (var15.field_1687 != x) {
            x = var15.field_1687;
            r = false;
         }

         l = var16.enabled() && var15.field_1687 != null && !var15.field_1773.method_35765() && GlassRenderer.ready();
         if (!l) {
            r = false;
         } else {
            try {
               N.set(var1);
               class_243 var17 = var0.method_71156();
               M = var17.field_1352;
               L = var17.field_1351;
               j = var17.field_1350;
               a = var3;
               Matrix4f var18 = new Matrix4f(var1).invert();
               var18.transformDirection(b.set(1.0F, 0.0F, 0.0F)).normalize();
               var18.transformDirection(K.set(0.0F, 1.0F, 0.0F)).normalize();
               var18.transformDirection(H.set(0.0F, 0.0F, 1.0F)).normalize();
               K(var16, var8, var9, (char)var10);
               GlassSupport4 var19 = S;
               var19.y = false;
               var19.E.identity();
               var19.k.set(var2).mul(var1);
               var19.k.invert(var19.X);
               var19.n.set(0.0F, 1.0F, 0.0F);
               var19.q.set(b);
               var19.v.set(b).mul(-2.6F).add(K.x * 3.2F, K.y * 3.2F, K.z * 3.2F).add(H.x * 1.6F, H.y * 1.6F, H.z * 1.6F);
               var19.N.set(b).mul(3.0F).add(K.x * 0.6F, K.y * 0.6F, K.z * 0.6F).add(H.x * 0.8F, H.y * 0.8F, H.z * 0.8F);
               var19.V = 0.32F;
               var19.r = O;
               Q(var15, var0, var11, var19);
               var19.look(X, q, T, i);
            } catch (Throwable var201) {
               metric("draw_failures");
               l = false;
               fail(var201);
            }
         }
      }
   }

   private static void K(GlassModule var0, int var1, int var2, char var3) {
      GlassRenderer4.ensureSourceClassInitialized();
      int var4 = var0.o.argb();
      F[0] = (var4 >> 16 & 0xFF) / 255.0F;
      F[1] = (var4 >> 8 & 0xFF) / 255.0F;
      F[2] = (var4 & 0xFF) / 255.0F;
      float var5 = r ? 1.0F - (float)Math.exp(-y * 6.0) : 1.0F;

      for (int var6 = 0; var6 < 3; var6++) {
         X[var6] = X[var6] + (F[var6] - X[var6]) * var5;
      }

      q = q + (var0.j.floatValue() - q) * var5;
      T = T + (var0.G.floatValue() - T) * var5;
      i = i + ((var0.I.enabled() ? 1.0F : 0.0F) - i) * var5;
      r = true;
   }

   private static void Q(class_310 var0, class_4184 var1, long var2, GlassSupport4 var4) {
      long var5 = 75813862906285L;
      float[] var7 = var4.e;
      GlassRenderer4.ensureSourceClassInitialized();
      float[] var8 = var4.I;
      float[] var9 = var4.w;
      Vector3f var10 = var4.B;
      float var11 = var1.method_55437();
      class_638 var12 = var0.field_1687;
      int var13 = (Integer)var1.method_75756().method_75689(class_12206.field_63746, var11);
      int var14 = (Integer)var1.method_75756().method_75689(class_12206.field_63742, var11);
      e(var5, var14, var7);
      long var15 = 77433613775523L;
      int var18 = var15 < 0L ? -1 : 1;
      boolean var18x = var12 != null && var12.method_8597().comp_642();
      long var19 = 77433613775524L;
      int var21 = var19 < 0L ? -1 : 1;
      float var22;
      float var21x;
      if (var18x) {
         e(var5, var13, var8);
         float var23 = (Float)var1.method_75756().method_75689(class_12206.field_64340, var11) * (float) (Math.PI / 180.0);
         var22 = class_3532.method_15363((Float)var1.method_75756().method_75689(class_12206.field_64346, var11), 0.0F, 1.0F);
         long var24 = 77433613775523L;
         int var27 = var24 < 0L ? -1 : 1;
         float var27x = class_3532.method_15362(var23);
         Vector3f var28 = var10.set(-class_3532.method_15374(var23), var27x, 0.0F);
         long var29 = 77433613775523L;
         int var29x = var29 < 0L ? -1 : 1;
         var28.normalize();
         if (var27x > -0.08F) {
            float var31x = 1.0F - class_3532.method_15363(var27x / 0.35F, 0.0F, 1.0F);
            var21x = class_3532.method_15363((var27x + 0.08F) / 0.2F, 0.0F, 1.0F) * (1.0F - var12.method_8430(var11) * 0.85F);
            long var32 = 77433613775523L;
            int var32x = var32 < 0L ? -1 : 1;
            var9[0] = 1.0F;
            var9[1] = 0.94F - 0.22F * var31x;
            var9[2] = 0.86F - 0.4F * var31x;
         } else {
            var10.negate();
            var21x = 0.35F * class_3532.method_15363((-var27x - 0.08F) / 0.2F, 0.0F, 1.0F) * (1.0F - var12.method_8430(var11));
            var9[0] = 0.72F;
            var9[1] = 0.8F;
            var9[2] = 1.0F;
         }
      } else {
         System.arraycopy(var7, 0, var8, 0, 3);
         var22 = 0.0F;
         var21x = 0.0F;
      }

      var4.U = var21x;
      var4.Q = var22;
      var4.b = time();
      var4.Y = w;
   }

   private static void e(long var0, int var2, float[] var3) {
      var3[0] = (var2 >> 16 & 0xFF) / 255.0F;
      var3[1] = (var2 >> 8 & 0xFF) / 255.0F;
      var3[2] = (var2 & 0xFF) / 255.0F;
   }

   static float time() {
      double var0 = c >= 0.0 ? c : System.nanoTime() / 1.0E9;
      return (float)(var0 % 3600.0);
   }

   public static void playerModel(class_591 var0) {
      m.add(var0.field_3394);
      m.add(var0.field_3483);
      m.add(var0.field_3484);
      m.add(var0.field_3486);
      m.add(var0.field_3482);
      m.add(var0.field_3479);
   }

   public static void extract(class_1297 var0, class_10017 var1) {
      long var2 = 12162641394683L;
      long var4 = var2 ^ 43181122815226L;
      long var6 = var2 ^ 21714330055784L;
      if (l && !var1.field_53333 && t < 192 && !ModuleWidget.claimed("mirror-capture") && !RenderHooks.dissolving(var1) && !GlassSupport6.drawing()) {
         try {
            GlassModule var8 = V;
            class_310 var9 = class_310.method_1551();
            boolean var10 = var8.H.enabled();
            boolean var11;
            if (!(var0 instanceof class_11890)) {
               if (var0 instanceof class_1542) {
                  if (!var10) {
                     return;
                  }

                  var11 = true;
               } else {
                  if (!var10 || !V(var4, var0)) {
                     return;
                  }

                  var11 = false;
               }
            } else {
               var11 = var0 == var9.field_1724 ? var8.B.enabled() || ModuleWidget.claimed("preview-glass") : var8.Y.enabled();
               if (!var11 && (!var10 || !V(var4, var0))) {
                  return;
               }
            }

            t++;
            int var12 = h < 64 ? h++ : 0;
            if (var12 > 0) {
               int var13 = var12 * 8;
               float var14 = var1.field_53330 * 0.5F;
               O[var13] = (float)(var1.field_53325 - M);
               O[var13 + 1] = (float)(var1.field_53326 + var14 - L);
               O[var13 + 2] = (float)(var1.field_53327 - j);
               O[var13 + 3] = Math.max(0.2F, Math.max(var1.field_53329, var1.field_53330) * 0.5F + 0.05F);
               O[var13 + 4] = E(var0, var6);
               O[var13 + 5] = hash(var0.method_5628());
               O[var13 + 6] = var0 instanceof class_1542 ? 1.0F : 0.0F;
               O[var13 + 7] = 0.0F;
            }

            s.add(var1);
            ((FabricRenderState)var1).setData(A, new GlassRegistry.TypeU(z, var12, var11, var10));
         } catch (Throwable var151) {
            metric("draw_failures");
            fail(var151);
         }
      }
   }

   private static boolean V(long var0, class_1297 var2) {
      GlassRenderer4.ensureSourceClassInitialized();
      long var3 = 48697255132928L;
      int var6 = var3 < 0L ? -1 : 1;
      return var2 instanceof class_1309 var7
         ? !var7.method_6047().method_7960() || !var7.method_6079().method_7960()
         : var2 instanceof class_1533 var6x && !var6x.method_6940().method_7960();
   }

   private static float E(class_1297 var0, long var1) {
      GlassRenderer4.ensureSourceClassInitialized();
      if (var0 instanceof class_1309 var3 && var3.field_6235 > 0) {
         float var4 = class_310.method_1551().method_61966().method_60637(false);
         float var5 = (var3.field_6235 - var4) / Math.max(1, var3.field_6254);
         return class_3532.method_15363(var5, 0.0F, 1.0F);
      } else {
         return 0.0F;
      }
   }

   public static class_11659 collector(class_10017 var0, class_11659 var1) {
      GlassRegistry.TypeU var2 = (GlassRegistry.TypeU)((FabricRenderState)var0).getData(A);
      return (class_11659)(var2 != null && var2.t == z && l && !ModuleWidget.claimed("mirror-capture") && !GlassSupport6.drawing()
         ? new Glassclass_11659(var1, var1, R, var2.g, var2.G, var2.l, V.r.enabled(), m)
         : var1);
   }

   public static void enterItem() {
      G++;
   }

   public static void exitItem() {
      if (G > 0) {
         G--;
      }
   }

   static boolean inItem() {
      return G > 0;
   }

   public static void hudProjection(int var0, int var1, float var2) {
      v.setPerspective(var2 * (float) (Math.PI / 180.0), (float)var0 / var1, 0.05F, 100.0F);
   }

   public static void beginHands(int var0) {
      C.reset();
      GlassModule var1 = V;
      class_310 var2 = class_310.method_1551();
      Y = var1.enabled() && (var1.B.enabled() || var1.H.enabled()) && var2.field_1687 != null && var2.field_1724 != null && GlassRenderer.ready();
      if (Y) {
         W.E.set(RenderSystem.getModelViewMatrix());
         I[4] = var2.field_1724 != null && var2.field_1724.field_6235 > 0
            ? class_3532.method_15363(
               (var2.field_1724.field_6235 - var2.method_61966().method_60637(false)) / Math.max(1, var2.field_1724.field_6254), 0.0F, 1.0F
            )
            : 0.0F;
         I[5] = 0.37F;
         I[6] = 0.0F;
      }
   }

   public static class_11659 handCollector(class_11659 var0) {
      if (!Y) {
         return var0;
      } else {
         GlassModule var1 = V;
         return new Glassclass_11659(var0, var0, C, 0, var1.B.enabled(), var1.H.enabled(), var1.r.enabled(), m);
      }
   }

   static void beforeTranslucent() {
      if (l && !R.isEmpty() && !ModuleWidget.claimed("mirror-capture") && Z) {
         try {
            class_276 var0 = class_310.method_1551().method_1522();
            if (var0.method_30278() != null) {
               D = GlassRenderer.x.keepDepth(var0.method_30278(), var0.field_1482, var0.field_1481);
            }
         } catch (Throwable var11) {
            metric("draw_failures");
            D = null;
            fail(var11);
         }
      }
   }

   public static void renderWorld() {
      boolean var0 = l;
      l = false;
      if (var0 && !R.isEmpty() && !ModuleWidget.claimed("mirror-capture")) {
         try {
            class_276 var1 = class_310.method_1551().method_1522();
            if (var1.method_30277() == null || var1.method_71639() == null) {
               return;
            }

            S.T = h;
            GlassRenderer.x.draw(R, var1.method_30277(), var1.method_71639(), var1.field_1482, var1.field_1481, S, D != null ? D : var1.method_71640(), a);
         } catch (Throwable var21) {
            metric("draw_failures");
            fail(var21);
         }
      }
   }

   public static void renderHand() {
      long var0 = 52549456661274L;
      long var2 = var0 ^ 88964339526335L;
      boolean var4 = Y;
      Y = false;
      if (var4 && !C.isEmpty()) {
         try {
            class_276 var5 = class_310.method_1551().method_1522();
            if (var5.method_30277() == null || var5.method_71639() == null) {
               return;
            }

            GlassSupport4 var6 = W;
            var6.y = true;
            var6.k.set(v);
            var6.k.invert(var6.X);
            N.transformDirection(var6.n.set(0.0F, 1.0F, 0.0F)).normalize();
            var6.q.set(1.0F, 0.0F, 0.0F);
            var6.v.set(-0.9F, 1.05F, 0.45F);
            var6.N.set(1.1F, 0.25F, 0.35F);
            N.transformDirection(var6.B.set(S.B)).normalize();
            var6.U = S.U;
            System.arraycopy(S.w, 0, var6.w, 0, 3);
            System.arraycopy(S.I, 0, var6.I, 0, 3);
            System.arraycopy(S.e, 0, var6.e, 0, 3);
            var6.Q = S.Q;
            var6.b = time();
            var6.Y = w;
            var6.V = 0.07F;
            var6.look(X, q, T, i);
            Q(var2, var6.E);
            var6.r = I;
            var6.T = 1;
            GlassRenderer.f.draw(C, var5.method_30277(), var5.method_71639(), var5.field_1482, var5.field_1481, var6, null, null);
         } catch (Throwable var71) {
            metric("draw_failures");
            fail(var71);
         }
      }
   }

   private static void Q(long var0, Matrix4f var2) {
      float[] var3 = C.data();
      GlassRenderer4.ensureSourceClassInitialized();
      Vector4f var4 = new Vector4f();
      u[0] = u[1] = u[2] = Float.POSITIVE_INFINITY;
      u[3] = u[4] = u[5] = Float.NEGATIVE_INFINITY;

      for (int var5 = 0; var5 < C.vertices(); var5++) {
         int var6 = var5 * 12;
         var2.transform(var4.set(var3[var6], var3[var6 + 1], var3[var6 + 2], 1.0F));
         u[0] = Math.min(u[0], var4.x);
         long var7 = 139786159940004L;
         int var10 = var7 < 0L ? -1 : 1;
         u[1] = Math.min(u[1], var4.y);
         u[2] = Math.min(u[2], var4.z);
         u[3] = Math.max(u[3], var4.x);
         u[4] = Math.max(u[4], var4.y);
         u[5] = Math.max(u[5], var4.z);
      }

      I[0] = (u[0] + u[3]) * 0.5F;
      float var13 = u[1];
      float var14 = u[4];
      long var15 = 139786159940005L;
      int var15x = var15 < 0L ? -1 : 1;
      I[1] = (var13 + var14) * 0.5F;
      I[2] = (u[2] + u[5]) * 0.5F;
      float var10 = u[3] - u[0];
      float var11 = u[4] - u[1];
      float var12 = u[5] - u[2];
      I[3] = Math.max(0.1F, 0.5F * (float)Math.sqrt(var10 * var10 + var11 * var11 + var12 * var12));
   }

   static void look(GlassSupport4 var0) {
      long var1 = 29092979584102L;
      long var3 = var1 ^ 67911283502835L;
      int var5 = (int)((var1 ^ 67911283502835L) >>> 32);
      int var6 = (int)((var1 ^ 67911283502835L) << 32 >>> 48);
      int var7 = (int)(var3 << 48 >>> 48);
      if (!r) {
         K(V, var5, var6, (char)var7);
         r = false;
      }

      var0.look(X, q, T, i);
      var0.Y = w;
   }

   static void clear() {
      R.reset();
      C.reset();
      l = false;
      Y = false;
   }

   static void fail(Throwable var0) {
      String var1 = var0.toString();
      if (!var1.equals(E)) {
         E = var1;
         ClientServices.LOGGER.error("Purxiy glass failed", var0);
      }
   }

   static float hash(int var0) {
      int var1 = var0 * -1640531527;
      var1 ^= var1 >>> 15;
      var1 *= -2048144789;
      var1 ^= var1 >>> 13;
      return (var1 & 65535) / 65536.0F;
   }

   private static void D(long var0) {
      GlassRenderer4.ensureSourceClassInitialized();

      for (class_10017 var3 : s) {
         long var4 = 82846958005150L;
         int var6 = var4 < 0L ? -1 : 1;
         ((FabricRenderState)var3).setData(A, null);
      }

      long var7 = 82846958005150L;
      int var8 = var7 < 0L ? -1 : 1;
      s.clear();
   }

   public static void install(GlassModule var0, ModuleContext var1) {
      V = var0;
      P = var1;
   }

   public static void metric(String var0) {
      if (P != null) {
         P.metric(var0, var0.equals("drawn") ? ++g : ++Q);
      }
   }

   public static void uninstall() {
      long var0 = 67404897957728L;
      long var2 = var0 ^ 129831014440191L;
      int var4 = 0;

      try {
         GlassRenderer.x.releaseInstanceResources();
      } catch (Exception var14) {
         var4++;
      }

      try {
         GlassRenderer.f.releaseInstanceResources();
      } catch (Exception var13) {
         var4++;
      }

      try {
         if (D != null) {
            D.close();
         }
      } catch (Exception var11) {
         var4++;
      } finally {
         D = null;
      }

      m.clear();
      x = null;
      D(var2);
      V = null;
      P = null;
      f = 0L;
      r = false;
      if (var4 != 0) {
         throw new IllegalStateException("World visual GPU cleanup failed");
      }
   }

   private record TypeU(long t, int g, boolean G, boolean l) {
      public long frame() {
         return this.t;
      }

      public int slot() {
         return this.g;
      }

      public boolean body() {
         return this.G;
      }

      public boolean items() {
         return this.l;
      }

      public long longState2() {
         return this.t;
      }

      public int integerState45() {
         return this.g;
      }

      public boolean booleanState43() {
         return this.G;
      }

      public boolean booleanState36() {
         return this.l;
      }
   }
}
