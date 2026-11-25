package dev.purxiy.modules.knife;

import net.minecraft.class_1799;

public final class KnifeFactory3 {
   private KnifeFactory3() {
   }

   public static KnifeApi of(class_1799 var0) {
      return var0.method_7960() ? null : KnifeRenderer4.wieldable(var0);
   }
}
