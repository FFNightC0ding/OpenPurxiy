// Sourced By DevFFNightmare
package dev.purxiy.modules.glass;

import dev.purxiy.loader.fabric.ModuleDescriptor;
import java.util.ArrayList;
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

final class GlassFactory {
   private static final class_5819 Y = class_5819.method_43047();
   private final Supplier<List<GlassFactory.Data2>> j;
   private final Supplier<Float> n;
   final List<GlassFactory.Data> b;
   
   private final Consumer<class_746> l;
   private final Supplier<String> J;



   private GlassFactory(
      Supplier<List<GlassFactory.Data2>> var1, Supplier<Float> var2, List<GlassFactory.Data> var3,  Consumer<class_746> var4, Supplier<String> var5
   ) {
      this.j = var1;
      this.n = var2;
      this.b = var3;
      this.l = var4;
      this.J = var5;
   }

   List<GlassFactory.Data2> timeline() {
      return this.j.get();
   }

   float length() {
      return this.n.get();
   }

   
   String note() {
      return this.J.get();
   }

   void closed(class_746 var1) {
      if (this.l != null) {
         this.l.accept(var1);
      }
   }

   private static void z(long var0) {
      if (!GlassSupport2.moved()) {
         GlassSupport2.overlook(26.0F, 1.55F);
      }
   }

   private static void m(class_746 var0, class_3414 var1, float var2) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1, class_3419.field_15248, var2, 0.95F + Y.method_43057() * 0.1F, Y, var0.method_23317(), var0.method_23318(), var0.method_23321()
            )
         );
   }

   private static class_243 T(class_746 var0, double var1, double var3) {
      class_243 var5 = GlassSupport2.position();
      double var6 = var0.method_23317() - var5.field_1352;
      double var8 = var0.method_23321() - var5.field_1350;
      double var10 = Math.max(0.001, Math.sqrt(var6 * var6 + var8 * var8));
      var6 /= var10;
      var8 /= var10;
      return new class_243(-var8 * var1 + var6 * var3, 0.0, var6 * var1 + var8 * var3);
   }

   private static void S(class_746 var0, class_3414 var1, float var2, float var3) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1,
               class_3419.field_15248,
               var2,
               var3 + Y.method_43057() * 0.1F,
               Y,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() + 0.3,
               var0.method_23321()
            )
         );
   }

   private static GlassFactory s(long var0, boolean var2) {
      GlassRenderer4.ensureSourceClassInitialized();
      Consumer var3 = var0x -> hit((class_746)(var0x), false);
      Consumer var4 = var0x -> GlassSupport2.turntable(7.0F);
      Consumer var5 = var0x -> {};
      ArrayList var6 = new ArrayList();
      boolean var7 = false;
      var6.add(new GlassFactory.Data2(0.0F, "Turn", var4));
      var6.add(new GlassFactory.Data2(2.6F, "Hit", var3));
      var6.add(new GlassFactory.Data2(4.8F, "Hit", var3));
      long var8 = 123572209943143L;
      if (123572209943143L == 0L) {
         boolean var10000 = false;
      } else {
         int var15 = var8 < 0L ? -1 : 1;
      }

      ArrayList var10 = new ArrayList();
      var10.add(new GlassFactory.Data("Hit", "swords", var3));
      long var11 = 123572209943142L;
      if (123572209943142L == 0L) {
         boolean var16 = false;
      } else {
         int var17 = var11 < 0L ? -1 : 1;
      }

      var10.add(new GlassFactory.Data("Turn", "rotate-3d", var4));
      List var14 = List.copyOf(var6);
      return new GlassFactory(() -> var14, () -> 7.1F, List.copyOf(var10), null, () -> null);
   }

   static class_243 from(class_746 var0) {
      class_243 var1 = GlassSupport2.position();
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
               0.95F + Y.method_43057() * 0.1F,
               Y,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() * 0.6,
               var0.method_23321()
            )
         );
   }

   static GlassFactory of(ModuleDescriptor var0) {
      long var1 = 78574980683858L;
      long var3 = var1 ^ 60566762838581L;
      return s(var3, false);
   }

record Data(String g, String H, Consumer<class_746> f) {
      public String label() {
         return this.g;
      }

      public String icon() {
         return this.H;
      }

      public Consumer<class_746> action() {
         return this.f;
      }

      public String stringState34() {
         return this.g;
      }

      public String stringState19() {
         return this.H;
      }

      public Consumer<class_746> consumerState9() {
         return this.f;
      }
   }

   record Data2(float d, String t, Consumer<class_746> s) {
      public float at() {
         return this.d;
      }

      public String label() {
         return this.t;
      }

      public Consumer<class_746> action() {
         return this.s;
      }

      public float floatState33() {
         return this.d;
      }

      public String stringState42() {
         return this.t;
      }

      public Consumer<class_746> consumerState10() {
         return this.s;
      }
   }
}
