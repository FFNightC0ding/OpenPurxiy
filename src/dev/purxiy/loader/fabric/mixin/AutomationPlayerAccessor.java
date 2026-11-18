// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_746.class})
public interface AutomationPlayerAccessor {
   @Accessor("field_3919")
   boolean purxiy$wasSprinting();

   @Accessor("field_3926")
   double purxiy$xLast();

   @Accessor("field_3940")
   double purxiy$yLast();

   @Accessor("field_3924")
   double purxiy$zLast();

   @Accessor("field_3941")
   float purxiy$yRotLast();

   @Accessor("field_3925")
   float purxiy$xRotLast();
}
