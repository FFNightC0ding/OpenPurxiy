package dev.purxiy.modules.mount;

public sealed interface MountApi
   permits MountApi.Play,
   MountApi.Pause,
   MountApi.TogglePlayPause,
   MountApi.SkipNext,
   MountApi.SkipPrevious,
   MountApi.Seek,
   MountApi.SetShuffle,
   MountApi.Data,
   MountApi.SetVolume,
   MountApi.SetMuted {
   public record Data(MountData.MountCategory s) implements MountApi {
      public MountData.MountCategory mode() {
         return this.s;
      }

      public MountData.MountCategory mediaPlaybackSnapshot_PlaybackRepeatModeState1() {
         return this.s;
      }
   }

   public record SetMuted(boolean e) implements MountApi {
      public boolean muted() {
         return this.e;
      }

      public boolean booleanState30() {
         return this.e;
      }
   }

   public record SkipNext() implements MountApi {
   }

   public record TogglePlayPause() implements MountApi {
   }

   public record Play() implements MountApi {
   }

   public record SkipPrevious() implements MountApi {
   }

   public record Pause() implements MountApi {
   }

   public record SetVolume(float H) implements MountApi {
      public float level() {
         return this.H;
      }

      public float floatState7() {
         return this.H;
      }
   }

   public record SetShuffle(boolean V) implements MountApi {
      public boolean on() {
         return this.V;
      }

      public boolean booleanState25() {
         return this.V;
      }
   }

   public record Seek(long H) implements MountApi {
      public long positionMs() {
         return this.H;
      }

      public long longState55() {
         return this.H;
      }
   }
}
