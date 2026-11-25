package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.GuiRenderContext;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_7842;
import net.minecraft.class_8130;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_8130.class})
public abstract class HomeStringWidgetMixin {
   @Inject(
      method = {"method_48579"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$title(class_332 var1, int var2, int var3, float var4, CallbackInfo var5) {
      if (GuiRenderContext.active() && (Object)this instanceof class_7842) {
         class_339 var6 = (class_339)(Object)(Object)this;
         if (GuiRenderContext.title(var6, var6.method_25369())) {
            var5.cancel();
         }
      }
   }
}
