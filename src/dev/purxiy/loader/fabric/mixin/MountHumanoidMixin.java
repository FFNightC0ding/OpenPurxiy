package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.CameraStateReader;
import net.minecraft.class_10034;
import net.minecraft.class_572;
import net.minecraft.class_630;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_572.class})
public abstract class MountHumanoidMixin {
   @Shadow
   @Final
   public class_630 field_3401;
   @Shadow
   @Final
   public class_630 field_27433;
   @Shadow
   @Final
   public class_630 field_3392;
   @Shadow
   @Final
   public class_630 field_3397;

   @Inject(
      method = {"method_17087(Lnet/minecraft/class_10034;)V"},
      at = {@At("TAIL")}
   )
   private void purxiy$mountPose(class_10034 var1, CallbackInfo var2) {
      CameraStateReader.humanoid((class_572<?>)(class_572<?>)(Object)this, var1);
   }
}
