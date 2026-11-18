// Sourced By DevFFNightmare
package dev.purxiy.modules.weather;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.render.pipeline.SkyVertexFormat;
import net.minecraft.class_10789;

final class WeatherSupport4 {
   static final RenderPipeline c = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/weather_particles"))
      .withVertexShader(ClientServices.id("core/weather_particles"))
      .withFragmentShader(ClientServices.id("core/weather_particles"))
      .withUniform("DynamicTransforms", class_10789.field_60031)
      .withUniform("Projection", class_10789.field_60031)
      .withUniform("Fog", class_10789.field_60031)
      .withUniform("PurxiyWeatherParticles", class_10789.field_60031)
      .withSampler("DepthSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(SkyVertexFormat.AMBIENCE, class_5596.field_27382)
      .build();
   static final RenderPipeline r = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/weather_particles_masked"))
      .withVertexShader(ClientServices.id("core/weather_particles"))
      .withFragmentShader(ClientServices.id("core/weather_particles"))
      .withShaderDefine("MASKED")
      .withUniform("DynamicTransforms", class_10789.field_60031)
      .withUniform("Projection", class_10789.field_60031)
      .withUniform("Fog", class_10789.field_60031)
      .withUniform("PurxiyWeatherParticles", class_10789.field_60031)
      .withSampler("DepthSampler")
      .withSampler("HandMaskSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(SkyVertexFormat.AMBIENCE, class_5596.field_27382)
      .build();



   private WeatherSupport4() {
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (c != null) {
            ClientCache.release(c);
         }
      } catch (Exception var14) {
         var0++;
      } finally {
         ;
      }

      try {
         if (r != null) {
            ClientCache.release(r);
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
