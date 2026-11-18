// Sourced By DevFFNightmare
package dev.purxiy.modules.afterglow;

import java.util.List;

final class AfterglowCatalog {
   static final int Q = 0;
   static final int Z = 1;
   static int s = 2;
   static AfterglowCatalog l;
   static AfterglowCatalog q;
   static AfterglowCatalog y;
   static List<AfterglowCatalog> p;
   static AfterglowCatalog I;
   final String h;
   final int m;
   float V;
   float C;
   float X;
   float U;
   float S;
   float z;
   float t;
   float P;
   float n;
   float e;
   float D;
   float c;
   float Y;
   float M;
   float G;
   float T;

   private AfterglowCatalog(String var1, int var2) {
      this.h = var1;
      this.m = var2;
   }

   static AfterglowCatalog byName(String var0) {
      for (AfterglowCatalog var2 : p) {
         if (var2.h.equals(var0)) {
            return var2;
         }
      }

      return l;
   }

   static String[] names() {
      return p.stream().map(var0 -> var0.h).toArray(String[]::new);
   }

   private AfterglowCatalog J(float var1, float var2) {
      this.V = var1;
      this.C = var2;
      return this;
   }

   private AfterglowCatalog N(float var1, float var2) {
      this.X = var1;
      this.U = var2;
      return this;
   }

   private AfterglowCatalog A(float var1, float var2, float var3, float var4) {
      this.S = var1;
      this.z = var2;
      this.t = var3;
      this.P = var4;
      return this;
   }

   private AfterglowCatalog b(float var1, float var2, float var3) {
      this.n = var1;
      this.e = var2;
      this.D = var3;
      return this;
   }

   private AfterglowCatalog F(float var1, float var2) {
      this.c = var1;
      this.Y = var2;
      return this;
   }

   private AfterglowCatalog N(float var1, float var2, float var3) {
      this.M = var1;
      this.G = var2;
      this.T = var3;
      return this;
   }

   // The style presets were missing from this source drop, which left p (the catalog list) null and made every
   // AfterglowModule constructor throw an NPE, so the module switched itself back off the moment it was enabled.
   // Size (J), lifetime (N), drift speeds (A), sway (b), spin (F) and glow (N) are rebuilt here per style.
   static {
      l = new AfterglowCatalog("Motes", Q)
         .J(0.020F, 0.040F)
         .N(2.5F, 5.0F)
         .A(-0.06F, 0.10F, -0.02F, 0.05F)
         .b(0.02F, 0.55F, 0.04F)
         .F(0.15F, 0.50F)
         .N(0.85F, 0.45F, 0.12F);
      q = new AfterglowCatalog("Stardust", Z)
         .J(0.010F, 0.022F)
         .N(1.4F, 3.2F)
         .A(-0.10F, 0.14F, -0.05F, 0.06F)
         .b(0.01F, 0.90F, 0.02F)
         .F(0.80F, 2.40F)
         .N(1.00F, 0.85F, 0.25F);
      y = new AfterglowCatalog("Petals", s)
         .J(0.055F, 0.095F)
         .N(4.0F, 7.5F)
         .A(-0.18F, 0.18F, -0.30F, -0.10F)
         .b(-0.02F, 0.35F, 0.22F)
         .F(0.50F, 1.40F)
         .N(0.92F, 0.30F, 0.08F);
      p = List.of(l, q, y);
      I = l;
   }

}
