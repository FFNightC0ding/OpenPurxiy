// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry4;
import net.minecraft.class_10055;
import net.minecraft.class_1007;
import net.minecraft.class_11890;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1007.class})
public abstract class HaloRendererMixin {
   @Inject(
      method = {"method_62604(Lnet/minecraft/class_11890;Lnet/minecraft/class_10055;F)V"},
      at = {@At("TAIL")}
   )
   private void purxiy$haloName(class_11890 var1, class_10055 var2, float var3, CallbackInfo var4) {
      ClientRegistry4.liftName(var1, var2);
   }
}
