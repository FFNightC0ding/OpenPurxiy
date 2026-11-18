// Sourced By DevFFNightmare
package dev.purxiy.ui.clickgui;

import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;

final class StudioTab {
   private static final int STARS = 150;
   private final float[] starX = new float[150];
   private final float[] starY = new float[150];
   private final float[] starDepth = new float[150];
   private final float[] starPhase = new float[150];

   StudioTab() {
      for (int var1 = 0; var1 < 150; var1++) {
         this.starX[var1] = hash(var1 * 4);
         this.starY[var1] = hash(var1 * 4 + 1);
         this.starDepth[var1] = 0.2F + 0.8F * hash(var1 * 4 + 2) * hash(var1 * 4 + 2);
         this.starPhase[var1] = hash(var1 * 4 + 3) * (float) (Math.PI * 2);
      }
   }

   void render(DrawContext var1, float var2) {
      Canvas var3 = var1.canvas;
      float var4 = var3.width();
      float var5 = var3.height();
      ClientServices.TypeH var6 = ClientServices.interfaceLook();
      boolean var7 = MotionStyle.reduced();
      float var8 = var6.dim();
      var3.blurStrength(var6.blur() ? var2 : 0.0F);
      if (var6.blur()) {
         var3.glass(0.0F, 0.0F, var4, var5, 0.0F, BlendMode.withAlpha(Theme.tint(-16448498), var8), var2, 1.15F, 0.012F);
      } else {
         var3.rect(0.0F, 0.0F, var4, var5, BlendMode.withAlpha(Theme.tint(-16448498), var8 * var2));
      }

      float var9 = var1.time;
      float var10 = var7 ? 0.0F : var1.mouseX / var4 - 0.5F;
      float var11 = var7 ? 0.0F : var1.mouseY / var5 - 0.5F;
      float var12 = var7 ? 0.0F : 1.0F;
      var3.glowCircle(
         var4 * 0.16F + (float)Math.sin(var9 * 0.11) * 40.0F * var12 - var10 * 20.0F,
         var5 * 0.24F + (float)Math.cos(var9 * 0.09) * 26.0F * var12 - var11 * 12.0F,
         var5 * 0.26F,
         var5 * 0.42F,
         BlendMode.withAlpha(Icons.INDIGO, 0.34F * var2)
      );
      var3.glowCircle(
         var4 * 0.86F + (float)Math.cos(var9 * 0.08) * 46.0F * var12 - var10 * 28.0F,
         var5 * 0.84F + (float)Math.sin(var9 * 0.1) * 30.0F * var12 - var11 * 16.0F,
         var5 * 0.28F,
         var5 * 0.45F,
         BlendMode.withAlpha(Icons.MAGENTA, 0.18F * var2)
      );
      var3.glowCircle(
         var4 * 0.55F + (float)Math.sin(var9 * 0.06) * 60.0F * var12 - var10 * 10.0F,
         var5 * 0.5F - var11 * 8.0F,
         var5 * 0.3F,
         var5 * 0.5F,
         BlendMode.withAlpha(Icons.VIOLET, 0.1F * var2)
      );
      if (var6.stars()) {
         for (int var13 = 0; var13 < 150; var13++) {
            float var14 = this.starDepth[var13];
            float var15 = var7 ? 0.7F : 0.5F + 0.5F * (float)Math.sin(var9 * (0.5F + var14 * 1.6F) + this.starPhase[var13]);
            float var16 = (0.08F + 0.55F * var15 * var14) * var2;
            var3.circle(
               this.starX[var13] * var4 - var10 * var14 * 18.0F,
               this.starY[var13] * var5 - var11 * var14 * 12.0F,
               0.4F + var14 * 0.95F,
               BlendMode.withAlpha(-1, var16)
            );
         }
      }

      var3.roundRectRadial(-var4 * 0.15F, -var5 * 0.15F, var4 * 1.3F, var5 * 1.3F, 0.0F, 0, BlendMode.withAlpha(-16777216, 0.42F * var2));
   }

   private static float hash(int var0) {
      double var1 = Math.sin(var0 * 12.9898 + 78.233) * 43758.5453;
      return (float)(var1 - Math.floor(var1));
   }
}
