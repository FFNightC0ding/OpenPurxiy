package dev.purxiy.loader.fabric.client;

import net.minecraft.class_10055;

public final class CabinStateProvider {
   private static final SkyPipelineRouter.PipelineList<CabinStateProvider.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(CabinStateProvider.Listener var0) {
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
