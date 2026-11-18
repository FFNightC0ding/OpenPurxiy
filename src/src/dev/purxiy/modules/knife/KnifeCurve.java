// Sourced By DevFFNightmare
package dev.purxiy.modules.knife;

import java.nio.charset.StandardCharsets;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class KnifeCurve {
   public static final int Y = 0;
   public static final int E = 1;
   public static int D = 2;
   public static int F = 3;
   public static int S = 4;
   public static int J = 5;
   public static int e = 6;
   public static int n = 7;
   public static int m = 8;
   public final float[] R = new float[8];

   public KnifeCurve() {
      this.R[6] = 1.0F;
   }

   public KnifeCurve set(KnifeCurve var1) {
      System.arraycopy(var1.R, 0, this.R, 0, 8);
      return this;
   }

   public KnifeCurve set(float[] var1) {
      System.arraycopy(var1, 0, this.R, 0, 8);
      return this;
   }

   public float x() {
      return this.R[0];
   }

   public float y() {
      return this.R[1];
   }

   public float z() {
      return this.R[2];
   }

   public float left() {
      return Math.clamp(this.R[6], 0.0F, 1.0F);
   }

   public KnifeCurve lerp(KnifeCurve var1, float var2) {
      for (int var3 = 0; var3 < 8; var3++) {
         this.R[var3] = this.R[var3] + (var1.R[var3] - this.R[var3]) * var2;
      }

      return this;
   }

   public KnifeCurve add(KnifeCurve var1, float var2) {
      for (int var3 = 0; var3 < 8; var3++) {
         this.R[var3] = this.R[var3] + var1.R[var3] * var2;
      }

      return this;
   }

   public Vector3f position(Vector3f var1) {
      return var1.set(this.R[0], this.R[1], this.R[2]);
   }

   public Quaternionf rotation(Quaternionf var1) {
      return rotation(this.R[3], this.R[4], this.R[5], var1);
   }

   public static Quaternionf rotation(float var0, float var1, float var2, Quaternionf var3) {
      return var3.rotationZ(rad(var0)).rotateX(rad(-var1)).rotateY(rad(var2));
   }

   public static void angles(Quaternionf var0, float var1, float var2, float[] var3) {
      Vector3f var4 = var0.transform(new Vector3f(0.0F, 1.0F, 0.0F));
      float var5 = (float)Math.toDegrees(Math.asin(Math.clamp(-var4.z, -1.0F, 1.0F)));
      float var6 = Math.abs(var4.x) + Math.abs(var4.y) < 1.0E-4F ? var1 : (float)Math.toDegrees(Math.atan2(-var4.x, var4.y));
      Quaternionf var7 = rotation(var6, var5, 0.0F, new Quaternionf()).invert();
      Vector3f var8 = var7.mul(var0, new Quaternionf()).transform(new Vector3f(1.0F, 0.0F, 0.0F));
      float var9 = (float)Math.toDegrees(Math.atan2(-var8.z, var8.x));
      var3[3] = var6 + 360.0F * Math.round((var1 - var6) / 360.0F);
      var3[4] = var5;
      var3[5] = var9 + 360.0F * Math.round((var2 - var9) / 360.0F);
   }

   static float rad(float var0) {
      return var0 * (float) Math.PI / 180.0F;
   }

}
