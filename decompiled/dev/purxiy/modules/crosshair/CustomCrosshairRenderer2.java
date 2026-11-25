package dev.purxiy.modules.crosshair;

import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.api.HudLayer;
import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.module.ModuleWidget;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import net.minecraft.class_11631;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_310;
import net.minecraft.class_3966;
import net.minecraft.class_4061;
import net.minecraft.class_638;

public final class CustomCrosshairRenderer2 {
   private static CustomCrosshairModule L;
   private static ModuleContext E;
   private static long n = -1L;
   private static long s = 0L;
   private static class_638 I;
   private static float T;
   private static float l;
   private static float u;
   private static float G = 1.0F;

   private CustomCrosshairRenderer2() {
   }

   public static AutoCloseable install(CustomCrosshairModule var0, ModuleContext var1) {
      L = var0;
      E = var1;
      var1.own(ModuleWidget.onHudExtraction(var0x -> {
         long var1x = 85769655460215L;
         CustomCrosshairRenderer3.ensureSourceClassInitialized();
         return var0x == HudLayer.CROSSHAIR && c(var1x);
      }));
      var1.own(ModuleWidget.onTick(var0x -> {
         CustomCrosshairRenderer3.ensureSourceClassInitialized();
         if (I != var0x.field_1687) {
            reset();
            I = var0x.field_1687;
         }
      }));
      var1.own(ModuleWidget.onDamage(CustomCrosshairRenderer2::onDamage));
      return () -> {
         reset();
         I = null;
         L = null;
         E = null;
      };
   }

   private static boolean p(class_310 var0, long var1, CustomCrosshairModule var3) {
      CustomCrosshairRenderer3.ensureSourceClassInitialized();
      long var4 = 55945143136687L;
      int var7 = var4 < 0L ? -1 : 1;
      if (var0.field_1687 != null && var0.field_1724 != null && var0.field_1724.method_5805()) {
         long var7x = 55945143136687L;
         int var9 = var7x < 0L ? -1 : 1;
         if (!var0.field_1724.method_7325()) {
            long var9x = 55945143136687L;
            int var11 = var9x < 0L ? -1 : 1;
            if (!var0.field_1690.field_1842 && var0.field_1755 == null && var0.method_18506() == null && !var0.field_1724.method_31550()) {
               long var11x = 55945143136687L;
               int var13 = var11x < 0L ? -1 : 1;
               var13 = 1100861469;
               int var6x = -1100861009;
               if (ModuleWidget.claimed("camera-detached")) {
                  if (!ModuleWidget.claimed("hide-crosshair")) {
                     return true;
                  }
               } else {
                  boolean var14 = ModuleWidget.claimed("camera-orbit");
                  long var15 = 55945143136687L;
                  int var17 = var15 < 0L ? -1 : 1;
                  if (var14 ? ModuleWidget.claimed("camera-in-head") : var0.field_1690.method_31044().method_31034() || var3.D.enabled()) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   public static void render() {
      long var0 = 58859324381303L;
      long var2 = var0 ^ 8151839353304L;
      class_310 var4 = class_310.method_1551();
      CustomCrosshairModule var5 = L;
      RenderPipelines var6 = RenderPipelines.get();
      if (var5.enabled() && n == var6.frame() && p(var4, var2, var5)) {
         float var7 = var4.method_1493() ? 0.0F : var6.delta();
         T = T + MotionStyle.HOVER.dt(var7) * var5.x.floatValue();
         float var8 = 1.0F - (float)Math.exp(-var7 * 16.0F);
         float var9 = (float)Math.min(1.0, var4.field_1724.method_18798().method_37267() / 0.22);
         l = l + (var9 - l) * var8;
         boolean var10 = var4.field_1765 instanceof class_3966 var12
            && var12.method_17782() instanceof class_1309 var11
            && var11.method_5805()
            && !var11.method_5756(var4.field_1724);
         u = u + ((var10 ? 1 : 0) - u) * var8;
         float var17 = var5.a.enabled() && var4.field_1724.method_6115() ? 0.0F : 1.0F;
         G = G + (var17 - G) * var8;
         float var18 = var4.method_61966().method_60637(true);
         float var20 = (float)Math.sin(var4.field_1724.method_6055(var18) * Math.PI);
         float var14 = s == 0L ? 0.0F : Math.max(0.0F, 1.0F - (float)(System.nanoTime() - s) / 3.2E8F);
         boolean var15 = !var5.d.is("Off") && (!var5.d.is("Auto") || var4.field_1690.method_42565().method_41753() == class_4061.field_18152);
         Canvas var16 = var6.hudCanvas();
         var16.pushAlpha(G);
         CustomCrosshairRenderer3.draw(
            var16,
            var5,
            (String)var5.k.get(),
            var16.width() / 2.0F,
            var16.height() / 2.0F,
            var16.height() / 720.0F,
            T,
            l,
            var20,
            u,
            var14,
            var4.field_1724.method_7261(var18),
            var15
         );
         var16.popAlpha();
         E.metric("drawn", 1.0);
         E.metric("frame", var6.frame());
      }
   }

   public static void onDamage(class_1297 var0, int var1) {
      long var2 = 109799010961722L;
      long var4 = var2 ^ 89323196758165L;
      class_310 var6 = class_310.method_1551();
      CustomCrosshairModule var7 = L;
      if (var7.enabled()
         && p(var6, var4, var7)
         && var6.field_1724 != null
         && var1 == var6.field_1724.method_5628()
         && var0 instanceof class_1309
         && var0 != var6.field_1724
         && !var0.method_5756(var6.field_1724)) {
         s = System.nanoTime();
         E.metric("confirmed_hit", 1.0);
         var7.playHitSound();
      }
   }

   public static void reset() {
      n = -1L;
      s = 0L;
      u = 0.0F;
      l = 0.0F;
      T = 0.0F;
      G = 1.0F;
   }

   private static boolean c(long var0) {
      long var2 = 55945143136687L;
      CustomCrosshairRenderer3.ensureSourceClassInitialized();
      class_310 var4 = class_310.method_1551();
      if (L != null && L.enabled() && var4.field_1724 != null && !var4.field_1724.method_7325() && !var4.field_61504.method_72772(class_11631.field_61583)) {
         if (p(var4, var2, L)) {
            n = RenderPipelines.get().frame();
         }

         return true;
      } else {
         return false;
      }
   }
}
