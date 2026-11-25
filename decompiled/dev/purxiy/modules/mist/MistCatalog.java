package dev.purxiy.modules.mist;

import java.util.List;

public final class MistCatalog {
   public static MistCatalog Q = new MistCatalog("Autumn", 3021323, 7025687, 11033634, 14129738, 15980966);
   public static MistCatalog r = new MistCatalog("Lilac", 2366259, 4864358, 8085150, 11111367, 14668528);
   public static MistCatalog J = new MistCatalog("Pearl", 2829875, 5659750, 9081500, 12436683, 15593972);
   public static MistCatalog y = new MistCatalog("Rose", 3084569, 6496818, 10239312, 13204620, 15780819);
   static List<MistCatalog> l = List.of(Q, r, J, y);
   public final String C;
   public final int N;
   public final int h;
   public final int I;
   public final int U;
   public final int p;
   public static boolean B;

   private MistCatalog(String var1, int var2, int var3, int var4, int var5, int var6) {
      this.C = var1;
      this.N = var2;
      this.h = var3;
      this.I = var4;
      this.U = var5;
      this.p = var6;
   }

   public static String[] names() {
      return l.stream().map(var0 -> var0.C).toArray(String[]::new);
   }

   public static MistCatalog byName(String var0) {
      for (MistCatalog var2 : l) {
         if (var2.C.equals(var0)) {
            return var2;
         }
      }

      return Q;
   }

   static float channel(int var0, int var1) {
      return (var0 >> 16 - 8 * var1 & 0xFF) / 255.0F;
   }
}
