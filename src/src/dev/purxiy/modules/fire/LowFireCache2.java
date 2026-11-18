// Sourced By DevFFNightmare
package dev.purxiy.modules.fire;

import com.mojang.blaze3d.systems.CommandEncoder;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.GpuTexture;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientApi;
import dev.purxiy.loader.fabric.client.ClientSupport;
import dev.purxiy.loader.fabric.mixin.SpriteAnimationStateAccessor;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap.Entry;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Arrays;
import java.util.Objects;
import net.minecraft.class_1011;
import net.minecraft.class_1058;
import net.minecraft.class_1088;
import net.minecraft.class_12253;
import net.minecraft.class_310;
import net.minecraft.class_4722;
import net.minecraft.class_4725;
import net.minecraft.class_4730;
import net.minecraft.class_7764;
import net.minecraft.class_7764.class_12298;

public final class LowFireCache2 {
   private static final class_4730[] x = new class_4730[]{
      class_1088.field_5397, class_1088.field_5370, class_4722.field_56356.method_67274("soul_fire_0"), class_4722.field_56356.method_67274("soul_fire_1")
   };
   private static final double N = Math.PI * 2;
   private static final float Y = -0.11F;
   private static final float L = 1.1F;
   private static final class_1058[] j = new class_1058[x.length];
   private static int y;
   private static final class_1058[] K = new class_1058[x.length];
   private static int n;


   private static final long[] c = new long[]{
      5368514944088521526L,
      -7621160180054517516L,
      4108532790014602121L,
      584994127267950503L,
      4320668766771698169L,
      6791105415133068054L,
      -4555719601855449553L,
      2144767948143686365L
   };


   private LowFireCache2() {
   }

   public static boolean active() {
      return y != 0;
   }

   static void update() {
      long var0 = 34814606371377L;
      long var2 = var0 ^ 137181319789924L;
      long var4 = var0 ^ 15421875720480L;
      long var6 = var0 ^ 62480049058739L;
      int var8 = (int)((var0 ^ 62480049058739L) >>> 48);
      int var9 = (int)((var0 ^ 62480049058739L) << 16 >>> 32);
      int var10 = (int)(var6 << 48 >>> 48);
      LowFireModule var11 = LowFireRenderer.ak;
      class_310 var12 = class_310.method_1551();
      boolean var13 = var11.enabled() && var11.s.enabled();
      LowFireLoader var14 = LowFireLoader.resolve(var11.R.get(), var11.c.argb());
      float var15 = var11.G.floatValue();
      int var16 = var13 ? Objects.hash(var14.F, var14.c, var14.g, Float.floatToIntBits(var15)) | 1 : 0;
      class_1058[] var17 = new class_1058[x.length];

      for (int var18 = 0; var18 < x.length; var18++) {
         var17[var18] = var12.method_72703().method_73030(x[var18]);
      }

      if (!Arrays.equals((Object[])var17, (Object[])j)) {
         y = 0;
      }

      if (var16 != y && (var16 != n || !Arrays.equals((Object[])var17, (Object[])K))) {
         try {
            if (var13) {
               float[] var30 = LowFireCache.ramp(LowFireCache.colours(var14));
               int[] var31 = W(var30, 0.0F, 1.0F, var4);
               int[] var32 = W(var30, -0.11F, 1.1F, var4);

               for (int var33 = 0; var33 < x.length; var33++) {
                  class_1058 var22 = var17[var33];
                  int[] var23 = var33 < 2 ? var31 : var32;
                  int var24 = var33 + 1;
                  a((short)var8, var9, var22, var23, var10, var15, var24);
               }
            } else {
               for (class_1058 var21 : var17) {
                  o(var2, var21);
               }
            }

            y = var16;
            System.arraycopy(var17, 0, j, 0, x.length);
         } catch (Throwable var28) {
            LowFireRenderer.metric("draw_failures");
            n = var16;
            System.arraycopy(var17, 0, K, 0, x.length);
            ClientServices.LOGGER.error("Purxiy could not rewrite the fire on the ground", var28);
         }
      }
   }

   private static void a(short var0, int var1, class_1058 var2, int[] var3, int var4, float var5, int var6) {
      long var7 = 43407844039042L;
      long var9 = var7 ^ 26244625054530L;
      int var11 = (int)((var7 ^ 26244625054530L) >>> 32);
      int var12 = (int)((var7 ^ 26244625054530L) << 32 >>> 48);
      int var13 = (int)(var9 << 48 >>> 48);
      int var14 = (int)((var7 ^ 134304852708148L) >>> 48);
      long var15 = (var7 ^ 134304852708148L) << 16 >>> 16;
      long var17 = var7 ^ 95979187263465L;
      class_7764 var19 = var2.method_45851();
      LowFireLoader.ensureSourceClassInitialized();
      ClientApi var20 = (ClientApi)var19;
      int var21 = var20.purxiy$images().length;
      int var22 = var19.method_45807();
      int var23 = var19.method_45815();
      CommandEncoder var24 = RenderSystem.getDevice().createCommandEncoder();
      class_12298 var25 = var20.purxiy$animation();
      if (var25 == null) {
         class_1011[] var26 = Y(var19, O(var22, var23, 0, 1, var5, var17, var6, var3), var21);

         try {
            j((char)var14, var24, var2, var26, var15);
         } finally {
            K(var11, (char)var12, (short)var13, var26);
         }
      } else {
         Int2ObjectMap var40 = ((SpriteAnimationStateAccessor)var25).purxiy$frames();
         int var27 = var40.keySet().intStream().min().orElse(0);

         for (Entry var29 : ((Iterable<Entry>)(Object)var40.int2ObjectEntrySet())) {
            class_1011[] var30 = Y(var19, O(var22, var23, var29.getIntKey(), var40.size(), var5, var17, var6, var3), var21);

            try {
               GpuTexture var31 = ((GpuTextureView)var29.getValue()).texture();

               for (int var32 = 0; var32 < var30.length; var32++) {
                  var24.writeToTexture(var31, var30[var32], var32, 0, 0, 0, var30[var32].method_4307(), var30[var32].method_4323(), 0, 0);
               }

               int var41 = 662351135;
               var41 = var29.getIntKey();
               if (var41 == var27) {
                  j((char)var14, var24, var2, var30, var15);
               }
            } finally {
               K(var11, (char)var12, (short)var13, var30);
            }
         }
      }
   }

   private static void o(long var0, class_1058 var2) {
      byte var3 = 0;
      long var4 = 102653545042614L;
      LowFireLoader.ensureSourceClassInitialized();
      class_7764 var6 = var2.method_45851();
      ClientApi var7 = (ClientApi)var6;
      class_1011[] var8 = var7.purxiy$images();
      int var9 = var6.method_45807();
      int var10 = var6.method_45815();
      int var11 = Math.max(1, var8[0].method_4307() / var9);
      CommandEncoder var12 = RenderSystem.getDevice().createCommandEncoder();
      class_12298 var13 = var7.purxiy$animation();
      long var14 = 109323760637781L;
      if (109323760637781L == 0L) {
         boolean var10000 = false;
      } else {
         int var29 = var14 < 0L ? -1 : 1;
      }

      if (var13 != null) {
         ObjectIterator var17 = ((SpriteAnimationStateAccessor)var13).purxiy$frames().int2ObjectEntrySet().iterator();

         while (var17.hasNext()) {
            Entry var18 = (Entry)var17.next();
            int var19 = var18.getIntKey();
            int var20 = var19 % var11 * var9;
            int var21 = var19 / var11 * var10;
            long var22 = 109323760637781L;
            if (109323760637781L == 0L) {
               boolean var30 = false;
            } else {
               int var31 = var22 < 0L ? -1 : 1;
            }

            for (int var24 = 0; var24 < var8.length; var24++) {
               var12.writeToTexture(
                  ((GpuTextureView)var18.getValue()).texture(), var8[var24], var24, 0, 0, 0, var9 >> var24, var10 >> var24, var20 >> var24, var21 >> var24
               );
            }

            long var28 = 109323760637780L;
            if (109323760637780L == 0L) {
               boolean var32 = false;
            } else {
               int var33 = var28 < 0L ? -1 : 1;
            }
         }
      }

      long var27 = 109323760637780L;
      if (109323760637780L == 0L) {
         boolean var34 = false;
      } else {
         int var35 = var27 < 0L ? -1 : 1;
      }

      j((char)var3, var12, var2, var8, var4);
   }

   private static void j(char var0, CommandEncoder var1, class_1058 var2, class_1011[] var3, long var4) {
      LowFireLoader.ensureSourceClassInitialized();
      GpuTexture var6 = class_310.method_1551().method_1531().method_4619(var2.method_45852()).method_68004();
      class_7764 var7 = var2.method_45851();
      int var8 = var7.method_45807();
      int var9 = var7.method_45815();
      int var10 = Math.round(var2.method_4594() * var6.getWidth(0)) - var2.method_35806();
      int var11 = Math.min(var3.length, var6.getMipLevels());

      for (int var12 = 0; var12 < var11; var12++) {
         int var13 = Math.max(1, var8 >> var12);
         int var14 = Math.max(1, var9 >> var12);
         int var15 = var10 >> var12;
         int var16 = var8 + 2 * var10 >> var12;
         int var17 = var9 + 2 * var10 >> var12;
         if (var16 <= 0 || var17 <= 0) {
            break;
         }

         try (class_1011 var18 = new class_1011(var16, var17, false)) {
            for (int var19 = 0; var19 < var17; var19++) {
               int var20 = var19 - var15;
               long var21 = 102653545042614L;
               if (102653545042614L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var32 = var21 < 0L ? -1 : 1;
               }

               int var24 = Math.clamp((long)var20, 0, var14 - 1);

               for (int var25 = 0; var25 < var16; var25++) {
                  long var26 = 102653545042613L;
                  if (102653545042613L == 0L) {
                     boolean var33 = false;
                  } else {
                     int var34 = var26 < 0L ? -1 : 1;
                  }

                  var18.method_61941(var25, var19, var3[var12].method_61940(Math.clamp((long)(var25 - var15), 0, var13 - 1), var24));
               }

               long var31 = 102653545042613L;
               if (102653545042613L == 0L) {
                  boolean var35 = false;
               } else {
                  int var36 = var31 < 0L ? -1 : 1;
               }
            }

            var1.writeToTexture(var6, var18, var12, 0, var2.method_35806() >> var12, var2.method_35807() >> var12, var16, var17, 0, 0);
            long var30 = 102653545042614L;
            if (102653545042614L == 0L) {
               boolean var37 = false;
            } else {
               int var38 = var30 < 0L ? -1 : 1;
            }
         }
      }
   }

   private static class_1011[] Y(class_7764 var0, class_1011 var1, int var2) {
      return class_4725.method_24102(var0.method_45816(), new class_1011[]{var1}, var2 - 1, class_12253.field_64076, 0.0F);
   }

   private static void K(int var0, char var1, short var2, class_1011[] var3) {
      LowFireLoader.ensureSourceClassInitialized();

      for (class_1011 var7 : var3) {
         var7.close();
      }
   }

   private static class_1011 O(int var0, int var1, int var2, int var3, float var4, long var5, int var7, int[] var8) {
      LowFireLoader.ensureSourceClassInitialized();
      class_1011 var9 = new class_1011(var0, var1, true);
      float var10 = var0 / 16.0F;
      double var11 = (double)var2 / var3;
      float var13 = Math.max(2.5F * var10, var4 * var1);
      float var14 = var0 / 3.0F;
      float[] var15 = new float[var0 * var1];
      Arrays.fill(var15, -1.0F);
      int var16 = 0;

      while (var16 < 3) {
         float var17 = LowFireRenderer.hash(var16, var7, 1);
         float var18 = LowFireRenderer.hash(var16, var7, 2);
         long var19 = 123352673095275L;
         if (123352673095275L == 0L) {
            boolean var10000 = false;
         } else {
            int var96 = var19 < 0L ? -1 : 1;
         }

         float var22 = LowFireRenderer.hash(var16, var7, 3);
         float var23 = (var16 + 0.5F) * var14 + (var17 - 0.5F) * var14 * 0.35F;
         int var24 = 1 + (var16 + var7) % 2;
         float var25 = var13
            * (
               0.68F
                  + 0.14F * var22
                  + 0.18F * (float)Math.sin((Math.PI * 2) * (var11 * var24 + var18))
                  + 0.06F * (float)Math.sin((Math.PI * 2) * (var11 * 3.0 + var17))
            );
         float var26 = var14 * (0.56F + 0.12F * var18);
         float var27 = (float)Math.sin((Math.PI * 2) * (var11 * var24 + var17)) * 1.1F * var10;
         int var28 = 0;

         while (var28 < var1) {
            float var29 = var1 - 1 - var28 + 0.5F;
            float var30 = var29 / var25;
            long var31 = 123352673095274L;
            if (123352673095274L == 0L) {
               boolean var97 = false;
            } else {
               int var98 = var31 < 0L ? -1 : 1;
            }

            if (var30 >= 1.0F) {
               long var76 = 123352673095275L;
               if (123352673095275L == 0L) {
                  boolean var107 = false;
               } else {
                  int var108 = var76 < 0L ? -1 : 1;
               }
            } else {
               float var33 = var26 * (float)Math.pow(1.0F - var30, 0.75);
               float var34 = var23 + var27 * var30 * var30;

               for (int var35 = 0; var35 < var0; var35++) {
                  int var36 = -var0;
                  long var37 = 123352673095274L;
                  if (123352673095274L == 0L) {
                     boolean var99 = false;
                  } else {
                     int var100 = var37 < 0L ? -1 : 1;
                  }

                  for (int var40 = var36; var40 <= var0; var40 += var0) {
                     float var41 = var33 - Math.abs(var35 + 0.5F + var40 - var34);
                     long var42 = 123352673095275L;
                     if (123352673095275L == 0L) {
                        boolean var101 = false;
                     } else {
                        int var102 = var42 < 0L ? -1 : 1;
                     }

                     float var44;
                     var36 = (var44 = var41 - 0.0F) == 0.0F ? 0 : (var44 < 0.0F ? -1 : 1);
                     long var45 = 123352673095275L;
                     if (123352673095275L == 0L) {
                        boolean var103 = false;
                     } else {
                        int var104 = var45 < 0L ? -1 : 1;
                     }

                     if (var36 > 0) {
                        int var47 = var28 * var0 + var35;
                        var15[var47] = Math.max(var15[var47], Math.min(var41, (var25 - var29) * 0.7F));
                     }
                  }

                  long var89 = 123352673095274L;
                  if (123352673095274L == 0L) {
                     boolean var105 = false;
                  } else {
                     int var106 = var89 < 0L ? -1 : 1;
                  }
               }
            }

            var28++;
            long var77 = 123352673095275L;
            if (123352673095275L == 0L) {
               boolean var109 = false;
            } else {
               int var110 = var77 < 0L ? -1 : 1;
            }
         }

         var16++;
         long var71 = 123352673095275L;
         if (123352673095275L == 0L) {
            boolean var111 = false;
         } else {
            int var112 = var71 < 0L ? -1 : 1;
         }
      }

      long var58 = 123352673095274L;
      if (123352673095274L == 0L) {
         boolean var113 = false;
      } else {
         int var114 = var58 < 0L ? -1 : 1;
      }

      int var20 = 0;

      while (true) {
         long var21 = 123352673095274L;
         if (123352673095274L == 0L) {
            boolean var115 = false;
         } else {
            int var116 = var21 < 0L ? -1 : 1;
         }

         if (var20 >= var1) {
            int var62 = Math.max(1, Math.round(var10));
            long var64 = 123352673095274L;
            if (123352673095274L == 0L) {
               boolean var135 = false;
            } else {
               int var136 = var64 < 0L ? -1 : 1;
            }

            var16 = var62;

            for (int var66 = 0; var66 < 2; var66++) {
               double var69 = LowFireRenderer.frac(var11 * (1 + var66) + LowFireRenderer.hash(var66, var7, 7));
               long var73 = 123352673095274L;
               if (123352673095274L == 0L) {
                  boolean var137 = false;
               } else {
                  int var138 = var73 < 0L ? -1 : 1;
               }

               if (!(var69 >= 0.35)) {
                  double var32 = var69 / 0.35;
                  int var79 = Math.floorMod((int)((var66 + 0.5F) * var14 * 1.5F + LowFireRenderer.hash(var66, var7, 8) * var14), var0);
                  int var82 = 5548;
                  int var85 = (int)Math.round(var1 - 1 - (var13 * 0.9 + var32 * var13 * 0.8));
                  int var86 = 0xFF000000 | (var32 < 0.5 ? var8[2] : var8[1]);

                  for (int var87 = 0; var87 < var16; var87++) {
                     long var88 = 123352673095274L;
                     if (123352673095274L == 0L) {
                        boolean var139 = false;
                     } else {
                        int var140 = var88 < 0L ? -1 : 1;
                     }

                     for (int var91 = 0; var91 < var16; var91++) {
                        var82 = var79 + var91;
                        long var94 = 123352673095275L;
                        if (123352673095275L == 0L) {
                           boolean var141 = false;
                        } else {
                           int var142 = var94 < 0L ? -1 : 1;
                        }

                        int var95 = var85 - var87;
                        long var46 = 123352673095275L;
                        if (123352673095275L == 0L) {
                           boolean var143 = false;
                        } else {
                           int var144 = var46 < 0L ? -1 : 1;
                        }

                        long var48 = 123352673095274L;
                        if (123352673095274L == 0L) {
                           boolean var145 = false;
                        } else {
                           int var146 = var48 < 0L ? -1 : 1;
                        }

                        if (var82 < var0) {
                           long var50 = 123352673095274L;
                           if (123352673095274L == 0L) {
                              boolean var147 = false;
                           } else {
                              int var148 = var50 < 0L ? -1 : 1;
                           }

                           if (var95 >= 0) {
                              long var52 = 123352673095274L;
                              if (123352673095274L == 0L) {
                                 boolean var149 = false;
                              } else {
                                 int var150 = var52 < 0L ? -1 : 1;
                              }

                              if (var95 < var1) {
                                 long var54 = 123352673095274L;
                                 if (123352673095274L == 0L) {
                                    boolean var151 = false;
                                 } else {
                                    int var152 = var54 < 0L ? -1 : 1;
                                 }

                                 if (var9.method_61940(var82, var95) >>> 24 == 0) {
                                    var9.method_61941(var82, var95, var86);
                                 }
                              }
                           }
                        }
                     }

                     long var92 = 123352673095275L;
                     if (123352673095275L == 0L) {
                        boolean var153 = false;
                     } else {
                        int var154 = var92 < 0L ? -1 : 1;
                     }
                  }
               }

               long var75 = 123352673095275L;
               if (123352673095275L == 0L) {
                  boolean var155 = false;
               } else {
                  int var156 = var75 < 0L ? -1 : 1;
               }
            }

            return var9;
         }

         float var63 = (var1 - 1 - var20) / var10;
         long var65 = 123352673095274L;
         if (123352673095274L == 0L) {
            boolean var117 = false;
         } else {
            int var118 = var65 < 0L ? -1 : 1;
         }

         for (int var67 = 0; var67 < var0; var67++) {
            float var70 = var15[var20 * var0 + var67];
            long var72 = 123352673095274L;
            if (123352673095274L == 0L) {
               boolean var119 = false;
            } else {
               int var120 = var72 < 0L ? -1 : 1;
            }

            long var74 = 123352673095274L;
            if (123352673095274L == 0L) {
               boolean var121 = false;
            } else {
               int var122 = var74 < 0L ? -1 : 1;
            }

            long var78 = 123352673095274L;
            if (123352673095274L == 0L) {
               boolean var123 = false;
            } else {
               int var124 = var78 < 0L ? -1 : 1;
            }

            if (var70 < 0.0F) {
               long var80 = 123352673095274L;
               if (123352673095274L == 0L) {
                  boolean var125 = false;
               } else {
                  int var126 = var80 < 0L ? -1 : 1;
               }
            } else {
               long var81 = 123352673095275L;
               if (123352673095275L == 0L) {
                  boolean var127 = false;
               } else {
                  int var128 = var81 < 0L ? -1 : 1;
               }

               float var38 = var70 / var10 + Math.max(0.0F, 0.7F - var63 * 0.5F);
               float var39;
               int var59 = (var39 = var38 - 0.9F) == 0.0F ? 0 : (var39 < 0.0F ? -1 : 1);
               long var90 = 123352673095275L;
               if (123352673095275L == 0L) {
                  boolean var129 = false;
               } else {
                  int var130 = var90 < 0L ? -1 : 1;
               }

               if (var59 < 0) {
                  var59 = var8[0];
               } else {
                  float var93;
                  var59 = (var93 = var38 - 1.9F) == 0.0F ? 0 : (var93 < 0.0F ? -1 : 1);
                  long var43 = 123352673095275L;
                  if (123352673095275L == 0L) {
                     boolean var131 = false;
                  } else {
                     int var132 = var43 < 0L ? -1 : 1;
                  }

                  var59 = var59 < 0 ? var8[1] : (var38 < 3.1F ? var8[2] : var8[3]);
               }

               var9.method_61941(var67, var20, 0xFF000000 | var59);
            }
         }

         long var68 = 123352673095274L;
         if (123352673095274L == 0L) {
            boolean var133 = false;
         } else {
            int var134 = var68 < 0L ? -1 : 1;
         }

         var20++;
      }
   }

   private static int[] W(float[] var0, float var1, float var2, long var3) {
      LowFireLoader.ensureSourceClassInitialized();
      int[] var5 = new int[4];

      for (int var6 = 0; var6 < 4; var6++) {
         int var7 = LowFireCache.channel(var0[var6 * 3]) << 16 | LowFireCache.channel(var0[var6 * 3 + 1]) << 8 | LowFireCache.channel(var0[var6 * 3 + 2]);
         if (var1 != 0.0F || var2 != 1.0F) {
            float[] var8 = ClientSupport.rgbToHsv(var7);
            float var9 = var8[0] + var1;
            var7 = ClientSupport.hsvToRgb(var9 - (float)Math.floor(var9), Math.min(1.0F, var8[1] * var2), var8[2]) & 16777215;
         }

         var5[var6] = var7;
      }

      return var5;
   }

   static void release() {
      long var0 = 16113136816005L;
      long var2 = var0 ^ 120715594488016L;

      for (class_1058 var7 : j) {
         if (var7 != null) {
            o(var2, var7);
         }
      }

      Arrays.fill(j, null);
      Arrays.fill(K, null);
      y = 0;
      n = 0;
   }

}
