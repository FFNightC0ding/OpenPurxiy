package dev.purxiy.profile;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Map.Entry;

final class ProfileRecordsPage {
   private static final int ROWS = 5;
   private final Animator[] hover = new Animator[5];
   private final Object[] keys = new Object[5];

   ProfileRecordsPage() {
      for (int var1 = 0; var1 < 5; var1++) {
         this.hover[var1] = Animator.smooth(0.0F);
         this.keys[var1] = new Object();
      }
   }

   void render(DrawContext var1, ProfileSummary var2, float var3, float var4, float var5, float var6, float var7, boolean var8) {
      Canvas var9 = var1.canvas;
      ProfileMetrics.card(var9, var3, var4, var5, var6, 0.0F);
      ProfileMetrics.title(var9, "Records", "Your personal bests", var3, var4);
      String[] var10 = new String[]{"hourglass", "calendar-days", "flame", "zap", "map-pin"};
      String[] var11 = new String[]{"Longest session", "Busiest day", "Best streak", "Top speed", "Favourite place"};
      String[] var12 = new String[5];
      String[] var13 = new String[5];
      ProfileDay.DayCache var14 = var2.longest;
      var12[0] = var14 == null ? "—" : ProfileFormatters.duration(var14.totals.play);
      var13[0] = var14 == null ? "" : ProfileFormatters.date(LocalDate.ofInstant(Instant.ofEpochMilli(var14.start), ZoneId.systemDefault()));
      Entry var15 = var2.busiest;
      var12[1] = var15 == null ? "—" : ProfileFormatters.duration(((ProfileLoader)var15.getValue()).play);
      var13[1] = var15 == null ? "" : ProfileFormatters.day((LocalDate)var15.getKey());
      var12[2] = var2.bestStreak == 0 ? "—" : var2.bestStreak + (var2.bestStreak == 1 ? " day" : " days");
      var13[2] = var2.streak > 0 && var2.streak == var2.bestStreak ? "Going on now" : "";
      var12[3] = var2.data.topSpeed <= 0.0 ? "—" : ProfileFormatters.speed(var2.data.topSpeed);
      var13[3] = var2.data.topSpeed <= 0.0 ? "" : ProfileFormatters.distance(var2.data.topSpeed) + " a second";
      ProfileDay.DayRecord var16 = var2.favourite;
      var12[4] = var16 == null ? "—" : var16.name;
      var13[4] = var16 == null ? "" : ProfileFormatters.duration(var16.play);
      float var17 = var4 + 60.0F;
      float var18 = Math.min(36.0F, (var6 - 70.0F) / 5.0F);

      for (int var19 = 0; var19 < 5; var19++) {
         float var20 = Easing.EMPHASIZED.apply(ProfileCurve.clamp01((var7 - 0.15F - var19 * 0.06F) / 0.5F));
         float var21 = var17 + var19 * var18;
         boolean var22 = var8 && var1.hover(this.keys[var19], var3 + 10.0F, var21, var5 - 20.0F, var18, DrawContext.Cursor.ARROW);
         float var23 = this.hover[var19].target(var22 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         var9.pushAlpha(var20);
         if (var23 > 0.01F) {
            var9.roundRect(var3 + 10.0F, var21 + 1.0F, var5 - 20.0F, var18 - 2.0F, 10.0F, BlendMode.withAlpha(-1, 0.04F * var23));
         }

         float var24 = var21 + var18 / 2.0F + (1.0F - var20) * 6.0F;
         float var25 = 24.0F;
         var9.roundRect(var3 + 20.0F, var24 - var25 / 2.0F, var25, var25, 7.0F, 318767103);
         var9.icon(var10[var19], var3 + 20.0F + var25 / 2.0F, var24, 12.0F, BlendMode.lerp(Theme.tint(-4611329), Theme.tint(-1012489), var19 / 4.0F));
         var9.text(Fonts.MEDIUM, var11[var19], var3 + 20.0F + var25 + 10.0F, var24, 11.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
         float var26 = var3 + var5 - 20.0F;
         float var27 = var13[var19].isEmpty() ? 0.0F : var9.textWidth(Fonts.REGULAR, var13[var19], 10.0F) + 10.0F;
         float var28 = var5 - 40.0F - var25 - 10.0F - var9.textWidth(Fonts.MEDIUM, var11[var19], 11.5F) - 16.0F - var27;
         String var29 = FontMetrics.ellipsize(Fonts.SEMIBOLD, var12[var19], 12.5F, Math.max(30.0F, var28), 0.0F);
         var9.text(Fonts.SEMIBOLD, var29, var26 - var27, var24, 12.5F, Icons.TEXT, FontStyle.RIGHT, FontWeight.MIDDLE);
         if (!var13[var19].isEmpty()) {
            var9.text(Fonts.REGULAR, var13[var19], var26, var24, 10.0F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.MIDDLE);
         }

         var9.popAlpha();
      }
   }
}
