package dev.purxiy.modules.bokeh;

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
import net.minecraft.class_10789;
import net.minecraft.class_12137;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import org.lwjgl.system.MemoryUtil;

final class BokehRenderer {
   private static final int m = 176;
   private static final RenderPipeline X = p(135764287999132L, "bokeh_prep", "core/bokeh_prep", null, false, "SceneSampler", "DepthSampler");
   private static final RenderPipeline b = p(135764287999132L, "bokeh_prep_masked", "core/bokeh_prep", null, true, "SceneSampler", "DepthSampler");
   private static final RenderPipeline G = p(135764287999132L, "bokeh_half", "core/bokeh_tiles", "HALF", false, "PrepSampler");
   private static final RenderPipeline n = p(135764287999132L, "bokeh_tiles", "core/bokeh_tiles", null, false, "PrepSampler");
   private static final RenderPipeline t = p(135764287999132L, "bokeh_dilate", "core/bokeh_tiles", "DILATE", false, "TileSampler");
   private static final RenderPipeline o = p(135764287999132L, "bokeh_gather", "core/bokeh_gather", null, false, "PrepSampler", "HalfSampler", "TileSampler");
   private static final RenderPipeline D = p(135764287999132L, "bokeh_spots", "core/bokeh_spots", null, false, "SceneSampler", "PrepSampler", "DepthSampler");
   private static final RenderPipeline v = p(
      135764287999132L, "bokeh_spots_masked", "core/bokeh_spots", null, true, "SceneSampler", "PrepSampler", "DepthSampler"
   );
   private static final RenderPipeline K = p(
      135764287999132L, "bokeh", "core/bokeh", null, false, "SceneSampler", "DepthSampler", "PrepSampler", "GatherSampler", "SpriteSampler"
   );
   private static final RenderPipeline V = p(
      135764287999132L, "bokeh_masked", "core/bokeh", null, true, "SceneSampler", "DepthSampler", "PrepSampler", "GatherSampler", "SpriteSampler"
   );
   private static final RenderPipeline A = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/bokeh_sprites"))
      .withVertexShader(ClientServices.id("core/bokeh_sprites"))
      .withFragmentShader(ClientServices.id("core/bokeh_sprites"))
      .withUniform("PurxiyBokeh", class_10789.field_60031)
      .withSampler("SpotSampler")
      .withSampler("SceneSampler")
      .withSampler("PrepSampler")
      .withSampler("GatherSampler")
      .withBlend(BlendFunction.ADDITIVE)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static GpuBuffer c;
   private static ByteBuffer u;
   private static BokehRenderer.BokehProfile z;
   private static BokehRenderer.BokehProfile x;
   private static BokehRenderer.BokehProfile M;
   private static BokehRenderer.BokehProfile g;
   private static BokehRenderer.BokehProfile a;
   private static BokehRenderer.BokehProfile Z;
   private static BokehRenderer.BokehProfile s;
   private static BokehRenderer.BokehProfile r;
   private static int l = 0;
   private static int F = 0;
   private static int I = 0;
   private static final String[] C = new String[]{"grid", "blur", "lights", "composite"};
   private static boolean Q;
   private static final GpuQuery[] E = new GpuQuery[C.length];
   private static final double[] w = new double[C.length];
   private static int p = 0;
   private static boolean S;
   private static final String[] e = new String[74];
   private static final Integer[] h = new Integer[10];
   private static final long i = 176L;

   private BokehRenderer() {
   }

   private static RenderPipeline p(long var0, String var2, String var3, String var4, boolean var5, String... var6) {
      Builder var7 = RenderPipeline.builder(new Snippet[0])
         .withLocation(ClientServices.id("pipeline/" + var2))
         .withVertexShader(ClientServices.id("core/bokeh"))
         .withFragmentShader(ClientServices.id(var3))
         .withUniform("PurxiyBokeh", class_10789.field_60031)
         .withoutBlend()
         .withDepthWrite(false)
         .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(class_290.field_60033, class_5596.field_27379);
      String[] var8 = var6;
      int var9 = var6.length;
      int var10 = 0;

      while (var10 < var9) {
         String var11 = var8[var10];
         var7.withSampler(var11);
         long var12 = 135764287999131L;
         int var15 = var12 < 0L ? -1 : 1;
         var10++;
         long var15x = 135764287999132L;
         int var16 = var15x < 0L ? -1 : 1;
      }

      if (var4 != null) {
         var7.withShaderDefine(var4);
      }

      if (var5) {
         var7.withShaderDefine("MASKED").withSampler("HandMaskSampler");
      }

      return var7.build();
   }

   static void setTiming(boolean var0) {
      Q = var0;
      Arrays.fill(w, 0.0);
      p = 0;
   }

   static void draw(class_310 var0) {
      long var1 = 41824381453316L;
      long var3 = var1 ^ 33858690680553L;
      int var5 = (int)((var1 ^ 98269607747651L) >>> 32);
      int var6 = (int)((var1 ^ 98269607747651L) << 32 >>> 32);
      long var7 = var1 ^ 29109740695932L;
      int var9 = (int)((var1 ^ 33057850450513L) >>> 48);
      long var10 = (var1 ^ 33057850450513L) << 16 >>> 16;
      int var12 = (int)((var1 ^ 49576419969974L) >>> 48);
      long var13 = (var1 ^ 49576419969974L) << 16 >>> 16;
      long var15 = var1 ^ 99917286827667L;
      class_276 var17 = var0.method_1522();
      GpuTexture var18 = var17.method_30277();
      GpuTextureView var19 = var17.method_71639();
      GpuTextureView var20 = var17.method_71640();
      if (var18 != null && var19 != null && var20 != null) {
         GpuDevice var21 = RenderSystem.getDevice();
         GpuTextureView var22 = BokehRenderPass.z ? SkyPipelineRouter.handMask() : null;
         RenderPipeline var23 = var22 != null ? b : X;
         RenderPipeline var24 = var22 != null ? v : D;
         RenderPipeline var25 = var22 != null ? V : K;

         for (RenderPipeline var29 : new RenderPipeline[]{var23, G, n, t, o, var24, A, var25}) {
            if (!var21.precompilePipeline(var29).isValid()) {
               return;
            }
         }

         int var54 = var17.field_1482;
         int var55 = var17.field_1481;
         y(var21, var54, (char)var12, var55, var13);
         CommandEncoder var56 = var21.createCommandEncoder();
         S = Q && e(var15);
         GpuQuery var57 = Q(var56, 0, var3);
         var56.copyTextureToTexture(var18, z.a, 0, 0, 0, 0, 0, var54, var55);
         var56.writeToBuffer(c.slice(), S((short)var9, var10, var54, var55));
         class_12137 var30 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
         class_12137 var31 = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
         RenderPass var32 = J(var56, var7, "prep", x, var23, false);

         try {
            var32.bindTexture("SceneSampler", z.d, var30);
            var32.bindTexture("DepthSampler", var20, var30);
            if (var22 != null) {
               var32.bindTexture("HandMaskSampler", var22, var30);
            }

            var32.draw(0, 3);
            BokehRenderPass.metric("drawn");
         } catch (Throwable var53) {
            if (var32 != null) {
               try {
                  var32.close();
               } catch (Throwable var45) {
                  var53.addSuppressed(var45);
               }
            }

            throw var53;
         }

         if (var32 != null) {
            var32.close();
         }

         var32 = J(var56, var7, "half grid", M, G, false);

         try {
            var32.bindTexture("PrepSampler", x.d, var30);
            var32.draw(0, 3);
            BokehRenderPass.metric("drawn");
         } catch (Throwable var50) {
            if (var32 != null) {
               try {
                  var32.close();
               } catch (Throwable var44) {
                  var50.addSuppressed(var44);
               }
            }

            throw var50;
         }

         if (var32 != null) {
            var32.close();
         }

         var32 = J(var56, var7, "tiles", g, n, false);

         try {
            var32.bindTexture("PrepSampler", x.d, var30);
            var32.draw(0, 3);
            BokehRenderPass.metric("drawn");
         } catch (Throwable var49) {
            if (var32 != null) {
               try {
                  var32.close();
               } catch (Throwable var43) {
                  var49.addSuppressed(var43);
               }
            }

            throw var49;
         }

         if (var32 != null) {
            var32.close();
         }

         var32 = J(var56, var7, "reach", a, t, false);

         try {
            var32.bindTexture("TileSampler", g.d, var30);
            var32.draw(0, 3);
            BokehRenderPass.metric("drawn");
         } catch (Throwable var48) {
            if (var32 != null) {
               try {
                  var32.close();
               } catch (Throwable var42) {
                  var48.addSuppressed(var42);
               }
            }

            throw var48;
         }

         if (var32 != null) {
            var32.close();
         }

         GpuQuery var62 = p(var56, var5, var6, var57, 1);
         RenderPass var33 = J(var56, var7, "gather", Z, o, false);

         try {
            var33.bindTexture("PrepSampler", x.d, var31);
            var33.bindTexture("HalfSampler", M.d, var31);
            var33.bindTexture("TileSampler", a.d, var30);
            var33.draw(0, 3);
            BokehRenderPass.metric("drawn");
         } catch (Throwable var47) {
            if (var33 != null) {
               try {
                  var33.close();
               } catch (Throwable var41) {
                  var47.addSuppressed(var41);
               }
            }

            throw var47;
         }

         if (var33 != null) {
            var33.close();
         }

         var57 = p(var56, var5, var6, var62, 2);
         boolean var63 = BokehRenderPass.R > 0.005F;
         if (var63) {
            RenderPass var34 = J(var56, var7, "lights", s, var24, false);

            try {
               var34.bindTexture("SceneSampler", z.d, var30);
               var34.bindTexture("PrepSampler", x.d, var31);
               var34.bindTexture("DepthSampler", var20, var30);
               if (var22 != null) {
                  var34.bindTexture("HandMaskSampler", var22, var30);
               }

               var34.draw(0, 3);
               BokehRenderPass.metric("drawn");
            } catch (Throwable var52) {
               if (var34 != null) {
                  try {
                     var34.close();
                  } catch (Throwable var40) {
                     var52.addSuppressed(var40);
                  }
               }

               throw var52;
            }

            if (var34 != null) {
               var34.close();
            }
         }

         RenderPass var64 = J(var56, var7, "discs", r, A, true);

         try {
            if (var63) {
               var64.bindTexture("SpotSampler", s.d, var30);
               var64.bindTexture("SceneSampler", z.d, var30);
               var64.bindTexture("PrepSampler", x.d, var30);
               var64.bindTexture("GatherSampler", Z.d, var30);
               var64.draw(0, s.P * s.W * 6);
               BokehRenderPass.metric("drawn");
            }
         } catch (Throwable var46) {
            if (var64 != null) {
               try {
                  var64.close();
               } catch (Throwable var39) {
                  var46.addSuppressed(var39);
               }
            }

            throw var46;
         }

         if (var64 != null) {
            var64.close();
         }

         GpuQuery var65 = p(var56, var5, var6, var57, 3);
         RenderPass var35 = var56.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX bokeh", var19, OptionalInt.empty());

         try {
            var35.setPipeline(var25);
            RenderSystem.bindDefaultUniforms(var35);
            var35.setUniform("PurxiyBokeh", c);
            var35.bindTexture("SceneSampler", z.d, var30);
            var35.bindTexture("DepthSampler", var20, var30);
            var35.bindTexture("PrepSampler", x.d, var30);
            var35.bindTexture("GatherSampler", Z.d, var30);
            var35.bindTexture("SpriteSampler", r.d, var31);
            if (var22 != null) {
               var35.bindTexture("HandMaskSampler", var22, var30);
            }

            var35.draw(0, 3);
            BokehRenderPass.metric("drawn");
         } catch (Throwable var51) {
            if (var35 != null) {
               try {
                  var35.close();
               } catch (Throwable var38) {
                  var51.addSuppressed(var38);
               }
            }

            throw var51;
         }

         if (var35 != null) {
            var35.close();
         }

         p(var56, var5, var6, var65, -1);
      }
   }

   private static GpuQuery Q(CommandEncoder var0, int var1, long var2) {
      if (!S) {
         return null;
      } else {
         GpuQuery var4 = var0.timerQueryBegin();
         E[var1] = var4;
         return var4;
      }
   }

   private static GpuQuery p(CommandEncoder var0, int var1, int var2, GpuQuery var3, int var4) {
      long var5 = 140092478432327L;
      long var7 = var5 ^ 78801815266986L;
      if (var3 != null) {
         var0.timerQueryEnd(var3);
      }

      return var4 < 0 ? null : Q(var0, var4, var7);
   }

   private static RenderPass J(CommandEncoder var0, long var1, String var3, BokehRenderer.BokehProfile var4, RenderPipeline var5, boolean var6) {
      BokehRenderPass.ensureSourceClassInitialized();
      RenderPass var7 = var0.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX bokeh " + var3, var4.d, var6 ? OptionalInt.of(0) : OptionalInt.empty());
      var7.setPipeline(var5);
      RenderSystem.bindDefaultUniforms(var7);
      var7.setUniform("PurxiyBokeh", c);
      return var7;
   }

   private static ByteBuffer S(short var0, long var1, int var3, int var4) {
      float var5 = BokehRenderPass.presenceCurve();
      BokehRenderPass.ensureSourceClassInitialized();
      float var6 = BokehSupport.maxBlur(var4);
      float var7 = BokehRenderPass.B;
      float var8 = BokehSupport.autoScale(BokehRenderPass.p, var4, BokehRenderPass.l);
      float var9 = BokehSupport.distanceBlur(BokehRenderPass.p, var4) / Math.max(var7, 0.001953125F);
      float var10 = var8 + (var9 - var8) * BokehRenderPass.d;
      float var11 = BokehRenderPass.b * 0.5F;
      Std140Builder var12 = Std140Builder.intoBuffer(u.clear())
         .putMat4f(BokehRenderPass.r)
         .putVec4(var3, var4, I, (float)(BokehRenderPass.k % 3600.0))
         .putVec4(x.P, x.W, s.P, s.W)
         .putVec4(BokehRenderPass.O.x, BokehRenderPass.O.y, BokehRenderPass.O.z, var7);
      var12 = var12.putVec4(var10, var6, BokehRenderPass.T, BokehRenderPass.D)
         .putVec4(0.5F, var11, 0.1F + var11 * 0.6F, BokehSupport.tiltBlur(BokehRenderPass.p, var4))
         .putVec4(BokehRenderPass.Z, BokehRenderPass.i, BokehRenderPass.Z == BokehRenderPass.i ? 0.0F : O(BokehRenderPass.E), BokehRenderPass.h);
      float var13 = BokehRenderPass.R;
      float var14 = BokehRenderPass.L;
      BokehRenderPass.ensureSourceClassInitialized();
      Std140Builder var15 = var12.putVec4(var13, var14, var5, 0.0F);
      return var15.get();
   }

   private static float O(float var0) {
      var0 = Math.clamp(var0, 0.0F, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static void y(GpuDevice var0, int var1, char var2, int var3, long var4) {
      long var6 = 12228166193074L;
      long var8 = var6 ^ 56292871656818L;
      BokehRenderPass.ensureSourceClassInitialized();
      if (c == null) {
         c = var0.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX bokeh"), 136, 176L);
         u = MemoryUtil.memAlloc(176);
      }

      if (z != null) {
         long var10 = 12228166193073L;
         int var12 = var10 < 0L ? -1 : 1;
         long var13 = 12228166193073L;
         int var14 = var13 < 0L ? -1 : 1;
         if (l == var1) {
            long var15 = 12228166193073L;
            int var16 = var15 < 0L ? -1 : 1;
            if (F == var3) {
               return;
            }
         }
      }

      S(var8);
      I = BokehSupport.gridFactor(var3);
      int var17 = (var1 + I - 1) / I;
      int var11 = (var3 + I - 1) / I;
      int var12 = (var17 + 7) / 8;
      int var18 = (var11 + 7) / 8;
      int var14 = (var17 + 1) / 2;
      int var19 = (var11 + 1) / 2;
      z = new BokehRenderer.BokehProfile(var0, "scene", var1, var3, false);
      x = new BokehRenderer.BokehProfile(var0, "grid", var17, var11, true);
      M = new BokehRenderer.BokehProfile(var0, "half grid", var14, var19, true);
      g = new BokehRenderer.BokehProfile(var0, "tiles", var12, var18, true);
      a = new BokehRenderer.BokehProfile(var0, "reach", var12, var18, true);
      Z = new BokehRenderer.BokehProfile(var0, "blur", var17, var11, true);
      s = new BokehRenderer.BokehProfile(var0, "lights", var14, var19, true);
      r = new BokehRenderer.BokehProfile(var0, "discs", var17, var11, true);
      l = var1;
      F = var3;
   }

   private static void S(long var0) {
      short var2 = 16013;
      BokehRenderer.BokehProfile[] var3 = new BokehRenderer.BokehProfile[]{z, x, M, g, a, Z, s, r};
      BokehRenderPass.ensureSourceClassInitialized();

      for (BokehRenderer.BokehProfile var6 : var3) {
         long var7 = 61768661392064L;
         int var10 = var7 < 0L ? -1 : 1;
         long var10x = 61768661392064L;
         int var13 = var10x < 0L ? -1 : 1;
         long var13x = 61768661392064L;
         int var14 = var13x < 0L ? -1 : 1;
         if (var6 != null) {
            var6.close();
         }
      }

      long var16 = 61768661392063L;
      int var16x = var16 < 0L ? -1 : 1;
      r = null;
      s = null;
      Z = null;
      a = null;
      g = null;
      M = null;
      x = null;
      z = null;
   }

   private static boolean e(long var0) {
      GpuQuery[] var2 = E;
      BokehRenderPass.ensureSourceClassInitialized();

      for (GpuQuery var5 : var2) {
         long var6 = 137259387265687L;
         int var13 = var6 < 0L ? -1 : 1;
         long var9x = 137259387265687L;
         int var25 = var9x < 0L ? -1 : 1;
         if (var5 != null && var5.getValue().isEmpty()) {
            return false;
         }
      }

      boolean var21 = false;

      for (int var22 = 0; var22 < C.length; var22++) {
         GpuQuery var7 = E[var22];
         long var8 = 137259387265686L;
         int var8x = var8 < 0L ? -1 : 1;
         long var11 = 137259387265687L;
         int var10 = var11 < 0L ? -1 : 1;
         if (var7 != null) {
            w[var22] = w[var22] + var7.getValue().getAsLong() / 1000000.0;
            var7.close();
            E[var22] = null;
            var21 = true;
         }
      }

      long var23 = 137259387265687L;
      int var22x = var23 < 0L ? -1 : 1;
      if (var21) {
         long var24 = 137259387265687L;
         int var251 = var24 < 0L ? -1 : 1;
         if (++p == 240) {
            StringBuilder var12 = new StringBuilder();
            double var13 = 0.0;

            for (int var15 = 0; var15 < C.length; var15++) {
               var13 += w[var15];
               long var16 = 137259387265686L;
               int var19 = var16 < 0L ? -1 : 1;
               long var19x = 137259387265687L;
               int var16x = var19x < 0L ? -1 : 1;
               var12.append(var15 == 0 ? "" : ", ").append(C[var15]).append(' ').append(String.format(Locale.ROOT, "%.3f", w[var15] / p));
            }

            long var25x = 137259387265687L;
            int var13x = var25x < 0L ? -1 : 1;
            ClientServices.LOGGER
               .info("SourceByDevFFnightmare DeObfDeCompByGuideX bokeh: {} ms GPU per frame at {}x{} (grid 1/{}): {}", new Object[]{String.format(Locale.ROOT, "%.3f", var13 / p), l, F, I, var12});
            Arrays.fill(w, 0.0);
            p = 0;
         }
      }

      return true;
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (X != null) {
            ClientCache.release(X);
         }
      } catch (Exception var1034) {
         var0++;
      } finally {
         ;
      }

      try {
         if (b != null) {
            ClientCache.release(b);
         }
      } catch (Exception var1032) {
         var0++;
      } finally {
         ;
      }

      try {
         if (G != null) {
            ClientCache.release(G);
         }
      } catch (Exception var1030) {
         var0++;
      } finally {
         ;
      }

      try {
         if (n != null) {
            ClientCache.release(n);
         }
      } catch (Exception var1028) {
         var0++;
      } finally {
         ;
      }

      try {
         if (t != null) {
            ClientCache.release(t);
         }
      } catch (Exception var1026) {
         var0++;
      } finally {
         ;
      }

      try {
         if (o != null) {
            ClientCache.release(o);
         }
      } catch (Exception var1024) {
         var0++;
      } finally {
         ;
      }

      try {
         if (D != null) {
            ClientCache.release(D);
         }
      } catch (Exception var1022) {
         var0++;
      } finally {
         ;
      }

      try {
         if (v != null) {
            ClientCache.release(v);
         }
      } catch (Exception var1020) {
         var0++;
      } finally {
         ;
      }

      try {
         if (K != null) {
            ClientCache.release(K);
         }
      } catch (Exception var1018) {
         var0++;
      } finally {
         ;
      }

      try {
         if (V != null) {
            ClientCache.release(V);
         }
      } catch (Exception var1016) {
         var0++;
      } finally {
         ;
      }

      try {
         if (A != null) {
            ClientCache.release(A);
         }
      } catch (Exception var1014) {
         var0++;
      } finally {
         ;
      }

      for (int var1 = 0; var1 < E.length; var1++) {
         try {
            if (E[var1] != null) {
               E[var1].close();
            }
         } catch (Exception var1012) {
            var0++;
         } finally {
            E[var1] = null;
         }
      }

      try {
         if (c != null) {
            c.close();
         }
      } catch (Exception var1010) {
         var0++;
      } finally {
         c = null;
      }

      try {
         if (z != null) {
            z.close();
         }
      } catch (Exception var1008) {
         var0++;
      } finally {
         z = null;
      }

      try {
         if (x != null) {
            x.close();
         }
      } catch (Exception var1006) {
         var0++;
      } finally {
         x = null;
      }

      try {
         if (M != null) {
            M.close();
         }
      } catch (Exception var1004) {
         var0++;
      } finally {
         M = null;
      }

      try {
         if (g != null) {
            g.close();
         }
      } catch (Exception var1002) {
         var0++;
      } finally {
         g = null;
      }

      try {
         if (a != null) {
            a.close();
         }
      } catch (Exception var1000) {
         var0++;
      } finally {
         a = null;
      }

      try {
         if (Z != null) {
            Z.close();
         }
      } catch (Exception var998) {
         var0++;
      } finally {
         Z = null;
      }

      try {
         if (s != null) {
            s.close();
         }
      } catch (Exception var996) {
         var0++;
      } finally {
         s = null;
      }

      try {
         if (r != null) {
            r.close();
         }
      } catch (Exception var994) {
         var0++;
      } finally {
         r = null;
      }

      try {
         if (u != null) {
            MemoryUtil.memFree(u);
         }
      } catch (Exception var9921) {
         var0++;
      } finally {
         u = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

   private static final class BokehProfile {
      final GpuTexture a;
      final GpuTextureView d;
      final int P;
      final int W;
      private static final String b = "SourceByDevFFnightmare DeObfDeCompByGuideX bokeh ";

      BokehProfile(GpuDevice var1, String var2, int var3, int var4, boolean var5) {
         int var6 = 4 | (var5 ? 8 : 1);
         this.a = var1.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX bokeh " + var2, var6, TextureFormat.RGBA8, var3, var4, 1, 1);
         this.d = var1.createTextureView(this.a);
         this.P = var3;
         this.W = var4;
      }

      void close() {
         this.d.close();
         this.a.close();
      }
   }
}
