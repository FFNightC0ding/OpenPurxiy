package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ChunkRenderProvider;
import net.minecraft.class_12074;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_761;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_761.class})
public abstract class PlaceOutlineMixin {
   @Unique
   private boolean purxiy$placeFollows;

   @Inject(
      method = {"method_22712"},
      at = {@At("HEAD")}
   )
   private void purxiy$placeOutline(
      class_4587 var1, class_4588 var2, double var3, double var5, double var7, class_12074 var9, int var10, float var11, CallbackInfo var12
   ) {
      var1.method_22903();
      this.purxiy$placeFollows = true;
      ChunkRenderProvider.follow(var9.comp_4932(), var1, var3, var5, var7);
   }

   @Inject(
      method = {"method_22712"},
      at = {@At("RETURN")}
   )
   private void purxiy$placeOutlineEnd(
      class_4587 var1, class_4588 var2, double var3, double var5, double var7, class_12074 var9, int var10, float var11, CallbackInfo var12
   ) {
      if (this.purxiy$placeFollows) {
         this.purxiy$placeFollows = false;
         var1.method_22909();
      }
   }
}
