// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRenderer5;
import net.minecraft.class_304;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_304.class})
public abstract class PorscheTouchKeyMixin {
   @Inject(
      method = {"method_23481"},
      at = {@At("TAIL")}
   )
   private void purxiy$touchPress(boolean var1, CallbackInfo var2) {
      ClientRenderer5.keyState((class_304)(Object)(Object)this, var1);
   }
}
