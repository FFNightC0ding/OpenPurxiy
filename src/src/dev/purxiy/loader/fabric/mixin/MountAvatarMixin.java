// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRenderer5;
import net.minecraft.class_10055;
import net.minecraft.class_1007;
import net.minecraft.class_11890;
import net.minecraft.class_243;
import net.minecraft.class_4587;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1007.class})
public abstract class MountAvatarMixin {
   @Inject(
      method = {"method_62604(Lnet/minecraft/class_11890;Lnet/minecraft/class_10055;F)V"},
      at = {@At("TAIL")}
   )
   private void purxiy$mountState(class_11890 var1, class_10055 var2, float var3, CallbackInfo var4) {
      ClientRenderer5.avatarState(var1, var2, var3);
   }

   @Inject(
      method = {"method_23206(Lnet/minecraft/class_10055;)Lnet/minecraft/class_243;"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$mountSeat(class_10055 var1, CallbackInfoReturnable<class_243> var2) {
      var2.setReturnValue(ClientRenderer5.avatarOffset(var1, (class_243)var2.getReturnValue()));
   }

   @Inject(
      method = {"method_4212(Lnet/minecraft/class_10055;Lnet/minecraft/class_4587;FF)V"},
      at = {@At("TAIL")}
   )
   private void purxiy$mountAttitude(class_10055 var1, class_4587 var2, float var3, float var4, CallbackInfo var5) {
      ClientRenderer5.avatarAttitude(var1, var2);
   }
}
