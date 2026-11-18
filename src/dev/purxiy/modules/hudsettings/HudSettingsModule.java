// Sourced By DevFFNightmare
package dev.purxiy.modules.hudsettings;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.config.Setting;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ModuleDescriptor;

public final class HudSettingsModule extends ModuleDescriptor {
   public final NumberSetting C = this.number("scale", "HUD scale", "Size of every HUD element together", 1.0, 0.75, 1.5, 0.05, NumberSetting.Listener.PERCENT);
   public final BooleanSetting K = this.bool("glass", "Frosted glass", "Blur the world behind HUD panels", true);
   public final BooleanSetting I = this.bool("chat_editor", "Edit in chat", "Open the chat (T) to move, resize and arrange your HUD", true);
   public final BooleanSetting D = this.bool("snap", "Snap to guides", "Magnetic centre lines and alignment with other elements", true);
   public final BooleanSetting L = this.bool("no_overlap", "Prevent overlap", "Elements push each other aside instead of stacking", true);
   public static boolean Z;
   public static boolean t;



   public HudSettingsModule() {
      super("hud", "HUD", "Scale, glass and the HUD editor", "layout-dashboard");
   }

   public <S extends Setting<?>> S internal(S var1) {
      var1.visibleWhen(() -> false);
      return this.add((S)var1);
   }

   @Override
   protected void attach(ModuleContext var1) {
      var1.own(ClientServices.leaseHud(this.C.floatValue(), this.K.enabled(), this.L.enabled(), this.I.enabled(), this.D.enabled()));
   }

}
