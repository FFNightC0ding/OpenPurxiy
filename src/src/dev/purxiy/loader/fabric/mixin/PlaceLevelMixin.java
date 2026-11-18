// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry6;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_638.class})
public abstract class PlaceLevelMixin {
   @Inject(
      method = {"method_8413"},
      at = {@At("HEAD")}
   )
   private void purxiy$placeBlockChanged(class_2338 var1, class_2680 var2, class_2680 var3, int var4, CallbackInfo var5) {
      ClientRegistry6.blockChanged(var1, var2, var3);
   }

   @Inject(
      method = {"method_41928"},
      at = {@At("HEAD")}
   )
   private void purxiy$placeServerStarted(class_2338 var1, class_2680 var2, int var3, CallbackInfo var4) {
      ClientRegistry6.serverUpdateStarted(var1);
   }

   @Inject(
      method = {"method_41928"},
      at = {@At("RETURN")}
   )
   private void purxiy$placeServerEnded(class_2338 var1, class_2680 var2, int var3, CallbackInfo var4) {
      ClientRegistry6.serverUpdateEnded(var1);
   }
}
