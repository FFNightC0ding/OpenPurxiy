// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_2561;
import net.minecraft.class_8519;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_8519.class})
public interface HomeSplashAccessor {
   @Accessor("field_44666")
   class_2561 purxiy$text();
}
