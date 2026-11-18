// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.loader.fabric.client.ClientApi;
import net.minecraft.class_1011;
import net.minecraft.class_7764;
import net.minecraft.class_7764.class_12298;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_7764.class})
public abstract class LowFireSpriteMixin implements ClientApi {
   @Shadow
   class_1011[] field_40540;
   @Unique
   private class_12298 purxiy$animation;

   @Inject(
      method = {"method_45818"},
      at = {@At("RETURN")}
   )
   private void purxiy$rememberAnimation(GpuBufferSlice var1, int var2, CallbackInfoReturnable<class_12298> var3) {
      this.purxiy$animation = (class_12298)var3.getReturnValue();
   }

   @Override
   public class_1011[] purxiy$images() {
      return this.field_40540;
   }

   @Override
   public class_12298 purxiy$animation() {
      return this.purxiy$animation;
   }
}
