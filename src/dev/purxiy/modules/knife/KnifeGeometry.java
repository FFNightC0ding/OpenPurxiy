package dev.purxiy.modules.knife;

import java.util.Locale;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class KnifeGeometry {
   static final float B = -2.0F;
   static float P = 8.7F;
   static float x = 30.0F;
   private static final float u = 0.1F;
   private static final Vector3f L = new Vector3f(-5.0F, 2.0F, 0.0F);
   private static final Vector3f G = new Vector3f(-5.0F, 2.5F, 0.0F);
   private static final Vector3f j = new Vector3f(5.0F, 2.0F, 0.0F);
   private static final Vector3f b = new Vector3f(5.0F, 2.5F, 0.0F);
   private static final Vector3f r = new Vector3f(0.6F, -1.0F, 0.25F);
   private static final Vector3f i = new Vector3f(-0.6F, -1.0F, 0.25F);
   static float R = 0.4F;
   static final Vector3f H = new Vector3f(0.3F, -0.6F, 0.15F);
   static final Vector3f F = new Vector3f(-0.3F, -0.6F, 0.15F);

   private KnifeGeometry() {
   }

   static float fistX(boolean var0, boolean var1) {
      return var0 ? (var1 ? -0.5F : -1.0F) : (var1 ? 0.5F : 1.0F);
   }

   public static KnifeGeometry.KnifeBounds solve(boolean var0, boolean var1, Vector3f var2, Vector3f var3, Vector3f var4, float var5, KnifeGeometry.KnifeBounds var6) {
      float var7 = var5 / 16.0F;
      float var8 = (P - -2.0F) * var7;
      float var9 = x * var7;
      Vector3f var10 = new Vector3f(var2).sub(var4);
      float var11 = var10.length();
      Vector3f var12 = var11 < 1.0E-5F ? new Vector3f(0.0F, 1.0F, 0.0F) : new Vector3f(var10).div(var11);
      float var13 = Math.max(var11, Math.abs(var9 - var8) + 1.0E-4F);
      if (var13 > (var9 + var8) * 0.999F) {
         var9 = var13 / 0.999F - var8;
      }

      Vector3f var14 = new Vector3f(var2).fma(-var13, var12);
      Vector3f var15 = new Vector3f(var0 ? r : i);
      var15.fma(-var15.dot(var12), var12);
      if (var15.lengthSquared() < 1.0E-6F) {
         var15.set(0.0F, -1.0F, 0.0F).fma(-var12.y, var12);
         if (var15.lengthSquared() < 1.0E-6F) {
            var15.set(var0 ? 1.0F : -1.0F, 0.0F, 0.0F);
         }
      }

      var15.normalize();
      float var16 = Math.clamp((var9 * var9 + var13 * var13 - var8 * var8) / (2.0F * var9 * var13), -1.0F, 1.0F);
      float var17 = (float)Math.sqrt(1.0F - var16 * var16);
      Vector3f var18 = new Vector3f(var14).fma(var9 * var16, var12).fma(var9 * var17, var15);
      Vector3f var19 = new Vector3f(var2).sub(var18).normalize();
      Vector3f var20 = new Vector3f(var3).normalize();
      Vector3f var21 = new Vector3f(var20).fma(-var20.dot(var19), var19).negate();
      if (var21.lengthSquared() < 1.0E-6F) {
         var21.set(var15).cross(var19);
      }

      var21.normalize();
      Vector3f var22 = new Vector3f(var19).cross(var21).normalize();
      Matrix4f var23 = new Matrix4f()
         .set(var22.x, var22.y, var22.z, 0.0F, var19.x, var19.y, var19.z, 0.0F, var21.x, var21.y, var21.z, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F);
      float var24 = fistX(var0, var1);
      Vector3f var25 = new Vector3f(var2).sub(var23.transformDirection(new Vector3f(var24, P, 0.0F)).mul(var7));
      var6.e.set(var14);
      var6.h.set(var18);
      var6.R.set(var2);
      var6.L.set(var19);
      var6.M = 0.0F;
      var23.getNormalizedRotation(var6.A);
      Vector3f var26 = var0 ? (var1 ? G : L) : (var1 ? b : j);
      var6.z.translation(var25).mul(var23).scale(var5).rotateZ(var0 ? -0.1F : 0.1F).translate(-var26.x / 16.0F, -var26.y / 16.0F, -var26.z / 16.0F);
      Vector3f var27 = new Vector3f(var18).sub(var14);
      var6.T = var27.length() / var7;
      var27.normalize();
      Vector3f var28 = new Vector3f(var22).fma(-var22.dot(var27), var27);
      if (var28.lengthSquared() < 1.0E-6F) {
         var28.set(var21).fma(-var21.dot(var27), var27);
      }

      var28.normalize();
      Vector3f var29 = new Vector3f(var28).cross(var27).normalize();
      var6.y
         .set(var28.x, var28.y, var28.z, 0.0F, var27.x, var27.y, var27.z, 0.0F, var29.x, var29.y, var29.z, 0.0F, var18.x, var18.y, var18.z, 1.0F)
         .scale(var5);
      return var6;
   }

   public static void tune(String[] var0) {
      String var1 = var0[0];
      switch (var1) {
         case "arm":
            R = Float.parseFloat(var0[1]);
            break;
         case "grip":
            P = Float.parseFloat(var0[1]);
            break;
         case "upper":
            x = Float.parseFloat(var0[1]);
            break;
         case "shoulder":
            (var0[1].equals("left") ? F : H).set(Float.parseFloat(var0[2]), Float.parseFloat(var0[3]), Float.parseFloat(var0[4]));
            break;
         default:
            throw new IllegalArgumentException("Unknown wield rig value: " + var0[0]);
      }
   }

   public static String describe() {
      return String.format(Locale.ROOT, "arm %.2f, grip %.2f, upper %.1f, shoulders %.3f %.3f %.3f / %.3f %.3f %.3f", R, P, x, H.x, H.y, H.z, F.x, F.y, F.z);
   }

   public static final class KnifeBounds {
      public final Matrix4f z = new Matrix4f();
      public final Matrix4f y = new Matrix4f();
      public float M;
      public float T;
      public final Vector3f e = new Vector3f();
      public final Vector3f h = new Vector3f();
      public final Vector3f R = new Vector3f();
      public final Vector3f L = new Vector3f();
      public final Quaternionf A = new Quaternionf();
   }
}
