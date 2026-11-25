package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.ClientTheme;
import net.minecraft.class_1297;
import net.minecraft.class_1937;
import net.minecraft.class_4184;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   value = {class_4184.class},
   priority = 1100
)
public abstract class PreviewCameraMixin {
   @Unique
   private static final float[] purxiy$previewRotation = new float[2];
   @Unique
   private static final double[] purxiy$previewPosition = new double[3];

   @Shadow
   protected abstract void method_19325(float var1, float var2);

   @Shadow
   protected abstract void method_19327(double var1, double var3, double var5);

   @Inject(
      method = {"method_19321"},
      at = {@At("TAIL")}
   )
   private void purxiy$previewCamera(class_1937 var1, class_1297 var2, boolean var3, boolean var4, float var5, CallbackInfo var6) {
      if (ClientTheme.place((class_4184)(Object)(Object)this, var1, var2, var5, purxiy$previewPosition, purxiy$previewRotation)) {
         this.method_19325(purxiy$previewRotation[0], purxiy$previewRotation[1]);
         this.method_19327(purxiy$previewPosition[0], purxiy$previewPosition[1], purxiy$previewPosition[2]);
      }
   }
}
