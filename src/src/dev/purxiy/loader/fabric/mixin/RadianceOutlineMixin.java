// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry16;
import net.minecraft.class_1921;
import net.minecraft.class_4588;
import net.minecraft.class_4618;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_4618.class})
public abstract class RadianceOutlineMixin {
   @Shadow
   private int field_61740;

   @Inject(
      method = {"method_73477"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$radianceMask(class_1921 var1, CallbackInfoReturnable<class_4588> var2) {
      class_4588 var3 = ClientRegistry16.maskBuffer(this.field_61740, var1);
      if (var3 != null) {
         var2.setReturnValue(var3);
      }
   }
}
