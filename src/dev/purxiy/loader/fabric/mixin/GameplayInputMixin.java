package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_10185;
import net.minecraft.class_241;
import net.minecraft.class_743;
import net.minecraft.class_744;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_743.class})
public abstract class GameplayInputMixin extends class_744 {
   @Inject(
      method = {"method_3129"},
      at = {@At("RETURN")}
   )
   private void purxiy$input(CallbackInfo var1) {
      class_10185 var2 = ModuleWidget.input(this.field_54155);
      if (var2 != this.field_54155) {
         this.field_54155 = var2;
         this.field_55868 = new class_241(
               (var2.comp_3161() ? 1.0F : 0.0F) - (var2.comp_3162() ? 1.0F : 0.0F), (var2.comp_3159() ? 1.0F : 0.0F) - (var2.comp_3160() ? 1.0F : 0.0F)
            )
            .method_35581();
      }
   }
}
