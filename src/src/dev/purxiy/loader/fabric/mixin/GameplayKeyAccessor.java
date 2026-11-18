// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_304;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_304.class})
public interface GameplayKeyAccessor {
   @Accessor("field_1661")
   int purxiy$clickCount();

   @Accessor("field_1661")
   void purxiy$setClickCount(int var1);
}
