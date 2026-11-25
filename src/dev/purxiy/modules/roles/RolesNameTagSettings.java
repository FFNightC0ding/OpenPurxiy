package dev.purxiy.modules.roles;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.loader.fabric.ModuleState;
import dev.purxiy.modules.Module;
import dev.purxiy.modules.SettingSection;

final class RolesNameTagSettings {
   final ModeSetting o;
   final ModeSetting d;
   final ModeSetting H;
   final BooleanSetting C;
   final BooleanSetting X;
   final NumberSetting e;

   private RolesNameTagSettings(long var1) {
      Module var3 = SettingSection.byId("name_tags");
      this.o = (ModeSetting)var3.setting("font");
      this.d = (ModeSetting)var3.setting("health");
      this.H = (ModeSetting)var3.setting("style");
      this.C = (BooleanSetting)var3.setting("team_colours");
      this.X = (BooleanSetting)var3.setting("faces");
      this.e = (NumberSetting)var3.setting("opacity");
   }

   static RolesNameTagSettings current() {
      long var0 = 29419877928899L;
      long var2 = var0 ^ 125696724246547L;
      return new RolesNameTagSettings(var2);
   }

   boolean enabled() {
      return ModuleState.moduleActive("name_tags");
   }

   RolesOverlay.RoleAlign look() {
      String var1 = (String)this.H.get();

      return switch (var1) {
         case "Solid" -> RolesOverlay.RoleAlign.y;
         case "Minimal" -> RolesOverlay.RoleAlign.o;
         default -> RolesOverlay.RoleAlign.G;
      };
   }
}
