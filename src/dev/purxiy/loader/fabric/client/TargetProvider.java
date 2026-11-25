package dev.purxiy.loader.fabric.client;

import net.minecraft.class_1309;

public final class TargetProvider {
   private static final SkyPipelineRouter.PipelineList<TargetProvider.Listener> rows = SkyPipelineRouter.PipelineList.create();

   private TargetProvider() {
   }

   public static AutoCloseable install(TargetProvider.Listener var0) {
      if (rows.subscribers() != 0) {
         throw new IllegalStateException("Target facts already owned");
      } else {
         return rows.subscribe(0, var0);
      }
   }

   public static class_1309 target() {
      return rows.invoke(TargetProvider.Listener::target, null);
   }

   public static float fightAt() {
      return rows.invoke(TargetProvider.Listener::fightAt, Float.NEGATIVE_INFINITY);
   }

   public interface Listener {
      class_1309 target();

      float fightAt();
   }
}
