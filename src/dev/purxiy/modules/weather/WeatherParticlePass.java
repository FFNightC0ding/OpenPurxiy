package dev.purxiy.modules.weather;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.systems.RenderSystem.class_5590;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.assets.IrisCompat;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.render.pipeline.SkyVertexFormat;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.OptionalInt;
import java.util.Random;
import net.minecraft.class_12206;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_265;
import net.minecraft.class_2680;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_3610;
import net.minecraft.class_4184;
import net.minecraft.class_5636;
import net.minecraft.class_638;
import net.minecraft.class_9909;
import net.minecraft.class_9916;
import net.minecraft.class_9925;
import net.minecraft.class_9960;
import net.minecraft.class_2338.class_2339;
import net.minecraft.class_2902.class_2903;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;

public final class WeatherParticlePass {
   private static final int cM = 2600;
   private static final int cL = 400;
   private static final int C = 6200;
   private static final int A = 0;
   private static final int cz = 1;
   private static final int t = 2;
   private static final int cr = 3;
   private static final int S = 4;
   private static final int H = 5;
   private static final int cU = 6;
   private static final int I = SkyVertexFormat.AMBIENCE_VERTEX_SIZE;
   private static final int cF = I * 4;
   private static final float ci = (float) (Math.PI * 2);
   private static final Random G = new Random(1985171525L);
   private static final WeatherParticlePass.WeatherParticle[] cZ = new WeatherParticlePass.WeatherParticle[2600];
   private static final WeatherParticlePass.WeatherBatch[] O = new WeatherParticlePass.WeatherBatch[400];
   private static final WeatherParticlePass.WeatherCell M;
   private static final float[] cy;
   private static final float[] T;
   private static final int[] Y;
   private static final long[] f;
   private static final long[] n;
   private static final int[] B;
   private static final float[] X;
   private static class_638 q;
   private static long F;
   private static float i;
   private static float E;
   private static float U;
   private static float l;
   private static float cw;
   private static float cV;
   private static float cX;
   private static float R;
   private static float w;
   private static float N;
   private static float J;
   private static double x;
   private static double v;
   private static double c;
   private static double h;
   private static double a;
   private static double d;
   private static float p;
   private static float V;
   private static float r;
   private static int cc;
   private static int Q;
   private static int L;
   private static int cf;
   private static int b;
   private static boolean o;
   private static boolean e;
   private static ByteBuffer m;
   private static ByteBuffer g;
   private static GpuBuffer u;
   private static GpuBuffer s;
   private static String W;
   private static final Matrix4f P;
   private static boolean cC;
   private static int y;
   private static GpuBufferSlice D;
   private static GpuBuffer c3;
   private static ByteBuffer z;
   private static final Matrix4f j;
   static WeatherModule K;
   private static ModuleContext Z;
   private static int ca;
   private static int cP;
   public static int k;
   private static final long[] eb;
   private static final Long[] fb;

   private WeatherParticlePass() {
   }

   public static void capture(Matrix4fc var0, Matrix4fc var1) {
      if (!ModuleWidget.claimed("mirror-capture")) {
         P.set(var0);
         j.set(var1);
         cC = IrisCompat.inUse();
         y = 0;
         if (cC && K.enabled()) {
            SkyPipelineRouter.wantHandMask();
         }
      }
   }

   public static void renderAfterLevel() {
      long var0 = 5978613373990L;
      long var2 = var0 ^ 15389940509781L;
      int var4 = y;
      y = 0;
      if (var4 != 0 && D != null && !ModuleWidget.claimed("mirror-capture")) {
         class_276 var5 = class_310.method_1551().method_1522();
         RenderSystem.setShaderFog(D);
         w(var5, var2, var5, var4, SkyPipelineRouter.handMask());
      }
   }

   public static float flash() {
      return J;
   }

   public static int precipitationCount() {
      int var0 = 0;

      for (int var1 = 0; var1 < cc; var1++) {
         if (cZ[var1].Z) {
            var0++;
         }
      }

      return var0;
   }

   public static int impactCount() {
      int var0 = 0;

      for (WeatherParticlePass.WeatherBatch var4 : O) {
         if (var4.l > 0.0F) {
            var0++;
         }
      }

      return var0;
   }

   public static int flashCount() {
      return b;
   }

   public static String lastError() {
      return W;
   }

   public static void devFlash() {
      if (Boolean.getBoolean("purxiy.dev")) {
         N = 0.0F;
         e = true;
      }
   }

   public static void addPass(class_9909 var0, class_9960 var1, GpuBufferSlice var2) {
      long var3 = 46145478850130L;
      long var5 = var3 ^ 131583819029306L;
      int var7 = (int)((var3 ^ 131583819029306L) >>> 32);
      int var8 = (int)((var3 ^ 131583819029306L) << 32 >>> 56);
      int var9 = (int)(var5 << 40 >>> 40);
      long var10 = var3 ^ 51466803430603L;
      long var12 = var3 ^ 15207070101373L;
      var5 = var3 ^ 98682379879644L;
      int var14 = (int)((var3 ^ 98682379879644L) >>> 32);
      int var15 = (int)((var3 ^ 98682379879644L) << 32 >>> 48);
      int var16 = (int)(var5 << 48 >>> 48);
      long var17 = var3 ^ 56967827404699L;
      long var19 = var3 ^ 21709588908001L;
      long var21 = var3 ^ 20843620254201L;
      long var23 = var3 ^ 62483489927292L;
      long var25 = var3 ^ 119891459806722L;
      if (!ModuleWidget.claimed("mirror-capture")) {
         class_310 var27 = class_310.method_1551();
         class_638 var28 = var27.field_1687;
         long var29 = System.nanoTime();
         float var31 = F == 0L ? 0.0F : Math.min(0.075F, (float)(var29 - F) / 1.0E9F);
         F = var29;
         if (var28 != q) {
            Z(var19);
            q = var28;
         }

         if (var28 != null) {
            int var47;
            if (var27.method_1493()) {
               var31 = 0.0F;
            }

            WeatherModule var32 = K;
            class_4184 var33 = var27.field_1773.method_19418();
            class_243 var34 = var33.method_71156();
            if (o && var34.method_1025(new class_243(x, v, c)) > cw * cw) {
               Z(var19);
            }

            x = var34.field_1352;
            v = var34.field_1351;
            c = var34.field_1350;
            Vector3fc var35 = var33.method_19335();
            p = var35.x();
            V = var35.y();
            r = var35.z();
            i += var31;
            float var36 = E;
            float var37 = var32.enabled() ? 1.0F : 0.0F;
            float var38 = var31 / 1.2F;
            E = p(var36, var7, (byte)var8, var37, var9, var38);
            if (E == 0.0F && !var32.enabled()) {
               d(var21);
            } else {
               float var40 = o ? 1.0F - (float)Math.exp(-var31 * 3.0F) : 1.0F;
               l = l + (var32.J.floatValue() - l) * var40;
               cw = cw + (var32.o.floatValue() - cw) * var40;
               cV = cV + (var32.B.floatValue() - cV) * var40;
               cX = cX + (var32.O.floatValue() - cX) * var40;
               var36 = U;
               float var41 = var33.method_19334() == class_5636.field_27888 ? 1.0F : 0.0F;
               var38 = var31 * 3.0F;
               U = p(var36, var7, (byte)var8, var41, var9, var38);
               R = var28.method_8597().comp_642()
                  ? 0.3F + 0.7F * class_3532.method_15363((Float)var33.method_75756().method_75689(class_12206.field_64346, var33.method_55437()), 0.0F, 1.0F)
                  : 0.55F;
               int var42 = var32.t.is("Snow") ? 1 : (var32.t.is("Fire") ? 2 : 0);

               for (int var43 = 0; var43 < 3; var43++) {
                  cy[var43] = cy[var43] + ((var43 == var42 ? 1 : 0) - cy[var43]) * var40;
               }

               int var52 = Math.clamp((long)Math.round(cw * cX * (var42 == 0 ? 34 : (var42 == 1 ? 29 : 17))), 40, 2600);

               for (int var44 = cc; var44 < var52; var44++) {
                  B(cZ[var44], var28, var42, true, var12);
               }

               cc = var52;
               h(var28, var42, var17, var31);
               H(var14, var28, var32, (short)var15, (char)var16, var42, var31);
               o = true;
               L = 0;
               if (U > 0.001F) {
                  s(var25);
               }

               if (L != 0) {
                  try {
                     if (!RenderSystem.getDevice().precompilePipeline(WeatherParticlePipelines.c).isValid()) {
                        z(var23, new IllegalStateException("Weather particle shader compilation failed"));
                        return;
                     }

                     D(var10);
                     if (cC) {
                        y = L;
                        D = var2;
                        return;
                     }

                     class_9916 var53 = var0.method_61911("purxiy_weather_particles");
                     class_9925 var45;
                     if (var1.field_53094 != null) {
                        var1.field_53094 = var53.method_61933(var1.field_53094);
                        var53.method_61928(var1.field_53091);
                        var45 = var1.field_53094;
                     } else {
                        var1.field_53091 = var53.method_61933(var1.field_53091);
                        var45 = var1.field_53091;
                     }

                     class_9925 var46 = var1.field_53091;
                     int var47x = L;
                     var53.method_61929(() -> {
                        long var4 = 9411327144051L;
                        RenderSystem.setShaderFog(var2);
                        w((class_276)var45.get(), var4, (class_276)var46.get(), var47x, null);
                     });
                  } catch (Throwable var471) {
                     z(var23, var471);
                  }
               }
            }
         }
      }
   }

   private static void h(class_638 var0, int var1, long var2, float var4) {
      long var5 = 39774498658607L;
      long var7 = 69904412482203L;
      short var9 = 16275;
      char var10 = '\ue3ac';
      int var11 = (int)(var7 << 48 >>> 48);
      var7 = 33947348010890L;
      short var12 = 7903;
      int var13 = 16490119;
      int var14 = (int)(var7 << 56 >>> 56);
      WeatherModule.ensureSourceClassInitialized();
      float var15 = class_3532.method_15362(i * 0.045F + 0.4F) * cV;
      float var16 = class_3532.method_15374(i * 0.036F + 1.3F) * cV;

      for (int var17 = 0; var17 < cc; var17++) {
         WeatherParticlePass.WeatherParticle var18 = cZ[var17];
         long var19 = 28827011137992L;
         int var21 = var19 < 0L ? -1 : 1;
         long var22 = 28827011137993L;
         int var24 = var22 < 0L ? -1 : 1;
         if (!var18.Z) {
            var18.q -= var4;
            long var25 = 28827011137992L;
            int var25x = var25 < 0L ? -1 : 1;
            long var28 = 28827011137992L;
            int var59 = var28 < 0L ? -1 : 1;
            if (var18.q <= 0.0F) {
               float var30;
               int var31 = (var30 = var4 - 0.0F) == 0.0F ? 0 : (var30 < 0.0F ? -1 : 1);
               long var32 = 28827011137993L;
               int var31x = var32 < 0L ? -1 : 1;
               if (var31 > 0) {
                  B(var18, var0, var1, true, var5);
               }
            }
         } else {
            long var54 = 28827011137993L;
            int var54x = var54 < 0L ? -1 : 1;
            if (var18.W != var1) {
               float var55;
               int var29 = (var55 = cy[var18.W] - 0.06F) == 0.0F ? 0 : (var55 < 0.0F ? -1 : 1);
               long var57 = 28827011137993L;
               int var64 = var57 < 0L ? -1 : 1;
               if (var29 < 0) {
                  long var61 = 28827011137992L;
                  int var72 = var61 < 0L ? -1 : 1;
                  B(var18, var0, var1, true, var5);
                  continue;
               }
            }

            var18.q += var4;
            double var56 = var18.R;
            long var58 = 28827011137992L;
            int var65 = var58 < 0L ? -1 : 1;
            if (var18.W == 1) {
               long var33 = 28827011137992L;
               int var70 = var33 < 0L ? -1 : 1;
               var18.n = var15 * 1.2F + class_3532.method_15374(i * 1.6F + var18.C * 24.0F) * 0.38F;
               var18.E = var16 * 1.2F + class_3532.method_15362(i * 1.2F + var18.C * 32.0F) * 0.32F;
               var18.J = -(0.8F + var18.C * 0.85F);
            } else {
               var18.n = var15 * (var18.W == 0 ? 2.1F : 1.3F);
               var18.E = var16 * (var18.W == 0 ? 2.1F : 1.3F);
               var18.J = var18.W == 0 ? -(12.0F + var18.C * 7.0F) : -(6.0F + var18.C * 6.0F);
            }

            var18.t = var18.t + var18.n * var4;
            var18.R = var18.R + var18.J * var4;
            var18.N = var18.N + var18.E * var4;
            double var60 = var18.t - x;
            double var35 = var18.N - c;
            long var37 = 28827011137992L;
            int var35x = var37 < 0L ? -1 : 1;
            if (!(var60 * var60 + var35 * var35 > cw * cw) && !(var18.R < v - 11.0) && !(var18.R > v + 17.0)) {
               float var42x = h(var12, var13, var18, (byte)var14, var0);
               if (!Float.isFinite(var42x)) {
                  long var43 = 28827011137992L;
                  int var38 = var43 < 0L ? -1 : 1;
                  var18.Z = false;
                  var18.q = 0.4F;
               } else if (var18.R <= var42x) {
                  if (var56 >= var42x - 2.0F && var42x >= v - 11.0 && var42x <= v + 16.0) {
                     float var62 = M.exact(var0, var18.t, var42x, var18.N);
                     if (var18.R > var62) {
                        continue;
                     }

                     double var44;
                     int var46 = (var44 = var56 - var62) == 0.0 ? 0 : (var44 < 0.0 ? -1 : 1);
                     long var47 = 28827011137992L;
                     if (var46 >= 0) {
                        l(var9, (short)var10, (short)var11, var18, var62);
                     }
                  }

                  B(var18, var0, var1, false, var5);
               }
            } else {
               B(var18, var0, var1, false, var5);
            }
         }
      }

      for (WeatherParticlePass.WeatherBatch var20 : O) {
         long var21 = 28827011137993L;
         int var51 = var21 < 0L ? -1 : 1;
         long var24 = 28827011137993L;
         int var57 = var24 < 0L ? -1 : 1;
         if (var20.l > 0.0F) {
            var20.P += var4;
            long var27 = 28827011137993L;
            int var62x = var27 < 0L ? -1 : 1;
            long var59 = 28827011137992L;
            int var69 = var59 < 0L ? -1 : 1;
            if (var20.P >= var20.l) {
               var20.l = 0.0F;
            }
         }
      }
   }

   private static void B(WeatherParticlePass.WeatherParticle var0, class_638 var1, int var2, boolean var3, long var4) {
      long var6 = 33947348010890L;
      short var8 = 7903;
      int var9 = 16490119;
      int var10 = (int)(var6 << 56 >>> 56);
      var0.W = var2;
      WeatherModule.ensureSourceClassInitialized();
      var0.C = G.nextFloat();
      float var11 = G.nextFloat() * (float) (Math.PI * 2);
      float var12 = 1.3F + G.nextFloat() * Math.max(1.0F, cw - 1.3F);
      var0.t = x + class_3532.method_15362(var11) * var12;
      long var13 = 39774498658606L;
      int var16 = var13 < 0L ? -1 : 1;
      var0.N = c + class_3532.method_15374(var11) * var12;
      var0.R = v + (var3 ? -10.0F + G.nextFloat() * 25.0F : 12.0F + G.nextFloat() * 3.0F);
      float var16x = h(var8, var9, var0, (byte)var10, var1);
      boolean var17 = Float.isFinite(var16x);
      long var18 = 39774498658606L;
      int var20 = var18 < 0L ? -1 : 1;
      var0.Z = var17 && var0.R > var16x + 0.04;
      long var20x = 39774498658607L;
      int var22 = var20x < 0L ? -1 : 1;
      var0.q = var0.Z ? (var3 && !o ? 0.5F : 0.0F) : 0.18F + G.nextFloat() * 0.4F;
      long var22x = 39774498658606L;
      int var25 = var22x < 0L ? -1 : 1;
      var0.n = 0.0F;
      var0.E = 0.0F;
      var0.J = var2 == 1 ? -1.2F : -14.0F;
      long var25x = 39774498658607L;
      var0.b = var2 == 0 ? 0.008F + var0.C * 0.008F : (var2 == 1 ? 0.035F + var0.C * 0.05F : 0.018F + var0.C * 0.024F);
   }

   private static void l(int var0, short var1, short var2, WeatherParticlePass.WeatherParticle var3, float var4) {
      WeatherModule.ensureSourceClassInitialized();
      double var5 = var3.t - x;
      double var7 = var3.N - c;
      if (!(var5 * var5 + var7 * var7 > Math.min(cw * cw, 529.0F))) {
         float var9;
         WeatherParticlePass.WeatherBatch var10;
         label25: {
            WeatherParticlePass.WeatherBatch var11 = O[Q++ % 400];
            var11.s = var3.t;
            var11.I = var4 + 0.018;
            var11.n = var3.N;
            var10 = var11;
            int var12 = var3.W;
            if (var0 > 0) {
               var11.O = var3.W;
               var11.t = var3.C;
               var11.P = 0.0F;
               var10 = var11;
               if (var11.O == 1) {
                  var9 = 1.8F;
                  break label25;
               }

               var12 = var11.O;
            }

            var9 = var12 == 2 ? 0.95F : 0.65F;
         }

         var10.l = var9;
      }
   }

   private static void s(long var0) {
      long var2 = 4185427206574L;
      long var4 = 104550655659645L;
      WeatherModule.ensureSourceClassInitialized();
      float var6 = D(E) * U;

      for (int var7 = 0; var7 < cc; var7++) {
         WeatherParticlePass.WeatherParticle var8 = cZ[var7];
         if (var8.Z) {
            float var9 = (float)(var8.t - x);
            float var10 = (float)(var8.R - v);
            float var11 = (float)(var8.N - c);
            float var12 = (float)Math.sqrt(var9 * var9 + var10 * var10 + var11 * var11);
            float var13 = 1.0F - M(cw * 0.76F, cw, (float)Math.sqrt(var9 * var9 + var11 * var11));
            float var14 = var6 * cy[var8.W] * var13;
            long var15 = 75808102178896L;
            int var46 = var15 < 0L ? -1 : 1;
            float var18 = var14 * M(0.65F, 1.6F, var12) * M(0.0F, var8.W == 0 ? 0.12F : 0.35F, var8.q);
            if (!(var18 < 0.002F)) {
               if (var8.W == 0) {
                  Z(
                     var9,
                     var10,
                     var11,
                     var8.b * l,
                     0,
                     (0.12F + var8.C * 0.17F) * l,
                     var8.C,
                     var4,
                     0.0F,
                     -var8.n,
                     -var8.J,
                     -var8.E,
                     0.0F,
                     0.64F,
                     0.79F,
                     0.91F,
                     var18 * 0.52F,
                     0.36F,
                     0.62F,
                     0.91F,
                     var18 * 0.07F
                  );
                  long var19 = 75808102178895L;
                  int var49 = var19 < 0L ? -1 : 1;
               } else {
                  long var47 = 75808102178895L;
                  int var51 = var47 < 0L ? -1 : 1;
                  if (var8.W == 1) {
                     Z(
                        var9,
                        var10,
                        var11,
                        var8.b * l,
                        1,
                        i * (0.35F + var8.C * 0.6F) + var8.C * (float) (Math.PI * 2),
                        var8.C,
                        var4,
                        0.0F,
                        0.0F,
                        1.0F,
                        0.0F,
                        0.0F,
                        0.9F,
                        0.95F,
                        1.0F,
                        var18 * 0.87F,
                        0.5F,
                        0.69F,
                        1.0F,
                        var18 * 0.055F
                     );
                     long var21 = 75808102178896L;
                     int var50 = var21 < 0L ? -1 : 1;
                  } else {
                     Z(
                        var9,
                        var10,
                        var11,
                        var8.b * l,
                        2,
                        (0.18F + var8.C * 0.24F) * l,
                        var8.C,
                        var4,
                        0.0F,
                        -var8.n,
                        -var8.J,
                        -var8.E,
                        0.0F,
                        1.0F,
                        0.78F,
                        0.36F,
                        var18 * 0.85F,
                        1.0F,
                        0.22F,
                        0.035F,
                        var18 * 0.7F
                     );
                  }
               }
            }
         }
      }

      for (WeatherParticlePass.WeatherBatch var33 : O) {
         if (!(var33.l <= 0.0F)) {
            float var35 = (float)(var33.s - x);
            float var37 = (float)(var33.I - v);
            float var39 = (float)(var33.n - c);
            float var41 = var33.P / var33.l;
            float var43 = var6 * cy[var33.O] * (1.0F - M(cw * 0.72F, cw, (float)Math.sqrt(var35 * var35 + var39 * var39)));
            long var16 = 75808102178896L;
            int var491 = var16 < 0L ? -1 : 1;
            long var48 = 75808102178896L;
            int var53 = var48 < 0L ? -1 : 1;
            if (var33.O == 0) {
               Z(
                  var35,
                  var37,
                  var39,
                  (0.04F + var41 * 0.34F) * l,
                  3,
                  var41,
                  var33.t,
                  var4,
                  0.0F,
                  0.0F,
                  1.0F,
                  0.0F,
                  0.0F,
                  0.65F,
                  0.83F,
                  0.94F,
                  var43 * (1.0F - var41) * 0.38F,
                  0.5F,
                  0.74F,
                  1.0F,
                  var43 * (1.0F - var41) * 0.045F
               );
               long var22 = 75808102178896L;
               int var56 = var22 < 0L ? -1 : 1;
               long var24 = 75808102178896L;
               int var24x = var24 < 0L ? -1 : 1;
               if (var33.P < 0.35F) {
                  z(var33, var2, var35, var37, var39, var43, false);
               }
            } else {
               long var49x = 75808102178896L;
               int var58 = var49x < 0L ? -1 : 1;
               if (var33.O == 1) {
                  float var50 = (0.04F + var33.t * 0.04F) * l;
                  float var25 = var41 * 0.55F;
                  long var26 = 75808102178895L;
                  int var26x = var26 < 0L ? -1 : 1;
                  Z(
                     var35,
                     var37,
                     var39,
                     var50 * (1.0F - var25),
                     4,
                     var41,
                     var33.t,
                     var4,
                     0.0F,
                     0.0F,
                     1.0F,
                     0.0F,
                     0.0F,
                     0.93F,
                     0.97F,
                     1.0F,
                     var43 * (1.0F - var41) * 0.78F,
                     0.6F,
                     0.78F,
                     1.0F,
                     0.0F
                  );
               } else {
                  Z(
                     var35,
                     var37,
                     var39,
                     (0.1F + var41 * 0.46F) * l,
                     3,
                     var41,
                     var33.t,
                     var4,
                     1.0F,
                     0.0F,
                     1.0F,
                     0.0F,
                     0.0F,
                     1.0F,
                     0.63F,
                     0.18F,
                     var43 * (1.0F - var41) * 0.3F,
                     1.0F,
                     0.19F,
                     0.018F,
                     var43 * (1.0F - var41) * 0.7F
                  );
                  z(var33, var2, var35, var37, var39, var43, true);
               }
            }
         }
      }

      long var30 = 75808102178896L;
      int var31 = var30 < 0L ? -1 : 1;
      if (w < 0.6F && J > 0.001F) {
         for (int var34 = 0; var34 < cf; var34++) {
            int var36 = var34 * 7;
            float var38 = X[var36];
            float var40 = X[var36 + 1];
            float var42 = X[var36 + 2];
            float var44 = X[var36 + 3] - var38;
            float var45 = X[var36 + 4] - var40;
            float var17 = X[var36 + 5] - var42;
            float var46 = (float)Math.sqrt(var44 * var44 + var45 * var45 + var17 * var17);
            Z(
               (float)(h - x) + var38 + var44 * 0.5F,
               (float)(a - v) + var40 + var45 * 0.5F,
               (float)(d - c) + var42 + var17 * 0.5F,
               X[var36 + 6],
               6,
               var46 * 0.53F,
               var34 * 0.03F,
               var4,
               0.0F,
               var44,
               var45,
               var17,
               0.0F,
               0.88F,
               0.94F,
               1.0F,
               var6 * J,
               0.37F,
               0.58F,
               1.0F,
               var6 * J * 0.88F
            );
         }
      }
   }

   private static void z(WeatherParticlePass.WeatherBatch var0, long var1, float var3, float var4, float var5, float var6, boolean var7) {
      long var8 = 104550655659645L;
      WeatherModule.ensureSourceClassInitialized();
      int var10 = var7 ? 5 : 3;

      for (int var11 = 0; var11 < var10; var11++) {
         float var12 = var0.P;
         float var13 = var0.t * 28.0F + var11 * (float) (Math.PI * 2) / var10;
         float var14 = (var7 ? 0.7F : 0.32F) + var0.t * 0.35F;
         float var15 = (var7 ? 2.1F : 1.2F) - (var7 ? 4.2F : 7.2F) * var12;
         float var16 = (var7 ? 2.1F : 1.2F) * var12 - (var7 ? 2.1F : 3.6F) * var12 * var12;
         if (!(var16 <= 0.0F)) {
            float var17 = var6 * (1.0F - var12 / (var7 ? 0.95F : 0.35F));
            float var18 = var3 + class_3532.method_15362(var13) * var14 * var12;
            float var19 = var4 + var16;
            float var20 = class_3532.method_15374(var13);
            long var21 = 4185427206573L;
            int var24 = var21 < 0L ? -1 : 1;
            float var24x = var5 + var20 * var14 * var12;
            var20 = (var7 ? 0.018F : 0.01F) * l;
            int var23x = var7 ? 5 : 0;
            float var25 = var7 ? 0.027F : 0.018F;
            float var26 = var0.t;
            float var27 = class_3532.method_15362(var13) * var14;
            float var28 = class_3532.method_15374(var13) * var14;
            float var29 = var7 ? 1.0F : 0.73F;
            float var30 = var7 ? 0.8F : 0.87F;
            float var31 = var7 ? 0.36F : 1.0F;
            long var32 = 4185427206573L;
            int var35 = var32 < 0L ? -1 : 1;
            float var35x = var17 * 0.7F;
            float var36 = var7 ? 1.0F : 0.5F;
            float var37 = var7 ? 0.23F : 0.72F;
            float var38 = var7 ? 0.035F : 1.0F;
            float var39 = var17 * (var7 ? 0.6F : 0.02F);
            float var47 = 0.0F;
            float var51 = 0.0F;
            Z(
               var18,
               var19,
               var24x,
               var20,
               var23x,
               var25,
               var26,
               var8,
               var51,
               var27,
               var15,
               var28,
               var47,
               var29,
               var30,
               var31,
               var35x,
               var36,
               var37,
               var38,
               var39
            );
         }
      }
   }

   private static void H(int var0, class_638 var1, WeatherModule var2, short var3, char var4, int var5, float var6) {
      long var7 = 123387379528334L;
      long var9 = var7 ^ 32252689687790L;
      int var11 = (int)((var7 ^ 32252689687790L) >>> 56);
      int var12 = (int)((var7 ^ 32252689687790L) << 8 >>> 32);
      int var13 = (int)(var9 << 40 >>> 40);
      WeatherModule.ensureSourceClassInitialized();
      w += var6;
      boolean var14 = var2.enabled() && var5 == 0 && var2.j.enabled() && var2.f.floatValue() > 0.0F;
      if (!var14) {
         J = 0.0F;
         w = 10.0F;
         e = false;
      } else {
         N -= var6;
         if (N <= 0.0F) {
            short var15 = 23789;
            if (E > 0.7F) {
               var15 = 28728;
               if (U > 0.5F) {
                  float var16 = (float)Math.atan2(r, p) + (e ? 0.16F : (G.nextFloat() - 0.5F) * 1.6F);
                  float var17 = e ? 30.0F : 18.0F + G.nextFloat() * 25.0F;
                  e = false;
                  h = x + class_3532.method_15362(var16) * var17;
                  d = c + class_3532.method_15374(var16) * var17;
                  float var18 = M.height(var1, h, d, i);
                  boolean var19 = Float.isFinite(var18);
                  if (var19) {
                     a = var18;
                     H((byte)var11, var12, var13);
                     w = 0.0F;
                     b++;
                     WeatherUpdater.thunder(Math.sqrt((h - x) * (h - x) + (a - v) * (a - v) + (d - c) * (d - c)));
                  }

                  N = (5.5F + G.nextFloat() * 9.5F) / (0.65F + cX * 0.35F);
               }
            }
         }

         J = (float)(Math.exp(-w * 13.0F) + 0.6 * Math.exp(-Math.pow((w - 0.13) / 0.03, 2.0)) + 0.28 * Math.exp(-Math.pow((w - 0.255) / 0.035, 2.0)))
            * D(E)
            * var2.f.floatValue();
         if (w >= 0.6F) {
            J = 0.0F;
         }
      }
   }

   private static void H(byte var0, int var1, int var2) {
      long var3 = 120315840665184L;
      int var5 = (int)((var3 ^ 67836491581375L) >>> 32);
      long var6 = (var3 ^ 67836491581375L) << 32 >>> 32;
      WeatherModule.ensureSourceClassInitialized();
      cf = 0;
      float var8 = Math.max(32.0F, (float)(v - a) + 34.0F + G.nextFloat() * 16.0F);
      float var9 = 0.0F;
      float var10 = 0.0F;
      float var11 = 0.0F;

      for (int var12 = 0; var12 < 22; var12++) {
         float var13 = var8 * (var12 + 1) / 22.0F;
         float var14 = var9 + (G.nextFloat() - 0.5F) * 3.0F;
         float var15 = var11 + (G.nextFloat() - 0.5F) * 3.0F;
         float var16 = 0.073F + (1.0F - var12 / 22.0F) * 0.047F;
         float var17 = (var9 + var14) * 0.5F + (G.nextFloat() - 0.5F) * 0.42F;
         float var18 = (var10 + var13) * 0.5F;
         float var19 = (var11 + var15) * 0.5F + (G.nextFloat() - 0.5F) * 0.42F;
         A(var5, var9, var10, var11, var17, var18, var19, var6, var16);
         A(var5, var17, var18, var19, var14, var13, var15, var6, var16);
         if (var12 > 4 && var12 < 19 && G.nextFloat() < 0.34F) {
            float var27 = var14;
            float var28 = var13;
            float var29 = var15;
            float var30 = G.nextFloat() * (float) (Math.PI * 2);
            int var31 = 3 + G.nextInt(4);

            for (int var32 = 0; var32 < var31; var32++) {
               float var33 = var27 + class_3532.method_15362(var30) * (1.2F + G.nextFloat() * 1.3F);
               float var34 = var28 - 1.2F - G.nextFloat() * 1.5F;
               float var35 = var29 + class_3532.method_15374(var30) * (1.2F + G.nextFloat() * 1.3F);
               A(var5, var27, var28, var29, var33, var34, var35, var6, 0.045F * (1.0F - (float)var32 / var31) + 0.014F);
               var27 = var33;
               var28 = var34;
               var29 = var35;
               var30 += (G.nextFloat() - 0.5F) * 0.7F;
            }
         }

         var9 = var14;
         var10 = var13;
         var11 = var15;
      }
   }

   private static void A(int var0, float var1, float var2, float var3, float var4, float var5, float var6, long var7, float var9) {
      WeatherModule.ensureSourceClassInitialized();
      int var10 = X.length / 7;
      if (cf != var10) {
         int var11 = cf++ * 7;
         X[var11] = var1;
         X[var11 + 1] = var2;
         X[var11 + 2] = var3;
         X[var11 + 3] = var4;
         X[var11 + 4] = var5;
         X[var11 + 5] = var6;
         X[var11 + 6] = var9;
      }
   }

   private static void Z(
      float var0,
      float var1,
      float var2,
      float var3,
      int var4,
      float var5,
      float var6,
      long var7,
      float var9,
      float var10,
      float var11,
      float var12,
      float var13,
      float var14,
      float var15,
      float var16,
      float var17,
      float var18,
      float var19,
      float var20,
      float var21
   ) {
      long var22 = 13627488078143L;
      WeatherModule.ensureSourceClassInitialized();
      if (L != 6200) {
         if (var17 < 0.002F) {
            float var24;
            int var25 = (var24 = var21 - 0.002F) == 0.0F ? 0 : (var24 < 0.0F ? -1 : 1);
            long var26 = 104550655659644L;
            int var29 = var26 < 0L ? -1 : 1;
            if (var25 < 0) {
               return;
            }
         }

         label59: {
            long var33 = 104550655659644L;
            int var341 = var33 < 0L ? -1 : 1;
            if (var4 != 0) {
               long var27 = 104550655659644L;
               int var37 = var27 < 0L ? -1 : 1;
               if (var4 != 1) {
                  long var29 = 104550655659644L;
                  int var31 = var29 < 0L ? -1 : 1;
                  if (var4 != 4) {
                     long var31x = 104550655659644L;
                     int var32 = var31x < 0L ? -1 : 1;
                     if (var4 != 3 || !(var9 < 0.5F)) {
                        break label59;
                     }
                  }
               }
            }

            var14 *= R;
            var15 *= R;
            var16 *= R;
            var18 *= R;
            var19 *= R;
            var20 *= R;
         }

         float var34 = var0 * p + var1 * V + var2 * r;
         if (!(var34 < -Math.max(var5, var3) - 0.5F)) {
            int var35 = L * 12;
            T[var35] = var0;
            T[var35 + 1] = var1;
            T[var35 + 2] = var2;
            T[var35 + 3] = var3;
            T[var35 + 4] = var4;
            T[var35 + 5] = var5;
            T[var35 + 6] = var6;
            T[var35 + 7] = var9;
            T[var35 + 8] = var10;
            T[var35 + 9] = var11;
            T[var35 + 10] = var12;
            T[var35 + 11] = var13;
            Y[L * 2] = a(var22, var14, var15, var16, var17);
            Y[L * 2 + 1] = a(var22, var18, var19, var20, var21);
            f[L] = (long)Float.floatToRawIntBits(Math.max(0.0F, var34)) << 32 | L;
            L++;
         }
      }
   }

   private static int a(long var0, float var2, float var3, float var4, float var5) {
      return v(var2) | v(var3) << 8 | v(var4) << 16 | v(var5) << 24;
   }

   private static int v(float var0) {
      return (int)(Math.min(1.0F, Math.max(0.0F, var0)) * 255.0F + 0.5F);
   }

   private static void D(long var0) {
      long var2;
      int var4;
      label90: {
         var2 = 70518071022092L;
         WeatherModule.ensureSourceClassInitialized();
         var4 = L * cF;
         if (m != null) {
            int var5 = m.capacity();
            long var6 = 7933212930713L;
            int var27 = var6 < 0L ? -1 : 1;
            long var9 = 7933212930712L;
            int var10 = var9 < 0L ? -1 : 1;
            if (var5 >= var4) {
               break label90;
            }
         }

         int var23 = Math.min(6200 * cF, Math.max(var4, m == null ? 1024 * cF : m.capacity() * 2));
         long var25 = 7933212930712L;
         int var271 = var25 < 0L ? -1 : 1;
         m = m == null ? MemoryUtil.memAlloc(var23) : MemoryUtil.memRealloc(m, var23);
      }

      long[] var24 = m(L, var2);
      long var26 = MemoryUtil.memAddress(m);

      for (int var8 = 0; var8 < L; var8++) {
         int var28 = (int)var24[L - 1 - var8];
         long var10 = var26 + (long)var8 * cF;

         for (int var12 = 0; var12 < 12; var12++) {
            long var13 = 7933212930713L;
            int var15 = var13 < 0L ? -1 : 1;
            MemoryUtil.memPutFloat(var10 + var12 * 4L, T[var28 * 12 + var12]);
         }

         long var29 = var10 + 48L;
         long var14 = 7933212930712L;
         int var30 = var14 < 0L ? -1 : 1;
         MemoryUtil.memPutInt(var29, Y[var28 * 2]);
         MemoryUtil.memPutInt(var10 + 52L, Y[var28 * 2 + 1]);

         for (int var17 = 1; var17 < 4; var17++) {
            long var18 = var10 + (long)I * var17;
            long var20 = 7933212930713L;
            int var21 = var20 < 0L ? -1 : 1;
            MemoryUtil.memCopy(var10, var18, I);
         }

         long var30x = 7933212930713L;
         int var181 = var30x < 0L ? -1 : 1;
      }

      long var27 = 7933212930713L;
      int var31 = var27 < 0L ? -1 : 1;
      m.clear().limit(var4);
   }

   private static long[] m(int var0, long var1) {
      long[] var3 = f;
      WeatherModule.ensureSourceClassInitialized();
      long[] var4 = n;

      for (byte var5 = 40; var5 < 64; var5 = (byte)(var5 + 8)) {
         Arrays.fill(B, 0);

         for (int var6 = 0; var6 < var0; var6++) {
            int var7 = (int)(var3[var6] >>> var5) & 0xFF;
            int var8 = B[(int)(var3[var6] >>> var5) & 0xFF] + 1;
            long var9 = 70518071022091L;
            int var21 = var9 < 0L ? -1 : 1;
            B[var7] = var8;
         }

         int var17 = 0;
         long var19 = 70518071022092L;
         int var211 = var19 < 0L ? -1 : 1;

         for (int var10 = 0; var10 < 256; var10++) {
            int var11 = B[var10];
            long var12 = 70518071022092L;
            int var14 = var12 < 0L ? -1 : 1;
            B[var10] = var17;
            var17 += var11;
         }

         long var20 = 70518071022091L;
         int var21x = var20 < 0L ? -1 : 1;
         var21x = 0;

         while (var21x < var0) {
            long var13 = var3[var21x];
            var4[B[(int)(var13 >>> var5) & 0xFF]++] = var13;
            var21x++;
            long var15 = 70518071022092L;
            int var16 = var15 < 0L ? -1 : 1;
         }

         long[] var22 = var3;
         var3 = var4;
         var4 = var22;
         long var14 = 70518071022091L;
         int var151 = var14 < 0L ? -1 : 1;
      }

      long var18 = 70518071022091L;
      int var201 = var18 < 0L ? -1 : 1;
      return var3;
   }

   private static void w(class_276 var0, long var1, class_276 var3, int var4, GpuTextureView var5) {
      long var6 = 18880792053294L;
      WeatherModule.ensureSourceClassInitialized();

      try {
         GpuTextureView var8 = var0.method_71639();
         GpuTextureView var9 = var3.method_71640();
         if (var8 == null || var9 == null) {
            return;
         }

         GpuDevice var10 = RenderSystem.getDevice();
         RenderPipeline var11 = var5 != null ? WeatherParticlePipelines.r : WeatherParticlePipelines.c;
         if (!var10.precompilePipeline(var11).isValid()) {
            return;
         }

         CommandEncoder var12 = var10.createCommandEncoder();
         int var13 = var4 * cF;
         long var14 = 9411327144051L;
         int var35 = var14 < 0L ? -1 : 1;
         if (s == null || s.isClosed()) {
            GpuBuffer var17 = var10.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX weather uniforms"), 136, 48L);
            long var18 = 9411327144051L;
            int var20 = var18 < 0L ? -1 : 1;
            s = var17;
            long var21 = 9411327144051L;
            int var21x = var21 < 0L ? -1 : 1;
            if (g == null) {
               g = MemoryUtil.memAlloc(48);
            }
         }

         label165: {
            if (u != null && !u.isClosed()) {
               long var33 = 9411327144051L;
               int var38 = var33 < 0L ? -1 : 1;
               if (u.size() >= var13) {
                  break label165;
               }
            }

            if (u != null) {
               u.close();
            }

            long var34 = 65536L;

            while (var34 < var13) {
               var34 <<= 1;
               long var19 = 9411327144051L;
               int var39 = var19 < 0L ? -1 : 1;
            }

            long var37 = 9411327144050L;
            int var40 = var37 < 0L ? -1 : 1;
            u = var10.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX weather vertices"), 40, Math.min(6200L * cF, var34));
         }

         var12.writeToBuffer(u.slice(0L, var13), m);
         var12.writeToBuffer(
            s.slice(),
            Std140Builder.intoBuffer(g.clear())
               .putVec4(0.05F, class_310.method_1551().field_1773.method_32796(), 0.14F, 0.0F)
               .putVec4(var8.getWidth(0), var8.getHeight(0), i, J)
               .putVec4(0.0F, 0.0F, 0.0F, 0.0F)
               .get()
         );
         GpuBufferSlice var35x = RenderSystem.getDynamicUniforms()
            .method_71106(cC ? P : RenderSystem.getModelViewMatrix(), new Vector4f(1.0F), new Vector3f(), new Matrix4f());
         if (cC) {
            if (c3 == null || c3.isClosed()) {
               c3 = var10.createBuffer(ClientServices.label(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX weather projection"), 136, 64L);
               if (z == null) {
                  z = MemoryUtil.memAlloc(64);
               }
            }

            var12.writeToBuffer(c3.slice(), Std140Builder.intoBuffer(z.clear()).putMat4f(j).get());
         }

         class_5590 var36 = RenderSystem.getSequentialBuffer(class_5596.field_27382);
         GpuBuffer var38 = var36.method_68274(var4 * 6);
         RenderPass var20 = var12.createRenderPass(() -> "SourceByDevFFnightmare DeObfDeCompByGuideX custom weather", var8, OptionalInt.empty());

         try {
            long var39 = 9411327144051L;
            int var24 = var39 < 0L ? -1 : 1;
            var20.setPipeline(var11);
            long var24x = 9411327144050L;
            RenderSystem.bindDefaultUniforms(var20);
            if (cC) {
               var20.setUniform("Projection", c3);
               var20.setUniform("Fog", D);
            }

            if (var5 != null) {
               var20.bindTexture("HandMaskSampler", var5, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
            }

            var20.setUniform("DynamicTransforms", var35x);
            long var27x = 9411327144051L;
            int var26 = var27x < 0L ? -1 : 1;
            var20.setUniform("PurxiyWeatherParticles", s);
            var20.bindTexture("DepthSampler", var9, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
            var20.setVertexBuffer(0, u);
            var20.setIndexBuffer(var38, var36.method_31924());
            var20.drawIndexed(0, 0, var4 * 6, 1);
            metric("drawn");
         } catch (Throwable var28) {
            if (var20 != null) {
               try {
                  var20.close();
               } catch (Throwable var271) {
                  var28.addSuppressed(var271);
               }
            }

            throw var28;
         }

         if (var20 != null) {
            var20.close();
         }
      } catch (Throwable var29) {
         z(var6, var29);
      }
   }

   private static void z(long var0, Throwable var2) {
      WeatherModule.ensureSourceClassInitialized();
      long var3 = 18880792053293L;
      int var5 = var3 < 0L ? -1 : 1;
      if (!var2.toString().equals(W)) {
         W = var2.toString();
         ClientServices.LOGGER.error("Custom weather rendering failed", var2);
      }
   }

   private static void d(long var0) {
      cc = 0;
      o = false;
      J = 0.0F;
      WeatherModule.ensureSourceClassInitialized();
      w = 10.0F;

      for (WeatherParticlePass.WeatherBatch var5 : O) {
         var5.l = 0.0F;
      }
   }

   private static void Z(long var0) {
      long var2 = 64927495305131L;
      WeatherModule.ensureSourceClassInitialized();
      d(var2);
      M.clear();
      Arrays.fill(cy, 0.0F);

      for (WeatherParticlePass.WeatherParticle var7 : cZ) {
         long var8 = 64075485295027L;
         int var10 = var8 < 0L ? -1 : 1;
         var7.D = 0.0F;
      }

      E = 0.0F;
      U = 1.0F;
      long var11 = 64075485295026L;
      int var12 = var11 < 0L ? -1 : 1;
      i = 0.0F;
      N = 3.5F;
      cf = 0;
      b = 0;
      e = false;
   }

   public static void close() {
      long var0 = 91830052637710L;
      long var2 = var0 ^ 116283641232829L;
      Z(var2);
      q = null;
      F = 0L;
      if (u != null) {
         u.close();
         u = null;
      }

      if (s != null) {
         s.close();
         s = null;
      }

      if (m != null) {
         MemoryUtil.memFree(m);
         m = null;
      }

      if (g != null) {
         MemoryUtil.memFree(g);
         g = null;
      }

      if (c3 != null) {
         c3.close();
         c3 = null;
      }

      if (z != null) {
         MemoryUtil.memFree(z);
         z = null;
      }

      y = 0;
      W = null;
   }

   private static float p(float var0, int var1, byte var2, float var3, int var4, float var5) {
      WeatherModule.ensureSourceClassInitialized();
      return var0 < var3 ? Math.min(var3, var0 + var5) : Math.max(var3, var0 - var5);
   }

   private static float D(float var0) {
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static float M(float var0, float var1, float var2) {
      return D(Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F));
   }

   private static float h(int var0, int var1, WeatherParticlePass.WeatherParticle var2, byte var3, class_638 var4) {
      WeatherModule.ensureSourceClassInitialized();
      int var5 = class_3532.method_15357(var2.t);
      int var6 = class_3532.method_15357(var2.N);
      if (var5 == var2.U && var6 == var2.M && !(i >= var2.D)) {
         return var2.e;
      } else {
         var2.U = var5;
         var2.M = var6;
         var2.e = M.read(var4, var5, var6, i);
         var2.D = i + 0.35F + var2.C * 0.23F;
         return var2.e;
      }
   }

   public static void install(WeatherModule var0, ModuleContext var1) {
      K = var0;
      Z = var1;
   }

   public static void metric(String var0) {
      if (Z != null) {
         Z.metric(var0, var0.equals("drawn") ? ++ca : ++cP);
      }
   }

   public static void uninstall() {
      int var0 = 0;

      try {
         if (u != null) {
            u.close();
         }
      } catch (Exception var90) {
         var0++;
      } finally {
         u = null;
      }

      try {
         if (s != null) {
            s.close();
         }
      } catch (Exception var88) {
         var0++;
      } finally {
         s = null;
      }

      try {
         if (c3 != null) {
            c3.close();
         }
      } catch (Exception var86) {
         var0++;
      } finally {
         c3 = null;
      }

      try {
         if (m != null) {
            MemoryUtil.memFree(m);
         }
      } catch (Exception var84) {
         var0++;
      } finally {
         m = null;
      }

      try {
         if (g != null) {
            MemoryUtil.memFree(g);
         }
      } catch (Exception var82) {
         var0++;
      } finally {
         g = null;
      }

      try {
         if (z != null) {
            MemoryUtil.memFree(z);
         }
      } catch (Exception var80) {
         var0++;
      } finally {
         z = null;
      }

      close();
      WeatherUpdater.stop();
      K = null;
      Z = null;
      F = 0L;
      if (var0 != 0) {
         throw new IllegalStateException("World visual GPU cleanup failed");
      }
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 5228) & 32767;
      if (fb[var3] == null) {
         fb[var3] = eb[var3] ^ var1;
      }

      return fb[var3];
   }

   static {
      for (int var0 = 0; var0 < cZ.length; var0++) {
         cZ[var0] = new WeatherParticlePass.WeatherParticle(0L);
      }

      for (int var1 = 0; var1 < O.length; var1++) {
         O[var1] = new WeatherParticlePass.WeatherBatch();
      }

      M = new WeatherParticlePass.WeatherCell(63121127719930L);
      cy = new float[3];
      T = new float[74400];
      Y = new int[12400];
      f = new long[6200];
      n = new long[6200];
      B = new int[256];
      X = new float[1120];
      F = 0L;
      U = 1.0F;
      l = 1.0F;
      cw = 24.0F;
      cV = 0.5F;
      cX = 1.0F;
      R = 1.0F;
      w = 10.0F;
      N = 3.5F;
      cc = 0;
      Q = 0;
      L = 0;
      cf = 0;
      b = 0;
      P = new Matrix4f();
      y = 0;
      j = new Matrix4f();
      ca = 0;
      cP = 0;
      k = 0;
      eb = new long[]{
         684434451535807101L, 5172567725796136415L, 4780832872182532052L, 15275465109746398L, 6656581068294242202L, 7647552078712949544L, 74359097971592363L
      };
      fb = new Long[7];
   }

   private static final class WeatherParticle {
      double t;
      double R;
      double N;
      float n;
      float J;
      float E;
      float b;
      float q;
      float C;
      int W;
      boolean Z;
      int U;
      int M;
      float e;
      float D;
      private static final long a;

      private WeatherParticle(long var1) {
         this.U = (int)a;
      }

      static {
         long var0 = 111236523916387L;
         long var2 = 6737986809432538211L ^ var0;
         a = var2;
      }
   }

   private static final class WeatherBatch {
      double s;
      double I;
      double n;
      float P;
      float l;
      float t;
      int O;
   }

   private static final class WeatherCell {
      private static final int v = 8192;
      private final long[] r = new long[8192];
      private final float[] k = new float[8192];
      private final float[] K = new float[8192];
      private final class_2339 u = new class_2339();
      private static final long[] a = new long[]{7061116375620894888L, -9046715854280240055L, -2245293241671880868L};
      private static final Integer[] b = new Integer[3];

      private WeatherCell(long var1) {
      }

      void clear() {
         Arrays.fill(this.r, Long.MIN_VALUE);
         Arrays.fill(this.K, 0.0F);
      }

      float height(class_638 var1, double var2, double var4, float var6) {
         int var7 = class_3532.method_15357(var2);
         int var8 = class_3532.method_15357(var4);
         long var9 = (long)var7 << 32 ^ var8 & 4294967295L;
         int var11 = (var7 * 73428767 ^ var8 * 912931) & 8191;
         return this.r[var11] == var9 && var6 < this.K[var11] ? this.k[var11] : this.read(var1, var7, var8, var6);
      }

      float read(class_638 var1, int var2, int var3, float var4) {
         long var5 = (long)var2 << 32 ^ var3 & 4294967295L;
         int var7 = (var2 * 73428767 ^ var3 * 912931) & 8191;
         this.u.method_10103(var2, class_3532.method_15357(WeatherParticlePass.v), var3);
         float var8 = var1.method_22340(this.u) ? var1.method_8624(class_2903.field_13197, var2, var3) : Float.NaN;
         this.r[var7] = var5;
         this.k[var7] = var8;
         this.K[var7] = var4 + 0.35F + var7 % 13 * 0.019F;
         return var8;
      }

      float exact(class_638 var1, double var2, float var4, double var5) {
         int var7 = class_3532.method_15357(var2);
         int var8 = class_3532.method_15357(var5);
         int var9 = class_3532.method_15375(var4) - 1;

         for (int var10 = 0; var10 < 3; var9--) {
            this.u.method_10103(var7, var9, var8);
            class_2680 var11 = var1.method_8320(this.u);
            class_3610 var12 = var11.method_26227();
            float var13 = var12.method_15769() ? Float.NEGATIVE_INFINITY : var9 + var12.method_15763(var1, this.u);
            class_265 var14 = var11.method_26218(var1, this.u);
            if (!var14.method_1110()) {
               double var15 = var2 - var7;
               double var17 = var5 - var8;

               for (class_238 var20 : var14.method_1090()) {
                  if (var15 >= var20.field_1323 && var15 <= var20.field_1320 && var17 >= var20.field_1321 && var17 <= var20.field_1324) {
                     var13 = Math.max(var13, (float)(var9 + var20.field_1325));
                  }
               }
            }

            if (Float.isFinite(var13)) {
               return var13;
            }

            var10++;
         }

         return var4 - 1.0F;
      }
   }
}
