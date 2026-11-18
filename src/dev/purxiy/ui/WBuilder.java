// Sourced By DevFFNightmare
package dev.purxiy.ui;

import dev.purxiy.duck.ScreenHook;
import java.util.List;
import net.minecraft.class_4587;
import net.minecraft.class_4588;
import net.minecraft.class_630;
import net.minecraft.class_7833;
import net.minecraft.class_4587.class_4665;
import net.minecraft.class_630.class_593;
import net.minecraft.class_630.class_618;
import net.minecraft.class_630.class_628;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public final class WBuilder {
   public static final float MAX = (float)Math.toRadians(140.0);
   private static final float EPSILON = 0.001F;
   
   private static Object owner;
   private static boolean active;
   private static float joint;
   private static float centreZ;
   private static float side;
   private static float cos;
   private static float sin;
   private static float tanHalf;
   private static final float[] A = new float[40];
   private static final float[] B = new float[40];
   private static final float[] P = new float[3];
   private static final float[] N = new float[3];
   private static final Vector3f NORMAL = new Vector3f();
   private static final Vector3f POSITION = new Vector3f();

   private WBuilder() {
   }

   public static boolean active() {
      return active;
   }

   public static void begin(Object var0, List<class_628> var1, float var2, float var3) {
      if (!var1.isEmpty() && !(var2 <= 0.001F)) {
         class_628 var4 = (class_628)var1.get(0);
         joint = (var4.field_3644 + var4.field_3647) / 2.0F;
         centreZ = (var4.field_3643 + var4.field_3646) / 2.0F;
         float var5 = Math.min(MAX, var2);
         side = var3 < 0.0F ? -1.0F : 1.0F;
         cos = (float)Math.cos(var5);
         sin = (float)Math.sin(var5);
         tanHalf = (float)Math.tan(var5 / 2.0F);
         owner = var0;
         active = true;
      }
   }

   public static void retarget(float var0) {
      if (active) {
         joint = var0;
      }
   }

   public static void end(Object var0) {
      if (owner == var0) {
         owner = null;
         active = false;
      }
   }

   public static void reset() {
      owner = null;
      active = false;
   }

   public static void map(float var0, float var1, float var2, float[] var3) {
      float var4 = (var2 - centreZ) * side;
      float var5 = var1 - joint;
      float var6 = -var4 * tanHalf;
      float var7 = var4;
      float var8;
      if (Math.abs(var5) < 0.001F) {
         var8 = var6;
      } else if (var5 < 0.0F) {
         var8 = var5 > var6 ? var6 : var5;
      } else if (var5 < -var6) {
         var8 = var6;
      } else {
         var7 = var4 * cos + var5 * sin;
         var8 = -var4 * sin + var5 * cos;
      }

      var3[0] = var0;
      var3[1] = joint + var8;
      var3[2] = centreZ + var7 * side;
   }

   public static boolean lower(float var0) {
      return var0 > joint + 0.001F;
   }

   public static void turnNormal(float var0, float var1, float var2, float[] var3) {
      float var4 = var2 * side;
      float var5 = var4 * cos + var1 * sin;
      float var6 = -var4 * sin + var1 * cos;
      var3[0] = var0;
      var3[1] = var6;
      var3[2] = var5 * side;
   }

   public static void compile(List<class_628> var0, class_4665 var1, class_4588 var2, int var3, int var4, int var5) {
      Matrix4f var6 = var1.method_23761();

      for (class_628 var8 : var0) {
         for (class_593 var12 : var8.field_3649) {
            class_618[] var13 = var12.comp_3184();
            int var14 = var13.length;
            float var15 = Float.MAX_VALUE;
            float var16 = -Float.MAX_VALUE;

            for (int var17 = 0; var17 < var14; var17++) {
               class_618 var18 = var13[var17];
               A[var17 * 5] = var18.comp_4804();
               A[var17 * 5 + 1] = var18.comp_4805();
               A[var17 * 5 + 2] = var18.comp_4806();
               A[var17 * 5 + 3] = var18.comp_3187();
               A[var17 * 5 + 4] = var18.comp_3188();
               var15 = Math.min(var15, var18.comp_4805());
               var16 = Math.max(var16, var18.comp_4805());
            }

            float var22 = var12.comp_3185().x();
            float var23 = var12.comp_3185().y();
            float var19 = var12.comp_3185().z();
            if (var15 < joint - 0.001F && var16 > joint + 0.001F) {
               int var20 = clip(A, var14, B, true);
               emit(B, var20, false, var22, var23, var19, var6, var1, var2, var3, var4, var5);
               int var21 = clip(A, var14, B, false);
               emit(B, var21, true, var22, var23, var19, var6, var1, var2, var3, var4, var5);
            } else {
               emit(A, var14, lower((var15 + var16) / 2.0F), var22, var23, var19, var6, var1, var2, var3, var4, var5);
            }
         }
      }
   }

   private static int clip(float[] var0, int var1, float[] var2, boolean var3) {
      int var4 = 0;

      for (int var5 = 0; var5 < var1; var5++) {
         int var6 = (var5 + 1) % var1;
         float var7 = var0[var5 * 5 + 1] - joint;
         float var8 = var0[var6 * 5 + 1] - joint;
         boolean var9 = var3 ? var7 <= 0.001F : var7 >= -0.001F;
         boolean var10 = var3 ? var8 <= 0.001F : var8 >= -0.001F;
         if (var9) {
            System.arraycopy(var0, var5 * 5, var2, var4 * 5, 5);
            var4++;
         }

         if (var9 != var10 && Math.abs(var7 - var8) > 0.001F) {
            float var11 = var7 / (var7 - var8);

            for (int var12 = 0; var12 < 5; var12++) {
               var2[var4 * 5 + var12] = var0[var5 * 5 + var12] + (var0[var6 * 5 + var12] - var0[var5 * 5 + var12]) * var11;
            }

            var2[var4 * 5 + 1] = joint;
            var4++;
         }
      }

      return var4;
   }

   private static void emit(
      float[] var0, int var1, boolean var2, float var3, float var4, float var5, Matrix4f var6, class_4665 var7, class_4588 var8, int var9, int var10, int var11
   ) {
      if (var1 >= 3) {
         if (var2) {
            turnNormal(var3, var4, var5, N);
            var7.method_56820(N[0], N[1], N[2], NORMAL);
         } else {
            var7.method_56820(var3, var4, var5, NORMAL);
         }

         float var12 = NORMAL.x();
         float var13 = NORMAL.y();
         float var14 = NORMAL.z();

         for (int var15 = 0; var15 < 4; var15++) {
            int var16 = Math.min(var15, var1 - 1);
            map(var0[var16 * 5], var0[var16 * 5 + 1], var0[var16 * 5 + 2], P);
            var6.transformPosition(P[0] / 16.0F, P[1] / 16.0F, P[2] / 16.0F, POSITION);
            var8.method_23919(POSITION.x(), POSITION.y(), POSITION.z(), var11, var0[var16 * 5 + 3], var0[var16 * 5 + 4], var10, var9, var12, var13, var14);
         }
      }
   }

   public static void hand(class_630 var0, List<class_628> var1, class_4587 var2) {
      ScreenHook var3 = (ScreenHook)(Object)(var0);
      float var4 = Math.min(MAX, var3.purxiy$bend());
      if (!(var4 <= 0.001F) && !var1.isEmpty()) {
         class_628 var5 = (class_628)var1.get(0);
         float var6 = (var5.field_3644 + var5.field_3647) / 2.0F / 16.0F;
         float var7 = (var5.field_3643 + var5.field_3646) / 2.0F / 16.0F;
         float var8 = var3.purxiy$bendDirection() < 0.0F ? -1.0F : 1.0F;
         var2.method_46416(0.0F, var6, var7);
         var2.method_22907(class_7833.field_40714.rotation(var8 * var4));
         var2.method_46416(0.0F, -var6, -var7);
      }
   }
}
