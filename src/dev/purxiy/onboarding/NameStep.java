package dev.purxiy.onboarding;

import dev.purxiy.core.ImageFiles;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.client.TabListProvider;
import dev.purxiy.modules.SettingSection;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.font.TrueTypeFont;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.widget.Slider;
import net.minecraft.class_310;

final class NameStep extends OnboardingStep {
   private static final float CARD_W = 420.0F;
   private static final float CARD_H = 104.0F;
   private final Slider field;
   private String lastName = "";
   private float editedAt = -100.0F;

   NameStep(WelcomeScreen var1) {
      super(var1, "Your name", "What should we call you?", "Your profile shows this name and SourceByDevFFnightmare DeObfDeCompByGuideX greets you with it. Your Minecraft name stays the same.");
      Slider var2 = new Slider(new Slider.Listener() {
         @Override
         public String get() {
            return SettingSection.PROFILE.name.get();
         }

         @Override
         public void set(String var1) {
            SettingSection.PROFILE.name.set(var1);
         }

         @Override
         public int maxLength() {
            return SettingSection.PROFILE.name.maxLength();
         }


         @Override
         public String placeholder() {
            return "Type your name…";
         }
      });
      this.field = var2.onSubmit(var1::next);
   }

   static String minecraftName() {
      return TabListProvider.plain(class_310.method_1551().method_1548().method_1676());
   }

   @Override
   void enter(float var1) {
      super.enter(var1);
      this.field.focus();
   }

   @Override
   void leave() {
      this.field.blur();
      SettingSection.PROFILE.name.set(SettingSection.PROFILE.name.get().trim());
   }

   @Override
   float height(float var1) {
      return 192.0F;
   }

   @Override
   void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      Canvas var6 = var1.canvas;
      float var7 = var1.time;
      float var8 = Math.min(var4, 420.0F);
      float var9 = var2 + (var4 - var8) / 2.0F;
      String var10 = SettingSection.PROFILE.name.get().trim();
      if (!var10.equals(this.lastName)) {
         this.lastName = var10;
         this.editedAt = var7;
      }

      String var11 = var10.isEmpty() ? minecraftName() : var10;
      float var12 = this.in(var7, 3);
      var6.push();
      var6.translate(0.0F, (1.0F - var12) * 14.0F);
      var6.pushAlpha(var12);
      KeyLabel.surface(var6, var9, var3, var8, 104.0F, 22.0F, 0.35F, 0.35F);
      float var13 = 64.0F;
      float var14 = var9 + 22.0F + var13 / 2.0F;
      float var15 = var3 + 52.0F;
      var6.glowCircle(var14, var15, var13 / 2.0F, 22.0F, BlendMode.withAlpha(Icons.VIOLET, 0.28F));
      ImageFiles.drawOrInitial(var6, var14, var15, var13, var11);
      var6.arc(
         var14, var15, var13 / 2.0F + 3.5F, 1.4F, -90.0F, 360.0F, BlendMode.withAlpha(Icons.PURPLE, 0.9F), BlendMode.withAlpha(Icons.MAGENTA, 0.5F), false
      );
      float var16 = var14 + var13 / 2.0F + 20.0F;
      float var17 = var9 + var8 - 22.0F - var16;
      var6.text(Fonts.SEMIBOLD, "SOURCEBYDEVFFNIGHTMARE DEOBFDECOMPBYGUIDEX ID", var16, var3 + 28.0F, 9.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE, 0.12F);
      Translations.pause();
      String var18 = FontMetrics.ellipsize(Fonts.SEMIBOLD, var11, 22.0F, var17, 0.0F);
      float var19 = Math.max(0.0F, 1.0F - (var7 - this.editedAt) / 0.5F);
      if (var19 > 0.01F && !var10.isEmpty()) {
         var6.textGlow(
            Fonts.SEMIBOLD,
            var18,
            var16,
            var3 + 53.0F,
            22.0F,
            BlendMode.withAlpha(Icons.MAGENTA, 0.45F * var19),
            FontStyle.LEFT,
            FontWeight.MIDDLE,
            -0.01F,
            2.0F,
            6.0F
         );
      }

      var6.text(Fonts.SEMIBOLD, var18, var16, var3 + 53.0F, 22.0F, var10.isEmpty() ? Icons.TEXT_2 : Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE, -0.01F);
      Translations.resume();
      float var20 = var6.text(Fonts.MEDIUM, "Minecraft", var16, var3 + 79.0F, 11.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
      Translations.pause();
      TrueTypeFont var21 = Fonts.MEDIUM;
      TrueTypeFont var22 = Fonts.MEDIUM;
      String var23 = minecraftName();
      float var24 = Math.max(20.0F, var17 - var20 - 20.0F);
      var6.text(
         var21,
         "  ·  " + FontMetrics.ellipsize(var22, var23, 11.5F, var24, 0.0F),
         var16 + var20,
         var3 + 79.0F,
         11.5F,
         Icons.TEXT_3,
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      Translations.resume();
      var6.popAlpha();
      var6.pop();
      float var25 = this.in(var7, 4);
      var6.pushAlpha(var25);
      float var26 = var3 + 104.0F + 22.0F + 16.0F + (1.0F - var25) * 10.0F;
      this.field.render(var1, var9, var26, var8, var5 && var25 > 0.8F);
      var6.text(
         Fonts.REGULAR,
         "Leave it empty to use your Minecraft name.",
         var2 + var4 / 2.0F,
         var26 + 16.0F + 18.0F,
         11.5F,
         Icons.TEXT_3,
         FontStyle.CENTER,
         FontWeight.MIDDLE
      );
      var6.popAlpha();
   }

   @Override
   boolean mouseDown(float var1, float var2, int var3) {
      return this.field.mouseDown(var1, var2);
   }

   @Override
   void mouseDrag(float var1, float var2) {
      this.field.mouseDrag(var1, var2);
   }

   @Override
   void mouseUp(float var1, float var2) {
      this.field.mouseUp();
   }

   @Override
   boolean keyPressed(int var1) {
      return this.field.keyPressed(var1);
   }

   @Override
   boolean charTyped(int var1) {
      if (!this.field.focused() && !Character.isISOControl(var1) && !Character.isWhitespace(var1)) {
         this.field.focus();
      }

      return this.field.charTyped(var1);
   }

   @Override
   boolean capturesKeys() {
      return this.field.focused();
   }
}
