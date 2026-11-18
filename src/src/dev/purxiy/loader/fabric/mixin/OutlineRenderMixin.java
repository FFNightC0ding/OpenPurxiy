// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import net.minecraft.class_11658;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_761;
import net.minecraft.class_9779;
import net.minecraft.class_9922;
import net.minecraft.class_4597.class_4598;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_761.class})
public abstract class OutlineRenderMixin {
   @Inject(
      method = {"method_22710"},
      at = {@At("HEAD")}
   )
   private void purxiy$outlineFrame(
      class_9922 var1,
      class_9779 var2,
      boolean var3,
      class_4184 var4,
      Matrix4f var5,
      Matrix4f var6,
      Matrix4f var7,
      GpuBufferSlice var8,
      Vector4f var9,
      boolean var10,
      CallbackInfo var11
   ) {
   }

   @Inject(
      method = {"method_74923"},
      at = {@At("RETURN")}
   )
   private void purxiy$extractOutline(class_4184 var1, class_11658 var2, CallbackInfo var3) {
      ClientSupport3.extractOutline(var2.field_63083);
   }

   @Inject(
      method = {"method_62210"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$outline(class_4598 var1, class_4587 var2, boolean var3, class_11658 var4, CallbackInfo var5) {
      if (ClientSupport3.renderOutline(var3, var4.field_63082.field_63078)) {
         var5.cancel();
      }
   }
}
