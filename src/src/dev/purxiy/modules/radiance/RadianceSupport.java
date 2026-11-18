// Sourced By DevFFNightmare
package dev.purxiy.modules.radiance;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.pipeline.RenderPipeline;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.systems.RenderPass;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.assets.IrisCompat;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.RenderHooks;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.loader.fabric.mixin.ModelPartAccessor;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.render.pipeline.SkyPipelines;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.class_10017;
import net.minecraft.class_11659;
import net.minecraft.class_12137;
import net.minecraft.class_1294;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_1542;
import net.minecraft.class_1569;
import net.minecraft.class_1657;
import net.minecraft.class_1792;
import net.minecraft.class_1799;
import net.minecraft.class_1921;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_3879;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_5636;
import net.minecraft.class_630;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_777;
import net.minecraft.class_630.class_628;
import org.joml.Matrix4f;
import org.joml.Vector3fc;
import org.joml.Vector4f;

import org.lwjgl.system.MemoryUtil;
import org.slf4j.Logger;

public final class RadianceSupport {
   static final int _h = 128;
   private static final int t = 16;
   private static final int _I = 16;
   private static final int _i = 352;
   private static final int q = 8192;
   private static final int _8 = 8;
   private static final int _X = 126;
   private static final int D = 1;
   private static final int _E = 2;
   private static final int W = 3;
   private static final int V = 1006632960;
   private static final float _b = 0.35F;
   private static final float s = 0.6F;
   private static final float _Y = 0.7F;
   private static final float x = 0.25F;
   private static final long Z = 2000000000L;
   private static final int _L = 16740252;
   private static final int[] S = new int[]{0, 16767354, 8840959, 16746220};
   private static final RenderStateDataKey<RadianceSupport.TypeB> _W = RenderStateDataKey.create(ClientServices.label(() -> {
      int var0x = 1417452743;
      int var1x = -1417449729;
      int var2 = 1417477760;
      return "purxiy:radiance";
   }));
   private static final RadianceRenderer _O = new RadianceRenderer("world");
   private static final RadianceRenderer _v = new RadianceRenderer("hand");
   private static final Int2ObjectOpenHashMap<RadianceSupport.TypeK> u = new Int2ObjectOpenHashMap();
   private static final RadianceSupport.TypeK F = new RadianceSupport.TypeK(0.137F);
   private static final RadianceSupport.TypeK _D = new RadianceSupport.TypeK(0.571F);
   private static long o = 0L;
   private static long B = 0L;
   private static float K;
   private static float U;
   private static float f;
   private static float C;
   private static boolean G;
   private static boolean I;
   
   private static class_638 _F;
   private static final Matrix4f Y = new Matrix4f();
   private static final Matrix4f __ = new Matrix4f();
   private static final Matrix4f _4 = new Matrix4f();
   private static final Vector4f T = new Vector4f();
   private static int c = 1;
   private static int k = 1;
   private static float l = 1.0F;
   private static final float[] r = new float[2048];
   private static int _u = 3;
   private static boolean _g;
   private static boolean _H;
   private static long _2 = 0L;
   private static final class_1792[] b = new class_1792[2];
   private static final class_1792[] d = new class_1792[2];
   private static boolean E;
   private static final float[] _P = new float[4];
   private static final float[] N = new float[4];
   private static float H;
   private static float _9;
   private static float _s;
   private static float _p;
   private static float w;
   private static float _K;
   private static float X;
   private static final float[] Q = new float[9];
   private static final float[] A = new float[9];
   private static boolean R;
   private static final RadianceLoader J = new RadianceLoader();
   private static GpuBuffer j;
   private static GpuBuffer p;
   private static GpuBuffer e;
   private static ByteBuffer v;
   private static ByteBuffer g;
   private static ByteBuffer L;
   private static int h = 0;
   private static final float[] y = new float[64];
   private static int a = 0;
   
   private static String _d;
   private static boolean z;
   private static GpuQuery _M;
   private static double _w;
   private static double _o;
   private static int _l = 0;
   private static final Set<class_10017> M = Collections.newSetFromMap(new WeakHashMap<>());
   static RadianceModule n;
   private static ModuleContext O;
   private static int i = 0;
   private static int P = 0;
   public static int m = 0;


   private static final long[] cb = new long[]{
      -7928599769428423244L,
      -2594401658566032657L,
      2239548963359887742L,
      -2622074438629253178L,
      7256483703872222799L,
      283606925260339960L,
      8535777054223587446L,
      -7302630613360874541L,
      -5510290277370392791L,
      4110950822096101589L,
      190092856838050768L,
      -8981522364743538949L,
      2528465254946065151L,
      -7708151970898080414L,
      -7326922225761362483L,
      -2293414762116852219L,
      -778652417902780665L,
      -6663452453317386887L,
      1500578431919018758L,
      -6826088322070769738L,
      -3098728322401041645L,
      -4859715748372553234L,
      -3965737357475450585L,
      6311775032587782483L,
      8951457743794766464L,
      -5571819666563914836L,
      4514825455997897806L,
      644458058747400563L,
      -6033335719296221767L,
      598303550780445976L,
      4355192687077253765L,
      901758279139914808L,
      -9220878313271608943L,
      3883358013646556911L,
      240408397084222226L,
      -5740254162362284728L,
      -9203253379392621910L,
      4586885829999665712L,
      4013857099844783406L
   };
   private static final Integer[] db = new Integer[39];
   private static final long[] eb = new long[]{
      679203524197304303L,
      6352890089508872456L,
      2044973246623055232L,
      4791183241480908521L,
      6156932291312411504L,
      7757069690472701602L,
      6769999954679061588L,
      2152308700762751602L
   };
   private static final Long[] fb = new Long[8];

   private RadianceSupport() {
   }

   public static void beginFrame(Matrix4f var0, Matrix4f var1) {
      long var2 = 102707588115885L;
      long var4 = var2 ^ 58356400132361L;
      int var6 = (int)((var2 ^ 58356400132361L) >>> 48);
      int var7 = (int)((var2 ^ 58356400132361L) << 16 >>> 32);
      int var8 = (int)(var4 << 48 >>> 48);
      var4 = var2 ^ 21626948088874L;
      long var9 = var2 ^ 119807817403758L;
      long var11 = var2 ^ 11602600910178L;
      if (ModuleWidget.claimed("mirror-capture")) {
         I = false;
      } else {
         long var13 = System.nanoTime();
         boolean var15 = o != 0L && var13 - o < 500000000L;
         K = var15 ? Math.min((float)(var13 - o) / 1.0E9F, 0.1F) : 0.0F;
         o = var13;
         B++;
         class_310 var16 = class_310.method_1551();
         RadianceModule var17 = n;
         if (var16.field_1687 != _F) {
            _F = var16.field_1687;
            u.clear();
            F.restart();
            _D.restart();
            R = false;
         }

         float var23;
         if (var15) {
            var23 = f;
            float var19 = var17.enabled() ? 1.0F : 0.0F;
            float var20 = K / 0.35F;
            var23 = F(var23, (char)var6, var19, var7, var20, (short)var8);
         } else {
            var23 = var17.enabled() ? 1.0F : 0.0F;
         }

         f = var23;
         C = f * f * (3.0F - 2.0F * f);
         _O.reset();
         _v.reset();
         _u = 3;
         _g = false;
         _H = false;
         G = C > 0.0F && var16.field_1687 != null && var16.field_1724 != null && !var16.field_1773.method_35765() && l(var11);
         if (!G) {
            R = false;
            I = false;
         } else {
            I = true;
            b(var17, var9);
            U = U + K * var17.u.floatValue();
            if (U > 3600.0F) {
               U -= 3600.0F;
            }

            Y.set(var1).mul(var0);
            class_276 var24 = var16.method_1522();
            c = var24.field_1482;
            k = var24.field_1481;
            l = Math.max(0.75F, k / 1080.0F);
            if (!u.isEmpty()) {
               u.values().removeIf(var2x -> {
                  RadianceModule.ensureSourceClassInitialized();
                  return var13 - var2x.s > 2000000000L;
               });
            }
         }
      }
   }

   public static void endLevel() {
      I = false;
   }

   private static void b(RadianceModule var0, long var1) {
      long var3 = 41280837592712L;
      RadianceModule.ensureSourceClassInitialized();
      RadianceLoader2 var5 = RadianceLoader2.resolve(var0.e.get(), var0.p.argb());
      A(A, 0, var5.q, var3);
      A(A, 3, var5.E, var3);
      A(A, 6, var5.T, var3);
      float var6 = R ? 1.0F - (float)Math.exp(-K * 6.0) : 1.0F;

      for (int var7 = 0; var7 < Q.length; var7++) {
         long var8 = 54561505796290L;
         if (54561505796290L == 0L) {
            boolean var10000 = false;
         } else {
            int var20 = var8 < 0L ? -1 : 1;
         }

         Q[var7] = Q[var7] + (A[var7] - Q[var7]) * var6;
      }

      long var19 = 54561505796290L;
      if (54561505796290L == 0L) {
         boolean var21 = false;
      } else {
         int var22 = var19 < 0L ? -1 : 1;
      }

      String var10 = var0.q.get();
      boolean var11 = var10.equals("Aura");
      boolean var12 = var10.equals("Outline");
      H = H + ((var11 ? 0.5F : 1.0F) - H) * var6;
      float var13 = var11 ? 0.3F : (var12 ? 0.14F : 1.0F);
      long var14 = 54561505796290L;
      if (54561505796290L == 0L) {
         boolean var23 = false;
      } else {
         int var24 = var14 < 0L ? -1 : 1;
      }

      _9 = _9 + (var13 - _9) * var6;
      _s = _s + ((var11 ? 1.0F : 0.0F) - _s) * var6;
      long var17 = 54561505796290L;
      if (54561505796290L == 0L) {
         boolean var25 = false;
      } else {
         int var26 = var17 < 0L ? -1 : 1;
      }

      _p = _p + (var0.i.floatValue() - _p) * var6;
      w = w + (var0.m.floatValue() - w) * var6;
      _K = _K + (var0.j.floatValue() - _K) * var6;
      X = X + ((var0.X.enabled() ? 1.0F : 0.0F) - X) * var6;
      R = true;
   }

   public static void extract(class_1297 var0, class_10017 var1) {
      long var2 = 16400201681349L;
      long var4 = var2 ^ 98090433258966L;
      long var6 = var2 ^ 102063541272913L;
      long var8 = var2 ^ 12598330379806L;
      long var10 = var2 ^ 135577477654810L;
      long var12 = var2 ^ 17462204198085L;
      long var14 = var2 ^ 86285874283661L;
      long var16 = var2 ^ 129654178339641L;
      if (G && I && !var1.field_53333) {
         try {
            RadianceModule var18 = n;
            class_310 var19 = class_310.method_1551();
            if (!y(var0, var8, var18, var19) || _u >= 128) {
               return;
            }

            double var20 = Math.sqrt(var1.field_53332);
            float var22 = var18.O.floatValue();
            if (var20 > var22) {
               return;
            }

            RadianceSupport.TypeK var23 = (RadianceSupport.TypeK)u.get(var0.method_5628());
            long var24 = System.nanoTime();
            if (var23 == null) {
               var23 = new RadianceSupport.TypeK(b(var0.method_5628(), var12));
               u.put(var0.method_5628(), var23);
            }

            var23.s = var24;
            float var26 = 1.0F - c(var22 * 0.8F, var22, (float)var20);
            float var27 = var26 * D(var4, var19, (float)var20);
            if (var0 instanceof class_1309 var28 && var28.method_29504()) {
               var27 *= 1.0F - Math.min(1.0F, var28.field_6213 / 10.0F);
            }

            int var38 = 0;
            float var29 = 0.0F;
            if (var18.Q.enabled() && var0 instanceof class_1309 var30 && var30.method_6063() > 0.0F) {
               float var31 = Math.clamp(var30.method_6032() / var30.method_6063(), 0.0F, 1.0F);
               var38 = 16740252;
               var29 = 0.9F * (1.0F - c(0.3F, 0.95F, var31));
            }

            if (var18.F.enabled() && var0 instanceof class_1542 var39) {
               int var41 = S[var39.method_6983().method_7932().ordinal()];
               if (var41 != 0) {
                  var38 = var41;
                  var29 = 0.8F;
               }
            }

            d(var23, var27, var0 == var19.field_1692, var38, var10, var29);
            int var40 = _u++;
            int var42 = var40 * 16;
            float var32 = Math.max(0.6F, 0.35F * Math.max(var1.field_53329, var1.field_53330));
            float var33 = var1.field_53329 * 0.5F + var32;
            class_243 var34 = var19.field_1773.method_19418().method_71156();
            l(
               Y,
               (float)(var1.field_53325 - var34.field_1352),
               (float)(var1.field_53326 - var34.field_1351),
               var14,
               (float)(var1.field_53327 - var34.field_1350),
               var33,
               -var32,
               var1.field_53330 + var32,
               var42
            );
            float var35 = r[var42 + 3] - r[var42 + 1];
            float var36 = Math.clamp(var35 / (150.0F * l), var0 instanceof class_1542 ? 0.6F : 0.5F, 1.0F);
            k(var42);
            p(var42, var6, var23, var36, (float)var20);
            M.add(var1);
            ((net.fabricmc.fabric.api.client.rendering.v1.FabricRenderState)var1).setData(_W, new RadianceSupport.TypeB(B, var40));
         } catch (Throwable var37) {
            metric("draw_failures");
            c(var16, var37);
         }
      }
   }

   private static boolean y(class_1297 var0, long var1, RadianceModule var3, class_310 var4) {
      RadianceModule.ensureSourceClassInitialized();
      long var5 = 6181518297051L;
      if (6181518297051L == 0L) {
         boolean var10000 = false;
      } else {
         int var21 = var5 < 0L ? -1 : 1;
      }

      boolean var8 = var0 instanceof class_1657;
      long var9 = 6181518297050L;
      if (6181518297050L == 0L) {
         boolean var22 = false;
      } else {
         int var23 = var9 < 0L ? -1 : 1;
      }

      if (!var8) {
         var8 = var0 instanceof class_1542;
         long var12 = 6181518297050L;
         if (6181518297050L == 0L) {
            boolean var24 = false;
         } else {
            int var25 = var12 < 0L ? -1 : 1;
         }

         if (var8) {
            return var3.U.enabled();
         } else if (!(var0 instanceof class_1531) && var0 instanceof class_1309) {
            String var14 = var3.H.get();
            byte var15 = -1;
            switch (var14.hashCode()) {
               case -254928903:
                  if (var14.equals("Monsters")) {
                     long var20 = 6181518297051L;
                     if (6181518297051L == 0L) {
                        boolean var28 = false;
                     } else {
                        int var29 = var20 < 0L ? -1 : 1;
                     }

                     var15 = 0;
                  }
                  break;
               case 65921:
                  var8 = var14.equals("All");
                  long var16 = 6181518297051L;
                  if (6181518297051L == 0L) {
                     boolean var26 = false;
                  } else {
                     int var27 = var16 < 0L ? -1 : 1;
                  }

                  if (var8) {
                     var15 = 1;
                  }
            }
            return switch (var15) {
               case 0 -> var0 instanceof class_1569;
               case 1 -> true;
               default -> false;
            };
         } else {
            return false;
         }
      } else {
         return var3.E.enabled() && (var0 != var4.field_1724 || !var4.field_1690.method_31044().method_31034());
      }
   }

   public static class_11659 collector(class_10017 var0, class_11659 var1) {
      if (ModuleWidget.claimed("mirror-capture")) {
         return var1;
      } else {
         RadianceSupport.TypeB var2 = (RadianceSupport.TypeB)((net.fabricmc.fabric.api.client.rendering.v1.FabricRenderState)var0).getData(_W);
         if (var2 != null && var2.m == B && G && !RenderHooks.dissolving(var0)) {
            int var3 = 1006632960 | var2.s;
            return new Radianceclass_11659(var1, var1, () -> var3);
         } else {
            return var1;
         }
      }
   }

   public static void onDamage( class_1297 var0, int var1, int var2) {
      if (var0 != null && (var1 >= 0 || var2 >= 0) && n.enabled() && n.R.enabled()) {
         class_310 var3 = class_310.method_1551();
         if (var0 == var3.field_1724) {
            s(F);
            s(_D);
         }

         RadianceSupport.TypeK var4 = (RadianceSupport.TypeK)u.get(var0.method_5628());
         if (var4 != null) {
            s(var4);
         }
      }
   }

   private static void s(RadianceSupport.TypeK var0) {
      var0.A = 1.0F;
      var0.r = 0.0F;
   }

   
   public static class_4588 maskBuffer(int var0, class_1921 var1) {
      if ((var0 & 0xFF000000) != 1006632960) {
         return null;
      } else {
         int var2 = var0 & 0xFF;
         class_1921 var3 = var1.method_24295() ? var1 : var1.method_23289().orElse(null);
         return (class_4588)(G && var3 != null && var2 > 0 && var2 < 128 ? (var2 < 3 ? _v : _O).consumer(var2, var3) : RadianceSupport.TypeN.Q);
      }
   }

   public static void drawWorldMask() {
      long var0 = 121478522453270L;
      long var2 = var0 ^ 23617540845546L;
      if (!ModuleWidget.claimed("mirror-capture") && G) {
         try {
            if (_O.isEmpty()) {
               return;
            }

            class_276 var4 = class_310.method_1551().method_1522();
            GpuTextureView var5 = var4.method_71640();
            if (var5 == null) {
               _O.reset();
               return;
            }

            J.ensure(var4.field_1482, var4.field_1481);
            _O.draw(J.W, var5, !_g);
            _g = true;
         } catch (Throwable var6) {
            metric("draw_failures");
            _O.reset();
            c(var2, var6);
         }
      }
   }

   public static void hudProjection(int var0, int var1, float var2) {
      __.setPerspective(var2 * (float) (Math.PI / 180.0), (float)var0 / var1, 0.05F, 100.0F);
   }

   public static float handLight(float[] var0) {
      System.arraycopy(Q, 0, var0, 0, Q.length);
      RadianceModule var1 = n;
      return G && (var1.N.enabled() || var1.o.enabled()) ? C : 0.0F;
   }

   public static class_11659 handCollector(class_11659 var0) {
      if (!G) {
         return var0;
      } else {
         RadianceModule var1 = n;
         boolean var2 = var1.N.enabled();
         boolean var3 = var1.o.enabled();
         if (!var2 && !var3) {
            return var0;
         } else {
            _4.set(__).mul(RenderSystem.getModelViewMatrix());
            h(_P);
            h(N);
            E = false;
            b[0] = b[1] = null;
            return new Radianceclass_11659(var0, var0, new RadianceSupport.TypeG(var2, var3));
         }
      }
   }

   public static void submittingItem( class_1799 var0) {
      E = var0 != null;
      if (var0 != null && !var0.method_7960()) {
         b[b[0] == null ? 0 : 1] = var0.method_7909();
      }
   }

   public static void drawHandMask() {
      long var0 = 138667453509371L;
      long var2 = var0 ^ 12897369050660L;
      long var4 = var0 ^ 5630823939079L;
      long var6 = var0 ^ 11188438005793L;
      if (G) {
         try {
            if (_v.isEmpty()) {
               return;
            }

            class_310 var8 = class_310.method_1551();
            class_746 var9 = var8.field_1724;
            long var10 = System.nanoTime();
            if ((float)(var10 - _2) > 2.5E8F) {
               F.restart();
               _D.restart();
            }

            _2 = var10;
            if (b[0] != d[0] || b[1] != d[1]) {
               _D.S = 0.0F;
            }

            d[0] = b[0];
            d[1] = b[1];
            RadianceModule var12 = n;
            float var13 = var9 == null ? 1.0F : Math.clamp(var9.method_6032() / Math.max(var9.method_6063(), 1.0F), 0.0F, 1.0F);
            int var14 = var12.Q.enabled() ? 16740252 : 0;
            float var15 = var12.Q.enabled() ? 0.9F * (1.0F - c(0.3F, 0.95F, var13)) : 0.0F;
            d(F, 1.0F, false, var14, var2, var15);
            int var16 = var14;
            float var17 = var15;
            if (var12.F.enabled() && var9 != null) {
               int var18 = S[Math.max(var9.method_6047().method_7932().ordinal(), var9.method_6079().method_7932().ordinal())];
               if (var18 != 0 && var15 < 0.5F) {
                  var16 = var18;
                  var17 = 0.8F;
               }
            }

            d(_D, 1.0F, false, var16, var2, var17);
            W(var6, 1, F, _P);
            W(var6, 2, _D, N);
            class_276 var21 = var8.method_1522();
            GpuTextureView var19 = var21.method_71640();
            if (var19 == null) {
               _v.reset();
               return;
            }

            J.ensure(var21.field_1482, var21.field_1481);
            _v.draw(J.W, var19, !_g);
            _g = true;
            _H = true;
         } catch (Throwable var20) {
            metric("draw_failures");
            _v.reset();
            c(var4, var20);
         }
      }
   }

   private static void W(long var0, int var2, RadianceSupport.TypeK var3, float[] var4) {
      long var5 = 90409014216852L;
      RadianceModule.ensureSourceClassInitialized();
      int var7 = var2 * 16;
      long var8 = 127762569467610L;
      if (127762569467610L == 0L) {
         boolean var10000 = false;
      } else {
         int var11 = var8 < 0L ? -1 : 1;
      }

      System.arraycopy(var4, 0, r, var7, 4);
      k(var7);
      if (r[var7] >= r[var7 + 2] || r[var7 + 1] >= r[var7 + 3]) {
         r[var7] = r[var7 + 1] = 0.0F;
         r[var7 + 2] = r[var7 + 3] = -1.0F;
      }

      p(var7, var5, var3, 1.0F, 0.0F);
   }

   private static void n(class_630 var0, long var1, class_4587 var3, float[] var4) {
      long var5 = var1 ^ 0L;
      long var7 = var1 ^ 128227297002543L;
      RadianceModule.ensureSourceClassInitialized();
      ModelPartAccessor var9 = (ModelPartAccessor)(Object)(var0);
      if (var0.field_3665) {
         var3.method_22903();
         var0.method_22703(var3);
         if (!var0.field_38456) {
            Matrix4f var10 = var3.method_23760().method_23761();
            Iterator var11 = var9.purxiy$cubes().iterator();

            while (var11.hasNext()) {
               Object var12 = var11;
               if (var1 >= 0L) {
                  var12 = var11.next();
               }

               label62:
               while (true) {
                  class_628 var13 = (class_628)var12;

                  for (int var14 = 0; var14 < a(14210, 6104810158522446572L ^ var1); var14++) {
                     var12 = var10;
                     if (var1 < 0L) {
                        continue label62;
                     }

                     float var15 = ((var14 & 1) == 0 ? var13.field_3645 : var13.field_3648) / 16.0F;
                     float var16 = ((var14 & 2) == 0 ? var13.field_3644 : var13.field_3647) / 16.0F;
                     float var17 = ((var14 & 4) == 0 ? var13.field_3643 : var13.field_3646) / 16.0F;
                     p(var7, var10, var15, var16, var17, var4);
                  }

                  if (var1 > 1L) {
                  }
                  break;
               }
            }
         }

         Iterator var23 = var9.purxiy$children().values().iterator();

         while (true) {
            Object var24;
            if (var23.hasNext()) {
               var24 = var23.next();
            } else {
               var24 = var3;
               if (var1 >= 1L) {
                  var3.method_22909();
                  return;
               }
            }

            class_630 var25 = (class_630)var24;
            if (var1 >= 0L) {
               n(var25, var5, var3, var4);
            }
         }
      }
   }

   private static void p(long var0, Matrix4f var2, float var3, float var4, float var5, float[] var6) {
      RadianceModule.ensureSourceClassInitialized();
      var2.transform(T.set(var3, var4, var5, 1.0F));
      _4.transform(T);
      float var7 = T.w;
      float var8 = 1.0E-4F;
      if (var0 >= 0L) {
         if (T.w <= 1.0E-4F) {
            return;
         }

         var7 = T.x / T.w * 0.5F + 0.5F;
         var8 = c;
      }

      float var9 = var7 * var8;
      float var10 = (T.y / T.w * 0.5F + 0.5F) * k;
      var6[0] = Math.min(var6[0], var9);
      var6[1] = Math.min(var6[1], var10);
      var6[2] = Math.max(var6[2], var9);
      var6[3] = Math.max(var6[3], var10);
   }

   private static void d(RadianceSupport.TypeK var0, float var1, boolean var2, int var3, long var4, float var6) {
      RadianceModule.ensureSourceClassInitialized();
      float var7 = 1.0F - (float)Math.exp(-K * 9.0);
      long var8 = 129351250551007L;
      if (129351250551007L == 0L) {
         boolean var10000 = false;
      } else {
         int var14 = var8 < 0L ? -1 : 1;
      }

      var0.j = var0.j + (var1 - var0.j) * var7;
      var0.i = var0.i + ((var2 ? 1.0F : 0.0F) - var0.i) * (1.0F - (float)Math.exp(-K * 8.0));
      var0.S = Math.min(1.0F, var0.S + K / 0.6F);
      var0.A = var0.A * (float)Math.exp(-K * 5.0);
      var0.r = Math.min(1.0F, var0.r + K / 0.7F);
      float var11 = var0.I <= 0.001F && var6 > 0.0F ? 1.0F : 1.0F - (float)Math.exp(-K * 4.0);
      long var12 = 129351250551007L;
      if (129351250551007L == 0L) {
         boolean var15 = false;
      } else {
         int var16 = var12 < 0L ? -1 : 1;
      }

      if (var6 > 0.0F) {
         var0.B = var0.B + ((var3 >> 16 & 0xFF) / 255.0F - var0.B) * var11;
         var0.x = var0.x + ((var3 >> 8 & 0xFF) / 255.0F - var0.x) * var11;
         var0.u = var0.u + ((var3 & 0xFF) / 255.0F - var0.u) * var11;
      }

      var0.I = var0.I + (var6 - var0.I) * (1.0F - (float)Math.exp(-K * 4.0));
   }

   private static void p(int var0, long var1, RadianceSupport.TypeK var3, float var4, float var5) {
      r[var0 + 4] = var3.B;
      r[var0 + 5] = var3.x;
      r[var0 + 6] = var3.u;
      r[var0 + 7] = var3.I;
      r[var0 + 8] = var3.j;
      r[var0 + 9] = var3.i;
      r[var0 + 10] = var3.A;
      r[var0 + 11] = var3.r;
      r[var0 + 12] = c(0.0F, 1.0F, var3.S);
      r[var0 + 13] = var4;
      r[var0 + 14] = var3.q;
      r[var0 + 15] = var5;
   }

   private static void l(Matrix4f var0, float var1, float var2, long var3, float var5, float var6, float var7, float var8, int var9) {
      RadianceModule.ensureSourceClassInitialized();
      float var10 = Float.POSITIVE_INFINITY;
      float var11 = Float.POSITIVE_INFINITY;
      float var12 = Float.NEGATIVE_INFINITY;
      float var13 = Float.NEGATIVE_INFINITY;

      for (int var14 = 0; var14 < 8; var14++) {
         var0.transform(
            T.set(var1 + ((var14 & 1) == 0 ? -var6 : var6), var2 + ((var14 & 2) == 0 ? var7 : var8), var5 + ((var14 & 4) == 0 ? -var6 : var6), 1.0F)
         );
         long var15 = 71002366201159L;
         if (71002366201159L == 0L) {
            boolean var10000 = false;
         } else {
            int var23 = var15 < 0L ? -1 : 1;
         }

         if (T.w <= 0.05F) {
            var11 = 0.0F;
            var10 = 0.0F;
            long var22 = 71002366201159L;
            if (71002366201159L == 0L) {
               boolean var24 = false;
            } else {
               int var25 = var22 < 0L ? -1 : 1;
            }

            var12 = c;
            var13 = k;
            break;
         }

         float var18 = (T.x / T.w * 0.5F + 0.5F) * c;
         float var19 = (T.y / T.w * 0.5F + 0.5F) * k;
         var10 = Math.min(var10, var18);
         var11 = Math.min(var11, var19);
         var12 = Math.max(var12, var18);
         var13 = Math.max(var13, var19);
      }

      r[var9] = var10;
      long var21 = 71002366201160L;
      if (71002366201160L == 0L) {
         boolean var26 = false;
      } else {
         int var27 = var21 < 0L ? -1 : 1;
      }

      r[var9 + 1] = var11;
      r[var9 + 2] = var12;
      r[var9 + 3] = var13;
   }

   private static void k(int var0) {
      r[var0] = Math.max(0.0F, r[var0]);
      r[var0 + 1] = Math.max(0.0F, r[var0 + 1]);
      r[var0 + 2] = Math.min((float)c, r[var0 + 2]);
      r[var0 + 3] = Math.min((float)k, r[var0 + 3]);
   }

   private static float D(long var0, class_310 var2, float var3) {
      RadianceModule.ensureSourceClassInitialized();
      class_5636 var4 = var2.field_1773.method_19418().method_19334();

      float var5 = switch (RadianceSupport.TypeQ.B[var4.ordinal()]) {
         case 1 -> 30.0F;
         case 2, 3 -> 3.0F;
         default -> Float.MAX_VALUE;
      };
      class_746 var6 = var2.field_1724;
      long var7 = 96602628566034L;
      if (96602628566034L == 0L) {
         boolean var10 = false;
      } else {
         int var11 = var7 < 0L ? -1 : 1;
      }

      if (var6 != null && (var6.method_6059(class_1294.field_5919) || var6.method_6059(class_1294.field_38092))) {
         var5 = Math.min(var5, 8.0F);
      }

      return var5 == Float.MAX_VALUE ? 1.0F : 1.0F - c(var5 * 0.45F, var5, var3);
   }

   public static void composite() {
      long var0 = 140311344786512L;
      int var2 = (int)((var0 ^ 90224095154341L) >>> 48);
      long var3 = (var0 ^ 90224095154341L) << 16 >>> 16;
      long var5 = var0 ^ 10865159809377L;
      long var7 = var0 ^ 99208669285071L;
      long var9 = var0 ^ 85615026325362L;
      long var11 = var0 ^ 104185125830088L;
      long var13 = var0 ^ 5075725549228L;
      long var15 = var0 ^ 210341425073L;
      long var17 = var0 ^ 50715737313071L;
      boolean var19 = _g;
      _g = false;
      if (G && var19) {
         long var20 = System.nanoTime();

         try {
            class_310 var22 = class_310.method_1551();
            class_276 var23 = var22.method_1522();
            GpuTextureView var24 = var23.method_71639();
            if (var24 == null || !J.fits(var23.field_1482, var23.field_1481)) {
               return;
            }

            int var25 = V(var17);
            if (!w((char)var2, var25, var3)) {
               return;
            }

            GpuDevice var26 = RenderSystem.getDevice();
            CommandEncoder var27 = var26.createCommandEncoder();
            r(var26, var9);
            w(var7, var27, var25);
            int var28 = g(var27, var25, var15);
            class_12137 var29 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
            GpuQuery var30 = z ? a(var5, var27) : null;

            try {
               int var31 = a * 6;
               T(var27, "coarse field", J.X, var11, true, SkyPipelines.RADIANCE_SEED, var31, var1 -> ((com.mojang.blaze3d.systems.RenderPass)(Object)var1).bindTexture("MaskSampler", J.W, var29));
               GpuTextureView var32 = J.X;
               GpuTextureView var33 = J.b;

               for (int var34 = 0; var34 < var28; var34++) {
                  GpuTextureView var35 = var32;
                  long var36 = (long)var34 * h;
                  String var38 = "flood " + var34;
                  boolean var39 = var34 == 0;
                  RenderPipeline var40 = SkyPipelines.RADIANCE_FLOOD;
                  Consumer var41 = var4 -> {
                     ((com.mojang.blaze3d.systems.RenderPass)(Object)var4).setUniform("RadianceStep", e.slice(var36, 16L));
                     ((com.mojang.blaze3d.systems.RenderPass)(Object)var4).bindTexture("FloodSampler", var35, var29);
                  };
                  T(var27, var38, var33, var11, var39, var40, var31, var41);
                  var32 = var33;
                  var33 = var35;
               }

               GpuTextureView var50 = var32;
               T(var27, "field", J.z, var11, true, SkyPipelines.RADIANCE_REFINE, var31, var2x -> {
                  ((com.mojang.blaze3d.systems.RenderPass)(Object)var2x).bindTexture("MaskSampler", J.W, var29);
                  ((com.mojang.blaze3d.systems.RenderPass)(Object)var2x).bindTexture("FloodSampler", var50, var29);
               });
               T(var27, "light", var24, var11, false, SkyPipelines.RADIANCE, var31, var1 -> {
                  ((com.mojang.blaze3d.systems.RenderPass)(Object)var1).bindTexture("MaskSampler", J.W, var29);
                  ((com.mojang.blaze3d.systems.RenderPass)(Object)var1).bindTexture("FieldSampler", J.z, var29);
               });
            } finally {
               if (var30 != null) {
                  var27.timerQueryEnd(var30);
                  _M = var30;
               }
            }

            if (z) {
               _o = _o + (System.nanoTime() - var20) / 1000000.0;
            }
         } catch (Throwable var49) {
            metric("draw_failures");
            c(var13, var49);
         }
      }
   }

   private static void T(CommandEncoder var0, String var1, GpuTextureView var2, long var3, boolean var5, RenderPipeline var6, int var7, Consumer var8) {
      RadianceModule.ensureSourceClassInitialized();
      long var9 = 36686448251287L;
      if (36686448251287L == 0L) {
         boolean var10000 = false;
      } else {
         int var17 = var9 < 0L ? -1 : 1;
      }

      try (RenderPass var12 = var0.createRenderPass(() -> "Purxiy radiance " + var1, var2, var5 ? OptionalInt.of(0) : OptionalInt.empty())) {
         var12.setPipeline(var6);
         var12.setUniform("PurxiyRadiance", j);
         var12.setUniform("PurxiyRadianceSlots", p);
         int var11 = -1162398758;
         var8.accept(var12);
         var12.draw(0, var7);
         metric("drawn");
      }
   }

   private static int V(long var0) {
      RadianceModule.ensureSourceClassInitialized();
      float var2 = k() * _9;
      float var3 = D() * 2.05F * _s;
      float var4 = Math.max(var2, var3);

      for (int var5 = 1; var5 < _u; var5++) {
         float var6;
         int var7 = (var6 = r[var5 * 16 + 11] - 1.0F) == 0.0F ? 0 : (var6 < 0.0F ? -1 : 1);
         long var8 = 89871827657598L;
         if (89871827657598L == 0L) {
            boolean var10000 = false;
         } else {
            int var14 = var8 < 0L ? -1 : 1;
         }

         if (var7 < 0) {
            var4 = Math.max(var4, 52.0F);
            long var11 = 89871827657598L;
            if (89871827657598L == 0L) {
               boolean var15 = false;
            } else {
               int var16 = var11 < 0L ? -1 : 1;
            }
            break;
         }
      }

      long var13 = 89871827657599L;
      if (89871827657599L == 0L) {
         boolean var17 = false;
      } else {
         int var18 = var13 < 0L ? -1 : 1;
      }

      if (X > 0.01F) {
         var4 = Math.max(var4, 12.0F);
      }

      return Math.min(126, (int)Math.ceil((_p + var4) * l + 3.0F));
   }

   private static boolean w(char var0, int var1, long var2) {
      a = 0;
      float[] var4 = new float[_u * 4];
      RadianceModule.ensureSourceClassInitialized();
      int var5 = 0;

      for (int var6 = 1; var6 < _u; var6++) {
         int var7 = var6 * 16;
         long var8 = 50105775914229L;
         if (50105775914229L == 0L) {
            boolean var10000 = false;
         } else {
            int var59 = var8 < 0L ? -1 : 1;
         }

         boolean var11 = false;
         long var12 = 50105775914228L;
         if (50105775914228L == 0L) {
            boolean var60 = false;
         } else {
            int var61 = var12 < 0L ? -1 : 1;
         }

         if (!(r[var7 + 2] < r[var7])) {
            float var15;
            int var16 = (var15 = r[var7 + 8] - 0.002F) == 0.0F ? 0 : (var15 < 0.0F ? -1 : 1);
            long var17 = 50105775914229L;
            if (50105775914229L == 0L) {
               boolean var62 = false;
            } else {
               int var63 = var17 < 0L ? -1 : 1;
            }

            if (var16 > 0) {
               boolean var19 = false;
               if (var6 >= 3 || _H) {
                  float var20 = Math.max(0.0F, r[var7] - var1);
                  float var21 = Math.max(0.0F, r[var7 + 1] - var1);
                  long var22 = 50105775914228L;
                  if (50105775914228L == 0L) {
                     boolean var64 = false;
                  } else {
                     int var65 = var22 < 0L ? -1 : 1;
                  }

                  float var24 = Math.min((float)c, r[var7 + 2] + var1);
                  float var25 = Math.min((float)k, r[var7 + 3] + var1);
                  long var26 = 50105775914228L;
                  if (50105775914228L == 0L) {
                     boolean var66 = false;
                  } else {
                     int var67 = var26 < 0L ? -1 : 1;
                  }

                  if (!(var20 >= var24)) {
                     float var28;
                     var16 = (var28 = var21 - var25) == 0.0F ? 0 : (var28 < 0.0F ? -1 : 1);
                     if (var16 < 0) {
                        var4[var5 * 4] = var20;
                        var4[var5 * 4 + 1] = var21;
                        var4[var5 * 4 + 2] = var24;
                        var4[var5 * 4 + 3] = var25;
                        var5++;
                     }
                  }
               }
            }
         }
      }

      boolean var32 = false;
      if (var5 == 0) {
         return false;
      } else {
         boolean var33 = true;

         while (var33) {
            long var34 = 50105775914228L;
            if (50105775914228L == 0L) {
               boolean var68 = false;
            } else {
               int var69 = var34 < 0L ? -1 : 1;
            }

            var33 = false;
            int var10 = 0;

            while (var10 < var5) {
               boolean var37 = false;
               if (var33) {
                  break;
               }

               for (int var39 = var10 + 1; var39 < var5; var39++) {
                  float var13 = var4[var10 * 4];
                  boolean var14 = false;
                  float var47;
                  int var49 = (var47 = var13 - var4[var39 * 4 + 2]) == 0.0F ? 0 : (var47 < 0.0F ? -1 : 1);
                  long var50 = 50105775914229L;
                  if (50105775914229L == 0L) {
                     boolean var70 = false;
                  } else {
                     int var71 = var50 < 0L ? -1 : 1;
                  }

                  if (var49 <= 0) {
                     var13 = var4[var39 * 4];
                     long var51 = 50105775914229L;
                     if (50105775914229L == 0L) {
                        boolean var72 = false;
                     } else {
                        int var73 = var51 < 0L ? -1 : 1;
                     }

                     float var52;
                     int var53 = (var52 = var13 - var4[var10 * 4 + 2]) == 0.0F ? 0 : (var52 < 0.0F ? -1 : 1);
                     int var23 = 0;
                     if (var53 <= 0) {
                        var13 = var4[var10 * 4 + 1];
                        long var56 = 50105775914228L;
                        if (50105775914228L == 0L) {
                           boolean var74 = false;
                        } else {
                           int var75 = var56 < 0L ? -1 : 1;
                        }

                        float var57;
                        int var27 = (var57 = var13 - var4[var39 * 4 + 3]) == 0.0F ? 0 : (var57 < 0.0F ? -1 : 1);
                        var23 = (0 != 0 ? 1 : 0);
                        if (var27 <= 0) {
                           var23 = var39 * 4;
                           long var58 = 50105775914228L;
                           if (50105775914228L == 0L) {
                              boolean var76 = false;
                           } else {
                              int var77 = var58 < 0L ? -1 : 1;
                           }

                           float var30;
                           int var31 = (var30 = var4[var23 + 1] - var4[var10 * 4 + 3]) == 0.0F ? 0 : (var30 < 0.0F ? -1 : 1);
                           if (var31 <= 0) {
                              var4[var10 * 4] = Math.min(var4[var10 * 4], var4[var39 * 4]);
                              var4[var10 * 4 + 1] = Math.min(var4[var10 * 4 + 1], var4[var39 * 4 + 1]);
                              var4[var10 * 4 + 2] = Math.max(var4[var10 * 4 + 2], var4[var39 * 4 + 2]);
                              var4[var10 * 4 + 3] = Math.max(var4[var10 * 4 + 3], var4[var39 * 4 + 3]);
                              System.arraycopy(var4, --var5 * 4, var4, var39 * 4, 4);
                              var33 = true;
                              break;
                           }
                        }
                     }
                  }
               }

               var10++;
               long var40 = 50105775914228L;
               if (50105775914228L == 0L) {
                  boolean var78 = false;
               } else {
                  int var79 = var40 < 0L ? -1 : 1;
               }
            }

            long var38 = 50105775914229L;
            if (50105775914229L == 0L) {
               boolean var80 = false;
            } else {
               int var81 = var38 < 0L ? -1 : 1;
            }
         }

         boolean var35 = false;
         long var9 = 50105775914229L;
         if (50105775914229L == 0L) {
            boolean var82 = false;
         } else {
            int var83 = var9 < 0L ? -1 : 1;
         }

         if (var5 > 16) {
            for (int var41 = 1; var41 < var5; var41++) {
               var4[0] = Math.min(var4[0], var4[var41 * 4]);
               var35 = false;
               var4[1] = Math.min(var4[1], var4[var41 * 4 + 1]);
               var4[2] = Math.max(var4[2], var4[var41 * 4 + 2]);
               var4[3] = Math.max(var4[3], var4[var41 * 4 + 3]);
            }

            long var42 = 50105775914229L;
            if (50105775914229L == 0L) {
               boolean var84 = false;
            } else {
               int var85 = var42 < 0L ? -1 : 1;
            }

            var5 = 1;
         }

         for (int var43 = 0; var43 < var5; var43++) {
            y[var43 * 4] = (float)Math.floor(var4[var43 * 4]) / c * 2.0F - 1.0F;
            y[var43 * 4 + 1] = (float)Math.floor(var4[var43 * 4 + 1]) / k * 2.0F - 1.0F;
            y[var43 * 4 + 2] = (float)Math.ceil(var4[var43 * 4 + 2]) / c * 2.0F - 1.0F;
            y[var43 * 4 + 3] = (float)Math.ceil(var4[var43 * 4 + 3]) / k * 2.0F - 1.0F;
         }

         long var44 = 50105775914229L;
         if (50105775914229L == 0L) {
            boolean var86 = false;
         } else {
            int var87 = var44 < 0L ? -1 : 1;
         }

         a = var5;
         return true;
      }
   }

   private static void w(long var0, CommandEncoder var2, int var3) {
      RadianceModule.ensureSourceClassInitialized();
      float var4 = k() * l;
      float var5 = D() * l;
      Std140Builder var6 = Std140Builder.intoBuffer(v.clear())
         .putVec4(c, k, l, U)
         .putVec4(_p * l, H, _9, _s)
         .putVec4(var4, var5, 0.4F + 0.8F * w, _K)
         .putVec4(Q[0], Q[1], Q[2], C)
         .putVec4(Q[3], Q[4], Q[5], var3)
         .putVec4(Q[6], Q[7], Q[8], X);

      for (int var7 = 0; var7 < 16; var7++) {
         var6.putVec4(y[var7 * 4], y[var7 * 4 + 1], y[var7 * 4 + 2], y[var7 * 4 + 3]);
      }

      var2.writeToBuffer(j.slice(), var6.get());
      long var14 = 41395274442399L;
      if (41395274442399L == 0L) {
         boolean var10000 = false;
      } else {
         int var16 = var14 < 0L ? -1 : 1;
      }

      Std140Builder var10 = Std140Builder.intoBuffer(g.clear());

      for (byte var11 = 0; var11 < _u * 16; var11 = (byte)(var11 + 4)) {
         var10.putVec4(r[var11], r[var11 + 1], r[var11 + 2], r[var11 + 3]);
         long var12 = 41395274442398L;
         if (41395274442398L == 0L) {
            boolean var17 = false;
         } else {
            int var18 = var12 < 0L ? -1 : 1;
         }
      }

      long var15 = 41395274442399L;
      if (41395274442399L == 0L) {
         boolean var19 = false;
      } else {
         int var20 = var15 < 0L ? -1 : 1;
      }

      var2.writeToBuffer(p.slice(0L, _u * 16L * 4L), var10.get());
   }

   private static float k() {
      return 12.0F + 36.0F * w;
   }

   private static float D() {
      return 14.0F + 34.0F * w;
   }

   private static int g(CommandEncoder var0, int var1, long var2) {
      int var4 = Integer.highestOneBit(Math.max(1, (var1 + 1) / 2));
      int var5 = 0;
      L.clear();
      RadianceModule.ensureSourceClassInitialized();

      for (int var6 = var4; var6 >= 1; var6 >>= 1) {
         long var7 = 140377760613345L;
         if (140377760613345L == 0L) {
            boolean var10000 = false;
         } else {
            int var15 = var7 < 0L ? -1 : 1;
         }

         long var10 = 140377760613344L;
         if (140377760613344L == 0L) {
            boolean var16 = false;
         } else {
            int var17 = var10 < 0L ? -1 : 1;
         }

         long var12 = 140377760613345L;
         if (140377760613345L == 0L) {
            boolean var18 = false;
         } else {
            int var19 = var12 < 0L ? -1 : 1;
         }

         if (var5 >= 8) {
            break;
         }

         L.putFloat(var5++ * h, var6);
      }

      L.limit(var5 * h);
      long var14 = 140377760613344L;
      if (140377760613344L == 0L) {
         boolean var20 = false;
      } else {
         int var21 = var14 < 0L ? -1 : 1;
      }

      var0.writeToBuffer(e.slice(0L, (long)var5 * h), L);
      return var5;
   }

   private static boolean l(long var0) {
      RadianceModule.ensureSourceClassInitialized();
      GpuDevice var2 = RenderSystem.getDevice();
      if (!IrisCompat.inUse()) {
         long var3 = 96636926292174L;
         if (96636926292174L == 0L) {
            boolean var10000 = false;
         } else {
            int var9 = var3 < 0L ? -1 : 1;
         }

         if (C(var2, SkyPipelines.RADIANCE_MASK) && C(var2, SkyPipelines.RADIANCE_SEED)) {
            long var6 = 96636926292175L;
            if (96636926292175L == 0L) {
               boolean var10 = false;
            } else {
               int var11 = var6 < 0L ? -1 : 1;
            }

            if (C(var2, SkyPipelines.RADIANCE_FLOOD) && C(var2, SkyPipelines.RADIANCE_REFINE) && C(var2, SkyPipelines.RADIANCE)) {
               return true;
            }
         }
      }

      return false;
   }

   private static boolean C(GpuDevice var0, RenderPipeline var1) {
      return var0.precompilePipeline(var1).isValid();
   }

   private static void r(GpuDevice var0, long var1) {
      RadianceModule.ensureSourceClassInitialized();
      if (j == null) {
         j = var0.createBuffer(ClientServices.label(() -> "Purxiy radiance uniforms"), 136, 352L);
         v = MemoryUtil.memAlloc(352);
         p = var0.createBuffer(ClientServices.label(() -> "Purxiy radiance slots"), 136, 8192L);
         g = MemoryUtil.memAlloc(8192);
         h = Math.max(16, var0.getUniformOffsetAlignment());
         e = var0.createBuffer(ClientServices.label(() -> "Purxiy radiance flood steps"), 136, h * 8L);
         L = MemoryUtil.memCalloc(h * 8);
      }
   }

   private static void c(long var0, Throwable var2) {
      RadianceModule.ensureSourceClassInitialized();
      String var3 = var2.toString();
      long var4 = 135244477649660L;
      if (135244477649660L == 0L) {
         boolean var10000 = false;
      } else {
         int var9 = var4 < 0L ? -1 : 1;
      }

      long var7 = 135244477649659L;
      if (135244477649659L == 0L) {
         boolean var10 = false;
      } else {
         int var11 = var7 < 0L ? -1 : 1;
      }

      if (!var3.equals(_d)) {
         _d = var3;
         ClientServices.LOGGER.error("Purxiy radiance failed", var2);
      }
   }

   public static void setTiming(boolean var0) {
      z = var0;
      _w = 0.0;
      _o = 0.0;
      _l = 0;
   }

   
   private static GpuQuery a(long var0, CommandEncoder var2) {
      RadianceModule.ensureSourceClassInitialized();
      if (_M != null) {
         OptionalLong var3 = _M.getValue();
         long var4 = 130281291941168L;
         if (130281291941168L == 0L) {
            boolean var10000 = false;
         } else {
            int var12 = var4 < 0L ? -1 : 1;
         }

         if (var3.isEmpty()) {
            return null;
         }

         _M.close();
         _M = null;
         _w = _w + var3.getAsLong() / 1000000.0;
         if (++_l == 240) {
            Logger var7 = ClientServices.LOGGER;
            Object[] var8 = new Object[]{
               String.format(Locale.ROOT, "%.3f", _o / _l), String.format(Locale.ROOT, "%.3f", _w / _l), null, null, null, null, null
            };
            long var9 = 130281291941168L;
            if (130281291941168L == 0L) {
               boolean var13 = false;
            } else {
               int var14 = var9 < 0L ? -1 : 1;
            }

            var8[2] = _u - 3 + (_H ? 2 : 0);
            var8[3] = a;
            var8[4] = c;
            var8[5] = k;
            var8[6] = n.q.get();
            var7.info("Purxiy radiance: {} ms CPU, {} ms GPU per frame ({} slots, {} rects, {}x{}, {})", var8);
            _w = 0.0;
            _o = 0.0;
            _l = 0;
         }
      }

      return var2.timerQueryBegin();
   }

   private static void h(float[] var0) {
      var0[0] = var0[1] = Float.POSITIVE_INFINITY;
      var0[2] = var0[3] = Float.NEGATIVE_INFINITY;
   }

   private static float b(int var0, long var1) {
      int var3 = var0 * -1640531527;
      var3 ^= var3 >>> 15;
      var3 *= -2048144789;
      var3 ^= var3 >>> 13;
      return (var3 & 65535) / 65536.0F;
   }

   private static float c(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   private static float F(float var0, char var1, float var2, int var3, float var4, short var5) {
      RadianceModule.ensureSourceClassInitialized();
      return var0 < var2 ? Math.min(var2, var0 + var4) : Math.max(var2, var0 - var4);
   }

   private static void A(float[] var0, int var1, int var2, long var3) {
      var0[var1] = (var2 >> 16 & 0xFF) / 255.0F;
      var0[var1 + 1] = (var2 >> 8 & 0xFF) / 255.0F;
      var0[var1 + 2] = (var2 & 0xFF) / 255.0F;
   }

   private static void S(long var0) {
      RadianceModule.ensureSourceClassInitialized();

      for (class_10017 var3 : M) {
         long var4 = 50891339804261L;
         if (50891339804261L == 0L) {
            boolean var10000 = false;
         } else {
            int var8 = var4 < 0L ? -1 : 1;
         }

         ((net.fabricmc.fabric.api.client.rendering.v1.FabricRenderState)var3).setData(_W, null);
      }

      long var7 = 50891339804260L;
      if (50891339804260L == 0L) {
         boolean var9 = false;
      } else {
         int var10 = var7 < 0L ? -1 : 1;
      }

      M.clear();
   }

   public static void install(RadianceModule var0, ModuleContext var1) {
      n = var0;
      O = var1;
   }

   public static void metric(String var0) {
      if (O != null) {
         O.metric(var0, var0.equals("drawn") ? ++i : ++P);
      }
   }

   public static void uninstall() {
      long var0 = 132655789228776L;
      long var2 = var0 ^ 95585655516301L;
      int var4 = 0;

      try {
         _O.releaseInstanceResources();
      } catch (Exception var188) {
         var4++;
      }

      try {
         _v.releaseInstanceResources();
      } catch (Exception var187) {
         var4++;
      }

      try {
         J.close();
      } catch (Exception var186) {
         var4++;
      }

      try {
         ClientCache.release(SkyPipelines.RADIANCE_SEED);
      } catch (Exception var185) {
         var4++;
      }

      try {
         ClientCache.release(SkyPipelines.RADIANCE_FLOOD);
      } catch (Exception var184) {
         var4++;
      }

      try {
         ClientCache.release(SkyPipelines.RADIANCE_REFINE);
      } catch (Exception var183) {
         var4++;
      }

      try {
         ClientCache.release(SkyPipelines.RADIANCE);
      } catch (Exception var182) {
         var4++;
      }

      try {
         ClientCache.release(SkyPipelines.RADIANCE_MASK);
      } catch (Exception var181) {
         var4++;
      }

      try {
         if (_M != null) {
            _M.close();
         }
      } catch (Exception var179) {
         var4++;
      } finally {
         _M = null;
      }

      try {
         if (j != null) {
            j.close();
         }
      } catch (Exception var177) {
         var4++;
      } finally {
         j = null;
      }

      try {
         if (p != null) {
            p.close();
         }
      } catch (Exception var175) {
         var4++;
      } finally {
         p = null;
      }

      try {
         if (e != null) {
            e.close();
         }
      } catch (Exception var173) {
         var4++;
      } finally {
         e = null;
      }

      try {
         if (v != null) {
            MemoryUtil.memFree(v);
         }
      } catch (Exception var171) {
         var4++;
      } finally {
         v = null;
      }

      try {
         if (g != null) {
            MemoryUtil.memFree(g);
         }
      } catch (Exception var169) {
         var4++;
      } finally {
         g = null;
      }

      try {
         if (L != null) {
            MemoryUtil.memFree(L);
         }
      } catch (Exception var167) {
         var4++;
      } finally {
         L = null;
      }

      u.clear();
      _F = null;
      S(var2);
      n = null;
      O = null;
      o = 0L;
      R = false;
      if (var4 != 0) {
         throw new IllegalStateException("World visual GPU cleanup failed");
      }
   }

private static int a(int var0, long var1) {
      int var3 = 14210 ^ (int)(var1 & 32767L) ^ 14383;
      if (db[var3] == null) {
         db[var3] = (int)(cb[var3] ^ var1);
      }

      return db[var3];
   }

   private static long c(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 28403) & 32767;
      if (fb[var3] == null) {
         fb[var3] = eb[var3] ^ var1;
      }

      return fb[var3];
   }

private record TypeB(long m, int s) {
      public long frame() {
         return this.m;
      }

      public int slot() {
         return this.s;
      }

      public long longState47() {
         return this.m;
      }

      public int integerState13() {
         return this.s;
      }
   }

   private record TypeG(boolean b, boolean x) implements Radianceclass_11659.Listener {
      @Override
      public int marker() {
         if (RadianceSupport.E) {
            return this.x ? 1006632962 : 0;
         } else {
            return this.b ? 1006632961 : 0;
         }
      }

      private float[] e() {
         return RadianceSupport.E ? RadianceSupport.N : RadianceSupport._P;
      }

      @Override
      public void model(class_3879<?> var1, class_4587 var2) {
         long var3 = 87204380236032L;
         long var5 = var3 ^ 14010593436754L;
         RadianceSupport.n(var1.method_63512(), var5, var2, this.e());
      }

      @Override
      public void part(class_630 var1, class_4587 var2) {
         long var3 = 65704959503184L;
         long var5 = var3 ^ 132164241839618L;
         RadianceSupport.n(var1, var5, var2, this.e());
      }

      @Override
      public void quads(List<class_777> var1, class_4587 var2) {
         long var3 = 27660206904293L;
         long var5 = var3 ^ 50881820699288L;
         Matrix4f var7 = var2.method_23760().method_23761();
         float[] var8 = this.e();

         for (class_777 var10 : var1) {
            for (int var11 = 0; var11 < 4; var11++) {
               Vector3fc var12 = var10.method_76648(var11);
               RadianceSupport.p(var5, var7, var12.x(), var12.y(), var12.z(), var8);
            }
         }
      }

      @Override
      public void block(class_4587 var1) {
         long var2 = 54078727904963L;
         long var4 = var2 ^ 6879787509694L;
         Matrix4f var6 = var1.method_23760().method_23761();
         float[] var7 = this.e();

         for (int var8 = 0; var8 < 8; var8++) {
            RadianceSupport.p(var4, var6, (float)(var8 & 1), (float)(var8 >> 1 & 1), (float)(var8 >> 2 & 1), var7);
         }
      }

      public boolean arm() {
         return this.b;
      }

      public boolean held() {
         return this.x;
      }

      public boolean booleanState50() {
         return this.b;
      }

      public boolean booleanState7() {
         return this.x;
      }
   }

   private static final class TypeK {
      final float q;
      float j;
      float i;
      float A;
      float r = 1.0F;
      float S;
      float B;
      float x;
      float u;
      float I;
      long s;

      TypeK(float var1) {
         this.q = var1;
      }

      void restart() {
         this.j = 0.0F;
         this.S = 0.0F;
         this.A = 0.0F;
         this.r = 1.0F;
      }
   }

   private static final class TypeN implements class_4588 {
      static final RadianceSupport.TypeN Q = new RadianceSupport.TypeN();

      @Override
      public class_4588 method_22912(float var1, float var2, float var3) {
         return this;
      }

      @Override
      public class_4588 method_1336(int var1, int var2, int var3, int var4) {
         return this;
      }

      @Override
      public class_4588 method_39415(int var1) {
         return this;
      }

      @Override
      public class_4588 method_22913(float var1, float var2) {
         return this;
      }

      @Override
      public class_4588 method_60796(int var1, int var2) {
         return this;
      }

      @Override
      public class_4588 method_22921(int var1, int var2) {
         return this;
      }

      @Override
      public class_4588 method_22914(float var1, float var2, float var3) {
         return this;
      }

      @Override
      public class_4588 method_75298(float var1) {
         return this;
      }
   }

   static class TypeQ {
      static final int[] B = new int[class_5636.values().length];

      static {
         try {
            B[class_5636.field_27886.ordinal()] = 1;
         } catch (NoSuchFieldError var3) {
         }

         try {
            B[class_5636.field_27885.ordinal()] = 2;
         } catch (NoSuchFieldError var2) {
         }

         try {
            B[class_5636.field_27887.ordinal()] = 3;
         } catch (NoSuchFieldError var1) {
         }
      }
   }
}
