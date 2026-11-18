// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.client.ClientRegistry21;
import net.minecraft.class_12225;
import net.minecraft.class_2561;
import net.minecraft.class_357;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_357.class})
public abstract class HomeSliderMixin {
   @WrapOperation(
      method = {"method_48579"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_357;method_75799(Lnet/minecraft/class_12225;Lnet/minecraft/class_2561;I)V"
      )}
   )
   private void purxiy$label(class_357 var1, class_12225 var2, class_2561 var3, int var4, Operation<Void> var5) {
      if (!ClientRegistry21.active() || !ClientRegistry21.sliderLabel(var1, var3)) {
         var5.call(new Object[]{var1, var2, var3, var4});
      }
   }
}
