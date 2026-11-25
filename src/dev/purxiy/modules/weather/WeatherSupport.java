package dev.purxiy.modules.weather;

import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import java.nio.ByteBuffer;
import java.util.Arrays;
import net.minecraft.class_2806;
import net.minecraft.class_2818;
import net.minecraft.class_2902;
import net.minecraft.class_3486;
import net.minecraft.class_3610;
import net.minecraft.class_3612;
import net.minecraft.class_638;
import net.minecraft.class_1011.class_1012;
import net.minecraft.class_2902.class_2903;
import org.lwjgl.system.MemoryUtil;

final class WeatherSupport {
   static final int S = 1024;
   private static final int K = 64;
   private static final int e = 31;
   private static final int g = 1024;
   private static final int t = 2048;
   private static final float y = 2400.0F;
   private static final float W = 15.0F;
   private static final int x = 160;
   private static final int r = 128;
   private static final int s = 64;
   private static final int q = 32;
   private static final int v = 16;
   private final long[] Q = new long[4096];
   private final byte[][] X = new byte[4096][];
   private final int[] G = new int[4096];
   private final long[] p = new long[4096];
   private final boolean[] Z = new boolean[4096];
   private final int[] U = new int[4096];
   private int m;
   private final byte[] l = new byte[1024];
   private int[] a = new int[0];
   private int M = -1;
   private int A;
   private float O;
   private float L;
   private int T;
   private int b;
   private int R;
   private GpuTexture D;
   private GpuTextureView d;
   private ByteBuffer j;
   private static final String c = "SourceByDevFFnightmare DeObfDeCompByGuideX rain map";
   private static final long[] i = new long[]{
      3672093034909269501L, 4691807244705616939L, -6270381546769424159L, -6563461524158654196L, 8455781183933288861L, -4836854492990089018L
   };
   private static final Long[] k = new Long[6];

   WeatherSupport() {
      Arrays.fill(this.Q, Long.MIN_VALUE);
   }

   GpuTextureView view() {
      return this.d;
   }

   int knownColumns() {
      return this.R;
   }

   void clear() {
      Arrays.fill(this.Q, Long.MIN_VALUE);
      Arrays.fill(this.G, 0);
      Arrays.fill(this.Z, false);
      this.m = 0;
      this.A = 0;
      this.R = 0;
      if (this.D != null) {
         this.D.close();
         this.d.close();
         this.D = null;
         this.d = null;
      }
   }

   void scan(class_638 var1, int var2, int var3, int var4, float var5) {
      long var6 = 110683411465137L;
      long var8 = var6 ^ 3282012056662L;
      int var10 = (int)((var6 ^ 3282012056662L) >>> 32);
      int var11 = (int)((var6 ^ 3282012056662L) << 32 >>> 48);
      int var12 = (int)(var8 << 48 >>> 48);
      long var13 = var6 ^ 12137730240249L;
      int var15 = Math.min(var4 + 1, 31);
      if (var15 != this.M) {
         this.k(var15, var13);
      }

      if (Math.abs(var2 - this.T) > 2 || Math.abs(var3 - this.b) > 2) {
         this.A = 0;
      }

      this.T = var2;
      this.b = var3;
      this.L = Math.min(this.L + var5 * 15.0F, 1.0F);
      if (this.L >= 1.0F) {
         this.L = 0.0F;

         for (int var16 = -1; var16 <= 1; var16++) {
            for (int var17 = -1; var17 <= 1; var17++) {
               this.l(var10, (short)var11, var1, var2 + var17, var3 + var16, (short)var12);
            }
         }
      }

      int var19;
      if (this.R < 20736) {
         var19 = 160;
      } else {
         this.O = Math.min(this.O + var5 * 2400.0F, 160.0F);
         var19 = (int)this.O;
         this.O -= var19;
      }

      for (int var20 = 0; var20 < var19 && this.a.length > 0; var20++) {
         int var18 = this.a[this.A];
         this.A = (this.A + 1) % this.a.length;
         this.l(var10, (short)var11, var1, var2 + (short)(var18 >> 16), var3 + (short)var18, (short)var12);
      }
   }

   void upload(GpuDevice var1, CommandEncoder var2) {
      if (this.D == null) {
         this.D = var1.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX rain map", 13, TextureFormat.RGBA8, 1024, 1024, 1, 1);
         this.d = var1.createTextureView(this.D);
         var2.clearColorTexture(this.D, 0);
         this.j = this.j == null ? MemoryUtil.memAlloc(1024) : this.j;
         this.m = 0;

         for (int var3 = 0; var3 < this.Q.length; var3++) {
            this.Z[var3] = this.Q[var3] != Long.MIN_VALUE;
            if (this.Z[var3]) {
               this.U[this.m++] = var3;
            }
         }
      }

      for (int var6 = 0; var6 < this.m; var6++) {
         int var4 = this.U[var6];
         this.Z[var4] = false;
         ByteBuffer var5 = this.j.clear();
         if (this.Q[var4] != Long.MIN_VALUE && this.X[var4] != null) {
            var5.put(this.X[var4]).flip();
         } else {
            MemoryUtil.memSet(MemoryUtil.memAddress0(var5), 0, 1024L);
         }

         var2.writeToTexture(this.D, var5, class_1012.field_4997, 0, 0, var4 % 64 * 16, var4 / 64 * 16, 16, 16);
      }

      this.m = 0;
   }

   void close() {
      this.clear();
      if (this.j != null) {
         MemoryUtil.memFree(this.j);
         this.j = null;
      }
   }

   private void l(int var1, short var2, class_638 var3, int var4, int var5, short var6) {
      long var7 = 112521067753447L;
      long var9 = var7 ^ 31226017373802L;
      long var11 = (var7 ^ 97522351754283L) >>> 8;
      int var13 = (int)((var7 ^ 97522351754283L) << 56 >>> 56);
      long var14 = var7 ^ 47968324958880L;
      WeatherModule.ensureSourceClassInitialized();
      int var16 = (var4 & 63) + (var5 & 63) * 64;
      long var17 = (long)var4 << 32 | var5 & 4294967295L;
      class_2818 var19 = var3.method_2935().method_2857(var4, var5, class_2806.field_12803, false);
      if (var19 != null) {
         int var20 = var19.method_12223() ? 1 : 0;
         short var21 = -4121;
         if (var20 == 0) {
            boolean var44;
            boolean var22;
            byte[] var23;
            label116: {
               var22 = this.Q[var16] != var17;
               var23 = var22 ? null : this.X[var16];
               if (!var22) {
                  var20 = this.G[var16]++;
                  if ((var20 & 15) != 0) {
                     var44 = false;
                     break label116;
                  }
               }

               var44 = true;
            }

            boolean var24 = var44;
            class_2902 var25 = X(var11, var19, class_2903.field_13197, (byte)var13);
            class_2902 var26 = X(var11, var19, class_2903.field_13203, (byte)var13);
            long var27 = i(var25.method_12598(), var26.method_12598(), var14);
            short var29 = 26198;
            if (var44 && this.p[var16] == var27) {
               return;
            }

            this.p[var16] = var27;
            int var30 = var4 >> 6 & 3 | (var5 >> 6 & 3) << 2;
            byte[] var31 = this.l;
            boolean var32 = var22;

            for (int var33 = 0; var33 < 16; var33++) {
               var29 = 26198;

               for (int var34 = 0; var34 < 16; var34++) {
                  int var35;
                  int var36;
                  int var37;
                  int var38;
                  label128: {
                     var35 = (var33 * 16 + var34) * 4;
                     int var39 = var25.method_12603(var34, var33);
                     int var40 = var26.method_12603(var34, var33);
                     var29 = 12309;
                     var21 = 23109;
                     var36 = Math.clamp((long)(var39 + 2048), 0, 4095);
                     var37 = Math.clamp((long)(var40 + 2048), 0, 4095);
                     if (!var24) {
                        var21 = -4121;
                        if (var23 != null) {
                           int var41 = var23[var35];
                           var29 = 26198;
                           var41 &= 255;
                           var29 = -4121;
                           if ((var41 | (var23[var35 + 1] & 15) << 8) == var36) {
                              int var42 = var23[var35 + 2];
                              var29 = 14095;
                              var21 = 26198;
                              var42 = var42 & 0xFF | (var23[var35 + 3] & 15) << 8;
                              var29 = -4121;
                              if (var42 == var37) {
                                 var38 = var23[var35 + 1] & 240;
                                 break label128;
                              }
                           }
                        }
                     }

                     var38 = 128;
                     class_3610 var78 = var39 > var3.method_31607() ? var19.method_12234(var34, var39 - 1, var33) : class_3612.field_15906.method_15785();
                     boolean var80 = var78.method_15769();
                     var21 = 26198;
                     if (!var80) {
                        var38 |= var78.method_15767(class_3486.field_15517) ? 64 : 16;
                     }

                     var21 = -4121;
                     var21 = 23109;
                     if (var40 < var39) {
                        var38 |= 32;
                     }
                  }

                  var31[var35] = (byte)var36;
                  var29 = -4121;
                  var31[var35 + 1] = (byte)(var36 >> 8 | var38);
                  var31[var35 + 2] = (byte)var37;
                  var31[var35 + 3] = (byte)(var37 >> 8 | var30 << 4);
                  if (!var32) {
                     byte var70 = var23[var35];
                     var21 = 26198;
                     byte var74 = var31[var35];
                     var21 = 23109;
                     if (var70 == var74) {
                        var70 = var23[var35 + 1];
                        var21 = -4121;
                        var74 = var31[var35 + 1];
                        var21 = 26198;
                        if (var70 == var74) {
                           var70 = var23[var35 + 2];
                           var21 = -4121;
                           var74 = var31[var35 + 2];
                           var21 = 23109;
                           if (var70 == var74) {
                              var70 = var23[var35 + 3];
                              var21 = 26198;
                              if (var70 == var31[var35 + 3]) {
                                 continue;
                              }
                           }
                        }
                     }

                     var32 = true;
                  }
               }
            }

            if (!var32) {
               return;
            }

            var21 = -4121;
            byte[] var69 = this.X[var16];
            var29 = 23109;
            if (var69 == null) {
               this.X[var16] = new byte[1024];
            }

            System.arraycopy(var31, 0, this.X[var16], 0, 1024);
            var21 = -4121;
            var29 = 26198;
            if (this.Q[var16] == Long.MIN_VALUE) {
               this.R += 256;
            }

            this.Q[var16] = var17;
            this.t(var16, var9);
            return;
         }
      }

      if (this.Q[var16] == var17) {
         this.Q[var16] = Long.MIN_VALUE;
         this.R -= 256;
         this.t(var16, var9);
      }
   }

   private static class_2902 X(long var0, class_2818 var2, class_2903 var3, byte var4) {
      WeatherModule.ensureSourceClassInitialized();
      long var5 = 270120216483L;
      int var7 = var5 < 0L ? -1 : 1;
      if (!var2.method_39295(var3)) {
         var2.method_12005(var3, 0, 0);
      }

      return var2.method_12032(var3);
   }

   private static long i(long[] var0, long[] var1, long var2) {
      WeatherModule.ensureSourceClassInitialized();
      long var4 = -3750763034362895579L;

      for (long var10 : var0) {
         long var12 = 85719573714247L;
         int var13 = var12 < 0L ? -1 : 1;
         var4 = (var4 ^ var10) * 1099511628211L;
      }

      long[] var6 = var1;
      int var15 = var1.length;
      long var16 = 85719573714247L;
      int var11 = var16 < 0L ? -1 : 1;

      for (int var11x = 0; var11x < var15; var11x++) {
         long var17 = var6[var11x];
         var4 = (var4 ^ var17) * 1099511628211L;
      }

      return var4;
   }

   private void t(int var1, long var2) {
      WeatherModule.ensureSourceClassInitialized();
      long var4 = 134346676220300L;
      int var6 = var4 < 0L ? -1 : 1;
      if (!this.Z[var1]) {
         this.Z[var1] = true;
         this.U[this.m++] = var1;
      }
   }

   private void k(int var1, long var2) {
      int var4 = var1 * 2 + 1;
      Integer[] var5 = new Integer[var4 * var4];
      WeatherModule.ensureSourceClassInitialized();
      int var6 = 0;

      for (int var7 = -var1; var7 <= var1; var7++) {
         long var8 = 122734939286856L;
         int var22 = var8 < 0L ? -1 : 1;

         for (int var11 = -var1; var11 <= var1; var11++) {
            int var12 = var11 * var11 + var7 * var7;
            long var13 = 122734939286855L;
            int var10x = var13 < 0L ? -1 : 1;
            var10x = var1 + 1;
            long var15 = 122734939286855L;
            int var17 = var15 < 0L ? -1 : 1;
            if (var12 <= var10x * (var1 + 1)) {
               var5[var6++] = var11 << 16 | var7 & 65535;
            }
         }

         long var21 = 122734939286855L;
         int var25 = var21 < 0L ? -1 : 1;
      }

      Arrays.sort(var5, 0, var6, (var0, var1x) -> {
         long var2x = 32104409544341L;
         return Integer.compare(r(var0, var2x), r(var1x, var2x));
      });
      long var18 = 122734939286856L;
      int var20 = var18 < 0L ? -1 : 1;
      this.a = new int[var6];

      for (int var19 = 0; var19 < var6; var19++) {
         this.a[var19] = var5[var19];
         long var22 = 122734939286856L;
         int var26 = var22 < 0L ? -1 : 1;
      }

      this.M = var1;
      long var20x = 122734939286856L;
      int var24 = var20x < 0L ? -1 : 1;
      this.A = 0;
   }

   private static int r(int var0, long var1) {
      short var3 = (short)(var0 >> 16);
      short var4 = (short)var0;
      return var3 * var3 + var4 * var4;
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 21472) & 32767;
      if (k[var3] == null) {
         k[var3] = i[var3] ^ var1;
      }

      return k[var3];
   }
}
