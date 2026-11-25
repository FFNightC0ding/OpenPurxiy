package dev.purxiy.modules.halo;

import dev.purxiy.loader.fabric.ClientServices;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import net.fabricmc.loader.api.FabricLoader;

final class HaloShadowProbe {
   private static final Object g;
   private static final MethodHandle m;

   private HaloShadowProbe() {
   }

   static boolean drawing() {
      if (g != null && m != null) {
         try {
            return (boolean)m.invoke((Object)g);
         } catch (Throwable var1) {
            HaloSceneRenderer.metric("draw_failures");
            return false;
         }
      } else {
         return false;
      }
   }

   static {
      Object var2 = null;
      MethodHandle var3 = null;
      if (FabricLoader.getInstance().isModLoaded("iris")) {
         try {
            Class<?> var4 = Class.forName("net.irisshaders.iris.api.v0.IrisApi");
            var2 = var4.getMethod("getInstance").invoke(null);
            var3 = MethodHandles.publicLookup().findVirtual(var4, "isRenderingShadowPass", MethodType.methodType(boolean.class));
         } catch (ReflectiveOperationException | LinkageError var3x) {
            ClientServices.LOGGER.warn("SourceByDevFFnightmare DeObfDeCompByGuideX could not ask Iris whether it draws its shadows", var3x);
            var2 = null;
            var3 = null;
         }
      }

      g = var2;
      m = var3;
   }
}
