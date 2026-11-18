// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import java.util.Map;
import net.minecraft.class_10151;
import net.minecraft.class_2960;
import net.minecraft.class_3298;
import net.minecraft.class_5913;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_10151.class})
public interface ShaderPreprocessorAccess {
   @Invoker("method_62950")
   static class_5913 purxiy$preprocessor(Map<class_2960, class_3298> var0, class_2960 var1) {
      throw new AssertionError("Mixin not applied");
   }
}
