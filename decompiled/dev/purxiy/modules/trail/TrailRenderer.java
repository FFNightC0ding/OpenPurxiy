package dev.purxiy.modules.trail;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.OptionalLong;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_742;
import net.minecraft.class_746;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

public final class TrailRenderer {
   private static final double B = 48.0;
   private static final int G = 16;
   private static final int p = Integer.MIN_VALUE;
   private static final float z = 1.1F;
   private static final Int2ObjectMap<TrailSupport> O = new Int2ObjectOpenHashMap();
   private static final TrailSupport2 I = new TrailSupport2();
   private static final TrailSupport2.TypeF y = new TrailSupport2.TypeF();
   private static final TrailSupport4.TypeN w = new TrailSupport4.TypeN();
   private static final double[] m = new double[3];
   private static TrailSupport4.TypeI i;
   private static class_638 l;
   private static TrailStyle d;
   private static String j;
   private static final List<class_1297> g = new ArrayList<>();
   private static final int[] f = new int[3];
   private static double P;
   private static float k;
   private static long D = 0L;
   private static boolean K;
   private static final Matrix4f Z = new Matrix4f();
   private static final Matrix4f n = new Matrix4f();
   private static double Y;
   private static double x;
   private static double M;
   private static float t;
   private static GpuBufferSlice R;
   private static boolean H;
   private static boolean r;
   private static double V;
   private static double h;
   private static double U;
   private static double a;
   private static boolean S;
   private static boolean N;
   private static GpuQuery C;
   private static double Q;
   private static int c = 0;
   private static int b = 0;
   static TrailModule F;
   private static ModuleContext e;
   private static int q = 0;
   private static int X = 0;
   public static boolean o;
   private static final String[] u = new String[10];

   private TrailRenderer() {
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2, GpuBufferSlice var3) {
      K = F.enabled();
      if (K) {
         Z.set(var2).mul(var1);
         Z.invert(n);
         var1.positiveX(w.L);
         var1.positiveY(w.e);
         class_243 var4 = var0.method_71156();
         Y = var4.field_1352;
         x = var4.field_1351;
         M = var4.field_1350;
         class_276 var5 = class_310.method_1551().method_1522();
         t = var2.m11() * var5.field_1481 * 0.5F;
         R = var3;
      }
   }

   public static void render(float var0) {
      long var1 = 130254391437036L;
      long var3 = var1 ^ 35528769979033L;
      long var5 = var1 ^ 92669788020098L;
      boolean var7 = K;
      K = false;
      class_310 var8 = class_310.method_1551();
      class_638 var9 = var8.field_1687;
      if (var9 != l) {
         l = var9;
         clear();
      }

      TrailModule var10 = F;
      if (var7 && var9 != null) {
         TrailStyle var11 = var10.trailStyle();
         if (var11 != d) {
            d = var11;
            clear();
         }

         long var12 = System.nanoTime();
         float var14 = D == 0L ? 0.0F : Math.min((float)(var12 - D) / 1.0E9F, 0.1F);
         D = var12;
         if (var8.method_1493()) {
            var14 = 0.0F;
         }

         P += var14;
         k += var14;

         try {
            b(var8, var5, var9, var0, var11, var14);
            J(var8, var3, var11);
         } catch (Throwable var17) {
            metric("draw_failures");
            String var16 = var17.toString();
            if (!var16.equals(j)) {
               j = var16;
               ClientServices.LOGGER.error("Purxiy trail failed", var17);
            }
         }
      } else {
         if (!O.isEmpty()) {
            clear();
         }

         D = 0L;
      }
   }

   public static void clear() {
      O.clear();
   }

   private static void b(class_310 var0, long var1, class_638 var3, float var4, TrailStyle var5, float var6) {
      long var7 = 117510407992319L;
      byte var9 = 0;
      short var10 = 27360;
      int var11 = (int)(var7 << 32 >>> 32);
      var7 = 96684046917110L;
      var7 = 56256489003847L;
      short var12 = 13098;
      short var13 = 15371;
      int var14 = (int)(var7 << 48 >>> 48);
      TrailModule var15 = F;
      float var16 = var15.B.floatValue();
      float var17 = var15.t.floatValue();
      int[] var18 = palette(var15, f);
      ObjectIterator var19 = O.values().iterator();

      while (var19.hasNext()) {
         TrailSupport var20 = (TrailSupport)var19.next();
         var20.T = false;
      }

      long var40 = 37655780834157L;
      int var22 = var40 < 0L ? -1 : 1;
      class_746 var22x = var0.field_1724;
      if (var22x != null) {
         long var25 = 37655780834158L;
         int var25x = var25 < 0L ? -1 : 1;
         if (u(var9, (char)var10, var11, var22x, var22x)) {
            w(var22x.method_5628(), var22x, var4, var12, var5, var6, var18, var17, var13, var16, (char)var14);
         }
      }

      if (var22x != null) {
         boolean var23 = var15.k.enabled();
         long var24 = 37655780834158L;
         int var42 = var24 < 0L ? -1 : 1;
         if (!var23) {
            var23 = false;
            long var27 = 37655780834157L;
            int var281 = var27 < 0L ? -1 : 1;
         } else {
            List var42x = g;
            long var28 = 37655780834158L;
            int var30 = var28 < 0L ? -1 : 1;
            var42x.clear();
            var30 = 0;

            for (class_742 var32 : var3.method_18456()) {
               if (var32 != var22x && u(var9, (char)var10, var11, var22x, var32)) {
                  var42x.add(var32);
               }
            }

            var30 = var42x.size();
            long var44 = 37655780834158L;
            int var32x = var44 < 0L ? -1 : 1;
            if (var30 > 16) {
               var42x.sort((var1x, var2) -> Double.compare(((class_1297)var1x).method_5858(var22), ((class_1297)var2).method_5858(var22)));
            }

            for (int var33 = 0; var33 < Math.min(var42x.size(), 16); var33++) {
               long var34 = 37655780834157L;
               int var37 = var34 < 0L ? -1 : 1;
               class_1297 var37x = (class_1297)var42x.get(var33);
               w(var37x.method_5628(), var37x, var4, var12, var5, var6, var18, var17, var13, var16, (char)var14);
            }

            long var45x = 37655780834157L;
            int var341 = var45x < 0L ? -1 : 1;
            var42x.clear();
         }
      }

      O.values().removeIf(var2 -> {
         long var3x = 14149593658809L;
         int var5x = var3x < 0L ? -1 : 1;
         if (!var2.T) {
            var2.idle(P, var6, var16);
         }

         return !var2.T && var2.finished(P, var16);
      });
   }

   private static boolean u(short var0, char var1, int var2, class_746 var3, class_1297 var4) {
      if (!var4.method_7325() && !var4.method_5756(var3) && !var4.method_5765()) {
         short var5 = 27360;
         if (var4.method_5805()) {
            if (var4 instanceof class_1309) {
               var5 = 27360;
               class_1309 var6 = (class_1309)var4;
               boolean var7 = var6.method_6113();
               if (var7) {
                  return false;
               }
            }

            return var4 == var3 || var4.method_5858(var3) < 2304.0;
         }
      }

      return false;
   }

   private static void w(int var0, class_1297 var1, float var2, int var3, TrailStyle var4, float var5, int[] var6, float var7, int var8, float var9, char var10) {
      TrailSupport var11 = (TrailSupport)O.get(var0);
      if (var11 == null) {
         var11 = new TrailSupport(var0);
         O.put(var0, var11);
      }

      var11.T = true;
      anchor(var1, var2, var4, m);
      if (var4 == TrailStyle.D) {
         m[1] = var11.ground(var1.method_24828(), var1.method_23318(), m[1]);
      }

      var11.follow(m[0], m[1], m[2], P, var5, var4, var6, var7, var9);
   }

   static void anchor(class_1297 var0, float var1, TrailStyle var2, double[] var3) {
      class_243 var4 = var0.method_30950(var1);
      double var5 = var0.method_17682() * ClientSupport3.bodyHeight(var0);
      double var7 = var4.field_1351;
      if (var2.atHips()) {
         var7 += var5 * (var5 < 1.0 ? 0.5 : 0.47);
      }

      var3[0] = var4.field_1352;
      var3[1] = var7;
      var3[2] = var4.field_1350;
   }

   private static void r(TrailStyle var0, float var1, int var2, int[] var3, short var4, float var5, int var6, float var7) {
      TrailSupport var8 = (TrailSupport)O.get(Integer.MIN_VALUE);
      if (var8 == null) {
         var8 = new TrailSupport(7);
         O.put(Integer.MIN_VALUE, var8);
      }

      var8.T = true;
      a += var1 * 5.6 / 2.6;
      double var9 = Math.max(0.0, Math.sin(a * 2.2)) * 0.9;
      double var11 = V + Math.cos(a) * 2.6;
      double var13 = U + Math.sin(a) * 2.6;
      double var15 = h + (var0 == TrailStyle.D ? 0.0 : var9) + (var0.atHips() ? 0.85 : 0.0);
      var8.follow(var11, var15, var13, P, var1, var0, var3, var5, var7);
   }

   static int[] palette(TrailModule var0, int[] var1) {
      TrailLoader var2 = TrailLoader.resolve((String)var0.f.get(), var0.Q.argb());
      var1[0] = var2.y;
      var1[1] = var2.l;
      var1[2] = var2.N;
      return var1;
   }

   private static void J(class_310 var0, long var1, TrailStyle var3) {
      if (!O.isEmpty() && R != null && TrailSupport4.ready(TrailSupport4.U)) {
         class_276 var4 = var0.method_1522();
         GpuTextureView var5 = var4.method_71639();
         GpuTextureView var6 = var4.method_71640();
         if (var5 != null && var6 != null) {
            TrailModule var7 = F;
            long var8 = 94725924037748L;
            int var11 = var8 < 0L ? -1 : 1;
            y.O = var3;
            y.T = var7.B.floatValue();
            y.E = var7.t.floatValue();
            y.z = var7.I.floatValue();
            y.X = k;
            y.y = Y;
            y.x = x;
            y.B = M;
            long var11x = 94725924037748L;
            int var13 = var11x < 0L ? -1 : 1;
            if (i == null) {
               i = new TrailSupport4.TypeI("world");
            }

            i.reset();
            long var13x = 94725924037749L;
            int var16 = var13x < 0L ? -1 : 1;
            TrailSupport var16x = var0.field_1724 != null && var0.field_1690.method_31044().method_31034()
               ? (TrailSupport)O.get(var0.field_1724.method_5628())
               : null;
            ObjectIterator var17 = O.values().iterator();

            while (var17.hasNext()) {
               TrailSupport var18 = (TrailSupport)var17.next();
               y.b = var18 == var16x ? 1.1F : 0.3F;
               I.path(var18.j, P, y, i);
               I.motes(var18.b, y, i);
            }

            long var21 = 94725924037749L;
            int var19 = var21 < 0L ? -1 : 1;
            if (i.quads() != 0) {
               TrailSupport4.TypeN var19x = w;
               var19x.n.set(Z);
               var19x.w.set(n);
               var19x.V = k;
               var19x.r = y.z;
               var19x.Z = y.E;
               var19x.g = var3;
               var19x.B = t;
               var19x.m = Math.max(6.0F, var4.field_1481 * 0.011F);
               var19x.H = Math.max(30.0F, var4.field_1481 * 0.065F);
               var19x.b = 0.7F;
               var19x.T = 1.2F;
               palette(var7, var19x.K);
               Object var20 = null;
               TrailSupport4.drawWorld(i, var19x, var5, var6, R, (GpuQuery)var20);
               if (var20 != null) {
                  C = (GpuQuery)var20;
               }
            }
         }
      }
   }

   private static GpuQuery x(CommandEncoder var0, int var1, long var2) {
      if (C != null) {
         OptionalLong var4 = C.getValue();
         long var5 = 98662031611119L;
         int var8 = var5 < 0L ? -1 : 1;
         if (var4.isEmpty()) {
            return null;
         }

         C.close();
         C = null;
         Q = Q + var4.getAsLong() / 1000000.0;
         b += var1;
         long var8x = 98662031611119L;
         int var9 = var8x < 0L ? -1 : 1;
         if (++c == 240) {
            ClientServices.LOGGER
               .info("Purxiy trail: {} ms GPU per frame ({} quads, {} trails)", new Object[]{String.format(Locale.ROOT, "%.3f", Q / c), b / c, O.size()});
            Q = 0.0;
            c = 0;
            b = 0;
         }
      }

      return var0.timerQueryBegin();
   }

   public static void install(TrailModule var0, ModuleContext var1) {
      F = var0;
      e = var1;
   }

   public static void metric(String var0) {
      if (e != null) {
         e.metric(var0, var0.equals("drawn") ? ++q : ++X);
      }
   }

   public static void uninstall() {
      int var0 = 0;

      try {
         if (i != null) {
            i.dispose();
         }
      } catch (Exception var14) {
         var0++;
      } finally {
         i = null;
      }

      try {
         if (C != null) {
            C.close();
         }
      } catch (Exception var12) {
         var0++;
      } finally {
         C = null;
      }

      clear();
      g.clear();
      l = null;
      d = null;
      R = null;
      K = false;
      F = null;
      e = null;
      D = 0L;
      if (var0 != 0) {
         throw new IllegalStateException("World visual GPU cleanup failed");
      }
   }

   public static void ensureSourceClassInitialized() {
   }
}
