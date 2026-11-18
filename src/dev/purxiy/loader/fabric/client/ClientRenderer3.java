// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import java.util.Optional;
import net.minecraft.class_10017;
import net.minecraft.class_11659;
import net.minecraft.class_1297;
import net.minecraft.class_2394;
import net.minecraft.class_243;
import net.minecraft.class_4184;
import org.joml.Matrix4fc;

public final class ClientRenderer3 {
   private static final ClientSupport3.TypeX<ClientRenderer3.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(ClientRenderer3.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void onAttack(class_1297 var0) {
      listeners.invoke(var1 -> {
         var1.onAttack(var0);
         return null;
      }, null);
   }

   public static void onDamage(class_1297 var0, int var1, int var2, Optional<class_243> var3) {
      listeners.invoke(var4 -> {
         var4.onDamage(var0, var1, var2, var3);
         return null;
      }, null);
   }

   public static void onAnimate(class_1297 var0, int var1) {
      listeners.invoke(var2 -> {
         var2.onAnimate(var0, var1);
         return null;
      }, null);
   }

   public static void onEntityEvent(class_1297 var0, byte var1) {
      listeners.invoke(var2 -> {
         var2.onEntityEvent(var0, var1);
         return null;
      }, null);
   }

   public static boolean replacesParticles(class_1297 var0, class_2394 var1) {
      return listeners.invoke(var2 -> var2.replacesParticles(var0, var1), false);
   }

   public static void moduleChanged() {
      listeners.invoke(var0 -> {
         var0.moduleChanged();
         return null;
      }, null);
   }

   public static void clear() {
      listeners.invoke(var0 -> {
         var0.clear();
         return null;
      }, null);
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2, GpuBufferSlice var3) {
      listeners.invoke(var4 -> {
         var4.beginFrame(var0, var1, var2, var3);
         return null;
      }, null);
   }

   public static void extract(class_1297 var0, class_10017 var1) {
      listeners.invoke(var2 -> {
         var2.extract(var0, var1);
         return null;
      }, null);
   }

   public static class_11659 collector(class_10017 var0, class_11659 var1) {
      return listeners.invoke(var2 -> var2.collector(var0, var1), var1);
   }

   public static void render() {
      listeners.invoke(var0 -> {
         var0.render();
         return null;
      }, null);
   }

   public interface Listener {
      void onAttack(class_1297 var1);

      void onDamage(class_1297 var1, int var2, int var3, Optional<class_243> var4);

      void onAnimate(class_1297 var1, int var2);

      void onEntityEvent(class_1297 var1, byte var2);

      boolean replacesParticles(class_1297 var1, class_2394 var2);

      void moduleChanged();

      void clear();

      void beginFrame(class_4184 var1, Matrix4fc var2, Matrix4fc var3, GpuBufferSlice var4);

      void extract(class_1297 var1, class_10017 var2);

      class_11659 collector(class_10017 var1, class_11659 var2);

      void render();
   }
}
