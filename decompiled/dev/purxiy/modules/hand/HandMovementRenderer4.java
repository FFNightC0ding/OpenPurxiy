package dev.purxiy.modules.hand;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.DrawContext.TypeE;

public final class HandMovementRenderer4 {
   public static final float Z = 100.0F;
   private static final float T = 36.0F;
   private static final float E = 6.0F;
   private final HandMovementModule J;
   private final Animator[] v = new Animator[HandMovementCatalog.P.size()];
   private final Object[] l = new Object[HandMovementCatalog.P.size()];
   private final float[] i = new float[HandMovementCatalog.P.size()];
   private final Animator g = Animator.snappy(0.0F);
   private final Animator f = Animator.snappy(0.0F);
   private final Animator p = Animator.smooth(0.0F);
   private final Animator R = Animator.smooth(0.0F);
   private float n;
   private float e;
   private float m;
   private boolean N;
   private boolean q = true;

   public HandMovementRenderer4(HandMovementModule var1) {
      this.J = var1;

      for (int var2 = 0; var2 < this.v.length; var2++) {
         this.v[var2] = Animator.smooth(0.0F);
         this.l[var2] = new Object();
      }
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      Canvas var6 = var1.canvas;
      this.n = var2;
      this.N = var5 && !var1.masked();
      float var7 = var3 + 29.0F - 9.0F;
      var6.text(Fonts.MEDIUM, "Placement", var2, var7, 13.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      String var8 = "Where your hand sits: a start, the studio fine-tunes it";
      var6.text(
         Fonts.REGULAR,
         FontMetrics.ellipsize(Fonts.REGULAR, var8, 11.5F, var4 - 90.0F, 0.0F),
         var2,
         var7 + 19.0F,
         11.5F,
         Icons.TEXT_3,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      int var9 = -1;

      for (int var10 = 0; var10 < HandMovementCatalog.P.size(); var10++) {
         if (this.J.F.is(HandMovementCatalog.P.get(var10).name())) {
            var9 = var10;
         }
      }

      float var23 = this.R.target(var9 < 0 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      if (var23 > 0.01F) {
         var6.pushAlpha(var23);
         float var11 = var6.textWidth(Fonts.SEMIBOLD, "Custom", 10.5F);
         float var12 = var11 + 26.0F;
         float var13 = 22.0F;
         float var14 = var2 + var4 - var12;
         float var15 = var7 - var13 / 2.0F;
         var6.roundRect(var14, var15, var12, var13, var13 / 2.0F, BlendMode.withAlpha(Icons.PURPLE, 0.16F));
         var6.roundRectOutline(var14, var15, var12, var13, var13 / 2.0F, 1.0F, BlendMode.withAlpha(Icons.PURPLE, 0.55F));
         var6.circle(var14 + 10.0F, var7, 2.5F, Icons.MAGENTA);
         var6.text(Fonts.SEMIBOLD, "Custom", var14 + 17.0F, var7, 10.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
         var6.popAlpha();
      }

      int var24 = HandMovementCatalog.P.size();
      this.e = var3 + 54.0F;
      this.m = (var4 - 6.0F * (var24 - 1)) / var24;

      for (int var25 = 0; var25 < var24; var25++) {
         this.i[var25] = var2 + var25 * (this.m + 6.0F);
      }

      float var26 = this.p.target(var9 >= 0 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      if (var9 >= 0) {
         if (this.q || var26 < 0.02F) {
            this.g.snap(this.i[var9] - var2);
            this.f.snap(this.m);
         }

         this.g.target(this.i[var9] - var2);
         this.f.target(this.m);
      }

      this.q = false;
      float var27 = var2 + this.g.update(MotionStyle.TOGGLE.dt(var1.dt));
      float var28 = this.f.update(MotionStyle.TOGGLE.dt(var1.dt));

      for (int var29 = 0; var29 < var24; var29++) {
         float var16 = this.i[var29];
         boolean var17 = this.N && var1.hover(this.l[var29], var16, this.e, this.m, 36.0F, TypeE.HAND);
         float var18 = this.v[var29].target(var17 && var29 != var9 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         var6.roundRect(var16, this.e, this.m, 36.0F, 11.0F, BlendMode.lerp(251658239, 452984831, var18));
         var6.roundRectOutline(var16, this.e, this.m, 36.0F, 11.0F, 1.0F, BlendMode.lerp(352321535, 788529151, var18));
      }

      if (var26 > 0.01F) {
         var6.pushAlpha(var26);
         var6.glow(var27, this.e, var28, 36.0F, 11.0F, 14.0F, BlendMode.withAlpha(Icons.VIOLET, 0.3F));
         var6.roundRectGradient(var27, this.e, var28, 36.0F, 11.0F, 0.0F, Icons.INDIGO, Icons.PURPLE);
         var6.roundRectCorners(var27, this.e, var28, 36.0F, 11.0F, 721420287, 721420287, 16777215, 16777215);
         var6.popAlpha();
      }

      for (int var30 = 0; var30 < var24; var30++) {
         HandMovementCatalog.Data var31 = HandMovementCatalog.P.get(var30);
         float var32 = this.i[var30] + this.m / 2.0F;
         float var33 = var30 == var9 ? var26 : 0.0F;
         int var19 = BlendMode.lerp(BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, this.v[var30].value()), Icons.TEXT, var33);
         float var20 = var6.textWidth(Fonts.MEDIUM, var31.name(), 11.0F);
         boolean var21 = var20 + 20.0F <= this.m - 10.0F;
         float var22 = var32 - (var20 + (var21 ? 17 : 0)) / 2.0F;
         if (var21) {
            var6.icon(var31.icon(), var22 + 6.0F, this.e + 18.0F, 12.0F, var19);
         }

         var6.text(Fonts.MEDIUM, var31.name(), var22 + (var21 ? 17 : 0), this.e + 18.0F, 11.0F, var19, FontStyle.LEFT, FontWeight.MIDDLE);
      }
   }

   public boolean mouseDown(float var1, float var2) {
      if (this.N && !(var2 < this.e) && !(var2 > this.e + 36.0F)) {
         for (int var3 = 0; var3 < this.i.length; var3++) {
            if (var1 >= this.i[var3] && var1 <= this.i[var3] + this.m) {
               String var4 = HandMovementCatalog.P.get(var3).name();
               if (!this.J.F.is(var4)) {
                  this.J.choosePreset(var4);
                  UiAudioPolicy.play("TAB");
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
