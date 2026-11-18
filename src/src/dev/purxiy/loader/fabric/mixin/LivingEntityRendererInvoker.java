// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_922.class})
public interface LivingEntityRendererInvoker {
   @Invoker("method_3919")
   float purxiy$flipDegrees();
}
