package dev.purxiy.modules.fire;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientRegistry12;
import dev.purxiy.loader.fabric.client.ClientRenderer7;
import java.util.List;
import net.minecraft.class_10017;
import net.minecraft.class_4597;
import net.minecraft.class_4587.class_4665;
import org.joml.Quaternionf;

public final class LowFireModule extends ModuleDescriptor {
   public static final String H = "Flames";
   public static final String f = "Pixel";
   public static final String S = "Glow";
   public static final String L = "Normal";
   public static final String P = "Calm";
   public static final String T = "Hide";
   public final ModeSetting E = this.section("Look")
      .mode("style", "Style", "Soft flames, pixel fire, or only a glow", "Flames", new String[]{"Flames", "Pixel", "Glow"});
   public final ModeSetting R = this.mode("palette", "Palette", "The colours of the fire", "Accent", LowFireLoader.names()).renamed(LowFireLoader.l);
   public final ColorSetting c = this.color("color", "Colour", "The rest of the palette is derived from it", -4944641, false).followsAccent();
   public final NumberSetting g = this.number("height", "Height", "How far up the screen the flames reach", 0.3, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting y = this.number("opacity", "Opacity", "How much of the world shows through them", 0.85, 0.2, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting I = this.number(
      "speed", "Animation speed", "How fast the flames flicker and rise (0 = still)", 1.0, 0.0, 3.0, 0.05, Listener.MULTIPLIER
   );
   public final NumberSetting r = this.section("Animations")
      .number("embers", "Embers", "Sparks that drift up from the flames", 1.0, 0.0, 2.0, 0.05, Listener.PERCENT);
   public final BooleanSetting B = this.bool("haze", "Heat haze", "The air above the flames shimmers", true);
   public final BooleanSetting d = this.bool("flare", "Flare", "The flames flare up each time the fire hurts you", true);
   public final BooleanSetting V = this.bool("sway", "Sway", "They lean and trail as you move and turn", true);
   public final ModeSetting W = this.section("Situations")
      .mode("resistance", "Fire Resistance", "While the fire cannot hurt you", "Calm", new String[]{"Normal", "Calm", "Hide"});
   public final BooleanSetting s = this.section("In the world").bool("ground", "Ground fire", "Fire on the ground burns low, in the palette", true);
   public final NumberSetting G = this.number("ground_height", "Ground height", "How high fire on the ground burns", 0.3, 0.15, 1.0, 0.05, Listener.PERCENT);
   public final BooleanSetting o = this.bool("world", "Burning mobs", "Players and mobs burn in the palette too", true);

   public LowFireModule() {
      super("low_fire", "Low Fire", "Low, soft flames in the Purxiy colours", "flame");
      this.c.visibleWhen(() -> this.R.is("Custom"));
      this.B.visibleWhen(() -> {
         LowFireLoader.ensureSourceClassInitialized();
         return !this.E.is("Glow");
      });
      this.G.visibleWhen(this.s::enabled);
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientCodec.install(var1, List.of("shaders/core/fire.fsh", "shaders/core/fire.vsh", "shaders/include/fire.glsl")));
      var1.own(ClientServices.acquire(this.id()));
      LowFireRenderer.install(this, var1);
      var1.own(LowFireRenderer::uninstall);
      var1.own(ClientRenderer7.install(new dev.purxiy.loader.fabric.client.ClientRenderer7.Listener() {
         public boolean replacesOverlay() {
            return LowFireRenderer.replacesOverlay();
         }

         public float vanillaDrop() {
            return LowFireRenderer.vanillaDrop();
         }

         public void render(boolean var1) {
            LowFireRenderer.render(var1);
         }

         public boolean renderEntityFlame(class_4665 var1, class_4597 var2, class_10017 var3, Quaternionf var4) {
            return LowFireRenderer.renderEntityFlame(var1, var2, var3, var4);
         }
      }));
      var1.own(ClientRegistry12.install(LowFireCache2::active));
   }
}
