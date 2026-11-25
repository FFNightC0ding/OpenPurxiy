package dev.purxiy.modules.jumpcircles;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Snippet;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_10789;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_742;
import net.minecraft.class_746;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.lwjgl.system.MemoryUtil;

public final class JumpCirclesRenderPass {
   private static final int P = 32;
   private static final int Q = 1216;
   private static final double D = 0.015;
   private static final float l = 0.14F;
   private static final float m = 0.034F;
   private static final float h = 0.26F;
   private static final float b = 0.18F;
   private static final double i = 64.0;
   private static final RenderPipeline C = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/jump_circles"))
      .withVertexShader(ClientServices.id("core/jump_circles"))
      .withFragmentShader(ClientServices.id("core/jump_circles"))
      .withUniform("Fog", class_10789.field_60031)
      .withUniform("PurxiyJumpCircles", class_10789.field_60031)
      .withSampler("DepthSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static final List<JumpCirclesRenderPass.JumpRing> u = new ArrayList<>();
   private static final Int2ObjectMap<JumpCirclesRenderPass.JumpRingBatch> y = new Int2ObjectOpenHashMap();
   private static class_638 N;
   private static boolean A;
   private static final Matrix4f d = new Matrix4f();
   private static final Matrix4f f = new Matrix4f();
   private static double j;
   private static double x;
   private static double T;
   private static GpuBufferSlice r;
   private static GpuBuffer z;
   private static ByteBuffer W;
   private static String K;
   private static float V = -1.0F;
   private static boolean M;
   private static long L = 0L;
   private static boolean k;
   private static boolean I;
   private static GpuQuery O;
   private static double F;
   private static int e = 0;
   static JumpCirclesModule g;
   private static ModuleContext Y;
   private static int S = 0;
   private static int v = 0;
   public static boolean J;
   private static final String[] c = new String[18];
   private static final long p = 1216L;

   private JumpCirclesRenderPass() {
   }

   public static void onJump(class_1309 var0) {
      long var1 = 12787724189338L;
      long var3 = var1 ^ 84153045180786L;
      class_310 var5 = class_310.method_1551();
      if (var0 == var5.field_1724 && g.enabled() && !var0.method_7325()) {
         T(var3, var0.method_23317(), var0.method_23318(), var0.method_23321());
      }
   }

   public static void preview(class_1309 var0, double var1, double var3) {
      long var5 = 20679203342220L;
      long var7 = var5 ^ 94450475685988L;
      T(var7, var0.method_23317() + var1, var0.method_23318(), var0.method_23321() + var3);
   }

   private static void T(long var0, double var2, double var4, double var6) {
      long var8 = 78238057275368L;
      int var10 = var8 < 0L ? -1 : 1;
      if (u.size() >= 32) {
         u.removeFirst();
      }

      u.add(new JumpCirclesRenderPass.JumpRing(var2, var4, var6, System.nanoTime(), ThreadLocalRandom.current().nextFloat()));
   }

   private static void C(class_310 var0, long var1, class_638 var3) {
      long var4 = 20792930762848L;
      if (g.z.enabled() && var0.field_1724 != null) {
         int var6 = 0;
         int var7 = 0;

         for (class_742 var9 : var3.method_18456()) {
            long var10 = 123501019755668L;
            int var121 = var10 < 0L ? -1 : 1;
            if (var9 != var0.field_1724) {
               M(var0.field_1724, var9, var4);
               var6++;
            }
         }

         var7 = y.size();
         long var14 = 123501019755669L;
         int var15 = var14 < 0L ? -1 : 1;
         if (var7 > var6) {
            y.values().removeIf(var0x -> !var0x.D);
         }

         ObjectIterator var11 = y.values().iterator();

         while (var11.hasNext()) {
            JumpCirclesRenderPass.JumpRingBatch var12 = (JumpCirclesRenderPass.JumpRingBatch)var11.next();
            var12.D = false;
         }
      } else {
         y.clear();
      }
   }

   private static void M(class_746 var0, class_1309 var1, long var2) {
      long var4 = 78238057275368L;
      JumpCirclesRenderPass.JumpRingBatch var6 = (JumpCirclesRenderPass.JumpRingBatch)y.get(var1.method_5628());
      long var7 = 20792930762847L;
      int var10 = var7 < 0L ? -1 : 1;
      if (var6 == null) {
         y.put(var1.method_5628(), new JumpCirclesRenderPass.JumpRingBatch(var1));
      } else {
         var6.D = true;
         if (var1.field_6012 != var6.K) {
            var6.K = var1.field_6012;
            boolean var10x = var1.method_24828();
            if (var10x) {
               long var11 = 20792930762848L;
               int var13 = var11 < 0L ? -1 : 1;
               var6.stand(var1);
            } else if (var6.d) {
               var6.y = var1.field_6012;
               var6.z = true;
            }

            var6.d = var10x;
            if (var6.z && !var10x) {
               double var23 = var1.method_23318() - var6.T;
               double var13;
               int var15 = (var13 = var23 - 0.08) == 0.0 ? 0 : (var13 < 0.0 ? -1 : 1);
               long var16 = 20792930762848L;
               int var25 = var16 < 0L ? -1 : 1;
               if (var15 > 0) {
                  var6.z = false;
                  boolean var18 = var1.method_5756(var0)
                     || var1.method_7325()
                     || var1.method_5765()
                     || var1.method_5799()
                     || var1.method_6128()
                     || var1.field_6235 > 0
                     || var1.method_5858(var0) > 4096.0;
                  long var19 = 20792930762847L;
                  int var201 = var19 < 0L ? -1 : 1;
                  if (!var18) {
                     T(var4, var6.S, var6.T, var6.b);
                  }
               } else {
                  double var25x;
                  var15 = (var25x = var23 - -0.08) == 0.0 ? 0 : (var25x < 0.0 ? -1 : 1);
                  long var20 = 20792930762847L;
                  int var21 = var20 < 0L ? -1 : 1;
                  if (var15 < 0 || var1.field_6012 - var6.y > 4) {
                     var6.z = false;
                  }
               }
            }
         }
      }
   }

   public static void clear() {
      u.clear();
      y.clear();
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2, GpuBufferSlice var3) {
      A = g.enabled();
      if (A) {
         d.set(var2).mul(var1);
         d.invert(f);
         class_243 var4 = var0.method_71156();
         j = var4.field_1352;
         x = var4.field_1351;
         T = var4.field_1350;
         r = var3;
      }
   }

   public static void render() {
      long var0 = 87306216548809L;
      int var2 = (int)((var0 ^ 109099063781320L) >>> 32);
      long var3 = (var0 ^ 109099063781320L) << 32 >>> 32;
      long var5 = var0 ^ 69497980411228L;
      boolean var7 = A;
      A = false;
      class_310 var8 = class_310.method_1551();
      class_638 var9 = var8.field_1687;
      if (var9 != N) {
         N = var9;
         clear();
      }

      if (var7 && var9 != null) {
         C(var8, var5, var9);
         long var10 = System.nanoTime();
         float var12 = g.c.floatValue();
         u.removeIf(var3x -> {
            long var4 = 109495214072925L;
            return y(var3x, var10, var12, var4) >= 1.0F;
         });
         if (!u.isEmpty() && RenderSystem.getDevice().precompilePipeline(C).isValid()) {
            try {
               d(var8, var2, var10, var3, var12);
            } catch (Throwable var15) {
               metric("draw_failures");
               String var14 = var15.toString();
               if (!var14.equals(K)) {
                  K = var14;
                  ClientServices.LOGGER.error("SourceByDevFFnightmare DeObfDeCompByGuideX jump circles failed", var15);
               }
            }
         }
      } else if (!u.isEmpty() || !y.isEmpty()) {
         clear();
      }
   }

   private static void d(class_310 var0, int var1, long var2, long var4, float var6) {
      long var7 = 48782422511105L;
      long var9 = var7 ^ 87737015331420L;
      long var11 = var7 ^ 10520300768445L;
      var11 = var7 ^ 123709732189355L;
      long var13 = var7 ^ 101145372903704L;
      class_276 var15 = var0.method_1522();
      GpuTextureView var16 = var15.method_71639();
      GpuTextureView var17 = var15.method_71640();
      if (var16 != null && var17 != null && r != null) {
         JumpCirclesModule var18 = g;
         JumpCirclesLoader var19 = JumpCirclesLoader.resolve((String)var18.G.get(), var18.u.argb());
         float var20 = var18.r.floatValue();
         float var21 = var18.U.floatValue();
         float var22 = 0.034F * (float)Math.sqrt(var20 / 1.2F);
         float var23 = 0.26F * (0.35F + 0.65F * var21) * (float)Math.sqrt(var20 / 1.2F);
         if (z == null) {
            z = RenderSystem.getDevice().createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX jump circles"), 136, 1216L);
            W = MemoryUtil.memAlloc(1216);
         }

         Std140Builder var24 = Std140Builder.intoBuffer(W.clear())
            .putMat4f(d)
            .putMat4f(f)
            .putVec4(var15.field_1482, var15.field_1481, u.size(), var23)
            .putVec4(X(var19.r, 16, var13), X(var19.r, 8, var13), X(var19.r, 0, var13), var21)
            .putVec4(X(var19.h, 16, var13), X(var19.h, 8, var13), X(var19.h, 0, var13), 0.0F)
            .putVec4(X(var19.P, 16, var13), X(var19.P, 8, var13), X(var19.P, 0, var13), 0.0F);

         for (int var25 = 0; var25 < 32; var25++) {
            long var26 = 183963136L;
            int var46 = var26 < 0L ? -1 : 1;
            if (var25 >= u.size()) {
               var24.putVec4(0.0F, 0.0F, 0.0F, 0.0F).putVec4(0.0F, 0.0F, 0.0F, 0.0F);
               long var29 = 183963136L;
               int var48 = var29 < 0L ? -1 : 1;
            } else {
               JumpCirclesRenderPass.JumpRing var45 = u.get(var25);
               float var30 = y(var45, var2, var6, var9);
               float var31 = 1.0F - (1.0F - var30) * (1.0F - var30) * (1.0F - var30);
               float var32 = var20 * (0.14F + 0.86F * var31);
               float var33 = J(0.0F, 0.07F, var30);
               float var34 = 1.0F - J(0.38F, 1.0F, var30);
               var24.putVec4((float)(var45.G - j), (float)(var45.B + 0.015 - x), (float)(var45.X - T), var32)
                  .putVec4(var22 * 0.5F * (1.0F - 0.5F * var30), var33 * var34, var30, var45.z + 0.18F * var31);
            }
         }

         GpuDevice var43 = RenderSystem.getDevice();
         long var44 = 183963137L;
         int var461 = var44 < 0L ? -1 : 1;
         CommandEncoder var46x = var43.createCommandEncoder();
         var46x.writeToBuffer(z.slice(), var24.get());
         Object var47 = null;
         boolean var48 = false;

         try {
            var48 = true;
            RenderPass var49 = var46x.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX jump circles", var16, OptionalInt.empty());

            try {
               var49.setPipeline(C);
               RenderSystem.bindDefaultUniforms(var49);
               var49.setUniform("Fog", r);
               var49.setUniform("PurxiyJumpCircles", z);
               var49.bindTexture("DepthSampler", var17, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
               var49.draw(0, u.size() * 6);
               metric("drawn");
            } catch (Throwable var39) {
               if (var49 != null) {
                  try {
                     var49.close();
                  } catch (Throwable var38) {
                     var39.addSuppressed(var38);
                  }
               }

               throw var39;
            }

            if (var49 != null) {
               var49.close();
            }
         } finally {
            if (var48 && var47 != null) {
               var46x.timerQueryEnd((GpuQuery)var47);
               O = (GpuQuery)var47;
            }
         }

         if (var47 != null) {
            var46x.timerQueryEnd((GpuQuery)var47);
            O = (GpuQuery)var47;
         }
      }
   }

   private static GpuQuery K(short var0, CommandEncoder var1, int var2, short var3) {
      if (O != null) {
         OptionalLong var4 = O.getValue();
         if (var4.isEmpty()) {
            return null;
         }

         O.close();
         O = null;
         F = F + var4.getAsLong() / 1000000.0;
         if (++e == 240) {
            ClientServices.LOGGER.info("SourceByDevFFnightmare DeObfDeCompByGuideX jump circles: {} ms GPU per frame ({} rings)", String.format(Locale.ROOT, "%.3f", F / e), u.size());
            F = 0.0;
            e = 0;
         }
      }

      return var1.timerQueryBegin();
   }

   private static float y(JumpCirclesRenderPass.JumpRing var0, long var1, float var3, long var4) {
      long var6 = 109495214072925L;
      int var8 = var6 < 0L ? -1 : 1;
      return V >= 0.0F ? V : (float)(var1 - var0.h) / 1.0E9F / var3;
   }

   private static float J(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   private static float X(int var0, int var1, long var2) {
      return (var0 >>> var1 & 0xFF) / 255.0F;
   }

   public static void install(JumpCirclesModule var0, ModuleContext var1) {
      g = var0;
      Y = var1;
   }

   public static void metric(String var0) {
      if (Y != null) {
         Y.metric(var0, var0.equals("drawn") ? ++S : ++v);
      }
   }

   public static void uninstall() {
      int var0 = 0;

      try {
         if (C != null) {
            ClientCache.release(C);
         }
      } catch (Exception var44) {
         var0++;
      } finally {
         ;
      }

      try {
         if (O != null) {
            O.close();
         }
      } catch (Exception var42) {
         var0++;
      } finally {
         O = null;
      }

      try {
         if (z != null) {
            z.close();
         }
      } catch (Exception var40) {
         var0++;
      } finally {
         z = null;
      }

      try {
         if (W != null) {
            MemoryUtil.memFree(W);
         }
      } catch (Exception var38) {
         var0++;
      } finally {
         W = null;
      }

      clear();
      N = null;
      A = false;
      g = null;
      Y = null;
      if (var0 != 0) {
         throw new IllegalStateException("World visual GPU cleanup failed");
      }
   }

   public static void ensureSourceClassInitialized() {
   }

   private record JumpRing(double G, double B, double X, long h, float z) {
      public double x() {
         return this.G;
      }

      public double y() {
         return this.B;
      }

      public double zz() {
         return this.X;
      }

      public long born() {
         return this.h;
      }

      public float spin() {
         return this.z;
      }

      public double doubleState14() {
         return this.G;
      }

      public double doubleState43() {
         return this.B;
      }

      public double doubleState19() {
         return this.X;
      }

      public long longState32() {
         return this.h;
      }

      public float floatState19() {
         return this.z;
      }
   }

   private static final class JumpRingBatch {
      double S;
      double T;
      double b;
      boolean d;
      boolean z;
      int y;
      int K;
      boolean D = true;

      JumpRingBatch(class_1309 var1) {
         this.stand(var1);
         this.d = var1.method_24828();
         this.K = var1.field_6012;
      }

      void stand(class_1309 var1) {
         this.S = var1.method_23317();
         this.T = var1.method_23318();
         this.b = var1.method_23321();
         this.z = false;
      }
   }
}
