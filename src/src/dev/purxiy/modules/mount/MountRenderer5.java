// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.pipeline.BlendFunction;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Builder;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.assets.IrisCompat;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.ui.Theme;
import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import net.fabricmc.fabric.api.client.rendering.v1.world.WorldRenderContext;
import net.minecraft.class_10055;
import net.minecraft.class_12137;
import net.minecraft.class_12206;
import net.minecraft.class_1944;
import net.minecraft.class_2338;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_6367;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector4f;

public final class MountRenderer5 {
   private static final float j = 0.08F;
   private static final float m = 0.32F;
   private static final float J = 0.3F;
   private static final float v = 0.6F;
   private static final RenderPipeline s = n("garage_composite", false, 36649270738400L);
   private static final RenderPipeline B = n("garage_composite_smooth", true, 36649270738400L);
   private static int c = 0;
   private static boolean l;
   private static boolean I;
   private static final MountGeometry D = new MountGeometry();
   private static final MountGeometry H = new MountGeometry();
   private static final MountRenderer7.TypeV P = new MountRenderer7.TypeV();
   private static final MountUpdater7 d = new MountUpdater7();
   private static final Vector3f r = new Vector3f();
   private static class_6367 T;
   private static boolean S;
   private static String W;
   private static int z = 0;
   private static int p = 100;
   private static final Matrix4f C = new Matrix4f();
   private static final Matrix4f N = new Matrix4f();

   private static final String[] b = new String[21];
   private static final long[] e = new long[]{
      -6801703868826468399L, 1488474304138517197L, -4902324572281063203L, -9203443368734053175L, -421009698445248660L, -638928763811010048L
   };


   private static RenderPipeline n(String var0, boolean var1, long var2) {
      Builder var4 = RenderPipeline.builder()
         .withLocation(ClientServices.id("pipeline/" + var0))
         .withVertexShader(ClientServices.id("core/garage_composite"))
         .withFragmentShader(ClientServices.id("core/garage_composite"))
         .withSampler("CarColor")
         .withSampler("CarDepth")
         .withDepthTestFunction(DepthTestFunction.LEQUAL_DEPTH_TEST)
         .withDepthWrite(true)
         .withCull(false)
         .withBlend(BlendFunction.TRANSLUCENT_PREMULTIPLIED_ALPHA)
         .withVertexFormat(class_290.field_60033, class_5596.field_27379);
      if (var1) {
         var4.withShaderDefine("SMOOTH");
      }

      return var4.build();
   }

   private MountRenderer5() {
   }

   public static void beginWorld() {
      S = true;
   }

   public static void endWorld() {
      S = false;
   }

   public static boolean isRider(class_10055 var0) {
      class_310 var1 = class_310.method_1551();
      return S
         && MountUpdater4.visible()
         && (MountUpdater4.mounted() || MountUpdater4.boarding(partialTick()) > 0.0F)
         && var1.field_1724 != null
         && var0.field_53528 == var1.field_1724.method_5628();
   }

   public static float partialTick() {
      return class_310.method_1551().method_61966().method_60637(false);
   }

   private static MountGeometry B(float var0) {
      MountUpdater4.pose(H, var0);
      H.compute(new Matrix4f(), MountUpdater4.spec());
      return H;
   }

   public static class_243 worldPoint(float var0, float var1, float var2, float var3) {
      B(var3).bodyPoint(var0, var1, var2, r);
      return MountUpdater4.renderPosition(var3).method_1031(r.x, r.y, r.z);
   }

   public static class_243 riderOffset(float var0) {
      class_310 var1 = class_310.method_1551();
      float var2 = var1.field_1724 == null ? 1.0F : var1.field_1724.method_55693();
      Vector3f var3 = MountUpdater4.spec().seat();
      class_243 var4 = worldPoint(var3.x, var3.y, var3.z, var0).method_1031(0.0, -0.70406246F * var2, 0.0);
      if (var1.field_1724 != null) {
         var4 = var4.method_1020(var1.field_1724.method_30950(var0));
      }

      return var4.method_1021(MountUpdater4.boarding(var0));
   }

   public static class_243 cameraOffset(float var0) {
      return riderOffset(var0);
   }

   public static boolean cockpitActive() {
      class_310 var0 = class_310.method_1551();
      return MountUpdater4.mounted() && var0.field_1690.method_31044().method_31034() && var0.method_1560() == var0.field_1724;
   }

   public static class_243 cockpitPosition(float var0) {
      if (MountUpdater4.entering()) {
         Vector3f var1 = MountUpdater4.entryEye(var0, new Vector3f());
         return worldPoint(var1.x, var1.y, var1.z, var0);
      } else {
         return seatEye(var0);
      }
   }

   public static class_243 seatEye(float var0) {
      Vector3f var1 = MountUpdater4.spec().eye();
      return worldPoint(var1.x, var1.y, var1.z, var0);
   }

   public static boolean inClosedCabin(class_10055 var0) {
      return isRider(var0) && MountUpdater4.spec().kind() == MountFactory.TypeK.CAR && MountUpdater4.boarding(partialTick()) > 0.5F;
   }

   static void warmUp() {
      if (--p <= 0) {
         p = 12;
         ArrayList var0 = new ArrayList<>(MountRenderer7.pipelines());
         var0.add(s);
         var0.add(B);
         if (z < var0.size()) {
            RenderSystem.getDevice().precompilePipeline((RenderPipeline)var0.get(z++));
         }
      }
   }

   public static void devCamera(String var0) {
      if (var0.equals("off")) {
         MountUpdater4.devCamera(null);
      } else {
         String[] var1 = var0.split(";");
         float[] var2 = new float[6];

         for (int var3 = 0; var3 < 6; var3++) {
            var2[var3] = Float.parseFloat(var1[var3]);
         }

         MountUpdater4.devCamera(var2);
      }
   }

   public static void devPerf(String var0) {
      String[] var1 = var0.split(";");
      String var2 = var1[0];
      switch (var2) {
         case "scale":
            c = Integer.parseInt(var1[1]);
            break;
         case "depthfirst":
            l = !Boolean.parseBoolean(var1[1]);
            break;
         case "screen":
            I = !Boolean.parseBoolean(var1[1]);
            break;
         case "mirrors":
            MountRenderer13.devEnabled(Boolean.parseBoolean(var1[1]));
            break;
         default:
            ClientServices.LOGGER.warn("Unknown mount perf setting {}", var0);
      }
   }

   public static float riderPitch(float var0) {
      MountGeometry var1 = B(var0);
      return var1.s - var1.W + var1.r;
   }

   public static float riderBank(float var0) {
      MountGeometry var1 = B(var0);
      return var1.u + var1.B - var1.E;
   }

   public static float riderForward() {
      return MountUpdater4.spec().kind() == MountFactory.TypeK.MOTORBIKE ? 0.42F : 0.0F;
   }

   static Matrix4fc modelView() {
      return IrisCompat.inUse() ? C : RenderSystem.getModelViewMatrix();
   }

   public static void frameMatrices(Matrix4f var0, Matrix4f var1) {
      C.set(var0);
      N.set(var1);
      MountRenderer12.frame(var0, var1);
   }

   public static void renderSheets() {
      long var0 = 113765242791823L;
      long var2 = var0 ^ 110678609101982L;
      if (IrisCompat.inUse() && !MountRenderer13.capturing()) {
         e(class_310.method_1551().field_1773.method_19418().method_71156(), var2, null);
      }

      if (MountUpdater4.visible() && cockpitActive() && MountRenderer10.framed() && !I) {
         try {
            MountRenderer2.render(C, N, MountRenderer10.bodyMatrix());
         } catch (RuntimeException var5) {
            MountRenderer4.drawFailed();
            if (!var5.toString().equals(W)) {
               W = var5.toString();
               ClientServices.LOGGER.error("Purxiy Garage could not draw the screen", var5);
            }
         }
      }
   }

   public static void render(WorldRenderContext var0) {
      long var1 = 76657571951750L;
      long var3 = var1 ^ 77418607155095L;
      if (!MountRenderer13.capturing() && !IrisCompat.inUse()) {
         e(var0.worldState().field_63082.field_63078, var3, var0);
      }
   }

   private static void e(class_243 var0, long var1, WorldRenderContext var3) {
      long var4 = 132433958302354L;
      MountFactory.Data7.ensureSourceClassInitialized();
      class_310 var6 = class_310.method_1551();
      if (MountUpdater4.visible() && var6.field_1687 != null && var6.field_1724 != null) {
         try {
            n(var3, var6, var4, var0);
            W = null;
         } catch (RuntimeException var13) {
            long var8 = 4197656060688L;
            if (4197656060688L == 0L) {
               boolean var14 = false;
            } else {
               int var10000 = var8 < 0L ? -1 : 1;
            }

            MountRenderer4.drawFailed();
            long var11 = 4197656060689L;
            if (4197656060689L == 0L) {
               boolean var15 = false;
            } else {
               int var16 = var11 < 0L ? -1 : 1;
            }

            if (!var13.toString().equals(W)) {
               W = var13.toString();
               ClientServices.LOGGER.error("Purxiy Garage could not render", var13);
            }
         }
      }
   }

   private static void n(WorldRenderContext var0, class_310 var1, long var2, class_243 var4) {
      long var7 = 8617765630132L;
      long var9 = 81805098545252L;
      long var11 = 35706929709122L;
      short var13 = 8313;
      char var14 = 'ꫜ';
      int var15 = (int)(var11 << 48 >>> 48);
      MountFactory.Data7.ensureSourceClassInitialized();
      float var16 = partialTick();
      MountFactory.Data7 var17 = MountUpdater4.spec();
      if (!var1.field_1690.method_31044().method_31034() || !MountUpdater4.mounted() || cockpitActive()) {
         MountRenderer7.TypeK var18 = MountRenderer7.acquire(var17.id());
         if (var18 != null) {
            class_243 var19 = MountUpdater4.renderPosition(var16);
            if (!(var19.method_1025(var4) > 16384.0)) {
               GpuDevice var20 = RenderSystem.getDevice();
               if (var20.precompilePipeline(s).isValid() && var20.precompilePipeline(B).isValid()) {
                  class_276 var21 = var1.method_1522();
                  if (var21.method_71639() != null && var21.method_71640() != null) {
                     MountUpdater4.pose(D, var16);
                     Matrix4f var22 = new Matrix4f()
                        .translation(
                           (float)(var19.field_1352 - var4.field_1352),
                           (float)(var19.field_1351 - var4.field_1351),
                           (float)(var19.field_1350 - var4.field_1350)
                        );
                     D.compute(var22, var17);
                     boolean var23 = cockpitActive();
                     float var24 = var23 ? 0.0F : e(var19, var4, var9, var17);
                     v(var1, var16, var13, var19, var14, var17, (short)var15, var24, var23);
                     int var25 = p(var21, var23, var7);
                     int var26 = Math.min(var20.getMaxTextureSize(), var21.field_1482 * var25);
                     int var27 = Math.min(var20.getMaxTextureSize(), var21.field_1481 * var25);
                     long var28 = 132433958302353L;
                     if (132433958302353L == 0L) {
                        boolean var10000 = false;
                     } else {
                        int var55 = var28 < 0L ? -1 : 1;
                     }

                     if (T == null) {
                        T = new class_6367("Purxiy garage supersampled ride", var26, var27, true);
                        long var49 = 132433958302354L;
                        if (132433958302354L == 0L) {
                           boolean var58 = false;
                        } else {
                           int var59 = var49 < 0L ? -1 : 1;
                        }
                     } else {
                        label155: {
                           if (T.field_1482 == var26) {
                              long var31 = 132433958302353L;
                              if (132433958302353L == 0L) {
                                 boolean var56 = false;
                              } else {
                                 int var57 = var31 < 0L ? -1 : 1;
                              }

                              if (T.field_1481 == var27) {
                                 break label155;
                              }
                           }

                           T.method_1234(var26, var27);
                        }
                     }

                     P.W = 1.0F / var26;
                     P.c = 1.0F / var27;
                     P.w = var25;
                     GpuBufferSlice var50 = RenderSystem.getDynamicUniforms().method_71106(modelView(), new Vector4f(1.0F), new Vector3f(), new Matrix4f());
                     Matrix4f[] var5 = D.A;
                     MountRenderer7.TypeV var6 = P;
                     MountUpdater7 var33 = d;
                     GpuTextureView var34 = T.method_71639();
                     GpuTextureView var35 = T.method_71640();
                     GpuTextureView var36 = var21.method_71640();
                     long var37 = 132433958302353L;
                     if (132433958302353L == 0L) {
                        boolean var60 = false;
                     } else {
                        int var61 = var37 < 0L ? -1 : 1;
                     }

                     boolean var51;
                     label126: {
                        if (var23) {
                           var51 = MountUpdater4.entering();
                           long var40 = 132433958302353L;
                           if (132433958302353L == 0L) {
                              boolean var62 = false;
                           } else {
                              int var63 = var40 < 0L ? -1 : 1;
                           }

                           if (!var51 && !l) {
                              var51 = true;
                              break label126;
                           }
                        }

                        var51 = false;
                     }

                     MountRenderer7.draw(var18, var5, var6, var33, var34, var35, var36, var50, null, var51);
                     CommandEncoder var52 = var20.createCommandEncoder();
                     class_12137 var38 = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
                     class_12137 var39 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
                     MountRenderer12.render(var0, var1, var16, var17, var18, var19, var4, P);

                     try (RenderPass var53 = var52.createRenderPass(
                           () -> "Purxiy garage composite", var21.method_71639(), OptionalInt.empty(), var21.method_71640(), OptionalDouble.empty()
                        )) {
                        var53.setPipeline(var25 == 1 ? B : s);
                        var53.bindTexture("CarColor", T.method_71639(), var38);
                        long var41 = 132433958302354L;
                        if (132433958302354L == 0L) {
                           boolean var64 = false;
                        } else {
                           int var65 = var41 < 0L ? -1 : 1;
                        }

                        var53.bindTexture("CarDepth", T.method_71640(), var39);
                        var53.draw(0, 3);
                        MountRenderer4.drawn(true);
                        long var44 = 132433958302353L;
                        if (132433958302353L == 0L) {
                           boolean var66 = false;
                        } else {
                           int var67 = var44 < 0L ? -1 : 1;
                        }
                     }

                     if (var23) {
                        MountRenderer13.update(var16, P);
                        MountRenderer9.render(var0, var17, D, var16, D.J);
                        MountRenderer10.render(var0, D.J);
                        MountRenderer13.render(var0, D.J);
                     }

                     class_276 var54 = var0 == null ? null : var0.worldRenderer().method_29360();
                     if (var54 != null) {
                        var54.method_29329(var21);
                     }
                  }
               }
            }
         }
      }
   }

   private static int p(class_276 var0, boolean var1, long var2) {
      MountFactory.Data7.ensureSourceClassInitialized();
      long var4 = 8617765630132L;
      if (8617765630132L == 0L) {
         boolean var10000 = false;
      } else {
         int var9 = var4 < 0L ? -1 : 1;
      }

      if (c > 0) {
         return c;
      } else {
         if (!var1 && !MountRenderer4.K.B.is("Performance")) {
            long var7 = 8617765630131L;
            if (8617765630131L == 0L) {
               boolean var10 = false;
            } else {
               int var11 = var7 < 0L ? -1 : 1;
            }

            if (var0.field_1482 <= 2600) {
               return 2;
            }
         }

         return 1;
      }
   }

   private static void v(class_310 var0, float var1, int var2, class_243 var3, int var4, MountFactory.Data7 var5, short var6, float var7, boolean var8) {
      class_4184 var11;
      boolean var12;
      float var13;
      label47: {
         long var9 = 35706929709122L;
         MountFactory.Data7.ensureSourceClassInitialized();
         var11 = var0.field_1773.method_19418();
         class_2338 var14 = class_2338.method_49638(var3.method_1031(0.0, 1.0, 0.0));
         P.N = var0.field_1687.method_8314(class_1944.field_9284, var14) / 15.0F;
         P.l = var0.field_1687.method_8314(class_1944.field_9282, var14) / 15.0F;
         P.y = var0.field_1687.method_8430(var1);
         P.E = MountUpdater4.time(var1);
         var12 = var0.field_1687.method_8597().comp_642();
         float var15 = var11.method_75756().method_75689(class_12206.field_64340, var1) * (float) (Math.PI / 180.0);
         var13 = var12 ? class_3532.method_15363(var11.method_75756().method_75689(class_12206.field_64346, var1), 0.0F, 1.0F) : 0.0F;
         Vector3f var16 = new Vector3f(-class_3532.method_15374(var15), class_3532.method_15362(var15), 0.22F).normalize();
         float var17 = class_3532.method_15363(-var16.y * 4.0F - 0.1F, 0.0F, 1.0F);
         float var18 = var12 ? class_3532.method_15363(var16.y * 5.0F + 0.1F, 0.0F, 1.0F) : 0.0F;
         char var19 = 'ꫜ';
         float var20;
         int var21 = (var20 = var17 - 0.0F) == 0.0F ? 0 : (var20 < 0.0F ? -1 : 1);
         if (var21 > 0) {
            var19 = 'ꫜ';
            if (var12) {
               P.T.set(var16).negate();
               P.z = var17 * 0.12F;
               var19 = 'ꫜ';
               P.V.set(0.6F, 0.7F, 1.0F);
               break label47;
            }
         }

         P.T.set(var16);
         P.z = var18;
         float var22 = 1.0F - class_3532.method_15363(var16.y * 2.2F, 0.0F, 1.0F);
         P.V.set(1.0F, class_3532.method_16439(var22, 0.96F, 0.62F), class_3532.method_16439(var22, 0.9F, 0.3F));
      }

      Object var23 = var11.method_75756().method_75689(class_12206.field_63746, var1);
      short var24 = 8313;
      MountRenderer7.linear((Integer)var23, P.b);
      MountRenderer7.linear(var11.method_75756().method_75689(class_12206.field_63742, var1), P.Y);
      var24 = -32702;
      if (!var12) {
         P.b.set(P.Y);
      }

      MountModule var26 = MountRenderer4.K;
      MountRenderer7.linear(var26.J.argb(), P.h);
      MountRenderer7.linear(var26.r.argb(), P.e);
      int var27 = Theme.tint(-4222985);
      P.B.set((var27 >> 16 & 0xFF) / 255.0F, (var27 >> 8 & 0xFF) / 255.0F, (var27 & 0xFF) / 255.0F);
      P.Q = 0.55F;
      float var28 = var8 ? class_3532.method_15363(MountUpdater4.boarding(var1), 0.0F, 1.0F) : 0.0F;
      var28 = var28 * var28 * (3.0F - 2.0F * var28);
      P.v = var28;
      P.u = class_3532.method_16439(var28, 0.62F, 1.25F);
      MountTransform.apply(P, var26, var5, var13, P.N, P.l, var1);
      P.D = class_3532.method_16439(var7, 0.08F, 0.3F);
      P.t = class_3532.method_16439(var7, 0.32F, 0.6F);
      P.I = var8 && !MountUpdater4.entering() ? 0.0F : 1.0F;
      P.i = var7;
      d.update(var0.field_1687, var3, var13, var8);
   }

   private static float e(class_243 var0, class_243 var1, long var2, MountFactory.Data7 var4) {
      MountFactory.Data7.ensureSourceClassInitialized();
      class_243 var5 = var1.method_1020(var0);
      float var6 = D.P * (float) (Math.PI / 180.0);
      float var7 = class_3532.method_15374(var6);
      float var8 = class_3532.method_15362(var6);
      double var9 = Math.abs(var8 * var5.field_1352 + var7 * var5.field_1350);
      double var11 = -var7 * var5.field_1352 + var8 * var5.field_1350;
      double var13 = var5.field_1351;
      double var15 = var4.bike() ? 0.32 : var4.halfWidth() * 0.88;
      double var17 = Math.max(
         var9 - var15, Math.max(Math.max(0.1 - var13, var13 - var4.height() * 0.94), Math.max(var4.lo().z * 0.92 - var11, var11 - var4.hi().z * 0.92))
      );
      return (float)Math.clamp(-var17 / 0.25, 0.0, 1.0);
   }

   public static void close() {
      MountRenderer7.close();
      d.close();
      MountRenderer12.close();
      MountRenderer2.close();
      MountRenderer13.close();
      MountRenderer6.close();
      MountRenderer.close();
      if (T != null) {
         T.method_1238();
      }

      T = null;
      S = false;
      W = null;
   }

   public static void releasePipelines() {
      ClientCache.release(s);
      ClientCache.release(B);
   }

}
