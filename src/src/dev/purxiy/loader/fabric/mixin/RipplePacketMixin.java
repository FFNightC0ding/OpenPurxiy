// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.ClientRenderer3;
import net.minecraft.class_2616;
import net.minecraft.class_2663;
import net.minecraft.class_310;
import net.minecraft.class_634;
import net.minecraft.class_638;
import net.minecraft.class_8143;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_634.class})
public abstract class RipplePacketMixin {
   private static final String ON_MAIN_THREAD = "Lnet/minecraft/network/protocol/PacketUtils;ensureRunningOnSameThread(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketListener;Lnet/minecraft/network/PacketProcessor;)V";

   @Inject(
      method = {"method_49034"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2600;method_11074(Lnet/minecraft/class_2596;Lnet/minecraft/class_2547;Lnet/minecraft/class_11980;)V",
         shift = Shift.AFTER
      )}
   )
   private void purxiy$rippleDamage(class_8143 var1, CallbackInfo var2) {
      class_638 var3 = class_310.method_1551().field_1687;
      if (var3 != null) {
         ClientRenderer3.onDamage(var3.method_8469(var1.comp_1267()), var1.comp_1269(), var1.comp_1270(), var1.comp_1271());
      }
   }

   @Inject(
      method = {"method_11160"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2600;method_11074(Lnet/minecraft/class_2596;Lnet/minecraft/class_2547;Lnet/minecraft/class_11980;)V",
         shift = Shift.AFTER
      )}
   )
   private void purxiy$rippleAnimate(class_2616 var1, CallbackInfo var2) {
      class_638 var3 = class_310.method_1551().field_1687;
      if (var3 != null) {
         ClientRenderer3.onAnimate(var3.method_8469(var1.method_11269()), var1.method_11267());
      }
   }

   @Inject(
      method = {"method_11148"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2600;method_11074(Lnet/minecraft/class_2596;Lnet/minecraft/class_2547;Lnet/minecraft/class_11980;)V",
         shift = Shift.AFTER
      )}
   )
   private void purxiy$rippleEntityEvent(class_2663 var1, CallbackInfo var2) {
      class_638 var3 = class_310.method_1551().field_1687;
      if (var3 != null) {
         ClientRenderer3.onEntityEvent(var1.method_11469(var3), var1.method_11470());
      }
   }
}
