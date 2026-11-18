// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.mojang.blaze3d.shaders.ShaderType;
import dev.purxiy.loader.fabric.client.ClientCodec;
import net.minecraft.class_10151;
import net.minecraft.class_2960;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_10151.class})
public abstract class OwnedShaderMixin {
   @Inject(
      method = {"method_68496"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$owned(class_2960 var1, ShaderType var2, CallbackInfoReturnable<String> var3) {
      String var4 = ClientCodec.shader(var1, var2);
      if (var4 != null) {
         var3.setReturnValue(var4);
      }
   }
}
