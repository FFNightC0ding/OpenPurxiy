// Sourced By DevFFNightmare
package dev.purxiy.render;

import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import dev.purxiy.loader.fabric.ClientServices;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_10366;
import net.minecraft.class_10444;
import net.minecraft.class_1087;
import net.minecraft.class_11278;
import net.minecraft.class_11566;
import net.minecraft.class_1799;
import net.minecraft.class_2246;
import net.minecraft.class_310;
import net.minecraft.class_4587;
import net.minecraft.class_4608;
import net.minecraft.class_7923;
import net.minecraft.class_811;
import net.minecraft.class_308.class_11274;
import org.joml.Matrix4fStack;

public final class ItemPictures {
   private static final int CELL = 64;
   private static final int SIZE = 1024;
   private static final int PER_ROW = 16;
   private static final int CELLS = 256;
   private static final int PER_FRAME = 16;
   private static final Map<String, ItemPictures.TypeK> SLOTS = new HashMap<>();
   private static final String[] OWNERS = new String[256];
   private static final class_10444 STATE = new class_10444();
   
   private static GpuTexture color;
   
   private static GpuTexture depth;
   
   private static GpuTextureView colorView;
   
   private static GpuTextureView depthView;
   
   private static class_11278 projection;
   
   private static Object models;
   private static long frame = -1L;
   private static int rendered = 0;
   private static int next = 0;
   private static boolean failed;

   private ItemPictures() {
   }

   public static boolean draw(Canvas var0, class_1799 var1, float var2, float var3, float var4) {
      if (!var1.method_7960() && !failed) {
         ItemPictures.TypeK var5 = slot(var1);
         if (var5 == null) {
            return false;
         } else {
            int var6 = var5.cell % 16 * 64;
            int var7 = var5.cell / 16 * 64;
            var0.image(
               colorView,
               RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR),
               true,
               var2,
               var3,
               var4,
               var4,
               var6 / 1024.0F,
               (1024 - var7) / 1024.0F,
               (var6 + 64) / 1024.0F,
               (1024 - var7 - 64) / 1024.0F,
               0.0F,
               -1
            );
            return true;
         }
      } else {
         return false;
      }
   }

   private static String key(class_1799 var0) {
      return class_7923.field_41178.method_10221(var0.method_7909()).toString();
   }

   
   private static ItemPictures.TypeK slot(class_1799 var0) {
      long var1 = RenderPipelines.get().frame();
      if (var1 != frame) {
         frame = var1;
         rendered = 0;
         checkModels();
      }

      String var3 = key(var0);
      ItemPictures.TypeK var4 = SLOTS.get(var3);
      if (var4 != null) {
         var4.used = var1;
         return var4;
      } else if (rendered >= 16) {
         return null;
      } else {
         try {
            ensureAtlas();
            int var5 = freeCell(var1);
            if (var5 < 0) {
               return null;
            } else {
               render(var0, var5);
               rendered++;
               var4 = new ItemPictures.TypeK(var5);
               var4.used = var1;
               SLOTS.put(var3, var4);
               OWNERS[var5] = var3;
               return var4;
            }
         } catch (Throwable var6) {
            failed = true;
            ClientServices.LOGGER.error("Purxiy could not render item pictures for its menu", var6);
            return null;
         }
      }
   }

   private static int freeCell(long var0) {
      if (next < 256) {
         return next++;
      } else {
         int var2 = -1;
         long var3 = var0;

         for (int var5 = 0; var5 < 256; var5++) {
            ItemPictures.TypeK var6 = OWNERS[var5] == null ? null : SLOTS.get(OWNERS[var5]);
            long var7 = var6 == null ? Long.MIN_VALUE : var6.used;
            if (var7 < var3) {
               var3 = var7;
               var2 = var5;
            }
         }

         if (var2 >= 0 && OWNERS[var2] != null) {
            SLOTS.remove(OWNERS[var2]);
            OWNERS[var2] = null;
         }

         return var2;
      }
   }

   private static void checkModels() {
      class_1087 var0 = class_310.method_1551().method_1554().method_4743().method_3335(class_2246.field_10340.method_9564());
      if (var0 != models) {
         models = var0;
         SLOTS.clear();
         Arrays.fill(OWNERS, null);
         next = 0;
      }
   }

   private static void ensureAtlas() {
      if (color == null) {
         GpuDevice var0 = RenderSystem.getDevice();
         byte var1 = 13;
         color = var0.createTexture(() -> "Purxiy item pictures", var1, TextureFormat.RGBA8, 1024, 1024, 1, 1);
         depth = var0.createTexture(() -> "Purxiy item pictures depth", 9, TextureFormat.DEPTH32, 1024, 1024, 1, 1);
         colorView = var0.createTextureView(color);
         depthView = var0.createTextureView(depth);
         var0.createCommandEncoder().clearColorAndDepthTextures(color, 0, depth, 1.0);
         projection = new class_11278("Purxiy item pictures", -1000.0F, 1000.0F, true);
      }
   }

   private static void render(class_1799 var0, int var1) {
      class_310 var2 = class_310.method_1551();
      int var3 = var1 % 16 * 64;
      int var4 = var1 / 16 * 64;
      CommandEncoder var5 = RenderSystem.getDevice().createCommandEncoder();
      var5.clearColorAndDepthTextures(color, 0, depth, 1.0, var3, 1024 - var4 - 64, 64, 64);
      class_10444 var6 = STATE;
      var2.method_65386().method_65598(var6, var0, class_811.field_4317, var2.field_1687, (class_11566)null, 0);
      GpuTextureView var7 = RenderSystem.outputColorTextureOverride;
      GpuTextureView var8 = RenderSystem.outputDepthTextureOverride;
      RenderSystem.outputColorTextureOverride = colorView;
      RenderSystem.outputDepthTextureOverride = depthView;
      RenderSystem.backupProjectionMatrix();
      RenderSystem.setProjectionMatrix(projection.method_71092(1024.0F, 1024.0F), class_10366.field_54954);
      Matrix4fStack var9 = RenderSystem.getModelViewStack();
      var9.pushMatrix();
      var9.identity();

      try {
         var2.field_1773.method_71114().method_71034(var6.method_65608() ? class_11274.field_60027 : class_11274.field_60026);
         class_4587 var10 = new class_4587();
         var10.method_46416(var3 + 32.0F, var4 + 32.0F, 0.0F);
         var10.method_22905(64.0F, -64.0F, 64.0F);
         RenderSystem.enableScissorForRenderTypeDraws(var3, 1024 - var4 - 64, 64, 64);
         var6.method_65604(var10, var2.field_1773.method_72910(), 15728880, class_4608.field_21444, 0);
         var2.field_1773.method_72911().method_73002();
         var2.method_22940().method_23000().method_22993();
      } finally {
         RenderSystem.disableScissorForRenderTypeDraws();
         var9.popMatrix();
         RenderSystem.restoreProjectionMatrix();
         RenderSystem.outputColorTextureOverride = var7;
         RenderSystem.outputDepthTextureOverride = var8;
      }
   }

   private static final class TypeK {
      final int cell;
      long used;

      TypeK(int var1) {
         this.cell = var1;
      }
   }
}
