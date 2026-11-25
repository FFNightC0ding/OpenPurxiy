package dev.purxiy.modules.watermark;

import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudElement;
import dev.purxiy.hud.HudElementGroup;
import dev.purxiy.hud.HudModule;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.texture.TextureLoader;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;

public final class WatermarkHud extends HudModule {
   private static final float r = 24.0F;
   private static final float x = 8.0F;
   private static final float v = 18.0F;
   private static final float a = 6.2F;
   private TextureLoader y;
   private TextureLoader A;
   private final ModeSetting q = this.mode("style", "Style", null, "Mark & name", new String[]{"Mark & name", "Mark only"});
   private final BooleanSetting k = this.bool("fps", "Show FPS", "Frames per second after the name", false);
   public static boolean z;
   public static int u = 0;

   public WatermarkHud() {
      super("watermark", "Watermark", "The Purxiy mark in your corner", "sparkles", 0.0, 0.0);
      this.enabledByDefault();
   }

   private void h(short var1, char var2, int var3) {
      if (this.y == null) {
         this.y = TextureLoader.load("textures/brand/mark.png");
         this.A = TextureLoader.load("textures/brand/wordmark.png");
      }
   }

   protected boolean mergeable() {
      return true;
   }

   protected float cornerRadius() {
      return 12.0F;
   }

   private boolean W(long var1) {
      return this.q.is("Mark & name");
   }

   private String K(long var1) {
      return HudElement.fps() + " fps";
   }

   protected void measure(HudBounds var1, float[] var2) {
      long var3 = 109126158163329L;
      long var5 = var3 ^ 115416667079566L;
      long var7 = var3 ^ 110374505344520L;
      long var9 = var3 ^ 51347646534397L;
      int var11 = (int)((var3 ^ 51347646534397L) >>> 48);
      int var12 = (int)((var3 ^ 51347646534397L) << 16 >>> 48);
      int var13 = (int)(var9 << 32 >>> 32);
      this.h((short)var11, (char)var12, var13);
      float var14 = 8.0F + 18.0F * this.y.aspect();
      if (this.W(var7)) {
         var14 += 7.0F + 6.2F * this.A.aspect();
      }

      if (this.k.enabled()) {
         var14 += 13.0F + Fonts.MEDIUM.width(HudElementGroup.stable(this.K(var5)), 8.5F);
      }

      var2[0] = var14 + 8.0F;
      var2[1] = 24.0F;
   }

   protected void render(Canvas var1, HudBounds var2) {
      long var3 = 37548235568352L;
      long var5 = var3 ^ 46046372424431L;
      long var7 = var3 ^ 41210646644585L;
      long var9 = var3 ^ 122779271822236L;
      int var11 = (int)((var3 ^ 122779271822236L) >>> 48);
      int var12 = (int)((var3 ^ 122779271822236L) << 16 >>> 48);
      int var13 = (int)(var9 << 32 >>> 32);
      this.h((short)var11, (char)var12, var13);
      this.panel(var1, 0.0F, 0.0F, this.contentWidth(), 24.0F, 12.0F);
      float var14 = 8.0F;
      float var15 = 18.0F * this.y.aspect();
      float var16 = 0.8F + 0.2F * (float)Math.sin(var2.time * 1.6);
      var1.glowCircle(var14 + var15 / 2.0F, 13.0F, 5.4F, 19.800001F, BlendMode.withAlpha(Theme.logoTint(-3069451), 0.16F * var16));
      var1.logo(this.y, var14, 3.0F, var15, 18.0F, -1);
      float var17 = var14 + var15;
      if (this.W(var7)) {
         var14 = var17 + 7.0F;
         float var18 = 6.2F * this.A.aspect();
         var1.image(this.A, var14, 8.9F, var18, 6.2F, 0.0F, -218103809);
         var17 = var14 + var18;
      }

      if (this.k.enabled()) {
         var14 = var17 + 6.0F;
         var1.circle(var14, 12.0F, 1.2F, Icons.TEXT_3);
         var14 += 7.0F;
         this.text(var1, Fonts.MEDIUM, this.K(var5), var14, 12.0F, 8.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      }
   }

   protected void closeContent() {
      try {
         if (this.y != null) {
            this.y.close();
         }
      } finally {
         if (this.A != null) {
            this.A.close();
         }

         this.y = null;
         this.A = null;
      }
   }
}
