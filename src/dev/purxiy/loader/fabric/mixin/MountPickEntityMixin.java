package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.CameraStateReader;
import net.minecraft.class_1297;
import net.minecraft.class_243;
import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1297.class})
public abstract class MountPickEntityMixin {
   private static boolean purxiy$localClient(class_1297 var0) {
      class_310 var1 = class_310.method_1551();
      return var1 != null && var1.method_18854() && var0 == var1.field_1724;
   }

   @Inject(
      method = {"method_5828"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$driverView(float var1, CallbackInfoReturnable<class_243> var2) {
      class_1297 var3 = (class_1297)(Object)(Object)this;
      if (purxiy$localClient(var3) && CameraStateReader.applies(var3)) {
         var2.setReturnValue(CameraStateReader.view(var3));
      }
   }

   @Inject(
      method = {"method_75117"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$driverHeadView(CallbackInfoReturnable<class_243> var1) {
      class_1297 var2 = (class_1297)(Object)(Object)this;
      if (purxiy$localClient(var2) && CameraStateReader.applies(var2)) {
         var1.setReturnValue(CameraStateReader.view(var2));
      }
   }

   @Inject(
      method = {"method_5836(F)Lnet/minecraft/class_243;"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$driverEye(float var1, CallbackInfoReturnable<class_243> var2) {
      class_1297 var3 = (class_1297)(Object)(Object)this;
      if (purxiy$localClient(var3) && CameraStateReader.applies(var3)) {
         var2.setReturnValue(CameraStateReader.eye(var3, (class_243)var2.getReturnValue()));
      }
   }

   @Inject(
      method = {"method_33571()Lnet/minecraft/class_243;"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$driverEyeNow(CallbackInfoReturnable<class_243> var1) {
      class_1297 var2 = (class_1297)(Object)(Object)this;
      if (purxiy$localClient(var2) && CameraStateReader.applies(var2)) {
         var1.setReturnValue(CameraStateReader.eye(var2, (class_243)var1.getReturnValue()));
      }
   }
}
