package dev.purxiy.ui.config;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.sound.SoundEvent;
import dev.purxiy.sound.SoundRequest;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.Theme;
import java.util.List;

abstract class ConfigLibraryPage {
   static final float PAD = 24.0F;
   static final float RADIUS = 22.0F;
   static final float FOOT = 70.0F;
   private static final float TITLE = 18.0F;
   private static final float LINE = 17.0F;
   private final Animator open = new Animator(0.0F, 250.0F, 27.0F);
   private final Animator grow = Animator.smooth(0.0F);
   private final float[] origin;
   private final ConfigLibraryDash.DashState close = new ConfigLibraryDash.DashState();
   private boolean closing;
   private boolean sized;
   float x;
   float y;
   float w;
   float h;

   ConfigLibraryPage(float[] var1) {
      this.origin = var1;
      this.open.target(1.0F);
   }

   abstract float width();

   abstract String title();


   abstract String subtitle();

   abstract float bodyHeight(Canvas var1, float var2);

   abstract void body(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6);

   abstract void footer(DrawContext var1, Canvas var2, float var3, float var4, float var5, boolean var6);

   abstract boolean press(float var1, float var2);

   void submit() {
   }

   boolean key(int var1, boolean var2) {
      return false;
   }

   void typed(int var1) {
   }

   void mouseDrag(float var1, float var2) {
   }

   void mouseUp() {
   }

   void close() {
      if (!this.closing) {
         this.closing = true;
         this.open.target(0.0F);
         this.closed();
      }
   }

   void closed() {
   }

   boolean closing() {
      return this.closing;
   }

   boolean finished() {
      return this.closing && this.open.value() < 0.02F;
   }

   float[] point(float var1, float var2) {
      return new float[]{this.x + this.w * var1, this.y + this.h * var2};
   }

   void render(DrawContext var1) {
      Canvas var2 = var1.canvas;
      float var3 = this.open.update(MotionStyle.PANEL.dt(var1.dt));
      float var4 = Math.max(0.0F, Math.min(1.0F, var3));
      float var5 = var2.width();
      float var6 = var2.height();
      float var7 = Math.min(this.width(), var5 - 48.0F);
      float var8 = var7 - 48.0F;
      List var9 = this.subtitleLines(var2, var8);
      float var10 = 42.0F + (var9.isEmpty() ? 14.0F : 12.0F + var9.size() * 17.0F + 16.0F);
      float var11 = var10 + this.bodyHeight(var2, var8) + 70.0F;
      if (!this.sized) {
         this.grow.snap(var11);
         this.sized = true;
      }

      float var12 = this.grow.target(var11).update(MotionStyle.PANEL.dt(var1.dt));
      this.w = var7;
      this.h = var12;
      this.x = var5 / 2.0F - var7 / 2.0F;
      this.y = Math.max(56.0F, var6 / 2.0F - var12 / 2.0F - 12.0F);
      var2.rect(0.0F, 0.0F, var5, var6, BlendMode.withAlpha(Theme.tint(-16514294), 0.45F * var4));
      var2.captureBackdrop();
      float[] var13 = this.origin;
      float var14 = lerp(var13[0], this.x, var3);
      float var15 = lerp(var13[1], this.y, var3);
      float var16 = lerp(var13[2], this.w, var3);
      float var17 = lerp(var13[3], this.h, var3);
      float var18 = lerp(Math.min(var13[3], var13[2]) / 2.0F, 22.0F, var4);
      KeyLabel.surface(var2, var14, var15, var16, var17, var18, lerp(0.4F, 1.0F, var4), 0.12F);
      float var19 = smooth((var3 - 0.5F) / 0.5F);
      if (!(var19 <= 0.01F)) {
         boolean var20 = !this.closing && var4 > 0.9F;
         var2.pushClip(var14, var15, var16, var17, var18);
         var2.pushAlpha(var19);
         var2.push();
         var2.translate(0.0F, (1.0F - var19) * 8.0F);
         float var21 = this.x + 24.0F;
         float var22 = this.y + 24.0F + 9.0F;
         var2.text(
            Fonts.BOLD,
            FontMetrics.ellipsize(Fonts.BOLD, this.title(), 18.0F, var8 - 40.0F, -0.01F),
            var21,
            var22,
            18.0F,
            Icons.TEXT,
            FontStyle.LEFT,
            FontWeight.MIDDLE,
            -0.01F
         );

         for (int var23 = 0; var23 < var9.size(); var23++) {
            var2.text(
               Fonts.REGULAR,
               (CharSequence)var9.get(var23),
               var21,
               var22 + 9.0F + 12.0F + 8.5F + var23 * 17.0F,
               12.0F,
               Icons.TEXT_2,
               FontStyle.LEFT,
               FontWeight.MIDDLE
            );
         }

         this.close.render(var1, this.x + this.w - 24.0F + 4.0F - 14.0F, var22, 28.0F, "x", false, 0, 1.0F, var20);
         this.body(var1, var2, var21, this.y + var10, var8, var20);
         this.footer(var1, var2, var21, this.x + this.w - 24.0F, this.y + this.h - 24.0F - 17.0F, var20);
         var2.pop();
         var2.popAlpha();
         var2.popClip();
      }
   }

   private List<String> subtitleLines(Canvas var1, float var2) {
      String var3 = this.subtitle();
      return var3 == null ? List.of() : FontMetrics.wrap(Fonts.REGULAR, var3, 12.0F, var2, 0.0F);
   }

   boolean contains(float var1, float var2) {
      return var1 >= this.x && var1 <= this.x + this.w && var2 >= this.y && var2 <= this.y + this.h;
   }

   void mouseDown(float var1, float var2) {
      if (!this.closing) {
         if (!this.close.hit(var1, var2) && this.contains(var1, var2)) {
            this.press(var1, var2);
         } else {
            this.close();
            SoundRequest.play(SoundEvent.COLLAPSE);
         }
      }
   }

   void keyPressed(int var1, boolean var2) {
      if (!this.closing && !this.key(var1, var2)) {
         if (var1 == 256) {
            this.close();
            SoundRequest.play(SoundEvent.COLLAPSE);
         } else if (var1 == 257 || var1 == 335) {
            this.submit();
         }
      }
   }

   static void iconTile(Canvas var0, float var1, float var2, float var3, String var4, int var5, float var6) {
      var0.roundRect(var1, var2 - var3 / 2.0F, var3, var3, var3 * 0.3F, BlendMode.lerp(268435455, BlendMode.withAlpha(var5, 0.2F), var6));
      var0.roundRectOutline(var1, var2 - var3 / 2.0F, var3, var3, var3 * 0.3F, 1.0F, BlendMode.lerp(385875967, BlendMode.withAlpha(var5, 0.45F), var6));
      var0.icon(var4, var1 + var3 / 2.0F, var2, var3 * 0.46F, BlendMode.lerp(Icons.TEXT_2, BlendMode.lerp(var5, -1, 0.55F), var6));
   }

   static float disabled(DrawContext var0, ConfigLibraryDash.DashBar var1, float var2, float var3, float var4,  String var5, String var6) {
      var0.canvas.pushAlpha(0.42F);
      float var7 = var1.render(var0, var2, var3, var4, var5, var6, ConfigLibraryDash.DashTone.QUIET, false);
      var0.canvas.popAlpha();
      return var7;
   }

   static float lerp(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   static float smooth(float var0) {
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }
}
