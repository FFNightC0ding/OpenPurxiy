package dev.purxiy.modules.mount;

import net.minecraft.class_243;
import net.minecraft.class_310;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

public final class MountFrameSplit {
   private static final MountCurve o = new MountCurve();

   private MountFrameSplit() {
   }

   public static boolean skipSecondaryFrame() {
      if (!MountGarageScreen.capturing()) {
         return false;
      } else {
         o.skip();
         return true;
      }
   }

   public static void mainFrame(Args var0) {
      if (!MountCameraState.cockpitActive()) {
         o.reset();
      } else {
         class_310 var1 = class_310.method_1551();
         class_243 var2 = var1.field_1773.method_19418().method_71156();
         Matrix4f var3 = (Matrix4f)var0.get(0);
         Matrix4f var4 = (Matrix4f)var0.get(2);
         MountCurve.Data var5 = o.sample(
            false, var1.field_1687, System.nanoTime(), var3.get(new float[16]), var4.get(new float[16]), var2.field_1352, var2.field_1351, var2.field_1350
         );
         var0.set(1, new Matrix4f().set(var5.view()));
         var0.set(3, new Matrix4f().set(var5.projection()));
         var0.set(4, var5.dx());
         var0.set(5, var5.dy());
         var0.set(6, var5.dz());
      }
   }

   public static MountCurve diagnostics() {
      return o;
   }
}
