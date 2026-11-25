package dev.purxiy.modules.auras;

import dev.purxiy.loader.fabric.client.ClientSupport;
import dev.purxiy.ui.Theme;
import java.util.List;
import java.util.Map;

public final class AuraLoader {
   public static final String P = "Accent";
   public static final Map<String, String> d = Map.of("Lilac", "Accent");
   static final AuraLoader y = new AuraLoader("Accent", 13481727, 11306751, 15442687);
   static final AuraLoader J = new AuraLoader("Purxiy", 9854207, 12607999, 16740054);
   static final AuraLoader F = new AuraLoader("Dream", 16758499, 12890367, 10404095);
   public static final String p = "Custom";
   static final List<AuraLoader> u = List.of(y, J, F);
   private static int a = Integer.MIN_VALUE;
   private static AuraLoader W = y;
   private static AuraLoader v = J;
   final String o;
   public final int A;
   public final int R;
   public final int n;

   private AuraLoader(String var1, int var2, int var3, int var4) {
      this.o = var1;
      this.A = var2;
      this.R = var3;
      this.n = var4;
   }

   public static String[] names() {
      String[] var0 = new String[u.size() + 1];

      for (int var1 = 0; var1 < u.size(); var1++) {
         var0[var1] = u.get(var1).o;
      }

      var0[u.size()] = "Custom";
      return var0;
   }

   public static AuraLoader resolve(String var0, int var1) {
      long var2 = 80868388208808L;
      long var4 = var2 ^ 34621952641732L;
      long var6 = var2 ^ 15198041655663L;
      String var8 = d.getOrDefault(var0, var0);
      if (!var8.equals(y.o) && !var8.equals(J.o)) {
         for (AuraLoader var10 : u) {
            if (var10.o.equals(var8)) {
               return var10;
            }
         }

         return i(var6, var1);
      } else {
         if (a != Theme.generation()) {
            a = Theme.generation();
            W = k(y, var4);
            v = k(J, var4);
         }

         return var8.equals(y.o) ? W : v;
      }
   }

   private static AuraLoader k(AuraLoader var0, long var1) {
      return new AuraLoader(
         var0.o, Theme.tint(0xFF000000 | var0.A) & 16777215, Theme.tint(0xFF000000 | var0.R) & 16777215, Theme.tint(0xFF000000 | var0.n) & 16777215
      );
   }

   private static AuraLoader i(long var0, int var2) {
      long var3 = 129989517524470L;
      float[] var5 = ClientSupport.rgbToHsv(var2);
      int var6 = l(var5[0] - 0.035F, var5[1] * 0.72F, var3, Math.min(1.0F, var5[2] * 1.08F));
      int var7 = l(var5[0] + 0.06F, var5[1] * 0.9F, var3, var5[2]);
      return new AuraLoader("Custom", var6, var2 & 16777215, var7);
   }

   private static int l(float var0, float var1, long var2, float var4) {
      return ClientSupport.hsvToRgb(var0 - (float)Math.floor(var0), var1, var4) & 16777215;
   }
}
