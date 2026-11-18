// Sourced By DevFFNightmare
package dev.purxiy.modules.place;

import net.minecraft.class_11791;
import net.minecraft.class_1920;
import net.minecraft.class_1937;
import net.minecraft.class_2338;
import net.minecraft.class_2586;
import net.minecraft.class_2680;
import net.minecraft.class_3610;
import net.minecraft.class_6539;

final class PlaceAnimationSupport2 extends class_11791 {
   PlaceAnimationSupport2(class_2338 var1, class_2680 var2, class_1920 var3) {
      this.field_62246 = var1;
      this.field_62245 = var1;
      this.field_62247 = var2;
      this.field_62249 = var3;
      if (var3 instanceof class_1937 var4) {
         this.field_62248 = var4.method_23753(var1);
      }
   }

   @Override
   public class_2680 method_8320(class_2338 var1) {
      return var1.equals(this.field_62246) ? this.field_62247 : this.field_62249.method_8320(var1);
   }

   @Override
   public class_3610 method_8316(class_2338 var1) {
      return this.method_8320(var1).method_26227();
   }

   @Override
   public int method_23752(class_2338 var1, class_6539 var2) {
      return this.field_62249.method_23752(var1, var2);
   }

   
   @Override
   public class_2586 method_8321(class_2338 var1) {
      return this.field_62249.method_8321(var1);
   }
}
