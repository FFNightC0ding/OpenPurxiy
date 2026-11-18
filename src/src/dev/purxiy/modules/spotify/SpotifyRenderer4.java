// Sourced By DevFFNightmare
package dev.purxiy.modules.spotify;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.config.NumberSetting;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.Iterator;
import java.util.List;
import net.minecraft.class_156;

public final class SpotifyRenderer4 {
   public static final float D = 348.0F;
   private static final String[] K = new String[]{"Top", "Karaoke", "Together", "Lyrics", "Player"};
   private static final float X = 60.0F;
   private static final float A = 54.0F;
   private static final float m = 156.0F;
   private static final float I = 184.0F;
   private final SpotifyModule G;
   private final SpotifyRenderer3 B = new SpotifyRenderer3();
   private final SpotifyRenderer3.TypeD a = new SpotifyRenderer3.TypeD();
   private final SpotifyRenderer2 l = new SpotifyRenderer2();
   private final SpotifyRenderer2.TypeH z = new SpotifyRenderer2.TypeH();
   private final SpotifyRenderer2 Z = new SpotifyRenderer2();
   private final SpotifyRenderer2.TypeH R = new SpotifyRenderer2.TypeH();
   private final SpotifyUpdater4<SpotifyRenderer4.TypeI> F = new SpotifyUpdater4<>();
   private final SpotifyUpdater2<SpotifyRenderer4.TypeI> e = new SpotifyUpdater2<>();
   private final SpotifyRenderer4.TypeR j = new SpotifyRenderer4.TypeR();
   private boolean v;
   private final SpotifyRenderer.TypeO x = new SpotifyRenderer.TypeO();
   private final SpotifyRenderer4.TypeY k = new SpotifyRenderer4.TypeY();
   private float b;
   private boolean J;
   private final Animator[] V = new Animator[K.length];
   private final Animator S = Animator.smooth(0.0F);
   private final Object[] C = new Object[K.length + 2];
   private float s;
   private float q;
   private float E;
   private float Y;
   private float r;
   private boolean n;
   private boolean W;
   
   private SpotifyCurve P;
   private final float[] Q = new float[4];
   private float p = 1.0F;
   private static final float f = 0.42F;
   private static final float y = 1.6F;
   private static final float T = 0.3F;
   private static final float t = 12.0F;
   private static final float o = 0.3F;
   private static final float h = 0.12F;

   private static final String[] d = new String[36];
   private static final long[] g = new long[]{
      1031959611666495281L,
      70894753091128439L,
      8257416717543926431L,
      328552724849406785L,
      -2810493132048261389L,
      -349692224657485780L,
      -6300680660127774955L,
      -7443198089467503943L,
      804188788908080008L,
      -8395873138463014900L,
      6731144427152514279L,
      -2479520188274622974L,
      2906341970353744648L,
      9159777889629638591L,
      -7320542019931664184L,
      -8686141903425414157L,
      -9179516032653428743L,
      6221696420970280649L,
      401066469916931885L,
      1888558703505164086L,
      8088850462763973199L,
      1785469447655366935L,
      -8243143135866232472L,
      8546727697903697420L,
      -1491482415230105715L,
      7411752986651978329L,
      2933349708977331235L,
      -3832901115424174064L,
      1286523897813625081L,
      -5779843500847596276L,
      -2239990105479208745L,
      -7780356826532240981L,
      -3640151675774415189L,
      -1317575982792136587L,
      8338152250505421913L,
      -5929856943876688674L,
      -4489948755680949975L,
      -5064365997932695195L,
      4729618299950843752L,
      4734258357071097270L,
      -3386177848327892319L
   };


   public SpotifyRenderer4(SpotifyModule var1) {
      this.G = var1;

      for (int var2 = 0; var2 < this.V.length; var2++) {
         this.V[var2] = Animator.smooth(0.0F);
      }

      for (int var3 = 0; var3 < this.C.length; var3++) {
         this.C[var3] = new Object();
      }
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 62066059192587L;
      long var8 = var6 ^ 16620558096229L;
      long var10 = var6 ^ 104294490233986L;
      long var12 = var6 ^ 75175477735283L;
      this.s = var2;
      this.q = var3;
      this.E = var4;
      this.n = var5;
      Canvas var14 = var1.canvas;
      SpotifyRegistry var15 = SpotifyRegistry.get();
      var15.keepAlive(this.G.w.enabled());
      var15.frame();
      SpotifyCurve var16 = var15.pick(true);
      this.P = var16;
      var14.text(Fonts.MEDIUM, "Choose a layout", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var14.text(Fonts.REGULAR, "Open the chat to move both parts, or to press play right there.", var2, var3 + 30.0F, 11.5F, Icons.TEXT_2);
      int var17 = this.f(var12);
      float var18 = (var4 - 32.0F) / 5.0F;

      for (int var19 = 0; var19 < K.length; var19++) {
         float var20 = var2 + var19 * (var18 + 8.0F);
         float var21 = var3 + 54.0F;
         boolean var22 = var5 && var1.hover(this.C[var19], var20, var21, var18, 80.0F, DrawContext.TypeE.HAND);
         float var23 = this.V[var19].target(var22 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         boolean var24 = var19 == var17;
         if (var24) {
            var14.glow(var20, var21, var18, 60.0F, 9.0F, 16.0F, BlendMode.withAlpha(Icons.VIOLET, 0.3F));
         }

         var14.roundRectGradient(
            var20, var21, var18, 60.0F, 9.0F, 90.0F, BlendMode.lerp(Theme.tint(-15068109), Theme.tint(-14476478), var23), Theme.tint(-16053995)
         );
         var14.roundRectOutline(
            var20, var21, var18, 60.0F, 9.0F, 1.0F, var24 ? Theme.tint(-4222985) : BlendMode.lerp(Theme.tint(482713077), Theme.tint(1086692853), var23)
         );
         float var25 = var24 ? 1.0F : 0.55F + 0.3F * var23;
         float var26 = 60.0F;
         this.P(var14, var8, var19, var20, var21, var18, var26, var25);
         var14.text(
            Fonts.MEDIUM,
            K[var19],
            var20 + var18 / 2.0F,
            var21 + 60.0F + 11.0F,
            10.5F,
            var24 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var23),
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
      }

      this.P(var1, var14, var16, var2, var3 + 156.0F, var4, var10, 184.0F);
   }

   private void P(Canvas var1, long var2, int var4, float var5, float var6, float var7, float var8, float var9) {
      long var10;
      long var12;
      int var14;
      int var15;
      boolean var16;
      boolean var17;
      label176: {
         var10 = 52401414258245L;
         var12 = 84673929511583L;
         SpotifyCurve.ensureSourceClassInitialized();
         var14 = BlendMode.withAlpha(-1, 0.9F * var9);
         var15 = BlendMode.withAlpha(-1, 0.38F * var9);
         var16 = this.G.E.is("In the world");
         if (var4 != 0) {
            long var18 = 60950537256558L;
            if (60950537256558L == 0L) {
               boolean var10000 = false;
            } else {
               int var52 = var18 < 0L ? -1 : 1;
            }

            if (var4 != 3) {
               var17 = false;
               break label176;
            }
         }

         var17 = true;
      }

      long var19 = 60950537256558L;
      if (60950537256558L == 0L) {
         boolean var53 = false;
      } else {
         int var54 = var19 < 0L ? -1 : 1;
      }

      label179: {
         boolean var22 = var4 == 1;
         if (var16) {
            long var23 = 60950537256558L;
            if (60950537256558L == 0L) {
               boolean var55 = false;
            } else {
               int var56 = var23 < 0L ? -1 : 1;
            }

            long var25 = 60950537256558L;
            if (60950537256558L == 0L) {
               boolean var57 = false;
            } else {
               int var58 = var25 < 0L ? -1 : 1;
            }

            if (var4 != 4) {
               var17 = this.G.inSky();
               long var27 = 60950537256557L;
               if (60950537256557L == 0L) {
                  boolean var59 = false;
               } else {
                  int var60 = var27 < 0L ? -1 : 1;
               }

               if (var17) {
                  float var29 = var6 + 43.2F;
                  var1.rect(var5 + 3.0F, var29, var7 - 6.0F, 0.8F, BlendMode.withAlpha(Theme.tint(-3826187), 0.22F * var9));
                  var1.glowCircle(var5 + var7 * 0.38F, var6 + 18.0F, var7 * 0.14F, 10.0F, BlendMode.withAlpha(Icons.VIOLET, 0.2F * var9));
                  var1.roundRect(var5 + var7 * 0.2F, var6 + 16.2F, var7 * 0.36F, 3.4F, 1.7F, var14);
                  var1.roundRect(var5 + var7 * 0.4F, var6 + 30.0F, var7 * 0.26F, 3.0F, 1.5F, var15);
                  break label179;
               }
            }
         }

         if (var16) {
            long var42 = 60950537256557L;
            if (60950537256557L == 0L) {
               boolean var61 = false;
            } else {
               int var62 = var42 < 0L ? -1 : 1;
            }

            if (var4 != 4) {
               float var46 = var6 + 21.6F;
               float var26 = var5 + 3.0F;
               float var49 = var7 - 6.0F;
               long var28 = 60950537256558L;
               if (60950537256558L == 0L) {
                  boolean var65 = false;
               } else {
                  int var66 = var28 < 0L ? -1 : 1;
               }

               var1.rect(var26, var46, var49, 0.8F, BlendMode.withAlpha(Theme.tint(-3826187), 0.22F * var9));
               var1.glowCircle(var5 + var7 * 0.32F, var6 + 36.0F, var7 * 0.12F, 10.0F, BlendMode.withAlpha(Icons.VIOLET, 0.2F * var9));
               this.W(var1, var5 + var7 * 0.33F, var6 + 39.600002F, var7 * 0.24F, 4.0F, var9, var10, var14);
               this.W(var1, var5 + var7 * 0.66F, var6 + 39.600002F, var7 * 0.17F, 4.0F, var9, var10, var15);
               var17 = this.G.groundAndSky();
               long var31 = 60950537256558L;
               if (60950537256558L == 0L) {
                  boolean var67 = false;
               } else {
                  int var68 = var31 < 0L ? -1 : 1;
               }

               if (var17) {
                  var1.roundRect(var5 + var7 * 0.46F, var6 + 9.0F, var7 * 0.3F, 2.6F, 1.3F, var15);
               }
               break label179;
            }
         }

         if (!var17) {
            long var43 = 60950537256558L;
            if (60950537256558L == 0L) {
               boolean var63 = false;
            } else {
               int var64 = var43 < 0L ? -1 : 1;
            }

            if (!var22) {
               break label179;
            }
         }

         float var44 = var17 ? var6 + 9.0F : var6 + 60.0F - 19.0F;
         var1.roundRect(var5 + var7 * 0.27F, var44, var7 * 0.46F, 3.2F, 1.6F, var14);
         var1.roundRect(var5 + var7 * 0.34F, var44 + 6.0F, var7 * 0.32F, 2.4F, 1.2F, var15);
      }

      long var41 = 60950537256557L;
      if (60950537256557L == 0L) {
         boolean var69 = false;
      } else {
         int var70 = var41 < 0L ? -1 : 1;
      }

      if (var4 != 0) {
         long var21 = 60950537256557L;
         if (60950537256557L == 0L) {
            boolean var71 = false;
         } else {
            int var72 = var21 < 0L ? -1 : 1;
         }

         if (var4 != 4) {
            long var45 = 60950537256557L;
            if (60950537256557L == 0L) {
               boolean var73 = false;
            } else {
               int var74 = var45 < 0L ? -1 : 1;
            }

            if (var4 == 1) {
               this.w(var12, var1, var5 + var7 - 34.0F, var6 + 21.6F, 29.0F, 12.0F, var9, false);
            } else if (var4 == 2) {
               float var47 = var5 + var7 - 38.0F;
               long var48 = 60950537256557L;
               if (60950537256557L == 0L) {
                  boolean var75 = false;
               } else {
                  int var76 = var48 < 0L ? -1 : 1;
               }

               float var50 = var6 + 18.0F;
               float var30 = var16 ? 12.0F : 25.0F;
               boolean var51 = !var16;
               float var34 = 33.0F;
               this.w(var12, var1, var47, var50, var34, var30, var9, var51);
            }

            return;
         }
      }

      this.w(var12, var1, var5 + var7 - 34.0F, var6 + 60.0F - 17.0F, 29.0F, 12.0F, var9, false);
   }

   private void W(Canvas var1, float var2, float var3, float var4, float var5, float var6, long var7, int var9) {
      var1.roundRect(var2 - var4 / 2.0F - 1.0F, var3 - 0.4F, var4 + 2.0F, 1.6F, 0.8F, BlendMode.withAlpha(Theme.tint(-16317425), 0.5F * var6));
      var1.roundRect(var2 - var4 / 2.0F, var3 - 4.0F - 1.2F, var4, 4.0F, 2.0F, var9);
   }

   private void w(long var1, Canvas var3, float var4, float var5, float var6, float var7, float var8, boolean var9) {
      SpotifyCurve.ensureSourceClassInitialized();
      var3.roundRect(var4, var5, var6, var7, 3.0F, BlendMode.withAlpha(Theme.tint(-13950384), 0.95F * var8));
      var3.roundRectOutline(var4, var5, var6, var7, 3.0F, 0.8F, BlendMode.withAlpha(Theme.tint(-4222985), 0.45F * var8));
      var3.roundRectGradient(
         var4 + 2.0F, var5 + 2.0F, 8.0F, 8.0F, 1.6F, 135.0F, BlendMode.withAlpha(Icons.INDIGO, var8), BlendMode.withAlpha(Icons.MAGENTA, var8)
      );
      float var10 = var4 + 12.0F;
      long var11 = 84673929511582L;
      if (84673929511582L == 0L) {
         boolean var10000 = false;
      } else {
         int var19 = var11 < 0L ? -1 : 1;
      }

      var3.roundRect(var10, var5 + 3.0F, var6 - 15.0F, 1.6F, 0.8F, BlendMode.withAlpha(-1, 0.8F * var8));
      var10 = var4 + 12.0F;
      float var14 = var5 + 6.5F;
      float var15 = (var6 - 15.0F) * 0.6F;
      long var16 = 84673929511582L;
      if (84673929511582L == 0L) {
         boolean var20 = false;
      } else {
         int var21 = var16 < 0L ? -1 : 1;
      }

      var3.roundRect(var10, var14, var15, 1.4F, 0.7F, BlendMode.withAlpha(-1, 0.35F * var8));
      if (var9) {
         var3.rect(var4 + 2.0F, var5 + 12.5F, var6 - 4.0F, 0.6F, BlendMode.withAlpha(-1, 0.12F * var8));
         var3.roundRect(var4 + 3.0F, var5 + 15.0F, var6 - 9.0F, 2.2F, 1.1F, BlendMode.withAlpha(-1, 0.85F * var8));
         var3.roundRect(var4 + 3.0F, var5 + 19.5F, (var6 - 9.0F) * 0.7F, 1.8F, 0.9F, BlendMode.withAlpha(-1, 0.35F * var8));
      }
   }

   private void P(DrawContext var1, Canvas var2, SpotifyCurve var3, float var4, float var5, float var6, long var7, float var9) {
      long var10 = 139538719765510L;
      short var12 = 32488;
      char var13 = '\ue3d2';
      int var14 = (int)(var10 << 48 >>> 48);
      long var15 = 45117548270827L;
      long var17 = 76384909072804L;
      long var19 = 122270409335948L;
      SpotifyCurve.ensureSourceClassInitialized();
      boolean var21 = this.G.inWorld();
      long var22 = 112872099411336L;
      if (112872099411336L == 0L) {
         boolean var10000 = false;
      } else {
         int var60 = var22 < 0L ? -1 : 1;
      }

      if (var21) {
         this.x(var2, var4, var5, var6, 184.0F, var1.dt, var19, var1.time);
         long var25 = 112872099411337L;
         if (112872099411337L == 0L) {
            boolean var61 = false;
         } else {
            int var62 = var25 < 0L ? -1 : 1;
         }
      } else {
         var2.roundRectGradient(var4, var5, var6, 184.0F, 12.0F, 90.0F, Theme.tint(-15265236), Theme.tint(-16119788));
         var2.glowCircle(var4 + var6 * 0.3F, var5 + 26.0F, 70.0F, 90.0F, BlendMode.withAlpha(Icons.VIOLET, 0.07F));
      }

      var2.roundRectOutline(var4, var5, var6, 184.0F, 12.0F, 1.0F, 385875967);
      boolean var49 = !var3.sample();
      long var26 = 112872099411336L;
      if (112872099411336L == 0L) {
         boolean var63 = false;
      } else {
         int var64 = var26 < 0L ? -1 : 1;
      }

      String var29 = var49 ? "NOW PLAYING" : "PREVIEW";
      long var30 = 112872099411336L;
      if (112872099411336L == 0L) {
         boolean var65 = false;
      } else {
         int var66 = var30 < 0L ? -1 : 1;
      }

      var2.text(Fonts.SEMIBOLD, var29, var4 + 14.0F, var5 + 16.0F, 8.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE, 0.12F);
      if (var49) {
         var2.circle(
            var4 + 14.0F + var2.textWidth(Fonts.SEMIBOLD, var29, 8.5F, 0.12F) + 7.0F, var5 + 16.0F, 2.2F, var3.playing() ? -7670328 : Theme.tint(-8620647)
         );
      } else {
         this.h(var17, var1, var2, var4 + var6 - 14.0F, var5 + 16.0F);
      }

      String var33 = this.G.J.get();
      this.a.y = this.G.d.enabled();
      this.a.V = this.G.w.enabled();
      this.a.g = this.G.G.is("Album");
      this.a.T = false;
      int var34 = this.a.g ? var3.accent() : Icons.PURPLE;
      float var35 = this.G.m.is("Hidden") ? 0.0F : 1.0F;
      float var36 = var5 + 30.0F;
      float var37 = var5 + 184.0F - 12.0F;
      this.Y = Float.NaN;
      if (var21) {
         boolean var38 = !var33.equals("Lyrics only");
         float var39 = SpotifyRenderer3.height(var35);
         float var40 = var38 ? var6 - 216.0F - 24.0F : var6;
         long var41 = 112872099411336L;
         if (112872099411336L == 0L) {
            boolean var67 = false;
         } else {
            int var68 = var41 < 0L ? -1 : 1;
         }

         this.x(var2, var3, var12, var4, var5, (short)var13, var6, 184.0F, var40, var34, var1.dt, var1.time, (short)var14);
         if (var38) {
            this.J(var1, var2, var3, var4 + var6 - 216.0F - 16.0F, var36 + Math.max(0.0F, (var37 - var36 - var39) / 2.0F), var15, 216.0F, var39, var35);
         }
      } else if (var33.equals("Combined")) {
         SpotifyRenderer2.TypeH var50 = this.R;
         var50.r = 198.0F;
         var50.u = Math.min(3, this.G.p.intValue());
         var50.O = 13.5F;
         var50.h = 10.5F;
         var50.T = 4.0F;
         var50.o = FontStyle.LEFT;
         var50.I = this.G.g.enabled();
         var50.x = this.G.e.enabled();
         var50.l = var34;
         float var52 = SpotifyRenderer3.height(var35) + 9.0F + SpotifyRenderer2.reservedHeight(var50) + 2.0F;
         float var54 = var4 + (var6 - 216.0F) / 2.0F;
         float var56 = var36 + Math.max(0.0F, (var37 - var36 - var52) / 2.0F);
         this.J(var1, var2, var3, var54, var56, var15, 216.0F, var52, var35);
         float var42 = var56 + SpotifyRenderer3.height(var35) + 4.5F - 2.0F;
         var2.rect(var54 + 9.0F, var42, 198.0F, 1.0F, 352321535);
         this.Z.render(var2, var54 + 9.0F, var42 + 4.5F + 1.0F, var50, var3, var1.dt, var1.time);
      } else {
         boolean var51 = !var33.equals("Player only");
         boolean var53 = !var33.equals("Lyrics only");
         float var55 = SpotifyRenderer3.height(var35);
         long var57 = 112872099411336L;
         if (112872099411336L == 0L) {
            boolean var69 = false;
         } else {
            int var70 = var57 < 0L ? -1 : 1;
         }

         if (var51) {
            SpotifyRenderer2.TypeH var44 = this.z;
            var44.r = var53 ? Math.min(330.0F, var6 - 216.0F - 64.0F) : Math.min(420.0F, var6 - 60.0F);
            var44.u = Math.min(3, this.G.p.intValue());
            var44.O = 15.0F;
            var44.h = 11.5F;
            var44.T = 4.5F;
            var44.o = FontStyle.CENTER;
            var44.I = this.G.g.enabled();
            var44.x = this.G.e.enabled();
            var44.l = var34;
            var44.z = false;
            float var45 = var53 ? var4 + 20.0F : var4 + (var6 - var44.r) / 2.0F;
            float var46 = SpotifyRenderer2.reservedHeight(var44);
            float var47 = var36 + Math.max(0.0F, (var37 - var36 - var46) / 2.0F);
            float var48 = this.l.contentHeight(var44);
            var2.roundRect(var45 - 12.0F, var47 - 7.0F, var44.r + 24.0F, var48 + 14.0F, 12.0F, Theme.tint(-1727132900));
            var2.roundRectOutline(var45 - 12.0F, var47 - 7.0F, var44.r + 24.0F, var48 + 14.0F, 12.0F, 1.0F, 318767103);
            this.l.render(var2, var45, var47, var44, var3, var1.dt, var1.time);
         }

         if (var53) {
            float var58 = var51 ? var4 + var6 - 216.0F - 16.0F : var4 + (var6 - 216.0F) / 2.0F;
            float var59 = var36 + Math.max(0.0F, (var37 - var36 - var55) / 2.0F);
            this.J(var1, var2, var3, var58, var59, var15, 216.0F, var55, var35);
         }
      }
   }

   private void J(DrawContext var1, Canvas var2, SpotifyCurve var3, float var4, float var5, long var6, float var8, float var9, float var10) {
      SpotifyCurve.ensureSourceClassInitialized();
      var2.shadow(var4, var5, 216.0F, var9, 11.0F, 16.0F, 0.0F, 0.0F, 4.0F, 1426063360);
      var2.roundRect(var4, var5, 216.0F, var9, 11.0F, Theme.tint(-535819234));
      var2.roundRectCorners(var4, var5, 216.0F, var9, 11.0F, 318767103, 150994943, 16777215, 16777215);
      var2.roundRectOutlineGradient(var4, var5, 216.0F, var9, 11.0F, 1.0F, 90.0F, 654311423, 150994943);
      this.Y = var4;
      this.r = var5;
      this.p = var10;
      float var11 = Float.NaN;
      float var12 = Float.NaN;
      long var13 = 45117548270827L;
      if (45117548270827L == 0L) {
         boolean var10000 = false;
      } else {
         int var23 = var13 < 0L ? -1 : 1;
      }

      long var16 = 45117548270827L;
      if (45117548270827L == 0L) {
         boolean var24 = false;
      } else {
         int var25 = var16 < 0L ? -1 : 1;
      }

      if (this.n) {
         boolean var18 = var1.inside(var4, var5, 216.0F, var9);
         long var19 = 45117548270827L;
         if (45117548270827L == 0L) {
            boolean var26 = false;
         } else {
            int var27 = var19 < 0L ? -1 : 1;
         }

         if (var18) {
            var11 = var1.mouseX - var4;
            var12 = var1.mouseY - var5;
            long var21 = 45117548270826L;
            if (45117548270826L == 0L) {
               boolean var28 = false;
            } else {
               int var29 = var21 < 0L ? -1 : 1;
            }

            if (this.B.hitAt(var11, var12, var10, var3, this.a) != SpotifyRenderer3.TypeX.O) {
               var1.claim(this.C[K.length], DrawContext.TypeE.HAND);
            }
         }
      }

      var2.push();
      var2.translate(var4, var5);
      this.B.render(var2, var3, this.a, var10, var11, var12, var1.dt, var1.time);
      var2.pop();
   }

   private void x(Canvas var1, float var2, float var3, float var4, float var5, float var6, long var7, float var9) {
      long var10 = 128430842948986L;
      SpotifyCurve.ensureSourceClassInitialized();
      this.b = (float)(Math.sin(var9 * 0.42) * 20.0 + Math.sin(var9 * 0.17 + 1.3) * 8.0);
      float var12 = var3 + 77.28F;
      var1.pushClip(var2, var3, var4, 184.0F, 12.0F);
      var1.roundRectGradient(var2, var3, var4, var12 - var3 + 1.0F, 0.0F, 90.0F, Theme.tint(-15593942), Theme.tint(-12175236));

      for (int var13 = 0; var13 < 18; var13++) {
         float var14 = h(var13);
         long var15 = 122270409335947L;
         if (122270409335947L == 0L) {
            boolean var10000 = false;
         } else {
            int var35 = var15 < 0L ? -1 : 1;
         }

         float var18 = var2 + U(var14 * var4 - this.b * 0.12F, var4);
         float var19 = var3 + 8.0F + h(var13 + 40) * (var12 - var3) * 0.62F;
         float var20 = 0.18F + 0.32F * (0.5F + 0.5F * (float)Math.sin(var9 * (1.1 + h(var13 + 80)) + var13 * 2.1));
         var1.circle(var18, var19, 0.6F + h(var13 + 120) * 0.6F, BlendMode.withAlpha(Theme.tint(-923137), var20));
      }

      float var28 = var4 * 0.24F;
      long var29 = 122270409335948L;
      if (122270409335948L == 0L) {
         boolean var36 = false;
      } else {
         int var37 = var29 < 0L ? -1 : 1;
      }

      float var17 = var2 + var28 - this.b * 0.15F;
      var1.glowCircle(var17, var12, 40.0F, 120.0F, Theme.tint(952470783));
      var1.glowCircle(var17, var12 - 6.0F, 12.0F, 40.0F, Theme.tint(822073074));
      this.B(var1, var2, var10, var4, var12, 6.0F, this.b * 0.45F, Theme.tint(-14016176), 0.0F, 1.4F);
      this.B(var1, var2, var10, var4, var12, 9.0F, this.b * 0.8F, Theme.tint(-15068109), 3.0F, 0.9F);
      var1.roundRectGradient(var2, var12, var4, var3 + 184.0F - var12, 0.0F, 90.0F, Theme.tint(-15002060), Theme.tint(-15988200));
      float var30 = var2 + var4 / 2.0F;
      float var31 = U(this.b * 1.6F, 36.0F);

      for (float var32 = var2 - 400.0F - var31; var32 < var2 + var4 + 400.0F; var32 += 36.0F) {
         var1.line(var30, var12, var32, var3 + 184.0F + 2.0F, 0.7F, 218103807);
      }

      long var33 = 122270409335947L;
      if (122270409335947L == 0L) {
         boolean var38 = false;
      } else {
         int var39 = var33 < 0L ? -1 : 1;
      }

      for (int var23 = 1; var23 <= 6; var23++) {
         long var24 = 122270409335948L;
         if (122270409335948L == 0L) {
            boolean var40 = false;
         } else {
            int var41 = var24 < 0L ? -1 : 1;
         }

         float var27 = var23 / 6.0F;
         var1.rect(var2, var12 + (var3 + 184.0F - var12) * var27 * var27, var4, 0.7F, BlendMode.withAlpha(-1, 0.025F + 0.03F * var27));
      }

      long var34 = 122270409335948L;
      if (122270409335948L == 0L) {
         boolean var42 = false;
      } else {
         int var43 = var34 < 0L ? -1 : 1;
      }

      var1.popClip();
   }

   private void B(Canvas var1, float var2, long var3, float var5, float var6, float var7, float var8, int var9, float var10, float var11) {
      SpotifyCurve.ensureSourceClassInitialized();
      int var12 = (int)Math.floor(var8 / var7) - 1;
      int var13 = (int)(var5 / var7) + 3;

      for (int var14 = var12; var14 < var12 + var13; var14++) {
         double var15 = Math.sin(var14 * 0.37 + var10) + 0.55 * Math.sin(var14 * 0.91 + var10 * 2.3) + 0.3 * Math.sin(var14 * 2.1 + var10);
         int var17 = Math.max(1, (int)Math.round((1.6 + var15) * var11));
         float var18 = var2 + var14 * var7 - var8;
         var1.rect(var18, var6 - var17 * var7, var7 + 0.5F, var17 * var7 + 1.0F, var9);
      }
   }

   private void x(
      Canvas var1,
      SpotifyCurve var2,
      int var3,
      float var4,
      float var5,
      short var6,
      float var7,
      float var8,
      float var9,
      int var10,
      float var11,
      float var12,
      short var13
   ) {
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifyModule var14 = this.G;
      SpotifyRenderer4.TypeY var15 = this.k;
      var15.G = var4 + 16.0F;
      var15.o = var4 + var9 - 12.0F;
      var15.L = (var15.G + var15.o) / 2.0F;
      var15.W = var4 + var7 / 2.0F;
      var15.s = var5 + 77.28F;
      var15.r = var5 + 36.0F;
      var15.X = var5 + 184.0F;
      var15.U = 20.0F * Math.min(1.25F, var14.X.floatValue());
      var15.A = 22.0F * Math.min(1.25F, var14.X.floatValue());
      boolean var16 = var14.inSky();
      boolean var17 = var14.F.is("Whole line");
      String var18 = var14.c.get();

      float var20 = switch (var18) {
         case "Narrow" -> 9.5F;
         case "Wide" -> 16.0F;
         default -> 12.5F;
      };
      float var21 = Math.min(var20, var16 ? (var15.o - var15.G - 40.0F) / var15.A : ((var15.o - var15.G) / 2.0F - 12.0F - 6.0F) / var15.em(var15.preferred()));
      this.v = !var16 && !var17;
      this.F.update(var2, true, MotionStyle.MENU.dt(var11), Math.min(3, var14.p.intValue()), var17, var21, var15);
      this.e.update(this.F, var2.lyricsTime(), this.v, this.j);
      float var22 = var15.L;
      float var23 = var5 + 92.0F;
      var1.rect(var22 - 5.0F, var23 - 0.6F, 10.0F, 1.2F, -1862270977);
      var1.rect(var22 - 0.6F, var23 - 5.0F, 1.2F, 10.0F, -1862270977);
      SpotifyRenderer.TypeO var24 = this.x;
      var24.B = var17;
      var24.J = var14.e.enabled();
      var24.g = var10;
      var24.o = 1.0F;
      var24.k = 0.7F;
      var24.L = 0.5F;
      var24.r = var2.level();
      var24.p = var12;
      double var25 = var2.lyricsTime();
      short var27 = -7214;
      if (var16 != this.J) {
         this.J = var16;
         this.F.update(var2, false, 0.0F, 1, var17, var21, var15);
      }

      var1.pushClip(var4, var5, var7, 184.0F, 12.0F);
      float var28 = this.F.clock();
      Iterator var29 = this.e.h.iterator();

      while (var29.hasNext()) {
         var27 = -3066;
         SpotifyUpdater2.TypeH var30 = (SpotifyUpdater2.TypeH)var29.next();
         if (var30.Y != null) {
            this.L(var1, (SpotifyRenderer4.TypeI)var30.Y, var30.width(), SpotifyRenderer.presence(var30, var28), var24);
         }
      }

      var27 = -3066;
      var29 = this.F.X.iterator();

      while (true) {
         var27 = -3066;
         if (!var29.hasNext()) {
            var27 = 32488;
            var24.Y = false;
            var29 = this.e.h.iterator();

            while (var29.hasNext()) {
               var27 = -7214;
               SpotifyUpdater2.TypeH var52 = (SpotifyUpdater2.TypeH)var29.next();
               SpotifyRenderer4.TypeI var31 = (SpotifyRenderer4.TypeI)var52.Y;
               if (var31 == null) {
                  var27 = -3066;
               } else {
                  float var32 = var15.em(var31.E) / 64.0F;
                  var1.push();
                  var1.translate(this.O(var31), this.Z(var31));
                  var1.scale(var32);
                  SpotifyRenderer.stand(var1, var52, var28, var24);
                  var1.pop();
               }
            }

            var27 = -7214;
            var1.popClip();

            for (SpotifyUpdater4.TypeM var54 : this.F.X) {
               SpotifyRenderer4.TypeI var55 = (SpotifyRenderer4.TypeI)var54.Z;
               if (var55 != null) {
                  var27 = -7214;
                  if (var55.A) {
                     var27 = -7214;
                  } else {
                     var27 = -7214;
                     if (var55.b) {
                        float var33 = var15.s - (var15.s - var15.r) * var55.t;
                        float var34 = var15.W + var55.I - this.b * 0.3F;
                        float var35 = var15.A / 64.0F;
                        var24.Y = true;
                        var1.pushClip(var4, var5, var7, var15.s - var5 + 2.0F, 12.0F);
                        var1.push();
                        var1.translate(var34, var33);
                        var1.scale(var35);
                        SpotifyRenderer.draw(var1, var54, var25, this.F.clock(), var24);
                        var1.pop();
                        var1.popClip();
                     } else {
                        var24.Y = false;
                        var1.pushClip(var4, var5, var7, 184.0F, 12.0F);
                        float var56 = var15.em(var55.E) / 64.0F;
                        var1.push();
                        var1.translate(this.O(var55), this.Z(var55) - (0.12F + var54.t.C / 2.0F) * var15.em(var55.E));
                        var1.scale(var56);
                        SpotifyRenderer.draw(var1, var54, var25, var28, var24);
                        var1.pop();
                        var1.popClip();
                     }
                  }
               }
            }

            return;
         }

         var27 = -7214;
         SpotifyUpdater4.TypeM var51 = (SpotifyUpdater4.TypeM)var29.next();
         if (var51.Z != null) {
            var27 = 32488;
            if (!((SpotifyRenderer4.TypeI)var51.Z).A) {
               var27 = 32488;
               if (!((SpotifyRenderer4.TypeI)var51.Z).b) {
                  this.L(var1, (SpotifyRenderer4.TypeI)var51.Z, var51.t.c * 64.0F, SpotifyRenderer.presence(var51, var25, var28, var24), var24);
               }
            }
         }
      }
   }

   private float O(SpotifyRenderer4.TypeI var1) {
      return this.k.W + (var1.I - this.b * 1.6F) * var1.E;
   }

   private float Z(SpotifyRenderer4.TypeI var1) {
      SpotifyRenderer4.TypeY var2 = this.k;
      return var2.s + (var2.X - var2.s) * var1.E;
   }

   private void L(Canvas var1, SpotifyRenderer4.TypeI var2, float var3, float var4, SpotifyRenderer.TypeO var5) {
      float var6 = this.k.em(var2.E) / 64.0F;
      var1.push();
      var1.translate(this.O(var2), this.Z(var2));
      var1.scale(var6, var6 * 0.3F);
      SpotifyRenderer.shadow(var1, var3, var4, var5);
      var1.pop();
   }

   private static float h(int var0) {
      double var1 = Math.sin(var0 * 12.9898 + 78.233) * 43758.5453;
      return (float)(var1 - Math.floor(var1));
   }

   private static float U(float var0, float var1) {
      return var0 - (float)Math.floor(var0 / var1) * var1;
   }

   private void h(long var1, DrawContext var3, Canvas var4, float var5, float var6) {
      SpotifyCurve.ensureSourceClassInitialized();
      boolean var7 = SpotifyFactory.get().supported();
      long var8 = 76384909072803L;
      if (76384909072803L == 0L) {
         boolean var10000 = false;
      } else {
         int var21 = var8 < 0L ? -1 : 1;
      }

      if (var7) {
         String var11 = "Open Spotify";
         float var12 = var4.textWidth(Fonts.SEMIBOLD, var11, 10.0F) + 30.0F;
         float var13 = 20.0F;
         float var14 = var5 - var12;
         float var15 = var6 - var13 / 2.0F;
         long var16 = 76384909072804L;
         if (76384909072804L == 0L) {
            boolean var22 = false;
         } else {
            int var23 = var16 < 0L ? -1 : 1;
         }

         boolean var19 = this.n && var3.hover(this.C[K.length + 1], var14, var15, var12, var13, DrawContext.TypeE.HAND);
         float var20 = this.S.target(var19 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var3.dt));
         var4.roundRect(var14, var15, var12, var13, 10.0F, BlendMode.lerp(452984831, 788529151, var20));
         var4.roundRectOutline(var14, var15, var12, var13, 10.0F, 1.0F, BlendMode.lerp(587202559, BlendMode.withAlpha(Icons.PURPLE, 0.7F), var20));
         var4.icon("music", var14 + 12.0F, var6, 10.0F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var20));
         var4.text(Fonts.SEMIBOLD, var11, var14 + 21.0F, var6, 10.0F, BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var20), FontStyle.LEFT, FontWeight.MIDDLE);
         this.Q[0] = var14;
         this.Q[1] = var15;
         this.Q[2] = var12;
         this.Q[3] = var13;
      }
   }

   private int f(long var1) {
      SpotifyCurve.ensureSourceClassInitialized();
      String var3 = this.G.J.get();
      byte var4 = -1;
      switch (var3.hashCode()) {
         case -2128297621:
            boolean var5 = var3.equals("Player only");
            long var6 = 136536050648696L;
            if (136536050648696L == 0L) {
               boolean var17 = false;
            } else {
               int var18 = var6 < 0L ? -1 : 1;
            }

            if (var5) {
               var4 = 2;
            }
            break;
         case -2018662344:
            if (var3.equals("Lyrics only")) {
               long var13 = 136536050648695L;
               if (136536050648695L == 0L) {
                  boolean var15 = false;
               } else {
                  int var16 = var13 < 0L ? -1 : 1;
               }

               var4 = 1;
            }
            break;
         case -547811547:
            if (var3.equals("Combined")) {
               long var9 = 136536050648695L;
               if (136536050648695L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var14 = var9 < 0L ? -1 : 1;
               }

               var4 = 0;
            }
      }
      return switch (var4) {
         case 0 -> 2;
         case 1 -> 3;
         case 2 -> 4;
         default -> {
            long var12 = 136536050648696L;
            if (136536050648696L == 0L) {
               boolean var19 = false;
            } else {
               int var20 = var12 < 0L ? -1 : 1;
            }

            yield this.M("lyrics_y").get() > 0.5 && this.H("lyrics_placed").enabled() ? 1 : 0;
         }
      };
   }

   private void R(int var1, long var2) {
      long var4 = 61739231020611L;
      long var6 = 138152391588749L;
      short var8 = 32166;
      short var9 = 7225;
      int var10 = (int)(var6 << 48 >>> 48);
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifyModule var11 = this.G;
      long var12 = 111137372308229L;
      if (111137372308229L == 0L) {
         boolean var10000 = false;
      } else {
         int var29 = var12 < 0L ? -1 : 1;
      }

      switch (var1) {
         case 0:
            long var15 = 111137372308229L;
            if (111137372308229L == 0L) {
               boolean var36 = false;
            } else {
               int var37 = var15 < 0L ? -1 : 1;
            }

            var11.J.set("Split");
            this.n("lyrics_", var4);
            this.n("player_", var4);
            break;
         case 1:
            long var18 = 111137372308229L;
            if (111137372308229L == 0L) {
               boolean var34 = false;
            } else {
               int var35 = var18 < 0L ? -1 : 1;
            }

            int var20 = 798583939;
            var11.J.set("Split");
            this.t(var8, var9, (char)var10, "lyrics_", 0.5, 0.8);
            this.t(var8, var9, (char)var10, "player_", 1.0, 0.42);
            break;
         case 2:
            var11.J.set("Combined");
            int var21 = 53307;
            long var22 = 111137372308228L;
            if (111137372308228L == 0L) {
               boolean var32 = false;
            } else {
               int var33 = var22 < 0L ? -1 : 1;
            }

            var21 = 798556933;
            this.n("player_", var4);
            break;
         case 3:
            long var25 = 111137372308228L;
            if (111137372308228L == 0L) {
               boolean var30 = false;
            } else {
               int var31 = var25 < 0L ? -1 : 1;
            }

            var11.J.set("Lyrics only");
            this.n("lyrics_", var4);
            break;
         default:
            var11.J.set("Player only");
            this.n("player_", var4);
      }
   }

   private void n(String var1, long var2) {
      this.M(var1 + "x").reset();
      this.M(var1 + "y").reset();
      this.H(var1 + "placed").reset();
   }

   private void t(int var1, int var2, char var3, String var4, double var5, double var7) {
      this.M(var4 + "x").set(var5);
      this.M(var4 + "y").set(var7);
      this.H(var4 + "placed").set(true);
   }

   private NumberSetting M(String var1) {
      return (NumberSetting)this.G.setting(var1);
   }

   private BooleanSetting H(String var1) {
      return (BooleanSetting)this.G.setting(var1);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 139807513466184L;
      long var6 = var4 ^ 3344703755056L;
      long var8 = var4 ^ 28807800313421L;
      if (this.n && var3 == 0) {
         float var10 = (this.E - 32.0F) / 5.0F;
         if (var2 >= this.q + 54.0F && var2 < this.q + 54.0F + 60.0F + 20.0F) {
            float var11 = var10 + 8.0F;
            int var12 = (int)((var1 - this.s) / var11);
            if (var12 >= 0 && var12 < K.length && var1 - this.s - var12 * var11 < var10) {
               if (var12 != this.f(var6)) {
                  this.R(var12, var8);
                  UiAudioPolicy.play("SELECT");
               }

               return true;
            }
         }

         SpotifyCurve var13 = this.P;
         if (var13 != null && !Float.isNaN(this.Y) && this.B.down(var1 - this.Y, var2 - this.r, this.p, var13, this.a)) {
            this.W = true;
            return true;
         } else if (this.Q[2] > 0.0F && var1 >= this.Q[0] && var1 <= this.Q[0] + this.Q[2] && var2 >= this.Q[1] && var2 <= this.Q[1] + this.Q[3]) {
            UiAudioPolicy.play("CLICK");
            class_156.method_668().method_670("spotify:");
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void mouseDrag(float var1, float var2) {
      if (this.W && this.P != null) {
         this.B.drag(var1 - this.Y, var2 - this.r, this.P);
      }
   }

   public void mouseUp() {
      if (this.W && this.P != null) {
         this.B.up(Float.NaN, Float.NaN, this.P);
      }

      this.W = false;
   }

private static final class TypeI {
      boolean b;
      boolean A;
      float I;
      float E;
      float t;
   }

   private final class TypeR implements SpotifyUpdater2.Listener<SpotifyRenderer4.TypeI> {
      public SpotifyRenderer4.TypeI place(SpotifyUpdater2.TypeH<SpotifyRenderer4.TypeI> var1, List<SpotifyUpdater2.TypeH<SpotifyRenderer4.TypeI>> var2) {
         SpotifyRenderer4.TypeY var3 = SpotifyRenderer4.this.k;
         float var4 = var3.preferred();
         float var5 = var3.L + var1.M * (12.0F + var1.width() * var3.em(var4) / 64.0F / 2.0F);
         SpotifyRenderer4.TypeI var6 = new SpotifyRenderer4.TypeI();
         var6.E = var4;
         var6.I = (var5 - var3.W) / var4 + SpotifyRenderer4.this.b * 1.6F;
         return var6;
      }

      @Override
      public boolean keeps(SpotifyUpdater2.TypeH<SpotifyRenderer4.TypeI> var1) {
         return true;
      }
   }

   private final class TypeY implements SpotifyUpdater4.Listener<SpotifyRenderer4.TypeI> {
      float G;
      float o;
      float L;
      float x = Float.NaN;
      float W;
      float s;
      float r;
      float X;
      float U;
      float A;
      private int V = 1;
      private static final long a;

      public SpotifyRenderer4.TypeI place(SpotifyUpdater4.TypeM<SpotifyRenderer4.TypeI> var1, List<SpotifyUpdater4.TypeM<SpotifyRenderer4.TypeI>> var2) {
         long var3 = 124059889826772L;
         long var5 = var3 ^ 65494519735502L;
         var1.C = SpotifyRenderer4.this.v && var1.t.k == SpotifyUpdater4.TypeA.G;
         if (var1.C) {
            SpotifyRenderer4.TypeI var14 = new SpotifyRenderer4.TypeI();
            var14.A = true;
            return var14;
         } else {
            long var7 = var1.t.x * 7919L + (long)(SpotifyRenderer4.this.F.clock() * 10.0F);
            if (SpotifyRenderer4.this.G.inSky()) {
               return this.l(var5, var1, var2, var7);
            } else {
               int var9 = 0;
               if (var1.t.k == SpotifyUpdater4.TypeA.G) {
                  this.V = -this.V;
                  var9 = this.V;
               }

               float var10 = this.preferred();
               float var11 = var1.t.c * this.em(var10);
               float var12 = var9 == 0 ? this.L : this.L + var9 * (12.0F + var11 / 2.0F);
               var12 = Math.clamp(var12, this.G + var11 / 2.0F, Math.max(this.G + var11 / 2.0F, this.o - var11 / 2.0F));
               SpotifyRenderer4.TypeI var13 = new SpotifyRenderer4.TypeI();
               var13.E = var10;
               var13.I = (var12 - this.W) / var10 + SpotifyRenderer4.this.b * 1.6F;
               return var13;
            }
         }
      }

      float preferred() {
         return Math.clamp(0.7F - (SpotifyRenderer4.this.G.L.floatValue() - 3.0F) * 0.03F, 0.4F, 0.75F);
      }

      private SpotifyRenderer4.TypeI l(long var1, SpotifyUpdater4.TypeM var3, List var4, long var5) {
         SpotifyCurve.ensureSourceClassInitialized();
         SpotifyRenderer4.TypeI var7 = new SpotifyRenderer4.TypeI();
         var7.b = true;
         float var8 = Float.NEGATIVE_INFINITY;
         float[] var9 = new float[]{0.32F, 0.52F, 0.72F};
         float var10 = var3.t.c * this.A / 2.0F + 12.0F;
         float var11 = this.o - this.G - 2.0F * var10;

         for (int var12 = 0; var12 < var9.length; var12++) {
            long var13 = 82763218460441L;
            if (82763218460441L == 0L) {
               boolean var10000 = false;
            } else {
               int var42 = var13 < 0L ? -1 : 1;
            }

            for (int var16 = 0; var16 < 5; var16++) {
               long var17 = 82763218460441L;
               if (82763218460441L == 0L) {
                  boolean var43 = false;
               } else {
                  int var44 = var17 < 0L ? -1 : 1;
               }

               long var19 = 82763218460441L;
               if (82763218460441L == 0L) {
                  boolean var45 = false;
               } else {
                  int var46 = var19 < 0L ? -1 : 1;
               }

               float var22 = var11 < 0.0F ? (this.G + this.o) / 2.0F : this.G + var10 + var11 * var16 / 4.0F;
               float var23 = (SpotifySupport.hash(var5 + var12 * a + var16) - 0.5F) * 0.3F - Math.abs(var9[var12] - 0.52F) * 0.4F;
               if (!Float.isNaN(this.x)) {
                  var23 += Math.min(1.0F, Math.abs(var22 - this.x) / Math.max(1.0F, (this.o - this.G) * 0.4F));
               }

               for (SpotifyUpdater4.TypeM var25 : (Iterable<SpotifyUpdater4.TypeM>)(Object)(var4)) {
                  long var26 = 82763218460442L;
                  if (82763218460442L == 0L) {
                     boolean var47 = false;
                  } else {
                     int var48 = var26 < 0L ? -1 : 1;
                  }

                  long var28 = 82763218460441L;
                  if (82763218460441L == 0L) {
                     boolean var49 = false;
                  } else {
                     int var50 = var28 < 0L ? -1 : 1;
                  }

                  if (var25.Z != null) {
                     int var30 = (var25.leaving() ? 1 : 0);
                     long var31 = 82763218460441L;
                     if (82763218460441L == 0L) {
                        boolean var51 = false;
                     } else {
                        int var52 = var31 < 0L ? -1 : 1;
                     }

                     if ((var30 == 0)) {
                        long var33 = 82763218460441L;
                        if (82763218460441L == 0L) {
                           boolean var53 = false;
                        } else {
                           int var54 = var33 < 0L ? -1 : 1;
                        }

                        float var35;
                        var30 = (var35 = Math.abs(((SpotifyRenderer4.TypeI)var25.Z).t - var9[var12]) - 0.18F) == 0.0F ? 0 : (var35 < 0.0F ? -1 : 1);
                        long var36 = 82763218460441L;
                        if (82763218460441L == 0L) {
                           boolean var55 = false;
                        } else {
                           int var56 = var36 < 0L ? -1 : 1;
                        }

                        if (var30 < 0) {
                           var23 -= 0.6F;
                        }
                     }
                  }
               }

               long var40 = 82763218460441L;
               if (82763218460441L == 0L) {
                  boolean var57 = false;
               } else {
                  int var58 = var40 < 0L ? -1 : 1;
               }

               if (var23 > var8) {
                  var8 = var23;
                  var7.t = var9[var12];
                  var7.I = var22 - this.W + SpotifyRenderer4.this.b * 0.3F;
               }
            }

            long var39 = 82763218460441L;
            if (82763218460441L == 0L) {
               boolean var59 = false;
            } else {
               int var60 = var39 < 0L ? -1 : 1;
            }
         }

         long var38 = 82763218460441L;
         if (82763218460441L == 0L) {
            boolean var61 = false;
         } else {
            int var62 = var38 < 0L ? -1 : 1;
         }

         this.x = this.W + var7.I - SpotifyRenderer4.this.b * 0.3F;
         return var7;
      }

      @Override
      public boolean keeps(SpotifyUpdater4.TypeM<SpotifyRenderer4.TypeI> var1, float var2) {
         return true;
      }

      float em(float var1) {
         return this.U * (0.55F + 0.45F * var1 / 0.63F);
      }

      static {
         long var0 = 115006966782359L;
         long var2 = 115006966782354L ^ var0;
         a = var2;
      }
   }
}
