// Sourced By DevFFNightmare
package dev.purxiy.modules.trail;

final class TrailUpdater {
   static final int f;
   private static final float B = 1.6F;
   private final double[] t = new double[768];
   private final double[] V = new double[768];
   private final double[] s = new double[768];
   private final float[] A = new float[768];
   private final float[] H = new float[768];
   private final float[] S = new float[768];
   private final float[] m = new float[768];
   private final float[] Y = new float[768];
   private final float[] i = new float[768];
   private final float[] x = new float[768];
   private final float[] d = new float[768];
   private final int[] W = new int[768];
   private int w;

   void spawn(double var1, double var3, double var5, float var7, float var8, float var9, float var10, float var11, float var12, float var13, int var14) {
      if (this.w != 768) {
         int var15 = this.w++;
         this.t[var15] = var1;
         this.V[var15] = var3;
         this.s[var15] = var5;
         this.A[var15] = var7;
         this.H[var15] = var8;
         this.S[var15] = var9;
         this.m[var15] = var10;
         this.Y[var15] = 0.0F;
         this.i[var15] = var11;
         this.x[var15] = var12;
         this.d[var15] = var13;
         this.W[var15] = var14;
      }
   }

   void update(float var1) {
      long var2 = 135920603094704L;
      long var4 = var2 ^ 126978188324960L;
      if (!(var1 <= 0.0F)) {
         float var6 = (float)Math.exp(-1.6F * var1);

         for (int var7 = 0; var7 < this.w; var7++) {
            this.Y[var7] = this.Y[var7] + var1;
            if (this.Y[var7] >= this.i[var7]) {
               this.C(var7--, var4);
            } else {
               this.A[var7] = this.A[var7] * var6;
               this.S[var7] = this.S[var7] * var6;
               this.H[var7] = this.H[var7] * var6 + this.m[var7] * var1;
               this.t[var7] = this.t[var7] + this.A[var7] * var1;
               this.V[var7] = this.V[var7] + this.H[var7] * var1;
               this.s[var7] = this.s[var7] + this.S[var7] * var1;
            }
         }
      }
   }

   private void C(int var1, long var2) {
      TrailRenderer.ensureSourceClassInitialized();
      int var4 = --this.w;
      long var5 = 9767066857167L;
      if (9767066857167L == 0L) {
         boolean var10000 = false;
      } else {
         int var8 = var5 < 0L ? -1 : 1;
      }

      if (var1 != var4) {
         this.t[var1] = this.t[var4];
         this.V[var1] = this.V[var4];
         this.s[var1] = this.s[var4];
         this.A[var1] = this.A[var4];
         this.H[var1] = this.H[var4];
         this.S[var1] = this.S[var4];
         this.m[var1] = this.m[var4];
         this.Y[var1] = this.Y[var4];
         this.i[var1] = this.i[var4];
         this.x[var1] = this.x[var4];
         this.d[var1] = this.d[var4];
         this.W[var1] = this.W[var4];
      }
   }

   void clear() {
      this.w = 0;
   }

   int count() {
      return this.w;
   }

   double x(int var1) {
      return this.t[var1];
   }

   double y(int var1) {
      return this.V[var1];
   }

   double z(int var1) {
      return this.s[var1];
   }

   float progress(int var1) {
      return this.Y[var1] / this.i[var1];
   }

   float size(int var1) {
      return this.x[var1];
   }

   float phase(int var1) {
      return this.d[var1];
   }

   int color(int var1) {
      return this.W[var1];
   }

   static {
      long var0 = 62988250488428L;
      long var2 = -287916798034338452L ^ var0;
      f = (int)var2;
   }
}
