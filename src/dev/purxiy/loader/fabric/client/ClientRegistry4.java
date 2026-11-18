// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import net.minecraft.class_10055;

public final class ClientRegistry4 {
   private static final ClientSupport3.TypeX<ClientRegistry4.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(ClientRegistry4.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void liftName(Object var0, class_10055 var1) {
      listeners.invoke(var2 -> {
         var2.liftName(var0, var1);
         return null;
      }, null);
   }

   public interface Listener {
      void liftName(Object var1, class_10055 var2);
   }
}
