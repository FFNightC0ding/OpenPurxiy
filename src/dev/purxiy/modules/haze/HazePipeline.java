package dev.purxiy.modules.haze;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline.Snippet;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Random;
import net.minecraft.class_10789;
import net.minecraft.class_12137;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_1011.class_1012;
import org.lwjgl.system.MemoryUtil;

final class HazePipeline {
   static final int h = 240;
   private static final int e = 256;
   private static final RenderPipeline y = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/haze_march"))
      .withVertexShader(ClientServices.id("core/haze"))
      .withFragmentShader(ClientServices.id("core/haze_march"))
      .withUniform("PurxiyHaze", class_10789.field_60031)
      .withSampler("DepthSampler")
      .withSampler("NoiseSampler")
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static final RenderPipeline[] a = new RenderPipeline[4];
   private static GpuBuffer Q;
   private static ByteBuffer g;
   private static GpuTexture A;
   private static GpuTexture d;
   private static GpuTexture i;
   private static GpuTexture L;
   private static GpuTexture f;
   private static GpuTextureView w;
   private static GpuTextureView D;
   private static GpuTextureView o;
   private static GpuTextureView F;
   private static GpuTextureView I;
   private static int l = 0;
   private static int b = 0;
   private static int j = 0;
   private static int G = 0;
   private static boolean z;
   private static GpuQuery M;
   private static double m;
   private static int E = 0;
   private static final String[] k = new String[40];
   private static final long[] n = new long[]{
      5176597688010484016L,
      8537215903770921855L,
      -4328480904663744886L,
      -395186893656805491L,
      -767112496818017660L,
      -9196211938881249654L,
      -323187200523065301L,
      5840393620286682900L
   };
   private static final long q = 240L;

   private HazePipeline() {
   }

   private static RenderPipeline R(boolean var0, boolean var1, long var2) {
      HazeRenderPass.ensureSourceClassInitialized();
      int var4 = (var0 ? 1 : 0) | (var1 ? 2 : 0);
      RenderPipeline var5 = a[var4];
      if (var5 != null) {
         return var5;
      } else {
         Builder var6 = RenderPipeline.builder(new Snippet[0]);
         long var7 = 130415999562877L;
         int var10 = var7 < 0L ? -1 : 1;
         String var10x = var0 ? "_creatures" : "";
         long var11 = 130415999562877L;
         int var14 = var11 < 0L ? -1 : 1;
         String var14x = var1 ? "_masked" : "";
         Builder var16 = var6.withLocation(ClientServices.id("pipeline/haze" + var10x + var14x))
            .withVertexShader(ClientServices.id("core/haze"))
            .withFragmentShader(ClientServices.id("core/haze"))
            .withUniform("PurxiyHaze", class_10789.field_60031)
            .withUniform("Fog", class_10789.field_60031)
            .withSampler("SceneSampler")
            .withSampler("DepthSampler")
            .withSampler("HazeSampler")
            .withSampler("NoiseSampler");
         if (var0) {
            var16.withShaderDefine("CREATURES").withSampler("TerrainDepthSampler").withSampler("OpaqueDepthSampler");
         }

         if (var1) {
            var16.withShaderDefine("MASKED").withSampler("HandMaskSampler");
         }

         var5 = var16.withoutBlend()
            .withDepthWrite(false)
            .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
            .withCull(false)
            .withVertexFormat(class_290.field_60033, class_5596.field_27379)
            .build();
         a[var4] = var5;
         return var5;
      }
   }

   static void setTiming(boolean var0) {
      z = var0;
      m = 0.0;
      E = 0;
   }

   static void keepDepth(boolean var0) {
      long var1 = 107849959669289L;
      long var3 = var1 ^ 87870270332375L;
      class_276 var5 = class_310.method_1551().method_1522();
      GpuTexture var6 = var5.method_30278();
      if (var6 != null) {
         GpuDevice var7 = RenderSystem.getDevice();
         G(var7, var5.field_1482, var3, var5.field_1481);
         var7.createCommandEncoder().copyTextureToTexture(var6, var0 ? f : L, 0, 0, 0, 0, 0, var5.field_1482, var5.field_1481);
      }
   }

   static void draw(class_310 var0) {
      long var1 = 103724423045932L;
      long var3 = var1 ^ 44852044650322L;
      long var5 = var1 ^ 45834069311762L;
      long var7 = var1 ^ 9695988168542L;
      long var9 = var1 ^ 85373687088622L;
      class_276 var11 = var0.method_1522();
      GpuTexture var12 = var11.method_30277();
      GpuTextureView var13 = var11.method_71639();
      GpuTextureView var14 = var11.method_71640();
      if (var12 != null && var13 != null && var14 != null && HazeRenderPass.n != null) {
         GpuDevice var15 = RenderSystem.getDevice();
         GpuTextureView var16 = HazeRenderPass.k ? SkyPipelineRouter.handMask() : null;
         int var17 = var11.field_1482;
         int var18 = var11.field_1481;
         boolean var19 = HazeRenderPass.D > 0.001F && HazeRenderPass.P && HazeRenderPass.q && L != null && j == var17 && G == var18;
         RenderPipeline var20 = R(var19, var16 != null, var3);
         if (var15.precompilePipeline(y).isValid() && var15.precompilePipeline(var20).isValid()) {
            l(var15, var17, var18, var5);
            CommandEncoder var21 = var15.createCommandEncoder();
            var21.writeToBuffer(Q.slice(), L(var0, var7, var17, var18, var19));
            class_12137 var22 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            class_12137 var23 = RenderSystem.getSamplerCache().method_75297(FilterMode.LINEAR);
            GpuQuery var24 = z ? q(var21, var9) : null;
            RenderPass var25 = var21.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX haze march", o, OptionalInt.empty());

            try {
               var25.setPipeline(y);
               RenderSystem.bindDefaultUniforms(var25);
               var25.setUniform("PurxiyHaze", Q);
               var25.bindTexture("DepthSampler", var14, var22);
               var25.bindTexture("NoiseSampler", w, var23);
               var25.draw(0, 3);
               HazeRenderPass.metric("drawn");
            } catch (Throwable var36) {
               if (var25 != null) {
                  try {
                     var25.close();
                  } catch (Throwable var35) {
                     var36.addSuppressed(var35);
                  }
               }

               throw var36;
            }

            if (var25 != null) {
               var25.close();
            }

            var21.copyTextureToTexture(var12, d, 0, 0, 0, 0, 0, var17, var18);

            try {
               var25 = var21.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX haze", var13, OptionalInt.empty());

               try {
                  var25.setPipeline(var20);
                  RenderSystem.bindDefaultUniforms(var25);
                  var25.setUniform("PurxiyHaze", Q);
                  var25.setUniform("Fog", HazeRenderPass.n);
                  var25.bindTexture("SceneSampler", D, var22);
                  var25.bindTexture("DepthSampler", var14, var22);
                  var25.bindTexture("HazeSampler", o, var22);
                  var25.bindTexture("NoiseSampler", w, var23);
                  if (var19) {
                     var25.bindTexture("TerrainDepthSampler", F, var22);
                     var25.bindTexture("OpaqueDepthSampler", I, var22);
                  }

                  if (var16 != null) {
                     var25.bindTexture("HandMaskSampler", var16, var22);
                  }

                  var25.draw(0, 3);
                  HazeRenderPass.metric("drawn");
               } catch (Throwable var37) {
                  if (var25 != null) {
                     try {
                        var25.close();
                     } catch (Throwable var34) {
                        var37.addSuppressed(var34);
                     }
                  }

                  throw var37;
               }

               if (var25 != null) {
                  var25.close();
               }
            } finally {
               if (var24 != null) {
                  var21.timerQueryEnd(var24);
                  M = var24;
               }
            }
         }
      }
   }

   private static ByteBuffer L(class_310 var0, long var1, int var3, int var4, boolean var5) {
      double var6 = HazeRenderPass.Q + HazeRenderPass.v;
      double var8 = HazeRenderPass.Z + HazeRenderPass.s;
      HazeRenderPass.ensureSourceClassInitialized();
      float var10 = (float)(var6 - Math.floor(var6 / 32768.0) * 32768.0);
      float var11 = (float)(var8 - Math.floor(var8 / 32768.0) * 32768.0);
      float var12 = Math.max(HazeRenderPass.F, 1.0F);
      float var13 = HazeRenderPass.A * HazeRenderPass.c[5];
      float var14 = Math.clamp(var0.field_1690.method_38521() * 16.0F, 64.0F, 512.0F);
      Std140Builder var15 = Std140Builder.intoBuffer(g.clear()).putMat4f(HazeRenderPass.i);
      float var16 = var3;
      float var17 = var4;
      float var18 = (float)(HazeRenderPass.U % 3600.0);
      HazeRenderPass.ensureSourceClassInitialized();
      var15 = var15.putVec4(var16, var17, var18, 0.0F);
      var17 = (float)HazeRenderPass.o;
      long var19 = 95140983985265L;
      int var21 = var19 < 0L ? -1 : 1;
      return var15.putVec4(var10, var17, var11, HazeRenderPass.O)
         .putVec4(HazeRenderPass.sigma(), 1.0F / var12, HazeRenderPass.c[1], 1.0F / (var12 * HazeRenderPass.c[2]))
         .putVec4(HazeRenderPass.clearRadius(), HazeRenderPass.clearRadius() + 20.0F, HazeRenderPass.S, 180.0F)
         .putVec4(HazeRenderPass.j, HazeRenderPass.N, var14, HazeRenderPass.l)
         .putVec4(HazeRenderPass.R[0], HazeRenderPass.R[1], HazeRenderPass.R[2], 0.0F)
         .putVec4(HazeRenderPass.m[0], HazeRenderPass.m[1], HazeRenderPass.m[2], 0.0F)
         .putVec4(HazeRenderPass.J[0], HazeRenderPass.J[1], HazeRenderPass.J[2], HazeRenderPass.b)
         .putVec4(HazeRenderPass.z7[0] * var13, HazeRenderPass.z7[1] * var13, HazeRenderPass.z7[2] * var13, HazeRenderPass.y)
         .putVec4(HazeRenderPass.I.x, HazeRenderPass.I.y, HazeRenderPass.I.z, 0.0F)
         .putVec4(HazeRenderPass.D, var5 ? 1.0F : 0.0F, 0.0F, 0.0F)
         .get();
   }

   private static void l(GpuDevice var0, int var1, int var2, long var3) {
      HazeRenderPass.ensureSourceClassInitialized();
      if (Q == null) {
         Q = var0.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX haze"), 136, 240L);
         g = MemoryUtil.memAlloc(240);
         A = var0.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX haze noise", 5, TextureFormat.RGBA8, 256, 256, 1, 1);
         w = var0.createTextureView(A);
         ByteBuffer var5 = noiseTexels();
         var0.createCommandEncoder().writeToTexture(A, var5, class_1012.field_4997, 0, 0, 0, 0, 256, 256);
         MemoryUtil.memFree(var5);
      }

      long var10 = 131914183238206L;
      int var8 = var10 < 0L ? -1 : 1;
      if (d != null) {
         long var8x = 131914183238206L;
         int var9 = var8x < 0L ? -1 : 1;
         if (l == var1 && b == var2) {
            return;
         }
      }

      if (d != null) {
         D.close();
         d.close();
         o.close();
         i.close();
      }

      d = var0.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX haze scene", 5, TextureFormat.RGBA8, var1, var2, 1, 1);
      D = var0.createTextureView(d);
      i = var0.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX haze march", 12, TextureFormat.RGBA8, (var1 + 1) / 2, (var2 + 1) / 2, 1, 1);
      o = var0.createTextureView(i);
      l = var1;
      b = var2;
   }

   private static void G(GpuDevice var0, int var1, long var2, int var4) {
      HazeRenderPass.ensureSourceClassInitialized();
      if (L != null) {
         long var5 = 50560937436157L;
         int var7 = var5 < 0L ? -1 : 1;
         if (j == var1 && G == var4) {
            return;
         }
      }

      if (L != null) {
         F.close();
         L.close();
         I.close();
         f.close();
      }

      L = var0.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX haze terrain depth", 5, TextureFormat.DEPTH32, var1, var4, 1, 1);
      F = var0.createTextureView(L);
      f = var0.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX haze opaque depth", 5, TextureFormat.DEPTH32, var1, var4, 1, 1);
      I = var0.createTextureView(f);
      j = var1;
      G = var4;
   }

   static ByteBuffer noiseTexels() {
      byte[] var0 = new byte[262144];
      new Random(1751218789L).nextBytes(var0);
      ByteBuffer var1 = MemoryUtil.memAlloc(var0.length);
      var1.put(var0).clear();
      return var1;
   }

   private static GpuQuery q(CommandEncoder var0, long var1) {
      HazeRenderPass.ensureSourceClassInitialized();
      if (M != null) {
         OptionalLong var3 = M.getValue();
         long var4 = 21937168918209L;
         int var7 = var4 < 0L ? -1 : 1;
         if (var3.isEmpty()) {
            return null;
         }

         M.close();
         M = null;
         m = m + var3.getAsLong() / 1000000.0;
         long var7x = 21937168918210L;
         int var8 = var7x < 0L ? -1 : 1;
         if (++E == 240) {
            ClientServices.LOGGER.info("SourceByDevFFnightmare DeObfDeCompByGuideX haze: {} ms GPU per frame at {}x{}", new Object[]{String.format(Locale.ROOT, "%.3f", m / E), l, b});
            m = 0.0;
            E = 0;
         }
      }

      return var0.timerQueryBegin();
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (y != null) {
            ClientCache.release(y);
         }
      } catch (Exception var495) {
         var0++;
      } finally {
         ;
      }

      for (int var1 = 0; var1 < a.length; var1++) {
         try {
            if (a[var1] != null) {
               ClientCache.release(a[var1]);
            }
         } catch (Exception var493) {
            var0++;
         } finally {
            a[var1] = null;
         }
      }

      try {
         if (w != null) {
            w.close();
         }
      } catch (Exception var491) {
         var0++;
      } finally {
         w = null;
      }

      try {
         if (D != null) {
            D.close();
         }
      } catch (Exception var489) {
         var0++;
      } finally {
         D = null;
      }

      try {
         if (o != null) {
            o.close();
         }
      } catch (Exception var487) {
         var0++;
      } finally {
         o = null;
      }

      try {
         if (F != null) {
            F.close();
         }
      } catch (Exception var485) {
         var0++;
      } finally {
         F = null;
      }

      try {
         if (I != null) {
            I.close();
         }
      } catch (Exception var483) {
         var0++;
      } finally {
         I = null;
      }

      try {
         if (M != null) {
            M.close();
         }
      } catch (Exception var481) {
         var0++;
      } finally {
         M = null;
      }

      try {
         if (Q != null) {
            Q.close();
         }
      } catch (Exception var479) {
         var0++;
      } finally {
         Q = null;
      }

      try {
         if (A != null) {
            A.close();
         }
      } catch (Exception var477) {
         var0++;
      } finally {
         A = null;
      }

      try {
         if (d != null) {
            d.close();
         }
      } catch (Exception var475) {
         var0++;
      } finally {
         d = null;
      }

      try {
         if (i != null) {
            i.close();
         }
      } catch (Exception var473) {
         var0++;
      } finally {
         i = null;
      }

      try {
         if (L != null) {
            L.close();
         }
      } catch (Exception var471) {
         var0++;
      } finally {
         L = null;
      }

      try {
         if (f != null) {
            f.close();
         }
      } catch (Exception var469) {
         var0++;
      } finally {
         f = null;
      }

      try {
         if (g != null) {
            MemoryUtil.memFree(g);
         }
      } catch (Exception var4671) {
         var0++;
      } finally {
         g = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }
}
