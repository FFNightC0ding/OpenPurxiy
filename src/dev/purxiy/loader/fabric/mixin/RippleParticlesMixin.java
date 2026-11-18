// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRenderer3;
import net.minecraft.class_1297;
import net.minecraft.class_2394;
import net.minecraft.class_702;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_702.class})
public abstract class RippleParticlesMixin {
   @Inject(
      method = {"method_3061(Lnet/minecraft/class_1297;Lnet/minecraft/class_2394;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$rippleCrits(class_1297 var1, class_2394 var2, CallbackInfo var3) {
      if (ClientRenderer3.replacesParticles(var1, var2)) {
         var3.cancel();
      }
   }
}
