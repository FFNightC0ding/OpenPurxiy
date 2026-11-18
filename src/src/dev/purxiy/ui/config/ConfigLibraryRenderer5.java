// Sourced By DevFFNightmare
package dev.purxiy.ui.config;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.Theme;

final class ConfigLibraryRenderer5 {
   static final float HEIGHT = 68.0F;
   private static final float THUMB = 44.0F;
   private final ConfigLibraryRenderer7.TypeT look = new ConfigLibraryRenderer7.TypeT();
   private final ConfigLibraryRenderer8.TypeC save = new ConfigLibraryRenderer8.TypeC();
   private final ConfigLibraryRenderer8.TypeC revert = new ConfigLibraryRenderer8.TypeC();
   private final ConfigLibraryRenderer8.TypeC saveAsNew = new ConfigLibraryRenderer8.TypeC();
   private final Animator changed = Animator.smooth(0.0F);
   private final Animator hover = Animator.smooth(0.0F);
   private final Animator morph = Animator.smooth(1.0F);
   private final Object key = new Object();
   
   private ConfigLibraryFactory2 from;
   
   private ConfigLibraryFactory2 to;
   private boolean overName;
   private float x;
   private float y;
   private float w;
   private float savedAt = -100.0F;

   void saved(float var1) {
      this.savedAt = var1;
   }

   void render(DrawContext var1, float var2, float var3, float var4, float var5, boolean var6) {
      this.x = var2;
      this.y = var3;
      this.w = var4;
      Canvas var7 = var1.canvas;
      ConfigLibraryFactory2 var8 = ConfigLibraryTransform.active();
      int var9 = var8 == null ? 0 : ConfigLibraryTransform.unsavedChanges();
      if (var5 < 0.5F) {
         this.changed.snap(var9 > 0 ? 1.0F : 0.0F);
      }

      float var10 = this.changed.target(var9 > 0 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      if (this.to != var8) {
         this.from = this.to;
         this.to = var8;
         this.morph.snap(0.0F).target(1.0F);
      }

      float var11 = this.morph.update(MotionStyle.PANEL.dt(var1.dt));
      KeyLabel.surface(var7, var2, var3, var4, 68.0F, 20.0F, 0.25F, 0.12F + 0.38F * var10);
      float var12 = var3 + 34.0F;
      float var13 = var2 + 12.0F;
      this.overName = var6 && var8 != null && var1.hover(this.key, var2, var3, Math.min(var4 * 0.5F, 420.0F), 68.0F, DrawContext.TypeE.HAND);
      float var14 = this.hover.target(this.overName ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      if (var8 != null) {
         this.thumb(var1, var7, var8, var13, var12 - 22.0F, var14);
      } else {
         var7.roundRect(var13, var12 - 22.0F, 44.0F, 44.0F, 13.0F, 285212671);
         var7.roundRectOutline(var13, var12 - 22.0F, 44.0F, 44.0F, 13.0F, 1.0F, 486539263);
         var7.icon("layers", var13 + 22.0F, var12, 19.0F, Icons.TEXT_2);
      }

      float var15 = var13 + 44.0F + 14.0F;
      float var16 = Math.min(var4 * 0.42F, 380.0F);
      float var17 = Math.min(1.0F, var11 * 1.4F);
      if (this.from != this.to && var17 < 1.0F) {
         this.name(var7, this.from, var15, var12 - 9.0F - var17 * 6.0F, var16, 1.0F - var17);
      }

      this.name(var7, var8, var15, var12 - 9.0F + (1.0F - var17) * 6.0F, var16, var17);
      String var18;
      if (var8 == null) {
         var18 = "Not saved as a config yet";
      } else if (var9 > 0) {
         String var19 = Translations.tr("In use");
         var18 = var19 + "  ·  " + Translations.tr(var9 + (var9 == 1 ? " change not saved" : " changes not saved"));
      } else {
         String var26 = Translations.tr("In use");
         var18 = var26 + "  ·  " + Translations.tr(ConfigLibraryRenderer8.modules(var8.moduleCount()));
      }

      float var27 = var12 + 11.0F;
      if (var8 != null && var9 > 0) {
         var7.circle(var15 + 3.0F, var27, 2.8F, BlendMode.withAlpha(Theme.tint(-1783297), var10));
         var15 += 12.0F * var10;
      }

      var7.text(
         Fonts.REGULAR,
         FontMetrics.ellipsize(Fonts.REGULAR, var18, 11.5F, var16, 0.0F),
         var15,
         var27,
         11.5F,
         BlendMode.lerp(Icons.TEXT_2, BlendMode.lerp(Icons.TEXT_2, Theme.tint(-1783297), 0.7F), var10),
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      float var20 = var2 + var4 - 14.0F;
      if (var8 == null) {
         this.revert.shown = false;
         this.saveAsNew.shown = false;
         float var21 = ConfigLibraryRenderer8.TypeC.width(var7, "save", "Save as config", 36.0F);
         this.save.render(var1, var20 - var21, var12, 36.0F, "save", "Save as config", ConfigLibraryRenderer8.TypeI.PRIMARY, var6);
      } else {
         if (var10 < 0.99F) {
            var7.pushAlpha(1.0F - var10);
            boolean var28 = var1.time - this.savedAt < 2.2F;
            String var22 = var28 ? "Saved" : "Everything saved";
            float var23 = var7.textWidth(Fonts.MEDIUM, var22, 12.0F);
            var7.icon("circle-check", var20 - var23 - 12.0F, var12, 14.0F, BlendMode.withAlpha(Theme.tint(-1783297), 0.9F));
            var7.text(
               Fonts.MEDIUM,
               var22,
               var20,
               var12,
               12.0F,
               BlendMode.lerp(Icons.TEXT_2, Theme.tint(-1783297), var28 ? 1.0F : 0.55F),
               FontStyle.RIGHT,
               FontWeight.MIDDLE
            );
            var7.popAlpha();
         }

         if (var10 > 0.01F) {
            var7.pushAlpha(var10);
            var7.push();
            var7.translate((1.0F - var10) * 10.0F, 0.0F);
            boolean var29 = var6 && var10 > 0.6F;
            float var30 = ConfigLibraryRenderer8.TypeC.width(var7, "save", "Save", 36.0F);
            float var31 = var20 - var30;
            this.save.render(var1, var31, var12, 36.0F, "save", "Save", ConfigLibraryRenderer8.TypeI.PRIMARY, var29);
            float var24 = ConfigLibraryRenderer8.TypeC.width(var7, "rotate-ccw", "Undo changes", 36.0F);
            var31 -= 8.0F + var24;
            this.revert.render(var1, var31, var12, 36.0F, "rotate-ccw", "Undo changes", ConfigLibraryRenderer8.TypeI.QUIET, var29);
            float var25 = ConfigLibraryRenderer8.TypeC.width(var7, "copy-plus", "Save as new", 36.0F);
            var31 -= 4.0F + var25;
            if (var31 > var2 + 14.0F + 44.0F + 14.0F + var16 * 0.6F) {
               this.saveAsNew.render(var1, var31, var12, 36.0F, "copy-plus", "Save as new", ConfigLibraryRenderer8.TypeI.TEXT, var29);
            } else {
               this.saveAsNew.shown = false;
            }

            var7.pop();
            var7.popAlpha();
         } else {
            this.save.shown = false;
            this.revert.shown = false;
            this.saveAsNew.shown = false;
         }
      }
   }

   private void name(Canvas var1,  ConfigLibraryFactory2 var2, float var3, float var4, float var5, float var6) {
      if (!(var6 <= 0.01F)) {
         int var7 = BlendMode.withAlpha(Icons.TEXT, var6);
         if (var2 == null) {
            var1.text(Fonts.SEMIBOLD, "Your setup", var3, var4, 15.0F, var7, FontStyle.LEFT, FontWeight.MIDDLE);
         } else {
            Translations.pause();
            var1.text(
               Fonts.SEMIBOLD, FontMetrics.ellipsize(Fonts.SEMIBOLD, var2.name, 15.0F, var5, 0.0F), var3, var4, 15.0F, var7, FontStyle.LEFT, FontWeight.MIDDLE
            );
            Translations.resume();
         }
      }
   }

   private void thumb(DrawContext var1, Canvas var2, ConfigLibraryFactory2 var3, float var4, float var5, float var6) {
      float var7 = 13.0F;
      var2.glow(var4, var5, 44.0F, 44.0F, var7, 10.0F + 8.0F * var6, BlendMode.withAlpha(var3.accent(), 0.18F + 0.18F * var6));
      var2.pushClip(var4, var5, 44.0F, 44.0F, var7);
      float var8 = ConfigLibraryRenderer13.appear(var3, false);
      if (var8 < 1.0F) {
         this.look.time = var1.time;
         this.look.fill = true;
         this.look.hover = 0.3F + 0.4F * var6;
         this.look.starScale = 0.62F;
         ConfigLibraryRenderer7.draw(var2, var3, var4, var5, 44.0F, 44.0F, this.look);
      }

      if (var8 > 0.0F) {
         ConfigLibraryRenderer13.draw(
            var2, var3, var4, var5, 44.0F, 44.0F, var7, 1.04F + 0.04F * var6, 0.0F, 0.0F, false, ConfigLibraryRenderer13.Mode.NONE, 0.0F
         );
      }

      var2.popClip();
      var2.roundRectOutline(var4, var5, 44.0F, 44.0F, var7, 1.0F, BlendMode.withAlpha(BlendMode.lerp(var3.accent(), -1, 0.35F), 0.35F + 0.3F * var6));
   }

   float[] thumbRect() {
      return new float[]{this.x + 12.0F, this.y + 34.0F - 22.0F, 44.0F, 44.0F};
   }

   float[] saveButton() {
      return new float[]{this.save.x, this.save.y, this.save.w, this.save.h};
   }

   ConfigLibraryRenderer5.TypeE mouseDown(float var1, float var2) {
      if (var1 < this.x || var1 > this.x + this.w || var2 < this.y || var2 > this.y + 68.0F) {
         return ConfigLibraryRenderer5.TypeE.NONE;
      } else if (this.save.hit(var1, var2)) {
         this.save.press();
         return ConfigLibraryTransform.active() == null ? ConfigLibraryRenderer5.TypeE.SAVE_AS_NEW : ConfigLibraryRenderer5.TypeE.SAVE;
      } else if (this.revert.hit(var1, var2)) {
         this.revert.press();
         return ConfigLibraryRenderer5.TypeE.REVERT;
      } else if (this.saveAsNew.hit(var1, var2)) {
         this.saveAsNew.press();
         return ConfigLibraryRenderer5.TypeE.SAVE_AS_NEW;
      } else {
         return this.overName ? ConfigLibraryRenderer5.TypeE.OPEN : ConfigLibraryRenderer5.TypeE.NONE;
      }
   }

   static enum TypeE {
      NONE,
      SAVE,
      REVERT,
      SAVE_AS_NEW,
      OPEN;

      private static ConfigLibraryRenderer5.TypeE[] $values() {
         return new ConfigLibraryRenderer5.TypeE[]{NONE, SAVE, REVERT, SAVE_AS_NEW, OPEN};
      }
   }
}
