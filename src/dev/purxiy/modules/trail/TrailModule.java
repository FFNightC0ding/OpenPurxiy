package dev.purxiy.modules.trail;

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
import net.minecraft.class_310;

public final class TrailModule extends ModuleDescriptor {
   public final ModeSetting X = this.mode("style", "Style", "How your trail looks", "Line", TrailStyle.names());
   public final ModeSetting f = this.mode("palette", "Palette", "The colours of the trail", "Accent", TrailLoader.names()).renamed(TrailLoader.t);
   public final ColorSetting Q = this.color("color", "Colour", "The rest of the palette is derived from it", -4944641, false).followsAccent();
   public final NumberSetting B = this.number("duration", "Length", "How long the light stays behind you", 1.2, 0.4, 3.0, 0.1, Listener.unit("s"));
   public final NumberSetting t = this.number("size", "Size", "How wide the trail is", 1.0, 0.5, 2.0, 0.05, Listener.PERCENT);
   public final NumberSetting I = this.number("glow", "Glow", "A soft halo of light around the trail", 0.6, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final BooleanSetting k = this.bool("others", "Other players", "Trails behind other players too", false);

   public TrailModule() {
      super("trail", "Trail", "Leave a trail of light behind you", "wand-sparkles");
      this.Q.visibleWhen(() -> this.f.is("Custom"));
   }

   public TrailStyle trailStyle() {
      return TrailStyle.of((String)this.X.get());
   }

   protected void onDisable() {
      TrailRenderer.clear();
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/trail.fsh",
               "shaders/core/trail.vsh",
               "shaders/core/trail_composite.fsh",
               "shaders/core/trail_composite.vsh",
               "shaders/include/trail.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      TrailRenderer.install(this, var1);
      var1.own(TrailRenderer::uninstall);
      var1.own(TrailPipelines::releaseResources);
      var1.own(SkyPipelineRouter.VIEW.subscribe(80, (FrameHook)(var0, var1x, var2, var3) -> TrailRenderer.beginFrame(var0, var1x, var2, var3)));
      var1.own(SkyPipelineRouter.AFTER_WORLD.subscribe(80, (Runnable)() -> TrailRenderer.render(class_310.method_1551().method_61966().method_60637(false))));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new TrailEditorPage(this);
   }
}
