// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry5;
import net.minecraft.class_2703;
import net.minecraft.class_634;
import net.minecraft.class_2703.class_2705;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_634.class})
public abstract class NickPlayerListMixin {
   @Inject(
      method = {"method_11113"},
      at = {@At("TAIL")}
   )
   private void purxiy$playersChanged(class_2703 var1, CallbackInfo var2) {
      for (class_2705 var4 : var1.method_46330()) {
         ClientRegistry5.meet(var4.comp_1107());
      }

      if (!var1.method_46330().isEmpty()) {
         ClientRegistry5.playersChanged();
      }
   }
}
