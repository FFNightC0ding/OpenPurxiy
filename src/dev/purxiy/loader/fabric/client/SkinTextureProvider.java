package dev.purxiy.loader.fabric.client;

import net.minecraft.class_10042;
import net.minecraft.class_2960;
import net.minecraft.class_583;

public final class SkinTextureProvider {
   private static final SkyPipelineRouter.PipelineList<SkinTextureProvider.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(SkinTextureProvider.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean applies(class_10042 var0) {
      return listeners.invoke(var1 -> var1.applies(var0), false);
   }

   public static class_2960 texture(class_2960 var0, class_10042 var1, class_583<?> var2) {
      return listeners.invoke(var3 -> var3.texture(var0, var1, var2), var0);
   }

   public static void clear() {
      listeners.invoke(var0 -> {
         var0.clear();
         return null;
      }, null);
   }

   public interface Listener {
      boolean applies(class_10042 var1);

      class_2960 texture(class_2960 var1, class_10042 var2, class_583<?> var3);

      void clear();
   }
}
