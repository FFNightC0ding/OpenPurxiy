// Sourced By DevFFNightmare
package dev.purxiy.modules.wings;

import net.minecraft.class_10055;
import net.minecraft.class_11659;
import net.minecraft.class_12249;
import net.minecraft.class_1921;
import net.minecraft.class_4587;
import net.minecraft.class_591;
import net.minecraft.class_922;

public final class WingsRenderer5 {
   private static final class_1921 H = class_12249.method_75998(WingsCache.c);
   private static final class_1921 p = class_12249.method_76014(WingsCache.a);
   private static final class_1921 q = class_12249.method_76014(WingsCache.c);
   private static final String a = "drawn";

   public static void draw(class_591 var0, class_4587 var1, class_11659 var2, int var3, class_10055 var4, float var5, float var6) {
      if (WingsUpdater2.wears(var4) && WingsCache.ready()) {
         WingsTransform.TypeR var7 = WingsUpdater2.update(var4);
         if (var7.E != 0) {
            int var8 = class_922.method_23622(var4, 0.0F);
            var1.method_22903();
            var0.method_63512().method_22703(var1);
            var0.field_3391.method_22703(var1);
            var2.method_73483(var1, H, (var3x, var4x) -> var7.emit(var3x, var4x, var3, var8));
            if (var7.D) {
               class_1921 var9 = WingsUpdater2.shown().e ? q : p;
               var2.method_73483(var1, var9, (var2x, var3x) -> var7.emitLight(var2x, var3x, var3));
            }

            var1.method_22909();
            WingsUpdater2.metric(a);
         }
      }
   }

}
