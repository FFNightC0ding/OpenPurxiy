package dev.purxiy.modules.regionmap;

final class RegionMapSupport {
   static final int H = 9;
   static final double n = 50000.0;
   static final double i = 225000.0;
   private static final int[][] u = new int[][]{
      {82, 5},
      {100, 3},
      {101, 3},
      {102, 3},
      {103, 2},
      {104, 2},
      {105, 2},
      {106, 2},
      {91, 2},
      {83, 5},
      {44, 3},
      {75, 3},
      {42, 3},
      {41, 2},
      {40, 2},
      {39, 2},
      {38, 2},
      {92, 2},
      {84, 5},
      {45, 3},
      {14, 3},
      {13, 3},
      {12, 2},
      {11, 2},
      {10, 2},
      {37, 2},
      {93, 2},
      {85, 5},
      {46, 5},
      {74, 5},
      {3, 3},
      {2, 2},
      {1, 2},
      {25, 2},
      {36, 2},
      {94, 2},
      {86, 4},
      {47, 4},
      {72, 4},
      {71, 4},
      {5, 2},
      {4, 2},
      {24, 2},
      {35, 2},
      {95, 2},
      {87, 4},
      {51, 1},
      {17, 1},
      {9, 0},
      {8, 0},
      {7, 0},
      {23, 0},
      {34, 0},
      {96, 2},
      {88, 4},
      {54, 1},
      {18, 1},
      {61, 0},
      {62, 0},
      {21, 0},
      {22, 0},
      {33, 0},
      {97, 0},
      {89, 0},
      {26, 1},
      {27, 0},
      {28, 0},
      {29, 0},
      {30, 0},
      {59, 0},
      {32, 0},
      {98, 0},
      {90, 0},
      {107, 1},
      {108, 1},
      {109, 1},
      {110, 1},
      {111, 1},
      {112, 1},
      {113, 1},
      {99, 0}
   };
   private static final RegionMapSupport.RegionTint[] V = RegionMapSupport.RegionTint.values();
   private static final String[] s = new String[u.length];
   private static final long[] a = new long[]{-2323577001082980541L, -4941031257273521820L, 5073049498542743047L};

   private RegionMapSupport() {
   }

   static double grid(double var0) {
      return (var0 + 225000.0) / 50000.0;
   }

   static int cell(double var0) {
      return (int)Math.floor(grid(var0));
   }

   static boolean inside(int var0, int var1) {
      return var0 >= 0 && var0 < 9 && var1 >= 0 && var1 < 9;
   }

   static int id(int var0, int var1) {
      return inside(var0, var1) ? u[var1 * 9 + var0][0] : -1;
   }

   static String number(int var0, int var1) {
      return inside(var0, var1) ? s[var1 * 9 + var0] : null;
   }

   static RegionMapSupport.RegionTint host(int var0, int var1) {
      return inside(var0, var1) ? V[u[var1 * 9 + var0][1]] : null;
   }

   static RegionMapSupport.Data ahead(double var0, double var2, double var4, double var6) {
      long var8 = 49044442080899L;
      long var10 = var8 ^ 131486998344023L;
      int var12 = (int)((var8 ^ 131486998344023L) >>> 48);
      int var13 = (int)((var8 ^ 131486998344023L) << 16 >>> 32);
      int var14 = (int)(var10 << 48 >>> 48);
      double var15 = Math.sqrt(var4 * var4 + var6 * var6);
      if (var15 < 1.0E-9) {
         return null;
      } else {
         var4 /= var15;
         var6 /= var15;
         double var17 = grid(var0);
         double var19 = grid(var2);
         int var21 = (int)Math.floor(var17);
         int var22 = (int)Math.floor(var19);
         int var23 = id(var21, var22);
         int var24 = var4 > 0.0 ? 1 : -1;
         int var25 = var6 > 0.0 ? 1 : -1;
         double var26 = Math.abs(var4) < 1.0E-9 ? Double.POSITIVE_INFINITY : (var24 > 0 ? var21 + 1 - var17 : var17 - var21) / Math.abs(var4);
         double var28 = Math.abs(var6) < 1.0E-9 ? Double.POSITIVE_INFINITY : (var25 > 0 ? var22 + 1 - var19 : var19 - var22) / Math.abs(var6);
         double var30 = Math.abs(var4) < 1.0E-9 ? Double.POSITIVE_INFINITY : 1.0 / Math.abs(var4);
         double var32 = Math.abs(var6) < 1.0E-9 ? Double.POSITIVE_INFINITY : 1.0 / Math.abs(var6);

         for (int var34 = 0; var34 < 100; var34++) {
            double var35;
            if (var26 < var28) {
               var21 += var24;
               var35 = var26;
               var26 += var30;
            } else {
               var22 += var25;
               var35 = var28;
               var28 += var32;
            }

            int var37 = id(var21, var22);
            if (var23 >= 0 && var37 < 0) {
               return new RegionMapSupport.Data(-1, var35 * 50000.0);
            }

            if (var37 >= 0 && var37 != var23) {
               return new RegionMapSupport.Data(var37, var35 * 50000.0);
            }

            if (var37 < 0 && N(var21, (char)var12, var22, var13, (short)var14, var24, var25)) {
               return null;
            }
         }

         return null;
      }
   }

   private static boolean N(int var0, char var1, int var2, int var3, short var4, int var5, int var6) {
      RegionMapSupport.RegionTint.ensureSourceClassInitialized();
      boolean var7;
      if ((var0 >= 0 || var5 >= 0) && (var0 < 9 || var5 <= 0)) {
         var7 = false;
      } else {
         var7 = true;
      }

      int var9 = 1527594840;
      if (var2 >= 0 || var6 >= 0) {
         var9 = 1527594840;
         if (var2 < 9 || var6 <= 0) {
            boolean var12 = false;
            return var7 || var12;
         }
      }

      boolean var10 = true;
      return var7 || var10;
   }

   record Data(int t, double l) {
      public int id() {
         return this.t;
      }

      public double distance() {
         return this.l;
      }

      public int integerState14() {
         return this.t;
      }

      public double doubleState16() {
         return this.l;
      }
   }

   static enum RegionTint {
      Z("EU Central", -3294977),
      D("EU West", -7509003),
      O("NA East", -7942913),
      G("NA West", -11632657),
      B("Asia", -27950),
      R("Oceania", -19323);

      final String t;
      final int A;
      public static boolean h;
      private static final long a = -114763995755315194L;

      private RegionTint(String nullxx, int nullxxx) {
         this.t = nullxx;
         this.A = nullxxx;
      }

      public static void ensureSourceClassInitialized() {
      }
   }
}
