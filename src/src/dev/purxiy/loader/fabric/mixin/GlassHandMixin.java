// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.client.ClientRegistry22;
import net.minecraft.class_11659;
import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_759;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   value = {class_759.class},
   priority = 1100
)
public abstract class GlassHandMixin {
   private static final String RENDER_ARM_WITH_ITEM = "Lnet/minecraft/client/renderer/ItemInHandRenderer;renderArmWithItem(Lnet/minecraft/client/player/AbstractClientPlayer;FFLnet/minecraft/world/InteractionHand;FLnet/minecraft/world/item/ItemStack;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;I)V";

   @Inject(
      method = {"method_22976"},
      at = {@At("HEAD")}
   )
   private void purxiy$glassHands(float var1, class_4587 var2, class_11659 var3, class_746 var4, int var5, CallbackInfo var6) {
      ClientRegistry22.beginHands(var5);
   }

   @WrapOperation(
      method = {"method_22976"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_759;method_3228(Lnet/minecraft/class_742;FFLnet/minecraft/class_1268;FLnet/minecraft/class_1799;FLnet/minecraft/class_4587;Lnet/minecraft/class_11659;I)V"
      )}
   )
   private void purxiy$glassHand(
      class_759 var1,
      class_742 var2,
      float var3,
      float var4,
      class_1268 var5,
      float var6,
      class_1799 var7,
      float var8,
      class_4587 var9,
      class_11659 var10,
      int var11,
      Operation<Void> var12
   ) {
      var12.call(new Object[]{var1, var2, var3, var4, var5, var6, var7, var8, var9, ClientRegistry22.handCollector(var10), var11});
   }
}
