package dev.purxiy.modules.knife;

import java.util.ArrayList;
import java.util.List;
import org.joml.Vector3f;

public final class KnifeFactory2 {
   public static final String[] B = new String[]{"Katana", "Greatsword", "Scythe"};
   public static final String[] k = new String[]{"NODACHI", "ZWEIHANDER", "REAPER"};
   public static final String[] C = new String[]{
      "Folded steel  ·  a wave of temper along its edge", "Two hands of steel  ·  a long cross guard", "A crescent of steel  ·  on a long ash haft"
   };
   static final float[][] N = new float[][]{
      {0.0F, 0.05F, 0.0F, 0.0F, 0.745F, 0.0F}, {0.0F, 0.06F, 0.0F, 0.0F, 0.86F, 0.0F}, {-0.03F, 0.37F, 0.0F, -0.83F, 0.15F, 0.0F}
   };
   static final float[][] g = new float[][]{{0.065F, 0.215F}, {0.07F, 0.235F}, {0.07F, 0.36F}};
   private static final KnifeModels.Model[][] L = new KnifeModels.Model[B.length][4];

   private KnifeFactory2() {
   }

   public static int index(String var0) {
      for (int var1 = 0; var1 < B.length; var1++) {
         if (B[var1].equals(var0)) {
            return var1;
         }
      }

      return -1;
   }

   public static KnifeModels.Model get(int var0, String var1) {
      long var2 = 36650729957768L;
      long var4 = var2 ^ 58605944664369L;

      byte var6 = switch (var1) {
         case "Obsidian" -> 1;
         case "Doppler" -> 2;
         case "Gold" -> 3;
         default -> 0;
      };
      if (L[var0][var6] == null) {
         L[var0][var6] = new KnifeModels.Model(n(var0, var4, var6), null, null);
      }

      return L[var0][var6];
   }

   static KnifeFactory2.Data palette(int var0) {
      return switch (var0) {
         case 1 -> new KnifeFactory2.Data(-13946820, -6641476, -3549980, -12959924, -15395300, -12894390, -7365712);
         case 2 -> new KnifeFactory2.Data(-9152316, -2768641, -989441, -10795104, -14016696, -3558158, -4222985);
         case 3 -> new KnifeFactory2.Data(-3562929, -3902, -1820, -1919138, -14870766, -1453414, -798852);
         default -> new KnifeFactory2.Data(-6773066, -1642762, -262657, -9608878, -15263714, -1449262, -3892646);
      };
   }

   private static KnifeBuilder n(int var0, long var1, int var3) {
      long var4 = 113499455204723L;
      long var6 = 129067422885991L;
      long var8 = 129265312812187L;
      KnifeUpdater2.ensureSourceClassInitialized();
      KnifeFactory2.Data var10 = palette(var3);
      KnifeBuilder.TypeB var11 = new KnifeBuilder.TypeB();
      switch (var0) {
         case 0:
            R(var4, var11, var10);
            long var12 = 22094037260472L;
            int var17 = var12 < 0L ? -1 : 1;
            break;
         case 1:
            long var15 = 22094037260473L;
            int var16 = var15 < 0L ? -1 : 1;
            k(var11, var8, var10);
            break;
         default:
            F(var11, var10, var6);
      }

      return var11.build();
   }

   private static void R(long var0, KnifeBuilder.TypeB var2, KnifeFactory2.Data var3) {
      long var4 = 119981690699879L;
      byte var6 = 0;
      int var7 = 7151466;
      int var8 = (int)(var4 << 40 >>> 40);
      long var9 = 95540379508343L;
      long var11 = 82450760078677L;
      long var13 = 50419912666802L;
      long var15 = 81309007281422L;
      float var17 = 0.012F;
      float var18 = 0.735F;
      byte var19 = 96;
      KnifeUpdater2.ensureSourceClassInitialized();
      ArrayList var20 = new ArrayList();

      for (int var21 = 0; var21 <= var19; var21++) {
         float var22 = (float)var21 / var19;
         float var23 = var17 + var18 * var22;
         float var24 = -0.04F * (float)Math.pow(var22, 1.7);
         float var25 = 0.0315F - 0.0075F * var22;
         float var26 = var24 + var25 * 0.52F;
         float var27 = var24 - var25 * 0.48F;
         float var28 = 0.072F;
         float var29 = (var23 - (var17 + var18 - var28)) / var28;
         long var30 = 113499455204722L;
         int var33 = var30 < 0L ? -1 : 1;
         long var33x = 113499455204723L;
         int var36 = var33x < 0L ? -1 : 1;
         float var36x;
         int var37 = (var36x = var29 - 0.0F) == 0.0F ? 0 : (var36x < 0.0F ? -1 : 1);
         long var38 = 113499455204723L;
         int var38x = var38 < 0L ? -1 : 1;
         if (var37 > 0) {
            float var41x = (float)Math.sqrt(Math.max(0.0F, 1.0F - var29 * var29));
            long var42 = 113499455204722L;
            int var41x = var42 < 0L ? -1 : 1;
            var26 = var27 + (var26 - var27) * (0.08F + 0.92F * var41x * var41x) * (1.0F - var29 * 0.15F);
            long var45x = 113499455204722L;
            int var43 = var45x < 0L ? -1 : 1;
            if (var21 == var19) {
               var26 = var27 + 6.0E-4F;
            }
         }

         var20.add(new float[]{var23, var27, var26});
      }

      i(var13, var2, var20, var3);
      var2.material(0.75F);
      var2.box(-0.003F, 0.026F, 0.0F, 0.036F, 0.028F, 0.0135F, var3.U);
      long var47 = 113499455204723L;
      int var451 = var47 < 0L ? -1 : 1;
      var2.box(-0.003F, 0.026F, 0.0F, 0.032F, 0.03F, 0.0105F, KnifeBuilder.tint(var3.U, 1.08F));
      var2.material(0.55F);
      var2.box(0.0F, 0.0105F, 0.0F, 0.05F, 0.003F, 0.034F, var3.C);
      k(var2, 0.0045F, 0.0075F, var15, var3);
      var2.box(0.0F, -0.0015F, 0.0F, 0.048F, 0.003F, 0.032F, var3.C);
      var2.material(0.6F);
      p(var2, var9, -0.014F, -0.003F, 0.0205F, 0.0158F, var3.C, 24);
      M(var2, -0.288F, -0.014F, var11, 0.0185F, 0.0145F, var3);
      var2.material(0.6F);
      p(var2, var9, -0.302F, -0.288F, 0.019F, 0.015F, var3.C, 24);
      m(var2, -0.302F, 0.019F, var6, var7, 0.015F, var3.C, var8);
   }

   private static void i(long var0, KnifeBuilder.TypeB var2, List var3, KnifeFactory2.Data var4) {
      int var5 = var3.size();
      KnifeUpdater2.ensureSourceClassInitialized();
      Vector3f[][] var6 = new Vector3f[var5][];
      Vector3f[][] var7 = new Vector3f[var5][];
      int[][] var8 = new int[var5][];

      for (int var9 = 0; var9 < var5; var9++) {
         float[] var10 = (float[])var3.get(var9);
         float var11 = var10[0];
         float var12 = var10[1];
         float var13 = var10[2];
         float var14 = var13 - var12;
         float var15 = (float)var9 / (var5 - 1);
         float var16 = 0.0042F * (1.0F - 0.55F * var15);
         float var17 = var12 + var14 * 0.3F;
         float var18 = 0.36F + 0.07F * (float)Math.sin(var11 * 52.0F) + 0.035F * (float)Math.sin(var11 * 131.0F + 1.3F);
         float var19 = var13 - var14 * var18;
         float var20 = var19 + var14 * 0.035F;
         float[] var21 = new float[]{var12, var12 + var14 * 0.06F, var17, var19, var20, var13};
         float[] var22 = new float[]{0.0F, var16 * 0.78F, var16, var16 * 0.62F, var16 * 0.55F, 0.0F};
         var6[var9] = new Vector3f[var21.length];
         var7[var9] = new Vector3f[var21.length];

         for (int var23 = 0; var23 < var21.length; var23++) {
            var6[var9][var23] = new Vector3f(var21[var23], var11, -var22[var23]);
            var7[var9][var23] = new Vector3f(var21[var23], var11, var22[var23]);
            long var24 = 50419912666802L;
            int var26 = var24 < 0L ? -1 : 1;
         }

         long var38 = 50419912666802L;
         int var25 = var38 < 0L ? -1 : 1;
         var8[var9] = new int[]{KnifeBuilder.tint(var4.X, 0.72F), KnifeBuilder.tint(var4.X, 0.82F), var4.X, KnifeBuilder.tint(var4.X, 1.06F), var4.E, var4.Q};
      }

      long var27 = 50419912666801L;
      int var28 = var27 < 0L ? -1 : 1;

      for (int var28x = 0; var28x < var5 - 1; var28x++) {
         long var30 = 50419912666802L;
         int var351 = var30 < 0L ? -1 : 1;

         for (int var33 = 0; var33 < 5; var33++) {
            long var35x = 50419912666801L;
            int var41 = var35x < 0L ? -1 : 1;
            long var36 = 50419912666801L;
            int var37 = var36 < 0L ? -1 : 1;
            var2.material(var33 >= 3 ? 0.62F : (var33 == 0 ? 0.7F : 0.92F));
            var37 = var8[var28x][var33 + 1];
            var2.quad(var7[var28x][var33], var7[var28x][var33 + 1], var7[var28x + 1][var33 + 1], var7[var28x + 1][var33], var33 == 0 ? var8[var28x][0] : var37);
            var2.quad(var6[var28x][var33 + 1], var6[var28x][var33], var6[var28x + 1][var33], var6[var28x + 1][var33 + 1], var33 == 0 ? var8[var28x][0] : var37);
         }

         long var34 = 50419912666801L;
         int var40 = var34 < 0L ? -1 : 1;
      }

      long var29 = 50419912666802L;
      int var321 = var29 < 0L ? -1 : 1;
      var2.material(0.8F);

      for (int var31 = 0; var31 < 5; var31++) {
         var2.quad(var6[0][var31], var6[0][var31 + 1], var7[0][var31 + 1], var7[0][var31], var4.X);
      }

      long var32x = 50419912666802L;
      int var39 = var32x < 0L ? -1 : 1;
   }

   private static void k(KnifeBuilder.TypeB var0, float var1, float var2, long var3, KnifeFactory2.Data var5) {
      short var6 = 29662;
      byte var7 = 64;
      byte var8 = 7;
      float var9 = 0.0185F;
      float var10 = 0.046F;
      float var11 = 0.00375F;
      boolean[][] var12 = new boolean[var7][var8];
      KnifeUpdater2.ensureSourceClassInitialized();

      for (int var13 = 0; var13 < var7; var13++) {
         float var14 = var13;
         long var15 = 81309007281421L;
         int var53 = var15 < 0L ? -1 : 1;
         float var18 = (var14 + 0.5F) / var7 * (float) Math.PI * 2.0F;

         for (int var19 = 0; var19 < var8; var19++) {
            long var20 = 81309007281422L;
            int var58 = var20 < 0L ? -1 : 1;
            float var23 = var9 + (var10 - var9) * (var19 + 0.5F) / var8;
            float var24 = (float)Math.cos(4.0F * var18 + (Math.PI / 4));
            float var25 = var9 + (var10 - var9) * 0.56F;
            long var26 = 81309007281421L;
            int var26x = var26 < 0L ? -1 : 1;
            boolean var29 = var24 > 0.55F && Math.abs(var23 - var25) < (var10 - var9) * 0.22F * (var24 - 0.45F) / 0.55F;
            var12[var13][var19] = !var29;
         }

         long var54 = 81309007281421L;
         int var20 = var54 < 0L ? -1 : 1;
      }

      long var52 = 81309007281422L;
      int var48 = var52 < 0L ? -1 : 1;

      for (int var16 = 0; var16 < var7; var16++) {
         float var17 = (float)var16 / var7 * (float) Math.PI * 2.0F;
         float var53 = (float)(var16 + 1) / var7 * (float) Math.PI * 2.0F;

         for (int var55 = 0; var55 < var8; var55++) {
            long var57 = 81309007281421L;
            int var58 = var57 < 0L ? -1 : 1;
            var58 = var12[var16][var55 != 0 ? 1 : 0] ? 1 : 0;
            long var61 = 81309007281421L;
            int var631 = var61 < 0L ? -1 : 1;
            if (var58 == 0) {
               long var27 = 81309007281421L;
               int var67 = var27 < 0L ? -1 : 1;
            } else {
               Vector3f[] var62;
               Vector3f[] var28;
               label191: {
                  float var63x = var9 + (var10 - var9) * var55 / var8;
                  float var30 = var9 + (var10 - var9) * (var55 + 1) / var8;
                  var28 = new Vector3f[]{
                     i(var17, var63x, 0.0045F + var11), i(var53, var63x, 0.0045F + var11), i(var53, var30, 0.0045F + var11), i(var17, var30, 0.0045F + var11)
                  };
                  var62 = new Vector3f[]{
                     i(var17, var63x, 0.0045F - var11), i(var53, var63x, 0.0045F - var11), i(var53, var30, 0.0045F - var11), i(var17, var30, 0.0045F - var11)
                  };
                  var58 = var55 + var16;
                  long var31 = 81309007281421L;
                  int var30x = var31 < 0L ? -1 : 1;
                  var58 %= 2;
                  long var34x = 81309007281422L;
                  int var37 = var34x < 0L ? -1 : 1;
                  var37 = var58 == 0 ? var5.C : KnifeBuilder.tint(var5.C, 0.94F);
                  var0.quad(var28[0], var28[1], var28[2], var28[3], var37);
                  Vector3f var38 = var62[0];
                  Vector3f var39 = var62[3];
                  Vector3f var40 = var62[2];
                  Vector3f var41 = var62[1];
                  long var42 = 81309007281422L;
                  int var39x = var42 < 0L ? -1 : 1;
                  var0.quad(var38, var39, var40, var41, KnifeBuilder.tint(var5.C, 0.8F));
                  long var45x = 81309007281422L;
                  int var41x = var45x < 0L ? -1 : 1;
                  if (var55 != var8 - 1) {
                     boolean var48x = var12[var16][var55 + 1];
                     long var49 = 81309007281422L;
                     int var33 = var49 < 0L ? -1 : 1;
                     if (var48x) {
                        break label191;
                     }
                  }

                  Vector3f var68 = var28[3];
                  var39 = var28[2];
                  var40 = var62[2];
                  var41 = var62[3];
                  long var69 = 81309007281421L;
                  int var80 = var69 < 0L ? -1 : 1;
                  var0.quad(var68, var39, var40, var41, var55 == var8 - 1 ? var5.U : KnifeBuilder.tint(var5.C, 0.7F));
               }

               if (var55 == 0 || !var12[var16][var55 - 1]) {
                  var0.quad(var28[0], var62[0], var62[1], var28[1], KnifeBuilder.tint(var5.C, 0.7F));
               }

               long var64 = 81309007281421L;
               int var32 = var64 < 0L ? -1 : 1;
               if (!var12[(var16 + 1) % var7][var55]) {
                  var0.quad(var28[1], var62[1], var62[2], var28[2], KnifeBuilder.tint(var5.C, 0.7F));
               }

               boolean var32x = var12[(var16 + var7 - 1) % var7][var55];
               long var33 = 81309007281421L;
               int var46 = var33 < 0L ? -1 : 1;
               if (!var32x) {
                  var0.quad(var28[0], var28[3], var62[3], var62[0], KnifeBuilder.tint(var5.C, 0.7F));
               }
            }
         }

         long var56 = 81309007281422L;
         int var56x = var56 < 0L ? -1 : 1;
      }
   }

   private static Vector3f i(float var0, float var1, float var2) {
      return new Vector3f((float)Math.cos(var0) * var1 * 1.06F, var2, (float)Math.sin(var0) * var1 * 0.86F);
   }

   private static void p(KnifeBuilder.TypeB var0, long var1, float var3, float var4, float var5, float var6, int var7, int var8) {
      KnifeUpdater2.ensureSourceClassInitialized();

      for (int var9 = 0; var9 < var8; var9++) {
         float var10 = (float)var9 / var8 * (float) Math.PI * 2.0F;
         float var11 = (float)(var9 + 1) / var8 * (float) Math.PI * 2.0F;
         Vector3f var12 = new Vector3f((float)Math.cos(var10) * var5, var3, (float)Math.sin(var10) * var6);
         Vector3f var13 = new Vector3f((float)Math.cos(var11) * var5, var3, (float)Math.sin(var11) * var6);
         Vector3f var14 = new Vector3f(var12.x, var4, var12.z);
         Vector3f var15 = new Vector3f(var13.x, var4, var13.z);
         long var16 = 95540379508343L;
         int var19 = var16 < 0L ? -1 : 1;
         var19 = var9 % 2;
         long var20 = 95540379508342L;
         int var21 = var20 < 0L ? -1 : 1;
         var0.quad(var12, var14, var15, var13, var19 == 0 ? var7 : KnifeBuilder.tint(var7, 0.96F));
      }
   }

   private static void m(KnifeBuilder.TypeB var0, float var1, float var2, byte var3, int var4, float var5, int var6, int var7) {
      KnifeUpdater2.ensureSourceClassInitialized();
      byte var8 = 24;
      Vector3f var9 = new Vector3f(0.0F, var1 - 0.004F, 0.0F);

      for (int var10 = 0; var10 < var8; var10++) {
         float var11 = (float)var10 / var8 * (float) Math.PI * 2.0F;
         float var12 = (float)(var10 + 1) / var8 * (float) Math.PI * 2.0F;
         var0.tri(
            var9,
            new Vector3f((float)Math.cos(var11) * var2, var1, (float)Math.sin(var11) * var5),
            new Vector3f((float)Math.cos(var12) * var2, var1, (float)Math.sin(var12) * var5),
            KnifeBuilder.tint(var6, 0.9F)
         );
      }
   }

   private static void M(KnifeBuilder.TypeB var0, float var1, float var2, long var3, float var5, float var6, KnifeFactory2.Data var7) {
      long var8 = 32268475794470L;
      KnifeUpdater2.ensureSourceClassInitialized();
      byte var10 = 28;
      byte var11 = 64;
      float var12 = var2 - var1;
      Vector3f[][] var13 = new Vector3f[var11 + 1][var10 + 1];
      int[][] var14 = new int[var11 + 1][var10 + 1];

      for (int var15 = 0; var15 <= var11; var15++) {
         long var16 = 82450760078676L;
         int var35 = var16 < 0L ? -1 : 1;
         float var19 = (float)var15 / var11;
         float var20 = var1 + var12 * var19;
         float var21 = 1.0F - 0.07F * (float)Math.sin(Math.PI * var19);

         for (int var22 = 0; var22 <= var10; var22++) {
            float var23 = (float)var22 / var10 * (float) Math.PI * 2.0F;
            long var24 = 82450760078676L;
            int var26 = var24 < 0L ? -1 : 1;
            float var26x = (float)var22 / var10;
            float var27 = S(var26x * 2.0F + var19 * 7.0F);
            float var28 = S(var26x * 2.0F - var19 * 7.0F);
            float var29 = Math.abs(var27 - 0.5F);
            float var30 = Math.abs(var28 - 0.5F);
            float var31 = Math.max(q(0.36F, 0.27F, var29), q(0.36F, 0.27F, var30));
            float var32 = var21 * (1.0F + 0.06F * var31);
            var13[var15][var22] = new Vector3f((float)Math.cos(var23) * var5 * var32, var20, (float)Math.sin(var23) * var6 * var32);
            var14[var15][var22] = z(var7.N, var7.a, var8, var31);
         }

         long var37 = 82450760078677L;
         int var42 = var37 < 0L ? -1 : 1;
      }

      long var33 = 82450760078677L;
      int var18 = var33 < 0L ? -1 : 1;
      var0.material(0.08F);

      for (int var18x = 0; var18x < var11; var18x++) {
         for (int var34 = 0; var34 < var10; var34++) {
            int var17x = z(
               z(var14[var18x][var34], var14[var18x][var34 + 1], var8, 0.5F), z(var14[var18x + 1][var34], var14[var18x + 1][var34 + 1], var8, 0.5F), var8, 0.5F
            );
            long var36 = 82450760078676L;
            int var41 = var36 < 0L ? -1 : 1;
            var0.quad(var13[var18x][var34], var13[var18x + 1][var34], var13[var18x + 1][var34 + 1], var13[var18x][var34 + 1], var17x);
         }

         long var35 = 82450760078677L;
         int var39 = var35 < 0L ? -1 : 1;
      }
   }

   private static void k(KnifeBuilder.TypeB var0, long var1, KnifeFactory2.Data var3) {
      long var4 = 82450760078677L;
      KnifeUpdater2.ensureSourceClassInitialized();
      float[][] var6 = new float[24][];

      for (int var7 = 0; var7 < var6.length; var7++) {
         long var8 = 129265312812187L;
         int var25 = var8 < 0L ? -1 : 1;
         float var11 = (float)var7 / (var6.length - 1);
         float var12 = 0.02F + 0.84F * var11;
         float var13 = 0.032F - 0.008F * var11;
         long var14 = 129265312812187L;
         int var18 = var14 < 0L ? -1 : 1;
         long var16 = 129265312812186L;
         int var17 = var16 < 0L ? -1 : 1;
         if (var11 > 0.9F) {
            var13 *= Math.max(0.03F, (1.0F - var11) / 0.1F);
         }

         var6[var7] = new float[]{var12, -var13, var13};
      }

      var0.material(0.9F);
      long var21 = 129265312812186L;
      int var23 = var21 < 0L ? -1 : 1;
      var0.blade(var6, 0.0055F, var3.X, var3.Q);

      for (int var27 : new int[]{-1, 1}) {
         long var15 = 129265312812187L;
         int var34 = var15 < 0L ? -1 : 1;
         var0.material(0.95F);
         var0.box(0.0F, 0.3F, var27 * 0.0057F, 0.01F, 0.52F, 6.0E-4F, KnifeBuilder.tint(var3.X, 0.62F));
      }

      var0.material(0.6F);
      var0.box(0.0F, 0.035F, 0.0F, 0.05F, 0.03F, 0.012F, var3.C);
      var0.material(0.7F);
      var0.box(0.0F, 0.0F, 0.0F, 0.3F, 0.022F, 0.024F, var3.C);
      int[] var23x = new int[2];
      long var25 = 129265312812186L;
      int var27 = var25 < 0L ? -1 : 1;
      var23x[0] = -1;
      var23x[1] = 1;

      for (int var18 : var23x) {
         long var19 = 129265312812186L;
         int var18x = var19 < 0L ? -1 : 1;
         var0.box(var18 * 0.155F, 0.008F, 0.0F, 0.022F, 0.038F, 0.028F, var3.U);
      }

      var0.box(0.0F, 0.0F, 0.0F, 0.06F, 0.034F, 0.034F, var3.U);
      var0.material(0.1F);
      M(var0, -0.27F, -0.011F, var4, 0.0175F, 0.0155F, new KnifeFactory2.Data(var3.X, var3.Q, var3.E, var3.C, var3.a, KnifeBuilder.tint(var3.a, 1.8F), var3.U));
      long var29 = 129265312812186L;
      int var32 = var29 < 0L ? -1 : 1;
      var0.material(0.75F);
      var0.box(0.0F, -0.292F, 0.0F, 0.042F, 0.042F, 0.042F, var3.U);
      var0.box(0.0F, -0.292F, 0.0F, 0.034F, 0.05F, 0.034F, var3.C);
   }

   private static void F(KnifeBuilder.TypeB var0, KnifeFactory2.Data var1, long var2) {
      long var4 = 119981690699879L;
      byte var6 = 0;
      int var7 = 7151466;
      int var8 = (int)(var4 << 40 >>> 40);
      long var9 = 95540379508343L;
      long var11 = 134584753197323L;
      long var13 = 50241059333308L;
      int var15 = -12899807;
      int var16 = -11913942;
      KnifeUpdater2.ensureSourceClassInitialized();
      int var17 = -14016485;
      var0.material(0.1F);

      for (int var18 = 0; var18 < 26; var18++) {
         float var19 = -0.95F + var18 * 0.05F;
         float var20 = var19 + 0.05F;
         float var21 = 0.0178F - 0.0026F * (var18 / 25.0F);
         long var22 = 129067422885991L;
         int var48 = var22 < 0L ? -1 : 1;
         float var25;
         int var26 = (var25 = var19 - -0.16F) == 0.0F ? 0 : (var25 < 0.0F ? -1 : 1);
         long var27 = 129067422885990L;
         int var30 = var27 < 0L ? -1 : 1;
         boolean var30x = var26 >= 0 && var20 <= 0.02F || var19 >= -0.46F && var20 <= -0.26F;
         if (var30x) {
            long var31 = 129067422885991L;
            int var31x = var31 < 0L ? -1 : 1;
            var26 = var18 % 2;
            long var34 = 129067422885991L;
            int var331 = var34 < 0L ? -1 : 1;
            var26 = var26 == 0 ? var17 : KnifeBuilder.tint(var17, 1.3F);
         } else {
            long var49 = 129067422885990L;
            int var53 = var49 < 0L ? -1 : 1;
            var26 = var18 % 2 == 0 ? var15 : var16;
         }

         float var32 = var30x ? var21 * 1.13F : var21;
         float var33 = var30x ? var21 * 1.13F : var21;
         byte var50 = 14;
         p(var0, var9, var19, var20, var32, var33, var26, var50);
      }

      var0.material(0.6F);
      m(var0, -0.95F, 0.0178F, var6, var7, 0.0178F, var1.C, var8);
      long var40 = 129067422885991L;
      int var35 = var40 < 0L ? -1 : 1;
      p(var0, var9, -0.95F, -0.9F, 0.0195F, 0.0195F, var1.C, 14);
      p(var0, var9, 0.3F, 0.41F, 0.0235F, 0.0235F, var1.C, 18);
      p(var0, var9, 0.335F, 0.365F, 0.029F, 0.029F, var1.U, 18);
      Vector3f var41 = new Vector3f(0.0F, 0.425F, 0.0F);

      for (int var23 = 0; var23 < 18; var23++) {
         float var24 = var23 / 18.0F * (float) Math.PI * 2.0F;
         float var43 = (var23 + 1) / 18.0F * (float) Math.PI * 2.0F;
         long var47 = 129067422885991L;
         int var481 = var47 < 0L ? -1 : 1;
         var0.tri(
            var41,
            new Vector3f((float)Math.cos(var43) * 0.0235F, 0.41F, (float)Math.sin(var43) * 0.0235F),
            new Vector3f((float)Math.cos(var24) * 0.0235F, 0.41F, (float)Math.sin(var24) * 0.0235F),
            var1.C
         );
      }

      long var42 = 129067422885991L;
      int var40x = var42 < 0L ? -1 : 1;
      Vector3f var44 = new Vector3f(0.018F, 0.355F, 0.0F);
      Vector3f var48 = new Vector3f(0.16F, 0.37F, 0.0F);
      float var28 = 0.016F;
      j(var0, var44, var13, var48, var28, var1);
      N(var11, var0, var1);
   }

   private static void j(KnifeBuilder.TypeB var0, Vector3f var1, long var2, Vector3f var4, float var5, KnifeFactory2.Data var6) {
      Vector3f[] var7 = new Vector3f[]{
         new Vector3f(var1).add(0.0F, 0.016F, 0.0F),
         new Vector3f(var1).add(0.0F, 0.0F, 0.016F),
         new Vector3f(var1).add(0.0F, -0.016F, 0.0F),
         new Vector3f(var1).add(0.0F, 0.0F, -0.016F)
      };
      KnifeUpdater2.ensureSourceClassInitialized();
      var0.material(0.85F);

      for (int var8 = 0; var8 < 4; var8++) {
         Vector3f var9 = var7[var8];
         Vector3f var10 = var7[(var8 + 1) % 4];
         long var11 = 50241059333308L;
         int var14 = var11 < 0L ? -1 : 1;
         var14 = var8 % 2;
         long var15 = 50241059333307L;
         int var16 = var15 < 0L ? -1 : 1;
         var0.tri(var4, var9, var10, var14 == 0 ? var6.X : KnifeBuilder.tint(var6.X, 0.8F));
      }

      long var17 = 50241059333308L;
      int var18 = var17 < 0L ? -1 : 1;
   }

   private static void N(long var0, KnifeBuilder.TypeB var2, KnifeFactory2.Data var3) {
      short var4 = 7316;
      float[][] var5 = new float[][]{{-0.012F, 0.36F}, {-0.3F, 0.475F}, {-0.62F, 0.405F}, {-0.83F, 0.15F}};
      long var6 = 6089225545131533263L;
      byte var8 = 64;
      KnifeUpdater2.ensureSourceClassInitialized();
      float[] var9 = new float[]{0.0F, 0.07F, 0.34F, 0.7F, 0.78F, 1.0F};
      float[] var10 = new float[]{0.0F, 0.85F, 1.0F, 0.6F, 0.45F, 0.0F};
      Vector3f[][] var11 = new Vector3f[var8 + 1][var9.length];
      Vector3f[][] var12 = new Vector3f[var8 + 1][var9.length];

      for (int var13 = 0; var13 <= var8; var13++) {
         float var14 = (float)var13 / var8;
         float var15 = 1.0F - var14;
         float var16 = var15 * var15 * var15 * var5[0][0]
            + 3.0F * var15 * var15 * var14 * var5[1][0]
            + 3.0F * var15 * var14 * var14 * var5[2][0]
            + var14 * var14 * var14 * var5[3][0];
         float var17 = var15 * var15 * var15 * var5[0][1]
            + 3.0F * var15 * var15 * var14 * var5[1][1]
            + 3.0F * var15 * var14 * var14 * var5[2][1]
            + var14 * var14 * var14 * var5[3][1];
         float var18 = 3.0F * var15 * var15 * (var5[1][0] - var5[0][0])
            + 6.0F * var15 * var14 * (var5[2][0] - var5[1][0])
            + 3.0F * var14 * var14 * (var5[3][0] - var5[2][0]);
         float var19 = 3.0F * var15 * var15 * (var5[1][1] - var5[0][1])
            + 6.0F * var15 * var14 * (var5[2][1] - var5[1][1])
            + 3.0F * var14 * var14 * (var5[3][1] - var5[2][1]);
         float var20 = (float)Math.sqrt(var18 * var18 + var19 * var19);
         float var21 = var19 / var20;
         float var22 = -var18 / var20;
         float var23 = 0.105F * (float)Math.pow(1.0F - var14, 0.62) + 0.003F;
         float var24 = 0.0072F * (1.0F - 0.6F * var14);

         for (int var25 = 0; var25 < var9.length; var25++) {
            float var26 = var16 - var21 * var23 * var9[var25];
            long var27 = 134584753197323L;
            int var30 = var27 < 0L ? -1 : 1;
            float var30x = var17 - var22 * var23 * var9[var25];
            var11[var13][var25] = new Vector3f(var26, var30x, var24 * var10[var25]);
            var12[var13][var25] = new Vector3f(var26, var30x, -var24 * var10[var25]);
         }

         long var43 = 134584753197323L;
         int var52 = var43 < 0L ? -1 : 1;
      }

      short var32 = 18403;
      long var33 = 134584753197322L;
      int var33x = var33 < 0L ? -1 : 1;
      var6 = 47717968361978020L;
      int[] var34 = new int[]{KnifeBuilder.tint(var3.X, 0.62F), KnifeBuilder.tint(var3.X, 0.82F), var3.X, KnifeBuilder.tint(var3.X, 1.05F), var3.E, var3.Q};

      for (int var35 = 0; var35 < var8; var35++) {
         long var37 = 134584753197323L;
         int var411 = var37 < 0L ? -1 : 1;

         for (int var40 = 0; var40 < var9.length - 1; var40++) {
            long var42 = 134584753197322L;
            int var47 = var42 < 0L ? -1 : 1;
            long var44 = 134584753197322L;
            int var29 = var44 < 0L ? -1 : 1;
            var2.material(var40 >= 3 ? 0.62F : (var40 == 0 ? 0.7F : 0.92F));
            var29 = var34[var40 + 1];
            var2.quad(var11[var35][var40], var11[var35 + 1][var40], var11[var35 + 1][var40 + 1], var11[var35][var40 + 1], var29);
            var2.quad(var12[var35][var40], var12[var35][var40 + 1], var12[var35 + 1][var40 + 1], var12[var35 + 1][var40], var29);
         }

         long var41x = 134584753197322L;
         int var46 = var41x < 0L ? -1 : 1;
      }

      long var36 = 134584753197323L;
      int var38 = var36 < 0L ? -1 : 1;
      var2.material(0.7F);

      for (int var38x = 0; var38x < var9.length - 1; var38x++) {
         var2.quad(var11[0][var38x], var11[0][var38x + 1], var12[0][var38x + 1], var12[0][var38x], var3.X);
      }

      long var39 = 134584753197323L;
      int var45 = var39 < 0L ? -1 : 1;
   }

   private static float S(float var0) {
      return var0 - (float)Math.floor(var0);
   }

   private static float q(float var0, float var1, float var2) {
      float var3 = Math.clamp((0.36F - var2) / 0.09F, 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   private static int z(int var0, int var1, long var2, float var4) {
      int var5 = var0 >> 16 & 0xFF;
      int var6 = var0 >> 8 & 0xFF;
      int var7 = var0 & 0xFF;
      int var8 = var1 >> 16 & 0xFF;
      int var9 = var1 >> 8 & 0xFF;
      int var10 = var1 & 0xFF;
      return 0xFF000000
         | Math.round(var5 + (var8 - var5) * var4) << 16
         | Math.round(var6 + (var9 - var6) * var4) << 8
         | Math.round(var7 + (var10 - var7) * var4);
   }

   public static void blade(int var0, Vector3f var1, Vector3f var2) {
      float[] var3 = N[var0];
      var1.set(var3[0], var3[1], var3[2]);
      var2.set(var3[3], var3[4], var3[5]);
   }

   public static float bladeFrom(int var0) {
      return N[var0][1];
   }

   public static float bladeTo(int var0) {
      return N[var0][4];
   }

   record Data(int X, int Q, int E, int C, int a, int N, int U) {
      public int steel() {
         return this.X;
      }

      public int edge() {
         return this.Q;
      }

      public int temper() {
         return this.E;
      }

      public int fitting() {
         return this.C;
      }

      public int wrap() {
         return this.a;
      }

      public int under() {
         return this.N;
      }

      public int accent() {
         return this.U;
      }

      public int integerState7() {
         return this.X;
      }

      public int integerState15() {
         return this.Q;
      }

      public int integerState40() {
         return this.E;
      }

      public int integerState50() {
         return this.C;
      }

      public int integerState26() {
         return this.a;
      }

      public int integerState28() {
         return this.N;
      }

      public int integerState58() {
         return this.U;
      }
   }
}
