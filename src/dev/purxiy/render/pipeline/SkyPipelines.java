package dev.purxiy.render.pipeline;

import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Builder;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import net.minecraft.class_10789;
import net.minecraft.class_290;

public final class SkyPipelines {
   public static final RenderPipeline UI = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/ui"))
      .withVertexShader(ClientServices.id("core/ui"))
      .withFragmentShader(ClientServices.id("core/ui"))
      .withUniform("PurxiyFrame", class_10789.field_60031)
      .withSampler("Sampler0")
      .withSampler("Sampler1")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(SkyVertexFormat.UI, class_5596.field_27382)
      .build();
   public static final RenderPipeline UI_SHEET = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/ui_sheet"))
      .withVertexShader(ClientServices.id("core/ui"))
      .withFragmentShader(ClientServices.id("core/ui"))
      .withShaderDefine("SHEET")
      .withUniform("PurxiyFrame", class_10789.field_60031)
      .withUniform("PurxiySheet", class_10789.field_60031)
      .withSampler("Sampler0")
      .withSampler("Sampler1")
      .withSampler("DepthSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(SkyVertexFormat.UI, class_5596.field_27382)
      .build();
   public static final RenderPipeline BLUR_DOWN = blur("blur_down");
   public static final RenderPipeline BLUR_UP = blur("blur_up");
   public static final RenderPipeline AMBIENCE = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/ambience"))
      .withVertexShader(ClientServices.id("core/ambience"))
      .withFragmentShader(ClientServices.id("core/ambience"))
      .withUniform("DynamicTransforms", class_10789.field_60031)
      .withUniform("Projection", class_10789.field_60031)
      .withUniform("Fog", class_10789.field_60031)
      .withUniform("PurxiyAmbience", class_10789.field_60031)
      .withSampler("DepthSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(SkyVertexFormat.AMBIENCE, class_5596.field_27382)
      .build();
   public static final RenderPipeline OUTLINE = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/outline"))
      .withVertexShader(ClientServices.id("core/outline"))
      .withFragmentShader(ClientServices.id("core/outline"))
      .withUniform("Fog", class_10789.field_60031)
      .withUniform("PurxiyOutline", class_10789.field_60031)
      .withSampler("DepthSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   public static final RenderPipeline AFTERGLOW = afterglow("afterglow", false, false);
   public static final RenderPipeline AFTERGLOW_TRANSLUCENT = afterglow("afterglow_translucent", true, false);
   public static final RenderPipeline AFTERGLOW_EMISSIVE = afterglow("afterglow_emissive", true, true);
   public static final RenderPipeline RADIANCE_MASK = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/radiance_mask"))
      .withVertexShader(ClientServices.id("core/radiance_mask"))
      .withFragmentShader(ClientServices.id("core/radiance_mask"))
      .withUniform("DynamicTransforms", class_10789.field_60031)
      .withUniform("Projection", class_10789.field_60031)
      .withSampler("Sampler0")
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
      .withDepthBias(-1.0F, -10.0F)
      .withCull(false)
      .withVertexFormat(class_290.field_1585, class_5596.field_27382)
      .build();
   public static final RenderPipeline RADIANCE_SEED = radiance("radiance_seed", "MaskSampler");
   public static final RenderPipeline RADIANCE_FLOOD = radiance("radiance_flood", "FloodSampler");
   public static final RenderPipeline RADIANCE_REFINE = radiance("radiance_refine", "MaskSampler", "FloodSampler");
   public static final RenderPipeline RADIANCE = radiance("radiance", "MaskSampler", "FieldSampler");
   public static final RenderPipeline FIRE = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/fire"))
      .withVertexShader(ClientServices.id("core/fire"))
      .withFragmentShader(ClientServices.id("core/fire"))
      .withUniform("PurxiyFire", class_10789.field_60031)
      .withSampler("PixelSampler")
      .withSampler("SceneSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static final RenderPipeline[] SKY_SPACE = new RenderPipeline[]{buildSky("sky_space", 0), buildSky("sky_space", 1), buildSky("sky_space", 2)};
   private static final RenderPipeline[] SKY = new RenderPipeline[]{buildSky("sky", 0), buildSky("sky", 1), buildSky("sky", 2)};
   private static final RenderPipeline[] SKY_BODIES = new RenderPipeline[]{buildSky("sky_body", 0), buildSky("sky_body", 1), buildSky("sky_body", 2)};

   private SkyPipelines() {
   }

   public static RenderPipeline sky(int var0) {
      return SKY[Math.clamp((long)var0, 0, SKY.length - 1)];
   }

   public static RenderPipeline skySpace(int var0) {
      return SKY_SPACE[Math.clamp((long)var0, 0, SKY_SPACE.length - 1)];
   }

   public static RenderPipeline skyBodies(int var0) {
      return SKY_BODIES[Math.clamp((long)var0, 0, SKY_BODIES.length - 1)];
   }

   private static RenderPipeline buildSky(String var0, int var1) {
      Builder var2 = RenderPipeline.builder()
         .withLocation(ClientServices.id("pipeline/" + var0 + "_" + var1))
         .withVertexShader(ClientServices.id(var0.equals("sky_body") ? "core/sky_body" : "core/sky"))
         .withFragmentShader(ClientServices.id("core/" + var0))
         .withShaderDefine("QUALITY", var1)
         .withUniform("PurxiySky", class_10789.field_60031)
         .withSampler("NoiseSampler");
      switch (var0) {
         case "sky_space":
            var2.withoutBlend();
            break;
         case "sky":
            var2.withUniform("Fog", class_10789.field_60031).withSampler("SpaceSampler").withBlend(BlendFunction.TRANSLUCENT);
            break;
         default:
            var2.withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA);
      }

      return var2.withDepthWrite(false)
         .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(class_290.field_60033, class_5596.field_27379)
         .build();
   }

   private static RenderPipeline afterglow(String var0, boolean var1, boolean var2) {
      Builder var3 = RenderPipeline.builder()
         .withLocation(ClientServices.id("pipeline/" + var0))
         .withVertexShader(ClientServices.id("core/afterglow"))
         .withFragmentShader(ClientServices.id("core/afterglow"))
         .withUniform("DynamicTransforms", class_10789.field_60031)
         .withUniform("Projection", class_10789.field_60031)
         .withUniform("Fog", class_10789.field_60031)
         .withSampler("Sampler0")
         .withCull(false)
         .withVertexFormat(class_290.field_1580, class_5596.field_27382);
      if (var2) {
         var3.withShaderDefine("EMISSIVE").withDepthWrite(false);
      } else {
         var3.withUniform("Lighting", class_10789.field_60031).withSampler("Sampler2");
      }

      if (var1) {
         var3.withBlend(BlendFunction.TRANSLUCENT);
      }

      return var3.build();
   }

   private static RenderPipeline radiance(String var0, String... var1) {
      Builder var2 = RenderPipeline.builder()
         .withLocation(ClientServices.id("pipeline/" + var0))
         .withVertexShader(ClientServices.id("core/radiance_rect"))
         .withFragmentShader(ClientServices.id("core/" + var0))
         .withUniform("PurxiyRadiance", class_10789.field_60031)
         .withUniform("PurxiyRadianceSlots", class_10789.field_60031);

      for (String var6 : var1) {
         var2.withSampler(var6);
      }

      if (var0.equals("radiance_flood")) {
         var2.withUniform("RadianceStep", class_10789.field_60031);
      }

      if (var0.equals("radiance")) {
         var2.withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA);
      } else {
         var2.withoutBlend();
      }

      return var2.withDepthWrite(false)
         .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(class_290.field_60033, class_5596.field_27379)
         .build();
   }

   private static RenderPipeline blur(String var0) {
      return RenderPipeline.builder()
         .withLocation(ClientServices.id("pipeline/" + var0))
         .withVertexShader("core/screenquad")
         .withFragmentShader(ClientServices.id("core/" + var0))
         .withSampler("InSampler")
         .withUniform("BlurInfo", class_10789.field_60031)
         .withoutBlend()
         .withDepthWrite(false)
         .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(class_290.field_60033, class_5596.field_27379)
         .build();
   }
}
