package dev.purxiy.modules.auras;

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

final class AuraFactory {
   private static final class_5819 u = class_5819.method_43047();
   private final Supplier<List<AuraFactory.Data2>> k;
   private final Supplier<Float> F;
   final List<AuraFactory.Data> H;
   private final Consumer<class_746> m;
   private final Supplier<String> G;

   private AuraFactory(
      Supplier<List<AuraFactory.Data2>> var1, Supplier<Float> var2, List<AuraFactory.Data> var3, Consumer<class_746> var4, Supplier<String> var5
   ) {
      this.k = var1;
      this.F = var2;
      this.H = var3;
      this.m = var4;
      this.G = var5;
   }

   List<AuraFactory.Data2> timeline() {
      return this.k.get();
   }

   float length() {
      return this.F.get();
   }

   String note() {
      return this.G.get();
   }

   void closed(class_746 var1) {
      if (this.m != null) {
         this.m.accept(var1);
      }
   }

   private static void O(long var0) {
      if (!AuraSupport2.moved()) {
         AuraSupport2.overlook(26.0F, 1.55F);
      }
   }

   private static void r(class_746 var0, class_3414 var1, float var2) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1, class_3419.field_15248, var2, 0.95F + u.method_43057() * 0.1F, u, var0.method_23317(), var0.method_23318(), var0.method_23321()
            )
         );
   }

   private static class_243 c(class_746 var0, double var1, double var3) {
      class_243 var5 = AuraSupport2.position();
      double var6 = var0.method_23317() - var5.field_1352;
      double var8 = var0.method_23321() - var5.field_1350;
      double var10 = Math.max(0.001, Math.sqrt(var6 * var6 + var8 * var8));
      var6 /= var10;
      var8 /= var10;
      return new class_243(-var8 * var1 + var6 * var3, 0.0, var6 * var1 + var8 * var3);
   }

   private static void t(class_746 var0, class_3414 var1, float var2, float var3) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1,
               class_3419.field_15248,
               var2,
               var3 + u.method_43057() * 0.1F,
               u,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() + 0.3,
               var0.method_23321()
            )
         );
   }

   private static AuraFactory H(long var0) {
      Consumer var2 = var0x -> AuraRenderer3.previewGather();
      Consumer var3 = var0x -> AuraSupport2.turntable(9.0F);
      Consumer var4 = var0x -> AuraRenderer3.pulse();
      List var5 = List.of(
         new AuraFactory.Data2(0.0F, "Gather", var2),
         new AuraFactory.Data2(0.5F, "Turn", var3),
         new AuraFactory.Data2(4.4F, "Pulse", var4),
         new AuraFactory.Data2(6.9F, "Pulse", var4)
      );
      List var6 = List.of(
         new AuraFactory.Data("Gather", "sparkles", var2), new AuraFactory.Data("Pulse", "radio", var4), new AuraFactory.Data("Turn", "rotate-3d", var3)
      );
      return new AuraFactory(() -> var5, () -> 9.6F, var6, null, () -> null);
   }

   static class_243 from(class_746 var0) {
      class_243 var1 = AuraSupport2.position();
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
               0.95F + u.method_43057() * 0.1F,
               u,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() * 0.6,
               var0.method_23321()
            )
         );
   }

   static AuraFactory of(ModuleDescriptor var0) {
      long var1 = 58786003691111L;
      long var3 = var1 ^ 129128415202293L;
      return H(var3);
   }

   record Data(String C, String D, Consumer<class_746> n) {
      public String label() {
         return this.C;
      }

      public String icon() {
         return this.D;
      }

      public Consumer<class_746> action() {
         return this.n;
      }

      public String stringState15() {
         return this.C;
      }

      public String stringState25() {
         return this.D;
      }

      public Consumer<class_746> consumerState6() {
         return this.n;
      }
   }

   record Data2(float a, String Q, Consumer<class_746> H) {
      public float at() {
         return this.a;
      }

      public String label() {
         return this.Q;
      }

      public Consumer<class_746> action() {
         return this.H;
      }

      public float floatState31() {
         return this.a;
      }

      public String stringState26() {
         return this.Q;
      }

      public Consumer<class_746> consumerState7() {
         return this.H;
      }
   }
}
