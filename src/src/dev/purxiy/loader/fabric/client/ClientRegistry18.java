// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

public final class ClientRegistry18 {
   private static final ClientSupport3.TypeX<ClientRegistry18.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(ClientRegistry18.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void frame() {
      listeners.invoke(var0 -> {
         var0.frame();
         return null;
      }, null);
   }

   public interface Listener {
      void frame();
   }
}
