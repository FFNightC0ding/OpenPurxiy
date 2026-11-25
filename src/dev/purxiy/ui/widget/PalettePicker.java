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
import java.util.Locale;

public final class PalettePicker {
   public static final float HEIGHT = 74.0F;
   private static final float FIELD_W = 216.0F;
   private static final float FIELD_H = 112.0F;
   private static final float STRIP_W = 16.0F;
   private static final float GAP = 14.0F;
   private static final PalettePicker.PaletteOption[] SWATCHES = new PalettePicker.PaletteOption[]{
      new PalettePicker.PaletteOption("Lilac", -4944641),
      new PalettePicker.PaletteOption("Rose", -28744),
      new PalettePicker.PaletteOption("Crimson", -50340),
      new PalettePicker.PaletteOption("Sunset", -24742),
      new PalettePicker.PaletteOption("Gold", -11930),
      new PalettePicker.PaletteOption("Mint", -9444427),
      new PalettePicker.PaletteOption("Aqua", -11544344),
      new PalettePicker.PaletteOption("Ocean", -11563521),
      new PalettePicker.PaletteOption("Ice", -2234625),
      new PalettePicker.PaletteOption("Mono", -1)
   };
   private static final int[] HUES = new int[]{-65536, -256, -16711936, -16711681, -16776961, -65281, -65536};
   private final ColorSetting setting;
   private final Animator[] hover = new Animator[SWATCHES.length + 1];
   private final Animator[] chosen = new Animator[SWATCHES.length + 1];
   private final Animator open = Animator.smooth(0.0F);
   private boolean expanded;
   private int dragging;
   private float hue;
   private float sat;
   private float val;
   private int lastColor;
   private float rowX;
   private float rowY;
   private float slot;
   private float fieldX;
   private float fieldY;
   private float hueX;

   public PalettePicker(ColorSetting var1) {
      this.setting = var1;

      for (int var2 = 0; var2 < this.hover.length; var2++) {
         this.hover[var2] = Animator.smooth(0.0F);
         this.chosen[var2] = Animator.snappy(var2 == this.selected() ? 1.0F : 0.0F);
      }

      this.syncFromSetting();
   }

   public float extraHeight() {
      return 138.0F * this.open.value();
   }

   public String label() {
      int var1 = this.selected();
      return var1 < SWATCHES.length ? SWATCHES[var1].name : String.format(Locale.ROOT, "#%06X", this.setting.argb() & 16777215);
   }

   private int selected() {
      int var1 = this.setting.argb() | 0xFF000000;

      for (int var2 = 0; var2 < SWATCHES.length; var2++) {
         if (SWATCHES[var2].color == var1) {
            return var2;
         }
      }

      return SWATCHES.length;
   }

   private void syncFromSetting() {
      int var1 = this.setting.argb();
      float[] var2 = ColorPicker.rgbToHsv(var1);
      if (var2[1] > 0.001F && var2[2] > 0.001F) {
         this.hue = var2[0];
      }

      this.sat = var2[1];
      this.val = var2[2];
      this.lastColor = var1;
   }

   public void render(DrawContext var1, float var2, float var3, float var4) {
      if (this.setting.argb() != this.lastColor) {
         this.syncFromSetting();
      }

      Canvas var5 = var1.canvas;
      int var6 = SWATCHES.length + 1;
      int var7 = this.selected();
      this.rowX = var2;
      this.rowY = var3;
      this.slot = var4 / var6;
      float var8 = Math.min(15.0F, this.slot * 0.3F);
      float var9 = var3 + 6.0F + var8;

      for (int var10 = 0; var10 < var6; var10++) {
         float var11 = var2 + this.slot * (var10 + 0.5F);
         boolean var12 = var1.hover(this.hover[var10], var11 - this.slot / 2.0F, var3, this.slot, 66.0F, DrawContext.Cursor.HAND);
         float var13 = this.hover[var10].target(var12 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         float var14 = this.chosen[var10].target(var10 == var7 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
         float var15 = 1.6F * var13;
         float var16 = var8 * (1.0F + 0.07F * var13);
         if (var10 < SWATCHES.length) {
            orb(var5, var11, var9 - var15, var16, SWATCHES[var10].color, var13, var14);
         } else {
            this.own(var5, var11, var9 - var15, var16, var13, var14, var7 == SWATCHES.length);
         }

         String var17 = var10 < SWATCHES.length ? SWATCHES[var10].name : "Custom";
         int var18 = BlendMode.lerp(BlendMode.lerp(Icons.TEXT_3, Icons.TEXT_2, var13), Icons.TEXT, var14);
         var5.text(var14 > 0.5F ? Fonts.SEMIBOLD : Fonts.MEDIUM, var17, var11, var9 + var8 + 15.0F, 10.5F, var18, FontStyle.CENTER, FontWeight.MIDDLE);
      }

      this.renderField(var1, var2, var3 + 74.0F, var4);
   }

   static void orb(Canvas var0, float var1, float var2, float var3, int var4, float var5, float var6) {
      var0.softCircle(var1, var2 + var3 * 0.45F, var3 * 0.75F, var3 * 0.9F, BlendMode.withAlpha(-16777216, 0.35F));
      if (var6 > 0.01F || var5 > 0.01F) {
         var0.glowCircle(var1, var2, var3 * 0.8F, var3 * 1.5F, BlendMode.withAlpha(var4, 0.42F * Math.max(var6, var5 * 0.5F)));
      }

      var0.circleRadial(var1, var2, var3, BlendMode.shade(var4, 0.16F), BlendMode.shade(var4, -0.22F));
      var0.softCircle(var1 - var3 * 0.3F, var2 - var3 * 0.36F, var3 * 0.22F, var3 * 0.42F, BlendMode.withAlpha(-1, 0.42F));
      var0.ring(var1, var2, var3 - 0.5F, 1.0F, 620756991);
      selection(var0, var1, var2, var3, var6);
   }

   private void own(Canvas var1, float var2, float var3, float var4, float var5, float var6, boolean var7) {
      var1.softCircle(var2, var3 + var4 * 0.45F, var4 * 0.75F, var4 * 0.9F, BlendMode.withAlpha(-16777216, 0.35F));
      float var8 = Math.max(3.0F, var4 * 0.28F);
      float var9 = var4 - var8 / 2.0F;

      for (int var10 = 0; var10 < 12; var10++) {
         int var11 = ColorPicker.hsvToRgb(var10 / 12.0F, 0.72F, 1.0F);
         int var12 = ColorPicker.hsvToRgb((var10 + 1) / 12.0F, 0.72F, 1.0F);
         var1.arc(var2, var3, var9, var8, -90 + var10 * 30 - 0.6F, 31.2F, var11, var12, false);
      }

      float var13 = var4 - var8 - 1.5F;
      if (var7) {
         int var14 = this.setting.argb() | 0xFF000000;
         var1.circleRadial(var2, var3, var13, BlendMode.shade(var14, 0.16F), BlendMode.shade(var14, -0.22F));
      } else {
         var1.circle(var2, var3, var13, Theme.tint(-15593950));
         var1.icon(this.expanded ? "chevron-up" : "plus", var2, var3, var13 * 1.15F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var5));
      }

      selection(var1, var2, var3, var4, var6);
   }

   static void selection(Canvas var0, float var1, float var2, float var3, float var4) {
      if (var4 > 0.01F) {
         var0.ring(var1, var2, var3 + 2.5F + 1.5F * var4, 1.5F, BlendMode.withAlpha(-1, 0.9F * var4));
      }
   }

   private void renderField(DrawContext var1, float var2, float var3, float var4) {
      float var5 = this.open.target(this.expanded ? 1.0F : 0.0F).update(MotionStyle.PANEL.dt(var1.dt));
      if (!(var5 <= 0.01F)) {
         Canvas var6 = var1.canvas;
         var6.pushAlpha(Math.min(1.0F, var5 * 1.4F));
         this.fieldX = var2;
         this.fieldY = var3 + 6.0F;
         this.hueX = this.fieldX + 216.0F + 14.0F;
         int var7 = ColorPicker.hsvToRgb(this.hue, 1.0F, 1.0F);
         var6.roundRectCorners(this.fieldX, this.fieldY, 216.0F, 112.0F, 12.0F, -1, var7, -16777216, -16777216);
         var6.roundRectOutline(this.fieldX, this.fieldY, 216.0F, 112.0F, 12.0F, 1.0F, 536870911);
         handle(var6, this.fieldX + this.sat * 216.0F, this.fieldY + (1.0F - this.val) * 112.0F, 8.0F, this.setting.argb());
         var6.pushClip(this.hueX, this.fieldY, 16.0F, 112.0F, 8.0F);
         float var8 = 18.666666F;

         for (int var9 = 0; var9 < 6; var9++) {
            var6.roundRectCorners(this.hueX, this.fieldY + var9 * var8, 16.0F, var8 + 0.5F, 0.0F, HUES[var9], HUES[var9], HUES[var9 + 1], HUES[var9 + 1]);
         }

         var6.popClip();
         handle(var6, this.hueX + 8.0F, this.fieldY + this.hue * 112.0F, 7.0F, var7);
         float var17 = this.hueX + 16.0F + 28.0F;
         float var10 = Math.max(0.0F, var2 + var4 - var17);
         if (var10 > 60.0F) {
            var6.text(Fonts.SEMIBOLD, "YOUR PALETTE", var17, this.fieldY + 6.0F, 9.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE, 0.12F);
            int[] var11 = new int[]{Icons.INDIGO, Icons.VIOLET, Icons.PURPLE, Icons.MAGENTA, Icons.PINK};
            float var12 = var10 / var11.length;

            for (int var13 = 0; var13 < var11.length; var13++) {
               float var14 = var17 + var13 * var12;
               float var15 = var13 == 0 ? 9.0F : 0.0F;
               float var16 = var13 == var11.length - 1 ? 9.0F : 0.0F;
               var6.roundRect(var14, this.fieldY + 18.0F, var12 + (var13 < var11.length - 1 ? 0.5F : 0.0F), 30.0F, var15, var16, var16, var15, var11[var13]);
            }

            var6.roundRectGradient(var17, this.fieldY + 56.0F, var10, 10.0F, 5.0F, 0.0F, Icons.INDIGO, Icons.MAGENTA);
            var6.glow(var17, this.fieldY + 56.0F, var10, 10.0F, 5.0F, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.3F));
            int var18 = Theme.accent();
            var6.circle(var17 + 6.0F, this.fieldY + 112.0F - 18.0F, 6.0F, var18);
            var6.ring(var17 + 6.0F, this.fieldY + 112.0F - 18.0F, 6.0F, 1.0F, 822083583);
            var6.text(
               Fonts.SEMIBOLD,
               String.format(Locale.ROOT, "#%06X", this.setting.argb() & 16777215),
               var17 + 18.0F,
               this.fieldY + 112.0F - 18.0F,
               12.5F,
               Icons.TEXT,
               FontStyle.LEFT,
               FontWeight.MIDDLE,
               0.02F
            );
         }

         var6.popAlpha();
      }
   }

   static void handle(Canvas var0, float var1, float var2, float var3, int var4) {
      var0.shadow(var1 - var3, var2 - var3, var3 * 2.0F, var3 * 2.0F, var3, 6.0F, 0.0F, 0.0F, 1.0F, Integer.MIN_VALUE);
      var0.circle(var1, var2, var3, -1);
      var0.circle(var1, var2, var3 - 2.5F, var4 | 0xFF000000);
   }

   public boolean mouseDown(float var1, float var2) {
      if (var2 >= this.rowY && var2 < this.rowY + 74.0F - 8.0F && var1 >= this.rowX && var1 < this.rowX + this.slot * (SWATCHES.length + 1)) {
         int var3 = (int)((var1 - this.rowX) / this.slot);
         if (var3 < SWATCHES.length) {
            if ((this.setting.argb() | 0xFF000000) != SWATCHES[var3].color) {
               this.setting.set(SWATCHES[var3].color);
               this.syncFromSetting();
               SoundRequest.play(SoundEvent.SELECT);
            }
         } else {
            this.expanded = !this.expanded;
            SoundRequest.play(this.expanded ? SoundEvent.EXPAND : SoundEvent.COLLAPSE);
         }

         return true;
      } else if (this.expanded && !(this.open.value() < 0.5F)) {
         if (inside(var1, var2, this.fieldX, this.fieldY, 216.0F, 112.0F)) {
            this.dragging = 1;
         } else {
            if (!inside(var1, var2, this.hueX - 4.0F, this.fieldY, 24.0F, 112.0F)) {
               return false;
            }

            this.dragging = 2;
         }

         this.mouseDrag(var1, var2);
         return true;
      } else {
         return false;
      }
   }

   public void mouseDrag(float var1, float var2) {
      if (this.dragging == 1) {
         this.sat = clamp01((var1 - this.fieldX) / 216.0F);
         this.val = 1.0F - clamp01((var2 - this.fieldY) / 112.0F);
      } else {
         if (this.dragging != 2) {
            return;
         }

         this.hue = clamp01((var2 - this.fieldY) / 112.0F);
      }

      this.setting.set(ColorPicker.hsvToRgb(this.hue, this.sat, this.val));
      this.lastColor = this.setting.argb();
   }

   public void mouseUp() {
      this.dragging = 0;
   }

   private static boolean inside(float var0, float var1, float var2, float var3, float var4, float var5) {
      return var0 >= var2 && var1 >= var3 && var0 <= var2 + var4 && var1 <= var3 + var5;
   }

   private static float clamp01(float var0) {
      return var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
   }

   private record PaletteOption(String name, int color) {
   }
}
