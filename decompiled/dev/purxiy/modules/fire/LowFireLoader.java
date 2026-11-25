package dev.purxiy.modules.fire;

import dev.purxiy.loader.fabric.client.ClientSupport;
import dev.purxiy.ui.Theme;
import java.util.List;
import java.util.Map;

public final class LowFireLoader {
   public static final String o = "Accent";
   public static final Map<String, String> l = Map.of("Lilac", "Accent");
   static final LowFireLoader y = new LowFireLoader("Accent", 13481727, 11306751, 15442687);
   static final LowFireLoader e = new LowFireLoader("Purxiy", 9854207, 12607999, 16740054);
   static final LowFireLoader t = new LowFireLoader("Dream", 16758499, 12890367, 10404095);
   public static final String n = "Custom";
   static final List<LowFireLoader> v = List.of(y, e, t);
   private static int Y = Integer.MIN_VALUE;
   private static LowFireLoader i = y;
   private static LowFireLoader N = e;
   final String E;
   public final int F;
   public final int c;
   public final int g;
   public static int p = 0;

   private LowFireLoader(String var1, int var2, int var3, int var4) {
      this.E = var1;
      this.F = var2;
      this.c = var3;
      this.g = var4;
   }

   public static String[] names() {
      String[] var0 = new String[v.size() + 1];

      for (int var1 = 0; var1 < v.size(); var1++) {
         var0[var1] = v.get(var1).E;
      }

      var0[v.size()] = "Custom";
      return var0;
   }

   public static LowFireLoader resolve(String var0, int var1) {
      long var2 = 136538702525668L;
      long var4 = var2 ^ 29529094275369L;
      long var6 = var2 ^ 97053810423776L;
      int var8 = (int)((var2 ^ 97053810423776L) >>> 48);
      int var9 = (int)((var2 ^ 97053810423776L) << 16 >>> 48);
      int var10 = (int)(var6 << 32 >>> 32);
      String var11 = l.getOrDefault(var0, var0);
      if (!var11.equals(y.E) && !var11.equals(e.E)) {
         for (LowFireLoader var13 : v) {
            if (var13.E.equals(var11)) {
               return var13;
            }
         }

         return T(var1, var4);
      } else {
         if (Y != Theme.generation()) {
            Y = Theme.generation();
            i = z(y, (char)var8, (char)var9, var10);
            N = z(e, (char)var8, (char)var9, var10);
         }

         return var11.equals(y.E) ? i : N;
      }
   }

   private static LowFireLoader z(LowFireLoader var0, char var1, char var2, int var3) {
      return new LowFireLoader(
         var0.E, Theme.tint(0xFF000000 | var0.F) & 16777215, Theme.tint(0xFF000000 | var0.c) & 16777215, Theme.tint(0xFF000000 | var0.g) & 16777215
      );
   }

   private static LowFireLoader T(int var0, long var1) {
      long var3 = 10529509644605L;
      float[] var5 = ClientSupport.rgbToHsv(var0);
      int var6 = a(var5[0] - 0.035F, var3, var5[1] * 0.72F, Math.min(1.0F, var5[2] * 1.08F));
      int var7 = a(var5[0] + 0.06F, var3, var5[1] * 0.9F, var5[2]);
      return new LowFireLoader("Custom", var6, var0 & 16777215, var7);
   }

   private static int a(float var0, long var1, float var3, float var4) {
      return ClientSupport.hsvToRgb(var0 - (float)Math.floor(var0), var3, var4) & 16777215;
   }

   public static void ensureSourceClassInitialized() {
   }
}
