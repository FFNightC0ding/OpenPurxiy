package dev.purxiy.loader.fabric.client;

import net.minecraft.class_11659;
import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_742;
import net.minecraft.class_898;

public final class HudRenderPass {
   private static final SkyPipelineRouter.PipelineList<HudRenderPass.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(HudRenderPass.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean render(
      class_742 var0, class_1268 var1, class_1799 var2, float var3, float var4, class_4587 var5, class_11659 var6, int var7, class_898 var8
   ) {
      return listeners.invoke(var9 -> var9.render(var0, var1, var2, var3, var4, var5, var6, var7, var8), false);
   }

   public interface Listener {
      boolean render(class_742 var1, class_1268 var2, class_1799 var3, float var4, float var5, class_4587 var6, class_11659 var7, int var8, class_898 var9);
   }
}
