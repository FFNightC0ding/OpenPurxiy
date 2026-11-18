// Sourced By DevFFNightmare
package dev.purxiy.modules.cape;

import com.mojang.blaze3d.buffers.GpuBuffer;
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
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import net.minecraft.class_1011;
import net.minecraft.class_1044;
import net.minecraft.class_10789;
import net.minecraft.class_12137;
import net.minecraft.class_1799;
import net.minecraft.class_290;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_3298;
import net.minecraft.class_8685;
import net.minecraft.class_9334;
import net.minecraft.class_12079.class_10726;
import net.minecraft.class_12079.class_12081;

import org.lwjgl.system.MemoryUtil;

public final class CapeCache {
   static final int t = 16;
   private static final int k = 1024;
   private static final int E = 512;
   private static final int sU = 5;
   private static final int sp = 4;
   private static final long a = 16666666L;
   static final int h = 128;
   static final int z = 205;
   private static final float q = 1.5F;
   private static final double p = 4096.0;
   public static final class_2960 J = ClientServices.id("cape/live");
   public static final class_2960 l = ClientServices.id("cape/glow");
   private static final class_12081 v = new class_10726(ClientServices.id("cape"), J);
   private static final class_12081 sJ = new class_10726(class_2960.method_60656("entity/equipment/wings/elytra"));
   private static final class_2960 s = sJ.comp_3627();
   private static final RenderPipeline W = RenderPipeline.builder()
      .withLocation(ClientServices.id("pipeline/cape"))
      .withVertexShader(ClientServices.id("core/cape"))
      .withFragmentShader(ClientServices.id("core/cape"))
      .withUniform("PurxiyCape", class_10789.field_60031)
      .withSampler("MarkSampler")
      .withSampler("ElytraSampler")
      .withoutBlend()
      .withDepthWrite(false)
      .withDepthTestFunction(DepthTestFunction.NO_DEPTH_TEST)
      .withCull(false)
      .withVertexFormat(class_290.field_60033, class_5596.field_27379)
      .build();
   private static final int S = 9;
   private static final int sr = 10;
   private static final int R = 48;
   
   private static CapeCache.TypeW b;
   
   private static CapeCache.TypeW d;
   
   private static GpuTexture sA;
   
   private static GpuTexture e;
   
   private static GpuTexture N;
   
   private static GpuTextureView C;
   
   private static GpuTextureView L;
   
   private static GpuTextureView K;
   
   private static class_12137 P;
   
   private static GpuBuffer Z;
   
   private static ByteBuffer f;
   private static int Y = 0;
   private static boolean x;
   
   private static String A;
   private static double j;
   private static long n = 0L;
   private static long c = 0L;
   private static long o = 0L;
   private static long Q = 0L;
   private static long G = -1L;
   private static long T = -1L;
   
   private static CapeDesign r;
   private static long V = 0L;
   
   private static CapeDesign D;
   private static long i = 0L;
   private static double y = -1.0;
   private static float O;
   private static boolean B;
   
   private static GpuQuery H;
   private static double sn;
   private static int I = 0;
   private static long g = 0L;
   private static long u = 0L;
   private static long w = 0L;
   static CapeModule X;
   private static ModuleContext U;
   private static int M = 0;
   private static int F = 0;
   public static boolean m;




   private static final long[] eb = new long[]{8618250700340156104L, 4746642308812072652L, 1209155884083794299L};
   private static final Long[] fb = new Long[3];

   private CapeCache() {
   }

   public static class_8685 dress(class_8685 var0) {
      return X.enabled() ? wear(var0) : var0;
   }

   public static class_8685 wear(class_8685 var0) {
      long var1 = 86581452289363L;
      long var3 = var1 ^ 90815496620613L;
      if (!Y(var3)) {
         return var0;
      } else {
         c = System.nanoTime();
         class_12081 var5 = null;
         if (!X.F.enabled()) {
            var5 = var0.comp_1628() != null ? var0.comp_1628() : (var0.comp_1627() != null && var0.comp_1627() != v ? var0.comp_1627() : sJ);
         }

         return new class_8685(var0.comp_1626(), v, var5, var0.comp_1629(), var0.comp_1630());
      }
   }

   public static boolean wears( class_8685 var0) {
      return var0 != null && var0.comp_1627() == v;
   }

   public static void chest(class_1799 var0) {
      if (!var0.method_7960() && var0.method_57826(class_9334.field_54197) && X.F.enabled()) {
         o = System.nanoTime();
      }
   }

   public static void frame() {
      long var0 = 53927417461766L;
      long var2 = var0 ^ 83700514271260L;
      V++;
      long var4 = System.nanoTime();
      float var6 = n == 0L ? 0.0F : Math.min((float)(var4 - n) / 1.0E9F, 0.1F);
      n = var4;
      CapeModule var7 = X;
      boolean var8 = var4 < i;
      if (!x && (var7.enabled() || var8)) {
         j = (j + var6 * var7.H.floatValue()) % 4096.0;
         boolean var9 = (float)(var4 - c) / 1.0E9F < 1.5F || var8;
         if (var9 && b != null && var4 - Q >= 15666666L) {
            x(var2);
         }
      }
   }

   public static void preview( CapeDesign var0) {
      long var1 = 74674835944227L;
      long var3 = var1 ^ 69000410562361L;
      long var5 = var1 ^ 105333650246709L;
      D = var0;
      i = System.nanoTime() + 250000000L;
      c = System.nanoTime();
      if (Y(var5) && (G != V || showing() != r)) {
         G = -1L;
         x(var3);
      }
   }

   public static CapeDesign showing() {
      return D != null && System.nanoTime() < i ? D : X.selected();
   }

   
   public static GpuTextureView swatches() {
      long var0 = 114667820948617L;
      long var2 = var0 ^ 31436636755141L;
      int var4 = (int)((var0 ^ 31436636755141L) >>> 48);
      int var5 = (int)((var0 ^ 31436636755141L) << 16 >>> 32);
      int var6 = (int)(var2 << 48 >>> 48);
      long var7 = (var0 ^ 17975565384263L) >>> 16;
      int var9 = (int)((var0 ^ 17975565384263L) << 48 >>> 48);
      long var10 = var0 ^ 128008805655455L;
      if (!Y(var10)) {
         return null;
      } else {
         if (T != V) {
            T = V;

            try {
               t(var7, (char)var9);
            } catch (RuntimeException var13) {
               X(var13, (short)var4, var5, var6);
               return null;
            }
         }

         return C;
      }
   }

   public static class_12137 swatchSampler() {
      return RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR);
   }

   public static class_12137 sampler() {
      return P != null ? P : RenderSystem.getSamplerCache().method_76520(FilterMode.LINEAR, true);
   }

   public static void release() {
      class_310 var0 = class_310.method_1551();
      if (b != null || d != null) {
         var0.method_1531().method_4615(J);
         var0.method_1531().method_4615(l);
         b = null;
         d = null;
      }

      if (C != null) {
         C.close();
         sA.close();
         C = null;
         sA = null;
      }

      if (K != null) {
         K.close();
         N.close();
         K = null;
         N = null;
      }

      G = -1L;
   }

   private static boolean Y(long var0) {
      long var2 = 128493730728012L;
      byte var4 = 0;
      int var5 = 1960658733;
      int var6 = (int)(var2 << 48 >>> 48);
      long var7 = 113112922174605L;
      long var9 = 137627930201114L;
      long var11 = 929335707L;
      short var13 = 22369;
      CapeModule.ensureSourceClassInitialized();
      if (x) {
         return false;
      } else if (b != null) {
         return true;
      } else {
         try {
            GpuDevice var14 = RenderSystem.getDevice();
            long var15 = 30951444823829L;
            if (30951444823829L == 0L) {
               boolean var10000 = false;
            } else {
               int var35 = var15 < 0L ? -1 : 1;
            }

            boolean var18 = var14.precompilePipeline(W).isValid();
            long var19 = 30951444823829L;
            if (30951444823829L == 0L) {
               boolean var36 = false;
            } else {
               int var37 = var19 < 0L ? -1 : 1;
            }

            if (!var18) {
               x = true;
               ClientServices.LOGGER.error("Purxiy cape: the cape shader did not compile; capes stay as they are");
               return false;
            } else {
               if (P == null) {
                  int var22 = Math.max(1, Math.min(16, var14.getMaxSupportedAnisotropy()));
                  P = var14.createSampler(
                     AddressMode.CLAMP_TO_EDGE, AddressMode.CLAMP_TO_EDGE, FilterMode.LINEAR, FilterMode.LINEAR, var22, OptionalDouble.empty()
                  );
               }

               if (e == null) {
                  try (InputStream var33 = resource("assets/purxiy/textures/cape/mark_sdf.png")) {
                     long var23 = 30951444823830L;
                     if (30951444823830L == 0L) {
                        boolean var38 = false;
                     } else {
                        int var39 = var23 < 0L ? -1 : 1;
                     }

                     try (class_1011 var25 = class_1011.method_4309(var33)) {
                        e = var14.createTexture(() -> "Purxiy cape mark", 5, TextureFormat.RGBA8, var25.method_4307(), var25.method_4323(), 1, 1);
                        var14.createCommandEncoder().writeToTexture(e, var25);
                        L = var14.createTextureView(e);
                     }
                  }
               }

               if (N == null) {
                  p(var11, (char)var13, var14);
               }

               if (Z == null) {
                  Y = Math.max(48, U(var7, 48, var14.getUniformOffsetAlignment()));
                  Z = var14.createBuffer(ClientServices.label(() -> "Purxiy cape"), 136, Y * 10);
                  f = MemoryUtil.memAlloc(Y * 10);
               }

               class_310 var34 = class_310.method_1551();
               b = new CapeCache.TypeW("Purxiy cape", 1, 5, P);
               d = new CapeCache.TypeW("Purxiy cape glow", 2, 4, P);
               var34.method_1531().method_4616(J, b);
               var34.method_1531().method_4616(l, d);
               x(var9);
               return true;
            }
         } catch (Exception var32) {
            X(var32, var4, var5, var6);
            x = true;
            return false;
         }
      }
   }

   private static void p(long var0, char var2, GpuDevice var3) {
      CapeModule.ensureSourceClassInitialized();
      class_1011 var4 = null;

      try {
         Optional var5 = class_310.method_1551().method_1478().method_14486(s);
         if (var5.isPresent()) {
            try (InputStream var6 = ((class_3298)var5.get()).method_14482()) {
               var4 = class_1011.method_4309(var6);
            }
         }
      } catch (Exception var21) {
         ClientServices.LOGGER.warn("Purxiy cape: could not read the elytra texture, the wings stay rectangular", var21);
      }

      label123: {
         short var22 = 22369;
         if (var4 != null) {
            long var24 = 929335707L;
            if (929335707L == 0L) {
               boolean var10000 = false;
            } else {
               int var26 = var24 < 0L ? -1 : 1;
            }

            if (var4.method_4307() * 32 == var4.method_4323() * 64) {
               if (var4.method_4307() != 64) {
                  class_1011 var8 = new class_1011(64, 32, true);
                  int var9 = var4.method_4307() / 64;

                  for (int var10 = 0; var10 < 32; var10++) {
                     long var11 = 929335706L;
                     if (929335706L == 0L) {
                        boolean var27 = false;
                     } else {
                        int var28 = var11 < 0L ? -1 : 1;
                     }

                     for (int var14 = 0; var14 < 64; var14++) {
                        long var15 = 929335707L;
                        if (929335707L == 0L) {
                           boolean var29 = false;
                        } else {
                           int var30 = var15 < 0L ? -1 : 1;
                        }

                        var8.method_61941(var14, var10, var4.method_61940(var14 * var9 + var9 / 2, var10 * var9 + var9 / 2));
                     }
                  }

                  long var25 = 929335706L;
                  if (929335706L == 0L) {
                     boolean var31 = false;
                  } else {
                     int var32 = var25 < 0L ? -1 : 1;
                  }

                  var4.close();
                  var4 = var8;
               }
               break label123;
            }
         }

         if (var4 != null) {
            var4.close();
         }

         var4 = new class_1011(64, 32, true);
         var4.method_4326(22, 0, 24, 22, -1);
      }

      class_1011 var23 = var4;

      try {
         N = var3.createTexture(() -> "Purxiy cape elytra", 5, TextureFormat.RGBA8, 64, 32, 1, 1);
         var3.createCommandEncoder().writeToTexture(N, var23);
         K = var3.createTextureView(N);
      } catch (Throwable var19) {
         if (var4 != null) {
            try {
               var23.close();
            } catch (Throwable var17) {
               var19.addSuppressed(var17);
            }
         }

         throw var19;
      }

      if (var4 != null) {
         var4.close();
      }
   }

   private static void x(long var0) {
      long var2 = 100954746890152L;
      var2 = 128493730728012L;
      byte var4 = 0;
      int var5 = 1960658733;
      int var6 = (int)(var2 << 48 >>> 48);
      long var7 = 47881293385128L;
      CapeModule.ensureSourceClassInitialized();
      if (b != null && d != null) {
         long var9 = 137627930201113L;
         if (137627930201113L == 0L) {
            boolean var10000 = false;
         } else {
            int var60 = var9 < 0L ? -1 : 1;
         }

         if (G != V) {
            G = V;
            Q = System.nanoTime();
            g++;

            try {
               CapeModule var12 = X;
               CapeDesign var13 = showing();
               r = var13;
               float var14 = (float)(y >= 0.0 ? y : j);
               boolean var15 = var12.F.enabled();
               long var16 = 137627930201114L;
               if (137627930201114L == 0L) {
                  boolean var61 = false;
               } else {
                  int var62 = var16 < 0L ? -1 : 1;
               }

               boolean var18 = var15 && (float)(System.nanoTime() - o) / 1.0E9F < 1.5F;
               float var19 = var12.e.floatValue();
               Std140Builder var20 = Std140Builder.intoBuffer(f.clear());

               for (int var21 = 0; var21 < 9; var21++) {
                  long var22 = 137627930201114L;
                  if (137627930201114L == 0L) {
                     boolean var63 = false;
                  } else {
                     int var64 = var22 < 0L ? -1 : 1;
                  }

                  long var25 = 137627930201113L;
                  if (137627930201113L == 0L) {
                     boolean var65 = false;
                  } else {
                     int var66 = var25 < 0L ? -1 : 1;
                  }

                  boolean var27 = var21 >= 5;
                  CapeCache.TypeW var28 = var27 ? d : b;
                  int var29 = var27 ? var21 - 5 : var21;
                  f.position(var21 * Y);
                  var20.putVec4(var14, var13.ordinal(), var27 ? 1.0F : 0.0F, var28.texel(var29))
                     .putVec4(0.0F, 0.0F, var18 ? 0.0F : 1.0F, 0.0F)
                     .putVec4(var19, 0.0F, 0.0F, 0.0F);
               }

               long var55 = 137627930201113L;
               if (137627930201113L == 0L) {
                  boolean var67 = false;
               } else {
                  int var68 = var55 < 0L ? -1 : 1;
               }

               f.position(0).limit(9 * Y);
               CommandEncoder var23 = RenderSystem.getDevice().createCommandEncoder();
               long var24 = 137627930201114L;
               if (137627930201114L == 0L) {
                  boolean var69 = false;
               } else {
                  int var70 = var24 < 0L ? -1 : 1;
               }

               var23.writeToBuffer(Z.slice(0L, 9 * Y), f);
               f.limit(f.capacity());
               boolean var26 = false;
               Object var56 = null;
               boolean var57 = false;

               try {
                  var57 = true;

                  for (int var58 = 0; var58 < 9; var58++) {
                     long var30 = 137627930201114L;
                     if (137627930201114L == 0L) {
                        boolean var71 = false;
                     } else {
                        int var72 = var30 < 0L ? -1 : 1;
                     }

                     long var33 = 137627930201113L;
                     if (137627930201113L == 0L) {
                        boolean var73 = false;
                     } else {
                        int var74 = var33 < 0L ? -1 : 1;
                     }

                     boolean var35 = var58 >= 5;
                     CapeCache.TypeW var36 = var35 ? d : b;
                     int var37 = var35 ? var58 - 5 : var58;

                     try (RenderPass var38 = var23.createRenderPass(() -> "Purxiy cape", var36.a[var37], OptionalInt.of(0))) {
                        var38.setPipeline(W);
                        var38.setUniform("PurxiyCape", Z.slice(var58 * Y, 48L));
                        long var39 = 137627930201113L;
                        if (137627930201113L == 0L) {
                           boolean var75 = false;
                        } else {
                           int var76 = var39 < 0L ? -1 : 1;
                        }

                        R(var7, var38);
                        long var42 = 137627930201114L;
                        if (137627930201114L == 0L) {
                           boolean var77 = false;
                        } else {
                           int var78 = var42 < 0L ? -1 : 1;
                        }

                        var38.draw(0, 6);
                        metric("drawn");
                     }
                  }

                  var57 = false;
               } finally {
                  if (var57 && var56 != null) {
                     var23.timerQueryEnd((GpuQuery)var56);
                     H = (GpuQuery)var56;
                  }
               }

               long var59 = 137627930201113L;
               if (137627930201113L == 0L) {
                  boolean var79 = false;
               } else {
                  int var80 = var59 < 0L ? -1 : 1;
               }

               if (var56 != null) {
                  var23.timerQueryEnd((GpuQuery)var56);
                  H = (GpuQuery)var56;
               }

               A = null;
            } catch (RuntimeException var53) {
               X(var53, var4, var5, var6);
            }

            return;
         }
      }
   }

   private static void t(long var0, char var2) {
      long var3 = 132024837629646L;
      long var5 = var3 ^ 91943823977318L;
      CapeModule.ensureSourceClassInitialized();
      GpuDevice var7 = RenderSystem.getDevice();
      int var8 = CapeDesign.values().length;
      int var9 = 26318;
      if (sA == null) {
         sA = var7.createTexture(() -> "Purxiy cape swatches", 12, TextureFormat.RGBA8, 128 * var8, 205, 1, 1);
         C = var7.createTextureView(sA);
      }

      float var10 = (float)(y >= 0.0 ? y : j);
      byte var11 = 9;
      Std140Builder var12 = Std140Builder.intoBuffer(f.clear());
      f.position(var11 * Y);
      var12.putVec4(var10, 0.0F, 0.0F, 0.078125F).putVec4(var8, var8, 1.0F, 0.0F).putVec4(X.e.floatValue(), 0.0F, 0.0F, 0.0F);
      f.position(var11 * Y).limit(var11 * Y + 48);
      CommandEncoder var13 = var7.createCommandEncoder();
      var13.writeToBuffer(Z.slice(var11 * Y, 48L), f);
      f.clear();

      try (RenderPass var14 = var13.createRenderPass(() -> "Purxiy cape swatches", C, OptionalInt.of(0))) {
         var14.setPipeline(W);
         long var15 = 2014539147L;
         if (2014539147L == 0L) {
            boolean var10000 = false;
         } else {
            int var23 = var15 < 0L ? -1 : 1;
         }

         long var18 = 2014539147L;
         if (2014539147L == 0L) {
            boolean var24 = false;
         } else {
            int var25 = var18 < 0L ? -1 : 1;
         }

         var14.setUniform("PurxiyCape", Z.slice(var11 * Y, 48L));
         int var17 = -1837911529;
         R(var5, var14);
         var9 = 1837907168;
         var14.draw(0, var8 * 6);
         metric("drawn");
      }
   }

   private static void R(long var0, RenderPass var2) {
      var2.bindTexture("MarkSampler", L, RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR));
      var2.bindTexture("ElytraSampler", K, RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST));
   }

   private static int U(long var0, int var2, int var3) {
      CapeModule.ensureSourceClassInitialized();
      long var4 = 113112922174604L;
      if (113112922174604L == 0L) {
         boolean var10000 = false;
      } else {
         int var9 = var4 < 0L ? -1 : 1;
      }

      long var7 = 113112922174604L;
      if (113112922174604L == 0L) {
         boolean var10 = false;
      } else {
         int var11 = var7 < 0L ? -1 : 1;
      }

      return var3 <= 1 ? 48 : (48 + var3 - 1) / var3 * var3;
   }

   private static void X(Exception var0, short var1, int var2, int var3) {
      CapeModule.ensureSourceClassInitialized();
      String var4 = var0.toString();
      if (!var4.equals(A)) {
         A = var4;
         ClientServices.LOGGER.error("Purxiy cape could not draw", var0);
      }
   }

   
   private static GpuQuery W(char var0, short var1, int var2, CommandEncoder var3) {
      CapeModule.ensureSourceClassInitialized();
      if (H != null) {
         OptionalLong var4 = H.getValue();
         short var5 = 23505;
         if (var4.isEmpty()) {
            return null;
         }

         H.close();
         H = null;
         sn = sn + var4.getAsLong() / 1000000.0;
         int var6 = I + 1;
         var5 = 23505;
         I = var6;
         if (var6 == 120) {
            ClientServices.LOGGER.info("Purxiy cape: {} ms GPU per frame ({})", String.format(Locale.ROOT, "%.3f", sn / I), showing().E);
            sn = 0.0;
            I = 0;
         }
      }

      return var3.timerQueryBegin();
   }

   public static float devOrbit() {
      return O;
   }

   static InputStream resource(String var0) throws IOException {
      return U.resource(var0);
   }

   public static void install(CapeModule var0, ModuleContext var1) {
      X = var0;
      U = var1;
   }

   public static void metric(String var0) {
      if (U != null) {
         U.metric(var0, var0.equals("drawn") ? ++M : ++F);
      }
   }

   public static void uninstall() {
      int var0 = 0;

      try {
         if (W != null) {
            ClientCache.release(W);
         }
      } catch (Exception var230) {
         var0++;
      } finally {
         ;
      }

      try {
         if (C != null) {
            C.close();
         }
      } catch (Exception var228) {
         var0++;
      } finally {
         C = null;
      }

      try {
         if (L != null) {
            L.close();
         }
      } catch (Exception var226) {
         var0++;
      } finally {
         L = null;
      }

      try {
         if (K != null) {
            K.close();
         }
      } catch (Exception var224) {
         var0++;
      } finally {
         K = null;
      }

      try {
         if (H != null) {
            H.close();
         }
      } catch (Exception var222) {
         var0++;
      } finally {
         H = null;
      }

      try {
         if (Z != null) {
            Z.close();
         }
      } catch (Exception var220) {
         var0++;
      } finally {
         Z = null;
      }

      try {
         if (sA != null) {
            sA.close();
         }
      } catch (Exception var218) {
         var0++;
      } finally {
         sA = null;
      }

      try {
         if (e != null) {
            e.close();
         }
      } catch (Exception var216) {
         var0++;
      } finally {
         e = null;
      }

      try {
         if (N != null) {
            N.close();
         }
      } catch (Exception var214) {
         var0++;
      } finally {
         N = null;
      }

      try {
         if (f != null) {
            MemoryUtil.memFree(f);
         }
      } catch (Exception var212) {
         var0++;
      } finally {
         f = null;
      }

      release();
      if (P != null) {
         P.close();
         P = null;
      }

      D = null;
      X = null;
      U = null;
      n = 0L;
      if (var0 != 0) {
         throw new IllegalStateException("World visual GPU cleanup failed");
      }
   }

private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 718) & 32767;
      if (fb[var3] == null) {
         fb[var3] = eb[var3] ^ var1;
      }

      return fb[var3];
   }

private static final class TypeW extends class_1044 {
      final GpuTextureView[] a;
      private final int C;

      TypeW(String var1, int var2, int var3, class_12137 var4) {
         this.C = var2;
         this.a = new GpuTextureView[var3];
         GpuDevice var5 = RenderSystem.getDevice();
         this.field_56974 = var5.createTexture(() -> var1, 13, TextureFormat.RGBA8, 1024 / var2, 512 / var2, 1, var3);
         this.field_60597 = var5.createTextureView(this.field_56974);
         this.field_63613 = var4;

         for (int var6 = 0; var6 < var3; var6++) {
            this.a[var6] = var5.createTextureView(this.field_56974, var6, 1);
         }
      }

      float texel(int var1) {
         return (this.C << var1) / 16.0F;
      }

      @Override
      public void close() {
         for (GpuTextureView var4 : this.a) {
            if (var4 != null) {
               var4.close();
            }
         }

         super.close();
      }
   }
}
