// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientUpdater;
import net.minecraft.class_10442;
import net.minecraft.class_10444;
import net.minecraft.class_11566;
import net.minecraft.class_1799;
import net.minecraft.class_1937;
import net.minecraft.class_811;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_10442.class})
public abstract class KnifeItemModelMixin {
   @Inject(
      method = {"method_65596"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$knifeItem(
      class_10444 var1, class_1799 var2, class_811 var3,  class_1937 var4,  class_11566 var5, int var6, CallbackInfo var7
   ) {
      if (ClientUpdater.update(var1, var2, var3, var5)) {
         var7.cancel();
      }
   }
}
