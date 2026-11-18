// Sourced By DevFFNightmare
package dev.purxiy.modules.wings;

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
import dev.purxiy.loader.fabric.client.ClientCache;
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

public final class WingsRenderer7 {
   private static final int a = class_290.field_1577.getVertexSize();
   private static final int f = 160;
   private static final int D = 480468;
   public static final float H = 32.5F;
   private static final RenderPipeline U = RenderPipeline.builder()
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
   
   private static class_6367 k;
   
   private static GpuBuffer F;
   
   private static GpuBuffer C;
   
   private static ByteBuffer v;
   
   private static ByteBuffer q;
   
   private static String s;
   private static final Matrix4f B = new Matrix4f();
   private static final Matrix4f x = new Matrix4f();
   private static final Matrix4f N = new Matrix4f();
   private static final Matrix4f l = new Matrix4f();
   private static final Matrix4f A = new Matrix4f();
   private static final Vector3f Z = new Vector3f();
   private static final Vector3f c = new Vector3f();
   private static final float[] y = new float[24];

   private static final String[] d = new String[17];


   private static final long h = 160L;

   private WingsRenderer7() {
   }

   public static boolean draw(
      Canvas var0,
      class_8685 var1,
      WingsRenderer7.TypeU var2,
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
      long var12 = 83148062834007L;
      long var14 = var12 ^ 41743396478983L;
      int var16 = (int)((var12 ^ 41743396478983L) >>> 32);
      int var17 = (int)((var12 ^ 41743396478983L) << 32 >>> 48);
      int var18 = (int)(var14 << 48 >>> 48);
      long var19 = var12 ^ 80463520934631L;
      var14 = var12 ^ 63395934246237L;
      int var21 = (int)((var12 ^ 63395934246237L) >>> 32);
      int var22 = (int)((var12 ^ 63395934246237L) << 32 >>> 48);
      int var23 = (int)(var14 << 48 >>> 48);
      if (!(var5 <= 1.0F) && !(var6 <= 1.0F) && !(var7 <= 0.0F) && !(var0.alpha() <= 0.004F)) {
         GpuTextureView var24 = ClientBuilder.view(var1.comp_1626().comp_3627());
         if (var24 == null) {
            return false;
         } else {
            class_12081 var25 = var1.comp_1627();
            GpuTextureView var26 = var25 == null ? null : ClientBuilder.view(var25.comp_3627());

            try {
               GpuDevice var27 = RenderSystem.getDevice();
               if (!var27.precompilePipeline(U).isValid()) {
                  return false;
               } else {
                  float var28 = (float)Math.hypot(var0.transform().m00(), var0.transform().m01());
                  float var29 = (float)Math.hypot(var0.transform().m10(), var0.transform().m11());
                  float var30 = Math.min(4096, var27.getMaxTextureSize());
                  float var31 = Math.min(Math.max(0.5F, 2.0F * var0.pixelScale() * Math.max(var28, var29)), Math.min(var30 / var5, var30 / var6));
                  int var32 = Math.max(1, Math.round(var5 * var31));
                  int var33 = Math.max(1, Math.round(var6 * var31));
                  b(var32, var33, var21, var22, var23);
                  ByteBuffer var34 = v;
                  var34.clear();
                  int var35 = m(var34, var2, var1.comp_1629() == class_7920.field_41122, var16, ClientBuilder.layers(var1), (short)var17, (short)var18);
                  int var36 = var26 != null ? S(var19, var34, var2) : 0;
                  var34.flip();
                  CommandEncoder var37 = var27.createCommandEncoder();
                  var37.writeToBuffer(F.slice(0L, var34.remaining()), var34);
                  k(var5, var6, var7, var8);
                  float var38 = var9 >= 0.999F ? 1000.0F : -3.0F + var9 * 38.5F;
                  int var39 = Theme.tint(-4357889);
                  Std140Builder var40 = Std140Builder.intoBuffer(q.clear())
                     .putMat4f(N)
                     .putMat4f(x)
                     .putVec4(var38, 3.2F, var11, var10)
                     .putVec4(0.74F * BlendMode.red(var39) / 189.0F, 0.5F * BlendMode.green(var39) / 128.0F, 1.0F * BlendMode.blue(var39) / 255.0F, 0.5F);
                  var37.writeToBuffer(C.slice(), var40.get());
                  GpuBuffer var41 = Theme.uniforms();
                  class_12137 var42 = ClientBuilder.pixels();

                  try (RenderPass var43 = var37.createRenderPass(
                        () -> "Purxiy profile avatar", k.method_71639(), OptionalInt.of(0), k.method_71640(), OptionalDouble.of(1.0)
                     )) {
                     var43.setPipeline(U);
                     var43.setUniform("ProfileAvatar", C);
                     var43.setUniform("PurxiyTheme", var41);
                     var43.setVertexBuffer(0, F);
                     var43.bindTexture("Sampler0", var24, var42);
                     int var44 = 1790950941;
                     var43.draw(0, var35);
                     int var45 = (int)var12;
                     WingsUpdater2.metric("drawn");
                     if (var36 > 0) {
                        var43.bindTexture("Sampler0", var26, ClientBuilder.capeSampler(var1));
                        var44 = 1790950936;
                        var43.draw(var35, var36);
                        var45 = (int)var12;
                        WingsUpdater2.metric("drawn");
                     }
                  }

                  var0.image(
                     k.method_71639(),
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
                  s = null;
                  return true;
               }
            } catch (RuntimeException var48) {
               if (!var48.toString().equals(s)) {
                  ClientServices.LOGGER.error("Purxiy profile avatar could not render", var48);
               }

               s = var48.toString();
               return false;
            }
         }
      } else {
         return false;
      }
   }

   private static void k(float var0, float var1, float var2, float var3) {
      float var4 = (float)Math.toRadians(16.0);
      float var5 = var1 / var2;
      float var6 = var5 / (2.0F * (float)Math.tan(var4 / 2.0F));
      float var7 = (var1 / 2.0F - var3) / var2;
      float var8 = (float)Math.toRadians(7.0);
      B.setPerspective(var4, var0 / var1, Math.max(1.0F, var6 - 60.0F), var6 + 60.0F);
      x.setLookAt(0.0F, var7 + var6 * (float)Math.sin(var8), var6 * (float)Math.cos(var8), 0.0F, var7, 0.0F, 0.0F, 1.0F, 0.0F);
      B.mul(x, N);
   }

   private static int m(ByteBuffer var0, WingsRenderer7.TypeU var1, boolean var2,  int var3, ClientBuilder.TypeE var4, short var5, short var6) {
      long var7 = 121394279882064L;
      long var9 = var7 ^ 25827732830078L;
      long var11 = var7 ^ 51532467507184L;
      long var13 = var7 ^ 137629239768502L;
      float var15 = ClientBuilder.yourHeight();
      l.identity().translate(0.0F, 24.0F * var15 + var1.H, 0.0F).rotateY(var1.a).scale(var15, -var15, -var15);
      float var16 = var1.L * 20.0F;
      WingsUpdater2.ensureSourceClassInitialized();
      float var17 = (float)Math.cos(var16 * 0.09F) * 0.05F + 0.05F;
      float var18 = (float)Math.sin(var16 * 0.067F) * 0.05F;
      float var19 = A(var1.S, var9);
      float var20 = 2.55F + 0.32F * (float)Math.sin(var1.L * 9.5F);
      int var21 = 0;
      boolean var22 = var4 == null || !var4.fullModel();
      float[][] var23 = var22 ? ClientBuilder.PIVOT : var4.pivots();
      boolean var24 = var4 != null && var4.rigidHead();
      l(var23[0][0], var23[0][1], var23[0][2], var24 ? 0.0F : var1.d, var24 ? 0.0F : var1.R, 0.0F);
      short var25 = 7504;
      ClientBuilder.build(A, 0);
      var25 = 28264;
      if (var22) {
         var21 += s(var0, -4.0F, -8.0F, -4.0F, 8.0F, var13, 8.0F, 8.0F, 0, 0, 0.0F, 64.0F, 64.0F, 0);
      }

      float var26 = 0.5F;
      byte var27 = 0;
      byte var28 = 32;
      float var29 = 8.0F;
      float var30 = 8.0F;
      float var31 = 8.0F;
      float var32 = -4.0F;
      float var33 = -8.0F;
      float var34 = -4.0F;
      byte var35 = 0;
      int var38 = var21 + c(var11, var0, var4, var35, var34, var33, var32, var31, var30, var29, var28, var27, var26);
      l(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
      ClientBuilder.build(A, 1);
      var25 = 7504;
      if (var22) {
         var38 += s(var0, -4.0F, 0.0F, -2.0F, 8.0F, var13, 12.0F, 4.0F, 16, 16, 0.0F, 64.0F, 64.0F, 0);
      }

      var26 = 0.25F;
      var27 = 32;
      var28 = 16;
      var29 = 4.0F;
      var30 = 12.0F;
      var31 = 8.0F;
      var32 = -2.0F;
      var33 = 0.0F;
      var34 = -4.0F;
      var35 = 1;
      int var39 = var38 + c(var11, var0, var4, var35, var34, var33, var32, var31, var30, var29, var28, var27, var26);
      float var40 = var2 ? 3.0F : 4.0F;
      l(var23[2][0] * ClientBuilder.armSpread(), var23[2][1], var23[2][2], i(var18, -0.25F, var19), 0.0F, i(var17, var20, var19));
      if (var22) {
         var39 += s(var0, var2 ? -2.0F : -3.0F, -2.0F, -2.0F, var40, var13, 12.0F, 4.0F, 40, 16, 0.0F, 64.0F, 64.0F, 0);
      }

      float var41 = var2 ? -2.0F : -3.0F;
      var26 = 0.25F;
      var27 = 32;
      var28 = 40;
      var29 = 4.0F;
      var30 = 12.0F;
      var32 = -2.0F;
      var33 = -2.0F;
      var35 = 2;
      int var42 = var39 + c(var11, var0, var4, var35, var41, var33, var32, var40, var30, var29, var28, var27, var26);
      l(var23[3][0] * ClientBuilder.armSpread(), var23[3][1], var23[3][2], -var18, 0.0F, -var17);
      if (var22) {
         var42 += s(var0, -1.0F, -2.0F, -2.0F, var40, var13, 12.0F, 4.0F, 32, 48, 0.0F, 64.0F, 64.0F, 0);
      }

      var26 = 0.25F;
      var27 = 48;
      var28 = 48;
      var29 = 4.0F;
      var30 = 12.0F;
      var32 = -2.0F;
      var33 = -2.0F;
      var34 = -1.0F;
      var35 = 3;
      int var43 = var42 + c(var11, var0, var4, var35, var34, var33, var32, var40, var30, var29, var28, var27, var26);
      var25 = 28264;
      int var44 = var43;
      l(var23[4][0] * ClientBuilder.bodyWidth(), var23[4][1], var23[4][2], 0.0F, 0.0F, 0.0F);
      ClientBuilder.build(A, 4);
      if (var22) {
         var44 = var43 + s(var0, -2.0F, 0.0F, -2.0F, 4.0F, var13, 12.0F, 4.0F, 0, 16, 0.0F, 64.0F, 64.0F, 0);
      }

      var26 = 0.25F;
      var27 = 32;
      var28 = 0;
      var29 = 4.0F;
      var30 = 12.0F;
      var31 = 4.0F;
      var32 = -2.0F;
      var33 = 0.0F;
      var34 = -2.0F;
      var35 = 4;
      int var45 = var44 + c(var11, var0, var4, var35, var34, var33, var32, var31, var30, var29, var28, var27, var26);
      l(var23[5][0] * ClientBuilder.bodyWidth(), var23[5][1], var23[5][2], 0.0F, 0.0F, 0.0F);
      ClientBuilder.build(A, 5);
      if (var22) {
         var45 += s(var0, -2.0F, 0.0F, -2.0F, 4.0F, var13, 12.0F, 4.0F, 16, 48, 0.0F, 64.0F, 64.0F, 0);
      }

      var26 = 0.25F;
      var27 = 48;
      var28 = 0;
      var29 = 4.0F;
      var30 = 12.0F;
      var31 = 4.0F;
      var32 = -2.0F;
      var33 = 0.0F;
      var34 = -2.0F;
      var35 = 5;
      return var45 + c(var11, var0, var4, var35, var34, var33, var32, var31, var30, var29, var28, var27, var26);
   }

   private static int c(
      long var0,
       ByteBuffer var2,
      ClientBuilder.TypeE var3,
      int var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      int var11,
      int var12,
      float var13
   ) {
      long var14 = 21182830897382L;
      return var3 == null
         ? s(var2, var5, var6, var7, var8, var14, var9, var10, var11, var12, var13, 64.0F, 64.0F, 1)
         : ClientBuilder.triangles(var2, var3, var4, A);
   }

   private static int S(long var0, ByteBuffer var2, WingsRenderer7.TypeU var3) {
      long var4 = 21182830897382L;
      A.set(l);
      ClientBuilder.build(A, 1);
      A.translate(0.0F, 0.0F, 2.0F).rotateX((float)Math.toRadians(var3.N)).rotateY((float) Math.PI);
      return s(var2, -5.0F, 0.0F, -1.0F, 10.0F, var4, 16.0F, 1.0F, 0, 0, 0.0F, 64.0F, 32.0F, 0);
   }

   private static void l(float var0, float var1, float var2, float var3, float var4, float var5) {
      A.set(l).translate(var0, var1, var2).rotateZYX(var5, var4, var3);
   }

   private static int s(
      ByteBuffer var0,
      float var1,
      float var2,
      float var3,
      float var4,
      long var5,
      float var7,
      float var8,
      int var9,
      int var10,
      float var11,
      float var12,
      float var13,
      int var14
   ) {
      long var15 = 103886302265162L;
      float var17 = var1 - var11;
      float var18 = var2 - var11;
      float var19 = var3 - var11;
      float var20 = var1 + var4 + var11;
      float var21 = var2 + var7 + var11;
      float var22 = var3 + var8 + var11;
      float[] var23 = y;
      O(var23, 0, var17, var18, var19);
      O(var23, 1, var20, var18, var19);
      O(var23, 2, var20, var21, var19);
      O(var23, 3, var17, var21, var19);
      O(var23, 4, var17, var18, var22);
      O(var23, 5, var20, var18, var22);
      O(var23, 6, var20, var21, var22);
      O(var23, 7, var17, var21, var22);
      float var24 = var9;
      float var25 = var9 + var8;
      float var26 = var9 + var8 + var4;
      float var27 = var9 + var8 + var4 + var4;
      float var28 = var9 + var8 + var4 + var8;
      float var29 = var9 + var8 + var4 + var8 + var4;
      float var30 = var10;
      float var31 = var10 + var8;
      float var32 = var10 + var8 + var7;
      int var33 = 0;
      var33 += I(var0, 5, 4, 0, 1, var25, var30, var26, var31, 0.0F, var15, -1.0F, 0.0F, 64.0F, var13, var14);
      var33 += I(var0, 2, 3, 7, 6, var26, var31, var27, var30, 0.0F, var15, 1.0F, 0.0F, 64.0F, var13, var14);
      var33 += I(var0, 0, 4, 7, 3, var24, var31, var25, var32, -1.0F, var15, 0.0F, 0.0F, 64.0F, var13, var14);
      var33 += I(var0, 1, 0, 3, 2, var25, var31, var26, var32, 0.0F, var15, 0.0F, -1.0F, 64.0F, var13, var14);
      var33 += I(var0, 5, 1, 2, 6, var26, var31, var28, var32, 1.0F, var15, 0.0F, 0.0F, 64.0F, var13, var14);
      return var33 + I(var0, 4, 5, 6, 7, var28, var31, var29, var32, 0.0F, var15, 0.0F, 1.0F, 64.0F, var13, var14);
   }

   private static int I(
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
      long var10,
      float var12,
      float var13,
      float var14,
      float var15,
      int var16
   ) {
      long var17 = 25144029058480L;
      short var19 = 5854;
      short var20 = 19691;
      int var21 = (int)(var17 << 48 >>> 48);
      A.transformDirection(c.set(var9, var12, var13)).normalize();
      Y(var19, var0, var1, (short)var20, var7 / 64.0F, var6 / var15, var16, var21);
      Y(var19, var0, var2, (short)var20, var5 / 64.0F, var6 / var15, var16, var21);
      Y(var19, var0, var3, (short)var20, var5 / 64.0F, var8 / var15, var16, var21);
      Y(var19, var0, var1, (short)var20, var7 / 64.0F, var6 / var15, var16, var21);
      Y(var19, var0, var3, (short)var20, var5 / 64.0F, var8 / var15, var16, var21);
      Y(var19, var0, var4, (short)var20, var7 / 64.0F, var8 / var15, var16, var21);
      return 6;
   }

   private static void Y(int var0, ByteBuffer var1, int var2, short var3, float var4, float var5, int var6, int var7) {
      WingsUpdater2.ensureSourceClassInitialized();
      A.transformPosition(Z.set(y[var2 * 3], y[var2 * 3 + 1], y[var2 * 3 + 2]));
      var1.putFloat(Z.x).putFloat(Z.y).putFloat(Z.z);
      var1.putFloat(var4).putFloat(var5);
      ByteBuffer var8 = var1.put((byte)-1).put((byte)-1);
      var8.put((byte)-1).put((byte)(var6 == 0 ? 0 : 255));
      var1.put((byte)Math.round(c.x * 127.0F)).put((byte)Math.round(c.y * 127.0F)).put((byte)Math.round(c.z * 127.0F)).put((byte)0);
   }

   private static void O(float[] var0, int var1, float var2, float var3, float var4) {
      var0[var1 * 3] = var2;
      var0[var1 * 3 + 1] = var3;
      var0[var1 * 3 + 2] = var4;
   }

   private static void b(int var0, int var1, int var2, int var3, int var4) {
      WingsUpdater2.ensureSourceClassInitialized();
      GpuDevice var5 = RenderSystem.getDevice();
      if (k == null) {
         k = new class_6367("Purxiy profile avatar", var0, var1, true);
      } else if (k.field_1482 != var0 || k.field_1481 != var1) {
         k.method_1234(var0, var1);
      }

      if (C == null) {
         C = var5.createBuffer(ClientServices.label(() -> "Purxiy profile avatar camera"), 136, 160L);
         q = MemoryUtil.memAlloc(160);
      }

      if (v == null) {
         v = MemoryUtil.memAlloc(480468 * a);
         F = var5.createBuffer(ClientServices.label(() -> "Purxiy profile avatar mesh"), 40, 480468 * a);
      }
   }

   private static float A(float var0, long var1) {
      WingsUpdater2.ensureSourceClassInitialized();
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static float i(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   static void close() {
      int var0 = 0;

      try {
         if (U != null) {
            ClientCache.release(U);
         }
      } catch (Exception var65) {
         var0++;
      } finally {
         ;
      }

      try {
         if (F != null) {
            F.close();
         }
      } catch (Exception var63) {
         var0++;
      } finally {
         F = null;
      }

      try {
         if (C != null) {
            C.close();
         }
      } catch (Exception var61) {
         var0++;
      } finally {
         C = null;
      }

      try {
         if (v != null) {
            MemoryUtil.memFree(v);
         }
      } catch (Exception var59) {
         var0++;
      } finally {
         v = null;
      }

      try {
         if (q != null) {
            MemoryUtil.memFree(q);
         }
      } catch (Exception var57) {
         var0++;
      } finally {
         q = null;
      }

      if (k != null) {
         k.method_1238();
         k = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("Wardrobe avatar cleanup failed");
      }
   }

public static final class TypeU {
      public float a;
      public float R;
      public float d;
      public float L;
      public float S;
      public float H;
      public float N = 6.0F;
   }
}
