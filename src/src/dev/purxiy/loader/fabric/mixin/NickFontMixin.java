// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.client.ClientRegistry5;
import net.minecraft.class_2583;
import net.minecraft.class_327;
import net.minecraft.class_5224;
import net.minecraft.class_5481;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin({class_327.class})
public abstract class NickFontMixin {
   @ModifyVariable(
      method = {"method_71795(Lnet/minecraft/class_5481;FFIZZI)Lnet/minecraft/class_327$class_11465;"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private class_5481 purxiy$hideNames(class_5481 var1) {
      return ClientRegistry5.sequence(var1);
   }

   @WrapOperation(
      method = {"method_71796(Ljava/lang/String;FFIZI)Lnet/minecraft/class_327$class_11465;"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_5223;method_27479(Ljava/lang/String;Lnet/minecraft/class_2583;Lnet/minecraft/class_5224;)Z"
      )}
   )
   private boolean purxiy$hideNames(String var1, class_2583 var2, class_5224 var3, Operation<Boolean> var4) {
      return ClientRegistry5.active() ? ClientRegistry5.iterate(var1, var2, var3) : (Boolean)var4.call(new Object[]{var1, var2, var3});
   }

   @ModifyVariable(
      method = {"method_37296"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private class_5481 purxiy$hideOutlined(class_5481 var1) {
      return ClientRegistry5.sequence(var1);
   }
}
