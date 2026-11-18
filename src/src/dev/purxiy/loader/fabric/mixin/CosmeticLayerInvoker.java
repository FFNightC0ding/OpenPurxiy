// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_3887;
import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_922.class})
public interface CosmeticLayerInvoker {
   @Invoker("method_4046")
   boolean purxiy$addLayer(class_3887<?, ?> var1);
}
