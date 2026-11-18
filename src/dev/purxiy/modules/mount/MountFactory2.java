// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

import dev.purxiy.loader.fabric.ClientServices;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;

public final class MountFactory2 {
   private static final long H = 4000000000L;
   private static final MountFactory2 W = new MountFactory2(84825825883789L);
   private final boolean i = System.getProperty("os.name", "").toLowerCase(Locale.ROOT).startsWith("windows");
   private final ConcurrentLinkedQueue<MountApi> j = new ConcurrentLinkedQueue<>();
   private final ConcurrentLinkedQueue<MountApi> Q = new ConcurrentLinkedQueue<>();
   private final Object b = new Object();
   
   private volatile MountData w;
   
   private volatile MountFactory2.Data M;
   private volatile MountFactory2.TypeB t = MountFactory2.TypeB.s;
   private volatile boolean x = true;
   private volatile long s;
   private volatile long Z;
   private volatile float G = -1.0F;
   private volatile boolean l;
   private volatile float d;
   
   private volatile String p;
   
   private Consumer<String> k;
   private Thread X;
   private Thread r;
   private volatile boolean h;


   private static final long[] e = new long[]{
      2675696664698207798L,
      41694574989999856L,
      6824008086150977729L,
      9075296400706753874L,
      7425108049873208955L,
      7201811236683473222L,
      5183152680248639188L,
      3748941750727155463L,
      4029548360148486380L,
      5211133583686438582L,
      5764391570200068041L,
      2735006286218828129L,
      1945758231275839268L,
      2183076473397151118L,
      4323884445379466612L,
      9127360150799245153L
   };
   private static final Long[] f = new Long[16];

   private MountFactory2(long var1) {
   }

   public static MountFactory2 get() {
      return W;
   }

   public boolean supported() {
      return this.i;
   }

   public MountFactory2.TypeB health() {
      return this.i ? this.t : MountFactory2.TypeB.n;
   }

   public void logTo(Consumer<String> var1) {
      this.k = var1;
   }

   public void spotifyOnly(boolean var1) {
      this.x = var1;
   }

   public void keepAlive(boolean var1) {
      long var2 = 53581395319353L;
      long var4 = var2 ^ 81269352161365L;
      if (!this.h && this.i) {
         long var6 = System.nanoTime();
         boolean var8 = var6 > this.s;
         this.s = var6 + 4000000000L;
         if (var1) {
            this.Z = var6 + 4000000000L;
         }

         if (this.X == null) {
            this.b(var4);
         } else if (var8) {
            synchronized (this.b) {
               this.b.notifyAll();
            }
         }
      }
   }

   
   public MountData state() {
      return this.w;
   }

   
   public MountFactory2.Data artwork() {
      return this.M;
   }

   public float volume() {
      return this.G;
   }

   public boolean muted() {
      return this.l;
   }

   public float peak() {
      return this.d;
   }

   
   public String lastError() {
      return this.p;
   }

   public void send(MountApi var1) {
      if (!this.h && this.i) {
         if (!(var1 instanceof MountApi.Data7) && !(var1 instanceof MountApi.Data10)) {
            this.j.add(var1);
         } else {
            this.Q.add(var1);
         }

         synchronized (this.b) {
            this.b.notifyAll();
         }
      }
   }

   private synchronized void b(long var1) {
      MountFactory.Data7.ensureSourceClassInitialized();
      long var3 = 133395691944556L;
      if (133395691944556L == 0L) {
         boolean var10000 = false;
      } else {
         int var6 = var3 < 0L ? -1 : 1;
      }

      if (this.X == null) {
         this.X = ClientServices.visualWorker(() -> this.p(65196982915496L), "Purxiy Media");
         this.X.setDaemon(true);
         this.X.start();
         this.r = ClientServices.visualWorker(() -> this.d(126414252626246L), "Purxiy Audio");
         this.r.setDaemon(true);
         this.r.start();
      }
   }

   private boolean i(long var1) {
      MountFactory.Data7.ensureSourceClassInitialized();
      return System.nanoTime() < this.s;
   }

   private void K(long var1, long var3) {
      long var5 = 106687388799002L;
      MountFactory.Data7.ensureSourceClassInitialized();
      Object var7 = this.b;
      synchronized (var7) {
         try {
            if (this.j.isEmpty() || !this.i(var5)) {
               this.b.wait(this.i(var5) ? var1 : 1000L);
            }
         } catch (InterruptedException var11) {
            Thread.currentThread().interrupt();
         }
      }
   }

   private void p(long var1) {
      long var3 = 106687388799002L;
      long var5 = 23614L;
      int var7 = -237854869;
      long var8 = 132495795898084L;
      MountFactory.Data7.ensureSourceClassInitialized();
      MountSupport5 var10 = new MountSupport5();
      boolean var11 = false;

      try {
         var11 = true;
         long var12 = 0L;
         long var14 = 0L;

         while (!Thread.currentThread().isInterrupted()) {
            long var16 = 65196982915496L;
            if (65196982915496L == 0L) {
               boolean var10000 = false;
            } else {
               int var46 = var16 < 0L ? -1 : 1;
            }

            if (!this.i(var3)) {
               long var39 = 65196982915496L;
               if (65196982915496L == 0L) {
                  boolean var59 = false;
               } else {
                  int var60 = var39 < 0L ? -1 : 1;
               }

               this.w = null;
               long var41 = 65196982915495L;
               if (65196982915495L == 0L) {
                  boolean var61 = false;
               } else {
                  int var62 = var41 < 0L ? -1 : 1;
               }

               if (var10.open()) {
                  var10.close();
               }

               this.K(1000L, var8);
               long var44 = 65196982915496L;
               if (65196982915496L == 0L) {
                  boolean var63 = false;
               } else {
                  int var64 = var44 < 0L ? -1 : 1;
               }
            } else {
               long var19 = System.nanoTime();

               try {
                  if (!var10.open()) {
                     long var21;
                     int var23 = (var21 = var19 - var14) == 0L ? 0 : (var21 < 0L ? -1 : 1);
                     long var24 = 65196982915495L;
                     if (65196982915495L == 0L) {
                        boolean var47 = false;
                     } else {
                        int var48 = var24 < 0L ? -1 : 1;
                     }

                     if (var23 < 0) {
                        long var45 = 65196982915495L;
                        if (65196982915495L == 0L) {
                           boolean var57 = false;
                        } else {
                           int var58 = var45 < 0L ? -1 : 1;
                        }

                        this.K(250L, var8);
                        continue;
                     }

                     var10.start();
                     this.t = MountFactory2.TypeB.i;
                  }

                  MountApi var22;
                  boolean var40;
                  for (var40 = false; (var22 = this.j.poll()) != null; var40 = true) {
                     var10.send(var22);
                     long var42 = 65196982915495L;
                     if (65196982915495L == 0L) {
                        boolean var49 = false;
                     } else {
                        int var50 = var42 < 0L ? -1 : 1;
                     }
                  }

                  long var43 = 65196982915495L;
                  if (65196982915495L == 0L) {
                     boolean var51 = false;
                  } else {
                     int var52 = var43 < 0L ? -1 : 1;
                  }

                  if (var40) {
                     var12 = var19 + 1500000000L;
                  }

                  MountData var26 = var10.poll(this.x, var40);
                  byte[] var27 = var10.G;
                  if (var27 != null && var26 != null) {
                     var10.G = null;
                     this.M = new MountFactory2.Data(var26.track(), var27);
                  }

                  long var28 = 65196982915496L;
                  if (65196982915496L == 0L) {
                     boolean var53 = false;
                  } else {
                     int var54 = var28 < 0L ? -1 : 1;
                  }

                  this.w = var26;
                  this.p = null;
                  long var31 = 65196982915495L;
                  if (65196982915495L == 0L) {
                     boolean var55 = false;
                  } else {
                     int var56 = var31 < 0L ? -1 : 1;
                  }

                  this.K(var19 < var12 ? 30L : (var26 == null ? 500L : 50L), var8);
               } catch (Throwable var36) {
                  this.X(var5, "media", var36, var7);
                  this.w = null;
                  var10.close();
                  this.t = MountFactory2.TypeB.F;
                  var14 = System.nanoTime() + 5000000000L;
                  this.K(1000L, var8);
               }
            }
         }

         var11 = false;
      } finally {
         if (var11) {
            var10.close();
            MountCache.endThread();
         }
      }

      long var38 = 65196982915496L;
      if (65196982915496L == 0L) {
         boolean var65 = false;
      } else {
         int var66 = var38 < 0L ? -1 : 1;
      }

      var10.close();
      MountCache.endThread();
   }

   private void d(long var1) {
      long var3 = 106687388799002L;
      long var5 = 23614L;
      int var7 = -237854869;
      MountFactory.Data7.ensureSourceClassInitialized();
      MountSupport var8 = new MountSupport();
      boolean var9 = false;

      try {
         var9 = true;
         long var10 = 0L;
         long var12 = 0L;
         String var14 = "";

         while (!Thread.currentThread().isInterrupted()) {
            if (!this.i(var3)) {
               long var34 = 126414252626245L;
               if (126414252626245L == 0L) {
                  boolean var62 = false;
               } else {
                  int var63 = var34 < 0L ? -1 : 1;
               }

               this.G = -1.0F;
               this.d = 0.0F;
               long var36 = 126414252626245L;
               if (126414252626245L == 0L) {
                  boolean var64 = false;
               } else {
                  int var65 = var36 < 0L ? -1 : 1;
               }

               if (var8.open()) {
                  var8.close();
               }

               this.l(1000L);
               long var21 = 126414252626246L;
               if (126414252626246L == 0L) {
                  boolean var66 = false;
               } else {
                  int var67 = var21 < 0L ? -1 : 1;
               }
            } else {
               long var15 = System.nanoTime();

               try {
                  if (!var8.open()) {
                     var8.start();
                  }

                  MountData var17 = this.w;
                  String var18 = var17 == null ? "" : var17.app();
                  long var19 = 126414252626245L;
                  if (126414252626245L == 0L) {
                     boolean var10000 = false;
                  } else {
                     int var45 = var19 < 0L ? -1 : 1;
                  }

                  long var22 = 126414252626245L;
                  if (126414252626245L == 0L) {
                     boolean var46 = false;
                  } else {
                     int var47 = var22 < 0L ? -1 : 1;
                  }

                  label326: {
                     if (var18.equals(var14)) {
                        long var24 = 126414252626245L;
                        if (126414252626245L == 0L) {
                           boolean var48 = false;
                        } else {
                           int var49 = var24 < 0L ? -1 : 1;
                        }

                        if (var15 <= var10 && (!var8.found() || var8.alive())) {
                           break label326;
                        }
                     }

                     var14 = var18;
                     var8.find(executables(var18));
                     boolean var43 = var8.found();
                     long var25 = 126414252626245L;
                     if (126414252626245L == 0L) {
                        boolean var50 = false;
                     } else {
                        int var51 = var25 < 0L ? -1 : 1;
                     }

                     var10 = var15 + (var43 ? 10000000000L : 2000000000L);
                     var12 = 0L;
                  }

                  Object var35;
                  for (; (var35 = this.Q.poll()) != null; var12 = var15 + 300000000L) {
                     var19 = 126414252626246L;
                     if (126414252626246L == 0L) {
                        boolean var52 = false;
                     } else {
                        int var53 = var19 < 0L ? -1 : 1;
                     }

                     if (var35 instanceof MountApi.Data7 var39) {
                        long var23 = 126414252626246L;
                        if (126414252626246L == 0L) {
                           boolean var54 = false;
                        } else {
                           int var55 = var23 < 0L ? -1 : 1;
                        }

                        var8.setVolume(var39.level());
                        this.G = Math.max(0.0F, Math.min(1.0F, var39.level()));
                     } else {
                        boolean var41 = var35 instanceof MountApi.Data10;
                        long var44 = 126414252626245L;
                        if (126414252626245L == 0L) {
                           boolean var56 = false;
                        } else {
                           int var57 = var44 < 0L ? -1 : 1;
                        }

                        if (var41) {
                           MountApi.Data10 var27 = (MountApi.Data10)var35;
                           var8.setMuted(var27.muted());
                           this.l = var27.muted();
                        }
                     }
                  }

                  var19 = 126414252626245L;
                  if (126414252626245L == 0L) {
                     boolean var58 = false;
                  } else {
                     int var59 = var19 < 0L ? -1 : 1;
                  }

                  if (var15 > var12) {
                     var12 = var15 + 250000000L;
                     this.G = var8.volume();
                     this.l = var8.muted();
                  }

                  boolean var40 = var15 < this.Z && var17 != null && var17.playing();
                  this.d = var40 ? var8.peak() : 0.0F;
                  long var42 = 126414252626246L;
                  if (126414252626246L == 0L) {
                     boolean var60 = false;
                  } else {
                     int var61 = var42 < 0L ? -1 : 1;
                  }

                  this.l(var40 ? 16L : 100L);
               } catch (Throwable var31) {
                  this.X(var5, "audio", var31, var7);
                  var8.close();
                  this.G = -1.0F;
                  this.d = 0.0F;
                  this.l(5000L);
               }
            }
         }

         var9 = false;
      } finally {
         if (var9) {
            var8.close();
            MountCache.endThread();
         }
      }

      long var33 = 126414252626246L;
      if (126414252626246L == 0L) {
         boolean var68 = false;
      } else {
         int var69 = var33 < 0L ? -1 : 1;
      }

      var8.close();
      MountCache.endThread();
   }

   static Set<String> executables(String var0) {
      HashSet var1 = new HashSet();
      if (var0.isEmpty()) {
         return var1;
      } else {
         String var2 = var0.toLowerCase(Locale.ROOT);
         if (MountSupport5.isSpotify(var2)) {
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

   private void l(long var1) {
      try {
         Thread.sleep(var1);
      } catch (InterruptedException var4) {
         Thread.currentThread().interrupt();
      }
   }

   private void X(long var1, String var3, Throwable var4, int var5) {
      MountFactory.Data7.ensureSourceClassInitialized();
      String var6 = var3 + ": " + var4;
      if (!var6.equals(this.p)) {
         this.p = var6;
         if (this.k != null) {
            this.k.accept(var6);
         }
      }
   }

   void closeOwned() {
      this.h = true;
      this.s = this.Z = 0L;
      this.j.clear();
      this.Q.clear();
      if (this.X != null) {
         this.X.interrupt();
      }

      if (this.r != null) {
         this.r.interrupt();
      }

      synchronized (this.b) {
         this.b.notifyAll();
      }

      for (Thread var4 : new Thread[]{this.X, this.r}) {
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

      this.X = this.r = null;
      this.w = null;
      this.M = null;
      this.k = null;
   }

private static long a(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 1256) & 32767;
      if (f[var3] == null) {
         f[var3] = e[var3] ^ var1;
      }

      return f[var3];
   }

public record Data(long z, byte[] m) {
      public long track() {
         return this.z;
      }

      public byte[] data() {
         return this.m;
      }

      public long longState21() {
         return this.z;
      }

      public byte[] byteArrayState2() {
         return this.m;
      }
   }

   public static enum TypeB {
      s,
      i,
      F,
      n;

}
}
