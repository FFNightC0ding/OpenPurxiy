// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import net.minecraft.class_12137;
import net.minecraft.class_1799;
import net.minecraft.class_8685;

public final class ClientRegistry23 {
   private static final ClientSupport3.TypeX<ClientRegistry23.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(ClientRegistry23.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static class_8685 dress(class_8685 var0) {
      return listeners.invoke(var1 -> var1.dress(var0), var0);
   }

   public static class_8685 wear(class_8685 var0) {
      return listeners.invoke(var1 -> var1.wear(var0), var0);
   }

   public static boolean wears(class_8685 var0) {
      return listeners.invoke(var1 -> var1.wears(var0), false);
   }

   public static void chest(class_1799 var0) {
      listeners.invoke(var1 -> {
         var1.chest(var0);
         return null;
      }, null);
   }

   public static class_12137 sampler() {
      return listeners.invoke(var0 -> var0.sampler(), RenderSystem.getSamplerCache().method_76520(FilterMode.LINEAR, true));
   }

   public interface Listener {
      class_8685 dress(class_8685 var1);

      class_8685 wear(class_8685 var1);

      boolean wears(class_8685 var1);

      void chest(class_1799 var1);

      class_12137 sampler();
   }
}
