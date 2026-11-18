// Sourced By DevFFNightmare
package dev.purxiy.modules.roles;

import dev.purxiy.render.Canvas;
import java.nio.charset.StandardCharsets;
import net.minecraft.class_11719;
import net.minecraft.class_2583;
import net.minecraft.class_2960;
import net.minecraft.class_11719.class_11721;

public final class RolesSupport2 {
   public static class_11719 S;
   private static class_2960 r;
   public static int L = 128249;
   public static int V = 57345;
   public static final float l = 8.0F;
   private static int[][] O;

   private RolesSupport2() {
   }

   public static boolean drawn(class_2583 var0, int var1) {
      if (var1 != 128249) {
         return false;
      } else {
         class_11719 var2 = var0.method_27708();
         return var2.equals(class_11719.field_61972) || var2 instanceof class_11721 var3 && var3.comp_4590().equals(r);
      }
   }

   public static void camera(Canvas var0, float var1, float var2, float var3, int var4) {
      float var5 = 0.5F * var3;
      float var6 = var2 - 7.0F * var3;

      for (int[] var10 : O) {
         var0.rect(var1 + (var10[0] - 1) * var5, var6 + var10[2] * var5, (var10[1] - var10[0] + 1) * var5, (var10[3] - var10[2] + 1) * var5, var4);
      }
   }

   public static void cameraShadow(Canvas var0, float var1, float var2, float var3, int var4) {
      float var5 = 0.5F * var3;
      float var6 = var2 - 7.0F * var3;
      var0.shadow(var1, var6 + 4.0F * var5, 12.0F * var5, 8.0F * var5, var5, 3.0F * var5, var4);
   }

}
