// Sourced By DevFFNightmare
package dev.purxiy.modules.outline;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import dev.purxiy.loader.fabric.mixin.OutlineMiningAccess;
import dev.purxiy.render.pipeline.SkyPipelines;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.OptionalLong;
import net.minecraft.class_12074;
import net.minecraft.class_1269;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_243;
import net.minecraft.class_2680;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_3726;
import net.minecraft.class_3965;
import net.minecraft.class_636;
import net.minecraft.class_638;
import net.minecraft.class_239.class_240;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;

import org.lwjgl.system.MemoryUtil;

public final class BlockOutlineRenderer {
   private static final int D = 192;
   private static final int ao = 8;
   private static final int aa = 16;
   private static final int ap = 18;
   private static final int h = 13472;
   private static final int t = 16;
   private static final int v = 12;
   private static final int M = 5;
   private static final double aD = 2.75;
   private static final float aZ = 620.0F;
   private static final float d = 2.0F * (float)Math.sqrt(620.0) * 0.85F;
   private static final float F = 0.2F;
   private static final float ab = 0.25F;
   private static final float a_ = 0.125F;
   private static final float z = 0.9947917F;
   private static final float A = 4.2F;
   private static final float N = 1.3F;
   private static final float I = 0.16F;
   private static final Matrix4f aR = new Matrix4f();
   private static final Matrix4f s = new Matrix4f();
   private static final Matrix4f aO = new Matrix4f();
   private static long B = 0L;
   
   private static class_638 l;
   
   private static BlockOutlineFactory Z;
   
   private static BlockOutlineFactory k;
   
   private static class_2338 m;
   private static boolean u;
   private static float j = 1.0F;
   private static final double[] aX = new double[6];
   private static final double[] av = new double[6];
   private static final double[] C = new double[6];
   private static final Animator w = new Animator(0.0F, 420.0F, 41.0F);
   private static final Animator Y = new Animator(1.0F, 380.0F, 23.0F);
   private static float r;
   private static float H;
   private static final double[] G = new double[3];
   private static final int[] a = new int[3];
   private static float aI;
   private static final List<BlockOutlineRenderer.TypeS> n = new ArrayList<>();
   
   private static class_2338 Q;
   
   private static BlockOutlineFactory aH;
   
   private static class_2338 X;
   
   private static class_2680 i;
   
   private static class_2680 x;
   private static final float[] aS = new float[3072];
   private static final float[] T = new float[96];
   private static final float[] P = new float[128];
   private static int a4 = 0;
   private static int aA = 0;
   private static int O = 0;
   private static float S;
   private static float q;
   private static float g;
   private static float a3;
   private static int p = 0;
   private static int J = 0;
   private static final Vector4f f = new Vector4f();
   private static final Vector4f aN = new Vector4f();
   private static final Vector4f am = new Vector4f();
   private static GpuBuffer V;
   private static ByteBuffer U;
   private static String o;
   private static boolean R;
   private static GpuQuery y;
   private static double K;
   private static int L = 0;
   static BlockOutlineModule e;
   private static ModuleContext E;
   private static int W = 0;
   private static int b = 0;
   public static boolean c;




   private static final long fb = 13472L;

   private BlockOutlineRenderer() {
   }

   public static void beginFrame(Matrix4fc var0) {
      aR.set(var0);
   }

   public static void extract( class_12074 var0) {
      long var1 = 139613867124526L;
      long var3 = var1 ^ 31265528762321L;
      long var5 = var1 ^ 9886632464666L;
      long var7 = var1 ^ 114839994460462L;
      long var9 = var1 ^ 118697774549597L;
      int var11 = (int)((var1 ^ 118697774549597L) >>> 32);
      int var12 = (int)((var1 ^ 118697774549597L) << 32 >>> 48);
      int var13 = (int)(var9 << 48 >>> 48);
      long var14 = System.nanoTime();
      float var16 = B != 0L && var14 - B < 500000000L ? Math.min((float)(var14 - B) / 1.0E9F, 0.1F) : 0.0F;
      B = var14;
      BlockOutlineModule var17 = e;
      class_310 var18 = class_310.method_1551();
      if (!var17.enabled() || var18.field_1687 != l) {
         l = var18.field_1687;
         v();
         if (!var17.enabled()) {
            return;
         }
      }

      float var19 = var16 * var17.U.floatValue();
      BlockOutlineFactory var20 = var0 == null ? null : BlockOutlineFactory.of(var0.comp_4935());
      boolean var21 = false;
      if (var0 != null && var20 != null) {
         class_2338 var22 = var0.comp_4932();
         Y(C, var22, var20);
         ClientSupport3.blockBounds(var22, C);
         boolean var23 = !var22.equals(m);
         if (Z == null) {
            F(var5);
            var21 = true;
         } else if (var23 || !var20.sameAs(Z)) {
            boolean var24 = var17.r.enabled();
            if (var24 && x(aX, C) <= 2.75) {
               if (!var20.sameAs(Z)) {
                  k = Z;
                  j = 0.0F;
               }
            } else {
               if (var24) {
                  V(var7, BlockOutlineRenderer.TypeS.fade(Z, aX, w.value() * Y.value()));
                  w.snap(0.0F);
                  Y.snap(0.92F).target(1.0F);
               }

               r(var3);
               k = null;
               j = 1.0F;
            }
         }

         if (var23) {
            r = 0.0F;
         }

         m = var22.method_10062();
         Z = var20;
         u = var0.comp_4933();
         w.target(1.0F);
      } else {
         w.target(0.0F);
         if (w.value() < 0.004F) {
            Z = null;
            k = null;
            m = null;
         }
      }

      if (var18.field_1765 instanceof class_3965 var26 && var26.method_17783() == class_240.field_1332 && Z != null && var0 != null) {
         class_243 var29 = var26.method_17784();
         float var31 = var21 ? 1.0F : 1.0F - (float)Math.exp(-var16 * 30.0);
         G[0] = G[0] + (var29.field_1352 - G[0]) * var31;
         G[1] = G[1] + (var29.field_1351 - G[1]) * var31;
         G[2] = G[2] + (var29.field_1350 - G[2]) * var31;
         class_2350 var25 = var26.method_17780();
         a[0] = var25.method_10148();
         a[1] = var25.method_10164();
         a[2] = var25.method_10165();
      }

      float var27 = 0.0F;
      class_636 var30 = var18.field_1761;
      if (var17.I.enabled() && var30 != null && m != null && var0 != null) {
         OutlineMiningAccess var32 = (OutlineMiningAccess)var30;
         if (m.equals(var32.purxiy$destroyBlockPos())) {
            var27 = Math.clamp(var32.purxiy$destroyProgress(), 0.0F, 1.0F);
         }
      }

      r = r + (var27 - r) * (1.0F - (float)Math.exp(-var16 * (var27 > r ? 14.0 : 9.0)));
      w(var19, var11, var12, (char)var13);
      w.update(var19);
      Y.update(var19);
      j = Math.min(1.0F, j + var19 / 0.2F);
      if (j >= 1.0F) {
         k = null;
      }

      H = H * (float)Math.exp(-var19 * 5.0);
      aI += var19;
      if (aI > 4.2F) {
         aI -= 4.2F;
      }

      n.removeIf(var1x -> {
         BlockOutlineModule.ensureSourceClassInitialized();
         return (var1x.r += var19) >= var1x.T;
      });
   }

   public static boolean render(boolean var0, class_243 var1) {
      long var2 = 109148644781507L;
      long var4 = var2 ^ 51134639136856L;
      BlockOutlineModule var6 = e;
      if (!var6.enabled() || !RenderSystem.getDevice().precompilePipeline(SkyPipelines.OUTLINE).isValid()) {
         return false;
      } else if (var0 != u) {
         return true;
      } else {
         try {
            o(var4, var6, var1);
            return true;
         } catch (Throwable var9) {
            metric("draw_failures");
            String var8 = var9.toString();
            if (!var8.equals(o)) {
               o = var8;
               ClientServices.LOGGER.error("Purxiy block outline failed, showing vanilla's", var9);
            }

            return false;
         }
      }
   }

   public static void beforeBreak(class_2338 var0) {
      Q = null;
      aH = null;
      BlockOutlineModule var1 = e;
      class_310 var2 = class_310.method_1551();
      if (var1.enabled() && var1.v.enabled() && var2.field_1687 != null && var2.field_1724 != null) {
         class_2680 var3 = var2.field_1687.method_8320(var0);
         if (!var3.method_26215()) {
            aH = BlockOutlineFactory.of(var3.method_26172(var2.field_1687, var0, class_3726.method_16195(var2.field_1724)));
            Q = var0.method_10062();
         }
      }
   }

   public static void afterBreak(class_2338 var0, boolean var1) {
      long var2 = 89867949298486L;
      long var4 = var2 ^ 78289281883446L;
      if (var1 && aH != null && var0.equals(Q)) {
         double[] var6 = new double[6];
         Y(var6, var0, aH);
         V(var4, BlockOutlineRenderer.TypeS.ripple(aH, var6));
      }

      Q = null;
      aH = null;
   }

   public static void beforeUse(class_3965 var0) {
      class_310 var1 = class_310.method_1551();
      X = null;
      if (var1.field_1687 != null && e.enabled() && e.v.enabled()) {
         X = var0.method_17777().method_10062();
         i = var1.field_1687.method_8320(X);
         x = var1.field_1687.method_8320(X.method_10093(var0.method_17780()));
      }
   }

   public static void afterUse(class_3965 var0, class_1269 var1) {
      class_310 var2 = class_310.method_1551();
      if (X != null
         && var2.field_1687 != null
         && var1.method_23665()
         && (var2.field_1687.method_8320(X) != i || var2.field_1687.method_8320(X.method_10093(var0.method_17780())) != x)) {
         H = 1.0F;
      }

      X = null;
      i = null;
      x = null;
   }

   private static void F(long var0) {
      long var2 = 108396120959231L;
      r(var2);
      w.snap(0.0F).target(1.0F);
      Y.snap(0.9F).target(1.0F);
      k = null;
      j = 1.0F;
      r = 0.0F;
      aI = -0.12F;
   }

   private static void v() {
      Z = null;
      k = null;
      m = null;
      n.clear();
      w.snap(0.0F);
      Y.snap(1.0F);
      r = 0.0F;
      H = 0.0F;
      j = 1.0F;
   }

   private static void Y(double[] var0, class_2338 var1, BlockOutlineFactory var2) {
      var0[0] = var1.method_10263() + (var2.E + var2.I) * 0.5;
      var0[1] = var1.method_10264() + (var2.R + var2.O) * 0.5;
      var0[2] = var1.method_10260() + (var2.C + var2.b) * 0.5;
      var0[3] = var2.sizeX() * 0.5;
      var0[4] = var2.sizeY() * 0.5;
      var0[5] = var2.sizeZ() * 0.5;
   }

   private static void r(long var0) {
      System.arraycopy(C, 0, aX, 0, 6);
      Arrays.fill(av, 0.0);
   }

   private static void w(float var0, int var1, int var2, char var3) {
      BlockOutlineModule.ensureSourceClassInitialized();

      while (var0 > 0.0F) {
         float var4 = Math.min(var0, 0.004166667F);

         for (int var5 = 0; var5 < 6; var5++) {
            double var6 = -620.0 * (aX[var5] - C[var5]) - d * av[var5];
            av[var5] = av[var5] + var6 * var4;
            aX[var5] = aX[var5] + av[var5] * var4;
         }

         var0 -= var4;
      }
   }

   private static double x(double[] var0, double[] var1) {
      double var2 = var0[0] - var1[0];
      double var4 = var0[1] - var1[1];
      double var6 = var0[2] - var1[2];
      return Math.sqrt(var2 * var2 + var4 * var4 + var6 * var6);
   }

   private static void V(long var0, BlockOutlineRenderer.TypeS var2) {
      BlockOutlineModule.ensureSourceClassInitialized();
      long var3 = 24774040866303L;
      if (24774040866303L == 0L) {
         boolean var10000 = false;
      } else {
         int var6 = var3 < 0L ? -1 : 1;
      }

      if (n.size() >= 5) {
         n.removeFirst();
      }

      n.add(var2);
   }

   private static float M(long var0) {
      BlockOutlineModule.ensureSourceClassInitialized();
      if (!(aI < 0.0F)) {
         long var2 = 56745629202101L;
         if (56745629202101L == 0L) {
            boolean var10000 = false;
         } else {
            int var5 = var2 < 0L ? -1 : 1;
         }

         if (!(aI > 1.3F)) {
            return -0.35F + 1.7F * Easing.SINE_IN_OUT.apply(aI / 1.3F);
         }
      }

      return -10.0F;
   }

   private static void o(long var0, BlockOutlineModule var2, class_243 var3) {
      long var4 = 103959897737421L;
      byte var6 = 0;
      int var7 = 1586302150;
      int var8 = (int)(var4 << 48 >>> 48);
      long var9 = 56745629202101L;
      long var11 = 74560165377627L;
      long var13 = 111096190029488L;
      var4 = 51174011618593L;
      BlockOutlineModule.ensureSourceClassInitialized();
      class_276 var15 = class_310.method_1551().method_1522();
      GpuTextureView var16 = var15.method_71639();
      GpuTextureView var17 = var15.method_71640();
      if (var16 != null && var17 != null) {
         p = var15.field_1482;
         J = var15.field_1481;
         s.set(aR).mul(RenderSystem.getModelViewMatrix());
         s.invert(aO);
         a4 = 0;
         aA = 0;
         O = 0;
         q = Float.POSITIVE_INFINITY;
         S = Float.POSITIVE_INFINITY;
         a3 = Float.NEGATIVE_INFINITY;
         g = Float.NEGATIVE_INFINITY;
         float var18 = w.value();
         boolean var19 = var2.g.get() > 0.0 || var2.V.get() > 0.0 || r > 0.001F;
         if (Z != null && var18 > 0.004F) {
            float var20 = k == null ? 1.0F : Easing.SMOOTHSTEP.apply(Math.min(1.0F, j * 2.0F));
            float var21 = Easing.SMOOTHSTEP.apply(Math.min(1.0F, 2.0F - j * 2.0F));
            float var22 = Y.value() * (1.0F - 0.02F * r);
            float var23 = var18 * var20;
            float var24 = r > 0.001F ? 1.0F : 0.0F;
            float var27 = H;
            float var29 = 0.0F;
            long var31 = 85506628210075L;
            if (85506628210075L == 0L) {
               boolean var10000 = false;
            } else {
               int var84 = var31 < 0L ? -1 : 1;
            }

            h(Z, aX, var22, var6, var7, var23, var29, var24, (short)var8, var27, var3, var19);
            if (k != null) {
               var23 = var18 * var21;
               boolean var25 = false;
               var27 = H;
               float var28 = 0.0F;
               var29 = 0.0F;
               h(k, aX, var22, var6, var7, var23, var29, var28, (short)var8, var27, var3, var25);
            }
         }

         for (int var65 = n.size() - 1; var65 >= 0; var65--) {
            BlockOutlineRenderer.TypeS var67 = n.get(var65);
            float var68 = var67.r / var67.T;
            long var71 = 85506628210074L;
            if (85506628210074L == 0L) {
               boolean var85 = false;
            } else {
               int var86 = var71 < 0L ? -1 : 1;
            }

            h(var67.Q, var67.D, var67.scale(var68), var6, var7, var67.alpha(var68), var67.heat(var68), 0.0F, (short)var8, 0.0F, var3, false);
         }

         long var66 = 85506628210075L;
         if (85506628210075L == 0L) {
            boolean var87 = false;
         } else {
            int var88 = var66 < 0L ? -1 : 1;
         }

         if (a4 != 0) {
            float var72 = Math.max(0.75F, J / 1080.0F);
            float var73 = var2.B.floatValue() * 0.5F * var72;
            float var74 = (7.0F + 2.0F * var2.B.floatValue()) * var72;
            float var26 = var73 + var74 + 2.0F;
            float var76 = Math.max(0.0F, S - var26);
            float var77 = Math.max(0.0F, q - var26);
            float var79 = Math.min((float)p, g + var26);
            float var30 = Math.min((float)J, a3 + var26);
            if (!(var76 >= var79) && !(var77 >= var30)) {
               if (V == null) {
                  V = RenderSystem.getDevice().createBuffer(ClientServices.label(() -> "Purxiy block outline"), 136, 13472L);
                  U = MemoryUtil.memAlloc(13472);
               }

               int var80 = var2.D.argb();
               int var32 = var2.N.enabled() ? var2.H.argb() : var80;
               Std140Builder var33 = Std140Builder.intoBuffer(U.clear())
                  .putMat4f(s)
                  .putMat4f(aO)
                  .putVec4(var76 / p * 2.0F - 1.0F, var77 / J * 2.0F - 1.0F, var79 / p * 2.0F - 1.0F, var30 / J * 2.0F - 1.0F);
               float var34 = p;
               float var35 = J;
               long var36 = 85506628210075L;
               if (85506628210075L == 0L) {
                  boolean var89 = false;
               } else {
                  int var90 = var36 < 0L ? -1 : 1;
               }

               Std140Builder var39 = var33.putVec4(var34, var35, var72, 0.0F)
                  .putVec4(k(var11, var80, 16), k(var11, var80, 8), k(var11, var80, 0), k(var11, var80, 24))
                  .putVec4(k(var11, var32, 16), k(var11, var32, 8), k(var11, var32, 0), k(var11, var32, 24))
                  .putVec4(var73, var74, var2.h.floatValue(), var2.corners() ? 0.25F : 0.0F)
                  .putVec4(var2.g.floatValue(), var2.V.floatValue(), var2.R.enabled() ? 0.4F : 0.0F, var2.Z.floatValue())
                  .putVec4((float)(G[0] - var3.field_1352), (float)(G[1] - var3.field_1351), (float)(G[2] - var3.field_1350), Z != null ? 1.0F : 0.0F)
                  .putVec4(a[0], a[1], a[2], r)
                  .putVec4(M(var9), 0.16F, 0.125F, 0.0F)
                  .putVec4(a4, O, 0.0F, 0.0F);

               for (int var40 = 0; var40 < 24; var40++) {
                  long var41 = 85506628210075L;
                  if (85506628210075L == 0L) {
                     boolean var91 = false;
                  } else {
                     int var92 = var41 < 0L ? -1 : 1;
                  }

                  var39.putVec4(T[var40 * 4], T[var40 * 4 + 1], T[var40 * 4 + 2], T[var40 * 4 + 3]);
               }

               long var81 = 85506628210074L;
               if (85506628210074L == 0L) {
                  boolean var93 = false;
               } else {
                  int var94 = var81 < 0L ? -1 : 1;
               }

               for (int var42 = 0; var42 < 32; var42++) {
                  long var43 = 85506628210074L;
                  if (85506628210074L == 0L) {
                     boolean var95 = false;
                  } else {
                     int var96 = var43 < 0L ? -1 : 1;
                  }

                  var39.putVec4(P[var42 * 4], P[var42 * 4 + 1], P[var42 * 4 + 2], P[var42 * 4 + 3]);
               }

               long var82 = 85506628210075L;
               if (85506628210075L == 0L) {
                  boolean var97 = false;
               } else {
                  int var98 = var82 < 0L ? -1 : 1;
               }

               for (int var44 = 0; var44 < a4 * 4; var44++) {
                  var39.putVec4(aS[var44 * 4], aS[var44 * 4 + 1], aS[var44 * 4 + 2], aS[var44 * 4 + 3]);
               }

               long var83 = 85506628210075L;
               if (85506628210075L == 0L) {
                  boolean var99 = false;
               } else {
                  int var100 = var83 < 0L ? -1 : 1;
               }

               CommandEncoder var46 = RenderSystem.getDevice().createCommandEncoder();
               long var47 = 85506628210075L;
               if (85506628210075L == 0L) {
                  boolean var101 = false;
               } else {
                  int var102 = var47 < 0L ? -1 : 1;
               }

               var46.writeToBuffer(V.slice(), var39.get());
               GpuQuery var49 = R ? j(var46, var13) : null;
               boolean var50 = false;

               try {
                  var50 = true;
                  long var51 = 85506628210074L;
                  if (85506628210074L == 0L) {
                     boolean var103 = false;
                  } else {
                     int var104 = var51 < 0L ? -1 : 1;
                  }

                  try (RenderPass var53 = var46.createRenderPass(() -> "Purxiy block outline", var16, OptionalInt.empty())) {
                     var53.setPipeline(SkyPipelines.OUTLINE);
                     RenderSystem.bindDefaultUniforms(var53);
                     long var54 = 85506628210075L;
                     if (85506628210075L == 0L) {
                        boolean var105 = false;
                     } else {
                        int var106 = var54 < 0L ? -1 : 1;
                     }

                     var53.setUniform("PurxiyOutline", V);
                     var53.bindTexture("DepthSampler", var17, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
                     int var69 = -1875696507;
                     var53.draw(0, 6);
                     metric("drawn");
                  }
               } finally {
                  if (var50 && var49 != null) {
                     var46.timerQueryEnd(var49);
                     y = var49;
                  }
               }

               if (var49 != null) {
                  var46.timerQueryEnd(var49);
                  y = var49;
               }
            }
         }
      }
   }

   public static void setTiming(boolean var0) {
      R = var0;
      K = 0.0;
      L = 0;
   }

   private static GpuQuery j(CommandEncoder var0, long var1) {
      BlockOutlineModule.ensureSourceClassInitialized();
      if (y != null) {
         OptionalLong var3 = y.getValue();
         long var4 = 111096190029487L;
         if (111096190029487L == 0L) {
            boolean var10000 = false;
         } else {
            int var10 = var4 < 0L ? -1 : 1;
         }

         if (var3.isEmpty()) {
            return null;
         }

         y.close();
         y = null;
         K = K + var3.getAsLong() / 1000000.0;
         int var7 = L + 1;
         long var8 = 111096190029487L;
         if (111096190029487L == 0L) {
            boolean var11 = false;
         } else {
            int var12 = var8 < 0L ? -1 : 1;
         }

         L = var7;
         if (var7 == 240) {
            ClientServices.LOGGER
               .info("Purxiy block outline: {} ms GPU per frame ({} edges, {}x{})", new Object[]{String.format(Locale.ROOT, "%.3f", K / L), a4, p, J});
            K = 0.0;
            L = 0;
         }
      }

      return var0.timerQueryBegin();
   }

   private static void h(
      BlockOutlineFactory var0,
      double[] var1,
      float var2,
      short var3,
      int var4,
      float var5,
      float var6,
      float var7,
      short var8,
      float var9,
      class_243 var10,
      boolean var11
   ) {
      long var12 = 103959897737421L;
      long var14 = var12 ^ 69885963535620L;
      long var16 = var12 ^ 15216289479140L;
      BlockOutlineModule.ensureSourceClassInitialized();
      if (aA < 8 && !(var5 <= 0.004F)) {
         int var18 = aA;
         float var19 = i(var14, var1[3], var0.sizeX()) * var2;
         float var20 = i(var14, var1[4], var0.sizeY()) * var2;
         float var21 = i(var14, var1[5], var0.sizeZ()) * var2;
         double var22 = var1[0] - (var0.E + var0.I) * 0.5 * var19 - var10.field_1352;
         double var24 = var1[1] - (var0.R + var0.O) * 0.5 * var20 - var10.field_1351;
         double var26 = var1[2] - (var0.C + var0.b) * 0.5 * var21 - var10.field_1350;
         int var28 = a4;
         float[] var29 = var0.D;

         for (byte var30 = 0; var30 < var29.length; var30 = (byte)(var30 + 6)) {
            short var31 = 0;
            var31 = -30515;
            if (a4 >= 192) {
               break;
            }

            Y(
               (float)(var29[var30] * var19 + var22),
               (float)(var29[var30 + 1] * var20 + var24),
               (float)(var29[var30 + 2] * var21 + var26),
               (float)(var29[var30 + 3] * var19 + var22),
               (float)(var29[var30 + 4] * var20 + var24),
               var16,
               (float)(var29[var30 + 5] * var21 + var26),
               var18
            );
         }

         int var35 = -30515;
         var35 = 1586302150;
         if (a4 != var28 || var11) {
            aA++;
            int var39 = var18 * 12;
            T[var39] = (float)(var0.E * var19 + var22);
            T[var39 + 1] = (float)(var0.R * var20 + var24);
            T[var39 + 2] = (float)(var0.C * var21 + var26);
            T[var39 + 3] = var5;
            T[var39 + 4] = (float)(var0.I * var19 + var22);
            T[var39 + 5] = (float)(var0.O * var20 + var24);
            T[var39 + 6] = (float)(var0.b * var21 + var26);
            T[var39 + 7] = var6;
            T[var39 + 8] = var7;
            T[var39 + 9] = var9;
            T[var39 + 10] = 0.0F;
            T[var39 + 11] = 0.0F;
            if (var11 && var18 == 0) {
               float[] var32 = var0.J;

               for (byte var33 = 0; var33 < var32.length; var33 = (byte)(var33 + 6)) {
                  var35 = 1586302150;
                  if (O >= 16) {
                     break;
                  }

                  int var34 = O++ * 8;
                  P[var34] = (float)(var32[var33] * var19 + var22);
                  P[var34 + 1] = (float)(var32[var33 + 1] * var20 + var24);
                  P[var34 + 2] = (float)(var32[var33 + 2] * var21 + var26);
                  P[var34 + 4] = (float)(var32[var33 + 3] * var19 + var22);
                  P[var34 + 5] = (float)(var32[var33 + 4] * var20 + var24);
                  P[var34 + 6] = (float)(var32[var33 + 5] * var21 + var26);
               }
            }
         }
      }
   }

   private static float i(long var0, double var2, float var4) {
      BlockOutlineModule.ensureSourceClassInitialized();
      return var4 > 1.0E-5F ? (float)(var2 / (var4 * 0.5F)) : 1.0F;
   }

   private static void Y(float var0, float var1, float var2, float var3, float var4, long var5, float var7, int var8) {
      BlockOutlineModule.ensureSourceClassInitialized();
      s.transform(f.set(var0, var1, var2, 1.0F));
      s.transform(aN.set(var3, var4, var7, 1.0F));
      float var9 = f.z + f.w;
      float var10 = aN.z + aN.w;
      if (!(var9 < 1.0E-4F) || !(var10 < 1.0E-4F)) {
         if (var9 < 1.0E-4F) {
            long var11 = 91654019215657L;
            if (91654019215657L == 0L) {
               boolean var10000 = false;
            } else {
               int var22 = var11 < 0L ? -1 : 1;
            }

            float var14 = (1.0E-4F - var9) / (var10 - var9);
            var0 += (var3 - var0) * var14;
            var1 += (var4 - var1) * var14;
            var2 += (var7 - var2) * var14;
            f.lerp(aN, var14);
         } else if (var10 < 1.0E-4F) {
            float var19 = (1.0E-4F - var10) / (var9 - var10);
            var3 += (var0 - var3) * var19;
            var4 += (var1 - var4) * var19;
            var7 += (var2 - var7) * var19;
            aN.lerp(f, var19);
         }

         float var20 = (f.x / f.w * 0.5F + 0.5F) * p;
         float var12 = (f.y / f.w * 0.5F + 0.5F) * J;
         float var13 = (aN.x / aN.w * 0.5F + 0.5F) * p;
         float var21 = (aN.y / aN.w * 0.5F + 0.5F) * J;
         S = Math.min(S, Math.min(var20, var13));
         q = Math.min(q, Math.min(var12, var21));
         g = Math.max(g, Math.max(var20, var13));
         a3 = Math.max(a3, Math.max(var12, var21));
         int var15 = a4++ * 16;
         aS[var15] = var20;
         aS[var15 + 1] = var12;
         aS[var15 + 2] = I(var0, var1, var2);
         aS[var15 + 3] = f.w;
         aS[var15 + 4] = var13;
         aS[var15 + 5] = var21;
         aS[var15 + 6] = I(var3, var4, var7);
         aS[var15 + 7] = aN.w;
         aS[var15 + 8] = var0;
         aS[var15 + 9] = var1;
         aS[var15 + 10] = var2;
         aS[var15 + 11] = var8;
         aS[var15 + 12] = var3;
         aS[var15 + 13] = var4;
         aS[var15 + 14] = var7;
         float var16 = var3 - var0;
         float var17 = var4 - var1;
         float var18 = var7 - var2;
         aS[var15 + 15] = (float)Math.sqrt(var16 * var16 + var17 * var17 + var18 * var18);
      }
   }

   private static float I(float var0, float var1, float var2) {
      s.transform(am.set(var0 * 0.9947917F, var1 * 0.9947917F, var2 * 0.9947917F, 1.0F));
      return am.z / am.w * 0.5F + 0.5F;
   }

   private static float k(long var0, int var2, int var3) {
      return (var2 >>> var3 & 0xFF) / 255.0F;
   }

   public static void install(BlockOutlineModule var0, ModuleContext var1) {
      e = var0;
      E = var1;
   }

   public static void metric(String var0) {
      if (E != null) {
         E.metric(var0, var0.equals("drawn") ? ++W : ++b);
      }
   }

   public static void uninstall() {
      int var0 = 0;

      try {
         ClientCache.release(SkyPipelines.OUTLINE);
      } catch (Exception var32) {
         var0++;
      }

      try {
         if (y != null) {
            y.close();
         }
      } catch (Exception var30) {
         var0++;
      } finally {
         y = null;
      }

      try {
         if (V != null) {
            V.close();
         }
      } catch (Exception var28) {
         var0++;
      } finally {
         V = null;
      }

      try {
         if (U != null) {
            MemoryUtil.memFree(U);
         }
      } catch (Exception var26) {
         var0++;
      } finally {
         U = null;
      }

      v();
      l = null;
      e = null;
      E = null;
      B = 0L;
      if (var0 != 0) {
         throw new IllegalStateException("World visual GPU cleanup failed");
      }
   }

private static final class TypeS {
      final BlockOutlineFactory Q;
      final double[] D;
      final float T;
      final float G;
      final boolean F;
      float r;

      private TypeS(BlockOutlineFactory var1, double[] var2, float var3, float var4, boolean var5) {
         this.Q = var1;
         this.D = var2;
         this.T = var3;
         this.G = var4;
         this.F = var5;
      }

      static BlockOutlineRenderer.TypeS fade(BlockOutlineFactory var0, double[] var1, float var2) {
         return new BlockOutlineRenderer.TypeS(var0, (double[])var1.clone(), 0.2F, var2, false);
      }

      static BlockOutlineRenderer.TypeS ripple(BlockOutlineFactory var0, double[] var1) {
         return new BlockOutlineRenderer.TypeS(var0, var1, 0.55F, 1.0F, true);
      }

      float alpha(float var1) {
         return this.F ? this.G * (1.0F - var1) * (1.0F - var1) : this.G * (1.0F - Easing.SMOOTHSTEP.apply(var1));
      }

      float scale(float var1) {
         return this.F ? 1.0F + 0.16F * Easing.EXPO_OUT.apply(var1) : 1.0F - 0.05F * Easing.CUBIC_OUT.apply(var1);
      }

      float heat(float var1) {
         return this.F ? 1.0F - 0.5F * var1 : 0.0F;
      }
   }
}
