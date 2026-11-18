// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.ModuleState;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_12077;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_4066;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_9976;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_9976.class})
public abstract class WeatherEffectMixin {
   @Inject(
      method = {"method_62315"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$precipitation(class_1937 var1, int var2, float var3, class_243 var4, class_12077 var5, CallbackInfo var6) {
      if (ModuleState.moduleActive("weather") && !ModuleWidget.claimed("mirror-capture")) {
         var5.field_63103 = 0.0F;
         var5.field_63101.clear();
         var5.field_63102.clear();
         var6.cancel();
      }
   }

   @Inject(
      method = {"method_62319"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$rainSound(class_638 var1, class_4184 var2, int var3, class_4066 var4, int var5, CallbackInfo var6) {
      if (ModuleState.moduleActive("weather")) {
         var6.cancel();
      }
   }
}
