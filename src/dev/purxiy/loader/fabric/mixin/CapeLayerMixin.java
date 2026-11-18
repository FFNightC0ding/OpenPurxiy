// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.client.ClientRegistry23;
import dev.purxiy.loader.fabric.client.ClientRegistry26;
import dev.purxiy.loader.fabric.client.ClientRegistry27;
import net.minecraft.class_10055;
import net.minecraft.class_11659;
import net.minecraft.class_12249;
import net.minecraft.class_1921;
import net.minecraft.class_2960;
import net.minecraft.class_3879;
import net.minecraft.class_4587;
import net.minecraft.class_4608;
import net.minecraft.class_572;
import net.minecraft.class_972;
import net.minecraft.class_11683.class_11792;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_972.class})
public abstract class CapeLayerMixin {
   @Shadow
   @Final
   private class_572<class_10055> field_53206;

   @WrapOperation(
      method = {"method_4177(Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;ILnet/minecraft/class_10055;FF)V"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_11659;method_73489(Lnet/minecraft/class_3879;Ljava/lang/Object;Lnet/minecraft/class_4587;Lnet/minecraft/class_1921;IIILnet/minecraft/class_11683$class_11792;)V"
      )}
   )
   private void purxiy$cape(
      class_11659 var1,
      class_3879<?> var2,
      Object var3,
      class_4587 var4,
      class_1921 var5,
      int var6,
      int var7,
      int var8,
       class_11792 var9,
      Operation<Void> var10
   ) {
      if (!(var3 instanceof class_10055 var11 && ClientRegistry26.inClosedCabin(var11))) {
         if (!(var3 instanceof class_10055 var12 && ClientRegistry23.wears(var12.field_53520))) {
            var10.call(new Object[]{var1, var2, var3, var4, var5, var6, var7, var8, var9});
         } else if (ClientRegistry27.active()) {
            ClientRegistry27.submit(this.field_53206, var12, var4, var1, var6, var8);
         } else {
            var10.call(new Object[]{var1, var2, var3, var4, var5, var6, var7, var8, var9});
            var1.method_73489(
               this.field_53206,
               var12,
               var4,
               class_12249.method_76014(class_2960.method_60655("purxiy", "cape/glow")),
               var6,
               class_4608.field_21444,
               0,
               (class_11792)null
            );
         }
      }
   }
}
