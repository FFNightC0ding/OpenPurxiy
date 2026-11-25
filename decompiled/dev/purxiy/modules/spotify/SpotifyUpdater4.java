package dev.purxiy.modules.spotify;

import dev.purxiy.i18n.Translations;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.font.TrueTypeFont;
import java.util.ArrayList;
import java.util.List;

final class SpotifyUpdater4<S> {
   static final float P = 64.0F;
   static final float w = 1.08F;
   static final TrueTypeFont T = Fonts.BOLD;
   static final long H = 110L;
   static final long L = 110L;
   static final long m = 520L;
   private static final long I = 6500L;
   private static final long q = 2500L;
   static final float V = 5.0F;
   static final float C = 0.42F;
   static final float r = 0.5F;
   static final float M = 1.1F;
   final List<SpotifyUpdater4.TypeM<S>> X = new ArrayList<>();
   private float n;
   private String t = "";
   private List<SpotifyUpdater4.TypeN> s = List.of();
   private SpotifySupport3 g;
   private float J = -1.0F;
   private int b = Integer.MIN_VALUE;
   private boolean Z;
   private float U;
   private static final String[] c = new String[4];
   private static final long[] d = new long[]{-945816790437240732L, -16177020991508984L};
   private static final long[] f = new long[]{
      2116337361658473376L,
      907719265462814435L,
      5418977489644649782L,
      6894647474402084419L,
      7929285293416545508L,
      3800949875254553713L,
      4558560654962127063L,
      6725190892024204088L,
      1425831157872070052L,
      2029097224292677228L
   };
   private static final Long[] h = new Long[10];

   float clock() {
      return this.n;
   }

   void update(SpotifyCurve var1, boolean var2, float var3, int var4, boolean var5, float var6, SpotifyUpdater4.Listener<S> var7) {
      long var8 = 128612824308353L;
      long var10 = var8 ^ 106980429140147L;
      int var12 = (int)((var8 ^ 106980429140147L) >>> 48);
      int var13 = (int)((var8 ^ 106980429140147L) << 16 >>> 32);
      int var14 = (int)(var10 << 48 >>> 48);
      long var15 = var8 ^ 31954628616416L;
      var10 = var8 ^ 24740676476115L;
      int var17 = (int)((var8 ^ 24740676476115L) >>> 32);
      int var18 = (int)((var8 ^ 24740676476115L) << 32 >>> 48);
      int var19 = (int)(var10 << 48 >>> 48);
      this.n += var3;
      SpotifyCurve.Data var20 = var1 == null ? null : var1.track();
      if (var2 && var20 != null) {
         String var21 = var20.key() + "|" + var1.lyricsState() + "|" + var1.lyrics().kind();
         if (!var21.equals(this.t)) {
            this.t = var21;
            this.u((short)var12, var13, var14);
            this.b = Integer.MIN_VALUE;
            this.Z = false;
         }

         if (var1.lyrics().synced() && var1.lyricsState() == SpotifyCurve.TypeF.h) {
            this.Q(var1, var17, var20, var4, (char)var18, var5, var6, var7, (short)var19);
         } else {
            this.u(var1, var15, var20, var6, var7);
         }
      } else {
         this.u((short)var12, var13, var14);
         this.b = Integer.MIN_VALUE;
         this.t = "";
      }

      SpotifyUpdater4.TypeM var28 = this.front();
      if (var28 != null && !var7.keeps(var28, var3)) {
         this.X.remove(var28);
         if (var28.Z != null) {
            SpotifyUpdater4.TypeM var22 = new SpotifyUpdater4.TypeM(var28.t, var28.K);
            var22.Z = var28.Z;
            var22.q = var28.q;
            var22.U = true;
            var22.j = var28.j;
            var22.D = this.n;
            this.X.add(var22);
         }

         var28.Z = var7.place(var28, this.X);
         var28.q = this.n;
         this.X.add(var28);
      }

      float var29 = 1.0F - (float)Math.exp(-7.0F * var3);
      float var23 = 1.1F * Math.max(1, var4 - 1);

      for (int var24 = this.X.size() - 1; var24 >= 0; var24--) {
         SpotifyUpdater4.TypeM var25 = this.X.get(var24);
         float var26 = var25.g == 0 ? 1.0F : 0.42F * (float)Math.pow(0.5, var25.g - 1);
         var25.j = var25.j + (var26 - var25.j) * var29;
         if (var25.g > 0 && !var25.leaving() && this.n - var25.d > var23) {
            var25.D = this.n;
         }

         if (var25.leaving() && this.n - var25.D > SpotifyRenderer.leaveTime(var25)) {
            this.X.remove(var24);
         }
      }
   }

   SpotifyUpdater4.TypeM<S> front() {
      for (int var1 = this.X.size() - 1; var1 >= 0; var1--) {
         SpotifyUpdater4.TypeM var2 = this.X.get(var1);
         if (!var2.leaving()) {
            return var2.g == 0 ? var2 : null;
         }
      }

      return null;
   }

   void clear() {
      this.X.clear();
      this.b = Integer.MIN_VALUE;
      this.t = "";
   }

   private void Q(SpotifyCurve var1, int var2, SpotifyCurve.Data var3, int var4, char var5, boolean var6, float var7, SpotifyUpdater4.Listener var8, short var9) {
      long var10 = 108273484121682L;
      long var12 = var10 ^ 131718883413088L;
      int var14 = (int)((var10 ^ 131718883413088L) >>> 48);
      int var15 = (int)((var10 ^ 131718883413088L) << 16 >>> 32);
      int var16 = (int)(var12 << 48 >>> 48);
      long var17 = var10 ^ 37596591064731L;
      long var19 = var10 ^ 136355831741524L;
      var12 = var10 ^ 117949078099230L;
      int var21 = (int)((var10 ^ 117949078099230L) >>> 32);
      int var22 = (int)((var10 ^ 117949078099230L) << 32 >>> 48);
      int var23 = (int)(var12 << 48 >>> 48);
      SpotifyCurve.ensureSourceClassInitialized();
      this.d(var19, var1.lyrics(), var3.durationMs(), var7);
      if (!this.s.isEmpty()) {
         double var24 = var1.lyricsTime();
         int var26 = this.h(var24 + (var6 ? 520L : (var1.lyrics().wordTimed() ? 110L : 110L)), var21, var22, (short)var23);
         if (var26 >= 0) {
            SpotifyUpdater4.TypeN var27 = this.s.get(var26);
            if (var27.w - var27.s >= 6500L) {
               double var28;
               int var30 = (var28 = var24 - (var27.s + 2500L)) == 0.0 ? 0 : (var28 < 0.0 ? -1 : 1);
               if (var30 > 0) {
                  var26 = -1;
               }
            }
         }

         short var32 = 18002;
         if (var26 != this.b) {
            boolean var37;
            label60: {
               label59: {
                  var32 = 25209;
                  if (this.b != Integer.MIN_VALUE) {
                     var32 = 25209;
                     var32 = 18002;
                     if (var26 < this.b) {
                        break label59;
                     }

                     var32 = 25209;
                     if (var26 > this.b + 2) {
                        break label59;
                     }
                  }

                  var37 = false;
                  break label60;
               }

               var37 = true;
            }

            SpotifyUpdater4.TypeN var38 = var26 >= 0 ? this.s.get(var26) : null;
            this.b = var26;
            if (!var37 && var38 != null) {
               this.V(var4, var17);
            } else {
               this.u((short)var14, var15, var16);
            }

            if (var38 != null) {
               this.M(var38, var8);
            }
         }
      }
   }

   private void u(SpotifyCurve var1, long var2, SpotifyCurve.Data var4, float var5, SpotifyUpdater4.Listener var6) {
      long var7 = 23868539324978L;
      byte var9 = 0;
      int var10 = 364205006;
      int var11 = (int)(var7 << 48 >>> 48);
      long var12 = 11159130096723L;
      SpotifyCurve.ensureSourceClassInitialized();
      if (var1.lyricsState() == SpotifyCurve.TypeF.h) {
         long var14 = 116449548794465L;
         int var17 = var14 < 0L ? -1 : 1;
         long var17x = 116449548794464L;
         int var20 = var17x < 0L ? -1 : 1;
         if (!this.Z) {
            this.Z = true;
            this.U = this.n;
            this.u(var9, var10, var11);
            this.M(this.k(var1, var4, var12, var5), var6);
         } else {
            long var20x = 116449548794464L;
            int var20x = var20x < 0L ? -1 : 1;
            if (this.n - this.U > 5.0F) {
               this.u(var9, var10, var11);
            }
         }
      }
   }

   private void M(SpotifyUpdater4.TypeN var1, SpotifyUpdater4.Listener<S> var2) {
      SpotifyUpdater4.TypeM var3 = new SpotifyUpdater4.TypeM(var1, this.n);
      var3.Z = var2.place(var3, this.X);
      this.X.add(var3);
   }

   private void V(int var1, long var2) {
      SpotifyCurve.ensureSourceClassInitialized();

      for (SpotifyUpdater4.TypeM var5 : this.X) {
         long var6 = 70681236324553L;
         int var9 = var6 < 0L ? -1 : 1;
         long var9x = 70681236324552L;
         int var11 = var9x < 0L ? -1 : 1;
         if (var5.leaving()) {
            long var17 = 70681236324553L;
            int var21 = var17 < 0L ? -1 : 1;
         } else {
            var5.g++;
            long var11x = 70681236324553L;
            int var14 = var11x < 0L ? -1 : 1;
            var5.d = this.n;
            long var14x = 70681236324552L;
            int var14x = var14x < 0L ? -1 : 1;
            if (var5.g >= var1 || var5.t.k != SpotifyUpdater4.TypeA.G) {
               var5.D = this.n;
            }
         }
      }

      long var16 = 70681236324553L;
      int var16x = var16 < 0L ? -1 : 1;
   }

   private void u(short var1, int var2, int var3) {
      SpotifyCurve.ensureSourceClassInitialized();

      for (SpotifyUpdater4.TypeM var5 : this.X) {
         boolean var6 = var5.leaving();
         if (!var6) {
            var5.D = this.n;
         }
      }
   }

   private int h(double var1, int var3, int var4, short var5) {
      SpotifyCurve.ensureSourceClassInitialized();
      int var6 = 0;
      int var7 = this.s.size() - 1;
      int var8 = -1;

      while (var6 <= var7) {
         int var9 = var6 + var7 >>> 1;
         if (this.s.get(var9).B <= var1) {
            var8 = var9;
            var6 = var9 + 1;
         } else {
            var7 = var9 - 1;
         }
      }

      return var8;
   }

   private void d(long var1, SpotifySupport3 var3, long var4, float var6) {
      SpotifyCurve.ensureSourceClassInitialized();
      if (this.g == var3) {
         long var7 = 33512327920133L;
         int var9 = var7 < 0L ? -1 : 1;
         if (this.J == var6) {
            return;
         }
      }

      this.g = var3;
      this.J = var6;
      this.b = Integer.MIN_VALUE;
      List var30 = var3.lines();
      ArrayList var8 = new ArrayList();
      if (var30.isEmpty()) {
         this.s = var8;
      } else {
         long var9 = 33512327920133L;
         int var12 = var9 < 0L ? -1 : 1;
         long var12x = var4 > 0L ? var4 : ((SpotifySupport3.Data)var30.getLast()).startMs() + 8000L;
         SpotifyRegistry var14 = SpotifyRegistry.get();

         for (int var15 = 0; var15 < var30.size(); var15++) {
            SpotifySupport3.Data var16 = (SpotifySupport3.Data)var30.get(var15);
            long var17 = var3.nextStart(var15, var12x);
            if (var16.isBreak()) {
               long var32 = 33512327920133L;
               int var35 = var32 < 0L ? -1 : 1;
            } else {
               long var19 = SpotifySupport4.sungEnd(var3, var15, var12x);
               String var21 = var14.shown(var16.text());
               SpotifySupport3.Data var22 = var21.equals(var16.text()) ? var16 : new SpotifySupport3.Data(var16.startMs(), var21, List.of(), var16.endMs());

               int var23;
               for (var23 = var15 + 1; var23 < var30.size(); var23++) {
                  boolean var24 = ((SpotifySupport3.Data)var30.get(var23)).isBreak();
                  long var25 = 33512327920133L;
                  int var28 = var25 < 0L ? -1 : 1;
                  long var28x = 33512327920133L;
                  int var28x = var28x < 0L ? -1 : 1;
                  if (!var24) {
                     break;
                  }
               }

               long var33 = 33512327920133L;
               int var27 = var33 < 0L ? -1 : 1;
               long var27x = var23 < var30.size() ? ((SpotifySupport3.Data)var30.get(var23)).startMs() : var12x;
               var8.add(layout(SpotifyUpdater4.TypeA.G, var8.size(), var16.startMs(), var19, var27x, var22, var21, var6 * 64.0F, null));
            }
         }

         long var31 = 33512327920133L;
         int var331 = var31 < 0L ? -1 : 1;
         this.s = var8;
      }
   }

   private SpotifyUpdater4.TypeN k(SpotifyCurve var1, SpotifyCurve.Data var2, long var3, float var5) {
      SpotifyCurve.ensureSourceClassInitialized();
      int var6 = SpotifyUpdater4.TypeF.g[var1.lyrics().kind().ordinal()];
      long var7 = 11159130096722L;
      int var10 = var7 < 0L ? -1 : 1;

      String var10 = switch (var6) {
         case 1 -> "Instrumental";
         case 2 -> "The lyrics of this song are not synced";
         default -> "No lyrics for this song";
      };
      SpotifyRegistry var11 = SpotifyRegistry.get();
      String var12 = var11.shown(var2.title());
      String var13 = var11.shown(var2.artist());
      String var14 = var13.isEmpty() ? var12 : var12 + " · " + var13;
      return layout(SpotifyUpdater4.TypeA.M, -1, 0L, 0L, 0L, null, var14, var5 * 64.0F, Translations.tr(var10));
   }

   static SpotifyUpdater4.TypeN layout(
      SpotifyUpdater4.TypeA var0, int var1, long var2, long var4, long var6, SpotifySupport3.Data var8, String var9, float var10, String var11
   ) {
      long var12 = 97875336781616L;
      long var14 = var12 ^ 27021156736657L;
      int[] var16 = SpotifySupport7.spans(var9);
      int var17 = var16.length / 2;
      String[] var18 = new String[var17];
      float[] var19 = new float[var17];
      int[] var20 = new int[var17];

      for (int var21 = 0; var21 < var17; var21++) {
         var18[var21] = var9.substring(var16[var21 * 2], var16[var21 * 2 + 1]);
         var19[var21] = T.measure(var18[var21], 64.0F, 0.0F);
         var20[var21] = var16[var21 * 2];
      }

      float var32 = T.measure(" ", 64.0F, 0.0F);
      int var22 = w(var19, var32, var10, null, var14);
      float var23 = 0.0F;
      float var24 = var10;
      if (var22 > 1) {
         for (int var25 = 0; var25 < 14; var25++) {
            float var26 = (var23 + var24) / 2.0F;
            if (w(var19, var32, var26, null, var14) <= var22) {
               var24 = var26;
            } else {
               var23 = var26;
            }
         }
      }

      int[] var34 = new int[var17];
      var22 = Math.max(1, w(var19, var32, var24, var34, var14));
      String[] var35 = new String[var22];
      float[] var27 = new float[var22];
      float[] var28 = new float[var17];
      StringBuilder[] var29 = new StringBuilder[var22];

      for (int var30 = 0; var30 < var17; var30++) {
         int var31 = var34[var30];
         if (var29[var31] == null) {
            var29[var31] = new StringBuilder();
         } else {
            var29[var31].append(' ');
            var27[var31] += var32;
         }

         var28[var30] = var27[var31];
         var29[var31].append(var18[var30]);
         var27[var31] += var19[var30];
      }

      for (int var36 = 0; var36 < var22; var36++) {
         var35[var36] = var29[var36] == null ? "" : var29[var36].toString();
      }

      long[] var37 = var8 != null ? SpotifySupport7.appearAt(var8, var4, var20) : new long[var17];
      return new SpotifyUpdater4.TypeN(var0, var1, var2, var4, var6, var8, var35, var27, var18, var34, var28, var19, var20, var37, var11);
   }

   private static int w(float[] var0, float var1, float var2, int[] var3, long var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      int var6 = 0;
      float var7 = -1.0F;

      for (int var8 = 0; var8 < var0.length; var8++) {
         float var9;
         int var10 = (var9 = var7 - 0.0F) == 0.0F ? 0 : (var9 < 0.0F ? -1 : 1);
         long var11 = 72117783396769L;
         int var25 = var11 < 0L ? -1 : 1;
         if (var10 < 0) {
            float var14 = var0[var8];
            long var15 = 72117783396769L;
            int var17 = var15 < 0L ? -1 : 1;
            var7 = var14;
         } else {
            long var24 = 72117783396769L;
            int var16 = var24 < 0L ? -1 : 1;
            float var16x;
            var10 = (var16x = var7 + var1 + var0[var8] - var2) == 0.0F ? 0 : (var16x < 0.0F ? -1 : 1);
            long var17 = 72117783396768L;
            int var19 = var17 < 0L ? -1 : 1;
            if (var10 <= 0) {
               var7 += var1 + var0[var8];
               long var19x = 72117783396768L;
               int var20 = var19x < 0L ? -1 : 1;
            } else {
               var6++;
               var7 = var0[var8];
            }
         }

         long var25x = 72117783396768L;
         int var31 = var25x < 0L ? -1 : 1;
         if (var3 != null) {
            var3[var8] = var6;
         }
      }

      int var21 = var0.length;
      long var22 = 72117783396769L;
      int var24 = var22 < 0L ? -1 : 1;
      long var12 = 72117783396769L;
      int var29 = var12 < 0L ? -1 : 1;
      return var21 == 0 ? 0 : var6 + 1;
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ -994957012 ^ 10010) & 32767;
      if (h[var3] == null) {
         h[var3] = f[var3] ^ 2029097224292673836L;
      }

      return h[var3];
   }

   interface Listener<S> {
      S place(SpotifyUpdater4.TypeM<S> var1, List<SpotifyUpdater4.TypeM<S>> var2);

      boolean keeps(SpotifyUpdater4.TypeM<S> var1, float var2);
   }

   static enum TypeA {
      G,
      M;
   }

   static class TypeF {
      static final int[] g = new int[SpotifySupport3.TypeK.values().length];

      static {
         try {
            g[SpotifySupport3.TypeK.I.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
         }

         try {
            g[SpotifySupport3.TypeK.g.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
         }
      }
   }

   static final class TypeM<S> {
      final SpotifyUpdater4.TypeN t;
      S Z;
      final float K;
      float q = Float.NaN;
      float D = Float.NaN;
      boolean U;
      int g;
      float j = 1.0F;
      float d;
      boolean C;

      TypeM(SpotifyUpdater4.TypeN var1, float var2) {
         this.t = var1;
         this.K = var2;
      }

      boolean leaving() {
         return !Float.isNaN(this.D);
      }
   }

   static final class TypeN {
      final SpotifyUpdater4.TypeA k;
      final int x;
      final long B;
      final long s;
      final long w;
      final SpotifySupport3.Data h;
      final String[] U;
      final float[] i;
      final String[] o;
      final int[] Q;
      final float[] E;
      final float[] W;
      final int[] A;
      final long[] F;
      final long j;
      final String l;
      final float c;
      final float C;

      TypeN(
         SpotifyUpdater4.TypeA var1,
         int var2,
         long var3,
         long var5,
         long var7,
         SpotifySupport3.Data var9,
         String[] var10,
         float[] var11,
         String[] var12,
         int[] var13,
         float[] var14,
         float[] var15,
         int[] var16,
         long[] var17,
         String var18
      ) {
         this.j = var9 != null && var9.wordTimed() ? 110L : 110L;
         this.k = var1;
         this.x = var2;
         this.B = var3;
         this.s = var5;
         this.w = var7;
         this.h = var9;
         this.U = var10;
         this.i = var11;
         this.o = var12;
         this.Q = var13;
         this.E = var14;
         this.W = var15;
         this.A = var16;
         this.F = var17;
         this.l = var18;
         float var19 = 0.0F;

         for (float var23 : var11) {
            var19 = Math.max(var19, var23);
         }

         this.c = Math.max(var19, var18 != null ? SpotifyUpdater4.T.measure(var18, 29.44F, 0.0F) : 0.0F) / 64.0F;
         this.C = var10.length * 1.08F + (var18 != null ? 0.62F : 0.0F);
      }
   }
}
