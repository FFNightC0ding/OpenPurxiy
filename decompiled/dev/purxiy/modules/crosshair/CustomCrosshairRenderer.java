package dev.purxiy.modules.crosshair;

import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.DrawContext.TypeE;

public final class CustomCrosshairRenderer {
   public static final float W = 282.0F;
   private static final String[] Q = new String[]{"Focus", "Signature", "Supernova"};
   private final CustomCrosshairModule N;
   private final Object[] V = new Object[10];
   private float L;
   private float x;
   private float I;
   private float A;
   private float n;
   private boolean G;
   private static final String[] b = new String[11];

   public CustomCrosshairRenderer(CustomCrosshairModule var1) {
      this.N = var1;

      for (int var2 = 0; var2 < this.V.length; var2++) {
         this.V[var2] = new Object();
      }
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.L = var2;
      this.x = var3;
      this.I = var4;
      this.G = var5;
      this.A = this.A + MotionStyle.HOVER.dt(var1.dt) * this.N.x.floatValue();
      this.n = Math.max(0.0F, this.n - var1.dt / 0.32F);
      Canvas var6 = var1.canvas;
      var6.text(Fonts.MEDIUM, "Choose your signature", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var6.text(Fonts.REGULAR, "Six shapes. A perfectly steady centre.", var2, var3 + 30.0F, 11.5F, Icons.TEXT_2);
      float var7 = (var4 - 40.0F) / 6.0F;

      for (int var8 = 0; var8 < 6; var8++) {
         String var9 = (String)this.N.k.modes().get(var8);
         float var10 = var2 + var8 * (var7 + 8.0F);
         float var11 = var3 + 51.0F;
         boolean var12 = this.N.k.is(var9);
         boolean var13 = var5 && var1.hover(this.V[var8], var10, var11, var7, 75.0F, TypeE.HAND);
         var6.roundRect(var10, var11, var7, 75.0F, 10.0F, var12 ? Theme.tint(-13623231) : (var13 ? Theme.tint(-14542287) : Theme.tint(-15725540)));
         var6.roundRectOutline(var10, var11, var7, 75.0F, 10.0F, 1.0F, var12 ? Theme.tint(-4222985) : Theme.tint(633708021));
         var6.pushClip(var10 + 2.0F, var11 + 2.0F, var7 - 4.0F, 48.0F);
         CustomCrosshairRenderer3.draw(
            var6, this.N, var9, var10 + var7 / 2.0F, var11 + 27.0F, 0.95F / this.N.b.floatValue(), 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, false
         );
         var6.popClip();
         var6.text(Fonts.MEDIUM, var9, var10 + var7 / 2.0F, var11 + 59.0F, 10.5F, var12 ? Icons.TEXT : Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
      }

      float var16 = var3 + 137.0F;
      float var17 = (var4 - 8.0F) / 2.0F;
      boolean var18 = var5 && var1.hover(this.V[9], var2, var16, var4, 82.0F, TypeE.HAND);

      for (int var19 = 0; var19 < 2; var19++) {
         float var21 = var2 + var19 * (var17 + 8.0F);
         var6.roundRectGradient(
            var21,
            var16,
            var17,
            82.0F,
            10.0F,
            90.0F,
            var19 == 0 ? Theme.tint(-15133907) : Theme.tint(-1712659),
            var19 == 0 ? Theme.tint(-16251376) : Theme.tint(-4804662)
         );
         var6.pushClip(var21, var16, var17, 82.0F, 10.0F);
         CustomCrosshairRenderer3.draw(
            var6,
            this.N,
            (String)this.N.k.get(),
            var21 + var17 / 2.0F,
            var16 + 43.0F,
            1.7F,
            this.A,
            0.0F,
            this.n,
            var18 ? 1.0F : 0.0F,
            this.n,
            1.0F - this.n,
            !this.N.d.is("Off")
         );
         var6.popClip();
         var6.text(Fonts.MEDIUM, var19 == 0 ? "NIGHT" : "DAY", var21 + 10.0F, var16 + 9.0F, 8.5F, var19 == 0 ? Icons.TEXT_3 : Theme.tint(-10398352));
      }

      var6.text(Fonts.REGULAR, "Click preview to try a hit · quick presets below", var2, var3 + 227.0F, 10.5F, Icons.TEXT_3);
      float var20 = (var4 - 16.0F) / 3.0F;

      for (int var22 = 0; var22 < 3; var22++) {
         float var23 = var2 + var22 * (var20 + 8.0F);
         float var14 = var3 + 246.0F;
         boolean var15 = var5 && var1.hover(this.V[var22 + 6], var23, var14, var20, 27.0F, TypeE.HAND);
         var6.roundRect(var23, var14, var20, 27.0F, 8.0F, var15 ? Theme.tint(901155562) : Theme.tint(313953002));
         var6.text(
            Fonts.MEDIUM,
            Q[var22],
            var23 + var20 / 2.0F,
            var14 + 13.5F,
            11.0F,
            BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var15 ? 1.0F : 0.0F),
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
      }
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      if (this.G && var3 == 0 && !(var1 < this.L) && !(var1 >= this.L + this.I)) {
         float var4 = var2 - this.x;
         float var5 = var1 - this.L;
         if (var4 >= 51.0F && var4 < 126.0F) {
            float var8 = (this.I + 8.0F) / 6.0F;
            int var9 = (int)(var5 / var8);
            if (var9 < 6 && var5 - var9 * var8 < var8 - 8.0F) {
               this.N.k.select(var9);
               UiAudioPolicy.play("CLICK");
               return true;
            }
         } else {
            if (var4 >= 137.0F && var4 < 219.0F) {
               this.n = 1.0F;
               this.N.playHitSound();
               return true;
            }

            if (var4 >= 246.0F && var4 < 273.0F) {
               float var6 = (this.I + 8.0F) / 3.0F;
               int var7 = (int)(var5 / var6);
               if (var7 < 3 && var5 - var7 * var6 < var6 - 8.0F) {
                  this.N.applyPreset(var7);
                  UiAudioPolicy.play("SELECT");
                  return true;
               }
            }
         }

         return false;
      } else {
         return false;
      }
   }
}
