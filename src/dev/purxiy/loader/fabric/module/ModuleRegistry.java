package dev.purxiy.loader.fabric.module;

import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import java.util.EnumMap;
import java.util.function.Supplier;
import net.minecraft.class_1309;

public final class ModuleRegistry {
   private static final EnumMap<ModuleRegistry.WeaponGroup, SkyPipelineRouter.PipelineList<Supplier<class_1309>>> channels = new EnumMap<>(ModuleRegistry.WeaponGroup.class);

   private ModuleRegistry() {
   }

   public static AutoCloseable install(ModuleRegistry.WeaponGroup var0, Supplier<class_1309> var1) {
      return channels.get(var0).subscribe(0, var1);
   }

   public static class_1309 current(ModuleRegistry.WeaponGroup var0) {
      return channels.get(var0).invoke(var0x -> var0x.get(), null);
   }

   static {
      for (ModuleRegistry.WeaponGroup var3 : ModuleRegistry.WeaponGroup.values()) {
         channels.put(var3, SkyPipelineRouter.PipelineList.create());
      }
   }

   public static enum WeaponGroup {
      MACE,
      LANCE,
      AURA;

      private static ModuleRegistry.WeaponGroup[] $values() {
         return new ModuleRegistry.WeaponGroup[]{MACE, LANCE, AURA};
      }
   }
}
