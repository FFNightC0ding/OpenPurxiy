// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.module;

import dev.purxiy.loader.fabric.client.ClientSupport3;
import java.util.EnumMap;
import java.util.function.Supplier;
import net.minecraft.class_1309;

public final class ModuleRegistry {
   private static final EnumMap<ModuleRegistry.TypeL, ClientSupport3.TypeX<Supplier<class_1309>>> channels = new EnumMap<>(ModuleRegistry.TypeL.class);

   private ModuleRegistry() {
   }

   public static AutoCloseable install(ModuleRegistry.TypeL var0, Supplier<class_1309> var1) {
      return channels.get(var0).subscribe(0, var1);
   }

   public static class_1309 current(ModuleRegistry.TypeL var0) {
      return channels.get(var0).invoke(var0x -> var0x.get(), null);
   }

   static {
      for (ModuleRegistry.TypeL var3 : ModuleRegistry.TypeL.values()) {
         channels.put(var3, ClientSupport3.TypeX.create());
      }
   }

   public static enum TypeL {
      MACE,
      LANCE,
      AURA;

      private static ModuleRegistry.TypeL[] $values() {
         return new ModuleRegistry.TypeL[]{MACE, LANCE, AURA};
      }
   }
}
