package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.client.TickRouter;
import net.minecraft.class_12076;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_9975;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_9975.class})
public abstract class SkyRendererMixin {
   @Inject(
      method = {"method_74926"},
      at = {@At("RETURN")}
   )
   private void purxiy$extract(class_638 var1, float var2, class_4184 var3, class_12076 var4, CallbackInfo var5) {
      SkyPipelineRouter.extract(var1, var4);
      var4.field_63097 = TickRouter.nature(var4.field_63097, "sky");
   }
}
