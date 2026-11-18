// Sourced By DevFFNightmare
package dev.purxiy.modules.afterglow;

import dev.purxiy.loader.fabric.client.ClientSupport;
import dev.purxiy.ui.Theme;
import java.util.List;
import java.util.Map;

public final class AfterglowLoader {
   public static final String v = "Accent";
   public static final Map<String, String> n = Map.of("Lilac", "Accent");
   static final AfterglowLoader i = new AfterglowLoader("Accent", 13481727, 11306751, 15442687);
   static final AfterglowLoader B = new AfterglowLoader("Purxiy", 9854207, 12607999, 16740054);
   static final AfterglowLoader L = new AfterglowLoader("Dream", 16758499, 12890367, 10404095);
   public static final String c = "Custom";
   static final List<AfterglowLoader> X = List.of(i, B, L);
   private static int m = Integer.MIN_VALUE;
   private static AfterglowLoader r = i;
   private static AfterglowLoader K = B;
   final String p;
   public final int t;
   public final int W;
   public final int G;

   private static final String[] b = new String[9];



   private AfterglowLoader(String var1, int var2, int var3, int var4) {
      this.p = var1;
      this.t = var2;
      this.W = var3;
      this.G = var4;
   }

   public static String[] names() {
      String[] var0 = new String[X.size() + 1];

      for (int var1 = 0; var1 < X.size(); var1++) {
         var0[var1] = X.get(var1).p;
      }

      var0[X.size()] = "Custom";
      return var0;
   }

   public static AfterglowLoader resolve(String var0, int var1) {
      long var2 = 54139639388492L;
      long var4 = var2 ^ 48963237898229L;
      long var6 = var2 ^ 68875797916853L;
      String var8 = n.getOrDefault(var0, var0);
      if (!var8.equals(i.p) && !var8.equals(B.p)) {
         for (AfterglowLoader var10 : X) {
            if (var10.p.equals(var8)) {
               return var10;
            }
         }

         return b(var6, var1);
      } else {
         if (m != Theme.generation()) {
            m = Theme.generation();
            r = m(var4, i);
            K = m(var4, B);
         }

         return var8.equals(i.p) ? r : K;
      }
   }

   private static AfterglowLoader m(long var0, AfterglowLoader var2) {
      return new AfterglowLoader(
         var2.p, Theme.tint(0xFF000000 | var2.t) & 16777215, Theme.tint(0xFF000000 | var2.W) & 16777215, Theme.tint(0xFF000000 | var2.G) & 16777215
      );
   }

   private static AfterglowLoader b(long var0, int var2) {
      long var3 = 82380974238386L;
      float[] var5 = ClientSupport.rgbToHsv(var2);
      int var6 = D(var5[0] - 0.035F, var5[1] * 0.72F, Math.min(1.0F, var5[2] * 1.08F), var3);
      int var7 = D(var5[0] + 0.06F, var5[1] * 0.9F, var5[2], var3);
      return new AfterglowLoader("Custom", var6, var2 & 16777215, var7);
   }

   private static int D(float var0, float var1, float var2, long var3) {
      return ClientSupport.hsvToRgb(var0 - (float)Math.floor(var0), var1, var2) & 16777215;
   }

}
