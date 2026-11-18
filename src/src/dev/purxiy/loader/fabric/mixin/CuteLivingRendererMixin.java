// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.purxiy.loader.fabric.client.ClientRegistry15;
import net.minecraft.class_10042;
import net.minecraft.class_2960;
import net.minecraft.class_583;
import net.minecraft.class_922;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_922.class})
public abstract class CuteLivingRendererMixin {
   @Shadow
   protected class_583<?> field_4737;

   @ModifyExpressionValue(
      method = {"method_24302"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_922;method_3885(Lnet/minecraft/class_10042;)Lnet/minecraft/class_2960;"
      )}
   )
   private class_2960 purxiy$cute(class_2960 var1, @Local(argsOnly = true) class_10042 var2) {
      return ClientRegistry15.texture(var1, var2, this.field_4737);
   }
}
