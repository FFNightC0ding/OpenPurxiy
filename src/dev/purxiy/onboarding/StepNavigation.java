package dev.purxiy.onboarding;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;

final class StepNavigation {
   private static final float SIZE = 13.0F;
   private final Animator hover = Animator.smooth(0.0F);
   private final Animator enabled = Animator.smooth(1.0F);
   private float x;
   private float y;
   private float w;
   private float h;
   private boolean active;
   private long drawn = -1L;

   static float width(Canvas var0, String var1,  String var2,  String var3, float var4) {
      if (var1.isEmpty()) {
         return var4;
      } else {
         float var5 = var4 * 0.5F;
         return var5 * 2.0F + var0.textWidth(Fonts.SEMIBOLD, var1, 13.0F) + (var2 != null ? 22 : 0) + (var3 != null ? 20 : 0);
      }
   }

   float render(
      DrawContext var1,
      float var2,
      float var3,
      float var4,
      String var5,
       String var6,
       String var7,
      StepNavigation.AccentStyle var8,
      boolean var9,
      boolean var10
   ) {
      Canvas var11 = var1.canvas;
      this.w = width(var11, var5, var6, var7, var4);
      this.h = var4;
      this.x = var2;
      this.y = var3 - var4 / 2.0F;
      this.active = var10 && var9;
      this.drawn = RenderPipelines.get().frame();
      boolean var12 = this.active && var1.hover(this, this.x, this.y, this.w, this.h, DrawContext.Cursor.HAND);
      float var13 = this.hover.target(var12 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var14 = this.enabled.target(var9 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var15 = var4 / 2.0F;
      var11.pushAlpha(0.4F + 0.6F * var14);
      switch (var8) {
         case PRIMARY:
            var11.glow(this.x, this.y, this.w, this.h, var15, 18.0F + 6.0F * var13, BlendMode.withAlpha(Icons.VIOLET, (0.26F + 0.22F * var13) * var14));
            var11.roundRectGradient(
               this.x,
               this.y,
               this.w,
               this.h,
               var15,
               0.0F,
               BlendMode.lerp(Icons.INDIGO, Theme.tint(-8770571), var13),
               BlendMode.lerp(Icons.PURPLE, Icons.MAGENTA, var13 * 0.7F)
            );
            var11.roundRectCorners(this.x, this.y, this.w, this.h, var15, 872415231, 654311423, 16777215, 16777215);
            var11.roundRectOutline(this.x, this.y, this.w, this.h, var15, 1.0F, 956301311);
            break;
         case ACCENT:
            if (var13 > 0.01F) {
               var11.glow(this.x, this.y, this.w, this.h, var15, 16.0F, BlendMode.withAlpha(Icons.VIOLET, 0.22F * var13));
            }

            var11.roundRectGradient(
               this.x,
               this.y,
               this.w,
               this.h,
               var15,
               0.0F,
               BlendMode.lerp(Theme.tint(707468114), Theme.tint(1077157994), var13),
               BlendMode.lerp(Theme.tint(572133424), Theme.tint(874911568), var13)
            );
            var11.roundRectOutlineGradient(
               this.x,
               this.y,
               this.w,
               this.h,
               var15,
               1.0F,
               0.0F,
               BlendMode.withAlpha(Icons.PURPLE, 0.55F + 0.35F * var13),
               BlendMode.withAlpha(Icons.MAGENTA, 0.35F + 0.45F * var13)
            );
            break;
         case GLASS:
            var11.roundRect(this.x, this.y, this.w, this.h, var15, BlendMode.lerp(285212671, 486539263, var13));
            var11.roundRectOutline(this.x, this.y, this.w, this.h, var15, 1.0F, BlendMode.lerp(486539263, 922746879, var13));
            break;
         case GHOST:
            if (var13 > 0.01F) {
               var11.roundRect(this.x, this.y, this.w, this.h, var15, BlendMode.withAlpha(-1, 0.07F * var13));
            }
      }

      int var16 = var8 == StepNavigation.AccentStyle.GHOST ? BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var13) : Icons.TEXT;
      float var17 = this.x + var4 * 0.5F;
      if (var5.isEmpty() && var6 != null) {
         var11.icon(var6, this.x + this.w / 2.0F, var3, 15.0F, var16);
         var11.popAlpha();
         return this.w;
      } else {
         if (var6 != null) {
            var11.icon(var6, var17 + 7.0F, var3, 14.0F, var16);
            var17 += 22.0F;
         }

         var11.text(Fonts.SEMIBOLD, var5, var17, var3, 13.0F, var16, FontStyle.LEFT, FontWeight.MIDDLE);
         if (var7 != null) {
            float var18 = !var7.startsWith("arrow-left") && !var7.startsWith("chevron-left") ? 2.5F * var13 : -2.5F * var13;
            var11.icon(var7, this.x + this.w - var4 * 0.5F - 7.0F + var18, var3, 14.0F, var16);
         }

         var11.popAlpha();
         return this.w;
      }
   }

   float hover() {
      return this.hover.value();
   }

   float width() {
      return this.w;
   }

   boolean hit(float var1, float var2) {
      return this.active
         && this.drawn == RenderPipelines.get().frame()
         && var1 >= this.x
         && var1 <= this.x + this.w
         && var2 >= this.y
         && var2 <= this.y + this.h;
   }

   static enum AccentStyle {
      PRIMARY,
      ACCENT,
      GLASS,
      GHOST;

      private static StepNavigation.AccentStyle[] $values() {
         return new StepNavigation.AccentStyle[]{PRIMARY, ACCENT, GLASS, GHOST};
      }
   }
}
