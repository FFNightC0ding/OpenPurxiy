// Sourced By DevFFNightmare
package dev.purxiy.modules.roles;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.textures.FilterMode;
import com.mojang.blaze3d.textures.GpuTextureView;
import dev.purxiy.anim.Easing;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.class_1044;
import net.minecraft.class_12137;
import net.minecraft.class_2960;
import net.minecraft.class_310;

public final class RolesRenderer3 {
   public static final float e = 9.0F;
   public static final float Z = 7.0F;
   public static final float N = 8.0F;
   public static final int n = 4;
   public static final float z = 118.0F;
   private static final float t = 17.0F;
   private static final float i = 6.0F;
   private static final float V = 27.0F;
   private static final float U = 8.0F;
   private static final float Q = 3.0F;
   private static final float M = 7.0F;
   private static final float O = 2.0F;
   private static final float x = 7.5F;
   private static final float d = 15.0F;
   private static final float X = 5.5F;
   private static final float L = 12.0F;
   private static final float B = 7.5F;
   private static final float S = 13.5F;
   private static final int j = -11654;
   private static final int K = -41852;
   private static final int r = -30116;
   private static final int P = -1711290648;
   private static final String a = " ♥";
   private static final long[] b = new long[]{
      8580572062946421335L,
      -969473750140522587L,
      -230712387385827433L,
      5570784512536284151L,
      262973344584804612L,
      7335643985840354277L,
      -5497259980932413198L,
      -8706236970977444443L,
      2433708285968875082L,
      8321531381715031011L,
      -3644318719154784470L,
      -3088505056706043077L,
      4027608202660461186L,
      -5739116204372955147L,
      -4127395896992561434L,
      -2439158635889946696L,
      7638999708064901604L,
      4249242148530442236L,
      2952378666377280027L,
      6603936654664143566L,
      -5852888872604144995L,
      -7915864297716720542L,
      2631064161532754817L,
      3771964522652292714L,
      -8827253929809154420L,
      3324021616601542317L,
      -6159873616090576008L,
      -3488004692295627279L,
      -7785914537075018879L,
      -2102750516848239211L,
      7810812556344251661L,
      -5976616912503683668L,
      -2548078752871922872L,
      5421128147229802634L
   };
   private static final Integer[] c = new Integer[34];

   private RolesRenderer3() {
   }

   public static float measure(RolesRenderer3.TypeL var0) {
      long var1 = 72499763833084L;
      long var3 = var1 ^ 130539072854176L;
      long var5 = var1 ^ 73838115093405L;
      boolean var7 = var0.s != null;
      float var8 = var7 ? 27.0F : 17.0F;
      float var9 = I(var5, var0, var8);
      float var10 = var0.t.U + j(var3, var0);
      if (var7) {
         var10 = Math.max(var10, var0.s.U);
      }

      var0.r = var8;
      var0.d = Math.max(var8, var9 + var10 + 7.0F);
      return var0.d;
   }

   public static float height(RolesRenderer3.TypeL var0) {
      return var0.r;
   }

   public static void draw(Canvas var0, RolesRenderer3.TypeL var1, RolesRenderer3.TypeE var2, float var3) {
      long var4 = 114954045414311L;
      long var6 = var4 ^ 104582158208137L;
      long var8 = var4 ^ 18210339840013L;
      long var10 = var4 ^ 57214083387728L;
      long var12 = var4 ^ 116940135680198L;
      long var14 = var4 ^ 119952293977954L;
      long var16 = var4 ^ 108372017073547L;
      int var18 = (int)((var4 ^ 108372017073547L) >>> 48);
      int var19 = (int)((var4 ^ 108372017073547L) << 16 >>> 32);
      int var20 = (int)(var16 << 48 >>> 48);
      var16 = var4 ^ 69184349593628L;
      int var21 = (int)((var4 ^ 69184349593628L) >>> 48);
      int var22 = (int)((var4 ^ 69184349593628L) << 16 >>> 32);
      int var23 = (int)(var16 << 48 >>> 48);
      boolean var24 = var1.s != null;
      float var25 = var1.d;
      float var26 = var1.r;
      float var27 = var24 ? 8.0F : 6.0F;
      boolean var28 = var2 == RolesRenderer3.TypeE.o;
      boolean var29 = var1.J == 1;
      var0.push();
      var0.translate(-var25 / 2.0F, -var26);
      if (!var28) {
         S(var0, var25, var26, var27, var6, var2, var3);
      }

      if (var1.U > 0.01F) {
         float var30 = var1.U * var1.U;
         if (var28) {
            var0.glow(0.0F, 0.0F, var25, var26, var27, 12.0F, BlendMode.withAlpha(Icons.PINK, 0.16F * var30));
         } else {
            var0.glow(0.0F, 0.0F, var25, var26, var27, 12.0F, BlendMode.withAlpha(Icons.PINK, 0.2F * var30));
            var0.roundRectOutline(0.0F, 0.0F, var25, var26, var27, 1.0F, BlendMode.withAlpha(-41852, 0.7F * var30));
         }
      }

      float var35 = I(var12, var1, var26);
      float var31 = var24 ? 9.5F : var26 / 2.0F - (var29 && !var28 ? 0.75F : 0.0F);
      if (var1.j != null || var1.B != null) {
         float var32 = var26 - 6.0F;
         float var33 = (var24 ? 8.0F : 6.0F) - 3.0F;
         if (var1.j != null) {
            B(var0, var1.j, 3.0F, 3.0F, var32, var33, var28, var14);
         } else {
            v(var0, var1.B, 3.0F, 3.0F, var32, var33, var10, var28);
         }
      }

      if (var28) {
         var1.t.drawShadow(var0, var35, var31, 1.0F);
      }

      var1.t.draw(var0, var35, var31);
      if (var24) {
         float var36 = 19.0F;
         if (var28) {
            var1.s.drawShadow(var0, var35, var36, 1.0F);
         }

         var1.s.draw(var0, var35, var36);
      }

      if (var1.J == 2) {
         n(var0, var1, var35 + var1.t.U + 6.0F, (char)var18, var31, var28, var19, (short)var20);
      } else if (var29) {
         if (var28) {
            P(var0, var1, var35, var31 + Fonts.SEMIBOLD.capHeight * 9.0F / 2.0F + 3.0F, var1.t.U, var8);
         } else {
            e(var0, var1, (short)var21, var25, var22, var26, var27, (char)var23);
         }
      }

      var0.pop();
   }

   private static float I(long var0, RolesRenderer3.TypeL var2, float var3) {
      RolesRegistry.ensureSourceClassInitialized();
      if (var2.j == null && var2.B == null) {
         return 7.0F;
      } else {
         boolean var4 = var2.s != null;
         return 3.0F + (var3 - 6.0F) + (var4 ? 6 : 5);
      }
   }

   private static float j(long var0, RolesRenderer3.TypeL var2) {
      return var2.J != 2 ? 0.0F : 6.0F + Fonts.SEMIBOLD.width(RolesSupport3.hearts(var2.O), 7.5F, 0.0F) + 2.0F + Fonts.SEMIBOLD.width("♥", 7.5F, 0.0F);
   }

   private static void n(Canvas var0, RolesRenderer3.TypeL var1, float var2, char var3, float var4, boolean var5, int var6, short var7) {
      String var8 = RolesSupport3.hearts(var1.O);
      RolesRegistry.ensureSourceClassInitialized();
      float var9 = Fonts.SEMIBOLD.width(var8, 7.5F, 0.0F);
      int var10 = var1.m ? -11654 : (var1.W ? -41852 : Icons.PINK);
      if (var5) {
         var0.textGlow(Fonts.SEMIBOLD, var8 + " ♥", var2, var4 + 0.5F, 7.5F, -1090519040, FontStyle.LEFT, FontWeight.MIDDLE, 0.0F, 0.6F, 1.6F);
      }

      var0.text(Fonts.SEMIBOLD, var8, var2, var4, 7.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      var0.text(Fonts.SEMIBOLD, "♥", var2 + var9 + 2.0F, var4, 7.5F, var10, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private static void e(Canvas var0, RolesRenderer3.TypeL var1, short var2, float var3, int var4, float var5, float var6, char var7) {
      long var8 = 94974336973755L;
      long var10 = var8 ^ 106284969932145L;
      int var12 = (int)((var8 ^ 106284969932145L) >>> 48);
      int var13 = (int)((var8 ^ 106284969932145L) << 16 >>> 48);
      int var14 = (int)(var10 << 32 >>> 32);
      float var15 = var5 - 2.0F;
      var0.pushClip(0.0F, 0.0F, var3, var5, var6);
      var0.rect(0.0F, var15, var3, 2.0F, 452984831);
      x(var0, var1, 0.0F, (char)var12, var15, var3, (short)var13, var14, 2.0F, 0.0F);
      var0.popClip();
   }

   private static void P(Canvas var0, RolesRenderer3.TypeL var1, float var2, float var3, float var4, long var5) {
      long var7 = 60243534843594L;
      byte var9 = 0;
      short var10 = 14026;
      int var11 = (int)(var7 << 32 >>> 32);
      float var12 = 1.6F;
      var0.shadow(var2, var3, var4, var12, var12 / 2.0F, 3.0F, 0.0F, 0.0F, 0.5F, 1711276032);
      var0.roundRect(var2, var3, var4, var12, var12 / 2.0F, 1090519039);
      x(var0, var1, var2, (char)var9, var3, var4, (short)var10, var11, var12, var12 / 2.0F);
   }

   private static void x(Canvas var0, RolesRenderer3.TypeL var1, float var2, char var3, float var4, float var5, short var6, int var7, float var8, float var9) {
      float var10 = RolesSupport3.clamp01(var1.T);
      RolesRegistry.ensureSourceClassInitialized();
      float var11 = RolesSupport3.clamp01(var1.a);
      if (var11 > var10 + 0.003F) {
         var0.roundRect(var2, var4, var5 * var11, var8, var9, Theme.tint(-1711290648));
      }

      if (var10 > 0.002F) {
         float var12 = Math.max(var8, var5 * var10);
         var0.roundRectGradient(var2, var4, var12, var8, var9, 0.0F, var1.W ? Icons.PINK : Icons.INDIGO, var1.W ? -30116 : Icons.MAGENTA);
         var0.roundRectCorners(var2, var4, var12, var8, var9, 956301311, 956301311, 16777215, 16777215);
      }

      float var14 = RolesSupport3.clamp01(var1.i);
      if (var14 > 0.003F) {
         float var13 = var2 + var5 * Math.min(var10, 1.0F - var14);
         var0.roundRect(var13, var4, var5 * var14, var8, var9, -11654);
      }
   }

   private static void B(Canvas var0, class_2960 var1, float var2, float var3, float var4, float var5, boolean var6, long var7) {
      RolesRegistry.ensureSourceClassInitialized();
      var0.shadow(3.0F, 3.0F, var4, var4, var5, var6 ? 6.0F : 4.0F, 0.0F, 0.0F, 1.0F, var6 ? 1711276032 : 1073741824);
      long var9 = 6134334588100L;
      if (6134334588100L == 0L) {
         boolean var10000 = false;
      } else {
         int var14 = var9 < 0L ? -1 : 1;
      }

      long var12 = 6134334588100L;
      if (6134334588100L == 0L) {
         boolean var15 = false;
      } else {
         int var16 = var12 < 0L ? -1 : 1;
      }

      if (!A(var0, var1, 3.0F, 3.0F, var4, var5)) {
         var0.roundRectGradient(3.0F, 3.0F, var4, var4, var5, 135.0F, BlendMode.withAlpha(Icons.INDIGO, 0.6F), BlendMode.withAlpha(Icons.MAGENTA, 0.5F));
      }

      var0.roundRectOutline(3.0F, 3.0F, var4, var4, var5, 1.0F, 788529151);
   }

   private static void v(Canvas var0, String var1, float var2, float var3, float var4, float var5, long var6, boolean var8) {
      if (var8) {
         var0.shadow(3.0F, 3.0F, var4, var4, var5, 6.0F, 0.0F, 0.0F, 1.0F, 1426063360);
      }

      var0.roundRectGradient(3.0F, 3.0F, var4, var4, var5, 135.0F, BlendMode.withAlpha(Icons.INDIGO, 0.62F), BlendMode.withAlpha(Icons.MAGENTA, 0.5F));
      var0.roundRectOutline(3.0F, 3.0F, var4, var4, var5, 1.0F, 788529151);
      var0.icon(var1, 3.0F + var4 / 2.0F, 3.0F + var4 / 2.0F, var4 * 0.62F, Icons.TEXT);
   }

   private static boolean A(Canvas var0, class_2960 var1, float var2, float var3, float var4, float var5) {
      try {
         class_1044 var6 = class_310.method_1551().method_1531().method_4619(var1);
         GpuTextureView var7 = var6.method_71659();
         class_12137 var8 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
         var0.image(var7, var8, false, 3.0F, 3.0F, var4, var4, 0.125F, 0.125F, 0.25F, 0.25F, var5, -1);
         var0.image(var7, var8, false, 3.0F, 3.0F, var4, var4, 0.625F, 0.125F, 0.75F, 0.25F, var5, -1);
         return true;
      } catch (IllegalStateException var9) {
         return false;
      }
   }

   public static float measure(RolesRenderer3.TypeD var0) {
      long var1 = 133701674991992L;
      long var3 = var1 ^ 43131960756391L;
      int var5 = (int)((var1 ^ 43131960756391L) >>> 32);
      int var6 = (int)((var1 ^ 43131960756391L) << 32 >>> 48);
      int var7 = (int)(var3 << 48 >>> 48);
      if (g(var5, var6, var7, var0)) {
         RolesRenderer3.TypeD.TypeA var12 = var0.r.getFirst();
         float var13 = var12.d.isEmpty() ? 0.0F : 4.5F + var12.amountWidth();
         var0.F = 15.0F;
         var0.k = 13.5F + var12.k.U + var13 + 7.0F;
         return var0.k;
      } else {
         float var8 = 0.0F;

         for (RolesRenderer3.TypeD.TypeA var10 : var0.r) {
            float var11 = var10.d.isEmpty() ? 0.0F : 10.0F + var10.amountWidth();
            var8 = Math.max(var8, var10.k.U + var11);
         }

         if (var0.R > 0) {
            var8 = Math.max(var8, Fonts.MEDIUM.width(var0.moreText(), 7.0F, 0.0F));
         }

         var0.F = 9.0F + var0.r.size() * 12.0F + (var0.R > 0 ? 11.0F : 0.0F);
         var0.k = Math.max(64.0F, 13.5F + var8 + 7.0F);
         return var0.k;
      }
   }

   public static float height(RolesRenderer3.TypeD var0) {
      return var0.F;
   }

   public static void draw(Canvas var0, RolesRenderer3.TypeD var1, RolesRenderer3.TypeE var2, float var3, float var4) {
      long var5 = 70894432780844L;
      long var7 = var5 ^ 33920788761587L;
      int var9 = (int)((var5 ^ 33920788761587L) >>> 32);
      int var10 = (int)((var5 ^ 33920788761587L) << 32 >>> 48);
      int var11 = (int)(var7 << 48 >>> 48);
      long var12 = var5 ^ 131852678632706L;
      long var14 = var5 ^ 3912480728311L;
      var7 = var5 ^ 139404553605442L;
      int var16 = (int)((var5 ^ 139404553605442L) >>> 32);
      int var17 = (int)((var5 ^ 139404553605442L) << 32 >>> 48);
      int var18 = (int)(var7 << 48 >>> 48);
      boolean var19 = var2 == RolesRenderer3.TypeE.o;
      boolean var20 = g(var9, var10, var11, var1);
      float var21 = var1.k;
      float var22 = var1.F;
      float var23 = var20 ? 5.5F : 7.0F;
      boolean var24 = false;

      for (RolesRenderer3.TypeD.TypeA var26 : var1.r) {
         var24 |= var26.n;
      }

      var0.push();
      var0.translate(-var21 / 2.0F, -var22);
      if (!var19) {
         S(var0, var21, var22, var23, var12, var2, var3);
         if (var24) {
            A(var0, var16, (char)var17, var21, var22, var23, var4, var18);
         }
      }

      float var32 = var20 ? var22 / 2.0F : 10.5F;

      for (RolesRenderer3.TypeD.TypeA var27 : var1.r) {
         o(var0, 7.5F, var32, var27, var14, var19, var4);
         if (var19) {
            var27.k.drawShadow(var0, 13.5F, var32, 1.0F);
         }

         var27.k.draw(var0, 13.5F, var32);
         if (!var27.d.isEmpty()) {
            float var28 = 7.5F;
            float var29 = var20 ? 13.5F + var27.k.U + 4.5F : var21 - 7.0F;
            FontStyle var30 = var20 ? FontStyle.LEFT : FontStyle.RIGHT;
            if (var19) {
               var0.textGlow(Fonts.SEMIBOLD, var27.d, var29, var32 + 0.5F, var28, -1090519040, var30, FontWeight.MIDDLE, 0.0F, 0.6F, 1.6F);
            }

            var0.text(Fonts.SEMIBOLD, var27.d, var29, var32, var28, Icons.TEXT_2, var30, FontWeight.MIDDLE);
         }

         var32 += 12.0F;
      }

      if (var1.R > 0) {
         float var34 = 7.0F;
         float var35 = var32 - 0.5F;
         if (var19) {
            var0.textGlow(Fonts.MEDIUM, var1.moreText(), 13.5F, var35 + 0.5F, var34, -1090519040, FontStyle.LEFT, FontWeight.MIDDLE, 0.0F, 0.6F, 1.6F);
         }

         var0.text(Fonts.MEDIUM, var1.moreText(), 13.5F, var35, var34, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      var0.pop();
   }

   private static boolean g(int var0, int var1, int var2, RolesRenderer3.TypeD var3) {
      RolesRegistry.ensureSourceClassInitialized();
      return var3.r.size() == 1 && var3.R == 0;
   }

   private static void o(Canvas var0, float var1, float var2, RolesRenderer3.TypeD.TypeA var3, long var4, boolean var6, float var7) {
      RolesRegistry.ensureSourceClassInitialized();
      if (var6) {
         var0.softCircle(7.5F, var2, 2.5F, 4.0F, Integer.MIN_VALUE);
      }

      if (var3.n) {
         float var8 = 0.5F + 0.5F * (float)Math.sin(var7 * 2.2F);
         var0.glowCircle(7.5F, var2, 2.2F, 5.0F, BlendMode.withAlpha(Icons.PURPLE, 0.35F + 0.25F * var8));
      }

      var0.circle(7.5F, var2, 2.1F, var3.c);
      var0.circle(6.9F, var2 - 0.6F, 0.8F, 1509949439);
   }

   private static void A(Canvas var0, int var1, char var2, float var3, float var4, float var5, float var6, int var7) {
      RolesRegistry.ensureSourceClassInitialized();
      var0.roundRectOutlineGradient(0.0F, 0.0F, var3, var4, var5, 1.0F, 0.0F, BlendMode.withAlpha(Icons.PURPLE, 0.55F), BlendMode.withAlpha(Icons.PINK, 0.3F));
      float var8 = var6 % 3.2F / 1.2F;
      if (var8 < 1.0F) {
         float var9 = -16.0F + (var3 + 32.0F) * Easing.SINE_IN_OUT.apply(var8);
         var0.pushClip(0.0F, 0.0F, var3, var4, var5);
         var0.push();
         var0.translate(var9, var4 / 2.0F);
         var0.rotate(0.35F);
         var0.glow(-5.0F, -var4, 10.0F, var4 * 2.0F, 5.0F, 12.0F, BlendMode.withAlpha(Theme.tint(-1451777), 0.16F * (float)Math.sin(Math.PI * var8)));
         var0.pop();
         var0.popClip();
      }
   }

   private static void S(Canvas var0, float var1, float var2, float var3, long var4, RolesRenderer3.TypeE var6, float var7) {
      RolesRegistry.ensureSourceClassInitialized();
      if (!(var7 <= 0.01F)) {
         long var8 = 61096940082989L;
         if (61096940082989L == 0L) {
            boolean var10000 = false;
         } else {
            int var15 = var8 < 0L ? -1 : 1;
         }

         var0.shadow(0.0F, 0.0F, var1, var2, var3, 9.0F, 0.0F, 0.0F, 1.5F, BlendMode.withAlpha(-16777216, 0.24F * var7));
         if (var6 == RolesRenderer3.TypeE.G) {
            int var11 = Theme.tint(-16119788);
            long var12 = 61096940082990L;
            if (61096940082990L == 0L) {
               boolean var16 = false;
            } else {
               int var17 = var12 < 0L ? -1 : 1;
            }

            var0.glass(0.0F, 0.0F, var1, var2, var3, BlendMode.withAlpha(var11, 0.62F), var7, 1.15F, 0.012F);
         } else {
            var0.roundRect(0.0F, 0.0F, var1, var2, var3, BlendMode.withAlpha(Theme.tint(-16053995), 0.86F * var7));
         }

         var0.roundRectCorners(0.0F, 0.0F, var1, var2, var3, BlendMode.fade(352321535, var7), BlendMode.fade(184549375, var7), 16777215, 16777215);
         var0.roundRectOutlineGradient(0.0F, 0.0F, var1, var2, var3, 1.0F, 90.0F, BlendMode.fade(654311423, var7), BlendMode.fade(150994943, var7));
      }
   }

public static final class TypeD {
      public final List<RolesRenderer3.TypeD.TypeA> r = new ArrayList<>(4);
      public int R;
      float k;
      float F;
      private int e = -1;
      private String a = "";
      private static final String b = " more";

      String moreText() {
         if (this.e != this.R) {
            this.e = this.R;
            this.a = "+" + this.R + b;
         }

         return this.a;
      }

public static final class TypeA {
         public RolesRenderer2 k;
         public String d = "";
         public int c = -4936494;
         public boolean n;
         private float O = -1.0F;

         float amountWidth() {
            if (this.O < 0.0F) {
               this.O = this.d.isEmpty() ? 0.0F : Fonts.SEMIBOLD.width(this.d, 7.5F, 0.0F);
            }

            return this.O;
         }
      }
   }

   public static enum TypeE {
      G,
      y,
      o;

}

   public static final class TypeL {
      public RolesRenderer2 t;
      
      public RolesRenderer2 s;
      
      public class_2960 j;
      
      public String B;
      public int J;
      public float T;
      public float a;
      public float i;
      public float O;
      public boolean m;
      public float U;
      public boolean W;
      float d;
      float r;

      public float width() {
         return this.d;
      }
   }
}
