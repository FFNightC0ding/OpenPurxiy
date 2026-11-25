package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.loader.fabric.client.EntityMaskPipeline;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import net.minecraft.class_11279;
import net.minecraft.class_11659;
import net.minecraft.class_4587;
import net.minecraft.class_746;
import net.minecraft.class_757;
import net.minecraft.class_759;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_757.class})
public abstract class RadianceHandMixin {
   private static final String HANDS = "Lnet/minecraft/client/renderer/ItemInHandRenderer;renderHandsWithItems(FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/player/LocalPlayer;I)V";
   private static final String HUD_PROJECTION = "Lnet/minecraft/client/renderer/CachedPerspectiveProjectionMatrixBuffer;getBuffer(IIF)Lcom/mojang/blaze3d/buffers/GpuBufferSlice;";
   private static final String SCREEN_EFFECTS = "Lnet/minecraft/client/renderer/ScreenEffectRenderer;renderScreenEffect(ZFLnet/minecraft/client/renderer/SubmitNodeCollector;)V";

   @WrapOperation(
      method = {"method_3172"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_759;method_22976(FLnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_746;I)V"
      )}
   )
   private void purxiy$radianceHand(class_759 var1, float var2, class_4587 var3, class_11659 var4, class_746 var5, int var6, Operation<Void> var7) {
      var7.call(new Object[]{var1, var2, var3, EntityMaskPipeline.handCollector(var4), var5, var6});
      EntityMaskPipeline.drawHandMask();
   }

   @WrapOperation(
      method = {"method_3188"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_11279;method_71095(IIF)Lcom/mojang/blaze3d/buffers/GpuBufferSlice;"
      )}
   )
   private GpuBufferSlice purxiy$radianceHudProjection(class_11279 var1, int var2, int var3, float var4, Operation<GpuBufferSlice> var5) {
      EntityMaskPipeline.hudProjection(var2, var3, var4);
      return (GpuBufferSlice)var5.call(new Object[]{var1, var2, var3, var4});
   }

   @Inject(
      method = {"method_3188"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_4603;method_23067(ZFLnet/minecraft/class_11659;)V"
      )}
   )
   private void purxiy$radianceLight(class_9779 var1, CallbackInfo var2) {
      SkyPipelineRouter.handComposite();
      EntityMaskPipeline.composite();
   }
}
