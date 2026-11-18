// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.font.TrueTypeFont;
import dev.purxiy.render.texture.TextureLoader;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.KeyLabel;
import dev.purxiy.ui.Theme;
import java.util.List;

public final class MountRenderer3 {
   private static final float y = 272.0F;
   private static final float R = 284.0F;
   private static final float b = 72.0F;
   private static final float x = 8.0F;
   private static final int v = 3;
   private static final int z = 3;
   private static final float t = 538.0F;
   private static final float e = 554.0F;
   private static final float P = 16.0F;
   public static final float N = 616.0F;
   private static final int s = -4222985;
   private final MountModule O;
   private final Animator[] m = new Animator[20];
   private final List<String> u = MountFactory.names();
   private final TextureLoader[] Q = new TextureLoader[9];
   private final boolean[] l = new boolean[9];
   private float T;
   private float V;
   private float S;
   private float L = 0.72F;
   private float o;
   private float F;
   private float D;
   private float Y;
   private boolean U;
   private boolean g;
   private boolean K;
   private int G = -1;





   public MountRenderer3(MountModule var1) {
      this.O = var1;

      for (int var2 = 0; var2 < this.m.length; var2++) {
         this.m[var2] = Animator.smooth(0.0F);
      }
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 90833475675751L;
      long var8 = var6 ^ 137929683647190L;
      long var10 = var6 ^ 95579919840305L;
      long var12 = var6 ^ 92779628714400L;
      this.T = var2;
      this.V = var3;
      this.S = var4;
      this.U = var5 && !var1.masked();
      float var14 = Math.min(var1.dt, 0.1F);
      this.Y += var14;
      if (this.K) {
         this.D += var14 * 7.0F;
      }

      this.o = Math.max(0.0F, this.o - var1.dt);
      if (!this.g && this.o == 0.0F) {
         this.L += var14 * 0.14F;
      }

      Canvas var15 = var1.canvas;
      MountFactory.Data7 var16 = MountFactory.get(this.O.u.get());
      int var17 = this.O.J.argb();
      List var18 = var16.paints();
      if (this.G >= 0 && this.G < var18.size()) {
         var17 = ((MountFactory.Data4)var18.get(this.G)).color();
      }

      this.b(var12, var1, var15, var16, var17);
      this.z(var1, var8, var15, var16);
      this.R(var10, var1, var15, var16);
   }

   private void b(long var1, DrawContext var3, Canvas var4, MountFactory.Data7 var5, int var6) {
      long var7 = 52776542669769L;
      long var9 = 40473848850954L;
      short var11 = 9423;
      char var12 = '赢';
      int var13 = (int)(var9 << 48 >>> 48);
      long var14 = 136523182796023L;
      var9 = 25518192908184L;
      byte var16 = 0;
      int var17 = 1521002;
      int var18 = (int)(var9 << 40 >>> 40);
      MountFactory.Data7.ensureSourceClassInitialized();
      float var19 = this.T;
      float var20 = this.V;
      float var21 = this.S;
      float var22 = 272.0F;
      float var23 = var19 + var21 / 2.0F;
      float var24 = var20 + var22 * 0.79F;
      int var25 = this.u.indexOf(var5.name());
      var4.roundRectGradient(var19, var20, var21, var22, 16.0F, 90.0F, Theme.tint(-14805452), Theme.tint(-16316915));
      var4.pushClip(var19 + 1.0F, var20 + 1.0F, var21 - 2.0F, var22 - 2.0F, 15.0F);
      int var26 = BlendMode.withAlpha(Theme.tint(-1647873), 0.0F);

      for (int var27 = 0; var27 < 4; var27++) {
         int var28 = BlendMode.withAlpha(Theme.tint(-1647873), 0.016F);
         float var29 = var21 * (0.035F + var27 * 0.012F);
         float var30 = var21 * (0.24F + var27 * 0.045F);
         var4.triangle(var23 - var29, var20, var23 + var29, var20, var23 + var30, var24, var28, var28, var26);
         var4.triangle(var23 - var29, var20, var23 + var30, var24, var23 - var30, var24, var28, var26, var26);
      }

      var4.glowCircle(var23, var20 - 6.0F, var21 * 0.05F, 50.0F, BlendMode.withAlpha(-1, 0.07F));
      var4.glowCircle(var23, var20 + var22 * 0.44F, var21 * 0.2F, 170.0F, BlendMode.withAlpha(Theme.tint(-4222985), 0.09F));
      var4.roundRectGradient(var19, var24 - 34.0F, var21, var22 - (var24 - 34.0F - var20), 0.0F, 90.0F, 0, 2046820352);
      long var56 = 7684262920135L;
      if (7684262920135L == 0L) {
         boolean var10000 = false;
      } else {
         int var60 = var56 < 0L ? -1 : 1;
      }

      var4.text(
         Fonts.MEDIUM,
         "PURXIY GARAGE  ·  " + (var25 + 1) + " / " + this.u.size(),
         var19 + 20.0F,
         var20 + 22.0F,
         8.4F,
         Icons.TEXT_3,
         FontStyle.LEFT,
         FontWeight.MIDDLE,
         0.1F
      );
      var4.text(Fonts.SEMIBOLD, var5.name(), var19 + 19.0F, var20 + 48.0F, 24.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      var4.text(Fonts.REGULAR, var5.line(), var19 + 20.0F, var20 + 72.0F, 11.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      float var57 = Math.min(var21 * 0.36F, 196.0F);
      this.Z(var4, var23, var24, var57, var7);
      float var31 = var5.hi().z - var5.lo().z;
      float var32 = Math.min(var21 * 0.72F / var31, var22 * 0.62F / Math.max(var5.hi().y, 1.2F));
      float var33 = var5.hi().y * 0.4F;
      float var34 = var24 - var33 * 0.97F * var32;
      boolean var35 = MountRenderer.ready(var5);
      long var36 = 7684262920135L;
      if (7684262920135L == 0L) {
         boolean var61 = false;
      } else {
         int var62 = var36 < 0L ? -1 : 1;
      }

      if (var35) {
         MountRenderer.draw(var4, var5, var6, var23, var34, var32, this.L, var33, var21 - 8.0F, var22 - 8.0F, this.D, 0.0F);
         long var39 = 7684262920134L;
         if (7684262920134L == 0L) {
            boolean var63 = false;
         } else {
            int var64 = var39 < 0L ? -1 : 1;
         }
      } else {
         float var58 = this.Y * 5.0F;
         var4.arc(var23, var24 - 56.0F, 15.0F, 2.4F, (float)Math.toDegrees(var58), 110.0F, BlendMode.withAlpha(Theme.tint(-4222985), 0.9F));
      }

      long var59 = 7684262920135L;
      if (7684262920135L == 0L) {
         boolean var65 = false;
      } else {
         int var66 = var59 < 0L ? -1 : 1;
      }

      boolean var42 = this.U && var3.hover(this.m[0], var19 + 5.0F, var20 + 92.0F, var21 - 10.0F, var22 - 130.0F, DrawContext.TypeE.HAND);
      float var43 = !var42 && !this.g ? 0.75F : 1.0F;
      var4.icon("rotate-3d", var19 + 26.0F, var20 + var22 - 20.0F, 11.0F, BlendMode.withAlpha(Icons.TEXT_3, var43));
      long var44 = 7684262920134L;
      if (7684262920134L == 0L) {
         boolean var67 = false;
      } else {
         int var68 = var44 < 0L ? -1 : 1;
      }

      var4.text(
         Fonts.MEDIUM,
         this.g ? "Turning" : "Drag to turn",
         var19 + 38.0F,
         var20 + var22 - 20.0F,
         9.5F,
         BlendMode.withAlpha(!var42 && !this.g ? Icons.TEXT_3 : Icons.TEXT_2, 1.0F),
         FontStyle.LEFT,
         FontWeight.MIDDLE
      );
      long var47 = 7684262920135L;
      if (7684262920135L == 0L) {
         boolean var69 = false;
      } else {
         int var70 = var47 < 0L ? -1 : 1;
      }

      if (!var5.credit().isEmpty()) {
         var4.textEllipsized(
            Fonts.REGULAR,
            "Model: " + var5.credit(),
            var19 + 20.0F,
            var20 + var22 - 40.0F,
            7.6F,
            var21 - 40.0F,
            Icons.TEXT_3,
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
      }

      this.A(var4, var19 + var21 - 18.0F, var14, var20 + var22 - 20.0F);
      var4.popClip();
      var4.roundRectOutlineGradient(
         var19, var20, var21, var22, 16.0F, 1.0F, 90.0F, BlendMode.withAlpha(Theme.tint(-4222985), 0.3F), BlendMode.withAlpha(Theme.tint(-4222985), 0.07F)
      );
      float var49 = var20 + 14.0F;
      float var50 = this.N((byte)var16, var3, 1, var19 + var21 - 94.0F, var49, 80.0F, var17, 26.0F, var18);
      Y(var4, var19 + var21 - 94.0F, var49, 80.0F, var11, (short)var12, var13, 26.0F, var50, this.K);
      long var51 = 7684262920134L;
      if (7684262920134L == 0L) {
         boolean var71 = false;
      } else {
         int var72 = var51 < 0L ? -1 : 1;
      }

      var4.icon(this.K ? "pause" : "play", var19 + var21 - 79.0F, var49 + 13.0F, 10.0F, this.K ? Icons.TEXT : Theme.tint(-4222985));
      long var53 = 7684262920134L;
      if (7684262920134L == 0L) {
         boolean var73 = false;
      } else {
         int var74 = var53 < 0L ? -1 : 1;
      }

      var4.text(Fonts.MEDIUM, this.K ? "Pause" : "Animate", var19 + var21 - 67.0F, var49 + 13.0F, 9.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private void Z(Canvas var1, float var2, float var3, float var4, long var5) {
      var1.push();
      var1.translate(var2, var3 + 7.0F);
      var1.scale(1.0F, 0.2F);
      var1.softCircle(0.0F, 0.0F, var4 * 1.12F, 46.0F, -1342177280);
      var1.circle(0.0F, 0.0F, var4, Theme.tint(-15856873));
      var1.pop();
      var1.push();
      var1.translate(var2, var3);
      var1.scale(1.0F, 0.2F);
      var1.circleRadial(0.0F, 0.0F, var4, Theme.tint(-13950398), Theme.tint(-15396832));
      var1.ring(0.0F, 0.0F, var4 - 1.0F, 1.6F, BlendMode.withAlpha(-1, 0.07F));
      var1.ring(0.0F, 0.0F, var4 * 0.72F, 1.0F, BlendMode.withAlpha(Theme.tint(-4222985), 0.08F));
      var1.softCircle(0.0F, 0.0F, var4 * 0.55F, 34.0F, -1711276032);
      float var7 = ((23.0F - (float)Math.toDegrees(this.L)) % 360.0F + 360.0F) % 360.0F;
      var1.arc(0.0F, 0.0F, var4 - 1.0F, 2.2F, var7, 64.0F, BlendMode.withAlpha(Theme.tint(-4222985), 0.7F));
      var1.pop();
      var1.glowCircle(var2, var3, var4 * 0.5F, 60.0F, BlendMode.withAlpha(Theme.tint(-4222985), 0.05F));
   }

   private void A(Canvas var1, float var2, long var3, float var5) {
      String var6 = MountUpdater4.boardKeyName();
      String var7 = MountUpdater4.keyName();
      float var8 = var2 - var1.textWidth(Fonts.MEDIUM, "Board", 9.5F);
      var1.text(Fonts.MEDIUM, "Board", var8, var5, 9.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
      var8 -= x(var1, var6) + 6.0F;
      KeyLabel.keyCap(var1, var6, var8, var5, 8.5F, Icons.TEXT_2);
      var8 -= 16.0F;
      var8 -= var1.textWidth(Fonts.MEDIUM, "Place", 9.5F);
      var1.text(Fonts.MEDIUM, "Place", var8, var5, 9.5F, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
      var8 -= x(var1, var7) + 6.0F;
      KeyLabel.keyCap(var1, var7, var8, var5, 8.5F, Icons.TEXT_2);
   }

   private static float x(Canvas var0, String var1) {
      return Math.max(18.5F, var0.textWidth(Fonts.SEMIBOLD, var1, 8.5F) + 12.0F);
   }

   private void z(DrawContext var1, long var2, Canvas var4, MountFactory.Data7 var5) {
      long var6 = 96279876432718L;
      long var8 = 134804297551062L;
      short var10 = 31386;
      short var11 = 146;
      int var12 = (int)(var8 << 40 >>> 40);
      var8 = 25518192908184L;
      byte var13 = 0;
      int var14 = 1521002;
      int var15 = (int)(var8 << 40 >>> 40);
      long var16 = 41447672185900L;
      MountFactory.Data7.ensureSourceClassInitialized();
      float var18 = (this.S - 16.0F) / 3.0F;

      for (int var19 = 0; var19 < this.u.size(); var19++) {
         int var20 = var19 % 3;
         int var21 = var19 / 3;
         float var22 = this.T + var20 * (var18 + 8.0F);
         float var23 = this.V + 284.0F + var21 * 80.0F;
         MountFactory.Data7 var24 = MountFactory.get(this.u.get(var19));
         boolean var25 = var24 == var5;
         float var26 = this.N((byte)var13, var1, 2 + var19, var22, var23, var18, var14, 72.0F, var15);
         L(var4, var22, var6, var23, var18, 72.0F, 11.0F, var25, var26);
         var4.pushClip(var22 + 1.0F, var23 + 1.0F, var18 - 2.0F, 70.0F, 10.0F);
         var4.glowCircle(
            var22 + var18 / 2.0F, var23 + 26.0F, var18 * 0.22F, 34.0F, BlendMode.withAlpha(Theme.tint(-4222985), var25 ? 0.1F : 0.04F + var26 * 0.04F)
         );
         TextureLoader var27 = this.K(var19, var10, (byte)var11, var12, var24);
         if (var27 != null) {
            float var28 = 46.0F;
            float var29 = var28 * var27.aspect();
            long var30 = 52701677296816L;
            if (52701677296816L == 0L) {
               boolean var10000 = false;
            } else {
               int var44 = var30 < 0L ? -1 : 1;
            }

            long var33 = 52701677296817L;
            if (52701677296817L == 0L) {
               boolean var45 = false;
            } else {
               int var46 = var33 < 0L ? -1 : 1;
            }

            if (var29 > var18 - 8.0F) {
               var29 = var18 - 8.0F;
               var28 = var29 / var27.aspect();
            }

            var4.image(var27, var22 + (var18 - var29) / 2.0F, var23 + 3.0F - var26, var29, var28);
         }

         var4.popClip();
         TrueTypeFont var40 = Fonts.SEMIBOLD;
         String var41 = var24.name();
         float var42 = var22 + 10.0F;
         float var31 = var23 + 72.0F - 13.0F;
         float var32 = var18 - 20.0F;
         long var43 = 52701677296816L;
         if (52701677296816L == 0L) {
            boolean var47 = false;
         } else {
            int var48 = var43 < 0L ? -1 : 1;
         }

         var4.textEllipsized(
            var40, var41, var42, var31, 10.5F, var32, var25 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var26), FontStyle.LEFT, FontWeight.MIDDLE
         );
         long var36 = 52701677296816L;
         if (52701677296816L == 0L) {
            boolean var49 = false;
         } else {
            int var50 = var36 < 0L ? -1 : 1;
         }

         if (var25) {
            E(var4, var22 + var18 - 13.0F, var16, var23 + 13.0F);
         }
      }
   }

   
   private TextureLoader K(int var1, int var2, byte var3, int var4, MountFactory.Data7 var5) {
      MountFactory.Data7.ensureSourceClassInitialized();
      if (!this.l[var1]) {
         this.l[var1] = true;

         try {
            this.Q[var1] = TextureLoader.load("garage/portrait/" + var5.id() + ".png");
         } catch (Exception var7) {
            this.Q[var1] = null;
         }
      }

      return this.Q[var1];
   }

   private void R(long var1, DrawContext var3, Canvas var4, MountFactory.Data7 var5) {
      long var6 = 25518192908184L;
      byte var8 = 0;
      int var9 = 1521002;
      int var10 = (int)(var6 << 40 >>> 40);
      var6 = 129211290747197L;
      byte var11 = 0;
      int var12 = 7701593;
      int var13 = (int)(var6 << 40 >>> 40);
      MountFactory.Data7.ensureSourceClassInitialized();
      List var14 = var5.paints();
      int var15 = this.O.J.argb();
      int var16 = -1;

      for (int var17 = 0; var17 < var14.size(); var17++) {
         long var18 = 4884420514390L;
         if (4884420514390L == 0L) {
            boolean var10000 = false;
         } else {
            int var58 = var18 < 0L ? -1 : 1;
         }

         long var21 = 4884420514389L;
         if (4884420514389L == 0L) {
            boolean var59 = false;
         } else {
            int var60 = var21 < 0L ? -1 : 1;
         }

         if (var15 == ((MountFactory.Data4)var14.get(var17)).color()) {
            var16 = var17;
         }
      }

      long var51 = 4884420514389L;
      if (4884420514389L == 0L) {
         boolean var61 = false;
      } else {
         int var62 = var51 < 0L ? -1 : 1;
      }

      int var24 = this.G >= 0 ? this.G : var16;
      long var25 = 4884420514389L;
      if (4884420514389L == 0L) {
         boolean var63 = false;
      } else {
         int var64 = var25 < 0L ? -1 : 1;
      }

      var4.text(Fonts.MEDIUM, "Paint finish", this.T, this.V + 538.0F, 10.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      long var27 = 4884420514389L;
      if (4884420514389L == 0L) {
         boolean var65 = false;
      } else {
         int var66 = var27 < 0L ? -1 : 1;
      }

      float var19;
      int var20;
      String var46;
      float var48;
      label131: {
         var46 = var24 >= 0 ? ((MountFactory.Data4)var14.get(var24)).name() : "Custom";
         var48 = this.T + this.S;
         var19 = this.V + 538.0F;
         if (this.G >= 0) {
            long var30 = 4884420514390L;
            if (4884420514390L == 0L) {
               boolean var67 = false;
            } else {
               int var68 = var30 < 0L ? -1 : 1;
            }

            if (this.G != var16) {
               var20 = Icons.TEXT;
               break label131;
            }
         }

         var20 = Theme.tint(-4222985);
      }

      var4.text(Fonts.MEDIUM, var46, var48, var19, 10.5F, var20, FontStyle.RIGHT, FontWeight.MIDDLE);
      float var52 = this.S / Math.max(1, var14.size());
      int var22 = -1;

      for (int var23 = 0; var23 < var14.size(); var23++) {
         MountFactory.Data4 var54 = (MountFactory.Data4)var14.get(var23);
         float var55 = this.T + var52 * (var23 + 0.5F);
         float var26 = this.V + 554.0F + 16.0F;
         var27 = 4884420514390L;
         if (4884420514390L == 0L) {
            boolean var69 = false;
         } else {
            int var70 = var27 < 0L ? -1 : 1;
         }

         float var57 = this.N((byte)var8, var3, 11 + var23, var55 - var52 / 2.0F + 2.0F, var26 - 16.0F - 4.0F, var52 - 4.0F, var9, 56.0F, var10);
         if (this.U && var3.hoverTarget() == this.m[11 + var23]) {
            var22 = var23;
         }

         var48 = 16.0F * (1.0F + 0.06F * var57);
         int var31 = var54.color();
         long var32 = 4884420514390L;
         if (4884420514390L == 0L) {
            boolean var71 = false;
         } else {
            int var72 = var32 < 0L ? -1 : 1;
         }

         boolean var35 = var23 == var16;
         e(var4, var55, var26, (byte)var11, var48, var31, var12, var35, var13, var57);
         TrueTypeFont var40 = Fonts.MEDIUM;
         var46 = var54.name();
         var19 = var26 + 16.0F + 15.0F;
         float var41 = var52 - 6.0F;
         long var42 = 4884420514390L;
         if (4884420514390L == 0L) {
            boolean var73 = false;
         } else {
            int var74 = var42 < 0L ? -1 : 1;
         }

         var4.textEllipsized(
            var40,
            var46,
            var55,
            var19,
            9.0F,
            var41,
            var23 == var16 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_3, Icons.TEXT_2, var57),
            FontStyle.CENTER,
            FontWeight.MIDDLE
         );
      }

      long var53 = 4884420514390L;
      if (4884420514390L == 0L) {
         boolean var75 = false;
      } else {
         int var76 = var53 < 0L ? -1 : 1;
      }

      this.G = var22;
   }

   private static void e(Canvas var0, float var1, float var2, byte var3, float var4, int var5, int var6, boolean var7, int var8, float var9) {
      MountFactory.Data7.ensureSourceClassInitialized();
      if (var7 || var9 > 0.01F) {
         var0.ring(var1, var2, var4 + 4.5F, var7 ? 1.6F : 1.2F, BlendMode.withAlpha(Theme.tint(-4222985), var7 ? 0.9F : 0.45F * var9));
      }

      var0.shadow(var1 - var4, var2 - var4, var4 * 2.0F, var4 * 2.0F, var4, 10.0F, 0.0F, 0.0F, 3.0F, 1879048192);
      var0.roundRectGradient(
         var1 - var4, var2 - var4, var4 * 2.0F, var4 * 2.0F, var4, 120.0F, BlendMode.lerp(var5, -1, 0.28F), BlendMode.lerp(var5, -16777216, 0.38F)
      );
      var0.softCircle(var1 - var4 * 0.32F, var2 - var4 * 0.42F, var4 * 0.28F, var4 * 0.5F, BlendMode.withAlpha(-1, 0.32F));
      var0.ring(var1, var2, var4 - 0.5F, 1.0F, BlendMode.withAlpha(-1, 0.12F));
   }

   private float N(byte var1, DrawContext var2, int var3, float var4, float var5, float var6, int var7, float var8, int var9) {
      MountFactory.Data7.ensureSourceClassInitialized();
      boolean var10 = this.U && var2.hover(this.m[var3], var4, var5, var6, var8, DrawContext.TypeE.HAND);
      return this.m[var3].target(var10 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var2.dt));
   }

   private static void L(Canvas var0, float var1, long var2, float var4, float var5, float var6, float var7, boolean var8, float var9) {
      var0.roundRectGradient(
         var1,
         var4,
         var5,
         72.0F,
         11.0F,
         90.0F,
         BlendMode.lerp(Theme.tint(-15330778), Theme.tint(-4222985), (var8 ? 0.1F : 0.02F) + var9 * 0.04F),
         BlendMode.lerp(Theme.tint(-15922411), Theme.tint(-4222985), (var8 ? 0.06F : 0.01F) + var9 * 0.02F)
      );
      var0.roundRectOutline(var1, var4, var5, 72.0F, 11.0F, var8 ? 1.3F : 1.0F, BlendMode.withAlpha(Theme.tint(-4222985), var8 ? 0.7F : 0.12F + var9 * 0.2F));
   }

   private static void E(Canvas var0, float var1, long var2, float var4) {
      var0.circle(var1, var4, 6.0F, Theme.tint(-4222985));
      var0.icon("check", var1, var4, 8.5F, Icons.BG_1);
   }

   private static void Y(Canvas var0, float var1, float var2, float var3, int var4, short var5, int var6, float var7, float var8, boolean var9) {
      MountFactory.Data7.ensureSourceClassInitialized();
      var0.roundRect(var1, var2, 80.0F, 26.0F, 8.0F, var9 ? BlendMode.withAlpha(Icons.VIOLET, 0.26F) : BlendMode.lerp(385875967, 587202559, var8));
      var0.roundRectOutline(
         var1, var2, 80.0F, 26.0F, 8.0F, 1.0F, var9 ? BlendMode.withAlpha(Icons.MAGENTA, 0.45F + 0.15F * var8) : BlendMode.lerp(486539263, 956301311, var8)
      );
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      if (this.U && var3 == 0 && !(var1 < this.T) && !(var1 >= this.T + this.S)) {
         float var4 = var1 - this.T;
         float var5 = var2 - this.V;
         float var6 = (this.S - 16.0F) / 3.0F;
         if (var5 >= 284.0F && var5 < 524.0F) {
            int var7 = (int)(var4 / (var6 + 8.0F));
            int var8 = (int)((var5 - 284.0F) / 80.0F);
            int var9 = var8 * 3 + var7;
            if (var9 >= 0 && var9 < this.u.size() && var4 - var7 * (var6 + 8.0F) < var6 && var5 - 284.0F - var8 * 80.0F < 72.0F) {
               String var10 = this.u.get(var9);
               if (!this.O.u.is(var10)) {
                  this.O.selectStyle(var10);
                  this.L = 0.72F;
                  this.o = 3.0F;
               }

               UiAudioPolicy.play("SELECT");
               return true;
            }
         }

         if (var5 >= 550.0F && var5 < 608.0F) {
            List var11 = MountFactory.get(this.O.u.get()).paints();
            int var12 = (int)(var4 / (this.S / var11.size()));
            if (var12 >= 0 && var12 < var11.size()) {
               this.O.J.set(((MountFactory.Data4)var11.get(var12)).color());
               UiAudioPolicy.play("CLICK");
               return true;
            }
         }

         if (var5 >= 14.0F && var5 < 40.0F && var4 >= this.S - 94.0F && var4 < this.S - 14.0F) {
            this.K = !this.K;
            UiAudioPolicy.play("CLICK");
            return true;
         } else if (var5 >= 44.0F && var5 < 266.0F) {
            this.g = true;
            this.F = var1;
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   public void mouseDrag(float var1, float var2) {
      if (this.g) {
         this.L = this.L + (var1 - this.F) * 0.012F;
         this.F = var1;
         this.o = 6.0F;
      }
   }

   public void mouseUp() {
      this.g = false;
   }

   public void close() {
      this.mouseUp();

      for (int var1 = 0; var1 < this.Q.length; var1++) {
         if (this.Q[var1] != null) {
            this.Q[var1].close();
         }

         this.Q[var1] = null;
      }
   }

}
