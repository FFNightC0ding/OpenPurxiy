// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry25;
import net.minecraft.class_1297;
import net.minecraft.class_3414;
import net.minecraft.class_3419;
import net.minecraft.class_638;
import net.minecraft.class_6880;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_638.class})
public abstract class KnifeSoundMixin {
   @Inject(
      method = {"method_8465(Lnet/minecraft/class_1297;DDDLnet/minecraft/class_6880;Lnet/minecraft/class_3419;FFJ)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$knifeQuietSword(
      class_1297 var1,
      double var2,
      double var4,
      double var6,
      class_6880<class_3414> var8,
      class_3419 var9,
      float var10,
      float var11,
      long var12,
      CallbackInfo var14
   ) {
      if (ClientRegistry25.mutes(var8, var2, var4, var6)) {
         var14.cancel();
      }
   }
}
