package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.GuiRenderContext;
import net.minecraft.class_332;
import net.minecraft.class_350;
import net.minecraft.class_364;
import net.minecraft.class_8021;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Coerce;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_350.class})
public abstract class HomeListMixin {
   @Shadow
   protected abstract boolean method_73379();

   @Inject(
      method = {"method_44397"},
      at = {@At("HEAD")}
   )
   private void purxiy$card(class_332 var1, int var2, int var3, float var4, @Coerce class_8021 var5, CallbackInfo var6) {
      if (GuiRenderContext.active()) {
         class_350 var7 = (class_350)(Object)(Object)this;
         Object var8 = var7.method_25334();
         boolean var9 = this.method_73379() && var8 == var5;
         boolean var10 = var7.method_25405(var2, var3) && ((class_364)var5).method_25405(var2, var3);
         GuiRenderContext.listRow(var7, var5, var9, var10);
      }
   }

   @Inject(
      method = {"method_44398"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$selection(class_332 var1, @Coerce class_8021 var2, int var3, CallbackInfo var4) {
      if (GuiRenderContext.active() && GuiRenderContext.hidesSelection((class_350<?>)(class_350<?>)(Object)this)) {
         var4.cancel();
      }
   }
}
