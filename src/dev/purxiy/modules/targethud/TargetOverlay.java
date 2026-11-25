package dev.purxiy.modules.targethud;

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

public final class TargetOverlay {
   public static final float Q = 9.0F;
   public static final float Y = 7.0F;
   public static final float J = 8.0F;
   public static final int f = 4;
   public static final float E = 118.0F;
   private static final float i = 17.0F;
   private static final float w = 6.0F;
   private static final float W = 27.0F;
   private static final float U = 8.0F;
   private static final float P = 3.0F;
   private static final float s = 7.0F;
   private static final float C = 2.0F;
   private static final float u = 7.5F;
   private static final float q = 15.0F;
   private static final float o = 5.5F;
   private static final float O = 12.0F;
   private static final float j = 7.5F;
   private static final float Z = 13.5F;
   private static final int H = -11654;
   private static final int h = -41852;
   private static final int l = -30116;
   private static final int g = -1711290648;
   private static final String a = " ♥";
   private static final long[] b = new long[]{
      -8022326197285004056L,
      -3741421639709126787L,
      785855939417565509L,
      -6956940862208568061L,
      3844703337891374187L,
      5571289620115684404L,
      -7554933604726712441L,
      -1534552564410499226L,
      -5833589457742347823L,
      550412270875620993L,
      -5955790737045904384L,
      5056878025087218253L,
      -8906189585808283262L,
      6543908789745674931L,
      -8976940224569809469L,
      5593680336060454911L,
      -3832365700125122538L,
      355296340688897174L,
      -7323538437226232517L,
      -4736097928024328385L,
      8053082412699706348L,
      3965397467726883348L,
      1803042066041446428L,
      3155635029181569614L,
      -7248981176777944924L,
      3102743978941881148L,
      573751655425077589L,
      -2619277033363863160L,
      -5417960697434752773L,
      -8014336902208753175L,
      7827079177117886122L,
      5888249352356249481L,
      4815627488364258651L,
      3375694932635558170L
   };

   private TargetOverlay() {
   }

   public static float measure(TargetOverlay.TargetPlateOverlay var0) {
      long var1 = 139825324827855L;
      long var3 = var1 ^ 31230932487897L;
      int var5 = (int)((var1 ^ 31230932487897L) >>> 32);
      int var6 = (int)((var1 ^ 31230932487897L) << 32 >>> 56);
      int var7 = (int)(var3 << 40 >>> 40);
      long var8 = var1 ^ 69414686679573L;
      boolean var10 = var0.m != null;
      float var11 = var10 ? 27.0F : 17.0F;
      float var12 = Y(var8, var0, var11);
      float var13 = var0.h.u + f(var5, var0, (byte)var6, var7);
      if (var10) {
         var13 = Math.max(var13, var0.m.u);
      }

      var0.y = var11;
      var0.F = Math.max(var11, var12 + var13 + 7.0F);
      return var0.F;
   }

   public static float height(TargetOverlay.TargetPlateOverlay var0) {
      return var0.y;
   }

   public static void draw(Canvas var0, TargetOverlay.TargetPlateOverlay var1, TargetOverlay.TargetAlign var2, float var3) {
      long var4 = 40162803268805L;
      long var6 = var4 ^ 37199514127786L;
      long var8 = var4 ^ 111645693159509L;
      int var10 = (int)((var4 ^ 111645693159509L) >>> 56);
      int var11 = (int)((var4 ^ 111645693159509L) << 8 >>> 32);
      int var12 = (int)(var8 << 40 >>> 40);
      long var13 = var4 ^ 99899619290991L;
      long var15 = var4 ^ 12807712376442L;
      long var17 = var4 ^ 99357980100384L;
      long var19 = var4 ^ 110558413322783L;
      long var21 = (var4 ^ 71595431167505L) >>> 32;
      int var23 = (int)((var4 ^ 71595431167505L) << 32 >>> 32);
      boolean var24 = var1.m != null;
      float var25 = var1.F;
      float var26 = var1.y;
      float var27 = var24 ? 8.0F : 6.0F;
      boolean var28 = var2 == TargetOverlay.TargetAlign.F;
      boolean var29 = var1.R == 1;
      var0.push();
      var0.translate(-var25 / 2.0F, -var26);
      if (!var28) {
         Z(var0, var25, var26, var27, var2, var17, var3);
      }

      if (var1.d > 0.01F) {
         float var30 = var1.d * var1.d;
         if (var28) {
            var0.glow(0.0F, 0.0F, var25, var26, var27, 12.0F, BlendMode.withAlpha(Icons.PINK, 0.16F * var30));
         } else {
            var0.glow(0.0F, 0.0F, var25, var26, var27, 12.0F, BlendMode.withAlpha(Icons.PINK, 0.2F * var30));
            var0.roundRectOutline(0.0F, 0.0F, var25, var26, var27, 1.0F, BlendMode.withAlpha(-41852, 0.7F * var30));
         }
      }

      float var34 = Y(var19, var1, var26);
      float var31 = var24 ? 9.5F : var26 / 2.0F - (var29 && !var28 ? 0.75F : 0.0F);
      if (var1.P != null || var1.X != null) {
         float var32 = var26 - 6.0F;
         float var33 = (var24 ? 8.0F : 6.0F) - 3.0F;
         if (var1.P != null) {
            g(var0, var1.P, 3.0F, var21, 3.0F, var32, var33, var23, var28);
         } else {
            I(var0, var1.X, 3.0F, 3.0F, var32, var33, var13, var28);
         }
      }

      if (var28) {
         var1.h.drawShadow(var0, var34, var31, 1.0F);
      }

      var1.h.draw(var0, var34, var31);
      if (var24) {
         float var35 = 19.0F;
         if (var28) {
            var1.m.drawShadow(var0, var34, var35, 1.0F);
         }

         var1.m.draw(var0, var34, var35);
      }

      if (var1.R == 2) {
         n(var0, (byte)var10, var1, var11, var34 + var1.h.u + 6.0F, var31, var28, var12);
      } else if (var29) {
         if (var28) {
            z(var6, var0, var1, var34, var31 + Fonts.SEMIBOLD.capHeight * 9.0F / 2.0F + 3.0F, var1.h.u);
         } else {
            I(var0, var15, var1, var25, var26, var27);
         }
      }

      var0.pop();
   }

   private static float Y(long var0, TargetOverlay.TargetPlateOverlay var2, float var3) {
      TargetHud.ensureSourceClassInitialized();
      if (var2.P == null && var2.X == null) {
         return 7.0F;
      } else {
         boolean var4 = var2.m != null;
         return 3.0F + (var3 - 6.0F) + (var4 ? 6 : 5);
      }
   }

   private static float f(int var0, TargetOverlay.TargetPlateOverlay var1, byte var2, int var3) {
      return var1.R != 2 ? 0.0F : 6.0F + Fonts.SEMIBOLD.width(TargetHealth.hearts(var1.D), 7.5F, 0.0F) + 2.0F + Fonts.SEMIBOLD.width("♥", 7.5F, 0.0F);
   }

   private static void n(Canvas var0, byte var1, TargetOverlay.TargetPlateOverlay var2, int var3, float var4, float var5, boolean var6, int var7) {
      TargetHud.ensureSourceClassInitialized();
      String var8 = TargetHealth.hearts(var2.D);
      float var9 = Fonts.SEMIBOLD.width(var8, 7.5F, 0.0F);
      int var10 = 8295568;
      int var11 = var2.Z ? -11654 : (var2.e ? -41852 : Icons.PINK);
      var10 = 4263336;
      if (var6) {
         var0.textGlow(Fonts.SEMIBOLD, var8 + " ♥", var4, var5 + 0.5F, 7.5F, -1090519040, FontStyle.LEFT, FontWeight.MIDDLE, 0.0F, 0.6F, 1.6F);
      }

      var0.text(Fonts.SEMIBOLD, var8, var4, var5, 7.5F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      var0.text(Fonts.SEMIBOLD, "♥", var4 + var9 + 2.0F, var5, 7.5F, var11, FontStyle.LEFT, FontWeight.MIDDLE);
   }

   private static void I(Canvas var0, long var1, TargetOverlay.TargetPlateOverlay var3, float var4, float var5, float var6) {
      long var7 = 68179222622472L;
      float var9 = var5 - 2.0F;
      var0.pushClip(0.0F, 0.0F, var4, var5, var6);
      var0.rect(0.0F, var9, var4, 2.0F, 452984831);
      Z(var7, var0, var3, 0.0F, var9, var4, 2.0F, 0.0F);
      var0.popClip();
   }

   private static void z(long var0, Canvas var2, TargetOverlay.TargetPlateOverlay var3, float var4, float var5, float var6) {
      long var7 = 68179222622472L;
      float var9 = 1.6F;
      var2.shadow(var4, var5, var6, var9, var9 / 2.0F, 3.0F, 0.0F, 0.0F, 0.5F, 1711276032);
      var2.roundRect(var4, var5, var6, var9, var9 / 2.0F, 1090519039);
      Z(var7, var2, var3, var4, var5, var6, var9, var9 / 2.0F);
   }

   private static void Z(long var0, Canvas var2, TargetOverlay.TargetPlateOverlay var3, float var4, float var5, float var6, float var7, float var8) {
      TargetHud.ensureSourceClassInitialized();
      float var9 = TargetHealth.clamp01(var3.G);
      float var10 = TargetHealth.clamp01(var3.t);
      if (var10 > var9 + 0.003F) {
         var2.roundRect(var4, var5, var6 * var10, var7, var8, Theme.tint(-1711290648));
      }

      if (var9 > 0.002F) {
         float var11 = Math.max(var7, var6 * var9);
         var2.roundRectGradient(var4, var5, var11, var7, var8, 0.0F, var3.e ? Icons.PINK : Icons.INDIGO, var3.e ? -30116 : Icons.MAGENTA);
         var2.roundRectCorners(var4, var5, var11, var7, var8, 956301311, 956301311, 16777215, 16777215);
      }

      float var16 = TargetHealth.clamp01(var3.z);
      long var12 = 68179222622472L;
      int var15 = var12 < 0L ? -1 : 1;
      if (var16 > 0.003F) {
         float var15x = var4 + var6 * Math.min(var9, 1.0F - var16);
         var2.roundRect(var15x, var5, var6 * var16, var7, var8, -11654);
      }
   }

   private static void g(Canvas var0, class_2960 var1, float var2, long var3, float var5, float var6, float var7, int var8, boolean var9) {
      TargetHud.ensureSourceClassInitialized();
      var0.shadow(3.0F, 3.0F, var6, var6, var7, var9 ? 6.0F : 4.0F, 0.0F, 0.0F, 1.0F, var9 ? 1711276032 : 1073741824);
      int var10 = -1113507116;
      long var11 = 26010L;
      int var13 = var11 < 0L ? -1 : 1;
      if (!k(var0, var1, 3.0F, 3.0F, var6, var7)) {
         var0.roundRectGradient(3.0F, 3.0F, var6, var6, var7, 135.0F, BlendMode.withAlpha(Icons.INDIGO, 0.6F), BlendMode.withAlpha(Icons.MAGENTA, 0.5F));
      }

      var0.roundRectOutline(3.0F, 3.0F, var6, var6, var7, 1.0F, 788529151);
   }

   private static void I(Canvas var0, String var1, float var2, float var3, float var4, float var5, long var6, boolean var8) {
      if (var8) {
         var0.shadow(3.0F, 3.0F, var4, var4, var5, 6.0F, 0.0F, 0.0F, 1.0F, 1426063360);
      }

      var0.roundRectGradient(3.0F, 3.0F, var4, var4, var5, 135.0F, BlendMode.withAlpha(Icons.INDIGO, 0.62F), BlendMode.withAlpha(Icons.MAGENTA, 0.5F));
      var0.roundRectOutline(3.0F, 3.0F, var4, var4, var5, 1.0F, 788529151);
      var0.icon(var1, 3.0F + var4 / 2.0F, 3.0F + var4 / 2.0F, var4 * 0.62F, Icons.TEXT);
   }

   private static boolean k(Canvas var0, class_2960 var1, float var2, float var3, float var4, float var5) {
      try {
         class_1044 var6 = class_310.method_1551().method_1531().method_4619(var1);
         GpuTextureView var7 = var6.method_71659();
         class_12137 var8 = RenderSystem.getSamplerCache().method_75294(FilterMode.NEAREST);
         var0.image(var7, var8, false, 3.0F, 3.0F, var4, var4, 0.125F, 0.125F, 0.25F, 0.25F, var5, -1);
         var0.image(var7, var8, false, 3.0F, 3.0F, var4, var4, 0.625F, 0.125F, 0.75F, 0.25F, var5, -1);
         return true;
      } catch (IllegalStateException var91) {
         return false;
      }
   }

   public static float measure(TargetOverlay.TargetGroup var0) {
      long var1 = 56160491156215L;
      long var3 = var1 ^ 58700903683551L;
      if (W(var0, var3)) {
         TargetOverlay.TargetGroup.TargetEntry var9 = var0.v.getFirst();
         float var10 = var9.F.isEmpty() ? 0.0F : 4.5F + var9.amountWidth();
         var0.i = 15.0F;
         var0.a = 13.5F + var9.C.u + var10 + 7.0F;
         return var0.a;
      } else {
         float var5 = 0.0F;

         for (TargetOverlay.TargetGroup.TargetEntry var7 : var0.v) {
            float var8 = var7.F.isEmpty() ? 0.0F : 10.0F + var7.amountWidth();
            var5 = Math.max(var5, var7.C.u + var8);
         }

         if (var0.k > 0) {
            var5 = Math.max(var5, Fonts.MEDIUM.width(var0.moreText(), 7.0F, 0.0F));
         }

         var0.i = 9.0F + var0.v.size() * 12.0F + (var0.k > 0 ? 11.0F : 0.0F);
         var0.a = Math.max(64.0F, 13.5F + var5 + 7.0F);
         return var0.a;
      }
   }

   public static float height(TargetOverlay.TargetGroup var0) {
      return var0.i;
   }

   public static void draw(Canvas var0, TargetOverlay.TargetGroup var1, TargetOverlay.TargetAlign var2, float var3, float var4) {
      long var5 = 53189077471804L;
      long var7 = var5 ^ 77607374025858L;
      int var9 = (int)((var5 ^ 77607374025858L) >>> 32);
      int var10 = (int)((var5 ^ 77607374025858L) << 32 >>> 48);
      int var11 = (int)(var7 << 48 >>> 48);
      long var12 = var5 ^ 59445311878420L;
      long var14 = var5 ^ 86563867503065L;
      long var16 = var5 ^ 52241754402928L;
      boolean var18 = var2 == TargetOverlay.TargetAlign.F;
      boolean var19 = W(var1, var12);
      float var20 = var1.a;
      float var21 = var1.i;
      float var22 = var19 ? 5.5F : 7.0F;
      boolean var23 = false;

      for (TargetOverlay.TargetGroup.TargetEntry var25 : var1.v) {
         var23 |= var25.v;
      }

      var0.push();
      var0.translate(-var20 / 2.0F, -var21);
      if (!var18) {
         Z(var0, var20, var21, var22, var2, var14, var3);
         if (var23) {
            t(var0, var20, var21, var22, var16, var4);
         }
      }

      float var30 = var19 ? var21 / 2.0F : 10.5F;

      for (TargetOverlay.TargetGroup.TargetEntry var26 : var1.v) {
         d(var0, var9, (char)var10, var11, 7.5F, var30, var26, var18, var4);
         if (var18) {
            var26.C.drawShadow(var0, 13.5F, var30, 1.0F);
         }

         var26.C.draw(var0, 13.5F, var30);
         if (!var26.F.isEmpty()) {
            float var27 = 7.5F;
            float var28 = var19 ? 13.5F + var26.C.u + 4.5F : var20 - 7.0F;
            FontStyle var29 = var19 ? FontStyle.LEFT : FontStyle.RIGHT;
            if (var18) {
               var0.textGlow(Fonts.SEMIBOLD, var26.F, var28, var30 + 0.5F, var27, -1090519040, var29, FontWeight.MIDDLE, 0.0F, 0.6F, 1.6F);
            }

            var0.text(Fonts.SEMIBOLD, var26.F, var28, var30, var27, Icons.TEXT_2, var29, FontWeight.MIDDLE);
         }

         var30 += 12.0F;
      }

      if (var1.k > 0) {
         float var32 = 7.0F;
         float var33 = var30 - 0.5F;
         if (var18) {
            var0.textGlow(Fonts.MEDIUM, var1.moreText(), 13.5F, var33 + 0.5F, var32, -1090519040, FontStyle.LEFT, FontWeight.MIDDLE, 0.0F, 0.6F, 1.6F);
         }

         var0.text(Fonts.MEDIUM, var1.moreText(), 13.5F, var33, var32, Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
      }

      var0.pop();
   }

   private static boolean W(TargetOverlay.TargetGroup var0, long var1) {
      TargetHud.ensureSourceClassInitialized();
      return var0.v.size() == 1 && var0.k == 0;
   }

   private static void d(Canvas var0, int var1, char var2, int var3, float var4, float var5, TargetOverlay.TargetGroup.TargetEntry var6, boolean var7, float var8) {
      TargetHud.ensureSourceClassInitialized();
      if (var7) {
         var0.softCircle(7.5F, var5, 2.5F, 4.0F, Integer.MIN_VALUE);
      }

      if (var6.v) {
         float var9 = 0.5F + 0.5F * (float)Math.sin(var8 * 2.2F);
         var0.glowCircle(7.5F, var5, 2.2F, 5.0F, BlendMode.withAlpha(Icons.PURPLE, 0.35F + 0.25F * var9));
      }

      var0.circle(7.5F, var5, 2.1F, var6.o);
      var0.circle(6.9F, var5 - 0.6F, 0.8F, 1509949439);
   }

   private static void t(Canvas var0, float var1, float var2, float var3, long var4, float var6) {
      var0.roundRectOutlineGradient(0.0F, 0.0F, var1, var2, var3, 1.0F, 0.0F, BlendMode.withAlpha(Icons.PURPLE, 0.55F), BlendMode.withAlpha(Icons.PINK, 0.3F));
      TargetHud.ensureSourceClassInitialized();
      float var7 = var6 % 3.2F / 1.2F;
      long var8 = 35061817090636L;
      int var11 = var8 < 0L ? -1 : 1;
      if (var7 < 1.0F) {
         float var11x = -16.0F + (var1 + 32.0F) * Easing.SINE_IN_OUT.apply(var7);
         var0.pushClip(0.0F, 0.0F, var1, var2, var3);
         var0.push();
         var0.translate(var11x, var2 / 2.0F);
         var0.rotate(0.35F);
         var0.glow(-5.0F, -var2, 10.0F, var2 * 2.0F, 5.0F, 12.0F, BlendMode.withAlpha(Theme.tint(-1451777), 0.16F * (float)Math.sin(Math.PI * var7)));
         var0.pop();
         var0.popClip();
      }
   }

   private static void Z(Canvas var0, float var1, float var2, float var3, TargetOverlay.TargetAlign var4, long var5, float var7) {
      TargetHud.ensureSourceClassInitialized();
      if (!(var7 <= 0.01F)) {
         long var8 = 139477796469733L;
         int var11 = var8 < 0L ? -1 : 1;
         var0.shadow(0.0F, 0.0F, var1, var2, var3, 9.0F, 0.0F, 0.0F, 1.5F, BlendMode.withAlpha(-16777216, 0.24F * var7));
         if (var4 == TargetOverlay.TargetAlign.O) {
            long var11x = 139477796469732L;
            int var12 = var11x < 0L ? -1 : 1;
            var0.glass(0.0F, 0.0F, var1, var2, var3, BlendMode.withAlpha(Theme.tint(-16119788), 0.62F), var7, 1.15F, 0.012F);
         } else {
            var0.roundRect(0.0F, 0.0F, var1, var2, var3, BlendMode.withAlpha(Theme.tint(-16053995), 0.86F * var7));
         }

         var0.roundRectCorners(0.0F, 0.0F, var1, var2, var3, BlendMode.fade(352321535, var7), BlendMode.fade(184549375, var7), 16777215, 16777215);
         var0.roundRectOutlineGradient(0.0F, 0.0F, var1, var2, var3, 1.0F, 90.0F, BlendMode.fade(654311423, var7), BlendMode.fade(150994943, var7));
      }
   }

   public static final class TargetPlateOverlay {
      public TargetPlate h;
      public TargetPlate m;
      public class_2960 P;
      public String X;
      public int R;
      public float G;
      public float t;
      public float z;
      public float D;
      public boolean Z;
      public float d;
      public boolean e;
      float F;
      float y;

      public float width() {
         return this.F;
      }
   }

   public static enum TargetAlign {
      O,
      w,
      F;
   }

   public static final class TargetGroup {
      public final List<TargetOverlay.TargetGroup.TargetEntry> v = new ArrayList<>(4);
      public int k;
      float a;
      float i;
      private int z = -1;
      private String M = "";
      private static final String b = " more";

      String moreText() {
         if (this.z != this.k) {
            this.z = this.k;
            this.M = "+" + this.k + " more";
         }

         return this.M;
      }

      public static final class TargetEntry {
         public TargetPlate C;
         public String F = "";
         public int o = -4936494;
         public boolean v;
         private float J = -1.0F;

         float amountWidth() {
            if (this.J < 0.0F) {
               this.J = this.F.isEmpty() ? 0.0F : Fonts.SEMIBOLD.width(this.F, 7.5F, 0.0F);
            }

            return this.J;
         }
      }
   }
}
