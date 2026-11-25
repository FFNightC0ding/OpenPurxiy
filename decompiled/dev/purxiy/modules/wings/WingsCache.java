package dev.purxiy.modules.wings;

import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.AddressMode;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import dev.purxiy.loader.fabric.ClientServices;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.OptionalDouble;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import net.minecraft.class_1011;
import net.minecraft.class_1044;
import net.minecraft.class_12137;
import net.minecraft.class_2960;
import net.minecraft.class_310;

public final class WingsCache {
   static final int j = 1024;
   private static final int M = 6;
   public static final class_2960 c = ClientServices.id("wings/feathers");
   public static final class_2960 a = ClientServices.id("wings/glow");
   private static CompletableFuture<int[][]> v;
   private static WingsCache.TypeS Z;
   private static WingsCache.TypeS R;
   private static class_12137 t;
   private static boolean K;
   private static final WingsCache.TypeR G = new WingsCache.TypeR(0.07F, 0.34F, 0.86F, 0.58F, 2.2F, 0.1F, 74.0F, 1.55F, 0.07F, 0.16F, 1.0F, 11);
   private static final WingsCache.TypeR d = new WingsCache.TypeR(0.06F, 0.62F, 0.9F, 0.7F, 1.7F, 0.04F, 62.0F, 1.25F, 0.06F, 0.18F, 0.9F, 23);
   private static final WingsCache.TypeR Y = new WingsCache.TypeR(0.06F, 0.78F, 0.9F, 0.62F, 1.8F, -0.03F, 56.0F, 1.15F, 0.05F, 0.2F, 0.8F, 37);
   private static final WingsCache.TypeR V = new WingsCache.TypeR(0.08F, 0.82F, 0.92F, 0.45F, 1.6F, 0.02F, 24.0F, 1.0F, 0.0F, 0.24F, 0.4F, 41);
   private static final float[] f = new float[3];
   private static final ExecutorService p = Executors.newSingleThreadExecutor(var0x -> ClientServices.visualWorker(var0x, "Purxiy wing textures"));
   public static boolean C;
   private static final long[] g = new long[]{
      -2685341928389102393L,
      -524390140103802781L,
      6766207876738161162L,
      5879164412051424900L,
      1800267580185747627L,
      -3766393551206844152L,
      -4679659052652981325L,
      1979562437175176267L,
      6900629477611520845L,
      483476189584566911L,
      -6020006694553044423L,
      3668990360253436694L,
      5409519391472695104L,
      3923621035103565942L,
      -7626539467301558555L,
      5942717583052354900L,
      9085520675567083955L,
      -6106054259412432847L,
      6776690250500476568L,
      -2792326559612263081L,
      3089199439258899245L,
      -7661962819351164074L,
      4208168761572051740L,
      -2712000252214102973L,
      2497110144756937615L,
      519619639575859647L,
      -8716535836010588714L,
      -897460750904582535L,
      -6385045175934513990L,
      7308417211075186953L
   };
   private static final Integer[] h = new Integer[30];

   private WingsCache() {
   }

   public static void prepare() {
      if (v == null && Z == null && !K) {
         v = CompletableFuture.supplyAsync(() -> U((byte)0, 101778377092649L), p);
      }
   }

   public static boolean ready() {
      if (Z != null) {
         return true;
      } else if (K) {
         return false;
      } else if (v == null) {
         v = CompletableFuture.supplyAsync(() -> U((byte)0, 101778377092649L), p);
         return false;
      } else if (!v.isDone()) {
         return false;
      } else {
         try {
            int[][] var0 = v.join();
            GpuDevice var1 = RenderSystem.getDevice();
            if (t == null) {
               int var2 = Math.max(1, Math.min(8, var1.getMaxSupportedAnisotropy()));
               t = var1.createSampler(AddressMode.CLAMP_TO_EDGE, AddressMode.CLAMP_TO_EDGE, FilterMode.LINEAR, FilterMode.LINEAR, var2, OptionalDouble.empty());
            }

            Z = new WingsCache.TypeS("Purxiy wing feathers", var0[0], t);
            R = new WingsCache.TypeS("Purxiy wing glow", var0[1], t);
            class_310 var4 = class_310.method_1551();
            var4.method_1531().method_4616(c, Z);
            var4.method_1531().method_4616(a, R);
            return true;
         } catch (Exception var31) {
            ClientServices.LOGGER.error("Purxiy wings: the feathers could not be made", var31);
            K = true;
            return false;
         }
      }
   }

   static GpuTextureView view() {
      return Z == null ? null : Z.method_71659();
   }

   static GpuTextureView glowView() {
      return R == null ? null : R.method_71659();
   }

   static class_12137 sampler() {
      return t;
   }

   static void dump(Path var0) throws IOException {
      long var1 = 94720450309773L;
      int var3 = (int)((var1 ^ 11772283584676L) >>> 56);
      long var4 = (var1 ^ 11772283584676L) << 8 >>> 8;
      int[][] var6 = U((byte)var3, var4);
      Files.createDirectories(var0);
      String[] var7 = new String[]{"feathers.png", "glow.png"};

      for (int var8 = 0; var8 < 2; var8++) {
         class_1011 var9 = new class_1011(1024, 1024, false);

         try {
            for (int var10 = 0; var10 < 1024; var10++) {
               for (int var11 = 0; var11 < 1024; var11++) {
                  var9.method_61941(var11, var10, var6[var8][var10 * 1024 + var11]);
               }
            }

            var9.method_4314(var0.resolve(var7[var8]));
         } catch (Throwable var13) {
            try {
               var9.close();
            } catch (Throwable var12) {
               var13.addSuppressed(var12);
            }

            throw var13;
         }

         var9.close();
      }
   }

   private static int[][] U(byte var0, long var1) {
      long var3 = 101778377092649L;
      long var5 = var3 ^ 117060682523893L;
      int var7 = (int)((var3 ^ 117060682523893L) >>> 48);
      int var8 = (int)((var3 ^ 117060682523893L) << 16 >>> 32);
      int var9 = (int)(var5 << 48 >>> 48);
      long var10 = var3 ^ 59674829534062L;
      var5 = var3 ^ 92201411009732L;
      int var12 = (int)((var3 ^ 92201411009732L) >>> 48);
      int var13 = (int)((var3 ^ 92201411009732L) << 16 >>> 48);
      int var14 = (int)(var5 << 32 >>> 32);
      var5 = var3 ^ 90898193115367L;
      int var15 = (int)((var3 ^ 90898193115367L) >>> 32);
      int var16 = (int)((var3 ^ 90898193115367L) << 32 >>> 48);
      int var17 = (int)(var5 << 48 >>> 48);
      int[] var18 = new int[1048576];
      int[] var19 = new int[1048576];
      A(var18, var19, (short)var12, WingsCache.TypeF.g, (short)var13, var14, G);
      A(var18, var19, (short)var12, WingsCache.TypeF.H, (short)var13, var14, d);
      A(var18, var19, (short)var12, WingsCache.TypeF.L, (short)var13, var14, Y);
      A(var18, var19, (short)var12, WingsCache.TypeF.i, (short)var13, var14, V);
      S(var18, (char)var7, var8, (short)var9, var19, WingsCache.TypeF.Y);
      O(var15, var18, (char)var16, var19, WingsCache.TypeF.d, (char)var17);
      R(var18, var19, WingsCache.TypeF.Z, var10, true);
      R(var18, var19, WingsCache.TypeF.w, var10, false);
      return new int[][]{var18, var19};
   }

   private static void A(int[] var0, int[] var1, short var2, WingsCache.TypeF var3, short var4, int var5, WingsCache.TypeR var6) {
      long var7 = 16812387005165L;
      long var9 = var7 ^ 47222358249419L;
      long var11 = var7 ^ 56301382058006L;
      int var13 = Math.round(var3.E * 1024.0F);
      int var14 = Math.round(var3.T * 1024.0F);
      int var15 = Math.round((var3.m - var3.E) * 1024.0F);
      int var16 = Math.round((var3.A - var3.T) * 1024.0F);
      WingsUpdater2.ensureSourceClassInitialized();
      float var17 = 0.09F;
      float var18 = 0.02F;
      float var19 = 2.0F / (var15 * (1.0F - 2.0F * var17));
      float var20 = 1.0F / (var16 * (1.0F - 2.0F * var18));

      for (int var21 = 0; var21 < var16; var21++) {
         float var22 = 1.0F - ((var21 + 0.5F) / var16 - var18) / (1.0F - 2.0F * var18);

         for (int var23 = 0; var23 < var15; var23++) {
            float var24 = ((var23 + 0.5F) / var15 - var17) / (1.0F - 2.0F * var17) * 2.0F - 1.0F;
            float[] var25 = J(var24, var22, var6, var11, var19, var20);
            int var26 = (var14 + var21) * 1024 + var13 + var23;
            var0[var26] = M(var25[1], var25[0], var9);
            var1[var26] = M(1.0F, var25[2], var9);
         }
      }
   }

   private static float[] J(float var0, float var1, WingsCache.TypeR var2, long var3, float var5, float var6) {
      long var7 = 110726140016725L;
      long var9 = 88210703559842L;
      short var11 = 20538;
      short var12 = 10150;
      int var13 = (int)(var9 << 48 >>> 48);
      var9 = 98825447759101L;
      short var14 = 23009;
      short var15 = 151;
      int var16 = (int)(var9 << 40 >>> 40);
      WingsUpdater2.ensureSourceClassInitialized();
      float[] var17 = f;
      var17[0] = 0.0F;
      var17[1] = 0.0F;
      var17[2] = 0.0F;
      long var18 = 66515617067770L;
      int var21 = var18 < 0L ? -1 : 1;
      if (!(var1 < -0.01F)) {
         long var21x = 66515617067771L;
         int var24 = var21x < 0L ? -1 : 1;
         if (!(var1 > 1.01F)) {
            float var24x = var2.o * var1 * var1;
            float var25 = var0 - var24x;
            boolean var26 = var25 < 0.0F;
            float var27 = Math.max(0.0F, Math.min(1.0F, (var1 - var2.K) / 0.1F));
            float var28 = var27 * var27 * (3.0F - 2.0F * var27);
            float var29 = 1.0F;
            if (var1 > var2.N) {
               float var30 = Math.min(1.0F, (var1 - var2.N) / (1.0F - var2.N));
               var29 = (float)Math.pow(Math.max(0.0, 1.0 - Math.pow(var30, var2.C)), 1.0F / var2.C);
            }

            float var87 = (var26 ? var2.q : var2.j) * var28 * var29;
            float var31 = var1 * var2.g - Math.abs(var25) * var2.c * var2.g * 0.32F;
            int var32 = (int)Math.floor(var31);
            float var33 = var31 - var32;
            float var34 = 1.0F + 0.025F * (float)Math.sin(var31 * Math.PI * 2.0);
            short var35 = 7088;
            long var36 = 66515617067771L;
            int var36x = var36 < 0L ? -1 : 1;
            float var39x = var34 + 0.03F * (o(var32 * 7 + (var26 ? 1 : 0), var7, var2.h) - 0.5F);
            float var40 = var87 * var39x;
            float var41 = Math.abs(var25);
            long var42 = 66515617067771L;
            int var41x = var42 < 0L ? -1 : 1;
            if (var40 <= 0.0F) {
               var34 = 0.0F;
            } else {
               float var44 = (var40 - var41) / (var5 * 1.6F) + 0.5F;
               var34 = K(var11, var44, (char)var12, var13);
            }

            float var45x = 0.0F;
            long var46 = 66515617067770L;
            int var49 = var46 < 0L ? -1 : 1;
            var49 = var32 * 13 + (var26 ? 5 : 0);
            long var50 = 66515617067771L;
            int var53 = var50 < 0L ? -1 : 1;
            var34 = o(var49, var7, var2.h + 3);
            long var53x = 66515617067770L;
            int var49x = var53x < 0L ? -1 : 1;
            if (var34 < var2.i) {
               long var55 = 66515617067771L;
               int var94 = var55 < 0L ? -1 : 1;
               long var57 = 66515617067771L;
               int var53x = var57 < 0L ? -1 : 1;
               if (var1 > var2.K + 0.12F) {
                  long var59 = 66515617067770L;
                  int var55x = var59 < 0L ? -1 : 1;
                  if (var1 < 0.92F) {
                     float var61x = K(var11, (var41 / Math.max(var40, 1.0E-4F) - 0.42F) / 0.3F, (char)var12, var13);
                     var45x = K(var11, 1.0F - Math.min(var33, 1.0F - var33) / 0.16F, (char)var12, var13) * var61x;
                  }
               }
            }

            float var93x = var34 * (1.0F - var45x);
            float var56 = var2.K + var2.T;
            long var94 = 66515617067771L;
            int var99 = var94 < 0L ? -1 : 1;
            if (var1 < var56) {
               float var95 = K(var11, (var1 - var2.K) / var2.T, (char)var12, var13);
               var34 = 0.4F + 0.6F * var95;
               long var60 = 66515617067770L;
               int var56x = var60 < 0L ? -1 : 1;
               float var62 = var34 + 0.3F * (o(var32 * 3 + (var26 ? 1 : 0), var7, var2.h + 17) - 0.5F) * (1.0F - var95);
               var93x *= K(var11, var62, (char)var12, var13);
            }

            float var96 = 0.045F * (1.0F - 0.75F * var1) + var5 * 0.5F;
            long var97 = 66515617067771L;
            int var105 = var97 < 0L ? -1 : 1;
            float var98 = var1 <= 0.985F ? K(var11, (var96 - var41) / (var5 * 1.2F) + 0.5F, (char)var12, var13) : 0.0F;
            long var63 = 66515617067771L;
            int var65 = var63 < 0L ? -1 : 1;
            if (var1 < var2.K) {
               var98 *= K(var11, var1 / var2.K * 1.5F, (char)var12, var13);
            }

            float var65x = Math.max(var93x, var98);
            long var66 = 66515617067770L;
            int var68 = var66 < 0L ? -1 : 1;
            if (var65x <= 0.0F) {
               return var17;
            }

            long var68x = 66515617067771L;
            int var70 = var68x < 0L ? -1 : 1;
            float var70x = var40 > 0.0F ? K(var11, var41 / var40, (char)var12, var13) : 1.0F;
            float var71 = 0.5F + 0.5F * (float)Math.cos(var33 * Math.PI * 2.0);
            var34 = 0.93F + 0.07F * (float)Math.sqrt(1.0F - var70x);
            float var72 = 0.08F * var71 * (0.3F + 0.7F * var70x);
            long var73 = 66515617067771L;
            int var76 = var73 < 0L ? -1 : 1;
            float var76x = var34
               - var2.y * (var72 + 0.04F * (o(var32, var7, var2.h + 9) < 0.12F ? 1 : 0))
               - (0.05F + 0.06F * var2.y) * l((var70x - 0.72F) / 0.28F, var14, (byte)var15, var16);
            float var77 = K(var11, 1.0F - Math.abs(var41 - var96 * 2.2F) / (var96 * 1.4F), (char)var12, var13);
            float var111 = var76x - 0.06F * var77 * (1.0F - var1);
            float var112 = var111 * (1.0F - var98) + 1.0F * var98;
            float var113 = var112 - 0.05F * (1.0F - var70x) * K(var11, (0.4F - var1) / 0.4F, (char)var12, var13);
            var17[0] = K(var11, var65x, (char)var12, var13);
            var17[1] = K(var11, var113, (char)var12, var13);
            float var78 = l((var70x - 0.42F) / 0.5F, var14, (byte)var15, var16) * var93x;
            float var79 = K(var11, (var1 - 0.7F) / 0.3F, (char)var12, var13) * var93x * 0.7F;
            float var80 = var98 * 0.75F * (1.0F - 0.35F * var1);
            long var81 = 66515617067771L;
            int var76x = var81 < 0L ? -1 : 1;
            float var83x = Math.max(Math.max(var78 * 0.9F, var79), var80);
            long var84 = 66515617067770L;
            int var79x = var84 < 0L ? -1 : 1;
            var17[2] = K(var11, var83x * (var1 < var56 ? K(var11, (var1 - var2.K) / var2.T, (char)var12, var13) : 1.0F), (char)var12, var13);
            return var17;
         }
      }

      return var17;
   }

   private static void S(int[] var0, char var1, int var2, short var3, int[] var4, WingsCache.TypeF var5) {
      long var6 = 60363356048092L;
      long var8 = var6 ^ 21297935185914L;
      long var10 = var6 ^ 113096551278206L;
      int var12 = (int)((var6 ^ 113096551278206L) >>> 32);
      int var13 = (int)((var6 ^ 113096551278206L) << 32 >>> 48);
      int var14 = (int)(var10 << 48 >>> 48);
      var10 = var6 ^ 122079744644641L;
      int var15 = (int)((var6 ^ 122079744644641L) >>> 32);
      int var16 = (int)((var6 ^ 122079744644641L) << 32 >>> 56);
      int var17 = (int)(var10 << 40 >>> 40);
      int var18 = Math.round(var5.E * 1024.0F);
      int var19 = Math.round(var5.T * 1024.0F);
      int var20 = Math.round((var5.m - var5.E) * 1024.0F);
      WingsUpdater2.ensureSourceClassInitialized();
      int var21 = Math.round((var5.A - var5.T) * 1024.0F);

      for (int var22 = 0; var22 < var21; var22++) {
         float var23 = 1.0F - (var22 + 0.5F) / var21;

         for (int var24 = 0; var24 < var20; var24++) {
            float var25 = (var24 + 0.5F) / var20 * 2.0F - 1.0F;
            float var26 = (var23 - 0.52F) / 0.44F;
            float var27 = var25 / (0.8F * (0.7F + 0.3F * l(var23 * 2.2F, var15, (byte)var16, var17)));
            float var28 = (float)Math.hypot(var27, var26);
            float var29 = (float)Math.atan2(var26, var27);
            float var30 = 0.05F * (float)Math.sin(var29 * 9.0F + 0.7F) + 0.03F * (float)Math.sin(var29 * 17.0F + 2.1F);
            float var31 = l((1.0F + var30 - var28) / 0.28F, var15, (byte)var16, var17);
            float var32 = 0.5F + 0.5F * (float)Math.cos(var29 * 46.0F);
            float var33 = K(var12, (0.03F - Math.abs(var25)) / 0.02F, (char)var13, var14)
               * K(var12, (var23 - 0.12F) / 0.15F, (char)var13, var14)
               * K(var12, (0.75F - var23) / 0.1F, (char)var13, var14);
            float var34 = Math.max(var31 * (0.9F - 0.12F * var32 * K(var12, var28, (char)var13, var14)), var33 * 0.6F);
            float var35 = 0.95F + 0.05F * (1.0F - var28) - 0.05F * var32 * var28 - 0.07F * l((var28 - 0.7F) / 0.3F, var15, (byte)var16, var17);
            var35 = Math.max(var35, var33);
            int var36 = (var19 + var22) * 1024 + var18 + var24;
            var0[var36] = M(K(var12, var35, (char)var13, var14), K(var12, var34, (char)var13, var14), var8);
            float var37 = (var28 - 0.5F) / 0.45F;
            byte var38 = (byte)var16;
            float var39 = l(var37, var15, var38, var17) * var31 * 0.75F + var33 * 0.3F;
            var4[var36] = M(1.0F, K(var12, var39, (char)var13, var14), var8);
         }
      }
   }

   private static void O(int var0, int[] var1, char var2, int[] var3, WingsCache.TypeF var4, char var5) {
      long var6 = 15645534176974L;
      long var8 = var6 ^ 47839446612968L;
      long var10 = var6 ^ 103357937576556L;
      int var12 = (int)((var6 ^ 103357937576556L) >>> 32);
      int var13 = (int)((var6 ^ 103357937576556L) << 32 >>> 48);
      int var14 = (int)(var10 << 48 >>> 48);
      int var15 = Math.round(var4.E * 1024.0F);
      int var16 = Math.round(var4.T * 1024.0F);
      WingsUpdater2.ensureSourceClassInitialized();
      int var17 = Math.round((var4.m - var4.E) * 1024.0F);
      int var18 = Math.round((var4.A - var4.T) * 1024.0F);

      for (int var19 = 0; var19 < var18; var19++) {
         float var20 = (var19 + 0.5F) / var18 * 2.0F - 1.0F;

         for (int var21 = 0; var21 < var17; var21++) {
            float var22 = (var21 + 0.5F) / var17 * 2.0F - 1.0F;
            float var23 = (float)Math.hypot(var22, var20);
            float var24 = (float)Math.exp(-var23 * var23 / 0.018F);
            float var25 = (float)Math.exp(-var23 * var23 / 0.12F) * 0.35F;
            float var26 = (float)(
               Math.exp(-var22 * var22 / 0.0012F) * Math.exp(-var20 * var20 / 0.25F) + Math.exp(-var20 * var20 / 0.0012F) * Math.exp(-var22 * var22 / 0.25F)
            );
            float var27 = var24 + var25 + var26 * 0.8F;
            float var28 = K(var12, var27 * K(var12, (1.0F - var23) / 0.15F, (char)var13, var14), (char)var13, var14);
            int var29 = (var16 + var19) * 1024 + var15 + var21;
            var1[var29] = M(1.0F, var28, var8);
            var3[var29] = M(1.0F, var28, var8);
         }
      }
   }

   private static void R(int[] var0, int[] var1, WingsCache.TypeF var2, long var3, boolean var5) {
      long var6 = 3251713485226L;
      byte var8 = 0;
      int var9 = 49617210;
      int var10 = (int)(var6 << 48 >>> 48);
      long var11 = 41475420814630L;
      var6 = 88210703559842L;
      short var13 = 20538;
      short var14 = 10150;
      int var15 = (int)(var6 << 48 >>> 48);
      int var16 = Math.round(var2.E * 1024.0F);
      WingsUpdater2.ensureSourceClassInitialized();
      int var17 = Math.round(var2.T * 1024.0F);
      int var18 = Math.round((var2.m - var2.E) * 1024.0F);
      int var19 = Math.round((var2.A - var2.T) * 1024.0F);
      float var20 = 1.0F / var18;

      for (int var21 = 0; var21 < var19; var21++) {
         float var22 = 1.0F - (var21 + 0.5F) / var19;

         for (int var23 = 0; var23 < var18; var23++) {
            float var24 = (var23 + 0.5F) / var18;
            float var25 = var24 - 0.04F;
            float var26 = var22 - 0.04F;
            float var27 = (float)Math.hypot(var25, var26);
            float var28 = (float)Math.atan2(var26, var25);
            float var29 = (var28 - 0.02F) / 1.5307964F;
            long var30 = 117471667756358L;
            int var33 = var30 < 0L ? -1 : 1;
            float var33x = K(var13, var29, (char)var14, var15);
            float var66;
            if (var5) {
               var66 = 0.93F * (float)Math.pow(Math.sin(Math.PI * Math.pow(var33x, 0.8F)), 0.55F) + 0.02F;
               long var35 = 117471667756359L;
               int var37 = var35 < 0L ? -1 : 1;
               var66 *= 0.78F + 0.22F * (float)Math.pow(var33x, 0.4F);
            } else {
               var66 = 0.86F * (float)Math.pow(Math.sin(Math.PI * var33x), 0.45F) * (1.05F - 0.25F * var33x);
            }

            var66 += 0.012F * (float)Math.sin(var33x * 34.0F);
            float var68 = (var66 - var27) / Math.max(var66, 0.01F);
            float var36 = K(var13, (var66 - var27) / (var20 * 2.5F) + 0.5F, (char)var14, var15);
            float var37;
            int var38 = (var37 = var36 - 0.0F) == 0.0F ? 0 : (var37 < 0.0F ? -1 : 1);
            long var39 = 117471667756359L;
            int var42 = var39 < 0L ? -1 : 1;
            if (var38 > 0) {
               float var42x = 0.0F;
               int var43 = var5 ? 7 : 5;

               for (int var44 = 0; var44 < var43; var44++) {
                  float var45 = 0.12F + (var44 + 0.5F) / var43 * 1.3307964F;
                  float var46 = var45 + 0.06F * (float)Math.sin(var27 * 6.0F + var44);
                  float var47 = Math.abs(f(var8, var9, var28 - var46, (char)var10)) * var27;
                  long var48 = 117471667756358L;
                  int var48x = var48 < 0L ? -1 : 1;
                  long var51x = 117471667756359L;
                  int var74 = var51x < 0L ? -1 : 1;
                  long var54 = 117471667756359L;
                  int var55 = var54 < 0L ? -1 : 1;
                  var42x = Math.max(var42x, K(var13, 1.0F - var47 / (0.0045F + 0.004F * (1.0F - var27)), (char)var14, var15));
                  if (var27 > 0.45F) {
                     long var57 = 117471667756358L;
                     int var54x = var57 < 0L ? -1 : 1;
                     float var60x = var46 + 0.09F * (var44 % 2 == 0 ? 1 : -1) * K(var13, (var27 - 0.45F) * 3.0F, (char)var14, var15);
                     float var61 = Math.abs(f(var8, var9, var28 - var60x, (char)var10)) * var27;
                     var42x = Math.max(var42x, 0.8F * K(var13, 1.0F - var61 / 0.004F, (char)var14, var15));
                  }
               }

               float var69 = K(var13, 1.0F - var68 / 0.07F, (char)var14, var15);
               float var70 = 0.0F;
               float var71 = var68 - 0.12F;
               var29 = Math.abs(var71);
               long var72 = 117471667756358L;
               int var50 = var72 < 0L ? -1 : 1;
               long var50x = 117471667756358L;
               int var73 = var50x < 0L ? -1 : 1;
               if (var29 < 0.05F) {
                  float var52 = var33x * (var5 ? 11 : 8);
                  float var53 = var52 - (float)Math.floor(var52) - 0.5F;
                  var70 = K(var13, 1.0F - (var53 * var53 * 16.0F + var71 * var71 * 900.0F), (char)var14, var15);
               }

               float var73x = 0.55F
                  + 0.25F * K(var13, 1.0F - var27, (char)var14, var15)
                  + 0.08F * (float)Math.sin(var28 * 40.0F + var27 * 20.0F) * (1.0F - var69);
               float var74 = K(var13, Math.max(var73x * var36, Math.max(var42x, var69) * var36), (char)var14, var15);
               float var75 = K(var13, 0.92F - 0.35F * var69 + 0.1F * var42x, (char)var14, var15);
               int var55 = (var17 + var21) * 1024 + var16 + var23;
               var0[var55] = M(var75, var74, var11);
               float var56 = K(var13, Math.max(var42x, Math.max(var70, var69 * 0.9F)) * var36, (char)var14, var15);
               var1[var55] = M(1.0F, var56, var11);
            }
         }

         long var64 = 117471667756359L;
         int var601 = var64 < 0L ? -1 : 1;
      }

      long var63 = 117471667756358L;
      int var59 = var63 < 0L ? -1 : 1;
   }

   private static int[] Y(char var0, int var1, short var2, int[] var3, int var4) {
      WingsUpdater2.ensureSourceClassInitialized();
      int var5 = var4 / 2;
      int[] var6 = new int[var5 * var5];

      for (int var7 = 0; var7 < var5; var7++) {
         for (int var8 = 0; var8 < var5; var8++) {
            float var9 = 0.0F;
            float var10 = 0.0F;
            float var11 = 0.0F;
            float var12 = 0.0F;

            for (int var13 = 0; var13 < 4; var13++) {
               int var14 = var3[(var7 * 2 + (var13 >> 1)) * var4 + var8 * 2 + (var13 & 1)];
               float var15 = (var14 >>> 24) / 255.0F;
               var9 += var15;
               var10 += (var14 >> 16 & 0xFF) * var15;
               var11 += (var14 >> 8 & 0xFF) * var15;
               var12 += (var14 & 0xFF) * var15;
            }

            int var16 = 0;
            float var18;
            var16 = (var18 = var9 - 0.0F) == 0.0F ? 0 : (var18 < 0.0F ? -1 : 1);
            if (var16 <= 0) {
               var6[var7 * var5 + var8] = 16777215;
            } else {
               var6[var7 * var5 + var8] = Math.round(var9 / 4.0F * 255.0F) << 24
                  | Math.round(var10 / var9) << 16
                  | Math.round(var11 / var9) << 8
                  | Math.round(var12 / var9);
            }
         }
      }

      return var6;
   }

   private static int M(float var0, float var1, long var2) {
      long var4 = 88210703559842L;
      short var6 = 20538;
      short var7 = 10150;
      int var8 = (int)(var4 << 48 >>> 48);
      WingsUpdater2.ensureSourceClassInitialized();
      int var9 = Math.round(K(var6, var0, (char)var7, var8) * 255.0F);
      int var10 = Math.round(K(var6, var1, (char)var7, var8) * 255.0F);
      return var10 == 0 ? 16777215 : var10 << 24 | var9 << 16 | var9 << 8 | var9;
   }

   private static float K(int var0, float var1, char var2, int var3) {
      WingsUpdater2.ensureSourceClassInitialized();
      return var1 < 0.0F ? 0.0F : Math.min(var1, 1.0F);
   }

   private static float l(float var0, int var1, byte var2, int var3) {
      long var4 = 98825447759101L;
      long var6 = var4 ^ 10839156584543L;
      int var8 = (int)((var4 ^ 10839156584543L) >>> 32);
      int var9 = (int)((var4 ^ 10839156584543L) << 32 >>> 48);
      int var10 = (int)(var6 << 48 >>> 48);
      float var11 = K(var8, var0, (char)var9, var10);
      return var11 * var11 * (3.0F - 2.0F * var11);
   }

   private static float f(short var0, int var1, float var2, char var3) {
      WingsUpdater2.ensureSourceClassInitialized();
      float var4 = (float) (Math.PI * 2);
      var2 %= var4;
      return var2 > Math.PI ? var2 - var4 : (var2 < -Math.PI ? var2 + var4 : var2);
   }

   private static float o(int var0, long var1, int var3) {
      int var4 = var0 * 668265261 + var3 * 374761393;
      var4 ^= var4 >>> 15;
      var4 *= 739982445;
      var4 ^= var4 >>> 12;
      var4 *= 695872825;
      var4 ^= var4 >>> 15;
      return (var4 & 65535) / 65535.0F;
   }

   static void release() {
      p.shutdown();

      try {
         if (!p.awaitTermination(5L, TimeUnit.SECONDS)) {
            p.shutdownNow();
            throw new IllegalStateException("Wing texture worker did not stop");
         }
      } catch (InterruptedException var1) {
         p.shutdownNow();
         Thread.currentThread().interrupt();
         throw new IllegalStateException("Wing texture worker interrupted");
      }

      v = null;
      if (Z != null) {
         class_310.method_1551().method_1531().method_4615(c);
         Z = null;
      }

      if (R != null) {
         class_310.method_1551().method_1531().method_4615(a);
         R = null;
      }

      if (t != null) {
         t.close();
         t = null;
      }
   }

   static enum TypeF {
      g(0, 0, 128, 512),
      H(128, 0, 128, 512),
      L(256, 0, 128, 512),
      i(384, 0, 128, 256),
      Y(384, 256, 128, 128),
      d(384, 384, 128, 128),
      Z(512, 0, 512, 512),
      w(512, 512, 512, 512);

      final float E;
      final float T;
      final float m;
      final float A;
      private static final long[] a = new long[]{-4600244879942229547L, -88119338054403611L, 7472798226546372849L};

      private TypeF(int nullxx, int nullxxx, int nullxxxx, int nullxxxxx) {
         this.E = nullxx / 1024.0F;
         this.T = nullxxx / 1024.0F;
         this.m = (nullxx + nullxxxx) / 1024.0F;
         this.A = (nullxxx + nullxxxxx) / 1024.0F;
      }

      float u(float var1) {
         return this.E + (this.m - this.E) * var1;
      }

      float v(float var1) {
         return this.A + (this.T - this.A) * var1;
      }
   }

   private record TypeR(float K, float q, float j, float N, float C, float o, float g, float c, float i, float T, float y, int h) {
      public float calamus() {
         return this.K;
      }

      public float outer() {
         return this.q;
      }

      public float inner() {
         return this.j;
      }

      public float tipStart() {
         return this.N;
      }

      public float tipPower() {
         return this.C;
      }

      public float bend() {
         return this.o;
      }

      public float barbs() {
         return this.g;
      }

      public float slant() {
         return this.c;
      }

      public float splits() {
         return this.i;
      }

      public float fluff() {
         return this.T;
      }

      public float detail() {
         return this.y;
      }

      public int seed() {
         return this.h;
      }

      public float floatState50() {
         return this.K;
      }

      public float floatState48() {
         return this.q;
      }

      public float floatState38() {
         return this.j;
      }

      public float floatState51() {
         return this.N;
      }

      public float floatState16() {
         return this.C;
      }

      public float floatState11() {
         return this.o;
      }

      public float floatState41() {
         return this.g;
      }

      public float floatState25() {
         return this.c;
      }

      public float floatState12() {
         return this.i;
      }

      public float floatState29() {
         return this.T;
      }

      public float floatState2() {
         return this.y;
      }

      public int integerState30() {
         return this.h;
      }
   }

   private static final class TypeS extends class_1044 {
      TypeS(String var1, int[] var2, class_12137 var3) {
         long var4 = 81486832995785L;
         long var6 = var4 ^ 105515335063918L;
         int var8 = (int)((var4 ^ 105515335063918L) >>> 48);
         int var9 = (int)((var4 ^ 105515335063918L) << 16 >>> 32);
         int var10 = (int)(var6 << 48 >>> 48);
         GpuDevice var11 = RenderSystem.getDevice();
         this.field_56974 = var11.createTexture(() -> var1, 5, TextureFormat.RGBA8, 1024, 1024, 1, 6);
         this.field_60597 = var11.createTextureView(this.field_56974);
         this.field_63613 = var3;
         CommandEncoder var12 = var11.createCommandEncoder();
         short var13 = 1024;
         int[] var14 = var2;

         for (int var15 = 0; var15 < 6; var15++) {
            class_1011 var16 = new class_1011(var13, var13, false);

            try {
               for (int var17 = 0; var17 < var13; var17++) {
                  for (int var18 = 0; var18 < var13; var18++) {
                     var16.method_61941(var18, var17, var14[var17 * var13 + var18]);
                  }
               }

               var12.writeToTexture(this.field_56974, var16, var15, 0, 0, 0, var13, var13, 0, 0);
            } catch (Throwable var201) {
               try {
                  var16.close();
               } catch (Throwable var191) {
                  var201.addSuppressed(var191);
               }

               throw var201;
            }

            var16.close();
            if (var15 + 1 < 6) {
               var14 = WingsCache.Y((char)var8, var9, (short)var10, var14, var13);
               var13 = (short)(var13 / 2);
            }
         }
      }
   }
}
