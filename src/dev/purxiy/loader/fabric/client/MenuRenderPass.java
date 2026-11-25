package dev.purxiy.loader.fabric.client;

import net.minecraft.class_2561;
import net.minecraft.class_442;

public final class MenuRenderPass {
   private static final SkyPipelineRouter.PipelineList<MenuRenderPass.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(MenuRenderPass.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void render(class_442 var0, class_2561 var1) {
      listeners.invoke(var2 -> {
         var2.render(var0, var1);
         return null;
      }, null);
   }

   public static boolean mouseClicked(double var0, double var2, int var4) {
      return listeners.invoke(var5 -> var5.mouseClicked(var0, var2, var4), false);
   }

   public static int maskPointer(int var0) {
      return listeners.invoke(var1 -> var1.maskPointer(var0), var0);
   }

   public static boolean allowKey(int var0) {
      return listeners.invoke(var1 -> var1.allowKey(var0), true);
   }

   public interface Listener {
      void render(class_442 var1, class_2561 var2);

      boolean mouseClicked(double var1, double var3, int var5);

      int maskPointer(int var1);

      boolean allowKey(int var1);
   }
}
