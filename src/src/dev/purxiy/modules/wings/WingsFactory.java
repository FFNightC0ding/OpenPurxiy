// Sourced By DevFFNightmare
package dev.purxiy.modules.wings;

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

final class WingsFactory {
   private static final class_5819 m = class_5819.method_43047();
   private final Supplier<List<WingsFactory.Data2>> J;
   private final Supplier<Float> s;
   final List<WingsFactory.Data> g;
   
   private final Consumer<class_746> A;
   private final Supplier<String> O;

   private static final String[] b = new String[15];

   private WingsFactory(
      Supplier<List<WingsFactory.Data2>> var1, Supplier<Float> var2, List<WingsFactory.Data> var3,  Consumer<class_746> var4, Supplier<String> var5
   ) {
      this.J = var1;
      this.s = var2;
      this.g = var3;
      this.A = var4;
      this.O = var5;
   }

   List<WingsFactory.Data2> timeline() {
      return this.J.get();
   }

   float length() {
      return this.s.get();
   }

   
   String note() {
      return this.O.get();
   }

   void closed(class_746 var1) {
      if (this.A != null) {
         this.A.accept(var1);
      }
   }

   private static void F(long var0) {
      if (!WingsSupport.moved()) {
         WingsSupport.overlook(26.0F, 1.55F);
      }
   }

   private static void O(class_746 var0, class_3414 var1, float var2) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1, class_3419.field_15248, var2, 0.95F + m.method_43057() * 0.1F, m, var0.method_23317(), var0.method_23318(), var0.method_23321()
            )
         );
   }

   private static class_243 Y(class_746 var0, double var1, double var3) {
      class_243 var5 = WingsSupport.position();
      double var6 = var0.method_23317() - var5.field_1352;
      double var8 = var0.method_23321() - var5.field_1350;
      double var10 = Math.max(0.001, Math.sqrt(var6 * var6 + var8 * var8));
      var6 /= var10;
      var8 /= var10;
      return new class_243(-var8 * var1 + var6 * var3, 0.0, var6 * var1 + var8 * var3);
   }

   private static void s(class_746 var0, class_3414 var1, float var2, float var3) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1,
               class_3419.field_15248,
               var2,
               var3 + m.method_43057() * 0.1F,
               m,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() + 0.3,
               var0.method_23321()
            )
         );
   }

   private static WingsFactory W(int var0, int var1, short var2) {
      Consumer var3 = var0x -> WingsSupport.turntable(9.0F);
      Consumer var4 = var0x -> WingsUpdater2.preview(WingsUpdater.TypeK.t, 2.6F);
      Consumer var5 = var0x -> {
         WingsUpdater2.preview(WingsUpdater.TypeK.T, 1.6F);
         class_310.method_1551()
            .method_1483()
            .method_4873(
               new class_1109(
                  class_3417.field_14550,
                  class_3419.field_15248,
                  0.35F,
                  1.5F + m.method_43057() * 0.2F,
                  m,
                  ((net.minecraft.class_1297)(Object)var0x).method_23317(),
                  ((net.minecraft.class_1297)(Object)var0x).method_23318() + ((net.minecraft.class_1297)(Object)var0x).method_17682() * 0.7,
                  ((net.minecraft.class_1297)(Object)var0x).method_23321()
               )
            );
      };
      Consumer var6 = var0x -> WingsUpdater2.preview(WingsUpdater.TypeK.l, 2.2F);
      Consumer var7 = var0x -> WingsUpdater2.preview(WingsUpdater.TypeK.b, 1.6F);
      List var8 = List.of(
         new WingsFactory.Data2(0.0F, "Turn", var3),
         new WingsFactory.Data2(0.3F, "Spread", var4),
         new WingsFactory.Data2(3.0F, "Beat", var5),
         new WingsFactory.Data2(4.8F, "Glide", var6),
         new WingsFactory.Data2(7.2F, "Fold", var7)
      );
      List var9 = List.of(
         new WingsFactory.Data("Spread", "maximize-2", var4),
         new WingsFactory.Data("Beat", "feather", var5),
         new WingsFactory.Data("Glide", "wind", var6),
         new WingsFactory.Data("Fold", "minimize-2", var7),
         new WingsFactory.Data("Turn", "rotate-3d", var3)
      );
      return new WingsFactory(() -> var8, () -> 9.2F, var9, var0x -> WingsUpdater2.preview(WingsUpdater.TypeK.b, 0.1F), () -> null);
   }

   static class_243 from(class_746 var0) {
      class_243 var1 = WingsSupport.position();
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
               0.95F + m.method_43057() * 0.1F,
               m,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() * 0.6,
               var0.method_23321()
            )
         );
   }

   static WingsFactory of(ModuleDescriptor var0) {
      long var1 = 123133092325914L;
      long var3 = var1 ^ 125222545350100L;
      int var5 = (int)((var1 ^ 125222545350100L) >>> 32);
      int var6 = (int)((var1 ^ 125222545350100L) << 32 >>> 48);
      int var7 = (int)(var3 << 48 >>> 48);
      return W(var5, var6, (short)var7);
   }

record Data(String f, String j, Consumer<class_746> Z) {
      public String label() {
         return this.f;
      }

      public String icon() {
         return this.j;
      }

      public Consumer<class_746> action() {
         return this.Z;
      }

      public String stringState38() {
         return this.f;
      }

      public String stringState29() {
         return this.j;
      }

      public Consumer<class_746> consumerState29() {
         return this.Z;
      }
   }

   record Data2(float r, String m, Consumer<class_746> d) {
      public float at() {
         return this.r;
      }

      public String label() {
         return this.m;
      }

      public Consumer<class_746> action() {
         return this.d;
      }

      public float floatState45() {
         return this.r;
      }

      public String stringState52() {
         return this.m;
      }

      public Consumer<class_746> consumerState24() {
         return this.d;
      }
   }
}
