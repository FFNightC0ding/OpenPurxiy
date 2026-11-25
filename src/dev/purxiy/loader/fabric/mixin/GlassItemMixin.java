package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ItemRenderPipeline;
import net.minecraft.class_10444;
import net.minecraft.class_11659;
import net.minecraft.class_4587;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_10444.class})
public abstract class GlassItemMixin {
   @Inject(
      method = {"method_65604"},
      at = {@At("HEAD")}
   )
   private void purxiy$glassItemStart(class_4587 var1, class_11659 var2, int var3, int var4, int var5, CallbackInfo var6) {
      ItemRenderPipeline.enterItem();
   }

   @Inject(
      method = {"method_65604"},
      at = {@At("RETURN")}
   )
   private void purxiy$glassItemEnd(class_4587 var1, class_11659 var2, int var3, int var4, int var5, CallbackInfo var6) {
      ItemRenderPipeline.exitItem();
   }
}
