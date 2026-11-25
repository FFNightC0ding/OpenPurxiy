package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ChunkRenderProvider;
import net.minecraft.class_2680;
import net.minecraft.class_2804;
import net.minecraft.class_4076;
import net.minecraft.class_7522;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Pseudo
@Mixin(
   targets = {"net/caffeinemc/mods/sodium/client/world/cloned/ClonedChunkSection"},
   remap = false
)
public abstract class PlaceSodiumCloneMixin {
   @Shadow
   @Final
   private class_4076 pos;
   @Shadow
   @Final
   private class_7522<class_2680> blockData;
   @Shadow
   @Final
   private class_2804[] lightDataArrays;

   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")},
      require = 0
   )
   private void purxiy$placeHide(CallbackInfo var1) {
      ChunkRenderProvider.hideIn(this.blockData, this.pos.method_18674(), this.pos.method_18683(), this.pos.method_18687());
      ChunkRenderProvider.lightIn(this.lightDataArrays, this.pos.method_18674(), this.pos.method_18683(), this.pos.method_18687());
      ChunkRenderProvider.sectionCopied(this.pos.method_18674(), this.pos.method_18683(), this.pos.method_18687());
   }
}
