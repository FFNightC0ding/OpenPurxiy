package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.TickRouter;
import net.minecraft.class_1920;
import net.minecraft.class_2246;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_324;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_324.class})
public abstract class CuteFixedFoliageMixin {
   @Inject(
      method = {"method_1697(Lnet/minecraft/class_2680;Lnet/minecraft/class_1920;Lnet/minecraft/class_2338;I)I"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$leaves(class_2680 var1, class_1920 var2, class_2338 var3, int var4, CallbackInfoReturnable<Integer> var5) {
      if (var1.method_27852(class_2246.field_10539) || var1.method_27852(class_2246.field_9988)) {
         var5.setReturnValue(TickRouter.nature(var5.getReturnValueI(), "leaves"));
      }
   }
}
