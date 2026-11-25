package dev.purxiy.modules.ambience;

import java.util.List;

final class AmbienceCatalog {
   static final int i = 0;
   static final int S = 1;
   static int I = 2;
   static int t = 3;
   static AmbienceCatalog q = new AmbienceCatalog("Motes", 0)
      .w(1.0F)
      .T(0.018F, 0.045F)
      .j(2.6F, 5.2F)
      .e(0.12F, 0.18F)
      .Y(1.0F, 0.1F, 0.06F, 0.35F)
      .Q(0.2F, 0.7F)
      .W(0.85F, 0.45F, 0.12F);
   static AmbienceCatalog X = new AmbienceCatalog("Stardust", 1)
      .w(1.4F)
      .T(0.01F, 0.024F)
      .j(1.6F, 3.4F)
      .e(0.05F, 0.25F)
      .Y(1.35F, 0.04F, 0.02F, 0.5F)
      .Q(0.9F, 2.6F)
      .W(1.0F, 0.9F, 0.3F)
      .R(0.6F, 1.0F);
   static AmbienceCatalog G = new AmbienceCatalog("Petals", I)
      .w(0.7F)
      .T(0.06F, 0.11F)
      .j(4.5F, 8.0F)
      .e(0.1F, 0.2F)
      .Y(0.9F, 0.05F, -0.22F, 0.6F)
      .Q(0.6F, 1.6F)
      .W(0.95F, 0.3F, 0.08F)
      .R(0.55F, 0.95F);
   static AmbienceCatalog E = new AmbienceCatalog("Snow", t)
      .w(1.2F)
      .T(0.02F, 0.05F)
      .j(4.0F, 7.0F)
      .e(0.08F, 0.22F)
      .Y(1.0F, 0.03F, -0.16F, 0.45F)
      .Q(0.3F, 0.9F)
      .W(0.9F, 0.35F, 0.1F)
      .R(0.65F, 1.0F);
   static List<AmbienceCatalog> e = List.of(q, X, G, E);
   final String u;
   final int F;
   float C;
   float j;
   float k;
   float w;
   float r;
   float O;
   float P;
   float o;
   float Y;
   float H;
   float f;
   float s;
   float B;
   boolean V;
   float J;
   float y;
   float b;
   float p;
   float T;

   private AmbienceCatalog(String var1, int var2) {
      this.u = var1;
      this.F = var2;
   }

   static AmbienceCatalog byName(String var0) {
      for (AmbienceCatalog var2 : e) {
         if (var2.u.equals(var0)) {
            return var2;
         }
      }

      return q;
   }

   static String[] names() {
      return e.stream().map(var0 -> var0.u).toArray(String[]::new);
   }

   int index() {
      return e.indexOf(this);
   }

   private AmbienceCatalog w(float var1) {
      this.C = var1;
      return this;
   }

   private AmbienceCatalog T(float var1, float var2) {
      this.j = var1;
      this.k = var2;
      return this;
   }

   private AmbienceCatalog j(float var1, float var2) {
      this.w = var1;
      this.r = var2;
      return this;
   }

   private AmbienceCatalog e(float var1, float var2) {
      this.O = var1;
      this.P = var2;
      return this;
   }

   private AmbienceCatalog Y(float var1, float var2, float var3, float var4) {
      this.o = var1;
      this.Y = var2;
      this.H = var3;
      this.f = var4;
      return this;
   }

   private AmbienceCatalog Q(float var1, float var2) {
      this.s = var1;
      this.B = var2;
      return this;
   }

   private AmbienceCatalog R(float var1, float var2) {
      this.V = true;
      this.J = var1;
      this.y = var2;
      return this;
   }

   private AmbienceCatalog W(float var1, float var2, float var3) {
      this.b = var1;
      this.p = var2;
      this.T = var3;
      return this;
   }
}
