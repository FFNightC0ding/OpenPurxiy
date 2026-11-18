// Sourced By DevFFNightmare
package dev.purxiy.hud;

import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.ui.Icons;

public final class HudGroupBounds {
   private HudGroupBounds() {
   }

   public static float facing(float var0, boolean var1) {
      if (!var1) {
         return 0.0F;
      } else {
         double var2 = Math.toRadians(var0);
         return (float)Math.atan2(-Math.sin(var2), -Math.cos(var2));
      }
   }

   public static void arrow(Canvas var0, float var1, float var2, float var3, float var4, int var5) {
      var0.push();
      var0.translate(var1, var2);
      var0.rotate(var3);
      var0.scale(var4);
      float var6 = -5.2F;
      float var7 = 4.0F;
      float var8 = 3.9F;
      float var9 = 1.7F;
      var0.triangle(0.0F, var6, -var8, var7, 0.0F, var9, var5);
      var0.triangle(0.0F, var6, 0.0F, var9, var8, var7, var5);
      float var10 = 1.1F;
      var0.line(0.0F, var6, -var8, var7, var10, var5);
      var0.line(-var8, var7, 0.0F, var9, var10, var5);
      var0.line(0.0F, var9, var8, var7, var10, var5);
      var0.line(var8, var7, 0.0F, var6, var10, var5);
      var0.pop();
   }

   public static void iconTile(Canvas var0, float var1, float var2, float var3, float var4, String var5, float var6, float var7) {
      var0.roundRect(var1, var2, var3, var3, var4, BlendMode.lerp(268435455, 402653183, var7));
      if (var6 > 0.005F) {
         var0.glow(var1, var2, var3, var3, var4, 18.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.3F * var6));
         var0.pushAlpha(var6);
         var0.roundRectGradient(var1, var2, var3, var3, var4, 135.0F, Icons.INDIGO, Icons.MAGENTA);
         var0.roundRectCorners(var1, var2, var3, var3, var4, 872415231, 352321535, 16777215, 16777215);
         var0.popAlpha();
      }

      var0.roundRectOutline(var1, var2, var3, var3, var4, 1.0F, BlendMode.lerp(318767103, 654311423, Math.max(var7, var6)));
      var0.icon(var5, var1 + var3 / 2.0F, var2 + var3 / 2.0F, var3 * 0.46F, BlendMode.lerp(BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var7), Icons.TEXT, var6));
   }
}
