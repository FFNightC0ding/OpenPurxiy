// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.client.ClientRegistry13;
import net.minecraft.class_312;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_312.class})
public abstract class FreelookMouseMixin {
   @WrapOperation(
      method = {"method_1606"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_746;method_5872(DD)V"
      )}
   )
   private void purxiy$turnFreelook(class_746 var1, double var2, double var4, Operation<Void> var6) {
      if (!ClientRegistry13.turn(var2, var4)) {
         var6.call(new Object[]{var1, var2, var4});
      }
   }
}
