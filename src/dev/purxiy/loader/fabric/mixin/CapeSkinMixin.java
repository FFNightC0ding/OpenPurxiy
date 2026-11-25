package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.ModuleState;
import dev.purxiy.loader.fabric.client.ArmorRenderProvider;
import net.minecraft.class_10055;
import net.minecraft.class_1007;
import net.minecraft.class_11890;
import net.minecraft.class_310;
import net.minecraft.class_8685;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1007.class})
public abstract class CapeSkinMixin {
   @Inject(
      method = {"method_62604(Lnet/minecraft/class_11890;Lnet/minecraft/class_10055;F)V"},
      at = {@At("TAIL")}
   )
   private void purxiy$cape(class_11890 var1, class_10055 var2, float var3, CallbackInfo var4) {
      if (var1 == class_310.method_1551().field_1724 && ModuleState.moduleActive("cape")) {
         class_8685 var5 = ArmorRenderProvider.dress(var2.field_53520);
         if (var5 != var2.field_53520) {
            var2.field_53520 = var5;
            var2.field_53532 = true;
            ArmorRenderProvider.chest(var2.field_53418);
         }
      }
   }
}
