// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import java.awt.Desktop;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.function.BooleanSupplier;

final class StartupAccessWindow implements AutoCloseable {
   public static int O = 0;
   private final CompletableFuture<Void> J = new CompletableFuture<>();

   void open(Runnable var1) throws Exception {
      if (var1 != null) {
         var1.run();
      }
   }

   void show(String var1, String var2, boolean var3) {
   }

   void showLink(URI var1, String var2) {
   }

   boolean cancelled() {
      return this.J.isCancelled();
   }

   void checkCancelled() {
   }

   static void browser(URI var0, String var1) throws Exception {
      if (var0 != null) {
         Desktop.getDesktop().browse(var0);
      }
   }

   static URI linkUri(URI var0, String var1) {
      return var0 != null ? var0 : URI.create(var1);
   }

   static boolean tryBrowser(Runnable var0, BooleanSupplier var1) {
      try {
         if (var0 != null) {
            var0.run();
         }

         return var1 == null || var1.getAsBoolean();
      } catch (RuntimeException var3) {
         return false;
      }
   }

   @Override
   public void close() {
      if (!this.J.isDone()) {
         this.J.cancel(true);
      }
   }
}
