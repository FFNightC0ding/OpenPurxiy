// Sourced By DevFFNightmare
package dev.purxiy.ui.config;

import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.Theme;

final class ConfigLibraryRenderer {
   private static final float HEIGHT = 40.0F;
   
   private ConfigLibraryRenderer.TypeZ current;
   
   private ConfigLibraryRenderer.TypeZ leaving;
   private float leftAt = -100.0F;
   private final ConfigLibraryRenderer8.TypeC button = new ConfigLibraryRenderer8.TypeC();

   void show(String var1, String var2, float var3) {
      this.show(var1, var2, (String)null, (Runnable)null, false, var3);
   }

   void warn(String var1, float var2) {
      this.show("circle-alert", var1, (String)null, (Runnable)null, true, var2);
   }

   void show(String var1, String var2,  String var3,  Runnable var4, boolean var5, float var6) {
      if (this.current != null) {
         this.leaving = this.current;
         this.leftAt = var6;
      }

      this.current = new ConfigLibraryRenderer.TypeZ(var1, var2, var3, var4, var5, var6, var3 != null ? 5.5F : 2.8F);
   }

   void dismiss(float var1) {
      if (this.current != null) {
         this.leaving = this.current;
         this.leftAt = var1;
         this.current = null;
      }
   }

   void render(DrawContext var1, float var2, float var3, boolean var4) {
      Canvas var5 = var1.canvas;
      float var6 = var1.time;
      if (this.leaving != null) {
         float var7 = (var6 - this.leftAt) * MotionStyle.MENU.speed() / 0.16F;
         if (var7 >= 1.0F) {
            this.leaving = null;
         } else {
            this.draw(var1, var5, this.leaving, var2, var3 + var7 * 10.0F, (1.0F - var7) * (1.0F - var7), false);
         }
      }

      ConfigLibraryRenderer.TypeZ var10 = this.current;
      if (var10 != null) {
         float var8 = (var6 - var10.born()) * MotionStyle.MENU.speed();
         if (var8 > var10.life()) {
            this.dismiss(var6);
         } else {
            float var9 = Easing.EMPHASIZED.apply(Math.min(1.0F, var8 / 0.4F));
            this.draw(var1, var5, var10, var2, var3 + (1.0F - var9) * 14.0F, var9, var4);
         }
      }
   }

   private void draw(DrawContext var1, Canvas var2, ConfigLibraryRenderer.TypeZ var3, float var4, float var5, float var6, boolean var7) {
      float var8 = var2.textWidth(Fonts.MEDIUM, var3.text(), 12.5F);
      float var9 = var3.action() != null ? ConfigLibraryRenderer8.TypeC.width(var2, (String)null, var3.action(), 28.0F) + 10.0F : 0.0F;
      float var10 = 50.0F + var8 + 18.0F + var9;
      float var11 = var4 - var10 / 2.0F;
      float var12 = var5 - 40.0F;
      var2.pushAlpha(var6);
      KeyLabel.surface(var2, var11, var12, var10, 40.0F, 20.0F, 0.9F, var3.warn() ? 0.0F : 0.35F);
      float var13 = var12 + 20.0F;
      int var14 = var3.warn() ? -29785 : Icons.MAGENTA;
      var2.circle(var11 + 14.0F + 13.0F, var13, 13.0F, BlendMode.withAlpha(var14, 0.16F));
      var2.icon(var3.icon(), var11 + 14.0F + 13.0F, var13, 13.5F, var3.warn() ? -29785 : Theme.tint(-1783297));
      var2.text(Fonts.MEDIUM, var3.text(), var11 + 14.0F + 26.0F + 10.0F, var13, 12.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      if (var3.action() != null) {
         this.button
            .render(
               var1, var11 + var10 - 8.0F - (var9 - 10.0F), var13, 28.0F, (String)null, var3.action(), ConfigLibraryRenderer8.TypeI.QUIET, var7 && var6 > 0.9F
            );
      }

      float var15 = Math.min(1.0F, (var1.time - var3.born()) * MotionStyle.MENU.speed() / var3.life());
      float var16 = (var10 - 40.0F) * (1.0F - var15);
      if (var16 > 1.0F) {
         var2.roundRect(var11 + 20.0F, var12 + 40.0F - 2.5F, var16, 1.5F, 0.75F, BlendMode.withAlpha(var14, 0.45F));
      }

      var2.popAlpha();
   }

   void act(float var1) {
      ConfigLibraryRenderer.TypeZ var2 = this.current;
      if (var2 != null && var2.run() != null) {
         var2.run().run();
         this.dismiss(var1);
      }
   }

   boolean mouseDown(float var1, float var2, float var3) {
      ConfigLibraryRenderer.TypeZ var4 = this.current;
      if (var4 != null && var4.run() != null && this.button.hit(var1, var2)) {
         this.button.press();
         var4.run().run();
         this.dismiss(var3);
         return true;
      } else {
         return false;
      }
   }

   private record TypeZ(String icon, String text,  String action,  Runnable run, boolean warn, float born, float life) {
   }
}
