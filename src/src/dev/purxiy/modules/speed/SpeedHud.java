// Sourced By DevFFNightmare
package dev.purxiy.modules.speed;

import dev.purxiy.config.ModeSetting;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudElement;
import dev.purxiy.hud.HudElementGroup;
import java.util.Locale;

public final class SpeedHud extends HudElementGroup {
   private final ModeSetting A = this.mode("unit", "Unit", null, "Blocks/s", new String[]{"Blocks/s", "km/h"});
   public static int q = 0;
   public static boolean K;



   public SpeedHud() {
      super("speed", "Speed", "How fast you are moving", "trending-up", 0.0, 0.0);
   }

   private boolean v(long var1) {
      return this.A.is("km/h");
   }

   @Override
   protected String value(HudBounds var1) {
      long var2 = 140530870697879L;
      long var4 = var2 ^ 80106240137423L;
      float var6 = HudElement.speed() * (this.v(var4) ? 3.6F : 1.0F);
      return String.format(Locale.ROOT, this.v(var4) ? "%.1f" : "%.2f", var6);
   }

   @Override
   protected String sample() {
      long var1 = 117706836267994L;
      long var3 = var1 ^ 101262602646658L;
      return this.v(var3) ? "20.2" : "5.61";
   }

   @Override
   protected String label(HudBounds var1) {
      long var2 = 14776832909237L;
      long var4 = var2 ^ 64204203374829L;
      return this.v(var4) ? "km/h" : "b/s";
   }

}
