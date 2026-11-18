// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.loader.fabric.client.ClientRegistry22;
import net.minecraft.class_11279;
import net.minecraft.class_757;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(
   value = {class_757.class},
   priority = 800
)
public abstract class GlassFrameMixin {
   private static final String RENDER_LEVEL = "Lnet/minecraft/client/renderer/LevelRenderer;renderLevel(Lcom/mojang/blaze3d/resource/GraphicsResourceAllocator;Lnet/minecraft/client/DeltaTracker;ZLnet/minecraft/client/Camera;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lcom/mojang/blaze3d/buffers/GpuBufferSlice;Lorg/joml/Vector4f;Z)V";
   private static final String HUD_PROJECTION = "Lnet/minecraft/client/renderer/CachedPerspectiveProjectionMatrixBuffer;getBuffer(IIF)Lcom/mojang/blaze3d/buffers/GpuBufferSlice;";

   @WrapOperation(
      method = {"method_3188"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_11279;method_71095(IIF)Lcom/mojang/blaze3d/buffers/GpuBufferSlice;"
      )}
   )
   private GpuBufferSlice purxiy$glassHudProjection(class_11279 var1, int var2, int var3, float var4, Operation<GpuBufferSlice> var5) {
      ClientRegistry22.hudProjection(var2, var3, var4);
      return (GpuBufferSlice)var5.call(new Object[]{var1, var2, var3, var4});
   }
}
