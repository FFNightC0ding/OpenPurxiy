package dev.purxiy.modules.skins3d;

public final class Skins3DAnimation {
   private Skins3DAnimation() {
   }

   public static void stride(Skins3DCurve var0, Skins3DSupport5 var1, Skins3DAnimation.Data var2, Skins3DAnimation.Data var3) {
      float var4 = var1.H;
      float var5 = var1.N;
      float var6 = (float)Math.cos(var5);
      float var7 = (float)Math.sin(var5);
      float var8 = mix(var2.kneeLead(), var3.kneeLead(), var4);
      float var9 = Math.max(0.0F, (float)Math.sin(var5 + var8));
      float var10 = Math.max(0.0F, -((float)Math.sin(var5 + var8)));
      float var11 = mix(var2.legSwing(), var3.legSwing(), var4);
      float var12 = mix(var2.lift(), var3.lift(), var4);
      var0.d = var11 * var6 - var12 * var9 * var9;
      var0.q = -var11 * var6 - var12 * var10 * var10;
      float var13 = mix(var2.knee(), var3.knee(), var4);
      float var14 = mix(var2.kneeRest(), var3.kneeRest(), var4);
      var0.H = var14 + var13 * var9;
      var0.M = var14 + var13 * var10;
      float var15 = mix(var2.armSwing(), var3.armSwing(), var4);
      float var16 = mix(var2.armForward(), var3.armForward(), var4);
      float var17 = mix(var2.armOut(), var3.armOut(), var4);
      float var18 = mix(var2.elbow(), var3.elbow(), var4);
      float var19 = mix(var2.pump(), var3.pump(), var4);
      if (var1.h) {
         var0.s = -var16 - var15 * var6;
         var0.f = 0.0F;
         var0.e = var17;
         var0.g = var18 + var19 * Math.max(0.0F, var6);
      }

      if (var1.r) {
         var0.n = -var16 + var15 * var6;
         var0.D = 0.0F;
         var0.X = -var17;
         var0.Y = var18 + var19 * Math.max(0.0F, -var6);
      }

      float var20 = mix(var2.lean(), var3.lean(), var4);
      float var21 = -mix(var2.twist(), var3.twist(), var4) * var6;
      var0.y += var20;
      var0.m += var21;
      float var22 = mix(var2.snap(), var3.snap(), var4);
      var0.h = var0.h + mix(var2.bounce(), var3.bounce(), var4) * (float)Math.pow(Math.abs(var7), 1.0F / Math.max(0.2F, var22));
      var0.A = var0.A + mix(var2.roll(), var3.roll(), var4) * var7;
      var0.V = var0.V + mix(var2.hips(), var3.hips(), var4) * var7;
      var0.v -= var20;
      var0.L -= var21;
      var0.v = var0.v + mix(var2.nod(), var3.nod(), var4) * (float)Math.cos(2.0F * var5);
   }

   public static void busyArms(Skins3DCurve var0, Skins3DCurve var1, Skins3DSupport5 var2) {
      if (!var2.h) {
         var0.s = var1.s;
         var0.f = var1.f;
         var0.e = var1.e;
         var0.g = 0.0F;
      }

      if (!var2.r) {
         var0.n = var1.n;
         var0.D = var1.D;
         var0.X = var1.X;
         var0.Y = 0.0F;
      }
   }

   public static float breath(Skins3DSupport5 var0, float var1) {
      return (float)Math.sin(var0.V * 2.0F * Math.PI / var1);
   }

   public static float mix(float var0, float var1, float var2) {
      return var0 + (var1 - var0) * var2;
   }

   public static float clamp01(float var0) {
      return Math.max(0.0F, Math.min(1.0F, var0));
   }

   public static float smooth(float var0) {
      var0 = clamp01(var0);
      return var0 * var0 * (3.0F - 2.0F * var0);
   }

   public static float easeOut(float var0) {
      var0 = clamp01(var0);
      return 1.0F - (1.0F - var0) * (1.0F - var0) * (1.0F - var0);
   }

   public static float easeIn(float var0) {
      var0 = clamp01(var0);
      return var0 * var0 * var0;
   }

   public static float easeInOut(float var0) {
      var0 = clamp01(var0);
      return var0 < 0.5F ? 4.0F * var0 * var0 * var0 : 1.0F - (float)Math.pow(-2.0F * var0 + 2.0F, 3.0) / 2.0F;
   }

   public static float hold(float var0, float var1, float var2) {
      if (var0 <= 0.0F || var0 >= 1.0F) {
         return 0.0F;
      } else if (var0 < var1) {
         return easeInOut(var0 / var1);
      } else {
         return var0 > 1.0F - var2 ? easeInOut((1.0F - var0) / var2) : 1.0F;
      }
   }

   public static float spring(float var0) {
      var0 = clamp01(var0);
      return 1.0F - (float)(Math.cos(var0 * Math.PI * 2.5) * Math.exp(-5.0F * var0));
   }

   public record Data(
      float a, float V, float D, float v, float L, float b, float m, float j, float q, float h, float Y, float E, float k, float C, float F, float W, float O
   ) {
      public static Skins3DAnimation.Data mix(Skins3DAnimation.Data var0, Skins3DAnimation.Data var1, float var2) {
         if (var2 <= 0.0F) {
            return var0;
         } else {
            return var2 >= 1.0F
               ? var1
               : new Skins3DAnimation.Data(
                  Skins3DAnimation.mix(var0.a, var1.a, var2),
                  Skins3DAnimation.mix(var0.V, var1.V, var2),
                  Skins3DAnimation.mix(var0.D, var1.D, var2),
                  Skins3DAnimation.mix(var0.v, var1.v, var2),
                  Skins3DAnimation.mix(var0.L, var1.L, var2),
                  Skins3DAnimation.mix(var0.b, var1.b, var2),
                  Skins3DAnimation.mix(var0.m, var1.m, var2),
                  Skins3DAnimation.mix(var0.j, var1.j, var2),
                  Skins3DAnimation.mix(var0.q, var1.q, var2),
                  Skins3DAnimation.mix(var0.h, var1.h, var2),
                  Skins3DAnimation.mix(var0.Y, var1.Y, var2),
                  Skins3DAnimation.mix(var0.E, var1.E, var2),
                  Skins3DAnimation.mix(var0.k, var1.k, var2),
                  Skins3DAnimation.mix(var0.C, var1.C, var2),
                  Skins3DAnimation.mix(var0.F, var1.F, var2),
                  Skins3DAnimation.mix(var0.W, var1.W, var2),
                  Skins3DAnimation.mix(var0.O, var1.O, var2)
               );
         }
      }

      public float legSwing() {
         return this.a;
      }

      public float knee() {
         return this.V;
      }

      public float kneeRest() {
         return this.D;
      }

      public float kneeLead() {
         return this.v;
      }

      public float lift() {
         return this.L;
      }

      public float armSwing() {
         return this.b;
      }

      public float armForward() {
         return this.m;
      }

      public float armOut() {
         return this.j;
      }

      public float elbow() {
         return this.q;
      }

      public float pump() {
         return this.h;
      }

      public float lean() {
         return this.Y;
      }

      public float twist() {
         return this.E;
      }

      public float bounce() {
         return this.k;
      }

      public float snap() {
         return this.C;
      }

      public float roll() {
         return this.F;
      }

      public float hips() {
         return this.W;
      }

      public float nod() {
         return this.O;
      }

      public float floatState31() {
         return this.a;
      }

      public float floatState15() {
         return this.V;
      }

      public float floatState24() {
         return this.D;
      }

      public float floatState30() {
         return this.v;
      }

      public float floatState57() {
         return this.L;
      }

      public float floatState32() {
         return this.b;
      }

      public float floatState59() {
         return this.m;
      }

      public float floatState38() {
         return this.j;
      }

      public float floatState48() {
         return this.q;
      }

      public float floatState5() {
         return this.h;
      }

      public float floatState26() {
         return this.Y;
      }

      public float floatState44() {
         return this.E;
      }

      public float floatState58() {
         return this.k;
      }

      public float floatState16() {
         return this.C;
      }

      public float floatState36() {
         return this.F;
      }

      public float floatState4() {
         return this.W;
      }

      public float floatState13() {
         return this.O;
      }
   }
}
