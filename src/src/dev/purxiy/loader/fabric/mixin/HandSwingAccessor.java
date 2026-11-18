// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_1309;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_1309.class})
public interface HandSwingAccessor {
   @Invoker("method_6028")
   int purxiy$swingDuration();
}
