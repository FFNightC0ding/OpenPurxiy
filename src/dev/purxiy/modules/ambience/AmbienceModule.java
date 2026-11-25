package dev.purxiy.modules.ambience;

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
import java.util.List;

public final class AmbienceModule extends ModuleDescriptor {
   public final ModeSetting s = this.mode("style", "Style", "What drifts through the air", "Motes", AmbienceCatalog.names());
   public final ModeSetting z = this.mode("palette", "Palette", "The colours of the light", "Accent", AmbienceLoader.names()).renamed(AmbienceLoader.o);
   public final ColorSetting F = this.color("color", "Colour", "The rest of the palette is derived from it", -4944641, false).followsAccent();
   public final NumberSetting h = this.number("amount", "Amount", "How many drift around you", 1.0, 0.1, 2.0, 0.05, Listener.PERCENT);
   public final NumberSetting E = this.number("size", "Size", null, 1.0, 0.5, 2.0, 0.05, Listener.MULTIPLIER);
   public final NumberSetting e = this.number("glow", "Glow", "The soft light around each one", 0.6, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting w = this.number("speed", "Speed", "How fast they drift; at 0 they hang still", 1.0, 0.0, 3.0, 0.05, Listener.MULTIPLIER);
   public final NumberSetting v = this.number("twinkle", "Twinkle", "How much they sparkle and breathe", 0.5, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting g = this.number("range", "Range", "How far around you they appear, in blocks", 24.0, 8.0, 40.0, 1.0, Listener.INTEGER);
   public final BooleanSetting y = this.bool("depth_of_field", "Depth of field", "The closest ones melt into soft bokeh", true);
   public final BooleanSetting t = this.bool("react", "React to you", "They swirl aside as you move through them", true);
   public final BooleanSetting N = this.bool("curious", "Curious", "Stand still and a few drift over to circle around you", false);
   public final NumberSetting P = this.number("daylight", "Daylight", "How many stay out in the open by day", 0.5, 0.0, 1.0, 0.05, Listener.PERCENT);

   public AmbienceModule() {
      super("ambience", "Ambience", "Glowing particles drifting through the world around you", "sparkles");
      this.F.visibleWhen(() -> this.z.is("Custom"));
      this.N.visibleWhen(() -> {
         AmbienceRegistry.ensureSourceClassInitialized();
         return !AmbienceCatalog.byName((String)this.s.get()).V;
      });
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientCodec.install(var1, List.of("shaders/core/ambience.fsh", "shaders/core/ambience.vsh", "shaders/include/ambience.glsl")));
      var1.own(ClientServices.acquire(this.id()));
      AmbienceRegistry.install(this, var1);
      var1.own(AmbienceRegistry::uninstall);
      var1.own(SkyPipelineRouter.PARTICLES.subscribe(0, AmbienceRegistry::addPass));
   }
}
