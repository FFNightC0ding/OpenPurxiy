package dev.purxiy.modules.skins3d;

import java.util.Arrays;

public final class Skins3DSupport3 {
   public static final int u = 0;
   public static final int C = 1;
   public static int f = 2;
   public static int E = 3;
   public static int n = 4;
   public static int S = 5;
   public static int K = 6;
   public static int a = 7;
   public final float[] V = new float[7];
   public final float[] b = new float[7];
   public final float[] F = new float[7];
   public final float[] d = new float[7];
   public final float[] D = new float[7];
   public final float[] v = new float[7];
   public final float[] N = new float[7];
   public final float[] z = new float[7];
   public final float[] x = new float[7];
   public final float[] y = new float[7];

   public Skins3DSupport3() {
      Arrays.fill(this.N, 1.0F);
      Arrays.fill(this.z, 1.0F);
      Arrays.fill(this.x, 1.0F);
   }

   public static Skins3DSupport3 rest(float[][] var0) {
      Skins3DSupport3 var1 = new Skins3DSupport3();

      for (int var2 = 0; var2 < 6; var2++) {
         var1.V[var2] = var0[var2][0];
         var1.b[var2] = var0[var2][1];
         var1.F[var2] = var0[var2][2];
      }

      return var1;
   }

   public Skins3DSupport3 set(Skins3DSupport3 var1) {
      System.arraycopy(var1.V, 0, this.V, 0, 7);
      System.arraycopy(var1.b, 0, this.b, 0, 7);
      System.arraycopy(var1.F, 0, this.F, 0, 7);
      System.arraycopy(var1.d, 0, this.d, 0, 7);
      System.arraycopy(var1.D, 0, this.D, 0, 7);
      System.arraycopy(var1.v, 0, this.v, 0, 7);
      System.arraycopy(var1.N, 0, this.N, 0, 7);
      System.arraycopy(var1.z, 0, this.z, 0, 7);
      System.arraycopy(var1.x, 0, this.x, 0, 7);
      System.arraycopy(var1.y, 0, this.y, 0, 7);
      return this;
   }

   public boolean finite() {
      for (int var1 = 0; var1 < 7; var1++) {
         float var2 = this.V[var1]
            + this.b[var1]
            + this.F[var1]
            + this.d[var1]
            + this.D[var1]
            + this.v[var1]
            + this.N[var1]
            + this.z[var1]
            + this.x[var1]
            + this.y[var1];
         if (!Float.isFinite(var2)) {
            return false;
         }
      }

      return true;
   }
}
