package dev.purxiy.modules.keystrokes;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ColorSetting;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudElement;
import dev.purxiy.hud.HudModule;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import net.minecraft.class_304;
import net.minecraft.class_315;

public final class KeystrokesHud extends HudModule {
   private static final float d = 22.0F;
   private static final float v = 3.0F;
   private static final float b = 11.0F;
   private final BooleanSetting O = this.bool("mouse", "Mouse buttons", null, true);
   private final BooleanSetting e = this.bool("space", "Space bar", null, true);
   private final BooleanSetting G = this.bool("cps", "Show CPS", "Clicks per second on the mouse buttons", true);
   private final ColorSetting y = this.color("accent", "Accent", "Colour of pressed keys", -7860231, false).followsAccent();
   private final Animator[] K = new Animator[7];
   public static boolean N;
   public static boolean a;

   public KeystrokesHud() {
      super("keystrokes", "Keystrokes", "Your movement keys and clicks, live", "keyboard", 0.0, 0.45);

      for (int var1 = 0; var1 < this.K.length; var1++) {
         this.K[var1] = Animator.snappy(0.0F);
      }

      this.background.set(true);
   }

   protected void measure(HudBounds var1, float[] var2) {
      float var3 = 47.0F;
      if (this.O.enabled()) {
         var3 += 25.0F;
      }

      if (this.e.enabled()) {
         var3 += 14.0F;
      }

      var2[0] = 72.0F;
      var2[1] = var3;
   }

   private boolean s(HudBounds var1, int var2, long var3) {
      class_315 var5 = var1.mc.field_1690;
      if (!var1.live()) {
         float var16 = var1.time % 4.0F;
         long var7 = 17184875346384L;
         int var10 = var7 < 0L ? -1 : 1;

         return switch (var2) {
            case 0 -> var16 < 1.2F;
            case 1 -> var16 > 1.4F && var16 < 1.9F;
            default -> false;
            case 4 -> {
               var10 = (int)(var1.time * 6.0F);
               long var11 = 17184875346384L;
               int var14 = var11 < 0L ? -1 : 1;
               var10 %= 4;
               long var14 = 17184875346384L;
               int var141 = var14 < 0L ? -1 : 1;
               yield var10 == 0;
            }
         };
      } else {
         class_304 var6 = switch (var2) {
            case 0 -> var5.field_1894;
            case 1 -> var5.field_1913;
            case 2 -> var5.field_1881;
            case 3 -> var5.field_1849;
            case 4 -> var5.field_1886;
            case 5 -> var5.field_1904;
            default -> var5.field_1903;
         };
         return var6.method_1434() && var1.mc.field_1755 == null;
      }
   }

   protected void render(Canvas var1, HudBounds var2) {
      long var3 = 136231536068608L;
      long var5 = var3 ^ 26536655489593L;
      long var7 = var3 ^ 127851683869137L;

      for (int var9 = 0; var9 < this.K.length; var9++) {
         this.K[var9].target(this.s(var2, var9, var7) ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var2.dt));
      }

      float var20 = 0.0F;
      this.p(var1, 0, 25.0F, var5, var20, 22.0F, 22.0F, "W", null);
      var20 += 25.0F;
      this.p(var1, 1, 0.0F, var5, var20, 22.0F, 22.0F, "A", null);
      this.p(var1, 2, 25.0F, var5, var20, 22.0F, 22.0F, "S", null);
      this.p(var1, 3, 50.0F, var5, var20, 22.0F, 22.0F, "D", null);
      float var10 = var20 + 25.0F;
      float var11 = 72.0F;
      if (this.O.enabled()) {
         float var12 = (var11 - 3.0F) / 2.0F;
         boolean var13 = this.G.enabled();
         String var14 = var13 ? (var2.live() ? HudElement.cps(0) : 7) + " CPS" : null;
         String var15 = "LMB";
         float var16 = 22.0F;
         this.p(var1, 4, 0.0F, var5, var10, var12, var16, var15, var14);
         float var19 = var12 + 3.0F;
         var14 = var13 ? (var2.live() ? HudElement.cps(1) : 2) + " CPS" : null;
         var15 = "RMB";
         var16 = 22.0F;
         this.p(var1, 5, var19, var5, var10, var12, var16, var15, var14);
         var10 += 25.0F;
      }

      if (this.e.enabled()) {
         this.p(var1, 6, 0.0F, var5, var10, var11, 11.0F, null, null);
      }
   }

   private void p(Canvas var1, int var2, float var3, long var4, float var6, float var7, float var8, String var9, String var10) {
      float var11 = Math.max(0.0F, Math.min(1.0F, this.K[var2].value()));
      float var12 = 1.0F - 0.06F * var11;
      var1.push();
      var1.scaleAround(var12, var3 + var7 / 2.0F, var6 + var8 / 2.0F);
      this.panel(var1, var3, var6, var7, var8, 6.0F);
      if (var11 > 0.01F) {
         int var13 = this.y.argb();
         var1.glow(var3, var6, var7, var8, 6.0F, 10.0F, BlendMode.withAlpha(var13, 0.45F * var11));
         var1.pushAlpha(var11);
         var1.roundRectGradient(var3, var6, var7, var8, 6.0F, 135.0F, BlendMode.shade(var13, -0.15F), BlendMode.lerp(var13, Icons.MAGENTA, 0.5F));
         var1.roundRectCorners(var3, var6, var7, var8, 6.0F, 872415231, 452984831, 16777215, 16777215);
         var1.popAlpha();
      }

      int var17 = BlendMode.lerp(Icons.TEXT, -1, var11);
      if (var9 == null) {
         long var14 = 109695272223289L;
         int var16 = var14 < 0L ? -1 : 1;
         var1.roundRect(var3 + var7 / 2.0F - 10.0F, var6 + var8 / 2.0F - 1.0F, 20.0F, 2.0F, 1.0F, BlendMode.lerp(-2130706433, -1, var11));
      } else if (var10 == null) {
         this.text(var1, Fonts.SEMIBOLD, var9, var3 + var7 / 2.0F, var6 + var8 / 2.0F, 9.5F, var17, FontStyle.CENTER, FontWeight.MIDDLE);
         long var18 = 109695272223288L;
         int var19 = var18 < 0L ? -1 : 1;
      } else {
         this.text(var1, Fonts.SEMIBOLD, var9, var3 + var7 / 2.0F, var6 + var8 / 2.0F - 3.5F, 8.5F, var17, FontStyle.CENTER, FontWeight.MIDDLE);
         this.text(
            var1,
            Fonts.MEDIUM,
            var10,
            var3 + var7 / 2.0F,
            var6 + var8 / 2.0F + 5.0F,
            6.5F,
            BlendMode.lerp(Icons.TEXT_2, -419430401, var11),
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
      }

      var1.pop();
   }
}
