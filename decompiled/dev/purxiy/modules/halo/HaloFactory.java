package dev.purxiy.modules.halo;

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

final class HaloFactory {
   private static final class_5819 O = class_5819.method_43047();
   private final Supplier<List<HaloFactory.Data>> T;
   private final Supplier<Float> C;
   final List<HaloFactory.Data2> K;
   private final Consumer<class_746> X;
   private final Supplier<String> y;
   private static final String[] b = new String[12];

   private HaloFactory(
      Supplier<List<HaloFactory.Data>> var1, Supplier<Float> var2, List<HaloFactory.Data2> var3, Consumer<class_746> var4, Supplier<String> var5
   ) {
      this.T = var1;
      this.C = var2;
      this.K = var3;
      this.X = var4;
      this.y = var5;
   }

   List<HaloFactory.Data> timeline() {
      return this.T.get();
   }

   float length() {
      return this.C.get();
   }

   String note() {
      return this.y.get();
   }

   void closed(class_746 var1) {
      if (this.X != null) {
         this.X.accept(var1);
      }
   }

   private static void D(long var0) {
      if (!HaloSupport9.moved()) {
         HaloSupport9.overlook(26.0F, 1.55F);
      }
   }

   private static void J(class_746 var0, class_3414 var1, float var2) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1, class_3419.field_15248, var2, 0.95F + O.method_43057() * 0.1F, O, var0.method_23317(), var0.method_23318(), var0.method_23321()
            )
         );
   }

   private static class_243 Q(class_746 var0, double var1, double var3) {
      class_243 var5 = HaloSupport9.position();
      double var6 = var0.method_23317() - var5.field_1352;
      double var8 = var0.method_23321() - var5.field_1350;
      double var10 = Math.max(0.001, Math.sqrt(var6 * var6 + var8 * var8));
      var6 /= var10;
      var8 /= var10;
      return new class_243(-var8 * var1 + var6 * var3, 0.0, var6 * var1 + var8 * var3);
   }

   private static HaloFactory r(long var0) {
      Consumer var2 = var0x -> HaloSupport9.turntable(9.0F);
      Consumer var3 = var0x -> {
         HaloRenderer7.previewAppear();
         h((class_746)var0x, class_3417.field_43154, 0.45F, 1.6F);
      };
      Consumer var4 = var0x -> {
         HaloRenderer7.previewShine();
         h((class_746)var0x, class_3417.field_26980, 0.8F, 1.25F);
      };
      Consumer var5 = var0x -> hit((class_746)var0x, false);
      List var6 = List.of(
         new HaloFactory.Data(0.0F, "Turn", var2),
         new HaloFactory.Data(0.3F, "Appear", var3),
         new HaloFactory.Data(2.8F, "Shine", var4),
         new HaloFactory.Data(4.6F, "Shiver", var5)
      );
      List var7 = List.of(
         new HaloFactory.Data2("Appear", "sparkles", var3),
         new HaloFactory.Data2("Shine", "sun", var4),
         new HaloFactory.Data2("Shiver", "zap", var5),
         new HaloFactory.Data2("Turn", "rotate-3d", var2)
      );
      return new HaloFactory(() -> var6, () -> 7.4F, var7, null, () -> null);
   }

   private static void h(class_746 var0, class_3414 var1, float var2, float var3) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1,
               class_3419.field_15248,
               var2,
               var3 + O.method_43057() * 0.1F,
               O,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() + 0.3,
               var0.method_23321()
            )
         );
   }

   static class_243 from(class_746 var0) {
      class_243 var1 = HaloSupport9.position();
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
               0.95F + O.method_43057() * 0.1F,
               O,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() * 0.6,
               var0.method_23321()
            )
         );
   }

   static HaloFactory of(ModuleDescriptor var0) {
      long var1 = 30617921457370L;
      long var3 = var1 ^ 88330649997788L;
      return r(var3);
   }

   record Data(float i, String Z, Consumer<class_746> b) {
      public float at() {
         return this.i;
      }

      public String label() {
         return this.Z;
      }

      public Consumer<class_746> action() {
         return this.b;
      }

      public float floatState12() {
         return this.i;
      }

      public String stringState43() {
         return this.Z;
      }

      public Consumer<class_746> consumerState12() {
         return this.b;
      }
   }

   record Data2(String K, String p, Consumer<class_746> G) {
      public String label() {
         return this.K;
      }

      public String icon() {
         return this.p;
      }

      public Consumer<class_746> action() {
         return this.G;
      }

      public String stringState14() {
         return this.K;
      }

      public String stringState12() {
         return this.p;
      }

      public Consumer<class_746> consumerState13() {
         return this.G;
      }
   }
}
