package dev.purxiy.modules.mount;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import net.minecraft.class_2338;
import net.minecraft.class_2680;
import net.minecraft.class_3620;
import net.minecraft.class_638;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2902.class_2903;

final class MountMapTiles {
   private static final int M = 160;
   private static final int v = 112;
   private static final int X = 1400;
   private static final int[] N = x(41904803824400L);
   private final Map<Long, MountMapTiles.MapTile> r = new HashMap<>();
   private final class_2339 P = new class_2339();
   private class_638 K;
   private int R = 2;
   private long T;
   private int Q;
   private static final Integer[] b = new Integer[9];

   synchronized void zoom(boolean var1) {
      int var2 = var1 ? Math.max(1, this.R / 2) : Math.min(4, this.R * 2);
      if (var2 != this.R) {
         this.R = var2;
         this.r.clear();
      }
   }

   synchronized MountMapTiles.Data update(class_638 var1, double var2, double var4, long var6) {
      long var8 = 94177261105502L;
      long var10 = var8 ^ 78872865601971L;
      if (this.K != var1) {
         this.K = var1;
         this.r.clear();
      }

      int var12 = Math.floorDiv((int)Math.floor(var2), this.R) * this.R;
      int var13 = Math.floorDiv((int)Math.floor(var4), this.R) * this.R;
      int var14 = var12 - 80 * this.R;
      int var15 = var13 - 56 * this.R;
      int var16 = 1400;

      for (int var20 : N) {
         int var21 = var14 + var20 % 160 * this.R;
         int var22 = var15 + var20 / 160 * this.R;
         long var23 = class_2338.method_10064(var21, 0, var22);
         MountMapTiles.MapTile var25 = this.r.get(var23);
         if (var25 == null || var6 - var25.o >= (var25.U ? 8000000000L : 2000000000L)) {
            this.P.method_10103(var21, 0, var22);
            if (!var1.method_22340(this.P)) {
               this.r.put(var23, new MountMapTiles.MapTile(0, 0, var6, false));
            } else {
               if (var16-- <= 0) {
                  break;
               }

               int var26 = var1.method_8624(class_2903.field_13202, var21, var22) - 1;
               this.P.method_10103(var21, var26, var22);
               class_2680 var27 = var1.method_8320(this.P);
               class_3620 var28 = var27.method_26205(var1, this.P);

               for (int var29 = 0; var28 == class_3620.field_16008 && var29 < 12 && var26 > var1.method_31607(); var29++) {
                  this.P.method_10103(var21, --var26, var22);
                  var27 = var1.method_8320(this.P);
                  var28 = var27.method_26205(var1, this.P);
               }

               this.r.put(var23, new MountMapTiles.MapTile(var28.field_16011, var26, var6, true));
               this.Q++;
            }
         }
      }

      BufferedImage var30 = new BufferedImage(160, 112, 1);

      for (int var31 = 0; var31 < 112; var31++) {
         for (int var33 = 0; var33 < 160; var33++) {
            int var34 = var14 + var33 * this.R;
            int var35 = var15 + var31 * this.R;
            MountMapTiles.MapTile var36 = this.r.get(class_2338.method_10064(var34, 0, var35));
            if (var36 != null && var36.U) {
               MountMapTiles.MapTile var37 = this.r.get(class_2338.method_10064(var34, 0, var35 - this.R));
               float var24 = var37 != null && var37.U ? var36.T - var37.T : 0.0F;
               float var38 = Math.clamp(1.0F + var24 * 0.045F, 0.64F, 1.22F);
               int var39 = n(var36.G >> 16 & 0xFF, var38, var10);
               int var41 = n(var36.G >> 8 & 0xFF, var38, var10);
               int var42 = n(var36.G & 0xFF, var38, var10);
               var30.setRGB(var33, var31, var39 << 16 | var41 << 8 | var42);
            } else {
               var30.setRGB(var33, var31, (var33 + var31) / 6 % 2 == 0 ? 3951443 : 4346201);
            }
         }
      }

      if (var6 > this.T) {
         this.T = var6 + 3000000000L;
         int var32 = 160 * this.R;
         this.r.keySet().removeIf(var3 -> {
            MountFactory.MountSpec.ensureSourceClassInitialized();
            int var4x = class_2338.method_10061(var3);
            long var5 = 510043978590L;
            int var8x = var5 < 0L ? -1 : 1;
            var4x = Math.abs(var4x - var12);
            long var8xx = 510043978590L;
            int var10x = var8xx < 0L ? -1 : 1;
            if (var4x <= var32) {
               var4x = class_2338.method_10083(var3);
               long var10xx = 510043978590L;
               int var11 = var10xx < 0L ? -1 : 1;
               if (Math.abs(var4x - var13) <= var32) {
                  return false;
               }
            }

            return true;
         });
      }

      return new MountMapTiles.Data(var30, (var2 - var12) / this.R + 0.5, (var4 - var13) / this.R + 0.5, this.R);
   }

   int cachedColumns() {
      return this.r.size();
   }

   int sampledColumns() {
      return this.Q;
   }

   synchronized void clear() {
      this.K = null;
      this.r.clear();
      this.Q = 0;
   }

   private static int n(int var0, float var1, long var2) {
      return Math.clamp((long)Math.round(var0 * var1 * 0.9F + 19.0F), 0, 255);
   }

   private static int[] x(long var0) {
      Integer[] var2 = new Integer[17920];
      int var3 = 0;

      while (var3 < var2.length) {
         long var4 = 41904803824400L;
         int var7 = var4 < 0L ? -1 : 1;
         var2[var3] = var3;
         var3++;
         long var7x = 41904803824399L;
         int var8 = var7x < 0L ? -1 : 1;
      }

      Arrays.sort(var2, Comparator.comparingInt(var0x -> {
         int var1 = var0x % 160 - 80;
         int var2x = var0x / 160 - 56;
         return var1 * var1 + var2x * var2x;
      }));
      return Arrays.stream(var2).mapToInt(Integer::intValue).toArray();
   }

   record Data(BufferedImage b, double K, double n, int u) {
      public BufferedImage image() {
         return this.b;
      }

      public double offsetX() {
         return this.K;
      }

      public double offsetZ() {
         return this.n;
      }

      public int step() {
         return this.u;
      }

      public BufferedImage bufferedImageState2() {
         return this.b;
      }

      public double doubleState31() {
         return this.K;
      }

      public double doubleState18() {
         return this.n;
      }

      public int integerState24() {
         return this.u;
      }
   }

   private record MapTile(int G, int T, long o, boolean U) {
      public int color() {
         return this.G;
      }

      public int height() {
         return this.T;
      }

      public long checked() {
         return this.o;
      }

      public boolean loaded() {
         return this.U;
      }

      public int integerState18() {
         return this.G;
      }

      public int integerState36() {
         return this.T;
      }

      public long longState24() {
         return this.o;
      }

      public boolean booleanState31() {
         return this.U;
      }
   }
}
