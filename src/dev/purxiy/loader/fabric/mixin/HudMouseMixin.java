package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.hud.HudElement;
import dev.purxiy.hud.HudSlot;
import net.minecraft.class_1041;
import net.minecraft.class_11910;
import net.minecraft.class_310;
import net.minecraft.class_312;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_312.class})
public abstract class HudMouseMixin {
   @Inject(
      method = {"method_1601"},
      at = {@At("HEAD")}
   )
   private void count(long var1, class_11910 var3, int var4, CallbackInfo var5) {
      class_310 var6 = class_310.method_1551();
      if (var1 == var6.method_22683().method_4490() && var3.comp_4801() == 0 && var4 == 0) {
         HudSlot.release();
      }

      if (var4 == 1 && var1 == var6.method_22683().method_4490() && var6.field_1755 == null) {
         HudElement.onClick(var3.comp_4801());
      }
   }

   @Inject(
      method = {"method_1600"},
      at = {@At("TAIL")}
   )
   private void drag(long var1, double var3, double var5, CallbackInfo var7) {
      class_310 var8 = class_310.method_1551();
      class_1041 var9 = var8.method_22683();
      if (var1 == var9.method_4490()) {
         HudSlot.drag(var3 * var9.method_4486() / var9.method_4480(), var5 * var9.method_4502() / var9.method_4507());
      }
   }
}
