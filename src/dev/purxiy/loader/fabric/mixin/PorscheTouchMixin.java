package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.CameraStateReader;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_310.class})
public abstract class PorscheTouchMixin {
   @Inject(
      method = {"method_1536"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$touchAttack(CallbackInfoReturnable<Boolean> var1) {
      if (CameraStateReader.touchAttack()) {
         var1.setReturnValue(false);
      }
   }

   @Inject(
      method = {"method_1590"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$touchHold(boolean var1, CallbackInfo var2) {
      if (var1 && CameraStateReader.blockAttackHold()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"method_1583"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$touchDisplay(CallbackInfo var1) {
      if (CameraStateReader.touchUse()) {
         var1.cancel();
      }
   }
}
