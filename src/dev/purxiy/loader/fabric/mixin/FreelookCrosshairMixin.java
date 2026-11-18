// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.purxiy.loader.fabric.client.ClientRegistry13;
import net.minecraft.class_329;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_329.class})
public abstract class FreelookCrosshairMixin {
   @ModifyExpressionValue(
      method = {"method_1736"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_5498;method_31034()Z"
      )}
   )
   private boolean purxiy$freelookCrosshair(boolean var1) {
      return var1 || ClientRegistry13.inHead();
   }
}
