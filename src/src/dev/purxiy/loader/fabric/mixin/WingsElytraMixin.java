// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry11;
import net.minecraft.class_10034;
import net.minecraft.class_10055;
import net.minecraft.class_11659;
import net.minecraft.class_4587;
import net.minecraft.class_979;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_979.class})
public abstract class WingsElytraMixin {
   @Inject(
      method = {"method_17161(Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;ILnet/minecraft/class_10034;FF)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$wingsInstead(class_4587 var1, class_11659 var2, int var3, class_10034 var4, float var5, float var6, CallbackInfo var7) {
      if (var4 instanceof class_10055 var8 && ClientRegistry11.hidesElytra(var8)) {
         var7.cancel();
      }
   }
}
