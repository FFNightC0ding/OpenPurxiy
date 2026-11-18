// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.client.ClientRenderer7;
import net.minecraft.class_1058;
import net.minecraft.class_11659;
import net.minecraft.class_4587;
import net.minecraft.class_4597;
import net.minecraft.class_4603;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_4603.class})
public abstract class LowFireScreenMixin {
   private static final String RENDER_FIRE = "Lnet/minecraft/client/renderer/ScreenEffectRenderer;renderFire(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;)V";

   @WrapOperation(
      method = {"method_23067"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_4603;method_23070(Lnet/minecraft/class_4587;Lnet/minecraft/class_4597;Lnet/minecraft/class_1058;)V"
      )}
   )
   private void purxiy$lowFire(class_4587 var1, class_4597 var2, class_1058 var3, Operation<Void> var4) {
      if (!ClientRenderer7.replacesOverlay()) {
         float var5 = ClientRenderer7.vanillaDrop();
         var1.method_22903();
         var1.method_46416(0.0F, -var5, 0.0F);
         var4.call(new Object[]{var1, var2, var3});
         var1.method_22909();
      }
   }

   @Inject(
      method = {"method_23067"},
      at = {@At("TAIL")}
   )
   private void purxiy$drawLowFire(boolean var1, float var2, class_11659 var3, CallbackInfo var4) {
      ClientRenderer7.render(var1);
   }
}
