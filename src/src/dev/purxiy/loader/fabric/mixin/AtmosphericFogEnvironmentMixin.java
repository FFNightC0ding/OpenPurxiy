// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientSupport3;
import dev.purxiy.loader.fabric.client.ClientUpdater2;
import net.minecraft.class_11398;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_11398.class})
public abstract class AtmosphericFogEnvironmentMixin {
   @Inject(
      method = {"method_71654"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$skyFog(class_638 var1, class_4184 var2, int var3, float var4, CallbackInfoReturnable<Integer> var5) {
      var5.setReturnValue(ClientUpdater2.nature(ClientSupport3.fogColor(var1, var5.getReturnValueI()), "fog"));
   }
}
