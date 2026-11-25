package dev.purxiy.loader.fabric.client;

import net.minecraft.class_10055;

public final class ElytraStateProvider {
   private static final SkyPipelineRouter.PipelineList<ElytraStateProvider.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(ElytraStateProvider.Listener var0) {
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
