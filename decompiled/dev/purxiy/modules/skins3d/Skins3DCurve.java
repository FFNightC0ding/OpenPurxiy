package dev.purxiy.modules.skins3d;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class Skins3DCurve {
   public float v;
   public float L;
   public float t;
   public float s;
   public float f;
   public float e;
   public float n;
   public float D;
   public float X;
   public float d;
   public float w;
   public float b;
   public float q;
   public float F;
   public float o;
   public float g;
   public float Y;
   public float H;
   public float M;
   public float y;
   public float m;
   public float U;
   public float V;
   public float h;
   public float C;
   public float u;
   public float Z;
   public float A;
   public float x;
   private static final Quaternionf l = new Quaternionf();
   private static final Quaternionf i = new Quaternionf();
   private static final Vector3f G = new Vector3f();

   public Skins3DCurve zero() {
      this.v = this.L = this.t = 0.0F;
      this.s = this.f = this.e = this.n = this.D = this.X = 0.0F;
      this.d = this.w = this.b = this.q = this.F = this.o = 0.0F;
      this.g = this.Y = this.H = this.M = 0.0F;
      this.y = this.m = this.U = this.V = 0.0F;
      this.h = this.C = this.u = this.Z = this.A = this.x = 0.0F;
      return this;
   }

   public Skins3DCurve set(Skins3DCurve var1) {
      this.v = var1.v;
      this.L = var1.L;
      this.t = var1.t;
      this.s = var1.s;
      this.f = var1.f;
      this.e = var1.e;
      this.n = var1.n;
      this.D = var1.D;
      this.X = var1.X;
      this.d = var1.d;
      this.w = var1.w;
      this.b = var1.b;
      this.q = var1.q;
      this.F = var1.F;
      this.o = var1.o;
      this.g = var1.g;
      this.Y = var1.Y;
      this.H = var1.H;
      this.M = var1.M;
      this.y = var1.y;
      this.m = var1.m;
      this.U = var1.U;
      this.V = var1.V;
      this.h = var1.h;
      this.C = var1.C;
      this.u = var1.u;
      this.Z = var1.Z;
      this.A = var1.A;
      this.x = var1.x;
      return this;
   }

   public Skins3DCurve lerp(Skins3DCurve var1, float var2) {
      long var3 = 73928304792944L;
      long var5 = var3 ^ 42174024164503L;
      if (var2 <= 0.0F) {
         return this;
      } else if (var2 >= 1.0F) {
         return this.set(var1);
      } else {
         Y(this.v, this.L, this.t, var1.v, var1.L, var5, var1.t, var2);
         this.v = G.x;
         this.L = G.y;
         this.t = G.z;
         Y(this.s, this.f, this.e, var1.s, var1.f, var5, var1.e, var2);
         this.s = G.x;
         this.f = G.y;
         this.e = G.z;
         Y(this.n, this.D, this.X, var1.n, var1.D, var5, var1.X, var2);
         this.n = G.x;
         this.D = G.y;
         this.X = G.z;
         Y(this.d, this.w, this.b, var1.d, var1.w, var5, var1.b, var2);
         this.d = G.x;
         this.w = G.y;
         this.b = G.z;
         Y(this.q, this.F, this.o, var1.q, var1.F, var5, var1.o, var2);
         this.q = G.x;
         this.F = G.y;
         this.o = G.z;
         this.g = this.g + (var1.g - this.g) * var2;
         this.Y = this.Y + (var1.Y - this.Y) * var2;
         this.H = this.H + (var1.H - this.H) * var2;
         this.M = this.M + (var1.M - this.M) * var2;
         this.y = this.y + (var1.y - this.y) * var2;
         this.m = this.m + (var1.m - this.m) * var2;
         this.U = this.U + (var1.U - this.U) * var2;
         this.V = this.V + (var1.V - this.V) * var2;
         this.h = this.h + (var1.h - this.h) * var2;
         this.C = this.C + (var1.C - this.C) * var2;
         this.u = this.u + (var1.u - this.u) * var2;
         this.Z = this.Z + (float)Math.IEEEremainder(var1.Z - this.Z, Math.PI * 2) * var2;
         this.A = this.A + (var1.A - this.A) * var2;
         this.x = this.x + (var1.x - this.x) * var2;
         return this;
      }
   }

   private static void Y(float var0, float var1, float var2, float var3, float var4, long var5, float var7, float var8) {
      SkinPreset.ensureSourceClassInitialized();
      if (var0 == var3 && var1 == var4 && var2 == var7) {
         G.set(var0, var1, var2);
      } else {
         l.rotationZYX(var2, var1, var0);
         i.rotationZYX(var7, var4, var3);
         l.slerp(i, var8).getEulerAnglesZYX(G);
      }
   }

   public Skins3DCurve mirror() {
      float var1 = this.s;
      float var2 = this.f;
      float var3 = this.e;
      this.s = this.n;
      this.f = -this.D;
      this.e = -this.X;
      this.n = var1;
      this.D = -var2;
      this.X = -var3;
      float var4 = this.d;
      float var5 = this.w;
      float var6 = this.b;
      this.d = this.q;
      this.w = -this.F;
      this.b = -this.o;
      this.q = var4;
      this.F = -var5;
      this.o = -var6;
      float var7 = this.g;
      this.g = this.Y;
      this.Y = var7;
      float var8 = this.H;
      this.H = this.M;
      this.M = var8;
      this.L = -this.L;
      this.t = -this.t;
      this.m = -this.m;
      this.U = -this.U;
      this.V = -this.V;
      this.C = -this.C;
      this.Z = -this.Z;
      this.A = -this.A;
      return this;
   }
}
