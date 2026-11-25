package dev.purxiy.modules.home;

import java.time.LocalTime;

final class HomeUpdater2 {
   static final HomeUpdater2.TypeZ f = O(
      8365800, 16044470, 15390150, 16756872, 0.6F, 1.0F, 0.87F, 0.76F, 0.05F, 16772578, 0.6F, 9.0F, 5323831156922L, 98.0F, -30.0F, 278.0F, 0.05F, 0.0F
   );
   static final HomeUpdater2.TypeZ C = O(
      7249663, 12572415, 12900863, 16777215, 0.0F, 1.0F, 0.985F, 0.95F, 0.0F, 16777215, 0.62F, 56.0F, 5323831156922L, 150.0F, -40.0F, 330.0F, 0.0F, 0.0F
   );
   static final HomeUpdater2.TypeZ c = O(
      4872114, 15506865, 13540023, 16751194, 0.9F, 1.0F, 0.74F, 0.6F, 0.08F, 16762048, 0.62F, 3.0F, 5323831156922L, 262.0F, -20.0F, 82.0F, 0.18F, 0.15F
   );
   static final HomeUpdater2.TypeZ X = O(
      395803, 1712454, 1317431, 0, 0.0F, 0.3F, 0.35F, 0.55F, 0.05F, 5397632, 0.5F, -35.0F, 5323831156922L, 82.0F, 44.0F, 205.0F, 1.0F, 1.0F
   );
   private final HomeUpdater2.TypeZ l = C.copy();
   private HomeUpdater2.TypeZ a = C.copy();
   private HomeUpdater2.TypeZ k = C;
   private String n = "Day";
   private double t = -100.0;
   private boolean J = true;
   private HomeUpdater2.TypeZ Y = C.copy();
   private static final String[] d = new String[5];
   private static final long[] e = new long[]{
      4846719234731377535L,
      -3649385973757369913L,
      696423802741908174L,
      -1811872892336625355L,
      3307978862972799441L,
      3399898535466198025L,
      5113805884339836616L,
      -6734211542934800812L,
      1331970067738642276L
   };
   private static final Integer[] g = new Integer[9];

   HomeUpdater2.TypeZ update(String var1, double var2, double var4) {
      long var6 = 112368026185928L;
      long var8 = var6 ^ 108685576341341L;
      int var10 = (int)((var6 ^ 108685576341341L) >>> 48);
      int var11 = (int)((var6 ^ 108685576341341L) << 16 >>> 48);
      int var12 = (int)(var8 << 32 >>> 32);
      HomeUpdater2.TypeZ var13 = this.E(var1, (short)var10, (char)var11, var12);
      if (this.J) {
         this.J = false;
         this.n = var1;
         this.k = var13;
         this.l.set(var13, var13, 0.0F);
         this.a = this.l.copy();
         return this.l;
      } else {
         if (!var1.equals(this.n)) {
            this.n = var1;
            this.a = this.l.copy();
            this.t = var2;
         }

         this.k = var13;
         float var14 = (float)Math.min(1.0, Math.max(0.0, (var2 - this.t) / var4));
         var14 = var14 * var14 * (3.0F - 2.0F * var14);
         this.l.set(this.a, this.k, var14);
         return this.l;
      }
   }

   HomeUpdater2.TypeZ current() {
      return this.l;
   }

   private HomeUpdater2.TypeZ E(String var1, short var2, char var3, int var4) {
      long var5 = 5411346544533L;
      long var7 = var5 ^ 61329190840792L;
      HomeSupport2.ensureSourceClassInitialized();

      return switch (var1) {
         case "Morning" -> f;
         case "Sunset" -> c;
         case "Night" -> X;
         case "Real time" -> this.f(var7);
         default -> C;
      };
   }

   private HomeUpdater2.TypeZ f(long var1) {
      HomeSupport2.ensureSourceClassInitialized();
      LocalTime var3 = LocalTime.now();
      float var4 = var3.getHour() + var3.getMinute() / 60.0F + var3.getSecond() / 3600.0F;
      float[] var5 = new float[]{0.0F, 5.4F, 6.6F, 8.6F, 17.6F, 19.4F, 20.7F, 24.0F};
      HomeUpdater2.TypeZ[] var6 = new HomeUpdater2.TypeZ[]{X, X, f, C, C, c, X, X};

      for (int var7 = 0; var7 < var5.length - 1; var7++) {
         float var8;
         int var9 = (var8 = var4 - var5[var7]) == 0.0F ? 0 : (var8 < 0.0F ? -1 : 1);
         long var10 = 56267255127628L;
         int var13 = var10 < 0L ? -1 : 1;
         if (var9 >= 0) {
            float var13x;
            var9 = (var13x = var4 - var5[var7 + 1]) == 0.0F ? 0 : (var13x < 0.0F ? -1 : 1);
            long var14 = 56267255127628L;
            int var16 = var14 < 0L ? -1 : 1;
            if (var9 <= 0) {
               float var16x = (var4 - var5[var7]) / Math.max(0.001F, var5[var7 + 1] - var5[var7]);
               long var17 = 56267255127628L;
               int var18 = var17 < 0L ? -1 : 1;
               float var24 = var16x * var16x * (3.0F - 2.0F * var16x);
               this.Y.set(var6[var7], var6[var7 + 1], var24);
               break;
            }
         }
      }

      long var19 = 56267255127629L;
      int var21 = var19 < 0L ? -1 : 1;
      return this.Y;
   }

   private static HomeUpdater2.TypeZ O(
      int var0,
      int var1,
      int var2,
      int var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      int var9,
      float var10,
      float var11,
      long var12,
      float var14,
      float var15,
      float var16,
      float var17,
      float var18
   ) {
      long var19 = 63369549710827L;
      HomeUpdater2.TypeZ var21 = new HomeUpdater2.TypeZ();
      g(var21.e, var0, var19);
      g(var21.Q, var1, var19);
      g(var21.m, var2, var19);
      g(var21.Y, var3, var19);
      var21.Z = var4;
      var21.L[0] = var5;
      var21.L[1] = var6;
      var21.L[2] = var7;
      var21.V[0] = var8;
      var21.V[1] = var8;
      var21.V[2] = var8 * 1.5F;
      g(var21.A, var9, var19);
      var21.w = var10;
      var21.y = var11;
      var21.n = var14;
      var21.r = var15;
      var21.v = var16;
      var21.i = var17;
      var21.d = var18;
      var21.S = var18 > 0.5F ? 30.0F : 40.0F;
      var21.k = 64.0F;
      return var21;
   }

   private static void g(float[] var0, int var1, long var2) {
      var0[0] = (var1 >> 16 & 0xFF) / 255.0F;
      var0[1] = (var1 >> 8 & 0xFF) / 255.0F;
      var0[2] = (var1 & 0xFF) / 255.0F;
   }

   static void mix(float[] var0, float[] var1, float[] var2, float var3) {
      for (int var4 = 0; var4 < var0.length; var4++) {
         var0[var4] = var1[var4] + (var2[var4] - var1[var4]) * var3;
      }
   }

   static float lerp(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   private static float k(float var0, float var1, float var2) {
      float var3 = ((var1 - var0) % 360.0F + 540.0F) % 360.0F - 180.0F;
      return var0 + var3 * var2;
   }

   static float[] direction(float var0, float var1) {
      double var2 = Math.toRadians(var0);
      double var4 = Math.toRadians(var1);
      return new float[]{(float)(Math.cos(var2) * Math.sin(var4)), (float)Math.sin(var2), (float)(Math.cos(var2) * Math.cos(var4))};
   }

   static final class TypeZ {
      float[] e = new float[3];
      float[] Q = new float[3];
      float[] m = new float[3];
      float[] Y = new float[3];
      float Z;
      float[] L = new float[3];
      float[] V = new float[3];
      float[] A = new float[3];
      float w;
      float y;
      float n;
      float r;
      float v;
      float i;
      float d;
      float S;
      float k;

      HomeUpdater2.TypeZ copy() {
         HomeUpdater2.TypeZ var1 = new HomeUpdater2.TypeZ();
         var1.set(this, this, 0.0F);
         return var1;
      }

      void set(HomeUpdater2.TypeZ var1, HomeUpdater2.TypeZ var2, float var3) {
         HomeUpdater2.mix(this.e, var1.e, var2.e, var3);
         HomeUpdater2.mix(this.Q, var1.Q, var2.Q, var3);
         HomeUpdater2.mix(this.m, var1.m, var2.m, var3);
         HomeUpdater2.mix(this.Y, var1.Y, var2.Y, var3);
         HomeUpdater2.mix(this.L, var1.L, var2.L, var3);
         HomeUpdater2.mix(this.V, var1.V, var2.V, var3);
         HomeUpdater2.mix(this.A, var1.A, var2.A, var3);
         this.Z = HomeUpdater2.lerp(var1.Z, var2.Z, var3);
         this.w = HomeUpdater2.lerp(var1.w, var2.w, var3);
         this.y = HomeUpdater2.lerp(var1.y, var2.y, var3);
         this.n = HomeUpdater2.k(var1.n, var2.n, var3);
         this.r = HomeUpdater2.lerp(var1.r, var2.r, var3);
         this.v = HomeUpdater2.k(var1.v, var2.v, var3);
         this.i = HomeUpdater2.lerp(var1.i, var2.i, var3);
         this.d = HomeUpdater2.lerp(var1.d, var2.d, var3);
         this.S = HomeUpdater2.lerp(var1.S, var2.S, var3);
         this.k = HomeUpdater2.lerp(var1.k, var2.k, var3);
      }
   }
}
