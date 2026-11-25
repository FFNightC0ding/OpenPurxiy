package dev.purxiy.loader.fabric.mixin;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.loader.fabric.RenderHooks;
import net.minecraft.class_11658;
import net.minecraft.class_11659;
import net.minecraft.class_4184;
import net.minecraft.class_4587;
import net.minecraft.class_761;
import net.minecraft.class_898;
import net.minecraft.class_9779;
import net.minecraft.class_9909;
import net.minecraft.class_9922;
import net.minecraft.class_9960;
import org.joml.Matrix4f;
import org.joml.Vector4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_761.class})
public abstract class AfterglowLevelMixin {
   @Shadow
   @Final
   private class_898 field_4109;
   @Shadow
   @Final
   private class_9960 field_53081;

   @Inject(
      method = {"method_22710"},
      at = {@At("HEAD")}
   )
   private void purxiy$afterglowFrame(
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
      RenderHooks.beginFrame();
   }

   @Inject(
      method = {"method_72916"},
      at = {@At("TAIL")}
   )
   private void purxiy$afterglowGhosts(class_4587 var1, class_11658 var2, class_11659 var3, CallbackInfo var4) {
      RenderHooks.submitGhosts(this.field_4109, var2, var1, var3);
   }

   @Inject(
      method = {"method_62201"},
      at = {@At("TAIL")}
   )
   private void purxiy$afterglowLight(class_9909 var1, GpuBufferSlice var2, CallbackInfo var3) {
      RenderHooks.addPass(var1, this.field_53081, var2);
   }
}
