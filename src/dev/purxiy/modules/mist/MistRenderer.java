package dev.purxiy.modules.mist;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline.Snippet;
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
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.Random;
import net.minecraft.class_10789;
import net.minecraft.class_12137;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_1011.class_1012;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryUtil;

final class MistRenderer {
   private static final int v = 352;
   private static final int V = 256;
   private static final int i = 10;
   private static final double h = 8192.0;
   private static final RenderPipeline p = t(27009741528094L, "mist_prep_x", "core/mist_prep", "BLUR_X", null, "TerrainSampler");
   private static final RenderPipeline U = t(27009741528094L, "mist_prep", "core/mist_prep", null, null, "BlurSampler", "TerrainSampler");
   private static final RenderPipeline Jc = t(
      27009741528094L,
      "mist_march",
      "core/mist_march",
      null,
      null,
      "DepthSampler",
      "KeptDepthSampler",
      "KeptSampler",
      "TerrainSampler",
      "FieldSampler",
      "NoiseSampler"
   );
   private static final RenderPipeline e = t(27009741528094L, "mist_blur_x", "core/mist_blur", "BLUR_X", null, "DepthSampler", "MarchSampler");
   private static final RenderPipeline a = t(27009741528094L, "mist_blur", "core/mist_blur", null, null, "DepthSampler", "MarchSampler");
   private static final RenderPipeline P = t(
      27009741528094L,
      "mist_march_lights",
      "core/mist_march",
      "LIGHTS",
      null,
      "DepthSampler",
      "KeptDepthSampler",
      "KeptSampler",
      "TerrainSampler",
      "FieldSampler",
      "NoiseSampler",
      "LightSampler"
   );
   private static final RenderPipeline[] t = new RenderPipeline[8];
   private static GpuBuffer W;
   private static ByteBuffer F;
   private static ByteBuffer g;
   private static ByteBuffer O;
   private static MistRenderer.MistUniforms y;
   private static MistRenderer.MistUniforms w;
   private static MistRenderer.MistUniforms M;
   private static MistRenderer.MistUniforms L;
   private static MistRenderer.MistUniforms T;
   private static MistRenderer.MistUniforms o;
   private static final MistRenderer.MistUniforms[] j = new MistRenderer.MistUniforms[2];
   private static int n = 0;
   private static GpuTexture z;
   private static GpuTextureView B;
   private static ByteBuffer d;
   private static GpuTexture H;
   private static GpuTexture R;
   private static GpuTexture m;
   private static GpuTextureView x;
   private static GpuTextureView Jh;
   private static GpuTextureView A;
   private static int I = 0;
   private static int b = 0;
   private static int E = 0;
   private static int D = 0;
   private static int u = 0;
   private static boolean K;
   private static final long f = 250000000L;
   private static final Matrix4f G = new Matrix4f();
   private static double J;
   private static double N;
   private static double q;
   private static long s = 0L;
   private static boolean l;
   private static int Jy = 0;
   private static int X = 0;
   static boolean r = true;
   private static final String[] Y = new String[]{"field", "march", "composite"};
   private static boolean Q;
   private static final GpuQuery[] Z = new GpuQuery[Y.length];
   private static final double[] k = new double[Y.length];
   private static final int[] C = new int[Y.length];
   private static int S = 0;
   private static boolean c;
   private static final long[] eb = new long[]{2402494601401803506L, 4022312011690147530L, 7438970445552940766L};
   private static final Long[] fb = new Long[3];

   private MistRenderer() {
   }

   private static RenderPipeline t(long var0, String var2, String var3, String var4, BlendFunction var5, String... var6) {
      Builder var7 = RenderPipeline.builder(new Snippet[0])
         .withLocation(ClientServices.id("pipeline/" + var2))
         .withVertexShader(ClientServices.id("core/mist"))
         .withFragmentShader(ClientServices.id(var3))
         .withUniform("PurxiyMist", class_10789.field_60031)
         .withDepthWrite(false)
         .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(class_290.field_60033, class_5596.field_27379);
      long var8 = 27009741528093L;
      int var20 = var8 < 0L ? -1 : 1;
      if (var5 != null) {
         var7.withBlend(var5);
         long var11 = 27009741528094L;
         int var13 = var11 < 0L ? -1 : 1;
      } else {
         var7.withoutBlend();
      }

      String[] var20x = var6;
      int var12 = var6.length;
      int var13 = 0;

      while (var13 < var12) {
         String var14 = var20x[var13];
         long var15 = 27009741528093L;
         int var18 = var15 < 0L ? -1 : 1;
         var7.withSampler(var14);
         var13++;
         long var18x = 27009741528093L;
      }

      if (var4 != null) {
         var7.withShaderDefine(var4);
      }

      return var7.build();
   }

   private static RenderPipeline M(boolean var0, boolean var1, long var2, boolean var4) {
      MistRenderPass.ensureSourceClassInitialized();
      int var5 = (var0 ? 1 : 0) | (var1 ? 2 : 0) | (var4 ? 4 : 0);
      RenderPipeline var6 = t[var5];
      if (var6 == null) {
         Builder var7 = RenderPipeline.builder(new Snippet[0]);
         long var8 = 39166360933580L;
         int var11 = var8 < 0L ? -1 : 1;
         String var11x = var0 ? "_masked" : "";
         long var12 = 39166360933580L;
         int var15 = var12 < 0L ? -1 : 1;
         String var15x = var1 ? "_creatures" : "";
         long var16 = 39166360933580L;
         int var16x = var16 < 0L ? -1 : 1;
         String var19x = var4 ? "_lights" : "";
         Builder var22 = var7.withLocation(ClientServices.id("pipeline/mist" + var11x + var15x + var19x))
            .withVertexShader(ClientServices.id("core/mist"))
            .withFragmentShader(ClientServices.id("core/mist"))
            .withUniform("PurxiyMist", class_10789.field_60031)
            .withSampler("DepthSampler")
            .withSampler("MarchSampler")
            .withSampler("TerrainSampler")
            .withSampler("FieldSampler")
            .withSampler("NoiseSampler")
            .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
            .withDepthWrite(false)
            .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
            .withCull(false)
            .withVertexFormat(class_290.field_60033, class_5596.field_27379);
         long var23 = 39166360933581L;
         int var20 = var23 < 0L ? -1 : 1;
         if (var0) {
            var22.withShaderDefine("MASKED").withSampler("HandMaskSampler");
         }

         if (var1) {
            var22.withShaderDefine("CREATURES").withSampler("TerrainDepthSampler").withSampler("OpaqueDepthSampler");
         }

         if (var4) {
            var22.withShaderDefine("LIGHTS").withSampler("LightSampler");
         }

         var6 = var22.build();
         t[var5] = var6;
      }

      return var6;
   }

   static void setTiming(boolean var0) {
      Q = var0;
      Arrays.fill(k, 0.0);
      Arrays.fill(C, 0);
      S = 0;
   }

   static void keepDepth(boolean var0) {
      long var1 = 88745069933391L;
      long var3 = var1 ^ 48371680595327L;
      class_276 var5 = class_310.method_1551().method_1522();
      GpuTexture var6 = var5.method_30278();
      if (var6 != null) {
         GpuDevice var7 = RenderSystem.getDevice();
         V(var7, var5.field_1482, var3, var5.field_1481);
         var7.createCommandEncoder().copyTextureToTexture(var6, var0 ? R : H, 0, 0, 0, 0, 0, var5.field_1482, var5.field_1481);
      }
   }

   static void draw(class_310 var0) {
      long var1 = 131747756213373L;
      long var3 = var1 ^ 83576542394677L;
      long var5 = var1 ^ 134754660413960L;
      long var7 = (var1 ^ 52944277924334L) >>> 8;
      int var9 = (int)((var1 ^ 52944277924334L) << 56 >>> 56);
      long var10 = var1 ^ 128011948990732L;
      long var12 = var1 ^ 92693098287280L;
      long var14 = var1 ^ 72338178920591L;
      long var16 = var1 ^ 66488112491171L;
      long var18 = var1 ^ 24310580562099L;
      int var20 = (int)((var1 ^ 8909688010768L) >>> 56);
      long var21 = (var1 ^ 8909688010768L) << 8 >>> 8;
      class_276 var23 = var0.method_1522();
      GpuTextureView var24 = var23.method_71639();
      GpuTextureView var25 = var23.method_71640();
      if (var24 != null && var25 != null) {
         GpuDevice var26 = RenderSystem.getDevice();
         GpuTextureView var27 = MistRenderPass.t ? SkyPipelineRouter.handMask() : null;
         int var28 = var23.field_1482;
         int var29 = var23.field_1481;
         boolean var30 = MistRenderPass.G > 0.001F && MistRenderPass.U && MistRenderPass.W && H != null && E == var28 && D == var29;
         boolean var31 = MistRenderPass.q > 0.001F && MistRenderPass.O.any();
         RenderPipeline var32 = M(var27 != null, var30, var12, var31);
         RenderPipeline var33 = var31 ? P : Jc;

         for (RenderPipeline var37 : new RenderPipeline[]{p, U, var33, e, a, var32}) {
            if (!var26.precompilePipeline(var37).isValid()) {
               return;
            }
         }

         i(var26, var28, var3, var29);
         CommandEncoder var58 = var26.createCommandEncoder();
         c = Q && Q(var7, (byte)var9);
         int var59 = y(var14);
         class_12137 var60 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
         class_12137 var61 = RenderSystem.getSamplerCache().method_75297(FilterMode.LINEAR);
         GpuQuery var38 = f(var58, 0, var5);
         boolean var39 = V(var16, var58);
         if (MistRenderPass.O.takeChanged()) {
            ByteBuffer var40 = d.clear();
            MistRenderPass.O.encode(var40);
            var40.clear();
            var58.writeToTexture(z, var40, class_1012.field_4998, 0, 0, 0, 0, 528, 264);
         }

         var58.writeToBuffer(W.slice(), j(var28, var29, var10, var30, var59));
         if (var39 || !K || --u <= 0) {
            u = 10;
            K = true;
            RenderPass var63 = Y(var18, var58, "blur", w, p);

            try {
               var63.bindTexture("TerrainSampler", y.K, var60);
               var63.draw(0, 3);
               MistRenderPass.metric("drawn");
            } catch (Throwable var55) {
               if (var63 != null) {
                  try {
                     var63.close();
                  } catch (Throwable var51) {
                     var55.addSuppressed(var51);
                  }
               }

               throw var55;
            }

            if (var63 != null) {
               var63.close();
            }

            var63 = Y(var18, var58, "field", M, U);

            try {
               var63.bindTexture("BlurSampler", w.K, var60);
               var63.bindTexture("TerrainSampler", y.K, var61);
               var63.draw(0, 3);
               MistRenderPass.metric("drawn");
            } catch (Throwable var54) {
               if (var63 != null) {
                  try {
                     var63.close();
                  } catch (Throwable var50) {
                     var54.addSuppressed(var50);
                  }
               }

               throw var54;
            }

            if (var63 != null) {
               var63.close();
            }
         }

         GpuQuery var65 = k((byte)var20, var58, var38, var21, 1);
         class_12137 var41 = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
         n ^= 1;
         MistRenderer.MistUniforms var42 = j[n];
         RenderPass var43 = Y(var18, var58, "march", var42, var33);

         try {
            var43.bindTexture("DepthSampler", var25, var60);
            var43.bindTexture("KeptDepthSampler", A, var60);
            var43.bindTexture("KeptSampler", j[n ^ 1].K, var60);
            var43.bindTexture("TerrainSampler", y.K, var61);
            var43.bindTexture("FieldSampler", M.K, var61);
            var43.bindTexture("NoiseSampler", L.K, var61);
            if (var31) {
               var43.bindTexture("LightSampler", B, var41);
            }

            var43.draw(0, 3);
            MistRenderPass.metric("drawn");
         } catch (Throwable var57) {
            if (var43 != null) {
               try {
                  var43.close();
               } catch (Throwable var49) {
                  var57.addSuppressed(var49);
               }
            }

            throw var57;
         }

         if (var43 != null) {
            var43.close();
         }

         var58.copyTextureToTexture(var23.method_30278(), m, 0, 0, 0, 0, 0, var28, var29);
         var43 = Y(var18, var58, "smooth x", T, e);

         try {
            var43.bindTexture("DepthSampler", var25, var60);
            var43.bindTexture("MarchSampler", var42.K, var60);
            var43.draw(0, 3);
            MistRenderPass.metric("drawn");
         } catch (Throwable var53) {
            if (var43 != null) {
               try {
                  var43.close();
               } catch (Throwable var48) {
                  var53.addSuppressed(var48);
               }
            }

            throw var53;
         }

         if (var43 != null) {
            var43.close();
         }

         var43 = Y(var18, var58, "smooth z", o, a);

         try {
            var43.bindTexture("DepthSampler", var25, var60);
            var43.bindTexture("MarchSampler", T.K, var60);
            var43.draw(0, 3);
            MistRenderPass.metric("drawn");
         } catch (Throwable var52) {
            if (var43 != null) {
               try {
                  var43.close();
               } catch (Throwable var47) {
                  var52.addSuppressed(var47);
               }
            }

            throw var52;
         }

         if (var43 != null) {
            var43.close();
         }

         var38 = k((byte)var20, var58, var65, var21, 2);
         var43 = var58.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX mist", var24, OptionalInt.empty());

         try {
            var43.setPipeline(var32);
            RenderSystem.bindDefaultUniforms(var43);
            var43.setUniform("PurxiyMist", W);
            var43.bindTexture("DepthSampler", var25, var60);
            var43.bindTexture("MarchSampler", o.K, var60);
            var43.bindTexture("TerrainSampler", y.K, var61);
            var43.bindTexture("FieldSampler", M.K, var61);
            var43.bindTexture("NoiseSampler", L.K, var61);
            if (var30) {
               var43.bindTexture("TerrainDepthSampler", x, var60);
               var43.bindTexture("OpaqueDepthSampler", Jh, var60);
            }

            if (var31) {
               var43.bindTexture("LightSampler", B, var41);
            }

            if (var27 != null) {
               var43.bindTexture("HandMaskSampler", var27, var60);
            }

            var43.draw(0, 3);
            MistRenderPass.metric("drawn");
         } catch (Throwable var56) {
            if (var43 != null) {
               try {
                  var43.close();
               } catch (Throwable var46) {
                  var56.addSuppressed(var46);
               }
            }

            throw var56;
         }

         if (var43 != null) {
            var43.close();
         }

         k((byte)var20, var58, var38, var21, -1);
      }
   }

   private static boolean V(long var0, CommandEncoder var2) {
      MistRenderPass.ensureSourceClassInitialized();
      MistConfig var3 = MistRenderPass.v;
      if (var3.allDirty()) {
         ByteBuffer var11 = O.clear();

         for (int var13 = 0; var13 < 512; var13++) {
            var3.encodeRow(var13 * 512, 512, var11);
            long var15 = 83195972693725L;
            int var8 = var15 < 0L ? -1 : 1;
         }

         var11.clear();
         var2.writeToTexture(y.n, var11, class_1012.field_4997, 0, 0, 0, 0, 512, 512);
         long var14 = 83195972693726L;
         int var16 = var14 < 0L ? -1 : 1;
         var3.uploaded();
         return true;
      } else {
         int var4 = var3.dirtyCount();

         for (int var5 = 0; var5 < var4; var5++) {
            int var6 = var3.dirty(var5);
            long var7 = 83195972693725L;
            int var10 = var7 < 0L ? -1 : 1;
            ByteBuffer var10x = g.clear();
            var3.encode(var6, var10x);
            var10x.clear();
            var2.writeToTexture(y.n, var10x, class_1012.field_4997, 0, 0, var6 % 32 * 16, var6 / 32 * 16, 16, 16);
         }

         long var12 = 83195972693725L;
         int var15 = var12 < 0L ? -1 : 1;
         var3.uploaded();
         return var4 > 0;
      }
   }

   private static RenderPass Y(long var0, CommandEncoder var2, String var3, MistRenderer.MistUniforms var4, RenderPipeline var5) {
      RenderPass var6 = var2.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX mist " + var3, var4.K, OptionalInt.empty());
      var6.setPipeline(var5);
      RenderSystem.bindDefaultUniforms(var6);
      var6.setUniform("PurxiyMist", W);
      return var6;
   }

   private static int y(long var0) {
      long var2;
      boolean var4;
      label37: {
         MistRenderPass.ensureSourceClassInitialized();
         var2 = System.nanoTime();
         long var5 = 59478338753777L;
         var4 = false;
         if (r) {
            long var8 = 59478338753778L;
            int var9 = var8 < 0L ? -1 : 1;
            if (l && var2 - s < 250000000L) {
               var4 = true;
               break label37;
            }
         }

         var4 = false;
      }

      long var6 = 59478338753778L;
      int var14 = var6 < 0L ? -1 : 1;
      s = var2;
      long var9 = 59478338753778L;
      int var11 = var9 < 0L ? -1 : 1;
      if (!var4) {
         l = true;
         return -1;
      } else {
         X++;
         Jy = Jy + 1 & 3;
         return Jy;
      }
   }

   static int keptFrames() {
      return X;
   }

   private static ByteBuffer j(int var0, int var1, long var2, boolean var4, int var5) {
      short var6 = 512;
      float var7 = (float)(MistRenderPass.y - Math.floor(MistRenderPass.y / var6) * var6);
      MistRenderPass.ensureSourceClassInitialized();
      float var8 = (float)(MistRenderPass.H - Math.floor(MistRenderPass.H / var6) * var6);
      double var9 = MistRenderPass.y + MistRenderPass.l;
      double var11 = MistRenderPass.H + MistRenderPass.B;
      float var13 = (float)(var9 - Math.floor(var9 / 8192.0) * 8192.0);
      float var14 = (float)(var11 - Math.floor(var11 / 8192.0) * 8192.0);
      float var15 = Math.max(MistRenderPass.s, 1.0F);
      Std140Builder var16 = Std140Builder.intoBuffer(F.clear()).putMat4f(MistRenderPass.A);
      float var17 = var0;
      float var18 = var1;
      float var19 = (var0 + 1) / 2;
      long var20 = 4122909255024L;
      int var23 = var20 < 0L ? -1 : 1;
      Std140Builder var23x = var16.putVec4(var17, var18, var19, (var1 + 1) / 2)
         .putVec4(var7, (float)MistRenderPass.f, var8, MistRenderPass.v.base())
         .putVec4(var13, (float)MistRenderPass.f, var14, (float)MistRenderPass.L)
         .putVec4(MistRenderPass.sigma(), 1.0F / var15, MistRenderPass.b[2], 1.0F / (var15 * MistRenderPass.b[3]))
         .putVec4(MistRenderPass.clearRadius(), MistRenderPass.skyReach(), MistRenderPass.v.regionalGround(), MistRenderPass.h);
      float var24 = MistRenderPass.X;
      var18 = MistRenderPass.D;
      var19 = MistRenderPass.t ? 1.0F : 0.0F;
      MistRenderPass.ensureSourceClassInitialized();
      Std140Builder var25 = var23x.putVec4(var24, var18, var19, 0.0F)
         .putVec4(MistRenderPass.Y[0], MistRenderPass.Y[1], MistRenderPass.Y[2], MistRenderPass.b[7] * MistRenderPass.J);
      float var26 = MistRenderPass.a[0];
      long var27 = 4122909255024L;
      int var29 = var27 < 0L ? -1 : 1;
      Std140Builder var29x = var25.putVec4(var26, MistRenderPass.a[1], MistRenderPass.a[2], MistRenderPass.C)
         .putVec4(MistRenderPass.i.x, MistRenderPass.i.y, MistRenderPass.i.z, MistRenderPass.J)
         .putVec4(MistRenderPass.K[0] * MistRenderPass.q, MistRenderPass.K[1] * MistRenderPass.q, MistRenderPass.K[2] * MistRenderPass.q, 0.0F)
         .putVec4(var5, 0.0F, 0.0F, 0.0F)
         .putVec4(MistRenderPass.G, var4 ? 1.0F : 0.0F, MistRenderPass.b[5], MistRenderPass.b[6])
         .putMat4f(G)
         .putVec4((float)(MistRenderPass.y - J), (float)(MistRenderPass.f - N), (float)(MistRenderPass.H - q), 0.0F);
      G.set(MistRenderPass.o);
      J = MistRenderPass.y;
      N = MistRenderPass.f;
      q = MistRenderPass.H;
      return var29x.get();
   }

   private static void i(GpuDevice var0, int var1, long var2, int var4) {
      MistRenderPass.ensureSourceClassInitialized();
      if (W == null) {
         W = var0.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX mist"), 136, 352L);
         F = MemoryUtil.memAlloc(352);
         g = MemoryUtil.memAlloc(1024);
         O = MemoryUtil.memAlloc(1048576);
         y = new MistRenderer.MistUniforms(var0, "terrain", 512, 512, false);
         w = new MistRenderer.MistUniforms(var0, "blur", 512, 512, true);
         M = new MistRenderer.MistUniforms(var0, "field", 512, 512, true);
         L = new MistRenderer.MistUniforms(var0, "noise", 256, 256, false);
         z = var0.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX mist lights", 5, TextureFormat.RED8, 528, 264, 1, 1);
         B = var0.createTextureView(z);
         d = MemoryUtil.memAlloc(139392);
         var0.createCommandEncoder().writeToTexture(L.n, noiseTexels(), class_1012.field_4997, 0, 0, 0, 0, 256, 256);
         MistRenderPass.v.forget();
         MistRenderPass.O.forget();
         K = false;
      }

      int var5 = (var1 + 1) / 2;
      int var6 = (var4 + 1) / 2;
      long var7 = 65772125388103L;
      int var10 = var7 < 0L ? -1 : 1;
      if (o != null) {
         long var10x = 65772125388104L;
         int var11 = var10x < 0L ? -1 : 1;
         if (I == var1 && b == var4) {
            return;
         }
      }

      if (o != null) {
         j[0].close();
         j[1].close();
         T.close();
         o.close();
         A.close();
         m.close();
      }

      j[0] = new MistRenderer.MistUniforms(var0, "march", var5, var6, true);
      j[1] = new MistRenderer.MistUniforms(var0, "last march", var5, var6, true);
      T = new MistRenderer.MistUniforms(var0, "smoothed", var5, var6, true);
      o = new MistRenderer.MistUniforms(var0, "soft", var5, var6, true);
      m = var0.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX mist kept depth", 5, TextureFormat.DEPTH32, var1, var4, 1, 1);
      A = var0.createTextureView(m);
      I = var1;
      b = var4;
      l = false;
   }

   private static void V(GpuDevice var0, int var1, long var2, int var4) {
      MistRenderPass.ensureSourceClassInitialized();
      if (H != null) {
         long var5 = 135553371127344L;
         int var7 = var5 < 0L ? -1 : 1;
         if (E == var1 && D == var4) {
            return;
         }
      }

      if (H != null) {
         x.close();
         H.close();
         Jh.close();
         R.close();
      }

      H = var0.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX mist terrain depth", 5, TextureFormat.DEPTH32, var1, var4, 1, 1);
      x = var0.createTextureView(H);
      R = var0.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX mist opaque depth", 5, TextureFormat.DEPTH32, var1, var4, 1, 1);
      Jh = var0.createTextureView(R);
      E = var1;
      D = var4;
   }

   static ByteBuffer noiseTexels() {
      Random var0 = new Random(1835627380L);
      byte[] var1 = new byte[65536];
      byte[] var2 = new byte[65536];
      var0.nextBytes(var1);
      var0.nextBytes(var2);
      ByteBuffer var3 = MemoryUtil.memAlloc(262144);

      for (int var4 = 0; var4 < 256; var4++) {
         for (int var5 = 0; var5 < 256; var5++) {
            int var6 = var4 * 256 + var5;
            int var7 = (var4 + 17 & 0xFF) * 256 + (var5 + 37 & 0xFF);
            var3.put(var1[var6]).put(var1[var7]).put(var2[var6]).put(var2[var7]);
         }
      }

      var3.clear();
      return var3;
   }

   private static GpuQuery f(CommandEncoder var0, int var1, long var2) {
      if (!c) {
         return null;
      } else {
         GpuQuery var4 = var0.timerQueryBegin();
         Z[var1] = var4;
         return var4;
      }
   }

   private static GpuQuery k(byte var0, CommandEncoder var1, GpuQuery var2, long var3, int var5) {
      long var6 = 140499536870509L;
      long var8 = var6 ^ 125986178042392L;
      if (var2 != null) {
         var1.timerQueryEnd(var2);
      }

      return var5 < 0 ? null : f(var1, var5, var8);
   }

   private static boolean Q(long var0, byte var2) {
      MistRenderPass.ensureSourceClassInitialized();
      GpuQuery[] var3 = Z;

      for (GpuQuery var7 : var3) {
         byte var8 = -109;
         var8 = -109;
         if (var7 != null && var7.getValue().isEmpty()) {
            return false;
         }
      }

      boolean var17 = false;

      for (int var18 = 0; var18 < Y.length; var18++) {
         GpuQuery var20 = Z[var18];
         long var22 = 309068223853L;
         int var25 = var22 < 0L ? -1 : 1;
         byte var10 = -109;
         long var11 = 309068223852L;
         int var13 = var11 < 0L ? -1 : 1;
         if (var20 != null) {
            k[var18] = k[var18] + var20.getValue().getAsLong() / 1000000.0;
            C[var18]++;
            var20.close();
            Z[var18] = null;
            var17 = true;
         }
      }

      byte var19 = -109;
      if (var17 && ++S == 240) {
         StringBuilder var21 = new StringBuilder();
         double var23 = 0.0;

         for (int var9 = 0; var9 < Y.length; var9++) {
            long var26 = 309068223852L;
            int var29 = var26 < 0L ? -1 : 1;
            double var13 = k[var9] / Math.max(C[var9], 1);
            var23 += var13;
            long var15 = 309068223852L;
            int var16 = var15 < 0L ? -1 : 1;
            var21.append(var9 == 0 ? "" : ", ").append(Y[var9]).append(' ').append(String.format(Locale.ROOT, "%.3f", var13));
         }

         ClientServices.LOGGER.info("SourceByDevFFnightmare DeObfDeCompByGuideX mist: {} ms GPU per frame at {}x{}: {}", new Object[]{String.format(Locale.ROOT, "%.3f", var23), I, b, var21});
         long var25 = 309068223852L;
         int var11 = var25 < 0L ? -1 : 1;
         Arrays.fill(k, 0.0);
         Arrays.fill(C, 0);
         S = 0;
      }

      return true;
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (p != null) {
            ClientCache.release(p);
         }
      } catch (Exception var1652) {
         var0++;
      } finally {
         ;
      }

      try {
         if (U != null) {
            ClientCache.release(U);
         }
      } catch (Exception var1650) {
         var0++;
      } finally {
         ;
      }

      try {
         if (Jc != null) {
            ClientCache.release(Jc);
         }
      } catch (Exception var1648) {
         var0++;
      } finally {
         ;
      }

      try {
         if (e != null) {
            ClientCache.release(e);
         }
      } catch (Exception var1646) {
         var0++;
      } finally {
         ;
      }

      try {
         if (a != null) {
            ClientCache.release(a);
         }
      } catch (Exception var1644) {
         var0++;
      } finally {
         ;
      }

      try {
         if (P != null) {
            ClientCache.release(P);
         }
      } catch (Exception var1642) {
         var0++;
      } finally {
         ;
      }

      for (int var1 = 0; var1 < t.length; var1++) {
         try {
            if (t[var1] != null) {
               ClientCache.release(t[var1]);
            }
         } catch (Exception var1640) {
            var0++;
         } finally {
            t[var1] = null;
         }
      }

      try {
         if (B != null) {
            B.close();
         }
      } catch (Exception var1638) {
         var0++;
      } finally {
         B = null;
      }

      try {
         if (x != null) {
            x.close();
         }
      } catch (Exception var1636) {
         var0++;
      } finally {
         x = null;
      }

      try {
         if (Jh != null) {
            Jh.close();
         }
      } catch (Exception var1634) {
         var0++;
      } finally {
         Jh = null;
      }

      try {
         if (A != null) {
            A.close();
         }
      } catch (Exception var1632) {
         var0++;
      } finally {
         A = null;
      }

      for (int var1654 = 0; var1654 < Z.length; var1654++) {
         try {
            if (Z[var1654] != null) {
               Z[var1654].close();
            }
         } catch (Exception var1630) {
            var0++;
         } finally {
            Z[var1654] = null;
         }
      }

      try {
         if (W != null) {
            W.close();
         }
      } catch (Exception var1628) {
         var0++;
      } finally {
         W = null;
      }

      try {
         if (y != null) {
            y.close();
         }
      } catch (Exception var1626) {
         var0++;
      } finally {
         y = null;
      }

      try {
         if (w != null) {
            w.close();
         }
      } catch (Exception var1624) {
         var0++;
      } finally {
         w = null;
      }

      try {
         if (M != null) {
            M.close();
         }
      } catch (Exception var1622) {
         var0++;
      } finally {
         M = null;
      }

      try {
         if (L != null) {
            L.close();
         }
      } catch (Exception var1620) {
         var0++;
      } finally {
         L = null;
      }

      try {
         if (T != null) {
            T.close();
         }
      } catch (Exception var1618) {
         var0++;
      } finally {
         T = null;
      }

      try {
         if (o != null) {
            o.close();
         }
      } catch (Exception var1616) {
         var0++;
      } finally {
         o = null;
      }

      for (int var1655 = 0; var1655 < j.length; var1655++) {
         try {
            if (j[var1655] != null) {
               j[var1655].close();
            }
         } catch (Exception var1614) {
            var0++;
         } finally {
            j[var1655] = null;
         }
      }

      try {
         if (z != null) {
            z.close();
         }
      } catch (Exception var1612) {
         var0++;
      } finally {
         z = null;
      }

      try {
         if (H != null) {
            H.close();
         }
      } catch (Exception var1610) {
         var0++;
      } finally {
         H = null;
      }

      try {
         if (R != null) {
            R.close();
         }
      } catch (Exception var1608) {
         var0++;
      } finally {
         R = null;
      }

      try {
         if (m != null) {
            m.close();
         }
      } catch (Exception var1606) {
         var0++;
      } finally {
         m = null;
      }

      try {
         if (F != null) {
            MemoryUtil.memFree(F);
         }
      } catch (Exception var1604) {
         var0++;
      } finally {
         F = null;
      }

      try {
         if (g != null) {
            MemoryUtil.memFree(g);
         }
      } catch (Exception var1602) {
         var0++;
      } finally {
         g = null;
      }

      try {
         if (O != null) {
            MemoryUtil.memFree(O);
         }
      } catch (Exception var1600) {
         var0++;
      } finally {
         O = null;
      }

      try {
         if (d != null) {
            MemoryUtil.memFree(d);
         }
      } catch (Exception var15981) {
         var0++;
      } finally {
         d = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 26508) & 32767;
      if (fb[var3] == null) {
         fb[var3] = eb[var3] ^ var1;
      }

      return fb[var3];
   }

   private static final class MistUniforms {
      final GpuTexture n;
      final GpuTextureView K;
      final int l;
      final int j;
      private static final String a = "SourceByDevFFnightmare DeObfDeCompByGuideX mist ";

      MistUniforms(GpuDevice var1, String var2, int var3, int var4, boolean var5) {
         int var6 = 4 | (var5 ? 8 : 1);
         this.n = var1.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX mist " + var2, var6, TextureFormat.RGBA8, var3, var4, 1, 1);
         this.K = var1.createTextureView(this.n);
         this.l = var3;
         this.j = var4;
      }

      void close() {
         this.K.close();
         this.n.close();
      }
   }
}
