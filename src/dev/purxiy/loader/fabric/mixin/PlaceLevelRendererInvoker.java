// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_2338;
import net.minecraft.class_761;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_761.class})
public interface PlaceLevelRendererInvoker {
   @Invoker("method_16037")
   void purxiy$setBlockDirty(class_2338 var1, boolean var2);
}
