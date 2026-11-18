// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import net.minecraft.class_10017;
import net.minecraft.class_1297;
import net.minecraft.class_2561;
import net.minecraft.class_640;

public final class ClientRegistry14 {
   private static final ClientSupport3.TypeX<ClientRegistry14.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(ClientRegistry14.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static class_2561 prefix() {
      return listeners.invoke(var0 -> var0.prefix(), class_2561.method_43473());
   }

   public static boolean any() {
      return listeners.invoke(var0 -> var0.any(), false);
   }

   public static class_2561 withRoles(class_2561 var0, String var1) {
      return listeners.invoke(var2 -> var2.withRoles(var0, var1), var0);
   }

   public static class_2561 own(class_2561 var0) {
      return listeners.invoke(var1 -> var1.own(var0), var0);
   }

   public static void decorate(class_1297 var0, class_10017 var1) {
      listeners.invoke(var2 -> {
         var2.decorate(var0, var1);
         return null;
      }, null);
   }

   public static class_2561 name(class_1297 var0, class_2561 var1) {
      return listeners.invoke(var2 -> var2.name(var0, var1), var1);
   }

   public static class_2561 tab(class_640 var0, class_2561 var1) {
      return listeners.invoke(var2 -> var2.tab(var0, var1), var1);
   }

   public interface Listener {
      class_2561 prefix();

      boolean any();

      class_2561 withRoles(class_2561 var1, String var2);

      class_2561 own(class_2561 var1);

      void decorate(class_1297 var1, class_10017 var2);

      class_2561 name(class_1297 var1, class_2561 var2);

      class_2561 tab(class_640 var1, class_2561 var2);
   }
}
