// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRegistry13;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_4184.class})
public abstract class FreelookCameraMixin {
   @Shadow
   private class_243 field_18712;
   @Shadow
   private float field_18717;
   @Shadow
   private float field_18718;
   @Shadow
   private boolean field_18719;
   @Shadow
   @Final
   private Vector3f field_18714;
   @Unique
   private class_243 purxiy$pivot;

   @Shadow
   protected abstract void method_19325(float var1, float var2);

   @Shadow
   protected abstract void method_19327(double var1, double var3, double var5);

   @Inject(
      method = {"method_19321"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_4184;method_19327(DDD)V",
         shift = Shift.AFTER
      ), @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_4184;method_19322(Lnet/minecraft/class_243;)V",
         shift = Shift.AFTER
      )}
   )
   private void pivot(class_1937 var1, class_1297 var2, boolean var3, boolean var4, float var5, CallbackInfo var6) {
      this.purxiy$pivot = this.field_18712;
   }

   @Inject(
      method = {"method_19321"},
      at = {@At("TAIL")}
   )
   private void apply(class_1937 var1, class_1297 var2, boolean var3, boolean var4, float var5, CallbackInfo var6) {
      class_243 var7 = this.purxiy$pivot;
      this.purxiy$pivot = null;
      if (var7 != null && (Object)this == class_310.method_1551().field_1773.method_19418()) {
         ClientRegistry13.TypeK var8 = ClientRegistry13.position(var1, var2, var7, this.field_18712, this.field_18718, this.field_18717, this.field_18714);
         if (var8 != null) {
            this.method_19325(var8.yaw(), var8.pitch());
            class_243 var9 = var8.position();
            this.method_19327(var9.field_1352, var9.field_1351, var9.field_1350);
            if (var8.hideBody()) {
               this.field_18719 = false;
            }
         }
      }
   }
}
