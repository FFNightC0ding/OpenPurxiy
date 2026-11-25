package dev.purxiy.core;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

public final class VersionGate {
   private VersionGate() {
   }

   public static VersionGate.PixelImage decode(byte[] var0, int var1) throws IOException {
      ByteBuffer var2 = MemoryUtil.memAlloc(var0.length);

      VersionGate.PixelImage var3;
      try {
         MemoryStack var4 = MemoryStack.stackPush();

         try {
            var2.put(var0).flip();
            IntBuffer var5 = var4.mallocInt(1);
            IntBuffer var6 = var4.mallocInt(1);
            IntBuffer var7 = var4.mallocInt(1);
            ByteBuffer var8 = STBImage.stbi_load_from_memory(var2, var5, var6, var7, 4);
            if (var8 == null) {
               throw new IOException(String.valueOf(STBImage.stbi_failure_reason()));
            }

            try {
               int var9 = var5.get(0);
               int var10 = var6.get(0);
               int var11 = Math.max(1, Math.min(var9, var10) / Math.max(1, var1));
               var3 = orient(reduce(var8, var9, var10, var11), exifOrientation(var0));
            } finally {
               STBImage.stbi_image_free(var8);
            }
         } catch (Throwable var24) {
            if (var4 != null) {
               try {
                  var4.close();
               } catch (Throwable var22) {
                  var24.addSuppressed(var22);
               }
            }

            throw var24;
         }

         if (var4 != null) {
            var4.close();
         }
      } finally {
         MemoryUtil.memFree(var2);
      }

      return var3;
   }

   static VersionGate.PixelImage reduce(ByteBuffer var0, int var1, int var2, int var3) {
      int var4 = Math.max(1, var1 / var3);
      int var5 = Math.max(1, var2 / var3);
      int[] var6 = new int[var4 * var5];

      for (int var7 = 0; var7 < var5; var7++) {
         for (int var8 = 0; var8 < var4; var8++) {
            long var9 = 0L;
            long var11 = 0L;
            long var13 = 0L;
            long var15 = 0L;

            for (int var17 = 0; var17 < var3; var17++) {
               int var18 = (var7 * var3 + var17) * var1;

               for (int var19 = 0; var19 < var3; var19++) {
                  int var20 = (var18 + var8 * var3 + var19) * 4;
                  int var21 = var0.get(var20 + 3) & 255;
                  var9 += (long)(var0.get(var20) & 255) * var21;
                  var11 += (long)(var0.get(var20 + 1) & 255) * var21;
                  var13 += (long)(var0.get(var20 + 2) & 255) * var21;
                  var15 += var21;
               }
            }

            int var22 = var3 * var3;
            int var23 = (int)((var15 + var22 / 2) / var22);
            var6[var7 * var4 + var8] = var15 == 0L ? 0 : var23 << 24 | (int)(var9 / var15) << 16 | (int)(var11 / var15) << 8 | (int)(var13 / var15);
         }
      }

      return new VersionGate.PixelImage(var4, var5, var6);
   }

   static VersionGate.PixelImage orient(VersionGate.PixelImage var0, int var1) {
      if (var1 >= 2 && var1 <= 8) {
         int var2 = var0.width();
         int var3 = var0.height();
         boolean var4 = var1 >= 5;
         int var5 = var4 ? var3 : var2;
         int var6 = var4 ? var2 : var3;
         int[] var7 = new int[var5 * var6];

         for (int var8 = 0; var8 < var6; var8++) {
            for (int var9 = 0; var9 < var5; var9++) {
               int var10;
               int var11;
               switch (var1) {
                  case 2:
                     var10 = var2 - 1 - var9;
                     var11 = var8;
                     break;
                  case 3:
                     var10 = var2 - 1 - var9;
                     var11 = var3 - 1 - var8;
                     break;
                  case 4:
                     var10 = var9;
                     var11 = var3 - 1 - var8;
                     break;
                  case 5:
                     var10 = var8;
                     var11 = var9;
                     break;
                  case 6:
                     var10 = var8;
                     var11 = var3 - 1 - var9;
                     break;
                  case 7:
                     var10 = var2 - 1 - var8;
                     var11 = var3 - 1 - var9;
                     break;
                  default:
                     var10 = var2 - 1 - var8;
                     var11 = var9;
               }

               var7[var8 * var5 + var9] = var0.argb()[var11 * var2 + var10];
            }
         }

         return new VersionGate.PixelImage(var5, var6, var7);
      } else {
         return var0;
      }
   }

   static int exifOrientation(byte[] var0) {
      if (var0.length >= 4 && (var0[0] & 255) == 255 && (var0[1] & 255) == 216) {
         int var1 = 2;

         while (var1 + 4 <= var0.length) {
            if ((var0[var1] & 255) != 255) {
               return 1;
            }

            int var2 = var0[var1 + 1] & 255;
            if (var2 != 216 && (var2 < 208 || var2 > 215) && var2 != 1) {
               if (var2 == 218 || var2 == 217) {
                  return 1;
               }

               int var3 = (var0[var1 + 2] & 255) << 8 | var0[var1 + 3] & 255;
               int var4 = var1 + 4;
               if (var2 == 225
                  && var3 >= 8
                  && var4 + 6 <= var0.length
                  && var0[var4] == 69
                  && var0[var4 + 1] == 120
                  && var0[var4 + 2] == 105
                  && var0[var4 + 3] == 102
                  && var0[var4 + 4] == 0
                  && var0[var4 + 5] == 0) {
                  return tiffOrientation(var0, var4 + 6, Math.min(var0.length, var1 + 2 + var3));
               }

               var1 += 2 + var3;
            } else {
               var1 += 2;
            }
         }

         return 1;
      } else {
         return 1;
      }
   }

   private static int tiffOrientation(byte[] var0, int var1, int var2) {
      if (var1 + 8 > var2) {
         return 1;
      } else {
         boolean var3 = var0[var1] == 73 && var0[var1 + 1] == 73;
         if (var3 || var0[var1] == 77 && var0[var1 + 1] == 77) {
            long var4 = var1 + u32(var0, var1 + 4, var3);
            if (var4 + 2L > var2) {
               return 1;
            } else {
               int var6 = u16(var0, (int)var4, var3);

               for (int var7 = 0; var7 < var6; var7++) {
                  int var8 = (int)var4 + 2 + var7 * 12;
                  if (var8 + 12 > var2) {
                     return 1;
                  }

                  if (u16(var0, var8, var3) == 274) {
                     int var9 = u16(var0, var8 + 8, var3);
                     return var9 >= 1 && var9 <= 8 ? var9 : 1;
                  }
               }

               return 1;
            }
         } else {
            return 1;
         }
      }
   }

   private static int u16(byte[] var0, int var1, boolean var2) {
      int var3 = var0[var1] & 255;
      int var4 = var0[var1 + 1] & 255;
      return var2 ? var4 << 8 | var3 : var3 << 8 | var4;
   }

   private static long u32(byte[] var0, int var1, boolean var2) {
      long var3 = 0L;

      for (int var5 = 0; var5 < 4; var5++) {
         int var6 = var2 ? var5 * 8 : (3 - var5) * 8;
         var3 |= (long)(var0[var1 + var5] & 255) << var6;
      }

      return var3;
   }

   public record PixelImage(int width, int height, int[] argb) {
   }
}
