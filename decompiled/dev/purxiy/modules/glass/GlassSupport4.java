package dev.purxiy.modules.glass;

import org.joml.Matrix4f;
import org.joml.Vector3f;

final class GlassSupport4 {
   boolean y;
   final Matrix4f E = new Matrix4f();
   final Matrix4f k = new Matrix4f();
   final Matrix4f X = new Matrix4f();
   final Vector3f n = new Vector3f(0.0F, 1.0F, 0.0F);
   final Vector3f q = new Vector3f(1.0F, 0.0F, 0.0F);
   final Vector3f v = new Vector3f();
   final Vector3f N = new Vector3f();
   final Vector3f B = new Vector3f(0.0F, 1.0F, 0.0F);
   float U;
   final float[] w = new float[]{1.0F, 1.0F, 1.0F};
   final float[] I = new float[3];
   final float[] e = new float[3];
   float Q;
   float o = 1.0F;
   float V;
   float b;
   float Y = -1.0F;
   final float[] f = new float[]{1.0F, 1.0F, 1.0F};
   float l;
   float Z;
   float m;
   float[] r = new float[8];
   int T;

   void look(float[] var1, float var2, float var3, float var4) {
      System.arraycopy(var1, 0, this.f, 0, 3);
      this.l = var2;
      this.Z = var3;
      this.m = var4;
   }
}
