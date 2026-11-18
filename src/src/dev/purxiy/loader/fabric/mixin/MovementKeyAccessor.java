// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_304;
import net.minecraft.class_3675.class_306;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_304.class})
public interface MovementKeyAccessor {
   @Accessor("field_1655")
   class_306 purxiy$movementKey();
}
