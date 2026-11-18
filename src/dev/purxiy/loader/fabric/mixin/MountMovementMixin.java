// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRenderer5;
import net.minecraft.class_241;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_746.class})
public abstract class MountMovementMixin {
   @Inject(
      method = {"method_67270"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$driveHeading(class_241 var1, CallbackInfoReturnable<class_241> var2) {
      var2.setReturnValue(ClientRenderer5.drivingInput((class_241)var2.getReturnValue()));
   }
}
