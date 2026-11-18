// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import net.minecraft.class_761;
import net.minecraft.class_9916;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_761.class})
public abstract class SkyPassMixin {
   @WrapOperation(
      method = {"method_62200"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_9916;method_61929(Ljava/lang/Runnable;)V"
      )}
   )
   private void purxiy$skyPass(class_9916 var1, Runnable var2, Operation<Void> var3, @Local(argsOnly = true) GpuBufferSlice var4) {
      var3.call(new Object[]{var1, (Runnable)() -> {
         RenderSystem.setShaderFog(var4);
         if (!ClientSupport3.replaceSky()) {
            var2.run();
            ClientSupport3.skyOverlay();
         }
      }});
   }
}
