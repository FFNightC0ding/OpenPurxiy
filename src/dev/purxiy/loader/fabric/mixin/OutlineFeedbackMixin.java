package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_2338;
import net.minecraft.class_3965;
import net.minecraft.class_636;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_636.class})
public abstract class OutlineFeedbackMixin {
   @Inject(
      method = {"method_2899"},
      at = {@At("HEAD")}
   )
   private void purxiy$beforeBreak(class_2338 var1, CallbackInfoReturnable<Boolean> var2) {
      SkyPipelineRouter.beforeBreak(var1);
   }

   @Inject(
      method = {"method_2899"},
      at = {@At("RETURN")}
   )
   private void purxiy$afterBreak(class_2338 var1, CallbackInfoReturnable<Boolean> var2) {
      SkyPipelineRouter.afterBreak(var1, var2.getReturnValueZ());
   }

   @Inject(
      method = {"method_2896"},
      at = {@At("HEAD")}
   )
   private void purxiy$beforeUse(class_746 var1, class_1268 var2, class_3965 var3, CallbackInfoReturnable<class_1269> var4) {
      SkyPipelineRouter.beforeUse(var3);
   }

   @Inject(
      method = {"method_2896"},
      at = {@At("RETURN")}
   )
   private void purxiy$afterUse(class_746 var1, class_1268 var2, class_3965 var3, CallbackInfoReturnable<class_1269> var4) {
      SkyPipelineRouter.afterUse(var3, (class_1269)var4.getReturnValue());
   }
}
