package dev.purxiy.modules.ripple;

import dev.purxiy.loader.fabric.ModuleDescriptor;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_1109;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_5819;
import net.minecraft.class_746;

final class RippleFactory {
   private static final class_5819 r = class_5819.method_43047();
   private final Supplier<List<RippleFactory.CosmeticEntry>> g;
   private final Supplier<Float> T;
   final List<RippleFactory.Data> b;
   private final Consumer<class_746> m;
   private final Supplier<String> W;

   private RippleFactory(
      Supplier<List<RippleFactory.CosmeticEntry>> var1, Supplier<Float> var2, List<RippleFactory.Data> var3, Consumer<class_746> var4, Supplier<String> var5
   ) {
      this.g = var1;
      this.T = var2;
      this.b = var3;
      this.m = var4;
      this.W = var5;
   }

   List<RippleFactory.CosmeticEntry> timeline() {
      return this.g.get();
   }

   float length() {
      return this.T.get();
   }

   String note() {
      return this.W.get();
   }

   void closed(class_746 var1) {
      if (this.m != null) {
         this.m.accept(var1);
      }
   }

   private static void X(int var0, short var1, short var2) {
      if (!RippleSupport.moved()) {
         RippleSupport.overlook(26.0F, 1.55F);
      }
   }

   private static void i(class_746 var0, class_3414 var1, float var2) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1, class_3419.field_15248, var2, 0.95F + r.method_43057() * 0.1F, r, var0.method_23317(), var0.method_23318(), var0.method_23321()
            )
         );
   }

   private static class_243 u(class_746 var0, double var1, double var3) {
      class_243 var5 = RippleSupport.position();
      double var6 = var0.method_23317() - var5.field_1352;
      double var8 = var0.method_23321() - var5.field_1350;
      double var10 = Math.max(0.001, Math.sqrt(var6 * var6 + var8 * var8));
      var6 /= var10;
      var8 /= var10;
      return new class_243(-var8 * var1 + var6 * var3, 0.0, var6 * var1 + var8 * var3);
   }

   private static void x(class_746 var0, class_3414 var1, float var2, float var3) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1,
               class_3419.field_15248,
               var2,
               var3 + r.method_43057() * 0.1F,
               r,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() + 0.3,
               var0.method_23321()
            )
         );
   }

   private static RippleFactory g(long var0) {
      Consumer var2 = var0x -> {
         hit((class_746)var0x, false);
         RippleRenderPass.previewHit((class_1309)var0x, from((class_746)var0x), false);
      };
      Consumer var3 = var0x -> {
         hit((class_746)var0x, true);
         RippleRenderPass.previewHit((class_1309)var0x, from((class_746)var0x), true);
      };
      Consumer var4 = var0x -> {
         hit((class_746)var0x, true);
         RippleRenderPass.previewHit((class_1309)var0x, from((class_746)var0x), false);
         RippleRenderPass.previewKill((class_1309)var0x);
      };
      List var5 = List.of(
         new RippleFactory.CosmeticEntry(0.0F, "Hit", var2),
         new RippleFactory.CosmeticEntry(0.85F, "Hit", var2),
         new RippleFactory.CosmeticEntry(1.7F, "Critical", var3),
         new RippleFactory.CosmeticEntry(2.75F, "Kill", var4)
      );
      List var6 = List.of(
         new RippleFactory.Data("Hit", "swords", var2), new RippleFactory.Data("Critical", "sparkle", var3), new RippleFactory.Data("Kill", "skull", var4)
      );
      return new RippleFactory(() -> var5, () -> 4.6F, var6, null, () -> null);
   }

   static class_243 from(class_746 var0) {
      class_243 var1 = RippleSupport.position();
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
               0.95F + r.method_43057() * 0.1F,
               r,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() * 0.6,
               var0.method_23321()
            )
         );
   }

   static RippleFactory of(ModuleDescriptor var0) {
      long var1 = 88212436832668L;
      long var3 = var1 ^ 60458031760366L;
      return g(var3);
   }

   record Data(String p, String N, Consumer<class_746> t) {
      public String label() {
         return this.p;
      }

      public String icon() {
         return this.N;
      }

      public Consumer<class_746> action() {
         return this.t;
      }

      public String stringState12() {
         return this.p;
      }

      public String stringState53() {
         return this.N;
      }

      public Consumer<class_746> consumerState23() {
         return this.t;
      }
   }

   record CosmeticEntry(float l, String r, Consumer<class_746> s) {
      public float at() {
         return this.l;
      }

      public String label() {
         return this.r;
      }

      public Consumer<class_746> action() {
         return this.s;
      }

      public float floatState37() {
         return this.l;
      }

      public String stringState13() {
         return this.r;
      }

      public Consumer<class_746> consumerState10() {
         return this.s;
      }
   }
}
