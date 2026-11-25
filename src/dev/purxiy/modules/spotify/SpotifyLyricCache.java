package dev.purxiy.modules.spotify;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.OfflineNetwork;
import dev.purxiy.loader.fabric.client.ByteArrayBodySubscriber;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HexFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public final class SpotifyLyricCache {
   private static final int q = 4;
   private static final long A = Duration.ofHours(6L).toMillis();
   private static final long l = Duration.ofDays(1L).toMillis();
   private static final Duration t = Duration.ofSeconds(5L);
   private static final long x = Duration.ofHours(1L).toMillis();
   private static final long[] U = new long[]{600L, 1500L};
   private static final long Q = 2500L;
   private static final long m = Duration.ofSeconds(8L).toMillis();
   private static final long J = Duration.ofMinutes(1L).toMillis();
   private static final int K = 600;
   private static final Pattern d = Pattern.compile(
      "(?i)\\s+[-–—]\\s+(?:\\d{4}\\s+)?(?:remaster|re-?master|live|radio|single|album|edit|mono|stereo|version|acoustic|from|feat|with|bonus|deluxe|original|extended|re-?recorded|sped|slowed|instrumental|demo|mix|remix|bonus).*$"
   );
   private static final Pattern p = Pattern.compile("(?i)\\s*[(\\[](?:feat\\.?|ft\\.?|featuring|with|prod\\.?)\\s[^)\\]]*[)\\]]");
   private static final Pattern Y = Pattern.compile(
      "(?i)\\s*[(\\[](?:\\d{4}\\s+)?(?:re-?master(?:ed)?|remaster(?:ed)?|radio edit|single version|album version|mono|stereo)(?:\\s[^)\\]]*)?[)\\]]"
   );
   static final double P = 4.0;
   private static final Pattern W = Pattern.compile("(?i)\\s*(?:,|;|&|/|\\sfeat\\.?\\s|\\sft\\.?\\s|\\sx\\s|\\sand\\s|\\sen\\s)\\s*");
   private final SpotifyLyricCache.Listener y;
   private final long[] B;
   private final String f;
   private final Path V;
   private final Consumer<String> e;
   private final ExecutorService I = ClientServices.visualExecutor("SourceByDevFFnightmare DeObfDeCompByGuideX Lyrics");
   private final AtomicLong i = new AtomicLong();
   private final Map<String, Long> u = new LinkedHashMap<>();
   private final Map<String, Long> M = new LinkedHashMap<>();
   private final Map<String, SpotifyLyricCache.CachedLyrics> X = new LinkedHashMap<String, SpotifyLyricCache.CachedLyrics>(64, 0.75F, true) {
      @Override
      protected boolean removeEldestEntry(Entry<String, SpotifyLyricCache.CachedLyrics> var1) {
         return this.size() > 48;
      }
   };
   private static final String[] b = new String[55];
   private static final long[] c = new long[]{
      6526872159723732398L,
      4477075256012429493L,
      3624420801700728393L,
      3352918823077677792L,
      -3419212928350848697L,
      -6697868590632212776L,
      -7903181358341481853L,
      -6070864815452135371L,
      -6931700990699166106L,
      -3944279361856441333L,
      6181332011033426356L,
      -642541923837531171L,
      3065821216926011822L
   };
   private static final Integer[] g = new Integer[13];
   private static final long[] h = new long[]{
      133329526067535662L,
      2496917912203467900L,
      5540250118507507266L,
      695268524345713288L,
      5281362783984083960L,
      8950226442489274827L,
      3829680989054925454L,
      6520842612988196757L,
      4865957995623371105L,
      -8583579125277011555L,
      2063411280211890411L,
      8946137463567075701L,
      4549951648405456868L
   };
   private static final Long[] j = new Long[13];

   public SpotifyLyricCache(String var1, Path var2, Consumer<String> var3) {
      this(var1, var2, var3, P(83009538631468L));
      long var4 = 89698792472541L;
      long var6 = var4 ^ 29598947039473L;
   }

   SpotifyLyricCache(String var1, Path var2, Consumer<String> var3, SpotifyLyricCache.Listener var4) {
      this(var1, var2, var3, var4, U);
   }

   SpotifyLyricCache(String var1, Path var2, Consumer<String> var3, SpotifyLyricCache.Listener var4, long[] var5) {
      this.f = var1;
      this.V = var2;
      this.e = var3;
      this.y = var4;
      this.B = (long[])var5.clone();
   }

   private static SpotifyLyricCache.Listener P(long var0) {
      long var2 = 44445443859825L;
      return new SpotifyLyricCache.LyricWorker(var2);
   }

   public synchronized SpotifyLyrics cached(SpotifyLyricCache.LyricQuery var1) {
      SpotifyLyricCache.CachedLyrics var2 = this.X.get(var1.key());
      if (var2 != null && var2.expiresAt() > System.currentTimeMillis()) {
         return var2.upgrade() ? null : var2.lyrics();
      } else {
         this.X.remove(var1.key());
         return null;
      }
   }

   public synchronized boolean wordsPending(SpotifyLyricCache.LyricQuery var1) {
      SpotifyLyricCache.CachedLyrics var2 = this.X.get(var1.key());
      return var2 != null && var2.upgrade();
   }

   public CompletableFuture<SpotifyLyrics> lookup(SpotifyLyricCache.LyricQuery var1) {
      long var2 = this.i.incrementAndGet();
      SpotifyLyrics var4 = this.cached(var1);
      if (var4 != null) {
         return CompletableFuture.completedFuture(var4);
      } else {
         CompletableFuture var5 = new CompletableFuture();
         CompletableFuture.runAsync(() -> {
            long var5x = 4155607285810L;
            long var7 = 84541932989164L;
            short var9 = 19683;
            short var10 = 243;
            int var11 = (int)(var7 << 40 >>> 40);

            try {
               SpotifyLyricCache.CachedLyrics var12 = this.c(var5x, var1, var2);
               synchronized (this) {
                  this.z(var9, (byte)var10, var11, var2);
                  this.X.put(var1.key(), var12);
               }

               var5.complete(var12.lyrics());
            } catch (SpotifyLyricCache.LyricCacheException var16) {
               var5.cancel(false);
            } catch (Throwable var171) {
               var5.completeExceptionally(var171);
            }
         }, this.I);
         return var5;
      }
   }

   private void z(int var1, byte var2, int var3, long var4) {
      if (var4 != this.i.get()) {
         throw new SpotifyLyricCache.LyricCacheException();
      }
   }

   private SpotifyLyricCache.CachedLyrics c(long var1, SpotifyLyricCache.LyricQuery var3, long var4) throws SpotifyLyricCache.LyricLookupException {
      long var6 = 74687208981327L;
      long var8 = 139986552115157L;
      long var10 = 126962524473503L;
      long var12 = 9926653561393L;
      long var14 = 84541932989164L;
      short var16 = 19683;
      short var17 = 243;
      int var18 = (int)(var14 << 40 >>> 40);
      short var19 = 31025;
      long var20 = 3228119071L;
      SpotifyCurve.ensureSourceClassInitialized();
      this.z(var16, (byte)var17, var18, var4);
      SpotifyLyricCache.CachedLyrics var22 = this.V(var3, var10);
      if (var22 != null && !var22.upgrade()) {
         return var22;
      } else if (var22 != null) {
         SpotifyLyrics var31 = this.m(var3, var6, var22.lyrics(), var4);
         this.z(var16, (byte)var17, var18, var4);
         if (var31 != null) {
            SpotifyLyricCache.CachedLyrics var32 = new SpotifyLyricCache.CachedLyrics(var31.synced() ? var31 : var22.lyrics(), var22.expiresAt());
            this.t(var3, var32, var8);
            return var32;
         } else {
            return var22;
         }
      } else {
         SpotifyLyricCache.LyricResult var23 = this.s(var3, var4, var12);
         this.z(var16, (byte)var17, var18, var4);
         long var24 = 4155607285810L;
         int var27 = var24 < 0L ? -1 : 1;
         if (!var23.complete()) {
            throw new SpotifyLyricCache.LyricLookupException("Some lyrics sources could not be checked; retry later", var23.lyrics());
         } else {
            long var27x = 4155607285810L;
            int var29 = var27x < 0L ? -1 : 1;
            boolean var29x = var23.lyrics().synced() && !var23.lyrics().wordTimed() && !var23.wordsAsked();
            SpotifyLyricCache.CachedLyrics var30 = new SpotifyLyricCache.CachedLyrics(var23.lyrics(), t(var23.lyrics(), System.currentTimeMillis(), var19, var20), var29x);
            this.t(var3, var30, var8);
            return var30;
         }
      }
   }

   private SpotifyLyrics m(SpotifyLyricCache.LyricQuery var1, long var2, SpotifyLyrics var4, long var5) {
      long var7 = 68147456482625L;
      byte var9 = 0;
      int var10 = 4061904;
      int var11 = (int)(var7 << 40 >>> 40);
      long var12 = 65264779382936L;
      long var14 = 69269020145378L;
      SpotifyCurve.ensureSourceClassInitialized();
      boolean var16 = true;

      for (SpotifyCodec.Data var18 : SpotifyCodec.sources(var1)) {
         long var19 = 74687208981326L;
         int var22 = var19 < 0L ? -1 : 1;
         boolean var22x = var18.words();
         long var23 = 74687208981326L;
         int var30 = var23 < 0L ? -1 : 1;
         if (!var22x) {
            long var26 = 74687208981327L;
            int var26x = var26 < 0L ? -1 : 1;
         } else {
            try {
               JsonElement var32 = this.A(this.J(var18.url(), var5, var12), var5, var14);
               SpotifyLyrics var27x = var18.parse().apply(var32);
               if (var27x.synced() && var27x.wordTimed()) {
                  return var27x;
               }

               SpotifyLyrics var28x = M(var18, var32, var4, var9, var10, var11);
               if (var28x != null) {
                  return var28x;
               }
            } catch (SpotifyLyricCache.LyricCacheException var271) {
               throw var271;
            } catch (SpotifyLyricCache.LyricLookupException | RuntimeException var281) {
               var16 = false;
            }
         }
      }

      long var31 = 74687208981327L;
      int var301 = var31 < 0L ? -1 : 1;
      return var16 ? SpotifyLyrics.f : null;
   }

   private SpotifyLyricCache.LyricResult s(SpotifyLyricCache.LyricQuery var1, long var2, long var4) throws SpotifyLyricCache.LyricLookupException {
      long var6 = 68147456482625L;
      byte var8 = 0;
      int var9 = 4061904;
      int var10 = (int)(var6 << 40 >>> 40);
      long var11 = 65264779382936L;
      long var13 = 88377796854512L;
      long var15 = 69269020145378L;
      long var17 = 74758817810697L;
      var6 = 84541932989164L;
      short var19 = 19683;
      short var20 = 243;
      int var21 = (int)(var6 << 40 >>> 40);
      SpotifyCurve.ensureSourceClassInitialized();
      if (!var1.title().isBlank() && !var1.artist().isBlank()) {
         List<SpotifyCodec.Data>var22 = SpotifyCodec.sources(var1);
         SpotifyLyrics var23 = SpotifyLyrics.f;
         boolean var24 = true;
         ArrayList var25 = new ArrayList();
         ArrayList var26 = new ArrayList();

         try {
            for (SpotifyCodec.Data var28 : var22) {
               long var29 = 9926653561393L;
               int var74 = var29 < 0L ? -1 : 1;
               long var32 = 9926653561392L;
               int var34 = var32 < 0L ? -1 : 1;
               boolean var35 = var28.first();
               long var36 = 9926653561393L;
               int var36x = var36 < 0L ? -1 : 1;
               if (var35) {
                  var25.add(var28);
                  var26.add(this.J(var28.url(), var2, var11));
               }
            }
         } catch (SpotifyLyricCache.LyricCacheException var67) {
            var26.forEach(v -> ((SpotifyLyricCache.LyricFetch)v).cancel());
            throw var67;
         }

         long var73 = 9926653561393L;
         int var70 = var73 < 0L ? -1 : 1;
         ArrayList<SpotifyLyrics>var30 = new ArrayList();
         ArrayList var31 = new ArrayList();
         boolean var74 = true;
         boolean var33 = false;
         boolean var34 = false;

         for (int var75 = 0; var75 < var25.size(); var75++) {
            SpotifyCodec.Data var77 = (SpotifyCodec.Data)var25.get(var75);

            try {
               JsonElement var37 = this.A((SpotifyLyricCache.LyricFetch)var26.get(var75), var2, var15);
               SpotifyLyrics var38 = var77.parse().apply(var37);
               var31.add(var37);
               long var39 = 9926653561393L;
               int var85 = var39 < 0L ? -1 : 1;
               var30.add(var38);
               var33 |= var38.synced() && var38.wordTimed();
               var34 |= var77.words() && var37 != null;
            } catch (SpotifyLyricCache.LyricCacheException var65) {
               var26.forEach(v -> ((SpotifyLyricCache.LyricFetch)v).cancel());
               throw var65;
            } catch (SpotifyLyricCache.LyricLookupException | RuntimeException var661) {
               var31.add(null);
               var30.add(null);
               var24 = false;
               var74 &= !var77.words();
               this.Z(var13, var77, var661);
            }
         }

         long var76 = 9926653561393L;
         int var791 = var76 < 0L ? -1 : 1;

         for (SpotifyCodec.Data var80 : var22) {
            long var81 = 9926653561392L;
            int var871 = var81 < 0L ? -1 : 1;
            if (!var33 && !var34 && !var80.first()) {
               if (!var80.words()) {
                  long var86 = 9926653561393L;
                  int var95 = var86 < 0L ? -1 : 1;
               } else {
                  var25.add(var80);

                  try {
                     JsonElement var42 = this.A(this.J(var80.url(), var2, var11), var2, var15);
                     SpotifyLyrics var43 = var80.parse().apply(var42);
                     var31.add(var42);
                     long var44 = 9926653561393L;
                     int var44x = var44 < 0L ? -1 : 1;
                     var30.add(var43);
                     long var46 = 9926653561393L;
                     int var46x = var46 < 0L ? -1 : 1;
                     var33 = var43.synced() && var43.wordTimed();
                  } catch (SpotifyLyricCache.LyricCacheException var63) {
                     throw var63;
                  } catch (SpotifyLyricCache.LyricLookupException | RuntimeException var64) {
                     var31.add(null);
                     var30.add(null);
                     var24 = false;
                     var74 = false;
                     this.Z(var13, var80, var64);
                  }
               }
            }
         }

         long var79x = 9926653561393L;
         int var84 = var79x < 0L ? -1 : 1;

         for (SpotifyLyrics var40 : var30) {
            long var41 = 9926653561393L;
            int var96 = var41 < 0L ? -1 : 1;
            if (var40 != null) {
               boolean var89 = var40.synced();
               long var45 = 9926653561393L;
               int var45x = var45 < 0L ? -1 : 1;
               if (var89) {
                  var89 = var40.wordTimed();
                  long var48 = 9926653561393L;
                  int var104 = var48 < 0L ? -1 : 1;
                  if (var89) {
                     return new SpotifyLyricCache.LyricResult(var40, true);
                  }
               }
            }
         }

         long var83 = 9926653561393L;
         int var91 = var83 < 0L ? -1 : 1;

         for (int var84x = 0; var84x < var25.size(); var84x++) {
            long var87 = 9926653561392L;
            int var1011 = var87 < 0L ? -1 : 1;
            SpotifyLyrics var93 = (SpotifyLyrics)var30.get(var84x);
            long var94 = 9926653561393L;
            int var1151 = var94 < 0L ? -1 : 1;
            long var49 = 9926653561393L;
            int var107 = var49 < 0L ? -1 : 1;
            if (var93 != null) {
               boolean var52 = ((SpotifyCodec.Data)var25.get(var84x)).exact();
               long var53 = 9926653561393L;
               int var113 = var53 < 0L ? -1 : 1;
               if (var52 && var93.synced()) {
                  for (int var56 = 0; var56 < var25.size(); var56++) {
                     SpotifyLyrics var57 = M((SpotifyCodec.Data)var25.get(var56), (JsonElement)var31.get(var56), var93, var8, var9, var10);
                     long var58 = 9926653561392L;
                     int var59 = var58 < 0L ? -1 : 1;
                     long var61 = 9926653561392L;
                     int var57x = var61 < 0L ? -1 : 1;
                     if (var57 != null) {
                        return new SpotifyLyricCache.LyricResult(var57, true);
                     }
                  }
               }
            }
         }

         long var85 = 9926653561393L;
         int var99 = var85 < 0L ? -1 : 1;
         Iterator var88 = var30.iterator();

         while (var88.hasNext()) {
            long var91x = 9926653561392L;
            int var108 = var91x < 0L ? -1 : 1;
            SpotifyLyrics var47 = (SpotifyLyrics)var88.next();
            long var102 = 9926653561393L;
            int var125 = var102 < 0L ? -1 : 1;
            if (var47 != null) {
               boolean var51 = var47.synced();
               long var112 = 9926653561392L;
               int var138 = var112 < 0L ? -1 : 1;
               if (var51) {
                  var51 = q(var47, var17);
                  long var55 = 9926653561393L;
                  int var147 = var55 < 0L ? -1 : 1;
                  if (var51) {
                     return new SpotifyLyricCache.LyricResult(var47, true, var74);
                  }
               }
            }
         }

         long var92 = 9926653561392L;
         int var110 = var92 < 0L ? -1 : 1;

         for (boolean var105 : new boolean[]{true, false}) {
            for (int var50 = 0; var50 < var25.size(); var50++) {
               long var109 = 9926653561393L;
               int var144 = var109 < 0L ? -1 : 1;
               SpotifyLyrics var54 = (SpotifyLyrics)var30.get(var50);
               long var114 = 9926653561393L;
               int var151 = var114 < 0L ? -1 : 1;
               long var116 = 9926653561393L;
               int var154 = var116 < 0L ? -1 : 1;
               if (var54 != null) {
                  boolean var118 = var54.synced();
                  long var62 = 9926653561392L;
                  int var601 = var62 < 0L ? -1 : 1;
                  if (var118 && (!var105 || ((SpotifyCodec.Data)var25.get(var50)).exact())) {
                     return new SpotifyLyricCache.LyricResult(var54, true, var74);
                  }
               }
            }

            long var106 = 9926653561392L;
            int var140 = var106 < 0L ? -1 : 1;
         }

         for (int var96 = 0; var96 < var25.size(); var96++) {
            long var100 = 9926653561392L;
            int var129 = var100 < 0L ? -1 : 1;
            SpotifyLyrics var107 = (SpotifyLyrics)var30.get(var96);
            if (var107 != null && ((SpotifyCodec.Data)var25.get(var96)).exact()) {
               long var110x = 9926653561393L;
               int var141 = var110x < 0L ? -1 : 1;
               if (var107.kind() == SpotifyLyrics.LyricStyle.I) {
                  return new SpotifyLyricCache.LyricResult(var107, true);
               }
            }

            if (var107 != null && var23.kind() == SpotifyLyrics.LyricStyle.H && var107.kind() == SpotifyLyrics.LyricStyle.g) {
               var23 = var107;
            }
         }

         for (SpotifyCodec.Data var101 : var22) {
            long var104 = 9926653561392L;
            int var135 = var104 < 0L ? -1 : 1;
            long var111 = 9926653561393L;
            int var142 = var111 < 0L ? -1 : 1;
            boolean var113 = var25.contains(var101);
            long var115 = 9926653561393L;
            int var66 = var115 < 0L ? -1 : 1;
            if (!var113) {
               this.z(var19, (byte)var20, var21, var2);

               try {
                  SpotifyLyrics var117 = var101.parse().apply(this.A(this.J(var101.url(), var2, var11), var2, var15));
                  if (var117.synced()) {
                     return new SpotifyLyricCache.LyricResult(var117, true, var74);
                  }

                  if (var101.exact()) {
                     SpotifyLyrics.LyricStyle var59 = var117.kind();
                     long var60 = 9926653561393L;
                     int var156 = var60 < 0L ? -1 : 1;
                     if (var59 == SpotifyLyrics.LyricStyle.I) {
                        return new SpotifyLyricCache.LyricResult(var117, true, var74);
                     }
                  }

                  if (var23.kind() == SpotifyLyrics.LyricStyle.H && var117.kind() == SpotifyLyrics.LyricStyle.g) {
                     var23 = var117;
                  }
               } catch (SpotifyLyricCache.LyricCacheException var61) {
                  throw var61;
               } catch (SpotifyLyricCache.LyricLookupException | RuntimeException var621) {
                  var24 = false;
                  this.Z(var13, var101, var621);
               }
            }
         }

         long var98 = 9926653561393L;
         int var123 = var98 < 0L ? -1 : 1;
         if (var23.kind() == SpotifyLyrics.LyricStyle.H && !var24) {
            throw new SpotifyLyricCache.LyricLookupException("Some lyrics sources could not be checked; retry later");
         } else {
            return new SpotifyLyricCache.LyricResult(var23, var24);
         }
      } else {
         return new SpotifyLyricCache.LyricResult(SpotifyLyrics.f, true);
      }
   }

   private static boolean q(SpotifyLyrics var0, long var1) {
      int var3 = 0;
      SpotifyCurve.ensureSourceClassInitialized();
      byte var4 = 0;

      for (SpotifyLyrics.Data var6 : var0.lines()) {
         long var7 = 74758817810696L;
         int var16 = var7 < 0L ? -1 : 1;
         long var10 = 74758817810696L;
         int var12 = var10 < 0L ? -1 : 1;
         boolean var12x = var6.isBreak();
         long var13 = 74758817810697L;
         int var14 = var13 < 0L ? -1 : 1;
         if (!var12x) {
            var3++;
            var4 = (byte)(var4 + (var6.hasEnd() ? 1 : 0));
         }
      }

      long var15 = 74758817810696L;
      int var161 = var15 < 0L ? -1 : 1;
      long var9 = 74758817810696L;
      int var19 = var9 < 0L ? -1 : 1;
      if (var3 > 0) {
         long var16x = 74758817810697L;
         int var22 = var16x < 0L ? -1 : 1;
         if (var4 * 2 >= var3) {
            return true;
         }
      }

      return false;
   }

   private static SpotifyLyrics M(SpotifyCodec.Data var0, JsonElement var1, SpotifyLyrics var2, byte var3, int var4, int var5) {
      SpotifyCurve.ensureSourceClassInitialized();
      if (var0.otherRecording() != null && var1 != null) {
         boolean var6 = false;
         if (var2.synced() && !var2.wordTimed()) {
            try {
               SpotifyLyrics var7 = var0.otherRecording().apply(var1);
               var6 = false;
               if (var7.synced() && var7.wordTimed()) {
                  SpotifyLyrics var8 = SpotifyAuth.wordsOnto(var2, var7);
                  var6 = false;
                  return var8 != null && var8.wordTimed() ? var8 : null;
               }

               return null;
            } catch (RuntimeException var91) {
               return null;
            }
         }
      }

      return null;
   }

   private void Z(long var1, SpotifyCodec.Data var3, Exception var4) {
      if (this.e != null && var4 instanceof RuntimeException) {
         this.e.accept("Lyrics source " + var3.name() + " unavailable (" + var4.getClass().getSimpleName() + "); trying next source");
      }
   }

   private SpotifyLyricCache.LyricFetch J(String var1, long var2, long var4) {
      long var6 = var4 ^ 131633501944436L;
      int var8 = (int)((var4 ^ 131633501944436L) >>> 32);
      int var9 = (int)((var4 ^ 131633501944436L) << 32 >>> 56);
      int var10 = (int)(var6 << 40 >>> 40);
      this.z(var8, (byte)var9, var10, var2);
      URI var11 = URI.create(var1);
      SpotifyCurve.ensureSourceClassInitialized();
      String var12 = var11.getHost();
      long var13 = System.currentTimeMillis();
      if (this.M.getOrDefault(var12, 0L) > var13) {
         return new SpotifyLyricCache.LyricFetch(var1, var12, System.nanoTime(), null, null);
      } else if (this.u.getOrDefault(var12, 0L) > var13) {
         return new SpotifyLyricCache.LyricFetch(var1, var12, System.nanoTime(), null, new SpotifyLyricCache.LyricLookupException("Source is cooling down"));
      } else {
         try {
            HttpRequest var15 = HttpRequest.newBuilder(var11).timeout(t).header("User-Agent", this.f).header("Accept", "application/json").GET().build();
            return new SpotifyLyricCache.LyricFetch(var1, var12, System.nanoTime(), this.y.send(var15), null);
         } catch (RuntimeException var161) {
            this.u.put(var12, var13 + m);
            return new SpotifyLyricCache.LyricFetch(var1, var12, System.nanoTime(), null, new SpotifyLyricCache.LyricLookupException(var161.getClass().getSimpleName()));
         }
      }
   }

   private JsonElement A(SpotifyLyricCache.LyricFetch var1, long var2, long var4) throws SpotifyLyricCache.LyricLookupException {
      int var10 = (int)((var4 ^ 67824948338399L) >>> 32);
      int var11 = (int)((var4 ^ 67824948338399L) << 32 >>> 32);
      long var12 = var4 ^ 6203286169210L;
      long var14 = var4 ^ 0L;
      long var16 = var4 ^ 135961909079408L;
      long var18 = var4 ^ 94556710650295L;
      SpotifyCurve.ensureSourceClassInitialized();
      byte var20 = 0;
      SpotifyLyricCache.LyricLookupException var21 = var1.refused();
      if (var4 > 0L) {
         if (var21 == null) {
            if (var1.future() == null) {
               return null;
            }

            String var22 = var1.host();
            int var23 = 0;

            while (true) {
               CompletableFuture var24 = var1.future();
               long var25 = b(17166, 8583510435012991104L ^ var4);
               boolean var27 = false;

               JsonElement var29;
               label720: {
                  JsonElement var52;
                  label721: {
                     label722: {
                        int var6;
                        String var30;
                        int var31;
                        label723: {
                           label705: {
                              int var7;
                              label724: {
                                 label703: {
                                    label725: {
                                       try {
                                          int var9;
                                          SpotifyLyricCache.Data var32;
                                          int var64;
                                          label727: {
                                             label635: {
                                                var27 = true;
                                                var32 = this.c(var10, var1, var11, var2);
                                                var64 = var32.status();
                                                if (var64 != a(2665, 3186696555103963864L ^ var4)) {
                                                   var9 = var64;
                                                   var31 = var20;
                                                   if (var4 < 1L) {
                                                      break label635;
                                                   }

                                                   if (var64 != a(14548, 269946386080757863L ^ var4)) {
                                                      var9 = var64;
                                                      var31 = var20;
                                                      break label635;
                                                   }
                                                }

                                                var52 = null;
                                                var27 = false;
                                                break label722;
                                             }

                                             label728: {
                                                if (var4 >= 1L) {
                                                   if (var9 == a(20092, 8035897516982734535L ^ var4)) {
                                                      break label728;
                                                   }

                                                   var9 = var64;
                                                   var31 = var20;
                                                }

                                                if (var4 < 0L) {
                                                   break label727;
                                                }

                                                if (var9 != a(11589, 3248387242997575154L ^ var4)) {
                                                   var9 = var64;
                                                   var31 = var20;
                                                   break label727;
                                                }
                                             }

                                             SpotifyLyricCache var65 = this;
                                             if (var4 >= 1L) {
                                                this.M.put(var22, System.currentTimeMillis() + x);
                                                var65 = this;
                                             }

                                             if (var65.e != null) {
                                                this.e.accept("Lyrics source " + var22 + " refused the request (HTTP " + var64 + "); skipping it for an hour");
                                             }

                                             var52 = null;
                                             var27 = false;
                                             break label721;
                                          }

                                          label664: {
                                             label663: {
                                                label729: {
                                                   int var59;
                                                   label730: {
                                                      if (var4 > 0L) {
                                                         var59 = 7501;
                                                         if (var4 < 0L) {
                                                            break label730;
                                                         }

                                                         var31 = a(7501, 989506214176546295L ^ var4);
                                                      }

                                                      if (var9 < var31) {
                                                         break label729;
                                                      }

                                                      var9 = var64;
                                                      var59 = var20;
                                                   }

                                                   if (var4 <= 0L) {
                                                      break label664;
                                                   }

                                                   if (var59 != 0) {
                                                      break label663;
                                                   }

                                                   if (var9 < a(4369, 7958106249318486440L ^ var4)) {
                                                      var52 = JsonParser.parseString(var32.body());
                                                      if (!var52.isJsonObject() && !var52.isJsonArray()) {
                                                         throw new SpotifyLyricCache.LyricLookupException("Invalid JSON response");
                                                      }

                                                      var29 = var52;
                                                      var27 = false;
                                                      break label720;
                                                   }
                                                }

                                                var9 = var64;
                                             }

                                             var31 = 212407430;
                                          }

                                          var30 = "HTTP " + var9;
                                          var7 = var64;
                                          if (var4 >= 0L) {
                                             if (var64 == a(20157, 7822830570444918280L ^ var4)) {
                                                long var34 = b(var32.retryAfter(), var16);
                                                var25 = var34 > 0L ? var34 : J;
                                                var27 = false;
                                                break label703;
                                             }

                                             var7 = var64;
                                          }

                                          if (var4 <= 1L) {
                                             var27 = false;
                                             break label724;
                                          }

                                          if (var7 >= a(20858, 4627682654660596174L ^ var4)) {
                                             var25 = b(var32.retryAfter(), var16);
                                             var27 = false;
                                          } else {
                                             var27 = false;
                                          }
                                          break label703;
                                       } catch (SpotifyLyricCache.LyricCacheException var45) {
                                          throw var45;
                                       } catch (InterruptedException var46) {
                                          Thread.currentThread().interrupt();
                                          throw new SpotifyLyricCache.LyricLookupException("Interrupted");
                                       } catch (ExecutionException var47) {
                                          Object var33 = var47;
                                          if (var4 > 0L) {
                                             var33 = var47.getCause();
                                          }

                                          String var8;
                                          label685: {
                                             if (var4 > 1L) {
                                                if (var33 == null) {
                                                   var8 = var47.getClass().getSimpleName();
                                                   break label685;
                                                }

                                                var33 = var47.getCause();
                                             }

                                             var8 = var33.getClass().getSimpleName();
                                          }

                                          var30 = var8;
                                          if (var4 > 1L) {
                                             var25 = 0L;
                                             if (var4 <= 0L) {
                                                var27 = false;
                                                break label705;
                                             }

                                             var27 = false;
                                          } else {
                                             var27 = false;
                                          }
                                          break label725;
                                       } catch (SpotifyLyricCache.LyricLookupException | RuntimeException var48) {
                                          var30 = var48 instanceof SpotifyLyricCache.LyricLookupException ? var48.getMessage() : var48.getClass().getSimpleName();
                                          var27 = false;
                                       } finally {
                                          label602:
                                          if (var27) {
                                             CompletableFuture var39 = var24;
                                             byte var40 = var20;
                                             if (var4 > 1L) {
                                                if (var24.isDone()) {
                                                   break label602;
                                                }

                                                var39 = var24;
                                                var40 = 1;
                                             }

                                             var39.cancel(var40 != 0);
                                          }
                                       }

                                       var6 = var24.isDone() ? 1 : 0;
                                       var31 = var20;
                                       if (var4 < 0L) {
                                          break label723;
                                       }

                                       if (var6 == 0) {
                                          var24.cancel(true);
                                       }
                                       break label705;
                                    }

                                    if (!var24.isDone()) {
                                       var24.cancel(true);
                                    }
                                    break label705;
                                 }

                                 var7 = var24.isDone() ? 1 : 0;
                              }

                              if (var7 == 0) {
                                 var24.cancel(true);
                              }
                           }

                           long var61;
                           var6 = (var61 = var25 - 0L) == 0L ? 0 : (var61 < 0L ? -1 : 1);
                           var31 = var20;
                        }

                        label733: {
                           if (var4 >= 0L) {
                              if (var6 < 0) {
                                 break label733;
                              }

                              var6 = var23;
                              var31 = var20;
                           }

                           if (var4 >= 1L) {
                              var31 = this.B.length;
                           }

                           if (var6 < var31 && var25 <= b(1578, 695289328920960430L ^ var4)) {
                              SpotifyLyricCache var62 = this;
                              if (var4 >= 0L) {
                                 this.E(Math.max(var25, this.B[var23]), var2, var18);
                                 var62 = this;
                              }

                              if (var4 > 1L) {
                                 var1 = var62.J(var1.url(), var2, var12);
                                 if (var1.future() != null) {
                                    var23++;
                                    continue;
                                 }

                                 var62 = this;
                              }

                              return var62.A(var1, var2, var14);
                           }
                        }

                        SpotifyLyricCache var63 = this;
                        if (var4 > 1L) {
                           this.u.put(var22, System.currentTimeMillis() + Math.min(J, Math.max(m, var25)));
                           var63 = this;
                        }

                        if (var63.e != null) {
                           this.e.accept("Lyrics source " + var22 + " unavailable (" + var30 + "); trying next source");
                        }

                        throw new SpotifyLyricCache.LyricLookupException(var30);
                     }

                     CompletableFuture var54 = var24;
                     byte var58 = var20;
                     if (var4 >= 1L) {
                        if (var24.isDone()) {
                           return var52;
                        }

                        var54 = var24;
                        var58 = 1;
                     }

                     var54.cancel(var58 != 0);
                     return var52;
                  }

                  CompletableFuture var53 = var24;
                  byte var56 = var20;
                  if (var4 >= 1L) {
                     if (var24.isDone()) {
                        return var52;
                     }

                     var53 = var24;
                     var56 = 1;
                  }

                  var53.cancel(var56 != 0);
                  return var52;
               }

               CompletableFuture var55 = var24;
               byte var60 = var20;
               if (var4 >= 0L) {
                  if (var24.isDone()) {
                     return var29;
                  }

                  var55 = var24;
                  var60 = 1;
               }

               var55.cancel(var60 != 0);
               return var29;
            }
         }

         var21 = var1.refused();
      }

      throw var21;
   }

   private SpotifyLyricCache.Data c(int var1, SpotifyLyricCache.LyricFetch var2, int var3, long var4) throws SpotifyLyricCache.LyricLookupException, InterruptedException, ExecutionException {
      long var6 = (long)var1 << 32 | (long)var3 << 32 >>> 32;
      long var8 = var6 ^ 87633326195409L;
      int var10 = (int)((var6 ^ 87633326195409L) >>> 32);
      int var11 = (int)((var6 ^ 87633326195409L) << 32 >>> 56);
      int var12 = (int)(var8 << 40 >>> 40);
      CompletableFuture var13 = var2.future();
      long var14 = var2.sentAt() + t.toNanos();

      while (true) {
         this.z(var10, (byte)var11, var12, var4);

         try {
            SpotifyLyricCache.Data var16 = (SpotifyLyricCache.Data)var13.get(b(19663, 8950223486568401298L ^ var6), TimeUnit.MILLISECONDS);
            this.z(var10, (byte)var11, var12, var4);
            return var16;
         } catch (TimeoutException var171) {
            if (System.nanoTime() >= var14) {
               throw new SpotifyLyricCache.LyricLookupException("Request timed out");
            }
         }
      }
   }

   private void E(long var1, long var3, long var5) throws SpotifyLyricCache.LyricLookupException {
      long var7 = var5 ^ 43858923806137L;
      int var9 = (int)((var5 ^ 43858923806137L) >>> 32);
      int var10 = (int)((var5 ^ 43858923806137L) << 32 >>> 56);
      int var11 = (int)(var7 << 40 >>> 40);
      SpotifyCurve.ensureSourceClassInitialized();
      long var12 = System.nanoTime() + var1 * b(19673, 5281385872651523821L ^ var5);

      do {
         try {
            while (true) {
               this.z(var9, (byte)var10, var11, var3);
               long var14 = (var12 - System.nanoTime()) / b(32332, 4865910717702055028L ^ var5);
               if (var14 <= 0L) {
                  break;
               }

               Thread.sleep(Math.min(b(28970, 2496892624510790427L ^ var5), var14));
            }
         } catch (InterruptedException var161) {
            Thread.currentThread().interrupt();
            throw new SpotifyLyricCache.LyricLookupException("Interrupted");
         }
      } while (var5 <= 0L);
   }

   private static long b(String var0, long var1) {
      SpotifyCurve.ensureSourceClassInitialized();
      if (var0 != null && !var0.isBlank()) {
         try {
            return Math.max(0L, Math.min(b(19765, 5540176819957365696L ^ var1), Long.parseLong(var0.strip()))) * b(15980, 2063476525429000337L ^ var1);
         } catch (NumberFormatException var7) {
            try {
               long var4 = ZonedDateTime.parse(var0.strip(), DateTimeFormatter.RFC_1123_DATE_TIME).toInstant().toEpochMilli();
               return Math.max(0L, Math.min(Duration.ofHours(1L).toMillis(), var4 - System.currentTimeMillis()));
            } catch (DateTimeException var61) {
               return 0L;
            }
         }
      } else {
         return 0L;
      }
   }

   static SpotifyLyrics fromRecord(JsonObject var0) {
      String var1 = string(var0, "syncedLyrics");
      if (!var1.isBlank()) {
         SpotifyLyrics var2 = SpotifyLyricParser.parse(var1);
         if (var2.synced()) {
            return var2;
         }
      }

      String var4 = string(var0, "plainLyrics");
      if (!var4.isBlank()) {
         return SpotifyLyrics.unsynced(var4);
      } else {
         JsonElement var3 = var0.get("instrumental");
         return var3 != null && var3.isJsonPrimitive() && var3.getAsBoolean() ? SpotifyLyrics.Q : SpotifyLyrics.f;
      }
   }

   static JsonObject bestMatch(JsonArray var0, SpotifyLyricCache.LyricQuery var1) {
      return bestMatch(var0, var1, 4.0);
   }

   static JsonObject bestMatch(JsonArray var0, SpotifyLyricCache.LyricQuery var1, double var2) {
      String var4 = normalize(cleanTitle(var1.title()));
      String var5 = normalize(var1.artist());
      String var6 = normalize(primaryArtist(var1.artist()));
      double var7 = var1.durationMs() / 1000.0;
      JsonObject var9 = null;
      double var10 = 0.0;

      for (JsonElement var13 : var0) {
         if (var13.isJsonObject()) {
            JsonObject var14 = var13.getAsJsonObject();
            SpotifyLyrics var15 = fromRecord(var14);
            if (var15.kind() != SpotifyLyrics.LyricStyle.H && var15.kind() != SpotifyLyrics.LyricStyle.I) {
               double var16 = similarity(normalize(cleanTitle(string(var14, "trackName"))), var4);
               String var18 = normalize(string(var14, "artistName"));
               double var19 = Math.max(
                  similarity(var18, var5), Math.max(similarity(var18, var6), (" " + var18 + " ").contains(" " + var6 + " ") && !var6.isEmpty() ? 0.9 : 0.0)
               );
               if (!(var16 < 0.8) && !(var19 < 0.7)) {
                  double var21 = var16 * 4.0 + var19 * 3.0;
                  JsonElement var23 = var14.get("duration");
                  if (var7 > 0.0 && var23 != null && !var23.isJsonNull()) {
                     double var24 = number(var14, "duration");
                     if (var24 <= 0.0) {
                        continue;
                     }

                     double var26 = Math.abs(var24 - var7);
                     if (var26 > var2) {
                        continue;
                     }

                     var21 += (var26 <= 2.0 ? 3.0 : (var26 <= 5.0 ? 1.5 : 0.0)) - var26 * 0.1;
                  }

                  if (var15.synced()) {
                     var21 += 10.0;
                  } else if (var15.kind() == SpotifyLyrics.LyricStyle.g) {
                     var21++;
                  }

                  if (!var1.album().isBlank() && normalize(string(var14, "albumName")).equals(normalize(var1.album()))) {
                     var21++;
                  }

                  if (var21 > var10) {
                     var10 = var21;
                     var9 = var14;
                  }
               }
            }
         }
      }

      return var9;
   }

   public static String cleanTitle(String var0) {
      String var1 = p.matcher(var0).replaceAll("");
      var1 = Y.matcher(var1).replaceAll("");
      var1 = d.matcher(var1).replaceAll("");
      var1 = var1.strip();
      return var1.isEmpty() ? var0.strip() : var1;
   }

   public static String primaryArtist(String var0) {
      String[] var1 = W.split(var0.strip(), 2);
      return var1.length == 0 ? "" : var1[0].strip();
   }

   public static String normalize(String var0) {
      String var1 = Normalizer.normalize(var0, Form.NFD).replaceAll("\\p{M}+", "").toLowerCase(Locale.ROOT).replace("&", " and ");
      StringBuilder var2 = new StringBuilder(var1.length());
      boolean var3 = false;
      int var4 = 0;

      while (var4 < var1.length()) {
         int var5 = var1.codePointAt(var4);
         var4 += Character.charCount(var5);
         if (Character.isLetterOrDigit(var5)) {
            if (var3 && !var2.isEmpty()) {
               var2.append(' ');
            }

            var3 = false;
            var2.appendCodePoint(var5);
         } else if (var5 != 39 && var5 != 8217) {
            var3 = true;
         }
      }

      return var2.toString();
   }

   static double similarity(String var0, String var1) {
      if (var0.equals(var1)) {
         return var0.isEmpty() ? 0.0 : 1.0;
      } else if (!var0.isEmpty() && !var1.isEmpty()) {
         List<String>var2 = List.of(var0.split(" "));
         ArrayList var3 = new ArrayList<>(List.of(var1.split(" ")));
         int var4 = 0;

         for (String var6 : var2) {
            if (var3.remove(var6)) {
               var4++;
            }
         }

         return 2.0 * var4 / (var2.size() + var1.split(" ").length);
      } else {
         return 0.0;
      }
   }

   static String string(JsonObject var0, String var1) {
      JsonElement var2 = var0.get(var1);
      return var2 != null && !var2.isJsonNull() && var2.isJsonPrimitive() ? var2.getAsString() : "";
   }

   static double number(JsonObject var0, String var1) {
      try {
         double var2 = Double.parseDouble(string(var0, var1));
         return Double.isFinite(var2) ? var2 : -1.0;
      } catch (NumberFormatException var41) {
         return -1.0;
      }
   }

   static String encode(String var0) {
      return URLEncoder.encode(var0, StandardCharsets.UTF_8);
   }

   private Path F(SpotifyLyricCache.LyricQuery var1, byte var2, int var3, int var4) {
      if (this.V == null) {
         return null;
      } else {
         try {
            byte[] var5 = MessageDigest.getInstance("SHA-1").digest(var1.key().getBytes(StandardCharsets.UTF_8));
            return this.V.resolve(HexFormat.of().formatHex(var5, 0, 12) + ".json");
         } catch (NoSuchAlgorithmException var61) {
            return null;
         }
      }
   }

   private static long t(SpotifyLyrics var0, long var1, int var3, long var4) {
      return switch (SpotifyLyricCache.LyricEntry.p[var0.kind().ordinal()]) {
         case 1 -> var1 + A;
         case 2 -> var1 + l;
         default -> Long.MAX_VALUE;
      };
   }

   private SpotifyLyricCache.CachedLyrics V(SpotifyLyricCache.LyricQuery var1, long var2) {
      long var4 = 34074029194184L;
      byte var6 = 0;
      int var7 = 2030970;
      int var8 = (int)(var4 << 40 >>> 40);
      short var9 = 31025;
      long var10 = 3228119071L;
      SpotifyCurve.ensureSourceClassInitialized();
      Path var12 = this.F(var1, var6, var7, var8);
      if (var12 != null) {
         long var13 = 126962524473502L;
         int var41 = var13 < 0L ? -1 : 1;
         if (Files.isRegularFile(var12)) {
            try {
               JsonObject var16 = JsonParser.parseString(Files.readString(var12, StandardCharsets.UTF_8)).getAsJsonObject();
               SpotifyLyrics.LyricStyle var17 = SpotifyLyrics.LyricStyle.valueOf(string(var16, "kind"));
               long var18 = 126962524473502L;
               int var20 = var18 < 0L ? -1 : 1;
               long var20x = 126962524473502L;
               int var15x = var20x < 0L ? -1 : 1;
               var15x = -1003753220;
               long var22 = var16.has("time") ? var16.get("time").getAsLong() : 0L;
               boolean var24 = var17 == SpotifyLyrics.LyricStyle.H || var17 == SpotifyLyrics.LyricStyle.g;
               double var25 = number(var16, "v");
               long var27 = 126962524473503L;
               int var30 = var27 < 0L ? -1 : 1;
               long var30x = 126962524473502L;
               int var33 = var30x < 0L ? -1 : 1;
               if (var24 && var25 != 4.0) {
                  return null;
               }

               Files.setLastModifiedTime(var12, FileTime.fromMillis(System.currentTimeMillis()));

               SpotifyLyrics var33x = switch (SpotifyLyricCache.LyricEntry.p[var17.ordinal()]) {
                  case 1 -> SpotifyLyrics.f;
                  case 2 -> SpotifyLyrics.unsynced(string(var16, "plain"));
                  case 3 -> {
                     SpotifyLyrics var34 = SpotifyLyricParser.parse(string(var16, "lrc"));
                     yield var34.synced() ? var34 : null;
                  }
                  case 4 -> SpotifyLyrics.Q;
                  default -> throw new MatchException(null, null);
               };
               if (var33x != null && (var17 != SpotifyLyrics.LyricStyle.g || !var33x.plain().isBlank())) {
                  long var37 = 126962524473503L;
                  int var42 = var37 < 0L ? -1 : 1;
                  long var42x = var16.has("expires") ? var16.get("expires").getAsLong() : t(var33x, var22, var9, var10);
                  if (var33x.synced()) {
                     boolean var36 = var33x.wordTimed();
                     long var39 = 126962524473502L;
                     int var39x = var39 < 0L ? -1 : 1;
                     if (!var36 && (var25 < 4.0 || string(var16, "words").equals("later"))) {
                        var36 = true;
                        return var42x > System.currentTimeMillis() ? new SpotifyLyricCache.CachedLyrics(var33x, var42x, var36) : null;
                     }
                  }

                  boolean var43 = false;
                  return var42x > System.currentTimeMillis() ? new SpotifyLyricCache.CachedLyrics(var33x, var42x, var43) : null;
               }

               return null;
            } catch (Exception var40) {
               return null;
            }
         }
      }

      return null;
   }

   private void t(SpotifyLyricCache.LyricQuery var1, SpotifyLyricCache.CachedLyrics var2, long var3) {
      long var5 = 34074029194184L;
      byte var7 = 0;
      int var8 = 2030970;
      int var9 = (int)(var5 << 40 >>> 40);
      long var10 = 69155993097708L;
      SpotifyCurve.ensureSourceClassInitialized();
      Path var12 = this.F(var1, var7, var8, var9);
      if (var12 != null) {
         JsonObject var13 = new JsonObject();
         SpotifyLyrics var14 = var2.lyrics();
         var13.addProperty("v", 4);
         var13.addProperty("kind", var14.kind().name());
         var13.addProperty("time", System.currentTimeMillis());
         var13.addProperty("expires", var2.expiresAt());
         if (var2.upgrade()) {
            var13.addProperty("words", "later");
         }

         if (var14.synced()) {
            var13.addProperty("lrc", toLrc(var14));
            long var15 = 139986552115157L;
            int var17 = var15 < 0L ? -1 : 1;
         } else if (var14.kind() == SpotifyLyrics.LyricStyle.g) {
            var13.addProperty("plain", var14.plain());
         }

         try {
            Files.createDirectories(var12.getParent());
            Path var19 = var12.resolveSibling(var12.getFileName() + ".tmp");
            Files.writeString(var19, var13.toString(), StandardCharsets.UTF_8);
            Files.move(var19, var12, StandardCopyOption.REPLACE_EXISTING);
            this.s(var10);
         } catch (IOException var18) {
         }
      }
   }

   private void s(long var1) throws IOException {
      SpotifyCurve.ensureSourceClassInitialized();
      if (this.V != null) {
         List var3;
         try (Stream var4 = Files.list(this.V)) {
            var3 = var4.filter(var0 -> ((Path)var0).getFileName().toString().endsWith(".json")).toList();
         }

         if (var3.size() > 600) {
            ArrayList var10 = new ArrayList(var3);
            var10.sort(Comparator.comparingLong(var0 -> {
               try {
                  return Files.getLastModifiedTime((Path)var0).toMillis();
               } catch (IOException var2) {
                  return 0L;
               }
            }));

            for (int var5 = 0; var5 < var10.size() - 600; var5++) {
               Files.deleteIfExists((Path)var10.get(var5));
            }

            long var11 = 69155993097708L;
            int var7 = var11 < 0L ? -1 : 1;
         }
      }
   }

   static String toLrc(SpotifyLyrics var0) {
      long var1 = 87234793523847L;
      int var3 = (int)((var1 ^ 129841925153540L) >>> 48);
      long var4 = (var1 ^ 129841925153540L) << 16 >>> 16;
      StringBuilder var6 = new StringBuilder();

      for (SpotifyLyrics.Data var8 : var0.lines()) {
         var6.append(o('[', var8.startMs(), ']', (char)var3, var4));
         List var9 = var8.words();
         if (var9.isEmpty()) {
            var6.append(var8.text());
            if (var8.hasEnd()) {
               var6.append(o('<', var8.endMs(), '>', (char)var3, var4));
            }
         } else {
            for (int var10 = 0; var10 < var9.size(); var10++) {
               SpotifyLyrics.TimedLyricLine var11 = (SpotifyLyrics.TimedLyricLine)var9.get(var10);
               var6.append(o('<', var11.startMs(), '>', (char)var3, var4)).append(var11.text());
               boolean var12 = var10 + 1 == var9.size();
               long var13 = var11.hasEnd() ? var11.endMs() : (var12 && var8.hasEnd() ? var8.endMs() : -1L);
               if (var13 > var11.startMs() && (var12 || var13 < ((SpotifyLyrics.TimedLyricLine)var9.get(var10 + 1)).startMs())) {
                  var6.append(o('<', var13, '>', (char)var3, var4));
               }
            }
         }

         var6.append('\n');
      }

      return var6.toString();
   }

   private static String o(char var0, long var1, char var3, char var4, long var5) {
      return String.format(Locale.ROOT, "%c%02d:%02d.%03d%c", var0, var1 / 60000L, var1 / 1000L % 60L, var1 % 1000L, var3);
   }

   void closeOwned() {
      this.i.incrementAndGet();
      this.I.shutdownNow();
      if (this.y instanceof SpotifyLyricCache.LyricWorker var1) {
         var1.close();
      }

      try {
         if (!this.I.awaitTermination(6L, TimeUnit.SECONDS)) {
            throw new IllegalStateException("Lyrics worker did not stop");
         }
      } catch (InterruptedException var31) {
         Thread.currentThread().interrupt();
         throw new IllegalStateException("Lyrics worker shutdown interrupted");
      }

      this.X.clear();
      this.u.clear();
      this.M.clear();
   }

   private static int a(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 13907;
      if (g[var3] == null) {
         g[var3] = (int)(c[var3] ^ var1);
      }

      return g[var3];
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 28005) & 32767;
      if (j[var3] == null) {
         j[var3] = h[var3] ^ var1;
      }

      return j[var3];
   }

   record Data(int P, String F, String V) {
      public int status() {
         return this.P;
      }

      public String body() {
         return this.F;
      }

      public String retryAfter() {
         return this.V;
      }

      public int integerState43() {
         return this.P;
      }

      public String stringState20() {
         return this.F;
      }

      public String stringState1() {
         return this.V;
      }
   }

   public record LyricQuery(String j, String y, String P, long J) {
      String key() {
         return SpotifyLyricCache.normalize(this.j)
            + "|"
            + SpotifyLyricCache.normalize(this.y)
            + "|"
            + SpotifyLyricCache.normalize(this.P)
            + "|"
            + Math.round(this.J / 1000.0);
      }

      public String title() {
         return this.j;
      }

      public String artist() {
         return this.y;
      }

      public String album() {
         return this.P;
      }

      public long durationMs() {
         return this.J;
      }

      public String stringState29() {
         return this.j;
      }

      public String stringState21() {
         return this.y;
      }

      public String stringState27() {
         return this.P;
      }

      public long longState11() {
         return this.J;
      }
   }

   @FunctionalInterface
   interface Listener {
      CompletableFuture<SpotifyLyricCache.Data> send(HttpRequest var1);
   }

   private record LyricFetch(String m, String u, long e, CompletableFuture<SpotifyLyricCache.Data> K, SpotifyLyricCache.LyricLookupException t) {
      void cancel() {
         if (this.K != null && !this.K.isDone()) {
            this.K.cancel(true);
         }
      }

      public String url() {
         return this.m;
      }

      public String host() {
         return this.u;
      }

      public long sentAt() {
         return this.e;
      }

      public CompletableFuture<SpotifyLyricCache.Data> future() {
         return this.K;
      }

      public SpotifyLyricCache.LyricLookupException refused() {
         return this.t;
      }

      public String stringState52() {
         return this.m;
      }

      public String stringState4() {
         return this.u;
      }

      public long longState4() {
         return this.e;
      }

      public CompletableFuture<SpotifyLyricCache.Data> completableFutureState8() {
         return this.K;
      }

      public SpotifyLyricCache.LyricLookupException lyricsLookupService_LyricsSourceResultState1() {
         return this.t;
      }
   }

   private static final class LyricWorker implements SpotifyLyricCache.Listener, AutoCloseable {
      private final ExecutorService W = ClientServices.visualExecutor("SourceByDevFFnightmare DeObfDeCompByGuideX Lyrics HTTP");
      private final HttpClient f = ClientServices.visualHttpClient(this.W);
      private static final String[] b = new String[4];

      private LyricWorker(long var1) {
      }

      @Override
      public CompletableFuture<SpotifyLyricCache.Data> send(HttpRequest var1) {
         CompletableFuture var2 = OfflineNetwork.sendAsync(this.f, var1, ByteArrayBodySubscriber.handler(2097152));
         CompletableFuture var3 = var2.thenApply(
            var0 -> new SpotifyLyricCache.Data(
               ((HttpResponse)var0).statusCode(),
               new String((byte[])((HttpResponse)var0).body(), StandardCharsets.UTF_8),
               ((HttpResponse)var0).headers().firstValue("Retry-After").orElse("")
            )
         );
         var3.whenComplete((var2xx, var3xx) -> {
            SpotifyCurve.ensureSourceClassInitialized();
            long var4 = 102040302359407L;
            int var6 = var4 < 0L ? -1 : 1;
            if (var3.isCancelled()) {
               var2.cancel(true);
            }
         });
         return var3;
      }

      @Override
      public void close() {
         this.f.shutdownNow();

         try {
            if (!this.f.awaitTermination(Duration.ofSeconds(6L))) {
               throw new IllegalStateException("Lyrics HTTP client did not stop");
            }
         } catch (InterruptedException var5) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Lyrics shutdown interrupted");
         } finally {
            this.W.shutdownNow();
         }
      }
   }

   private static final class LyricCacheException extends RuntimeException {
      LyricCacheException() {
         super(null, null, false, false);
      }
   }

   static class LyricEntry {
      static final int[] p = new int[SpotifyLyrics.LyricStyle.values().length];

      static {
         try {
            p[SpotifyLyrics.LyricStyle.H.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
         }

         try {
            p[SpotifyLyrics.LyricStyle.g.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
         }

         try {
            p[SpotifyLyrics.LyricStyle.k.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
         }

         try {
            p[SpotifyLyrics.LyricStyle.I.ordinal()] = 4;
         } catch (NoSuchFieldError var1) {
         }
      }
   }

   private record CachedLyrics(SpotifyLyrics v, long i, boolean F) {
      CachedLyrics(SpotifyLyrics var1, long var2) {
         this(var1, var2, false);
      }

      public SpotifyLyrics lyrics() {
         return this.v;
      }

      public long expiresAt() {
         return this.i;
      }

      public boolean upgrade() {
         return this.F;
      }

      public SpotifyLyrics lyricsDocumentState6() {
         return this.v;
      }

      public long longState17() {
         return this.i;
      }

      public boolean booleanState53() {
         return this.F;
      }
   }

   private record LyricResult(SpotifyLyrics U, boolean Z, boolean b) {
      LyricResult(SpotifyLyrics var1, boolean var2) {
         this(var1, var2, true);
      }

      public SpotifyLyrics lyrics() {
         return this.U;
      }

      public boolean complete() {
         return this.Z;
      }

      public boolean wordsAsked() {
         return this.b;
      }

      public SpotifyLyrics lyricsDocumentState7() {
         return this.U;
      }

      public boolean booleanState6() {
         return this.Z;
      }

      public boolean booleanState50() {
         return this.b;
      }
   }

   public static final class LyricLookupException extends Exception {
      private final SpotifyLyrics d;

      public LyricLookupException(String var1) {
         this(var1, SpotifyLyrics.f);
      }

      public LyricLookupException(String var1, SpotifyLyrics var2) {
         super(var1);
         this.d = var2;
      }

      public SpotifyLyrics found() {
         return this.d;
      }
   }
}
