// Sourced By DevFFNightmare
package dev.purxiy.ui.widget;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import net.minecraft.class_3532;

public final class ModeButton {
   public static final float CELL = 22.0F;
   private static final float MIN_CELL = 14.0F;
   private static final float GAP = 4.0F;
   private static final float HOTBAR_GAP = 7.0F;
   private static final float AUTO_W = 44.0F;
   private static final float AUTO_GAP = 8.0F;
   private static final float RADIUS = 6.0F;
   private static final int GOLD = -11654;
   private final NumberSetting setting;
   private final boolean auto;
   private final boolean inventory;
   private final int count;
   private final Animator highlightX = Animator.snappy(0.0F);
   private final Animator highlightY = Animator.snappy(0.0F);
   private final Animator highlightW = Animator.snappy(0.0F);
   private final Animator[] hover;
   private final float[] cellX;
   private final float[] cellY;
   private final float[] cellW;
   private float cell = 22.0F;
   private boolean first = true;

   public ModeButton(NumberSetting var1) {
      this.setting = var1;
      this.auto = var1.min() < 1.0;
      this.inventory = var1.slots() == NumberSetting.TypeO.INVENTORY;
      this.count = this.inventory ? 36 : 9;
      this.hover = new Animator[this.count + 1];

      for (int var2 = 0; var2 < this.hover.length; var2++) {
         this.hover[var2] = Animator.smooth(0.0F);
      }

      this.cellX = new float[this.count + 1];
      this.cellY = new float[this.count + 1];
      this.cellW = new float[this.count + 1];
   }

   public float fit(float var1) {
      float var2 = (this.auto ? 52.0F : 0.0F) + 32.0F;
      return class_3532.method_15363((var1 - var2) / 9.0F, 14.0F, 22.0F);
   }

   public float width(float var1) {
      return (this.auto ? 52.0F : 0.0F) + var1 * 9.0F + 32.0F;
   }

   public float height(float var1) {
      return this.inventory ? var1 * 4.0F + 8.0F + 7.0F : var1;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      Canvas var6 = var1.canvas;
      this.cell = var4;
      float var7 = var2 - (var4 * 9.0F + 32.0F);
      float var8 = this.inventory ? var3 + var4 * 3.0F + 8.0F + 7.0F : var3;
      this.cellX[0] = var7 - 8.0F - 44.0F;
      this.cellY[0] = var8;
      this.cellW[0] = 44.0F;

      for (int var9 = 1; var9 <= this.count; var9++) {
         int var10 = var9 <= 9 ? var9 - 1 : (var9 - 10) % 9;
         this.cellX[var9] = var7 + var10 * (var4 + 4.0F);
         this.cellY[var9] = var9 <= 9 ? var8 : var3 + (var9 - 10) / 9 * (var4 + 4.0F);
         this.cellW[var9] = var4;
      }

      int var21 = this.selected();
      if (this.first) {
         this.highlightX.snap(this.cellX[var21]);
         this.highlightY.snap(this.cellY[var21]);
         this.highlightW.snap(this.cellW[var21]);
         this.first = false;
      }

      float var22 = this.highlightX.target(this.cellX[var21]).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var11 = this.highlightY.target(this.cellY[var21]).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var12 = this.highlightW.target(this.cellW[var21]).update(MotionStyle.TOGGLE.dt(var1.dt));
      float[] var13 = new float[this.count + 1];

      for (int var14 = this.auto ? 0 : 1; var14 <= this.count; var14++) {
         float var15 = this.cellX[var14];
         float var16 = this.cellY[var14];
         float var17 = this.cellW[var14];
         boolean var18 = var5 && var1.hover(this.hover[var14], var15, var16, var17, var4, DrawContext.TypeE.HAND);
         float var19 = this.hover[var14].target(var18 && var14 != var21 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         var13[var14] = var19;
         float var20 = var14 == 0 ? var4 / 2.0F : 6.0F;
         var6.roundRect(var15, var16, var17, var4, var20, BlendMode.lerp(318767103, 620756991, var19));
         var6.roundRectOutline(var15, var16, var17, var4, var20, 1.0F, BlendMode.lerp(352321535, BlendMode.withAlpha(Icons.PURPLE, 0.55F), var19));
      }

      float var23 = var21 == 0 ? var4 / 2.0F : 6.0F;
      var6.glow(var22, var11, var12, var4, var23, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.3F));
      var6.roundRectGradient(var22, var11, var12, var4, var23, 0.0F, Icons.INDIGO, Icons.PURPLE);
      var6.roundRectCorners(var22, var11, var12, var4, var23, 721420287, 721420287, 16777215, 16777215);

      for (int var24 = this.auto ? 0 : 1; var24 <= this.count; var24++) {
         float var25 = this.cellX[var24];
         float var26 = this.cellY[var24];
         float var27 = this.cellW[var24];
         if (var24 == 0) {
            int var28 = var21 == 0 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var13[0]);
            var6.text(Fonts.MEDIUM, "Auto", var25 + var27 / 2.0F, var26 + var4 / 2.0F, 11.5F, var28, FontStyle.CENTER, FontWeight.MIDDLE);
         } else {
            if (var24 <= 9) {
               int var29 = var24 == var21 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_3, Icons.TEXT_2, var13[var24]);
               var6.text(
                  Fonts.MEDIUM,
                  Integer.toString(var24),
                  var25 + var27 / 2.0F,
                  var26 + var4 / 2.0F,
                  Math.min(10.5F, var4 * 0.5F),
                  var29,
                  FontStyle.CENTER,
                  FontWeight.MIDDLE
               );
            }

            if (this.setting.marked(var24 - 1)) {
               var6.circle(var25 + var27 - 4.5F, var26 + 4.5F, Math.min(2.2F, var4 * 0.1F), -11654);
            }
         }
      }
   }

   public boolean mouseDown(float var1, float var2) {
      for (int var3 = this.auto ? 0 : 1; var3 <= this.count; var3++) {
         if (var1 >= this.cellX[var3] && var1 < this.cellX[var3] + this.cellW[var3] && var2 >= this.cellY[var3] && var2 < this.cellY[var3] + this.cell) {
            if (var3 != this.selected()) {
               this.setting.set((double)var3);
               SoundRequest.play(SoundEvent.TAB);
            }

            return true;
         }
      }

      return false;
   }

   private int selected() {
      return class_3532.method_15340(this.setting.intValue(), this.auto ? 0 : 1, this.count);
   }
}
