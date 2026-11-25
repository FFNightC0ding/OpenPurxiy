package dev.purxiy.modules.spotify;

public sealed interface SpotifyApi
   permits SpotifyApi.Play,
   SpotifyApi.Pause,
   SpotifyApi.Data,
   SpotifyApi.SkipNext,
   SpotifyApi.SkipPrevious,
   SpotifyApi.Seek,
   SpotifyApi.SetShuffle,
   SpotifyApi.SetRepeat,
   SpotifyApi.SetVolume,
   SpotifyApi.SetMuted {
   public record Data() implements SpotifyApi {
   }

   public record SetMuted(boolean S) implements SpotifyApi {
      public boolean muted() {
         return this.S;
      }

      public boolean booleanState41() {
         return this.S;
      }
   }

   public record SkipNext() implements SpotifyApi {
   }

   public record SetVolume(float v) implements SpotifyApi {
      public float level() {
         return this.v;
      }

      public float floatState30() {
         return this.v;
      }
   }

   public record Play() implements SpotifyApi {
   }

   public record Seek(long q) implements SpotifyApi {
      public long positionMs() {
         return this.q;
      }

      public long longState42() {
         return this.q;
      }
   }

   public record SetRepeat(SpotifyData.PlaybackState Z) implements SpotifyApi {
      public SpotifyData.PlaybackState mode() {
         return this.Z;
      }

      public SpotifyData.PlaybackState mediaPlaybackSnapshot_PlaybackRepeatModeState8() {
         return this.Z;
      }
   }

   public record Pause() implements SpotifyApi {
   }

   public record SetShuffle(boolean y) implements SpotifyApi {
      public boolean on() {
         return this.y;
      }

      public boolean booleanState16() {
         return this.y;
      }
   }

   public record SkipPrevious() implements SpotifyApi {
   }
}
