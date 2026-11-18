// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.client.ClientRenderer5;
import net.minecraft.class_1297;
import net.minecraft.class_239;
import net.minecraft.class_746;
import net.minecraft.class_757;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_757.class})
public abstract class MountPickMixin {
   @WrapOperation(
      method = {"method_3190"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_746;method_76762(FLnet/minecraft/class_1297;)Lnet/minecraft/class_239;"
      )}
   )
   private class_239 purxiy$pickFromDriver(class_746 var1, float var2, class_1297 var3, Operation<class_239> var4) {
      return ClientRenderer5.pick(var3, var2, () -> (class_239)var4.call(new Object[]{var1, var2, var3}));
   }
}
