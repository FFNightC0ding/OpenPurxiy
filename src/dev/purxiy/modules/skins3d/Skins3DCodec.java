package dev.purxiy.modules.skins3d;

import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class Skins3DCodec {
   private static final Vector3f y = new Vector3f();
   private static final Vector3f A = new Vector3f();
   private static final Vector3f b = new Vector3f();
   private static final Quaternionf a = new Quaternionf();
   private static final Quaternionf O = new Quaternionf();
   private static final Quaternionf n = new Quaternionf();
   private static final long c;

   private Skins3DCodec() {
   }

   public static Skins3DCurve read(Skins3DRestPose var0, Skins3DCurve var1) {
      var1.zero();
      var1.v = var0.d[0];
      var1.L = var0.D[0];
      var1.t = var0.v[0];
      var1.s = var0.d[2];
      var1.f = var0.D[2];
      var1.e = var0.v[2];
      var1.n = var0.d[3];
      var1.D = var0.D[3];
      var1.X = var0.v[3];
      var1.d = var0.d[4];
      var1.w = var0.D[4];
      var1.b = var0.v[4];
      var1.q = var0.d[5];
      var1.F = var0.D[5];
      var1.o = var0.v[5];
      return var1;
   }

   public static void write(Skins3DRestPose var0, Skins3DCurve var1, Skins3DCodec.Data var2, float var3) {
      long var4 = 77800606286420L;
      long var6 = var4 ^ 102870885475249L;
      int var8 = (int)((var4 ^ 139642583679153L) >>> 48);
      long var9 = (var4 ^ 139642583679153L) << 16 >>> 16;
      var0.d[4] = var1.d;
      var0.D[4] = var1.w;
      var0.v[4] = var1.b;
      var0.d[5] = var1.q;
      var0.D[5] = var1.F;
      var0.v[5] = var1.o;
      var0.y[2] = Math.max(0.0F, var1.g);
      var0.y[3] = Math.max(0.0F, var1.Y);
      var0.y[4] = Math.max(0.0F, var1.H);
      var0.y[5] = Math.max(0.0F, var1.M);
      if (var1.V != 0.0F) {
         d(var0, var1.V, var2);
      }

      F(var0, var1, var2, var6);
      float var11 = var3 > 0.0F ? var3 * sink(var0, var2) : 0.0F;
      c((char)var8, var9, var0, var1, var2, var11);
   }

   private static void d(Skins3DRestPose var0, float var1, Skins3DCodec.Data var2) {
      float var3 = (float)(-Math.asin(Math.max(-0.9F, Math.min(0.9F, var1 / var2.hips()))));
      var0.v[1] = var0.v[1] + var3;
      float var4 = var2.hips() * (1.0F - (float)Math.cos(var3));
      var0.V[4] = var0.V[4] + var1;
      var0.V[5] = var0.V[5] + var1;
      var0.b[4] = var0.b[4] - var4;
      var0.b[5] = var0.b[5] - var4;
   }

   private static void F(Skins3DRestPose var0, Skins3DCurve var1, Skins3DCodec.Data var2, long var3) {
      long var5 = 91101428119011L;
      byte var7 = 1;
      a.rotationZYX(var0.v[var7], var0.D[var7], var0.d[var7]).transform(A.set(0.0F, var2.hips(), 0.0F)).add(var0.V[var7], var0.b[var7], var0.F[var7]);
      SkinPreset.ensureSourceClassInitialized();
      O.rotationZ(var1.U).rotateX(var1.y).rotateY(var1.m);
      boolean var8 = var1.y != 0.0F || var1.U != 0.0F || var1.m != 0.0F;
      if (var8) {
         X(var0, var7, A);
         X(var0, 0, A);
         X(var0, 2, A);
         X(var0, 3, A);
         L(var5, var0, var7, true, var0.d[var7], var0.D[var7], var0.v[var7]);
      }

      if (var2.rigidHead()) {
         var0.d[0] = var0.d[var7];
         long var9 = 30018115101156L;
         int var11 = var9 < 0L ? -1 : 1;
         var0.D[0] = var0.D[var7];
         var0.v[0] = var0.v[var7];
      } else {
         L(var5, var0, 0, var8, var1.v, var1.L, var1.t);
      }

      L(var5, var0, 2, var8, var1.s, var1.f, var1.e);
      L(var5, var0, 3, var8, var1.n, var1.D, var1.X);
   }

   private static void L(long var0, Skins3DRestPose var2, int var3, boolean var4, float var5, float var6, float var7) {
      if (!var4) {
         var2.d[var3] = var5;
         var2.D[var3] = var6;
         var2.v[var3] = var7;
      } else {
         Skins3DSupport.angles(n.set(O).mul(a.rotationZYX(var7, var6, var5)), b);
         var2.d[var3] = b.x;
         var2.D[var3] = b.y;
         var2.v[var3] = b.z;
      }
   }

   private static void X(Skins3DRestPose var0, int var1, Vector3f var2) {
      O.transform(y.set(var0.V[var1] - var2.x, var0.b[var1] - var2.y, var0.F[var1] - var2.z));
      var0.V[var1] = var2.x + y.x;
      var0.b[var1] = var2.y + y.y;
      var0.F[var1] = var2.z + y.z;
   }

   static float sink(Skins3DRestPose var0, Skins3DCodec.Data var1) {
      return var1.ground() - Math.max(lowest(var0, 4, var1), lowest(var0, 5, var1));
   }

   static float lowest(Skins3DRestPose var0, int var1, Skins3DCodec.Data var2) {
      float var3 = var0.y[var1];
      float var4 = (float)Math.cos(var3);
      float var5 = (float)Math.sin(var3);
      a.rotationZYX(var0.v[var1], var0.D[var1], var0.d[var1]);
      float var6 = Float.NEGATIVE_INFINITY;

      for (int var7 = 0; var7 < 2; var7++) {
         float var8 = var7 == 0 ? var2.toe() : var2.heel();
         a.transform(y.set(0.0F, var2.thigh() - var8 * var5 + var2.shin() * var4, var8 * var4 + var2.shin() * var5));
         var6 = Math.max(var6, y.y);
      }

      return var6 + var0.b[var1];
   }

   private static void c(char var0, long var1, Skins3DRestPose var3, Skins3DCurve var4, Skins3DCodec.Data var5, float var6) {
      SkinPreset.ensureSourceClassInitialized();
      int var7 = (int)c;
      var3.V[var7] = var3.V[var7] + var4.C;
      var3.b[var7] = var3.b[var7] + (var6 - var4.h);
      var3.F[var7] = var3.F[var7] + var4.u;
      if (var4.Z != 0.0F || var4.A != 0.0F || var4.x != 0.0F) {
         var3.d[var7] = var3.d[var7] + var4.x;
         var3.D[var7] = var3.D[var7] + var4.Z;
         var3.v[var7] = var3.v[var7] + var4.A;
         float var8 = var5.ground();
         a.rotationZYX(var3.v[var7], var3.D[var7], var3.d[var7]).transform(y.set(0.0F, var8, 0.0F));
         var3.V[var7] = var3.V[var7] + (0.0F - y.x);
         var3.b[var7] = var3.b[var7] + (var8 - y.y);
         var3.F[var7] = var3.F[var7] + (0.0F - y.z);
      }
   }

   static {
      long var0 = 80614017875469L;
      long var2 = 6977018840478006795L ^ var0;
      c = var2;
   }

   public record Data(float w, float c, float v, float Q, float y, float N, boolean T) {
      public float leg() {
         return this.v + this.Q;
      }

      public static Skins3DCodec.Data of(float[][] var0, Skins3DBuilder.SkinVertex var1, boolean var2) {
         float var3 = Float.isNaN(var1.t) ? var1.r / 2.0F : var1.t;
         float var4 = 0.0F;
         float var5 = 0.0F;

         for (int var6 = 0; var6 < var1.p; var6++) {
            for (int var7 = 0; var7 < 4; var7++) {
               if (var1.coordinate(var6, var7, 1, 0.0F) > var1.r - 1.5F) {
                  float var8 = var1.coordinate(var6, var7, 2, 0.0F);
                  var4 = Math.min(var4, var8);
                  var5 = Math.max(var5, var8);
               }
            }
         }

         return new Skins3DCodec.Data(var0[4][1], 24.0F, var3, var1.r - var3, var4, var5, var2);
      }

      public float hips() {
         return this.w;
      }

      public float ground() {
         return this.c;
      }

      public float thigh() {
         return this.v;
      }

      public float shin() {
         return this.Q;
      }

      public float toe() {
         return this.y;
      }

      public float heel() {
         return this.N;
      }

      public boolean rigidHead() {
         return this.T;
      }

      public float floatState6() {
         return this.w;
      }

      public float floatState25() {
         return this.c;
      }

      public float floatState30() {
         return this.v;
      }

      public float floatState34() {
         return this.Q;
      }

      public float floatState2() {
         return this.y;
      }

      public float floatState51() {
         return this.N;
      }

      public boolean booleanState67() {
         return this.T;
      }
   }
}
