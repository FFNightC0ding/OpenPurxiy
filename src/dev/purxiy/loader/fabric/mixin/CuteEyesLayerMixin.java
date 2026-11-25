package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.SkinTextureProvider;
import net.minecraft.class_10017;
import net.minecraft.class_10042;
import net.minecraft.class_11659;
import net.minecraft.class_4587;
import net.minecraft.class_4606;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_4606.class})
public abstract class CuteEyesLayerMixin {
   @Inject(
      method = {"method_4199"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$cuteEyes(class_4587 var1, class_11659 var2, int var3, class_10017 var4, float var5, float var6, CallbackInfo var7) {
      if (var4 instanceof class_10042 var8 && SkinTextureProvider.applies(var8)) {
         var7.cancel();
      }
   }
}
