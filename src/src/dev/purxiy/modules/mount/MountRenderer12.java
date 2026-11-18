// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Builder;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.platform.DestFactor;
import com.mojang.blaze3d.platform.SourceFactor;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import java.nio.ByteBuffer;
import java.util.OptionalInt;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.class_10789;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.system.MemoryUtil;

final class MountRenderer12 {
   private static final int b = 272;
   private static final RenderPipeline y = k("shadow", BlendFunction.TRANSLUCENT, "SHADOW", 26826691837311L);
   private static final RenderPipeline U = k("light", new BlendFunction(SourceFactor.ONE, DestFactor.ONE), "LIGHT", 26826691837311L);
   private static GpuBuffer G;
   private static ByteBuffer L;
   private static final Matrix4f E = new Matrix4f();
   private static final Matrix4f X = new Matrix4f();
   private static final Matrix4f o = new Matrix4f();
   private static final Matrix4f h = new Matrix4f();
   private static boolean g;



   private static RenderPipeline k(String var0, BlendFunction var1, String var2, long var3) {
      Builder var5 = RenderPipeline.builder()
         .withLocation(ClientServices.id("pipeline/garage_decal_" + var0))
         .withVertexShader(ClientServices.id("core/garage_composite"))
         .withFragmentShader(ClientServices.id("core/garage_decal"))
         .withShaderDefine(var2)
         .withUniform("GarageDecal", class_10789.field_60031)
         .withSampler("SceneDepth")
         .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
         .withDepthWrite(false)
         .withCull(false)
         .withBlend(var1)
         .withVertexFormat(class_290.field_60033, class_5596.field_27379);
      if (var2.equals("SHADOW")) {
         var5.withSampler("GroundMap");
      }

      return var5.build();
   }

   private MountRenderer12() {
   }

   static void frame(Matrix4f var0, Matrix4f var1) {
      o.set(var0);
      h.set(var1);
      g = true;
   }

   static Matrix4f view() {
      return o;
   }

   static Matrix4f projection() {
      return h;
   }

   static void render(
      WorldRenderContext var0,
      class_310 var1,
      float var2,
      MountFactory.Data7 var3,
      MountRenderer7.TypeK var4,
      class_243 var5,
      class_243 var6,
      MountRenderer7.TypeV var7
   ) {
      if (g) {
         GpuDevice var8 = RenderSystem.getDevice();
         if (var8.precompilePipeline(y).isValid() && var8.precompilePipeline(U).isValid()) {
            class_276 var9 = var1.method_1522();
            if (var9.method_71639() != null && var9.method_71640() != null) {
               if (G == null) {
                  G = var8.createBuffer(ClientServices.label(() -> "Purxiy garage decals"), 136, 272L);
                  L = MemoryUtil.memAlloc(272);
               }

               E.set(h).mul(o).invert();
               float var10 = (float)Math.toRadians(-MountUpdater4.yaw(var2));
               X.identity()
                  .rotateY(-var10)
                  .translate((float)(var6.field_1352 - var5.field_1352), (float)(var6.field_1351 - var5.field_1351), (float)(var6.field_1350 - var5.field_1350));
               Vector3f var11 = new Vector3f(var7.T);
               new Matrix4f().rotateY(-var10).transformDirection(var11);
               Std140Builder var12 = Std140Builder.intoBuffer(L.clear()).putMat4f(E).putMat4f(X);
               float var13 = var3.bike()
                  ? var3.wheels()[0].halfWidth() + 0.12F
                  : Math.min(var3.halfWidth(), var3.track() / 2.0F + var3.wheels()[0].halfWidth() + 0.03F);
               float var14 = (var3.hi().z - var3.lo().z) / 2.0F * (var3.bike() ? 0.9F : 0.97F);
               float var15 = (var3.hi().z + var3.lo().z) / 2.0F;
               float var16 = Math.clamp(var7.E / 0.62F, 0.0F, 1.0F);
               var16 *= var16;
               float var17 = Math.min(1.0F, 0.35F + 0.65F * var7.N) * (0.5F + 0.5F * var7.z) * var16;
               var12.putVec4(var13, var14, var15, var17);
               var12.putVec4(var11.x, var11.y, var11.z, var7.z * (1.0F - 0.7F * var7.y) * var7.N * var7.N * var16);
               float[] var18 = var4.g.q;
               float[] var19 = var4.g.D;
               var12.putVec4(var18[0], var18[1], 1.0F / (var19[0] - var18[0]), 1.0F / (var19[1] - var18[1]));
               int var20 = Math.min(2, var3.headlights().size());

               for (int var21 = 0; var21 < 2; var21++) {
                  if (var21 < var20) {
                     MountFactory.Data5 var22 = var3.headlights().get(var21);
                     var12.putVec4(var22.position().x, var22.position().y, var22.position().z, var7.q);
                     var12.putVec4(var22.direction().x, var22.direction().y, var22.direction().z, var7.S * 0.0F);
                  } else {
                     var12.putVec4(0.0F, 0.0F, 0.0F, 0.0F);
                     var12.putVec4(0.0F, 0.0F, 1.0F, 0.0F);
                  }
               }

               var12.putVec4(var3.hi().z, var3.lo().z, var7.j, var7.m);
               var12.putVec4(var7.f, var3.height(), 0.0F, 0.0F);
               CommandEncoder var28 = var8.createCommandEncoder();
               var28.writeToBuffer(G.slice(), var12.get());

               try (RenderPass var29 = var28.createRenderPass(() -> "Purxiy garage ground", var9.method_71639(), OptionalInt.empty())) {
                  var29.setPipeline(y);
                  var29.setUniform("GarageDecal", G);
                  var29.bindTexture("GroundMap", var4.groundView(), RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR));
                  var29.bindTexture("SceneDepth", var9.method_71640(), RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
                  var29.draw(0, 3);
                  MountRenderer4.drawn(false);
                  if (var7.q > 0.01F || var7.m > 0.01F || var7.f > 0.01F || var7.j > 0.01F) {
                     var29.setPipeline(U);
                     var29.setUniform("GarageDecal", G);
                     var29.bindTexture("SceneDepth", var9.method_71640(), RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
                     var29.draw(0, 3);
                     MountRenderer4.drawn(false);
                  }
               }
            }
         }
      }
   }

   static void close() {
      if (G != null) {
         G.close();
      }

      if (L != null) {
         MemoryUtil.memFree(L);
      }

      G = null;
      L = null;
      g = false;
   }

   public static void releasePipelines() {
      ClientCache.release(y);
      ClientCache.release(U);
   }

}
