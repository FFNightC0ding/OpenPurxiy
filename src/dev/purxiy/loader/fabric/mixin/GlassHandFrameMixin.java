// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry22;
import net.minecraft.class_757;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   value = {class_757.class},
   priority = 1100
)
public abstract class GlassHandFrameMixin {
   private static final String SCREEN_EFFECTS = "Lnet/minecraft/client/renderer/ScreenEffectRenderer;renderScreenEffect(ZFLnet/minecraft/client/renderer/SubmitNodeCollector;)V";

   @Inject(
      method = {"method_3188"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_4603;method_23067(ZFLnet/minecraft/class_11659;)V"
      )}
   )
   private void purxiy$glassHand(class_9779 var1, CallbackInfo var2) {
      ClientRegistry22.renderHand();
   }
}
