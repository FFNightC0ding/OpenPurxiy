// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientTransform;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_1060;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1060.class})
public abstract class Skins3DReloadMixin {
   @Inject(
      method = {"method_25931"},
      at = {@At("RETURN")}
   )
   private void purxiy$reload(CallbackInfoReturnable<CompletableFuture<Void>> var1) {
      ((CompletableFuture)var1.getReturnValue()).thenRun(() -> class_310.method_1551().execute(ClientTransform::reloaded));
   }
}
