// Sourced By DevFFNightmare
package dev.purxiy.modules.home;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

final class HomeSupport5 {
   static int w;
   static int c;
   static int L;
   static int M;
   static final byte C = 0;
   static final byte Q = 1;
   static byte S;
   static byte e;
   static byte N;
   static byte I;
   static final byte J = 0;
   static final byte Y = 1;
   static byte B;
   static byte D;
   static byte K;
   static byte u;
   static byte d;
   static byte n;
   static byte o;
   static byte X;
   static byte i;
   static byte U;
   static byte R;
   static byte z;
   static byte v;
   static byte A;
   static byte k;
   static byte b;
   final long l;
   final HomeSupport4 F;
   final int[] m;
   final byte[] g;
   final byte[] W;
   final byte[] H;
   final List<int[]> O;
   final float x;
   final float y;
   final float E;
   final float j;
   final float s;
   private static long[] a;
   private static Integer[] f;
   private static long[] h;
   private static Long[] p;

   HomeSupport5(long var1) {
      long var3 = 89140285079685L;
      long var5 = var3 ^ 82734047882308L;
      long var7 = var3 ^ 24772104473249L;
      int var9 = (int)((var3 ^ 24772104473249L) >>> 48);
      int var10 = (int)((var3 ^ 24772104473249L) << 16 >>> 48);
      int var11 = (int)(var7 << 32 >>> 32);
      long var12 = var3 ^ 28312106581829L;
      this.m = new int[16384];
      this.g = new byte[16384];
      this.W = new byte[16384];
      this.H = new byte[16384];
      this.O = new ArrayList<>();
      this.l = var1;
      this.F = new HomeSupport4(var1);
      float var14 = HomeSupport4.unit(var1 * 7L + 1L) * (float) (Math.PI * 2);
      this.j = (float)Math.cos(var14) * 21.0F;
      this.s = (float)Math.sin(var14) * 21.0F;
      this.G(var12);
      this.R((char)var9, (char)var10, var11);
      this.J(var5);
      this.x = 0.5F;
      this.E = 0.5F;
      this.y = this.heightAt(0, 0) + 1 + 6.2F;
   }

   static int index(int var0, int var1) {
      return var0 + 64 + (var1 + 64) * 128;
   }

   static boolean inside(int var0, int var1) {
      return var0 >= -64 && var1 >= -64 && var0 < 64 && var1 < 64;
   }

   int heightAt(int var1, int var2) {
      long var3 = 10243914569362L;
      long var5 = var3 ^ 5123615783L;
      return this.m[index(y(var1, var5), y(var2, var5))];
   }

   boolean water(int var1, int var2) {
      return inside(var1, var2) && this.m[index(var1, var2)] < 63;
   }

   boolean solid(int var1, int var2, int var3) {
      return var2 <= this.heightAt(var1, var3);
   }

   int plantHeight(int var1, int var2) {
      if (!inside(var1, var2)) {
         return 0;
      } else {
         return switch (this.W[index(var1, var2)]) {
            case 0, 15 -> 0;
            default -> 1;
            case 2, 11, 12, 13 -> 2;
            case 14 -> this.caneHeight(var1, var2);
         };
      }
   }

   int caneHeight(int var1, int var2) {
      SplittableRandom var3 = new SplittableRandom(this.l ^ var1 * 73428767L + var2 * 912931L);
      var3.nextFloat();
      var3.nextFloat();
      return 1 + var3.nextInt(3);
   }

   private static int y(int var0, long var1) {
      return Math.max(-64, Math.min(63, var0));
   }

   private void G(long var1) {
      HomeSupport2.ensureSourceClassInitialized();
      HomeSupport4 var3 = this.F;
      float var4 = 10.5F + HomeSupport4.unit(this.l * b(19629, 2018742005342312639L) + b(31919, 8569114328737327292L)) * 3.5F;

      for (int var5 = -64; var5 < 64; var5++) {
         for (int var6 = -64; var6 < 64; var6++) {
            float var7 = (float)Math.sqrt(var6 * var6 + var5 * var5);
            float var8 = var3.fbm(var6 * 0.03F, var5 * 0.03F, 4) * 3.0F;
            float var9 = Math.max(0.0F, var3.fbm(var6 * 0.016F + 40.0F, var5 * 0.016F - 20.0F, 3) * 0.5F + 0.55F);
            float var10 = (float)Math.pow(var9, 1.6) * 21.0F * smooth(24.0F, 58.0F, var7);
            float var11 = 2.6F * (1.0F - smooth(3.0F, 15.0F, var7));
            float var12 = var6 - this.j;
            float var13 = var5 - this.s;
            float var14 = 1.0F + 0.28F * var3.noise(var6 * 0.09F + 7.0F, var5 * 0.09F - 3.0F);
            long var15 = 79909913968576L;
            if (79909913968576L == 0L) {
               boolean var10000 = false;
            } else {
               int var27 = var15 < 0L ? -1 : 1;
            }

            float var18 = (float)Math.sqrt(var12 * var12 + var13 * var13) / (var4 * var14);
            float var19 = (1.0F - smooth(0.7F, 1.15F, var18)) * 7.5F;
            float var20 = 64.0F + var8 + var10 + var11 - var19;
            float var21 = var7 - 57.0F;
            long var22 = 79909913968576L;
            if (79909913968576L == 0L) {
               boolean var28 = false;
            } else {
               int var29 = var22 < 0L ? -1 : 1;
            }

            long var24 = 79909913968576L;
            if (79909913968576L == 0L) {
               boolean var30 = false;
            } else {
               int var31 = var24 < 0L ? -1 : 1;
            }

            if (var21 > 0.0F) {
               var20 = Math.max(var20, 64.0F + var21 * 0.6F);
            }

            this.m[index(var6, var5)] = Math.round(var20);
         }

         long var26 = 79909913968575L;
         if (79909913968575L == 0L) {
            boolean var32 = false;
         } else {
            int var33 = var26 < 0L ? -1 : 1;
         }
      }
   }

   private void R(char var1, char var2, int var3) {
      long var4 = 78705871014436L;
      long var6 = var4 ^ 75517786677907L;
      long var8 = var4 ^ 28480505298660L;
      this.z(var8);
      HomeSupport2.ensureSourceClassInitialized();
      HomeSupport4 var10 = this.F;

      for (int var11 = -64; var11 < 64; var11++) {
         for (int var12 = -64; var12 < 64; var12++) {
            int var13 = index(var12, var11);
            int var14 = this.m[var13];
            short var15 = 0;
            int var16 = Math.max(
               Math.max(Math.abs(var14 - this.heightAt(var12 + 1, var11)), Math.abs(var14 - this.heightAt(var12 - 1, var11))),
               Math.max(Math.abs(var14 - this.heightAt(var12, var11 + 1)), Math.abs(var14 - this.heightAt(var12, var11 - 1)))
            );
            int var17 = 18325;
            int var18;
            if (var14 < 63) {
               float var19 = var10.noise(var12 * 0.2F + 50.0F, var11 * 0.2F);
               var17 = 595315236;
               var18 = var14 >= 61 ? 1 : (var19 > 0.35F ? 3 : (var19 < -0.2F ? 2 : 1));
               var15 = (0 != 0 ? 1 : 0);
            } else {
               label83: {
                  int var29 = 18325;
                  var29 = (short)18325;
                  if (var14 <= 64) {
                     var15 = this.H[var13];
                     var29 = 595315236;
                     if (var15 <= 2) {
                        var18 = 1;
                        var15 = 18325;
                        break label83;
                     }
                  }

                  var29 = 595315236;
                  int var20 = 0;
                  if (var16 < 3) {
                     var20 = 595315236;
                     if (var14 < 80 || !(var10.noise(var12 * 0.15F, var11 * 0.15F + 90.0F) > -0.2F)) {
                        var18 = 0;
                        break label83;
                     }
                  }

                  var18 = 4;
               }
            }

            this.g[var13] = (byte)var18;
         }
      }

      int var21 = -64;

      while (true) {
         int var22 = 18325;
         if (var21 >= 64) {
            return;
         }

         for (int var24 = -64; var24 < 64; var24++) {
            this.W[index(var24, var21)] = this.I(var24, var6, var21);
            var22 = 595315236;
         }

         var21++;
      }
   }

   private void z(long var1) {
      HomeSupport2.ensureSourceClassInitialized();
      int[] var3 = new int[16384];

      for (int var4 = -64; var4 < 64; var4++) {
         long var5 = 103843737990336L;
         if (103843737990336L == 0L) {
            boolean var10000 = false;
         } else {
            int var39 = var5 < 0L ? -1 : 1;
         }

         for (int var8 = -64; var8 < 64; var8++) {
            long var9 = 103843737990336L;
            if (103843737990336L == 0L) {
               boolean var40 = false;
            } else {
               int var41 = var9 < 0L ? -1 : 1;
            }

            long var11 = 103843737990335L;
            if (103843737990335L == 0L) {
               boolean var42 = false;
            } else {
               int var43 = var11 < 0L ? -1 : 1;
            }

            int var7 = index(var8, var4);
            int var14 = this.m[index(var8, var4)];
            long var15 = 103843737990336L;
            if (103843737990336L == 0L) {
               boolean var44 = false;
            } else {
               int var45 = var15 < 0L ? -1 : 1;
            }

            var3[var7] = var14 < 63 ? 0 : 99;
         }

         long var26 = 103843737990335L;
         if (103843737990335L == 0L) {
            boolean var46 = false;
         } else {
            int var47 = var26 < 0L ? -1 : 1;
         }
      }

      long var25 = 103843737990335L;
      if (103843737990335L == 0L) {
         boolean var48 = false;
      } else {
         int var49 = var25 < 0L ? -1 : 1;
      }

      int var27 = -64;

      while (true) {
         long var28 = 103843737990335L;
         if (103843737990335L == 0L) {
            boolean var50 = false;
         } else {
            int var51 = var28 < 0L ? -1 : 1;
         }

         if (var27 >= 64) {
            long var29 = 103843737990336L;
            if (103843737990336L == 0L) {
               boolean var60 = false;
            } else {
               int var61 = var29 < 0L ? -1 : 1;
            }

            int var33 = 63;

            while (var33 >= -64) {
               long var16 = 103843737990336L;
               if (103843737990336L == 0L) {
                  boolean var62 = false;
               } else {
                  int var63 = var16 < 0L ? -1 : 1;
               }

               for (int var35 = 63; var35 >= -64; var35--) {
                  int var19 = index(var35, var33);
                  long var20 = 103843737990336L;
                  if (103843737990336L == 0L) {
                     boolean var64 = false;
                  } else {
                     int var65 = var20 < 0L ? -1 : 1;
                  }

                  long var22 = 103843737990335L;
                  if (103843737990335L == 0L) {
                     boolean var66 = false;
                  } else {
                     int var67 = var22 < 0L ? -1 : 1;
                  }

                  if (var35 < 63) {
                     var3[var19] = Math.min(var3[var19], var3[var19 + 1] + 1);
                  }

                  if (var33 < 63) {
                     var3[var19] = Math.min(var3[var19], var3[var19 + 128] + 1);
                  }
               }

               var33--;
               long var36 = 103843737990336L;
               if (103843737990336L == 0L) {
                  boolean var68 = false;
               } else {
                  int var69 = var36 < 0L ? -1 : 1;
               }
            }

            long var34 = 103843737990336L;
            if (103843737990336L == 0L) {
               boolean var70 = false;
            } else {
               int var71 = var34 < 0L ? -1 : 1;
            }

            int var37 = 0;

            while (true) {
               long var38 = 103843737990336L;
               if (103843737990336L == 0L) {
                  boolean var72 = false;
               } else {
                  int var73 = var38 < 0L ? -1 : 1;
               }

               if (var37 >= var3.length) {
                  long var21 = 103843737990335L;
                  if (103843737990335L == 0L) {
                     boolean var74 = false;
                  } else {
                     int var75 = var21 < 0L ? -1 : 1;
                  }

                  return;
               }

               this.H[var37] = (byte)Math.min(12, var3[var37]);
               var37++;
            }
         }

         long var12 = 103843737990336L;
         if (103843737990336L == 0L) {
            boolean var52 = false;
         } else {
            int var53 = var12 < 0L ? -1 : 1;
         }

         for (int var30 = -64; var30 < 64; var30++) {
            long var32 = 103843737990335L;
            if (103843737990335L == 0L) {
               boolean var54 = false;
            } else {
               int var55 = var32 < 0L ? -1 : 1;
            }

            int var17 = index(var30, var27);
            long var18 = 103843737990336L;
            if (103843737990336L == 0L) {
               boolean var56 = false;
            } else {
               int var57 = var18 < 0L ? -1 : 1;
            }

            if (var30 > -64) {
               var3[var17] = Math.min(var3[var17], var3[var17 - 1] + 1);
            }

            if (var27 > -64) {
               var3[var17] = Math.min(var3[var17], var3[var17 - 128] + 1);
            }
         }

         long var31 = 103843737990336L;
         if (103843737990336L == 0L) {
            boolean var58 = false;
         } else {
            int var59 = var31 < 0L ? -1 : 1;
         }

         var27++;
      }
   }

   private byte I(int var1, long var2, int var4) {
      HomeSupport2.ensureSourceClassInitialized();
      int var5 = index(var1, var4);
      int var6 = this.m[var5];
      float var7 = (float)Math.sqrt(var1 * var1 + var4 * var4);
      float var8 = HomeSupport4.hash(this.l, var1, var6, var4, 1);
      long var9 = 3556126140598L;
      if (3556126140598L == 0L) {
         boolean var10000 = false;
      } else {
         int var72 = var9 < 0L ? -1 : 1;
      }

      long var12 = 3556126140599L;
      if (3556126140599L == 0L) {
         boolean var73 = false;
      } else {
         int var74 = var12 < 0L ? -1 : 1;
      }

      if (var6 < 63) {
         int var53 = 63;
         long var54 = 3556126140599L;
         if (3556126140599L == 0L) {
            boolean var115 = false;
         } else {
            int var116 = var54 < 0L ? -1 : 1;
         }

         return (byte)(var53 - var6 >= 1 && var8 < 0.03F ? 15 : 0);
      } else if (var7 < 6.5F) {
         return (byte)(var7 > 3.5F && var8 < 0.22F ? 1 : 0);
      } else {
         byte var14 = this.g[var5];
         long var15 = 3556126140599L;
         if (3556126140599L == 0L) {
            boolean var75 = false;
         } else {
            int var76 = var15 < 0L ? -1 : 1;
         }

         long var17 = 3556126140599L;
         if (3556126140599L == 0L) {
            boolean var77 = false;
         } else {
            int var78 = var17 < 0L ? -1 : 1;
         }

         if (var14 != 1) {
            long var55 = 3556126140599L;
            if (3556126140599L == 0L) {
               boolean var81 = false;
            } else {
               int var82 = var55 < 0L ? -1 : 1;
            }

            if (var14 != 0) {
               return 0;
            } else {
               int var21 = this.H[var5];
               long var22 = 3556126140599L;
               if (3556126140599L == 0L) {
                  boolean var83 = false;
               } else {
                  int var84 = var22 < 0L ? -1 : 1;
               }

               if (var21 <= 3) {
                  float var24;
                  var21 = (var24 = var8 - 0.965F) == 0.0F ? 0 : (var24 < 0.0F ? -1 : 1);
                  long var25 = 3556126140599L;
                  if (3556126140599L == 0L) {
                     boolean var85 = false;
                  } else {
                     int var86 = var25 < 0L ? -1 : 1;
                  }

                  if (var21 > 0) {
                     return (byte)16;
                  }
               }

               int var57 = this.H[var5];
               long var65 = 3556126140598L;
               if (3556126140598L == 0L) {
                  boolean var87 = false;
               } else {
                  int var88 = var65 < 0L ? -1 : 1;
               }

               if (var57 <= 2 && var8 > 0.93F) {
                  return (byte)10;
               } else {
                  HomeSupport4 var27 = this.F;
                  float var28 = var27.fbm(var1 * 0.07F + 300.0F, var4 * 0.07F - 120.0F, 2);
                  long var29 = 3556126140598L;
                  if (3556126140598L == 0L) {
                     boolean var89 = false;
                  } else {
                     int var90 = var29 < 0L ? -1 : 1;
                  }

                  if (var28 > 0.33F) {
                     long var32 = 3556126140599L;
                     if (3556126140599L == 0L) {
                        boolean var91 = false;
                     } else {
                        int var92 = var32 < 0L ? -1 : 1;
                     }

                     if (var8 < 0.42F) {
                        float var34 = var27.noise(var1 * 0.035F - 77.0F, var4 * 0.035F + 51.0F) * 0.5F + 0.5F;
                        float var35 = HomeSupport4.hash(this.l, var1, var6, var4, 2);
                        long var67 = 3556126140599L;
                        if (3556126140599L == 0L) {
                           boolean var101 = false;
                        } else {
                           int var102 = var67 < 0L ? -1 : 1;
                        }

                        if (var35 < 0.06F) {
                           float var69;
                           var57 = (var69 = var34 - 0.33F) == 0.0F ? 0 : (var69 < 0.0F ? -1 : 1);
                           long var70 = 3556126140598L;
                           if (3556126140598L == 0L) {
                              boolean var113 = false;
                           } else {
                              int var114 = var70 < 0L ? -1 : 1;
                           }

                           return (byte)(
                              var57 < 0 ? 11 : (var34 < 0.66F ? 13 : 12)
                           );
                        }

                        long var68 = 3556126140599L;
                        if (3556126140599L == 0L) {
                           boolean var103 = false;
                        } else {
                           int var104 = var68 < 0L ? -1 : 1;
                        }

                        float var40;
                        var57 = (var40 = var34 - 0.2F) == 0.0F ? 0 : (var40 < 0.0F ? -1 : 1);
                        long var71 = 3556126140598L;
                        if (3556126140598L == 0L) {
                           boolean var105 = false;
                        } else {
                           int var106 = var71 < 0L ? -1 : 1;
                        }

                        byte[] var44;
                        if (var57 < 0) {
                           var44 = new byte[]{4, (byte)8};
                        } else {
                           long var45 = 3556126140598L;
                           if (3556126140598L == 0L) {
                              boolean var107 = false;
                           } else {
                              int var108 = var45 < 0L ? -1 : 1;
                           }

                           float var47;
                           var57 = (var47 = var34 - 0.4F) == 0.0F ? 0 : (var47 < 0.0F ? -1 : 1);
                           long var48 = 3556126140598L;
                           if (3556126140598L == 0L) {
                              boolean var109 = false;
                           } else {
                              int var110 = var48 < 0L ? -1 : 1;
                           }

                           if (var57 < 0) {
                              var44 = new byte[]{5, (byte)7};
                           } else {
                              float var50;
                              var57 = (var50 = var34 - 0.6F) == 0.0F ? 0 : (var50 < 0.0F ? -1 : 1);
                              long var51 = 3556126140598L;
                              if (3556126140598L == 0L) {
                                 boolean var111 = false;
                              } else {
                                 int var112 = var51 < 0L ? -1 : 1;
                              }

                              var44 = var57 < 0
                                 ? new byte[]{(byte)9, (byte)6}
                                 : (var34 < 0.8F ? new byte[]{(byte)6, (byte)8} : new byte[]{4, 5});
                           }
                        }

                        return var44[var35 < 0.55F ? 0 : 1];
                     }
                  }

                  float var66;
                  var57 = (var66 = var8 - 0.3F) == 0.0F ? 0 : (var66 < 0.0F ? -1 : 1);
                  long var33 = 3556126140598L;
                  if (3556126140598L == 0L) {
                     boolean var93 = false;
                  } else {
                     int var94 = var33 < 0L ? -1 : 1;
                  }

                  if (var57 < 0) {
                     return 1;
                  } else {
                     long var36 = 3556126140598L;
                     if (3556126140598L == 0L) {
                        boolean var95 = false;
                     } else {
                        int var96 = var36 < 0L ? -1 : 1;
                     }

                     float var38;
                     var57 = (var38 = var8 - 0.335F) == 0.0F ? 0 : (var38 < 0.0F ? -1 : 1);
                     long var39 = 3556126140599L;
                     if (3556126140599L == 0L) {
                        boolean var97 = false;
                     } else {
                        int var98 = var39 < 0L ? -1 : 1;
                     }

                     if (var57 < 0) {
                        return 2;
                     } else {
                        float var41;
                        var57 = (var41 = var8 - 0.35F) == 0.0F ? 0 : (var41 < 0.0F ? -1 : 1);
                        long var42 = 3556126140598L;
                        if (3556126140598L == 0L) {
                           boolean var99 = false;
                        } else {
                           int var100 = var42 < 0L ? -1 : 1;
                        }

                        return var57 < 0 ? (byte)17 : (byte)(var8 < 0.37F && var27.noise(var1 * 0.05F, var4 * 0.05F) > 0.1F ? 3 : 0);
                     }
                  }
               }
            }
         } else {
            byte var19 = this.H[var5];
            long var20 = 3556126140598L;
            if (3556126140598L == 0L) {
               boolean var79 = false;
            } else {
               int var80 = var20 < 0L ? -1 : 1;
            }

            return (byte)(var19 == 1 && var8 < 0.2F ? 14 : 0);
         }
      }
   }

   private void J(long var1) {
      HomeSupport2.ensureSourceClassInitialized();
      ArrayList var3 = new ArrayList();
      long var4 = 5272380419953156863L;
      byte var6 = 5;

      for (int var7 = -64; var7 < 64; var7 += var6) {
         long var8 = 28783034756289L;
         if (28783034756289L == 0L) {
            boolean var10000 = false;
         } else {
            int var54 = var8 < 0L ? -1 : 1;
         }

         for (int var11 = -64; var11 < 64; var11 += var6) {
            long var12 = 28783034756289L;
            if (28783034756289L == 0L) {
               boolean var55 = false;
            } else {
               int var56 = var12 < 0L ? -1 : 1;
            }

            int var15 = var11 + (int)(HomeSupport4.hash(this.l, var11, 0, var7, 11) * var6);
            int var16 = var7 + (int)(HomeSupport4.hash(this.l, var11, 0, var7, 12) * var6);
            long var17 = 28783034756288L;
            if (28783034756288L == 0L) {
               boolean var57 = false;
            } else {
               int var58 = var17 < 0L ? -1 : 1;
            }

            int var20 = (inside(var15, var16) ? 1 : 0);
            long var21 = 28783034756288L;
            if (28783034756288L == 0L) {
               boolean var59 = false;
            } else {
               int var60 = var21 < 0L ? -1 : 1;
            }

            if ((var20 == 0)) {
               long var24 = 28783034756288L;
               if (28783034756288L == 0L) {
                  boolean var61 = false;
               } else {
                  int var62 = var24 < 0L ? -1 : 1;
               }
            } else {
               long var51 = 28783034756289L;
               if (28783034756289L == 0L) {
                  boolean var63 = false;
               } else {
                  int var64 = var51 < 0L ? -1 : 1;
               }

               float var27 = (float)Math.sqrt(var15 * var15 + var16 * var16);
               var20 = index(var15, var16);
               long var28 = 28783034756288L;
               if (28783034756288L == 0L) {
                  boolean var65 = false;
               } else {
                  int var66 = var28 < 0L ? -1 : 1;
               }

               if (!(var27 < 7.5F) && !(var27 > 58.0F)) {
                  byte var32 = this.g[var20];
                  long var33 = 28783034756289L;
                  if (28783034756289L == 0L) {
                     boolean var67 = false;
                  } else {
                     int var68 = var33 < 0L ? -1 : 1;
                  }

                  if (var32 == 0) {
                     var32 = this.H[var20];
                     long var36 = 28783034756289L;
                     if (28783034756289L == 0L) {
                        boolean var69 = false;
                     } else {
                        int var70 = var36 < 0L ? -1 : 1;
                     }

                     if (var32 < 3) {
                        long var39 = 28783034756288L;
                        if (28783034756288L == 0L) {
                           boolean var71 = false;
                        } else {
                           int var72 = var39 < 0L ? -1 : 1;
                        }
                     } else {
                        int var53 = this.m[var20];
                        int var40 = Math.max(
                           Math.max(Math.abs(var53 - this.heightAt(var15 + 1, var16)), Math.abs(var53 - this.heightAt(var15 - 1, var16))),
                           Math.max(Math.abs(var53 - this.heightAt(var15, var16 + 1)), Math.abs(var53 - this.heightAt(var15, var16 - 1)))
                        );
                        long var41 = 28783034756289L;
                        if (28783034756289L == 0L) {
                           boolean var73 = false;
                        } else {
                           int var74 = var41 < 0L ? -1 : 1;
                        }

                        if (var40 > 1) {
                           long var44 = 28783034756288L;
                           if (28783034756288L == 0L) {
                              boolean var75 = false;
                           } else {
                              int var76 = var44 < 0L ? -1 : 1;
                           }
                        } else {
                           var3.add(new int[]{var15, var16});
                        }
                     }
                  }
               }
            }
         }

         long var48 = 28783034756289L;
         if (28783034756289L == 0L) {
            boolean var77 = false;
         } else {
            int var78 = var48 < 0L ? -1 : 1;
         }
      }

      long var47 = 28783034756288L;
      if (28783034756288L == 0L) {
         boolean var79 = false;
      } else {
         int var80 = var47 < 0L ? -1 : 1;
      }

      var3.sort(java.util.Comparator.comparingInt((int[] a) -> a[0] * a[0] + a[1] * a[1]));

      for (int[] var49 : (Iterable<int[]>)(Object)(var3)) {
         this.W[index(var49[0], var49[1])] = 0;
         this.O.add(var49);
      }
   }

   static float smooth(float var0, float var1, float var2) {
      float var3 = Math.max(0.0F, Math.min(1.0F, (var2 - var0) / (var1 - var0)));
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

private static long b(int var0, long var1) {
      int var3 = (var0 ^ (int)var1 ^ 21522) & 32767;
      if (p[var3] == null) {
         p[var3] = h[var3] ^ var1;
      }

      return p[var3];
   }

   private static void __purxiyInit0() {
      long var0 = 4048738852825L;
      long[] var2 = new long[47];
      int var3 = 0;
      String var4 = "É:rª$\u0098\u0014ÛßÂÂTå|ò%u\u0018\u0000u#Ó6\u001bÀÐï81_\bå\u008ck\u00adÌ#È½s\u008e\u001dn\u009dT8é\u0015\u009f \u000f-Äm\u0002\u0094\u008e\u001a\u0005ü2¡q'ÈÑÑÖôXN±×â9&@\u007fèÜgÀ\u001fzÛX\u0081úã\u0083³ïpqÝs\r3å3ëÞ¨\u0005\u0016\u0095dÀuÐ\n½wöó±\u008ceã\u0099bXð½Ä\u0002u®]\u001ajÅ1tÄa °ñðû3\u0000p-`P\u0084\u0091Æ\u0004\u0094ï&(Èð \u0096p\u0006\fÚõà\f\u008clXð{\u00927»¤eg±L\u008c9M\\\u0014Sj\u0081B¼Ü¬$G½\u0016lìUxFò9fG)±ã¬ûc«¹\u0088³FtÝ(²\u0002E\u0001\u009b\u008cñY'ç²ßûàªYVT%Qðß\u0085Ñ\u0002W«ÂjU\nF\u0080N\u0087ÐÂ9\fÂB8¥ö»\u0018Á¢»&clkæIRÅÛ\u0090S\u0082æOðÊy\u008b\u001f\u009d\u0085ª¥\u0092xâæ.ØÇ\u000f>¡±RÏÏ\u009dæ&æn1ã\u0002ö\nd¡Æèýà\u008a-Õ°\u0004?\u000e½C4\u0094ý\u0011Q\fu\u0004_³¡ÊGm©\u0000\u0006'x\u001a\b\"\u001c\u008aÕøoIm¼\u009a";
      int var5 = "É:rª$\u0098\u0014ÛßÂÂTå|ò%u\u0018\u0000u#Ó6\u001bÀÐï81_\bå\u008ck\u00adÌ#È½s\u008e\u001dn\u009dT8é\u0015\u009f \u000f-Äm\u0002\u0094\u008e\u001a\u0005ü2¡q'ÈÑÑÖôXN±×â9&@\u007fèÜgÀ\u001fzÛX\u0081úã\u0083³ïpqÝs\r3å3ëÞ¨\u0005\u0016\u0095dÀuÐ\n½wöó±\u008ceã\u0099bXð½Ä\u0002u®]\u001ajÅ1tÄa °ñðû3\u0000p-`P\u0084\u0091Æ\u0004\u0094ï&(Èð \u0096p\u0006\fÚõà\f\u008clXð{\u00927»¤eg±L\u008c9M\\\u0014Sj\u0081B¼Ü¬$G½\u0016lìUxFò9fG)±ã¬ûc«¹\u0088³FtÝ(²\u0002E\u0001\u009b\u008cñY'ç²ßûàªYVT%Qðß\u0085Ñ\u0002W«ÂjU\nF\u0080N\u0087ÐÂ9\fÂB8¥ö»\u0018Á¢»&clkæIRÅÛ\u0090S\u0082æOðÊy\u008b\u001f\u009d\u0085ª¥\u0092xâæ.ØÇ\u000f>¡±RÏÏ\u009dæ&æn1ã\u0002ö\nd¡Æèýà\u008a-Õ°\u0004?\u000e½C4\u0094ý\u0011Q\fu\u0004_³¡ÊGm©\u0000\u0006'x\u001a\b\"\u001c\u008aÕøoIm¼\u009a"
         .length();
      int var6 = 0;

      label28:
      while (true) {
         int var7 = var6;
         var6 += 8;
         byte[] var8 = var4.substring(var7, var6).getBytes(StandardCharsets.ISO_8859_1);
         long[] var9 = var2;
         int var10 = var3++;
         long var11 = (var8[0] & 255L) << 56
            | (var8[1] & 255L) << 48
            | (var8[2] & 255L) << 40
            | (var8[3] & 255L) << 32
            | (var8[4] & 255L) << 24
            | (var8[5] & 255L) << 16
            | (var8[6] & 255L) << 8
            | var8[7] & 255L;
         byte var13 = -1;

         while (true) {
            long var14 = var11 ^ var0;
            switch (var13) {
               case 0:
                  var9[var10] = var14;
                  if (var6 >= var5) {
                     a = var2;
                     f = new Integer[47];
                     R = (byte)12;
                     w = 64;
                     X = (byte)9;
                     A = (byte)15;
                     B = (byte)2;
                     k = (byte)16;
                     I = (byte)5;
                     c = 128;
                     U = (byte)11;
                     e = (byte)3;
                     N = (byte)4;
                     K = (byte)4;
                     u = (byte)5;
                     b = (byte)17;
                     M = 64;
                     v = (byte)14;
                     d = (byte)6;
                     S = (byte)2;
                     D = (byte)3;
                     i = (byte)10;
                     n = (byte)7;
                     z = (byte)13;
                     L = 63;
                     o = (byte)8;
                     long[] var27 = new long[2];
                     int var17 = 0;
                     String var18 = "\u001c\u0004\u0000\u0086Ò2\u0087avë\u009fº\u001f½7g";
                     int var19 = "\u001c\u0004\u0000\u0086Ò2\u0087avë\u009fº\u001f½7g".length();
                     byte var20 = 0;

                     do {
                        var7 = var20;
                        var20 = (byte)(var20 + 8);
                        byte[] var21 = var18.substring(var7, var20).getBytes(StandardCharsets.ISO_8859_1);
                        var7 = var17++;
                        var14 = (
                              (var21[0] & 255L) << 56
                                 | (var21[1] & 255L) << 48
                                 | (var21[2] & 255L) << 40
                                 | (var21[3] & 255L) << 32
                                 | (var21[4] & 255L) << 24
                                 | (var21[5] & 255L) << 16
                                 | (var21[6] & 255L) << 8
                                 | var21[7] & 255L
                           )
                           ^ var0;
                        var13 = -1;
                        var27[var7] = var14;
                     } while (var20 < var19);

                     h = var27;
                     p = new Long[2];
                     return;
                  }
                  break;
               default:
                  var9[var10] = var14;
                  if (var6 < var5) {
                     continue label28;
                  }

                  var4 = "bQúI \u00198\u0093<U\u0098W\\jn\u0018";
                  var5 = "bQúI \u00198\u0093<U\u0098W\\jn\u0018".length();
                  var6 = 0;
            }

            int var16 = var6;
            var6 += 8;
            var8 = var4.substring(var16, var6).getBytes(StandardCharsets.ISO_8859_1);
            var9 = var2;
            var10 = var3++;
            var11 = (var8[0] & 255L) << 56
               | (var8[1] & 255L) << 48
               | (var8[2] & 255L) << 40
               | (var8[3] & 255L) << 32
               | (var8[4] & 255L) << 24
               | (var8[5] & 255L) << 16
               | (var8[6] & 255L) << 8
               | var8[7] & 255L;
            var13 = 0;
         }
      }
   }

   static {
      __purxiyInit0();
   }

}
