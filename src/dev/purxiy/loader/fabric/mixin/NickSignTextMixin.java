// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry5;
import java.util.function.Function;
import net.minecraft.class_2561;
import net.minecraft.class_5481;
import net.minecraft.class_8242;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_8242.class})
public abstract class NickSignTextMixin {
   @Shadow
   
   private class_5481[] field_43305;
   @Unique
   private int purxiy$nickGeneration;

   @Inject(
      method = {"method_49868"},
      at = {@At("HEAD")}
   )
   private void purxiy$refresh(boolean var1, Function<class_2561, class_5481> var2, CallbackInfoReturnable<class_5481[]> var3) {
      int var4 = ClientRegistry5.generation();
      if (var4 != this.purxiy$nickGeneration) {
         this.purxiy$nickGeneration = var4;
         this.field_43305 = null;
      }
   }
}
