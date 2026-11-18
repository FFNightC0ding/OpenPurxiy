// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRenderer2;
import net.minecraft.class_156;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_3532;
import net.minecraft.class_4011;
import net.minecraft.class_4071;
import net.minecraft.class_425;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_425.class})
public abstract class HomeLoadingMixin {
   @Shadow
   @Final
   private class_310 field_18217;
   @Shadow
   @Final
   private class_4011 field_17767;
   @Shadow
   @Final
   private boolean field_18219;
   @Shadow
   private float field_17770;
   @Shadow
   private long field_17771;
   @Shadow
   private long field_18220;

   @Inject(
      method = {"method_25394"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$render(class_332 var1, int var2, int var3, float var4, CallbackInfo var5) {
      if (ClientRenderer2.takesOver()) {
         if (this.field_18219 && this.field_18220 == -1L) {
            this.field_18220 = class_156.method_658();
         }

         this.field_17770 = class_3532.method_15363(this.field_17770 * 0.95F + this.field_17767.method_18229() * 0.050000012F, 0.0F, 1.0F);
         if (ClientRenderer2.render((class_425)(Object)(Object)this, var1, var2, var3, var4, this.field_18219, this.field_17771, this.field_17770)) {
            this.field_18217.method_18502((class_4071)null);
         }

         var5.cancel();
      }
   }
}
