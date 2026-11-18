// Sourced By DevFFNightmare
package dev.purxiy.modules.ripple;

import dev.purxiy.loader.fabric.client.ClientSupport;
import dev.purxiy.ui.Theme;
import java.util.List;
import java.util.Map;

public final class RippleLoader {
   public static final String V = "Accent";
   public static final Map<String, String> w = Map.of("Lilac", "Accent");
   static final RippleLoader t = new RippleLoader("Accent", 13481727, 11306751, 15442687);
   static final RippleLoader C = new RippleLoader("Purxiy", 9854207, 12607999, 16740054);
   static final RippleLoader q = new RippleLoader("Dream", 16758499, 12890367, 10404095);
   public static final String Y = "Custom";
   static final List<RippleLoader> d = List.of(t, C, q);
   private static int r = Integer.MIN_VALUE;
   private static RippleLoader x = t;
   private static RippleLoader G = C;
   final String R;
   public final int D;
   public final int v;
   public final int H;





   private RippleLoader(String var1, int var2, int var3, int var4) {
      this.R = var1;
      this.D = var2;
      this.v = var3;
      this.H = var4;
   }

   public static String[] names() {
      String[] var0 = new String[d.size() + 1];

      for (int var1 = 0; var1 < d.size(); var1++) {
         var0[var1] = d.get(var1).R;
      }

      var0[d.size()] = "Custom";
      return var0;
   }

   public static RippleLoader resolve(String var0, int var1) {
      long var2 = 32720553433411L;
      long var4 = var2 ^ 101378926863710L;
      long var6 = var2 ^ 61999348700533L;
      String var8 = w.getOrDefault(var0, var0);
      if (!var8.equals(t.R) && !var8.equals(C.R)) {
         for (RippleLoader var10 : d) {
            if (var10.R.equals(var8)) {
               return var10;
            }
         }

         return n(var1, var4);
      } else {
         if (r != Theme.generation()) {
            r = Theme.generation();
            x = u(t, var6);
            G = u(C, var6);
         }

         return var8.equals(t.R) ? x : G;
      }
   }

   private static RippleLoader u(RippleLoader var0, long var1) {
      return new RippleLoader(
         var0.R, Theme.tint(0xFF000000 | var0.D) & 16777215, Theme.tint(0xFF000000 | var0.v) & 16777215, Theme.tint(0xFF000000 | var0.H) & 16777215
      );
   }

   private static RippleLoader n(int var0, long var1) {
      long var3 = 122143959809317L;
      float[] var5 = ClientSupport.rgbToHsv(var0);
      int var6 = i(var5[0] - 0.035F, var5[1] * 0.72F, var3, Math.min(1.0F, var5[2] * 1.08F));
      int var7 = i(var5[0] + 0.06F, var5[1] * 0.9F, var3, var5[2]);
      return new RippleLoader("Custom", var6, var0 & 16777215, var7);
   }

   private static int i(float var0, float var1, long var2, float var4) {
      return ClientSupport.hsvToRgb(var0 - (float)Math.floor(var0), var1, var4) & 16777215;
   }

}
