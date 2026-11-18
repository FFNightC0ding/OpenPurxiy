// Sourced By DevFFNightmare
package dev.purxiy.modules.shockwave;

public final class ShockwaveSupport3 {
   public static final int D = 0;
   public static final int A = 1;
   public static final int Z;
   public static final float l = 0.25F;
   public static final float S = 0.45F;
   static final float u = 12.0F;
   static final float e = 0.4F;
   static final float i = 0.17F;
   static final float E = 0.35F;
   static final float c = 1.45F;
   static final float U = 1.25F;
   static final float h = 0.045F;
   static final float T = 0.26F;
   static final float L = 0.6F;
   static final float d = 2.1F;
   static final float w = 0.4F;
   private static final float m = (float) (Math.PI * 2);
   private static final float s = t();

   private ShockwaveSupport3() {
   }

   static float level(double var0) {
      return 1.0F - smoothstep(0.6F, 2.1F, (float)Math.abs(var0));
   }

   public static float sink(ShockwaveSupport3.TypeS var0, double var1, double var3, double var5, double var7) {
      float var9 = (float)(var7 - var0.b);
      if (var9 <= 0.0F) {
         return 0.0F;
      } else {
         float var10 = distance(var0, var1, var3);
         float var11 = var0.amplitude(var10);
         if (var11 <= 0.0F) {
            return 0.0F;
         } else {
            float var12 = var11 * level(var5 - var0.o);
            if (var0.a == 1) {
               return var12 * press(var9);
            } else {
               float var13 = var9 - var0.arrival(var10);
               return var13 <= 0.0F ? 0.0F : var12 * swing(var13);
            }
         }
      }
   }

   public static float shine(ShockwaveSupport3.TypeS var0, double var1, double var3, double var5, double var7) {
      float var9 = (float)(var7 - var0.b);
      if (var9 <= 0.0F) {
         return 0.0F;
      } else {
         float var10 = distance(var0, var1, var3);
         float var11 = var0.amplitude(var10);
         if (var11 <= 0.0F) {
            return 0.0F;
         } else {
            float var12 = Math.min(var11 / 0.12F, 1.0F) * level(var5 - var0.o);
            if (var0.a == 1) {
               return var12 * 0.5F * press(var9);
            } else {
               float var13 = var9 - var0.arrival(var10);
               return var13 <= 0.0F ? 0.0F : var12 * smoothstep(0.0F, 0.035F, var13) * (float)Math.exp(-var13 / 0.4F);
            }
         }
      }
   }

   static float swing(float var0) {
      return (float)(Math.sin((float) (Math.PI * 2) * var0 / 0.4F) * Math.exp(-var0 / 0.17F));
   }

   static float press(float var0) {
      return (float)((1.0 - Math.exp(-var0 / 0.045F)) * Math.exp(-var0 / 0.26F)) / s;
   }

   private static float t() {
      double var0 = 0.045F;
      double var2 = 0.26F;
      double var4 = var0 * Math.log((var0 + var2) / var0);
      return (float)((1.0 - Math.exp(-var4 / var0)) * Math.exp(-var4 / var2));
   }

   public static float turn(ShockwaveSupport3.TypeS var0, double var1, double var3) {
      double var5 = Math.atan2(var3 - var0.H, var1 - var0.J) / (float) (Math.PI * 2);
      double var7 = var5 + var0.w;
      return (float)(var7 - Math.floor(var7));
   }

   static float distance(ShockwaveSupport3.TypeS var0, double var1, double var3) {
      double var5 = var1 - var0.J;
      double var7 = var3 - var0.H;
      return (float)Math.sqrt(var5 * var5 + var7 * var7);
   }

   static float smoothstep(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   static {
      long var0 = 40417838438859L;
      long var2 = -1782681935569020471L ^ var0;
      Z = (int)var2;
   }

   public static final class TypeS {
      public final double J;
      public final double H;
      public final double o;
      public final double b;
      public final float y;
      public final float S;
      public final float w;
      public final int a;
      final float r;

      public TypeS(double var1, double var3, double var5, double var7, float var9, float var10, float var11, int var12) {
         this.J = var1;
         this.H = var3;
         this.o = var5;
         this.b = var7;
         this.y = Math.max(var9, 0.5F);
         this.S = var10;
         this.w = var11;
         this.a = var12;
         this.r = 2.0F * this.y / 12.0F;
      }

      public float life() {
         return this.a == 1 ? 1.3F : this.r + 1.02F;
      }

      public boolean alive(double var1) {
         return var1 - this.b < this.life();
      }

      public float extent() {
         return this.a == 1 ? 1.75F : this.y;
      }

      public float front(float var1) {
         if (this.a == 1) {
            return 1.25F;
         } else {
            float var2 = Math.clamp(var1 / this.r, 0.0F, 1.0F);
            return this.y * (1.0F - (1.0F - var2) * (1.0F - var2));
         }
      }

      float arrival(float var1) {
         return var1 >= this.y ? Float.POSITIVE_INFINITY : this.r * (1.0F - (float)Math.sqrt(1.0F - var1 / this.y));
      }

      float amplitude(float var1) {
         if (this.a == 1) {
            return this.S * (1.0F - ShockwaveSupport3.smoothstep(0.3F, 1.25F, var1));
         } else if (var1 >= this.y) {
            return 0.0F;
         } else {
            float var2 = var1 / this.y;
            return this.S * ShockwaveSupport3.smoothstep(0.35F, 1.45F, var1) * (float)Math.pow(1.0F - var2 * var2, 1.2F);
         }
      }
   }
}
