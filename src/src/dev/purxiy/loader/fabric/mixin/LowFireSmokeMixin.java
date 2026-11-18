// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import dev.purxiy.loader.fabric.client.ClientRegistry12;
import net.minecraft.class_1937;
import net.minecraft.class_2394;
import net.minecraft.class_4770;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin({class_4770.class})
public abstract class LowFireSmokeMixin {
   private static final String ADD_PARTICLE = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V";

   @WrapWithCondition(
      method = {"method_9496"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/class_1937;method_8406(Lnet/minecraft/class_2394;DDDDDD)V"
      )}
   )
   private boolean purxiy$noSmoke(class_1937 var1, class_2394 var2, double var3, double var5, double var7, double var9, double var11, double var13) {
      return !ClientRegistry12.active();
   }
}
