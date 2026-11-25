package dev.purxiy.onboarding;

import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.texture.TextureLoader;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;

final class IntroStep extends OnboardingStep {
   private static final String[] GREETINGS = new String[]{"Welcome", "Добро пожаловать"};
   private static final float TILT = -0.1F;

   private static TextureLoader mark;
   private final StepNavigation start = new StepNavigation();

   IntroStep(WelcomeScreen var1) {
      super(var1, "", "Welcome to SourceByDevFFnightmare DeObfDeCompByGuideX", "A few quick questions and SourceByDevFFnightmare DeObfDeCompByGuideX is all yours. It takes about a minute.  Sources: DevFFNightmare.  Deobfuscation and rebuild: GuideX.");
   }

   @Override
   boolean full() {
      return true;
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
      boolean var10 = MotionStyle.reduced();
      float var11 = var7 / 2.0F;
      float var12 = Math.max(80.0F, Math.min(120.0F, var8 * 0.15F));
      float var13 = var12 * mark.aspect();
      float var14 = var10 ? 0.0F : (float)Math.sin(var9 * 1.1F) * 3.5F;
      float var15 = var8 * 0.335F + var14;
      float var16 = this.in(var9, 0);
      float var17 = Easing.CUBIC_OUT.apply(OnboardingState.clamp01((var9 - this.enteredAt - 0.15F) / 1.1F));
      float var18 = var10 ? 0.9F : 0.84F + 0.16F * (float)Math.sin(var9 * 1.3F);
      float var19 = var10 ? 0.0F : Math.max(0.0F, 1.0F - Math.abs((var9 - this.enteredAt - 0.45F) / 0.45F));
      var6.glowCircle(
         var11, var15, var12 * 0.5F, var12 * (1.4F + 0.6F * var19), BlendMode.withAlpha(Theme.logoTint(-3069451), (0.22F * var18 + 0.2F * var19) * var16)
      );
      var6.glowCircle(var11, var15, var12 * 0.28F, var12 * 0.8F, BlendMode.withAlpha(Theme.logoTint(-7860231), 0.2F * var16));
      float var20 = var12 * 1.55F;
      float var21 = var20 * 0.22F;
      float var22 = var15 + var12 * 0.12F;
      OnboardingState.ring(var6, var11, var22, var20, var21, -0.1F, var16, false, var17);
      OnboardingState.moons(var6, var11, var22, var20, var21, -0.1F, var9, var16 * var17, false, 3);
      float var23 = 0.84F + 0.16F * var16;
      var6.push();
      var6.scaleAround(var23, var11, var15);
      var6.logo(mark, var11 - var13 / 2.0F, var15 - var12 / 2.0F, var13, var12, BlendMode.withAlpha(-1, var16));
      var6.pop();
      OnboardingState.ring(var6, var11, var22, var20, var21, -0.1F, var16, true, var17);
      OnboardingState.moons(var6, var11, var22, var20, var21, -0.1F, var9, var16 * var17, true, 3);
      float var24 = var15 - var14 + var12 / 2.0F + var21 + 44.0F;
      float var25 = 3.4F;
      float var26 = var10 ? 0.5F / var25 : (Math.max(0.0F, var9 - this.enteredAt - 1.4F) + 0.5F) / var25;
      int var27 = (int)Math.floor(var26) % GREETINGS.length;
      float var28 = (var26 - (float)Math.floor(var26)) * var25;
      float var29 = var28 > var25 - 0.45F ? 1.0F - (var28 - (var25 - 0.45F)) / 0.45F : Math.min(1.0F, var28 / 0.45F);
      Translations.pause();
      OnboardingState.eyebrow(var6, GREETINGS[var27], var11, var24 + (1.0F - this.in(var9, 1)) * 8.0F, this.in(var9, 1) * Easing.SMOOTHSTEP.apply(var29));
      Translations.resume();
      float var30 = this.in(var9, 2);
      var6.pushAlpha(var30);
      var6.text(Fonts.BOLD, this.title, var11, var24 + 40.0F + (1.0F - var30) * 12.0F, 40.0F, Icons.TEXT, FontStyle.CENTER, FontWeight.MIDDLE, -0.015F);
      var6.popAlpha();
      float var31 = this.in(var9, 3);
      var6.pushAlpha(var31);
      float var32 = Math.min(520.0F, var7 - 64.0F);
      var6.paragraph(
         Fonts.REGULAR, this.subtitle, var11 - var32 / 2.0F, var24 + 72.0F + (1.0F - var31) * 10.0F, 14.5F, var32, 1.45F, Icons.TEXT_2, FontStyle.CENTER
      );
      var6.popAlpha();
      float var33 = this.in(var9, 4);
      float var34 = 48.0F;
      String var35 = "Get started";
      float var36 = StepNavigation.width(var6, var35, (String)null, "arrow-right", var34);
      float var37 = var24 + 150.0F + (1.0F - var33) * 12.0F;
      var6.pushAlpha(var33);
      this.start.render(var1, var11 - var36 / 2.0F, var37, var34, var35, (String)null, "arrow-right", StepNavigation.AccentStyle.PRIMARY, true, var5 && var33 > 0.6F);
      var6.text(Fonts.MEDIUM, "or press Enter", var11, var37 + var34 / 2.0F + 22.0F, 11.5F, Icons.TEXT_3, FontStyle.CENTER, FontWeight.MIDDLE);
      var6.popAlpha();
   }

   @Override
   boolean mouseDown(float var1, float var2, int var3) {
      if (var3 == 0 && this.start.hit(var1, var2)) {
         this.screen.next();
         return true;
      } else {
         return false;
      }
   }

   @Override
   boolean keyPressed(int var1) {
      return var1 == 32 && this.screen.next();
   }
}
