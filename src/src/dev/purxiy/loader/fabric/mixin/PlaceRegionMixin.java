// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry6;
import dev.purxiy.loader.fabric.client.ClientSupport4;
import net.minecraft.class_1920;
import net.minecraft.class_1944;
import net.minecraft.class_2338;
import net.minecraft.class_853;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_853.class})
public abstract class PlaceRegionMixin implements class_1920 {
   @Shadow
   @Final
   private int field_60943;
   @Shadow
   @Final
   private int field_60944;
   @Shadow
   @Final
   private int field_60945;
   @Unique
   
   private ClientSupport4 purxiy$hiddenLight;

   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")}
   )
   private void purxiy$placeLight(CallbackInfo var1) {
      this.purxiy$hiddenLight = ClientRegistry6.lightIn(this.field_60943, this.field_60944, this.field_60945);
   }

   @Override
   public int method_8314(class_1944 var1, class_2338 var2) {
      ClientSupport4 var3 = this.purxiy$hiddenLight;
      if (var3 != null) {
         int var4 = var3.at(var1, var2);
         if (var4 >= 0) {
            return var4;
         }
      }

      return this.method_22336().method_15562(var1).method_15543(var2);
   }
}
