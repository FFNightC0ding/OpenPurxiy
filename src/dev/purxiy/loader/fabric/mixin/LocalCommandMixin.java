package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ChatCommandRouter;
import net.minecraft.class_634;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_634.class})
public abstract class LocalCommandMixin {
   @Inject(
      method = {"method_45730"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$localCommand(String var1, CallbackInfo var2) {
      if (ChatCommandRouter.intercept(var1)) {
         var2.cancel();
      }
   }
}
