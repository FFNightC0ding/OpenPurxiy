// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_1713;
import net.minecraft.class_1735;
import net.minecraft.class_310;
import net.minecraft.class_465;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_465.class})
public abstract class GameplayContainerMixin {
   @Inject(
      method = {"method_2383"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$click(class_1735 var1, int var2, int var3, class_1713 var4, CallbackInfo var5) {
      class_310 var6 = class_310.method_1551();
      if (var6.field_1724 != null
         && ModuleWidget.click(var6.field_1761, var6.field_1724.field_7512.field_7763, var1 == null ? var2 : var1.field_7874, var3, var4, var6.field_1724)) {
         var5.cancel();
      }
   }
}
