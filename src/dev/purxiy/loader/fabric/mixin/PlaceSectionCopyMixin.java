// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry6;
import net.minecraft.class_2680;
import net.minecraft.class_2818;
import net.minecraft.class_2841;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   targets = {"net/minecraft/class_6849"}
)
public abstract class PlaceSectionCopyMixin {
   @Shadow
   @Final
   private class_2841<class_2680> field_60947;

   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")}
   )
   private void purxiy$placeHide(class_2818 var1, int var2, CallbackInfo var3) {
      ClientRegistry6.hideIn(this.field_60947, var1.method_12004().field_9181, var1.method_31604(var2), var1.method_12004().field_9180);
   }
}
