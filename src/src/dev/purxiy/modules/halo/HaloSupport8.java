// Sourced By DevFFNightmare
package dev.purxiy.modules.halo;

import dev.purxiy.loader.fabric.ClientServices;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import net.fabricmc.loader.api.FabricLoader;

final class HaloSupport8 {
   
   private static final Object g;

   static {
      Object var2 = null;
      MethodHandle var3 = null;
      if (FabricLoader.getInstance().isModLoaded("iris")) {
         try {
            Class<?> var4 = Class.forName("net.irisshaders.iris.api.v0.IrisApi");
            var2 = var4.getMethod("getInstance").invoke(null);
            var3 = MethodHandles.publicLookup().findVirtual(var4, "isRenderingShadowPass", MethodType.methodType(boolean.class));
         } catch (LinkageError | ReflectiveOperationException var5) {
            ClientServices.LOGGER.warn("Purxiy could not ask Iris whether it draws its shadows", var5);
            var2 = null;
            var3 = null;
         }
      }

      g = var2;
      m = var3;
   }

   
   private static final MethodHandle m;



   private HaloSupport8() {
   }

   static boolean drawing() {
      if (g != null && m != null) {
         try {
            return (boolean)m.invoke((Object)g);
         } catch (Throwable var1) {
            HaloRenderer7.metric("draw_failures");
            return false;
         }
      } else {
         return false;
      }
   }

}
