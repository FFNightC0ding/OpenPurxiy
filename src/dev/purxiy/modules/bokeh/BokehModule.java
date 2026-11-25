package dev.purxiy.modules.bokeh;

import dev.purxiy.config.BooleanSetting;
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

public final class BokehModule extends ModuleDescriptor {
   public static final String d = "Auto";
   public static final String G = "Distance";
   public static final String M = "Tilt-shift";
   public final ModeSetting T = this.section("Focus")
      .mode("focus", "Focus", "What the lens keeps sharp", "Auto", new String[]{"Auto", "Distance", "Tilt-shift"});
   public final NumberSetting E = this.number("speed", "Focus speed", "How quickly the focus follows your look", 1.0, 0.25, 3.0, 0.05, Listener.MULTIPLIER);
   public final BooleanSetting D = this.bool("near", "Near blur", "What is closer than the focus melts away too", true);
   public final NumberSetting y = this.number("distance", "Distance", "Everything nearer than this stays sharp", 24.0, 4.0, 96.0, 1.0, Listener.unit(" blocks"));
   public final NumberSetting L = this.number("band", "Sharp band", "How much of the picture stays sharp", 0.26, 0.06, 0.6, 0.02, Listener.PERCENT);
   public final ModeSetting t = this.section("Lens").mode("shape", "Bokeh", "The shape lights take when they blur", BokehKind.O.V, BokehKind.names());
   public final NumberSetting K = this.number("blur", "Blur", "How soft the out-of-focus world becomes", 0.5, 0.1, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting h = this.number("highlights", "Highlights", "How brightly lights bloom into discs", 0.6, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting b = this.number("fringe", "Fringe", "A soft lilac edge around every disc", 0.45, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final BooleanSetting Q = this.bool("swirl", "Swirl", "Discs near the edges turn, like an old lens", false);

   public BokehModule() {
      super("bokeh", "Bokeh", "Sharp where you look, creamy light behind", "aperture");
      this.E.visibleWhen(() -> this.T.is("Auto"));
      this.D.visibleWhen(() -> this.T.is("Auto"));
      this.y.visibleWhen(() -> this.T.is("Distance"));
      this.L.visibleWhen(() -> this.T.is("Tilt-shift"));
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/bokeh.fsh",
               "shaders/core/bokeh.vsh",
               "shaders/core/bokeh_gather.fsh",
               "shaders/core/bokeh_prep.fsh",
               "shaders/core/bokeh_preview.fsh",
               "shaders/core/bokeh_spots.fsh",
               "shaders/core/bokeh_sprites.fsh",
               "shaders/core/bokeh_sprites.vsh",
               "shaders/core/bokeh_tiles.fsh",
               "shaders/include/bokeh.glsl",
               "shaders/include/bokeh_shape.glsl",
               "shaders/include/theme.glsl",
               "shaders/include/tint.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      BokehRenderPass.install(this, var1);
      var1.own(BokehRenderPass::uninstall);
      var1.own(BokehRenderer::releaseResources);
      var1.own(SkyPipelineRouter.VIEW.subscribe(90, (FrameHook)(var0, var1x, var2, var3) -> BokehRenderPass.beginFrame(var0, var1x, var2)));
      var1.own(SkyPipelineRouter.AFTER_WORLD.subscribe(90, BokehRenderPass::render));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new BokehEditorPage(this);
   }
}
