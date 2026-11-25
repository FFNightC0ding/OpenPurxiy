package dev.purxiy.loader.fabric.client;

import net.minecraft.class_10017;
import net.minecraft.class_11659;
import net.minecraft.class_1297;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_4588;
import org.joml.Matrix4f;

public final class EntityMaskPipeline {
   private static final SkyPipelineRouter.PipelineList<EntityMaskPipeline.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(EntityMaskPipeline.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void beginFrame(Matrix4f var0, Matrix4f var1) {
      listeners.invoke(var2 -> {
         var2.beginFrame(var0, var1);
         return null;
      }, null);
   }

   public static void endLevel() {
      listeners.invoke(var0 -> {
         var0.endLevel();
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

   public static void onDamage(class_1297 var0, int var1, int var2) {
      listeners.invoke(var3 -> {
         var3.onDamage(var0, var1, var2);
         return null;
      }, null);
   }

   public static class_4588 maskBuffer(int var0, class_1921 var1) {
      return listeners.invoke(var2 -> var2.maskBuffer(var0, var1), null);
   }

   public static void drawWorldMask() {
      listeners.invoke(var0 -> {
         var0.drawWorldMask();
         return null;
      }, null);
   }

   public static void hudProjection(int var0, int var1, float var2) {
      listeners.invoke(var3 -> {
         var3.hudProjection(var0, var1, var2);
         return null;
      }, null);
   }

   public static float handLight(float[] var0) {
      return listeners.invoke(var1 -> var1.handLight(var0), 0.0F);
   }

   public static class_11659 handCollector(class_11659 var0) {
      return listeners.invoke(var1 -> var1.handCollector(var0), var0);
   }

   public static void submittingItem(class_1799 var0) {
      listeners.invoke(var1 -> {
         var1.submittingItem(var0);
         return null;
      }, null);
   }

   public static void drawHandMask() {
      listeners.invoke(var0 -> {
         var0.drawHandMask();
         return null;
      }, null);
   }

   public static void composite() {
      listeners.invoke(var0 -> {
         var0.composite();
         return null;
      }, null);
   }

   public interface Listener {
      void beginFrame(Matrix4f var1, Matrix4f var2);

      void endLevel();

      void extract(class_1297 var1, class_10017 var2);

      class_11659 collector(class_10017 var1, class_11659 var2);

      void onDamage(class_1297 var1, int var2, int var3);

      class_4588 maskBuffer(int var1, class_1921 var2);

      void drawWorldMask();

      void hudProjection(int var1, int var2, float var3);

      float handLight(float[] var1);

      class_11659 handCollector(class_11659 var1);

      void submittingItem(class_1799 var1);

      void drawHandMask();

      void composite();
   }
}
