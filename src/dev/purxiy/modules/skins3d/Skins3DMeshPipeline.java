package dev.purxiy.modules.skins3d;

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
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.class_1011;
import net.minecraft.class_10789;
import net.minecraft.class_12206;
import net.minecraft.class_1944;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;

public final class Skins3DMeshPipeline {
   private static final int x = class_290.field_1577.getVertexSize();
   static final RenderPipeline q = b(15690008705008L, false);
   static final RenderPipeline D = b(15690008705008L, true);
   private static GpuBuffer w;
   private static GpuBuffer N;
   private static GpuBuffer L;
   private static ByteBuffer i;
   private static ByteBuffer F;
   private static ByteBuffer k;
   private static final int R = 12;
   private static final float e = 0.022F;
   private static Skins3DMeshPipeline.SkinMesh S;
   private static Skins3DMeshPipeline.SkinMesh E;
   private static int[] f;
   private static String h;
   private static final String[] b = new String[19];
   private static final long g = 112L;

   private Skins3DMeshPipeline() {
   }

   private static RenderPipeline b(long var0, boolean var2) {
      Builder var3 = RenderPipeline.builder(new Snippet[0]);
      long var4 = 15690008705008L;
      int var7 = var4 < 0L ? -1 : 1;
      var3 = var3.withLocation(ClientServices.id("pipeline/spider_mask" + (var2 ? "_shadow" : "")))
         .withVertexShader(ClientServices.id("core/mount"))
         .withFragmentShader(ClientServices.id("core/mount"))
         .withUniform("DynamicTransforms", class_10789.field_60031)
         .withUniform("Projection", class_10789.field_60031)
         .withUniform("Fog", class_10789.field_60031);
      long var7x = 15690008705008L;
      int var6x = var7x < 0L ? -1 : 1;
      var6x = 493176924;
      Builder var9 = var3.withUniform("PurxiyMount", class_10789.field_60031)
         .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
         .withDepthWrite(!var2)
         .withCull(false)
         .withVertexFormat(class_290.field_1577, class_5596.field_27379);
      long var10 = 15690008705008L;
      int var13 = var10 < 0L ? -1 : 1;
      if (var2) {
         var9.withShaderDefine("CONTACT_SHADOW").withBlend(BlendFunction.TRANSLUCENT);
         long var13x = 15690008705007L;
         int var14 = var13x < 0L ? -1 : 1;
      } else {
         var9.withoutBlend();
      }

      return var9.build();
   }

   public static void render(WorldRenderContext var0) {
      long var1 = 77552805182487L;
      long var3 = var1 ^ 77845903399591L;
      long var5 = var1 ^ 63037201069075L;
      int var7 = (int)((var1 ^ 63037201069075L) >>> 48);
      int var8 = (int)((var1 ^ 63037201069075L) << 16 >>> 32);
      int var9 = (int)(var5 << 48 >>> 48);
      class_310 var10 = class_310.method_1551();
      if (var10.field_1687 != null && var10.field_1724 != null) {
         float var11 = var10.method_61966().method_60637(false);
         Skins3DUpdater.Data var12 = Skins3DUpdater.visual(var11);
         if (var12 != null) {
            class_243 var13 = var0.worldState().field_63082.field_63078;
            Skins3DUpdater.PoseVector var14 = Skins3DUpdater.web(var11);
            if (!(var12.position().method_1025(var13) > 4096.0) && var10.field_1687.method_22340(class_2338.method_49638(var12.position()))) {
               try {
                  GpuDevice var15 = RenderSystem.getDevice();
                  if (!var15.precompilePipeline(q).isValid()) {
                     return;
                  }

                  Skins3DMeshPipeline.SkinMesh var16 = v(var3, var12.shell());
                  if (var16 == null) {
                     return;
                  }

                  if (w == null) {
                     w = var15.createBuffer(ClientServices.label(() -> "Spider mask world material"), 136, 112L);
                     i = MemoryUtil.memAlloc(112);
                  }

                  class_243 var17 = var12.position().method_1020(var13);
                  float var18 = SkinPreset.B.t * 0.9375F;
                  Matrix4f var19 = new Matrix4f()
                     .translation((float)var17.field_1352, (float)var17.field_1351 + 0.004F, (float)var17.field_1350)
                     .rotateY(var12.yaw())
                     .rotateX(var12.pitch())
                     .rotateZ(var12.roll())
                     .scale(var18, var18 * var12.squash(), var18);
                  class_2338 var20 = class_2338.method_49638(var12.position().method_1031(0.0, 0.15, 0.0));
                  float var21 = var10.field_1687.method_8314(class_1944.field_9284, var20) / 15.0F;
                  float var22 = var10.field_1687.method_8314(class_1944.field_9282, var20) / 15.0F;
                  float var23 = (Float)var10.field_1773.method_19418().method_75756().method_75689(class_12206.field_64340, var11) * (float) (Math.PI / 180.0);
                  float var24 = var10.field_1687.method_8597().comp_642() ? Math.clamp(class_3532.method_15362(var23) * 3.0F + 0.25F, 0.0F, 1.0F) : 0.0F;
                  Vector3f var25 = new Vector3f(-class_3532.method_15374(var23), Math.max(0.28F, Math.abs(class_3532.method_15362(var23))), 0.35F).normalize();
                  CommandEncoder var26 = var15.createCommandEncoder();
                  var26.writeToBuffer(
                     w.slice(),
                     Std140Builder.intoBuffer(i.clear())
                        .putMat4f(var19)
                        .putVec4(var25.x, var25.y, var25.z, var24)
                        .putVec4(var21, var22, var10.field_1687.method_8430(var11), 0.0F)
                        .putVec4(0.0F, 0.0F, 0.0F, 0.0F)
                        .get()
                  );
                  GpuBufferSlice var27 = RenderSystem.getDynamicUniforms()
                     .method_71106(RenderSystem.getModelViewMatrix(), new Vector4f(1.0F), new Vector3f(), new Matrix4f());
                  class_276 var28 = var10.method_1522();
                  if (var28.method_71639() == null || var28.method_71640() == null) {
                     return;
                  }

                  RenderPass var29 = var26.createRenderPass(
                     () -> "Spider-Man cloth mask", var28.method_71639(), OptionalInt.empty(), var28.method_71640(), OptionalDouble.empty()
                  );

                  try {
                     var29.setVertexBuffer(0, var16.W);
                     if (var16.Y > 0 && Skins3DUpdater.resting()) {
                        var29.setPipeline(D);
                        RenderSystem.bindDefaultUniforms(var29);
                        var29.setUniform("DynamicTransforms", var27);
                        var29.setUniform("PurxiyMount", w);
                        var29.draw(0, var16.Y);
                     }

                     var29.setPipeline(q);
                     RenderSystem.bindDefaultUniforms(var29);
                     var29.setUniform("DynamicTransforms", var27);
                     var29.setUniform("PurxiyMount", w);
                     var29.draw(var16.Y, var16.f - var16.Y);
                  } catch (Throwable var33) {
                     if (var29 != null) {
                        try {
                           var29.close();
                        } catch (Throwable var32) {
                           var33.addSuppressed(var32);
                        }
                     }

                     throw var33;
                  }

                  if (var29 != null) {
                     var29.close();
                  }

                  if (var14 != null && var14.strength() > 0.0F) {
                     q(var26, var15, var14, var13, (char)var7, var25, var8, var24, var21, (char)var9, var22, var10, var11, var28);
                  }

                  class_276 var35 = var0.worldRenderer().method_29360();
                  if (var35 != null) {
                     var35.method_29329(var28);
                  }

                  h = null;
               } catch (RuntimeException var341) {
                  if (!var341.toString().equals(h)) {
                     h = var341.toString();
                     ClientServices.LOGGER.error("Could not draw Spider-Man's cloth mask", var341);
                  }
               }
            }
         }
      }
   }

   private static Skins3DMeshPipeline.SkinMesh v(long var0, boolean var2) {
      long var3 = 107071818671015L;
      SkinPreset.ensureSourceClassInitialized();
      Skins3DMeshPipeline.SkinMesh var5 = var2 ? S : E;
      if (var5 != null) {
         return var5;
      } else {
         Skins3DBuilder.SkinQuad var6 = Skins3DFactory.model(var2 ? SkinPreset.H : SkinPreset.q);
         if (var6 == null) {
            return null;
         } else {
            if (f == null) {
               try (InputStream var7 = class_310.method_1551()
                     .method_1478()
                     .getResourceOrThrow(ClientServices.id("textures/skins3d/spiderman.png"))
                     .method_14482()) {
                  long var8 = 293165868719L;
                  int var10 = var8 < 0L ? -1 : 1;
                  class_1011 var11 = class_1011.method_4309(var7);

                  try {
                     long var12 = 293165868720L;
                     int var49 = var12 < 0L ? -1 : 1;
                     f = var11.method_61942();
                  } catch (Throwable var36) {
                     if (var11 != null) {
                        try {
                           var11.close();
                        } catch (Throwable var35) {
                           var36.addSuppressed(var35);
                        }
                     }

                     throw var36;
                  }

                  if (var11 != null) {
                     var11.close();
                  }
               } catch (IOException var39) {
                  throw new UncheckedIOException(var39);
               }
            }

            Skins3DBuilder.SkinVertex var43 = var6.R[0];
            int var44 = var2 ? 0 : 6;
            int var9 = var43.p * 6 + var44;
            ByteBuffer var10 = MemoryUtil.memAlloc(var9 * x);

            Skins3DMeshPipeline.SkinMesh var59;
            try {
               if (var44 > 0) {
                  for (int var14 : new int[]{0, 1, 2, 0, 2, 3}) {
                     long var15 = 293165868720L;
                     int var56 = var15 < 0L ? -1 : 1;
                     float var18x = var14 != 0 && var14 != 3 ? 0.24F : -0.24F;
                     float var19 = var14 < 2 ? -0.32F : 0.32F;
                     m(var3, var10, var18x, 0.001F, var19, var18x / 0.24F, var19 / 0.32F, 1694498816, 0, 1, 0);
                  }
               }

               for (int var46 = 0; var46 < var43.p; var46++) {
                  int var49 = f[var43.texelV(var46) * 64 + var43.texelU(var46)];
                  int[] var50 = Skins3DBuilder.C[var43.normal(var46)];
                  int[] var51 = new int[]{0, 1, 2, 0, 2, 3};
                  long var53 = 293165868720L;
                  int var581 = var53 < 0L ? -1 : 1;

                  for (int var21 : var51) {
                     long var22 = 293165868720L;
                     int var26 = var22 < 0L ? -1 : 1;
                     float var26x = var43.coordinate(var46, var21, 0, 0.0F) / 16.0F;
                     float var27 = -(var43.coordinate(var46, var21, 1, 0.0F) + (var2 ? 3.8F : 0.0F)) / 16.0F;
                     float var28 = var43.coordinate(var46, var21, 2, 0.0F) / 16.0F;
                     m(var3, var10, var26x, var27, var28, 0.0F, 0.015F, var49, var50[0], -var50[1], var50[2]);
                  }

                  long var56 = 293165868720L;
                  int var64 = var56 < 0L ? -1 : 1;
               }

               long var47 = 293165868720L;
               int var51 = var47 < 0L ? -1 : 1;
               var10.flip();
               GpuBuffer var52 = RenderSystem.getDevice().createBuffer(ClientServices.label(() -> "Empty voxel mask " + var2), 32, var10);
               long var54 = 293165868719L;
               int var17 = var54 < 0L ? -1 : 1;
               var5 = new Skins3DMeshPipeline.SkinMesh(var52, var9, var44);
               long var17x = 293165868720L;
               int var62 = var17x < 0L ? -1 : 1;
               if (var2) {
                  long var58 = 293165868719L;
                  int var65 = var58 < 0L ? -1 : 1;
                  S = var5;
               } else {
                  E = var5;
               }

               var59 = var5;
            } finally {
               MemoryUtil.memFree(var10);
            }

            return var59;
         }
      }
   }

   private static void m(long var0, ByteBuffer var2, float var3, float var4, float var5, float var6, float var7, int var8, int var9, int var10, int var11) {
      var2.putFloat(var3).putFloat(var4).putFloat(var5).putFloat(var6).putFloat(var7);
      var2.put((byte)(var8 >> 16)).put((byte)(var8 >> 8)).put((byte)var8).put((byte)(var8 >>> 24));
      var2.put((byte)(var9 * 127)).put((byte)(var10 * 127)).put((byte)(var11 * 127)).put((byte)0);
   }

   private static void q(
      CommandEncoder var0,
      GpuDevice var1,
      Skins3DUpdater.PoseVector var2,
      class_243 var3,
      char var4,
      Vector3f var5,
      int var6,
      float var7,
      float var8,
      char var9,
      float var10,
      class_310 var11,
      float var12,
      class_276 var13
   ) {
      SkinPreset.ensureSourceClassInitialized();
      byte var14 = 72;
      int var15 = 0;
      if (N == null) {
         N = var1.createBuffer(ClientServices.label(() -> "Spider-Man web strand"), 40, var14 * x);
         F = MemoryUtil.memAlloc(var14 * x);
         L = var1.createBuffer(ClientServices.label(() -> "Spider-Man web material"), 136, 112L);
         k = MemoryUtil.memAlloc(112);
      }

      class_243 var16 = var2.from().method_1020(var3);
      class_243 var17 = var2.to().method_1020(var3);
      class_243 var18 = var17.method_1020(var16);
      double var19 = var18.method_1033();
      if (!(var19 < 0.001)) {
         ByteBuffer var21 = F.clear();
         float var22 = 0.022F * (0.5F + 0.5F * var2.strength());

         for (int var23 = 0; var23 < 12; var23++) {
            class_243 var24 = a(var16, var18, var19, var23 / 12.0F);
            class_243 var25 = a(var16, var18, var19, (var23 + 1) / 12.0F);
            class_243 var26 = var25.method_1020(var24).method_1036(var24).method_1029().method_1021(var22);
            boolean var34 = false;
            if (!Double.isFinite(var26.field_1352) || var26.method_1027() < 1.0E-12) {
               var26 = new class_243(var22, 0.0, 0.0);
            }

            class_243[] var27 = new class_243[]{var24.method_1020(var26), var24.method_1019(var26), var25.method_1019(var26), var25.method_1020(var26)};

            for (int var31 : new int[]{0, 1, 2, 0, 2, 3}) {
               var21.putFloat((float)var27[var31].field_1352)
                  .putFloat((float)var27[var31].field_1351)
                  .putFloat((float)var27[var31].field_1350)
                  .putFloat(0.0F)
                  .putFloat(0.015F);
               var21.put((byte)-12).put((byte)-10).put((byte)-6).put((byte)-1);
               var21.put((byte)0).put((byte)127).put((byte)0).put((byte)0);
            }
         }

         var21.flip();
         var0.writeToBuffer(N.slice(), var21);
         var0.writeToBuffer(
            L.slice(),
            Std140Builder.intoBuffer(k.clear())
               .putMat4f(new Matrix4f())
               .putVec4(var5.x, var5.y, var5.z, var7)
               .putVec4(var8, var10, var11.field_1687.method_8430(var12), 0.0F)
               .putVec4(0.0F, 0.0F, 0.0F, 0.0F)
               .get()
         );
         RenderPass var37 = var0.createRenderPass(
            () -> "Spider-Man web", var13.method_71639(), OptionalInt.empty(), var13.method_71640(), OptionalDouble.empty()
         );

         try {
            var15 = 2145146525;
            var37.setVertexBuffer(0, N);
            var37.setPipeline(q);
            var15 = 2145146525;
            RenderSystem.bindDefaultUniforms(var37);
            var37.setUniform(
               "DynamicTransforms",
               RenderSystem.getDynamicUniforms().method_71106(RenderSystem.getModelViewMatrix(), new Vector4f(1.0F), new Vector3f(), new Matrix4f())
            );
            var37.setUniform("PurxiyMount", L);
            var37.draw(0, var14);
         } catch (Throwable var33) {
            if (var37 != null) {
               try {
                  var37.close();
               } catch (Throwable var32) {
                  var33.addSuppressed(var32);
               }
            }

            throw var33;
         }

         if (var37 != null) {
            var37.close();
         }
      }
   }

   private static class_243 a(class_243 var0, class_243 var1, double var2, float var4) {
      double var5 = Math.sin(Math.PI * var4) * Math.min(0.18, var2 * 0.04);
      return var0.method_1019(var1.method_1021(var4)).method_1031(0.0, -var5, 0.0);
   }

   public static void close() {
      if (N != null) {
         N.close();
      }

      if (L != null) {
         L.close();
      }

      if (F != null) {
         MemoryUtil.memFree(F);
      }

      if (k != null) {
         MemoryUtil.memFree(k);
      }

      L = null;
      N = null;
      k = null;
      F = null;
      if (S != null) {
         S.W.close();
      }

      if (E != null) {
         E.W.close();
      }

      if (w != null) {
         w.close();
      }

      if (i != null) {
         MemoryUtil.memFree(i);
      }

      E = null;
      S = null;
      w = null;
      i = null;
      f = null;
      h = null;
   }

   private record SkinMesh(GpuBuffer W, int f, int Y) {
      public GpuBuffer vertices() {
         return this.W;
      }

      public int count() {
         return this.f;
      }

      public int shadow() {
         return this.Y;
      }

      public GpuBuffer gpuBufferState27() {
         return this.W;
      }

      public int integerState3() {
         return this.f;
      }

      public int integerState42() {
         return this.Y;
      }
   }
}
