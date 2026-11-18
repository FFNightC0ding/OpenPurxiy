// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_11908;
import net.minecraft.class_309;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_309.class})
public abstract class GameplayKeyboardMixin {
   @Inject(
      method = {"method_1466"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$key(long var1, int var3, class_11908 var4, CallbackInfo var5) {
      if (var1 == class_310.method_1551().method_22683().method_4490()) {
         ModuleWidget.key(var4.comp_4795(), var4.comp_4796(), var3);
         if (ModuleWidget.interceptKey(var4.comp_4795(), var4.comp_4796(), var3)) {
            var5.cancel();
         }
      }
   }
}
