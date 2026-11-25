package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.NametagCapturePipeline;
import net.minecraft.class_1309;
import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_922.class})
public abstract class NameTagOwnMixin {
   @Inject(
      method = {"method_4055(Lnet/minecraft/class_1309;D)Z"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$ownTag(class_1309 var1, double var2, CallbackInfoReturnable<Boolean> var4) {
      if (!var4.getReturnValueZ() && NametagCapturePipeline.showOwn(var1)) {
         var4.setReturnValue(true);
      }
   }
}
