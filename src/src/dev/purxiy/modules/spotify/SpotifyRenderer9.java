// Sourced By DevFFNightmare
package dev.purxiy.modules.spotify;

import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.loader.fabric.client.ClientRegistry21;
import dev.purxiy.loader.fabric.client.ClientRenderer8;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.ui.Icons;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import net.minecraft.class_310;
import net.minecraft.class_339;
import net.minecraft.class_350;
import net.minecraft.class_364;
import net.minecraft.class_403;
import net.minecraft.class_437;
import net.minecraft.class_442;
import net.minecraft.class_8030;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;
import org.joml.Vector4f;

public final class SpotifyRenderer9 {
   public static final int aw = 0;
   public static final int S = 1;
   public static final int aR = 2;
   private static final float n = 0.066F;
   private static final float ax = 0.072F;
   private static final float x = 0.7F;
   private static final float[] A = new float[]{0.84F, 0.65F};
   private static final float[] Z = new float[]{0.0F, -0.035F, 0.035F, -0.07F, 0.06F};
   private static final float[] w = new float[]{0.0F, -0.2F, 0.2F, -0.38F, 0.38F};
   private static final int t = 3;
   private static final float a0 = 1.9F;
   private static final float i = 0.5F;
   private static final float[] a = new float[]{0.43F, 0.72F};
   private static final float z = 0.3F;
   private static final float p = 0.97F;
   private static final float T = 0.14F;
   private static final float aP = 0.86F;
   private static final float H = 14.0F;
   private static final float G = 12.0F;
   private static final float ap = 260.0F;
   private static final float M = 72.0F;
   private static final float I = 0.9F;
   private static final float aT = 5.0F;
   private static final float r = 56.0F;
   private static final float D = 1.1F;
   private static final float B = 0.75F;
   private static final float aG = 0.9F;
   private static final float E = 0.03F;
   private static final float ac = 0.12F;
   private static final float f = 0.3F;
   private static final float Y = 0.8F;
   private static final float at = 0.55F;
   private static final float k = 0.35F;
   private static final float J = 0.12F;
   private static final float al = 0.2F;
   private static final long g = 3L;
   private static final float j = 64.0F;
   private static final SpotifyUpdater4<SpotifyRenderer9.TypeX> O = new SpotifyUpdater4<>();
   private static final SpotifyUpdater2<SpotifyRenderer9.TypeX> au = new SpotifyUpdater2<>();
   private static final SpotifyRenderer.TypeO a3 = new SpotifyRenderer.TypeO();
   private static final SpotifyRenderer9.TypeG a2 = new SpotifyRenderer9.TypeG();
   private static final SpotifyRenderer9.TypeZ o = new SpotifyRenderer9.TypeZ();
   private static SpotifyRenderer9.TypeF U = SpotifyRenderer9.TypeF.g;
   private static boolean s;
   private static long m = 0L;
   private static long ao = -4611686018427387904L;
   private static float C;
   private static float b;
   private static int P = 1;
   private static int aD = 1;
   private static final int[] v = new int[]{-1, -1};
   
   private static SpotifyUpdater4.TypeN ar;
   private static int c = 0;
   private static int aH = 0;
   private static float F = -1.0F;
   
   private static ClientRenderer8.Listener2 K;
   private static float d = 1.0F;
   private static float aq = 1.0F;
   private static float V = 1.0F;
   private static float L = 1.0F;
   private static final float[] ay = new float[4];
   private static float[] N = new float[128];
   private static int ae = 0;
   private static float[] h = new float[64];
   private static int aQ = 0;
   private static final Matrix4f y = new Matrix4f();
   private static final Matrix4f aB = new Matrix4f();
   private static final Matrix4f aa = new Matrix4f();
   private static final Vector3f aK = new Vector3f();
   private static final Vector3f aZ = new Vector3f();
   private static final Vector3f Q = new Vector3f();
   private static final Vector3f a9 = new Vector3f();
   private static final Vector3f X = new Vector3f();
   private static final Vector4f q = new Vector4f();
   private static float R = 1.0F;
   private static float l = 1.0F;
   private static final float[] a5 = new float[4];
   private static final int[] W = new int[1];
   private static boolean e;
   private static String u = "";
   private static double aS;
   private static int av = 0;
   private static int aI = 0;
   private static final float[][] aE = new float[][]{
      {0.0F, 0.45F}, {-1.02F, 0.3F}, {-0.5F, 0.3F}, {0.5F, 0.3F}, {1.02F, 0.3F}, {-0.7F, 0.8F}, {0.0F, 0.85F}, {0.7F, 0.8F}
   };





   private SpotifyRenderer9() {
   }

   public static void draw(class_437 var0, ClientRenderer8.Listener2 var1) {
      long var2 = 83944764776753L;
      long var4 = var2 ^ 32945476675118L;

      try {
         long var6 = System.nanoTime();
         long var8 = RenderPipelines.get().frame();
         if (var8 - ao > 3L) {
            K();
            m = 0L;
         }

         ao = var8;
         float var10 = m == 0L ? 0.0F : Math.min(0.1F, (float)(var6 - m) / 1.0E9F);
         m = var6;

         try {
            N(var4, var0, var1, var10);
            aS = aS + ((System.nanoTime() - var6) / 1000000.0 - aS) * 0.05;
         } catch (RuntimeException var16) {
            String var12 = var16.toString();
            if (!var12.equals(u)) {
               u = var12;
               ClientServices.LOGGER.error("Spotify Player: the lyrics behind the menus failed", var16);
            }

            K();
         }
      } finally {
         K = null;
      }
   }

   private static void N(long var0, class_437 var2, ClientRenderer8.Listener2 var3, float var4) {
      long var5 = 120121271099445L;
      long var7 = 127490110729949L;
      short var9 = 21022;
      long var10 = 2785156563L;
      long var12 = 43250150262962L;
      long var14 = 45042343599168L;
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifyModule var16 = SpotifyRegistry.y;
      boolean var17 = var16.enabled() && var16.inMenus();
      if (!var17 && O.X.isEmpty()) {
         boolean var18 = au.h.isEmpty();
         long var19 = 89809352492831L;
         if (89809352492831L == 0L) {
            boolean var10000 = false;
         } else {
            int var63 = var19 < 0L ? -1 : 1;
         }

         if (var18) {
            U = SpotifyRenderer9.TypeF.g;
            return;
         }
      }

      if (var17) {
         var16.frame(var4);
      }

      K = var3;
      C = var4;
      D(var3);
      SpotifyRenderer9.TypeF var53 = var17 ? H(var2, var3, var5) : SpotifyRenderer9.TypeF.g;
      SpotifyCurve var54 = SpotifyRegistry.get().pick(false);
      boolean var20 = var53 != SpotifyRenderer9.TypeF.g && var16.visible(var54);
      long var21 = 89809352492831L;
      if (89809352492831L == 0L) {
         boolean var64 = false;
      } else {
         int var65 = var21 < 0L ? -1 : 1;
      }

      long var24 = 89809352492830L;
      if (89809352492830L == 0L) {
         boolean var66 = false;
      } else {
         int var67 = var24 < 0L ? -1 : 1;
      }

      if (var53 != U) {
         U = var53;
         var20 = false;
      }

      boolean var26 = var16.F.is("Whole line");
      s = !var26;
      O.update(var54, var20, MotionStyle.MENU.dt(var4), var16.p.intValue(), var26, Z(var16, var9, var26, var10), a2);
      double var27 = var54.lyricsTime();
      long var29 = 89809352492831L;
      if (89809352492831L == 0L) {
         boolean var68 = false;
      } else {
         int var69 = var29 < 0L ? -1 : 1;
      }

      au.update(O, var27, var20 && s, o);
      a3.B = var26;
      long var32 = 89809352492830L;
      if (89809352492830L == 0L) {
         boolean var70 = false;
      } else {
         int var71 = var32 < 0L ? -1 : 1;
      }

      a3.J = var16.e.enabled();
      a3.g = var16.G.is("Album") ? var54.accent() : Icons.PURPLE;
      a3.o = var16.pausedAlpha(var54);
      a3.r = var54.level();
      a3.p = RenderPipelines.get().time();
      a3.Y = false;
      float var35 = O.clock();
      Iterator var36 = O.X.iterator();

      while (var36.hasNext()) {
         long var37 = 89809352492831L;
         if (89809352492831L == 0L) {
            boolean var72 = false;
         } else {
            int var73 = var37 < 0L ? -1 : 1;
         }

         SpotifyUpdater4.TypeM var39 = (SpotifyUpdater4.TypeM)var36.next();
         long var40 = 89809352492830L;
         if (89809352492830L == 0L) {
            boolean var74 = false;
         } else {
            int var75 = var40 < 0L ? -1 : 1;
         }

         if (var39.Z != null) {
            long var42 = 89809352492830L;
            if (89809352492830L == 0L) {
               boolean var76 = false;
            } else {
               int var77 = var42 < 0L ? -1 : 1;
            }

            if (!((SpotifyRenderer9.TypeX)var39.Z).x) {
               o((SpotifyRenderer9.TypeX)var39.Z, var39.t.c * 64.0F, var14, a3.o * SpotifyRenderer.presence(var39, var27, var35, a3));
            }
         }
      }

      long var56 = 89809352492830L;
      if (89809352492830L == 0L) {
         boolean var78 = false;
      } else {
         int var79 = var56 < 0L ? -1 : 1;
      }

      var36 = au.h.iterator();

      while (true) {
         long var57 = 89809352492831L;
         if (89809352492831L == 0L) {
            boolean var80 = false;
         } else {
            int var81 = var57 < 0L ? -1 : 1;
         }

         if (!var36.hasNext()) {
            long var58 = 89809352492831L;
            if (89809352492831L == 0L) {
               boolean var86 = false;
            } else {
               int var87 = var58 < 0L ? -1 : 1;
            }

            for (SpotifyUpdater4.TypeM var45 : O.X) {
               long var46 = 89809352492831L;
               if (89809352492831L == 0L) {
                  boolean var88 = false;
               } else {
                  int var89 = var46 < 0L ? -1 : 1;
               }

               if (var45.Z != null) {
                  long var48 = 89809352492830L;
                  if (89809352492830L == 0L) {
                     boolean var90 = false;
                  } else {
                     int var91 = var48 < 0L ? -1 : 1;
                  }

                  if (!((SpotifyRenderer9.TypeX)var45.Z).x) {
                     f(var12, var45, var27);
                  }
               }
            }

            long var60 = 89809352492830L;
            if (89809352492830L == 0L) {
               boolean var92 = false;
            } else {
               int var93 = var60 < 0L ? -1 : 1;
            }

            for (SpotifyUpdater2.TypeH var47 : au.h) {
               long var62 = 89809352492830L;
               if (89809352492830L == 0L) {
                  boolean var94 = false;
               } else {
                  int var95 = var62 < 0L ? -1 : 1;
               }

               if (var47.Y != null) {
                  q(var47, var7);
               }
            }

            return;
         }

         long var41 = 89809352492830L;
         if (89809352492830L == 0L) {
            boolean var82 = false;
         } else {
            int var83 = var41 < 0L ? -1 : 1;
         }

         SpotifyUpdater2.TypeH var43 = (SpotifyUpdater2.TypeH)var36.next();
         long var44 = 89809352492830L;
         if (89809352492830L == 0L) {
            boolean var84 = false;
         } else {
            int var85 = var44 < 0L ? -1 : 1;
         }

         if (var43.Y != null) {
            o((SpotifyRenderer9.TypeX)var43.Y, var43.width(), var14, a3.o * SpotifyRenderer.presence(var43, var35));
         }
      }
   }

   private static void K() {
      O.clear();
      au.clear();
      U = SpotifyRenderer9.TypeF.g;
      P = 1;
      aD = 1;
      v[0] = v[1] = -1;
      ar = null;
   }

   private static SpotifyRenderer9.TypeF H(class_437 var0, ClientRenderer8.Listener2 var1, long var2) {
      long var4 = 30590414000395L;
      SpotifyCurve.ensureSourceClassInitialized();
      boolean var6 = var0 instanceof class_442;
      long var7 = 120121271099445L;
      if (120121271099445L == 0L) {
         boolean var10000 = false;
      } else {
         int var15 = var7 < 0L ? -1 : 1;
      }

      if (!var6) {
         long var10 = 120121271099444L;
         if (120121271099444L == 0L) {
            boolean var16 = false;
         } else {
            int var17 = var10 < 0L ? -1 : 1;
         }

         if (ClientRegistry21.skins(var0)) {
            long var13 = 120121271099445L;
            if (120121271099445L == 0L) {
               boolean var18 = false;
            } else {
               int var19 = var13 < 0L ? -1 : 1;
            }

            if (!(var0 instanceof class_403)) {
               return T(var0, false, var4) ? SpotifyRenderer9.TypeF.b : SpotifyRenderer9.TypeF.g;
            }
         }

         return SpotifyRenderer9.TypeF.g;
      } else {
         return var1.settled() && T(var0, true, var4) ? SpotifyRenderer9.TypeF.j : SpotifyRenderer9.TypeF.g;
      }
   }

   private static float Z(SpotifyModule var0, int var1, boolean var2, long var3) {
      SpotifyCurve.ensureSourceClassInitialized();
      String var5 = var0.c.get();
      byte var6 = -1;
      switch (var5.hashCode()) {
         case -1968802693:
            if (var5.equals("Narrow")) {
               long var7 = 2785156562L;
               if (2785156562L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var17 = var7 < 0L ? -1 : 1;
               }

               var6 = 0;
            }
            break;
         case 2695923:
            if (var5.equals("Wide")) {
               var6 = 1;
            }
      }
      float var16 = switch (var6) {
         case 0 -> 9.5F;
         case 1 -> 16.0F;
         default -> 12.5F;
      };
      if (!var2) {
         return var16;
      } else {
         if (U != SpotifyRenderer9.TypeF.g) {
            float var8 = 0.7F * (U == SpotifyRenderer9.TypeF.j ? V * 0.066F * L() / L : aq * 0.072F * L());
            float var9 = Math.min(Math.min(ay[1] - ay[0], 260.0F), Math.min(ay[3] - ay[2], 260.0F));
            float var10 = Math.max(4.0F, (float)Math.floor(var9 * 0.9F / Math.max(1.0F, var8) * 2.0F) / 2.0F);
            float var11 = Math.min(var16, var10);
            short var12 = 21022;
            if (!(F < 0.0F)) {
               float var13 = var11 - F;
               long var14 = 2785156562L;
               if (2785156562L == 0L) {
                  boolean var19 = false;
               } else {
                  int var20 = var14 < 0L ? -1 : 1;
               }

               if (!(Math.abs(var13) >= 1.0F)) {
                  return F < 0.0F ? var16 : Math.min(F, var16);
               }
            }

            F = var11;
         }

         return F < 0.0F ? var16 : Math.min(F, var16);
      }
   }

   private static float L() {
      return SpotifyRegistry.y.X.floatValue();
   }

   private static boolean T(class_437 var0, boolean var1, long var2) {
      long var4 = 12178684316018L;
      long var6 = 1565219508L;
      short var8 = 3660;
      SpotifyCurve.ensureSourceClassInitialized();
      d = Math.max(1, var0.field_22789);
      aq = Math.max(1, var0.field_22790);
      V = Math.max(1, class_310.method_1551().method_1522().field_1481);
      L = V / aq;
      float var9 = aq * (var1 ? 0.3F : 0.14F);
      float var10 = aq * (var1 ? 0.97F : 0.86F);
      float var11 = Float.MAX_VALUE;
      float var12 = -Float.MAX_VALUE;
      ae = 0;

      for (class_364 var14 : var0.method_25396()) {
         long var15 = 30590414000394L;
         if (30590414000394L == 0L) {
            boolean var10000 = false;
         } else {
            int var53 = var15 < 0L ? -1 : 1;
         }

         long var18 = 30590414000394L;
         if (30590414000394L == 0L) {
            boolean var54 = false;
         } else {
            int var55 = var18 < 0L ? -1 : 1;
         }

         int var20 = (w(var4, var14) ? 1 : 0);
         long var21 = 30590414000395L;
         if (30590414000395L == 0L) {
            boolean var56 = false;
         } else {
            int var57 = var21 < 0L ? -1 : 1;
         }

         if ((var20 == 0)) {
            long var23 = 30590414000394L;
            if (30590414000394L == 0L) {
               boolean var58 = false;
            } else {
               int var59 = var23 < 0L ? -1 : 1;
            }
         } else {
            u(var6, a5[0], a5[1], a5[2], (char)var8, a5[3]);
            float var51 = (a5[0] + a5[2]) / 2.0F;
            float var24;
            var20 = (var24 = a5[3] - var9) == 0.0F ? 0 : (var24 < 0.0F ? -1 : 1);
            long var25 = 30590414000395L;
            if (30590414000395L == 0L) {
               boolean var60 = false;
            } else {
               int var61 = var25 < 0L ? -1 : 1;
            }

            if (var20 > 0) {
               float var27 = a5[1];
               long var28 = 30590414000394L;
               if (30590414000394L == 0L) {
                  boolean var62 = false;
               } else {
                  int var63 = var28 < 0L ? -1 : 1;
               }

               float var31;
               var20 = (var31 = var27 - var10) == 0.0F ? 0 : (var31 < 0.0F ? -1 : 1);
               long var32 = 30590414000395L;
               if (30590414000395L == 0L) {
                  boolean var64 = false;
               } else {
                  int var65 = var32 < 0L ? -1 : 1;
               }

               if (var20 < 0) {
                  float var34;
                  var20 = (var34 = var51 - d * 0.2F) == 0.0F ? 0 : (var34 < 0.0F ? -1 : 1);
                  long var35 = 30590414000395L;
                  if (30590414000395L == 0L) {
                     boolean var66 = false;
                  } else {
                     int var67 = var35 < 0L ? -1 : 1;
                  }

                  if (var20 > 0) {
                     long var37 = 30590414000394L;
                     if (30590414000394L == 0L) {
                        boolean var68 = false;
                     } else {
                        int var69 = var37 < 0L ? -1 : 1;
                     }

                     float var39;
                     var20 = (var39 = var51 - d * 0.8F) == 0.0F ? 0 : (var39 < 0.0F ? -1 : 1);
                     long var40 = 30590414000394L;
                     if (30590414000394L == 0L) {
                        boolean var70 = false;
                     } else {
                        int var71 = var40 < 0L ? -1 : 1;
                     }

                     if (var20 < 0) {
                        var11 = Math.min(var11, a5[0]);
                        var12 = Math.max(var12, a5[2]);
                     }
                  }
               }
            }
         }
      }

      long var42 = 30590414000394L;
      if (30590414000394L == 0L) {
         boolean var72 = false;
      } else {
         int var73 = var42 < 0L ? -1 : 1;
      }

      if (var1) {
         float var16 = d - 64.0F;
         long var17 = 30590414000394L;
         if (30590414000394L == 0L) {
            boolean var74 = false;
         } else {
            int var75 = var17 < 0L ? -1 : 1;
         }

         float var49 = aq - 34.0F;
         float var50 = aq;
         float var22 = d;
         long var52 = 30590414000394L;
         if (30590414000394L == 0L) {
            boolean var76 = false;
         } else {
            int var77 = var52 < 0L ? -1 : 1;
         }

         u(var6, var16, var49, var22, (char)var8, var50);
         if (var11 > var12) {
            var11 = d / 2.0F - 100.0F;
            var12 = d / 2.0F + 100.0F;
         }
      }

      if (var11 > var12) {
         return false;
      } else {
         ay[0] = Math.max(12.0F, var11 - 14.0F - 260.0F);
         ay[1] = var11 - 14.0F;
         ay[2] = var12 + 14.0F;
         ay[3] = Math.min(d - 12.0F, var12 + 14.0F + 260.0F);
         float var43 = ay[1];
         long var44 = 30590414000395L;
         if (30590414000395L == 0L) {
            boolean var78 = false;
         } else {
            int var79 = var44 < 0L ? -1 : 1;
         }

         return var43 - ay[0] >= 72.0F && ay[3] - ay[2] >= 72.0F;
      }
   }

   private static boolean w(long var0, class_364 var2) {
      SpotifyCurve.ensureSourceClassInitialized();
      long var3 = 12178684316018L;
      if (12178684316018L == 0L) {
         boolean var10000 = false;
      } else {
         int var16 = var3 < 0L ? -1 : 1;
      }

      if (var2 instanceof class_350 var6) {
         long var8 = 12178684316017L;
         if (12178684316017L == 0L) {
            boolean var17 = false;
         } else {
            int var18 = var8 < 0L ? -1 : 1;
         }

         long var11 = 12178684316018L;
         if (12178684316018L == 0L) {
            boolean var19 = false;
         } else {
            int var20 = var11 < 0L ? -1 : 1;
         }

         if (!var6.field_22764) {
            return false;
         }

         a5[0] = var6.method_25342() - 2;
         a5[1] = var6.method_46427();
         a5[2] = var6.method_31383() + 16;
         a5[3] = var6.method_46427() + var6.method_25364();
      } else if (var2 instanceof class_339 var7) {
         long var13 = 12178684316018L;
         if (12178684316018L == 0L) {
            boolean var21 = false;
         } else {
            int var22 = var13 < 0L ? -1 : 1;
         }

         long var15 = 12178684316018L;
         if (12178684316018L == 0L) {
            boolean var23 = false;
         } else {
            int var24 = var15 < 0L ? -1 : 1;
         }

         if (!var7.field_22764) {
            return false;
         }

         a5[0] = var7.method_46426();
         a5[1] = var7.method_46427();
         a5[2] = var7.method_46426() + var7.method_25368();
         a5[3] = var7.method_46427() + var7.method_25364();
      } else {
         class_8030 var14 = var2.method_48202();
         a5[0] = var14.method_49620();
         a5[1] = var14.method_49618();
         a5[2] = var14.method_49621();
         a5[3] = var14.method_49619();
      }

      return a5[2] > a5[0] && a5[3] > a5[1];
   }

   private static void u(long var0, float var2, float var3, float var4, char var5, float var6) {
      SpotifyCurve.ensureSourceClassInitialized();
      int var7 = ae * 4;
      int var8 = N.length;
      if (var7 >= var8) {
         N = Arrays.copyOf(N, N.length * 2);
      }

      int var9 = ae++ * 4;
      N[var9] = var2;
      N[var9 + 1] = var3;
      N[var9 + 2] = var4;
      N[var9 + 3] = var6;
   }

   private static boolean o(long var0, float[] var2, float var3) {
      long var4 = 64779469691157L;
      SpotifyCurve.ensureSourceClassInitialized();

      for (byte var6 = 0; var6 < ae * 4; var6 = (byte)(var6 + 4)) {
         float[] var9 = N;
         if (V(var2, var4, var9, var6, var3)) {
            return false;
         }
      }

      long var7 = 67347562850819L;
      if (67347562850819L == 0L) {
         boolean var10000 = false;
      } else {
         int var18 = var7 < 0L ? -1 : 1;
      }

      for (byte var10 = 0; var10 < aQ * 4; var10 = (byte)(var10 + 4)) {
         float[] var13 = h;
         boolean var14 = V(var2, var4, var13, var10, var3);
         long var15 = 67347562850819L;
         if (67347562850819L == 0L) {
            boolean var19 = false;
         } else {
            int var20 = var15 < 0L ? -1 : 1;
         }

         if (var14) {
            return false;
         }
      }

      return true;
   }

   private static boolean V(float[] var0, long var1, float[] var3, int var4, float var5) {
      SpotifyCurve.ensureSourceClassInitialized();
      float var6 = var0[0];
      long var7 = 64779469691157L;
      if (64779469691157L == 0L) {
         boolean var10000 = false;
      } else {
         int var19 = var7 < 0L ? -1 : 1;
      }

      if (var6 - var5 < var3[var4 + 2]) {
         long var10 = 64779469691156L;
         if (64779469691156L == 0L) {
            boolean var20 = false;
         } else {
            int var21 = var10 < 0L ? -1 : 1;
         }

         if (var0[2] + var5 > var3[var4]) {
            var6 = var0[1];
            long var12 = 64779469691156L;
            if (64779469691156L == 0L) {
               boolean var22 = false;
            } else {
               int var23 = var12 < 0L ? -1 : 1;
            }

            var6 -= var5;
            int var9 = var4 + 3;
            long var14 = 64779469691156L;
            if (64779469691156L == 0L) {
               boolean var24 = false;
            } else {
               int var25 = var14 < 0L ? -1 : 1;
            }

            if (var6 < var3[var9] && var0[3] + var5 > var3[var4 + 1]) {
               return true;
            }
         }
      }

      return false;
   }

   private static boolean V(long var0, float[] var2) {
      SpotifyCurve.ensureSourceClassInitialized();
      long var3 = 82103841161448L;
      if (82103841161448L == 0L) {
         boolean var10000 = false;
      } else {
         int var13 = var3 < 0L ? -1 : 1;
      }

      if (var2[1] >= 0.0F && var2[3] <= aq) {
         long var6 = 82103841161448L;
         if (82103841161448L == 0L) {
            boolean var14 = false;
         } else {
            int var15 = var6 < 0L ? -1 : 1;
         }

         float var9 = var2[0];
         long var10 = 82103841161447L;
         if (82103841161447L == 0L) {
            boolean var16 = false;
         } else {
            int var17 = var10 < 0L ? -1 : 1;
         }

         if (var9 >= ay[0] - 1.0F && var2[2] <= ay[1] + 1.0F || var2[0] >= ay[2] - 1.0F && var2[2] <= ay[3] + 1.0F) {
            return true;
         }
      }

      return false;
   }

   private static void M(long var0) {
      long var2 = 98345919579103L;
      long var4 = 20602329139792L;
      short var6 = 4796;
      char var7 = '\uda82';
      int var8 = (int)(var4 << 48 >>> 48);
      SpotifyCurve.ensureSourceClassInitialized();
      aQ = 0;
      Iterator var9 = O.X.iterator();

      while (var9.hasNext()) {
         long var10 = 12170799621970L;
         if (12170799621970L == 0L) {
            boolean var10000 = false;
         } else {
            int var49 = var10 < 0L ? -1 : 1;
         }

         SpotifyUpdater4.TypeM var13 = (SpotifyUpdater4.TypeM)var9.next();
         long var14 = 12170799621970L;
         if (12170799621970L == 0L) {
            boolean var50 = false;
         } else {
            int var51 = var14 < 0L ? -1 : 1;
         }

         if (var13.Z != null) {
            long var17 = 12170799621969L;
            if (12170799621969L == 0L) {
               boolean var52 = false;
            } else {
               int var53 = var17 < 0L ? -1 : 1;
            }

            long var20 = 12170799621970L;
            if (12170799621970L == 0L) {
               boolean var54 = false;
            } else {
               int var55 = var20 < 0L ? -1 : 1;
            }

            if (!((SpotifyRenderer9.TypeX)var13.Z).x) {
               boolean var23 = var13.leaving();
               long var24 = 12170799621970L;
               if (12170799621970L == 0L) {
                  boolean var56 = false;
               } else {
                  int var57 = var24 < 0L ? -1 : 1;
               }

               if (!var23 && ((SpotifyRenderer9.TypeX)var13.Z).t == (U == SpotifyRenderer9.TypeF.j)) {
                  long var27 = 12170799621969L;
                  if (12170799621969L == 0L) {
                     boolean var58 = false;
                  } else {
                     int var59 = var27 < 0L ? -1 : 1;
                  }

                  SpotifyRenderer9.TypeX var30 = (SpotifyRenderer9.TypeX)var13.Z;
                  long var31 = 12170799621969L;
                  if (12170799621969L == 0L) {
                     boolean var60 = false;
                  } else {
                     int var61 = var31 < 0L ? -1 : 1;
                  }

                  if (var30.t) {
                     var23 = Q((SpotifyRenderer9.TypeX)var13.Z, var13.t.c, 0.12F + var13.t.C, var2);
                     long var34 = 12170799621969L;
                     if (12170799621969L == 0L) {
                        boolean var62 = false;
                     } else {
                        int var63 = var34 < 0L ? -1 : 1;
                     }

                     if (!var23) {
                        continue;
                     }
                  } else {
                     var23 = M((SpotifyRenderer9.TypeX)var13.Z, var13.t.c, 0.12F + var13.t.C);
                     long var48 = 12170799621970L;
                     if (12170799621970L == 0L) {
                        boolean var64 = false;
                     } else {
                        int var65 = var48 < 0L ? -1 : 1;
                     }

                     if (!var23) {
                        continue;
                     }
                  }

                  g(var6, var7, var8);
               }
            }
         }
      }

      long var38 = 12170799621970L;
      if (12170799621970L == 0L) {
         boolean var66 = false;
      } else {
         int var67 = var38 < 0L ? -1 : 1;
      }

      var9 = au.h.iterator();

      while (true) {
         long var39 = 12170799621969L;
         if (12170799621969L == 0L) {
            boolean var68 = false;
         } else {
            int var69 = var39 < 0L ? -1 : 1;
         }

         if (!var9.hasNext()) {
            return;
         }

         SpotifyUpdater2.TypeH var16 = (SpotifyUpdater2.TypeH)var9.next();
         SpotifyRenderer9.TypeX var40 = (SpotifyRenderer9.TypeX)var16.Y;
         if (var40 != null && !var16.leaving() && var40.t == (U == SpotifyRenderer9.TypeF.j)) {
            float var18 = var16.width() / 64.0F;
            float var19 = SpotifyRenderer.standHeight(var16);
            long var41 = 12170799621969L;
            if (12170799621969L == 0L) {
               boolean var70 = false;
            } else {
               int var71 = var41 < 0L ? -1 : 1;
            }

            if (var40.t) {
               boolean var44 = Q(var40, var18, var19, var2);
               long var46 = 12170799621970L;
               if (12170799621970L == 0L) {
                  boolean var72 = false;
               } else {
                  int var73 = var46 < 0L ? -1 : 1;
               }

               if (!var44) {
                  continue;
               }
            } else {
               boolean var45 = M(var40, var18, var19);
               long var47 = 12170799621970L;
               if (12170799621970L == 0L) {
                  boolean var74 = false;
               } else {
                  int var75 = var47 < 0L ? -1 : 1;
               }

               if (!var45) {
                  continue;
               }
            }

            g(var6, var7, var8);
         }
      }
   }

   private static void g(int var0, int var1, int var2) {
      if (aQ * 4 >= h.length) {
         h = Arrays.copyOf(h, h.length * 2);
      }

      System.arraycopy(a5, 0, h, aQ++ * 4, 4);
   }

   private static boolean K(List var0, short var1, int var2, char var3, int var4, int var5) {
      SpotifyCurve.ensureSourceClassInitialized();

      for (SpotifyUpdater2.TypeH var7 : (Iterable<SpotifyUpdater2.TypeH>)(Object)(var0)) {
         SpotifyRenderer9.TypeX var8 = (SpotifyRenderer9.TypeX)var7.Y;
         if (var8 != null && var7.M == var4 && var8.i == var5 && var8.t == (U == SpotifyRenderer9.TypeF.j) && (!var7.leaving() || O.clock() - var7.e < 0.4F)) {
            return true;
         }
      }

      return false;
   }

   private static float f(int var0) {
      float var1 = aq * 0.072F * L();
      return aq * 0.5F + 0.45F * var1 + (var0 - 1.0F) * 1.9F * var1;
   }

   
   private static SpotifyRenderer9.TypeX m( SpotifyRenderer9.TypeX var0, float var1, int var2, int var3, long var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      long var6 = 54847652197798L;
      if (54847652197798L == 0L) {
         boolean var10000 = false;
      } else {
         int var14 = var6 < 0L ? -1 : 1;
      }

      aQ = 0;
      long var9 = 54847652197797L;
      if (54847652197797L == 0L) {
         boolean var15 = false;
      } else {
         int var16 = var9 < 0L ? -1 : 1;
      }

      if (var0 != null) {
         int var8 = av + 1;
         long var11 = 54847652197798L;
         if (54847652197798L == 0L) {
            boolean var17 = false;
         } else {
            int var18 = var11 < 0L ? -1 : 1;
         }

         av = var8;
      } else {
         aI++;
      }

      if (e) {
         ClientServices.LOGGER
            .info(
               var0 == null
                  ? String.format(Locale.ROOT, "Spotify dev: menu %s, side %d, band %d, %.1f em wide: no place", U, var2, var3, var1)
                  : (
                     var0.t
                        ? String.format(
                           Locale.ROOT,
                           "Spotify dev: menu ground, side %d, band %d, %.1f em wide: %.1f blocks away, em %.2f blocks / %.0f px",
                           var2,
                           var0.i,
                           var1,
                           Math.sqrt(N(var0.Y - aK.x) + N(var0.p - aK.y) + N(var0.l - aK.z)),
                           var0.E,
                           var0.b
                        )
                        : String.format(
                           Locale.ROOT,
                           "Spotify dev: beside the menu, side %d, band %d, %.1f em wide: foot %.0f,%.0f, em %.1f GUI",
                           var2,
                           var0.i,
                           var1,
                           var0.z,
                           var0.P,
                           var0.y
                        )
                  )
            );
      }

      return var0;
   }

   private static void h(int var0, long var1, float[] var3) {
      var3[0] = var0 < 0 ? ay[0] : ay[2];
      var3[1] = var0 < 0 ? ay[1] : ay[3];
   }

   
   private static SpotifyRenderer9.TypeX p(int var0, float var1, float var2, short var3, int var4, int var5, float var6, float var7, short var8) {
      long var9 = 82623642377194L;
      long var11 = var9 ^ 130179440964073L;
      long var13 = var9 ^ 125631320086425L;
      float[] var15 = new float[2];
      h(var4, var13, var15);
      float var16 = var15[1] - var15[0];
      SpotifyRenderer9.TypeX var17 = new SpotifyRenderer9.TypeX();
      var17.O = var4;
      var17.i = var5;
      var17.z = (var15[0] + var15[1]) / 2.0F;
      var17.P = var6;
      SpotifyCurve.ensureSourceClassInitialized();
      var17.y = Math.min(aq * 0.072F * L() * var7, var16 * 0.9F / Math.max(0.5F, var1));
      var17.D = 0.55F + 0.27F * K.dark();
      if (M(var17, var1, var2)) {
         float var18 = a5[1];
         short var19 = 19237;
         if (var18 >= aq * 0.14F) {
            var19 = 20698;
            if (a5[3] <= aq * 0.86F && o(var11, a5, 3.0F)) {
               return var17;
            }
         }
      }

      return null;
   }

   private static boolean M(SpotifyRenderer9.TypeX var0, float var1, float var2) {
      float var3 = var1 * var0.y / 2.0F;
      a5[0] = var0.z - var3;
      a5[1] = var0.P - var2 * var0.y;
      a5[2] = var0.z + var3;
      a5[3] = var0.P;
      return true;
   }

   
   private static SpotifyRenderer9.TypeX W(float var0, float var1, int var2, int var3, long var4, float var6) {
      long var7 = 67347562850819L;
      long var9 = 98345919579103L;
      long var11 = 61570107308113L;
      long var13 = 63116741069939L;
      SpotifyCurve.ensureSourceClassInitialized();
      ClientRenderer8.Listener2 var15 = K;
      if (var15 == null) {
         return null;
      } else {
         float[] var16 = new float[2];
         h(var2, var13, var16);
         float var17 = var16[1] - var16[0];
         float var18 = Math.min(V * 0.066F * L() * var6, var17 * L * 0.9F / Math.max(0.5F, var0));
         float var19 = var18 / L;
         float var20 = var0 * var19 / 2.0F;
         float[] var21 = Z;
         int var22 = var21.length;
         int var23 = 0;

         while (var23 < var22) {
            float var24 = var21[var23];

            for (float var28 : w) {
               float var29 = Math.clamp((var16[0] + var16[1]) / 2.0F + var28 * var17 * var2, var16[0] + var20, Math.max(var16[0] + var20, var16[1] - var20));
               long var30 = 105990089560243L;
               if (105990089560243L == 0L) {
                  boolean var10000 = false;
               } else {
                  int var68 = var30 < 0L ? -1 : 1;
               }

               float var33 = aq * (A[var3] + var24);
               long var34 = 105990089560242L;
               if (105990089560242L == 0L) {
                  boolean var69 = false;
               } else {
                  int var70 = var34 < 0L ? -1 : 1;
               }

               a5[0] = var29 - var20;
               a5[1] = var33 - var1 * var19;
               a5[2] = var29 + var20;
               long var36 = 105990089560242L;
               if (105990089560242L == 0L) {
                  boolean var71 = false;
               } else {
                  int var72 = var36 < 0L ? -1 : 1;
               }

               a5[3] = var33;
               if (!(a5[1] < aq * 0.3F)) {
                  long var38 = 105990089560243L;
                  if (105990089560243L == 0L) {
                     boolean var73 = false;
                  } else {
                     int var74 = var38 < 0L ? -1 : 1;
                  }

                  if (!(a5[3] > aq * 0.95F)) {
                     int var41 = (o(var7, a5, 3.0F) ? 1 : 0);
                     long var42 = 105990089560243L;
                     if (105990089560243L == 0L) {
                        boolean var75 = false;
                     } else {
                        int var76 = var42 < 0L ? -1 : 1;
                     }

                     if ((var41 == 0)) {
                        long var44 = 105990089560242L;
                        if (105990089560242L == 0L) {
                           boolean var77 = false;
                        } else {
                           int var78 = var44 < 0L ? -1 : 1;
                        }
                     } else {
                        float var67 = var29 / d * 2.0F;
                        long var45 = 105990089560242L;
                        if (105990089560242L == 0L) {
                           boolean var79 = false;
                        } else {
                           int var80 = var45 < 0L ? -1 : 1;
                        }

                        o(var67 - 1.0F, 1.0F - var33 / aq * 2.0F);
                        float var47 = var15.trace(X.x, X.y, X.z, 56.0F, W);
                        long var48 = 105990089560242L;
                        if (105990089560242L == 0L) {
                           boolean var81 = false;
                        } else {
                           int var82 = var48 < 0L ? -1 : 1;
                        }

                        if (!(var47 < 5.0F)) {
                           float var50;
                           var41 = (var50 = var47 - 56.0F) == 0.0F ? 0 : (var50 < 0.0F ? -1 : 1);
                           long var51 = 105990089560242L;
                           if (105990089560242L == 0L) {
                              boolean var83 = false;
                           } else {
                              int var84 = var51 < 0L ? -1 : 1;
                           }

                           if (var41 <= 0 && W[0] != 2) {
                              SpotifyRenderer9.TypeX var53 = new SpotifyRenderer9.TypeX();
                              var53.t = true;
                              var53.O = var2;
                              var53.i = var3;
                              var53.Y = aK.x + X.x * var47;
                              var53.p = aK.y + X.y * var47;
                              var53.l = aK.z + X.z * var47;
                              float var54 = X.dot(a9) * var47;
                              var53.b = var18;
                              var53.E = var18 * 2.0F * var54 * l / V;
                              float var55 = var0 * var53.E / 2.0F;
                              float var56 = Math.max(
                                 var15.ground(var53.Y - aZ.x * var55, var53.l - aZ.z * var55), var15.ground(var53.Y + aZ.x * var55, var53.l + aZ.z * var55)
                              );
                              long var57 = 105990089560243L;
                              if (105990089560243L == 0L) {
                                 boolean var85 = false;
                              } else {
                                 int var86 = var57 < 0L ? -1 : 1;
                              }

                              float var59;
                              var41 = (var59 = var56 - var53.p) == 0.0F ? 0 : (var59 < 0.0F ? -1 : 1);
                              long var60 = 105990089560243L;
                              if (105990089560243L == 0L) {
                                 boolean var87 = false;
                              } else {
                                 int var88 = var60 < 0L ? -1 : 1;
                              }

                              if (var41 > 0 && var56 - var53.p < 1.1F) {
                                 var53.p = var56;
                              }

                              var53.D = var15.dark();
                              long var62 = 105990089560242L;
                              if (105990089560242L == 0L) {
                                 boolean var89 = false;
                              } else {
                                 int var90 = var62 < 0L ? -1 : 1;
                              }

                              if (P(var53, var0, var1, true, var11) && Q(var53, var0, var1, var9) && o(var7, a5, 2.0F)) {
                                 return var53;
                              }
                           }
                        }
                     }
                  }
               }
            }

            var23++;
            long var64 = 105990089560242L;
            if (105990089560242L == 0L) {
               boolean var91 = false;
            } else {
               int var92 = var64 < 0L ? -1 : 1;
            }
         }

         return null;
      }
   }

   private static void o(float var0, float var1) {
      X.set(a9).fma(var0 * R, aZ).fma(var1 * l, Q).normalize();
   }

   private static boolean P(SpotifyRenderer9.TypeX var0, float var1, float var2, boolean var3, long var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      ClientRenderer8.Listener2 var6 = K;
      float var7 = var1 * var0.E / 2.0F;
      float var8 = var2 * var0.E;
      int var9 = 0;
      float[][] var10 = aE;

      for (float[] var14 : var10) {
         float var15 = var14[0] * var7;
         float var16 = var14[1] * var8;
         float var17 = var0.Y + aZ.x * var15 + Q.x * var16 - aK.x;
         float var18 = var0.p + aZ.y * var15 + Q.y * var16 - aK.y;
         float var19 = var0.l + aZ.z * var15 + Q.z * var16 - aK.z;
         float var20 = (float)Math.sqrt(var17 * var17 + var18 * var18 + var19 * var19);
         float var21;
         int var22 = (var21 = var20 - 0.001F) == 0.0F ? 0 : (var21 < 0.0F ? -1 : 1);
         long var23 = 61570107308112L;
         if (61570107308112L == 0L) {
            boolean var10000 = false;
         } else {
            int var33 = var23 < 0L ? -1 : 1;
         }

         if (var22 >= 0) {
            float var26 = var6.trace(var17 / var20, var18 / var20, var19 / var20, var20, W);
            long var27 = 61570107308112L;
            if (61570107308112L == 0L) {
               boolean var34 = false;
            } else {
               int var35 = var27 < 0L ? -1 : 1;
            }

            if (var26 < var20 - 0.35F) {
               var9++;
            }
         }
      }

      long var29 = 61570107308113L;
      if (61570107308113L == 0L) {
         boolean var36 = false;
      } else {
         int var37 = var29 < 0L ? -1 : 1;
      }

      long var30 = 61570107308113L;
      if (61570107308113L == 0L) {
         boolean var38 = false;
      } else {
         int var39 = var30 < 0L ? -1 : 1;
      }

      boolean var31;
      if (var3) {
         var31 = var9 == 0;
      } else {
         long var32 = 61570107308113L;
         if (61570107308113L == 0L) {
            boolean var40 = false;
         } else {
            int var41 = var32 < 0L ? -1 : 1;
         }

         var31 = var9 <= aE.length / 3;
      }

      return var31;
   }

   private static boolean Q(SpotifyRenderer9.TypeX var0, float var1, float var2, long var3) {
      float var5 = var1 * var0.E / 2.0F;
      float var6 = var2 * var0.E;
      float var7 = Float.MAX_VALUE;
      float var8 = Float.MAX_VALUE;
      SpotifyCurve.ensureSourceClassInitialized();
      float var9 = -Float.MAX_VALUE;
      float var10 = -Float.MAX_VALUE;

      for (int var11 = 0; var11 < 4; var11++) {
         long var12 = 98345919579102L;
         if (98345919579102L == 0L) {
            boolean var10000 = false;
         } else {
            int var24 = var12 < 0L ? -1 : 1;
         }

         float var15 = (var11 & 1) == 0 ? -var5 : var5;
         float var16 = (var11 & 2) == 0 ? 0.0F : var6;
         q.set(var0.Y - aK.x + aZ.x * var15 + Q.x * var16, var0.p - aK.y + aZ.y * var15 + Q.y * var16, var0.l - aK.z + aZ.z * var15 + Q.z * var16, 1.0F);
         y.transform(q);
         float var17;
         int var18 = (var17 = q.w - 1.0E-4F) == 0.0F ? 0 : (var17 < 0.0F ? -1 : 1);
         long var19 = 98345919579103L;
         if (98345919579103L == 0L) {
            boolean var25 = false;
         } else {
            int var26 = var19 < 0L ? -1 : 1;
         }

         if (var18 <= 0) {
            return false;
         }

         float var21 = (q.x / q.w + 1.0F) / 2.0F * d;
         float var22 = (1.0F - q.y / q.w) / 2.0F * aq;
         var7 = Math.min(var7, var21);
         var8 = Math.min(var8, var22);
         var9 = Math.max(var9, var21);
         var10 = Math.max(var10, var22);
      }

      a5[0] = var7;
      long var23 = 98345919579102L;
      if (98345919579102L == 0L) {
         boolean var27 = false;
      } else {
         int var28 = var23 < 0L ? -1 : 1;
      }

      a5[1] = var8;
      a5[2] = var9;
      a5[3] = var10;
      return true;
   }

   private static boolean L(SpotifyRenderer9.TypeX var0, float var1, float var2, long var3, float var5) {
      long var6 = 67347562850819L;
      long var8 = 98345919579103L;
      long var10 = 61570107308113L;
      long var12 = 82103841161448L;
      SpotifyCurve.ensureSourceClassInitialized();
      if (U != SpotifyRenderer9.TypeF.g && var0.t == (U == SpotifyRenderer9.TypeF.j)) {
         boolean var14;
         if (var0.t) {
            long var15 = 75813435747786L;
            if (75813435747786L == 0L) {
               boolean var10000 = false;
            } else {
               int var26 = var15 < 0L ? -1 : 1;
            }

            float var18 = var0.V - var5;
            long var19 = 75813435747785L;
            if (75813435747785L == 0L) {
               boolean var27 = false;
            } else {
               int var28 = var19 < 0L ? -1 : 1;
            }

            var0.V = var18;
            if (var0.V <= 0.0F) {
               var0.V = 0.12F;
               var0.S = !P(var0, var1, var2, false, var10);
            }

            boolean var21;
            label91: {
               if (!var0.S && Q(var0, var1, var2, var8) && a5[0] >= 0.0F) {
                  long var22 = 75813435747786L;
                  if (75813435747786L == 0L) {
                     boolean var29 = false;
                  } else {
                     int var30 = var22 < 0L ? -1 : 1;
                  }

                  if (a5[2] <= d) {
                     long var24 = 75813435747785L;
                     if (75813435747785L == 0L) {
                        boolean var31 = false;
                     } else {
                        int var32 = var24 < 0L ? -1 : 1;
                     }

                     if (a5[3] <= aq && o(var6, a5, 0.0F)) {
                        var21 = true;
                        break label91;
                     }
                  }
               }

               var21 = false;
            }

            var14 = var21;
         } else {
            var14 = M(var0, var1, var2) && V(var12, a5) && o(var6, a5, 0.0F);
         }

         var0.m = var14 ? Math.max(0.0F, var0.m - var5) : var0.m + var5;
         return var0.m < 0.35F;
      } else {
         return false;
      }
   }

   private static void D(ClientRenderer8.Listener2 var0) {
      aK.set(var0.eye());
      aZ.set(var0.right());
      Q.set(var0.up());
      a9.set(var0.forward());
      y.set(var0.viewProj()).translate(aK.x, aK.y, aK.z);
      Matrix4fc var1 = var0.projection();
      R = 1.0F / Math.max(1.0E-4F, Math.abs(var1.m00()));
      l = 1.0F / Math.max(1.0E-4F, Math.abs(var1.m11()));
   }

   private static void f(long var0, SpotifyUpdater4.TypeM var2, double var3) {
      long var5 = 37555226826245L;
      short var7 = 8744;
      short var8 = 500;
      int var9 = (int)(var5 << 48 >>> 48);
      SpotifyRenderer9.TypeX var10 = (SpotifyRenderer9.TypeX)var2.Z;
      SpotifyUpdater4.TypeN var11 = var2.t;
      SpotifyCurve.ensureSourceClassInitialized();
      a3.L = var10.D;
      float var12 = 0.12F + var11.C / 2.0F;
      long var13 = 43250150262961L;
      if (43250150262961L == 0L) {
         boolean var10000 = false;
      } else {
         int var21 = var13 < 0L ? -1 : 1;
      }

      if (var10.t) {
         a3.k = 1.0F;
         Canvas var16 = e(var7, var10, (char)var8, var11.c * 64.0F, (char)var9, var11.C * 64.0F, var12 * var10.E);
         long var17 = 43250150262961L;
         if (43250150262961L == 0L) {
            boolean var22 = false;
         } else {
            int var23 = var17 < 0L ? -1 : 1;
         }

         if (var16 != null) {
            SpotifyRenderer.draw(var16, var2, var3, O.clock(), a3);
            RenderPipelines.get().flushSheet();
         }
      } else {
         a3.k = 0.8F;
         Canvas var20 = RenderPipelines.get().menuCanvas();
         var20.push();
         var20.translate(var10.z, var10.P - var12 * var10.y);
         var20.scale(var10.y / 64.0F);
         SpotifyRenderer.draw(var20, var2, var3, O.clock(), a3);
         var20.pop();
      }
   }

   private static void q(SpotifyUpdater2.TypeH var0, long var1) {
      long var3 = 37555226826245L;
      short var5 = 8744;
      short var6 = 500;
      int var7 = (int)(var3 << 48 >>> 48);
      SpotifyCurve.ensureSourceClassInitialized();
      SpotifyRenderer9.TypeX var8 = (SpotifyRenderer9.TypeX)var0.Y;
      a3.L = var8.D;
      long var9 = 127490110729949L;
      if (127490110729949L == 0L) {
         boolean var10000 = false;
      } else {
         int var17 = var9 < 0L ? -1 : 1;
      }

      if (var8.t) {
         a3.k = 1.0F;
         Canvas var12 = e(var5, var8, (char)var6, var0.width(), (char)var7, SpotifyRenderer.standHeight(var0) * 64.0F, 0.0F);
         long var13 = 127490110729949L;
         if (127490110729949L == 0L) {
            boolean var18 = false;
         } else {
            int var19 = var13 < 0L ? -1 : 1;
         }

         if (var12 != null) {
            SpotifyRenderer.stand(var12, var0, O.clock(), a3);
            RenderPipelines.get().flushSheet();
         }
      } else {
         a3.k = 0.8F;
         Canvas var16 = RenderPipelines.get().menuCanvas();
         var16.push();
         var16.translate(var8.z, var8.P);
         var16.scale(var8.y / 64.0F);
         SpotifyRenderer.stand(var16, var0, O.clock(), a3);
         var16.pop();
      }
   }

   
   private static Canvas e(int var0, SpotifyRenderer9.TypeX var1, char var2, float var3, char var4, float var5, float var6) {
      ClientRenderer8.Listener2 var7 = K;
      if (var7 == null) {
         return null;
      } else {
         float var8 = var1.E / 64.0F;
         aB.set(
            aZ.x * var8,
            aZ.y * var8,
            aZ.z * var8,
            0.0F,
            -Q.x * var8,
            -Q.y * var8,
            -Q.z * var8,
            0.0F,
            -a9.x,
            -a9.y,
            -a9.z,
            0.0F,
            var1.Y - aK.x + Q.x * var6,
            var1.p - aK.y + Q.y * var6,
            var1.l - aK.z + Q.z * var6,
            1.0F
         );
         aa.set(y).mul(aB);
         RenderPipelines.TypeW var9 = new RenderPipelines.TypeW(new Matrix4f(aB), var1.p + 0.9F - aK.y);
         return RenderPipelines.get().beginSheet(aa, var3, var5, var1.b / 64.0F, var7.projection(), 0.75F, null, var9, var7.depth());
      }
   }

   private static void o(SpotifyRenderer9.TypeX var0, float var1, long var2, float var4) {
      SpotifyCurve.ensureSourceClassInitialized();
      if (!(var4 <= 0.002F)) {
         a3.L = var0.D;
         if (var0.t) {
            ClientRenderer8.Listener2 var5 = K;
            if (var5 == null) {
               return;
            }

            a3.k = 1.0F;
            float var6 = (float)Math.sqrt(a9.x * a9.x + a9.z * a9.z);
            long var7 = 45042343599167L;
            if (45042343599167L == 0L) {
               boolean var10000 = false;
            } else {
               int var18 = var7 < 0L ? -1 : 1;
            }

            float var10 = var6 > 1.0E-4F ? a9.x / var6 : 0.0F;
            long var11 = 45042343599167L;
            if (45042343599167L == 0L) {
               boolean var19 = false;
            } else {
               int var20 = var11 < 0L ? -1 : 1;
            }

            float var13 = var6 > 1.0E-4F ? a9.z / var6 : 1.0F;
            float var14 = var0.E / 64.0F;
            aB.set(
               -var13 * var14,
               0.0F,
               var10 * var14,
               0.0F,
               -var10 * var14,
               0.0F,
               -var13 * var14,
               0.0F,
               0.0F,
               1.0F,
               0.0F,
               0.0F,
               var0.Y - aK.x,
               var0.p + 0.03F - aK.y,
               var0.l - aK.z,
               1.0F
            );
            aa.set(y).mul(aB);
            Canvas var15 = RenderPipelines.get().beginSheet(aa, var1, 64.0F, var0.b / 64.0F, var5.projection(), 0.0F, null, null, var5.depth());
            SpotifyRenderer.shadow(var15, var1, var4, a3);
            RenderPipelines.get().flushSheet();
         } else {
            a3.k = 0.8F;
            Canvas var16 = RenderPipelines.get().menuCanvas();
            float var17 = var0.y / 64.0F;
            var16.push();
            var16.translate(var0.z, var0.P);
            var16.scale(var17, var17 * 0.3F);
            SpotifyRenderer.shadow(var16, var1, var4, a3);
            var16.pop();
         }
      }
   }

   private static float N(float var0) {
      return var0 * var0;
   }

   public static void dev(String[] var0) {
      String var1 = var0.length > 1 ? var0[1] : "log";
      switch (var1) {
         case "places":
            e = var0.length < 3 || Boolean.parseBoolean(var0[2]);
            break;
         case "log":
            ClientServices.LOGGER
               .info(
                  String.format(
                     Locale.ROOT,
                     "Spotify dev: menu lyrics %s, room %.0f..%.0f and %.0f..%.0f of %.0f GUI, %d lines, %d words, %d placed, %d without a place, %.3f ms",
                     U,
                     ay[0],
                     ay[1],
                     ay[2],
                     ay[3],
                     d,
                     O.X.size(),
                     au.h.size(),
                     av,
                     aI,
                     aS
                  )
               );
            break;
         default:
            ClientServices.LOGGER.warn("Dev step spotify=menu: unknown action {}", var1);
      }
   }

   static void closeOwned() {
      K();
      K = null;
   }

private static enum TypeF {
      g,
      j,
      b;

}

   private static final class TypeG implements SpotifyUpdater4.Listener<SpotifyRenderer9.TypeX> {
      
      public SpotifyRenderer9.TypeX place(SpotifyUpdater4.TypeM<SpotifyRenderer9.TypeX> var1, List<SpotifyUpdater4.TypeM<SpotifyRenderer9.TypeX>> var2) {
         long var3 = 122358570555409L;
         long var5 = var3 ^ 16687801911458L;
         long var7 = var3 ^ 110335278333763L;
         long var9 = var3 ^ 104088095255991L;
         long var11 = var3 ^ 40052784137211L;
         int var13 = (int)((var3 ^ 40052784137211L) >>> 32);
         int var14 = (int)((var3 ^ 40052784137211L) << 32 >>> 48);
         int var15 = (int)(var11 << 48 >>> 48);
         var1.C = SpotifyRenderer9.s && var1.t.k == SpotifyUpdater4.TypeA.G;
         if (var1.C) {
            SpotifyRenderer9.TypeX var23 = new SpotifyRenderer9.TypeX();
            var23.x = true;
            return var23;
         } else if (SpotifyRenderer9.U == SpotifyRenderer9.TypeF.g) {
            return null;
         } else {
            int var16 = -1;
            int var17 = 0;
            if (var1.t.k == SpotifyUpdater4.TypeA.G) {
               SpotifyRenderer9.TypeX var18 = (SpotifyRenderer9.TypeX)var1.Z;
               if (var18 != null && var18.O != 0) {
                  var16 = var18.O;
                  var17 = var18.i;
               } else {
                  SpotifyRenderer9.P = -SpotifyRenderer9.P;
                  if (SpotifyRenderer9.P < 0) {
                     SpotifyRenderer9.aD ^= 1;
                  }

                  var16 = SpotifyRenderer9.P;
                  var17 = SpotifyRenderer9.aD;
               }
            }

            SpotifyRenderer9.M(var7);
            float var24 = var1.t.c;
            float var19 = 0.12F + var1.t.C;
            SpotifyRenderer9.TypeX var20 = null;

            for (int var21 = 0; var21 < 2 && var20 == null; var21++) {
               int var22 = (var17 + var21) % 2;
               var20 = SpotifyRenderer9.U == SpotifyRenderer9.TypeF.j
                  ? SpotifyRenderer9.W(var24, var19, var16, var22, var5, 0.7F)
                  : SpotifyRenderer9.p(var13, var24, var19, (short)var14, var16, var22, SpotifyRenderer9.aq * SpotifyRenderer9.a[var22], 0.7F, (short)var15);
            }

            return SpotifyRenderer9.m(var20, var24, var16, var17, var9);
         }
      }

      @Override
      public boolean keeps(SpotifyUpdater4.TypeM<SpotifyRenderer9.TypeX> var1, float var2) {
         long var3 = 13674646844862L;
         long var5 = var3 ^ 79836204734580L;
         SpotifyRenderer9.TypeX var7 = (SpotifyRenderer9.TypeX)var1.Z;
         if (var7 != null && var7.x) {
            return SpotifyRenderer9.s;
         } else if (var7 == null) {
            if (SpotifyRenderer9.U != SpotifyRenderer9.TypeF.g && !(SpotifyRenderer9.O.clock() < SpotifyRenderer9.b)) {
               SpotifyRenderer9.b = SpotifyRenderer9.O.clock() + 0.2F;
               return false;
            } else {
               return true;
            }
         } else {
            return SpotifyRenderer9.L(var7, var1.t.c, 0.12F + var1.t.C, var5, var2);
         }
      }
   }

   static final class TypeX {
      boolean t;
      boolean x;
      int O;
      int i;
      float Y;
      float p;
      float l;
      float E;
      float b;
      float z;
      float P;
      float y;
      float D;
      float m;
      boolean S;
      float V;
   }

   private static final class TypeZ implements SpotifyUpdater2.Listener<SpotifyRenderer9.TypeX> {
      
      public SpotifyRenderer9.TypeX place(SpotifyUpdater2.TypeH<SpotifyRenderer9.TypeX> var1, List<SpotifyUpdater2.TypeH<SpotifyRenderer9.TypeX>> var2) {
         long var3 = 42013185213267L;
         long var5 = var3 ^ 77310641853408L;
         long var7 = var3 ^ 49633863345153L;
         long var9 = var3 ^ 26215442839285L;
         long var11 = var3 ^ 119918240569529L;
         int var13 = (int)((var3 ^ 119918240569529L) >>> 32);
         int var14 = (int)((var3 ^ 119918240569529L) << 32 >>> 48);
         int var15 = (int)(var11 << 48 >>> 48);
         var11 = var3 ^ 95674295645641L;
         int var16 = (int)((var3 ^ 95674295645641L) >>> 48);
         int var17 = (int)((var3 ^ 95674295645641L) << 16 >>> 32);
         int var18 = (int)(var11 << 48 >>> 48);
         if (SpotifyRenderer9.U == SpotifyRenderer9.TypeF.g) {
            return null;
         } else {
            SpotifyRenderer9.M(var7);
            int var19 = SpotifyRenderer9.U == SpotifyRenderer9.TypeF.j ? SpotifyRenderer9.A.length : 3;
            int var20 = var1.M < 0 ? 0 : 1;
            boolean var21 = var1.M > 0 && SpotifyRenderer9.ar == var1.W && SpotifyRenderer9.c == var1.g - 1;
            int var22 = var21 ? SpotifyRenderer9.aH : (SpotifyRenderer9.v[var20] + 1) % var19;
            float var23 = var1.width() / 64.0F;
            float var24 = SpotifyRenderer.standHeight(var1);
            SpotifyRenderer9.TypeX var25 = null;

            for (int var26 = 0; var26 < 2 && var25 == null; var26++) {
               for (int var27 = 0; var27 < var19 && var25 == null; var27++) {
                  int var28 = (var22 + var27) % var19;
                  if (var26 != 0 || !SpotifyRenderer9.K(var2, (short)var16, var17, (char)var18, var1.M, var28)) {
                     var25 = SpotifyRenderer9.U == SpotifyRenderer9.TypeF.j
                        ? SpotifyRenderer9.W(var23, var24, var1.M, var28, var5, 1.0F)
                        : SpotifyRenderer9.p(var13, var23, var24, (short)var14, var1.M, var28, SpotifyRenderer9.f(var28), 1.0F, (short)var15);
                  }
               }
            }

            if (var25 != null) {
               SpotifyRenderer9.v[var20] = var25.i;
               if (var1.M < 0) {
                  SpotifyRenderer9.ar = var1.W;
                  SpotifyRenderer9.c = var1.g;
                  SpotifyRenderer9.aH = var25.i;
               }
            }

            return SpotifyRenderer9.m(var25, var23, var1.M, var22, var9);
         }
      }

      @Override
      public boolean keeps(SpotifyUpdater2.TypeH<SpotifyRenderer9.TypeX> var1) {
         long var2 = 12658757767904L;
         long var4 = var2 ^ 87346350047018L;
         SpotifyRenderer9.TypeX var6 = (SpotifyRenderer9.TypeX)var1.Y;
         return var6 != null && SpotifyRenderer9.L(var6, var1.width() / 64.0F, SpotifyRenderer.standHeight(var1), var4, SpotifyRenderer9.C);
      }
   }
}
