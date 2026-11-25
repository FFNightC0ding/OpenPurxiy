package dev.purxiy.modules.spotify;

import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.TabListProvider;
import dev.purxiy.render.RenderPipelines;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CompletableFuture;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;

public final class SpotifyRegistry {
   private static final SpotifyRegistry O = new SpotifyRegistry(112706006650242L);
   private static final long N = 350000000L;
   private static final long[] D = new long[]{3L, 6L, 12L, 20L, 30L};
   private static final long S = 2000L;
   private static final long[] Y = new long[]{20L, 45L, 90L};
   private static final long R = 40000000L;
   private final SpotifyCurve K = new SpotifyCurve(false, new SpotifyTrack());
   private final SpotifyCurve g = new SpotifyCurve(true, null);
   private long o = -1L;
   private SpotifyLyricCache F;
   private CompletableFuture<SpotifyLyrics> I;
   private long C;
   private long z;
   private long Q;
   private int c;
   private boolean h;
   private long i = Long.MAX_VALUE;
   private int n;
   private boolean M;
   private boolean d;
   private boolean J;
   private long m;
   private long W;
   private long k = Long.MIN_VALUE;
   private final Map<String, String> H = new HashMap<>();
   private long a;
   static SpotifyModule y;
   private static ModuleContext p;
   private static int U = 0;
   private static int B = 0;
   private static final long[] f = new long[]{6939675453562496268L, -1516486420194010888L};
   private static final Integer[] j = new Integer[2];
   private static final long[] l = new long[]{
      1819951260408662659L,
      8314983310441824186L,
      3443425151312376896L,
      693795748049953024L,
      1871016133384467794L,
      6986276492318179502L,
      4830600269523933417L,
      -1022010222584442918L,
      3627346792826876776L,
      7984471803068930828L,
      -127621051180607516L,
      1603065201814425900L,
      9147935461562924611L,
      1210206830122297061L,
      3281779126290161047L,
      1743717952572438849L,
      5787412771030417299L,
      -3612742689643564552L,
      5365092217260277995L
   };
   private static final Long[] q = new Long[19];

   private SpotifyRegistry(long var1) {
      SpotifyFactory.get().logTo(var0 -> ClientServices.LOGGER.warn("Spotify Player: {}", var0));
      this.g.O = new SpotifyCurve.Data("sample", "Violet Hour", "SourceByDevFFnightmare DeObfDeCompByGuideX", "Nightfall Sessions", 46000L);
      this.g.f = SpotifyDemoTrack.f;
      this.g.z = SpotifyCurve.CurveKind.h;
      this.g.b = true;
      this.g.S = 0.72F;
      this.g.k = 255;
      this.g.I = true;
      this.g.R = 1800.0;
   }

   public static SpotifyRegistry get() {
      return O;
   }

   public SpotifyCurve live() {
      return this.K;
   }

   public SpotifyCurve sample() {
      return this.g;
   }

   public SpotifyCurve pick(boolean var1) {
      return !this.M && (!var1 || this.K.O != null) ? this.K : this.g;
   }

   public void devSample(boolean var1) {
      this.M = var1;
   }

   public void devSync(boolean var1) {
      this.d = var1;
      this.k = Long.MIN_VALUE;
   }

   public void devLookups(boolean var1) {
      this.J = var1;
   }

   public void keepAlive(boolean var1) {
      SpotifyFactory.get().keepAlive(var1);
   }

   static double timingMillis() {
      return (Double)y.Z.get() * 1000.0;
   }

   public String shown(String var1) {
      long var2 = System.nanoTime();
      if (var2 - this.a > 3000000000L) {
         this.H.clear();
         this.a = var2;
      }

      return this.H.computeIfAbsent(var1, TabListProvider::plain);
   }

   public void frame() {
      long var1 = 100112666837944L;
      long var3 = var1 ^ 93478799858446L;
      long var5 = var1 ^ 93746856847649L;
      RenderPipelines var7 = RenderPipelines.get();
      if (var7.frame() != this.o) {
         this.o = var7.frame();
         float var8 = var7.delta();
         float var9 = var7.time();
         long var10 = System.nanoTime();
         this.J(var10, var5, var8, var9);
         this.p(var8, var9, var3);
      }
   }

   private void J(long var1, long var3, float var5, float var6) {
      long var7 = 8023L;
      int var9 = -1725670371;
      long var10 = 24611037266559L;
      long var12 = 127526950357746L;
      byte var14 = 0;
      int var15 = 1945906835;
      int var16 = (int)(var12 << 48 >>> 48);
      long var17 = 42501010721092L;
      SpotifyCurve var19 = this.K;
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifyFactory var20 = SpotifyFactory.get();
      var20.spotifyOnly(!y.S.is("Any player"));
      SpotifyData var21 = var20.state();
      long var22 = 15730047724185L;
      int var25 = var22 < 0L ? -1 : 1;
      if (var21 != null && !var21.title().isBlank()) {
         String var59 = var21.app() + "\n" + var21.title() + "\n" + var21.artist() + "\n" + var21.album();
         if (var19.O == null || !var59.equals(var19.O.key())) {
            this.G(var10, var19, var59, var1);
         }

         long var26 = 15730047724185L;
         int var60 = var26 < 0L ? -1 : 1;
         var19.O = new SpotifyCurve.Data(var59, var21.title(), var21.artist(), var21.album(), var21.durationMs());
         var19.k = var21.controls();
         if (var19.j >= 0) {
            long var29 = 15730047724184L;
            int var29x = var29 < 0L ? -1 : 1;
            if (var19.j == 1 == var21.playing() || var1 > var19.D) {
               var19.j = -1;
            }
         }

         boolean var60x;
         if (var19.j >= 0) {
            long var30 = 15730047724184L;
            int var30x = var30 < 0L ? -1 : 1;
            var60x = var19.j == 1;
         } else {
            var60x = var21.playing();
         }

         var19.b = var60x;
         long var61 = 15730047724185L;
         int var561 = var61 < 0L ? -1 : 1;
         long var32 = 15730047724184L;
         int var32x = var32 < 0L ? -1 : 1;
         if (var19.o >= 0) {
            long var35 = 15730047724184L;
            int var63 = var35 < 0L ? -1 : 1;
            boolean var37 = var19.o == 1;
            int var38 = var21.shuffle();
            long var39 = 15730047724184L;
            int var38x = var39 < 0L ? -1 : 1;
            if (var37 == (var38 == 1) || var1 > var19.C) {
               var19.o = -1;
            }
         }

         long var62x = 15730047724184L;
         int var61x = var62x < 0L ? -1 : 1;
         boolean var63;
         if (var19.o >= 0) {
            long var64 = 15730047724185L;
            int var41 = var64 < 0L ? -1 : 1;
            var63 = var19.o == 1;
         } else {
            int var65 = var21.shuffle();
            long var40 = 15730047724184L;
            int var69 = var40 < 0L ? -1 : 1;
            var63 = var65 == 1;
         }

         var19.I = var63;
         if (var19.p != null && (var19.p == var21.repeat() || var1 > var19.d)) {
            var19.p = null;
         }

         var19.M = var19.p != null ? var19.p : var21.repeat();
         var19.l.report(var21.positionMs(), var19.b, var21.anchorNanos());
         var19.R = Math.max(0.0, Math.min(var21.durationMs() > 0L ? var21.durationMs() : Double.MAX_VALUE, var19.l.update(var1)));
         if (this.d) {
            this.k((char)var14, var19, var15, var21, var1, (short)var16);
         }

         if (var19.t >= 0.0F && var1 - var19.L > 40000000L) {
            var20.send(new SpotifyApi.SetVolume(var19.t));
            var19.t = -1.0F;
            var19.L = var1;
         }

         float var66 = var20.volume();
         float var67;
         int var41 = (var67 = var19.B - 0.0F) == 0.0F ? 0 : (var67 < 0.0F ? -1 : 1);
         long var42 = 15730047724185L;
         int var45 = var42 < 0L ? -1 : 1;
         if (var41 >= 0 && (var1 > var19.G || Math.abs(var66 - var19.B) < 0.005F)) {
            var19.B = -1.0F;
         }

         var19.S = var19.B >= 0.0F ? var19.B : var66;
         var19.g = var20.muted();
         float var45x = var20.peak();
         float var46 = 20.0F * (float)Math.log10(Math.max(var45x, 1.0E-5F));
         float var47 = var19.b ? Math.max(0.0F, Math.min(1.0F, (var46 + 44.0F) / 44.0F)) : 0.0F;
         this.a(var17, var19, var47, var5, var6);
         SpotifyFactory.Data var52 = var20.artwork();
         long var53 = 15730047724185L;
         int var46x = var53 < 0L ? -1 : 1;
         if (var19.Y != null) {
            var19.Y.want(var59);
            long var56x = 15730047724184L;
            int var48 = var56x < 0L ? -1 : 1;
            if (var52 != null && var52.track() == var21.track()) {
               var19.Y.offer(var59, var52.data());
            }

            var19.Y.update(var5);
         }

         this.f(var19, var7, var9, var21, var1);
      } else {
         if (var19.O != null) {
            var19.O = null;
            long var25x = 15730047724184L;
            int var26x = var25x < 0L ? -1 : 1;
            var19.l.reset();
            long var28x = 15730047724184L;
            int var61x = var28x < 0L ? -1 : 1;
            var19.f = SpotifyLyrics.f;
            var19.z = SpotifyCurve.CurveKind.i;
            this.I = null;
            if (var19.Y != null) {
               var19.Y.clear();
            }
         }

         var19.A = 0.0F;
         if (var19.Y != null) {
            var19.Y.update(var5);
         }
      }
   }

   private void G(long var1, SpotifyCurve var3, String var4, long var5) {
      SpotifyCurve.ensureSourceClassInitialized();
      var3.l.reset();
      long var7 = 24611037266559L;
      int var9 = var7 < 0L ? -1 : 1;
      var3.f = SpotifyLyrics.f;
      var3.z = SpotifyCurve.CurveKind.i;
      this.I = null;
      this.C = var5 + 350000000L;
      this.c = 0;
      this.h = false;
      this.i = Long.MAX_VALUE;
      this.n = 0;
      if (var3.O != null) {
         var3.W = var5 - var3.u < 2000000000L ? var3.U : 1;
         var3.h++;
      }
   }

   private void f(SpotifyCurve var1, long var2, int var4, SpotifyData var5, long var6) {
      long var8 = 34461091912733L;
      long var10 = var8 ^ 26698695104747L;
      SpotifyCurve.ensureSourceClassInitialized();
      boolean var12 = this.I != null || var1.z == SpotifyCurve.CurveKind.h;
      int var13 = -1725670371;
      if (var12 && Math.abs(var5.durationMs() - this.z) > 2000L && !var1.f.synced()) {
         this.I = null;
         var1.f = SpotifyLyrics.f;
         var1.z = SpotifyCurve.CurveKind.i;
         this.C = var6;
      }

      boolean var14;
      label145: {
         boolean var15 = var1.z == SpotifyCurve.CurveKind.Z || this.h;
         if (var1.z != SpotifyCurve.CurveKind.i) {
            var13 = -1725670371;
            if (!var15 || var6 <= this.Q) {
               var14 = false;
               break label145;
            }
         }

         var14 = true;
      }

      boolean var32 = var14;
      boolean var16 = var1.z == SpotifyCurve.CurveKind.h && !this.h && var1.f.synced() && !var1.f.wordTimed();
      if (var16 && this.I == null && var6 > this.i) {
         var13 = -1725670371;
         this.i = this.n < Y.length ? var6 + Y[this.n++] * 1000000000L : Long.MAX_VALUE;
         var32 = this.H(var10).wordsPending(new SpotifyLyricCache.LyricQuery(var5.title(), var5.artist(), var5.album(), var5.durationMs()));
      }

      if (var32 && this.I == null && var6 >= this.C) {
         this.I = this.H(var10).lookup(new SpotifyLyricCache.LyricQuery(var5.title(), var5.artist(), var5.album(), var5.durationMs()));
         this.z = var5.durationMs();
         if (this.J) {
            this.m = var6;
            ClientServices.LOGGER
               .info(
                  String.format(
                     Locale.ROOT, "Spotify dev: looking up \"%s\" by \"%s\" (%d ms long), try %d", var5.title(), var5.artist(), var5.durationMs(), this.c + 1
                  )
               );
         }
      }

      CompletableFuture var17 = this.I;
      var13 = -1725670371;
      if (var17 != null && var17.isDone()) {
         this.I = null;

         try {
            label122: {
               SpotifyLyrics var18 = (SpotifyLyrics)var17.join();
               if (var1.z == SpotifyCurve.CurveKind.h && !this.h && !var18.wordTimed()) {
                  long var34 = 8023L;
                  int var39 = var34 < 0L ? -1 : 1;
                  if (var1.f.synced()) {
                     break label122;
                  }
               }

               var1.f = var18;
            }

            if (var1.z != SpotifyCurve.CurveKind.h) {
               this.i = var6 + Y[0] * 1000000000L;
            }

            var1.z = SpotifyCurve.CurveKind.h;
            this.h = false;
            if (this.J) {
               Logger var33 = ClientServices.LOGGER;
               Locale var35 = Locale.ROOT;
               Object[] var37 = new Object[]{var1.f.kind(), null, null, null};
               int var39 = var1.f.lines().size();
               var37[1] = var39;
               var37[2] = var1.f.wordTimed() ? " (words timed)" : "";
               var37[3] = (var6 - this.m) / 1000000L;
               var33.info(String.format(var35, "Spotify dev: lyrics %s, %d lines%s, after %d ms", var37));
            }
         } catch (CancellationException var27) {
         } catch (RuntimeException var281) {
            SpotifyLyrics var19 = var281.getCause() instanceof SpotifyLyricCache.LyricLookupException var20 ? var20.found() : SpotifyLyrics.f;
            if (var19.kind() != SpotifyLyrics.LyricStyle.H) {
               var1.f = var19;
               var1.z = SpotifyCurve.CurveKind.h;
               this.h = true;
            } else if (!this.h && (var1.z != SpotifyCurve.CurveKind.h || !var1.f.synced())) {
               var1.z = SpotifyCurve.CurveKind.Z;
            }

            this.Q = var6 + D[Math.min(this.c, D.length - 1)] * 1000000000L;
            this.c++;
            if (this.J) {
               Logger var36 = ClientServices.LOGGER;
               Locale var38 = Locale.ROOT;
               Object[] var22 = new Object[]{(var6 - this.m) / 1000000L, null, null, null};
               long var23 = 8023L;
               int var26 = var23 < 0L ? -1 : 1;
               Throwable var26x = var281.getCause();
               int var25x = -1725670371;
               var22[1] = var26x != null ? var281.getCause().getMessage() : var281.getMessage();
               var22[2] = var19.kind();
               var22[3] = (this.Q - var6) / 1000000000L;
               var36.info(String.format(var38, "Spotify dev: lyrics sources unreachable after %d ms (%s; meanwhile %s), asking again in %d s", var22));
            }
         }
      }
   }

   private void k(char var1, SpotifyCurve var2, int var3, SpotifyData var4, long var5, short var7) {
      SpotifyCurve.ensureSourceClassInitialized();
      long var8 = var4.anchorNanos() / 1000000L - var4.positionMs();
      boolean var10 = this.k == Long.MIN_VALUE || Math.abs(var8 - this.k) > 2L;
      int var11 = 1945906835;
      var11 = 1945906835;
      if (var10 || var5 - this.W >= 100000000L) {
         this.W = var5;
         Logger var12 = ClientServices.LOGGER;
         Locale var13 = Locale.ROOT;
         Object[] var14 = new Object[]{System.currentTimeMillis(), var2.R, var4.positionAt(var5), var2.lyricsTime(), null, null};
         boolean var15 = var4.playing();
         var14[4] = var15 ? "playing" : "paused";
         var14[5] = var10 && this.k != Long.MIN_VALUE ? String.format(Locale.ROOT, " report moved %+d ms", var8 - this.k) : "";
         var12.info(String.format(var13, "Spotify sync: wall %d shown %.0f reported %d lyrics %.0f %s%s", var14));
         this.k = var8;
      }
   }

   private void p(float var1, float var2, long var3) {
      long var5 = 42501010721092L;
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifyCurve var7 = this.g;
      long var8 = 15435866642613L;
      int var14 = var8 < 0L ? -1 : 1;
      if (var7.b) {
         var7.R += var1 * 1000.0;
         long var11 = 15435866642613L;
         int var121 = var11 < 0L ? -1 : 1;
         if (var7.R >= 46000.0) {
            var7.R -= 46000.0;
         }
      }

      float var14x = (float)Math.pow(Math.max(0.0, Math.sin(var2 * Math.PI * 2.0 * 1.35)), 3.0);
      float var12 = var7.b ? 0.45F + 0.3F * var14x + 0.12F * (float)Math.sin(var2 * 0.9) : 0.0F;
      this.a(var5, var7, var12, var1, var2);
   }

   private void a(long var1, SpotifyCurve var3, float var4, float var5, float var6) {
      SpotifyCurve.ensureSourceClassInitialized();
      float var7 = var4 > var3.A ? 22.0F : 5.5F;
      var3.A = var3.A + (var4 - var3.A) * Math.min(1.0F, var7 * var5);

      for (int var8 = 0; var8 < var3.s.length; var8++) {
         float var9 = 0.5F + 0.5F * (float)(Math.sin(var6 * (7.3 + var8 * 2.9) + var8 * 1.7) * Math.sin(var6 * (3.1 + var8 * 1.3) + var8));
         float var10 = var3.b ? Math.max(0.14F, var3.A * (0.45F + 0.55F * var9)) : 0.14F;
         float var11 = var10 > var3.s[var8] ? 26.0F : 7.0F;
         var3.s[var8] = var3.s[var8] + (var10 - var3.s[var8]) * Math.min(1.0F, var11 * var5);
      }
   }

   private SpotifyLyricCache H(long var1) {
      SpotifyCurve.ensureSourceClassInitialized();
      long var3 = 7833468306678L;
      int var6 = var3 < 0L ? -1 : 1;
      if (this.F == null) {
         String var6x = FabricLoader.getInstance().getModContainer("purxiy").map(var0 -> var0.getMetadata().getVersion().getFriendlyString()).orElse("dev");
         Path var7 = FabricLoader.getInstance().getGameDir().resolve("purxiy").resolve("cache").resolve("lyrics");
         this.F = new SpotifyLyricCache(
            "Purxiy/" + var6x + " (Minecraft mod; Spotify Player lyrics)", var7, var0 -> ClientServices.LOGGER.warn("Spotify Player: {}", var0)
         );
      }

      return this.F;
   }

   public String problem() {
      return switch (SpotifyRegistry.TrackCache.A[SpotifyFactory.get().health().ordinal()]) {
         case 1 -> "The Spotify Player works on Windows 10 and 11";
         case 2 -> "Windows' media controls are not available";
         default -> null;
      };
   }

   public static void install(SpotifyModule var0, ModuleContext var1) {
      y = var0;
      p = var1;
   }

   public static void metric(String var0) {
      if (p != null) {
         p.metric(var0, var0.equals("drawn") ? ++U : ++B);
      }
   }

   public static void uninstall() {
      long var0 = 40200600885904L;
      long var2 = var0 ^ 123212638718347L;
      get().a(var2);
      y = null;
      p = null;
   }

   private void a(long var1) {
      SpotifyCurve.ensureSourceClassInitialized();
      long var3 = 92909858263834L;
      int var6 = var3 < 0L ? -1 : 1;
      SpotifyFactory.get().closeOwned();
      long var6x = 92909858263834L;
      int var18 = var6x < 0L ? -1 : 1;
      if (this.F != null) {
         this.F.closeOwned();
         this.F = null;
      }

      SpotifyTrack.stopDecoder();

      for (SpotifyCurve var11 : new SpotifyCurve[]{this.K, this.g}) {
         long var12 = 92909858263834L;
         int var14 = var12 < 0L ? -1 : 1;
         long var14x = 92909858263835L;
         int var16 = var14x < 0L ? -1 : 1;
         long var16x = 92909858263834L;
         int var17 = var16x < 0L ? -1 : 1;
         if (var11.Y != null) {
            var11.Y.closeOwned();
         }
      }

      long var18x = 92909858263834L;
      int var21 = var18x < 0L ? -1 : 1;
      long var19 = 92909858263834L;
      int var23 = var19 < 0L ? -1 : 1;
      if (this.I != null) {
         this.I.cancel(true);
      }

      this.I = null;
      this.H.clear();
      SpotifyOverlay.closeOwned();
      SpotifyPipeline.closeOwned();
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ -1934196911 ^ 9967) & 32767;
      if (q[var3] == null) {
         q[var3] = l[var3] ^ 2237095544536596305L;
      }

      return q[var3];
   }

   static class TrackCache {
      static final int[] A = new int[SpotifyFactory.TrackSource.values().length];

      static {
         try {
            A[SpotifyFactory.TrackSource.E.ordinal()] = 1;
         } catch (NoSuchFieldError var2) {
         }

         try {
            A[SpotifyFactory.TrackSource.u.ordinal()] = 2;
         } catch (NoSuchFieldError var1) {
         }
      }
   }
}
