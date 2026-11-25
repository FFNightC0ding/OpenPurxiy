package dev.purxiy.modules.skins3d;

import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class Skins3DPose {
   public static final int d = 0;
   public static final int V = 1;
   public static final int j;
   public static final float S = 0.3F;
   public static final float s = 0.95F;
   public static final float r = 1.85F;
   public static final float X = 2.3F;
   public static final float z = 0.3F;
   public static final float b = 0.6F;
   public static final float m = 0.95F;
   public static final float p = 1.35F;
   public static final float o = 1.95F;
   private static final float O = 0.42F;
   private static final float c = 9.4F;
   private static final Vector3f i = new Vector3f(-0.6F, -7.7F, 1.2F);
   private static final float n = 1.0F;
   private static final Vector3f y = new Vector3f(-3.0F, -1.8F, -0.2F);
   private static final Vector3f u = new Vector3f(3.0F, -1.8F, -0.2F);
   private static final Vector3f G = new Vector3f(-2.6F, -1.6F, -1.0F);
   private static final Vector3f x = new Vector3f(2.6F, -1.6F, -1.0F);
   private static final Vector3f U = new Vector3f(-1.0F, 0.35F, 0.3F);
   private static final Vector3f C = new Vector3f(1.0F, 0.35F, 0.3F);
   private static final Vector3f P = new Vector3f(-2.4F, -2.6F, -8.6F);
   private static final Vector3f F = new Vector3f(0.0F, -0.5F, -3.9F);
   private static final Quaternionf W = new Quaternionf().rotationY((float) Math.PI).rotateX((float) (-Math.PI / 2));
   private static final float h = 0.12F;
   private static final float l = 0.18F;
   private static final Vector3f w = new Vector3f(-2.8F, 3.8F, -6.6F);
   private static final Matrix4f E = new Matrix4f();
   private static final Matrix4f f = new Matrix4f();
   private static final Vector3f k = new Vector3f();
   private static final Vector3f e = new Vector3f();
   private static final Vector3f D = new Vector3f();
   private static final Vector3f a = new Vector3f();
   private static final Vector3f t = new Vector3f();
   private static final Vector3f Q = new Vector3f();
   private static final Quaternionf K = new Quaternionf();
   private static final Quaternionf H = new Quaternionf();
   private static final Quaternionf N = new Quaternionf();
   private static final Quaternionf R = new Quaternionf();

   private Skins3DPose() {
   }

   public static boolean bare(int var0, float var1) {
      return var0 == 1 ? var1 >= 0.3F : var0 == 2 && var1 < 1.35F;
   }

   public static float length(int var0) {
      return var0 == 1 ? 2.3F : (var0 == 2 ? 1.95F : 0.0F);
   }

   public static void pose(Skins3DCurve var0, Skins3DGroundPose var1) {
      float var2 = var1.J;
      if (var1.S == 1) {
         float var3 = 0.5F * Skins3DAnimation.easeInOut(var2 / 0.3F) + 0.14F * Skins3DAnimation.smooth((var2 - 0.42F) / 0.53F);
         float var4 = Skins3DAnimation.spring((var2 - 0.95F + 0.05F) / 0.45F);
         var0.v = Skins3DAnimation.mix(var0.v + var3, var0.v - 0.04F, var4);
         var0.L = var0.L - 0.12F * Skins3DAnimation.hold(var2 / 0.95F, 0.3F, 0.2F);
         float var5 = Skins3DAnimation.clamp01((var2 - 0.95F - 0.12F) / 0.55F);
         if (var5 > 0.0F && var5 < 1.0F) {
            var0.L = var0.L + 0.34F * (float)Math.sin(var5 * Math.PI * 2.0 * 2.5) * (1.0F - var5);
            var0.t = var0.t + 0.08F * (float)Math.sin(var5 * Math.PI * 2.0 * 2.5 + 0.8F) * (1.0F - var5);
         }

         var0.y = var0.y + 0.12F * Skins3DAnimation.hold(var2 / 0.95F, 0.35F, 0.15F);
         var0.m = var0.m - 0.1F * Skins3DAnimation.hold(var2 / 0.95F, 0.4F, 0.2F);
         var0.m = var0.m + 0.16F * Skins3DAnimation.hold((var2 - 1.62F) / 0.5F, 0.4F, 0.6F);
         if (var1.h) {
            float var6 = Skins3DAnimation.hold(var2 / 1.15F, 0.3F, 0.3F);
            var0.s = Skins3DAnimation.mix(var0.s, -2.6F, var6);
            var0.e = Skins3DAnimation.mix(var0.e, -0.2F, var6);
            var0.g = Skins3DAnimation.mix(var0.g, 0.6F, var6);
         }

         if (var1.r) {
            float var9 = Skins3DAnimation.hold(var2 / 1.25F, 0.35F, 0.3F);
            var0.X = Skins3DAnimation.mix(var0.X, -0.32F, var9);
            var0.n = Skins3DAnimation.mix(var0.n, -0.3F, var9);
            var0.Y = Skins3DAnimation.mix(var0.Y, 0.55F, var9);
         }
      } else if (var1.S == 2) {
         float var7 = Skins3DAnimation.hold(var2 / 0.70000005F, 0.35F, 0.25F);
         var0.L = Skins3DAnimation.mix(var0.L, var1.K, var7 * 0.8F);
         var0.v = Skins3DAnimation.mix(var0.v, var1.E, var7 * 0.7F);
         var0.m = var0.m + 0.35F * var1.K * var7;
         var0.v = var0.v + 0.28F * Skins3DAnimation.hold((var2 - 0.95F + 0.1F) / 0.50000006F, 0.5F, 0.3F);
         var0.v = var0.v + 0.12F * Skins3DAnimation.hold((var2 - 1.35F) / 0.45F, 0.3F, 0.6F);
         float var8 = Skins3DAnimation.hold((var2 - 0.6F) / 1.35F, 0.2F, 0.3F);
         if (var1.h) {
            var0.s = Skins3DAnimation.mix(var0.s, -1.5F, Skins3DAnimation.hold(var2 / 1.95F, 0.12F, 0.2F));
            var0.g = Skins3DAnimation.mix(var0.g, 0.8F, var8);
         }

         if (var1.r) {
            var0.n = Skins3DAnimation.mix(var0.n, -2.4F, var8);
            var0.X = Skins3DAnimation.mix(var0.X, 0.2F, var8);
            var0.Y = Skins3DAnimation.mix(var0.Y, 1.2F, var8);
         }
      }
   }

   public static void figure(Skins3DRestPose var0, Skins3DGroundPose var1, Skins3DPose.PoseAngles var2, Vector3f var3) {
      long var4 = 110640962831131L;
      long var6 = var4 ^ 36149483724800L;
      long var8 = var4 ^ 24874718711495L;
      var2.c = false;
      var2.B = false;
      float var10 = var1.J;
      f(var0);
      byte var11 = 2;
      Skins3DSupport.palm(var0, var11, 9.4F, e);
      if (var1.S == 1) {
         e(var6, var0, var1, var10, var2);
      } else if (var1.S == 2) {
         T(var0, var1, var10, var8, var2);
      }

      Skins3DSupport.palm(var0, var11, 9.4F, var3);
   }

   private static void e(long var0, Skins3DRestPose var2, Skins3DGroundPose var3, float var4, Skins3DPose.PoseAngles var5) {
      long var6 = 90384860741681L;
      long var8 = 11809890213234L;
      SkinPreset.ensureSourceClassInitialized();
      byte var10 = 2;
      Vector3f var11 = q(i, var8, 0.0F, 0.0F, D);
      float var12 = Skins3DAnimation.hold(var4 / 0.95F, 0.3F, 0.2F);
      var2.b[var10] = var2.b[var10] - 1.3F * var12;
      if (var4 < 0.3F) {
         float var13 = Skins3DAnimation.easeInOut(var4 / 0.3F);
         k.set(e).lerp(var11, var13).add(0.3F, -2.4F * (float)Math.sin(Math.PI * var13), -0.8F * (float)Math.sin(Math.PI * var13));
         Vector3f var14 = k;
         float var15 = Skins3DAnimation.smooth(var4 / 0.18F) * (var3.h ? 1 : 0);
         M(var2, var6, var10, var14, var15);
      } else if (var4 < 0.42F) {
         float var30 = (var4 - 0.3F) / 0.119999975F;
         k.set(var11).add(0.0F, -0.25F * Skins3DAnimation.smooth(var30), -0.1F * Skins3DAnimation.smooth(var30));
         float var33 = var3.h ? 1.0F : 0.0F;
         Vector3f var36 = k;
         M(var2, var6, var10, var36, var33);
         q(var5, i, k, K, 1.0F, 1.0F + 0.05F * Skins3DAnimation.smooth(var30), 1.0F);
      } else if (var4 < 0.95F) {
         float var31 = (var4 - 0.42F) / 0.53F;
         float var34 = Skins3DAnimation.easeInOut(var31);
         k.set(var11).add(0.0F, -0.25F, 0.0F).lerp(P, var34).add(0.0F, -2.4F * (float)Math.sin(Math.PI * var31), 0.0F);
         float var37 = var3.h ? 1.0F : 0.0F;
         Vector3f var16 = k;
         M(var2, var6, var10, var16, var37);
         float var18 = (float)Math.sin(Math.PI * var31);
         H.set(K).rotateX(-1.85F * Skins3DAnimation.easeIn(Math.min(1.0F, var31 * 1.1F)) - 0.25F * var34);
         Skins3DSupport.palm(var2, var10, 9.4F, k);
         q(var5, i, k, H, 1.0F - 0.07F * var18, 1.05F + 0.3F * var18 - 0.05F * var31, 1.0F - 0.05F * var18);
      } else {
         float var32 = Skins3DAnimation.easeInOut((var4 - 0.95F) / 0.3F);
         Vector3f var35 = a.set(-3.4F, 4.2F, -6.2F);
         float var38;
         int var39 = (var38 = var4 - 1.62F) == 0.0F ? 0 : (var38 < 0.0F ? -1 : 1);
         long var17 = 75042447385370L;
         int var41 = var17 < 0L ? -1 : 1;
         if (var39 < 0) {
            k.set(P).lerp(var35, var32).add(0.0F, 0.35F * (float)Math.sin(var4 * 9.0F) * var32, 0.0F);
         } else if (var4 < 1.85F) {
            float var20 = var4 - 1.62F;
            long var21 = 75042447385371L;
            int var24 = var21 < 0L ? -1 : 1;
            float var23 = var20 / 0.23000002F;
            k.set(var35).lerp(D.set(-4.8F, 9.2F, -0.6F), Skins3DAnimation.easeInOut(var23 / 0.45F));
            k.lerp(D.set(-3.0F, 6.4F, -9.0F), Skins3DAnimation.easeOut((var23 - 0.45F) / 0.45F));
         } else {
            float var40 = (var4 - 1.85F) / 0.44999993F;
            k.set(-3.0F, 6.4F, -9.0F).lerp(D.set(-3.6F, 9.6F, -5.0F), Skins3DAnimation.easeOut(var40 * 1.6F));
         }

         float var41x = var4 < 1.85F ? 1.0F : 1.0F - Skins3DAnimation.smooth((var4 - 1.85F - 0.1F) / 0.34999993F);
         float var42 = var41x * (var3.h ? 1 : 0);
         Vector3f var22 = k;
         M(var2, var6, var10, var22, var42);
         long var24 = 75042447385371L;
         int var24x = var24 < 0L ? -1 : 1;
         if (var4 < 1.85F) {
            Skins3DSupport.palm(var2, var10, 9.4F, k);
            long var27x = 75042447385370L;
            int var29 = var27x < 0L ? -1 : 1;
            if (var4 < 1.0699999F) {
               float var29x = Skins3DAnimation.easeIn((var4 - 0.95F) / 0.12F);
               H.set(K).rotateX(-2.1F - 0.5F * var29x);
               q(
                  var5,
                  i,
                  k,
                  H,
                  Skins3DAnimation.mix(0.93F, 0.55F, var29x),
                  Skins3DAnimation.mix(1.0F, 0.45F, var29x),
                  Skins3DAnimation.mix(0.95F, 0.55F, var29x)
               );
            } else {
               l(var5, k, var4 - 0.95F - 0.12F);
            }
         }
      }
   }

   private static void T(Skins3DRestPose var0, Skins3DGroundPose var1, float var2, long var3, Skins3DPose.PoseAngles var5) {
      long var6 = 60876827526291L;
      short var8 = 14173;
      char var9 = 'ﶮ';
      int var10 = (int)(var6 << 48 >>> 48);
      long var11 = 90384860741681L;
      byte var13 = 2;
      SkinPreset.ensureSourceClassInitialized();
      byte var14 = 3;
      boolean var15 = var1.h;
      boolean var16 = var1.r;
      long var17 = 125614968032731L;
      int var43 = var17 < 0L ? -1 : 1;
      if (var2 < 0.6F) {
         float var20 = Skins3DAnimation.smooth(var2 / 0.22F);
         D.set(var1.C[0], var1.C[1], var1.C[2]);
         Vector3f var21 = a.set(var0.V[var13], var0.b[var13], var0.F[var13]).add(D.mul(9.7F));
         float var22 = Skins3DAnimation.easeIn((var2 - 0.3F - 0.04F) / 0.26000002F);
         float var23 = Skins3DAnimation.hold((var2 - 0.3F + 0.03F) / 0.1F, 0.3F, 0.7F);
         k.set(var21).add(0.0F, -0.9F * var23, 0.0F).lerp(w, var22);
         float var24 = var20 * (var15 ? 1 : 0);
         Vector3f var25 = k;
         M(var0, var11, var13, var25, var24);
      } else {
         float var43x = Skins3DAnimation.easeInOut((var2 - 0.6F - 0.08F) / 0.26999998F);
         float var44 = Skins3DAnimation.easeInOut((var2 - 0.95F) / 0.40000004F);
         float var45 = 7.2F * (1.0F - var44);
         float var46 = Skins3DAnimation.mix(1.14F, 1.0F, var44);
         float var47 = Skins3DAnimation.mix(1.05F, 1.0F, var44);
         long var48 = 125614968032732L;
         int var49 = var48 < 0L ? -1 : 1;
         if (var2 < 1.35F) {
            k.set(w).lerp(F(y, var45, 0.8F * (1.0F - var44), var47, var46, t), var43x);
            float var27 = var15 ? 1.0F : 0.0F;
            Vector3f var28 = k;
            M(var0, var11, var13, var28, var27);
            Skins3DSupport.palm(var0, var13, 9.4F, k);
            long var30 = 125614968032732L;
            int var30x = var30 < 0L ? -1 : 1;
            if (var2 < 0.78000003F) {
               l(var5, k, var2 - 0.6F + 0.3F);
            } else {
               float var32 = 0.25F * (float)Math.sin((var2 - 0.6F) * 10.0F) * (1.0F - var43x);
               H.identity().rotateX(0.35F + var32).rotateZ(0.25F);
               H.slerp(K, var43x);
               float var33 = Skins3DAnimation.mix(0.6F, var47, var43x);
               float var34 = Skins3DAnimation.mix(0.55F, var46, var43x);
               q(var5, y, k, H, var33, var34, var33);
               if (var16) {
                  float var35 = Skins3DAnimation.smooth((var2 - 0.6F - 0.12F) / 0.22999996F);
                  a.set(u).sub(y).mul(var33, var34, var33);
                  M(var0, var11, var14, H.transform(a).add(k), var35);
               }
            }
         } else {
            float var49x = (var2 - 1.35F) / 0.6F;
            float var50 = 0.9F * Skins3DAnimation.hold(var49x / 0.5F, 0.4F, 0.6F);
            float var29 = 1.0F - Skins3DAnimation.smooth((var49x - 0.35F) / 0.65F);
            Vector3f var51 = x(var8, G, -var50, 0.0F, k, var9, (short)var10, false);
            float var31 = var29 * (var15 ? 1 : 0);
            long var53 = 125614968032732L;
            int var53x = var53 < 0L ? -1 : 1;
            M(var0, var11, var13, var51, var31);
            if (var16) {
               float var37 = -var50;
               boolean var38 = false;
               Vector3f var52 = k;
               float var39 = 0.0F;
               Vector3f var41 = x;
               Vector3f var42 = x(var8, var41, var37, var39, var52, var9, (short)var10, var38);
               M(var0, var11, var14, var42, var29);
            }
         }
      }
   }

   private static void l(Skins3DPose.PoseAngles var0, Vector3f var1, float var2) {
      float var3 = 0.45F * (float)Math.sin(var2 * 9.0F) * (float)Math.exp(-var2 * 3.2F);
      H.set(W).premul(N.rotationX(var3).rotateZ(0.2F * var3));
      q(var0, F, var1, H, 0.92F, 1.0F, 0.82F);
      var0.B = true;
   }

   private static void M(Skins3DRestPose var0, long var1, int var3, Vector3f var4, float var5) {
      Skins3DSupport.reach(var0, var3, var4, 9.4F, var3 == 2 ? U : C, var5);
   }

   private static void f(Skins3DRestPose var0) {
      byte var1 = 0;
      E.translation(var0.V[var1], var0.b[var1], var0.F[var1])
         .rotateZYX(var0.v[var1], var0.D[var1], var0.d[var1])
         .scale(var0.N[var1], var0.z[var1], var0.x[var1]);
      K.rotationZYX(var0.v[var1], var0.D[var1], var0.d[var1]);
   }

   private static Vector3f q(Vector3f var0, long var1, float var3, float var4, Vector3f var5) {
      long var6 = 60876827526291L;
      short var8 = 14173;
      char var9 = 'ﶮ';
      int var10 = (int)(var6 << 48 >>> 48);
      return x(var8, var0, var3, var4, var5, var9, (short)var10, true);
   }

   private static Vector3f x(int var0, Vector3f var1, float var2, float var3, Vector3f var4, int var5, short var6, boolean var7) {
      var4.set(var1.x, var1.y - (var7 ? 1.0F : 0.0F) - var2, var1.z + var3);
      return E.transformPosition(var4);
   }

   private static Vector3f F(Vector3f var0, float var1, float var2, float var3, float var4, Vector3f var5) {
      var5.set(i.x + (var0.x - i.x) * var3, i.y + (var0.y - i.y) * var4, i.z + (var0.z - i.z) * var3);
      var5.y -= 1.0F + var1;
      var5.z += var2;
      return E.transformPosition(var5);
   }

   private static void q(Skins3DPose.PoseAngles var0, Vector3f var1, Vector3f var2, Quaternionf var3, float var4, float var5, float var6) {
      f.translation(var2).rotate(var3).scale(var4, var5, var6).translate(-var1.x, -var1.y, -var1.z);
      f.mulLocal(E.invert(new Matrix4f()));
      f.getTranslation(D);
      f.getScale(t);
      Skins3DSupport.angles(f.getNormalizedRotation(N), Q);
      var0.c = true;
      var0.k = D.x;
      var0.L = D.y;
      var0.A = D.z;
      var0.G = Q.x;
      var0.n = Q.y;
      var0.b = Q.z;
      var0.u = t.x;
      var0.e = t.y;
      var0.x = t.z;
   }

   static {
      long var0 = 109529752194693L;
      long var2 = 1944569322050997895L ^ var0;
      j = (int)var2;
   }

   public static final class PoseAngles {
      public boolean c;
      public boolean B;
      public float k;
      public float L;
      public float A;
      public float G;
      public float n;
      public float b;
      public float u = 1.0F;
      public float e = 1.0F;
      public float x = 1.0F;
   }
}
