package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.WorldEventDispatcher;
import net.minecraft.class_1268;
import net.minecraft.class_1269;
import net.minecraft.class_1747;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_310;
import net.minecraft.class_3965;
import net.minecraft.class_636;
import net.minecraft.class_746;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_636.class})
public abstract class ProfileGameModeMixin {
   @Unique

   private class_2680 purxiy$clicked;
   @Unique

   private class_2680 purxiy$front;

   @Inject(
      method = {"method_2899"},
      at = {@At("RETURN")}
   )
   private void purxiy$profileBreak(class_2338 var1, CallbackInfoReturnable<Boolean> var2) {
      if (var2.getReturnValueZ()) {
         WorldEventDispatcher.onBlockBroken();
      }
   }

   @Inject(
      method = {"method_2896"},
      at = {@At("HEAD")}
   )
   private void purxiy$profileBeforeUse(class_746 var1, class_1268 var2, class_3965 var3, CallbackInfoReturnable<class_1269> var4) {
      class_310 var5 = class_310.method_1551();
      this.purxiy$clicked = null;
      if (var5.field_1687 != null && var1.method_5998(var2).method_7909() instanceof class_1747) {
         this.purxiy$clicked = var5.field_1687.method_8320(var3.method_17777());
         this.purxiy$front = var5.field_1687.method_8320(var3.method_17777().method_10093(var3.method_17780()));
      }
   }

   @Inject(
      method = {"method_2896"},
      at = {@At("RETURN")}
   )
   private void purxiy$profileAfterUse(class_746 var1, class_1268 var2, class_3965 var3, CallbackInfoReturnable<class_1269> var4) {
      class_310 var5 = class_310.method_1551();
      if (this.purxiy$clicked != null
         && var5.field_1687 != null
         && ((class_1269)var4.getReturnValue()).method_23665()
         && (
            var5.field_1687.method_8320(var3.method_17777()) != this.purxiy$clicked
               || var5.field_1687.method_8320(var3.method_17777().method_10093(var3.method_17780())) != this.purxiy$front
         )) {
         WorldEventDispatcher.onBlockPlaced();
      }

      this.purxiy$clicked = null;
      this.purxiy$front = null;
   }
}
