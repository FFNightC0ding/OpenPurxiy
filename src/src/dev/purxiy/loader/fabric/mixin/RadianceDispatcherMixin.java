// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.client.ClientRegistry16;
import net.minecraft.class_10017;
import net.minecraft.class_11659;
import net.minecraft.class_12075;
import net.minecraft.class_1297;
import net.minecraft.class_4587;
import net.minecraft.class_897;
import net.minecraft.class_898;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_898.class})
public abstract class RadianceDispatcherMixin {
   private static final String RENDERER_SUBMIT = "Lnet/minecraft/client/renderer/entity/EntityRenderer;submit(Lnet/minecraft/client/renderer/entity/state/EntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V";

   @Inject(
      method = {"method_72977"},
      at = {@At("RETURN")}
   )
   private void purxiy$radianceExtract(class_1297 var1, float var2, CallbackInfoReturnable<class_10017> var3) {
      ClientRegistry16.extract(var1, (class_10017)var3.getReturnValue());
   }

   @WrapOperation(
      method = {"method_72976"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_897;method_3936(Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"
      )}
   )
   private void purxiy$radiance(class_897<?, ?> var1, class_10017 var2, class_4587 var3, class_11659 var4, class_12075 var5, Operation<Void> var6) {
      var6.call(new Object[]{var1, var2, var3, ClientRegistry16.collector(var2, var4), var5});
   }
}
