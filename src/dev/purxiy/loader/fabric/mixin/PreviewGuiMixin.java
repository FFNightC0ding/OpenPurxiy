// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.ClientTheme;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_329.class})
public abstract class PreviewGuiMixin {
   @Inject(
      method = {"method_1753"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$previewClear(class_332 var1, class_9779 var2, CallbackInfo var3) {
      if (ClientTheme.active()) {
         var3.cancel();
      }
   }
}
