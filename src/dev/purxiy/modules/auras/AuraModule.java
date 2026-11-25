package dev.purxiy.modules.auras;

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
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter.FrameHook;
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

public final class AuraModule extends ModuleDescriptor {
   public final ModeSetting S = this.mode("style", "Style", "How your aura looks", AuraStyle.J.b, AuraStyle.names());
   public final ModeSetting H = this.section("Look").mode("palette", "Palette", "The colours of the aura", "Accent", AuraLoader.names()).renamed(AuraLoader.d);
   public final ColorSetting z = this.color("color", "Colour", "The rest of the palette is derived from it", -4944641, false).followsAccent();
   public final NumberSetting R = this.number("size", "Size", "How far the aura reaches around you", 1.0, 0.6, 1.6, 0.05, Listener.PERCENT);
   public final NumberSetting O = this.number("speed", "Speed", "How lively it moves", 1.0, 0.25, 2.5, 0.05, Listener.MULTIPLIER);
   public final NumberSetting A = this.number("glow", "Glow", "A soft halo of light around every light", 0.65, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final BooleanSetting J = this.section("Visibility").bool("first_person", "In first person", "See your aura from your own eyes too", false);
   public final BooleanSetting f = this.bool("others", "Other players", "Auras around other players too", false);
   private static final String[] b = new String[41];

   public AuraModule() {
      super("aura", "Aura", "Soft light that lives around you", "sparkles");
      this.z.visibleWhen(() -> this.H.is("Custom"));
   }

   public AuraStyle auraStyle() {
      return AuraStyle.of((String)this.S.get());
   }

   private void E() {
      AuraRenderPass.gather();
   }

   public Studio studio() {
      return AuraRenderer.of(this);
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/aura.fsh",
               "shaders/core/aura.vsh",
               "shaders/core/aura_composite.fsh",
               "shaders/core/aura_composite.vsh",
               "shaders/core/aura_marker.fsh",
               "shaders/core/aura_marker.vsh",
               "shaders/include/aura.glsl",
               "shaders/include/aura_marker.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      AuraRenderPass.install(this, var1);
      var1.own(AuraRenderPass::uninstall);
      var1.own(ClientTheme.install(new dev.purxiy.loader.fabric.ClientTheme.Listener() {
         public boolean active() {
            return AuraFrameView.active();
         }

         public float fov(float var1) {
            return AuraFrameView.fov(var1);
         }

         public boolean hides(class_1297 var1) {
            return AuraFrameView.hides(var1);
         }

         public void reset() {
            AuraRenderer.closeOwned();
         }

         public boolean place(class_4184 var1, class_1937 var2, class_1297 var3, float var4, double[] var5, float[] var6) {
            return AuraFrameView.place(var1, var2, var3, var4, var5, var6);
         }

         public void pose(class_11890 var1, class_10055 var2) {
            if (AuraFrameView.active() && var1 == class_310.method_1551().field_1724) {
               float var3 = Easing.SMOOTHSTEP.apply(Math.clamp((AuraFrameView.progress() - 0.15F) / 0.6F, 0.0F, 1.0F));
               var2.field_53447 = class_3532.method_16439(var3, var2.field_53447, 0.0F);
               var2.field_53448 = class_3532.method_16439(var3, var2.field_53448, -2.0F);
            }
         }
      }));
      var1.own(ModuleWidget.claim("preview-aura", () -> AuraRenderer.showing(this)));
      var1.own(ModuleWidget.onTick(AuraRenderer::tick));
      var1.own(AuraRenderer::closeOwned);
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> {
         ClientTheme.resetAll();
         return new AuraSupport(this);
      }));
      this.E();
      var1.own(AuraPipelineSet::releaseResources);
      var1.own(SkyPipelineRouter.VIEW.subscribe(85, (FrameHook)(var0, var1x, var2, var3) -> AuraRenderPass.beginFrame(var0, var1x, var2, var3)));
      var1.own(SkyPipelineRouter.AFTER_WORLD.subscribe(85, (Runnable)() -> AuraRenderPass.render(class_310.method_1551().method_61966().method_60637(false))));
   }
}
