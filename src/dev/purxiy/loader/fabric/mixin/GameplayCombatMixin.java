package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.module.ModuleSupport;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1713;
import net.minecraft.class_636;
import net.minecraft.class_746;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_636.class})
public abstract class GameplayCombatMixin {
   @Inject(
      method = {"method_2897"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$sustain(class_1657 var1, CallbackInfo var2) {
      if (var1 instanceof class_746 var3 && ModuleWidget.sustainUse(var3)) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"method_2918"},
      at = {@At("HEAD")}
   )
   private void purxiy$attacked(class_1657 var1, class_1297 var2, CallbackInfo var3) {
      ModuleSupport.attacked(var1, var2);
      ModuleWidget.actualAttack(var1, var2);
   }

   @Inject(
      method = {"method_2906"},
      at = {@At("HEAD")}
   )
   private void purxiy$inventory(int var1, int var2, int var3, class_1713 var4, class_1657 var5, CallbackInfo var6) {
      if (var5 instanceof class_746 var7) {
         ModuleSupport.clicked(var7);
      }
   }
}
