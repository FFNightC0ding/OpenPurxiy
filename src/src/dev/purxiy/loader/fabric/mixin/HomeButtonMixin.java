// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry21;
import net.minecraft.class_12225;
import net.minecraft.class_339;
import net.minecraft.class_4264;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_4264.class})
public abstract class HomeButtonMixin {
   @Inject(
      method = {"method_75793"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$label(class_12225 var1, CallbackInfo var2) {
      class_339 var3 = (class_339)(Object)(Object)this;
      if (ClientRegistry21.active() && ClientRegistry21.label(var3, var3.method_25369(), 2)) {
         var2.cancel();
      }
   }
}
