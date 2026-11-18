// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import net.minecraft.class_10055;

public final class ClientRegistry11 {
   private static final ClientSupport3.TypeX<ClientRegistry11.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(ClientRegistry11.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean wears(class_10055 var0) {
      return listeners.invoke(var1 -> var1.wears(var0), false);
   }

   public static boolean hidesElytra(class_10055 var0) {
      return listeners.invoke(var1 -> var1.hidesElytra(var0), false);
   }

   public interface Listener {
      boolean wears(class_10055 var1);

      boolean hidesElytra(class_10055 var1);
   }
}
