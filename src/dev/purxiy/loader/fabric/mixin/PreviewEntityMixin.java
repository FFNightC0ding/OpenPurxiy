package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.ClientTheme;
import net.minecraft.class_1297;
import net.minecraft.class_4604;
import net.minecraft.class_898;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_898.class})
public abstract class PreviewEntityMixin {
   @Inject(
      method = {"method_3950"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$previewInside(class_1297 var1, class_4604 var2, double var3, double var5, double var7, CallbackInfoReturnable<Boolean> var9) {
      if (ClientTheme.hides(var1)) {
         var9.setReturnValue(false);
      }
   }
}
