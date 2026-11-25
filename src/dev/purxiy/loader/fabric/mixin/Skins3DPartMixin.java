package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.EquipmentRenderPass;
import dev.purxiy.ui.WBuilder;
import java.util.List;
import net.minecraft.class_4588;
import net.minecraft.class_630;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_630.class_628;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_630.class})
public abstract class Skins3DPartMixin {
   @Shadow
   @Final
   private List<class_628> field_3663;

   @Inject(
      method = {"method_22702"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$compile(class_4665 var1, class_4588 var2, int var3, int var4, int var5, CallbackInfo var6) {
      if (EquipmentRenderPass.paint((class_630)(Object)(Object)this, var1, var2, var3, var4, var5)) {
         var6.cancel();
      } else if (WBuilder.active()) {
         WBuilder.compile(this.field_3663, var1, var2, var3, var4, var5);
         var6.cancel();
      }
   }
}
