package dev.purxiy.modules.place;

public final class PlaceMotion {
   private static final float n = 0.24F;
   private static final float y = 3.0F;
   public static final float h = 0.002F;
   private static final float C = 1.15F;
   private static final float b = 0.42F;
   public static int O = 0;

   private PlaceMotion() {
   }

   public static PlaceMotion.PlacePose placed(PlaceMotion.Style var0, float var1, float var2, PlaceMotion.PlacePose var3) {
      if (var1 >= 1.0F) {
         return var3.rest();
      } else {
         float var4 = Math.max(var1, 0.0F);
         float var5 = placedGlow(var4);
         switch (var0) {
            case C:
               float var6 = spring(var4, var2);
               float var7 = 0.5F + 0.5F * spring(Math.min(var4 * 1.12F, 1.0F), var2 * 0.6F);
               return var3.set(var6, var7, 0.0F, 0.0F, var5);
            case z:
               float var8 = spring(var4, var2);
               float var9 = 1.0F - var4;
               float var10 = (float) (-Math.PI / 2) * var9 * var9 * var9;
               return var3.set(var8, var8, var10, 0.0F, var5);
            case y:
               if (var4 < 0.42F) {
                  float var14 = var4 / 0.42F;
                  float var16 = 0.55F + 0.45F * (1.0F - (1.0F - var14) * (1.0F - var14));
                  float var17 = 0.14F * var14 * var14 * (0.4F + 0.6F * var2);
                  return var3.set(var16 * (1.0F + var17), var16 * (1.0F - 0.5F * var17), 0.0F, 1.15F * (1.0F - var14 * var14), var5);
               }

               float var11 = (var4 - 0.42F) / 0.58000004F;
               float var12 = (0.07F + 0.2F * var2) * (float)Math.exp(-4.2F * var11) * (float)Math.cos(8.482300314495253 * var11);
               var12 *= 1.0F - smoothstep(0.72F, 1.0F, var11);
               return var3.set(1.0F - var12, 1.0F + 0.5F * var12, 0.0F, 0.0F, var5);
            default:
               float var13 = spring(var4, var2);
               return var3.set(var13, var13, 0.0F, 0.0F, var5);
         }
      }
   }

   public static PlaceMotion.PlacePose broken(float var0, PlaceMotion.PlacePose var1) {
      float var2 = Math.clamp(var0, 0.0F, 1.0F);
      float var3;
      if (var2 < 0.22F) {
         var3 = 1.0F + 0.07F * (float)Math.sin(Math.PI * var2 / 0.22F);
      } else {
         float var4 = (var2 - 0.22F) / 0.78F;
         var3 = 1.0F - (float)Math.pow(var4, 1.6);
      }

      return var1.set(var3, var3, 0.0F, 0.0F, 0.85F * smoothstep(0.05F, 0.7F, var2));
   }

   static float placedGlow(float var0) {
      float var1 = 1.0F - smoothstep(0.0F, 0.85F, var0);
      return var1 * (float)Math.sqrt(var1);
   }

   public static float spring(float var0, float var1) {
      if (var0 <= 0.0F) {
         return 0.0F;
      } else if (var0 >= 1.0F) {
         return 1.0F;
      } else {
         float var2 = 0.24F * Math.clamp(var1, 0.0F, 1.0F);
         float var3;
         if (var2 < 0.001F) {
            var3 = 1.0F;
         } else {
            double var4 = Math.log(var2);
            var3 = (float)(-var4 / Math.sqrt(9.869604401089358 + var4 * var4));
         }

         float var9 = Math.max(5.4F / var3, 8.0F);
         double var5;
         if (var3 >= 0.999F) {
            var5 = (-1.0 + (3.0F - var9) * var0) * Math.exp(-var9 * var0);
         } else {
            double var7 = var9 * Math.sqrt(1.0 - var3 * var3);
            var5 = Math.exp(-var3 * var9 * var0) * (-Math.cos(var7 * var0) + (3.0F - var3 * var9) / var7 * Math.sin(var7 * var0));
         }

         float var10 = (float)(1.0 + var5);
         return var10 + (1.0F - var10) * smoothstep(0.8F, 1.0F, var0);
      }
   }

   static float smoothstep(float var0, float var1, float var2) {
      float var3 = Math.clamp((var2 - var0) / (var1 - var0), 0.0F, 1.0F);
      return var3 * var3 * (3.0F - 2.0F * var3);
   }

   public static void ensureSourceClassInitialized() {
   }

   public static enum Style {
      I,
      C,
      z,
      y;

      public boolean fromFace() {
         return this == C || this == y;
      }
   }

   public static final class PlacePose {
      public float Z = 1.0F;
      public float t = 1.0F;
      public float h;
      public float u;
      public float W;

      public PlaceMotion.PlacePose set(float var1, float var2, float var3, float var4, float var5) {
         this.Z = Math.max(var1, 0.002F);
         this.t = Math.max(var2, 0.002F);
         this.h = var3;
         this.u = var4;
         this.W = var5;
         return this;
      }

      public PlaceMotion.PlacePose rest() {
         return this.set(1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
      }
   }
}
