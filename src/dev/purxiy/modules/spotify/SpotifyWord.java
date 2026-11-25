package dev.purxiy.modules.spotify;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.texture.TextureLoader;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.Locale;

final class SpotifyWord {
   static final float N = 216.0F;
   static final float I = 9.0F;
   static final float o = 46.0F;
   static final float E = 25.0F;
   private static final float a = 65.0F;
   private static final float y = 11.5F;
   private static final float J = 27.0F;
   private static final float q = 49.0F;
   private static final float b = 72.5F;
   private static final float h = 30.0F;
   private static final float z = 56.0F;
   private static final float G = 83.0F;
   private static final float u = 110.0F;
   private static final float c = 136.0F;
   private static final float K = 157.0F;
   private static final float g = 167.0F;
   private static final float m = 205.0F;
   private static final float A = 10.5F;
   private static TextureLoader d;
   private final Animator[] W = new Animator[SpotifyWord.WordAlign.values().length];
   private final Animator[] f = new Animator[SpotifyWord.WordAlign.values().length];
   private final Animator l = Animator.smooth(0.0F);
   private final Animator Z = Animator.smooth(0.0F);
   private final Animator w = Animator.smooth(0.0F);
   private int t = Integer.MIN_VALUE;
   private int j = 1;
   private boolean i;
   private boolean D;
   private double H = -1.0;
   private float r = -1.0F;
   private float M = 89.0F;
   private float X = 100.0F;
   private static final Integer[] p = new Integer[30];

   SpotifyWord() {
      for (int var1 = 0; var1 < this.W.length; var1++) {
         this.W[var1] = Animator.smooth(0.0F);
         this.f[var1] = Animator.snappy(0.0F);
      }
   }

   static float height(float var0) {
      return 64.0F + 25.0F * var0;
   }

   void render(Canvas var1, SpotifyCurve var2, SpotifyWord.WordSprite var3, float var4, float var5, float var6, float var7, float var8) {
      long var9 = 15301446172476L;
      long var11 = var9 ^ 132816270583844L;
      long var13 = var9 ^ 122857810368160L;
      long var15 = var9 ^ 9451690516136L;
      long var17 = var9 ^ 44021703454944L;
      long var19 = var9 ^ 10085341317113L;
      SpotifyCurve.Data var21 = var2.track();
      if (var21 != null) {
         float var22 = MotionStyle.HOVER.dt(var7);
         SpotifyWord.WordAlign var23 = Float.isNaN(var5) ? SpotifyWord.WordAlign.O : this.hitAt(var5, var6, var4, var2, var3);

         for (SpotifyWord.WordAlign var27 : SpotifyWord.WordAlign.values()) {
            boolean var28 = var27 == var23 || var27 == SpotifyWord.WordAlign.F && this.i || var27 == SpotifyWord.WordAlign.L && this.D;
            this.W[var27.ordinal()].target(var28 ? 1.0F : 0.0F).update(var22);
            this.f[var27.ordinal()].update(MotionStyle.TOGGLE.dt(var7));
         }

         float var30 = this.Z.target(var2.playing() ? 0.0F : 1.0F).update(var22);
         if (var2.skips() != this.t) {
            if (this.t != Integer.MIN_VALUE) {
               this.w.snap(1.0F).target(0.0F);
               this.j = var2.skipDirection();
            }

            this.t = var2.skips();
         }

         float var31 = this.w.update(MotionStyle.MENU.dt(var7) * 0.9F);
         int var32 = var3.g ? var2.accent() : Icons.PURPLE;
         int var33 = var3.g ? BlendMode.shade(var32, -0.3F) : Icons.INDIGO;
         int var34 = var3.g ? BlendMode.shade(var32, 0.12F) : Icons.MAGENTA;
         float var29 = var3.y ? 65.0F : 11.0F;
         if (var3.y) {
            this.h(var15, var1, var2, var32, var30, var31);
         }

         var1.pushAlpha(1.0F - 0.85F * var31);
         var1.push();
         var1.translate(var31 * 9.0F * this.j, 0.0F);
         this.X(var1, var11, var2, var3, var29, var8);
         var1.pop();
         var1.popAlpha();
         if (var3.V) {
            this.q(var1, var2, var33, var17, var34);
         }

         this.f(var1, var2, var13, var3, var29, var33, var34, var30);
         if (var4 > 0.01F) {
            var1.pushAlpha(Math.min(1.0F, var4 * 1.25F));
            this.l(var1, var19, var2, var4, var32, var33, var34);
            var1.popAlpha();
         }
      }
   }

   private void h(long var1, Canvas var3, SpotifyCurve var4, int var5, float var6, float var7) {
      byte var8;
      int var9;
      int var10;
      float var11;
      float var12;
      float var13;
      float var14;
      int var15;
      TextureLoader var16;
      TextureLoader var17;
      float var18;
      boolean var19;
      label68: {
         long var20 = 129755511634138L;
         var8 = 0;
         var9 = 1979911981;
         var10 = (int)(var20 << 48 >>> 48);
         SpotifyCurve.ensureSourceClassInitialized();
         var11 = 46.0F * (1.0F - 0.05F * var7);
         var12 = 9.0F + (46.0F - var11) / 2.0F;
         var13 = 9.0F + (46.0F - var11) / 2.0F;
         var14 = 8.0F;
         var3.glow(var12, var13, var11, var11, var14, 16.0F, BlendMode.withAlpha(var5, 0.26F * (1.0F - 0.6F * var6)));
         var3.shadow(var12, var13, var11, var11, var14, 10.0F, 0.0F, 0.0F, 3.0F, 1426063360);
         var15 = BlendMode.lerp(-1, Theme.tint(-4344116), var6);
         var16 = var4.art();
         var17 = var4.previousArt();
         var18 = var4.artFade();
         var19 = false;
         if (var17 != null) {
            float var22;
            int var23 = (var22 = var18 - 1.0F) == 0.0F ? 0 : (var22 < 0.0F ? -1 : 1);
            long var24 = 5987216171412L;
            int var26 = var24 < 0L ? -1 : 1;
            if (var23 < 0) {
               var3.image(var17, var12, var13, var11, var11, var14, var15);
               var19 = true;
               break label68;
            }
         }

         if (var16 == null || var18 < 1.0F) {
            float var30 = var18 < 1.0F && var17 == null && var16 != null ? 1.0F : 1.0F;
            this.V(var3, (char)var8, var4, var12, var9, var13, var11, var14, var30, (char)var10);
            var19 = true;
         }
      }

      if (var16 != null) {
         var3.pushAlpha(var19 ? var18 : 1.0F);
         long var28 = 5987216171411L;
         int var301 = var28 < 0L ? -1 : 1;
         var3.image(var16, var12, var13, var11, var11, var14, var15);
         var3.popAlpha();
      } else if (var17 != null && var18 < 1.0F) {
         var3.pushAlpha(var18);
         this.V(var3, (char)var8, var4, var12, var9, var13, var11, var14, 1.0F, (char)var10);
         var3.popAlpha();
      }

      var3.roundRectOutline(var12, var13, var11, var11, var14, 1.0F, 486539263);
      if (var6 > 0.01F) {
         float var29 = var12 + var11 / 2.0F;
         float var21 = var13 + var11 / 2.0F;
         var3.circle(var29, var21, 11.0F, BlendMode.withAlpha(Theme.tint(-16053995), 0.55F * var6));
         var3.icon("pause", var29, var21, 11.0F, BlendMode.withAlpha(-1, 0.9F * var6));
      }
   }

   private void V(Canvas var1, char var2, SpotifyCurve var3, float var4, int var5, float var6, float var7, float var8, float var9, char var10) {
      SpotifyCurve.ensureSourceClassInitialized();
      var1.pushAlpha(var9);
      var1.roundRectGradient(var4, var6, var7, var7, 8.0F, 135.0F, Theme.tint(-14020512), Theme.tint(-8774736));
      var1.roundRectRadial(var4, var6, var7, var7, 8.0F, Theme.tint(1090485221), Theme.tint(16743397));
      if (var3.sample()) {
         if (d == null) {
            d = TextureLoader.load("textures/brand/mark.png");
         }

         float var11 = var7 * 0.62F;
         float var12 = var11 / d.aspect();
         var1.logo(d, var4 + (var7 - var11) / 2.0F, var6 + (var7 - var12) / 2.0F, var11, var12, -1);
      } else {
         var1.icon("music", var4 + var7 / 2.0F, var6 + var7 / 2.0F, var7 * 0.42F, -637534209);
      }

      var1.popAlpha();
   }

   private void X(Canvas var1, long var2, SpotifyCurve var4, SpotifyWord.WordSprite var5, float var6, float var7) {
      long var8 = 41208395845279L;
      byte var10 = 0;
      short var11 = 9594;
      int var12 = (int)(var8 << 32 >>> 32);
      Translations.pause();

      try {
         this.Y(var1, var10, var4, var5, var11, var6, var12, var7);
      } finally {
         Translations.resume();
      }
   }

   private void Y(Canvas var1, short var2, SpotifyCurve var3, SpotifyWord.WordSprite var4, short var5, float var6, int var7, float var8) {
      SpotifyCurve.Data var9 = var3.track();
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifyRegistry var10 = SpotifyRegistry.get();
      String var11 = var10.shown(var9.title());
      String var12 = var9.artist();
      String var13 = var10.shown(var12.isEmpty() ? var9.album() : var9.artist());
      float var14 = 207.0F - (var4.V ? 20 : 0);
      float var15 = var14 - var6;
      float var16 = 11.5F;
      float var17 = var1.textWidth(Fonts.SEMIBOLD, var11, var16);
      float var18 = 0.0F;
      if (var17 > var15) {
         float var19 = var17 - var15 + 10.0F;
         float var20 = Math.max(1.2F, var19 / 20.0F);
         float var21 = 2.6F + var20 + 1.8F + var20;
         float var22 = var8 % var21;
         float var23;
         int var24 = (var23 = var22 - 2.6F) == 0.0F ? 0 : (var23 < 0.0F ? -1 : 1);
         if (var24 < 0) {
            var18 = 0.0F;
         } else {
            float var25;
            var24 = (var25 = var22 - (2.6F + var20)) == 0.0F ? 0 : (var25 < 0.0F ? -1 : 1);
            if (var24 < 0) {
               var18 = var19 * B((var22 - 2.6F) / var20);
            } else {
               short var26 = 0;
               float var27 = 2.6F + var20;
               var26 = 9594;
               if (var22 < var27 + 1.8F) {
                  var18 = var19;
               } else {
                  var18 = var19 * (1.0F - B((var22 - 2.6F - var20 - 1.8F) / var20));
               }
            }
         }
      }

      var1.pushClip(var6, 7.0F, var15, 18.0F);
      if (var4.T) {
         var1.textGlow(Fonts.SEMIBOLD, var11, var6 - var18, 12.3F, var16, -1728053248, FontStyle.LEFT, FontWeight.TOP, 0.0F, 0.6F, 1.8F);
      }

      if (var17 > var15) {
         var1.textSweep(
            Fonts.SEMIBOLD,
            var11,
            var6 - var18,
            11.5F,
            var16,
            Icons.TEXT,
            Theme.tint(16184319),
            var6 + var15 - 6.0F,
            12.0F,
            FontStyle.LEFT,
            FontWeight.TOP,
            0.0F
         );
      } else {
         var1.text(Fonts.SEMIBOLD, var11, var6, 11.5F, var16, Icons.TEXT);
      }

      var1.popClip();
      String var28 = FontMetrics.ellipsize(Fonts.MEDIUM, var13, 9.5F, 207.0F - var6, 0.0F);
      if (var4.T) {
         var1.textGlow(Fonts.MEDIUM, var28, var6, 27.8F, 9.5F, -2013265920, FontStyle.LEFT, FontWeight.TOP, 0.0F, 0.6F, 1.8F);
      }

      var1.text(Fonts.MEDIUM, var28, var6, 27.0F, 9.5F, Theme.tint(-4607786));
   }

   private void q(Canvas var1, SpotifyCurve var2, int var3, long var4, int var6) {
      float var7 = 2.2F;
      float var8 = 1.8F;
      float var9 = 12.5F;
      float var10 = 207.0F - (var7 * 4.0F + var8 * 3.0F);
      SpotifyCurve.ensureSourceClassInitialized();
      float var11 = 23.5F;

      for (int var12 = 0; var12 < 4; var12++) {
         float var13 = Math.max(var7, var9 * var2.bar(var12));
         float var14 = var10 + var12 * (var7 + var8);
         var1.roundRectGradient(var14, var11 - var13, var7, var13, var7 / 2.0F, 90.0F, BlendMode.lerp(var6, -1, 0.25F), var3);
      }
   }

   private void f(Canvas var1, SpotifyCurve var2, long var3, SpotifyWord.WordSprite var5, float var6, int var7, int var8, float var9) {
      SpotifyCurve.ensureSourceClassInitialized();
      double var10 = var2.duration();
      double var12 = this.i ? this.H : var2.position();
      float var14 = 7.5F;
      String var15 = time(var12);
      double var16;
      int var18 = (var16 = var10 - 0.0) == 0.0 ? 0 : (var16 < 0.0 ? -1 : 1);
      long var19 = 108128845064603L;
      int var22 = var19 < 0L ? -1 : 1;
      String var22x = var18 > 0 ? time(var10) : "--:--";
      float var23 = var1.textWidth(Fonts.MEDIUM, var15, var14);
      float var24 = var1.textWidth(Fonts.MEDIUM, var22x, var14);
      int var25 = Theme.tint(-7502420);
      var1.text(Fonts.MEDIUM, var15, var6, 49.0F, var14, var25, FontStyle.LEFT, FontWeight.MIDDLE);
      var1.text(Fonts.MEDIUM, var22x, 207.0F, 49.0F, var14, var25, FontStyle.RIGHT, FontWeight.MIDDLE);
      float var26 = var6 + var23 + 6.0F;
      float var27 = 207.0F - var24 - 6.0F;
      float var28 = Math.max(1.0F, var27 - var26);
      float var29 = this.l.target(this.W[SpotifyWord.WordAlign.F.ordinal()].target() > 0.5F ? 1.0F : 0.0F).update(0.016F);
      float var30 = 3.0F + 1.5F * var29;
      float var31 = 49.0F - var30 / 2.0F;
      long var32 = 108128845064603L;
      int var35 = var32 < 0L ? -1 : 1;
      float var35x = var10 > 0.0 ? (float)Math.max(0.0, Math.min(1.0, var12 / var10)) : 0.0F;
      var1.roundRect(var26, var31, var28, var30, var30 / 2.0F, 620756991);
      float var36 = Math.max(var30, var28 * var35x);
      long var37 = 108128845064603L;
      int var37x = var37 < 0L ? -1 : 1;
      if (var35x > 0.0F) {
         var1.glow(var26, var31, var36, var30, var30 / 2.0F, 8.0F, BlendMode.withAlpha(var8, 0.35F * (1.0F - var9 * 0.7F)));
         var1.roundRectGradient(var26, var31, var36, var30, var30 / 2.0F, 0.0F, var7, var8);
      }

      long var39x = 108128845064603L;
      if (var29 > 0.01F) {
         float var41x = var26 + var28 * var35x;
         float var42 = 3.8F * var29;
         var1.shadow(var41x - var42, 49.0F - var42, var42 * 2.0F, var42 * 2.0F, var42, 6.0F, 0.0F, 0.0F, 1.0F, 1711276032);
         var1.circle(var41x, 49.0F, var42, -1);
      }

      this.M = var26;
      this.X = var28;
   }

   private void l(Canvas var1, long var2, SpotifyCurve var4, float var5, int var6, int var7, int var8) {
      long var9 = 62887015381005L;
      SpotifyCurve.ensureSourceClassInitialized();
      float var11 = 60.0F + 12.5F * var5;
      this.v(var1, SpotifyWord.WordAlign.S, 30.0F, var11, "shuffle", var4.can(32), var9, var4.shuffle(), var6);
      long var12 = 5250738862277L;
      int var15 = var12 < 0L ? -1 : 1;
      this.v(var1, SpotifyWord.WordAlign.H, 56.0F, var11, "skip-back", var4.can(8) || var4.sample(), var9, false, var6);
      this.v(var1, SpotifyWord.WordAlign.I, 110.0F, var11, "skip-forward", var4.can(4) || var4.sample(), var9, false, var6);
      boolean var15x = var4.repeat() == SpotifyData.PlaybackState.H || var4.repeat() == SpotifyData.PlaybackState.r;
      this.v(var1, SpotifyWord.WordAlign.p, 136.0F, var11, var4.repeat() == SpotifyData.PlaybackState.r ? "repeat-1" : "repeat", var4.can(64), var9, var15x, var6);
      float var16 = this.W[SpotifyWord.WordAlign.f.ordinal()].value();
      float var17 = this.f[SpotifyWord.WordAlign.f.ordinal()].value();
      float var18 = 10.5F * (1.0F + 0.08F * var16 - 0.1F * var17);
      var1.glowCircle(83.0F, var11, var18, 10.0F + 6.0F * var16, BlendMode.withAlpha(var8, 0.3F + 0.25F * var16));
      var1.shadow(83.0F - var18, var11 - var18, var18 * 2.0F, var18 * 2.0F, var18, 6.0F, 0.0F, 0.0F, 1.5F, 1426063360);
      var1.roundRectGradient(83.0F - var18, var11 - var18, var18 * 2.0F, var18 * 2.0F, var18, 135.0F, var7, var8);
      var1.roundRectCorners(83.0F - var18, var11 - var18, var18 * 2.0F, var18 * 2.0F, var18, 872415231, 285212671, 16777215, 16777215);
      boolean var19 = var4.playing();
      long var20 = 5250738862277L;
      int var23 = var20 < 0L ? -1 : 1;
      var1.icon(var19 ? "pause" : "play", 83.0F + (var4.playing() ? 0.0F : 0.7F), var11, 10.5F * (1.0F - 0.08F * var17), -1);
      float var23x = var4.volume();
      boolean var24 = var23x >= 0.0F;
      float var25 = this.W[SpotifyWord.WordAlign.X.ordinal()].value();
      long var26 = 5250738862277L;
      int var26x = var26 < 0L ? -1 : 1;
      String var28x;
      if (!var24) {
         var28x = "volume-2";
      } else if (!var4.muted() && !(var23x <= 0.001F)) {
         float var29;
         int var30 = (var29 = var23x - 0.5F) == 0.0F ? 0 : (var29 < 0.0F ? -1 : 1);
         long var31 = 5250738862276L;
         int var31x = var31 < 0L ? -1 : 1;
         var28x = var30 < 0 ? "volume-1" : "volume-2";
      } else {
         var28x = "volume-x";
      }

      var1.icon(var28x, 157.0F, var11, 11.0F, var24 ? BlendMode.lerp(Theme.tint(-4936494), -1, var25) : Theme.tint(1719432601));
      float var38 = this.W[SpotifyWord.WordAlign.L.ordinal()].value();
      float var39 = 3.0F + 1.2F * var38;
      float var32 = 38.0F;
      var1.roundRect(167.0F, var11 - var39 / 2.0F, var32, var39, var39 / 2.0F, 620756991);
      if (var24) {
         float var33 = var4.muted() ? 0.0F : var23x;
         float var34 = Math.max(var39, var32 * var33);
         var1.roundRectGradient(167.0F, var11 - var39 / 2.0F, var34, var39, var39 / 2.0F, 0.0F, var7, var8);
         long var35 = 5250738862277L;
         int var34x = var35 < 0L ? -1 : 1;
         if (var38 > 0.01F) {
            float var37x = 3.4F * var38;
            var1.circle(167.0F + var32 * var33, var11, var37x, -1);
         }
      }
   }

   private void v(Canvas var1, SpotifyWord.WordAlign var2, float var3, float var4, String var5, boolean var6, long var7, boolean var9, int var10) {
      SpotifyCurve.ensureSourceClassInitialized();
      float var11 = var6 ? this.W[var2.ordinal()].value() : 0.0F;
      float var12 = this.f[var2.ordinal()].value();
      float var13 = 11.5F * (1.0F + 0.1F * var11 - 0.12F * var12);
      float var14;
      int var15 = (var14 = var11 - 0.01F) == 0.0F ? 0 : (var14 < 0.0F ? -1 : 1);
      long var16 = 62887015381005L;
      int var19 = var16 < 0L ? -1 : 1;
      if (var15 > 0) {
         var1.circle(var3, var4, 9.5F, BlendMode.withAlpha(-1, 0.07F * var11));
      }

      long var19x = 62887015381005L;
      int var21 = var19x < 0L ? -1 : 1;
      if (!var6) {
         var15 = Theme.tint(1434219929);
      } else {
         long var21x = 62887015381005L;
         int var22 = var21x < 0L ? -1 : 1;
         var15 = var9 ? BlendMode.lerp(var10, -1, 0.2F + 0.3F * var11) : BlendMode.lerp(Theme.tint(-4936494), -1, var11);
      }

      var1.icon(var5, var3, var4, var13, var15);
      if (var9 && var6) {
         var1.circle(var3, var4 + 8.5F, 1.2F, var10);
      }
   }

   SpotifyWord.WordAlign hitAt(float var1, float var2, float var3, SpotifyCurve var4, SpotifyWord.WordSprite var5) {
      if (!(var1 < 0.0F) && !(var1 > 216.0F) && !(var2 < 0.0F) && !(var2 > height(var3))) {
         if (var3 > 0.6F) {
            float var6 = 60.0F + 12.5F * var3;
            if (Math.abs(var2 - var6) <= 11.0F) {
               if (Math.abs(var1 - 83.0F) <= 12.5F) {
                  return SpotifyWord.WordAlign.f;
               }

               for (Object[] var10 : new Object[][]{
                  {SpotifyWord.WordAlign.S, 30.0F},
                  {SpotifyWord.WordAlign.H, 56.0F},
                  {SpotifyWord.WordAlign.I, 110.0F},
                  {SpotifyWord.WordAlign.p, 136.0F},
                  {SpotifyWord.WordAlign.X, 157.0F}
               }) {
                  if (Math.abs(var1 - (Float)var10[1]) <= 10.0F) {
                     return (SpotifyWord.WordAlign)var10[0];
                  }
               }

               if (var1 >= 163.0F && var1 <= 209.0F) {
                  return SpotifyWord.WordAlign.L;
               }
            }
         }

         if (Math.abs(var2 - 49.0F) <= 6.0F && var1 >= this.M - 5.0F && var1 <= this.M + this.X + 5.0F && var4.can(16)) {
            return SpotifyWord.WordAlign.F;
         } else {
            return var5.y && var1 >= 9.0F && var1 <= 55.0F && var2 >= 9.0F && var2 <= 55.0F ? SpotifyWord.WordAlign.y : SpotifyWord.WordAlign.O;
         }
      } else {
         return SpotifyWord.WordAlign.O;
      }
   }

   boolean down(float var1, float var2, float var3, SpotifyCurve var4, SpotifyWord.WordSprite var5) {
      long var6 = 27132175859952L;
      long var8 = var6 ^ 91595506889355L;
      SpotifyWord.WordAlign var10 = this.hitAt(var1, var2, var3, var4, var5);
      switch (var10) {
         case O:
            return false;
         case y:
         case f:
            var4.togglePlay();
            break;
         case S:
            if (!var4.can(32)) {
               return true;
            }

            var4.toggleShuffle();
            break;
         case H:
            var4.previous();
            break;
         case I:
            var4.next();
            break;
         case p:
            if (!var4.can(64)) {
               return true;
            }

            var4.cycleRepeat();
            break;
         case X:
            if (var4.volume() < 0.0F) {
               return true;
            }

            var4.toggleMute();
            break;
         case L:
            if (var4.volume() < 0.0F) {
               return true;
            }

            this.D = true;
            this.X(var1, var8, var4);
            return true;
         case F:
            this.i = true;
            this.H = this.J(var1, var4);
            UiAudioPolicy.play("CLICK", 1.1F, 0.6F);
            return true;
      }

      this.f[var10.ordinal()].impulse(14.0F);
      UiAudioPolicy.play("CLICK", var10 != SpotifyWord.WordAlign.f && var10 != SpotifyWord.WordAlign.y ? 1.12F : 1.0F, 0.75F);
      return true;
   }

   void drag(float var1, float var2, SpotifyCurve var3) {
      long var4 = 110025856630187L;
      long var6 = var4 ^ 52718207717328L;
      if (this.i) {
         this.H = this.J(var1, var3);
      } else if (this.D) {
         this.X(var1, var6, var3);
      }
   }

   void up(float var1, float var2, SpotifyCurve var3) {
      if (this.i) {
         if (!Float.isNaN(var1)) {
            this.H = this.J(var1, var3);
         }

         var3.seek(this.H);
         UiAudioPolicy.play("TAB", 1.05F, 0.7F);
      }

      this.i = false;
      this.D = false;
      this.H = -1.0;
   }

   boolean scroll(float var1, float var2, double var3, float var5, SpotifyCurve var6, SpotifyWord.WordSprite var7, float var8) {
      long var9 = 100558949887385L;
      long var11 = var9 ^ 102091074468763L;
      SpotifyWord.WordAlign var13 = this.hitAt(var1, var2, var5, var6, var7);
      if ((var13 == SpotifyWord.WordAlign.L || var13 == SpotifyWord.WordAlign.X) && var6.volume() >= 0.0F) {
         var6.setVolume(var6.volume() + (float)Math.signum(var3) * var8);
         this.t(var11, var6);
         return true;
      } else if (var13 == SpotifyWord.WordAlign.F) {
         var6.seek(var6.position() + Math.signum(var3) * 5000.0);
         UiAudioPolicy.play("SLIDER_TICK", 1.0F, 0.6F);
         return true;
      } else {
         return false;
      }
   }

   boolean busy() {
      return this.i || this.D;
   }

   String hint(float var1, float var2, float var3, SpotifyCurve var4, SpotifyWord.WordSprite var5) {
      return switch (this.hitAt(var1, var2, var3, var4, var5)) {
         case O -> null;
         default -> "Click to control your music  ·  Drag the card to move it";
         case X, L -> "Drag or scroll to change the volume  ·  Click the speaker to mute";
         case F -> "Click or drag to jump  ·  Scroll to skip 5 seconds";
      };
   }

   private double J(float var1, SpotifyCurve var2) {
      float var3 = (var1 - this.M) / Math.max(1.0F, this.X);
      return Math.max(0.0F, Math.min(1.0F, var3)) * (float)var2.duration();
   }

   private void X(float var1, long var2, SpotifyCurve var4) {
      long var5 = 8438977844738L;
      float var7 = Math.max(0.0F, Math.min(1.0F, (var1 - 167.0F) / 38.0F));
      var7 = Math.round(var7 * 100.0F) / 100.0F;
      var4.setVolume(var7);
      this.t(var5, var4);
   }

   private void t(long var1, SpotifyCurve var3) {
      SpotifyCurve.ensureSourceClassInitialized();
      float var4 = Math.round(var3.volume() * 20.0F) / 20.0F;
      long var5 = 8438977844737L;
      int var7 = var5 < 0L ? -1 : 1;
      if (var4 != this.r) {
         this.r = var4;
         UiAudioPolicy.play("SLIDER_TICK", 0.85F + 0.45F * var4, 0.8F);
      }
   }

   static String time(double var0) {
      long var2 = Math.max(0L, (long)(var0 / 1000.0));
      return var2 >= 3600L
         ? String.format(Locale.ROOT, "%d:%02d:%02d", var2 / 3600L, var2 / 60L % 60L, var2 % 60L)
         : String.format(Locale.ROOT, "%d:%02d", var2 / 60L, var2 % 60L);
   }

   private static float B(float var0) {
      var0 = Math.max(0.0F, Math.min(1.0F, var0));
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   static final class WordSprite {
      boolean y = true;
      boolean V = true;
      boolean g;
      boolean T;
   }

   static enum WordAlign {
      O,
      y,
      S,
      H,
      f,
      I,
      p,
      X,
      L,
      F;

      private static final long[] a = new long[]{5868026551805679469L, 3934331879853786412L, -5888184992431137209L, 490038037543967803L, -9031405270492413297L};
      private static final Integer[] b = new Integer[5];
   }
}
