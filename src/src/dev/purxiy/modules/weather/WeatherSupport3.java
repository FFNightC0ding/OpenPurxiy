// Sourced By DevFFNightmare
package dev.purxiy.modules.weather;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.AddressMode;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.assets.IrisCompat;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.OptionalLong;
import net.minecraft.class_10789;
import net.minecraft.class_12137;
import net.minecraft.class_12202;
import net.minecraft.class_12206;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_4184;
import net.minecraft.class_5636;
import net.minecraft.class_638;
import net.minecraft.class_9909;
import net.minecraft.class_9916;
import net.minecraft.class_9925;
import net.minecraft.class_9960;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

import org.lwjgl.system.MemoryUtil;

public final class WeatherSupport3 {
   private static final int z = 288;
   private static final int P = 32;
   private static final int B = 8;
   private static final RenderPipeline V = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/weather_surface"))
      .withVertexShader(ClientServices.id("core/weather_surface"))
      .withFragmentShader(ClientServices.id("core/weather_surface"))
      .withUniform("Fog", class_10789.field_60031)
      .withUniform("WeatherSurface", class_10789.field_60031)
      .withSampler("SceneSampler")
      .withSampler("DepthSampler")
      .withSampler("RainSampler")
      .withSampler("SkySampler")
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static final RenderPipeline s = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/weather_sky"))
      .withVertexShader(ClientServices.id("core/weather_surface"))
      .withFragmentShader(ClientServices.id("core/weather_sky"))
      .withUniform("WeatherSurface", class_10789.field_60031)
      .withSampler("SceneSampler")
      .withSampler("DepthSampler")
      .withSampler("PreviousSampler")
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static final RenderPipeline A = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/weather_surface_masked"))
      .withVertexShader(ClientServices.id("core/weather_surface"))
      .withFragmentShader(ClientServices.id("core/weather_surface"))
      .withShaderDefine("MASKED")
      .withUniform("Fog", class_10789.field_60031)
      .withUniform("WeatherSurface", class_10789.field_60031)
      .withSampler("SceneSampler")
      .withSampler("DepthSampler")
      .withSampler("RainSampler")
      .withSampler("SkySampler")
      .withSampler("HandMaskSampler")
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static final RenderPipeline k = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/weather_sky_masked"))
      .withVertexShader(ClientServices.id("core/weather_surface"))
      .withFragmentShader(ClientServices.id("core/weather_sky"))
      .withShaderDefine("MASKED")
      .withUniform("WeatherSurface", class_10789.field_60031)
      .withSampler("SceneSampler")
      .withSampler("DepthSampler")
      .withSampler("PreviousSampler")
      .withSampler("HandMaskSampler")
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static final WeatherSupport S = new WeatherSupport();
   private static final Matrix4f F = new Matrix4f();
   private static final Matrix4f i = new Matrix4f();
   private static float U = 1.0F;
   private static boolean L;
   
   private static GpuBufferSlice C;
   
   private static class_243 c;
   
   private static class_638 j;
   private static long w = 0L;
   private static float l;
   private static float Q = 0.016666668F;
   private static float O;
   private static float d;
   private static float p;
   private static int q = 0;
   private static final float[] Z = new float[3];
   private static final float[] G = new float[3];
   private static final float[] u = new float[3];
   private static float D;
   private static float M = 1.0F;
   private static float K;
   private static float t;
   private static float T;
   
   private static GpuBuffer H;
   
   private static ByteBuffer E;
   
   private static GpuTexture e;
   
   private static GpuTextureView W;
   private static final GpuTexture[] m = new GpuTexture[2];
   private static final GpuTextureView[] f = new GpuTextureView[2];
   private static int x = 0;
   private static boolean r = true;
   
   private static String N;
   private static boolean a;
   private static int Y = 0;
   private static boolean g;
   
   private static GpuQuery b;
   private static double o;
   private static int v = 0;




   private static final long J = 288L;

   private WeatherSupport3() {
   }

   public static void capture(Matrix4fc var0, Matrix4fc var1) {
      if (!ModuleWidget.claimed("mirror-capture")) {
         F.set(var1).mul(var0);
         F.invert(i);
         U = var1.m11();
         L = IrisCompat.inUse();
         c = null;
         WeatherModule var2 = WeatherSupport5.K;
         if (L && var2.enabled() && var2.N.enabled()) {
            ClientSupport3.wantHandMask();
         }
      }
   }

   public static void renderAfterLevel() {
      long var0 = 56386813849792L;
      long var2 = var0 ^ 113383397764001L;
      class_243 var4 = c;
      GpuBufferSlice var5 = C;
      c = null;
      if (var4 != null && var5 != null && !ModuleWidget.claimed("mirror-capture")) {
         x(class_310.method_1551().method_1522(), var2, var5, var4, ClientSupport3.handMask());
      }
   }

   public static void addPass(class_9909 var0, class_9960 var1, GpuBufferSlice var2) {
      long var3 = 97448874822290L;
      long var5 = var3 ^ 37544555357151L;
      if (!ModuleWidget.claimed("mirror-capture")) {
         class_310 var7 = class_310.method_1551();
         long var8 = System.nanoTime();
         float var10 = w != 0L && var8 - w <= 500000000L ? Math.min(0.1F, (float)(var8 - w) * 1.0E-9F) : 0.0F;
         w = var8;
         if (var7.method_1493()) {
            var10 = 0.0F;
         }

         if (var10 > 0.0F) {
            Q = var10;
         }

         class_638 var11 = var7.field_1687;
         if (j != var11) {
            S.clear();
            p = 0.0F;
            d = 0.0F;
            O = 0.0F;
            r = true;
            j = var11;
         }

         l += var10;
         WeatherModule var12 = WeatherSupport5.K;
         boolean var13 = var11 != null && var12.enabled() && var12.N.enabled();
         float var14 = var12.Z.floatValue();
         float var15 = Math.max(0.15F, var12.O.floatValue());
         O = x(O, var13 && var12.t.is("Rain") ? 1.0F : 0.0F, var10, var13 ? 0.45F * var15 : 1.6F);
         d = x(d, var13 && var12.t.is("Snow") ? var14 : 0.0F, var10, var13 && var12.t.is("Snow") ? 0.15F * var15 : 1.8F);
         p = x(p, var13 && var12.t.is("Fire") ? var14 : 0.0F, var10, var13 ? 0.6F * var15 : 3.0F);
         q = 0;
         if (var11 != null && !(O + d + p < 0.002F)) {
            class_4184 var16 = var7.field_1773.method_19418();
            class_243 var17 = var16.method_71156();
            S.scan(var11, class_3532.method_15357(var17.field_1352) >> 4, class_3532.method_15357(var17.field_1350) >> 4, var7.field_1690.method_38521(), var10);
            if (var16.method_19334() == class_5636.field_27888) {
               Z(var11, var5, var16);
               if (L) {
                  c = var17;
                  C = var2;
               } else {
                  class_9916 var18 = var0.method_61911("purxiy_weather_surfaces");
                  var1.field_53091 = var18.method_61933(var1.field_53091);
                  class_9925 var19 = var1.field_53091;
                  var18.method_61929(() -> {
                     long var3x = 92735678176097L;
                     x((class_276)var19.get(), var3x, var2, var17, null);
                  });
               }
            }
         } else if (S.knownColumns() > 0) {
            S.clear();
         }
      }
   }

   private static void Z(class_638 var0, long var1, class_4184 var3) {
      long var4 = 78663405368798L;
      float var6 = var3.method_55437();
      WeatherModule.ensureSourceClassInitialized();
      float var7 = WeatherSupport2.storminess();
      float var8 = Z[0] * 0.2126F + Z[1] * 0.7152F + Z[2] * 0.0722F;
      class_12202 var9 = var3.method_75756();
      long var10 = 134709777787213L;
      if (134709777787213L == 0L) {
         boolean var10000 = false;
      } else {
         int var39 = var10 < 0L ? -1 : 1;
      }

      N(var9.method_75689(class_12206.field_63742, var6), var4, G);
      if (!var0.method_8597().comp_642()) {
         System.arraycopy(G, 0, Z, 0, 3);
         T = 0.0F;
         t = 0.0F;
      } else {
         N(var3.method_75756().method_75689(class_12206.field_63746, var6), var4, Z);
         float var13 = 0.3F * (Z[0] + Z[1] + Z[2]) / 3.0F + 0.04F;
         float var14 = 0.45F * (G[0] + G[1] + G[2]) / 3.0F + 0.04F;

         for (int var15 = 0; var15 < 3; var15++) {
            long var16 = 134709777787212L;
            if (134709777787212L == 0L) {
               boolean var40 = false;
            } else {
               int var41 = var16 < 0L ? -1 : 1;
            }

            Z[var15] = class_3532.method_16439(var7, Z[var15], var13);
            G[var15] = class_3532.method_16439(var7, G[var15], var14);
         }

         long var38 = 134709777787212L;
         if (134709777787212L == 0L) {
            boolean var42 = false;
         } else {
            int var43 = var38 < 0L ? -1 : 1;
         }

         float var18 = Z[0] * 0.2126F + Z[1] * 0.7152F + Z[2] * 0.0722F;
         long var19 = 134709777787212L;
         if (134709777787212L == 0L) {
            boolean var44 = false;
         } else {
            int var45 = var19 < 0L ? -1 : 1;
         }

         float var21 = Math.abs(var18 - var8);
         float var22 = 0.3F * Math.max(var18, var8);
         long var23 = 134709777787213L;
         if (134709777787213L == 0L) {
            boolean var46 = false;
         } else {
            int var47 = var23 < 0L ? -1 : 1;
         }

         if (var21 > var22 + 0.01F) {
            r = true;
         }

         float var25 = var3.method_75756().method_75689(class_12206.field_64340, var6) * (float) (Math.PI / 180.0);
         T = class_3532.method_15363(var3.method_75756().method_75689(class_12206.field_64346, var6), 0.0F, 1.0F);
         float var26 = class_3532.method_15362(var25);
         float var27 = (1.0F - var7 * 0.9F) * (1.0F - var0.method_8430(var6) * 0.85F);
         D = -class_3532.method_15374(var25);
         M = var26;
         long var28 = 134709777787212L;
         if (134709777787212L == 0L) {
            boolean var48 = false;
         } else {
            int var49 = var28 < 0L ? -1 : 1;
         }

         K = 0.0F;
         long var31 = 134709777787212L;
         if (134709777787212L == 0L) {
            boolean var50 = false;
         } else {
            int var51 = var31 < 0L ? -1 : 1;
         }

         if (var26 > -0.08F) {
            long var34 = 134709777787213L;
            if (134709777787213L == 0L) {
               boolean var52 = false;
            } else {
               int var53 = var34 < 0L ? -1 : 1;
            }

            float var37 = 1.0F - class_3532.method_15363(var26 / 0.35F, 0.0F, 1.0F);
            t = class_3532.method_15363((var26 + 0.08F) / 0.2F, 0.0F, 1.0F) * var27;
            u[0] = 1.0F;
            u[1] = 0.94F - 0.22F * var37;
            u[2] = 0.86F - 0.4F * var37;
         } else {
            D = -D;
            M = -M;
            t = 0.35F * class_3532.method_15363((-var26 - 0.08F) / 0.2F, 0.0F, 1.0F) * var27;
            u[0] = 0.72F;
            u[1] = 0.8F;
            u[2] = 1.0F;
         }
      }
   }

   private static void x(class_276 var0, long var1, GpuBufferSlice var3,  class_243 var4, GpuTextureView var5) {
      long var6 = 46182919071023L;
      byte var8 = 0;
      int var9 = 704695420;
      int var10 = (int)(var6 << 48 >>> 48);
      long var11 = 5929817992479L;
      WeatherModule.ensureSourceClassInitialized();

      try {
         GpuTextureView var13 = var0.method_71639();
         GpuTextureView var14 = var0.method_71640();
         if (var13 == null || var14 == null) {
            return;
         }

         GpuDevice var15 = RenderSystem.getDevice();
         RenderPipeline var16 = var5 != null ? A : V;
         RenderPipeline var17 = var5 != null ? k : s;
         long var18 = 92735678176097L;
         if (92735678176097L == 0L) {
            boolean var10000 = false;
         } else {
            int var56 = var18 < 0L ? -1 : 1;
         }

         boolean var21 = var15.precompilePipeline(var16).isValid();
         long var22 = 92735678176096L;
         if (92735678176096L == 0L) {
            boolean var57 = false;
         } else {
            int var58 = var22 < 0L ? -1 : 1;
         }

         if (!var21 || !var15.precompilePipeline(var17).isValid()) {
            N = "Surface shader compilation failed";
            return;
         }

         int var25 = var13.getWidth(0);
         int var26 = var13.getHeight(0);
         z(var15, var11, var25, var26, var13.texture().getFormat());
         CommandEncoder var27 = var15.createCommandEncoder();
         S.upload(var15, var27);
         GpuTextureView var28 = S.view();
         if (var28 == null) {
            return;
         }

         var27.copyTextureToTexture(var13.texture(), e, 0, 0, 0, 0, 0, var25, var26);
         var27.writeToBuffer(H.slice(), r(var25, var26, var8, var9, var4, (short)var10));
         class_12137 var29 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
         class_12137 var30 = RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
         var21 = false;
         Object var31 = null;
         boolean var32 = false;

         try {
            var32 = true;

            try (RenderPass var33 = var27.createRenderPass(() -> "Purxiy weather sky", f[x ^ 1], OptionalInt.empty())) {
               long var34 = 92735678176096L;
               if (92735678176096L == 0L) {
                  boolean var59 = false;
               } else {
                  int var60 = var34 < 0L ? -1 : 1;
               }

               var33.setPipeline(var17);
               var33.setUniform("WeatherSurface", H);
               var33.bindTexture("SceneSampler", W, var30);
               long var37 = 92735678176096L;
               if (92735678176096L == 0L) {
                  boolean var61 = false;
               } else {
                  int var62 = var37 < 0L ? -1 : 1;
               }

               var33.bindTexture("DepthSampler", var14, var29);
               var33.bindTexture("PreviousSampler", f[x], var29);
               if (var5 != null) {
                  var33.bindTexture("HandMaskSampler", var5, var29);
               }

               var33.draw(0, 3);
               long var39 = 92735678176096L;
               if (92735678176096L == 0L) {
                  boolean var63 = false;
               } else {
                  int var64 = var39 < 0L ? -1 : 1;
               }

               WeatherSupport5.metric("drawn");
            }

            x ^= 1;
            long var55 = 92735678176096L;
            if (92735678176096L == 0L) {
               boolean var65 = false;
            } else {
               int var66 = var55 < 0L ? -1 : 1;
            }

            r = false;

            try (RenderPass var35 = var27.createRenderPass(() -> "Purxiy weather surfaces", var13, OptionalInt.empty())) {
               var35.setPipeline(var16);
               var35.setUniform("Fog", var3);
               var35.setUniform("WeatherSurface", H);
               var35.bindTexture("SceneSampler", W, var30);
               var35.bindTexture("DepthSampler", var14, var29);
               var35.bindTexture("RainSampler", var28, var29);
               var35.bindTexture(
                  "SkySampler",
                  f[x],
                  RenderSystem.getSamplerCache().method_75293(AddressMode.REPEAT, AddressMode.CLAMP_TO_EDGE, FilterMode.LINEAR, FilterMode.LINEAR, false)
               );
               if (var5 != null) {
                  var35.bindTexture("HandMaskSampler", var5, var29);
               }

               var35.draw(0, 3);
               WeatherSupport5.metric("drawn");
            }
         } finally {
            if (var32 && var31 != null) {
               var27.timerQueryEnd((GpuQuery)var31);
               b = (GpuQuery)var31;
            }
         }

         if (var31 != null) {
            var27.timerQueryEnd((GpuQuery)var31);
            b = (GpuQuery)var31;
         }

         q = Math.max(1, S.knownColumns());
      } catch (Exception var53) {
         if (!var53.toString().equals(N)) {
            ClientServices.LOGGER.error("Weather surface rendering failed", var53);
         }

         N = var53.toString();
      }
   }

   private static ByteBuffer r(int var0, int var1, short var2, int var3, class_243 var4, short var5) {
      WeatherModule var6 = WeatherSupport5.K;
      int var7 = class_3532.method_15357(var4.field_1352);
      WeatherModule.ensureSourceClassInitialized();
      int var8 = class_3532.method_15357(var4.field_1351);
      int var9 = class_3532.method_15357(var4.field_1350);
      float var10 = Q;
      Std140Builder var11 = Std140Builder.intoBuffer(E.clear())
         .putMat4f(i)
         .putMat4f(F)
         .putVec4(var0, var1, 2.0F / (U * var1), l % 3600.0F)
         .putVec4((float)(var4.field_1352 - var7), (float)(var4.field_1351 - var8), (float)(var4.field_1350 - var9), (float)var4.field_1351)
         .putIVec4(var7, var8, var9, 0)
         .putVec4(O, d, p, var6.Z.floatValue());
      float var12 = var6.H.enabled() ? 1.0F : 0.0F;
      float var13 = WeatherSupport5.flash();
      return var11.putVec4(var12, var13, var6.B.floatValue(), var6.O.floatValue())
         .putVec4(D, M, K, t)
         .putVec4(u[0], u[1], u[2], T)
         .putVec4(Z[0], Z[1], Z[2], 0.05F)
         .putVec4(G[0], G[1], G[2], class_310.method_1551().field_1773.method_32796())
         .putVec4(1.0F - (float)Math.exp(-var10 * 6.0F), 1.0F - (float)Math.exp(-var10 * 0.8), r ? 1.0F : 0.0F, 0.0F)
         .get();
   }

   private static void z(GpuDevice var0, long var1, int var3, int var4, TextureFormat var5) {
      long var6 = 95430382977944L;
      WeatherModule.ensureSourceClassInitialized();
      if (H == null) {
         H = var0.createBuffer(ClientServices.label(() -> "Purxiy weather ground"), 136, 288L);
         E = MemoryUtil.memAlloc(288);
      }

      GpuTexture var8 = m[0];
      long var9 = 5929817992478L;
      if (5929817992478L == 0L) {
         boolean var10000 = false;
      } else {
         int var22 = var9 < 0L ? -1 : 1;
      }

      if (var8 == null) {
         for (int var12 = 0; var12 < 2; var12++) {
            m[var12] = var0.createTexture(() -> "Purxiy weather sky dome", 12, TextureFormat.RGBA8, 32, 8, 1, 1);
            long var13 = 5929817992478L;
            if (5929817992478L == 0L) {
               boolean var23 = false;
            } else {
               int var24 = var13 < 0L ? -1 : 1;
            }

            f[var12] = var0.createTextureView(m[var12]);
         }

         long var20 = 5929817992478L;
         if (5929817992478L == 0L) {
            boolean var25 = false;
         } else {
            int var26 = var20 < 0L ? -1 : 1;
         }

         r = true;
      }

      if (e != null) {
         long var21 = 5929817992478L;
         if (5929817992478L == 0L) {
            boolean var27 = false;
         } else {
            int var28 = var21 < 0L ? -1 : 1;
         }

         long var14 = 5929817992478L;
         if (5929817992478L == 0L) {
            boolean var29 = false;
         } else {
            int var30 = var14 < 0L ? -1 : 1;
         }

         if (e.getWidth(0) == var3) {
            long var16 = 5929817992479L;
            if (5929817992479L == 0L) {
               boolean var31 = false;
            } else {
               int var32 = var16 < 0L ? -1 : 1;
            }

            long var18 = 5929817992478L;
            if (5929817992478L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var18 < 0L ? -1 : 1;
            }

            if (e.getHeight(0) == var4 && e.getFormat() == var5) {
               return;
            }
         }
      }

      s(var6);
      e = var0.createTexture(() -> "Purxiy weather ground scene", 5, var5, var3, var4, 1, 1);
      W = var0.createTextureView(e);
   }

   private static float x(float var0, float var1, float var2, float var3) {
      return var0 + (var1 - var0) * (1.0F - (float)Math.exp(-var2 * var3));
   }

   private static void N(int var0, long var1, float[] var3) {
      var3[0] = (var0 >> 16 & 0xFF) / 255.0F;
      var3[1] = (var0 >> 8 & 0xFF) / 255.0F;
      var3[2] = (var0 & 0xFF) / 255.0F;
   }

   public static int surfaceCount() {
      return q;
   }

   public static float wetness() {
      return O;
   }

   public static float snowCover() {
      return d;
   }

   public static String lastError() {
      return N;
   }

   
   private static GpuQuery w(long var0, CommandEncoder var2) {
      WeatherModule.ensureSourceClassInitialized();
      if (b != null) {
         OptionalLong var3 = b.getValue();
         long var4 = 28607526478761L;
         if (28607526478761L == 0L) {
            boolean var10000 = false;
         } else {
            int var10 = var4 < 0L ? -1 : 1;
         }

         if (var3.isEmpty()) {
            return null;
         }

         b.close();
         b = null;
         o = o + var3.getAsLong() / 1000000.0;
         int var7 = v + 1;
         long var8 = 28607526478761L;
         if (28607526478761L == 0L) {
            boolean var11 = false;
         } else {
            int var12 = var8 < 0L ? -1 : 1;
         }

         v = var7;
         if (var7 == 240) {
            ClientServices.LOGGER.info("Purxiy weather ground: {} ms GPU per frame ({} columns)", String.format(Locale.ROOT, "%.3f", o / v), S.knownColumns());
            o = 0.0;
            v = 0;
         }
      }

      return var2.timerQueryBegin();
   }

   private static void s(long var0) {
      WeatherModule.ensureSourceClassInitialized();
      long var2 = 95430382977944L;
      if (95430382977944L == 0L) {
         boolean var10000 = false;
      } else {
         int var5 = var2 < 0L ? -1 : 1;
      }

      if (W != null) {
         W.close();
         W = null;
      }

      if (e != null) {
         e.close();
         e = null;
      }
   }

   public static void close() {
      long var0 = 43076735528686L;
      long var2 = var0 ^ 125234997947766L;
      s(var2);

      for (int var4 = 0; var4 < 2; var4++) {
         if (f[var4] != null) {
            f[var4].close();
            f[var4] = null;
         }

         if (m[var4] != null) {
            m[var4].close();
            m[var4] = null;
         }
      }

      if (H != null) {
         H.close();
         H = null;
      }

      if (E != null) {
         MemoryUtil.memFree(E);
         E = null;
      }

      if (b != null) {
         b.close();
         b = null;
      }

      S.close();
      j = null;
      q = 0;
      p = 0.0F;
      d = 0.0F;
      O = 0.0F;
      w = 0L;
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (V != null) {
            ClientCache.release(V);
         }
      } catch (Exception var275) {
         var0++;
      } finally {
         ;
      }

      try {
         if (s != null) {
            ClientCache.release(s);
         }
      } catch (Exception var273) {
         var0++;
      } finally {
         ;
      }

      try {
         if (A != null) {
            ClientCache.release(A);
         }
      } catch (Exception var271) {
         var0++;
      } finally {
         ;
      }

      try {
         if (k != null) {
            ClientCache.release(k);
         }
      } catch (Exception var269) {
         var0++;
      } finally {
         ;
      }

      try {
         if (W != null) {
            W.close();
         }
      } catch (Exception var267) {
         var0++;
      } finally {
         W = null;
      }

      for (int var1 = 0; var1 < f.length; var1++) {
         try {
            if (f[var1] != null) {
               f[var1].close();
            }
         } catch (Exception var265) {
            var0++;
         } finally {
            f[var1] = null;
         }
      }

      try {
         if (b != null) {
            b.close();
         }
      } catch (Exception var263) {
         var0++;
      } finally {
         b = null;
      }

      try {
         if (H != null) {
            H.close();
         }
      } catch (Exception var261) {
         var0++;
      } finally {
         H = null;
      }

      try {
         if (e != null) {
            e.close();
         }
      } catch (Exception var259) {
         var0++;
      } finally {
         e = null;
      }

      for (int var277 = 0; var277 < m.length; var277++) {
         try {
            if (m[var277] != null) {
               m[var277].close();
            }
         } catch (Exception var257) {
            var0++;
         } finally {
            m[var277] = null;
         }
      }

      try {
         if (E != null) {
            MemoryUtil.memFree(E);
         }
      } catch (Exception var255) {
         var0++;
      } finally {
         E = null;
      }

      close();
      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

}
