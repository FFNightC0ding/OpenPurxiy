package dev.purxiy.modules.hand;

import com.mojang.blaze3d.buffers.GpuBuffer;
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
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientApi2;
import dev.purxiy.loader.fabric.client.ClientRegistry16;
import dev.purxiy.render.RenderPipelines;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import net.minecraft.class_11659;
import net.minecraft.class_12137;
import net.minecraft.class_1268;
import net.minecraft.class_276;
import net.minecraft.class_287;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_9799;
import net.minecraft.class_9801;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;

public final class HandMovementSupport2 {
   static final int s = 160;
   private static final float i = 0.3F;
   private static final int k = 13024;
   private static final float R = 3.5F;
   private static final float N = 14.0F;
   private static final int Y = 96;
   private static final float[] J = new float[]{
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F,
      0.0F
   };
   private static final HandMovementRenderer d = new HandMovementRenderer();
   private static boolean tZ;
   private static boolean a;
   private static boolean U;
   private static long D = -1L;
   private static boolean L;
   private static boolean f;
   private static class_1268 V;
   private static boolean l;
   private static boolean r;
   private static boolean O;
   private static int E = 0;
   private static final Matrix4f e = new Matrix4f();
   private static final Matrix4f t = new Matrix4f();
   private static final Matrix4f W = new Matrix4f();
   private static int A = 0;
   private static float tl = 70.0F;
   private static GpuTexture u;
   private static GpuTexture S;
   private static GpuTextureView v;
   private static GpuTextureView K;
   private static int j = 0;
   private static int b = 0;
   private static GpuTexture H;
   private static GpuTextureView B;
   private static int F = 0;
   private static int Q = 0;
   private static GpuBuffer G;
   private static GpuBuffer tp;
   private static ByteBuffer o;
   private static final class_9799 z = new class_9799(65536);
   private static String P;
   private static final ClientApi2[] t7 = new ClientApi2[2];
   private static final long[] n = new long[]{Long.MIN_VALUE, Long.MIN_VALUE};
   private static final Matrix4f[] w = new Matrix4f[160];
   private static final float[] p;
   private static final SwingStyle.TypeJ Z;
   private static final Matrix4f I;
   private static final Matrix4f g;
   private static final class_4587 X;
   private static final float[] y;
   private static final float[] T;
   private static final Vector4f q;
   private static boolean C;
   private static GpuQuery m;
   private static double c;
   private static double M;
   private static int h;
   private static int x;
   private static final long[] eb;
   private static final Long[] fb;

   private HandMovementSupport2() {
   }

   public static void hudProjection(int var0, int var1, float var2) {
      tl = var2;
      W.setPerspective(var2 * (float) (Math.PI / 180.0), (float)var0 / var1, 0.05F, 100.0F);
   }

   public static float fov() {
      return tl;
   }

   static Matrix4f projection() {
      return W;
   }

   static Matrix4f modelView() {
      return t;
   }

   static boolean masked() {
      return D == RenderPipelines.get().frame();
   }

   static HandMovementRenderer geometry() {
      return d;
   }

   public static void beginHands(int var0, boolean var1) {
      long var2 = 72990988514340L;
      long var4 = var2 ^ 27221539471462L;
      d.reset();
      tZ = true;
      l = false;
      A = var0;
      t.set(RenderSystem.getModelViewMatrix());
      f = var1;
      V = null;
      O = false;
      HandMovementModule var6 = HandMovementSupport4.y;
      a = IrisCompat.inUse();
      L = a && HandMovementRenderer3.takeWanted();
      long var7 = RenderPipelines.get().frame();
      D = L ? var7 : -1L;
      if (var6.enabled() && var6.N.enabled()) {
         class_1268 var9 = HandMovementSupport4.lastSwung();
         if (HandMovementSupport4.swingingWithin(var9, k(var4, var9))) {
            V = var9;
         } else {
            for (class_1268 var13 : class_1268.values()) {
               if (n[var13.ordinal()] >= var7 - 1L) {
                  V = var13;
                  O = true;
                  break;
               }
            }
         }
      }

      U = V != null || f || L;
   }

   public static class_11659 collector(class_1268 var0, class_11659 var1) {
      if (U && (var0 == V || f || L)) {
         d.hand(var0);
         return new HandMovementclass_11659(var1, var1, d);
      } else {
         d.hand(null);
         return var1;
      }
   }

   public static void endHand() {
      d.hand(null);
   }

   public static void sweeps(boolean var0) {
      d.sweeps(var0);
   }

   static void swingFrom(class_4587 var0, class_1268 var1, int var2, boolean var3) {
      if (var1 == V) {
         e.set(var0.method_23760().method_23761());
         l = true;
         r = var3;
         E = var2;
      }
   }

   public static void sweep(class_1268 var0, ClientApi2 var1) {
      t7[var0.ordinal()] = var1;
      n[var0.ordinal()] = RenderPipelines.get().frame();
   }

   private static ClientApi2 J(long var0, class_1268 var2) {
      SwingStyle.ensureSourceClassInitialized();
      return O && n[var2.ordinal()] == RenderPipelines.get().frame() ? t7[var2.ordinal()] : null;
   }

   private static float k(long var0, class_1268 var2) {
      float var3 = HandMovementSupport4.y.u.floatValue();
      return var3 * (O ? 0.3F : HandMovementSupport4.swingSeconds(var2));
   }

   public static void composite() {
      long var0 = 107869214882938L;
      long var2 = var0 ^ 131119563507886L;
      long var4 = var0 ^ 36317241588585L;
      long var6 = var0 ^ 64509570926132L;
      boolean var8 = tZ;
      tZ = false;
      class_1268 var9 = V;
      if (var8 && var9 != null && l && d.has(var9)) {
         long var10 = System.nanoTime();

         try {
            int var12 = G(var4, var9);
            if (var12 > 0) {
               r(var9, var6, var12);
               if (C) {
                  M = M + (System.nanoTime() - var10) / 1000000.0;
                  x += var12;
               }
            }
         } catch (Throwable var131) {
            HandMovementSupport4.metric("draw_failures");
            r(var131, var2);
         }
      }
   }

   private static int G(long var0, class_1268 var2) {
      long var3 = 99645586011202L;
      byte var5 = 0;
      long var6 = 50598152625783L;
      long var8 = 23953650217474L;
      long var10 = 65748169568095L;
      long var12 = 70134441633159L;
      long var14 = 62514923401302L;
      double var16 = HandMovementSupport4.clock();
      SwingStyle.ensureSourceClassInitialized();
      float var18 = k(var3, var2);
      if (!d.bounds(var2, y)) {
         return 0;
      } else {
         Matrix4f var19 = new Matrix4f(e).invert();
         ClientApi2 var20 = J(var8, var2);
         HandMovementSupport4.swingAt(var2, r, var16, Z);
         Matrix4f var21 = Y(new Matrix4f(), (char)var5, var6);
         long var22 = 73789793928978L;
         int var25 = var22 < 0L ? -1 : 1;
         if (var20 != null) {
            var20.at(0.0F, I);
            var21.mul(I);
         }

         var21.invert().mul(var19);
         Matrix4f var25x = new Matrix4f(W).mul(t);
         int var26 = 3945;
         class_276 var27 = class_310.method_1551().method_1522();
         float[] var28 = new float[16];
         float[] var29 = new float[16];
         float[] var30 = new float[8];
         r(var25x, new Matrix4f(), var12, var27.field_1482, var27.field_1481, var28);
         F(var28, var10, true);
         Matrix4f var31 = new Matrix4f();
         J[0] = 0.0F;

         for (int var32 = 1; var32 <= 96; var32++) {
            E(var2, var20, var16, var14, var18 * var32 / 96.0F, var21, var31);
            long var33 = 73789793928979L;
            int var55 = var33 < 0L ? -1 : 1;
            r(var25x, var31, var12, var27.field_1482, var27.field_1481, var29);
            F(var29, var10, false);
            float var35 = 0.0F;

            for (int var36 = 0; var36 < 8; var36++) {
               long var37 = 73789793928979L;
               int var38 = var37 < 0L ? -1 : 1;
               var30[var36] += (float)Math.hypot(var29[var36 * 2] - var28[var36 * 2], var29[var36 * 2 + 1] - var28[var36 * 2 + 1]);
               var35 = Math.max(var35, var30[var36]);
            }

            long var56 = 73789793928978L;
            int var58 = var56 < 0L ? -1 : 1;
            J[var32] = var35;
            float[] var38 = var28;
            var28 = var29;
            var29 = var38;
         }

         long var54 = 73789793928978L;
         int var34 = var54 < 0L ? -1 : 1;
         float var34x = J[96];
         long var55 = 73789793928978L;
         int var581 = var55 < 0L ? -1 : 1;
         float var57;
         var26 = (var57 = var34x - 2.0F) == 0.0F ? 0 : (var57 < 0.0F ? -1 : 1);
         long var58x = 73789793928979L;
         int var40 = var58x < 0L ? -1 : 1;
         if (var26 < 0) {
            return 0;
         } else {
            var40 = Math.clamp((long)((int)Math.ceil(var34x / 3.5F)), 6, 160);
            int var41 = 1;

            for (int var42 = 0; var42 < var40; var42++) {
               float var43;
               for (var43 = var34x * (var42 + 1.0F) / var40; var41 < 96; var41++) {
                  float var44;
                  var26 = (var44 = J[var41] - var43) == 0.0F ? 0 : (var44 < 0.0F ? -1 : 1);
                  long var45 = 73789793928978L;
                  int var47 = var45 < 0L ? -1 : 1;
                  if (var26 >= 0) {
                     break;
                  }
               }

               long var59 = 73789793928979L;
               int var46 = var59 < 0L ? -1 : 1;
               float var46x = J[var41] - J[var41 - 1];
               long var47 = 73789793928979L;
               int var50 = var47 < 0L ? -1 : 1;
               float var50x = var46x > 1.0E-4F ? (var43 - J[var41 - 1]) / var46x : 1.0F;
               float var51 = var18 * (var41 - 1 + var50x) / 96.0F;
               E(var2, var20, var16, var14, var51, var21, w[var42]);
               p[var42 * 2] = (var42 + 1.0F) / var40;
               p[var42 * 2 + 1] = 1.0F;
            }

            return var40;
         }
      }
   }

   private static void E(class_1268 var0, ClientApi2 var1, double var2, long var4, float var6, Matrix4f var7, Matrix4f var8) {
      byte var9 = 0;
      long var10 = 50598152625783L;
      SwingStyle.ensureSourceClassInitialized();
      HandMovementSupport4.swingAt(var0, r, var2 - var6, Z);
      var8.set(e).mul(Y(new Matrix4f(), (char)var9, var10));
      long var12 = 62514923401301L;
      int var14 = var12 < 0L ? -1 : 1;
      if (var1 != null) {
         var1.at(var6, g);
         var8.mul(g);
      }

      var8.mul(var7);
   }

   private static Matrix4f Y(Matrix4f var0, char var1, long var2) {
      SwingStyle.ensureSourceClassInitialized();
      X.method_22903();
      X.method_23760().method_23761().identity();
      if (r) {
         Z.applyArm(X, E);
      } else {
         Z.applyItem(X, E);
      }

      var0.set(X.method_23760().method_23761());
      X.method_22909();
      return var0;
   }

   private static void r(Matrix4f var0, Matrix4f var1, long var2, int var4, int var5, float[] var6) {
      SwingStyle.ensureSourceClassInitialized();

      for (int var7 = 0; var7 < 8; var7++) {
         q.set((var7 & 1) == 0 ? y[0] : y[3], (var7 & 2) == 0 ? y[1] : y[4], (var7 & 4) == 0 ? y[2] : y[5], 1.0F);
         var1.transform(q);
         var0.transform(q);
         long var8 = 70134441633158L;
         int var11 = var8 < 0L ? -1 : 1;
         float var11x = q.w > 1.0E-4F ? 1.0F / q.w : Float.NaN;
         var6[var7 * 2] = (q.x * var11x * 0.5F + 0.5F) * var4;
         var6[var7 * 2 + 1] = (q.y * var11x * 0.5F + 0.5F) * var5;
      }

      long var12 = 70134441633159L;
      int var9 = var12 < 0L ? -1 : 1;
   }

   private static void F(float[] var0, long var1, boolean var3) {
      SwingStyle.ensureSourceClassInitialized();
      if (var3) {
         T[0] = T[1] = Float.POSITIVE_INFINITY;
         T[2] = T[3] = Float.NEGATIVE_INFINITY;
      }

      int var4 = 0;

      while (true) {
         if (var4 >= 8) {
            return;
         }

         float var5 = var0[var4 * 2];
         float var6 = var0[var4 * 2 + 1];
         long var7 = 65748169568094L;
         int var10 = var7 < 0L ? -1 : 1;
         long var10x = 65748169568094L;
         int var12 = var10x < 0L ? -1 : 1;
         if (Float.isNaN(var5)) {
            break;
         }

         boolean var12x = Float.isNaN(var6);
         long var13 = 65748169568095L;
         int var14 = var13 < 0L ? -1 : 1;
         if (var12x) {
            break;
         }

         T[0] = Math.min(T[0], var5);
         T[1] = Math.min(T[1], var6);
         T[2] = Math.max(T[2], var5);
         T[3] = Math.max(T[3], var6);
         var4++;
      }

      T[0] = T[1] = -1000000.0F;
      T[2] = T[3] = 1000000.0F;
   }

   private static void r(class_1268 var0, long var1, int var3) {
      long var4 = 36368324360322L;
      long var6 = 112520080515020L;
      long var8 = 89706725362038L;
      long var10 = 5695755005688L;
      long var12 = 33872103603113L;
      long var14 = 64702733296325L;
      long var16 = 93870937847262L;
      class_310 var18 = class_310.method_1551();
      SwingStyle.ensureSourceClassInitialized();
      class_276 var19 = var18.method_1522();
      GpuTextureView var20 = var19.method_71639();
      GpuTextureView var21 = var19.method_71640();
      if (var20 != null && var21 != null && J(var6)) {
         GpuDevice var22 = RenderSystem.getDevice();
         CommandEncoder var23 = var22.createCommandEncoder();
         U(var14, var22, var19.field_1482, var19.field_1481);
         I(var22, var8);
         class_287 var24 = new class_287(z, class_5596.field_27382, class_290.field_1577);
         List var25 = d.runs();
         float[] var26 = d.data();
         int[] var27 = d.colors();
         int var28 = 0;

         for (HandMovementRenderer.Data var30 : var25) {
            if (var30.hand() != var0) {
               long var61 = 97515289672269L;
               int var67 = var61 < 0L ? -1 : 1;
            } else {
               for (int var31 = var30.first(); var31 < var30.first() + var30.count(); var31++) {
                  int var32 = var31 * 8;
                  long var33 = 97515289672269L;
                  int var37 = var33 < 0L ? -1 : 1;
                  var24.method_22912(var26[var32], var26[var32 + 1], var26[var32 + 2])
                     .method_22913(var26[var32 + 3], var26[var32 + 4])
                     .method_39415(var27[var31])
                     .method_22914(var26[var32 + 5], var26[var32 + 6], var26[var32 + 7]);
               }

               long var60 = 97515289672270L;
               int var661 = var60 < 0L ? -1 : 1;
               var28 += var30.count();
            }
         }

         long var59 = 97515289672269L;
         int var61 = var59 < 0L ? -1 : 1;
         if (var28 != 0) {
            class_9801 var63 = var24.method_60800();

            try {
               int var65 = class_290.field_1577.getVertexSize();
               long var34 = 97515289672269L;
               int var711 = var34 < 0L ? -1 : 1;
               C(var22, (long)var28 * var65, var10);
               var23.writeToBuffer(G.slice(0L, (long)var28 * var65), var63.method_60818());
               long var36 = 97515289672269L;
               int var39 = var36 < 0L ? -1 : 1;
            } catch (Throwable var54) {
               if (var63 != null) {
                  try {
                     var63.close();
                  } catch (Throwable var53) {
                     var54.addSuppressed(var53);
                  }
               }

               throw var54;
            }

            if (var63 != null) {
               var63.close();
            }

            e(var23, var16, var3);
            class_5590 var64 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
            int var66 = 0;

            for (HandMovementRenderer.Data var35 : var25) {
               if (var35.hand() == var0) {
                  var66 = Math.max(var66, var35.count() / 4);
               }
            }

            long var68 = 97515289672270L;
            int var731 = var68 < 0L ? -1 : 1;
            GpuBuffer var37 = var64.method_68274(var66 * 6);
            GpuTextureView var38 = var18.field_1773.method_22974().method_71650();
            class_12137 var39 = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
            GpuQuery var40 = C ? v(var12, var23, var3) : null;
            RenderPass var41 = var23.createRenderPass(() -> "Purxiy hand trail", v, OptionalInt.of(0), K, OptionalDouble.of(1.0));

            try {
               var41.setPipeline(HandMovementSupport3.d);
               var41.setUniform("PurxiyHandTrail", tp);
               var41.bindTexture("Sampler2", var38, var39);
               var41.setVertexBuffer(0, G);
               var41.setIndexBuffer(var37, var64.method_31924());
               int var42 = 0;

               for (HandMovementRenderer.Data var44 : var25) {
                  long var45 = 97515289672269L;
                  int var81 = var45 < 0L ? -1 : 1;
                  if (var44.hand() != var0) {
                     long var47 = 97515289672269L;
                     int var47x = var47 < 0L ? -1 : 1;
                  } else {
                     var81 = var44.sweeps() ? 1 : 0;
                     long var48 = 97515289672269L;
                     int var69 = var48 < 0L ? -1 : 1;
                     if (var81 != 0) {
                        var41.bindTexture("Sampler0", var44.texture().comp_5226(), var44.texture().comp_5227());
                        var81 = -1942793574;
                        var41.drawIndexed(var42, 0, var44.count() / 4 * 6, var3);
                        var69 = -1942803185;
                        HandMovementSupport4.metric("drawn");
                     }

                     var42 += var44.count();
                  }
               }
            } catch (Throwable var57) {
               if (var41 != null) {
                  try {
                     var41.close();
                  } catch (Throwable var52) {
                     var57.addSuppressed(var52);
                  }
               }

               throw var57;
            }

            if (var41 != null) {
               var41.close();
            }

            if (a) {
               d(var4, var22, var19.field_1482, var19.field_1481);
               var41 = var23.createRenderPass(() -> "Purxiy hand trail mask", B, OptionalInt.of(0));

               try {
                  var41.setPipeline(HandMovementSupport3.C);
                  var41.setUniform("PurxiyHandTrail", tp);
                  var41.bindTexture("Sampler2", var38, var39);
                  var41.setVertexBuffer(0, G);
                  var41.setIndexBuffer(var37, var64.method_31924());
                  int var74 = 0;

                  for (HandMovementRenderer.Data var78 : var25) {
                     long var79 = 97515289672270L;
                     int var89 = var79 < 0L ? -1 : 1;
                     long var83 = 97515289672270L;
                     int var62 = var83 < 0L ? -1 : 1;
                     if (var78.hand() != var0) {
                        long var49 = 97515289672269L;
                        int var49x = var49 < 0L ? -1 : 1;
                     } else {
                        var41.bindTexture("Sampler0", var78.texture().comp_5226(), var78.texture().comp_5227());
                        var62 = -1942793577;
                        var41.drawIndexed(var74, 0, var78.count() / 4 * 6, 1);
                        int var70 = 1942786770;
                        HandMovementSupport4.metric("drawn");
                        var74 += var78.count();
                     }
                  }
               } catch (Throwable var56) {
                  if (var41 != null) {
                     try {
                        var41.close();
                     } catch (Throwable var51) {
                        var56.addSuppressed(var51);
                     }
                  }

                  throw var56;
               }

               if (var41 != null) {
                  var41.close();
               }
            }

            class_12137 var73x = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            RenderPass var75 = a
               ? var23.createRenderPass(() -> "Purxiy hand trail composite", var20, OptionalInt.empty())
               : var23.createRenderPass(() -> "Purxiy hand trail composite", var20, OptionalInt.empty(), var21, OptionalDouble.empty());

            try {
               var75.setPipeline(a ? HandMovementSupport3.a : HandMovementSupport3.b);
               long var77 = 97515289672269L;
               int var841 = var77 < 0L ? -1 : 1;
               var75.setUniform("PurxiyHandTrail", tp);
               long var80 = 97515289672269L;
               int var71 = var80 < 0L ? -1 : 1;
               var71 = -1942793594;
               int var84x = -1942791859;
               var75.bindTexture("TrailSampler", v, var39);
               if (a) {
                  var75.bindTexture("MaskSampler", B, var73x);
               }

               long var85 = 97515289672269L;
               int var100 = var85 < 0L ? -1 : 1;
               var75.draw(0, 6);
               HandMovementSupport4.metric("drawn");
            } catch (Throwable var55) {
               if (var75 != null) {
                  try {
                     var75.close();
                  } catch (Throwable var50) {
                     var55.addSuppressed(var50);
                  }
               }

               throw var55;
            }

            if (var75 != null) {
               var75.close();
            }

            if (var40 != null) {
               var23.timerQueryEnd(var40);
               m = var40;
            }
         }
      }
   }

   private static void e(CommandEncoder var0, long var1, int var3) {
      short var4 = 14898;
      SwingStyle.ensureSourceClassInitialized();
      HandMovementModule var5 = HandMovementSupport4.y;
      float[] var6 = new float[9];
      float var7 = var5.K.enabled() ? ClientRegistry16.handLight(var6) : 0.0F;
      Std140Builder var8 = Std140Builder.intoBuffer(o.clear())
         .putMat4f(W)
         .putMat4f(t)
         .putVec4(var6[0], var6[1], var6[2], var7)
         .putVec4(var6[3], var6[4], var6[5], var5.D.floatValue())
         .putVec4(var6[6], var6[7], var6[8], (float)(HandMovementSupport4.clock() % 3600.0))
         .putVec4(j, b, var3, 0.0F)
         .putVec4(A & 65535, A >>> 16 & 65535, 0.0F, 0.0F)
         .putVec4(K(T[0] - 14.0F, j), K(T[1] - 14.0F, b), K(T[2] + 14.0F, j), K(T[3] + 14.0F, b));

      for (int var9 = 0; var9 < 160; var9++) {
         long var10 = 93870937847261L;
         int var12 = var10 < 0L ? -1 : 1;
         var8.putMat4f(w[var9]);
      }

      long var14 = 93870937847262L;
      int var15 = var14 < 0L ? -1 : 1;

      for (int var11x = 0; var11x < 160; var11x++) {
         var8.putVec4(p[var11x * 2], p[var11x * 2 + 1], 0.0F, 0.0F);
         long var12 = 93870937847261L;
         int var14x = var12 < 0L ? -1 : 1;
      }

      long var15x = 93870937847262L;
      int var13 = var15x < 0L ? -1 : 1;
      var0.writeToBuffer(tp.slice(), var8.get());
   }

   private static float K(float var0, int var1) {
      return Math.clamp(var0 / var1 * 2.0F - 1.0F, -1.0F, 1.0F);
   }

   private static boolean J(long var0) {
      SwingStyle.ensureSourceClassInitialized();
      GpuDevice var2 = RenderSystem.getDevice();
      if (var2.precompilePipeline(HandMovementSupport3.d).isValid()) {
         boolean var3 = var2.precompilePipeline(HandMovementSupport3.b).isValid();
         long var4 = 112520080515020L;
         int var6 = var4 < 0L ? -1 : 1;
         if (var3 && (!a || var2.precompilePipeline(HandMovementSupport3.C).isValid() && var2.precompilePipeline(HandMovementSupport3.a).isValid())) {
            return true;
         }
      }

      return false;
   }

   private static void U(long var0, GpuDevice var2, int var3, int var4) {
      long var5 = 100885178554073L;
      SwingStyle.ensureSourceClassInitialized();
      if (u != null) {
         long var7 = 64702733296325L;
         int var9 = var7 < 0L ? -1 : 1;
         long var10 = 64702733296324L;
         int var12 = var10 < 0L ? -1 : 1;
         if (j == var3) {
            long var12x = 64702733296325L;
            int var13 = var12x < 0L ? -1 : 1;
            if (b == var4) {
               return;
            }
         }
      }

      W(var5);
      byte var14 = 12;
      int var8 = (var3 + 1) / 2;
      int var9 = (var4 + 1) / 2;
      u = var2.createTexture(() -> "Purxiy hand trail", var14, TextureFormat.RGBA8, var8, var9, 1, 1);
      S = var2.createTexture(() -> "Purxiy hand trail depth", var14, TextureFormat.DEPTH32, var8, var9, 1, 1);
      v = var2.createTextureView(u);
      K = var2.createTextureView(S);
      j = var3;
      b = var4;
   }

   private static void d(long var0, GpuDevice var2, int var3, int var4) {
      SwingStyle.ensureSourceClassInitialized();
      if (H != null) {
         long var5 = 36368324360322L;
         int var7 = var5 < 0L ? -1 : 1;
         if (F == var3 && Q == var4) {
            return;
         }
      }

      if (H != null) {
         B.close();
         H.close();
      }

      H = var2.createTexture(() -> "Purxiy hand trail mask", 12, TextureFormat.RED8, var3, var4, 1, 1);
      B = var2.createTextureView(H);
      F = var3;
      Q = var4;
   }

   private static void W(long var0) {
      SwingStyle.ensureSourceClassInitialized();
      long var2 = 100885178554072L;
      int var4 = var2 < 0L ? -1 : 1;
      if (u != null) {
         v.close();
         K.close();
         u.close();
         S.close();
         S = null;
         u = null;
         K = null;
         v = null;
      }
   }

   private static void I(GpuDevice var0, long var1) {
      SwingStyle.ensureSourceClassInitialized();
      if (tp == null) {
         tp = var0.createBuffer(ClientServices.label(() -> "Purxiy hand trail uniforms"), 136, 13024L);
         o = MemoryUtil.memAlloc(13024);
      }
   }

   private static void C(GpuDevice var0, long var1, long var3) {
      SwingStyle.ensureSourceClassInitialized();
      if (G != null) {
         long var5;
         int var7 = (var5 = G.size() - var1) == 0L ? 0 : (var5 < 0L ? -1 : 1);
         long var8 = 5695755005688L;
         int var10 = var8 < 0L ? -1 : 1;
         if (var7 >= 0) {
            return;
         }
      }

      long var11;
      for (var11 = 65536L; var11 < var1; var11 <<= 1) {
         long var12 = 5695755005687L;
         int var9 = var12 < 0L ? -1 : 1;
      }

      long var13 = 5695755005687L;
      int var14 = var13 < 0L ? -1 : 1;
      if (G != null) {
         G.close();
      }

      G = var0.createBuffer(ClientServices.label(() -> "Purxiy hand trail vertices"), 40, var11);
   }

   public static void setTiming(boolean var0) {
      C = var0;
      M = 0.0;
      c = 0.0;
      x = 0;
      h = 0;
   }

   private static GpuQuery v(long var0, CommandEncoder var2, int var3) {
      SwingStyle.ensureSourceClassInitialized();
      if (m != null) {
         OptionalLong var4 = m.getValue();
         long var5 = 33872103603112L;
         int var8 = var5 < 0L ? -1 : 1;
         if (var4.isEmpty()) {
            return null;
         }

         m.close();
         m = null;
         c = c + var4.getAsLong() / 1000000.0;
         var8 = ++h;
         long var9 = 33872103603112L;
         int var10 = var9 < 0L ? -1 : 1;
         if (var8 == 120) {
            ClientServices.LOGGER
               .info(
                  "Purxiy hand trail: {} ms CPU, {} ms GPU per frame ({} moments on average, {}x{})",
                  new Object[]{String.format(Locale.ROOT, "%.3f", M / h), String.format(Locale.ROOT, "%.3f", c / h), x / h, j, b}
               );
            M = 0.0;
            c = 0.0;
            x = 0;
            h = 0;
         }
      }

      return var2.timerQueryBegin();
   }

   private static void r(Throwable var0, long var1) {
      SwingStyle.ensureSourceClassInitialized();
      String var3 = var0.toString();
      long var4 = 23484432233684L;
      int var7 = var4 < 0L ? -1 : 1;
      long var7x = 23484432233684L;
      int var8 = var7x < 0L ? -1 : 1;
      if (!var3.equals(P)) {
         P = var3;
         ClientServices.LOGGER.error("Purxiy hand trail failed", var0);
      }
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (v != null) {
            v.close();
         }
      } catch (Exception var275) {
         var0++;
      } finally {
         v = null;
      }

      try {
         if (K != null) {
            K.close();
         }
      } catch (Exception var273) {
         var0++;
      } finally {
         K = null;
      }

      try {
         if (B != null) {
            B.close();
         }
      } catch (Exception var271) {
         var0++;
      } finally {
         B = null;
      }

      try {
         if (m != null) {
            m.close();
         }
      } catch (Exception var269) {
         var0++;
      } finally {
         m = null;
      }

      try {
         if (G != null) {
            G.close();
         }
      } catch (Exception var267) {
         var0++;
      } finally {
         G = null;
      }

      try {
         if (tp != null) {
            tp.close();
         }
      } catch (Exception var265) {
         var0++;
      } finally {
         tp = null;
      }

      try {
         if (u != null) {
            u.close();
         }
      } catch (Exception var263) {
         var0++;
      } finally {
         u = null;
      }

      try {
         if (S != null) {
            S.close();
         }
      } catch (Exception var261) {
         var0++;
      } finally {
         S = null;
      }

      try {
         if (H != null) {
            H.close();
         }
      } catch (Exception var259) {
         var0++;
      } finally {
         H = null;
      }

      try {
         if (o != null) {
            MemoryUtil.memFree(o);
         }
      } catch (Exception var257) {
         var0++;
      } finally {
         o = null;
      }

      try {
         if (z != null) {
            z.close();
         }
      } catch (Exception var255) {
         var0++;
      } finally {
         ;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

   static void frameEnded() {
      Arrays.fill(t7, null);
      d.reset();
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ 562076493 ^ 4567) & 32767;
      if (fb[var3] == null) {
         fb[var3] = eb[var3] ^ 7164311750177758029L;
      }

      return fb[var3];
   }

   static {
      for (int var0 = 0; var0 < w.length; var0++) {
         w[var0] = new Matrix4f();
      }

      p = new float[320];
      Z = new SwingStyle.TypeJ();
      I = new Matrix4f();
      g = new Matrix4f();
      X = new class_4587();
      y = new float[6];
      T = new float[4];
      q = new Vector4f();
      h = 0;
      x = 0;
      eb = new long[]{8981928284204814134L, 7164311750177823565L};
      fb = new Long[2];
   }
}
