// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRenderer3;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_636;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_636.class})
public abstract class RippleAttackMixin {
   @Inject(
      method = {"method_2918"},
      at = {@At("HEAD")}
   )
   private void purxiy$rippleAttack(class_1657 var1, class_1297 var2, CallbackInfo var3) {
      ClientRenderer3.onAttack(var2);
   }
}
