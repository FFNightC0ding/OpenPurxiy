// Sourced By DevFFNightmare
package dev.purxiy.modules.halo;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Builder;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.OptionalLong;
import net.minecraft.class_10789;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_6367;

import org.lwjgl.system.MemoryUtil;

final class HaloSupport3 {
   private static final RenderPipeline T = Y(126885296655136L, "halo", false);
   private static final RenderPipeline d = Y(126885296655136L, "halo_preview", true);
   
   private static GpuBuffer y;
   
   private static GpuBuffer C;
   
   private static ByteBuffer w;
   
   private static String p;
   private static boolean r;
   
   private static GpuQuery g;
   private static double l;
   private static int f = 0;
   private final HaloSupport2 E = new HaloSupport2();


   private static final long[] c = new long[]{499709474441024531L, 3826374139569241758L};


   private static RenderPipeline Y(long var0, String var2, boolean var3) {
      Builder var4 = RenderPipeline.builder()
         .withLocation(ClientServices.id("pipeline/" + var2))
         .withVertexShader(ClientServices.id("core/halo"))
         .withFragmentShader(ClientServices.id("core/halo"))
         .withUniform("PurxiyHalo", class_10789.field_60031)
         .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
         .withDepthWrite(false)
         .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(class_290.field_60033, class_5596.field_27379);
      long var5 = 126885296655135L;
      if (126885296655135L == 0L) {
         boolean var10000 = false;
      } else {
         int var10 = var5 < 0L ? -1 : 1;
      }

      if (var3) {
         var4.withShaderDefine("HALO_PREVIEW");
         long var8 = 126885296655136L;
         if (126885296655136L == 0L) {
            boolean var11 = false;
         } else {
            int var12 = var8 < 0L ? -1 : 1;
         }
      } else {
         var4.withUniform("Fog", class_10789.field_60031).withSampler("DepthSampler");
      }

      return var4.build();
   }

   private static boolean z(boolean var0, long var1) {
      return RenderSystem.getDevice().precompilePipeline(var0 ? d : T).isValid();
   }

   private static ByteBuffer v(long var0, short var2) {
      HaloDesign.ensureSourceClassInitialized();
      if (w == null) {
         w = MemoryUtil.memAlloc(15984);
      }

      return w.clear();
   }

   void drawWorld(HaloSupport7 var1, HaloSupport2.TypeK var2, GpuBufferSlice var3) {
      long var4 = 104775348508810L;
      long var6 = (var4 ^ 135714889112192L) >>> 32;
      int var8 = (int)((var4 ^ 135714889112192L) << 32 >>> 32);
      long var9 = (var4 ^ 7106786919204L) >>> 16;
      int var11 = (int)((var4 ^ 7106786919204L) << 48 >>> 48);
      long var12 = var4 ^ 109223109171410L;
      long var14 = var4 ^ 75242018774934L;
      class_310 var16 = class_310.method_1551();
      class_276 var17 = var16.method_1522();
      GpuTextureView var18 = var17.method_71639();
      GpuTextureView var19 = var17.method_71640();
      if (var18 != null && var19 != null && z(false, var12)) {
         var2.Y = var17.field_1482;
         var2.c = var17.field_1481;
         var2.R = true;
         if (this.E.project(var1, var2)) {
            try {
               if (y == null) {
                  y = RenderSystem.getDevice().createBuffer(ClientServices.label(() -> "Purxiy halo"), 136, 15984L);
               }

               ByteBuffer var20 = this.E.pack(var2, v(var9, (short)var11));
               CommandEncoder var21 = RenderSystem.getDevice().createCommandEncoder();
               var21.writeToBuffer(y.slice(0L, var20.remaining()), var20);
               GpuQuery var22 = r ? F(var6, var21, var8) : null;

               try (RenderPass var23 = var21.createRenderPass(() -> "Purxiy halo", var18, OptionalInt.empty())) {
                  var23.setPipeline(T);
                  RenderSystem.bindDefaultUniforms(var23);
                  var23.setUniform("Fog", var3);
                  var23.setUniform("PurxiyHalo", y);
                  var23.bindTexture("DepthSampler", var19, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
                  var23.draw(0, 6);
                  HaloRenderer7.metric("drawn");
               } finally {
                  if (var22 != null) {
                     var21.timerQueryEnd(var22);
                     g = var22;
                  }
               }

               p = null;
            } catch (RuntimeException var34) {
               O(var14, var34);
            }
         }
      }
   }

   void drawPreview(HaloSupport7 var1, HaloSupport2.TypeK var2, class_6367 var3) {
      long var4 = 84617954258800L;
      long var6 = (var4 ^ 23956857140446L) >>> 16;
      int var8 = (int)((var4 ^ 23956857140446L) << 48 >>> 48);
      long var9 = var4 ^ 124146883800872L;
      long var11 = var4 ^ 96559114627180L;
      GpuTextureView var13 = var3.method_71639();
      if (var13 != null && z(true, var9)) {
         var2.Y = var3.field_1482;
         var2.c = var3.field_1481;
         var2.R = false;

         try {
            if (C == null) {
               C = RenderSystem.getDevice().createBuffer(ClientServices.label(() -> "Purxiy halo preview"), 136, 15984L);
            }

            boolean var14 = this.E.project(var1, var2);
            CommandEncoder var15 = RenderSystem.getDevice().createCommandEncoder();
            if (var14) {
               ByteBuffer var16 = this.E.pack(var2, v(var6, (short)var8));
               var15.writeToBuffer(C.slice(0L, var16.remaining()), var16);
            }

            try (RenderPass var22 = var15.createRenderPass(() -> "Purxiy halo preview", var13, OptionalInt.of(0))) {
               if (var14) {
                  var22.setPipeline(d);
                  var22.setUniform("PurxiyHalo", C);
                  var22.draw(0, 6);
                  HaloRenderer7.metric("drawn");
               }
            }

            p = null;
         } catch (RuntimeException var21) {
            O(var11, var21);
         }
      }
   }

   String stats() {
      return this.E.stats();
   }

   private static void O(long var0, RuntimeException var2) {
      HaloDesign.ensureSourceClassInitialized();
      String var3 = var2.toString();
      long var4 = 29842718377755L;
      if (29842718377755L == 0L) {
         boolean var10000 = false;
      } else {
         int var9 = var4 < 0L ? -1 : 1;
      }

      long var7 = 29842718377756L;
      if (29842718377756L == 0L) {
         boolean var10 = false;
      } else {
         int var11 = var7 < 0L ? -1 : 1;
      }

      if (!var3.equals(p)) {
         p = var3;
         ClientServices.LOGGER.error("Purxiy halo could not render", var2);
      }
   }

   static void timing(boolean var0) {
      r = var0;
      l = 0.0;
      f = 0;
   }

   
   private static GpuQuery F(long var0, CommandEncoder var2, int var3) {
      HaloDesign.ensureSourceClassInitialized();
      if (g != null) {
         OptionalLong var4 = g.getValue();
         int var5 = 2091663882;
         if (var4.isEmpty()) {
            return null;
         }

         g.close();
         g = null;
         l = l + var4.getAsLong() / 1000000.0;
         int var6 = f + 1;
         var5 = 2091663882;
         f = var6;
         if (var6 == 240) {
            ClientServices.LOGGER.info("Purxiy halo: {} ms GPU per frame", String.format(Locale.ROOT, "%.3f", l / f));
            l = 0.0;
            f = 0;
         }
      }

      return var2.timerQueryBegin();
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (T != null) {
            ClientCache.release(T);
         }
      } catch (Exception var90) {
         var0++;
      } finally {
         ;
      }

      try {
         if (d != null) {
            ClientCache.release(d);
         }
      } catch (Exception var88) {
         var0++;
      } finally {
         ;
      }

      try {
         if (g != null) {
            g.close();
         }
      } catch (Exception var86) {
         var0++;
      } finally {
         g = null;
      }

      try {
         if (y != null) {
            y.close();
         }
      } catch (Exception var84) {
         var0++;
      } finally {
         y = null;
      }

      try {
         if (C != null) {
            C.close();
         }
      } catch (Exception var82) {
         var0++;
      } finally {
         C = null;
      }

      try {
         if (w != null) {
            MemoryUtil.memFree(w);
         }
      } catch (Exception var80) {
         var0++;
      } finally {
         w = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

}
