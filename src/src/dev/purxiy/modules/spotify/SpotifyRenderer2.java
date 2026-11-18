// Sourced By DevFFNightmare
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

final class SpotifyRenderer2 {
   static final long B = 160L;
   private static final long x = 2500L;
   private static final long m = 6500L;
   private static final long S = 1400L;
   private static final float q = 1.2F;
   private static final TrueTypeFont h = Fonts.BOLD;
   
   private SpotifyRenderer2.TypeS b;
   
   private SpotifyRenderer2.TypeS t;
   private final Animator k = Animator.smooth(0.0F);
   private List<SpotifyRenderer2.TypeB> G = List.of();
   private float[] M = new float[0];
   private float r = -1.0F;
   private float s = -1.0F;
   
   private SpotifySupport3 w;
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

   static float reservedHeight(SpotifyRenderer2.TypeH var0) {
      float var1 = 2.0F * lineHeight(var0.O);
      int var2 = Math.max(0, var0.u - 1);
      return var1 + var2 * (lineHeight(var0.h) + var0.T);
   }

   float contentHeight(SpotifyRenderer2.TypeH var1) {
      return this.C < 0.0F ? reservedHeight(var1) : this.C;
   }

   void render(Canvas var1, float var2, float var3, SpotifyRenderer2.TypeH var4, SpotifyCurve var5, float var6, float var7) {
      long var8 = 136447538534136L;
      long var10 = var8 ^ 42584041498117L;
      long var12 = var8 ^ 100954499451073L;
      long var14 = var8 ^ 140669772058944L;
      int var16 = (int)((var8 ^ 140669772058944L) >>> 48);
      int var17 = (int)((var8 ^ 140669772058944L) << 16 >>> 48);
      int var18 = (int)(var14 << 32 >>> 32);
      SpotifyCurve.Data var19 = var5.track();
      SpotifyRenderer2.TypeS var20 = var19 == null
         ? null
         : new SpotifyRenderer2.TypeS(
            var19.key() + "|" + var5.lyricsState() + "|" + var5.lyrics().kind(),
            var5.lyrics(),
            var5.lyricsState(),
            var19.title(),
            var19.artist(),
            var19.durationMs()
         );
      this.z(var10, var20, var6);
      SpotifyRenderer2.TypeS var21 = this.b;
      if (var21 != null) {
         float var22 = reservedHeight(var4);
         float var23 = this.k.value();
         var1.pushAlpha(var23);
         if (var21.lyrics().synced() && var21.state() == SpotifyCurve.TypeF.h) {
            this.z(var1, var2, var3, var22, var4, var21, var5, var12, var6, var7);
         } else {
            this.a(var1, var2, (char)var16, (short)var17, var3, var22, var4, var21, var5, var6, var7, var18);
         }

         var1.popAlpha();
      }
   }

   private void z( long var1, SpotifyRenderer2.TypeS var3, float var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      float var5 = MotionStyle.HOVER.dt(var4);
      long var6 = 99660248789244L;
      if (99660248789244L == 0L) {
         boolean var10000 = false;
      } else {
         int var18 = var6 < 0L ? -1 : 1;
      }

      boolean var9 = var3 != null && this.b != null && var3.key().equals(this.b.key());
      long var10 = 99660248789245L;
      if (99660248789245L == 0L) {
         boolean var19 = false;
      } else {
         int var20 = var10 < 0L ? -1 : 1;
      }

      if (var9) {
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
         long var13 = 99660248789244L;
         if (99660248789244L == 0L) {
            boolean var21 = false;
         } else {
            int var22 = var13 < 0L ? -1 : 1;
         }

         this.k.target(0.0F).update(var5 * 2.2F);
         long var16 = 99660248789244L;
         if (99660248789244L == 0L) {
            boolean var23 = false;
         } else {
            int var24 = var16 < 0L ? -1 : 1;
         }

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
      SpotifyRenderer2.TypeH var5,
      SpotifyRenderer2.TypeS var6,
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
            SpotifyRenderer2.TypeB var37 = (SpotifyRenderer2.TypeB)var28.get(var36);
            long var38 = 43741658521145L;
            if (43741658521145L == 0L) {
               boolean var10000 = false;
            } else {
               int var130 = var38 < 0L ? -1 : 1;
            }

            float var41 = var36 == var31 ? 1.0F : 0.0F;
            long var42 = 43741658521145L;
            if (43741658521145L == 0L) {
               boolean var131 = false;
            } else {
               int var132 = var42 < 0L ? -1 : 1;
            }

            if (!this.H) {
               long var45 = 43741658521144L;
               if (43741658521144L == 0L) {
                  boolean var133 = false;
               } else {
                  int var134 = var45 < 0L ? -1 : 1;
               }

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
         if (43741658521144L == 0L) {
            boolean var135 = false;
         } else {
            int var136 = var108 < 0L ? -1 : 1;
         }

         long var39 = 43741658521145L;
         if (43741658521145L == 0L) {
            boolean var137 = false;
         } else {
            int var138 = var39 < 0L ? -1 : 1;
         }

         boolean var111 = var5.u >= 3;
         int var43 = Math.max(0, var31);
         int var44 = -1;

         for (int var113 = var43 - 1; var111; var113--) {
            long var115 = 43741658521145L;
            if (43741658521145L == 0L) {
               boolean var139 = false;
            } else {
               int var140 = var115 < 0L ? -1 : 1;
            }

            if (var113 < 0 || var44 >= 0) {
               break;
            }

            if (var33[var113] >= 0.5F) {
               var44 = var113;
            }
         }

         int var114 = -1;
         long var116 = 43741658521144L;
         if (43741658521144L == 0L) {
            boolean var141 = false;
         } else {
            int var142 = var116 < 0L ? -1 : 1;
         }

         for (int var48 = var43 + 1; var48 < var28.size(); var48++) {
            long var49 = 43741658521144L;
            if (43741658521144L == 0L) {
               boolean var143 = false;
            } else {
               int var144 = var49 < 0L ? -1 : 1;
            }

            if (var114 >= 0) {
               break;
            }

            if (var33[var48] >= 0.5F) {
               var114 = var48;
            }
         }

         long var117 = 43741658521144L;
         if (43741658521144L == 0L) {
            boolean var145 = false;
         } else {
            int var146 = var117 < 0L ? -1 : 1;
         }

         float var51 = var33[var43] + (var114 >= 0 ? var5.T + var33[var114] : 0.0F);
         if (var44 >= 0 && var34[var43] - var34[var44] + var51 > var4 + 0.5F) {
            var44 = -1;
         }

         float var52 = var44 >= 0 ? var34[var43] - var34[var44] : 0.0F;
         float var53 = var34[var43] - var52;
         long var54 = 43741658521144L;
         if (43741658521144L == 0L) {
            boolean var147 = false;
         } else {
            int var148 = var54 < 0L ? -1 : 1;
         }

         long var57 = 43741658521145L;
         if (43741658521145L == 0L) {
            boolean var149 = false;
         } else {
            int var150 = var57 < 0L ? -1 : 1;
         }

         if (this.H && this.O != Integer.MIN_VALUE) {
            this.N.target(var53).update(MotionStyle.MENU.dt(var10));
         } else {
            this.N.snap(var53);
            this.H = true;
         }

         this.O = var31;
         float var60 = this.N.value();
         float var61 = var52;
         int var62 = var44 >= 0 ? 1 : 0;
         int var63 = var62;

         for (int var64 = var43; var64 < var28.size(); var64++) {
            long var65 = 43741658521145L;
            if (43741658521145L == 0L) {
               boolean var151 = false;
            } else {
               int var152 = var65 < 0L ? -1 : 1;
            }

            long var67 = 43741658521144L;
            if (43741658521144L == 0L) {
               boolean var153 = false;
            } else {
               int var154 = var67 < 0L ? -1 : 1;
            }

            if (var62 >= var5.u) {
               break;
            }

            if (var33[var64] < 0.5F) {
               long var69 = 43741658521145L;
               if (43741658521145L == 0L) {
                  boolean var155 = false;
               } else {
                  int var156 = var69 < 0L ? -1 : 1;
               }
            } else {
               var61 += var33[var64] + (var62 > var63 ? var5.T : 0.0F);
               var62++;
            }
         }

         long var119 = 43741658521145L;
         if (43741658521145L == 0L) {
            boolean var157 = false;
         } else {
            int var158 = var119 < 0L ? -1 : 1;
         }

         var61 = Math.min(var4, Math.max(lineHeight(var5.O), var61));
         long var66 = 43741658521144L;
         if (43741658521144L == 0L) {
            boolean var159 = false;
         } else {
            int var160 = var66 < 0L ? -1 : 1;
         }

         this.C = this.C < 0.0F ? var61 : this.C + (var61 - this.C) * var32;
         float var68 = var5.z ? var3 + var4 - this.C : var3;
         float var120 = var68 + this.C;
         var1.pushClip(var2 - 16.0F, var68 - 2.0F, var5.r + 32.0F, this.C + 4.0F);
         int var70 = 0;

         for (int var71 = 0; var71 < var28.size(); var71++) {
            SpotifyRenderer2.TypeB var72 = (SpotifyRenderer2.TypeB)var28.get(var71);
            float var73 = var33[var71];
            float var74 = var68 + var34[var71] - var60;
            long var75 = 43741658521145L;
            if (43741658521145L == 0L) {
               boolean var161 = false;
            } else {
               int var162 = var75 < 0L ? -1 : 1;
            }

            if (var71 > var43) {
               long var77 = 43741658521145L;
               if (43741658521145L == 0L) {
                  boolean var163 = false;
               } else {
                  int var164 = var77 < 0L ? -1 : 1;
               }

               if (var73 > 0.5F) {
                  var70++;
               }
            }

            long var122 = 43741658521144L;
            if (43741658521144L == 0L) {
               boolean var165 = false;
            } else {
               int var166 = var122 < 0L ? -1 : 1;
            }

            if (!(var73 < 0.5F)) {
               float var79 = var74 + var73;
               long var80 = 43741658521145L;
               if (43741658521145L == 0L) {
                  boolean var167 = false;
               } else {
                  int var168 = var80 < 0L ? -1 : 1;
               }

               float var82;
               int var109 = (var82 = var79 - (var68 - 1.0F)) == 0.0F ? 0 : (var82 < 0.0F ? -1 : 1);
               long var83 = 43741658521145L;
               if (43741658521145L == 0L) {
                  boolean var169 = false;
               } else {
                  int var170 = var83 < 0L ? -1 : 1;
               }

               if (var109 >= 0) {
                  float var86;
                  var109 = (var86 = var74 - (var120 + 1.0F)) == 0.0F ? 0 : (var86 < 0.0F ? -1 : 1);
                  long var87 = 43741658521144L;
                  if (43741658521144L == 0L) {
                     boolean var171 = false;
                  } else {
                     int var172 = var87 < 0L ? -1 : 1;
                  }

                  if (var109 > 0) {
                     long var90 = 43741658521145L;
                     if (43741658521145L == 0L) {
                        boolean var173 = false;
                     } else {
                        int var174 = var90 < 0L ? -1 : 1;
                     }
                  } else {
                     float var123 = this.M[var71];
                     long var92 = 43741658521144L;
                     if (43741658521144L == 0L) {
                        boolean var175 = false;
                     } else {
                        int var176 = var92 < 0L ? -1 : 1;
                     }

                     float var91;
                     label540: {
                        label424:
                        if (var71 >= var43) {
                           if (var31 < 0) {
                              long var94 = 43741658521144L;
                              if (43741658521144L == 0L) {
                                 boolean var177 = false;
                              } else {
                                 int var178 = var94 < 0L ? -1 : 1;
                              }

                              if (var71 < 0) {
                                 break label424;
                              }
                           }

                           long var126 = 43741658521145L;
                           if (43741658521145L == 0L) {
                              boolean var179 = false;
                           } else {
                              int var180 = var126 < 0L ? -1 : 1;
                           }

                           if (var71 == var43 && var31 >= 0) {
                              long var96 = 43741658521145L;
                              if (43741658521145L == 0L) {
                                 boolean var181 = false;
                              } else {
                                 int var182 = var96 < 0L ? -1 : 1;
                              }

                              var91 = 1.0F;
                              break label540;
                           }

                           var91 = Math.max(0.12F, 0.6F - 0.16F * (var70 - 1));
                           break label540;
                        }

                        var91 = 0.34F;
                     }

                     float var124 = Math.min(1.0F, var91 * var5.y) + (1.0F - Math.min(1.0F, var91 * var5.y)) * var123;
                     long var93 = 43741658521145L;
                     if (43741658521145L == 0L) {
                        boolean var183 = false;
                     } else {
                        int var184 = var93 < 0L ? -1 : 1;
                     }

                     float var125 = var124 * F(var12, var14, (var74 + var73 - var68) / Math.max(1.0F, var73 * 0.8F));
                     long var127 = 43741658521145L;
                     if (43741658521145L == 0L) {
                        boolean var185 = false;
                     } else {
                        int var186 = var127 < 0L ? -1 : 1;
                     }

                     float var98 = var125 * F(var12, var14, (var120 - var74) / Math.max(1.0F, var73 * 0.8F));
                     if (var98 <= 0.01F) {
                        long var99 = 43741658521145L;
                        if (43741658521145L == 0L) {
                           boolean var187 = false;
                        } else {
                           int var188 = var99 < 0L ? -1 : 1;
                        }
                     } else {
                        float var128 = var5.h + (var5.O - var5.h) * var123;
                        long var100 = 43741658521144L;
                        if (43741658521144L == 0L) {
                           boolean var189 = false;
                        } else {
                           int var190 = var100 < 0L ? -1 : 1;
                        }

                        if (var72.isBreak()) {
                           this.X(var1, var2, var19, var74, (short)var20, var5, var72, var7, var21, var123, var98, var11);
                           long var103 = 43741658521144L;
                           if (43741658521144L == 0L) {
                              boolean var191 = false;
                           } else {
                              int var192 = var103 < 0L ? -1 : 1;
                           }
                        } else {
                           long var129 = 43741658521145L;
                           if (43741658521145L == 0L) {
                              boolean var193 = false;
                           } else {
                              int var194 = var129 < 0L ? -1 : 1;
                           }

                           this.D(var1, var2, var74, var5, var72, var128, var123, var98, var15, var71 == var31, var7);
                        }
                     }
                  }
               }
            }
         }

         long var121 = 43741658521144L;
         if (43741658521144L == 0L) {
            boolean var195 = false;
         } else {
            int var196 = var121 < 0L ? -1 : 1;
         }

         var1.popClip();
      }
   }

   private void D(
      Canvas var1,
      float var2,
      float var3,
      SpotifyRenderer2.TypeH var4,
      SpotifyRenderer2.TypeB var5,
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
      SpotifyRenderer2.TypeH var6,
      SpotifyRenderer2.TypeB var7,
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
      float var20 = var11 && var6.I ? SpotifySupport4.position(var7.line(), var7.end(), (long)var12.lyricsTime()) : -1.0F;

      for (int var21 = 0; var21 < var7.rows().size(); var21++) {
         String var22 = var7.rows().get(var21);
         float var23 = var5 + var21 * var18 + (var18 - var8) * 0.5F;
         long var24 = 86130534934494L;
         if (86130534934494L == 0L) {
            boolean var10000 = false;
         } else {
            int var33 = var24 < 0L ? -1 : 1;
         }

         if (var6.U > 0.0F) {
            a(var1, var17, var23, h.width(var22, var8), var15, var8, var6, var10);
         }

         if (var6.b) {
            var1.textGlow(h, var22, var17, var23 + var8 * 0.06F, var8, BlendMode.withAlpha(-16777216, 0.6F * var10), var6.o, FontWeight.TOP, 0.0F, 0.7F, 2.2F);
         }

         if (var6.x) {
            long var27 = 86130534934493L;
            if (86130534934493L == 0L) {
               boolean var34 = false;
            } else {
               int var35 = var27 < 0L ? -1 : 1;
            }

            if (var9 > 0.02F) {
               var1.textGlow(h, var22, var17, var23, var8, BlendMode.withAlpha(var6.l, 0.34F * var9 * var10), var6.o, FontWeight.TOP, 0.0F, 0.45F, 3.2F);
            }
         }

         if (var20 >= 0.0F) {
            float var32 = var17 - h.width(var22, var8) * var6.o.factor;
            float var28 = D(var22, var20 - var7.rowStarts()[var21], var13, var32, var8);
            long var29 = 86130534934494L;
            if (86130534934494L == 0L) {
               boolean var36 = false;
            } else {
               int var37 = var29 < 0L ? -1 : 1;
            }

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

   private static void a(Canvas var0, float var1, float var2, float var3, long var4, float var6, SpotifyRenderer2.TypeH var7, float var8) {
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
         if (114536591038981L == 0L) {
            boolean var10000 = false;
         } else {
            int var12 = var6 < 0L ? -1 : 1;
         }

         if (var1 >= var0.length()) {
            return var4 + h.width(var0, var5) + var5 * 2.0F;
         } else {
            int var9 = (int)var1;
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
      SpotifyRenderer2.TypeH var6,
      SpotifyRenderer2.TypeB var7,
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
      SpotifyRenderer2.TypeH var7,
      SpotifyRenderer2.TypeS var8,
      SpotifyCurve var9,
      float var10,
      float var11,
      int var12
   ) {
      long var13 = 4299545102264L;
      long var15 = var13 ^ 7483721170048L;
      SpotifyCurve.ensureSourceClassInitialized();

      String var17 = switch (SpotifyRenderer2.TypeN.x[var8.state().ordinal()]) {
         case 1 -> "Looking for lyrics" + ".".repeat(1 + (int)(var11 * 2.5F) % 3);
         case 2 -> "Lyrics are offline right now";
         case 3 -> {
            int var18 = SpotifyRenderer2.TypeN.L[var8.lyrics().kind().ordinal()];
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
      int var41 = SpotifyRenderer2.TypeN.x[var8.state().ordinal()];

      String var19 = switch (var41) {
         case 1 -> "search";
         case 2 -> "wifi-off";
         default -> var8.lyrics().kind() == SpotifySupport3.TypeK.I ? "audio-lines" : "music";
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
      float var37 = var8.state() == SpotifyCurve.TypeF.i ? 0.55F + 0.45F * (float)Math.sin(var11 * 3.2) : 1.0F;
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

   private void m(int var1, SpotifyRenderer2.TypeS var2, int var3, char var4, SpotifyRenderer2.TypeH var5) {
      SpotifyCurve.ensureSourceClassInitialized();
      if (this.w != var2.lyrics() || this.r != var5.r || this.s != var5.O) {
         this.w = var2.lyrics();
         this.r = var5.r;
         this.s = var5.O;
         SpotifySupport3 var6 = var2.lyrics();
         List var7 = var6.lines();
         long var8 = var2.duration();
         short var10 = 24323;
         long var11 = var8 > 0L ? var2.duration() : ((SpotifySupport3.Data)var7.getLast()).startMs() + 8000L;
         ArrayList var13 = new ArrayList();
         if (((SpotifySupport3.Data)var7.getFirst()).startMs() >= 2500L && !((SpotifySupport3.Data)var7.getFirst()).isBreak()) {
            var13.add(T(0L, ((SpotifySupport3.Data)var7.getFirst()).startMs()));
         }

         SpotifyRegistry var14 = SpotifyRegistry.get();

         for (int var15 = 0; var15 < var7.size(); var15++) {
            SpotifySupport3.Data var16 = (SpotifySupport3.Data)var7.get(var15);
            long var17 = var6.nextStart(var15, var11);
            var10 = 31739;
            boolean var19 = var16.isBreak();
            var10 = 24323;
            if (var19) {
               var13.add(T(var16.startMs(), var17));
            } else {
               long var20 = SpotifySupport4.sungEnd(var6, var15, var11);
               String var22 = var14.shown(var16.text());
               SpotifySupport3.Data var23 = var22.equals(var16.text()) ? var16 : new SpotifySupport3.Data(var16.startMs(), var22, List.of(), var16.endMs());
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

               var13.add(new SpotifyRenderer2.TypeB(var16.startMs(), var20, var23, var22, var24, var25, false));
               boolean var36 = var15 + 1 < var7.size() && ((SpotifySupport3.Data)var7.get(var15 + 1)).isBreak();
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

   private static SpotifyRenderer2.TypeB T(long var0, long var2) {
      return new SpotifyRenderer2.TypeB(var0, var2, null, "", List.of(), new int[0], true);
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

private record TypeB(long t, long h,  SpotifySupport3.Data p, String e, List<String> b, int[] m, boolean K) {
      public long start() {
         return this.t;
      }

      public long end() {
         return this.h;
      }

      
      public SpotifySupport3.Data line() {
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

      
      public SpotifySupport3.Data lyricsDocument_TimedLyricsLineState1() {
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

   static final class TypeH {
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

   static class TypeN {
      static final int[] L;
      static final int[] x = new int[SpotifyCurve.TypeF.values().length];

      static {
         try {
            x[SpotifyCurve.TypeF.i.ordinal()] = 1;
         } catch (NoSuchFieldError var5) {
         }

         try {
            x[SpotifyCurve.TypeF.Z.ordinal()] = 2;
         } catch (NoSuchFieldError var4) {
         }

         try {
            x[SpotifyCurve.TypeF.h.ordinal()] = 3;
         } catch (NoSuchFieldError var3) {
         }

         L = new int[SpotifySupport3.TypeK.values().length];

         try {
            L[SpotifySupport3.TypeK.I.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
         }

         try {
            L[SpotifySupport3.TypeK.g.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
         }
      }
   }

   private record TypeS(String Y, SpotifySupport3 n, SpotifyCurve.TypeF Z, String u, String A, long d) {
      public String key() {
         return this.Y;
      }

      public SpotifySupport3 lyrics() {
         return this.n;
      }

      public SpotifyCurve.TypeF state() {
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

      public SpotifySupport3 lyricsDocumentState3() {
         return this.n;
      }

      public SpotifyCurve.TypeF mediaPlaybackModel_LyricsAvailabilityState4() {
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
