// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientTransform;
import dev.purxiy.ui.WBuilder;
import net.minecraft.class_757;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_757.class})
public abstract class Skins3DFrameMixin {
   @Inject(
      method = {"method_3192"},
      at = {@At("HEAD")}
   )
   private void purxiy$skins3dFrame(class_9779 var1, boolean var2, CallbackInfo var3) {
      WBuilder.reset();
      ClientTransform.frame();
   }
}
