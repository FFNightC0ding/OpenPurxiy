package dev.purxiy.loader.fabric.client;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.class_10055;
import net.minecraft.class_10201;
import net.minecraft.class_11890;
import net.minecraft.class_4588;
import net.minecraft.class_630;
import net.minecraft.class_4587.class_4665;

public final class EquipmentRenderPass {
   private static final SkyPipelineRouter.PipelineList<EquipmentRenderPass.Listener> paint = SkyPipelineRouter.PipelineList.create();
   private static final SkyPipelineRouter.PipelineList<BiConsumer<class_11890, class_10055>> extract = SkyPipelineRouter.PipelineList.create();
   private static final SkyPipelineRouter.PipelineList<Consumer<WorldRenderContext>> world = SkyPipelineRouter.PipelineList.create();
   private static class_10201 equipment;

   private EquipmentRenderPass() {
   }

   public static void equipment(class_10201 var0) {
      equipment = var0;
   }

   public static class_10201 equipment() {
      return equipment;
   }

   public static AutoCloseable paint(EquipmentRenderPass.Listener var0) {
      return paint.subscribe(0, var0);
   }

   public static AutoCloseable extract(BiConsumer<class_11890, class_10055> var0) {
      return extract.subscribe(0, var0);
   }

   public static AutoCloseable world(Consumer<WorldRenderContext> var0) {
      return world.subscribe(0, var0);
   }

   public static boolean paint(class_630 var0, class_4665 var1, class_4588 var2, int var3, int var4, int var5) {
      return paint.invoke(var6 -> var6.draw(var0, var1, var2, var3, var4, var5), false);
   }

   public static void extract(class_11890 var0, class_10055 var1) {
      extract.invoke(var2 -> {
         var2.accept(var0, var1);
         return null;
      }, null);
   }

   public static void world(WorldRenderContext var0) {
      world.invoke(var1 -> {
         var1.accept(var0);
         return null;
      }, null);
   }

   @FunctionalInterface
   public interface Listener {
      boolean draw(class_630 var1, class_4665 var2, class_4588 var3, int var4, int var5, int var6);
   }
}
