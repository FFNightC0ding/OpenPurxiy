// Sourced By DevFFNightmare
package dev.purxiy.onboarding;

import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.MenuCommands;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;

final class OnboardingProgress {
   private static final int STARS = 170;
   private final float[] starX = new float[170];
   private final float[] starY = new float[170];
   private final float[] starDepth = new float[170];
   private final float[] starPhase = new float[170];

   OnboardingProgress() {
      for (int var1 = 0; var1 < 170; var1++) {
         this.starX[var1] = OnboardingState.hash(var1 * 4 + 11);
         this.starY[var1] = OnboardingState.hash(var1 * 4 + 12) * 0.9F;
         float var2 = OnboardingState.hash(var1 * 4 + 13);
         this.starDepth[var1] = 0.2F + 0.8F * var2 * var2;
         this.starPhase[var1] = OnboardingState.hash(var1 * 4 + 14) * (float) (Math.PI * 2);
      }
   }

   void render(DrawContext var1, float var2, float var3) {
      Canvas var4 = var1.canvas;
      float var5 = var4.width();
      float var6 = var4.height();
      MenuCommands.TypeG var7 = SettingSection.INTERFACE;
      boolean var8 = MotionStyle.reduced();
      float var9 = var1.time;
      var4.blurStrength(var7.blur.enabled() ? var2 : 0.0F);
      if (var7.blur.enabled()) {
         var4.glass(0.0F, 0.0F, var5, var6, 0.0F, BlendMode.withAlpha(Theme.tint(-16448498), 0.62F), var2, 1.1F, 0.012F);
      } else {
         var4.rect(0.0F, 0.0F, var5, var6, BlendMode.withAlpha(Theme.tint(-16448498), 0.8F * var2));
      }

      var4.roundRectGradient(
         0.0F, 0.0F, var5, var6, 0.0F, 90.0F, BlendMode.withAlpha(Theme.tint(-16580086), 0.55F * var2), BlendMode.withAlpha(Theme.tint(-16054504), 0.1F * var2)
      );
      float var10 = var8 ? 0.0F : var1.mouseX / var5 - 0.5F;
      float var11 = var8 ? 0.0F : var1.mouseY / var6 - 0.5F;
      float var12 = var8 ? 0.0F : 1.0F;
      var4.glowCircle(
         var5 * 0.2F + (float)Math.sin(var9 * 0.1) * 44.0F * var12 - var10 * 20.0F,
         var6 * 0.26F + (float)Math.cos(var9 * 0.08) * 26.0F * var12 - var11 * 12.0F,
         var6 * 0.26F,
         var6 * 0.46F,
         BlendMode.withAlpha(Icons.INDIGO, 0.3F * var2)
      );
      var4.glowCircle(
         var5 * 0.82F + (float)Math.cos(var9 * 0.07) * 50.0F * var12 - var10 * 28.0F,
         var6 * 0.3F + (float)Math.sin(var9 * 0.09) * 30.0F * var12 - var11 * 16.0F,
         var6 * 0.22F,
         var6 * 0.42F,
         BlendMode.withAlpha(Icons.MAGENTA, 0.13F * var2)
      );
      var4.glowCircle(
         var5 * 0.5F + (float)Math.sin(var9 * 0.05) * 60.0F * var12 - var10 * 10.0F,
         var6 * 0.55F - var11 * 8.0F,
         var6 * 0.3F,
         var6 * 0.5F,
         BlendMode.withAlpha(Icons.VIOLET, 0.08F * var2)
      );

      for (int var13 = 0; var13 < 170; var13++) {
         float var14 = this.starDepth[var13];
         float var15 = var8 ? 0.7F : 0.5F + 0.5F * (float)Math.sin(var9 * (0.5F + var14 * 1.6F) + this.starPhase[var13]);
         float var16 = (0.07F + 0.5F * var15 * var14) * var2;
         var4.circle(
            this.starX[var13] * var5 - var10 * var14 * 18.0F,
            this.starY[var13] * var6 - var11 * var14 * 12.0F,
            0.4F + var14 * 0.95F,
            BlendMode.withAlpha(-1, var16)
         );
      }

      this.horizon(var4, var5, var6, var2, var3, var9, var8, var10);
      var4.roundRectRadial(-var5 * 0.15F, -var6 * 0.15F, var5 * 1.3F, var6 * 1.3F, 0.0F, 0, BlendMode.withAlpha(-16777216, 0.4F * var2));
   }

   private void horizon(Canvas var1, float var2, float var3, float var4, float var5, float var6, boolean var7, float var8) {
      float var9 = var2 * 1.25F;
      float var10 = var3 * (0.935F - 0.075F * var5);
      float var11 = var2 * 0.5F - var8 * 14.0F;
      float var12 = var10 + var9;
      float var13 = var7 ? 1.0F : 0.92F + 0.08F * (float)Math.sin(var6 * 0.7F);
      float var14 = var2 * 0.79F - var8 * 22.0F;
      float var15 = var10 - var3 * (0.09F + 0.05F * var5);
      float var16 = var4 * (0.55F + 0.45F * var5) * var13;
      var1.glowCircle(var14, var15, 3.0F, 26.0F, BlendMode.withAlpha(Icons.PINK, 0.35F * var16));
      var1.line(var14 - 14.0F, var15, var14, var15, 1.1F, BlendMode.withAlpha(Theme.tint(-5633), 0.0F), BlendMode.withAlpha(Theme.tint(-5633), 0.55F * var16));
      var1.line(var14, var15, var14 + 14.0F, var15, 1.1F, BlendMode.withAlpha(Theme.tint(-5633), 0.55F * var16), BlendMode.withAlpha(Theme.tint(-5633), 0.0F));
      var1.line(var14, var15 - 10.0F, var14, var15, 1.1F, BlendMode.withAlpha(Theme.tint(-5633), 0.0F), BlendMode.withAlpha(Theme.tint(-5633), 0.45F * var16));
      var1.line(var14, var15, var14, var15 + 10.0F, 1.1F, BlendMode.withAlpha(Theme.tint(-5633), 0.45F * var16), BlendMode.withAlpha(Theme.tint(-5633), 0.0F));
      var1.circle(var14, var15, 1.9F, BlendMode.withAlpha(Theme.tint(-2817), 0.95F * var16));
      float var17 = (0.14F + 0.2F * var5) * var4 * var13;
      var1.glowCircle(var11, var12, var9, var3 * (0.16F + 0.06F * var5), BlendMode.withAlpha(Icons.VIOLET, var17));
      var1.glowCircle(var11, var12, var9, var3 * 0.035F, BlendMode.withAlpha(Icons.MAGENTA, var17 * 0.8F));
      var1.pushAlpha(var4);
      var1.circleRadial(var11, var12, var9, Theme.tint(-16382963), Theme.tint(-15463127));
      float var18 = (float)Math.toDegrees(Math.asin(Math.min(1.0F, var2 * 0.62F / var9)));
      int var19 = BlendMode.withAlpha(BlendMode.lerp(Icons.PURPLE, Icons.PINK, 0.25F), 0.5F + 0.4F * var5);
      var1.arc(var11, var12, var9, 1.6F, -90.0F - var18, var18, Theme.tint(11870196), var19, false);
      var1.arc(var11, var12, var9, 1.6F, -90.0F, var18, var19, Theme.tint(11870196), false);
      var1.popAlpha();
   }
}
