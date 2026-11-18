// Sourced By DevFFNightmare
package dev.purxiy.profile;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientBuilder;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.ui.Theme;
import java.nio.ByteBuffer;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import net.minecraft.class_10789;
import net.minecraft.class_12137;
import net.minecraft.class_290;
import net.minecraft.class_6367;
import net.minecraft.class_7920;
import net.minecraft.class_8685;
import net.minecraft.class_12079.class_12081;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import org.lwjgl.system.MemoryUtil;

public final class ProfileRenderer10 {
   private static final int STRIDE = class_290.field_1577.getVertexSize();
   private static final int UNIFORM_BYTES = 160;
   private static final int MAX_VERTICES = 480468;
   public static final float HEIGHT = 32.5F;
   private static final RenderPipeline PIPELINE = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/profile_avatar"))
      .withVertexShader(ClientServices.id("core/profile_avatar"))
      .withFragmentShader(ClientServices.id("core/profile_avatar"))
      .withUniform("ProfileAvatar", class_10789.field_60031)
      .withUniform("PurxiyTheme", class_10789.field_60031)
      .withSampler("Sampler0")
      .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
      .withDepthWrite(true)
      .withCull(false)
      .withoutBlend()
      .withVertexFormat(class_290.field_1577, class_5596.field_27379)
      .build();
   
   private static class_6367 target;
   
   private static GpuBuffer vertexBuffer;
   
   private static GpuBuffer uniforms;
   
   private static ByteBuffer vertices;
   
   private static ByteBuffer staging;
   
   private static String lastError;
   private static final Matrix4f PROJECTION = new Matrix4f();
   private static final Matrix4f VIEW = new Matrix4f();
   private static final Matrix4f VIEW_PROJECTION = new Matrix4f();
   private static final Matrix4f ROOT = new Matrix4f();
   private static final Matrix4f PART = new Matrix4f();
   private static final Vector3f P = new Vector3f();
   private static final Vector3f N = new Vector3f();
   private static final float[] CORNERS = new float[24];

   private ProfileRenderer10() {
   }

   public static boolean draw(
      Canvas var0,
      class_8685 var1,
      ProfileRenderer10.TypeX var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11
   ) {
      if (!(var5 <= 1.0F) && !(var6 <= 1.0F) && !(var7 <= 0.0F) && !(var0.alpha() <= 0.004F)) {
         GpuTextureView var12 = ProfileSupport5.view(var1.comp_1626().comp_3627());
         if (var12 == null) {
            return false;
         } else {
            class_12081 var13 = var1.comp_1627();
            GpuTextureView var14 = var13 == null ? null : ProfileSupport5.view(var13.comp_3627());

            try {
               GpuDevice var15 = RenderSystem.getDevice();
               if (!var15.precompilePipeline(PIPELINE).isValid()) {
                  return false;
               } else {
                  float var16 = (float)Math.hypot(var0.transform().m00(), var0.transform().m01());
                  float var17 = (float)Math.hypot(var0.transform().m10(), var0.transform().m11());
                  float var18 = Math.min(4096, var15.getMaxTextureSize());
                  float var19 = Math.min(Math.max(0.5F, 2.0F * var0.pixelScale() * Math.max(var16, var17)), Math.min(var18 / var5, var18 / var6));
                  int var20 = Math.max(1, Math.round(var5 * var19));
                  int var21 = Math.max(1, Math.round(var6 * var19));
                  ensureResources(var20, var21);
                  ByteBuffer var22 = vertices;
                  var22.clear();
                  int var23 = mesh(var22, var2, var1.comp_1629() == class_7920.field_41122, ClientBuilder.layers(var1));
                  int var24 = var14 != null ? cape(var22, var2) : 0;
                  var22.flip();
                  CommandEncoder var25 = var15.createCommandEncoder();
                  var25.writeToBuffer(vertexBuffer.slice(0L, var22.remaining()), var22);
                  camera(var5, var6, var7, var8);
                  float var26 = var9 >= 0.999F ? 1000.0F : -3.0F + var9 * 38.5F;
                  int var27 = Theme.tint(-4357889);
                  Std140Builder var28 = Std140Builder.intoBuffer(staging.clear())
                     .putMat4f(VIEW_PROJECTION)
                     .putMat4f(VIEW)
                     .putVec4(var26, 3.2F, var11, var10)
                     .putVec4(0.74F * BlendMode.red(var27) / 189.0F, 0.5F * BlendMode.green(var27) / 128.0F, 1.0F * BlendMode.blue(var27) / 255.0F, 0.5F);
                  var25.writeToBuffer(uniforms.slice(), var28.get());
                  GpuBuffer var29 = Theme.uniforms();
                  class_12137 var30 = ProfileSupport5.pixels();

                  try (RenderPass var31 = var25.createRenderPass(
                        () -> "Purxiy profile avatar", target.method_71639(), OptionalInt.of(0), target.method_71640(), OptionalDouble.of(1.0)
                     )) {
                     var31.setPipeline(PIPELINE);
                     var31.setUniform("ProfileAvatar", uniforms);
                     var31.setUniform("PurxiyTheme", var29);
                     var31.setVertexBuffer(0, vertexBuffer);
                     var31.bindTexture("Sampler0", var12, var30);
                     var31.draw(0, var23);
                     if (var24 > 0) {
                        var31.bindTexture("Sampler0", var14, ProfileSupport5.capeSampler(var1));
                        var31.draw(var23, var24);
                     }
                  }

                  var0.image(
                     target.method_71639(),
                     RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR),
                     true,
                     var3,
                     var4,
                     var5,
                     var6,
                     0.0F,
                     1.0F,
                     1.0F,
                     0.0F,
                     0.0F,
                     -1
                  );
                  lastError = null;
                  return true;
               }
            } catch (RuntimeException var36) {
               if (!var36.toString().equals(lastError)) {
                  ClientServices.LOGGER.error("Purxiy profile avatar could not render", var36);
               }

               lastError = var36.toString();
               return false;
            }
         }
      } else {
         return false;
      }
   }

   private static void camera(float var0, float var1, float var2, float var3) {
      float var4 = (float)Math.toRadians(16.0);
      float var5 = var1 / var2;
      float var6 = var5 / (2.0F * (float)Math.tan(var4 / 2.0F));
      float var7 = (var1 / 2.0F - var3) / var2;
      float var8 = (float)Math.toRadians(7.0);
      PROJECTION.setPerspective(var4, var0 / var1, Math.max(1.0F, var6 - 60.0F), var6 + 60.0F);
      VIEW.setLookAt(0.0F, var7 + var6 * (float)Math.sin(var8), var6 * (float)Math.cos(var8), 0.0F, var7, 0.0F, 0.0F, 1.0F, 0.0F);
      PROJECTION.mul(VIEW, VIEW_PROJECTION);
   }

   private static int mesh(ByteBuffer var0, ProfileRenderer10.TypeX var1, boolean var2,  ClientBuilder.TypeE var3) {
      float var4 = ClientBuilder.yourHeight();
      ROOT.identity().translate(0.0F, 24.0F * var4 + var1.lift, 0.0F).rotateY(var1.yaw).scale(var4, -var4, -var4);
      float var5 = var1.time * 20.0F;
      float var6 = (float)Math.cos(var5 * 0.09F) * 0.05F + 0.05F;
      float var7 = (float)Math.sin(var5 * 0.067F) * 0.05F;
      float var8 = smooth(var1.wave);
      float var9 = 2.55F + 0.32F * (float)Math.sin(var1.time * 9.5F);
      int var10 = 0;
      boolean var11 = var3 == null || !var3.fullModel();
      float[][] var12 = var11 ? ClientBuilder.PIVOT : var3.pivots();
      boolean var13 = var3 != null && var3.rigidHead();
      part(var12[0][0], var12[0][1], var12[0][2], var13 ? 0.0F : var1.headPitch, var13 ? 0.0F : var1.headYaw, 0.0F);
      ClientBuilder.build(PART, 0);
      if (var11) {
         var10 += box(var0, -4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0, 0, 0.0F, 64.0F, 64.0F, 0);
      }

      var10 += outer(var0, var3, 0, -4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 32, 0, 0.5F);
      part(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
      ClientBuilder.build(PART, 1);
      if (var11) {
         var10 += box(var0, -4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 16, 16, 0.0F, 64.0F, 64.0F, 0);
      }

      var10 += outer(var0, var3, 1, -4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 16, 32, 0.25F);
      float var14 = var2 ? 3.0F : 4.0F;
      part(var12[2][0] * ClientBuilder.armSpread(), var12[2][1], var12[2][2], lerp(var7, -0.25F, var8), 0.0F, lerp(var6, var9, var8));
      if (var11) {
         var10 += box(var0, var2 ? -2.0F : -3.0F, -2.0F, -2.0F, var14, 12.0F, 4.0F, 40, 16, 0.0F, 64.0F, 64.0F, 0);
      }

      var10 += outer(var0, var3, 2, var2 ? -2.0F : -3.0F, -2.0F, -2.0F, var14, 12.0F, 4.0F, 40, 32, 0.25F);
      part(var12[3][0] * ClientBuilder.armSpread(), var12[3][1], var12[3][2], -var7, 0.0F, -var6);
      if (var11) {
         var10 += box(var0, -1.0F, -2.0F, -2.0F, var14, 12.0F, 4.0F, 32, 48, 0.0F, 64.0F, 64.0F, 0);
      }

      var10 += outer(var0, var3, 3, -1.0F, -2.0F, -2.0F, var14, 12.0F, 4.0F, 48, 48, 0.25F);
      part(var12[4][0] * ClientBuilder.bodyWidth(), var12[4][1], var12[4][2], 0.0F, 0.0F, 0.0F);
      ClientBuilder.build(PART, 4);
      if (var11) {
         var10 += box(var0, -2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0, 16, 0.0F, 64.0F, 64.0F, 0);
      }

      var10 += outer(var0, var3, 4, -2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0, 32, 0.25F);
      part(var12[5][0] * ClientBuilder.bodyWidth(), var12[5][1], var12[5][2], 0.0F, 0.0F, 0.0F);
      ClientBuilder.build(PART, 5);
      if (var11) {
         var10 += box(var0, -2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 16, 48, 0.0F, 64.0F, 64.0F, 0);
      }

      return var10 + outer(var0, var3, 5, -2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0, 48, 0.25F);
   }

   private static int outer(
      ByteBuffer var0,
       ClientBuilder.TypeE var1,
      int var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      int var9,
      int var10,
      float var11
   ) {
      return var1 == null
         ? box(var0, var3, var4, var5, var6, var7, var8, var9, var10, var11, 64.0F, 64.0F, 1)
         : ClientBuilder.triangles(var0, var1, var2, PART);
   }

   private static int cape(ByteBuffer var0, ProfileRenderer10.TypeX var1) {
      PART.set(ROOT);
      ClientBuilder.build(PART, 1);
      PART.translate(0.0F, 0.0F, 2.0F).rotateX((float)Math.toRadians(var1.capeLean)).rotateY((float) Math.PI);
      return box(var0, -5.0F, 0.0F, -1.0F, 10.0F, 16.0F, 1.0F, 0, 0, 0.0F, 64.0F, 32.0F, 0);
   }

   private static void part(float var0, float var1, float var2, float var3, float var4, float var5) {
      PART.set(ROOT).translate(var0, var1, var2).rotateZYX(var5, var4, var3);
   }

   private static int box(
      ByteBuffer var0,
      float var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      int var7,
      int var8,
      float var9,
      float var10,
      float var11,
      int var12
   ) {
      float var13 = var1 - var9;
      float var14 = var2 - var9;
      float var15 = var3 - var9;
      float var16 = var1 + var4 + var9;
      float var17 = var2 + var5 + var9;
      float var18 = var3 + var6 + var9;
      float[] var19 = CORNERS;
      set(var19, 0, var13, var14, var15);
      set(var19, 1, var16, var14, var15);
      set(var19, 2, var16, var17, var15);
      set(var19, 3, var13, var17, var15);
      set(var19, 4, var13, var14, var18);
      set(var19, 5, var16, var14, var18);
      set(var19, 6, var16, var17, var18);
      set(var19, 7, var13, var17, var18);
      float var20 = var7;
      float var21 = var7 + var6;
      float var22 = var7 + var6 + var4;
      float var23 = var7 + var6 + var4 + var4;
      float var24 = var7 + var6 + var4 + var6;
      float var25 = var7 + var6 + var4 + var6 + var4;
      float var26 = var8;
      float var27 = var8 + var6;
      float var28 = var8 + var6 + var5;
      int var29 = 0;
      var29 += face(var0, 5, 4, 0, 1, var21, var26, var22, var27, 0.0F, -1.0F, 0.0F, var10, var11, var12);
      var29 += face(var0, 2, 3, 7, 6, var22, var27, var23, var26, 0.0F, 1.0F, 0.0F, var10, var11, var12);
      var29 += face(var0, 0, 4, 7, 3, var20, var27, var21, var28, -1.0F, 0.0F, 0.0F, var10, var11, var12);
      var29 += face(var0, 1, 0, 3, 2, var21, var27, var22, var28, 0.0F, 0.0F, -1.0F, var10, var11, var12);
      var29 += face(var0, 5, 1, 2, 6, var22, var27, var24, var28, 1.0F, 0.0F, 0.0F, var10, var11, var12);
      return var29 + face(var0, 4, 5, 6, 7, var24, var27, var25, var28, 0.0F, 0.0F, 1.0F, var10, var11, var12);
   }

   private static int face(
      ByteBuffer var0,
      int var1,
      int var2,
      int var3,
      int var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12,
      float var13,
      int var14
   ) {
      PART.transformDirection(N.set(var9, var10, var11)).normalize();
      vertex(var0, var1, var7 / var12, var6 / var13, var14);
      vertex(var0, var2, var5 / var12, var6 / var13, var14);
      vertex(var0, var3, var5 / var12, var8 / var13, var14);
      vertex(var0, var1, var7 / var12, var6 / var13, var14);
      vertex(var0, var3, var5 / var12, var8 / var13, var14);
      vertex(var0, var4, var7 / var12, var8 / var13, var14);
      return 6;
   }

   private static void vertex(ByteBuffer var0, int var1, float var2, float var3, int var4) {
      PART.transformPosition(P.set(CORNERS[var1 * 3], CORNERS[var1 * 3 + 1], CORNERS[var1 * 3 + 2]));
      var0.putFloat(P.x).putFloat(P.y).putFloat(P.z);
      var0.putFloat(var2).putFloat(var3);
      var0.put((byte)-1).put((byte)-1).put((byte)-1).put((byte)(var4 == 0 ? 0 : 255));
      var0.put((byte)Math.round(N.x * 127.0F)).put((byte)Math.round(N.y * 127.0F)).put((byte)Math.round(N.z * 127.0F)).put((byte)0);
   }

   private static void set(float[] var0, int var1, float var2, float var3, float var4) {
      var0[var1 * 3] = var2;
      var0[var1 * 3 + 1] = var3;
      var0[var1 * 3 + 2] = var4;
   }

   private static void ensureResources(int var0, int var1) {
      GpuDevice var2 = RenderSystem.getDevice();
      if (target == null) {
         target = new class_6367("Purxiy profile avatar", var0, var1, true);
      } else if (target.field_1482 != var0 || target.field_1481 != var1) {
         target.method_1234(var0, var1);
      }

      if (uniforms == null) {
         uniforms = var2.createBuffer(() -> "Purxiy profile avatar camera", 136, 160L);
         staging = MemoryUtil.memAlloc(160);
      }

      if (vertices == null) {
         vertices = MemoryUtil.memAlloc(480468 * STRIDE);
         vertexBuffer = var2.createBuffer(() -> "Purxiy profile avatar mesh", 40, 480468 * STRIDE);
      }
   }

   private static float smooth(float var0) {
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static float lerp(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   public static final class TypeX {
      public float yaw;
      public float headYaw;
      public float headPitch;
      public float time;
      public float wave;
      public float lift;
      public float capeLean = 6.0F;
   }
}
