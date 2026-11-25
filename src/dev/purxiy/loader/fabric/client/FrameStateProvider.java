package dev.purxiy.loader.fabric.client;

public final class FrameStateProvider {
   private static final SkyPipelineRouter.PipelineList<FrameStateProvider.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(FrameStateProvider.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void frame() {
      listeners.invoke(var0 -> {
         var0.frame();
         return null;
      }, null);
   }

   public interface Listener {
      void frame();
   }
}
