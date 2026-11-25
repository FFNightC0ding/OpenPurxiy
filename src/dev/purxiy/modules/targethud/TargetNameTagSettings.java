package dev.purxiy.modules.targethud;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.loader.fabric.ModuleState;
import dev.purxiy.modules.Module;
import dev.purxiy.modules.SettingSection;

final class TargetNameTagSettings {
   final ModeSetting T;
   final ModeSetting f;
   final ModeSetting t;
   final BooleanSetting a;
   final BooleanSetting M;
   final NumberSetting Z;

   private TargetNameTagSettings(long var1) {
      Module var3 = SettingSection.byId("name_tags");
      this.T = (ModeSetting)var3.setting("font");
      this.f = (ModeSetting)var3.setting("health");
      this.t = (ModeSetting)var3.setting("style");
      this.a = (BooleanSetting)var3.setting("team_colours");
      this.M = (BooleanSetting)var3.setting("faces");
      this.Z = (NumberSetting)var3.setting("opacity");
   }

   static TargetNameTagSettings current() {
      long var0 = 23640687131728L;
      long var2 = var0 ^ 24766535423529L;
      return new TargetNameTagSettings(var2);
   }

   boolean enabled() {
      return ModuleState.moduleActive("name_tags");
   }

   TargetOverlay.TargetAlign look() {
      String var1 = (String)this.t.get();

      return switch (var1) {
         case "Solid" -> TargetOverlay.TargetAlign.w;
         case "Minimal" -> TargetOverlay.TargetAlign.F;
         default -> TargetOverlay.TargetAlign.O;
      };
   }
}
