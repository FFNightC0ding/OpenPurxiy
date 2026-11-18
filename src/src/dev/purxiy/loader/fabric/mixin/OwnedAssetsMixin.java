// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry24;
import java.util.List;
import java.util.Optional;
import net.minecraft.class_2960;
import net.minecraft.class_3294;
import net.minecraft.class_3298;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_3294.class})
public abstract class OwnedAssetsMixin {
   @Inject(
      method = {"method_14486"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void owned(class_2960 var1, CallbackInfoReturnable<Optional<class_3298>> var2) {
      Optional var3 = ClientRegistry24.resource(var1);
      if (var3.isPresent()) {
         var2.setReturnValue(var3);
      }
   }

   @Inject(
      method = {"method_14489"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void stack(class_2960 var1, CallbackInfoReturnable<List<class_3298>> var2) {
      Optional var3 = ClientRegistry24.resource(var1);
      if (var3.isPresent()) {
         var2.setReturnValue(List.of((class_3298)var3.get()));
      }
   }
}
