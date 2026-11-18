// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import java.util.List;
import net.minecraft.class_310;
import net.minecraft.class_634;

public final class Marker {
   private Marker() {
   }

   public static List<String> knownNames() {
      class_634 var0 = class_310.method_1551().method_1562();
      return var0 == null ? List.of() : var0.method_2880().stream().map(var0x -> var0x.method_2966().name()).sorted().toList();
   }
}
