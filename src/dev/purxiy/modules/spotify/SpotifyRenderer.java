package dev.purxiy.modules.spotify;

import dev.purxiy.i18n.Translations;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Theme;
import org.joml.Matrix3x2f;

final class SpotifyRenderer {
   static final float h = 29.44F;
   static final float i = 0.46F;
   static final float t = 0.055F;
   static final float q = 0.5F;
   static final float O = 0.6F;
   static final float H = 0.045F;
   static final float u = 0.34F;
   static final float K = 0.02F;
   static final float I = 0.62F;
   static final float f = 0.06F;
   static final float J = 1.1F;
   static final float N = 0.3F;
   static final float l = 0.52F;
   static final float d = 0.04F;
   static final float E = 0.6F;
   static final float o = 0.72F;
   static final float b = 0.3F;
   static final float M = 0.02F;
   static final int s = -2699789;
   static final int R = -263681;
   static final int g = -16317425;
   private static final float x = 64.0F;

   private SpotifyRenderer() {
   }

   static float leaveTime(SpotifyPlaylist.PlaylistSource<?> var0) {
      int var1 = Math.max(3, var0.t.o.length);
      return var0.U ? 0.3F + var1 * 0.02F : 0.6F + var1 * 0.045F;
   }

   static float breakTime(SpotifyCacheStore.LyricStream<?> var0) {
      return (0.52F + Math.max(0, var0.letters().length - 1) * 0.04F) * (var0.z ? 0.6F : 1.0F);
   }

   static float standHeight(SpotifyCacheStore.LyricStream<?> var0) {
      return 0.06F + var0.inkBelow() + var0.inkAbove();
   }

   static float presence(SpotifyPlaylist.PlaylistSource<?> var0, double var1, float var3, SpotifyRenderer.WidgetCache var4) {
      SpotifyPlaylist.PlaylistState var5 = var0.t;
      float var6 = Float.isNaN(var0.q) ? var0.K : var0.q;
      boolean var7 = var4.B || var5.k == SpotifyPlaylist.PlaylistKind.M;
      float var8 = 0.0F;

      for (int var9 = 0; var9 < var5.o.length; var9++) {
         float var10 = reveal(var0, var9, var1, var3, var6, var7);
         var8 = Math.max(var8, ease(var10) * (1.0F - smooth(leave(var0, var9, var3))));
      }

      return var8 * var0.j;
   }

   static float presence(SpotifyCacheStore.LyricStream<?> var0, float var1) {
      float var2 = ease((var1 - var0.P) / (0.34F + 0.5F * var0.letters().length * 0.02F));
      float var3 = var0.leaving() ? smooth((var1 - var0.e) / (var0.z ? breakTime(var0) : 0.3F)) : 0.0F;
      return var2 * (1.0F - var3) * (0.72F + 0.27999997F * var0.V);
   }

   static void draw(Canvas var0, SpotifyPlaylist.PlaylistSource<?> var1, double var2, float var4, SpotifyRenderer.WidgetCache var5) {
      long var6 = 117080994534010L;
      long var8 = var6 ^ 22448238154953L;
      int var10 = (int)((var6 ^ 22448238154953L) >>> 48);
      int var11 = (int)((var6 ^ 22448238154953L) << 16 >>> 32);
      int var12 = (int)(var8 << 48 >>> 48);
      Translations.pause();

      try {
         l(var0, (char)var10, var1, var2, var11, var4, (char)var12, var5);
      } finally {
         Translations.resume();
      }
   }

   private static void l(Canvas var0, char var1, SpotifyPlaylist.PlaylistSource var2, double var3, int var5, float var6, char var7, SpotifyRenderer.WidgetCache var8) {
      long var9 = 138635778459315L;
      long var11 = var9 ^ 40044224678216L;
      long var13 = var9 ^ 9498333214388L;
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifyPlaylist.PlaylistState var15 = var2.t;
      boolean var16 = var15.k == SpotifyPlaylist.PlaylistKind.M;
      boolean var17 = var8.B || var16;
      float var18 = -var15.C * 64.0F / 2.0F;
      float var19 = 69.12F;
      int var20 = 2115414100;
      float var21 = Float.isNaN(var2.q) ? var2.K : var2.q;
      boolean var22 = var2.g == 0 && !var2.leaving();
      float var23 = var8.B && var15.h != null ? SpotifySyllables.position(var15.h, var15.s, (long)var3) : Float.MAX_VALUE;
      float var24 = U(var0);
      float var25 = 0.01F * var24;
      float var26 = 0.1F * var24;
      float var27 = 0.02F * var24;
      float var28 = 0.09F * var24;
      int var29 = BlendMode.lerp(var8.g, -1, 0.4F);

      for (int var30 = 0; var30 < var15.U.length; var30++) {
         float var31 = 0.0F;
         short var32 = 1715;
         float var33 = 0.0F;
         float var34 = 0.0F;

         for (int var35 = 0; var35 < var15.o.length; var35++) {
            var32 = 1715;
            int var36 = var15.Q[var35];
            int var37 = 0;
            if (var36 == var30) {
               float var38 = ease(reveal(var2, var35, var3, var6, var21, var17));
               int var63 = 1715;
               float var39 = smooth(leave(var2, var35, var6));
               float var40 = var38 * (1.0F - var39);
               var63 = 2115414100;
               if (var40 > 0.002F) {
                  var31 = Math.max(var31, var15.E[var35] + var15.W[var35] * var38);
                  var33 = Math.max(var33, var40);
                  var34 = Math.max(var34, var40 * w(var15, var35, var23, var13));
               }
            }
         }

         int var58 = 0;
         float var61;
         int var65 = (var61 = var33 - 0.002F) == 0.0F ? 0 : (var61 < 0.0F ? -1 : 1);
         var58 = 2115414100;
         if (var65 > 0) {
            float var67 = -var15.i[var30] / 2.0F;
            int var69 = 1715;
            float var72 = var18 + (var30 + 0.5F) * var19;
            float var41 = var8.o * var2.j * var33;
            var0.shadow(
               var67 - 11.52F,
               var72 - 32.0F,
               var31 + 23.04F,
               64.0F,
               32.0F,
               44.8F,
               BlendMode.withAlpha(Theme.tint(-16317425), 0.2F * var8.k * (1.0F - 0.6F * var8.L) * var41)
            );
            var69 = 2115414100;
            if (var8.J && var22) {
               var0.glow(
                  var67 - 6.4F, var72 - 21.76F, var31 + 12.8F, 43.52F, 21.76F, 57.6F, BlendMode.withAlpha(var8.g, (0.1F + 0.22F * var8.L) * var41 * var34)
               );
            }
         }
      }

      int var48 = 1715;

      for (int var50 = 0; var50 < var15.o.length; var50++) {
         float var53 = reveal(var2, var50, var3, var6, var21, var17);
         float var55;
         var48 = (var55 = var53 - 0.0F) == 0.0F ? 0 : (var55 < 0.0F ? -1 : 1);
         var20 = 2115414100;
         if (var48 > 0) {
            float var57 = ease(var53);
            float var60 = smooth(leave(var2, var50, var6));
            float var62 = var8.o * var2.j * var57 * (1.0F - var60);
            if (!(var62 <= 0.002F)) {
               int var66 = var15.Q[var50];
               float var68 = -var15.i[var66] / 2.0F + var15.E[var50] - (1.0F - var57) * 0.32F * 64.0F + var60 * 0.16F * 64.0F;
               float var71 = var18 + (var66 + 0.5F) * var19 - (1.0F - var57) * 0.1F * 64.0F - var60 * 0.06F * 64.0F;
               String var73 = var15.o[var50];
               float var74 = w(var15, var50, var23, var13);
               float var42 = var53 * 0.46F;
               float var43 = var17 ? 0.0F : (float)Math.exp(-var42 / 0.5F * 2.2F) * (1.0F - var60);
               float var44 = (var8.Y ? 0.3F : 0.42F) * var8.k * (1.0F - 0.5F * var8.L) * var62;
               var0.textGlow(
                  SpotifyPlaylist.T,
                  var73,
                  var68,
                  var71 + (var8.Y ? 0.02F : 0.04F) * 64.0F,
                  64.0F,
                  BlendMode.withAlpha(Theme.tint(-16317425), var44),
                  FontStyle.LEFT,
                  FontWeight.MIDDLE,
                  0.0F,
                  var25,
                  var26
               );
               if (var8.J) {
                  float var45 = (var22 ? 0.3F + 0.25F * var8.L + 0.55F * var43 : 0.1F) * Math.max(var74, var43);
                  if (var45 > 0.01F) {
                     var0.textGlow(
                        SpotifyPlaylist.T,
                        var73,
                        var68,
                        var71,
                        64.0F,
                        BlendMode.withAlpha(var29, var45 * var62),
                        FontStyle.LEFT,
                        FontWeight.MIDDLE,
                        0.0F,
                        var27,
                        var28
                     );
                  }
               }

               int var75 = BlendMode.lerp(Theme.tint(-2699789), Theme.tint(-263681), Math.min(1.0F, 0.25F + var2.j));
               if (var8.B && !var16 && var74 < 1.0F && var15.h != null) {
                  float var46 = g(var73, var11, var23 - var15.A[var50], var68);
                  var0.textSweep(
                     SpotifyPlaylist.T,
                     var73,
                     var68,
                     var71,
                     64.0F,
                     BlendMode.withAlpha(var75, var62),
                     BlendMode.withAlpha(Theme.tint(-2699789), 0.62F * var62),
                     var46,
                     32.0F,
                     FontStyle.LEFT,
                     FontWeight.MIDDLE,
                     0.0F
                  );
               } else {
                  var0.text(
                     SpotifyPlaylist.T,
                     var73,
                     var68,
                     var71,
                     64.0F,
                     BlendMode.withAlpha(BlendMode.lerp(var75, -1, var43 * 0.6F), var62),
                     FontStyle.LEFT,
                     FontWeight.MIDDLE,
                     0.0F
                  );
               }
            }
         }
      }

      if (var15.l != null) {
         float var51 = clamp01((var6 - var21 - (var15.o.length * 0.055F + 0.2F)) / 0.46F);
         float var54 = var8.o * var2.j * ease(var51) * (1.0F - smooth(leave(var2, var15.o.length, var6)));
         if (var54 > 0.002F) {
            float var56 = var18 + var15.U.length * var19 + 21.76F - (1.0F - ease(var51)) * 0.08F * 64.0F;
            var0.textGlow(
               Fonts.MEDIUM,
               var15.l,
               -(1.0F - ease(var51)) * 0.2F * 64.0F,
               var56 + 1.92F,
               29.44F,
               BlendMode.withAlpha(Theme.tint(-16317425), 0.6F * var8.k * var54),
               FontStyle.CENTER,
               FontWeight.MIDDLE,
               0.0F,
               var25 * 0.6F,
               var26 * 0.6F
            );
            var0.text(
               Fonts.MEDIUM,
               var15.l,
               -(1.0F - ease(var51)) * 0.2F * 64.0F,
               var56,
               29.44F,
               BlendMode.withAlpha(Theme.tint(-3884060), var54),
               FontStyle.CENTER,
               FontWeight.MIDDLE,
               0.0F
            );
         }
      }
   }

   static void stand(Canvas var0, SpotifyCacheStore.LyricStream<?> var1, float var2, SpotifyRenderer.WidgetCache var3) {
      Translations.pause();

      try {
         String[] var4 = var1.letters();
         int var5 = var4.length;
         float var6 = Math.max(0.0F, var2 - var1.P);
         float var7 = var1.leaving() ? var2 - var1.e : -1.0F;
         float var8 = -var1.width() / 2.0F;
         float var9 = -(0.06F + var1.inkBelow()) * 64.0F;
         float var10 = U(var0);
         float var11 = (float)Math.exp(-var6 / 0.5F * 2.2F);
         int var12 = BlendMode.lerp(var3.g, -1, 0.4F);
         float var13 = var3.o * (0.72F + 0.27999997F * var1.V);
         int var14 = BlendMode.lerp(Theme.tint(-2699789), BlendMode.lerp(Theme.tint(-263681), -1, var11 * 0.6F), 0.3F + 0.7F * var1.V);
         int var15 = Theme.tint(-16317425);
         float var16 = 0.45F * var3.k * (1.0F - 0.5F * var3.L);
         float var17 = var3.J ? (0.3F + 0.25F * var3.L) * (0.45F + 0.55F * var1.V) + 0.55F * var11 : 0.0F;
         var0.pushClip(-4096.0F, -4096.0F, 8192.0F, 4096.0F);
         if (var7 < 0.0F && var6 >= 0.34F + (var5 - 1) * 0.02F) {
            String var39 = var1.text();
            var0.textGlow(
               SpotifyPlaylist.T,
               var39,
               var8,
               var9 + 2.56F,
               64.0F,
               BlendMode.withAlpha(var15, var16 * var13),
               FontStyle.LEFT,
               FontWeight.BASELINE,
               0.0F,
               0.01F * var10,
               0.1F * var10
            );
            if (var17 > 0.01F) {
               var0.textGlow(
                  SpotifyPlaylist.T,
                  var39,
                  var8,
                  var9,
                  64.0F,
                  BlendMode.withAlpha(var12, var17 * var13),
                  FontStyle.LEFT,
                  FontWeight.BASELINE,
                  0.0F,
                  0.02F * var10,
                  0.09F * var10
               );
            }

            var0.text(SpotifyPlaylist.T, var39, var8, var9, 64.0F, BlendMode.withAlpha(var14, var13), FontStyle.LEFT, FontWeight.BASELINE, 0.0F);
         } else {
            float var18 = SpotifyPlaylist.T.capHeight * 64.0F / 2.0F;
            int var19 = var3.J ? var12 : Theme.tint(-263681);
            float var20 = !(var7 < 0.0F) && !var1.z ? 70.4F * cubicOut(var7 / 0.3F) : 0.0F;
            float var21 = var1.z ? 0.6F : 1.0F;

            for (int var22 = 0; var22 < var5; var22++) {
               float var23 = clamp01((var6 - var22 * 0.02F) / 0.34F);
               if (!(var23 <= 0.0F)) {
                  int var24 = var1.M < 0 ? var22 : var5 - 1 - var22;
                  float var25 = var7 < 0.0F ? 0.0F : clamp01((var7 - var24 * 0.04F * var21) / (0.52F * var21));
                  float var26 = var13 * smooth(var23 * 2.5F);
                  float var27 = var26 * (1.0F - smooth(var25 * 1.3F));
                  float var28 = var26 * (float)Math.sin(Math.PI * Math.min(1.0F, var25 * 1.15F)) * (var3.J ? 0.42F : 0.26F);
                  if (!(var27 <= 0.002F) || !(var28 <= 0.002F)) {
                     String var29 = var4[var22];
                     float var30 = SpotifyPlaylist.T.measure(var29, 64.0F, 0.0F);
                     float var31 = SpotifySupport.hash(var1.F * 131L + var22);
                     float var32 = var8 + var1.letterX(var22) + var30 / 2.0F + var1.M * 0.5F * 64.0F * (float)Math.pow(var25, 1.5);
                     float var33 = var9 - var18 + (1.0F - springOut(var23)) * 0.62F * 64.0F - var20 - 0.35F * var25 * var25 * 64.0F;
                     float var34 = var1.M * (var31 < 0.25F ? -1 : 1) * (0.25F + 0.25F * var31) * var25;
                     var0.push();
                     var0.translate(var32, var33);
                     var0.rotate(var34);
                     var0.scale(1.0F - 0.25F * var25);
                     float var35 = -var30 / 2.0F;
                     if (var27 > 0.002F) {
                        var0.textGlow(
                           SpotifyPlaylist.T,
                           var29,
                           var35,
                           var18 + 2.56F,
                           64.0F,
                           BlendMode.withAlpha(var15, var16 * var27),
                           FontStyle.LEFT,
                           FontWeight.BASELINE,
                           0.0F,
                           0.01F * var10,
                           0.1F * var10
                        );
                     }

                     if (var28 > 0.002F) {
                        var0.textGlow(
                           SpotifyPlaylist.T,
                           var29,
                           var35,
                           var18,
                           64.0F,
                           BlendMode.withAlpha(var19, var28),
                           FontStyle.LEFT,
                           FontWeight.BASELINE,
                           0.0F,
                           (0.02F + 0.03F * var25) * var10,
                           (0.08F + 0.2F * var25) * var10
                        );
                     }

                     if (var27 > 0.002F) {
                        if (var17 > 0.01F) {
                           var0.textGlow(
                              SpotifyPlaylist.T,
                              var29,
                              var35,
                              var18,
                              64.0F,
                              BlendMode.withAlpha(var12, var17 * var27),
                              FontStyle.LEFT,
                              FontWeight.BASELINE,
                              0.0F,
                              0.02F * var10,
                              0.09F * var10
                           );
                        }

                        var0.text(SpotifyPlaylist.T, var29, var35, var18, 64.0F, BlendMode.withAlpha(var14, var27), FontStyle.LEFT, FontWeight.BASELINE, 0.0F);
                     }

                     var0.pop();
                  }
               }
            }
         }

         var0.popClip();
      } finally {
         Translations.resume();
      }
   }

   static void shadow(Canvas var0, float var1, float var2, SpotifyRenderer.WidgetCache var3) {
      if (!(var2 <= 0.002F)) {
         var0.shadow(
            -var1 / 2.0F - 12.8F,
            -30.72F,
            var1 + 25.6F,
            61.44F,
            30.72F,
            38.4F,
            BlendMode.withAlpha(Theme.tint(-16317425), 0.42F * var3.k * (1.0F - 0.55F * var3.L) * var2)
         );
         if (var3.J) {
            var0.glow(-var1 / 2.0F, -20.48F, var1, 40.96F, 20.48F, 51.2F, BlendMode.withAlpha(var3.g, (0.05F + 0.25F * var3.L) * var2));
         }
      }
   }

   static float reveal(SpotifyPlaylist.PlaylistSource<?> var0, int var1, double var2, float var4, float var5, boolean var6) {
      float var7 = (var4 - var5 - var1 * 0.055F) / 0.46F;
      if (var6) {
         return clamp01(var7);
      } else {
         float var8 = (float)(var2 - (var0.t.F[var1] - var0.t.j)) / 1000.0F / 0.46F;
         return clamp01(Math.min(var7, var8));
      }
   }

   static float leave(SpotifyPlaylist.PlaylistSource<?> var0, int var1, float var2) {
      if (!var0.leaving()) {
         return 0.0F;
      } else {
         float var3 = var0.U ? 0.02F : 0.045F;
         float var4 = var0.U ? 0.3F : 0.6F;
         return clamp01((var2 - var0.D - var1 * var3) / var4);
      }
   }

   private static float w(SpotifyPlaylist.PlaylistState var0, int var1, float var2, long var3) {
      SpotifyCurve.ensureSourceClassInitialized();
      long var5 = 130520441876487L;
      int var7 = var5 < 0L ? -1 : 1;
      return var2 == Float.MAX_VALUE ? 1.0F : clamp01((var2 - var0.A[var1]) / Math.max(1, var0.o[var1].length()));
   }

   private static float g(String var0, long var1, float var3, float var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      if (var3 <= 0.0F) {
         return var4 - 64.0F;
      } else {
         long var5 = 99493682712571L;
         int var8 = var5 < 0L ? -1 : 1;
         if (var3 >= var0.length()) {
            return var4 + SpotifyPlaylist.T.measure(var0, 64.0F, 0.0F) + 64.0F;
         } else {
            var8 = (int)var3;
            float var9 = SpotifyPlaylist.T.measure(var0.substring(0, var8), 64.0F, 0.0F);
            float var10 = SpotifyPlaylist.T.measure(var0.substring(var8, var8 + 1), 64.0F, 0.0F);
            return var4 + var9 + var10 * (var3 - var8);
         }
      }
   }

   private static float U(Canvas var0) {
      Matrix3x2f var1 = var0.transform();
      return 64.0F * var0.pixelScale() * (float)Math.sqrt(Math.abs(var1.m00() * var1.m11() - var1.m01() * var1.m10()));
   }

   static float ease(float var0) {
      float var1 = clamp01(var0);
      float var2 = 1.0F - var1;
      return 1.0F - var2 * var2 * var2 * var2;
   }

   static float cubicOut(float var0) {
      float var1 = 1.0F - clamp01(var0);
      return 1.0F - var1 * var1 * var1;
   }

   static float springOut(float var0) {
      float var1 = clamp01(var0) - 1.0F;
      return 1.0F + var1 * var1 * (2.0F * var1 + 1.0F);
   }

   static float smooth(float var0) {
      float var1 = clamp01(var0);
      return var1 * var1 * (3.0F - 2.0F * var1);
   }

   static float clamp01(float var0) {
      return var0 < 0.0F ? 0.0F : Math.min(1.0F, var0);
   }

   static final class WidgetCache {
      boolean B;
      boolean J = true;
      int g = Theme.tint(-4907020);
      float o = 1.0F;
      float k = 1.0F;
      float r;
      float p;
      float L;
      boolean Y;
   }
}
