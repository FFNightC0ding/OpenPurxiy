// Sourced By DevFFNightmare
package dev.purxiy.modules.creamykeys;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.DrawContext;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class CreamyKeysRenderer {
   private static final float r = 14.0F;
   private static final float k = 210.0F;
   private static final float L = 250.0F;
   private static final float J = 268.0F;
   private static final float x = 82.0F;
   private static final float Z = 10.0F;
   public static final float W = 360.0F;
   private static final int B = 0;
   private static final int i = 1;
   private static final int v = 2;
   private static final CreamyKeySounds.Style[] G = CreamyKeySounds.Style.values();
   private static final long g = 140000000L;
   private static final long n = 620000000L;
   private static final long a = 700000000L;
   private static final CreamyKeysRenderer.TypeT[] u = new CreamyKeysRenderer.TypeT[]{
      new CreamyKeysRenderer.TypeT(-527877, -2306572, -5338891, -10663045, -1, -1779214, -3692801),
      new CreamyKeysRenderer.TypeT(-788484, -3483412, -9128974, -13746857, -1, -2629138, -7351809),
      new CreamyKeysRenderer.TypeT(-12503206, -13686971, -5207304, -1647361, -14937040, -14673361, -5735425),
      new CreamyKeysRenderer.TypeT(-199709, -928348, -1858484, -9745627, -1, -1122106, -867718)
   };
   private static final CreamyKeysRenderer.TypeX[][] m = P(19565708920243L);
   private static final int t = M(38458533443985L);
   private final CreamyKeysModule V;
   private final Animator[] d = new Animator[t];
   private final Animator[] C = new Animator[t];
   private final long[] c = new long[t];
   private final long[] N = new long[t];
   private final float[] D = new float[t];
   private final float[] j = new float[t];
   private final float[] P = new float[t];
   private final float[] F = new float[t];
   private final Animator[] H = new Animator[3];
   private final Animator[] e = new Animator[3];
   private final long[] S = new long[3];
   private final long[] I = new long[3];
   private final Animator[] U = new Animator[4];
   private final long[] Q = new long[4];
   private final Animator y = Animator.smooth(1.0F);
   private final Animator E = new Animator(0.0F, 160.0F, 18.0F);
   private final List<CreamyKeysRenderer.TypeV> f = new ArrayList<>();
   private int K;
   private int s = -1;
   private long p;
   private float R;
   private float z;
   private float l;
   private float T;
   private float o;
   private float w;
   private float A;
   private float q;
   private boolean X;

   private static final String[] h = new String[21];
   private static final long[] M = new long[]{
      2977897431609839438L,
      6518799849295110075L,
      -4379413052826997673L,
      6663320363338321428L,
      -7937746765698518889L,
      -3370583053198101739L,
      7835379955874718005L,
      4751901730195994694L,
      6512977842178405736L,
      7819704542782310188L,
      5186258083384009205L,
      4831107759483736914L,
      -8434235133749992519L,
      7870432531268196903L,
      4761385099671481203L,
      -5970513521460336156L,
      -7058638086823703436L,
      -2070376647848979715L,
      -6634200538335541726L,
      -6311712886969617566L,
      4085876115972240790L,
      -5495423116339227775L,
      3025216665039129358L,
      -853875145953365503L,
      4316047627755948903L,
      -472467945698862957L,
      2353111703006808029L,
      6895293029494425343L,
      -7081631410274050370L,
      6643974604489350793L,
      -6280500915116777115L,
      9197640020744590811L,
      -1443970459759917220L,
      -2835328872946553497L,
      -5340922270086716032L,
      -2848906942302554027L,
      7031147911477823699L,
      1476077509260618992L,
      8048509380698640995L,
      -5971546447278533007L,
      -8354770742105178144L,
      3199573982873243772L,
      -3657520722007269403L
   };

   private static final long[] Y = new long[]{
      1749289730397117789L, 2828900417631702993L, 79731757285804386L, 1404221355197846039L, 8989340794339012678L, 2759710731231959722L, 2912140150348887886L
   };
   private static final Long[] ab = new Long[7];

   public CreamyKeysRenderer(CreamyKeysModule var1) {
      this.V = var1;

      for (int var2 = 0; var2 < t; var2++) {
         this.d[var2] = Animator.smooth(0.0F);
         this.C[var2] = new Animator(0.0F, 520.0F, 30.0F);
      }

      for (int var3 = 0; var3 < 3; var3++) {
         this.H[var3] = Animator.smooth(0.0F);
         this.e[var3] = new Animator(0.0F, 520.0F, 30.0F);
      }

      for (int var4 = 0; var4 < 4; var4++) {
         this.U[var4] = Animator.smooth(0.0F);
      }
   }

   public void render(DrawContext var1, float var2, float var3, float var4, boolean var5) {
      long var6 = 21702218412346L;
      long var8 = var6 ^ 17904672265050L;
      long var10 = (var6 ^ 4191240259626L) >>> 32;
      int var12 = (int)((var6 ^ 4191240259626L) << 32 >>> 32);
      int var13 = (int)((var6 ^ 91225094827642L) >>> 32);
      long var14 = (var6 ^ 91225094827642L) << 32 >>> 32;
      long var16 = var6 ^ 124049117256097L;
      int var18 = (int)((var6 ^ 124049117256097L) >>> 32);
      int var19 = (int)((var6 ^ 124049117256097L) << 32 >>> 48);
      int var20 = (int)(var16 << 48 >>> 48);
      this.R = var2;
      this.z = var3;
      this.l = var4;
      this.X = var5 && !var1.masked();
      if (var5) {
         this.V.audition();
      }

      Canvas var21 = var1.canvas;
      long var22 = System.nanoTime();
      int var24 = this.V.style().ordinal();
      if (this.s != var24) {
         this.K = this.s < 0 ? var24 : this.s;
         this.s = var24;
         this.y.snap(this.K == var24 ? 1.0F : 0.0F).target(1.0F);
      }

      float var25 = Math.clamp(this.y.update(MotionStyle.PANEL.dt(var1.dt)), 0.0F, 1.0F);
      CreamyKeysRenderer.TypeT var26 = u[this.K].mix(u[this.s], var25);
      float var28 = var3 + 14.0F;
      float var30 = 210.0F;
      this.t(var10, var21, var12, var2, var28, var4, var30, var26, var24);
      var21.pushClip(var2 + 1.0F, var28 + 1.0F, var4 - 2.0F, var30 - 2.0F, 15.0F);
      this.a(var1, var21, var2, var28, var8, var4, var30, var26, var22);
      this.G(var18, (short)var19, var21, var22, var20);
      var21.popClip();
      var21.text(Fonts.MEDIUM, "Sound style", var2, var3 + 250.0F, 13.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      var21.text(Fonts.REGULAR, G.length + " recordings", var2 + var4, var3 + 250.0F, 10.5F, Icons.TEXT_3, FontStyle.RIGHT, FontWeight.MIDDLE);
      this.t(var13, var1, var21, var14, var24, var22);
   }

   private void t(long var1, Canvas var3, int var4, float var5, float var6, float var7, float var8, CreamyKeysRenderer.TypeT var9, int var10) {
      var3.roundRectGradient(var5, var6, var7, 210.0F, 16.0F, 90.0F, Theme.tint(-14936784), Theme.tint(-16053996));
      var3.pushClip(var5 + 1.0F, var6 + 1.0F, var7 - 2.0F, 208.0F, 15.0F);
      var3.glowCircle(var5 + var7 / 2.0F, var6 + 63.000004F, var7 * 0.2F, 150.0F, BlendMode.withAlpha(var9.W, 0.11F));
      var3.glowCircle(var5 + var7 / 2.0F, var6 - 10.0F, var7 * 0.12F, 90.0F, BlendMode.withAlpha(-1, 0.05F));
      var3.popClip();
      var3.roundRectOutlineGradient(
         var5, var6, var7, 210.0F, 16.0F, 1.0F, 90.0F, BlendMode.withAlpha(Theme.tint(-3826187), 0.24F), BlendMode.withAlpha(Theme.tint(-3826187), 0.08F)
      );
      var3.text(
         Fonts.MEDIUM,
         "0" + (var10 + 1) + " / " + G[var10].F.toUpperCase(Locale.ROOT),
         var5 + 16.0F,
         var6 + 18.0F,
         8.6F,
         Icons.TEXT_2,
         FontStyle.LEFT,
         FontWeight.MIDDLE,
         0.06F
      );
      String var11 = "Type or click to listen";
      float var12 = var3.textWidth(Fonts.MEDIUM, var11, 9.5F) + 36.0F;
      float var13 = var5 + var7 - 14.0F - var12;
      float var14 = var6 + 8.0F;
      var3.roundRect(var13, var14, var12, 22.0F, 7.0F, 318767103);
      var3.roundRectOutline(var13, var14, var12, 22.0F, 7.0F, 1.0F, 486539263);
      var3.icon("keyboard", var13 + 14.0F, var14 + 11.0F, 11.0F, BlendMode.lerp(Icons.TEXT_2, var9.W, 0.55F));
      var3.text(Fonts.MEDIUM, var11, var13 + 26.0F, var14 + 11.0F, 9.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      if (this.V.Z.floatValue() <= 0.001F) {
         String var15 = "Muted  ·  raise Keyboard volume to listen";
         float var16 = var3.textWidth(Fonts.MEDIUM, var15, 9.5F) + 34.0F;
         float var17 = var5 + var7 / 2.0F - var16 / 2.0F;
         float var18 = var6 + 210.0F - 30.0F;
         var3.roundRect(var17, var18, var16, 22.0F, 11.0F, BlendMode.withAlpha(Theme.tint(-16053996), 0.8F));
         var3.roundRectOutline(var17, var18, var16, 22.0F, 11.0F, 1.0F, BlendMode.withAlpha(-867718, 0.35F));
         var3.icon("volume-x", var17 + 14.0F, var18 + 11.0F, 11.0F, -867718);
         var3.text(Fonts.MEDIUM, var15, var17 + 25.0F, var18 + 11.0F, 9.5F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      }
   }

   private void a(DrawContext var1, Canvas var2, float var3, float var4, long var5, float var7, float var8, CreamyKeysRenderer.TypeT var9, long var10) {
      long var12 = 118895349624017L;
      short var14 = 27682;
      byte var15 = 123;
      int var16 = (int)(var12 << 40 >>> 40);
      long var17 = 38119448268761L;
      float var19 = Math.min((var7 - 52.0F) / 18.85F, 31.0F);
      this.q = var19;
      float var20 = 15.9F * var19;
      float var21 = 4.9F * var19;
      float var22 = var20 + 0.95F * var19 + 2.0F * var19;
      float var23 = var3 + (var7 - var22) / 2.0F;
      float var24 = var4 + 38.0F + (150.0F - var21) / 2.0F;
      this.D(var1, var2, var23, var24, var20, var21, var19, var9, var14, (byte)var15, var10, var16);
      this.w = 2.0F * var19;
      this.A = 3.55F * var19;
      this.T = var23 + var20 + 0.95F * var19;
      this.o = var24 + (var21 - this.A) / 2.0F + 0.25F * var19;
      this.d(var1, var2, this.T, this.o, this.w, this.A, var19, var17, var9, var10);
   }

   private void D(
      DrawContext var1,
      Canvas var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      CreamyKeysRenderer.TypeT var8,
      int var9,
      byte var10,
      long var11,
      int var13
   ) {
      long var14 = 118895349624017L;
      long var16 = var14 ^ 68813893350534L;
      long var18 = var14 ^ 16101013455274L;
      long var20 = var14 ^ 20324082087920L;
      int var22 = (int)((var14 ^ 20324082087920L) >>> 32);
      int var23 = (int)((var14 ^ 20324082087920L) << 32 >>> 48);
      int var24 = (int)(var20 << 48 >>> 48);
      float var25 = 0.42F * var7;
      float var26 = 0.2F * var7;
      var2.push();
      var2.translate(var3 + var5 / 2.0F, var4 + var6 + var26 * 0.6F);
      CreamyKeysModule.ensureSourceClassInitialized();
      var2.scale(1.0F, 0.16F);
      var2.softCircle(0.0F, 0.0F, var5 * 0.5F, 60.0F, -1342177280);
      var2.pop();
      var2.shadow(var3, var4, var5, var6 + var26, var25, 28.0F, 0.0F, 0.0F, 12.0F, -1979711488);
      var2.roundRect(var3, var4 + var26, var5, var6, var25, BlendMode.shade(var8.u, -0.34F));
      var2.roundRectGradient(var3, var4, var5, var6, var25, 90.0F, BlendMode.shade(var8.u, 0.07F), BlendMode.shade(var8.u, -0.09F));
      var2.roundRectOutlineGradient(var3, var4, var5, var6, var25, 1.0F, 90.0F, BlendMode.withAlpha(-1, 0.42F), BlendMode.withAlpha(-1, 0.02F));
      float var27 = 0.45F * var7;
      var2.roundRect(
         var3 + var27 - 0.13F * var7,
         var4 + var27 - 0.13F * var7,
         var5 - var27 * 2.0F + 0.26F * var7,
         var6 - var27 * 2.0F + 0.26F * var7,
         0.2F * var7,
         BlendMode.shade(var8.u, -0.22F)
      );
      float var28 = 0.085F * var7;
      int var29 = 0;

      for (int var30 = 0; var30 < m.length; var30++) {
         float var31 = var3 + var27;
         float var32 = var4 + var27 + var30 * var7;

         for (CreamyKeysRenderer.TypeX var36 : m[var30]) {
            float var37 = var36.units() * var7;
            this.D[var29] = var31 + var28 / 2.0F;
            this.j[var29] = var32 + var28 / 2.0F;
            this.P[var29] = var37 - var28;
            this.F[var29] = var7 - var28;
            int var38 = 27682;
            boolean var39 = this.X && var1.hover(this.d[var29], this.D[var29], this.j[var29], this.P[var29], this.F[var29], DrawContext.TypeE.HAND);
            float var40 = this.d[var29].target(var39 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
            long var41 = this.V.lastPress(var36.key());
            if (var41 != 0L && var41 != this.N[var29]) {
               var38 = 1338577;
               long var43 = this.N[var29];
               if (var43 != 0L || var11 - var41 < 400000000L) {
                  this.T(var29, var36, var22, (char)var23, (char)var24, var8, var41);
               }

               this.N[var29] = var41;
            }

            boolean var52 = var11 < this.c[var29] || this.V.physicallyDown(var36.key());
            float var44 = this.C[var29].target(var52 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
            int var45 = var36.role();
            if (var45 == 2) {
               var45 = var8.b;
            } else {
               var45 = var36.role();
               var38 = 1338577;
               var45 = var45 == 1 ? var8.i : var8.B;
            }

            int var55 = var36.role();
            var38 = 1338577;
            int var47 = var55 == 2 ? var8.h : var8.e;
            float var48 = Math.max(0.0F, 1.0F - (float)(var11 - this.K(var18, var29)) / 2.6E8F);
            n(var2, this.D[var29], this.j[var29], this.P[var29], var16, this.F[var29], var7, var36, var45, var47, var40, var44, var48, var8.W);
            var31 += var37;
            var29++;
         }
      }
   }

   private long K(long var1, int var3) {
      return Math.max(this.N[var3], this.c[var3] - 140000000L);
   }

   private static void n(
      Canvas var0,
      float var1,
      float var2,
      float var3,
      long var4,
      float var6,
      float var7,
      CreamyKeysRenderer.TypeX var8,
      int var9,
      int var10,
      float var11,
      float var12,
      float var13,
      int var14
   ) {
      CreamyKeysModule.ensureSourceClassInitialized();
      float var15 = Math.clamp(var12, 0.0F, 1.2F) * 0.075F * var7;
      float var16 = 0.17F * var7;
      var0.shadow(var1, var2 + 0.06F * var7, var3, var6, var16, 0.3F * var7, 0.0F, 0.0F, 0.05F * var7, 1073741824);
      var0.roundRectGradient(var1, var2 + var15 * 0.45F, var3, var6 - var15 * 0.45F, var16, 90.0F, BlendMode.shade(var9, -0.15F), BlendMode.shade(var9, -0.34F));
      float var17 = 0.105F * var7;
      float var18 = 0.05F * var7;
      float var19 = 0.2F * var7;
      float var20 = var1 + var17;
      float var21 = var2 + var18 + var15;
      float var22 = var3 - var17 * 2.0F;
      float var23 = var6 - var18 - var19;
      var0.roundRectGradient(
         var20, var21, var22, var23, var16 * 0.78F, 90.0F, BlendMode.shade(var9, 0.12F + var11 * 0.06F), BlendMode.shade(var9, -0.035F + var11 * 0.05F)
      );
      var0.roundRectOutlineGradient(var20, var21, var22, var23, var16 * 0.78F, 0.8F, 90.0F, BlendMode.withAlpha(-1, 0.38F), BlendMode.withAlpha(-1, 0.0F));
      float var24;
      int var25 = (var24 = var13 - 0.01F) == 0.0F ? 0 : (var24 < 0.0F ? -1 : 1);
      long var26 = 90948092516438L;
      if (90948092516438L == 0L) {
         boolean var10000 = false;
      } else {
         int var30 = var26 < 0L ? -1 : 1;
      }

      if (var25 > 0) {
         var0.glow(var20, var21, var22, var23, var16, 0.5F * var7, BlendMode.withAlpha(var14, 0.5F * var13));
      }

      if (var8.key() == 32) {
         var0.text(
            Fonts.MEDIUM,
            "purxiy",
            var20 + var22 / 2.0F,
            var21 + var23 / 2.0F,
            0.25F * var7,
            BlendMode.withAlpha(var10, 0.42F),
            FontStyle.CENTER,
            FontWeight.MIDDLE,
            0.18F
         );
      } else if (!var8.label().isEmpty()) {
         float var29 = var8.label().length() > 1 ? 0.25F * var7 : 0.32F * var7;
         var0.text(Fonts.SEMIBOLD, var8.label(), var20 + var22 / 2.0F, var21 + var23 / 2.0F, var29, var10, FontStyle.CENTER, FontWeight.MIDDLE);
      }
   }

   private void d(
      DrawContext var1, Canvas var2, float var3, float var4, float var5, float var6, float var7, long var8, CreamyKeysRenderer.TypeT var10, long var11
   ) {
      long var13 = 114425360060015L;
      long var15 = 16009572416498L;
      float var17 = var6 * 0.42F;
      CreamyKeysModule.ensureSourceClassInitialized();
      float[] var18 = new float[3];

      for (int var19 = 0; var19 < 3; var19++) {
         float[] var20 = this.q(var19, var15);
         long var21 = 38119448268761L;
         if (38119448268761L == 0L) {
            boolean var10000 = false;
         } else {
            int var62 = var21 < 0L ? -1 : 1;
         }

         boolean var24 = this.X && var1.hover(this.H[var19], var20[0], var20[1], var20[2], var20[3], DrawContext.TypeE.HAND);
         var18[var19] = this.H[var19].target(var24 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var1.dt));
         long var25 = this.V.lastClick(var19);
         long var27 = 38119448268761L;
         if (38119448268761L == 0L) {
            boolean var63 = false;
         } else {
            int var64 = var27 < 0L ? -1 : 1;
         }

         long var30 = 38119448268761L;
         if (38119448268761L == 0L) {
            boolean var65 = false;
         } else {
            int var66 = var30 < 0L ? -1 : 1;
         }

         if (var25 != 0L && var25 != this.I[var19]) {
            long var32 = 38119448268761L;
            if (38119448268761L == 0L) {
               boolean var67 = false;
            } else {
               int var68 = var32 < 0L ? -1 : 1;
            }

            if (this.I[var19] != 0L) {
               this.o(var20[0] + var20[2] / 2.0F, var20[1] + var20[3] / 2.0F, var13, var7 * 1.4F, var10.W, var25);
            }

            this.I[var19] = var25;
         }
      }

      float[] var49 = new float[3];

      for (int var50 = 0; var50 < 3; var50++) {
         boolean var52 = var11 < this.S[var50] || this.V.buttonDown(var50);
         var49[var50] = this.e[var50].target(var52 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var1.dt));
      }

      long var51 = 38119448268761L;
      if (38119448268761L == 0L) {
         boolean var69 = false;
      } else {
         int var70 = var51 < 0L ? -1 : 1;
      }

      long var23 = this.V.lastScroll();
      long var53 = 38119448268760L;
      if (38119448268760L == 0L) {
         boolean var71 = false;
      } else {
         int var72 = var53 < 0L ? -1 : 1;
      }

      if (var23 != 0L && var23 != this.p) {
         if (this.p != 0L) {
            this.E.target(this.E.target() - this.V.scrollDirection() * 0.34F);
         }

         this.p = var23;
      }

      float var28 = this.E.update(MotionStyle.TOGGLE.dt(var1.dt));
      float var29 = var5 / 2.0F;
      float var54 = 0.16F * var7;
      int var31 = var10.B;
      var2.push();
      var2.translate(var3 + var5 / 2.0F, var4 + var6 + var54 * 0.7F);
      var2.scale(1.0F, 0.2F);
      var2.softCircle(0.0F, 0.0F, var5 * 0.62F, 30.0F, -1610612736);
      var2.pop();
      var2.shadow(var3, var4, var5, var6 + var54, var29, 22.0F, 0.0F, 0.0F, 10.0F, Integer.MIN_VALUE);
      var2.roundRect(var3, var4 + var54, var5, var6, var29, BlendMode.shade(var10.u, -0.34F));
      var2.roundRectGradient(var3, var4, var5, var6, var29, 90.0F, BlendMode.shade(var31, 0.08F), BlendMode.shade(var31, -0.12F));
      var2.pushClip(var3, var4, var5, var6, var29);
      int var55 = BlendMode.shade(var31, -0.42F);

      for (int var33 = 0; var33 < 2; var33++) {
         float var34 = var49[var33];
         long var35 = 38119448268761L;
         if (38119448268761L == 0L) {
            boolean var73 = false;
         } else {
            int var74 = var35 < 0L ? -1 : 1;
         }

         float var38 = Math.clamp(var34, 0.0F, 1.0F) * 0.34F + var18[var33] * 0.06F;
         long var39 = 38119448268760L;
         if (38119448268760L == 0L) {
            boolean var75 = false;
         } else {
            int var76 = var39 < 0L ? -1 : 1;
         }

         if (var38 > 0.005F) {
            var2.rect(var3 + var33 * var5 / 2.0F, var4, var5 / 2.0F, var17, BlendMode.withAlpha(var55, var38));
         }
      }

      var2.popClip();
      int var56 = BlendMode.withAlpha(BlendMode.shade(var31, -0.45F), 0.8F);
      var2.line(var3 + var5 / 2.0F, var4 + 1.0F, var3 + var5 / 2.0F, var4 + var17, 0.9F, var56);
      var2.line(var3 + var5 * 0.07F, var4 + var17, var3 + var5 * 0.93F, var4 + var17, 0.9F, var56);
      long var57 = 38119448268760L;
      if (38119448268760L == 0L) {
         boolean var77 = false;
      } else {
         int var78 = var57 < 0L ? -1 : 1;
      }

      float var37 = var5 * 0.17F;
      float var58 = var17 * 0.42F;
      float var59 = var3 + var5 / 2.0F - var37 / 2.0F;
      float var40 = var4 + var17 * 0.22F + Math.clamp(var49[2], 0.0F, 1.0F) * 0.04F * var7;
      var2.roundRect(var59 - 1.6F, var40 - 1.6F, var37 + 3.2F, var58 + 3.2F, var37 / 2.0F + 1.6F, BlendMode.shade(var31, -0.5F));
      var2.roundRectGradient(
         var59, var40, var37, var58, var37 / 2.0F, 90.0F, BlendMode.shade(var10.b, 0.18F + var18[2] * 0.1F), BlendMode.shade(var10.b, -0.2F)
      );
      var2.pushClip(var59, var40, var37, var58, var37 / 2.0F);
      float var41 = var58 / 3.2F;
      float var42 = (var28 * var58 % var41 + var41) % var41;

      for (int var43 = -1; var43 < 5; var43++) {
         float var44 = var40 + var43 * var41 + var42;
         long var45 = 38119448268761L;
         if (38119448268761L == 0L) {
            boolean var79 = false;
         } else {
            int var80 = var45 < 0L ? -1 : 1;
         }

         var2.line(var59 + 1.0F, var44, var59 + var37 - 1.0F, var44, 0.8F, BlendMode.withAlpha(BlendMode.shade(var10.b, -0.45F), 0.55F));
      }

      var2.popClip();
      long var60 = 38119448268761L;
      if (38119448268761L == 0L) {
         boolean var81 = false;
      } else {
         int var82 = var60 < 0L ? -1 : 1;
      }

      var2.roundRectOutlineGradient(var3, var4, var5, var6, var29, 1.0F, 90.0F, BlendMode.withAlpha(-1, 0.45F), BlendMode.withAlpha(-1, 0.02F));

      for (int var61 = 0; var61 < 2; var61++) {
         float var46 = Math.max(0.0F, 1.0F - (float)(var11 - Math.max(this.I[var61], this.S[var61] - 140000000L)) / 2.6E8F);
         long var47 = 38119448268760L;
         if (38119448268760L == 0L) {
            boolean var83 = false;
         } else {
            int var84 = var47 < 0L ? -1 : 1;
         }

         if (var46 > 0.01F) {
            var2.pushClip(var3, var4, var5, var6, var29);
            var2.glow(
               var3 + var61 * var5 / 2.0F + 2.0F, var4 + 2.0F, var5 / 2.0F - 4.0F, var17 - 4.0F, 6.0F, 0.5F * var7, BlendMode.withAlpha(var10.W, 0.35F * var46)
            );
            var2.popClip();
         }
      }
   }

   private float[] q(int var1, long var2) {
      CreamyKeysModule.ensureSourceClassInitialized();
      float var4 = this.A * 0.42F;
      long var5 = 16009572416497L;
      if (16009572416497L == 0L) {
         boolean var10000 = false;
      } else {
         int var9 = var5 < 0L ? -1 : 1;
      }

      if (var1 == 2) {
         float var8 = this.w * 0.34F;
         return new float[]{this.T + this.w / 2.0F - var8 / 2.0F, this.o + var4 * 0.12F, var8, var4 * 0.62F};
      } else {
         return new float[]{this.T + var1 * this.w / 2.0F, this.o, this.w / 2.0F, var4};
      }
   }

   private void t(int var1, DrawContext var2, Canvas var3, long var4, int var6, long var7) {
      long var9 = 71790619254592L;
      long var11 = var9 ^ 70745118695366L;
      CreamyKeysModule.ensureSourceClassInitialized();
      float var13 = (this.l - 30.0F) / 4.0F;

      for (int var14 = 0; var14 < 4; var14++) {
         float var15 = this.R + var14 * (var13 + 10.0F);
         float var16 = this.z + 268.0F;
         CreamyKeysRenderer.TypeT var17 = u[var14];
         long var18 = 240901951L;
         if (240901951L == 0L) {
            boolean var10000 = false;
         } else {
            int var39 = var18 < 0L ? -1 : 1;
         }

         boolean var21 = var14 == var6;
         boolean var22 = this.X && var2.hover(this.U[var14], var15, var16, var13, 82.0F, DrawContext.TypeE.HAND);
         float var23 = this.U[var14].target(var22 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var2.dt));
         int var24 = var17.W;
         var3.roundRect(var15, var16, var13, 82.0F, 12.0F, BlendMode.lerp(Theme.tint(-15725031), var24, (var21 ? 0.1F : 0.025F) + var23 * 0.04F));
         var3.roundRectOutline(var15, var16, var13, 82.0F, 12.0F, var21 ? 1.3F : 1.0F, BlendMode.withAlpha(var24, var21 ? 0.72F : 0.12F + var23 * 0.2F));
         float var25 = var15 + 14.0F;
         short var26 = 16715;
         q(var3, var25, var16 + 13.0F, var17.B, var17.e);
         var25 = var15 + 30.0F;
         var26 = 16715;
         q(var3, var25, var16 + 13.0F, var17.b, var17.h);
         if (var21) {
            var3.circle(var15 + var13 - 15.0F, var16 + 20.0F, 6.0F, var24);
            var3.icon("check", var15 + var13 - 15.0F, var16 + 20.0F, 8.5F, Icons.BG_1);
         }

         float var27 = Math.clamp((float)(var7 - this.Q[var14]) / 7.0E8F, 0.0F, 1.0F);
         float var28 = var15 + 52.0F;
         float var29 = var16 + 20.0F;
         float var30 = var13 - 52.0F - (var21 ? 30 : 14);
         float var31 = var21 ? 0.85F : 0.45F + var23 * 0.25F;
         float var32 = this.Q[var14] == 0L ? 1.0F : var27;
         z(var3, var14, var28, var29, var30, 7.0F, var11, var24, var31, var32);
         var29 = var15 + 14.0F;
         float var35 = var16 + 48.0F;
         var3.text(
            Fonts.SEMIBOLD,
            G[var14].F,
            var29,
            var35,
            12.5F,
            var21 ? Icons.TEXT : BlendMode.lerp(Icons.TEXT_2, Icons.TEXT, var23),
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
         var3.textEllipsized(
            Fonts.REGULAR,
            G[var14].p,
            var15 + 14.0F,
            var16 + 66.0F,
            10.5F,
            var13 - 24.0F,
            var21 ? BlendMode.lerp(Icons.TEXT_2, var24, 0.35F) : Icons.TEXT_3,
            FontStyle.LEFT,
            FontWeight.MIDDLE
         );
      }
   }

   private static void q(Canvas var0, float var1, float var2, int var3, int var4) {
      var0.roundRect(var1, var2 + 2.0F, 14.0F, 14.0F, 4.0F, BlendMode.shade(var3, -0.35F));
      var0.roundRectGradient(var1, var2, 14.0F, 13.0F, 4.0F, 90.0F, BlendMode.shade(var3, 0.1F), BlendMode.shade(var3, -0.05F));
      var0.roundRectOutline(var1, var2, 14.0F, 13.0F, 4.0F, 0.8F, BlendMode.withAlpha(-1, 0.25F));
      var0.circle(var1 + 7.0F, var2 + 6.5F, 1.4F, BlendMode.withAlpha(var4, 0.7F));
   }

   private static void z(Canvas var0, int var1, float var2, float var3, float var4, float var5, long var6, int var8, float var9, float var10) {
      short var11 = 20422;
      int var12 = -2098949434;
      CreamyKeysModule.ensureSourceClassInitialized();
      if (!(var4 <= 10.0F)) {
         byte var13 = 40;
         float var14 = 1.0F + 0.45F * (1.0F - var10) * (var10 < 1.0F ? 1 : 0);
         float var15 = var2;
         float var16 = var3;

         for (int var17 = 0; var17 <= var13; var17++) {
            float var18 = (float)var17 / var13;
            float var19 = var2 + var4 * var18;
            float var20 = var3 - V(var1, var11, var18, var12) * 7.0F * var14;
            long var21 = 1222722486406L;
            if (1222722486406L == 0L) {
               boolean var10000 = false;
            } else {
               int var25 = var21 < 0L ? -1 : 1;
            }

            if (var17 > 0) {
               boolean var24 = var10 < 1.0F && var18 <= var10;
               var0.line(var15, var16, var19, var20, var24 ? 1.6F : 1.2F, BlendMode.withAlpha(var8, var24 ? Math.min(1.0F, var9 + 0.3F) : var9));
            }

            var15 = var19;
            var16 = var20;
         }
      }
   }

   private static float V(int var0, int var1, float var2, int var3) {
      CreamyKeysModule.ensureSourceClassInitialized();
      float var4 = Math.clamp(var2 / 0.06F, 0.0F, 1.0F);
      double var5 = Math.PI * 2;

      return var4 * switch (var0) {
         case 0 -> (float)(Math.sin(var5 * 3.4 * var2) * Math.exp(-4.2 * var2));
         case 1 -> {
            double var7 = Math.sin(var5 * 9.0 * var2) * Math.exp(-10.0F * var2);
            double var9 = var2;
            yield (float)(var7 + (var9 > 0.46 ? 0.62 * Math.sin(var5 * 9.0 * (var2 - 0.46)) * Math.exp(-11.0 * (var2 - 0.46)) : 0.0));
         }
         case 2 -> (float)(Math.sin(var5 * 1.9 * var2) * Math.exp(-2.5 * var2));
         default -> (float)(0.8 * Math.sin(var5 * 2.7 * var2) * Math.exp(-3.2 * var2) + 0.28 * Math.sin(var5 * 5.4 * var2) * Math.exp(-5.0F * var2));
      };
   }

   private void T(int var1, CreamyKeysRenderer.TypeX var2, int var3, char var4, char var5, CreamyKeysRenderer.TypeT var6, long var7) {
      long var9 = 138943981230881L;
      long var11 = var9 ^ 24532053497166L;
      CreamyKeysModule.ensureSourceClassInitialized();
      this.o(this.D[var1] + this.P[var1] / 2.0F, this.j[var1] + this.F[var1] / 2.0F, var11, this.q * (var2.units() > 2.0F ? 1.9F : 1.35F), var6.W, var7);
      this.Q[this.V.style().ordinal()] = var7;
   }

   private void o(float var1, float var2, long var3, float var5, int var6, long var7) {
      CreamyKeysModule.ensureSourceClassInitialized();
      long var9 = 114425360060014L;
      if (114425360060014L == 0L) {
         boolean var10000 = false;
      } else {
         int var15 = var9 < 0L ? -1 : 1;
      }

      long var12 = 114425360060014L;
      if (114425360060014L == 0L) {
         boolean var16 = false;
      } else {
         int var17 = var12 < 0L ? -1 : 1;
      }

      if (this.f.size() >= 24) {
         this.f.removeFirst();
      }

      this.f.add(new CreamyKeysRenderer.TypeV(var1, var2, var5, var6, var7));
   }

   private void G(int var1, short var2, Canvas var3, long var4, int var6) {
      CreamyKeysModule.ensureSourceClassInitialized();
      this.f.removeIf(var2x -> {
         CreamyKeysModule.ensureSourceClassInitialized();
         return var4 - var2x.born() > 620000000L;
      });

      for (CreamyKeysRenderer.TypeV var8 : this.f) {
         float var9 = Math.clamp((float)(var4 - var8.born()) / 6.2E8F, 0.0F, 1.0F);
         float var10 = 1.0F - (1.0F - var9) * (1.0F - var9) * (1.0F - var9);
         float var11 = (1.0F - var9) * (1.0F - var9);
         float var12 = var8.x();
         float var13 = var8.y();
         float var14 = var8.size() * (0.35F + 0.9F * var10);
         float var15 = 1.6F - var9;
         var3.ring(var12, var13, var14, var15, BlendMode.withAlpha(var8.color(), 0.55F * var11));
         float var16;
         int var17 = (var16 = var9 - 0.4F) == 0.0F ? 0 : (var16 < 0.0F ? -1 : 1);
         if (var17 < 0) {
            var3.glowCircle(var8.x(), var8.y(), var8.size() * 0.25F, var8.size() * 0.6F, BlendMode.withAlpha(var8.color(), 0.22F * (1.0F - var9 / 0.4F)));
         }
      }
   }

   private int b(float var1, float var2, long var3) {
      long var5 = 16009572416498L;
      long var7 = 83196188545016L;
      CreamyKeysModule.ensureSourceClassInitialized();
      long var9 = 59440461288917L;
      if (59440461288917L == 0L) {
         boolean var10000 = false;
      } else {
         int var32 = var9 < 0L ? -1 : 1;
      }

      if (!this.X) {
         return Integer.MIN_VALUE;
      } else {
         float var12 = (this.l - 30.0F) / 4.0F;

         for (int var13 = 0; var13 < 4; var13++) {
            long var14 = 59440461288916L;
            if (59440461288916L == 0L) {
               boolean var33 = false;
            } else {
               int var34 = var14 < 0L ? -1 : 1;
            }

            if (c(var1, var2, this.R + var13 * (var12 + 10.0F), this.z + 268.0F, var12, var7, 82.0F)) {
               return var13;
            }
         }

         long var28 = 59440461288917L;
         if (59440461288917L == 0L) {
            boolean var35 = false;
         } else {
            int var36 = var28 < 0L ? -1 : 1;
         }

         for (int var16 = 0; var16 < t; var16++) {
            boolean var17 = c(var1, var2, this.D[var16], this.j[var16], this.P[var16], var7, this.F[var16]);
            long var18 = 59440461288916L;
            if (59440461288916L == 0L) {
               boolean var37 = false;
            } else {
               int var38 = var18 < 0L ? -1 : 1;
            }

            if (var17) {
               return 10 + var16;
            }
         }

         long var29 = 59440461288916L;
         if (59440461288916L == 0L) {
            boolean var39 = false;
         } else {
            int var40 = var29 < 0L ? -1 : 1;
         }

         for (int var21 : new int[]{2, 0, 1}) {
            float[] var22 = this.q(var21, var5);
            long var23 = 59440461288917L;
            if (59440461288917L == 0L) {
               boolean var41 = false;
            } else {
               int var42 = var23 < 0L ? -1 : 1;
            }

            boolean var25 = c(var1, var2, var22[0], var22[1], var22[2], var7, var22[3]);
            long var26 = 59440461288916L;
            if (59440461288916L == 0L) {
               boolean var43 = false;
            } else {
               int var44 = var26 < 0L ? -1 : 1;
            }

            if (var25) {
               return -1 - var21;
            }
         }

         long var31 = 59440461288916L;
         if (59440461288916L == 0L) {
            boolean var45 = false;
         } else {
            int var46 = var31 < 0L ? -1 : 1;
         }

         return Integer.MIN_VALUE;
      }
   }

   public boolean contains(float var1, float var2) {
      long var3 = 473484199753L;
      long var5 = var3 ^ 59793231254172L;
      return this.b(var1, var2, var5) != Integer.MIN_VALUE;
   }

   public boolean mouseDown(float var1, float var2, int var3) {
      long var4 = 59198030212831L;
      long var6 = var4 ^ 186859002616L;
      long var8 = var4 ^ 4228699093770L;
      long var10 = var4 ^ 103107795050672L;
      long var12 = var4 ^ 65251717387565L;
      long var14 = var4 ^ 56917320082831L;
      long var16 = var4 ^ 83053781906942L;
      int var18 = (int)((var4 ^ 83053781906942L) >>> 32);
      int var19 = (int)((var4 ^ 83053781906942L) << 32 >>> 48);
      int var20 = (int)(var16 << 48 >>> 48);
      int var21 = this.b(var1, var2, var8);
      if (var3 == 0 && var21 != Integer.MIN_VALUE) {
         long var22 = System.nanoTime();
         if (var21 < 0) {
            int var27 = -1 - var21;
            this.V.previewMouse(var27);
            this.S[var27] = var22 + 140000000L;
            float[] var28 = this.q(var27, var12);
            this.o(var28[0] + var28[2] / 2.0F, var28[1] + var28[3] / 2.0F, var10, this.q * 1.4F, u[this.V.style().ordinal()].W, var22);
            if (var27 == 2) {
               this.E.target(this.E.target() + 0.34F);
            }

            return true;
         } else {
            if (var21 < 10) {
               this.V.C.set(G[var21].F);
               var21 = 10 + this.m(var6, 65);
            }

            int var24 = var21 - 10;
            CreamyKeysRenderer.TypeX var25 = this.I(var24, var14);
            CreamyKeysRenderer.TypeT var26 = u[this.V.style().ordinal()];
            this.V.preview(this.V.style(), var25.key());
            this.c[var24] = var22 + 140000000L;
            this.T(var24, var25, var18, (char)var19, (char)var20, var26, var22);
            return true;
         }
      } else {
         return false;
      }
   }

   private CreamyKeysRenderer.TypeX I(int var1, long var2) {
      CreamyKeysModule.ensureSourceClassInitialized();
      CreamyKeysRenderer.TypeX[][] var4 = m;
      int var5 = var4.length;

      for (int var6 = 0; var6 < var5; var6++) {
         long var7 = 6678760835919L;
         if (6678760835919L == 0L) {
            boolean var10000 = false;
         } else {
            int var16 = var7 < 0L ? -1 : 1;
         }

         CreamyKeysRenderer.TypeX[] var10 = var4[var6];
         long var11 = 6678760835919L;
         if (6678760835919L == 0L) {
            boolean var17 = false;
         } else {
            int var18 = var11 < 0L ? -1 : 1;
         }

         long var14 = 6678760835920L;
         if (6678760835920L == 0L) {
            boolean var19 = false;
         } else {
            int var20 = var14 < 0L ? -1 : 1;
         }

         if (var1 < var10.length) {
            return var10[var1];
         }

         var1 -= var10.length;
      }

      return m[0][0];
   }

   private int m(long var1, int var3) {
      CreamyKeysModule.ensureSourceClassInitialized();
      int var4 = 0;
      CreamyKeysRenderer.TypeX[][] var5 = m;
      int var6 = var5.length;
      int var7 = 0;

      while (var7 < var6) {
         CreamyKeysRenderer.TypeX[] var8 = var5[var7];

         for (CreamyKeysRenderer.TypeX var12 : var8) {
            long var13 = 59359067763751L;
            if (59359067763751L == 0L) {
               boolean var10000 = false;
            } else {
               int var21 = var13 < 0L ? -1 : 1;
            }

            int var16 = var12.key();
            long var17 = 59359067763751L;
            if (59359067763751L == 0L) {
               boolean var22 = false;
            } else {
               int var23 = var17 < 0L ? -1 : 1;
            }

            if (var16 == 65) {
               return var4;
            }

            var4++;
         }

         var7++;
         long var20 = 59359067763750L;
         if (59359067763750L == 0L) {
            boolean var24 = false;
         } else {
            int var25 = var20 < 0L ? -1 : 1;
         }
      }

      return 0;
   }

   private static boolean c(float var0, float var1, float var2, float var3, float var4, long var5, float var7) {
      CreamyKeysModule.ensureSourceClassInitialized();
      long var8 = 83196188545015L;
      if (83196188545015L == 0L) {
         boolean var10000 = false;
      } else {
         int var11 = var8 < 0L ? -1 : 1;
      }

      return var0 >= var2 && var0 < var2 + var4 && var1 >= var3 && var1 < var3 + var7;
   }

   private static CreamyKeysRenderer.TypeX[][] P(long var0) {
      long var2 = 118336974770205L;
      ArrayList var4 = new ArrayList();
      ArrayList var5 = new ArrayList();
      ArrayList var6 = new ArrayList();
      ArrayList var7 = new ArrayList();
      var4.add(new CreamyKeysRenderer.TypeX("Tab", 1.5F, 258, 1));
      char[] var8 = "QWERTYUIOP".toCharArray();
      int var9 = var8.length;
      int var10 = 0;

      while (var10 < var9) {
         char var11 = var8[var10];
         long var12 = 19565708920243L;
         if (19565708920243L == 0L) {
            boolean var10000 = false;
         } else {
            int var28 = var12 < 0L ? -1 : 1;
         }

         var4.add(n(var2, var11));
         var10++;
         long var15 = 19565708920243L;
         if (19565708920243L == 0L) {
            boolean var29 = false;
         } else {
            int var30 = var15 < 0L ? -1 : 1;
         }
      }

      var4.add(new CreamyKeysRenderer.TypeX("[", 1.0F, 91, 0));
      var4.add(new CreamyKeysRenderer.TypeX("]", 1.0F, 93, 0));
      var4.add(new CreamyKeysRenderer.TypeX("\\", 1.5F, 92, 1));
      var5.add(new CreamyKeysRenderer.TypeX("Caps", 1.75F, 280, 1));
      var8 = "ASDFGHJKL".toCharArray();
      var9 = var8.length;
      int var24 = 0;

      while (var24 < var9) {
         char var25 = var8[var24];
         var5.add(n(var2, var25));
         long var13 = 19565708920242L;
         if (19565708920242L == 0L) {
            boolean var31 = false;
         } else {
            int var32 = var13 < 0L ? -1 : 1;
         }

         var24++;
         long var16 = 19565708920243L;
         if (19565708920243L == 0L) {
            boolean var33 = false;
         } else {
            int var34 = var16 < 0L ? -1 : 1;
         }
      }

      var5.add(new CreamyKeysRenderer.TypeX(";", 1.0F, 59, 0));
      var5.add(new CreamyKeysRenderer.TypeX("'", 1.0F, 39, 0));
      var5.add(new CreamyKeysRenderer.TypeX("Enter", 2.25F, 257, 2));
      var6.add(new CreamyKeysRenderer.TypeX("Shift", 2.25F, 340, 1));
      var8 = "ZXCVBNM".toCharArray();
      var9 = var8.length;
      int var26 = 0;

      while (var26 < var9) {
         char var27 = var8[var26];
         long var14 = 19565708920243L;
         if (19565708920243L == 0L) {
            boolean var35 = false;
         } else {
            int var36 = var14 < 0L ? -1 : 1;
         }

         char var17 = (char)var27;
         var6.add(n(var2, var17));
         var26++;
         long var18 = 19565708920243L;
         if (19565708920243L == 0L) {
            boolean var37 = false;
         } else {
            int var38 = var18 < 0L ? -1 : 1;
         }
      }

      var6.add(new CreamyKeysRenderer.TypeX(",", 1.0F, 44, 0));
      var6.add(new CreamyKeysRenderer.TypeX(".", 1.0F, 46, 0));
      var6.add(new CreamyKeysRenderer.TypeX("/", 1.0F, 47, 0));
      var6.add(new CreamyKeysRenderer.TypeX("Shift", 2.75F, 344, 1));
      var7.add(new CreamyKeysRenderer.TypeX("Ctrl", 1.5F, 341, 1));
      var7.add(new CreamyKeysRenderer.TypeX("Alt", 1.5F, 342, 1));
      var7.add(new CreamyKeysRenderer.TypeX("", 9.0F, 32, 0));
      var7.add(new CreamyKeysRenderer.TypeX("Alt", 1.5F, 346, 1));
      var7.add(new CreamyKeysRenderer.TypeX("Ctrl", 1.5F, 345, 1));
      return new CreamyKeysRenderer.TypeX[][]{
         (TypeX[])(var4.toArray(CreamyKeysRenderer.TypeX[]::new)),
         (TypeX[])(var5.toArray(CreamyKeysRenderer.TypeX[]::new)),
         (TypeX[])(var6.toArray(CreamyKeysRenderer.TypeX[]::new)),
         (TypeX[])(var7.toArray(CreamyKeysRenderer.TypeX[]::new))
      };
   }

   private static CreamyKeysRenderer.TypeX n(long var0, char var2) {
      return new CreamyKeysRenderer.TypeX(String.valueOf(var2), 1.0F, 65 + (var2 - 'A'), 0);
   }

   private static int M(long var0) {
      int var2 = 0;
      CreamyKeysRenderer.TypeX[][] var3 = m;
      int var4 = var3.length;
      int var5 = 0;

      while (var5 < var4) {
         CreamyKeysRenderer.TypeX[] var6 = var3[var5];
         long var7 = 38458533443984L;
         if (38458533443984L == 0L) {
            boolean var10000 = false;
         } else {
            int var13 = var7 < 0L ? -1 : 1;
         }

         var2 += var6.length;
         var5++;
         long var10 = 38458533443985L;
         if (38458533443985L == 0L) {
            boolean var14 = false;
         } else {
            int var15 = var10 < 0L ? -1 : 1;
         }
      }

      return var2;
   }

private static long b(int var0, long var1) {
      byte var3 = 2;
      if (ab[var3] == null) {
         ab[var3] = Y[var3] ^ 79731757760576098L;
      }

      return ab[var3];
   }

private record TypeT(int B, int i, int b, int e, int h, int u, int W) {
      CreamyKeysRenderer.TypeT mix(CreamyKeysRenderer.TypeT var1, float var2) {
         return new CreamyKeysRenderer.TypeT(
            BlendMode.lerp(this.B, var1.B, var2),
            BlendMode.lerp(this.i, var1.i, var2),
            BlendMode.lerp(this.b, var1.b, var2),
            BlendMode.lerp(this.e, var1.e, var2),
            BlendMode.lerp(this.h, var1.h, var2),
            BlendMode.lerp(this.u, var1.u, var2),
            BlendMode.lerp(this.W, var1.W, var2)
         );
      }

      public int alpha() {
         return this.B;
      }

      public int mod() {
         return this.i;
      }

      public int accent() {
         return this.b;
      }

      public int legend() {
         return this.e;
      }

      public int accentLegend() {
         return this.h;
      }

      public int shell() {
         return this.u;
      }

      public int glow() {
         return this.W;
      }

      public int integerState32() {
         return this.B;
      }

      public int integerState35() {
         return this.i;
      }

      public int integerState44() {
         return this.b;
      }

      public int integerState25() {
         return this.e;
      }

      public int integerState30() {
         return this.h;
      }

      public int integerState24() {
         return this.u;
      }

      public int integerState6() {
         return this.W;
      }
   }

   private record TypeV(float k, float V, float c, int I, long N) {
      public float x() {
         return this.k;
      }

      public float y() {
         return this.V;
      }

      public float size() {
         return this.c;
      }

      public int color() {
         return this.I;
      }

      public long born() {
         return this.N;
      }

      public float floatState58() {
         return this.k;
      }

      public float floatState15() {
         return this.V;
      }

      public float floatState25() {
         return this.c;
      }

      public int integerState29() {
         return this.I;
      }

      public long longState6() {
         return this.N;
      }
   }

   private record TypeX(String c, float Z, int C, int l) {
      public String label() {
         return this.c;
      }

      public float units() {
         return this.Z;
      }

      public int key() {
         return this.C;
      }

      public int role() {
         return this.l;
      }

      public String stringState11() {
         return this.c;
      }

      public float floatState43() {
         return this.Z;
      }

      public int integerState50() {
         return this.C;
      }

      public int integerState20() {
         return this.l;
      }
   }
}
