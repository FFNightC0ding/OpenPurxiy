// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import java.util.List;
import net.minecraft.class_10017;
import net.minecraft.class_11658;
import net.minecraft.class_11659;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_4587;
import net.minecraft.class_898;
import net.minecraft.class_9909;
import net.minecraft.class_9960;
import net.minecraft.class_10017.class_11680;

public final class RenderHooks {
   private static final ClientSupport3.TypeX<RenderHooks.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(RenderHooks.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void beginFrame() {
      listeners.invoke(var0 -> {
         var0.beginFrame();
         return null;
      }, null);
   }

   public static void addPass(class_9909 var0, class_9960 var1, GpuBufferSlice var2) {
      listeners.invoke(var3 -> {
         var3.addPass(var0, var1, var2);
         return null;
      }, null);
   }

   public static void extract(class_1297 var0, class_10017 var1) {
      listeners.invoke(var2 -> {
         var2.extract(var0, var1);
         return null;
      }, null);
   }

   public static boolean beginSubmit(class_10017 var0, double var1, double var3, double var5) {
      return listeners.invoke(var7 -> var7.beginSubmit(var0, var1, var3, var5), false);
   }

   public static boolean dissolving(class_10017 var0) {
      return listeners.invoke(var1 -> var1.dissolving(var0), false);
   }

   public static class_11659 collector(class_10017 var0, class_11659 var1) {
      return listeners.invoke(var2 -> var2.collector(var0, var1), var1);
   }

   public static List<class_11680> shadow(class_10017 var0, List<class_11680> var1) {
      return listeners.invoke(var2 -> var2.shadow(var0, var1), var1);
   }

   public static void submitGhosts(class_898 var0, class_11658 var1, class_4587 var2, class_11659 var3) {
      listeners.invoke(var4 -> {
         var4.submitGhosts(var0, var1, var2, var3);
         return null;
      }, null);
   }

   public static boolean hidesPoof(class_1309 var0) {
      return listeners.invoke(var1 -> var1.hidesPoof(var0), false);
   }

   public static void onDamage(class_1297 var0, int var1) {
      listeners.invoke(var2 -> {
         var2.onDamage(var0, var1);
         return null;
      }, null);
   }

   public interface Listener {
      void beginFrame();

      void addPass(class_9909 var1, class_9960 var2, GpuBufferSlice var3);

      void extract(class_1297 var1, class_10017 var2);

      boolean beginSubmit(class_10017 var1, double var2, double var4, double var6);

      boolean dissolving(class_10017 var1);

      class_11659 collector(class_10017 var1, class_11659 var2);

      List<class_11680> shadow(class_10017 var1, List<class_11680> var2);

      void submitGhosts(class_898 var1, class_11658 var2, class_4587 var3, class_11659 var4);

      boolean hidesPoof(class_1309 var1);

      void onDamage(class_1297 var1, int var2);
   }
}
