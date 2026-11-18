// Sourced By DevFFNightmare
package dev.purxiy.modules.radiance;

import dev.purxiy.loader.fabric.client.ClientSupport;
import dev.purxiy.ui.Theme;
import java.util.List;
import java.util.Map;

public final class RadianceLoader2 {
   public static final String h = "Accent";
   public static final Map<String, String> o = Map.of("Lilac", "Accent");
   static final RadianceLoader2 x = new RadianceLoader2("Accent", 13481727, 11306751, 15442687);
   static final RadianceLoader2 Y = new RadianceLoader2("Purxiy", 9854207, 12607999, 16740054);
   static final RadianceLoader2 c = new RadianceLoader2("Dream", 16758499, 12890367, 10404095);
   public static final String C = "Custom";
   static final List<RadianceLoader2> H = List.of(x, Y, c);
   private static int n = Integer.MIN_VALUE;
   private static RadianceLoader2 m = x;
   private static RadianceLoader2 a = Y;
   final String g;
   public final int q;
   public final int E;
   public final int T;





   private RadianceLoader2(String var1, int var2, int var3, int var4) {
      this.g = var1;
      this.q = var2;
      this.E = var3;
      this.T = var4;
   }

   public static String[] names() {
      String[] var0 = new String[H.size() + 1];

      for (int var1 = 0; var1 < H.size(); var1++) {
         var0[var1] = H.get(var1).g;
      }

      var0[H.size()] = "Custom";
      return var0;
   }

   public static RadianceLoader2 resolve(String var0, int var1) {
      long var2 = 103458557389460L;
      long var4 = var2 ^ 42137975733311L;
      long var6 = var2 ^ 25335420630734L;
      String var8 = o.getOrDefault(var0, var0);
      if (!var8.equals(x.g) && !var8.equals(Y.g)) {
         for (RadianceLoader2 var10 : H) {
            if (var10.g.equals(var8)) {
               return var10;
            }
         }

         return q(var4, var1);
      } else {
         if (n != Theme.generation()) {
            n = Theme.generation();
            m = p(x, var6);
            a = p(Y, var6);
         }

         return var8.equals(x.g) ? m : a;
      }
   }

   private static RadianceLoader2 p(RadianceLoader2 var0, long var1) {
      return new RadianceLoader2(
         var0.g, Theme.tint(0xFF000000 | var0.q) & 16777215, Theme.tint(0xFF000000 | var0.E) & 16777215, Theme.tint(0xFF000000 | var0.T) & 16777215
      );
   }

   private static RadianceLoader2 q(long var0, int var2) {
      long var3 = 69572656883451L;
      float[] var5 = ClientSupport.rgbToHsv(var2);
      int var6 = t(var3, var5[0] - 0.035F, var5[1] * 0.72F, Math.min(1.0F, var5[2] * 1.08F));
      int var7 = t(var3, var5[0] + 0.06F, var5[1] * 0.9F, var5[2]);
      return new RadianceLoader2("Custom", var6, var2 & 16777215, var7);
   }

   private static int t(long var0, float var2, float var3, float var4) {
      return ClientSupport.hsvToRgb(var2 - (float)Math.floor(var2), var3, var4) & 16777215;
   }

}
