// Sourced By DevFFNightmare
package dev.purxiy.ui;

import com.mojang.blaze3d.buffers.GpuBuffer;
import com.mojang.blaze3d.buffers.Std140Builder;
import com.mojang.blaze3d.systems.RenderSystem;
import dev.purxiy.anim.Animator;
import dev.purxiy.loader.fabric.ClientServices;
import java.nio.ByteBuffer;
import java.util.Arrays;

import org.lwjgl.system.MemoryUtil;

public final class Theme {
   public static final int DESIGN = -4944641;
   private static final float GREY = 0.02F;
   private static final double DESIGN_L;
   private static final double DESIGN_C;
   private static final double DESIGN_H;
   private static final Theme.TypeV ACCENT = new Theme.TypeV();
   private static final Theme.TypeV LOGO = new Theme.TypeV();
   private static boolean started;
   private static final int[] CACHE_KEYS = new int[2048];
   private static final int[] CACHE_VALUES = new int[2048];
   private static int generation = 0;
   private static int logoGeneration = 0;
   
   private static GpuBuffer uniforms;
   
   private static ByteBuffer uniformsStaging;
   private static int uniformsFor = Integer.MIN_VALUE;

   private Theme() {
   }

   public static int accent() {
      return ACCENT.shown;
   }

   public static int generation() {
      return generation;
   }

   public static int tint(int var0) {
      if (ACCENT.turn.identity) {
         return var0;
      } else {
         int var1 = var0 & 16777215;
         int var2 = var1 | 16777216;
         int var3 = CACHE_KEYS.length - 1;
         int var4 = var1 * -1640531535 >>> 21 & var3;

         for (int var5 = 0; var5 < 8; var4 = var4 + 1 & var3) {
            if (CACHE_KEYS[var4] == var2) {
               return var0 & 0xFF000000 | CACHE_VALUES[var4];
            }

            if (CACHE_KEYS[var4] == 0) {
               int var6 = ACCENT.turn.carry(var1);
               CACHE_VALUES[var4] = var6;
               CACHE_KEYS[var4] = var2;
               return var0 & 0xFF000000 | var6;
            }

            var5++;
         }

         return var0 & 0xFF000000 | ACCENT.turn.carry(var1);
      }
   }

   public static int logoTint(int var0) {
      return LOGO.turn.identity ? var0 : var0 & 0xFF000000 | LOGO.turn.carry(var0 & 16777215);
   }

   static int logoGeneration() {
      return logoGeneration;
   }

   static boolean logoMoving() {
      return LOGO.moving;
   }

   static Theme.TypeC logoTurn() {
      return LOGO.turn.copy();
   }

   public static void writeLogo(ByteBuffer var0, int var1) {
      LOGO.turn.write(var0, var1);
   }

   public static GpuBuffer uniforms() {
      if (uniforms == null) {
         uniforms = RenderSystem.getDevice().createBuffer(() -> "Purxiy theme", 136, 32L);
         uniformsStaging = MemoryUtil.memAlloc(32);
      }

      if (uniformsFor != generation) {
         uniformsFor = generation;
         ACCENT.turn.write(uniformsStaging.clear(), 0);
         RenderSystem.getDevice().createCommandEncoder().writeToBuffer(uniforms.slice(), uniformsStaging.position(0).limit(32));
      }

      return uniforms;
   }

   public static int follow(int var0) {
      return (var0 & 16777215) == 11832575 ? var0 & 0xFF000000 | ACCENT.shown & 16777215 : tint(var0);
   }

   public static void frame(float var0) {
      if (!started) {
         snap();
      } else {
         if (ACCENT.frame(ClientServices.accent() | 0xFF000000, var0)) {
            Arrays.fill(CACHE_KEYS, 0);
            generation++;
            Icons.follow();
         }

         if (LOGO.frame(logoTarget(), var0)) {
            logoGeneration++;
         }
      }
   }

   public static void snap() {
      started = true;
      ACCENT.snap(ClientServices.accent() | 0xFF000000);
      LOGO.snap(logoTarget());
      Arrays.fill(CACHE_KEYS, 0);
      generation++;
      logoGeneration++;
      Icons.follow();
   }

   private static int logoTarget() {
      return ClientServices.logo();
   }

   private static double yellow(double var0) {
      var0 = (var0 % 360.0 + 360.0) % 360.0;
      return smoothstep(50.0, 80.0, var0) * (1.0 - smoothstep(105.0, 125.0, var0));
   }

   static double[] lch(int var0) {
      double var1 = linear(var0 >> 16 & 0xFF);
      double var3 = linear(var0 >> 8 & 0xFF);
      double var5 = linear(var0 & 0xFF);
      double var7 = Math.cbrt(0.4122214708 * var1 + 0.5363325363 * var3 + 0.0514459929 * var5);
      double var9 = Math.cbrt(0.2119034982 * var1 + 0.6806995451 * var3 + 0.1073969566 * var5);
      double var11 = Math.cbrt(0.0883024619 * var1 + 0.2817188376 * var3 + 0.6299787005 * var5);
      double var13 = 0.2104542553 * var7 + 0.793617785 * var9 - 0.0040720468 * var11;
      double var15 = 1.9779984951 * var7 - 2.428592205 * var9 + 0.4505937099 * var11;
      double var17 = 0.0259040371 * var7 + 0.7827717662 * var9 - 0.808675766 * var11;
      double var19 = Math.toDegrees(Math.atan2(var17, var15));
      return new double[]{var13, Math.hypot(var15, var17), var19 < 0.0 ? var19 + 360.0 : var19};
   }

   static int fromLch(double var0, double var2, double var4) {
      var0 = clamp(var0, 0.0, 1.0);
      double var6 = Math.cos(Math.toRadians(var4));
      double var8 = Math.sin(Math.toRadians(var4));
      double[] var10 = new double[3];
      if (!linear(var0, var2 * var6, var2 * var8, var10)) {
         double var11 = 0.0;
         double var13 = var2;

         for (int var15 = 0; var15 < 16; var15++) {
            double var16 = (var11 + var13) / 2.0;
            if (linear(var0, var16 * var6, var16 * var8, var10)) {
               var11 = var16;
            } else {
               var13 = var16;
            }
         }

         linear(var0, var11 * var6, var11 * var8, var10);
      }

      return 0xFF000000 | encode(var10[0]) << 16 | encode(var10[1]) << 8 | encode(var10[2]);
   }

   private static boolean linear(double var0, double var2, double var4, double[] var6) {
      double var7 = var0 + 0.3963377774 * var2 + 0.2158037573 * var4;
      double var9 = var0 - 0.1055613458 * var2 - 0.0638541728 * var4;
      double var11 = var0 - 0.0894841775 * var2 - 1.291485548 * var4;
      var7 = var7 * var7 * var7;
      var9 = var9 * var9 * var9;
      var11 = var11 * var11 * var11;
      var6[0] = 4.0767416621 * var7 - 3.3077115913 * var9 + 0.2309699292 * var11;
      var6[1] = -1.2684380046 * var7 + 2.6097574011 * var9 - 0.3413193965 * var11;
      var6[2] = -0.0041960863 * var7 - 0.7034186147 * var9 + 1.707614701 * var11;
      double var13 = 1.0E-4;
      return var6[0] >= -var13 && var6[0] <= 1.0 + var13 && var6[1] >= -var13 && var6[1] <= 1.0 + var13 && var6[2] >= -var13 && var6[2] <= 1.0 + var13;
   }

   private static double linear(int var0) {
      double var1 = var0 / 255.0;
      return var1 <= 0.04045 ? var1 / 12.92 : Math.pow((var1 + 0.055) / 1.055, 2.4);
   }

   private static int encode(double var0) {
      double var2 = clamp(var0, 0.0, 1.0);
      var2 = var2 <= 0.0031308 ? 12.92 * var2 : 1.055 * Math.pow(var2, 0.4166666666666667) - 0.055;
      return (int)Math.round(clamp(var2, 0.0, 1.0) * 255.0);
   }

   private static double wrap(double var0) {
      return (var0 % 360.0 + 540.0) % 360.0 - 180.0;
   }

   private static double smoothstep(double var0, double var2, double var4) {
      double var6 = clamp((var4 - var0) / (var2 - var0), 0.0, 1.0);
      return var6 * var6 * (3.0 - 2.0 * var6);
   }

   private static double clamp(double var0, double var2, double var4) {
      return var0 < var2 ? var2 : Math.min(var0, var4);
   }

   static {
      double[] var0 = lch(-4944641);
      DESIGN_L = var0[0];
      DESIGN_C = var0[1];
      DESIGN_H = var0[2];
   }

   static final class TypeC {
      boolean identity = true;
      double chromaScale = 1.0;
      double hueTurn;
      double lightShift;
      double spread = 1.0;
      double yellowAccent;

      void toward(double var1, double var3, double var5, int var7) {
         this.chromaScale = Math.min(var3 / Theme.DESIGN_C, 1.8);
         this.hueTurn = var3 < 1.0E-4 ? 0.0 : Theme.wrap(var5 - Theme.DESIGN_H);
         this.lightShift = Theme.clamp(var1 - Theme.DESIGN_L, -0.12, 0.06);
         this.spread = 1.0 - 0.6 * Theme.smoothstep(15.0, 75.0, Math.abs(this.hueTurn));
         this.yellowAccent = Theme.yellow(var5);
         this.identity = (var7 & 16777215) == 11832575;
      }

      Theme.TypeC copy() {
         Theme.TypeC var1 = new Theme.TypeC();
         var1.identity = this.identity;
         var1.chromaScale = this.chromaScale;
         var1.hueTurn = this.hueTurn;
         var1.lightShift = this.lightShift;
         var1.spread = this.spread;
         var1.yellowAccent = this.yellowAccent;
         return var1;
      }

      void write(ByteBuffer var1, int var2) {
         Std140Builder.intoBuffer(var1.position(var2))
            .putVec4((float)Math.toRadians(this.hueTurn), (float)this.chromaScale, (float)this.lightShift, (float)this.spread)
            .putVec4((float)this.yellowAccent, this.identity ? 1.0F : 0.0F, (float)Math.toRadians(Theme.DESIGN_H), 0.0F);
      }

      int carry(int var1) {
         double[] var2 = Theme.lch(var1);
         double var3 = var2[0];
         double var5 = var2[1];
         double var7 = var2[2];
         if (var5 < 0.002) {
            return var1;
         } else {
            double var9 = Theme.smoothstep(0.06, 0.12, var5);
            double var11 = Math.min(this.chromaScale, 1.15) + (this.chromaScale - Math.min(this.chromaScale, 1.15)) * var9;
            double var13 = Theme.wrap(var7 - Theme.DESIGN_H) * this.spread;
            if (var13 > 0.0) {
               var13 *= 1.0 - 0.75 * this.yellowAccent;
            }

            double var15 = Theme.DESIGN_H + this.hueTurn + var13;
            double var17 = Theme.smoothstep(0.3, 0.45, var3) * (1.0 - Theme.smoothstep(0.8, 0.93, var3)) * var9;
            double var19 = var3 + this.lightShift * var17;
            var15 = (var15 % 360.0 + 360.0) % 360.0;
            var15 -= 30.0 * Theme.yellow(var15) * (1.0 - Theme.smoothstep(0.55, 0.85, var19));
            return Theme.fromLch(var19, var5 * var11, var15) & 16777215;
         }
      }
   }

   private static final class TypeV {
      private final Animator l = new Animator((float)Theme.DESIGN_L, 120.0F, 22.0F);
      private final Animator c = new Animator((float)Theme.DESIGN_C, 120.0F, 22.0F);
      private final Animator h = new Animator((float)Theme.DESIGN_H, 120.0F, 22.0F);
      final Theme.TypeC turn = new Theme.TypeC();
      int picked = -4944641;
      int shown = -4944641;
      boolean moving;

      boolean frame(int var1, float var2) {
         if (var1 != this.picked) {
            this.picked = var1;
            double[] var3 = Theme.lch(var1);
            float var4 = this.h.value();
            float var5;
            if (var3[1] < 0.02F) {
               var5 = var4;
            } else if (this.c.value() < 0.02F) {
               var5 = (float)var3[2];
               this.h.snap(var5);
            } else {
               var5 = var4 + (float)Theme.wrap(var3[2] - var4);
            }

            this.l.target((float)var3[0]);
            this.c.target((float)var3[1]);
            this.h.target(var5);
            this.moving = true;
         }

         if (!this.moving) {
            return false;
         } else {
            this.l.update(var2);
            this.c.update(var2);
            this.h.update(var2);
            boolean var6 = Math.abs(this.l.value() - this.l.target()) < 5.0E-4F
               && Math.abs(this.c.value() - this.c.target()) < 5.0E-4F
               && Math.abs(this.h.value() - this.h.target()) < 0.05F;
            if (var6) {
               this.snap(this.picked);
            } else {
               this.shown = Theme.fromLch(this.l.value(), this.c.value(), this.h.value());
               this.turn.toward(this.l.value(), this.c.value(), this.h.value(), this.shown);
            }

            return true;
         }
      }

      void snap(int var1) {
         this.picked = var1;
         double[] var2 = Theme.lch(var1);
         this.l.snap((float)var2[0]);
         this.c.snap((float)var2[1]);
         this.h.snap((float)var2[2]);
         this.moving = false;
         this.shown = var1;
         this.turn.toward(var2[0], var2[1], var2[2], var1);
      }
   }
}
