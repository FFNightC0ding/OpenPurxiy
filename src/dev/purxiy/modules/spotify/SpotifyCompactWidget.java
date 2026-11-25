package dev.purxiy.modules.spotify;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudModule;
import dev.purxiy.loader.fabric.mixin.BossHealthOverlayAccessor;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.ui.Icons;

final class SpotifyCompactWidget extends HudModule {
   private static final float f = 14.0F;
   private static final float E = 8.0F;
   private static final float G = 12.0F;
   private final SpotifyModule r;
   private final SpotifyLine j = new SpotifyLine();
   private final SpotifyLine.LineBuffer N = new SpotifyLine.LineBuffer();
   private final Animator T = Animator.smooth(0.0F);
   private static final String[] b = new String[11];
   private static final long c = 1891740292806082572L;

   SpotifyCompactWidget(SpotifyModule var1) {
      super("spotify_lyrics", "Spotify Lyrics", "The words of the song, in time", "mic-vocal", 0.5, 0.0, var1, "lyrics_", 0.6);
      this.r = var1;
   }

   protected boolean shown() {
      return this.r.enabled() && this.r.showsLyricsStrip();
   }

   protected boolean hasContent(HudBounds var1) {
      this.r.frame(var1);
      return this.r.visible(SpotifyRegistry.get().live());
   }

   protected void measure(HudBounds var1, float[] var2) {
      long var3 = 7527182705391L;
      long var5 = var3 ^ 73654179297332L;
      this.r.frame(var1);
      this.i(var1, var5);
      var2[0] = this.N.r + 28.0F;
      var2[1] = SpotifyLine.reservedHeight(this.N) + 16.0F;
   }

   private void i(HudBounds var1, long var2) {
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifyLine.LineBuffer var4 = this.N;
      String var5 = (String)this.r.c.get();
      byte var6 = -1;
      switch (var5.hashCode()) {
         case -1968802693:
            if (var5.equals("Narrow")) {
               long var7 = 74923493841627L;
               int var10 = var7 < 0L ? -1 : 1;
               var6 = 0;
            }
            break;
         case 2695923:
            if (var5.equals("Wide")) {
               var6 = 1;
            }
      }
      var4.r = switch (var6) {
         case 0 -> 300.0F;
         case 1 -> 480.0F;
         default -> 380.0F;
      };
      var4.u = this.r.p.intValue();
      var4.O = 17.0F;
      var4.h = 12.5F;
      var4.T = 5.0F;
      long var18 = 74923493841626L;
      int var18x = var18 < 0L ? -1 : 1;
      var4.I = this.r.g.enabled();
      var4.x = this.r.e.enabled();
      boolean var10 = this.needsShadow();
      long var11 = 74923493841626L;
      int var14 = var11 < 0L ? -1 : 1;
      var4.b = var10;
      var4.z = this.bottomSide(var1);
      var5 = (String)this.r.t.get();
      byte var14x = -1;
      switch (var5.hashCode()) {
         case 2364455:
            if (var5.equals("Left")) {
               long var19 = 74923493841627L;
               int var23 = var19 < 0L ? -1 : 1;
               var14x = 0;
            }
            break;
         case 78959100:
            if (var5.equals("Right")) {
               var14x = 2;
            }
            break;
         case 2014820859:
            if (var5.equals("Centre")) {
               long var15 = 74923493841626L;
               int var15x = var15 < 0L ? -1 : 1;
               var14x = 1;
            }
      }
      var4.o = switch (var14x) {
         case 0 -> FontStyle.LEFT;
         case 1 -> FontStyle.CENTER;
         case 2 -> FontStyle.RIGHT;
         default -> {
            float var20 = this.screenX() + this.screenWidth() / 2.0F;
            yield Math.abs(var20 - var1.width / 2.0F) < var1.width * 0.12F ? FontStyle.CENTER : (this.rightSide(var1) ? FontStyle.RIGHT : FontStyle.LEFT);
         }
      };
   }

   protected void render(Canvas var1, HudBounds var2) {
      long var3 = 78478820211132L;
      long var5 = var3 ^ 90024179663418L;
      SpotifyCurve var7 = SpotifyRegistry.get().pick(var2.editing);
      if (var7.track() != null) {
         this.N.l = this.r.G.is("Album") ? var7.accent() : Icons.PURPLE;
         float var8 = this.contentWidth();
         float var9 = SpotifyLine.reservedHeight(this.N);
         float var10 = this.T.target(this.o(var5, var2)).update(MotionStyle.HOVER.dt(var2.dt));
         var1.push();
         var1.translate(0.0F, var10);
         var1.pushAlpha(this.r.pausedAlpha(var7));
         float var11 = this.j.contentHeight(this.N);
         float var12 = var11 + 16.0F;
         float var13 = this.N.z ? this.contentHeight() - var12 : 0.0F;
         this.panel(var1, 0.0F, var13, var8, var12, 12.0F);
         this.j.render(var1, 14.0F, 8.0F, this.N, var7, var2.dt, var2.time);
         var1.popAlpha();
         var1.pop();
      }
   }

   private float o(long var1, HudBounds var3) {
      SpotifyCurve.ensureSourceClassInitialized();
      long var4 = 24739639950214L;
      int var7 = var4 < 0L ? -1 : 1;
      if (var3.mc != null && var3.mc.field_1705 != null) {
         var7 = ((BossHealthOverlayAccessor)var3.mc.field_1705.method_1740()).purxiy$events().size();
         if (var7 == 0) {
            return 0.0F;
         } else {
            int var8 = var3.mc.method_22683().method_4502();
            byte var9 = 12;
            int var10 = 0;

            for (int var11 = 0; var11 < var7; var11++) {
               int var12 = var9 + 5;
               long var13 = 24739639950213L;
               int var25 = var13 < 0L ? -1 : 1;
               var10 = var12;
               var9 = (byte)(var9 + 19);
               long var16 = 24739639950213L;
               int var16x = var16 < 0L ? -1 : 1;
               if (var9 >= var8 / 3) {
                  break;
               }
            }

            long var24 = 24739639950213L;
            int var251 = var24 < 0L ? -1 : 1;
            RenderPipelines var14 = RenderPipelines.get();
            float var15 = var14.hudFromGui(var10 + 5);
            float var25x = var14.hudFromGui(101.0);
            float var17 = this.screenX() + this.screenWidth() / 2.0F;
            long var18 = 24739639950214L;
            int var18x = var18 < 0L ? -1 : 1;
            float var20x = Math.abs(var17 - var3.width / 2.0F);
            float var21 = var25x + this.screenWidth() / 2.0F;
            long var22 = 24739639950214L;
            int var22x = var22 < 0L ? -1 : 1;
            return var20x > var21 ? 0.0F : Math.max(0.0F, var15 - this.screenY()) / this.scale();
         }
      } else {
         return 0.0F;
      }
   }
}
