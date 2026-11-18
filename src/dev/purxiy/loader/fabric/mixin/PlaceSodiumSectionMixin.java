// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry6;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(
   targets = {"net/caffeinemc/mods/sodium/client/render/chunk/RenderSection"},
   remap = false
)
public abstract class PlaceSodiumSectionMixin {
   @Shadow
   @Final
   private int chunkX;
   @Shadow
   @Final
   private int chunkY;
   @Shadow
   @Final
   private int chunkZ;

   @Inject(
      method = {"setInfo"},
      at = {@At("RETURN")},
      require = 0
   )
   private void purxiy$placeMeshed(CallbackInfoReturnable<Boolean> var1) {
      ClientRegistry6.sectionMeshed(this.chunkX, this.chunkY, this.chunkZ);
   }
}
