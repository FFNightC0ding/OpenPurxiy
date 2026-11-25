package dev.purxiy.modules.ripple;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
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
import dev.purxiy.loader.fabric.client.ClientRenderer3;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.ui.clickgui.Studio;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_10017;
import net.minecraft.class_10055;
import net.minecraft.class_11659;
import net.minecraft.class_11890;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_2394;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import org.joml.Matrix4fc;

public final class RippleModule extends ModuleDescriptor {
   public static final String E = "Everyone";
   public static final String f = "Your hits";
   public final ModeSetting J = this.section("Look")
      .mode("palette", "Palette", "The colours of the light", "Accent", RippleLoader.names())
      .renamed(RippleLoader.w);
   public final ColorSetting K = this.color("color", "Colour", "The rest of the palette is derived from it", -4944641, false).followsAccent();
   public final NumberSetting F = this.number("glow", "Glow", "How brightly a hit lights them up", 0.8, 0.2, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting q = this.number("duration", "Duration", "How long the light lingers", 0.8, 0.4, 2.0, 0.1, Listener.unit("s"));
   public final ModeSetting n = this.section("Hits")
      .mode("who", "Whose hits", "Every hit you see, or only the ones you land", "Everyone", new String[]{"Everyone", "Your hits"});
   public final BooleanSetting o = this.bool("crits", "Critical stars", "A burst of stars where a critical hit lands", true);
   public final NumberSetting u = this.number("stars", "Stars", "How many stars a critical hit throws", 1.0, 0.25, 2.0, 0.05, Listener.MULTIPLIER);
   public final BooleanSetting H = this.section("Kills").bool("kills", "Light pillar", "A pillar of light where your kills fall", true);
   public final BooleanSetting c = this.bool("ring", "Shockwave", "A ring of light that rolls over the ground", true);

   public RippleModule() {
      super("ripple", "Ripple", "A wave of light where every hit lands", "waves");
      this.K.visibleWhen(() -> this.J.is("Custom"));
      this.u.visibleWhen(this.o::enabled);
      this.c.visibleWhen(this.H::enabled);
   }

   private void d() {
      RippleRenderer2.moduleChanged();
   }

   protected void onDisable() {
      RippleRenderer2.moduleChanged();
   }

   public Studio studio() {
      return RippleRenderer.of(this);
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/ripple_body.fsh",
               "shaders/core/ripple_body.vsh",
               "shaders/core/ripple_fx.fsh",
               "shaders/core/ripple_fx.vsh",
               "shaders/include/ripple.glsl",
               "shaders/include/ripple_fx.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      RippleRenderer2.install(this, var1);
      var1.own(RippleRenderer2::uninstall);
      var1.own(ClientTheme.install(new dev.purxiy.loader.fabric.ClientTheme.Listener() {
         public boolean active() {
            return RippleSupport.active();
         }

         public float fov(float var1) {
            return RippleSupport.fov(var1);
         }

         public boolean hides(class_1297 var1) {
            return RippleSupport.hides(var1);
         }

         public void reset() {
            RippleRenderer.closeOwned();
         }

         public boolean place(class_4184 var1, class_1937 var2, class_1297 var3, float var4, double[] var5, float[] var6) {
            return RippleSupport.place(var1, var2, var3, var4, var5, var6);
         }

         public void pose(class_11890 var1, class_10055 var2) {
            if (RippleSupport.active() && var1 == class_310.method_1551().field_1724) {
               float var3 = Easing.SMOOTHSTEP.apply(Math.clamp((RippleSupport.progress() - 0.15F) / 0.6F, 0.0F, 1.0F));
               var2.field_53447 = class_3532.method_16439(var3, var2.field_53447, 0.0F);
               var2.field_53448 = class_3532.method_16439(var3, var2.field_53448, -2.0F);
            }
         }
      }));
      var1.own(ModuleWidget.claim("preview-ripple", () -> RippleRenderer.showing(this)));
      var1.own(ModuleWidget.onTick(RippleRenderer::tick));
      var1.own(RippleRenderer::closeOwned);
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> {
         ClientTheme.resetAll();
         return new RippleSupport3(this);
      }));
      this.d();
      var1.own(RippleRenderer3::releaseResources);
      var1.own(RippleSupport4::releaseResources);
      var1.own(ClientRenderer3.install(new dev.purxiy.loader.fabric.client.ClientRenderer3.Listener() {
         public void onAttack(class_1297 var1) {
            RippleRenderer2.onAttack(var1);
         }

         public void onDamage(class_1297 var1, int var2, int var3, Optional<class_243> var4) {
            RippleRenderer2.onDamage(var1, var2, var3, var4);
         }

         public void onAnimate(class_1297 var1, int var2) {
            RippleRenderer2.onAnimate(var1, var2);
         }

         public void onEntityEvent(class_1297 var1, byte var2) {
            RippleRenderer2.onEntityEvent(var1, var2);
         }

         public boolean replacesParticles(class_1297 var1, class_2394 var2) {
            return RippleRenderer2.replacesParticles(var1, var2);
         }

         public void moduleChanged() {
            RippleRenderer2.moduleChanged();
         }

         public void clear() {
            RippleRenderer2.clear();
         }

         public void beginFrame(class_4184 var1, Matrix4fc var2, Matrix4fc var3, GpuBufferSlice var4) {
            RippleRenderer2.beginFrame(var1, var2, var3, var4);
         }

         public void extract(class_1297 var1, class_10017 var2) {
            RippleRenderer2.extract(var1, var2);
         }

         public class_11659 collector(class_10017 var1, class_11659 var2) {
            return RippleRenderer2.collector(var1, var2);
         }

         public void render() {
            RippleRenderer2.render();
         }
      }));
      var1.own(ClientSupport3.AFTER_WORLD.subscribe(105, RippleRenderer2::render));
   }
}
