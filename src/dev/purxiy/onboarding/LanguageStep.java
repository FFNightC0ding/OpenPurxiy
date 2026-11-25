package dev.purxiy.onboarding;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
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
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.widget.Spacer;
import java.util.Locale;
import net.minecraft.class_310;

final class LanguageStep extends OnboardingStep {
   private static final String[] SETTING = new String[]{"English", "Русский"};
   private static final String[] NAMES = new String[]{"English", "Русский"};
   private static final String[] GLYPHS = new String[]{"Aa", "Яя"};
   private static final String[] SAMPLES = new String[]{"Welcome to SourceByDevFFnightmare DeObfDeCompByGuideX", "Добро пожаловать в SourceByDevFFnightmare DeObfDeCompByGuideX"};
   private static final String[] GAME_CODES = new String[]{"en_us", "ru_ru"};
   private static final float CARD_H = 172.0F;
   private static final float GAME_ROW = 62.0F;
   private final Animator[] hover = new Animator[]{Animator.smooth(0.0F), Animator.smooth(0.0F)};
   private final Animator[] chosen = new Animator[]{Animator.snappy(0.0F), Animator.snappy(0.0F)};
   private final float[][] cards = new float[2][4];
   private final Animator gameRow = Animator.smooth(0.0F);
   private final Animator gameHover = Animator.smooth(0.0F);
   private final Spacer gameSwitch = new Spacer(false);
   private final Object gameKey = new Object();
   private float gameY;
   private float gameX;
   private float gameW;
   private boolean alsoGame;
   private boolean first = true;

   LanguageStep(WelcomeScreen var1) {
      super(var1, "Language", "Which language do you prefer?", "The menu, the HUD and every tip will use it. You can change it later in Client → Interface.");
   }

   private static int selected() {
      return Translations.russian() ? 1 : 0;
   }


   String gameLanguage() {
      return this.alsoGame && this.offer() ? GAME_CODES[selected()] : null;
   }

   private boolean offer() {
      String var1 = class_310.method_1551().field_1690.field_1883.toLowerCase(Locale.ROOT);
      return selected() == 1 ? !var1.startsWith("ru") : var1.startsWith("ru");
   }

   @Override
   float height(float var1) {
      return 172.0F + 78.0F * this.gameRow.value();
   }

   @Override
   void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      Canvas var6 = var1.canvas;
      float var7 = var1.time;
      int var8 = selected();
      if (this.first) {
         this.chosen[var8].snap(1.0F);
         this.first = false;
      }

      float var9 = 16.0F;
      float var10 = (var4 - var9) / 2.0F;

      for (int var11 = 0; var11 < 2; var11++) {
         float var12 = this.in(var7, 3 + var11);
         float var13 = var2 + var11 * (var10 + var9);
         var6.push();
         var6.translate(0.0F, (1.0F - var12) * 16.0F);
         var6.pushAlpha(var12);
         this.card(var1, var6, var11, var13, var3, var10, var8 == var11, var5 && var12 > 0.8F);
         var6.popAlpha();
         var6.pop();
      }

      boolean var17 = this.offer();
      float var18 = this.gameRow.target(var17 ? 1.0F : 0.0F).update(MotionStyle.PANEL.dt(var1.dt)) * this.in(var7, 5);
      this.gameX = var2;
      this.gameW = var4;
      this.gameY = var3 + 172.0F + 16.0F;
      if (var18 > 0.01F) {
         var6.pushAlpha(var18);
         float var19 = this.gameY + (1.0F - var18) * 8.0F;
         boolean var14 = var5 && var17 && var1.hover(this.gameKey, var2, var19, var4, 62.0F, DrawContext.Cursor.HAND);
         float var15 = this.gameHover.target(var14 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         var6.roundRect(var2, var19, var4, 62.0F, 16.0F, BlendMode.lerp(184549375, 318767103, var15));
         var6.roundRectOutline(var2, var19, var4, 62.0F, 16.0F, 1.0F, BlendMode.lerp(352321535, 654311423, var15));
         String var16 = var8 == 1 ? "Play Minecraft in Russian too" : "Play Minecraft in English too";
         OnboardingState.option(var6, var2, var19, var4, "globe", var16, "Switches the game's own language when you finish", 60.0F, 0.0F);
         this.gameSwitch.render(var1, var2 + var4 - 16.0F - 46.0F, var19 + 31.0F - 13.0F, 46.0F, 26.0F, this.alsoGame, var14, false);
         var6.popAlpha();
      }
   }

   private void card(DrawContext var1, Canvas var2, int var3, float var4, float var5, float var6, boolean var7, boolean var8) {
      float[] var9 = this.cards[var3];
      var9[0] = var4;
      var9[1] = var5;
      var9[2] = var6;
      var9[3] = 172.0F;
      boolean var10 = var8 && var1.hover(this.hover[var3], var4, var5, var6, 172.0F, DrawContext.Cursor.HAND);
      float var11 = this.hover[var3].target(var10 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var12 = this.chosen[var3].target(var7 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      float var13 = -3.0F * var11;
      float var14 = var5 + var13;
      KeyLabel.surface(var2, var4, var14, var6, 172.0F, 20.0F, 0.2F + 0.3F * var11, var12);
      if (var12 > 0.01F) {
         var2.roundRectOutlineGradient(
            var4, var14, var6, 172.0F, 20.0F, 1.5F, 0.0F, BlendMode.withAlpha(Icons.PURPLE, var12), BlendMode.withAlpha(Icons.MAGENTA, 0.8F * var12)
         );
         var2.glowCircle(var4 + var6 * 0.3F, var14 + 60.2F, 30.0F, 90.0F, BlendMode.withAlpha(Icons.VIOLET, 0.12F * var12));
      }

      Translations.pause();
      float var15 = 50.0F;
      float var16 = var4 + 22.0F;
      float var17 = var14 + 22.0F;
      var2.roundRect(var16, var17, var15, var15, 15.0F, BlendMode.lerp(285212671, 419430399, var11));
      if (var12 > 0.01F) {
         var2.pushAlpha(var12);
         var2.glow(var16, var17, var15, var15, 15.0F, 16.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.3F));
         var2.roundRectGradient(var16, var17, var15, var15, 15.0F, 135.0F, Icons.INDIGO, Icons.MAGENTA);
         var2.roundRectCorners(var16, var17, var15, var15, 15.0F, 872415231, 352321535, 16777215, 16777215);
         var2.popAlpha();
      }

      var2.roundRectOutline(var16, var17, var15, var15, 15.0F, 1.0F, BlendMode.lerp(352321535, 788529151, Math.max(var11, var12)));
      var2.text(
         Fonts.SEMIBOLD,
         GLYPHS[var3],
         var16 + var15 / 2.0F,
         var17 + var15 / 2.0F + 0.5F,
         19.0F,
         BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, Math.max(var11, var12)),
         FontStyle.CENTER,
         FontWeight.MIDDLE
      );
      float var18 = var4 + var6 - 34.0F;
      float var19 = var14 + 36.0F;
      var2.ring(var18, var19, 11.0F, 1.5F, BlendMode.lerp(788529151, 16777215, var12));
      if (var12 > 0.01F) {
         float var20 = 0.6F + 0.4F * var12;
         var2.glowCircle(var18, var19, 11.0F * var20, 18.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.35F * var12));
         var2.pushAlpha(var12);
         var2.roundRectGradient(var18 - 11.0F * var20, var19 - 11.0F * var20, 22.0F * var20, 22.0F * var20, 11.0F * var20, 135.0F, Icons.INDIGO, Icons.MAGENTA);
         var2.icon("check", var18, var19 + 0.5F, 12.0F * var20, Icons.TEXT);
         var2.popAlpha();
      }

      var2.text(Fonts.SEMIBOLD, NAMES[var3], var4 + 22.0F, var14 + 172.0F - 56.0F, 21.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE, -0.01F);
      var2.text(
         Fonts.REGULAR,
         SAMPLES[var3],
         var4 + 22.0F,
         var14 + 172.0F - 30.0F,
         12.5F,
         BlendMode.lerp(Icons.TEXT_3, Icons.TEXT_2, Math.max(var11, var12)),
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      Translations.resume();
   }

   private void choose(int var1) {
      if (selected() != var1) {
         SettingSection.INTERFACE.language.set(SETTING[var1]);
         this.screen.languageChanged();
      }

      SoundRequest.play(SoundEvent.TAB);
   }

   @Override
   boolean mouseDown(float var1, float var2, int var3) {
      for (int var4 = 0; var4 < 2; var4++) {
         float[] var5 = this.cards[var4];
         if (var1 >= var5[0] && var1 <= var5[0] + var5[2] && var2 >= var5[1] && var2 <= var5[1] + var5[3]) {
            this.choose(var4);
            return true;
         }
      }

      if (this.offer() && var1 >= this.gameX && var1 <= this.gameX + this.gameW && var2 >= this.gameY && var2 <= this.gameY + 62.0F) {
         this.alsoGame = !this.alsoGame;
         SoundRequest.play(this.alsoGame ? SoundEvent.TOGGLE_ON : SoundEvent.TOGGLE_OFF);
         return true;
      } else {
         return false;
      }
   }

   @Override
   boolean keyPressed(int var1) {
      switch (var1) {
         case 258:
            this.choose(1 - selected());
            break;
         case 259:
         case 260:
         case 261:
         default:
            return false;
         case 262:
         case 264:
            this.choose(1);
            break;
         case 263:
         case 265:
            this.choose(0);
      }

      return true;
   }

   void dev(int var1, boolean var2) {
      this.choose(var1);
      this.alsoGame = var2;
   }
}
