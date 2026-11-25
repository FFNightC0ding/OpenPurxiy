package dev.purxiy.modules.cyberspace;

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
import dev.purxiy.loader.fabric.client.ClientSupport3;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.OptionalLong;
import net.minecraft.class_10789;
import net.minecraft.class_12137;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import org.lwjgl.system.MemoryUtil;

final class CyberspaceRenderer {
   private static final int j = 336;
   private static final RenderPipeline J = E(28269077807663L, false);
   private static final RenderPipeline l = E(28269077807663L, true);
   private static final RenderPipeline H = K(105912506823316L, false);
   private static final RenderPipeline A = K(105912506823316L, true);
   private static final int D = 4;
   private static final int R = 124;
   private static final int I = 8288;
   private static GpuBuffer z;
   private static ByteBuffer P;
   private static GpuBuffer M;
   private static ByteBuffer f;
   private static final double[] B = new double[9];
   private static GpuTexture Y;
   private static GpuTextureView v;
   private static int q = 0;
   private static int T = 0;
   private static float[] U = new float[9];
   private static final float[] a = new float[9];
   private static boolean h;
   private static boolean S;
   private static GpuQuery W;
   private static double V;
   private static int e = 0;
   private static final long[] d = new long[]{
      -4109423895650627686L,
      -6286805034665756488L,
      -3788695341878540215L,
      6084269774344694708L,
      5879897407116357266L,
      1063746325050681820L,
      -6437327785320558314L,
      7976892549224348817L,
      -922242107423679883L,
      -7070437914684273164L,
      2659415583656932050L,
      8014578898210795411L,
      -5990115365680078186L,
      -7460620861396163728L,
      -8238248667682401231L,
      -6673142202010280279L,
      -5834289220495823563L,
      4199970435669142053L,
      8549343848844375361L,
      -7416287647396998528L,
      1110376936710610069L
   };
   private static final Integer[] g = new Integer[21];
   private static final long[] i = new long[]{329606016950855633L, 311353099279855692L};
   private static final Long[] k = new Long[2];

   private CyberspaceRenderer() {
   }

   private static RenderPipeline E(long var0, boolean var2) {
      Builder var3 = RenderPipeline.builder(new Snippet[0]);
      long var4 = 28269077807663L;
      int var7 = var4 < 0L ? -1 : 1;
      Builder var7x = var3.withLocation(ClientServices.id(var2 ? "pipeline/cyberspace_masked" : "pipeline/cyberspace"))
         .withVertexShader(ClientServices.id("core/cyberspace"))
         .withFragmentShader(ClientServices.id("core/cyberspace"))
         .withUniform("Fog", class_10789.field_60031)
         .withUniform("PurxiyCyberspace", class_10789.field_60031)
         .withSampler("SceneSampler")
         .withSampler("DepthSampler")
         .withoutBlend()
         .withDepthWrite(false)
         .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(class_290.field_60033, class_5596.field_27379);
      if (var2) {
         var7x.withShaderDefine("MASKED").withSampler("HandMaskSampler");
      }

      return var7x.build();
   }

   private static RenderPipeline K(long var0, boolean var2) {
      Builder var3 = RenderPipeline.builder(new Snippet[0]);
      long var4 = 105912506823316L;
      int var7 = var4 < 0L ? -1 : 1;
      Builder var7x = var3.withLocation(ClientServices.id(var2 ? "pipeline/cyberspace_flight_masked" : "pipeline/cyberspace_flight"))
         .withVertexShader(ClientServices.id("core/cyberspace_flight"))
         .withFragmentShader(ClientServices.id("core/cyberspace_flight"))
         .withUniform("PurxiyCyberspace", class_10789.field_60031)
         .withUniform("PurxiyCyberspaceFlight", class_10789.field_60031)
         .withSampler("DepthSampler")
         .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
         .withDepthWrite(false)
         .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(class_290.field_60033, class_5596.field_27379);
      if (var2) {
         var7x.withShaderDefine("MASKED").withSampler("HandMaskSampler");
      }

      return var7x.build();
   }

   static void setTiming(boolean var0) {
      S = var0;
      V = 0.0;
      e = 0;
   }

   static void draw(class_310 var0) {
      long var1 = 132514408913544L;
      long var3 = var1 ^ 30087086898789L;
      int var5 = (int)((var1 ^ 30087086898789L) >>> 48);
      int var6 = (int)((var1 ^ 30087086898789L) << 16 >>> 32);
      int var7 = (int)(var3 << 48 >>> 48);
      var3 = var1 ^ 126958961516715L;
      long var8 = var1 ^ 109380640070622L;
      long var10 = var1 ^ 49947772096634L;
      long var12 = var1 ^ 6305262508139L;
      class_276 var14 = var0.method_1522();
      GpuTexture var15 = var14.method_30277();
      GpuTextureView var16 = var14.method_71639();
      GpuTextureView var17 = var14.method_71640();
      if (var15 != null && var16 != null && var17 != null && CyberspaceRenderer2.W != null) {
         GpuDevice var18 = RenderSystem.getDevice();
         GpuTextureView var19 = CyberspaceRenderer2.w ? ClientSupport3.handMask() : null;
         RenderPipeline var20 = var19 != null ? l : J;
         if (var18.precompilePipeline(var20).isValid()) {
            int var21 = var14.field_1482;
            int var22 = var14.field_1481;
            o(var18, var10, var21, var22);
            CommandEncoder var23 = var18.createCommandEncoder();
            var23.copyTextureToTexture(var15, Y, 0, 0, 0, 0, 0, var21, var22);
            var23.writeToBuffer(z.slice(), Q(var21, var22, var12, var19 != null));
            GpuQuery var24 = S ? l(var23, (char)var5, var6, var7) : null;
            class_12137 var25 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            RenderPass var26 = var23.createRenderPass(() -> "Purxiy cyberspace", var16, OptionalInt.empty());

            try {
               var26.setPipeline(var20);
               RenderSystem.bindDefaultUniforms(var26);
               var26.setUniform("Fog", CyberspaceRenderer2.W);
               var26.setUniform("PurxiyCyberspace", z);
               var26.bindTexture("SceneSampler", v, var25);
               var26.bindTexture("DepthSampler", var17, var25);
               if (var19 != null) {
                  var26.bindTexture("HandMaskSampler", var19, var25);
               }

               var26.draw(0, 3);
               CyberspaceRenderer2.metric("drawn");
            } catch (Throwable var35) {
               if (var26 != null) {
                  try {
                     var26.close();
                  } catch (Throwable var33) {
                     var35.addSuppressed(var33);
                  }
               }

               throw var35;
            }

            if (var26 != null) {
               var26.close();
            }

            try {
               l(var18, var23, var16, var17, var19, var8, var25);
            } finally {
               if (var24 != null) {
                  var23.timerQueryEnd(var24);
                  W = var24;
               }
            }
         }
      }
   }

   private static void l(GpuDevice var0, CommandEncoder var1, GpuTextureView var2, GpuTextureView var3, GpuTextureView var4, long var5, class_12137 var7) {
      long var8 = 129837988206336L;
      byte var10 = 0;
      short var11 = 30230;
      int var12 = (int)(var8 << 32 >>> 32);
      CyberspaceRenderer2.ensureSourceClassInitialized();
      if (!(CyberspaceRenderer2.K <= 0.001F) && (!CyberspaceUpdater.m.isEmpty() || !CyberspaceUpdater.b.isEmpty())) {
         RenderPipeline var13 = var4 != null ? A : H;
         if (var0.precompilePipeline(var13).isValid()) {
            if (M == null) {
               M = var0.createBuffer(ClientServices.label(() -> "Purxiy cyberspace flight"), 136, 8288L);
               f = MemoryUtil.memCalloc(8288);
            }

            Std140Builder var14 = Std140Builder.intoBuffer(f.clear())
               .putMat4f(CyberspaceRenderer2.e)
               .putVec4(CyberspaceRenderer2.N.x, CyberspaceRenderer2.N.y, CyberspaceRenderer2.N.z, 0.0F)
               .putVec4(CyberspaceRenderer2.O.x, CyberspaceRenderer2.O.y, CyberspaceRenderer2.O.z, 0.0F);
            int var15 = 0;
            Iterator var16 = CyberspaceUpdater.m.iterator();

            while (var16.hasNext()) {
               long var17 = 30778817184085L;
               int var49 = var17 < 0L ? -1 : 1;
               CyberspaceUpdater.TypeQ var20 = (CyberspaceUpdater.TypeQ)var16.next();
               long var21 = 30778817184085L;
               int var23 = var21 < 0L ? -1 : 1;
               if (var15 >= 124) {
                  break;
               }

               float var24 = m((char)var10, (char)var11, var12, var20.m, var20.T, var20.j) * CyberspaceRenderer2.K * k(0.0F, 0.25F, var20.w);
               long var25 = 30778817184086L;
               int var25x = var25 < 0L ? -1 : 1;
               float var28;
               int var29 = (var28 = var24 - 0.001F) == 0.0F ? 0 : (var28 < 0.0F ? -1 : 1);
               long var30 = 30778817184085L;
               int var29x = var30 < 0L ? -1 : 1;
               if (var29 <= 0) {
                  long var33 = 30778817184086L;
                  int var60 = var33 < 0L ? -1 : 1;
               } else {
                  CyberspaceUpdater.trail(var20, B);
                  long var58 = 30778817184086L;
                  int var67 = var58 < 0L ? -1 : 1;
                  float var36 = var20.s >= 0.0F ? 1.0F - k(0.0F, 0.15F, var20.s) : 1.0F;
                  var14.putVec4(g(B[0]), S(B[1]), M(B[2]), var20.l)
                     .putVec4(g(B[3]), S(B[4]), M(B[5]), var24)
                     .putVec4(g(B[6]), S(B[7]), M(B[8]), 1.0F)
                     .putVec4(var20.N, 0.0F, 0.0F, var36);
                  var15++;
               }
            }

            long var46 = 30778817184086L;
            int var47 = var46 < 0L ? -1 : 1;
            var16 = CyberspaceUpdater.b.iterator();

            while (true) {
               var47 = var16.hasNext() ? 1 : 0;
               long var50 = 30778817184086L;
               int var491 = var50 < 0L ? -1 : 1;
               if (var47 == 0) {
                  break;
               }

               CyberspaceUpdater.TypeS var51 = (CyberspaceUpdater.TypeS)var16.next();
               long var54 = 30778817184085L;
               int var561 = var54 < 0L ? -1 : 1;
               long var56x = 30778817184085L;
               int var601 = var56x < 0L ? -1 : 1;
               if (var15 >= 124) {
                  long var57 = 30778817184085L;
                  int var66 = var57 < 0L ? -1 : 1;
                  break;
               }

               float var31 = m((char)var10, (char)var11, var12, var51.g, var51.W, var51.U) * CyberspaceRenderer2.K;
               float var32 = k(0.0F, 0.4F, var51.H) * (1.0F - k(var51.q - 0.9F, var51.q, var51.H));
               long var59 = 30778817184085L;
               int var69 = var59 < 0L ? -1 : 1;
               float var60x;
               var47 = (var60x = var31 * var32 - 0.001F) == 0.0F ? 0 : (var60x < 0.0F ? -1 : 1);
               long var37 = 30778817184085L;
               int var40 = var37 < 0L ? -1 : 1;
               if (var47 > 0) {
                  float var40x = g(var51.g);
                  float var41 = S(var51.W);
                  float var42 = M(var51.U);
                  var14.putVec4(var40x, var41, var42, var51.t * (0.4F + 0.6F * var32))
                     .putVec4(var40x, var41, var42, var31)
                     .putVec4(var40x, var41, var42, 0.0F)
                     .putVec4(var51.K, 1.0F, var51.r, var32);
                  var15++;
               }
            }

            long var49x = 30778817184086L;
            int var21x = var49x < 0L ? -1 : 1;
            if (var15 != 0) {
               ByteBuffer var22x = var14.get();
               var1.writeToBuffer(M.slice(0L, var22x.remaining()), var22x);
               RenderPass var23x = var1.createRenderPass(() -> "Purxiy cyberspace flight", var2, OptionalInt.empty());

               try {
                  var23x.setPipeline(var13);
                  RenderSystem.bindDefaultUniforms(var23x);
                  var23x.setUniform("PurxiyCyberspace", z);
                  var23x.setUniform("PurxiyCyberspaceFlight", M);
                  int var52 = 44114;
                  long var55 = 30778817184085L;
                  int var27 = var55 < 0L ? -1 : 1;
                  var52 = 1081562475;
                  var27 = 1081540950;
                  var23x.bindTexture("DepthSampler", var3, var7);
                  if (var4 != null) {
                     var23x.bindTexture("HandMaskSampler", var4, var7);
                  }

                  var23x.draw(0, var15 * 18);
                  CyberspaceRenderer2.metric("drawn");
               } catch (Throwable var38) {
                  if (var23x != null) {
                     try {
                        var23x.close();
                     } catch (Throwable var371) {
                        var38.addSuppressed(var371);
                     }
                  }

                  throw var38;
               }

               if (var23x != null) {
                  var23x.close();
               }
            }
         }
      }
   }

   private static float m(char var0, char var1, int var2, double var3, double var5, double var7) {
      double var9 = var3 - CyberspaceRenderer2.x;
      CyberspaceRenderer2.ensureSourceClassInitialized();
      double var11 = var5 - CyberspaceRenderer2.p;
      double var13 = var7 - CyberspaceRenderer2.j;
      float var15 = (float)Math.sqrt(var9 * var9 + var11 * var11 + var13 * var13);
      float var16 = CyberspaceRenderer2.U >= CyberspaceRenderer2.F ? 1.0F : 1.0F - k(CyberspaceRenderer2.U - 0.6F, CyberspaceRenderer2.U + 0.6F, var15);
      float var17 = CyberspaceRenderer2.d <= 0.0F ? 1.0F : k(CyberspaceRenderer2.d - 0.6F, CyberspaceRenderer2.d + 0.6F, var15);
      return var16 * var17;
   }

   private static float g(double var0) {
      return (float)(var0 - CyberspaceRenderer2.b);
   }

   private static float S(double var0) {
      return (float)(var0 - CyberspaceRenderer2.B);
   }

   private static float M(double var0) {
      return (float)(var0 - CyberspaceRenderer2.v);
   }

   private static ByteBuffer Q(int var0, int var1, long var2, boolean var4) {
      long var5 = 6913732406772L;
      CyberspaceModule var7 = CyberspaceRenderer2.I;
      V(CyberspaceLoader.resolve((String)var7.I.get(), var7.O.argb()), var5);
      int var8 = (int)Math.floor(CyberspaceRenderer2.b);
      CyberspaceRenderer2.ensureSourceClassInitialized();
      int var9 = (int)Math.floor(CyberspaceRenderer2.B);
      int var10 = (int)Math.floor(CyberspaceRenderer2.v);
      float var11 = CyberspaceRenderer2.F;
      float var12 = CyberspaceRenderer2.U >= var11 ? 1.0E9F : CyberspaceRenderer2.U;
      float var13 = CyberspaceRenderer2.d <= 0.0F ? -100.0F : CyberspaceRenderer2.d;
      float var14 = k(0.55F * var11, var11, CyberspaceRenderer2.U >= var11 ? var11 : CyberspaceRenderer2.U)
         * (1.0F - k(0.55F * var11, var11, Math.max(CyberspaceRenderer2.d, 0.0F)));
      float var15 = CyberspaceRenderer2.P;
      float var16 = CyberspaceRenderer2.l;
      Std140Builder var17 = Std140Builder.intoBuffer(P.clear()).putMat4f(CyberspaceRenderer2.y).putIVec4(var8, var9, var10, var4 ? 1 : 0);
      float var18 = (float)(CyberspaceRenderer2.b - var8);
      float var19 = (float)(CyberspaceRenderer2.B - var9);
      float var20 = (float)(CyberspaceRenderer2.v - var10);
      long var21 = 137685455586018L;
      int var24 = var21 < 0L ? -1 : 1;
      Std140Builder var24x = var17.putVec4(var18, var19, var20, (float)(CyberspaceRenderer2.X % 3600.0))
         .putVec4(var0, var1, CyberspaceRenderer2.EH, CyberspaceRenderer2.R)
         .putVec4(U[0], U[1], U[2], CyberspaceRenderer2.q)
         .putVec4(U[3], U[4], U[5], CyberspaceRenderer2.k)
         .putVec4(U[6], U[7], U[8], CyberspaceRenderer2.E)
         .putVec4(CyberspaceRenderer2.S, 0.0F, CyberspaceRenderer2.z, (float)Math.floor(CyberspaceRenderer2.X * 18.0) % 997.0F)
         .putVec4(-((float)Math.sin(var15)), (float)Math.cos(var15), 0.0F, CyberspaceRenderer2.r ? 1.0F : 0.0F)
         .putVec4(-((float)Math.sin(var16)), (float)Math.cos(var16), 0.0F, 0.0F)
         .putVec4(
            (float)(CyberspaceRenderer2.x - CyberspaceRenderer2.b),
            (float)(CyberspaceRenderer2.p - CyberspaceRenderer2.B),
            (float)(CyberspaceRenderer2.j - CyberspaceRenderer2.v),
            var12
         )
         .putVec4(var13, var14, var11, 0.0F)
         .putIVec4(CyberspaceRenderer2.f, CyberspaceRenderer2.H, CyberspaceRenderer2.EP, CyberspaceRenderer2.i ? 1 : 0)
         .putVec4(CyberspaceRenderer2.n, CyberspaceRenderer2.D, CyberspaceRenderer2.w ? 1.0F : 0.0F, 0.0F);

      for (int var25 = 0; var25 < 4; var25++) {
         double var26 = CyberspaceRenderer2.M[var25 * 4 + 3];
         long var28 = 137685455586018L;
         int var29 = var28 < 0L ? -1 : 1;
         var24x.putVec4(
            (float)(CyberspaceRenderer2.M[var25 * 4] - CyberspaceRenderer2.b),
            (float)(CyberspaceRenderer2.M[var25 * 4 + 1] - CyberspaceRenderer2.B),
            (float)(CyberspaceRenderer2.M[var25 * 4 + 2] - CyberspaceRenderer2.v),
            (float)var26
         );
      }

      return var24x.get();
   }

   private static void V(CyberspaceLoader var0, long var1) {
      long var3 = 122209926681081L;
      CyberspaceRenderer2.ensureSourceClassInitialized();
      D(0, var0.l, var3);
      D(3, var0.M, var3);
      D(6, var0.Q, var3);
      float var5 = h ? 0.12F : 1.0F;
      h = true;

      for (int var6 = 0; var6 < U.length; var6++) {
         U[var6] = U[var6] + (a[var6] - U[var6]) * var5;
      }

      long var9 = 6913732406771L;
      int var8 = var9 < 0L ? -1 : 1;
   }

   private static void D(int var0, int var1, long var2) {
      a[var0] = (var1 >>> 16 & 0xFF) / 255.0F;
      a[var0 + 1] = (var1 >>> 8 & 0xFF) / 255.0F;
      a[var0 + 2] = (var1 & 0xFF) / 255.0F;
   }

   private static float k(float var0, float var1, float var2) {
      float var3 = Math.max(0.0F, Math.min((var2 - var0) / (var1 - var0), 1.0F));
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   private static void o(GpuDevice var0, long var1, int var3, int var4) {
      CyberspaceRenderer2.ensureSourceClassInitialized();
      if (z == null) {
         z = var0.createBuffer(ClientServices.label(() -> "Purxiy cyberspace"), 136, 336L);
         P = MemoryUtil.memAlloc(336);
      }

      long var5 = 94455817554674L;
      int var8 = var5 < 0L ? -1 : 1;
      if (Y != null) {
         long var8x = 94455817554674L;
         int var9 = var8x < 0L ? -1 : 1;
         if (q == var3 && T == var4) {
            return;
         }
      }

      if (Y != null) {
         v.close();
         Y.close();
      }

      Y = var0.createTexture(() -> "Purxiy cyberspace scene", 5, TextureFormat.RGBA8, var3, var4, 1, 1);
      v = var0.createTextureView(Y);
      q = var3;
      T = var4;
   }

   private static GpuQuery l(CommandEncoder var0, char var1, int var2, int var3) {
      CyberspaceRenderer2.ensureSourceClassInitialized();
      if (W != null) {
         OptionalLong var4 = W.getValue();
         int var5 = 0;
         if (var4.isEmpty()) {
            return null;
         }

         W.close();
         W = null;
         V = V + var4.getAsLong() / 1000000.0;
         var5 = 1675122789;
         if (++e == 240) {
            ClientServices.LOGGER.info("Purxiy cyberspace: {} ms GPU per frame at {}x{}", new Object[]{String.format(Locale.ROOT, "%.3f", V / e), q, T});
            V = 0.0;
            e = 0;
         }
      }

      return var0.timerQueryBegin();
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (J != null) {
            ClientCache.release(J);
         }
      } catch (Exception var275) {
         var0++;
      } finally {
         ;
      }

      try {
         if (l != null) {
            ClientCache.release(l);
         }
      } catch (Exception var273) {
         var0++;
      } finally {
         ;
      }

      try {
         if (H != null) {
            ClientCache.release(H);
         }
      } catch (Exception var271) {
         var0++;
      } finally {
         ;
      }

      try {
         if (A != null) {
            ClientCache.release(A);
         }
      } catch (Exception var269) {
         var0++;
      } finally {
         ;
      }

      try {
         if (v != null) {
            v.close();
         }
      } catch (Exception var267) {
         var0++;
      } finally {
         v = null;
      }

      try {
         if (W != null) {
            W.close();
         }
      } catch (Exception var265) {
         var0++;
      } finally {
         W = null;
      }

      try {
         if (z != null) {
            z.close();
         }
      } catch (Exception var263) {
         var0++;
      } finally {
         z = null;
      }

      try {
         if (M != null) {
            M.close();
         }
      } catch (Exception var261) {
         var0++;
      } finally {
         M = null;
      }

      try {
         if (Y != null) {
            Y.close();
         }
      } catch (Exception var259) {
         var0++;
      } finally {
         Y = null;
      }

      try {
         if (P != null) {
            MemoryUtil.memFree(P);
         }
      } catch (Exception var257) {
         var0++;
      } finally {
         P = null;
      }

      try {
         if (f != null) {
            MemoryUtil.memFree(f);
         }
      } catch (Exception var255) {
         var0++;
      } finally {
         f = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 24415) & 32767;
      if (k[var3] == null) {
         k[var3] = i[var3] ^ var1;
      }

      return k[var3];
   }
}
