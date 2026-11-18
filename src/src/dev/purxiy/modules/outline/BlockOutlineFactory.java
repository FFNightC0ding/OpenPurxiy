// Sourced By DevFFNightmare
package dev.purxiy.modules.outline;

import java.util.Arrays;
import net.minecraft.class_238;
import net.minecraft.class_265;

final class BlockOutlineFactory {
   final float[] D;
   final float[] J;
   final float E;
   final float R;
   final float C;
   final float I;
   final float O;
   final float b;
   private static class_265 P;
   private static BlockOutlineFactory d;



   private BlockOutlineFactory(float[] var1, float[] var2, class_238 var3) {
      this.D = var1;
      this.J = var2;
      this.E = (float)var3.field_1323;
      this.R = (float)var3.field_1322;
      this.C = (float)var3.field_1321;
      this.I = (float)var3.field_1320;
      this.O = (float)var3.field_1325;
      this.b = (float)var3.field_1324;
   }

   static BlockOutlineFactory of(class_265 var0) {
      if (var0 == P) {
         return d;
      } else if (var0.method_1110()) {
         return null;
      } else {
         float[][] var1 = new float[][]{new float[72]};
         int[] var2 = new int[]{0};
         var0.method_1104((var2x, var4x, var6, var8, var10, var12) -> {
            long var14 = 57646513085777L;
            if (var2[0] + 6 > var1[0].length) {
               var1[0] = Arrays.copyOf(var1[0], var1[0].length * 2);
            }

            float[] var16 = var1[0];
            int var17 = var2[0];
            var16[var17] = (float)var2x;
            var16[var17 + 1] = (float)var4x;
            var16[var17 + 2] = (float)var6;
            var16[var17 + 3] = (float)var8;
            var16[var17 + 4] = (float)var10;
            var16[var17 + 5] = (float)var12;
            var2[0] += 6;
         });
         float[][] var3 = new float[][]{new float[12]};
         int[] var4 = new int[]{0};
         var0.method_1089((var2x, var4x, var6, var8, var10, var12) -> {
            if (var4[0] + 6 > var3[0].length) {
               var3[0] = Arrays.copyOf(var3[0], var3[0].length * 2);
            }

            float[] var14 = var3[0];
            int var15 = var4[0];
            var14[var15] = (float)var2x;
            var14[var15 + 1] = (float)var4x;
            var14[var15 + 2] = (float)var6;
            var14[var15 + 3] = (float)var8;
            var14[var15 + 4] = (float)var10;
            var14[var15 + 5] = (float)var12;
            var4[0] += 6;
         });
         BlockOutlineFactory var5 = new BlockOutlineFactory(Arrays.copyOf(var1[0], var2[0]), Arrays.copyOf(var3[0], var4[0]), var0.method_1107());
         if (d != null && d.sameAs(var5)) {
            var5 = d;
         }

         P = var0;
         d = var5;
         return var5;
      }
   }

   int edgeCount() {
      return this.D.length / 6;
   }

   int boxCount() {
      return this.J.length / 6;
   }

   float sizeX() {
      return this.I - this.E;
   }

   float sizeY() {
      return this.O - this.R;
   }

   float sizeZ() {
      return this.b - this.C;
   }

   boolean sameAs(BlockOutlineFactory var1) {
      return var1 == this || var1 != null && Arrays.equals(this.D, var1.D) && Arrays.equals(this.J, var1.J);
   }

}
