package dev.purxiy.modules.home;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.config.ModeSetting;
import dev.purxiy.i18n.Translations;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.class_2561;
import net.minecraft.class_310;
import net.minecraft.class_339;
import net.minecraft.class_364;
import net.minecraft.class_437;
import net.minecraft.class_442;

public final class HomeScreenRenderer {
   private static final float H = 20.0F;
   private static final float u = 8.0F;
   private static class_437 g;
   private static double X;
   private static final Animator r = Animator.smooth(0.0F);
   private static final Animator S = Animator.smooth(0.0F);
   private static final Animator K = Animator.smooth(0.0F);
   private static final Animator l = Animator.snappy(0.0F);
   private static final Animator W = Animator.snappy(0.0F);
   private static final Animator N = Animator.smooth(0.0F);
   private static final Animator q = Animator.bouncy(0.0F);
   private static final Map<String, Animator> v = new HashMap<>();
   private static boolean O;
   private static float[] e = new float[4];
   private static float[] o = new float[4];
   private static float[] d = new float[4];
   private static final Map<String, float[]> M = new HashMap<>();
   private static String U;
   private static String j;
   private static float i;
   private static float m = 10.0F;
   private static final String[][] t = new String[][]{
      {"Morning", "sunrise"}, {"Day", "sun"}, {"Sunset", "sunset"}, {"Night", "moon-star"}, {"Real time", "clock"}
   };
   private static final String[][] R = new String[][]{
      {"Oak", "tree-deciduous"}, {"Birch", "trees"}, {"Cherry", "flower-2"}, {"Spruce", "tree-pine"}, {"Mixed", "shuffle"}
   };
   private static final String[][] k = new String[][]{{"Orbit", "rotate-3d"}, {"Drift", "wind"}, {"Still", "pause"}};
   private static final String[] b = new String[40];

   private HomeScreenRenderer() {
   }

   public static float[] logoSlot(class_437 var0) {
      float var1 = var0.field_22790 / 4.0F + 48.0F;
      float var2 = var0.field_22789 / 2.0F;

      for (class_364 var4 : var0.method_25396()) {
         if (var4 instanceof class_339 var5
            && var5.field_22764
            && var5.method_46426() < var2
            && var5.method_46426() + var5.method_25368() > var2
            && var5.method_46427() < var0.field_22790 * 0.8F
            && var5.method_46427() > var0.field_22790 * 0.1F) {
            var1 = Math.min(var1, (float)var5.method_46427());
         }
      }

      float var8 = Math.max(6.0F, var0.field_22790 * 0.035F);
      float var9 = var1 - var8 - 17.0F;
      float var10 = Math.max(28.0F, Math.min(var9, Math.min(80.0F, var0.field_22790 * 0.3F)));
      float var6 = var10 / 1.305F;
      float var7 = var8 + Math.max(0.0F, (var9 - var10) * 0.5F);
      return new float[]{var2, var7, var6};
   }

   public static void render(class_442 var0, class_2561 var1) {
      long var2 = 30196064533073L;
      long var4 = var2 ^ 109839119861296L;
      int var6 = (int)((var2 ^ 109839119861296L) >>> 48);
      int var7 = (int)((var2 ^ 109839119861296L) << 16 >>> 32);
      int var8 = (int)(var4 << 48 >>> 48);
      long var9 = var2 ^ 56460019755438L;
      long var11 = (var2 ^ 13457094213317L) >>> 16;
      int var13 = (int)((var2 ^ 13457094213317L) << 48 >>> 48);
      class_310 var14 = class_310.method_1551();
      int var15 = (int)var14.field_1729.method_68879(var14.method_22683());
      int var16 = (int)var14.field_1729.method_68883(var14.method_22683());
      Canvas var17 = RenderPipelines.get().menuCanvas();
      float var18 = RenderPipelines.get().delta();
      float var19 = RenderPipelines.get().time();
      if (var0 != g) {
         g = var0;
         X = System.nanoTime() / 1.0E9;
         O = false;
         N.snap(0.0F);
         r.snap(HomeRenderer.ownsLogo() ? 1.0F : 0.0F);
         q.snap(0.0F);
      }

      float var20 = (float)(System.nanoTime() / 1.0E9 - X);
      U = null;
      float var21 = HomeRenderer.sinceLanding();
      if (!Float.isNaN(var21)) {
         m = var21;
      } else if (m < 10.0F) {
         m = Math.min(10.0F, m + Math.min(var18, 0.05F));
      }

      float[] var22 = logoSlot(var0);
      if (!HomeRenderer.ownsLogo()) {
         float var23 = r.target(1.0F).update(MotionStyle.MENU.dt(var18));
         float var24 = Easing.EMPHASIZED.apply(Math.min(1.0F, var23));
         float var25 = m >= 0.0F && m < 1.2F ? 1.0F - m / 1.2F : 0.0F;
         if (var25 > 0.0F) {
            var17.glowCircle(
               var22[0],
               var22[1] + var22[2] * 0.55F,
               var22[2] * 0.4F,
               var22[2] * (0.9F + 0.5F * (1.0F - var25)),
               BlendMode.withAlpha(Icons.VIOLET, 0.28F * var25 * var25)
            );
         }

         float var26 = m < 0.0F ? 0.0F : b(m / 0.15F) * (1.0F - b((m - 0.75F) / 0.45F));
         HomePipeline.draw(
            var17,
            var22[0],
            var22[1] - (1.0F - var24) * 6.0F,
            var22[2],
            Math.min(1.0F, var23 * 1.2F),
            1.0F,
            0.0F,
            0.85F,
            var19,
            1.0F,
            HomePipeline.sweepAt(b(m / 1.1F)),
            var26 * 0.9F,
            var25 * var25
         );
         L(var17, (short)var6, var0, var1, var7, (short)var8, var22, var19, var18);
      }

      float var27 = Easing.EMPHASIZED.apply(Math.max(0.0F, Math.min(1.0F, (HomeGuiBridge.shown() - 0.25F) / 0.5F)));
      if (var27 > 0.003F) {
         Canvas var28 = RenderPipelines.get().canvas();
         var28.push();
         var28.scale(RenderPipelines.get().fromGui(1.0));
         F(var28, var0, var11, var15, var16, var18, var19, var27, (char)var13);
         v(var28, var0, var15, var9, var16, var18);
         var28.pop();
      }

      if (U != null && !U.equals(j)) {
         HomeUpdater.play(HomeKind.HOVER);
      }

      j = U;
   }

   private static void L(Canvas var0, short var1, class_437 var2, class_2561 var3, int var4, short var5, float[] var6, float var7, float var8) {
      HomeGuiBridge.ensureSourceClassInitialized();
      if (var3 != null && HomeUpdater.z.s.enabled() && !(Boolean)class_310.method_1551().field_1690.method_54581().method_41753()) {
         String var9 = var3.getString();
         boolean var10 = var9.isEmpty();
         if (!var10 && HomeGuiBridge.supported(Fonts.BOLD, var9) && !(m < 0.12F)) {
            float var11 = Math.max(0.0F, q.target(1.0F).update(MotionStyle.MENU.dt(var8)));
            float var12 = HomePipeline.markWidth(var6[2]);
            float var13 = var6[0] + var12 * 0.45F;
            float var14 = var6[1] + var6[2] * 0.92F;
            float var15 = MotionStyle.reduced() ? 1.0F : 1.0F + 0.045F * (float)Math.abs(Math.sin(var7 * Math.PI));
            float var16 = 7.6F;
            float var17 = var0.textWidth(Fonts.BOLD, var9, var16);
            float var18 = Math.max(40.0F, var2.field_22789 - var13 - 12.0F);
            boolean var19 = false;
            var19 = false;
            if (var17 > var18) {
               var16 *= var18 / var17;
            }

            var0.push();
            var0.translate(var13, var14);
            var0.rotate(-0.28F);
            var0.scale(var15 * var11);
            Translations.pause();
            var0.textGlow(Fonts.BOLD, var9, 0.0F, 0.0F, var16, -1944449531, FontStyle.LEFT, FontWeight.MIDDLE, 0.0F, 1.2F, 4.0F);
            var0.text(Fonts.BOLD, var9, 0.0F, 0.0F, var16, -7558, FontStyle.LEFT, FontWeight.MIDDLE, 0.01F);
            Translations.resume();
            var0.pop();
         }
      }
   }

   private static void F(Canvas var0, class_437 var1, long var2, int var4, int var5, float var6, float var7, float var8, char var9) {
      long var10 = 25613723419796L;
      long var12 = var10 ^ 92787369947567L;
      long var14 = var10 ^ 51851054306061L;
      int var16 = (int)((var10 ^ 51851054306061L) >>> 32);
      int var17 = (int)((var10 ^ 51851054306061L) << 32 >>> 48);
      int var18 = (int)(var14 << 48 >>> 48);
      HomeGuiBridge.ensureSourceClassInitialized();
      HomeUpdater var19 = HomeUpdater.get();
      boolean var20 = var19.growing();
      float var21 = var1.field_22789 - 8.0F;
      float var22 = var1.field_22790 - 14 - 20.0F;
      float var23 = 20.0F + Q(var0, "Grow trees") * S.value();
      float var24 = var21 - var23;
      boolean var25 = b(var16, (short)var17, var4, var5, (short)var18, var24, var22, var23, 20.0F) && !O;
      S.target(var25 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var6));
      l.target(var25 && class_310.method_1551().field_1729.method_1608() ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var6));
      e = new float[]{var24, var22, var23, 20.0F};
      float var26 = 20.0F + Q(var0, "Scene") * K.value();
      float var27 = var24 - 6.0F - var26;
      boolean var28 = b(var16, (short)var17, var4, var5, (short)var18, var27, var22, var26, 20.0F);
      K.target(!var28 && !O ? 0.0F : 1.0F).update(MotionStyle.HOVER.dt(var6));
      W.target(var28 && class_310.method_1551().field_1729.method_1608() ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var6));
      long var29 = 390834402L;
      int var32 = var29 < 0L ? -1 : 1;
      o = new float[]{var27, var22, var26, 20.0F};
      char var32x = '쒔';
      if (var25) {
         U = "grow";
         var32x = '쒔';
      } else if (var28) {
         U = "scene";
      }

      var0.pushAlpha(var8);
      i = var20 ? i + var6 * 2.4F : i * Math.max(0.0F, 1.0F - var6 * 8.0F);
      q(var0, e, "sprout", "Grow trees", S.value(), l.value(), var12, var20 ? (float)Math.sin(i) * 0.18F : 0.0F, var20 ? var19.growProgress() : -1.0F);
      q(var0, o, "sliders-horizontal", "Scene", K.value(), W.value(), var12, 0.0F, -1.0F);
      var0.popAlpha();
   }

   private static float Q(Canvas var0, String var1) {
      return var0.textWidth(Fonts.SEMIBOLD, var1, 7.6F) + 9.0F;
   }

   private static void q(Canvas var0, float[] var1, String var2, String var3, float var4, float var5, long var6, float var8, float var9) {
      float var10 = var1[0];
      float var11 = var1[1];
      HomeGuiBridge.ensureSourceClassInitialized();
      float var12 = var1[2];
      float var13 = var1[3];
      var0.push();
      long var14 = 73839461233978L;
      int var17 = var14 < 0L ? -1 : 1;
      if (var5 > 0.001F) {
         var0.scaleAround(1.0F - 0.06F * var5, var10 + var12 / 2.0F, var11 + var13 / 2.0F);
      }

      HomeGuiBridge.glassPill(var0, var10, var11, var12, var13, var13 / 2.0F, var4, 0.0F, false);
      long var17x = 73839461233979L;
      int var20 = var17x < 0L ? -1 : 1;
      if (var9 >= 0.0F) {
         var0.arc(
            var10 + var12 - var13 / 2.0F,
            var11 + var13 / 2.0F,
            var13 / 2.0F - 1.6F,
            1.4F,
            -90.0F,
            360.0F * Math.min(1.0F, var9),
            Icons.PURPLE,
            Icons.PINK,
            true
         );
      }

      float var20x = var10 + var12 - var13 / 2.0F;
      float var21 = var11 + var13 / 2.0F;
      var0.push();
      long var22 = 73839461233978L;
      int var22x = var22 < 0L ? -1 : 1;
      var0.rotateAround(var8, var20x, var21 + 4.0F);
      var0.icon(var2, var20x, var21, 10.5F, BlendMode.lerp(Theme.tint(-1580553), -1, var4));
      var0.pop();
      long var25x = 73839461233978L;
      int var24 = var25x < 0L ? -1 : 1;
      if (var4 > 0.02F) {
         var0.pushClip(var10, var11, var12 - var13 + 4.0F, var13, var13 / 2.0F);
         var0.pushAlpha(Math.min(1.0F, var4 * 1.4F));
         var0.text(Fonts.SEMIBOLD, var3, var10 + 9.0F, var11 + var13 / 2.0F, 7.6F, -1, FontStyle.LEFT, FontWeight.MIDDLE);
         var0.popAlpha();
         var0.popClip();
      }

      var0.pop();
   }

   private static void v(Canvas var0, class_437 var1, int var2, long var3, int var5, float var6) {
      long var7 = 89370977407261L;
      HomeGuiBridge.ensureSourceClassInitialized();
      float var9 = N.target(O ? 1.0F : 0.0F).update(MotionStyle.PANEL.dt(var6));
      M.clear();
      float var10;
      int var11 = (var10 = var9 - 0.003F) == 0.0F ? 0 : (var10 < 0.0F ? -1 : 1);
      long var12 = 44182577665023L;
      int var15 = var12 < 0L ? -1 : 1;
      if (var11 < 0) {
         d = new float[4];
      } else {
         float var15x = 178.0F;
         float var16 = 10.0F;
         float var17 = 44.0F;
         float var18 = var16 * 2.0F + var17 * 3.0F - 8.0F;
         float var19 = var1.field_22789 - 8.0F - var15x;
         float var20 = Math.max(4.0F, o[1] - 8.0F - var18);
         d = new float[]{var19, var20, var15x, var18};
         float var21 = Easing.EMPHASIZED.apply(Math.min(1.0F, var9));
         var0.push();
         float var22 = o[0] + o[2] / 2.0F;
         float var23 = o[1];
         var0.translate(var22, var23);
         var0.scale(0.9F + 0.1F * var21);
         var0.translate(-var22, -var23 + (1.0F - var21) * 6.0F);
         var0.pushAlpha(Math.min(1.0F, var9 * 1.3F));
         var0.shadow(var19, var20 + 4.0F, var15x, var18, 12.0F, 26.0F, Theme.tint(1711604496));
         var0.glow(var19, var20, var15x, var18, 12.0F, 22.0F, BlendMode.withAlpha(Icons.VIOLET, 0.14F));
         var0.glass(var19, var20, var15x, var18, 12.0F, Theme.tint(-1205725112), 1.0F, 0.45F, 0.016F);
         var0.roundRectCorners(var19, var20, var15x, var18, 12.0F, 385875967, 218103807, 16777215, 50331647);
         var0.roundRectOutlineGradient(var19, var20, var15x, var18, 12.0F, 1.0F, 90.0F, 1090519039, 218103807);
         HomeModule var24 = HomeUpdater.z;
         float var25 = var20 + var16;
         float var26 = var19 + var16;
         float var27 = var15x - var16 * 2.0F;
         boolean var28 = var9 > 0.6F;
         d(var0, "Time of day", var24.M, t, var26, var25, var27, var2, var7, var5, var6, var28);
         var26 = var19 + var16;
         float var31 = var25 + var17;
         var27 = var15x - var16 * 2.0F;
         var28 = var9 > 0.6F;
         d(var0, "Trees", var24.X, R, var26, var31, var27, var2, var7, var5, var6, var28);
         var26 = var19 + var16;
         var31 = var25 + var17 * 2.0F;
         var27 = var15x - var16 * 2.0F;
         var28 = var9 > 0.6F;
         d(var0, "Camera", var24.q, k, var26, var31, var27, var2, var7, var5, var6, var28);
         var0.popAlpha();
         var0.pop();
      }
   }

   private static void d(
      Canvas var0,
      String var1,
      ModeSetting var2,
      String[][] var3,
      float var4,
      float var5,
      float var6,
      int var7,
      long var8,
      int var10,
      float var11,
      boolean var12
   ) {
      long var13 = 28726965332368L;
      var13 = 61998579488665L;
      short var15 = 14435;
      short var16 = 11086;
      int var17 = (int)(var13 << 48 >>> 48);
      HomeGuiBridge.ensureSourceClassInitialized();
      String var18 = Translations.display(var1).toString().toUpperCase(Locale.ROOT);
      var0.text(Fonts.SEMIBOLD, var18, var4 + 1.0F, var5 + 4.0F, 6.3F, Theme.tint(-6647112), FontStyle.LEFT, FontWeight.MIDDLE, 0.12F);
      var0.text(Fonts.MEDIUM, (CharSequence)var2.get(), var4 + var6 - 1.0F, var5 + 4.0F, 7.0F, Theme.tint(-1185793), FontStyle.RIGHT, FontWeight.MIDDLE);
      float var19 = var5 + 11.0F;
      float var20 = 22.0F;
      var0.roundRect(var4, var19, var6, var20, var20 / 2.0F, 352321535);
      float var21 = var6 / var3.length;

      for (int var22 = 0; var22 < var3.length; var22++) {
         String var23 = var3[var22][0];
         String var24 = var2.id() + ":" + var23;
         float var25 = var4 + var22 * var21;
         boolean var26 = var12 && b(var15, var16, var7, var10, (short)var17, var25, var19, var21, var20);
         if (var26) {
            U = var24;
         }

         Animator var27 = v.computeIfAbsent(var24, var0x -> Animator.smooth(0.0F));
         Animator var28 = v.computeIfAbsent(var24 + "#on", var2xx -> Animator.smooth(var2.is(var23) ? 1.0F : 0.0F));
         float var29 = var27.target(var26 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var11));
         float var30 = var28.target(var2.is(var23) ? 1.0F : 0.0F).update(MotionStyle.TOGGLE.dt(var11));
         float var31 = 2.0F;
         if (var30 > 0.01F) {
            var0.pushAlpha(var30);
            var0.glow(
               var25 + var31,
               var19 + var31,
               var21 - var31 * 2.0F,
               var20 - var31 * 2.0F,
               (var20 - var31 * 2.0F) / 2.0F,
               10.0F,
               BlendMode.withAlpha(Icons.MAGENTA, 0.3F)
            );
            var0.roundRectGradient(
               var25 + var31, var19 + var31, var21 - var31 * 2.0F, var20 - var31 * 2.0F, (var20 - var31 * 2.0F) / 2.0F, 0.0F, Icons.INDIGO, Icons.MAGENTA
            );
            var0.roundRectCorners(
               var25 + var31,
               var19 + var31,
               var21 - var31 * 2.0F,
               var20 - var31 * 2.0F,
               (var20 - var31 * 2.0F) / 2.0F,
               822083583,
               486539263,
               16777215,
               16777215
            );
            var0.popAlpha();
         }

         float var32;
         int var33 = (var32 = var29 - 0.01F) == 0.0F ? 0 : (var32 < 0.0F ? -1 : 1);
         long var34 = 89370977407261L;
         int var37 = var34 < 0L ? -1 : 1;
         if (var33 > 0) {
            float var37x;
            var33 = (var37x = var30 - 0.99F) == 0.0F ? 0 : (var37x < 0.0F ? -1 : 1);
            long var38 = 89370977407260L;
            int var39 = var38 < 0L ? -1 : 1;
            if (var33 < 0) {
               var0.roundRect(
                  var25 + var31,
                  var19 + var31,
                  var21 - var31 * 2.0F,
                  var20 - var31 * 2.0F,
                  (var20 - var31 * 2.0F) / 2.0F,
                  BlendMode.withAlpha(-1, 0.09F * var29)
               );
            }
         }

         var0.icon(
            var3[var22][1], var25 + var21 / 2.0F, var19 + var20 / 2.0F, 10.5F, BlendMode.lerp(BlendMode.lerp(Theme.tint(-5199668), -1, var29), -1, var30)
         );
         M.put(var24, new float[]{var25, var19, var21, var20});
      }
   }

   public static boolean mouseClicked(double var0, double var2, int var4) {
      long var5 = 68913576697420L;
      long var7 = var5 ^ 29959533093396L;
      long var9 = var5 ^ 90002990362193L;
      if (var4 != 0) {
         return O && L(var7, (float)var0, (float)var2, d);
      } else {
         float var11 = (float)var0;
         float var12 = (float)var2;
         if (O) {
            for (Entry var14 : M.entrySet()) {
               if (L(var7, var11, var12, (float[])var14.getValue())) {
                  J((String)var14.getKey(), var9);
                  return true;
               }
            }

            if (L(var7, var11, var12, d)) {
               return true;
            }

            if (!L(var7, var11, var12, o)) {
               j();
               return true;
            }
         }

         if (L(var7, var11, var12, e)) {
            HomeUpdater.get().regrow();
            l.impulse(6.0F);
            HomeUpdater.play(HomeKind.SELECT);
            return true;
         } else if (L(var7, var11, var12, o)) {
            if (O) {
               j();
            } else {
               O = true;
               HomeUpdater.play(HomeKind.EXPAND);
            }

            return true;
         } else {
            return false;
         }
      }
   }

   public static int maskPointer(int var0) {
      long var1 = 26530135646975L;
      long var3 = var1 ^ 67840239345319L;
      class_310 var5 = class_310.method_1551();
      float var6 = (float)var5.field_1729.method_68879(var5.method_22683());
      float var7 = (float)var5.field_1729.method_68883(var5.method_22683());
      return !L(var3, var6, var7, e) && !L(var3, var6, var7, o) && (!O || !L(var3, var6, var7, d)) ? var0 : -10000;
   }

   static void devPopover() {
      O = !O;
   }

   public static boolean allowKey(int var0) {
      if (var0 == 256 && O) {
         j();
         return false;
      } else {
         return true;
      }
   }

   private static void j() {
      O = false;
      HomeUpdater.play(HomeKind.COLLAPSE);
      HomeUpdater.persist();
   }

   private static void J(String var0, long var1) {
      String[] var3 = var0.split(":", 2);
      HomeGuiBridge.ensureSourceClassInitialized();
      HomeModule var4 = HomeUpdater.z;
      String var5 = var3[0];
      byte var6 = -1;
      switch (var5.hashCode()) {
         case 3560141:
            if (var5.equals("time")) {
               long var7 = 122553754890269L;
               int var9 = var7 < 0L ? -1 : 1;
               var6 = 0;
            }
            break;
         case 110624917:
            if (var5.equals("trees")) {
               var6 = 1;
            }
      }
      ModeSetting var14 = switch (var6) {
         case 0 -> var4.M;
         case 1 -> var4.X;
         default -> var4.q;
      };
      long var8 = 122553754890269L;
      int var11 = var8 < 0L ? -1 : 1;
      long var11x = 122553754890268L;
      int var12 = var11x < 0L ? -1 : 1;
      if (!var14.is(var3[1])) {
         var14.set(var3[1]);
         HomeUpdater.play(HomeKind.TAB);
      }
   }

   private static boolean L(long var0, float var2, float var3, float[] var4) {
      long var5 = 61998579488665L;
      short var7 = 14435;
      short var8 = 11086;
      int var9 = (int)(var5 << 48 >>> 48);
      return b(var7, var8, var2, var3, (short)var9, var4[0], var4[1], var4[2], var4[3]);
   }

   private static boolean b(int var0, short var1, float var2, float var3, short var4, float var5, float var6, float var7, float var8) {
      HomeGuiBridge.ensureSourceClassInitialized();
      return var7 > 0.0F && var2 >= var5 && var3 >= var6 && var2 < var5 + var7 && var3 < var6 + var8;
   }

   private static float b(float var0) {
      var0 = Math.max(0.0F, Math.min(1.0F, var0));
      return var0 * var0 * (3.0F - 2.0F * var0);
   }
}
