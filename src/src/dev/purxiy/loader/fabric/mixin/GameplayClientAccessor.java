// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_310.class})
public interface GameplayClientAccessor {
   @Accessor("field_1771")
   int purxiy$missTime();
}
