// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_1297;
import net.minecraft.class_2561;
import net.minecraft.class_897;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_897.class})
public interface NameTagNameInvoker {
   @Invoker("method_3921")
   boolean purxiy$shouldShowName(class_1297 var1, double var2);

   @Invoker("method_62426")
   
   class_2561 purxiy$nameTag(class_1297 var1);
}
