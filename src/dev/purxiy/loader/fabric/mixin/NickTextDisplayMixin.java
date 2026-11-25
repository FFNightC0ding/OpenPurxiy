package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.TabListProvider;
import net.minecraft.class_8113.class_8123;
import net.minecraft.class_8113.class_8123.class_8125;
import net.minecraft.class_8113.class_8123.class_8127;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_8123.class})
public abstract class NickTextDisplayMixin {
   @Shadow

   private class_8125 field_42442;
   @Unique
   private int purxiy$nickGeneration;

   @Inject(
      method = {"method_48905"},
      at = {@At("HEAD")}
   )
   private void purxiy$refresh(class_8127 var1, CallbackInfoReturnable<class_8125> var2) {
      int var3 = TabListProvider.generation();
      if (var3 != this.purxiy$nickGeneration) {
         this.purxiy$nickGeneration = var3;
         this.field_42442 = null;
      }
   }
}
