// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_638.class})
public interface VisualClientLevelAccessor {
   @Invoker("method_23789")
   int purxiy$skyFlashTime();
}
