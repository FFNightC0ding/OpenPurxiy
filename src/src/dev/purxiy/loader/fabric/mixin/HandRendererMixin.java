// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.purxiy.loader.fabric.client.ClientRegistry17;
import dev.purxiy.loader.fabric.client.ClientRegistry19;
import dev.purxiy.loader.fabric.client.ClientRenderer10;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import net.minecraft.class_1007;
import net.minecraft.class_11659;
import net.minecraft.class_1268;
import net.minecraft.class_1306;
import net.minecraft.class_1664;
import net.minecraft.class_1799;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_759;
import net.minecraft.class_898;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_759.class})
public abstract class HandRendererMixin {
   private static final String RENDER_ARM_WITH_ITEM = "Lnet/minecraft/client/renderer/ItemInHandRenderer;renderArmWithItem(Lnet/minecraft/client/player/AbstractClientPlayer;FFLnet/minecraft/world/InteractionHand;FLnet/minecraft/world/item/ItemStack;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;I)V";
   private static final String SWING_ARM = "Lnet/minecraft/client/renderer/ItemInHandRenderer;swingArm(FLcom/mojang/blaze3d/vertex/PoseStack;ILnet/minecraft/world/entity/HumanoidArm;)V";
   private static final String RENDER_PLAYER_ARM = "Lnet/minecraft/client/renderer/ItemInHandRenderer;renderPlayerArm(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;IFFLnet/minecraft/world/entity/HumanoidArm;)V";
   private static final String ROTATION_DEGREES = "Lcom/mojang/math/Axis;rotationDegrees(F)Lorg/joml/Quaternionf;";
   private static final String ITEM_SWAP_SCALE = "Lnet/minecraft/client/player/LocalPlayer;getItemSwapScale(F)F";
   private static final String INSTANT_REPLACE = "Lnet/minecraft/client/renderer/ItemInHandRenderer;shouldInstantlyReplaceVisibleItem(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)Z";
   @Shadow
   @Final
   private class_898 field_4046;

   @Inject(
      method = {"method_22976"},
      at = {@At("HEAD")}
   )
   private void purxiy$handsBegin(float var1, class_4587 var2, class_11659 var3, class_746 var4, int var5, CallbackInfo var6) {
      ClientRegistry17.beginHands(var2, var1, var4);
      ClientRegistry19.beginHands(var5, ModuleWidget.claimed("hand-studio"));
   }

   @ModifyArg(
      method = {"method_22976"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_7833;rotationDegrees(F)Lorg/joml/Quaternionf;",
         ordinal = 0
      )
   )
   private float purxiy$swayPitch(float var1) {
      return ClientRegistry17.swayPitch(var1);
   }

   @ModifyArg(
      method = {"method_22976"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_7833;rotationDegrees(F)Lorg/joml/Quaternionf;",
         ordinal = 1
      )
   )
   private float purxiy$swayYaw(float var1) {
      return ClientRegistry17.swayYaw(var1);
   }

   @WrapOperation(
      method = {"method_22976"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_759;method_3228(Lnet/minecraft/class_742;FFLnet/minecraft/class_1268;FLnet/minecraft/class_1799;FLnet/minecraft/class_4587;Lnet/minecraft/class_11659;I)V"
      )}
   )
   private void purxiy$hand(
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
      class_1799 var13 = var7;
      float var14 = var8;
      class_1799 var15 = ClientRegistry17.preview(var5);
      if (var15 != null) {
         var13 = var15;
         var14 = 0.0F;
      }

      if (var5 == class_1268.field_5808 && ModuleWidget.claimed("hand-studio")) {
         var14 = ClientRegistry17.studioLowered(var13);
      }

      if (var5 == class_1268.field_5808) {
         ClientRegistry17.traceLowered(var14);
      }

      if (!var13.method_7960() || ClientRegistry17.emptyHandShown()) {
         var14 = ClientRegistry17.equip(var5, var14);
         class_1306 var16 = var5 == class_1268.field_5808 ? var2.method_6068() : var2.method_6068().method_5928();
         int var17 = var16 == class_1306.field_6183 ? 1 : -1;
         var9.method_22903();
         ClientRegistry17.place(var9, var5, var17);
         class_11659 var18 = ClientRegistry19.collector(var5, var10);

         try {
            if (!ClientRenderer10.render(var2, var5, var13, var6, var14, var9, var18, var11, this.field_4046)) {
               var12.call(new Object[]{var1, var2, var3, var4, var5, var6, var13, var14, var9, var18, var11});
            }
         } finally {
            ClientRegistry19.endHand();
            var9.method_22909();
         }
      }
   }

   @WrapOperation(
      method = {"method_3228"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_759;method_65816(FLnet/minecraft/class_4587;ILnet/minecraft/class_1306;)V"
      )}
   )
   private void purxiy$swing(
      class_759 var1, float var2, class_4587 var3, int var4, class_1306 var5, Operation<Void> var6, @Local(argsOnly = true) class_1268 var7
   ) {
      if (!ClientRegistry17.swingItem(var3, var7, var4)) {
         var6.call(new Object[]{var1, var2, var3, var4, var5});
      }
   }

   @WrapOperation(
      method = {"method_3228"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_759;method_3219(Lnet/minecraft/class_4587;Lnet/minecraft/class_11659;IFFLnet/minecraft/class_1306;)V"
      )}
   )
   private void purxiy$arm(
      class_759 var1,
      class_4587 var2,
      class_11659 var3,
      int var4,
      float var5,
      float var6,
      class_1306 var7,
      Operation<Void> var8,
      @Local(argsOnly = true) class_1268 var9
   ) {
      class_746 var10 = class_310.method_1551().field_1724;
      if (ClientRegistry17.swingsOwned() && var10 != null) {
         boolean var11 = var7 == class_1306.field_6183;
         ClientRegistry17.armPose(var2, var9, var11 ? 1 : -1, var5);
         class_1007 var12 = this.field_4046.method_74405(var10);
         class_2960 var13 = var10.method_52814().comp_1626().comp_3627();
         if (var11) {
            var12.method_4220(var2, var3, var4, var13, var10.method_74091(class_1664.field_7570));
         } else {
            var12.method_4221(var2, var3, var4, var13, var10.method_74091(class_1664.field_7568));
         }
      } else {
         var8.call(new Object[]{var1, var2, var3, var4, var5, var6, var7});
      }
   }

   @WrapOperation(
      method = {"method_3220"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_746;method_75194(F)F"
      )}
   )
   private float purxiy$cooldownDip(class_746 var1, float var2, Operation<Float> var3) {
      return ClientRegistry17.cooldownDip() ? (Float)var3.call(new Object[]{var1, var2}) : 1.0F;
   }

   @WrapOperation(
      method = {"method_3220"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_759;method_65910(Lnet/minecraft/class_1799;Lnet/minecraft/class_1799;)Z"
      )}
   )
   private boolean purxiy$instantSwitch(class_759 var1, class_1799 var2, class_1799 var3, Operation<Boolean> var4) {
      return ClientRegistry17.instantSwitch() || (Boolean)var4.call(new Object[]{var1, var2, var3});
   }
}
