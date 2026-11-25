package dev.purxiy.onboarding;

import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.core.ImageFiles;
import dev.purxiy.i18n.Translations;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.profile.ProfilePanel;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.texture.TextureLoader;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.Theme;

final class CompleteStep extends OnboardingStep {
   private static final float CARD_W = 420.0F;
   private static final float CARD_H = 168.0F;
   private static final int SPARKS = 30;
   private static final float TILT = -0.12F;

   private static TextureLoader mark;
   private final StepNavigation play = new StepNavigation();
   private final StepNavigation open = new StepNavigation();
   private final boolean fromMenu;

   CompleteStep(WelcomeScreen var1, boolean var2) {
      super(var1, "", "You're all set", "Everything is saved. You can change all of it later in Client and Profile.");
      this.fromMenu = var2;
   }

   @Override
   boolean full() {
      return true;
   }

   @Override
   String title() {
      return "You're all set, " + SettingSection.PROFILE.displayName() + "!";
   }

   @Override
   void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      Canvas var6 = var1.canvas;
      if (mark == null) {
         mark = TextureLoader.load("textures/brand/mark.png");
      }

      float var7 = var6.width();
      float var8 = var6.height();
      float var9 = var1.time;
      float var10 = var7 / 2.0F;
      float var11 = Math.max(70.0F, var8 * 0.5F - 250.0F);
      float var12 = this.in(var9, 0);
      var6.pushAlpha(var12);
      String var13 = SettingSection.PROFILE.displayName();
      var6.text(
         Fonts.BOLD,
         FontMetrics.ellipsize(Fonts.BOLD, this.title(), 36.0F, var7 - 80.0F, 0.0F),
         var10,
         var11 + (1.0F - var12) * 12.0F,
         36.0F,
         Icons.TEXT,
         FontStyle.CENTER,
         FontWeight.MIDDLE,
         -0.015F
      );
      var6.popAlpha();
      float var14 = this.in(var9, 1);
      var6.pushAlpha(var14);
      float var15 = Math.min(520.0F, var7 - 64.0F);
      var6.paragraph(
         Fonts.REGULAR, this.subtitle, var10 - var15 / 2.0F, var11 + 30.0F + (1.0F - var14) * 10.0F, 14.0F, var15, 1.45F, Icons.TEXT_2, FontStyle.CENTER
      );
      var6.popAlpha();
      float var16 = this.in(var9, 2);
      float var17 = var10 - 210.0F;
      float var18 = var11 + 92.0F + (1.0F - var16) * 16.0F;
      var6.pushAlpha(var16);
      this.card(var6, var9, var17, var18, var13);
      var6.popAlpha();
      this.sparks(var6, var9, var17 + 30.0F + 46.0F, var18 + 84.0F + 12.0F);
      float var19 = this.in(var9, 3);
      var6.pushAlpha(var19);
      float var20 = var18 + 168.0F + 42.0F + (1.0F - var19) * 8.0F;
      String var21 = KeyLabel.keyName(EssentialsStep.menuKey());
      String var22 = Translations.shown("Press");
      String var23 = Translations.shown("to open SourceByDevFFnightmare DeObfDeCompByGuideX any time");
      Translations.pause();
      float var24 = var23.startsWith(",") ? 2.0F : 10.0F;
      float var25 = var6.textWidth(Fonts.MEDIUM, var22, 13.0F);
      float var26 = var6.textWidth(Fonts.MEDIUM, var23, 13.0F);
      float var27 = Math.max(21.0F, var6.textWidth(Fonts.SEMIBOLD, var21, 11.0F) + 12.0F);
      float var28 = var25 + 10.0F + var27 + var24 + var26;
      float var29 = var10 - var28 / 2.0F;
      var6.text(Fonts.MEDIUM, var22, var29, var20, 13.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      var6.glow(var29 + var25 + 10.0F, var20 - 10.5F, var27, 21.0F, 6.0F, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.3F));
      KeyLabel.keyCap(var6, var21, var29 + var25 + 10.0F, var20, 11.0F, Icons.TEXT);
      var6.text(Fonts.MEDIUM, var23, var29 + var25 + 10.0F + var27 + var24, var20, 13.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      Translations.resume();
      var6.popAlpha();
      float var30 = this.in(var9, 4);
      var6.pushAlpha(var30);
      float var31 = var20 + 62.0F + (1.0F - var30) * 10.0F;
      float var32 = 46.0F;
      boolean var33 = var5 && var30 > 0.7F;
      if (this.fromMenu) {
         float var34 = StepNavigation.width(var6, "Back to SourceByDevFFnightmare DeObfDeCompByGuideX", (String)null, "arrow-right", var32);
         this.open.render(var1, var10 - var34 / 2.0F, var31, var32, "Back to SourceByDevFFnightmare DeObfDeCompByGuideX", (String)null, "arrow-right", StepNavigation.AccentStyle.PRIMARY, true, var33);
      } else {
         float var37 = StepNavigation.width(var6, "Start playing", "play", (String)null, var32);
         float var35 = StepNavigation.width(var6, "Open SourceByDevFFnightmare DeObfDeCompByGuideX", (String)null, "arrow-right", var32);
         float var36 = var10 - (var37 + 12.0F + var35) / 2.0F;
         this.play.render(var1, var36, var31, var32, "Start playing", "play", (String)null, StepNavigation.AccentStyle.GLASS, true, var33);
         this.open.render(var1, var36 + var37 + 12.0F, var31, var32, "Open SourceByDevFFnightmare DeObfDeCompByGuideX", (String)null, "arrow-right", StepNavigation.AccentStyle.PRIMARY, true, var33);
      }

      var6.popAlpha();
   }

   private void card(Canvas var1, float var2, float var3, float var4, String var5) {
      KeyLabel.surface(var1, var3, var4, 420.0F, 168.0F, 22.0F, 0.45F, 0.5F);
      var1.glowCircle(var3 + 105.0F, var4 + 100.8F, 40.0F, 120.0F, BlendMode.withAlpha(Icons.VIOLET, 0.14F));
      float var6 = 15.0F;
      float var7 = var6 * mark.aspect();
      var1.logo(mark, var3 + 24.0F, var4 + 26.0F - var6 / 2.0F, var7, var6, BlendMode.withAlpha(-1, 0.85F));
      var1.text(Fonts.SEMIBOLD, "SOURCEBYDEVFFNIGHTMARE DEOBFDECOMPBYGUIDEX ID", var3 + 24.0F + var7 + 8.0F, var4 + 26.0F, 9.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE, 0.12F);
      float var8 = 92.0F;
      float var9 = var3 + 30.0F + var8 / 2.0F;
      float var10 = var4 + 84.0F + 12.0F;
      float var11 = var8 * 0.74F;
      float var12 = var11 * 0.24F;
      float var13 = var10 + var8 * 0.08F;
      var1.glowCircle(var9, var10, var8 / 2.0F, 26.0F, BlendMode.withAlpha(Icons.VIOLET, 0.3F));
      OnboardingState.ring(var1, var9, var13, var11, var12, -0.12F, 0.9F, false, 1.0F);
      OnboardingState.moons(var1, var9, var13, var11, var12, -0.12F, var2, 0.9F, false, 2);
      ImageFiles.drawOrInitial(var1, var9, var10, var8, var5);
      var1.arc(var9, var10, var8 / 2.0F + 0.5F, 1.5F, -90.0F, 360.0F, BlendMode.withAlpha(Icons.PURPLE, 0.9F), BlendMode.withAlpha(Icons.MAGENTA, 0.5F), false);
      OnboardingState.ring(var1, var9, var13, var11, var12, -0.12F, 0.9F, true, 1.0F);
      OnboardingState.moons(var1, var9, var13, var11, var12, -0.12F, var2, 0.9F, true, 2);
      float var14 = var9 + var8 / 2.0F + 34.0F;
      float var15 = var3 + 420.0F - 24.0F - var14;
      Translations.pause();
      var1.textGradient(
         Fonts.SEMIBOLD,
         FontMetrics.ellipsize(Fonts.SEMIBOLD, var5, 24.0F, var15, 0.0F),
         var14,
         var10 - 14.0F,
         24.0F,
         Icons.TEXT,
         Theme.tint(-1780481),
         FontStyle.LEFT,
         FontWeight.MIDDLE,
         -0.01F
      );
      Translations.resume();
      var1.text(
         Fonts.MEDIUM,
         FontMetrics.ellipsize(Fonts.MEDIUM, ProfilePanel.orbitLine(), 12.0F, var15, 0.0F),
         var14,
         var10 + 16.0F,
         12.0F,
         Icons.TEXT_3,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      double var16 = SettingSection.INTERFACE.scale.get();
      String[][] var18 = new String[][]{
         {"globe", Translations.russian() ? "Русский" : "English"},
         {"scaling", var16 < 0.95 ? "Small" : (var16 > 1.1 ? "Large" : "Default")},
         {"venetian-mask", SettingSection.NICK_HIDER.enabled() ? "Streamer mode" : null}
      };
      float var19 = var14;
      float var20 = var10 + 46.0F;

      for (String[] var24 : var18) {
         if (var24[1] != null) {
            boolean var25 = var24[0].equals("globe");
            if (var25) {
               Translations.pause();
            }

            float var26 = var1.textWidth(Fonts.MEDIUM, var24[1], 11.0F) + 36.0F;
            if (var19 + var26 > var3 + 420.0F - 20.0F) {
               if (var25) {
                  Translations.resume();
               }
               break;
            }

            var1.roundRect(var19, var20 - 12.0F, var26, 24.0F, 12.0F, 285212671);
            var1.roundRectOutline(var19, var20 - 12.0F, var26, 24.0F, 12.0F, 1.0F, 486539263);
            var1.icon(var24[0], var19 + 14.0F, var20, 11.5F, Theme.tint(-3561729));
            var1.text(Fonts.MEDIUM, var24[1], var19 + 25.0F, var20, 11.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
            if (var25) {
               Translations.resume();
            }

            var19 += var26 + 6.0F;
         }
      }
   }

   private void sparks(Canvas var1, float var2, float var3, float var4) {
      float var5 = var2 - this.enteredAt - 0.25F;
      if (!(var5 <= 0.0F) && !(var5 > 1.8F) && !MotionStyle.reduced()) {
         float var6 = var5 / 1.8F;
         var1.ring(
            var3,
            var4,
            48.0F + Easing.CUBIC_OUT.apply(Math.min(1.0F, var5 / 0.9F)) * 60.0F,
            2.2F * (1.0F - var6),
            BlendMode.withAlpha(Icons.PINK, 0.55F * (1.0F - var6))
         );

         for (int var7 = 0; var7 < 30; var7++) {
            float var8 = OnboardingState.hash(var7 * 3 + 101) * (float) (Math.PI * 2);
            float var9 = 70.0F + 150.0F * OnboardingState.hash(var7 * 3 + 102);
            float var10 = Easing.CUBIC_OUT.apply(Math.min(1.0F, var5 / 1.3F)) * var9;
            float var11 = var3 + (float)Math.cos(var8) * var10;
            float var12 = var4 + (float)Math.sin(var8) * var10 * 0.8F + var5 * var5 * 14.0F;
            float var13 = 1.2F + 1.6F * OnboardingState.hash(var7 * 3 + 103);
            float var14 = (1.0F - var6) * (1.0F - var6);
            int var15 = var7 % 3 == 0 ? Icons.PINK : (var7 % 3 == 1 ? Theme.tint(-3561729) : -1);
            var1.glowCircle(var11, var12, var13, var13 * 5.0F, BlendMode.withAlpha(var15, 0.4F * var14));
            var1.circle(var11, var12, var13, BlendMode.withAlpha(var15, var14));
         }
      }
   }

   @Override
   boolean mouseDown(float var1, float var2, int var3) {
      if (this.open.hit(var1, var2)) {
         this.screen.finish(!this.fromMenu);
         return true;
      } else if (!this.fromMenu && this.play.hit(var1, var2)) {
         this.screen.finish(false);
         return true;
      } else {
         return false;
      }
   }
}
