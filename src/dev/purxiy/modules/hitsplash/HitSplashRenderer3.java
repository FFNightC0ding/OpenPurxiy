// Sourced By DevFFNightmare
package dev.purxiy.modules.hitsplash;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.concurrent.CompletableFuture;
import net.minecraft.class_10789;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_1011.class_1012;
import org.joml.Vector4f;

import org.lwjgl.system.MemoryUtil;

final class HitSplashRenderer3 {
   private static final int z = 12;
   private static final int U = 10176;
   private static final float V = 2.6F;
   private static final float n = 0.45F;
   private static final float J = 0.05F;
   private static final float t = 0.36F;
   private static final RenderPipeline j = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/hit_splash"))
      .withVertexShader(ClientServices.id("core/hit_splash"))
      .withFragmentShader(ClientServices.id("core/hit_splash"))
      .withUniform("Fog", class_10789.field_60031)
      .withUniform("PurxiyHitSplash", class_10789.field_60031)
      .withSampler("DepthSampler")
      .withSampler("ShapeSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static final RenderPipeline e = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/hit_splash_drops"))
      .withVertexShader(ClientServices.id("core/hit_splash_drops"))
      .withFragmentShader(ClientServices.id("core/hit_splash_drops"))
      .withUniform("Fog", class_10789.field_60031)
      .withUniform("PurxiyHitSplash", class_10789.field_60031)
      .withSampler("DepthSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static GpuBuffer L;
   private static ByteBuffer D;
   
   private static CompletableFuture<byte[][]> m;
   
   private static GpuTexture M;
   
   private static GpuTextureView p;
   private static boolean g;
   private static final int[] P = new int[128];
   private static final int[] k = new int[128];
   private static final float[] Z = new float[768];
   private static final float[] N = new float[192];
   private static int S = 0;
   private static final float[] I = new float[768];
   private static final int[] i = new int[128];
   private static final int[] a = new int[128];
   private static final Vector4f w = new Vector4f();
   private static boolean F;
   
   private static GpuQuery u;
   private static double q;
   private static int X = 0;

   private static final String[] c = new String[23];
   private static final long[] d = new long[]{
      4709604590601640476L,
      4216600357113210408L,
      6256565313090996430L,
      7741630881557880389L,
      -6533490760668217040L,
      8521790492722783665L,
      -3131760713482834580L,
      2121448165564704558L,
      -8319359368938365503L,
      -7146981562268213458L,
      2614853799838160388L,
      977731879432723314L,
      1788352612740409423L,
      -7578701524117348630L,
      3260632277807493722L,
      -5820913875627159084L,
      -5408578766813424303L,
      -3740099591033751233L
   };
   private static final Integer[] f = new Integer[18];

   private HitSplashRenderer3() {
   }

   static void prepare() {
      if (m == null) {
         m = CompletableFuture.supplyAsync(HitSplashBuilder::bake);
      }
   }

   static void setTiming(boolean var0) {
      F = var0;
      q = 0.0;
      X = 0;
   }

   private static boolean t(long var0) {
      HitSplashRenderer2.ensureSourceClassInitialized();
      if (p != null) {
         return true;
      } else {
         prepare();
         if (!g && m.isDone()) {
            byte[][] var2;
            try {
               var2 = m.join();
            } catch (RuntimeException var15) {
               g = true;
               ClientServices.LOGGER.error("Purxiy hit splash: baking the shapes failed", var15);
               return false;
            }

            GpuDevice var3 = RenderSystem.getDevice();
            M = var3.createTexture(() -> "Purxiy hit splash shapes", 5, TextureFormat.RGBA8, 1024, 512, 1, 5);
            CommandEncoder var4 = var3.createCommandEncoder();

            for (int var5 = 0; var5 < var2.length; var5++) {
               int var6 = var2[var5].length;
               long var7 = 75537280133866L;
               if (75537280133866L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var18 = var7 < 0L ? -1 : 1;
               }

               ByteBuffer var10 = MemoryUtil.memAlloc(var6);
               boolean var11 = false;

               try {
                  var11 = true;
                  var10.put(var2[var5]).flip();
                  var4.writeToTexture(M, var10, class_1012.field_4997, var5, 0, 0, 0, 1024 >> var5, 512 >> var5);
                  var11 = false;
               } finally {
                  if (var11) {
                     MemoryUtil.memFree(var10);
                  }
               }

               MemoryUtil.memFree(var10);
            }

            long var17 = 75537280133866L;
            if (75537280133866L == 0L) {
               boolean var19 = false;
            } else {
               int var20 = var17 < 0L ? -1 : 1;
            }

            p = var3.createTextureView(M);
            return true;
         } else {
            return false;
         }
      }
   }

   static void draw(class_310 var0) {
      long var1 = 63146311023518L;
      long var3 = (var1 ^ 86720620573282L) >>> 32;
      int var5 = (int)((var1 ^ 86720620573282L) << 32 >>> 32);
      long var6 = var1 ^ 9749769476688L;
      long var8 = var1 ^ 29545005376795L;
      int var10 = (int)((var1 ^ 29545005376795L) >>> 32);
      int var11 = (int)((var1 ^ 29545005376795L) << 32 >>> 48);
      int var12 = (int)(var8 << 48 >>> 48);
      var8 = var1 ^ 56525340548415L;
      int var13 = (int)((var1 ^ 56525340548415L) >>> 32);
      int var14 = (int)((var1 ^ 56525340548415L) << 32 >>> 48);
      int var15 = (int)(var8 << 48 >>> 48);
      var8 = var1 ^ 103045706209351L;
      long var16 = var1 ^ 62317049933765L;
      int var18 = (int)((var1 ^ 1203006764054L) >>> 48);
      long var19 = (var1 ^ 1203006764054L) << 16 >>> 16;
      long var21 = var1 ^ 138388171657589L;
      class_276 var23 = var0.method_1522();
      GpuTextureView var24 = var23.method_71639();
      GpuTextureView var25 = var23.method_71640();
      if (var24 != null && var25 != null && HitSplashRenderer.J != null) {
         GpuDevice var26 = RenderSystem.getDevice();
         boolean var27 = !HitSplashRenderer.u.isEmpty() && t(var21) && var26.precompilePipeline(j).isValid();
         boolean var28 = !HitSplashRenderer.T.isEmpty() && var26.precompilePipeline(e).isValid();
         S = 0;
         if (var27) {
            b(var13, (char)var14, var15);
         }

         if (S != 0 || var28) {
            HitSplashModule var29 = HitSplashRenderer.k;
            HitSplashLoader var30 = HitSplashLoader.resolve(var29.Z.get(), var29.p.argb());
            if (L == null) {
               L = var26.createBuffer(ClientServices.label(() -> "Purxiy hit splash"), 136, 10176L);
               D = MemoryUtil.memAlloc(10176);
            }

            Std140Builder var31 = Std140Builder.intoBuffer(D.clear())
               .putMat4f(HitSplashRenderer.B)
               .putMat4f(HitSplashRenderer.D)
               .putVec4(var23.field_1482, var23.field_1481, HitSplashRenderer.c, var29.x.floatValue())
               .putVec4(k(var6, var30.F, 16), k(var6, var30.F, 8), k(var6, var30.F, 0), (float)(HitSplashRenderer.L % 3600.0))
               .putVec4(k(var6, var30.k, 16), k(var6, var30.k, 8), k(var6, var30.k, 0), 0.0F)
               .putVec4(k(var6, var30.D, 16), k(var6, var30.D, 8), k(var6, var30.D, 0), 0.0F);

            for (int var32 = 0; var32 < 16; var32++) {
               if (var32 < S) {
                  int var33 = var32 * 12;
                  var31.putVec4(N[var33], N[var33 + 1], N[var33 + 2], N[var33 + 6])
                     .putVec4(N[var33 + 3], N[var33 + 4], N[var33 + 5], N[var33 + 7])
                     .putVec4(N[var33 + 8], N[var33 + 9], N[var33 + 10], N[var33 + 11]);
               } else {
                  var31.putVec4(0.0F, 0.0F, 0.0F, 0.0F).putVec4(0.0F, 0.0F, 0.0F, 0.0F).putVec4(0.0F, 0.0F, 0.0F, 0.0F);
               }
            }

            X(var31, var16, var27 ? HitSplashRenderer.u.size() : 0, var29.J.floatValue());
            int var47 = var28 ? Math.min(HitSplashRenderer.T.size(), 96) : 0;
            U(var10, var11, var31, var47, (char)var12);
            CommandEncoder var48 = var26.createCommandEncoder();
            var48.writeToBuffer(L.slice(), var31.get());
            GpuQuery var34 = F ? o(var3, var48, var5) : null;

            try (RenderPass var35 = var48.createRenderPass(() -> "Purxiy hit splash", var24, OptionalInt.empty())) {
               if (S > 0) {
                  var35.setPipeline(j);
                  e(var35, (char)var18, var25, var19);
                  var35.bindTexture("ShapeSampler", p, RenderSystem.getSamplerCache().method_76520(FilterMode.LINEAR, true));
                  var35.draw(0, S * 6);
                  HitSplashRenderer.metric("drawn");
               }

               if (var47 > 0) {
                  var35.setPipeline(e);
                  e(var35, (char)var18, var25, var19);
                  var35.draw(0, var47 * 6);
                  HitSplashRenderer.metric("drawn");
               }
            } finally {
               if (var34 != null) {
                  var48.timerQueryEnd(var34);
                  u = var34;
               }
            }
         }
      }
   }

   private static void e(RenderPass var0, char var1, GpuTextureView var2, long var3) {
      RenderSystem.bindDefaultUniforms(var0);
      var0.setUniform("Fog", HitSplashRenderer.J);
      var0.setUniform("PurxiyHitSplash", L);
      var0.bindTexture("DepthSampler", var2, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
   }

   private static void b(int var0, char var1, int var2) {
      long var3 = 11023867700897L;
      long var5 = var3 ^ 4171035336235L;
      long var7 = var3 ^ 114123639255204L;
      long var9 = var3 ^ 113094648552240L;
      long var11 = var3 ^ 43875803082745L;
      long var13 = var3 ^ 6178568234422L;
      long var15 = var3 ^ 128303745528736L;
      HitSplashRenderer2.ensureSourceClassInitialized();
      int var17 = Math.min(HitSplashRenderer.u.size(), 128);

      for (int var18 = 0; var18 < var17; k[var18] = var18++) {
         HitSplashRenderer.TypeW var19 = HitSplashRenderer.u.get(var18);
         float var20 = 0.3F + 0.7F * var19.t;
         float var21 = Math.max(2.4F * var19.t + 0.04F, 2.6F * var20);
         float var22 = (float)(var19.M - HitSplashRenderer.d);
         float var23 = (float)(var19.z - HitSplashRenderer.N);
         float var24 = (float)(var19.o - HitSplashRenderer.v);
         Z[var18 * 6] = var22 - var21;
         int var25 = var18 * 6;
         Z[var25 + 1] = var23 - 0.45F;
         Z[var18 * 6 + 2] = var24 - var21;
         Z[var18 * 6 + 3] = var22 + var21;
         Z[var18 * 6 + 4] = var23 + 0.45F;
         Z[var18 * 6 + 5] = var24 + var21;
      }

      int var32 = 2566;
      int var36 = 0;

      while (var36 < var17) {
         for (int var37 = var36 + 1; var37 < var17; var37++) {
            char var39 = '놷';
            var39 = '놷';
            boolean var44 = L(var36, var15, var37);
            if (var44) {
               b(var36, var37, var5);
            }
         }

         var36++;
         var32 = (short)2566;
      }

      var32 = (short)2566;
      int var38 = 0;
      Arrays.fill(i, 0, var17, -1);

      for (int var41 = 0; var41 < var17; var41++) {
         var32 = S(var11, var41);
         if (i[var32] < 0) {
            i[var32] = var38;
            System.arraycopy(Z, var41 * 6, I, var38 * 6, 6);
            var38++;
         } else {
            v(I, var13, i[var32], Z, var41);
         }

         i[var41] = i[var32];
      }

      int var42 = 2566;
      int var45 = 0;

      while (var45 < var38) {
         a[var45] = var45++;
      }

      int var46 = 59041;
      int var50 = var38;

      while (true) {
         char var51 = '\ue6a1';
         if (var50 <= 16) {
            int var58 = 0;
            var51 = '\ue6a1';
            S = 0;

            for (int var59 = 0; var59 < var38; var59++) {
               var42 = a[var59];
               if (var42 == var59) {
                  int var60 = var58;

                  for (int var61 = 0; var61 < var17; var61++) {
                     int var64 = i[var61];

                     while (a[var64] != var64) {
                        var64 = a[var64];
                     }

                     var46 = (char)45495;
                     if (var64 == var59) {
                        P[var58++] = var61;
                     }
                  }

                  int var62 = S * 12;
                  System.arraycopy(I, var59 * 6, N, var62, 6);
                  N[var62 + 6] = var60;
                  N[var62 + 7] = var58 - var60;
                  int var65 = (A(I, var59, var62 + 8, var9) ? 1 : 0);
                  var51 = '놷';
                  if ((var65 != 0)) {
                     var65 = S + 1;
                     var51 = '놷';
                     S = var65;
                  } else {
                     var58 = var60;
                  }
               }
            }

            Arrays.fill(P, var58, var17, -1);
            return;
         }

         int var57 = -1;
         int var26 = -1;
         float var27 = Float.MAX_VALUE;

         for (int var28 = 0; var28 < var38; var28++) {
            var46 = a[var28];
            short var29 = 2566;
            var29 = 2566;
            if (var46 == var28) {
               for (int var30 = var28 + 1; var30 < var38; var30++) {
                  var46 = a[var30];
                  var51 = '\ue6a1';
                  if (var46 == var30) {
                     float var31 = Q(var7, I, var28, var30);
                     var51 = '놷';
                     if (var31 < var27) {
                        var27 = var31;
                        var57 = var28;
                        var26 = var30;
                     }
                  }
               }
            }
         }

         v(I, var13, var57, I, var26);
         a[var26] = var57;
         var50--;
      }
   }

   private static boolean L(int var0, long var1, int var3) {
      HitSplashRenderer2.ensureSourceClassInitialized();
      float var4 = Z[var0 * 6];
      long var5 = 139327574840576L;
      if (139327574840576L == 0L) {
         boolean var10000 = false;
      } else {
         int var16 = var5 < 0L ? -1 : 1;
      }

      if (var4 <= Z[var3 * 6 + 3] && Z[var3 * 6] <= Z[var0 * 6 + 3]) {
         var4 = Z[var0 * 6 + 1];
         long var8 = 139327574840577L;
         if (139327574840577L == 0L) {
            boolean var17 = false;
         } else {
            int var18 = var8 < 0L ? -1 : 1;
         }

         if (var4 <= Z[var3 * 6 + 4]) {
            long var10 = 139327574840577L;
            if (139327574840577L == 0L) {
               boolean var19 = false;
            } else {
               int var20 = var10 < 0L ? -1 : 1;
            }

            if (Z[var3 * 6 + 1] <= Z[var0 * 6 + 4]) {
               long var13 = 139327574840577L;
               if (139327574840577L == 0L) {
                  boolean var21 = false;
               } else {
                  int var22 = var13 < 0L ? -1 : 1;
               }

               if (Z[var0 * 6 + 2] <= Z[var3 * 6 + 5] && Z[var3 * 6 + 2] <= Z[var0 * 6 + 5]) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   private static int S(long var0, int var2) {
      HitSplashRenderer2.ensureSourceClassInitialized();

      while (k[var2] != var2) {
         k[var2] = k[k[var2]];
         long var3 = 50444661410135L;
         if (50444661410135L == 0L) {
            boolean var10000 = false;
         } else {
            int var7 = var3 < 0L ? -1 : 1;
         }

         var2 = k[var2];
      }

      long var6 = 50444661410135L;
      if (50444661410135L == 0L) {
         boolean var8 = false;
      } else {
         int var9 = var6 < 0L ? -1 : 1;
      }

      return var2;
   }

   private static void b(int var0, int var1, long var2) {
      long var4 = 50444661410136L;
      int var6 = S(var4, var0);
      int var7 = S(var4, var1);
      if (var6 != var7) {
         k[var7] = var6;
      }
   }

   private static void v(float[] var0, long var1, int var3, float[] var4, int var5) {
      HitSplashRenderer2.ensureSourceClassInitialized();

      for (int var6 = 0; var6 < 3; var6++) {
         var0[var3 * 6 + var6] = Math.min(var0[var3 * 6 + var6], var4[var5 * 6 + var6]);
         var0[var3 * 6 + 3 + var6] = Math.max(var0[var3 * 6 + 3 + var6], var4[var5 * 6 + 3 + var6]);
      }

      long var7 = 17146565002007L;
      if (17146565002007L == 0L) {
         boolean var10000 = false;
      } else {
         int var10 = var7 < 0L ? -1 : 1;
      }
   }

   private static float Q(long var0, float[] var2, int var3, int var4) {
      float var5 = 0.0F;
      HitSplashRenderer2.ensureSourceClassInitialized();

      for (int var6 = 0; var6 < 3; var6++) {
         float var7 = (var2[var3 * 6 + var6] + var2[var3 * 6 + 3 + var6]) * 0.5F;
         float var8 = var2[var4 * 6 + var6];
         float var9 = var2[var4 * 6 + 3 + var6];
         long var10 = 120730646582789L;
         if (120730646582789L == 0L) {
            boolean var10000 = false;
         } else {
            int var14 = var10 < 0L ? -1 : 1;
         }

         float var13 = (var8 + var9) * 0.5F;
         var5 += (var7 - var13) * (var7 - var13);
      }

      return var5;
   }

   private static boolean A(float[] var0, int var1, int var2, long var3) {
      float var5 = Float.MAX_VALUE;
      float var6 = Float.MAX_VALUE;
      float var7 = -Float.MAX_VALUE;
      HitSplashRenderer2.ensureSourceClassInitialized();
      float var8 = -Float.MAX_VALUE;
      boolean var9 = false;

      for (int var10 = 0; var10 < 8; var10++) {
         long var11 = 119697918770576L;
         if (119697918770576L == 0L) {
            boolean var10000 = false;
         } else {
            int var34 = var11 < 0L ? -1 : 1;
         }

         float var14 = var0[var1 * 6 + ((var10 & 1) == 0 ? 0 : 3)];
         long var15 = 119697918770577L;
         if (119697918770577L == 0L) {
            boolean var35 = false;
         } else {
            int var36 = var15 < 0L ? -1 : 1;
         }

         float var18 = var0[var1 * 6 + ((var10 & 2) == 0 ? 1 : 4)];
         long var19 = 119697918770576L;
         if (119697918770576L == 0L) {
            boolean var37 = false;
         } else {
            int var38 = var19 < 0L ? -1 : 1;
         }

         w.set(var14, var18, var0[var1 * 6 + ((var10 & 4) == 0 ? 2 : 5)], 1.0F);
         long var21 = 119697918770577L;
         if (119697918770577L == 0L) {
            boolean var39 = false;
         } else {
            int var40 = var21 < 0L ? -1 : 1;
         }

         long var24 = 119697918770577L;
         if (119697918770577L == 0L) {
            boolean var41 = false;
         } else {
            int var42 = var24 < 0L ? -1 : 1;
         }

         HitSplashRenderer.B.transform(w);
         if (w.w < 0.05F) {
            long var33 = 119697918770576L;
            if (119697918770576L == 0L) {
               boolean var43 = false;
            } else {
               int var44 = var33 < 0L ? -1 : 1;
            }

            var9 = true;
            break;
         }

         float var27 = w.x / w.w;
         float var28 = w.y / w.w;
         var5 = Math.min(var5, var27);
         var6 = Math.min(var6, var28);
         var7 = Math.max(var7, var27);
         var8 = Math.max(var8, var28);
      }

      long var30 = 119697918770576L;
      if (119697918770576L == 0L) {
         boolean var45 = false;
      } else {
         int var46 = var30 < 0L ? -1 : 1;
      }

      long var13 = 119697918770577L;
      if (119697918770577L == 0L) {
         boolean var47 = false;
      } else {
         int var48 = var13 < 0L ? -1 : 1;
      }

      if (var9) {
         var6 = -1.0F;
         var5 = -1.0F;
         var8 = 1.0F;
         var7 = 1.0F;
      }

      float var16 = Math.max(var5, -1.0F);
      float var17 = Math.max(var6, -1.0F);
      float var31 = Math.min(var7, 1.0F);
      float var32 = Math.min(var8, 1.0F);
      if (!(var16 >= var31) && !(var17 >= var32)) {
         N[var2] = var16;
         N[var2 + 1] = var17;
         N[var2 + 2] = var31;
         N[var2 + 3] = var32;
         return true;
      } else {
         return false;
      }
   }

   private static void X(Std140Builder var0, long var1, int var3, float var4) {
      HitSplashRenderer2.ensureSourceClassInitialized();
      double var5 = HitSplashRenderer.L;

      for (int var7 = 0; var7 < 128; var7++) {
         long var8 = 1937918399578L;
         if (1937918399578L == 0L) {
            boolean var10000 = false;
         } else {
            int var22 = var8 < 0L ? -1 : 1;
         }

         int var11 = var7 < var3 ? P[var7] : -1;
         long var12 = 1937918399579L;
         if (1937918399579L == 0L) {
            boolean var23 = false;
         } else {
            int var24 = var12 < 0L ? -1 : 1;
         }

         if (var11 < 0) {
            var0.putVec4(0.0F, 0.0F, 0.0F, 0.0F).putVec4(0.0F, 0.0F, 0.0F, 0.0F).putVec4(0.0F, 0.0F, 0.0F, 0.0F);
            long var15 = 1937918399579L;
            if (1937918399579L == 0L) {
               boolean var25 = false;
            } else {
               int var26 = var15 < 0L ? -1 : 1;
            }
         } else {
            HitSplashRenderer.TypeW var21 = HitSplashRenderer.u.get(var11);
            float var16 = (float)(var5 - var21.i);
            float var17 = var21.life(var5, var4);
            float var18 = Math.min(var16 / (var21.f ? 0.3F : 0.14F), 1.0F);
            float var19 = 2.4F * (1.0F - (1.0F - var18) * (1.0F - var18) * (1.0F - var18));
            var0.putVec4((float)(var21.M - HitSplashRenderer.d), (float)(var21.z - HitSplashRenderer.N), (float)(var21.o - HitSplashRenderer.v), var21.t)
               .putVec4(var21.a, var21.p, var21.Y, var19)
               .putVec4(
                  var21.heat(var5, var4),
                  0.9F * HitSplashRenderer.smoothstep(0.62F, 1.0F, var17),
                  (float)Math.exp(-var16 / 0.12F),
                  1.0F - HitSplashRenderer.smoothstep(0.86F, 1.0F, var17)
               );
         }
      }

      long var20 = 1937918399579L;
      if (1937918399579L == 0L) {
         boolean var27 = false;
      } else {
         int var28 = var20 < 0L ? -1 : 1;
      }
   }

   private static void U(int var0, int var1, Std140Builder var2, int var3, char var4) {
      double var5 = HitSplashRenderer.L;
      HitSplashRenderer2.ensureSourceClassInitialized();

      for (int var7 = 0; var7 < 96; var7++) {
         short var8 = 9136;
         if (var7 >= var3) {
            var2.putVec4(0.0F, 0.0F, 0.0F, 0.0F).putVec4(0.0F, 0.0F, 0.0F, 0.0F);
         } else {
            HitSplashRenderer.TypeA var9 = HitSplashRenderer.T.get(var7);
            float var10 = (float)(var5 - var9.h);
            float var11 = (float)(var9.i - HitSplashRenderer.d);
            float var12 = (float)(var9.D - HitSplashRenderer.N);
            float var13 = (float)(var9.l - HitSplashRenderer.v);
            if (var9.N) {
               float var14 = Math.min(var10 / 0.16F, 1.0F);
               var8 = 9136;
               float var15 = var9.n * (0.6F + 0.8F * (float)Math.sqrt(var14));
               var2.putVec4(var11, var12, var13, var15).putVec4(var11, var12, var13, -(1.0F - var14) * (1.0F - var14) * var9.G);
            } else {
               var2.putVec4(var11, var12, var13, var9.n * 0.36F)
                  .putVec4(var11 - (float)var9.P * 0.05F, var12 - (float)var9.r * 0.05F, var13 - (float)var9.f * 0.05F, Math.min(var10 / 0.04F, 1.0F) * var9.G);
            }
         }
      }
   }

   private static float k(long var0, int var2, int var3) {
      return (var2 >>> var3 & 0xFF) / 255.0F;
   }

   
   private static GpuQuery o(long var0, CommandEncoder var2, int var3) {
      HitSplashRenderer2.ensureSourceClassInitialized();
      if (u != null) {
         OptionalLong var4 = u.getValue();
         int var5 = 1386695164;
         if (var4.isEmpty()) {
            return null;
         }

         u.close();
         u = null;
         q = q + var4.getAsLong() / 1000000.0;
         int var6 = X + 1;
         long var7 = 30641L;
         if (30641L == 0L) {
            boolean var10000 = false;
         } else {
            int var9 = var7 < 0L ? -1 : 1;
         }

         X = var6;
         if (var6 == 240) {
            ClientServices.LOGGER
               .info(
                  "Purxiy hit splash: {} ms GPU per frame ({} splats in {} clusters, {} droplets)",
                  new Object[]{String.format(Locale.ROOT, "%.3f", q / X), HitSplashRenderer.u.size(), S, HitSplashRenderer.T.size()}
               );
            q = 0.0;
            X = 0;
         }
      }

      return var2.timerQueryBegin();
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (j != null) {
            ClientCache.release(j);
         }
      } catch (Exception var119) {
         var0++;
      } finally {
         ;
      }

      try {
         if (e != null) {
            ClientCache.release(e);
         }
      } catch (Exception var117) {
         var0++;
      } finally {
         ;
      }

      try {
         if (p != null) {
            p.close();
         }
      } catch (Exception var115) {
         var0++;
      } finally {
         p = null;
      }

      try {
         if (u != null) {
            u.close();
         }
      } catch (Exception var113) {
         var0++;
      } finally {
         u = null;
      }

      try {
         if (L != null) {
            L.close();
         }
      } catch (Exception var111) {
         var0++;
      } finally {
         L = null;
      }

      try {
         if (M != null) {
            M.close();
         }
      } catch (Exception var109) {
         var0++;
      } finally {
         M = null;
      }

      try {
         if (D != null) {
            MemoryUtil.memFree(D);
         }
      } catch (Exception var107) {
         var0++;
      } finally {
         D = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

}
