package dev.purxiy.modules.jumpcircles;

import dev.purxiy.loader.fabric.ModuleDescriptor;
import java.util.ArrayList;
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

final class JumpCirclesFactory {
   private static final class_5819 c = class_5819.method_43047();
   private final Supplier<List<JumpCirclesFactory.Data2>> e;
   private final Supplier<Float> C;
   final List<JumpCirclesFactory.Data> G;
   private final Consumer<class_746> n;
   private final Supplier<String> g;
   private static final String[] b = new String[7];

   private JumpCirclesFactory(
      Supplier<List<JumpCirclesFactory.Data2>> var1, Supplier<Float> var2, List<JumpCirclesFactory.Data> var3, Consumer<class_746> var4, Supplier<String> var5
   ) {
      this.e = var1;
      this.C = var2;
      this.G = var3;
      this.n = var4;
      this.g = var5;
   }

   List<JumpCirclesFactory.Data2> timeline() {
      return this.e.get();
   }

   float length() {
      return this.C.get();
   }

   String note() {
      return this.g.get();
   }

   void closed(class_746 var1) {
      if (this.n != null) {
         this.n.accept(var1);
      }
   }

   private static JumpCirclesFactory n(int var0, short var1, char var2) {
      long var3 = 137845025946990L;
      long var5 = var3 ^ 36555893863149L;
      var5 = var3 ^ 17411686669142L;
      Consumer var7 = var0x -> {
         long var1x = 101575420759939L;
         long var3x = (var1x ^ 6727081287169L) >>> 16;
         int var5x = (int)((var1x ^ 6727081287169L) << 48 >>> 48);
         h(var3x, (char)var5x);
         JumpCirclesRenderer2.preview((class_1309)var0x, 0.0, 0.0);
      };
      JumpCirclesRenderer2.ensureSourceClassInitialized();
      ArrayList var8 = new ArrayList();

      for (int var9 = 0; var9 < 3; var9++) {
         double var10 = (var9 - 1) * 1.1;
         var8.add(var2x -> {
            long var3x = 125943249643064L;
            long var5x = (var3x ^ 45032350234554L) >>> 16;
            int var7x = (int)((var3x ^ 45032350234554L) << 48 >>> 48);
            h(var5x, (char)var7x);
            class_243 var8x = X((class_746)var2x, var10, 0.0);
            JumpCirclesRenderer2.preview(var2x, var8x.field_1352, var8x.field_1350);
         });
      }

      List var13 = List.of(
         new JumpCirclesFactory.Data2(0.0F, "Jump", var7),
         new JumpCirclesFactory.Data2(1.5F, "Hop", (Consumer<class_746>)var8.get(0)),
         new JumpCirclesFactory.Data2(1.95F, "Hop", (Consumer<class_746>)var8.get(1)),
         new JumpCirclesFactory.Data2(2.4F, "Hop", (Consumer<class_746>)var8.get(2))
      );
      List var14 = List.of(new JumpCirclesFactory.Data("Jump", "arrow-up", var7), new JumpCirclesFactory.Data("Hops", "rabbit", var1x -> {
         ((Consumer)var8.get(0)).accept(var1x);
         JumpCirclesRenderer.later(0.45F, (Consumer<class_746>)var8.get(1));
         JumpCirclesRenderer.later(0.9F, (Consumer<class_746>)var8.get(2));
      }));
      return new JumpCirclesFactory(() -> var13, () -> 2.6F + JumpCirclesRenderer2.g.c.floatValue(), var14, null, () -> null);
   }

   private static void h(long var0, char var2) {
      if (!JumpCirclesSupport2.moved()) {
         JumpCirclesSupport2.overlook(26.0F, 1.55F);
      }
   }

   private static void N(class_746 var0, class_3414 var1, float var2) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1, class_3419.field_15248, var2, 0.95F + c.method_43057() * 0.1F, c, var0.method_23317(), var0.method_23318(), var0.method_23321()
            )
         );
   }

   private static class_243 X(class_746 var0, double var1, double var3) {
      class_243 var5 = JumpCirclesSupport2.position();
      double var6 = var0.method_23317() - var5.field_1352;
      double var8 = var0.method_23321() - var5.field_1350;
      double var10 = Math.max(0.001, Math.sqrt(var6 * var6 + var8 * var8));
      var6 /= var10;
      var8 /= var10;
      return new class_243(-var8 * var1 + var6 * var3, 0.0, var6 * var1 + var8 * var3);
   }

   private static void d(class_746 var0, class_3414 var1, float var2, float var3) {
      class_310.method_1551()
         .method_1483()
         .method_4873(
            new class_1109(
               var1,
               class_3419.field_15248,
               var2,
               var3 + c.method_43057() * 0.1F,
               c,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() + 0.3,
               var0.method_23321()
            )
         );
   }

   static class_243 from(class_746 var0) {
      class_243 var1 = JumpCirclesSupport2.position();
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
               0.95F + c.method_43057() * 0.1F,
               c,
               var0.method_23317(),
               var0.method_23318() + var0.method_17682() * 0.6,
               var0.method_23321()
            )
         );
   }

   static JumpCirclesFactory of(ModuleDescriptor var0) {
      long var1 = 59088697355859L;
      long var3 = var1 ^ 80140383998781L;
      int var5 = (int)((var1 ^ 80140383998781L) >>> 32);
      int var6 = (int)((var1 ^ 80140383998781L) << 32 >>> 48);
      int var7 = (int)(var3 << 48 >>> 48);
      return n(var5, (short)var6, (char)var7);
   }

   record Data(String W, String O, Consumer<class_746> o) {
      public String label() {
         return this.W;
      }

      public String icon() {
         return this.O;
      }

      public Consumer<class_746> action() {
         return this.o;
      }

      public String stringState47() {
         return this.W;
      }

      public String stringState54() {
         return this.O;
      }

      public Consumer<class_746> consumerState19() {
         return this.o;
      }
   }

   record Data2(float C, String b, Consumer<class_746> M) {
      public float at() {
         return this.C;
      }

      public String label() {
         return this.b;
      }

      public Consumer<class_746> action() {
         return this.M;
      }

      public float floatState16() {
         return this.C;
      }

      public String stringState5() {
         return this.b;
      }

      public Consumer<class_746> consumerState20() {
         return this.M;
      }
   }
}
