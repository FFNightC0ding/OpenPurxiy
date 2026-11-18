// Sourced By DevFFNightmare
package dev.purxiy.modules.afterglow;

import net.minecraft.class_1309;

import dev.purxiy.loader.fabric.ModuleDescriptor;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_1109;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_5819;
import net.minecraft.class_746;

final class AfterglowFactory {
   private static final class_5819 y = class_5819.method_43047();
   private final Supplier<List<AfterglowFactory.Data>> N;
   private final Supplier<Float> X;
   final List<AfterglowFactory.Data2> Y;
   
   private final Consumer<class_746> S;
   private final Supplier<String> g;



   private AfterglowFactory(
      Supplier<List<AfterglowFactory.Data>> var1,
      Supplier<Float> var2,
      List<AfterglowFactory.Data2> var3,
       Consumer<class_746> var4,
      Supplier<String> var5
   ) {
      this.N = var1;
      this.X = var2;
      this.Y = var3;
      this.S = var4;
      this.g = var5;
   }

   List<AfterglowFactory.Data> timeline() {
      return this.N.get();
   }

   float length() {
      return this.X.get();
   }

   
   String note() {
      return this.g.get();
   }

   void closed(class_746 var1) {
      if (this.S != null) {
         this.S.accept(var1);
      }
   }

   private static void U(long var0) {
      if (!AfterglowSupport4.moved()) {
         AfterglowSupport4.overlook(26.0F, 1.55F);
      }
   }

   private static void K(class_746 var0, class_3414 var1, float var2) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1, class_3419.field_15248, var2, 0.95F + y.method_43057() * 0.1F, y, var0.method_23317(), var0.method_23318(), var0.method_23321()
            )
         );
   }

   private static class_243 Z(class_746 var0, double var1, double var3) {
      class_243 var5 = AfterglowSupport4.position();
      double var6 = var0.method_23317() - var5.field_1352;
      double var8 = var0.method_23321() - var5.field_1350;
      double var10 = Math.max(0.001, Math.sqrt(var6 * var6 + var8 * var8));
      var6 /= var10;
      var8 /= var10;
      return new class_243(-var8 * var1 + var6 * var3, 0.0, var6 * var1 + var8 * var3);
   }

   private static void T(class_746 var0, class_3414 var1, float var2, float var3) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1,
               class_3419.field_15248,
               var2,
               var3 + y.method_43057() * 0.1F,
               y,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() + 0.3,
               var0.method_23321()
            )
         );
   }

   private static AfterglowFactory P(long var0) {
      Consumer var2 = var0x -> {
         hit((class_746)(var0x), true);
         AfterglowRegistry.preview((class_1309)(var0x));
      };
      Consumer var3 = var0x -> {};
      Supplier var4 = () -> List.of(
         new AfterglowFactory.Data(0.0F, "Fall", var2),
         new AfterglowFactory.Data(AfterglowRegistry.previewGone() * 0.45F, "Light", var3),
         new AfterglowFactory.Data(AfterglowRegistry.previewGone(), "Return", var3)
      );
      return new AfterglowFactory(
         var4,
         () -> AfterglowRegistry.previewLength() + 0.3F,
         List.of(),
         AfterglowRegistry::previewEnd,
         () -> AfterglowRegistry.previewReady() ? null : "With a shader pack on, the fallen fall as in vanilla"
      );
   }

   static class_243 from(class_746 var0) {
      class_243 var1 = AfterglowSupport4.position();
      return new class_243(var1.field_1352, var0.method_23318() + var0.method_17682() * 0.7, var1.field_1350);
   }

   static void hit(class_746 var0, boolean var1) {
      var0.field_6254 = 10;
      var0.field_6235 = 10;
      class_3414 var2 = var1 ? class_3417.field_15016 : class_3417.field_14840;
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var2,
               class_3419.field_15248,
               0.5F,
               0.95F + y.method_43057() * 0.1F,
               y,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() * 0.6,
               var0.method_23321()
            )
         );
   }

   static AfterglowFactory of(ModuleDescriptor var0) {
      long var1 = 48812764855850L;
      long var3 = var1 ^ 130265149268210L;
      return P(var3);
   }

record Data(float P, String C, Consumer<class_746> E) {
      public float at() {
         return this.P;
      }

      public String label() {
         return this.C;
      }

      public Consumer<class_746> action() {
         return this.E;
      }

      public float floatState21() {
         return this.P;
      }

      public String stringState15() {
         return this.C;
      }

      public Consumer<class_746> consumerState3() {
         return this.E;
      }
   }

   record Data2(String G, String q, Consumer<class_746> m) {
      public String label() {
         return this.G;
      }

      public String icon() {
         return this.q;
      }

      public Consumer<class_746> action() {
         return this.m;
      }

      public String stringState16() {
         return this.G;
      }

      public String stringState17() {
         return this.q;
      }

      public Consumer<class_746> consumerState4() {
         return this.m;
      }
   }
}
