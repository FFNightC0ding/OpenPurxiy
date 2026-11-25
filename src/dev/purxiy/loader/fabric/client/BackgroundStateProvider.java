package dev.purxiy.loader.fabric.client;

import net.minecraft.class_437;

public final class BackgroundStateProvider {
   private static final SkyPipelineRouter.PipelineList<BackgroundStateProvider.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(BackgroundStateProvider.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean drawBackground(class_437 var0) {
      return listeners.invoke(var1 -> var1.drawBackground(var0), false);
   }

   public static boolean drawnThisFrame() {
      return listeners.invoke(var0 -> var0.drawnThisFrame(), false);
   }

   public static boolean plant(double var0, double var2) {
      return listeners.invoke(var4 -> var4.plant(var0, var2), false);
   }

   public interface Listener {
      boolean drawBackground(class_437 var1);

      boolean drawnThisFrame();

      boolean plant(double var1, double var3);
   }
}
