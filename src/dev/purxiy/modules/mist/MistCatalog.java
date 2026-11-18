// Sourced By DevFFNightmare
package dev.purxiy.modules.mist;

import java.util.List;

public final class MistCatalog {
   public static MistCatalog Q;
   public static MistCatalog r;
   public static MistCatalog J;
   public static MistCatalog y;
   static List<MistCatalog> l;
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

   // The palette presets were missing from this source drop, which left l (the palette list) null and made
   // MistModule's constructor throw an NPE, so the module switched itself back off the moment it was enabled.
   // Every palette is the five stop gradient the renderer samples (N, h, I, U, p).
   static {
      Q = new MistCatalog("Autumn", 0x2E1A0B, 0x6B3417, 0xA85C22, 0xD79A4A, 0xF3D9A6);
      r = new MistCatalog("Lilac", 0x241B33, 0x4A3966, 0x7B5E9E, 0xA98BC7, 0xDFD2F0);
      J = new MistCatalog("Pearl", 0x2B2E33, 0x565C66, 0x8A929C, 0xBDC4CB, 0xEDF1F4);
      y = new MistCatalog("Rose", 0x2F1119, 0x632232, 0x9C3D50, 0xC97C8C, 0xF0CBD3);
      l = List.of(Q, r, J, y);
   }

}
