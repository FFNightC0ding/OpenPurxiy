package dev.purxiy.profile;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.Theme;

final class ProfileMetrics {
   static final float RADIUS = 18.0F;
   static final float PAD = 20.0F;
   static final int DANGER = -29785;

   private ProfileMetrics() {
   }

   static void card(Canvas var0, float var1, float var2, float var3, float var4, float var5) {
      KeyLabel.surface(var0, var1, var2, var3, var4, 18.0F, 0.22F + var5 * 0.3F, var5 * 0.22F);
   }

   static void title(Canvas var0, String var1, String var2, float var3, float var4) {
      title(var0, var1, var2, var3, var4, Float.MAX_VALUE);
   }

   static void title(Canvas var0, String var1, String var2, float var3, float var4, float var5) {
      var0.text(Fonts.SEMIBOLD, var1, var3 + 20.0F, var4 + 18.0F, 14.5F, Icons.TEXT);
      var0.text(Fonts.REGULAR, FontMetrics.ellipsize(Fonts.REGULAR, var2, 11.5F, var5, 0.0F), var3 + 20.0F, var4 + 39.0F, 11.5F, Icons.TEXT_2);
   }

   static void label(Canvas var0, String var1, float var2, float var3, int var4, FontStyle var5) {
      var0.text(Fonts.SEMIBOLD, var1, var2, var3, 9.5F, var4, var5, FontWeight.MIDDLE, 0.12F);
   }

   static void tooltip(Canvas var0, float var1, float var2, String var3, String var4, float var5) {
      if (!(var5 <= 0.01F)) {
         float var6 = Math.max(var0.textWidth(Fonts.MEDIUM, var3, 10.5F), var0.textWidth(Fonts.SEMIBOLD, var4, 13.0F));
         float var7 = var6 + 24.0F;
         float var8 = 46.0F;
         float var9 = Math.max(8.0F, Math.min(var0.width() - var7 - 8.0F, var1 - var7 / 2.0F));
         float var10 = var2 - var8 - 12.0F + (1.0F - var5) * 4.0F;
         var0.pushAlpha(var5);
         var0.shadow(var9, var10, var7, var8, 10.0F, 18.0F, 0.0F, 0.0F, 5.0F, 1996488704);
         var0.roundRect(var9, var10, var7, var8, 10.0F, Theme.tint(-233434583));
         var0.roundRectOutline(var9, var10, var7, var8, 10.0F, 1.0F, 620756991);
         var0.text(Fonts.MEDIUM, var3, var9 + 12.0F, var10 + 11.0F, 10.5F, Icons.TEXT_2);
         var0.text(Fonts.SEMIBOLD, var4, var9 + 12.0F, var10 + 25.0F, 13.0F, Icons.TEXT);
         var0.popAlpha();
      }
   }

   static void tooltipBeside(Canvas var0, float var1, float var2, String var3, String var4, float var5, float var6, float var7, float var8) {
      if (!(var5 <= 0.01F)) {
         float var9 = Math.max(var0.textWidth(Fonts.MEDIUM, var3, 10.5F), var0.textWidth(Fonts.SEMIBOLD, var4, 13.0F));
         float var10 = var9 + 24.0F;
         float var11 = 46.0F;
         boolean var12 = var1 + 16.0F + var10 > var6;
         float var13 = var12 ? var1 - 16.0F - var10 : var1 + 16.0F;
         float var14 = Math.max(var7, Math.min(var8 - var11, var2 - var11 / 2.0F));
         var0.pushAlpha(var5);
         var0.shadow(var13, var14, var10, var11, 10.0F, 18.0F, 0.0F, 0.0F, 5.0F, 1996488704);
         var0.roundRect(var13, var14, var10, var11, 10.0F, Theme.tint(-233434583));
         var0.roundRectOutline(var13, var14, var10, var11, 10.0F, 1.0F, 620756991);
         var0.text(Fonts.MEDIUM, var3, var13 + 12.0F, var14 + 11.0F, 10.5F, Icons.TEXT_2);
         var0.text(Fonts.SEMIBOLD, var4, var13 + 12.0F, var14 + 25.0F, 13.0F, Icons.TEXT);
         var0.popAlpha();
      }
   }

   static final class MetricBar {
      final Animator hover = Animator.smooth(0.0F);
      private final Animator bump = Animator.snappy(0.0F);
      private float x;
      private float y;
      private float w;
      private float h;
      private boolean shown;

      static float width(Canvas var0,  String var1, String var2, float var3) {
         float var4 = textSize(var3);
         float var5 = var2.isEmpty() ? 0.0F : var0.textWidth(Fonts.MEDIUM, var2, var4);
         float var6 = var1 == null ? 0.0F : (var2.isEmpty() ? 0.0F : var4 + 6.0F);
         return var2.isEmpty() ? var3 : Math.round(var5 + var6 + var3 * 0.9F);
      }

      private static float textSize(float var0) {
         return var0 >= 34.0F ? 12.5F : (var0 >= 30.0F ? 12.0F : 11.5F);
      }

      float render(DrawContext var1, float var2, float var3, float var4,  String var5, String var6, ProfileMetrics.MetricTone var7, boolean var8) {
         return this.render(var1, var2, var3, width(var1.canvas, var5, var6, var4), var4, var5, var6, var7, var8);
      }

      float render(
         DrawContext var1, float var2, float var3, float var4, float var5,  String var6, String var7, ProfileMetrics.MetricTone var8, boolean var9
      ) {
         label65: {
            Canvas var10 = var1.canvas;
            this.x = var2;
            this.y = var3 - var5 / 2.0F;
            this.w = var4;
            this.h = var5;
            this.shown = var9;
            boolean var11 = var9 && var1.hover(this, var2, this.y, var4, var5, DrawContext.Cursor.HAND);
            float var12 = this.hover.target(var11 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
            float var13 = this.bump.update(MotionStyle.TOGGLE.dt(var1.dt));
            float var14 = var5 / 2.0F;
            var10.push();
            var10.scaleAround(1.0F - 0.06F * var13, var2 + var4 / 2.0F, var3);
            int var15 = 0;

            var15 = switch (var8) {
               case PRIMARY -> {
                  if (var12 > 0.01F) {
                     var10.glow(var2, this.y, var4, var5, var14, 16.0F, BlendMode.withAlpha(Icons.VIOLET, 0.36F * var12));
                  }

                  var10.roundRectGradient(
                     var2,
                     this.y,
                     var4,
                     var5,
                     var14,
                     0.0F,
                     BlendMode.withAlpha(Icons.INDIGO, 0.78F + 0.22F * var12),
                     BlendMode.withAlpha(Icons.PURPLE, 0.78F + 0.22F * var12)
                  );
                  var10.roundRectCorners(var2, this.y, var4, var5, var14, 654311423, 654311423, 16777215, 16777215);
                  var10.roundRectOutline(var2, this.y, var4, var5, var14, 1.0F, 872415231);
                  if ((var8 == ProfileMetrics.MetricTone.TEXT || var8 == ProfileMetrics.MetricTone.DANGER) && var12 > 0.01F) {
                     var10.roundRect(var2, this.y, var4, var5, var14, BlendMode.withAlpha(-1, 0.06F * var12));
                  }

                  float var16 = textSize(var5);
                  if (var7.isEmpty()) {
                     if (var6 != null) {
                        var10.icon(var6, var2 + var4 / 2.0F, var3, var5 * 0.44F, var15);
                     }
                  } else {
                     float var17 = var10.textWidth(Fonts.MEDIUM, var7, var16);
                     float var18 = var6 == null ? 0.0F : var16 + 6.0F;
                     float var19 = var2 + var4 / 2.0F - (var17 + var18) / 2.0F;
                     if (var6 != null) {
                        var10.icon(var6, var19 + var16 / 2.0F, var3, var16 + 1.0F, var15);
                     }

                     var10.text(Fonts.MEDIUM, var7, var19 + var18, var3, var16, var15, FontStyle.LEFT, FontWeight.MIDDLE);
                  }

                  var10.pop();
                  yield (int)var4;
               }
               case QUIET -> {
                  var10.roundRect(var2, this.y, var4, var5, var14, BlendMode.lerp(285212671, 520093695, var12));
                  var10.roundRectOutline(var2, this.y, var4, var5, var14, 1.0F, BlendMode.lerp(419430399, 922746879, var12));
                  yield BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, Math.max(var12, 0.35F));
               }
               default -> BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var12);
               case DANGER -> BlendMode.lerp(BlendMode.withAlpha(-29785, 0.8F), -29785, var12);
            };
         }
         return 0.0F;
      }

      boolean hit(float var1, float var2) {
         return this.shown && var1 >= this.x && var1 <= this.x + this.w && var2 >= this.y && var2 <= this.y + this.h;
      }

      void hide() {
         this.shown = false;
      }

      void press() {
         this.bump.snap(1.0F).target(0.0F);
      }
   }

   static final class MetricOption {
      static final float HEIGHT = 28.0F;
      private static final float PAD = 3.0F;
      private static final float TEXT = 11.5F;
      private final String[] labels;
      private final String[] icons;
      private final Animator highlightX = Animator.snappy(0.0F);
      private final Animator highlightW = Animator.snappy(0.0F);
      private final Animator[] hover;
      private final float[] optionX;
      private final float[] optionW;
      private int selected;
      private float x;
      private float y;
      private boolean first = true;

      MetricOption(String[] var1, String[] var2) {
         this.labels = var1;
         this.icons = var2;
         int var3 = var1.length;
         this.hover = new Animator[var3];
         this.optionX = new float[var3];
         this.optionW = new float[var3];

         for (int var4 = 0; var4 < var3; var4++) {
            this.hover[var4] = Animator.smooth(0.0F);
         }
      }

      int selected() {
         return this.selected;
      }

      void select(int var1) {
         this.selected = Math.max(0, Math.min(this.labels.length - 1, var1));
      }

      float width() {
         float var1 = 6.0F;

         for (int var2 = 0; var2 < this.labels.length; var2++) {
            var1 += this.optionWidth(var2);
         }

         return var1;
      }

      private float optionWidth(int var1) {
         float var2 = this.icons != null && this.icons[var1] != null ? 18.0F : 0.0F;
         return Math.max(38.0F, Fonts.MEDIUM.width(this.labels[var1], 11.5F) + 22.0F + var2);
      }

      void render(DrawContext var1, float var2, float var3, boolean var4) {
         Canvas var5 = var1.canvas;
         float var6 = this.width();
         this.x = var2 - var6;
         this.y = var3 - 14.0F;
         var5.roundRect(this.x, this.y, var6, 28.0F, 14.0F, 285212671);
         var5.roundRectOutline(this.x, this.y, var6, 28.0F, 14.0F, 1.0F, 318767103);
         float var7 = this.x + 3.0F;

         for (int var8 = 0; var8 < this.labels.length; var8++) {
            this.optionX[var8] = var7;
            this.optionW[var8] = this.optionWidth(var8);
            var7 += this.optionW[var8];
         }

         if (this.first) {
            this.highlightX.snap(this.optionX[this.selected] - this.x);
            this.highlightW.snap(this.optionW[this.selected]);
            this.first = false;
         }

         float var19 = this.highlightX.target(this.optionX[this.selected] - this.x).update(MotionStyle.TOGGLE.dt(var1.dt));
         float var9 = this.highlightW.target(this.optionW[this.selected]).update(MotionStyle.TOGGLE.dt(var1.dt));
         float var10 = 22.0F;
         var5.glow(this.x + var19, this.y + 3.0F, var9, var10, var10 / 2.0F, 10.0F, BlendMode.withAlpha(Icons.VIOLET, 0.26F));
         var5.roundRectGradient(this.x + var19, this.y + 3.0F, var9, var10, var10 / 2.0F, 0.0F, Icons.INDIGO, Icons.PURPLE);
         var5.roundRectCorners(this.x + var19, this.y + 3.0F, var9, var10, var10 / 2.0F, 721420287, 721420287, 16777215, 16777215);

         for (int var11 = 0; var11 < this.labels.length; var11++) {
            boolean var12 = var4 && var1.hover(this.hover[var11], this.optionX[var11], this.y, this.optionW[var11], 28.0F, DrawContext.Cursor.HAND);
            float var13 = this.hover[var11].target(var12 && var11 != this.selected ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
            int var14 = var11 == this.selected ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var13);
            float var15 = this.optionX[var11] + this.optionW[var11] / 2.0F;
            String var16 = this.icons != null ? this.icons[var11] : null;
            if (var16 != null) {
               float var17 = var5.textWidth(Fonts.MEDIUM, this.labels[var11], 11.5F);
               float var18 = var15 - (var17 + 18.0F) / 2.0F;
               var5.icon(var16, var18 + 6.5F, var3, 12.5F, var14);
               var5.text(Fonts.MEDIUM, this.labels[var11], var18 + 18.0F, var3, 11.5F, var14, FontStyle.LEFT, FontWeight.MIDDLE);
            } else {
               var5.text(Fonts.MEDIUM, this.labels[var11], var15, var3, 11.5F, var14, FontStyle.CENTER, FontWeight.MIDDLE);
            }
         }
      }

      boolean mouseDown(float var1, float var2) {
         if (!(var2 < this.y) && !(var2 > this.y + 28.0F)) {
            for (int var3 = 0; var3 < this.labels.length; var3++) {
               if (var1 >= this.optionX[var3] && var1 < this.optionX[var3] + this.optionW[var3]) {
                  if (var3 != this.selected) {
                     this.selected = var3;
                     SoundRequest.play(SoundEvent.TAB);
                  }

                  return true;
               }
            }

            return false;
         } else {
            return false;
         }
      }
   }

   static enum MetricTone {
      PRIMARY,
      QUIET,
      TEXT,
      DANGER;

      private static ProfileMetrics.MetricTone[] $values() {
         return new ProfileMetrics.MetricTone[]{PRIMARY, QUIET, TEXT, DANGER};
      }
   }
}
