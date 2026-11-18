// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import java.util.Map;
import net.minecraft.class_10141;
import net.minecraft.class_10865;
import net.minecraft.class_10867;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_10865.class})
public interface VisualPipelineAccess {
   @Accessor("field_57860")
   Map<RenderPipeline, class_10867> purxiy$programs();

   @Accessor("field_57861")
   Map<?, class_10141> purxiy$shaders();
}
