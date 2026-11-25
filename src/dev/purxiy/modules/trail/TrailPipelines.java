package dev.purxiy.modules.trail;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
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
import com.mojang.blaze3d.systems.RenderSystem.class_5590;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.render.pipeline.SkyVertexFormat;
import java.nio.ByteBuffer;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import net.minecraft.class_10789;
import net.minecraft.class_290;
import net.minecraft.class_6367;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

final class TrailPipelines {
   static final VertexFormat V = VertexFormat.builder()
      .add("Head", SkyVertexFormat.POS_LOCAL)
      .add("Tail", SkyVertexFormat.UV_SIZE)
      .add("Width", SkyVertexFormat.RADII)
      .add("Life", SkyVertexFormat.PARAMS)
      .add("Extra", SkyVertexFormat.CLIP)
      .add("Color", VertexFormatElement.COLOR)
      .build();
   static final int G = V.getVertexSize();
   static final int A = G * 4;
   private static final int H = 240;
   static final RenderPipeline U = K((short)0, 122998393, "trail", '㗓', false);
   static final RenderPipeline K = K((short)0, 122998393, "trail_preview", '㗓', true);
   static final RenderPipeline g = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/trail_composite"))
      .withVertexShader(ClientServices.id("core/trail_composite"))
      .withFragmentShader(ClientServices.id("core/trail_composite"))
      .withSampler("LayerSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static GpuTexture d;
   private static GpuTexture h;
   private static GpuTextureView i;
   private static GpuTextureView L;
   private static int X = 0;
   private static int N = 0;
   private static final String[] b = new String[31];
   private static final long[] c = new long[]{-4309216576338806218L, 2781793062154652631L, 972226567497814947L};
   private static final Integer[] e = new Integer[3];

   private TrailPipelines() {
   }

   private static RenderPipeline K(short var0, int var1, String var2, char var3, boolean var4) {
      Builder var5 = RenderPipeline.builder(new Snippet[0])
         .withLocation(ClientServices.id("pipeline/" + var2))
         .withVertexShader(ClientServices.id("core/trail"))
         .withFragmentShader(ClientServices.id("core/trail"))
         .withUniform("PurxiyTrail", class_10789.field_60031)
         .withoutBlend()
         .withDepthWrite(true)
         .withDepthTestFunction(DepthTestFunction.LESS_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(V, class_5596.field_27382);
      if (var4) {
         var5.withShaderDefine("TRAIL_PREVIEW");
      } else {
         var5.withUniform("Fog", class_10789.field_60031).withSampler("DepthSampler");
      }

      return var5.build();
   }

   static boolean ready(RenderPipeline var0) {
      GpuDevice var1 = RenderSystem.getDevice();
      return var1.precompilePipeline(var0).isValid() && (var0 != U || var1.precompilePipeline(g).isValid());
   }

   static void drawWorld(TrailPipelines.TrailVertexSink var0, TrailPipelines.TrailSlot var1, GpuTextureView var2, GpuTextureView var3, GpuBufferSlice var4, GpuQuery var5) {
      long var6 = 25092651085220L;
      long var8 = var6 ^ 110222636756530L;
      long var10 = var6 ^ 137749968076216L;
      int var12 = var0.quads();
      if (var12 != 0) {
         GpuDevice var13 = RenderSystem.getDevice();
         CommandEncoder var14 = var13.createCommandEncoder();
         int var15 = var2.getWidth(0);
         int var16 = var2.getHeight(0);
         G(var13, var10, var15, var16);
         var0.J(var8, var13, var14, var1, (float)var15, (float)var16);
         class_5590 var17 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
         GpuBuffer var18 = var17.method_68274(var12 * 6);

         try {
            RenderPass var19 = var14.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX trail", i, OptionalInt.of(0), L, OptionalDouble.of(1.0));

            try {
               var19.setPipeline(U);
               var19.setUniform("PurxiyTrail", var0.u);
               var19.setUniform("Fog", var4);
               var19.bindTexture("DepthSampler", var3, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
               var19.setVertexBuffer(0, var0.b);
               var19.setIndexBuffer(var18, var17.method_31924());
               var19.drawIndexed(0, 0, var12 * 6, 1);
               TrailRenderer.metric("drawn");
            } catch (Throwable var31) {
               if (var19 != null) {
                  try {
                     var19.close();
                  } catch (Throwable var29) {
                     var31.addSuppressed(var29);
                  }
               }

               throw var31;
            }

            if (var19 != null) {
               var19.close();
            }

            var19 = var14.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX trail composite", var2, OptionalInt.empty());

            try {
               var19.setPipeline(g);
               var19.bindTexture("LayerSampler", i, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
               var19.draw(0, 6);
               TrailRenderer.metric("drawn");
            } catch (Throwable var30) {
               if (var19 != null) {
                  try {
                     var19.close();
                  } catch (Throwable var28) {
                     var30.addSuppressed(var28);
                  }
               }

               throw var30;
            }

            if (var19 != null) {
               var19.close();
            }
         } finally {
            if (var5 != null) {
               var14.timerQueryEnd(var5);
            }
         }
      }
   }

   static void drawPreview(TrailPipelines.TrailVertexSink var0, TrailPipelines.TrailSlot var1, class_6367 var2) {
      long var3 = 79374348493946L;
      long var5 = var3 ^ 64723618457580L;
      GpuDevice var7 = RenderSystem.getDevice();
      CommandEncoder var8 = var7.createCommandEncoder();
      GpuTextureView var9 = var2.method_71639();
      GpuTextureView var10 = var2.method_71640();
      if (var9 != null && var10 != null) {
         int var11 = var0.quads();
         if (var11 == 0) {
            var8.clearColorTexture(var9.texture(), 0);
         } else {
            var0.J(var5, var7, var8, var1, (float)var9.getWidth(0), (float)var9.getHeight(0));
            class_5590 var12 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
            GpuBuffer var13 = var12.method_68274(var11 * 6);
            RenderPass var14 = var8.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX trail " + var0.M, var9, OptionalInt.of(0), var10, OptionalDouble.of(1.0));

            try {
               var14.setPipeline(K);
               var14.setUniform("PurxiyTrail", var0.u);
               var14.setVertexBuffer(0, var0.b);
               var14.setIndexBuffer(var13, var12.method_31924());
               var14.drawIndexed(0, 0, var11 * 6, 1);
               TrailRenderer.metric("drawn");
            } catch (Throwable var18) {
               if (var14 != null) {
                  try {
                     var14.close();
                  } catch (Throwable var17) {
                     var18.addSuppressed(var17);
                  }
               }

               throw var18;
            }

            if (var14 != null) {
               var14.close();
            }
         }
      }
   }

   private static void G(GpuDevice var0, long var1, int var3, int var4) {
      TrailRenderer.ensureSourceClassInitialized();
      if (d != null) {
         long var5 = 118310232903708L;
         int var8 = var5 < 0L ? -1 : 1;
         long var8x = 118310232903707L;
         int var10 = var8x < 0L ? -1 : 1;
         if (X == var3) {
            long var10x = 118310232903707L;
            int var11 = var10x < 0L ? -1 : 1;
            if (N == var4) {
               return;
            }
         }
      }

      closeLayer();
      byte var12 = 12;
      d = var0.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX trail layer", var12, TextureFormat.RGBA8, var3, var4, 1, 1);
      h = var0.createTexture(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX trail layer depth", var12, TextureFormat.DEPTH32, var3, var4, 1, 1);
      i = var0.createTextureView(d);
      L = var0.createTextureView(h);
      X = var3;
      N = var4;
   }

   static void closeLayer() {
      if (i != null) {
         i.close();
      }

      if (L != null) {
         L.close();
      }

      if (d != null) {
         d.close();
      }

      if (h != null) {
         h.close();
      }

      h = null;
      d = null;
      L = null;
      i = null;
      N = 0;
      X = 0;
   }

   private static float B(long var0, int var2, int var3) {
      return (var2 >>> var3 & 0xFF) / 255.0F;
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (U != null) {
            ClientCache.release(U);
         }
      } catch (Exception var119) {
         var0++;
      } finally {
         ;
      }

      try {
         if (K != null) {
            ClientCache.release(K);
         }
      } catch (Exception var117) {
         var0++;
      } finally {
         ;
      }

      try {
         if (g != null) {
            ClientCache.release(g);
         }
      } catch (Exception var115) {
         var0++;
      } finally {
         ;
      }

      try {
         if (i != null) {
            i.close();
         }
      } catch (Exception var113) {
         var0++;
      } finally {
         i = null;
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
         if (d != null) {
            d.close();
         }
      } catch (Exception var109) {
         var0++;
      } finally {
         d = null;
      }

      try {
         if (h != null) {
            h.close();
         }
      } catch (Exception var107) {
         var0++;
      } finally {
         h = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

   static final class TrailVertexSink implements TrailPoints.Listener, AutoCloseable {
      private final String M;
      private ByteBuffer P = MemoryUtil.memAlloc(TrailPipelines.A * 512);
      private GpuBuffer b;
      private GpuBuffer u;
      private ByteBuffer j;
      private int g;
      private static final String[] c = new String[3];
      private static final long[] d = new long[]{
         1419017198081100514L,
         -5132706204628979135L,
         3072030066773293965L,
         4103930556362413868L,
         -3758537246191219857L,
         -5310670344642356694L,
         -4497942316858847822L,
         -3902622219562591012L
      };
      private static final Integer[] e = new Integer[8];
      private static final long f = 240L;

      TrailVertexSink(String var1) {
         this.M = var1;
      }

      void reset() {
         this.P.clear();
         this.g = 0;
      }

      int quads() {
         return this.g / 4;
      }

      String dump() {
         StringBuilder var1 = new StringBuilder();

         for (int var2 = 0; var2 < this.g; var2++) {
            int var3 = var2 * TrailPipelines.G;

            for (int var4 = 0; var4 < 20; var4++) {
               var1.append(this.P.getFloat(var3 + var4 * 4)).append(' ');
            }

            var1.append(Integer.toHexString(this.P.getInt(var3 + 80))).append('\n');
         }

         return var1.toString();
      }

      @Override
      public void vertex(float[] var1, int var2) {
         if (this.P.remaining() < TrailPipelines.G) {
            this.P = MemoryUtil.memRealloc(this.P, this.P.capacity() * 2);
         }

         for (int var3 = 0; var3 < 20; var3++) {
            this.P.putFloat(var1[var3]);
         }

         this.P.put((byte)(var2 >> 16)).put((byte)(var2 >> 8)).put((byte)var2).put((byte)(var2 >>> 24));
         this.g++;
      }

      private void J(long var1, GpuDevice var3, CommandEncoder var4, TrailPipelines.TrailSlot var5, float var6, float var7) {
         long var8;
         int var12;
         label28: {
            var8 = 37883052061873L;
            long var10 = 132160408231203L;
            TrailRenderer.ensureSourceClassInitialized();
            var12 = this.g * TrailPipelines.G;
            if (this.b != null) {
               long var13 = 126363903312789L;
               int var151 = var13 < 0L ? -1 : 1;
               if (this.b.size() >= var12) {
                  break label28;
               }
            }

            if (this.b != null) {
               this.b.close();
            }

            int var16 = Math.max(var12 + var12 / 2, TrailPipelines.A * 1024);
            this.b = var3.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX trail " + this.M), 40, var16);
         }

         if (this.u == null) {
            this.u = var3.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX trail " + this.M + " uniforms"), 136, 240L);
            this.j = MemoryUtil.memAlloc(240);
         }

         ByteBuffer var17 = this.P.duplicate().flip();
         var4.writeToBuffer(this.b.slice(0L, var12), var17);
         int[] var14 = var5.K;
         Std140Builder var15 = Std140Builder.intoBuffer(this.j.clear())
            .putMat4f(var5.n)
            .putMat4f(var5.w)
            .putVec4(var6, var7, var5.V, var5.r)
            .putVec4(var5.L.x, var5.L.y, var5.L.z, var5.B)
            .putVec4(var5.e.x, var5.e.y, var5.e.z, 0.0F)
            .putVec4(TrailPipelines.B(var8, var14[0], 16), TrailPipelines.B(var8, var14[0], 8), TrailPipelines.B(var8, var14[0], 0), var5.g.ordinal())
            .putVec4(TrailPipelines.B(var8, var14[1], 16), TrailPipelines.B(var8, var14[1], 8), TrailPipelines.B(var8, var14[1], 0), var5.Z)
            .putVec4(TrailPipelines.B(var8, var14[2], 16), TrailPipelines.B(var8, var14[2], 8), TrailPipelines.B(var8, var14[2], 0), 0.0F)
            .putVec4(var5.m, var5.H, var5.b, var5.T);
         var4.writeToBuffer(this.u.slice(), var15.get());
      }

      @Override
      public void close() {
         if (this.b != null) {
            this.b.close();
            this.b = null;
         }

         if (this.u != null) {
            this.u.close();
            this.u = null;
         }

         if (this.j != null) {
            MemoryUtil.memFree(this.j);
            this.j = null;
         }

         MemoryUtil.memFree(this.P);
         this.P = MemoryUtil.memAlloc(TrailPipelines.A * 16);
      }

      public void dispose() {
         this.close();
         MemoryUtil.memFree(this.P);
         this.P = null;
      }
   }

   static final class TrailSlot {
      final Matrix4f n = new Matrix4f();
      final Matrix4f w = new Matrix4f();
      final Vector3f L = new Vector3f(1.0F, 0.0F, 0.0F);
      final Vector3f e = new Vector3f(0.0F, 1.0F, 0.0F);
      float V;
      float r = 0.6F;
      float Z = 1.0F;
      float B = 500.0F;
      TrailStyle g = TrailStyle.q;
      final int[] K = new int[3];
      float m = 12.0F;
      float H = 70.0F;
      float b = 0.7F;
      float T = 1.2F;
   }
}
