package dev.purxiy.modules.mount;

public record MountData(String b, long k, String B, String N, String l, long H, long e, long X, MountData.MountType a, int s, MountData.MountCategory O, int z) {
   public static final int r = 1;
   public static int t = 2;
   public static int J = 4;
   public static int R = 8;
   public static int K = 16;
   public static int q = 32;
   public static int w = 64;
   public static int Z = 128;

   public boolean playing() {
      return this.a == MountData.MountType.w;
   }

   public boolean can(int var1) {
      return (this.z & var1) != 0;
   }

   public long positionAt(long var1) {
      long var3 = this.e;
      if (this.playing()) {
         var3 += (var1 - this.X) / 1000000L;
      }

      return this.H > 0L ? Math.max(0L, Math.min(this.H, var3)) : Math.max(0L, var3);
   }

   public boolean sameTrack(MountData var1) {
      return var1 != null && this.k == var1.k;
   }

   public String app() {
      return this.b;
   }

   public long track() {
      return this.k;
   }

   public String title() {
      return this.B;
   }

   public String artist() {
      return this.N;
   }

   public String album() {
      return this.l;
   }

   public long durationMs() {
      return this.H;
   }

   public long positionMs() {
      return this.e;
   }

   public long anchorNanos() {
      return this.X;
   }

   public MountData.MountType status() {
      return this.a;
   }

   public int shuffle() {
      return this.s;
   }

   public MountData.MountCategory repeat() {
      return this.O;
   }

   public int controls() {
      return this.z;
   }

   public String stringState5() {
      return this.b;
   }

   public long longState33() {
      return this.k;
   }

   public String stringState49() {
      return this.B;
   }

   public String stringState53() {
      return this.N;
   }

   public String stringState23() {
      return this.l;
   }

   public long longState55() {
      return this.H;
   }

   public long longState4() {
      return this.e;
   }

   public long longState39() {
      return this.X;
   }

   public MountData.MountType mediaPlaybackSnapshot_PlaybackStatusState1() {
      return this.a;
   }

   public int integerState13() {
      return this.s;
   }

   public MountData.MountCategory mediaPlaybackSnapshot_PlaybackRepeatModeState2() {
      return this.O;
   }

   public int integerState19() {
      return this.z;
   }

   public static enum MountType {
      k,
      q,
      M,
      c,
      w,
      T;

      private static final long a = -8291181520203284474L;

      static MountData.MountType of(int var0) {
         MountData.MountType[] var1 = values();
         return var0 >= 0 && var0 < var1.length ? var1[var0] : k;
      }
   }

   public static enum MountCategory {
      C,
      W,
      Q,
      w;

      static MountData.MountCategory of(int var0) {
         return switch (var0) {
            case 0 -> C;
            case 1 -> W;
            case 2 -> Q;
            default -> w;
         };
      }

      int winrt() {
         return switch (this) {
            case W -> 1;
            case Q -> 2;
            default -> 0;
         };
      }
   }
}
