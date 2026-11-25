package dev.purxiy.modules.haze;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter.FrameHook;
import java.util.List;

public final class HazeModule extends ModuleDescriptor {
   public final ModeSetting H = this.section("Haze").mode("palette", "Colour", "The colour of the haze", HazeCatalog.x.p, HazeCatalog.names());
   public final ColorSetting d = this.color("color", "Your colour", "The rest of the haze is drawn from it", -4944641, false).followsAccent();
   public final NumberSetting U = this.number("strength", "Strength", "How strongly the world takes on the colour", 0.45, 0.1, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting Q = this.number("height", "Height", "How high the haze rises above the ground", 24.0, 6.0, 64.0, 1.0, Listener.unit(" blocks"));
   public final NumberSetting s = this.number(
      "clear", "Clear around you", "How far around you the air stays clear", 8.0, 0.0, 32.0, 1.0, Listener.unit(" blocks")
   );
   public final NumberSetting V = this.number("drift", "Drift", "How fast the colour drifts by", 1.0, 0.0, 3.0, 0.05, Listener.MULTIPLIER);
   public final NumberSetting k = this.section("Light")
      .number("glow", "Sun glow", "A soft glow towards the sun and moon", 0.6, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final BooleanSetting u = this.bool("creatures", "See players", "Players and mobs keep their colours", true);

   public HazeModule() {
      super("haze", "Haze", "A soft haze of colour over the world around you", "haze");
      this.d.visibleWhen(() -> this.H.is("Custom"));
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/haze.fsh", "shaders/core/haze.vsh", "shaders/core/haze_march.fsh", "shaders/core/haze_preview.fsh", "shaders/include/haze.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      HazeRenderPass.install(this, var1);
      var1.own(HazeRenderPass::uninstall);
      var1.own(HazePipeline::releaseResources);
      var1.own(SkyPipelineRouter.VIEW.subscribe(30, (FrameHook)(var0, var1x, var2, var3) -> HazeRenderPass.beginFrame(var0, var1x, var2, var3)));
      var1.own(SkyPipelineRouter.DEPTH.subscribe(30, HazeRenderPass::keepDepth));
      var1.own(SkyPipelineRouter.AFTER_WORLD.subscribe(30, HazeRenderPass::render));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new HazeRenderer(this);
   }
}
