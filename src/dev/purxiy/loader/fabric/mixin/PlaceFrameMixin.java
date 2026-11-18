// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry6;
import net.minecraft.class_11658;
import net.minecraft.class_11661;
import net.minecraft.class_4587;
import net.minecraft.class_761;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_761.class})
public abstract class PlaceFrameMixin {
   @Inject(
      method = {"method_22710"},
      at = {@At("HEAD")}
   )
   private void purxiy$placeFrame(CallbackInfo var1) {
      ClientRegistry6.beginFrame();
   }

   @Inject(
      method = {"method_62208"},
      at = {@At("TAIL")}
   )
   private void purxiy$placeSubmit(class_4587 var1, class_11658 var2, class_11661 var3, CallbackInfo var4) {
      ClientRegistry6.submit(var1, var2, var3);
   }
}
