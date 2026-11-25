package dev.purxiy.modules.halo;

final class HaloBuilder {
   static final float d = 5.2F;
   private static final float o = 2.6F;
   private static final float I = (float) (Math.PI * 2);
   private static final int T = -2068;
   private static final int i = -15922666;
   private static final int N = -19833;
   private HaloBatch D;
   private HaloBuilder.HaloBatchState r;
   private HaloBuilder.HaloSegments z;
   private int c;
   private int n;
   private int w;
   private int k;
   private int B;
   private float L;
   private float x;
   private float G;
   private float Y;
   private float J;
   private float W;
   private float p;
   private float j;
   private float R;
   private float E;
   private float P;
   private float H;
   private float f;
   private int O;
   private int b;
   private static final long[] a = new long[]{
      -4582547858904045865L,
      -2125883973577655916L,
      -7282427518345733971L,
      29330240190041151L,
      -7090576589187428610L,
      -6948750825785807640L,
      -5310189347739141539L,
      -1609234097125990686L,
      6179949354117632078L,
      -6144181453020470890L,
      5252127074455113313L,
      -7190094531169579882L,
      -5526344396978217854L,
      6310059523731386963L,
      -7682693619621203279L,
      -9042961026265011887L,
      4762380962395833593L,
      7667554286550990400L,
      -3320675203855355058L,
      -8790784865213667180L,
      -1696053822507471118L,
      3178052114064034351L,
      -5184483666528786959L,
      -6599252175477676722L,
      -3436550749824318164L
   };

   void build(HaloBatch var1, HaloDesign var2, HaloBuilder.HaloBatchState var3, HaloBuilder.HaloSegments var4) {
      long var5 = 24564481230309L;
      long var7 = var5 ^ 47048085787229L;
      long var9 = var5 ^ 93033922812828L;
      long var11 = var5 ^ 1064444413051L;
      long var13 = var5 ^ 7962712657066L;
      long var15 = var5 ^ 40261286097259L;
      int var17 = (int)((var5 ^ 40261286097259L) >>> 48);
      int var18 = (int)((var5 ^ 40261286097259L) << 16 >>> 48);
      int var19 = (int)(var15 << 32 >>> 32);
      long var20 = var5 ^ 50133300125261L;
      this.D = var1;
      this.r = var3;
      this.z = var4;
      var1.clear();
      if (!(var4.T <= 0.002F)) {
         int var22 = var4.H | 0xFF000000;
         this.c = HaloBuilder.HaloVertex.lerp(var22, -2068, 0.52F);
         this.n = HaloBuilder.HaloVertex.lerp(HaloBuilder.HaloVertex.saturate(var22, 0.12F), -2068, 0.12F);
         this.w = HaloBuilder.HaloVertex.lerp(HaloBuilder.HaloVertex.hue(var22, 0.075F), -2068, 0.45F);
         this.k = HaloBuilder.HaloVertex.lerp(var22, -19833, 0.55F);
         this.B = HaloBuilder.HaloVertex.lerp(-15922666, var22, 0.13F);
         this.G = var4.N * var4.N;
         this.L = (0.32F + 0.68F * var4.c) * (1.0F + 0.45F * this.G);
         this.x = 2.6F * (0.42F + 0.7F * var4.c) * (1.0F + 0.25F * this.G);
         float var23 = 5.2F * var4.u;
         switch (HaloBuilder.HaloStrip.z[var2.ordinal()]) {
            case 1:
               this.s((char)var17, (short)var18, var23, var19);
               break;
            case 2:
               this.M(var20, var23);
               break;
            case 3:
               this.k(var9, var23);
               break;
            case 4:
               this.G(var23, var11);
               break;
            case 5:
               this.L(var7, var23);
               break;
            case 6:
               this.F(var23, var13);
         }
      }
   }

   private void s(char var1, short var2, float var3, int var4) {
      long var5 = 55840254620814L;
      long var7 = var5 ^ 100708112667703L;
      float var9 = this.z.J;
      float var10 = 0.93F + 0.07F * (float)Math.sin(var9 * 1.25F);
      float var11 = 0.3F * this.z.u;
      this.X(
         var3,
         0.0F,
         72,
         (var4x, var5x) -> {
            long var6 = 284791600568L;
            short var8 = 220;
            float var9x = K(var4x, var9 * 1.05F) + 0.55F * K(var4x, -var9 * 0.66F + 2.2F) + this.j(var6, (byte)var8, var4x) + var5x;
            float var10x = 0.5F + 0.5F * (float)Math.sin(var4x + var9 * 0.35F);
            int var11x = HaloBuilder.HaloVertex.lerp(this.c, this.w, 0.28F * var10x);
            this.c(
               var11 * (1.0F + 0.12F * var9x),
               this.x * (1.0F + 0.3F * var9x),
               0.95F + 0.45F * var9x,
               this.L * 0.62F * var10 * (0.9F + 0.6F * var9x),
               1.0F,
               0.55F + 0.35F * Math.min(var9x, 1.0F),
               var11x,
               HaloBuilder.HaloVertex.lerp(this.n, this.w, 0.3F * var10x)
            );
         },
         var7
      );
   }

   private void M(long var1, float var3) {
      long var4 = 134694533516808L;
      byte var6 = 0;
      int var7 = 2055275474;
      int var8 = (int)(var4 << 48 >>> 48);
      long var9 = 75057392370975L;
      long var11 = 75080486235813L;
      long var13 = 115855631474873L;
      HaloDesign.ensureSourceClassInitialized();
      float var15 = this.z.J;
      float var16 = this.z.u;
      this.X(
         var3 * 0.74F,
         0.0F,
         64,
         (var3x, var4x) -> {
            long var5 = 39091391363915L;
            long var7x = (var5 ^ 107491397157783L) >>> 8;
            int var9x = (int)((var5 ^ 107491397157783L) << 56 >>> 56);
            float var10 = K(var3x, -var15 * 1.4F) + this.j(var7x, (byte)var9x, var3x) + var4x;
            this.c(
               0.17F * var16 * (1.0F + 0.2F * var10),
               this.x * 0.75F * (1.0F + 0.4F * var10),
               0.82F + 0.5F * var10,
               this.L * 0.42F * (0.75F + 0.8F * var10),
               1.0F,
               0.45F + 0.4F * Math.min(var10, 1.0F),
               this.c,
               this.n
            );
         },
         var13
      );
      float var17 = var15 * 0.32F;

      for (int var18 = 0; var18 < 4; var18++) {
         float var19 = var18 * (float) (Math.PI * 2) / 4.0F + var17;
         float var20 = 0.5F + 0.5F * (float)Math.sin(var15 * 1.7F - var18 * (float) (Math.PI * 2) / 4.0F);
         float var21 = 0.56F;
         int var22 = var18;
         this.Y(
            var3,
            0.0F,
            var19 - var21,
            var19 + var21,
            14,
            (char)var6,
            (var5, var6x) -> {
               long var7x = 284791600568L;
               short var9x = 220;
               float var10 = Math.min(var6x, 1.0F - var6x);
               float var11x = 0.45F + 0.55F * smooth(0.0F, 0.2F, var10);
               float var12 = this.j(var7x, (byte)var9x, var5) + 0.6F * K(var5, var19 + (var20 - 0.5F) * 0.6F) * var20;
               this.c(
                  0.27F * var16 * var11x * (1.0F + 0.15F * var12),
                  this.x * (1.0F + 0.35F * var12),
                  (0.88F + 0.32F * var20 + 0.4F * var12) * (0.75F + 0.25F * var11x),
                  this.L * 0.6F * (0.8F + 0.45F * var20 + 0.6F * var12),
                  1.0F,
                  0.5F + 0.3F * var20,
                  HaloBuilder.HaloVertex.lerp(this.c, this.w, 0.18F * (var22 % 2)),
                  this.n
               );
            },
            var7,
            var8
         );
         float var23 = var19 + (float) (Math.PI / 4);
         float var24 = (float)Math.pow(0.5 + 0.5 * Math.sin(var15 * 2.2F - var18 * (float) (Math.PI * 2) / 4.0F - (float) (Math.PI / 4)), 4.0);
         float var25 = this.z(var11, var23 + this.r.a);
         long var26 = 65763677707176L;
         int var28 = var26 < 0L ? -1 : 1;
         if (var25 > 0.01F) {
            this.N(
               var3,
               0.0F,
               var23,
               var9,
               0.3F * var16 * (0.8F + 0.4F * var24),
               this.x * 0.8F * (1.0F + var24),
               (1.0F + 0.8F * var24) * var25,
               this.L * 0.7F * (0.6F + var24) * var25,
               0.75F + 0.25F * var24,
               this.c,
               this.n,
               0.35F + 0.55F * var24
            );
         }
      }
   }

   private void k(long var1, float var3) {
      long var4 = 284791600568L;
      short var6 = 220;
      long var7 = 75057392370975L;
      long var9 = 75080486235813L;
      long var11 = 115855631474873L;
      float var13 = this.z.J;
      float var14 = this.z.u;
      this.X(
         var3,
         0.0F,
         72,
         (var3x, var4x) -> {
            long var5 = 284791600568L;
            short var7x = 220;
            float var8 = t(var3x, var13);
            float var9x = 0.6F * var8 + this.j(var5, (byte)var7x, var3x) + var4x;
            this.c(
               0.28F * var14 * (1.0F + 0.12F * var9x),
               this.x * (1.0F + 0.4F * var9x),
               0.92F + 0.5F * var9x,
               this.L * 0.55F * (0.85F + 0.8F * var9x),
               1.0F,
               0.5F + 0.35F * Math.min(var9x, 1.0F),
               this.c,
               this.n
            );
         },
         var11
      );
      HaloDesign.ensureSourceClassInitialized();
      byte var15 = 10;

      for (int var16 = 0; var16 < var15; var16++) {
         float var17 = var16 * (float) (Math.PI * 2) / var15;
         float var18 = this.z(var9, var17 + this.r.a);
         if (var18 <= 0.01F) {
            long var33 = 73437377093241L;
            int var341 = var33 < 0L ? -1 : 1;
         } else {
            long var19 = 73437377093240L;
            int var22 = var19 < 0L ? -1 : 1;
            boolean var22x = var16 % 2 == 0;
            float var23 = (var22x ? 2.6F : 1.55F) * var14 * smooth(0.0F, 1.0F, var18);
            float var24 = t(var17, var13);
            float var25 = (0.85F + 0.6F * var24 + this.j(var4, (byte)var6, var17)) * var18;
            if (!this.D.begin(0, false, 0.0F)) {
               return;
            }

            for (int var26 = 0; var26 <= 4; var26++) {
               float var27 = var26 / 4.0F;
               float var28 = var3 + 0.25F * var14 * var27 * var27;
               float var29 = var25 * (1.0F - 0.35F * var27) + 0.5F * var24 * (float)Math.exp(-(var27 - 0.85F) * (var27 - 0.85F) / 0.04F) * var18;
               this.d(var28, var23 * var27, var17 + this.r.a);
               long var30 = 73437377093241L;
               int var31 = var30 < 0L ? -1 : 1;
               this.E(
                  0.3F * var14 * (1.0F - 0.9F * (float)Math.pow(var27, 0.9F)),
                  this.x * 0.75F * (0.7F + 0.5F * var24),
                  var29,
                  this.L * 0.7F * var29,
                  1.0F,
                  0.55F + 0.35F * var24,
                  HaloBuilder.HaloVertex.lerp(this.c, this.w, 0.25F * var27),
                  this.n
               );
            }

            long var34 = 73437377093240L;
            int var36 = var34 < 0L ? -1 : 1;
            this.D.end();
            long var35 = 73437377093241L;
            int var38 = var35 < 0L ? -1 : 1;
            if (var22x) {
               this.N(
                  var3 + 0.25F * var14,
                  var23 + 0.15F * var14,
                  var17,
                  var7,
                  0.15F * var14 * (0.7F + 0.6F * var24),
                  this.x * 0.45F,
                  (0.6F + 0.8F * var24) * var18,
                  this.L * 0.45F * (0.4F + var24) * var18,
                  0.6F,
                  this.c,
                  this.n,
                  0.15F + 0.6F * var24
               );
            }
         }
      }
   }

   private void G(float var1, long var2) {
      long var4 = 104773792173911L;
      long var6 = 284791600568L;
      short var8 = 220;
      long var9 = 75057392370975L;
      long var11 = 75080486235813L;
      long var13 = 115855631474873L;
      HaloDesign.ensureSourceClassInitialized();
      float var15 = this.z.J;
      float var16 = this.z.u;
      this.X(
         var1,
         0.0F,
         64,
         (var3, var4x) -> {
            long var5 = 284791600568L;
            short var7 = 220;
            float var8x = 0.5F * K(var3, var15 * 0.5F) + this.j(var5, (byte)var7, var3) + var4x;
            this.c(
               0.12F * var16 * (1.0F + 0.3F * var8x),
               this.x * 0.8F * (1.0F + 0.4F * var8x),
               0.55F + 0.45F * var8x,
               this.L * 0.34F * (0.8F + 0.8F * var8x),
               0.8F,
               0.4F + 0.3F * Math.min(var8x, 1.0F),
               this.c,
               this.n
            );
         },
         var13
      );

      for (int var17 = 0; var17 < 26; var17++) {
         float var18 = T(var17, var4, 1) * (float) (Math.PI * 2) + var15 * (0.16F + 0.3F * T(var17, var4, 2)) + this.r.a;
         float var19 = this.z(var11, var18);
         long var20 = 24878748615069L;
         int var54 = var20 < 0L ? -1 : 1;
         if (var19 <= 0.01F) {
            long var23 = 24878748615069L;
            int var25 = var23 < 0L ? -1 : 1;
         } else {
            float var54x = var1 * (0.88F + 0.24F * T(var17, var4, 3));
            float var24 = (T(var17, var4, 4) - 0.5F) * 1.5F * var16;
            float var25 = (float)Math.pow(0.5 + 0.5 * Math.sin(var15 * (1.1F + 2.4F * T(var17, var4, 5)) + T(var17, var4, 6) * (float) (Math.PI * 2)), 3.0);
            long var26 = 24878748615070L;
            int var29 = var26 < 0L ? -1 : 1;
            boolean var29x = var17 % 6 == 0;
            long var30 = 24878748615070L;
            int var30x = var30 < 0L ? -1 : 1;
            float var32x = (0.15F + 0.22F * T(var17, var4, 7)) * var16 * (var29x ? 1.35F : 1.0F);
            long var33 = 24878748615069L;
            int var33x = var33 < 0L ? -1 : 1;
            float var35x = (0.55F + 0.85F * var25 + (var29x ? 0.35F : 0.0F) + this.j(var6, (byte)var8, var18)) * var19;
            long var36 = 24878748615069L;
            int var36x = var36 < 0L ? -1 : 1;
            long var38x = 24878748615069L;
            int var41 = var38x < 0L ? -1 : 1;
            var41 = HaloBuilder.HaloVertex.lerp(this.c, T(var17, var4, 8) > 0.5F ? this.w : -2068, 0.35F);
            float var42 = var18 - this.r.a;
            float var43 = var32x * (0.75F + 0.45F * var25);
            float var44 = this.x * 0.55F * (0.7F + 0.6F * var25);
            float var45 = this.L * 0.45F * var35x;
            float var46 = var29x ? 0.25F + 0.75F * var25 : 0.3F * var25;
            int var47 = this.n;
            float var49 = 0.8F;
            this.N(var54x, var24, var42, var9, var43, var44, var35x, var45, var49, var41, var47, var46);
         }
      }
   }

   private void L(long var1, float var3) {
      long var4 = 115855631474873L;
      float var6 = this.z.J;
      float var7 = this.z.u;
      float var8 = 0.12F * var7;
      this.X(var3 - 0.44F * var7, var8, 64, (var3x, var4x) -> {
         long var5 = 284791600568L;
         short var7x = 220;
         float var8x = 0.6F * (K(var3x, var6 * 0.4F + 1.2F) + K(var3x, var6 * 0.4F + 1.2F + (float) Math.PI)) + this.j(var5, (byte)var7x, var3x) + var4x;
         this.c(0.07F * var7, this.x * 0.6F * (1.0F + 0.4F * var8x), 0.6F + 0.6F * var8x, this.L * 0.3F * (0.7F + var8x), 0.9F, 0.5F, this.c, this.n);
      }, var4);
      this.X(var3, 0.0F, 64, (var2, var3x) -> {
         long var4x = 284791600568L;
         short var6x = 220;
         float var7x = this.j(var4x, (byte)var6x, var2) + var3x;
         this.c(0.5F * var7, this.x * 0.5F, 1.0F, this.L * 0.12F * var7x, 1.0F, 0.0F, HaloBuilder.HaloVertex.lerp(this.B, this.c, 0.12F * var7x), this.n);
      }, var4);
      this.X(
         var3 + 0.44F * var7,
         var8,
         72,
         (var3x, var4x) -> {
            long var5 = 284791600568L;
            short var7x = 220;
            float var8x = K(var3x, var6 * 0.4F + 1.2F) + K(var3x, var6 * 0.4F + 1.2F + (float) Math.PI) + this.j(var5, (byte)var7x, var3x) + var4x;
            this.c(
               0.11F * var7 * (1.0F + 0.25F * var8x),
               this.x * 1.25F * (1.0F + 0.6F * var8x),
               0.9F + 0.8F * var8x,
               this.L * 0.8F * (0.75F + 1.1F * var8x),
               1.0F,
               0.6F + 0.3F * Math.min(var8x, 1.0F),
               HaloBuilder.HaloVertex.lerp(this.c, -2068, 0.2F),
               this.n
            );
         },
         var4
      );
   }

   private void F(float var1, long var2) {
      long var4 = 109353405447248L;
      long var6 = 75057392370975L;
      long var8 = 75080486235813L;
      float var10 = this.z.J;
      float var11 = this.z.u;
      HaloDesign.ensureSourceClassInitialized();

      for (int var12 = 0; var12 < 3; var12++) {
         float var13 = 0.5F + 0.5F * (float)Math.sin(var10 * 0.75F + var12 * 2.09F);
         long var14 = 19149841844558L;
         int var32 = var14 < 0L ? -1 : 1;
         float var17 = 0.4F * var11 * var13;
         float var18 = (0.18F * (float)Math.sin(var10 * 0.6F + var12 * 1.7F) - 0.12F) * var11;
         float var19 = 0.08F * (float)Math.sin(var10 * 0.5F + var12 * 2.6F);
         float var20 = var12 * (float) (Math.PI * 2) / 3.0F + 0.1F;
         float var21 = (var12 + 1) * (float) (Math.PI * 2) / 3.0F - 0.1F;
         float var22 = (var20 + var21) / 2.0F;
         this.K(
            var1 + var17,
            var18,
            var20,
            var4,
            var21,
            22,
            (var4x, var5) -> {
               long var6x = 284791600568L;
               short var8x = 220;
               float var9 = Math.min(var5, 1.0F - var5);
               float var10x = 1.0F - smooth(0.0F, 0.12F, var9);
               float var11x = this.j(var6x, (byte)var8x, var4x) + 0.35F * K(var4x, var22 + 0.6F * (float)Math.sin(var10 * 0.4F));
               int var12x = HaloBuilder.HaloVertex.lerp(this.c, HaloBuilder.HaloVertex.lerp(this.k, -2068, 0.3F), var10x);
               this.c(
                  0.3F * var11 * (0.75F + 0.25F * smooth(0.0F, 0.06F, var9)),
                  this.x * (1.0F + 0.35F * var10x + 0.3F * var11x),
                  0.88F + 0.42F * var10x + 0.4F * var11x,
                  this.L * 0.55F * (0.8F + 0.7F * var10x + 0.6F * var11x),
                  1.0F,
                  0.35F + 0.3F * var10x,
                  var12x,
                  HaloBuilder.HaloVertex.lerp(this.n, this.k, 0.6F * var10x)
               );
            },
            (var2x, var3) -> var19 * (var3 - 0.5F) * 2.0F * var11
         );
      }

      long var30 = 19149841844558L;
      int var28 = var30 < 0L ? -1 : 1;

      for (int var16 = 0; var16 < 3; var16++) {
         float var31 = var16 * (float) (Math.PI * 2) / 3.0F;
         float var32 = this.z(var8, var31 + this.r.a);

         for (int var33 = 0; var33 < 2; var33++) {
            float var35 = h(var10 / 1.8F + var33 * 0.5F + var16 * 0.23F);
            long var36 = 19149841844559L;
            int var371 = var36 < 0L ? -1 : 1;
            float var24 = smooth(0.0F, 0.12F, var35) * (float)Math.pow(1.0F - var35, 1.5) * var32;
            long var25 = 19149841844559L;
            int var24x = var25 < 0L ? -1 : 1;
            if (var24 <= 0.01F) {
               long var28x = 19149841844559L;
               int var26 = var28x < 0L ? -1 : 1;
            } else {
               float var37x = (float)Math.sin(var35 * 5.0F + var16 + var33 * 2) * 0.35F * var11;
               this.N(
                  var1 + 0.3F * var11 * var35,
                  (0.2F + 1.5F * var35) * var11,
                  var31 + var37x / var1,
                  var6,
                  0.13F * var11 * (1.0F - 0.6F * var35),
                  this.x * 0.35F,
                  0.95F * var24,
                  this.L * 0.4F * var24,
                  0.7F,
                  HaloBuilder.HaloVertex.lerp(this.k, -2068, 0.35F),
                  this.k,
                  0.15F * var24
               );
            }
         }

         long var34 = 19149841844559L;
         int var361 = var34 < 0L ? -1 : 1;
      }
   }

   private void X(float var1, float var2, int var3, HaloBuilder.HaloGeometryView var4, long var5) {
      long var7 = 1208367481L;
      char var9 = '鎾';
      HaloDesign.ensureSourceClassInitialized();
      float var10 = n(this.z.T);
      long var11 = 115855631474872L;
      int var25 = var11 < 0L ? -1 : 1;
      if (var10 >= 0.999F) {
         if (this.D.begin(0, true, 0.0F)) {
            for (int var14 = 0; var14 < var3; var14++) {
               float var15 = var14 * (float) (Math.PI * 2) / var3;
               long var16 = 115855631474872L;
               int var171 = var16 < 0L ? -1 : 1;
               this.d(var1, var2, var15);
               var4.at(var15 - this.r.a, 0.0F);
               this.v(var7, var9);
            }

            long var24 = 115855631474873L;
            int var261 = var24 < 0L ? -1 : 1;
            this.D.end();
         }
      } else {
         float var25x = (float) Math.PI * var10;
         int var26 = Math.max(2, Math.round(var3 * var10) + 1);
         if (this.D.begin(0, false, 0.0F)) {
            for (int var27 = 0; var27 <= var26; var27++) {
               long var17 = 115855631474873L;
               int var20 = var17 < 0L ? -1 : 1;
               float var20x = (float)var27 / var26;
               float var21 = (float) Math.PI - var25x + 2.0F * var25x * var20x;
               float var22 = Math.min(var20x, 1.0F - var20x) * 2.0F * var25x;
               float var23 = 1.4F * (float)Math.exp(-var22 * var22 / 0.05F);
               this.d(var1, var2, var21);
               var4.at(var21 - this.r.a, var23);
               this.v(var7, var9);
            }

            long var28 = 115855631474873L;
            int var29 = var28 < 0L ? -1 : 1;
            this.D.end();
         }
      }
   }

   private void Y(float var1, float var2, float var3, float var4, int var5, char var6, HaloBuilder.HaloMeshSink var7, int var8, int var9) {
      long var10 = 134694533516808L;
      long var12 = var10 ^ 28543567278680L;
      this.K(var1, var2, var3, var12, var4, 14, var7, null);
   }

   private void K(float var1, float var2, float var3, long var4, float var6, int var7, HaloBuilder.HaloMeshSink var8, HaloBuilder.HaloMeshSource var9) {
      long var10 = 1208367481L;
      char var12 = '鎾';
      long var13 = 75080486235813L;
      HaloDesign.ensureSourceClassInitialized();
      float var15 = this.r.a;
      if (this.D.begin(0, false, 0.0F)) {
         boolean var16 = false;

         for (int var17 = 0; var17 <= var7; var17++) {
            float var18 = (float)var17 / var7;
            float var19 = var3 + (var6 - var3) * var18;
            float var20 = this.z(var13, var19 + var15);
            float var21;
            int var22 = (var21 = var20 - 0.02F) == 0.0F ? 0 : (var21 < 0.0F ? -1 : 1);
            long var23 = 109353405447247L;
            int var31 = var23 < 0L ? -1 : 1;
            if (var22 <= 0) {
               if (var16) {
                  this.D.end();
                  long var26 = 109353405447248L;
                  int var28 = var26 < 0L ? -1 : 1;
                  if (!this.D.begin(0, false, 0.0F)) {
                     return;
                  }

                  var16 = false;
                  long var28x = 109353405447247L;
                  int var29 = var28x < 0L ? -1 : 1;
               }
            } else {
               float var31x = var2 + (var9 == null ? 0.0F : var9.at(var19, var18));
               this.d(var1, var31x, var19 + var15);
               var8.at(var19, var18);
               this.g(var20);
               this.v(var10, var12);
               var16 = true;
            }
         }

         long var30 = 109353405447248L;
         int var311 = var30 < 0L ? -1 : 1;
         this.D.end();
      }
   }

   private void N(float var1, float var2, float var3, long var4, float var6, float var7, float var8, float var9, float var10, int var11, int var12, float var13) {
      HaloDesign.ensureSourceClassInitialized();
      long var14 = 75057392370974L;
      int var16 = var14 < 0L ? -1 : 1;
      if (this.D.begin(1, false, Math.clamp(var13, 0.0F, 1.0F))) {
         this.d(var1, var2, var3 + this.r.a);
         this.E(var6, var7, var8, var9, var10, 0.75F, var11, var12);
         this.D.end();
      }
   }

   private void d(float var1, float var2, float var3) {
      float var4 = (float)Math.cos(var3);
      float var5 = (float)Math.sin(var3);
      float var6 = var1 * var4;
      float var7 = var1 * var5;
      HaloBuilder.HaloBatchState var8 = this.r;
      float var9 = var8.i;
      this.Y = var8.U + (var6 * var8.n + var2 * var8.w + var7 * var8.u) * var9;
      this.J = var8.c + (var6 * var8.J + var2 * var8.q + var7 * var8.F) * var9;
      this.W = var8.y + (var6 * var8.G + var2 * var8.h + var7 * var8.C) * var9;
   }

   private void c(float var1, float var2, float var3, float var4, float var5, float var6, int var7, int var8) {
      this.j = var1;
      this.R = var2;
      this.E = var3;
      this.P = var4;
      this.H = var5;
      this.f = var6;
      this.O = var7;
      this.b = var8;
      this.p = 1.0F;
   }

   private void g(float var1) {
      this.E *= var1;
      this.P *= var1;
      this.j *= 0.4F + 0.6F * var1;
   }

   private void v(long var1, char var3) {
      HaloDesign.ensureSourceClassInitialized();
      if (!(this.p <= 0.0F)) {
         this.p = 0.0F;
         this.E(this.j, this.R, this.E, this.P, this.H, this.f, this.O, this.b);
      }
   }

   private void E(float var1, float var2, float var3, float var4, float var5, float var6, int var7, int var8) {
      float var9 = this.r.i;
      float var10 = this.G;
      this.D
         .point(this.Y, this.J, this.W, var1 * var9, var2 * var9, var3 * (1.0F + 0.2F * var10), var4, var5, Math.min(1.0F, var6 + 0.25F * var10), var7, var8);
   }

   private float z(long var1, float var3) {
      HaloDesign.ensureSourceClassInitialized();
      float var4 = n(this.z.T);
      long var5 = 75080486235812L;
      int var8 = var5 < 0L ? -1 : 1;
      if (var4 >= 0.999F) {
         return 1.0F;
      } else {
         float var8x = Math.abs(wrap(var3 - (float) Math.PI));
         float var9 = (float) Math.PI * var4;
         return 1.0F - smooth(var9 - 0.05F, var9 + 0.2F, var8x);
      }
   }

   private float j(long var1, byte var3, float var4) {
      HaloDesign.ensureSourceClassInitialized();
      float var5 = this.z.v;
      long var6 = 284791600568L;
      int var9 = var6 < 0L ? -1 : 1;
      if (var5 <= 0.001F) {
         return 0.0F;
      } else {
         float var9x = (1.0F - var5) * (float) Math.PI * 1.15F;
         float var10 = Math.abs(wrap(var4 + this.r.a - (float) Math.PI));
         float var11 = var10 - var9x;
         return 1.6F * var5 * (float)Math.exp(-var11 * var11 / 0.06F);
      }
   }

   private static float K(float var0, float var1) {
      float var2 = wrap(var0 - var1);
      return (float)Math.exp(-var2 * var2 / 0.09F);
   }

   private static float t(float var0, float var1) {
      return (float)Math.pow(0.5 + 0.5 * Math.cos(var0 - var1 * 1.6F), 5.0);
   }

   private static float n(float var0) {
      float var1 = Math.clamp(var0, 0.0F, 1.0F);
      return 1.0F - (1.0F - var1) * (1.0F - var1) * (1.0F - var1);
   }

   static float wrap(float var0) {
      float var1 = (var0 + (float) Math.PI) % (float) (Math.PI * 2);
      if (var1 < 0.0F) {
         var1 += (float) (Math.PI * 2);
      }

      return var1 - (float) Math.PI;
   }

   static float smooth(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   private static float h(float var0) {
      return var0 - (float)Math.floor(var0);
   }

   private static float T(int var0, long var1, int var3) {
      int var4 = var0 * 668265261 + var3 * 374761393;
      var4 ^= var4 >>> 15;
      var4 *= 739982445;
      var4 ^= var4 >>> 12;
      var4 *= 695872825;
      var4 ^= var4 >>> 15;
      return (var4 & 65535) / 65535.0F;
   }

   static final class HaloSegments {
      int H = -4222985;
      float c = 0.7F;
      float u = 1.0F;
      float J;
      float T = 1.0F;
      float N;
      float v;
   }

   static final class HaloVertex {
      private HaloVertex() {
      }

      static int lerp(int var0, int var1, float var2) {
         var2 = Math.clamp(var2, 0.0F, 1.0F);
         int var3 = Math.round((var0 >>> 24 & 0xFF) + ((var1 >>> 24 & 0xFF) - (var0 >>> 24 & 0xFF)) * var2);
         int var4 = Math.round((var0 >>> 16 & 0xFF) + ((var1 >>> 16 & 0xFF) - (var0 >>> 16 & 0xFF)) * var2);
         int var5 = Math.round((var0 >>> 8 & 0xFF) + ((var1 >>> 8 & 0xFF) - (var0 >>> 8 & 0xFF)) * var2);
         int var6 = Math.round((var0 & 0xFF) + ((var1 & 0xFF) - (var0 & 0xFF)) * var2);
         return var3 << 24 | var4 << 16 | var5 << 8 | var6;
      }

      static int hue(int var0, float var1) {
         float[] var2 = hsv(var0);
         return rgb(var0 >>> 24, (var2[0] + var1 % 1.0F + 1.0F) % 1.0F, var2[1], var2[2]);
      }

      static int saturate(int var0, float var1) {
         float[] var2 = hsv(var0);
         return rgb(var0 >>> 24, var2[0], Math.clamp(var2[1] + var1, 0.0F, 1.0F), var2[2]);
      }

      static float[] hsv(int var0) {
         float var1 = (var0 >>> 16 & 0xFF) / 255.0F;
         float var2 = (var0 >>> 8 & 0xFF) / 255.0F;
         float var3 = (var0 & 0xFF) / 255.0F;
         float var4 = Math.max(var1, Math.max(var2, var3));
         float var5 = Math.min(var1, Math.min(var2, var3));
         float var6 = var4 - var5;
         float var7 = 0.0F;
         if (var6 > 1.0E-5F) {
            if (var4 == var1) {
               var7 = (var2 - var3) / var6 % 6.0F;
            } else if (var4 == var2) {
               var7 = (var3 - var1) / var6 + 2.0F;
            } else {
               var7 = (var1 - var2) / var6 + 4.0F;
            }

            var7 /= 6.0F;
            if (var7 < 0.0F) {
               var7++;
            }
         }

         return new float[]{var7, var4 <= 0.0F ? 0.0F : var6 / var4, var4};
      }

      static int rgb(int var0, float var1, float var2, float var3) {
         float var4 = var3 * var2;
         float var5 = var1 * 6.0F;
         float var6 = var4 * (1.0F - Math.abs(var5 % 2.0F - 1.0F));
         float var7;
         float var8;
         float var9;
         if (var5 < 1.0F) {
            var7 = var4;
            var8 = var6;
            var9 = 0.0F;
         } else if (var5 < 2.0F) {
            var7 = var6;
            var8 = var4;
            var9 = 0.0F;
         } else if (var5 < 3.0F) {
            var7 = 0.0F;
            var8 = var4;
            var9 = var6;
         } else if (var5 < 4.0F) {
            var7 = 0.0F;
            var8 = var6;
            var9 = var4;
         } else if (var5 < 5.0F) {
            var7 = var6;
            var8 = 0.0F;
            var9 = var4;
         } else {
            var7 = var4;
            var8 = 0.0F;
            var9 = var6;
         }

         float var10 = var3 - var4;
         return (var0 & 0xFF) << 24
            | Math.round((var7 + var10) * 255.0F) << 16
            | Math.round((var8 + var10) * 255.0F) << 8
            | Math.round((var9 + var10) * 255.0F);
      }
   }

   static class HaloStrip {
      static final int[] z = new int[HaloDesign.values().length];

      static {
         try {
            z[HaloDesign.o.ordinal()] = 1;
         } catch (NoSuchFieldError var6) {
         }

         try {
            z[HaloDesign.v.ordinal()] = 2;
         } catch (NoSuchFieldError var5) {
         }

         try {
            z[HaloDesign.C.ordinal()] = 3;
         } catch (NoSuchFieldError var4) {
         }

         try {
            z[HaloDesign.W.ordinal()] = 4;
         } catch (NoSuchFieldError var3) {
         }

         try {
            z[HaloDesign.L.ordinal()] = 5;
         } catch (NoSuchFieldError var2) {
         }

         try {
            z[HaloDesign.m.ordinal()] = 6;
         } catch (NoSuchFieldError var1) {
         }
      }
   }

   private interface HaloMeshSink {
      void at(float var1, float var2);
   }

   private interface HaloMeshSource {
      float at(float var1, float var2);
   }

   static final class HaloBatchState {
      float U;
      float c;
      float y;
      float n = 1.0F;
      float J;
      float G;
      float u;
      float F;
      float C = 1.0F;
      float w;
      float q = 1.0F;
      float h;
      float i = 1.0F;
      float a;

      void basis(float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9) {
         this.n = var1;
         this.J = var2;
         this.G = var3;
         this.w = var4;
         this.q = var5;
         this.h = var6;
         this.u = var7;
         this.F = var8;
         this.C = var9;
      }
   }

   private interface HaloGeometryView {
      void at(float var1, float var2);
   }
}
