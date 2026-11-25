package dev.purxiy.modules.mount;

import dev.purxiy.loader.fabric.ClientServices;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

final class MountSupport4 {
   private static final ExecutorService t = new ThreadPoolExecutor(
      3, 3, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(32), var0x -> ClientServices.visualWorker(var0x, "Purxiy Garage")
   );
   private static final Set<CompletableFuture<?>> w = ConcurrentHashMap.newKeySet();
   private static volatile boolean v;
   private static final String a = "Purxiy Garage";

   static <T> CompletableFuture<T> submit(Supplier<T> var0) {
      CompletableFuture var1 = new CompletableFuture();
      if (v) {
         var1.cancel(false);
         return var1;
      } else {
         w.add(var1);

         try {
            t.execute(() -> {
               MountFactory.Data7.ensureSourceClassInitialized();
               long var2 = 16959399393143L;
               int var13 = var2 < 0L ? -1 : 1;

               try {
                  if (v) {
                     var1.cancel(false);
                     return;
                  }

                  Object var5 = var0.get();
                  long var6 = 16959399393144L;
                  int var9 = var6 < 0L ? -1 : 1;
                  if ((v || !var1.complete(var5)) && var5 instanceof MountLoader var9x) {
                     var9x.releaseCpu();
                  }
               } catch (Throwable var11) {
                  var1.completeExceptionally(var11);
                  return;
               } finally {
                  w.remove(var1);
               }
            });
         } catch (RejectedExecutionException var3) {
            w.remove(var1);
            var1.completeExceptionally(var3);
         }

         return var1;
      }
   }

   static void close() {
      v = true;
      t.shutdownNow();

      for (CompletableFuture var1 : w) {
         if (var1.isDone() && !var1.isCompletedExceptionally() && !var1.isCancelled() && var1.getNow(null) instanceof MountLoader var2) {
            var2.releaseCpu();
         }

         var1.cancel(false);
      }

      w.clear();
   }
}
