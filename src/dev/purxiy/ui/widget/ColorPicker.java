package dev.purxiy.ui.widget;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;

public final class ColorPicker {
   public static final float SWATCH = 30.0F;
   private static final float FIELD_W = 216.0F;
   private static final float FIELD_H = 118.0F;
   private static final float STRIP_W = 16.0F;
   private static final float GAP = 14.0F;
   private static final float PRESET = 22.0F;
   private static final int[] PRESETS = new int[]{-4944641, -28744, -50340, -11930, -9444427, -11563521, -2234625, -1};
   private static final float CHIP_H = 24.0F;
   private static final int[] HUES = new int[]{-65536, -256, -16711936, -16711681, -16776961, -65281, -65536};
   private final ColorSetting setting;
   private final Animator open = Animator.smooth(0.0F);
   private final Animator hover = Animator.smooth(0.0F);
   private final Animator follow = Animator.snappy(0.0F);
   private final Object chipKey = new Object();
   private boolean expanded;
   private int dragging;
   private float hue;
   private float sat;
   private float val;
   private int lastColor;
   private float swatchX;
   private float swatchY;
   private float fieldX;
   private float fieldY;
   private float hueX;
   private float alphaX;
   private float presetX;

   public ColorPicker(ColorSetting var1) {
      this.setting = var1;
      this.syncFromSetting();
   }

   private void syncFromSetting() {
      int var1 = this.setting.argb();
      float[] var2 = rgbToHsv(var1);
      if (var2[1] > 0.001F && var2[2] > 0.001F) {
         this.hue = var2[0];
      }

      this.sat = var2[1];
      this.val = var2[2];
      this.lastColor = var1;
   }

   public float extraHeight() {
      return 136.0F * this.open.value();
   }

   public boolean expanded() {
      return this.expanded;
   }

   public boolean animating() {
      return !this.open.settled();
   }

   public void renderSwatch(DrawContext var1, float var2, float var3) {
      if (this.setting.argb() != this.lastColor) {
         this.syncFromSetting();
      }

      Canvas var4 = var1.canvas;
      this.swatchX = var2 - 30.0F;
      this.swatchY = var3 - 15.0F;
      boolean var5 = var1.hover(this, this.swatchX, this.swatchY, 30.0F, 30.0F, DrawContext.Cursor.HAND);
      float var6 = this.hover.target(!var5 && !this.expanded ? 0.0F : 1.0F).update(MotionStyle.HOVER.dt(var1.dt));
      this.checker(var4, this.swatchX, this.swatchY, 30.0F, 30.0F, 9.0F);
      var4.roundRect(this.swatchX, this.swatchY, 30.0F, 30.0F, 9.0F, this.setting.argb());
      var4.roundRectOutline(this.swatchX, this.swatchY, 30.0F, 30.0F, 9.0F, 1.5F, BlendMode.lerp(872415231, -855638017, var6));
      if (this.setting.following()) {
         float var7 = var4.textWidth(Fonts.MEDIUM, "Accent", 12.0F);
         var4.text(Fonts.MEDIUM, "Accent", this.swatchX - 12.0F, var3, 12.0F, Icons.TEXT_2, FontStyle.RIGHT, FontWeight.MIDDLE);
         var4.icon("link", this.swatchX - 12.0F - var7 - 10.0F, var3, 11.0F, Icons.TEXT_3);
      } else {
         var4.text(Fonts.MEDIUM, this.setting.hex(), this.swatchX - 12.0F, var3, 12.0F, Icons.TEXT_2, FontStyle.RIGHT, FontWeight.MIDDLE);
      }
   }

   public void renderPicker(DrawContext var1, float var2, float var3, float var4) {
      float var5 = this.open.target(this.expanded ? 1.0F : 0.0F).update(MotionStyle.PANEL.dt(var1.dt));
      if (!(var5 <= 0.01F)) {
         Canvas var6 = var1.canvas;
         var6.pushAlpha(Math.min(1.0F, var5 * 1.4F));
         this.fieldX = var2;
         this.fieldY = var3 + 4.0F;
         this.hueX = this.fieldX + 216.0F + 14.0F;
         this.alphaX = this.hueX + 16.0F + 14.0F;
         this.presetX = (this.setting.allowsAlpha() ? this.alphaX + 16.0F : this.hueX + 16.0F) + 28.0F;
         int var7 = hsvToRgb(this.hue, 1.0F, 1.0F);
         var6.roundRectCorners(this.fieldX, this.fieldY, 216.0F, 118.0F, 12.0F, -1, var7, -16777216, -16777216);
         var6.roundRectOutline(this.fieldX, this.fieldY, 216.0F, 118.0F, 12.0F, 1.0F, 536870911);
         float var8 = this.fieldX + this.sat * 216.0F;
         float var9 = this.fieldY + (1.0F - this.val) * 118.0F;
         this.handle(var6, var8, var9, 8.0F, this.setting.argb() | 0xFF000000);
         var6.pushClip(this.hueX, this.fieldY, 16.0F, 118.0F, 8.0F);
         float var10 = 19.666666F;

         for (int var11 = 0; var11 < 6; var11++) {
            var6.roundRectCorners(this.hueX, this.fieldY + var11 * var10, 16.0F, var10 + 0.5F, 0.0F, HUES[var11], HUES[var11], HUES[var11 + 1], HUES[var11 + 1]);
         }

         var6.popClip();
         this.handle(var6, this.hueX + 8.0F, this.fieldY + this.hue * 118.0F, 7.0F, var7);
         if (this.setting.allowsAlpha()) {
            this.checker(var6, this.alphaX, this.fieldY, 16.0F, 118.0F, 8.0F);
            int var15 = this.setting.argb() | 0xFF000000;
            var6.roundRectCorners(this.alphaX, this.fieldY, 16.0F, 118.0F, 8.0F, var15, var15, var15 & 16777215, var15 & 16777215);
            float var12 = BlendMode.alpha(this.setting.argb()) / 255.0F;
            this.handle(var6, this.alphaX + 8.0F, this.fieldY + (1.0F - var12) * 118.0F, 7.0F, this.setting.argb());
         }

         for (int var16 = 0; var16 < PRESETS.length; var16++) {
            float var17 = this.presetX + var16 % 4 * 32.0F;
            float var13 = this.fieldY + 8.0F + var16 / 4 * 32.0F;
            boolean var14 = var1.hover(PRESETS[var16], var17, var13, 22.0F, 22.0F, DrawContext.Cursor.HAND);
            var6.circle(var17 + 11.0F, var13 + 11.0F, 11.0F + (var14 ? 1.5F : 0.0F), PRESETS[var16]);
            if (!this.setting.following() && (this.setting.argb() | 0xFF000000) == PRESETS[var16]) {
               var6.ring(var17 + 11.0F, var13 + 11.0F, 14.5F, 1.5F, -419430401);
            }
         }

         if (this.setting.themed()) {
            this.accentChip(var1, var6);
         } else {
            var6.text(Fonts.MEDIUM, "Presets", this.presetX, this.fieldY + 118.0F - 6.0F, 10.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.BOTTOM);
         }

         var6.popAlpha();
      }
   }

   private void accentChip(DrawContext var1, Canvas var2) {
      float var3 = 118.0F;
      float var4 = this.presetX;
      float var5 = this.chipY();
      boolean var6 = var1.hover(this.chipKey, var4, var5, var3, 24.0F, DrawContext.Cursor.HAND);
      float var7 = this.follow.target(this.setting.following() ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var8 = 12.0F;
      var2.roundRect(var4, var5, var3, 24.0F, var8, BlendMode.lerp(318767103, 520093695, var6 ? 1.0F : 0.0F));
      if (var7 > 0.01F) {
         var2.pushAlpha(var7);
         var2.glow(var4, var5, var3, 24.0F, var8, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.3F));
         var2.roundRectGradient(var4, var5, var3, 24.0F, var8, 0.0F, Icons.INDIGO, Icons.PURPLE);
         var2.roundRectCorners(var4, var5, var3, 24.0F, var8, 721420287, 721420287, 16777215, 16777215);
         var2.popAlpha();
      }

      var2.roundRectOutline(var4, var5, var3, 24.0F, var8, 1.0F, BlendMode.lerp(486539263, 16777215, var7));
      int var9 = BlendMode.lerp(var6 ? Icons.TEXT : Icons.TEXT_2, Icons.TEXT, var7);
      float var10 = var2.textWidth(Fonts.SEMIBOLD, "Accent", 11.5F);
      float var11 = var4 + var3 / 2.0F - (var10 + 16.0F) / 2.0F;
      var2.circle(var11 + 5.0F, var5 + var8, 5.0F, Theme.follow(this.setting.defaultValue()) | 0xFF000000);
      var2.ring(var11 + 5.0F, var5 + var8, 5.0F, 1.0F, 1090519039);
      var2.text(Fonts.SEMIBOLD, "Accent", var11 + 16.0F, var5 + var8, 11.5F, var9, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private float chipY() {
      return this.fieldY + 8.0F + 64.0F + 6.0F;
   }

   private void handle(Canvas var1, float var2, float var3, float var4, int var5) {
      var1.shadow(var2 - var4, var3 - var4, var4 * 2.0F, var4 * 2.0F, var4, 6.0F, 0.0F, 0.0F, 1.0F, Integer.MIN_VALUE);
      var1.circle(var2, var3, var4, -1);
      var1.circle(var2, var3, var4 - 2.5F, var5 | 0xFF000000);
   }

   private void checker(Canvas var1, float var2, float var3, float var4, float var5, float var6) {
      var1.pushClip(var2, var3, var4, var5, var6);
      var1.rect(var2, var3, var4, var5, -1644818);
      float var7 = 5.0F;

      for (int var8 = 0; var8 * var7 < var5; var8++) {
         for (int var9 = 0; var9 * var7 < var4; var9++) {
            if ((var9 + var8 & 1) == 0) {
               var1.rect(var2 + var9 * var7, var3 + var8 * var7, var7, var7, -4671292);
            }
         }
      }

      var1.popClip();
   }

   public boolean mouseDown(float var1, float var2) {
      if (var1 >= this.swatchX && var1 <= this.swatchX + 30.0F && var2 >= this.swatchY && var2 <= this.swatchY + 30.0F) {
         this.expanded = !this.expanded;
         SoundRequest.play(this.expanded ? SoundEvent.EXPAND : SoundEvent.COLLAPSE);
         return true;
      } else if (this.expanded && !(this.open.value() < 0.5F)) {
         if (inside(var1, var2, this.fieldX, this.fieldY, 216.0F, 118.0F)) {
            this.dragging = 1;
         } else if (inside(var1, var2, this.hueX - 4.0F, this.fieldY, 24.0F, 118.0F)) {
            this.dragging = 2;
         } else {
            if (!this.setting.allowsAlpha() || !inside(var1, var2, this.alphaX - 4.0F, this.fieldY, 24.0F, 118.0F)) {
               if (this.setting.themed() && inside(var1, var2, this.presetX, this.chipY(), 118.0F, 24.0F)) {
                  if (!this.setting.following()) {
                     this.setting.follow();
                     this.syncFromSetting();
                     SoundRequest.play(SoundEvent.CLICK);
                  }

                  return true;
               }

               for (int var3 = 0; var3 < PRESETS.length; var3++) {
                  float var4 = this.presetX + var3 % 4 * 32.0F;
                  float var5 = this.fieldY + 8.0F + var3 / 4 * 32.0F;
                  if (inside(var1, var2, var4, var5, 22.0F, 22.0F)) {
                     int var6 = this.setting.allowsAlpha() ? this.setting.argb() & 0xFF000000 : -16777216;
                     this.setting.set(var6 | PRESETS[var3] & 16777215);
                     this.syncFromSetting();
                     SoundRequest.play(SoundEvent.CLICK);
                     return true;
                  }
               }

               return false;
            }

            this.dragging = 3;
         }

         this.mouseDrag(var1, var2);
         return true;
      } else {
         return false;
      }
   }

   public void mouseDrag(float var1, float var2) {
      switch (this.dragging) {
         case 1:
            this.sat = clamp01((var1 - this.fieldX) / 216.0F);
            this.val = 1.0F - clamp01((var2 - this.fieldY) / 118.0F);
            this.apply();
            break;
         case 2:
            this.hue = clamp01((var2 - this.fieldY) / 118.0F);
            this.apply();
            break;
         case 3:
            int var3 = Math.round((1.0F - clamp01((var2 - this.fieldY) / 118.0F)) * 255.0F);
            this.setting.set(var3 << 24 | this.setting.argb() & 16777215);
            this.lastColor = this.setting.argb();
      }
   }

   public void mouseUp() {
      this.dragging = 0;
   }

   public void collapse() {
      this.expanded = false;
   }

   private void apply() {
      int var1 = this.setting.argb() & 0xFF000000;
      this.setting.set(var1 | hsvToRgb(this.hue, this.sat, this.val) & 16777215);
      this.lastColor = this.setting.argb();
   }

   private static boolean inside(float var0, float var1, float var2, float var3, float var4, float var5) {
      return var0 >= var2 && var1 >= var3 && var0 <= var2 + var4 && var1 <= var3 + var5;
   }

   private static float clamp01(float var0) {
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   public static int hsvToRgb(float var0, float var1, float var2) {
      float var3 = var0 % 1.0F * 6.0F;
      int var4 = (int)Math.floor(var3);
      float var5 = var3 - var4;
      float var6 = var2 * (1.0F - var1);
      float var7 = var2 * (1.0F - var1 * var5);
      float var8 = var2 * (1.0F - var1 * (1.0F - var5));
      float var9;
      float var10;
      float var11;
      switch (var4 % 6) {
         case 0:
            var9 = var2;
            var10 = var8;
            var11 = var6;
            break;
         case 1:
            var9 = var7;
            var10 = var2;
            var11 = var6;
            break;
         case 2:
            var9 = var6;
            var10 = var2;
            var11 = var8;
            break;
         case 3:
            var9 = var6;
            var10 = var7;
            var11 = var2;
            break;
         case 4:
            var9 = var8;
            var10 = var6;
            var11 = var2;
            break;
         default:
            var9 = var2;
            var10 = var6;
            var11 = var7;
      }

      return BlendMode.argb(1.0F, var9, var10, var11);
   }

   public static float[] rgbToHsv(int var0) {
      float var1 = BlendMode.red(var0) / 255.0F;
      float var2 = BlendMode.green(var0) / 255.0F;
      float var3 = BlendMode.blue(var0) / 255.0F;
      float var4 = Math.max(var1, Math.max(var2, var3));
      float var5 = Math.min(var1, Math.min(var2, var3));
      float var6 = var4 - var5;
      float var7 = 0.0F;
      if (var6 > 1.0E-6F) {
         if (var4 == var1) {
            var7 = (var2 - var3) / var6 % 6.0F;
         } else if (var4 == var2) {
            var7 = (var3 - var1) / var6 + 2.0F;
         } else {
            var7 = (var1 - var2) / var6 + 4.0F;
         }

         var7 /= 6.0F;
         if (var7 < 0.0F) {
            var7++;
         }
      }

      return new float[]{var7, var4 <= 0.0F ? 0.0F : var6 / var4, var4};
   }
}
