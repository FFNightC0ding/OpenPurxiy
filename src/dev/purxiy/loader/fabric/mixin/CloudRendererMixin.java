package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.CloudStateProvider;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.client.TickRouter;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_9955;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_9955.class})
public abstract class CloudRendererMixin {
   @Inject(
      method = {"method_62168"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$cleanSky(CallbackInfo var1) {
      if (!ModuleWidget.claimed("mirror-capture") && (SkyPipelineRouter.hideClouds() || CloudStateProvider.hideVanillaClouds())) {
         var1.cancel();
      }
   }

   @ModifyVariable(
      method = {"method_62168"},
      at = @At("HEAD"),
      argsOnly = true,
      ordinal = 0
   )
   private int purxiy$tint(int var1) {
      return TickRouter.nature(SkyPipelineRouter.cloudColor(var1), "cloud");
   }
}
