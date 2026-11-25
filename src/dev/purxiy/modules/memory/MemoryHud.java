package dev.purxiy.modules.memory;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudElementGroup;
import dev.purxiy.render.Canvas;
import dev.purxiy.ui.Icons;
import java.util.Locale;

public final class MemoryHud extends HudElementGroup {
   private final BooleanSetting L = this.bool("amount", "Show amount", "Used and maximum memory next to the percentage", false);
   public static boolean y;
   public static int T = 0;

   public MemoryHud() {
      super("memory", "Memory", "How much memory the game is using", "memory-stick", 0.0, 0.0);
   }

   private static float v() {
      Runtime var0 = Runtime.getRuntime();
      return (float)(var0.totalMemory() - var0.freeMemory()) / (float)var0.maxMemory();
   }

   protected String value(HudBounds var1) {
      return Math.round(v() * 100.0F) + "%";
   }

   protected String sample() {
      return this.value(null);
   }

   protected String label(HudBounds var1) {
      if (!this.L.enabled()) {
         return null;
      } else {
         Runtime var2 = Runtime.getRuntime();
         double var3 = (float)(var2.totalMemory() - var2.freeMemory()) / 1.0737418E9F;
         double var5 = (float)var2.maxMemory() / 1.0737418E9F;
         return String.format(Locale.ROOT, "%.1f / %.1f GB", var3, var5);
      }
   }

   protected float extraWidth(HudBounds var1) {
      return 26.0F;
   }

   protected void renderExtra(Canvas var1, HudBounds var2, float var3, float var4) {
      float var5 = 26.0F;
      float var6 = 3.5F;
      float var7 = var4 / 2.0F - var6 / 2.0F;
      var1.roundRect(var3, var7, var5, var6, var6 / 2.0F, 654311423);
      float var8 = Math.min(1.0F, v());
      var1.roundRectGradient(var3, var7, Math.max(var6, var5 * var8), var6, var6 / 2.0F, 0.0F, Icons.INDIGO, var8 > 0.85F ? Icons.PINK : Icons.MAGENTA);
   }
}
