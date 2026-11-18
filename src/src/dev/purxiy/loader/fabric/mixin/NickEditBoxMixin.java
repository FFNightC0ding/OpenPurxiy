// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.purxiy.loader.fabric.client.ClientRegistry5;
import net.minecraft.class_327;
import net.minecraft.class_332;
import net.minecraft.class_342;
import net.minecraft.class_5481;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_342.class})
public abstract class NickEditBoxMixin {
   @Shadow
   private String field_2092;
   @Shadow
   private int field_2103;
   @Shadow
   
   private String field_2106;
   @Unique
   
   private ClientRegistry5.TypeX purxiy$mask;
   @Unique
   
   private String purxiy$maskValue;
   @Unique
   
   private String purxiy$maskSuggestion;
   @Unique
   private int purxiy$maskGeneration = -1;

   @Unique
   
   private ClientRegistry5.TypeX purxiy$mask() {
      if (!ClientRegistry5.active()) {
         return null;
      } else {
         int var1 = ClientRegistry5.generation();
         if (this.field_2092 != this.purxiy$maskValue || this.field_2106 != this.purxiy$maskSuggestion || var1 != this.purxiy$maskGeneration) {
            this.purxiy$maskValue = this.field_2092;
            this.purxiy$maskSuggestion = this.field_2106;
            this.purxiy$maskGeneration = var1;
            this.purxiy$mask = ClientRegistry5.TypeX.of(this.field_2092, this.field_2106);
         }

         return this.purxiy$mask;
      }
   }

   @WrapOperation(
      method = {"method_48579"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_342;method_73211(Ljava/lang/String;I)Lnet/minecraft/class_5481;"
      )}
   )
   private class_5481 purxiy$maskText(class_342 var1, String var2, int var3, Operation<class_5481> var4) {
      class_5481 var5 = (class_5481)var4.call(new Object[]{var1, var2, var3});
      ClientRegistry5.TypeX var6 = this.purxiy$mask();
      return var6 == null ? var5 : var6.segment(var5, var3);
   }

   @WrapOperation(
      method = {"method_48579"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_327;method_1727(Ljava/lang/String;)I"
      )}
   )
   private int purxiy$maskSelection(class_327 var1, String var2, Operation<Integer> var3) {
      ClientRegistry5.TypeX var4 = this.purxiy$mask();
      return var4 == null ? (Integer)var3.call(new Object[]{var1, var2}) : var1.method_30880(var4.segment(var2, this.field_2103));
   }

   @WrapOperation(
      method = {"method_48579"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_332;method_51433(Lnet/minecraft/class_327;Ljava/lang/String;IIIZ)V"
      )}
   )
   private void purxiy$maskSuggestion(class_332 var1, class_327 var2,  String var3, int var4, int var5, int var6, boolean var7, Operation<Void> var8) {
      ClientRegistry5.TypeX var9 = var3 != null && var3 == this.field_2106 ? this.purxiy$mask() : null;
      if (var9 != null) {
         var1.method_51430(var2, var9.segment(var3, this.field_2092.length()), var4, var5, var6, var7);
      } else {
         var8.call(new Object[]{var1, var2, var3, var4, var5, var6, var7});
      }
   }
}
