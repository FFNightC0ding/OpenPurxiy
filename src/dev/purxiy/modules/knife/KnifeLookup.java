package dev.purxiy.modules.knife;

import net.minecraft.class_1799;

public final class KnifeLookup {
   private KnifeLookup() {
   }

   public static KnifeApi of(class_1799 var0) {
      return var0.method_7960() ? null : KnifeWieldable.wieldable(var0);
   }
}
