// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

public final class ClientRegistry12 {
   private static final ClientSupport3.TypeX<ClientRegistry12.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(ClientRegistry12.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean active() {
      return listeners.invoke(var0 -> var0.active(), false);
   }

   public interface Listener {
      boolean active();
   }
}
