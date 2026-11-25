package dev.purxiy.ui.widget;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import java.util.List;

public final class Pagination {
   private static final float HEIGHT = 30.0F;
   private static final float PAD = 3.0F;
   private static final float TEXT = 12.0F;
   private static final float ARROW = 26.0F;
   private static final float TRAVEL = 18.0F;
   private final ModeSetting setting;
   private final Animator slide = Animator.snappy(1.0F);
   private final Animator dot;
   private final Animator hoverLeft = Animator.smooth(0.0F);
   private final Animator hoverRight = Animator.smooth(0.0F);
   private final Object leftKey = new Object();
   private final Object rightKey = new Object();
   private int shown;
   private int previous = -1;
   private int direction = 1;
   private float x;
   private float y;
   private float w;

   public Pagination(ModeSetting var1) {
      this.setting = var1;
      this.shown = var1.index();
      this.dot = Animator.snappy(this.shown);
   }

   public float width() {
      float var1 = 0.0F;

      for (String var3 : this.setting.modes()) {
         var1 = Math.max(var1, Fonts.MEDIUM.width(var3, 12.0F));
      }

      return 52.0F + var1 + 30.0F;
   }

   public void render(DrawContext var1, float var2, float var3) {
      Canvas var4 = var1.canvas;
      List var5 = this.setting.modes();
      this.w = this.width();
      this.x = var2 - this.w;
      this.y = var3 - 15.0F;
      int var6 = this.setting.index();
      if (var6 != this.shown) {
         this.step(var6, Math.floorMod(var6 - this.shown, var5.size()) <= var5.size() / 2 ? 1 : -1);
      }

      float var7 = this.slide.target(1.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var8 = this.dot.target(this.shown).update(MotionStyle.TOGGLE.dt(var1.dt));
      boolean var9 = var1.hover(this.leftKey, this.x, this.y, 26.0F, 30.0F, DrawContext.Cursor.HAND);
      boolean var10 = !var9 && var1.hover(this.rightKey, this.x + 26.0F, this.y, this.w - 26.0F, 30.0F, DrawContext.Cursor.HAND);
      float var11 = this.hoverLeft.target(var9 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var12 = this.hoverRight.target(var10 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      var4.roundRect(this.x, this.y, this.w, 30.0F, 15.0F, 318767103);
      var4.roundRectOutline(this.x, this.y, this.w, 30.0F, 15.0F, 1.0F, 352321535);
      var4.circle(this.x + 13.0F + 3.0F, var3, 10.0F, BlendMode.withAlpha(-1, 0.07F * var11));
      var4.circle(this.x + this.w - 13.0F - 3.0F, var3, 10.0F, BlendMode.withAlpha(-1, 0.07F * var12));
      var4.icon("chevron-left", this.x + 13.0F + 3.0F, var3, 14.0F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var11));
      var4.icon("chevron-right", this.x + this.w - 13.0F - 3.0F, var3, 14.0F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var12));
      float var13 = this.x + 26.0F + 3.0F;
      float var14 = this.w - 58.0F;
      float var15 = 24.0F;
      float var16 = this.y + 3.0F;
      var4.glow(var13, var16, var14, var15, var15 / 2.0F, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.3F));
      var4.roundRectGradient(var13, var16, var14, var15, var15 / 2.0F, 0.0F, Icons.INDIGO, Icons.PURPLE);
      var4.roundRectCorners(var13, var16, var14, var15, var15 / 2.0F, 721420287, 721420287, 16777215, 16777215);
      var4.pushClip(var13, var16, var14, var15, var15 / 2.0F);
      float var17 = var13 + var14 / 2.0F;
      float var18 = var3 - 1.5F;
      if (this.previous >= 0 && var7 < 1.0F) {
         var4.pushAlpha(1.0F - Math.max(0.0F, var7));
         var4.text(
            Fonts.MEDIUM,
            (CharSequence)var5.get(this.previous),
            var17 - this.direction * 18.0F * var7,
            var18,
            12.0F,
            Icons.TEXT,
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
         var4.popAlpha();
      }

      var4.pushAlpha(Math.min(1.0F, Math.max(0.0F, var7)));
      var4.text(
         Fonts.MEDIUM,
         (CharSequence)var5.get(this.shown),
         var17 + this.direction * 18.0F * (1.0F - var7),
         var18,
         12.0F,
         Icons.TEXT,
         FontStyle.CENTER,
         FontWeight.MIDDLE
      );
      var4.popAlpha();
      var4.popClip();
      float var19 = 4.5F;
      float var20 = var16 + var15 - 4.5F;
      float var21 = var17 - (var5.size() - 1) * var19 / 2.0F;

      for (int var22 = 0; var22 < var5.size(); var22++) {
         var4.circle(var21 + var22 * var19, var20, 0.9F, 1509949439);
      }

      var4.roundRect(var21 + var8 * var19 - 2.6F, var20 - 0.9F, 5.2F, 1.8F, 0.9F, -1);
   }

   public boolean mouseDown(float var1, float var2) {
      if (!(var1 < this.x) && !(var1 > this.x + this.w) && !(var2 < this.y) && !(var2 > this.y + 30.0F)) {
         int var3 = var1 < this.x + 26.0F + 3.0F ? -1 : 1;
         this.setting.select(this.setting.index() + var3);
         this.step(this.setting.index(), var3);
         SoundRequest.play(SoundEvent.TAB);
         return true;
      } else {
         return false;
      }
   }

   private void step(int var1, int var2) {
      this.previous = this.shown;
      this.shown = var1;
      this.direction = var2;
      this.slide.snap(0.0F).target(1.0F);
   }
}
