package dev.purxiy.loader.fabric.client;

import net.minecraft.class_10055;
import net.minecraft.class_2960;
import net.minecraft.class_591;
import net.minecraft.class_630;
import net.minecraft.class_742;
import net.minecraft.class_8685;

public final class ClientTransform {
   private static final SkyPipelineRouter.PipelineList<ClientTransform.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(ClientTransform.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void pose(class_591 var0, class_10055 var1) {
      listeners.invoke(var2 -> {
         var2.pose(var0, var1);
         return null;
      }, null);
   }

   public static void hand(class_591 var0, class_2960 var1, class_630 var2) {
      listeners.invoke(var3 -> {
         var3.hand(var0, var1, var2);
         return null;
      }, null);
   }

   public static void handPose(class_630 var0) {
      listeners.invoke(var1 -> {
         var1.handPose(var0);
         return null;
      }, null);
   }

   public static void hideArmor(Object var0, class_10055 var1) {
      listeners.invoke(var2 -> {
         var2.hideArmor(var0, var1);
         return null;
      }, null);
   }

   public static class_8685 wear(class_742 var0, class_8685 var1) {
      return listeners.invoke(var2 -> var2.wear(var0, var1), var1);
   }

   public static void size(Object var0, class_10055 var1) {
      listeners.invoke(var2 -> {
         var2.size(var0, var1);
         return null;
      }, null);
   }

   public static void shape(class_591 var0, class_10055 var1) {
      listeners.invoke(var2 -> {
         var2.shape(var0, var1);
         return null;
      }, null);
   }

   public static void frame() {
      listeners.invoke(var0 -> {
         var0.frame();
         return null;
      }, null);
   }

   public static void reloaded() {
      listeners.invoke(var0 -> {
         var0.reloaded();
         return null;
      }, null);
   }

   public interface Listener {
      void pose(class_591 var1, class_10055 var2);

      void hand(class_591 var1, class_2960 var2, class_630 var3);

      void handPose(class_630 var1);

      void hideArmor(Object var1, class_10055 var2);

      class_8685 wear(class_742 var1, class_8685 var2);

      void size(Object var1, class_10055 var2);

      void shape(class_591 var1, class_10055 var2);

      void frame();

      void reloaded();
   }
}
