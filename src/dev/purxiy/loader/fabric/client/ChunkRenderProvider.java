package dev.purxiy.loader.fabric.client;

import net.minecraft.class_11658;
import net.minecraft.class_11661;
import net.minecraft.class_1269;
import net.minecraft.class_1750;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_2804;
import net.minecraft.class_4587;
import net.minecraft.class_7522;

public final class ChunkRenderProvider {
   private static final SkyPipelineRouter.PipelineList<ChunkRenderProvider.Listener> listeners = SkyPipelineRouter.PipelineList.create();

   public static AutoCloseable install(ChunkRenderProvider.Listener var0) {
      if (listeners.subscribers() != 0) {
         throw new IllegalStateException("Visual contract already owned");
      } else {
         return listeners.subscribe(0, var0);
      }
   }

   public static void placeStarted(class_1750 var0) {
      listeners.invoke(var1 -> {
         var1.placeStarted(var0);
         return null;
      }, null);
   }

   public static void placeEnded(class_1750 var0, class_1269 var1) {
      listeners.invoke(var2 -> {
         var2.placeEnded(var0, var1);
         return null;
      }, null);
   }

   public static void blockChanged(class_2338 var0, class_2680 var1, class_2680 var2) {
      listeners.invoke(var3 -> {
         var3.blockChanged(var0, var1, var2);
         return null;
      }, null);
   }

   public static void destroyStarted(class_2338 var0) {
      listeners.invoke(var1 -> {
         var1.destroyStarted(var0);
         return null;
      }, null);
   }

   public static void destroyEnded(boolean var0) {
      listeners.invoke(var1 -> {
         var1.destroyEnded(var0);
         return null;
      }, null);
   }

   public static void serverUpdateStarted(class_2338 var0) {
      listeners.invoke(var1 -> {
         var1.serverUpdateStarted(var0);
         return null;
      }, null);
   }

   public static void serverUpdateEnded(class_2338 var0) {
      listeners.invoke(var1 -> {
         var1.serverUpdateEnded(var0);
         return null;
      }, null);
   }

   public static void hideIn(class_7522<class_2680> var0, int var1, int var2, int var3) {
      listeners.invokeReadOnly(var4 -> {
         var4.hideIn(var0, var1, var2, var3);
         return null;
      }, null);
   }

   public static SectionLightSnapshot lightIn(int var0, int var1, int var2) {
      return listeners.invokeReadOnly(var3 -> var3.lightIn(var0, var1, var2), null);
   }

   public static void lightIn(class_2804[] var0, int var1, int var2, int var3) {
      listeners.invokeReadOnly(var4 -> {
         var4.lightIn(var0, var1, var2, var3);
         return null;
      }, null);
   }

   public static void sectionCopied(int var0, int var1, int var2) {
      listeners.invoke(var3 -> {
         var3.sectionCopied(var0, var1, var2);
         return null;
      }, null);
   }

   public static void sectionMeshed(int var0, int var1, int var2) {
      listeners.invoke(var3 -> {
         var3.sectionMeshed(var0, var1, var2);
         return null;
      }, null);
   }

   public static void beginFrame() {
      listeners.invoke(var0 -> {
         var0.beginFrame();
         return null;
      }, null);
   }

   public static void submit(class_4587 var0, class_11658 var1, class_11661 var2) {
      listeners.invoke(var3 -> {
         var3.submit(var0, var1, var2);
         return null;
      }, null);
   }

   public static boolean follow(class_2338 var0, double[] var1) {
      return listeners.invoke(var2 -> var2.follow(var0, var1), false);
   }

   public static boolean follow(class_2338 var0, class_4587 var1, double var2, double var4, double var6) {
      return listeners.invoke(var8 -> var8.follow(var0, var1, var2, var4, var6), false);
   }

   public interface Listener {
      void placeStarted(class_1750 var1);

      void placeEnded(class_1750 var1, class_1269 var2);

      void blockChanged(class_2338 var1, class_2680 var2, class_2680 var3);

      void destroyStarted(class_2338 var1);

      void destroyEnded(boolean var1);

      void serverUpdateStarted(class_2338 var1);

      void serverUpdateEnded(class_2338 var1);

      void hideIn(class_7522<class_2680> var1, int var2, int var3, int var4);

      SectionLightSnapshot lightIn(int var1, int var2, int var3);

      void lightIn(class_2804[] var1, int var2, int var3, int var4);

      void sectionCopied(int var1, int var2, int var3);

      void sectionMeshed(int var1, int var2, int var3);

      void beginFrame();

      void submit(class_4587 var1, class_11658 var2, class_11661 var3);

      boolean follow(class_2338 var1, double[] var2);

      boolean follow(class_2338 var1, class_4587 var2, double var3, double var5, double var7);
   }
}
