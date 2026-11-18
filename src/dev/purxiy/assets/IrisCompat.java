// Sourced By DevFFNightmare
package dev.purxiy.assets;

import dev.purxiy.loader.fabric.ClientServices;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import net.fabricmc.loader.api.FabricLoader;

public final class IrisCompat {
   
   private static final Object IRIS;
   
   private static final MethodHandle IN_USE;

   private IrisCompat() {
   }

   public static boolean inUse() {
      if (IRIS != null && IN_USE != null) {
         try {
            return (Boolean)IN_USE.invoke((Object)IRIS);
         } catch (Throwable var1) {
            return false;
         }
      } else {
         return false;
      }
   }

   static {
      Object var0 = null;
      MethodHandle var1 = null;
      if (FabricLoader.getInstance().isModLoaded("iris")) {
         try {
            Class var2 = Class.forName("net.irisshaders.iris.api.v0.IrisApi");
            var0 = var2.getMethod("getInstance").invoke(null);
            var1 = MethodHandles.publicLookup().findVirtual(var2, "isShaderPackInUse", MethodType.methodType(boolean.class));
         } catch (ReflectiveOperationException | LinkageError var3) {
            ClientServices.LOGGER.warn("Purxiy could not ask Iris whether a shader pack is in use", var3);
            var0 = null;
            var1 = null;
         }
      }

      IRIS = var0;
      IN_USE = var1;
   }
}
