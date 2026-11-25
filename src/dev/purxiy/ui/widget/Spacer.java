package dev.purxiy.ui.widget;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;

public final class Spacer {
   private final Animator value;
   private final Animator hover = Animator.smooth(0.0F);
   private final Animator press = Animator.snappy(0.0F);

   public Spacer(boolean var1) {
      this.value = Animator.snappy(var1 ? 1.0F : 0.0F);
   }

   public void snap(boolean var1) {
      this.value.snap(var1 ? 1.0F : 0.0F);
   }

   public void render(DrawContext var1, float var2, float var3, float var4, float var5, boolean var6, boolean var7, boolean var8) {
      float var9 = this.value.target(var6 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var10 = this.hover.target(var7 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var11 = this.press.target(var8 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      Canvas var12 = var1.canvas;
      float var13 = var5 / 2.0F;
      var12.roundRect(var2, var3, var4, var5, var13, BlendMode.lerp(486539263, 738197503, var10));
      float var14 = Math.max(0.0F, Math.min(1.0F, var9));
      if (var14 > 0.005F) {
         var12.glow(var2, var3, var4, var5, var13, 14.0F, BlendMode.withAlpha(Icons.VIOLET, 0.34F * var14));
         var12.pushAlpha(var14);
         var12.roundRectGradient(var2, var3, var4, var5, var13, 0.0F, Icons.INDIGO, Icons.MAGENTA);
         var12.popAlpha();
      }

      float var15 = Math.max(2.5F, var5 * 0.14F);
      float var16 = var5 - var15 * 2.0F;
      float var17 = var11 * var16 * 0.3F;
      float var18 = var2 + var15 + (var4 - var15 * 2.0F - var16 - var17) * var9;
      var12.shadow(var18, var3 + var15, var16 + var17, var16, var16 / 2.0F, 6.0F, 0.0F, 0.0F, 1.5F, 1711276032);
      var12.roundRect(var18, var3 + var15, var16 + var17, var16, var16 / 2.0F, -1);
   }
}
