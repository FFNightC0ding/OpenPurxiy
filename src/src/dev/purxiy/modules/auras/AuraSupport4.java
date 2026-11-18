// Sourced By DevFFNightmare
package dev.purxiy.modules.auras;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Builder;
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

final class AuraSupport4 {
   static final VertexFormat e = VertexFormat.builder()
      .add("Head", SkyVertexFormat.POS_LOCAL)
      .add("Tail", SkyVertexFormat.UV_SIZE)
      .add("Width", SkyVertexFormat.RADII)
      .add("Shade", SkyVertexFormat.PARAMS)
      .add("Color", VertexFormatElement.COLOR)
      .add("Color2", SkyVertexFormat.COLOR2)
      .build();
   static final int x = e.getVertexSize();
   static final int u = x * 4;
   private static final int z = 192;
   static final RenderPipeline H = N("aura", 118009007357553L, false);
   static final RenderPipeline J = N("aura_preview", 118009007357553L, true);
   static final RenderPipeline L = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/aura_composite"))
      .withVertexShader(ClientServices.id("core/aura_composite"))
      .withFragmentShader(ClientServices.id("core/aura_composite"))
      .withSampler("LayerSampler")
      .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   
   private static GpuTexture s;
   
   private static GpuTexture Y;
   
   private static GpuTextureView U;
   
   private static GpuTextureView r;
   private static int G = 0;
   private static int l = 0;


   private static final long[] c = new long[]{-203425789221716440L, -3561790373537401718L};
   private static final Integer[] d = new Integer[2];

   private AuraSupport4() {
   }

   private static RenderPipeline N(String var0, long var1, boolean var3) {
      Builder var4 = RenderPipeline.builder()
         .withLocation(ClientServices.id("pipeline/" + var0))
         .withVertexShader(ClientServices.id("core/aura"))
         .withFragmentShader(ClientServices.id("core/aura"))
         .withUniform("PurxiyAura", class_10789.field_60031)
         .withoutBlend()
         .withDepthWrite(true)
         .withDepthTestFunction(DepthTestFunction.LESS_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(e, class_5596.field_27382);
      long var5 = 118009007357553L;
      if (118009007357553L == 0L) {
         boolean var10000 = false;
      } else {
         int var10 = var5 < 0L ? -1 : 1;
      }

      if (var3) {
         var4.withShaderDefine("AURA_PREVIEW");
         long var8 = 118009007357553L;
         if (118009007357553L == 0L) {
            boolean var11 = false;
         } else {
            int var12 = var8 < 0L ? -1 : 1;
         }
      } else {
         var4.withUniform("Fog", class_10789.field_60031).withSampler("DepthSampler");
      }

      return var4.build();
   }

   static boolean ready(RenderPipeline var0) {
      GpuDevice var1 = RenderSystem.getDevice();
      return var1.precompilePipeline(var0).isValid() && (var0 != H || var1.precompilePipeline(L).isValid());
   }

   static void drawWorld(
      AuraSupport4.TypeG var0, AuraSupport4.TypeT var1, GpuTextureView var2, GpuTextureView var3, GpuBufferSlice var4,  GpuQuery var5
   ) {
      long var6 = 50483064646837L;
      long var8 = var6 ^ 98257182021500L;
      int var10 = (int)((var6 ^ 98257182021500L) >>> 32);
      int var11 = (int)((var6 ^ 98257182021500L) << 32 >>> 48);
      int var12 = (int)(var8 << 48 >>> 48);
      long var13 = var6 ^ 11630999602535L;
      int var15 = var0.quads();
      if (var15 != 0) {
         GpuDevice var16 = RenderSystem.getDevice();
         CommandEncoder var17 = var16.createCommandEncoder();
         int var18 = var2.getWidth(0);
         int var19 = var2.getHeight(0);
         d(var10, (char)var11, var16, var18, var19, var12);
         var0.v(var16, var17, var1, (float)var18, var13, (float)var19);
         class_5590 var20 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
         GpuBuffer var21 = var20.method_68274(var15 * 6);

         try {
            try (RenderPass var22 = var17.createRenderPass(() -> "Purxiy aura", U, OptionalInt.of(0), r, OptionalDouble.of(1.0))) {
               var22.setPipeline(H);
               var22.setUniform("PurxiyAura", var0.H);
               var22.setUniform("Fog", var4);
               var22.bindTexture("DepthSampler", var3, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
               var22.setVertexBuffer(0, var0.t);
               var22.setIndexBuffer(var21, var20.method_31924());
               var22.drawIndexed(0, 0, var15 * 6, 1);
               AuraRenderer3.metric("drawn");
            }

            try (RenderPass var36 = var17.createRenderPass(() -> "Purxiy aura composite", var2, OptionalInt.empty())) {
               var36.setPipeline(L);
               var36.bindTexture("LayerSampler", U, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
               var36.draw(0, 6);
               AuraRenderer3.metric("drawn");
            }
         } finally {
            if (var5 != null) {
               var17.timerQueryEnd(var5);
            }
         }
      }
   }

   static void drawPreview(AuraSupport4.TypeG var0, AuraSupport4.TypeT var1, class_6367 var2) {
      long var3 = 23056967994640L;
      long var5 = var3 ^ 56652008531138L;
      GpuDevice var7 = RenderSystem.getDevice();
      CommandEncoder var8 = var7.createCommandEncoder();
      GpuTextureView var9 = var2.method_71639();
      GpuTextureView var10 = var2.method_71640();
      if (var9 != null && var10 != null) {
         int var11 = var0.quads();
         if (var11 == 0) {
            var8.clearColorTexture(var9.texture(), 0);
         } else {
            var0.v(var7, var8, var1, (float)var9.getWidth(0), var5, (float)var9.getHeight(0));
            class_5590 var12 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
            GpuBuffer var13 = var12.method_68274(var11 * 6);

            try (RenderPass var14 = var8.createRenderPass(() -> "Purxiy aura " + var0.j, var9, OptionalInt.of(0), var10, OptionalDouble.of(1.0))) {
               var14.setPipeline(J);
               var14.setUniform("PurxiyAura", var0.H);
               var14.setVertexBuffer(0, var0.t);
               var14.setIndexBuffer(var13, var12.method_31924());
               var14.drawIndexed(0, 0, var11 * 6, 1);
               AuraRenderer3.metric("drawn");
            }
         }
      }
   }

   private static void d(int var0, char var1, GpuDevice var2, int var3, int var4, int var5) {
      AuraStyle.ensureSourceClassInitialized();
      if (s != null) {
         short var6 = 18764;
         var6 = 29879;
         if (G == var3) {
            var6 = 29879;
            if (l == var4) {
               return;
            }
         }
      }

      closeLayer();
      byte var9 = 12;
      s = var2.createTexture(() -> "Purxiy aura layer", var9, TextureFormat.RGBA8, var3, var4, 1, 1);
      Y = var2.createTexture(() -> "Purxiy aura layer depth", var9, TextureFormat.DEPTH32, var3, var4, 1, 1);
      U = var2.createTextureView(s);
      r = var2.createTextureView(Y);
      G = var3;
      l = var4;
   }

   static void closeLayer() {
      if (U != null) {
         U.close();
      }

      if (r != null) {
         r.close();
      }

      if (s != null) {
         s.close();
      }

      if (Y != null) {
         Y.close();
      }

      Y = null;
      s = null;
      r = null;
      U = null;
      l = 0;
      G = 0;
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (H != null) {
            ClientCache.release(H);
         }
      } catch (Exception var119) {
         var0++;
      } finally {
         ;
      }

      try {
         if (J != null) {
            ClientCache.release(J);
         }
      } catch (Exception var117) {
         var0++;
      } finally {
         ;
      }

      try {
         if (L != null) {
            ClientCache.release(L);
         }
      } catch (Exception var115) {
         var0++;
      } finally {
         ;
      }

      try {
         if (U != null) {
            U.close();
         }
      } catch (Exception var113) {
         var0++;
      } finally {
         U = null;
      }

      try {
         if (r != null) {
            r.close();
         }
      } catch (Exception var111) {
         var0++;
      } finally {
         r = null;
      }

      try {
         if (s != null) {
            s.close();
         }
      } catch (Exception var109) {
         var0++;
      } finally {
         s = null;
      }

      try {
         if (Y != null) {
            Y.close();
         }
      } catch (Exception var107) {
         var0++;
      } finally {
         Y = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

static final class TypeG implements AuraRenderer2.Listener2, AutoCloseable {
      private final String j;
      private ByteBuffer x = MemoryUtil.memAlloc(AuraSupport4.u * 256);
      
      private GpuBuffer t;
      
      private GpuBuffer H;
      
      private ByteBuffer l;
      private int D;


      private static final long[] c = new long[]{8378404633520733223L, -600675837915529469L, 5592350934605613634L, 7132532305315858221L};
      private static final Integer[] d = new Integer[4];
      private static final long e = 192L;

      TypeG(String var1) {
         this.j = var1;
      }

      void reset() {
         this.x.clear();
         this.D = 0;
      }

      int quads() {
         return this.D / 4;
      }

      @Override
      public void vertex(float[] var1, int var2, int var3) {
         if (this.x.remaining() < AuraSupport4.x) {
            this.x = MemoryUtil.memRealloc(this.x, this.x.capacity() * 2);
         }

         for (int var4 = 0; var4 < 16; var4++) {
            this.x.putFloat(var1[var4]);
         }

         this.x.put((byte)(var2 >> 16)).put((byte)(var2 >> 8)).put((byte)var2).put((byte)-1);
         this.x.put((byte)(var3 >> 16)).put((byte)(var3 >> 8)).put((byte)var3).put((byte)-1);
         this.D++;
      }

      private void v(GpuDevice var1, CommandEncoder var2, AuraSupport4.TypeT var3, float var4, long var5, float var7) {
         int var10;
         label31: {
            long var8 = 59645008183881L;
            AuraStyle.ensureSourceClassInitialized();
            var10 = this.D * AuraSupport4.x;
            if (this.t != null) {
               long var11 = 43422332955089L;
               if (43422332955089L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var16 = var11 < 0L ? -1 : 1;
               }

               if (this.t.size() >= var10) {
                  break label31;
               }
            }

            if (this.t != null) {
               this.t.close();
            }

            int var14 = Math.max(var10 + var10 / 2, AuraSupport4.u * 512);
            this.t = var1.createBuffer(ClientServices.label(() -> "Purxiy aura " + this.j), 40, var14);
         }

         if (this.H == null) {
            this.H = var1.createBuffer(ClientServices.label(() -> "Purxiy aura " + this.j + " uniforms"), 136, 192L);
            this.l = MemoryUtil.memAlloc(192);
         }

         ByteBuffer var15 = this.x.duplicate().flip();
         var2.writeToBuffer(this.t.slice(0L, var10), var15);
         Std140Builder var12 = Std140Builder.intoBuffer(this.l.clear())
            .putMat4f(var3.d)
            .putMat4f(var3.X)
            .putVec4(var4, var7, var3.Y, var3.F)
            .putVec4(var3.I, var3.u, var3.x, var3.R)
            .putVec4(var3.Z, var3.W, var3.p, var3.M)
            .putVec4(var3.m.x, var3.m.y, var3.m.z, 0.0F);
         var2.writeToBuffer(this.H.slice(), var12.get());
      }

      @Override
      public void close() {
         if (this.t != null) {
            this.t.close();
            this.t = null;
         }

         if (this.H != null) {
            this.H.close();
            this.H = null;
         }

         if (this.l != null) {
            MemoryUtil.memFree(this.l);
            this.l = null;
         }

         MemoryUtil.memFree(this.x);
         this.x = MemoryUtil.memAlloc(AuraSupport4.u * 16);
      }

      public void dispose() {
         this.close();
         MemoryUtil.memFree(this.x);
         this.x = null;
      }

}

   static final class TypeT {
      final Matrix4f d = new Matrix4f();
      final Matrix4f X = new Matrix4f();
      final Vector3f m = new Vector3f(0.0F, 0.0F, -1.0F);
      float Y;
      float F = 0.65F;
      float R = 500.0F;
      float I = 12.0F;
      float u = 70.0F;
      float x = 0.7F;
      float Z = 0.05F;
      float W = 0.1F;
      float p;
      float M;
   }
}
