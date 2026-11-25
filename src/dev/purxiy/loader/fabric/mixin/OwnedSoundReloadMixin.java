package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientLoader;
import net.minecraft.class_1144;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1144.class})
public abstract class OwnedSoundReloadMixin {
   @Inject(
      method = {"method_18182(Lnet/minecraft/class_1144$class_4009;Lnet/minecraft/class_3300;Lnet/minecraft/class_3695;)V"},
      at = {@At("RETURN")}
   )
   private void admittedSounds(CallbackInfo var1) {
      ClientLoader.reload();
   }
}
