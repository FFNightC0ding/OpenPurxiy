// Sourced By DevFFNightmare
package dev.purxiy.onboarding;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.modules.Module;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.ui.clickgui.StudioAnchor;
import java.util.List;
import java.util.function.BooleanSupplier;

final class EssentialsStep extends OnboardingPage {
   EssentialsStep(WelcomeScreen var1) {
      super(var1, "The essentials", "A few last things", "Settle these once and you are good to go.");
      this.rows
         .add(new OnboardingPage.TypeC("keyboard", "Menu key", "Opens Purxiy anywhere, even on the title screen", StudioAnchor::key, StudioAnchor::setKey));
      List var2 = this.rows;
      BooleanSetting var3 = SettingSection.PROFILE.track;
      BooleanSupplier var4 = var3::enabled;
      BooleanSetting var5 = SettingSection.PROFILE.track;
      var2.add(
         new OnboardingPage.TypeO("chart-line", "Profile statistics", "Play time, distance and records. They never leave your computer.", var4, var5::set)
      );
      var2 = this.rows;
      Module var6 = SettingSection.NICK_HIDER;
      BooleanSupplier var7 = var6::switchedOn;
      Module var8 = SettingSection.NICK_HIDER;
      var2.add(
         new OnboardingPage.TypeO("venetian-mask", "Streamer mode", "Hides your Minecraft name on screen, for streams and videos", var7, var8::setEnabled)
      );
   }

   static int menuKey() {
      return StudioAnchor.key();
   }
}
