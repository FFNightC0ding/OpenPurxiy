// Sourced By DevFFNightmare
package dev.purxiy.modules.sessiontime;

import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudElement;
import dev.purxiy.hud.HudElementGroup;
import java.util.Locale;

public final class SessionHud extends HudElementGroup {
   public static boolean a;
   public static int b = 0;



   public SessionHud() {
      super("session_time", "Session Time", "How long you have been playing", "timer", 0.0, 0.0);
   }

   @Override
   protected String value(HudBounds var1) {
      long var2 = HudElement.sessionMillis() / 1000L;
      long var4 = var2 / 3600L;
      long var6 = var2 / 60L % 60L;
      long var8 = var2 % 60L;
      return var4 > 0L ? String.format(Locale.ROOT, "%d:%02d:%02d", var4, var6, var8) : String.format(Locale.ROOT, "%d:%02d", var6, var8);
   }

   @Override
   protected String sample() {
      return "1:24:08";
   }

}
