// Sourced By DevFFNightmare
package dev.purxiy.modules.home;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_425;
import net.minecraft.class_437;
import net.minecraft.class_442;

public final class HomeRenderer {
   private static final float U = 0.05F;
   private static final float s = 1.7F;
   private static final float H = 0.85F;
   private static final float Y = 1.45F;
   private static final float G = 1.35F;
   private static final float A = 0.5F;
   private static final float d = 1.6F;
   private static final float R = 1.3F;
   private static final float b = 3.4F;
   private static final float t = 3.0F;
   private static final int W = 130;
   private static final int Z = 10;
   private static boolean w;
   
   private static Boolean k;
   
   private static class_425 Q;
   private static HomeRenderer.TypeB C = HomeRenderer.TypeB.A;
   private static boolean g;
   private static float z;
   private static float c;
   private static float o;
   private static float J;
   private static int y = 0;
   private static int f = 0;
   private static boolean X;
   private static float r;
   private static float N;
   private static float i;
   private static final Animator O = new Animator(0.0F, 90.0F, 19.0F);
   private static final float[] m = new float[130];
   private static final float[] V = new float[130];
   private static final float[] E = new float[130];
   private static final float[] n = new float[130];
   private static final float x = 0.0F;
   private static final float[] j = new float[]{0.3F, 0.6F, 1.0F, 1.6F, 2.6F, 3.0F, 3.4F, 3.8F, 4.2F};
   private static final float[] B = new float[]{0.0F, 0.25F, 0.5F, 0.75F, 1.0F, 1.2F, 1.5F};
   private static int u = 0;
   private static int e = 0;

   private static final String[] h = new String[4];



   private HomeRenderer() {
   }

   public static boolean takesOver() {
      if (k == null) {
         FabricLoader var0 = FabricLoader.getInstance();
         k = var0.isModLoaded("drippyloadingscreen") || var0.isModLoaded("fancymenu");
      }

      return !w && !k && HomeUpdater.z.J.enabled();
   }

   public static boolean render(class_425 var0, class_332 var1, int var2, int var3, float var4, boolean var5, long var6, float var8) {
      long var9 = 84277783335656L;
      long var11 = var9 ^ 54623737020261L;
      int var13 = (int)((var9 ^ 54623737020261L) >>> 48);
      int var14 = (int)((var9 ^ 54623737020261L) << 16 >>> 32);
      int var15 = (int)(var11 << 48 >>> 48);
      long var16 = var9 ^ 34918994551877L;
      long var18 = (var9 ^ 64679698609611L) >>> 16;
      int var20 = (int)((var9 ^ 64679698609611L) << 48 >>> 48);
      long var21 = var9 ^ 75824619688305L;
      class_310 var23 = class_310.method_1551();
      if (var0 != Q) {
         Q = var0;
         C = HomeRenderer.TypeB.A;
         g = var5;
         J = 0.0F;
         o = 0.0F;
         c = 0.0F;
         z = 0.0F;
         f = 0;
         y = 0;
         O.snap(0.0F);
         e = 0;
         u = 0;
      }

      float var24 = RenderPipelines.get().delta();
      if (C == HomeRenderer.TypeB.F) {
         var24 = Math.min(var24, 0.05F);
      }

      z += var24;
      class_437 var25 = var23.field_1755;
      boolean var26 = MotionStyle.reduced();
      if (C == HomeRenderer.TypeB.A && var6 > -1L) {
         C = HomeRenderer.TypeB.w;
         c = 0.0F;
         f = 0;
         y = 0;
      }

      if (C != HomeRenderer.TypeB.F) {
         X = var25 instanceof class_442 && HomeSupport2.skins(var25) && !var26;
      }

      if (C == HomeRenderer.TypeB.w) {
         c += var24;
         y = P(var23, var25, var16) ? y + 1 : 0;
         f++;
         if (f(var21) && (y >= 3 || c >= 3.0F)) {
            Canvas var27 = RenderPipelines.get().canvas();
            float[] var28 = s(var27.height());
            r = var27.width() / 2.0F;
            N = var28[0] + o((short)var13, var14, var15, var28[1]);
            i = var28[1];
            C = HomeRenderer.TypeB.F;
            o = 0.0F;
            HomeUpdater.get().beginIntro();
         }
      } else if (C == HomeRenderer.TypeB.F) {
         o += var24;
      }

      boolean var30 = C != HomeRenderer.TypeB.A || g && z < 0.95000005F;
      if (var25 != null && var30) {
         var25.method_47413(var1, var2, var3, var4);
      } else {
         var23.field_1705.method_72738();
      }

      try {
         M(RenderPipelines.get().canvas(), var18, var8, var26, var24, (short)var20);
      } catch (Throwable var29) {
         HomeUpdater.metric("draw_failures");
         w = true;
         ClientServices.LOGGER.error("Purxiy loading screen failed; Minecraft's takes over", var29);
      }

      if (C == HomeRenderer.TypeB.F && o >= 1.6F) {
         Q = null;
         return true;
      } else {
         return false;
      }
   }

   private static boolean P(class_310 var0,  class_437 var1, long var2) {
      HomeSupport2.ensureSourceClassInitialized();
      if (var1 == null) {
         return false;
      } else if (HomeUpdater.z.worldEnabled() && var0.field_1687 == null) {
         HomeUpdater var4 = HomeUpdater.get();
         if (!var4.readyForIntro()) {
            long var5 = 91690120689324L;
            if (91690120689324L == 0L) {
               boolean var10000 = false;
            } else {
               int var8 = var5 < 0L ? -1 : 1;
            }

            if (f <= 4 || var4.drawnLately()) {
               return false;
            }
         }

         return true;
      } else {
         return true;
      }
   }

   private static boolean f(long var0) {
      HomeSupport2.ensureSourceClassInitialized();
      return z >= (g ? 1.35F : 1.7F) + x;
   }

   private static float[] s(float var0) {
      float var1 = var0 * 0.24F;
      return new float[]{var0 * 0.46F - HomeRenderer2.height(var1) / 2.0F, var1};
   }

   private static float o(short var0, int var1, int var2, float var3) {
      return MotionStyle.reduced() ? 0.0F : (float)Math.sin(z * 1.45F) * var3 * 0.013F;
   }

   private static void M(Canvas var0, long var1, float var3, boolean var4, float var5, short var6) {
      long var25 = 130245304985379L;
      long var27 = var25 ^ 12633613093550L;
      int var7 = (int)((var25 ^ 12633613093550L) >>> 48);
      int var8 = (int)((var25 ^ 12633613093550L) << 16 >>> 32);
      int var9 = (int)(var27 << 48 >>> 48);
      var27 = var25 ^ 56490288312713L;
      int var10 = (int)((var25 ^ 56490288312713L) >>> 56);
      int var11 = (int)((var25 ^ 56490288312713L) << 8 >>> 32);
      int var12 = (int)(var27 << 40 >>> 40);
      int var13 = (int)((var25 ^ 137240998393370L) >>> 48);
      long var14 = (var25 ^ 137240998393370L) << 16 >>> 16;
      float var16 = var0.width();
      float var17 = var0.height();
      HomeSupport2.ensureSourceClassInitialized();
      float var18 = RenderPipelines.get().time();
      RenderPipelines var19 = RenderPipelines.get();
      boolean var20 = C == HomeRenderer.TypeB.F;
      float var21 = var17 * 1.32F;
      float var22 = 0.0F;
      float var23 = 1.0F;
      float var24 = 1.0F;
      if (!var20 && g) {
         float var61 = Math.min(1.0F, z / 0.85F);
         float var63 = S(var61);
         var24 = var63;
         if (var4) {
            var23 = S(var61);
            long var31 = 1987385634L;
            if (1987385634L == 0L) {
               boolean var10000 = false;
            } else {
               int var93 = var31 < 0L ? -1 : 1;
            }
         } else {
            var22 = -var21 * (1.0F - Easing.CUBIC_IN_OUT.apply(var61));
         }
      } else if (var20) {
         float var29 = Math.min(1.0F, o / 1.45F);
         float var30 = S(var29 / 0.3F);
         var24 = 1.0F - var30;
         if (var4) {
            var23 = 1.0F - S(var29);
         } else {
            var22 = -var21 * Easing.bezier(0.62F, 0.0F, 0.32F, 1.0F).apply(var29);
         }
      }

      if (var23 > 0.003F) {
         float var55;
         int var26 = (var55 = var22 - -var21) == 0.0F ? 0 : (var55 < 0.0F ? -1 : 1);
         var27 = 1987385634L;
         if (1987385634L == 0L) {
            boolean var94 = false;
         } else {
            int var95 = var27 < 0L ? -1 : 1;
         }

         if (var26 > 0) {
            D((byte)var10, var0, var16, var17, var11, var12, var22, var21, var23, var24, var18, var4);
         }
      }

      float[] var56 = s(var17);
      float var57 = var56[0];
      float var60 = var56[1];
      float var28 = o((short)var7, var8, var9, var60);
      float var62 = var16 / 2.0F;
      float var64 = var57 + var28;
      float var66 = var60;
      float var32 = 1.0F;
      float var33 = 1.0F;
      float var34 = var4 ? 0.2F : 0.22F + 0.2F * (float)Math.sin(z * 1.6F);
      float var35 = 0.0F;
      float var36 = 0.0F;
      float var37 = 0.0F;
      float var38 = T((short)var13, var14, z);
      if (!var20 && !g) {
         float var69 = S(z / 0.8F) * (1.0F - S((z - 0.9F) / 0.8F));
         float var72 = Easing.EMPHASIZED.apply(Math.max(0.0F, Math.min(1.0F, (z - 0.12F) / 0.9F)));
         var32 = Math.min(1.0F, var72 * 1.25F);
         var66 = var60 * (0.9F + 0.1F * var72);
         var64 = var57 + var28 + (1.0F - var72) * var60 * 0.16F + (var60 - var66) * 0.5F;
         var33 = S((z - 0.55F) / 0.7F);
         var37 = S((z - 0.95F) / 0.6F);
         var36 = R(z, 1.15F, 0.22F);
         float var77;
         int var80 = (var77 = var69 - 0.003F) == 0.0F ? 0 : (var77 < 0.0F ? -1 : 1);
         short var43 = 10019;
         if (var80 > 0) {
            var0.glowCircle(var62, var57 + var60 * 0.55F, var60 * 0.3F * var69, var60 * (0.6F + 0.5F * var69), BlendMode.withAlpha(Icons.VIOLET, 0.22F * var69));
         }

         long var84 = 1987385635L;
         if (1987385635L == 0L) {
            boolean var104 = false;
         } else {
            int var105 = var84 < 0L ? -1 : 1;
         }
      } else if (!var20) {
         float var39 = Math.min(1.0F, z / 0.85F);
         class_437 var40 = class_310.method_1551().field_1755;
         if (X && var40 != null) {
            float[] var74 = HomeRenderer3.logoSlot(var40);
            long var42 = 1987385634L;
            if (1987385634L == 0L) {
               boolean var96 = false;
            } else {
               int var97 = var42 < 0L ? -1 : 1;
            }

            float var45 = Easing.CUBIC_IN_OUT.apply(Math.max(0.0F, (var39 - 0.25F) / 0.75F));
            var62 = h(var19.fromGui(var74[0]), var62, var45);
            var64 = h(var19.fromGui(var74[1]), var57 + var28, var45);
            var66 = h(var19.fromGui(var74[2]), var60, var45);
            var35 = 1.0F - var45;
         } else {
            float var41 = Easing.EMPHASIZED.apply(var39);
            var32 = var41;
            var64 += (1.0F - var41) * var60 * 0.12F;
         }

         float var75 = (z - 0.85F) / 0.5F;
         var37 = S(var75);
      } else {
         float var68 = Math.min(1.0F, o / 1.35F);
         var37 = 1.0F - S(o / 0.7F);
         var34 *= 1.0F - S(var68);
         class_437 var71 = class_310.method_1551().field_1755;
         long var76 = 1987385634L;
         if (1987385634L == 0L) {
            boolean var98 = false;
         } else {
            int var99 = var76 < 0L ? -1 : 1;
         }

         if (X && var71 != null) {
            float[] var44 = HomeRenderer3.logoSlot(var71);
            float var86 = Easing.bezier(0.55F, 0.0F, 0.12F, 1.0F).apply(var68);
            var62 = h(r, var19.fromGui(var44[0]), var86);
            var64 = h(N, var19.fromGui(var44[1]), var86);
            long var46 = 1987385635L;
            if (1987385635L == 0L) {
               boolean var100 = false;
            } else {
               int var101 = var46 < 0L ? -1 : 1;
            }

            var66 = h(i, var19.fromGui(var44[2]), var86);
            var35 = S(o / 0.8F);
            float var48;
            int var49 = (var48 = var68 - 1.0F) == 0.0F ? 0 : (var48 < 0.0F ? -1 : 1);
            short var50 = 10019;
            if (var49 >= 0) {
               var32 = 0.0F;
            }

            long var51 = 1987385634L;
            if (1987385634L == 0L) {
               boolean var102 = false;
            } else {
               int var103 = var51 < 0L ? -1 : 1;
            }
         } else {
            var62 = r;
            var64 = N + var22 * 0.6F;
            var66 = i;
            var32 = 1.0F - S(o / 0.9F);
         }
      }

      float var70;
      int var73 = (var70 = var32 - 0.003F) == 0.0F ? 0 : (var70 < 0.0F ? -1 : 1);
      short var78 = 10019;
      if (var73 > 0) {
         HomeRenderer2.draw(var0, var62, var64, var66, var32, var33, var34, 0.9F, var18, var35, var38, var37, var36);
      }

      float var81;
      if (var20) {
         long var82 = 1987385635L;
         if (1987385635L == 0L) {
            boolean var106 = false;
         } else {
            int var107 = var82 < 0L ? -1 : 1;
         }

         var81 = 1.0F - S(o / 0.32F);
      } else {
         var81 = g ? S((z - 0.595F) / 0.4F) : S((z - 0.75F) / 0.5F);
      }

      J = Math.min(1.0F, C == HomeRenderer.TypeB.A ? 0.0F : J + var5 * 2.5F);
      if (var81 > 0.003F) {
         float var83 = C == HomeRenderer.TypeB.A ? var3 : 1.0F;
         float var85 = Math.max(0.0F, Math.min(1.0F, O.target(var83).update(var5)));
         float var87 = Math.max(120.0F, Math.min(220.0F, var16 * 0.16F));
         float var88 = 3.0F;
         float var47 = var57 + HomeRenderer2.height(var60) + var17 * 0.07F + var22;
         float var89 = C == HomeRenderer.TypeB.F ? S(o / 0.32F) : 0.0F;
         float var90 = var87 * (1.0F - var89);
         float var91 = var16 / 2.0F - var90 / 2.0F;
         var0.pushAlpha(var81 * var23);
         var0.roundRect(var91, var47, var90, var88, var88 / 2.0F, 486539263);
         float var92 = var90 * var85;
         var78 = 10019;
         if (var92 > 0.5F) {
            float var52 = 1.0F - S(J) * 0.25F * (1.0F - (float)Math.sin(z * 5.0F));
            var0.glow(var91, var47, var92, var88, var88 / 2.0F, 8.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.38F * var52));
            var0.roundRectGradient(var91, var47, var92, var88, var88 / 2.0F, 0.0F, Icons.PURPLE, Icons.PINK);
            float var53 = z * 0.55F % 1.4F - 0.2F;
            float var54 = var91 + var92 * var53;
            var0.pushClip(var91, var47, var92, var88, var88 / 2.0F);
            var0.roundRectGradient(var54 - 18.0F, var47, 18.0F, var88, 0.0F, 0.0F, 16777215, 1728053247);
            var0.roundRectGradient(var54, var47, 18.0F, var88, 0.0F, 0.0F, 1728053247, 16777215);
            var0.popClip();
            var0.glowCircle(var91 + var92, var47 + var88 / 2.0F, 1.6F, 7.0F, BlendMode.withAlpha(Theme.tint(-5633), 0.55F));
         }

         var0.popAlpha();
      }
   }

   private static float T(short var0, long var1, float var3) {
      HomeSupport2.ensureSourceClassInitialized();
      float var4 = var3 - 0.9F;
      if (var4 < 0.0F) {
         return -1.75F;
      } else {
         float var5 = var4 % 3.4F / 1.3F;
         return var5 >= 1.0F ? -1.75F : HomeRenderer2.sweepAt(S(var5));
      }
   }

   private static void D(
      byte var0, Canvas var1, float var2, float var3, int var4, int var5, float var6, float var7, float var8, float var9, float var10, boolean var11
   ) {
      float var12 = var3;
      float var13 = var7 - var3;
      var1.push();
      HomeSupport2.ensureSourceClassInitialized();
      var1.translate(0.0F, var6);
      var1.pushAlpha(var8);
      g(var1, var2, 0.0F, var3, Theme.tint(-16054248), Theme.tint(-16382960));
      int var14 = 0;

      while (true) {
         if (var14 >= 10) {
            float var21 = var2 * 0.5F;
            float var23 = var12 + var13 * 0.15F;
            var1.glowCircle(var21, var23, var2 * 0.34F, var13 * 0.9F, BlendMode.withAlpha(Icons.VIOLET, 0.12F));
            break;
         }

         float var15 = 1.0F - S(var14 / 10.0F);
         if (var0 < 0) {
            break;
         }

         float var16 = 1.0F - S((var14 + 1) / 10.0F);
         g(
            var1,
            var2,
            var12 + var13 * var14 / 10.0F,
            var12 + var13 * (var14 + 1) / 10.0F,
            BlendMode.withAlpha(Theme.tint(-16382960), var15),
            BlendMode.withAlpha(Theme.tint(-16382960), var16)
         );
         var14++;
      }

      float var22 = var11 ? 0.0F : 1.0F;
      var1.glowCircle(
         var2 * 0.18F + (float)Math.sin(var10 * 0.11) * 40.0F * var22,
         var3 * 0.22F + (float)Math.cos(var10 * 0.09) * 24.0F * var22,
         var3 * 0.26F,
         var3 * 0.46F,
         BlendMode.withAlpha(Icons.INDIGO, 0.3F)
      );
      var1.glowCircle(
         var2 * 0.84F + (float)Math.cos(var10 * 0.08) * 46.0F * var22,
         var3 * 0.82F + (float)Math.sin(var10 * 0.1) * 28.0F * var22,
         var3 * 0.28F,
         var3 * 0.46F,
         BlendMode.withAlpha(Icons.MAGENTA, 0.15F)
      );
      var1.glowCircle(var2 * 0.5F, var3 * 0.46F, var3 * 0.2F, var3 * 0.42F, BlendMode.withAlpha(Icons.VIOLET, 0.16F));

      for (int var24 = 0; var24 < 130; var24++) {
         float var17 = E[var24];
         float var18 = V[var24] * var3;
         float var19 = var18 > var12 ? 1.0F - Math.min(1.0F, (var18 - var12) / (var13 * 0.6F)) : 1.0F;
         float var20 = var11 ? 0.7F : 0.5F + 0.5F * (float)Math.sin(var10 * (0.5F + var17 * 1.6F) + n[var24]);
         var1.circle(m[var24] * var2, var18, 0.4F + var17 * 0.9F, BlendMode.withAlpha(-1, (0.06F + 0.45F * var20 * var17) * var19));
      }

      if (var0 >= 0) {
         var1.popAlpha();
         var1.pop();
         if (var9 > 0.003F) {
            var1.roundRectRadial(-var2 * 0.15F, -var3 * 0.15F, var2 * 1.3F, var3 * 1.3F, 0.0F, 0, BlendMode.withAlpha(-16777216, 0.42F * var8 * var9));
         }
      }
   }

   private static void g(Canvas var0, float var1, float var2, float var3, int var4, int var5) {
      var0.triangle(0.0F, var2, var1, var2, var1, var3, var4, var4, var5);
      var0.triangle(0.0F, var2, var1, var3, 0.0F, var3, var4, var5, var5);
   }

   public static boolean ownsLogo() {
      return Q != null && class_310.method_1551().method_18506() == Q && X && (C != HomeRenderer.TypeB.F || !(o >= 1.35F));
   }

   public static float screenClock() {
      if (Q != null && !g && class_310.method_1551().method_18506() == Q) {
         return C == HomeRenderer.TypeB.F ? o - 0.5F : -100.0F;
      } else {
         return Float.NaN;
      }
   }

   public static boolean holdsIntro() {
      return Q != null && !g && C != HomeRenderer.TypeB.F && class_310.method_1551().method_18506() == Q;
   }

   static float sinceLanding() {
      if (Q != null && class_310.method_1551().method_18506() == Q) {
         return C == HomeRenderer.TypeB.F ? o - 1.35F : -10.0F;
      } else {
         return Float.NaN;
      }
   }

   private static float S(float var0) {
      var0 = Math.max(0.0F, Math.min(1.0F, var0));
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   private static float R(float var0, float var1, float var2) {
      float var3 = (var0 - 1.15F) / 0.22F;
      return (float)Math.exp(-var3 * var3);
   }

   private static float h(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

private static enum TypeB {
      A,
      w,
      F;

}
}
