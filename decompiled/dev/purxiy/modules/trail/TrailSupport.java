package dev.purxiy.modules.trail;

final class TrailSupport {
   final TrailSupport3 j = new TrailSupport3();
   final TrailUpdater b = new TrailUpdater();
   private float g;
   private double z;
   private double C;
   private double s;
   private boolean J;
   private float A;
   private int E;
   boolean T;
   private double N = Double.NaN;

   TrailSupport(int var1) {
      this.E = var1 * 625341585 | 1;
   }

   void follow(double var1, double var3, double var5, double var7, float var9, TrailStyle var10, int[] var11, float var12, float var13) {
      long var14 = 83609032064648L;
      long var16 = var14 ^ 133733853850779L;
      int var18 = (int)((var14 ^ 133733853850779L) >>> 32);
      int var19 = (int)((var14 ^ 133733853850779L) << 32 >>> 48);
      int var20 = (int)(var16 << 48 >>> 48);
      if (this.J && var9 > 0.0F) {
         double var21 = var1 - this.z;
         double var23 = var3 - this.C;
         double var25 = var5 - this.s;
         float var27 = (float)Math.sqrt(var21 * var21 + var23 * var23 + var25 * var25);
         float var28 = var27 > 8.0 ? 0.0F : var27 / var9;
         this.g = this.g + (var28 - this.g) * (1.0F - (float)Math.exp(-var9 * 10.0F));
      }

      double var29 = this.J ? this.z : var1;
      double var30 = this.J ? this.C : var3;
      double var31 = this.J ? this.s : var5;
      this.J = true;
      this.z = var1;
      this.C = var3;
      this.s = var5;
      this.j.follow(var1, var3, var5, var7);
      this.j.trim(var7, var13);
      this.b.update(var9);
      if (var9 > 0.0F && var10 == TrailStyle.T) {
         this.q(var18, var29, var30, var31, var1, var3, var5, var19, (short)var20, var9, var11, var12, var13);
      }
   }

   double ground(boolean var1, double var2, double var4) {
      if (var1 || Double.isNaN(this.N)) {
         this.N = var1 ? var2 : var4;
      } else if (var4 < this.N) {
         this.N = var4;
      }

      float var6 = TrailSupport2.smoothstep(1.3F, 2.6F, (float)(var4 - this.N));
      return this.N + (var4 - this.N) * var6;
   }

   void idle(double var1, float var3, float var4) {
      this.j.trim(var1, var4);
      this.b.update(var3);
      this.J = false;
      this.g = 0.0F;
   }

   boolean finished(double var1, float var3) {
      return !this.j.visible(var1, var3) && this.b.count() == 0;
   }

   void clear() {
      this.j.clear();
      this.b.clear();
      this.J = false;
      this.g = 0.0F;
      this.A = 0.0F;
      this.N = Double.NaN;
   }

   float speed() {
      return this.g;
   }

   private void q(
      int var1,
      double var2,
      double var4,
      double var6,
      double var8,
      double var10,
      double var12,
      int var14,
      short var15,
      float var16,
      int[] var17,
      float var18,
      float var19
   ) {
      long var20 = 59012456223251L;
      long var22 = var20 ^ 87630282140383L;
      long var24 = var20 ^ 111703629039627L;
      TrailRenderer.ensureSourceClassInitialized();
      float var26 = Math.min(this.g, 12.0F);
      if (!(var26 < 0.4F)) {
         this.A += var26 * 6.5F * var16;
         int var27 = Math.min((int)this.A, 24);
         this.A = this.A - (int)this.A;

         for (int var28 = 0; var28 < var27; var28++) {
            float var29 = this.p(var22);
            double var30 = var2 + (var8 - var2) * var29;
            double var32 = var4 + (var10 - var4) * var29;
            double var34 = var6 + (var12 - var6) * var29;
            float var36 = this.p(var22) * (float) (Math.PI * 2);
            float var37 = (float)Math.sqrt(this.p(var22)) * 0.3F * var18;
            float var38 = this.p(var22);
            var38 = var38 * var38 * 1.05F * var18;
            float var39 = (0.09F + 0.07F * this.p(var22) * this.p(var22)) * var18;
            float var40 = 0.22F;
            this.b
               .spawn(
                  var30 + Math.cos(var36) * var37,
                  var32 + 0.05 + var38,
                  var34 + Math.sin(var36) * var37,
                  (this.p(var22) - 0.5F) * var40,
                  0.12F + 0.22F * this.p(var22),
                  (this.p(var22) - 0.5F) * var40,
                  0.04F + 0.08F * this.p(var22),
                  (0.6F + 0.5F * this.p(var22)) * var19,
                  var39,
                  this.p(var22),
                  this.U(var17, var24)
               );
         }
      }
   }

   private int U(int[] var1, long var2) {
      long var4 = 134247451670732L;
      TrailRenderer.ensureSourceClassInitialized();
      float var6 = this.p(var4);
      float var7;
      int var8 = (var7 = var6 - 0.42F) == 0.0F ? 0 : (var7 < 0.0F ? -1 : 1);
      long var9 = 88183989377559L;
      int var12 = var9 < 0L ? -1 : 1;
      var12 = var8 < 0 ? var1[0] : (var6 < 0.8F ? var1[1] : var1[2]);
      float var13 = this.p(var4) * this.p(var4) * 0.4F;
      int var14 = var12 >> 16 & 0xFF;
      int var15 = var12 >> 8 & 0xFF;
      int var16 = var12 & 0xFF;
      var14 += (int)((255 - var14) * var13);
      var15 += (int)((255 - var15) * var13);
      var16 += (int)((255 - var16) * var13);
      return var14 << 16 | var15 << 8 | var16;
   }

   private float p(long var1) {
      int var3 = this.E;
      var3 ^= var3 << 13;
      var3 ^= var3 >>> 17;
      var3 ^= var3 << 5;
      this.E = var3;
      return (var3 >>> 8) / 1.6777216E7F;
   }
}
