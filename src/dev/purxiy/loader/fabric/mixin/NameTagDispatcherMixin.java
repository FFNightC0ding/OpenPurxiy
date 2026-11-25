package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.NameDecorationProvider;
import dev.purxiy.loader.fabric.client.NametagCapturePipeline;
import net.minecraft.class_10017;
import net.minecraft.class_1297;
import net.minecraft.class_898;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_898.class})
public abstract class NameTagDispatcherMixin {
   @Inject(
      method = {"method_72977"},
      at = {@At("RETURN")}
   )
   private void purxiy$nameTag(class_1297 var1, float var2, CallbackInfoReturnable<class_10017> var3) {
      NameDecorationProvider.decorate(var1, (class_10017)var3.getReturnValue());
      NametagCapturePipeline.extract(var1, (class_10017)var3.getReturnValue());
   }
}
