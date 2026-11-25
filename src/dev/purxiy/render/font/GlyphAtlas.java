package dev.purxiy.render.font;

import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.class_12137;
import net.minecraft.class_1011.class_1012;
import org.lwjgl.system.MemoryUtil;

public final class GlyphAtlas implements AutoCloseable {
   static final int SIZE = 2048;
   private static final int GUTTER = 1;
   private static final int SHELF_STEP = 8;
   private final List<GlyphAtlas.GlyphPage> pages = new ArrayList<>();
   private final ConcurrentLinkedQueue<GlyphAtlas.GlyphKey> pending = new ConcurrentLinkedQueue<>();
   private int uploadedGlyphs;

   void enqueue(FontFamily var1, GlyphRasterizer.GlyphBitmap var2) {
      this.pending.add(new GlyphAtlas.GlyphKey(var1, var2));
   }

   public void flushUploads() {
      if (!this.pending.isEmpty()) {
         CommandEncoder var1 = RenderSystem.getDevice().createCommandEncoder();
         ByteBuffer var2 = null;

         GlyphAtlas.GlyphKey var3;
         try {
            while ((var3 = this.pending.poll()) != null) {
               GlyphRasterizer.GlyphBitmap var4 = var3.field();
               GlyphAtlas.GlyphPage var5 = this.allocate(var4.width(), var4.height());
               int[] var6 = var5.lastSlot;
               int var7 = var4.width() * var4.height();
               if (var2 == null || var2.capacity() < var7) {
                  if (var2 != null) {
                     MemoryUtil.memFree(var2);
                  }

                  var2 = MemoryUtil.memAlloc(Math.max(var7, 16384));
               }

               var2.clear();
               var2.put(var4.data(), 0, var7).flip();
               var1.writeToTexture(var5.texture, var2, class_1012.field_4998, 0, 0, var6[0], var6[1], var4.width(), var4.height());
               FontFamily var8 = var3.glyph();
               var8.page = var5.view;
               var8.u0 = var6[0] / 2048.0F;
               var8.v0 = var6[1] / 2048.0F;
               var8.u1 = (var6[0] + var4.width()) / 2048.0F;
               var8.v1 = (var6[1] + var4.height()) / 2048.0F;
               var8.state = 1;
               this.uploadedGlyphs++;
            }
         } finally {
            if (var2 != null) {
               MemoryUtil.memFree(var2);
            }
         }
      }
   }

   public class_12137 sampler() {
      return RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
   }

   public int glyphCount() {
      return this.uploadedGlyphs;
   }

   public int pageCount() {
      return this.pages.size();
   }

   private GlyphAtlas.GlyphPage allocate(int var1, int var2) {
      for (GlyphAtlas.GlyphPage var4 : this.pages) {
         if (var4.tryPlace(var1, var2)) {
            return var4;
         }
      }

      GlyphAtlas.GlyphPage var5 = new GlyphAtlas.GlyphPage(this.pages.size());
      this.pages.add(var5);
      if (!var5.tryPlace(var1, var2)) {
         throw new IllegalStateException("Glyph of " + var1 + "x" + var2 + " does not fit an empty atlas page");
      } else {
         return var5;
      }
   }

   @Override
   public void close() {
      for (GlyphAtlas.GlyphPage var2 : this.pages) {
         var2.view.close();
         var2.texture.close();
      }

      this.pages.clear();
   }

   private record GlyphKey(FontFamily glyph, GlyphRasterizer.GlyphBitmap field) {
   }

   private static final class GlyphPage {
      final GpuTexture texture;
      final GpuTextureView view;
      final List<int[]> shelves = new ArrayList<>();
      int bottom;
      final int[] lastSlot = new int[2];

      GlyphPage(int var1) {
         GpuDevice var2 = RenderSystem.getDevice();
         this.texture = var2.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX glyph atlas " + var1, 13, TextureFormat.RED8, 2048, 2048, 1, 1);
         this.view = var2.createTextureView(this.texture);
         var2.createCommandEncoder().clearColorTexture(this.texture, 0);
      }

      boolean tryPlace(int var1, int var2) {
         int var3 = var1 + 1;
         int var4 = (var2 + 1 + 8 - 1) / 8 * 8;

         for (int[] var6 : this.shelves) {
            if (var6[1] == var4 && var6[2] + var3 <= 2048) {
               this.lastSlot[0] = var6[2];
               this.lastSlot[1] = var6[0];
               var6[2] += var3;
               return true;
            }
         }

         if (this.bottom + var4 <= 2048 && var3 <= 2048) {
            int[] var7 = new int[]{this.bottom, var4, var3};
            this.shelves.add(var7);
            this.lastSlot[0] = 0;
            this.lastSlot[1] = this.bottom;
            this.bottom += var4;
            return true;
         } else {
            return false;
         }
      }
   }
}
