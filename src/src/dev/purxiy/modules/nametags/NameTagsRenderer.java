// Sourced By DevFFNightmare
package dev.purxiy.modules.nametags;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.loader.fabric.client.ClientRegistry14;
import dev.purxiy.loader.fabric.client.ClientRegistry5;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.Objects;
import net.minecraft.class_1044;
import net.minecraft.class_1068;
import net.minecraft.class_12137;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_746;

public final class NameTagsRenderer {
   public static final float O = 222.0F;
   private static final float P = 51.0F;
   private static final float j = 132.0F;
   private static final float w = 156.0F;
   private static final float Y = 2.0F;
   private static final float e = 1.2F;
   private static final String[] u = new String[]{"Glass", "Solid", "Minimal"};
   private static final TagPainter.Look[] E = new TagPainter.Look[]{TagPainter.Look.c, TagPainter.Look.e, TagPainter.Look.h};
   private static final float N = 4.6F;
   private final NameTagsModule B;
   private final Object[] p = new Object[]{new Object(), new Object(), new Object()};
   private final TagPainter.TypeQ k = new TagPainter.TypeQ();
   private final TagPainter.TypeO S = new TagPainter.TypeO();
   private class_2561 V = class_2561.method_43473();
   private int v = -1;
   private float q;
   private float H;
   private float g;
   private float i;
   private boolean l;


   private static final long[] c = new long[]{
      9033344014912087832L,
      -8859085131404847042L,
      3804560634728906254L,
      8541914382141600082L,
      2162433129589978575L,
      3058313722067118185L,
      9139382561332109038L,
      270787480908715524L,
      -9173147145164575167L,
      -4131942646342035536L,
      7081023162732003284L,
      -995721700350952398L,
      4243497837066627311L,
      5672765126593634407L,
      -96427870475116240L,
      4952566629895639832L,
      -8193032956930322558L,
      4424572684218916596L,
      397185242296537074L,
      -6016111566221233654L,
      -7909887489330585436L,
      -311511236506500203L,
      -1993867521222809080L
   };
   private static final Integer[] d = new Integer[23];

   public NameTagsRenderer(NameTagsModule var1) {
      this.B = var1;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 124604543432033L;
      long var8 = var6 ^ 20160689635265L;
      long var10 = var6 ^ 104595094673116L;
      long var12 = var6 ^ 27183020534025L;
      this.q = var2;
      this.H = var3;
      this.g = var4;
      this.l = var5;
      this.i = this.i + MotionStyle.HOVER.dt(var1.dt);
      Canvas var14 = var1.canvas;
      var14.text(Fonts.MEDIUM, "Choose a look", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var14.text(Fonts.REGULAR, "Live, with your own name and face", var2, var3 + 30.0F, 11.5F, Icons.TEXT_2);
      this.K(var10);
      float var15 = (var4 - 16.0F) / 3.0F;

      for (int var16 = 0; var16 < 3; var16++) {
         float var17 = var2 + var16 * (var15 + 8.0F);
         float var18 = var3 + 51.0F;
         boolean var19 = this.B.G.is(u[var16]);
         boolean var20 = var5 && var1.hover(this.p[var16], var17, var18, var15, 156.0F, DrawContext.TypeE.HAND);
         var14.roundRect(var17, var18, var15, 156.0F, 10.0F, var19 ? Theme.tint(-13623231) : (var20 ? Theme.tint(-14542287) : Theme.tint(-15725540)));
         this.v(var14, var17 + 3.0F, var18 + 3.0F, var15 - 6.0F, 129.0F, var16, var12);
      }

      var14.captureBackdrop();
      class_2960 var27 = Q(var8);

      for (int var28 = 0; var28 < 3; var28++) {
         float var29 = var2 + var28 * (var15 + 8.0F);
         float var30 = var3 + 51.0F;
         boolean var31 = this.B.G.is(u[var28]);
         float var21 = var29 + var15 / 2.0F;
         float var22 = var30 + 132.0F - 16.0F;
         float var23 = var21 - 22.0F;
         var14.pushClip(var29 + 3.0F, var30 + 3.0F, var15 - 6.0F, 129.0F, 8.0F);
         figure(var14, var27, var23, var22);
         var14.icon("sword", var21 + 40.0F, var22 - 4.0F, 13.0F, -4199950);
         var14.push();
         var14.translate(var21 + 40.0F, var22 - 16.0F);
         TagPainter.draw(var14, this.S, E[var28], this.B.C.floatValue(), this.i);
         var14.pop();
         float var24 = Math.min(1.2F, (var15 - 16.0F) / this.k.width());
         float var25 = this.k.width() * var24 / 2.0F;
         float var26 = Math.max(var29 + 8.0F + var25, Math.min(var29 + var15 - 8.0F - var25, var23));
         var14.push();
         var14.translate(var26, var22 - 64.0F - 6.0F);
         var14.scale(var24);
         TagPainter.draw(var14, this.k, E[var28], this.B.C.floatValue());
         var14.pop();
         var14.popClip();
         var14.roundRectOutline(var29, var30, var15, 156.0F, 10.0F, 1.0F, var31 ? Theme.tint(-4222985) : Theme.tint(633708021));
         var14.text(Fonts.MEDIUM, u[var28], var21, var30 + 132.0F + 12.0F, 10.5F, var31 ? Icons.TEXT : Icons.TEXT_2, FontStyle.CENTER, FontWeight.MIDDLE);
      }
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      if (this.l && var3 == 0 && !(var1 < this.q) && !(var1 >= this.q + this.g)) {
         float var4 = var2 - this.H;
         float var5 = var1 - this.q;
         if (!(var4 < 51.0F) && !(var4 >= 207.0F)) {
            float var6 = (this.g + 8.0F) / 3.0F;
            int var7 = (int)(var5 / var6);
            if (var7 < 3 && var5 - var7 * var6 < var6 - 8.0F) {
               this.B.G.select(var7);
               UiAudioPolicy.play("CLICK");
               return true;
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private void K(long var1) {
      long var4 = 108876467931808L;
      NameTagsModule var6 = this.B;
      TagPainter.ensureSourceClassInitialized();
      class_310 var7 = class_310.method_1551();
      class_746 var8 = var7.field_1724;
      class_2561 var9 = ClientRegistry14.own((class_2561)(var8 != null ? var8.method_5476() : class_2561.method_43470(var7.method_1548().method_1676())));
      boolean var10 = var6.f.is("Minecraft");
      int var11 = ClientRegistry5.generation();
      short var12 = 23973;
      long var13 = 51072277865404L;
      if (51072277865404L == 0L) {
         boolean var10000 = false;
      } else {
         int var37 = var13 < 0L ? -1 : 1;
      }

      var11 *= 31;
      long var16 = 51072277865404L;
      if (51072277865404L == 0L) {
         boolean var38 = false;
      } else {
         int var39 = var16 < 0L ? -1 : 1;
      }

      int var18 = var11 * 31 + (var6.Q.enabled() ? 31 : 0) + (var10 ? 1 : 0);
      long var19 = 51072277865404L;
      if (51072277865404L == 0L) {
         boolean var40 = false;
      } else {
         int var41 = var19 < 0L ? -1 : 1;
      }

      long var21 = 51072277865405L;
      if (51072277865405L == 0L) {
         boolean var42 = false;
      } else {
         int var43 = var21 < 0L ? -1 : 1;
      }

      label180: {
         if (var18 == this.v && Objects.equals(var9, this.V)) {
            long var23 = 51072277865404L;
            if (51072277865404L == 0L) {
               boolean var44 = false;
            } else {
               int var45 = var23 < 0L ? -1 : 1;
            }

            if (this.k.g != null) {
               long var25 = 51072277865404L;
               if (51072277865404L == 0L) {
                  boolean var46 = false;
               } else {
                  int var47 = var25 < 0L ? -1 : 1;
               }

               if (!this.k.g.stale()) {
                  break label180;
               }
            }
         }

         this.v = var18;
         this.V = var9;
         this.k.g = NameTagsRenderer3.of(var9, 9.0F, Fonts.SEMIBOLD, Fonts.BOLD, Icons.TEXT, var6.Q.enabled(), var10);
         this.k.m = NameTagsRenderer3.of(class_2561.method_43470(Translations.tr("Level 42")), 7.0F, Fonts.MEDIUM, Fonts.SEMIBOLD, Icons.TEXT_2, false, var10);
         TagPainter.TypeO.TypeC var35 = new TagPainter.TypeO.TypeC();
         var35.r = NameTagsRenderer3.of(class_2561.method_43471("item.minecraft.diamond_sword"), 8.0F, Fonts.MEDIUM, Fonts.SEMIBOLD, Icons.TEXT, true, var10);
         var35.X = -8394766;
         var35.t = true;
         this.S.O.clear();
         this.S.O.add(var35);
      }

      TagPainter.TypeQ var27 = this.k;
      var27.r = var6.d.enabled() ? Q(var4) : null;
      long var28 = 51072277865404L;
      if (51072277865404L == 0L) {
         boolean var48 = false;
      } else {
         int var49 = var28 < 0L ? -1 : 1;
      }

      if (!var6.s.enabled()) {
         var27.m = null;
      } else if (var27.m == null) {
         this.v = -1;
      }

      float var32 = this.i % 4.6F;
      long var14 = 51072277865404L;
      if (51072277865404L == 0L) {
         boolean var50 = false;
      } else {
         int var51 = var14 < 0L ? -1 : 1;
      }

      float var30 = var32 < 1.2F ? 0.9F : (var32 < 3.0F ? 0.55F : 0.55F + 0.35F * NameTagsSupport2.clamp01((var32 - 3.0F) / 1.2F));
      float var31 = var32 - 1.2F;
      boolean var17 = var6.w.is("Off");
      long var34 = 51072277865404L;
      if (51072277865404L == 0L) {
         boolean var52 = false;
      } else {
         int var53 = var34 < 0L ? -1 : 1;
      }

      var27.R = var17 ? 0 : (var6.w.is("Bar") ? 1 : 2);
      var27.u = var30;
      long var20 = 51072277865405L;
      if (51072277865405L == 0L) {
         boolean var54 = false;
      } else {
         int var55 = var20 < 0L ? -1 : 1;
      }

      float var3;
      label170: {
         if (var31 >= 0.0F) {
            long var36 = 51072277865405L;
            if (51072277865405L == 0L) {
               boolean var56 = false;
            } else {
               int var57 = var36 < 0L ? -1 : 1;
            }

            if (var31 < 1.2F) {
               var3 = 0.9F - 0.35F * NameTagsSupport2.clamp01((var31 - 0.4F) / 0.6F);
               break label170;
            }
         }

         var3 = var30;
      }

      var27.P = var3;
      var13 = 51072277865405L;
      if (51072277865405L == 0L) {
         boolean var58 = false;
      } else {
         int var59 = var13 < 0L ? -1 : 1;
      }

      var27.W = 0.0F;
      var27.z = var30 * 20.0F;
      var27.U = false;
      var27.o = false;
      long var15 = 51072277865404L;
      if (51072277865404L == 0L) {
         boolean var60 = false;
      } else {
         int var61 = var15 < 0L ? -1 : 1;
      }

      var27.s = var31 >= 0.0F && var31 < 0.5F ? 1.0F - var31 / 0.5F : 0.0F;
      TagPainter.measure(var27);
      TagPainter.measure(this.S);
   }

   private void v(Canvas var1, float var2, float var3, float var4, float var5, int var6, long var7) {
      int[] var9 = new int[]{Theme.tint(-12965274), Theme.tint(-14016174), Theme.tint(-15067849)};
      int[] var10 = new int[]{Theme.tint(-4879658), Theme.tint(-7574332), Theme.tint(-10794346)};
      var1.pushClip(var2, var3, var4, 129.0F, 8.0F);
      var1.roundRectGradient(var2, var3, var4, 129.0F, 8.0F, 90.0F, var9[var6], var10[var6]);
      var1.glowCircle(var2 + var4 * 0.78F, var3 + 36.12F, 7.0F, 18.0F, BlendMode.withAlpha(Theme.tint(-6410), 0.5F - var6 * 0.08F));
      TagPainter.ensureSourceClassInitialized();
      var1.circle(var2 + var4 * 0.78F, var3 + 36.12F, 4.5F, Theme.tint(-463105));

      for (int var11 = 0; var11 < 9; var11++) {
         float var12 = var2 + var4 * (0.08F + 0.103F * var11) + var11 % 3 * 3;
         float var13 = var3 + 129.0F * (0.12F + 0.07F * (var11 * 7 % 4));
         float var14 = 0.35F + 0.3F * (float)Math.sin(this.i * 1.7F + var11 * 1.9F);
         long var15 = 116457161890919L;
         if (116457161890919L == 0L) {
            boolean var10000 = false;
         } else {
            int var19 = var15 < 0L ? -1 : 1;
         }

         var1.circle(var12, var13, 0.7F, BlendMode.withAlpha(-1, var14 * (0.4F + 0.3F * var6)));
      }

      var1.roundRect(var2 - 20.0F, var3 + 129.0F - 30.0F, var4 * 0.6F, 40.0F, 20.0F, Theme.tint(-11847048));
      long var18 = 116457161890920L;
      if (116457161890920L == 0L) {
         boolean var20 = false;
      } else {
         int var21 = var18 < 0L ? -1 : 1;
      }

      var1.roundRect(var2 + var4 * 0.42F, var3 + 129.0F - 26.0F, var4 * 0.75F, 40.0F, 22.0F, Theme.tint(-12635800));
      var1.roundRectGradient(var2, var3 + 129.0F - 16.0F, var4, 16.0F, 0.0F, 90.0F, -12821953, -14535649);
      var1.rect(var2, var3 + 129.0F - 16.0F, var4, 1.0F, 864673931);
      var1.popClip();
   }

   static void figure(Canvas var0, class_2960 var1, float var2, float var3) {
      long var4 = 55852490536527L;
      long var6 = var4 ^ 79986892895286L;
      float var8 = 2.0F;

      GpuTextureView var9;
      try {
         class_1044 var10 = class_310.method_1551().method_1531().method_4619(var1);
         var9 = var10.method_71659();
      } catch (IllegalStateException var14) {
         return;
      }

      boolean var15 = var9.getHeight(0) * 2 == var9.getWidth(0);
      class_12137 var11 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
      float var12 = var2 - 8.0F * var8;
      float var13 = var3 - 32.0F * var8;
      var0.shadow(var2 - 7.0F * var8, var3 - 1.0F, 14.0F * var8, 3.0F, 1.5F, 4.0F, 1711276032);
      x(var6, var0, var9, var11, var15, var12 + 4.0F * var8, var13 + 20.0F * var8, 4, 12, 4, 20, 4, 36, var8);
      x(var6, var0, var9, var11, var15, var12 + 8.0F * var8, var13 + 20.0F * var8, 4, 12, 20, 52, 4, 52, var8);
      x(var6, var0, var9, var11, var15, var12 + 4.0F * var8, var13 + 8.0F * var8, 8, 12, 20, 20, 20, 36, var8);
      x(var6, var0, var9, var11, var15, var12, var13 + 8.0F * var8, 4, 12, 44, 20, 44, 36, var8);
      x(var6, var0, var9, var11, var15, var12 + 12.0F * var8, var13 + 8.0F * var8, 4, 12, 36, 52, 52, 52, var8);
      x(var6, var0, var9, var11, var15, var12 + 4.0F * var8, var13, 8, 8, 8, 8, 40, 8, var8);
   }

   private static void x(
      long var0,
      Canvas var2,
      GpuTextureView var3,
      class_12137 var4,
      boolean var5,
      float var6,
      float var7,
      int var8,
      int var9,
      int var10,
      int var11,
      int var12,
      int var13,
      float var14
   ) {
      TagPainter.ensureSourceClassInitialized();
      float var15 = var5 ? 32.0F : 64.0F;
      long var16 = 134635640963705L;
      if (134635640963705L == 0L) {
         boolean var10000 = false;
      } else {
         int var32 = var16 < 0L ? -1 : 1;
      }

      long var19 = 134635640963705L;
      if (134635640963705L == 0L) {
         boolean var33 = false;
      } else {
         int var34 = var19 < 0L ? -1 : 1;
      }

      if (var5) {
         long var21 = 134635640963704L;
         if (134635640963704L == 0L) {
            boolean var35 = false;
         } else {
            int var36 = var21 < 0L ? -1 : 1;
         }

         long var24 = 134635640963705L;
         if (134635640963705L == 0L) {
            boolean var37 = false;
         } else {
            int var38 = var24 < 0L ? -1 : 1;
         }

         if (var11 >= 32) {
            long var26 = 134635640963704L;
            if (134635640963704L == 0L) {
               boolean var41 = false;
            } else {
               int var42 = var26 < 0L ? -1 : 1;
            }

            var10 = var10 == 20 ? 4 : 44;
            byte var28 = 20;
            var2.image(
               var3,
               var4,
               false,
               var6,
               var7,
               var8 * var14,
               var9 * var14,
               (var10 + var8) / 64.0F,
               var28 / var15,
               var10 / 64.0F,
               (var28 + var9) / var15,
               0.0F,
               -1
            );
            return;
         }
      }

      float var30 = var8 * var14;
      float var22 = var9 * var14;
      float var23 = var10 / 64.0F;
      float var31 = var11;
      long var25 = 134635640963705L;
      if (134635640963705L == 0L) {
         boolean var39 = false;
      } else {
         int var40 = var25 < 0L ? -1 : 1;
      }

      var2.image(var3, var4, false, var6, var7, var30, var22, var23, var31 / var15, (var10 + var8) / 64.0F, (var11 + var9) / var15, 0.0F, -1);
      if (!var5 || var13 < 32) {
         var2.image(
            var3, var4, false, var6, var7, var8 * var14, var9 * var14, var12 / 64.0F, var13 / var15, (var12 + var8) / 64.0F, (var13 + var9) / var15, 0.0F, -1
         );
      }
   }

   private static class_2960 Q(long var0) {
      TagPainter.ensureSourceClassInitialized();
      class_310 var2 = class_310.method_1551();
      long var3 = 108876467931807L;
      if (108876467931807L == 0L) {
         boolean var10000 = false;
      } else {
         int var6 = var3 < 0L ? -1 : 1;
      }

      return var2.field_1724 != null
         ? var2.field_1724.method_52814().comp_1626().comp_3627()
         : class_1068.method_4648(var2.method_1548().method_44717()).comp_1626().comp_3627();
   }

}
