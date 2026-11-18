// Sourced By DevFFNightmare
package dev.purxiy.profile;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.core.ImageFiles;
import dev.purxiy.i18n.Translations;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.onboarding.WelcomeScreen;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.texture.TextureLoader;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import net.minecraft.class_310;
import net.minecraft.class_8685;

final class ProfileRenderer5 {
   private static final float TILT = -0.07F;
   
   private static TextureLoader mark;
   private final ProfileRenderer10.TypeX pose = new ProfileRenderer10.TypeX();
   private final Animator headYaw = Animator.smooth(0.0F);
   private final Animator headPitch = Animator.smooth(0.0F);
   private final Animator hover = Animator.smooth(0.0F);
   private final Animator levelBar = Animator.smooth(0.0F);
   private final Object stageKey = new Object();
   private float spin = -0.42F;
   private float spinVelocity;
   private boolean dragging;
   private boolean dragMoved;
   private float dragX;
   private float dragLastX;
   private long dragLastTime;
   private float waveAt = -100.0F;
   private float builtAt = -100.0F;
   private float stageX;
   private float stageY;
   private float stageW;
   private float stageH;
   private float photoX;
   private float photoY;
   private float photoD;

   void enter(float var1) {
      this.builtAt = var1;
      this.levelBar.snap(0.0F);
   }

   void render(DrawContext var1, ProfileSupport6 var2, float var3, float var4, float var5, float var6, float var7, float var8, boolean var9) {
      Canvas var10 = var1.canvas;
      float var11 = var8 - this.builtAt;
      ProfileRenderer9.card(var10, var3, var4, var5, var6, 0.0F);
      float var12 = 20.0F;
      if (mark == null) {
         mark = TextureLoader.load("textures/brand/mark.png");
      }

      float var13 = var4 + 26.0F;
      float var14 = 13.0F;
      float var15 = var14 * mark.aspect();
      var10.logo(mark, var3 + var12, var13 - var14 / 2.0F, var15, var14, BlendMode.withAlpha(-1, 0.85F));
      ProfileRenderer9.label(var10, "PURXIY ID", var3 + var12 + var15 + 8.0F, var13, Icons.TEXT_3, FontStyle.LEFT);
      this.levelBadge(var10, var2, var3 + var5 - var12, var13);
      float var16 = 186.0F;
      this.stageX = var3 + 8.0F;
      this.stageY = var4 + 44.0F;
      this.stageW = var5 - 16.0F;
      this.stageH = Math.max(150.0F, var6 - 44.0F - var16);
      boolean var17;
      if (SettingSection.PROFILE.photoPortrait()) {
         var17 = var9 && this.photoD > 0.0F && var1.insideCircle(this.photoX, this.photoY, this.photoD / 2.0F);
         if (var17) {
            var1.claim(this.stageKey, DrawContext.TypeE.HAND);
         }
      } else {
         var17 = var9
            && var1.hover(this.stageKey, this.stageX, this.stageY, this.stageW, this.stageH, this.dragging ? DrawContext.TypeE.MOVE : DrawContext.TypeE.HAND);
      }

      float var18 = this.hover.target(!var17 && !this.dragging ? 0.0F : 1.0F).update(MotionStyle.HOVER.dt(var1.dt));
      this.stage(var1, var10, var8, var11, var18, var9);
      float var19 = this.stageY + this.stageH + 6.0F;
      String var20 = SettingSection.PROFILE.displayName();
      Translations.pause();
      var20 = FontMetrics.ellipsize(Fonts.SEMIBOLD, var20, 22.0F, var5 - var12 * 2.0F, 0.0F);
      float var21 = Easing.EMPHASIZED.apply(ProfileCurve.clamp01((var11 - 0.35F) / 0.6F));
      var10.pushAlpha(var21);
      var10.text(Fonts.SEMIBOLD, var20, var3 + var5 / 2.0F, var19 + (1.0F - var21) * 6.0F, 22.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.TOP, -0.01F);
      Translations.resume();
      this.archetype(var10, var2, var3 + var5 / 2.0F, var19 + 40.0F);
      var10.popAlpha();
      float var22 = var19 + 66.0F;
      this.level(var1, var10, var2, var3 + var12, var22, var5 - var12 * 2.0F, var11);
      float var23 = var22 + 42.0F;
      var10.rect(var3 + var12, var23, var5 - var12 * 2.0F, 1.0F, 285212671);
      this.facts(var10, var2, var3 + var12, var23 + 14.0F, var5 - var12 * 2.0F, var11);
   }

   private void levelBadge(Canvas var1, ProfileSupport6 var2, float var3, float var4) {
      String var5 = "LV " + var2.level;
      float var6 = var1.textWidth(Fonts.BOLD, var5, 10.0F, 0.06F);
      float var7 = var6 + 18.0F;
      float var8 = 20.0F;
      float var9 = var3 - var7;
      var1.glow(var9, var4 - var8 / 2.0F, var7, var8, var8 / 2.0F, 10.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.28F));
      var1.roundRectGradient(var9, var4 - var8 / 2.0F, var7, var8, var8 / 2.0F, 0.0F, Icons.INDIGO, Icons.MAGENTA);
      var1.roundRectCorners(var9, var4 - var8 / 2.0F, var7, var8, var8 / 2.0F, 872415231, 587202559, 16777215, 16777215);
      var1.text(Fonts.BOLD, var5, var9 + var7 / 2.0F, var4, 10.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE, 0.06F);
   }

   private void stage(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6) {
      if (SettingSection.PROFILE.photoPortrait()) {
         this.photoStage(var2, var3, var4, var5);
      } else {
         boolean var7 = MotionStyle.reduced();
         float var8 = this.stageX + this.stageW / 2.0F;
         float var9 = Math.min(this.stageH * 0.8F, 270.0F);
         float var10 = var9 / 32.5F;
         float var11 = Math.max(24.0F, this.stageH * 0.13F);
         float var12 = this.stageY + this.stageH - var11;
         float var13 = Math.min(this.stageW * 0.36F, var9 * 0.46F);
         float var14 = var13 * 0.2F;
         float var15 = Easing.EMPHASIZED.apply(ProfileCurve.clamp01(var4 / 0.7F));
         var2.pushClip(this.stageX, this.stageY - 4.0F, this.stageW, this.stageH + 8.0F, 14.0F);
         this.sky(var2, var3, var8, var12 - var9 * 0.55F, var9, var15);
         this.ringHalf(var2, var8, var12, var13, var14, var15, false);
         var2.push();
         var2.translate(var8, var12);
         var2.scale(1.0F, 0.22F);
         var2.glowCircle(0.0F, 0.0F, var13 * 0.8F, var13 * 0.9F, BlendMode.withAlpha(Icons.PURPLE, (0.16F + 0.1F * var5) * var15));
         var2.softCircle(0.0F, 0.0F, var13 * 0.34F, var13 * 0.5F, BlendMode.withAlpha(-16777216, 0.55F * var15));
         var2.pop();
         this.sparks(var2, var8, var12, var13, var14, var3, var15, false);
         this.pose(var1, var3, var8, var12 - var9 * 0.86F, var6);
         class_8685 var16 = ProfileSupport5.skin();
         boolean var17 = var16 != null
            && ProfileRenderer10.draw(
               var2,
               var16,
               this.pose,
               this.stageX,
               this.stageY,
               this.stageW,
               this.stageH,
               var10,
               var11,
               ProfileCurve.clamp01((var4 - 0.15F) / 1.25F),
               var5,
               var3
            );
         if (!var17) {
            var2.pushAlpha(var15 * 0.5F);
            var2.roundRect(var8 - 4.0F * var10, var12 - 32.0F * var10, 8.0F * var10, 8.0F * var10, 3.0F, 587202559);
            var2.roundRect(var8 - 4.0F * var10, var12 - 24.0F * var10, 8.0F * var10, 24.0F * var10, 3.0F, 385875967);
            var2.popAlpha();
         }

         this.ringHalf(var2, var8, var12, var13, var14, var15, true);
         this.sparks(var2, var8, var12, var13, var14, var3, var15, true);
         var2.popClip();
         float var18 = var5 * (this.dragging ? 0 : 1) * var15;
         if (var18 > 0.01F) {
            var2.text(
               Fonts.MEDIUM,
               "Drag to turn  ·  Click to wave",
               var8,
               this.stageY + this.stageH - 8.0F,
               10.0F,
               BlendMode.withAlpha(Icons.TEXT_3, var18),
               FontStyle.CENTER,
               FontWeight.MIDDLE
            );
         }
      }
   }

   private void sky(Canvas var1, float var2, float var3, float var4, float var5, float var6) {
      boolean var7 = MotionStyle.reduced();
      float var8 = var7 ? 0.9F : 0.85F + 0.15F * (float)Math.sin(var2 * 0.9F);
      var1.glowCircle(var3, var4, var5 * 0.22F, var5 * 0.62F, BlendMode.withAlpha(Icons.VIOLET, 0.2F * var6 * var8));
      var1.glowCircle(var3 + var5 * 0.12F, var4 + var5 * 0.35F, var5 * 0.14F, var5 * 0.4F, BlendMode.withAlpha(Icons.MAGENTA, 0.11F * var6));

      for (int var9 = 0; var9 < 18; var9++) {
         float var10 = this.stageX + 14.0F + hash(var9 * 3) * (this.stageW - 28.0F);
         float var11 = this.stageY + 6.0F + hash(var9 * 3 + 1) * this.stageH * 0.8F;
         float var12 = var7 ? 0.6F : 0.5F + 0.5F * (float)Math.sin(var2 * (0.7F + hash(var9 * 3 + 2)) + var9);
         var1.circle(var10, var11, 0.6F + hash(var9 * 7) * 0.8F, BlendMode.withAlpha(-1, (0.08F + 0.3F * var12) * var6));
      }
   }

   private void photoStage(Canvas var1, float var2, float var3, float var4) {
      boolean var5 = MotionStyle.reduced();
      float var6 = this.stageX + this.stageW / 2.0F;
      float var7 = Math.min(Math.min(this.stageW * 0.62F, this.stageH * 0.72F), 224.0F);
      float var8 = var5 ? 0.0F : (float)Math.sin(var2 * 1.05F) * 2.5F;
      float var9 = this.stageY + this.stageH * 0.48F + var8;
      float var10 = Easing.EMPHASIZED.apply(ProfileCurve.clamp01(var3 / 0.7F));
      float var11 = var7 * (0.9F + 0.1F * var10) * (1.0F + 0.02F * var4);
      float var12 = var7 * 0.76F;
      float var13 = var12 * 0.22F;
      float var14 = var9 + var7 * 0.1F;
      var1.pushClip(this.stageX, this.stageY - 4.0F, this.stageW, this.stageH + 8.0F, 14.0F);
      this.sky(var1, var2, var6, var9, var7 * 1.25F, var10);
      this.ringHalf(var1, var6, var14, var12, var13, var10, false);
      this.sparks(var1, var6, var14, var12, var13, var2, var10, false);
      var1.softCircle(var6, var9 + var11 * 0.14F, var11 * 0.44F, 30.0F, BlendMode.withAlpha(-16777216, 0.4F * var10));
      var1.glowCircle(var6, var9, var11 / 2.0F, 26.0F + 10.0F * var4, BlendMode.withAlpha(Icons.VIOLET, (0.26F + 0.14F * var4) * var10));
      var1.pushAlpha(var10);
      ImageFiles.drawOrInitial(var1, var6, var9, var11, SettingSection.PROFILE.displayName());
      var1.pushClip(var6 - var11 / 2.0F, var9 - var11 / 2.0F, var11, var11, var11 / 2.0F);
      var1.circleRadial(var6 - var11 * 0.2F, var9 - var11 * 0.26F, var11 * 0.55F, 452984831, 16777215);
      if (var4 > 0.01F) {
         var1.rect(var6 - var11 / 2.0F, var9 - var11 / 2.0F, var11, var11, BlendMode.withAlpha(Theme.tint(-16120042), 0.38F * var4));
      }

      var1.popClip();
      if (var4 > 0.01F) {
         var1.icon(ImageFiles.present() ? "image-up" : "image-plus", var6, var9, 24.0F, BlendMode.withAlpha(Icons.TEXT, var4));
      }

      var1.arc(
         var6,
         var9,
         var11 / 2.0F + 0.5F,
         1.6F,
         -90.0F,
         360.0F,
         BlendMode.withAlpha(Icons.PURPLE, 0.85F + 0.15F * var4),
         BlendMode.withAlpha(Icons.MAGENTA, 0.5F),
         false
      );
      var1.popAlpha();
      this.ringHalf(var1, var6, var14, var12, var13, var10, true);
      this.sparks(var1, var6, var14, var12, var13, var2, var10, true);
      var1.popClip();
      this.photoX = var6;
      this.photoY = var9;
      this.photoD = var11;
      if (var4 > 0.01F) {
         String var15 = ImageFiles.present() ? "Click to change your photo" : "Click to add a photo";
         var1.text(
            Fonts.MEDIUM,
            var15,
            var6,
            this.stageY + this.stageH - 8.0F,
            10.0F,
            BlendMode.withAlpha(Icons.TEXT_3, var4 * var10),
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
      }
   }

   private void ringHalf(Canvas var1, float var2, float var3, float var4, float var5, float var6, boolean var7) {
      if (!(var6 <= 0.01F)) {
         var1.push();
         var1.translate(var2, var3);
         var1.rotate(-0.07F);
         var1.scale(1.0F, var5 / var4);
         int var8 = BlendMode.withAlpha(Icons.PURPLE, (var7 ? 0.85F : 0.3F) * var6);
         int var9 = BlendMode.withAlpha(Icons.INDIGO, (var7 ? 0.5F : 0.16F) * var6);
         if (var7) {
            var1.arc(0.0F, 0.0F, var4, 2.4F, 0.0F, 180.0F, var8, var9, false);
            var1.arc(0.0F, 0.0F, var4, 10.0F, 25.0F, 130.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.08F * var6), BlendMode.withAlpha(Icons.VIOLET, 0.0F), false);
         } else {
            var1.arc(0.0F, 0.0F, var4, 2.0F, 180.0F, 180.0F, var9, var8, false);
         }

         var1.pop();
      }
   }

   private void sparks(Canvas var1, float var2, float var3, float var4, float var5, float var6, float var7, boolean var8) {
      float var9 = (float)Math.cos(-0.07F);
      float var10 = (float)Math.sin(-0.07F);

      for (int var11 = 0; var11 < 3; var11++) {
         double var12 = (MotionStyle.reduced() ? 0.6 : var6 * 0.42) + var11 * 2.0944;
         float var14 = (float)Math.cos(var12) * var4;
         float var15 = (float)Math.sin(var12) * var5;
         boolean var16 = Math.sin(var12) > 0.0;
         if (var16 == var8) {
            float var17 = var2 + var14 * var9 - var15 * var10;
            float var18 = var3 + var14 * var10 + var15 * var9;
            float var19 = (float)(Math.sin(var12) + 1.0) / 2.0F;
            float var20 = (var11 == 0 ? 3.2F : 2.2F) * (0.7F + 0.3F * var19);
            float var21 = var7 * (0.35F + 0.65F * var19);
            var1.glowCircle(var17, var18, var20, var20 * 4.0F, BlendMode.withAlpha(var11 == 0 ? Icons.MAGENTA : Icons.VIOLET, 0.45F * var21));
            var1.circle(var17, var18, var20, BlendMode.withAlpha(BlendMode.lerp(-1, Icons.PINK, var11 == 0 ? 0.35F : 0.1F), var21));
         }
      }
   }

   private void pose(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      float var6 = Math.min(var1.dt, 0.1F);
      if (!this.dragging) {
         this.spin = this.spin + this.spinVelocity * var6;
         this.spinVelocity = this.spinVelocity * (float)Math.exp(-3.2F * var6);
      }

      float var7 = MotionStyle.reduced() ? 0.0F : 0.16F * (float)Math.sin(var2 * 0.33F);
      this.pose.yaw = this.spin + var7;
      this.pose.time = MotionStyle.reduced() ? 0.0F : var2;
      this.pose.lift = MotionStyle.reduced() ? 0.0F : 0.35F + 0.35F * (float)Math.sin(var2 * 1.3F);
      float var8 = 0.0F;
      float var9 = 0.0F;
      if (var5 && !var1.masked()) {
         float var10 = var1.mouseX - var3;
         float var11 = var1.mouseY - var4;
         float var12 = (float)Math.atan2(var10, 260.0);
         var8 = clamp(this.pose.yaw - var12, -1.0F, 1.0F);
         var9 = clamp((float)Math.atan2(var11, 320.0), -0.45F, 0.5F);
      }

      this.pose.headYaw = this.headYaw.target(var8).update(MotionStyle.HOVER.dt(var1.dt) * 0.6F);
      this.pose.headPitch = this.headPitch.target(var9).update(MotionStyle.HOVER.dt(var1.dt) * 0.6F);
      float var13 = var2 - this.waveAt;
      this.pose.wave = var13 < 0.0F ? 0.0F : (var13 < 0.25F ? var13 / 0.25F : (var13 < 1.65F ? 1.0F : Math.max(0.0F, 1.0F - (var13 - 1.65F) / 0.35F)));
   }

   private void archetype(Canvas var1, ProfileSupport6 var2, float var3, float var4) {
      ProfileSupport3.TypeF var5 = var2.archetype;
      String var6 = var5 == null ? "Stargazer" : "The " + var5.label;
      String var7 = var5 == null ? "sparkles" : var5.icon;
      float var8 = 12.5F;
      float var9 = var1.textWidth(Fonts.MEDIUM, var6, var8);
      float var10 = var3 - (var9 + 20.0F) / 2.0F;
      var1.icon(var7, var10 + 7.0F, var4, 13.0F, Theme.tint(-4611329));
      var1.textGradient(Fonts.MEDIUM, var6, var10 + 20.0F, var4, var8, Theme.tint(-4611329), Theme.tint(-1012489), FontStyle.LEFT, FontWeight.MIDDLE, 0.0F);
   }

   private void level(DrawContext var1, Canvas var2, ProfileSupport6 var3, float var4, float var5, float var6, float var7) {
      float var8 = ProfileCurve.clamp01((var7 - 0.45F) / 0.5F);
      var2.pushAlpha(var8);
      var2.text(Fonts.SEMIBOLD, "Level " + var3.level, var4, var5, 11.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      String var9 = ProfileSupport2.duration(Math.max(60.0, var3.toNextLevel));
      String var10 = var9 + " to level " + (var3.level + 1);
      var2.text(Fonts.MEDIUM, var10, var4 + var6, var5, 10.5F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.MIDDLE);
      float var11 = var5 + 13.0F;
      float var12 = 5.0F;
      var2.roundRect(var4, var11, var6, var12, var12 / 2.0F, 352321535);
      float var13 = this.levelBar.target(var7 > 0.55F ? (float)var3.levelProgress : 0.0F).update(MotionStyle.MENU.dt(var1.dt) * 0.55F);
      float var14 = Math.max(var12, var6 * ProfileCurve.clamp01(var13));
      var2.glow(var4, var11, var14, var12, var12 / 2.0F, 10.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.4F));
      var2.roundRectGradient(var4, var11, var14, var12, var12 / 2.0F, 0.0F, Theme.tint(-4611329), Theme.tint(-1012489));
      var2.circle(var4 + var14 - var12 / 2.0F, var11 + var12 / 2.0F, var12 * 0.8F, -1);
      var2.popAlpha();
   }

   private void facts(Canvas var1, ProfileSupport6 var2, float var3, float var4, float var5, float var6) {
      String[] var7 = new String[]{"DAYS PLAYED", "BEST STREAK", "SINCE"};
      String[] var8 = new String[]{
         ProfileSupport2.count(var2.activeDays),
         var2.bestStreak + (var2.bestStreak == 1 ? " day" : " days"),
         var2.data.since == null ? "Today" : ProfileSupport2.date(var2.data.since)
      };
      float var9 = var5 / 3.0F;

      for (int var10 = 0; var10 < 3; var10++) {
         float var11 = Easing.EMPHASIZED.apply(ProfileCurve.clamp01((var6 - 0.55F - var10 * 0.07F) / 0.5F));
         float var12 = var3 + var9 * var10 + var9 / 2.0F;
         var1.pushAlpha(var11);
         var1.text(Fonts.SEMIBOLD, var8[var10], var12, var4 + 8.0F + (1.0F - var11) * 5.0F, 15.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE);
         ProfileRenderer9.label(var1, var7[var10], var12, var4 + 30.0F, Icons.TEXT_3, FontStyle.CENTER);
         var1.popAlpha();
         if (var10 > 0) {
            var1.rect(var3 + var9 * var10, var4, 1.0F, 36.0F, 285212671);
         }
      }
   }

   boolean mouseDown(float var1, float var2, float var3) {
      if (var1 < this.stageX || var1 > this.stageX + this.stageW || var2 < this.stageY || var2 > this.stageY + this.stageH) {
         return false;
      } else if (SettingSection.PROFILE.photoPortrait()) {
         float var4 = var1 - this.photoX;
         float var5 = var2 - this.photoY;
         if (var4 * var4 + var5 * var5 > this.photoD * this.photoD / 4.0F) {
            return false;
         } else {
            SoundRequest.play(SoundEvent.EXPAND);
            class_310 var6 = class_310.method_1551();
            var6.method_1507(WelcomeScreen.photo(var6.field_1755));
            return true;
         }
      } else {
         this.dragging = true;
         this.dragMoved = false;
         this.dragX = var1;
         this.dragLastX = var1;
         this.dragLastTime = System.nanoTime();
         this.spinVelocity = 0.0F;
         return true;
      }
   }

   void mouseDrag(float var1) {
      if (this.dragging) {
         if (Math.abs(var1 - this.dragX) > 3.0F) {
            this.dragMoved = true;
         }

         long var2 = System.nanoTime();
         float var4 = Math.max(0.001F, (float)(var2 - this.dragLastTime) / 1.0E9F);
         float var5 = (var1 - this.dragLastX) * 0.012F;
         this.spin += var5;
         this.spinVelocity = this.spinVelocity * 0.5F + var5 / var4 * 0.5F;
         this.dragLastX = var1;
         this.dragLastTime = var2;
      }
   }

   void mouseUp(float var1) {
      if (this.dragging) {
         this.dragging = false;
         this.spinVelocity = clamp(this.spinVelocity, -14.0F, 14.0F);
         if (!this.dragMoved) {
            this.spinVelocity = 0.0F;
            if (var1 - this.waveAt > 1.2F) {
               this.waveAt = var1;
               SoundRequest.play(SoundEvent.CLICK);
            }
         }
      }
   }

   boolean dragging() {
      return this.dragging;
   }

   private static float clamp(float var0, float var1, float var2) {
      return var0 < var1 ? var1 : Math.min(var0, var2);
   }

   private static float hash(int var0) {
      double var1 = Math.sin(var0 * 12.9898 + 78.233) * 43758.5453;
      return (float)(var1 - Math.floor(var1));
   }
}
