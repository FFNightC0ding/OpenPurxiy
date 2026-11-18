// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

public final class ClientRenderer {
   private static final ClientSupport3.TypeX<ClientRenderer.Listener> draws = ClientSupport3.TypeX.create();

   private ClientRenderer() {
   }

   public static AutoCloseable install(ClientRenderer.Listener var0) {
      return draws.subscribe(0, var0);
   }

   public static void render(int var0, int var1) {
      draws.invoke(var2 -> {
         var2.render(var0, var1);
         return null;
      }, null);
   }

   @FunctionalInterface
   public interface Listener {
      void render(int var1, int var2);
   }
}
