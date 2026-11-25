package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_746.class})
public abstract class GameplayPlayerMixin {
   @Inject(
      method = {"method_5773"},
      at = {@At("HEAD")}
   )
   private void purxiy$tick(CallbackInfo var1) {
      ModuleWidget.playerTick((class_746)(Object)(Object)this);
   }
}
