package dev.purxiy.modules.halo;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
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
import net.minecraft.class_6367;
import net.minecraft.class_8685;
import org.joml.Matrix4f;

public final class HaloRenderer2 {
   private static final int q = -4222985;
   private static final float s = 36.0F;
   private static final float S = 232.0F;
   private static final float N = 12.0F;
   private static final float h = 86.0F;
   private static final float D = 7.0F;
   private static final float O = 34.0F;
   private static final int t = 3;
   private static final int W = 2;
   public static final float u = 493.0F;
   private static final HaloDesign[] Q = HaloDesign.values();
   private static final float m = (float)Math.toRadians(16.0);
   private static final float j = (float)Math.toRadians(7.0);
   private static final class_6367[] z = new class_6367[Q.length + 1];
   private final HaloModule F;
   private final Animator[] y = new Animator[Q.length + 1];
   private final HaloRenderer4.TypeR p = new HaloRenderer4.TypeR();
   private final HaloUpdater J = new HaloUpdater();
   private final HaloSupport5 K = new HaloSupport5();
   private final HaloBuilder A = new HaloBuilder();
   private final HaloSupport7 f = new HaloSupport7();
   private final HaloSupport3 P = new HaloSupport3();
   private final HaloBuilder.TypeS d = new HaloBuilder.TypeS();
   private final HaloBuilder.TypeC r = new HaloBuilder.TypeC();
   private final HaloSupport2.TypeK v = new HaloSupport2.TypeK();
   private final Matrix4f U = new Matrix4f();
   private final Matrix4f c = new Matrix4f();
   private final Matrix4f H = new Matrix4f();
   private final float[] Y = new float[7];
   private float e;
   private float I;
   private float a;
   private float X;
   private long M;
   private boolean b;
   private HaloDesign R;
   private static final String[] i = new String[8];

   public HaloRenderer2(HaloModule var1) {
      this.F = var1;

      for (int var2 = 0; var2 < this.y.length; var2++) {
         this.y[var2] = Animator.smooth(0.0F);
      }
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 39419541825570L;
      long var8 = var6 ^ 24605266752402L;
      long var10 = var6 ^ 135038263589439L;
      int var12 = (int)((var6 ^ 135038263589439L) >>> 32);
      int var13 = (int)((var6 ^ 135038263589439L) << 32 >>> 48);
      int var14 = (int)(var10 << 48 >>> 48);
      this.e = var2;
      this.I = var3;
      this.a = var4;
      this.b = var5 && !var1.masked();
      float var15 = Math.min(var1.dt, 0.1F);
      this.X += var15;
      Canvas var16 = var1.canvas;
      HaloDesign var17 = this.F.selected();
      HaloDesign var18 = this.R != null ? this.R : var17;
      if (this.R != null) {
         HaloRenderer7.tryOn(this.R);
      }

      int var19 = HaloRenderer7.colour();
      var16.text(Fonts.MEDIUM, "Wardrobe", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var16.text(Fonts.REGULAR, Q.length + " halos", var2 + var4, var3 + 12.0F, 10.5F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.TOP);
      this.f(var1, var16, var2, var3 + 36.0F, var4, var18, var19, var8, var15);
      float var20 = (var4 - 14.0F) / 3.0F;
      float var21 = var3 + 36.0F + 232.0F + 12.0F;
      HaloDesign var22 = null;

      for (int var23 = 0; var23 < Q.length; var23++) {
         HaloDesign var24 = Q[var23];
         float var25 = var2 + var23 % 3 * (var20 + 7.0F);
         float var26 = var21 + var23 / 3 * 93.0F;
         boolean var27 = this.b && var1.hover(this.y[var23], var25, var26, var20, 86.0F, TypeE.HAND);
         float var28 = this.y[var23].target(var27 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         if (var27) {
            var22 = var24;
         }

         boolean var29 = var24 == var17;
         var16.roundRect(
            var25, var26, var20, 86.0F, 10.0F, BlendMode.lerp(Theme.tint(-15725031), Theme.tint(-4222985), (var29 ? 0.1F : 0.025F) + var28 * 0.04F)
         );
         var16.roundRectOutline(
            var25, var26, var20, 86.0F, 10.0F, var29 ? 1.3F : 1.0F, BlendMode.withAlpha(Theme.tint(-4222985), var29 ? 0.72F : 0.12F + var28 * 0.2F)
         );
         float var30 = 54.0F;
         var16.glowCircle(
            var25 + var20 / 2.0F,
            var26 + var30 * 0.55F,
            var20 * 0.16F,
            var20 * 0.42F,
            BlendMode.withAlpha(var19, 0.05F + 0.08F * var28 + (var29 ? 0.04F : 0.0F))
         );
         var16.pushClip(var25 + 1.0F, var26 + 1.0F, var20 - 2.0F, var30 + 6.0F, 9.0F);
         this.c(var16, var23, var24, var12, (char)var13, var25 + 1.0F, var26 + 1.0F, var20 - 2.0F, (char)var14, var30 + 6.0F, var19, var28);
         var16.popClip();
         var16.textEllipsized(
            Fonts.SEMIBOLD,
            var24.U,
            var25 + var20 / 2.0F,
            var26 + 86.0F - 24.0F,
            11.0F,
            var20 - 10.0F,
            var29 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var28),
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
         var16.textEllipsized(
            Fonts.MEDIUM,
            var24.A.toUpperCase(Locale.ROOT),
            var25 + var20 / 2.0F,
            var26 + 86.0F - 11.0F,
            7.2F,
            var20 - 10.0F,
            BlendMode.lerp(Icons.TEXT_3, var24.g, 0.35F + 0.4F * Math.max(var28, var29 ? 1.0F : 0.0F)),
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
         if (var29) {
            var16.circle(var25 + var20 - 11.0F, var26 + 11.0F, 5.5F, Theme.tint(-4222985));
            var16.icon("check", var25 + var20 - 11.0F, var26 + 11.0F, 8.0F, Icons.BG_1);
         }
      }

      this.R = var22;
      float var31 = var3 + 493.0F - 17.0F;
      var16.circle(var2 + 3.0F, var31, 2.5F, var19);
      var16.textEllipsized(Fonts.REGULAR, var18.f, var2 + 14.0F, var31, 10.5F, var4 - 14.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private void f(DrawContext var1, Canvas var2, float var3, float var4, float var5, HaloDesign var6, int var7, long var8, float var10) {
      long var11 = 71225248678827L;
      long var13 = 39984501429024L;
      var2.roundRectGradient(var3, var4, var5, 232.0F, 12.0F, 90.0F, Theme.tint(-14870991), Theme.tint(-16053995));
      HaloDesign.ensureSourceClassInitialized();
      var2.roundRectOutline(var3, var4, var5, 232.0F, 12.0F, 1.0F, Theme.tint(633708021));
      var2.pushClip(var3 + 1.0F, var4 + 1.0F, var5 - 2.0F, 230.0F, 11.0F);
      float var15 = var3 + var5 / 2.0F;
      float var16 = 14.0F;
      float var17 = var4 + 232.0F - var16;
      float var18 = 4.495F;
      float var19 = ClientBuilder.yourHeight();
      var2.glowCircle(var15, var17 - 33.0F * var19 * var18, 8.0F * var18, 30.0F * var18, BlendMode.withAlpha(var7, 0.14F));

      for (int var20 = 0; var20 < 16; var20++) {
         float var21 = var3 + 14.0F + G(var20 * 3, var11) * (var5 - 28.0F);
         float var22 = var4 + 8.0F + G(var20 * 3 + 1, var11) * 180.95999F;
         long var23 = 59076468186032L;
         int var30 = var23 < 0L ? -1 : 1;
         float var26 = 0.5F + 0.5F * (float)Math.sin(this.X * (0.7F + G(var20 * 3 + 2, var11)) + var20);
         var2.circle(var21, var22, 0.6F + G(var20 * 7, var11) * 0.7F, BlendMode.withAlpha(-1, 0.08F + 0.25F * var26));
      }

      var2.push();
      var2.translate(var15, var17);
      var2.scale(1.0F, 0.24F);
      var2.softCircle(0.0F, 0.0F, var5 * 0.16F, var5 * 0.12F, -1341979384);
      var2.ring(0.0F, 0.0F, var5 * 0.26F, 0.8F, BlendMode.withAlpha(Theme.tint(-4222985), 0.2F));
      var2.pop();
      long var28 = 59076468186031L;
      int var28x = var28 < 0L ? -1 : 1;
      boolean var29 = this.b && var1.hover(this.y[this.y.length - 1], var3, var4, var5, 232.0F, TypeE.HAND);
      float var24 = this.y[this.y.length - 1].target(var29 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var25 = 0.32F + 0.14F * (float)Math.sin(this.X * 0.35F);
      HaloRenderer4.TypeR var30 = this.p;
      var30.F = var25;
      var30.P = this.X;
      var30.C = 0.18F * (float)Math.sin(this.X * 0.45F);
      var30.B = -0.06F + 0.05F * (float)Math.sin(this.X * 0.31F);
      var30.w = 6.0F;
      class_8685 var27 = ClientBuilder.skin();
      if (var27 != null) {
         HaloRenderer4.draw(var2, var27, var30, var3, var4, var5, 232.0F, var18, var16, 1.0F, var24 * 0.5F, this.X);
         this.j(var2, var27, var6, var13, var7, var3, var4, var5, 232.0F, var18, var16, var10);
      }

      var2.text(
         Fonts.MEDIUM,
         String.format(Locale.ROOT, "%02d / %s", var6.ordinal() + 1, Translations.tr(var6.U).toUpperCase(Locale.ROOT)),
         var3 + 14.0F,
         var4 + 17.0F,
         8.6F,
         Icons.TEXT_2,
         FontStyle.LEFT,
         FontWeight.TOP,
         0.06F
      );
      var2.text(
         Fonts.REGULAR,
         "Click and it shines",
         var15,
         var4 + 232.0F - 9.0F,
         9.0F,
         var24 > 0.5F ? Icons.TEXT_2 : Icons.TEXT_3,
         FontStyle.CENTER,
         FontWeight.MIDDLE
      );
      var2.popClip();
   }

   private void j(
      Canvas var1,
      class_8685 var2,
      HaloDesign var3,
      long var4,
      int var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12,
      float var13
   ) {
      long var14 = 127829221691378L;
      HaloDesign.ensureSourceClassInitialized();
      dev.purxiy.loader.fabric.client.ClientBuilder.TypeE var16 = ClientBuilder.layers(var2);
      boolean var17 = var16 != null && var16.fullModel();
      float var18 = ClientBuilder.yourHeight();
      float[] var19 = var17 ? var16.pivots()[0] : ClientBuilder.PIVOT[0];
      boolean var20 = var16 != null && var16.rigidHead();
      Matrix4f var21 = this.H.identity().translate(0.0F, 24.0F * var18, 0.0F).rotateY(this.p.F);
      long var22 = 39984501429023L;
      int var25 = var22 < 0L ? -1 : 1;
      var21.scale(var18, -var18, -var18).translate(var19[0], var19[1], var19[2]).rotateZYX(0.0F, var20 ? 0.0F : this.p.C, var20 ? 0.0F : this.p.B);
      ClientBuilder.build(this.H, 0);
      HaloSupport4.bounds(var16, this.Y);
      this.K.picture(this.H, this.Y);
      this.J.leanBack(-13.0F);
      this.J.update(var13, true, this.K, 0.0, 0.0, 0.0, this.F.a.floatValue(), this.F.i.enabled());
      this.r.H = var6;
      this.r.c = this.F.w.floatValue();
      this.J.place(this.K, var3, this.F.b.floatValue(), this.F.V.floatValue(), this.F.i.enabled(), this.d, this.r);
      this.A.build(this.f, var3, this.d, this.r);
      float var25x = 51.612904F / (2.0F * (float)Math.tan(m / 2.0F));
      float var26 = 22.691881F;
      this.U.setPerspective(m, var9 / 232.0F, Math.max(1.0F, var25x - 60.0F), var25x + 60.0F);
      float var27 = var26 + var25x * (float)Math.sin(j);
      float var28 = var25x * (float)Math.cos(j);
      this.c.setLookAt(0.0F, var27, var28, 0.0F, var26, 0.0F, 0.0F, 1.0F, 0.0F);
      this.s(var1, Q.length, var14, var7, var8, var9, 232.0F, var27, var28);
   }

   private void c(
      Canvas var1, int var2, HaloDesign var3, int var4, char var5, float var6, float var7, float var8, char var9, float var10, int var11, float var12
   ) {
      long var13 = 97904189690397L;
      long var15 = var13 ^ 49794092047855L;
      HaloBuilder.TypeC var17 = this.r;
      var17.H = var11;
      var17.c = this.F.w.floatValue();
      var17.u = 1.0F;
      var17.J = this.X + var2 * 1.7F;
      var17.T = 1.0F;
      var17.N = 0.0F;
      var17.v = 0.0F;
      HaloBuilder.TypeS var18 = this.d;
      var18.U = var18.y = 0.0F;
      var18.c = 0.6F * var12;
      var18.basis(0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F);
      var18.i = 1.0F;
      var18.a = this.X * 0.25F + var2;
      this.A.build(this.f, var3, var18, var17);
      float var19 = (float)Math.toRadians(30.0);
      float var20 = (float)Math.toRadians(var3 == HaloDesign.C ? 18.0 : 26.0);
      float var21 = 27.0F;
      this.U.setPerspective(var19, var8 / var10, 1.0F, 80.0F);
      float var22 = -0.8F + var21 * (float)Math.sin(var20);
      float var23 = var21 * (float)Math.cos(var20);
      this.c.setLookAt(0.0F, var22, var23, 0.0F, -0.8F, 0.0F, 0.0F, 1.0F, 0.0F);
      this.s(var1, var2, var15, var6, var7, var8, var10, var22, var23);
   }

   private void s(Canvas var1, int var2, long var3, float var5, float var6, float var7, float var8, float var9, float var10) {
      HaloDesign.ensureSourceClassInitialized();
      float var11 = (float)Math.hypot(var1.transform().m00(), var1.transform().m01());
      float var12 = Math.max(0.5F, var1.pixelScale() * var11);
      int var13 = Math.max(1, Math.round(var7 * var12));
      int var14 = Math.max(1, Math.round(var8 * var12));
      class_6367 var15 = z[var2];
      long var16 = 127829221691378L;
      int var21 = var16 < 0L ? -1 : 1;
      if (var15 == null) {
         var15 = new class_6367("Purxiy halo picture " + var2, var13, var14, false);
         z[var2] = var15;
      } else {
         label23: {
            if (var15.field_1482 == var13) {
               long var19 = 127829221691378L;
               int var20 = var19 < 0L ? -1 : 1;
               if (var15.field_1481 == var14) {
                  break label23;
               }
            }

            var15.method_1234(var13, var14);
         }
      }

      HaloSupport2.TypeK var21x = this.v;
      this.U.mul(this.c, var21x.D);
      var21x.D.invert(var21x.s);
      var21x.F = 0.0F;
      var21x.d = var9;
      var21x.p = var10;
      var21x.Q = this.d.U;
      var21x.E = this.d.c;
      var21x.I = this.d.y;
      var21x.a = var1.alpha();
      var21x.t = this.U.m11() * var14 * 0.5F;
      this.P.drawPreview(this.f, var21x, var15);
      var1.image(
         var15.method_71639(), RenderSystem.getSamplerCache().method_75294(FilterMode.LINEAR), true, var5, var6, var7, var8, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, -1
      );
   }

   private void P() {
      this.J.flourish();
      HaloRenderer7.chosen();
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      if (this.b && var3 == 0 && !(var1 < this.e) && !(var1 >= this.e + this.a)) {
         float var4 = var2 - this.I;
         if (var4 >= 36.0F && var4 < 268.0F) {
            this.P();
            UiAudioPolicy.play("CLICK");
            return true;
         } else {
            float var5 = 280.0F;
            float var6 = (this.a - 14.0F) / 3.0F;
            int var7 = (int)((var1 - this.e) / (var6 + 7.0F));
            int var8 = (int)((var4 - var5) / 93.0F);
            if (!(var4 < var5) && var7 < 3 && var8 < 2 && !(var1 - this.e - var7 * (var6 + 7.0F) > var6) && !(var4 - var5 - var8 * 93.0F > 86.0F)) {
               int var9 = var8 * 3 + var7;
               if (var9 >= Q.length) {
                  return false;
               } else {
                  if (this.F.selected() != Q[var9]) {
                     this.F.S.set(Q[var9].U);
                  }

                  this.P();
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

   private static float G(int var0, long var1) {
      int var3 = var0 * 668265261;
      var3 ^= var3 >>> 15;
      var3 *= 739982445;
      var3 ^= var3 >>> 12;
      return (var3 & 65535) / 65535.0F;
   }

   static void close() {
      for (int var0 = 0; var0 < z.length; var0++) {
         if (z[var0] != null) {
            z[var0].method_1238();
            z[var0] = null;
         }
      }
   }
}
