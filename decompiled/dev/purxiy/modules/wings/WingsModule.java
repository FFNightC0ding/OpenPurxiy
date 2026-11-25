package dev.purxiy.modules.wings;

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
import dev.purxiy.loader.fabric.client.ClientRegistry11;
import dev.purxiy.loader.fabric.client.ClientRenderer6;
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

public final class WingsModule extends ModuleDescriptor {
   public static final String N = "Folded";
   public static final String x = "Unfolded";
   public final ModeSetting n;
   public final ColorSetting p;
   public final NumberSetting l;
   public final NumberSetting k;
   public final ModeSetting f;
   public final BooleanSetting v;
   public final BooleanSetting m;

   public WingsModule() {
      super("wings", "Wings", "A pair of softly animated wings", "feather");
      this.n = this.mode("design", "Wings", "Which wings you wear", WingDesign.r.x, WingDesign.labels());
      this.p = this.section("Look").color("tint", "Colour", "The colour in your wings", -4222985, false).followsAccent();
      this.l = this.number("size", "Size", "How large they are", 0.9, 0.5, 1.3, 0.05, Listener.PERCENT);
      this.k = this.number("glow", "Glow", "How brightly they shine in the dark", 0.7, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.f = this.section("Movement").mode("rest", "At rest", "How they rest while you stand", "Folded", new String[]{"Folded", "Unfolded"});
      this.v = this.bool("jump", "Beat as you jump", "Two strong beats lift you off the ground", true);
      this.m = this.bool("elytra", "In place of your elytra", "Gliding, your wings carry you instead", true);
      this.p.visibleWhen(() -> this.selected().h);
   }

   public WingDesign selected() {
      return WingDesign.byLabel((String)this.n.get());
   }

   public boolean restsOpen() {
      return this.f.is("Unfolded");
   }

   public boolean flapOnJump() {
      return this.v.enabled();
   }

   public float size() {
      return ((Double)this.l.get()).floatValue();
   }

   private void F() {
      WingsCache.prepare();
   }

   public Studio studio() {
      return WingsRenderer2.of(this);
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(
         ClientCodec.install(
            var1, List.of("shaders/core/profile_avatar.fsh", "shaders/core/profile_avatar.vsh", "shaders/include/theme.glsl", "shaders/include/tint.glsl")
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      WingsUpdater2.install(this, var1);
      var1.own(WingsUpdater2::uninstall);
      var1.own(WingsRenderer7::close);
      var1.own(ClientTheme.install(new dev.purxiy.loader.fabric.ClientTheme.Listener() {
         public boolean active() {
            return WingsSupport.active();
         }

         public float fov(float var1) {
            return WingsSupport.fov(var1);
         }

         public boolean hides(class_1297 var1) {
            return WingsSupport.hides(var1);
         }

         public void reset() {
            WingsRenderer2.closeOwned();
         }

         public boolean place(class_4184 var1, class_1937 var2, class_1297 var3, float var4, double[] var5, float[] var6) {
            return WingsSupport.place(var1, var2, var3, var4, var5, var6);
         }

         public void pose(class_11890 var1, class_10055 var2) {
            if (WingsSupport.active() && var1 == class_310.method_1551().field_1724) {
               float var3 = Easing.SMOOTHSTEP.apply(Math.clamp((WingsSupport.progress() - 0.15F) / 0.6F, 0.0F, 1.0F));
               var2.field_53447 = class_3532.method_16439(var3, var2.field_53447, 0.0F);
               var2.field_53448 = class_3532.method_16439(var3, var2.field_53448, -2.0F);
            }
         }
      }));
      var1.own(ModuleWidget.claim("preview-wings", () -> WingsRenderer2.showing(this)));
      var1.own(ModuleWidget.onTick(WingsRenderer2::tick));
      var1.own(WingsRenderer2::closeOwned);
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> {
         ClientTheme.resetAll();
         return new WingsRenderer(this);
      }));
      this.F();
      var1.own(ClientRegistry11.install(new dev.purxiy.loader.fabric.client.ClientRegistry11.Listener() {
         public boolean wears(class_10055 var1) {
            return WingsUpdater2.wears(var1);
         }

         public boolean hidesElytra(class_10055 var1) {
            return WingsUpdater2.hidesElytra(var1);
         }
      }));
      var1.own(ClientRenderer6.install(10, WingsRenderer5::draw));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new WingsRenderer6(this);
   }
}
