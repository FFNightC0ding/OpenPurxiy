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
import java.time.LocalDate;

final class ProfileChart {
   static final float HEIGHT = 292.0F;
   private static final int[] RANGES = new int[]{7, 30, 90};
   private static final int SAMPLES = 6;
   private final ProfileMetrics.MetricOption metrics;
   private final ProfileMetrics.MetricOption ranges = new ProfileMetrics.MetricOption(new String[]{"7D", "30D", "90D"}, (String[])null);
   private final double[] shown = new double[90];
   private final float[] xs = new float[90];
   private final float[] ys = new float[90];
   private final float[] curveX = new float[535];
   private final float[] curveY = new float[535];
   private final Animator guide = Animator.smooth(0.0F);
   private final Animator guideAlpha = Animator.smooth(0.0F);
   private final Object plotKey = new Object();
   private double axisMax = -1.0;
   private int lastRange = -1;
   private float drawnAt = -100.0F;
   private int hoverIndex = -1;
   private float x;
   private float y;
   private float w;

   ProfileChart() {
      ProfileKind[] var1 = ProfileKind.values();
      String[] var2 = new String[var1.length];
      String[] var3 = new String[var1.length];

      for (int var4 = 0; var4 < var1.length; var4++) {
         var2[var4] = var1[var4].label;
         var3[var4] = var1[var4].icon;
      }

      this.metrics = new ProfileMetrics.MetricOption(var2, var3);
   }

   void enter(float var1) {
      this.drawnAt = var1 + 0.25F;
      this.lastRange = -1;
   }

   void render(DrawContext var1, ProfileSummary var2, float var3, float var4, float var5, float var6, boolean var7) {
      this.x = var3;
      this.y = var4;
      this.w = var5;
      Canvas var8 = var1.canvas;
      ProfileKind var9 = ProfileKind.values()[this.metrics.selected()];
      int var10 = RANGES[this.ranges.selected()];
      double[] var11 = ProfileTotals.series(var2.data, var9, var2.today, var10);
      double var12 = 0.0;
      double var14 = 0.0;

      for (double var19 : var11) {
         var12 += var19;
         var14 = Math.max(var14, var19);
      }

      ProfileMetrics.card(var8, var3, var4, var5, 292.0F, 0.0F);
      float var35 = var3 + var5 - 20.0F - this.ranges.width() - 10.0F - this.metrics.width();
      ProfileMetrics.title(
         var8,
         "Activity",
         var9.format(var12) + " in " + var10 + " days  ·  avg " + var9.format(var12 / var10) + " a day",
         var3,
         var4,
         var35 - var3 - 20.0F - 14.0F
      );
      this.ranges.render(var1, var3 + var5 - 20.0F, var4 + 30.0F, var7);
      this.metrics.render(var1, var3 + var5 - 20.0F - this.ranges.width() - 10.0F, var4 + 30.0F, var7);
      if (var10 != this.lastRange) {
         if (this.lastRange != -1) {
            this.drawnAt = var6;
         }

         System.arraycopy(var11, 0, this.shown, 0, var10);
         this.axisMax = -1.0;
      }

      this.lastRange = var10;
      float var36 = 1.0F - (float)Math.exp(-Math.min(var1.dt, 0.1F) * 9.0F * MotionStyle.MENU.speed());

      for (int var37 = 0; var37 < var10; var37++) {
         double[] var39 = this.shown;
         var39[var37] += (var11[var37] - this.shown[var37]) * var36;
      }

      double var38 = var9.step(var14, 4);
      double var20 = Math.max(var38, Math.ceil(Math.max(var14, var9.minimum()) / var38) * var38);
      this.axisMax = this.axisMax < 0.0 ? var20 : this.axisMax + (var20 - this.axisMax) * var36;
      float var22 = var3 + 58.0F;
      float var23 = var3 + var5 - 24.0F;
      float var24 = var4 + 80.0F;
      float var25 = var4 + 292.0F - 40.0F;
      float var26 = var25 - var24;
      double var27 = var12 / var10;
      float var29 = var14 > 0.0 ? var25 - (float)(var27 / this.axisMax) * var26 : Float.NaN;
      this.grid(var8, var9, var38, var22, var23, var24, var25, var29);
      this.labels(var8, var2.today, var10, var22, var23, var25);

      for (int var30 = 0; var30 < var10; var30++) {
         this.xs[var30] = var10 == 1 ? var22 : var22 + (var23 - var22) * var30 / (var10 - 1);
         this.ys[var30] = var25 - (float)(this.shown[var30] / this.axisMax) * var26;
      }

      int var40 = ProfileCurve.smooth(this.xs, this.ys, var10, var10 > 40 ? 3 : 6, this.curveX, this.curveY);
      float var31 = Easing.EMPHASIZED.apply(ProfileCurve.clamp01((var6 - this.drawnAt) / 1.1F));
      if (!(var31 <= 0.001F)) {
         float var32 = var22 - 6.0F + (var23 - var22 + 30.0F) * var31;
         var8.pushClip(var3, var4 + 54.0F, var32 - var3, 238.0F, 0.0F);
         if (var14 <= 0.0) {
            this.empty(var8, var22, var23, var24, var25);
         } else {
            ProfileCurve.area(
               var8, this.curveX, this.curveY, var40, var24, var25, BlendMode.withAlpha(Icons.PURPLE, 0.3F), BlendMode.withAlpha(Icons.PURPLE, 0.0F)
            );
            ProfileCurve.ribbon(
               var8,
               this.curveX,
               this.curveY,
               var40,
               2.0F,
               7.0F,
               BlendMode.withAlpha(Theme.tint(-4907020), 0.16F),
               BlendMode.withAlpha(Icons.MAGENTA, 0.2F),
               var22,
               var23
            );
            ProfileCurve.ribbon(var8, this.curveX, this.curveY, var40, 2.2F, 1.1F, Theme.tint(-4611329), Theme.tint(-1012489), var22, var23);
            this.average(var8, var29, var22, var23);
         }

         var8.popClip();
         if (var14 > 0.0) {
            this.today(var8, var6, var10, var31);
            this.hover(var1, var8, var2, var9, var10, var22, var23, var24, var25, var31, var7);
         }

         if (var31 < 0.999F && var14 > 0.0) {
            float var33 = Math.min(var32 - 12.0F, var23);
            float var34 = this.curveAt(var33, var40);
            var8.glowCircle(var33, var34, 3.0F, 14.0F, BlendMode.withAlpha(Icons.PINK, 0.6F * (1.0F - var31)));
            var8.circle(var33, var34, 2.4F, BlendMode.withAlpha(-1, 1.0F - var31));
         }
      }
   }

   private void grid(Canvas var1, ProfileKind var2, double var3, float var5, float var6, float var7, float var8, float var9) {
      int var10 = 0;

      while (true) {
         double var11 = var10 * var3;
         if (var11 > this.axisMax + var3 * 0.01) {
            return;
         }

         float var13 = var8 - (float)(var11 / this.axisMax) * (var8 - var7);
         var1.rect(var5, var13 - 0.5F, var6 - var5, 1.0F, var10 == 0 ? 452984831 : 201326591);
         if (Float.isNaN(var9) || Math.abs(var13 - var9) > 11.0F) {
            var1.text(Fonts.MEDIUM, var2.axis(var11), var5 - 12.0F, var13, 10.0F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.MIDDLE);
         }

         var10++;
      }
   }

   private void labels(Canvas var1, LocalDate var2, int var3, float var4, float var5, float var6) {
      int var7 = var3 <= 7 ? 1 : (var3 <= 30 ? 5 : 15);

      for (int var8 = var3 - 1; var8 >= 0; var8 -= var7) {
         LocalDate var9 = var2.minusDays(var3 - 1L - var8);
         float var10 = var3 == 1 ? var4 : var4 + (var5 - var4) * var8 / (var3 - 1);
         String var11 = var8 == var3 - 1 ? "Today" : (var3 <= 7 ? ProfileFormatters.weekday(var9) : ProfileFormatters.date(var9));
         var1.text(Fonts.MEDIUM, var11, var10, var6 + 18.0F, 10.0F, var8 == var3 - 1 ? Icons.TEXT_2 : Icons.TEXT_3, FontStyle.CENTER, FontWeight.MIDDLE);
      }
   }

   private void average(Canvas var1, float var2, float var3, float var4) {
      if (!Float.isNaN(var2)) {
         for (float var5 = var3; var5 < var4; var5 += 7.0F) {
            var1.rect(var5, var2 - 0.5F, 3.5F, 1.0F, 822083583);
         }

         var1.text(Fonts.SEMIBOLD, "avg", var3 - 12.0F, var2, 10.0F, Theme.tint(-1012489), FontStyle.RIGHT, FontWeight.MIDDLE);
      }
   }

   private void today(Canvas var1, float var2, int var3, float var4) {
      if (!(var4 < 0.98F) && !(this.shown[var3 - 1] <= 0.0)) {
         float var5 = this.xs[var3 - 1];
         float var6 = this.ys[var3 - 1];
         float var7 = MotionStyle.reduced() ? 0.5F : var2 * 0.8F % 1.0F;
         var1.ring(var5, var6, 4.0F + var7 * 10.0F, 1.5F, BlendMode.withAlpha(Icons.PINK, 0.55F * (1.0F - var7)));
         var1.glowCircle(var5, var6, 3.0F, 10.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.5F));
         var1.circle(var5, var6, 3.6F, -1);
         var1.circle(var5, var6, 2.1F, Theme.tint(-1012489));
      }
   }

   private void hover(
      DrawContext var1,
      Canvas var2,
      ProfileSummary var3,
      ProfileKind var4,
      int var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      boolean var11
   ) {
      boolean var12 = var11
         && var10 > 0.95F
         && var1.hover(this.plotKey, var6 - 16.0F, var8 - 12.0F, var7 - var6 + 32.0F, var9 - var8 + 34.0F, DrawContext.Cursor.ARROW);
      if (var12) {
         float var13 = (var1.mouseX - var6) / (var7 - var6);
         this.hoverIndex = Math.max(0, Math.min(var5 - 1, Math.round(var13 * (var5 - 1))));
      }

      float var22 = this.guideAlpha.target(var12 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      if (this.hoverIndex >= 0 && this.hoverIndex < var5 && !(var22 <= 0.01F)) {
         float var14 = this.guide.target(this.hoverIndex).update(MotionStyle.HOVER.dt(var1.dt) * 1.4F);
         float var15 = var5 == 1 ? var6 : var6 + (var7 - var6) * var14 / (var5 - 1);
         int var16 = this.hoverIndex;
         float var17 = this.xs[var16];
         float var18 = this.ys[var16];
         var2.pushAlpha(var22);
         var2.rect(var15 - 0.5F, var8 - 6.0F, 1.0F, var9 - var8 + 6.0F, 654311423);
         var2.glowCircle(var17, var18, 4.0F, 14.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.55F));
         var2.circle(var17, var18, 5.0F, -1);
         var2.circle(var17, var18, 3.0F, Theme.tint(-1012489));
         var2.popAlpha();
         LocalDate var19 = var3.today.minusDays(var5 - 1L - var16);
         double var20 = ProfileTotals.series(var3.data, var4, var19, 1)[0];
         ProfileMetrics.tooltipBeside(
            var2, var17, var18, var16 == var5 - 1 ? "Today" : ProfileFormatters.day(var19), var4.format(var20), var22, var7 + 20.0F, var8 - 10.0F, var9 + 8.0F
         );
      } else if (var22 <= 0.01F) {
         this.guide.snap(this.hoverIndex);
      }
   }

   private void empty(Canvas var1, float var2, float var3, float var4, float var5) {
      float var6 = (var2 + var3) / 2.0F;
      float var7 = (var4 + var5) / 2.0F;
      var1.icon("sparkles", var6, var7 - 18.0F, 20.0F, BlendMode.withAlpha(Theme.tint(-4611329), 0.8F));
      var1.text(Fonts.MEDIUM, "Nothing here yet", var6, var7 + 6.0F, 12.5F, Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
      var1.text(Fonts.REGULAR, "Your line grows with every day you play", var6, var7 + 24.0F, 11.0F, Icons.TEXT_3, FontStyle.CENTER, FontWeight.MIDDLE);
   }

   private float curveAt(float var1, int var2) {
      for (int var3 = 1; var3 < var2; var3++) {
         if (this.curveX[var3] >= var1) {
            float var4 = (var1 - this.curveX[var3 - 1]) / Math.max(0.001F, this.curveX[var3] - this.curveX[var3 - 1]);
            return ProfileCurve.lerp(this.curveY[var3 - 1], this.curveY[var3], ProfileCurve.clamp01(var4));
         }
      }

      return var2 > 0 ? this.curveY[var2 - 1] : 0.0F;
   }

   boolean mouseDown(float var1, float var2) {
      return !(var2 < this.y) && !(var2 > this.y + 56.0F) && !(var1 < this.x) && !(var1 > this.x + this.w)
         ? this.ranges.mouseDown(var1, var2) || this.metrics.mouseDown(var1, var2)
         : false;
   }

   void select(int var1, int var2) {
      if (var1 >= 0) {
         this.metrics.select(var1);
      }

      if (var2 >= 0) {
         this.ranges.select(var2);
      }
   }
}
