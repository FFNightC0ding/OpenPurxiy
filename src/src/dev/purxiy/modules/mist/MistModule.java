// Sourced By DevFFNightmare
package dev.purxiy.modules.mist;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.CosmeticCatalog;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import java.util.List;

public final class MistModule extends ModuleDescriptor {
   public final ModeSetting P = this.section("Mist").mode("palette", "Colour", "The colour of the mist", MistCatalog.Q.C, MistCatalog.names());
   public final NumberSetting U = this.number(
      "density", "Density", "How thick the mist hangs; less lets you see further", 0.5, 0.1, 1.0, 0.05, NumberSetting.Listener.PERCENT
   );
   public final NumberSetting l = this.number(
      "height", "Height", "How high the mist rises above the ground", 10.0, 3.0, 32.0, 1.0, NumberSetting.Listener.unit(" blocks")
   );
   public final NumberSetting E = this.number(
      "clear", "Clear around you", "How far around you the air stays clear", 6.0, 0.0, 24.0, 1.0, NumberSetting.Listener.unit(" blocks")
   );
   public final NumberSetting H = this.number(
      "drift", "Drift", "How fast the banks of mist roll by; at 0 they hang still", 1.0, 0.0, 3.0, 0.05, NumberSetting.Listener.MULTIPLIER
   );
   public final NumberSetting I = this.section("Light")
      .number("rays", "Sun rays", "Warm light through the mist, in beams between the trees", 0.6, 0.0, 1.0, 0.05, NumberSetting.Listener.PERCENT);
   public final NumberSetting Y = this.number(
      "lanterns", "Lantern glow", "Torches, lanterns and fire glow softly in the mist at night", 0.7, 0.0, 1.0, 0.05, NumberSetting.Listener.PERCENT
   );
   public final BooleanSetting e = this.bool("creatures", "See players", "Players and mobs show clearly through the mist", true);



   public MistModule() {
      super("mist", "Mist", "Soft autumn mist that hangs low in the world", "cloud-fog");
   }

   @Override
   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/mist.fsh",
               "shaders/core/mist.vsh",
               "shaders/core/mist_blur.fsh",
               "shaders/core/mist_march.fsh",
               "shaders/core/mist_prep.fsh",
               "shaders/core/mist_preview.fsh",
               "shaders/include/mist.glsl",
               "shaders/include/mist_field.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      MistRenderer3.install(this, var1);
      var1.own(MistRenderer3::uninstall);
      var1.own(MistRenderer::releaseResources);
      var1.own(ClientSupport3.VIEW.subscribe(20, (var0, var1x, var2, var3) -> MistRenderer3.beginFrame(var0, var1x, var2)));
      var1.own(ClientSupport3.DEPTH.subscribe(20, MistRenderer3::keepDepth));
      var1.own(ClientSupport3.AFTER_WORLD.subscribe(20, MistRenderer3::render));
   }

   @Override
   protected CosmeticCatalog.Listener createPreview() {
      return new MistRenderer2(this);
   }

}
