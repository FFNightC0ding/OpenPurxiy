package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.FirstPersonProvider;
import net.minecraft.class_761;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin({class_761.class})
public abstract class FreelookCullingMixin {
   @ModifyArg(
      method = {"method_22710"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_761;method_74752(Lnet/minecraft/class_4184;Lnet/minecraft/class_4604;Z)V"
      ),
      index = 2
   )
   private boolean purxiy$freelookCulling(boolean var1) {
      return var1 || FirstPersonProvider.passesWalls();
   }
}
