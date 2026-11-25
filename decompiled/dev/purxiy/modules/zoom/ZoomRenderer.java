package dev.purxiy.modules.zoom;

import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.Locale;
import net.minecraft.class_310;

public final class ZoomRenderer {
   public static int v = 0;

   private ZoomRenderer() {
   }

   public static void render(ZoomModule var0) {
      class_310 var1 = class_310.method_1551();
      float var2 = var0.presence();
      if (var0.l.enabled()
         && !(var2 < 0.01F)
         && var1.field_1687 != null
         && var1.field_1724 != null
         && !var1.field_1690.field_1842
         && var1.field_1755 == null
         && var1.method_18506() == null) {
         Canvas var3 = RenderPipelines.get().hudCanvas();
         float var4 = var3.height() / 720.0F;
         var3.push();
         var3.translate(var3.width() / 2.0F, var3.height() / 2.0F + 62.0F * var4);
         var3.scale(var4);
         var3.translate(0.0F, (1.0F - var2) * 6.0F);
         var3.pushAlpha(var2 * 0.94F);
         var3.roundRect(-63.0F, -17.0F, 126.0F, 34.0F, 12.0F, Theme.tint(-888139748));
         var3.roundRectOutline(-63.0F, -17.0F, 126.0F, 34.0F, 12.0F, 0.8F, Theme.tint(852015359));
         var3.icon("zoom-in", -43.0F, -1.0F, 14.0F, Icons.PURPLE);
         var3.text(
            Fonts.SEMIBOLD, String.format(Locale.ROOT, "%.2f×", var0.magnification()), -27.0F, -1.0F, 12.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE
         );

         for (int var5 = 0; var5 < 7; var5++) {
            float var6 = 24 + var5 * 4;
            boolean var7 = var5 / 6.0 <= Math.log(var0.magnification()) / Math.log(32.0);
            var3.line(var6, 3.0F, var6, 3.0F - (4.0F + var5 * 0.7F), 1.5F, var7 ? Icons.PURPLE : 905969663);
         }

         var3.popAlpha();
         var3.pop();
      }
   }

   public static void ensureSourceClassInitialized() {
   }
}
