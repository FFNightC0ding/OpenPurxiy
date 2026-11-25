package dev.purxiy.modules.wings;

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
import dev.purxiy.render.font.TrueTypeFont;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import dev.purxiy.ui.DrawContext.Cursor;
import java.util.Locale;
import net.minecraft.class_8685;

public final class WingsEditorScreen {
   private static final int n = -4222985;
   private static final float t = 36.0F;
   private static final float q = 232.0F;
   private static final float F = 12.0F;
   private static final float M = 92.0F;
   private static final float x = 7.0F;
   private static final float J = 34.0F;
   public static final float W = 406.0F;
   private static final WingDesign[] O = WingDesign.values();
   private static final float h = 32.0F;
   private final WingsModule c;
   private final Animator[] A = new Animator[O.length + 1];
   private final WingsRenderPass.WingVertexData w = new WingsRenderPass.WingVertexData();
   private final WingsGeometry z = new WingsGeometry();
   private final WingsGeometry N = new WingsGeometry();
   private final WingsPose I = new WingsPose();
   private final WingsPose Z = new WingsPose();
   private float Y;
   private float s;
   private float P;
   private float d;
   private float i;
   private float C;
   private boolean e;
   private WingDesign U;
   private static final String[] b = new String[7];

   public WingsEditorScreen(WingsModule var1) {
      this.c = var1;

      for (int var2 = 0; var2 < this.A.length; var2++) {
         this.A[var2] = Animator.smooth(0.0F);
      }
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 94825508564368L;
      long var8 = var6 ^ 83749574378349L;
      this.Y = var2;
      this.s = var3;
      this.P = var4;
      this.e = var5 && !var1.masked();
      float var10 = Math.min(var1.dt, 0.1F);
      this.d += var10;
      this.C = Math.max(0.0F, this.C - var10 * 0.9F);
      this.i += var10 * 2.3F * (float) Math.PI * 2.0F;
      Canvas var11 = var1.canvas;
      WingDesign var12 = this.c.selected();
      WingDesign var13 = this.U != null ? this.U : var12;
      if (this.U != null) {
         WingsState.tryOn(this.U);
      }

      var11.text(Fonts.MEDIUM, "Wardrobe", var2, var3 + 10.0F, 13.5F, Icons.TEXT);
      var11.text(Fonts.REGULAR, O.length + " pairs", var2 + var4, var3 + 12.0F, 10.5F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.TOP);
      this.s(var1, var8, var11, var2, var3 + 36.0F, var4, var13);
      float var14 = (var4 - (O.length - 1) * 7.0F) / O.length;
      float var15 = var3 + 36.0F + 232.0F + 12.0F;
      WingDesign var16 = null;
      int var17 = this.c.p.get();
      float var18 = ((Double)this.c.k.get()).floatValue();

      for (int var19 = 0; var19 < O.length; var19++) {
         WingDesign var20 = O[var19];
         float var21 = var2 + var19 * (var14 + 7.0F);
         boolean var22 = this.e && var1.hover(this.A[var19], var21, var15, var14, 92.0F, Cursor.HAND);
         float var23 = this.A[var19].target(var22 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         if (var22) {
            var16 = var20;
         }

         boolean var24 = var20 == var12;
         var11.roundRect(
            var21, var15, var14, 92.0F, 10.0F, BlendMode.lerp(Theme.tint(-15725031), Theme.tint(-4222985), (var24 ? 0.1F : 0.025F) + var23 * 0.04F)
         );
         var11.roundRectOutline(
            var21, var15, var14, 92.0F, 10.0F, var24 ? 1.3F : 1.0F, BlendMode.withAlpha(Theme.tint(-4222985), var24 ? 0.72F : 0.12F + var23 * 0.2F)
         );
         float var25 = 52.0F;
         float var26 = var21 + var14 / 2.0F;
         float var27 = var15 + 27.0F - var23 * 1.5F;
         var11.glowCircle(
            var26, var15 + var25 * 0.5F + 4.0F, var14 * 0.28F, var14 * 0.55F, BlendMode.withAlpha(var20.H, 0.08F + 0.12F * var23 + (var24 ? 0.06F : 0.0F))
         );
         var11.pushClip(var21 + 1.0F, var15 + 1.0F, var14 - 2.0F, var25 + 8.0F, 9.0F);
         this.q(var19, var23);
         float var28 = Math.min(var14 / 66.0F, var25 / 34.0F) * (var20.w ? 1.1F : 1.0F);
         this.N.draw(var11, var20, var20.h ? var17 : var20.H, var18, this.d + var19 * 1.7F, this.Z, var26, var27, var28, 0.0F, 0.0F);
         var11.popClip();
         var11.textEllipsized(
            Fonts.SEMIBOLD,
            var20.x,
            var26,
            var15 + 92.0F - 26.0F,
            11.0F,
            var14 - 10.0F,
            var24 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var23),
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
         var11.textEllipsized(
            Fonts.MEDIUM,
            var20.z.toUpperCase(Locale.ROOT),
            var26,
            var15 + 92.0F - 12.0F,
            7.2F,
            var14 - 10.0F,
            BlendMode.lerp(Icons.TEXT_3, var20.H, 0.35F + 0.4F * Math.max(var23, var24 ? 1.0F : 0.0F)),
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
         if (var24) {
            var11.circle(var21 + var14 - 11.0F, var15 + 11.0F, 5.5F, Theme.tint(-4222985));
            var11.icon("check", var21 + var14 - 11.0F, var15 + 11.0F, 8.0F, Icons.BG_1);
         }
      }

      this.U = var16;
      float var29 = var3 + 406.0F - 17.0F;
      var11.circle(var2 + 3.0F, var29, 2.5F, var13.H);
      var11.textEllipsized(Fonts.REGULAR, var13.Q, var2 + 14.0F, var29, 10.5F, var4 - 14.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private void q(int var1, float var2) {
      float var3 = this.d + var1 * 1.7F;
      WingsPose var4 = this.Z;
      var4.X = 1.0F;
      var4.v = 0.18F + 0.07F * (float)Math.sin(var3 * 1.25F) + 0.12F * var2;
      var4.b = 0.22F;
      var4.p = 0.08F;
      var4.V = 3.2F;
      var4.i = 0.25F * (float)Math.cos(var3 * 1.25F);
      var4.K = 0.0F;
      var4.y = 0.0F;
      var4.G = 0.0F;
      var4.Q = 1.0F;
   }

   private void s(DrawContext var1, long var2, Canvas var4, float var5, float var6, float var7, WingDesign var8) {
      long var9 = 81432552490981L;
      var4.roundRectGradient(var5, var6, var7, 232.0F, 12.0F, 90.0F, Theme.tint(-14870991), Theme.tint(-16053995));
      var4.roundRectOutline(var5, var6, var7, 232.0F, 12.0F, 1.0F, Theme.tint(633708021));
      WingsState.ensureSourceClassInitialized();
      var4.pushClip(var5 + 1.0F, var6 + 1.0F, var7 - 2.0F, 230.0F, 11.0F);
      float var11 = var5 + var7 / 2.0F;
      float var12 = 16.0F;
      float var13 = var6 + 232.0F - var12;
      float var14 = 4.3500004F;
      float var15 = ClientBuilder.yourHeight();
      var4.glowCircle(var11, var13 - 32.0F * var14 * 0.62F, 32.0F * var14 * 0.3F, 32.0F * var14 * 0.95F, BlendMode.withAlpha(var8.H, 0.16F));

      for (int var16 = 0; var16 < 16; var16++) {
         float var17 = var5 + 14.0F + d(var16 * 3, var9) * (var7 - 28.0F);
         long var18 = 28678559985404L;
         int var36 = var18 < 0L ? -1 : 1;
         float var21 = var6 + 8.0F + d(var16 * 3 + 1, var9) * 180.95999F;
         float var22 = 0.5F + 0.5F * (float)Math.sin(this.d * (0.7F + d(var16 * 3 + 2, var9)) + var16);
         var4.circle(var17, var21, 0.6F + d(var16 * 7, var9) * 0.7F, BlendMode.withAlpha(-1, 0.08F + 0.25F * var22));
      }

      var4.push();
      var4.translate(var11, var13);
      var4.scale(1.0F, 0.24F);
      var4.softCircle(0.0F, 0.0F, var7 * 0.16F, var7 * 0.12F, -1341979384);
      var4.ring(0.0F, 0.0F, var7 * 0.26F, 0.8F, BlendMode.withAlpha(Theme.tint(-4222985), 0.2F));
      var4.pop();
      long var35 = 28678559985404L;
      int var34 = var35 < 0L ? -1 : 1;
      boolean var19 = this.e && var1.hover(this.A[this.A.length - 1], var5, var6, var7, 232.0F, Cursor.HAND);
      float var20 = this.A[this.A.length - 1].target(var19 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
      float var36 = 0.12F * (float)Math.sin(this.d * 0.35F);
      this.w.a = (float) Math.PI + var36;
      this.w.L = this.d;
      this.w.R = 0.15F * (float)Math.sin(this.d * 0.45F);
      this.w.d = 0.05F;
      this.w.N = 8.0F;
      class_8685 var37 = ClientBuilder.skin();
      if (var37 != null) {
         WingsRenderPass.draw(var4, var37, this.w, var5, var6, var7, 232.0F, var14, var12, 1.0F, var20 * 0.5F, this.d);
      }

      float var24 = var13 - 24.0F * var15 * var14;
      float var25 = (float)Math.sin(this.i + 0.42F * Math.sin(this.i));
      WingsPose var26 = this.I;
      var26.X = 1.0F;
      var26.v = 0.2F + 0.05F * (float)Math.sin(this.d * 1.15F) + 0.08F * var20 + this.C * 0.55F * var25;
      var26.b = 0.24F;
      var26.p = 0.1F;
      var26.V = 3.3F;
      var26.i = this.C * 0.55F * (float)Math.cos(this.i) + 0.15F * (float)Math.cos(this.d * 1.15F);
      var26.K = this.C * 0.4F;
      var26.y = 0.0F;
      var26.G = 0.0F;
      var26.Q = this.c.size();
      long var27 = 28678559985405L;
      int var27x = var27 < 0L ? -1 : 1;
      this.z
         .draw(
            var4,
            var8,
            var8.h ? this.c.p.get() : var8.H,
            ((Double)this.c.k.get()).floatValue(),
            this.d,
            var26,
            var11,
            var24,
            var14 * var15,
            var36,
            (float)Math.toRadians(7.0)
         );
      TrueTypeFont var30x = Fonts.MEDIUM;
      String var31 = String.format(Locale.ROOT, "%02d / %s", var8.ordinal() + 1, Translations.tr(var8.x).toUpperCase(Locale.ROOT));
      float var32 = var5 + 14.0F;
      long var33 = 28678559985404L;
      int var32x = var33 < 0L ? -1 : 1;
      var4.text(var30x, var31, var32, var6 + 17.0F, 8.6F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.TOP, 0.06F);
      var4.text(
         Fonts.REGULAR,
         "Click and they beat",
         var11,
         var6 + 232.0F - 9.0F,
         9.0F,
         var20 > 0.5F ? Icons.TEXT_2 : Icons.TEXT_3,
         FontStyle.CENTER,
         FontWeight.MIDDLE
      );
      var4.popClip();
   }

   private void j() {
      this.C = 1.0F;
      this.i = (float) (-Math.PI / 2);
      WingsState.rig().act(WingsUpdater.WingPhase.T, 1.3F);
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      if (this.e && var3 == 0 && !(var1 < this.Y) && !(var1 >= this.Y + this.P)) {
         float var4 = var2 - this.s;
         if (var4 >= 36.0F && var4 < 268.0F) {
            this.j();
            UiAudioPolicy.play("CLICK");
            return true;
         } else {
            float var5 = 280.0F;
            float var6 = (this.P - (O.length - 1) * 7.0F) / O.length;
            int var7 = (int)((var1 - this.Y) / (var6 + 7.0F));
            if (!(var4 < var5) && !(var4 >= var5 + 92.0F) && var7 < O.length && !(var1 - this.Y - var7 * (var6 + 7.0F) > var6)) {
               if (this.c.selected() != O[var7]) {
                  this.c.n.set(O[var7].x);
               }

               this.j();
               UiAudioPolicy.play("SELECT");
               return true;
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   private static float d(int var0, long var1) {
      int var3 = var0 * 668265261;
      var3 ^= var3 >>> 15;
      var3 *= 739982445;
      var3 ^= var3 >>> 12;
      return (var3 & 65535) / 65535.0F;
   }
}
