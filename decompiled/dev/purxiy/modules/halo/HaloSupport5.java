package dev.purxiy.modules.halo;

import java.util.Locale;
import net.minecraft.class_10055;
import net.minecraft.class_4587;
import net.minecraft.class_591;
import org.joml.Matrix4f;
import org.joml.Vector3f;

final class HaloSupport5 {
   float N;
   float g;
   float p;
   final float[] m = new float[24];
   float f;
   float a = 1.0F;
   float w;
   float K;
   float M;
   float r = 1.0F;
   float J;
   float j = 1.0F;
   float o;
   float y = 0.0625F;
   float s = 4.5F;
   boolean n;
   boolean l;
   boolean c;
   boolean Z;
   boolean x;
   boolean W;
   private final float[] I = new float[7];
   private final Matrix4f q = new Matrix4f();
   private final Vector3f A = new Vector3f();
   private static final String b = "sources %s, box x %.1f..%.1f y %.1f..%.1f z %.1f..%.1f, half width %.1f";

   void measure(class_4587 var1, class_591 var2, class_10055 var3) {
      long var4 = 24270741629786L;
      long var6 = var4 ^ 115238558107661L;
      int var8 = (int)((var4 ^ 115238558107661L) >>> 32);
      int var9 = (int)((var4 ^ 115238558107661L) << 32 >>> 40);
      int var10 = (int)(var6 << 56 >>> 56);
      Matrix4f var11 = var1.method_23760().method_23761();
      var11.transformDirection(this.A.set(0.0F, -1.0F, 0.0F)).normalize();
      this.J = this.A.x;
      this.j = this.A.y;
      this.o = this.A.z;
      var1.method_22903();
      var2.method_63512().method_22703(var1);
      var2.field_3398.method_22703(var1);
      this.q.set(var1.method_23760().method_23761());
      var1.method_22909();
      HaloSupport4.bounds(var2, var3, this.I);
      this.u(this.q, var8, var9, this.I, (byte)var10, 0.0625F);
      this.n = var3.field_53455;
      this.l = var3.field_53411;
      this.c = var3.field_53403 > 0.5F;
      this.Z = var3.field_53460;
      this.x = var3.field_53449 > 0.0F;
      this.W = false;
   }

   void picture(Matrix4f var1, float[] var2) {
      long var3 = 38385159665727L;
      long var5 = var3 ^ 101381972335464L;
      int var7 = (int)((var3 ^ 101381972335464L) >>> 32);
      int var8 = (int)((var3 ^ 101381972335464L) << 32 >>> 40);
      int var9 = (int)(var5 << 56 >>> 56);
      this.J = 0.0F;
      this.j = 1.0F;
      this.o = 0.0F;
      this.u(var1, var7, var8, var2, (byte)var9, 1.0F);
      this.n = this.l = this.c = this.Z = this.x = this.W = false;
   }

   private void u(Matrix4f var1, int var2, int var3, float[] var4, byte var5, float var6) {
      var1.transformPosition(this.A.set(0.0F, -4.0F * var6, 0.0F));
      this.N = this.A.x;
      HaloDesign.ensureSourceClassInitialized();
      this.g = this.A.y;
      this.p = this.A.z;

      for (int var7 = 0; var7 < 8; var7++) {
         float var8 = var4[(var7 & 1) == 0 ? 0 : 3] * var6;
         float var9 = var4[(var7 & 2) == 0 ? 1 : 4] * var6;
         var1.transformPosition(this.A.set(var8, var9, var4[(var7 & 4) == 0 ? 2 : 5] * var6));
         this.m[var7 * 3] = this.A.x;
         this.m[var7 * 3 + 1] = this.A.y;
         this.m[var7 * 3 + 2] = this.A.z;
      }

      var1.transformDirection(this.A.set(0.0F, -1.0F, 0.0F)).normalize();
      this.f = this.A.x;
      this.a = this.A.y;
      this.w = this.A.z;
      var1.transformDirection(this.A.set(0.0F, 0.0F, -1.0F)).normalize();
      this.K = this.A.x;
      this.M = this.A.y;
      this.r = this.A.z;
      float var10 = var1.transformDirection(this.A.set(var6, 0.0F, 0.0F)).length();
      float var11 = var1.transformDirection(this.A.set(0.0F, 0.0F, var6)).length();
      this.y = (var10 + var11) * 0.5F;
      this.s = var4[6];
   }

   String describe() {
      float[] var1 = this.I;
      return String.format(
         Locale.ROOT,
         "sources %s, box x %.1f..%.1f y %.1f..%.1f z %.1f..%.1f, half width %.1f",
         Integer.toBinaryString(HaloSupport4.v),
         var1[0],
         var1[3],
         var1[1],
         var1[4],
         var1[2],
         var1[5],
         var1[6]
      );
   }

   float reach(float var1, float var2, float var3) {
      float var4 = 0.0F;

      for (int var5 = 0; var5 < 8; var5++) {
         float var6 = (this.m[var5 * 3] - this.N) * var1 + (this.m[var5 * 3 + 1] - this.g) * var2 + (this.m[var5 * 3 + 2] - this.p) * var3;
         var4 = Math.max(var4, var6);
      }

      return var4;
   }
}
