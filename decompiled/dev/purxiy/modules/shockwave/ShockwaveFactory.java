package dev.purxiy.modules.shockwave;

import dev.purxiy.loader.fabric.ModuleDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import net.minecraft.class_1109;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3419;
import net.minecraft.class_5819;
import net.minecraft.class_746;

final class ShockwaveFactory {
   private static final class_5819 a = class_5819.method_43047();
   private final Supplier<List<ShockwaveFactory.Data2>> t;
   private final Supplier<Float> I;
   final List<ShockwaveFactory.Data> w;
   private final Consumer<class_746> J;
   private final Supplier<String> q;

   private ShockwaveFactory(
      Supplier<List<ShockwaveFactory.Data2>> var1, Supplier<Float> var2, List<ShockwaveFactory.Data> var3, Consumer<class_746> var4, Supplier<String> var5
   ) {
      this.t = var1;
      this.I = var2;
      this.w = var3;
      this.J = var4;
      this.q = var5;
   }

   List<ShockwaveFactory.Data2> timeline() {
      return this.t.get();
   }

   float length() {
      return this.I.get();
   }

   String note() {
      return this.q.get();
   }

   void closed(class_746 var1) {
      if (this.J != null) {
         this.J.accept(var1);
      }
   }

   private static ShockwaveFactory G(long var0) {
      Consumer var2 = var0x -> {
         long var1 = 26563972653249L;
         W(var1);
         ShockwaveRenderer2.previewLand((class_1309)var0x, 2.5);
         x((class_746)var0x, class_3417.field_15018, 0.5F);
      };
      Consumer var3 = var0x -> {
         long var1 = 26563972653249L;
         W(var1);
         ShockwaveRenderer2.previewLand((class_1309)var0x, 7.0);
         x((class_746)var0x, class_3417.field_14928, 0.6F);
      };
      Consumer var4 = var0x -> {
         long var1 = 26563972653249L;
         ShockwaveModule.ensureSourceClassInitialized();
         W(var1);
         int var3x = 0;

         for (boolean var4x = false; var3x < 4; var3x++) {
            double var5x = var3x % 2 == 0 ? -0.25 : 0.25;
            double var7x = 0.9 - var3x * 0.6;
            ShockwaveRenderer4.later(var3x * 0.32F, var4xx -> {
               class_243 var5xx = h(var4xx, var5x, var7x);
               ShockwaveRenderer2.previewStep(var4xx, var5xx.field_1352, var5xx.field_1350);
            });
         }

         long var9 = 138328520879515L;
         int var6x = var9 < 0L ? -1 : 1;
      };
      Consumer var5 = var0x -> {
         long var1 = 26563972653249L;
         W(var1);
         class_243 var3x = h((class_746)var0x, 3.5, 2.5);
         ShockwaveRenderer2.previewBlast((class_1309)var0x, var3x.field_1352, var3x.field_1350);
         class_310.method_1551()
            .method_1483()
            .method_4873(
               new class_1109(
                  (class_3414)class_3417.field_15152.comp_349(),
                  class_3419.field_15245,
                  0.35F,
                  0.9F + a.method_43057() * 0.2F,
                  a,
                  ((class_1297)var0x).method_23317() + var3x.field_1352,
                  ((class_1297)var0x).method_23318(),
                  ((class_1297)var0x).method_23321() + var3x.field_1350
               )
            );
      };
      Supplier var6 = () -> {
         ShockwaveModule.ensureSourceClassInitialized();
         ArrayList var4x = new ArrayList();
         var4x.add(new ShockwaveFactory.Data2(0.0F, "Land", var2));
         boolean var5x = false;
         var4x.add(new ShockwaveFactory.Data2(1.6F, "Big fall", var3));
         long var6x = 84210067145833L;
         int var9 = var6x < 0L ? -1 : 1;
         boolean var9x = ShockwaveRenderer2.Q.E.enabled();
         long var10 = 84210067145834L;
         int var11 = var10 < 0L ? -1 : 1;
         if (var9x) {
            var4x.add(new ShockwaveFactory.Data2(3.6F, "Steps", var4));
         }

         if (ShockwaveRenderer2.Q.q.enabled()) {
            var4x.add(new ShockwaveFactory.Data2(5.0F, "Blast", var5));
         }

         return var4x;
      };
      List var7 = List.of(
         new ShockwaveFactory.Data("Land", "arrow-down", var2),
         new ShockwaveFactory.Data("Big fall", "arrow-down-to-line", var3),
         new ShockwaveFactory.Data("Steps", "footprints", var4),
         new ShockwaveFactory.Data("Blast", "bomb", var5)
      );
      return new ShockwaveFactory(var6, () -> {
         ShockwaveModule.ensureSourceClassInitialized();
         boolean var0x = false;
         return ShockwaveRenderer2.Q.q.enabled() ? 7.4F : (ShockwaveRenderer2.Q.E.enabled() ? 5.6F : 4.4F);
      }, var7, null, () -> null);
   }

   private static void W(long var0) {
      if (!ShockwaveSupport2.moved()) {
         ShockwaveSupport2.overlook(26.0F, 1.55F);
      }
   }

   private static void x(class_746 var0, class_3414 var1, float var2) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1, class_3419.field_15248, var2, 0.95F + a.method_43057() * 0.1F, a, var0.method_23317(), var0.method_23318(), var0.method_23321()
            )
         );
   }

   private static class_243 h(class_746 var0, double var1, double var3) {
      class_243 var5 = ShockwaveSupport2.position();
      double var6 = var0.method_23317() - var5.field_1352;
      double var8 = var0.method_23321() - var5.field_1350;
      double var10 = Math.max(0.001, Math.sqrt(var6 * var6 + var8 * var8));
      var6 /= var10;
      var8 /= var10;
      return new class_243(-var8 * var1 + var6 * var3, 0.0, var6 * var1 + var8 * var3);
   }

   private static void b(class_746 var0, class_3414 var1, float var2, float var3) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1,
               class_3419.field_15248,
               var2,
               var3 + a.method_43057() * 0.1F,
               a,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() + 0.3,
               var0.method_23321()
            )
         );
   }

   static class_243 from(class_746 var0) {
      class_243 var1 = ShockwaveSupport2.position();
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
               0.95F + a.method_43057() * 0.1F,
               a,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() * 0.6,
               var0.method_23321()
            )
         );
   }

   static ShockwaveFactory of(ModuleDescriptor var0) {
      long var1 = 73617433346427L;
      long var3 = var1 ^ 59489409026305L;
      return G(var3);
   }

   record Data(String j, String R, Consumer<class_746> d) {
      public String label() {
         return this.j;
      }

      public String icon() {
         return this.R;
      }

      public Consumer<class_746> action() {
         return this.d;
      }

      public String stringState29() {
         return this.j;
      }

      public String stringState46() {
         return this.R;
      }

      public Consumer<class_746> consumerState24() {
         return this.d;
      }
   }

   record Data2(float B, String w, Consumer<class_746> e) {
      public float at() {
         return this.B;
      }

      public String label() {
         return this.w;
      }

      public Consumer<class_746> action() {
         return this.e;
      }

      public float floatState42() {
         return this.B;
      }

      public String stringState59() {
         return this.w;
      }

      public Consumer<class_746> consumerState25() {
         return this.e;
      }
   }
}
