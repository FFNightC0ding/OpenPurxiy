// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.client.ClientRegistry9;
import net.minecraft.class_10017;
import net.minecraft.class_11659;
import net.minecraft.class_12075;
import net.minecraft.class_4587;
import net.minecraft.class_897;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_897.class})
public abstract class NameTagRendererMixin {
   private static final String SUBMIT_NAME_TAG = "Lnet/minecraft/client/renderer/entity/EntityRenderer;submitNameTag(Lnet/minecraft/client/renderer/entity/state/EntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V";

   @WrapOperation(
      method = {"method_3936"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_897;method_3926(Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"
      )}
   )
   private void purxiy$nameTag(class_897<?, ?> var1, class_10017 var2, class_4587 var3, class_11659 var4, class_12075 var5, Operation<Void> var6) {
      if (!ClientRegistry9.capture(var2, var3)) {
         var6.call(new Object[]{var1, var2, var3, var4, var5});
      }
   }
}
