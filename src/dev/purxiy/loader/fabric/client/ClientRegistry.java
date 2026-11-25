package dev.purxiy.loader.fabric.client;

import net.minecraft.class_1268;

public final class ClientRegistry {
   private static final SkyPipelineRouter.PipelineList<ClientRegistry.Listener> providers = SkyPipelineRouter.PipelineList.create();

   private ClientRegistry() {
   }

   public static AutoCloseable install(ClientRegistry.Listener var0) {
      return providers.subscribe(0, var0);
   }

   public static String select(class_1268 var0, String var1) {
      String var3 = providers.invoke(var2 -> var2.select(var0, var1), var1);

      return switch (var3) {
         case "VANILLA", "SMOOTH", "CHOP", "SLASH", "PUNCH" -> var1;
         default -> var1;
      };
   }

   @FunctionalInterface
   public interface Listener {
      String select(class_1268 var1, String var2);
   }
}
