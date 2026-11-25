package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import net.minecraft.class_1309;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1309.class})
public abstract class JumpCirclesJumpMixin {
   @Inject(
      method = {"method_6043"},
      at = {@At("HEAD")}
   )
   private void purxiy$jumpCircle(CallbackInfo var1) {
      SkyPipelineRouter.jump((class_1309)(Object)(Object)this);
   }
}
