package dev.purxiy.modules.skins3d;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class Skins3DSupport {
   public static final float m = 4.0F;
   private static final Vector3f Q = new Vector3f();
   private static final Vector3f R = new Vector3f();
   private static final Vector3f M = new Vector3f();
   private static final Vector3f j = new Vector3f();
   private static final Vector3f x = new Vector3f();
   private static final Vector3f I = new Vector3f();
   private static final Vector3f Y = new Vector3f();
   private static final Quaternionf r = new Quaternionf();
   private static final Quaternionf L = new Quaternionf();
   private static final Quaternionf y = new Quaternionf();

   private Skins3DSupport() {
   }

   public static void reach(Skins3DRestPose var0, int var1, Vector3f var2, float var3, Vector3f var4, float var5) {
      if (!(var5 <= 0.0F)) {
         float var6 = 4.0F;
         float var7 = var3 - 4.0F;
         Q.set(var2).sub(var0.V[var1], var0.b[var1], var0.F[var1]);
         float var8 = Math.max(Math.abs(var6 - var7) + 0.05F, Math.min(var6 + var7 - 0.02F, Q.length()));
         float var9 = (var8 * var8 - var6 * var6 - var7 * var7) / (2.0F * var6 * var7);
         float var10 = (float)Math.acos(Math.max(-1.0F, Math.min(1.0F, var9)));
         R.set(0.0F, var6 + var7 * (float)Math.cos(var10), -var7 * (float)Math.sin(var10));
         r.rotationTo(x.set(R).normalize(), I.set(Q).normalize());
         r.transform(M.set(0.0F, var6, 0.0F));
         Vector3f var11 = I;
         M.sub(x.set(var11).mul(M.dot(var11)));
         j.set(var4).sub(Y.set(var11).mul(var4.dot(var11)));
         if (M.lengthSquared() > 1.0E-6F && j.lengthSquared() > 1.0E-6F) {
            M.normalize();
            j.normalize();
            float var12 = (float)Math.atan2(x.set(M).cross(j).dot(var11), M.dot(j));
            r.premul(L.fromAxisAngleRad(var11, var12));
         }

         if (var5 < 1.0F) {
            y.rotationZYX(var0.v[var1], var0.D[var1], var0.d[var1]);
            y.slerp(r, var5, r);
            var10 = var0.y[var1] + (var10 - var0.y[var1]) * var5;
         }

         angles(r, Y);
         var0.d[var1] = Y.x;
         var0.D[var1] = Y.y;
         var0.v[var1] = Y.z;
         var0.y[var1] = var10;
      }
   }

   public static Vector3f angles(Quaternionf var0, Vector3f var1) {
      float var2 = var0.x;
      float var3 = var0.y;
      float var4 = var0.z;
      float var5 = var0.w;
      float var6 = 2.0F * (var2 * var4 - var5 * var3);
      float var7 = 2.0F * (var3 * var4 + var5 * var2);
      float var8 = 1.0F - 2.0F * (var2 * var2 + var3 * var3);
      float var9 = 2.0F * (var2 * var3 + var5 * var4);
      float var10 = 1.0F - 2.0F * (var3 * var3 + var4 * var4);
      float var11 = 2.0F * (var2 * var3 - var5 * var4);
      float var12 = 1.0F - 2.0F * (var2 * var2 + var4 * var4);
      float var13 = Math.max(-1.0F, Math.min(1.0F, -var6));
      float var14 = (float)Math.asin(var13);
      return Math.abs(var13) < 0.9999F
         ? var1.set((float)Math.atan2(var7, var8), var14, (float)Math.atan2(var9, var10))
         : var1.set((float)Math.atan2(var13 > 0.0F ? var11 : -var11, var12), var14, 0.0F);
   }

   public static Vector3f palm(Skins3DRestPose var0, int var1, float var2, Vector3f var3) {
      float var4 = var0.y[var1];
      var3.set(0.0F, 4.0F + (var2 - 4.0F) * (float)Math.cos(var4), -(var2 - 4.0F) * (float)Math.sin(var4));
      r.rotationZYX(var0.v[var1], var0.D[var1], var0.d[var1]).transform(var3);
      return var3.add(var0.V[var1], var0.b[var1], var0.F[var1]);
   }

   public static Quaternionf forearm(Skins3DRestPose var0, int var1, Quaternionf var2) {
      return var2.rotationZYX(var0.v[var1], var0.D[var1], var0.d[var1]).rotateX(-var0.y[var1]);
   }
}
