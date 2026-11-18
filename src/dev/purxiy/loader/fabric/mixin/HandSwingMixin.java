// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry17;
import dev.purxiy.loader.fabric.client.ClientRegistry25;
import net.minecraft.class_1268;
import net.minecraft.class_1309;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1309.class})
public abstract class HandSwingMixin {
   @Inject(
      method = {"method_23667(Lnet/minecraft/class_1268;Z)V"},
      at = {@At(
         value = "FIELD",
         target = "Lnet/minecraft/class_1309;field_6279:I",
         opcode = 181
      )}
   )
   private void purxiy$swingStarts(class_1268 var1, boolean var2, CallbackInfo var3) {
      if ((Object)this == class_310.method_1551().field_1724) {
         ClientRegistry17.onSwing(var1, ((HandSwingAccessor)this).purxiy$swingDuration());
         ClientRegistry25.onSwing();
      }
   }
}
