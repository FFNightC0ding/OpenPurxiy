// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.ui.WBuilder;
import net.minecraft.class_10055;
import net.minecraft.class_1306;
import net.minecraft.class_4587;
import net.minecraft.class_591;
import net.minecraft.class_630;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_591.class})
public abstract class CharacterHandMixin {
   @Inject(
      method = {"method_72859(Lnet/minecraft/class_10055;Lnet/minecraft/class_1306;Lnet/minecraft/class_4587;)V"},
      at = {@At("TAIL")}
   )
   private void purxiy$forearm(class_10055 var1, class_1306 var2, class_4587 var3, CallbackInfo var4) {
      class_630 var5 = ((class_591)(Object)(Object)this).method_2808(var2);
      WBuilder.hand(var5, ((ModelPartAccessor)(Object)(var5)).purxiy$cubes(), var3);
   }
}
