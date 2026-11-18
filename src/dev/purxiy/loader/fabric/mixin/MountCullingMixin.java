// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRenderer5;
import net.minecraft.class_1297;
import net.minecraft.class_238;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_897.class})
public abstract class MountCullingMixin {
   @Inject(
      method = {"method_62358(Lnet/minecraft/class_1297;)Lnet/minecraft/class_238;"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$mountBounds(class_1297 var1, CallbackInfoReturnable<class_238> var2) {
      var2.setReturnValue(ClientRenderer5.bounds(var1, (class_238)var2.getReturnValue()));
   }
}
