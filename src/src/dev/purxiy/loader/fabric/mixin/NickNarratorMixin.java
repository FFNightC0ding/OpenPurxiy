// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry5;
import net.minecraft.class_333;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin({class_333.class})
public abstract class NickNarratorMixin {
   @ModifyVariable(
      method = {"method_66522"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private String purxiy$hideNames(String var1) {
      return ClientRegistry5.plain(var1);
   }
}
