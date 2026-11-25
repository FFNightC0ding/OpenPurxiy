package dev.purxiy.modules.halo;

import net.minecraft.class_10055;
import net.minecraft.class_11659;
import net.minecraft.class_4587;
import net.minecraft.class_591;

public final class HaloRenderer5 {
   public static void draw(class_591 var0, class_4587 var1, class_11659 var2, int var3, class_10055 var4, float var5, float var6) {
      if (HaloRenderer7.wears(var4)) {
         var0.method_62110(var4);
         HaloRenderer7.measure(var4, var1, var0, var2);
      }
   }
}
