package dev.purxiy.modules.shockwave;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.pipeline.RenderPipeline.Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline.Snippet;
import com.mojang.blaze3d.platform.DepthTestFunction;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import com.mojang.blaze3d.textures.TextureFormat;
import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.OptionalLong;
import net.minecraft.class_10789;
import net.minecraft.class_12137;
import net.minecraft.class_276;
import net.minecraft.class_290;
import net.minecraft.class_310;
import net.minecraft.class_1011.class_1012;
import org.joml.Vector4f;
import org.lwjgl.system.MemoryUtil;

final class ShockwaveRenderer {
   private static final int l = 240;
   private static final int z = 3;
   private static final int E = 49152;
   private static final RenderPipeline d = j("shockwave", 47129036970382L, false);
   private static final RenderPipeline b = j("shockwave_masked", 47129036970382L, true);
   private static GpuBuffer m;
   private static ByteBuffer p;
   private static ByteBuffer Y;
   private static GpuTexture r;
   private static GpuTextureView j;
   private static GpuTexture u;
   private static GpuTextureView S;
   private static int O = 0;
   private static int e = 0;
   private static final boolean[] w = new boolean[4096];
   private static final Vector4f R = new Vector4f();
   private static final float[] g = new float[4];
   private static boolean t;
   private static GpuQuery P;
   private static double H;
   private static int a = 0;
   private static final Integer[] i = new Integer[25];
   private static final long[] k = new long[]{5389630967090010080L, 8960514136791950358L};
   private static final Long[] n = new Long[2];

   private ShockwaveRenderer() {
   }

   private static RenderPipeline j(String var0, long var1, boolean var3) {
      Builder var4 = RenderPipeline.builder(new Snippet[0])
         .withLocation(ClientServices.id("pipeline/" + var0))
         .withVertexShader(ClientServices.id("core/shockwave"))
         .withFragmentShader(ClientServices.id("core/shockwave"))
         .withUniform("Fog", class_10789.field_60031)
         .withUniform("PurxiyShockwave", class_10789.field_60031)
         .withSampler("DepthSampler")
         .withSampler("SceneSampler")
         .withSampler("GroundSampler");
      if (var3) {
         var4.withShaderDefine("MASKED").withSampler("HandMaskSampler");
      }

      return var4.withoutBlend()
         .withDepthWrite(false)
         .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
         .withCull(false)
         .withVertexFormat(class_290.field_60033, class_5596.field_27379)
         .build();
   }

   static void setTiming(boolean var0) {
      t = var0;
      H = 0.0;
      a = 0;
   }

   static void draw(class_310 var0) {
      long var1 = 20412104888289L;
      long var3 = var1 ^ 46432878820837L;
      long var5 = var1 ^ 123939475731697L;
      long var7 = var1 ^ 60177997008332L;
      long var9 = var1 ^ 12430747395937L;
      long var11 = var1 ^ 90704343724516L;
      int var13 = (int)((var1 ^ 90704343724516L) >>> 32);
      int var14 = (int)((var1 ^ 90704343724516L) << 32 >>> 56);
      int var15 = (int)(var11 << 40 >>> 40);
      class_276 var16 = var0.method_1522();
      GpuTextureView var17 = var16.method_71639();
      GpuTexture var18 = var16.method_30277();
      GpuTextureView var19 = var16.method_71640();
      if (var17 != null && var18 != null && var19 != null && ShockwaveRenderer2.W != null) {
         GpuDevice var20 = RenderSystem.getDevice();
         if (var20.precompilePipeline(d).isValid() && i(var13, (byte)var14, var15)) {
            o(var20, var3, var16.field_1482, var16.field_1481);
            CommandEncoder var21 = var20.createCommandEncoder();
            C(var9);
            var21.writeToTexture(r, Y.clear(), class_1012.field_4997, 0, 0, 0, 0, 192, 64);
            var21.copyTextureToTexture(var18, u, 0, 0, 0, 0, 0, var16.field_1482, var16.field_1481);
            GpuTextureView var22 = ShockwaveRenderer2.K ? ClientSupport3.handMask() : null;
            RenderPipeline var23 = var22 != null && var20.precompilePipeline(b).isValid() ? b : d;
            ShockwaveModule var24 = ShockwaveRenderer2.Q;
            ShockwaveLoader var25 = ShockwaveLoader.resolve((String)var24.I.get(), var24.U.argb());
            Std140Builder var26 = Std140Builder.intoBuffer(p.clear())
               .putMat4f(ShockwaveRenderer2.p)
               .putMat4f(ShockwaveRenderer2.L)
               .putVec4(var16.field_1482, var16.field_1481, var24.j.floatValue(), 0.0F)
               .putVec4(
                  (float)(ShockwaveRenderer2.v - ShockwaveRenderer2.r),
                  (float)(ShockwaveRenderer2.d - ShockwaveRenderer2.j),
                  (float)(ShockwaveRenderer2.l - ShockwaveRenderer2.G),
                  64.0F
               )
               .putVec4(ShockwaveRenderer2.e + 0.01F, ShockwaveRenderer2.N + 0.01F, ShockwaveRenderer2.Y * var16.field_1481 * 0.5F, 0.0F)
               .putVec4(G(var25.o, var5, 16), G(var25.o, var5, 8), G(var25.o, var5, 0), 0.0F)
               .putVec4(G(var25.B, var5, 16), G(var25.B, var5, 8), G(var25.B, var5, 0), 0.0F)
               .putVec4(G(var25.O, var5, 16), G(var25.O, var5, 8), G(var25.O, var5, 0), 0.0F)
               .putVec4(g[0], g[1], g[2], g[3]);
            var21.writeToBuffer(m.slice(), var26.get());
            class_12137 var27 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            GpuQuery var28 = t ? V(var21, var7) : null;

            try {
               RenderPass var29 = var21.createRenderPass(() -> "Purxiy shockwave", var17, OptionalInt.empty());

               try {
                  var29.setPipeline(var23);
                  RenderSystem.bindDefaultUniforms(var29);
                  var29.setUniform("Fog", ShockwaveRenderer2.W);
                  var29.setUniform("PurxiyShockwave", m);
                  var29.bindTexture("DepthSampler", var19, var27);
                  var29.bindTexture("SceneSampler", S, var27);
                  var29.bindTexture("GroundSampler", j, var27);
                  if (var23 == b) {
                     var29.bindTexture("HandMaskSampler", var22, var27);
                  }

                  var29.draw(0, 6);
                  ShockwaveRenderer2.metric("drawn");
               } catch (Throwable var37) {
                  if (var29 != null) {
                     try {
                        var29.close();
                     } catch (Throwable var36) {
                        var37.addSuppressed(var36);
                     }
                  }

                  throw var37;
               }

               if (var29 != null) {
                  var29.close();
               }
            } finally {
               if (var28 != null) {
                  var21.timerQueryEnd(var28);
                  P = var28;
               }
            }
         }
      }
   }

   private static void C(long var0) {
      long var3 = 132555059735366L;
      long var5 = 59724120238419L;
      byte var7 = 64;
      ShockwaveModule.ensureSourceClassInitialized();
      ByteBuffer var8 = Y;
      MemoryUtil.memSet(MemoryUtil.memAddress0(var8), 0, 49152L);
      int var9 = ShockwaveRenderer2.F;
      int var10 = ShockwaveRenderer2.H;
      int var11 = ShockwaveRenderer2.C;
      int var12 = ShockwaveRenderer2.u;

      for (int var13 = var11; var13 <= var12; var13++) {
         long var14 = 28444797347967L;
         int var37 = var14 < 0L ? -1 : 1;

         for (int var17 = var9; var17 <= var10; var17++) {
            long var18 = 28444797347967L;
            int var20 = var18 < 0L ? -1 : 1;
            var20 = var13 * var7 + var17;
            long var21 = 28444797347968L;
            int var21x = var21 < 0L ? -1 : 1;
            w[var20] = !Float.isNaN(ShockwaveRenderer2.i[var20]) && (Math.abs(ShockwaveRenderer2.D[var20]) > 8.0E-4F || ShockwaveRenderer2.q[var20] > 0.004F);
         }

         long var36 = 28444797347968L;
         int var39 = var36 < 0L ? -1 : 1;
      }

      long var35 = 28444797347967L;
      int var16 = var35 < 0L ? -1 : 1;
      var16 = var11;

      while (true) {
         long var37 = 28444797347968L;
         int var391 = var37 < 0L ? -1 : 1;
         if (var16 > var12) {
            long var40 = 28444797347968L;
            int var46 = var40 < 0L ? -1 : 1;
            return;
         }

         for (int var38 = var9; var38 <= var10; var38++) {
            long var41 = 28444797347967L;
            int var23 = var41 < 0L ? -1 : 1;
            var23 = var16 * var7 + var38;
            float var24 = ShockwaveRenderer2.i[var23];
            if (Float.isNaN(var24)) {
               long var25 = 28444797347968L;
               int var24x = var25 < 0L ? -1 : 1;
            } else {
               int var42;
               byte var2;
               label110: {
                  long var26 = 28444797347967L;
                  var42 = var26 < 0L ? -1 : 1;
                  var42 = A(var38, var16, 1, var5, var9, var10, var11, var12) ? 1 : 0;
                  long var29 = 28444797347968L;
                  int var31 = var29 < 0L ? -1 : 1;
                  long var31x = 28444797347968L;
                  var2 = (byte)(var31x < 0L ? -1 : 1);
                  if (var42 != 1) {
                     long var33 = 28444797347968L;
                     int var43 = var33 < 0L ? -1 : 1;
                     if (!A(var38, var16, 2, var5, var9, var10, var11, var12)) {
                        var2 = (byte)0;
                        break label110;
                     }
                  }

                  var2 = (byte)2;
               }

               int var27 = Math.clamp((long)(Math.round((var24 - ShockwaveRenderer2.l) * 256.0F) + 8192), 1, 16383);
               float var28 = ShockwaveRenderer2.D[var23];
               int var43 = Math.clamp((long)(Math.round(var28 * 16384.0F) + 32768), 0, 65535);
               int var30 = (var16 * var7 + var38) * 3 * 4;
               var8.put(var30, (byte)(var27 >> 6));
               var8.put(var30 + 1, (byte)((var27 & 63) << 2 | var42 | var2));
               var8.put(var30 + 2, (byte)(var43 >> 8));
               var8.put(var30 + 3, (byte)var43);
               var8.put(var30 + 4, (byte)Math.round(Math.clamp(ShockwaveRenderer2.q[var23], 0.0F, 1.0F) * 255.0F));
               var8.put(var30 + 5, (byte)Math.round(ShockwaveRenderer2.S[var23] * 255.0F));
               var8.put(var30 + 7, (byte)-1);
               var8.put(var30 + 8, Q(var24, var28, var38 - 1, var16, var9, var10, var3, var11, var12));
               var8.put(var30 + 9, Q(var24, var28, var38 + 1, var16, var9, var10, var3, var11, var12));
               var8.put(var30 + 10, Q(var24, var28, var38, var16 - 1, var9, var10, var3, var11, var12));
               var8.put(var30 + 11, Q(var24, var28, var38, var16 + 1, var9, var10, var3, var11, var12));
            }
         }

         long var39x = 28444797347967L;
         int var45 = var39x < 0L ? -1 : 1;
         var16++;
      }
   }

   private static byte Q(float var0, float var1, int var2, int var3, int var4, int var5, long var6, int var8, int var9) {
      ShockwaveModule.ensureSourceClassInitialized();
      float var10 = var0;
      float var11 = 0.0F;
      long var12 = 132555059735365L;
      int var24 = var12 < 0L ? -1 : 1;
      if (var2 >= var4) {
         long var15 = 132555059735366L;
         int var25 = var15 < 0L ? -1 : 1;
         if (var2 <= var5) {
            long var17 = 132555059735365L;
            int var19 = var17 < 0L ? -1 : 1;
            if (var3 >= var8) {
               long var19x = 132555059735365L;
               int var21 = var19x < 0L ? -1 : 1;
               long var21x = 132555059735366L;
               int var23 = var21x < 0L ? -1 : 1;
               if (var3 <= var9) {
                  var23 = var3 * 64 + var2;
                  if (!Float.isNaN(ShockwaveRenderer2.i[var23])) {
                     var10 = ShockwaveRenderer2.i[var23];
                     var11 = ShockwaveRenderer2.D[var23];
                  }
               }
            }
         }
      }

      float var24x = var10 - var11 - (var0 - var1);
      float var16 = var1 - var11;
      float var25 = Math.max(Math.min(var24x, var16), 0.0F);
      float var18 = Math.max(Math.min(-var24x, -var16), 0.0F);
      return (byte)Math.clamp((long)(Math.round((var25 - var18) * 512.0F) + 128), 0, 255);
   }

   private static boolean A(int var0, int var1, int var2, long var3, int var5, int var6, int var7, int var8) {
      ShockwaveModule.ensureSourceClassInitialized();
      byte var9 = 64;

      for (int var10 = Math.max(var1 - var2, var7); var10 <= Math.min(var1 + var2, var8); var10++) {
         int var11 = var0 - var2;
         long var12 = 59724120238418L;
         int var18 = var12 < 0L ? -1 : 1;

         for (int var15 = Math.max(var11, var5); var15 <= Math.min(var0 + var2, var6); var15++) {
            long var16 = 59724120238418L;
            int var17 = var16 < 0L ? -1 : 1;
            if (w[var10 * var9 + var15]) {
               return true;
            }
         }

         long var18x = 59724120238418L;
         int var161 = var18x < 0L ? -1 : 1;
      }

      return false;
   }

   private static boolean i(int var0, byte var1, int var2) {
      float var3 = Float.MAX_VALUE;
      ShockwaveModule.ensureSourceClassInitialized();
      float var4 = -Float.MAX_VALUE;
      byte var5 = 64;

      for (int var6 = ShockwaveRenderer2.C; var6 <= ShockwaveRenderer2.u; var6++) {
         int var7 = 16622;

         for (int var8 = ShockwaveRenderer2.F; var8 <= ShockwaveRenderer2.H; var8++) {
            int var27 = 16622;
            float var9 = ShockwaveRenderer2.i[var6 * var5 + var8];
            var27 = 7159301;
            var27 = 7159301;
            if (!Float.isNaN(var9)) {
               var3 = Math.min(var3, var9);
               var4 = Math.max(var4, var9);
            }
         }
      }

      int var24 = 16622;
      float var30;
      var24 = (var30 = var3 - var4) == 0.0F ? 0 : (var30 < 0.0F ? -1 : 1);
      byte var31 = 80;
      if (var24 > 0) {
         return false;
      } else {
         float var34 = (float)(ShockwaveRenderer2.v + ShockwaveRenderer2.F - ShockwaveRenderer2.r);
         float var10 = (float)(ShockwaveRenderer2.v + ShockwaveRenderer2.H + 1 - ShockwaveRenderer2.r);
         float var11 = (float)(ShockwaveRenderer2.d + ShockwaveRenderer2.C - ShockwaveRenderer2.j);
         float var12 = (float)(ShockwaveRenderer2.d + ShockwaveRenderer2.u + 1 - ShockwaveRenderer2.j);
         float var13 = (float)(var3 - ShockwaveRenderer2.N - 0.05F - ShockwaveRenderer2.G);
         float var14 = (float)(var4 + ShockwaveRenderer2.e + 0.05F - ShockwaveRenderer2.G);
         float var15 = Float.MAX_VALUE;
         float var16 = Float.MAX_VALUE;
         float var17 = -Float.MAX_VALUE;
         float var18 = -Float.MAX_VALUE;
         boolean var19 = false;

         for (int var20 = 0; var20 < 8; var20++) {
            R.set((var20 & 1) == 0 ? var34 : var10, (var20 & 2) == 0 ? var13 : var14, (var20 & 4) == 0 ? var11 : var12, 1.0F);
            ShockwaveRenderer2.p.transform(R);
            float var21;
            var24 = (var21 = R.w - 0.05F) == 0.0F ? 0 : (var21 < 0.0F ? -1 : 1);
            var31 = 80;
            if (var24 < 0) {
               var31 = 80;
               var19 = true;
               break;
            }

            float var22 = R.x / R.w;
            float var23 = R.y / R.w;
            var15 = Math.min(var15, var22);
            var16 = Math.min(var16, var23);
            var17 = Math.max(var17, var22);
            var18 = Math.max(var18, var23);
         }

         if (var19) {
            var16 = -1.0F;
            var15 = -1.0F;
            var18 = 1.0F;
            var17 = 1.0F;
         }

         g[0] = Math.max(var15, -1.0F);
         g[1] = Math.max(var16, -1.0F);
         g[2] = Math.min(var17, 1.0F);
         g[3] = Math.min(var18, 1.0F);
         return g[0] < g[2] && g[1] < g[3];
      }
   }

   private static void o(GpuDevice var0, long var1, int var3, int var4) {
      ShockwaveModule.ensureSourceClassInitialized();
      if (m == null) {
         m = var0.createBuffer(ClientServices.label(() -> "Purxiy shockwave"), 136, 240L);
         p = MemoryUtil.memAlloc(240);
         Y = MemoryUtil.memAlloc(49152);
         r = var0.createTexture(() -> "Purxiy shockwave ground", 5, TextureFormat.RGBA8, 192, 64, 1, 1);
         j = var0.createTextureView(r);
      }

      long var5 = 62304666104323L;
      int var8 = var5 < 0L ? -1 : 1;
      if (u != null) {
         long var8x = 62304666104324L;
         int var9 = var8x < 0L ? -1 : 1;
         if (O == var3 && e == var4) {
            return;
         }
      }

      if (u != null) {
         S.close();
         u.close();
      }

      u = var0.createTexture(() -> "Purxiy shockwave scene", 5, TextureFormat.RGBA8, var3, var4, 1, 1);
      S = var0.createTextureView(u);
      O = var3;
      e = var4;
   }

   private static float G(int var0, long var1, int var3) {
      return (var0 >>> var3 & 0xFF) / 255.0F;
   }

   private static GpuQuery V(CommandEncoder var0, long var1) {
      ShockwaveModule.ensureSourceClassInitialized();
      if (P != null) {
         OptionalLong var3 = P.getValue();
         long var4 = 39770732380717L;
         int var7 = var4 < 0L ? -1 : 1;
         if (var3.isEmpty()) {
            return null;
         }

         P.close();
         P = null;
         H = H + var3.getAsLong() / 1000000.0;
         var7 = a + 1;
         long var8 = 39770732380717L;
         int var9 = var8 < 0L ? -1 : 1;
         a = var7;
         if (var7 == 240) {
            ClientServices.LOGGER
               .info("Purxiy shockwave: {} ms GPU per frame ({} waves)", String.format(Locale.ROOT, "%.3f", H / a), ShockwaveRenderer2.T.size());
            H = 0.0;
            a = 0;
         }
      }

      return var0.timerQueryBegin();
   }

   public static void releaseResources() {
      int var0 = 0;

      try {
         if (d != null) {
            ClientCache.release(d);
         }
      } catch (Exception var230) {
         var0++;
      } finally {
         ;
      }

      try {
         if (b != null) {
            ClientCache.release(b);
         }
      } catch (Exception var228) {
         var0++;
      } finally {
         ;
      }

      try {
         if (j != null) {
            j.close();
         }
      } catch (Exception var226) {
         var0++;
      } finally {
         j = null;
      }

      try {
         if (S != null) {
            S.close();
         }
      } catch (Exception var224) {
         var0++;
      } finally {
         S = null;
      }

      try {
         if (P != null) {
            P.close();
         }
      } catch (Exception var222) {
         var0++;
      } finally {
         P = null;
      }

      try {
         if (m != null) {
            m.close();
         }
      } catch (Exception var220) {
         var0++;
      } finally {
         m = null;
      }

      try {
         if (r != null) {
            r.close();
         }
      } catch (Exception var218) {
         var0++;
      } finally {
         r = null;
      }

      try {
         if (u != null) {
            u.close();
         }
      } catch (Exception var216) {
         var0++;
      } finally {
         u = null;
      }

      try {
         if (p != null) {
            MemoryUtil.memFree(p);
         }
      } catch (Exception var214) {
         var0++;
      } finally {
         p = null;
      }

      try {
         if (Y != null) {
            MemoryUtil.memFree(Y);
         }
      } catch (Exception var212) {
         var0++;
      } finally {
         Y = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 12400) & 32767;
      if (n[var3] == null) {
         n[var3] = k[var3] ^ var1;
      }

      return n[var3];
   }
}
