package dev.purxiy.modules.ambience;

import dev.purxiy.loader.fabric.client.ClientSupport;
import dev.purxiy.ui.Theme;
import java.util.List;
import java.util.Map;

public final class AmbienceLoader {
   public static final String H = "Accent";
   public static final Map<String, String> o = Map.of("Lilac", "Accent");
   static final AmbienceLoader f = new AmbienceLoader("Accent", 13481727, 11306751, 15442687);
   static final AmbienceLoader M = new AmbienceLoader("Purxiy", 9854207, 12607999, 16740054);
   static final AmbienceLoader N = new AmbienceLoader("Dream", 16758499, 12890367, 10404095);
   public static final String F = "Custom";
   static final List<AmbienceLoader> v = List.of(f, M, N);
   private static int x = Integer.MIN_VALUE;
   private static AmbienceLoader p = f;
   private static AmbienceLoader X = M;
   final String y;
   public final int B;
   public final int D;
   public final int d;
   public static boolean s;

   private AmbienceLoader(String var1, int var2, int var3, int var4) {
      this.y = var1;
      this.B = var2;
      this.D = var3;
      this.d = var4;
   }

   public static String[] names() {
      String[] var0 = new String[v.size() + 1];

      for (int var1 = 0; var1 < v.size(); var1++) {
         var0[var1] = v.get(var1).y;
      }

      var0[v.size()] = "Custom";
      return var0;
   }

   public static AmbienceLoader resolve(String var0, int var1) {
      long var2 = 129812604320862L;
      long var4 = (var2 ^ 67673412924640L) >>> 16;
      int var6 = (int)((var2 ^ 67673412924640L) << 48 >>> 48);
      long var7 = (var2 ^ 112220740094783L) >>> 8;
      int var9 = (int)((var2 ^ 112220740094783L) << 56 >>> 56);
      String var10 = o.getOrDefault(var0, var0);
      if (!var10.equals(f.y) && !var10.equals(M.y)) {
         for (AmbienceLoader var12 : v) {
            if (var12.y.equals(var10)) {
               return var12;
            }
         }

         return R(var7, var1, (byte)var9);
      } else {
         if (x != Theme.generation()) {
            x = Theme.generation();
            p = p(var4, f, (short)var6);
            X = p(var4, M, (short)var6);
         }

         return var10.equals(f.y) ? p : X;
      }
   }

   private static AmbienceLoader p(long var0, AmbienceLoader var2, short var3) {
      return new AmbienceLoader(
         var2.y, Theme.tint(0xFF000000 | var2.B) & 16777215, Theme.tint(0xFF000000 | var2.D) & 16777215, Theme.tint(0xFF000000 | var2.d) & 16777215
      );
   }

   private static AmbienceLoader R(long var0, int var2, byte var3) {
      long var4 = 17593116358497L;
      long var6 = var4 ^ 20034846638317L;
      float[] var8 = ClientSupport.rgbToHsv(var2);
      int var9 = B(var6, var8[0] - 0.035F, var8[1] * 0.72F, Math.min(1.0F, var8[2] * 1.08F));
      int var10 = B(var6, var8[0] + 0.06F, var8[1] * 0.9F, var8[2]);
      return new AmbienceLoader("Custom", var9, var2 & 16777215, var10);
   }

   private static int B(long var0, float var2, float var3, float var4) {
      return ClientSupport.hsvToRgb(var2 - (float)Math.floor(var2), var3, var4) & 16777215;
   }
}
