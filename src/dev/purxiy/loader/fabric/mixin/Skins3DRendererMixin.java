package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_591;

import dev.purxiy.duck.ModuleOwner;
import dev.purxiy.loader.fabric.client.EquipmentRenderPass;
import dev.purxiy.loader.fabric.client.ClientTransform;
import net.minecraft.class_10055;
import net.minecraft.class_1007;
import net.minecraft.class_11659;
import net.minecraft.class_11890;
import net.minecraft.class_2960;
import net.minecraft.class_4587;
import net.minecraft.class_583;
import net.minecraft.class_630;
import net.minecraft.class_5617.class_5618;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1007.class})
public abstract class Skins3DRendererMixin {
   @Inject(
      method = {"<init>"},
      at = {@At("TAIL")}
   )
   private void purxiy$body(class_5618 var1, boolean var2, CallbackInfo var3) {
      class_583 var4 = ((class_591)(Object)((class_1007)(Object)(Object)this).method_4038());
      ((ModuleOwner)var4).purxiy$body(var2);
      EquipmentRenderPass.equipment(var1.method_64071());
   }

   @Inject(
      method = {"method_62604(Lnet/minecraft/class_11890;Lnet/minecraft/class_10055;F)V"},
      at = {@At("TAIL")}
   )
   private void purxiy$height(class_11890 var1, class_10055 var2, float var3, CallbackInfo var4) {
      ClientTransform.size(var1, var2);
      ClientTransform.hideArmor(var1, var2);
      EquipmentRenderPass.extract(var1, var2);
   }

   @Inject(
      method = {"method_23205"},
      at = {@At("HEAD")}
   )
   private void purxiy$hand(class_4587 var1, class_11659 var2, int var3, class_2960 var4, class_630 var5, boolean var6, CallbackInfo var7) {
      ClientTransform.hand(((class_591)(Object)((class_1007)(Object)(Object)this).method_4038()), var4, var5);
   }

   @Inject(
      method = {"method_23205"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_630;method_41923()V",
         shift = Shift.AFTER
      )}
   )
   private void purxiy$pawPose(class_4587 var1, class_11659 var2, int var3, class_2960 var4, class_630 var5, boolean var6, CallbackInfo var7) {
      ClientTransform.handPose(var5);
   }
}
