package dev.purxiy.modules.place;

import dev.purxiy.loader.fabric.client.ClientSupport;
import dev.purxiy.ui.Theme;
import java.util.List;
import java.util.Map;

public final class PlaceAnimationLoader {
   public static final String d = "Accent";
   public static final Map<String, String> k = Map.of("Lilac", "Accent");
   static final PlaceAnimationLoader j = new PlaceAnimationLoader("Accent", 13481727, 11306751, 15442687);
   static final PlaceAnimationLoader x = new PlaceAnimationLoader("SourceByDevFFnightmare DeObfDeCompByGuideX", 9854207, 12607999, 16740054);
   static final PlaceAnimationLoader Y = new PlaceAnimationLoader("Dream", 16758499, 12890367, 10404095);
   public static final String Z = "Custom";
   static final List<PlaceAnimationLoader> g = List.of(j, x, Y);
   private static int u = Integer.MIN_VALUE;
   private static PlaceAnimationLoader w = j;
   private static PlaceAnimationLoader W = x;
   final String K;
   public final int V;
   public final int L;
   public final int E;

   private PlaceAnimationLoader(String var1, int var2, int var3, int var4) {
      this.K = var1;
      this.V = var2;
      this.L = var3;
      this.E = var4;
   }

   public static String[] names() {
      String[] var0 = new String[g.size() + 1];

      for (int var1 = 0; var1 < g.size(); var1++) {
         var0[var1] = g.get(var1).K;
      }

      var0[g.size()] = "Custom";
      return var0;
   }

   public static PlaceAnimationLoader resolve(String var0, int var1) {
      long var2 = 6449311953104L;
      long var4 = var2 ^ 104004760657439L;
      long var6 = (var2 ^ 139560583854538L) >>> 32;
      int var8 = (int)((var2 ^ 139560583854538L) << 32 >>> 32);
      String var9 = k.getOrDefault(var0, var0);
      if (!var9.equals(j.K) && !var9.equals(x.K)) {
         for (PlaceAnimationLoader var11 : g) {
            if (var11.K.equals(var9)) {
               return var11;
            }
         }

         return H(var6, var1, var8);
      } else {
         if (u != Theme.generation()) {
            u = Theme.generation();
            w = F(var4, j);
            W = F(var4, x);
         }

         return var9.equals(j.K) ? w : W;
      }
   }

   private static PlaceAnimationLoader F(long var0, PlaceAnimationLoader var2) {
      return new PlaceAnimationLoader(
         var2.K, Theme.tint(0xFF000000 | var2.V) & 16777215, Theme.tint(0xFF000000 | var2.L) & 16777215, Theme.tint(0xFF000000 | var2.E) & 16777215
      );
   }

   private static PlaceAnimationLoader H(long var0, int var2, int var3) {
      long var4 = 135447765633306L;
      int var6 = (int)((var4 ^ 110182932645982L) >>> 32);
      int var7 = (int)((var4 ^ 110182932645982L) << 32 >>> 32);
      float[] var8 = ClientSupport.rgbToHsv(var2);
      int var9 = G(var6, var8[0] - 0.035F, var8[1] * 0.72F, Math.min(1.0F, var8[2] * 1.08F), var7);
      int var10 = G(var6, var8[0] + 0.06F, var8[1] * 0.9F, var8[2], var7);
      return new PlaceAnimationLoader("Custom", var9, var2 & 16777215, var10);
   }

   private static int G(int var0, float var1, float var2, float var3, int var4) {
      return ClientSupport.hsvToRgb(var1 - (float)Math.floor(var1), var2, var3) & 16777215;
   }
}
