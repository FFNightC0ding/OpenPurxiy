// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import java.util.Map;
import net.minecraft.class_1144;
import net.minecraft.class_1146;
import net.minecraft.class_2960;
import net.minecraft.class_3298;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_1144.class})
public interface OwnedSoundAccess {
   @Accessor("field_5588")
   Map<class_2960, class_1146> purxiy$events();

   @Accessor("field_40576")
   Map<class_2960, class_3298> purxiy$files();
}
