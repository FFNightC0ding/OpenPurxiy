package dev.purxiy.modules.shockwave;

import dev.purxiy.anim.Easing;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ClientTheme;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import dev.purxiy.loader.fabric.client.ClientSupport3.Listener10;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.ui.clickgui.Studio;
import java.util.List;
import net.minecraft.class_10055;
import net.minecraft.class_11890;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;

public final class ShockwaveModule extends ModuleDescriptor {
   public final ModeSetting I = this.section("Look")
      .mode("palette", "Palette", "The colours of the light", "Accent", ShockwaveLoader.names())
      .renamed(ShockwaveLoader.w);
   public final ColorSetting U = this.color("color", "Colour", "The rest of the palette is derived from it", -4944641, false).followsAccent();
   public final NumberSetting f = this.number("strength", "Strength", "How deep the blocks sink", 1.0, 0.4, 2.0, 0.05, Listener.MULTIPLIER);
   public final NumberSetting N = this.number("size", "Size", "How far the wave runs", 1.0, 0.5, 2.0, 0.05, Listener.MULTIPLIER);
   public final NumberSetting j = this.number("glow", "Glow", "The light that wells up between the blocks", 0.7, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final BooleanSetting E = this.section("What moves the ground").bool("steps", "Footsteps", "Every step softly presses the ground", true);
   public final BooleanSetting q = this.bool("blasts", "Explosions", "Blasts send a wave through the ground", true);
   public final BooleanSetting L = this.bool("others", "Other players", "Their landings and steps move the ground too", false);
   public static int u = 0;

   public ShockwaveModule() {
      super("shockwave", "Shockwave", "Land, and a wave runs through the ground", "waves");
      this.U.visibleWhen(() -> this.I.is("Custom"));
   }

   protected void onDisable() {
      ShockwaveRenderer2.clear();
   }

   public Studio studio() {
      return ShockwaveRenderer4.of(this);
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientCodec.install(var1, List.of("shaders/core/shockwave.fsh", "shaders/core/shockwave.vsh", "shaders/include/shockwave.glsl")));
      var1.own(ClientServices.acquire(this.id()));
      ShockwaveRenderer2.install(this, var1);
      var1.own(ShockwaveRenderer2::uninstall);
      var1.own(ClientTheme.install(new dev.purxiy.loader.fabric.ClientTheme.Listener() {
         public boolean active() {
            return ShockwaveSupport2.active();
         }

         public float fov(float var1) {
            return ShockwaveSupport2.fov(var1);
         }

         public boolean hides(class_1297 var1) {
            return ShockwaveSupport2.hides(var1);
         }

         public void reset() {
            ShockwaveRenderer4.closeOwned();
         }

         public boolean place(class_4184 var1, class_1937 var2, class_1297 var3, float var4, double[] var5, float[] var6) {
            return ShockwaveSupport2.place(var1, var2, var3, var4, var5, var6);
         }

         public void pose(class_11890 var1, class_10055 var2) {
            if (ShockwaveSupport2.active() && var1 == class_310.method_1551().field_1724) {
               float var3 = Easing.SMOOTHSTEP.apply(Math.clamp((ShockwaveSupport2.progress() - 0.15F) / 0.6F, 0.0F, 1.0F));
               var2.field_53447 = class_3532.method_16439(var3, var2.field_53447, 0.0F);
               var2.field_53448 = class_3532.method_16439(var3, var2.field_53448, -2.0F);
            }
         }
      }));
      var1.own(ModuleWidget.claim("preview-shockwave", () -> ShockwaveRenderer4.showing(this)));
      var1.own(ModuleWidget.onTick(ShockwaveRenderer4::tick));
      var1.own(ShockwaveRenderer4::closeOwned);
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> {
         ClientTheme.resetAll();
         return new ShockwaveSupport(this);
      }));
      var1.own(ShockwaveRenderer::releaseResources);
      var1.own(ClientSupport3.VIEW.subscribe(40, (Listener10)(var0, var1x, var2, var3) -> ShockwaveRenderer2.beginFrame(var0, var1x, var2, var3)));
      var1.own(ClientSupport3.AFTER_WORLD.subscribe(40, (Runnable)() -> ShockwaveRenderer2.render(false)));
      var1.own(ClientSupport3.AFTER_WORLD.subscribe(60, (Runnable)() -> ShockwaveRenderer2.render(true)));
      var1.own(ClientSupport3.LAND.subscribe(0, ShockwaveRenderer2::onLand));
      var1.own(ClientSupport3.STEP.subscribe(0, ShockwaveRenderer2::onStep));
      var1.own(ClientSupport3.BLAST.subscribe(0, ShockwaveRenderer2::onBlast));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new ShockwaveRenderer5(this);
   }

   public static void ensureSourceClassInitialized() {
   }
}
