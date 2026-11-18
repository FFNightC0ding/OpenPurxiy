// Sourced By DevFFNightmare
package dev.purxiy.ui.clickgui;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.modules.Module;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.widget.Spacer;

final class ModuleRow {
   static final float HEIGHT = 112.0F;
   static final float RADIUS = 18.0F;
   private static final float PAD = 18.0F;
   private static final float SWITCH_W = 40.0F;
   private static final float SWITCH_H = 22.0F;
   private static final float BUTTON = 28.0F;
   final Module module;
   final Object key = new Object();
   final Object settingsKey = new Object();
   private final Animator hover = Animator.smooth(0.0F);
   private final Animator press = Animator.snappy(0.0F);
   private final Animator active;
   private final Animator buttonHover = Animator.smooth(0.0F);
   private final Spacer toggle;
   private float rippleStart = -10.0F;
   private float rippleX;
   private float rippleY;
   float x;
   float y;
   float w;
   float h;
   boolean pressed;

   ModuleRow(Module var1) {
      this.module = var1;
      this.active = Animator.snappy(var1.switchedOn() ? 1.0F : 0.0F);
      this.toggle = new Spacer(var1.switchedOn());
   }

   boolean hasSettingsButton() {
      return this.module.toggleable() && !this.module.settings().isEmpty();
   }

   boolean overSettingsButton(float var1, float var2) {
      if (!this.hasSettingsButton()) {
         return false;
      } else {
         float var3 = this.x + this.w - 18.0F - 40.0F - 10.0F - 28.0F;
         float var4 = this.y + 19.0F;
         return var1 >= var3 && var1 <= var3 + 28.0F && var2 >= var4 && var2 <= var4 + 28.0F;
      }
   }

   boolean overSwitch(float var1, float var2) {
      float var3 = this.x + this.w - 18.0F - 40.0F;
      float var4 = this.y + 22.0F;
      return this.module.toggleable() && var1 >= var3 - 6.0F && var1 <= var3 + 40.0F + 6.0F && var2 >= var4 - 6.0F && var2 <= var4 + 22.0F + 6.0F;
   }

   void ripple(float var1, float var2, float var3) {
      this.rippleStart = var1;
      this.rippleX = var2;
      this.rippleY = var3;
   }

   void render(DrawContext var1, float var2, float var3, float var4, float var5, float var6, boolean var7) {
      this.x = var2;
      this.y = var3;
      this.w = var4;
      this.h = 112.0F;
      Canvas var8 = var1.canvas;
      float var9 = var1.dt;
      boolean var10 = var7 && var1.hover(this.key, var2, var3, var4, 112.0F, DrawContext.TypeE.HAND);
      boolean var11 = var10 && this.overSettingsButton(var1.mouseX, var1.mouseY);
      if (var11) {
         var1.claim(this.settingsKey, DrawContext.TypeE.HAND);
      }

      float var12 = this.hover.target(var10 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var9));
      float var13 = this.press.target(this.pressed && var10 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var9));
      float var14 = this.active.target(this.module.switchedOn() ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var9));
      float var15 = this.buttonHover.target(var11 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var9));
      float var16 = Math.max(0.0F, Math.min(1.0F, var14));
      var8.push();
      var8.translate(var6 * (1.0F - var5), (1.0F - var5) * 18.0F - var12 * 2.5F);
      var8.scaleAround((0.97F + 0.03F * var5) * (1.0F - var13 * 0.018F), var2 + var4 / 2.0F, var3 + 56.0F);
      var8.pushAlpha(var5);
      KeyLabel.surface(var8, var2, var3, var4, 112.0F, 18.0F, 0.25F + var12 * 0.55F, Math.max(var16 * 0.5F, var12 * 0.3F));
      this.drawRipple(var8, var1.time);
      KeyLabel.iconTile(var8, var2 + 18.0F, var3 + 18.0F, 40.0F, 12.0F, this.module.icon(), var16, var12);
      if (this.module.toggleable()) {
         this.toggle.render(var1, var2 + var4 - 18.0F - 40.0F, var3 + 22.0F, 40.0F, 22.0F, this.module.switchedOn(), var10, this.pressed && var10);
      } else {
         var8.icon("chevron-right", var2 + var4 - 18.0F - 8.0F, var3 + 33.0F, 16.0F, BlendMode.lerp(Icons.TEXT_3, Icons.TEXT, var12));
      }

      if (this.hasSettingsButton()) {
         float var17 = var2 + var4 - 18.0F - 40.0F - 10.0F - 28.0F;
         float var18 = var3 + 19.0F;
         var8.circle(var17 + 14.0F, var18 + 14.0F, 14.0F, BlendMode.withAlpha(-1, 0.04F + 0.08F * var15 + 0.03F * var12));
         var8.icon("sliders-horizontal", var17 + 14.0F, var18 + 14.0F, 14.0F, BlendMode.lerp(Icons.TEXT_3, Icons.TEXT, Math.max(var15, var12 * 0.5F)));
      }

      float var20 = var4 - 36.0F;
      float var21 = 0.0F;
      if (this.module.key() != -1) {
         String var19 = KeyLabel.keyName(this.module.key());
         var21 = var8.textWidth(Fonts.SEMIBOLD, var19, 10.0F) + 12.0F;
         KeyLabel.keyCap(var8, var19, var2 + var4 - 18.0F - var21, var3 + 112.0F - 26.0F, 10.0F, Icons.TEXT_2);
         var21 += 10.0F;
      }

      var8.text(Fonts.SEMIBOLD, this.module.name(), var2 + 18.0F, var3 + 66.0F, 14.5F, Icons.TEXT);
      var8.text(
         Fonts.REGULAR,
         FontMetrics.ellipsize(Fonts.REGULAR, this.module.description(), 11.5F, var20 - var21, 0.0F),
         var2 + 18.0F,
         var3 + 86.0F,
         11.5F,
         BlendMode.lerp(Icons.TEXT_2, Theme.tint(-3159830), var12 * 0.5F)
      );
      var8.popAlpha();
      var8.pop();
   }

   private void drawRipple(Canvas var1, float var2) {
      float var3 = (var2 - this.rippleStart) * MotionStyle.TOGGLE.speed();
      if (!(var3 < 0.0F) && !(var3 > 0.7F)) {
         float var4 = var3 / 0.7F;
         float var5 = 1.0F - (1.0F - var4) * (1.0F - var4) * (1.0F - var4);
         float var6 = 20.0F + var5 * Math.max(this.w, 112.0F) * 1.1F;
         int var7 = this.module.switchedOn() ? Icons.MAGENTA : Icons.INDIGO;
         var1.pushClip(this.x, this.y, this.w, 112.0F, 18.0F);
         var1.glowCircle(this.rippleX, this.rippleY, var6 * 0.5F, var6, BlendMode.withAlpha(var7, 0.3F * (1.0F - var4) * (1.0F - var4)));
         var1.popClip();
      }
   }
}
