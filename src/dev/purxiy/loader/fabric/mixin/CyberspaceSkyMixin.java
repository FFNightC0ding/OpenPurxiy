// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry7;
import net.minecraft.class_12076;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_9975;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_9975.class})
public abstract class CyberspaceSkyMixin {
   @Inject(
      method = {"method_74926"},
      at = {@At("RETURN")}
   )
   private void purxiy$cyberspaceSky(class_638 var1, float var2, class_4184 var3, class_12076 var4, CallbackInfo var5) {
      ClientRegistry7.sky(var4);
   }
}
