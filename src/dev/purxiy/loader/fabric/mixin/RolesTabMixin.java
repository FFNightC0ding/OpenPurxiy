// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry14;
import net.minecraft.class_2561;
import net.minecraft.class_355;
import net.minecraft.class_640;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_355.class})
public abstract class RolesTabMixin {
   @Inject(
      method = {"method_1918"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$roles(class_640 var1, CallbackInfoReturnable<class_2561> var2) {
      class_2561 var3 = (class_2561)var2.getReturnValue();
      class_2561 var4 = ClientRegistry14.tab(var1, var3);
      if (var4 != var3) {
         var2.setReturnValue(var4);
      }
   }
}
