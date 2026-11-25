package dev.purxiy.modules.glass;

import dev.purxiy.loader.fabric.ClientServices;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import net.fabricmc.loader.api.FabricLoader;

final class GlassShadowProbe {
   private static final Object H;
   private static final MethodHandle b;

   private GlassShadowProbe() {
   }

   static boolean drawing() {
      if (H != null && b != null) {
         try {
            return (boolean)b.invoke((Object)H);
         } catch (Throwable var1) {
            GlassRegistry.metric("draw_failures");
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

      H = var2;
      b = var3;
   }
}
