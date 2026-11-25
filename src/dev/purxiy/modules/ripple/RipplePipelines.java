package dev.purxiy.modules.ripple;

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

final class RipplePipelines {
   static final RenderPipeline V = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/ripple_depth"))
      .withVertexShader(ClientServices.id("core/ripple_body"))
      .withFragmentShader(ClientServices.id("core/ripple_body"))
      .withShaderDefine("DEPTH")
      .withUniform("PurxiyRipple", class_10789.field_60031)
      .withSampler("Sampler0")
      .withSampler("DepthSampler")
      .withoutBlend()
      .withColorWrite(false)
      .withDepthWrite(true)
      .withDepthTestFunction(DepthTestFunction.LESS_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_1577, class_5596.field_27382)
      .build();
   static final RenderPipeline E = W("ripple_light", 136578456358819L, false);
   static final RenderPipeline o = W("ripple_light_masked", 136578456358819L, true);
   static final RenderPipeline c = v("ripple_fx", 3598168609603L, false);
   static final RenderPipeline S = v("ripple_fx_masked", 3598168609603L, true);

   private RipplePipelines() {
   }

   private static RenderPipeline W(String var0, long var1, boolean var3) {
      Builder var4 = RenderPipeline.builder(new Snippet[0])
         .withLocation(ClientServices.id("pipeline/" + var0))
         .withVertexShader(ClientServices.id("core/ripple_body"))
         .withFragmentShader(ClientServices.id("core/ripple_body"))
         .withUniform("Fog", class_10789.field_60031)
         .withUniform("PurxiyRipple", class_10789.field_60031)
         .withSampler("Sampler0")
         .withSampler("DepthSampler");
      if (var3) {
         var4.withShaderDefine("MASKED").withSampler("HandMaskSampler");
      }

      return var4.withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
         .withDepthWrite(false)
         .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(class_290.field_1577, class_5596.field_27382)
         .build();
   }

   private static RenderPipeline v(String var0, long var1, boolean var3) {
      Builder var4 = RenderPipeline.builder(new Snippet[0])
         .withLocation(ClientServices.id("pipeline/" + var0))
         .withVertexShader(ClientServices.id("core/ripple_fx"))
         .withFragmentShader(ClientServices.id("core/ripple_fx"))
         .withUniform("Fog", class_10789.field_60031)
         .withUniform("PurxiyRippleFx", class_10789.field_60031)
         .withSampler("DepthSampler");
      if (var3) {
         var4.withShaderDefine("MASKED").withSampler("HandMaskSampler");
      }

      return var4.withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
         .withDepthWrite(false)
         .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(class_290.field_60033, class_5596.field_27379)
         .build();
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (V != null) {
            ClientCache.release(V);
         }
      } catch (Exception var65) {
         var0++;
      } finally {
         ;
      }

      try {
         if (E != null) {
            ClientCache.release(E);
         }
      } catch (Exception var63) {
         var0++;
      } finally {
         ;
      }

      try {
         if (o != null) {
            ClientCache.release(o);
         }
      } catch (Exception var61) {
         var0++;
      } finally {
         ;
      }

      try {
         if (c != null) {
            ClientCache.release(c);
         }
      } catch (Exception var59) {
         var0++;
      } finally {
         ;
      }

      try {
         if (S != null) {
            ClientCache.release(S);
         }
      } catch (Exception var57) {
         var0++;
      } finally {
         ;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }
}
