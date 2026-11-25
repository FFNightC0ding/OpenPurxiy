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

   static List<SpotifyCodec.Data> sources(SpotifyCache2.Data2 var0) {
      String var1 = SpotifyCache2.encode(SpotifyCache2.cleanTitle(var0.title()));
      String var2 = SpotifyCache2.encode(SpotifyCache2.primaryArtist(var0.artist()));
      String var3 = var0.durationMs() > 0L ? "&duration=" + var0.durationMs() / 1000.0 : "";
      String var4 = "https://lrclib.net/api/get?track_name=" + SpotifyCache2.encode(var0.title()) + "&artist_name=" + SpotifyCache2.encode(var0.artist());
      if (!var0.album().isBlank()) {
         var4 = var4 + "&album_name=" + SpotifyCache2.encode(var0.album());
      }

      String var5 = "title=" + SpotifyCache2.encode(var0.title()) + "&artist=" + SpotifyCache2.encode(var0.artist());
      if (!var0.album().isBlank()) {
         var5 = var5 + "&album=" + SpotifyCache2.encode(var0.album());
      }

      SpotifyCodec.Data var6 = new SpotifyCodec.Data("LRCLIB exact", var4 + var3, true, true, false, var0x -> {
         long var1x = 112452613987946L;
         SpotifyCurve.ensureSourceClassInitialized();
         boolean var3x = false;
         return var0x == null ? SpotifySupport3.f : SpotifyCache2.fromRecord(h(var1x, var0x));
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
      String var13 = SpotifyCache2.encode(var0.title());
      String var14 = SpotifyCache2.encode(var0.artist());
      String var15 = var0.durationMs() > 0L ? "&d=" + Math.round(var0.durationMs() / 1000.0) : "";
      String var16 = SpotifyCache2.encode(var0.album());
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
                  return var0x == null ? SpotifySupport3.f : SpotifyCodec2.parse(SpotifyCache2.string(h(var1x, var0x), "ttml"));
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

   private static SpotifySupport3 C(char var0, int var1, JsonElement var2, SpotifyCache2.Data2 var3, int var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      if (var2 == null) {
         return SpotifySupport3.f;
      } else if (!var2.isJsonArray()) {
         throw new IllegalArgumentException("Expected lyrics search results");
      } else {
         JsonObject var5 = SpotifyCache2.bestMatch(var2.getAsJsonArray(), var3);
         return var5 == null ? SpotifySupport3.f : SpotifyCache2.fromRecord(var5);
      }
   }

   static SpotifySupport3 lrcApi(JsonElement var0, SpotifyCache2.Data2 var1) {
      long var2 = 71843860746685L;
      long var4 = var2 ^ 88410924616862L;
      int var6 = (int)((var2 ^ 88410924616862L) >>> 48);
      int var7 = (int)((var2 ^ 88410924616862L) << 16 >>> 32);
      int var8 = (int)(var4 << 48 >>> 48);
      if (var0 == null) {
         return SpotifySupport3.f;
      } else if (!var0.isJsonArray()) {
         throw new IllegalArgumentException("Expected LrcAPI results");
      } else {
         JsonArray var9 = new JsonArray();

         for (JsonElement var11 : var0.getAsJsonArray()) {
            if (var11.isJsonObject()) {
               JsonObject var12 = var11.getAsJsonObject();
               JsonObject var13 = new JsonObject();
               var13.addProperty("trackName", SpotifyCache2.string(var12, "title"));
               var13.addProperty("artistName", SpotifyCache2.string(var12, "artist"));
               var13.addProperty("albumName", SpotifyCache2.string(var12, "album"));
               if (var12.has("duration")) {
                  var13.add("duration", var12.get("duration"));
               }

               var13.addProperty("syncedLyrics", SpotifyCache2.string(var12, "lrc"));
               var9.add(var13);
            }
         }

         return C((char)var6, var7, var9, var1, var8);
      }
   }

   static SpotifySupport3 lyricsPlus(JsonElement var0, SpotifyCache2.Data2 var1) {
      return lyricsPlus(var0, var1, 2.5);
   }

   static SpotifySupport3 lyricsPlus(JsonElement var0, SpotifyCache2.Data2 var1, double var2) {
      long var4 = 110334050624822L;
      long var6 = var4 ^ 2333508942684L;
      if (var0 == null) {
         return SpotifySupport3.f;
      } else {
         JsonObject var8 = h(var6, var0);
         String var9 = SpotifyCache2.string(var8, "type");
         if (!var9.equalsIgnoreCase("Word") && !var9.equalsIgnoreCase("Line")) {
            return SpotifySupport3.f;
         } else if (var8.has("lyrics") && var8.get("lyrics").isJsonArray()) {
            JsonObject var10 = var8.has("metadata") && var8.get("metadata").isJsonObject() ? var8.getAsJsonObject("metadata") : new JsonObject();
            JsonObject var11 = new JsonObject();
            var11.addProperty("trackName", SpotifyCache2.string(var10, "title"));
            var11.addProperty("artistName", SpotifyCache2.string(var10, "artist"));
            var11.addProperty("albumName", SpotifyCache2.string(var10, "album"));
            long var12 = SpotifyCodec2.time(SpotifyCache2.string(var10, "totalDuration"));
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
                  double var47 = SpotifyCache2.number(var17, "time");
                  String var20 = SpotifyCache2.string(var17, "text");
                  if (!(var47 < 0.0) && !(var47 > 8.64E7) && !var20.isBlank()) {
                     double var21 = SpotifyCache2.number(var17, "duration");
                     long var23 = var21 > 0.0 ? (long)(var47 + var21) : -1L;
                     ArrayList var25 = new ArrayList();
                     StringBuilder var26 = new StringBuilder();
                     long var27 = (long)var47;
                     if (var17.has("syllabus") && var17.get("syllabus").isJsonArray()) {
                        for (JsonElement var30 : var17.getAsJsonArray("syllabus")) {
                           if (var30.isJsonObject()) {
                              JsonObject var31 = var30.getAsJsonObject();
                              double var32 = SpotifyCache2.number(var31, "time");
                              String var34 = SpotifyCache2.string(var31, "text");
                              if (!(var32 < 0.0) && !(var32 > 8.64E7) && !var34.isEmpty()) {
                                 long var35 = Math.max(var27, (long)var32);
                                 double var37 = SpotifyCache2.number(var31, "duration");
                                 long var39 = var37 > 0.0 ? (long)(var32 + var37) : -1L;
                                 var25.add(new SpotifySupport3.Data2(var35, var34, var39 > var35 ? var39 : -1L));
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

                     var41.add(new SpotifySupport3.Data((long)var47, var20, List.copyOf(var25), var23));
                  }
               }
            }

            var41.sort(Comparator.comparingLong(SpotifySupport3.Data::startMs));

            for (int var42 = 0; var42 + 1 < var41.size(); var42++) {
               SpotifySupport3.Data var45 = (SpotifySupport3.Data)var41.get(var42);
               if (var45.hasEnd() && Math.abs(var45.endMs() - ((SpotifySupport3.Data)var41.get(var42 + 1)).startMs()) <= 60L && !var45.wordTimed()) {
                  var41.set(var42, new SpotifySupport3.Data(var45.startMs(), var45.text(), var45.words(), -1L));
               }
            }

            var11.addProperty("syncedLyrics", SpotifyCache2.toLrc(new SpotifySupport3(SpotifySupport3.TypeK.k, var41, "")));
            JsonArray var43 = new JsonArray();
            var43.add(var11);
            JsonObject var46 = SpotifyCache2.bestMatch(var43, var1, var2);
            return var46 == null ? SpotifySupport3.f : SpotifyCache2.fromRecord(var46);
         } else {
            return SpotifySupport3.f;
         }
      }
   }

   private static SpotifySupport3 V(long var0, JsonElement var2) {
      long var3 = 112452613987946L;
      SpotifyCurve.ensureSourceClassInitialized();
      if (var2 == null) {
         return SpotifySupport3.f;
      } else {
         String var5 = SpotifyCache2.string(h(var3, var2), "lyrics").strip();
         if (var5.isEmpty()) {
            return SpotifySupport3.f;
         } else {
            SpotifySupport3 var6 = SpotifyCodec3.parse(var5);
            return var6.synced() ? var6 : SpotifySupport3.unsynced(var5);
         }
      }
   }

   record Data(String x, String J, boolean i, boolean O, boolean G, Function<JsonElement, SpotifySupport3> K, Function<JsonElement, SpotifySupport3> g) {
      Data(String var1, String var2, boolean var3, boolean var4, boolean var5, Function<JsonElement, SpotifySupport3> var6) {
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

      public Function<JsonElement, SpotifySupport3> parse() {
         return this.K;
      }

      public Function<JsonElement, SpotifySupport3> otherRecording() {
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

      public Function<JsonElement, SpotifySupport3> functionState1() {
         return this.K;
      }

      public Function<JsonElement, SpotifySupport3> functionState2() {
         return this.g;
      }
   }
}
