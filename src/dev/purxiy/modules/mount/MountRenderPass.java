package dev.purxiy.modules.mount;

import dev.purxiy.anim.Animator;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.RenderPipelines;
import dev.purxiy.render.font.FontStyle;
import dev.purxiy.render.font.FontWeight;
import dev.purxiy.render.font.Fonts;
import dev.purxiy.ui.Icons;
import dev.purxiy.ui.Theme;
import net.minecraft.class_1074;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3726;
import net.minecraft.class_3959;
import net.minecraft.class_4184;
import net.minecraft.class_638;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;
import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector4f;

public final class MountRenderPass {
   private static final float I = 20.0F;
   private static final float n = 7.0F;
   private static final float b = 3.5F;
   private static final float y = 13.0F;
   private static final float Q = 6.0F;
   private static final float o = 8.0F;
   private static final float c = 8.5F;
   private static final float M = 10.0F;
   private static final float S = 14.0F;
   private static final float O = 0.62F;
   private static final Matrix4f G = new Matrix4f();
   private static final Vector4f g = new Vector4f();
   private static final Animator j = Animator.smooth(0.0F);
   private static final Animator i = Animator.smooth(0.0F);
   private static class_243 f = class_243.field_1353;
   private static class_638 x;
   private static boolean E;
   private static boolean a;

   private MountRenderPass() {
   }

   public static void beginFrame(class_4184 var0, Matrix4fc var1, Matrix4fc var2) {
      E = true;
      x = class_310.method_1551().field_1687;
      f = var0.method_71156();
      G.set(var2).mul(var1);
   }

   public static void reset() {
      j.snap(0.0F);
      i.snap(0.0F);
      a = false;
      E = false;
      x = null;
   }

   public static void render() {
      long var0 = 97821362324679L;
      long var2 = var0 ^ 4210769697384L;
      long var4 = var0 ^ 88141302381806L;
      long var6 = (var0 ^ 111068091988959L) >>> 8;
      int var8 = (int)((var0 ^ 111068091988959L) << 56 >>> 56);
      int var9 = (int)((var0 ^ 2700545117228L) >>> 48);
      long var10 = (var0 ^ 2700545117228L) << 16 >>> 16;
      long var12 = var0 ^ 134004731754261L;
      int var14 = (int)((var0 ^ 134004731754261L) >>> 32);
      int var15 = (int)((var0 ^ 134004731754261L) << 32 >>> 48);
      int var16 = (int)(var12 << 48 >>> 48);
      class_310 var17 = class_310.method_1551();
      MountModule var18 = MountCameraReader.K;
      boolean var19 = E;
      E = false;
      if (var19
         && x == var17.field_1687
         && MountInteraction.available(var17)
         && var18.c.enabled()
         && MountInteraction.visible()
         && !MountInteraction.mounted()
         && var18.z.key() != -1
         && !var17.field_1690.field_1842
         && var17.field_1755 == null
         && var17.method_18506() == null) {
         float var20 = MountCameraState.partialTick();
         class_243 var21 = MountInteraction.renderPosition(var20);
         double var22 = var17.field_1724.method_73189().method_1022(var21);
         if (!(var22 > 7.0) && !(MountInteraction.boarding(var20) > 0.05F)) {
            double var24 = Math.toRadians(MountInteraction.yaw(var20));
            double var26 = Math.cos(var24);
            double var28 = Math.sin(var24);
            class_243 var30 = f.method_1020(var21);
            float var31 = var26 * var30.field_1352 + var28 * var30.field_1350 < 0.0 ? -1.0F : 1.0F;
            MountFactory.MountSpec var32 = MountInteraction.spec();
            float var33 = var32.height() * 0.62F;
            float var34 = var32.bike() ? 0.36F : var32.halfWidth() * 0.94F;
            class_243 var35 = MountCameraState.worldPoint(var31 * var34, var33, 0.0F, var20);
            if (i(var6, (byte)var8, var17, var35) && i(var6, (byte)var8, var17, var21.method_1031(0.0, 0.7, 0.0))) {
               Canvas var36 = RenderPipelines.get().hudCanvas();
               if (!f(var2, var35)) {
                  S();
               } else {
                  float var37 = (g.x / g.w * 0.5F + 0.5F) * var36.width();
                  float var38 = (0.5F - g.y / g.w * 0.5F) * var36.height();
                  if (!f(var2, var21.method_1031(0.0, var33, 0.0))) {
                     S();
                  } else {
                     float var39 = (g.x / g.w * 0.5F + 0.5F) * var36.width();
                     float var40 = var37 >= var39 ? 1.0F : -1.0F;
                     float var41 = RenderPipelines.get().delta();
                     boolean var42 = MountInteraction.canEnter();
                     if (!a) {
                        i.snap(var42 ? 1.0F : 0.0F);
                        a = true;
                     }

                     float var43 = Math.clamp(i.target(var42 ? 1.0F : 0.0F).update(MotionStyle.HOVER.dt(var41)), 0.0F, 1.0F);
                     String var44 = MountInteraction.boardKeyName();
                     String var45 = class_1074.method_4662("purxiy.mount.board", new Object[0]);
                     String var46 = class_1074.method_4662("purxiy.mount.approach", new Object[0]);
                     float var47 = Math.clamp(var36.textWidth(Fonts.SEMIBOLD, var44, 8.5F) + 8.0F, 13.0F, 72.0F);
                     float var48 = var36.textWidth(Fonts.SEMIBOLD, var45, 10.0F);
                     float var49 = var36.textWidth(Fonts.MEDIUM, var46, 10.0F);
                     float var50 = 3.5F + var47 + 6.0F + var48 + (var49 - var48) * (1.0F - var43) + 8.0F;
                     float var51 = 8.0F;
                     float var52 = var40 > 0.0F ? var37 + 14.0F : var37 - 14.0F - var50;
                     if (var52 < var51 || var52 + var50 > var36.width() - var51) {
                        var40 = -var40;
                        var52 = var40 > 0.0F ? var37 + 14.0F : var37 - 14.0F - var50;
                     }

                     if (!(var37 < var51)
                        && !(var37 > var36.width() - var51)
                        && !(var52 < var51)
                        && !(var52 + var50 > var36.width() - var51)
                        && !(var38 < 20.0F + var51)
                        && !(var38 > var36.height() - 20.0F - var51)) {
                        float var53 = Math.clamp(j.target(1.0F).update(MotionStyle.HOVER.dt(var41)), 0.0F, 1.0F);
                        float var54 = (float)Math.clamp((7.0 - var22) / 1.2, 0.0, 1.0);
                        float var55 = var53 * var54;
                        if (!(var55 <= 0.005F)) {
                           float var56 = RenderPipelines.get().time();
                           float var57 = 0.5F + 0.5F * (float)Math.sin(var56 * 2.4F);
                           var36.push();
                           var36.pushAlpha(var55);
                           D(var36, var14, var37, (short)var15, var38, (char)var16, var40, var43);
                           float var58 = var40 > 0.0F ? var52 : var52 + var50;
                           var36.scaleAround(0.92F + 0.08F * var53, var58, var38);
                           var36.translate(var52, var38 - 10.0F);
                           F((short)var9, var36, var10, var50);
                           Y(var36, var4, var44, var47, var43, var57);
                           float var59 = 3.5F + var47 + 6.0F;
                           var36.pushClip(var59 - 1.0F, 0.0F, var50 - var59 - 2.0F, 20.0F);
                           if (var43 > 0.01F) {
                              var36.pushAlpha(var43);
                              var36.text(Fonts.SEMIBOLD, var45, var59, 10.0F, 10.0F, Icons.TEXT, FontStyle.LEFT, FontWeight.MIDDLE);
                              var36.popAlpha();
                           }

                           if (var43 < 0.99F) {
                              var36.pushAlpha(1.0F - var43);
                              var36.text(Fonts.MEDIUM, var46, var59, 10.0F, 10.0F, Icons.TEXT_2, FontStyle.LEFT, FontWeight.MIDDLE);
                              var36.popAlpha();
                           }

                           var36.popClip();
                           var36.popAlpha();
                           var36.pop();
                        }
                     } else {
                        S();
                     }
                  }
               }
            } else {
               S();
            }
         } else {
            S();
         }
      } else {
         S();
      }
   }

   private static void S() {
      j.snap(0.0F);
      a = false;
   }

   private static void D(Canvas var0, int var1, float var2, short var3, float var4, char var5, float var6, float var7) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      var0.softCircle(var2, var4, 2.2F, 3.0F, 1493172224);
      if (var7 > 0.01F) {
         var0.glowCircle(var2, var4, 2.0F, 6.0F, BlendMode.withAlpha(Icons.MAGENTA, 0.32F * var7));
      }

      var0.circle(var2, var4, 1.7F, BlendMode.lerp(-637534209, Theme.tint(-1849089), var7));
      var0.line(var2 + var6 * 4.0F, var4, var2 + var6 * 13.0F, var4, 1.0F, 1090519039, 536870911);
   }

   private static void F(short var0, Canvas var1, long var2, float var4) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      boolean var5 = false;
      var1.shadow(0.0F, 0.0F, var4, 20.0F, 7.0F, 10.0F, 0.0F, 0.0F, 2.0F, BlendMode.withAlpha(-16777216, 0.1364F));
      if (ClientServices.hudLook().glass()) {
         var5 = false;
         var1.glass(0.0F, 0.0F, var4, 20.0F, 7.0F, BlendMode.withAlpha(Theme.tint(-16119788), 0.55F), 0.62F, 1.15F, 0.012F);
      } else {
         var1.roundRect(0.0F, 0.0F, var4, 20.0F, 7.0F, BlendMode.withAlpha(Theme.tint(-16053995), 0.4836F));
      }

      var1.roundRectCorners(0.0F, 0.0F, var4, 20.0F, 7.0F, BlendMode.fade(352321535, 0.62F), BlendMode.fade(184549375, 0.62F), 16777215, 16777215);
      var1.roundRectOutlineGradient(0.0F, 0.0F, var4, 20.0F, 7.0F, 1.0F, 90.0F, BlendMode.fade(654311423, 0.62F), BlendMode.fade(150994943, 0.62F));
   }

   private static void Y(Canvas var0, long var1, String var3, float var4, float var5, float var6) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      float var7 = 3.5F;
      var0.roundRectBordered(3.5F, 3.5F, var4, 13.0F, var7, 318767103, 1.0F, 486539263);
      long var8 = 9750390061097L;
      int var11 = var8 < 0L ? -1 : 1;
      long var11x = 9750390061097L;
      int var12 = var11x < 0L ? -1 : 1;
      if (var5 > 0.01F) {
         var0.glow(3.5F, 3.5F, var4, 13.0F, var7, 9.0F, BlendMode.withAlpha(Icons.MAGENTA, (0.2F + 0.12F * var6) * var5));
         var0.pushAlpha(var5);
         var0.roundRectGradient(3.5F, 3.5F, var4, 13.0F, var7, 135.0F, Icons.INDIGO, Icons.MAGENTA);
         var0.roundRectCorners(3.5F, 3.5F, var4, 13.0F, var7, 872415231, 352321535, 16777215, 16777215);
         var0.popAlpha();
      }

      var0.textEllipsized(
         Fonts.SEMIBOLD, var3, 3.5F + var4 / 2.0F, 10.0F, 8.5F, var4 - 6.0F, BlendMode.lerp(Icons.TEXT_2, -1, var5), FontStyle.CENTER, FontWeight.MIDDLE
      );
   }

   private static boolean f(long var0, class_243 var2) {
      MountFactory.MountSpec.ensureSourceClassInitialized();
      long var3 = 100208748994222L;
      int var5 = var3 < 0L ? -1 : 1;
      g.set((float)(var2.field_1352 - f.field_1352), (float)(var2.field_1351 - f.field_1351), (float)(var2.field_1350 - f.field_1350), 1.0F);
      G.transform(g);
      return Float.isFinite(g.w) && g.w > 0.05F && g.z >= -g.w && g.z <= g.w;
   }

   private static boolean i(long var0, byte var2, class_310 var3, class_243 var4) {
      return var3.field_1687.method_17742(new class_3959(f, var4, class_3960.field_23142, class_242.field_1348, class_3726.method_16194())).method_17783()
         == class_240.field_1333;
   }
}
