package dev.purxiy.modules.skins3d;

import dev.purxiy.loader.fabric.ClientServices;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.class_1011;
import net.minecraft.class_1043;
import net.minecraft.class_1044;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3298;

final class Skins3DFactory {
   private static final int v = 96;
   private static final Map<Skins3DFactory.TypeH, Skins3DFactory.TypeF> a = new LinkedHashMap<Skins3DFactory.TypeH, Skins3DFactory.TypeF>(32, 0.75F, true) {
      @Override
      protected boolean removeEldestEntry(Entry<Skins3DFactory.TypeH, Skins3DFactory.TypeF> var1) {
         return this.size() > 96;
      }
   };
   private static final AtomicInteger M = new AtomicInteger();
   private static final AtomicInteger g = new AtomicInteger();
   private static final ExecutorService n = ClientServices.visualExecutor("Purxiy voxel skins");
   private static final long[] d = new long[]{5648482313916149014L, -1521467118863748695L, 9166649614045233849L};
   private static final Integer[] e = new Integer[3];

   private Skins3DFactory() {
   }

   static Skins3DBuilder.TypeR get(class_2960 var0, boolean var1) {
      long var2 = 129512096553081L;
      long var4 = var2 ^ 49276458926432L;
      Skins3DFactory.TypeH var6 = new Skins3DFactory.TypeH(var0, var1);
      Skins3DFactory.TypeF var7 = a.get(var6);
      if (var7 == null) {
         var7 = new Skins3DFactory.TypeF();
         a.put(var6, var7);
         B(var0, var1, var4, var7);
      }

      return var7.C;
   }

   static Skins3DBuilder.TypeR model(class_2960 var0) {
      Skins3DFactory.TypeH var1 = new Skins3DFactory.TypeH(var0, false);
      Skins3DFactory.TypeF var2 = a.get(var1);
      if (var2 == null) {
         Skins3DFactory.TypeF var2x = new Skins3DFactory.TypeF();
         var2 = var2x;
         a.put(var1, var2x);
         CompletableFuture.<Skins3DBuilder.TypeR>supplyAsync(() -> {
            long var1x = 52196331148727L;
            return n(var0, false, var1x);
         }, n).whenComplete((var9, var3x) -> {
            long var4 = 48801127151881L;
            q(var2x, var4, var0, var9, var3x);
         });
      }

      return var2.C;
   }

   static void clear() {
      a.clear();
   }

   static String stats() {
      int var0 = 0;
      int var1 = 0;

      for (Skins3DFactory.TypeF var3 : a.values()) {
         Skins3DBuilder.TypeR var4 = var3.C;
         if (var4 != null) {
            var0++;
            var1 += var4.quads();
         }
      }

      int var8 = a.size();
      int var9 = M.get();
      int var10 = g.get();
      return var8 + " skins (" + var0 + " ready, " + var1 + " quads), " + var9 + " built, " + var10 + " unreadable";
   }

   private static void B(class_2960 var0, boolean var1, long var2, Skins3DFactory.TypeF var4) {
      long var5 = 59862835691705L;
      var5 = 117711488998322L;
      short var7 = 27406;
      SkinPreset.ensureSourceClassInitialized();
      class_2960 var8 = SkinPreset.voxelModel(var0);
      if (var8 != null) {
         CompletableFuture.<Skins3DBuilder.TypeR>supplyAsync(() -> {
            long var2x = 52196331148727L;
            return n(var8, var1, var2x);
         }, n).whenComplete((var2x, var3) -> {
            long var4x = 59862835691705L;
            long var6 = var4x ^ 28671101876656L;
            q(var4, var6, var0, var2x, var3);
         });
      } else {
         class_1044 var9;
         try {
            var9 = class_310.method_1551().method_1531().method_4619(var0);
         } catch (RuntimeException var231) {
            var4.P = true;
            return;
         }

         boolean var10 = var9 instanceof class_1043;
         long var11 = 97974434060569L;
         int var14 = var11 < 0L ? -1 : 1;
         if (!var10) {
            CompletableFuture.<Skins3DBuilder.TypeR>supplyAsync(() -> {
               long var3 = (long)var7 << 32 | 3615227904L | 56242L;
               long var5x = var3 ^ 91173690790551L;
               return Skins3DBuilder.build(J(var0, var5x), var1);
            }, n).whenComplete((var2x, var3) -> {
               long var4x = 48801127151881L;
               q(var4, var4x, var0, var2x, var3);
            });
            return;
         }

         class_1043 var14x = (class_1043)var9;
         class_1011 var15 = var14x.method_4525();
         long var16 = 97974434060569L;
         int var19 = var16 < 0L ? -1 : 1;
         if (var15 != null) {
            long var19x = 97974434060568L;
            int var19x = var19x < 0L ? -1 : 1;
            if (var15.method_4307() == 64 && var15.method_4323() == 64) {
               int[] var22x = var15.method_61942();
               long var23 = 97974434060569L;
               int var22x = var23 < 0L ? -1 : 1;
               CompletableFuture.<Skins3DBuilder.TypeR>supplyAsync(() -> Skins3DBuilder.build(var22, var1), n).whenComplete((var2x, var3) -> {
                  long var4x = 48801127151881L;
                  q(var4, var4x, var0, var2x, var3);
               });
               return;
            }
         }

         j(var4, var0, "not a 64x64 skin");
      }
   }

   private static int[] J(class_2960 var0, long var1) {
      SkinPreset.ensureSourceClassInitialized();
      long var3 = var1 ^ 84905108460866L;
      class_3298 var5 = (class_3298)class_310.method_1551().method_1478().method_14486(var0).orElseThrow(() -> new IllegalStateException("no such texture"));

      try {
         int[] var9;
         try (InputStream var6 = var5.method_14482()) {
            class_1011 var7 = class_1011.method_4309(var6);

            try {
               int var8 = a(27456, 3367832651657602163L ^ var1);
               if (var7.method_4307() != var8 || var7.method_4323() != var8) {
                  throw new IllegalStateException("not a 64x64 skin");
               }

               var9 = var7.method_61942();
            } catch (Throwable var13) {
               if (var7 != null) {
                  try {
                     var7.close();
                  } catch (Throwable var12) {
                     var13.addSuppressed(var12);
                  }
               }

               throw var13;
            }

            if (var7 != null) {
               var7.close();
            }
         }

         return var9;
      } catch (IOException var15) {
         throw new UncheckedIOException(var15);
      }
   }

   private static Skins3DBuilder.TypeR n(class_2960 var0, boolean var1, long var2) {
      SkinPreset.ensureSourceClassInitialized();
      class_3298 var4 = (class_3298)class_310.method_1551()
         .method_1478()
         .method_14486(var0)
         .orElseThrow(() -> new IllegalStateException("Missing voxel model " + var0));

      try {
         Skins3DBuilder.TypeR var6;
         try (InputStream var5 = var4.method_14482()) {
            var6 = Skins3DBuilder.readModel(var5, var1);
         }

         return var6;
      } catch (IOException var11) {
         throw new UncheckedIOException(var11);
      }
   }

   private static void q(Skins3DFactory.TypeF var0, long var1, class_2960 var3, Skins3DBuilder.TypeR var4, Throwable var5) {
      SkinPreset.ensureSourceClassInitialized();
      long var6 = 48801127151880L;
      int var9 = var6 < 0L ? -1 : 1;
      if (var4 != null) {
         long var9x = 48801127151880L;
         int var10 = var9x < 0L ? -1 : 1;
         var0.C = var4;
         M.incrementAndGet();
      } else {
         j(var0, var3, var5 == null ? "unknown" : String.valueOf(var5.getCause() != null ? var5.getCause().getMessage() : var5.getMessage()));
      }
   }

   private static void j(Skins3DFactory.TypeF var0, class_2960 var1, String var2) {
      var0.P = true;
      g.incrementAndGet();
   }

   static void shutdown() {
      n.shutdownNow();
      clear();
   }

   private static int a(int var0, long var1) {
      int var3 = 27456 ^ (int)(var1 & 32767L) ^ 1558;
      if (e[var3] == null) {
         e[var3] = (int)(d[var3] ^ var1);
      }

      return e[var3];
   }

   private static final class TypeF {
      volatile Skins3DBuilder.TypeR C;
      volatile boolean P;
   }

   private record TypeH(class_2960 L, boolean d) {
      public class_2960 texture() {
         return this.L;
      }

      public boolean slim() {
         return this.d;
      }

      public class_2960 minecraftObjectState20() {
         return this.L;
      }

      public boolean booleanState19() {
         return this.d;
      }
   }
}
