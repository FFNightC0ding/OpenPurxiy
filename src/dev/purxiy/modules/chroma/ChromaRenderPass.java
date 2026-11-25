package dev.purxiy.modules.chroma;

import dev.purxiy.assets.IrisCompat;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.SkyPipelineRouter;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3726;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_742;
import net.minecraft.class_746;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;

public final class ChromaRenderPass {
   static final int m = 64;
   private static final float x = 2.4F;
   private static final double j = 1.0;
   private static final double W = 1.25;
   private static final double K = 64.0;
   private static final double f = 1.2;
   static final ChromaCodec v = new ChromaCodec();
   static final List<ChromaRenderPass.ChromaBatch> C = new ArrayList<>();
   static double k;
   private static long V = 0L;
   private static boolean n;
   static final Matrix4f b = new Matrix4f();
   static final Matrix4f A = new Matrix4f();
   static float t;
   static double z;
   static double P;
   static double O;
   static boolean s;
   static boolean X;
   static float S;
   static float p = 256.0F;
   static float g;
   private static class_638 h;
   private static boolean N;
   private static double Z;
   private static double e;
   private static double G;
   private static double i;
   private static boolean c = true;
   private static double y;
   private static final Int2ObjectMap<ChromaRenderPass.ChromaState> U = new Int2ObjectOpenHashMap();
   private static boolean w;
   private static boolean B;
   private static boolean d;
   private static boolean J;
   static int R = 0;
   static int[] L;
   private static String Q;
   static ChromaModule E;
   private static ModuleContext D;
   private static int F = 0;
   private static int T = 0;
   public static int Y = 0;
   private static final long[] o = new long[]{-4421235405070683884L, 3143123035164928746L, 6305218659945599380L};
   private static final Integer[] q = new Integer[3];

   private ChromaRenderPass() {
   }

   static void onEnable() {
      if (!X) {
         X = true;
         S = p + 32.0F;
      }

      N = false;
   }

   public static void onBlast(class_243 var0, float var1) {
      long var2 = 34780632787155L;
      long var4 = var2 ^ 2817901239426L;
      class_310 var6 = class_310.method_1551();
      ChromaModule var7 = E;
      if (var7.enabled()
         && var7.L.enabled()
         && var6.field_1687 != null
         && var6.field_1724 != null
         && !(var6.field_1724.method_73189().method_1025(var0) > 4096.0)) {
         double var8 = D(var6.field_1687, var0.method_1031(0.0, 0.5, 0.0), var4, var1 + 4.0);
         splash(var0.field_1352, Double.isNaN(var8) ? var0.field_1351 : var8, var0.field_1350, var7.s.floatValue(), Math.clamp(var1 / 2.0F, 1.2F, 2.6F));
      }
   }

   public static void onAttack(class_1657 var0, class_1297 var1) {
      long var2 = 97925442016981L;
      long var4 = var2 ^ 74912491119236L;
      class_310 var6 = class_310.method_1551();
      ChromaModule var7 = E;
      if (var0 == var6.field_1724 && var7.enabled() && var7.L.enabled() && var1 instanceof class_1309 && var6.field_1687 != null) {
         double var8 = var1.method_24828() ? var1.method_23318() : D(var6.field_1687, var1.method_73189(), var4, 6.0);
         splash(var1.method_23317(), Double.isNaN(var8) ? var1.method_23318() : var8, var1.method_23321(), var7.s.floatValue() * 0.55F, 1.0F);
      }
   }

   static void splash(double var0, double var2, double var4, float var6, float var7) {
      long var8 = 76448136042840L;
      long var10 = var8 ^ 7865134706173L;
      ThreadLocalRandom var12 = ThreadLocalRandom.current();
      float var13 = var6 * var7;
      g(new ChromaRenderPass.ChromaBatch(var0, var2, var4, var13, 0.34F + 0.035F * var13, 1.0F, k), var10);
      int var14 = 4 + Math.round(3.0F * var7);

      for (int var15 = 0; var15 < var14; var15++) {
         double var16 = var12.nextDouble() * Math.PI * 2.0;
         float var18 = var13 * (0.95F + 0.6F * var12.nextFloat());
         float var19 = Math.max(var13 * (0.12F + 0.14F * var12.nextFloat()), 0.8F);
         g(
            new ChromaRenderPass.ChromaBatch(
               var0 + Math.cos(var16) * var18, var2, var4 + Math.sin(var16) * var18, var19, 0.22F + 0.05F * var19, 1.0F, k + 0.05 + 0.18 * var12.nextDouble()
            ),
            var10
         );
      }
   }

   private static void g(ChromaRenderPass.ChromaBatch var0, long var1) {
      ChromaCodec.ensureSourceClassInitialized();
      if (C.size() >= 64) {
         long var3 = 73256194372772L;
         int var6 = var3 < 0L ? -1 : 1;
         ChromaRenderPass.ChromaBatch var6x = C.removeFirst();
         if (!var6x.R) {
            v.paint(var6x.X, var6x.q, var6x.W, var6x.radiusAt(k), tick(), memoryTicks());
         }
      }

      C.add(var0);
   }

   private static void V(long var0, class_310 var2, class_638 var3) {
      long var4 = 73256194372773L;
      long var6 = 32100170576977L;
      ChromaCodec.ensureSourceClassInitialized();
      class_746 var8 = var2.field_1724;
      if (var8 != null && !var8.method_7325()) {
         int var9 = 0;
         ChromaModule var10 = E;
         float var11 = var10.s.floatValue();
         double var12 = var8.method_23318();
         boolean var14 = var8.method_24828();
         long var15 = 83209373319276L;
         int var45 = var15 < 0L ? -1 : 1;
         if (!var14) {
            y = c ? var12 : Math.max(y, var12);
            long var18 = 83209373319277L;
            int var46 = var18 < 0L ? -1 : 1;
         } else if (!c && N) {
            double var44 = y - var12;
            long var20 = 83209373319276L;
            int var23 = var20 < 0L ? -1 : 1;
            double var23x;
            var9 = (var23x = var44 - 1.2) == 0.0 ? 0 : (var23x < 0.0 ? -1 : 1);
            long var25 = 83209373319277L;
            int var25x = var25 < 0L ? -1 : 1;
            if (var9 >= 0 && var10.L.enabled() && !var8.method_5799()) {
               splash(var8.method_23317(), var12, var8.method_23321(), var11, (float)Math.clamp(1.05 + var44 / 8.0, 1.1, 1.8));
            }
         }

         c = var14;
         double var45x = var14 ? var12 : D(var3, var8.method_73189(), var6, 20.0);
         if (!Double.isNaN(var45x)) {
            class_243 var46 = var8.method_18798();
            double var21 = var8.method_23317() + var46.field_1352 * 3.0;
            double var47 = var8.method_23321() + var46.field_1350 * 3.0;
            double var48 = var21 - Z;
            double var27 = var45x - e;
            double var29 = var47 - G;
            boolean var31 = var48 * var48 + var27 * var27 + var29 * var29 >= 1.0;
            int var32 = var10.memorySeconds();
            double var33 = var32 == 0 ? 60.0 : Math.min(var32 * 0.25, 8.0);
            long var35 = 83209373319276L;
            int var37 = var35 < 0L ? -1 : 1;
            if (!N || var31 || k - i > var33) {
               float var37x = 0.45F + 0.06F * var11;
               long var38 = 83209373319276L;
               int var41 = var38 < 0L ? -1 : 1;
               if (!N) {
                  g(new ChromaRenderPass.ChromaBatch(var21, var45x, var47, var11 * 1.3F, var37x * 1.3F, 1.0F, k), var4);
                  long var41x = 83209373319277L;
               } else {
                  g(new ChromaRenderPass.ChromaBatch(var21, var45x, var47, var11, var37x, var31 ? 0.35F : 0.0F, k), var4);
               }

               N = true;
               Z = var21;
               e = var45x;
               G = var47;
               i = k;
            }
         }
      }
   }

   private static void C(char var0, class_310 var1, int var2, char var3, class_638 var4) {
      long var5 = 127429001943257L;
      long var7 = var5 ^ 61303065804650L;
      ChromaCodec.ensureSourceClassInitialized();
      ChromaModule var9 = E;
      if (var9.o.enabled() && var1.field_1724 != null) {
         int var10 = 0;
         int var11 = 0;

         for (class_742 var13 : var4.method_18456()) {
            if (var13 != var1.field_1724) {
               r(var1.field_1724, var7, var13, var9);
               var10++;
            }
         }

         var11 = U.size();
         if (var11 > var10) {
            U.values().removeIf(var0x -> {
               ChromaCodec.ensureSourceClassInitialized();
               return !var0x.I;
            });
         }

         ObjectIterator var15 = U.values().iterator();

         while (var15.hasNext()) {
            ChromaRenderPass.ChromaState var16 = (ChromaRenderPass.ChromaState)var15.next();
            var16.I = false;
         }
      } else {
         U.clear();
      }
   }

   private static void r(class_746 var0, long var1, class_1309 var3, ChromaModule var4) {
      long var5 = 73256194372773L;
      ChromaCodec.ensureSourceClassInitialized();
      ChromaRenderPass.ChromaState var7 = (ChromaRenderPass.ChromaState)U.get(var3.method_5628());
      long var8 = 74922937345971L;
      int var11 = var8 < 0L ? -1 : 1;
      if (var7 == null) {
         var7 = new ChromaRenderPass.ChromaState();
         var7.c = var3.method_23317();
         var7.g = var3.method_23318();
         var7.O = var3.method_23321();
         U.put(var3.method_5628(), var7);
      }

      long var11x = 74922937345971L;
      int var14 = var11x < 0L ? -1 : 1;
      var7.I = true;
      if (var3.field_6012 != var7.Y) {
         var7.Y = var3.field_6012;
         boolean var14x = var3.method_5756(var0) || var3.method_7325() || var3.method_5765() || var3.method_5858(var0) > 4096.0;
         boolean var15 = var3.method_24828();
         double var16 = var3.method_23317();
         double var18 = var3.method_23318();
         double var20 = var3.method_23321();
         float var22 = var4.s.floatValue() * 0.7F;
         if (!var15) {
            var7.V = var7.d ? var18 : Math.max(var7.V, var18);
         } else {
            long var23 = 74922937345970L;
            int var23x = var23 < 0L ? -1 : 1;
            if (!var7.d && !var14x) {
               long var25 = 74922937345971L;
               int var25x = var25 < 0L ? -1 : 1;
               if (var7.V - var18 >= 1.2 && var4.L.enabled() && !var3.method_5799()) {
                  splash(var16, var18, var20, var22, 1.1F);
               }
            }
         }

         var7.d = var15;
         double var31 = var16 - var7.c;
         double var32x = var18 - var7.g;
         double var27 = var20 - var7.O;
         long var29 = 74922937345970L;
         int var29x = var29 < 0L ? -1 : 1;
         if (var15 && !var14x && var31 * var31 + var32x * var32x + var27 * var27 >= 1.5625) {
            g(new ChromaRenderPass.ChromaBatch(var16, var18, var20, var22, 0.4F + 0.06F * var22, 0.3F, k), var5);
            var7.c = var16;
            var7.g = var18;
            var7.O = var20;
         }
      }
   }

   private static double D(class_638 var0, class_243 var1, long var2, double var4) {
      ChromaCodec.ensureSourceClassInitialized();
      class_3965 var6 = var0.method_17742(
         new class_3959(var1, var1.method_1031(0.0, -var4, 0.0), class_3960.field_17558, class_242.field_1347, class_3726.method_16194())
      );
      return var6.method_17783() == class_240.field_1332 ? var6.method_17784().field_1351 : Double.NaN;
   }

   private static void U(char var0, int var1, int var2) {
      ChromaCodec.ensureSourceClassInitialized();
      int var3 = tick();
      int var4 = memoryTicks();
      Iterator var5 = C.iterator();

      while (var5.hasNext()) {
         ChromaRenderPass.ChromaBatch var6 = (ChromaRenderPass.ChromaBatch)var5.next();
         double var7 = k - var6.Z;
         if (!var6.R) {
            double var9;
            int var11 = (var9 = var7 - var6.S) == 0.0 ? 0 : (var9 < 0.0 ? -1 : 1);
            if (var11 >= 0) {
               v.paint(var6.X, var6.q, var6.W, var6.Y, var3, var4);
               var6.R = true;
            }
         }

         if (var6.R && var7 >= 2.4F) {
            var5.remove();
         }
      }
   }

   static void forget() {
      C.clear();
      U.clear();
      v.clear();
      N = false;
      c = true;
      g = 0.0F;
   }

   static int tick() {
      return (int)Math.floor(k * 8.0);
   }

   static int memoryTicks() {
      return E.memorySeconds() * 8;
   }

   static int fadeTicks() {
      int var0 = E.memorySeconds();
      float var1 = var0 == 0 ? 1.0F : Math.clamp(var0 * 0.3F, 4.0F, 40.0F);
      return Math.round(var1 * 8.0F);
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2) {
      if (!ModuleWidget.claimed("mirror-capture")) {
         class_310 var3 = class_310.method_1551();
         n = (X || E.enabled()) && !var3.field_1773.method_35765();
         if (n) {
            b.set(var2).mul(var1);
            b.invert(A);
            t = var2.m11();
            class_243 var4 = var0.method_71156();
            z = var4.field_1352;
            P = var4.field_1351;
            O = var4.field_1350;
            s = IrisCompat.inUse();
            if (s) {
               SkyPipelineRouter.wantHandMask();
            }
         }
      }
   }

   static void keepDepth(boolean var0) {
      long var1 = 56585814254545L;
      long var3 = var1 ^ 119332676337590L;
      if (n && X && E.p.enabled() && !ModuleWidget.claimed("mirror-capture")) {
         try {
            ChromaRenderer.keepDepth(var0);
         } catch (Throwable var6) {
            metric("draw_failures");
            g(var6, var3);
         }
      }
   }

   public static void render() {
      long var0 = 132982925488255L;
      long var2 = var0 ^ 12197177452710L;
      int var4 = (int)((var0 ^ 12197177452710L) >>> 48);
      int var5 = (int)((var0 ^ 12197177452710L) << 16 >>> 32);
      int var6 = (int)(var2 << 48 >>> 48);
      long var7 = var0 ^ 112569641532952L;
      var2 = var0 ^ 20621498622243L;
      int var9 = (int)((var0 ^ 20621498622243L) >>> 48);
      int var10 = (int)((var0 ^ 20621498622243L) << 16 >>> 32);
      int var11 = (int)(var2 << 48 >>> 48);
      long var12 = var0 ^ 42933107239960L;
      long var14 = var0 ^ 56483939840018L;
      var2 = var0 ^ 24523073868589L;
      int var16 = (int)((var0 ^ 24523073868589L) >>> 32);
      int var17 = (int)((var0 ^ 24523073868589L) << 32 >>> 48);
      int var18 = (int)(var2 << 48 >>> 48);
      if (!ModuleWidget.claimed("mirror-capture")) {
         boolean var19 = n;
         n = false;
         class_310 var20 = class_310.method_1551();
         class_638 var21 = var20.field_1687;
         ChromaModule var22 = E;
         p = var20.field_1690.method_38521() * 16.0F + 48.0F;
         if (var21 != h) {
            h = var21;
            forget();
            if (var22.enabled()) {
               X = true;
               S = p + 32.0F;
            }
         }

         long var23 = System.nanoTime();
         float var25 = V == 0L ? 0.0F : Math.min((float)(var23 - V) / 1.0E9F, 0.1F);
         V = var23;
         if (var19 && var21 != null) {
            boolean var26 = !var20.method_1493();
            if (var26) {
               k += var25;
               n(var22.enabled(), var25, var7);
            }

            if (X) {
               v.setFade(fadeTicks());
               if (var26 && var22.enabled()) {
                  V(var14, var20, var21);
                  C((char)var4, var20, var5, (char)var6, var21);
               }

               U((char)var9, var10, var11);
               v.follow(z, O);
               v.sweep(tick(), memoryTicks(), 16, z, O);
               t(var16, (char)var17, var25, (short)var18);

               try {
                  ChromaRenderer.draw(var20);
               } catch (Throwable var28) {
                  metric("draw_failures");
                  g(var28, var12);
               }
            }
         } else {
            if (!var22.enabled() && X) {
               X = false;
               forget();
            }

            V = 0L;
         }
      }
   }

   private static void n(boolean var0, float var1, long var2) {
      ChromaCodec.ensureSourceClassInitialized();
      long var4 = 33617404628582L;
      int var7 = var4 < 0L ? -1 : 1;
      long var7x = 33617404628583L;
      int var9 = var7x < 0L ? -1 : 1;
      if (var0) {
         if (!X) {
            X = true;
            S = p + 32.0F;
         }

         S = Math.max(0.0F, S - var1 * (10.0F + S * 1.7F));
      } else if (X) {
         float var9x = 10.0F + S * 1.9F;
         long var10 = 33617404628582L;
         int var11 = var10 < 0L ? -1 : 1;
         S += var1 * var9x;
         if (S > p + 48.0F) {
            X = false;
            forget();
         }
      }
   }

   private static void t(int var0, char var1, float var2, short var3) {
      ChromaCodec.ensureSourceClassInitialized();
      int var4 = tick();
      float var5 = 0.0F;
      int var6 = 0;
      double var7 = P - 1.6;

      for (int var9 = 1; var9 <= 4; var9++) {
         double var10 = var9 * var9 * 2.5;

         for (int var12 = 0; var12 < 12; var12++) {
            double var13 = (var12 + var9 * 0.5) * Math.PI / 6.0;
            var5 += v.colour((int)Math.floor(z + Math.cos(var13) * var10), (int)Math.floor(O + Math.sin(var13) * var10), var7, var4);
            var6++;
         }
      }

      float var15 = Math.max(var5 / var6 * 0.8F, smoothstep(p * 0.55F, p, S));
      g = g + (var15 - g) * (1.0F - (float)Math.exp(-var2 / 0.8F));
   }

   private static void g(Throwable var0, long var1) {
      ChromaCodec.ensureSourceClassInitialized();
      String var3 = var0.toString();
      long var4 = 105547287485543L;
      int var7 = var4 < 0L ? -1 : 1;
      long var7x = 105547287485543L;
      int var8 = var7x < 0L ? -1 : 1;
      if (!var3.equals(Q)) {
         Q = var3;
         ClientServices.LOGGER.error("SourceByDevFFnightmare DeObfDeCompByGuideX chroma failed", var0);
      }
   }

   static float smoothstep(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   private static void w(class_638 var0, class_746 var1, long var2, float var4, float var5) {
      long var6 = var2 ^ 65569349301350L;
      ChromaCodec.ensureSourceClassInitialized();
      double var8 = Math.toRadians(var1.method_36454());
      double var10 = -Math.sin(var8);
      double var12 = Math.cos(var8);
      float var14 = 0.0F;

      while (var14 <= var4) {
         double var15 = Math.sin(var14 / 7.0) * 3.5;
         double var17 = var1.method_23317() + var10 * var14 - var12 * var15;
         double var19 = var1.method_23321() + var12 * var14 + var10 * var15;
         double var21 = D(var0, new class_243(var17, var1.method_23318() + 24.0, var19), var6, 60.0);
         if (var2 >= 1L) {
            if (!Double.isNaN(var21)) {
               v.paint(var17, var21, var19, var5, tick(), memoryTicks());
            }

            var14++;
         }
      }
   }

   public static void install(ChromaModule var0, ModuleContext var1) {
      E = var0;
      D = var1;
   }

   public static void metric(String var0) {
      if (D != null) {
         D.metric(var0, var0.equals("drawn") ? ++F : ++T);
      }
   }

   public static void uninstall() {
      v.clear();
      C.clear();
      U.clear();
      h = null;
      X = false;
      n = false;
      E = null;
      D = null;
      V = 0L;
   }

   public static void ensureSourceClassInitialized() {
   }

   static final class ChromaBatch {
      final double X;
      final double q;
      final double W;
      final float Y;
      final float S;
      final float T;
      final double Z;
      boolean R;

      ChromaBatch(double var1, double var3, double var5, float var7, float var8, float var9, double var10) {
         this.X = var1;
         this.q = var3;
         this.W = var5;
         this.Y = var7;
         this.S = var8;
         this.T = var9;
         this.Z = var10;
      }

      float radiusAt(double var1) {
         double var3 = var1 - this.Z;
         if (var3 <= 0.0) {
            return 0.0F;
         } else {
            float var5 = (float)Math.min(var3 / this.S, 1.0);
            float var6 = 1.0F - var5;
            return this.Y * (1.0F - var6 * var6 * var6);
         }
      }

      float wetAt(double var1) {
         double var3 = var1 - this.Z;
         return var3 < 0.0 ? 0.0F : this.T * (1.0F - ChromaRenderPass.smoothstep(0.84000003F, 2.4F, (float)var3));
      }
   }

   private static final class ChromaState {
      double c;
      double g;
      double O;
      boolean d = true;
      boolean I;
      double V;
      int Y = -1;
   }
}
