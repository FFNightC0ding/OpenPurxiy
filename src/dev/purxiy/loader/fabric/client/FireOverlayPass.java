package dev.purxiy.loader.fabric.client;

import net.minecraft.class_10017;
import net.minecraft.class_4597;
import net.minecraft.class_4587.class_4665;
import org.joml.Quaternionf;

public final class FireOverlayPass {
   private static final SkyPipelineRouter.PipelineList<FireOverlayPass.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(FireOverlayPass.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean replacesOverlay() {
      return listeners.invoke(var0 -> var0.replacesOverlay(), false);
   }

   public static float vanillaDrop() {
      return listeners.invoke(var0 -> var0.vanillaDrop(), 0.0F);
   }

   public static void render(boolean var0) {
      listeners.invoke(var1 -> {
         var1.render(var0);
         return null;
      }, null);
   }

   public static boolean renderEntityFlame(class_4665 var0, class_4597 var1, class_10017 var2, Quaternionf var3) {
      return listeners.invoke(var4 -> var4.renderEntityFlame(var0, var1, var2, var3), false);
   }

   public interface Listener {
      boolean replacesOverlay();

      float vanillaDrop();

      void render(boolean var1);

      boolean renderEntityFlame(class_4665 var1, class_4597 var2, class_10017 var3, Quaternionf var4);
   }
}
