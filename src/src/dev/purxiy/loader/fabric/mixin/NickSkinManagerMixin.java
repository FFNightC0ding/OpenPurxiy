// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.mixin;

import com.mojang.authlib.GameProfile;
import dev.purxiy.loader.fabric.client.ClientRegistry5;
import java.util.function.Supplier;
import net.minecraft.class_1068;
import net.minecraft.class_1071;
import net.minecraft.class_8685;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_1071.class})
public abstract class NickSkinManagerMixin {
   @Inject(
      method = {"method_73544"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$hideSkin(GameProfile var1, boolean var2, CallbackInfoReturnable<Supplier<class_8685>> var3) {
      Supplier var4 = (Supplier)var3.getReturnValue();
      var3.setReturnValue((Supplier<class_8685>) () -> ClientRegistry5.hideSkin(var1) ? class_1068.method_52854(var1) : (class_8685)var4.get());
   }
}
