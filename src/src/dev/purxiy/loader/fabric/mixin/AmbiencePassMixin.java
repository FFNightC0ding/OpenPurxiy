// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import net.minecraft.class_761;
import net.minecraft.class_9909;
import net.minecraft.class_9960;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_761.class})
public abstract class AmbiencePassMixin {
   @Shadow
   @Final
   private class_9960 field_53081;

   @Inject(
      method = {"method_62201"},
      at = {@At("TAIL")}
   )
   private void purxiy$ambience(class_9909 var1, GpuBufferSlice var2, CallbackInfo var3) {
      ClientSupport3.particles(var1, this.field_53081, var2);
   }
}
