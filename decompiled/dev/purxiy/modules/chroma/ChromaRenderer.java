package dev.purxiy.modules.chroma;

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
import org.lwjgl.system.MemoryUtil;

final class ChromaRenderer {
   private static final int q = 2272;
   private static final RenderPipeline[] e = new RenderPipeline[4];
   private static GpuBuffer x;
   private static ByteBuffer A;
   private static GpuTexture u;
   private static GpuTextureView L;
   private static ByteBuffer I;
   private static GpuTexture R;
   private static GpuTextureView D;
   private static GpuTexture J;
   private static GpuTexture h;
   private static GpuTextureView g;
   private static GpuTextureView i;
   private static int f = 0;
   private static int Q = 0;
   private static boolean H;
   private static boolean B;
   private static boolean o;
   private static GpuQuery Y;
   private static double N;
   private static int y = 0;
   private static final Integer[] d = new Integer[17];
   private static final long j = 2272L;

   private ChromaRenderer() {
   }

   private static RenderPipeline j(int var0, char var1, boolean var2, short var3, boolean var4) {
      ChromaCodec.ensureSourceClassInitialized();
      int var5 = (var2 ? 1 : 0) | (var4 ? 2 : 0);
      if (e[var5] != null) {
         return e[var5];
      } else {
         Builder var6 = RenderPipeline.builder(new Snippet[0]);
         String var7 = var2 ? "_living" : "";
         String var8 = var4 ? "_masked" : "";
         Builder var10 = var6.withLocation(ClientServices.id("pipeline/chroma" + var7 + var8))
            .withVertexShader(ClientServices.id("core/chroma"))
            .withFragmentShader(ClientServices.id("core/chroma"))
            .withUniform("PurxiyChroma", class_10789.field_60031)
            .withSampler("SceneSampler")
            .withSampler("DepthSampler")
            .withSampler("PaintSampler");
         if (var2) {
            var10.withShaderDefine("LIVING").withSampler("TerrainDepthSampler").withSampler("OpaqueDepthSampler");
         }

         if (var4) {
            var10.withShaderDefine("MASKED").withSampler("HandMaskSampler");
         }

         RenderPipeline var11 = var10.withoutBlend()
            .withDepthWrite(false)
            .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
            .withCull(false)
            .withVertexFormat(class_290.field_60033, class_5596.field_27379)
            .build();
         e[var5] = var11;
         return var11;
      }
   }

   static void setTiming(boolean var0) {
      o = var0;
      N = 0.0;
      y = 0;
   }

   static void keepDepth(boolean var0) {
      long var1 = 20712454981836L;
      long var3 = var1 ^ 42416058612535L;
      class_276 var5 = class_310.method_1551().method_1522();
      GpuTexture var6 = var5.method_30278();
      if (var6 != null) {
         GpuDevice var7 = RenderSystem.getDevice();
         f(var7, var3, var5.field_1482, var5.field_1481);
         var7.createCommandEncoder().copyTextureToTexture(var6, var0 ? h : J, 0, 0, 0, 0, 0, var5.field_1482, var5.field_1481);
         if (var0) {
            B = true;
         } else {
            H = true;
         }
      }
   }

   static void draw(class_310 var0) {
      long var1 = 67953168807093L;
      long var3 = var1 ^ 10316828049318L;
      int var5 = (int)((var1 ^ 10316828049318L) >>> 32);
      int var6 = (int)((var1 ^ 10316828049318L) << 32 >>> 48);
      int var7 = (int)(var3 << 48 >>> 48);
      var3 = var1 ^ 135363809969741L;
      long var8 = var1 ^ 8224049332324L;
      long var10 = var1 ^ 64119135281434L;
      var3 = var1 ^ 29474044459306L;
      int var12 = (int)((var1 ^ 29474044459306L) >>> 48);
      int var13 = (int)((var1 ^ 29474044459306L) << 16 >>> 48);
      int var14 = (int)(var3 << 32 >>> 32);
      long var15 = var1 ^ 10480406120270L;
      long var17 = var1 ^ 31126303006179L;
      boolean var19 = H && B && ChromaRenderer2.E.p.enabled();
      B = false;
      H = false;
      class_276 var20 = var0.method_1522();
      GpuTexture var21 = var20.method_30277();
      GpuTextureView var22 = var20.method_71639();
      GpuTextureView var23 = var20.method_71640();
      if (var21 != null && var22 != null && var23 != null) {
         GpuDevice var24 = RenderSystem.getDevice();
         GpuTextureView var25 = ChromaRenderer2.s ? ClientSupport3.handMask() : null;
         boolean var26 = var25 != null;
         RenderPipeline var28 = j(var5, (char)var6, var19, (short)var7, var26);
         if (var24.precompilePipeline(var28).isValid()) {
            int var29 = var20.field_1482;
            int var30 = var20.field_1481;
            T(var24, var10);
            f(var24, var15, var29, var30);
            CommandEncoder var31 = var24.createCommandEncoder();
            d(var31, var8);
            var31.copyTextureToTexture(var21, R, 0, 0, 0, 0, 0, var29, var30);
            var31.writeToBuffer(x.slice(), n(var29, var30, var17, var19));
            class_12137 var32 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            GpuQuery var33 = o ? q((char)var12, (short)var13, var14, var31) : null;

            try {
               RenderPass var34 = var31.createRenderPass(() -> "Purxiy chroma", var22, OptionalInt.empty());

               try {
                  var34.setPipeline(var28);
                  RenderSystem.bindDefaultUniforms(var34);
                  var34.setUniform("PurxiyChroma", x);
                  var34.bindTexture("SceneSampler", D, var32);
                  var34.bindTexture("DepthSampler", var23, var32);
                  var34.bindTexture("PaintSampler", L, var32);
                  if (var19) {
                     var34.bindTexture("TerrainDepthSampler", g, var32);
                     var34.bindTexture("OpaqueDepthSampler", i, var32);
                  }

                  if (var25 != null) {
                     var34.bindTexture("HandMaskSampler", var25, var32);
                  }

                  var34.draw(0, 3);
                  ChromaRenderer2.metric("drawn");
               } catch (Throwable var41) {
                  if (var34 != null) {
                     try {
                        var34.close();
                     } catch (Throwable var40) {
                        var41.addSuppressed(var40);
                     }
                  }

                  throw var41;
               }

               if (var34 != null) {
                  var34.close();
               }
            } finally {
               if (var33 != null) {
                  var31.timerQueryEnd(var33);
                  Y = var33;
               }
            }
         }
      }
   }

   private static void d(CommandEncoder var0, long var1) {
      ByteBuffer var3 = I;
      ChromaRenderer2.v.upload((var2, var3xx, var4) -> {
         ChromaCodec.ensureSourceClassInitialized();
         long var5 = 41211718623906L;
         int var8 = var5 < 0L ? -1 : 1;
         var3.clear();
         long var8x = 41211718623906L;
         int var10 = var8x < 0L ? -1 : 1;
         if (var4 == null) {
            MemoryUtil.memSet(MemoryUtil.memAddress0(var3), 0, var3.capacity());
            long var10x = 41211718623906L;
            int var11 = var10x < 0L ? -1 : 1;
         } else {
            var3.put(var4);
            var3.clear();
         }

         var0.writeToTexture(u, var3, class_1012.field_4997, 0, 0, var2, var3xx, 32, 32);
      }, Integer.MAX_VALUE);
   }

   private static ByteBuffer n(int var0, int var1, long var2, boolean var4) {
      long var5 = 85180796031761L;
      long var7 = 25740197047668L;
      short var9 = 21073;
      int var10 = 468279301;
      ChromaCodec.ensureSourceClassInitialized();
      ChromaModule var11 = ChromaRenderer2.E;
      ChromaKind var12 = ChromaKind.of((String)var11.Z.get());
      int var13 = var12.Z;
      int var14 = var12.V;
      int var15 = var12.r;
      long var16 = 36845388245334L;
      int var19 = var16 < 0L ? -1 : 1;
      if (ChromaRenderer2.L != null) {
         var13 = ChromaRenderer2.L[0];
         var14 = ChromaRenderer2.L[1];
         var15 = ChromaRenderer2.L[2];
      }

      double var19x = ChromaRenderer2.z;
      short var21 = 4802;
      double var22 = ChromaRenderer2.P;
      double var24 = ChromaRenderer2.O;
      int var26 = (int)Math.floor(var19x);
      int var27 = (int)Math.floor(var22);
      int var28 = (int)Math.floor(var24);
      short var29 = 512;
      Std140Builder var30 = Std140Builder.intoBuffer(A.clear()).putMat4f(ChromaRenderer2.A);
      float var31 = var0;
      float var32 = var1;
      long var33 = 36845388245333L;
      int var36 = var33 < 0L ? -1 : 1;
      Std140Builder var36x = var30.putVec4(var31, var32, 2.0F / (ChromaRenderer2.t * var1), (float)(ChromaRenderer2.k % 3600.0))
         .putVec4((float)(var19x - var26), (float)(var22 - var27), (float)(var24 - var28), (float)var22)
         .putIVec4(var26, var27, var28, 0)
         .putIVec4(var26 & var29 - 1, var28 & var29 - 1, ChromaRenderer2.v.windowX() - var26, ChromaRenderer2.v.windowZ() - var28);
      int var18x = Math.floorMod(ChromaRenderer2.tick(), 65534);
      int var37 = ChromaRenderer2.v.fade();
      int var35 = Math.min(ChromaRenderer2.C.size(), 64);
      ChromaRenderer2.ensureSourceClassInitialized();
      Std140Builder var38 = var36x.putIVec4(var18x, var37, var35, 0)
         .putVec4(y(var5, var13), k(var13, var7), x(var13, var9, var10), var11.f.floatValue())
         .putVec4(y(var5, var14), k(var14, var7), x(var14, var9, var10), 1.0F)
         .putVec4(y(var5, var15), k(var15, var7), x(var15, var9, var10), Math.max(ChromaRenderer2.g, var11.f.floatValue()))
         .putVec4(ChromaRenderer2.S, var4 ? 1.0F : 0.0F, 0.0F, 0.0F);
      double var39 = ChromaRenderer2.k;
      int var41 = 0;

      for (ChromaRenderer2.TypeR var43 : ChromaRenderer2.C) {
         long var44 = 36845388245333L;
         int var46 = var44 < 0L ? -1 : 1;
         long var46x = 36845388245333L;
         int var48 = var46x < 0L ? -1 : 1;
         long var48x = 36845388245333L;
         int var50 = var48x < 0L ? -1 : 1;
         if (var41 >= 64) {
            long var50x = 36845388245334L;
            int var52 = var50x < 0L ? -1 : 1;
            break;
         }

         var38.putVec4((float)(var43.X - var19x), (float)(var43.q - var22), (float)(var43.W - var24), var43.radiusAt(var39))
            .putVec4(var43.wetAt(var39), 0.0F, 0.0F, 0.0F);
         var41++;
      }

      while (true) {
         long var52 = 36845388245333L;
         int var56 = var52 < 0L ? -1 : 1;
         if (var41 >= 64) {
            return var38.get();
         }

         long var53 = 36845388245333L;
         int var59 = var53 < 0L ? -1 : 1;
         var38.putVec4(0.0F, 0.0F, 0.0F, 0.0F).putVec4(0.0F, 0.0F, 0.0F, 0.0F);
         var41++;
      }
   }

   private static void T(GpuDevice var0, long var1) {
      ChromaCodec.ensureSourceClassInitialized();
      if (x == null) {
         x = var0.createBuffer(ClientServices.label(() -> "Purxiy chroma"), 136, 2272L);
         A = MemoryUtil.memAlloc(2272);
         I = MemoryUtil.memAlloc(4096);
         u = var0.createTexture(() -> "Purxiy chroma paint", 5, TextureFormat.RGBA8, 512, 512, 1, 1);
         L = var0.createTextureView(u);
         ChromaRenderer2.v.resend();
      }
   }

   private static void f(GpuDevice var0, long var1, int var3, int var4) {
      ChromaCodec.ensureSourceClassInitialized();
      if (R != null) {
         long var5 = 57474121905147L;
         int var7 = var5 < 0L ? -1 : 1;
         if (f == var3 && Q == var4) {
            return;
         }
      }

      if (R != null) {
         D.close();
         R.close();
         g.close();
         J.close();
         i.close();
         h.close();
      }

      R = var0.createTexture(() -> "Purxiy chroma scene", 5, TextureFormat.RGBA8, var3, var4, 1, 1);
      D = var0.createTextureView(R);
      J = var0.createTexture(() -> "Purxiy chroma terrain depth", 5, TextureFormat.DEPTH32, var3, var4, 1, 1);
      g = var0.createTextureView(J);
      h = var0.createTexture(() -> "Purxiy chroma opaque depth", 5, TextureFormat.DEPTH32, var3, var4, 1, 1);
      i = var0.createTextureView(h);
      f = var3;
      Q = var4;
      B = false;
      H = false;
   }

   private static float y(long var0, int var2) {
      return (var2 >>> 16 & 0xFF) / 255.0F;
   }

   private static float k(int var0, long var1) {
      return (var0 >>> 8 & 0xFF) / 255.0F;
   }

   private static float x(int var0, int var1, int var2) {
      return (var0 & 0xFF) / 255.0F;
   }

   private static GpuQuery q(char var0, short var1, int var2, CommandEncoder var3) {
      ChromaCodec.ensureSourceClassInitialized();
      if (Y != null) {
         OptionalLong var4 = Y.getValue();
         if (var4.isEmpty()) {
            return null;
         }

         Y.close();
         Y = null;
         N = N + var4.getAsLong() / 1000000.0;
         if (++y == 240) {
            ClientServices.LOGGER
               .info(
                  "Purxiy chroma: {} ms GPU per frame ({} blooms, {} regions)",
                  new Object[]{String.format(Locale.ROOT, "%.3f", N / y), ChromaRenderer2.C.size(), ChromaRenderer2.v.regionCount()}
               );
            N = 0.0;
            y = 0;
         }
      }

      return var3.timerQueryBegin();
   }

   public static void releaseResources() {
      int var0 = 0;

      for (int var1 = 0; var1 < e.length; var1++) {
         try {
            if (e[var1] != null) {
               ClientCache.release(e[var1]);
            }
         } catch (Exception var378) {
            var0++;
         } finally {
            e[var1] = null;
         }
      }

      try {
         if (L != null) {
            L.close();
         }
      } catch (Exception var376) {
         var0++;
      } finally {
         L = null;
      }

      try {
         if (D != null) {
            D.close();
         }
      } catch (Exception var374) {
         var0++;
      } finally {
         D = null;
      }

      try {
         if (g != null) {
            g.close();
         }
      } catch (Exception var372) {
         var0++;
      } finally {
         g = null;
      }

      try {
         if (i != null) {
            i.close();
         }
      } catch (Exception var370) {
         var0++;
      } finally {
         i = null;
      }

      try {
         if (Y != null) {
            Y.close();
         }
      } catch (Exception var368) {
         var0++;
      } finally {
         Y = null;
      }

      try {
         if (x != null) {
            x.close();
         }
      } catch (Exception var366) {
         var0++;
      } finally {
         x = null;
      }

      try {
         if (u != null) {
            u.close();
         }
      } catch (Exception var364) {
         var0++;
      } finally {
         u = null;
      }

      try {
         if (R != null) {
            R.close();
         }
      } catch (Exception var362) {
         var0++;
      } finally {
         R = null;
      }

      try {
         if (J != null) {
            J.close();
         }
      } catch (Exception var360) {
         var0++;
      } finally {
         J = null;
      }

      try {
         if (h != null) {
            h.close();
         }
      } catch (Exception var358) {
         var0++;
      } finally {
         h = null;
      }

      try {
         if (A != null) {
            MemoryUtil.memFree(A);
         }
      } catch (Exception var356) {
         var0++;
      } finally {
         A = null;
      }

      try {
         if (I != null) {
            MemoryUtil.memFree(I);
         }
      } catch (Exception var3541) {
         var0++;
      } finally {
         I = null;
      }

      if (var0 != 0) {
         throw new IllegalStateException("GPU resources did not close");
      }
   }
}
