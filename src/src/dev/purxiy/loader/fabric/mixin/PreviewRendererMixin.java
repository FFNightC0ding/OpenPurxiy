// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.ClientTheme;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_757.class})
public abstract class PreviewRendererMixin {
   @Inject(
      method = {"method_3196"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$previewLens(class_4184 var1, float var2, boolean var3, CallbackInfoReturnable<Float> var4) {
      if (var3 && ClientTheme.active()) {
         var4.setReturnValue(ClientTheme.fov(var4.getReturnValueF()));
      }
   }

   @Inject(
      method = {"method_3198"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$previewSteadyHurt(class_4587 var1, float var2, CallbackInfo var3) {
      if (ClientTheme.active()) {
         var3.cancel();
      }
   }

   @Inject(
      method = {"method_3186"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$previewSteadyBob(class_4587 var1, float var2, CallbackInfo var3) {
      if (ClientTheme.active()) {
         var3.cancel();
      }
   }
}
