// Sourced By DevFFNightmare
package dev.purxiy.modules.direction;

import dev.purxiy.config.ModeSetting;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudModule;
import dev.purxiy.i18n.Translations;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import java.util.Locale;
import net.minecraft.class_3532;

public final class DirectionHud extends HudModule {
   private static final float B = 150.0F;
   private static final float L = 22.0F;
   private static final float c = 180.0F;
   private static final String[] E = new String[]{"S", "SW", "W", "NW", "N", "NE", "E", "SE"};
   private static final String[] T = new String[]{"Ю", "ЮЗ", "З", "СЗ", "С", "СВ", "В", "ЮВ"};
   private final ModeSetting y = this.mode(
      "degrees", "Degrees", "Heading counts clockwise from north, yaw matches the debug screen", "Heading", new String[]{"Heading", "Yaw", "Off"}
   );
   public static boolean i;
   public static boolean w;



   public DirectionHud() {
      super("direction", "Direction", "A compass strip that glides with your view", "compass", 0.5, 0.0);
      this.enabledByDefault();
   }

   @Override
   protected boolean mergeable() {
      return true;
   }

   @Override
   protected void measure(HudBounds var1, float[] var2) {
      var2[0] = 150.0F;
      var2[1] = 22.0F + (this.y.is("Off") ? 0 : 10);
   }

   @Override
   protected void render(Canvas var1, HudBounds var2) {
      float var3 = var2.live() ? var2.player.method_5705(var2.partialTick) : 180.0F + 25.0F * (float)Math.sin(var2.time * 0.6);
      float var4 = (var3 % 360.0F + 360.0F) % 360.0F;
      this.panel(var1, 150.0F, 22.0F);
      float var5 = 75.0F;
      float var6 = 0.8333333F;
      var1.pushClip(0.0F, 0.0F, 150.0F, 22.0F, 7.0F);
      int var7 = (int)Math.floor((var4 - 90.0F) / 15.0F) * 15;

      for (int var8 = var7; var8 <= var4 + 90.0F + 15.0F; var8 += 15) {
         float var9 = var5 + (var8 - var4) * var6;
         float var10 = 1.0F - Math.abs(var9 - var5) / 75.0F;
         float var11 = Math.max(0.0F, Math.min(1.0F, var10 * 2.2F));
         if (!(var11 <= 0.01F)) {
            int var12 = Math.floorMod(var8, 360);
            if (var12 % 45 == 0) {
               String var13 = E[var12 / 45];
               boolean var14 = var13.length() == 1;
               int var15 = var13.equals("N") ? Icons.PINK : (var14 ? Icons.TEXT : Icons.TEXT_2);
               String var16 = Translations.russian() ? T[var12 / 45] : var13;
               this.text(
                  var1,
                  var14 ? Fonts.BOLD : Fonts.SEMIBOLD,
                  var16,
                  var9,
                  11.5F,
                  var14 ? 9.5F : 8.0F,
                  BlendMode.fade(var15, var11),
                  FontStyle.CENTER,
                  FontWeight.MIDDLE
               );
            } else {
               var1.roundRect(var9 - 0.5F, 8.5F, 1.0F, 5.0F, 0.5F, BlendMode.fade(1509949439, var11));
            }
         }
      }

      var1.popClip();
      var1.roundRectGradient(var5 - 5.0F, 19.5F, 10.0F, 2.0F, 1.0F, 0.0F, Icons.INDIGO, Icons.MAGENTA);
      if (!this.y.is("Off")) {
         String var17 = this.y.is("Yaw")
            ? String.format(Locale.ROOT, "%.1f°", class_3532.method_15393(var3))
            : String.format(Locale.ROOT, "%d°", Math.round(var4 + 180.0F) % 360);
         this.text(var1, Fonts.SEMIBOLD, var17, var5, 27.5F, 8.0F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
      }
   }

}
