package dev.purxiy.modules.hand;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Snippet;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import net.minecraft.class_10789;
import net.minecraft.class_290;

final class HandMovementSupport3 {
   static final RenderPipeline d = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/hand_trail"))
      .withVertexShader(ClientServices.id("core/hand_trail"))
      .withFragmentShader(ClientServices.id("core/hand_trail"))
      .withUniform("PurxiyHandTrail", class_10789.field_60031)
      .withSampler("Sampler0")
      .withSampler("Sampler2")
      .withoutBlend()
      .withDepthWrite(true)
      .withDepthTestFunction(DepthTestFunction.LESS_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_1577, class_5596.field_27382)
      .build();
   static final RenderPipeline b = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/hand_trail_composite"))
      .withVertexShader(ClientServices.id("core/hand_trail_composite"))
      .withFragmentShader(ClientServices.id("core/hand_trail_composite"))
      .withUniform("PurxiyHandTrail", class_10789.field_60031)
      .withSampler("TrailSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   static final RenderPipeline C = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/hand_trail_mask"))
      .withVertexShader(ClientServices.id("core/hand_trail"))
      .withFragmentShader(ClientServices.id("core/hand_trail"))
      .withShaderDefine("MASK")
      .withUniform("PurxiyHandTrail", class_10789.field_60031)
      .withSampler("Sampler0")
      .withSampler("Sampler2")
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_1577, class_5596.field_27382)
      .build();
   static final RenderPipeline a = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/hand_trail_composite_masked"))
      .withVertexShader(ClientServices.id("core/hand_trail_composite"))
      .withFragmentShader(ClientServices.id("core/hand_trail_composite"))
      .withShaderDefine("MASKED")
      .withUniform("PurxiyHandTrail", class_10789.field_60031)
      .withSampler("TrailSampler")
      .withSampler("MaskSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();

   private HandMovementSupport3() {
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (d != null) {
            ClientCache.release(d);
         }
      } catch (Exception var44) {
         var0++;
      } finally {
         ;
      }

      try {
         if (b != null) {
            ClientCache.release(b);
         }
      } catch (Exception var42) {
         var0++;
      } finally {
         ;
      }

      try {
         if (C != null) {
            ClientCache.release(C);
         }
      } catch (Exception var40) {
         var0++;
      } finally {
         ;
      }

      try {
         if (a != null) {
            ClientCache.release(a);
         }
      } catch (Exception var38) {
         var0++;
      } finally {
         ;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }
}
