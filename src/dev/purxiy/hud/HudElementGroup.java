package dev.purxiy.hud;

import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;

public abstract class HudElementGroup extends HudModule {
   protected static final float HEIGHT = 20.0F;
   protected static final float PAD = 7.0F;
   protected static final float ICON = 10.0F;
   protected static final float VALUE = 10.0F;
   protected static final float LABEL = 8.5F;
   private String value = "";

   private String label;

   protected HudElementGroup(String var1, String var2, String var3, String var4, double var5, double var7) {
      super(var1, var2, var3, var4, var5, var7);
   }

   protected abstract String value(HudBounds var1);

   protected String sample() {
      return "0";
   }


   protected String label(HudBounds var1) {
      return null;
   }

   protected int valueColor(HudBounds var1) {
      return Icons.TEXT;
   }

   protected float extraWidth(HudBounds var1) {
      return 0.0F;
   }

   protected void renderExtra(Canvas var1, HudBounds var2, float var3, float var4) {
   }

   @Override
   protected boolean mergeable() {
      return true;
   }

   @Override
   protected float cornerRadius() {
      return 10.0F;
   }

   @Override
   protected void measure(HudBounds var1, float[] var2) {
      this.value = var1.live() ? this.value(var1) : this.sample();
      this.label = this.label(var1);
      float var3 = 22.0F + Fonts.SEMIBOLD.width(stable(this.value), 10.0F);
      if (this.label != null) {
         var3 += 3.5F + Fonts.MEDIUM.width(this.label, 8.5F);
      }

      float var4 = this.extraWidth(var1);
      if (var4 > 0.0F) {
         var3 += 7.0F + var4;
      }

      var2[0] = var3 + 7.0F;
      var2[1] = 20.0F;
   }

   @Override
   protected void render(Canvas var1, HudBounds var2) {
      float var3 = this.contentWidth();
      this.panel(var1, 0.0F, 0.0F, var3, 20.0F, 10.0F);
      float var4 = 10.0F;
      this.icon(var1, this.icon(), 12.0F, var4, 10.0F, accent());
      float var5 = 22.0F;
      float var6 = var5 + this.text(var1, Fonts.SEMIBOLD, this.value, var5, var4, 10.0F, this.valueColor(var2), FontStyle.LEFT, FontWeight.MIDDLE);
      float var7 = Fonts.SEMIBOLD.width(stable(this.value), 10.0F);
      var5 = 22.0F + var7;
      if (this.label != null) {
         var5 += 3.5F;
         var5 += this.text(var1, Fonts.MEDIUM, this.label, var5, var4 + 0.3F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      if (this.extraWidth(var2) > 0.0F) {
         this.renderExtra(var1, var2, var5 + 7.0F, 20.0F);
      }
   }

   public static String stable(String var0) {
      StringBuilder var1 = new StringBuilder(var0.length());

      for (int var2 = 0; var2 < var0.length(); var2++) {
         char var3 = var0.charAt(var2);
         var1.append(Character.isDigit(var3) ? '0' : var3);
      }

      return var1.toString();
   }
}
