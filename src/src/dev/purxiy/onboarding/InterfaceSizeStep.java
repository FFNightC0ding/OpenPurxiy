// Sourced By DevFFNightmare
package dev.purxiy.onboarding;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.ui.DrawContext;
import java.util.List;
import java.util.function.BooleanSupplier;

final class InterfaceSizeStep extends OnboardingPage {
   private static final List<String> SIZES = List.of("Small", "Default", "Large");
   private static final double[] SCALES = new double[]{0.9, 1.0, 1.15};
   private final ModeSetting size = new ModeSetting("size", "Interface size", (String)null, "Default", SIZES);
   private int applied;

   InterfaceSizeStep(WelcomeScreen var1) {
      super(var1, "Comfort", "How should Purxiy feel?", "Pick what suits you. Everything here lives in Client later on, too.");
      double var2 = SettingSection.INTERFACE.scale.get();
      int var4 = 1;

      for (int var5 = 0; var5 < SCALES.length; var5++) {
         if (Math.abs(SCALES[var5] - var2) < Math.abs(SCALES[var4] - var2)) {
            var4 = var5;
         }
      }

      this.size.select(var4);
      this.applied = var4;
      this.rows.add(new OnboardingPage.TypeB("scaling", "Interface size", "How big the menu and its panels are", this.size));
      List var13 = this.rows;
      BooleanSetting var6 = SettingSection.SOUND.enabled;
      BooleanSupplier var7 = var6::enabled;
      BooleanSetting var8 = SettingSection.SOUND.enabled;
      var13.add(new OnboardingPage.TypeO("volume-2", "Interface sounds", "Soft sounds when you click, type and switch", var7, var8::set));
      var13 = this.rows;
      BooleanSetting var9 = SettingSection.MOTION.reduce;
      BooleanSupplier var10 = var9::enabled;
      var8 = SettingSection.MOTION.reduce;
      var13.add(new OnboardingPage.TypeO("waves", "Reduce motion", "Calmer: no parallax, floating or cascades", var10, var8::set));
      var13 = this.rows;
      BooleanSetting var11 = SettingSection.INTERFACE.blur;
      BooleanSupplier var12 = var11::enabled;
      var8 = SettingSection.INTERFACE.blur;
      var13.add(new OnboardingPage.TypeO("blend", "Background blur", "Frosted glass behind the menu; off is lighter on slow computers", var12, var8::set));
   }

   @Override
   void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      if (this.size.index() != this.applied) {
         this.applied = this.size.index();
         SettingSection.INTERFACE.scale.set(SCALES[this.applied]);
      }

      super.render(var1, var2, var3, var4, var5);
   }
}
