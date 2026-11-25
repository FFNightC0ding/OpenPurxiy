package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.CameraStateReader;
import net.minecraft.class_1297;
import net.minecraft.class_4184;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin({class_4184.class})
public abstract class MountCameraMixin {
   @Shadow
   private class_1297 field_18711;
   @Shadow
   private boolean field_18719;
   @Shadow
   private float field_47549;

   @ModifyArgs(
      method = {"method_19321"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_4184;method_19327(DDD)V"
      )
   )
   private void purxiy$mountFocus(Args var1) {
      double[] var2 = new double[]{(Double)var1.get(0), (Double)var1.get(1), (Double)var1.get(2)};
      CameraStateReader.cameraPosition(this.field_18711, this.field_18719, this.field_47549, var2);

      for (int var3 = 0; var3 < 3; var3++) {
         if (!Double.isFinite(var2[var3])) {
            throw new IllegalStateException("Invalid garage camera");
         }

         var1.set(var3, var2[var3]);
      }
   }

   @ModifyArgs(
      method = {"method_19321"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_4184;method_19325(FF)V",
         ordinal = 1
      )
   )
   private void purxiy$drivingView(Args var1) {
      float[] var2 = new float[]{(Float)var1.get(0), (Float)var1.get(1)};
      CameraStateReader.cameraRotation(this.field_18711, this.field_47549, var2);

      for (int var3 = 0; var3 < 2; var3++) {
         if (!Float.isFinite(var2[var3])) {
            throw new IllegalStateException("Invalid garage rotation");
         }

         var1.set(var3, var2[var3]);
      }
   }

   @ModifyArg(
      method = {"method_19321"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_4184;method_19318(F)F"
      ),
      index = 0
   )
   private float purxiy$mountDistance(float var1) {
      return CameraStateReader.cameraDistance(this.field_18711, this.field_18719, this.field_47549, var1);
   }
}
