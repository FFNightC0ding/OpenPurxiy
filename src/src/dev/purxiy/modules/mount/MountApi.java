// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

public sealed interface MountApi
   permits MountApi.Data4,
   MountApi.Data6,
   MountApi.Data3,
   MountApi.Data2,
   MountApi.Data5,
   MountApi.Data9,
   MountApi.Data8,
   MountApi.Data,
   MountApi.Data7,
   MountApi.Data10 {
   public record Data(MountData.TypeQ s) implements MountApi {
      public MountData.TypeQ mode() {
         return this.s;
      }

      public MountData.TypeQ mediaPlaybackSnapshot_PlaybackRepeatModeState1() {
         return this.s;
      }
   }

   public record Data10(boolean e) implements MountApi {
      public boolean muted() {
         return this.e;
      }

      public boolean booleanState30() {
         return this.e;
      }
   }

   public record Data2() implements MountApi {
   }

   public record Data3() implements MountApi {
   }

   public record Data4() implements MountApi {
   }

   public record Data5() implements MountApi {
   }

   public record Data6() implements MountApi {
   }

   public record Data7(float H) implements MountApi {
      public float level() {
         return this.H;
      }

      public float floatState7() {
         return this.H;
      }
   }

   public record Data8(boolean V) implements MountApi {
      public boolean on() {
         return this.V;
      }

      public boolean booleanState25() {
         return this.V;
      }
   }

   public record Data9(long H) implements MountApi {
      public long positionMs() {
         return this.H;
      }

      public long longState55() {
         return this.H;
      }
   }
}
