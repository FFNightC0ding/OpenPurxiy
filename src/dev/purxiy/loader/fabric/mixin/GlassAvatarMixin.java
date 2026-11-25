package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_591;

import dev.purxiy.loader.fabric.client.ItemRenderPipeline;
import net.minecraft.class_1007;
import net.minecraft.class_5617.class_5618;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_1007.class})
public abstract class GlassAvatarMixin {
   @Inject(
      method = {"<init>"},
      at = {@At("TAIL")}
   )
   private void purxiy$glassBody(class_5618 var1, boolean var2, CallbackInfo var3) {
      ItemRenderPipeline.playerModel(((class_591)(Object)((class_1007)(Object)(Object)this).method_4038()));
   }
}
