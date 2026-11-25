package dev.purxiy.loader.fabric.client;

import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_3414;
import net.minecraft.class_6880;

public final class AttackAnimationProvider {
   private static final SkyPipelineRouter.PipelineList<AttackAnimationProvider.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(AttackAnimationProvider.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean replaces(class_1799 var0, class_1268 var1) {
      return listeners.invoke(var2 -> var2.replaces(var0, var1), false);
   }

   public static boolean inspect() {
      return listeners.invoke(var0 -> var0.inspect(), false);
   }

   public static boolean slash() {
      return listeners.invoke(var0 -> var0.slash(), false);
   }

   public static void onSwing() {
      listeners.invoke(var0 -> {
         var0.onSwing();
         return null;
      }, null);
   }

   public static void attackStarts() {
      listeners.invoke(var0 -> {
         var0.attackStarts();
         return null;
      }, null);
   }

   public static void attackSwings() {
      listeners.invoke(var0 -> {
         var0.attackSwings();
         return null;
      }, null);
   }

   public static void onAttack(class_1657 var0, class_1297 var1) {
      listeners.invoke(var2 -> {
         var2.onAttack(var0, var1);
         return null;
      }, null);
   }

   public static boolean mutes(class_6880<class_3414> var0, double var1, double var3, double var5) {
      return listeners.invoke(var7 -> var7.mutes(var0, var1, var3, var5), false);
   }

   public static boolean inspecting() {
      return listeners.invoke(var0 -> var0.inspecting(), false);
   }

   public interface Listener {
      boolean replaces(class_1799 var1, class_1268 var2);

      boolean inspect();

      boolean slash();

      void onSwing();

      void attackStarts();

      void attackSwings();

      void onAttack(class_1657 var1, class_1297 var2);

      boolean mutes(class_6880<class_3414> var1, double var2, double var4, double var6);

      boolean inspecting();
   }
}
