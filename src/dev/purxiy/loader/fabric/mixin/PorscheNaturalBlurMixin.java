package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.CameraStateReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Pseudo
@Mixin(
   targets = {"net/natural/motionblur/ShaderManager"},
   remap = false
)
public abstract class PorscheNaturalBlurMixin {
   @Inject(
      method = {"captureAllocator", "clearFrameAllocator", "beginFrame", "setFrameMotionBlur", "applyPreEntityBlur", "applyF5EntityRideBlur", "applyPostRenderVelocityOnly", "applyDeferredTemporalBlur"},
      at = {@At("HEAD")},
      cancellable = true,
      remap = false
   )
   private static void purxiy$skipMirrorBlur(CallbackInfo var0) {
      if (CameraStateReader.skipSecondaryFrame()) {
         var0.cancel();
      }
   }

   @ModifyArgs(
      method = {"setFrameMotionBlur"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/natural/motionblur/shader/CameraState;setFrame(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;FFF)V"
      ),
      remap = false
   )
   private static void purxiy$realFrameHistory(Args var0) {
      CameraStateReader.mainFrame(var0);
   }
}
