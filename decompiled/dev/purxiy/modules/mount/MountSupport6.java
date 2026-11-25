package dev.purxiy.modules.mount;

import java.util.function.Supplier;
import net.minecraft.class_1297;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_310;

public final class MountSupport6 {
   private static final ThreadLocal<MountSupport6.TypeK> H = new ThreadLocal<>();

   private MountSupport6() {
   }

   public static class_239 pick(class_1297 var0, float var1, Supplier<class_239> var2) {
      if (MountUpdater4.driving() && var0 == class_310.method_1551().field_1724) {
         MountSupport6.TypeK var3 = H.get();
         H.set(new MountSupport6.TypeK(var0, var1));

         class_239 var4;
         try {
            var4 = var2.get();
         } finally {
            if (var3 == null) {
               H.remove();
            } else {
               H.set(var3);
            }
         }

         return var4;
      } else {
         return var2.get();
      }
   }

   public static boolean applies(class_1297 var0) {
      MountSupport6.TypeK var1 = H.get();
      return var1 != null && var1.c == var0;
   }

   public static class_243 view(class_1297 var0) {
      float var1 = H.get().t;
      return var0.method_5631(var0.method_5695(var1), var0.method_5705(var1) + MountUpdater4.cameraYawOffset(var1));
   }

   public static class_243 eye(class_1297 var0, class_243 var1) {
      if (!MountRenderer5.cockpitActive()) {
         return var1;
      } else {
         float var2 = H.get().t;
         class_243 var3 = var0.method_30950(var2).method_1031(0.0, var0.method_5751(), 0.0);
         return var3.method_35590(MountRenderer5.cockpitPosition(var2), MountUpdater4.boarding(var2));
      }
   }

   private record TypeK(class_1297 c, float t) {
      public class_1297 camera() {
         return this.c;
      }

      public float delta() {
         return this.t;
      }

      public class_1297 minecraftObjectState5() {
         return this.c;
      }

      public float floatState20() {
         return this.t;
      }
   }
}
