// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_310.class})
public abstract class GameplayInteractionMixin {
   @WrapOperation(
      method = {"method_1508"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_310;method_1536()Z"
      )}
   )
   private boolean purxiy$attackClick(class_310 var1, Operation<Boolean> var2) {
      return ModuleWidget.allowAttackClick(var1) && (Boolean)var2.call(new Object[]{var1});
   }

   @Inject(
      method = {"method_1536"},
      at = {@At("HEAD")}
   )
   private void purxiy$startAttack(CallbackInfoReturnable<Boolean> var1) {
      ModuleWidget.startAttack((class_310)(Object)(Object)this);
   }

   @Inject(
      method = {"method_1508"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_746;method_6115()Z",
         ordinal = 0
      )}
   )
   private void purxiy$attack(CallbackInfo var1) {
      ModuleWidget.beforeAttack((class_310)(Object)(Object)this);
   }

   @ModifyConstant(
      method = {"method_1583"},
      constant = {@Constant(
         intValue = 4
      )},
      require = 1,
      allow = 1
   )
   private int purxiy$repeat(int var1) {
      return ModuleWidget.repeatUse(var1);
   }
}
