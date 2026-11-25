package dev.purxiy.modules.auras;

final class AuraGeometry {
   static final int j = 0;
   static final int Y = 1;
   static final int R = 16;
   private static final float W = (float) (Math.PI * 2);
   private static final float q = 0.55F;
   private static final float y = 0.34F;
   private static final float i = 0.45F;
   private static final int d = 3;
   private static final float s = 0.76F;
   private static final float D = 2.3F;
   private static final float x = 2.5F;
   private static final int P = 26;
   private static final float G = 0.064F;
   private static final float E = 0.52F;
   private static final float B = 0.046F;
   private static final float T = 0.36F;
   private static final int L = 28;
   private static final float X = 0.027F;
   private static final float f = 0.21F;
   private static final int v = 3;
   private static final float o = 2.6F;
   private static final float p = 1.7F;
   private static final float w = 0.46F;
   private static final int t = 28;
   private static final float e = 0.64F;
   private static final float A = 0.04F;
   private static final float I = 0.32F;
   private static final float b = 0.86F;
   private static final float l = 0.28F;
   private static final float u = 0.033F;
   private static final float r = 0.3F;
   private static final float n = 0.075F;
   private static final float K = 0.0105F;
   private static final float c = 0.13F;
   private static final float[][] V = new float[][]{
      {0.0F, 0.8F, 1.0F, 1.25F},
      {19.0F, 0.88F, 0.95F, 0.85F},
      {39.0F, 0.84F, 1.0F, 1.0F},
      {54.0F, 0.71F, 0.97F, 1.1F},
      {140.0F, 0.4F, 1.0F, 1.15F},
      {161.0F, 0.53F, 0.96F, 0.9F},
      {178.0F, 0.36F, 1.02F, 1.0F},
      {200.0F, 0.22F, 0.98F, 0.85F},
      {255.0F, 0.62F, 1.0F, 1.0F},
      {276.0F, 0.55F, 0.95F, 1.3F},
      {297.0F, 0.66F, 1.0F, 0.9F},
      {95.0F, 0.56F, 1.06F, 0.7F},
      {226.0F, 0.86F, 1.0F, 0.65F},
      {322.0F, 0.3F, 1.05F, 0.75F},
      {62.0F, 0.24F, 1.0F, 0.6F}
   };
   private static final int[][] Q = new int[][]{{0, 1}, {1, 2}, {2, 3}, {4, 5}, {5, 6}, {6, 4}, {6, 7}, {8, 9}, {9, 10}};
   private final float[] m = new float[16];
   private final float[] N = new float[3];
   private final float[] Z = new float[32];
   private final float[] U = new float[32];
   private final float[] h = new float[32];
   private final float[] z = new float[V.length];
   private final float[] a = new float[V.length];
   private final float[] C = new float[V.length];
   private final float[] k = new float[V.length];
   private final int[] S = new int[V.length];
   private static final long[] g = new long[]{
      -3337022719091283648L,
      1365449099565429457L,
      4346262196876722271L,
      528759969374424078L,
      -38652365415384672L,
      -2688381837800390245L,
      -3439138796898139221L,
      4682943202464527288L,
      1425548869528550588L,
      -5005168809526230168L,
      1008986046565679533L,
      3922570760172881318L,
      8289111085076752325L,
      -345575479621224265L,
      2261950202455570968L,
      6398416780290695736L,
      1398860903410091398L,
      -3255679169268972876L,
      -2159518274115406385L,
      -3410112466512411545L,
      -6339472194325604597L,
      -2570671231660331246L,
      1857839888188145333L,
      -8968501845390747184L,
      -8292184746266716294L,
      -1246050075787639370L,
      4793752572525404938L,
      82910740746379284L,
      6024459996655195309L,
      -4622485460938214034L,
      4477057551833126412L,
      8997141050509256782L,
      3926903503882161600L,
      7780161412275508079L,
      3216083725713563341L,
      8309111848597009134L,
      181650606207152153L,
      1339278798697293006L,
      278365094542964643L,
      2621048443851568094L,
      -62535970896483234L,
      8413499209094255001L,
      -5604143753959742728L,
      9119845833875460303L
   };
   private static final Integer[] F = new Integer[44];

   int draw(AuraGeometry.Listener var1, AuraGeometry.MeshVertex var2, AuraGeometry.VertexEmitter var3) {
      long var4 = 31162300070460L;
      long var6 = var4 ^ 94736833814409L;
      long var8 = var4 ^ 102943141225511L;
      long var10 = var4 ^ 42311128872048L;
      long var12 = var4 ^ 64996174736314L;
      long var14 = var4 ^ 77376162598177L;
      if (var2.n <= 0.002F) {
         return 0;
      } else {
         int var16 = var2.v > 0.0F ? this.H(var1, var2, var3, var12) : 0;

         return var16 + switch (AuraGeometry.MeshBuffer.a[var2.f.ordinal()]) {
            case 1 -> this.I(var1, var10, var2, var3);
            case 2 -> this.u(var1, var2, var3, var14);
            case 3 -> this.p(var6, var1, var2, var3);
            case 4 -> this.B(var1, var8, var2, var3);
            default -> throw new MatchException(null, null);
         };
      }
   }

   private int H(AuraGeometry.Listener var1, AuraGeometry.MeshVertex var2, AuraGeometry.VertexEmitter var3, long var4) {
      long var6 = 60827205411640L;
      long var8 = 119501985133691L;
      AuraStyle.ensureSourceClassInitialized();
      float var10 = 0.34F * var2.v * smooth(var2.n) * (1.0F + 0.6F * var2.k);
      float var11;
      int var12 = (var11 = var10 - 0.004F) == 0.0F ? 0 : (var11 < 0.0F ? -1 : 1);
      long var13 = 43201200869765L;
      int var16 = var13 < 0L ? -1 : 1;
      if (var12 < 0) {
         return 0;
      } else {
         var1.feet(0.06F, this.N);
         float var16x = var1.height();
         float var17 = var1.width() * var2.T;
         float var18 = this.N[0];
         float var19 = this.N[1] + var16x * 0.55F;
         float var20 = this.N[2];
         float var21 = (float)Math.sqrt(var18 * var18 + var19 * var19 + var20 * var20);
         long var22 = 43201200869765L;
         int var25 = var22 < 0L ? -1 : 1;
         if (var21 < 0.6F) {
            return 0;
         } else {
            float var25x = 0.45F * var17 / var21;

            for (int var26 = 0; var26 < 3; var26++) {
               float var27 = 1.0F + 0.08F * (float)Math.sin(var2.b * 0.8 + var26 * 2.1);
               long var28 = 43201200869766L;
               int var28x = var28 < 0L ? -1 : 1;
               float var30x = (var26 == 1 ? 1.05F : 0.85F) * var17 * var27;
               this.D(
                  var18 * (1.0F + var25x),
                  var19 * (1.0F + var25x) + var16x * (0.27F * var26 - 0.27F),
                  var20 * (1.0F + var25x),
                  0.0F,
                  var30x,
                  0.0F,
                  0.0F,
                  var10 * (var26 == 1 ? 1.0F : 0.8F),
                  0.0F,
                  this.g(var2, 0.15F + 0.3F * var26, var8),
                  var6,
                  var3
               );
            }

            return 3;
         }
      }
   }

   private int I(AuraGeometry.Listener var1, long var2, AuraGeometry.MeshVertex var4, AuraGeometry.VertexEmitter var5) {
      long var6 = 60827205411640L;
      long var8 = 36243701690994L;
      long var10 = 119501985133691L;
      float var12 = smooth(var4.n);
      AuraStyle.ensureSourceClassInitialized();
      float var13 = r(var12) * (1.0F + 0.18F * var4.k);
      float var14 = (float)Math.pow(var12, 1.2) * (1.0F + 0.45F * var4.k);
      float var15 = (1.0F - var12) * (1.0F - var12) * 3.2F;
      float var16 = var1.height();
      float var17 = var1.width() * var4.T;
      float var18 = Math.max(var4.y, 0.05F);
      int var19 = 0;

      for (int var20 = 0; var20 < 3; var20++) {
         float var21 = (float)Math.toRadians(17 + 9 * var20);
         float var22 = var20 == 1 ? -0.19F : 0.23F;
         short var23 = 5969;
         float var24 = 0.76F * (0.97F + 0.05F * var20) * var17 * var13;
         float var25 = var16 * (0.5F + 0.065F * (var20 - 1));
         double var26 = 2.3F * var4.b + var20 * (float) (Math.PI * 2) / 3.0F + var15;
         double var28 = var20 * (float) (Math.PI * 2) / 3.0F + var22 * var4.b;
         float var30 = var20 * 0.16F;
         byte var31 = 26;

         for (int var32 = 0; var32 <= var31; var32++) {
            long var33 = 63964312309323L;
            int var35 = var33 < 0L ? -1 : 1;
            float var36 = 2.5F * var32 / var31;
            float var37 = var36 / (2.3F * var18);
            var1.feet(var37 * 0.55F, this.N);
            double var38 = var26 - var36;
            double var40 = var28 - var22 * var37 * var18;
            this.a(var38, var40, var21, var24, var32);
            this.Z[var32] = this.Z[var32] + this.N[0];
            this.U[var32] = this.U[var32] + (this.N[1] + var25);
            this.h[var32] = this.h[var32] + this.N[2];
         }

         long var51 = 63964312309324L;
         int var52 = var51 < 0L ? -1 : 1;

         for (int var34x = 0; var34x < var31; var34x++) {
            float var35 = (float)var34x / var31;
            float var53 = (float)(var34x + 1) / var31;
            float var55 = K(var35) * var14;
            float var56 = K(var53) * var14;
            long var39 = 63964312309323L;
            int var41 = var39 < 0L ? -1 : 1;
            long var41x = 63964312309323L;
            int var44 = var41x < 0L ? -1 : 1;
            if (var55 < 0.004F) {
               break;
            }

            float var44x = 0.046F * var4.T * (float)Math.pow(1.0F - var35, 1.25);
            float var45 = 0.046F * var4.T * (float)Math.pow(1.0F - var53, 1.25);
            float var46 = 0.36F * var4.T * (0.25F + 0.75F * (1.0F - var35));
            float var47 = 0.36F * var4.T * (0.25F + 0.75F * (1.0F - var53));
            int var48 = this.g(var4, var30 + 0.55F * var35, var10);
            int var49 = this.g(var4, var30 + 0.55F * var53, var10);
            this.L(
               this.Z[var34x],
               this.U[var34x],
               this.h[var34x],
               var8,
               this.Z[var34x + 1],
               this.U[var34x + 1],
               this.h[var34x + 1],
               var44x,
               var45,
               var46,
               var47,
               var55,
               var56,
               0.35F * (1.0F - var35),
               0.35F * (1.0F - var53),
               var48,
               var49,
               var5
            );
            var19++;
         }

         long var52x = 63964312309323L;
         int var57 = var52x < 0L ? -1 : 1;
         float var54 = Math.min(var14, 1.6F);
         this.D(
            this.Z[0],
            this.U[0],
            this.h[0],
            0.064F * var4.T * var4.F,
            0.52F * var4.T * var4.F,
            0.5F,
            0.32F,
            var54,
            0.38F + 0.3F * var4.k,
            this.g(var4, var30, var10),
            var6,
            var5
         );
         var19++;
      }

      long var50 = 63964312309323L;
      int var50x = var50 < 0L ? -1 : 1;
      return var19;
   }

   private void a(double var1, double var3, float var5, float var6, int var7) {
      float var8 = (float)Math.cos(var1);
      float var9 = (float)Math.sin(var1);
      float var10 = (float)Math.cos(var3);
      float var11 = (float)Math.sin(var3);
      float var12 = (float)Math.cos(var5);
      float var13 = (float)Math.sin(var5);
      float var14 = var8 * var10 + var9 * var11;
      float var15 = var11 * var8 - var10 * var9;
      this.Z[var7] = (var8 * var12 + var10 * var14 * (1.0F - var12)) * var6;
      this.U[var7] = var15 * var13 * var6;
      this.h[var7] = (var9 * var12 + var11 * var14 * (1.0F - var12)) * var6;
   }

   private static float K(float var0) {
      return (float)Math.pow(1.0F - var0, 1.6F);
   }

   private int u(AuraGeometry.Listener var1, AuraGeometry.MeshVertex var2, AuraGeometry.VertexEmitter var3, long var4) {
      long var6 = 60827205411640L;
      long var8 = 119501985133691L;
      long var10 = 72262074415740L;
      float var12 = smooth(var2.n);
      float var13 = r(var12) * (1.0F + 0.14F * var2.k);
      AuraStyle.ensureSourceClassInitialized();
      float var14 = (float)Math.pow(var12, 1.2) * (1.0F + 0.5F * var2.k);
      float var15 = var1.height();
      float var16 = var1.width() * var2.T;
      int var17 = 0;

      for (int var18 = 0; var18 < 28; var18++) {
         float var19 = 3.4F + 2.4F * v(var18, 1, var10);
         double var20 = var2.b / var19 + v(var18, 2, var10);
         int var22 = (int)Math.floor(var20);
         float var23 = (float)(var20 - var22);
         int var24 = var18 * 7919 + var22 * 104729;
         float var25 = (float) (Math.PI * 2) * v(var24, 3, var10);
         float var26 = (0.44F + 0.5F * v(var24, 4, var10)) * var16 * var13;
         float var27 = var15 * (0.02F + 0.9F * v(var24, 5, var10));
         long var28 = 98990464759581L;
         int var31 = var28 < 0L ? -1 : 1;
         long var31x = 98990464759581L;
         int var33 = var31x < 0L ? -1 : 1;
         float var33x = (v(var24, 6, var10) < 0.5F ? -1 : 1) * (0.2F + 0.24F * v(var24, 7, var10));
         float var34 = 0.05F + 0.1F * v(var24, 8, var10);
         float var35 = var23 * var19;
         float var36 = var25 + var33x * var35 + (1.0F - var12) * (1.0F - var12) * 2.4F;
         float var37 = var26 * (1.0F + 0.08F * (float)Math.sin(var35 * 0.9F + var25));
         float var38 = var37 * (float)Math.cos(var36) + 0.035F * (float)Math.sin(var35 * 1.9F + v(var24, 9, var10) * (float) (Math.PI * 2));
         float var39 = var37 * (float)Math.sin(var36) + 0.035F * (float)Math.cos(var35 * 1.6F + v(var24, 10, var10) * (float) (Math.PI * 2));
         float var40 = var27 + var34 * var35 + 0.03F * (float)Math.sin(var35 * 2.3F + v(var24, 11, var10) * (float) (Math.PI * 2));
         var1.feet(0.04F + 0.26F * v(var18, 12, var10), this.N);
         float var41 = i(0.0F, 0.2F, var23) * (1.0F - i(0.58F, 1.0F, var23));
         double var42 = var2.b;
         float var44 = 0.66F + 0.34F * (0.5F + 0.5F * (float)Math.sin(var42 * (1.7 + 2.6 * v(var18, 13, var10)) + v(var18, 14, var10) * (float) (Math.PI * 2)));
         float var45 = (float)Math.pow(Math.max(0.0, Math.sin(var42 * (0.42 + 0.3 * v(var18, 15, var10)) + v(var18, 16, var10) * (float) (Math.PI * 2))), 28.0);
         float var46 = var41 * var14 * (var44 + 0.55F * var45);
         long var47 = 98990464759580L;
         int var55 = var47 < 0L ? -1 : 1;
         if (var46 < 0.004F) {
            long var49 = 98990464759581L;
            int var51 = var49 < 0L ? -1 : 1;
         } else {
            float var55x = (0.75F + 0.6F * v(var18, 17, var10)) * var2.T * var2.F;
            float var50 = v(var18, 18, var10);
            long var51 = 98990464759580L;
            int var54 = var51 < 0L ? -1 : 1;
            var54 = this.g(var2, var50 < 0.78F ? var50 * 0.7F : 0.82F + 0.18F * v(var18, 19, var10), var8);
            this.D(
               this.N[0] + var38,
               this.N[1] + var40,
               this.N[2] + var39,
               0.027F * var55x,
               0.21F * var55x * (1.0F + 0.35F * var45),
               0.7F * var45,
               0.2F,
               var46,
               0.16F + 0.5F * var45,
               var54,
               var6,
               var3
            );
            var17++;
         }
      }

      return var17;
   }

   private int p(long var1, AuraGeometry.Listener var3, AuraGeometry.MeshVertex var4, AuraGeometry.VertexEmitter var5) {
      long var6 = 60827205411640L;
      long var8 = 36243701690994L;
      long var10 = 119501985133691L;
      float var12 = smooth(var4.n);
      float var13 = r(var12) * (1.0F + 0.16F * var4.k);
      float var14 = (float)Math.pow(var12, 1.2) * (1.0F + 0.45F * var4.k);
      float var15 = var3.height();
      AuraStyle.ensureSourceClassInitialized();
      float var16 = var3.width() * var4.T;
      float var17 = Math.max(var4.y, 0.05F);
      float var18 = (1.0F - var12) * (1.0F - var12) * 2.6F;
      int var19 = 0;

      for (int var20 = 0; var20 < 3; var20++) {
         double var21 = var4.b / 2.6F + var20 / 3.0;
         int var23 = (int)Math.floor(var21);
         float var24 = (float)(var21 - var23);
         float var25 = var23 * 2.3999631F + var20 * (float) (Math.PI * 2) / 3.0F + var18;
         float var26 = Math.min(0.46F, var24);
         int var27 = Math.max(2, Math.round(28.0F * var26 / 0.46F));

         for (int var28 = 0; var28 <= var27; var28++) {
            float var29 = var24 - var26 * var28 / var27;
            var3.feet((var24 - var29) * 2.6F / var17 * 0.55F, this.N);
            float var30 = var25 + 10.681416F * var29;
            float var31 = 0.64F * (1.0F - 0.32F * var29) * var16 * var13;
            this.Z[var28] = this.N[0] + var31 * (float)Math.cos(var30);
            this.U[var28] = this.N[1] + var15 * (-0.04F + 1.16F * var29);
            this.h[var28] = this.N[2] + var31 * (float)Math.sin(var30);
            long var32 = 81905739425205L;
            int var35 = var32 < 0L ? -1 : 1;
         }

         long var49 = 81905739425205L;
         int var481 = var49 < 0L ? -1 : 1;
         float var50 = i(0.0F, 0.12F, var24) * (1.0F - i(0.7F, 1.0F, var24)) * var14;
         if (var50 < 0.004F) {
            long var53 = 81905739425205L;
            int var55 = var53 < 0L ? -1 : 1;
         } else {
            for (int var51 = 0; var51 < var27; var51++) {
               float var33 = var26 * var51 / var27 / 0.46F;
               float var34 = var26 * (var51 + 1) / var27 / 0.46F;
               float var35 = var24 - var26 * var51 / var27;
               float var36 = var24 - var26 * (var51 + 1) / var27;
               float var37 = K(var33) * var50;
               float var38 = K(var34) * var50;
               float var39 = 0.04F * var4.T * (float)Math.pow(1.0F - var33, 1.2F) * (1.0F - 0.3F * var35);
               float var40 = 0.04F * var4.T * (float)Math.pow(1.0F - var34, 1.2F) * (1.0F - 0.3F * var36);
               float var41 = 0.32F * var4.T * (0.3F + 0.7F * (1.0F - var33));
               float var42 = 0.32F * var4.T * (0.3F + 0.7F * (1.0F - var34));
               long var43 = 81905739425204L;
               int var46 = var43 < 0L ? -1 : 1;
               var46 = this.g(var4, 0.08F + 0.75F * var35 + 0.12F * var20, var10);
               int var47 = this.g(var4, 0.08F + 0.75F * var36 + 0.12F * var20, var10);
               this.L(
                  this.Z[var51],
                  this.U[var51],
                  this.h[var51],
                  var8,
                  this.Z[var51 + 1],
                  this.U[var51 + 1],
                  this.h[var51 + 1],
                  var39,
                  var40,
                  var41,
                  var42,
                  var37,
                  var38,
                  0.32F * (1.0F - var33),
                  0.32F * (1.0F - var34),
                  var46,
                  var47,
                  var5
               );
               var19++;
            }

            long var52 = 81905739425205L;
            int var54 = var52 < 0L ? -1 : 1;
            this.D(
               this.Z[0],
               this.U[0],
               this.h[0],
               0.058F * var4.T * var4.F * (1.0F - 0.25F * var24),
               0.46F * var4.T * var4.F,
               0.32F,
               0.32F,
               var50,
               0.38F + 0.3F * var4.k,
               this.g(var4, 0.08F + 0.75F * var24 + 0.12F * var20, var10),
               var6,
               var5
            );
            var19++;
         }
      }

      long var48 = 81905739425205L;
      int var22 = var48 < 0L ? -1 : 1;
      return var19;
   }

   private int B(AuraGeometry.Listener var1, long var2, AuraGeometry.MeshVertex var4, AuraGeometry.VertexEmitter var5) {
      long var6 = 60827205411640L;
      long var8 = 36243701690994L;
      long var10 = 119501985133691L;
      long var12 = 72262074415740L;
      float var14 = smooth(var4.n);
      float var15 = r(var14) * (1.0F + 0.12F * var4.k);
      float var16 = (float)Math.pow(var14, 1.2) * (1.0F + 0.5F * var4.k);
      float var17 = var1.height();
      float var18 = var1.width() * var4.T;
      AuraStyle.ensureSourceClassInitialized();
      double var19 = 0.28F * var4.b + (1.0F - var14) * (1.0F - var14) * 2.2F;
      int var21 = 0;

      for (int var22 = 0; var22 < V.length; var22++) {
         float[] var23 = V[var22];
         long var24 = 72532745920026L;
         int var27 = var24 < 0L ? -1 : 1;
         float var27x = var23[1];
         var1.feet(0.05F + 0.12F * var27x, this.N);
         double var28 = Math.toRadians(var23[0]) + var19;
         float var30 = 0.86F * var23[2] * var18 * var15;
         float var31 = 0.035F * (float)Math.sin(var4.b * 0.9 + var22 * 1.7);
         this.z[var22] = this.N[0] + var30 * (float)Math.cos(var28);
         this.a[var22] = this.N[1] + var17 * var27x + var31;
         this.C[var22] = this.N[2] + var30 * (float)Math.sin(var28);
         float var32 = 0.72F + 0.28F * (float)Math.sin(var4.b * (1.2 + 1.6 * v(var22, 31, var12)) + var22 * 2.1);
         float var33 = i(0.08F * (var22 % 5), 0.6F + 0.08F * (var22 % 5), var14);
         this.k[var22] = var33 * var16;
         long var34 = 72532745920027L;
         int var37 = var34 < 0L ? -1 : 1;
         this.S[var22] = this.g(var4, 0.05F + 0.35F * v(var22, 32, var12) + (var23[3] > 1.2F ? 0.5F : 0.0F), var10);
         float var37x = this.k[var22] * var32;
         long var38 = 72532745920026L;
         int var38x = var38 < 0L ? -1 : 1;
         if (var37x < 0.004F) {
            long var41 = 72532745920027L;
            int var64 = var41 < 0L ? -1 : 1;
         } else {
            float var62x = var23[3] * var4.T * var4.F;
            float var42 = this.z[var22];
            float var43 = this.a[var22];
            float var44 = this.C[var22];
            float var45 = 0.033F * var62x;
            long var46 = 72532745920027L;
            int var45x = var46 < 0L ? -1 : 1;
            this.D(
               var42,
               var43,
               var44,
               var45,
               0.3F * var62x,
               var23[3] >= 0.85F ? 0.95F : 0.6F,
               (v(var22, 33, var12) - 0.5F) * 0.5F,
               var37x,
               0.5F + 0.25F * var4.k,
               this.S[var22],
               var6,
               var5
            );
            var21++;
         }
      }

      long var53 = 72532745920027L;
      int var25 = var53 < 0L ? -1 : 1;
      var25 = 0;

      while (true) {
         long var26 = 72532745920026L;
         int var29 = var26 < 0L ? -1 : 1;
         if (var25 >= Q.length) {
            long var55 = 72532745920027L;
            int var601 = var55 < 0L ? -1 : 1;
            return var21;
         }

         var29 = Q[var25][0];
         int var56 = Q[var25][1];
         float var57 = this.z[var56] - this.z[var29];
         float var58 = this.a[var56] - this.a[var29];
         float var59 = this.C[var56] - this.C[var29];
         float var60 = (float)Math.sqrt(var57 * var57 + var58 * var58 + var59 * var59);
         float var35 = 0.075F * var4.T;
         long var36 = 72532745920027L;
         int var661 = var36 < 0L ? -1 : 1;
         float var61 = var35 * 2.0F + 0.02F;
         long var39 = 72532745920026L;
         int var70 = var39 < 0L ? -1 : 1;
         if (!(var60 < var61)) {
            float var63 = i(0.35F + 0.05F * var25, 0.95F, var14);
            long var64 = 72532745920027L;
            int var74 = var64 < 0L ? -1 : 1;
            float var65 = var35 / var60;
            float var66x = var65 + (1.0F - 2.0F * var65) * var63;
            float var47 = 0.75F * Math.min(this.k[var29], this.k[var56]) * (0.85F + 0.15F * (float)Math.sin(var4.b * 0.7 + var25));
            long var48 = 72532745920026L;
            int var47x = var48 < 0L ? -1 : 1;
            if (!(var63 <= 0.0F)) {
               float var50x;
               int var54 = (var50x = var47 - 0.004F) == 0.0F ? 0 : (var50x < 0.0F ? -1 : 1);
               long var51 = 72532745920026L;
               int var51x = var51 < 0L ? -1 : 1;
               if (var54 >= 0) {
                  this.L(
                     this.z[var29] + var57 * var65,
                     this.a[var29] + var58 * var65,
                     this.C[var29] + var59 * var65,
                     var8,
                     this.z[var29] + var57 * var66x,
                     this.a[var29] + var58 * var66x,
                     this.C[var29] + var59 * var66x,
                     0.0105F * var4.T * var4.F,
                     0.0105F * var4.T * var4.F,
                     0.13F * var4.T,
                     0.13F * var4.T,
                     var47,
                     var47,
                     0.22F,
                     0.22F,
                     this.S[var29],
                     this.S[var56],
                     var5
                  );
                  var21++;
               }
            }
         }

         var25++;
      }
   }

   private static float r(float var0) {
      float var1 = 1.0F - var0;
      return 1.0F + 1.5F * var1 * var1;
   }

   private int g(AuraGeometry.MeshVertex var1, float var2, long var3) {
      long var5 = 30531801112562L;
      AuraStyle.ensureSourceClassInitialized();
      float var7 = var2 % 2.0F;
      if (var7 < 0.0F) {
         var7 += 2.0F;
      }

      if (var7 > 1.0F) {
         var7 = 2.0F - var7;
      }

      int[] var8 = var1.d;
      int var9 = V(var8[0], var8[1], var5, i(0.0F, 0.55F, var7));
      return V(var9, var8[2], var5, i(0.5F, 1.0F, var7));
   }

   private void L(
      float var1,
      float var2,
      float var3,
      long var4,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12,
      float var13,
      float var14,
      float var15,
      float var16,
      int var17,
      int var18,
      AuraGeometry.VertexEmitter var19
   ) {
      AuraStyle.ensureSourceClassInitialized();
      float[] var20 = this.m;
      var20[0] = var1;
      var20[1] = var2;
      var20[2] = var3;
      var20[3] = 0.0F;
      var20[4] = var6;
      var20[5] = var7;
      var20[6] = var8;
      var20[7] = 0.0F;
      var20[8] = var9;
      var20[9] = var10;
      var20[10] = Math.max(var11, var9);
      var20[11] = Math.max(var12, var10);
      var20[12] = var13;
      var20[13] = var14;
      var20[14] = var15;
      var20[15] = var16;

      for (int var21 = 0; var21 < 4; var21++) {
         var19.vertex(var20, var17, var18);
      }

      long var24 = 36243701690993L;
      int var23 = var24 < 0L ? -1 : 1;
   }

   private void D(
      float var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      int var10,
      long var11,
      AuraGeometry.VertexEmitter var13
   ) {
      float[] var14 = this.m;
      var14[0] = var1;
      var14[1] = var2;
      var14[2] = var3;
      var14[3] = 1.0F;
      var14[4] = var4;
      var14[5] = Math.max(var5, var4);
      var14[6] = var6;
      var14[7] = var7;
      var14[8] = 0.0F;
      var14[9] = 0.0F;
      var14[10] = 0.0F;
      AuraStyle.ensureSourceClassInitialized();
      var14[11] = 0.0F;
      var14[12] = var8;
      var14[13] = var9;
      var14[14] = 0.0F;
      var14[15] = 0.0F;

      for (int var15 = 0; var15 < 4; var15++) {
         var13.vertex(var14, var10, var10);
      }

      long var18 = 60827205411639L;
      int var17 = var18 < 0L ? -1 : 1;
   }

   private static int V(int var0, int var1, long var2, float var4) {
      int var5 = Math.round((var0 >> 16 & 0xFF) + ((var1 >> 16 & 0xFF) - (var0 >> 16 & 0xFF)) * var4);
      int var6 = Math.round((var0 >> 8 & 0xFF) + ((var1 >> 8 & 0xFF) - (var0 >> 8 & 0xFF)) * var4);
      int var7 = Math.round((var0 & 0xFF) + ((var1 & 0xFF) - (var0 & 0xFF)) * var4);
      return var5 << 16 | var6 << 8 | var7;
   }

   static float smooth(float var0) {
      var0 = Math.clamp(var0, 0.0F, 1.0F);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static float i(float var0, float var1, float var2) {
      return smooth((var2 - var0) / (var1 - var0));
   }

   private static float v(int var0, int var1, long var2) {
      int var4 = var0 * -1640531535 + var1 * -2048144777 + 374761393;
      var4 ^= var4 >>> 15;
      var4 *= 739982445;
      var4 ^= var4 >>> 12;
      var4 *= 695872825;
      var4 ^= var4 >>> 15;
      return (var4 >>> 8) / 1.6777216E7F;
   }

   interface Listener {
      void feet(float var1, float[] var2);

      float height();

      float width();
   }

   interface VertexEmitter {
      void vertex(float[] var1, int var2, int var3);
   }

   static final class MeshVertex {
      AuraStyle f = AuraStyle.J;
      float T = 1.0F;
      float y = 1.0F;
      double b;
      float n = 1.0F;
      float k;
      final int[] d = new int[3];
      float F = 1.0F;
      float v = 1.0F;
   }

   static class MeshBuffer {
      static final int[] a = new int[AuraStyle.values().length];

      static {
         try {
            a[AuraStyle.J.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
         }

         try {
            a[AuraStyle.L.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
         }

         try {
            a[AuraStyle.v.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
         }

         try {
            a[AuraStyle.G.ordinal()] = 4;
         } catch (NoSuchFieldError var1) {
         }
      }
   }
}
