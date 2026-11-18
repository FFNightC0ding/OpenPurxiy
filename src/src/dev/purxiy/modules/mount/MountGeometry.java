// Sourced By DevFFNightmare
package dev.purxiy.modules.mount;

import org.joml.Matrix4f;
import org.joml.Matrix4fc;
import org.joml.Vector3f;

public final class MountGeometry {
   public float P;
   public float s;
   public float u;
   public float W;
   public float B;
   public float j;
   public float E;
   public float r;
   public float v;
   public float G;
   public float H;
   public float l;
   public float x;
   public final float[] p = new float[4];
   public final float[] L = new float[4];
   public float n;
   public float i;
   public final Matrix4f[] A = MountRenderer7.newParts();
   public final Matrix4f c = new Matrix4f();
   public final Matrix4f J = new Matrix4f();
   private final Matrix4f T = new Matrix4f();
   private final Vector3f Z = new Vector3f();



   public void compute(Matrix4fc var1, MountFactory.Data7 var2) {
      long var3 = 137160051825009L;
      long var5 = var3 ^ 28245960112908L;
      int var7 = (int)((var3 ^ 28245960112908L) >>> 32);
      int var8 = (int)((var3 ^ 28245960112908L) << 32 >>> 48);
      int var9 = (int)(var5 << 48 >>> 48);
      long var10 = var3 ^ 138867974131557L;
      long var12 = var3 ^ 135307642662532L;
      this.c.set(var1).rotateY((float)Math.toRadians(-this.P));
      this.c.rotateX(-this.s).rotateZ(this.u);
      if (var2.bike() && this.E != 0.0F) {
         this.c.rotateZ(-this.E);
      }

      if (this.r != 0.0F) {
         float var14 = var2.wheels()[2].center().z;
         float var15 = 0.0F;
         this.c.translate(0.0F, var15, var14).rotateX(-this.r).translate(0.0F, -var15, -var14);
      }

      this.J.set(this.c).translate(0.0F, this.j, 0.0F).translate(0.0F, 0.45F, 0.0F).rotateX(this.W).rotateZ(this.B).translate(0.0F, -0.45F, 0.0F);

      for (Matrix4f var17 : this.A) {
         var17.set(this.J);
      }

      MountFactory.Data2 var20 = var2.steer();
      MountFactory.Data2 var22 = var2.fork();
      MountFactory.Data2 var23 = var2.swing();
      if (var2.bike()) {
         Matrix4f var24 = this.A(new Matrix4f(this.J), var22 != null ? var22 : var20, this.G * (var22 != null ? var22.ratio() : 1.0F));
         if (var22 != null) {
            this.A[10].set(var24).translate(var22.axis().x * -this.L[0], var22.axis().y * -this.L[0], var22.axis().z * -this.L[0]);
         }

         if (var20 != null) {
            this.A(this.A[9].set(this.J), var20, this.G * var20.ratio());
         }

         this.R(var24, var2, 0, 0.0F, var12, true, var22);
         Matrix4f var18 = new Matrix4f(this.J);
         if (var23 != null) {
            this.A(var18, var23, this.l);
            this.A[11].set(var18);
         }

         this.R(var18, var2, 2, 0.0F, var12, false, null);
         this.I(var7, (short)var8, var24, var18, (short)var9);
      } else {
         if (var20 != null) {
            this.A(this.A[9].set(this.J), var20, this.v * var20.ratio());
         }

         for (int var25 = 0; var25 < 4; var25++) {
            this.R(this.c, var2, var25, var25 < 2 ? this.G : 0.0F, var12, false, null);
         }
      }

      if (var2.kind() == MountFactory.TypeK.BICYCLE && var2.swing() != null) {
         this.A(this.A[15].set(this.J), var2.swing(), this.p[2] * var2.swing().ratio());
      }

      MountFactory.Data2 var26 = var2.crank();
      if (var26 != null) {
         this.A(this.A[12].set(this.J), var26, this.H);
         this.X(13, var26, var2, 0.0F, var10);
         this.X(14, var26, var2, (float) Math.PI, var10);
      }
   }

   private void I(int var1, short var2, Matrix4f var3, Matrix4f var4, short var5) {
      this.A[5].set(var3);
      this.A[7].set(var4);
   }

   private void X(int var1, MountFactory.Data2 var2, MountFactory.Data7 var3, float var4, long var5) {
      MountFactory.Data7.ensureSourceClassInitialized();
      Matrix4f var7 = this.A[var1].set(this.J);
      Vector3f var8 = var3.grip() != null ? var3.grip() : new Vector3f(0.0F, 0.0F, 0.0F);
      float var9 = this.H;
      float var10 = var1 == 13 ? var8.y : -var8.y;
      float var11 = var1 == 13 ? var8.z : -var8.z;
      float var12 = (float)Math.cos(var9);
      float var13 = (float)Math.sin(var9);
      float var14 = var10 * var12 - var11 * var13 - var10;
      float var15 = var10 * var13 + var11 * var12 - var11;
      var7.translate(0.0F, var14, var15);
   }

   private void R(Matrix4f var1, MountFactory.Data7 var2, int var3, float var4, long var5, boolean var7, MountFactory.Data2 var8) {
      MountFactory.Data7.ensureSourceClassInitialized();
      MountFactory.Data6 var9 = var2.wheels()[var3];
      if (var9 != null) {
         Vector3f var10 = var9.center();
         Matrix4f var11 = this.A[1 + var3];
         Matrix4f var12 = this.A[5 + var3];
         long var13 = 8456049489909L;
         if (8456049489909L == 0L) {
            boolean var10000 = false;
         } else {
            int var18 = var13 < 0L ? -1 : 1;
         }

         if (var8 != null) {
            var11.set(var1)
               .translate(var8.axis().x * -this.L[var3], var8.axis().y * -this.L[var3], var8.axis().z * -this.L[var3])
               .translate(var10.x, var10.y, var10.z)
               .rotate(this.p[var3], var9.axle().x, var9.axle().y, var9.axle().z)
               .translate(-var10.x, -var10.y, -var10.z);
            var12.set(var1).translate(var8.axis().x * -this.L[var3], var8.axis().y * -this.L[var3], var8.axis().z * -this.L[var3]);
         } else {
            long var16 = 8456049489909L;
            if (8456049489909L == 0L) {
               boolean var19 = false;
            } else {
               int var20 = var16 < 0L ? -1 : 1;
            }

            var11.set(var1).translate(var10.x, var10.y + this.L[var3], var10.z);
            if (var4 != 0.0F) {
               var11.rotateY(var4);
            }

            var12.set(var11);
            var11.rotate(this.p[var3], var9.axle().x, var9.axle().y, var9.axle().z);
            var11.translate(-var10.x, -var10.y, -var10.z);
            var12.translate(-var10.x, -var10.y, -var10.z);
         }
      }
   }

   private Matrix4f A(Matrix4f var1, MountFactory.Data2 var2, float var3) {
      Vector3f var4 = var2.pivot();
      Vector3f var5 = var2.axis();
      return var1.translate(var4.x, var4.y, var4.z).rotate(var3, var5.x, var5.y, var5.z).translate(-var4.x, -var4.y, -var4.z);
   }

   public Vector3f bodyPoint(float var1, float var2, float var3, Vector3f var4) {
      return this.J.transformPosition(var1, var2, var3, var4);
   }

}
