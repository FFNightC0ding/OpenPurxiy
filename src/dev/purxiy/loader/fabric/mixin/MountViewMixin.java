package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.client.CameraStateReader;
import net.minecraft.class_11659;
import net.minecraft.class_4587;
import net.minecraft.class_746;
import net.minecraft.class_757;
import net.minecraft.class_759;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_757.class})
public abstract class MountViewMixin {
   @Inject(
      method = {"method_3186"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$seatedView(class_4587 var1, float var2, CallbackInfo var3) {
      if (CameraStateReader.cockpit()) {
         var3.cancel();
      }
   }

   @WrapOperation(
      method = {"method_3172"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_759;method_22976(FLnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_746;I)V"
      )}
   )
   private void purxiy$seatedHands(class_759 var1, float var2, class_4587 var3, class_11659 var4, class_746 var5, int var6, Operation<Void> var7) {
      if (!CameraStateReader.cockpit()) {
         var7.call(new Object[]{var1, var2, var3, var4, var5, var6});
      }
   }
}
