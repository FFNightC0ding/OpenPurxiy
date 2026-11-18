// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.AddressMode;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import dev.purxiy.loader.fabric.ClientServices;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.Random;
import net.minecraft.class_12137;
import net.minecraft.class_1944;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_3532;
import net.minecraft.class_3620;
import net.minecraft.class_638;
import net.minecraft.class_1011.class_1012;
import net.minecraft.class_2338.class_2339;
import org.lwjgl.system.MemoryUtil;

public final class MountUpdater7 {
   private static final int O = 32;
   private static final int r = 6;
   private static final int d = 6144;
   private static final int[] P = G(6814, '荟', (short)-6850);
   private static final float[][] Z = g(119887490188902L);
   private static final float j = 42.0F;
   private final int[][] y = new int[6][1024];
   private final MountUpdater7.TypeL G = new MountUpdater7.TypeL();
   private GpuTexture W;
   private GpuTextureView h;
   private class_12137 w;
   private ByteBuffer g;
   private int b;
   private int x;
   private long z;
   private boolean F;
   private class_638 S;
   private double c = Double.NaN;
   private double I;
   private static final String a = "Purxiy garage reflections";
   private static final long[] e = new long[]{
      6330045000750030886L,
      -3145241104478784863L,
      -3774776217024102249L,
      -4825378306739651350L,
      -72532496657370900L,
      7615360063111068921L,
      -4582491224214580001L,
      462108824584507726L,
      3179303290647099108L,
      3140217579931894745L,
      2823555425637275749L,
      3061182332589621974L,
      3865723959280684122L,
      -2313511910294821095L,
      -1974275822202702320L,
      2980621168785886216L,
      -4554024085300211429L,
      5761771443421696422L,
      9091707626119659723L,
      2496925708203082204L,
      -8626415305417010287L
   };

   private static final long i = 2636928640L;

   public GpuTextureView view() {
      return this.h;
   }

   public class_12137 sampler() {
      return this.w;
   }

   public void update(class_638 var1, class_243 var2, float var3, boolean var4) {
      long var5 = 121924981538280L;
      long var7 = var5 ^ 113791236373567L;
      long var9 = var5 ^ 38418132364076L;
      this.J(var7);
      if (var1 != this.S) {
         this.S = var1;

         for (int[] var14 : this.y) {
            Arrays.fill(var14, -8355712);
         }

         this.x = 0;
         this.b = 0;
      }

      boolean var22 = Double.isNaN(this.c) || Math.abs(var2.field_1352 - this.c) + Math.abs(var2.field_1350 - this.I) > 0.4;
      if (var22) {
         this.c = var2.field_1352;
         this.I = var2.field_1350;
      }

      int var23 = this.x < 6144 ? 1400 : (var22 ? 340 : 60) / (var4 ? 4 : 1);
      double var24 = var2.field_1352;
      double var15 = var2.field_1351 + 1.05;
      double var17 = var2.field_1350;

      for (int var19 = 0; var19 < var23; var19++) {
         int var20 = P[this.b];
         this.b = (this.b + 1) % 6144;
         float[] var21 = Z[var20];
         this.y[var20 / 1024][var20 % 1024] = this.G.trace(var1, var24, var15, var17, var21[0], var21[1], var21[2], var3, 42.0, 72);
         this.x++;
      }

      this.F = true;
      long var25 = System.nanoTime();
      if (this.F && var25 - this.z > 120000000L) {
         this.z = var25;
         this.Z(var9);
         this.F = false;
      }
   }

   public void studio() {
      long var1 = 34264179485276L;
      long var3 = var1 ^ 24962504158091L;
      long var5 = var1 ^ 91512669502616L;
      this.J(var3);

      for (int var7 = 0; var7 < 6; var7++) {
         for (int var8 = 0; var8 < 1024; var8++) {
            float[] var9 = Z[var7 * 32 * 32 + var8];
            float var10 = var9[1];
            float var11 = class_3532.method_15363(-var10 * 3.0F, 0.0F, 1.0F);
            int var12 = class_3532.method_15375(class_3532.method_16439(var11, 54.0F, 10.0F) + class_3532.method_15363(var10, 0.0F, 1.0F) * 40.0F);
            int var13 = var12 + 6;
            int var15 = var12 + 18;
            this.y[var7][var8] = 0xFF000000 | var13 << 16 | var12 << 8 | var15;
         }
      }

      this.S = null;
      this.Z(var5);
   }

   private void J(long var1) {
      MountFactory.Data7.ensureSourceClassInitialized();
      if (this.W == null) {
         GpuDevice var3 = RenderSystem.getDevice();
         this.W = var3.createTexture(ClientServices.label(() -> "Purxiy garage reflections"), 21, TextureFormat.RGBA8, 32, 32, 6, 6);
         this.h = var3.createTextureView(this.W);
         this.w = var3.createSampler(AddressMode.CLAMP_TO_EDGE, AddressMode.CLAMP_TO_EDGE, FilterMode.LINEAR, FilterMode.LINEAR, 1, OptionalDouble.empty());
         this.g = MemoryUtil.memAlloc(4096);

         for (int[] var7 : this.y) {
            Arrays.fill(var7, -8355712);
         }
      }
   }

   private void Z(long var1) {
      long var3 = 72813773793416L;
      MountFactory.Data7.ensureSourceClassInitialized();
      CommandEncoder var5 = RenderSystem.getDevice().createCommandEncoder();

      for (int var6 = 0; var6 < 6; var6++) {
         int[] var7 = this.y[var6];
         int var8 = 32;

         for (int var9 = 0; var9 < 6; var9++) {
            ByteBuffer var10 = this.g.clear();
            long var11 = 83645362034371L;
            if (83645362034371L == 0L) {
               boolean var10000 = false;
            } else {
               int var35 = var11 < 0L ? -1 : 1;
            }

            for (int var14 = 0; var14 < var8 * var8; var14++) {
               int var15 = var7[var14];
               var10.put((byte)(var15 >> 16)).put((byte)(var15 >> 8)).put((byte)var15).put((byte)(var15 >>> 24));
            }

            long var32 = 83645362034371L;
            if (83645362034371L == 0L) {
               boolean var36 = false;
            } else {
               int var37 = var32 < 0L ? -1 : 1;
            }

            var10.flip();
            long var17 = 83645362034372L;
            if (83645362034372L == 0L) {
               boolean var38 = false;
            } else {
               int var39 = var17 < 0L ? -1 : 1;
            }

            var5.writeToTexture(this.W, var10, class_1012.field_4997, var9, var6, 0, 0, var8, var8);
            long var19 = 83645362034371L;
            if (83645362034371L == 0L) {
               boolean var40 = false;
            } else {
               int var41 = var19 < 0L ? -1 : 1;
            }

            if (var9 + 1 < 6) {
               int var21 = var8 >> 1;
               int[] var22 = new int[var21 * var21];
               int var23 = 0;

               while (var23 < var21) {
                  for (int var24 = 0; var24 < var21; var24++) {
                     int var25 = var7[var23 * 2 * var8 + var24 * 2];
                     int var26 = var7[var23 * 2 * var8 + var24 * 2 + 1];
                     int var27 = var7[(var23 * 2 + 1) * var8 + var24 * 2];
                     int var28 = var7[(var23 * 2 + 1) * var8 + var24 * 2 + 1];
                     var22[var23 * var21 + var24] = h(var25, var26, var27, var28, 24, var3) << 24
                        | h(var25, var26, var27, var28, 16, var3) << 16
                        | h(var25, var26, var27, var28, 8, var3) << 8
                        | h(var25, var26, var27, var28, 0, var3);
                     long var29 = 83645362034372L;
                     if (83645362034372L == 0L) {
                        boolean var42 = false;
                     } else {
                        int var43 = var29 < 0L ? -1 : 1;
                     }
                  }

                  var23++;
                  long var33 = 83645362034371L;
                  if (83645362034371L == 0L) {
                     boolean var44 = false;
                  } else {
                     int var45 = var33 < 0L ? -1 : 1;
                  }
               }

               long var34 = 83645362034372L;
               if (83645362034372L == 0L) {
                  boolean var46 = false;
               } else {
                  int var47 = var34 < 0L ? -1 : 1;
               }

               var7 = var22;
               var8 = var21;
            }
         }

         long var31 = 83645362034372L;
         if (83645362034372L == 0L) {
            boolean var48 = false;
         } else {
            int var49 = var31 < 0L ? -1 : 1;
         }
      }
   }

   private static int h(int var0, int var1, int var2, int var3, int var4, long var5) {
      return (var0 >>> var4 & 0xFF) + (var1 >>> var4 & 0xFF) + (var2 >>> var4 & 0xFF) + (var3 >>> var4 & 0xFF) + 2 >> 2;
   }

   public void close() {
      if (this.h != null) {
         this.h.close();
      }

      if (this.W != null) {
         this.W.close();
      }

      if (this.w != null) {
         this.w.close();
      }

      if (this.g != null) {
         MemoryUtil.memFree(this.g);
      }

      this.h = null;
      this.W = null;
      this.w = null;
      this.g = null;
      this.S = null;
      this.x = 0;
      this.b = 0;
      this.c = Double.NaN;
   }

   private static int[] G(int var0, char var1, short var2) {
      int[] var3 = new int[6144];
      int var4 = 0;

      while (var4 < 6144) {
         var3[var4] = var4++;
      }

      Random var8 = new Random(2636928640L);

      for (int var5 = 6143; var5 > 0; var5--) {
         int var6 = var8.nextInt(var5 + 1);
         int var7 = var3[var5];
         var3[var5] = var3[var6];
         var3[var6] = var7;
      }

      return var3;
   }

   private static float[][] g(long var0) {
      short var2 = 8350;
      float[][] var3 = new float[6144][];
      int var4 = 0;

      while (var4 < 6) {
         int var5 = 0;

         while (var5 < 32) {
            long var6 = 119887490188901L;
            if (119887490188901L == 0L) {
               boolean var10000 = false;
            } else {
               int var23 = var6 < 0L ? -1 : 1;
            }

            for (int var8 = 0; var8 < 32; var8++) {
               float var9 = (var8 + 0.5F) / 32.0F * 2.0F - 1.0F;
               float var10 = (var5 + 0.5F) / 32.0F * 2.0F - 1.0F;
               long var11 = 119887490188902L;
               if (119887490188902L == 0L) {
                  boolean var24 = false;
               } else {
                  int var25 = var11 < 0L ? -1 : 1;
               }

               long var14 = 119887490188901L;
               if (119887490188901L == 0L) {
                  boolean var26 = false;
               } else {
                  int var27 = var14 < 0L ? -1 : 1;
               }
               float[] var17 = switch (var4) {
                  case 0 -> new float[]{1.0F, -var10, -var9};
                  case 1 -> new float[]{-1.0F, -var10, var9};
                  case 2 -> new float[]{var9, 1.0F, var10};
                  case 3 -> new float[]{var9, -1.0F, -var10};
                  case 4 -> new float[]{var9, -var10, 1.0F};
                  default -> new float[]{-var9, -var10, -1.0F};
               };
               float var18 = class_3532.method_15355(var17[0] * var17[0] + var17[1] * var17[1] + var17[2] * var17[2]);
               var3[var4 * 32 * 32 + var5 * 32 + var8] = new float[]{var17[0] / var18, var17[1] / var18, var17[2] / var18};
               long var19 = 119887490188901L;
               if (119887490188901L == 0L) {
                  boolean var29 = false;
               } else {
                  int var30 = var19 < 0L ? -1 : 1;
               }
            }

            var5++;
            long var22 = 119887490188901L;
            if (119887490188901L == 0L) {
               boolean var31 = false;
            } else {
               int var32 = var22 < 0L ? -1 : 1;
            }
         }

         var4++;
         long var21 = 119887490188902L;
         if (119887490188902L == 0L) {
            boolean var33 = false;
         } else {
            int var34 = var21 < 0L ? -1 : 1;
         }
      }

      return var3;
   }

static final class TypeL {
      private final class_2339 N = new class_2339();
      private final class_2339 e = new class_2339();
      private double y;
      private static final long[] a = new long[]{
         8185388756738734052L,
         -7346022427902049960L,
         67388439400692444L,
         -5739049640953448246L,
         5173408080945148753L,
         -5889034988914346534L,
         1201671434495560893L,
         -2263223349231131939L,
         -8790572321262606046L
      };
      private static final Integer[] b = new Integer[9];

      double distance() {
         return this.y;
      }

      int trace(class_638 var1, double var2, double var4, double var6, double var8, double var10, double var12, float var14, double var15, int var17) {
         long var18 = 19843227913835L;
         long var20 = var18 ^ 115738186202593L;
         int var22 = class_3532.method_15357(var2);
         int var23 = class_3532.method_15357(var4);
         int var24 = class_3532.method_15357(var6);
         int var25 = var8 > 0.0 ? 1 : -1;
         int var26 = var10 > 0.0 ? 1 : -1;
         int var27 = var12 > 0.0 ? 1 : -1;
         double var28 = var8 == 0.0 ? 1.0E30 : Math.abs(1.0 / var8);
         double var30 = var10 == 0.0 ? 1.0E30 : Math.abs(1.0 / var10);
         double var32 = var12 == 0.0 ? 1.0E30 : Math.abs(1.0 / var12);
         double var34 = var8 == 0.0 ? 1.0E30 : (var8 > 0.0 ? var22 + 1 - var2 : var2 - var22) * var28;
         double var36 = var10 == 0.0 ? 1.0E30 : (var10 > 0.0 ? var23 + 1 - var4 : var4 - var23) * var30;
         double var38 = var12 == 0.0 ? 1.0E30 : (var12 > 0.0 ? var24 + 1 - var6 : var6 - var24) * var32;
         byte var40 = 1;
         double var41 = 0.0;
         int var43 = var1.method_31607();
         int var44 = var1.method_31600();
         this.e.method_10103(var22, var23, var24);
         this.y = 1.0E30;

         for (int var45 = 0; var45 < var17; var45++) {
            if (var34 < var36 && var34 < var38) {
               var22 += var25;
               var41 = var34;
               var34 += var28;
               var40 = 0;
            } else if (var36 < var38) {
               var23 += var26;
               var41 = var36;
               var36 += var30;
               var40 = 1;
            } else {
               var24 += var27;
               var41 = var38;
               var38 += var32;
               var40 = 2;
            }

            if (!(var41 > var15) && var23 >= var43 && var23 < var44) {
               this.N.method_10103(var22, var23, var24);
               if (var1.method_22340(this.N)) {
                  class_2680 var46 = var1.method_8320(this.N);
                  if (!var46.method_26215() && (var46.method_51366() || !var46.method_26227().method_15769())) {
                     this.y = var41;
                     return this.Q(var20, var1, var46, var40, var26, var41, var14, var15);
                  }

                  this.e.method_10103(var22, var23, var24);
                  continue;
               }
            }
            break;
         }

         return -16777216;
      }

      private int Q(long var1, class_638 var3, class_2680 var4, int var5, int var6, double var7, float var9, double var10) {
         MountFactory.Data7.ensureSourceClassInitialized();
         class_3620 var12 = var4.method_26205(var3, this.N);
         int var13 = var12 == class_3620.field_16008 ? 8026746 : var12.field_16011;
         boolean var14 = var4.method_26227().method_15769();
         long var15 = 135581278712714L;
         if (135581278712714L == 0L) {
            boolean var10000 = false;
         } else {
            int var31 = var15 < 0L ? -1 : 1;
         }

         if (!var14) {
            var13 = 3824262;
         }

         long var18 = 135581278712713L;
         if (135581278712713L == 0L) {
            boolean var32 = false;
         } else {
            int var33 = var18 < 0L ? -1 : 1;
         }

         float var20 = var5 == 1 ? (var6 < 0 ? 1.0F : 0.55F) : (var5 == 0 ? 0.78F : 0.68F);
         float var21 = var3.method_8314(class_1944.field_9284, this.e) / 15.0F;
         float var22 = var3.method_8314(class_1944.field_9282, this.e) / 15.0F;
         float var23 = var4.method_26213() / 15.0F;
         float var24 = Math.max(Math.max(var21 * (0.06F + 0.94F * var9), var22 * 0.92F), var23) + 0.03F;
         var24 = class_3532.method_15363(var24 * var20, 0.0F, 1.0F);
         int var25 = Math.round((var13 >> 16 & 0xFF) * var24);
         int var26 = Math.round((var13 >> 8 & 0xFF) * var24);
         int var27 = Math.round((var13 & 0xFF) * var24);
         float var28 = class_3532.method_15363((float)((var7 - var10 * 0.62) / (var10 * 0.38)), 0.0F, 1.0F);
         int var29 = Math.round(var28 * 255.0F);
         return var29 << 24 | var25 << 16 | var26 << 8 | var27;
      }

}
}
