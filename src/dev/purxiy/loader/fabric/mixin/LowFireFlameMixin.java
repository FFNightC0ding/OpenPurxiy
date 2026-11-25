package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.FireOverlayPass;
import net.minecraft.class_10017;
import net.minecraft.class_11685;
import net.minecraft.class_11697;
import net.minecraft.class_4597;
import net.minecraft.class_4587.class_4665;
import org.joml.Quaternionf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_11685.class})
public abstract class LowFireFlameMixin {
   @Inject(
      method = {"method_73005"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$paletteFlame(class_4665 var1, class_4597 var2, class_10017 var3, Quaternionf var4, class_11697 var5, CallbackInfo var6) {
      if (FireOverlayPass.renderEntityFlame(var1, var2, var3, var4)) {
         var6.cancel();
      }
   }
}
