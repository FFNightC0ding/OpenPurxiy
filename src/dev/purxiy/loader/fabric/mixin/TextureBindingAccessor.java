package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_2960;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(
   targets = {"net/minecraft/class_12247$class_12338"}
)
public interface TextureBindingAccessor {
   @Accessor("comp_5228")
   class_2960 purxiy$location();
}
