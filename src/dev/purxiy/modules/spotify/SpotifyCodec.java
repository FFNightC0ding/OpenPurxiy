package dev.purxiy.modules.spotify;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

final class SpotifyCodec {
   static final double x = 2.5;
   static final double h = 5.43;
   private static final long s = 60L;

   private SpotifyCodec() {
   }

   static List<SpotifyCodec.Data> sources(SpotifyLyricCache.LyricQuery var0) {
      String var1 = SpotifyLyricCache.encode(SpotifyLyricCache.cleanTitle(var0.title()));
      String var2 = SpotifyLyricCache.encode(SpotifyLyricCache.primaryArtist(var0.artist()));
      String var3 = var0.durationMs() > 0L ? "&duration=" + var0.durationMs() / 1000.0 : "";
      String var4 = "https://lrclib.net/api/get?track_name=" + SpotifyLyricCache.encode(var0.title()) + "&artist_name=" + SpotifyLyricCache.encode(var0.artist());
      if (!var0.album().isBlank()) {
         var4 = var4 + "&album_name=" + SpotifyLyricCache.encode(var0.album());
      }

      String var5 = "title=" + SpotifyLyricCache.encode(var0.title()) + "&artist=" + SpotifyLyricCache.encode(var0.artist());
      if (!var0.album().isBlank()) {
         var5 = var5 + "&album=" + SpotifyLyricCache.encode(var0.album());
      }

      SpotifyCodec.Data var6 = new SpotifyCodec.Data("LRCLIB exact", var4 + var3, true, true, false, var0x -> {
         long var1x = 112452613987946L;
         SpotifyCurve.ensureSourceClassInitialized();
         boolean var3x = false;
         return var0x == null ? SpotifyLyrics.f : SpotifyLyricCache.fromRecord(h(var1x, var0x));
      });
      ArrayList var9 = new ArrayList<>(
         List.of(
            var6,
            new SpotifyCodec.Data(
               "LyricsPlus",
               "https://lyricsplus.binimum.org/v2/lyrics/get?" + var5 + var3,
               false,
               true,
               true,
               var1x -> lyricsPlus(var1x, var0),
               var1x -> lyricsPlus(var1x, var0, Double.MAX_VALUE)
            )
         )
      );
      String var10 = "title=" + var1 + "&artist=" + var2;
      if (!var10.equals(var5)) {
         var9.add(
            new SpotifyCodec.Data(
               "LyricsPlus search",
               "https://lyricsplus.binimum.org/v2/lyrics/get?" + var10 + var3,
               false,
               false,
               true,
               var1x -> lyricsPlus(var1x, var0),
               var1x -> lyricsPlus(var1x, var0, Double.MAX_VALUE)
            )
         );
      }

      SpotifyCodec.Data var11 = new SpotifyCodec.Data(
         "LRCLIB search", "https://lrclib.net/api/search?track_name=" + var1 + "&artist_name=" + var2, false, false, false, var1x -> {
            long var2x = 18965334924579L;
            byte var4x = 0;
            int var5x = 289388045;
            int var6x = (int)(var2x << 48 >>> 48);
            return C((char)var4x, var5x, var1x, var0, var6x);
         }
      );
      var6 = new SpotifyCodec.Data("LRCLIB text search", "https://lrclib.net/api/search?q=" + var1 + "+" + var2, false, false, false, var1x -> {
         long var2x = 18965334924579L;
         byte var4x = 0;
         int var5x = 289388045;
         int var6x = (int)(var2x << 48 >>> 48);
         return C((char)var4x, var5x, var1x, var0, var6x);
      });
      SpotifyCodec.Data var12 = new SpotifyCodec.Data(
         "LrcAPI", "https://api.lrc.cx/jsonapi?title=" + var1 + "&artist=" + var2, false, false, false, var1x -> lrcApi(var1x, var0)
      );
      String var13 = SpotifyLyricCache.encode(var0.title());
      String var14 = SpotifyLyricCache.encode(var0.artist());
      String var15 = var0.durationMs() > 0L ? "&d=" + Math.round(var0.durationMs() / 1000.0) : "";
      String var16 = SpotifyLyricCache.encode(var0.album());
      var9.addAll(
         List.of(
            var11,
            var6,
            var12,
            new SpotifyCodec.Data(
               "Better Lyrics", "https://api.betterlyrics.org/getLyrics?s=" + var13 + "&a=" + var14 + "&al=" + var16 + var15, false, false, false, var0x -> {
                  long var1x = 112452613987946L;
                  SpotifyCurve.ensureSourceClassInitialized();
                  boolean var3x = false;
                  return var0x == null ? SpotifyLyrics.f : SpotifyError.parse(SpotifyLyricCache.string(h(var1x, var0x), "ttml"));
               }
            ),
            new SpotifyCodec.Data(
               "lyrics.ovh",
               "https://api.lyrics.ovh/v1/" + var2.replace("+", "%20") + "/" + var1.replace("+", "%20"),
               false,
               false,
               false,
               var0x -> V(122740691723228L, var0x)
            )
         )
      );
      return List.copyOf(var9);
   }

   private static JsonObject h(long var0, JsonElement var2) {
      SpotifyCurve.ensureSourceClassInitialized();
      if (!var2.isJsonObject()) {
         throw new IllegalArgumentException("Expected a lyrics object");
      } else {
         JsonObject var3 = var2.getAsJsonObject();
         JsonElement var4 = var3.get("error");
         long var5 = 112452613987945L;
         int var7 = var5 < 0L ? -1 : 1;
         if (var4 != null && !var4.isJsonNull() && !var4.toString().equals("false")) {
            throw new IllegalArgumentException("Provider returned an error");
         } else {
            return var3;
         }
      }
   }

   private static SpotifyLyrics C(char var0, int var1, JsonElement var2, SpotifyLyricCache.LyricQuery var3, int var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      if (var2 == null) {
         return SpotifyLyrics.f;
      } else if (!var2.isJsonArray()) {
         throw new IllegalArgumentException("Expected lyrics search results");
      } else {
         JsonObject var5 = SpotifyLyricCache.bestMatch(var2.getAsJsonArray(), var3);
         return var5 == null ? SpotifyLyrics.f : SpotifyLyricCache.fromRecord(var5);
      }
   }

   static SpotifyLyrics lrcApi(JsonElement var0, SpotifyLyricCache.LyricQuery var1) {
      long var2 = 71843860746685L;
      long var4 = var2 ^ 88410924616862L;
      int var6 = (int)((var2 ^ 88410924616862L) >>> 48);
      int var7 = (int)((var2 ^ 88410924616862L) << 16 >>> 32);
      int var8 = (int)(var4 << 48 >>> 48);
      if (var0 == null) {
         return SpotifyLyrics.f;
      } else if (!var0.isJsonArray()) {
         throw new IllegalArgumentException("Expected LrcAPI results");
      } else {
         JsonArray var9 = new JsonArray();

         for (JsonElement var11 : var0.getAsJsonArray()) {
            if (var11.isJsonObject()) {
               JsonObject var12 = var11.getAsJsonObject();
               JsonObject var13 = new JsonObject();
               var13.addProperty("trackName", SpotifyLyricCache.string(var12, "title"));
               var13.addProperty("artistName", SpotifyLyricCache.string(var12, "artist"));
               var13.addProperty("albumName", SpotifyLyricCache.string(var12, "album"));
               if (var12.has("duration")) {
                  var13.add("duration", var12.get("duration"));
               }

               var13.addProperty("syncedLyrics", SpotifyLyricCache.string(var12, "lrc"));
               var9.add(var13);
            }
         }

         return C((char)var6, var7, var9, var1, var8);
      }
   }

   static SpotifyLyrics lyricsPlus(JsonElement var0, SpotifyLyricCache.LyricQuery var1) {
      return lyricsPlus(var0, var1, 2.5);
   }

   static SpotifyLyrics lyricsPlus(JsonElement var0, SpotifyLyricCache.LyricQuery var1, double var2) {
      long var4 = 110334050624822L;
      long var6 = var4 ^ 2333508942684L;
      if (var0 == null) {
         return SpotifyLyrics.f;
      } else {
         JsonObject var8 = h(var6, var0);
         String var9 = SpotifyLyricCache.string(var8, "type");
         if (!var9.equalsIgnoreCase("Word") && !var9.equalsIgnoreCase("Line")) {
            return SpotifyLyrics.f;
         } else if (var8.has("lyrics") && var8.get("lyrics").isJsonArray()) {
            JsonObject var10 = var8.has("metadata") && var8.get("metadata").isJsonObject() ? var8.getAsJsonObject("metadata") : new JsonObject();
            JsonObject var11 = new JsonObject();
            var11.addProperty("trackName", SpotifyLyricCache.string(var10, "title"));
            var11.addProperty("artistName", SpotifyLyricCache.string(var10, "artist"));
            var11.addProperty("albumName", SpotifyLyricCache.string(var10, "album"));
            long var12 = SpotifyError.time(SpotifyLyricCache.string(var10, "totalDuration"));
            if (var12 > 0L) {
               double var14 = var12 / 1000.0;
               double var16 = var14 - 5.43;
               double var18 = var1.durationMs() / 1000.0;
               var11.addProperty("duration", var18 > 0.0 && Math.abs(var16 - var18) < Math.abs(var14 - var18) ? var16 : var14);
            }

            ArrayList var41 = new ArrayList();

            for (JsonElement var44 : var8.getAsJsonArray("lyrics")) {
               if (var44.isJsonObject()) {
                  JsonObject var17 = var44.getAsJsonObject();
                  double var47 = SpotifyLyricCache.number(var17, "time");
                  String var20 = SpotifyLyricCache.string(var17, "text");
                  if (!(var47 < 0.0) && !(var47 > 8.64E7) && !var20.isBlank()) {
                     double var21 = SpotifyLyricCache.number(var17, "duration");
                     long var23 = var21 > 0.0 ? (long)(var47 + var21) : -1L;
                     ArrayList var25 = new ArrayList();
                     StringBuilder var26 = new StringBuilder();
                     long var27 = (long)var47;
                     if (var17.has("syllabus") && var17.get("syllabus").isJsonArray()) {
                        for (JsonElement var30 : var17.getAsJsonArray("syllabus")) {
                           if (var30.isJsonObject()) {
                              JsonObject var31 = var30.getAsJsonObject();
                              double var32 = SpotifyLyricCache.number(var31, "time");
                              String var34 = SpotifyLyricCache.string(var31, "text");
                              if (!(var32 < 0.0) && !(var32 > 8.64E7) && !var34.isEmpty()) {
                                 long var35 = Math.max(var27, (long)var32);
                                 double var37 = SpotifyLyricCache.number(var31, "duration");
                                 long var39 = var37 > 0.0 ? (long)(var32 + var37) : -1L;
                                 var25.add(new SpotifyLyrics.TimedLyricLine(var35, var34, var39 > var35 ? var39 : -1L));
                                 var27 = var35;
                                 var26.append(var34);
                                 var23 = Math.max(var23, var39);
                              }
                           }
                        }
                     }

                     if (!var26.toString().strip().equals(var20.strip())) {
                        var25.clear();
                     }

                     var41.add(new SpotifyLyrics.Data((long)var47, var20, List.copyOf(var25), var23));
                  }
               }
            }

            var41.sort(Comparator.comparingLong(SpotifyLyrics.Data::startMs));

            for (int var42 = 0; var42 + 1 < var41.size(); var42++) {
               SpotifyLyrics.Data var45 = (SpotifyLyrics.Data)var41.get(var42);
               if (var45.hasEnd() && Math.abs(var45.endMs() - ((SpotifyLyrics.Data)var41.get(var42 + 1)).startMs()) <= 60L && !var45.wordTimed()) {
                  var41.set(var42, new SpotifyLyrics.Data(var45.startMs(), var45.text(), var45.words(), -1L));
               }
            }

            var11.addProperty("syncedLyrics", SpotifyLyricCache.toLrc(new SpotifyLyrics(SpotifyLyrics.LyricStyle.k, var41, "")));
            JsonArray var43 = new JsonArray();
            var43.add(var11);
            JsonObject var46 = SpotifyLyricCache.bestMatch(var43, var1, var2);
            return var46 == null ? SpotifyLyrics.f : SpotifyLyricCache.fromRecord(var46);
         } else {
            return SpotifyLyrics.f;
         }
      }
   }

   private static SpotifyLyrics V(long var0, JsonElement var2) {
      long var3 = 112452613987946L;
      SpotifyCurve.ensureSourceClassInitialized();
      if (var2 == null) {
         return SpotifyLyrics.f;
      } else {
         String var5 = SpotifyLyricCache.string(h(var3, var2), "lyrics").strip();
         if (var5.isEmpty()) {
            return SpotifyLyrics.f;
         } else {
            SpotifyLyrics var6 = SpotifyLyricParser.parse(var5);
            return var6.synced() ? var6 : SpotifyLyrics.unsynced(var5);
         }
      }
   }

   record Data(String x, String J, boolean i, boolean O, boolean G, Function<JsonElement, SpotifyLyrics> K, Function<JsonElement, SpotifyLyrics> g) {
      Data(String var1, String var2, boolean var3, boolean var4, boolean var5, Function<JsonElement, SpotifyLyrics> var6) {
         this(var1, var2, var3, var4, var5, var6, null);
      }

      public String name() {
         return this.x;
      }

      public String url() {
         return this.J;
      }

      public boolean exact() {
         return this.i;
      }

      public boolean first() {
         return this.O;
      }

      public boolean words() {
         return this.G;
      }

      public Function<JsonElement, SpotifyLyrics> parse() {
         return this.K;
      }

      public Function<JsonElement, SpotifyLyrics> otherRecording() {
         return this.g;
      }

      public String stringState44() {
         return this.x;
      }

      public String stringState45() {
         return this.J;
      }

      public boolean booleanState3() {
         return this.i;
      }

      public boolean booleanState52() {
         return this.O;
      }

      public boolean booleanState43() {
         return this.G;
      }

      public Function<JsonElement, SpotifyLyrics> functionState1() {
         return this.K;
      }

      public Function<JsonElement, SpotifyLyrics> functionState2() {
         return this.g;
      }
   }
}
