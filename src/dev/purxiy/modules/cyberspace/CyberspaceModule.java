package dev.purxiy.modules.cyberspace;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.SkyTintProvider;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import java.util.List;
import net.minecraft.class_12076;

public final class CyberspaceModule extends ModuleDescriptor {
   public final ModeSetting I;
   public final ColorSetting O;
   public final ModeSetting H;
   public final NumberSetting j;
   public final NumberSetting S;
   public final BooleanSetting L;
   public final BooleanSetting R;
   public final BooleanSetting X;
   public final NumberSetting P;
   public final BooleanSetting e;
   public final BooleanSetting b;
   public static int w = 0;

   public CyberspaceModule() {
      super("cyberspace", "Cyberspace", "Step into a digital dimension of light, wires and data", "circuit-board");
      this.section("The grid");
      this.I = this.mode("palette", "Palette", "The colours of the light", "Accent", CyberspaceLoader.names()).renamed(CyberspaceLoader.n);
      this.O = this.color("color", "Colour", "The rest of the palette is derived from it", -4944641, false).followsAccent();
      this.H = this.mode("theme", "Theme", "A midnight network, or a bright one of porcelain", "Midnight", new String[]{"Midnight", "Porcelain"});
      this.j = this.number("reality", "Reality", "How much of the real world shows through", 0.15, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.S = this.number("glow", "Glow", "How brightly the lines shine", 0.7, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.section("A living network");
      this.L = this.bool("circuits", "Circuits", "Wiring on every block, with data running through it", true);
      this.R = this.bool("flight", "Flying data", "Packets of light race through the air, bits drift up from the ground", true);
      this.X = this.bool("scan", "Scan wave", "A wave of light sweeps out from you every few seconds", true);
      this.P = this.number("interval", "Scan every", null, 8.0, 3.0, 20.0, 1.0, Listener.unit("s"));
      this.e = this.bool("sky", "Digital sky", "An endless grid below the horizon, stars of data above", true);
      this.b = this.bool("glitch", "Glitch when hurt", "The simulation stutters for a moment when you take damage", true);
      this.O.visibleWhen(() -> this.I.is("Custom"));
      this.P.visibleWhen(this.X::get);
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/cyberspace.fsh",
               "shaders/core/cyberspace.vsh",
               "shaders/core/cyberspace_flight.fsh",
               "shaders/core/cyberspace_flight.vsh",
               "shaders/include/cyberspace.glsl",
               "shaders/include/cyberspace_common.glsl",
               "shaders/include/cyberspace_flight.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      CyberspaceRenderPass.install(this, var1);
      var1.own(CyberspaceRenderPass::uninstall);
      var1.own(CyberspaceRenderer::releaseResources);
      var1.own(SkyTintProvider.install(new dev.purxiy.loader.fabric.client.SkyTintProvider.Listener() {
         public boolean hidesClouds() {
            return CyberspaceRenderPass.hidesClouds();
         }

         public void sky(class_12076 var1) {
            CyberspaceRenderPass.sky(var1);
         }
      }));
      var1.own(SkyPipelineRouter.VIEW.subscribe(50, CyberspaceRenderPass::beginFrame));
      var1.own(SkyPipelineRouter.AFTER_WORLD.subscribe(55, CyberspaceRenderPass::render));
   }
}
