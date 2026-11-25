package dev.purxiy.modules.jumpcircles;

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

public final class JumpCirclesModule extends ModuleDescriptor {
   public final ModeSetting G = this.mode("palette", "Palette", "The colours of the rings", "Accent", JumpCirclesLoader.names()).renamed(JumpCirclesLoader.f);
   public final ColorSetting u = this.color("color", "Colour", "The rest of the palette is derived from it", -4944641, false).followsAccent();
   public final NumberSetting r = this.number("size", "Size", "How far a ring spreads", 1.2, 0.5, 3.0, 0.1, Listener.unit(" blocks"));
   public final NumberSetting c = this.number("duration", "Duration", "How long a ring lasts", 1.1, 0.4, 3.0, 0.1, Listener.unit("s"));
   public final NumberSetting U = this.number("glow", "Glow", "A soft halo of light around the ring", 0.6, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final BooleanSetting z = this.bool("others", "Other players", "Rings where other players jump too", false);

   public JumpCirclesModule() {
      super("jump_circles", "Jump Circles", "A ring of light where you jump", "circle-dot");
      this.u.visibleWhen(() -> this.G.is("Custom"));
   }

   protected void onDisable() {
      JumpCirclesRenderer2.clear();
   }

   public Studio studio() {
      return JumpCirclesRenderer.of(this);
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientCodec.install(var1, List.of("shaders/core/jump_circles.fsh", "shaders/core/jump_circles.vsh", "shaders/include/jump_circles.glsl")));
      var1.own(ClientServices.acquire(this.id()));
      JumpCirclesRenderer2.install(this, var1);
      var1.own(JumpCirclesRenderer2::uninstall);
      var1.own(ClientTheme.install(new dev.purxiy.loader.fabric.ClientTheme.Listener() {
         public boolean active() {
            return JumpCirclesSupport2.active();
         }

         public float fov(float var1) {
            return JumpCirclesSupport2.fov(var1);
         }

         public boolean hides(class_1297 var1) {
            return JumpCirclesSupport2.hides(var1);
         }

         public void reset() {
            JumpCirclesRenderer.closeOwned();
         }

         public boolean place(class_4184 var1, class_1937 var2, class_1297 var3, float var4, double[] var5, float[] var6) {
            return JumpCirclesSupport2.place(var1, var2, var3, var4, var5, var6);
         }

         public void pose(class_11890 var1, class_10055 var2) {
            if (JumpCirclesSupport2.active() && var1 == class_310.method_1551().field_1724) {
               float var3 = Easing.SMOOTHSTEP.apply(Math.clamp((JumpCirclesSupport2.progress() - 0.15F) / 0.6F, 0.0F, 1.0F));
               var2.field_53447 = class_3532.method_16439(var3, var2.field_53447, 0.0F);
               var2.field_53448 = class_3532.method_16439(var3, var2.field_53448, -2.0F);
            }
         }
      }));
      var1.own(ModuleWidget.claim("preview-jump-circles", () -> JumpCirclesRenderer.showing(this)));
      var1.own(ModuleWidget.onTick(JumpCirclesRenderer::tick));
      var1.own(JumpCirclesRenderer::closeOwned);
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> {
         ClientTheme.resetAll();
         return new JumpCirclesSupport(this);
      }));
      var1.own(ClientSupport3.VIEW.subscribe(100, (Listener10)(var0, var1x, var2, var3) -> JumpCirclesRenderer2.beginFrame(var0, var1x, var2, var3)));
      var1.own(ClientSupport3.AFTER_WORLD.subscribe(100, JumpCirclesRenderer2::render));
      var1.own(ClientSupport3.JUMP.subscribe(0, JumpCirclesRenderer2::onJump));
   }
}
