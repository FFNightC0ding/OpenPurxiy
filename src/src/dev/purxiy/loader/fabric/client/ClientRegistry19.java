// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import net.minecraft.class_11659;
import net.minecraft.class_1268;

public final class ClientRegistry19 {
   private static final ClientSupport3.TypeX<ClientRegistry19.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(ClientRegistry19.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void hudProjection(int var0, int var1, float var2) {
      listeners.invoke(var3 -> {
         var3.hudProjection(var0, var1, var2);
         return null;
      }, null);
   }

   public static float fov() {
      return listeners.invoke(var0 -> var0.fov(), 70.0F);
   }

   public static void beginHands(int var0, boolean var1) {
      listeners.invoke(var2 -> {
         var2.beginHands(var0, var1);
         return null;
      }, null);
   }

   public static class_11659 collector(class_1268 var0, class_11659 var1) {
      return listeners.invoke(var2 -> var2.collector(var0, var1), var1);
   }

   public static void endHand() {
      listeners.invoke(var0 -> {
         var0.endHand();
         return null;
      }, null);
   }

   public static void sweeps(boolean var0) {
      listeners.invoke(var1 -> {
         var1.sweeps(var0);
         return null;
      }, null);
   }

   public static void sweep(class_1268 var0, ClientApi2 var1) {
      listeners.invoke(var2 -> {
         var2.sweep(var0, var1);
         return null;
      }, null);
   }

   public static void composite() {
      listeners.invoke(var0 -> {
         var0.composite();
         return null;
      }, null);
   }

   public interface Listener {
      void hudProjection(int var1, int var2, float var3);

      float fov();

      void beginHands(int var1, boolean var2);

      class_11659 collector(class_1268 var1, class_11659 var2);

      void endHand();

      void sweeps(boolean var1);

      void sweep(class_1268 var1, ClientApi2 var2);

      void composite();
   }
}
