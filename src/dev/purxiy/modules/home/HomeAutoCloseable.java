package dev.purxiy.modules.home;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline.Snippet;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.systems.RenderSystem.class_5590;
import com.mojang.blaze3d.textures.AddressMode;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.pipeline.BlurPipeline;
import dev.purxiy.render.pipeline.SkyVertexFormat;
import dev.purxiy.ui.Theme;
import java.nio.ByteBuffer;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import net.minecraft.class_1059;
import net.minecraft.class_10725;
import net.minecraft.class_10789;
import net.minecraft.class_11697;
import net.minecraft.class_12137;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_6367;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryUtil;

final class HomeAutoCloseable implements AutoCloseable {
   static final VertexFormat n = VertexFormat.builder()
      .add("Position", VertexFormatElement.POSITION)
      .add("UV0", VertexFormatElement.UV0)
      .add("Color", VertexFormatElement.COLOR)
      .add("Grow", SkyVertexFormat.POS_LOCAL)
      .build();
   private static final RenderPipeline P = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/home_sky"))
      .withVertexShader(ClientServices.id("core/home_sky"))
      .withFragmentShader(ClientServices.id("core/home_sky"))
      .withUniform("HomeFrame", class_10789.field_60031)
      .withSampler("CelestialSampler")
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static final RenderPipeline M = P("home_solid", 88976651066837L, "SOLID", false);
   private static final RenderPipeline V = P("home_water", 88976651066837L, "WATER", true);
   private static final RenderPipeline u = P("home_clouds", 88976651066837L, "CLOUDS", true);
   private static final RenderPipeline m = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/home_particles"))
      .withVertexShader(ClientServices.id("core/home_particle"))
      .withFragmentShader(ClientServices.id("core/home_particle"))
      .withUniform("HomeFrame", class_10789.field_60031)
      .withSampler("Sampler0")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(n, class_5596.field_27382)
      .build();
   private static final RenderPipeline g = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/home_composite"))
      .withVertexShader("core/screenquad")
      .withFragmentShader(ClientServices.id("core/home_composite"))
      .withUniform("HomeComposite", class_10789.field_60031)
      .withSampler("SceneSampler")
      .withSampler("BlurSampler")
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static final int B = 368;
   private static final int O = 4;
   static final int b = 0;
   static final int q = 1;
   static final int E = 2;
   static final int Y = 3;
   final HomeAutoCloseable.HomeLease x = new HomeAutoCloseable.HomeLease("terrain");
   final HomeAutoCloseable.HomeLease k = new HomeAutoCloseable.HomeLease("water");
   HomeAutoCloseable.HomeLease X = new HomeAutoCloseable.HomeLease("forest");
   HomeAutoCloseable.HomeLease U = new HomeAutoCloseable.HomeLease("old forest");
   final HomeAutoCloseable.HomeLease d = new HomeAutoCloseable.HomeLease("clouds");
   final HomeAutoCloseable.HomeLease D = new HomeAutoCloseable.HomeLease("particles");
   private class_6367 A;
   private final BlurPipeline h = new BlurPipeline();
   private GpuBuffer t;
   private GpuBuffer G;
   private GpuBuffer p;
   private ByteBuffer Z;
   private int a;
   private static final String[] e = new String[52];
   private static final Integer[] i = new Integer[14];
   private static final long[] j = new long[]{7613868609598900869L, 6217830737918024691L, 2334061076209702607L, 2852311656254319253L, 4304165463177710582L};
   private static final Long[] l = new Long[5];

   private static RenderPipeline P(String var0, long var1, String var3, boolean var4) {
      Builder var5 = RenderPipeline.builder(new Snippet[0])
         .withLocation(ClientServices.id("pipeline/" + var0))
         .withVertexShader(ClientServices.id("core/home_block"))
         .withFragmentShader(ClientServices.id("core/home_block"))
         .withShaderDefine(var3)
         .withUniform("HomeFrame", class_10789.field_60031)
         .withUniform("HomeDraw", class_10789.field_60031)
         .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(n, class_5596.field_27382);
      boolean var6 = var3.equals("CLOUDS");
      long var7 = 88976651066836L;
      int var10 = var7 < 0L ? -1 : 1;
      if (!var6) {
         var5.withSampler("Sampler0");
      }

      if (var4) {
         var5.withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA).withDepthWrite(false);
         long var10x = 88976651066837L;
         int var11 = var10x < 0L ? -1 : 1;
      } else {
         var5.withoutBlend().withDepthWrite(true);
      }

      return var5.build();
   }

   void render(HomeAutoCloseable.HomeWorker var1) {
      long var2 = 27786686954494L;
      long var4 = var2 ^ 76458410862762L;
      long var6 = var2 ^ 16180093475065L;
      long var8 = var2 ^ 3282996450842L;
      int var10 = (int)((var2 ^ 3282996450842L) >>> 32);
      int var11 = (int)((var2 ^ 3282996450842L) << 32 >>> 48);
      int var12 = (int)(var8 << 48 >>> 48);
      class_310 var13 = class_310.method_1551();
      class_276 var14 = var13.method_1522();
      GpuTextureView var15 = var14.method_71639();
      if (var15 != null) {
         GpuDevice var16 = RenderSystem.getDevice();

         for (RenderPipeline var20 : new RenderPipeline[]{P, M, V, u, m, g}) {
            if (!var16.precompilePipeline(var20).isValid()) {
               return;
            }
         }

         float var44 = var14.field_1481 <= 1200 ? 2.0F : (var14.field_1481 <= 1600 ? 1.5F : 1.0F);
         int var45 = Math.max(1, Math.round(var14.field_1482 * var44));
         int var46 = Math.max(1, Math.round(var14.field_1481 * var44));
         int var47 = var16.getMaxTextureSize();
         if (var45 > var47 || var46 > var47) {
            var45 = var14.field_1482;
            var46 = var14.field_1481;
         }

         this.b(var16, var10, (short)var11, (short)var12, var45, var46);
         CommandEncoder var21 = var16.createCommandEncoder();
         this.r(var21, var1, var45, var46, var4);
         class_11697 var22 = var13.method_72703();
         class_1059 var23 = var22.method_73025(class_10725.field_56382);
         class_1059 var24 = var22.method_73025(class_10725.field_56389);
         GpuTextureView var25 = null;

         try {
            var25 = var22.method_73025(class_10725.field_63647).method_71659();
         } catch (RuntimeException var41) {
         }

         Object var26 = var1.X != null ? var1.X.d : null;
         class_12137 var27 = RenderSystem.getSamplerCache()
            .method_75293(AddressMode.CLAMP_TO_EDGE, AddressMode.CLAMP_TO_EDGE, FilterMode.NEAREST, FilterMode.NEAREST, true);
         class_12137 var28 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
         int var29 = Math.max(Math.max(this.x.F, this.X.F), Math.max(this.U.F, Math.max(this.D.F, Math.max(this.k.F, this.d.F))));
         class_5590 var30 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
         GpuBuffer var31 = var30.method_68274(Math.max(6, var29 * 6));
         class_6367 var32 = this.A;
         RenderPass var33 = var21.createRenderPass(
            () -> "SourceByDevFFnightmare DeObfDeCompByGuideX home world", var32.method_71639(), OptionalInt.of(-16777216), var32.method_71640(), OptionalDouble.of(1.0)
         );

         try {
            var33.setPipeline(P);
            var33.setUniform("HomeFrame", this.t);
            var33.bindTexture("CelestialSampler", var25 != null ? var25 : var23.method_71659(), var28);
            int var34 = 1751470778;
            var33.draw(0, 3);
            int var35 = (int)var2;
            HomeUpdater.metric("drawn");
            if (var26 != null) {
               var33.setPipeline(M);
               var33.setUniform("HomeFrame", this.t);
               var33.bindTexture("Sampler0", var23.method_71659(), var27);
               var33.setIndexBuffer(var31, var30.method_31924());
               this.q(var33, var6, this.x, var26, 0);
               this.q(var33, var6, this.U, var26, 2);
               this.q(var33, var6, this.X, var26, 1);
               if (this.k.drawable(var26)) {
                  var33.setPipeline(V);
                  var33.setUniform("HomeFrame", this.t);
                  var33.bindTexture("Sampler0", var23.method_71659(), var27);
                  var33.setIndexBuffer(var31, var30.method_31924());
                  this.q(var33, var6, this.k, var26, 0);
               }
            }

            if (this.d.F > 0 && this.d.C != null) {
               var33.setPipeline(u);
               var33.setUniform("HomeFrame", this.t);
               var33.bindTexture("Sampler0", var23.method_71659(), var28);
               var33.setIndexBuffer(var31, var30.method_31924());
               var33.setUniform("HomeDraw", this.G.slice(3L * this.a, 16L));
               var34 = 1751470778;
               var33.setVertexBuffer(0, this.d.C);
               var35 = (int)var2;
               var33.drawIndexed(0, 0, this.d.F * 6, 1);
               HomeUpdater.metric("drawn");
            }

            if (this.D.F > 0 && this.D.C != null) {
               var33.setPipeline(m);
               var33.setUniform("HomeFrame", this.t);
               var33.bindTexture("Sampler0", var24.method_71659(), var28);
               var34 = 1751470778;
               var33.setIndexBuffer(var31, var30.method_31924());
               var35 = (int)var2;
               var33.setVertexBuffer(0, this.D.C);
               var33.drawIndexed(0, 0, this.D.F * 6, 1);
               HomeUpdater.metric("drawn");
            }
         } catch (Throwable var43) {
            if (var33 != null) {
               try {
                  var33.close();
               } catch (Throwable var40) {
                  var43.addSuppressed(var40);
               }
            }

            throw var43;
         }

         if (var33 != null) {
            var33.close();
         }

         GpuTextureView var48 = var32.method_71639();
         GpuTextureView var51 = var48;
         if (var1.m > 0.004F) {
            byte var54 = 4;
            var51 = this.h.run(var48, var54, 0.8F + 2.6F * var1.m);
         }

         class_12137 var55 = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
         RenderPass var36 = var21.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX home composite", var15, OptionalInt.empty());

         try {
            var36.setPipeline(g);
            var36.setUniform("HomeComposite", this.p);
            var36.bindTexture("SceneSampler", var48, var55);
            var36.bindTexture("BlurSampler", var51, var55);
            var36.draw(0, 3);
            HomeUpdater.metric("drawn");
         } catch (Throwable var42) {
            if (var36 != null) {
               try {
                  var36.close();
               } catch (Throwable var39) {
                  var42.addSuppressed(var39);
               }
            }

            throw var42;
         }

         if (var36 != null) {
            var36.close();
         }
      }
   }

   private void q(RenderPass var1, long var2, HomeAutoCloseable.HomeLease var4, Object var5, int var6) {
      int var7 = var4.drawable(var5) ? 1 : 0;
      long var8 = 26331087872775L;
      int var10 = var8 < 0L ? -1 : 1;
      if (var7 != 0) {
         var1.setUniform("HomeDraw", this.G.slice((long)var6 * this.a, 16L));
         var7 = 1356597848;
         var1.setVertexBuffer(0, var4.C);
         var10 = 1356603251;
         var1.drawIndexed(0, 0, var4.F * 6, 1);
         HomeUpdater.metric("drawn");
      }
   }

   private void r(CommandEncoder var1, HomeAutoCloseable.HomeWorker var2, int var3, int var4, long var5) {
      long var7 = 73616875743921L;
      HomePalette.HomeColorSet var9 = var2.v;
      HomeGuiBridge.ensureSourceClassInitialized();
      float[] var10 = HomePalette.direction(var9.y, var9.n);
      float[] var11 = HomePalette.direction(var9.r, var9.v);
      float[] var12 = var2.X != null ? var2.X.s : new float[4];
      float[] var13 = var2.X != null ? var2.X.p : new float[4];
      Std140Builder var14 = Std140Builder.intoBuffer(this.Z.clear())
         .putMat4f(var2.s)
         .putMat4f(var2.Y)
         .putVec4(var2.z, var2.S, var2.I, var2.k)
         .putVec4(var9.S, var9.k, 0.0F, var9.d)
         .putVec4(var9.m[0], var9.m[1], var9.m[2], 1.0F)
         .putVec4(var9.L[0], var9.L[1], var9.L[2], var2.F)
         .putVec4(var9.V[0], var9.V[1], var9.V[2], 0.0F)
         .putVec4(var9.e[0], var9.e[1], var9.e[2], 1.0F)
         .putVec4(var9.Q[0], var9.Q[1], var9.Q[2], 1.0F)
         .putVec4(var9.Y[0], var9.Y[1], var9.Y[2], var9.Z)
         .putVec4(var10[0], var10[1], var10[2], 0.16F)
         .putVec4(var11[0], var11[1], var11[2], 0.11F)
         .putVec4(var12[0], var12[1], var12[2], var12[3])
         .putVec4(var13[0], var13[1], var13[2], var13[3])
         .putVec4(var2.U, var2.l, var2.G, var2.W)
         .putVec4(var2.T, var2.o, var2.d, var9.i)
         .putVec4(var9.A[0], var9.A[1], var9.A[2], var9.w);
      var1.writeToBuffer(this.t.slice(), var14.get());
      ByteBuffer var15 = this.Z.clear();
      var15.limit(this.a * 4);
      E(var7, var15, 0 * this.a, 1000000.0F, 0.0F, 0.0F);
      E(var7, var15, 1 * this.a, var2.L, 1.0F, 0.0F);
      E(var7, var15, 2 * this.a, var2.i, -1.0F, var2.u);
      E(var7, var15, 3 * this.a, 0.0F, 0.0F, 0.0F);
      var15.position(0);
      var1.writeToBuffer(this.G.slice(0L, this.a * 4L), var15);
      int var16 = Theme.tint(-11713178);
      int var17 = Theme.tint(-16448758);
      Std140Builder var18 = Std140Builder.intoBuffer(this.Z.clear())
         .putVec4(var2.m, var2.D, var2.M, var2.q)
         .putVec4(var3, var4, 0.0F, 0.0F)
         .putVec4(0.3F * BlendMode.red(var16) / 77.0F, 0.27F * BlendMode.green(var16) / 69.0F, 0.4F * BlendMode.blue(var16) / 102.0F, 0.0F)
         .putVec4(0.018F * BlendMode.red(var17) / 5.0F, 0.012F * BlendMode.green(var17) / 3.0F, 0.04F * BlendMode.blue(var17) / 10.0F, 0.0F);
      var1.writeToBuffer(this.p.slice(), var18.get());
   }

   private static void E(long var0, ByteBuffer var2, int var3, float var4, float var5, float var6) {
      var2.putFloat(var3, var4).putFloat(var3 + 4, var5).putFloat(var3 + 8, var6).putFloat(var3 + 12, 0.0F);
   }

   private void b(GpuDevice var1, int var2, short var3, short var4, int var5, int var6) {
      HomeGuiBridge.ensureSourceClassInitialized();
      if (this.A == null) {
         this.A = new class_6367("SourceByDevFFnightmare DeObfDeCompByGuideX home world", var5, var6, true);
      } else if (this.A.field_1482 != var5 || this.A.field_1481 != var6) {
         this.A.method_1234(var5, var6);
      }

      if (this.t == null) {
         int var7 = Math.max(16, var1.getUniformOffsetAlignment());
         this.a = (16 + var7 - 1) / var7 * var7;
         this.t = var1.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX home frame"), 136, 368L);
         this.G = var1.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX home draws"), 136, this.a * 4L);
         this.p = var1.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX home composite"), 136, 64L);
         this.Z = MemoryUtil.memAlloc(Math.max(368, this.a * 4));
      }
   }

   @Override
   public void close() {
      this.x.close();
      this.k.close();
      this.X.close();
      this.U.close();
      this.d.close();
      this.D.close();
      this.releaseTarget();
      if (this.t != null) {
         this.t.close();
         this.G.close();
         this.p.close();
         this.t = null;
         this.G = null;
         this.p = null;
      }

      if (this.Z != null) {
         MemoryUtil.memFree(this.Z);
         this.Z = null;
      }
   }

   GpuTextureView depth() {
      return this.A != null ? this.A.method_71640() : null;
   }

   void releaseTarget() {
      if (this.A != null) {
         this.A.method_1238();
         this.A = null;
      }

      this.h.close();
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (P != null) {
            ClientCache.release(P);
         }
      } catch (Exception var90) {
         var0++;
      } finally {
         ;
      }

      try {
         if (M != null) {
            ClientCache.release(M);
         }
      } catch (Exception var88) {
         var0++;
      } finally {
         ;
      }

      try {
         if (V != null) {
            ClientCache.release(V);
         }
      } catch (Exception var86) {
         var0++;
      } finally {
         ;
      }

      try {
         if (u != null) {
            ClientCache.release(u);
         }
      } catch (Exception var84) {
         var0++;
      } finally {
         ;
      }

      try {
         if (m != null) {
            ClientCache.release(m);
         }
      } catch (Exception var82) {
         var0++;
      } finally {
         ;
      }

      try {
         if (g != null) {
            ClientCache.release(g);
         }
      } catch (Exception var80) {
         var0++;
      } finally {
         ;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 28050) & 32767;
      if (l[var3] == null) {
         l[var3] = j[var3] ^ var1;
      }

      return l[var3];
   }

   static final class HomeLease implements AutoCloseable {
      final String I;
      GpuBuffer C;
      int F;
      Object Z;
      private static final String a = "SourceByDevFFnightmare DeObfDeCompByGuideX home ";

      HomeLease(String var1) {
         this.I = var1;
      }

      void upload(ByteBuffer var1, int var2, Object var3) {
         GpuDevice var4 = RenderSystem.getDevice();
         long var5 = var1.remaining();
         if (this.C == null || this.C.size() < var5) {
            if (this.C != null) {
               this.C.close();
            }

            long var7 = 65536L;

            while (var7 < var5) {
               var7 <<= 1;
            }

            this.C = var4.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX home " + this.I), 40, var7);
         }

         if (var5 > 0L) {
            var4.createCommandEncoder().writeToBuffer(this.C.slice(0L, var5), var1);
         }

         this.F = var2;
         this.Z = var3;
      }

      boolean drawable(Object var1) {
         return this.C != null && this.F > 0 && this.Z == var1;
      }

      void clear() {
         this.F = 0;
      }

      @Override
      public void close() {
         if (this.C != null) {
            this.C.close();
            this.C = null;
         }

         this.F = 0;
      }
   }

   static final class HomeWorker {
      final Matrix4f s = new Matrix4f();
      final Matrix4f Y = new Matrix4f();
      final Matrix4f V = new Matrix4f();
      float z;
      float S;
      float I;
      float U;
      float l;
      float G;
      float T;
      float o;
      float d;
      float A;
      float Z;
      float B = -1.0F;
      float k;
      float F = 1.0F;
      float W;
      HomePalette.HomeColorSet v;
      HomeBlocks X;
      float L = 1000000.0F;
      float i = -1.0F;
      float u = 1.0F;
      float m;
      float D;
      float M;
      float q = 1.0F;
   }
}
