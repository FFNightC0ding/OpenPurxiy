package dev.purxiy.modules.cute;

import java.util.List;

public final class CuteWorldTransform {
   public static int L;
   private static final String[] a = new String[]{"EêôÙ'\u008b", "\u0091}\u0003\u0092g", "ÿrÎsÞ"};

   private CuteWorldTransform() {
   }

   public static CuteWorldTransform.ImagePixels transform(
      CuteWorldTransform.ImagePixels var0, List<CuteWorldTransform.TextureQuad> var1, CuteWorldTransform.Data var2, boolean var3
   ) {
      long var4 = 114497095274866L;
      long var6 = var4 ^ 33133655171106L;
      int var8 = Math.max(1, Math.min(4, 256 / var0.l));
      int var9 = var0.l * var8;
      int var10 = var0.y * var8;
      int[] var11 = new int[var9 * var10];

      for (int var12 = 0; var12 < var10; var12++) {
         for (int var13 = 0; var13 < var9; var13++) {
            int var14 = var0.z[var12 / var8 * var0.l + var13 / var8];
            var11[var12 * var9 + var13] = var3 ? var14 : mix(var14, -6161, var2.C);
         }
      }

      CuteWorldTransform.ImagePixels var15 = new CuteWorldTransform.ImagePixels(var9, var10, var11);

      for (CuteWorldTransform.TextureQuad var17 : var1) {
         m(var15, var6, var0, var17, var2);
      }

      return var15;
   }

   private static void m(CuteWorldTransform.ImagePixels var0, long var1, CuteWorldTransform.ImagePixels var3, CuteWorldTransform.TextureQuad var4, CuteWorldTransform.Data var5) {
      long var6 = 137722986549786L;
      long var8 = 135087659226120L;
      long var10 = 102308177208439L;
      long var12 = 49092105673312L;
      CuteWorldUpdater.ensureSourceClassInitialized();
      int var14 = (int)Math.round(var4.N * var0.l);
      int var15 = (int)Math.round(var4.e * var0.y);
      int var16 = (int)Math.round(var4.j * var0.l);
      int var17 = (int)Math.round(var4.s * var0.y);
      if (var14 >= 0) {
         long var18 = 129746282099024L;
         int var21 = var18 < 0L ? -1 : 1;
         if (var15 >= 0) {
            long var21x = 129746282099024L;
            int var23 = var21x < 0L ? -1 : 1;
            if (var16 <= var0.l) {
               long var23x = 129746282099024L;
               int var25 = var23x < 0L ? -1 : 1;
               long var25x = 129746282099024L;
               int var27 = var25x < 0L ? -1 : 1;
               if (var17 <= var0.y) {
                  long var27x = 129746282099024L;
                  int var29 = var27x < 0L ? -1 : 1;
                  var29 = var16 - var14;
                  long var30 = 129746282099024L;
                  int var32 = var30 < 0L ? -1 : 1;
                  if (var29 >= 2 && var17 - var15 >= 2) {
                     boolean var32x = var4.p == CuteWorldTransform.CutePart.g;
                     int var33 = skinTone(var3, var4);
                     if (var32x) {
                        long var34 = 129746282099024L;
                        int var36 = var34 < 0L ? -1 : 1;
                        if (!var5.W) {
                           var33 = -797507;
                        }
                     }

                     long var74 = 129746282099023L;
                     int var36 = var74 < 0L ? -1 : 1;
                     if (!var32x) {
                        var33 = mix(var33, -6161, var5.C);
                     }

                     var36 = var5.W ? e(var3, var4, var6) : -4879397;
                     int var37 = mix(var33, -3863, 0.45);
                     int var38 = var15;

                     while (var38 < var17) {
                        for (int var39 = var14; var39 < var16; var39++) {
                           double var40 = (var39 + 0.5 - var14) / (var16 - var14);
                           double var42 = (var38 + 0.5 - var15) / (var17 - var15);
                           int var44 = var38 * var0.l + var39;
                           long var45 = 129746282099024L;
                           int var47 = var45 < 0L ? -1 : 1;
                           var47 = var0.z[var44];
                           if (var4.p == CuteWorldTransform.CutePart.J) {
                              double var48;
                              var29 = (var48 = var40 - 0.125) == 0.0 ? 0 : (var48 < 0.0 ? -1 : 1);
                              long var50 = 129746282099023L;
                              int var53 = var50 < 0L ? -1 : 1;
                              if (var29 >= 0) {
                                 long var53x = 129746282099023L;
                                 double var56;
                                 var29 = (var56 = var40 - 0.875) == 0.0 ? 0 : (var56 < 0.0 ? -1 : 1);
                                 long var58 = 129746282099024L;
                                 int var59 = var58 < 0L ? -1 : 1;
                                 if (var29 < 0) {
                                    double var60;
                                    var29 = (var60 = var42 - 0.375) == 0.0 ? 0 : (var60 < 0.0 ? -1 : 1);
                                    long var62 = 129746282099024L;
                                    int var64 = var62 < 0L ? -1 : 1;
                                    if (var29 >= 0) {
                                       double var64x;
                                       var29 = (var64x = var42 - 0.94) == 0.0 ? 0 : (var64x < 0.0 ? -1 : 1);
                                       long var66 = 129746282099023L;
                                       int var68 = var66 < 0L ? -1 : 1;
                                       if (var29 < 0) {
                                          var0.z[var44] = 0;
                                          long var68x = 129746282099023L;
                                          int var67 = var68x < 0L ? -1 : 1;
                                       }
                                    }
                                 }
                              }
                           } else {
                              long var77 = 129746282099024L;
                              int var51 = var77 < 0L ? -1 : 1;
                              if (var47 >>> 24 >= 16) {
                                 var51 = var47;
                                 if (var4.p == CuteWorldTransform.CutePart.T) {
                                    var51 = J(var40, var42, 0.5, 0.5, 0.42, 0.46, var33, var36, var37, var12, var5.S);
                                 } else {
                                    if (var4.p != CuteWorldTransform.CutePart.I) {
                                       long var52 = 129746282099023L;
                                       int var52x = var52 < 0L ? -1 : 1;
                                       boolean var54 = var32x ? var40 >= 0.125 && var40 < 0.875 && var42 >= 0.375 && var42 < 0.94 : var42 >= 0.18;
                                       long var55 = 129746282099024L;
                                       int var96 = var55 < 0L ? -1 : 1;
                                       long var57 = 129746282099023L;
                                       int var99 = var57 < 0L ? -1 : 1;
                                       if (var54) {
                                          var51 = mix(var33, var47, var32x ? 0.0 : 0.055);
                                       }

                                       var51 = J(var40, var42, 0.29, var4.P, 0.097, 0.135, var51, var36, var37, var12, var5.S);
                                       var51 = J(var40, var42, 0.71, var4.P, 0.097, 0.135, var51, var36, var37, var12, var5.S);
                                       boolean var59 = w(var40, var42, 0.5, var10, 0.814, 0.038, 0.035);
                                       long var83 = 129746282099024L;
                                       int var84 = var83 < 0L ? -1 : 1;
                                       if (var59) {
                                          var51 = -1535062;
                                       }

                                       double var84x = 0.461;
                                       if (s(var8, var40, var42, var84x) || s(var8, var40, var42, 0.539)) {
                                          var51 = -11191728;
                                       }
                                    }

                                    if (var5.t) {
                                       double var79 = Math.max(W(var40, var42, 0.18), W(var40, var42, 0.82));
                                       long var80x = 129746282099024L;
                                       int var54x = var80x < 0L ? -1 : 1;
                                       var51 = mix(var51, -28757, var79 * 0.8);
                                       double var81x = Math.abs(var42 - 0.71);
                                       long var82 = 129746282099023L;
                                       int var101 = var82 < 0L ? -1 : 1;
                                       if (var81x < 0.013 && (Math.abs(var40 - 0.17) < 0.026 || Math.abs(var40 - 0.81) < 0.026)) {
                                          var51 = mix(var51, -10270, 0.6);
                                       }
                                    }
                                 }

                                 var0.z[var44] = var47 & 0xFF000000 | var51 & 16777215;
                              }
                           }
                        }

                        var38++;
                        long var75 = 129746282099023L;
                        int var401 = var75 < 0L ? -1 : 1;
                     }

                     long var76 = 129746282099023L;
                     int var83x = var76 < 0L ? -1 : 1;
                     return;
                  }
               }
            }
         }
      }
   }

   private static int J(
      double var0, double var2, double var4, double var6, double var8, double var10, int var12, int var13, int var14, long var15, String var17
   ) {
      CuteWorldUpdater.ensureSourceClassInitialized();
      double var18 = (var0 - var4) / var8;
      double var20 = (var2 - var6) / var10;
      long var22 = 49092105673312L;
      int var25 = var22 < 0L ? -1 : 1;
      var25 = -629503417;
      int var26 = -48981;
      long var27 = 49092105673311L;
      int var24 = var27 < 0L ? -1 : 1;
      var24 = -629529210 - var26;
      var26 = 629480032;
      if (!var17.equals("Happy") && !var17.equals("Sleepy")) {
         double var56 = var18 * var18;
         long var59 = 49092105673311L;
         int var62 = var59 < 0L ? -1 : 1;
         if (var56 + var20 * var20 < 1.26) {
            var12 = var14;
         }

         var56 = var18 * var18;
         long var60 = 49092105673312L;
         int var65 = var60 < 0L ? -1 : 1;
         double var61;
         int var62x = (var61 = var56 + var20 * var20 - 1.0) == 0.0 ? 0 : (var61 < 0.0 ? -1 : 1);
         long var63 = 49092105673312L;
         int var69 = var63 < 0L ? -1 : 1;
         if (var62x < 0) {
            var12 = -13622219;
         }

         var56 = var18 * var18;
         long var41 = 49092105673312L;
         int var43 = var41 < 0L ? -1 : 1;
         double var43x;
         int var45 = (var43x = var56 / 0.71 + (var20 - 0.38) * (var20 - 0.38) / 0.33 - 1.0) == 0.0 ? 0 : (var43x < 0.0 ? -1 : 1);
         long var46 = 49092105673312L;
         int var48 = var46 < 0L ? -1 : 1;
         if (var45 < 0) {
            var12 = mix(var13, -2246934, 0.32);
         }

         long var48x = 49092105673311L;
         int var50 = var48x < 0L ? -1 : 1;
         double var50x;
         int var52 = (var50x = (var18 + 0.27) * (var18 + 0.27) + (var20 + 0.4) * (var20 + 0.4) - 0.14) == 0.0 ? 0 : (var50x < 0.0 ? -1 : 1);
         long var53 = 49092105673312L;
         int var54 = var53 < 0L ? -1 : 1;
         if (var52 < 0) {
            var12 = -1293;
         }

         if ((var18 - 0.33) * (var18 - 0.33) + (var20 - 0.4) * (var20 - 0.4) < 0.04) {
            var12 = -4360;
         }

         return var12;
      } else {
         double var30 = var17.equals("Happy") ? -0.22 + 0.67 * var18 * var18 : 0.24 - 0.37 * var18 * var18;
         double var32 = Math.abs(var18);
         long var34 = 49092105673311L;
         int var36 = var34 < 0L ? -1 : 1;
         if (var32 < 1.05) {
            double var36x;
            int var38 = (var36x = Math.abs(var20 - var30) - 0.21) == 0.0 ? 0 : (var36x < 0.0 ? -1 : 1);
            long var39 = 49092105673311L;
            int var41x = var39 < 0L ? -1 : 1;
            if (var38 < 0) {
               return -13162178;
            }
         }

         return Math.abs(var18) < 1.18 && Math.abs(var20 - var30) < 0.35 ? var14 : var12;
      }
   }

   private static boolean s(long var0, double var2, double var4, double var6) {
      CuteWorldUpdater.ensureSourceClassInitialized();
      double var8 = (var2 - var6) / 0.043;
      long var10 = 135087659226120L;
      int var13 = var10 < 0L ? -1 : 1;
      double var13x = Math.abs(var8);
      long var15 = 135087659226119L;
      int var16 = var15 < 0L ? -1 : 1;
      return var13x < 1.13 && Math.abs(var4 - (0.787 - 0.04 * var8 * var8)) < 0.015;
   }

   private static boolean w(double var0, double var2, double var4, long var6, double var8, double var10, double var12) {
      CuteWorldUpdater.ensureSourceClassInitialized();
      return (var0 - 0.5) * (var0 - 0.5) / 0.001444 + (var2 - 0.814) * (var2 - 0.814) / 0.0012250000000000002 < 1.0;
   }

   private static double W(double var0, double var2, double var4) {
      double var6 = (var0 - var4) * (var0 - var4) / 0.009 + (var2 - 0.715) * (var2 - 0.715) / 0.0025;
      return Math.max(0.0, 1.0 - var6);
   }

   static int skinTone(CuteWorldTransform.ImagePixels var0, CuteWorldTransform.TextureQuad var1) {
      long var2 = 17111686129619L;
      long var4 = var2 ^ 46770910201382L;
      long var6 = var2 ^ 140062667174899L;
      int[] var8 = new int[64];
      int var9 = 0;

      for (int var10 = 3; var10 < 8; var10++) {
         for (int var11 = 0; var11 < 8; var11++) {
            if ((var10 >= 5 || var11 <= 0 || var11 >= 7) && (var10 < 5 || var11 < 3 || var11 > 4)) {
               int var12 = A(var0, var1, (var11 + 0.5) / 8.0, (var10 + 0.5) / 8.0);
               if (var12 >>> 24 > 127) {
                  var8[var9++] = var12;
               }
            }
         }
      }

      if (var9 == 0) {
         return -931111;
      } else {
         int var21 = var8[0];
         int var22 = -1;

         for (int var23 = 0; var23 < var9; var23++) {
            int var13 = 0;

            for (int var14 = 0; var14 < var9; var14++) {
               if (Q(var8[var23], var8[var14], var6) < 2400) {
                  var13++;
               }
            }

            if (var13 > var22 || var13 == var22 && T(var8[var23], var4) > T(var21, var4)) {
               var21 = var8[var23];
               var22 = var13;
            }
         }

         long var24 = 0L;
         long var25 = 0L;
         long var16 = 0L;
         long var18 = 0L;

         for (int var20 = 0; var20 < var9; var20++) {
            if (Q(var21, var8[var20], var6) < 2400) {
               var24 += var8[var20] >> 16 & 0xFF;
               var25 += var8[var20] >> 8 & 0xFF;
               var16 += var8[var20] & 0xFF;
               var18++;
            }
         }

         return 0xFF000000 | (int)(var24 / var18) << 16 | (int)(var25 / var18) << 8 | (int)(var16 / var18);
      }
   }

   private static int e(CuteWorldTransform.ImagePixels var0, CuteWorldTransform.TextureQuad var1, long var2) {
      long var4 = 40792198750709L;
      CuteWorldUpdater.ensureSourceClassInitialized();
      int var6 = -5602609;
      int var7 = 25;
      int var8 = 3;

      while (var8 <= 4) {
         for (int var12 : new int[]{1, 2, 5, 6}) {
            int var13 = A(var0, var1, (var12 + 0.5) / 8.0, (var8 + 0.5) / 8.0);
            int var14 = var13 >> 16 & 0xFF;
            int var15 = var13 >> 8 & 0xFF;
            int var16 = var13 & 0xFF;
            int var17 = Math.max(var14, Math.max(var15, var16)) - Math.min(var14, Math.min(var15, var16));
            long var18 = 137722986549785L;
            int var22 = var18 < 0L ? -1 : 1;
            long var22x = 137722986549785L;
            long var24x = 137722986549786L;
            if (var17 > var7) {
               long var26x = 137722986549786L;
               var17 = Math.max(var15, var16);
               long var28x = 137722986549785L;
               if (var17 > var14 * 1.06) {
                  long var30x = 137722986549785L;
                  if (T(var13, var4) > 45) {
                     var6 = var13;
                     var7 = var17;
                  }
               }
            }
         }

         var8++;
         long var32 = 137722986549785L;
         int var33 = var32 < 0L ? -1 : 1;
      }

      long var33 = 137722986549785L;
      int var34 = var33 < 0L ? -1 : 1;
      return var6;
   }

   private static int A(CuteWorldTransform.ImagePixels var0, CuteWorldTransform.TextureQuad var1, double var2, double var4) {
      int var6 = Math.clamp((long)((int)((var1.N + (var1.j - var1.N) * var2) * var0.l)), 0, var0.l - 1);
      int var7 = Math.clamp((long)((int)((var1.e + (var1.s - var1.e) * var4) * var0.y)), 0, var0.y - 1);
      return var0.z[var7 * var0.l + var6];
   }

   private static int T(int var0, long var1) {
      return ((var0 >> 16 & 0xFF) * 3 + (var0 >> 8 & 0xFF) * 6 + (var0 & 0xFF)) / 10;
   }

   private static int Q(int var0, int var1, long var2) {
      int var4 = (var0 >> 16 & 0xFF) - (var1 >> 16 & 0xFF);
      int var5 = (var0 >> 8 & 0xFF) - (var1 >> 8 & 0xFF);
      int var6 = (var0 & 0xFF) - (var1 & 0xFF);
      return var4 * var4 + var5 * var5 + var6 * var6;
   }

   public static int mix(int var0, int var1, double var2) {
      var2 = Math.clamp(var2, 0.0, 1.0);
      int var4 = (int)Math.round((var0 >> 16 & 0xFF) * (1.0 - var2) + (var1 >> 16 & 0xFF) * var2);
      int var5 = (int)Math.round((var0 >> 8 & 0xFF) * (1.0 - var2) + (var1 >> 8 & 0xFF) * var2);
      int var6 = (int)Math.round((var0 & 0xFF) * (1.0 - var2) + (var1 & 0xFF) * var2);
      return var0 & 0xFF000000 | var4 << 16 | var5 << 8 | var6;
   }

   public record Data(String S, boolean t, boolean W, double C) {
      public String expression() {
         return this.S;
      }

      public boolean blush() {
         return this.t;
      }

      public boolean adapt() {
         return this.W;
      }

      public double softness() {
         return this.C;
      }

      public String stringState37() {
         return this.S;
      }

      public boolean booleanState23() {
         return this.t;
      }

      public boolean booleanState12() {
         return this.W;
      }

      public double doubleState33() {
         return this.C;
      }
   }

   public record ImagePixels(int l, int y, int[] z) {
      public int width() {
         return this.l;
      }

      public int height() {
         return this.y;
      }

      public int[] pixels() {
         return this.z;
      }

      public int integerState20() {
         return this.l;
      }

      public int integerState52() {
         return this.y;
      }

      public int[] integerArrayState8() {
         return this.z;
      }
   }

   public record TextureQuad(double N, double e, double j, double s, CuteWorldTransform.CutePart p, double P) {
      public TextureQuad(double var1, double var3, double var5, double var7, CuteWorldTransform.CutePart var9) {
         this(var1, var3, var5, var7, var9, 0.54);
      }

      public double u0() {
         return this.N;
      }

      public double v0() {
         return this.e;
      }

      public double u1() {
         return this.j;
      }

      public double v1() {
         return this.s;
      }

      public CuteWorldTransform.CutePart kind() {
         return this.p;
      }

      public double eyeHeight() {
         return this.P;
      }

      public double doubleState37() {
         return this.N;
      }

      public double doubleState42() {
         return this.e;
      }

      public double doubleState35() {
         return this.j;
      }

      public double doubleState12() {
         return this.s;
      }

      public CuteWorldTransform.CutePart cuteTextureColorTransform_CuteTextureRegionState1() {
         return this.p;
      }

      public double doubleState40() {
         return this.P;
      }
   }

   public static enum CutePart {
      g,
      J,
      K,
      I,
      T;

      static {
         String[] var10000 = new String[]{"PLAYER", "CHEEKS", "HAT", "EYE", "MOB"};
         String var1 = "\u0018<\u000b\u0003\u0010*\f";
      }
   }
}
