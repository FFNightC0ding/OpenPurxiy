// Sourced By DevFFNightmare
package dev.purxiy.modules.cute;

import dev.purxiy.loader.fabric.ClientServices;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import net.minecraft.class_10042;
import net.minecraft.class_10055;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_1044;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3300;
import net.minecraft.class_583;

public final class CuteWorldUpdater {
   private static final Map<CuteWorldUpdater.TypeU, CuteWorldUpdater.TypeO> A = new LinkedHashMap<>(32, 0.75F, true);
   private static final Map<CuteWorldUpdater.TypeU, Long> E = new LinkedHashMap<>();
   private static final Set<CuteWorldUpdater.TypeU> p = new HashSet<>();
   private static final ConcurrentLinkedQueue<CuteWorldUpdater.TypeL> y = new ConcurrentLinkedQueue<>();
   private static final ThreadPoolExecutor x = new ThreadPoolExecutor(
      1, 1, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(12), var0x -> ClientServices.visualWorker(var0x, "Purxiy cute skins")
   );
   private static volatile int l;
   private static int n = 0;
   private static CuteWorldTransform.Data a;
   private static int G = 0;
   private static Object w;
   public static int m = 0;



   private static final Integer[] e = new Integer[2];

   private CuteWorldUpdater() {
   }

   public static boolean applies(class_10042 var0) {
      CuteWorldModule var1 = CuteWorldUpdater2.r;
      return var1.enabled() && var0 instanceof class_10055 ? var1.f.get() : var1.e.get();
   }

   public static class_2960 texture(class_2960 var0, class_10042 var1, class_583<?> var2) {
      long var3 = 87486656190929L;
      long var5 = var3 ^ 115189976112491L;
      if (!applies(var1)) {
         return var0;
      } else {
         boolean var7 = var1 instanceof class_10055;
         CuteWorldUpdater.TypeU var8 = new CuteWorldUpdater.TypeU(var0, var7 ? CuteWorldFactory.w : CuteWorldFactory.of(var2), var7);
         class_310 var9 = class_310.method_1551();
         class_1044 var10 = var9.method_1531().method_4619(var0);
         Object var11 = var10 instanceof class_1043 var12 ? var12.method_4525() : var10;
         CuteWorldUpdater.TypeO var20 = A.get(var8);
         if (var20 != null) {
            if (var20.z == var11) {
               return var20.V;
            }

            A.remove(var8);
            b(var20);
         }

         if (!p.contains(var8) && p.size() < 12 && System.nanoTime() >= E.getOrDefault(var8, 0L)) {
            CuteWorldTransform.Data2 var13 = null;
            if (var10 instanceof class_1043 var14) {
               class_1011 var15 = var14.method_4525();
               if (var15 == null || !k(var5, var15.method_4307(), var15.method_4323())) {
                  return var0;
               }

               var13 = new CuteWorldTransform.Data2(var15.method_4307(), var15.method_4323(), var15.method_61942());
            }

            CuteWorldTransform.Data2 var21 = var13;
            CuteWorldTransform.Data var22 = T();
            int var16 = l;
            class_3300 var17 = var9.method_1478();
            p.add(var8);

            try {
               x.execute(
                  () -> {
                     long var8x = 43234525643962L;
                     if (var16 == l) {
                        try {
                           CuteWorldTransform.Data2 var10x = var21;
                           if (var21 == null) {
                              try (InputStream var24 = var17.getResourceOrThrow(var0).method_14482()) {
                                 long var12x = 101877401841501L;
                                 if (101877401841501L == 0L) {
                                    boolean var30 = false;
                                 } else {
                                    int var31 = var12x < 0L ? -1 : 1;
                                 }

                                 try (class_1011 var15x = class_1011.method_4309(var24)) {
                                    int var16x = var15x.method_4307();
                                    long var17x = 101877401841501L;
                                    if (101877401841501L == 0L) {
                                       boolean var32 = false;
                                    } else {
                                       int var33 = var17x < 0L ? -1 : 1;
                                    }

                                    if (!k(var8x, var16x, var15x.method_4323())) {
                                       throw new IllegalArgumentException("Unsupported texture dimensions");
                                    }

                                    var10x = new CuteWorldTransform.Data2(var15x.method_4307(), var15x.method_4323(), var15x.method_61942());
                                 }
                              }
                           }

                           List var25 = var8.v;
                           long var26 = 101877401841501L;
                           if (101877401841501L == 0L) {
                              boolean var34 = false;
                           } else {
                              int var35 = var26 < 0L ? -1 : 1;
                           }

                           if (var7 && var10x.height() * 2 == var10x.width()) {
                              var25 = var25.stream()
                                 .map(var0xx -> new CuteWorldTransform.Data3(((CuteWorldTransform.Data3)var0xx).u0(), ((CuteWorldTransform.Data3)var0xx).v0() * 2.0, ((CuteWorldTransform.Data3)var0xx).u1(), ((CuteWorldTransform.Data3)var0xx).v1() * 2.0, ((CuteWorldTransform.Data3)var0xx).kind()))
                                 .toList();
                           }

                           CuteWorldTransform.Data2 var27 = CuteWorldTransform.transform(var10x, var25, var22, var7);
                           long var28 = 101877401841500L;
                           if (101877401841500L == 0L) {
                              boolean var36 = false;
                           } else {
                              int var37 = var28 < 0L ? -1 : 1;
                           }

                           if (var16 == l) {
                              y.add(new CuteWorldUpdater.TypeL(var8, var11, var16, var27, null));
                           }
                        } catch (Exception var23) {
                           long var11x = 101877401841500L;
                           if (101877401841500L == 0L) {
                              boolean var29 = false;
                           } else {
                              int var10000 = var11x < 0L ? -1 : 1;
                           }

                           if (var16 == l) {
                              y.add(new CuteWorldUpdater.TypeL(var8, var11, var16, null, var23));
                           }
                        }
                     }
                  }
               );
            } catch (RejectedExecutionException var19) {
               p.remove(var8);
            }

            return var0;
         } else {
            return var0;
         }
      }
   }

   private static boolean k(long var0, int var2, int var3) {
      long var4 = 43234525643962L;
      if (43234525643962L == 0L) {
         boolean var10000 = false;
      } else {
         int var13 = var4 < 0L ? -1 : 1;
      }

      if (var2 > 0) {
         long var7 = 43234525643962L;
         if (43234525643962L == 0L) {
            boolean var14 = false;
         } else {
            int var15 = var7 < 0L ? -1 : 1;
         }

         if (var3 > 0) {
            long var9 = 43234525643962L;
            if (43234525643962L == 0L) {
               boolean var16 = false;
            } else {
               int var17 = var9 < 0L ? -1 : 1;
            }

            if (var2 <= 1024) {
               long var11 = 43234525643961L;
               if (43234525643961L == 0L) {
                  boolean var18 = false;
               } else {
                  int var19 = var11 < 0L ? -1 : 1;
               }

               if (var3 <= 1024) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private static CuteWorldTransform.Data T() {
      CuteWorldModule var0 = CuteWorldUpdater2.r;
      return new CuteWorldTransform.Data(var0.w.get(), var0.n.get(), var0.h.get(), var0.g.get());
   }

   public static void tick(class_310 var0) {
      CuteWorldModule var1 = CuteWorldUpdater2.r;
      CuteWorldTransform.Data var2 = T();
      int var3 = var1.enabled() ? 1 + (var1.f.get() ? 2 : 0) + (var1.e.get() ? 4 : 0) : 0;
      if (!var2.equals(a) || var3 != G || var0.field_1687 != w) {
         clear();
         a = var2;
         G = var3;
         w = var0.field_1687;
      }

      for (int var4 = 0; var4 < 2; var4++) {
         CuteWorldUpdater.TypeL var5 = y.poll();
         if (var5 == null) {
            break;
         }

         if (var5.u == l) {
            p.remove(var5.t);
            if (var5.f != null) {
               if (E.size() >= 128) {
                  E.remove(E.keySet().iterator().next());
               }

               E.put(var5.t, System.nanoTime() + 30000000000L);
               ClientServices.LOGGER.debug("Cute World kept original texture {}: {}", var5.t.w, var5.f.toString());
            } else {
               CuteWorldTransform.Data2 var6 = var5.n;
               int var7 = var6.width() * var6.height();

               while (!A.isEmpty() && (A.size() >= 96 || n + var7 > 4194304)) {
                  Iterator var8 = A.entrySet().iterator();
                  CuteWorldUpdater.TypeO var9 = (CuteWorldUpdater.TypeO)((Entry)var8.next()).getValue();
                  var8.remove();
                  b(var9);
               }

               class_1011 var11 = new class_1011(var6.width(), var6.height(), false);

               for (int var12 = 0; var12 < var6.height(); var12++) {
                  for (int var10 = 0; var10 < var6.width(); var10++) {
                     var11.method_61941(var10, var12, var6.pixels()[var12 * var6.width() + var10]);
                  }
               }

               class_2960 var13 = ClientServices.id("cute_world/" + UUID.nameUUIDFromBytes(var5.t.toString().getBytes(StandardCharsets.UTF_8)));
               var0.method_1531().method_4616(var13, new class_1043(ClientServices.label(var13::toString), var11));
               A.put(var5.t, new CuteWorldUpdater.TypeO(var13, var5.v, var7));
               n += var7;
               E.remove(var5.t);
            }
         }
      }
   }

   public static void clear() {
      l++;
      x.getQueue().clear();
      p.clear();
      y.clear();
      E.clear();
      A.values().forEach(CuteWorldUpdater::b);
      A.clear();
      n = 0;
   }

   private static void b(CuteWorldUpdater.TypeO var0) {
      class_310.method_1551().method_1531().method_4615(var0.V);
      n = n - var0.s;
   }

   public static void close() {
      clear();
      x.shutdownNow();

      try {
         if (!x.awaitTermination(5L, TimeUnit.SECONDS)) {
            throw new IllegalStateException("Cute texture worker did not stop");
         }
      } catch (InterruptedException var1) {
         Thread.currentThread().interrupt();
         throw new IllegalStateException("Cute texture worker interrupted");
      }

      y.clear();
      w = null;
      a = null;
   }

public static void ensureSourceClassInitialized() {
   }

private record TypeL(CuteWorldUpdater.TypeU t, Object v, int u, CuteWorldTransform.Data2 n, Exception f) {
      public CuteWorldUpdater.TypeU key() {
         return this.t;
      }

      public Object sourceIdentity() {
         return this.v;
      }

      public int generation() {
         return this.u;
      }

      public CuteWorldTransform.Data2 picture() {
         return this.n;
      }

      public Exception failure() {
         return this.f;
      }

      public CuteWorldUpdater.TypeU cuteTextureOverrideCache_FaceTextureCacheKeyState1() {
         return this.t;
      }

      public Object objectState5() {
         return this.v;
      }

      public int integerState24() {
         return this.u;
      }

      public CuteWorldTransform.Data2 cuteTextureColorTransform_DecodedImagePixelsState1() {
         return this.n;
      }

      public Exception exceptionState1() {
         return this.f;
      }
   }

   private record TypeO(class_2960 V, Object z, int s) {
      public class_2960 texture() {
         return this.V;
      }

      public Object sourceIdentity() {
         return this.z;
      }

      public int pixels() {
         return this.s;
      }

      public class_2960 minecraftObjectState4() {
         return this.V;
      }

      public Object objectState6() {
         return this.z;
      }

      public int integerState13() {
         return this.s;
      }
   }

   private record TypeU(class_2960 w, List<CuteWorldTransform.Data3> v, boolean C) {
      public class_2960 source() {
         return this.w;
      }

      public List<CuteWorldTransform.Data3> faces() {
         return this.v;
      }

      public boolean player() {
         return this.C;
      }

      public class_2960 minecraftObjectState5() {
         return this.w;
      }

      public List<CuteWorldTransform.Data3> listState10() {
         return this.v;
      }

      public boolean booleanState22() {
         return this.C;
      }
   }
}
