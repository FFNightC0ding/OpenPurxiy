// Sourced By DevFFNightmare
package dev.purxiy.ui.clickgui;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.modules.Category;
import dev.purxiy.modules.Module;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class LayoutEditor {
   static final float TOP = 150.0F;
   private static final float GAP = 16.0F;
   private static final float MIN_CARD = 240.0F;
   private static final float ENTER = 0.42F;
   private static final float EXIT = 0.16F;
   private final Map<Module, ModuleRow> cards = new HashMap<>();
   private final List<ModuleRow> current = new ArrayList<>();
   private final List<ModuleRow> outgoing = new ArrayList<>();
   private final float[][] outgoingRects = new float[64][2];
   
   private Category category;
   private float enterTime;
   private float exitTime = -10.0F;
   private float enterDelay;
   private int direction;
   private final Animator scroll = Animator.smooth(0.0F);
   private float scrollTarget;
   private float maxScroll;
   private float areaX;
   private float areaY;
   private float areaW;
   private float areaH;
   
   private ModuleRow pressed;
   
   private ModuleRow hidden;
   private final float[] editButton = new float[4];
   private final Animator editHover = Animator.smooth(0.0F);
   
   private ModuleRow target;

   
   Category category() {
      return this.category;
   }

   void show(Category var1, float var2, int var3, float var4) {
      if (var1 != this.category) {
         this.outgoing.clear();

         for (int var5 = 0; var5 < this.current.size() && var5 < this.outgoingRects.length; var5++) {
            ModuleRow var6 = this.current.get(var5);
            this.outgoing.add(var6);
            this.outgoingRects[var5][0] = var6.x;
            this.outgoingRects[var5][1] = var6.y;
         }

         this.exitTime = var2;
         this.category = var1;
         this.current.clear();

         for (Module var8 : SettingSection.of(var1)) {
            this.current.add(this.cards.computeIfAbsent(var8, ModuleRow::new));
         }

         this.enterTime = var2;
         this.enterDelay = var4;
         this.direction = var3;
         this.scroll.snap(0.0F);
         this.scrollTarget = 0.0F;
      }
   }

   void hide( ModuleRow var1) {
      this.hidden = var1;
   }

   
   ModuleRow card(Module var1) {
      return this.cards.get(var1);
   }

   void render(DrawContext var1, float var2, float var3, boolean var4) {
      if (this.category != null && !(var2 <= 0.01F)) {
         float var5 = var4 ? smooth((var2 - 0.45F) / 0.4F) : smooth(var2 * 1.6F - 0.3F);
         Canvas var6 = var1.canvas;
         float var7 = var6.width();
         float var8 = var6.height();
         float var9 = Math.max(32.0F, Math.min(72.0F, var7 * 0.045F));
         this.areaX = var9;
         this.areaW = var7 - var9 * 2.0F;
         this.areaY = 150.0F;
         this.areaH = var8 - 150.0F - 46.0F;
         float var10 = smooth((var2 - 0.5F) / 0.5F) * var3;
         this.editButton[2] = 0.0F;
         if (var10 > 0.01F && this.category != Category.PROFILE && this.category != Category.CONFIGS) {
            this.header(var1, var6, var9, var7, var10);
            String var11 = this.category == Category.CLIENT
               ? "Click a page to open  ·  Right-click a module for settings  ·  Esc for the orbit"
               : "Click to toggle  ·  Right-click for settings  ·  Esc for the orbit";
            var6.text(Fonts.MEDIUM, var11, var7 / 2.0F, var8 - 24.0F, 10.5F, BlendMode.withAlpha(Icons.TEXT_3, var10), FontStyle.CENTER, FontWeight.MIDDLE);
         }

         int var26 = Math.max(2, Math.min(5, (int)((this.areaW + 16.0F) / 256.0F)));
         float var12 = (this.areaW - 16.0F * (var26 - 1)) / var26;
         int var13 = (this.current.size() + var26 - 1) / var26;
         float var14 = var13 * 128.0F - 16.0F + 12.0F;
         this.maxScroll = Math.max(0.0F, var14 - this.areaH);
         this.scrollTarget = Math.max(0.0F, Math.min(this.maxScroll, this.scrollTarget));
         float var15 = this.scroll.target(this.scrollTarget).update(MotionStyle.MENU.dt(var1.dt));
         var6.pushClip(0.0F, this.areaY - 14.0F, var7, this.areaH + 30.0F);
         float var16 = (var1.time - this.exitTime) * MotionStyle.MENU.speed() / 0.16F;
         if (var16 < 1.0F) {
            float var17 = 1.0F - Easing.CUBIC_OUT.apply(var16);

            for (int var18 = 0; var18 < this.outgoing.size(); var18++) {
               ModuleRow var19 = this.outgoing.get(var18);
               if (!this.current.contains(var19)) {
                  var6.push();
                  var6.translate(-this.direction * 26 * (1.0F - var17), 0.0F);
                  var19.render(var1, this.outgoingRects[var18][0], this.outgoingRects[var18][1], var19.w, var17 * var3, 0.0F, false);
                  var6.pop();
               }
            }
         } else {
            this.outgoing.clear();
         }

         float var27 = (var1.time - this.enterTime) * MotionStyle.CASCADE.speed() - this.enterDelay;

         for (int var28 = 0; var28 < this.current.size(); var28++) {
            ModuleRow var29 = this.current.get(var28);
            float var20 = this.areaX + var28 % var26 * (var12 + 16.0F);
            float var21 = this.areaY + var28 / var26 * 128.0F - var15;
            float var22 = MotionStyle.reduced() ? 0.0F : var28 * 0.028F;
            float var23 = Easing.EMPHASIZED.apply(Math.max(0.0F, Math.min(1.0F, (var27 - var22) / 0.42F))) * var3 * var5;
            float var24 = Math.min(var21 + 112.0F - this.areaY + 14.0F, this.areaY + this.areaH - var21);
            float var25 = smooth(var24 / 60.0F);
            if (var29 == this.hidden) {
               var29.x = var20;
               var29.y = var21;
               var29.w = var12;
            } else if (var23 * var25 <= 0.005F) {
               var29.x = var20;
               var29.y = var21;
               var29.w = var12;
            } else {
               var6.pushAlpha(var25);
               var29.render(var1, var20, var21, var12, var23, this.direction * 30, !var1.masked() && var25 > 0.6F && var2 > 0.95F);
               var6.popAlpha();
            }
         }

         var6.popClip();
         if (this.maxScroll > 1.0F) {
            this.scrollbar(var6, var15, var14, var3 * var2);
         }
      }
   }

   private void header(DrawContext var1, Canvas var2, float var3, float var4, float var5) {
      Category var6 = this.category;
      float var7 = (1.0F - var5) * 10.0F;
      var2.pushAlpha(var5);
      var2.text(Fonts.BOLD, var6.displayName, var3, 82.0F + var7, 28.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.TOP, -0.01F);
      var2.text(Fonts.REGULAR, var6.tagline, var3, 118.0F + var7, 13.0F, Icons.TEXT_2);
      if (var6 == Category.HUD) {
         this.editButton(var1, var2, var4 - var3, 104.0F + var7);
      }

      var2.popAlpha();
   }

   private void editButton(DrawContext var1, Canvas var2, float var3, float var4) {
      String var5 = "Edit layout";
      float var6 = 34.0F + var2.textWidth(Fonts.MEDIUM, var5, 12.0F) + 14.0F;
      float var7 = 30.0F;
      float var8 = var3 - var6;
      float var9 = var4 - var7 / 2.0F;
      this.editButton[0] = var8;
      this.editButton[1] = var9;
      this.editButton[2] = var6;
      this.editButton[3] = var7;
      boolean var10 = var1.hover(this.editButton, var8, var9, var6, var7, DrawContext.TypeE.HAND);
      float var11 = this.editHover.target(var10 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      if (var11 > 0.01F) {
         var2.glow(var8, var9, var6, var7, var7 / 2.0F, 16.0F, BlendMode.withAlpha(Icons.VIOLET, 0.35F * var11));
      }

      var2.roundRectGradient(
         var8,
         var9,
         var6,
         var7,
         var7 / 2.0F,
         0.0F,
         BlendMode.withAlpha(Icons.INDIGO, 0.75F + 0.25F * var11),
         BlendMode.withAlpha(Icons.PURPLE, 0.75F + 0.25F * var11)
      );
      var2.roundRectOutline(var8, var9, var6, var7, var7 / 2.0F, 1.0F, 872415231);
      var2.icon("move", var8 + 14.0F + 6.5F, var4, 13.0F, Icons.TEXT);
      var2.text(Fonts.MEDIUM, var5, var8 + 14.0F + 13.0F + 7.0F, var4, 12.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private void scrollbar(Canvas var1, float var2, float var3, float var4) {
      float var5 = this.areaH - 20.0F;
      float var6 = Math.max(36.0F, var5 * this.areaH / var3);
      float var7 = this.areaY + 10.0F + (var5 - var6) * (var2 / this.maxScroll);
      float var8 = this.areaX + this.areaW + Math.min(20.0F, this.areaX * 0.4F);
      var1.roundRect(var8 - 1.5F, var7, 3.0F, var6, 1.5F, BlendMode.withAlpha(-1, 0.18F * var4));
   }

   
   ModuleRow target() {
      return this.target;
   }

   
   private ModuleRow cardAt(float var1, float var2) {
      if (!(var2 < this.areaY - 6.0F) && !(var2 > this.areaY + this.areaH)) {
         for (ModuleRow var4 : this.current) {
            if (var1 >= var4.x && var1 <= var4.x + var4.w && var2 >= var4.y && var2 <= var4.y + var4.h) {
               return var4;
            }
         }

         return null;
      } else {
         return null;
      }
   }

   LayoutEditor.TypeD mouseDown(float var1, float var2, int var3) {
      float[] var4 = this.editButton;
      if (var4[2] > 0.0F && var1 >= var4[0] && var1 <= var4[0] + var4[2] && var2 >= var4[1] && var2 <= var4[1] + var4[3]) {
         return LayoutEditor.TypeD.EDIT_HUD;
      } else {
         ModuleRow var5 = this.cardAt(var1, var2);
         this.target = var5;
         if (var5 == null) {
            return LayoutEditor.TypeD.NONE;
         } else if (var3 != 1 && var5.module.toggleable() && !var5.overSettingsButton(var1, var2)) {
            this.pressed = var5;
            var5.pressed = true;
            return LayoutEditor.TypeD.CONSUMED;
         } else {
            return LayoutEditor.TypeD.OPEN_PANEL;
         }
      }
   }

   void mouseUp(float var1, float var2, float var3) {
      ModuleRow var4 = this.pressed;
      this.pressed = null;
      if (var4 != null) {
         var4.pressed = false;
         if (var1 >= var4.x && var1 <= var4.x + var4.w && var2 >= var4.y && var2 <= var4.y + var4.h) {
            var4.module.toggle();
            var4.ripple(var3, var1, var2);
            SoundRequest.play(var4.module.switchedOn() ? SoundEvent.TOGGLE_ON : SoundEvent.TOGGLE_OFF);
         }
      }
   }

   boolean scroll(double var1) {
      if (this.maxScroll <= 0.0F) {
         return false;
      } else {
         this.scrollTarget = (float)Math.max(0.0, Math.min((double)this.maxScroll, this.scrollTarget - var1 * 64.0));
         return true;
      }
   }

   private static float smooth(float var0) {
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   static enum TypeD {
      NONE,
      OPEN_PANEL,
      EDIT_HUD,
      CONSUMED;

      private static LayoutEditor.TypeD[] $values() {
         return new LayoutEditor.TypeD[]{NONE, OPEN_PANEL, EDIT_HUD, CONSUMED};
      }
   }
}
