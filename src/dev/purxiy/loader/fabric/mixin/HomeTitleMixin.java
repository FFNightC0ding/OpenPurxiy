package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.client.GuiRenderContext;
import dev.purxiy.loader.fabric.client.BackgroundStateProvider;
import dev.purxiy.loader.fabric.client.MenuRenderPass;
import net.minecraft.class_11909;
import net.minecraft.class_2561;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_8020;
import net.minecraft.class_8519;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_442.class})
public abstract class HomeTitleMixin extends class_437 {
   @Shadow
   private boolean field_18222;
   @Shadow

   private class_8519 field_2586;

   protected HomeTitleMixin(class_2561 var1) {
      super(var1);
   }

   @Inject(
      method = {"method_25426"},
      at = {@At("HEAD")}
   )
   private void purxiy$noFade(CallbackInfo var1) {
      if (GuiRenderContext.skins(this)) {
         this.field_18222 = false;
      }
   }

   @WrapOperation(
      method = {"method_25394"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_8020;method_48209(Lnet/minecraft/class_332;IF)V"
      )}
   )
   private void purxiy$logo(class_8020 var1, class_332 var2, int var3, float var4, Operation<Void> var5) {
      if (!GuiRenderContext.active()) {
         var5.call(new Object[]{var1, var2, var3, var4});
      }
   }

   @WrapOperation(
      method = {"method_25394"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_8519;method_51453(Lnet/minecraft/class_332;ILnet/minecraft/class_327;F)V"
      )}
   )
   private void purxiy$splash(class_8519 var1, class_332 var2, int var3, class_327 var4, float var5, Operation<Void> var6) {
      if (!GuiRenderContext.active()) {
         var6.call(new Object[]{var1, var2, var3, var4, var5});
      }
   }

   @WrapOperation(
      method = {"method_25394"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_332;method_25303(Lnet/minecraft/class_327;Ljava/lang/String;III)V"
      )}
   )
   private void purxiy$version(class_332 var1, class_327 var2, String var3, int var4, int var5, int var6, Operation<Void> var7) {
      if (!GuiRenderContext.active()) {
         var7.call(new Object[]{var1, var2, var3, var4, var5, var6});
      }
   }

   @Inject(
      method = {"method_25394"},
      at = {@At("TAIL")}
   )
   private void purxiy$chrome(class_332 var1, int var2, int var3, float var4, CallbackInfo var5) {
      if (GuiRenderContext.active()) {
         class_8519 var6 = this.field_2586;
         MenuRenderPass.render((class_442)(Object)(Object)this, var6 == null ? null : ((HomeSplashAccessor)var6).purxiy$text());
      }
   }

   @ModifyVariable(
      method = {"method_25394"},
      at = @At("HEAD"),
      argsOnly = true,
      ordinal = 0
   )
   private int purxiy$maskX(int var1) {
      return GuiRenderContext.skins(this) ? MenuRenderPass.maskPointer(var1) : var1;
   }

   @ModifyVariable(
      method = {"method_25394"},
      at = @At("HEAD"),
      argsOnly = true,
      ordinal = 1
   )
   private int purxiy$maskY(int var1) {
      return GuiRenderContext.skins(this) ? MenuRenderPass.maskPointer(var1) : var1;
   }

   @Inject(
      method = {"method_25402"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$plant(class_11909 var1, boolean var2, CallbackInfoReturnable<Boolean> var3) {
      if (!var3.getReturnValueZ() && var1.method_74245() == 0 && GuiRenderContext.skins(this) && BackgroundStateProvider.plant(var1.comp_4798(), var1.comp_4799())) {
         var3.setReturnValue(true);
      }
   }

   @Inject(
      method = {"method_25402"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$click(class_11909 var1, boolean var2, CallbackInfoReturnable<Boolean> var3) {
      if (GuiRenderContext.skins(this) && MenuRenderPass.mouseClicked(var1.comp_4798(), var1.comp_4799(), var1.method_74245())) {
         var3.setReturnValue(true);
      }
   }
}
