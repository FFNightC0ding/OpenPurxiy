package dev.purxiy.modules.spotify;

public sealed interface SpotifyApi
   permits SpotifyApi.Data4,
   SpotifyApi.Data7,
   SpotifyApi.Data,
   SpotifyApi.Data2,
   SpotifyApi.Data9,
   SpotifyApi.Data5,
   SpotifyApi.Data8,
   SpotifyApi.Data6,
   SpotifyApi.Data3,
   SpotifyApi.Data10 {
   public record Data() implements SpotifyApi {
   }

   public record Data10(boolean S) implements SpotifyApi {
      public boolean muted() {
         return this.S;
      }

      public boolean booleanState41() {
         return this.S;
      }
   }

   public record Data2() implements SpotifyApi {
   }

   public record Data3(float v) implements SpotifyApi {
      public float level() {
         return this.v;
      }

      public float floatState30() {
         return this.v;
      }
   }

   public record Data4() implements SpotifyApi {
   }

   public record Data5(long q) implements SpotifyApi {
      public long positionMs() {
         return this.q;
      }

      public long longState42() {
         return this.q;
      }
   }

   public record Data6(SpotifyData.TypeD Z) implements SpotifyApi {
      public SpotifyData.TypeD mode() {
         return this.Z;
      }

      public SpotifyData.TypeD mediaPlaybackSnapshot_PlaybackRepeatModeState8() {
         return this.Z;
      }
   }

   public record Data7() implements SpotifyApi {
   }

   public record Data8(boolean y) implements SpotifyApi {
      public boolean on() {
         return this.y;
      }

      public boolean booleanState16() {
         return this.y;
      }
   }

   public record Data9() implements SpotifyApi {
   }
}
