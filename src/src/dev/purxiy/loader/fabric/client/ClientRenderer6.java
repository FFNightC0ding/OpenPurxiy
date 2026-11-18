// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import net.minecraft.class_10055;
import net.minecraft.class_11659;
import net.minecraft.class_3883;
import net.minecraft.class_3887;
import net.minecraft.class_4587;
import net.minecraft.class_591;

public final class ClientRenderer6 extends class_3887<class_10055, class_591> {
   private static final ClientSupport3.TypeX<ClientRenderer6.Listener> layers = ClientSupport3.TypeX.create();

   public static AutoCloseable install(int var0, ClientRenderer6.Listener var1) {
      return layers.subscribe(var0, var1);
   }

   public ClientRenderer6(class_3883<class_10055, class_591> var1) {
      super(var1);
   }

   public void method_4199(class_4587 var1, class_11659 var2, int var3, class_10055 var4, float var5, float var6) {
      this.submit(var1, var2, var3, var4, var5, var6);
   }

   public void submit(class_4587 var1, class_11659 var2, int var3, class_10055 var4, float var5, float var6) {
      layers.invoke(var7 -> {
         var7.draw(this.method_17165(), var1, var2, var3, var4, var5, var6);
         return null;
      }, null);
   }

   @FunctionalInterface
   public interface Listener {
      void draw(class_591 var1, class_4587 var2, class_11659 var3, int var4, class_10055 var5, float var6, float var7);
   }
}
