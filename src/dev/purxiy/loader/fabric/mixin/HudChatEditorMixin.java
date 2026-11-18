// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.hud.HudLayoutEngine;
import dev.purxiy.hud.HudSlot;
import net.minecraft.class_11908;
import net.minecraft.class_11909;
import net.minecraft.class_332;
import net.minecraft.class_408;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_408.class})
public abstract class HudChatEditorMixin {
   @Inject(
      method = {"method_25394"},
      at = {@At("TAIL")}
   )
   private void overlay(class_332 var1, int var2, int var3, float var4, CallbackInfo var5) {
      HudLayoutEngine.renderOverlay(var1, (class_408)(Object)(Object)this);
   }

   @Inject(
      method = {"method_25404"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void key(class_11908 var1, CallbackInfoReturnable<Boolean> var2) {
      if (HudLayoutEngine.keyPressed(var1.comp_4795())) {
         var2.setReturnValue(true);
      }
   }

   @Inject(
      method = {"method_25402"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void press(class_11909 var1, boolean var2, CallbackInfoReturnable<Boolean> var3) {
      if (HudSlot.press(var1.comp_4798(), var1.comp_4799(), var1.method_74245())) {
         var3.setReturnValue(true);
      }
   }

   @Inject(
      method = {"method_25401"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void scroll(double var1, double var3, double var5, double var7, CallbackInfoReturnable<Boolean> var9) {
      if (HudSlot.scroll(var1, var3, var7)) {
         var9.setReturnValue(true);
      }
   }

   @Inject(
      method = {"method_25432"},
      at = {@At("HEAD")}
   )
   private void close(CallbackInfo var1) {
      HudSlot.release();
   }
}
