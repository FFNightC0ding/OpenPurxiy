// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.RenderHooks;
import net.minecraft.class_1309;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1309.class})
public abstract class AfterglowPoofMixin {
   @Inject(
      method = {"method_36549"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$noPoof(CallbackInfo var1) {
      if (RenderHooks.hidesPoof((class_1309)(Object)(Object)this)) {
         var1.cancel();
      }
   }
}
