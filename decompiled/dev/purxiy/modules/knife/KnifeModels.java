package dev.purxiy.modules.knife;

import org.joml.Matrix4f;

public final class KnifeModels {
   public static final String[] u = new String[]{"Karambit", "Butterfly", "M9 Bayonet", "Tactical", "Crystal Fang"};
   public static final String[] i = new String[]{"TALON / RING SPIN", "BALISONG / HANDLE FLIP", "M9 / PALM ROLL", "OPS / FLIP CATCH", "FROST / AERIAL TWIRL"};
   public static final float W = 0.021F;
   public static final float v = 0.105F;
   private static final KnifeModels.Model[][] R = new KnifeModels.Model[5][4];
   private static final KnifeModels.Model[] D = new KnifeModels.Model[5];

   public static Matrix4f hinge(Matrix4f var0, int var1, float var2) {
      return var0.translate(var1 * 0.021F, 0.105F, 0.0F).rotateZ((float)Math.toRadians(var2)).translate(-var1 * 0.021F, -0.105F, 0.0F);
   }

   private KnifeModels() {
   }

   public static int index(String var0) {
      for (int var1 = 0; var1 < u.length; var1++) {
         if (u[var1].equals(var0)) {
            return var1;
         }
      }

      return 0;
   }

   public static KnifeModels.Model get(int var0, String var1) {
      long var2 = 46494087071534L;
      long var4 = var2 ^ 15876197218071L;
      int var6 = (int)((var2 ^ 15876197218071L) >>> 32);
      int var7 = (int)((var2 ^ 15876197218071L) << 32 >>> 56);
      int var8 = (int)(var4 << 40 >>> 40);

      byte var9 = switch (var1) {
         case "Obsidian" -> 1;
         case "Doppler" -> 2;
         case "Gold" -> 3;
         default -> 0;
      };
      if (R[var0][var9] == null) {
         R[var0][var9] = U(var6, (byte)var7, var0, var9, var8);
      }

      return R[var0][var9];
   }

   public static KnifeModels.Model get(int var0, String var1, String var2) {
      long var3 = 58358979748356L;
      long var5 = var3 ^ 18874415370813L;
      int var7 = (int)((var3 ^ 18874415370813L) >>> 32);
      int var8 = (int)((var3 ^ 18874415370813L) << 32 >>> 56);
      int var9 = (int)(var5 << 40 >>> 40);
      if (KnifeCamos.find(var0, var2) == null) {
         return get(var0, var1);
      } else {
         if (D[var0] == null) {
            KnifeModels.Model var10 = U(var7, (byte)var8, var0, 4, var9);
            D[var0] = new KnifeModels.Model(var10.C.undercoat(), var10.H == null ? null : var10.H.undercoat(), var10.O == null ? null : var10.O.undercoat());
         }

         return D[var0];
      }
   }

   private static KnifeModels.Model U(int var0, byte var1, int var2, int var3, int var4) {
      long var5 = 39828449341497L;
      long var7 = var5 ^ 73070620489494L;
      int var9 = (int)((var5 ^ 96345044408300L) >>> 32);
      int var10 = (int)((var5 ^ 96345044408300L) << 32 >>> 48);
      int var12 = (int)((var5 ^ 96345044408300L) << 48 >>> 48);
      KnifeBuilder.TypeB var13 = new KnifeBuilder.TypeB();

      int var14 = switch (var3) {
         case 1 -> -13354164;
         case 2 -> -8230966;
         case 3 -> -2708905;
         default -> -6508865;
      };
      int var11 = 9273;

      int var15 = switch (var3) {
         case 1 -> -7035204;
         case 2 -> -4086278;
         case 3 -> -7520;
         default -> -2036235;
      };
      var11 = 9646137;

      int var16 = switch (var3) {
         case 1 -> -8876634;
         case 2 -> -4290574;
         case 3 -> -1654931;
         default -> {
            var11 = 9646137;
            if (var2 == 0) {
               yield -3782559;
            } else {
               int var17 = 72;
               if (var2 == 1) {
                  yield -11024702;
               } else {
                  var17 = 9646137;
                  yield var2 == 2 ? -7760534 : (var2 == 3 ? -4614805 : -9053971);
               }
            }
         }
      };
      int var32 = -14538958;
      int var18 = 9273;
      if (var3 == 4) {
         var14 = -1;
         var15 = -1;
         var16 = -1;
      }

      var18 = 9646137;
      if (var2 == 0) {
         float[][] var19 = new float[][]{
            {0.09F, -0.035F, 0.033F},
            {0.135F, -0.047F, 0.05F},
            {0.18F, -0.046F, 0.071F},
            {0.23F, -0.016F, 0.104F},
            {0.275F, 0.033F, 0.119F},
            {0.31F, 0.082F, 0.128F},
            {0.325F, 0.139F, 0.139F}
         };
         var13.blade(B(var19, var9, var10, (short)var12), 0.01F, var14, var15);
         var13.material(0.15F).grip(0.0F, -0.135F, 0.102F, 0.037F, 0.022F, var32);

         for (int var20 = 0; var20 < 6; var20++) {
            float var21 = var20;
            float var22 = -0.105F + var21 * 0.033F;
            var13.box(-0.007F, var22, 0.0F, 0.06F, 0.009F, 0.046F, var20 % 2 == 0 ? -13354170 : var32);
         }

         var13.material(0.9F).ring(0.004F, -0.181F, 0.041F, 0.009F, var14);

         for (float var23 : new float[]{-0.103F, 0.072F}) {
            short var24 = 72;

            for (int var28 : new int[]{-1, 1}) {
               var24 = 9273;
               var13.bolt(0.0F, var23, var28 * 0.024F, 0.008F, var16);
            }
         }

         var13.box(0.008F, 0.1F, 0.0F, 0.086F, 0.015F, 0.032F, var14);
      } else {
         int var34 = 9273;
         if (var2 == 1) {
            var13.blade(
               new float[][]{{0.103F, -0.021F, 0.021F}, {0.15F, -0.031F, 0.032F}, {0.3F, -0.033F, 0.025F}, {0.375F, -0.007F, 0.019F}, {0.419F, 0.01F, 0.01F}},
               0.009F,
               var14,
               var15
            );

            for (int var58 : new int[]{-1, 1}) {
               var13.bolt(var58 * 0.021F, 0.105F, var58 * 0.012F, 0.01F, var15);
            }

            return new KnifeModels.Model(var13.build(), i(-1, var14, var7, var16), i(1, var14, var7, var16));
         }

         var34 = 9646137;
         if (var2 == 2) {
            var13.blade(
               new float[][]{{0.1F, -0.042F, 0.041F}, {0.16F, -0.046F, 0.046F}, {0.35F, -0.046F, 0.046F}, {0.4F, -0.02F, 0.043F}, {0.485F, 0.043F, 0.043F}},
               0.012F,
               var14,
               var15
            );
            var13.material(0.1F).grip(0.0F, -0.19F, 0.1F, 0.032F, 0.025F, -12565193);

            for (int var37 = 0; var37 < 8; var37++) {
               var13.box(0.0F, -0.165F + var37 * 0.028F, 0.0F, 0.067F, 0.007F, 0.051F, var32);
            }

            var13.material(0.85F).box(0.0F, 0.105F, 0.0F, 0.142F, 0.018F, 0.046F, var14);
            var13.ring(-0.087F, 0.108F, 0.025F, 0.006F, var14);
            var13.box(0.0F, -0.192F, 0.0F, 0.065F, 0.02F, 0.046F, var14);

            for (int var54 : new int[]{-1, 1}) {
               var13.box(-0.007F, 0.256F, var54 * 0.0122F, 0.014F, 0.159F, 0.001F, -11181452);

               for (int var60 = 0; var60 < 7; var60++) {
                  float var64 = var60;
                  var13.box(-0.047F, 0.178F + var64 * 0.021F, 0.0F, 0.007F, 0.01F, 0.008F, var15);
               }
            }
         } else if (var2 == 3) {
            float[][] var39 = new float[][]{
               {0.09F, -0.033F, 0.032F}, {0.15F, -0.035F, 0.041F}, {0.29F, -0.033F, 0.04F}, {0.358F, -0.01F, 0.025F}, {0.416F, 0.024F, 0.024F}
            };
            var13.blade(var39, 0.012F, var3 == 0 ? -10983819 : var14, var15);
            var13.material(0.08F).grip(0.0F, -0.166F, 0.088F, 0.029F, 0.024F, -13422548);

            for (int var44 = 0; var44 < 10; var44++) {
               float var49 = -0.148F + var44 * 0.023F;
               var13.box(0.0F, var49, 0.0F, 0.06F, 0.012F, 0.049F, var44 % 2 == 0 ? var16 : KnifeBuilder.tint(var16, 0.64F));

               for (int var68 : new int[]{-1, 1}) {
                  float var69 = var68 * 0.027F;
                  var13.box(var69, -0.148F + var44 * 0.023F, 0.0F, 0.005F, 0.014F, 0.04F, var32);
               }
            }

            var13.material(0.8F).box(0.0F, 0.1F, 0.0F, 0.109F, 0.017F, 0.042F, var14);
            var13.box(0.0F, -0.173F, 0.0F, 0.058F, 0.023F, 0.044F, var14);
            var13.ring(0.0F, -0.195F, 0.014F, 0.004F, var14);

            for (int var66 : new int[]{-1, 1}) {
               var13.box(-0.01F, 0.24F, var66 * 0.0123F, 0.009F, 0.116F, 0.001F, -14471621);
            }
         } else {
            int var40 = var3 == 0 ? -10438183 : var14;
            float[][] var45 = new float[][]{
               {0.09F, -0.024F, 0.024F},
               {0.16F, -0.077F, 0.06F},
               {0.213F, -0.053F, 0.086F},
               {0.26F, -0.06F, 0.039F},
               {0.335F, -0.02F, 0.05F},
               {0.43F, 0.019F, 0.019F}
            };
            var13.blade(var45, 0.022F, var40, var3 == 0 ? -3213313 : var15);
            var13.material(0.25F).grip(0.0F, -0.175F, 0.073F, 0.025F, 0.023F, -14077109);

            for (int var51 = 0; var51 < 6; var51++) {
               var13.box(0.0F, -0.147F + var51 * 0.034F, 0.0F, 0.051F, 0.011F, 0.049F, -11049595);
            }

            var13.material(0.85F).blade(new float[][]{{0.066F, -0.1F, 0.1F}, {0.092F, -0.083F, 0.083F}, {0.12F, -0.042F, 0.042F}}, 0.02F, var16, var15);
            var13.blade(new float[][]{{-0.225F, 0.0F, 0.0F}, {-0.184F, -0.034F, 0.034F}, {-0.152F, -0.015F, 0.015F}}, 0.024F, var40, var15);

            for (int var67 : new int[]{-1, 1}) {
               var13.bolt(0.0F, 0.087F, var67 * 0.021F, 0.02F, var14);
               var13.bolt(0.0F, 0.087F, var67 * 0.022F, 0.01F, var16);
            }
         }
      }

      return new KnifeModels.Model(var13.build(), null, null);
   }

   private static float[][] B(float[][] var0, int var1, int var2, short var3) {
      long var4 = 127102522681301L;
      float[][] var6 = new float[(var0.length - 1) * 6 + 1][3];

      for (int var7 = 0; var7 < var6.length; var7++) {
         int var8 = Math.min(var0.length - 2, var7 / 6);
         float var9 = (var7 - var8 * 6) / 6.0F;

         for (int var10 = 0; var10 < 3; var10++) {
            float var11 = var0[Math.max(0, var8 - 1)][var10];
            float var12 = var0[var8][var10];
            float var13 = var0[var8 + 1][var10];
            float var14 = var0[Math.min(var0.length - 1, var8 + 2)][var10];
            var6[var7][var10] = 0.5F
               * (
                  2.0F * var12
                     + (-var11 + var13) * var9
                     + (2.0F * var11 - 5.0F * var12 + 4.0F * var13 - var14) * var9 * var9
                     + (-var11 + 3.0F * var12 - 3.0F * var13 + var14) * var9 * var9 * var9
               );
         }

         var6[var7][2] = Math.max(var6[var7][1], var6[var7][2]);
      }

      return var6;
   }

   private static KnifeBuilder i(int var0, int var1, long var2, int var4) {
      KnifeBuilder.TypeB var5 = new KnifeBuilder.TypeB();
      float var6 = var0 * 0.026F;
      var5.material(0.8F).grip(var6, -0.204F, 0.108F, 0.021F, 0.018F, var1);
      var5.material(0.3F).box(var6, -0.046F, 0.0F, 0.032F, 0.239F, 0.038F, -14143175);
      int var7 = 0;

      while (var7 < 6) {
         int[] var8 = new int[]{-1, 1};
         long var9 = 112478163011374L;
         int var24 = var9 < 0L ? -1 : 1;
         int[] var12 = var8;
         int var13 = var8.length;
         int var14 = 0;

         while (var14 < var13) {
            int var15 = var12[var14];
            long var16 = 112478163011374L;
            int var19 = var16 < 0L ? -1 : 1;
            var5.material(0.7F).bolt(var6, -0.153F + var7 * 0.039F, var15 * 0.0195F, 0.008F, var4);
            var5.bolt(var6, -0.153F + var7 * 0.039F, var15 * 0.02F, 0.0048F, -15722205);
            var14++;
            long var19x = 112478163011374L;
            int var19x = var19x < 0L ? -1 : 1;
         }

         var7++;
         long var26 = 112478163011375L;
         int var161 = var26 < 0L ? -1 : 1;
      }

      int[] var22 = new int[]{-1, 1};
      int var23 = var22.length;
      int var10 = 0;

      while (var10 < var23) {
         int var11 = var22[var10];
         long var25 = 112478163011375L;
         int var28 = var25 < 0L ? -1 : 1;
         var5.bolt(var6, 0.101F, var11 * 0.019F, 0.01F, var4);
         var10++;
         long var27 = 112478163011374L;
         int var30 = var27 < 0L ? -1 : 1;
      }

      long var24 = 112478163011374L;
      int var26 = var24 < 0L ? -1 : 1;
      var5.box(var6, -0.204F, 0.0F, 0.038F, 0.015F, 0.038F, var4);
      if (var0 == 1) {
         var5.box(var6, -0.22F, 0.0F, 0.011F, 0.026F, 0.015F, var1);
      }

      return var5.build();
   }

   public record Model(KnifeBuilder C, KnifeBuilder H, KnifeBuilder O) {
      public KnifeBuilder posed(int var1, KnifeRenderer7.Data var2) {
         return this.posed(KnifeGeometry2.inHand(var1, var2, new Matrix4f()), var2.handleA(), var2.handleB());
      }

      public KnifeBuilder posed(Matrix4f var1, float var2, float var3) {
         KnifeBuilder.TypeB var4 = new KnifeBuilder.TypeB();
         var4.append(this.C, var1);
         if (this.H != null) {
            var4.append(this.H, KnifeModels.hinge(new Matrix4f(var1), -1, var2));
         }

         if (this.O != null) {
            var4.append(this.O, KnifeModels.hinge(new Matrix4f(var1), 1, var3));
         }

         return var4.build();
      }

      public KnifeBuilder blade() {
         return this.C;
      }

      public KnifeBuilder handleA() {
         return this.H;
      }

      public KnifeBuilder handleB() {
         return this.O;
      }

      public KnifeBuilder knifeMaterialMeshState1() {
         return this.C;
      }

      public KnifeBuilder knifeMaterialMeshState2() {
         return this.H;
      }

      public KnifeBuilder knifeMaterialMeshState3() {
         return this.O;
      }
   }
}
