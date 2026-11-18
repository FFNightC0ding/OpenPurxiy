// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRenderer5;
import net.minecraft.class_310;
import net.minecraft.class_743;
import net.minecraft.class_744;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_743.class})
public abstract class MountInputMixin extends class_744 {
   @Inject(
      method = {"method_3129"},
      at = {@At("TAIL")}
   )
   private void purxiy$mountThrottle(CallbackInfo var1) {
      this.field_55868 = ClientRenderer5.moveInput(class_310.method_1551(), this.field_55868, this.field_54155);
      this.field_54155 = ClientRenderer5.keyInput(class_310.method_1551(), this.field_54155);
   }
}
