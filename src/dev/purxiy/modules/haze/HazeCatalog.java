package dev.purxiy.modules.haze;

import java.util.List;

public final class HazeCatalog {
   public static final HazeCatalog x = new HazeCatalog(
      "Rose", 16753612, 13808895, 16761768, 0.55F, 16771312, 16757390, 2757936, "Soft pink, lilac far away and peach drifting through it"
   );
   public static final HazeCatalog d = new HazeCatalog(
      "Lilac", 14066423, 12760575, 16758494, 0.55F, 16116991, 16758482, 1774901, "SourceByDevFFnightmare DeObfDeCompByGuideX's own lilac, with a blush of pink"
   );
   public static final HazeCatalog t = new HazeCatalog(
      "Peach", 16760474, 16766662, 16752824, 0.5F, 16773854, 16753766, 2824212, "Warm apricot and cream, like a summer evening"
   );
   public static final HazeCatalog a = new HazeCatalog(
      "Sunset", 16752318, 16762250, 16748166, 0.45F, 16770744, 16751196, 2888226, "Pink around you, gold towards the horizon"
   );
   public static final HazeCatalog k = new HazeCatalog(
      "Mint", 10154196, 10673407, 13828030, 0.5F, 15794168, 16771256, 861218, "Cool mint and aqua, fresh as the morning"
   );
   public static final HazeCatalog j = new HazeCatalog(
      "Ocean", 8830975, 9033721, 9235183, 0.5F, 15660287, 16765620, 988722, "Soft sky blue, turning to aqua in the distance"
   );
   public static final HazeCatalog Y = new HazeCatalog(
      "Aurora", 8843980, 12096255, 16750290, 0.9F, 15920383, 16762068, 924716, "Mint, violet and pink drifting through each other"
   );
   public static final String S = "Custom";
   private static final String s = "Your own colour; the rest of the haze is drawn from it";
   static final List<HazeCatalog> U = List.of(x, d, t, a, k, j, Y);
   private static volatile HazeCatalog i;
   private static volatile int g;
   public final String p;
   public final int R;
   public final int c;
   public final int r;
   public final float L;
   public final int E;
   public final int N;
   public final int n;
   public final String T;
   public static int G = 0;

   private HazeCatalog(String var1, int var2, int var3, int var4, float var5, int var6, int var7, int var8, String var9) {
      this.p = var1;
      this.R = var2;
      this.c = var3;
      this.r = var4;
      this.L = var5;
      this.E = var6;
      this.N = var7;
      this.n = var8;
      this.T = var9;
   }

   public static String[] names() {
      String[] var0 = new String[U.size() + 1];

      for (int var1 = 0; var1 < U.size(); var1++) {
         var0[var1] = U.get(var1).p;
      }

      var0[U.size()] = "Custom";
      return var0;
   }

   public static HazeCatalog byName(String var0, int var1) {
      for (HazeCatalog var3 : U) {
         if (var3.p.equals(var0)) {
            return var3;
         }
      }

      return "Custom".equals(var0) ? from(var1) : x;
   }

   public static HazeCatalog from(int var0) {
      long var1 = 51931452176569L;
      long var3 = var1 ^ 32326474043371L;
      HazeCatalog var5 = i;
      if (var5 != null && g == (var0 & 16777215)) {
         return var5;
      } else {
         HazeCatalog var6 = l(var3, var0 & 16777215);
         g = var0 & 16777215;
         i = var6;
         return var6;
      }
   }

   private static HazeCatalog l(long var0, int var2) {
      float[] var3 = lch(var2);
      float var4 = Math.clamp(var3[0], 0.62F, 0.9F);
      float var5 = Math.min(var3[1], 0.16F);
      float var6 = var3[2];
      int var7 = fromLch(var4, var5, var6);
      int var8 = fromLch(Math.min(var4 + 0.03F, 0.92F), var5 * 0.85F, var6 + 28.0F);
      int var9 = fromLch(Math.min(var4 + 0.02F, 0.92F), var5 * 0.9F, var6 - 32.0F);
      int var10 = fromLch(0.95F, var5 * 0.25F, var6);
      int var11 = mix(fromLch(0.84F, var5 * 0.8F, var6), 16756848, 0.5F);
      int var12 = fromLch(0.2F, var5 * 0.45F, var6);
      return new HazeCatalog("Custom", var7, var8, var9, 0.5F, var10, var11, var12, "Your own colour; the rest of the haze is drawn from it");
   }

   static float channel(int var0, int var1) {
      return (var0 >> 16 - 8 * var1 & 0xFF) / 255.0F;
   }

   static int mix(int var0, int var1, float var2) {
      long var3 = 128629960391484L;
      long var5 = var3 ^ 56782222095086L;
      long var7 = var3 ^ 28461976167745L;
      float[] var9 = o(var0, var5);
      float[] var10 = o(var1, var5);
      return J(var9[0] + (var10[0] - var9[0]) * var2, var9[1] + (var10[1] - var9[1]) * var2, var7, var9[2] + (var10[2] - var9[2]) * var2);
   }

   static float[] lch(int var0) {
      long var1 = 6255589808865L;
      long var3 = var1 ^ 73564820640563L;
      float[] var5 = o(var0, var3);
      float var6 = (float)Math.toDegrees(Math.atan2(var5[2], var5[1]));
      return new float[]{var5[0], (float)Math.hypot(var5[1], var5[2]), var6 < 0.0F ? var6 + 360.0F : var6};
   }

   static int fromLch(float var0, float var1, float var2) {
      long var3 = 120540260001955L;
      long var5 = var3 ^ 44753871532027L;
      long var7 = var3 ^ 821840508638L;
      float var9 = (float)Math.cos(Math.toRadians(var2));
      float var10 = (float)Math.sin(Math.toRadians(var2));
      float var11 = 0.0F;
      float var12 = var1;
      if (!w(var0, var1 * var9, var5, var1 * var10)) {
         for (int var13 = 0; var13 < 14; var13++) {
            float var14 = (var11 + var12) * 0.5F;
            if (w(var0, var14 * var9, var5, var14 * var10)) {
               var11 = var14;
            } else {
               var12 = var14;
            }
         }

         var1 = var11;
      }

      return J(var0, var1 * var9, var7, var1 * var10);
   }

   private static boolean w(float var0, float var1, long var2, float var4) {
      float[] var5 = k(var0, var1, var4);
      HazeRenderPass.ensureSourceClassInitialized();

      for (float var9 : var5) {
         float var10;
         int var11 = (var10 = var9 - -1.0E-4F) == 0.0F ? 0 : (var10 < 0.0F ? -1 : 1);
         long var12 = 75958207743832L;
         int var14 = var12 < 0L ? -1 : 1;
         if (var11 < 0 || var9 > 1.0001F) {
            return false;
         }
      }

      return true;
   }

   private static float P(int var0, long var1) {
      HazeRenderPass.ensureSourceClassInitialized();
      float var3 = var0 / 255.0F;
      long var4 = 47623086262413L;
      int var6 = var4 < 0L ? -1 : 1;
      return var3 <= 0.04045F ? var3 / 12.92F : (float)Math.pow((var3 + 0.055F) / 1.055F, 2.4F);
   }

   private static int G(float var0, long var1) {
      HazeRenderPass.ensureSourceClassInitialized();
      float var3 = Math.clamp(var0, 0.0F, 1.0F);
      long var4 = 83748169201660L;
      int var7 = var4 < 0L ? -1 : 1;
      float var7x = var3 <= 0.0031308F ? var3 * 12.92F : 1.055F * (float)Math.pow(var3, 0.41666666F) - 0.055F;
      return Math.round(Math.clamp(var7x, 0.0F, 1.0F) * 255.0F);
   }

   private static float[] o(int var0, long var1) {
      long var3 = 47623086262414L;
      float var5 = P(var0 >> 16 & 0xFF, var3);
      float var6 = P(var0 >> 8 & 0xFF, var3);
      float var7 = P(var0 & 0xFF, var3);
      float var8 = (float)Math.cbrt(0.41222146F * var5 + 0.53633255F * var6 + 0.051445995F * var7);
      float var9 = (float)Math.cbrt(0.2119035F * var5 + 0.6806995F * var6 + 0.10739696F * var7);
      float var10 = (float)Math.cbrt(0.08830246F * var5 + 0.28171885F * var6 + 0.6299787F * var7);
      return new float[]{
         0.21045426F * var8 + 0.7936178F * var9 - 0.004072047F * var10,
         1.9779985F * var8 - 2.4285922F * var9 + 0.4505937F * var10,
         0.025904037F * var8 + 0.78277177F * var9 - 0.80867577F * var10
      };
   }

   private static float[] k(float var0, float var1, float var2) {
      float var3 = var0 + 0.39633778F * var1 + 0.21580376F * var2;
      float var4 = var0 - 0.105561346F * var1 - 0.06385417F * var2;
      float var5 = var0 - 0.08948418F * var1 - 1.2914855F * var2;
      var3 = var3 * var3 * var3;
      var4 = var4 * var4 * var4;
      var5 = var5 * var5 * var5;
      return new float[]{
         4.0767417F * var3 - 3.3077116F * var4 + 0.23096994F * var5,
         -1.268438F * var3 + 2.6097574F * var4 - 0.34131938F * var5,
         -0.0041960864F * var3 - 0.7034186F * var4 + 1.7076147F * var5
      };
   }

   private static int J(float var0, float var1, long var2, float var4) {
      long var5 = 83748169201660L;
      float[] var7 = k(var0, var1, var4);
      return G(var7[0], var5) << 16 | G(var7[1], var5) << 8 | G(var7[2], var5);
   }
}
