// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry21;
import dev.purxiy.loader.fabric.client.ClientRegistry3;
import net.minecraft.class_332;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_437.class})
public abstract class HomeScreenMixin {
   @Inject(
      method = {"method_57728"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$world(class_332 var1, float var2, CallbackInfo var3) {
      if (ClientRegistry3.drawBackground((class_437)(Object)(Object)this)) {
         var3.cancel();
      }
   }

   @Inject(
      method = {"method_57734"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$noBlur(class_332 var1, CallbackInfo var2) {
      if (ClientRegistry3.drawnThisFrame()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"method_57735(Lnet/minecraft/class_332;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$noBackdrop(class_332 var1, CallbackInfo var2) {
      if (ClientRegistry3.drawnThisFrame()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"method_47413"},
      at = {@At("HEAD")}
   )
   private void purxiy$skinBegin(class_332 var1, int var2, int var3, float var4, CallbackInfo var5) {
      ClientRegistry21.begin((class_437)(Object)(Object)this, var1, var2, var3);
   }

   @Inject(
      method = {"method_47413"},
      at = {@At("RETURN")}
   )
   private void purxiy$skinEnd(class_332 var1, int var2, int var3, float var4, CallbackInfo var5) {
      ClientRegistry21.end((class_437)(Object)(Object)this);
   }
}
