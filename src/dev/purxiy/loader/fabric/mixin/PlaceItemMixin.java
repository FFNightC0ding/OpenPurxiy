package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ChunkRenderProvider;
import net.minecraft.class_1269;
import net.minecraft.class_1747;
import net.minecraft.class_1750;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1747.class})
public abstract class PlaceItemMixin {
   @Inject(
      method = {"method_7712"},
      at = {@At("HEAD")}
   )
   private void purxiy$placeStarted(class_1750 var1, CallbackInfoReturnable<class_1269> var2) {
      ChunkRenderProvider.placeStarted(var1);
   }

   @Inject(
      method = {"method_7712"},
      at = {@At("RETURN")}
   )
   private void purxiy$placeEnded(class_1750 var1, CallbackInfoReturnable<class_1269> var2) {
      ChunkRenderProvider.placeEnded(var1, (class_1269)var2.getReturnValue());
   }
}
