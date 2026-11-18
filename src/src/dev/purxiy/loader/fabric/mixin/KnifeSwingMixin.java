// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry25;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_310.class})
public abstract class KnifeSwingMixin {
   @Inject(
      method = {"method_1536"},
      at = {@At("HEAD")}
   )
   private void purxiy$knifeAttackStarts(CallbackInfoReturnable<Boolean> var1) {
      ClientRegistry25.attackStarts();
   }

   @Inject(
      method = {"method_1536"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_746;method_6104(Lnet/minecraft/class_1268;)V"
      )}
   )
   private void purxiy$knifeAttackSwings(CallbackInfoReturnable<Boolean> var1) {
      ClientRegistry25.attackSwings();
   }
}
