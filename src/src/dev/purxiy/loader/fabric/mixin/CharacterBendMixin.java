// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.duck.ScreenHook;
import dev.purxiy.ui.WBuilder;
import java.util.List;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_630;
import net.minecraft.class_630.class_628;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_630.class})
public abstract class CharacterBendMixin implements ScreenHook {
   @Shadow
   @Final
   private List<class_628> field_3663;
   @Unique
   private float purxiy$bend;
   @Unique
   private float purxiy$bendDirection;

   @Override
   public float purxiy$bend() {
      return this.purxiy$bend;
   }

   @Override
   public float purxiy$bendDirection() {
      return this.purxiy$bendDirection;
   }

   @Override
   public void purxiy$bend(float var1, float var2) {
      this.purxiy$bend = var1;
      this.purxiy$bendDirection = var2;
   }

   @Inject(
      method = {"method_41923"},
      at = {@At("TAIL")}
   )
   private void purxiy$straighten(CallbackInfo var1) {
      this.purxiy$bend = 0.0F;
   }

   @Inject(
      method = {"method_22699(Lnet/minecraft/class_4587;Lnet/minecraft/class_4588;III)V"},
      at = {@At("HEAD")}
   )
   private void purxiy$bendBegin(class_4587 var1, class_4588 var2, int var3, int var4, int var5, CallbackInfo var6) {
      if (this.purxiy$bend != 0.0F) {
         WBuilder.begin(this, this.field_3663, this.purxiy$bend, this.purxiy$bendDirection);
      }
   }

   @Inject(
      method = {"method_22699(Lnet/minecraft/class_4587;Lnet/minecraft/class_4588;III)V"},
      at = {@At("RETURN")}
   )
   private void purxiy$bendEnd(class_4587 var1, class_4588 var2, int var3, int var4, int var5, CallbackInfo var6) {
      if (this.purxiy$bend != 0.0F) {
         WBuilder.end(this);
      }
   }
}
