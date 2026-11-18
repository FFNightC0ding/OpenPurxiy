// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import java.util.List;
import net.minecraft.class_1309;
import net.minecraft.class_2394;
import net.minecraft.class_2940;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_1309.class})
public interface LivingEntityAccessor {
   @Accessor("field_49792")
   static class_2940<List<class_2394>> purxiy$effectParticles() {
      throw new AssertionError();
   }
}
