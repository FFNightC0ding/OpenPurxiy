package dev.purxiy.modules.sky;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.DrawContext.Cursor;

public final class SkyRenderer {
   private static final int U = 3;
   private static final float d = 9.0F;
   private static final float n = 38.0F;
   private static final float F = 82.0F;
   private static final int[] D = new int[]{-3431681, -1906705, -3756065, -4089105, -4856599, -5520687, -1379336, -18584};
   private static final int j = 3;
   public static final float k = 335.0F;
   private static final String[] s = new String[]{
      "A luminous nebula with a quiet field of stars.",
      "Silver caustics flowing across a charcoal sky.",
      "Soft violet clouds, drifting through the dark.",
      "Violet light curtains and glimmering crystal dust.",
      "Pearlescent folds of lavender and pale cyan.",
      "Heavy rain clouds over a misty silver horizon.",
      "Soft winter clouds in a luminous, snowy white sky.",
      "Crimson clouds and golden fire, like a living fairy tale."
   };
   private final SkyModule H;
   private final Animator[] m = new Animator[D.length];
   private final SkyCodec[] Z = new SkyCodec[D.length];
   private float S;
   private float t;
   private float q;
   private float G;
   private boolean I;
   private boolean J;
   private static final Integer[] e = new Integer[79];

   public SkyRenderer(SkyModule var1) {
      this.H = var1;

      for (int var2 = 0; var2 < this.m.length; var2++) {
         this.m[var2] = Animator.smooth(0.0F);
      }

      for (int var3 = 0; var3 < this.Z.length; var3++) {
         this.Z[var3] = new SkyCodec();
      }
   }

   static int accent(int var0) {
      return D[var0];
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      this.S = var2;
      this.t = var3;
      this.q = var4;
      this.I = var5 && this.H.S.visible();
      this.G = this.G + MotionStyle.HOVER.dt(Math.min(var1.dt, 0.1F)) * 0.42F * this.H.v.floatValue();
      float var6 = this.J ? 1.0F - (float)Math.exp(-Math.min(var1.dt, 0.1F) * 7.0F) : 1.0F;
      this.J = true;

      for (int var7 = 0; var7 < this.Z.length; var7++) {
         this.Z[var7].followPicture(this.H, SkyKind.values()[var7], var6);
      }

      Canvas var17 = var1.canvas;
      var17.text(Fonts.MEDIUM, "Sky style", var2, var3 + 13.0F, 13.5F, Icons.TEXT);
      var17.text(Fonts.REGULAR, this.m.length + " skies", var2 + var4, var3 + 15.0F, 10.5F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.TOP);
      float var8 = this.C();
      int var9 = this.H.S.index();

      for (int var10 = 0; var10 < this.m.length; var10++) {
         float var11 = this.d(var10);
         float var12 = this.e(var10);
         int var13 = this.Z[var10].carry(D[var10]);
         boolean var14 = this.I && var1.hover(this.m[var10], var11, var12, var8, 82.0F, Cursor.HAND);
         float var15 = this.m[var10].target(var14 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         boolean var16 = var10 == var9;
         var17.roundRect(var11, var12, var8, 82.0F, 11.0F, BlendMode.lerp(Theme.tint(-15725031), var13, (var16 ? 0.1F : 0.025F) + var15 * 0.04F));
         var17.pushClip(var11 + 3.0F, var12 + 3.0F, var8 - 6.0F, 50.0F, 8.0F);
         paint(var17, var10, var11 + 3.0F, var12 + 3.0F, var8 - 6.0F, 50.0F, this.G, this.Z[var10]);
         var17.popClip();
         var17.roundRectOutline(var11, var12, var8, 82.0F, 11.0F, var16 ? 1.3F : 1.0F, BlendMode.withAlpha(var13, var16 ? 0.76F : 0.12F + var15 * 0.21F));
         if (var16) {
            var17.circle(var11 + var8 - 13.0F, var12 + 13.0F, 6.0F, Theme.tint(-552595943));
            var17.icon("check", var11 + var8 - 13.0F, var12 + 13.0F, 9.0F, var13);
         }

         var17.text(
            Fonts.SEMIBOLD,
            (CharSequence)this.H.S.modes().get(var10),
            var11 + var8 / 2.0F,
            var12 + 66.0F,
            11.5F,
            var16 ? var13 : Icons.TEXT_2,
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
      }

      var17.circle(var2 + 3.0F, var3 + 335.0F - 19.0F, 2.5F, this.Z[var9].carry(D[var9]));
      var17.textEllipsized(Fonts.REGULAR, s[var9], var2 + 14.0F, var3 + 335.0F - 19.0F, 10.5F, var4 - 14.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   static void paint(Canvas var0, int var1, float var2, float var3, float var4, float var5, float var6, SkyCodec var7) {
      long var8 = 132874430116203L;
      long var10 = var8 ^ 62602243552241L;
      long var12 = var8 ^ 9273971731444L;
      long var14 = var8 ^ 50164290291499L;
      long var16 = var8 ^ 120486059701847L;
      long var18 = var8 ^ 116821596899048L;
      int var20 = (int)((var8 ^ 116821596899048L) >>> 56);
      int var21 = (int)((var8 ^ 116821596899048L) << 8 >>> 32);
      int var22 = (int)(var18 << 40 >>> 40);
      long var23 = var8 ^ 18916738449709L;
      long var25 = var8 ^ 139659188583031L;
      long var27 = var8 ^ 102115867287492L;
      switch (var1) {
         case 0:
            l(var14, var0, var2, var3, var4, var5, var6, var7);
            break;
         case 1:
            G(var0, var12, var2, var3, var4, var5, var6, var7);
            break;
         case 2:
            U(var0, var25, var2, var3, var4, var5, var6, var7);
            break;
         case 3:
            f(var0, var2, var3, var4, var5, var6, var16, var7);
            break;
         case 4:
            t((byte)var20, var0, var2, var21, var3, var22, var4, var5, var6, var7);
            break;
         case 5:
            J(var0, var2, var3, var4, var5, var6, var7, var10);
            break;
         case 6:
            m(var0, var2, var3, var4, var5, var6, var23, var7);
            break;
         case 7:
            R(var0, var2, var3, var4, var5, var6, var27, var7);
      }
   }

   private static void l(long var0, Canvas var2, float var3, float var4, float var5, float var6, float var7, SkyCodec var8) {
      SkyKind.ensureSourceClassInitialized();
      var2.roundRectGradient(var3, var4, var5, var6, 0.0F, 125.0F, var8.carry(-14937546), var8.carry(-16315880));

      for (int var9 = 0; var9 < 5; var9++) {
         long var10 = 93763254941247L;
         int var24 = var10 < 0L ? -1 : 1;
         float var13 = var9 / 4.0F;
         float var14 = var3 + var5 * var13;
         float var15 = var4 + var6 * (0.72F - 0.46F * var13 + 0.08F * z(var7 + var9));
         float var16 = var6 * 0.2F;
         float var17 = var6 * 0.58F;
         long var18 = 93763254941247L;
         int var19 = var18 < 0L ? -1 : 1;
         var2.softCircle(var14, var15, var16, var17, var9 % 2 == 0 ? var8.carry(-2057489009) : var8.carry(2017805717));
      }

      long var21 = 93763254941248L;
      int var23 = var21 < 0L ? -1 : 1;

      for (int var22 = 0; var22 < 21; var22++) {
         float var24 = var3 + var5 * ((var22 * 37 + 17) % 101) / 101.0F;
         float var25 = var4 + var6 * ((var22 * 61 + 11) % 97) / 97.0F;
         var2.circle(var24, var25, var22 % 7 == 0 ? 0.8F : 0.45F, BlendMode.withAlpha(var8.carry(-1187073), 0.36F + 0.24F * z(var7 * 2.0F + var22)));
      }

      long var23x = 93763254941248L;
      int var26 = var23x < 0L ? -1 : 1;
   }

   private static void G(Canvas var0, long var1, float var3, float var4, float var5, float var6, float var7, SkyCodec var8) {
      SkyKind.ensureSourceClassInitialized();
      var0.roundRectGradient(var3, var4, var5, var6, 0.0F, 70.0F, var8.carry(-14473685), var8.carry(-15921131));

      for (int var9 = 0; var9 < 5; var9++) {
         float var10 = var3;
         float var11 = var4;

         for (int var12 = 0; var12 <= 18; var12++) {
            long var13 = 123929116973726L;
            int var16 = var13 < 0L ? -1 : 1;
            float var16x = var12 / 18.0F;
            float var17 = var3 + var5 * var16x;
            float var18 = var4 + var6 * (0.12F + var9 * 0.18F + 0.18F * z(var16x * 7.0F + var9 * 1.8F + var7) + 0.05F * z(var16x * 15.0F - var9 + var7 * 0.7F));
            long var19 = 123929116973726L;
            int var20 = var19 < 0L ? -1 : 1;
            if (var12 > 0) {
               var0.line(var10, var11, var17, var18, 5.0F, var8.carry(198763247));
               var0.line(var10, var11, var17, var18, 1.8F, var8.carry(601416431));
               var0.line(var10, var11, var17, var18, 0.55F, var8.carry(-2099255570));
            }

            var10 = var17;
            var11 = var18;
         }

         long var21 = 123929116973727L;
         int var14 = var21 < 0L ? -1 : 1;
      }
   }

   private static void U(Canvas var0, long var1, float var3, float var4, float var5, float var6, float var7, SkyCodec var8) {
      SkyKind.ensureSourceClassInitialized();
      var0.roundRectGradient(var3, var4, var5, var6, 0.0F, 90.0F, var8.carry(-15265250), var8.carry(-15659496));

      for (int var9 = 0; var9 < 2; var9++) {
         long var10 = 8649215298332L;
         int var22 = var10 < 0L ? -1 : 1;

         for (int var13 = 0; var13 < 6; var13++) {
            long var14 = 8649215298331L;
            int var16 = var14 < 0L ? -1 : 1;
            float var16x = var13 / 5.0F;
            float var17 = var3 + var5 * var16x;
            long var18 = 8649215298332L;
            int var19 = var18 < 0L ? -1 : 1;
            var0.softCircle(
               var17,
               var4 + var6 * (0.12F + var9 * 0.78F + 0.16F * z(var16x * 5.0F + var7 + var9 * 3)),
               var6 * 0.22F,
               var6 * 0.58F,
               var9 == 0 ? var8.carry(-2056957827) : var8.carry(-2140449914)
            );
         }

         long var22x = 8649215298332L;
         int var141 = var22x < 0L ? -1 : 1;
      }

      long var21 = 8649215298332L;
      int var11 = var21 < 0L ? -1 : 1;
      var0.softCircle(var3 + var5 * 0.55F, var4 + var6 * 0.4F, var6 * 0.5F, var6 * 0.75F, var8.carry(-1441459940));
   }

   private static void f(Canvas var0, float var1, float var2, float var3, float var4, float var5, long var6, SkyCodec var8) {
      SkyKind.ensureSourceClassInitialized();
      var0.roundRectGradient(var1, var2, var3, var4, 0.0F, 90.0F, var8.carry(-16251367), var8.carry(-14282432));
      int var9 = 0;

      while (var9 < 3) {
         long var10 = 23424315230012L;
         int var24 = var10 < 0L ? -1 : 1;

         for (int var13 = 0; var13 <= 36; var13++) {
            float var14 = var13 / 36.0F;
            float var15 = var1 + var3 * var14;
            float var16 = var2 + var4 * (0.42F + var9 * 0.19F + 0.12F * z(var14 * 5.0F + var9 * 2 + var5 * 0.7F) + 0.035F * z(var14 * 14.0F - var5 + var9));
            float var17 = var4 * (0.16F + 0.13F * (0.5F + 0.5F * z(var14 * 27.0F + var9 * 4 - var5 * 2.0F)));
            int var18 = BlendMode.lerp(var8.carry(-6786822), var8.carry(-942121), 0.5F + 0.5F * z(var14 * 6.0F - var5 + var9));
            long var19 = 23424315230011L;
            int var20 = var19 < 0L ? -1 : 1;
            var0.line(var15, var16 - var17, var15, var16, var3 * 0.035F, BlendMode.withAlpha(var18, 0.045F));
            var0.line(var15, var16 - var17 * 0.65F, var15, var16, var3 * 0.013F, BlendMode.withAlpha(var18, 0.13F));
            var0.softCircle(var15, var16, 0.4F, 2.2F, BlendMode.withAlpha(var18, 0.27F));
         }

         var9++;
         long var23 = 23424315230011L;
         int var27 = var23 < 0L ? -1 : 1;
      }

      long var22 = 23424315230012L;
      int var24 = var22 < 0L ? -1 : 1;
      var24 = 0;

      while (true) {
         long var25 = 23424315230012L;
         int var281 = var25 < 0L ? -1 : 1;
         if (var24 >= 17) {
            long var27 = 23424315230012L;
            int var33 = var27 < 0L ? -1 : 1;
            return;
         }

         float var26 = (var24 * 37 + 11) % 101 / 101.0F;
         float var28x = (var24 * 61 + 3) % 97 / 97.0F;
         long var29 = 23424315230011L;
         int var191 = var29 < 0L ? -1 : 1;
         var0.circle(
            var1 + var3 * var26,
            var2 + var4 * var28x,
            var24 % 5 == 0 ? 0.65F : 0.4F,
            BlendMode.withAlpha(var8.carry(-1845505), 0.42F + 0.2F * z(var5 * 2.0F + var24 * 1.7F))
         );
         var24++;
      }
   }

   private static void t(byte var0, Canvas var1, float var2, int var3, float var4, int var5, float var6, float var7, float var8, SkyCodec var9) {
      SkyKind.ensureSourceClassInitialized();
      var1.roundRectGradient(var2, var4, var6, var7, 0.0F, 25.0F, var9.carry(-14671819), var9.carry(-15192005));

      for (int var10 = 0; var10 < 7; var10++) {
         float var11 = var2;
         float var12 = var4;
         int var13 = 16643971;
         int var14 = var10 % 2 == 0 ? var9.carry(-3689235) : var9.carry(-5251616);

         for (int var15 = 0; var15 <= 12; var15++) {
            var13 = 16643971;
            float var16 = var15 / 12.0F;
            float var17 = var2 + var6 * (-0.05F + var10 * 0.18F + 0.09F * z(var16 * 3.5F + var10 * 0.55F + var8));
            float var18 = var4 + var7 * var16;
            var13 = 1238680;
            if (var15 > 0) {
               var1.line(var11, var12, var17, var18, var6 * 0.085F, BlendMode.withAlpha(var14, 0.05F));
               var1.line(var11, var12, var17, var18, var6 * 0.034F, BlendMode.withAlpha(var14, 0.11F));
               var1.line(var11, var12, var17, var18, 0.8F, BlendMode.withAlpha(var14, 0.26F));
            }

            var11 = var17;
            var12 = var18;
         }
      }
   }

   private static void J(Canvas var0, float var1, float var2, float var3, float var4, float var5, SkyCodec var6, long var7) {
      var0.roundRectGradient(var1, var2, var3, var4, 0.0F, 90.0F, var6.carry(-14273473), var6.carry(-7101010));
      SkyKind.ensureSourceClassInitialized();
      var0.softCircle(var1 + var3 * 0.58F, var2 + var4 * 0.95F, var3 * 0.28F, var3 * 0.4F, var6.carry(1920636062));
      int var9 = 0;

      while (var9 < 3) {
         long var10 = 70602908615322L;
         int var30 = var10 < 0L ? -1 : 1;

         for (int var13 = 0; var13 < 6; var13++) {
            float var14 = var13 / 5.0F;
            long var15 = 70602908615321L;
            int var33 = var15 < 0L ? -1 : 1;
            float var18 = var2 + var4 * (0.05F + var9 * 0.19F + 0.08F * z(var14 * 7.0F + var9 * 2 + var5 * 0.5F));
            float var19 = var1 + var3 * var14;
            float var20 = var4 * 0.19F;
            float var21 = var4 * 0.4F;
            long var22 = 70602908615322L;
            int var25 = var22 < 0L ? -1 : 1;
            if (var9 == 0) {
               var25 = var6.carry(-753129166);
            } else {
               long var26 = 70602908615322L;
               int var25x = var26 < 0L ? -1 : 1;
               var25 = var9 == 1 ? var6.carry(-1388754347) : var6.carry(-2024446095);
            }

            var0.softCircle(var19, var18, var20, var21, var25);
         }

         var9++;
         long var29 = 70602908615321L;
         int var32 = var29 < 0L ? -1 : 1;
      }

      long var28 = 70602908615321L;
      int var30 = var28 < 0L ? -1 : 1;
      var30 = 0;

      while (true) {
         long var31 = 70602908615322L;
         int var17 = var31 < 0L ? -1 : 1;
         if (var30 >= 16) {
            long var32 = 70602908615321L;
            int var38 = var32 < 0L ? -1 : 1;
            return;
         }

         float var17x = (var30 * 37 + 13) % 101 / 101.0F;
         float var33 = ((var30 * 59 + 7) % 97 / 97.0F + var5 * 0.3F) % 1.0F;
         float var34 = var1 + var3 * var17x;
         float var35 = var2 + var4 * (0.3F + var33 * 0.7F);
         var0.line(var34, var35, var34 - 1.4F, var35 + 5.0F, 0.55F, var6.carry(1724702180));
         var30++;
      }
   }

   private static void m(Canvas var0, float var1, float var2, float var3, float var4, float var5, long var6, SkyCodec var8) {
      SkyKind.ensureSourceClassInitialized();
      var0.roundRectGradient(var1, var2, var3, var4, 0.0F, 90.0F, var8.carry(-5522229), var8.carry(-985867));
      var0.softCircle(var1 + var3 * 0.7F, var2 + var4 * 0.3F, var4 * 0.08F, var4 * 0.38F, var8.carry(-1073741833));
      int var9 = 0;

      while (var9 < 3) {
         long var10 = 116468109619270L;
         int var27 = var10 < 0L ? -1 : 1;

         for (int var13 = 0; var13 < 5; var13++) {
            float var14 = var13 / 4.0F;
            float var15 = var2 + var4 * (0.13F + var9 * 0.33F + 0.09F * z(var14 * 6.0F + var9 + var5 * 0.35F));
            float var16 = var1 + var3 * var14;
            float var17 = var4 * 0.22F;
            float var18 = var4 * 0.45F;
            long var19 = 116468109619269L;
            int var22 = var19 < 0L ? -1 : 1;
            if (var9 == 0) {
               var22 = var8.carry(-1832332323);
            } else {
               long var23 = 116468109619269L;
               int var23x = var23 < 0L ? -1 : 1;
               var22 = var9 == 1 ? var8.carry(-1243025935) : var8.carry(-1056964609);
            }

            var0.softCircle(var16, var15, var17, var18, var22);
         }

         var9++;
         long var26 = 116468109619270L;
         int var30 = var26 < 0L ? -1 : 1;
      }

      long var25 = 116468109619270L;
      int var27 = var25 < 0L ? -1 : 1;
      var27 = 0;

      while (true) {
         long var28 = 116468109619270L;
         int var311 = var28 < 0L ? -1 : 1;
         if (var27 >= 15) {
            long var30 = 116468109619269L;
            int var36 = var30 < 0L ? -1 : 1;
            return;
         }

         float var29 = (var27 * 43 + 19) % 101 / 101.0F + 0.012F * z(var5 + var27);
         float var31x = ((var27 * 67 + 5) % 97 / 97.0F + var5 * 0.13F) % 1.0F;
         float var32 = var3 * var29;
         long var20 = 116468109619270L;
         int var39 = var20 < 0L ? -1 : 1;
         var0.circle(var1 + var32, var2 + var4 * var31x, var27 % 4 == 0 ? 1.1F : 0.6F, var8.carry(-570425345));
         var27++;
      }
   }

   private static void R(Canvas var0, float var1, float var2, float var3, float var4, float var5, long var6, SkyCodec var8) {
      SkyKind.ensureSourceClassInitialized();
      var0.roundRectGradient(var1, var2, var3, var4, 0.0F, 90.0F, var8.carry(-13299679), var8.carry(-1608666));
      var0.softCircle(var1 + var3 * 0.5F, var2 + var4, var3 * 0.3F, var3 * 0.5F, var8.carry(-1342194611));

      for (int var9 = 0; var9 < 6; var9++) {
         float var10 = var9;
         long var11 = 39610584018606L;
         int var30 = var11 < 0L ? -1 : 1;
         float var14 = var10 / 5.0F;
         float var15 = var1 + var3 * var14;
         float var16 = var2 + var4 * (0.1F + 0.1F * z(var9 * 2 + var5 * 0.5F));
         float var17 = var4 * 0.18F;
         float var18 = var4 * 0.38F;
         long var19 = 39610584018607L;
         int var23 = var19 < 0L ? -1 : 1;
         var0.softCircle(var15, var16, var17, var18, var9 % 2 == 0 ? var8.carry(-1402331088) : var8.carry(-1372187356));
      }

      long var26 = 39610584018606L;
      int var24 = var26 < 0L ? -1 : 1;

      for (int var13 = 0; var13 < 7; var13++) {
         float var28 = var1;
         float var29 = var2;

         for (int var31 = 0; var31 <= 10; var31++) {
            long var34 = 39610584018606L;
            int var37 = var34 < 0L ? -1 : 1;
            float var20 = var31 / 10.0F;
            float var21 = var1 + var3 * (0.03F + var13 * 0.16F + 0.06F * z(var20 * 5.0F + var13 * 1.7F + var5));
            float var22 = var2 + var4 * (1.15F - var20 * (0.6F + 0.22F * z(var13 * 2 + 0.5F)));
            long var23 = 39610584018607L;
            int var22x = var23 < 0L ? -1 : 1;
            if (var31 > 0) {
               float var25x = 1.0F - var20 * 0.78F;
               var0.line(var28, var29, var21, var22, var3 * 0.11F * var25x, var8.carry(1174366504));
               var0.line(var28, var29, var21, var22, var3 * 0.045F * var25x, var8.carry(-1996512706));
               var0.line(var28, var29, var21, var22, var3 * 0.012F * var25x, var8.carry(-1124080992));
            }

            var28 = var21;
            var29 = var22;
         }

         long var32 = 39610584018607L;
         int var36 = var32 < 0L ? -1 : 1;
      }

      long var27 = 39610584018607L;
      int var29 = var27 < 0L ? -1 : 1;

      for (int var30 = 0; var30 < 10; var30++) {
         float var33 = (var30 * 41 + 23) % 101 / 101.0F;
         float var35 = ((var30 * 61 + 11) % 97 / 97.0F + var5 * 0.18F) % 1.0F;
         var0.circle(
            var1 + var3 * (var33 + 0.015F * z(var5 + var30)),
            var2 + var4 * (1.0F - var35),
            0.6F,
            BlendMode.withAlpha(var8.carry(-12160), 0.42F + 0.18F * z(var5 * 2.0F + var30))
         );
      }
   }

   private float C() {
      return (this.q - 18.0F) / 3.0F;
   }

   private float d(int var1) {
      int var2 = var1 / 3 * 3;
      int var3 = Math.min(3, this.m.length - var2);
      float var4 = var1 % 3 + (3 - var3) * 0.5F;
      return this.S + var4 * (this.C() + 9.0F);
   }

   private float e(int var1) {
      return this.t + 38.0F + var1 / 3 * 91.0F;
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      if (this.I && this.H.S.visible() && var3 == 0) {
         for (int var4 = 0; var4 < this.m.length; var4++) {
            float var5 = this.d(var4);
            float var6 = this.e(var4);
            if (var1 >= var5 && var1 < var5 + this.C() && var2 >= var6 && var2 < var6 + 82.0F) {
               if (this.H.S.index() != var4) {
                  this.H.S.select(var4);
                  UiAudioPolicy.play("SELECT");
               }

               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private static float z(float var0) {
      return (float)Math.sin(var0);
   }
}
