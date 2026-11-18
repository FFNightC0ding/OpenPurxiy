// Sourced By DevFFNightmare
package dev.purxiy.modules.nick;

import dev.purxiy.loader.fabric.client.ClientRegistry5;
import java.util.ArrayList;
import java.util.Optional;
import net.minecraft.class_2583;
import net.minecraft.class_5223;
import net.minecraft.class_5224;
import net.minecraft.class_5251;
import net.minecraft.class_5348;
import net.minecraft.class_5481;
import net.minecraft.class_5225.class_5231;
import net.minecraft.class_5348.class_5245;
import net.minecraft.class_5348.class_5246;

public final class NickHiderSupport2 {
   public static int p = 0;

   private NickHiderSupport2() {
   }

   public static class_5481 sequence(class_5481 var0) {
      return (class_5481)(!(var0 instanceof ClientRegistry5.Listener) && var0 != class_5481.field_26385 && NickHiderCache.active()
         ? new NickHiderSupport2.TypeT(var0)
         : var0);
   }

   public static class_5348 text(class_5348 var0) {
      if (var0 instanceof ClientRegistry5.Listener) {
         return var0;
      } else {
         NickHiderBuilder var1 = NickHiderCache.table();
         if (var1 == null) {
            return var0;
         } else {
            NickHiderCache2 var2 = NickHiderCache2.acquire();

            class_5348 var3;
            try {
               class_5223.method_27476(var0, class_2583.field_24360, collector(var2));
               if (var2.run(var1)) {
                  ArrayList var11 = new ArrayList();
                  StringBuilder var4 = new StringBuilder();
                  class_2583 var5 = null;

                  for (int var6 = 0; var6 < var2.U; var6++) {
                     class_2583 var7 = P(var2, var6);
                     if (var5 != null && !var7.equals(var5)) {
                        var11.add(class_5348.method_29431(var4.toString(), var5));
                        var4.setLength(0);
                     }

                     var5 = var7;
                     var4.appendCodePoint(var2.A[var6]);
                  }

                  if (var5 != null) {
                     var11.add(class_5348.method_29431(var4.toString(), var5));
                  }

                  return new NickHiderSupport2.TypeL(class_5348.method_29432(var11));
               }

               var3 = var0;
            } finally {
               var2.release();
            }

            return var3;
         }
      }
   }

   public static float width(String var0, class_5231 var1) {
      long var2 = 13512597346792L;
      long var4 = var2 ^ 101442866546201L;
      NickHiderBuilder var6 = NickHiderCache.table();
      NickHiderCache2 var7 = NickHiderCache2.acquire();

      float var8;
      try {
         class_5223.method_27479(var0, class_2583.field_24360, collector(var7));
         var8 = i(var7, var6, var4, var1);
      } finally {
         var7.release();
      }

      return var8;
   }

   public static float width(class_5348 var0, class_5231 var1) {
      long var2 = 38695250122037L;
      long var4 = var2 ^ 126685818473156L;
      NickHiderBuilder var6 = NickHiderCache.table();
      NickHiderCache2 var7 = NickHiderCache2.acquire();

      float var8;
      try {
         class_5223.method_27476(var0, class_2583.field_24360, collector(var7));
         var8 = i(var7, var6, var4, var1);
      } finally {
         var7.release();
      }

      return var8;
   }

   private static float i(NickHiderCache2 var0,  NickHiderBuilder var1, long var2, class_5231 var4) {
      NickHiderCodec.ensureSourceClassInitialized();
      float var5 = 0.0F;
      if (var1 != null && var0.run(var1)) {
         for (int var10 = 0; var10 < var0.U; var10++) {
            long var13 = 88000249077745L;
            if (88000249077745L == 0L) {
               boolean var15 = false;
            } else {
               int var16 = var13 < 0L ? -1 : 1;
            }

            var5 += var4.getWidth(var0.A[var10], P(var0, var10));
         }

         long var11 = 88000249077745L;
         if (88000249077745L == 0L) {
            boolean var17 = false;
         } else {
            int var18 = var11 < 0L ? -1 : 1;
         }
      } else {
         for (int var6 = 0; var6 < var0.D; var6++) {
            long var7 = 88000249077744L;
            if (88000249077744L == 0L) {
               boolean var10000 = false;
            } else {
               int var14 = var7 < 0L ? -1 : 1;
            }

            var5 += var4.getWidth(var0.N[var6], (class_2583)var0.V[var6]);
         }
      }

      long var12 = 88000249077744L;
      if (88000249077744L == 0L) {
         boolean var19 = false;
      } else {
         int var20 = var12 < 0L ? -1 : 1;
      }

      return var5;
   }

   public static boolean iterate(String var0, class_2583 var1, class_5224 var2) {
      long var3 = 117197899076952L;
      long var5 = var3 ^ 33241074252087L;
      long var7 = var3 ^ 42878003595328L;
      NickHiderBuilder var9 = NickHiderCache.table();
      if (var9 == null) {
         return class_5223.method_27479(var0, var1, var2);
      } else {
         NickHiderCache2 var10 = NickHiderCache2.acquire();

         boolean var11;
         try {
            class_5223.method_27479(var0, var1, collector(var10));
            var11 = var10.run(var9) ? F(var10, var7, var2) : L(var10, var2, var5);
         } finally {
            var10.release();
         }

         return var11;
      }
   }

   public static String plain(String var0) {
      NickHiderBuilder var1 = NickHiderCache.table();
      if (var1 != null && !var0.isEmpty()) {
         NickHiderCache2 var2 = NickHiderCache2.acquire();

         String var3;
         try {
            class_5223.method_27479(var0, class_2583.field_24360, collector(var2));
            var3 = var2.run(var1) ? var2.output() : var0;
         } finally {
            var2.release();
         }

         return var3;
      } else {
         return var0;
      }
   }

   static class_5224 collector(NickHiderCache2 var0) {
      return (var1, var2, var3) -> {
         var0.add(var1, var3, var2, var2.method_10973());
         return true;
      };
   }

   private static class_2583 P(NickHiderCache2 var0, int var1) {
      return paint((class_2583)var0.V[var0.q[var1]], var0.K[var1]);
   }

   static class_2583 paint(class_2583 var0,  NickHiderData var1) {
      if (var1 == null) {
         return var0;
      } else {
         class_2583 var2 = var1.rgb() >= 0 ? var0.method_27703(class_5251.method_27717(var1.rgb())) : var0;
         if (var1.has(1) || var1.clears(1)) {
            var2 = var2.method_10982(var1.has(1));
         }

         if (var1.has(2) || var1.clears(2)) {
            var2 = var2.method_10978(var1.has(2));
         }

         if (var1.has(4) || var1.clears(4)) {
            var2 = var2.method_30938(var1.has(4));
         }

         if (var1.has(8) || var1.clears(8)) {
            var2 = var2.method_36140(var1.has(8));
         }

         if (var1.has(16) || var1.clears(16)) {
            var2 = var2.method_36141(var1.has(16));
         }

         return var2;
      }
   }

   private static boolean L(NickHiderCache2 var0, class_5224 var1, long var2) {
      NickHiderCodec.ensureSourceClassInitialized();

      for (int var4 = 0; var4 < var0.D; var4++) {
         boolean var5 = var1.accept(var0.W[var4], (class_2583)var0.V[var4], var0.N[var4]);
         long var6 = 128285460180079L;
         if (128285460180079L == 0L) {
            boolean var10000 = false;
         } else {
            int var10 = var6 < 0L ? -1 : 1;
         }

         if (!var5) {
            return false;
         }
      }

      long var9 = 128285460180079L;
      if (128285460180079L == 0L) {
         boolean var11 = false;
      } else {
         int var12 = var9 < 0L ? -1 : 1;
      }

      return true;
   }

   private static boolean F(NickHiderCache2 var0, long var1, class_5224 var3) {
      NickHiderCodec.ensureSourceClassInitialized();
      int var4 = 0;

      for (int var5 = 0; var5 < var0.U; var5++) {
         int var6 = var0.q[var5];
         long var8 = 84009879657752L;
         if (84009879657752L == 0L) {
            boolean var10000 = false;
         } else {
            int var18 = var8 < 0L ? -1 : 1;
         }

         int var15;
         label61: {
            if (var5 > 0) {
               var15 = var0.q[var5 - 1];
               long var11 = 84009879657751L;
               if (84009879657751L == 0L) {
                  boolean var19 = false;
               } else {
                  int var20 = var11 < 0L ? -1 : 1;
               }

               long var13 = 84009879657751L;
               if (84009879657751L == 0L) {
                  boolean var21 = false;
               } else {
                  int var22 = var13 < 0L ? -1 : 1;
               }

               if (var15 == var6) {
                  var15 = var4 + 1;
                  break label61;
               }
            }

            var15 = 0;
         }

         var4 = var15;
         var8 = 84009879657752L;
         if (84009879657752L == 0L) {
            boolean var23 = false;
         } else {
            int var24 = var8 < 0L ? -1 : 1;
         }

         boolean var17 = var3.accept(var0.W[var6] + var15, P(var0, var5), var0.A[var5]);
         long var12 = 84009879657752L;
         if (84009879657752L == 0L) {
            boolean var25 = false;
         } else {
            int var26 = var12 < 0L ? -1 : 1;
         }

         if (!var17) {
            return false;
         }
      }

      return true;
   }

   static class_5348 exportText(class_5348 var0) {
      class_5348 var1 = text(var0);
      return var1 instanceof NickHiderSupport2.TypeL var2 ? var2.inner() : var1;
   }

   public interface Listener extends ClientRegistry5.Listener {
   }

   private record TypeL(class_5348 O) implements class_5348, NickHiderSupport2.Listener {
      @Override
      public <T> Optional<T> method_27657(class_5245<T> var1) {
         return this.O.method_27657(var1);
      }

      @Override
      public <T> Optional<T> method_27658(class_5246<T> var1, class_2583 var2) {
         return this.O.method_27658(var1, var2);
      }

      public class_5348 inner() {
         return this.O;
      }

      public class_5348 minecraftObjectState1() {
         return this.O;
      }
   }

   private record TypeT(class_5481 l) implements class_5481, NickHiderSupport2.Listener {
      @Override
      public boolean accept(class_5224 var1) {
         long var2 = 17019004385638L;
         long var4 = var2 ^ 136160177116425L;
         long var6 = var2 ^ 73747151004798L;
         NickHiderBuilder var8 = NickHiderCache.table();
         if (var8 == null) {
            return this.l.accept(var1);
         } else {
            NickHiderCache2 var9 = NickHiderCache2.acquire();

            boolean var10;
            try {
               this.l.accept(NickHiderSupport2.collector(var9));
               var10 = var9.run(var8) ? NickHiderSupport2.F(var9, var6, var1) : NickHiderSupport2.L(var9, var1, var4);
            } finally {
               var9.release();
            }

            return var10;
         }
      }

      public class_5481 inner() {
         return this.l;
      }

      public class_5481 minecraftObjectState2() {
         return this.l;
      }
   }
}
