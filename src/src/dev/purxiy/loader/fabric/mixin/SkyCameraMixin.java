// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import net.minecraft.class_4587;
import net.minecraft.class_757;
import net.minecraft.class_9779;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_757.class})
public abstract class SkyCameraMixin {
   @Inject(
      method = {"method_3188"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_757;method_3186(Lnet/minecraft/class_4587;F)V"
      )}
   )
   private void purxiy$beforeBobbing(class_9779 var1, CallbackInfo var2, @Local Matrix4f var3, @Local class_4587 var4) {
      ClientSupport3.steady(var3, var4.method_23760().method_23761());
   }

   @Inject(
      method = {"method_3188"},
      at = {@At(
         value = "INVOKE",
         target = "Lcom/mojang/blaze3d/systems/RenderSystem;setProjectionMatrix(Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lnet/minecraft/class_10366;)V",
         ordinal = 0
      )}
   )
   private void purxiy$projection(class_9779 var1, CallbackInfo var2, @Local Matrix4f var3) {
      ClientSupport3.projection(var3);
   }
}
