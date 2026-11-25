package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import net.minecraft.class_2664;
import net.minecraft.class_634;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_634.class})
public abstract class ChromaPacketMixin {
   @Inject(
      method = {"method_11124"},
      at = {@At("TAIL")}
   )
   private void purxiy$chromaBlast(class_2664 var1, CallbackInfo var2) {
      SkyPipelineRouter.blast(var1.comp_2883(), var1.comp_4594());
   }
}
