// Sourced By DevFFNightmare
package dev.purxiy.onboarding;

import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.ui.DrawContext;

abstract class OnboardingStep {
   final WelcomeScreen screen;
   final String eyebrow;
   final String title;
   final String subtitle;
   float enteredAt;

   OnboardingStep(WelcomeScreen var1, String var2, String var3, String var4) {
      this.screen = var1;
      this.eyebrow = var2;
      this.title = var3;
      this.subtitle = var4;
   }

   void enter(float var1) {
      this.enteredAt = var1;
   }

   void leave() {
   }

   float in(float var1, int var2) {
      float var3 = (var1 - this.enteredAt - MotionStyle.stagger(0.055F) * var2) * MotionStyle.CASCADE.speed() / 0.55F;
      return Easing.EMPHASIZED.apply(OnboardingState.clamp01(var3));
   }

   boolean full() {
      return false;
   }

   String title() {
      return this.title;
   }

   String nextLabel() {
      return "Continue";
   }

   boolean ready() {
      return true;
   }

   float height(float var1) {
      return 0.0F;
   }

   abstract void render(DrawContext var1, float var2, float var3, float var4, boolean var5);

   boolean mouseDown(float var1, float var2, int var3) {
      return false;
   }

   void mouseDrag(float var1, float var2) {
   }

   void mouseUp(float var1, float var2) {
   }

   boolean scroll(float var1, float var2, double var3) {
      return false;
   }

   boolean keyPressed(int var1) {
      return false;
   }

   boolean charTyped(int var1) {
      return false;
   }

   boolean capturesKeys() {
      return false;
   }
}
