package dev.purxiy.modules.sky;

import com.mojang.blaze3d.buffers.Std140Builder;
import java.util.Arrays;

public final class SkyCodec {
   private static final float l = 0.02F;
   private final float[] U = new float[3];
   private final float[] d = new float[3];
   private SkyCodec.Data g;
   private float o;
   private float Y;
   private float k;
   private float O;
   private boolean h;
   private float E;
   private float V;
   private float D;
   private float v;
   private float i;
   private float n;
   private int y;
   private final int[] t = new int[64];
   private final int[] j = new int[64];
   public static int T = 0;

   public void follow(SkyModule var1, SkyKind var2, float var3) {
      long var4 = 78693680667225L;
      long var6 = var4 ^ 89202132890298L;
      SkyModule.Colours var8 = var1.Y.get(var2.ordinal());
      SkyCodec.Data var9 = var2 == SkyKind.X ? SkyCatalog.byName((String)var1.D.get()).J : var2.j;
      this.Y(var9, !var8.original(), var6, var8.colour(), var3);
   }

   public void followPicture(SkyModule var1, SkyKind var2, float var3) {
      long var4 = 106171553735253L;
      long var6 = var4 ^ 130005643201206L;
      SkyModule.Colours var8 = var1.Y.get(var2.ordinal());
      this.Y(var2 == SkyKind.X ? SkyCatalog.M.J : var2.j, !var8.original(), var6, var8.colour(), var3);
   }

   private void Y(SkyCodec.Data var1, boolean var2, long var3, int var5, float var6) {
      long var8 = 85691228810830L;
      SkyKind.ensureSourceClassInitialized();
      boolean var10 = false;
      long var11 = 24956739021027L;
      float var14;
      if (var1 != this.g) {
         this.g = var1;
         H(var1.N, var8, this.U);
         var10 = true;
      }

      float var15;
      float var16;
      float var17;
      label151: {
         label158: {
            H(var5, var8, this.d);
            var14 = this.o;
            var15 = this.Y;
            var16 = this.k;
            var17 = this.O;
            long var18 = 24956739021026L;
            int var18x = var18 < 0L ? -1 : 1;
            long var20x = 24956739021027L;
            if (this.h) {
               float var22;
               int var23 = (var22 = var6 - 1.0F) == 0.0F ? 0 : (var22 < 0.0F ? -1 : 1);
               long var24 = 24956739021027L;
               int var24x = var24 < 0L ? -1 : 1;
               if (var23 < 0) {
                  if (!var2) {
                     break label158;
                  }

                  float var26;
                  var23 = (var26 = this.O - 0.001F) == 0.0F ? 0 : (var26 < 0.0F ? -1 : 1);
                  long var27 = 24956739021026L;
                  int var27x = var27 < 0L ? -1 : 1;
                  if (var23 > 0) {
                     break label158;
                  }
               }
            }

            label138: {
               long var33 = 24956739021026L;
               int var39 = var33 < 0L ? -1 : 1;
               this.o = this.d[0];
               this.Y = this.d[1];
               if (!(this.d[1] >= 0.02F)) {
                  long var25 = 24956739021026L;
                  int var44 = var25 < 0L ? -1 : 1;
                  if (this.h) {
                     break label138;
                  }
               }

               this.k = this.d[2];
            }

            this.h = true;
            break label151;
         }

         if (var2) {
            this.o = this.o + (this.d[0] - this.o) * var6;
            this.Y = this.Y + (this.d[1] - this.Y) * var6;
            long var29 = 24956739021027L;
            int var341 = var29 < 0L ? -1 : 1;
            if (this.d[1] >= 0.02F) {
               long var21 = 24956739021026L;
               int var41 = var21 < 0L ? -1 : 1;
               this.k = var15 < 0.02F ? this.d[2] : A(this.k + Z(this.d[2] - this.k) * var6);
            }

            if (Math.abs(this.d[0] - this.o) < 1.0E-4F && Math.abs(this.d[1] - this.Y) < 1.0E-4F && Math.abs(Z(this.d[2] - this.k)) < 0.05F) {
               this.o = this.d[0];
               this.Y = this.d[1];
               if (this.d[1] >= 0.02F) {
                  this.k = this.d[2];
               }
            }
         }
      }

      float var7;
      label131: {
         float var31 = var2 ? 1.0F : 0.0F;
         long var19 = 24956739021027L;
         int var7x = var19 < 0L ? -1 : 1;
         if (!(var6 >= 1.0F)) {
            long var34x = 24956739021027L;
            int var26 = var34x < 0L ? -1 : 1;
            if (!(Math.abs(var31 - this.O) < 1.0E-4F)) {
               var7 = this.O + (var31 - this.O) * var6;
               break label131;
            }
         }

         var7 = var31;
      }

      this.O = var7;
      if (!var10 && var14 == this.o) {
         long var30 = 24956739021026L;
         int var46 = var30 < 0L ? -1 : 1;
         if (var15 == this.Y && var16 == this.k && var17 == this.O) {
            return;
         }
      }

      this.z();
   }

   private void z() {
      SkyCodec.Data var1 = this.g;
      this.E = Z(this.k - this.U[2]);
      float var2 = this.Y / Math.max(this.U[1], 0.02F);
      this.V = Math.min(Math.max(var2, var1.F * U(0.03F, 0.08F, this.Y)), var1.D);
      this.D = Math.clamp(this.o - this.U[0], -var1.l, var1.y);
      this.v = 1.0F - (1.0F - var1.v) * U(15.0F, 75.0F, Math.abs(this.E));
      this.i = this.Y * var1.c;
      this.n = c(this.k);
      this.y++;
      Arrays.fill(this.t, 0);
   }

   public boolean original() {
      return this.O <= 0.0F;
   }

   public int generation() {
      return this.y;
   }

   void write(Std140Builder var1) {
      var1.putVec4((float)Math.toRadians(this.E), this.V, this.D, this.v).putVec4(this.i, this.O, (float)Math.toRadians(this.U[2]), this.n);
   }

   public int carry(int var1) {
      long var2 = 110096432404162L;
      long var4 = var2 ^ 109578555826568L;
      if (this.O <= 0.0F) {
         return var1;
      } else {
         int var6 = var1 & 16777215;
         int var7 = var6 * -1640531535 >>> 26;
         if (this.t[var7] == (var6 | 16777216)) {
            return var1 & 0xFF000000 | this.j[var7];
         } else {
            int var8 = this.J(var6, var4);
            this.j[var7] = var8;
            this.t[var7] = var6 | 16777216;
            return var1 & 0xFF000000 | var8;
         }
      }
   }

   private int J(int var1, long var2) {
      long var4 = 43504361873591L;
      long var6 = 57817914416325L;
      double[] var8 = m(var6, var1);
      SkyKind.ensureSourceClassInitialized();
      double var9 = Math.hypot(var8[1], var8[2]);
      double var11 = Math.toRadians(this.U[2]);
      long var13 = 8284662380362L;
      int var16 = var13 < 0L ? -1 : 1;
      double var16x = var9 > 1.0E-5 ? Math.atan2(var8[2], var8[1]) : var11;
      double var18 = U(0.02F, 0.08F, (float)var9);
      double var20 = Math.min((double)this.V, 1.15);
      double var22 = var20 + (this.V - var20) * var18;
      double var24 = l(var16x - var11) * this.v;
      long var26 = 8284662380362L;
      int var28 = var26 < 0L ? -1 : 1;
      if (var24 > 0.0) {
         var24 *= 1.0 - 0.75 * this.n;
      }

      double var28x = var11 + Math.toRadians(this.E);
      double var30 = U(0.12F, 0.6F, (float)var8[0]) * (1.0 - 0.55 * U(0.8F, 1.0F, (float)var8[0]));
      double var32 = Math.cos(var28x + var24) * var9 * var22 + Math.cos(var28x) * this.i * var30;
      double var34 = Math.sin(var28x + var24) * var9 * var22 + Math.sin(var28x) * this.i * var30;
      double var36 = Math.clamp(var8[0] + this.D * U(0.25F, 0.5F, (float)var8[0]), 0.0, 1.0);
      double var38 = Math.hypot(var32, var34);
      long var40 = 8284662380361L;
      int var42 = var40 < 0L ? -1 : 1;
      double var42x = var38 > 1.0E-5 ? Math.atan2(var34, var32) : var28x;
      double var51 = var42x - Math.toRadians(30.0) * c((float)Math.toDegrees(var42x)) * (1.0 - U(0.55F, 0.85F, (float)var36));
      int var44 = N(var36, var4, var38, var51);
      if (this.O >= 1.0F) {
         return var44;
      } else {
         int var45 = 0;

         for (byte var46 = 16; var46 >= 0; var46 = (byte)(var46 - 8)) {
            float var47 = var1 >> var46 & 0xFF;
            float var48 = var44 >> var46 & 0xFF;
            var45 |= Math.round(var47 + (var48 - var47) * this.O) << var46;
         }

         return var45;
      }
   }

   private static void H(int var0, long var1, float[] var3) {
      long var4 = 57817914416325L;
      SkyKind.ensureSourceClassInitialized();
      double[] var6 = m(var4, var0);
      double var7 = Math.toDegrees(Math.atan2(var6[2], var6[1]));
      var3[0] = (float)var6[0];
      double var9 = Math.hypot(var6[1], var6[2]);
      long var11 = 85691228810829L;
      int var13 = var11 < 0L ? -1 : 1;
      var3[1] = (float)var9;
      var3[2] = (float)(var7 < 0.0 ? var7 + 360.0 : var7);
   }

   private static double[] m(long var0, int var2) {
      long var3 = 32707100087669L;
      double var5 = j(var2 >> 16 & 0xFF, var3);
      double var7 = j(var2 >> 8 & 0xFF, var3);
      double var9 = j(var2 & 0xFF, var3);
      double var11 = Math.cbrt(0.4122214708 * var5 + 0.5363325363 * var7 + 0.0514459929 * var9);
      double var13 = Math.cbrt(0.2119034982 * var5 + 0.6806995451 * var7 + 0.1073969566 * var9);
      double var15 = Math.cbrt(0.0883024619 * var5 + 0.2817188376 * var7 + 0.6299787005 * var9);
      return new double[]{
         0.2104542553 * var11 + 0.793617785 * var13 - 0.0040720468 * var15,
         1.9779984951 * var11 - 2.428592205 * var13 + 0.4505937099 * var15,
         0.0259040371 * var11 + 0.7827717662 * var13 - 0.808675766 * var15
      };
   }

   private static int N(double var0, long var2, double var4, double var6) {
      long var8 = 102593407092777L;
      long var10 = 24219501129894L;
      SkyKind.ensureSourceClassInitialized();
      double var12 = Math.cos(var6);
      double var14 = Math.sin(var6);
      double[] var16 = new double[3];
      double var17 = var4 * var12;
      double var19 = var4 * var14;
      if (!i(var8, var0, var17, var19, var16)) {
         double var28 = 0.0;
         double var30 = var4;

         for (int var32 = 0; var32 < 16; var32++) {
            double var33 = (var28 + var30) * 0.5;
            long var35 = 43504361873591L;
            int var30x = var35 < 0L ? -1 : 1;
            var17 = var33 * var12;
            var19 = var33 * var14;
            long var38x = 43504361873590L;
            int var32x = var38x < 0L ? -1 : 1;
            if (i(var8, var0, var17, var19, var16)) {
               var28 = var33;
               long var41x = 43504361873590L;
               int var34 = var41x < 0L ? -1 : 1;
            } else {
               var30 = var33;
            }
         }

         var17 = var28 * var12;
         long var48 = 43504361873591L;
         int var27 = var48 < 0L ? -1 : 1;
         var19 = var28 * var14;
         i(var8, var0, var17, var19, var16);
      }

      return H(var10, var16[0]) << 16 | H(var10, var16[1]) << 8 | H(var10, var16[2]);
   }

   private static boolean i(long var0, double var2, double var4, double var6, double[] var8) {
      double var9 = var2 + 0.3963377774 * var4 + 0.2158037573 * var6;
      double var11 = var2 - 0.1055613458 * var4 - 0.0638541728 * var6;
      double var13 = var2 - 0.0894841775 * var4 - 1.291485548 * var6;
      SkyKind.ensureSourceClassInitialized();
      var9 = var9 * var9 * var9;
      var11 = var11 * var11 * var11;
      var13 = var13 * var13 * var13;
      var8[0] = 4.0767416621 * var9 - 3.3077115913 * var11 + 0.2309699292 * var13;
      var8[1] = -1.2684380046 * var9 + 2.6097574011 * var11 - 0.3413193965 * var13;
      var8[2] = -0.0041960863 * var9 - 0.7034186147 * var11 + 1.707614701 * var13;
      double var15 = 1.0E-4;
      double var17 = var8[0];
      long var19 = 102593407092776L;
      int var22 = var19 < 0L ? -1 : 1;
      double var22x = -var15;
      long var24 = 102593407092777L;
      int var25 = var24 < 0L ? -1 : 1;
      return var17 >= var22x && var8[0] <= 1.0 + var15 && var8[1] >= -var15 && var8[1] <= 1.0 + var15 && var8[2] >= -var15 && var8[2] <= 1.0 + var15;
   }

   private static double j(int var0, long var1) {
      SkyKind.ensureSourceClassInitialized();
      double var3 = var0 / 255.0;
      long var5 = 32707100087668L;
      int var7 = var5 < 0L ? -1 : 1;
      return var3 <= 0.04045 ? var3 / 12.92 : Math.pow((var3 + 0.055) / 1.055, 2.4);
   }

   private static int H(long var0, double var2) {
      SkyKind.ensureSourceClassInitialized();
      double var4 = Math.clamp(var2, 0.0, 1.0);
      long var6 = 24219501129893L;
      int var8 = var6 < 0L ? -1 : 1;
      var4 = var4 <= 0.0031308 ? 12.92 * var4 : 1.055 * Math.pow(var4, 0.4166666666666667) - 0.055;
      return (int)Math.round(Math.clamp(var4, 0.0, 1.0) * 255.0);
   }

   private static float c(float var0) {
      float var1 = A(var0);
      return U(50.0F, 80.0F, var1) * (1.0F - U(105.0F, 125.0F, var1));
   }

   private static float Z(float var0) {
      return (var0 % 360.0F + 540.0F) % 360.0F - 180.0F;
   }

   private static float A(float var0) {
      return (var0 % 360.0F + 360.0F) % 360.0F;
   }

   private static double l(double var0) {
      return var0 - (Math.PI * 2) * Math.floor((var0 + Math.PI) / (Math.PI * 2));
   }

   private static float U(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   public record Data(int N, float v, float F, float D, float c, float l, float y) {
      public int key() {
         return this.N;
      }

      public float spread() {
         return this.v;
      }

      public float floor() {
         return this.F;
      }

      public float cap() {
         return this.D;
      }

      public float greys() {
         return this.c;
      }

      public float darker() {
         return this.l;
      }

      public float lighter() {
         return this.y;
      }

      public int integerState28() {
         return this.N;
      }

      public float floatState30() {
         return this.v;
      }

      public float floatState36() {
         return this.F;
      }

      public float floatState24() {
         return this.D;
      }

      public float floatState25() {
         return this.c;
      }

      public float floatState37() {
         return this.l;
      }

      public float floatState2() {
         return this.y;
      }
   }
}
