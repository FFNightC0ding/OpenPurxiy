// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import net.minecraft.class_1297;

public final class ClientRegistry20 {
   private static final ClientSupport3.TypeX<ClientRegistry20.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(ClientRegistry20.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void onBlockBroken() {
      listeners.invoke(var0 -> {
         var0.onBlockBroken();
         return null;
      }, null);
   }

   public static void onBlockPlaced() {
      listeners.invoke(var0 -> {
         var0.onBlockPlaced();
         return null;
      }, null);
   }

   public static void onDamage(class_1297 var0, int var1) {
      listeners.invoke(var2 -> {
         var2.onDamage(var0, var1);
         return null;
      }, null);
   }

   public static void onEntityEvent(class_1297 var0, byte var1) {
      listeners.invoke(var2 -> {
         var2.onEntityEvent(var0, var1);
         return null;
      }, null);
   }

   public static void onDeath() {
      listeners.invoke(var0 -> {
         var0.onDeath();
         return null;
      }, null);
   }

   public static void onChat() {
      listeners.invoke(var0 -> {
         var0.onChat();
         return null;
      }, null);
   }

   public interface Listener {
      void onBlockBroken();

      void onBlockPlaced();

      void onDamage(class_1297 var1, int var2);

      void onEntityEvent(class_1297 var1, byte var2);

      void onDeath();

      void onChat();
   }
}
