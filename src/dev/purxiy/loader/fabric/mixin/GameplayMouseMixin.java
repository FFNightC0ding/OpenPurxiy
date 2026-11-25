package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_11910;
import net.minecraft.class_310;
import net.minecraft.class_312;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_312.class})
public abstract class GameplayMouseMixin {
   @Inject(
      method = {"method_1601"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$button(long var1, class_11910 var3, int var4, CallbackInfo var5) {
      if (var1 == class_310.method_1551().method_22683().method_4490()) {
         ModuleWidget.mouseButton(var3.comp_4801(), var4);
         if (ModuleWidget.interceptMouse(var3.comp_4801(), var4)) {
            var5.cancel();
         }
      }
   }

   @Inject(
      method = {"method_1598"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$scroll(long var1, double var3, double var5, CallbackInfo var7) {
      if (var1 == class_310.method_1551().method_22683().method_4490() && ModuleWidget.scroll(var3, var5)) {
         var7.cancel();
      }
   }

   @ModifyExpressionValue(
      method = {"method_1606"},
      at = {@At(
         value = "FIELD",
         target = "Lnet/minecraft/class_312;field_1789:D"
      ), @At(
         value = "FIELD",
         target = "Lnet/minecraft/class_312;field_1787:D"
      )}
   )
   private double purxiy$mouse(double var1) {
      return ModuleWidget.mouse(var1);
   }
}
