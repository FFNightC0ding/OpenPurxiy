// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

public final class ClientRegistry2 {
   private static final ClientSupport3.TypeX<ClientRegistry2.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(ClientRegistry2.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean hideVanillaClouds() {
      return listeners.invoke(var0 -> var0.hideVanillaClouds(), false);
   }

   public interface Listener {
      boolean hideVanillaClouds();
   }
}
