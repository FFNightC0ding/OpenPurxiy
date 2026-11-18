// Sourced By DevFFNightmare
package dev.purxiy.modules.knife;

import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import net.minecraft.class_3675.class_307;

public final class KnifeSupport {



   private KnifeSupport() {
   }

   public static void surface(Canvas var0, float var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      var0.shadow(var1, var2, var3, var4, var5, 22.0F + var6 * 22.0F, 0.0F, 0.0F, 6.0F + var6 * 10.0F, BlendMode.withAlpha(-16777216, 0.3F + var6 * 0.14F));
      if (var7 > 0.005F) {
         var0.glow(var1, var2, var3, var4, var5, 28.0F, BlendMode.withAlpha(Icons.VIOLET, 0.16F * var7));
      }

      var0.glass(var1, var2, var3, var4, var5, Icons.GLASS_TINT);
      var0.roundRectCorners(var1, var2, var3, var4, var5, 285212671, 167772159, 16777215, 50331647);
      var0.roundRectOutlineGradient(
         var1,
         var2,
         var3,
         var4,
         var5,
         1.0F,
         90.0F,
         BlendMode.lerp(721420287, BlendMode.withAlpha(Icons.PURPLE, 0.85F), var7),
         BlendMode.lerp(150994943, BlendMode.withAlpha(Icons.INDIGO, 0.35F), var7)
      );
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

   public static float keyCap(Canvas var0, String var1, float var2, float var3, float var4, int var5) {
      float var6 = var0.textWidth(Fonts.SEMIBOLD, var1, var4);
      float var7 = var4 + 10.0F;
      float var8 = Math.max(var7, var6 + 12.0F);
      var0.roundRectBordered(var2, var3 - var7 / 2.0F, var8, var7, 6.0F, 318767103, 1.0F, 486539263);
      var0.text(Fonts.SEMIBOLD, var1, var2 + var8 / 2.0F, var3, var4, var5, FontStyle.CENTER, FontWeight.MIDDLE);
      return var8;
   }

   public static String keyName(int var0) {
      if (var0 == -1) {
         return "None";
      } else {
         String var1 = class_307.field_1668.method_1447(var0).method_27445().getString();
         return var1.isEmpty() ? "Key " + var0 : var1;
      }
   }

}
