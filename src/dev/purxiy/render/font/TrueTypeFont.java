// Sourced By DevFFNightmare
package dev.purxiy.render.font;

import dev.purxiy.assets.AssetPaths;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.ClientServices;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.longs.Long2FloatOpenHashMap;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.concurrent.Executor;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public final class TrueTypeFont implements AutoCloseable {
   public static final float DISTANCE_RANGE = 0.25F;
   public final String name;
   public final float ascent;
   public final float descent;
   public final float lineGap;
   public final float capHeight;
   public final float xHeight;
   private final ByteBuffer data;
   private final STBTTFontinfo info;
   private final float emScale;
   private final GlyphAtlas atlas;
   private final Executor workers;
   private final Int2ObjectOpenHashMap<FontFamily> glyphs = new Int2ObjectOpenHashMap();
   private final Long2FloatOpenHashMap kerning = new Long2FloatOpenHashMap();
   private static final int DIRECT = 256;
   private final FontFamily[] direct = new FontFamily[256];

   TrueTypeFont(String var1, String var2, GlyphAtlas var3, Executor var4) throws IOException {
      this.name = var1;
      this.atlas = var3;
      this.workers = var4;
      this.data = AssetPaths.direct(var2);
      this.info = STBTTFontinfo.malloc();
      if (!STBTruetype.stbtt_InitFont(this.info, this.data)) {
         this.close();
         throw new IOException("Not a valid TrueType font: " + var2);
      } else {
         this.emScale = STBTruetype.stbtt_ScaleForMappingEmToPixels(this.info, 1.0F);
         this.kerning.defaultReturnValue(Float.NaN);
         MemoryStack var5 = MemoryStack.stackPush();

         try {
            IntBuffer var6 = var5.mallocInt(1);
            IntBuffer var7 = var5.mallocInt(1);
            IntBuffer var8 = var5.mallocInt(1);
            STBTruetype.stbtt_GetFontVMetrics(this.info, var6, var7, var8);
            this.ascent = var6.get(0) * this.emScale;
            this.descent = -var7.get(0) * this.emScale;
            this.lineGap = var8.get(0) * this.emScale;
         } catch (Throwable var10) {
            if (var5 != null) {
               try {
                  var5.close();
               } catch (Throwable var9) {
                  var10.addSuppressed(var9);
               }
            }

            throw var10;
         }

         if (var5 != null) {
            var5.close();
         }

         float var11 = this.inkTop(72);
         float var12 = this.inkTop(120);
         this.capHeight = var11 > 0.0F ? var11 : this.ascent * 0.72F;
         this.xHeight = var12 > 0.0F ? var12 : this.capHeight * 0.75F;
      }
   }

   private float inkTop(int var1) {
      MemoryStack var2 = MemoryStack.stackPush();

      float var10;
      label45: {
         try {
            IntBuffer var4 = var2.mallocInt(1);
            IntBuffer var5 = var2.mallocInt(1);
            IntBuffer var6 = var2.mallocInt(1);
            IntBuffer var7 = var2.mallocInt(1);
            if (!STBTruetype.stbtt_GetCodepointBox(this.info, var1, var4, var5, var6, var7)) {
               var10 = 0.0F;
               break label45;
            }

            var10 = var7.get(0) * this.emScale;
         } catch (Throwable var9) {
            if (var2 != null) {
               try {
                  var2.close();
               } catch (Throwable var8) {
                  var9.addSuppressed(var8);
               }
            }

            throw var9;
         }

         if (var2 != null) {
            var2.close();
         }

         return var10;
      }

      if (var2 != null) {
         var2.close();
      }

      return var10;
   }

   public float[] inkExtent(CharSequence var1) {
      float var2 = 0.0F;
      float var3 = 0.0F;
      MemoryStack var4 = MemoryStack.stackPush();

      try {
         IntBuffer var5 = var4.mallocInt(1);
         IntBuffer var6 = var4.mallocInt(1);
         IntBuffer var7 = var4.mallocInt(1);
         IntBuffer var8 = var4.mallocInt(1);
         int var9 = 0;

         while (var9 < var1.length()) {
            int var10 = Character.codePointAt(var1, var9);
            var9 += Character.charCount(var10);
            if (STBTruetype.stbtt_GetCodepointBox(this.info, var10, var5, var6, var7, var8)) {
               var2 = Math.max(var2, -var6.get(0) * this.emScale);
               var3 = Math.max(var3, var8.get(0) * this.emScale);
            }
         }
      } catch (Throwable var12) {
         if (var4 != null) {
            try {
               var4.close();
            } catch (Throwable var11) {
               var12.addSuppressed(var11);
            }
         }

         throw var12;
      }

      if (var4 != null) {
         var4.close();
      }

      return new float[]{var2, var3};
   }

   public float lineHeight() {
      return this.ascent + this.descent + this.lineGap;
   }

   public boolean hasGlyph(int var1) {
      return STBTruetype.stbtt_FindGlyphIndex(this.info, var1) != 0;
   }

   public FontFamily glyph(int var1) {
      if (var1 >= 0 && var1 < 256) {
         FontFamily var2 = this.direct[var1];
         if (var2 == null) {
            var2 = this.lookup(var1);
            this.direct[var1] = var2;
         }

         return var2;
      } else {
         return this.lookup(var1);
      }
   }

   private FontFamily lookup(int var1) {
      FontFamily var2 = (FontFamily)this.glyphs.get(var1);
      if (var2 == null) {
         var2 = this.create(var1);
         this.glyphs.put(var1, var2);
      }

      return var2;
   }

   private FontFamily create(int var1) {
      int var2 = STBTruetype.stbtt_FindGlyphIndex(this.info, var1);
      MemoryStack var3 = MemoryStack.stackPush();

      float var4;
      try {
         IntBuffer var5 = var3.mallocInt(1);
         IntBuffer var6 = var3.mallocInt(1);
         STBTruetype.stbtt_GetGlyphHMetrics(this.info, var2, var5, var6);
         var4 = var5.get(0) * this.emScale;
      } catch (Throwable var8) {
         if (var3 != null) {
            try {
               var3.close();
            } catch (Throwable var7) {
               var8.addSuppressed(var7);
            }
         }

         throw var8;
      }

      if (var3 != null) {
         var3.close();
      }

      FontFamily var9 = new FontFamily(var1, var2, var4);
      if (!Character.isWhitespace(var1) && !Character.isISOControl(var1)) {
         this.workers.execute(() -> {
            try {
               GlyphRasterizer.TypeW var4x = GlyphRasterizer.rasterize(this.info, var2);
               if (var4x == null) {
                  var9.state = 2;
                  return;
               }

               float var5x = 64.0F;
               var9.x0 = var4x.originX() / var5x;
               var9.y0 = var4x.originY() / var5x;
               var9.x1 = (var4x.originX() + var4x.width()) / var5x;
               var9.y1 = (var4x.originY() + var4x.height()) / var5x;
               this.atlas.enqueue(var9, var4x);
            } catch (Throwable var6x) {
               var9.state = 2;
               ClientServices.LOGGER.error("Failed to rasterize glyph U+{} of {}", new Object[]{Integer.toHexString(var1), this.name, var6x});
            }
         });
         return var9;
      } else {
         var9.state = 2;
         return var9;
      }
   }

   public float kerning(FontFamily var1, FontFamily var2) {
      if (var2.codepoint >= 0 && var2.codepoint < 256) {
         float[] var3 = var1.kerningAfter;
         if (var3 == null) {
            var3 = new float[256];
            Arrays.fill(var3, Float.NaN);
            var1.kerningAfter = var3;
         }

         float var4 = var3[var2.codepoint];
         if (Float.isNaN(var4)) {
            var4 = this.pairKerning(var1, var2);
            var3[var2.codepoint] = var4;
         }

         return var4;
      } else {
         return this.pairKerning(var1, var2);
      }
   }

   private float pairKerning(FontFamily var1, FontFamily var2) {
      long var3 = (long)var1.index << 32 | var2.index & 4294967295L;
      float var5 = this.kerning.get(var3);
      if (Float.isNaN(var5)) {
         var5 = STBTruetype.stbtt_GetGlyphKernAdvance(this.info, var1.index, var2.index) * this.emScale;
         this.kerning.put(var3, var5);
      }

      return var5;
   }

   public float width(CharSequence var1, float var2, float var3) {
      return this.measure(Translations.display(var1), var2, var3);
   }

   public float measure(CharSequence var1, float var2, float var3) {
      float var4 = 0.0F;
      FontFamily var5 = null;
      int var6 = 0;
      int var8 = 0;

      while (var8 < var1.length()) {
         int var9 = Character.codePointAt(var1, var8);
         var8 += Character.charCount(var9);
         FontFamily var7 = this.glyph(var9);
         if (var5 != null) {
            var4 += this.kerning(var5, var7);
         }

         var4 += var7.advance;
         var6++;
         var5 = var7;
      }

      if (var6 > 1) {
         var4 += var3 * (var6 - 1);
      }

      return var4 * var2;
   }

   public float width(CharSequence var1, float var2) {
      return this.width(var1, var2, 0.0F);
   }

   public void preload(CharSequence var1) {
      int var2 = 0;

      while (var2 < var1.length()) {
         int var3 = Character.codePointAt(var1, var2);
         var2 += Character.charCount(var3);
         this.glyph(var3);
      }
   }

   @Override
   public void close() {
      this.info.free();
      MemoryUtil.memFree(this.data);
   }
}
