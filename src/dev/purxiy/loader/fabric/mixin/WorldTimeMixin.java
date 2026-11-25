package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.ModuleState;
import net.minecraft.class_1937;
import net.minecraft.class_638;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1937.class})
public abstract class WorldTimeMixin {
   @Inject(
      method = {"method_8532"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiyLoader$time(CallbackInfoReturnable<Long> var1) {
      if ((Object)this instanceof class_638 var2) {
         long var3 = var1.getReturnValueJ();
         long var5 = ModuleState.worldTime(var2, var3);
         if (var5 != var3) {
            var1.setReturnValue(var5);
         }
      }
   }
}
