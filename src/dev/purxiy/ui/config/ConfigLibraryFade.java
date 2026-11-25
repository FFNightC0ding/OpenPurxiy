package dev.purxiy.ui.config;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.ui.DrawContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

final class ConfigLibraryFade {
   static final float GAP = 16.0F;
   private static final float MIN_CARD = 280.0F;
   private static final float ENTER = 0.42F;
   private final Map<ConfigLibraryStats, ConfigLibraryCover> cards = new HashMap<>();
   private final List<ConfigLibraryCover> current = new ArrayList<>();
   private final Animator scroll = Animator.smooth(0.0F);
   private float scrollTarget;
   private float maxScroll;
   private float enterTime = -100.0F;
   private float x;
   private float y;
   private float w;
   private float h;
   private int columns = 1;
   private float cardW;

   private ConfigLibraryCover hidden;

   private ConfigLibraryCover pressed;
   private float lead;

   ConfigLibraryCover card(ConfigLibraryStats var1) {
      return this.cards.computeIfAbsent(var1, ConfigLibraryCover::new);
   }


   ConfigLibraryCover existing(ConfigLibraryStats var1) {
      return this.cards.get(var1);
   }

   List<ConfigLibraryCover> shown() {
      return this.current;
   }

   float[] cell(int var1) {
      float var2 = this.x + var1 % this.columns * (this.cardW + 16.0F) + this.cardW / 2.0F;
      float var3 = this.y + this.lead + var1 / this.columns * 210.0F + 97.0F - this.scroll.value();
      return new float[]{var2, var3};
   }

   void enter(float var1) {
      this.enterTime = var1;
      this.scroll.snap(0.0F);
      this.scrollTarget = 0.0F;
   }

   void hide( ConfigLibraryCover var1) {
      this.hidden = var1;
   }

   float offset() {
      return this.scroll.value();
   }

   void render(DrawContext var1, List<ConfigLibraryStats> var2, float var3, float var4, float var5, float var6, float var7, float var8, boolean var9) {
      this.x = var3;
      this.y = var4;
      this.w = var5;
      this.h = var6;
      this.lead = var7;
      Canvas var10 = var1.canvas;
      float var11 = var1.time;
      this.current.clear();

      for (ConfigLibraryStats var13 : var2) {
         this.current.add(this.card(var13));
      }

      this.columns = Math.max(1, Math.min(5, (int)((var5 + 16.0F) / 296.0F)));
      this.cardW = (var5 - 16.0F * (this.columns - 1)) / this.columns;
      int var25 = (this.current.size() + this.columns - 1) / this.columns;
      float var26 = var7 + var25 * 210.0F - 16.0F + 14.0F;
      this.maxScroll = Math.max(0.0F, var26 - var6);
      this.scrollTarget = Math.max(0.0F, Math.min(this.maxScroll, this.scrollTarget));
      float var14 = this.scroll.target(this.scrollTarget).update(MotionStyle.MENU.dt(var1.dt));
      float var15 = (var11 - this.enterTime) * MotionStyle.CASCADE.speed();
      var10.pushClip(var3 - 30.0F, var4 - 14.0F, var5 + 60.0F, var6 + 14.0F);

      for (int var16 = 0; var16 < this.current.size(); var16++) {
         ConfigLibraryCover var17 = this.current.get(var16);
         float var18 = var16 % this.columns * (this.cardW + 16.0F);
         float var19 = var7 + var16 / this.columns * 210.0F;
         if (!var17.placed || Math.abs(var17.w - this.cardW) > 0.5F && var17.w > 0.0F) {
            var17.lx.snap(var18);
            var17.ly.snap(var19);
            var17.placed = true;
         }

         float var20 = var3 + var17.lx.target(var18).update(MotionStyle.MENU.dt(var1.dt));
         float var21 = var4 + var17.ly.target(var19).update(MotionStyle.MENU.dt(var1.dt)) - var14;
         float var22 = MotionStyle.reduced() ? 0.0F : var16 * 0.03F;
         float var23 = Easing.EMPHASIZED.apply(Math.max(0.0F, Math.min(1.0F, (var15 - var22) / 0.42F))) * var8;
         float var24 = this.fade(var21);
         if (var17 == this.hidden) {
            var17.x = var20;
            var17.y = var21;
            var17.w = this.cardW;
            var17.shown = false;
         } else if (!(var23 * var24 <= 0.005F) && !(var21 > var4 + var6 + 20.0F) && !(var21 + 194.0F < var4 - 20.0F)) {
            var17.entered = MotionStyle.reduced() ? 1.0F : Math.max(0.0F, Math.min(1.0F, (var15 - var22 - 0.12F) / 1.1F));
            var10.pushAlpha(var24);
            var17.render(var1, var20, var21, this.cardW, var23, var9 && var24 > 0.6F && !var1.masked());
            var10.popAlpha();
         } else {
            var17.x = var20;
            var17.y = var21;
            var17.w = this.cardW;
            var17.shown = false;
         }
      }

      var10.popClip();
      if (this.maxScroll > 1.0F) {
         float var27 = var6 - 20.0F;
         float var28 = Math.max(36.0F, var27 * var6 / var26);
         float var29 = var4 + 10.0F + (var27 - var28) * (var14 / this.maxScroll);
         float var30 = var3 + var5 + 14.0F;
         var10.roundRect(var30 - 1.5F, var29, 3.0F, var28, 1.5F, BlendMode.withAlpha(-1, 0.18F * var8));
      }
   }

   private float fade(float var1) {
      float var2 = var1 + 194.0F - this.y + 14.0F;
      float var3 = this.y + this.h - var1;
      return smooth(Math.min(var2 / 70.0F, (var3 - 30.0F) / 164.0F));
   }

   boolean inside(float var1, float var2) {
      return var1 >= this.x - 6.0F && var1 <= this.x + this.w + 6.0F && var2 >= this.y - 6.0F && var2 <= this.y + this.h + 6.0F;
   }


   ConfigLibraryCover cardAt(float var1, float var2) {
      if (!this.inside(var1, var2)) {
         return null;
      } else {
         for (ConfigLibraryCover var4 : this.current) {
            if (var4 != this.hidden && var4.contains(var1, var2)) {
               return var4;
            }
         }

         return null;
      }
   }

   void press(ConfigLibraryCover var1) {
      this.pressed = var1;
      var1.pressed = true;
   }


   ConfigLibraryCover release(float var1, float var2) {
      ConfigLibraryCover var3 = this.pressed;
      this.pressed = null;
      if (var3 == null) {
         return null;
      } else {
         var3.pressed = false;
         return var3.contains(var1, var2) ? var3 : null;
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

   void reveal(ConfigLibraryCover var1) {
      float var2 = var1.ly.target();
      float var3 = this.maxScroll;
      if (var2 - this.scrollTarget < 0.0F) {
         this.scrollTarget = Math.max(0.0F, var2 - 8.0F);
      } else if (var2 + 194.0F - this.scrollTarget > this.h) {
         this.scrollTarget = Math.min(var3, var2 + 194.0F - this.h + 8.0F);
      }
   }

   void scrollTo(float var1) {
      this.scrollTarget = var1;
      this.scroll.snap(Math.max(0.0F, Math.min(this.maxScroll, var1)));
   }

   void prune(List<ConfigLibraryStats> var1) {
      this.cards.keySet().retainAll(var1);
   }

   private static float smooth(float var0) {
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }
}
