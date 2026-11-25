package dev.purxiy.modules.home;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;

final class HomeSupport {
   static final byte p = 0;
   static final byte w = 1;
   static final byte s = 2;
   static final byte k = 3;
   static final byte o = 4;
   static final byte J = 5;
   static final byte i = 6;
   static final byte U = 7;
   final long D;
   final String O;
   final List<HomeSupport.TypeV> m;
   final Map<Long, HomeSupport.TypeP> M;
   final List<HomeSupport.TypeP> f;
   float j;
   private static final String[] b = new String[4];
   private static final Integer[] d = new Integer[18];

   HomeSupport(HomeSupport5 var1, long var2, String var4) {
      long var5 = 9138222714574L;
      long var7 = var5 ^ 93686933026404L;
      long var9 = var5 ^ 96739867403171L;
      int var11 = (int)((var5 ^ 96739867403171L) >>> 48);
      int var12 = (int)((var5 ^ 96739867403171L) << 16 >>> 32);
      int var13 = (int)(var9 << 48 >>> 48);
      long var14 = var5 ^ 81683108150455L;
      this.m = new ArrayList<>();
      this.M = new HashMap<>();
      this.f = new ArrayList<>();
      this.D = var2;
      this.O = var4;
      SplittableRandom var16 = new SplittableRandom(var2);
      HomeSupport4 var17 = new HomeSupport4(var2 ^ 25214903917L);
      float var18 = var16.nextFloat();

      for (int[] var20 : var1.O) {
         int var21 = var20[0];
         int var22 = var20[1];
         float var23 = (float)Math.sqrt(var21 * var21 + var22 * var22);
         float var24 = var17.fbm(var21 * 0.045F, var22 * 0.045F, 2) * 0.5F + 0.5F;
         if (!(var23 < 17.0F)) {
            float var25 = var23 < 24.0F ? 0.16F : 0.06F + 0.6F * HomeSupport5.smooth(0.38F, 0.68F, var24);
            if (!(var16.nextFloat() > var25)) {
               int var26 = var1.heightAt(var21, var22);
               HomeSupport.TypeX var27 = this.b(var1, var16, var21, var22, var26, var18, var14);
               float var28 = 0.12F + var23 * 0.017F + var16.nextFloat() * 0.38F;
               HomeSupport.TypeV var29 = new HomeSupport.TypeV(var21, var26 + 1, var22, var27, var28);
               this.q(var1, var7, var29, new SplittableRandom(var16.nextLong()));
               if (!var29.p.isEmpty()) {
                  this.m.add(var29);
               }
            }
         }
      }

      for (HomeSupport.TypeV var33 : this.m) {
         this.U((char)var11, var12, (short)var13, var1, var33, new SplittableRandom(var2 ^ var33.C * 341873128712L + var33.w * 132897987541L));
      }

      for (HomeSupport.TypeV var34 : this.m) {
         for (HomeSupport.TypeP var37 : var34.p) {
            this.j = Math.max(this.j, var37.d);
         }
      }

      for (HomeSupport.TypeP var35 : this.f) {
         this.j = Math.max(this.j, var35.d);
      }
   }

   HomeSupport.TypeV plant(HomeSupport5 var1, int var2, int var3, float var4) {
      long var5 = 39539652671248L;
      long var7 = var5 ^ 139142212849594L;
      long var9 = var5 ^ 136638530968189L;
      int var11 = (int)((var5 ^ 136638530968189L) >>> 48);
      int var12 = (int)((var5 ^ 136638530968189L) << 16 >>> 32);
      int var13 = (int)(var9 << 48 >>> 48);
      long var14 = var5 ^ 107700445922665L;
      if (HomeSupport5.inside(var2, var3) && var1.g[HomeSupport5.index(var2, var3)] == 0) {
         for (HomeSupport.TypeV var17 : this.m) {
            int var18 = var17.C - var2;
            int var19 = var17.w - var3;
            if (var18 * var18 + var19 * var19 < 9) {
               return null;
            }
         }

         SplittableRandom var21 = new SplittableRandom(this.D ^ var2 * -7046029254386353131L + var3 * -4417276706812531889L + this.m.size());
         int var22 = var1.heightAt(var2, var3);
         HomeSupport.TypeV var23 = new HomeSupport.TypeV(var2, var22 + 1, var3, this.b(var1, var21, var2, var3, var22, 0.37F, var14), var4);
         this.q(var1, var7, var23, new SplittableRandom(var21.nextLong()));
         if (var23.p.isEmpty()) {
            return null;
         } else {
            this.m.add(var23);
            this.U((char)var11, var12, (short)var13, var1, var23, new SplittableRandom(var21.nextLong()));

            for (HomeSupport.TypeP var20 : var23.p) {
               this.j = Math.max(this.j, var20.d);
            }

            return var23;
         }
      } else {
         return null;
      }
   }

   private HomeSupport.TypeX b(HomeSupport5 var1, SplittableRandom var2, int var3, int var4, int var5, float var6, long var7) {
      HomeSupport2.ensureSourceClassInitialized();
      float var9 = var2.nextFloat();
      String var10 = this.O;
      byte var11 = -1;
      switch (var10.hashCode()) {
         case -1811801630:
            boolean var12 = var10.equals("Spruce");
            long var13 = 72593472352888L;
            int var231 = var13 < 0L ? -1 : 1;
            if (var12) {
               var11 = 3;
            }
            break;
         case 79033:
            if (var10.equals("Oak")) {
               long var22 = 72593472352888L;
               int var27 = var22 < 0L ? -1 : 1;
               var11 = 0;
            }
            break;
         case 64193168:
            if (var10.equals("Birch")) {
               long var21 = 72593472352889L;
               int var26 = var21 < 0L ? -1 : 1;
               var11 = 1;
            }
            break;
         case 2017321401:
            if (var10.equals("Cherry")) {
               long var16 = 72593472352888L;
               int var18 = var16 < 0L ? -1 : 1;
               var11 = 2;
            }
      }

      long var20 = 72593472352888L;
      int var15 = var20 < 0L ? -1 : 1;
      switch (var11) {
         case 0:
            return var9 < 0.16F ? HomeSupport.TypeX.w : HomeSupport.TypeX.E;
         case 1:
            return var9 < 0.1F ? HomeSupport.TypeX.E : (var9 < 0.3F ? HomeSupport.TypeX.p : HomeSupport.TypeX.t);
         case 2:
            return HomeSupport.TypeX.j;
         case 3:
            return var9 < 0.08F ? HomeSupport.TypeX.t : HomeSupport.TypeX.I;
         default:
            long var15x = 72593472352889L;
            int var23 = var15x < 0L ? -1 : 1;
            if (var5 >= 73) {
               long var17 = 72593472352889L;
               int var181 = var17 < 0L ? -1 : 1;
               if (var9 < 0.8F) {
                  return HomeSupport.TypeX.I;
               }
            }

            float var23 = (float)(Math.atan2(var4, var3) / (Math.PI * 2)) + 0.5F + var6 + var1.F.noise(var3 * 0.04F, var4 * 0.04F + 200.0F) * 0.08F;
            int var18 = Math.floorMod((int)Math.floor(var23 * 4.0F), 4);

            return switch (var18) {
               case 0 -> var9 < 0.18F ? HomeSupport.TypeX.w : HomeSupport.TypeX.E;
               case 1 -> var9 < 0.25F ? HomeSupport.TypeX.p : HomeSupport.TypeX.t;
               case 2 -> var9 < 0.85F ? HomeSupport.TypeX.j : HomeSupport.TypeX.t;
               default -> var9 < 0.45F ? HomeSupport.TypeX.E : (var9 < 0.6F ? HomeSupport.TypeX.w : HomeSupport.TypeX.t);
            };
      }
   }

   private void q(HomeSupport5 var1, long var2, HomeSupport.TypeV var4, SplittableRandom var5) {
      long var6 = 9137857707046L;
      byte var8 = 0;
      short var9 = 2127;
      int var10 = (int)(var6 << 32 >>> 32);
      long var11 = 99590931173154L;
      var6 = 71543345560269L;
      short var13 = 16657;
      int var14 = 8106682;
      int var15 = (int)(var6 << 56 >>> 56);
      long var16 = 69076484354393L;
      HomeSupport2.ensureSourceClassInitialized();
      HomeSupport.TypeR var18 = new HomeSupport.TypeR();
      int var19 = var4.x.ordinal();
      long var20 = 102781131700393L;
      int var62 = var20 < 0L ? -1 : 1;
      switch (var19) {
         case 0:
            Q(var13, var14, var18, var5, 4 + var5.nextInt(3), (byte)var15);
            long var23 = 102781131700393L;
            int var60 = var23 < 0L ? -1 : 1;
            break;
         case 1:
            r(var18, var5, var11);
            long var26 = 102781131700394L;
            int var63 = var26 < 0L ? -1 : 1;
            break;
         case 2:
            int var28 = 5 + var5.nextInt(3);
            long var29 = 102781131700393L;
            int var691 = var29 < 0L ? -1 : 1;
            Q(var13, var14, var18, var5, var28, (byte)var15);
            break;
         case 3:
            long var32 = 102781131700394L;
            int var71 = var32 < 0L ? -1 : 1;
            Q(var13, var14, var18, var5, 8 + var5.nextInt(3), (byte)var15);
            break;
         case 4:
            M(var18, var5, var16);
            break;
         case 5:
            E((char)var8, var9, var18, var10, var5);
      }

      HomeSupport.TypeP var62 = new HomeSupport.TypeP(var4.C, var4.d, var4.w, (byte)4, var4);
      var62.d = var4.H;
      var4.p.add(var62);
      float var24 = var4.H + 0.42F;
      float var25 = var24;

      for (int[] var27 : var18.O) {
         int var65 = var4.C + var27[0];
         int var67 = var4.d + var27[1];
         int var30 = var4.w + var27[2];
         long var31 = 102781131700394L;
         int var72 = var31 < 0L ? -1 : 1;
         if (var67 <= var1.heightAt(var65, var30)) {
            long var34 = 102781131700394L;
            int var34x = var34 < 0L ? -1 : 1;
            var19 = var27[0];
            long var36 = 102781131700393L;
            int var36x = var36 < 0L ? -1 : 1;
            if (var19 != 0) {
               continue;
            }

            var19 = var27[2];
            long var39 = 102781131700394L;
            int var84 = var39 < 0L ? -1 : 1;
            if (var19 != 0) {
               long var41 = 102781131700394L;
               int var100 = var41 < 0L ? -1 : 1;
               continue;
            }
         }

         long var73 = pack(var65, var67, var30);
         HomeSupport.TypeP var75 = this.M.get(var73);
         if (var75 != null) {
            long var37 = 102781131700394L;
            int var37x = var37 < 0L ? -1 : 1;
            boolean var81 = var75.log();
            long var40 = 102781131700393L;
            int var40x = var40 < 0L ? -1 : 1;
            if (var81) {
               long var43x = 102781131700394L;
               int var421 = var43x < 0L ? -1 : 1;
               continue;
            }
         }

         if (var75 != null) {
            var75.j.p.remove(var75);
         }

         HomeSupport.TypeP var77 = new HomeSupport.TypeP(var65, var67, var30, (byte)var27[3], var4);
         var77.d = var24 + var27[4] * 0.068F;
         var25 = Math.max(var25, var77.d);
         var4.p.add(var77);
         this.M.put(var73, var77);
      }

      long var64 = 102781131700394L;
      int var661 = var64 < 0L ? -1 : 1;
      float var66x = var25;
      float var68 = 0.0F;
      float var69 = 0.0F;
      float var70 = 0.0F;
      int var71 = 0;

      for (int[] var74 : var18.K) {
         long var35 = 102781131700393L;
         int var38 = var35 < 0L ? -1 : 1;
         var38 = var4.C + var74[0];
         int var82 = var4.d + var74[1];
         int var84 = var4.w + var74[2];
         long var85 = 102781131700394L;
         int var44 = var85 < 0L ? -1 : 1;
         if (var82 > var1.heightAt(var38, var84)) {
            long var44x = pack(var38, var82, var84);
            boolean var46 = this.M.containsKey(var44x);
            long var47 = 102781131700394L;
            int var46x = var47 < 0L ? -1 : 1;
            if (!var46) {
               float var50x = Float.MAX_VALUE;

               for (int[] var52 : var18.D) {
                  float var53 = var74[0] - var52[0];
                  float var54 = var74[1] - var52[1];
                  float var55 = var74[2] - var52[2];
                  var50x = Math.min(var50x, var53 * var53 + var54 * var54 * 1.6F + var55 * var55);
                  long var56 = 102781131700393L;
                  int var54x = var56 < 0L ? -1 : 1;
               }

               HomeSupport.TypeP var86 = new HomeSupport.TypeP(var38, var82, var84, (byte)3, var4);
               var86.d = var25 + 0.02F + (float)Math.sqrt(var50x) * 0.058F + var5.nextFloat() * 0.07F;
               var66x = Math.max(var66x, var86.d);
               var4.p.add(var86);
               long var87 = 102781131700394L;
               int var106 = var87 < 0L ? -1 : 1;
               this.M.put(var44x, var86);
               var68 += var38;
               var69 += var82;
               var70 += var84;
               var71++;
            }
         }
      }

      long var72x = 102781131700394L;
      int var81 = var72x < 0L ? -1 : 1;
      var4.T = var66x;
      if (var71 > 0) {
         var4.q = var68 / var71 + 0.5F;
         var4.W = var69 / var71 + 0.5F;
         var4.G = var70 / var71 + 0.5F;
         float var76x = 0.0F;

         for (HomeSupport.TypeP var80x : var4.p) {
            long var83 = 102781131700393L;
            int var97 = var83 < 0L ? -1 : 1;
            long var42 = 102781131700394L;
            int var102 = var42 < 0L ? -1 : 1;
            if (var80x.R == 3) {
               var76x = Math.max(var76x, Math.abs(var80x.v + 0.5F - var4.q) + Math.abs(var80x.X + 0.5F - var4.G));
            }
         }

         long var79 = 102781131700394L;
         int var93 = var79 < 0L ? -1 : 1;
         var4.h = Math.max(1.5F, var76x * 0.7F);
      }
   }

   private void U(char var1, int var2, short var3, HomeSupport5 var4, HomeSupport.TypeV var5, SplittableRandom var6) {
      HomeSupport2.ensureSourceClassInitialized();
      byte var7;
      float var8;
      float var9;
      switch (var5.x) {
         case j:
            var7 = 5;
            var8 = 4.2F;
            var9 = 0.5F;
            break;
         case I:
            var7 = 7;
            var8 = 3.0F;
            var9 = 0.22F;
            break;
         default:
            var7 = 6;
            var8 = 3.0F;
            var9 = 0.24F;
      }

      int var10 = (int)Math.ceil(var8);

      for (int var11 = -var10; var11 <= var10; var11++) {
         for (int var12 = -var10; var12 <= var10; var12++) {
            int var13 = 1605609962;
            int var14 = var12 * var12;
            int var15 = var11 * var11;
            var13 = 1605609962;
            float var16 = var14 + var15;
            var13 = 1605609962;
            if (!(var16 > var8 * var8) && !(var6.nextFloat() > var9)) {
               int var17 = var5.C + var12;
               int var18 = var5.w + var11;
               var15 = 1605609962;
               short var19 = 0;
               if (HomeSupport5.inside(var17, var18)) {
                  int var20 = HomeSupport5.index(var17, var18);
                  var15 = 1605609962;
                  int var21 = var4.m[var20];
                  var19 = 28013;
                  if (var4.g[var20] == 0) {
                     byte var22 = var4.W[var20];
                     var19 = 28013;
                     if (var22 == 0 && Math.abs(var21 + 1 - var5.d) <= 2 && !this.M.containsKey(pack(var17, var21 + 1, var18))) {
                        HomeSupport.TypeP var23 = new HomeSupport.TypeP(var17, var21 + 1, var18, var7, var5);
                        var23.d = var5.T * 0.82F + var6.nextFloat() * 0.45F + (float)Math.sqrt(var12 * var12 + var11 * var11) * 0.05F;
                        this.f.add(var23);
                        this.M.put(pack(var17, var21 + 1, var18), var23);
                     }
                  }
               }
            }
         }
      }
   }

   private static void Q(int var0, int var1, HomeSupport.TypeR var2, SplittableRandom var3, int var4, byte var5) {
      HomeSupport2.ensureSourceClassInitialized();

      for (int var6 = 0; var6 < var4; var6++) {
         var2.log(0, var6, 0, (byte)0, var6);
      }

      var2.crown(0, var4 - 1, 0);
      int var7 = var4 - 3;

      label76:
      while (var7 <= var4) {
         int var8 = var7 - var4;
         int var9 = 8106682;
         int var10 = var8 >= -1 ? 1 : 2;
         int var11 = -var10;

         while (var11 <= var10) {
            if (var0 < 0) {
               continue label76;
            }

            int var12 = -var10;

            while (var12 <= var10) {
               label61: {
                  label88: {
                     var9 = 8106682;
                     int var13 = Math.abs(var11);
                     int var14 = var13 == var10 && Math.abs(var12) == var10 ? 1 : 0;
                     int var15 = var14;
                     if (var0 > 0) {
                        if (var14 == 0) {
                           break label88;
                        }

                        var15 = var8;
                     }

                     if (var15 == 0) {
                        break label61;
                     }

                     if (var3.nextFloat() < 0.5F) {
                        if (var0 < 0) {
                           continue;
                        }
                        break label61;
                     }
                  }

                  var2.leaf(var11, var7, var12);
               }

               var12++;
            }

            var11++;
            if (var0 < 0) {
               break;
            }
         }

         var7++;
      }
   }

   private static void r(HomeSupport.TypeR var0, SplittableRandom var1, long var2) {
      HomeSupport2.ensureSourceClassInitialized();
      int var4 = 7 + var1.nextInt(4);

      for (int var5 = 0; var5 < var4; var5++) {
         long var6 = 99590931173153L;
         int var9 = var6 < 0L ? -1 : 1;
         var0.log(0, var5, 0, (byte)0, var5);
      }

      var0.blob(var1, 0, var4, 0, 2.7F, 1.9F);
      long var18 = 99590931173154L;
      int var8 = var18 < 0L ? -1 : 1;
      var0.crown(0, var4, 0);
      var8 = 2 + var1.nextInt(3);
      float var9 = var1.nextFloat() * (float) (Math.PI * 2);

      for (int var10 = 0; var10 < var8; var10++) {
         float var11 = var9 + var10 * (float) (Math.PI * 2) / var8 + (var1.nextFloat() - 0.5F) * 0.8F;
         int var12 = (int)(var4 * 0.45F) + var1.nextInt(Math.max(1, var4 / 3));
         float var13 = 2.6F + var1.nextFloat() * 1.8F;
         float var14 = 1.4F + var1.nextFloat() * 1.6F;
         int var15 = Math.round((float)Math.cos(var11) * var13);
         int var16 = Math.round((float)Math.sin(var11) * var13);
         int var17 = Math.round(var12 + var14);
         var0.branch(0, var12, 0, var15, var17, var16, var12);
         var0.blob(var1, var15, var17, var16, 2.2F, 1.5F);
         var0.crown(var15, var17, var16);
      }
   }

   private static void E(char var0, short var1, HomeSupport.TypeR var2, int var3, SplittableRandom var4) {
      HomeSupport2.ensureSourceClassInitialized();
      int var5 = 7 + var4.nextInt(4);

      for (int var6 = 0; var6 < var5; var6++) {
         var2.log(0, var6, 0, (byte)0, var6);
      }

      int var7 = 0;
      var2.crown(0, var5, 0);
      int[] var8 = new int[]{0, 1, 1, 2, 1, 2, 3, 2, 3, 2, 3, 3};
      int var9 = 2 + var4.nextInt(2);
      int var10 = var5;

      for (int var11 = 0; var10 >= var9; var11++) {
         int var12 = 2127;
         if (var11 >= var8.length) {
            break;
         }

         int var13 = var8[var11];

         for (int var14 = -var13; var14 <= var13; var14++) {
            int var15 = -1832698842;

            for (int var16 = -var13; var16 <= var13; var16++) {
               var7 = var14 * var14;
               var15 = var16 * var16;
               var12 = -1832698842;
               int var17 = var7 + var15;
               short var18 = 0;
               var18 = 2127;
               if (!(var17 > var13 * var13 + var13 * 0.5F)) {
                  var12 = -1832698842;
                  var12 = -1832698842;
                  if (var13 >= 2) {
                     var7 = Math.abs(var14);
                     var12 = -1832698842;
                     boolean var19 = false;
                     if (var7 == var13) {
                        var7 = Math.abs(var16);
                        var19 = false;
                        if (var7 == var13) {
                           continue;
                        }
                     }
                  }

                  int var31 = 2127;
                  if (var13 == 3) {
                     var31 = -1832698842;
                     if (var17 >= 9 && var4.nextFloat() < 0.35F) {
                        continue;
                     }
                  }

                  var2.leaf(var14, var10, var16);
               }
            }
         }

         var10--;
      }

      var7 = -1832698842;
      var2.leaf(0, var5 + 1, 0);
   }

   private static void M(HomeSupport.TypeR var0, SplittableRandom var1, long var2) {
      HomeSupport2.ensureSourceClassInitialized();
      int var4 = 2 + var1.nextInt(2);

      for (int var5 = 0; var5 < var4; var5++) {
         long var6 = 69076484354392L;
         int var9 = var6 < 0L ? -1 : 1;
         var0.log(0, var5, 0, (byte)0, var5);
      }

      float var50 = var1.nextFloat();
      long var51 = 69076484354393L;
      int var9 = var51 < 0L ? -1 : 1;
      var9 = var50 < 0.35F ? 3 : 2;
      float var10 = var1.nextFloat() * (float) (Math.PI * 2);
      int var11 = 0;

      while (var11 < var9) {
         float var12 = var10 + var11 * (float) (Math.PI * 2) / var9 + (var1.nextFloat() - 0.5F) * 0.6F;
         int var13 = 3 + var1.nextInt(2);
         int var14 = Math.round((float)Math.cos(var12) * var13 * 0.95F);
         int var15 = Math.round((float)Math.sin(var12) * var13 * 0.95F);
         int var16 = var4 - 1 + Math.round(var13 * 0.9F);
         var0.branch(0, var4 - 1, 0, var14, var16, var15, var4 - 1);
         int var17 = var16 + 1;
         var0.crown(var14, var17, var15);
         float[] var18 = new float[]{3.0F, 3.8F, 3.5F, 2.2F};

         for (int var19 = 0; var19 < var18.length; var19++) {
            int var20 = var19 - 1;
            long var21 = 69076484354392L;
            int var23 = var21 < 0L ? -1 : 1;
            float var23x = var18[var19] - var1.nextFloat() * 0.3F;
            int var24 = (int)Math.ceil(var23x);

            for (int var25 = -var24; var25 <= var24; var25++) {
               int var26 = -var24;
               long var27 = 69076484354393L;
               int var54 = var27 < 0L ? -1 : 1;

               for (int var30 = var26; var30 <= var24; var30++) {
                  int var31 = var25 * var25;
                  long var32 = 69076484354393L;
                  int var32x = var32 < 0L ? -1 : 1;
                  float var35x = (float)Math.sqrt(var31 + var30 * var30);
                  float var36;
                  int var37 = (var36 = var35x - var23x) == 0.0F ? 0 : (var36 < 0.0F ? -1 : 1);
                  long var38 = 69076484354392L;
                  int var37x = var38 < 0L ? -1 : 1;
                  if (var37 > 0) {
                     long var40 = 69076484354393L;
                     int var58 = var40 < 0L ? -1 : 1;
                  } else {
                     if (var20 < 0) {
                        float var55;
                        int var41 = (var55 = var35x - (var23x - 1.3F)) == 0.0F ? 0 : (var55 < 0.0F ? -1 : 1);
                        long var42 = 69076484354392L;
                        int var41x = var42 < 0L ? -1 : 1;
                        if (var41 < 0) {
                           continue;
                        }

                        float var44;
                        int var45 = (var44 = var1.nextFloat() - 0.55F) == 0.0F ? 0 : (var44 < 0.0F ? -1 : 1);
                        long var46 = 69076484354392L;
                        int var45x = var46 < 0L ? -1 : 1;
                        if (var45 < 0) {
                           long var48x = 69076484354393L;
                           int var47 = var48x < 0L ? -1 : 1;
                           continue;
                        }
                     } else if (var35x > var23x - 0.8F) {
                        float var56x;
                        int var57 = (var56x = var1.nextFloat() - 0.35F) == 0.0F ? 0 : (var56x < 0.0F ? -1 : 1);
                        long var58 = 69076484354392L;
                        int var68 = var58 < 0L ? -1 : 1;
                        if (var57 < 0) {
                           long var59x = 69076484354392L;
                           int var70 = var59x < 0L ? -1 : 1;
                           continue;
                        }
                     }

                     var0.leaf(var14 + var25, var17 + var20, var15 + var30);
                  }
               }

               long var54x = 69076484354392L;
               int var591 = var54x < 0L ? -1 : 1;
            }

            long var53 = 69076484354393L;
            int var561 = var53 < 0L ? -1 : 1;
         }

         var11++;
         long var52 = 69076484354393L;
         int var531 = var52 < 0L ? -1 : 1;
      }
   }

   static long pack(int var0, int var1, int var2) {
      return (long)(var0 & 67108863) << 38 | (long)(var2 & 67108863) << 12 | var1 & 4095;
   }

   static final class TypeP {
      final int v;
      final int P;
      final int X;
      final byte R;
      final HomeSupport.TypeV j;
      float d;

      TypeP(int var1, int var2, int var3, byte var4, HomeSupport.TypeV var5) {
         this.v = var1;
         this.P = var2;
         this.X = var3;
         this.R = var4;
         this.j = var5;
      }

      boolean log() {
         return this.R <= 2;
      }
   }

   private static final class TypeR {
      final List<int[]> O = new ArrayList<>();
      final List<int[]> K = new ArrayList<>();
      final List<int[]> D = new ArrayList<>();

      void log(int var1, int var2, int var3, byte var4, int var5) {
         this.O.add(new int[]{var1, var2, var3, var4, var5});
      }

      void leaf(int var1, int var2, int var3) {
         this.K.add(new int[]{var1, var2, var3});
      }

      void crown(int var1, int var2, int var3) {
         this.D.add(new int[]{var1, var2, var3});
      }

      void branch(int var1, int var2, int var3, int var4, int var5, int var6, int var7) {
         int var8 = var4 - var1;
         int var9 = var5 - var2;
         int var10 = var6 - var3;
         int var11 = Math.max(Math.abs(var8), Math.max(Math.abs(var9), Math.abs(var10)));
         int var12 = var1;
         int var13 = var2;
         int var14 = var3;

         for (int var15 = 1; var15 <= var11; var15++) {
            int var16 = Math.round(var1 + (float)var8 * var15 / var11);
            int var17 = Math.round(var2 + (float)var9 * var15 / var11);
            int var18 = Math.round(var3 + (float)var10 * var15 / var11);
            int var19 = Math.abs(var16 - var12);
            int var20 = Math.abs(var17 - var13);
            int var21 = Math.abs(var18 - var14);
            int var22 = var20 >= var19 && var20 >= var21 ? 0 : (var19 >= var21 ? 1 : 2);
            this.log(var16, var17, var18, (byte)var22, var7 + var15);
            var12 = var16;
            var13 = var17;
            var14 = var18;
         }
      }

      void blob(SplittableRandom var1, int var2, int var3, int var4, float var5, float var6) {
         int var7 = (int)Math.ceil(var5);
         int var8 = (int)Math.ceil(var6);

         for (int var9 = -var8; var9 <= var8; var9++) {
            for (int var10 = -var7; var10 <= var7; var10++) {
               for (int var11 = -var7; var11 <= var7; var11++) {
                  float var12 = (var10 * var10 + var11 * var11) / (var5 * var5) + var9 * var9 / (var6 * var6);
                  if (!(var12 > 1.0F) && (!(var12 > 0.68F) || !(var1.nextFloat() < 0.38F))) {
                     this.leaf(var2 + var10, var3 + var9, var4 + var11);
                  }
               }
            }
         }
      }
   }

   static final class TypeV {
      final int C;
      final int d;
      final int w;
      final HomeSupport.TypeX x;
      final float H;
      final List<HomeSupport.TypeP> p = new ArrayList<>();
      final List<int[]> Z = new ArrayList<>();
      float T;
      float q;
      float W;
      float G;
      float h;

      TypeV(int var1, int var2, int var3, HomeSupport.TypeX var4, float var5) {
         this.C = var1;
         this.d = var2;
         this.w = var3;
         this.x = var4;
         this.H = var5;
      }

      boolean cherry() {
         return this.x == HomeSupport.TypeX.j;
      }
   }

   static enum TypeX {
      E,
      w,
      t,
      p,
      j,
      I;

      private static final long a = -9063154655608963066L;
   }
}
