package dev.purxiy.modules.knife;

import java.util.Locale;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class KnifePlacement {
   static final Vector3f m = new Vector3f(-0.22F, 0.14F, -0.03F);
   static final Vector3f l = new Vector3f(0.6F, -0.85F, 0.4F);
   static float o = -45.0F;
   static float L = 45.0F;
   static float X;
   static float G = -70.0F;
   static float K = 0.55F;
   private static final float[][] z = new float[][]{
      {0.0F, 0.0F, 0.0F}, {-0.002F, -0.1F, 0.0F}, {0.0F, -0.005F, 0.0F}, {0.0F, -0.01F, 0.0F}, {0.0F, -0.02F, 0.0F}
   };
   private static final float[] J = new float[]{40.0F, 0.0F, 0.0F, 0.0F, 0.0F};
   private static final float[][] O = new float[][]{
      {0.004F, -0.181F, 0.0F}, {-0.021F, 0.105F, 0.0F}, {-0.087F, 0.108F, 0.0F}, {0.0F, -0.04F, 0.0F}, {0.0F, 0.08F, 0.0F}
   };
   private static final float a = 0.1F;
   private static final Vector3f V = new Vector3f(-5.0F, 2.0F, 0.0F).div(16.0F);
   private static final Vector3f i = new Vector3f(-1.0F, 8.5F, 0.0F).div(16.0F);
   private static final Vector3f M = new Vector3f(-5.0F, 2.5F, 0.0F).div(16.0F);
   private static final Vector3f Y = new Vector3f(-0.5F, 8.5F, 0.0F).div(16.0F);
   static final float j = -1.12F;
   private static final Vector3f x = new Vector3f(0.1F, -0.62F, 0.16F);
   private static final float h = -38.0F;
   private static final Vector3f[] C = new Vector3f[z.length];
   private static final float[] g = new float[z.length];

   private KnifePlacement() {
   }

   public static Matrix4f hand(KnifeGestureRenderer.Data var0, Matrix4f var1) {
      Matrix4f var2 = l(new Matrix4f());
      var1.translation(m.x + var0.hx(), m.y + var0.hy(), m.z + var0.hz());
      var1.mul(rest(new Matrix4f())).mul(var2);
      var1.rotateZ(p(var0.cock())).rotateX(p(var0.flex())).rotateY(p(var0.twist()));
      return var1.mul(var2.invert());
   }

   public static Matrix4f knife(int var0, KnifeGestureRenderer.Data var1, float var2, Matrix4f var3, Matrix4f var4) {
      float[] var5 = z[var0];
      var4.set(var3).mul(l(new Matrix4f())).scale(var2).rotateZ(p(J[var0]));
      var4.translate(-var5[0], -var5[1], -var5[2]);
      return Z(var0, var1, var4);
   }

   public static Vector3f fist() {
      return new Vector3f(m);
   }

   public static Matrix4f mirror(Matrix4f var0, Matrix4f var1) {
      return var1.identity().translate(-1.12F, 0.0F, 0.0F).scale(-1.0F, 1.0F, 1.0F).mul(var0).scale(-1.0F, 1.0F, 1.0F);
   }

   public static Matrix4f unmirror(Matrix4f var0, Matrix4f var1) {
      return var1.identity().scale(-1.0F, 1.0F, 1.0F).translate(1.12F, 0.0F, 0.0F).mul(var0).scale(-1.0F, 1.0F, 1.0F);
   }

   public static Matrix4f otherArm(Matrix4f var0, boolean var1, Matrix4f var2) {
      Matrix4f var3 = arm(new Matrix4f(), unmirror(var0, new Matrix4f()), var1, new Matrix4f());
      return var2.identity().translate(-1.12F, 0.0F, 0.0F).scale(-1.0F, 1.0F, 1.0F).mul(var3).scale(-1.0F, 1.0F, 1.0F);
   }

   public static Matrix4f away(float var0, Matrix4f var1) {
      float var2 = Math.clamp(var0, 0.0F, 1.0F);
      float var3 = 1.0F - var2 * var2 * (3.0F - 2.0F * var2);
      var1.translation(x.x * var3, x.y * var3, x.z * var3);
      Vector3f var4 = new Vector3f(m);
      return var1.translate(var4).rotateX(p(-38.0F * var3)).translate(var4.negate());
   }

   public static Matrix4f onStage(int var0, KnifeGestureRenderer.GesturePose var1, boolean var2, Matrix4f var3) {
      long var4 = 77818252885103L;
      long var6 = var4 ^ 106599950914514L;
      if (var2) {
         Matrix4f var12 = knife(var0, KnifeGestureRenderer.M, 1.0F, hand(KnifeGestureRenderer.M, new Matrix4f()), new Matrix4f());
         var3.set(var12.invert().mul(var1.r));
         return R(var6, var0, var3);
      } else {
         Matrix4f var8 = rest(new Matrix4f()).mul(l(new Matrix4f()));
         Quaternionf var9 = new Matrix4f(var8).invert().mul(var1.N).mul(l(new Matrix4f())).getNormalizedRotation(new Quaternionf());
         float var10 = (float)(2.0 * Math.atan2(var9.y, var9.w));
         Matrix4f var11 = knife(var0, KnifeGestureRenderer.M, 1.0F, var1.N, new Matrix4f()).invert().mul(var1.r);
         var3.translation(z[var0][0], z[var0][1], z[var0][2]).rotateY(var10).translate(-z[var0][0], -z[var0][1], -z[var0][2]).mul(var11);
         return R(var6, var0, var3);
      }
   }

   private static Matrix4f R(long var0, int var2, Matrix4f var3) {
      Vector3f var4 = centre(var2);
      KnifeSwingState.ensureSourceClassInitialized();
      Vector3f var5 = var3.transformPosition(new Vector3f(var4)).sub(var4);
      float var6 = var5.length();
      float var7 = 0.22F;
      long var8 = 42012386848700L;
      int var11 = var8 < 0L ? -1 : 1;
      if (var6 > var7) {
         float var11x = var7 + (var6 - var7) * 0.35F;
         Vector3f var12 = new Vector3f(var5).mul(var11x / var6 - 1.0F);
         var3.set(new Matrix4f().translation(var12).mul(var3));
      }

      return var3;
   }

   public static Vector3f centre(int var0) {
      long var1 = 32583969332245L;
      long var3 = var1 ^ 133926152879240L;
      Y(var0, var3);
      return new Vector3f(C[var0]);
   }

   public static Vector3f centre(int var0, float var1, float var2) {
      long var3 = 55789718173225L;
      long var5 = var3 ^ 15311527199610L;
      return KnifeModels.get(var0, "Signature").handleA() == null
         ? centre(var0)
         : A(var5, KnifeModels.get(var0, "Signature").posed(new Matrix4f(), var1, var2));
   }

   private static Vector3f A(long var0, KnifeBuilder var2) {
      Vector3f var3 = new Vector3f();
      Vector3f var4 = new Vector3f();
      Vector3f var5 = new Vector3f();
      Vector3f var6 = new Vector3f();
      float var7 = 0.0F;
      byte var8 = 0;

      while (var8 + 2 < var2.vertexCount()) {
         var4.set(var2.x(var8), var2.y(var8), var2.z(var8));
         var5.set(var2.x(var8 + 1), var2.y(var8 + 1), var2.z(var8 + 1));
         var6.set(var2.x(var8 + 2), var2.y(var8 + 2), var2.z(var8 + 2));
         long var9 = 69619011728722L;
         int var12 = var9 < 0L ? -1 : 1;
         float var12x = new Vector3f(var5).sub(var4).cross(new Vector3f(var6).sub(var4)).length() * 0.5F;
         var3.add(new Vector3f(var4).add(var5).add(var6).mul(var12x / 3.0F));
         var7 += var12x;
         var8 = (byte)(var8 + 3);
         long var13 = 69619011728723L;
         int var14 = var13 < 0L ? -1 : 1;
      }

      return var7 > 0.0F ? var3.div(var7) : var3;
   }

   public static float length(int var0) {
      long var1 = 40296005564342L;
      long var3 = var1 ^ 71239317493547L;
      Y(var0, var3);
      return g[var0];
   }

   private static synchronized void Y(int var0, long var1) {
      if (C[var0] == null) {
         KnifeBuilder var3 = KnifeModels.get(var0, "Signature").posed(var0, KnifeGestureRenderer.M);
         Vector3f var4 = new Vector3f();
         Vector3f var5 = new Vector3f();
         Vector3f var6 = new Vector3f();
         Vector3f var7 = new Vector3f();
         float var8 = 0.0F;
         float var9 = 0.0F;

         for (byte var10 = 0; var10 + 2 < var3.vertexCount(); var10 = (byte)(var10 + 3)) {
            long var11 = 110417555644572L;
            int var14 = var11 < 0L ? -1 : 1;
            var5.set(var3.x(var10), var3.y(var10), var3.z(var10));
            var6.set(var3.x(var10 + 1), var3.y(var10 + 1), var3.z(var10 + 1));
            var7.set(var3.x(var10 + 2), var3.y(var10 + 2), var3.z(var10 + 2));
            long var14x = 110417555644573L;
            int var16 = var14x < 0L ? -1 : 1;
            float var16x = new Vector3f(var6).sub(var5).cross(new Vector3f(var7).sub(var5)).length() * 0.5F;
            var4.add(new Vector3f(var5).add(var6).add(var7).mul(var16x / 3.0F));
            var8 += var16x;
            var9 = Math.max(var9, Math.max(var5.y, Math.max(var6.y, var7.y)));
         }

         g[var0] = var9;
         C[var0] = var8 > 0.0F ? var4.div(var8) : new Vector3f();
      }
   }

   public static Vector3f grip(int var0) {
      return new Vector3f(z[var0]);
   }

   public static Matrix4f inHand(int var0, KnifeGestureRenderer.Data var1, Matrix4f var2) {
      return Z(var0, var1, var2.identity());
   }

   private static Matrix4f Z(int var0, KnifeGestureRenderer.Data var1, Matrix4f var2) {
      float[] var3 = O[var0];
      var2.translate(var1.kx(), var1.ky(), var1.kz());
      var2.translate(var3[0], var3[1], var3[2]);
      var2.rotateX(p(var1.pitch())).rotateZ(p(var1.roll()));
      return var2.translate(-var3[0], -var3[1], -var3[2]).rotateY(p(var1.yaw()));
   }

   public static Matrix4f arm(Matrix4f var0, Matrix4f var1, boolean var2, Matrix4f var3) {
      Matrix4f var4 = new Matrix4f(var0).mul(var1);
      Vector3f var5 = var4.getTranslation(new Vector3f());
      Vector3f var6 = var4.transformDirection(new Vector3f(1.0F, 0.0F, 0.0F));
      Vector3f var7 = new Vector3f(var5).sub(l).normalize();
      Vector3f var8 = new Vector3f(var6).fma(-var6.dot(var7), var7);
      if (var8.lengthSquared() < 1.0E-8F) {
         var8.set(Math.abs(var7.z) < 0.9F ? new Vector3f(0.0F, 0.0F, 1.0F).cross(var7) : new Vector3f(1.0F, 0.0F, 0.0F).cross(var7));
      }

      var8.normalize();
      Vector3f var9 = new Vector3f(var8).cross(var7);
      Vector3f var10 = var2 ? M : V;
      Vector3f var11 = new Vector3f(var2 ? Y : i).rotateZ(0.1F).add(var10);
      var3.translation(var5)
         .mul(new Matrix4f().set(var8.x, var8.y, var8.z, 0.0F, var7.x, var7.y, var7.z, 0.0F, var9.x, var9.y, var9.z, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F));
      var3.scale(K).rotateZ(-0.1F);
      return var3.translate(-var11.x, -var11.y, -var11.z);
   }

   static Matrix4f rest(Matrix4f var0) {
      Vector3f var1 = new Vector3f(m).sub(l).normalize();
      Vector3f var2 = new Vector3f(0.0F, 1.0F, 0.0F).fma(-var1.y, var1).normalize();
      Vector3f var3 = new Vector3f(var2).negate();
      Vector3f var4 = new Vector3f(var1).cross(var3);
      var0.set(var4.x, var4.y, var4.z, 0.0F, var1.x, var1.y, var1.z, 0.0F, var3.x, var3.y, var3.z, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
      return var0.rotateY(p(o));
   }

   private static Matrix4f l(Matrix4f var0) {
      var0.rotationXYZ(p(L), p(X), p(G));
      return var0.mul(new Matrix4f().set(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, -1.0F, 0.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F));
   }

   private static float p(float var0) {
      return var0 * (float) Math.PI / 180.0F;
   }

   public static void tune(String[] var0) {
      String var1 = var0[0];
      switch (var1) {
         case "fist":
            m.set(Float.parseFloat(var0[1]), Float.parseFloat(var0[2]), Float.parseFloat(var0[3]));
            break;
         case "shoulder":
            l.set(Float.parseFloat(var0[1]), Float.parseFloat(var0[2]), Float.parseFloat(var0[3]));
            break;
         case "twist":
            o = Float.parseFloat(var0[1]);
            break;
         case "grip":
            L = Float.parseFloat(var0[1]);
            X = Float.parseFloat(var0[2]);
            G = Float.parseFloat(var0[3]);
            break;
         case "arm":
            K = Float.parseFloat(var0[1]);
            break;
         default:
            throw new IllegalArgumentException("Unknown knife rig value: " + var0[0]);
      }
   }

   public static String describe() {
      return String.format(
         Locale.ROOT, "fist %.3f %.3f %.3f, shoulder %.3f %.3f %.3f, twist %.1f, grip %.1f %.1f %.1f, arm %.2f", m.x, m.y, m.z, l.x, l.y, l.z, o, L, X, G, K
      );
   }
}
