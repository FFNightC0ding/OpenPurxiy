package dev.purxiy.modules.halo;

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
import dev.purxiy.loader.fabric.client.ClientRegistry24;
import dev.purxiy.loader.fabric.client.ClientRegistry4;
import dev.purxiy.loader.fabric.client.ClientRenderer6;
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

public final class HaloModule extends ModuleDescriptor {
   public final ModeSetting S = this.mode("design", "Halo", "Which halo you wear", HaloDesign.o.U, HaloDesign.labels());
   public final BooleanSetting F = this.section("Look").bool("skin_colour", "Match your skin", "Its colour comes from the skin you wear", false);
   public final ColorSetting Q = this.color("tint", "Colour", "The colour of its light", -4222985, false).followsAccent();
   public final NumberSetting b = this.number("size", "Size", "How wide the ring is", 1.0, 0.7, 1.4, 0.05, Listener.PERCENT);
   public final NumberSetting w = this.number("glow", "Glow", "How brightly it shines", 0.7, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final NumberSetting V = this.number("height", "Height", "How far it floats above your head", 1.0, 0.6, 1.8, 0.05, Listener.PERCENT);
   public final NumberSetting a = this.section("Movement")
      .number("follow", "Follow your head", "How far it tilts as you look up and down", 0.4, 0.0, 1.0, 0.05, Listener.PERCENT);
   public final BooleanSetting i = this.bool("float", "Float", "It bobs and sways softly over your head", true);

   public HaloModule() {
      super("halo", "Halo", "A gently glowing halo", "circle-dashed");
      this.Q.visibleWhen(() -> {
         HaloDesign.ensureSourceClassInitialized();
         return !this.F.enabled();
      });
   }

   public HaloDesign selected() {
      return HaloDesign.byLabel((String)this.S.get());
   }

   public Studio studio() {
      return HaloRenderer3.of(this);
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientRegistry24.install(var1, List.of("assets/purxiy/textures/halo/glow.png")));
      var1.own(
         ClientCodec.install(
            var1,
            List.of(
               "shaders/core/halo.fsh",
               "shaders/core/halo.vsh",
               "shaders/core/profile_avatar.fsh",
               "shaders/core/profile_avatar.vsh",
               "shaders/include/halo.glsl",
               "shaders/include/theme.glsl",
               "shaders/include/tint.glsl"
            )
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      HaloRenderer7.install(this, var1);
      var1.own(HaloRenderer7::uninstall);
      var1.own(HaloRenderer4::close);
      var1.own(HaloRenderer2::close);
      var1.own(ClientTheme.install(new dev.purxiy.loader.fabric.ClientTheme.Listener() {
         public boolean active() {
            return HaloSupport9.active();
         }

         public float fov(float var1) {
            return HaloSupport9.fov(var1);
         }

         public boolean hides(class_1297 var1) {
            return HaloSupport9.hides(var1);
         }

         public void reset() {
            HaloRenderer3.closeOwned();
         }

         public boolean place(class_4184 var1, class_1937 var2, class_1297 var3, float var4, double[] var5, float[] var6) {
            return HaloSupport9.place(var1, var2, var3, var4, var5, var6);
         }

         public void pose(class_11890 var1, class_10055 var2) {
            if (HaloSupport9.active() && var1 == class_310.method_1551().field_1724) {
               float var3 = Easing.SMOOTHSTEP.apply(Math.clamp((HaloSupport9.progress() - 0.15F) / 0.6F, 0.0F, 1.0F));
               var2.field_53447 = class_3532.method_16439(var3, var2.field_53447, 0.0F);
               var2.field_53448 = class_3532.method_16439(var3, var2.field_53448, -2.0F);
            }
         }
      }));
      var1.own(ModuleWidget.claim("preview-halo", () -> HaloRenderer3.showing(this)));
      var1.own(ModuleWidget.onTick(HaloRenderer3::tick));
      var1.own(HaloRenderer3::closeOwned);
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> {
         ClientTheme.resetAll();
         return new HaloRenderer(this);
      }));
      var1.own(HaloSupport3::releaseResources);
      var1.own(ClientRegistry4.install(new dev.purxiy.loader.fabric.client.ClientRegistry4.Listener() {
         public void liftName(Object var1, class_10055 var2) {
            HaloRenderer7.liftName(var1, var2);
         }
      }));
      var1.own(ClientRenderer6.install(20, HaloRenderer5::draw));
      var1.own(ClientSupport3.VIEW.subscribe(0, HaloRenderer7::beginFrame));
      var1.own(ClientSupport3.AFTER_WORLD.subscribe(120, HaloRenderer7::render));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new HaloRenderer6(this);
   }
}
