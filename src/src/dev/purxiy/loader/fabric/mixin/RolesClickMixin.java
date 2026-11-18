// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.LocalCommands;
import net.minecraft.class_2558;
import net.minecraft.class_310;
import net.minecraft.class_437;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_437.class})
public abstract class RolesClickMixin {
   @Inject(
      method = {"method_71999"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void purxiy$roles(class_2558 var0, class_310 var1, class_437 var2, CallbackInfo var3) {
      if (LocalCommands.click(var0)) {
         var3.cancel();
      }
   }
}
