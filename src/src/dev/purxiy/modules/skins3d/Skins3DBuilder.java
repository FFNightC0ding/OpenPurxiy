// Sourced By DevFFNightmare
package dev.purxiy.modules.skins3d;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

public final class Skins3DBuilder {
   public static final int M = 0;
   public static final int P = 1;
   public static final int K = 2;
   public static final int c = 3;
   public static final int o = 4;
   public static final int A = 5;
   public static final int j = 6;
   public static final int Z = 5;
   public static final float[] I = new float[]{1.0F, 0.5F, 0.5F, 0.5F, 0.5F, 0.5F};
   public static final float[] H = new float[]{1.0F, 0.8F, 0.68F, 0.6F};
   static final int D = 26;
   static final int S = 250;
   public static final int[][] C = new int[][]{{-1, 0, 0}, {1, 0, 0}, {0, -1, 0}, {0, 1, 0}, {0, 0, -1}, {0, 0, 1}};
   private static final int[][] r = new int[][]{
      {-4, -8, -4, 8, 8, 8, 32, 0},
      {-4, 0, -2, 8, 12, 4, 16, 32},
      {-3, -2, -2, 4, 12, 4, 40, 32},
      {-1, -2, -2, 4, 12, 4, 48, 48},
      {-2, 0, -2, 4, 12, 4, 0, 32},
      {-2, 0, -2, 4, 12, 4, 0, 48}
   };
   private static final int[][] u = new int[][]{r[0], r[1], {-2, -2, -2, 3, 12, 4, 40, 32}, {-1, -2, -2, 3, 12, 4, 48, 48}, r[4], r[5]};
   public static final int[][] p = new int[][]{{0, 0}, {16, 16}, {40, 16}, {32, 48}, {0, 16}, {16, 48}};
   public static final float[][] U = new float[][]{
      {0.0F, 0.0F, 0.0F}, {0.0F, 0.0F, 0.0F}, {-5.0F, 2.0F, 0.0F}, {5.0F, 2.0F, 0.0F}, {-1.9F, 12.0F, 0.0F}, {1.9F, 12.0F, 0.0F}
   };
   private static final int[] q = new int[]{-1, -1, 1, -1, 1, 1, -1, 1};
   private static final int[] h = new int[]{-1, -1, -1, 1, 1, 1, 1, -1};


   private static final long[] d = new long[]{
      7388749739387899817L,
      -5036521881619621368L,
      4258831294726988292L,
      -7942138550006231077L,
      -2576688550774914985L,
      8605429779068646339L,
      3043205568668712746L,
      -2778210527809106278L,
      -6219200178028630303L,
      -8170592135971256672L,
      -7836542368915141767L,
      9069669101277175166L,
      2512462786104546577L,
      -7424270916691048221L,
      5038069781615694088L,
      3531834696517635326L,
      6670707001700722535L,
      -6011428447711214699L,
      2794928576154587445L
   };


   private Skins3DBuilder() {
   }

   public static int[] box(int var0, boolean var1) {
      return (var1 ? u : r)[var0];
   }

   public static Skins3DBuilder.TypeR build(int[] var0, boolean var1) {
      long var2 = 130246926358306L;
      long var4 = var2 ^ 71449825659493L;
      if (var0.length < 4096) {
         throw new IllegalArgumentException("A skin has 64x64 pixels, got " + var0.length);
      } else {
         Skins3DBuilder.TypeO[] var6 = new Skins3DBuilder.TypeO[6];

         for (int var7 = 0; var7 < 6; var7++) {
            var6[var7] = C(var4, var0, var7, box(var7, var1));
         }

         return new Skins3DBuilder.TypeR(var6, var1);
      }
   }

   public static Skins3DBuilder.TypeR readModel(InputStream var0, boolean var1) throws IOException {
      Skins3DBuilder.TypeR var19;
      try (DataInputStream var2 = new DataInputStream(new GZIPInputStream(var0))) {
         if (var2.readInt() != 1448627506) {
            throw new IOException("Invalid voxel model signature");
         }

         float var3 = var2.readFloat();
         if (!Float.isFinite(var3) || var3 < 0.0078125F || var3 > 1.0F) {
            throw new IOException("Invalid voxel grid size");
         }

         float[][] var4 = new float[6][3];

         for (float[] var8 : var4) {
            for (int var9 = 0; var9 < 3; var9++) {
               var8[var9] = var2.readFloat();
               if (!Float.isFinite(var8[var9]) || Math.abs(var8[var9]) > 128.0F) {
                  throw new IOException("Invalid voxel joint");
               }
            }
         }

         Skins3DBuilder.TypeO[] var16 = new Skins3DBuilder.TypeO[6];
         int var17 = 0;

         for (int var18 = 0; var18 < 6; var18++) {
            int[] var20 = new int[8];

            for (int var21 = 0; var21 < 3; var21++) {
               var20[var21] = var2.readInt();
               if (Math.abs((long)var20[var21]) > 128L) {
                  throw new IOException("Voxel origin outside model bounds");
               }
            }

            int var22 = var2.readInt();
            if (var22 < 0 || var22 > 100000 - var17) {
               throw new IOException("Too many voxel faces");
            }

            var17 += var22;
            int[] var10 = new int[var22 * 5];

            for (int var11 = 0; var11 < var22; var11++) {
               int var12 = var11 * 5;
               var10[var12] = var2.readInt();
               if ((var10[var12] & 7) >= C.length) {
                  throw new IOException("Invalid voxel normal");
               }

               for (int var13 = 1; var13 <= 4; var13++) {
                  var10[var12 + var13] = var2.readInt();
                  if ((var10[var12 + var13] & 0xFF000000) != 0) {
                     throw new IOException("A sculpture cannot grow with the shell depth");
                  }
               }
            }

            var16[var18] = new Skins3DBuilder.TypeO(var18, var20, var10, var22, var3);
         }

         if (var2.read() != -1) {
            throw new IOException("Trailing voxel model data");
         }

         var19 = new Skins3DBuilder.TypeR(var16, var1, true, var4);
      }

      return var19;
   }

   static int texel(int[] var0, int var1, boolean var2, int var3, int var4) {
      int var5 = var0[3];
      int var6 = var0[5];
      int var7 = var0[6];
      int var8 = var0[7];

      return switch (var1) {
         case 0 -> var2 ? var7 + var6 + var5 + var3 + 64 * (var8 + var6 + var4) : var7 + var6 - 1 - var3 + 64 * (var8 + var6 + var4);
         case 1 -> var2 ? var7 + var6 + var5 + var3 + 64 * (var8 + var6 - 1 - var4) : var7 + var6 + var3 + 64 * (var8 + var6 - 1 - var4);
         default -> var2 ? var7 + 2 * var6 + var5 + var5 - 1 - var3 + 64 * (var8 + var6 + var4) : var7 + var6 + var3 + 64 * (var8 + var6 + var4);
      };
   }

   private static Skins3DBuilder.TypeO C(long var0, int[] var2, int var3, int[] var4) {
      long var5 = 98327183945072L;
      long var7 = 119875124088547L;
      long var9 = 132190785189826L;
      int var11 = var4[3];
      int var12 = var4[4];
      int var13 = var4[5];
      SkinPreset.ensureSourceClassInitialized();
      int var14 = var11 + 2;
      int var15 = var12 + 2;
      int var16 = var13 + 2;
      int var17 = var14 * var15 * var16;
      int[][] var18 = new int[][]{new int[var17], new int[var17], new int[var17]};
      byte[] var19 = new byte[var17];
      int[] var20 = new int[3];
      int[] var21 = new int[3];

      for (int var22 = -1; var22 <= var11; var22++) {
         for (int var23 = -1; var23 <= var12; var23++) {
            long var24 = 59986948201799L;
            if (59986948201799L == 0L) {
               boolean var10000 = false;
            } else {
               int var136 = var24 < 0L ? -1 : 1;
            }

            for (int var27 = -1; var27 <= var13; var27++) {
               long var28 = 59986948201799L;
               if (59986948201799L == 0L) {
                  boolean var137 = false;
               } else {
                  int var138 = var28 < 0L ? -1 : 1;
               }

               int var30 = var22 + 1 + var14 * (var23 + 1 + var15 * (var27 + 1));
               int var31;
               if (var22 < 0) {
                  var31 = -1;
               } else {
                  long var32 = 59986948201798L;
                  if (59986948201798L == 0L) {
                     boolean var139 = false;
                  } else {
                     int var140 = var32 < 0L ? -1 : 1;
                  }

                  var31 = var22 >= var11 ? 1 : 0;
               }

               var20[0] = var31;
               long var90 = 59986948201799L;
               if (59986948201799L == 0L) {
                  boolean var141 = false;
               } else {
                  int var142 = var90 < 0L ? -1 : 1;
               }

               if (var23 < 0) {
                  var31 = -1;
               } else {
                  long var35 = 59986948201799L;
                  if (59986948201799L == 0L) {
                     boolean var143 = false;
                  } else {
                     int var144 = var35 < 0L ? -1 : 1;
                  }

                  var31 = var23 >= var12 ? 1 : 0;
               }

               var20[1] = var31;
               long var93 = 59986948201798L;
               if (59986948201798L == 0L) {
                  boolean var145 = false;
               } else {
                  int var146 = var93 < 0L ? -1 : 1;
               }

               if (var27 < 0) {
                  var31 = -1;
               } else {
                  long var37 = 59986948201799L;
                  if (59986948201799L == 0L) {
                     boolean var147 = false;
                  } else {
                     int var148 = var37 < 0L ? -1 : 1;
                  }

                  var31 = var27 >= var13 ? 1 : 0;
               }

               var20[2] = var31;
               if (var20[0] == 0 && var20[1] == 0 && var20[2] == 0) {
                  var19[var30] = 1;
                  long var96 = 59986948201799L;
                  if (59986948201799L == 0L) {
                     boolean var179 = false;
                  } else {
                     int var180 = var96 < 0L ? -1 : 1;
                  }
               } else {
                  int var95 = Math.clamp((long)var22, 0, var11 - 1);
                  int var38 = Math.clamp((long)var23, 0, var12 - 1);
                  int var39 = Math.clamp((long)var27, 0, var13 - 1);
                  boolean var40 = true;
                  boolean var41 = true;

                  for (int var42 = 0; var42 < 3; var42++) {
                     var21[var42] = -1;
                     int var43 = var20[var42];
                     long var44 = 59986948201798L;
                     if (59986948201798L == 0L) {
                        boolean var149 = false;
                     } else {
                        int var150 = var44 < 0L ? -1 : 1;
                     }

                     long var46 = 59986948201799L;
                     if (59986948201799L == 0L) {
                        boolean var151 = false;
                     } else {
                        int var152 = var46 < 0L ? -1 : 1;
                     }

                     if (var43 == 0) {
                        long var48 = 59986948201799L;
                        if (59986948201799L == 0L) {
                           boolean var153 = false;
                        } else {
                           int var154 = var48 < 0L ? -1 : 1;
                        }
                     } else {
                        int var116 = var42 == 0 ? var39 : var95;
                        long var49 = 59986948201798L;
                        if (59986948201798L == 0L) {
                           boolean var155 = false;
                        } else {
                           int var156 = var49 < 0L ? -1 : 1;
                        }

                        int var51 = var42 == 1 ? var39 : var38;
                        int var52 = texel(var4, var42, var20[var42] > 0, var116, var51);
                        int var53 = var2[var52] >>> 24;
                        long var54 = 59986948201799L;
                        if (59986948201799L == 0L) {
                           boolean var157 = false;
                        } else {
                           int var158 = var54 < 0L ? -1 : 1;
                        }

                        if (var53 < 26) {
                           long var126 = 59986948201799L;
                           if (59986948201799L == 0L) {
                              boolean var161 = false;
                           } else {
                              int var162 = var126 < 0L ? -1 : 1;
                           }

                           var40 = false;
                           break;
                        }

                        long var56 = 59986948201799L;
                        if (59986948201799L == 0L) {
                           boolean var159 = false;
                        } else {
                           int var160 = var56 < 0L ? -1 : 1;
                        }

                        var41 &= var53 >= 250;
                        var21[var42] = var52;
                     }
                  }

                  long var105 = 59986948201798L;
                  if (59986948201798L == 0L) {
                     boolean var163 = false;
                  } else {
                     int var164 = var105 < 0L ? -1 : 1;
                  }

                  if (!var40) {
                     var18[0][var30] = var18[1][var30] = var18[2][var30] = -1;
                  } else {
                     for (int var45 = 0; var45 < 3; var45++) {
                        int var107 = var21[var45];
                        long var114 = 59986948201799L;
                        if (59986948201799L == 0L) {
                           boolean var165 = false;
                        } else {
                           int var166 = var114 < 0L ? -1 : 1;
                        }

                        int var117 = var107;
                        long var118 = 59986948201799L;
                        if (59986948201799L == 0L) {
                           boolean var167 = false;
                        } else {
                           int var168 = var118 < 0L ? -1 : 1;
                        }

                        if (var107 < 0) {
                           long var120 = 59986948201798L;
                           if (59986948201798L == 0L) {
                              boolean var169 = false;
                           } else {
                              int var170 = var120 < 0L ? -1 : 1;
                           }

                           long var123 = 59986948201799L;
                           if (59986948201799L == 0L) {
                              boolean var171 = false;
                           } else {
                              int var172 = var123 < 0L ? -1 : 1;
                           }

                           if (var21[1] >= 0) {
                              var107 = var21[1];
                           } else {
                              long var55 = 59986948201798L;
                              if (59986948201798L == 0L) {
                                 boolean var173 = false;
                              } else {
                                 int var174 = var55 < 0L ? -1 : 1;
                              }

                              long var57 = 59986948201798L;
                              if (59986948201798L == 0L) {
                                 boolean var175 = false;
                              } else {
                                 int var176 = var57 < 0L ? -1 : 1;
                              }

                              var107 = var21[2] >= 0 ? var21[2] : var21[0];
                           }

                           var117 = var107;
                        }

                        var18[var45][var30] = var117;
                     }

                     long var110 = 59986948201799L;
                     if (59986948201799L == 0L) {
                        boolean var177 = false;
                     } else {
                        int var178 = var110 < 0L ? -1 : 1;
                     }

                     var19[var30] = (byte)(var41 ? 3 : 2);
                  }
               }
            }

            long var83 = 59986948201799L;
            if (59986948201799L == 0L) {
               boolean var181 = false;
            } else {
               int var182 = var83 < 0L ? -1 : 1;
            }
         }

         long var81 = 59986948201798L;
         if (59986948201798L == 0L) {
            boolean var183 = false;
         } else {
            int var184 = var81 < 0L ? -1 : 1;
         }
      }

      short var80 = 3257;
      long var82 = 59986948201798L;
      if (59986948201798L == 0L) {
         boolean var185 = false;
      } else {
         int var186 = var82 < 0L ? -1 : 1;
      }

      int[] var26 = new int[320];
      int var84 = 0;
      int[] var85 = new int[3];
      int[] var29 = new int[3];
      int[] var86 = new int[4];
      int[] var89 = new int[4];
      int[] var91 = new int[4];

      for (int var33 = -1; var33 <= var11; var33++) {
         for (int var34 = -1; var34 <= var12; var34++) {
            long var94 = 59986948201798L;
            if (59986948201798L == 0L) {
               boolean var187 = false;
            } else {
               int var188 = var94 < 0L ? -1 : 1;
            }

            int var97 = -1;

            while (var97 <= var13) {
               long var98 = 59986948201799L;
               if (59986948201799L == 0L) {
                  boolean var189 = false;
               } else {
                  int var190 = var98 < 0L ? -1 : 1;
               }

               int var100 = var33 + 1 + var14 * (var34 + 1 + var15 * (var97 + 1));
               int var101 = var19[var100];
               long var106 = 59986948201798L;
               if (59986948201798L == 0L) {
                  boolean var191 = false;
               } else {
                  int var192 = var106 < 0L ? -1 : 1;
               }

               if (var101 < 2) {
                  long var112 = 59986948201799L;
                  if (59986948201799L == 0L) {
                     boolean var223 = false;
                  } else {
                     int var224 = var112 < 0L ? -1 : 1;
                  }
               } else {
                  for (int var111 = 0; var111 < 6; var111++) {
                     int var115 = var111 >> 1;
                     long var47 = 59986948201799L;
                     if (59986948201799L == 0L) {
                        boolean var193 = false;
                     } else {
                        int var194 = var47 < 0L ? -1 : 1;
                     }

                     long var119 = 59986948201798L;
                     if (59986948201798L == 0L) {
                        boolean var195 = false;
                     } else {
                        int var196 = var119 < 0L ? -1 : 1;
                     }

                     int var121 = (var111 & 1) == 0 ? -1 : 1;
                     var85[0] = var33;
                     var85[1] = var34;
                     var85[2] = var97;
                     var85[var115] += var121;
                     byte var122 = u(var19, var85, var11, var12, var5, var13);
                     long var124 = 59986948201799L;
                     if (59986948201799L == 0L) {
                        boolean var197 = false;
                     } else {
                        int var198 = var124 < 0L ? -1 : 1;
                     }

                     long var125 = 59986948201799L;
                     if (59986948201799L == 0L) {
                        boolean var199 = false;
                     } else {
                        int var200 = var125 < 0L ? -1 : 1;
                     }

                     if (var122 != 1) {
                        long var127 = 59986948201799L;
                        if (59986948201799L == 0L) {
                           boolean var201 = false;
                        } else {
                           int var202 = var127 < 0L ? -1 : 1;
                        }

                        long var59 = 59986948201799L;
                        if (59986948201799L == 0L) {
                           boolean var203 = false;
                        } else {
                           int var204 = var59 < 0L ? -1 : 1;
                        }

                        if (var122 == 3) {
                           long var128 = 59986948201798L;
                           if (59986948201798L == 0L) {
                              boolean var221 = false;
                           } else {
                              int var222 = var128 < 0L ? -1 : 1;
                           }
                        } else {
                           int var61 = (var115 + 1) % 3;
                           int var62 = (var115 + 2) % 3;
                           int[] var63 = var121 > 0 ? q : h;

                           for (int var64 = 0; var64 < 4; var64++) {
                              int var65 = var63[2 * var64];
                              var101 = var63[2 * var64 + 1];
                              long var66 = 59986948201798L;
                              if (59986948201798L == 0L) {
                                 boolean var205 = false;
                              } else {
                                 int var206 = var66 < 0L ? -1 : 1;
                              }

                              int var109;
                              label657: {
                                 var29[var115] = var121 > 0 ? 1 : 0;
                                 var29[var61] = var65 > 0 ? 1 : 0;
                                 var29[var62] = var101 > 0 ? 1 : 0;
                                 var89[var64] = l(var7, var33, var34, var97, var29, var11, var12, var13);
                                 boolean var69 = P(var19, var85, var61, var65, var9, -1, 0, var11, var12, var13);
                                 boolean var70 = P(var19, var85, var62, var101, var9, -1, 0, var11, var12, var13);
                                 boolean var71 = P(var19, var85, var61, var65, var9, var62, var101, var11, var12, var13);
                                 if (var69) {
                                    long var72 = 59986948201798L;
                                    if (59986948201798L == 0L) {
                                       boolean var207 = false;
                                    } else {
                                       int var208 = var72 < 0L ? -1 : 1;
                                    }

                                    if (var70) {
                                       var109 = 3;
                                       break label657;
                                    }
                                 }

                                 var109 = (var69 ? 1 : 0) + (var70 ? 1 : 0) + (var71 ? 1 : 0);
                              }

                              var86[var64] = var109;
                              var91[var64] = var29[var61] | var29[var62] << 1;
                           }

                           long var129 = 59986948201799L;
                           if (59986948201799L == 0L) {
                              boolean var209 = false;
                           } else {
                              int var210 = var129 < 0L ? -1 : 1;
                           }

                           var101 = var86[0];
                           long var130 = 59986948201798L;
                           if (59986948201798L == 0L) {
                              boolean var211 = false;
                           } else {
                              int var212 = var130 < 0L ? -1 : 1;
                           }

                           int var68 = Math.abs(var101 - var86[2]) > Math.abs(var86[1] - var86[3]) ? 1 : 0;
                           int var131 = var18[var115][var100];
                           int var132 = var111 | (var131 & 63) << 3 | var131 >>> 6 << 9;
                           long var134 = 59986948201798L;
                           if (59986948201798L == 0L) {
                              boolean var213 = false;
                           } else {
                              int var214 = var134 < 0L ? -1 : 1;
                           }

                           var101 = var84 * 5 + 5;
                           long var73 = 59986948201798L;
                           if (59986948201798L == 0L) {
                              boolean var215 = false;
                           } else {
                              int var216 = var73 < 0L ? -1 : 1;
                           }

                           if (var101 > var26.length) {
                              var26 = Arrays.copyOf(var26, var26.length * 2);
                           }

                           int var75 = var84 * 5;

                           for (int var76 = 0; var76 < 4; var76++) {
                              int var77 = var76 + var68 & 3;
                              var132 |= var86[var77] << 15 + 2 * var76;
                              long var78 = 59986948201799L;
                              if (59986948201799L == 0L) {
                                 boolean var217 = false;
                              } else {
                                 int var218 = var78 < 0L ? -1 : 1;
                              }

                              var132 |= var91[var77] << 23 + 2 * var76;
                              var26[var75 + 1 + var76] = var89[var77];
                           }

                           long var135 = 59986948201798L;
                           if (59986948201798L == 0L) {
                              boolean var219 = false;
                           } else {
                              int var220 = var135 < 0L ? -1 : 1;
                           }

                           var26[var75] = var132;
                           var84++;
                        }
                     }
                  }
               }

               var97++;
               long var113 = 59986948201798L;
               if (59986948201798L == 0L) {
                  boolean var225 = false;
               } else {
                  int var226 = var113 < 0L ? -1 : 1;
               }
            }

            long var99 = 59986948201799L;
            if (59986948201799L == 0L) {
               boolean var227 = false;
            } else {
               int var228 = var99 < 0L ? -1 : 1;
            }
         }

         long var92 = 59986948201799L;
         if (59986948201799L == 0L) {
            boolean var229 = false;
         } else {
            int var230 = var92 < 0L ? -1 : 1;
         }
      }

      return new Skins3DBuilder.TypeO(var3, var4, Arrays.copyOf(var26, var84 * 5), var84);
   }

   private static byte u(byte[] var0, int[] var1, int var2, int var3, long var4, int var6) {
      SkinPreset.ensureSourceClassInitialized();
      int var7 = var1[0];
      long var8 = 98327183945071L;
      if (98327183945071L == 0L) {
         boolean var10000 = false;
      } else {
         int var36 = var8 < 0L ? -1 : 1;
      }

      long var11 = 98327183945071L;
      if (98327183945071L == 0L) {
         boolean var37 = false;
      } else {
         int var38 = var11 < 0L ? -1 : 1;
      }

      if (var7 >= -1) {
         var7 = var1[0];
         long var13 = 98327183945072L;
         if (98327183945072L == 0L) {
            boolean var39 = false;
         } else {
            int var40 = var13 < 0L ? -1 : 1;
         }

         long var15 = 98327183945071L;
         if (98327183945071L == 0L) {
            boolean var41 = false;
         } else {
            int var42 = var15 < 0L ? -1 : 1;
         }

         if (var7 <= var2) {
            var7 = var1[1];
            long var17 = 98327183945072L;
            if (98327183945072L == 0L) {
               boolean var43 = false;
            } else {
               int var44 = var17 < 0L ? -1 : 1;
            }

            long var19 = 98327183945072L;
            if (98327183945072L == 0L) {
               boolean var45 = false;
            } else {
               int var46 = var19 < 0L ? -1 : 1;
            }

            if (var7 >= -1) {
               var7 = var1[1];
               long var21 = 98327183945071L;
               if (98327183945071L == 0L) {
                  boolean var47 = false;
               } else {
                  int var48 = var21 < 0L ? -1 : 1;
               }

               long var23 = 98327183945071L;
               if (98327183945071L == 0L) {
                  boolean var49 = false;
               } else {
                  int var50 = var23 < 0L ? -1 : 1;
               }

               if (var7 <= var3) {
                  var7 = var1[2];
                  long var25 = 98327183945072L;
                  if (98327183945072L == 0L) {
                     boolean var51 = false;
                  } else {
                     int var52 = var25 < 0L ? -1 : 1;
                  }

                  long var27 = 98327183945071L;
                  if (98327183945071L == 0L) {
                     boolean var53 = false;
                  } else {
                     int var54 = var27 < 0L ? -1 : 1;
                  }

                  if (var7 >= -1) {
                     var7 = var1[2];
                     long var29 = 98327183945071L;
                     if (98327183945071L == 0L) {
                        boolean var55 = false;
                     } else {
                        int var56 = var29 < 0L ? -1 : 1;
                     }

                     if (var7 <= var6) {
                        return var0[var1[0] + 1 + (var2 + 2) * (var1[1] + 1 + (var3 + 2) * (var1[2] + 1))];
                     }
                  }
               }
            }
         }
      }

      return 0;
   }

   private static boolean P(byte[] var0, int[] var1, int var2, int var3, long var4, int var6, int var7, int var8, int var9, int var10) {
      long var11 = 98327183945072L;
      int var13 = var1[0];
      int var14 = var1[1];
      int var15 = var1[2];
      SkinPreset.ensureSourceClassInitialized();
      int[] var16 = new int[]{var13, var14, var15};
      var16[var2] += var3;
      if (var6 >= 0) {
         var16[var6] += var7;
      }

      return u(var0, var16, var8, var9, var11, var10) != 0;
   }

   private static int l(long var0, int var2, int var3, int var4, int[] var5, int var6, int var7, int var8) {
      SkinPreset.ensureSourceClassInitialized();
      int var9 = 0;
      int[] var10 = new int[]{var2, var3, var4};
      int[] var11 = new int[]{var6, var7, var8};

      for (int var12 = 0; var12 < 3; var12++) {
         int var13 = var10[var12];
         int var14 = var11[var12];
         int var15 = var5[var12];
         long var16 = 119875124088546L;
         if (119875124088546L == 0L) {
            boolean var10000 = false;
         } else {
            int var32 = var16 < 0L ? -1 : 1;
         }

         int var19;
         int var20;
         if (var13 < 0) {
            var19 = 0;
            var20 = var15 == 0 ? 1 : 0;
            long var21 = 119875124088546L;
            if (119875124088546L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var21 < 0L ? -1 : 1;
            }
         } else {
            long var31 = 119875124088546L;
            if (119875124088546L == 0L) {
               boolean var35 = false;
            } else {
               int var36 = var31 < 0L ? -1 : 1;
            }

            long var23 = 119875124088546L;
            if (119875124088546L == 0L) {
               boolean var37 = false;
            } else {
               int var38 = var23 < 0L ? -1 : 1;
            }

            if (var13 >= var14) {
               var19 = var14;
               long var25 = 119875124088547L;
               if (119875124088547L == 0L) {
                  boolean var39 = false;
               } else {
                  int var40 = var25 < 0L ? -1 : 1;
               }

               var20 = var15 == 1 ? 2 : 0;
               long var27 = 119875124088546L;
               if (119875124088546L == 0L) {
                  boolean var41 = false;
               } else {
                  int var42 = var27 < 0L ? -1 : 1;
               }
            } else {
               var19 = var13 + var15;
               var20 = 0;
            }
         }

         var9 |= var19 << 8 * var12;
         var9 |= var20 << 24 + 2 * var12;
      }

      return var9;
   }

public static final class TypeO {
      public final int d;
      public final int[] F;
      public final int[] V;
      public final int p;
      public final float h;
      public final float r;
      public final float t;
      public static int R;

      TypeO(int var1, int[] var2, int[] var3, int var4) {
         this(var1, var2, var3, var4, 1.0F);
      }

      TypeO(int var1, int[] var2, int[] var3, int var4, float var5) {
         this.d = var1;
         this.F = var2;
         this.V = var3;
         this.p = var4;
         this.h = var5;
         float var6 = Float.NEGATIVE_INFINITY;
         float var7 = Float.POSITIVE_INFINITY;

         for (int var8 = 0; var8 < var4; var8++) {
            for (int var9 = 1; var9 <= 4; var9++) {
               float var10 = var2[1] + (var3[var8 * 5 + var9] >>> 8 & 0xFF) * var5;
               var6 = Math.max(var6, var10);
               var7 = Math.min(var7, var10);
            }
         }

         this.r = var4 == 0 ? var2[1] + var2[4] : var6;
         boolean var11 = var1 == 4 || var1 == 5;
         this.t = var11 && var5 < 1.0F && var4 > 0 ? (Math.max(0.0F, var7) + var6) / 2.0F : Float.NaN;
      }

      public int normal(int var1) {
         return this.V[var1 * 5] & 7;
      }

      public int texelU(int var1) {
         return this.V[var1 * 5] >>> 3 & 63;
      }

      public int texelV(int var1) {
         return this.V[var1 * 5] >>> 9 & 63;
      }

      public int shade(int var1, int var2) {
         return this.V[var1 * 5] >>> 15 + 2 * var2 & 3;
      }

      public int uvCorner(int var1, int var2) {
         return this.V[var1 * 5] >>> 23 + 2 * var2 & 3;
      }

      public float coordinate(int var1, int var2, int var3, float var4) {
         int var5 = this.V[var1 * 5 + 1 + var2];
         int var6 = var5 >>> 8 * var3 & 0xFF;
         int var7 = var5 >>> 24 + 2 * var3 & 3;
         return this.F[var3] + var6 * this.h + (var7 == 1 ? -var4 : (var7 == 2 ? var4 : 0.0F));
      }
   }

   public static final class TypeR {
      public final Skins3DBuilder.TypeO[] R;
      public final boolean u;
      public final boolean C;
      public final float[][] y;
      public final boolean n;

      TypeR(Skins3DBuilder.TypeO[] var1, boolean var2) {
         this(var1, var2, false);
      }

      TypeR(Skins3DBuilder.TypeO[] var1, boolean var2, boolean var3) {
         this(var1, var2, var3, Skins3DBuilder.U);
      }

      TypeR(Skins3DBuilder.TypeO[] var1, boolean var2, boolean var3, float[][] var4) {
         this.R = var1;
         this.u = var2;
         this.C = var3;
         this.y = var4;
         this.n = var3 && Arrays.equals(var4[0], var4[1]);
      }

      public int quads() {
         int var1 = 0;

         for (Skins3DBuilder.TypeO var5 : this.R) {
            var1 += var5.p;
         }

         return var1;
      }
   }
}
