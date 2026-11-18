// Sourced By DevFFNightmare
package dev.purxiy.modules.knife;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import net.minecraft.class_10789;
import net.minecraft.class_290;

final class KnifeSupport4 {
   static final RenderPipeline W = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/wield_arc"))
      .withVertexShader(ClientServices.id("core/wield_arc"))
      .withFragmentShader(ClientServices.id("core/wield_arc"))
      .withUniform("PurxiyWield", class_10789.field_60031)
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_1575, class_5596.field_27382)
      .build();
   static final RenderPipeline U = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/wield_arc_masked"))
      .withVertexShader(ClientServices.id("core/wield_arc"))
      .withFragmentShader(ClientServices.id("core/wield_arc"))
      .withShaderDefine("MASKED")
      .withUniform("PurxiyWield", class_10789.field_60031)
      .withSampler("MaskSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_1575, class_5596.field_27382)
      .build();



   private KnifeSupport4() {
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (W != null) {
            ClientCache.release(W);
         }
      } catch (Exception var14) {
         var0++;
      } finally {
         ;
      }

      try {
         if (U != null) {
            ClientCache.release(U);
         }
      } catch (Exception var12) {
         var0++;
      } finally {
         ;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

}
