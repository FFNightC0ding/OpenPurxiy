package dev.purxiy.loader.fabric.client;

import net.minecraft.class_12076;

public final class SkyTintProvider {
   private static final SkyPipelineRouter.PipelineList<SkyTintProvider.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(SkyTintProvider.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean hidesClouds() {
      return listeners.invoke(var0 -> var0.hidesClouds(), false);
   }

   public static void sky(class_12076 var0) {
      listeners.invoke(var1 -> {
         var1.sky(var0);
         return null;
      }, null);
   }

   public interface Listener {
      boolean hidesClouds();

      void sky(class_12076 var1);
   }
}
