// Sourced By DevFFNightmare
package dev.purxiy.modules.cyberspace;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_2680;
import net.minecraft.class_638;
import net.minecraft.class_2338.class_2339;

final class CyberspaceUpdater {
   static final int o = 64;
   static final int k = 60;
   private static final int p = 50;
   private static final int R = 46;
   private static final double B = 3.0;
   private static final double E = 28.0;
   private static final double M = 50.0;
   private static final float X = 0.55F;
   private static final float i = 11.0F;
   static final List<CyberspaceUpdater.TypeQ> m = new ArrayList<>();
   static final List<CyberspaceUpdater.TypeS> b = new ArrayList<>();
   private static float F;
   private static float A;
   private static final class_2339 l = new class_2339();



   private CyberspaceUpdater() {
   }

   static void clear() {
      m.clear();
      b.clear();
   }

   static void update(class_638 var0, double var1, double var3, double var5, float var7, float var8, boolean var9) {
      long var10 = 133413712158096L;
      long var12 = var10 ^ 90228081430084L;
      long var14 = var10 ^ 62343373112601L;
      long var16 = var10 ^ 113036004583930L;

      for (int var18 = m.size() - 1; var18 >= 0; var18--) {
         CyberspaceUpdater.TypeQ var19 = m.get(var18);
         if (!p(var0, var19, var8, var16) || H(var19.m, var19.T, var19.j, var1, var3, var5) > 2500.0) {
            m.remove(var18);
         }
      }

      for (int var20 = b.size() - 1; var20 >= 0; var20--) {
         CyberspaceUpdater.TypeS var22 = b.get(var20);
         var22.H += var8;
         var22.W = var22.W + var22.B * var8;
         var22.r = var22.r + var22.V * var8;
         if (var22.H >= var22.q || H(var22.g, var22.W, var22.U, var1, var3, var5) > 2500.0) {
            b.remove(var20);
         }
      }

      if (!var9) {
         F = 0.0F;
         A = 0.0F;
      } else {
         ThreadLocalRandom var21 = ThreadLocalRandom.current();
         F = Math.min(F + var8 * 16.0F, 3.0F);

         while (F >= 1.0F && m.size() < 50) {
            F--;
            l(var0, var1, var3, var5, var7, var21, var12);
         }

         A = Math.min(A + var8 * 18.0F, 3.0F);

         while (A >= 1.0F && b.size() < 46) {
            A--;
            T(var0, var1, var14, var3, var5, var21);
         }
      }
   }

   static void burst(class_638 var0, double var1, double var3, double var5) {
      long var7 = 51932752688103L;
      long var9 = var7 ^ 69963298120788L;
      long var11 = var7 ^ 94537706442263L;
      int var13 = (int)((var7 ^ 94537706442263L) >>> 48);
      int var14 = (int)((var7 ^ 94537706442263L) << 16 >>> 32);
      int var15 = (int)(var11 << 48 >>> 48);
      ThreadLocalRandom var16 = ThreadLocalRandom.current();
      int var17 = (int)Math.floor(var1);
      int var18 = (int)Math.floor(var3);
      int var19 = (int)Math.floor(var5);
      int[][] var20 = new int[][]{{1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}, {0, 1, 0}};

      for (int[] var24 : var20) {
         for (int var25 = 0; var25 < 2 && m.size() < 64; var25++) {
            int var26 = var24[0] == 0 ? var16.nextInt(-2, 3) : 0;
            int var27 = var24[2] == 0 ? var16.nextInt(-2, 3) : 0;
            int var28 = var24[1] == 0 ? var16.nextInt(0, 3) : 0;
            int var29 = var17 + var26 + var24[0];
            int var30 = var18 + var28 + var24[1];
            int var31 = var19 + var27 + var24[2];
            if (M(var0, var29, var30, var31, (char)var13, var14, var15)) {
               CyberspaceUpdater.TypeQ var32 = e(var29 + 0.5, var30 + 0.5, var31 + 0.5, var24[0], var24[1], var9, var24[2], var16);
               var32.C = 20.0F + var16.nextFloat() * 8.0F;
               var32.H = 18.0 + var16.nextDouble() * 24.0;
               m.add(var32);
            }
         }
      }
   }

   private static void l(class_638 var0, double var1, double var3, double var5, float var7, ThreadLocalRandom var8, long var9) {
      long var11 = 18253892982707L;
      long var13 = 101574947250144L;
      long var15 = 134968528322032L;
      byte var17 = 0;
      int var18 = 2059456303;
      int var19 = (int)(var15 << 48 >>> 48);
      CyberspaceRenderer2.ensureSourceClassInitialized();

      for (int var20 = 0; var20 < 4; var20++) {
         long var21 = 47661526143955L;
         if (47661526143955L == 0L) {
            boolean var10000 = false;
         } else {
            int var61 = var21 < 0L ? -1 : 1;
         }

         double var24 = var8.nextInt(4) != 0 ? var7 + (var8.nextDouble() - 0.5) * 2.2 : var8.nextDouble() * Math.PI * 2.0;
         double var26 = 3.0 + 25.0 * Math.sqrt(var8.nextDouble());
         int var28 = (int)Math.floor(var1 + Math.cos(var24) * var26);
         int var29 = (int)Math.floor(var5 + Math.sin(var24) * var26);
         int var30 = H(var0, var13, var28, (int)Math.floor(var3) + 10, var29, 28);
         long var31 = 47661526143955L;
         if (47661526143955L == 0L) {
            boolean var62 = false;
         } else {
            int var63 = var31 < 0L ? -1 : 1;
         }

         int var33 = var30 == Integer.MIN_VALUE ? (int)Math.floor(var3) : var30 + 1;
         long var34 = 47661526143956L;
         if (47661526143956L == 0L) {
            boolean var64 = false;
         } else {
            int var65 = var34 < 0L ? -1 : 1;
         }

         long var36 = 47661526143956L;
         if (47661526143956L == 0L) {
            boolean var66 = false;
         } else {
            int var67 = var36 < 0L ? -1 : 1;
         }

         if (var30 != Integer.MIN_VALUE && var8.nextInt(8) == 0 && M(var0, var28, var33 + 1, var29, (char)var17, var18, var19)) {
            CyberspaceUpdater.TypeQ var56 = e(var28 + 0.5, var33 + 0.5, var29 + 0.5, 0, 1, var11, 0, var8);
            var56.C = 26.0F + var8.nextFloat() * 14.0F;
            var56.H = 60.0 + var8.nextDouble() * 40.0;
            var56.l *= 1.25F;
            m.add(var56);
            return;
         }

         int[] var38 = new int[]{0, 0, 1, 1, 2, 3, 5, 8};
         int var39 = var33 + var38[var8.nextInt(var38.length)];
         if (!M(var0, var28, var39, var29, (char)var17, var18, var19)) {
            long var40 = 47661526143956L;
            if (47661526143956L == 0L) {
               boolean var68 = false;
            } else {
               int var69 = var40 < 0L ? -1 : 1;
            }
         } else {
            byte var57 = 0;
            byte var41 = 0;
            byte var42 = 0;
            int var43 = var8.nextInt(10);
            long var44 = 47661526143955L;
            if (47661526143955L == 0L) {
               boolean var70 = false;
            } else {
               int var71 = var44 < 0L ? -1 : 1;
            }

            long var46 = 47661526143956L;
            if (47661526143956L == 0L) {
               boolean var72 = false;
            } else {
               int var73 = var46 < 0L ? -1 : 1;
            }

            if (var43 < 4) {
               var57 = (byte)(var8.nextBoolean() ? 1 : -1);
               long var48 = 47661526143956L;
               if (47661526143956L == 0L) {
                  boolean var74 = false;
               } else {
                  int var75 = var48 < 0L ? -1 : 1;
               }
            } else {
               long var58 = 47661526143955L;
               if (47661526143955L == 0L) {
                  boolean var76 = false;
               } else {
                  int var77 = var58 < 0L ? -1 : 1;
               }

               long var50 = 47661526143955L;
               if (47661526143955L == 0L) {
                  boolean var78 = false;
               } else {
                  int var79 = var50 < 0L ? -1 : 1;
               }

               if (var43 < 8) {
                  var42 = (byte)(var8.nextBoolean() ? 1 : -1);
                  long var52 = 47661526143955L;
                  if (47661526143955L == 0L) {
                     boolean var80 = false;
                  } else {
                     int var81 = var52 < 0L ? -1 : 1;
                  }
               } else {
                  long var60 = 47661526143955L;
                  if (47661526143955L == 0L) {
                     boolean var82 = false;
                  } else {
                     int var83 = var60 < 0L ? -1 : 1;
                  }

                  var41 = (byte)(var43 == 8 ? 1 : -1);
               }
            }

            if (M(var0, var28 + var57, var39 + var41, var29 + var42, (char)var17, var18, var19)) {
               m.add(e(var28 + 0.5, var39 + 0.5, var29 + 0.5, var57, var41, var11, var42, var8));
               return;
            }

            long var59 = 47661526143956L;
            if (47661526143956L == 0L) {
               boolean var84 = false;
            } else {
               int var85 = var59 < 0L ? -1 : 1;
            }
         }
      }

      long var55 = 47661526143956L;
      if (47661526143956L == 0L) {
         boolean var86 = false;
      } else {
         int var87 = var55 < 0L ? -1 : 1;
      }
   }

   private static CyberspaceUpdater.TypeQ e(double var0, double var2, double var4, int var6, int var7, long var8, int var10, ThreadLocalRandom var11) {
      CyberspaceUpdater.TypeQ var12 = new CyberspaceUpdater.TypeQ();
      CyberspaceRenderer2.ensureSourceClassInitialized();
      var12.m = var0;
      var12.T = var2;
      var12.j = var4;
      long var13 = 18253892982707L;
      if (18253892982707L == 0L) {
         boolean var10000 = false;
      } else {
         int var16 = var13 < 0L ? -1 : 1;
      }

      var12.G = var6;
      var12.Y = var7;
      var12.i = var10;
      var12.C = 6.0F + var11.nextFloat() * 10.0F;
      var12.H = 14.0 + var11.nextDouble() * 40.0;
      var12.l = 0.08F + var11.nextFloat() * 0.05F;
      var12.N = var11.nextInt(7) == 0 ? 1.0F : 0.0F;
      var12.R[0] = var0;
      var12.R[1] = var2;
      var12.R[2] = var4;
      var12.z = 1;
      return var12;
   }

   private static boolean p(class_638 var0, CyberspaceUpdater.TypeQ var1, float var2, long var3) {
      long var5 = 23007114196583L;
      long var7 = 134968528322032L;
      byte var9 = 0;
      int var10 = 2059456303;
      int var11 = (int)(var7 << 48 >>> 48);
      CyberspaceRenderer2.ensureSourceClassInitialized();
      var1.w += var2;
      float var12 = Math.min(var1.C * 0.55F, 11.0F);
      long var13 = 34741756474473L;
      if (34741756474473L == 0L) {
         boolean var10000 = false;
      } else {
         int var62 = var13 < 0L ? -1 : 1;
      }

      if (var1.s >= 0.0F) {
         var1.s += var2;
         var1.Z = Math.max(0.0F, var1.Z - var1.C * var2);
         return var1.Z > 0.0F || var1.s < 0.15F;
      } else {
         var1.Z = Math.min(var12, var1.Z + var1.C * var2);
         double var16 = var1.C * var2;
         ThreadLocalRandom var18 = ThreadLocalRandom.current();
         int var19 = 0;

         while (var16 > 1.0E-6) {
            int var20 = var19;
            long var21 = 34741756474474L;
            if (34741756474474L == 0L) {
               boolean var63 = false;
            } else {
               int var64 = var21 < 0L ? -1 : 1;
            }

            long var24 = 34741756474474L;
            if (34741756474474L == 0L) {
               boolean var65 = false;
            } else {
               int var66 = var24 < 0L ? -1 : 1;
            }

            var19++;
            if (var20 >= 16) {
               break;
            }

            long var26 = 34741756474473L;
            if (34741756474473L == 0L) {
               boolean var67 = false;
            } else {
               int var68 = var26 < 0L ? -1 : 1;
            }

            double var28 = var1.G != 0 ? var1.m : (var1.Y != 0 ? var1.T : var1.j);
            int var30 = var1.G + var1.Y + var1.i;
            double var31 = var30 > 0 ? Math.floor(var28 - 0.5 + 1.0E-6) + 1.5 : Math.ceil(var28 - 0.5 - 1.0E-6) - 0.5;
            double var33 = Math.abs(var31 - var28);
            double var35;
            var20 = (var35 = var33 - var16) == 0.0 ? 0 : (var35 < 0.0 ? -1 : 1);
            long var37 = 34741756474474L;
            if (34741756474474L == 0L) {
               boolean var69 = false;
            } else {
               int var70 = var37 < 0L ? -1 : 1;
            }

            if (var20 <= 0) {
               long var59 = 34741756474473L;
               if (34741756474473L == 0L) {
                  boolean var75 = false;
               } else {
                  int var76 = var59 < 0L ? -1 : 1;
               }

               j(var1, var33 * var30);
               var1.H -= var33;
               var16 -= var33;
               double var43;
               var20 = (var43 = var1.H - 0.0) == 0.0 ? 0 : (var43 < 0.0 ? -1 : 1);
               long var45 = 34741756474473L;
               if (34741756474473L == 0L) {
                  boolean var77 = false;
               } else {
                  int var78 = var45 < 0L ? -1 : 1;
               }

               if (var20 <= 0) {
                  var1.s = 0.0F;
                  return true;
               }

               int var48 = (int)Math.floor(var1.m);
               int var49 = (int)Math.floor(var1.T);
               int var50 = (int)Math.floor(var1.j);
               boolean var51 = !M(var0, var48 + var1.G, var49 + var1.Y, var50 + var1.i, (char)var9, var10, var11);
               if (!var51) {
                  float var52;
                  var20 = (var52 = var18.nextFloat() - 0.14F) == 0.0F ? 0 : (var52 < 0.0F ? -1 : 1);
                  long var53 = 34741756474474L;
                  if (34741756474474L == 0L) {
                     boolean var79 = false;
                  } else {
                     int var80 = var53 < 0L ? -1 : 1;
                  }

                  if (var20 >= 0) {
                     continue;
                  }
               }

               boolean var60 = s(var0, var1, var48, var5, var49, var50, var18);
               long var61 = 34741756474474L;
               if (34741756474474L == 0L) {
                  boolean var81 = false;
               } else {
                  int var82 = var61 < 0L ? -1 : 1;
               }

               if (!var60 && var51) {
                  var1.s = 0.0F;
                  return true;
               }
            } else {
               double var40 = var16 * var30;
               long var42 = 34741756474473L;
               if (34741756474473L == 0L) {
                  boolean var71 = false;
               } else {
                  int var72 = var42 < 0L ? -1 : 1;
               }

               j(var1, var40);
               var1.H -= var16;
               break;
            }
         }

         long var56 = 34741756474474L;
         if (34741756474474L == 0L) {
            boolean var73 = false;
         } else {
            int var74 = var56 < 0L ? -1 : 1;
         }

         return true;
      }
   }

   private static void j(CyberspaceUpdater.TypeQ var0, double var1) {
      var0.m = var0.m + var0.G * var1;
      var0.T = var0.T + var0.Y * var1;
      var0.j = var0.j + var0.i * var1;
   }

   private static boolean s(class_638 var0, CyberspaceUpdater.TypeQ var1, int var2, long var3, int var5, int var6, ThreadLocalRandom var7) {
      long var8 = 134968528322032L;
      byte var10 = 0;
      int var11 = 2059456303;
      int var12 = (int)(var8 << 48 >>> 48);
      CyberspaceRenderer2.ensureSourceClassInitialized();
      int[][] var13 = new int[4][];
      int var14 = 0;
      int[][] var15 = new int[][]{{1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0}};
      int[][] var16 = var15;
      int var17 = var15.length;

      for (int var18 = 0; var18 < var17; var18++) {
         long var19 = 23007114196583L;
         if (23007114196583L == 0L) {
            boolean var10000 = false;
         } else {
            int var40 = var19 < 0L ? -1 : 1;
         }

         int[] var22 = var16[var18];
         int var23 = var22[0];
         long var24 = 23007114196582L;
         if (23007114196582L == 0L) {
            boolean var41 = false;
         } else {
            int var42 = var24 < 0L ? -1 : 1;
         }

         boolean var27 = var23 * var1.G + var22[1] * var1.Y + var22[2] * var1.i == 0;
         long var28 = 23007114196582L;
         if (23007114196582L == 0L) {
            boolean var43 = false;
         } else {
            int var44 = var28 < 0L ? -1 : 1;
         }

         long var31 = 23007114196583L;
         if (23007114196583L == 0L) {
            boolean var45 = false;
         } else {
            int var46 = var31 < 0L ? -1 : 1;
         }

         if (var27) {
            if (var22[1] != 0) {
               var23 = var7.nextInt(3);
               long var34 = 23007114196582L;
               if (23007114196582L == 0L) {
                  boolean var47 = false;
               } else {
                  int var48 = var34 < 0L ? -1 : 1;
               }

               if (var23 != 0) {
                  continue;
               }
            }

            boolean var39 = M(var0, var2 + var22[0], var5 + var22[1], var6 + var22[2], (char)var10, var11, var12);
            long var35 = 23007114196582L;
            if (23007114196582L == 0L) {
               boolean var49 = false;
            } else {
               int var50 = var35 < 0L ? -1 : 1;
            }

            if (var39 && var14 < 4) {
               var13[var14++] = var22;
            }
         }
      }

      if (var14 == 0) {
         return false;
      } else {
         int[] var37 = var13[var7.nextInt(var14)];
         var1.G = var37[0];
         var1.Y = var37[1];
         var1.i = var37[2];
         System.arraycopy(var1.R, 0, var1.R, 3, 6);
         var1.R[0] = var2 + 0.5;
         var1.R[1] = var5 + 0.5;
         var1.R[2] = var6 + 0.5;
         var1.z = Math.min(var1.z + 1, 3);
         return true;
      }
   }

   private static void T(class_638 var0, double var1, long var3, double var5, double var7, ThreadLocalRandom var9) {
      long var10 = 101574947250144L;
      CyberspaceRenderer2.ensureSourceClassInitialized();

      for (int var12 = 0; var12 < 3; var12++) {
         double var13 = var9.nextDouble() * Math.PI * 2.0;
         double var15 = 1.5 + 16.0 * Math.sqrt(var9.nextDouble());
         int var17 = (int)Math.floor(var1 + Math.cos(var13) * var15);
         int var18 = (int)Math.floor(var7 + Math.sin(var13) * var15);
         int var19 = H(var0, var10, var17, (int)Math.floor(var5) + 4, var18, 14);
         long var20 = 72454998333577L;
         if (72454998333577L == 0L) {
            boolean var10000 = false;
         } else {
            int var32 = var20 < 0L ? -1 : 1;
         }

         if (var19 != Integer.MIN_VALUE) {
            CyberspaceUpdater.TypeS var31 = new CyberspaceUpdater.TypeS();
            var31.g = var17 + 0.1 + var9.nextDouble() * 0.8;
            var31.W = var19 + 1.02 + var9.nextDouble() * 0.3;
            var31.U = var18 + 0.1 + var9.nextDouble() * 0.8;
            long var24 = 72454998333576L;
            if (72454998333576L == 0L) {
               boolean var35 = false;
            } else {
               int var36 = var24 < 0L ? -1 : 1;
            }

            var31.B = 0.25F + var9.nextFloat() * 0.55F;
            var31.t = 0.035F + var9.nextFloat() * 0.045F;
            var31.r = var9.nextFloat() * 6.28F;
            float var26 = var9.nextBoolean() ? 1.0F : -1.0F;
            float var27 = var9.nextFloat() * 1.6F;
            long var28 = 72454998333576L;
            if (72454998333576L == 0L) {
               boolean var37 = false;
            } else {
               int var38 = var28 < 0L ? -1 : 1;
            }

            var31.V = var26 * (0.8F + var27);
            var31.q = 2.2F + var9.nextFloat() * 2.8F;
            var31.K = var9.nextInt(5) == 0 ? 1.0F : 0.0F;
            b.add(var31);
            return;
         }

         long var23 = 72454998333577L;
         if (72454998333577L == 0L) {
            boolean var33 = false;
         } else {
            int var34 = var23 < 0L ? -1 : 1;
         }
      }
   }

   private static int H(class_638 var0, long var1, int var3, int var4, int var5, int var6) {
      long var7 = 134968528322032L;
      byte var9 = 0;
      int var10 = 2059456303;
      int var11 = (int)(var7 << 48 >>> 48);
      CyberspaceRenderer2.ensureSourceClassInitialized();

      for (int var12 = 0; var12 < var6; var12++) {
         long var13 = 101574947250143L;
         if (101574947250143L == 0L) {
            boolean var10000 = false;
         } else {
            int var25 = var13 < 0L ? -1 : 1;
         }

         int var16 = var4 - var12;
         long var17 = 101574947250143L;
         if (101574947250143L == 0L) {
            boolean var26 = false;
         } else {
            int var27 = var17 < 0L ? -1 : 1;
         }

         long var20 = 101574947250143L;
         if (101574947250143L == 0L) {
            boolean var28 = false;
         } else {
            int var29 = var20 < 0L ? -1 : 1;
         }

         if (!M(var0, var3, var16, var5, (char)var9, var10, var11) && M(var0, var3, var16 + 1, var5, (char)var9, var10, var11)) {
            return var16;
         }
      }

      long var24 = 101574947250144L;
      if (101574947250144L == 0L) {
         boolean var30 = false;
      } else {
         int var31 = var24 < 0L ? -1 : 1;
      }

      var7 = 3700793383225128890L;
      return Integer.MIN_VALUE;
   }

   private static boolean M(class_638 var0, int var1, int var2, int var3, char var4, int var5, int var6) {
      CyberspaceRenderer2.ensureSourceClassInitialized();
      l.method_10103(var1, var2, var3);
      boolean var7 = var0.method_8477(l);
      if (!var7) {
         return false;
      } else {
         class_2680 var8 = var0.method_8320(l);
         return var8.method_26215() || var8.method_26220(var0, l).method_1110() && var8.method_26227().method_15769();
      }
   }

   private static double H(double var0, double var2, double var4, double var6, double var8, double var10) {
      return (var0 - var6) * (var0 - var6) + (var2 - var8) * (var2 - var8) + (var4 - var10) * (var4 - var10);
   }

   static void trail(CyberspaceUpdater.TypeQ var0, double[] var1) {
      var1[0] = var0.m;
      var1[1] = var0.T;
      var1[2] = var0.j;
      double var2 = var0.Z;
      double var4 = var0.m;
      double var6 = var0.T;
      double var8 = var0.j;
      int var10 = 1;

      for (int var11 = 0; var11 < var0.z && var10 < 3 && var2 > 0.0; var11++) {
         double var12 = var0.R[var11 * 3];
         double var14 = var0.R[var11 * 3 + 1];
         double var16 = var0.R[var11 * 3 + 2];
         double var18 = Math.sqrt(H(var12, var14, var16, var4, var6, var8));
         if (var18 >= var2) {
            double var20 = var2 / var18;
            var4 += (var12 - var4) * var20;
            var6 += (var14 - var6) * var20;
            var8 += (var16 - var8) * var20;
            var2 = 0.0;
         } else {
            var4 = var12;
            var6 = var14;
            var8 = var16;
            var2 -= var18;
         }

         var1[var10 * 3] = var4;
         var1[var10 * 3 + 1] = var6;
         var1[var10 * 3 + 2] = var8;
         var10++;
      }

      for (int var22 = var10; var22 < 3; var22++) {
         var1[var22 * 3] = var1[(var10 - 1) * 3];
         var1[var22 * 3 + 1] = var1[(var10 - 1) * 3 + 1];
         var1[var22 * 3 + 2] = var1[(var10 - 1) * 3 + 2];
      }
   }

static final class TypeQ {
      double m;
      double T;
      double j;
      int G;
      int Y;
      int i;
      float C;
      double H;
      final double[] R = new double[9];
      int z;
      float w;
      float s = -1.0F;
      float l;
      float N;
      float Z;
   }

   static final class TypeS {
      double g;
      double W;
      double U;
      float B;
      float t;
      float r;
      float V;
      float H;
      float q;
      float K;
   }
}
