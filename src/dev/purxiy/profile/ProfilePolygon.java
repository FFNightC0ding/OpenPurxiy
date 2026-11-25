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

final class ProfilePolygon {
   private static final ProfileTotals.TotalKind[] STYLES = ProfileTotals.TotalKind.values();
   private final Animator[] values = new Animator[STYLES.length];
   private final Animator[] hover = new Animator[STYLES.length];
   private final Object[] keys = new Object[STYLES.length];
   private final float[] px = new float[STYLES.length];
   private final float[] py = new float[STYLES.length];

   ProfilePolygon() {
      for (int var1 = 0; var1 < STYLES.length; var1++) {
         this.values[var1] = new Animator(0.0F, 90.0F, 13.0F);
         this.hover[var1] = Animator.smooth(0.0F);
         this.keys[var1] = new Object();
      }
   }

   void enter() {
      for (Animator var4 : this.values) {
         var4.snap(0.0F);
      }
   }

   void render(DrawContext var1, ProfileSummary var2, float var3, float var4, float var5, float var6, float var7, float var8, boolean var9) {
      Canvas var10 = var1.canvas;
      ProfileMetrics.card(var10, var3, var4, var5, var6, 0.0F);
      ProfileTotals.TotalKind var11 = var2.archetype;
      ProfileMetrics.title(var10, "Playstyle", var11 == null ? "Play a little and your shape appears" : "Measured per hour you play", var3, var4);
      if (var11 != null) {
         String var12 = "The " + var11.label;
         float var13 = var10.textWidth(Fonts.SEMIBOLD, var12, 12.0F);
         float var14 = var13 + 34.0F;
         float var15 = 24.0F;
         float var16 = var3 + var5 - 20.0F - var14;
         float var17 = var4 + 30.0F - var15 / 2.0F;
         var10.roundRect(var16, var17, var14, var15, var15 / 2.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.14F));
         var10.roundRectOutline(var16, var17, var14, var15, var15 / 2.0F, 1.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.4F));
         var10.icon(var11.icon, var16 + 13.0F, var17 + var15 / 2.0F, 12.0F, Theme.tint(-1012489));
         var10.textGradient(
            Fonts.SEMIBOLD,
            var12,
            var16 + 24.0F,
            var17 + var15 / 2.0F,
            12.0F,
            Theme.tint(-4611329),
            Theme.tint(-1012489),
            FontStyle.LEFT,
            FontWeight.MIDDLE,
            0.0F
         );
      }

      float var29 = var3 + var5 / 2.0F;
      float var30 = var4 + 60.0F + (var6 - 60.0F) / 2.0F + 2.0F;
      float var31 = Math.min(var5 * 0.3F, (var6 - 60.0F) / 2.0F - 26.0F);
      boolean var32 = var8 > 0.3F;

      for (int var33 = 0; var33 < STYLES.length; var33++) {
         double var38 = STYLES[var33].score(var2.lifetime);
         float var19 = var32 ? (float)(0.08 + 0.92 * var38) : 0.0F;
         this.values[var33].target(var19).update(MotionStyle.MENU.dt(var1.dt) * 0.8F);
      }

      for (int var34 = 1; var34 <= 3; var34++) {
         this.polygon(var10, var29, var30, var31 * var34 / 3.0F, var34 == 3 ? 486539263 : 234881023);
      }

      for (int var35 = 0; var35 < STYLES.length; var35++) {
         double var39 = angle(var35);
         var10.line(var29, var30, var29 + (float)Math.cos(var39) * var31, var30 + (float)Math.sin(var39) * var31, 1.0F, 234881023);
      }

      for (int var36 = 0; var36 < STYLES.length; var36++) {
         double var40 = angle(var36);
         float var45 = Math.max(0.0F, this.values[var36].value());
         this.px[var36] = var29 + (float)Math.cos(var40) * var31 * var45;
         this.py[var36] = var30 + (float)Math.sin(var40) * var31 * var45;
      }

      int var37 = BlendMode.withAlpha(Icons.PURPLE, 0.1F);
      int var41 = BlendMode.withAlpha(Icons.MAGENTA, 0.34F);

      for (int var18 = 0; var18 < STYLES.length; var18++) {
         int var46 = (var18 + 1) % STYLES.length;
         var10.triangle(var29, var30, this.px[var18], this.py[var18], this.px[var46], this.py[var46], var37, var41, var41);
      }

      for (int var42 = 0; var42 < STYLES.length; var42++) {
         int var47 = (var42 + 1) % STYLES.length;
         var10.line(this.px[var42], this.py[var42], this.px[var47], this.py[var47], 1.8F, Theme.tint(-4611329), Theme.tint(-1012489));
      }

      for (int var43 = 0; var43 < STYLES.length; var43++) {
         float var48 = this.hover[var43].value();
         float var20 = Math.max(0.0F, this.values[var43].value());
         var10.glowCircle(
            this.px[var43],
            this.py[var43],
            2.0F,
            8.0F + var48 * 6.0F,
            BlendMode.withAlpha(Icons.MAGENTA, (0.45F + 0.3F * var48) * Math.min(1.0F, var20 * 3.0F))
         );
         var10.circle(this.px[var43], this.py[var43], 1.2F + 2.0F * Math.min(1.0F, var20 * 2.5F) + var48, -1);
      }

      int var44 = -1;

      for (int var49 = 0; var49 < STYLES.length; var49++) {
         double var51 = angle(var49);
         float var22 = var29 + (float)Math.cos(var51) * (var31 + 22.0F);
         float var23 = var30 + (float)Math.sin(var51) * (var31 + 16.0F);
         ProfileTotals.TotalKind var24 = STYLES[var49];
         float var25 = var10.textWidth(Fonts.MEDIUM, var24.label, 10.5F);
         boolean var26 = var9 && var1.hover(this.keys[var49], var22 - var25 / 2.0F - 18.0F, var23 - 11.0F, var25 + 36.0F, 22.0F, DrawContext.Cursor.ARROW);
         float var27 = this.hover[var49].target(var26 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         int var28 = var24 == var11 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var27);
         var10.icon(
            var24.icon,
            var22 - var25 / 2.0F - 8.0F,
            var23,
            11.0F,
            var24 == var11 ? Theme.tint(-1012489) : BlendMode.lerp(Icons.TEXT_3, Theme.tint(-4611329), var27)
         );
         var10.text(Fonts.MEDIUM, var24.label, var22 + 6.0F, var23, 10.5F, var28, FontStyle.CENTER, FontWeight.MIDDLE);
         if (var27 > 0.01F && (var44 < 0 || var27 > this.hover[var44].value())) {
            var44 = var49;
         }
      }

      if (var44 >= 0) {
         ProfileTotals.TotalKind var50 = STYLES[var44];
         double var52 = angle(var44);
         float var53 = var29 + (float)Math.cos(var52) * (var31 + 22.0F);
         float var54 = var30 + (float)Math.sin(var52) * (var31 + 16.0F);
         String var55 = ProfileFormatters.count(Math.round(var50.rate(var2.lifetime)));
         String var56 = var55 + " " + var50.unit + " an hour";
         ProfileMetrics.tooltip(var10, var53, var54 - 10.0F, var50.label, var56, this.hover[var44].value());
      }
   }

   private void polygon(Canvas var1, float var2, float var3, float var4, int var5) {
      for (int var6 = 0; var6 < STYLES.length; var6++) {
         double var7 = angle(var6);
         double var9 = angle(var6 + 1);
         var1.line(
            var2 + (float)Math.cos(var7) * var4,
            var3 + (float)Math.sin(var7) * var4,
            var2 + (float)Math.cos(var9) * var4,
            var3 + (float)Math.sin(var9) * var4,
            1.0F,
            var5
         );
      }
   }

   private static double angle(int var0) {
      return (-Math.PI / 2) + var0 * 2 * Math.PI / STYLES.length;
   }
}
