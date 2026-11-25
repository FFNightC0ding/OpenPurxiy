package dev.purxiy.onboarding;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.render.Canvas;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.widget.Divider;
import dev.purxiy.ui.widget.KeybindButton;
import dev.purxiy.ui.widget.Spacer;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;

abstract class OnboardingPage extends OnboardingStep {
   private static final float PAD = 6.0F;
   final List<OnboardingPage.PageBlock> rows = new ArrayList<>();

   OnboardingPage(WelcomeScreen var1, String var2, String var3, String var4) {
      super(var1, var2, var3, var4);
   }

   @Override
   float height(float var1) {
      return this.rows.size() * 62.0F + 12.0F;
   }

   @Override
   void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      Canvas var6 = var1.canvas;
      float var7 = var1.time;
      float var8 = this.in(var7, 3);
      var6.push();
      var6.translate(0.0F, (1.0F - var8) * 14.0F);
      var6.pushAlpha(var8);
      KeyLabel.surface(var6, var2, var3, var4, this.height(var4), 22.0F, 0.3F, 0.12F);

      for (int var9 = 0; var9 < this.rows.size(); var9++) {
         OnboardingPage.PageBlock var10 = this.rows.get(var9);
         float var11 = this.in(var7, 4 + var9);
         var10.x = var2;
         var10.w = var4;
         var10.y = var3 + 6.0F + var9 * 62.0F;
         var6.pushAlpha(var11);
         boolean var12 = var5 && var8 > 0.8F && var11 > 0.8F;
         boolean var13 = var12 && var10.wholeRow() && var1.hover(var10, var2, var10.y, var4, 62.0F, DrawContext.Cursor.HAND);
         float var14 = var10.hover.target(var13 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         OnboardingState.option(var6, var2, var10.y + (1.0F - var11) * 6.0F, var4, var10.icon, var10.label, var10.description, var10.reserve(), var14);
         var10.control(var1, var2 + var4 - 18.0F, var10.y + 31.0F + (1.0F - var11) * 6.0F, var13, var12);
         if (var9 > 0) {
            OnboardingState.divider(var6, var2, var10.y, var4);
         }

         var6.popAlpha();
      }

      var6.popAlpha();
      var6.pop();
   }

   @Override
   boolean mouseDown(float var1, float var2, int var3) {
      boolean var4 = false;

      for (OnboardingPage.PageBlock var6 : this.rows) {
         boolean var7 = var1 >= var6.x && var1 <= var6.x + var6.w && var2 >= var6.y && var2 <= var6.y + 62.0F;
         var4 |= var6.press(var1, var2, var7);
      }

      return var4;
   }

   @Override
   boolean keyPressed(int var1) {
      for (OnboardingPage.PageBlock var3 : this.rows) {
         if (var3.key(var1)) {
            return true;
         }
      }

      return false;
   }

   @Override
   boolean capturesKeys() {
      for (OnboardingPage.PageBlock var2 : this.rows) {
         if (var2.captures()) {
            return true;
         }
      }

      return false;
   }

   static final class SettingsBlock extends OnboardingPage.PageBlock {
      private final ModeSetting setting;
      private final Divider control;

      SettingsBlock(String var1, String var2, String var3, ModeSetting var4) {
         super(var1, var2, var3);
         this.setting = var4;
         this.control = new Divider(var4);
      }

      @Override
      float reserve() {
         return this.control.width();
      }

      @Override
      void control(DrawContext var1, float var2, float var3, boolean var4, boolean var5) {
         boolean var6 = var1.masked();
         var1.mask(!var5 || var6);
         this.control.render(var1, var2, var3);
         var1.mask(var6);
      }

      @Override
      boolean press(float var1, float var2, boolean var3) {
         return var3 && this.control.mouseDown(var1, var2);
      }

      ModeSetting setting() {
         return this.setting;
      }
   }

   static final class KeybindBlock extends OnboardingPage.PageBlock {
      private final KeybindButton button;

      KeybindBlock(String var1, String var2, String var3, IntSupplier var4, IntConsumer var5) {
         super(var1, var2, var3);
         this.button = new KeybindButton(var4, var5);
      }

      @Override
      float reserve() {
         return 110.0F;
      }

      @Override
      void control(DrawContext var1, float var2, float var3, boolean var4, boolean var5) {
         this.button.render(var1, var2, var3);
      }

      @Override
      boolean press(float var1, float var2, boolean var3) {
         return this.button.mouseDown(var1, var2);
      }

      @Override
      boolean key(int var1) {
         return this.button.keyPressed(var1);
      }

      @Override
      boolean captures() {
         return this.button.listening();
      }
   }

   abstract static class PageBlock {
      final String icon;
      final String label;
      final String description;
      final Animator hover = Animator.smooth(0.0F);
      float x;
      float y;
      float w;

      PageBlock(String var1, String var2, String var3) {
         this.icon = var1;
         this.label = var2;
         this.description = var3;
      }

      abstract float reserve();

      abstract void control(DrawContext var1, float var2, float var3, boolean var4, boolean var5);

      boolean wholeRow() {
         return false;
      }

      boolean press(float var1, float var2, boolean var3) {
         return false;
      }

      boolean key(int var1) {
         return false;
      }

      boolean captures() {
         return false;
      }
   }

   static final class ToggleBlock extends OnboardingPage.PageBlock {
      private final BooleanSupplier value;
      private final Consumer<Boolean> set;
      private final Spacer toggle;

      ToggleBlock(String var1, String var2, String var3, BooleanSupplier var4, Consumer<Boolean> var5) {
         super(var1, var2, var3);
         this.value = var4;
         this.set = var5;
         this.toggle = new Spacer(var4.getAsBoolean());
      }

      @Override
      float reserve() {
         return 46.0F;
      }

      @Override
      boolean wholeRow() {
         return true;
      }

      @Override
      void control(DrawContext var1, float var2, float var3, boolean var4, boolean var5) {
         this.toggle.render(var1, var2 - 46.0F, var3 - 13.0F, 46.0F, 26.0F, this.value.getAsBoolean(), var4, false);
      }

      @Override
      boolean press(float var1, float var2, boolean var3) {
         if (!var3) {
            return false;
         } else {
            boolean var4 = !this.value.getAsBoolean();
            this.set.accept(var4);
            SoundRequest.play(var4 ? SoundEvent.TOGGLE_ON : SoundEvent.TOGGLE_OFF);
            return true;
         }
      }
   }
}
