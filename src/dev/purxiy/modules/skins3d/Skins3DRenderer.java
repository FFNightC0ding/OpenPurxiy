package dev.purxiy.modules.skins3d;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Snippet;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.systems.RenderSystem.class_5590;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.loader.fabric.client.ArmorRenderProvider;
import dev.purxiy.render.Canvas;
import dev.purxiy.ui.Theme;
import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.Map.Entry;
import net.minecraft.class_1044;
import net.minecraft.class_10789;
import net.minecraft.class_12137;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_6367;
import org.joml.Matrix4f;
import org.lwjgl.system.MemoryUtil;

final class Skins3DRenderer {
   static final float m = 33.0F;
   private static final int i = 7;
   private static final int u = 6;
   private static final int v = 640;
   private static final RenderPipeline k = RenderPipeline.builder(new Snippet[0])
      .withLocation(ClientServices.id("pipeline/skins3d_stage"))
      .withVertexShader(ClientServices.id("core/skins3d_stage"))
      .withFragmentShader(ClientServices.id("core/skins3d_stage"))
      .withUniform("SkinStage", class_10789.field_60031)
      .withSampler("Sampler0")
      .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
      .withDepthWrite(true)
      .withCull(false)
      .withoutBlend()
      .withVertexFormat(class_290.field_1577, class_5596.field_27382)
      .build();
   private static final int K = class_290.field_1577.getVertexSize();
   private static final Map<String, Skins3DRenderer.SkinBatch> g = new LinkedHashMap<String, Skins3DRenderer.SkinBatch>(16, 0.75F, true) {
      @Override
      protected boolean removeEldestEntry(Entry<String, Skins3DRenderer.SkinBatch> var1) {
         if (this.size() > 12) {
            var1.getValue().close();
            return true;
         } else {
            return false;
         }
      }
   };
   private static Skins3DRenderer.SkinBatch a;
   private static final Matrix4f O = new Matrix4f();
   private static final Matrix4f e = new Matrix4f();
   private static final Matrix4f n = new Matrix4f();
   private static final Matrix4f T = new Matrix4f();
   private static final Matrix4f[] Q = new Matrix4f[7];
   private final String H;
   private class_6367 S;
   private GpuBuffer Y;
   private ByteBuffer l;
   private String d;
   private static final float[] p;
   private static final Integer[] h;
   private static final long j = 640L;

   Skins3DRenderer(String var1) {
      this.H = var1;
   }

   boolean draw(
      Canvas var1,
      class_2960 var2,
      boolean var3,
      class_2960 var4,
      boolean var5,
      Skins3DRenderer.SkinFrame var6,
      Skins3DRenderer.SkinRenderState var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12,
      float var13,
      float var14
   ) {
      long var15 = 48064058510289L;
      long var17 = var15 ^ 37247159106536L;
      long var19 = var15 ^ 136310783310886L;
      long var21 = var15 ^ 13277074842410L;
      int var23 = (int)((var15 ^ 13277074842410L) >>> 32);
      int var24 = (int)((var15 ^ 13277074842410L) << 32 >>> 48);
      int var25 = (int)(var21 << 48 >>> 48);
      var21 = var15 ^ 19614490494905L;
      int var26 = (int)((var15 ^ 19614490494905L) >>> 48);
      int var27 = (int)((var15 ^ 19614490494905L) << 16 >>> 48);
      int var28 = (int)(var21 << 32 >>> 32);
      if (!(var11 <= 1.0F) && !(var12 <= 1.0F) && !(var13 <= 0.0F) && !(var1.alpha() <= 0.004F)) {
         GpuTextureView var29 = view(var2);
         Skins3DBuilder.SkinQuad var30 = var29 == null ? null : Skins3DFactory.get(var2, var3);
         if (var30 == null) {
            return false;
         } else {
            GpuTextureView var31 = view(var4);

            try {
               GpuDevice var32 = RenderSystem.getDevice();
               if (!var32.precompilePipeline(k).isValid()) {
                  return false;
               } else {
                  Skins3DRenderer.SkinBatch var33 = r(var23, var2, var3, (short)var24, var25, var30);
                  float var34 = (float)Math.hypot(var1.transform().m00(), var1.transform().m01());
                  float var35 = (float)Math.hypot(var1.transform().m10(), var1.transform().m11());
                  float var36 = Math.min(4096, var32.getMaxTextureSize());
                  float var37 = Math.min(Math.max(0.5F, 2.0F * var1.pixelScale() * Math.max(var34, var35)), Math.min(var36 / var11, var36 / var12));
                  int var38 = Math.max(1, Math.round(var11 * var37));
                  int var39 = Math.max(1, Math.round(var12 * var37));
                  this.M(var19, var32, var38, var39);
                  A(var6, var17, var3, var30);
                  P(var11, var12, var13, var14, var7);
                  CommandEncoder var40 = var32.createCommandEncoder();
                  Std140Builder var41 = Std140Builder.intoBuffer(this.l.clear()).putMat4f(n).putMat4f(e);

                  for (int var42 = 0; var42 < 7; var42++) {
                     var41.putMat4f(Q[var42]);
                  }

                  float var54 = Skins3DBuilder.I[0] * var8;
                  float var43 = Skins3DBuilder.I[1] * var8;
                  var41.putVec4(var54, var43, var43, var43).putVec4(var43, var43, 0.0F, 0.0F);
                  var41.putVec4((var7.m >> 16 & 0xFF) / 255.0F, (var7.m >> 8 & 0xFF) / 255.0F, (var7.m & 0xFF) / 255.0F, var7.S);
                  var41.putVec4(var6.N, var7.N, 0.0F, 0.0F);
                  var40.writeToBuffer(this.Y.slice(), var41.get());
                  class_5590 var44 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
                  class_12137 var45 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
                  RenderPass var46 = var40.createRenderPass(
                     () -> "SourceByDevFFnightmare DeObfDeCompByGuideX 3D skin " + this.H, this.S.method_71639(), OptionalInt.of(0), this.S.method_71640(), OptionalDouble.of(1.0)
                  );

                  try {
                     var46.setPipeline(k);
                     var46.setUniform("SkinStage", this.Y);
                     var46.bindTexture("Sampler0", var29, var45);
                     int var47 = -920491118;
                     var46.setVertexBuffer(0, var33.v);
                     int var48 = (int)var15;
                     var46.setIndexBuffer(var44.method_68274(var33.e * 6), var44.method_31924());
                     var46.drawIndexed(0, 0, var33.e * 6, 1);
                     Skins3DRig.metric("drawn");
                     if (var31 != null) {
                        Skins3DRenderer.SkinBatch var49 = r((short)var26, (char)var27, var28);
                        var46.bindTexture("Sampler0", var31, var5 ? ArmorRenderProvider.sampler() : var45);
                        var47 = -920491109;
                        var46.setVertexBuffer(0, var49.v);
                        var48 = (int)var15;
                        var46.setIndexBuffer(var44.method_68274(var49.e * 6), var44.method_31924());
                        var46.drawIndexed(0, 0, var49.e * 6, 1);
                        Skins3DRig.metric("drawn");
                     }
                  } catch (Throwable var51) {
                     if (var46 != null) {
                        try {
                           var46.close();
                        } catch (Throwable var50) {
                           var51.addSuppressed(var50);
                        }
                     }

                     throw var51;
                  }

                  if (var46 != null) {
                     var46.close();
                  }

                  var1.image(
                     this.S.method_71639(),
                     RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR),
                     true,
                     var9,
                     var10,
                     var11,
                     var12,
                     0.0F,
                     1.0F,
                     1.0F,
                     0.0F,
                     0.0F,
                     -1
                  );
                  this.d = null;
                  return true;
               }
            } catch (RuntimeException var521) {
               if (!var521.toString().equals(this.d)) {
                  ClientServices.LOGGER.error("SourceByDevFFnightmare DeObfDeCompByGuideX 3D skin preview could not render", var521);
               }

               this.d = var521.toString();
               return false;
            }
         }
      } else {
         return false;
      }
   }

   private void M(long var1, GpuDevice var3, int var4, int var5) {
      SkinPreset.ensureSourceClassInitialized();
      if (this.S == null) {
         this.S = new class_6367("SourceByDevFFnightmare DeObfDeCompByGuideX 3D skin " + this.H, var4, var5, true);
      } else {
         label28: {
            if (this.S.field_1482 == var4) {
               long var6 = 88302832269303L;
               int var8 = var6 < 0L ? -1 : 1;
               if (this.S.field_1481 == var5) {
                  break label28;
               }
            }

            this.S.method_1234(var4, var5);
         }
      }

      long var9 = 88302832269303L;
      int var10 = var9 < 0L ? -1 : 1;
      if (this.Y == null) {
         this.Y = var3.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX 3D skin " + this.H + " pose"), 136, 640L);
         this.l = MemoryUtil.memAlloc(640);
      }
   }

   private static void A(Skins3DRenderer.SkinFrame var0, long var1, boolean var3, Skins3DBuilder.SkinQuad var4) {
      long var5 = 83432673278023L;
      boolean var7 = var4.C;
      SkinPreset.ensureSourceClassInitialized();
      T.identity().translate(0.0F, 24.0F + var0.n, 0.0F).rotateY(var0.T).scale(1.0F, -1.0F, -1.0F);
      float var8 = var0.N * 20.0F;
      float var9 = (float)Math.cos(var8 * 0.09F) * 0.05F + 0.05F;
      float var10 = (float)Math.sin(var8 * 0.067F) * 0.05F;
      float var11 = i(var0.i, var5);
      float var12 = 2.55F + 0.32F * (float)Math.sin(var0.N * 9.5F);
      float var13 = (4.0F * var0.q + 1.0F) / 5.0F;
      float[] var14 = var4.y[0];
      G(0, var14[0], var14[1], var14[2], var4.n ? 0.0F : var0.v, var4.n ? 0.0F : var0.V, 0.0F);
      Matrix4f var15 = Q[0];
      long var16 = 11366875115576L;
      int var19 = var16 < 0L ? -1 : 1;
      var15.scale(var0.Y, var0.C, var0.J);
      G(1, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
      Q[1].scale(var0.q, 1.0F, var0.E);
      G(2, -5.0F * var13, 2.0F, 0.0F, p(var10, -0.25F, var11), 0.0F, p(var9, var12, var11));
      G(3, 5.0F * var13, 2.0F, 0.0F, -var10, 0.0F, -var9);
      G(4, -1.9F * var0.q, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F);
      Q[4].scale(var0.w, 1.0F, var0.j);
      G(5, 1.9F * var0.q, 12.0F, 0.0F, 0.0F, 0.0F, 0.0F);
      var15 = Q[5];
      long var19x = 11366875115576L;
      int var21 = var19x < 0L ? -1 : 1;
      var15.scale(var0.w, 1.0F, var0.j);
      if (var7) {
         float[][] var21x = var4.y;
         G(2, var21x[2][0], var21x[2][1], var21x[2][2], p(var10, -0.25F, var11), 0.0F, p(var9, var12, var11));
         G(3, var21x[3][0], var21x[3][1], var21x[3][2], -var10, 0.0F, -var9);
         G(4, var21x[4][0], var21x[4][1], var21x[4][2], 0.0F, 0.0F, 0.0F);
         Q[4].scale(var0.w, 1.0F, var0.j);
         G(5, var21x[5][0], var21x[5][1], var21x[5][2], 0.0F, 0.0F, 0.0F);
         Q[5].scale(var0.w, 1.0F, var0.j);
      }

      Q[6].set(T).scale(var0.q, 1.0F, var0.E).translate(0.0F, 0.0F, 2.0F).rotateX((float)Math.toRadians(var0.p)).rotateY((float) Math.PI);
   }

   private static void G(int var0, float var1, float var2, float var3, float var4, float var5, float var6) {
      Q[var0].set(T).translate(var1, var2, var3).rotateZYX(var6, var5, var4);
   }

   private static void P(float var0, float var1, float var2, float var3, Skins3DRenderer.SkinRenderState var4) {
      float var5 = (float)Math.toRadians(var4.i);
      float var6 = var1 / var2;
      float var7 = var6 / (2.0F * (float)Math.tan(var5 / 2.0F));
      float var8 = (var1 / 2.0F - var3) / var2;
      float var9 = (float)Math.toRadians(var4.J);
      O.setPerspective(var5, var0 / var1, Math.max(1.0F, var7 - 60.0F), var7 + 60.0F);
      e.setLookAt(0.0F, var8 + var7 * (float)Math.sin(var9), var7 * (float)Math.cos(var9), 0.0F, var8, 0.0F, 0.0F, 1.0F, 0.0F);
      O.mul(e, n);
   }

   private static Skins3DRenderer.SkinBatch r(int var0, class_2960 var1, boolean var2, short var3, int var4, Skins3DBuilder.SkinQuad var5) {
      long var6 = 43591878977787L;
      long var8 = var6 ^ 39718592336016L;
      SkinPreset.ensureSourceClassInitialized();
      String var10 = var1 + (var2 ? "#slim" : "#wide") + "@" + System.identityHashCode(var5);
      Skins3DRenderer.SkinBatch var11 = g.get(var10);
      if (var11 == null) {
         var11 = V(var5, var8, var2, var10);
         g.put(var10, var11);
      }

      return var11;
   }

   private static Skins3DRenderer.SkinBatch V(Skins3DBuilder.SkinQuad var0, long var1, boolean var3, String var4) {
      long var5 = 51804383182804L;
      long var7 = 52140500845484L;
      SkinPreset.ensureSourceClassInitialized();
      int var9 = (var0.C ? 0 : 36) + var0.quads();
      ByteBuffer var10 = MemoryUtil.memAlloc(var9 * 4 * K);

      Skins3DRenderer.SkinBatch var51;
      try {
         for (int var11 = 0; var11 < 6; var11++) {
            long var12 = 4098252372074L;
            int var15 = var12 < 0L ? -1 : 1;
            if (var0.C) {
               break;
            }

            int[] var15x = Skins3DBuilder.box(var11, var3);
            int[] var16 = Skins3DBuilder.p[var11];
            W(var10, var11, var15x[0], var15x[1], var15x[2], var15x[3], var15x[4], var15x[5], var16[0], var7, var16[1], 64.0F, 64.0F);
         }

         long var46 = 4098252372075L;
         int var47 = var46 < 0L ? -1 : 1;

         for (Skins3DBuilder.SkinVertex var17 : var0.R) {
            long var18 = 4098252372074L;
            int var52 = var18 < 0L ? -1 : 1;

            for (int var21 = 0; var21 < var17.p; var21++) {
               int[] var22 = Skins3DBuilder.C[var17.normal(var21)];
               long var23 = 4098252372074L;
               int var25 = var23 < 0L ? -1 : 1;
               float var25x = var17.texelU(var21);
               float var26 = var17.texelV(var21);

               for (int var27 = 0; var27 < 4; var27++) {
                  long var28 = 4098252372074L;
                  int var30 = var28 < 0L ? -1 : 1;
                  var30 = var17.uvCorner(var21, var27);
                  int var31 = 0;

                  for (int var32 = 2; var32 >= 0; var32--) {
                     float var33 = var17.coordinate(var21, var27, var32, 1.0F);
                     long var34 = 4098252372074L;
                     int var36 = var34 < 0L ? -1 : 1;
                     float var36x = var17.coordinate(var21, var27, var32, 0.0F);
                     long var37 = 4098252372075L;
                     int var13 = var37 < 0L ? -1 : 1;
                     var13 = var31 * 3;
                     float var40;
                     int var20x = (var40 = var33 - var36x) == 0.0F ? 0 : (var40 < 0.0F ? -1 : 1);
                     long var41 = 4098252372075L;
                     int var43 = var41 < 0L ? -1 : 1;
                     var31 = var13 + (var20x < 0 ? 0 : (var33 > var36x ? 2 : 1));
                  }

                  long var54 = 4098252372075L;
                  int var62 = var54 < 0L ? -1 : 1;
                  J(
                     var10,
                     var17.coordinate(var21, var27, 0, 0.0F),
                     var17.coordinate(var21, var27, 1, 0.0F),
                     var17.coordinate(var21, var27, 2, 0.0F),
                     (var25x + ((var30 & 1) != 0 ? 0.98F : 0.02F)) / 64.0F,
                     (var26 + ((var30 & 2) != 0 ? 0.98F : 0.02F)) / 64.0F,
                     Math.round(Skins3DBuilder.H[var17.shade(var21, var27)] * 255.0F),
                     var17.d | 8,
                     var31,
                     var22[0],
                     var5,
                     var22[1],
                     var22[2]
                  );
               }

               long var53 = 4098252372074L;
               int var281 = var53 < 0L ? -1 : 1;
            }

            long var52x = 4098252372075L;
            int var57 = var52x < 0L ? -1 : 1;
         }

         long var47x = 4098252372075L;
         int var531 = var47x < 0L ? -1 : 1;
         var10.flip();
         GpuBuffer var50 = RenderSystem.getDevice().createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX 3D skin " + var4), 32, var10);
         var51 = new Skins3DRenderer.SkinBatch(var50, var9);
      } finally {
         MemoryUtil.memFree(var10);
      }

      return var51;
   }

   private static Skins3DRenderer.SkinBatch r(short var0, char var1, int var2) {
      long var3 = 64184320478312L;
      long var5 = var3 ^ 23141209821124L;
      SkinPreset.ensureSourceClassInitialized();
      if (a == null) {
         ByteBuffer var7 = MemoryUtil.memAlloc(24 * K);

         try {
            W(var7, 6, -5.0F, 0.0F, -1.0F, 10.0F, 16.0F, 1.0F, 0, var5, 0, 64.0F, 32.0F);
            var7.flip();
            a = new Skins3DRenderer.SkinBatch(RenderSystem.getDevice().createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX 3D skin cape"), 32, var7), 6);
         } finally {
            MemoryUtil.memFree(var7);
         }
      }

      return a;
   }

   private static void W(
      ByteBuffer var0,
      int var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      int var8,
      long var9,
      int var11,
      float var12,
      float var13
   ) {
      long var14 = 79116770132280L;
      byte var16 = 0;
      int var17 = 1207226106;
      int var18 = (int)(var14 << 48 >>> 48);
      float var19 = var2 + var5;
      float var20 = var3 + var6;
      float var21 = var4 + var7;
      float[] var22 = p;
      x(var22, 0, var2, var3, var4);
      x(var22, 1, var19, var3, var4);
      x(var22, 2, var19, var20, var4);
      x(var22, 3, var2, var20, var4);
      x(var22, 4, var2, var3, var21);
      x(var22, 5, var19, var3, var21);
      x(var22, 6, var19, var20, var21);
      x(var22, 7, var2, var20, var21);
      float var23 = var8;
      float var24 = var8 + var7;
      float var25 = var8 + var7 + var5;
      float var26 = var8 + var7 + var5 + var5;
      float var27 = var8 + var7 + var5 + var7;
      float var28 = var8 + var7 + var5 + var7 + var5;
      float var29 = var11;
      float var30 = var11 + var7;
      float var31 = var11 + var7 + var6;
      h(var0, var1, 5, 4, 0, (char)var16, 1, var17, var18, var24, var29, var25, var30, 0, -1, 0, 64.0F, var13);
      h(var0, var1, 2, 3, 7, (char)var16, 6, var17, var18, var25, var30, var26, var29, 0, 1, 0, 64.0F, var13);
      h(var0, var1, 0, 4, 7, (char)var16, 3, var17, var18, var23, var30, var24, var31, -1, 0, 0, 64.0F, var13);
      h(var0, var1, 1, 0, 3, (char)var16, 2, var17, var18, var24, var30, var25, var31, 0, 0, -1, 64.0F, var13);
      h(var0, var1, 5, 1, 2, (char)var16, 6, var17, var18, var25, var30, var27, var31, 1, 0, 0, 64.0F, var13);
      h(var0, var1, 4, 5, 6, (char)var16, 7, var17, var18, var27, var30, var28, var31, 0, 0, 1, 64.0F, var13);
   }

   private static void h(
      ByteBuffer var0,
      int var1,
      int var2,
      int var3,
      int var4,
      char var5,
      int var6,
      int var7,
      int var8,
      float var9,
      float var10,
      float var11,
      float var12,
      int var13,
      int var14,
      int var15,
      float var16,
      float var17
   ) {
      long var18 = 79116770132280L;
      long var20 = var18 ^ 53554671905851L;
      B(var0, var1, var2, var11 / 64.0F, var10 / var17, var13, var14, var20, var15);
      B(var0, var1, var3, var9 / 64.0F, var10 / var17, var13, var14, var20, var15);
      B(var0, var1, var4, var9 / 64.0F, var12 / var17, var13, var14, var20, var15);
      B(var0, var1, var6, var11 / 64.0F, var12 / var17, var13, var14, var20, var15);
   }

   private static void B(ByteBuffer var0, int var1, int var2, float var3, float var4, int var5, int var6, long var7, int var9) {
      long var10 = 51804383182804L;
      J(var0, p[var2 * 3], p[var2 * 3 + 1], p[var2 * 3 + 2], var3, var4, 255, var1, 13, var5, var10, var6, var9);
   }

   private static void J(
      ByteBuffer var0, float var1, float var2, float var3, float var4, float var5, int var6, int var7, int var8, int var9, long var10, int var12, int var13
   ) {
      var0.putFloat(var1).putFloat(var2).putFloat(var3);
      var0.putFloat(var4).putFloat(var5);
      var0.put((byte)var6).put((byte)var7).put((byte)var8).put((byte)-1);
      var0.put((byte)(var9 * 127)).put((byte)(var12 * 127)).put((byte)(var13 * 127)).put((byte)0);
   }

   private static void x(float[] var0, int var1, float var2, float var3, float var4) {
      var0[var1 * 3] = var2;
      var0[var1 * 3 + 1] = var3;
      var0[var1 * 3 + 2] = var4;
   }

   static GpuTextureView view(class_2960 var0) {
      if (var0 == null) {
         return null;
      } else {
         try {
            class_1044 var1 = class_310.method_1551().method_1531().method_4619(var0);
            return var1.method_71659();
         } catch (IllegalStateException var21) {
            return null;
         }
      }
   }

   private static float i(float var0, long var1) {
      SkinPreset.ensureSourceClassInitialized();
      var0 = var0 < 0.0F ? 0.0F : Math.min(var0, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static float p(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   void release() {
      if (this.S != null) {
         this.S.method_1238();
         this.S = null;
      }

      if (this.Y != null) {
         this.Y.close();
         this.Y = null;
      }

      if (this.l != null) {
         MemoryUtil.memFree(this.l);
         this.l = null;
      }
   }

   static void releaseMeshes() {
      for (Skins3DRenderer.SkinBatch var1 : g.values()) {
         var1.close();
      }

      g.clear();
      if (a != null) {
         a.close();
         a = null;
      }

      ClientCache.release(k);
   }

   static {
      for (int var0 = 0; var0 < Q.length; var0++) {
         Q[var0] = new Matrix4f();
      }

      p = new float[24];
      h = new Integer[18];
   }

   private static final class SkinBatch {
      final GpuBuffer v;
      final int e;

      SkinBatch(GpuBuffer var1, int var2) {
         this.v = var1;
         this.e = var2;
      }

      void close() {
         this.v.close();
      }
   }

   static final class SkinRenderState {
      int m = Theme.tint(-4222985);
      float S = 0.5F;
      float N;
      float i = 16.0F;
      float J = 7.0F;
   }

   static final class SkinFrame {
      float T;
      float V;
      float v;
      float N;
      float i;
      float n;
      float p = 8.0F;
      float Y = 1.0F;
      float C = 1.0F;
      float J = 1.0F;
      float q = 1.0F;
      float E = 1.0F;
      float w = 1.0F;
      float j = 1.0F;

      void build(SkinPreset.Data var1) {
         this.Y = var1.headWidth();
         this.C = var1.headHeight();
         this.J = var1.headDepth();
         this.q = var1.bodyWidth();
         this.E = var1.bodyDepth();
         this.w = var1.legWidth();
         this.j = var1.legDepth();
      }
   }
}
