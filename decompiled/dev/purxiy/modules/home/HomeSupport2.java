package dev.purxiy.modules.home;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.i18n.Translations;
import dev.purxiy.loader.fabric.UiAudioPolicy;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.render.font.TrueTypeFont;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_310;
import net.minecraft.class_332;
import net.minecraft.class_339;
import net.minecraft.class_350;
import net.minecraft.class_4280;
import net.minecraft.class_437;
import net.minecraft.class_8021;
import org.joml.Matrix3x2fStack;

public final class HomeSupport2 {
   private static final Map<Object, HomeSupport2.TypeZ> F = new WeakHashMap<>();
   private static final ArrayDeque<HomeSupport2.TypeH> n = new ArrayDeque<>();
   private static final ArrayDeque<float[]> K = new ArrayDeque<>();
   private static class_437 B;
   private static class_437 d;
   private static class_332 X;
   private static int Z = 0;
   private static int C = 0;
   private static double y;
   private static float v;
   private static Object o;
   private static Object x;
   private static Object h;
   private static float D;
   private static float L;
   private static float s;
   private static float a;
   static final float H = 0.45F;
   public static boolean S;

   private HomeSupport2() {
   }

   public static boolean skins(class_437 var0) {
      if (var0 != null && HomeUpdater.z.Q.enabled() && class_310.method_1551().field_1687 == null) {
         String var1 = var0.getClass().getName();
         return var1.startsWith("net.minecraft.") || var1.startsWith("com.mojang.realmsclient.");
      } else {
         return false;
      }
   }

   public static boolean active() {
      return B != null;
   }

   public static void begin(class_437 var0, class_332 var1, int var2, int var3) {
      n.clear();
      K.clear();
      x = null;
      if (!skins(var0)) {
         B = null;
      } else {
         float var4 = HomeRenderer.screenClock();
         if (!Float.isNaN(var4)) {
            d = var0;
            y = i() - var4;
            v = 0.0F;
         } else if (var0 != d) {
            d = var0;
            y = i();
            v = 0.0F;
         }

         B = var0;
         X = var1;
         Z = var2;
         C = var3;
      }
   }

   public static void end(class_437 var0) {
      if (B != null) {
         if (x != null && x != o) {
            HomeUpdater.play(HomeKind.HOVER);
         }

         o = x;
      }

      B = null;
      X = null;
      n.clear();
      K.clear();
   }

   static float shown() {
      return (float)(i() - y) - v;
   }

   static void replayEntrance(float var0) {
      y = i();
      v = var0;
   }

   private static double i() {
      return System.nanoTime() / 1.0E9;
   }

   private static Canvas n() {
      return RenderPipelines.get().menuCanvas();
   }

   private static float v() {
      return RenderPipelines.get().delta();
   }

   public static void widgetBegin(class_339 var0, class_332 var1) {
      long var2 = 113280395122060L;
      long var4 = var2 ^ 3319402089397L;
      if (B != null) {
         float var6 = F(var0.method_46427(), var4);
         float var7 = (1.0F - var6) * 7.0F;
         boolean var8 = var7 > 0.01F;
         if (var8) {
            var1.method_51448().pushMatrix();
            var1.method_51448().translate(0.0F, var7);
         }

         float var9 = var0.method_75798();
         var0.method_25350(var9 * var6);
         n.push(new HomeSupport2.TypeH(var0, var7, var9, var8));
      }
   }

   public static void widgetEnd(class_339 var0, class_332 var1) {
      if (B != null && !n.isEmpty() && n.peek().j == var0) {
         HomeSupport2.TypeH var2 = n.pop();
         var0.method_25350(var2.K);
         if (var2.R) {
            var1.method_51448().popMatrix();
         }
      }
   }

   private static float F(float var0, long var1) {
      if (MotionStyle.reduced()) {
         return Math.min(1.0F, Math.max(0.0F, shown() / 0.2F));
      } else {
         class_437 var3 = B;
         float var4 = var3 != null ? Math.max(1, var3.field_22790) : 240.0F;
         float var5 = 0.02F + Math.max(0.0F, Math.min(1.0F, var0 / var4)) * MotionStyle.stagger(0.26F);
         float var6 = (shown() - var5) * MotionStyle.MENU.speed() / 0.46F;
         return Easing.EMPHASIZED.apply(Math.max(0.0F, Math.min(1.0F, var6)));
      }
   }

   private static class_339 F(long var0) {
      HomeSupport2.TypeH var2 = n.peek();
      return var2 == null ? null : var2.j;
   }

   private static HomeSupport2.TypeZ r(Object var0) {
      return F.computeIfAbsent(var0, var0x -> new HomeSupport2.TypeZ());
   }

   public static void scissor(class_332 var0, int var1, int var2, int var3, int var4) {
      if (B != null) {
         Matrix3x2fStack var5 = var0.method_51448();
         float var6 = var5.m00() * var1 + var5.m10() * var2 + var5.m20();
         float var7 = var5.m01() * var1 + var5.m11() * var2 + var5.m21();
         float var8 = var5.m00() * var3 + var5.m10() * var4 + var5.m20();
         float var9 = var5.m01() * var3 + var5.m11() * var4 + var5.m21();
         float[] var10 = new float[]{Math.min(var6, var8), Math.min(var7, var9), Math.max(var6, var8), Math.max(var7, var9)};
         float[] var11 = K.peek();
         if (var11 != null) {
            var10[0] = Math.max(var10[0], var11[0]);
            var10[1] = Math.max(var10[1], var11[1]);
            var10[2] = Math.min(var10[2], var11[2]);
            var10[3] = Math.min(var10[3], var11[3]);
         }

         K.push(var10);
      }
   }

   public static void unscissor() {
      if (B != null && !K.isEmpty()) {
         K.pop();
      }
   }

   private static void o(long var0, Canvas var2) {
      float[] var3 = K.peek();
      if (var3 != null) {
         var2.pushClip(var3[0], var3[1], var3[2] - var3[0], var3[3] - var3[1]);
      }

      var2.push();
      class_332 var4 = X;
      if (var4 != null) {
         Matrix3x2fStack var5 = var4.method_51448();
         long var6 = 24141339743832L;
         int var9 = var6 < 0L ? -1 : 1;
         var2.translate(var5.m20(), var5.m21());
         float var9x = var5.m00() - 1.0F;
         long var10 = 24141339743833L;
         int var11 = var10 < 0L ? -1 : 1;
         if (Math.abs(var9x) > 1.0E-4F || Math.abs(var5.m11() - 1.0F) > 1.0E-4F) {
            var2.scale(var5.m00(), var5.m11());
         }
      }
   }

   private static void l(char var0, short var1, int var2, Canvas var3) {
      var3.pop();
      if (K.peek() != null) {
         var3.popClip();
      }
   }

   public static boolean sprite(class_2960 var0, int var1, int var2, int var3, int var4, int var5) {
      long var6 = 89851334404996L;
      long var8 = var6 ^ 84466743120760L;
      int var10 = (int)((var6 ^ 117637110048759L) >>> 56);
      long var11 = (var6 ^ 117637110048759L) << 8 >>> 8;
      long var13 = var6 ^ 8485849768396L;
      long var15 = var6 ^ 140131364131711L;
      long var17 = var6 ^ 21012336096848L;
      int var19 = (int)((var6 ^ 21012336096848L) >>> 48);
      int var20 = (int)((var6 ^ 21012336096848L) << 16 >>> 48);
      int var21 = (int)(var17 << 32 >>> 32);
      long var22 = var6 ^ 75097287458269L;
      long var24 = var6 ^ 132274826147204L;
      long var26 = var6 ^ 90992606276442L;
      long var28 = var6 ^ 54276470574131L;
      if (B != null && "minecraft".equals(var0.method_12836())) {
         String var30 = var0.method_12832();
         float var31 = (var5 >>> 24) / 255.0F;
         Canvas var32 = n();
         switch (var30) {
            case "widget/button":
            case "widget/button_highlighted":
            case "widget/button_disabled":
               o(var22, var32);
               Q(var32, var1, var2, var8, var3, var4, var30.endsWith("_highlighted"), var30.endsWith("_disabled"), var31);
               l((char)var19, (short)var20, var21, var32);
               break;
            case "widget/text_field":
            case "widget/text_field_highlighted":
               o(var22, var32);
               W(var32, var1, var26, var2, var3, var4, var30.endsWith("_highlighted"), var31);
               l((char)var19, (short)var20, var21, var32);
               break;
            case "widget/slider":
            case "widget/slider_highlighted":
               o(var22, var32);
               i(var32, var1, var2, var3, var4, var30.endsWith("_highlighted"), var31, var15);
               l((char)var19, (short)var20, var21, var32);
               break;
            case "widget/slider_handle":
            case "widget/slider_handle_highlighted":
               o(var22, var32);
               Q(var32, var1, var2, var13, var3, var4, var30.endsWith("_highlighted"), var31);
               l((char)var19, (short)var20, var21, var32);
               break;
            case "widget/checkbox":
            case "widget/checkbox_highlighted":
            case "widget/checkbox_selected":
            case "widget/checkbox_selected_highlighted":
               o(var22, var32);
               F(var32, var1, var2, var3, var4, (byte)var10, var30.contains("_selected"), var11, var30.endsWith("_highlighted"), var31);
               l((char)var19, (short)var20, var21, var32);
               break;
            case "widget/tab":
            case "widget/tab_highlighted":
            case "widget/tab_selected":
            case "widget/tab_selected_highlighted":
               o(var22, var32);
               m(var32, var1, var28, var2, var3, var4, var30.contains("_selected"), var30.endsWith("_highlighted"), var31);
               l((char)var19, (short)var20, var21, var32);
               break;
            case "widget/scroller":
               o(var22, var32);
               var32.roundRect(var1 + var3 / 2.0F - 1.5F, var2 + 1, 3.0F, var4 - 2, 1.5F, BlendMode.withAlpha(Theme.tint(-1449217), 0.42F * var31));
               l((char)var19, (short)var20, var21, var32);
               break;
            case "widget/scroller_background":
               o(var22, var32);
               var32.roundRect(var1 + var3 / 2.0F - 1.5F, var2 + 1, 3.0F, var4 - 2, 1.5F, BlendMode.withAlpha(-1, 0.07F * var31));
               l((char)var19, (short)var20, var21, var32);
               break;
            case "icon/language":
               o(var32, "languages", var1, var2, var24, var3, var4, var31);
               break;
            case "icon/accessibility":
               o(var32, "accessibility", var1, var2, var24, var3, var4, var31);
               break;
            default:
               return false;
         }

         return true;
      } else {
         return false;
      }
   }

   public static boolean texture(class_2960 var0, int var1, int var2, int var3, int var4) {
      long var5 = 79444361665539L;
      long var7 = var5 ^ 11980291064279L;
      int var9 = (int)((var5 ^ 11980291064279L) >>> 48);
      int var10 = (int)((var5 ^ 11980291064279L) << 16 >>> 48);
      int var11 = (int)(var7 << 32 >>> 32);
      long var12 = var5 ^ 103035242941018L;
      if (B != null && "minecraft".equals(var0.method_12836())) {
         String var14 = var0.method_12832();
         switch (var14) {
            case "textures/gui/menu_background.png":
            case "textures/gui/menu_list_background.png":
            case "textures/gui/inworld_menu_background.png":
            case "textures/gui/inworld_menu_list_background.png":
            case "textures/gui/tab_header_background.png":
               return true;
            case "textures/gui/header_separator.png":
            case "textures/gui/footer_separator.png":
            case "textures/gui/inworld_header_separator.png":
            case "textures/gui/inworld_footer_separator.png":
               Canvas var16 = n();
               o(var12, var16);
               float var17 = var2 + var4 / 2.0F - 0.5F;
               float var18 = var3 / 2.0F;
               var16.rect(var1, var17, var18, 1.0F, 0);
               var16.roundRectCorners(var1, var17, var18, 1.0F, 0.0F, 16777215, 620756991, 620756991, 16777215);
               var16.roundRectCorners(var1 + var18, var17, var18, 1.0F, 0.0F, 620756991, 16777215, 16777215, 620756991);
               l((char)var9, (short)var10, var11, var16);
               return true;
            default:
               return false;
         }
      } else {
         return false;
      }
   }

   public static boolean fill(class_332 var0, int var1, int var2, int var3, int var4, int var5) {
      if (B != null && var5 == -1601138544) {
         var0.method_25296(var1, var2, var3, var4, Theme.tint(-1204148104), Theme.tint(-1201000806));
         return true;
      } else {
         return false;
      }
   }

   private static void o(Canvas var0, String var1, int var2, int var3, long var4, int var6, int var7, float var8) {
      long var9 = 73274088922580L;
      byte var11 = 0;
      short var12 = 17060;
      int var13 = (int)(var9 << 32 >>> 32);
      long var14 = 102152110571775L;
      long var16 = 24141339743833L;
      class_339 var18 = F(var14);
      float var19 = var18 != null ? r(var18).L.value() : 0.0F;
      o(var16, var0);
      var0.icon(
         var1, var2 + var6 / 2.0F, var3 + var7 / 2.0F, Math.min(var6, var7) * 0.86F, BlendMode.withAlpha(BlendMode.lerp(Theme.tint(-1580553), -1, var19), var8)
      );
      l((char)var11, var12, var13, var0);
   }

   private static void Q(Canvas var0, int var1, int var2, long var3, int var5, int var6, boolean var7, boolean var8, float var9) {
      long var10;
      class_339 var12;
      HomeSupport2.TypeZ var13;
      boolean var14;
      boolean var33;
      label93: {
         long var16 = 102152110571775L;
         var10 = 92130417277715L;
         var12 = F(var16);
         Object var18 = var12 != null ? var12 : new HomeSupport2.TypeU(var1, var2, var5, var6);
         var13 = r(var18);
         var14 = var12 != null ? var12.method_49606() && var12.field_22763 : var7;
         if (var12 != null) {
            var33 = var12.method_25370();
            long var19 = 32342561700092L;
            int var23 = var19 < 0L ? -1 : 1;
            if (var33 && a()) {
               var33 = true;
               break label93;
            }
         }

         var33 = false;
      }

      if (var8) {
         var14 = false;
      }

      if (var14 && var12 != null) {
         x = var12;
      }

      float var17 = v();
      float var34 = var13.L.target(var14 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var17));
      long var35 = 32342561700092L;
      int var22 = var35 < 0L ? -1 : 1;
      boolean var22x = var14 && class_310.method_1551().field_1729.method_1608();
      long var23 = 32342561700092L;
      int var23x = var23 < 0L ? -1 : 1;
      if (var22x && !var13.Z) {
         var13.r = Z - var1;
         var13.O = C - var2;
         var13.T = i();
      }

      var13.Z = var22x;
      float var26x = var13.F.target(var22x ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var17));
      float var27 = var13.N.target(var33 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var17));
      float var28 = Math.min(var6 / 2.0F, 10.0F);
      float var29 = var9 * (var8 ? 0.5F : 1.0F);
      long var30 = 32342561700091L;
      int var29x = var30 < 0L ? -1 : 1;
      var0.pushAlpha(var29);
      if (var26x > 0.001F) {
         var0.scaleAround(1.0F - 0.025F * var26x, var1 + var5 / 2.0F, var2 + var6 / 2.0F);
      }

      glassPill(var0, var1, var2, var5, var6, var28, var34, var27, var8);
      w(var0, var10, var13, var1, var2, var5, var6, var28);
      var0.popAlpha();
   }

   static void glassPill(Canvas var0, float var1, float var2, float var3, float var4, float var5, float var6, float var7, boolean var8) {
      var0.shadow(var1, var2 + 1.5F, var3, var4, var5, 9.0F, Theme.tint(1075054642));
      if (var6 > 0.01F) {
         var0.glow(var1, var2, var3, var4, var5, 12.0F + 6.0F * var6, BlendMode.withAlpha(Icons.VIOLET, 0.3F * var6));
      }

      var0.glass(var1, var2, var3, var4, var5, BlendMode.lerp(Theme.tint(-2058471274), Theme.tint(-1805106492), var6), 1.0F, 0.45F, 0.012F);
      var0.roundRectGradient(var1, var2, var3, var4, var5, 0.0F, Theme.tint(543837183), Theme.tint(449070576));
      if (var6 > 0.01F) {
         var0.pushAlpha(var6);
         var0.roundRectGradient(var1, var2, var3, var4, var5, 0.0F, Theme.tint(1029637355), Theme.tint(869345781));
         var0.popAlpha();
      }

      var0.roundRectCorners(var1, var2, var3, var4, var5, 754974719, 503316479, 16777215, 83886079);
      int var9 = var8 ? 452984831 : BlendMode.lerp(1627389951, BlendMode.withAlpha(Theme.tint(-3695617), 0.95F), var6);
      int var10 = var8 ? 150994943 : BlendMode.lerp(385875967, BlendMode.withAlpha(Icons.MAGENTA, 0.6F), var6);
      var0.roundRectOutlineGradient(var1, var2, var3, var4, var5, 1.0F, 90.0F, var9, var10);
      if (var7 > 0.01F) {
         var0.roundRectOutline(var1 - 2.0F, var2 - 2.0F, var3 + 4.0F, var4 + 4.0F, var5 + 2.0F, 1.2F, BlendMode.withAlpha(Theme.tint(-1586945), 0.85F * var7));
      }
   }

   private static void w(Canvas var0, long var1, HomeSupport2.TypeZ var3, float var4, float var5, float var6, float var7, float var8) {
      float var9 = (float)(i() - var3.T);
      if (!(var9 < 0.0F) && !(var9 > 0.7F) && !MotionStyle.reduced()) {
         float var10 = var9 / 0.7F;
         float var11 = 1.0F - (1.0F - var10) * (1.0F - var10) * (1.0F - var10);
         float var12 = 4.0F + Math.max(var6, var7) * 1.1F * var11;
         var0.pushClip(var4, var5, var6, var7, var8);
         var0.circleRadial(
            var4 + var3.r,
            var5 + var3.O,
            var12,
            BlendMode.withAlpha(Theme.tint(-1453313), 0.0F),
            BlendMode.withAlpha(Theme.tint(-1453313), 0.28F * (1.0F - var10))
         );
         var0.popClip();
      }
   }

   private static boolean a() {
      return class_310.method_1551().method_48186().method_48183();
   }

   public static boolean label(class_339 var0, class_2561 var1, int var2) {
      long var3 = 111497908656775L;
      long var5 = var3 ^ 43758556514131L;
      int var7 = (int)((var3 ^ 43758556514131L) >>> 48);
      int var8 = (int)((var3 ^ 43758556514131L) << 16 >>> 48);
      int var9 = (int)(var5 << 32 >>> 32);
      long var10 = var3 ^ 123819457498334L;
      if (B == null) {
         return false;
      } else {
         String var12 = var1.getString();
         if (!supported(Fonts.SEMIBOLD, var12)) {
            return false;
         } else {
            Canvas var13 = n();
            HomeSupport2.TypeZ var14 = r(var0);
            float var15 = var14.L.value();
            float var16 = var0.method_75798();
            boolean var17 = var0.field_22763;
            int var18 = var17 ? BlendMode.lerp(Theme.tint(-286003457), -1, var15) : Theme.tint(-5923398);
            float var19 = var0.method_46426();
            float var20 = var0.method_46427();
            float var21 = var0.method_25368();
            float var22 = var0.method_25364();
            o(var10, var13);
            var13.pushAlpha(var16);
            float var23 = var14.F.value();
            if (var23 > 0.001F) {
               var13.scaleAround(1.0F - 0.025F * var23, var19 + var21 / 2.0F, var20 + var22 / 2.0F);
            }

            fitText(var13, Fonts.SEMIBOLD, var12, var19 + var2, var20, var21 - var2 * 2, var22, Math.min(9.2F, var22 * 0.46F), var18);
            var13.popAlpha();
            l((char)var7, (short)var8, var9, var13);
            return true;
         }
      }
   }

   static void fitText(Canvas var0, TrueTypeFont var1, String var2, float var3, float var4, float var5, float var6, float var7, int var8) {
      Translations.pause();

      try {
         float var9 = var0.textWidth(var1, var2, var7);
         if (var9 > var5 && var9 > 0.0F) {
            var7 = Math.max(var7 * 0.82F, var7 * var5 / var9);
            var9 = var0.textWidth(var1, var2, var7);
         }

         float var10 = var4 + var6 / 2.0F;
         if (!(var9 <= var5)) {
            float var11 = var9 - var5;
            float var12 = Math.max(3.0F, var11 * 0.5F);
            double var13 = RenderPipelines.get().time() / var12;
            float var15 = (float)(Math.sin(var13 * Math.PI * 2.0) * 0.5 + 0.5);
            var0.pushClip(var3, var4, var5, var6);
            var0.text(var1, var2, var3 - var11 * var15, var10, var7, var8, FontStyle.LEFT, FontWeight.MIDDLE);
            var0.popClip();
            return;
         }

         var0.text(var1, var2, var3 + var5 / 2.0F, var10, var7, var8, FontStyle.CENTER, FontWeight.MIDDLE);
      } finally {
         Translations.resume();
      }
   }

   static boolean supported(TrueTypeFont var0, String var1) {
      int var2 = 0;

      while (var2 < var1.length()) {
         int var3 = var1.codePointAt(var2);
         var2 += Character.charCount(var3);
         if (var3 > 32 && !var0.hasGlyph(var3)) {
            return false;
         }
      }

      return true;
   }

   public static boolean link(class_339 var0, class_2561 var1) {
      long var2 = 117615817228742L;
      long var4 = var2 ^ 44379444812818L;
      int var6 = (int)((var2 ^ 44379444812818L) >>> 48);
      int var7 = (int)((var2 ^ 44379444812818L) << 16 >>> 48);
      int var8 = (int)(var4 << 32 >>> 32);
      long var9 = var2 ^ 139690707212191L;
      if (B == null) {
         return false;
      } else {
         String var11 = var1.getString();
         if (!supported(Fonts.MEDIUM, var11)) {
            return false;
         } else {
            Canvas var12 = n();
            HomeSupport2.TypeZ var13 = r(var0);
            boolean var14 = var0.method_25367();
            if (var0.method_49606()) {
               x = var0;
            }

            float var15 = var13.L.target(var14 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(v()));
            float var16 = 7.2F;
            float var17 = var0.method_46426() + var0.method_25368();
            float var18 = var0.method_46427() + var0.method_25364() / 2.0F;
            o(var9, var12);
            var12.pushAlpha(var0.method_75798());
            Translations.pause();
            float var19 = var12.textWidth(Fonts.MEDIUM, var11, var16);
            float var20 = Math.min((float)var0.method_46426(), var17 - var19);
            var12.text(Fonts.MEDIUM, var11, var20, var18, var16, BlendMode.lerp(Theme.tint(-1712724993), -1, var15), FontStyle.LEFT, FontWeight.MIDDLE);
            Translations.resume();
            if (var15 > 0.01F) {
               var12.rect(var20, var18 + 4.6F, var19 * var15, 0.6F, BlendMode.withAlpha(Theme.tint(-1586945), 0.8F * var15));
            }

            var12.popAlpha();
            l((char)var6, (short)var7, var8, var12);
            return true;
         }
      }
   }

   public static boolean title(class_339 var0, class_2561 var1) {
      long var2 = 5966870848702L;
      long var4 = var2 ^ 78929440094570L;
      int var6 = (int)((var2 ^ 78929440094570L) >>> 48);
      int var7 = (int)((var2 ^ 78929440094570L) << 16 >>> 48);
      int var8 = (int)(var4 << 32 >>> 32);
      long var9 = var2 ^ 18251778978535L;
      class_437 var11 = B;
      if (var11 != null && var1.getString().equals(var11.method_25440().getString())) {
         String var12 = var1.getString();
         if (!var12.isEmpty() && supported(Fonts.BOLD, var12)) {
            Canvas var13 = n();
            o(var9, var13);
            var13.pushAlpha(var0.method_75798());
            float var14 = var0.method_46426() + var0.method_25368() / 2.0F;
            float var15 = var0.method_46427() + var0.method_25364() / 2.0F;
            Translations.pause();
            var13.textGlow(
               Fonts.BOLD, var12, var14, var15, 11.0F, BlendMode.withAlpha(Theme.tint(-16120300), 0.55F), FontStyle.CENTER, FontWeight.MIDDLE, 0.0F, 1.5F, 6.0F
            );
            var13.text(Fonts.BOLD, var12, var14, var15, 11.0F, -1, FontStyle.CENTER, FontWeight.MIDDLE, -0.01F);
            Translations.resume();
            var13.popAlpha();
            l((char)var6, (short)var7, var8, var13);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private static void W(Canvas var0, int var1, long var2, int var4, int var5, int var6, boolean var7, float var8) {
      long var9 = 102152110571775L;
      class_339 var11 = F(var9);
      HomeSupport2.TypeZ var12 = r(var11 != null ? var11 : new HomeSupport2.TypeU(var1, var4, var5, var6));
      boolean var13 = var11 != null && var11.method_49606();
      float var14 = v();
      float var15 = var12.L.target(var13 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var14));
      float var16 = var12.N.target(var7 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var14));
      float var17 = Math.min(var6 / 2.0F, 6.0F);
      long var18 = 3822406058205L;
      int var21 = var18 < 0L ? -1 : 1;
      var0.pushAlpha(var8);
      float var21x = var1;
      float var22 = var4 + 1;
      float var23 = var5;
      long var24 = 3822406058205L;
      int var27 = var24 < 0L ? -1 : 1;
      var0.shadow(var21x, var22, var23, var6, var17, 8.0F, Theme.tint(805634832));
      if (var16 > 0.01F) {
         var0.glow(var1, var4, var5, var6, var17, 12.0F, BlendMode.withAlpha(Icons.VIOLET, 0.18F * var16));
      }

      long var27x = 3822406058206L;
      int var27x = var27x < 0L ? -1 : 1;
      var0.glass(var1, var4, var5, var6, var17, BlendMode.lerp(Theme.tint(-1609298384), Theme.tint(-1675881406), var15), 1.0F, 0.45F, 0.01F);
      var0.roundRectCorners(var1, var4, var5, var6, var17, 402653184, 402653184, 0, 0);
      var0.roundRectOutline(var1, var4, var5, var6, var17, 1.0F, BlendMode.lerp(520093695, 822083583, var15));
      long var30x = 3822406058205L;
      int var29 = var30x < 0L ? -1 : 1;
      if (var16 > 0.01F) {
         var0.pushAlpha(var16);
         var0.roundRectOutlineGradient(
            var1, var4, var5, var6, var17, 1.0F, 0.0F, BlendMode.withAlpha(Icons.PURPLE, 0.85F), BlendMode.withAlpha(Icons.PINK, 0.7F)
         );
         var0.popAlpha();
      }

      var0.popAlpha();
   }

   private static void i(Canvas var0, int var1, int var2, int var3, int var4, boolean var5, float var6, long var7) {
      long var9 = 102152110571775L;
      class_339 var11 = F(var9);
      HomeSupport2.TypeZ var12 = r(var11 != null ? var11 : new HomeSupport2.TypeU(var1, var2, var3, var4));
      boolean var13 = var11 != null ? var11.method_49606() : var5;
      if (var13 && var11 != null) {
         x = var11;
      }

      float var14 = var12.L.target(var13 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(v()));
      float var15 = var12.N.target(var11 != null && var11.method_25370() && a() ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(v()));
      float var16 = Math.min(var4 / 2.0F, 10.0F);
      var0.pushAlpha(var6);
      glassPill(var0, var1, var2, var3, var4, var16, var14 * 0.6F, var15, false);
      var0.popAlpha();
      h = var11;
      D = var1;
      L = var2;
      s = var3;
      a = var4;
   }

   private static void Q(Canvas var0, int var1, int var2, long var3, int var5, int var6, boolean var7, float var8) {
      long var9 = 102152110571775L;
      class_339 var11 = F(var9);
      float var12 = Math.min(a / 2.0F, 10.0F);
      float var13 = var1 + var5 / 2.0F;
      var0.pushAlpha(var8);
      if (h == var11 && s > 0.0F) {
         var0.pushClip(D, L, s, a, var12);
         var0.roundRectGradient(D, L, Math.max(0.0F, var13 - D), a, 0.0F, 0.0F, Theme.tint(861865195), Theme.tint(1555374068));
         var0.popClip();
      }

      HomeSupport2.TypeZ var14 = r(var11 != null ? var11 : new HomeSupport2.TypeU(var1, var2, var5, var6));
      float var15 = var14.L.value();
      float var16 = 2.4F + var15 * 0.6F;
      float var17 = 3.2F + var15 * 0.8F;
      var0.glow(var13 - var16 / 2.0F, var2 + 2, var16, var6 - 4, var16 / 2.0F, 9.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.3F + 0.25F * var15));
      var0.roundRect(var13 - var16 / 2.0F, var2 + 1.5F, var16, var17, var16 / 2.0F, Theme.tint(-218367745));
      var0.roundRect(var13 - var16 / 2.0F, var2 + var6 - 1.5F - var17, var16, var17, var16 / 2.0F, Theme.tint(-218367745));
      var0.popAlpha();
   }

   public static boolean sliderLabel(class_339 var0, class_2561 var1) {
      long var2 = 38397009588913L;
      long var4 = var2 ^ 105864301604709L;
      int var6 = (int)((var2 ^ 105864301604709L) >>> 48);
      int var7 = (int)((var2 ^ 105864301604709L) << 16 >>> 48);
      int var8 = (int)(var4 << 32 >>> 32);
      long var9 = var2 ^ 60579840992488L;
      if (B == null) {
         return false;
      } else {
         String var11 = var1.getString();
         if (!supported(Fonts.SEMIBOLD, var11)) {
            return false;
         } else {
            Canvas var12 = n();
            float var13 = var0.method_46426();
            float var14 = var0.method_46427();
            float var15 = var0.method_25368();
            float var16 = var0.method_25364();
            float var17 = Math.min(9.2F, var16 * 0.46F);
            o(var9, var12);
            var12.pushAlpha(var0.method_75798());
            Translations.pause();
            if (var12.textWidth(Fonts.SEMIBOLD, var11, var17) <= var15 - 8.0F) {
               var12.textGlow(
                  Fonts.SEMIBOLD,
                  var11,
                  var13 + var15 / 2.0F,
                  var14 + var16 / 2.0F,
                  var17,
                  Theme.tint(-1945500140),
                  FontStyle.CENTER,
                  FontWeight.MIDDLE,
                  0.0F,
                  1.0F,
                  3.0F
               );
            }

            Translations.resume();
            fitText(var12, Fonts.SEMIBOLD, var11, var13 + 4.0F, var14, var15 - 8.0F, var16, var17, var0.field_22763 ? -1 : Theme.tint(-5923398));
            var12.popAlpha();
            l((char)var6, (short)var7, var8, var12);
            return true;
         }
      }
   }

   private static void F(Canvas var0, int var1, int var2, int var3, int var4, byte var5, boolean var6, long var7, boolean var9, float var10) {
      long var11 = 65170272849011L;
      long var13 = var11 ^ 113994987015308L;
      class_339 var15 = F(var13);
      HomeSupport2.TypeZ var16 = r(var15 != null ? var15 : new HomeSupport2.TypeU(var1, var2, var3, var4));
      boolean var17 = var15 != null ? var15.method_49606() : var9;
      if (var17 && var15 != null) {
         x = var15;
      }

      float var18 = v();
      float var19 = var16.L.target(var17 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var18));
      float var20 = var16.n.target(var6 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var18));
      float var21 = Math.min(var3, var4) * 0.28F;
      long var22 = 65170272849010L;
      int var25 = var22 < 0L ? -1 : 1;
      var0.pushAlpha(var10);
      long var25x = 65170272849010L;
      int var26 = var25x < 0L ? -1 : 1;
      var0.shadow(var1, var2 + 1, var3, var4, var21, 6.0F, Theme.tint(855966480));
      var0.glass(var1, var2, var3, var4, var21, BlendMode.lerp(Theme.tint(-1708515750), Theme.tint(-1707464584), var19), 1.0F, 0.45F, 0.01F);
      if (var20 > 0.01F) {
         var0.pushAlpha(var20);
         var0.glow(var1, var2, var3, var4, var21, 10.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.35F));
         var0.roundRectGradient(var1, var2, var3, var4, var21, 135.0F, Icons.INDIGO, Icons.MAGENTA);
         var0.roundRectCorners(var1, var2, var3, var4, var21, 872415231, 419430399, 16777215, 16777215);
         var0.popAlpha();
         var0.icon("check", var1 + var3 / 2.0F, var2 + var4 / 2.0F, Math.min(var3, var4) * (0.55F + 0.15F * var20), BlendMode.withAlpha(-1, var20));
      }

      var0.roundRectOutline(
         var1, var2, var3, var4, var21, 1.0F, BlendMode.lerp(754974719, BlendMode.withAlpha(Theme.tint(-3695617), 0.9F), Math.max(var19, var20 * 0.6F))
      );
      var0.popAlpha();
   }

   private static void m(Canvas var0, int var1, long var2, int var4, int var5, int var6, boolean var7, boolean var8, float var9) {
      long var10 = 102152110571775L;
      class_339 var12 = F(var10);
      HomeSupport2.TypeZ var13 = r(var12 != null ? var12 : new HomeSupport2.TypeU(var1, var4, var5, var6));
      boolean var14 = var12 != null ? var12.method_49606() : var8;
      if (var14 && var12 != null && !var7) {
         x = var12;
      }

      float var15 = v();
      float var16 = var13.L.target(var14 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var15));
      float var17 = var13.n.target(var7 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var15));
      float var18 = 3.0F;
      float var19 = var1 + var18;
      float var20 = var4 + var18;
      float var21 = var5 - var18 * 2.0F;
      float var22 = var6 - var18 * 2.0F;
      float var23 = var22 / 2.0F;
      var0.pushAlpha(var9);
      if (var16 > 0.01F && var17 < 0.99F) {
         var0.roundRect(var19, var20, var21, var22, var23, BlendMode.withAlpha(-1, 0.07F * var16 * (1.0F - var17)));
      }

      if (var17 > 0.01F) {
         var0.pushAlpha(var17);
         glassPill(var0, var19, var20, var21, var22, var23, 0.85F, 0.0F, false);
         var0.popAlpha();
      }

      var0.popAlpha();
   }

   public static boolean tabLabel(class_339 var0, class_2561 var1, boolean var2) {
      long var3 = 87137676689328L;
      long var5 = var3 ^ 15273546048100L;
      int var7 = (int)((var3 ^ 15273546048100L) >>> 48);
      int var8 = (int)((var3 ^ 15273546048100L) << 16 >>> 48);
      int var9 = (int)(var5 << 32 >>> 32);
      long var10 = var3 ^ 99731384117737L;
      if (B == null) {
         return false;
      } else {
         String var12 = var1.getString();
         if (!supported(Fonts.SEMIBOLD, var12)) {
            return false;
         } else {
            Canvas var13 = n();
            HomeSupport2.TypeZ var14 = r(var0);
            int var15 = !var0.field_22763
               ? Theme.tint(-7568218)
               : BlendMode.lerp(BlendMode.lerp(Theme.tint(-4607530), -1, var14.L.value() * 0.6F), -1, var14.n.value());
            o(var10, var13);
            var13.pushAlpha(var0.method_75798());
            fitText(var13, Fonts.SEMIBOLD, var12, var0.method_46426() + 4, var0.method_46427(), var0.method_25368() - 8, var0.method_25364(), 8.6F, var15);
            var13.popAlpha();
            l((char)var7, (short)var8, var9, var13);
            return true;
         }
      }
   }

   public static void listRow(class_350<?> var0, class_8021 var1, boolean var2, boolean var3) {
      long var4 = 80337538614505L;
      long var6 = var4 ^ 12874138851645L;
      int var8 = (int)((var4 ^ 12874138851645L) >>> 48);
      int var9 = (int)((var4 ^ 12874138851645L) << 16 >>> 48);
      int var10 = (int)(var6 << 32 >>> 32);
      long var11 = var4 ^ 102142334030512L;
      if (B != null && var0 instanceof class_4280) {
         String var13 = var1.getClass().getSimpleName();
         if (!var13.contains("Header") && !var13.contains("NoWorlds")) {
            Canvas var14 = n();
            HomeSupport2.TypeZ var15 = r(var1);
            float var16 = v();
            float var17 = var15.L.target(var3 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var16));
            float var18 = var15.n.target(var2 ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var16));
            if (var3 && !var2) {
               x = var1;
            }

            float var19 = var1.method_46426() - 3;
            float var20 = var1.method_46427() + 0.5F;
            float var21 = var1.method_25368() + 6;
            float var22 = var1.method_25364() - 1;
            float var23 = 7.0F;
            o(var11, var14);
            var14.pushClip(var0.method_46426(), var0.method_46427(), var0.method_25368(), var0.method_25364());
            var14.pushAlpha(var0.method_75798());
            var14.shadow(var19, var20 + 1.5F, var21, var22, var23, 10.0F, Theme.tint(738525968));
            if (var18 > 0.01F) {
               var14.glow(var19, var20, var21, var22, var23, 16.0F, BlendMode.withAlpha(Icons.VIOLET, 0.26F * var18));
            }

            var14.glass(
               var19,
               var20,
               var21,
               var22,
               var23,
               BlendMode.lerp(BlendMode.lerp(Theme.tint(-1809047972), Theme.tint(-1808259984), var17), Theme.tint(-1706809212), var18),
               1.0F,
               0.45F,
               0.012F
            );
            if (var18 > 0.01F) {
               var14.pushAlpha(var18);
               var14.roundRectGradient(var19, var20, var21, var22, var23, 0.0F, Theme.tint(643761387), Theme.tint(349252085));
               var14.popAlpha();
            }

            var14.roundRectCorners(var19, var20, var21, var22, var23, 352321535, 218103807, 16777215, 50331647);
            int var24 = BlendMode.lerp(BlendMode.lerp(486539263, 822083583, var17), BlendMode.withAlpha(Theme.tint(-3695617), 0.95F), var18);
            int var25 = BlendMode.lerp(BlendMode.lerp(150994943, 352321535, var17), BlendMode.withAlpha(Icons.MAGENTA, 0.55F), var18);
            var14.roundRectOutlineGradient(var19, var20, var21, var22, var23, 1.0F, 90.0F, var24, var25);
            var14.popAlpha();
            var14.popClip();
            l((char)var8, (short)var9, var10, var14);
         }
      }
   }

   public static boolean hidesSelection(class_350<?> var0) {
      return B != null && var0 instanceof class_4280;
   }

   public static boolean click() {
      class_310 var0 = class_310.method_1551();
      if (skins(var0.field_1755) && UiAudioPolicy.active()) {
         HomeUpdater.play(HomeKind.CLICK);
         return true;
      } else {
         return false;
      }
   }

   public static void ensureSourceClassInitialized() {
   }

   private record TypeH(class_339 j, float D, float K, boolean R) {
      public class_339 widget() {
         return this.j;
      }

      public float offset() {
         return this.D;
      }

      public float alpha() {
         return this.K;
      }

      public boolean pushed() {
         return this.R;
      }

      public class_339 minecraftObjectState1() {
         return this.j;
      }

      public float floatState24() {
         return this.D;
      }

      public float floatState50() {
         return this.K;
      }

      public boolean booleanState21() {
         return this.R;
      }
   }

   private record TypeU(int z, int x, int G, int t) {
      public int x() {
         return this.z;
      }

      public int y() {
         return this.x;
      }

      public int w() {
         return this.G;
      }

      public int h() {
         return this.t;
      }

      public int integerState19() {
         return this.z;
      }

      public int integerState18() {
         return this.G;
      }

      public int integerState14() {
         return this.t;
      }
   }

   private static final class TypeZ {
      final Animator L = Animator.smooth(0.0F);
      final Animator F = Animator.snappy(0.0F);
      final Animator N = Animator.smooth(0.0F);
      final Animator n = Animator.smooth(0.0F);
      float r;
      float O;
      double T = -10.0;
      boolean Z;
   }
}
