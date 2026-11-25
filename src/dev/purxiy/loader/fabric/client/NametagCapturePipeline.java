package dev.purxiy.loader.fabric.client;

import net.minecraft.class_10017;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_4587;

public final class NametagCapturePipeline {
   private static final SkyPipelineRouter.PipelineList<NametagCapturePipeline.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(NametagCapturePipeline.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void extract(class_1297 var0, class_10017 var1) {
      listeners.invoke(var2 -> {
         var2.extract(var0, var1);
         return null;
      }, null);
   }

   public static boolean capture(class_10017 var0, class_4587 var1) {
      return listeners.invoke(var2 -> var2.capture(var0, var1), false);
   }

   public static boolean showOwn(class_1309 var0) {
      return listeners.invoke(var1 -> var1.showOwn(var0), false);
   }

   public static boolean bounds(class_1297 var0, float[] var1) {
      return listeners.invoke(var2 -> var2.bounds(var0, var1), false);
   }

   public interface Listener {
      void extract(class_1297 var1, class_10017 var2);

      boolean capture(class_10017 var1, class_4587 var2);

      boolean showOwn(class_1309 var1);

      boolean bounds(class_1297 var1, float[] var2);
   }
}
