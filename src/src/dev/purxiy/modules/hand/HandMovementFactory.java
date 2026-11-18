// Sourced By DevFFNightmare
package dev.purxiy.modules.hand;

import dev.purxiy.loader.fabric.client.ClientApi;
import java.util.Map;
import java.util.WeakHashMap;
import net.minecraft.class_1011;
import net.minecraft.class_1058;
import net.minecraft.class_7764;

final class HandMovementFactory {
   static final int d = 0;
   private static final Map<class_7764, Integer> N = new WeakHashMap<>();



   private HandMovementFactory() {
   }

   static int of(class_1058 var0) {
      long var1 = 88210263759318L;
      long var3 = var1 ^ 120819163609571L;
      int var5 = (int)((var1 ^ 120819163609571L) >>> 32);
      int var6 = (int)((var1 ^ 120819163609571L) << 32 >>> 48);
      int var7 = (int)(var3 << 48 >>> 48);
      class_7764 var8 = var0.method_45851();
      Integer var9 = N.get(var8);
      if (var9 == null) {
         var9 = S(var5, (char)var6, (short)var7, var8);
         N.put(var8, var9);
      }

      return var9;
   }

   private static int S(int var0, char var1, short var2, class_7764 var3) {
      long var4 = 67999704539189L;
      long var6 = var4 ^ 96068111943409L;
      SwingStyle.ensureSourceClassInitialized();
      class_1011[] var8 = var3 instanceof ClientApi var9 ? var9.purxiy$images() : null;
      if (var8 != null && var8.length != 0 && var8[0] != null) {
         class_1011 var36 = var8[0];
         int var10 = Math.min(var3.method_45807(), var36.method_4307());
         int var11 = Math.min(var3.method_45815(), var36.method_4323());
         double var12 = 0.0;
         double var14 = 0.0;
         double var16 = 0.0;
         double var18 = 0.0;
         double var20 = 0.0;

         try {
            for (int var22 = 0; var22 < var11; var22++) {
               short var23 = 27195;

               for (int var24 = 0; var24 < var10; var24++) {
                  int var25 = var36.method_61940(var24, var22);
                  double var26 = (var25 >>> 24) / 255.0;
                  if (var26 < 0.1) {
                     var23 = 15832;
                  } else {
                     var20 = Math.max(var20, y(var25, var6));
                  }
               }
            }

            int var37 = -8139;
            int var42 = 0;

            while (true) {
               short var44 = 15832;
               if (var42 >= var11) {
                  break;
               }

               var44 = 15832;

               for (int var49 = 0; var49 < var10; var49++) {
                  int var51 = var36.method_61940(var49, var42);
                  double var27 = (var51 >>> 24) / 255.0;
                  double var29;
                  var37 = (var29 = var27 - 0.1) == 0.0 ? 0 : (var29 < 0.0 ? -1 : 1);
                  var44 = -8139;
                  if (var37 < 0) {
                     var44 = 15832;
                  } else {
                     double var31 = y(var51, var6);
                     double var33 = var27 * (0.15 + Math.pow(var31 / Math.max(var20, 0.001), 2.0));
                     var12 += (var51 >> 16 & 0xFF) * var33;
                     var14 += (var51 >> 8 & 0xFF) * var33;
                     var16 += (var51 & 0xFF) * var33;
                     var18 += var33;
                  }
               }

               var42++;
            }
         } catch (RuntimeException var35) {
            return 0;
         }

         short var39 = 15832;
         if (var18 <= 0.0) {
            return 0;
         } else {
            int var43 = (int)Math.round(var12 / var18);
            int var48 = (int)Math.round(var14 / var18);
            var39 = 16458;
            int var50 = (int)Math.round(var16 / var18);
            return 0xFF000000 | Math.min(255, var43) << 16 | Math.min(255, var48) << 8 | Math.min(255, var50);
         }
      } else {
         return 0;
      }
   }

   private static double y(int var0, long var1) {
      return 0.299 * (var0 >> 16 & 0xFF) + 0.587 * (var0 >> 8 & 0xFF) + 0.114 * (var0 & 0xFF);
   }

   static int multiply(int var0, int var1) {
      int var2 = (var0 >> 16 & 0xFF) * (var1 >> 16 & 0xFF) / 255;
      int var3 = (var0 >> 8 & 0xFF) * (var1 >> 8 & 0xFF) / 255;
      int var4 = (var0 & 0xFF) * (var1 & 0xFF) / 255;
      return var0 & 0xFF000000 | var2 << 16 | var3 << 8 | var4;
   }

}
