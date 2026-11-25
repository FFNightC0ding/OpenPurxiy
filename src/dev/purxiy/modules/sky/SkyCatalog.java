package dev.purxiy.modules.sky;

import java.util.List;

final class SkyCatalog {
   static SkyCatalog M;
   static SkyCatalog X;
   static SkyCatalog o;
   static SkyCatalog n;
   static List<SkyCatalog> a;
   final String q;
   int S;
   int B;
   int A;
   int H;
   int x;
   int w;
   int e;
   int b;
   int O;
   int k;
   int W;
   int j;
   int Z;
   int p;
   int P;
   int z;
   int m;
   int C;
   float U;
   float E;
   float s;
   int I;
   int R;
   int L;
   int d;
   SkyCodec.Data J;

   private SkyCatalog(String var1) {
      this.q = var1;
   }

   SkyCatalog in(SkyCodec var1) {
      if (var1.original()) {
         return this;
      } else {
         SkyCatalog var2 = new SkyCatalog(this.q)
            .O(var1.carry(this.S), var1.carry(this.B), var1.carry(this.A), var1.carry(this.H))
            .A(var1.carry(this.x), var1.carry(this.w), var1.carry(this.e), var1.carry(this.b))
            .z(var1.carry(this.O), var1.carry(this.k), var1.carry(this.W))
            .R(var1.carry(this.j), var1.carry(this.Z))
            .O(var1.carry(this.p), var1.carry(this.P), var1.carry(this.z))
            .S(var1.carry(this.m), var1.carry(this.C))
            .L(var1.carry(this.I), var1.carry(this.R), var1.carry(this.L))
            .y(var1.carry(this.d))
            .V(this.J);
         var2.U = this.U;
         var2.E = this.E;
         var2.s = this.s;
         return var2;
      }
   }

   static SkyCatalog byName(String var0) {
      for (SkyCatalog var2 : a) {
         if (var2.q.equals(var0)) {
            return var2;
         }
      }

      return M;
   }

   static String[] names() {
      return a.stream().map(var0 -> var0.q).toArray(String[]::new);
   }

   private SkyCatalog O(int var1, int var2, int var3, int var4) {
      this.S = var1;
      this.B = var2;
      this.A = var3;
      this.H = var4;
      return this;
   }

   private SkyCatalog A(int var1, int var2, int var3, int var4) {
      this.x = var1;
      this.w = var2;
      this.e = var3;
      this.b = var4;
      return this;
   }

   private SkyCatalog z(int var1, int var2, int var3) {
      this.O = var1;
      this.k = var2;
      this.W = var3;
      return this;
   }

   private SkyCatalog R(int var1, int var2) {
      this.j = var1;
      this.Z = var2;
      return this;
   }

   private SkyCatalog O(int var1, int var2, int var3) {
      this.p = var1;
      this.P = var2;
      this.z = var3;
      return this;
   }

   private SkyCatalog S(int var1, int var2) {
      this.m = var1;
      this.C = var2;
      return this;
   }

   private SkyCatalog l(float var1, float var2, float var3) {
      float var4 = (float)Math.sqrt(var1 * var1 + var2 * var2 + var3 * var3);
      this.U = var1 / var4;
      this.E = var2 / var4;
      this.s = var3 / var4;
      return this;
   }

   private SkyCatalog L(int var1, int var2, int var3) {
      this.I = var1;
      this.R = var2;
      this.L = var3;
      return this;
   }

   private SkyCatalog y(int var1) {
      this.d = var1;
      return this;
   }

   private SkyCatalog V(SkyCodec.Data var1) {
      this.J = var1;
      return this;
   }

   private static SkyCatalog palette(String var0, int var1, int var2, int var3, int var4) {
      int[] var5 = ramp(var1, var2, 4);
      int[] var6 = ramp(var2, var3, 4);
      int[] var7 = ramp(var3, var4, 3);
      int[] var8 = ramp(var4, mix(var4, 16777215, 0.35F), 2);
      int[] var9 = ramp(var3, mix(var3, var4, 0.5F), 3);
      int[] var10 = ramp(var4, var3, 2);
      int[] var11 = ramp(var2, var4, 3);
      return new SkyCatalog(var0)
         .O(var5[0], var5[1], var5[2], var5[3])
         .A(var6[0], var6[1], var6[2], var6[3])
         .z(var7[0], var7[1], var7[2])
         .R(var8[0], var8[1])
         .O(var9[0], var9[1], var9[2])
         .S(var10[0], var10[1])
         .L(var11[0], var11[1], var11[2])
         .y(mix(var3, 16777215, 0.55F))
         .l(0.35F, 0.85F, 0.25F)
         .V(new SkyCodec.Data(var1, 0.0F, 0.0F, 1.0F, 1.0F, 0.12F, 0.04F));
   }

   private static int mix(int var0, int var1, float var2) {
      int var3 = (int)((var0 >> 16 & 0xFF) + ((var1 >> 16 & 0xFF) - (var0 >> 16 & 0xFF)) * var2 + 0.5F);
      int var4 = (int)((var0 >> 8 & 0xFF) + ((var1 >> 8 & 0xFF) - (var0 >> 8 & 0xFF)) * var2 + 0.5F);
      int var5 = (int)((var0 & 0xFF) + ((var1 & 0xFF) - (var0 & 0xFF)) * var2 + 0.5F);
      return var3 << 16 | var4 << 8 | var5;
   }

   private static int[] ramp(int var0, int var1, int var2) {
      int[] var3 = new int[var2];

      for (int var4 = 0; var4 < var2; var4++) {
         var3[var4] = mix(var0, var1, var2 <= 1 ? 0.0F : (float)var4 / (var2 - 1));
      }

      return var3;
   }

   static {
      SkyCatalog var0 = palette("SourceByDevFFnightmare DeObfDeCompByGuideX", 659246, 4403307, 13084927, 8381439);
      SkyCatalog var1 = palette("Nebula", 1378342, 5971788, 16752342, 16766632);
      SkyCatalog var2 = palette("Eclipse", 460555, 2761524, 16761994, 16743238);
      SkyCatalog var3 = palette("Dream", 992313, 4026514, 11332095, 14202623);
      M = var0;
      X = var1;
      o = var2;
      n = var3;
      a = List.of(var0, var1, var2, var3);
   }
}
