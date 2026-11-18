// Sourced By DevFFNightmare
package dev.purxiy.modules.cps;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudElement;
import dev.purxiy.hud.HudElementGroup;

public final class CpsHud extends HudElementGroup {
   private final BooleanSetting Q = this.bool("right", "Right clicks", "Show right clicks next to left clicks", true);
   public static boolean c;
   public static boolean i;



   public CpsHud() {
      super("cps", "CPS", "Clicks per second, left and right", "mouse-pointer-click", 0.0, 0.0);
   }

   @Override
   protected String value(HudBounds var1) {
      return this.Q.enabled() ? HudElement.cps(0) + " · " + HudElement.cps(1) : Integer.toString(HudElement.cps(0));
   }

   @Override
   protected String sample() {
      return this.Q.enabled() ? "8 · 3" : "8";
   }

   @Override
   protected String label(HudBounds var1) {
      return "CPS";
   }

}
