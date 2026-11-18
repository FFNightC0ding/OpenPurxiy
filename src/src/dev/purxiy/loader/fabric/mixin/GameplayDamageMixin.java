// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_310;
import net.minecraft.class_634;
import net.minecraft.class_638;
import net.minecraft.class_8143;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_634.class})
public abstract class GameplayDamageMixin {
   @Inject(
      method = {"method_49034"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2600;method_11074(Lnet/minecraft/class_2596;Lnet/minecraft/class_2547;Lnet/minecraft/class_11980;)V",
         shift = Shift.AFTER
      )}
   )
   private void purxiy$damage(class_8143 var1, CallbackInfo var2) {
      class_638 var3 = class_310.method_1551().field_1687;
      if (var3 != null) {
         ModuleWidget.damage(var3.method_8469(var1.comp_1267()), var1.comp_1269());
      }
   }
}
