package dev.purxiy.modules.spotify;

import dev.purxiy.anim.MotionStyle;
import dev.purxiy.hud.HudModule;
import dev.purxiy.hud.HudSlot;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.ClientTheme;
import dev.purxiy.loader.fabric.client.ClientRegistry10;
import dev.purxiy.loader.fabric.client.ClientRegistry9;
import dev.purxiy.loader.fabric.mixin.BossHealthOverlayAccessor;
import dev.purxiy.loader.fabric.module.ModuleRegistry;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.RenderPipelines.TypeJ;
import dev.purxiy.render.RenderPipelines.TypeW;
import dev.purxiy.ui.Icons;
import java.util.List;
import java.util.Locale;
import net.minecraft.class_12206;
import net.minecraft.class_1282;
import net.minecraft.class_1297;
import net.minecraft.class_1306;
import net.minecraft.class_1309;
import net.minecraft.class_1531;
import net.minecraft.class_1569;
import net.minecraft.class_1657;
import net.minecraft.class_2338;
import net.minecraft.class_2350;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_266;
import net.minecraft.class_276;
import net.minecraft.class_310;
import net.minecraft.class_3532;
import net.minecraft.class_3726;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_4063;
import net.minecraft.class_4184;
import net.minecraft.class_5636;
import net.minecraft.class_638;
import net.minecraft.class_746;
import net.minecraft.class_8646;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;

public final class SpotifyRenderer5 {
   private static final float G = 0.45F;
   private static final float C = 0.9F;
   private static final float eV = 0.25F;
   private static final float u = 0.03F;
   private static final float U = 0.078F;
   private static final float b = 0.074F;
   private static final float f = 0.075F;
   private static final float c = 0.75F;
   private static final float ee = 9.0F;
   private static final float eT = 2.2F;
   private static final float Q = 0.3F;
   private static final float[] I = new float[]{1.0F, 0.8F, 1.3F, 0.64F, 1.7F, 0.5F, 2.3F};
   private static final float W = 0.8F;
   private static final float k = 1.1F;
   private static final float a = 1.2F;
   private static final float e9 = 2.6F;
   private static final float eG = 4.0F;
   private static final float M = 0.6F;
   private static final float s = 0.9F;
   private static final float j = 0.74F;
   private static final float J = 3.5F;
   private static final float eX = 0.3F;
   private static final float ex = 0.45F;
   private static final float X = 0.12F;
   private static final float m = 0.2F;
   private static final float e1 = 0.12F;
   private static final float ei = 0.6F;
   private static final float eB = 1.6F;
   private static final float n = 28.0F;
   private static final float x = 80.0F;
   private static final float e6 = 8.0F;
   private static final float[] ed = new float[]{-0.55F, -0.3F, 0.0F, 0.3F, 0.55F};
   private static final float[] ey = new float[]{0.16F, 0.3F, 0.44F, 0.58F};
   private static final float eW = 3.0F;
   private static final float eK = 256.0F;
   private static final float h = 14.0F;
   private static final float V = 0.25F;
   private static final float r = 0.35F;
   private static final float eo = 6.0F;
   private static final float e2 = 0.35F;
   private static final double w = 6.0;
   private static final double A = 14.0;
   private static final Matrix4f e4 = new Matrix4f();
   private static final Matrix4f eJ = new Matrix4f();
   private static final Matrix4f H = new Matrix4f();
   private static final Matrix4f ej = new Matrix4f();
   private static final Vector4f eO = new Vector4f();
   private static final Vector3f eI = new Vector3f(0.0F, 0.0F, 1.0F);
   private static final Vector3f eD = new Vector3f(0.0F, 1.0F, 0.0F);
   private static final Vector3f o = new Vector3f(-1.0F, 0.0F, 0.0F);
   private static double ec;
   private static double D;
   private static double eP;
   private static float es;
   private static float Y;
   private static boolean Z;
   private static boolean eN;
   private static boolean eQ;
   private static float P = Float.MAX_VALUE;
   private static boolean K;
   private static float ek = -100.0F;
   private static final SpotifyUpdater4<SpotifyRenderer5.TypeL> F = new SpotifyUpdater4<>();
   private static final SpotifySupport ev = new SpotifySupport();
   private static final SpotifyRenderer.TypeO eR = new SpotifyRenderer.TypeO();
   private static final SpotifyRenderer5.TypeD eZ = new SpotifyRenderer5.TypeD();
   private static SpotifyRenderer5.TypeN O;
   private static long e = 0L;
   private static class_638 S;
   private static int eH = 0;
   private static boolean d;
   private static boolean eg;
   private static boolean en;
   private static float N;
   private static float e0;
   private static final SpotifyUpdater2<SpotifyRenderer5.TypeL> eE = new SpotifyUpdater2<>();
   private static final SpotifyRenderer5.TypeP B = new SpotifyRenderer5.TypeP();
   private static boolean eb;
   private static int E = 1;
   private static class_1309 el;
   private static float eU = -100.0F;
   private static class_1309 t;
   private static float l = -100.0F;
   private static class_1297 y;
   private static float eA;
   private static class_1309 ef;
   private static float ea;
   private static final SpotifySupport.TypeU e_ = new SpotifySupport.TypeU();
   private static final float[] R = new float[4];
   private static final float[] e8 = new float[2];
   private static final Vector3f z = new Vector3f();
   private static final Vector3f v = new Vector3f();
   private static final Vector3f et = new Vector3f();
   private static final Vector3f T = new Vector3f();
   private static final Matrix4f e3 = new Matrix4f();
   private static float q = 1.0F;
   private static double eC;
   private static double L;
   private static float eS;
   private static int i = 0;
   private static boolean e7;
   private static boolean eY;
   private static boolean g;
   private static boolean eq;
   private static boolean p;
   private static final long[] fb = new long[]{7552787752051463516L, 6611659068413600988L, 3305024939086391672L, 9007932081104627696L, 4270426458973792179L};
   private static final Long[] gb = new Long[5];

   private SpotifyRenderer5() {
   }

   private static SpotifyModule e() {
      return SpotifyRegistry.y;
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2) {
      eQ = true;
      e4.set(var2);
      eJ.set(var2).mul(var1);
      class_243 var3 = var0.method_71156();
      ec = var3.field_1352;
      D = var3.field_1351;
      eP = var3.field_1350;
      es = var0.method_19330();
      Y = var0.method_19329();
      Vector3fc var4 = var0.method_19335();
      Vector3fc var5 = var0.method_19336();
      Vector3fc var6 = var0.method_35689();
      eI.set(var4);
      eD.set(var5);
      o.set(var6).negate();
      Z = var0.method_19333();
      class_5636 var7 = var0.method_19334();
      eN = var7 == class_5636.field_27886 || var7 == class_5636.field_27885;
      P = Float.MAX_VALUE;
      class_310 var8 = class_310.method_1551();
      if (var8.field_1690.method_1632() != class_4063.field_18162) {
         try {
            P = (Float)var0.method_75756().method_75689(class_12206.field_63748, E()) - 0.5F;
         } catch (RuntimeException var10) {
            P = 191.5F;
         }
      }
   }

   public static void render() {
      long var0 = 80190985338172L;
      long var2 = var0 ^ 49437834628779L;
      int var4 = (int)((var0 ^ 49437834628779L) >>> 48);
      int var5 = (int)((var0 ^ 49437834628779L) << 16 >>> 32);
      int var6 = (int)(var2 << 48 >>> 48);
      boolean var7 = eQ;
      eQ = false;
      long var8 = System.nanoTime();
      float var10 = e == 0L ? 0.0F : Math.min(0.1F, (float)(var8 - e) / 1.0E9F);
      e = var8;
      ea += var10;

      try {
         Q((short)var4, var5, var7, var10, var6);
         eC = eC + ((System.nanoTime() - var8) / 1000000.0 - eC) * 0.05;
      } catch (RuntimeException var12) {
         ClientServices.LOGGER.error("Spotify Player: the lyrics in the world failed", var12);
         F.clear();
      }
   }

   private static void Q(short var0, int var1, boolean var2, float var3, int var4) {
      long var5 = 110055578121111L;
      long var7 = var5 ^ 40734408659853L;
      long var9 = var5 ^ 18036909732938L;
      long var11 = var5 ^ 30214658133695L;
      long var13 = var5 ^ 38769074314965L;
      long var15 = var5 ^ 93118167704407L;
      int var17 = (int)((var5 ^ 93118167704407L) >>> 48);
      int var18 = (int)((var5 ^ 93118167704407L) << 16 >>> 48);
      int var19 = (int)(var15 << 32 >>> 32);
      long var20 = var5 ^ 119112604753372L;
      class_310 var22 = class_310.method_1551();
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifyModule var23 = e();
      class_638 var24 = var22.field_1687;
      class_746 var25 = var22.field_1724;
      if (var24 != S) {
         S = var24;
         i();
      }

      boolean var26 = var23.enabled() && var23.inWorld() && var24 != null && var25 != null;
      if ((var26 || !F.X.isEmpty() || !eE.h.isEmpty()) && var24 != null && var25 != null && var2) {
         if (var26) {
            var23.frame(var3);
         }

         SpotifyCurve var27;
         boolean var41;
         label175: {
            var27 = SpotifyRegistry.get().pick(false);
            char var29 = '뎗';
            if (var26) {
               var41 = var23.visible(var27);
               var29 = '뎗';
               if (var41 && !var22.field_1690.field_1842 && !ClientTheme.active()) {
                  var41 = true;
                  break label175;
               }
            }

            var41 = false;
         }

         boolean var31;
         boolean var51;
         SpotifyRenderer5.TypeN var30;
         label167: {
            var51 = var41;
            var30 = SpotifyRenderer5.TypeN.of(var22);
            O = var30;
            var31 = G(var25, var20);
            if (!var23.inSky()) {
               var41 = var23.groundAndSky();
               if (!var41 || !var31 && O(var9, var25, var30)) {
                  var41 = false;
                  break label167;
               }
            }

            var41 = true;
         }

         label153: {
            boolean var44 = var23.groundAndSky();
            int var33 = 1679314851;
            N = var44 && var41 != eg ? N + var3 : 0.0F;
            var41 = var23.groundAndSky();
            if (var41) {
               float var34 = N - (var41 ? 0.3F : 0.45F);
               var33 = 1679314851;
               if (Float.compare(var34, 0.0F) < 0) {
                  break label153;
               }
            }

            eg = var41;
            N = 0.0F;
         }

         en = !eg && !var31;
         boolean var52 = eg;
         int var54 = 0;
         if (var52 != d) {
            d = var52;
            var51 = false;
         }

         boolean var57 = var23.F.is("Whole line");
         eb = !var52 && !var57;
         F.update(var27, var51, e7 ? 0.0F : MotionStyle.MENU.dt(var3), var23.p.intValue(), var57, v(var7, var23), eZ);
         double var35 = var27.lyricsTime();
         if (!e7) {
            boolean var37;
            label133: {
               boolean var38 = false;
               if (var51) {
                  var38 = false;
                  if (eb && en) {
                     var37 = true;
                     break label133;
                  }
               }

               var37 = false;
            }

            eE.update(F, var35, var37, B);
         }

         eR.B = var23.F.is("Whole line");
         var41 = true;
         eR.J = var23.e.enabled();
         eR.g = var23.G.is("Album") ? var27.accent() : Icons.PURPLE;
         eR.o = var23.pausedAlpha(var27);
         eR.r = var27.level();
         eR.p = RenderPipelines.get().time();
         float var58 = F.clock();

         for (SpotifyUpdater4.TypeM var39 : F.X) {
            int var52x = false;
            if (var39.Z != null && !((SpotifyRenderer5.TypeL)var39.Z).X && ((SpotifyRenderer5.TypeL)var39.Z).N == SpotifyRenderer5.TypeT.j) {
               S((SpotifyRenderer5.TypeL)var39.Z, var13, var39.t.c * 64.0F, eR.o * SpotifyRenderer.presence(var39, var35, var58, eR));
            }
         }

         for (SpotifyUpdater2.TypeH var40 : eE.h) {
            if (var40.Y != null) {
               S((SpotifyRenderer5.TypeL)var40.Y, var13, var40.width(), eR.o * SpotifyRenderer.presence(var40, var58));
            }
         }

         var41 = true;

         for (SpotifyUpdater4.TypeM var64 : F.X) {
            if (var64.Z != null && !((SpotifyRenderer5.TypeL)var64.Z).X) {
               c(var64, var35, var30, var11, var3);
            }
         }

         var41 = true;

         for (SpotifyUpdater2.TypeH var65 : eE.h) {
            var54 = 1679314851;
            if (var65.Y != null) {
               q(var65, (short)var17, var30, (char)var18, var3, var19);
            }
         }
      }
   }

   private static float v(long var0, SpotifyModule var2) {
      SpotifyCurve.ensureSourceClassInitialized();
      String var3 = (String)var2.c.get();
      byte var4 = -1;
      switch (var3.hashCode()) {
         case -1968802693:
            if (var3.equals("Narrow")) {
               long var5 = 71556174110746L;
               int var7 = var5 < 0L ? -1 : 1;
               var4 = 0;
            }
            break;
         case 2695923:
            if (var3.equals("Wide")) {
               var4 = 1;
            }
      }
      return switch (var4) {
         case 0 -> 9.5F;
         case 1 -> 16.0F;
         default -> 12.5F;
      };
   }

   private static float T() {
      return e().L.floatValue();
   }

   private static void i() {
      F.clear();
      eE.clear();
      ev.forgetLast();
      E = 1;
      el = null;
      t = null;
      y = null;
      ef = null;
   }

   private static void R(long var0, SpotifyRenderer5.TypeL var2, SpotifyUpdater4.TypeN var3, SpotifyRenderer5.TypeN var4) {
      long var5 = var0 ^ 82820136735203L;
      Z(var2, var3.C, var4, var5);
   }

   private static void Z(SpotifyRenderer5.TypeL var0, float var1, SpotifyRenderer5.TypeN var2, long var3) {
      SpotifyCurve.ensureSourceClassInitialized();
      float var5;
      switch (var0.N) {
         case j:
            V();
            var5 = var1 > 0.0F ? (0.12F + var1 / 2.0F) * var0.i : 0.0F;
            if (var3 > 1L) {
               T.set((float)(var0.H - ec) + z.x * var5, (float)(var0.j - D) + z.y * var5, (float)(var0.y - eP) + z.z * var5);
               q = 1.0F;
               return;
            }
            break;
         case T:
            var5 = (float)Math.toRadians(var0.O);
            break;
         default:
            return;
      }

      float var6 = (float)Math.toRadians(var0.l);
      Vector3f var7 = new Vector3f((float)(-Math.sin(var5) * Math.cos(var6)), (float)Math.sin(var6), (float)(Math.cos(var5) * Math.cos(var6)));
      Vector3f var8 = new Vector3f(-var7.z, 0.0F, var7.x).normalize();
      Vector3f var9 = var8.cross(var7, new Vector3f()).normalize();
      float var10 = (float)Math.cos(var0.u);
      float var11 = (float)Math.sin(var0.u);
      v.set(var8).mul(var10).add(var9.x * var11, var9.y * var11, var9.z * var11);
      z.set(var9).mul(var10).sub(var8.x * var11, var8.y * var11, var8.z * var11);
      et.set(var7).negate();
      T.set(var7).mul(var0.f);
      q = var0.f / 80.0F;
   }

   private static void V() {
      v.set(o);
      z.set(eD);
      et.set(eI).negate();
   }

   private static void R(SpotifyRenderer5.TypeL var0) {
      double var1 = Math.toRadians(es);
      float var3 = (float)(-Math.sin(var1));
      float var4 = (float)Math.cos(var1);
      v.set(-var4, 0.0F, var3);
      z.set(var3, 0.0F, var4);
      et.set(0.0F, 1.0F, 0.0F);
      T.set((float)(var0.H - ec), (float)(var0.j + 0.03F - D), (float)(var0.y - eP));
   }

   private static void c(SpotifyUpdater4.TypeM var0, double var1, SpotifyRenderer5.TypeN var3, long var4, float var6) {
      long var7 = 46143330451785L;
      long var9 = 21257358388504L;
      byte var11 = 0;
      int var12 = 324361547;
      int var13 = (int)(var9 << 48 >>> 48);
      var9 = 84239354017570L;
      short var14 = 19613;
      char var15 = '胅';
      int var16 = (int)(var9 << 48 >>> 48);
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifyRenderer5.TypeL var17 = (SpotifyRenderer5.TypeL)var0.Z;
      SpotifyUpdater4.TypeN var18 = var0.t;
      eR.L = var17.R;
      eR.Y = var17.N == SpotifyRenderer5.TypeT.T;
      boolean var19 = var17.N == SpotifyRenderer5.TypeT.T && e().onlyOpenSky();
      long var20 = 140061741202728L;
      int var26 = var20 < 0L ? -1 : 1;
      if (var17.N != SpotifyRenderer5.TypeT.j && !var19) {
         k(var17, var18, var3, var14, var15, (char)var16, var6);
      }

      R(var7, var17, var18, var3);
      var17.P = H(var18, var17, var11, var12, var13, 0.0F, e_);
      if (var17.P) {
         var17.U.set(e_);
      }

      float var26x = var17.G * q / 64.0F;
      float var27 = var17.i * q / 64.0F;
      H.set(v.x * var26x, v.y * var26x, v.z * var26x, 0.0F, -z.x * var27, -z.y * var27, -z.z * var27, 0.0F, et.x, et.y, et.z, 0.0F, T.x, T.y, T.z, 1.0F);
      ej.set(eJ).mul(H);
      float var28 = var17.N == SpotifyRenderer5.TypeT.j ? 0.45F : 0.25F;
      RenderPipelines var29 = RenderPipelines.get();
      TypeJ var30 = null;
      if (var19) {
         float var31 = l();
         var30 = new TypeJ(e3.set(H), (float)D, P, var31 - class_3532.method_15363(var31 / 10.0F, 4.0F, 64.0F), var31);
      }

      Canvas var33 = var29.beginSheet(
         ej, var18.c * 64.0F, var18.C * 64.0F, var17.n / 64.0F, e4, var28, var30, var17.N == SpotifyRenderer5.TypeT.j ? B(var17) : null
      );
      SpotifyRenderer.draw(var33, var0, var1, F.clock(), eR);
      var29.flushSheet();
      SpotifyRegistry.metric("lyrics_drawn");
   }

   private static void q(SpotifyUpdater2.TypeH var0, short var1, SpotifyRenderer5.TypeN var2, char var3, float var4, int var5) {
      long var6 = 53500477001920L;
      long var8 = var6 ^ 98441974459768L;
      long var10 = var6 ^ 8888325618028L;
      long var12 = var6 ^ 90211642753130L;
      SpotifyRenderer5.TypeL var14 = (SpotifyRenderer5.TypeL)var0.Y;
      SpotifyCurve.ensureSourceClassInitialized();
      eR.L = var14.R;
      eR.Y = false;
      float var15 = var0.width() / 64.0F;
      float var16 = SpotifyRenderer.standHeight(var0);
      var14.P = m(var14, var15, var16, var8, var14.U);
      boolean var17 = var14.P && SpotifySupport.shown(var14.U) >= 0.45F && !c(var14, var15, var10, var16, var4);
      var14.b = var17 ? Math.max(0.0F, var14.b - var4) : var14.b + var4;
      Z(var14, 0.0F, var2, var12);
      float var18 = var14.G / 64.0F;
      H.set(v.x * var18, v.y * var18, v.z * var18, 0.0F, -z.x * var18, -z.y * var18, -z.z * var18, 0.0F, et.x, et.y, et.z, 0.0F, T.x, T.y, T.z, 1.0F);
      ej.set(eJ).mul(H);
      RenderPipelines var19 = RenderPipelines.get();
      Canvas var20 = var19.beginSheet(ej, var0.width(), var16 * 64.0F, var14.n / 64.0F, e4, 0.45F, null, B(var14));
      SpotifyRenderer.stand(var20, var0, F.clock(), eR);
      var19.flushSheet();
      SpotifyRegistry.metric("lyrics_drawn");
   }

   private static TypeW B(SpotifyRenderer5.TypeL var0) {
      return new TypeW(new Matrix4f(H), (float)(var0.j + 0.9F - D));
   }

   private static boolean c(SpotifyRenderer5.TypeL var0, float var1, long var2, float var4, float var5) {
      long var6 = 76765914713047L;
      SpotifyCurve.ensureSourceClassInitialized();
      var0.e -= var5;
      class_638 var8 = class_310.method_1551().field_1687;
      if (!(var0.e > 0.0F) && var8 != null) {
         var0.e = 0.12F;
         V();
         float var9 = var1 * var0.G / 2.0F * 0.7F;
         float var10 = var4 * var0.i;
         class_243 var11 = new class_243(ec, D, eP);
         float[][] var12 = new float[][]{{0.0F, 0.55F}, {-var9, 0.45F}, {var9, 0.45F}};
         int var13 = 0;

         for (float[] var17 : var12) {
            long var18 = 62388519889324L;
            int var21 = var18 < 0L ? -1 : 1;
            float var21x = var10 * var17[1];
            class_243 var22 = new class_243(
               var0.H + v.x * var17[0] + z.x * var21x, var0.j + v.y * var17[0] + z.y * var21x, var0.y + v.z * var17[0] + z.z * var21x
            );
            long var23 = 62388519889323L;
            int var24 = var23 < 0L ? -1 : 1;
            if (!p(var8, var11, var22, var6, class_3960.field_23142)) {
               var13++;
            }
         }

         long var25 = 62388519889324L;
         int var26 = var25 < 0L ? -1 : 1;
         long var26x = 62388519889324L;
         int var28 = var26x < 0L ? -1 : 1;
         var0.Y = var13 >= 2;
         return var0.Y;
      } else {
         return var0.Y;
      }
   }

   private static void S(SpotifyRenderer5.TypeL var0, long var1, float var3, float var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      if (var0.s && !(var4 <= 0.002F)) {
         eR.L = var0.R;
         R(var0);
         float var5 = var0.G / 64.0F;
         H.set(v.x * var5, v.y * var5, v.z * var5, 0.0F, -z.x * var5, -z.y * var5, -z.z * var5, 0.0F, et.x, et.y, et.z, 0.0F, T.x, T.y, T.z, 1.0F);
         ej.set(eJ).mul(H);
         RenderPipelines var6 = RenderPipelines.get();
         Canvas var7 = var6.beginSheet(ej, var3, 64.0F, var0.n / 64.0F, e4, 0.0F);
         SpotifyRenderer.shadow(var7, var3, var4, eR);
         var6.flushSheet();
         SpotifyRegistry.metric("lyrics_drawn");
      }
   }

   private static boolean m(SpotifyRenderer5.TypeL var0, float var1, float var2, long var3, SpotifySupport.TypeU var5) {
      long var6 = 129881390158680L;
      SpotifyCurve.ensureSourceClassInitialized();
      V();
      float var8 = var1 * var0.G / 2.0F;
      float var9 = var2 * var0.i;
      float var10 = (float)(var0.H - ec);
      float var11 = (float)(var0.j - D);
      float var12 = (float)(var0.y - eP);
      var5.empty();

      for (int var13 = 0; var13 < 4; var13++) {
         long var14 = 115589415075256L;
         int var17 = var14 < 0L ? -1 : 1;
         float var17x = (var13 & 1) == 0 ? -var8 : var8;
         float var18 = (var13 & 2) == 0 ? 0.0F : var9;
         long var19 = 115589415075255L;
         int var21 = var19 < 0L ? -1 : 1;
         boolean var21x = O(var10 + v.x * var17x + z.x * var18, var11 + v.y * var17x + z.y * var18, var6, var12 + v.z * var17x + z.z * var18);
         long var22 = 115589415075256L;
         int var23 = var22 < 0L ? -1 : 1;
         if (!var21x) {
            return false;
         }

         var5.add(e8[0], e8[1]);
      }

      long var24 = 115589415075255L;
      int var15 = var24 < 0L ? -1 : 1;
      return var5.valid();
   }

   private static void k(SpotifyRenderer5.TypeL var0, SpotifyUpdater4.TypeN var1, SpotifyRenderer5.TypeN var2, int var3, int var4, char var5, float var6) {
      long var7 = 84239354017570L;
      long var9 = var7 ^ 111506278037099L;
      long var11 = var7 ^ 37145663993325L;
      SpotifyCurve.ensureSourceClassInitialized();
      class_638 var13 = class_310.method_1551().field_1687;
      float var14 = 80.0F;
      float var15 = 1.0F;
      if (var13 != null && !w(var11)) {
         var0.f = var14;
         R(var9, var0, var1, var2);
         class_243 var16 = new class_243(ec, D, eP);
         class_242 var17 = eN ? class_242.field_1348 : class_242.field_1347;
         class_243 var18 = var16.method_1031(T.x, T.y, T.z);
         class_3965 var19 = var13.method_17742(new class_3959(var16, var18, class_3960.field_23142, var17, class_3726.method_16194()));
         if (var19.method_17783() != class_240.field_1333) {
            var15 = Math.min(var15, (float)(var19.method_17784().method_1022(var16) / Math.max(0.001, var18.method_1022(var16))));
         }
      }

      float var20 = class_3532.method_15363(var14 * var15 - 2.0F, 8.0F, var14);
      float var21 = var0.Q ? var0.E : var20;
      float var22 = var20 < var21 ? 30.0F : 3.0F;
      var0.E = var21 + (var20 - var21) * (1.0F - (float)Math.exp(-var22 * var6));
      var0.Q = true;
      var0.f = var0.E;
   }

   private static boolean H(SpotifyUpdater4.TypeN var0, SpotifyRenderer5.TypeL var1, short var2, int var3, int var4, float var5, SpotifySupport.TypeU var6) {
      long var7 = 21257358388504L;
      long var9 = var7 ^ 111553241431616L;
      SpotifyCurve.ensureSourceClassInitialized();
      float var11 = var0.c * var1.G * q / 2.0F + var5;
      float var12 = var0.C * var1.i * q / 2.0F + var5;
      var6.empty();

      for (int var13 = 0; var13 < 4; var13++) {
         int var14 = 44312;
         float var15 = (var13 & 1) == 0 ? -var11 : var11;
         float var16 = (var13 & 2) == 0 ? -var12 : var12;
         float var17 = T.x + v.x * var15 + z.x * var16;
         float var18 = T.y + v.y * var15 + z.y * var16;
         float var19 = T.z + v.z * var15 + z.z * var16;
         int var21 = 44312;
         boolean var20 = O(var17, var18, var9, var19);
         var21 = 324361547;
         if (!var20) {
            return false;
         }

         var6.add(e8[0], e8[1]);
      }

      return var6.valid();
   }

   private static boolean A(long var0, SpotifyRenderer5.TypeL var2) {
      SpotifyCurve.ensureSourceClassInitialized();
      double var3 = var2.H - ec;
      double var5 = var2.y - eP;
      double var7 = Math.sqrt(var3 * var3 + var5 * var5);
      return var7 < 28.0 && var7 > Math.max(1.6F, var2.T * 0.6F);
   }

   private static SpotifyRenderer5.TypeL L(class_638 var0, long var1, class_746 var3, SpotifyRenderer5.TypeN var4, SpotifyUpdater4.TypeM var5) {
      short var6 = 19602;
      long var7 = 2043162418L;
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifyUpdater4.TypeN var9 = var5.t;
      int var10 = 0;
      if (var9.k == SpotifyUpdater4.TypeA.G) {
         SpotifyRenderer5.TypeL var11 = (SpotifyRenderer5.TypeL)var5.Z;
         long var12 = 122805782298058L;
         int var15 = var12 < 0L ? -1 : 1;
         if (var11 != null && var11.N == SpotifyRenderer5.TypeT.j && var11.V != 0) {
            long var15x = 122805782298058L;
            int var16 = var15x < 0L ? -1 : 1;
            var10 = var11.V;
         } else {
            E = -E;
            var10 = E;
         }
      }

      return f(var0, var6, var3, var4, var9.c, 0.12F + var9.C, var7, var10);
   }

   private static SpotifyRenderer5.TypeL f(class_638 var0, int var1, class_746 var2, SpotifyRenderer5.TypeN var3, float var4, float var5, long var6, int var8) {
      long var9 = 84191992098610L;
      long var11 = var9 ^ 45688044364384L;
      long var13 = var9 ^ 96495244133474L;
      double var15 = Math.toRadians(es);
      SpotifyCurve.ensureSourceClassInitialized();
      float var17 = (float)(-Math.sin(var15));
      float var18 = (float)Math.cos(var15);
      float var19 = T();
      float var20 = Math.max(2.2F, var19 + Math.min(9.0F, l(var11, var2, var17, var18) * 0.75F));
      SpotifyRenderer5.TypeL var21 = null;
      float var22 = Float.NEGATIVE_INFINITY;
      int var23 = 0;

      for (int var24 = 0; var24 < I.length; var24++) {
         float var25 = var20 * I[var24];
         short var26 = 19602;
         float var27;
         int var28 = (var27 = var25 - 2.2F) == 0.0F ? 0 : (var27 < 0.0F ? -1 : 1);
         var26 = 19602;
         if (var28 < 0) {
            var26 = 19602;
         } else {
            SpotifyRenderer5.TypeL var29 = d(var0, var2, var3, var17, var18, var25, var13, var19, var4, var5, var8);
            var23++;
            float var30 = var29.P ? SpotifySupport.inside(var29.U) : 0.0F;
            float var31 = var29.P ? ev.covered(var29.U) : 0.0F;
            float var32 = (float)(var29.j - var2.method_23318());
            if (var29.s && !(var30 < 0.9F) && !(var29.q < 0.74F) && !(var32 > 2.6F)) {
               float var33 = -var32;
               if (!(var33 > Math.max(4.0F, var25 * 0.6F))) {
                  float var34 = Math.max(0.0F, var32 - 1.2F);
                  long var35 = 2043162417L;
                  int var37 = var35 < 0L ? -1 : 1;
                  float var37x = 1.0F
                     - 3.0F * (1.0F - var30)
                     - var31
                     - 1.5F * (1.0F - var29.q)
                     - (var25 < var20 ? 0.2F : 0.1F) * Math.abs(var25 - var20)
                     - (var29.s ? 0.0F : 1.0F)
                     - 0.3F * var34;
                  long var38 = 2043162417L;
                  int var41 = var38 < 0L ? -1 : 1;
                  if (eq) {
                     ClientServices.LOGGER
                        .info(
                           String.format(
                              Locale.ROOT,
                              "Spotify dev: standing %.1f blocks (side %d): inside %.2f, covered %.2f, seen %.2f, ground %s, score %.2f, box %.2f,%.2f..%.2f,%.2f",
                              var25,
                              var8,
                              var30,
                              var31,
                              var29.q,
                              var29.s,
                              var37x,
                              var29.U.b,
                              var29.U.E,
                              var29.U.T,
                              var29.U.G
                           )
                        );
                  }

                  float var41x;
                  var28 = (var41x = var37x - var22) == 0.0F ? 0 : (var41x < 0.0F ? -1 : 1);
                  long var42 = 2043162417L;
                  int var44 = var42 < 0L ? -1 : 1;
                  if (var28 > 0) {
                     var22 = var37x;
                     var21 = var29;
                  }

                  if (var24 == 0 && var37x >= 0.8F) {
                     long var44x = 2043162417L;
                     int var45 = var44x < 0L ? -1 : 1;
                     break;
                  }
                  continue;
               }
            }

            if (eq) {
               ClientServices.LOGGER
                  .info(
                     String.format(
                        Locale.ROOT,
                        "Spotify dev: standing %.1f blocks (side %d): not taken (ground %s, inside %.2f, seen %.2f, %+.1f blocks from your feet)",
                        var25,
                        var8,
                        var29.s,
                        var30,
                        var29.q,
                        var32
                     )
                  );
               long var50 = 2043162417L;
               int var35x = var50 < 0L ? -1 : 1;
            }
         }
      }

      i = var23;
      long var46 = 2043162418L;
      int var49 = var46 < 0L ? -1 : 1;
      eS = var22;
      eq = false;
      if (var21 != null) {
         var21.R = u(var0, var21.H, var21.j, var21.y);
      }

      return var21;
   }

   private static SpotifyRenderer5.TypeL d(
      class_638 var0,
      class_746 var1,
      SpotifyRenderer5.TypeN var2,
      float var3,
      float var4,
      float var5,
      long var6,
      float var8,
      float var9,
      float var10,
      int var11
   ) {
      long var12 = 115589415075256L;
      long var14 = 6164559453966L;
      byte var16 = 0;
      short var17 = 1435;
      int var18 = (int)(var14 << 32 >>> 32);
      var14 = 49894323168759L;
      short var19 = 11616;
      char var20 = '\ued68';
      int var21 = (int)(var14 << 48 >>> 48);
      long var22 = 138854403468967L;
      SpotifyCurve.ensureSourceClassInitialized();
      float var24 = -var4;
      float var25 = var3;
      class_242 var26 = eN ? class_242.field_1348 : class_242.field_1347;
      float var27 = l(var19, var2, var20, false, (short)var21) * (float)Math.pow(var8 / var5, 0.3F);
      double var28 = var1.method_23318();
      long var30 = 30036677094223L;
      int var33 = var30 < 0L ? -1 : 1;
      float var33x = var2.em(var27, N(var5, (float)(var28 - D)));
      float var34 = var11 * (0.075F * var5 * var2.across() + var9 * var33x / 2.0F);
      double var35 = ec + var3 * var5 + var24 * var34;
      double var37 = eP + var4 * var5 + var3 * var34;
      class_243 var39 = S(var0, var35, var22, var37, var5, var26);
      SpotifyRenderer5.TypeL var40 = new SpotifyRenderer5.TypeL();
      var40.N = SpotifyRenderer5.TypeT.j;
      var40.V = var11;
      var40.s = var39 != null;
      double var41 = var39 != null ? var39.field_1351 : var28;
      boolean var43 = false;
      if (var39 != null) {
         var33x = var2.em(var27, N(var5, (float)(var41 - D) + var10 * var33x / 2.0F));
         var34 = var11 * (0.075F * var5 * var2.across() + var9 * var33x / 2.0F);
         var35 = ec + var3 * var5 + var24 * var34;
         var37 = eP + var4 * var5 + var3 * var34;
         float var44 = var9 * var33x / 2.0F * 0.85F;

         for (byte var45 = -1; var45 <= 1; var45 = (byte)(var45 + 2)) {
            class_243 var46 = S(var0, var35 + var24 * var44 * var45, var22, var37 + var25 * var44 * var45, var5, var26);
            long var47 = 30036677094223L;
            int var50 = var47 < 0L ? -1 : 1;
            long var50x = 30036677094223L;
            int var50x = var50x < 0L ? -1 : 1;
            if (var46 != null) {
               double var53;
               int var32x = (var53 = var41 - var46.field_1351 - 1.1F) == 0.0 ? 0 : (var53 < 0.0 ? -1 : 1);
               long var55 = 30036677094223L;
               int var55x = var55 < 0L ? -1 : 1;
               if (var32x <= 0) {
                  long var58x = 30036677094223L;
                  int var61 = var58x < 0L ? -1 : 1;
                  double var61x;
                  var32x = (var61x = var46.field_1351 - var41) == 0.0 ? 0 : (var61x < 0.0 ? -1 : 1);
                  long var63 = 30036677094223L;
                  int var61x = var63 < 0L ? -1 : 1;
                  if (var32x > 0) {
                     double var66x;
                     var32x = (var66x = var46.field_1351 - var41 - 1.1F) == 0.0 ? 0 : (var66x < 0.0 ? -1 : 1);
                     long var68 = 30036677094224L;
                     int var65 = var68 < 0L ? -1 : 1;
                     if (var32x <= 0) {
                        var41 = var46.field_1351;
                     }
                  }
                  continue;
               }
            }

            var43 = true;
            long var76x = 30036677094224L;
            int var74 = var76x < 0L ? -1 : 1;
         }
      }

      var40.H = var35;
      var40.j = var41;
      var40.y = var37;
      long var75 = 30036677094223L;
      int var70 = var75 < 0L ? -1 : 1;
      var40.G = var33x;
      var40.i = var33x;
      var40.n = l(var19, var2, var20, false, (short)var21);
      var40.T = (float)Math.sqrt((var35 - ec) * (var35 - ec) + (var37 - eP) * (var37 - eP));
      var40.P = m(var40, var9, var10, var12, var40.U);
      var40.q = J(var16, var0, var17, var18, var40, var9, var10) * (var43 ? 0.6F : 1.0F);
      return var40;
   }

   private static float N(float var0, float var1) {
      float var2 = (float)Math.sqrt(eI.x() * eI.x() + eI.z() * eI.z());
      return Math.max(0.5F, var0 * var2 + var1 * eI.y());
   }

   private static float J(short var0, class_638 var1, short var2, int var3, SpotifyRenderer5.TypeL var4, float var5, float var6) {
      long var7 = 6164559453966L;
      long var9 = var7 ^ 70687535633625L;
      V();
      float var11 = var5 * var4.G / 2.0F * 0.8F;
      SpotifyCurve.ensureSourceClassInitialized();
      float var12 = var6 * var4.i;
      class_243 var13 = new class_243(ec, D, eP);
      float[][] var14 = new float[][]{{0.0F, 0.5F}, {-var11, 0.3F}, {var11, 0.3F}, {0.0F, 0.9F}};
      float var15 = 0.0F;

      for (float[] var19 : var14) {
         boolean var20 = false;
         float var21 = var12 * var19[1];
         class_243 var22 = new class_243(var4.H + v.x * var19[0] + z.x * var21, var4.j + v.y * var19[0] + z.y * var21, var4.y + v.z * var19[0] + z.z * var21);
         var20 = false;
         if (p(var1, var13, var22, var9, class_3960.field_23142)) {
            var15++;
         }
      }

      return var15 / var14.length;
   }

   private static class_243 S(class_638 var0, double var1, long var3, double var5, float var7, class_242 var8) {
      SpotifyCurve.ensureSourceClassInitialized();
      double[] var9 = new double[]{D - 0.2, D + 2.5};

      for (double var13 : var9) {
         class_243 var15 = new class_243(var1, var13, var5);
         class_243 var16 = new class_243(var1, var13 - Math.max(12.0F, var7) - 2.7, var5);
         class_3965 var17 = var0.method_17742(new class_3959(var15, var16, class_3960.field_23142, var8, class_3726.method_16194()));
         long var18 = 138854403468966L;
         int var21 = var18 < 0L ? -1 : 1;
         long var21x = 138854403468966L;
         int var23 = var21x < 0L ? -1 : 1;
         if (var17.method_17783() != class_240.field_1333) {
            long var23x = 138854403468967L;
            int var24 = var23x < 0L ? -1 : 1;
            if (!var17.method_17781() && var17.method_17780() == class_2350.field_11036) {
               return var17.method_17784();
            }
         }
      }

      return null;
   }

   private static boolean p(class_638 var0, class_243 var1, class_243 var2, long var3, class_3960 var5) {
      SpotifyCurve.ensureSourceClassInitialized();
      class_3965 var6 = var0.method_17742(new class_3959(var1, var2, var5, class_242.field_1348, class_3726.method_16194()));
      return var6.method_17783() == class_240.field_1333 || var6.method_17784().method_1025(var2) < 0.0025000000000000005;
   }

   private static float l(long var0, class_746 var2, float var3, float var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      long var5 = 111187751570770L;
      int var8 = var5 < 0L ? -1 : 1;
      Object var8x = var2.method_5854() != null ? var2.method_5668() : var2;
      return (float)Math.max(
         0.0,
         (
               (((class_1297)var8x).method_23317() - ((class_1297)var8x).field_6014) * var3
                  + (((class_1297)var8x).method_23321() - ((class_1297)var8x).field_5969) * var4
            )
            * 20.0
      );
   }

   private static boolean G(class_746 var0, long var1) {
      SpotifyCurve.ensureSourceClassInitialized();
      if (!var0.method_31549().field_7479 && !var0.method_7325() && !var0.method_6128() && !var0.method_6123()) {
         class_1297 var3 = var0.method_5668();
         if (!var3.method_24828() && !var3.method_5799() && !var3.method_5771()) {
            class_638 var4 = class_310.method_1551().field_1687;
            if (var4 == null) {
               return false;
            } else {
               class_243 var5 = var3.method_73189();
               class_3965 var6 = var4.method_17742(
                  new class_3959(var5, var5.method_1031(0.0, -3.5, 0.0), class_3960.field_17558, class_242.field_1347, class_3726.method_16194())
               );
               return var6.method_17783() == class_240.field_1333;
            }
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   private static boolean O(long var0, class_746 var2, SpotifyRenderer5.TypeN var3) {
      long var4 = 129881390158680L;
      double var6 = Math.toRadians(es);
      float var8 = Math.max(2.2F, T());
      float var9 = (float)(-Math.sin(var6) * var8);
      float var10 = (float)(Math.cos(var6) * var8);
      SpotifyCurve.ensureSourceClassInitialized();
      float var11 = (float)(var2.method_23318() - D);
      if (O(var9, var11, var4, var10)) {
         long var12 = 128092101887964L;
         int var14 = var12 < 0L ? -1 : 1;
         if (e8[1] > -0.92F && e8[1] < 1.0F) {
            return true;
         }
      }

      return false;
   }

   private static boolean w(long var0) {
      SpotifyCurve.ensureSourceClassInitialized();
      class_746 var2 = class_310.method_1551().field_1724;
      return var2 != null && var2.method_7325();
   }

   private static float l(int var0, SpotifyRenderer5.TypeN var1, int var2, boolean var3, short var4) {
      return (var3 ? 0.074F : 0.078F) * var1.height() * e().X.floatValue();
   }

   private static float w(SpotifyUpdater4.TypeM var0, float var1, long var2) {
      long var4 = 114510153039564L;
      float var6 = (eH & 1) == 0 ? 1.0F : -1.0F;
      return var6 * (float)Math.toRadians(2.5 * (0.33 + 0.67 * SpotifySupport.hash(A(var0, var4) + 3L)));
   }

   private static long A(SpotifyUpdater4.TypeM var0, long var1) {
      return var0.t.x * 7919L + eH * 31L;
   }

   private static SpotifyRenderer5.TypeL d(class_638 var0, long var1, SpotifyRenderer5.TypeN var3, SpotifyUpdater4.TypeM var4) {
      long var5 = 46143330451785L;
      long var7 = 21257358388504L;
      byte var9 = 0;
      int var10 = 324361547;
      int var11 = (int)(var7 << 48 >>> 48);
      long var12 = 126847145608280L;
      var7 = 49894323168759L;
      short var14 = 11616;
      char var15 = '\ued68';
      int var16 = (int)(var7 << 48 >>> 48);
      long var17 = 79639401684027L;
      long var19 = 114510153039564L;
      SpotifyUpdater4.TypeN var21 = var4.t;
      long var22 = A(var4, var19);
      SpotifyCurve.ensureSourceClassInitialized();
      boolean var24 = e().onlyOpenSky();
      float var25 = l(var14, var3, var15, true, (short)var16);
      float var26 = var3.em(var25, 80.0F);
      class_243 var27 = new class_243(ec, D, eP);
      class_242 var28 = eN ? class_242.field_1348 : class_242.field_1347;
      SpotifyRenderer5.TypeL var29 = null;
      float var30 = Float.NEGATIVE_INFINITY;
      float var31 = 1.0F;
      int var32 = 0;

      for (int var33 = 0; var33 < ey.length; var33++) {
         long var34 = 56739217600201L;
         int var84 = var34 < 0L ? -1 : 1;

         for (int var37 = 0; var37 < ed.length; var37++) {
            float var38 = ed[var37] / var3.fx();
            float var39 = ey[var33] / var3.fy();
            long var40 = 56739217600201L;
            int var43 = var40 < 0L ? -1 : 1;
            Vector3f var43x = new Vector3f(eI).add(o.x * var38, o.y * var38, o.z * var38).add(eD.x * var39, eD.y * var39, eD.z * var39).normalize();
            SpotifyRenderer5.TypeL var44 = new SpotifyRenderer5.TypeL();
            var44.N = SpotifyRenderer5.TypeT.T;
            var44.l = (float)Math.toDegrees(Math.asin(class_3532.method_15363(var43x.y, -1.0F, 1.0F)));
            var44.O = (float)Math.toDegrees(Math.atan2(-var43x.x, var43x.z));
            var44.u = w(var4, 2.5F, var12);
            var44.f = 80.0F;
            float var45 = Math.max(0.2F, var43x.dot(eI));
            long var46 = 56739217600202L;
            int var46x = var46 < 0L ? -1 : 1;
            long var49x = 56739217600202L;
            int var55 = var49x < 0L ? -1 : 1;
            var44.G = var26 * (float)Math.pow(var45, 1.5);
            var44.i = var44.G;
            if (var24) {
               if (var44.l < 5.0F) {
                  continue;
               }

               boolean var52 = X(var44.O, var17, var44.l);
               long var53 = 56739217600202L;
               int var59 = var53 < 0L ? -1 : 1;
               if (!var52) {
                  continue;
               }
            }

            R(var5, var44, var21, var3);
            boolean var55x = H(var21, var44, var9, var10, var11, 0.0F, e_);
            long var56 = 56739217600202L;
            int var851 = var56 < 0L ? -1 : 1;
            if (var55x) {
               var32++;
               SpotifySupport.TypeU var59 = new SpotifySupport.TypeU().set(e_);
               float var60 = 1.0F;
               float var61 = var21.c * var44.G / 2.0F * 0.8F;

               for (int var62 = -1; var62 <= 1; var62++) {
                  class_243 var63 = var27.method_1031(T.x + v.x * var61 * var62, T.y + v.y * var61 * var62, T.z + v.z * var61 * var62);
                  class_3965 var64 = var0.method_17742(new class_3959(var27, var63, class_3960.field_23142, var28, class_3726.method_16194()));
                  long var65 = 56739217600201L;
                  int var59x = var65 < 0L ? -1 : 1;
                  if (var64.method_17783() != class_240.field_1333) {
                     var60 = Math.min(var60, (float)(var64.method_17784().method_1022(var27) / var63.method_1022(var27)));
                  }
               }

               float var87 = (SpotifySupport.hash(var22 * 131L + var37 * 17L + var33) - 0.5F) * 0.2F;
               float var88 = 0.25F * Math.max(0.0F, 8.0F - var44.l);
               long var89 = 56739217600201L;
               int var67 = var89 < 0L ? -1 : 1;
               float var67x = var88 + 0.03F * Math.max(0.0F, var44.l - 40.0F);
               long var68 = 56739217600202L;
               int var61x = var68 < 0L ? -1 : 1;
               float var71x = var87 - var67x;
               long var72 = 56739217600201L;
               int var90 = var72 < 0L ? -1 : 1;
               if (var59.E < 0.1F && var59.b < 0.08F) {
                  long var75 = 56739217600201L;
                  int var66 = var75 < 0L ? -1 : 1;
                  if (var59.T > -0.08F) {
                     var71x--;
                  }
               }

               float var90x = ev.score(var59, 1.0F, 1.0F, 1.0F, 1.0F, var60, var71x);
               long var76 = 56739217600202L;
               int var67x = var76 < 0L ? -1 : 1;
               long var79x = 56739217600202L;
               int var69 = var79x < 0L ? -1 : 1;
               if (var90x > var30) {
                  var30 = var90x;
                  var29 = var44;
                  var31 = var60;
                  ev.last(var59.centerX(), var59.centerY());
               }
            }
         }

         long var84x = 56739217600202L;
         int var78 = var84x < 0L ? -1 : 1;
      }

      i = var32;
      long var83 = 56739217600201L;
      int var36 = var83 < 0L ? -1 : 1;
      eS = var30;
      long var36x = 56739217600201L;
      int var75 = var36x < 0L ? -1 : 1;
      if (var29 == null && var24) {
         float var86 = -Y + (float)Math.toDegrees(Math.atan(1.0F / var3.fy()));
         var29 = new SpotifyRenderer5.TypeL();
         var29.N = SpotifyRenderer5.TypeT.T;
         var29.O = es;
         var29.l = class_3532.method_15363(var86 + 14.0F, 24.0F, 70.0F);
         var29.u = w(var4, 2.5F, var12);
         var29.f = 80.0F;
         var29.G = var26 * 0.85F;
         var29.i = var29.G;
         var29.J = X(var29.O, var17, var29.l);
      } else {
         long var85 = 56739217600201L;
         int var791 = var85 < 0L ? -1 : 1;
         if (var29 == null) {
            var29 = new SpotifyRenderer5.TypeL();
            var29.N = SpotifyRenderer5.TypeT.T;
            var29.O = es;
            var29.l = -Y + 10.0F;
            var29.f = 80.0F;
            var29.G = var26;
            var29.i = var26;
         }
      }

      var29.n = var25;
      var29.E = var24 ? 80.0F : class_3532.method_15363(80.0F * var31 - 2.0F, 8.0F, 80.0F);
      var29.f = var29.E;
      var29.Q = true;
      var29.W = 0.25F;
      var29.R = u(var0, ec, D + 2.0, eP);
      return var29;
   }

   private static float l() {
      return class_310.method_1551().field_1690.method_38521() * 16.0F;
   }

   private static boolean X(float var0, long var1, float var3) {
      SpotifyCurve.ensureSourceClassInitialized();
      class_638 var4 = class_310.method_1551().field_1687;
      if (var4 != null) {
         float var5;
         int var6 = (var5 = var3 - 3.0F) == 0.0F ? 0 : (var5 < 0.0F ? -1 : 1);
         long var7 = 79639401684027L;
         int var10 = var7 < 0L ? -1 : 1;
         if (var6 >= 0) {
            double var10x = Math.toRadians(var0);
            double var12 = Math.toRadians(var3);
            double var14 = Math.min(256.0F, l());
            class_243 var16 = new class_243(ec, D, eP);
            class_243 var17 = var16.method_1031(
               -Math.sin(var10x) * Math.cos(var12) * var14, Math.sin(var12) * var14, Math.cos(var10x) * Math.cos(var12) * var14
            );
            class_242 var18 = eN ? class_242.field_1348 : class_242.field_1347;
            return var4.method_17742(new class_3959(var16, var17, class_3960.field_23142, var18, class_3726.method_16194())).method_17783()
               == class_240.field_1333;
         }
      }

      return false;
   }

   private static boolean F(SpotifyRenderer5.TypeN var0, long var1) {
      long var3 = 79639401684027L;
      SpotifyCurve.ensureSourceClassInitialized();
      float var5;
      int var6 = (var5 = ea - ek - 0.25F) == 0.0F ? 0 : (var5 < 0.0F ? -1 : 1);
      long var7 = 49718485484412L;
      int var9 = var7 < 0L ? -1 : 1;
      if (var6 < 0) {
         return K;
      } else {
         ek = ea;
         K = false;

         for (float var13 : new float[]{0.3F, 0.6F}) {
            for (float var17 : new float[]{-0.45F, 0.0F, 0.45F}) {
               long var18 = 49718485484412L;
               int var20 = var18 < 0L ? -1 : 1;
               float var20x = var17 / var0.fx();
               float var21 = var13 / var0.fy();
               Vector3f var22 = new Vector3f(eI).add(o.x * var20x, o.y * var20x, o.z * var20x).add(eD.x * var21, eD.y * var21, eD.z * var21).normalize();
               float var23 = (float)Math.toDegrees(Math.asin(class_3532.method_15363(var22.y, -1.0F, 1.0F)));
               float var24 = (float)Math.toDegrees(Math.atan2(-var22.x, var22.z));
               long var25 = 49718485484412L;
               int var27 = var25 < 0L ? -1 : 1;
               long var27x = 49718485484412L;
               int var28 = var27x < 0L ? -1 : 1;
               if (var23 >= 5.0F && X(var24, var3, var23)) {
                  K = true;
                  return true;
               }
            }

            long var29 = 49718485484413L;
            int var31 = var29 < 0L ? -1 : 1;
         }

         return false;
      }
   }

   private static float u(class_638 var0, double var1, double var3, double var5) {
      int var7 = var0.method_22339(class_2338.method_49637(var1, var3 + 0.5, var5));
      return 1.0F - class_3532.method_15363(var7 / 15.0F, 0.0F, 1.0F);
   }

   private static void t(class_310 var0, class_638 var1, class_746 var2, SpotifyRenderer5.TypeN var3, SpotifyUpdater4.TypeM var4, long var5, List var7) {
      long var8 = 32934739877722L;
      E(var0, var1, var2, var3, var8);
      SpotifyCurve.ensureSourceClassInitialized();

      for (SpotifyUpdater4.TypeM var11 : var7) {
         if (var11 != var4 && var11.Z != null) {
            long var12 = 42162879594931L;
            int var15 = var12 < 0L ? -1 : 1;
            if (!((SpotifyRenderer5.TypeL)var11.Z).P) {
               long var15x = 42162879594930L;
               int var16 = var15x < 0L ? -1 : 1;
            } else {
               ev.written(((SpotifyRenderer5.TypeL)var11.Z).U, var11.leaving() ? 0.3F : Math.max(0.3F, var11.j));
            }
         }
      }

      long var17 = 42162879594931L;
      int var18 = var17 < 0L ? -1 : 1;
   }

   private static void E(class_310 var0, class_638 var1, class_746 var2, SpotifyRenderer5.TypeN var3, long var4) {
      long var6 = 12346923872629L;
      short var8 = 2874;
      char var9 = '븂';
      int var10 = (int)(var6 << 48 >>> 48);
      var6 = 103365549187474L;
      short var11 = 24066;
      char var12 = '\uaad7';
      int var13 = (int)(var6 << 48 >>> 48);
      long var14 = 63562171045163L;
      SpotifyCurve.ensureSourceClassInitialized();
      ev.clear();
      ef = k(var0, var1, var14, var2);
      if (ef != null && l(var8, (short)var9, var10, ef, e_) && e_.onScreen()) {
         ev.obstacle(e_.b - 0.03F, e_.E - 0.03F, e_.T + 0.03F, e_.G + 0.03F, 3.0F);
      }

      k(var11, var0, var12, var1, (char)var13, var2, var3);
   }

   private static void k(int var0, class_310 var1, int var2, class_638 var3, char var4, class_746 var5, SpotifyRenderer5.TypeN var6) {
      long var7 = 103365549187474L;
      long var9 = var7 ^ 20444346245045L;
      long var11 = var7 ^ 93699356070119L;
      int var13 = (int)((var7 ^ 93699356070119L) >>> 32);
      int var14 = (int)((var7 ^ 93699356070119L) << 32 >>> 48);
      int var15 = (int)(var11 << 48 >>> 48);
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifySupport var16 = ev;
      float var17 = 14.0F * var6.guiX();
      float var18 = 14.0F * var6.guiY();
      var16.obstacle(-var17, -var18, var17, var18, 1.5F);
      float var19 = 91.0F * var6.guiX();
      var16.obstacle(-var19, -1.0F, var19, -1.0F + 56.0F * var6.guiY(), 1.4F);
      int var20 = ((BossHealthOverlayAccessor)var1.field_1705.method_1740()).purxiy$events().size();
      if (var20 > 0) {
         var16.obstacle(-var19 - 10.0F * var6.guiX(), 1.0F - (12 + 19 * Math.min(var20, 4)) * var6.guiY(), var19 + 10.0F * var6.guiX(), 1.0F, 0.9F);
      }

      class_266 var21 = var3.method_8428().method_1189(class_8646.field_45157);
      if (var21 != null) {
         int var22 = Math.min(15, var3.method_8428().method_1184(var21).size()) + 1;
         float var23 = var22 * 9 * var6.guiY();
         var16.obstacle(1.0F - 130.0F * var6.guiX(), -var23 / 2.0F - 10.0F * var6.guiY(), 1.0F, var23 / 2.0F + 10.0F * var6.guiY(), 0.9F);
      }

      if (!Z) {
         boolean var36 = var5.method_6068() == class_1306.field_6183;
         float var38 = var36 ? 0.28F : -1.0F;
         float var24 = var36 ? 1.0F : -0.28F;
         var16.obstacle(var38, -1.0F, var24, -0.2F, 2.0F);
         boolean var25 = var5.method_6079().method_7960();
         if (!var25) {
            var16.obstacle(-var24, -1.0F, -var38, -0.3F, 0.6F);
         }
      } else if (l(var13, (short)var14, var15, var5, e_)) {
         var16.obstacle(e_.b, e_.E, e_.T, e_.G, 2.0F);
      }

      RenderPipelines var37 = RenderPipelines.get();
      Canvas var39 = var37.hudCanvas();
      float var40 = Math.max(1.0F, var39.width());
      float var41 = Math.max(1.0F, var39.height());

      for (HudModule var27 : HudSlot.elements()) {
         int var28 = var27.onScreen() ? 1 : 0;
         if (var28 != 0) {
            float var29;
            var28 = (var29 = var27.screenWidth() - 0.0F) == 0.0F ? 0 : (var29 < 0.0F ? -1 : 1);
            if (var28 > 0) {
               float var30 = var27.screenX() / var40 * 2.0F - 1.0F;
               float var31 = (var27.screenX() + var27.screenWidth()) / var40 * 2.0F - 1.0F;
               float var32 = 1.0F - var27.screenY() / var41 * 2.0F;
               float var33 = 1.0F - (var27.screenY() + var27.screenHeight()) / var41 * 2.0F;
               var16.obstacle(var30, var33, var31, var32, 1.2F);
            }
         }
      }

      char var42 = '\uaad7';
      class_238 var44 = new class_238(ec - 14.0, D - 14.0, eP - 14.0, ec + 14.0, D + 14.0, eP + 14.0);
      int var46 = 0;

      for (class_1297 var48 : var3.method_8333((class_1297)null, var44, var0x -> {
         SpotifyCurve.ensureSourceClassInitialized();
         return var0x instanceof class_1657 || var0x instanceof class_1569;
      })) {
         short var49 = 24066;
         if (var48 != var5) {
            var49 = 24066;
            if (var48 != ef) {
               boolean var52 = var48 instanceof class_1309;
               if (var52) {
                  class_1309 var54 = (class_1309)var48;
                  var52 = X(var54, var5, var3, var9);
                  if (var52) {
                     SpotifySupport.TypeU var34 = e_;
                     var49 = 24066;
                     if (l(var13, (short)var14, var15, var48, var34) && e_.onScreen()) {
                        var16.obstacle(e_.b, e_.E, e_.T, e_.G, 0.8F);
                        if (ClientRegistry9.bounds(var48, R)) {
                           var16.obstacle(R[0] * 2.0F - 1.0F, 1.0F - R[3] * 2.0F, R[2] * 2.0F - 1.0F, 1.0F - R[1] * 2.0F, 0.4F);
                        }

                        if (++var46 >= 12) {
                           break;
                        }
                     }
                  }
               }
            }
         }
      }

      var42 = '\uaad7';
   }

   private static class_1309 k(class_310 var0, class_638 var1, long var2, class_746 var4) {
      long var5 = 84227448749607L;
      long var7 = 28561674792534L;
      SpotifyCurve.ensureSourceClassInitialized();
      if (eY) {
         return r(var1, var7, var4);
      } else {
         class_1309 var9 = ModuleRegistry.current(dev.purxiy.loader.fabric.module.ModuleRegistry.TypeL.AURA);
         if (X(var9, var4, var1, var5)) {
            return var9;
         } else {
            class_1309 var10 = ModuleRegistry.current(dev.purxiy.loader.fabric.module.ModuleRegistry.TypeL.MACE);
            if (X(var10, var4, var1, var5)) {
               return var10;
            } else {
               class_1309 var11 = ModuleRegistry.current(dev.purxiy.loader.fabric.module.ModuleRegistry.TypeL.LANCE);
               if (X(var11, var4, var1, var5)) {
                  return var11;
               } else {
                  class_1309 var12 = ClientRegistry10.target();
                  if (X(var12, var4, var1, var5) && RenderPipelines.get().time() - ClientRegistry10.fightAt() < 6.0F) {
                     return var12;
                  } else {
                     class_1282 var13 = var4.method_6081();
                     if (var13 != null) {
                        class_1297 var14 = var13.method_5529();
                        boolean var15 = var14 instanceof class_1309;
                        long var16 = 63562171045162L;
                        int var37 = var16 < 0L ? -1 : 1;
                        if (var15) {
                           class_1309 var19 = (class_1309)var14;
                           long var20 = 63562171045162L;
                           int var22 = var20 < 0L ? -1 : 1;
                           label152:
                           if (var19 != var4) {
                              if (var19 == t) {
                                 long var22x = 63562171045162L;
                                 int var40 = var22x < 0L ? -1 : 1;
                                 float var24 = ea - l;
                                 long var25 = 63562171045163L;
                                 int var28 = var25 < 0L ? -1 : 1;
                                 if (!(var24 > 0.5F)) {
                                    break label152;
                                 }
                              }

                              t = var19;
                              l = ea;
                           }
                        }
                     }

                     float var33;
                     int var34 = (var33 = ea - eU - 6.0F) == 0.0F ? 0 : (var33 < 0.0F ? -1 : 1);
                     long var36 = 63562171045162L;
                     int var371 = var36 < 0L ? -1 : 1;
                     boolean var37x = var34 < 0 && X(el, var4, var1, var5);
                     float var38;
                     var34 = (var38 = ea - l - 6.0F) == 0.0F ? 0 : (var38 < 0.0F ? -1 : 1);
                     long var21 = 63562171045162L;
                     int var23 = var21 < 0L ? -1 : 1;
                     boolean var23x = var34 < 0 && X(t, var4, var1, var5);
                     if (var37x) {
                        long var39 = 63562171045162L;
                        int var25 = var39 < 0L ? -1 : 1;
                        if (!var23x || eU >= l) {
                           return el;
                        }
                     }

                     if (var23x) {
                        return t;
                     } else {
                        class_1297 var40 = var0.field_1692;
                        long var41 = 63562171045163L;
                        int var46 = var41 < 0L ? -1 : 1;
                        long var28 = 63562171045162L;
                        int var28x = var28 < 0L ? -1 : 1;
                        if (var40 instanceof class_1309 var30x && (var30x instanceof class_1657 || var30x instanceof class_1569) && X(var30x, var4, var1, var5)
                           )
                         {
                           long var31 = 63562171045163L;
                           int var31x = var31 < 0L ? -1 : 1;
                           if (var30x.method_5739(var4) <= 6.0) {
                              if (var40 != y) {
                                 y = var40;
                                 eA = ea;
                              }

                              return ea - eA >= 0.35F ? var30x : null;
                           }
                        }

                        y = null;
                        return null;
                     }
                  }
               }
            }
         }
      }
   }

   private static boolean X(class_1309 var0, class_746 var1, class_638 var2, long var3) {
      SpotifyCurve.ensureSourceClassInitialized();
      long var5 = 84227448749607L;
      int var8 = var5 < 0L ? -1 : 1;
      if (var0 != null) {
         long var8x = 84227448749606L;
         int var10 = var8x < 0L ? -1 : 1;
         if (var0 != var1) {
            long var10x = 84227448749606L;
            int var12 = var10x < 0L ? -1 : 1;
            if (var0.method_5805()) {
               long var12x = 84227448749607L;
               int var14 = var12x < 0L ? -1 : 1;
               if (!var0.method_31481()) {
                  long var14x = 84227448749606L;
                  int var16 = var14x < 0L ? -1 : 1;
                  if (var0.method_73183() == var2) {
                     long var16x = 84227448749607L;
                     int var17 = var16x < 0L ? -1 : 1;
                     if (!var0.method_5756(var1) && !(var0 instanceof class_1531) && var0.method_5649(ec, D, eP) < 400.0) {
                        return true;
                     }
                  }
               }
            }
         }
      }

      return false;
   }

   private static class_1309 r(class_638 var0, long var1, class_746 var3) {
      long var4 = 84227448749607L;
      class_1309 var6 = null;
      SpotifyCurve.ensureSourceClassInitialized();
      double var7 = 576.0;

      for (class_1297 var10 : var0.method_18112()) {
         int var11 = var10 instanceof class_1309 ? 1 : 0;
         long var12 = 28561674792533L;
         int var15 = var12 < 0L ? -1 : 1;
         if (var11 != 0) {
            class_1309 var15x = (class_1309)var10;
            int var27 = X(var15x, var3, var0, var4);
            long var16 = 28561674792533L;
            int var18 = var16 < 0L ? -1 : 1;
            if (var27) {
               double var18x = var15x.method_5858(var3);
               long var20 = 28561674792533L;
               int var22 = var20 < 0L ? -1 : 1;
               double var22x;
               var27 = (var22x = var18x - var7) == 0.0 ? 0 : (var22x < 0.0 ? -1 : 1);
               long var24 = 28561674792534L;
               int var25 = var24 < 0L ? -1 : 1;
               if (var27 < 0) {
                  var7 = var18x;
                  var6 = var15x;
               }
            }
         }
      }

      long var26 = 28561674792533L;
      int var29 = var26 < 0L ? -1 : 1;
      return var6;
   }

   private static float E() {
      return class_310.method_1551().method_61966().method_60637(true);
   }

   private static boolean l(int var0, short var1, int var2, class_1297 var3, SpotifySupport.TypeU var4) {
      long var5 = (long)var0 << 32 | 3187802112L | 61813L;
      long var7 = var5 ^ 137554382643757L;
      float var9 = E();
      class_238 var10 = var3.method_5829();
      double var11 = class_3532.method_16436(var9, var3.field_6014, var3.method_23317()) - var3.method_23317();
      SpotifyCurve.ensureSourceClassInitialized();
      double var13 = class_3532.method_16436(var9, var3.field_6036, var3.method_23318()) - var3.method_23318();
      double var15 = class_3532.method_16436(var9, var3.field_5969, var3.method_23321()) - var3.method_23321();
      var4.empty();
      int var17 = 0;

      while (var17 < 8) {
         double var18 = ((var17 & 1) == 0 ? var10.field_1323 : var10.field_1320) + var11;
         double var20 = ((var17 & 2) == 0 ? var10.field_1322 : var10.field_1325) + var13;
         double var22 = ((var17 & 4) == 0 ? var10.field_1321 : var10.field_1324) + var15;
         if (var0 >= 0) {
            boolean var24 = O((float)(var18 - ec), (float)(var20 - D), var7, (float)(var22 - eP));
            if (!var24) {
               return false;
            }

            var4.add(e8[0], e8[1]);
            var17++;
         }
      }

      return var4.valid();
   }

   private static boolean O(float var0, float var1, long var2, float var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      eO.set(var0, var1, var4, 1.0F);
      boolean var5 = false;
      eJ.transform(eO);
      int var6 = var5 ? 1 : 0;
      if (var2 > 1L) {
         float var7;
         var6 = (var7 = eO.w - 0.05F) == 0.0F ? 0 : (var7 < 0.0F ? -1 : 1);
         if (var2 < 1L) {
            return var6 != 0;
         }

         if (var6 <= 0) {
            return false;
         }

         e8[0] = eO.x / eO.w;
         e8[1] = eO.y / eO.w;
         var6 = 1;
      }

      return var6 != 0;
   }

   private static String c(long var0) {
      SpotifyCurve.ensureSourceClassInitialized();
      StringBuilder var2 = new StringBuilder(eb ? "on" : "off");

      for (SpotifyUpdater2.TypeH var4 : eE.h) {
         Locale var5;
         Object[] var6;
         String var7;
         label77: {
            SpotifyRenderer5.TypeL var8 = (SpotifyRenderer5.TypeL)var4.Y;
            var5 = Locale.ROOT;
            var6 = new Object[]{
               var4.W.x,
               var4.g,
               var4.M < 0 ? "L" : "R",
               var8 == null ? -1.0 : Math.sqrt((var8.H - ec) * (var8.H - ec) + (var8.y - eP) * (var8.y - eP)),
               var8 == null ? -1.0F : var8.T,
               var8 == null ? 0.0F : var8.G,
               var8 == null ? 0.0F : var8.U.b,
               var8 == null ? 0.0F : var8.U.E,
               var8 == null ? 0.0F : var8.U.T,
               var8 == null ? 0.0F : var8.U.G,
               null,
               null
            };
            if (var8 != null) {
               long var9 = 10965705623400L;
               int var11 = var9 < 0L ? -1 : 1;
               if (!var8.s) {
                  var7 = " in the air";
                  break label77;
               }
            }

            var7 = "";
         }

         var6[10] = var7;
         boolean var12 = var4.leaving();
         long var13 = 10965705623401L;
         int var14 = var13 < 0L ? -1 : 1;
         var6[11] = var12 ? " going" : "";
         var2.append(String.format(var5, " [#%d.%d %s %.1f blocks (written %.1f) em %.2f box %.2f,%.2f..%.2f,%.2f%s%s]", var6));
      }

      return var2.toString();
   }

   public static void dev(String[] var0) {
      long var1 = 97277540980743L;
      long var3 = var1 ^ 89610595819374L;
      String var5 = var0.length > 1 ? var0[1] : "log";
      switch (var5) {
         case "move":
            g = true;
            break;
         case "candidates":
            eq = true;
            break;
         case "subject":
            eY = var0.length > 2 && Boolean.parseBoolean(var0[2]);
            break;
         case "freeze":
            e7 = var0.length > 2 && Boolean.parseBoolean(var0[2]);
            break;
         case "timing":
            p = var0.length < 3 || Boolean.parseBoolean(var0[2]);
            break;
         default:
            StringBuilder var7 = new StringBuilder();

            for (SpotifyUpdater4.TypeM var9 : F.X) {
               SpotifyRenderer5.TypeL var10 = (SpotifyRenderer5.TypeL)var9.Z;
               String var11;
               if (var10 == null) {
                  var11 = "-";
               } else if (var10.X) {
                  var11 = "spelled word by word";
               } else {
                  switch (var10.N) {
                     case j:
                        var11 = String.format(
                           Locale.ROOT,
                           "standing at %.1f %.2f %.1f (%.1f blocks, side %d)",
                           var10.H,
                           var10.j,
                           var10.y,
                           Math.sqrt((var10.H - ec) * (var10.H - ec) + (var10.y - eP) * (var10.y - eP)),
                           var10.V
                        );
                        break;
                     case T:
                        var11 = String.format(Locale.ROOT, "sky at %.0f/%.0f (%.0f blocks)", var10.O, var10.l, var10.f);
                        break;
                     default:
                        throw new MatchException(null, null);
                  }
               }

               var7.append(
                  String.format(
                     Locale.ROOT,
                     " [%s #%d rank %d%s%s %s em %.2f/%.2f box %.2f,%.2f..%.2f,%.2f]",
                     var9.t.k,
                     var9.t.x,
                     var9.g,
                     var9.leaving() ? " leaving" : "",
                     var9.U ? " ghost" : "",
                     var11,
                     var10 == null ? 0.0F : var10.G,
                     var10 == null ? 0.0F : var10.i,
                     var10 == null ? 0.0F : var10.U.b,
                     var10 == null ? 0.0F : var10.U.E,
                     var10 == null ? 0.0F : var10.U.T,
                     var10 == null ? 0.0F : var10.U.G
                  )
               );
            }

            class_1309 var13 = ef;
            ClientServices.LOGGER
               .info(
                  String.format(
                     Locale.ROOT,
                     "Spotify dev: world lyrics%s, words %s, written %d, last place %.3f ms from %d candidates (score %.2f), subject %s, %d obstacles, %s, cpu %.3f ms",
                     var7,
                     c(var3),
                     eH,
                     L,
                     i,
                     eS,
                     var13 == null ? "none" : var13.method_5864().method_35050(),
                     ev.obstacleCount(),
                     eg ? "in the sky" : (en ? "on the ground" : "in the air (nothing new stands)"),
                     eC
                  )
               );
      }
   }

   static void attacked(class_1657 var0, class_1297 var1) {
      if (var0 == class_310.method_1551().field_1724 && var1 instanceof class_1309 var2) {
         el = var2;
         eU = ea;
      }
   }

   static void closeOwned() {
      el = null;
      t = null;
      eQ = false;
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 30022) & 32767;
      if (gb[var3] == null) {
         gb[var3] = fb[var3] ^ var1;
      }

      return gb[var3];
   }

   private static final class TypeD implements SpotifyUpdater4.Listener<SpotifyRenderer5.TypeL> {
      private static final String a = "Spotify Player: no place for the lyrics in the world";

      public SpotifyRenderer5.TypeL place(SpotifyUpdater4.TypeM<SpotifyRenderer5.TypeL> var1, List<SpotifyUpdater4.TypeM<SpotifyRenderer5.TypeL>> var2) {
         long var3 = 66277715556685L;
         long var5 = var3 ^ 28723479263486L;
         long var7 = var3 ^ 92322615690374L;
         long var9 = var3 ^ 17445542287239L;
         var1.C = SpotifyRenderer5.eb && var1.t.k == SpotifyUpdater4.TypeA.G;
         if (var1.C) {
            SpotifyRenderer5.TypeL var20 = new SpotifyRenderer5.TypeL();
            var20.X = true;
            SpotifyRenderer5.eH++;
            return var20;
         } else {
            long var11 = System.nanoTime();
            class_310 var13 = class_310.method_1551();
            class_638 var14 = var13.field_1687;
            class_746 var15 = var13.field_1724;
            SpotifyRenderer5.TypeN var16 = SpotifyRenderer5.O;
            SpotifyRenderer5.TypeL var17 = null;

            try {
               if (var14 != null && var15 != null && var16 != null && (SpotifyRenderer5.eg || SpotifyRenderer5.en)) {
                  SpotifyRenderer5.t(var13, var14, var15, var16, var1, var5, var2);
                  var17 = SpotifyRenderer5.eg ? SpotifyRenderer5.d(var14, var9, var16, var1) : SpotifyRenderer5.L(var14, var7, var15, var16, var1);
               }
            } catch (RuntimeException var19) {
               ClientServices.LOGGER.error("Spotify Player: no place for the lyrics in the world", var19);
            }

            if (var17 != null) {
               SpotifyRenderer5.eH++;
            }

            SpotifyRenderer5.L = (System.nanoTime() - var11) / 1000000.0;
            return var17;
         }
      }

      @Override
      public boolean keeps(SpotifyUpdater4.TypeM<SpotifyRenderer5.TypeL> var1, float var2) {
         long var3 = 13674646844862L;
         long var5 = var3 ^ 36662325098179L;
         long var7 = var3 ^ 41336722273527L;
         long var9 = var3 ^ 34337143396518L;
         int var11 = (int)((var3 ^ 34337143396518L) >>> 48);
         int var12 = (int)((var3 ^ 34337143396518L) << 16 >>> 32);
         int var13 = (int)(var9 << 48 >>> 48);
         long var14 = var3 ^ 58076915260434L;
         long var16 = var3 ^ 74773741234565L;
         long var18 = var3 ^ 99953664015112L;
         SpotifyRenderer5.TypeL var20 = var1.Z;
         if (SpotifyRenderer5.g) {
            SpotifyRenderer5.g = false;
            return false;
         } else if (var20 != null && var20.X) {
            return SpotifyRenderer5.eb || SpotifyRenderer5.e7;
         } else if (var20 == null) {
            if (!SpotifyRenderer5.e7 && (SpotifyRenderer5.eg || SpotifyRenderer5.en) && !(SpotifyRenderer5.ea < SpotifyRenderer5.e0)) {
               SpotifyRenderer5.e0 = SpotifyRenderer5.ea + 0.2F;
               return false;
            } else {
               return true;
            }
         } else if (SpotifyRenderer5.e7) {
            return true;
         } else {
            SpotifyRenderer5.TypeN var21 = SpotifyRenderer5.O;
            if (var21 == null) {
               return true;
            } else {
               SpotifyRenderer5.R(var7, var20, var1.t, var21);
               float var22 = SpotifyRenderer5.H(var1.t, var20, (short)var11, var12, var13, 0.0F, SpotifyRenderer5.e_)
                  ? SpotifySupport.shown(SpotifyRenderer5.e_)
                  : 0.0F;
               if (var20.N == SpotifyRenderer5.TypeT.T && SpotifyRenderer5.e().onlyOpenSky()) {
                  var20.W -= var2;
                  if (var20.W <= 0.0F) {
                     var20.W = 0.25F;
                     var20.J = SpotifyRenderer5.X(var20.O, var16, var20.l);
                  }

                  boolean var24 = var22 >= 0.45F && var20.J;
                  if (!var24 && SpotifyRenderer5.F(var21, var5)) {
                     var20.b += var2;
                     return var20.b < 0.35F;
                  } else {
                     var20.b = Math.max(0.0F, var20.b - var2);
                     return true;
                  }
               } else {
                  boolean var23 = var20.N == SpotifyRenderer5.TypeT.j
                     && (!SpotifyRenderer5.A(var18, var20) || SpotifyRenderer5.c(var20, var1.t.c, var14, 0.12F + var1.t.C, var2));
                  var20.b = !(var22 < 0.45F) && !var23 ? Math.max(0.0F, var20.b - var2) : var20.b + var2;
                  return var20.b < 0.35F;
               }
            }
         }
      }
   }

   static final class TypeL {
      SpotifyRenderer5.TypeT N = SpotifyRenderer5.TypeT.j;
      double H;
      double j;
      double y;
      int V;
      boolean s = true;
      float u;
      float O;
      float l;
      float f;
      float E;
      boolean Q;
      float G;
      float i;
      float n;
      float R;
      final SpotifySupport.TypeU U = new SpotifySupport.TypeU();
      boolean P;
      float T;
      float q;
      float b;
      boolean Y;
      float e;
      boolean X;
      boolean J = true;
      float W;
   }

   private record TypeN(float D, float S, float g, float x, float h, float F) {
      static SpotifyRenderer5.TypeN of(class_310 var0) {
         class_276 var1 = var0.method_1522();
         float var2 = (float)(1.0 / Math.tan(Math.toRadians(((Integer)var0.field_1690.method_41808().method_41753()).intValue()) / 2.0));
         return new SpotifyRenderer5.TypeN(
            Math.max(1, var1.field_1482),
            Math.max(1, var1.field_1481),
            Math.abs(SpotifyRenderer5.e4.m00()),
            Math.abs(SpotifyRenderer5.e4.m11()),
            var2,
            var0.method_22683().method_4495()
         );
      }

      float guiX() {
         return this.F * 2.0F / this.D;
      }

      float guiY() {
         return this.F * 2.0F / this.S;
      }

      float em(float var1, float var2) {
         return var1 * 2.0F * var2 / (this.h * this.S);
      }

      float across() {
         return this.D / (this.h * this.S);
      }

      public float width() {
         return this.D;
      }

      public float height() {
         return this.S;
      }

      public float fx() {
         return this.g;
      }

      public float fy() {
         return this.x;
      }

      public float chosen() {
         return this.h;
      }

      public float guiScale() {
         return this.F;
      }

      public float floatState24() {
         return this.D;
      }

      public float floatState14() {
         return this.S;
      }

      public float floatState41() {
         return this.g;
      }

      public float floatState1() {
         return this.x;
      }

      public float floatState5() {
         return this.h;
      }

      public float floatState36() {
         return this.F;
      }
   }

   private static final class TypeP implements SpotifyUpdater2.Listener<SpotifyRenderer5.TypeL> {
      private static final String[] b = new String[4];

      public SpotifyRenderer5.TypeL place(SpotifyUpdater2.TypeH<SpotifyRenderer5.TypeL> var1, List<SpotifyUpdater2.TypeH<SpotifyRenderer5.TypeL>> var2) {
         long var3 = 86054046739172L;
         long var5 = var3 ^ 92016245192126L;
         int var7 = (int)((var3 ^ 3120246228438L) >>> 32);
         long var8 = (var3 ^ 3120246228438L) << 32 >>> 32;
         class_310 var10 = class_310.method_1551();
         class_638 var11 = var10.field_1687;
         class_746 var12 = var10.field_1724;
         SpotifyRenderer5.TypeN var13 = SpotifyRenderer5.O;
         if (var11 != null && var12 != null && var13 != null && SpotifyRenderer5.en) {
            long var14 = System.nanoTime();

            Object var17;
            try {
               SpotifyRenderer5.E(var10, var11, var12, var13, var5);

               for (SpotifyUpdater2.TypeH var27 : var2) {
                  if (var27.Y != null && ((SpotifyRenderer5.TypeL)var27.Y).P && !var27.leaving()) {
                     SpotifyRenderer5.ev.written(((SpotifyRenderer5.TypeL)var27.Y).U, 1.0F);
                  }
               }

               SpotifyRenderer5.TypeL var26 = SpotifyRenderer5.f(
                  var11, var7, var12, var13, var1.width() / 64.0F, SpotifyRenderer.standHeight(var1), var8, var1.M
               );
               if (SpotifyRenderer5.p && var26 != null) {
                  double var28 = SpotifyRegistry.get().pick(false).lyricsTime();
                  long var19 = var1.W.F[var1.g];
                  ClientServices.LOGGER
                     .info(
                        String.format(
                           Locale.ROOT,
                           "Spotify dev: word #%d.%d written at %.0f ms, sung at %d ms (%+.0f ms), %s, %.1f blocks, em %.2f",
                           var1.W.x,
                           var1.g,
                           var28,
                           var19,
                           var28 - var19,
                           var1.M < 0 ? "left" : "right",
                           var26.T,
                           var26.G
                        )
                     );
               }

               return var26;
            } catch (RuntimeException var25) {
               ClientServices.LOGGER.error("Spotify Player: no place for a word in the world", var25);
               var17 = null;
            } finally {
               SpotifyRenderer5.L = (System.nanoTime() - var14) / 1000000.0;
            }

            return (SpotifyRenderer5.TypeL)var17;
         } else {
            return null;
         }
      }

      @Override
      public boolean keeps(SpotifyUpdater2.TypeH<SpotifyRenderer5.TypeL> var1) {
         long var2 = 12658757767904L;
         long var4 = var2 ^ 102276024114262L;
         SpotifyRenderer5.TypeL var6 = var1.Y;
         return var6 != null && (SpotifyRenderer5.e7 || SpotifyRenderer5.A(var4, var6) && var6.b < 0.35F);
      }
   }

   static enum TypeT {
      j,
      T;
   }
}
