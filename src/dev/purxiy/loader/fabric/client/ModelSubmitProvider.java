package dev.purxiy.loader.fabric.client;

import net.minecraft.class_10055;
import net.minecraft.class_11659;
import net.minecraft.class_4587;
import net.minecraft.class_572;

public final class ModelSubmitProvider {
   private static final SkyPipelineRouter.PipelineList<ModelSubmitProvider.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(ModelSubmitProvider.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean active() {
      return listeners.invoke(var0 -> var0.active(), false);
   }

   public static void submit(class_572<class_10055> var0, class_10055 var1, class_4587 var2, class_11659 var3, int var4, int var5) {
      listeners.invoke(var6 -> {
         var6.submit(var0, var1, var2, var3, var4, var5);
         return null;
      }, null);
   }

   public interface Listener {
      boolean active();

      void submit(class_572<class_10055> var1, class_10055 var2, class_4587 var3, class_11659 var4, int var5, int var6);
   }
}
