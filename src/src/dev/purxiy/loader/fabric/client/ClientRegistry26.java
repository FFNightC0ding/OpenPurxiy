// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import net.minecraft.class_10055;

public final class ClientRegistry26 {
   private static final ClientSupport3.TypeX<ClientRegistry26.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(ClientRegistry26.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean inClosedCabin(class_10055 var0) {
      return listeners.invoke(var1 -> var1.inClosedCabin(var0), false);
   }

   public interface Listener {
      boolean inClosedCabin(class_10055 var1);
   }
}
