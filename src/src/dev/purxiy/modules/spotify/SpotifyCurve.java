// Sourced By DevFFNightmare
package dev.purxiy.modules.spotify;

import dev.purxiy.render.texture.TextureLoader;
import dev.purxiy.ui.Icons;

public final class SpotifyCurve {
   private static final long r;
   final boolean V;
   
   SpotifyCurve.Data O;
   double R;
   boolean b;
   boolean I;
   SpotifyData.TypeD M = SpotifyData.TypeD.n;
   int k;
   float S = -1.0F;
   boolean g;
   float A;
   final float[] s = new float[4];
   SpotifySupport3 f;
   SpotifyCurve.TypeF z;
   int h;
   int W;
   int U;
   long u;
   
   final SpotifyUpdater3 Y;
   final SpotifyUpdater l;
   int j;
   long D;
   int o;
   long C;
   
   SpotifyData.TypeD p;
   long d;
   float B;
   long G;
   float t;
   long L;
   public static boolean v;

   SpotifyCurve(boolean var1,  SpotifyUpdater3 var2) {
      this.f = SpotifySupport3.f;
      this.z = SpotifyCurve.TypeF.i;
      this.W = 1;
      this.U = 1;
      this.l = new SpotifyUpdater();
      this.j = -1;
      this.o = -1;
      this.B = -1.0F;
      this.t = -1.0F;
      this.V = var1;
      this.Y = var2;
   }

   public boolean sample() {
      return this.V;
   }

   
   public SpotifyCurve.Data track() {
      return this.O;
   }

   public double position() {
      return this.R;
   }

   public double lyricsTime() {
      return this.R - SpotifyRegistry.timingMillis();
   }

   public long duration() {
      return this.O == null ? 0L : this.O.durationMs();
   }

   public boolean playing() {
      return this.b;
   }

   public boolean shuffle() {
      return this.I;
   }

   public SpotifyData.TypeD repeat() {
      return this.M;
   }

   public boolean can(int var1) {
      return (this.k & var1) != 0;
   }

   public float volume() {
      return this.S;
   }

   public boolean muted() {
      return this.g;
   }

   public float level() {
      return this.A;
   }

   public float bar(int var1) {
      return this.s[var1];
   }

   public SpotifySupport3 lyrics() {
      return this.f;
   }

   public SpotifyCurve.TypeF lyricsState() {
      return this.z;
   }

   public int skips() {
      return this.h;
   }

   public int skipDirection() {
      return this.W;
   }

   
   public TextureLoader art() {
      return this.Y == null ? null : this.Y.current();
   }

   
   public TextureLoader previousArt() {
      return this.Y == null ? null : this.Y.previous();
   }

   public float artFade() {
      return this.Y == null ? 1.0F : this.Y.fade();
   }

   public int accent() {
      return this.Y == null ? Icons.PURPLE : this.Y.accent();
   }

   public void togglePlay() {
      if (this.O != null) {
         boolean var1 = !this.b;
         this.b = var1;
         if (!this.V) {
            this.j = var1 ? 1 : 0;
            this.D = System.nanoTime() + 1500000000L;
            SpotifyFactory.get().send((SpotifyApi)(this.can(128) ? new SpotifyApi.Data() : (var1 ? new SpotifyApi.Data4() : new SpotifyApi.Data7())));
         }
      }
   }

   public void next() {
      if (this.V) {
         this.h++;
         this.W = 1;
         this.R = 0.0;
      } else {
         this.U = 1;
         this.u = System.nanoTime();
         SpotifyFactory.get().send(new SpotifyApi.Data2());
      }
   }

   public void previous() {
      if (this.V) {
         this.h++;
         this.W = -1;
         this.R = 0.0;
      } else {
         this.U = -1;
         this.u = System.nanoTime();
         SpotifyFactory.get().send(new SpotifyApi.Data9());
      }
   }

   public void seek(double var1) {
      if (this.O != null) {
         double var3 = Math.max(0.0, Math.min((double)Math.max(0L, this.duration() - 250L), var1));
         this.R = var3;
         if (!this.V) {
            this.l.seek(var3, System.nanoTime());
            SpotifyFactory.get().send(new SpotifyApi.Data5((long)var3));
         }
      }
   }

   public void setVolume(float var1) {
      float var2 = Math.max(0.0F, Math.min(1.0F, var1));
      this.S = var2;
      if (this.g && var2 > 0.0F) {
         this.toggleMute();
      }

      if (!this.V) {
         this.B = var2;
         this.G = System.nanoTime() + 1500000000L;
         this.t = var2;
      }
   }

   public void toggleMute() {
      this.g = !this.g;
      if (!this.V) {
         SpotifyFactory.get().send(new SpotifyApi.Data10(this.g));
      }
   }

   public void toggleShuffle() {
      this.I = !this.I;
      if (!this.V) {
         this.o = this.I ? 1 : 0;
         this.C = System.nanoTime() + 1500000000L;
         SpotifyFactory.get().send(new SpotifyApi.Data8(this.I));
      }
   }

   public void cycleRepeat() {
      this.M = switch (SpotifyCurve.TypeL.s[this.M.ordinal()]) {
         case 1, 2 -> SpotifyData.TypeD.H;
         case 3 -> SpotifyData.TypeD.r;
         case 4 -> SpotifyData.TypeD.n;
         default -> throw new MatchException(null, null);
      };
      if (!this.V) {
         this.p = this.M;
         this.d = System.nanoTime() + 1500000000L;
         SpotifyFactory.get().send(new SpotifyApi.Data6(this.M));
      }
   }

   public static void ensureSourceClassInitialized() {
   }

   static {
      long var0 = 17282081266114L;
      long var2 = 17281156953794L ^ var0;
      r = var2;
   }

   public record Data(String Y, String K, String z, String q, long W) {
      public String key() {
         return this.Y;
      }

      public String title() {
         return this.K;
      }

      public String artist() {
         return this.z;
      }

      public String album() {
         return this.q;
      }

      public long durationMs() {
         return this.W;
      }

      public String stringState8() {
         return this.Y;
      }

      public String stringState14() {
         return this.K;
      }

      public String stringState50() {
         return this.z;
      }

      public String stringState17() {
         return this.q;
      }

      public long longState16() {
         return this.W;
      }
   }

   public static enum TypeF {
      i,
      h,
      Z;

}

   static class TypeL {
      static final int[] s = new int[SpotifyData.TypeD.values().length];

      static {
         try {
            s[SpotifyData.TypeD.n.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
         }

         try {
            s[SpotifyData.TypeD.D.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
         }

         try {
            s[SpotifyData.TypeD.H.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
         }

         try {
            s[SpotifyData.TypeD.r.ordinal()] = 4;
         } catch (NoSuchFieldError var1) {
         }
      }
   }
}
