// Sourced By DevFFNightmare
package dev.purxiy.render.font;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

final class GlyphRasterizer {
   static final int EM = 64;
   static final int SPREAD = 8;
   static final int OVERSAMPLE = 4;
   private static final float INF = 1.0E20F;

   private GlyphRasterizer() {
   }

   
   static GlyphRasterizer.TypeW rasterize(STBTTFontinfo var0, int var1) {
      float var2 = STBTruetype.stbtt_ScaleForMappingEmToPixels(var0, 256.0F);
      MemoryStack var3 = MemoryStack.stackPush();

      int var4;
      int var5;
      int var6;
      int var7;
      try {
         IntBuffer var8 = var3.mallocInt(1);
         IntBuffer var9 = var3.mallocInt(1);
         IntBuffer var10 = var3.mallocInt(1);
         IntBuffer var11 = var3.mallocInt(1);
         STBTruetype.stbtt_GetGlyphBitmapBox(var0, var1, var2, var2, var8, var9, var10, var11);
         var4 = var8.get(0);
         var5 = var9.get(0);
         var6 = var10.get(0);
         var7 = var11.get(0);
      } catch (Throwable var37) {
         if (var3 != null) {
            try {
               var3.close();
            } catch (Throwable var36) {
               var37.addSuppressed(var36);
            }
         }

         throw var37;
      }

      if (var3 != null) {
         var3.close();
      }

      if (var6 > var4 && var7 > var5) {
         int var39 = Math.floorDiv(var4, 4) - 8;
         int var40 = Math.floorDiv(var5, 4) - 8;
         int var41 = -Math.floorDiv(-var6, 4) + 8;
         int var42 = -Math.floorDiv(-var7, 4) + 8;
         int var12 = var41 - var39;
         int var13 = var42 - var40;
         int var14 = var12 * 4;
         int var15 = var13 * 4;
         int var16 = var14 * var15;
         ByteBuffer var17 = MemoryUtil.memCalloc(var16);
         float[] var18 = new float[var16];
         float[] var19 = new float[var16];

         try {
            int var20 = (var5 - var40 * 4) * var14 + (var4 - var39 * 4);
            ByteBuffer var21 = MemoryUtil.memSlice(var17, var20, var16 - var20);
            STBTruetype.stbtt_MakeGlyphBitmap(var0, var21, var6 - var4, var7 - var5, var14, var2, var2, var1);

            for (int var22 = 0; var22 < var16; var22++) {
               float var23 = (var17.get(var22) & 255) / 255.0F;
               if (var23 <= 0.0F) {
                  var18[var22] = 1.0E20F;
                  var19[var22] = 0.0F;
               } else if (var23 >= 1.0F) {
                  var18[var22] = 0.0F;
                  var19[var22] = 1.0E20F;
               } else {
                  float var24 = 0.5F - var23;
                  var18[var22] = var24 > 0.0F ? var24 * var24 : 0.0F;
                  var19[var22] = var24 < 0.0F ? var24 * var24 : 0.0F;
               }
            }
         } finally {
            MemoryUtil.memFree(var17);
         }

         int var43 = Math.max(var14, var15);
         float[] var44 = new float[var43];
         float[] var45 = new float[var43 + 1];
         int[] var46 = new int[var43];
         edt(var18, var14, var15, var44, var46, var45);
         edt(var19, var14, var15, var44, var46, var45);
         byte[] var47 = new byte[var12 * var13];
         byte var25 = 2;

         for (int var26 = 0; var26 < var13; var26++) {
            int var27 = var26 * 4 + var25;

            for (int var28 = 0; var28 < var12; var28++) {
               int var29 = var28 * 4 + var25;
               float var30 = signed(var18, var19, (var27 - 1) * var14 + var29 - 1)
                  + signed(var18, var19, (var27 - 1) * var14 + var29)
                  + signed(var18, var19, var27 * var14 + var29 - 1)
                  + signed(var18, var19, var27 * var14 + var29);
               var30 = var30 * 0.25F / 4.0F;
               float var31 = 0.5F - var30 / 16.0F;
               int var32 = Math.round(Math.max(0.0F, Math.min(1.0F, var31)) * 255.0F);
               var47[var26 * var12 + var28] = (byte)var32;
            }
         }

         return new GlyphRasterizer.TypeW(var12, var13, var47, var39, var40);
      } else {
         return null;
      }
   }

   private static float signed(float[] var0, float[] var1, int var2) {
      return (float)(Math.sqrt(var0[var2]) - Math.sqrt(var1[var2]));
   }

   private static void edt(float[] var0, int var1, int var2, float[] var3, int[] var4, float[] var5) {
      for (int var6 = 0; var6 < var1; var6++) {
         edt1d(var0, var6, var1, var2, var3, var4, var5);
      }

      for (int var7 = 0; var7 < var2; var7++) {
         edt1d(var0, var7 * var1, 1, var1, var3, var4, var5);
      }
   }

   private static void edt1d(float[] var0, int var1, int var2, int var3, float[] var4, int[] var5, float[] var6) {
      var5[0] = 0;
      var6[0] = -1.0E20F;
      var6[1] = 1.0E20F;
      var4[0] = var0[var1];
      int var7 = 0;

      for (int var8 = 1; var8 < var3; var8++) {
         var4[var8] = var0[var1 + var8 * var2];
         float var9 = (float)var8 * var8;

         float var10;
         do {
            int var11 = var5[var7];
            var10 = (var4[var8] - var4[var11] + var9 - (float)var11 * var11) / (var8 - var11) / 2.0F;
         } while (var10 <= var6[var7] && --var7 > -1);

         var5[++var7] = var8;
         var6[var7] = var10;
         var6[var7 + 1] = 1.0E20F;
      }

      var7 = 0;

      for (int var13 = 0; var13 < var3; var13++) {
         while (var6[var7 + 1] < var13) {
            var7++;
         }

         int var14 = var5[var7];
         float var15 = var13 - var14;
         var0[var1 + var13 * var2] = var4[var14] + var15 * var15;
      }
   }

   record TypeW(int width, int height, byte[] data, int originX, int originY) {
   }
}
