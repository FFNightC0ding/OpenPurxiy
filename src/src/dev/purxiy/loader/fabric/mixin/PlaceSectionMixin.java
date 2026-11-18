// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry6;
import net.minecraft.class_11516;
import net.minecraft.class_11517;
import net.minecraft.class_4076;
import net.minecraft.class_6850;
import net.minecraft.class_846.class_851;
import net.minecraft.class_846.class_851.class_4577;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_851.class})
public abstract class PlaceSectionMixin {
   @Shadow
   public abstract long method_62975();

   @Inject(
      method = {"method_3674"},
      at = {@At("HEAD")}
   )
   private void purxiy$placeCopied(class_6850 var1, CallbackInfoReturnable<class_4577> var2) {
      long var3 = this.method_62975();
      ClientRegistry6.sectionCopied(class_4076.method_18686(var3), class_4076.method_18689(var3), class_4076.method_18690(var3));
   }

   @Inject(
      method = {"method_72049"},
      at = {@At("RETURN")}
   )
   private void purxiy$placeMeshed(class_11517 var1, CallbackInfo var2) {
      if (var1 != class_11516.field_60935) {
         long var3 = this.method_62975();
         ClientRegistry6.sectionMeshed(class_4076.method_18686(var3), class_4076.method_18689(var3), class_4076.method_18690(var3));
      }
   }
}
