// Sourced By DevFFNightmare
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

public final class Divider {
   private static final float HEIGHT = 30.0F;
   private static final float PAD = 3.0F;
   private static final float TEXT = 12.0F;
   private final ModeSetting setting;
   private final Animator highlightX;
   private final Animator highlightW;
   private final Animator[] hover;
   private final float[] optionX;
   private final float[] optionW;
   private float x;
   private float y;
   private boolean first = true;

   public Divider(ModeSetting var1) {
      this.setting = var1;
      int var2 = var1.modes().size();
      this.optionX = new float[var2];
      this.optionW = new float[var2];
      this.hover = new Animator[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         this.hover[var3] = Animator.smooth(0.0F);
      }

      this.highlightX = Animator.snappy(0.0F);
      this.highlightW = Animator.snappy(0.0F);
   }

   public float width() {
      float var1 = 6.0F;

      for (String var3 : this.setting.modes()) {
         var1 += optionWidth(var3);
      }

      return var1;
   }

   private static float optionWidth(String var0) {
      return Math.max(52.0F, Fonts.MEDIUM.width(var0, 12.0F) + 26.0F);
   }

   public void render(DrawContext var1, float var2, float var3) {
      Canvas var4 = var1.canvas;
      float var5 = this.width();
      this.x = var2 - var5;
      this.y = var3 - 15.0F;
      var4.roundRect(this.x, this.y, var5, 30.0F, 15.0F, 318767103);
      var4.roundRectOutline(this.x, this.y, var5, 30.0F, 15.0F, 1.0F, 352321535);
      float var6 = this.x + 3.0F;

      for (int var7 = 0; var7 < this.optionX.length; var7++) {
         this.optionX[var7] = var6;
         this.optionW[var7] = optionWidth(this.setting.modes().get(var7));
         var6 += this.optionW[var7];
      }

      int var15 = this.setting.index();
      if (this.first) {
         this.highlightX.snap(this.optionX[var15] - this.x);
         this.highlightW.snap(this.optionW[var15]);
         this.first = false;
      }

      float var8 = this.highlightX.target(this.optionX[var15] - this.x).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var9 = this.highlightW.target(this.optionW[var15]).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var10 = 24.0F;
      var4.glow(this.x + var8, this.y + 3.0F, var9, var10, var10 / 2.0F, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.3F));
      var4.roundRectGradient(this.x + var8, this.y + 3.0F, var9, var10, var10 / 2.0F, 0.0F, Icons.INDIGO, Icons.PURPLE);
      var4.roundRectCorners(this.x + var8, this.y + 3.0F, var9, var10, var10 / 2.0F, 721420287, 721420287, 16777215, 16777215);

      for (int var11 = 0; var11 < this.optionX.length; var11++) {
         boolean var12 = var1.hover(this.hover[var11], this.optionX[var11], this.y, this.optionW[var11], 30.0F, DrawContext.TypeE.HAND);
         float var13 = this.hover[var11].target(var12 && var11 != var15 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         int var14 = var11 == var15 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var13);
         var4.text(
            Fonts.MEDIUM,
            this.setting.modes().get(var11),
            this.optionX[var11] + this.optionW[var11] / 2.0F,
            var3,
            12.0F,
            var14,
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
      }
   }

   public boolean mouseDown(float var1, float var2) {
      if (!(var2 < this.y) && !(var2 > this.y + 30.0F)) {
         for (int var3 = 0; var3 < this.optionX.length; var3++) {
            if (var1 >= this.optionX[var3] && var1 < this.optionX[var3] + this.optionW[var3]) {
               if (var3 != this.setting.index()) {
                  this.setting.select(var3);
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
