package dev.purxiy.modules.trail;

import dev.purxiy.loader.fabric.client.ClientSupport;
import dev.purxiy.ui.Theme;
import java.util.List;
import java.util.Map;

public final class TrailLoader {
   public static final String b = "Accent";
   public static final Map<String, String> t = Map.of("Lilac", "Accent");
   static final TrailLoader X = new TrailLoader("Accent", 13481727, 11306751, 15442687);
   static final TrailLoader L = new TrailLoader("Purxiy", 9854207, 12607999, 16740054);
   static final TrailLoader H = new TrailLoader("Dream", 16758499, 12890367, 10404095);
   public static final String P = "Custom";
   static final List<TrailLoader> Y = List.of(X, L, H);
   private static int W = Integer.MIN_VALUE;
   private static TrailLoader w = X;
   private static TrailLoader C = L;
   final String R;
   public final int y;
   public final int l;
   public final int N;
   private static final String[] c = new String[9];

   private TrailLoader(String var1, int var2, int var3, int var4) {
      this.R = var1;
      this.y = var2;
      this.l = var3;
      this.N = var4;
   }

   public static String[] names() {
      String[] var0 = new String[Y.size() + 1];

      for (int var1 = 0; var1 < Y.size(); var1++) {
         var0[var1] = Y.get(var1).R;
      }

      var0[Y.size()] = "Custom";
      return var0;
   }

   public static TrailLoader resolve(String var0, int var1) {
      long var2 = 53296437971609L;
      long var4 = var2 ^ 16728662825729L;
      long var6 = var2 ^ 116511371789527L;
      String var8 = t.getOrDefault(var0, var0);
      if (!var8.equals(X.R) && !var8.equals(L.R)) {
         for (TrailLoader var10 : Y) {
            if (var10.R.equals(var8)) {
               return var10;
            }
         }

         return A(var6, var1);
      } else {
         if (W != Theme.generation()) {
            W = Theme.generation();
            w = c(X, var4);
            C = c(L, var4);
         }

         return var8.equals(X.R) ? w : C;
      }
   }

   private static TrailLoader c(TrailLoader var0, long var1) {
      return new TrailLoader(
         var0.R, Theme.tint(0xFF000000 | var0.y) & 16777215, Theme.tint(0xFF000000 | var0.l) & 16777215, Theme.tint(0xFF000000 | var0.N) & 16777215
      );
   }

   private static TrailLoader A(long var0, int var2) {
      long var3 = 87785897491266L;
      float[] var5 = ClientSupport.rgbToHsv(var2);
      int var6 = J(var5[0] - 0.035F, var5[1] * 0.72F, Math.min(1.0F, var5[2] * 1.08F), var3);
      int var7 = J(var5[0] + 0.06F, var5[1] * 0.9F, var5[2], var3);
      return new TrailLoader("Custom", var6, var2 & 16777215, var7);
   }

   private static int J(float var0, float var1, float var2, long var3) {
      return ClientSupport.hsvToRgb(var0 - (float)Math.floor(var0), var1, var2) & 16777215;
   }
}
