// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_2338;
import net.minecraft.class_636;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_636.class})
public interface OutlineMiningAccess {
   @Accessor("field_3715")
   float purxiy$destroyProgress();

   @Accessor("field_3714")
   class_2338 purxiy$destroyBlockPos();
}
