package dev.purxiy.modules.glass;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline.Snippet;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import net.minecraft.class_10789;
import net.minecraft.class_290;

final class GlassSupport3 {
   private static final int v = 0;
   private static final int o = 1;
   private static final int b = 2;
   static final RenderPipeline y = m("glass_back_world", 136851550076892L, 0, true);
   static final RenderPipeline A = m("glass_back_hand", 136851550076892L, 0, false);
   static final RenderPipeline d = m("glass_depth_world", 136851550076892L, 1, true);
   static final RenderPipeline L = m("glass_depth_hand", 136851550076892L, 1, false);
   static final RenderPipeline a = m("glass_world", 136851550076892L, 2, true);
   static final RenderPipeline F = m("glass_hand", 136851550076892L, 2, false);
   static final RenderPipeline M = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/glass_halo_source"))
      .withVertexShader(ClientServices.id("core/glass_halo"))
      .withFragmentShader(ClientServices.id("core/glass_halo"))
      .withShaderDefine("SOURCE")
      .withUniform("PurxiyGlass", class_10789.field_60031)
      .withSampler("GlassDepthSampler")
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   static final RenderPipeline j = I('\u0000', "glass_halo_blur_x", "BLUR_X", 354493939, '女');
   static final RenderPipeline B = I('\u0000', "glass_halo_blur_y", "BLUR_Y", 354493939, '女');
   static final RenderPipeline w = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/glass_halo"))
      .withVertexShader(ClientServices.id("core/glass_halo"))
      .withFragmentShader(ClientServices.id("core/glass_halo"))
      .withUniform("PurxiyGlass", class_10789.field_60031)
      .withSampler("GlassDepthSampler")
      .withSampler("HaloSampler")
      .withBlend(BlendFunction.ADDITIVE)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   static final RenderPipeline I = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/glass_stage"))
      .withVertexShader(ClientServices.id("core/glass_halo"))
      .withFragmentShader(ClientServices.id("core/glass_stage"))
      .withUniform("GlassStage", class_10789.field_60031)
      .withUniform("PurxiyTheme", class_10789.field_60031)
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();

   private GlassSupport3() {
   }

   private static RenderPipeline I(char var0, String var1, String var2, int var3, char var4) {
      return RenderPipeline.builder(new Snippet[0])
         .withLocation(ClientServices.id("pipeline/" + var1))
         .withVertexShader(ClientServices.id("core/glass_halo"))
         .withFragmentShader(ClientServices.id("core/glass_halo"))
         .withShaderDefine(var2)
         .withSampler("HaloSampler")
         .withoutBlend()
         .withDepthWrite(false)
         .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(class_290.field_60033, class_5596.field_27379)
         .build();
   }

   private static RenderPipeline m(String var0, long var1, int var3, boolean var4) {
      Builder var5 = RenderPipeline.builder(new Snippet[0])
         .withLocation(ClientServices.id("pipeline/" + var0))
         .withVertexShader(ClientServices.id("core/glass"))
         .withFragmentShader(ClientServices.id("core/glass"))
         .withUniform("PurxiyGlass", class_10789.field_60031)
         .withSampler("Sampler0");
      long var6 = 136851550076891L;
      int var9 = var6 < 0L ? -1 : 1;
      if (var4) {
         var5.withShaderDefine("WORLD").withSampler("DepthSampler");
      }

      long var9x = 136851550076892L;
      int var11 = var9x < 0L ? -1 : 1;
      if (var3 == 1) {
         return var5.withShaderDefine("DEPTH")
            .withoutBlend()
            .withColorWrite(false)
            .withDepthWrite(true)
            .withDepthTestFunction(DepthTestFunction.LESS_DEPTH_TEST)
            .withCull(false)
            .withVertexFormat(GlassRenderer.l, class_5596.field_27382)
            .build();
      } else {
         long var11x = 136851550076892L;
         int var13 = var11x < 0L ? -1 : 1;
         if (var4) {
            var5.withUniform("Fog", class_10789.field_60031);
         }

         Builder var13x = var5.withSampler("Sampler2");
         long var14 = 136851550076891L;
         int var17 = var14 < 0L ? -1 : 1;
         var13x.withDepthWrite(false);
         if (var3 == 0) {
            var5.withShaderDefine("BACK").withBlend(BlendFunction.ADDITIVE).withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST);
            long var17x = 136851550076891L;
            int var17x = var17x < 0L ? -1 : 1;
         } else {
            var5.withSampler("SceneSampler").withSampler("FrostSampler").withoutBlend().withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST);
         }

         return var5.withCull(false).withVertexFormat(GlassRenderer.l, class_5596.field_27382).build();
      }
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (y != null) {
            ClientCache.release(y);
         }
      } catch (Exception var275) {
         var0++;
      } finally {
         ;
      }

      try {
         if (A != null) {
            ClientCache.release(A);
         }
      } catch (Exception var273) {
         var0++;
      } finally {
         ;
      }

      try {
         if (d != null) {
            ClientCache.release(d);
         }
      } catch (Exception var271) {
         var0++;
      } finally {
         ;
      }

      try {
         if (L != null) {
            ClientCache.release(L);
         }
      } catch (Exception var269) {
         var0++;
      } finally {
         ;
      }

      try {
         if (a != null) {
            ClientCache.release(a);
         }
      } catch (Exception var267) {
         var0++;
      } finally {
         ;
      }

      try {
         if (F != null) {
            ClientCache.release(F);
         }
      } catch (Exception var265) {
         var0++;
      } finally {
         ;
      }

      try {
         if (M != null) {
            ClientCache.release(M);
         }
      } catch (Exception var263) {
         var0++;
      } finally {
         ;
      }

      try {
         if (j != null) {
            ClientCache.release(j);
         }
      } catch (Exception var261) {
         var0++;
      } finally {
         ;
      }

      try {
         if (B != null) {
            ClientCache.release(B);
         }
      } catch (Exception var259) {
         var0++;
      } finally {
         ;
      }

      try {
         if (w != null) {
            ClientCache.release(w);
         }
      } catch (Exception var257) {
         var0++;
      } finally {
         ;
      }

      try {
         if (I != null) {
            ClientCache.release(I);
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
}
