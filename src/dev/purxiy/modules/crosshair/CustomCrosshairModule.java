package dev.purxiy.modules.crosshair;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.CosmeticRegistry;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.ui.Theme;

public final class CustomCrosshairModule extends ModuleDescriptor {
   public final ModeSetting k;
   public final ModeSetting E;
   public final ModeSetting B;
   public final ModeSetting d;
   public final ModeSetting M;
   public final NumberSetting b;
   public final NumberSetting r;
   public final NumberSetting z;
   public final NumberSetting Q;
   public final NumberSetting s;
   public final NumberSetting N;
   public final NumberSetting i;
   public final NumberSetting x;
   public final NumberSetting n;
   public final NumberSetting u;
   public final NumberSetting t;
   public final NumberSetting v;
   public final BooleanSetting O;
   public final BooleanSetting g;
   public final BooleanSetting T;
   public final BooleanSetting C;
   public final BooleanSetting D;
   public final BooleanSetting a;
   public final ColorSetting A;
   public final ColorSetting S;
   public final ColorSetting U;
   public static int I = 0;

   public CustomCrosshairModule() {
      super("custom_crosshair", "Custom Crosshair", "Your aim, in SourceByDevFFnightmare DeObfDeCompByGuideX colours. Make it yours.", "crosshair");
      long var1 = 6176111832498L;
      long var3 = var1 ^ 39409733318462L;
      var3 = var1 ^ 112801926760072L;
      this.k = this.mode(
         "style", "Style", "Six signatures. One perfect centre.", "Vesper", new String[]{"Vesper", "Cross", "Halo", "Diamond", "Brackets", "Dot"}
      );
      this.b = this.number("size", "Size", "Independent of HUD and GUI scale", 1.0, 0.6, 2.0, 0.05, Listener.MULTIPLIER);
      this.r = this.number("thickness", "Thickness", "Bold strokes with a precise centre", 2.4, 1.0, 5.0, 0.2, Listener.unit(" px"));
      this.E = this.mode("palette", "Palette", "Colours from the SourceByDevFFnightmare DeObfDeCompByGuideX night sky", "SourceByDevFFnightmare DeObfDeCompByGuideX", new String[]{"SourceByDevFFnightmare DeObfDeCompByGuideX", "Frost", "Rose", "Custom"});
      this.B = this.mode("animation", "Idle animation", "The centre always stays still", "Breathe", new String[]{"Off", "Breathe", "Orbit"});
      this.O = this.bool("advanced", "Advanced settings", "Shape, light, reactions and sound", false);
      this.section("Shape & light");
      this.z = this.number("gap", "Centre gap", "Clear space around your aim", 3.5, 1.0, 10.0, 0.5, Listener.unit(" px"));
      this.Q = this.number("length", "Arm length", "Also changes the radius of closed shapes", 4.5, 2.0, 12.0, 0.5, Listener.unit(" px"));
      this.g = this.bool("dot", "Centre dot", "A fixed, bright aiming point", true);
      this.s = this.number("outline", "Outline", "Dark edge for bright backgrounds", 1.0, 0.0, 3.0, 0.25, Listener.unit(" px"));
      this.N = this.number("glow", "Glow", "A soft violet halo around the strokes", 0.25, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.i = this.number("opacity", "Opacity", "Keep the crosshair clearly visible", 1.0, 0.25, 1.0, 0.05, Listener.PERCENT);
      this.A = this.color("primary", "Primary colour", "Inner stroke", -1782273, false).followsAccent();
      this.S = this.color("secondary", "Secondary colour", "Outer stroke and orbit", -3243009, false).followsAccent();
      this.section("Motion & feedback");
      this.x = this.number("speed", "Animation speed", "Zero freezes the decorative animation", 1.0, 0.0, 3.0, 0.1, Listener.MULTIPLIER);
      this.n = this.number("breathing", "Breathing amount", "Gentle movement of the outer strokes", 0.3, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.u = this.number("spread", "Movement spread", "Visual response to walking; not weapon accuracy", 0.0, 0.0, 6.0, 0.5, Listener.unit(" px"));
      this.t = this.number("kick", "Attack pulse", "Outer strokes open as you swing", 1.5, 0.0, 6.0, 0.5, Listener.unit(" px"));
      this.T = this.bool("target_tint", "Target highlight", "Tint when aiming at a visible living entity", true);
      this.U = this.color("target_color", "Target colour", "Soft rose when a target is in your sights", -21809, false);
      this.C = this.bool("hit_marker", "Hit confirmation", "A brief sparkle on server-confirmed damage you deal", true);
      this.d = this.mode(
         "cooldown", "Attack indicator", "Auto follows Minecraft's crosshair indicator option", "Auto", new String[]{"Auto", "Ring", "Bar", "Off"}
      );
      this.M = this.mode("sound", "Hit sound", "Only for confirmed hits; respects Client > Sound", "Off", new String[]{"Off", "Soft", "Chime"});
      this.v = this.number("volume", "Hit volume", "Keep combat feedback subtle", 0.45, 0.0, 1.0, 0.05, Listener.PERCENT);
      this.section("Visibility");
      this.D = this.bool("third_person", "Third person", "Also show the reticle in third person", false);
      this.a = this.bool("hide_using", "Hide while using items", "Fade while eating, blocking or drawing a bow", false);
      boolean var5 = false;

      for (Setting var7 : this.settings()) {
         if (var5) {
            var7.visibleWhen(this.O::enabled);
         }

         if (var7 == this.O) {
            var5 = true;
         }
      }

      this.A.visibleWhen(() -> {
         CrosshairOverlay.ensureSourceClassInitialized();
         return this.O.enabled() && this.E.is("Custom");
      });
      this.S.visibleWhen(() -> {
         CrosshairOverlay.ensureSourceClassInitialized();
         return this.O.enabled() && this.E.is("Custom");
      });
      this.U.visibleWhen(() -> {
         CrosshairOverlay.ensureSourceClassInitialized();
         return this.O.enabled() && this.T.enabled();
      });
      this.v.visibleWhen(() -> {
         CrosshairOverlay.ensureSourceClassInitialized();
         return this.O.enabled() && !this.M.is("Off");
      });
      this.x.visibleWhen(() -> {
         CrosshairOverlay.ensureSourceClassInitialized();
         return this.O.enabled() && !this.B.is("Off");
      });
      this.n.visibleWhen(() -> {
         CrosshairOverlay.ensureSourceClassInitialized();
         return this.O.enabled() && this.B.is("Breathe");
      });
   }

   public void applyPreset(int var1) {
      boolean var2 = this.O.enabled();
      this.settings().forEach(var0 -> var0.reset());
      this.O.set(var2);
      if (var1 == 0) {
         this.k.set("Cross");
         this.B.set("Off");
         this.b.set(0.85);
         this.N.set(0.0);
         this.t.set(0.0);
         this.T.set(false);
      } else if (var1 == 2) {
         this.k.set("Halo");
         this.B.set("Orbit");
         this.N.set(0.55);
         this.u.set(2.0);
         this.t.set(3.0);
         this.M.set("Chime");
      }
   }

   public int primaryColor() {
      String var1 = (String)this.E.get();

      return switch (var1) {
         case "Frost" -> -1967361;
         case "Rose" -> -9747;
         case "Custom" -> this.A.get();
         default -> Theme.tint(-1782273);
      };
   }

   public int secondaryColor() {
      String var1 = (String)this.E.get();

      return switch (var1) {
         case "Frost" -> -7946241;
         case "Rose" -> -32568;
         case "Custom" -> this.S.get();
         default -> Theme.tint(-3243009);
      };
   }

   public void playHitSound() {
      if (!this.M.is("Off")) {
         UiAudioPolicy.play(this.M.is("Chime") ? "ORBIT_TICK" : "CLICK", this.M.is("Chime") ? 1.45F : 1.15F, this.v.floatValue());
      }
   }

   protected void onEnable() {
      CrosshairRenderPass.reset();
   }

   protected void onDisable() {
      CrosshairRenderPass.reset();
   }

   protected void attach(ModuleContext var1) throws Exception {
      var1.own(ClientServices.acquire(this.id()));
      var1.own(CrosshairRenderPass.install(this, var1));
      var1.onHud(var0 -> {
         if (ClientServices.drawing()) {
            CrosshairRenderPass.render();
         }
      });
      var1.own(CosmeticRegistry.register(this.id(), this.getClass().getClassLoader(), () -> new CustomCrosshairScreen(this)));
   }

   protected dev.purxiy.loader.fabric.CosmeticCatalog.Listener createPreview() {
      return new CrosshairEditorPage(this);
   }
}
