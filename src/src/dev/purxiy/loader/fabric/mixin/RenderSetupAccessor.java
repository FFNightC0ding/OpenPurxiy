// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import java.util.Map;
import net.minecraft.class_12245;
import net.minecraft.class_12247;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({class_12247.class})
public interface RenderSetupAccessor {
   @Accessor("field_63987")
   Map<String, Object> purxiy$textures();

   @Accessor("field_63996")
   class_12245 purxiy$layeringTransform();

   @Accessor("field_63994")
   boolean purxiy$sortOnUpload();
}
