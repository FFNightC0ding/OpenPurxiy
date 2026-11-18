// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientSupport3;
import net.minecraft.class_1297;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1297.class})
public abstract class ShockwaveEntityMixin {
   private static final String FALL_ON = "Lnet/minecraft/world/level/block/Block;fallOn(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/Entity;D)V";

   @Inject(
      method = {"method_5623"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2248;method_9554(Lnet/minecraft/class_1937;Lnet/minecraft/class_2680;Lnet/minecraft/class_2338;Lnet/minecraft/class_1297;D)V"
      )}
   )
   private void purxiy$shockwaveLand(double var1, boolean var3, class_2680 var4, class_2338 var5, CallbackInfo var6) {
      class_1297 var7 = (class_1297)(Object)(Object)this;
      ClientSupport3.land(var7, var7.field_6017);
   }

   @Inject(
      method = {"method_51297"},
      at = {@At("HEAD")}
   )
   private void purxiy$shockwaveStep(class_2338 var1, class_2680 var2, CallbackInfo var3) {
      ClientSupport3.step((class_1297)(Object)(Object)this);
   }
}
