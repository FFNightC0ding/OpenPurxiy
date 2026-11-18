// Sourced By DevFFNightmare
package dev.purxiy.ui.config;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.List;

final class ConfigLibraryRenderer6 {
   private static final float WIDTH = 206.0F;
   private static final float ROW = 34.0F;
   private static final float PAD = 6.0F;
   private final List<ConfigLibraryRenderer6.TypeT> items;
   private final Animator open = new Animator(0.0F, 420.0F, 32.0F);
   private final Animator highlight = Animator.snappy(0.0F);
   private final Object[] keys;
   private final float born;
   private final float ax;
   private final float ay;
   private boolean closing;
   private boolean fromRight;
   private boolean fromBelow;
   private int hovered = -1;
   private float x;
   private float y;
   private float h;

   ConfigLibraryRenderer6(float var1, float var2, List<ConfigLibraryRenderer6.TypeT> var3, float var4) {
      this.ax = var1;
      this.ay = var2;
      this.items = var3;
      this.born = var4;
      this.keys = new Object[var3.size()];

      for (int var5 = 0; var5 < this.keys.length; var5++) {
         this.keys[var5] = new Object();
      }

      this.open.target(1.0F);
      SoundRequest.play(SoundEvent.EXPAND, 1.15F);
   }

   void close() {
      if (!this.closing) {
         this.closing = true;
         this.open.target(0.0F);
      }
   }

   boolean closing() {
      return this.closing;
   }

   boolean finished() {
      return this.closing && this.open.value() < 0.02F;
   }

   void render(DrawContext var1) {
      Canvas var2 = var1.canvas;
      float var3 = Math.max(0.0F, Math.min(1.0F, this.open.update(MotionStyle.PANEL.dt(var1.dt))));
      this.h = this.items.size() * 34.0F + 12.0F;
      this.fromRight = this.ax + 206.0F > var2.width() - 12.0F;
      this.fromBelow = this.ay + this.h > var2.height() - 12.0F;
      this.x = this.fromRight ? this.ax - 206.0F : this.ax;
      this.y = this.fromBelow ? this.ay - this.h : this.ay;
      float var4 = this.fromRight ? this.x + 206.0F : this.x;
      float var5 = this.fromBelow ? this.y + this.h : this.y;
      var2.push();
      var2.scaleAround(0.9F + 0.1F * Easing.CUBIC_OUT.apply(var3), var4, var5);
      var2.pushAlpha(var3);
      var2.shadow(this.x, this.y, 206.0F, this.h, 14.0F, 26.0F, 0.0F, 0.0F, 8.0F, -2013265920);
      var2.roundRect(this.x, this.y, 206.0F, this.h, 14.0F, Theme.tint(-183234522));
      var2.roundRectCorners(this.x, this.y, 206.0F, this.h, 14.0F, 251658239, 150994943, 16777215, 50331647);
      var2.roundRectOutline(this.x, this.y, 206.0F, this.h, 14.0F, 1.0F, 620756991);
      int var6 = -1;

      for (int var7 = 0; var7 < this.items.size(); var7++) {
         float var8 = this.y + 6.0F + var7 * 34.0F;
         if (!this.closing && var3 > 0.8F && var1.hover(this.keys[var7], this.x + 6.0F, var8, 194.0F, 34.0F, DrawContext.TypeE.HAND)) {
            var6 = var7;
         }
      }

      if (var6 != this.hovered && var6 >= 0 && this.hovered < 0) {
         this.highlight.snap(var6 * 34.0F);
      }

      this.hovered = var6;
      if (this.hovered >= 0) {
         float var14 = this.highlight.target(this.hovered * 34.0F).update(MotionStyle.HOVER.dt(var1.dt));
         ConfigLibraryRenderer6.TypeT var16 = this.items.get(this.hovered);
         var2.roundRect(this.x + 6.0F, this.y + 6.0F + var14, 194.0F, 34.0F, 9.0F, var16.danger() ? BlendMode.withAlpha(-29785, 0.12F) : 352321535);
      }

      for (int var15 = 0; var15 < this.items.size(); var15++) {
         ConfigLibraryRenderer6.TypeT var17 = this.items.get(var15);
         float var9 = (var1.time - this.born) * MotionStyle.CASCADE.speed() - var15 * 0.022F;
         float var10 = Easing.EMPHASIZED.apply(Math.max(0.0F, Math.min(1.0F, var9 / 0.28F)));
         float var11 = this.y + 6.0F + var15 * 34.0F + 17.0F + (1.0F - var10) * 5.0F;
         boolean var12 = var15 == this.hovered;
         int var13 = var17.danger() ? -29785 : (var12 ? Icons.TEXT : Icons.TEXT_2);
         var2.pushAlpha(var10);
         var2.icon(var17.icon(), this.x + 6.0F + 18.0F, var11, 14.0F, var17.danger() ? -29785 : (var12 ? Icons.TEXT : Icons.TEXT_3));
         var2.text(Fonts.MEDIUM, var17.label(), this.x + 6.0F + 36.0F, var11, 12.5F, var13, FontStyle.LEFT, FontWeight.MIDDLE);
         if (var17.hint() != null) {
            var2.text(Fonts.MEDIUM, var17.hint(), this.x + 206.0F - 6.0F - 12.0F, var11, 10.5F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.MIDDLE);
         }

         var2.popAlpha();
      }

      var2.popAlpha();
      var2.pop();
   }

   boolean contains(float var1, float var2) {
      return var1 >= this.x && var1 <= this.x + 206.0F && var2 >= this.y && var2 <= this.y + this.h;
   }

   void mouseDown(float var1, float var2) {
      if (!this.closing) {
         if (this.contains(var1, var2)) {
            int var3 = (int)((var2 - this.y - 6.0F) / 34.0F);
            if (var3 >= 0 && var3 < this.items.size()) {
               this.close();
               this.items.get(var3).run().run();
            }
         } else {
            this.close();
            SoundRequest.play(SoundEvent.COLLAPSE, 1.1F);
         }
      }
   }

   record TypeT(String icon, String label,  String hint, boolean danger, Runnable run) {
      TypeT(String var1, String var2, Runnable var3) {
         this(var1, var2, (String)null, false, var3);
      }
   }
}
