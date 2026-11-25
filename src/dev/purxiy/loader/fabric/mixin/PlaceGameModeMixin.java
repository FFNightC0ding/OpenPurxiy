package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ChunkRenderProvider;
import net.minecraft.class_2338;
import net.minecraft.class_636;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_636.class})
public abstract class PlaceGameModeMixin {
   @Inject(
      method = {"method_2899"},
      at = {@At("HEAD")}
   )
   private void purxiy$placeDestroyStarted(class_2338 var1, CallbackInfoReturnable<Boolean> var2) {
      ChunkRenderProvider.destroyStarted(var1);
   }

   @Inject(
      method = {"method_2899"},
      at = {@At("RETURN")}
   )
   private void purxiy$placeDestroyEnded(class_2338 var1, CallbackInfoReturnable<Boolean> var2) {
      ChunkRenderProvider.destroyEnded(var2.getReturnValueZ());
   }
}
