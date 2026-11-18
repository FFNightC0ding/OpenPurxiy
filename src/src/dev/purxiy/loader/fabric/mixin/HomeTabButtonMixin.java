// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry21;
import net.minecraft.class_12225;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_8209;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_8209.class})
public abstract class HomeTabButtonMixin {
   @Shadow
   public abstract boolean method_49611();

   @Inject(
      method = {"method_49608"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$label(class_12225 var1, CallbackInfo var2) {
      class_339 var3 = (class_339)(Object)(Object)this;
      if (ClientRegistry21.active() && ClientRegistry21.tabLabel(var3, var3.method_25369(), this.method_49611())) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"method_49610"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$underline(class_332 var1, class_327 var2, int var3, CallbackInfo var4) {
      if (ClientRegistry21.active()) {
         var4.cancel();
      }
   }
}
