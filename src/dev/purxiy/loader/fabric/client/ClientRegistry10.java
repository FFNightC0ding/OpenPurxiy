// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import net.minecraft.class_1309;

public final class ClientRegistry10 {
   private static final ClientSupport3.TypeX<ClientRegistry10.Listener> rows = ClientSupport3.TypeX.create();

   private ClientRegistry10() {
   }

   public static AutoCloseable install(ClientRegistry10.Listener var0) {
      if (rows.subscribers() != 0) {
         throw new IllegalStateException("Target facts already owned");
      } else {
         return rows.subscribe(0, var0);
      }
   }

   public static class_1309 target() {
      return rows.invoke(ClientRegistry10.Listener::target, null);
   }

   public static float fightAt() {
      return rows.invoke(ClientRegistry10.Listener::fightAt, Float.NEGATIVE_INFINITY);
   }

   public interface Listener {
      class_1309 target();

      float fightAt();
   }
}
