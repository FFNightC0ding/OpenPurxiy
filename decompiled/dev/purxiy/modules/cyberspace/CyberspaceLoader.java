package dev.purxiy.modules.cyberspace;

import dev.purxiy.loader.fabric.client.ClientSupport;
import dev.purxiy.ui.Theme;
import java.util.List;
import java.util.Map;

public final class CyberspaceLoader {
   public static final String j = "Accent";
   public static final Map<String, String> n = Map.of("Lilac", "Accent");
   static final CyberspaceLoader i = new CyberspaceLoader("Accent", 13481727, 11306751, 15442687);
   static final CyberspaceLoader z = new CyberspaceLoader("Purxiy", 9854207, 12607999, 16740054);
   static final CyberspaceLoader C = new CyberspaceLoader("Dream", 16758499, 12890367, 10404095);
   public static final String g = "Custom";
   static final List<CyberspaceLoader> x = List.of(i, z, C);
   private static int w = Integer.MIN_VALUE;
   private static CyberspaceLoader t = i;
   private static CyberspaceLoader a = z;
   final String y;
   public final int l;
   public final int M;
   public final int Q;

   private CyberspaceLoader(String var1, int var2, int var3, int var4) {
      this.y = var1;
      this.l = var2;
      this.M = var3;
      this.Q = var4;
   }

   public static String[] names() {
      String[] var0 = new String[x.size() + 1];

      for (int var1 = 0; var1 < x.size(); var1++) {
         var0[var1] = x.get(var1).y;
      }

      var0[x.size()] = "Custom";
      return var0;
   }

   public static CyberspaceLoader resolve(String var0, int var1) {
      long var2 = 16769029966150L;
      long var4 = var2 ^ 71118222758621L;
      long var6 = var2 ^ 95926746152611L;
      String var8 = n.getOrDefault(var0, var0);
      if (!var8.equals(i.y) && !var8.equals(z.y)) {
         for (CyberspaceLoader var10 : x) {
            if (var10.y.equals(var8)) {
               return var10;
            }
         }

         return j(var4, var1);
      } else {
         if (w != Theme.generation()) {
            w = Theme.generation();
            t = X(var6, i);
            a = X(var6, z);
         }

         return var8.equals(i.y) ? t : a;
      }
   }

   private static CyberspaceLoader X(long var0, CyberspaceLoader var2) {
      return new CyberspaceLoader(
         var2.y, Theme.tint(0xFF000000 | var2.l) & 16777215, Theme.tint(0xFF000000 | var2.M) & 16777215, Theme.tint(0xFF000000 | var2.Q) & 16777215
      );
   }

   private static CyberspaceLoader j(long var0, int var2) {
      long var3 = 57858244332933L;
      float[] var5 = ClientSupport.rgbToHsv(var2);
      int var6 = X(var5[0] - 0.035F, var3, var5[1] * 0.72F, Math.min(1.0F, var5[2] * 1.08F));
      int var7 = X(var5[0] + 0.06F, var3, var5[1] * 0.9F, var5[2]);
      return new CyberspaceLoader("Custom", var6, var2 & 16777215, var7);
   }

   private static int X(float var0, long var1, float var3, float var4) {
      return ClientSupport.hsvToRgb(var0 - (float)Math.floor(var0), var3, var4) & 16777215;
   }
}
