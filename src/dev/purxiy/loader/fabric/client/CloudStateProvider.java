package dev.purxiy.loader.fabric.client;

public final class CloudStateProvider {
   private static final SkyPipelineRouter.PipelineList<CloudStateProvider.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(CloudStateProvider.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean hideVanillaClouds() {
      return listeners.invoke(var0 -> var0.hideVanillaClouds(), false);
   }

   public interface Listener {
      boolean hideVanillaClouds();
   }
}
