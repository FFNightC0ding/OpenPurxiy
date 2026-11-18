// Sourced By DevFFNightmare
package dev.purxiy.modules.knife;

import dev.purxiy.anim.Animator;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.RenderPipelines;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import net.minecraft.class_1011;
import net.minecraft.class_11902;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_239;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3966;
import net.minecraft.class_746;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class KnifeSupport3 {
   private static final float y = 0.004166667F;
   private static final float pv = 1.15F;
   private static final float p7 = 0.06F;
   private static final float p4 = 0.12F;
   private static final float H = 0.09F;
   private static final float st = 0.2F;
   private static final float r = 0.32F;
   private static final float o = 0.17F;
   private static final float K = 2.5F;
   private static final float pG = 1.1F;
   private static final float B = 2.0F;
   private static final double Z = 0.0016;
   
   private static Object pr;
   
   private static String ph;
   
   private static KnifeTransform.TypeH b;
   
   private static KnifeApi g;
   private static long x = 0L;
   private static float O;
   private static Random m = new Random();
   
   private static KnifeTransform.TypeU pq;
   private static KnifeSupport3.TypeS pD = KnifeSupport3.TypeS.G;
   private static float pZ;
   private static float T = 1.0F;
   private static int sC = 0;
   private static boolean pa;
   private static boolean V;
   private static boolean pk;
   private static float pd;
   private static float pi = 1.0F;
   
   private static class_1297 pW;
   
   private static class_243 pt;
   private static final KnifeCurve N = new KnifeCurve();
   private static final KnifeCurve pe = new KnifeCurve();
   private static final KnifeCurve M = new KnifeCurve();
   private static final KnifeCurve sR = new KnifeCurve();
   private static final KnifeCurve a = new KnifeCurve();
   private static final KnifeCurve sg = new KnifeCurve();
   private static final KnifeCurve p3 = new KnifeCurve();
   private static final Quaternionf pM = new Quaternionf();
   private static final Quaternionf pJ = new Quaternionf();
   private static final Quaternionf sf = new Quaternionf();
   private static final Quaternionf E = new Quaternionf();
   private static final Quaternionf s3 = new Quaternionf();
   private static final Vector3f w = new Vector3f();
   private static final Vector3f sG = new Vector3f();
   private static final Vector3f p6 = new Vector3f();
   private static final Vector3f pL = new Vector3f();
   private static final Vector3f sZ = new Vector3f();
   private static final Vector3f S = new Vector3f();
   private static final Vector3f ps = new Vector3f();
   private static final Vector3f sw = new Vector3f();
   private static final Quaternionf R = new Quaternionf();
   private static final Quaternionf pl = new Quaternionf();
   private static float sp = 1.0F;
   private static float pp;
   private static final Vector3f U = new Vector3f();
   private static final Vector3f sK = new Vector3f();
   private static final Vector3f pz = new Vector3f(1.0F, 0.0F, 0.0F);
   private static float p9;
   private static float pg;
   private static float sJ;
   private static float h;
   private static float pB = 99.0F;
   private static float sU = 0.2F;
   private static boolean sl = true;
   private static final Animator pY = new Animator(0.0F, 30.0F, 10.0F);
   private static final Animator A = new Animator(0.0F, 34.0F, 11.0F);
   private static final Animator C = new Animator(0.0F, 90.0F, 17.0F);
   private static final Animator s_ = new Animator(0.0F, 55.0F, 13.0F);
   private static final Animator pI = new Animator(0.0F, 70.0F, 13.0F);
   private static final Animator sE = new Animator(0.0F, 160.0F, 11.0F);
   private static final Animator pR = new Animator(0.0F, 120.0F, 15.0F);
   private static final Animator se = new Animator(0.0F, 120.0F, 15.0F);
   private static final Animator z = new Animator(0.0F, 260.0F, 13.0F);
   private static final Animator l = Animator.smooth(0.0F);
   private static final Animator po = new Animator(0.0F, 140.0F, 22.0F);
   private static final Animator pV = new Animator(0.0F, 90.0F, 18.0F);
   private static final Animator pu = new Animator(0.0F, 160.0F, 24.0F);
   private static boolean u = true;
   private static float f;
   private static float pA;
   private static float p_;
   private static float v;
   private static float P;
   private static boolean c;
   private static float sk;
   private static float p8;
   private static float Q;
   private static float pb = 99.0F;
   private static float pU;
   private static float pO = 1.0F;
   private static float F;
   private static final KnifeSupport2.TypeM sD = new KnifeSupport2.TypeM();
   private static final KnifeSupport2.TypeM W = new KnifeSupport2.TypeM();
   private static float L;
   private static int sv = 0;
   private static int pC = 0;
   private static int t = 0;
   private static long pX = 0L;
   private static boolean n;
   private static boolean sM;
   private static boolean p5;
   private static boolean I;
   private static boolean J;
   private static boolean s2;
   private static boolean p1;
   private static boolean D;
   
   private static class_1297 pc;
   
   private static class_243 k;
   private static boolean pT;
   private static boolean s;
   private static boolean p;
   private static boolean pm;
   
   private static class_1297 pw;
   
   private static class_243 pF;
   private static float pj = -99.0F;
   private static float py = 1.0F;
   public static final int G = 240;
   private static final int sV = 9;
   private static final float[] pf = new float[2160];
   private static int X = 0;
   private static int s1 = 0;
   private static int p0 = 0;
   private static int sx = 0;
   private static final Vector3f d = new Vector3f();
   private static final Vector3f p2 = new Vector3f();
   private static final Quaternionf pH = new Quaternionf();
   private static float q;
   private static float Y = 3.0F;
   private static boolean px;
   
   private static KnifeTransform.TypeU pS;
   
   private static String pN;
   private static float pP;
   private static float pK = -1.0F;
   
   private static Path i;
   
   private static String j;
   private static float sS;
   private static int e = 0;
   private static int pE = 0;
   private static volatile int pn;
   private static String pQ = "";





   private KnifeSupport3() {
   }

   public static void attackStarts() {
      s2 = false;
      J = false;
      pc = null;
   }

   public static void onAttack(class_1657 var0, class_1297 var1) {
      if (var0 == class_310.method_1551().field_1724) {
         float var2 = var0.method_7261(0.5F);
         J = true;
         pc = var1;
         k = class_310.method_1551().field_1765 instanceof class_3966 var3 && var3.method_17782() == var1 ? var3.method_17784() : null;
         p1 = var2 > 0.9F;
         D = p1 && var0.method_5624();
         s2 = p1
            && var0.field_6017 > 0.0
            && !var0.method_24828()
            && !var0.method_6101()
            && !var0.method_5799()
            && !var0.method_74025()
            && !var0.method_5765()
            && var1 instanceof class_1309
            && !var0.method_5624();
      }
   }

   public static void attackSwings() {
      n = true;
      sM = false;
   }

   public static void onSwing() {
      sM = true;
   }

   public static boolean inspect() {
      if (g != null && b != null) {
         p5 = true;
         return true;
      } else {
         return false;
      }
   }

   public static boolean slash() {
      if (g != null && b != null) {
         n = true;
         D = false;
         s2 = false;
         J = false;
         pc = null;
         return true;
      } else {
         return false;
      }
   }

   public static boolean holding() {
      return g != null && x != 0L && System.nanoTime() - x < 250000000L;
   }

   public static boolean attacking() {
      return pq != null && pD == KnifeSupport3.TypeS.O;
   }

   
   public static KnifeApi item() {
      return holding() ? g : null;
   }

   public static KnifeCurve frame(KnifeApi var0, class_746 var1, float var2, boolean var3, boolean var4) {
      long var5 = 102845466035798L;
      long var7 = var5 ^ 78584003530469L;
      long var9 = var5 ^ 74847116236174L;
      long var11 = var5 ^ 41041569491675L;
      long var13 = var5 ^ 19179977901435L;
      int var15 = (int)((var5 ^ 19179977901435L) >>> 32);
      int var16 = (int)((var5 ^ 19179977901435L) << 32 >>> 40);
      int var17 = (int)(var13 << 56 >>> 56);
      var13 = var5 ^ 66149449295950L;
      int var18 = (int)((var5 ^ 66149449295950L) >>> 48);
      int var19 = (int)((var5 ^ 66149449295950L) << 16 >>> 32);
      int var20 = (int)(var13 << 48 >>> 48);
      long var21 = var5 ^ 58068435762420L;
      long var23 = var5 ^ 83548464140952L;
      long var25 = var5 ^ 13243572220852L;
      long var27 = var5 ^ 119972061411952L;
      class_310 var29 = class_310.method_1551();
      long var30 = System.nanoTime();
      float var32 = x != 0L && var30 - x <= 500000000L ? Math.min(0.1F, (float)(var30 - x) / 1.0E9F) : 0.0F;
      x = var30;
      if (var29.method_1493()) {
         var32 = 0.0F;
      }

      if (!var0.sheet().equals(ph) || var0.identity() != pr && !var0.identity().equals(pr)) {
         boolean var33 = ph != null;
         g = var0;
         pr = var0.identity();
         ph = var0.sheet();
         b = KnifeTransform.sheet(ph);
         J(b, var7);
         pq = null;
         pT = false;
         p0 = 0;
         if (sl || !var33) {
            pY.snap(0.0F);
            A.snap(0.0F);
            r(var1, var2, 0.0F, var25);
            sZ.set(w);
            R.set(E);
            sp = sR.R[6];
            ps.zero();
            sw.zero();
            U.zero();
            sK.zero();
            h = 0.0F;
            sJ = 0.0F;
            pg = 0.0F;
            p9 = 0.0F;
            pB = 99.0F;
            sl = false;
         }

         KnifeTransform.TypeU var34 = b.clip("draw");
         if (var4 && var34 != null) {
            A(var34, KnifeSupport3.TypeS.r, 0.09F, var9, 0.0F, var0.tempo());
            pB = 99.0F;
         }
      }

      g = var0;
      po.target(var3 ? 1.0F : 0.0F);
      if (i != null && b != null) {
         return d(var11, var0);
      } else if (pN != null && b != null) {
         pq = null;
         p0 = 0;
         KnifeTransform.TypeU var38 = pN.startsWith("gen:") ? pS : b.clip(pN);
         if (var38 != null) {
            var38.at(pP, sg);
         } else {
            b.pose(pN, sg);
         }

         c((char)var18, var19, (char)var20, var0);
         return sg;
      } else {
         v(var1, var2, var15, var32, var16, (byte)var17);
         E(var1, var21);
         float var37 = var32;
         int var39 = 0;

         do {
            float var35 = Math.min(var37, 0.004166667F);
            c(var23, var1, var2, var35);
            var37 -= var35;
         } while (var37 > 1.0E-6F && ++var39 < 48);

         e(var27);
         return sg;
      }
   }

   private static void J(KnifeTransform.TypeH var0, long var1) {
      KnifeUpdater2.ensureSourceClassInitialized();
      long var3 = 29623746676915L;
      if (29623746676915L == 0L) {
         boolean var10000 = false;
      } else {
         int var22 = var3 < 0L ? -1 : 1;
      }

      var0.pose("ready", N);
      int var6 = (var0.has("carry") ? 1 : 0);
      long var7 = 29623746676915L;
      if (29623746676915L == 0L) {
         boolean var23 = false;
      } else {
         int var24 = var7 < 0L ? -1 : 1;
      }

      var0.pose((var6 != 0 ? "carry" : "ready"), pe);
      long var10 = 29623746676914L;
      if (29623746676914L == 0L) {
         boolean var25 = false;
      } else {
         int var26 = var10 < 0L ? -1 : 1;
      }

      var6 = 1357218925;
      long var12 = 29623746676915L;
      if (29623746676915L == 0L) {
         boolean var27 = false;
      } else {
         int var28 = var12 < 0L ? -1 : 1;
      }

      int var9 = 1357243704;
      if (var0.has("run")) {
         var0.pose("run", M);
         long var14 = 29623746676915L;
         if (29623746676915L == 0L) {
            boolean var29 = false;
         } else {
            int var30 = var14 < 0L ? -1 : 1;
         }
      } else {
         long var19 = 29623746676914L;
         if (29623746676914L == 0L) {
            boolean var31 = false;
         } else {
            int var32 = var19 < 0L ? -1 : 1;
         }

         if (var0.has("sprint")) {
            var0.pose("sprint", M);
            long var16 = 29623746676915L;
            if (29623746676915L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var16 < 0L ? -1 : 1;
            }
         } else {
            M.set(pe);
         }
      }

      N.rotation(pM);
      pe.rotation(pJ);
      M.rotation(sf);
      long var20 = 29623746676914L;
      if (29623746676914L == 0L) {
         boolean var35 = false;
      } else {
         int var36 = var20 < 0L ? -1 : 1;
      }

      long var21 = 29623746676914L;
      if (29623746676914L == 0L) {
         boolean var37 = false;
      } else {
         int var38 = var21 < 0L ? -1 : 1;
      }

      if (pM.dot(pJ) < 0.0F) {
         pJ.mul(-1.0F);
      }

      if (pJ.dot(sf) < 0.0F) {
         sf.mul(-1.0F);
      }
   }

   private static void v(class_746 var0, float var1, int var2, float var3, int var4, byte var5) {
      float var6;
      float var7;
      label16: {
         KnifeUpdater2.ensureSourceClassInitialized();
         var6 = var0.method_5705(var1);
         var7 = var0.method_5695(var1);
         if (c) {
            float var8 = Math.abs(var6 - pA);
            if (!(var8 > 90.0F)) {
               break label16;
            }
         }

         pA = var6;
         p_ = var7;
         c = true;
      }

      if (var3 > 0.0F) {
         v = (var6 - pA) / var3;
         P = (var7 - p_) / var3;
      }

      pA = var6;
      p_ = var7;
   }

   private static void E(class_746 var0, long var1) {
      long var3 = 28154060002264L;
      long var5 = 72594910358557L;
      KnifeUpdater2.ensureSourceClassInitialized();
      KnifeTransform.TypeH var7 = b;
      if (var7 != null) {
         class_310 var8 = class_310.method_1551();
         long var9 = 115833372424865L;
         if (115833372424865L == 0L) {
            boolean var10000 = false;
         } else {
            int var26 = var9 < 0L ? -1 : 1;
         }

         if (n) {
            n = false;
            sM = false;
            float var12 = O - pj;
            pj = O;
            long var13 = 115833372424866L;
            if (115833372424866L == 0L) {
               boolean var27 = false;
            } else {
               int var28 = var13 < 0L ? -1 : 1;
            }

            py = var12 < 0.6F ? Math.clamp(0.52F / Math.max(0.05F, var12), 1.0F, 1.7F) : 1.0F;
            long var15 = 115833372424865L;
            if (115833372424865L == 0L) {
               boolean var29 = false;
            } else {
               int var30 = var15 < 0L ? -1 : 1;
            }

            s = pT && s || J;
            long var17 = 115833372424865L;
            if (115833372424865L == 0L) {
               boolean var31 = false;
            } else {
               int var32 = var17 < 0L ? -1 : 1;
            }

            p = pT && p || s2;
            pm = D;
            long var19 = 115833372424865L;
            if (115833372424865L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var19 < 0L ? -1 : 1;
            }

            if (pc != null || !pT) {
               pw = pc;
               pF = k;
            }

            pT = true;
            s2 = false;
            J = false;
            pc = null;
            u(s ? 2.5F : 1.1F);
            pU = 0.0F;
         } else {
            if (sM) {
               sM = false;
               if (var0.field_6266 == class_1268.field_5808 && var8.field_1690.field_1886.method_1434()) {
                  KnifeTransform.TypeU var22 = var7.clip("chop");
                  long var23 = 115833372424865L;
                  if (115833372424865L == 0L) {
                     boolean var35 = false;
                  } else {
                     int var36 = var23 < 0L ? -1 : 1;
                  }

                  label157:
                  if (var22 != null) {
                     if (pq != null && pD == KnifeSupport3.TypeS.O) {
                        float var24;
                        int var16 = (var24 = pZ - var22.E * 0.55F) == 0.0F ? 0 : (var24 < 0.0F ? -1 : 1);
                        long var25 = 115833372424866L;
                        if (115833372424866L == 0L) {
                           boolean var37 = false;
                        } else {
                           int var38 = var25 < 0L ? -1 : 1;
                        }

                        if (var16 <= 0) {
                           break label157;
                        }
                     }

                     A(var22, KnifeSupport3.TypeS.L, 0.09F, var3, 0.0F, g != null ? g.tempo() : 1.0F);
                     u(1.1F);
                  }
               }
            }

            if (p5) {
               p5 = false;
               if (pq == null || pD != KnifeSupport3.TypeS.p && pD != KnifeSupport3.TypeS.r) {
                  if (pq == null || pD != KnifeSupport3.TypeS.O && pD != KnifeSupport3.TypeS.L) {
                     i(var5, false);
                  }
               } else {
                  I = true;
               }
            }
         }
      }
   }

   private static void u(float var0) {
      L = Math.max(L, O + var0);
   }

   private static void c(long var0, class_746 var2, float var3, float var4) {
      long var5 = 91113929928516L;
      byte var7 = 0;
      int var8 = 1390288237;
      int var9 = (int)(var5 << 48 >>> 48);
      long var10 = 30060619343682L;
      long var12 = 87013169076364L;
      long var14 = 73664460845224L;
      long var16 = 104333499249419L;
      var5 = 107340042153496L;
      byte var18 = 0;
      int var19 = 1637879061;
      int var20 = (int)(var5 << 48 >>> 48);
      long var21 = 102694097462514L;
      long var23 = 89619074037218L;
      long var25 = 33880786720043L;
      KnifeUpdater2.ensureSourceClassInitialized();
      KnifeApi var27 = g;
      KnifeTransform.TypeH var28 = b;
      if (var27 != null && var28 != null) {
         O += var4;
         long var29 = 24692706415821L;
         if (24692706415821L == 0L) {
            boolean var10000 = false;
         } else {
            int var52 = var29 < 0L ? -1 : 1;
         }

         r(var2, var3, var4, var23);
         if (pT && (pq == null || pD != KnifeSupport3.TypeS.O || pZ >= pq.y)) {
            n(var2, var14);
         }

         N(var2, var4, var25);
         KnifeTransform.TypeU var32 = pq;
         if (var32 != null) {
            float var33 = T;
            long var34 = 24692706415821L;
            if (24692706415821L == 0L) {
               boolean var53 = false;
            } else {
               int var54 = var34 < 0L ? -1 : 1;
            }

            if (pd > 0.0F) {
               pd -= var4;
               var33 *= 0.12F;
            }

            float var37 = pZ;
            long var38 = 24692706415822L;
            if (24692706415822L == 0L) {
               boolean var55 = false;
            } else {
               int var56 = var38 < 0L ? -1 : 1;
            }

            float var40 = pZ + var4 * var33;
            long var41 = 24692706415821L;
            if (24692706415821L == 0L) {
               boolean var57 = false;
            } else {
               int var58 = var41 < 0L ? -1 : 1;
            }

            pZ = var40;
            M(var12, var32, var37, pZ);
            if (pD == KnifeSupport3.TypeS.O && var32.T >= 0.0F && var37 < var32.T) {
               long var44 = 24692706415821L;
               if (24692706415821L == 0L) {
                  boolean var59 = false;
               } else {
                  int var60 = var44 < 0L ? -1 : 1;
               }

               float var46;
               int var31 = (var46 = pZ - var32.T) == 0.0F ? 0 : (var46 < 0.0F ? -1 : 1);
               long var47 = 24692706415821L;
               if (24692706415821L == 0L) {
                  boolean var61 = false;
               } else {
                  int var62 = var47 < 0L ? -1 : 1;
               }

               if (var31 >= 0) {
                  B((char)var7, var27, var8, (short)var9, var32);
               }
            }

            if (pZ >= var32.E) {
               pq = null;
               pb = 0.0F;
               u(sR, w, E, p6, pL, var10, 0.32F);
            } else {
               var32.at(pZ, a);
            }
         }

         if (pq == null) {
            a.set(sR);
         }

         float var51 = po.update(var4);
         a.R[6] = Math.min(a.R[6], 1.0F - var51);
         L(var16, var4);
         Y(var2, var3, var21, var4);
         c((char)var18, var19, (char)var20, var27);
      }
   }

   private static void r(class_746 var0, float var1, float var2, long var3) {
      KnifeUpdater2.ensureSourceClassInitialized();
      class_310 var5 = class_310.method_1551();
      boolean var6 = var0.method_31549().field_7479;
      boolean var7 = px || var0.method_18798().method_37268() >= 0.0016;
      boolean var8 = O < L;
      p8 = var0.method_5624() && !var6 ? p8 + var2 : 0.0F;
      Q = var7 ? Q + var2 : 0.0F;
      float var9;
      int var10 = (var9 = p8 - 0.35F) == 0.0F ? 0 : (var9 < 0.0F ? -1 : 1);
      long var11 = 89619074037217L;
      if (89619074037217L == 0L) {
         boolean var10000 = false;
      } else {
         int var33 = var11 < 0L ? -1 : 1;
      }

      boolean var14 = var10 > 0 && !var8;
      float var15 = pY.target((!(Q > 0.2F) || var8) && !var14 ? 0.0F : 1.0F).update(var2);
      float var16 = A.target(var14 ? 1.0F : 0.0F).update(var2);
      float var17 = C.target(var0.method_18276() ? 1.0F : 0.0F).update(var2);
      sG.set(w);
      s3.set(E);
      long var18 = 89619074037217L;
      if (89619074037217L == 0L) {
         boolean var34 = false;
      } else {
         int var35 = var18 < 0L ? -1 : 1;
      }

      w.set(N.position(new Vector3f())).lerp(pe.position(new Vector3f()), var15).lerp(M.position(new Vector3f()), var16);
      E.set(pM).slerp(pJ, var15).slerp(sf, var16);
      float var20 = N.R[6] + (pe.R[6] - N.R[6]) * var15;
      float var21 = var20 + (M.R[6] - var20) * var16;
      long var22 = 89619074037218L;
      if (89619074037218L == 0L) {
         boolean var36 = false;
      } else {
         int var37 = var22 < 0L ? -1 : 1;
      }

      if (var17 > 0.001F) {
         w.add(0.0F, -0.03F * var17, 0.02F * var17);
         E.premul(new Quaternionf().rotationX((float)Math.toRadians(4.0F * var17)));
      }

      long var24 = 89619074037217L;
      if (89619074037217L == 0L) {
         boolean var38 = false;
      } else {
         int var39 = var24 < 0L ? -1 : 1;
      }

      sR.R[0] = w.x;
      sR.R[1] = w.y;
      sR.R[2] = w.z;
      KnifeCurve.angles(E, sR.R[3], sR.R[5], sR.R);
      sR.R[6] = var21;
      sR.R[7] = 0.0F;
      float var27;
      var10 = (var27 = var2 - 0.0F) == 0.0F ? 0 : (var27 < 0.0F ? -1 : 1);
      long var28 = 89619074037217L;
      if (89619074037217L == 0L) {
         boolean var40 = false;
      } else {
         int var41 = var28 < 0L ? -1 : 1;
      }

      if (var10 > 0) {
         p6.set(w).sub(sG).div(var2);
         long var30 = 89619074037218L;
         if (89619074037218L == 0L) {
            boolean var42 = false;
         } else {
            int var43 = var30 < 0L ? -1 : 1;
         }

         spin(s3, E, var2, pL);
      } else {
         p6.zero();
         pL.zero();
      }

      if (var5.field_1755 != null) {
         pU = 0.0F;
      }
   }

   private static void N(class_746 var0, float var1, long var2) {
      long var4 = 72594910358557L;
      long var6 = 28154060002264L;
      long var8 = 52581148588539L;
      KnifeUpdater2.ensureSourceClassInitialized();
      KnifeApi var10 = g;
      KnifeTransform.TypeH var11 = b;
      if (var10 != null && var11 != null) {
         class_310 var12 = class_310.method_1551();
         long var13 = 33880786720042L;
         if (33880786720042L == 0L) {
            boolean var10000 = false;
         } else {
            int var45 = var13 < 0L ? -1 : 1;
         }

         long var16 = 33880786720043L;
         if (33880786720043L == 0L) {
            boolean var46 = false;
         } else {
            int var47 = var16 < 0L ? -1 : 1;
         }

         if (var0.field_6235 > sv) {
            u(2.0F);
         }

         sv = var0.field_6235;
         boolean var18 = px || var0.method_18798().method_37268() >= 0.0016 && !var0.method_5765();
         sk = var18 ? sk + var1 : 0.0F;
         long var21 = 33880786720043L;
         if (33880786720043L == 0L) {
            boolean var48 = false;
         } else {
            int var49 = var21 < 0L ? -1 : 1;
         }

         boolean var19;
         boolean var37;
         label230: {
            label229: {
               var19 = !var18 && var0.method_24828();
               if (O >= L && var12.field_1755 == null && !var0.method_6115()) {
                  var37 = var12.field_1690.field_1886.method_1434();
                  long var24 = 33880786720043L;
                  if (33880786720043L == 0L) {
                     boolean var50 = false;
                  } else {
                     int var51 = var24 < 0L ? -1 : 1;
                  }

                  if (!var37 && var12.field_1690.method_31044().method_31034()) {
                     break label229;
                  }
               }

               if (!px) {
                  var37 = false;
                  break label230;
               }
            }

            var37 = true;
         }

         if (pq == null) {
            pb += var1;
         }

         label284: {
            if (I) {
               if (pq == null) {
                  break label284;
               }

               if (pD == KnifeSupport3.TypeS.p) {
                  float var22;
                  int varCmp3 = Float.compare(var22 = pZ - pq.t, 0.0F);
                  long var23 = 33880786720043L;
                  if (33880786720043L == 0L) {
                     boolean var52 = false;
                  } else {
                     int var53 = var23 < 0L ? -1 : 1;
                  }

                  if (varCmp3 >= 0) {
                     break label284;
                  }
               }
            }

            long var40 = 33880786720042L;
            if (33880786720042L == 0L) {
               boolean var54 = false;
            } else {
               int var55 = var40 < 0L ? -1 : 1;
            }

            if (!var37) {
               pU = 0.0F;
               return;
            }

            if (var18 && var10.flows() && sk > 0.12F) {
               pU = 0.0F;
               KnifeSupport2.TypeX var41 = A.value() > 0.5F ? KnifeSupport2.TypeX.q : KnifeSupport2.TypeX.X;
               KnifeCurve var42 = var41 == KnifeSupport2.TypeX.q ? M : pe;
               float var27 = var10.flowPause();
               if (pq == null) {
                  if (pb >= F) {
                     KnifeTransform.TypeU var43 = KnifeSupport2.flow(var11, m, var41, var42, sD, var10.airCuts());
                     A(var43, KnifeSupport3.TypeS.W, 0.2F, var6, 0.0F, var10.tempo() * var10.flowTempo());
                     F = 0.12F + var27 * (0.6F + 0.8F * m.nextFloat());
                  }
               } else if ((pD == KnifeSupport3.TypeS.W || pD == KnifeSupport3.TypeS.x) && var27 < 0.05F && pZ >= pq.t) {
                  KnifeTransform.TypeU var44 = KnifeSupport2.flow(var11, m, var41, var42, sD, var10.airCuts());
                  A(var44, KnifeSupport3.TypeS.W, 0.17F, var6, var44.J, var10.tempo() * var10.flowTempo());
               }

               return;
            }

            pU = var19 && pq == null ? pU + var1 : 0.0F;
            float var25 = var10.idleEvery();
            long var26 = 33880786720042L;
            if (33880786720042L == 0L) {
               boolean var56 = false;
            } else {
               int var57 = var26 < 0L ? -1 : 1;
            }

            if (var25 > 0.0F) {
               long var28 = 33880786720042L;
               if (33880786720042L == 0L) {
                  boolean var58 = false;
               } else {
                  int var59 = var28 < 0L ? -1 : 1;
               }

               if (pU > var25 * pO) {
                  pU = 0.0F;
                  pO = 0.8F + m.nextFloat() * 0.5F;
                  List var31 = n(var8, var11, "idle");
                  boolean var32 = var11.q.containsKey("moves.tempo");
                  boolean var33 = var31.isEmpty();
                  long var34 = 33880786720043L;
                  if (33880786720043L == 0L) {
                     boolean var60 = false;
                  } else {
                     int var61 = var34 < 0L ? -1 : 1;
                  }

                  KnifeTransform.TypeU var36 = !var33 && (!var32 || m.nextFloat() < 0.25F)
                     ? (KnifeTransform.TypeU)var31.get(m.nextInt(var31.size()))
                     : KnifeSupport2.flow(var11, m, KnifeSupport2.TypeX.i, N, sD, var10.airCuts());
                  A(var36, KnifeSupport3.TypeS.x, 0.2F, var6, 0.0F, var10.tempo());
               }
            }

            return;
         }

         i(var4, pq != null);
      }
   }

   private static void i(long var0, boolean var2) {
      long var3 = 52581148588539L;
      long var5 = 28154060002264L;
      KnifeUpdater2.ensureSourceClassInitialized();
      KnifeTransform.TypeH var7 = b;
      KnifeApi var8 = g;
      if (var7 != null && var8 != null) {
         I = false;
         List var9 = n(var3, var7, "inspect");
         boolean var10 = var7.q.containsKey("moves.tempo");
         boolean var11 = var9.isEmpty();
         long var12 = 72594910358557L;
         if (72594910358557L == 0L) {
            boolean var10000 = false;
         } else {
            int var23 = var12 < 0L ? -1 : 1;
         }

         if (var11) {
            long var15 = 72594910358557L;
            if (72594910358557L == 0L) {
               boolean var24 = false;
            } else {
               int var25 = var15 < 0L ? -1 : 1;
            }

            if (!var10) {
               return;
            }
         }

         var11 = var9.isEmpty();
         long var22 = 72594910358556L;
         if (72594910358556L == 0L) {
            boolean var26 = false;
         } else {
            int var27 = var22 < 0L ? -1 : 1;
         }

         KnifeTransform.TypeU var18;
         if (!var11 && (!var10 || t % 3 == 0)) {
            var18 = (KnifeTransform.TypeU)var9.get(t / (var10 ? 3 : 1) % var9.size());
            long var19 = 72594910358557L;
            if (72594910358557L == 0L) {
               boolean var28 = false;
            } else {
               int var29 = var19 < 0L ? -1 : 1;
            }
         } else {
            var18 = KnifeSupport2.flow(var7, m, KnifeSupport2.TypeX.a, N, sD, true);
         }

         t++;
         A(var18, KnifeSupport3.TypeS.p, var2 ? 0.17F : 0.2F, var5, var2 ? var18.J : 0.0F, var8.tempo());
         pU = 0.0F;
      }
   }

   private static void n(class_746 var0, long var1) {
      long var3 = 28154060002264L;
      KnifeUpdater2.ensureSourceClassInitialized();
      KnifeTransform.TypeH var5 = b;
      KnifeApi var6 = g;
      pT = false;
      if (var5 != null && var6 != null) {
         long var7 = System.nanoTime();
         long var9 = 73664460845223L;
         if (73664460845223L == 0L) {
            boolean var10000 = false;
         } else {
            int var29 = var9 < 0L ? -1 : 1;
         }

         boolean var12 = pX != 0L && (float)(var7 - pX) / 1.0E9F < 1.15F;
         pX = var7;
         KnifeTransform.TypeU var13 = null;
         float var14;
         int var15 = (var14 = var5.number("cuts", 0.0F) - 0.0F) == 0.0F ? 0 : (var14 < 0.0F ? -1 : 1);
         long var16 = 73664460845223L;
         if (73664460845223L == 0L) {
            boolean var30 = false;
         } else {
            int var31 = var16 < 0L ? -1 : 1;
         }

         if (var15 > 0) {
            KnifeSupport2.TypeP var26 = p ? KnifeSupport2.TypeP.H : (pm ? KnifeSupport2.TypeP.T : KnifeSupport2.TypeP.v);
            KnifeCurve var20 = new KnifeCurve();
            var20.R[0] = sZ.x;
            var20.R[1] = sZ.y;
            var20.R[2] = sZ.z;
            KnifeCurve.angles(R, 0.0F, 0.0F, var20.R);
            var13 = KnifeSupport2.attack(var5, m, var26, var20, N, W);
         } else {
            if (p) {
               var13 = var5.clip("crit");
            } else if (pm) {
               var13 = var5.clip("thrust");
            }

            long var19 = 73664460845224L;
            if (73664460845224L == 0L) {
               boolean var32 = false;
            } else {
               int var33 = var19 < 0L ? -1 : 1;
            }

            if (var13 == null) {
               int var21;
               for (var21 = 0; var5.clip("slash" + (var21 + 1)) != null; var21++) {
                  long var22 = 73664460845223L;
                  if (73664460845223L == 0L) {
                     boolean var34 = false;
                  } else {
                     int var35 = var22 < 0L ? -1 : 1;
                  }
               }

               long var28 = 73664460845223L;
               if (73664460845223L == 0L) {
                  boolean var36 = false;
               } else {
                  int var37 = var28 < 0L ? -1 : 1;
               }

               long var24 = 73664460845223L;
               if (73664460845223L == 0L) {
                  boolean var38 = false;
               } else {
                  int var39 = var24 < 0L ? -1 : 1;
               }

               pC = var12 && var21 > 0 ? (pC + 1) % var21 : 0;
               var13 = var5.clip("slash" + (pC + 1));
            }
         }

         if (var13 != null) {
            A(var13, KnifeSupport3.TypeS.O, 0.09F, var3, 0.0F, var6.tempo() * py);
            long var27 = 73664460845223L;
            if (73664460845223L == 0L) {
               boolean var40 = false;
            } else {
               int var41 = var27 < 0L ? -1 : 1;
            }

            pa = s;
            pW = pw;
            pt = pF;
            pi = p ? 1.35F : (s ? 1.1F : 0.9F);
            pk = p;
         }

         pm = false;
         p = false;
         s = false;
         pw = null;
         pF = null;
         pU = 0.0F;
      }
   }

   private static List n(long var0, KnifeTransform.TypeH var2, String var3) {
      KnifeUpdater2.ensureSourceClassInitialized();
      ArrayList var4 = new ArrayList();
      KnifeTransform.TypeU var5 = var2.clip(var3);
      if (var5 != null) {
         var4.add(var5);
      }

      for (int var6 = 2; var2.clip(var3 + var6) != null; var6++) {
         var4.add(var2.clip(var3 + var6));
      }

      long var9 = 52581148588539L;
      if (52581148588539L == 0L) {
         boolean var10000 = false;
      } else {
         int var10 = var9 < 0L ? -1 : 1;
      }

      return var4;
   }

   private static void A(KnifeTransform.TypeU var0, KnifeSupport3.TypeS var1, float var2, long var3, float var5, float var6) {
      long var7 = 30060619343682L;
      KnifeUpdater2.ensureSourceClassInitialized();
      s1 = ++X;
      pi = var1 == KnifeSupport3.TypeS.O ? 1.0F : 0.7F * var0.g;
      pq = var0;
      pD = var1;
      pZ = var5;
      T = var6;

      for (sC = 0; sC < var0.U.size(); sC++) {
         long var9 = 28154060002263L;
         if (28154060002263L == 0L) {
            boolean var10000 = false;
         } else {
            int var22 = var9 < 0L ? -1 : 1;
         }

         float var12;
         int var13 = (var12 = var0.U.get(sC).time() - (var5 - 1.0E-4F)) == 0.0F ? 0 : (var12 < 0.0F ? -1 : 1);
         long var14 = 28154060002263L;
         if (28154060002263L == 0L) {
            boolean var23 = false;
         } else {
            int var24 = var14 < 0L ? -1 : 1;
         }

         if (var13 >= 0) {
            break;
         }
      }

      pa = false;
      V = false;
      long var18 = 28154060002264L;
      if (28154060002264L == 0L) {
         boolean var25 = false;
      } else {
         int var26 = var18 < 0L ? -1 : 1;
      }

      pk = false;
      pd = 0.0F;
      pb = 0.0F;
      var0.at(var5, a);
      var0.at(var5 + 0.004166667F, p3);
      Vector3f var19 = a.position(new Vector3f());
      Vector3f var20 = p3.position(new Vector3f());
      Quaternionf var21 = a.rotation(new Quaternionf());
      Quaternionf var15 = p3.rotation(new Quaternionf());
      Vector3f var16 = var20.sub(var19).div(0.004166667F / var6);
      Vector3f var17 = spin(var21, var15, 0.004166667F / var6, new Vector3f());
      u(a, var19, var21, var16, var17, var7, var2);
   }

   private static void u(KnifeCurve var0, Vector3f var1, Quaternionf var2, Vector3f var3, Vector3f var4, long var5, float var7) {
      KnifeUpdater2.ensureSourceClassInitialized();
      U.set(sZ).sub(var1);
      sK.set(ps).sub(var3);
      Quaternionf var8 = new Quaternionf(R).mul(new Quaternionf(var2).conjugate());
      if (var8.w < 0.0F) {
         var8.set(-var8.x, -var8.y, -var8.z, -var8.w);
      }

      float var9 = (float)Math.acos(Math.clamp(var8.w, -1.0F, 1.0F));
      float var10 = (float)Math.sin(var9);
      float var11;
      int var12 = (var11 = var10 - 1.0E-5F) == 0.0F ? 0 : (var11 < 0.0F ? -1 : 1);
      long var13 = 30060619343682L;
      if (30060619343682L == 0L) {
         boolean var10000 = false;
      } else {
         int var18 = var13 < 0L ? -1 : 1;
      }

      if (var12 > 0) {
         long var16 = 30060619343682L;
         if (30060619343682L == 0L) {
            boolean var19 = false;
         } else {
            int var20 = var16 < 0L ? -1 : 1;
         }

         pz.set(var8.x / var10, var8.y / var10, var8.z / var10);
         p9 = 2.0F * var9;
      } else {
         p9 = 0.0F;
      }

      pg = new Vector3f(sw).sub(var4).dot(pz);
      sJ = sp - var0.R[6];
      h = pp - var0.R[7];
      pB = 0.0F;
      sU = var7;
   }

   private static void L(long var0, float var2) {
      S.set(sZ);
      KnifeUpdater2.ensureSourceClassInitialized();
      pl.set(R);
      pB += var2;
      boolean var3 = pB < sU;
      sZ.set(a.R[0], a.R[1], a.R[2]);
      long var4 = 104333499249419L;
      if (104333499249419L == 0L) {
         boolean var10000 = false;
      } else {
         int var16 = var4 < 0L ? -1 : 1;
      }

      a.rotation(R);
      sp = a.R[6];
      long var7 = 104333499249419L;
      if (104333499249419L == 0L) {
         boolean var17 = false;
      } else {
         int var18 = var7 < 0L ? -1 : 1;
      }

      pp = a.R[7];
      if (var3) {
         sZ.add(decay(U.x, sK.x, sU, pB), decay(U.y, sK.y, sU, pB), decay(U.z, sK.z, sU, pB));
         float var10 = decay(p9, pg, sU, pB);
         long var11 = 104333499249418L;
         if (104333499249418L == 0L) {
            boolean var19 = false;
         } else {
            int var20 = var11 < 0L ? -1 : 1;
         }

         float var13 = Math.abs(var10);
         long var14 = 104333499249419L;
         if (104333499249419L == 0L) {
            boolean var21 = false;
         } else {
            int var22 = var14 < 0L ? -1 : 1;
         }

         if (var13 > 1.0E-6F) {
            R.premul(new Quaternionf().rotationAxis(var10, pz));
         }

         sp = sp + decay(sJ, 0.0F, sU, pB);
         pp = pp + decay(h, 0.0F, sU, pB);
      }

      sp = Math.clamp(sp, 0.0F, 1.0F);
      if (var2 > 0.0F) {
         ps.set(sZ).sub(S).div(var2);
         spin(pl, R, var2, sw);
      }
   }

   private static void Y(class_746 var0, float var1, long var2, float var4) {
      KnifeUpdater2.ensureSourceClassInitialized();
      boolean var5 = var0.method_31549().field_7479;
      float var15 = s_.target(var0.method_5624() && !var5 ? 1.0F : 0.0F).update(var4);
      class_11902 var16 = var0.method_74192();
      float var17 = var16.method_74205(var1);
      float var18 = var16.method_74204(var1);
      float var6 = pV.target(!var0.method_24828() && !var5 ? 0.0F : Math.clamp(var18 / 0.1F, 0.0F, 1.0F)).update(var4);
      float var19 = var17 * (float) Math.PI;
      float var20 = (float)Math.sin(var19);
      float var21 = Math.abs((float)Math.cos(var19));
      float var7 = 1.0F - 0.6F * pu.target(pq != null && pD == KnifeSupport3.TypeS.O ? 1.0F : 0.0F).update(var4);
      float var22 = (1.0F + var15 * 0.8F) * var7;
      float var8 = 0.008F * var6 * var20 * var22;
      float var9 = (-0.011F * var21 + 0.005F) * var6 * var22;
      float var10 = -0.012F * var6 * (var21 - 0.5F) * var22;
      float var11 = 2.8F * var6 * var20 * var22;
      float var12 = 2.6F * var6 * (Math.abs((float)Math.cos(var19 - 0.35F)) - 0.6F) * var22;
      class_243 var13 = var0.method_18798();
      double var23;
      int var14 = (var23 = var13.method_37268() - 4.0E-4) == 0.0 ? 0 : (var23 < 0.0 ? -1 : 1);
      long var25 = 102694097462513L;
      if (102694097462513L == 0L) {
         boolean var10000 = false;
      } else {
         int var59 = var25 < 0L ? -1 : 1;
      }

      boolean var46;
      label137: {
         if (var14 < 0) {
            boolean var28 = var0.method_24828();
            long var29 = 102694097462514L;
            if (102694097462514L == 0L) {
               boolean var60 = false;
            } else {
               int var61 = var29 < 0L ? -1 : 1;
            }

            if (var28 && !px) {
               var46 = true;
               break label137;
            }
         }

         var46 = false;
      }

      float var48 = l.target(var46 ? 1.0F : 0.0F).update(var4 * 0.6F);
      long var49 = 102694097462514L;
      if (102694097462514L == 0L) {
         boolean var62 = false;
      } else {
         int var63 = var49 < 0L ? -1 : 1;
      }

      var19 = var9 + 0.0035F * var48 * (float)Math.sin(O * 1.7F);
      var20 = var12 + 0.8F * var48 * (float)Math.sin(O * 1.7F - 0.8F);
      var21 = var11 + 0.45F * var48 * (float)Math.sin(O * 0.85F + 0.4F);
      long var53 = 102694097462514L;
      if (102694097462514L == 0L) {
         boolean var64 = false;
      } else {
         int var65 = var53 < 0L ? -1 : 1;
      }

      float var54 = !var5 && !var0.method_24828() ? (float)var13.field_1351 : 0.0F;
      float var26 = pI.target(Math.clamp(var54, -1.0F, 0.6F)).update(var4);
      boolean var27 = var0.method_24828();
      long var55 = 102694097462514L;
      if (102694097462514L == 0L) {
         boolean var66 = false;
      } else {
         int var67 = var55 < 0L ? -1 : 1;
      }

      long var30 = 102694097462513L;
      if (102694097462513L == 0L) {
         boolean var68 = false;
      } else {
         int var69 = var30 < 0L ? -1 : 1;
      }

      if (var27 && !u && f < -0.2F) {
         sE.impulse(-Math.min(1.8F, -f * 1.6F));
      }

      u = var27;
      f = (float)var13.field_1351;
      float var32 = sE.target(0.0F).update(var4);
      var9 = var19 + (-0.035F * var26 + 0.03F * var32) * var7;
      var12 = var20 + (5.0F * var26 - 4.0F * var32) * var7;
      var11 = var21 + pR.target(Math.clamp(v * 0.012F, -8.0F, 8.0F)).update(var4);
      var12 += se.target(Math.clamp(-P * 0.008F, -6.0F, 6.0F)).update(var4);
      float var33 = z.update(var4);
      float var34 = var12 + var33 * 6.0F;
      float var35 = var10 + var33 * 0.012F;
      if (var6 > 0.5F && pq == null) {
         long var36 = 102694097462514L;
         if (102694097462514L == 0L) {
            boolean var70 = false;
         } else {
            int var71 = var36 < 0L ? -1 : 1;
         }

         float var38 = O - q;
         long var39 = 102694097462514L;
         if (102694097462514L == 0L) {
            boolean var72 = false;
         } else {
            int var73 = var39 < 0L ? -1 : 1;
         }

         if (var38 > Y) {
            q = O;
            Y = 2.4F + m.nextFloat() * 2.8F;
            KnifeSupport6.glintQuietly();
         }
      }

      KnifeTransform.TypeH var56 = b;
      float var37 = var56 == null ? 0.0F : var56.k * var56.M;
      Vector3f var57 = R.transform(new Vector3f(0.0F, -var37, 0.0F)).add(sZ);
      Quaternionf var58 = new Quaternionf().rotationZ((float)Math.toRadians(var11)).rotateX((float)Math.toRadians(-var34));
      Vector3f var40 = var58.transform(new Vector3f(sZ).sub(var57)).add(var57).add(var8, var9, var35);
      Quaternionf var41 = new Quaternionf(var58).mul(R);
      sg.R[0] = var40.x;
      sg.R[1] = var40.y;
      sg.R[2] = var40.z;
      KnifeCurve.angles(var41, sg.R[3], sg.R[5], sg.R);
      sg.R[6] = sp;
      sg.R[7] = pp;
   }

   private static void B(char var0, KnifeApi var1, int var2, short var3, KnifeTransform.TypeU var4) {
      KnifeUpdater2.ensureSourceClassInitialized();
      if (pa) {
         boolean var5;
         label30: {
            pd = 0.06F;
            z.impulse(-2.2F);
            if (var1.hitSounds()) {
               KnifeFactory.play(var1, "hit", pk ? 1.0F : 0.85F, pk ? 0.94F : 1.0F, true);
               var5 = pk;
               if (var0 < 0) {
                  break label30;
               }

               if (pk) {
                  KnifeFactory.play(var1, "crit", 0.8F, 1.0F, true);
               }
            }

            var5 = V;
         }

         if (!var5) {
            V = true;
            KnifeSupport6.hit(var1, pW, pt, pk);
         }
      }
   }

   private static void M(long var0, KnifeTransform.TypeU var2, float var3, float var4) {
      KnifeUpdater2.ensureSourceClassInitialized();
      List var5 = var2.U;

      while (sC < var5.size() && ((KnifeTransform.Data)var5.get(sC)).time() <= var4) {
         KnifeTransform.Data var6 = (KnifeTransform.Data)var5.get(sC++);
         if (!(var6.time() < var3 - 0.05F)) {
            if (g == null) {
               long var11 = 87013169076364L;
               if (87013169076364L == 0L) {
                  boolean var13 = false;
               } else {
                  int var14 = var11 < 0L ? -1 : 1;
               }
            } else if (!var6.kind().equals("sound")) {
               KnifeSupport6.event(g, var6.name());
            } else {
               KnifeFactory.play(g, var6.name(), var6.volume(), var6.pitch(), pD == KnifeSupport3.TypeS.O || pD == KnifeSupport3.TypeS.L);
               long var7 = 87013169076364L;
               if (87013169076364L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var12 = var7 < 0L ? -1 : 1;
               }
            }
         }
      }

      long var10 = 87013169076363L;
      if (87013169076363L == 0L) {
         boolean var15 = false;
      } else {
         int var16 = var10 < 0L ? -1 : 1;
      }
   }

   static Vector3f spin(Quaternionf var0, Quaternionf var1, float var2, Vector3f var3) {
      Quaternionf var4 = new Quaternionf(var1).mul(new Quaternionf(var0).conjugate());
      if (var4.w < 0.0F) {
         var4.set(-var4.x, -var4.y, -var4.z, -var4.w);
      }

      float var5 = (float)Math.acos(Math.clamp(var4.w, -1.0F, 1.0F));
      float var6 = (float)Math.sin(var5);
      return !(var6 < 1.0E-6F) && !(var2 <= 0.0F) ? var3.set(var4.x, var4.y, var4.z).mul(2.0F * var5 / (var6 * var2)) : var3.zero();
   }

   private static void c(char var0, int var1, char var2, KnifeApi var3) {
      int var6;
      int var12;
      label57: {
         long var4 = 107340042153496L;
         KnifeUpdater2.ensureSourceClassInitialized();
         sg.rotation(pH);
         Vector3f var8 = sg.position(new Vector3f());
         float var9 = b == null ? 1.0F : b.M;
         var3.blade(d, p2);
         pH.transform(d.mul(var9)).add(var8);
         pH.transform(p2.mul(var9)).add(var8);
         var6 = sx * 9;
         pf[var6] = O;
         pf[var6 + 1] = d.x;
         pf[var6 + 2] = d.y;
         pf[var6 + 3] = d.z;
         pf[var6 + 4] = p2.x;
         pf[var6 + 5] = p2.y;
         pf[var6 + 6] = p2.z;
         KnifeTransform.TypeU var10 = pq;
         if (var10 != null && var10.R >= 0.0F && pZ >= var10.R) {
            float var11;
            var12 = (var11 = pZ - var10.v) == 0.0F ? 0 : (var11 < 0.0F ? -1 : 1);
            if (var12 <= 0 && var3.slashFx()) {
               var12 = 1;
               break label57;
            }
         }

         var12 = 0;
      }

      float var14 = var3.ambientFx() ? (var12 != 0 ? pV.value() * (0.22F + 0.2F * s_.value()) : 0.0F) : 0.0F;
      int var15 = 1637879061;
      int var17 = var6 + 7;
      var15 = 1637879061;
      pf[var17] = var12 != 0 ? s1 : (var14 > 0.02F ? -1.0F : 0.0F);
      pf[var6 + 8] = var12 != 0 ? pi : var14;
      sx = (sx + 1) % 240;
      p0 = Math.min(240, p0 + 1);
   }

   public static float clock() {
      return O;
   }

   public static int pathCount() {
      return p0;
   }

   public static float moment(int var0, Vector3f var1, Vector3f var2) {
      int var3 = Math.floorMod(sx - 1 - var0, 240) * 9;
      var1.set(pf[var3 + 1], pf[var3 + 2], pf[var3 + 3]);
      var2.set(pf[var3 + 4], pf[var3 + 5], pf[var3 + 6]);
      return pf[var3];
   }

   public static int arcOf(int var0) {
      return (int)pf[Math.floorMod(sx - 1 - var0, 240) * 9 + 7];
   }

   public static float arcStrengthOf(int var0) {
      return pf[Math.floorMod(sx - 1 - var0, 240) * 9 + 8];
   }

   public static boolean actionCrit() {
      return pk;
   }

   
   public static KnifeTransform.TypeU action() {
      return pq;
   }

   public static float actionTime() {
      return pZ;
   }

   public static boolean actionStruck() {
      return pa;
   }

   public static void reset() {
      pr = null;
      ph = null;
      b = null;
      g = null;
      pq = null;
      pT = false;
      x = 0L;
      sl = true;
      p0 = 0;
      c = false;
   }

   static float decay(float var0, float var1, float var2, float var3) {
      if (var0 < 0.0F) {
         return -decay(-var0, -var1, var2, var3);
      } else if (!(var0 < 1.0E-6F) && !(var3 >= var2)) {
         float var4 = Math.min(var1, 0.0F);
         float var5 = var4 < 0.0F ? Math.min(var2, -5.0F * var0 / var4) : var2;
         if (var3 >= var5) {
            return 0.0F;
         } else {
            float var6 = var5 * var5;
            float var7 = var6 * var5;
            float var8 = var7 * var5;
            float var9 = var8 * var5;
            float var10 = (-8.0F * var4 * var5 - 20.0F * var0) / var6;
            float var11 = -(var10 * var6 + 6.0F * var4 * var5 + 12.0F * var0) / (2.0F * var9);
            float var12 = (3.0F * var10 * var6 + 16.0F * var4 * var5 + 30.0F * var0) / (2.0F * var8);
            float var13 = -(3.0F * var10 * var6 + 12.0F * var4 * var5 + 20.0F * var0) / (2.0F * var7);
            return ((((var11 * var3 + var12) * var3 + var13) * var3 + var10 * 0.5F) * var3 + var4) * var3 + var0;
         }
      } else {
         return 0.0F;
      }
   }

   public static void devPlay(String var0) {
      long var1 = 8760747798507L;
      long var3 = var1 ^ 116717387616915L;
      long var5 = var1 ^ 33453063830579L;
      KnifeTransform.TypeU var7 = X(var3, var0);
      if (var7 != null) {
         boolean var8 = var0.startsWith("slash") || var0.equals("crit") || var0.equals("thrust") || var0.startsWith("gen:attack");
         A(
            var7,
            var8 ? KnifeSupport3.TypeS.O : (var0.equals("chop") ? KnifeSupport3.TypeS.L : KnifeSupport3.TypeS.G),
            0.2F,
            var5,
            0.0F,
            g != null ? g.tempo() : 1.0F
         );
      }
   }

   
   private static KnifeTransform.TypeU X(long var0, String var2) {
      // $VF: Couldn't be decompiled
      // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
      // java.lang.StackOverflowError
      //   at org.jetbrains.java.decompiler.util.TextBuffer.addAllClassTokens(TextBuffer.java:148)
      //   at org.jetbrains.java.decompiler.util.TextBuffer.appendAllClasses(TextBuffer.java:138)
      //   at org.jetbrains.java.decompiler.util.TextBuffer.appendTypeName(TextBuffer.java:118)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.NewExprent.toJava(NewExprent.java:454)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.InvocationExprent.appendParamList(InvocationExprent.java:1153)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.InvocationExprent.toJava(InvocationExprent.java:904)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.AssignmentExprent.toJava(AssignmentExprent.java:154)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:200)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.IfStatement.toJava(IfStatement.java:238)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.DoStatement.toJava(DoStatement.java:142)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.jmpWrapper(ExprProcessor.java:829)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.SequenceStatement.toJava(SequenceStatement.java:107)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.SwitchExprent.toJava(SwitchExprent.java:167)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.getCastedExprent(ExprProcessor.java:1014)
      //   at org.jetbrains.java.decompiler.modules.decompiler.exps.ExitExprent.toJava(ExitExprent.java:86)
      //   at org.jetbrains.java.decompiler.modules.decompiler.ExprProcessor.listToJava(ExprProcessor.java:891)
      //   at org.jetbrains.java.decompiler.modules.decompiler.stats.BasicBlockStatement.toJava(BasicBlockStatement.java:91)
      //
      // Bytecode:
      // 000: invokestatic dev/purxiy/modules/knife/KnifeUpdater2.ensureSourceClassInitialized ()V
      // 003: getstatic dev/purxiy/modules/knife/KnifeSupport3.b Ldev/purxiy/modules/knife/KnifeTransform$TypeH;
      // 006: astore 3
      // 007: aload 3
      // 008: ifnonnull 00d
      // 00b: aconst_null
      // 00c: areturn
      // 00d: ldc2_w 120742925516152
      // 010: dup2
      // 011: lstore 4
      // 013: lconst_0
      // 014: lcmp
      // 015: ifne 01c
      // 018: bipush 0
      // 019: goto 028
      // 01c: lload 4
      // 01e: lconst_0
      // 01f: lcmp
      // 020: ifge 027
      // 023: bipush -1
      // 024: goto 028
      // 027: bipush 1
      // 028: istore 6
      // 02a: aload 2
      // 02b: ldc_w "gen:"
      // 02e: invokevirtual java/lang/String.startsWith (Ljava/lang/String;)Z
      // 031: ifne 03a
      // 034: aload 3
      // 035: aload 2
      // 036: invokevirtual dev/purxiy/modules/knife/KnifeTransform$TypeH.clip (Ljava/lang/String;)Ldev/purxiy/modules/knife/KnifeTransform$TypeU;
      // 039: areturn
      // 03a: aload 2
      // 03b: ldc_w ":"
      // 03e: invokevirtual java/lang/String.split (Ljava/lang/String;)[Ljava/lang/String;
      // 041: astore 7
      // 043: aload 7
      // 045: arraylength
      // 046: bipush 2
      // 047: if_icmple 05b
      // 04a: new java/util/Random
      // 04d: dup
      // 04e: aload 7
      // 050: bipush 2
      // 051: aaload
      // 052: invokestatic java/lang/Long.parseLong (Ljava/lang/String;)J
      // 055: invokespecial java/util/Random.<init> (J)V
      // 058: goto 05e
      // 05b: getstatic dev/purxiy/modules/knife/KnifeSupport3.m Ljava/util/Random;
      // 05e: astore 8
      // 060: new dev/purxiy/modules/knife/KnifeSupport2$TypeM
      // 063: dup
      // 064: invokespecial dev/purxiy/modules/knife/KnifeSupport2$TypeM.<init> ()V
      // 067: astore 9
      // 069: aload 7
      // 06b: bipush 1
      // 06c: aaload
      // 06d: astore 10
      // 06f: bipush -1
      // 070: istore 11
      // 072: aload 10
      // 074: invokevirtual java/lang/String.hashCode ()I
      // 077: lookupswitch 280 5 -1407259064 49 113291 95 3062234 141 3146030 187 3227604 233
      // 0a8: aload 10
      // 0aa: ldc_w "attack"
      // 0ad: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0b0: ifeq 18f
      // 0b3: ldc2_w 120742925516151
      // 0b6: dup2
      // 0b7: lstore 12
      // 0b9: lconst_0
      // 0ba: lcmp
      // 0bb: ifne 0c2
      // 0be: bipush 0
      // 0bf: goto 0ce
      // 0c2: lload 12
      // 0c4: lconst_0
      // 0c5: lcmp
      // 0c6: ifge 0cd
      // 0c9: bipush -1
      // 0ca: goto 0ce
      // 0cd: bipush 1
      // 0ce: istore 14
      // 0d0: bipush 0
      // 0d1: istore 11
      // 0d3: goto 18f
      // 0d6: aload 10
      // 0d8: ldc_w "run"
      // 0db: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 0de: ifeq 18f
      // 0e1: ldc2_w 120742925516152
      // 0e4: dup2
      // 0e5: lstore 12
      // 0e7: lconst_0
      // 0e8: lcmp
      // 0e9: ifne 0f0
      // 0ec: bipush 0
      // 0ed: goto 0fc
      // 0f0: lload 12
      // 0f2: lconst_0
      // 0f3: lcmp
      // 0f4: ifge 0fb
      // 0f7: bipush -1
      // 0f8: goto 0fc
      // 0fb: bipush 1
      // 0fc: istore 14
      // 0fe: bipush 3
      // 0ff: istore 11
      // 101: goto 18f
      // 104: aload 10
      // 106: ldc_w "crit"
      // 109: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 10c: ifeq 18f
      // 10f: ldc2_w 120742925516151
      // 112: dup2
      // 113: lstore 12
      // 115: lconst_0
      // 116: lcmp
      // 117: ifne 11e
      // 11a: bipush 0
      // 11b: goto 12a
      // 11e: lload 12
      // 120: lconst_0
      // 121: lcmp
      // 122: ifge 129
      // 125: bipush -1
      // 126: goto 12a
      // 129: bipush 1
      // 12a: istore 14
      // 12c: bipush 1
      // 12d: istore 11
      // 12f: goto 18f
      // 132: aload 10
      // 134: ldc_w "flow"
      // 137: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 13a: ifeq 18f
      // 13d: ldc2_w 120742925516152
      // 140: dup2
      // 141: lstore 12
      // 143: lconst_0
      // 144: lcmp
      // 145: ifne 14c
      // 148: bipush 0
      // 149: goto 158
      // 14c: lload 12
      // 14e: lconst_0
      // 14f: lcmp
      // 150: ifge 157
      // 153: bipush -1
      // 154: goto 158
      // 157: bipush 1
      // 158: istore 14
      // 15a: bipush 2
      // 15b: istore 11
      // 15d: goto 18f
      // 160: aload 10
      // 162: ldc_w "idle"
      // 165: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 168: istore 12
      // 16a: ldc2_w 120742925516151
      // 16d: dup2
      // 16e: lstore 13
      // 170: lconst_0
      // 171: lcmp
      // 172: ifne 179
      // 175: bipush 0
      // 176: goto 185
      // 179: lload 13
      // 17b: lconst_0
      // 17c: lcmp
      // 17d: ifge 184
      // 180: bipush -1
      // 181: goto 185
      // 184: bipush 1
      // 185: istore 6
      // 187: iload 12
      // 189: ifeq 18f
      // 18c: bipush 4
      // 18d: istore 11
      // 18f: iload 11
      // 191: tableswitch 129 0 4 35 55 75 93 111
      // 1b4: aload 3
      // 1b5: aload 8
      // 1b7: getstatic dev/purxiy/modules/knife/KnifeSupport2$TypeP.v Ldev/purxiy/modules/knife/KnifeSupport2$TypeP;
      // 1ba: getstatic dev/purxiy/modules/knife/KnifeSupport3.N Ldev/purxiy/modules/knife/KnifeCurve;
      // 1bd: getstatic dev/purxiy/modules/knife/KnifeSupport3.N Ldev/purxiy/modules/knife/KnifeCurve;
      // 1c0: aload 9
      // 1c2: invokestatic dev/purxiy/modules/knife/KnifeSupport2.attack (Ldev/purxiy/modules/knife/KnifeTransform$TypeH;Ljava/util/Random;Ldev/purxiy/modules/knife/KnifeSupport2$TypeP;Ldev/purxiy/modules/knife/KnifeCurve;Ldev/purxiy/modules/knife/KnifeCurve;Ldev/purxiy/modules/knife/KnifeSupport2$TypeM;)Ldev/purxiy/modules/knife/KnifeTransform$TypeU;
      // 1c5: goto 28b
      // 1c8: aload 3
      // 1c9: aload 8
      // 1cb: getstatic dev/purxiy/modules/knife/KnifeSupport2$TypeP.H Ldev/purxiy/modules/knife/KnifeSupport2$TypeP;
      // 1ce: getstatic dev/purxiy/modules/knife/KnifeSupport3.N Ldev/purxiy/modules/knife/KnifeCurve;
      // 1d1: getstatic dev/purxiy/modules/knife/KnifeSupport3.N Ldev/purxiy/modules/knife/KnifeCurve;
      // 1d4: aload 9
      // 1d6: invokestatic dev/purxiy/modules/knife/KnifeSupport2.attack (Ldev/purxiy/modules/knife/KnifeTransform$TypeH;Ljava/util/Random;Ldev/purxiy/modules/knife/KnifeSupport2$TypeP;Ldev/purxiy/modules/knife/KnifeCurve;Ldev/purxiy/modules/knife/KnifeCurve;Ldev/purxiy/modules/knife/KnifeSupport2$TypeM;)Ldev/purxiy/modules/knife/KnifeTransform$TypeU;
      // 1d9: goto 28b
      // 1dc: aload 3
      // 1dd: aload 8
      // 1df: getstatic dev/purxiy/modules/knife/KnifeSupport2$TypeX.X Ldev/purxiy/modules/knife/KnifeSupport2$TypeX;
      // 1e2: getstatic dev/purxiy/modules/knife/KnifeSupport3.pe Ldev/purxiy/modules/knife/KnifeCurve;
      // 1e5: aload 9
      // 1e7: bipush 1
      // 1e8: invokestatic dev/purxiy/modules/knife/KnifeSupport2.flow (Ldev/purxiy/modules/knife/KnifeTransform$TypeH;Ljava/util/Random;Ldev/purxiy/modules/knife/KnifeSupport2$TypeX;Ldev/purxiy/modules/knife/KnifeCurve;Ldev/purxiy/modules/knife/KnifeSupport2$TypeM;Z)Ldev/purxiy/modules/knife/KnifeTransform$TypeU;
      // 1eb: goto 28b
      // 1ee: aload 3
      // 1ef: aload 8
      // 1f1: getstatic dev/purxiy/modules/knife/KnifeSupport2$TypeX.q Ldev/purxiy/modules/knife/KnifeSupport2$TypeX;
      // 1f4: getstatic dev/purxiy/modules/knife/KnifeSupport3.M Ldev/purxiy/modules/knife/KnifeCurve;
      // 1f7: aload 9
      // 1f9: bipush 1
      // 1fa: invokestatic dev/purxiy/modules/knife/KnifeSupport2.flow (Ldev/purxiy/modules/knife/KnifeTransform$TypeH;Ljava/util/Random;Ldev/purxiy/modules/knife/KnifeSupport2$TypeX;Ldev/purxiy/modules/knife/KnifeCurve;Ldev/purxiy/modules/knife/KnifeSupport2$TypeM;Z)Ldev/purxiy/modules/knife/KnifeTransform$TypeU;
      // 1fd: goto 28b
      // 200: aload 3
      // 201: aload 8
      // 203: getstatic dev/purxiy/modules/knife/KnifeSupport2$TypeX.i Ldev/purxiy/modules/knife/KnifeSupport2$TypeX;
      // 206: getstatic dev/purxiy/modules/knife/KnifeSupport3.N Ldev/purxiy/modules/knife/KnifeCurve;
      // 209: aload 9
      // 20b: bipush 1
      // 20c: invokestatic dev/purxiy/modules/knife/KnifeSupport2.flow (Ldev/purxiy/modules/knife/KnifeTransform$TypeH;Ljava/util/Random;Ldev/purxiy/modules/knife/KnifeSupport2$TypeX;Ldev/purxiy/modules/knife/KnifeCurve;Ldev/purxiy/modules/knife/KnifeSupport2$TypeM;Z)Ldev/purxiy/modules/knife/KnifeTransform$TypeU;
      // 20f: goto 28b
      // 212: bipush 0
      // 213: istore 12
      // 215: iload 12
      // 217: sipush 500
      // 21a: if_icmplt 221
      // 21d: aconst_null
      // 21e: goto 28b
      // 221: aload 3
      // 222: aload 8
      // 224: getstatic dev/purxiy/modules/knife/KnifeSupport2$TypeX.a Ldev/purxiy/modules/knife/KnifeSupport2$TypeX;
      // 227: getstatic dev/purxiy/modules/knife/KnifeSupport3.pe Ldev/purxiy/modules/knife/KnifeCurve;
      // 22a: new dev/purxiy/modules/knife/KnifeSupport2$TypeM
      // 22d: dup
      // 22e: invokespecial dev/purxiy/modules/knife/KnifeSupport2$TypeM.<init> ()V
      // 231: bipush 1
      // 232: invokestatic dev/purxiy/modules/knife/KnifeSupport2.flow (Ldev/purxiy/modules/knife/KnifeTransform$TypeH;Ljava/util/Random;Ldev/purxiy/modules/knife/KnifeSupport2$TypeX;Ldev/purxiy/modules/knife/KnifeCurve;Ldev/purxiy/modules/knife/KnifeSupport2$TypeM;Z)Ldev/purxiy/modules/knife/KnifeTransform$TypeU;
      // 235: astore 13
      // 237: ldc2_w 120742925516152
      // 23a: dup2
      // 23b: lstore 14
      // 23d: lconst_0
      // 23e: lcmp
      // 23f: ifne 246
      // 242: bipush 0
      // 243: goto 252
      // 246: lload 14
      // 248: lconst_0
      // 249: lcmp
      // 24a: ifge 251
      // 24d: bipush -1
      // 24e: goto 252
      // 251: bipush 1
      // 252: istore 16
      // 254: ldc2_w 120742925516152
      // 257: dup2
      // 258: lstore 17
      // 25a: lconst_0
      // 25b: lcmp
      // 25c: ifne 263
      // 25f: bipush 0
      // 260: goto 26f
      // 263: lload 17
      // 265: lconst_0
      // 266: lcmp
      // 267: ifge 26e
      // 26a: bipush -1
      // 26b: goto 26f
      // 26e: bipush 1
      // 26f: istore 16
      // 271: aload 13
      // 273: getfield dev/purxiy/modules/knife/KnifeTransform$TypeU.o Ljava/lang/String;
      // 276: aload 7
      // 278: bipush 1
      // 279: aaload
      // 27a: invokevirtual java/lang/String.equals (Ljava/lang/Object;)Z
      // 27d: ifeq 285
      // 280: aload 13
      // 282: goto 28b
      // 285: iinc 12 1
      // 288: goto 215
      // 28b: areturn
      return null;
   }

   public static void devHold( String var0, float var1) {
      long var2 = 113826457037158L;
      long var4 = var2 ^ 11368487359518L;
      pN = var0;
      pP = var1;
      pS = var0 != null && var0.startsWith("gen:") ? X(var4, var0) : null;
   }

   public static void devFlow(boolean var0, long var1) {
      px = var0;
      if (var1 != 0L) {
         m = new Random(var1);
      }
   }

   public static void devTrace(float var0) {
      pK = O + var0;
   }

   private static void e(long var0) {
      if (!(O > pK) && b != null && g != null) {
         Vector3f var2 = new Vector3f();
         Vector3f var3 = new Vector3f();
         moment(0, var2, var3);
         log(
            String.format(
               Locale.ROOT,
               "trace %.4f tip %.4f %.4f %.4f hand %.4f %.4f %.4f left %.2f %s %.3f",
               O,
               var3.x,
               var3.y,
               var3.z,
               sg.R[0],
               sg.R[1],
               sg.R[2],
               sg.R[6],
               pq == null ? "-" : pD + ":" + pq.o,
               pZ
            )
         );
      }
   }

   public static void film(Path var0, String var1, float var2, String var3) {
      pQ = var3;
      i = var0;
      j = var1;
      sS = 1.0F / var2;
      e = 0;
      pn = 0;
      pE = -1;
   }

   private static KnifeCurve d(long var0, KnifeApi var2) {
      long var3 = 120742925516152L;
      long var5 = 28154060002264L;
      long var7 = 24692706415822L;
      KnifeUpdater2.ensureSourceClassInitialized();
      if (e > pn) {
         return sg;
      } else {
         class_746 var9 = class_310.method_1551().field_1724;
         if (pE < 0) {
            boolean var10 = j.startsWith("flow:");
            KnifeTransform.TypeU var11 = var10 ? null : X(var3, j);
            if (var11 == null) {
               long var12 = 132878492232844L;
               if (132878492232844L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var37 = var12 < 0L ? -1 : 1;
               }

               if (!var10) {
                  log("no clip " + j);
                  i = null;
                  return sg;
               }
            }

            sZ.set(N.position(new Vector3f()));
            R.set(pM);
            ps.zero();
            sw.zero();
            pB = 99.0F;
            pq = null;
            long var31 = 132878492232845L;
            if (132878492232845L == 0L) {
               boolean var38 = false;
            } else {
               int var39 = var31 < 0L ? -1 : 1;
            }

            if (var10) {
               px = true;
               long var32 = 132878492232845L;
               if (132878492232845L == 0L) {
                  boolean var48 = false;
               } else {
                  int var49 = var32 < 0L ? -1 : 1;
               }

               pE = Math.round(Float.parseFloat(j.substring(5)) / sS);
            } else {
               boolean var15 = j.startsWith("slash") || j.equals("crit") || j.equals("thrust") || j.startsWith("gen:attack");
               A(var11, var15 ? KnifeSupport3.TypeS.O : KnifeSupport3.TypeS.G, 0.2F, var5, 0.0F, var2.tempo());
               long var16 = 132878492232845L;
               if (132878492232845L == 0L) {
                  boolean var40 = false;
               } else {
                  int var41 = var16 < 0L ? -1 : 1;
               }

               if (j.equals("draw")) {
                  pB = 99.0F;
               }

               if (!pQ.isEmpty()) {
                  class_310 var18 = class_310.method_1551();
                  pa = true;
                  pk = pQ.equals("crit");
                  class_239 var19 = var18.field_1765;
                  long var20 = 132878492232844L;
                  if (132878492232844L == 0L) {
                     boolean var42 = false;
                  } else {
                     int var43 = var20 < 0L ? -1 : 1;
                  }

                  long var23 = 132878492232845L;
                  if (132878492232845L == 0L) {
                     boolean var44 = false;
                  } else {
                     int var45 = var23 < 0L ? -1 : 1;
                  }

                  if (var19 instanceof class_3966 var25) {
                     long var26 = 132878492232844L;
                     if (132878492232844L == 0L) {
                        boolean var46 = false;
                     } else {
                        int var47 = var26 < 0L ? -1 : 1;
                     }

                     pW = var25.method_17782();
                     pt = var25.method_17784();
                  } else {
                     pW = null;
                     pt = var18.field_1765 != null ? var18.field_1765.method_17784() : null;
                  }

                  pi = pk ? 1.35F : 1.1F;
               }

               pE = Math.round((var11.E / T + 0.25F) / sS);
            }

            log("film " + j + ": " + pE + " frames");
            if (var9 != null) {
               float var33 = 1.0E-4F;
               float var36 = 1.0F;
               c(var7, var9, var36, var33);
            }

            long var34 = 132878492232845L;
            if (132878492232845L == 0L) {
               boolean var50 = false;
            } else {
               int var51 = var34 < 0L ? -1 : 1;
            }
         } else {
            float var28 = sS;

            while (var28 > 1.0E-6F && var9 != null) {
               float var30 = Math.min(var28, 0.004166667F);
               c(var7, var9, 1.0F, var30);
               var28 -= var30;
            }
         }

         long var29 = 132878492232845L;
         if (132878492232845L == 0L) {
            boolean var52 = false;
         } else {
            int var53 = var29 < 0L ? -1 : 1;
         }

         long var13 = 132878492232844L;
         if (132878492232844L == 0L) {
            boolean var54 = false;
         } else {
            int var55 = var13 < 0L ? -1 : 1;
         }

         if (e >= pE) {
            log("film done: " + e + " frames in " + i);
            i = null;
            if (j.startsWith("flow:")) {
               px = false;
            }

            return sg;
         } else {
            Path var35 = i.resolve(String.format(Locale.ROOT, "f%04d.png", e++));
            RenderPipelines.get().requestScreenshot(var1 -> {
               KnifeUpdater2.ensureSourceClassInitialized();

               try {
                  long var2x = 29408705683854L;
                  if (29408705683854L == 0L) {
                     boolean var10000x = false;
                  } else {
                     int var12x = var2x < 0L ? -1 : 1;
                  }

                  class_1011 var5x = var1;

                  try {
                     Files.createDirectories(var35.getParent());
                     long var6 = 29408705683854L;
                     if (29408705683854L == 0L) {
                        boolean var13x = false;
                     } else {
                        int var14 = var6 < 0L ? -1 : 1;
                     }

                     var1.method_4314(var35);
                  } catch (Throwable var10x) {
                     if (var1 != null) {
                        try {
                           var5x.close();
                        } catch (Throwable var9x) {
                           var10x.addSuppressed(var9x);
                        }
                     }

                     throw var10x;
                  }

                  if (var1 != null) {
                     var1.close();
                  }
               } catch (Exception var11x) {
                  ClientServices.LOGGER.error("Could not save wield film frame {}", var35, var11x);
               }

               pn++;
            });
            return sg;
         }
      }
   }

   public static void devReload() {
      KnifeTransform.clear();
      pr = null;
      ph = null;
   }

   public static String describe() {
      return String.format(
         Locale.ROOT,
         "held %s sheet %s action %s %s %.3f shown %.3f %.3f %.3f %.1f %.1f %.1f left %.2f",
         g == null ? "-" : g.identity(),
         ph,
         pD,
         pq == null ? "-" : pq.o,
         pZ,
         sg.R[0],
         sg.R[1],
         sg.R[2],
         sg.R[3],
         sg.R[4],
         sg.R[5],
         sg.R[6]
      );
   }

   static void log(String var0) {
      ClientServices.LOGGER.info("Wield: {}", var0);
   }

private static enum TypeS {
      r,
      O,
      L,
      W,
      x,
      p,
      G;

      private static final long[] a = new long[]{8675848803955245125L, -1889814507542028902L};
      private static final Integer[] b = new Integer[2];

}
}
