package dev.purxiy.loader.fabric.client;

public final class ModuleActivityProvider {
   private static final SkyPipelineRouter.PipelineList<ModuleActivityProvider.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(ModuleActivityProvider.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean active() {
      return listeners.invoke(var0 -> var0.active(), false);
   }

   public interface Listener {
      boolean active();
   }
}
