package dev.purxiy.modules.spotify;

import dev.purxiy.loader.fabric.ClientServices;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public final class SpotifyFactory {
   private static final long V = 4000000000L;
   private static final SpotifyFactory r = new SpotifyFactory(69253021031740L);
   private final boolean d = System.getProperty("os.name", "").toLowerCase(Locale.ROOT).startsWith("windows");
   private final ConcurrentLinkedQueue<SpotifyApi> M = new ConcurrentLinkedQueue<>();
   private final ConcurrentLinkedQueue<SpotifyApi> E = new ConcurrentLinkedQueue<>();
   private final Object J = new Object();
   private volatile SpotifyData e;
   private volatile SpotifyFactory.Data s;
   private volatile SpotifyFactory.TypeQ F = SpotifyFactory.TypeQ.o;
   private volatile boolean X = true;
   private volatile long n;
   private volatile long C;
   private volatile float o = -1.0F;
   private volatile boolean R;
   private volatile float h;
   private volatile String W;
   private Consumer<String> b;
   private Thread p;
   private Thread U;
   private volatile boolean G;
   private static final long[] f = new long[]{
      7281235989989821407L,
      6425409607920651794L,
      3797656662200423075L,
      2947490495731952877L,
      7542087596953292333L,
      4446249569589344597L,
      4555088338809889626L,
      2796085792521838530L,
      4115394093771611222L,
      7256903246627462600L,
      7668132663444729874L,
      3498667547222121672L,
      7759554846917961347L,
      1165722402113175543L,
      4980323863769188889L,
      7751005173742213879L
   };
   private static final Long[] g = new Long[16];

   private SpotifyFactory(long var1) {
   }

   public static SpotifyFactory get() {
      return r;
   }

   public boolean supported() {
      return this.d;
   }

   public SpotifyFactory.TypeQ health() {
      return this.d ? this.F : SpotifyFactory.TypeQ.E;
   }

   public void logTo(Consumer<String> var1) {
      this.b = var1;
   }

   public void spotifyOnly(boolean var1) {
      this.X = var1;
   }

   public void keepAlive(boolean var1) {
      long var2 = 134734521107203L;
      long var4 = var2 ^ 113837512434593L;
      if (!this.G && this.d) {
         long var6 = System.nanoTime();
         boolean var8 = var6 > this.n;
         this.n = var6 + 4000000000L;
         if (var1) {
            this.C = var6 + 4000000000L;
         }

         if (this.p == null) {
            this.q(var4);
         } else if (var8) {
            synchronized (this.J) {
               this.J.notifyAll();
            }
         }
      }
   }

   public SpotifyData state() {
      return this.e;
   }

   public SpotifyFactory.Data artwork() {
      return this.s;
   }

   public float volume() {
      return this.o;
   }

   public boolean muted() {
      return this.R;
   }

   public float peak() {
      return this.h;
   }

   public String lastError() {
      return this.W;
   }

   public void send(SpotifyApi var1) {
      if (!this.G && this.d) {
         if (!(var1 instanceof SpotifyApi.Data3) && !(var1 instanceof SpotifyApi.Data10)) {
            this.M.add(var1);
         } else {
            this.E.add(var1);
         }

         synchronized (this.J) {
            this.J.notifyAll();
         }
      }
   }

   private synchronized void q(long var1) {
      SpotifyCurve.ensureSourceClassInitialized();
      long var3 = 31896829993122L;
      int var5 = var3 < 0L ? -1 : 1;
      if (this.p == null) {
         this.p = ClientServices.visualWorker(() -> this.j(117582736866384L), "Purxiy Media");
         this.p.setDaemon(true);
         this.p.start();
         this.U = ClientServices.visualWorker(() -> this.s(3610, 15018, (short)-20989), "Purxiy Audio");
         this.U.setDaemon(true);
         this.U.start();
      }
   }

   private boolean v(long var1) {
      SpotifyCurve.ensureSourceClassInitialized();
      return System.nanoTime() < this.n;
   }

   private void y(long var1, long var3) {
      long var5 = 82343596743169L;
      SpotifyCurve.ensureSourceClassInitialized();
      Object var7 = this.J;
      synchronized (var7) {
         try {
            if (this.M.isEmpty() || !this.v(var5)) {
               this.J.wait(this.v(var5) ? var1 : 1000L);
            }
         } catch (InterruptedException var11) {
            Thread.currentThread().interrupt();
         }
      }
   }

   private void j(long var1) {
      long var3 = 20755875941491L;
      long var5 = 56161253748480L;
      long var7 = 82343596743169L;
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifySupport5 var9 = new SpotifySupport5();
      boolean var10 = false;

      try {
         var10 = true;
         long var11 = 0L;
         long var13 = 0L;

         while (!Thread.currentThread().isInterrupted()) {
            long var15 = 117582736866384L;
            int var18 = var15 < 0L ? -1 : 1;
            if (!this.v(var7)) {
               long var38 = 117582736866383L;
               int var40 = var38 < 0L ? -1 : 1;
               this.e = null;
               long var40x = 117582736866384L;
               int var44 = var40x < 0L ? -1 : 1;
               if (var9.open()) {
                  var9.close();
               }

               this.y(1000L, var3);
               long var43 = 117582736866384L;
               int var48 = var43 < 0L ? -1 : 1;
            } else {
               long var18x = System.nanoTime();

               try {
                  if (!var9.open()) {
                     long var20;
                     int var22 = (var20 = var18x - var13) == 0L ? 0 : (var20 < 0L ? -1 : 1);
                     long var23 = 117582736866383L;
                     int var26 = var23 < 0L ? -1 : 1;
                     if (var22 < 0) {
                        long var44 = 117582736866383L;
                        int var261 = var44 < 0L ? -1 : 1;
                        this.y(250L, var3);
                        continue;
                     }

                     var9.start();
                     this.F = SpotifyFactory.TypeQ.I;
                  }

                  boolean var39;
                  SpotifyApi var21;
                  for (var39 = false; (var21 = this.M.poll()) != null; var39 = true) {
                     long var41 = 117582736866383L;
                     int var25 = var41 < 0L ? -1 : 1;
                     var9.send(var21);
                  }

                  long var42 = 117582736866383L;
                  int var46 = var42 < 0L ? -1 : 1;
                  if (var39) {
                     var11 = var18x + 1500000000L;
                  }

                  SpotifyData var25 = var9.poll(this.X, var39);
                  byte[] var26 = var9.U;
                  if (var26 != null && var25 != null) {
                     var9.U = null;
                     this.s = new SpotifyFactory.Data(var25.track(), var26);
                  }

                  long var27 = 117582736866384L;
                  int var27x = var27 < 0L ? -1 : 1;
                  this.e = var25;
                  this.W = null;
                  long var30x = 117582736866384L;
                  int var29 = var30x < 0L ? -1 : 1;
                  this.y(var18x < var11 ? 30L : (var25 == null ? 500L : 50L), var3);
               } catch (Throwable var33) {
                  this.L("media", var5, var33);
                  this.e = null;
                  var9.close();
                  this.F = SpotifyFactory.TypeQ.u;
                  var13 = System.nanoTime() + 5000000000L;
                  this.y(1000L, var3);
               }
            }
         }

         var10 = false;
      } finally {
         if (var10) {
            var9.close();
            SpotifyCache.endThread();
         }
      }

      long var37 = 117582736866384L;
      int var36 = var37 < 0L ? -1 : 1;
      var9.close();
      SpotifyCache.endThread();
   }

   private void s(int var1, int var2, short var3) {
      long var4 = 15505816202755L;
      long var6 = var4 ^ 67131232141571L;
      long var8 = var4 ^ 75858358301698L;
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifySupport8 var10 = new SpotifySupport8();
      boolean var11 = false;

      try {
         var11 = true;
         long var12 = 0L;
         long var14 = 0L;
         String var16 = "";

         while (!Thread.currentThread().isInterrupted()) {
            short var17 = 3610;
            if (!this.v(var8)) {
               this.o = -1.0F;
               var17 = 15018;
               this.h = 0.0F;
               short var36 = -20989;
               if (var10.open()) {
                  var10.close();
               }

               this.Y(1000L);
               var36 = -20989;
            } else {
               long var18 = System.nanoTime();

               try {
                  if (!var10.open()) {
                     var10.start();
                  }

                  SpotifyData var20;
                  label181: {
                     var20 = this.e;
                     String var21 = var20 == null ? "" : var20.app();
                     short var22 = -20989;
                     var22 = 15018;
                     if (var21.equals(var16)) {
                        var17 = 15018;
                        if (var18 <= var12 && (!var10.found() || var10.alive())) {
                           break label181;
                        }
                     }

                     var16 = var21;
                     var10.find(executables(var21));
                     boolean var23 = var10.found();
                     var17 = -20989;
                     var12 = var18 + (var23 ? 10000000000L : 2000000000L);
                     var14 = 0L;
                  }

                  SpotifyApi var38;
                  for (; (var38 = this.E.poll()) != null; var14 = var18 + 300000000L) {
                     var17 = -20989;
                     var17 = 15018;
                     if (var38 instanceof SpotifyApi.Data3 var40) {
                        var10.setVolume(var40.level());
                        this.o = Math.max(0.0F, Math.min(1.0F, var40.level()));
                     } else {
                        boolean var42 = var38 instanceof SpotifyApi.Data10;
                        if (var42) {
                           SpotifyApi.Data10 var24 = (SpotifyApi.Data10)var38;
                           var10.setMuted(var24.muted());
                           this.R = var24.muted();
                        }
                     }
                  }

                  if (var18 > var14) {
                     var14 = var18 + 250000000L;
                     this.o = var10.volume();
                     this.R = var10.muted();
                  }

                  boolean var41 = var18 < this.C && var20 != null && var20.playing();
                  this.h = var41 ? var10.peak() : 0.0F;
                  var17 = -20989;
                  this.Y(var41 ? 16L : 100L);
               } catch (Throwable var281) {
                  this.L("audio", var6, var281);
                  var10.close();
                  this.o = -1.0F;
                  this.h = 0.0F;
                  this.Y(5000L);
               }
            }
         }

         var11 = false;
      } finally {
         if (var11) {
            var10.close();
            SpotifyCache.endThread();
         }
      }

      var10.close();
      SpotifyCache.endThread();
   }

   static Set<String> executables(String var0) {
      HashSet var1 = new HashSet();
      if (var0.isEmpty()) {
         return var1;
      } else {
         String var2 = var0.toLowerCase(Locale.ROOT);
         if (SpotifySupport5.isSpotify(var2)) {
            var1.add("spotify.exe");
         }

         if (var2.endsWith(".exe")) {
            var1.add(var2.substring(Math.max(var2.lastIndexOf(92), var2.lastIndexOf(47)) + 1));
         }

         int var3 = var2.lastIndexOf(33);
         var1.add((var3 >= 0 ? var2.substring(var3 + 1) : var2) + ".exe");
         return var1;
      }
   }

   private void Y(long var1) {
      try {
         Thread.sleep(var1);
      } catch (InterruptedException var4) {
         Thread.currentThread().interrupt();
      }
   }

   private void L(String var1, long var2, Throwable var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      String var5 = var1 + ": " + var4;
      long var6 = 56161253748480L;
      int var8 = var6 < 0L ? -1 : 1;
      if (!var5.equals(this.W)) {
         this.W = var5;
         if (this.b != null) {
            this.b.accept(var5);
         }
      }
   }

   void closeOwned() {
      this.G = true;
      this.n = this.C = 0L;
      this.M.clear();
      this.E.clear();
      if (this.p != null) {
         this.p.interrupt();
      }

      if (this.U != null) {
         this.U.interrupt();
      }

      synchronized (this.J) {
         this.J.notifyAll();
      }

      for (Thread var4 : new Thread[]{this.p, this.U}) {
         if (var4 != null) {
            try {
               var4.join(6000L);
            } catch (InterruptedException var6) {
               Thread.currentThread().interrupt();
               throw new IllegalStateException("Media shutdown interrupted");
            }

            if (var4.isAlive()) {
               throw new IllegalStateException("Media worker did not stop");
            }
         }
      }

      this.p = this.U = null;
      this.e = null;
      this.s = null;
      this.b = null;
   }

   private static long a(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 5917) & 32767;
      if (g[var3] == null) {
         g[var3] = f[var3] ^ var1;
      }

      return g[var3];
   }

   public record Data(long Y, byte[] v) {
      public long track() {
         return this.Y;
      }

      public byte[] data() {
         return this.v;
      }

      public long longState30() {
         return this.Y;
      }

      public byte[] byteArrayState14() {
         return this.v;
      }
   }

   public static enum TypeQ {
      o,
      I,
      u,
      E;
   }
}
