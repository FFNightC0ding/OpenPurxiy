package dev.purxiy.modules.place;

import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.DrawContext.Cursor;

public final class PlaceAnimationScreen {
   public static final float g = 214.0F;
   private static final float L = 51.0F;
   private static final float b = 8.0F;
   private static int K;
   private static int[][] G;
   private static final float I = 0.45F;
   private static final float Q = 1.1F;
   private final PlaceAnimationModule C;
   private final Object A = new Object();
   private final PlaceMotion.PlacePose T = new PlaceMotion.PlacePose();
   private final float[] d = new float[8];
   private final float[] k = new float[8];
   private float W;
   private float u;
   private float w;
   private float z;
   private boolean R;
   private static String[] a;
   private static String[] c;

   public PlaceAnimationScreen(PlaceAnimationModule var1) {
      this.C = var1;
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 55825075141245L;
      long var8 = (var6 ^ 95651405642267L) >>> 16;
      int var10 = (int)((var6 ^ 95651405642267L) << 48 >>> 48);
      long var11 = var6 ^ 60487680247896L;
      this.u = var2;
      this.w = var3;
      this.z = var4;
      this.R = var5;
      Canvas var13 = var1.canvas;
      var13.text(Fonts.MEDIUM, "Build, and every block grows into place", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var13.text(Fonts.REGULAR, "Each one springs to its size as you place it", var2, var3 + 30.0F, 11.5F, Icons.TEXT_2);
      float var14 = var3 + 51.0F;
      float var15 = 155.0F;
      boolean var16 = var5 && var1.hover(this.A, var2, var14, var4, var15, Cursor.HAND);
      this.W = this.W + var1.dt;
      float var17 = this.k(var11);
      if (this.W > var17) {
         this.W -= var17;
      }

      var13.roundRectGradient(var2, var14, var4, var15, 12.0F, 90.0F, Theme.tint(-15396828), Theme.tint(-16317171));
      var13.pushClip(var2, var14, var4, var15, 12.0F);
      this.p(var13, var2 + var4 * 0.5F, var14 + var15 * 0.6F, var4, var8, (char)var10, var15);
      var13.popClip();
      var13.roundRectOutline(var2, var14, var4, var15, 12.0F, 1.0F, var16 ? Theme.tint(1086692853) : Theme.tint(633708021));
      var13.text(Fonts.MEDIUM, "CLICK TO BUILD AGAIN", var2 + 14.0F, var14 + 12.0F, 8.5F, var16 ? Icons.TEXT_2 : Icons.TEXT_3);
   }

   private float u() {
      return Math.clamp(this.C.l.floatValue() * 0.55F, 0.16F, 0.45F);
   }

   private float w(int var1) {
      return 0.45F + var1 * this.u();
   }

   private float a() {
      return this.w(G.length - 1) + this.C.l.floatValue() + 1.1F;
   }

   private float K(int var1) {
      return this.a() + (G.length - 1 - var1) * this.u() * 0.7F;
   }

   private float k(long var1) {
      PlaceMotion.ensureSourceClassInitialized();
      return this.C.L.enabled() ? this.K(0) + this.C.l.floatValue() * 0.8F + 0.5F : this.a() + 0.45F;
   }

   private void p(Canvas var1, float var2, float var3, float var4, long var5, char var7, float var8) {
      long var9 = 110206139482214L;
      long var11 = var9 ^ 29805623055183L;
      long var13 = var9 ^ 119587008946923L;
      PlaceAnimationLoader var15 = PlaceAnimationLoader.resolve((String)this.C.D.get(), this.C.q.argb());
      float var16 = Math.clamp(var4 / 15.0F, 18.0F, 30.0F);
      PlaceMotion.ensureSourceClassInitialized();
      float var17 = var16 * 0.5F;
      float var18 = var16 * 1.12F;
      float var19 = this.C.L.enabled() ? 1.0F : 1.0F - Math.clamp((this.W - this.a()) / 0.35F, 0.0F, 1.0F);
      this.B(var1, var2, var3, var4, var13, 155.0F, var16, var17);

      for (int var20 = -6; var20 <= 6; var20++) {
         for (int var21 = 0; var21 < 3; var21++) {
            int var22 = 49254;

            for (int var23 = 0; var23 < G.length; var23++) {
               long var24 = 1681612235L;
               int var26 = var24 < 0L ? -1 : 1;
               int[] var26x = G[var23];
               long var27 = 1681612235L;
               int var29 = var27 < 0L ? -1 : 1;
               var29 = var26x[0];
               var22 = var26x[2];
               if (var29 + var22 == var20 && var26x[1] == var21) {
                  this.x(var1, var23, var2, var11, var3, var16, var17, var18, var15, var19);
               }
            }

            long var31 = 1681612235L;
            int var25 = var31 < 0L ? -1 : 1;
         }
      }
   }

   private void B(Canvas var1, float var2, float var3, float var4, long var5, float var7, float var8, float var9) {
      PlaceMotion.ensureSourceClassInitialized();

      for (int var10 = -3; var10 <= 3; var10++) {
         for (int var11 = -3; var11 <= 3; var11++) {
            float var12 = var2 + (var11 - var10) * var8;
            float var13 = var3 + (var11 + var10) * var9;
            float var14 = (var12 - var2) / (var4 * 0.5F);
            float var15 = (var13 - var3) / 85.25F;
            float var16 = Math.clamp(1.2F - (float)Math.sqrt(var14 * var14 + var15 * var15), 0.0F, 1.0F);
            float var17 = (var11 * 73 + var10 * 151 & 7) / 7.0F;
            int var18 = BlendMode.lerp(Theme.tint(-16054252), BlendMode.lerp(Theme.tint(-14542281), Theme.tint(-14016190), var17), var16);
            long var19 = 9864885297804L;
            int var22 = var19 < 0L ? -1 : 1;
            var22 = BlendMode.lerp(var18, Theme.tint(-12240791), 0.16F * var16);
            var1.triangle(var12, var13 - var9, var12 + var8, var13, var12, var13 + var9, var22, var18, var18);
            var1.triangle(var12, var13 - var9, var12, var13 + var9, var12 - var8, var13, var22, var18, var18);
            int var23 = BlendMode.withAlpha(-1, 0.06F * var16);
            var1.line(var12 - var8, var13, var12, var13 - var9, 0.8F, var23);
            var1.line(var12, var13 - var9, var12 + var8, var13, 0.8F, var23);
         }

         long var24 = 9864885297804L;
         int var26 = var24 < 0L ? -1 : 1;
      }

      long var25 = 9864885297804L;
      int var25x = var25 < 0L ? -1 : 1;
   }

   private void x(Canvas var1, int var2, float var3, long var4, float var6, float var7, float var8, float var9, PlaceAnimationLoader var10, float var11) {
      long var12 = 93119950393650L;
      long var14 = 15729479115086L;
      long var16 = 8192853073377L;
      PlaceMotion.ensureSourceClassInitialized();
      float var18 = this.C.l.floatValue();
      float var19 = this.W - this.w(var2);
      if (!(var19 < 0.0F) && !(var11 <= 0.0F)) {
         boolean var20 = this.C.L.enabled() && this.W >= this.K(var2);
         PlaceMotion.Style var21 = this.C.motionStyle();
         if (var20) {
            float var22 = (this.W - this.K(var2)) / (var18 * 0.8F);
            long var23 = 139779515142952L;
            int var50 = var23 < 0L ? -1 : 1;
            long var26 = 139779515142952L;
            int var51 = var26 < 0L ? -1 : 1;
            if (var22 >= 1.0F) {
               return;
            }

            PlaceMotion.broken(var22, this.T);
            long var28 = 139779515142952L;
            int var52 = var28 < 0L ? -1 : 1;
         } else {
            PlaceMotion.placed(var21, var19 / var18, this.C.J.floatValue(), this.T);
         }

         long var49 = 139779515142953L;
         int var25 = var49 < 0L ? -1 : 1;
         boolean var25x = !var20 && var21.fromFace();
         int[] var50 = G[var2];
         float var27 = var25x ? 0.0F : 0.5F;
         float var51 = (float)Math.cos(this.T.h);
         float var29 = (float)Math.sin(this.T.h);

         for (int var30 = 0; var30 < 8; var30++) {
            float var31 = ((var30 & 1) == 0 ? 0.0F : 1.0F) - 0.5F;
            float var32 = ((var30 & 2) == 0 ? 0.0F : 1.0F) - var27;
            float var33 = ((var30 & 4) == 0 ? 0.0F : 1.0F) - 0.5F;
            var31 *= this.T.t;
            var33 *= this.T.t;
            var32 *= this.T.Z;
            float var34 = var31 * var51 + var33 * var29;
            float var35 = -var31 * var29 + var33 * var51;
            float var36 = var50[0] + 0.5F + var34;
            float var37 = var50[1] + var27 + var32 + this.T.u;
            float var38 = var50[2] + 0.5F + var35;
            this.d[var30] = var3 + (var36 - var38) * var7;
            this.k[var30] = var6 + (var36 + var38 - 1.0F) * var8 - var37 * var9;
         }

         long var52 = 139779515142952L;
         int var581 = var52 < 0L ? -1 : 1;
         float var57 = Math.max(this.T.t, 0.0F);
         float var58x = this.C.v.floatValue() * this.T.W;
         int var59 = BlendMode.lerp(C(var10.V, var12), -1, 0.45F);
         float var60 = var3 + (var50[0] - var50[2]) * var7;
         float var61 = var6 + (var50[0] + var50[2]) * var8 - var50[1] * var9;
         var1.softCircle(var60, var61, var7 * 0.75F * var57, var7 * 0.5F, BlendMode.withAlpha(-16777216, 0.35F * var11 * Math.min(var57, 1.0F)));
         long var62 = 139779515142953L;
         int var41 = var62 < 0L ? -1 : 1;
         float var41x;
         int var55 = (var41x = var58x - 0.01F) == 0.0F ? 0 : (var41x < 0.0F ? -1 : 1);
         long var42 = 139779515142952L;
         int var42x = var42 < 0L ? -1 : 1;
         if (var55 > 0) {
            float var44 = 0.0F;
            float var45 = 0.0F;

            for (int var46 = 0; var46 < 8; var46++) {
               var44 += this.d[var46] / 8.0F;
               long var47 = 139779515142953L;
               int var47x = var47 < 0L ? -1 : 1;
               var45 += this.k[var46] / 8.0F;
            }

            long var65 = 139779515142953L;
            int var46 = var65 < 0L ? -1 : 1;
            var1.softCircle(var44, var45, var7 * (0.9F + 0.6F * var57), var7 * 0.9F, BlendMode.withAlpha(C(var10.V, var12), 0.42F * var58x * var11));
         }

         float var63x = (var2 * 37 & 7) / 7.0F;
         int var64 = BlendMode.lerp(Theme.tint(-11912328), Theme.tint(-10859892), var63x);
         int var66 = this.l(BlendMode.lerp(var64, Theme.tint(-8492108), 0.35F), var59, var58x, var14, var11);
         this.I(var16, var1, 1, 5, 3, 7, var51, -var29, var64, var59, var58x, var11);
         this.I(var16, var1, 4, 5, 6, 7, var29, var51, var64, var59, var58x, var11);
         this.I(var16, var1, 0, 4, 2, 6, -var51, var29, var64, var59, var58x, var11);
         this.I(var16, var1, 0, 1, 2, 3, -var29, -var51, var64, var59, var58x, var11);
         int var67 = BlendMode.lerp(var66, -1, 0.08F * var11);
         var1.triangle(this.d[2], this.k[2], this.d[3], this.k[3], this.d[7], this.k[7], var67, var66, var66);
         var1.triangle(this.d[2], this.k[2], this.d[7], this.k[7], this.d[6], this.k[6], var67, var66, var66);
      }
   }

   private void I(long var1, Canvas var3, int var4, int var5, int var6, int var7, float var8, float var9, int var10, int var11, float var12, float var13) {
      long var14 = 15729479115086L;
      PlaceMotion.ensureSourceClassInitialized();
      float var16 = var8 + var9;
      long var17 = 8192853073377L;
      int var20 = var17 < 0L ? -1 : 1;
      if (!(var16 <= 0.02F)) {
         float var20x = 0.5F + 0.22F * var8 - 0.04F * var9;
         int var21 = BlendMode.lerp(Theme.tint(-15922664), var10, Math.clamp(var20x, 0.0F, 1.0F) * 1.25F);
         int var22 = this.l(var21, var11, var12, var14, var13);
         int var23 = this.l(BlendMode.lerp(var21, -1, 0.06F), var11, var12, var14, var13);
         var3.triangle(this.d[var4], this.k[var4], this.d[var5], this.k[var5], this.d[var7], this.k[var7], var22, var22, var23);
         var3.triangle(this.d[var4], this.k[var4], this.d[var7], this.k[var7], this.d[var6], this.k[var6], var22, var23, var23);
      }
   }

   private int l(int var1, int var2, float var3, long var4, float var6) {
      int var7 = BlendMode.lerp(var1, var2, Math.clamp(var3 * 0.8F, 0.0F, 1.0F));
      return BlendMode.lerp(Theme.tint(-16317171), var7, var6);
   }

   private static int C(int var0, long var1) {
      float var3 = (var0 >> 16 & 0xFF) / 255.0F;
      float var4 = (var0 >> 8 & 0xFF) / 255.0F;
      float var5 = (var0 & 0xFF) / 255.0F;
      float var6 = 0.299F * var3 + 0.587F * var4 + 0.114F * var5;
      return BlendMode.argb(1.0F, l(var6 + (var3 - var6) * 1.45F), l(var6 + (var4 - var6) * 1.45F), l(var6 + (var5 - var6) * 1.45F));
   }

   private static float l(float var0) {
      return Math.clamp(var0, 0.0F, 1.0F);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 37033583715367L;
      if (this.R && var3 == 0 && !(var1 < this.u) && !(var1 >= this.u + this.z) && !(var2 < this.w + 51.0F) && !(var2 >= this.w + 214.0F - 8.0F)) {
         this.W = 0.0F;
         UiAudioPolicy.play("CLICK");
         return true;
      } else {
         return false;
      }
   }
}
