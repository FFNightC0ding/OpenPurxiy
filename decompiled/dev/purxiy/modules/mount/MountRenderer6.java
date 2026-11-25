package dev.purxiy.modules.mount;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Snippet;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import java.nio.ByteBuffer;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.class_10789;
import net.minecraft.class_12137;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;

public final class MountRenderer6 {
   private static final int q = class_290.field_1577.getVertexSize();
   private static final RenderPipeline y = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/porsche_surface"))
      .withVertexShader(ClientServices.id("core/porsche_surface"))
      .withFragmentShader(ClientServices.id("core/porsche_surface"))
      .withUniform("DynamicTransforms", class_10789.field_60031)
      .withUniform("Projection", class_10789.field_60031)
      .withUniform("PorscheSurface", class_10789.field_60031)
      .withSampler("SurfaceSampler")
      .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
      .withDepthWrite(true)
      .withCull(false)
      .withoutBlend()
      .withVertexFormat(class_290.field_1577, class_5596.field_27379)
      .build();
   private static GpuBuffer o;
   private static GpuBuffer N;
   private static ByteBuffer h;
   private static ByteBuffer u;
   private static final long e = 80L;

   private MountRenderer6() {
   }

   public static void draw(
      WorldRenderContext var0, GpuTextureView var1, class_12137 var2, Matrix4f var3, float[] var4, float[] var5, float var6, float var7, float var8
   ) {
      long var9 = 6311555657828L;
      long var11 = var9 ^ 59600408387857L;
      if (var4.length == 12 && var5.length == 8) {
         float[] var13 = new float[18];
         float[] var14 = new float[12];
         int[] var15 = new int[]{0, 1, 2, 0, 2, 3};

         for (int var16 = 0; var16 < 6; var16++) {
            System.arraycopy(var4, var15[var16] * 3, var13, var16 * 3, 3);
            System.arraycopy(var5, var15[var16] * 2, var14, var16 * 2, 2);
         }

         float var18 = new Vector3f(var4[3] - var4[0], var4[4] - var4[1], var4[5] - var4[2]).length();
         float var17 = new Vector3f(var4[9] - var4[0], var4[10] - var4[1], var4[11] - var4[2]).length();
         F(var1, var2, var11, var3, var13, var14, var6, var7, var8, var18 / Math.max(0.001F, var17));
      } else {
         throw new IllegalArgumentException("Surface needs four corners");
      }
   }

   public static void drawTriangles(WorldRenderContext var0, GpuTextureView var1, class_12137 var2, Matrix4f var3, float[] var4, float[] var5, float var6) {
      long var7 = 65362945014762L;
      long var9 = var7 ^ 9875006270111L;
      F(var1, var2, var9, var3, var4, var5, 0.0F, var6, 0.0F, 1.0F);
   }

   private static void F(
      GpuTextureView var0, class_12137 var1, long var2, Matrix4f var4, float[] var5, float[] var6, float var7, float var8, float var9, float var10
   ) {
      long var11 = 1323099214454L;
      MountFactory.Data7.ensureSourceClassInitialized();
      if (var0 != null && var5.length != 0) {
         int var13 = var5.length / 3;
         int var14 = var5.length;
         long var15 = 56664764174708L;
         int var18 = var15 < 0L ? -1 : 1;
         if (var14 % 9 != 0 || var6.length != var13 * 2) {
            throw new IllegalArgumentException("Invalid surface triangles");
         }

         GpuDevice var18x = RenderSystem.getDevice();
         if (!var18x.precompilePipeline(y).isValid()) {
            throw new IllegalStateException("Porsche surface shader failed");
         }

         class_276 var19 = class_310.method_1551().method_1522();
         if (var19.method_71639() != null && var19.method_71640() != null) {
            W(var13 * q, var11);
            h.clear();
            byte var20 = 0;

            while (var20 < var13) {
               long var21 = 56664764174708L;
               int var24 = var21 < 0L ? -1 : 1;
               var24 = var20 * 3;
               Vector3f var25 = new Vector3f(var5[var24 + 3] - var5[var24], var5[var24 + 4] - var5[var24 + 1], var5[var24 + 5] - var5[var24 + 2])
                  .cross(var5[var24 + 6] - var5[var24], var5[var24 + 7] - var5[var24 + 1], var5[var24 + 8] - var5[var24 + 2]);
               float var26;
               var14 = (var26 = var25.lengthSquared() - 1.0E-14F) == 0.0F ? 0 : (var26 < 0.0F ? -1 : 1);
               long var27 = 56664764174708L;
               int var27x = var27 < 0L ? -1 : 1;
               if (var14 < 0) {
                  var25.set(0.0F, 0.0F, -1.0F);
                  long var29 = 56664764174708L;
                  int var29x = var29 < 0L ? -1 : 1;
               } else {
                  var25.normalize();
               }

               for (int var39 = var20; var39 < var20 + 3; var39++) {
                  h.putFloat(var5[var39 * 3]).putFloat(var5[var39 * 3 + 1]).putFloat(var5[var39 * 3 + 2]);
                  long var30 = 56664764174709L;
                  int var30x = var30 < 0L ? -1 : 1;
                  h.putFloat(var6[var39 * 2]).putFloat(var6[var39 * 2 + 1]).putInt(-1);
                  h.put((byte)Math.round(var25.x * 127.0F)).put((byte)Math.round(var25.y * 127.0F)).put((byte)Math.round(var25.z * 127.0F)).put((byte)0);
               }

               var20 = (byte)(var20 + 3);
               long var40 = 56664764174709L;
               int var47 = var40 < 0L ? -1 : 1;
            }

            long var35 = 56664764174708L;
            int var371 = var35 < 0L ? -1 : 1;
            h.flip();
            CommandEncoder var23 = var18x.createCommandEncoder();
            var23.writeToBuffer(o.slice(0L, h.remaining()), h);
            var23.writeToBuffer(N.slice(), Std140Builder.intoBuffer(u.clear()).putMat4f(var4).putVec4(var7, var8, var9, var10).get());
            GpuBufferSlice var36 = RenderSystem.getDynamicUniforms()
               .method_71106(MountRenderer5.modelView(), new Vector4f(1.0F), new Vector3f(), new Matrix4f());
            RenderPass var37x = var23.createRenderPass(
               () -> "Porsche cabin surface", var19.method_71639(), OptionalInt.empty(), var19.method_71640(), OptionalDouble.empty()
            );

            try {
               long var38 = 56664764174708L;
               int var45 = var38 < 0L ? -1 : 1;
               var37x.setPipeline(y);
               RenderSystem.bindDefaultUniforms(var37x);
               var37x.setUniform("DynamicTransforms", var36);
               var37x.setUniform("PorscheSurface", N);
               var37x.bindTexture("SurfaceSampler", var0, var1);
               var37x.setVertexBuffer(0, o);
               long var41 = 56664764174709L;
               int var48 = var41 < 0L ? -1 : 1;
               var37x.draw(0, var13);
            } catch (Throwable var32) {
               if (var37x != null) {
                  try {
                     var37x.close();
                  } catch (Throwable var31) {
                     var32.addSuppressed(var31);
                  }
               }

               throw var32;
            }

            if (var37x != null) {
               var37x.close();
            }
         }
      }
   }

   private static void W(int var0, long var1) {
      MountFactory.Data7.ensureSourceClassInitialized();
      GpuDevice var3 = RenderSystem.getDevice();
      long var4 = 1323099214453L;
      int var7 = var4 < 0L ? -1 : 1;
      if (N == null) {
         N = var3.createBuffer(ClientServices.label(() -> "Porsche surface material"), 136, 80L);
         u = MemoryUtil.memAlloc(80);
      }

      if (h != null) {
         var7 = h.capacity();
         long var8 = 1323099214454L;
         int var11 = var8 < 0L ? -1 : 1;
         long var11x = 1323099214454L;
         int var11x = var11x < 0L ? -1 : 1;
         if (var7 >= var0) {
            return;
         }
      }

      var7 = Math.max(4096, Integer.highestOneBit(Math.max(1, var0 - 1)) << 1);
      long var14 = 1323099214453L;
      int var16 = var14 < 0L ? -1 : 1;
      h = h == null ? MemoryUtil.memAlloc(var7) : MemoryUtil.memRealloc(h, var7);
      if (o != null) {
         o.close();
      }

      o = var3.createBuffer(ClientServices.label(() -> "Porsche surface triangles"), 40, var7);
   }

   public static void close() {
      if (o != null) {
         o.close();
      }

      if (N != null) {
         N.close();
      }

      if (h != null) {
         MemoryUtil.memFree(h);
      }

      if (u != null) {
         MemoryUtil.memFree(u);
      }

      o = null;
      N = null;
      h = null;
      u = null;
   }

   public static void releasePipelines() {
      ClientCache.release(y);
   }
}
