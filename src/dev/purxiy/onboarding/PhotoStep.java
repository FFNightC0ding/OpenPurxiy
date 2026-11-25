package dev.purxiy.onboarding;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.core.ImageFiles;
import dev.purxiy.i18n.Translations;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;

final class PhotoStep extends OnboardingStep {
   private static final float HEIGHT = 226.0F;
   private static final float DIAMETER = 178.0F;
   private static final float TILT = -0.12F;
   private static final String[] HOW = new String[]{"Open your photo folder", "Put a picture of yourself in it", "It shows up here: drag to frame it"};
   private final boolean alone;
   private final StepNavigation folder = new StepNavigation();
   private final StepNavigation remove = new StepNavigation();
   private final StepNavigation reset = new StepNavigation();
   private final Animator hover = Animator.smooth(0.0F);
   private final Animator[] done = new Animator[]{Animator.snappy(0.0F), Animator.snappy(0.0F), Animator.snappy(0.0F)};
   private final Object photoKey = new Object();
   private float pcx;
   private float pcy;
   private float diameter;
   private boolean dragging;
   private boolean dragged;
   private float lastX;
   private float lastY;
   private boolean openedFolder;

   PhotoStep(WelcomeScreen var1, boolean var2) {
      super(
         var1,
         "Profile photo",
         var2 ? "Your profile photo" : "Put a face to your profile",
         var2
            ? "Put a picture in your photo folder and it shows up here. Drag to move it, scroll to zoom."
            : "Your profile shows this photo instead of your skin. Click the button, put a picture in the folder that opens, and it appears here."
      );
      this.alone = var2;
   }

   @Override
   String nextLabel() {
      return this.alone ? "Done" : (ImageFiles.present() ? "Continue" : "Skip for now");
   }

   @Override
   float height(float var1) {
      return 226.0F;
   }

   @Override
   void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      Canvas var6 = var1.canvas;
      float var7 = var1.time;
      boolean var8 = ImageFiles.present();
      float var9 = this.in(var7, 3);
      this.diameter = 178.0F;
      this.pcx = var2 + 20.0F + 89.0F;
      this.pcy = var3 + 113.0F;
      var6.push();
      var6.translate(0.0F, (1.0F - var9) * 14.0F);
      var6.pushAlpha(var9);
      this.photo(var1, var6, var8, var5 && var9 > 0.8F);
      var6.popAlpha();
      var6.pop();
      float var10 = this.pcx + 89.0F + 60.0F;
      float var11 = var2 + var4 - var10;
      boolean[] var12 = new boolean[]{this.openedFolder || var8, var8, var8};

      for (int var13 = 0; var13 < 3; var13++) {
         float var14 = this.in(var7, 4 + var13);
         float var15 = var3 + 26.0F + var13 * 40 + (1.0F - var14) * 8.0F;
         float var16 = this.done[var13].target(var12[var13] ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
         var6.pushAlpha(var14);
         this.badge(var6, var13, var10 + 11.0F, var15, var16);
         var6.text(
            Fonts.MEDIUM,
            FontMetrics.ellipsize(Fonts.MEDIUM, HOW[var13], 13.5F, var11 - 34.0F, 0.0F),
            var10 + 34.0F,
            var15,
            13.5F,
            BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var16),
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
         var6.popAlpha();
      }

      float var18 = this.in(var7, 7);
      var6.pushAlpha(var18);
      float var19 = var3 + 26.0F + 120.0F + 20.0F + (1.0F - var18) * 8.0F;
      float var20 = var10
         + this.folder
            .render(var1, var10, var19, 40.0F, "Open photo folder", "folder-open", (String)null, StepNavigation.AccentStyle.ACCENT, true, var5 && var18 > 0.8F)
         + 8.0F;
      if (var8) {
         this.remove.render(var1, var20, var19, 40.0F, "", "trash-2", (String)null, StepNavigation.AccentStyle.GLASS, true, var5 && var18 > 0.8F);
         tooltip(var6, "Remove photo", var20 + 20.0F, var19 - 30.0F, this.remove.hover());
         var20 += 46.0F;
         if (ImageFiles.framedByHand()) {
            this.reset.render(var1, var20, var19, 40.0F, "", "rotate-ccw", (String)null, StepNavigation.AccentStyle.GLASS, true, var5 && var18 > 0.8F);
            tooltip(var6, "Reset framing", var20 + 20.0F, var19 - 30.0F, this.reset.hover());
         }
      }

      String var22 = ImageFiles.problem();
      String var17 = var22 != null ? var22 : (var8 ? "Drag the photo to move it  ·  Scroll to zoom" : "PNG or JPG  ·  or drop a picture on this window");
      var6.text(
         Fonts.REGULAR,
         FontMetrics.ellipsize(Fonts.REGULAR, var17, 11.5F, var11 + 20.0F, 0.0F),
         var10,
         var19 + 38.0F,
         11.5F,
         var22 != null ? Icons.PINK : Icons.TEXT_3,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      var6.popAlpha();
   }

   private void photo(DrawContext var1, Canvas var2, boolean var3, boolean var4) {
      float var5 = var1.time;
      float var6 = this.diameter;
      float var7 = var6 / 2.0F;
      float var8 = this.pcx;
      float var9 = this.pcy;
      boolean var10 = var4 && var1.insideCircle(var8, var9, var7);
      if (var10 || this.dragging) {
         var1.claim(this.photoKey, var3 ? DrawContext.Cursor.MOVE : DrawContext.Cursor.HAND);
      }

      float var11 = this.hover.target(!var10 && !this.dragging ? 0.0F : 1.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var12 = var5 - ImageFiles.changedAt();
      float var13 = Easing.EMPHASIZED.apply(OnboardingState.clamp01(var12 / 0.6F));
      float var14 = var7 * 1.42F;
      float var15 = var14 * 0.24F;
      float var16 = var9 + var7 * 0.16F;
      var2.softCircle(var8, var9 + var7 * 0.16F, var7 * 0.92F, 34.0F, 1509949440);
      var2.glowCircle(var8, var9, var7, 30.0F + 10.0F * var11, BlendMode.withAlpha(Icons.VIOLET, 0.24F + 0.12F * var11));
      OnboardingState.ring(var2, var8, var16, var14, var15, -0.12F, 0.85F, false, 1.0F);
      OnboardingState.moons(var2, var8, var16, var14, var15, -0.12F, var5, 0.85F, false, 3);
      if (var3) {
         float var17 = (0.9F + 0.1F * var13) * (1.0F + 0.015F * var11);
         float var18 = var6 * var17;
         ImageFiles.draw(var2, var8 - var18 / 2.0F, var9 - var18 / 2.0F, var18, var18 / 2.0F, BlendMode.withAlpha(-1, var13));
         var2.pushClip(var8 - var18 / 2.0F, var9 - var18 / 2.0F, var18, var18, var18 / 2.0F);
         var2.circleRadial(var8 - var18 * 0.2F, var9 - var18 * 0.26F, var18 * 0.55F, 452984831, 16777215);
         var2.popClip();
         var2.arc(
            var8,
            var9,
            var18 / 2.0F + 0.5F,
            1.6F,
            -90.0F,
            360.0F,
            BlendMode.withAlpha(Icons.PURPLE, 0.8F + 0.2F * var11),
            BlendMode.withAlpha(Icons.MAGENTA, 0.55F),
            false
         );
         if (var12 < 0.9F) {
            float var19 = var12 / 0.9F;
            var2.ring(var8, var9, var7 + 4.0F + var19 * 34.0F, 2.0F * (1.0F - var19), BlendMode.withAlpha(Icons.PINK, 0.5F * (1.0F - var19)));
         }
      } else {
         var2.circle(var8, var9, var7, Theme.tint(-15593948));
         var2.circle(var8, var9, var7, BlendMode.lerp(234881023, 385875967, var11));
         var2.circleRadial(var8, var9 - var7 * 0.2F, var7, Theme.tint(347414516), 0);
         float var20 = MotionStyle.reduced() ? 0.0F : var5 * 12.0F;
         int var23 = BlendMode.withAlpha(Icons.TEXT_2, 0.35F + 0.35F * var11);

         for (int var24 = 0; var24 < 18; var24++) {
            var2.arc(var8, var9, var7 - 1.0F, 1.4F, var20 + var24 * 20, 11.0F, var23);
         }

         var2.icon("image-plus", var8, var9 - 10.0F, 30.0F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var11));
         var2.text(Fonts.MEDIUM, "No photo yet", var8, var9 + 22.0F, 11.5F, Icons.TEXT_3, FontStyle.CENTER, FontWeight.MIDDLE);
      }

      if (ImageFiles.busy()) {
         float var21 = var5 * 5.0F;
         var2.arc(var8, var9, var7 + 7.0F, 2.2F, (float)Math.toDegrees(var21), 100.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.0F), Icons.MAGENTA, true);
      }

      OnboardingState.ring(var2, var8, var16, var14, var15, -0.12F, 0.85F, true, 1.0F);
      OnboardingState.moons(var2, var8, var16, var14, var15, -0.12F, var5, 0.85F, true, 3);
      String var22 = SettingSection.PROFILE.displayName();
      Translations.pause();
      var2.text(
         Fonts.SEMIBOLD,
         FontMetrics.ellipsize(Fonts.SEMIBOLD, var22, 13.0F, var6 + 40.0F, 0.0F),
         var8,
         var9 + var7 + 30.0F,
         13.0F,
         Icons.TEXT_2,
         FontStyle.CENTER,
         FontWeight.MIDDLE
      );
      Translations.resume();
   }

   private static void tooltip(Canvas var0, String var1, float var2, float var3, float var4) {
      if (!(var4 <= 0.01F)) {
         float var5 = var0.textWidth(Fonts.MEDIUM, var1, 11.0F);
         float var6 = var5 + 18.0F;
         float var7 = 24.0F;
         var0.pushAlpha(var4);
         var0.shadow(var2 - var6 / 2.0F, var3 - var7 / 2.0F + 3.0F * (1.0F - var4), var6, var7, 8.0F, 14.0F, 0.0F, 0.0F, 4.0F, 1711276032);
         var0.roundRect(var2 - var6 / 2.0F, var3 - var7 / 2.0F + 3.0F * (1.0F - var4), var6, var7, 8.0F, Theme.tint(-233434583));
         var0.roundRectOutline(var2 - var6 / 2.0F, var3 - var7 / 2.0F + 3.0F * (1.0F - var4), var6, var7, 8.0F, 1.0F, 620756991);
         var0.text(Fonts.MEDIUM, var1, var2, var3 + 3.0F * (1.0F - var4), 11.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
         var0.popAlpha();
      }
   }

   private void badge(Canvas var1, int var2, float var3, float var4, float var5) {
      var1.ring(var3, var4, 11.0F, 1.4F, BlendMode.lerp(872415231, 16777215, var5));
      if (var5 < 0.999F) {
         var1.text(
            Fonts.SEMIBOLD,
            String.valueOf(var2 + 1),
            var3,
            var4 + 0.5F,
            11.0F,
            BlendMode.withAlpha(Icons.TEXT_2, 1.0F - var5),
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
      }

      if (var5 > 0.01F) {
         float var6 = 0.55F + 0.45F * var5;
         var1.pushAlpha(var5);
         var1.glowCircle(var3, var4, 11.0F * var6, 14.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.3F));
         var1.roundRectGradient(var3 - 11.0F * var6, var4 - 11.0F * var6, 22.0F * var6, 22.0F * var6, 11.0F * var6, 135.0F, Icons.INDIGO, Icons.MAGENTA);
         var1.icon("check", var3, var4 + 0.5F, 12.0F * var6, Icons.TEXT);
         var1.popAlpha();
      }
   }

   private boolean onPhoto(float var1, float var2) {
      float var3 = var1 - this.pcx;
      float var4 = var2 - this.pcy;
      return var3 * var3 + var4 * var4 <= this.diameter * this.diameter / 4.0F;
   }

   void openFolder() {
      ImageFiles.openFolder();
      this.openedFolder = true;
      SoundRequest.play(SoundEvent.EXPAND);
   }

   @Override
   boolean mouseDown(float var1, float var2, int var3) {
      if (this.folder.hit(var1, var2)) {
         this.openFolder();
         return true;
      } else if (this.remove.hit(var1, var2)) {
         ImageFiles.remove();
         SoundRequest.play(SoundEvent.TOGGLE_OFF);
         return true;
      } else if (this.reset.hit(var1, var2) && ImageFiles.framedByHand()) {
         ImageFiles.resetFrame();
         ImageFiles.saveFrame();
         SoundRequest.play(SoundEvent.CLICK);
         return true;
      } else if (var3 == 0 && this.onPhoto(var1, var2)) {
         if (ImageFiles.present()) {
            this.dragging = true;
            this.dragged = false;
            this.lastX = var1;
            this.lastY = var2;
         } else {
            this.openFolder();
         }

         return true;
      } else {
         return false;
      }
   }

   @Override
   void mouseDrag(float var1, float var2) {
      if (this.dragging) {
         ImageFiles.pan((var1 - this.lastX) / this.diameter, (var2 - this.lastY) / this.diameter);
         this.dragged = this.dragged | Math.abs(var1 - this.lastX) + Math.abs(var2 - this.lastY) > 0.5F;
         this.lastX = var1;
         this.lastY = var2;
      }
   }

   @Override
   void mouseUp(float var1, float var2) {
      if (this.dragging) {
         this.dragging = false;
         if (this.dragged) {
            ImageFiles.saveFrame();
         }
      }
   }

   @Override
   boolean scroll(float var1, float var2, double var3) {
      if (ImageFiles.present() && this.onPhoto(var1, var2)) {
         ImageFiles.zoom((float)Math.pow(1.12, var3), (var1 - this.pcx) / this.diameter, (var2 - this.pcy) / this.diameter);
         ImageFiles.saveFrame();
         SoundRequest.play(SoundEvent.SLIDER_TICK, 0.9F + 0.1F * ImageFiles.zoomLevel() / 4.0F);
         return true;
      } else {
         return false;
      }
   }

   @Override
   void leave() {
      this.dragging = false;
   }
}
