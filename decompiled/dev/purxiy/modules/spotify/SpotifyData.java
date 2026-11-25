package dev.purxiy.modules.spotify;

public record SpotifyData(String u, long Z, String w, String K, String S, long T, long e, long X, SpotifyData.TypeG s, int J, SpotifyData.TypeD t, int D) {
   public static final int V = 1;
   public static int H = 2;
   public static int O = 4;
   public static int y = 8;
   public static int M = 16;
   public static int z = 32;
   public static int k = 64;
   public static int r = 128;

   public boolean playing() {
      return this.s == SpotifyData.TypeG.t;
   }

   public boolean can(int var1) {
      return (this.D & var1) != 0;
   }

   public long positionAt(long var1) {
      long var3 = this.e;
      if (this.playing()) {
         var3 += (var1 - this.X) / 1000000L;
      }

      return this.T > 0L ? Math.max(0L, Math.min(this.T, var3)) : Math.max(0L, var3);
   }

   public boolean sameTrack(SpotifyData var1) {
      return var1 != null && this.Z == var1.Z;
   }

   public String app() {
      return this.u;
   }

   public long track() {
      return this.Z;
   }

   public String title() {
      return this.w;
   }

   public String artist() {
      return this.K;
   }

   public String album() {
      return this.S;
   }

   public long durationMs() {
      return this.T;
   }

   public long positionMs() {
      return this.e;
   }

   public long anchorNanos() {
      return this.X;
   }

   public SpotifyData.TypeG status() {
      return this.s;
   }

   public int shuffle() {
      return this.J;
   }

   public SpotifyData.TypeD repeat() {
      return this.t;
   }

   public int controls() {
      return this.D;
   }

   public String stringState4() {
      return this.u;
   }

   public long longState45() {
      return this.Z;
   }

   public String stringState59() {
      return this.w;
   }

   public String stringState14() {
      return this.K;
   }

   public String stringState37() {
      return this.S;
   }

   public long longState27() {
      return this.T;
   }

   public long longState4() {
      return this.e;
   }

   public long longState39() {
      return this.X;
   }

   public SpotifyData.TypeG mediaPlaybackSnapshot_PlaybackStatusState2() {
      return this.s;
   }

   public int integerState37() {
      return this.J;
   }

   public SpotifyData.TypeD mediaPlaybackSnapshot_PlaybackRepeatModeState3() {
      return this.t;
   }

   public int integerState33() {
      return this.D;
   }

   public static enum TypeD {
      n,
      r,
      H,
      D;

      static SpotifyData.TypeD of(int var0) {
         return switch (var0) {
            case 0 -> n;
            case 1 -> r;
            case 2 -> H;
            default -> D;
         };
      }

      int winrt() {
         return switch (this) {
            case r -> 1;
            case H -> 2;
            default -> 0;
         };
      }
   }

   public static enum TypeG {
      V,
      Y,
      L,
      g,
      t,
      z;

      private static final long a = 3948966049189199878L;

      static SpotifyData.TypeG of(int var0) {
         SpotifyData.TypeG[] var1 = values();
         return var0 >= 0 && var0 < var1.length ? var1[var0] : V;
      }
   }
}
