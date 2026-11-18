// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import net.minecraft.class_310;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({class_310.class})
public interface PorscheTouchInvoker {
   @Invoker("method_1536")
   boolean purxiy$startAttack();

   @Invoker("method_1590")
   void purxiy$continueAttack(boolean var1);

   @Invoker("method_1583")
   void purxiy$startUseItem();
}
