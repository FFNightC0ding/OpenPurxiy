package dev.purxiy.modules.weather;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Snippet;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.assets.IrisCompat;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import java.nio.ByteBuffer;
import java.util.OptionalInt;
import net.minecraft.class_10789;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_5636;
import net.minecraft.class_638;
import net.minecraft.class_9909;
import net.minecraft.class_9916;
import net.minecraft.class_9925;
import net.minecraft.class_9960;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryUtil;

public final class WeatherAtmospherePass {
   private static final RenderPipeline Z = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/weather_atmosphere"))
      .withVertexShader(ClientServices.id("core/weather_atmosphere"))
      .withFragmentShader(ClientServices.id("core/weather_atmosphere"))
      .withUniform("Fog", class_10789.field_60031)
      .withUniform("WeatherAtmosphere", class_10789.field_60031)
      .withSampler("DepthSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static final Matrix4f q = new Matrix4f();
   private static GpuBuffer t;
   private static ByteBuffer H;
   private static long D = 0L;
   private static float p;
   private static float n;
   private static float o;
   private static float i;
   private static float E;
   private static class_638 h;
   private static String W;
   private static final long e = 128L;

   private WeatherAtmospherePass() {
   }

   public static void capture(Matrix4f var0, Matrix4f var1) {
      q.set(var1).mul(var0).invert();
   }

   public static void addPass(class_9909 var0, class_9960 var1, GpuBufferSlice var2) {
      if (!ModuleWidget.claimed("mirror-capture") && !IrisCompat.inUse()) {
         class_310 var3 = class_310.method_1551();
         long var4 = System.nanoTime();
         float var6 = D == 0L ? 0.0F : Math.min(0.1F, (float)(var4 - D) * 1.0E-9F);
         D = var4;
         if (h != var3.field_1687) {
            E = 0.0F;
            i = 0.0F;
            o = 0.0F;
            n = 0.0F;
            h = var3.field_1687;
         }

         if (h != null) {
            if (var3.method_1493()) {
               var6 = 0.0F;
            }

            p += var6;
            WeatherModule var7 = WeatherParticlePass.K;
            boolean var8 = var7.enabled() && var3.field_1773.method_19418().method_19334() == class_5636.field_27888;
            float var9 = 1.0F - (float)Math.exp(-var6 * 1.6);
            n = n + ((var8 && var7.t.is("Rain") ? 1 : 0) - n) * var9;
            o = o + ((var8 && var7.t.is("Snow") ? 1 : 0) - o) * var9;
            i = i + ((var8 && var7.t.is("Fire") ? 1 : 0) - i) * var9;
            E = E + ((var8 && var7.t.is("Rain") && var7.j.enabled() ? 1 : 0) - E) * var9;
            if (!(n + o + i < 0.001)) {
               class_9916 var10 = var0.method_61911("purxiy_weather_atmosphere");
               var1.field_53091 = var10.method_61933(var1.field_53091);
               class_9925 var11 = var1.field_53091;
               var10.method_61929(
                  () -> {
                     WeatherModule.ensureSourceClassInitialized();

                     try {
                        class_276 var4x = (class_276)var11.get();
                        GpuTextureView var5 = var4x.method_71639();
                        GpuTextureView var6x = var4x.method_71640();
                        if (var5 == null || var6x == null) {
                           return;
                        }

                        GpuDevice var7x = RenderSystem.getDevice();
                        long var8x = 2844693217707L;
                        int var11x = var8x < 0L ? -1 : 1;
                        boolean var11xx = var7x.precompilePipeline(Z).isValid();
                        long var12 = 2844693217708L;
                        int var14 = var12 < 0L ? -1 : 1;
                        if (!var11xx) {
                           W = "Atmosphere shader compilation failed";
                           return;
                        }

                        if (t == null) {
                           t = var7x.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX weather sky"), 136, 128L);
                           H = MemoryUtil.memAlloc(128);
                        }

                        class_243 var14x = var3.field_1773.method_19418().method_71156();
                        ByteBuffer var15 = Std140Builder.intoBuffer(H.clear())
                           .putMat4f(q)
                           .putVec4(var5.getWidth(0), var5.getHeight(0), p, WeatherParticlePass.flash())
                           .putVec4(n, o, i, E)
                           .putVec4(
                              (float)(var14x.field_1352 % 4096.0),
                              (float)var14x.field_1351,
                              (float)(var14x.field_1350 % 4096.0),
                              Math.max(196.0F, (float)var14x.field_1351 + 45.0F)
                           )
                           .putVec4(Math.max(0.18F, 1.0F - h.method_8594() / 15.0F), var7.O.floatValue(), SkyPipelineRouter.skyPresence(), 0.0F)
                           .get();
                        CommandEncoder var16 = var7x.createCommandEncoder();
                        var16.writeToBuffer(t.slice(), var15);
                        RenderPass var17 = var16.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX weather atmosphere", var5, OptionalInt.empty());

                        try {
                           long var18 = 2844693217707L;
                           int var20 = var18 < 0L ? -1 : 1;
                           var17.setPipeline(Z);
                           var17.setUniform("Fog", var2);
                           var17.setUniform("WeatherAtmosphere", t);
                           var17.bindTexture("DepthSampler", var6x, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
                           long var20x = 2844693217707L;
                           int var21 = var20x < 0L ? -1 : 1;
                           var17.draw(0, 3);
                        } catch (Throwable var23) {
                           if (var17 != null) {
                              try {
                                 var17.close();
                              } catch (Throwable var22) {
                                 var23.addSuppressed(var22);
                              }
                           }

                           throw var23;
                        }

                        if (var17 != null) {
                           var17.close();
                        }
                     } catch (Exception var241) {
                        if (!var241.toString().equals(W)) {
                           ClientServices.LOGGER.error("Weather atmosphere rendering failed", var241);
                        }

                        W = var241.toString();
                     }
                  }
               );
            }
         }
      }
   }

   public static String lastError() {
      return W;
   }

   public static float storminess() {
      return E * (1.0F - SkyPipelineRouter.skyPresence());
   }

   public static boolean hideVanillaClouds() {
      return storminess() > 0.02F && W == null;
   }

   public static void close() {
      if (t != null) {
         t.close();
         t = null;
      }

      if (H != null) {
         MemoryUtil.memFree(H);
         H = null;
      }

      h = null;
      E = 0.0F;
      i = 0.0F;
      o = 0.0F;
      n = 0.0F;
      D = 0L;
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (Z != null) {
            ClientCache.release(Z);
         }
      } catch (Exception var27) {
         var0++;
      } finally {
         ;
      }

      try {
         if (t != null) {
            t.close();
         }
      } catch (Exception var25) {
         var0++;
      } finally {
         t = null;
      }

      try {
         if (H != null) {
            MemoryUtil.memFree(H);
         }
      } catch (Exception var23) {
         var0++;
      } finally {
         H = null;
      }

      close();
      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }
}
