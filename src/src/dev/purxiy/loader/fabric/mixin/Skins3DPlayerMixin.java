// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientTransform;
import net.minecraft.class_742;
import net.minecraft.class_8685;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_742.class})
public abstract class Skins3DPlayerMixin {
   @Inject(
      method = {"method_52814"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$wear(CallbackInfoReturnable<class_8685> var1) {
      class_8685 var2 = (class_8685)var1.getReturnValue();
      class_8685 var3 = ClientTransform.wear((class_742)(Object)(Object)this, var2);
      if (var3 != var2) {
         var1.setReturnValue(var3);
      }
   }
}
