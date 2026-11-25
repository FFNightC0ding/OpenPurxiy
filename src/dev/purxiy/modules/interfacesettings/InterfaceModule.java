package dev.purxiy.modules.interfacesettings;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.NumberSetting.Listener;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;
import dev.purxiy.loader.fabric.client.ClientRenderer;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.ui.DrawContext;

public final class InterfaceModule extends ModuleDescriptor {
   public static final String d = "Original";
   public static final String z = "Accent";
   public static final String i = "Custom";
   public final ColorSetting I = this.color("accent", "Accent colour", "One colour for the menu, the HUD and your effects", -4944641, false);
   public final ModeSetting g = this.mode(
      "logo", "Logo colour", "Its own purple, your accent, or one you pick", "Original", new String[]{"Original", "Accent", "Custom"}
   );
   public final ColorSetting P = this.color("logo_color", "Colour", "The colour the dog takes on", -4944641, false);
   public final ModeSetting R = this.mode("language", "Language", "Auto follows the language of Minecraft", "Auto", new String[]{"Auto", "English", "Русский"});
   public final NumberSetting m = this.number("scale", "Interface scale", "Size of everything in the SourceByDevFFnightmare DeObfDeCompByGuideX menu", 1.0, 0.8, 1.25, 0.05, Listener.PERCENT);
   public final BooleanSetting M = this.bool("blur", "Background blur", "Frosted glass over the game behind the menu", true);
   public final NumberSetting S = this.number("dim", "Background dim", "How much the game darkens behind the menu", 0.45, 0.2, 0.85, 0.05, Listener.PERCENT);
   public final BooleanSetting B = this.bool("stars", "Starfield", "Twinkling stars around the orbit", true);
   public final BooleanSetting K = this.bool("pause", "Pause singleplayer", "Pause the game while the menu is open", false);
   public static boolean O;

   public InterfaceModule() {
      super("interface", "Interface", "Your colour, language, scale and the look of the menu", "monitor");
      this.P.visibleWhen(() -> this.g.is("Custom"));
   }

   protected void attach(ModuleContext var1) {
      var1.own(
         ClientServices.leaseInterface(
            this.I.get(),
            this.g.is("Accent") ? this.I.get() : (this.g.is("Custom") ? this.P.get() : -4944641),
            (String)this.R.get(),
            this.m.floatValue(),
            this.M.enabled(),
            this.S.floatValue(),
            this.B.enabled(),
            this.K.enabled()
         )
      );
      var1.own(ClientServices.acquire(this.id()));
      InterfaceRenderer var2 = new InterfaceRenderer(this);
      DrawContext var3 = new DrawContext();
      var1.own(ClientRenderer.install((var3xx, var4) -> {
         if (ClientServices.drawing()) {
            RenderPipelines var5 = RenderPipelines.get();
            var3.begin(var5.menuCanvas(), var5.time(), var5.delta(), var5.fromGui(var3xx), var5.fromGui(var4), false);
            var2.render(var3, 1.0F);
            var1.metric("backdrop_drawn", 1.0);
         }
      }));
   }
}
