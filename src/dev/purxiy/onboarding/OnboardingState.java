package dev.purxiy.onboarding;

import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.KeyLabel;
import java.util.Locale;

final class OnboardingState {
   static final float ROW = 62.0F;

   private OnboardingState() {
   }

   static void ring(Canvas var0, float var1, float var2, float var3, float var4, float var5, float var6, boolean var7, float var8) {
      if (!(var6 <= 0.01F) && !(var8 <= 0.001F)) {
         var0.push();
         var0.translate(var1, var2);
         var0.rotate(var5);
         var0.scale(1.0F, var4 / var3);
         int var9 = BlendMode.withAlpha(Icons.PURPLE, (var7 ? 0.85F : 0.32F) * var6);
         int var10 = BlendMode.withAlpha(Icons.INDIGO, (var7 ? 0.5F : 0.16F) * var6);
         if (var7) {
            var0.arc(0.0F, 0.0F, var3, 2.4F, 0.0F, 180.0F * var8, var9, var10, false);
            var0.arc(
               0.0F, 0.0F, var3, 10.0F, 25.0F, 130.0F * var8, BlendMode.withAlpha(Icons.MAGENTA, 0.08F * var6), BlendMode.withAlpha(Icons.VIOLET, 0.0F), false
            );
         } else {
            var0.arc(0.0F, 0.0F, var3, 2.0F, 180.0F, 180.0F * var8, var10, var9, false);
         }

         var0.pop();
      }
   }

   static void moons(Canvas var0, float var1, float var2, float var3, float var4, float var5, float var6, float var7, boolean var8, int var9) {
      if (!(var7 <= 0.01F)) {
         float var10 = (float)Math.cos(var5);
         float var11 = (float)Math.sin(var5);

         for (int var12 = 0; var12 < var9; var12++) {
            double var13 = (MotionStyle.reduced() ? 0.6 : var6 * 0.42) + var12 * Math.PI * 2.0 / var9;
            float var15 = (float)Math.cos(var13) * var3;
            float var16 = (float)Math.sin(var13) * var4;
            if (Math.sin(var13) > 0.0 == var8) {
               float var17 = var1 + var15 * var10 - var16 * var11;
               float var18 = var2 + var15 * var11 + var16 * var10;
               float var19 = (float)(Math.sin(var13) + 1.0) / 2.0F;
               float var20 = (var12 == 0 ? 3.4F : 2.3F) * (0.7F + 0.3F * var19);
               float var21 = var7 * (0.35F + 0.65F * var19);
               var0.glowCircle(var17, var18, var20, var20 * 4.0F, BlendMode.withAlpha(var12 == 0 ? Icons.MAGENTA : Icons.VIOLET, 0.45F * var21));
               var0.circle(var17, var18, var20, BlendMode.withAlpha(BlendMode.lerp(-1, Icons.PINK, var12 == 0 ? 0.35F : 0.1F), var21));
            }
         }
      }
   }

   static void eyebrow(Canvas var0, String var1, float var2, float var3, float var4) {
      String var5 = var1.toUpperCase(Locale.ROOT);
      float var6 = var0.textWidth(Fonts.SEMIBOLD, var5, 10.5F, 0.16F);
      float var7 = var2 - (var6 + 14.0F) / 2.0F;
      var0.glowCircle(var7 + 3.0F, var3, 2.6F, 8.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.5F * var4));
      var0.circle(var7 + 3.0F, var3, 2.6F, BlendMode.withAlpha(Icons.PINK, var4));
      var0.text(Fonts.SEMIBOLD, var5, var7 + 14.0F, var3, 10.5F, BlendMode.withAlpha(Icons.TEXT_2, var4), FontStyle.LEFT, FontWeight.MIDDLE, 0.16F);
   }

   static void option(Canvas var0, float var1, float var2, float var3, String var4, String var5, String var6, float var7, float var8) {
      float var9 = var2 + 31.0F;
      if (var8 > 0.01F) {
         var0.roundRect(var1 + 6.0F, var2 + 4.0F, var3 - 12.0F, 54.0F, 14.0F, BlendMode.withAlpha(-1, 0.035F * var8));
      }

      KeyLabel.iconTile(var0, var1 + 16.0F, var9 - 18.0F, 36.0F, 11.0F, var4, 0.0F, var8);
      float var10 = var1 + 16.0F + 36.0F + 14.0F;
      float var11 = var3 - (var10 - var1) - var7 - 28.0F;
      var0.text(Fonts.MEDIUM, var5, var10, var9 - 9.0F, 13.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      var0.text(
         Fonts.REGULAR,
         FontMetrics.ellipsize(Fonts.REGULAR, var6, 11.5F, Math.max(40.0F, var11), 0.0F),
         var10,
         var9 + 10.0F,
         11.5F,
         Icons.TEXT_3,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
   }

   static void divider(Canvas var0, float var1, float var2, float var3) {
      var0.rect(var1 + 18.0F, var2, var3 - 36.0F, 1.0F, 234881023);
   }

   static float clamp01(float var0) {
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   static float hash(int var0) {
      double var1 = Math.sin(var0 * 12.9898 + 78.233) * 43758.5453;
      return (float)(var1 - Math.floor(var1));
   }
}
