// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.client.ClientRegistry13;
import net.minecraft.class_11659;
import net.minecraft.class_4587;
import net.minecraft.class_746;
import net.minecraft.class_757;
import net.minecraft.class_759;
import net.minecraft.class_9779;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_757.class})
public abstract class FreelookRenderMixin {
   @Unique
   private static final float purxiy$handDrop = 0.85F;

   @Inject(
      method = {"method_3192"},
      at = {@At("HEAD")}
   )
   private void purxiy$freelookFrame(class_9779 var1, boolean var2, CallbackInfo var3) {
   }

   @ModifyExpressionValue(
      method = {"method_3172"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_5498;method_31034()Z"
      )}
   )
   private boolean purxiy$freelookHands(boolean var1) {
      return var1 || ClientRegistry13.inHead();
   }

   @WrapOperation(
      method = {"method_3172"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_759;method_22976(FLnet/minecraft/class_4587;Lnet/minecraft/class_11659;Lnet/minecraft/class_746;I)V"
      )}
   )
   private void purxiy$sinkHands(class_759 var1, float var2, class_4587 var3, class_11659 var4, class_746 var5, int var6, Operation<Void> var7) {
      float var8 = ClientRegistry13.handsDown();
      if (!ClientRegistry13.active() || var8 <= 0.0F) {
         var7.call(new Object[]{var1, var2, var3, var4, var5, var6});
      } else if (!(var8 >= 1.0F)) {
         var3.method_22903();
         var3.method_46416(0.0F, -0.85F * var8 * var8, 0.0F);
         var7.call(new Object[]{var1, var2, var3, var4, var5, var6});
         var3.method_22909();
      }
   }
}
