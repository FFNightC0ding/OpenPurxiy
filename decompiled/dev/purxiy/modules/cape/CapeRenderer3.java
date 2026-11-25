package dev.purxiy.modules.cape;

import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.loader.fabric.client.ClientBuilder;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.DrawContext.TypeE;
import java.util.Locale;
import net.minecraft.class_8685;

public final class CapeRenderer3 {
   private static final int S = -4222985;
   private static final float Q = 36.0F;
   private static final float q = 220.0F;
   private static final float f = 12.0F;
   private static final float V = 80.0F;
   private static final float X = 8.0F;
   private static final float B = 34.0F;
   private static final int t = 3;
   private static final int w = (CapeDesign.values().length + 3 - 1) / 3;
   public static final float x = 268.0F + w * 80.0F + (w - 1) * 8.0F + 34.0F;
   private static final float g = 3.5615928F;
   private final CapeModule m;
   private final Animator[] E = new Animator[CapeDesign.values().length + 1];
   private final CapeRenderer.TypeK s = new CapeRenderer.TypeK();
   private float N;
   private float A;
   private float M;
   private float W;
   private boolean Y;
   private boolean z;
   private float j = 3.5615928F;
   private float n;
   private float G;
   private float c;
   private CapeDesign o;
   private static final long[] d = new long[]{
      4527975762438878933L,
      336747719851725437L,
      1339521064775765026L,
      -5804470069271032805L,
      302500589469837487L,
      -1223376126011619142L,
      -8812225121546970969L,
      680937956331177655L,
      3488196224400972266L,
      3664755750062829114L,
      -3160800545216676818L,
      -262969685222969298L,
      8066913503759017129L,
      6150996977291726249L,
      -6021263084389696445L,
      3750144862708924645L
   };

   public CapeRenderer3(CapeModule var1) {
      this.m = var1;

      for (int var2 = 0; var2 < this.E.length; var2++) {
         this.E[var2] = Animator.smooth(0.0F);
      }
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 53507037854572L;
      long var8 = var6 ^ 39274924540320L;
      this.N = var2;
      this.A = var3;
      this.M = var4;
      this.Y = var5 && !var1.masked();
      float var10 = Math.min(var1.dt, 0.1F);
      this.W += var10;
      Canvas var11 = var1.canvas;
      CapeDesign[] var12 = CapeDesign.values();
      CapeDesign var13 = this.m.selected();
      CapeDesign var14 = this.o != null ? this.o : var13;
      CapeCache.preview(this.o);
      var11.text(Fonts.MEDIUM, "Wardrobe", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var11.text(Fonts.REGULAR, var12.length + " capes", var2 + var4, var3 + 12.0F, 10.5F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.TOP);
      this.F(var8, var1, var11, var2, var3 + 36.0F, var4, var14, var10);
      float var15 = (var4 - 16.0F) / 3.0F;
      float var16 = var3 + 36.0F + 220.0F + 12.0F;
      GpuTextureView var17 = CapeCache.swatches();
      CapeDesign var18 = null;

      for (int var19 = 0; var19 < var12.length; var19++) {
         CapeDesign var20 = var12[var19];
         float var21 = var2 + var19 % 3 * (var15 + 8.0F);
         float var22 = var16 + var19 / 3 * 88.0F;
         boolean var23 = this.Y && var1.hover(this.E[var19], var21, var22, var15, 80.0F, TypeE.HAND);
         float var24 = this.E[var19].target(var23 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         if (var23) {
            var18 = var20;
         }

         boolean var25 = var20 == var13;
         var11.roundRect(
            var21, var22, var15, 80.0F, 10.0F, BlendMode.lerp(Theme.tint(-15725031), Theme.tint(-4222985), (var25 ? 0.1F : 0.025F) + var24 * 0.04F)
         );
         var11.roundRectOutline(
            var21, var22, var15, 80.0F, 10.0F, var25 ? 1.3F : 1.0F, BlendMode.withAlpha(Theme.tint(-4222985), var25 ? 0.72F : 0.12F + var24 * 0.2F)
         );
         float var26 = 40.0F;
         float var27 = 64.0F;
         float var28 = var24 * 1.5F;
         float var29 = var21 + 8.0F;
         float var30 = var22 + (80.0F - var27) / 2.0F - var28;
         var11.glowCircle(
            var29 + var26 / 2.0F, var30 + var27 / 2.0F, var26 * 0.4F, var26 * 0.9F, BlendMode.withAlpha(var20.o, 0.1F + 0.14F * var24 + (var25 ? 0.08F : 0.0F))
         );
         if (var17 != null) {
            float var31 = (float)var19 / var12.length;
            float var32 = (float)(var19 + 1) / var12.length;
            var11.image(var17, CapeCache.swatchSampler(), false, var29, var30, var26, var27, var31, 0.0F, var32, 1.0F, 4.0F, -1);
         } else {
            var11.roundRectGradient(
               var29, var30, var26, var27, 4.0F, 90.0F, BlendMode.lerp(var20.o, -16777216, 0.35F), BlendMode.lerp(var20.o, -16777216, 0.75F)
            );
         }

         var11.roundRectOutline(var29, var30, var26, var27, 4.0F, 1.0F, BlendMode.withAlpha(-1, 0.08F + 0.1F * var24));
         float var34 = var29 + var26 + 11.0F;
         float var35 = var21 + var15 - var34 - (var25 ? 22 : 8);
         var11.textEllipsized(
            Fonts.SEMIBOLD,
            var20.E,
            var34,
            var22 + 40.0F - 8.0F,
            12.0F,
            var35,
            var25 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var24),
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
         var11.textEllipsized(
            Fonts.MEDIUM,
            var20.g.toUpperCase(Locale.ROOT),
            var34,
            var22 + 40.0F + 9.0F,
            7.4F,
            var21 + var15 - var34 - 8.0F,
            BlendMode.lerp(Icons.TEXT_3, var20.o, 0.35F + 0.4F * Math.max(var24, var25 ? 1.0F : 0.0F)),
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
         if (var25) {
            var11.circle(var21 + var15 - 14.0F, var22 + 14.0F, 5.5F, Theme.tint(-4222985));
            var11.icon("check", var21 + var15 - 14.0F, var22 + 14.0F, 8.0F, Icons.BG_1);
         }
      }

      this.o = var18;
      float var33 = var3 + x - 17.0F;
      var11.circle(var2 + 3.0F, var33, 2.5F, var14.o);
      var11.textEllipsized(Fonts.REGULAR, var14.L, var2 + 14.0F, var33, 10.5F, var4 - 14.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private void F(long var1, DrawContext var3, Canvas var4, float var5, float var6, float var7, CapeDesign var8, float var9) {
      byte var10 = 0;
      long var11 = 63520599731582L;
      long var13 = 93271951895785L;
      short var15 = 21716;
      char var16 = '醏';
      int var17 = (int)(var13 << 48 >>> 48);
      var4.roundRectGradient(var5, var6, var7, 220.0F, 12.0F, 90.0F, Theme.tint(-14870991), Theme.tint(-16053995));
      var4.roundRectOutline(var5, var6, var7, 220.0F, 12.0F, 1.0F, Theme.tint(633708021));
      CapeModule.ensureSourceClassInitialized();
      var4.pushClip(var5 + 1.0F, var6 + 1.0F, var7 - 2.0F, 218.0F, 11.0F);
      float var18 = var5 + var7 / 2.0F;
      float var19 = 193.6F;
      float var20 = var19 / 32.5F;
      float var21 = 18.0F;
      float var22 = var6 + 220.0F - var21;
      float var23 = 0.85F + 0.15F * (float)Math.sin(this.W * 0.9F);
      var4.glowCircle(var18, var22 - var19 * 0.5F, var19 * 0.24F, var19 * 0.62F, BlendMode.withAlpha(var8.o, 0.16F * var23));

      for (int var24 = 0; var24 < 14; var24++) {
         long var25 = 20969978002123L;
         int var49 = var25 < 0L ? -1 : 1;
         float var28 = var5 + 14.0F + H(var24 * 3, var15, (short)var16, (short)var17) * (var7 - 28.0F);
         float var29 = var6 + 8.0F + H(var24 * 3 + 1, var15, (short)var16, (short)var17) * 165.0F;
         float var30 = 0.5F + 0.5F * (float)Math.sin(this.W * (0.7F + H(var24 * 3 + 2, var15, (short)var16, (short)var17)) + var24);
         var4.circle(var28, var29, 0.6F + H(var24 * 7, var15, (short)var16, (short)var17) * 0.7F, BlendMode.withAlpha(-1, 0.08F + 0.25F * var30));
      }

      float var47 = var7 * 0.3F;
      long var48 = 20969978002124L;
      int var47x = var48 < 0L ? -1 : 1;
      float var49 = Math.min(var47, var19 * 0.42F);
      var4.push();
      var4.translate(var18, var22);
      var4.scale(1.0F, 0.24F);
      var4.softCircle(0.0F, 0.0F, var49 * 0.55F, var49 * 0.5F, -1341979384);
      var4.ring(0.0F, 0.0F, var49, 0.8F, BlendMode.withAlpha(Theme.tint(-4222985), 0.22F));
      float var50 = ((float)Math.toDegrees(-this.j) * 1.0F % 360.0F + 360.0F) % 360.0F;
      var4.arc(0.0F, 0.0F, var49, 1.3F, var50, 60.0F, BlendMode.withAlpha(var8.o, 0.7F));
      var4.pop();
      this.c = Math.max(0.0F, this.c - var9);
      float var51 = this.j;
      if (!this.z) {
         float var31 = 3.5615928F + 0.38F * (float)Math.sin(this.W * 0.32F);
         float var32 = this.c > 0.0F ? 0.0F : 1.0F - (float)Math.exp(-var9 * 1.6F);
         this.j = this.j + j((char)var10, var31 - this.j, var11) * var32;
      }

      long var52 = 20969978002124L;
      int var34 = var52 < 0L ? -1 : 1;
      this.n = this.n + ((var9 > 0.0F ? (this.j - var51) / var9 : 0.0F) - this.n) * Math.min(1.0F, var9 * 6.0F);
      this.s.d = this.j;
      this.s.q = this.W;
      this.s.Y = 0.18F * (float)Math.sin(this.W * 0.45F);
      this.s.y = 0.05F;
      float var34x = 3.5F * (float)Math.sin(this.W * 1.15F) + 1.5F * (float)Math.sin(this.W * 2.6F + 1.0F);
      this.s.G = 9.0F + var34x + Math.min(28.0F, Math.abs(this.n) * 9.0F);
      class_8685 var35 = ClientBuilder.skin();
      long var36 = 20969978002123L;
      int var36x = var36 < 0L ? -1 : 1;
      boolean var38x = this.Y && var3.hover(this.E[this.E.length - 1], var5, var6, var7, 220.0F, TypeE.HAND);
      float var39 = this.E[this.E.length - 1].target(!var38x && !this.z ? 0.0F : 1.0F).update(MotionStyle.HOVER.dt(var3.dt));
      boolean var40 = var35 != null
         && CapeRenderer.draw(var4, CapeCache.wear(var35), this.s, var5, var6, var7, 220.0F, var20, var21, 1.0F, var39 * 0.6F, this.W);
      long var41 = 20969978002124L;
      int var41x = var41 < 0L ? -1 : 1;
      if (!var40) {
         var4.roundRect(var18 - 4.0F * var20, var22 - 32.0F * var20, 8.0F * var20, 8.0F * var20, 3.0F, 587202559);
         var4.roundRect(var18 - 4.0F * var20, var22 - 24.0F * var20, 8.0F * var20, 24.0F * var20, 3.0F, 385875967);
      }

      var4.text(
         Fonts.MEDIUM,
         String.format(Locale.ROOT, "%02d / %s", var8.ordinal() + 1, Translations.tr(var8.E).toUpperCase(Locale.ROOT)),
         var5 + 14.0F,
         var6 + 17.0F,
         8.6F,
         Icons.TEXT_2,
         FontStyle.LEFT,
         FontWeight.TOP,
         0.06F
      );
      long var44x = 20969978002123L;
      int var43 = var44x < 0L ? -1 : 1;
      var4.text(
         Fonts.REGULAR,
         this.z ? "Every angle" : "Drag to turn",
         var18,
         var6 + 220.0F - 9.0F,
         9.0F,
         var39 > 0.5F ? Icons.TEXT_2 : Icons.TEXT_3,
         FontStyle.CENTER,
         FontWeight.MIDDLE
      );
      var4.popClip();
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      if (this.Y && var3 == 0 && !(var1 < this.N) && !(var1 >= this.N + this.M)) {
         float var4 = var2 - this.A;
         if (var4 >= 36.0F && var4 < 256.0F) {
            this.z = true;
            this.G = var1;
            return true;
         } else {
            float var5 = 268.0F;
            float var6 = (this.M - 16.0F) / 3.0F;
            int var7 = (int)((var1 - this.N) / (var6 + 8.0F));
            int var8 = (int)((var4 - var5) / 88.0F);
            if (!(var4 < var5) && var7 < 3 && var8 < w && !(var1 - this.N - var7 * (var6 + 8.0F) > var6) && !(var4 - var5 - var8 * 88.0F > 80.0F)) {
               int var9 = var8 * 3 + var7;
               CapeDesign[] var10 = CapeDesign.values();
               if (var9 >= var10.length) {
                  return false;
               } else {
                  if (this.m.selected() != var10[var9]) {
                     this.m.h.set(var10[var9].E);
                  }

                  UiAudioPolicy.play("SELECT");
                  return true;
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
      if (this.z) {
         this.j = this.j + (var1 - this.G) * 0.012F;
         this.G = var1;
         this.c = 4.0F;
      }
   }

   public void mouseUp() {
      this.z = false;
   }

   private static float j(char var0, float var1, long var2) {
      CapeModule.ensureSourceClassInitialized();
      float var4 = (float) (Math.PI * 2);
      var1 %= var4;
      return var1 > Math.PI ? var1 - var4 : (var1 < -Math.PI ? var1 + var4 : var1);
   }

   private static float H(int var0, int var1, short var2, short var3) {
      int var4 = var0 * 668265261;
      var4 ^= var4 >>> 15;
      var4 *= 739982445;
      var4 ^= var4 >>> 12;
      return (var4 & 65535) / 65535.0F;
   }
}
