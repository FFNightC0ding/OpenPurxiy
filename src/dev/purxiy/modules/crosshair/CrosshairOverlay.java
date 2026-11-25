package dev.purxiy.modules.crosshair;

import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.ui.Theme;

public final class CrosshairOverlay {
   public static int s = 0;
   private static final String[] b = new String[10];
   private static final Integer[] d = new Integer[3];

   private CrosshairOverlay() {
   }

   public static void draw(
      Canvas var0,
      CustomCrosshairModule var1,
      String var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      boolean var12
   ) {
      long var13 = 18725259572577L;
      long var15 = var13 ^ 118509618994447L;
      if (!var1.C.enabled()) {
         var10 = 0.0F;
      }

      float var17 = var1.B.is("Breathe") ? (float)Math.sin(var6 * 2.2F) * var1.n.floatValue() : 0.0F;
      float var18 = var1.z.floatValue() + var7 * var1.u.floatValue() + var8 * var1.t.floatValue() + var17;
      float var19 = var1.Q.floatValue();
      float var20 = var1.r.floatValue();
      float var21 = var18 + var19;
      int var22 = BlendMode.lerp(var1.primaryColor(), var1.U.get(), var1.T.enabled() ? var9 * 0.85F : 0.0F);
      int var23 = BlendMode.lerp(var1.secondaryColor(), var1.U.get(), var1.T.enabled() ? var9 * 0.7F : 0.0F);
      int var24 = BlendMode.lerp(var22, -1, var10 * 0.65F);
      var0.push();
      var0.translate(var3, var4);
      var0.scale(var5 * var1.b.floatValue());
      var0.pushAlpha(var1.i.floatValue());

      for (int var25 = 0; var25 < 3; var25++) {
         float var26 = var20;
         int var27 = var24;
         int var28 = var23;
         if (var25 == 0) {
            if (var1.N.floatValue() <= 0.0F) {
               continue;
            }

            var26 = var20 + 5.0F;
            var27 = var28 = BlendMode.withAlpha(var23, var1.N.floatValue() * 0.13F);
         } else if (var25 == 1) {
            if (var1.s.floatValue() <= 0.0F) {
               continue;
            }

            var26 = var20 + 2.0F * var1.s.floatValue();
            var27 = var28 = Theme.tint(-536279277);
         }

         z(var0, var2, var18, var19, var26, var27, var15, var28);
         if (var1.g.enabled() || var2.equals("Dot")) {
            float var29 = var20 * 0.52F + (var25 == 0 ? 2.5F : (var25 == 1 ? var1.s.floatValue() : 0.0F));
            var0.circle(0.0F, 0.0F, var29, var27);
         }
      }

      if (var1.B.is("Orbit")) {
         float var30 = var6 * 42.0F;
         var0.arc(0.0F, 0.0F, var21 + 4.0F, 1.1F, var30, 65.0F, BlendMode.fade(var23, 0.05F), var23, true);
         var0.arc(0.0F, 0.0F, var21 + 4.0F, 1.1F, var30 + 180.0F, 65.0F, BlendMode.fade(var24, 0.05F), var24, true);
      }

      if (var12 && var11 < 0.995F) {
         float var31 = var21 + (var1.B.is("Orbit") ? 8 : 5);
         if (var1.d.is("Bar")) {
            var0.roundRect(-8.0F, var31, 16.0F, 2.2F, 1.1F, Theme.tint(-1341585645));
            if (var11 > 0.01F) {
               var0.roundRect(-8.0F, var31, 16.0F * var11, 2.2F, 1.1F, var24);
            }
         } else {
            var0.ring(0.0F, 0.0F, var31, 1.8F, Theme.tint(-1610021101));
            var0.arc(0.0F, 0.0F, var31, 1.5F, -90.0F, var11 * 360.0F, var23, var24, true);
         }
      }

      if (var1.C.enabled() && var10 > 0.001F) {
         var0.pushAlpha(var10);
         float var32 = var21 + 4.0F + (1.0F - var10) * 3.0F;

         for (int var33 = 0; var33 < 4; var33++) {
            var0.push();
            var0.rotate((float)Math.toRadians(45 + var33 * 90));
            var0.line(var32, 0.0F, var32 + 4.0F, 0.0F, 3.0F, Theme.tint(-1341585645));
            var0.line(var32, 0.0F, var32 + 4.0F, 0.0F, 1.5F, Theme.tint(-4865), var23);
            var0.pop();
         }

         var0.popAlpha();
      }

      var0.popAlpha();
      var0.pop();
   }

   private static void z(Canvas var0, String var1, float var2, float var3, float var4, int var5, long var6, int var8) {
      float var9 = var2 + var3;
      byte var11 = -1;
      switch (var1.hashCode()) {
         case -1736047847:
            if (var1.equals("Vesper")) {
               long var42 = 135031554041966L;
               int var38 = var42 < 0L ? -1 : 1;
               var11 = 0;
            }
            break;
         case -975259340:
            if (var1.equals("Diamond")) {
               long var41x = 135031554041966L;
               int var371 = var41x < 0L ? -1 : 1;
               var11 = 3;
            }
            break;
         case 2241628:
            if (var1.equals("Halo")) {
               long var40 = 135031554041965L;
               int var13x = var40 < 0L ? -1 : 1;
               var11 = 2;
            }
            break;
         case 29314283:
            boolean var12 = var1.equals("Brackets");
            long var13 = 135031554041966L;
            int var41 = var13 < 0L ? -1 : 1;
            if (var12) {
               var11 = 4;
            }
            break;
         case 65382432:
            if (var1.equals("Cross")) {
               long var16 = 135031554041965L;
               int var16x = var16 < 0L ? -1 : 1;
               var11 = 1;
            }
      }

      switch (var11) {
         case 0:
            for (int var43 = 0; var43 < 4; var43++) {
               long var45 = 135031554041966L;
               int var42 = var45 < 0L ? -1 : 1;
               var0.push();
               var0.rotate((float)Math.toRadians(var43 * 90));
               var0.line(var2, 0.0F, var9, -var3 * 0.52F, var4, var5, var8);
               var0.pop();
            }

            long var44 = 135031554041965L;
            int var39 = var44 < 0L ? -1 : 1;
            long var15 = 135031554041965L;
            int var44x = var15 < 0L ? -1 : 1;
            break;
         case 1:
            for (int var18 = 0; var18 < 4; var18++) {
               long var19 = 135031554041965L;
               int var18x = var19 < 0L ? -1 : 1;
               var0.push();
               var0.rotate((float)Math.toRadians(var18 * 90));
               var0.line(var2 + 1.0F, 0.0F, var2 + var3, 0.0F, var4, var5, var8);
               var0.pop();
            }

            long var46 = 135031554041965L;
            int var21 = var46 < 0L ? -1 : 1;
            long var21x = 135031554041966L;
            int var481 = var21x < 0L ? -1 : 1;
            break;
         case 2:
            for (int var24 = 0; var24 < 4; var24++) {
               var0.arc(0.0F, 0.0F, var2 + var3 * 0.7F, var4, var24 * 90 + 15, 60.0F, var5, var8, true);
               long var25 = 135031554041966L;
               int var22 = var25 < 0L ? -1 : 1;
            }

            long var47 = 135031554041966L;
            int var27 = var47 < 0L ? -1 : 1;
            long var27x = 135031554041965L;
            int var51 = var27x < 0L ? -1 : 1;
            break;
         case 3:
            for (int var30 = 0; var30 < 4; var30++) {
               long var31 = 135031554041966L;
               int var26 = var31 < 0L ? -1 : 1;
               var0.push();
               var0.rotate((float)Math.toRadians(var30 * 90));
               var0.line(0.0F, -var9, var9, 0.0F, var4, var5, var8);
               var0.pop();
            }

            long var48 = 135031554041966L;
            int var33 = var48 < 0L ? -1 : 1;
            long var33x = 135031554041966L;
            int var54 = var33x < 0L ? -1 : 1;
            break;
         case 4:
            for (int var36 = 0; var36 < 4; var36++) {
               long var37 = 135031554041965L;
               int var30 = var37 < 0L ? -1 : 1;
               var0.push();
               var0.rotate((float)Math.toRadians(var36 * 90));
               var0.line(var2, -var9, var9, -var9, var4, var5, var8);
               var0.line(var9, -var9, var9, -var2, var4, var8, var5);
               var0.pop();
            }

            long var49 = 135031554041966L;
            int var29 = var49 < 0L ? -1 : 1;
      }
   }

   public static void ensureSourceClassInitialized() {
   }
}
