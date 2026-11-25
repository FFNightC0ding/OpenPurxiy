package dev.purxiy.loader.fabric.client;

import net.minecraft.class_332;
import net.minecraft.class_425;

public final class ScreenOverridePass {
   private static final SkyPipelineRouter.PipelineList<ScreenOverridePass.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(ScreenOverridePass.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean takesOver() {
      return listeners.invoke(var0 -> var0.takesOver(), false);
   }

   public static boolean render(class_425 var0, class_332 var1, int var2, int var3, float var4, boolean var5, long var6, float var8) {
      return listeners.invoke(var9 -> var9.render(var0, var1, var2, var3, var4, var5, var6, var8), false);
   }

   public interface Listener {
      boolean takesOver();

      boolean render(class_425 var1, class_332 var2, int var3, int var4, float var5, boolean var6, long var7, float var9);
   }
}
