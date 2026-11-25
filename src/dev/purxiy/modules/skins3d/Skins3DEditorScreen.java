package dev.purxiy.modules.skins3d;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.loader.fabric.client.ArmorRenderProvider;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.DrawContext.Cursor;
import java.util.Locale;
import net.minecraft.class_2960;
import net.minecraft.class_7920;
import net.minecraft.class_8685;

public final class Skins3DEditorScreen {
   private static final int M = -4222985;
   private static final float x = 36.0F;
   private static final float U = 236.0F;
   private static final float o = 12.0F;
   private static final float g = 156.0F;
   private static final float m = 7.0F;
   private static final float I = 34.0F;
   private static final float r = 30.0F;
   public static final float p = 474.0F;
   private static final SkinPreset[] A = SkinPreset.values();
   private static final float B = 0.38F;
   private static final float n = 32.0F;
   private static final Skins3DRenderer S = new Skins3DRenderer("wardrobe");
   private static final Skins3DRenderer[] c = new Skins3DRenderer[A.length];
   private final Skins3DModule G;
   private final Animator[] R = new Animator[A.length + 1];
   private final Animator[] a = new Animator[A.length];
   private final Animator Y = Animator.smooth(0.0F);
   private final Animator E = new Animator(1.0F, 120.0F, 20.0F);
   private final float[] f = new float[]{1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F};
   private final Skins3DRenderer.SkinFrame C = new Skins3DRenderer.SkinFrame();
   private final Skins3DRenderer.SkinFrame T = new Skins3DRenderer.SkinFrame();
   private final Skins3DRenderer.SkinRenderState W = new Skins3DRenderer.SkinRenderState();
   private final Skins3DRenderer.SkinRenderState v = new Skins3DRenderer.SkinRenderState();
   private float L;
   private float P;
   private float V;
   private float e;
   private boolean i;
   private boolean s;
   private float J = 0.38F;
   private float Z;
   private float t;
   private float H;
   private float y;
   private SkinPreset d;
   private static final String[] h;
   private static final long[] j;

   public Skins3DEditorScreen(Skins3DModule var1) {
      this.G = var1;

      for (int var2 = 0; var2 < this.R.length; var2++) {
         this.R[var2] = Animator.smooth(0.0F);
      }

      for (int var3 = 0; var3 < A.length; var3++) {
         this.a[var3] = Animator.smooth(0.0F);
      }

      this.v.i = 18.0F;
      this.v.J = 9.0F;
      this.v.S = 0.42F;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 111610256569617L;
      long var8 = var6 ^ 72463068357382L;
      int var10 = (int)((var6 ^ 30182312264682L) >>> 48);
      long var11 = (var6 ^ 30182312264682L) << 16 >>> 16;
      long var13 = var6 ^ 137787160630456L;
      long var15 = var6 ^ 21686888980423L;
      long var17 = var6 ^ 71613802919762L;
      this.L = var2;
      this.P = var3;
      this.V = var4;
      this.i = var5 && !var1.masked();
      float var19 = Math.min(var1.dt, 0.1F);
      this.e += var19;
      Canvas var20 = var1.canvas;
      SkinPreset var21 = this.G.preset();
      SkinPreset var22 = this.d != null ? this.d : var21;
      class_8685 var23 = Skins3DRig.own();
      var20.text(Fonts.MEDIUM, "Wardrobe", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var20.text(Fonts.REGULAR, A.length + " skins", var2 + var4, var3 + 12.0F, 10.5F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.TOP);
      this.C(var1, var20, var2, var15, var3 + 36.0F, var4, var22, var23, var19);
      float var24 = (var4 - (A.length - 1) * 7.0F) / A.length;
      float var25 = var3 + 36.0F + 236.0F + 12.0F;
      SkinPreset var26 = null;
      float var27 = this.G.b.floatValue();

      for (int var28 = 0; var28 < A.length; var28++) {
         SkinPreset var29 = A[var28];
         float var30 = var2 + var28 * (var24 + 7.0F);
         boolean var31 = this.i && var1.hover(this.R[var28], var30, var25, var24, 156.0F, Cursor.HAND);
         float var32 = this.R[var28].target(var31 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         if (var31) {
            var26 = var29;
         }

         boolean var33 = var29 == var21;
         var20.roundRect(
            var30, var25, var24, 156.0F, 10.0F, BlendMode.lerp(Theme.tint(-15725031), Theme.tint(-4222985), (var33 ? 0.1F : 0.025F) + var32 * 0.04F)
         );
         float var34 = 126.0F;
         var20.pushClip(var30 + 2.0F, var25 + 2.0F, var24 - 4.0F, var34 - 2.0F, 8.0F);
         var20.roundRectGradient(var30 + 2.0F, var25 + 2.0F, var24 - 4.0F, var34 - 2.0F, 8.0F, 90.0F, Theme.tint(-15068112), Theme.tint(-15988202));
         float var35 = var25 + var34 - 12.0F;
         var20.glowCircle(
            var30 + var24 / 2.0F,
            var35 - var34 * 0.34F,
            var24 * 0.22F,
            var24 * 0.62F,
            BlendMode.withAlpha(var29.y, 0.08F + 0.12F * var32 + (var33 ? 0.06F : 0.0F))
         );
         var20.push();
         var20.translate(var30 + var24 / 2.0F, var35);
         var20.scale(1.0F, 0.26F);
         var20.softCircle(0.0F, 0.0F, var24 * 0.26F, var24 * 0.16F, -1610414840);
         var20.pop();
         class_2960 var36 = var29 == SkinPreset.n ? (var23 == null ? null : var23.comp_1626().comp_3627()) : var29.texture();
         if (var36 != null) {
            float var37 = this.a[var28].target(var31 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
            float var38 = 0.55F * (A.length / 2 - var28) / (A.length / 2.0F);
            this.T.T = var38 * (1.0F - var37) + 0.08F * (float)Math.sin(this.e * 0.9F + var28) * var37;
            this.T.N = this.e + var28 * 0.7F;
            this.T.V = 0.12F * (float)Math.sin(this.e * 0.5F + var28 * 1.3F) * (1.0F - var37);
            this.T.v = 0.04F;
            this.T.n = var32 * 1.2F;
            this.T.i = 0.0F;
            this.T.build(this.H(var29, var17));
            this.v.m = var29.y;
            this.v.N = var32;
            float var39 = var34 * 0.8F;
            float var40 = var39 / 33.0F;
            boolean var41 = c[var28]
               .draw(
                  var20,
                  var36,
                  M(var29, (char)var10, var23, var11),
                  null,
                  false,
                  this.T,
                  this.v,
                  var27,
                  var30 + 2.0F,
                  var25 + 2.0F,
                  var24 - 4.0F,
                  var34 - 2.0F,
                  var40 * this.H(var29, var17).scale(this.h(var8, var29)),
                  12.0F
               );
            if (!var41) {
               M(var20, var30 + var24 / 2.0F, var35, var40, var13);
            }
         }

         var20.popClip();
         var20.roundRectOutline(
            var30, var25, var24, 156.0F, 10.0F, var33 ? 1.3F : 1.0F, BlendMode.withAlpha(Theme.tint(-4222985), var33 ? 0.72F : 0.12F + var32 * 0.2F)
         );
         float var43 = var20.textWidth(Fonts.SEMIBOLD, var29.N, 10.5F);
         float var44 = var43 > var24 - 10.0F ? Math.max(8.6F, 10.5F * (var24 - 10.0F) / var43) : 10.5F;
         var20.textEllipsized(
            Fonts.SEMIBOLD,
            var29.N,
            var30 + var24 / 2.0F,
            var25 + 156.0F - 15.0F - 1.0F,
            var44,
            var24 - 8.0F,
            var33 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var32),
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
         if (var33) {
            var20.circle(var30 + var24 - 12.0F, var25 + 12.0F, 5.5F, Theme.tint(-4222985));
            var20.icon("check", var30 + var24 - 12.0F, var25 + 12.0F, 8.0F, Icons.BG_1);
         }
      }

      if (var26 != this.d && var26 != null) {
         this.Y.snap(0.6F);
      }

      this.d = var26;
      float var42 = var3 + 474.0F - 17.0F;
      var20.circle(var2 + 3.0F, var42, 2.5F, var22.y);
      var20.textEllipsized(Fonts.REGULAR, var22.c, var2 + 14.0F, var42, 10.5F, var4 - 14.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private void C(DrawContext var1, Canvas var2, float var3, long var4, float var6, float var7, SkinPreset var8, class_8685 var9, float var10) {
      long var11 = 40019813258775L;
      byte var13 = 0;
      long var14 = 139573949789947L;
      byte var16 = 0;
      long var17 = 89510529422191L;
      short var19 = 29168;
      int var20 = -1203156424;
      long var21 = 100345276647765L;
      long var23 = 27296051339689L;
      long var25 = 40285366813251L;
      var2.roundRectGradient(var3, var6, var7, 236.0F, 12.0F, 90.0F, Theme.tint(-14870991), Theme.tint(-16053995));
      SkinPreset.ensureSourceClassInitialized();
      var2.roundRectOutline(var3, var6, var7, 236.0F, 12.0F, 1.0F, Theme.tint(633708021));
      var2.pushClip(var3 + 1.0F, var6 + 1.0F, var7 - 2.0F, 234.0F, 11.0F);
      float var27 = var3 + var7 / 2.0F;
      float var28 = 174.64F;
      float var29 = var28 / 33.0F;
      float var30 = 30.0F;
      float var31 = var6 + 236.0F - var30;
      float var32 = 0.85F + 0.15F * (float)Math.sin(this.e * 0.9F);
      float var33 = this.Y.target(0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      var2.glowCircle(var27, var31 - var28 * 0.5F, var28 * 0.24F, var28 * 0.62F, BlendMode.withAlpha(var8.y, (0.15F + 0.12F * var33) * var32));

      for (int var34 = 0; var34 < 16; var34++) {
         float var35 = var3 + 14.0F + A(var16, var34 * 3, var17) * (var7 - 28.0F);
         float var36 = var6 + 8.0F;
         float var37 = A(var16, var34 * 3 + 1, var17) * 177.0F;
         long var38 = 129996159085269L;
         int var77 = var38 < 0L ? -1 : 1;
         float var41 = var36 + var37;
         float var42 = 0.5F + 0.5F * (float)Math.sin(this.e * (0.7F + A(var16, var34 * 3 + 2, var17)) + var34);
         var2.circle(var35, var41, 0.6F + A(var16, var34 * 7, var17) * 0.7F, BlendMode.withAlpha(-1, 0.08F + 0.25F * var42));
      }

      float var72 = var7 * 0.3F;
      long var74 = 129996159085270L;
      int var72x = var74 < 0L ? -1 : 1;
      float var75 = Math.min(var72, var28 * 0.42F);
      var2.push();
      var2.translate(var27, var31);
      var2.scale(1.0F, 0.24F);
      var2.softCircle(0.0F, 0.0F, var75 * 0.55F, var75 * 0.5F, -1341979384);
      var2.ring(0.0F, 0.0F, var75, 0.8F, BlendMode.withAlpha(Theme.tint(-4222985), 0.22F + 0.2F * var33));
      float var39 = ((float)Math.toDegrees(-this.J) % 360.0F + 360.0F) % 360.0F;
      var2.arc(0.0F, 0.0F, var75, 1.3F, var39, 60.0F, BlendMode.withAlpha(var8.y, 0.7F));
      var2.pop();
      this.H = Math.max(0.0F, this.H - var10);
      float var40 = this.J;
      if (!this.s) {
         long var76 = 129996159085270L;
         int var79 = var76 < 0L ? -1 : 1;
         if (this.y > 0.0F) {
            var72 = Math.min(this.y, var10 * (2.5F + this.y * 2.2F));
            long var44 = 129996159085270L;
            int var44x = var44 < 0L ? -1 : 1;
            this.J += var72;
            this.y -= var72;
         } else {
            this.y = 0.0F;
            float var78 = 0.38F + 0.34F * (float)Math.sin(this.e * 0.32F);
            float var45 = this.H > 0.0F ? 0.0F : 1.0F - (float)Math.exp(-var10 * 1.6F);
            this.J = this.J + X(var78 - this.J, var19, var20) * var45;
         }
      }

      long var77 = 129996159085269L;
      int var801 = var77 < 0L ? -1 : 1;
      this.Z = this.Z + ((var10 > 0.0F ? (this.J - var40) / var10 : 0.0F) - this.Z) * Math.min(1.0F, var10 * 6.0F);
      this.C.T = this.J;
      this.C.N = this.e;
      this.C.V = 0.18F * (float)Math.sin(this.e * 0.45F);
      this.C.v = 0.05F;
      float var79 = 3.5F * (float)Math.sin(this.e * 1.15F) + 1.5F * (float)Math.sin(this.e * 2.6F + 1.0F);
      this.C.p = 9.0F + var79 + Math.min(28.0F, Math.abs(this.Z) * 9.0F);
      float var80x = this.E.target(this.h(var11, var8)).update(MotionStyle.HOVER.dt(var1.dt));
      SkinPreset.Data var46 = this.H(var8, var25);
      float[] var47 = new float[]{
         var46.headWidth(), var46.headHeight(), var46.headDepth(), var46.bodyWidth(), var46.bodyDepth(), var46.legWidth(), var46.legDepth()
      };
      float var48 = 1.0F - (float)Math.exp(-var10 * 9.0F);

      for (int var49 = 0; var49 < this.f.length; var49++) {
         this.f[var49] = this.f[var49] + (var47[var49] - this.f[var49]) * var48;
         long var50 = 129996159085269L;
         int var50x = var50 < 0L ? -1 : 1;
      }

      long var81 = 129996159085270L;
      int var52 = var81 < 0L ? -1 : 1;
      this.C.Y = this.f[0];
      this.C.C = this.f[1];
      this.C.J = this.f[2];
      this.C.q = this.f[3];
      this.C.E = this.f[4];
      this.C.w = this.f[5];
      this.C.j = this.f[6];
      this.p(var2, var3, var7, var31, var29, var80x, var8, var21);
      class_8685 var52x = var9 == null ? null : var8.dress(var9);
      long var53 = 129996159085270L;
      boolean var56x = this.i && var1.hover(this.R[this.R.length - 1], var3, var6, var7, 236.0F, Cursor.HAND);
      float var57 = this.R[this.R.length - 1].target(!var56x && !this.s ? 0.0F : 1.0F).update(MotionStyle.HOVER.dt(var1.dt));
      this.W.m = BlendMode.lerp(Theme.tint(-4222985), var8.y, 0.5F);
      this.W.N = var57 * 0.6F;
      boolean var58 = var52x != null
         && S.draw(
            var2,
            var52x.comp_1626().comp_3627(),
            M(var8, (char)var13, var9, var14),
            var52x.comp_1627() == null ? null : var52x.comp_1627().comp_3627(),
            ArmorRenderProvider.wears(var52x),
            this.C,
            this.W,
            this.G.b.floatValue(),
            var3,
            var6,
            var7,
            236.0F,
            var29 * var80x * 32.0F / (24.0F + 8.0F * this.f[1]),
            var30
         );
      if (!var58) {
         M(var2, var27, var31, var29, var23);
      }

      int var59 = var8.ordinal() + 1;
      String var60 = String.format(Locale.ROOT, "%02d / %s", var59, Translations.tr(var8.N).toUpperCase(Locale.ROOT));
      float var61 = var2.text(Fonts.MEDIUM, var60, var3 + 14.0F, var6 + 17.0F, 8.6F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.TOP, 0.06F);
      String var62 = Translations.tr(var8.P).toUpperCase(Locale.ROOT);
      float var63 = var2.textWidth(Fonts.SEMIBOLD, var62, 7.4F, 0.08F) + 14.0F;
      float var64 = var3 + 14.0F + var61 + 8.0F;
      float var65 = var6 + 14.0F;
      var2.roundRect(var64, var65, var63, 15.0F, 7.5F, BlendMode.withAlpha(var8.y, 0.16F));
      var2.roundRectOutline(var64, var65, var63, 15.0F, 7.5F, 1.0F, BlendMode.withAlpha(var8.y, 0.4F));
      var2.text(Fonts.SEMIBOLD, var62, var64 + var63 / 2.0F, var65 + 7.5F, 7.4F, BlendMode.lerp(var8.y, -1, 0.55F), FontStyle.CENTER, FontWeight.MIDDLE, 0.08F);
      boolean var66 = this.G.enabled();
      long var67 = 129996159085270L;
      int var65x = var67 < 0L ? -1 : 1;
      String var69x;
      if (!var66) {
         var69x = "Switch 3D Skins on to wear it";
      } else {
         long var70 = 129996159085270L;
         int var68 = var70 < 0L ? -1 : 1;
         var69x = this.s ? "Every angle" : "Drag to turn";
      }

      var2.text(Fonts.REGULAR, var69x, var27, var6 + 236.0F - 9.0F, 9.0F, var57 > 0.5F ? Icons.TEXT_2 : Icons.TEXT_3, FontStyle.CENTER, FontWeight.MIDDLE);
      var2.popClip();
   }

   private void p(Canvas var1, float var2, float var3, float var4, float var5, float var6, SkinPreset var7, long var8) {
      long var10 = 132025361773048L;
      float var12 = var2 + var3 - 26.0F;
      SkinPreset.ensureSourceClassInitialized();
      float var13 = var4 - 32.0F * var5;
      float var14 = var4 - 32.0F * var5 * var6;
      int var15 = BlendMode.withAlpha(Theme.tint(-4222985), 0.16F);
      var1.line(var12, var4, var12, Math.min(var13, var14) - 4.0F, 1.0F, var15);
      var1.line(var12 - 4.0F, var4, var12 + 4.0F, var4, 1.0F, var15);
      boolean var16 = Math.abs(var6 - 1.0F) > 0.015F;
      if (var16) {
         var1.line(var12 - 4.0F, var13, var12 + 4.0F, var13, 1.0F, BlendMode.withAlpha(Theme.tint(-4222985), 0.3F));
         var1.text(Fonts.MEDIUM, t(var10, 1.0F), var12 - 9.0F, var13, 8.4F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.MIDDLE);
      }

      int var17;
      float var18;
      label35: {
         var17 = BlendMode.lerp(var7.y, -1, 0.35F);
         var1.line(var12 - 6.0F, var14, var12 + 6.0F, var14, 1.4F, BlendMode.withAlpha(var17, 0.9F));
         var1.circle(var12, var14, 2.2F, var17);
         if (var16) {
            long var19 = 100345276647764L;
            int var22 = var19 < 0L ? -1 : 1;
            if (Math.abs(var14 - var13) < 12.0F) {
               long var22x = 100345276647764L;
               int var23 = var22x < 0L ? -1 : 1;
               var18 = var14 > var13 ? var13 + 12.0F : var13 - 12.0F;
               break label35;
            }
         }

         var18 = var14;
      }

      var1.text(Fonts.SEMIBOLD, t(var10, var6), var12 - 9.0F, var18, 8.8F, var17, FontStyle.RIGHT, FontWeight.MIDDLE);
   }

   private static String t(long var0, float var2) {
      return String.format(Locale.ROOT, "%.2f m", 1.8F * var2);
   }

   private SkinPreset.Data H(SkinPreset var1, long var2) {
      return this.G.M.enabled() ? var1.h : SkinPreset.Data.P;
   }

   private float h(long var1, SkinPreset var3) {
      return this.G.M.enabled() ? var3.t : 1.0F;
   }

   private static void M(Canvas var0, float var1, float var2, float var3, long var4) {
      var0.roundRect(var1 - 4.0F * var3, var2 - 32.0F * var3, 8.0F * var3, 8.0F * var3, 3.0F, 587202559);
      var0.roundRect(var1 - 4.0F * var3, var2 - 24.0F * var3, 8.0F * var3, 24.0F * var3, 3.0F, 385875967);
   }

   private static boolean M(SkinPreset var0, char var1, class_8685 var2, long var3) {
      SkinPreset.ensureSourceClassInitialized();
      return var0 == SkinPreset.n ? var2 != null && var2.comp_1629() == class_7920.field_41122 : var0.o;
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      if (this.i && var3 == 0 && !(var1 < this.L) && !(var1 >= this.L + this.V)) {
         float var4 = var2 - this.P;
         if (var4 >= 36.0F && var4 < 272.0F) {
            this.s = true;
            this.t = var1;
            this.y = 0.0F;
            return true;
         } else {
            float var5 = 284.0F;
            if (!(var4 < var5) && !(var4 >= var5 + 156.0F)) {
               float var6 = (this.V - (A.length - 1) * 7.0F) / A.length;
               int var7 = (int)((var1 - this.L) / (var6 + 7.0F));
               if (var7 >= 0 && var7 < A.length && !(var1 - this.L - var7 * (var6 + 7.0F) > var6)) {
                  SkinPreset var8 = A[var7];
                  if (this.G.preset() != var8) {
                     this.G.B.set(var8.N);
                     this.y = (float) (Math.PI * 2);
                     this.Y.snap(1.0F);
                  }

                  UiAudioPolicy.play("SELECT");
                  return true;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   public void mouseDrag(float var1, float var2) {
      if (this.s) {
         this.J = this.J + (var1 - this.t) * 0.012F;
         this.t = var1;
         this.H = 4.0F;
      }
   }

   public void mouseUp() {
      this.s = false;
   }

   private static float X(float var0, int var1, int var2) {
      SkinPreset.ensureSourceClassInitialized();
      float var3 = (float) (Math.PI * 2);
      var0 %= var3;
      return var0 > Math.PI ? var0 - var3 : (var0 < -Math.PI ? var0 + var3 : var0);
   }

   private static float A(byte var0, int var1, long var2) {
      int var4 = var1 * 668265261;
      var4 ^= var4 >>> 15;
      var4 *= 739982445;
      var4 ^= var4 >>> 12;
      return (var4 & 65535) / 65535.0F;
   }

   static void release() {
      S.release();

      for (Skins3DRenderer var3 : c) {
         var3.release();
      }

      Skins3DRenderer.releaseMeshes();
   }

   static {
      for (int var0 = 0; var0 < c.length; var0++) {
         c[var0] = new Skins3DRenderer(A[var0].name());
      }

      h = new String[11];
      j = new long[]{
         7041780036883765524L,
         2145206703788305203L,
         2659880858901847178L,
         -5061704409890381321L,
         -8460573173573206862L,
         194463540754341436L,
         4360818236312069514L,
         2527063333807370973L,
         5778953360229796757L,
         -2738535477150764839L,
         9086229248997303332L,
         878106924525264946L,
         6310564098533325301L,
         -9049878980077372871L,
         -3818570785033913718L,
         1907460427929495060L,
         6042820578772130794L,
         -7189696333475668410L,
         -7347006611634169808L
      };
   }
}
