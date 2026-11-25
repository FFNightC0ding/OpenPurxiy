package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.SkyTintProvider;
import net.minecraft.class_9955;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_9955.class})
public abstract class CyberspaceCloudMixin {
   @Inject(
      method = {"method_62168"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$cyberspaceClouds(CallbackInfo var1) {
      if (SkyTintProvider.hidesClouds()) {
         var1.cancel();
      }
   }
}
