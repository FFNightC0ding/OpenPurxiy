// Sourced By DevFFNightmare
package dev.purxiy.modules.clock;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudElementGroup;
import dev.purxiy.i18n.Translations;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public final class ClockHud extends HudElementGroup {
   private static final DateTimeFormatter H = DateTimeFormatter.ofPattern("HH:mm", Locale.ROOT);
   private static final DateTimeFormatter j = DateTimeFormatter.ofPattern("HH:mm:ss", Locale.ROOT);
   private static final DateTimeFormatter X = DateTimeFormatter.ofPattern("h:mm", Locale.ROOT);
   private static final DateTimeFormatter I = DateTimeFormatter.ofPattern("h:mm:ss", Locale.ROOT);
   private static final DateTimeFormatter B = DateTimeFormatter.ofPattern("d MMM", Locale.ENGLISH);
   private static final DateTimeFormatter A = DateTimeFormatter.ofPattern("d MMM", Locale.forLanguageTag("ru"));
   private final ModeSetting f = this.mode("format", "Format", null, "24h", new String[]{"24h", "12h"});
   private final BooleanSetting K = this.bool("seconds", "Seconds", null, false);
   private final BooleanSetting w = this.bool("date", "Date", "Today's date next to the time", false);
   public static int T = 0;
   public static int e = 0;



   public ClockHud() {
      super("clock", "Clock", "Real-world time", "clock", 0.0, 0.0);
   }

   @Override
   protected String value(HudBounds var1) {
      LocalDateTime var2 = LocalDateTime.now();
      boolean var3 = this.f.is("12h");
      DateTimeFormatter var4 = var3 ? (this.K.enabled() ? I : X) : (this.K.enabled() ? j : H);
      return var2.format(var4);
   }

   @Override
   protected String sample() {
      return this.value(null);
   }

   @Override
   protected String label(HudBounds var1) {
      LocalDateTime var2 = LocalDateTime.now();
      String var3 = this.f.is("12h") ? (var2.getHour() < 12 ? "AM" : "PM") : null;
      if (this.w.enabled()) {
         String var4 = var2.format(Translations.russian() ? A : B);
         return var3 == null ? var4 : var3 + "  " + var4;
      } else {
         return var3;
      }
   }

}
