package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.GuiRenderContext;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_7077;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_7077.class})
public abstract class HomePlainTextButtonMixin {
   @Inject(
      method = {"method_75752"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$link(class_332 var1, int var2, int var3, float var4, CallbackInfo var5) {
      class_339 var6 = (class_339)(Object)(Object)this;
      if (GuiRenderContext.active() && GuiRenderContext.link(var6, var6.method_25369())) {
         var5.cancel();
      }
   }
}
