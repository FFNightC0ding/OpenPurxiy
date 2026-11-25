package dev.purxiy.loader.fabric.client;

import net.minecraft.class_310;

public final class TickRouter {
   private static final SkyPipelineRouter.PipelineList<TickRouter.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(TickRouter.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void tick(class_310 var0) {
      listeners.invoke(var1 -> {
         var1.tick(var0);
         return null;
      }, null);
   }

   public static int nature(int var0, String var1) {
      return listeners.invokeReadOnly(var2 -> var2.nature(var0, var1), var0);
   }

   public interface Listener {
      void tick(class_310 var1);

      int nature(int var1, String var2);
   }
}
