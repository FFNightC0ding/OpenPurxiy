package dev.purxiy.modules.hitsplash;

import dev.purxiy.loader.fabric.client.ClientSupport;
import dev.purxiy.ui.Theme;
import java.util.List;
import java.util.Map;

public final class HitSplashLoader {
   public static final String q = "Accent";
   public static final Map<String, String> w = Map.of("Lilac", "Accent");
   static final HitSplashLoader V = new HitSplashLoader("Accent", 13481727, 11306751, 15442687);
   static final HitSplashLoader Z = new HitSplashLoader("SourceByDevFFnightmare DeObfDeCompByGuideX", 9854207, 12607999, 16740054);
   static final HitSplashLoader X = new HitSplashLoader("Dream", 16758499, 12890367, 10404095);
   public static final String J = "Custom";
   static final List<HitSplashLoader> P = List.of(V, Z, X);
   private static int f = Integer.MIN_VALUE;
   private static HitSplashLoader i = V;
   private static HitSplashLoader h = Z;
   final String C;
   public final int F;
   public final int k;
   public final int D;

   private HitSplashLoader(String var1, int var2, int var3, int var4) {
      this.C = var1;
      this.F = var2;
      this.k = var3;
      this.D = var4;
   }

   public static String[] names() {
      String[] var0 = new String[P.size() + 1];

      for (int var1 = 0; var1 < P.size(); var1++) {
         var0[var1] = P.get(var1).C;
      }

      var0[P.size()] = "Custom";
      return var0;
   }

   public static HitSplashLoader resolve(String var0, int var1) {
      long var2 = 85020132353184L;
      long var4 = var2 ^ 13379397315421L;
      int var6 = (int)((var2 ^ 13379397315421L) >>> 48);
      int var7 = (int)((var2 ^ 13379397315421L) << 16 >>> 32);
      int var8 = (int)(var4 << 48 >>> 48);
      long var9 = var2 ^ 10740596207609L;
      String var11 = w.getOrDefault(var0, var0);
      if (!var11.equals(V.C) && !var11.equals(Z.C)) {
         for (HitSplashLoader var13 : P) {
            if (var13.C.equals(var11)) {
               return var13;
            }
         }

         return y(var9, var1);
      } else {
         if (f != Theme.generation()) {
            f = Theme.generation();
            i = H(V, (short)var6, var7, (short)var8);
            h = H(Z, (short)var6, var7, (short)var8);
         }

         return var11.equals(V.C) ? i : h;
      }
   }

   private static HitSplashLoader H(HitSplashLoader var0, short var1, int var2, short var3) {
      return new HitSplashLoader(
         var0.C, Theme.tint(0xFF000000 | var0.F) & 16777215, Theme.tint(0xFF000000 | var0.k) & 16777215, Theme.tint(0xFF000000 | var0.D) & 16777215
      );
   }

   private static HitSplashLoader y(long var0, int var2) {
      long var3 = 44164305900263L;
      float[] var5 = ClientSupport.rgbToHsv(var2);
      int var6 = j(var3, var5[0] - 0.035F, var5[1] * 0.72F, Math.min(1.0F, var5[2] * 1.08F));
      int var7 = j(var3, var5[0] + 0.06F, var5[1] * 0.9F, var5[2]);
      return new HitSplashLoader("Custom", var6, var2 & 16777215, var7);
   }

   private static int j(long var0, float var2, float var3, float var4) {
      return ClientSupport.hsvToRgb(var2 - (float)Math.floor(var2), var3, var4) & 16777215;
   }
}
