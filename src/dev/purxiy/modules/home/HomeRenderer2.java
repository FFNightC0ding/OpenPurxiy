// Sourced By DevFFNightmare
package dev.purxiy.modules.home;

import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import dev.purxiy.render.texture.TextureLoader;
import dev.purxiy.ui.Theme;

final class HomeRenderer2 {
   static final float l = 0.155F;
   static final float y = 0.15F;
   private static final float d = 0.9244F;
   private static final float Y = 0.3156F;
   private static final float u = 0.2F;
   private static final float k = 0.5F;
   private static final float W = 0.26F;
   static final float b = -1.75F;
   static final float S = 1.75F;
   
   private static TextureLoader T;
   
   private static TextureLoader K;
   
   private static TextureLoader I;





   private HomeRenderer2() {
   }

   private static void w(long var0) {
      HomeSupport2.ensureSourceClassInitialized();
      if (T == null) {
         T = TextureLoader.load("textures/brand/mark.png");
         K = TextureLoader.load("textures/brand/mark_glow.png");
         I = TextureLoader.load("textures/brand/wordmark.png");
      }
   }

   static float height(float var0) {
      return var0 * 1.305F;
   }

   static float markWidth(float var0) {
      long var1 = 66172171067926L;
      long var3 = var1 ^ 117422507191596L;
      w(var3);
      return var0 * T.aspect();
   }

   static float sweepAt(float var0) {
      return -1.75F + 3.5F * var0;
   }

   static void draw(Canvas var0, float var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8, float var9) {
      draw(var0, var1, var2, var3, var4, var5, var6, var7, var8, var9, -1.75F, 0.0F, 0.0F);
   }

   static void draw(
      Canvas var0,
      float var1,
      float var2,
      float var3,
      float var4,
      float var5,
      float var6,
      float var7,
      float var8,
      float var9,
      float var10,
      float var11,
      float var12
   ) {
      long var13 = 81030312126126L;
      long var15 = var13 ^ 34459343399316L;
      int var17 = (int)((var13 ^ 58159808906397L) >>> 32);
      long var18 = (var13 ^ 58159808906397L) << 32 >>> 32;
      w(var15);
      if (!(var4 <= 0.003F)) {
         float var21 = var3 * T.aspect();
         float var22 = var1 - var21 / 2.0F;
         float var23 = var3 * 0.2F;
         var0.pushAlpha(var4);
         var0.glowCircle(
            var1, var2 + var3 * 0.52F, var3 * 0.34F, var3 * 0.85F, BlendMode.withAlpha(Theme.logoTint(-7860231), 0.2F + 0.08F * var6 + 0.12F * var12)
         );
         if (var9 > 0.01F) {
            var0.softCircle(var1, var2 + var3 * 0.52F, var3 * 0.42F, var3 * 0.5F, BlendMode.withAlpha(Theme.logoTint(-16120298), 0.22F * var9));
         }

         var0.logo(T, var22, var2, var21, var3, -1);
         if (var6 > 0.01F) {
            var0.pushAlpha(var6);
            var0.logo(K, var22 - var23, var2 - var23, var21 + 2.0F * var23, var3 + 2.0F * var23, -1);
            var0.popAlpha();
         }

         if (var11 > 0.003F && var10 > -1.75F && var10 < 1.75F) {
            var0.imageSheen(
               K, var22 - var23, var2 - var23, var21 + 2.0F * var23, var3 + 2.0F * var23, BlendMode.withAlpha(-1, 0.62F * var11), 0.5F, var10, 0.26F
            );
         }

         if (var7 > 0.01F || var12 > 0.01F) {
            T(var0, var22 + var21 * 0.9244F, var2 + var3 * 0.3156F, var17, var3 * 0.085F, var18, Math.max(var7, var12), var8, var12);
         }

         if (var5 > 0.003F) {
            float var24 = var3 * 0.155F;
            float var25 = var24 * I.aspect();
            float var26 = var2 + var3 * 1.15F;
            float var27 = var5 * var5 * (3.0F - 2.0F * var5);
            if (var9 > 0.01F) {
               var0.shadow(
                  var1 - var25 / 2.0F, var26, var25, var24, var24 / 2.0F, var24 * 1.4F, BlendMode.withAlpha(Theme.logoTint(-16317425), 0.32F * var9 * var27)
               );
            }

            float var28 = var25 * var27;
            var0.pushClip(var1 - var28 / 2.0F, var26 - var24, var28, var24 * 3.0F);
            var0.pushAlpha(var27);
            var0.image(I, var1 - var25 / 2.0F, var26 + (1.0F - var27) * var24 * 0.35F, var25, var24, 0.0F, -1);
            var0.popAlpha();
            var0.popClip();
         }

         var0.popAlpha();
      }
   }

   private static void T(Canvas var0, float var1, float var2, int var3, float var4, long var5, float var7, float var8, float var9) {
      float var10 = Math.max((float)Math.pow(Math.max(0.0, Math.sin(var8 * 1.7F)), 6.0), var9);
      float var11 = var4 * (0.8F + 0.55F * var10 + 0.6F * var9);
      float var12 = var7 * (0.35F + 0.65F * var10);
      var0.glowCircle(var1, var2, var11 * 0.18F, var11 * 1.6F, BlendMode.withAlpha(Theme.logoTint(-42279), 0.38F * var12 + 0.3F * var9));
      var0.line(
         var1 - var11,
         var2,
         var1,
         var2,
         var11 * 0.12F,
         BlendMode.withAlpha(Theme.logoTint(-2817), 0.0F),
         BlendMode.withAlpha(Theme.logoTint(-2817), 0.9F * var12)
      );
      var0.line(
         var1,
         var2,
         var1 + var11,
         var2,
         var11 * 0.12F,
         BlendMode.withAlpha(Theme.logoTint(-2817), 0.9F * var12),
         BlendMode.withAlpha(Theme.logoTint(-2817), 0.0F)
      );
      var0.line(
         var1,
         var2 - var11,
         var1,
         var2,
         var11 * 0.12F,
         BlendMode.withAlpha(Theme.logoTint(-2817), 0.0F),
         BlendMode.withAlpha(Theme.logoTint(-2817), 0.9F * var12)
      );
      var0.line(
         var1,
         var2,
         var1,
         var2 + var11,
         var11 * 0.12F,
         BlendMode.withAlpha(Theme.logoTint(-2817), 0.9F * var12),
         BlendMode.withAlpha(Theme.logoTint(-2817), 0.0F)
      );
      var0.circle(var1, var2, var11 * 0.12F, BlendMode.withAlpha(-1, var12));
   }

   static void closeTextures() {
      if (T != null) {
         T.close();
         T = null;
      }

      if (K != null) {
         K.close();
         K = null;
      }

      if (I != null) {
         I.close();
         I = null;
      }
   }

   public static void releaseResources() {
   }

}
