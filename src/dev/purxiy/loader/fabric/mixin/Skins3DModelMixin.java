package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.duck.ModuleOwner;
import dev.purxiy.loader.fabric.client.ClientTransform;
import net.minecraft.class_10055;
import net.minecraft.class_591;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_591.class})
public abstract class Skins3DModelMixin implements ModuleOwner {
   @Unique
   private boolean purxiy$body;
   @Unique
   private boolean purxiy$slim;

   @Override
   public boolean purxiy$body() {
      return this.purxiy$body;
   }

   @Override
   public boolean purxiy$slim() {
      return this.purxiy$slim;
   }

   @Override
   public void purxiy$body(boolean var1) {
      this.purxiy$body = true;
      this.purxiy$slim = var1;
   }

   @Inject(
      method = {"method_62110(Lnet/minecraft/class_10055;)V"},
      at = {@At("TAIL")}
   )
   private void purxiy$voxels(class_10055 var1, CallbackInfo var2) {
      class_591 var3 = (class_591)(Object)(Object)this;
      ClientTransform.shape(var3, var1);
      ClientTransform.pose(var3, var1);
   }
}
