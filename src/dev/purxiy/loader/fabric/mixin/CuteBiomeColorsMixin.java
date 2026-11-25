package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.TickRouter;
import net.minecraft.class_1163;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1163.class})
public abstract class CuteBiomeColorsMixin {
   @Inject(
      method = {"method_4962"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private static void purxiy$grass(CallbackInfoReturnable<Integer> var0) {
      var0.setReturnValue(TickRouter.nature(var0.getReturnValueI(), "grass"));
   }

   @Inject(
      method = {"method_4966", "method_68163"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private static void purxiy$leaves(CallbackInfoReturnable<Integer> var0) {
      var0.setReturnValue(TickRouter.nature(var0.getReturnValueI(), "leaves"));
   }

   @Inject(
      method = {"method_4961"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private static void purxiy$water(CallbackInfoReturnable<Integer> var0) {
      var0.setReturnValue(TickRouter.nature(var0.getReturnValueI(), "water"));
   }
}
