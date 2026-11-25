package dev.purxiy.modules.spotify;

import java.util.List;

public final class SpotifySupport3 {
   public static final SpotifySupport3 f = new SpotifySupport3(SpotifySupport3.TypeK.H, List.of(), "");
   public static final SpotifySupport3 Q = new SpotifySupport3(SpotifySupport3.TypeK.I, List.of(), "");
   private final SpotifySupport3.TypeK g;
   private final List<SpotifySupport3.Data> M;
   private final String S;
   float X = -1.0F;
   public static boolean y;

   public SpotifySupport3(SpotifySupport3.TypeK var1, List<SpotifySupport3.Data> var2, String var3) {
      this.g = var1;
      this.M = List.copyOf(var2);
      this.S = var3;
   }

   public static SpotifySupport3 unsynced(String var0) {
      return new SpotifySupport3(SpotifySupport3.TypeK.g, List.of(), var0);
   }

   public SpotifySupport3.TypeK kind() {
      return this.g;
   }

   public boolean synced() {
      return this.g == SpotifySupport3.TypeK.k && !this.M.isEmpty();
   }

   public boolean wordTimed() {
      int var1 = 0;
      byte var2 = 0;

      for (SpotifySupport3.Data var4 : this.M) {
         if (!var4.isBreak()) {
            var1++;
            var2 = (byte)(var2 + (var4.wordTimed() ? 1 : 0));
         }
      }

      return var1 > 0 && var2 * 2 >= var1;
   }

   public List<SpotifySupport3.Data> lines() {
      return this.M;
   }

   public String plain() {
      return this.S;
   }

   public int indexAt(long var1) {
      int var3 = 0;
      int var4 = this.M.size() - 1;
      int var5 = -1;

      while (var3 <= var4) {
         int var6 = var3 + var4 >>> 1;
         if (this.M.get(var6).startMs() <= var1) {
            var5 = var6;
            var3 = var6 + 1;
         } else {
            var4 = var6 - 1;
         }
      }

      return var5;
   }

   public long nextStart(int var1, long var2) {
      return var1 + 1 < this.M.size() ? this.M.get(var1 + 1).startMs() : var2;
   }

   public record Data(long k, String q, List<SpotifySupport3.Data2> p, long Y) {
      public Data(long var1, String var3, List<SpotifySupport3.Data2> var4) {
         this(var1, var3, var4, -1L);
      }

      public boolean isBreak() {
         return this.q.isBlank();
      }

      public boolean wordTimed() {
         return !this.p.isEmpty();
      }

      public boolean hasEnd() {
         return this.Y > this.k;
      }

      public long startMs() {
         return this.k;
      }

      public String text() {
         return this.q;
      }

      public List<SpotifySupport3.Data2> words() {
         return this.p;
      }

      public long endMs() {
         return this.Y;
      }

      public long longState33() {
         return this.k;
      }

      public String stringState17() {
         return this.q;
      }

      public List<SpotifySupport3.Data2> listState4() {
         return this.p;
      }

      public long longState30() {
         return this.Y;
      }
   }

   public record Data2(long w, String E, long r) {
      public Data2(long var1, String var3) {
         this(var1, var3, -1L);
      }

      public boolean hasEnd() {
         return this.r > this.w;
      }

      public long startMs() {
         return this.w;
      }

      public String text() {
         return this.E;
      }

      public long endMs() {
         return this.r;
      }

      public long longState7() {
         return this.w;
      }

      public String stringState33() {
         return this.E;
      }

      public long longState53() {
         return this.r;
      }
   }

   public static enum TypeK {
      k,
      g,
      I,
      H;
   }
}
