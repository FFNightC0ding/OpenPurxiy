// Sourced By DevFFNightmare
package dev.purxiy.modules.hitsplash;

import dev.purxiy.anim.Easing;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ClientTheme;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientCodec;
import dev.purxiy.loader.fabric.client.ClientSupport3;
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

public final class HitSplashModule extends ModuleDescriptor {
   public final ModeSetting Z = this.mode("palette", "Palette", "The colours of the ink", "Accent", HitSplashLoader.names()).renamed(HitSplashLoader.w);
   public final ColorSetting p = this.color("color", "Colour", "The rest of the palette is derived from it", -4944641, false).followsAccent();
   public final NumberSetting N = this.number("size", "Size", "How big the splashes are", 1.0, 0.5, 2.0, 0.05, NumberSetting.Listener.MULTIPLIER);
   public final NumberSetting J = this.number(
      "duration", "Duration", "How long a splash stays on the ground", 6.0, 2.0, 20.0, 0.5, NumberSetting.Listener.unit("s")
   );
   public final NumberSetting x = this.number("glow", "Glow", "Its light, brightest where hits pile up", 0.7, 0.0, 1.0, 0.05, NumberSetting.Listener.PERCENT);
   public final BooleanSetting y = this.bool("others", "Other players", "Splashes where other players land hits too", false);



   public HitSplashModule() {
      super("hit_splash", "Hit Splash", "Glowing splashes where your hits land", "droplets");
      this.p.visibleWhen(() -> this.Z.is("Custom"));
   }

   private void V() {
      HitSplashRenderer3.prepare();
   }

   @Override
   protected void onDisable() {
      HitSplashRenderer.clear();
   }

   
   @Override
   public Studio studio() {
      return HitSplashRenderer2.of(this);
   }

   @Override
   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/hit_splash.fsh",
               "shaders/core/hit_splash.vsh",
               "shaders/core/hit_splash_drops.fsh",
               "shaders/core/hit_splash_drops.vsh",
               "shaders/include/hit_splash.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      HitSplashRenderer.install(this, var1);
      var1.own(HitSplashRenderer::uninstall);
      var1.own(ClientTheme.install(new ClientTheme.Listener() {
         @Override
         public boolean active() {
            return HitSplashSupport.active();
         }

         @Override
         public float fov(float var1) {
            return HitSplashSupport.fov(var1);
         }

         @Override
         public boolean hides(class_1297 var1) {
            return HitSplashSupport.hides(var1);
         }

         @Override
         public void reset() {
            HitSplashRenderer2.closeOwned();
         }

         @Override
         public boolean place(class_4184 var1, class_1937 var2, class_1297 var3, float var4, double[] var5, float[] var6) {
            return HitSplashSupport.place(var1, var2, var3, var4, var5, var6);
         }

         @Override
         public void pose(class_11890 var1, class_10055 var2) {
            if (HitSplashSupport.active() && var1 == class_310.method_1551().field_1724) {
               float var3 = Easing.SMOOTHSTEP.apply(Math.clamp((HitSplashSupport.progress() - 0.15F) / 0.6F, 0.0F, 1.0F));
               var2.field_53447 = class_3532.method_16439(var3, var2.field_53447, 0.0F);
               var2.field_53448 = class_3532.method_16439(var3, var2.field_53448, -2.0F);
            }
         }
      }));
      var1.own(ModuleWidget.claim("preview-hit-splash", () -> HitSplashRenderer2.showing(this)));
      var1.own(ModuleWidget.onTick(HitSplashRenderer2::tick));
      var1.own(HitSplashRenderer2::closeOwned);
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> {
         ClientTheme.resetAll();
         return new HitSplashSupport2(this);
      }));
      this.V();
      var1.own(HitSplashRenderer3::releaseResources);
      var1.own(ClientSupport3.VIEW.subscribe(95, (var0, var1x, var2, var3) -> HitSplashRenderer.beginFrame(var0, var1x, var2, var3)));
      var1.own(ClientSupport3.AFTER_WORLD.subscribe(95, HitSplashRenderer::render));
      var1.own(ModuleWidget.onActualAttack(HitSplashRenderer::onAttack));
      var1.own(ClientSupport3.DAMAGE.subscribe(0, HitSplashRenderer::onDamage));
      var1.own(ClientSupport3.ENTITY_EVENT.subscribe(0, HitSplashRenderer::onEntityEvent));
   }

}
