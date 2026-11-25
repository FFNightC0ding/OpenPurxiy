package dev.purxiy.modules.knife;

import java.util.ArrayList;
import java.util.List;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public final class KnifeBuilder {
   final KnifeBuilder.Data[] B;
   public static int u = 0;

   KnifeBuilder(List<KnifeBuilder.Data> var1) {
      this.B = var1.toArray(KnifeBuilder.Data[]::new);
   }

   public int vertexCount() {
      return this.B.length;
   }

   public float x(int var1) {
      return this.B[var1].S;
   }

   public float y(int var1) {
      return this.B[var1].o;
   }

   public float z(int var1) {
      return this.B[var1].y;
   }

   public float nx(int var1) {
      return this.B[var1].x;
   }

   public float ny(int var1) {
      return this.B[var1].W;
   }

   public float nz(int var1) {
      return this.B[var1].t;
   }

   public int color(int var1) {
      return this.B[var1].A;
   }

   public float metal(int var1) {
      return this.B[var1].Z;
   }

   public float u(int var1) {
      return this.B[var1].U;
   }

   public float v(int var1) {
      return this.B[var1].J;
   }

   KnifeBuilder undercoat() {
      ArrayList var1 = new ArrayList(this.B.length);

      for (KnifeBuilder.Data var5 : this.B) {
         int var6 = Math.max(var5.A >> 16 & 0xFF, Math.max(var5.A >> 8 & 0xFF, var5.A & 0xFF));
         var1.add(new KnifeBuilder.Data(var5.S, var5.o, var5.y, var5.x, var5.W, var5.t, 0xFF000000 | var6 << 16 | var6 << 8 | var6, var5.Z, var5.U, var5.J));
      }

      return new KnifeBuilder(var1);
   }

   static int tint(int var0, float var1) {
      return 0xFF000000
         | Math.min(255, (int)((var0 >> 16 & 0xFF) * var1)) << 16
         | Math.min(255, (int)((var0 >> 8 & 0xFF) * var1)) << 8
         | Math.min(255, (int)((var0 & 0xFF) * var1));
   }

   public record Data(float S, float o, float y, float x, float W, float t, int A, float Z, float U, float J) {
      public float x() {
         return this.S;
      }

      public float y() {
         return this.o;
      }

      public float z() {
         return this.y;
      }

      public float nx() {
         return this.x;
      }

      public float ny() {
         return this.W;
      }

      public float nz() {
         return this.t;
      }

      public int color() {
         return this.A;
      }

      public float metal() {
         return this.Z;
      }

      public float u() {
         return this.U;
      }

      public float v() {
         return this.J;
      }

      public float floatState14() {
         return this.S;
      }

      public float floatState11() {
         return this.o;
      }

      public float floatState4() {
         return this.W;
      }

      public float floatState20() {
         return this.t;
      }

      public int integerState38() {
         return this.A;
      }

      public float floatState43() {
         return this.Z;
      }

      public float floatState18() {
         return this.U;
      }

      public float floatState10() {
         return this.J;
      }
   }

   static final class TypeB {
      final List<KnifeBuilder.Data> y = new ArrayList<>();
      float w = 0.85F;
      int Y = 3;

      KnifeBuilder.TypeB material(float var1) {
         this.w = var1;
         this.Y = var1 < 0.5F ? 2 : 3;
         return this;
      }

      Vector3f p(float var1, float var2, float var3) {
         return new Vector3f(var1, var2, var3);
      }

      void tri(Vector3f var1, Vector3f var2, Vector3f var3, int var4) {
         Vector3f var5 = new Vector3f(var2).sub(var1).cross(new Vector3f(var3).sub(var1));
         if (!(var5.lengthSquared() < 1.0E-15F)) {
            var5.normalize();

            for (Vector3f var9 : new Vector3f[]{var1, var2, var3}) {
               float var10 = (this.Y % 2 * 512 + 4 + Math.clamp((var9.x + 0.16F) / 0.32F, 0.0F, 1.0F) * 503.0F) / 1024.0F;
               float var11 = (this.Y / 2 * 512 + 4 + Math.clamp((0.5F - var9.y) / 0.74F, 0.0F, 1.0F) * 503.0F) / 1024.0F;
               this.y.add(new KnifeBuilder.Data(var9.x, var9.y, var9.z, var5.x, var5.y, var5.z, var4 | 0xFF000000, this.w, var10, var11));
            }
         }
      }

      void quad(Vector3f var1, Vector3f var2, Vector3f var3, Vector3f var4, int var5) {
         this.tri(var1, var2, var3, var5);
         this.tri(var1, var3, var4, var5);
      }

      void blade(float[][] var1, float var2, int var3, int var4) {
         int var5 = this.Y;

         for (int var6 = 0; var6 < var1.length - 1; var6++) {
            for (int var10 : new int[]{-1, 1}) {
               Vector3f[] var11 = this.bladeRow(var1[var6], var2 * var10);
               Vector3f[] var12 = this.bladeRow(var1[var6 + 1], var2 * var10);

               for (int var13 = 0; var13 < 4; var13++) {
                  int var14 = var13 != 0 && var13 != 3 ? (var13 == 1 ? var3 : KnifeBuilder.tint(var3, 0.78F)) : var4;
                  this.Y = var13 != 0 && var13 != 3 ? 0 : 1;
                  if (var10 > 0) {
                     this.quad(var11[var13], var11[var13 + 1], var12[var13 + 1], var12[var13], var14);
                  } else {
                     this.quad(var11[var13 + 1], var11[var13], var12[var13], var12[var13 + 1], var14);
                  }
               }
            }
         }

         Vector3f[] var15 = this.bladeRow(var1[0], var2);
         Vector3f[] var16 = this.bladeRow(var1[0], -var2);
         this.Y = 0;

         for (int var17 = 0; var17 < 4; var17++) {
            this.quad(var15[var17 + 1], var15[var17], var16[var17], var16[var17 + 1], var3);
         }

         this.Y = var5;
      }

      Vector3f[] bladeRow(float[] var1, float var2) {
         float var3 = var1[0];
         float var4 = var1[1];
         float var5 = var1[2];
         float var6 = var5 - var4;
         return new Vector3f[]{
            this.p(var4, var3, 0.0F),
            this.p(var4 + var6 * 0.16F, var3, var2 * 0.72F),
            this.p(var4 + var6 * 0.52F, var3, var2),
            this.p(var5 - var6 * 0.18F, var3, var2 * 0.72F),
            this.p(var5, var3, 0.0F)
         };
      }

      void grip(float var1, float var2, float var3, float var4, float var5, int var6) {
         int var7 = this.Y;
         this.Y = 2;
         byte var8 = 12;
         byte var9 = 12;

         for (int var10 = 0; var10 < var9; var10++) {
            float var11 = (float)var10 / var9;
            float var12 = (float)(var10 + 1) / var9;
            float var13 = 0.8F + 0.2F * (float)Math.sin(Math.PI * var11);
            float var14 = 0.8F + 0.2F * (float)Math.sin(Math.PI * var12);

            for (int var15 = 0; var15 < var8; var15++) {
               float var16 = var15 * 2 * (float) Math.PI / var8;
               float var17 = (var15 + 1) * 2 * (float) Math.PI / var8;
               this.quad(
                  this.p(var1 + var4 * var13 * (float)Math.cos(var16), var2 + (var3 - var2) * var11, var5 * var13 * (float)Math.sin(var16)),
                  this.p(var1 + var4 * var14 * (float)Math.cos(var16), var2 + (var3 - var2) * var12, var5 * var14 * (float)Math.sin(var16)),
                  this.p(var1 + var4 * var14 * (float)Math.cos(var17), var2 + (var3 - var2) * var12, var5 * var14 * (float)Math.sin(var17)),
                  this.p(var1 + var4 * var13 * (float)Math.cos(var17), var2 + (var3 - var2) * var11, var5 * var13 * (float)Math.sin(var17)),
                  var6
               );
            }
         }

         for (int var18 = 0; var18 < 2; var18++) {
            for (int var19 = 0; var19 < var8; var19++) {
               float var20 = var19 * 2 * (float) Math.PI / var8;
               float var21 = (var19 + 1) * 2 * (float) Math.PI / var8;
               float var22 = var18 == 0 ? var2 : var3;
               Vector3f var23 = this.p(var1, var22, 0.0F);
               Vector3f var24 = this.p(var1 + var4 * 0.8F * (float)Math.cos(var20), var22, var5 * 0.8F * (float)Math.sin(var20));
               Vector3f var25 = this.p(var1 + var4 * 0.8F * (float)Math.cos(var21), var22, var5 * 0.8F * (float)Math.sin(var21));
               if (var18 == 0) {
                  this.tri(var23, var24, var25, var6);
               } else {
                  this.tri(var23, var25, var24, var6);
               }
            }
         }

         this.Y = var7;
      }

      void box(float var1, float var2, float var3, float var4, float var5, float var6, int var7) {
         Vector3f[] var8 = new Vector3f[8];

         for (int var9 = 0; var9 < 8; var9++) {
            var8[var9] = this.p(
               var1 + ((var9 & 1) == 0 ? -var4 : var4) / 2.0F, var2 + ((var9 & 2) == 0 ? -var5 : var5) / 2.0F, var3 + ((var9 & 4) == 0 ? -var6 : var6) / 2.0F
            );
         }

         for (int[] var12 : new int[][]{{0, 2, 3, 1}, {4, 5, 7, 6}, {0, 4, 6, 2}, {1, 3, 7, 5}, {0, 1, 5, 4}, {2, 6, 7, 3}}) {
            this.quad(var8[var12[0]], var8[var12[1]], var8[var12[2]], var8[var12[3]], var7);
         }
      }

      void ring(float var1, float var2, float var3, float var4, int var5) {
         for (int var6 = 0; var6 < 48; var6++) {
            for (int var7 = 0; var7 < 10; var7++) {
               Vector3f[] var8 = new Vector3f[4];
               int var9 = 0;

               for (int[] var13 : new int[][]{{var6, var7}, {var6 + 1, var7}, {var6 + 1, var7 + 1}, {var6, var7 + 1}}) {
                  float var14 = var13[0] * 2 * (float) Math.PI / 48.0F;
                  float var15 = var13[1] * 2 * (float) Math.PI / 10.0F;
                  float var16 = var3 + var4 * (float)Math.cos(var15);
                  var8[var9++] = this.p(var1 + var16 * (float)Math.cos(var14), var2 + var16 * (float)Math.sin(var14), var4 * (float)Math.sin(var15));
               }

               this.quad(var8[0], var8[1], var8[2], var8[3], var5);
            }
         }
      }

      void bolt(float var1, float var2, float var3, float var4, int var5) {
         for (int var6 = 0; var6 < 20; var6++) {
            float var7 = var6 * 2 * (float) Math.PI / 20.0F;
            float var8 = (var6 + 1) * 2 * (float) Math.PI / 20.0F;
            Vector3f var9 = this.p(var1 + var4 * (float)Math.cos(var7), var2 + var4 * (float)Math.sin(var7), var3);
            Vector3f var10 = this.p(var1 + var4 * (float)Math.cos(var8), var2 + var4 * (float)Math.sin(var8), var3);
            if (var3 > 0.0F) {
               this.tri(this.p(var1, var2, var3), var9, var10, var5);
            } else {
               this.tri(this.p(var1, var2, var3), var10, var9, var5);
            }
         }

         this.box(var1, var2, var3 + Math.signum(var3) * 3.0E-4F, var4 * 0.95F, 0.0015F, 5.0E-4F, -14473678);
      }

      void append(KnifeBuilder var1, Matrix4f var2) {
         Vector3f var3 = new Vector3f();
         Vector3f var4 = new Vector3f();

         for (KnifeBuilder.Data var8 : var1.B) {
            var2.transformPosition(var8.S, var8.o, var8.y, var3);
            var2.transformDirection(var8.x, var8.W, var8.t, var4).normalize();
            this.y.add(new KnifeBuilder.Data(var3.x, var3.y, var3.z, var4.x, var4.y, var4.z, var8.A, var8.Z, var8.U, var8.J));
         }
      }

      KnifeBuilder build() {
         return new KnifeBuilder(this.y);
      }
   }
}
