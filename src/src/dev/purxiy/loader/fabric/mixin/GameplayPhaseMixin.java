// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.module.ModuleCache;
import dev.purxiy.loader.fabric.module.ModuleSupport;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_310.class})
public abstract class GameplayPhaseMixin {
   @Inject(
      method = {"method_1574"},
      at = {@At("HEAD")}
   )
   private void purxiy$startTick(CallbackInfo var1) {
      class_310 var2 = (class_310)(Object)(Object)this;
      ModuleSupport.observe(var2);
      ModuleCache.drain(var2);
      ModuleWidget.clientTickStart(var2);
   }

   @Inject(
      method = {"method_1508"},
      at = {@At("HEAD")}
   )
   private void purxiy$input(CallbackInfo var1) {
      class_310 var2 = (class_310)(Object)(Object)this;
      ModuleSupport.input(var2);
      ModuleWidget.inputPhase(var2);
   }
}
