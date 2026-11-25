package dev.purxiy.loader.fabric.client;

import net.minecraft.class_10055;

public final class NametagLiftProvider {
   private static final SkyPipelineRouter.PipelineList<NametagLiftProvider.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(NametagLiftProvider.Listener var0) {
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
