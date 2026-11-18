// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric.client;

import net.minecraft.class_1268;
import net.minecraft.class_1799;
import net.minecraft.class_4587;
import net.minecraft.class_746;
import org.joml.Matrix4f;

public final class ClientRegistry17 {
   private static final ClientSupport3.TypeX<ClientRegistry17.Listener> listeners = ClientSupport3.TypeX.create();

   public static AutoCloseable install(ClientRegistry17.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static boolean active() {
      return listeners.invoke(var0 -> var0.active(), false);
   }

   public static boolean swingsOwned() {
      return listeners.invoke(var0 -> var0.swingsOwned(), false);
   }

   public static double clock() {
      return listeners.invoke(var0 -> var0.clock(), 0.0);
   }

   public static void frame() {
      listeners.invoke(var0 -> {
         var0.frame();
         return null;
      }, null);
   }

   public static void onSwing(class_1268 var0, int var1) {
      listeners.invoke(var2 -> {
         var2.onSwing(var0, var1);
         return null;
      }, null);
   }

   public static boolean swingingWithin(class_1268 var0, float var1) {
      return listeners.invoke(var2 -> var2.swingingWithin(var0, var1), false);
   }

   public static class_1268 lastSwung() {
      return listeners.invoke(var0 -> var0.lastSwung(), null);
   }

   public static float swingSeconds(class_1268 var0) {
      return listeners.invoke(var1 -> var1.swingSeconds(var0), 0.0F);
   }

   public static void beginHands(class_4587 var0, float var1, class_746 var2) {
      listeners.invoke(var3 -> {
         var3.beginHands(var0, var1, var2);
         return null;
      }, null);
   }

   public static float swayPitch(float var0) {
      return listeners.invoke(var1 -> var1.swayPitch(var0), var0);
   }

   public static float swayYaw(float var0) {
      return listeners.invoke(var1 -> var1.swayYaw(var0), var0);
   }

   public static void place(class_4587 var0, class_1268 var1, int var2) {
      listeners.invoke(var3 -> {
         var3.place(var0, var1, var2);
         return null;
      }, null);
   }

   public static Matrix4f placement(class_1268 var0, int var1, float var2, Matrix4f var3) {
      return listeners.invoke(var4 -> var4.placement(var0, var1, var2, var3), var3.identity());
   }

   public static float equip(class_1268 var0, float var1) {
      return listeners.invoke(var2 -> var2.equip(var0, var1), var1);
   }

   public static boolean instantSwitch() {
      return listeners.invoke(var0 -> var0.instantSwitch(), false);
   }

   public static boolean cooldownDip() {
      return listeners.invoke(var0 -> var0.cooldownDip(), true);
   }

   public static boolean emptyHandShown() {
      return listeners.invoke(var0 -> var0.emptyHandShown(), true);
   }

   public static boolean swingItem(class_4587 var0, class_1268 var1, int var2) {
      return listeners.invoke(var3 -> var3.swingItem(var0, var1, var2), false);
   }

   public static void armPose(class_4587 var0, class_1268 var1, int var2, float var3) {
      listeners.invoke(var4 -> {
         var4.armPose(var0, var1, var2, var3);
         return null;
      }, null);
   }

   public static class_1799 preview(class_1268 var0) {
      return listeners.invoke(var1 -> var1.preview(var0), null);
   }

   public static float studioLowered(class_1799 var0) {
      return listeners.invoke(var1 -> var1.studioLowered(var0), 0.0F);
   }

   public static void traceLowered(float var0) {
      listeners.invoke(var1 -> {
         var1.traceLowered(var0);
         return null;
      }, null);
   }

   public interface Listener {
      boolean active();

      boolean swingsOwned();

      double clock();

      void frame();

      void onSwing(class_1268 var1, int var2);

      boolean swingingWithin(class_1268 var1, float var2);

      class_1268 lastSwung();

      float swingSeconds(class_1268 var1);

      void beginHands(class_4587 var1, float var2, class_746 var3);

      float swayPitch(float var1);

      float swayYaw(float var1);

      void place(class_4587 var1, class_1268 var2, int var3);

      Matrix4f placement(class_1268 var1, int var2, float var3, Matrix4f var4);

      float equip(class_1268 var1, float var2);

      boolean instantSwitch();

      boolean cooldownDip();

      boolean emptyHandShown();

      boolean swingItem(class_4587 var1, class_1268 var2, int var3);

      void armPose(class_4587 var1, class_1268 var2, int var3, float var4);

      class_1799 preview(class_1268 var1);

      float studioLowered(class_1799 var1);

      void traceLowered(float var1);
   }
}
