// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_1309;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin({class_1309.class})
public abstract class GameplaySprintMixin {
   @ModifyVariable(
      method = {"method_5728"},
      at = @At("HEAD"),
      argsOnly = true,
      ordinal = 0
   )
   private boolean purxiy$sprint(boolean var1) {
      return ModuleWidget.sprint((class_1309)(Object)(Object)this, var1);
   }
}
