package dev.purxiy.modules.jumpcircles;

import dev.purxiy.loader.fabric.client.ClientSupport;
import dev.purxiy.ui.Theme;
import java.util.List;
import java.util.Map;

public final class JumpCirclesLoader {
   public static final String G = "Accent";
   public static final Map<String, String> f = Map.of("Lilac", "Accent");
   static final JumpCirclesLoader W = new JumpCirclesLoader("Accent", 13481727, 11306751, 15442687);
   static final JumpCirclesLoader K = new JumpCirclesLoader("Purxiy", 9854207, 12607999, 16740054);
   static final JumpCirclesLoader x = new JumpCirclesLoader("Dream", 16758499, 12890367, 10404095);
   public static final String y = "Custom";
   static final List<JumpCirclesLoader> N = List.of(W, K, x);
   private static int Q = Integer.MIN_VALUE;
   private static JumpCirclesLoader R = W;
   private static JumpCirclesLoader k = K;
   final String E;
   public final int r;
   public final int h;
   public final int P;

   private JumpCirclesLoader(String var1, int var2, int var3, int var4) {
      this.E = var1;
      this.r = var2;
      this.h = var3;
      this.P = var4;
   }

   public static String[] names() {
      String[] var0 = new String[N.size() + 1];

      for (int var1 = 0; var1 < N.size(); var1++) {
         var0[var1] = N.get(var1).E;
      }

      var0[N.size()] = "Custom";
      return var0;
   }

   public static JumpCirclesLoader resolve(String var0, int var1) {
      long var2 = 99258914692055L;
      long var4 = (var2 ^ 49701201634264L) >>> 16;
      int var6 = (int)((var2 ^ 49701201634264L) << 48 >>> 48);
      long var7 = var2 ^ 49845891558647L;
      String var9 = f.getOrDefault(var0, var0);
      if (!var9.equals(W.E) && !var9.equals(K.E)) {
         for (JumpCirclesLoader var11 : N) {
            if (var11.E.equals(var9)) {
               return var11;
            }
         }

         return C(var7, var1);
      } else {
         if (Q != Theme.generation()) {
            Q = Theme.generation();
            R = A(W, var4, (char)var6);
            k = A(K, var4, (char)var6);
         }

         return var9.equals(W.E) ? R : k;
      }
   }

   private static JumpCirclesLoader A(JumpCirclesLoader var0, long var1, char var3) {
      return new JumpCirclesLoader(
         var0.E, Theme.tint(0xFF000000 | var0.r) & 16777215, Theme.tint(0xFF000000 | var0.h) & 16777215, Theme.tint(0xFF000000 | var0.P) & 16777215
      );
   }

   private static JumpCirclesLoader C(long var0, int var2) {
      short var3 = 17779;
      long var4 = 2721641809L;
      float[] var6 = ClientSupport.rgbToHsv(var2);
      int var7 = T(var6[0] - 0.035F, var6[1] * 0.72F, Math.min(1.0F, var6[2] * 1.08F), var3, var4);
      int var8 = T(var6[0] + 0.06F, var6[1] * 0.9F, var6[2], var3, var4);
      return new JumpCirclesLoader("Custom", var7, var2 & 16777215, var8);
   }

   private static int T(float var0, float var1, float var2, int var3, long var4) {
      return ClientSupport.hsvToRgb(var0 - (float)Math.floor(var0), var1, var2) & 16777215;
   }
}
