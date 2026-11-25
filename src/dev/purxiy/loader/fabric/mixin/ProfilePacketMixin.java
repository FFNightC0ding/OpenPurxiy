package dev.purxiy.loader.fabric.mixin;

import dev.purxiy.loader.fabric.client.WorldEventDispatcher;
import net.minecraft.class_2663;
import net.minecraft.class_310;
import net.minecraft.class_5892;
import net.minecraft.class_634;
import net.minecraft.class_638;
import net.minecraft.class_8143;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({class_634.class})
public abstract class ProfilePacketMixin {
   private static final String ON_MAIN_THREAD = "Lnet/minecraft/network/protocol/PacketUtils;ensureRunningOnSameThread(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketListener;Lnet/minecraft/network/PacketProcessor;)V";

   @Inject(
      method = {"method_49034"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2600;method_11074(Lnet/minecraft/class_2596;Lnet/minecraft/class_2547;Lnet/minecraft/class_11980;)V",
         shift = Shift.AFTER
      )}
   )
   private void purxiy$profileDamage(class_8143 var1, CallbackInfo var2) {
      class_638 var3 = class_310.method_1551().field_1687;
      if (var3 != null) {
         WorldEventDispatcher.onDamage(var3.method_8469(var1.comp_1267()), var1.comp_1269());
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
   private void purxiy$profileEntityEvent(class_2663 var1, CallbackInfo var2) {
      class_638 var3 = class_310.method_1551().field_1687;
      if (var3 != null) {
         WorldEventDispatcher.onEntityEvent(var1.method_11469(var3), var1.method_11470());
      }
   }

   @Inject(
      method = {"method_34075"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_2600;method_11074(Lnet/minecraft/class_2596;Lnet/minecraft/class_2547;Lnet/minecraft/class_11980;)V",
         shift = Shift.AFTER
      )}
   )
   private void purxiy$profileDeath(class_5892 var1, CallbackInfo var2) {
      class_310 var3 = class_310.method_1551();
      if (var3.field_1724 != null && var1.comp_2275() == var3.field_1724.method_5628()) {
         WorldEventDispatcher.onDeath();
      }
   }

   @Inject(
      method = {"method_45729"},
      at = {@At("HEAD")}
   )
   private void purxiy$profileChat(String var1, CallbackInfo var2) {
      WorldEventDispatcher.onChat();
   }
}
