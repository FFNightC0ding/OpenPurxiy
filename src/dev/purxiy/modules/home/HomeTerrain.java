package dev.purxiy.modules.home;

import java.util.SplittableRandom;

final class HomeTerrain {
   static final int A = 9551193;
   static final int h = 7842607;
   static final int C = 8431445;
   static final int b = 6396257;
   static final int X = 11762247;
   static final int l = 4159204;
   static final int u = 2129968;
   static final float r = 63.875F;
   private static final long[] d = new long[]{
      5111624815121048175L,
      7017676808467890739L,
      8985406900022912115L,
      4228500390679495286L,
      7303310789528536684L,
      -4558534688362064503L,
      124468030533812943L,
      5332206731936949373L,
      -3520287841385309572L,
      3445122885991813880L,
      -4460744977921272070L,
      -2571807280097289704L,
      -1064570190794897488L,
      -8154879128890334950L,
      1761079909310984277L,
      1747504378948095848L
   };
   private static final long[] f = new long[]{5381513061195172913L, 7267591156689020305L};
   private static final Long[] g = new Long[2];

   private HomeTerrain() {
   }

   static HomeCurve[] terrain(HomeWorldState var0, HomeBlocks var1) {
      long var2 = 119116916997986L;
      long var4 = var2 ^ 8370971316431L;
      int var6 = (int)((var2 ^ 8370971316431L) >>> 48);
      int var7 = (int)((var2 ^ 8370971316431L) << 16 >>> 48);
      int var8 = (int)(var4 << 32 >>> 32);
      var4 = var2 ^ 17863046030920L;
      int var9 = (int)((var2 ^ 17863046030920L) >>> 48);
      int var10 = (int)((var2 ^ 17863046030920L) << 16 >>> 32);
      int var11 = (int)(var4 << 48 >>> 48);
      long var12 = (var2 ^ 67996074094914L) >>> 32;
      int var14 = (int)((var2 ^ 67996074094914L) << 32 >>> 32);
      HomeCurve var15 = new HomeCurve(48000);
      HomeCurve var16 = new HomeCurve(2000);
      HomeCurve.Listener var17 = var0::solid;
      byte var18 = 64;

      for (int var19 = -var18; var19 < var18; var19++) {
         for (int var20 = -var18; var20 < var18; var20++) {
            int var21 = HomeWorldState.index(var20, var19);
            int var22 = var0.m[var21];
            byte var23 = var0.g[var21];
            var15.face(
               var20,
               var22,
               var19,
               1,
               s((short)var9, var10, var1, (short)var11, var23),
               var23 == 0 ? 9551193 : 16777215,
               false,
               var17,
               255,
               0.0F,
               0.0F,
               0.0F,
               0.0F
            );

            for (int var24 = 2; var24 <= 5; var24++) {
               int var25 = var20 + (var24 == 4 ? -1 : (var24 == 5 ? 1 : 0));
               int var26 = var19 + (var24 == 2 ? -1 : (var24 == 3 ? 1 : 0));
               if (HomeWorldState.inside(var25, var26)) {
                  int var27 = var0.m[HomeWorldState.index(var25, var26)];

                  for (int var28 = var27 + 1; var28 <= var22; var28++) {
                     int var29 = var22 - var28;
                     if (var29 == 0 && var23 == 0) {
                        var15.face(var20, var28, var19, var24, var1.block("grass_block_side"), 16777215, false, var17, 255, 0.0F, 0.0F, 0.0F, 0.0F);
                        var15.face(var20, var28, var19, var24, var1.block("grass_block_side_overlay"), 9551193, false, var17, 255, 0.0F, 0.0F, 0.0F, 0.0F);
                     } else {
                        float[] var30 = var29 == 0
                           ? d(var12, var1, var23, var14)
                           : (var29 < 4 ? var1.block(var23 == 1 ? "sand" : "dirt") : var1.block("stone"));
                        var15.face(var20, var28, var19, var24, var30, 16777215, false, var17, 255, 0.0F, 0.0F, 0.0F, 0.0F);
                     }
                  }
               }
            }

            if (var22 < 63) {
               float[] var32 = var1.block("water_still");
               var16.vertex(var20, 63.875F, var19, var32[0], var32[1], 4159204, 255, 0.0F, 0.0F, 0.0F, 0.0F);
               var16.vertex(var20, 63.875F, var19 + 1, var32[0], var32[3], 4159204, 255, 0.0F, 0.0F, 0.0F, 0.0F);
               var16.vertex(var20 + 1, 63.875F, var19 + 1, var32[2], var32[3], 4159204, 255, 0.0F, 0.0F, 0.0F, 0.0F);
               var16.vertex(var20 + 1, 63.875F, var19, var32[2], var32[1], 4159204, 255, 0.0F, 0.0F, 0.0F, 0.0F);
            }

            Y((short)var6, var15, (short)var7, var0, var1, var20, var19, var21, var22, var8);
         }
      }

      return new HomeCurve[]{var15, var16};
   }

   private static void Y(short var0, HomeCurve var1, short var2, HomeWorldState var3, HomeBlocks var4, int var5, int var6, int var7, int var8, int var9) {
      long var10 = 118519979290541L;
      int var12 = (int)((var10 ^ 22138163222201L) >>> 48);
      long var13 = (var10 ^ 22138163222201L) << 16 >>> 16;
      long var15 = var10 ^ 44046231287853L;
      HomeGuiBridge.ensureSourceClassInitialized();
      byte var17 = var3.W[var7];
      if (var17 != 0) {
         SplittableRandom var18 = new SplittableRandom(var3.l ^ var5 * 73428767L + var6 * 912931L);
         float var19 = (var18.nextFloat() - 0.5F) * 0.4F;
         float var20 = (var18.nextFloat() - 0.5F) * 0.4F;
         float var21 = var5 + var19;
         float var22 = var8 + 1;
         float var23 = var6 + var20;
         int var24 = 356757421;
         switch (var17) {
            case 1:
               var1.cross(var21, var22, var23, 1.0F, var4.block("short_grass"), 9551193, 251, 0.0F);
               break;
            case 2:
               z(var1, var21, var22, var23, var15, var4.block("tall_grass_bottom"), var4.block("tall_grass_top"), 9551193);
               break;
            case 3:
               float[] var25 = var4.block("fern");
               var1.cross(var21, var22, var23, 1.0F, var25, 9551193, 251, 0.0F);
               break;
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            default:
               float[] var26 = var4.block(U((char)var12, var13, var17));
               var1.cross(var21, var22, var23, 1.0F, var26, 16777215, 251, 0.0F);
               break;
            case 11:
               float[] var27 = var4.block("lilac_bottom");
               z(var1, var21, var22, var23, var15, var27, var4.block("lilac_top"), 16777215);
               break;
            case 12:
               float[] var28 = var4.block("rose_bush_bottom");
               float[] var29 = var4.block("rose_bush_top");
               z(var1, var21, var22, var23, var15, var28, var29, 16777215);
               break;
            case 13:
               z(var1, var21, var22, var23, var15, var4.block("peony_bottom"), var4.block("peony_top"), 16777215);
               break;
            case 14:
               int var30 = var3.caneHeight(var5, var6);

               for (int var35 = 0; var35 < var30; var35++) {
                  var1.cross(var5, var22 + var35, var6, 1.0F, var4.block("sugar_cane"), 9551193, 251, 0.0F, var22);
               }

               var24 = 356757421;
               break;
            case 15:
               var1.flat(var5, 63.887F, var6, var4.block("lily_pad"), 2129968, var18.nextInt(4), 255, 0.0F);
               break;
            case 16:
               float var31 = var5;
               float var32 = var6;
               float[] var33 = var4.block("firefly_bush");
               var1.cross(var31, var22, var32, 1.0F, var33, 16777215, 251, 0.0F);
               var1.cross(var5, var22, var6, 1.0F, var4.block("firefly_bush_emissive"), 16777215, 248, 0.0F);
               break;
            case 17:
               var1.cross(var21, var22, var23, 1.0F, var4.block("bush"), 9551193, 251, 0.0F);
         }
      }
   }

   private static void z(HomeCurve var0, float var1, float var2, float var3, long var4, float[] var6, float[] var7, int var8) {
      var0.cross(var1, var2, var3, 1.0F, var6, var8, 251, 0.0F, var2);
      var0.cross(var1, var2 + 1.0F, var3, 1.0F, var7, var8, 251, 0.0F, var2);
   }

   private static String U(char var0, long var1, byte var3) {
      return switch (var3) {
         case 4 -> "poppy";
         case 5 -> "dandelion";
         case 6 -> "cornflower";
         case 7 -> "azure_bluet";
         case 8 -> "oxeye_daisy";
         case 9 -> "allium";
         case 10 -> "blue_orchid";
         default -> "poppy";
      };
   }

   private static float[] s(short var0, int var1, HomeBlocks var2, short var3, byte var4) {
      return switch (var4) {
         case 0 -> var2.block("grass_block_top");
         case 1 -> var2.block("sand");
         case 2 -> var2.block("gravel");
         case 3 -> var2.block("clay");
         case 4 -> var2.block("stone");
         default -> var2.block("dirt");
      };
   }

   private static float[] d(long var0, HomeBlocks var2, byte var3, int var4) {
      long var5 = 89616645459488L;
      long var7 = var5 ^ 50476625483530L;
      int var9 = (int)((var5 ^ 50476625483530L) >>> 48);
      int var10 = (int)((var5 ^ 50476625483530L) << 16 >>> 32);
      int var11 = (int)(var7 << 48 >>> 48);
      return var3 == 0 ? var2.block("dirt") : s((short)var9, var10, var2, (short)var11, var3);
   }

   static HomeCurve forest(HomeWorldState var0, HomeSupport var1, HomeBlocks var2) {
      long var3 = 9079541421970L;
      long var5 = var3 ^ 118870209892022L;
      int var7 = (int)((var3 ^ 118870209892022L) >>> 32);
      int var8 = (int)((var3 ^ 118870209892022L) << 32 >>> 48);
      int var9 = (int)(var5 << 48 >>> 48);
      HomeCurve var10 = new HomeCurve(40000);
      HomeCurve.Listener var11 = (var2x, var3x, var4) -> {
         HomeGuiBridge.ensureSourceClassInitialized();
         long var5x = 95134144605347L;
         int var8x = var5x < 0L ? -1 : 1;
         boolean var8xx = var0.solid(var2x, var3x, var4);
         long var9x = 95134144605348L;
         int var12 = var9x < 0L ? -1 : 1;
         if (var8xx) {
            return true;
         } else {
            HomeSupport.HomeBuffers var12x = var1.M.get(HomeSupport.pack(var2x, var3x, var4));
            if (var12x != null) {
               if (var12x.log()) {
                  return true;
               }

               long var13x = 95134144605347L;
               int var13xx = var13x < 0L ? -1 : 1;
               if (var12x.R == 3) {
                  return true;
               }
            }

            return false;
         }
      };

      for (HomeSupport.HomeGeometry var13 : var1.m) {
         String var14 = wood(var13.x);

         int var15 = switch (HomeTerrain.HomeTerrainMesh.s[var13.x.ordinal()]) {
            case 1, 2 -> 8431445;
            case 3 -> 6396257;
            case 4 -> 16777215;
            default -> 7842607;
         };
         float[] var16 = var2.block(var14 + "_log");
         float[] var17 = var2.block(var14 + "_log_top");
         float[] var18 = var2.block(var14 + "_leaves");

         label118:
         for (HomeSupport.HomeBuffers var20 : var13.p) {
            float var21;
            int var22;
            switch (var20.R) {
               case 3:
                  int var28 = 0;

                  while (true) {
                     if (var28 >= 6) {
                        continue label118;
                     }

                     if (!f(var0, var7, (short)var8, var20, (short)var9, var28)) {
                        var10.face(var20.v, var20.P, var20.X, var28, var18, var15, false, var11, 253, var20.v + 0.5F, var20.P + 0.5F, var20.X + 0.5F, var20.d);
                     }

                     var28++;
                  }
               case 4:
                  var10.cross(var20.v, var20.P, var20.X, 1.0F, var2.block(var14 + "_sapling"), 16777215, 252, var20.d);
                  continue;
               default:
                  var21 = var20.R == 0 ? var20.P : var20.P + 0.5F;
                  var22 = 0;
            }

            for (; var22 < 6; var22++) {
               if (!f(var0, var7, (short)var8, var20, (short)var9, var22)) {
                  boolean var23 = var20.R == 0 && var22 <= 1 || var20.R == 1 && var22 >= 4 || var20.R == 2 && (var22 == 2 || var22 == 3);
                  boolean var24 = !var23 && (var20.R == 1 && var22 <= 3 || var20.R == 2 && var22 >= 4);
                  var10.face(
                     var20.v, var20.P, var20.X, var22, var23 ? var17 : var16, 16777215, var24, var11, 254, var20.v + 0.5F, var21, var20.X + 0.5F, var20.d
                  );
               }
            }
         }
      }

      for (HomeSupport.HomeBuffers var26 : var1.f) {
         SplittableRandom var27 = new SplittableRandom(var1.D ^ var26.v * 73428767L + var26.X * 912931L);
         switch (var26.R) {
            case 5:
               var10.flat(var26.v, var26.P + 0.02F, var26.X, var2.block("pink_petals"), 16777215, var27.nextInt(4), 250, var26.d);
               break;
            case 6:
               var10.flat(var26.v, var26.P + 0.02F, var26.X, var2.block("leaf_litter"), 11762247, var27.nextInt(4), 250, var26.d);
               break;
            default:
               var10.cross(var26.v, var26.P, var26.X, 1.0F, var2.block("fern"), 9551193, 249, var26.d);
         }
      }

      return var10;
   }

   private static boolean f(HomeWorldState var0, int var1, short var2, HomeSupport.HomeBuffers var3, short var4, int var5) {
      HomeGuiBridge.ensureSourceClassInitialized();
      int var6;
      if (var5 == 4) {
         var6 = -1;
      } else {
         var6 = var5 == 5 ? 1 : 0;
      }

      int var7 = var3.v + var6;
      short var8 = -9948;
      if (var5 == 0) {
         var6 = -1;
      } else {
         var8 = 25693;
         var6 = var5 == 1 ? 1 : 0;
      }

      int var9 = var3.P + var6;
      var8 = 25693;
      if (var5 == 2) {
         var6 = -1;
      } else {
         var8 = -9948;
         var6 = var5 == 3 ? 1 : 0;
      }

      int var10 = var3.X + var6;
      return var0.solid(var7, var9, var10);
   }

   static String wood(HomeSupport.HomeFace var0) {
      return switch (HomeTerrain.HomeTerrainMesh.s[var0.ordinal()]) {
         case 1, 2 -> "birch";
         case 3 -> "spruce";
         case 4 -> "cherry";
         default -> "oak";
      };
   }

   private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 5540) & 32767;
      if (g[var3] == null) {
         g[var3] = f[var3] ^ var1;
      }

      return g[var3];
   }

   static class HomeTerrainMesh {
      static final int[] s = new int[HomeSupport.HomeFace.values().length];

      static {
         try {
            s[HomeSupport.HomeFace.t.ordinal()] = 1;
         } catch (NoSuchFieldError var4) {
         }

         try {
            s[HomeSupport.HomeFace.p.ordinal()] = 2;
         } catch (NoSuchFieldError var3) {
         }

         try {
            s[HomeSupport.HomeFace.I.ordinal()] = 3;
         } catch (NoSuchFieldError var2) {
         }

         try {
            s[HomeSupport.HomeFace.j.ordinal()] = 4;
         } catch (NoSuchFieldError var1) {
         }
      }
   }
}
