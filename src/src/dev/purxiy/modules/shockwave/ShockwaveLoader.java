// Sourced By DevFFNightmare
package dev.purxiy.modules.shockwave;

import dev.purxiy.loader.fabric.client.ClientSupport;
import dev.purxiy.ui.Theme;
import java.util.List;
import java.util.Map;

public final class ShockwaveLoader {
   public static final String Y = "Accent";
   public static final Map<String, String> w = Map.of("Lilac", "Accent");
   static final ShockwaveLoader D = new ShockwaveLoader("Accent", 13481727, 11306751, 15442687);
   static final ShockwaveLoader M = new ShockwaveLoader("Purxiy", 9854207, 12607999, 16740054);
   static final ShockwaveLoader v = new ShockwaveLoader("Dream", 16758499, 12890367, 10404095);
   public static final String X = "Custom";
   static final List<ShockwaveLoader> l = List.of(D, M, v);
   private static int e = Integer.MIN_VALUE;
   private static ShockwaveLoader i = D;
   private static ShockwaveLoader F = M;
   final String q;
   public final int o;
   public final int B;
   public final int O;


   private static final long[] c = new long[]{-5443196259563616474L, -8532233919004797025L, 7871072270492928593L, 2170110254104300673L};
   private static final Integer[] d = new Integer[4];

   private ShockwaveLoader(String var1, int var2, int var3, int var4) {
      this.q = var1;
      this.o = var2;
      this.B = var3;
      this.O = var4;
   }

   public static String[] names() {
      String[] var0 = new String[l.size() + 1];

      for (int var1 = 0; var1 < l.size(); var1++) {
         var0[var1] = l.get(var1).q;
      }

      var0[l.size()] = "Custom";
      return var0;
   }

   public static ShockwaveLoader resolve(String var0, int var1) {
      long var2 = 41766037523160L;
      int var4 = (int)((var2 ^ 50266916220847L) >>> 32);
      long var5 = (var2 ^ 50266916220847L) << 32 >>> 32;
      long var7 = var2 ^ 4850275936167L;
      String var9 = w.getOrDefault(var0, var0);
      if (!var9.equals(D.q) && !var9.equals(M.q)) {
         for (ShockwaveLoader var11 : l) {
            if (var11.q.equals(var9)) {
               return var11;
            }
         }

         return a(var7, var1);
      } else {
         if (e != Theme.generation()) {
            e = Theme.generation();
            i = U(var4, D, var5);
            F = U(var4, M, var5);
         }

         return var9.equals(D.q) ? i : F;
      }
   }

   private static ShockwaveLoader U(int var0, ShockwaveLoader var1, long var2) {
      return new ShockwaveLoader(
         var1.q, Theme.tint(0xFF000000 | var1.o) & 16777215, Theme.tint(0xFF000000 | var1.B) & 16777215, Theme.tint(0xFF000000 | var1.O) & 16777215
      );
   }

   private static ShockwaveLoader a(long var0, int var2) {
      long var3 = 121884114121638L;
      short var5 = 28378;
      short var6 = 23379;
      int var7 = (int)(var3 << 48 >>> 48);
      float[] var8 = ClientSupport.rgbToHsv(var2);
      int var9 = h(var8[0] - 0.035F, var5, (char)var6, (char)var7, var8[1] * 0.72F, Math.min(1.0F, var8[2] * 1.08F));
      int var10 = h(var8[0] + 0.06F, var5, (char)var6, (char)var7, var8[1] * 0.9F, var8[2]);
      return new ShockwaveLoader("Custom", var9, var2 & 16777215, var10);
   }

   private static int h(float var0, int var1, char var2, char var3, float var4, float var5) {
      return ClientSupport.hsvToRgb(var0 - (float)Math.floor(var0), var4, var5) & 16777215;
   }

private static int a(int var0, long var1) {
      int var3 = var0 ^ (int)(var1 & 32767L) ^ 24931;
      if (d[var3] == null) {
         d[var3] = (int)(c[var3] ^ var1);
      }

      return d[var3];
   }

}
