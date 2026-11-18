// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.loader.fabric.client.ClientRegistry17;
import dev.purxiy.loader.fabric.client.ClientRegistry19;
import net.minecraft.class_11279;
import net.minecraft.class_757;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_757.class})
public abstract class HandFrameMixin {
   private static final String HUD_PROJECTION = "Lnet/minecraft/client/renderer/CachedPerspectiveProjectionMatrixBuffer;getBuffer(IIF)Lcom/mojang/blaze3d/buffers/GpuBufferSlice;";

   @Inject(
      method = {"method_3188"},
      at = {@At("HEAD")}
   )
   private void purxiy$handFrame(class_9779 var1, CallbackInfo var2) {
      ClientRegistry17.frame();
   }

   @WrapOperation(
      method = {"method_3188"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_11279;method_71095(IIF)Lcom/mojang/blaze3d/buffers/GpuBufferSlice;"
      )}
   )
   private GpuBufferSlice purxiy$handProjection(class_11279 var1, int var2, int var3, float var4, Operation<GpuBufferSlice> var5) {
      ClientRegistry19.hudProjection(var2, var3, var4);
      return (GpuBufferSlice)var5.call(new Object[]{var1, var2, var3, var4});
   }
}
