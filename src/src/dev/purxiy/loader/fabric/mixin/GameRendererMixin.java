// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.purxiy.loader.fabric.ModuleState;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_4184;
import net.minecraft.class_757;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_757.class})
public abstract class GameRendererMixin {
   @Inject(
      method = {"method_3192"},
      at = {@At("HEAD")}
   )
   private void purxiy$frame(class_9779 var1, boolean var2, CallbackInfo var3) {
      ModuleState.frame();
   }

   @Inject(
      method = {"method_3196"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$fov(class_4184 var1, float var2, boolean var3, CallbackInfoReturnable<Float> var4) {
      if (var3) {
         var4.setReturnValue((float)ModuleWidget.fov(ModuleState.fov(var4.getReturnValueF())));
      }
   }

   @ModifyExpressionValue(
      method = {"method_3172"},
      at = {@At(
         value = "FIELD",
         target = "Lnet/minecraft/class_315;field_1842:Z"
      )}
   )
   private boolean purxiy$hideHand(boolean var1) {
      return ModuleWidget.hideHand(var1);
   }
}
