package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.purxiy.loader.fabric.RenderHooks;
import java.util.List;
import net.minecraft.class_10017;
import net.minecraft.class_11659;
import net.minecraft.class_12075;
import net.minecraft.class_1297;
import net.minecraft.class_4587;
import net.minecraft.class_897;
import net.minecraft.class_898;
import net.minecraft.class_10017.class_11680;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_898.class})
public abstract class AfterglowDispatcherMixin {
   private static final String RENDERER_SUBMIT = "Lnet/minecraft/client/renderer/entity/EntityRenderer;submit(Lnet/minecraft/client/renderer/entity/state/EntityRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V";
   private static final String SUBMIT_SHADOW = "Lnet/minecraft/client/renderer/SubmitNodeCollector;submitShadow(Lcom/mojang/blaze3d/vertex/PoseStack;FLjava/util/List;)V";

   @Inject(
      method = {"method_72977"},
      at = {@At("RETURN")}
   )
   private void purxiy$extract(class_1297 var1, float var2, CallbackInfoReturnable<class_10017> var3) {
      RenderHooks.extract(var1, (class_10017)var3.getReturnValue());
   }

   @Inject(
      method = {"method_72976"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$beginSubmit(
      class_10017 var1, class_12075 var2, double var3, double var5, double var7, class_4587 var9, class_11659 var10, CallbackInfo var11
   ) {
      if (RenderHooks.beginSubmit(var1, var3, var5, var7)) {
         var11.cancel();
      }
   }

   @WrapOperation(
      method = {"method_72976"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_897;method_3936(Lnet/minecraft/class_10017;Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_12075;)V"
      )}
   )
   private void purxiy$dissolve(class_897<?, ?> var1, class_10017 var2, class_4587 var3, class_11659 var4, class_12075 var5, Operation<Void> var6) {
      var6.call(new Object[]{var1, var2, var3, RenderHooks.collector(var2, var4), var5});
   }

   @WrapOperation(
      method = {"method_72976"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_11659;method_73479(Lnet/minecraft/class_4587;FLjava/util/List;)V"
      )}
   )
   private void purxiy$shadow(
      class_11659 var1, class_4587 var2, float var3, List<class_11680> var4, Operation<Void> var5, @Local(argsOnly = true) class_10017 var6
   ) {
      var5.call(new Object[]{var1, var2, var3, RenderHooks.shadow(var6, var4)});
   }
}
