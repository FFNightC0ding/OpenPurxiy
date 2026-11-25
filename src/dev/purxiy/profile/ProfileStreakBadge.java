package dev.purxiy.profile;

import dev.purxiy.anim.Animator;
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

final class ProfileStreakBadge {
   private final Animator hoverAlpha = Animator.smooth(0.0F);
   private final Object gridKey = new Object();

   private LocalDate hoverDay;
   private float hoverX;
   private float hoverY;

   void render(DrawContext var1, ProfileSummary var2, float var3, float var4, float var5, float var6, float var7, float var8, boolean var9) {
      Canvas var10 = var1.canvas;
      ProfileMetrics.card(var10, var3, var4, var5, var6, 0.0F);
      String var11 = var2.activeDays == 0
         ? "Every star is a day you played"
         : ProfileFormatters.count(var2.activeDays) + (var2.activeDays == 1 ? " day" : " days") + " under the stars  ·  best streak " + var2.bestStreak;
      ProfileMetrics.title(var10, "Your sky", var11, var3, var4);
      this.streakPill(var10, var2.streak, var3 + var5 - 20.0F, var4 + 30.0F, var7);
      float var12 = var3 + 20.0F + 30.0F;
      float var13 = var4 + 76.0F;
      float var14 = var4 + var6 - 20.0F;
      float var15 = Math.min(22.0F, (var14 - var13) / 7.0F);
      int var16 = Math.max(4, (int)((var3 + var5 - 20.0F - var12) / var15));
      float var17 = var16 * var15;
      var12 = var3 + var5 - 20.0F - var17;
      LocalDate var18 = var2.today;
      int var19 = var18.getDayOfWeek().getValue() - 1;
      LocalDate var20 = var18.minusDays(var19).minusWeeks(var16 - 1L);
      double var21 = 60.0;

      for (int var23 = 0; var23 < var16; var23++) {
         for (int var24 = 0; var24 < 7; var24++) {
            ProfileLoader var25 = var2.data.days.get(var20.plusDays(var23 * 7L + var24));
            if (var25 != null) {
               var21 = Math.max(var21, var25.play);
            }
         }
      }

      String[] var41 = new String[]{"Mon", "", "Wed", "", "Fri", "", ""};

      for (int var42 = 0; var42 < 7; var42++) {
         if (!var41[var42].isEmpty()) {
            var10.text(Fonts.MEDIUM, var41[var42], var12 - 10.0F, var13 + var42 * var15 + var15 / 2.0F, 9.5F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.MIDDLE);
         }
      }

      float var43 = -1000.0F;

      for (int var44 = 0; var44 < var16; var44++) {
         LocalDate var26 = var20.plusWeeks(var44).plusDays(6L);
         boolean var27 = var44 == 0 || var26.getMonth() != var26.minusWeeks(1L).getMonth();
         float var28 = var12 + var44 * var15 + 2.0F;
         if (var27 && var28 - var43 >= var15 * 3.0F) {
            var10.text(Fonts.MEDIUM, ProfileFormatters.month(var26), var28, var13 - 12.0F, 9.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
            var43 = var28;
         }
      }

      float var45 = ProfileCurve.clamp01((var8 - 0.2F) / 1.2F);
      if (var2.streak > 1 && var45 > 0.6F) {
         float var46 = ProfileCurve.clamp01((var45 - 0.6F) / 0.4F);
         LocalDate var48 = var18;
         if (!ProfileTotals.active(var2.data, var18)) {
            var48 = var18.minusDays(1L);
         }

         for (int var50 = 0; var50 < var2.streak - 1; var50++) {
            LocalDate var29 = var48.minusDays(1L);
            float[] var30 = this.center(var20, var48, var12, var13, var15);
            float[] var31 = this.center(var20, var29, var12, var13, var15);
            if (var31[0] < var12) {
               break;
            }

            var10.line(var30[0], var30[1], var31[0], var31[1], 0.9F, BlendMode.withAlpha(Theme.tint(-4611329), 0.26F * var46));
            var48 = var29;
         }
      }

      LocalDate var47 = null;
      boolean var49 = var9 && var1.hover(this.gridKey, var12, var13, var17, var15 * 7.0F, DrawContext.Cursor.ARROW);

      for (int var51 = 0; var51 < var16; var51++) {
         for (int var53 = 0; var53 < 7; var53++) {
            LocalDate var55 = var20.plusDays(var51 * 7L + var53);
            if (!var55.isAfter(var18)) {
               float[] var57 = this.center(var20, var55, var12, var13, var15);
               float var32 = var57[0];
               float var33 = var57[1];
               float var34 = ProfileCurve.clamp01(var45 * 1.4F - (float)var51 / var16);
               ProfileLoader var35 = var2.data.days.get(var55);
               double var36 = var35 == null ? 0.0 : var35.play;
               this.star(var10, var32, var33, var36, var21, var55.equals(var18), var7, var51 * 7 + var53, var34);
               float var38 = var12 + var51 * var15 + var15 / 2.0F;
               float var39 = var13 + var53 * var15 + var15 / 2.0F;
               if (var49 && Math.abs(var1.mouseX - var38) <= var15 / 2.0F && Math.abs(var1.mouseY - var39) <= var15 / 2.0F) {
                  var47 = var55;
                  this.hoverX = var32;
                  this.hoverY = var33;
               }
            }
         }
      }

      if (var47 != null) {
         this.hoverDay = var47;
      }

      float var52 = this.hoverAlpha.target(var47 != null ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      if (var52 > 0.01F && this.hoverDay != null) {
         var10.ring(this.hoverX, this.hoverY, var15 * 0.42F, 1.0F, BlendMode.withAlpha(-1, 0.5F * var52));
         ProfileLoader var54 = var2.data.days.get(this.hoverDay);
         String var56 = var54 != null && !(var54.play < 60.0) ? ProfileFormatters.duration(var54.play) + " played" : "Not played";
         ProfileMetrics.tooltip(
            var10, this.hoverX, this.hoverY - var15 * 0.35F, this.hoverDay.equals(var18) ? "Today" : ProfileFormatters.day(this.hoverDay), var56, var52
         );
      }
   }

   private float[] center(LocalDate var1, LocalDate var2, float var3, float var4, float var5) {
      long var6 = var2.toEpochDay() - var1.toEpochDay();
      long var8 = Math.floorDiv(var6, 7);
      long var10 = Math.floorMod(var6, 7);
      int var12 = (int)var2.toEpochDay();
      float var13 = (hash(var12 * 2) - 0.5F) * var5 * 0.44F;
      float var14 = (hash(var12 * 2 + 1) - 0.5F) * var5 * 0.44F;
      return new float[]{var3 + (float)var8 * var5 + var5 / 2.0F + var13, var4 + (float)var10 * var5 + var5 / 2.0F + var14};
   }

   private void star(Canvas var1, float var2, float var3, double var4, double var6, boolean var8, float var9, int var10, float var11) {
      if (!(var11 <= 0.01F)) {
         float var12 = MotionStyle.reduced() ? 1.0F : 0.82F + 0.18F * (float)Math.sin(var9 * (1.1F + hash(var10) * 1.3F) + var10);
         if (var4 < 60.0) {
            var1.circle(var2, var3, var8 ? 1.8F : 0.9F, BlendMode.withAlpha(-1, (var8 ? 0.35F : 0.09F) * var11));
         } else {
            float var13 = (float)Math.sqrt(var4 / var6);
            float var14 = 1.3F + 3.3F * var13;
            int var15 = BlendMode.lerp(Theme.tint(-4611329), Icons.PINK, var13 * 0.8F);
            var1.glowCircle(
               var2,
               var3,
               var14 * 0.8F,
               var14 * 3.2F,
               BlendMode.withAlpha(BlendMode.lerp(Icons.VIOLET, Icons.MAGENTA, var13), (0.12F + 0.3F * var13) * var11 * var12)
            );
            if (var13 > 0.9F) {
               float var16 = var14 * 2.2F * var12;
               int var17 = BlendMode.withAlpha(-1, 0.3F * var11);
               var1.line(var2 - var16, var3, var2 + var16, var3, 0.8F, var17 & 16777215, var17);
               var1.line(var2 + var16, var3, var2 - var16, var3, 0.8F, var17 & 16777215, var17);
               var1.line(var2, var3 - var16, var2, var3 + var16, 0.8F, var17 & 16777215, var17);
               var1.line(var2, var3 + var16, var2, var3 - var16, 0.8F, var17 & 16777215, var17);
            }

            var1.circle(var2, var3, var14, BlendMode.withAlpha(BlendMode.lerp(var15, -1, 0.25F * var12), var11));
         }

         if (var8) {
            float var18 = MotionStyle.reduced() ? 0.5F : var9 * 0.6F % 1.0F;
            var1.ring(var2, var3, 4.0F + var18 * 7.0F, 1.2F, BlendMode.withAlpha(Icons.PINK, 0.6F * (1.0F - var18) * var11));
         }
      }
   }

   private void streakPill(Canvas var1, int var2, float var3, float var4, float var5) {
      String var6 = var2 == 0 ? "No streak yet" : var2 + "-day streak";
      float var7 = var1.textWidth(Fonts.SEMIBOLD, var6, 11.0F);
      float var8 = var7 + 34.0F;
      float var9 = 24.0F;
      float var10 = var3 - var8;
      boolean var11 = var2 > 0;
      var1.roundRect(var10, var4 - var9 / 2.0F, var8, var9, var9 / 2.0F, var11 ? BlendMode.withAlpha(Icons.MAGENTA, 0.14F) : 285212671);
      var1.roundRectOutline(var10, var4 - var9 / 2.0F, var8, var9, var9 / 2.0F, 1.0F, var11 ? BlendMode.withAlpha(Icons.MAGENTA, 0.4F) : 352321535);
      float var12 = !MotionStyle.reduced() && var11 ? 0.85F + 0.15F * (float)Math.sin(var5 * 7.3F) * (float)Math.sin(var5 * 3.1F) : 1.0F;
      if (var11) {
         var1.glowCircle(var10 + 14.0F, var4, 3.0F, 9.0F, BlendMode.withAlpha(Icons.PINK, 0.4F * var12));
      }

      var1.icon("flame", var10 + 14.0F, var4, 13.0F, var11 ? BlendMode.lerp(Theme.tint(-1012489), Theme.tint(-11788), 1.0F - var12) : Icons.TEXT_3);
      var1.text(Fonts.SEMIBOLD, var6, var10 + 25.0F, var4, 11.0F, var11 ? Icons.TEXT : Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private static float hash(int var0) {
      double var1 = Math.sin(var0 * 12.9898 + 78.233) * 43758.5453;
      return (float)(var1 - Math.floor(var1));
   }
}
