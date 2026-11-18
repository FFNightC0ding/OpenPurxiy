// Sourced By DevFFNightmare
package dev.purxiy.modules.halo;

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

public final class HaloRenderer4 {
   private static final int p = class_290.field_1577.getVertexSize();
   private static final int S = 160;
   private static final int E = 480468;
   public static final float D = 32.5F;
   private static final RenderPipeline Q = RenderPipeline.builder()
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
   
   private static class_6367 X;
   
   private static GpuBuffer G;
   
   private static GpuBuffer l;
   
   private static ByteBuffer A;
   
   private static ByteBuffer B;
   
   private static String a;
   private static final Matrix4f n = new Matrix4f();
   private static final Matrix4f g = new Matrix4f();
   private static final Matrix4f t = new Matrix4f();
   private static final Matrix4f M = new Matrix4f();
   private static final Matrix4f O = new Matrix4f();
   private static final Vector3f z = new Vector3f();
   private static final Vector3f f = new Vector3f();
   private static final float[] y = new float[24];




   private static final long h = 160L;

   private HaloRenderer4() {
   }

   public static boolean draw(
      Canvas var0,
      class_8685 var1,
      HaloRenderer4.TypeR var2,
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
      long var12 = 11344715832281L;
      long var14 = var12 ^ 90377531270417L;
      long var16 = var12 ^ 94282627022761L;
      int var18 = (int)((var12 ^ 94282627022761L) >>> 32);
      int var19 = (int)((var12 ^ 94282627022761L) << 32 >>> 56);
      int var20 = (int)(var16 << 40 >>> 40);
      long var21 = var12 ^ 42229108492183L;
      if (!(var5 <= 1.0F) && !(var6 <= 1.0F) && !(var7 <= 0.0F) && !(var0.alpha() <= 0.004F)) {
         GpuTextureView var23 = ClientBuilder.view(var1.comp_1626().comp_3627());
         if (var23 == null) {
            return false;
         } else {
            class_12081 var24 = var1.comp_1627();
            GpuTextureView var25 = var24 == null ? null : ClientBuilder.view(var24.comp_3627());

            try {
               GpuDevice var26 = RenderSystem.getDevice();
               if (!var26.precompilePipeline(Q).isValid()) {
                  return false;
               } else {
                  float var27 = (float)Math.hypot(var0.transform().m00(), var0.transform().m01());
                  float var28 = (float)Math.hypot(var0.transform().m10(), var0.transform().m11());
                  float var29 = Math.min(4096, var26.getMaxTextureSize());
                  float var30 = Math.min(Math.max(0.5F, 2.0F * var0.pixelScale() * Math.max(var27, var28)), Math.min(var29 / var5, var29 / var6));
                  int var31 = Math.max(1, Math.round(var5 * var30));
                  int var32 = Math.max(1, Math.round(var6 * var30));
                  L(var21, var31, var32);
                  ByteBuffer var33 = A;
                  var33.clear();
                  int var34 = (var1.comp_1629() == class_7920.field_41122) ? 1 : 0;
                  ClientBuilder.TypeE var35 = ClientBuilder.layers(var1);
                  int var39 = t(var18, (byte)var19, var33, var2, var20, (boolean)(var34 != 0), var35);
                  int var40 = var25 != null ? v(var33, var2, var14) : 0;
                  var33.flip();
                  CommandEncoder var41 = var26.createCommandEncoder();
                  var41.writeToBuffer(G.slice(0L, var33.remaining()), var33);
                  t(var5, var6, var7, var8);
                  float var42 = var9 >= 0.999F ? 1000.0F : -3.0F + var9 * 38.5F;
                  int var43 = Theme.tint(-4357889);
                  Std140Builder var44 = Std140Builder.intoBuffer(B.clear())
                     .putMat4f(t)
                     .putMat4f(g)
                     .putVec4(var42, 3.2F, var11, var10)
                     .putVec4(0.74F * BlendMode.red(var43) / 189.0F, 0.5F * BlendMode.green(var43) / 128.0F, 1.0F * BlendMode.blue(var43) / 255.0F, 0.5F);
                  var41.writeToBuffer(l.slice(), var44.get());
                  GpuBuffer var45 = Theme.uniforms();
                  class_12137 var46 = ClientBuilder.pixels();

                  try (RenderPass var47 = var41.createRenderPass(
                        () -> "Purxiy profile avatar", X.method_71639(), OptionalInt.of(0), X.method_71640(), OptionalDouble.of(1.0)
                     )) {
                     var47.setPipeline(Q);
                     var47.setUniform("ProfileAvatar", l);
                     var47.setUniform("PurxiyTheme", var45);
                     var47.setVertexBuffer(0, G);
                     var47.bindTexture("Sampler0", var23, var46);
                     int var48 = -1707204571;
                     var47.draw(0, var39);
                     var34 = (int)var12;
                     HaloRenderer7.metric("drawn");
                     if (var40 > 0) {
                        var47.bindTexture("Sampler0", var25, ClientBuilder.capeSampler(var1));
                        var48 = -1707204568;
                        var47.draw(var39, var40);
                        var34 = (int)var12;
                        HaloRenderer7.metric("drawn");
                     }
                  }

                  var0.image(
                     X.method_71639(),
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
                  a = null;
                  return true;
               }
            } catch (RuntimeException var52) {
               if (!var52.toString().equals(a)) {
                  ClientServices.LOGGER.error("Purxiy profile avatar could not render", var52);
               }

               a = var52.toString();
               return false;
            }
         }
      } else {
         return false;
      }
   }

   private static void t(float var0, float var1, float var2, float var3) {
      float var4 = (float)Math.toRadians(16.0);
      float var5 = var1 / var2;
      float var6 = var5 / (2.0F * (float)Math.tan(var4 / 2.0F));
      float var7 = (var1 / 2.0F - var3) / var2;
      float var8 = (float)Math.toRadians(7.0);
      n.setPerspective(var4, var0 / var1, Math.max(1.0F, var6 - 60.0F), var6 + 60.0F);
      g.setLookAt(0.0F, var7 + var6 * (float)Math.sin(var8), var6 * (float)Math.cos(var8), 0.0F, var7, 0.0F, 0.0F, 1.0F, 0.0F);
      n.mul(g, t);
   }

   private static int t(int var0, byte var1, ByteBuffer var2,  HaloRenderer4.TypeR var3, int var4, boolean var5, ClientBuilder.TypeE var6) {
      long var7 = 105478084109424L;
      long var9 = var7 ^ 64498898810172L;
      long var11 = var7 ^ 130884079207979L;
      long var13 = var7 ^ 71674671154712L;
      HaloDesign.ensureSourceClassInitialized();
      float var15 = ClientBuilder.yourHeight();
      M.identity().translate(0.0F, 24.0F * var15 + var3.x, 0.0F).rotateY(var3.F).scale(var15, -var15, -var15);
      float var16 = var3.P * 20.0F;
      float var17 = (float)Math.cos(var16 * 0.09F) * 0.05F + 0.05F;
      float var18 = (float)Math.sin(var16 * 0.067F) * 0.05F;
      float var19 = R(var3.K, var9);
      float var20 = 2.55F + 0.32F * (float)Math.sin(var3.P * 9.5F);
      int var21 = 0;
      boolean var22 = var6 == null || !var6.fullModel();
      float[][] var23 = var22 ? ClientBuilder.PIVOT : var6.pivots();
      boolean var24 = var6 != null && var6.rigidHead();
      P(var23[0][0], var23[0][1], var23[0][2], var24 ? 0.0F : var3.B, var24 ? 0.0F : var3.C, 0.0F);
      ClientBuilder.build(O, 0);
      int var25 = -121;
      if (var22) {
         var21 += N(var11, var2, -4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0, 0, 0.0F, 64.0F, 64.0F, 0);
      }

      int var26 = var21 + R(var13, var2, var6, 0, -4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 32, 0, 0.5F);
      P(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
      ClientBuilder.build(O, 1);
      var25 = (byte)-121;
      if (var22) {
         var26 += N(var11, var2, -4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 16, 16, 0.0F, 64.0F, 64.0F, 0);
      }

      int var27 = var26 + R(var13, var2, var6, 1, -4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 16, 32, 0.25F);
      float var28 = var5 ? 3.0F : 4.0F;
      P(var23[2][0] * ClientBuilder.armSpread(), var23[2][1], var23[2][2], R(var18, -0.25F, var19), 0.0F, R(var17, var20, var19));
      if (var22) {
         float var29 = var5 ? -2.0F : -3.0F;
         byte var30 = 0;
         float var31 = 64.0F;
         float var32 = 64.0F;
         float var33 = 0.0F;
         byte var34 = 16;
         byte var35 = 40;
         float var36 = 4.0F;
         float var37 = 12.0F;
         float var39 = -2.0F;
         float var40 = -2.0F;
         var27 += N(var11, var2, var29, var40, var39, var28, var37, var36, var35, var34, var33, var32, var31, var30);
      }

      float var49 = var5 ? -2.0F : -3.0F;
      float var50 = 0.25F;
      byte var53 = 32;
      byte var56 = 40;
      float var59 = 4.0F;
      float var62 = 12.0F;
      float var67 = -2.0F;
      float var70 = -2.0F;
      byte var74 = 2;
      int var42 = var27 + R(var13, var2, var6, var74, var49, var70, var67, var28, var62, var59, var56, var53, var50);
      int var43 = -121;
      P(var23[3][0] * ClientBuilder.armSpread(), var23[3][1], var23[3][2], -var18, 0.0F, -var17);
      var43 = 12330096;
      if (var22) {
         var42 += N(var11, var2, -1.0F, -2.0F, -2.0F, var28, 12.0F, 4.0F, 32, 48, 0.0F, 64.0F, 64.0F, 0);
      }

      var25 = var42 + R(var13, var2, var6, 3, -1.0F, -2.0F, -2.0F, var28, 12.0F, 4.0F, 48, 48, 0.25F);
      byte var44 = -121;
      int var45 = var25;
      P(var23[4][0] * ClientBuilder.bodyWidth(), var23[4][1], var23[4][2], 0.0F, 0.0F, 0.0F);
      ClientBuilder.build(O, 4);
      var44 = -121;
      if (var22) {
         var45 = var25 + N(var11, var2, -2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0, 16, 0.0F, 64.0F, 64.0F, 0);
      }

      var50 = 0.25F;
      var53 = 32;
      var56 = 0;
      var59 = 4.0F;
      var62 = 12.0F;
      float var65 = 4.0F;
      var67 = -2.0F;
      var70 = 0.0F;
      float var38 = -2.0F;
      var74 = 4;
      int var46 = var45 + R(var13, var2, var6, var74, var38, var70, var67, var65, var62, var59, var56, var53, var50);
      P(var23[5][0] * ClientBuilder.bodyWidth(), var23[5][1], var23[5][2], 0.0F, 0.0F, 0.0F);
      ClientBuilder.build(O, 5);
      var44 = -121;
      if (var22) {
         var46 += N(var11, var2, -2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 16, 48, 0.0F, 64.0F, 64.0F, 0);
      }

      var50 = 0.25F;
      var53 = 48;
      var56 = 0;
      var59 = 4.0F;
      var62 = 12.0F;
      var65 = 4.0F;
      var67 = -2.0F;
      var70 = 0.0F;
      var38 = -2.0F;
      var74 = 5;
      return var46 + R(var13, var2, var6, var74, var38, var70, var67, var65, var62, var59, var56, var53, var50);
   }

   private static int R(
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
      long var14 = 44974023402075L;
      return var3 == null
         ? N(var14, var2, var5, var6, var7, var8, var9, var10, var11, var12, var13, 64.0F, 64.0F, 1)
         : ClientBuilder.triangles(var2, var3, var4, O);
   }

   private static int v(ByteBuffer var0, HaloRenderer4.TypeR var1, long var2) {
      long var4 = 44974023402075L;
      O.set(M);
      ClientBuilder.build(O, 1);
      O.translate(0.0F, 0.0F, 2.0F).rotateX((float)Math.toRadians(var1.w)).rotateY((float) Math.PI);
      return N(var4, var0, -5.0F, 0.0F, -1.0F, 10.0F, 16.0F, 1.0F, 0, 0, 0.0F, 64.0F, 32.0F, 0);
   }

   private static void P(float var0, float var1, float var2, float var3, float var4, float var5) {
      O.set(M).translate(var0, var1, var2).rotateZYX(var5, var4, var3);
   }

   private static int N(
      long var0,
      ByteBuffer var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      int var9,
      int var10,
      float var11,
      float var12,
      float var13,
      int var14
   ) {
      long var15 = 88704447366547L;
      float var17 = var3 - var11;
      float var18 = var4 - var11;
      float var19 = var5 - var11;
      float var20 = var3 + var6 + var11;
      float var21 = var4 + var7 + var11;
      float var22 = var5 + var8 + var11;
      float[] var23 = y;
      t(var23, 0, var17, var18, var19);
      t(var23, 1, var20, var18, var19);
      t(var23, 2, var20, var21, var19);
      t(var23, 3, var17, var21, var19);
      t(var23, 4, var17, var18, var22);
      t(var23, 5, var20, var18, var22);
      t(var23, 6, var20, var21, var22);
      t(var23, 7, var17, var21, var22);
      float var24 = var9;
      float var25 = var9 + var8;
      float var26 = var9 + var8 + var6;
      float var27 = var9 + var8 + var6 + var6;
      float var28 = var9 + var8 + var6 + var8;
      float var29 = var9 + var8 + var6 + var8 + var6;
      float var30 = var10;
      float var31 = var10 + var8;
      float var32 = var10 + var8 + var7;
      int var33 = 0;
      var33 += q(var2, 5, 4, 0, 1, var15, var25, var30, var26, var31, 0.0F, -1.0F, 0.0F, 64.0F, var13, var14);
      var33 += q(var2, 2, 3, 7, 6, var15, var26, var31, var27, var30, 0.0F, 1.0F, 0.0F, 64.0F, var13, var14);
      var33 += q(var2, 0, 4, 7, 3, var15, var24, var31, var25, var32, -1.0F, 0.0F, 0.0F, 64.0F, var13, var14);
      var33 += q(var2, 1, 0, 3, 2, var15, var25, var31, var26, var32, 0.0F, 0.0F, -1.0F, 64.0F, var13, var14);
      var33 += q(var2, 5, 1, 2, 6, var15, var26, var31, var28, var32, 1.0F, 0.0F, 0.0F, 64.0F, var13, var14);
      return var33 + q(var2, 4, 5, 6, 7, var15, var28, var31, var29, var32, 0.0F, 0.0F, 1.0F, 64.0F, var13, var14);
   }

   private static int q(
      ByteBuffer var0,
      int var1,
      int var2,
      int var3,
      int var4,
      long var5,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12,
      float var13,
      float var14,
      float var15,
      int var16
   ) {
      long var17 = 110880777007357L;
      O.transformDirection(f.set(var11, var12, var13)).normalize();
      g(var0, var1, var9 / 64.0F, var17, var8 / var15, var16);
      g(var0, var2, var7 / 64.0F, var17, var8 / var15, var16);
      g(var0, var3, var7 / 64.0F, var17, var10 / var15, var16);
      g(var0, var1, var9 / 64.0F, var17, var8 / var15, var16);
      g(var0, var3, var7 / 64.0F, var17, var10 / var15, var16);
      g(var0, var4, var9 / 64.0F, var17, var10 / var15, var16);
      return 6;
   }

   private static void g(ByteBuffer var0, int var1, float var2, long var3, float var5, int var6) {
      O.transformPosition(z.set(y[var1 * 3], y[var1 * 3 + 1], y[var1 * 3 + 2]));
      HaloDesign.ensureSourceClassInitialized();
      var0.putFloat(z.x).putFloat(z.y).putFloat(z.z);
      var0.putFloat(var2).putFloat(var5);
      long var7 = 110880777007357L;
      if (110880777007357L == 0L) {
         boolean var10000 = false;
      } else {
         int var13 = var7 < 0L ? -1 : 1;
      }

      ByteBuffer var10 = var0.put((byte)-1).put((byte)-1);
      long var11 = 110880777007356L;
      if (110880777007356L == 0L) {
         boolean var14 = false;
      } else {
         int var15 = var11 < 0L ? -1 : 1;
      }

      var10.put((byte)-1).put((byte)(var6 == 0 ? 0 : 255));
      var0.put((byte)Math.round(f.x * 127.0F)).put((byte)Math.round(f.y * 127.0F)).put((byte)Math.round(f.z * 127.0F)).put((byte)0);
   }

   private static void t(float[] var0, int var1, float var2, float var3, float var4) {
      var0[var1 * 3] = var2;
      var0[var1 * 3 + 1] = var3;
      var0[var1 * 3 + 2] = var4;
   }

   private static void L(long var0, int var2, int var3) {
      HaloDesign.ensureSourceClassInitialized();
      GpuDevice var4 = RenderSystem.getDevice();
      if (X == null) {
         X = new class_6367("Purxiy profile avatar", var2, var3, true);
      } else {
         label30: {
            if (X.field_1482 == var2) {
               long var5 = 48624914603085L;
               if (48624914603085L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var8 = var5 < 0L ? -1 : 1;
               }

               if (X.field_1481 == var3) {
                  break label30;
               }
            }

            X.method_1234(var2, var3);
         }
      }

      if (l == null) {
         l = var4.createBuffer(ClientServices.label(() -> "Purxiy profile avatar camera"), 136, 160L);
         B = MemoryUtil.memAlloc(160);
      }

      if (A == null) {
         A = MemoryUtil.memAlloc(480468 * p);
         G = var4.createBuffer(ClientServices.label(() -> "Purxiy profile avatar mesh"), 40, 480468 * p);
      }
   }

   private static float R(float var0, long var1) {
      HaloDesign.ensureSourceClassInitialized();
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static float R(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   static void close() {
      int var0 = 0;

      try {
         if (Q != null) {
            ClientCache.release(Q);
         }
      } catch (Exception var65) {
         var0++;
      } finally {
         ;
      }

      try {
         if (G != null) {
            G.close();
         }
      } catch (Exception var63) {
         var0++;
      } finally {
         G = null;
      }

      try {
         if (l != null) {
            l.close();
         }
      } catch (Exception var61) {
         var0++;
      } finally {
         l = null;
      }

      try {
         if (A != null) {
            MemoryUtil.memFree(A);
         }
      } catch (Exception var59) {
         var0++;
      } finally {
         A = null;
      }

      try {
         if (B != null) {
            MemoryUtil.memFree(B);
         }
      } catch (Exception var57) {
         var0++;
      } finally {
         B = null;
      }

      if (X != null) {
         X.method_1238();
         X = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("Wardrobe avatar cleanup failed");
      }
   }

public static final class TypeR {
      public float F;
      public float C;
      public float B;
      public float P;
      public float K;
      public float x;
      public float w = 6.0F;
   }
}
