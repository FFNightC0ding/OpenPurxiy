// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry21;
import net.minecraft.class_1144;
import net.minecraft.class_332;
import net.minecraft.class_339;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_339.class})
public abstract class HomeWidgetMixin {
   private static final String RENDER_WIDGET = "Lnet/minecraft/client/gui/components/AbstractWidget;renderWidget(Lnet/minecraft/client/gui/GuiGraphics;IIF)V";

   @Inject(
      method = {"method_25394"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_339;method_48579(Lnet/minecraft/class_332;IIF)V"
      )}
   )
   private void purxiy$begin(class_332 var1, int var2, int var3, float var4, CallbackInfo var5) {
      ClientRegistry21.widgetBegin((class_339)(Object)(Object)this, var1);
   }

   @Inject(
      method = {"method_25394"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_339;method_48579(Lnet/minecraft/class_332;IIF)V",
         shift = Shift.AFTER
      )}
   )
   private void purxiy$end(class_332 var1, int var2, int var3, float var4, CallbackInfo var5) {
      ClientRegistry21.widgetEnd((class_339)(Object)(Object)this, var1);
   }

   @Inject(
      method = {"method_25354"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$click(class_1144 var1, CallbackInfo var2) {
      if (ClientRegistry21.click()) {
         var2.cancel();
      }
   }
}
