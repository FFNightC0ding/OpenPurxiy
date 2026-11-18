// Sourced By DevFFNightmare
package dev.purxiy.modules.shockwave;

import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;

public final class ShockwaveRenderer3 {
   public static final float w = 214.0F;
   private static final float s = 51.0F;
   private static final float I = 8.0F;
   private static final int d = 12;
   private static final float Q = 0.34F;
   private static final float v = 3.4F;
   private static final float C = 5.2F;
   private static final float T = 0.4F;
   private final ShockwaveModule N;
   private final Object V = new Object();
   private final float[] i = new float[625];
   private final float[] l = new float[625];
   private float a = 1.4F;
   private float p;
   private float S;
   private float r;
   private boolean o;



   private static final Integer[] f = new Integer[24];

   public ShockwaveRenderer3(ShockwaveModule var1) {
      this.N = var1;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 11754000680271L;
      long var8 = var6 ^ 55345584842490L;
      this.p = var2;
      this.S = var3;
      this.r = var4;
      this.o = var5;
      Canvas var10 = var1.canvas;
      var10.text(Fonts.MEDIUM, "Land, and the ground answers", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var10.text(Fonts.REGULAR, "A wave runs through the blocks around you", var2, var3 + 30.0F, 11.5F, Icons.TEXT_2);
      float var11 = var3 + 51.0F;
      float var12 = 155.0F;
      boolean var13 = var5 && var1.hover(this.V, var2, var11, var4, var12, DrawContext.TypeE.HAND);
      this.a = this.a + var1.dt;
      if (this.a > 3.4F) {
         this.a -= 3.4F;
      }

      var10.roundRectGradient(var2, var11, var4, var12, 12.0F, 90.0F, Theme.tint(-15396828), Theme.tint(-16317171));
      var10.pushClip(var2, var11, var4, var12, 12.0F);
      this.L(var10, var2 + var4 * 0.5F, var11 + var12 * 0.47F, var4, var8, var12);
      var10.popClip();
      var10.roundRectOutline(var2, var11, var4, var12, 12.0F, 1.0F, var13 ? Theme.tint(1086692853) : Theme.tint(633708021));
      var10.text(Fonts.MEDIUM, "CLICK TO LAND", var2 + 14.0F, var11 + 12.0F, 8.5F, var13 ? Icons.TEXT_2 : Icons.TEXT_3);
   }

   private void L(Canvas var1, float var2, float var3, float var4, long var5, float var7) {
      long var8 = 858328259L;
      short var10 = 4639;
      long var11 = 38121619785543L;
      long var13 = 72949448588730L;
      short var15 = 16984;
      char var16 = '\uddf8';
      int var17 = (int)(var13 << 48 >>> 48);
      ShockwaveModule.ensureSourceClassInitialized();
      ShockwaveLoader var18 = ShockwaveLoader.resolve(this.N.I.get(), this.N.U.argb());
      float var19 = this.N.f.floatValue();
      float var20 = this.N.N.floatValue();
      float var21 = this.N.j.floatValue();
      ShockwaveSupport3.TypeS var22 = new ShockwaveSupport3.TypeS(0.0, 0.0, 0.0, 0.34F, 5.2F * (0.6F + 0.4F * var20), 0.4F * (0.5F + 0.5F * var19), 0.15F, 0);
      byte var23 = 25;

      for (int var24 = -12; var24 <= 12; var24++) {
         for (int var25 = -12; var25 <= 12; var25++) {
            long var26 = 62562991711156L;
            if (62562991711156L == 0L) {
               boolean var10000 = false;
            } else {
               int var98 = var26 < 0L ? -1 : 1;
            }

            int var29 = (var24 + 12) * var23 + var25 + 12;
            this.i[var29] = -ShockwaveSupport3.sink(var22, var25, var24, 0.0, this.a);
            this.l[var29] = ShockwaveSupport3.shine(var22, var25, var24, 0.0, this.a);
         }

         long var89 = 62562991711157L;
         if (62562991711157L == 0L) {
            boolean var99 = false;
         } else {
            int var100 = var89 < 0L ? -1 : 1;
         }
      }

      float var88 = Math.clamp(var4 / 22.0F, 20.0F, 34.0F);
      int var90 = 20576;
      float var92 = var88 * 0.42F;
      var13 = 6297483077371177631L;
      float var27 = var88 * 1.35F;
      int var28 = R(var15, (char)var16, var18.o, (char)var17);
      int var93 = R(var15, (char)var16, var18.B, (char)var17);
      int var30 = BlendMode.lerp(var28, -1, 0.5F);

      for (int var31 = -24; var31 <= 24; var31++) {
         long var32 = 62562991711157L;
         if (62562991711157L == 0L) {
            boolean var101 = false;
         } else {
            int var102 = var32 < 0L ? -1 : 1;
         }

         for (int var35 = -12; var35 <= 12; var35++) {
            long var36 = 62562991711156L;
            if (62562991711156L == 0L) {
               boolean var103 = false;
            } else {
               int var104 = var36 < 0L ? -1 : 1;
            }

            int var39 = var31 - var35;
            long var40 = 62562991711157L;
            if (62562991711157L == 0L) {
               boolean var105 = false;
            } else {
               int var106 = var40 < 0L ? -1 : 1;
            }

            long var43 = 62562991711157L;
            if (62562991711157L == 0L) {
               boolean var107 = false;
            } else {
               int var108 = var43 < 0L ? -1 : 1;
            }

            if (var39 >= -12) {
               long var46 = 62562991711156L;
               if (62562991711156L == 0L) {
                  boolean var109 = false;
               } else {
                  int var110 = var46 < 0L ? -1 : 1;
               }

               long var49 = 62562991711157L;
               if (62562991711157L == 0L) {
                  boolean var111 = false;
               } else {
                  int var112 = var49 < 0L ? -1 : 1;
               }

               if (var39 > 12) {
                  long var51 = 62562991711156L;
                  if (62562991711156L == 0L) {
                     boolean var113 = false;
                  } else {
                     int var114 = var51 < 0L ? -1 : 1;
                  }
               } else {
                  int var96 = (var39 + 12) * var23 + var35 + 12;
                  long var52 = 62562991711156L;
                  if (62562991711156L == 0L) {
                     boolean var115 = false;
                  } else {
                     int var116 = var52 < 0L ? -1 : 1;
                  }

                  float var55 = var2 + (var35 - var39) * var88;
                  float var56 = var3 + (var35 + var39) * var92 - this.i[var96] * var27;
                  float var57 = var55 + var88;
                  long var58 = 62562991711157L;
                  if (62562991711157L == 0L) {
                     boolean var117 = false;
                  } else {
                     int var118 = var58 < 0L ? -1 : 1;
                  }

                  if (!(var57 < var2 - var4 * 0.5F)) {
                     long var61 = 62562991711157L;
                     if (62562991711157L == 0L) {
                        boolean var119 = false;
                     } else {
                        int var120 = var61 < 0L ? -1 : 1;
                     }

                     float var64 = var55 - var88;
                     float var65 = var4 * 0.5F;
                     long var66 = 62562991711157L;
                     if (62562991711157L == 0L) {
                        boolean var121 = false;
                     } else {
                        int var122 = var66 < 0L ? -1 : 1;
                     }

                     if (!(var64 > var2 + var65) && !(var56 + var92 < var3 - 77.5F)) {
                        long var68 = 62562991711157L;
                        if (62562991711157L == 0L) {
                           boolean var123 = false;
                        } else {
                           int var124 = var68 < 0L ? -1 : 1;
                        }

                        float var71;
                        var90 = (var71 = var56 - var92 - var27 - (var3 + 93.0F)) == 0.0F ? 0 : (var71 < 0.0F ? -1 : 1);
                        long var72 = 62562991711157L;
                        if (62562991711157L == 0L) {
                           boolean var125 = false;
                        } else {
                           int var126 = var72 < 0L ? -1 : 1;
                        }

                        if (var90 <= 0) {
                           float var75 = (var55 - var2) / (var4 * 0.5F);
                           float var76 = (var56 - var3) / 96.1F;
                           float var77 = Math.clamp(1.25F - (float)Math.sqrt(var75 * var75 + var76 * var76), 0.0F, 1.0F);
                           float var78 = this.l[var96];
                           float var79 = (var35 * 73 + var39 * 151 & 7) / 7.0F;
                           int var80 = BlendMode.lerp(Theme.tint(-14279107), Theme.tint(-13753271), var79);
                           int var81 = BlendMode.lerp(var80, Theme.tint(-12372629), Math.min(var78, 1.0F) * 0.85F);
                           var81 = BlendMode.lerp(Theme.tint(-16054252), var81, var77);
                           int var82 = BlendMode.lerp(var81, Theme.tint(-11451527), 0.18F * var77);
                           var1.triangle(var55, var56 - var92, var55 + var88, var56, var55, var56 + var92, var82, var81, var81);
                           var1.triangle(var55, var56 - var92, var55, var56 + var92, var55 - var88, var56, var82, var81, var81);
                           int var83 = BlendMode.withAlpha(-1, 0.07F * var77);
                           var1.line(var55 - var88, var56, var55, var56 - var92, 0.8F, var83);
                           var1.line(var55, var56 - var92, var55 + var88, var56, 0.8F, var83);
                           int var84 = (var39 + 12) * var23 + Math.min(var35 + 1 + 12, var23 - 1);
                           int var85 = Math.min(var39 + 1 + 12, var23 - 1) * var23 + var35 + 12;
                           float var86 = var21 * var77 * (0.55F + 0.45F * var78);
                           this.X(
                              var1,
                              var55 + var88,
                              var56,
                              var55,
                              var56 + var92,
                              this.i[var96] * var27,
                              this.i[var84] * var27,
                              var86,
                              var8,
                              (char)var10,
                              var81,
                              var30,
                              var28,
                              var93
                           );
                           this.X(
                              var1,
                              var55 - var88,
                              var56,
                              var55,
                              var56 + var92,
                              this.i[var96] * var27,
                              this.i[var85] * var27,
                              var86,
                              var8,
                              (char)var10,
                              var81,
                              var30,
                              var28,
                              var93
                           );
                        }
                     }
                  }
               }
            }
         }

         long var95 = 62562991711157L;
         if (62562991711157L == 0L) {
            boolean var127 = false;
         } else {
            int var128 = var95 < 0L ? -1 : 1;
         }
      }

      long var94 = 62562991711157L;
      if (62562991711157L == 0L) {
         boolean var129 = false;
      } else {
         int var130 = var94 < 0L ? -1 : 1;
      }

      this.x(var1, var2, var3, var88, var11, var92, var27, var30, var28);
   }

   private void X(
      Canvas var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      long var9,
      char var11,
      int var12,
      int var13,
      int var14,
      int var15
   ) {
      ShockwaveModule.ensureSourceClassInitialized();
      float var16 = var6 - var7;
      short var17 = 4639;
      if (!(var16 < 0.4F)) {
         float var18 = Math.max(Math.min(var6, var16), 0.0F);
         long var19 = 858328259L;
         if (858328259L == 0L) {
            boolean var10000 = false;
         } else {
            int var28 = var19 < 0L ? -1 : 1;
         }

         if (var18 > 0.4F) {
            int var21 = BlendMode.lerp(var12, -16777216, 0.35F);
            var1.triangle(var2, var3, var4, var5, var4, var5 + var18, var21, var21, var21);
            var1.triangle(var2, var3, var4, var5 + var18, var2, var3 + var18, var21, var21, var21);
         }

         float var26 = var16 - var18;
         float var22 = Math.min(var16 / 7.0F, 1.0F) * var8;
         if (var26 > 0.4F) {
            int var24 = BlendMode.lerp(Theme.tint(-15594208), var13, Math.min(var22 * 1.3F, 1.0F));
            int var25 = BlendMode.lerp(Theme.tint(-15922922), var15, var22 * 0.45F);
            var1.triangle(var2, var3 + var18, var4, var5 + var18, var4, var5 + var16, var25, var25, var24);
            var1.triangle(var2, var3 + var18, var4, var5 + var16, var2, var3 + var16, var25, var24, var24);
         }

         if (var22 > 0.02F) {
            int var23 = BlendMode.withAlpha(var14, 0.16F * var22);
            var1.line(var2, var3 + var16, var4, var5 + var16, 7.0F, var23, var23);
            int var27 = BlendMode.withAlpha(var13, Math.min(var22 * 1.4F, 1.0F));
            var1.line(var2, var3 + var16, var4, var5 + var16, 1.6F, var27, var27);
         }
      }
   }

   private void x(Canvas var1, float var2, float var3, float var4, long var5, float var7, float var8, int var9, int var10) {
      ShockwaveModule.ensureSourceClassInitialized();
      float var11 = Math.min(this.a / 0.34F, 1.0F);
      float var12 = (1.0F - var11 * var11) * var8 * 3.2F;
      long var13 = 38121619785542L;
      if (38121619785542L == 0L) {
         boolean var10000 = false;
      } else {
         int var27 = var13 < 0L ? -1 : 1;
      }

      float var16 = var4 * 0.3F;
      float var17 = var3 - var16 * 0.7F;
      float var18 = this.a - 0.34F;
      float var19 = var16 * (1.6F - 0.5F * var11);
      long var20 = 38121619785543L;
      if (38121619785543L == 0L) {
         boolean var28 = false;
      } else {
         int var29 = var20 < 0L ? -1 : 1;
      }

      var1.softCircle(var2, var3, var19, var16 * 0.9F, BlendMode.withAlpha(-16777216, 0.2F + 0.3F * var11));
      if (var18 >= 0.0F) {
         long var23 = 38121619785542L;
         if (38121619785542L == 0L) {
            boolean var30 = false;
         } else {
            int var31 = var23 < 0L ? -1 : 1;
         }

         if (var18 < 0.5F) {
            float var26 = var18 / 0.5F;
            var1.softCircle(var2, var3, var4 * (0.7F + 2.8F * var26), var4 * 0.9F, BlendMode.withAlpha(var10, 0.4F * (1.0F - var26) * (1.0F - var26)));
         }
      }

      var1.softCircle(var2, var17 - var12, var16 * 2.4F, var16 * 1.8F, BlendMode.withAlpha(var10, 0.3F));
      var1.circle(var2, var17 - var12, var16, var9);
   }

   private static int R(int var0, char var1, int var2, char var3) {
      float var4 = (var2 >> 16 & 0xFF) / 255.0F;
      float var5 = (var2 >> 8 & 0xFF) / 255.0F;
      float var6 = (var2 & 0xFF) / 255.0F;
      float var7 = 0.299F * var4 + 0.587F * var5 + 0.114F * var6;
      return BlendMode.argb(1.0F, E(var7 + (var4 - var7) * 1.45F), E(var7 + (var5 - var7) * 1.45F), E(var7 + (var6 - var7) * 1.45F));
   }

   private static float E(float var0) {
      return Math.clamp(var0, 0.0F, 1.0F);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      if (this.o && var3 == 0 && !(var1 < this.p) && !(var1 >= this.p + this.r) && !(var2 < this.S + 51.0F) && !(var2 >= this.S + 214.0F - 8.0F)) {
         this.a = 0.0F;
         UiAudioPolicy.play("CLICK");
         return true;
      } else {
         return false;
      }
   }

}
