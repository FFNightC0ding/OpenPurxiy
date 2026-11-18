// Sourced By DevFFNightmare
package dev.purxiy.modules.hand;

import com.mojang.blaze3d.vertex.VertexFormat.class_5596;
import dev.purxiy.loader.fabric.mixin.ModelPartAccessor;
import dev.purxiy.loader.fabric.mixin.RenderTypeAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.class_1058;
import net.minecraft.class_1268;
import net.minecraft.class_1921;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_5611;
import net.minecraft.class_630;
import net.minecraft.class_777;
import net.minecraft.class_11659.class_11660;
import net.minecraft.class_12247.class_12337;
import net.minecraft.class_2350.class_2351;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_630.class_593;
import net.minecraft.class_630.class_618;
import net.minecraft.class_630.class_628;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;
import org.joml.Vector4f;

final class HandMovementRenderer {
   static final int X = 8;
   private float[] S = new float[8192];
   private int[] b = new int[1024];
   private boolean[] J = new boolean[1024];
   private int t;
   private final List<HandMovementRenderer.Data> L = new ArrayList<>();
   
   private class_1268 n;
   private boolean x = true;
   
   private class_12337 M;
   
   private class_1268 C;
   private boolean O;
   private int W;
   private final Vector3f A = new Vector3f();
   private final Vector3f d = new Vector3f();
   private final HandMovementRenderer.TypeV D = new HandMovementRenderer.TypeV();
   private float[] P = new float[2048];
   private static final String a = "Sampler0";
   private static final long[] c = new long[]{
      -1073866473287743562L,
      -6006324310693627624L,
      2625727353027472068L,
      -7141801112892502050L,
      7963580474590791634L,
      -3235932078023191833L,
      4953411992137488625L,
      -1724515533746734323L
   };


   void reset() {
      this.t = 0;
      this.L.clear();
      this.M = null;
      this.C = null;
      this.n = null;
      this.x = true;
   }

   void hand( class_1268 var1) {
      long var2 = 54943582749441L;
      long var4 = var2 ^ 21360327084138L;
      this.U(var4);
      this.n = var1;
      this.x = true;
   }

   void sweeps(boolean var1) {
      long var2 = 13857994376138L;
      long var4 = var2 ^ 50643215304865L;
      if (var1 != this.x) {
         this.U(var4);
         this.x = var1;
      }
   }

   List<HandMovementRenderer.Data> runs() {
      long var1 = 64790215145758L;
      long var3 = var1 ^ 26907966763637L;
      this.U(var3);
      return this.L;
   }

   float[] data() {
      return this.S;
   }

   int[] colors() {
      return this.b;
   }

   int vertices() {
      return this.t;
   }

   boolean has(class_1268 var1) {
      for (HandMovementRenderer.Data var3 : this.runs()) {
         if (var3.j == var1 && var3.v) {
            return true;
         }
      }

      return false;
   }

   void quads(class_4665 var1, List<class_777> var2, int[] var3, class_1921 var4) {
      long var5 = 20047085073957L;
      long var7 = var5 ^ 101578875050682L;
      long var9 = var5 ^ 68984182314032L;
      long var11 = var5 ^ 96225744790306L;
      if (this.n != null && !var2.isEmpty() && this.F(var4, var7)) {
         Matrix4f var13 = var1.method_23761();

         for (class_777 var15 : var2) {
            int var16 = var15.comp_3722();
            int var17 = a(HandMovementFactory.of(var15.comp_3724()), var11, var16 >= 0 && var16 < var3.length ? var3[var16] : -1);
            var1.method_56821(var15.comp_3723().method_68072(), this.d);
            boolean var18 = var15.comp_3723().method_10166() != class_2351.field_11051;

            for (int var19 = 0; var19 < 4; var19++) {
               Vector3fc var20 = var15.method_76648(var19);
               var13.transformPosition(var20.x(), var20.y(), var20.z(), this.A);
               long var21 = var15.method_76649(var19);
               this.v(this.A, class_5611.method_76641(var21), class_5611.method_76642(var21), var9, var17, this.d, var18);
            }
         }
      }
   }

   void part(class_630 var1, class_4587 var2, class_1921 var3,  class_1058 var4, int var5) {
      long var6 = 100641982567702L;
      long var8 = var6 ^ 137787743711626L;
      long var10 = var6 ^ 23990449944969L;
      long var12 = var6 ^ 33223042826257L;
      if (this.n != null && this.F(var3, var10)) {
         this.s(var1, var2, var4, var8, a(var4 != null ? HandMovementFactory.of(var4) : 0, var12, var5));
      }
   }

   void shape(class_4665 var1, class_1921 var2, class_11660 var3) {
      long var4 = 64731092497923L;
      long var6 = var4 ^ 128120458304156L;
      if (this.n != null && var2.method_23033() == class_5596.field_27382 && this.F(var2, var6)) {
         var3.render(var1, this.D);
         this.D.flush();
      }
   }

   private static int a(int var0, long var1, int var3) {
      SwingStyle.ensureSourceClassInitialized();
      long var4 = 76690369041670L;
      if (76690369041670L == 0L) {
         boolean var10000 = false;
      } else {
         int var7 = var4 < 0L ? -1 : 1;
      }

      return var0 == 0 ? var3 & 16777215 | -2147483648 : HandMovementFactory.multiply(0xFF000000 | var0, var3) | 0xFF000000;
   }

   private void s(class_630 var1, class_4587 var2,  class_1058 var3, long var4, int var6) {
      long var7 = var4 ^ 0L;
      long var9 = var4 ^ 11405534594697L;
      SwingStyle.ensureSourceClassInitialized();
      if (var1.field_3665) {
         ModelPartAccessor var11 = (ModelPartAccessor)(Object)(var1);
         if (var4 > 0L) {
            var2.method_22903();
            var1.method_22703(var2);
         }

         if (!var1.field_38456) {
            class_4665 var12 = var2.method_23760();
            Matrix4f var13 = var12.method_23761();
            Iterator var14 = var11.purxiy$cubes().iterator();

            while (var14.hasNext()) {
               Object var15 = var14;
               if (var4 >= 1L) {
                  var15 = var14.next();
               }

               class_628 var16 = (class_628)var15;
               class_593[] var17 = var16.field_3649;
               int var18 = var17.length;

               label84:
               while (true) {
                  int var19 = 0;

                  label82:
                  while (var19 < var18) {
                     class_593 var20 = var17[var19];
                     var12.method_56821(var20.comp_3185(), this.d);
                     class_618[] var21 = var20.comp_3184();
                     if (var4 < 1L) {
                        continue label84;
                     }

                     for (class_618 var25 : var21) {
                        var13.transformPosition(var25.method_74250(), var25.method_74251(), var25.method_74252(), this.A);
                        float var26 = var25.comp_3187();
                        if (var4 < 0L) {
                           continue label82;
                        }

                        float var27 = var25.comp_3188();
                        if (var4 > 0L) {
                           label73: {
                              class_1058 var28 = var3;
                              if (var4 >= 1L) {
                                 if (var3 == null) {
                                    break label73;
                                 }

                                 var26 = var3.method_4580(var26);
                                 var28 = var3;
                              }

                              var27 = var28.method_4570(var27);
                           }

                           this.v(this.A, var26, var27, var9, var6, this.d, true);
                        }
                     }

                     if (var4 >= 0L) {
                        var19++;
                     }
                  }

                  if (var4 > 1L) {
                  }
                  break;
               }
            }
         }

         Iterator var29 = var11.purxiy$children().values().iterator();

         while (true) {
            Object var30;
            if (var29.hasNext()) {
               var30 = var29.next();
            } else {
               var30 = var2;
               if (var4 > 0L) {
                  var2.method_22909();
                  return;
               }
            }

            class_630 var31 = (class_630)var30;
            if (var4 >= 1L) {
               this.s(var31, var2, var3, var7, var6);
            }
         }
      }
   }

   private boolean F(class_1921 var1, long var2) {
      long var4 = 38027003874155L;
      SwingStyle.ensureSourceClassInitialized();
      class_12337 var6 = ((RenderTypeAccessor)var1).purxiy$setup().method_75926().get("Sampler0");
      if (var6 == null) {
         return false;
      } else {
         if (this.M != null && this.C == this.n && this.O == this.x) {
            long var7 = 86145226632351L;
            if (86145226632351L == 0L) {
               boolean var10000 = false;
            } else {
               int var10 = var7 < 0L ? -1 : 1;
            }

            if (this.M.comp_5226() == var6.comp_5226()) {
               return true;
            }
         }

         this.U(var4);
         this.M = var6;
         this.C = this.n;
         this.O = this.x;
         this.W = this.t;
         return true;
      }
   }

   private void U(long var1) {
      SwingStyle.ensureSourceClassInitialized();
      if (this.M != null && this.C != null) {
         long var3 = 38027003874154L;
         if (38027003874154L == 0L) {
            boolean var10000 = false;
         } else {
            int var6 = var3 < 0L ? -1 : 1;
         }

         if (this.t > this.W) {
            this.L.add(new HandMovementRenderer.Data(this.C, this.M, this.W, this.t - this.W, this.O));
         }
      }

      this.M = null;
      this.C = null;
   }

   private void v(Vector3f var1, float var2, float var3, long var4, int var6, Vector3f var7, boolean var8) {
      SwingStyle.ensureSourceClassInitialized();
      if (this.t == this.b.length) {
         this.b = Arrays.copyOf(this.b, this.b.length * 2);
         this.S = Arrays.copyOf(this.S, this.S.length * 2);
         this.J = Arrays.copyOf(this.J, this.J.length * 2);
      }

      this.J[this.t] = var8;
      int var9 = this.t * 8;
      this.S[var9] = var1.x;
      this.S[var9 + 1] = var1.y;
      this.S[var9 + 2] = var1.z;
      this.S[var9 + 3] = var2;
      this.S[var9 + 4] = var3;
      this.S[var9 + 5] = var7.x;
      this.S[var9 + 6] = var7.y;
      this.S[var9 + 7] = var7.z;
      this.b[this.t] = var6;
      this.t++;
   }

   int hull(class_1268 var1, Matrix4f var2, float var3, float var4, float[] var5) {
      long var6 = 64687827797403L;
      long var8 = var6 ^ 54402798777133L;
      int var10 = 0;
      float[] var11 = this.M(this.t * 2, var8);
      Vector4f var12 = new Vector4f();

      for (HandMovementRenderer.Data var14 : this.runs()) {
         if (var14.j == var1) {
            for (int var15 = var14.K; var15 < var14.K + var14.E; var15++) {
               if (this.J[var15]) {
                  int var16 = var15 * 8;
                  var2.transform(var12.set(this.S[var16], this.S[var16 + 1], this.S[var16 + 2], 1.0F));
                  if (!(var12.w <= 1.0E-4F)) {
                     var11[var10 * 2] = (var12.x / var12.w * 0.5F + 0.5F) * var3;
                     var11[var10 * 2 + 1] = (0.5F - var12.y / var12.w * 0.5F) * var4;
                     var10++;
                  }
               }
            }
         }
      }

      return var10 < 3 ? 0 : convexHull(var11, var10, var5);
   }

   private float[] M(int var1, long var2) {
      SwingStyle.ensureSourceClassInitialized();
      if (this.P.length < var1) {
         this.P = new float[Math.max(var1, this.P.length * 2)];
      }

      return this.P;
   }

   static int convexHull(float[] var0, int var1, float[] var2) {
      Integer[] var3 = new Integer[var1];

      for (int var4 = 0; var4 < var1; var4++) {
         var3[var4] = var4;
      }

      Arrays.sort(var3, (var1x, var2x) -> {
         SwingStyle.ensureSourceClassInitialized();
         int var3x = var1x;
         long var4x = 84836490714119L;
         if (84836490714119L == 0L) {
            boolean var10000 = false;
         } else {
            int var7x = var4x < 0L ? -1 : 1;
         }

         return var0[var3x * 2] != var0[var2x * 2] ? Float.compare(var0[var1x * 2], var0[var2x * 2]) : Float.compare(var0[var1x * 2 + 1], var0[var2x * 2 + 1]);
      });
      int[] var9 = new int[var1 * 2];
      int var5 = 0;

      for (int var6 = 0; var6 < var1; var6++) {
         int var7 = var3[var6];

         while (var5 >= 2 && Z(var0, var9[var5 - 2], var9[var5 - 1], var7) <= 0.0F) {
            var5--;
         }

         var9[var5++] = var7;
      }

      int var10 = var1 - 2;

      for (int var12 = var5 + 1; var10 >= 0; var10--) {
         int var8 = var3[var10];

         while (var5 >= var12 && Z(var0, var9[var5 - 2], var9[var5 - 1], var8) <= 0.0F) {
            var5--;
         }

         var9[var5++] = var8;
      }

      var10 = Math.min(var5 - 1, var2.length / 2);

      for (int var13 = 0; var13 < var10; var13++) {
         var2[var13 * 2] = var0[var9[var13] * 2];
         var2[var13 * 2 + 1] = var0[var9[var13] * 2 + 1];
      }

      return var10;
   }

   private static float Z(float[] var0, int var1, int var2, int var3) {
      return (var0[var2 * 2] - var0[var1 * 2]) * (var0[var3 * 2 + 1] - var0[var1 * 2 + 1])
         - (var0[var2 * 2 + 1] - var0[var1 * 2 + 1]) * (var0[var3 * 2] - var0[var1 * 2]);
   }

   boolean bounds(class_1268 var1, float[] var2) {
      boolean var3 = false;
      var2[0] = var2[1] = var2[2] = Float.POSITIVE_INFINITY;
      var2[3] = var2[4] = var2[5] = Float.NEGATIVE_INFINITY;

      for (HandMovementRenderer.Data var5 : this.runs()) {
         if (var5.j == var1 && var5.v) {
            for (int var6 = var5.K; var6 < var5.K + var5.E; var6++) {
               int var7 = var6 * 8;

               for (int var8 = 0; var8 < 3; var8++) {
                  var2[var8] = Math.min(var2[var8], this.S[var7 + var8]);
                  var2[var8 + 3] = Math.max(var2[var8 + 3], this.S[var7 + var8]);
               }

               var3 = true;
            }
         }
      }

      return var3;
   }

record Data(class_1268 j, class_12337 w, int K, int E, boolean v) {
      public class_1268 hand() {
         return this.j;
      }

      public class_12337 texture() {
         return this.w;
      }

      public int first() {
         return this.K;
      }

      public int count() {
         return this.E;
      }

      public boolean sweeps() {
         return this.v;
      }

      public class_1268 minecraftObjectState3() {
         return this.j;
      }

      public class_12337 class_12247_class_12337State4() {
         return this.w;
      }

      public int integerState21() {
         return this.K;
      }

      public int integerState40() {
         return this.E;
      }

      public boolean booleanState47() {
         return this.v;
      }
   }

   private final class TypeV implements class_4588 {
      private boolean F;
      private float z;
      private float M;
      private float r;
      private float s;
      private float t;
      private float c;
      private float x = 1.0F;
      private float q;
      private int k = -1;
      private final Vector3f S = new Vector3f();
      private final Vector3f h = new Vector3f();

      @Override
      public class_4588 method_22912(float var1, float var2, float var3) {
         this.flush();
         this.F = true;
         this.z = var1;
         this.M = var2;
         this.r = var3;
         this.s = this.t = this.c = this.q = 0.0F;
         this.x = 1.0F;
         this.k = -1;
         return this;
      }

      @Override
      public class_4588 method_1336(int var1, int var2, int var3, int var4) {
         this.k = var4 << 24 | var1 << 16 | var2 << 8 | var3;
         return this;
      }

      @Override
      public class_4588 method_39415(int var1) {
         this.k = var1;
         return this;
      }

      @Override
      public class_4588 method_22913(float var1, float var2) {
         this.s = var1;
         this.t = var2;
         return this;
      }

      @Override
      public class_4588 method_60796(int var1, int var2) {
         return this;
      }

      @Override
      public class_4588 method_22921(int var1, int var2) {
         return this;
      }

      @Override
      public class_4588 method_22914(float var1, float var2, float var3) {
         this.c = var1;
         this.x = var2;
         this.q = var3;
         return this;
      }

      @Override
      public class_4588 method_75298(float var1) {
         return this;
      }

      void flush() {
         long var1 = 53977500086570L;
         long var3 = var1 ^ 32511551542079L;
         if (this.F) {
            this.F = false;
            HandMovementRenderer.this.v(
               this.S.set(this.z, this.M, this.r), this.s, this.t, var3, this.k & 16777215 | -2147483648, this.h.set(this.c, this.x, this.q), true
            );
         }
      }
   }
}
