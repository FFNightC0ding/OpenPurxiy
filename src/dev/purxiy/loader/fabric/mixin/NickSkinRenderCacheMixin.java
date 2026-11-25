package dev.purxiy.loader.fabric.mixin;

import com.google.common.cache.LoadingCache;
import dev.purxiy.loader.fabric.client.TabListProvider;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import net.minecraft.class_11786;
import net.minecraft.class_9296;
import net.minecraft.class_11786.class_11787;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({class_11786.class})
public abstract class NickSkinRenderCacheMixin {
   @Shadow
   @Final
   private LoadingCache<class_9296, class_11787> field_62217;

   @Inject(
      method = {"method_74320"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void purxiy$hideHead(class_9296 var1, CallbackInfoReturnable<CompletableFuture<Optional<class_11787>>> var2) {
      if (TabListProvider.hideSkin(var1)) {
         var2.setReturnValue(CompletableFuture.completedFuture(Optional.of((class_11787)this.field_62217.getUnchecked(var1))));
      }
   }

   @Inject(
      method = {"method_74050"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void purxiy$hideGlyph(class_9296 var1, CallbackInfoReturnable<Supplier<class_11787>> var2) {
      Supplier var3 = (Supplier)var2.getReturnValue();
      var2.setReturnValue((Supplier<class_11787>) () -> TabListProvider.hideSkin(var1) ? (class_11787)this.field_62217.getUnchecked(var1) : (class_11787)var3.get());
   }
}
