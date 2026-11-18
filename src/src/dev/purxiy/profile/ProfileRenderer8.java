// Sourced By DevFFNightmare
package dev.purxiy.profile;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.Locale;

final class ProfileRenderer8 {
   static final float HEIGHT = 124.0F;
   private static final float GAP = 14.0F;
   private static final ProfileKind[] METRICS = ProfileKind.values();
   private static final int DAYS = 14;
   private final Animator[] hover = new Animator[METRICS.length];
   private final Object[] keys = new Object[METRICS.length];
   private final float[] xs = new float[14];
   private final float[] ys = new float[14];
   private final float[] curveX = new float[53];
   private final float[] curveY = new float[53];

   ProfileRenderer8() {
      for (int var1 = 0; var1 < METRICS.length; var1++) {
         this.hover[var1] = Animator.smooth(0.0F);
         this.keys[var1] = new Object();
      }
   }

   void render(DrawContext var1, ProfileSupport6 var2, float var3, float var4, float var5, float var6, boolean var7) {
      Canvas var8 = var1.canvas;
      float var9 = (var5 - 14.0F * (METRICS.length - 1)) / METRICS.length;

      for (int var10 = 0; var10 < METRICS.length; var10++) {
         float var11 = Easing.EMPHASIZED.apply(ProfileCurve.clamp01((var6 - 0.08F - var10 * 0.06F) / 0.55F));
         if (!(var11 <= 0.005F)) {
            float var12 = var3 + var10 * (var9 + 14.0F);
            boolean var13 = var7 && var1.hover(this.keys[var10], var12, var4, var9, 124.0F, DrawContext.TypeE.ARROW);
            float var14 = this.hover[var10].target(var13 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
            var8.push();
            var8.translate(0.0F, (1.0F - var11) * 14.0F - var14 * 2.0F);
            var8.pushAlpha(var11);
            this.tile(var8, var2, METRICS[var10], var12, var4, var9, var14, var6 - 0.1F - var10 * 0.06F);
            var8.popAlpha();
            var8.pop();
         }
      }
   }

   private void tile(Canvas var1, ProfileSupport6 var2, ProfileKind var3, float var4, float var5, float var6, float var7, float var8) {
      ProfileRenderer9.card(var1, var4, var5, var6, 124.0F, var7);
      float var9 = 16.0F;
      float var10 = 26.0F;
      var1.roundRect(var4 + var9, var5 + var9, var10, var10, 8.0F, BlendMode.lerp(318767103, 486539263, var7));
      var1.roundRectOutline(var4 + var9, var5 + var9, var10, var10, 8.0F, 1.0F, 352321535);
      var1.icon(var3.icon, var4 + var9 + var10 / 2.0F, var5 + var9 + var10 / 2.0F, 13.5F, Theme.tint(-4611329));
      ProfileRenderer9.label(var1, var3.label.toUpperCase(Locale.ROOT), var4 + var9 + var10 + 10.0F, var5 + var9 + var10 / 2.0F, Icons.TEXT_3, FontStyle.LEFT);
      this.trend(var1, var2, var3, var4 + var6 - var9, var5 + var9 + var10 / 2.0F);
      double var11 = ProfileCurve.countUp(var3.of(var2.lifetime), (var8 - 0.05F) / 0.9F);
      var1.text(Fonts.BOLD, var3.format(var11), var4 + var9, var5 + 54.0F, 23.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.TOP, -0.01F);
      String var13 = var3.format(var3.of(var2.week));
      String var14 = var13 + " this week";
      if (var3 == ProfileKind.COMBAT) {
         int var15 = var2.lifetime.deaths;
         var13 = var15 == 0 ? ProfileSupport2.count(var2.lifetime.kills) : String.format(Locale.ROOT, "%.2f", (double)var2.lifetime.kills / var15);
         var14 = "K/D " + var13 + "  ·  " + ProfileSupport2.count(var2.week.allKills()) + " this week";
      }

      var1.text(Fonts.REGULAR, var14, var4 + var9, var5 + 84.0F, 11.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.TOP);
      var1.pushClip(var4, var5, var6, 124.0F, 18.0F);
      this.sparkline(var1, var2, var3, var4, var5 + 124.0F - 24.0F, var6, 20.0F, var8);
      var1.popClip();
   }

   private void trend(Canvas var1, ProfileSupport6 var2, ProfileKind var3, float var4, float var5) {
      double var6 = var2.trend(var3);
      if (!Double.isNaN(var6) && !(Math.abs(var6) < 0.005)) {
         boolean var8 = var6 > 0.0;
         String var9 = ProfileSupport2.percent(Math.max(-9.99, Math.min(9.99, var6)));
         float var10 = var1.textWidth(Fonts.SEMIBOLD, var9, 10.0F);
         float var11 = var10 + 26.0F;
         float var12 = 18.0F;
         int var13 = var8 ? Theme.tint(-1012489) : Icons.TEXT_3;
         var1.roundRect(var4 - var11, var5 - var12 / 2.0F, var11, var12, var12 / 2.0F, BlendMode.withAlpha(var8 ? Icons.MAGENTA : -1, var8 ? 0.14F : 0.06F));
         var1.icon(var8 ? "trending-up" : "trending-down", var4 - var11 + 11.0F, var5, 11.0F, var13);
         var1.text(Fonts.SEMIBOLD, var9, var4 - 7.0F, var5, 10.0F, var13, FontStyle.RIGHT, FontWeight.MIDDLE);
      }
   }

   private void sparkline(Canvas var1, ProfileSupport6 var2, ProfileKind var3, float var4, float var5, float var6, float var7, float var8) {
      double[] var9 = ProfileSupport3.series(var2.data, var3, var2.today, 14);
      double var10 = 0.0;

      for (double var15 : var9) {
         var10 = Math.max(var10, var15);
      }

      int var18 = 0;

      for (double var16 : var9) {
         var18 += var16 > 0.0 ? 1 : 0;
      }

      if (var18 < 2) {
         var1.rect(var4, var5 + var7 - 1.0F, var6, 1.0F, 352321535);
         if (var9[13] > 0.0) {
            float var20 = var4 + var6 - 20.0F;
            float var23 = var5 + var7 - 0.5F;
            var1.glowCircle(var20, var23, 2.0F, 8.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.5F));
            var1.circle(var20, var23, 2.2F, Theme.tint(-1012489));
         }
      } else {
         float var21 = Easing.STANDARD.apply(ProfileCurve.clamp01((var8 - 0.2F) / 0.8F));

         for (int var24 = 0; var24 < 14; var24++) {
            this.xs[var24] = var4 + var6 * var24 / 13.0F;
            this.ys[var24] = var5 + var7 - (float)(var9[var24] / var10) * var7 * var21;
         }

         int var25 = ProfileCurve.smooth(this.xs, this.ys, 14, 4, this.curveX, this.curveY);
         ProfileCurve.area(
            var1, this.curveX, this.curveY, var25, var5, var5 + var7 + 4.0F, BlendMode.withAlpha(Icons.PURPLE, 0.22F), BlendMode.withAlpha(Icons.PURPLE, 0.0F)
         );
         ProfileCurve.ribbon(
            var1,
            this.curveX,
            this.curveY,
            var25,
            1.4F,
            0.9F,
            BlendMode.withAlpha(Theme.tint(-4611329), 0.8F),
            BlendMode.withAlpha(Theme.tint(-1012489), 0.95F),
            var4,
            var4 + var6
         );
      }
   }
}
