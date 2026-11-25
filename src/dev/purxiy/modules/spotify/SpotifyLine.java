package dev.purxiy.modules.spotify;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontMetrics;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.font.TrueTypeFont;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.ArrayList;
import java.util.List;

final class SpotifyLine {
   static final long B = 160L;
   private static final long x = 2500L;
   private static final long m = 6500L;
   private static final long S = 1400L;
   private static final float q = 1.2F;
   private static final TrueTypeFont h = Fonts.BOLD;
   private SpotifyLine.TrackDocument b;
   private SpotifyLine.TrackDocument t;
   private final Animator k = Animator.smooth(0.0F);
   private List<SpotifyLine.LyricLine> G = List.of();
   private float[] M = new float[0];
   private float r = -1.0F;
   private float s = -1.0F;
   private SpotifyLyrics w;
   private final Animator N = new Animator(0.0F, 170.0F, 26.0F);
   private boolean H;
   private float C = -1.0F;
   private int O = Integer.MIN_VALUE;
   private static final long[] d = new long[]{
      -370684431583592312L,
      -8673132122167344201L,
      497121007298181950L,
      -4081077919012803671L,
      -6821475854409891878L,
      5574996714550149262L,
      358502999443864625L,
      -181426175104992812L,
      -7524789322999007424L,
      -5166043290767418262L,
      2070381388722213746L,
      -2998715482432625924L,
      6772947272970180728L
   };
   private static final Integer[] e = new Integer[13];
   private static final long[] f = new long[]{
      7725198544847877356L,
      4160850212250072400L,
      5515845550275949021L,
      8424239864331566903L,
      6973957303427462896L,
      9158328055483932138L,
      7512559447335009076L,
      1153971851609689163L
   };
   private static final Long[] g = new Long[8];

   static float lineHeight(float var0) {
      return var0 * 1.2F;
   }

   static float reservedHeight(SpotifyLine.LineBuffer var0) {
      float var1 = 2.0F * lineHeight(var0.O);
      int var2 = Math.max(0, var0.u - 1);
      return var1 + var2 * (lineHeight(var0.h) + var0.T);
   }

   float contentHeight(SpotifyLine.LineBuffer var1) {
      return this.C < 0.0F ? reservedHeight(var1) : this.C;
   }

   void render(Canvas var1, float var2, float var3, SpotifyLine.LineBuffer var4, SpotifyCurve var5, float var6, float var7) {
      long var8 = 136447538534136L;
      long var10 = var8 ^ 42584041498117L;
      long var12 = var8 ^ 100954499451073L;
      long var14 = var8 ^ 140669772058944L;
      int var16 = (int)((var8 ^ 140669772058944L) >>> 48);
      int var17 = (int)((var8 ^ 140669772058944L) << 16 >>> 48);
      int var18 = (int)(var14 << 32 >>> 32);
      SpotifyCurve.Data var19 = var5.track();
      SpotifyLine.TrackDocument var20 = var19 == null
         ? null
         : new SpotifyLine.TrackDocument(
            var19.key() + "|" + var5.lyricsState() + "|" + var5.lyrics().kind(),
            var5.lyrics(),
            var5.lyricsState(),
            var19.title(),
            var19.artist(),
            var19.durationMs()
         );
      this.z(var10, var20, var6);
      SpotifyLine.TrackDocument var21 = this.b;
      if (var21 != null) {
         float var22 = reservedHeight(var4);
         float var23 = this.k.value();
         var1.pushAlpha(var23);
         if (var21.lyrics().synced() && var21.state() == SpotifyCurve.CurveKind.h) {
            this.z(var1, var2, var3, var22, var4, var21, var5, var12, var6, var7);
         } else {
            this.a(var1, var2, (char)var16, (short)var17, var3, var22, var4, var21, var5, var6, var7, var18);
         }

         var1.popAlpha();
      }
   }

   private void z(long var1, SpotifyLine.TrackDocument var3, float var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      float var5 = MotionStyle.HOVER.dt(var4);
      long var6 = 99660248789244L;
      int var9 = var6 < 0L ? -1 : 1;
      boolean var9x = var3 != null && this.b != null && var3.key().equals(this.b.key());
      long var10 = 99660248789245L;
      int var13 = var10 < 0L ? -1 : 1;
      if (var9x) {
         this.b = var3;
         this.t = null;
         this.k.target(1.0F).update(var5 * 1.4F);
      } else if (this.b == null) {
         this.b = var3;
         this.k.snap(0.0F);
         this.k.target(1.0F);
         this.H = false;
         this.C = -1.0F;
      } else {
         this.t = var3;
         long var13x = 99660248789244L;
         this.k.target(0.0F).update(var5 * 2.2F);
         long var16x = 99660248789244L;
         int var15 = var16x < 0L ? -1 : 1;
         if (this.k.value() < 0.03F) {
            this.b = this.t;
            this.t = null;
            this.H = false;
            this.O = Integer.MIN_VALUE;
            this.k.snap(0.0F);
            this.k.target(1.0F);
         }
      }
   }

   private void z(
      Canvas var1,
      float var2,
      float var3,
      float var4,
      SpotifyLine.LineBuffer var5,
      SpotifyLine.TrackDocument var6,
      SpotifyCurve var7,
      long var8,
      float var10,
      float var11
   ) {
      long var12 = 11454L;
      int var14 = 413858708;
      long var15 = 86903677379727L;
      long var17 = 32771947198958L;
      byte var19 = 0;
      short var20 = 7630;
      int var21 = (int)(var17 << 32 >>> 32);
      var17 = 72302974312568L;
      short var22 = 16834;
      short var23 = 22809;
      int var24 = (int)(var17 << 48 >>> 48);
      var17 = 5829864684539L;
      short var25 = 1357;
      short var26 = 24323;
      int var27 = (int)(var17 << 48 >>> 48);
      SpotifyCurve.ensureSourceClassInitialized();
      this.m(var25, var6, var26, (char)var27, var5);
      List var28 = this.G;
      if (!var28.isEmpty()) {
         double var29 = var7.lyricsTime();
         int var31 = this.U(var22, var29 + 160.0, var23, (short)var24);
         float var32 = 1.0F - (float)Math.exp(-MotionStyle.MENU.dt(var10) * 11.0F);
         float[] var33 = new float[var28.size()];
         float[] var34 = new float[var28.size()];
         float var35 = 0.0F;

         for (int var36 = 0; var36 < var28.size(); var36++) {
            SpotifyLine.LyricLine var37 = (SpotifyLine.LyricLine)var28.get(var36);
            long var38 = 43741658521145L;
            int var111 = var38 < 0L ? -1 : 1;
            float var41 = var36 == var31 ? 1.0F : 0.0F;
            long var42 = 43741658521145L;
            int var114 = var42 < 0L ? -1 : 1;
            if (!this.H) {
               long var45 = 43741658521144L;
               int var45x = var45 < 0L ? -1 : 1;
               this.M[var36] = var41;
            } else {
               this.M[var36] = this.M[var36] + (var41 - this.M[var36]) * var32;
            }

            float var112 = this.M[var36];
            float var46 = var5.h + (var5.O - var5.h) * var112;
            var33[var36] = var37.isBreak() ? var112 * lineHeight(var5.O) : var37.rows().size() * lineHeight(var46);
            var34[var36] = var35;
            var35 += var33[var36] + (var37.isBreak() ? var5.T * var112 : var5.T);
         }

         long var108 = 43741658521144L;
         int var104 = var108 < 0L ? -1 : 1;
         long var39 = 43741658521145L;
         int var107 = var39 < 0L ? -1 : 1;
         boolean var111 = var5.u >= 3;
         int var43 = Math.max(0, var31);
         int var44 = -1;

         for (int var113 = var43 - 1; var111; var113--) {
            long var115 = 43741658521145L;
            int var117 = var115 < 0L ? -1 : 1;
            if (var113 < 0 || var44 >= 0) {
               break;
            }

            if (var33[var113] >= 0.5F) {
               var44 = var113;
            }
         }

         int var114 = -1;
         long var116 = 43741658521144L;
         int var116x = var116 < 0L ? -1 : 1;

         for (int var48 = var43 + 1; var48 < var28.size(); var48++) {
            long var49 = 43741658521144L;
            int var49x = var49 < 0L ? -1 : 1;
            if (var114 >= 0) {
               break;
            }

            if (var33[var48] >= 0.5F) {
               var114 = var48;
            }
         }

         long var117x = 43741658521144L;
         int var51 = var117x < 0L ? -1 : 1;
         float var51x = var33[var43] + (var114 >= 0 ? var5.T + var33[var114] : 0.0F);
         if (var44 >= 0 && var34[var43] - var34[var44] + var51x > var4 + 0.5F) {
            var44 = -1;
         }

         float var52x = var44 >= 0 ? var34[var43] - var34[var44] : 0.0F;
         float var53 = var34[var43] - var52x;
         long var54 = 43741658521144L;
         int var53x = var54 < 0L ? -1 : 1;
         long var57x = 43741658521145L;
         int var60 = var57x < 0L ? -1 : 1;
         if (this.H && this.O != Integer.MIN_VALUE) {
            this.N.target(var53).update(MotionStyle.MENU.dt(var10));
         } else {
            this.N.snap(var53);
            this.H = true;
         }

         this.O = var31;
         float var60x = this.N.value();
         float var61 = var52x;
         int var62 = var44 >= 0 ? 1 : 0;
         int var63 = var62;

         for (int var64 = var43; var64 < var28.size(); var64++) {
            long var65 = 43741658521145L;
            int var62x = var65 < 0L ? -1 : 1;
            long var67x = 43741658521144L;
            int var64x = var67x < 0L ? -1 : 1;
            if (var62 >= var5.u) {
               break;
            }

            if (var33[var64] < 0.5F) {
               long var69 = 43741658521145L;
               int var121 = var69 < 0L ? -1 : 1;
            } else {
               var61 += var33[var64] + (var62 > var63 ? var5.T : 0.0F);
               var62++;
            }
         }

         long var119 = 43741658521145L;
         int var61x = var119 < 0L ? -1 : 1;
         var61 = Math.min(var4, Math.max(lineHeight(var5.O), var61));
         long var66x = 43741658521144L;
         int var63x = var66x < 0L ? -1 : 1;
         this.C = this.C < 0.0F ? var61 : this.C + (var61 - this.C) * var32;
         float var68x = var5.z ? var3 + var4 - this.C : var3;
         float var120x = var68x + this.C;
         var1.pushClip(var2 - 16.0F, var68x - 2.0F, var5.r + 32.0F, this.C + 4.0F);
         int var70 = 0;

         for (int var71 = 0; var71 < var28.size(); var71++) {
            SpotifyLine.LyricLine var72 = (SpotifyLine.LyricLine)var28.get(var71);
            float var73 = var33[var71];
            float var74 = var68x + var34[var71] - var60x;
            long var75 = 43741658521145L;
            int var72x = var75 < 0L ? -1 : 1;
            if (var71 > var43) {
               long var77 = 43741658521145L;
               int var74x = var77 < 0L ? -1 : 1;
               if (var73 > 0.5F) {
                  var70++;
               }
            }

            long var122x = 43741658521144L;
            int var135 = var122x < 0L ? -1 : 1;
            if (!(var73 < 0.5F)) {
               float var79 = var74 + var73;
               long var80 = 43741658521145L;
               int var82 = var80 < 0L ? -1 : 1;
               float var82x;
               int var109 = (var82x = var79 - (var68x - 1.0F)) == 0.0F ? 0 : (var82x < 0.0F ? -1 : 1);
               long var83 = 43741658521145L;
               int var86 = var83 < 0L ? -1 : 1;
               if (var109 >= 0) {
                  float var86x;
                  var109 = (var86x = var74 - (var120x + 1.0F)) == 0.0F ? 0 : (var86x < 0.0F ? -1 : 1);
                  long var87 = 43741658521144L;
                  float var123;
                  if (var109 > 0) {
                     long var90 = 43741658521145L;
                  } else {
                     float var91;
                     label438: {
                        var123 = this.M[var71];
                        long var92 = 43741658521144L;
                        int var91x = var92 < 0L ? -1 : 1;
                        label322:
                        if (var71 >= var43) {
                           if (var31 < 0) {
                              long var94 = 43741658521144L;
                              int var96 = var94 < 0L ? -1 : 1;
                              if (var71 < 0) {
                                 break label322;
                              }
                           }

                           long var126 = 43741658521145L;
                           int var145 = var126 < 0L ? -1 : 1;
                           if (var71 == var43 && var31 >= 0) {
                              long var96 = 43741658521145L;
                              int var92x = var96 < 0L ? -1 : 1;
                              var91 = 1.0F;
                              break label438;
                           }

                           var91 = Math.max(0.12F, 0.6F - 0.16F * (var70 - 1));
                           break label438;
                        }

                        var91 = 0.34F;
                     }

                     float var124 = Math.min(1.0F, var91 * var5.y) + (1.0F - Math.min(1.0F, var91 * var5.y)) * var123;
                     long var93 = 43741658521145L;
                     int var91x = var93 < 0L ? -1 : 1;
                     float var125x = var124 * F(var12, var14, (var74 + var73 - var68x) / Math.max(1.0F, var73 * 0.8F));
                     long var127 = 43741658521145L;
                     int var98 = var127 < 0L ? -1 : 1;
                     float var98x = var125x * F(var12, var14, (var120x - var74) / Math.max(1.0F, var73 * 0.8F));
                     if (var98x <= 0.01F) {
                        long var99 = 43741658521145L;
                        int var97 = var99 < 0L ? -1 : 1;
                     } else {
                        float var128 = var5.h + (var5.O - var5.h) * var123;
                        long var100 = 43741658521144L;
                        if (var72.isBreak()) {
                           this.X(var1, var2, var19, var74, var20, var5, var72, var7, var21, var123, var98x, var11);
                           long var103 = 43741658521144L;
                           int var100x = var103 < 0L ? -1 : 1;
                        } else {
                           long var129x = 43741658521145L;
                           int var153 = var129x < 0L ? -1 : 1;
                           this.D(var1, var2, var74, var5, var72, var128, var123, var98x, var15, var71 == var31, var7);
                        }
                     }
                  }
               }
            }
         }

         long var121 = 43741658521144L;
         int var132 = var121 < 0L ? -1 : 1;
         var1.popClip();
      }
   }

   private void D(
      Canvas var1,
      float var2,
      float var3,
      SpotifyLine.LineBuffer var4,
      SpotifyLine.LyricLine var5,
      float var6,
      float var7,
      float var8,
      long var9,
      boolean var11,
      SpotifyCurve var12
   ) {
      long var13 = 86130534934494L;
      Translations.pause();

      try {
         this.N(var1, var2, var13, var3, var4, var5, var6, var7, var8, var11, var12);
      } finally {
         Translations.resume();
      }
   }

   private void N(
      Canvas var1,
      float var2,
      long var3,
      float var5,
      SpotifyLine.LineBuffer var6,
      SpotifyLine.LyricLine var7,
      float var8,
      float var9,
      float var10,
      boolean var11,
      SpotifyCurve var12
   ) {
      long var13 = 114536591038982L;
      long var15 = 5666677816120L;
      SpotifyCurve.ensureSourceClassInitialized();
      float var17 = var2 + var6.r * var6.o.factor;
      float var18 = lineHeight(var8);
      int var19 = BlendMode.lerp(Theme.tint(-3292180), -1, var9);
      float var20 = var11 && var6.I ? SpotifySyllables.position(var7.line(), var7.end(), (long)var12.lyricsTime()) : -1.0F;

      for (int var21 = 0; var21 < var7.rows().size(); var21++) {
         String var22 = var7.rows().get(var21);
         float var23 = var5 + var21 * var18 + (var18 - var8) * 0.5F;
         long var24 = 86130534934494L;
         int var32 = var24 < 0L ? -1 : 1;
         if (var6.U > 0.0F) {
            a(var1, var17, var23, h.width(var22, var8), var15, var8, var6, var10);
         }

         if (var6.b) {
            var1.textGlow(h, var22, var17, var23 + var8 * 0.06F, var8, BlendMode.withAlpha(-16777216, 0.6F * var10), var6.o, FontWeight.TOP, 0.0F, 0.7F, 2.2F);
         }

         if (var6.x) {
            long var27 = 86130534934493L;
            int var29 = var27 < 0L ? -1 : 1;
            if (var9 > 0.02F) {
               var1.textGlow(h, var22, var17, var23, var8, BlendMode.withAlpha(var6.l, 0.34F * var9 * var10), var6.o, FontWeight.TOP, 0.0F, 0.45F, 3.2F);
            }
         }

         if (var20 >= 0.0F) {
            float var32x = var17 - h.width(var22, var8) * var6.o.factor;
            float var28 = D(var22, var20 - var7.rowStarts()[var21], var13, var32x, var8);
            long var29 = 86130534934494L;
            int var30 = var29 < 0L ? -1 : 1;
            var1.textSweep(
               h,
               var22,
               var17,
               var23,
               var8,
               BlendMode.withAlpha(-1, var10),
               BlendMode.withAlpha(Theme.tint(-1647105), Math.min(1.0F, 0.46F * var6.y) * var10),
               var28,
               var8 * 0.9F,
               var6.o,
               FontWeight.TOP,
               0.0F
            );
         } else {
            var1.text(h, var22, var17, var23, var8, BlendMode.withAlpha(var19, var10), var6.o, FontWeight.TOP, 0.0F);
         }
      }
   }

   private static void a(Canvas var0, float var1, float var2, float var3, long var4, float var6, SpotifyLine.LineBuffer var7, float var8) {
      float var9 = var1 - var3 * var7.o.factor;
      var0.shadow(
         var9 - var6 * 0.5F,
         var2 - var6 * 0.14F,
         var3 + var6,
         var6 * 1.28F,
         var6 * 0.64F,
         var6 * 1.6F,
         BlendMode.withAlpha(Theme.tint(-15857632), 0.5F * var7.U * var8)
      );
   }

   private static float D(String var0, float var1, long var2, float var4, float var5) {
      SpotifyCurve.ensureSourceClassInitialized();
      if (var1 <= 0.0F) {
         return var4 - var5 * 2.0F;
      } else {
         long var6 = 114536591038981L;
         int var9 = var6 < 0L ? -1 : 1;
         if (var1 >= var0.length()) {
            return var4 + h.width(var0, var5) + var5 * 2.0F;
         } else {
            var9 = (int)var1;
            float var10 = h.width(var0.substring(0, var9), var5);
            float var11 = h.width(var0.substring(var9, var9 + 1), var5);
            return var4 + var10 + var11 * (var1 - var9);
         }
      }
   }

   private void X(
      Canvas var1,
      float var2,
      short var3,
      float var4,
      short var5,
      SpotifyLine.LineBuffer var6,
      SpotifyLine.LyricLine var7,
      SpotifyCurve var8,
      int var9,
      float var10,
      float var11,
      float var12
   ) {
      long var13 = 32771947198958L;
      long var15 = (var13 ^ 54358329754234L) >>> 32;
      int var17 = (int)((var13 ^ 54358329754234L) << 32 >>> 32);
      long var18 = var13 ^ 27389813064406L;
      double var20 = var8.lyricsTime();
      SpotifyCurve.ensureSourceClassInitialized();
      float var22 = F(var15, var17, (float)((var20 - var7.start()) / Math.max(1L, var7.end() - var7.start())));
      float var23 = F(var15, var17, (float)((var7.end() - var20) / 350.0));
      float var24 = var6.O;
      float var25 = lineHeight(var24) * var10;
      float var26 = var4 + var25 * 0.5F;
      float var27 = var24 * 0.62F;
      float var28 = var24 * 0.15F;
      float var29 = 1.0F + 0.07F * (float)Math.sin(var12 * 2.6) + 0.16F * var8.level();
      float var30 = var27 * 2.0F + var28 * 2.0F;
      float var31 = var2 + var6.r * var6.o.factor - var30 * var6.o.factor + var28;
      if (var6.U > 0.0F) {
         a(var1, var2 + var6.r * var6.o.factor, var26 - var24 * 0.5F, var30, var18, var24, var6, var11 * var10);
      }

      for (int var32 = 0; var32 < 3; var32++) {
         float var33 = F(var15, var17, var22 * 3.0F - var32);
         float var34 = var28 * var29 * var10 * (0.55F + 0.45F * var23) * (0.86F + 0.14F * var33);
         float var35 = var31 + var32 * var27;
         int var36 = BlendMode.lerp(Theme.tint(-2133209099), -1, var33);
         if (var6.x && var33 > 0.0F) {
            var1.glowCircle(var35, var26, var34, var34 * 2.4F, BlendMode.withAlpha(var6.l, 0.35F * var33 * var11 * var10));
         }

         if (var6.b) {
            var1.softCircle(var35, var26 + var34 * 0.2F, var34 * 1.2F, var34 * 1.4F, BlendMode.withAlpha(-16777216, 0.35F * var11));
         }

         var1.circle(var35, var26, var34, BlendMode.fade(var36, var11 * var23));
      }
   }

   private void a(
      Canvas var1,
      float var2,
      char var3,
      short var4,
      float var5,
      float var6,
      SpotifyLine.LineBuffer var7,
      SpotifyLine.TrackDocument var8,
      SpotifyCurve var9,
      float var10,
      float var11,
      int var12
   ) {
      long var13 = 4299545102264L;
      long var15 = var13 ^ 7483721170048L;
      SpotifyCurve.ensureSourceClassInitialized();

      String var17 = switch (SpotifyLine.LineState.x[var8.state().ordinal()]) {
         case 1 -> "Looking for lyrics" + ".".repeat(1 + (int)(var11 * 2.5F) % 3);
         case 2 -> "Lyrics are offline right now";
         case 3 -> {
            int var18 = SpotifyLine.LineState.L[var8.lyrics().kind().ordinal()];
            switch (var18) {
               case 1:
                  yield "Instrumental";
               case 2:
                  yield "The lyrics of this song are not synced";
               default:
                  yield "No lyrics for this song";
            }
         }
         default -> throw new MatchException(null, null);
      };
      int var41 = SpotifyLine.LineState.x[var8.state().ordinal()];

      String var19 = switch (var41) {
         case 1 -> "search";
         case 2 -> "wifi-off";
         default -> var8.lyrics().kind() == SpotifyLyrics.LyricStyle.I ? "audio-lines" : "music";
      };
      SpotifyRegistry var20 = SpotifyRegistry.get();
      String var21 = var20.shown(var8.title());
      String var22 = var20.shown(var8.artist());
      float var23 = var7.O * 0.86F;
      float var24 = Math.max(8.5F, var7.h * 0.78F);
      float var25 = lineHeight(var23) + 3.0F + lineHeight(var24);
      float var26 = 1.0F - (float)Math.exp(-MotionStyle.MENU.dt(var10) * 11.0F);
      this.C = this.C < 0.0F ? var25 : this.C + (var25 - this.C) * var26;
      float var27 = var7.z ? var5 + var6 - this.C : var5;
      float var28 = var27 + (this.C - var25) / 2.0F;
      String var29 = var22.isEmpty() ? var21 : var21 + "  ·  " + var22;
      String var30 = Translations.tr(var17);
      Translations.pause();
      String var31 = FontMetrics.ellipsize(Fonts.SEMIBOLD, var29, var23, var7.r - var23 * 1.6F, 0.0F);
      float var32 = var1.textWidth(Fonts.SEMIBOLD, var31, var23);
      float var33 = var23 * 0.95F;
      float var34 = var33 + var23 * 0.5F + var32;
      float var35 = var2 + (var7.r - var34) * var7.o.factor;
      float var36 = var28 + (lineHeight(var23) - var23) * 0.5F;
      float var37 = var8.state() == SpotifyCurve.CurveKind.i ? 0.55F + 0.45F * (float)Math.sin(var11 * 3.2) : 1.0F;
      float var38 = var28 + lineHeight(var23) + 3.0F + (lineHeight(var24) - var24) * 0.5F;
      float var39 = var2 + var7.r * var7.o.factor;
      if (var7.U > 0.0F) {
         a(var1, var35 + var34 * var7.o.factor, var36, var34, var15, var23, var7, 1.0F);
         a(var1, var39, var38, Fonts.MEDIUM.width(var30, var24), var15, var24, var7, 0.8F);
      }

      if (var7.b) {
         var1.textGlow(Fonts.SEMIBOLD, var31, var35 + var33 + var23 * 0.5F, var36 + 1.0F, var23, -1728053248, FontStyle.LEFT, FontWeight.TOP, 0.0F, 0.7F, 2.2F);
         var1.softCircle(var35 + var33 / 2.0F, var36 + var23 * 0.55F, var33 * 0.45F, var33, 1711276032);
      }

      var1.icon(var19, var35 + var33 / 2.0F, var36 + var23 * 0.55F, var33, BlendMode.withAlpha(BlendMode.lerp(var7.l, -1, 0.35F), var37));
      float var40 = var35 + var33;
      var1.text(Fonts.SEMIBOLD, var31, var40 + var23 * 0.5F, var36, var23, Theme.tint(-790785), FontStyle.LEFT, FontWeight.TOP);
      Translations.resume();
      if (var7.b) {
         var1.textGlow(Fonts.MEDIUM, var30, var39, var38 + 1.0F, var24, -1728053248, var7.o, FontWeight.TOP, 0.0F, 0.6F, 2.0F);
      }

      var1.text(Fonts.MEDIUM, var30, var39, var38, var24, Theme.tint(-5791804), var7.o, FontWeight.TOP);
   }

   private int U(int var1, double var2, int var4, short var5) {
      int var6 = 0;
      SpotifyCurve.ensureSourceClassInitialized();
      int var7 = this.G.size() - 1;
      int var8 = -1;

      while (var6 <= var7) {
         short var9 = 16834;
         int var10 = var6 + var7 >>> 1;
         if (this.G.get(var10).start() <= var2) {
            var9 = -24456;
            var8 = var10;
            var6 = var10 + 1;
         } else {
            var7 = var10 - 1;
         }
      }

      return var8;
   }

   private void m(int var1, SpotifyLine.TrackDocument var2, int var3, char var4, SpotifyLine.LineBuffer var5) {
      SpotifyCurve.ensureSourceClassInitialized();
      if (this.w != var2.lyrics() || this.r != var5.r || this.s != var5.O) {
         this.w = var2.lyrics();
         this.r = var5.r;
         this.s = var5.O;
         SpotifyLyrics var6 = var2.lyrics();
         List var7 = var6.lines();
         long var8 = var2.duration();
         short var10 = 24323;
         long var11 = var8 > 0L ? var2.duration() : ((SpotifyLyrics.Data)var7.getLast()).startMs() + 8000L;
         ArrayList var13 = new ArrayList();
         if (((SpotifyLyrics.Data)var7.getFirst()).startMs() >= 2500L && !((SpotifyLyrics.Data)var7.getFirst()).isBreak()) {
            var13.add(T(0L, ((SpotifyLyrics.Data)var7.getFirst()).startMs()));
         }

         SpotifyRegistry var14 = SpotifyRegistry.get();

         for (int var15 = 0; var15 < var7.size(); var15++) {
            SpotifyLyrics.Data var16 = (SpotifyLyrics.Data)var7.get(var15);
            long var17 = var6.nextStart(var15, var11);
            var10 = 31739;
            boolean var19 = var16.isBreak();
            var10 = 24323;
            if (var19) {
               var13.add(T(var16.startMs(), var17));
            } else {
               long var20 = SpotifySyllables.sungEnd(var6, var15, var11);
               String var22 = var14.shown(var16.text());
               SpotifyLyrics.Data var23 = var22.equals(var16.text()) ? var16 : new SpotifyLyrics.Data(var16.startMs(), var22, List.of(), var16.endMs());
               Translations.pause();
               List var24 = FontMetrics.wrap(h, var22, var5.O, var5.r, 0.0F);
               Translations.resume();
               int[] var25 = new int[var24.size()];
               int var26 = 0;

               for (int var27 = 0; var27 < var24.size(); var27++) {
                  int var28 = var22.indexOf((String)var24.get(var27), var26);
                  var10 = 24323;
                  var25[var27] = var28 >= 0 ? var28 : var26;
                  var26 = var25[var27] + ((String)var24.get(var27)).length();
               }

               var13.add(new SpotifyLine.LyricLine(var16.startMs(), var20, var23, var22, var24, var25, false));
               boolean var36 = var15 + 1 < var7.size() && ((SpotifyLyrics.Data)var7.get(var15 + 1)).isBreak();
               var10 = 24323;
               var10 = 1357;
               if (!var36 && var17 - var20 >= 6500L) {
                  var13.add(T(var20 + 1400L, var17));
               }
            }
         }

         var10 = 24323;
         this.G = var13;
         this.M = new float[var13.size()];
         this.H = false;
      }
   }

   private static SpotifyLine.LyricLine T(long var0, long var2) {
      return new SpotifyLine.LyricLine(var0, var2, null, "", List.of(), new int[0], true);
   }

   private static float F(long var0, int var2, float var3) {
      SpotifyCurve.ensureSourceClassInitialized();
      return var3 < 0.0F ? 0.0F : Math.min(1.0F, var3);
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 7611) & 32767;
      if (g[var3] == null) {
         g[var3] = f[var3] ^ var1;
      }

      return g[var3];
   }

   private record LyricLine(long t, long h, SpotifyLyrics.Data p, String e, List<String> b, int[] m, boolean K) {
      public long start() {
         return this.t;
      }

      public long end() {
         return this.h;
      }

      public SpotifyLyrics.Data line() {
         return this.p;
      }

      public String text() {
         return this.e;
      }

      public List<String> rows() {
         return this.b;
      }

      public int[] rowStarts() {
         return this.m;
      }

      public boolean isBreak() {
         return this.K;
      }

      public long longState2() {
         return this.t;
      }

      public long longState32() {
         return this.h;
      }

      public SpotifyLyrics.Data lyricsDocument_TimedLyricsLineState1() {
         return this.p;
      }

      public String stringState41() {
         return this.e;
      }

      public List<String> listState21() {
         return this.b;
      }

      public int[] integerArrayState26() {
         return this.m;
      }

      public boolean booleanState10() {
         return this.K;
      }
   }

   static final class LineBuffer {
      float r = 380.0F;
      int u = 2;
      float O = 17.0F;
      float h = 12.5F;
      float T = 5.0F;
      FontStyle o = FontStyle.CENTER;
      boolean I = true;
      boolean x = true;
      boolean b;
      float U;
      float y = 1.0F;
      boolean z;
      int l = Icons.PURPLE;
   }

   static class LineState {
      static final int[] L;
      static final int[] x = new int[SpotifyCurve.CurveKind.values().length];

      static {
         try {
            x[SpotifyCurve.CurveKind.i.ordinal()] = 1;
         } catch (NoSuchFieldError var5) {
         }

         try {
            x[SpotifyCurve.CurveKind.Z.ordinal()] = 2;
         } catch (NoSuchFieldError var4) {
         }

         try {
            x[SpotifyCurve.CurveKind.h.ordinal()] = 3;
         } catch (NoSuchFieldError var3) {
         }

         L = new int[SpotifyLyrics.LyricStyle.values().length];

         try {
            L[SpotifyLyrics.LyricStyle.I.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
         }

         try {
            L[SpotifyLyrics.LyricStyle.g.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
         }
      }
   }

   private record TrackDocument(String Y, SpotifyLyrics n, SpotifyCurve.CurveKind Z, String u, String A, long d) {
      public String key() {
         return this.Y;
      }

      public SpotifyLyrics lyrics() {
         return this.n;
      }

      public SpotifyCurve.CurveKind state() {
         return this.Z;
      }

      public String title() {
         return this.u;
      }

      public String artist() {
         return this.A;
      }

      public long duration() {
         return this.d;
      }

      public String stringState8() {
         return this.Y;
      }

      public SpotifyLyrics lyricsDocumentState3() {
         return this.n;
      }

      public SpotifyCurve.CurveKind mediaPlaybackModel_LyricsAvailabilityState4() {
         return this.Z;
      }

      public String stringState4() {
         return this.u;
      }

      public String stringState36() {
         return this.A;
      }

      public long longState50() {
         return this.d;
      }
   }
}
