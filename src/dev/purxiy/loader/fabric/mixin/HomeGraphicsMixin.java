// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import dev.purxiy.loader.fabric.client.ClientRegistry21;
import net.minecraft.class_2960;
import net.minecraft.class_332;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_332.class})
public abstract class HomeGraphicsMixin {
   @Inject(
      method = {"method_52707(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIIII)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$sprite(RenderPipeline var1, class_2960 var2, int var3, int var4, int var5, int var6, int var7, CallbackInfo var8) {
      if (ClientRegistry21.active() && ClientRegistry21.sprite(var2, var3, var4, var5, var6, var7)) {
         var8.cancel();
      }
   }

   @Inject(
      method = {"method_25293(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/class_2960;IIFFIIIIIII)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$texture(
      RenderPipeline var1,
      class_2960 var2,
      int var3,
      int var4,
      float var5,
      float var6,
      int var7,
      int var8,
      int var9,
      int var10,
      int var11,
      int var12,
      int var13,
      CallbackInfo var14
   ) {
      if (ClientRegistry21.active() && ClientRegistry21.texture(var2, var3, var4, var7, var8)) {
         var14.cancel();
      }
   }

   @Inject(
      method = {"method_25294(IIIII)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$fill(int var1, int var2, int var3, int var4, int var5, CallbackInfo var6) {
      if (ClientRegistry21.active() && ClientRegistry21.fill((class_332)(Object)(Object)this, var1, var2, var3, var4, var5)) {
         var6.cancel();
      }
   }

   @Inject(
      method = {"method_44379"},
      at = {@At("HEAD")}
   )
   private void purxiy$scissor(int var1, int var2, int var3, int var4, CallbackInfo var5) {
      ClientRegistry21.scissor((class_332)(Object)(Object)this, var1, var2, var3, var4);
   }

   @Inject(
      method = {"method_44380"},
      at = {@At("HEAD")}
   )
   private void purxiy$unscissor(CallbackInfo var1) {
      ClientRegistry21.unscissor();
   }
}
