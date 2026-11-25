package dev.purxiy.modules.ripple;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.loader.fabric.ClientServices;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.OptionalLong;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_5819;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryUtil;

final class RipplePipeline {
   static final int G = 128;
   private static final int T = 4304;
   private static final int D = 0;
   private static final int W = 1;
   private static final int o = 2;
   private static final int j = 3;
   private static final int V = 4;
   static final float B = 1.7F;
   static final float l = 44.0F;
   static final float a = 1.0F;
   static final float O = 3.4F;
   private static final double h = 64.0;
   private static final List<RipplePipeline.RippleState> I = new ArrayList<>();
   private static final List<RipplePipeline.RippleBuffers> x = new ArrayList<>();
   private static final class_5819 R = class_5819.method_43047();
   private static final float[] Q = new float[1024];
   private static int C = 0;
   private static float d = -1.0F;
   private static GpuBuffer X;
   private static ByteBuffer U;
   private static boolean g;
   private static GpuQuery M;
   private static double i;
   private static int Z = 0;
   private static int A = 0;
   private static final String[] c = new String[13];
   private static final long k = 4304L;

   private RipplePipeline() {
   }

   static void burst(class_243 var0, class_243 var1, class_243 var2, boolean var3, int var4) {
      long var5 = 87385375339391L;
      long var7 = var5 ^ 109793117251644L;
      class_310 var9 = class_310.method_1551();
      if (var9.field_1724 != null && !(var0.method_1025(var9.field_1773.method_19418().method_71156()) > 4096.0)) {
         float var10 = RippleRenderPass.R.u.floatValue();
         class_243 var11 = var1.method_1027() < 1.0E-6 ? var0.method_1020(var2) : var1.method_1021(-1.0);
         class_243 var12 = var11.method_1027() < 1.0E-6 ? new class_243(0.0, 1.0, 0.0) : var11.method_1029();
         class_243 var13 = var0.method_1020(var2).method_18805(1.0, 0.35, 1.0);
         class_243 var14 = var13.method_1027() < 1.0E-6 ? var12 : var13.method_1029();
         class_243 var15 = new class_243(0.0, 1.0, 0.0);
         class_243 var16 = var12.method_1036(var15);
         class_243 var17 = var16.method_1027() < 1.0E-6 ? new class_243(1.0, 0.0, 0.0) : var16.method_1029();
         if (var3) {
            int var18 = Math.max(3, Math.round((9 + R.method_43048(4)) * var10));

            for (int var19 = 0; var19 < var18; var19++) {
               float var20 = (var19 + R.method_43057()) / var18 * 2.0F - 1.0F;
               if (var4 != 0) {
                  var20 = var20 * 0.8F + 0.35F * var4;
               }

               float var21 = -0.1F + R.method_43057() * 0.75F;
               class_243 var22 = var12.method_1021(0.55)
                  .method_1019(var14.method_1021(0.45))
                  .method_1019(var17.method_1021(var20 * 1.1))
                  .method_1019(var15.method_1021(var21))
                  .method_1029();
               RipplePipeline.RippleState var23 = new RipplePipeline.RippleState();
               float var24 = 4.2F + R.method_43057() * 4.2F;
               var23.E = var0.field_1352;
               var23.g = var0.field_1351;
               var23.t = var0.field_1350;
               var23.u = (float)var22.field_1352 * var24;
               var23.L = (float)var22.field_1351 * var24;
               var23.K = (float)var22.field_1350 * var24;
               var23.w = 0.45F + R.method_43057() * 0.3F;
               var23.d = 0.014F + R.method_43057() * 0.012F;
               var23.H = R.method_43057() * 1.57F;
               var23.l = (R.method_43057() - 0.5F) * 5.0F;
               var23.n = R.method_43057();
               d(var7, var23);
            }
         } else {
            int var25 = Math.max(2, Math.round((4 + R.method_43048(3)) * var10));

            for (int var26 = 0; var26 < var25; var26++) {
               RipplePipeline.RippleState var27 = new RipplePipeline.RippleState();
               var27.a = true;
               double var28 = R.method_43057() * Math.PI * 2.0;
               float var29 = 0.12F + R.method_43057() * 0.22F;
               var27.E = var0.field_1352 + Math.cos(var28) * var29 * 0.6;
               var27.g = var0.field_1351 + (R.method_43057() - 0.4F) * 0.3F;
               var27.t = var0.field_1350 + Math.sin(var28) * var29 * 0.6;
               var27.u = (float)(Math.cos(var28) * var29 + var14.field_1352 * 0.5);
               var27.L = 0.45F + R.method_43057() * 0.5F;
               var27.K = (float)(Math.sin(var28) * var29 + var14.field_1350 * 0.5);
               var27.w = 0.6F + R.method_43057() * 0.35F;
               var27.d = 0.008F + R.method_43057() * 0.006F;
               var27.H = R.method_43057() * 1.57F;
               var27.l = (R.method_43057() - 0.5F) * 2.0F;
               var27.n = R.method_43057();
               d(var7, var27);
            }
         }
      }
   }

   private static void d(long var0, RipplePipeline.RippleState var2) {
      RippleRenderer.ensureSourceClassInitialized();
      long var3 = 49077744163650L;
      int var5 = var3 < 0L ? -1 : 1;
      if (I.size() >= 120) {
         I.removeFirst();
      }

      I.add(var2);
   }

   static void kill(double var0, double var2, double var4, float var6, float var7, boolean var8) {
      RipplePipeline.RippleBuffers var9 = new RipplePipeline.RippleBuffers();
      var9.E = var0;
      var9.m = var2;
      var9.X = var4;
      var9.g = Math.max(0.4F, var6);
      var9.N = Math.max(0.3F, var7);
      var9.n = var8;
      var9.T = R.method_43057();
      if (x.size() >= 8) {
         x.removeFirst();
      }

      x.add(var9);
   }

   static void clear() {
      I.clear();
      x.clear();
   }

   static void freeze(float var0) {
      long var1 = 33454807823346L;
      long var3 = var1 ^ 14111456576369L;
      if (var0 >= 0.0F) {
         d = -1.0F;

         for (int var5 = 0; var5 < 600 && I.stream().anyMatch(var1x -> {
            RippleRenderer.ensureSourceClassInitialized();
            return var1x.Q < var0 && var1x.Q < var1x.w - 0.02F;
         }); var5++) {
            float var6 = 0.004166667F;

            for (RipplePipeline.RippleState var8 : I) {
               if (var8.Q < var0 && var8.Q < var8.w - 0.02F) {
                  c(var3, var8, var6);
               }
            }
         }
      }

      d = var0;
   }

   static void setTiming(boolean var0) {
      g = var0;
      i = 0.0;
      A = 0;
      Z = 0;
   }

   static String describe() {
      return I.size() + " stars, " + x.size() + " pillars";
   }

   static void update(float var0) {
      long var1 = 54363663794537L;
      long var3 = var1 ^ 39347796210154L;
      if (!(d >= 0.0F)) {
         Iterator var5 = I.iterator();

         while (var5.hasNext()) {
            RipplePipeline.RippleState var6 = (RipplePipeline.RippleState)var5.next();
            if (var6.Q + var0 >= var6.w) {
               var5.remove();
            } else {
               c(var3, var6, var0);
            }
         }

         var5 = x.iterator();

         while (var5.hasNext()) {
            RipplePipeline.RippleBuffers var8 = (RipplePipeline.RippleBuffers)var5.next();
            var8.z += var0;
            if (var8.z >= Math.max(1.7F, var8.n ? 1.0F : 0.0F)) {
               var5.remove();
            }
         }
      }
   }

   private static void c(long var0, RipplePipeline.RippleState var2, float var3) {
      var2.Q += var3;
      float var4 = (float)Math.exp(-var3 * (var2.a ? 2.2 : 6.5));
      var2.u *= var4;
      var2.K *= var4;
      var2.L = var2.L * var4 - var3 * (var2.a ? -0.15F : 2.2F);
      var2.E = var2.E + var2.u * var3;
      var2.g = var2.g + var2.L * var3;
      var2.t = var2.t + var2.K * var3;
      var2.H = var2.H + var2.l * var3;
   }

   static boolean active() {
      return !I.isEmpty() || !x.isEmpty();
   }

   static void render(Matrix4f var0, Matrix4f var1, GpuBufferSlice var2, GpuTextureView var3) {
      long var4 = 84810028823019L;
      long var6 = var4 ^ 84783681518799L;
      int var8 = (int)((var4 ^ 84783681518799L) >>> 32);
      int var9 = (int)((var4 ^ 84783681518799L) << 32 >>> 48);
      int var10 = (int)(var6 << 48 >>> 48);
      if ((!I.isEmpty() || !x.isEmpty()) && var2 != null) {
         try {
            if (!RenderSystem.getDevice().precompilePipeline(var3 != null ? RipplePipelines.S : RipplePipelines.c).isValid()) {
               return;
            }

            l(var8, var0, (short)var9, var1, (char)var10, var2, var3);
         } catch (Throwable var12) {
            RippleRenderPass.metric("draw_failures");
            RippleRenderPass.fail(var12);
         }
      }
   }

   private static void l(int var0, Matrix4f var1, short var2, Matrix4f var3, char var4, GpuBufferSlice var5, GpuTextureView var6) {
      long var7 = 267942366500L;
      long var9 = var7 ^ 30235284567789L;
      int var11 = (int)((var7 ^ 30235284567789L) >>> 32);
      int var12 = (int)((var7 ^ 30235284567789L) << 32 >>> 48);
      int var13 = (int)(var9 << 48 >>> 48);
      long var14 = var7 ^ 4133340406013L;
      class_310 var16 = class_310.method_1551();
      class_276 var17 = var16.method_1522();
      RippleRenderer.ensureSourceClassInitialized();
      GpuTextureView var18 = var17.method_71639();
      GpuTextureView var19 = var17.method_71640();
      if (var18 != null && var19 != null) {
         class_243 var20 = var16.field_1773.method_19418().method_71156();
         u(var20, var11, (short)var12, (char)var13);
         if (C != 0) {
            if (X == null) {
               X = RenderSystem.getDevice().createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX ripple light"), 136, 4304L);
               U = MemoryUtil.memAlloc(4304);
            }

            float[] var21 = RippleRenderPass.palette();
            float var22 = Math.max(0.75F, var17.field_1481 / 1080.0F);
            Std140Builder var23 = Std140Builder.intoBuffer(U.clear())
               .putMat4f(var1)
               .putMat4f(var3)
               .putVec4(var17.field_1482, var17.field_1481, C, var22)
               .putVec4(RippleRenderPass.glow(), 1.0F, (float)(RippleRenderPass.clock() % 3600.0), RippleRenderPass.focal() * var17.field_1481 * 0.5F)
               .putVec4(var21[0], var21[1], var21[2], 0.0F)
               .putVec4(var21[3], var21[4], var21[5], 0.0F)
               .putVec4(var21[6], var21[7], var21[8], 0.0F);

            for (int var24 = 0; var24 < 256; var24++) {
               int var25 = var24 * 4;
               if (var24 < C * 2) {
                  var23.putVec4(Q[var25], Q[var25 + 1], Q[var25 + 2], Q[var25 + 3]);
               } else {
                  var23.putVec4(0.0F, 0.0F, 0.0F, 0.0F);
               }
            }

            CommandEncoder var38 = RenderSystem.getDevice().createCommandEncoder();
            short var39 = 3364;
            var38.writeToBuffer(X.slice(), var23.get());
            GpuQuery var26 = g ? K(var38, var14) : null;
            boolean var27 = false;

            try {
               var27 = true;
               RenderPass var28 = var38.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX ripple light", var18, OptionalInt.empty());

               try {
                  var28.setPipeline(var6 != null ? RipplePipelines.S : RipplePipelines.c);
                  var39 = 3364;
                  RenderSystem.bindDefaultUniforms(var28);
                  var28.setUniform("Fog", var5);
                  int var29 = 3364;
                  var29 = -1654398716;
                  var28.setUniform("PurxiyRippleFx", X);
                  var28.bindTexture("DepthSampler", var19, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
                  if (var6 != null) {
                     var28.bindTexture("HandMaskSampler", var6, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
                  }

                  var28.draw(0, C * 6);
                  RippleRenderPass.metric("drawn");
               } catch (Throwable var36) {
                  if (var28 != null) {
                     try {
                        var28.close();
                     } catch (Throwable var35) {
                        var36.addSuppressed(var35);
                     }
                  }

                  throw var36;
               }

               if (var28 != null) {
                  var28.close();
               }
            } finally {
               if (var27 && var26 != null) {
                  var38.timerQueryEnd(var26);
                  M = var26;
               }
            }

            if (var26 != null) {
               var38.timerQueryEnd(var26);
               M = var26;
            }
         }
      }
   }

   private static void u(class_243 var0, int var1, short var2, char var3) {
      long var4 = 29969509612489L;
      long var6 = var4 ^ 14997703430612L;
      RippleRenderer.ensureSourceClassInitialized();
      C = 0;
      Iterator var8 = x.iterator();

      while (var8.hasNext()) {
         short var9 = 6977;
         RipplePipeline.RippleBuffers var10 = (RipplePipeline.RippleBuffers)var8.next();
         float var11 = d >= 0.0F ? Math.min(d, 1.69F) : var10.z;
         float var12 = (float)(var10.E - var0.field_1352);
         float var13 = (float)(var10.m - var0.field_1351);
         float var14 = (float)(var10.X - var0.field_1350);
         if (var10.n) {
            float var15;
            int var16 = (var15 = var11 - 1.0F) == 0.0F ? 0 : (var15 < 0.0F ? -1 : 1);
            var9 = 6977;
            if (var16 < 0) {
               M(3, var12, var6, var13 + 0.02F, var14, var10.N * 0.5F + 3.4F, var11 / 1.0F, var10.T, 0.0F);
            }
         }

         if (var11 < 1.7F) {
            float var36 = var11 / 1.7F;
            float var37 = var10.g;
            float var18 = var10.N;
            float var19 = 44.0F;
            M(2, var12, var6, var13, var14, var19, var18, var36, var37);
            float var22;
            int var23 = (var22 = var11 - 0.55F) == 0.0F ? 0 : (var22 < 0.0F ? -1 : 1);
            var9 = -11783;
            if (var23 < 0) {
               float var24 = var13 + var10.g * 0.5F;
               float var25 = var10.g * 0.55F + 0.35F;
               float var26 = var11 / 0.55F;
               var37 = 0.0F;
               float var17 = var10.T;
               M(4, var12, var6, var24, var14, var25, var26, var17, var37);
            }
         }
      }

      short var30 = -11783;

      for (RipplePipeline.RippleState var32 : I) {
         short var33 = 6977;
         var33 = -11783;
         if (C >= 128) {
            break;
         }

         float var35 = d >= 0.0F ? Math.min(d, var32.w - 0.01F) : var32.Q;
         M(
            var32.a ? 1 : 0,
            (float)(var32.E - var0.field_1352),
            var6,
            (float)(var32.g - var0.field_1351),
            (float)(var32.t - var0.field_1350),
            var32.d,
            var35 / var32.w,
            var32.H,
            var32.n
         );
      }

      var30 = -11783;
      if (g) {
         A = A + C;
      }
   }

   private static void M(int var0, float var1, long var2, float var4, float var5, float var6, float var7, float var8, float var9) {
      RippleRenderer.ensureSourceClassInitialized();
      long var10 = 25160945003037L;
      int var13 = var10 < 0L ? -1 : 1;
      long var13x = 25160945003037L;
      int var16 = var13x < 0L ? -1 : 1;
      if (C < 128) {
         var16 = C * 8;
         Q[var16] = var1;
         Q[var16 + 1] = var4;
         Q[var16 + 2] = var5;
         Q[var16 + 3] = var0;
         Q[var16 + 4] = var6;
         Q[var16 + 5] = var7;
         Q[var16 + 6] = var8;
         Q[var16 + 7] = var9;
         C++;
      }
   }

   private static GpuQuery K(CommandEncoder var0, long var1) {
      RippleRenderer.ensureSourceClassInitialized();
      if (M != null) {
         OptionalLong var3 = M.getValue();
         long var4 = 4381887262169L;
         int var7 = var4 < 0L ? -1 : 1;
         if (var3.isEmpty()) {
            return null;
         }

         M.close();
         M = null;
         i = i + var3.getAsLong() / 1000000.0;
         var7 = ++Z;
         long var8 = 4381887262168L;
         int var9 = var8 < 0L ? -1 : 1;
         if (var7 == 120) {
            ClientServices.LOGGER.info("SourceByDevFFnightmare DeObfDeCompByGuideX ripple light: {} ms GPU per frame ({} pieces on average)", String.format(Locale.ROOT, "%.3f", i / Z), A / Z);
            i = 0.0;
            A = 0;
            Z = 0;
         }
      }

      return var0.timerQueryBegin();
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (M != null) {
            M.close();
         }
      } catch (Exception var27) {
         var0++;
      } finally {
         M = null;
      }

      try {
         if (X != null) {
            X.close();
         }
      } catch (Exception var25) {
         var0++;
      } finally {
         X = null;
      }

      try {
         if (U != null) {
            MemoryUtil.memFree(U);
         }
      } catch (Exception var23) {
         var0++;
      } finally {
         U = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

   private static final class RippleBuffers {
      double E;
      double m;
      double X;
      float g;
      float N;
      float z;
      boolean n;
      float T;
   }

   private static final class RippleState {
      double E;
      double g;
      double t;
      float u;
      float L;
      float K;
      float Q;
      float w;
      float d;
      float H;
      float l;
      float n;
      boolean a;
   }
}
