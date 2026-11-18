// Sourced By DevFFNightmare
package dev.purxiy.modules.hitsplash;

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

final class HitSplashFactory {
   private static final class_5819 T = class_5819.method_43047();
   private final Supplier<List<HitSplashFactory.Data>> h;
   private final Supplier<Float> U;
   final List<HitSplashFactory.Data2> a;
   
   private final Consumer<class_746> J;
   private final Supplier<String> F;



   private HitSplashFactory(
      Supplier<List<HitSplashFactory.Data>> var1,
      Supplier<Float> var2,
      List<HitSplashFactory.Data2> var3,
       Consumer<class_746> var4,
      Supplier<String> var5
   ) {
      this.h = var1;
      this.U = var2;
      this.a = var3;
      this.J = var4;
      this.F = var5;
   }

   List<HitSplashFactory.Data> timeline() {
      return this.h.get();
   }

   float length() {
      return this.U.get();
   }

   
   String note() {
      return this.F.get();
   }

   void closed(class_746 var1) {
      if (this.J != null) {
         this.J.accept(var1);
      }
   }

   private static void V(long var0) {
      if (!HitSplashSupport.moved()) {
         HitSplashSupport.overlook(26.0F, 1.55F);
      }
   }

   private static void K(class_746 var0, class_3414 var1, float var2) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1, class_3419.field_15248, var2, 0.95F + T.method_43057() * 0.1F, T, var0.method_23317(), var0.method_23318(), var0.method_23321()
            )
         );
   }

   private static class_243 I(class_746 var0, double var1, double var3) {
      class_243 var5 = HitSplashSupport.position();
      double var6 = var0.method_23317() - var5.field_1352;
      double var8 = var0.method_23321() - var5.field_1350;
      double var10 = Math.max(0.001, Math.sqrt(var6 * var6 + var8 * var8));
      var6 /= var10;
      var8 /= var10;
      return new class_243(-var8 * var1 + var6 * var3, 0.0, var6 * var1 + var8 * var3);
   }

   private static void K(class_746 var0, class_3414 var1, float var2, float var3) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1,
               class_3419.field_15248,
               var2,
               var3 + T.method_43057() * 0.1F,
               T,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() + 0.3,
               var0.method_23321()
            )
         );
   }

   private static HitSplashFactory J(long var0) {
      Consumer var2 = var0x -> {
         hit((class_746)(var0x), false);
         HitSplashRenderer.previewHit((class_1309)(Object)var0x, from((class_746)(var0x)), false);
      };
      Consumer var3 = var0x -> {
         hit((class_746)(var0x), true);
         HitSplashRenderer.previewHit((class_1309)(Object)var0x, from((class_746)(var0x)), true);
      };
      Consumer var4 = var0x -> {
         hit((class_746)(var0x), true);
         HitSplashRenderer.previewKill((class_1309)(Object)var0x, from((class_746)(var0x)));
      };
      List var5 = List.of(
         new HitSplashFactory.Data(0.0F, "Hit", var2),
         new HitSplashFactory.Data(0.45F, "Hit", var2),
         new HitSplashFactory.Data(0.9F, "Critical", var3),
         new HitSplashFactory.Data(1.6F, "Kill", var4)
      );
      List var6 = List.of(
         new HitSplashFactory.Data2("Hit", "swords", var2),
         new HitSplashFactory.Data2("Critical", "sparkle", var3),
         new HitSplashFactory.Data2("Kill", "skull", var4),
         new HitSplashFactory.Data2("Clear", "eraser", var0x -> HitSplashRenderer.clear())
      );
      return new HitSplashFactory(() -> var5, () -> 2.6F, var6, null, () -> null);
   }

   static class_243 from(class_746 var0) {
      class_243 var1 = HitSplashSupport.position();
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
               0.95F + T.method_43057() * 0.1F,
               T,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() * 0.6,
               var0.method_23321()
            )
         );
   }

   static HitSplashFactory of(ModuleDescriptor var0) {
      long var1 = 113161852254935L;
      long var3 = var1 ^ 53314399333490L;
      return J(var3);
   }

record Data(float C, String I, Consumer<class_746> v) {
      public float at() {
         return this.C;
      }

      public String label() {
         return this.I;
      }

      public Consumer<class_746> action() {
         return this.v;
      }

      public float floatState16() {
         return this.C;
      }

      public String stringState7() {
         return this.I;
      }

      public Consumer<class_746> consumerState17() {
         return this.v;
      }
   }

   record Data2(String q, String U, Consumer<class_746> i) {
      public String label() {
         return this.q;
      }

      public String icon() {
         return this.U;
      }

      public Consumer<class_746> action() {
         return this.i;
      }

      public String stringState17() {
         return this.q;
      }

      public String stringState22() {
         return this.U;
      }

      public Consumer<class_746> consumerState18() {
         return this.i;
      }
   }
}
