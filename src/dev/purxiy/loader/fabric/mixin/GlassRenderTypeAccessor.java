package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_1921;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_1921.class})
public interface GlassRenderTypeAccessor {
   @Accessor("field_64011")
   String purxiy$name();
}
