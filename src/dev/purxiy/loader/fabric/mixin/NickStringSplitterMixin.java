package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.TabListProvider;
import net.minecraft.class_5225;
import net.minecraft.class_5348;
import net.minecraft.class_5481;
import net.minecraft.class_5225.class_5231;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_5225.class})
public abstract class NickStringSplitterMixin {
   @Shadow
   @Final
   class_5231 field_24216;

   @Inject(
      method = {"method_27482(Ljava/lang/String;)F"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$widthOfString( String var1, CallbackInfoReturnable<Float> var2) {
      if (var1 != null && TabListProvider.active()) {
         var2.setReturnValue(TabListProvider.width(var1, this.field_24216));
      }
   }

   @Inject(
      method = {"method_27488(Lnet/minecraft/class_5348;)F"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$widthOfText(class_5348 var1, CallbackInfoReturnable<Float> var2) {
      if (!(var1 instanceof TabListProvider.Listener) && TabListProvider.active()) {
         var2.setReturnValue(TabListProvider.width(var1, this.field_24216));
      }
   }

   @ModifyVariable(
      method = {"method_30875(Lnet/minecraft/class_5481;)F"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private class_5481 purxiy$widthOfSequence(class_5481 var1) {
      return TabListProvider.sequence(var1);
   }

   @ModifyVariable(
      method = {"method_27490"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private class_5348 purxiy$cut(class_5348 var1) {
      return TabListProvider.text(var1);
   }

   @ModifyVariable(
      method = {"method_29971(Lnet/minecraft/class_5348;ILnet/minecraft/class_2583;Ljava/util/function/BiConsumer;)V"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private class_5348 purxiy$wrap(class_5348 var1) {
      return TabListProvider.text(var1);
   }
}
