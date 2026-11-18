// Sourced By DevFFNightmare
package dev.purxiy.modules.afterglow;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.GpuDevice;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.purxiy.assets.IrisCompat;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientCache;
import dev.purxiy.loader.fabric.mixin.LivingEntityRendererInvoker;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.render.pipeline.SkyPipelines;
import it.unimi.dsi.fastutil.ints.Int2FloatOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2FloatMap.Entry;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.WeakHashMap;
import net.fabricmc.fabric.api.client.rendering.v1.RenderStateDataKey;
import net.minecraft.class_10017;
import net.minecraft.class_10042;
import net.minecraft.class_10275;
import net.minecraft.class_1109;
import net.minecraft.class_11658;
import net.minecraft.class_11659;
import net.minecraft.class_11890;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1510;
import net.minecraft.class_1528;
import net.minecraft.class_1531;
import net.minecraft.class_1569;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3532;
import net.minecraft.class_4587;
import net.minecraft.class_5819;
import net.minecraft.class_638;
import net.minecraft.class_898;
import net.minecraft.class_922;
import net.minecraft.class_9909;
import net.minecraft.class_9960;
import net.minecraft.class_10017.class_11680;
import org.joml.Vector3f;

public final class AfterglowRegistry {
   private static final RenderStateDataKey<AfterglowSupport2> L = RenderStateDataKey.create(ClientServices.label(() -> {
      int var0x = -2114947236;
      int var1x = 2114975621;
      int var2 = -2114951498;
      return "purxiy:afterglow";
   }));
   private static final float g = 1.45F;
   private static final float H = 0.08F;
   private static final float d = 0.05F;
   private static final float x = 5.0F;
   private static final float S = 14.0F;
   private static final float Y = 200.0F;
   private static final float B = 8.0F;
   private static final float Z = 0.55F;
   private static final float F = 1.0F;
   private static final class_3414 A = class_3414.method_47908(ClientServices.id("world.afterglow"));
   private static final long N = 90000000L;
   private static final Int2ObjectOpenHashMap<AfterglowSupport2> D = new Int2ObjectOpenHashMap();
   private static final Int2FloatOpenHashMap X = new Int2FloatOpenHashMap();
   private static final AfterglowUpdater b = new AfterglowUpdater();
   private static final class_5819 U = class_5819.method_43047();
   private static final Vector3f j = new Vector3f();
   private static long w = 0L;
   private static long V = 0L;
   private static float E;
   
   private static class_638 z;
   private static float t = -1.0F;
   private static boolean R;
   private static double k;
   private static int K = 0;
   private static final float[] y = new float[9];
   private static final float[] v = new float[9];
   private static float e;
   private static boolean C;
   private static float Q;
   private static float r;
   private static float M;
   private static final Set<class_10017> n = Collections.newSetFromMap(new WeakHashMap<>());
   static AfterglowModule a;
   private static ModuleContext W;
   private static int I = 0;
   private static int p = 0;
   public static int h = 0;


   private static final long[] i = new long[]{
      8418041543335846039L,
      -7932186263885135505L,
      -7963643352913090173L,
      -9168931186665511853L,
      729360806513753004L,
      5897169777232215364L,
      -6473626618358563560L,
      -2964079422203029082L
   };

   private static final long[] m = new long[]{8091408831253342495L, 96938431991166168L};
   private static final Long[] o = new Long[2];

   private AfterglowRegistry() {
   }

   public static void beginFrame() {
      long var0 = 13299038777744L;
      long var2 = var0 ^ 138252499437704L;
      int var4 = (int)((var0 ^ 138252499437704L) >>> 48);
      int var5 = (int)((var0 ^ 138252499437704L) << 16 >>> 32);
      int var6 = (int)(var2 << 48 >>> 48);
      long var7 = var0 ^ 105256952936660L;
      if (!ModuleWidget.claimed("mirror-capture")) {
         long var9 = System.nanoTime();
         boolean var11 = w != 0L && var9 - w < 500000000L;
         float var12 = var11 ? Math.min((float)(var9 - w) / 1.0E9F, 0.1F) : 0.0F;
         w = var9;
         E += var12;
         class_310 var13 = class_310.method_1551();
         AfterglowModule var14 = a;
         if (var13.field_1687 != z) {
            z = var13.field_1687;
            D.clear();
            X.clear();
            b.clear();
         }

         if (!var14.enabled()) {
            D.clear();
            C = false;
         } else {
            C(var14, (char)var4, var12, var5, (char)var6);
         }

         ObjectIterator var15 = D.values().iterator();

         while (var15.hasNext()) {
            AfterglowSupport2 var16 = (AfterglowSupport2)var15.next();
            if (!var16.W) {
               class_1309 var17 = var16.n;
               if (var17.method_31481()) {
                  if (var16.V == null || var16.finished() || var17 == var13.field_1724) {
                     var15.remove();
                     continue;
                  }

                  var16.W = true;
               } else if (!var17.method_29504() && !var16.c) {
                  var15.remove();
                  continue;
               }
            }

            p(var16, var14, var7, var12);
            if (var16.c && var16.H >= var16.D + 0.55F + 1.0F) {
               var15.remove();
            } else if (var16.W && var16.finished()) {
               var15.remove();
            }
         }

         b.update(var12, E);
         ObjectIterator var18 = X.int2FloatEntrySet().iterator();

         while (var18.hasNext()) {
            if (E - ((Entry)var18.next()).getFloatValue() > 8.0F) {
               var18.remove();
            }
         }

         if (R && (!D.isEmpty() || b.V > 0)) {
            k = k + (System.nanoTime() - var9) / 1000000.0;
            if (++K == 120) {
               ClientServices.LOGGER
                  .info(
                     "Purxiy afterglow: {} ms CPU per frame ({} dissolving, {} motes)", new Object[]{String.format(Locale.ROOT, "%.3f", k / K), D.size(), b.V}
                  );
               k = 0.0;
               K = 0;
            }
         }
      }
   }

   private static void p(AfterglowSupport2 var0, AfterglowModule var1, long var2, float var4) {
      long var5 = 14777450266454L;
      AfterglowRenderer.ensureSourceClassInitialized();
      long var7 = 91961276057924L;
      if (91961276057924L == 0L) {
         boolean var10000 = false;
      } else {
         int var21 = var7 < 0L ? -1 : 1;
      }

      var0.H = t >= 0.0F ? t * var0.D : var0.H + var4;
      float var10 = Math.clamp(var0.H / var0.D, 0.0F, 1.0F);
      long var11 = 91961276057923L;
      if (91961276057923L == 0L) {
         boolean var22 = false;
      } else {
         int var23 = var11 < 0L ? -1 : 1;
      }

      long var14 = 91961276057924L;
      if (91961276057924L == 0L) {
         boolean var24 = false;
      } else {
         int var25 = var14 < 0L ? -1 : 1;
      }

      if (var0.c) {
         long var16 = 91961276057923L;
         if (91961276057923L == 0L) {
            boolean var26 = false;
         } else {
            int var27 = var16 < 0L ? -1 : 1;
         }

         if (var0.H > var0.D + 0.55F) {
            float var19 = Math.clamp((var0.H - var0.D - 0.55F) / 1.0F, 0.0F, 1.0F);
            float var20 = 1.0F - var19 * var19 * (3.0F - 2.0F * var19);
            var0.v = Math.min(var20, 0.999F);
            var0.a = o(var20);
            var0.b = smoothstep(0.0F, 0.3F, var20);
            var0.C = 0.6F * Q(var20);
            return;
         }
      }

      var0.v = var10;
      var0.a = o(var10);
      var0.b = smoothstep(0.0F, 0.14F, var10);
      var0.C = Q(var10);
      J(var0, var1, var4, var5);
   }

   private static float o(float var0) {
      float var1 = Math.clamp((var0 - 0.08F) / 0.92F, 0.0F, 1.0F);
      var1 = var1 * var1 * (3.0F - 2.0F * var1);
      return -0.05F + 1.051F * var1;
   }

   private static float Q(float var0) {
      float var1 = Math.clamp(var0 / 0.2F, 0.0F, 1.0F);
      float var2 = class_3532.method_15374(var1 * (float) Math.PI);
      return var2 * var2 * (1.0F - 0.4F * var1);
   }

   private static void C(AfterglowModule var0, char var1, float var2, int var3, char var4) {
      long var5 = 124953607616792L;
      long var7 = var5 ^ 78391106669567L;
      long var9 = var5 ^ 106945486279370L;
      AfterglowRenderer.ensureSourceClassInitialized();
      AfterglowLoader var11 = AfterglowLoader.resolve(var0.b.get(), var0.r.argb());
      W(v, 0, var11.t, var7);
      W(v, 3, var11.W, var7);
      W(v, 6, var11.G, var7);
      float var12 = C ? 1.0F - (float)Math.exp(-var2 * 3.5) : 1.0F;

      for (int var13 = 0; var13 < y.length; var13++) {
         y[var13] = y[var13] + (v[var13] - y[var13]) * var12;
      }

      e = e + (var0.s.floatValue() - e) * var12;
      C = true;
      Q = Y(y, var9, 0);
      r = Y(y, var9, 3);
      M = Y(y, var9, 6);
   }

   private static void J(AfterglowSupport2 var0, AfterglowModule var1, float var2, long var3) {
      long var5 = 74888359143341L;
      AfterglowRenderer.ensureSourceClassInitialized();
      if (var0.K != 0) {
         float[] var7 = var0.Y;
         if (var7 == null) {
            float var8 = Math.clamp(var0.p * 5.0F, 14.0F, 200.0F) * var1.W.floatValue();
            var0.plan(Math.round(var8), U);
            var7 = var0.Y;
         }

         if (var7 != null && var0.F != 0) {
            float var19 = 1.0F + 0.6F * var0.C;
            float var9;
            int var10 = (var9 = t - 0.0F) == 0.0F ? 0 : (var9 < 0.0F ? -1 : 1);
            long var11 = 14777450266453L;
            if (14777450266453L == 0L) {
               boolean var10000 = false;
            } else {
               int var24 = var11 < 0L ? -1 : 1;
            }

            if (var10 >= 0) {
               for (int var22 = Math.round(var0.F * var2 / var0.D * (1.0F + U.method_43057())); var22 > 0; var22--) {
                  int var23 = U.method_43048(var0.F);
                  long var16 = 14777450266453L;
                  if (14777450266453L == 0L) {
                     boolean var29 = false;
                  } else {
                     int var30 = var16 < 0L ? -1 : 1;
                  }

                  if (Math.abs(var7[var23 * 4 + 3] - var0.a) < 0.12F) {
                     N(var0, var7, var23, var19, var5);
                  }
               }
            } else {
               while (var0.j < var0.F) {
                  float var14;
                  var10 = (var14 = var7[var0.j * 4 + 3] - var0.a) == 0.0F ? 0 : (var14 < 0.0F ? -1 : 1);
                  long var15 = 14777450266454L;
                  if (14777450266454L == 0L) {
                     boolean var25 = false;
                  } else {
                     int var26 = var15 < 0L ? -1 : 1;
                  }

                  if (var10 > 0) {
                     break;
                  }

                  N(var0, var7, var0.j++, var19, var5);
               }

               long var21 = 14777450266454L;
               if (14777450266454L == 0L) {
                  boolean var27 = false;
               } else {
                  int var28 = var21 < 0L ? -1 : 1;
               }
            }
         }
      }
   }

   private static void N(AfterglowSupport2 var0, float[] var1, int var2, float var3, long var4) {
      AfterglowRenderer.ensureSourceClassInitialized();
      int var6 = (int)var1[var2 * 4];
      long var7 = 74888359143340L;
      if (74888359143340L == 0L) {
         boolean var10000 = false;
      } else {
         int var22 = var7 < 0L ? -1 : 1;
      }

      if (var6 < var0.K) {
         var0.point(var6, var1[var2 * 4 + 1], var1[var2 * 4 + 2], j);
         double var10 = var0.O + j.x;
         double var12 = var0.I + j.y;
         double var14 = var0.L + j.z;
         long var16 = 74888359143341L;
         if (74888359143341L == 0L) {
            boolean var23 = false;
         } else {
            int var24 = var16 < 0L ? -1 : 1;
         }

         float var19 = var0.normal(var6, 0);
         float var20 = var0.normal(var6, 1);
         float var21 = var0.normal(var6, 2);
         b.emit(var0.k, var10, var12, var14, var19, var20, var21, var3);
         if (U.method_43056()) {
            b.emit(AfterglowCatalog.I, var10, var12, var14, var19, var20, var21, 1.0F);
         }
      }
   }

   public static void addPass(class_9909 var0, class_9960 var1, GpuBufferSlice var2) {
      if (!ModuleWidget.claimed("mirror-capture") && b.V > 0) {
         b.addPass(var0, var1, var2, y, e, E);
      }
   }

   public static void extract(class_1297 var0, class_10017 var1) {
      long var2 = 84929280123111L;
      long var4 = var2 ^ 33660810291619L;
      int var6 = (int)((var2 ^ 106989252902177L) >>> 32);
      int var7 = (int)((var2 ^ 106989252902177L) << 32 >>> 32);
      long var8 = var2 ^ 17174174521299L;
      long var10 = var2 ^ 82731970933170L;
      int var12 = (int)((var2 ^ 82731970933170L) >>> 32);
      int var13 = (int)((var2 ^ 82731970933170L) << 32 >>> 48);
      int var14 = (int)(var10 << 48 >>> 48);
      long var15 = var2 ^ 104665631343854L;
      AfterglowModule var17 = a;
      if (var17.enabled() && var0 instanceof class_1309 var18 && var1 instanceof class_10042 var19) {
         AfterglowSupport2 var20 = (AfterglowSupport2)D.get(var18.method_5628());
         if (var20 == null || var20.n != var18) {
            if (!var18.method_29504() || !H(var18, var17, var8) || !U(var12, var13, (char)var14)) {
               return;
            }

            var20 = new AfterglowSupport2(var18, (var18.method_5628() * 625341585 ^ 1597334677) & 16777215, AfterglowCatalog.byName(var17.Z.get()));
            var20.D = 1.45F / var17.g.floatValue();
            D.put(var18.method_5628(), var20);
            p(var20, var17, var4, 0.0F);
            if (var17.D.enabled()) {
               X(var18, var17.g.floatValue(), var15);
            }
         }

         var20.V = var1;
         var20.O = var1.field_53325;
         var20.I = var1.field_53326;
         var20.L = var1.field_53327;
         if (var20.K == 0) {
            var20.e = 0.0F;
            var20.i = Math.max(var1.field_53330, 0.2F);
         }

         var20.x = (180.0F - var19.field_53446) * (float) (Math.PI / 180.0);
         if (var20.c) {
            var19.field_53449 = var20.H <= var20.D + 0.55F ? var20.H * 20.0F : 0.0F;
         }

         if (var17.w.enabled()) {
            float var21 = Math.min(1.0F, class_3532.method_15355(Math.max(0.0F, (var19.field_53449 - 1.0F) / 20.0F * 1.6F)));
            var20.h = var21 * h(var18, var6, var7) * (float) (Math.PI / 180.0);
         } else {
            var19.field_53449 = 0.0F;
            var20.h = 0.0F;
         }

         var19.field_53460 = false;
         var1.field_53335 = false;
         n.add(var1);
         ((net.fabricmc.fabric.api.client.rendering.v1.FabricRenderState)var1).setData(L, var20);
      }
   }

   public static boolean beginSubmit(class_10017 var0, double var1, double var3, double var5) {
      AfterglowSupport2 var7 = (AfterglowSupport2)((net.fabricmc.fabric.api.client.rendering.v1.FabricRenderState)var0).getData(L);
      if (var7 == null) {
         return false;
      } else if (var7.finished()) {
         return true;
      } else {
         var7.o = (float)var1;
         var7.d = (float)var3;
         var7.Q = (float)var5;
         return false;
      }
   }

   public static boolean dissolving(class_10017 var0) {
      return ((net.fabricmc.fabric.api.client.rendering.v1.FabricRenderState)var0).getData(L) != null;
   }

   public static class_11659 collector(class_10017 var0, class_11659 var1) {
      AfterglowSupport2 var2 = (AfterglowSupport2)((net.fabricmc.fabric.api.client.rendering.v1.FabricRenderState)var0).getData(L);
      return (class_11659)(var2 == null ? var1 : new Afterglowclass_11659(var1, var1, var2));
   }

   public static List<class_11680> shadow(class_10017 var0, List<class_11680> var1) {
      AfterglowSupport2 var2 = (AfterglowSupport2)((net.fabricmc.fabric.api.client.rendering.v1.FabricRenderState)var0).getData(L);
      return var2 == null ? var1 : var2.shadow(var1);
   }

   public static void submitGhosts(class_898 var0, class_11658 var1, class_4587 var2, class_11659 var3) {
      if (!D.isEmpty()) {
         class_243 var4 = var1.field_63082.field_63078;
         ObjectIterator var5 = D.values().iterator();

         while (var5.hasNext()) {
            AfterglowSupport2 var6 = (AfterglowSupport2)var5.next();
            class_10017 var7 = var6.V;
            if (var6.W && var7 != null && !var6.finished()) {
               var7.field_61821 = 0;
               var0.method_72976(
                  var7,
                  var1.field_63082,
                  var7.field_53325 - var4.field_1352,
                  var7.field_53326 - var4.field_1351,
                  var7.field_53327 - var4.field_1350,
                  var2,
                  var3
               );
            }
         }
      }
   }

   public static boolean hidesPoof(class_1309 var0) {
      return a.enabled() && D.containsKey(var0.method_5628());
   }

   public static boolean preview(class_1309 var0) {
      long var1 = 58550342439746L;
      long var3 = var1 ^ 113126073539078L;
      long var5 = var1 ^ 56354172781079L;
      int var7 = (int)((var1 ^ 56354172781079L) >>> 32);
      int var8 = (int)((var1 ^ 56354172781079L) << 32 >>> 48);
      int var9 = (int)(var5 << 48 >>> 48);
      long var10 = var1 ^ 43221028222795L;
      AfterglowModule var12 = a;
      if (!U(var7, var8, (char)var9)) {
         return false;
      } else {
         AfterglowSupport2 var13 = new AfterglowSupport2(
            var0, (var0.method_5628() * 625341585 ^ 1597334677 ^ U.method_43048(65536)) & 16777215, AfterglowCatalog.byName(var12.Z.get())
         );
         var13.D = 1.45F / var12.g.floatValue();
         var13.c = true;
         D.put(var0.method_5628(), var13);
         p(var13, var12, var3, 0.0F);
         if (var12.D.enabled()) {
            V = 0L;
            X(var0, var12.g.floatValue(), var10);
         }

         return true;
      }
   }

   public static boolean previewReady() {
      long var0 = 10503001624776L;
      long var2 = var0 ^ 17101511311773L;
      int var4 = (int)((var0 ^ 17101511311773L) >>> 32);
      int var5 = (int)((var0 ^ 17101511311773L) << 32 >>> 48);
      int var6 = (int)(var2 << 48 >>> 48);
      return U(var4, var5, (char)var6);
   }

   public static float previewLength() {
      return 1.45F / a.g.floatValue() + 0.55F + 1.0F;
   }

   public static float previewGone() {
      return 1.45F / a.g.floatValue() + 0.55F;
   }

   public static void previewEnd(class_1309 var0) {
      AfterglowSupport2 var1 = (AfterglowSupport2)D.get(var0.method_5628());
      if (var1 != null && var1.c) {
         D.remove(var0.method_5628());
      }
   }

   public static void onDamage( class_1297 var0, int var1) {
      class_310 var2 = class_310.method_1551();
      if (var0 != null && var2.field_1724 != null && var1 == var2.field_1724.method_5628() && var0 != var2.field_1724) {
         X.put(var0.method_5628(), E);
      }
   }

   private static boolean H(class_1309 var0, AfterglowModule var1, long var2) {
      long var4 = 48724554421599L;
      AfterglowRenderer.ensureSourceClassInitialized();
      if (!(var0 instanceof class_1510) && !(var0 instanceof class_1528) && !(var0 instanceof class_10275) && !(var0 instanceof class_1531)) {
         boolean var6 = var0 instanceof class_11890;
         String var7 = var1.H.get();
         byte var8 = -1;
         switch (var7.hashCode()) {
            case -2099245599:
               boolean var9 = var7.equals("Players & monsters");
               long var10 = 73257361405747L;
               if (73257361405747L == 0L) {
                  boolean var19 = false;
               } else {
                  int var20 = var10 < 0L ? -1 : 1;
               }

               if (var9) {
                  var8 = 1;
               }
               break;
            case 1171085714:
               if (var7.equals("Players")) {
                  long var13 = 73257361405748L;
                  if (73257361405748L == 0L) {
                     boolean var10000 = false;
                  } else {
                     int var18 = var13 < 0L ? -1 : 1;
                  }

                  var8 = 0;
               }
         }
         boolean var16 = switch (var8) {
            case 0 -> var6;
            case 1 -> var6 || var0 instanceof class_1569;
            default -> true;
         };
         long var17 = 73257361405748L;
         if (73257361405748L == 0L) {
            boolean var22 = false;
         } else {
            int var23 = var17 < 0L ? -1 : 1;
         }

         return var16 && (!var1.h.enabled() || y(var0, var4));
      } else {
         return false;
      }
   }

   private static void X(class_1309 var0, float var1, long var2) {
      AfterglowRenderer.ensureSourceClassInitialized();
      long var4 = System.nanoTime();
      long var6 = 19857753269257L;
      if (19857753269257L == 0L) {
         boolean var10000 = false;
      } else {
         int var10 = var6 < 0L ? -1 : 1;
      }

      if (var4 - V >= 90000000L) {
         V = var4;
         float var9 = Math.clamp((0.94F + U.method_43057() * 0.12F) * (float)Math.sqrt(var1), 0.75F, 1.35F);
         class_310.method_1551()
            .method_1483()
            .method_4873(
               new class_1109(
                  A,
                  var0.method_5634(),
                  0.8F,
                  var9,
                  class_5819.method_43047(),
                  var0.method_23317(),
                  var0.method_23318() + var0.method_17682() * 0.6,
                  var0.method_23321()
               )
            );
      }
   }

   private static boolean U(int var0, int var1, char var2) {
      AfterglowRenderer.ensureSourceClassInitialized();
      GpuDevice var3 = RenderSystem.getDevice();
      return !IrisCompat.inUse()
         && var3.precompilePipeline(SkyPipelines.AFTERGLOW).isValid()
         && var3.precompilePipeline(SkyPipelines.AFTERGLOW_TRANSLUCENT).isValid()
         && var3.precompilePipeline(SkyPipelines.AFTERGLOW_EMISSIVE).isValid();
   }

   private static boolean y(class_1309 var0, long var1) {
      AfterglowRenderer.ensureSourceClassInitialized();
      boolean var3 = X.containsKey(var0.method_5628());
      long var4 = 48724554421598L;
      if (48724554421598L == 0L) {
         boolean var10000 = false;
      } else {
         int var7 = var4 < 0L ? -1 : 1;
      }

      return var3 && E - X.get(var0.method_5628()) <= 8.0F;
   }

   private static float h(class_1309 var0, int var1, int var2) {
      AfterglowRenderer.ensureSourceClassInitialized();
      return class_310.method_1551().method_1561().method_3953(var0) instanceof class_922 var4
         ? ((LivingEntityRendererInvoker)var4).purxiy$flipDegrees()
         : 90.0F;
   }

   static float packedFirst() {
      return Q;
   }

   static float packedSecond() {
      return r;
   }

   static float packedAccent() {
      return M;
   }

   static float glow() {
      return e;
   }

   public static void setFreeze(float var0) {
      t = var0 < 0.0F ? -1.0F : Math.min(var0, 0.999F);
   }

   public static void setTiming(boolean var0) {
      R = var0;
      k = 0.0;
      K = 0;
      b.setTiming(var0);
   }

   static float smoothstep(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   private static void W(float[] var0, int var1, int var2, long var3) {
      var0[var1] = (var2 >> 16 & 0xFF) / 255.0F;
      var0[var1 + 1] = (var2 >> 8 & 0xFF) / 255.0F;
      var0[var1 + 2] = (var2 & 0xFF) / 255.0F;
   }

   private static float Y(float[] var0, long var1, int var3) {
      int var4 = Math.round(Math.clamp(var0[var3], 0.0F, 1.0F) * 255.0F);
      int var5 = Math.round(Math.clamp(var0[var3 + 1], 0.0F, 1.0F) * 255.0F);
      int var6 = Math.round(Math.clamp(var0[var3 + 2], 0.0F, 1.0F) * 255.0F);
      return var4 << 16 | var5 << 8 | var6;
   }

   private static void g(long var0) {
      AfterglowRenderer.ensureSourceClassInitialized();

      for (class_10017 var3 : n) {
         long var4 = 88127816973796L;
         if (88127816973796L == 0L) {
            boolean var10000 = false;
         } else {
            int var8 = var4 < 0L ? -1 : 1;
         }

         ((net.fabricmc.fabric.api.client.rendering.v1.FabricRenderState)var3).setData(L, null);
      }

      long var7 = 88127816973797L;
      if (88127816973797L == 0L) {
         boolean var9 = false;
      } else {
         int var10 = var7 < 0L ? -1 : 1;
      }

      n.clear();
   }

   public static void install(AfterglowModule var0, ModuleContext var1) {
      a = var0;
      W = var1;
   }

   public static void metric(String var0) {
      if (W != null) {
         W.metric(var0, var0.equals("drawn") ? ++I : ++p);
      }
   }

   public static void uninstall() {
      long var0 = 77312196918833L;
      long var2 = var0 ^ 24698162316244L;
      int var4 = 0;

      try {
         b.releaseInstanceResources();
      } catch (Exception var10) {
         var4++;
      }

      try {
         ClientCache.release(SkyPipelines.AFTERGLOW);
      } catch (Exception var9) {
         var4++;
      }

      try {
         ClientCache.release(SkyPipelines.AFTERGLOW_TRANSLUCENT);
      } catch (Exception var8) {
         var4++;
      }

      try {
         ClientCache.release(SkyPipelines.AFTERGLOW_EMISSIVE);
      } catch (Exception var7) {
         var4++;
      }

      try {
         ClientCache.release(SkyPipelines.AMBIENCE);
      } catch (Exception var6) {
         var4++;
      }

      D.clear();
      X.clear();
      z = null;
      g(var2);
      a = null;
      W = null;
      w = 0L;
      C = false;
      if (var4 != 0) {
         throw new IllegalStateException("World visual GPU cleanup failed");
      }
   }

private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 31155) & 32767;
      if (o[var3] == null) {
         o[var3] = m[var3] ^ var1;
      }

      return o[var3];
   }

}
