// Sourced By DevFFNightmare
package dev.purxiy.loader.fabric;

import net.minecraft.class_1144;
import net.minecraft.class_2561;
import net.minecraft.class_4185;
import net.minecraft.class_7919;
import net.minecraft.class_4185.class_12231;
import net.minecraft.class_4185.class_4241;
import net.minecraft.class_4185.class_7840;
import net.minecraft.class_4185.class_7841;

public final class ModuleClick {
   private ModuleClick() {
   }

   public static class_7840 builder(class_2561 var0, class_4241 var1) {
      return new ModuleClick.TypeV(var0, var1);
   }

   private static final class TypeP extends class_12231 {
      TypeP(int var1, int var2, int var3, int var4, class_2561 var5, class_4241 var6, class_7841 var7) {
         super(var1, var2, var3, var4, var5, var6, var7 == null ? field_40754 : var7);
      }

      @Override
      public void method_25354(class_1144 var1) {
         if (UiAudioPolicy.active()) {
            UiAudioPolicy.play("CLICK");
         } else {
            super.method_25354(var1);
         }
      }
   }

   private static final class TypeV extends class_7840 {
      private final class_2561 label;
      private final class_4241 action;
      private int x;
      private int y;
      private int w = 150;
      private int h = 20;
      private class_7919 tip;
      private class_7841 narration;

      TypeV(class_2561 var1, class_4241 var2) {
         super(var1, var2);
         this.label = var1;
         this.action = var2;
      }

      @Override
      public class_7840 method_46433(int var1, int var2) {
         this.x = var1;
         this.y = var2;
         return this;
      }

      @Override
      public class_7840 method_46432(int var1) {
         this.w = var1;
         return this;
      }

      @Override
      public class_7840 method_46437(int var1, int var2) {
         this.w = var1;
         this.h = var2;
         return this;
      }

      @Override
      public class_7840 method_46434(int var1, int var2, int var3, int var4) {
         this.x = var1;
         this.y = var2;
         this.w = var3;
         this.h = var4;
         return this;
      }

      @Override
      public class_7840 method_46436(class_7919 var1) {
         this.tip = var1;
         return this;
      }

      @Override
      public class_7840 method_46435(class_7841 var1) {
         this.narration = var1;
         return this;
      }

      @Override
      public class_4185 method_46431() {
         ModuleClick.TypeP var1 = new ModuleClick.TypeP(this.x, this.y, this.w, this.h, this.label, this.action, this.narration);
         if (this.tip != null) {
            var1.method_47400(this.tip);
         }

         return var1;
      }
   }
}
