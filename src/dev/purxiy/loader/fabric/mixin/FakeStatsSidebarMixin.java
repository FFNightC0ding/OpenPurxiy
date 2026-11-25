package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.client.ChatCommandRouter;
import net.minecraft.class_2561;
import net.minecraft.class_266;
import net.minecraft.class_327;
import net.minecraft.class_329;
import net.minecraft.class_332;
import net.minecraft.class_5348;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_329.class})
public abstract class FakeStatsSidebarMixin {
   @Inject(
      method = {"method_1757"},
      at = {@At("HEAD")}
   )
   private void purxiy$beginSidebar(class_332 var1, class_266 var2, CallbackInfo var3) {
      ChatCommandRouter.beginSidebar();
   }

   @WrapOperation(
      method = {"method_1757"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_327;method_27525(Lnet/minecraft/class_5348;)I",
         ordinal = 1
      )}
   )
   private int purxiy$lineWidth(class_327 var1, class_5348 var2, Operation<Integer> var3) {
      Object[] var4 = new Object[]{var1, null};
      Object var5;
      if (var2 instanceof class_2561 var6) {
         var5 = ChatCommandRouter.lineForWidth(var6);
      } else {
         var5 = var2;
      }

      var4[1] = var5;
      return (Integer)var3.call(var4);
   }

   @ModifyArg(
      method = {"method_1757"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_332;method_51439(Lnet/minecraft/class_327;Lnet/minecraft/class_2561;IIIZ)V",
         ordinal = 1
      ),
      index = 1
   )
   private class_2561 purxiy$lineText(class_2561 var1) {
      return ChatCommandRouter.lineForDraw(var1);
   }
}
