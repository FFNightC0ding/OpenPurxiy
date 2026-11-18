// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRenderer6;
import net.minecraft.class_1007;
import net.minecraft.class_5617.class_5618;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1007.class})
public abstract class CosmeticLayerMixin {
   @Inject(
      method = {"<init>"},
      at = {@At("TAIL")}
   )
   private void purxiy$wingsLayer(class_5618 var1, boolean var2, CallbackInfo var3) {
      class_1007 var4 = (class_1007)(Object)(Object)this;
      ((CosmeticLayerInvoker)var4).purxiy$addLayer(new ClientRenderer6(var4));
   }
}
