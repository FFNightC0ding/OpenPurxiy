// Sourced By DevFFNightmare
package dev.purxiy.ui.config;

import dev.purxiy.anim.Easing;
import dev.purxiy.anim.MotionStyle;
import dev.purxiy.render.BlendMode;
import dev.purxiy.render.Canvas;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class ConfigLibraryRenderer10 {
   private static final float FLIGHT = 0.72F;
   private final List<ConfigLibraryRenderer10.TypeV> orbs = new ArrayList<>();
   private final List<ConfigLibraryRenderer10.TypeI> motes = new ArrayList<>();
   private final List<ConfigLibraryRenderer10.TypeJ> rings = new ArrayList<>();
   private int seed = 1;

   void orb(float var1, float var2, float var3, float var4, int var5, float var6,  Runnable var7) {
      ConfigLibraryRenderer10.TypeV var8 = new ConfigLibraryRenderer10.TypeV();
      var8.x0 = var1;
      var8.y0 = var2;
      var8.x1 = var3;
      var8.y1 = var4;
      var8.color = var5;
      var8.born = var6;
      var8.land = var7;
      this.orbs.add(var8);
   }

   void dust(float var1, float var2, float var3, float var4, int var5, float var6, int var7) {
      for (int var8 = 0; var8 < var7; var8++) {
         ConfigLibraryRenderer10.TypeI var9 = new ConfigLibraryRenderer10.TypeI();
         var9.x = var1 + this.random() * var3;
         var9.y = var2 + this.random() * var4;
         var9.vx = (this.random() - 0.5F) * 46.0F;
         var9.vy = -12.0F - this.random() * 46.0F;
         var9.rise = 18.0F + this.random() * 30.0F;
         var9.born = var6 + this.random() * 0.16F;
         var9.life = 0.7F + this.random() * 0.7F;
         var9.size = 0.6F + this.random() * this.random() * 2.2F;
         var9.color = BlendMode.lerp(var5, -1, this.random() * 0.6F);
         this.motes.add(var9);
      }
   }

   void burst(float var1, float var2, int var3, float var4, int var5, float var6) {
      for (int var7 = 0; var7 < var5; var7++) {
         ConfigLibraryRenderer10.TypeI var8 = new ConfigLibraryRenderer10.TypeI();
         double var9 = (var7 + this.random() * 0.6F) / var5 * Math.PI * 2.0;
         float var11 = var6 * (0.6F + this.random() * 0.6F);
         var8.x = var1;
         var8.y = var2;
         var8.vx = (float)Math.cos(var9) * var11;
         var8.vy = (float)Math.sin(var9) * var11;
         var8.rise = 0.0F;
         var8.born = var4;
         var8.life = 0.42F + this.random() * 0.2F;
         var8.size = 1.1F + this.random() * 0.9F;
         var8.color = var3;
         this.motes.add(var8);
      }
   }

   void ring(float var1, float var2, int var3, float var4, float var5, float var6) {
      ConfigLibraryRenderer10.TypeJ var7 = new ConfigLibraryRenderer10.TypeJ();
      var7.x = var1;
      var7.y = var2;
      var7.color = var3;
      var7.born = var4;
      var7.life = 0.65F;
      var7.from = var5;
      var7.to = var6;
      this.rings.add(var7);
   }

   boolean busy() {
      return !this.orbs.isEmpty() || !this.motes.isEmpty() || !this.rings.isEmpty();
   }

   void render(Canvas var1, float var2, float var3) {
      float var4 = MotionStyle.MENU.speed();
      float var5 = var3 * var4;
      Iterator var6 = this.rings.iterator();

      while (var6.hasNext()) {
         ConfigLibraryRenderer10.TypeJ var7 = (ConfigLibraryRenderer10.TypeJ)var6.next();
         float var8 = (var2 - var7.born) * var4 / var7.life;
         if (var8 >= 1.0F) {
            var6.remove();
         } else {
            float var9 = var7.from + (var7.to - var7.from) * Easing.CUBIC_OUT.apply(var8);
            float var10 = (1.0F - var8) * (1.0F - var8);
            var1.ring(var7.x, var7.y, var9, 1.6F, BlendMode.withAlpha(var7.color, 0.85F * var10));
            var1.glowCircle(var7.x, var7.y, var9 * 0.5F, var9 * 0.9F, BlendMode.withAlpha(var7.color, 0.12F * var10));
         }
      }

      Iterator var18 = this.motes.iterator();

      while (var18.hasNext()) {
         ConfigLibraryRenderer10.TypeI var19 = (ConfigLibraryRenderer10.TypeI)var18.next();
         float var21 = (var2 - var19.born) * var4;
         if (!(var21 < 0.0F)) {
            float var23 = var21 / var19.life;
            if (var23 >= 1.0F) {
               var18.remove();
            } else {
               var19.vx = var19.vx * (float)Math.pow(0.1, var5);
               var19.vy = var19.vy * (float)Math.pow(0.18, var5) - var19.rise * var5;
               var19.x = var19.x + var19.vx * var5;
               var19.y = var19.y + var19.vy * var5;
               float var11 = (float)Math.pow(1.0F - var23, 1.4);
               float var12 = var19.size * (1.0F - var23 * 0.5F);
               if (var19.size > 1.4F) {
                  var1.glowCircle(var19.x, var19.y, var12 * 0.6F, var12 * 5.0F, BlendMode.withAlpha(var19.color, 0.32F * var11));
               }

               var1.circle(var19.x, var19.y, var12, BlendMode.withAlpha(var19.color, 0.9F * var11));
            }
         }
      }

      ArrayList var20 = new ArrayList();
      Iterator var22 = this.orbs.iterator();

      while (var22.hasNext()) {
         ConfigLibraryRenderer10.TypeV var24 = (ConfigLibraryRenderer10.TypeV)var22.next();
         float var26 = (var2 - var24.born) * var4 / 0.72F;
         if (var26 >= 1.0F) {
            var22.remove();
            if (!var24.landed) {
               var24.landed = true;
               var20.add(var24);
            }
         } else {
            float var28 = Easing.CUBIC_IN_OUT.apply(var26);
            float var13 = this.arcX(var24, var28);
            float var14 = this.arcY(var24, var28);
            if (this.random() < var5 * 70.0F) {
               ConfigLibraryRenderer10.TypeI var15 = new ConfigLibraryRenderer10.TypeI();
               var15.x = var13 + (this.random() - 0.5F) * 4.0F;
               var15.y = var14 + (this.random() - 0.5F) * 4.0F;
               var15.vx = (this.random() - 0.5F) * 18.0F;
               var15.vy = (this.random() - 0.5F) * 18.0F;
               var15.rise = 6.0F;
               var15.born = var2;
               var15.life = 0.45F + this.random() * 0.35F;
               var15.size = 0.5F + this.random() * 1.1F;
               var15.color = BlendMode.lerp(var24.color, -1, this.random() * 0.7F);
               this.motes.add(var15);
            }

            for (int var29 = 7; var29 >= 1; var29--) {
               float var16 = Easing.CUBIC_IN_OUT.apply(Math.max(0.0F, var26 - var29 * 0.022F));
               float var17 = 1.0F - var29 / 8.0F;
               var1.circle(this.arcX(var24, var16), this.arcY(var24, var16), 3.6F - var29 * 0.35F, BlendMode.withAlpha(var24.color, 0.36F * var17 * var17));
            }

            float var30 = 3.8F * (0.9F + 0.25F * (float)Math.sin(var26 * Math.PI));
            var1.glowCircle(var13, var14, var30 * 1.4F, 34.0F, BlendMode.withAlpha(var24.color, 0.8F));
            var1.glowCircle(var13, var14, var30 * 0.6F, 12.0F, BlendMode.withAlpha(-1, 0.5F));
            var1.circle(var13, var14, var30, BlendMode.lerp(var24.color, -1, 0.7F));
         }
      }

      for (ConfigLibraryRenderer10.TypeV var27 : ((Iterable<ConfigLibraryRenderer10.TypeV>)(Object)var20)) {
         this.ring(var27.x1, var27.y1, var27.color, var2, 4.0F, 46.0F);
         this.burst(var27.x1, var27.y1, BlendMode.lerp(var27.color, -1, 0.4F), var2, 9, 60.0F);
         if (var27.land != null) {
            var27.land.run();
         }
      }
   }

   private float arcX(ConfigLibraryRenderer10.TypeV var1, float var2) {
      float var3 = (var1.x0 + var1.x1) / 2.0F;
      return (1.0F - var2) * (1.0F - var2) * var1.x0 + 2.0F * (1.0F - var2) * var2 * var3 + var2 * var2 * var1.x1;
   }

   private float arcY(ConfigLibraryRenderer10.TypeV var1, float var2) {
      float var3 = Math.abs(var1.x1 - var1.x0) + Math.abs(var1.y1 - var1.y0);
      float var4 = Math.min(var1.y0, var1.y1) - Math.min(160.0F, var3 * 0.35F);
      return (1.0F - var2) * (1.0F - var2) * var1.y0 + 2.0F * (1.0F - var2) * var2 * var4 + var2 * var2 * var1.y1;
   }

   private float random() {
      this.seed = this.seed * 1103515245 + 12345;
      return (this.seed >>> 8 & 65535) / 65535.0F;
   }

   private static final class TypeI {
      float x;
      float y;
      float vx;
      float vy;
      float born;
      float life;
      float size;
      float rise;
      int color;
   }

   private static final class TypeJ {
      float x;
      float y;
      float born;
      float life;
      float from;
      float to;
      int color;
   }

   private static final class TypeV {
      float x0;
      float y0;
      float x1;
      float y1;
      float born;
      int color;
      
      Runnable land;
      boolean landed;
   }
}
