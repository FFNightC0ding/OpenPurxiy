package dev.purxiy.modules.spotify;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudModule;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.ui.Icons;

final class SpotifyRenderer6 extends HudModule {
   private static final float t = 11.0F;
   private static final float J = 9.0F;
   private final SpotifyModule y;
   private final SpotifyRenderer3 W = new SpotifyRenderer3();
   private final SpotifyRenderer3.TypeD F = new SpotifyRenderer3.TypeD();
   private final SpotifyRenderer2 Q = new SpotifyRenderer2();
   private final SpotifyRenderer2.TypeH B = new SpotifyRenderer2.TypeH();
   private final Animator h = Animator.smooth(1.0F);
   private boolean N;
   private SpotifyCurve P;
   private static final String[] b = new String[8];

   SpotifyRenderer6(SpotifyModule var1) {
      super("spotify_player_card", "Spotify Player", "The song that plays and its controls", "music", 1.0, 1.0, var1, "player_", 0.6);
      this.y = var1;
   }

   protected boolean shown() {
      return this.y.enabled() && this.y.showsPlayer();
   }

   boolean shownOnScreen() {
      return this.shown() && this.onScreen();
   }

   protected boolean hasContent(HudBounds var1) {
      this.y.frame(var1);
      return this.y.visible(SpotifyRegistry.get().live());
   }

   private SpotifyCurve v(HudBounds var1) {
      return SpotifyRegistry.get().pick(var1.editing);
   }

   protected void measure(HudBounds var1, float[] var2) {
      long var3 = 45654833682230L;
      long var5 = var3 ^ 72430038896466L;
      this.y.frame(var1);
      this.P = this.v(var1);
      String var8 = (String)this.y.m.get();

      float var7 = switch (var8) {
         case "Always" -> 1.0F;
         case "In chat" -> var1.editing ? 1.0F : 0.0F;
         default -> 0.0F;
      };
      if (!this.N) {
         this.h.snap(var7);
         this.N = true;
      }

      float var10 = this.h.target(var7).update(MotionStyle.PANEL.dt(var1.dt));
      this.C(var5, var1);
      float var11 = SpotifyRenderer3.height(var10);
      if (this.y.combined()) {
         var11 += 9.0F + SpotifyRenderer2.reservedHeight(this.B) + 2.0F;
      }

      var2[0] = 216.0F;
      var2[1] = var11;
   }

   private void C(long var1, HudBounds var3) {
      this.F.y = this.y.d.enabled();
      this.F.V = this.y.w.enabled();
      this.F.g = this.y.G.is("Album");
      this.F.T = this.needsShadow();
      SpotifyRenderer2.TypeH var4 = this.B;
      var4.r = 198.0F;
      var4.u = this.y.p.intValue();
      var4.O = 13.5F;
      var4.h = 10.5F;
      var4.T = 4.0F;
      var4.o = FontStyle.LEFT;
      var4.I = this.y.g.enabled();
      var4.x = this.y.e.enabled();
      var4.b = this.needsShadow();
      var4.z = false;
   }

   protected void render(Canvas var1, HudBounds var2) {
      SpotifyCurve var3 = this.P != null ? this.P : this.v(var2);
      if (var3.track() != null) {
         float var4 = this.contentWidth();
         float var5 = this.contentHeight();
         var1.pushAlpha(this.y.pausedAlpha(var3));
         this.panel(var1, 0.0F, 0.0F, var4, var5, 11.0F);
         float var6 = this.h.value();
         this.W.render(var1, var3, this.F, var6, this.pointerX(), this.pointerY(), var2.dt, var2.time);
         if (this.y.combined()) {
            float var7 = SpotifyRenderer3.height(var6) + 4.5F - 2.0F;
            var1.rect(9.0F, var7, var4 - 18.0F, 1.0F, 352321535);
            this.B.l = this.F.g ? var3.accent() : Icons.PURPLE;
            this.Q.render(var1, 9.0F, var7 + 4.5F + 1.0F, this.B, var3, var2.dt, var2.time);
         }

         var1.popAlpha();
      }
   }

   protected boolean pointerDown(float var1, float var2, int var3) {
      SpotifyCurve var4 = this.P;
      return var4 != null && var4.track() != null && this.W.down(var1, var2, this.h.value(), var4, this.F);
   }

   protected void pointerDrag(float var1, float var2) {
      if (this.P != null) {
         this.W.drag(var1, var2, this.P);
      }
   }

   protected void pointerUp(float var1, float var2) {
      if (this.P != null) {
         this.W.up(var1, var2, this.P);
      }
   }

   protected boolean pointerScroll(float var1, float var2, double var3) {
      SpotifyCurve var5 = this.P;
      return var5 != null && var5.track() != null && this.W.scroll(var1, var2, var3, this.h.value(), var5, this.F, this.y.r.floatValue());
   }

   protected String editorHint(float var1, float var2) {
      SpotifyCurve var3 = this.P;
      return var3 != null && var3.track() != null ? this.W.hint(var1, var2, this.h.value(), var3, this.F) : null;
   }
}
