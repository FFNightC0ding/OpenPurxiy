package dev.purxiy.loader.fabric.client;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import net.minecraft.class_10017;
import net.minecraft.class_11659;
import net.minecraft.class_1297;
import net.minecraft.class_4184;
import net.minecraft.class_591;
import org.joml.Matrix4fc;

public final class ItemRenderPipeline {
   private static final SkyPipelineRouter.PipelineList<ItemRenderPipeline.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(ItemRenderPipeline.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2, GpuBufferSlice var3) {
      listeners.invoke(var4 -> {
         var4.beginFrame(var0, var1, var2, var3);
         return null;
      }, null);
   }

   public static void playerModel(class_591 var0) {
      listeners.invoke(var1 -> {
         var1.playerModel(var0);
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

   public static void enterItem() {
      listeners.invoke(var0 -> {
         var0.enterItem();
         return null;
      }, null);
   }

   public static void exitItem() {
      listeners.invoke(var0 -> {
         var0.exitItem();
         return null;
      }, null);
   }

   public static void hudProjection(int var0, int var1, float var2) {
      listeners.invoke(var3 -> {
         var3.hudProjection(var0, var1, var2);
         return null;
      }, null);
   }

   public static void beginHands(int var0) {
      listeners.invoke(var1 -> {
         var1.beginHands(var0);
         return null;
      }, null);
   }

   public static class_11659 handCollector(class_11659 var0) {
      return listeners.invoke(var1 -> var1.handCollector(var0), var0);
   }

   public static void renderWorld() {
      listeners.invoke(var0 -> {
         var0.renderWorld();
         return null;
      }, null);
   }

   public static void renderHand() {
      listeners.invoke(var0 -> {
         var0.renderHand();
         return null;
      }, null);
   }

   public interface Listener {
      void beginFrame(class_4184 var1, Matrix4fc var2, Matrix4fc var3, GpuBufferSlice var4);

      void playerModel(class_591 var1);

      void extract(class_1297 var1, class_10017 var2);

      class_11659 collector(class_10017 var1, class_11659 var2);

      void enterItem();

      void exitItem();

      void hudProjection(int var1, int var2, float var3);

      void beginHands(int var1);

      class_11659 handCollector(class_11659 var1);

      void renderWorld();

      void renderHand();
   }
}
