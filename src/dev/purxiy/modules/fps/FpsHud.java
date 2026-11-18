// Sourced By DevFFNightmare
package dev.purxiy.modules.fps;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudElement;
import dev.purxiy.hud.HudElementGroup;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.ui.Icons;

public final class FpsHud extends HudElementGroup {
   private static final int H = 16;
   private static final float M = 2.1F;
   private static final float Y = 1.3F;
   private final BooleanSetting f = this.bool("graph", "Frame graph", "A tiny live graph of recent frame times", true);
   public static boolean V;
   public static int U = 0;



   public FpsHud() {
      super("fps", "FPS", "Frames per second, with a live frame graph", "activity", 0.0, 0.0);
      this.enabledByDefault();
   }

   @Override
   protected String value(HudBounds var1) {
      return Integer.toString(HudElement.fps());
   }

   @Override
   protected String sample() {
      return Integer.toString(Math.max(1, HudElement.fps()));
   }

   @Override
   protected String label(HudBounds var1) {
      return "FPS";
   }

   @Override
   protected float extraWidth(HudBounds var1) {
      return this.f.enabled() ? 32.8F : 0.0F;
   }

   @Override
   protected void renderExtra(Canvas var1, HudBounds var2, float var3, float var4) {
      float var5 = 0.0F;
      float var6 = 0.0F;

      for (int var7 = 0; var7 < 16; var7++) {
         var5 += HudElement.frameTime(var7);
         var6 = Math.max(var6, HudElement.frameTime(var7));
      }

      float var16 = var5 / 16.0F;
      float var8 = Math.max(Math.max(var16 * 2.0F, var6), 1.0E-4F);
      float var9 = var4 * 0.5F;
      float var10 = var4 * 0.25F;

      for (int var11 = 0; var11 < 16; var11++) {
         float var12 = HudElement.frameTime(15 - var11);
         float var13 = Math.max(1.5F, var9 * Math.min(1.0F, var12 / var8));
         float var14 = var16 > 0.0F ? Math.max(0.0F, Math.min(1.0F, (var12 / var16 - 1.25F) * 1.5F)) : 0.0F;
         int var15 = BlendMode.lerp(Icons.VIOLET, Icons.PINK, var14);
         var1.roundRect(var3 + var11 * 2.1F, var10 + var9 - var13, 1.3F, var13, 0.65F, BlendMode.withAlpha(var15, 0.45F + 0.55F * (var11 + 1) / 16.0F));
      }
   }

}
