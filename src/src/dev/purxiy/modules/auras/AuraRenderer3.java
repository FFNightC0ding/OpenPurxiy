// Sourced By DevFFNightmare
package dev.purxiy.modules.auras;

import com.mojang.blaze3d.buffers.GpuBufferSlice;
import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.GpuQuery;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientSupport3;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry;
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

public final class AuraRenderer3 {
   private static final double S = 48.0;
   private static final int b = 16;
   private static final int X = Integer.MIN_VALUE;
   private static final float n = 0.3F;
   private static final float j = 0.75F;
   static final float N = 1.4F;
   private static final Int2ObjectMap<AuraSupport3> L = new Int2ObjectOpenHashMap();
   private static final AuraRenderer2 g = new AuraRenderer2();
   private static final AuraRenderer2.TypeE T = new AuraRenderer2.TypeE();
   private static final AuraSupport4.TypeT Y = new AuraSupport4.TypeT();
   
   private static AuraSupport4.TypeG c;
   
   private static class_638 J;
   
   private static String I;
   private static final List<class_1297> F = new ArrayList<>();
   private static double a;
   private static double q;
   private static long W = 0L;
   private static float H;
   
   private static AuraStyle v;
   
   private static AuraStyle C;
   private static float o;
   private static float Q = -1.0F;
   
   private static AuraStyle y;
   private static long i = 0L;
   private static boolean m;
   private static boolean d;
   private static final Matrix4f A = new Matrix4f();
   private static final Matrix4f B = new Matrix4f();
   private static double l;
   private static double x;
   private static double r;
   private static boolean f;
   private static float w;
   
   private static GpuBufferSlice V;
   private static boolean s;
   private static boolean p;
   private static double k;
   private static double M;
   private static double O;
   private static double K;
   private static boolean Z;
   private static boolean R;
   
   private static GpuQuery D;
   private static double E;
   private static int h = 0;
   private static int P = 0;
   static AuraModule U;
   private static ModuleContext z;
   private static int G = 0;
   private static int t = 0;

   private static final String[] u = new String[7];



   private AuraRenderer3() {
   }

   static void gather() {
      if (H >= 1.0F) {
         H = 0.0F;
      }
   }

   public static void previewGather() {
      H = 0.0F;
      ObjectIterator var0 = L.values().iterator();

      while (var0.hasNext()) {
         AuraSupport3 var1 = (AuraSupport3)var0.next();
         var1.k = 0.0F;
      }
   }

   public static void pulse() {
      Q = 0.0F;
   }

   static void tryOn(AuraStyle var0) {
      y = var0;
      i = System.nanoTime() + 400000000L;
   }

   private static AuraStyle Z(long var0, AuraModule var2) {
      AuraStyle.ensureSourceClassInitialized();
      if (y != null && System.nanoTime() < i) {
         return y;
      } else {
         y = null;
         return var2.auraStyle();
      }
   }

   static float pulseAt(float var0) {
      if (!(var0 < 0.0F) && !(var0 >= 1.4F)) {
         return var0 < 0.16F
            ? AuraRenderer2.smooth(var0 / 0.16F)
            : (float)Math.exp(-(var0 - 0.16F) * 3.4F) * (1.0F - AuraRenderer2.smooth((var0 - 1.0F) / 0.4F));
      } else {
         return 0.0F;
      }
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2, GpuBufferSlice var3) {
      d = U.enabled() || H > 0.0F || C != null || m;
      if (d) {
         A.set(var2).mul(var1);
         A.invert(B);
         class_243 var4 = var0.method_71156();
         l = var4.field_1352;
         x = var4.field_1351;
         r = var4.field_1350;
         f = var0.method_19333();
         class_276 var5 = class_310.method_1551().method_1522();
         w = var2.m11() * var5.field_1481 * 0.5F;
         V = var3;
      }
   }

   public static void render(float var0) {
      long var1 = 130384429331071L;
      long var3 = var1 ^ 58952803017768L;
      long var5 = var1 ^ 57497958152595L;
      long var7 = var1 ^ 19599713237855L;
      boolean var9 = d;
      d = false;
      class_310 var10 = class_310.method_1551();
      class_638 var11 = var10.field_1687;
      if (var11 != J) {
         J = var11;
         L.clear();
      }

      if (var9 && var11 != null) {
         long var12 = System.nanoTime();
         float var14 = W == 0L ? 0.0F : Math.min((float)(var12 - W) / 1.0E9F, 0.1F);
         W = var12;
         if (var10.method_1493()) {
            var14 = 0.0F;
         }

         AuraModule var15 = U;
         a += var14;
         q = q + var14 * var15.O.floatValue();
         if (Q >= 0.0F) {
            Q += var14;
            if (Q >= 1.4F) {
               Q = -1.0F;
            }
         }

         try {
            boolean var16 = var15.enabled();
            AuraStyle var23 = Z(var5, var15);
            if (v != var23) {
               if (v != null && H > 0.0F && (C == null || H >= o)) {
                  C = v;
                  o = H;
               }

               v = var23;
               H = 0.0F;
            }

            H = var16 ? Math.min(1.0F, H + var14 / 1.1F) : Math.max(0.0F, H - var14 / 0.7F);
            if (C != null) {
               o -= var14 / 0.7F;
               if (o <= 0.0F) {
                  C = null;
               }
            }

            boolean var18 = var16 || H > 0.0F;
            a(var10, var3, var11, var0, var14, var18);
            m = Y(var7, var10, var15);
            if (!m && !var16 && H <= 0.0F && C == null) {
               L.clear();
               AuraSupport4.closeLayer();
            }
         } catch (Throwable var22) {
            metric("draw_failures");
            String var17 = var22.toString();
            if (!var17.equals(I)) {
               I = var17;
               ClientServices.LOGGER.error("Purxiy aura failed", var22);
            }
         }
      } else {
         if (m) {
            m = false;
            AuraSupport4.closeLayer();
         }

         W = 0L;
      }
   }

   private static void a(class_310 var0, long var1, class_638 var3, float var4, float var5, boolean var6) {
      long var7 = 4711756904975L;
      long var9 = 54699192607548L;
      AuraModule var11 = U;
      AuraStyle.ensureSourceClassInitialized();
      ObjectIterator var12 = L.values().iterator();

      while (var12.hasNext()) {
         AuraSupport3 var13 = (AuraSupport3)var12.next();
         var13.N = false;
      }

      long var31 = 73716561729111L;
      if (73716561729111L == 0L) {
         boolean var10000 = false;
      } else {
         int var39 = var31 < 0L ? -1 : 1;
      }

      class_746 var16 = var0.field_1724;
      if (var16 != null) {
         boolean var17 = q(var16, var16, var7);
         long var18 = 73716561729111L;
         if (73716561729111L == 0L) {
            boolean var40 = false;
         } else {
            int var41 = var18 < 0L ? -1 : 1;
         }

         if (var17) {
            AuraSupport3 var20 = T(var16.method_5628(), var16, var9, var4, var5);
            var20.k = 1.0F;
         }
      }

      if (var16 != null && var6) {
         boolean var32 = var11.f.enabled();
         long var34 = 73716561729111L;
         if (73716561729111L == 0L) {
            boolean var42 = false;
         } else {
            int var43 = var34 < 0L ? -1 : 1;
         }

         if (!var32) {
            var32 = false;
            long var21 = 73716561729111L;
            if (73716561729111L == 0L) {
               boolean var44 = false;
            } else {
               int var45 = var21 < 0L ? -1 : 1;
            }
         } else {
            List var35 = F;
            long var22 = 73716561729110L;
            if (73716561729110L == 0L) {
               boolean var46 = false;
            } else {
               int var47 = var22 < 0L ? -1 : 1;
            }

            var35.clear();
            int var24 = 0;

            for (class_742 var26 : var3.method_18456()) {
               if (var26 != var16 && q(var16, var26, var7)) {
                  var35.add(var26);
               }
            }

            var24 = var35.size();
            long var37 = 73716561729111L;
            if (73716561729111L == 0L) {
               boolean var48 = false;
            } else {
               int var49 = var37 < 0L ? -1 : 1;
            }

            if (var24 > 16) {
               var35.sort((var1x, var2) -> Double.compare(((net.minecraft.class_1297)(Object)var1x).method_5858(var16), ((net.minecraft.class_1297)(Object)var2).method_5858(var16)));
            }

            for (int var27 = 0; var27 < Math.min(var35.size(), 16); var27++) {
               class_1297 var28 = (class_1297)var35.get(var27);
               long var29 = 73716561729111L;
               if (73716561729111L == 0L) {
                  boolean var50 = false;
               } else {
                  int var51 = var29 < 0L ? -1 : 1;
               }

               T(var28.method_5628(), var28, var9, var4, var5).come(true, var5);
            }

            long var38 = 73716561729111L;
            if (73716561729111L == 0L) {
               boolean var52 = false;
            } else {
               int var53 = var38 < 0L ? -1 : 1;
            }

            var35.clear();
         }
      }

      L.values().removeIf(var1x -> {
         AuraStyle.ensureSourceClassInitialized();
         long var2 = 85892733704100L;
         if (85892733704100L == 0L) {
            boolean var10000x = false;
         } else {
            int var5x = var2 < 0L ? -1 : 1;
         }

         if (var1x.N) {
            return false;
         } else {
            var1x.come(false, var5);
            return var1x.k <= 0.0F;
         }
      });
   }

   private static boolean q(class_746 var0, class_1297 var1, long var2) {
      AuraStyle.ensureSourceClassInitialized();
      if (!var1.method_7325() && !var1.method_5756(var0)) {
         long var4 = 4711756904974L;
         if (4711756904974L == 0L) {
            boolean var10000 = false;
         } else {
            int var14 = var4 < 0L ? -1 : 1;
         }

         if (var1.method_5805()) {
            if (var1 instanceof class_1309) {
               long var7 = 4711756904974L;
               if (4711756904974L == 0L) {
                  boolean var15 = false;
               } else {
                  int var16 = var7 < 0L ? -1 : 1;
               }

               class_1309 var9 = (class_1309)var1;
               boolean var10 = var9.method_6113();
               long var11 = 4711756904975L;
               if (4711756904975L == 0L) {
                  boolean var17 = false;
               } else {
                  int var18 = var11 < 0L ? -1 : 1;
               }

               if (var10) {
                  return false;
               }
            }

            return var1 == var0 || var1.method_5858(var0) < 2304.0;
         }
      }

      return false;
   }

   private static AuraSupport3 T(int var0, class_1297 var1, long var2, float var4, float var5) {
      AuraStyle.ensureSourceClassInitialized();
      AuraSupport3 var6 = (AuraSupport3)L.get(var0);
      long var7 = 54699192607547L;
      if (54699192607547L == 0L) {
         boolean var10000 = false;
      } else {
         int var13 = var7 < 0L ? -1 : 1;
      }

      if (var6 == null) {
         var6 = new AuraSupport3();
         L.put(var0, var6);
      }

      var6.N = true;
      class_243 var10 = var1.method_30950(var4);
      float var11 = var1.method_17682() * ClientSupport3.bodyHeight(var1);
      float var12 = var1.method_17681() / 0.6F;
      var6.follow(a, var10.field_1352, var10.field_1351, var10.field_1350, var11, var12, var5);
      return var6;
   }

   private static void F(float var0, long var1) {
      AuraStyle.ensureSourceClassInitialized();
      AuraSupport3 var3 = (AuraSupport3)L.get(Integer.MIN_VALUE);
      long var4 = 30286870687110L;
      if (30286870687110L == 0L) {
         boolean var10000 = false;
      } else {
         int var9 = var4 < 0L ? -1 : 1;
      }

      if (var3 == null) {
         var3 = new AuraSupport3();
         L.put(Integer.MIN_VALUE, var3);
      }

      var3.N = true;
      K += var0 * 4.3 / 2.6;
      double var7 = Math.max(0.0, Math.sin(K * 2.2)) * 0.9;
      var3.follow(a, k + Math.cos(K) * 2.6, M + var7, O + Math.sin(K) * 2.6, 1.8F, 1.0F, var0);
      var3.come(true, var0);
   }

   static int[] palette(AuraModule var0, int[] var1) {
      AuraLoader var2 = AuraLoader.resolve(var0.H.get(), var0.z.argb());
      var1[0] = var2.A;
      var1[1] = var2.R;
      var1[2] = var2.n;
      return var1;
   }

   private static boolean Y(long var0, class_310 var2, AuraModule var3) {
      AuraStyle.ensureSourceClassInitialized();
      if (!L.isEmpty() && V != null && AuraSupport4.ready(AuraSupport4.H)) {
         class_276 var4 = var2.method_1522();
         GpuTextureView var5 = var4.method_71639();
         GpuTextureView var6 = var4.method_71640();
         if (var5 != null && var6 != null) {
            AuraRenderer2.TypeE var7 = T;
            long var8 = 113550687848736L;
            if (113550687848736L == 0L) {
               boolean var10000 = false;
            } else {
               int var32 = var8 < 0L ? -1 : 1;
            }

            var7.T = var3.R.floatValue();
            long var11 = 113550687848735L;
            if (113550687848735L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var11 < 0L ? -1 : 1;
            }

            var7.y = var3.O.floatValue();
            var7.b = q;
            var7.k = pulseAt(Q);
            var7.F = 1.0F;
            palette(var3, var7.d);
            if (c == null) {
               c = new AuraSupport4.TypeG("world");
            }

            c.reset();
            boolean var14 = !f;
            AuraSupport3 var15 = var2.field_1724 != null ? (AuraSupport3)L.get(var2.field_1724.method_5628()) : null;
            boolean var16 = var15 != null && (!var14 || var3.J.enabled());
            ObjectIterator var17 = L.int2ObjectEntrySet().iterator();

            while (var17.hasNext()) {
               Entry var18 = (Entry)var17.next();
               AuraSupport3 var19 = (AuraSupport3)var18.getValue();
               long var20 = 113550687848735L;
               if (113550687848735L == 0L) {
                  boolean var35 = false;
               } else {
                  int var36 = var20 < 0L ? -1 : 1;
               }

               long var22 = 113550687848736L;
               if (113550687848736L == 0L) {
                  boolean var37 = false;
               } else {
                  int var38 = var22 < 0L ? -1 : 1;
               }

               if (var19 == var15) {
                  long var24 = 113550687848736L;
                  if (113550687848736L == 0L) {
                     boolean var39 = false;
                  } else {
                     int var40 = var24 < 0L ? -1 : 1;
                  }

                  if (!var16) {
                     long var26 = 113550687848735L;
                     if (113550687848735L == 0L) {
                        boolean var41 = false;
                     } else {
                        int var42 = var26 < 0L ? -1 : 1;
                     }
                     continue;
                  }
               }

               var19.from(l, x, r);
               var7.v = var19 == var15 && var14 ? 0.0F : 1.0F;
               if (C != null) {
                  var7.f = C;
                  var7.n = o * var19.k;
                  g.draw(var19, var7, c);
               }

               if (v != null) {
                  var7.f = v;
                  var7.n = H * var19.k;
                  g.draw(var19, var7, c);
               }
            }

            long var28 = 113550687848736L;
            if (113550687848736L == 0L) {
               boolean var43 = false;
            } else {
               int var44 = var28 < 0L ? -1 : 1;
            }

            if (c.quads() == 0) {
               return false;
            } else {
               AuraSupport4.TypeT var29 = Y;
               var29.d.set(A);
               var29.X.set(B);
               var29.Y = (float)(q % 3600.0);
               var29.F = var3.A.floatValue();
               var29.R = w;
               var29.I = Math.max(6.0F, var4.field_1481 * 0.011F);
               var29.u = Math.max(30.0F, var4.field_1481 * 0.065F);
               var29.x = 0.7F;
               long var30 = 113550687848735L;
               if (113550687848735L == 0L) {
                  boolean var45 = false;
               } else {
                  int var46 = var30 < 0L ? -1 : 1;
               }

               boolean var23 = var14 && var16;
               var29.Z = var23 ? 0.3F : 0.05F;
               var29.W = var23 ? 0.75F : 0.25F;
               var29.p = 0.0F;
               Object var31 = null;
               AuraSupport4.drawWorld(c, var29, var5, var6, V, (GpuQuery)var31);
               if (var31 != null) {
                  D = (GpuQuery)var31;
               }

               return true;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   
   private static GpuQuery u(CommandEncoder var0, long var1, int var3) {
      AuraStyle.ensureSourceClassInitialized();
      if (D != null) {
         OptionalLong var4 = D.getValue();
         long var5 = 133444365009093L;
         if (133444365009093L == 0L) {
            boolean var10000 = false;
         } else {
            int var10 = var5 < 0L ? -1 : 1;
         }

         if (var4.isEmpty()) {
            return null;
         }

         D.close();
         D = null;
         E = E + var4.getAsLong() / 1000000.0;
         P += var3;
         long var8 = 133444365009094L;
         if (133444365009094L == 0L) {
            boolean var11 = false;
         } else {
            int var12 = var8 < 0L ? -1 : 1;
         }

         if (++h == 240) {
            ClientServices.LOGGER
               .info("Purxiy aura: {} ms GPU per frame ({} quads, {} auras)", new Object[]{String.format(Locale.ROOT, "%.3f", E / h), P / h, L.size()});
            E = 0.0;
            h = 0;
            P = 0;
         }
      }

      return var0.timerQueryBegin();
   }

   public static void install(AuraModule var0, ModuleContext var1) {
      U = var0;
      z = var1;
   }

   public static void metric(String var0) {
      if (z != null) {
         z.metric(var0, var0.equals("drawn") ? ++G : ++t);
      }
   }

   public static void uninstall() {
      int var0 = 0;

      try {
         if (c != null) {
            c.dispose();
         }
      } catch (Exception var14) {
         var0++;
      } finally {
         c = null;
      }

      try {
         if (D != null) {
            D.close();
         }
      } catch (Exception var12) {
         var0++;
      } finally {
         D = null;
      }

      L.clear();
      F.clear();
      J = null;
      v = null;
      C = null;
      y = null;
      V = null;
      d = false;
      m = false;
      H = 0.0F;
      U = null;
      z = null;
      W = 0L;
      if (var0 != 0) {
         throw new IllegalStateException("World visual GPU cleanup failed");
      }
   }

}
