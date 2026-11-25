package dev.purxiy.modules.regionmap;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.BooleanSetting;
import dev.purxiy.hud.HudBounds;
import dev.purxiy.hud.HudGroupBounds;
import dev.purxiy.hud.HudModule;
import dev.purxiy.i18n.Translations;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.Locale;
import net.minecraft.class_1937;
import net.minecraft.class_3532;
import net.minecraft.class_5321;
import net.minecraft.class_638;

public final class RegionMapHud extends HudModule {
   private static final float I = 8.0F;
   private static final float H = 24.0F;
   private static final float x = 18.0F;
   private static final float s = 14.0F;
   private static final float g = 2.2F;
   private static final float l = 0.7F;
   private static final float A = 16.2F;
   private static final float w = 3.0F;
   private static final float k = 143.6F;
   private static final float e = 149.6F;
   private static final float P = 165.6F;
   private static final float z = 10.5F;
   private static final int n = -11910301;
   private static final int R = -14936271;
   private static final double i = 96400.0;
   private static final double U = 41800.0;
   private static boolean y;
   private static double j = Double.NaN;
   private static double T;
   private static class_5321<class_1937> Q;
   private final BooleanSetting G;
   private final BooleanSetting M;
   private final BooleanSetting d;
   private double c;
   private double N;
   private float S;
   private boolean L;
   private long Y;
   private class_638 t;
   private boolean p;
   private final Animator J;
   private final Animator B;
   private final Animator f;
   private final Animator Z;
   private final Animator X;
   private final Animator u;
   private final RegionMapHud.TypeI C;
   private final RegionMapHud.TypeI h;
   private float a;
   private RegionMapSupport.Data o;
   private float b;
   private float r;
   private final RegionMapHud.TypeI E;
   public static boolean W;
   private static final Integer[] D = new Integer[18];

   public RegionMapHud() {
      super("region_map", "Region Map", "Donut SMP's regions, and which one you are in", "earth", 0.0, 0.36);
      long var1 = 44469385322699L;
      long var3 = var1 ^ 14609215144785L;
      this.G = this.bool("numbers", "Region numbers", "The number of every region on the map", true);
      this.M = this.bool("legend", "Legend", "Which colour is which server location", true);
      this.d = this.bool("next", "Next region", "The region you are heading for and how far away it is", true);
      this.L = true;
      this.Y = -1L;
      this.J = new Animator(0.0F, 90.0F, 19.0F);
      this.B = new Animator(0.0F, 90.0F, 19.0F);
      this.f = new Animator(0.0F, 90.0F, 19.0F);
      this.Z = new Animator(0.0F, 90.0F, 19.0F);
      this.X = Animator.smooth(1.0F);
      this.u = Animator.smooth(1.0F);
      this.C = new RegionMapHud.TypeI(var3);
      this.h = new RegionMapHud.TypeI(var3);
      this.a = 1.0F;
      this.E = new RegionMapHud.TypeI(var3);
   }

   protected float cornerRadius() {
      return 9.0F;
   }

   protected void onEnable() {
      this.p = false;
      this.C.N = "";
   }

   protected void measure(HudBounds var1, float[] var2) {
      var2[0] = 165.6F;
      var2[1] = this.J() + (this.M.enabled() ? 22.0F : 0.0F) + 8.0F - 3.0F;
   }

   private float E() {
      return 36.0F;
   }

   private float J() {
      return this.E() + 149.6F + 7.0F;
   }

   private void i(int var1, long var2, HudBounds var4) {
      long var5 = 126161800943057L;
      long var7 = var5 ^ 99432773489460L;
      int var9 = (int)((var5 ^ 99432773489460L) >>> 32);
      int var10 = (int)((var5 ^ 99432773489460L) << 32 >>> 48);
      int var11 = (int)(var7 << 48 >>> 48);
      RegionMapSupport.TypeB.ensureSourceClassInitialized();
      long var12 = RenderPipelines.get().frame();
      if (var12 != this.Y) {
         long var14 = 1431590352L;
         int var17 = var14 < 0L ? -1 : 1;
         this.Y = var12;
         if (var4.level != this.t) {
            this.t = var4.level;
            this.p = false;
         }

         short var17x = 0;
         float var18 = MotionStyle.PANEL.dt(var4.dt);
         class_5321 var19 = class_1937.field_25179;
         long var20 = 1431590352L;
         int var42 = var20 < 0L ? -1 : 1;
         if (var4.live()) {
            float var23 = var4.partialTick;
            this.c = class_3532.method_16436(var23, var4.player.field_6038, var4.player.method_23317());
            this.N = class_3532.method_16436(var23, var4.player.field_5989, var4.player.method_23321());
            this.S = var4.player.method_5705(var23);
            class_5321 var24 = var4.level.method_27983();
            var19 = var24;
            var17x = 29374;
            if (!Double.isNaN(j)) {
               this.c = j;
               this.N = T;
            }

            if (Q != null) {
               var19 = Q;
            }
         } else {
            this.c = 96400.0 + 900.0 * Math.sin(var4.time * 0.13);
            this.N = 41800.0;
            this.S = 215.0F + 20.0F * (float)Math.sin(var4.time * 0.4F);
         }

         this.L = var19 == class_1937.field_25179;
         var42 = RegionMapSupport.cell(this.c);
         int var43 = RegionMapSupport.cell(this.N);
         int var25 = RegionMapSupport.id(var42, var43);
         boolean var26 = this.L && var25 >= 0;
         RegionMapHud.TypeI var27 = this.R(var19, var42, var43, var25, var9, (short)var10, (short)var11);
         if (!var27.N.equals(this.C.N)) {
            long var28 = 1431590353L;
            int var28x = var28 < 0L ? -1 : 1;
            if (this.C.N.isEmpty()) {
               this.C.set(var27);
            } else {
               this.h.set(this.C);
               this.C.set(var27);
               this.a = 0.0F;
            }
         }

         this.a = Math.min(1.0F, this.a + var18 / 0.38F);
         float var44 = (float)class_3532.method_15350(RegionMapSupport.grid(this.c), 0.0, 9.0);
         float var29 = (float)class_3532.method_15350(RegionMapSupport.grid(this.N), 0.0, 9.0);
         int var30 = class_3532.method_15340(var42, 0, 8);
         int var31 = class_3532.method_15340(var43, 0, 8);
         long var32 = 1431590353L;
         int var32x = var32 < 0L ? -1 : 1;
         long var35x = 1431590353L;
         int var38 = var35x < 0L ? -1 : 1;
         if (this.p && this.L) {
            this.J.target(var44).update(var18);
            this.B.target(var29).update(var18);
            this.f.target(var30).update(var18);
            this.Z.target(var31).update(var18);
         } else {
            this.J.snap(var44);
            this.B.snap(var29);
            this.f.snap(var30);
            this.Z.snap(var31);
            this.p = this.L;
         }

         Animator var38x = this.X.target(var26 ? 1.0F : 0.0F);
         var38x.update(var18);
         this.u.target(this.L ? 1.0F : 0.0F).update(var18);
         double var39 = Math.toRadians(this.S);
         this.o = this.L ? RegionMapSupport.ahead(this.c, this.N, -Math.sin(var39), Math.cos(var39)) : null;
      }
   }

   private RegionMapHud.TypeI R(class_5321 var1, int var2, int var3, int var4, int var5, short var6, short var7) {
      RegionMapSupport.TypeB.ensureSourceClassInitialized();
      RegionMapHud.TypeI var8 = this.E;
      if (var1 != class_1937.field_25179) {
         var8.N = "dim:" + var1.method_29177();
         var8.k = var1 == class_1937.field_25180 ? "Nether" : (var1 == class_1937.field_25181 ? "The End" : "Not mapped");
         var8.F = "Regions are in the overworld";
         var8.w = false;
         var8.K = "map-pin-off";
         var8.r = Theme.tint(-11910301);
      } else if (var4 < 0) {
         var8.N = "off";
         var8.k = "Off the map";
         var8.F = "No region out here";
         var8.w = false;
         var8.K = "compass";
         var8.r = Theme.tint(-11910301);
      } else {
         RegionMapSupport.TypeB var9 = RegionMapSupport.host(var2, var3);
         var8.N = "region:" + var4;
         var8.k = "Region " + var4;
         var8.F = var9.t;
         var8.w = true;
         var8.K = "map-pin";
         var8.r = var9.A;
      }

      return var8;
   }

   protected void render(Canvas var1, HudBounds var2) {
      long var3 = 109202412650187L;
      long var5 = var3 ^ 103116733401676L;
      int var7 = (int)((var3 ^ 103116733401676L) >>> 56);
      int var8 = (int)((var3 ^ 103116733401676L) << 8 >>> 32);
      int var9 = (int)(var5 << 40 >>> 40);
      int var10 = (int)((var3 ^ 19722462030618L) >>> 32);
      long var11 = (var3 ^ 19722462030618L) << 32 >>> 32;
      long var13 = var3 ^ 113979295023851L;
      long var15 = var3 ^ 17180544078775L;
      this.i(var10, var11, var2);
      float var17 = this.contentWidth();
      float var18 = this.contentHeight();
      this.panel(var1, 0.0F, 0.0F, var17, var18, this.cornerRadius());
      this.x(var1, var13);
      this.m(var1, var15);
      if (this.M.enabled()) {
         this.J(var1, (byte)var7, var8, var9);
      }
   }

   private void x(Canvas var1, long var2) {
      long var4 = 117006667229310L;
      RegionMapSupport.TypeB.ensureSourceClassInitialized();
      float var6 = 19.0F;
      float var7 = 8.0F;
      float var8 = var6 - 9.0F;
      float var9 = Easing.STANDARD.apply(this.a);
      int var10 = BlendMode.lerp(this.h.r, this.C.r, var9);
      int var11 = BlendMode.lerp(var10, Icons.INDIGO, 0.45F);
      var1.glow(var7, var8, 18.0F, 18.0F, 5.5F, 8.0F, BlendMode.withAlpha(var10, 0.3F));
      var1.roundRectGradient(var7, var8, 18.0F, 18.0F, 5.5F, 135.0F, var10, var11);
      var1.roundRectOutline(var7, var8, 18.0F, 18.0F, 5.5F, 1.0F, 822083583);
      float var12 = var7 + 18.0F + 7.0F;
      if (var9 < 1.0F) {
         var1.pushAlpha(1.0F - var9);
         this.D(var1, this.h, var7, var4, var6, var12, -5.0F * var9);
         var1.popAlpha();
      }

      var1.pushAlpha(var9);
      this.D(var1, this.C, var7, var4, var6, var12, 5.0F * (1.0F - var9));
      var1.popAlpha();
      RegionMapSupport.Data var13 = this.o;
      if (this.d.enabled() && var13 != null) {
         float var14 = 157.6F;
         int var15 = var13.id();
         long var16 = 5465151372319L;
         int var19 = var16 < 0L ? -1 : 1;
         String var19x = var15 >= 0 ? Integer.toString(var13.id()) : "Map edge";
         float var20 = this.text(var1, Fonts.SEMIBOLD, var19x, var14, var6 - 4.4F, 8.4F, Icons.TEXT, FontStyle.RIGHT, FontWeight.MIDDLE);
         HudGroupBounds.arrow(var1, var14 - var20 - 5.5F, var6 - 4.4F, HudGroupBounds.facing(this.S, true), 0.62F, Icons.TEXT_2);
         this.text(var1, Fonts.MEDIUM, "in " + distance(var13.distance()), var14, var6 + 5.4F, 6.4F, Icons.TEXT_2, FontStyle.RIGHT, FontWeight.MIDDLE);
      }
   }

   private void D(Canvas var1, RegionMapHud.TypeI var2, float var3, long var4, float var6, float var7, float var8) {
      RegionMapSupport.TypeB.ensureSourceClassInitialized();
      this.icon(var1, var2.K, 17.0F, 19.0F + var8 * 0.6F, 9.5F, -218103809);
      long var9 = 117006667229309L;
      int var11 = var9 < 0L ? -1 : 1;
      this.text(var1, Fonts.SEMIBOLD, var2.k, var7, 14.6F + var8, 8.6F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
      if (var2.w) {
         Translations.pause();
      }

      this.text(var1, Fonts.MEDIUM, var2.F, var7, 24.4F + var8, 6.6F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
      if (var2.w) {
         Translations.resume();
      }
   }

   private void m(Canvas var1, long var2) {
      long var4 = 117400981285239L;
      float var6 = this.E();
      var1.roundRect(8.0F, var6, 149.6F, 149.6F, 7.0F, 855638016);
      var1.roundRectOutline(8.0F, var6, 149.6F, 149.6F, 7.0F, 1.0F, 234881023);
      float var7 = 11.0F;
      float var8 = var6 + 3.0F;
      RegionMapSupport.TypeB.ensureSourceClassInitialized();
      float var9 = this.X.value();
      float var10 = this.u.value();
      float var11 = this.f.value();
      float var12 = this.Z.value();
      float var13 = var7 + this.J.value() * 16.2F - 1.1F;
      float var14 = var8 + this.B.value() * 16.2F - 1.1F;
      this.b = var13;
      this.r = var14;
      var1.pushAlpha(0.45F + 0.55F * var10);

      for (int var15 = 0; var15 < 9; var15++) {
         long var16 = 119784544513403L;
         int var27 = var16 < 0L ? -1 : 1;

         for (int var19 = 0; var19 < 9; var19++) {
            float var20 = var9 * Math.max(0.0F, 1.0F - Math.abs(var19 - var11)) * Math.max(0.0F, 1.0F - Math.abs(var15 - var12));
            long var21 = 119784544513404L;
            int var24 = var21 < 0L ? -1 : 1;
            this.Q(var1, var7, var8, var4, var19, var15, var20);
         }

         long var27x = 119784544513403L;
         int var30 = var27x < 0L ? -1 : 1;
      }

      long var26 = 119784544513404L;
      int var18 = var26 < 0L ? -1 : 1;
      long var18x = 119784544513403L;
      int var281 = var18x < 0L ? -1 : 1;
      if (var9 > 0.01F) {
         float var28x = var7 + var11 * 16.2F;
         float var22 = var8 + var12 * 16.2F;
         RegionMapSupport.TypeB var23 = RegionMapSupport.host(Math.round(var11), Math.round(var12));
         int var24 = var23 != null ? var23.A : Theme.tint(-11910301);
         var1.glow(var28x, var22, 14.0F, 14.0F, 4.0F, 9.0F, BlendMode.withAlpha(var24, 0.45F * var9));
         var1.roundRectOutline(var28x - 1.2F, var22 - 1.2F, 16.4F, 16.4F, 4.6F, 1.2F, BlendMode.withAlpha(-1, 0.92F * var9));
         float var25 = HudGroupBounds.facing(this.S, true);
         var1.pushAlpha(var9);
         var1.softCircle(var13, var14 + 0.4F, 4.2F, 5.0F, -1728053248);
         HudGroupBounds.arrow(var1, var13, var14, var25, 1.0F, Theme.tint(-15593954));
         HudGroupBounds.arrow(var1, var13, var14, var25, 0.72F, Icons.TEXT);
         var1.popAlpha();
      }

      var1.popAlpha();
   }

   private void Q(Canvas var1, float var2, float var3, long var4, int var6, int var7, float var8) {
      RegionMapSupport.TypeB.ensureSourceClassInitialized();
      RegionMapSupport.TypeB var9 = RegionMapSupport.host(var6, var7);
      if (var9 != null) {
         long var10 = 117400981285238L;
         int var13 = var10 < 0L ? -1 : 1;
         boolean var13x = RegionMapSupport.host(var6 - 1, var7) == var9;
         long var14 = 117400981285238L;
         int var16 = var14 < 0L ? -1 : 1;
         boolean var16x = RegionMapSupport.host(var6 + 1, var7) == var9;
         long var17 = 117400981285238L;
         int var20 = var17 < 0L ? -1 : 1;
         boolean var20x = RegionMapSupport.host(var6, var7 - 1) == var9;
         long var21 = 117400981285239L;
         int var21x = var21 < 0L ? -1 : 1;
         boolean var23x = RegionMapSupport.host(var6, var7 + 1) == var9;
         float var24 = 0.75F;
         long var25 = 117400981285238L;
         int var25x = var25 < 0L ? -1 : 1;
         float var27x = 11.0F + var6 * 16.2F - (var13x ? var24 : 0.0F);
         long var28 = 117400981285239L;
         int var28x = var28 < 0L ? -1 : 1;
         float var30x = var3 + var7 * 16.2F - (var20x ? var24 : 0.0F);
         long var31 = 117400981285238L;
         int var31x = var31 < 0L ? -1 : 1;
         float var33x = 11.0F + var6 * 16.2F + 14.0F + (var16x ? var24 : 0.0F);
         long var34 = 117400981285239L;
         int var34x = var34 < 0L ? -1 : 1;
         float var36x = var3 + var7 * 16.2F + 14.0F + (var23x ? var24 : 0.0F);
         float var37 = 4.0F;
         float var38 = 1.2F;
         float var39 = !var13x && !var20x ? var37 : var38;
         float var40 = !var16x && !var20x ? var37 : var38;
         float var41 = !var16x && !var23x ? var37 : var38;
         float var42 = !var13x && !var23x ? var37 : var38;
         int var43 = BlendMode.lerp(BlendMode.lerp(Theme.tint(-14936271), var9.A, 0.55F), var9.A, var8);
         var1.roundRect(var27x, var30x, var33x - var27x, var36x - var30x, var39, var40, var41, var42, var43);
         float var44;
         int var45 = (var44 = var8 - 0.01F) == 0.0F ? 0 : (var44 < 0.0F ? -1 : 1);
         long var46 = 117400981285239L;
         int var46x = var46 < 0L ? -1 : 1;
         if (var45 > 0) {
            var1.roundRectGradient(
               var27x, var30x, var33x - var27x, var36x - var30x, Math.min(var39, var40), 90.0F, BlendMode.withAlpha(-1, 0.22F * var8), 16777215
            );
         }

         short var48x = (short)(this.G.enabled() ? 1 : 0);
         long var49 = 117400981285238L;
         int var49x = var49 < 0L ? -1 : 1;
         if (var48x != 0) {
            String var51x = RegionMapSupport.number(var6, var7);
            float var52 = var51x.length() >= 3 ? 5.0F : 5.8F;
            var48x = 22244;
            float var53 = 11.0F + var6 * 16.2F + 7.0F;
            float var54 = var3 + var7 * 16.2F + 7.0F;
            float var55 = (float)Math.hypot(var53 - this.b, var54 - this.r);
            float var56 = 1.0F - this.X.value() * (1.0F - class_3532.method_15363((var55 - 3.5F) / 4.5F, 0.0F, 1.0F));
            int var57 = BlendMode.fade(BlendMode.lerp(-1275068417, Theme.tint(-267186654), var8), var56);
            var1.text(Fonts.SEMIBOLD, var51x, var53, var54 + 0.2F, var52, var57, FontStyle.CENTER, FontWeight.MIDDLE);
         }
      }
   }

   private void J(Canvas var1, byte var2, int var3, int var4) {
      RegionMapSupport.TypeB[] var5 = RegionMapSupport.TypeB.values();
      RegionMapSupport.TypeB.ensureSourceClassInitialized();
      float var6 = this.J();
      float var7 = 49.86667F;
      RegionMapSupport.TypeB var8 = this.X.target() > 0.5F ? RegionMapSupport.host(RegionMapSupport.cell(this.c), RegionMapSupport.cell(this.N)) : null;

      for (int var9 = 0; var9 < var5.length; var9++) {
         RegionMapSupport.TypeB var10 = var5[var9];
         float var11 = 11.0F + var9 / 2 * var7;
         float var12 = var6 + var9 % 2 * 10.5F + 5.25F;
         boolean var13 = var10 == var8;
         if (var13) {
            var1.ring(var11 + 2.6F, var12, 4.1F, 1.0F, BlendMode.withAlpha(var10.A, 0.75F));
         }

         var1.circle(var11 + 2.6F, var12, 2.6F, var10.A);
         Translations.pause();
         this.text(var1, Fonts.MEDIUM, var10.t, var11 + 9.0F, var12 + 0.2F, 6.4F, var13 ? Icons.TEXT : Icons.TEXT_3, FontStyle.LEFT, FontWeight.MIDDLE);
         Translations.resume();
      }
   }

   static String distance(double var0) {
      long var2 = Math.max(10L, Math.round(var0 / 10.0) * 10L);
      if (var2 < 1000L) {
         return var2 + " m";
      } else {
         double var4 = var0 / 1000.0;
         return var4 < 9.95 ? String.format(Locale.ROOT, "%.1f km", var4) : Math.round(var4) + " km";
      }
   }

   private static final class TypeI {
      String N = "";
      String k = "";
      String F = "";
      boolean w;
      String K = "map-pin";
      int r = Theme.tint(-11910301);
      private static final String a = "map-pin";
      private static final long b = -2390144361023519901L;

      private TypeI(long var1) {
      }

      void set(RegionMapHud.TypeI var1) {
         this.N = var1.N;
         this.k = var1.k;
         this.F = var1.F;
         this.w = var1.w;
         this.K = var1.K;
         this.r = var1.r;
      }
   }
}
