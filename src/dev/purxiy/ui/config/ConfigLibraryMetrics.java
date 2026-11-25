package dev.purxiy.ui.config;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;

final class ConfigLibraryMetrics {
   static final float HEIGHT = 30.0F;
   private static final float PAD = 3.0F;
   private static final float CHIP_GAP = 6.0F;
   private static final float TEXT = 12.0F;
   private final String[] labels;

   private final String[] icons;
   private final boolean track;
   private final Animator highlightX = Animator.snappy(0.0F);
   private final Animator highlightW = Animator.snappy(0.0F);
   private final Animator[] hover;
   private final Animator[] bump;
   private final float[] optionX;
   private final float[] optionW;
   private int selected;
   private float x;
   private float y;
   private boolean first = true;

   ConfigLibraryMetrics(String[] var1,  String[] var2, boolean var3) {
      this.labels = var1;
      this.icons = var2;
      this.track = var3;
      int var4 = var1.length;
      this.hover = new Animator[var4];
      this.bump = new Animator[var4];
      this.optionX = new float[var4];
      this.optionW = new float[var4];

      for (int var5 = 0; var5 < var4; var5++) {
         this.hover[var5] = Animator.smooth(0.0F);
         this.bump[var5] = Animator.bouncy(0.0F);
      }
   }

   int selected() {
      return this.selected;
   }

   String label() {
      return this.labels[this.selected];
   }

   void select(int var1) {
      this.selected = Math.max(0, Math.min(this.labels.length - 1, var1));
   }

   void bump(int var1) {
      this.bump[var1].snap(0.0F).impulse(-38.0F);
   }

   float[] center(int var1) {
      return new float[]{this.optionX[var1] + this.optionW[var1] / 2.0F, this.y + 15.0F};
   }

   float width(Canvas var1) {
      float var2 = this.track ? 6.0F : -6.0F;

      for (int var3 = 0; var3 < this.labels.length; var3++) {
         var2 += this.optionWidth(var1, var3) + (this.track ? 0.0F : 6.0F);
      }

      return var2;
   }

   private float optionWidth(Canvas var1, int var2) {
      float var3 = this.icons != null && this.icons[var2] != null ? 19.0F : 0.0F;
      return Math.max(44.0F, var1.textWidth(Fonts.MEDIUM, this.labels[var2], 12.0F) + 26.0F + var3);
   }

   void render(DrawContext var1, float var2, float var3, boolean var4) {
      Canvas var5 = var1.canvas;
      float var6 = this.width(var5);
      this.x = var2;
      this.y = var3 - 15.0F;
      if (this.track) {
         var5.roundRect(var2, this.y, var6, 30.0F, 15.0F, 318767103);
         var5.roundRectOutline(var2, this.y, var6, 30.0F, 15.0F, 1.0F, 352321535);
      }

      float var7 = var2 + (this.track ? 3.0F : 0.0F);

      for (int var8 = 0; var8 < this.labels.length; var8++) {
         this.optionX[var8] = var7;
         this.optionW[var8] = this.optionWidth(var5, var8);
         var7 += this.optionW[var8] + (this.track ? 0.0F : 6.0F);
      }

      if (this.first) {
         this.highlightX.snap(this.optionX[this.selected] - var2);
         this.highlightW.snap(this.optionW[this.selected]);
         this.first = false;
      }

      float var21 = this.highlightX.target(this.optionX[this.selected] - var2).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var9 = this.highlightW.target(this.optionW[this.selected]).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var10 = this.track ? 3.0F : 0.0F;
      float var11 = 30.0F - var10 * 2.0F;
      if (!this.track) {
         for (int var12 = 0; var12 < this.labels.length; var12++) {
            float var13 = this.hover[var12].value();
            var5.roundRect(this.optionX[var12], this.y, this.optionW[var12], 30.0F, 15.0F, BlendMode.lerp(201326591, 419430399, var13));
            var5.roundRectOutline(this.optionX[var12], this.y, this.optionW[var12], 30.0F, 15.0F, 1.0F, BlendMode.lerp(352321535, 721420287, var13));
         }
      }

      var5.glow(var2 + var21, this.y + var10, var9, var11, var11 / 2.0F, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.28F));
      var5.roundRectGradient(var2 + var21, this.y + var10, var9, var11, var11 / 2.0F, 0.0F, Icons.INDIGO, Icons.PURPLE);
      var5.roundRectCorners(var2 + var21, this.y + var10, var9, var11, var11 / 2.0F, 721420287, 721420287, 16777215, 16777215);

      for (int var22 = 0; var22 < this.labels.length; var22++) {
         boolean var23 = var4 && var1.hover(this.hover[var22], this.optionX[var22], this.y, this.optionW[var22], 30.0F, DrawContext.Cursor.HAND);
         float var14 = this.hover[var22].target(var23 && var22 != this.selected ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         float var15 = this.bump[var22].update(MotionStyle.TOGGLE.dt(var1.dt));
         int var16 = var22 == this.selected ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var14);
         float var17 = this.optionX[var22] + this.optionW[var22] / 2.0F;
         String var18 = this.icons != null ? this.icons[var22] : null;
         var5.push();
         var5.translate(0.0F, var15);
         if (var18 != null) {
            float var19 = var5.textWidth(Fonts.MEDIUM, this.labels[var22], 12.0F);
            float var20 = var17 - (var19 + 19.0F) / 2.0F;
            var5.icon(var18, var20 + 7.0F, var3, 13.5F, var16);
            var5.text(Fonts.MEDIUM, this.labels[var22], var20 + 19.0F, var3, 12.0F, var16, FontStyle.LEFT, FontWeight.MIDDLE);
         } else {
            var5.text(Fonts.MEDIUM, this.labels[var22], var17, var3, 12.0F, var16, FontStyle.CENTER, FontWeight.MIDDLE);
         }

         var5.pop();
      }
   }

   int mouseDown(float var1, float var2) {
      if (!(var2 < this.y) && !(var2 > this.y + 30.0F)) {
         for (int var3 = 0; var3 < this.labels.length; var3++) {
            if (var1 >= this.optionX[var3] && var1 < this.optionX[var3] + this.optionW[var3]) {
               if (var3 != this.selected) {
                  this.selected = var3;
                  SoundRequest.play(SoundEvent.TAB);
               }

               return var3;
            }
         }

         return -1;
      } else {
         return -1;
      }
   }
}
