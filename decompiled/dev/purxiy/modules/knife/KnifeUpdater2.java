package dev.purxiy.modules.knife;

import dev.purxiy.loader.api.ModuleContext;
import dev.purxiy.loader.fabric.ClientServices;
import dev.purxiy.render.RenderPipelines;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.class_1011;
import net.minecraft.class_1268;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1799;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3489;
import net.minecraft.class_6880;
import net.minecraft.class_746;

public final class KnifeUpdater2 {
   private static final float x = 0.24F;
   private static final float M = 3.0F;
   private static final float S = 1.2F;
   private static final float d = 2.0F;
   private static final Set<class_3414> U = Set.of(
      class_3417.field_15016, class_3417.field_14999, class_3417.field_14914, class_3417.field_14840, class_3417.field_14706, class_3417.field_14625
   );
   private static final KnifeUpdater2.TypeX[] h = new KnifeUpdater2.TypeX[]{new KnifeUpdater2.TypeX(), new KnifeUpdater2.TypeX()};
   private static Object B;
   private static Object u;
   private static long b;
   private static long O;
   private static float H = 1.0F;
   private static boolean i;
   private static int w;
   private static KnifeRenderer7.TypeW e;
   private static float I = 1.0F;
   public static int a;
   public static int m;
   public static final List<String> v = new ArrayList<>();
   public static float N;
   public static float f;
   public static final List<String> T = new ArrayList<>();
   private static Path C;
   private static float R;
   private static int X;
   private static int y;
   private static int k;
   private static int Q;
   private static int j;
   private static List<KnifeRenderer7.TypeZ> r = List.of();
   static KnifeModule Z;
   private static ModuleContext E;
   private static int c;
   private static int F;
   public static boolean G;

   private KnifeUpdater2() {
   }

   public static boolean replaces(class_1799 var0, class_1268 var1) {
      KnifeModule var2 = Z;
      return var2.enabled() && !var0.method_7960() && var0.method_31573(class_3489.field_42611) && (var1 == class_1268.field_5808 || var2.d.enabled());
   }

   public static void reset() {
      for (KnifeUpdater2.TypeX var3 : h) {
         var3.clear();
      }

      u = null;
      B = null;
   }

   public static void tick(class_310 var0) {
      long var1 = 50674312326315L;
      long var3 = var1 ^ 48428796994372L;
      int var5 = (int)((var1 ^ 48428796994372L) >>> 48);
      int var6 = (int)((var1 ^ 48428796994372L) << 16 >>> 32);
      int var7 = (int)(var3 << 48 >>> 48);
      if (var0.field_1687 != B || var0.field_1724 != u || !Z.enabled()) {
         reset();
         B = var0.field_1687;
         u = var0.field_1724;
      }

      if (var0.field_1724 != null) {
         if (var0.field_1724.field_6235 > w) {
            o(2.0F, (char)var5, var6, (short)var7);
         }

         w = var0.field_1724.field_6235;
         if (var0.field_1690.method_31044().method_31034() && var0.field_1724.method_5805() && !var0.field_1724.method_7325()) {
            for (class_1268 var15 : class_1268.values()) {
               if (!replaces(var0.field_1724.method_5998(var15), var15)) {
                  h[var15.ordinal()].clear();
               }
            }
         } else {
            for (KnifeUpdater2.TypeX var11 : h) {
               var11.clear();
            }
         }
      }
   }

   public static boolean otherHandFree(class_1268 var0) {
      class_310 var1 = class_310.method_1551();
      return var0 == class_1268.field_5808 && var1.field_1724 != null && var1.field_1724.method_6079().method_7960() && Z.J.enabled();
   }

   public static boolean inspect() {
      long var0 = 9790498780926L;
      long var2 = var0 ^ 121361526182950L;
      long var4 = var0 ^ 73992006374833L;
      long var6 = var0 ^ 48177726452696L;
      KnifeUpdater2.TypeX var8 = J(var2);
      if (var8 == null) {
         return false;
      } else if (var8.B == KnifeUpdater2.TypeY.z) {
         if (!var8.Q && !var8.g) {
            var8.Q = true;
            return true;
         } else {
            return false;
         }
      } else {
         var8.play(g(var4, var8, z(var6, class_310.method_1551())));
         return true;
      }
   }

   public static boolean slash() {
      long var0 = 117519034986369L;
      long var2 = var0 ^ 13628498398553L;
      long var4 = var0 ^ 41721041112921L;
      KnifeUpdater2.TypeX var6 = J(var2);
      if (var6 == null) {
         return false;
      } else if (var6.B == KnifeUpdater2.TypeY.z) {
         if (!var6.Q && !var6.g) {
            var6.g = true;
            return true;
         } else {
            return false;
         }
      } else {
         KnifeRenderer7.TypeZ var7 = F(var6, var4);
         if (var7 == null) {
            return false;
         } else {
            var6.play(var7);
            return true;
         }
      }
   }

   private static KnifeUpdater2.TypeX J(long var0) {
      long var2 = 38718181655846L;
      class_310 var4 = class_310.method_1551();
      if (var4.field_1724 != null) {
         long var5 = 112730704700119L;
         int var8 = var5 < 0L ? -1 : 1;
         if (var4.field_1755 == null) {
            long var8x = 112730704700119L;
            int var10 = var8x < 0L ? -1 : 1;
            if (var4.method_18506() == null) {
               long var10x = 112730704700119L;
               int var12 = var10x < 0L ? -1 : 1;
               if (!var4.method_1493()) {
                  long var12x = 112730704700120L;
                  int var14 = var12x < 0L ? -1 : 1;
                  if (var4.method_1569() && var4.field_1690.method_31044().method_31034() && !var4.field_1724.method_7325()) {
                     long var14x = 112730704700119L;
                     int var16 = var14x < 0L ? -1 : 1;
                     if (var4.field_1724.method_5805()) {
                        long var16x = 112730704700119L;
                        int var18 = var16x < 0L ? -1 : 1;
                        if (!var4.field_1724.method_6115() && !var4.field_1724.field_6252) {
                           class_1268 var18x = z(var2, var4);
                           if (!replaces(var4.field_1724.method_5998(var18x), var18x)) {
                              return null;
                           }

                           KnifeUpdater2.TypeX var19 = h[var18x.ordinal()];
                           long var20 = 112730704700119L;
                           int var21 = var20 < 0L ? -1 : 1;
                           return var19.y != null && var19.B != KnifeUpdater2.TypeY.d ? var19 : null;
                        }
                     }
                  }
               }
            }
         }
      }

      return null;
   }

   private static boolean L(long var0, KnifeRenderer7.TypeZ var2, class_1268 var3) {
      long var4 = 4478982401481L;
      int var7 = var4 < 0L ? -1 : 1;
      if (var2.O) {
         boolean var7x = otherHandFree(var3);
         long var8 = 4478982401481L;
         int var9 = var8 < 0L ? -1 : 1;
         if (!var7x) {
            return false;
         }
      }

      return !var2.V || Z.D.enabled();
   }

   private static KnifeRenderer7.TypeZ g(long var0, KnifeUpdater2.TypeX var2, class_1268 var3) {
      long var4 = 4478982401481L;
      List var6 = KnifeRenderer7.inspects(var2.r);

      for (int var7 = 0; var7 < var6.size(); var7++) {
         KnifeRenderer7.TypeZ var8 = (KnifeRenderer7.TypeZ)var6.get((var2.F + var7) % var6.size());
         long var9 = 83202583700303L;
         int var11 = var9 < 0L ? -1 : 1;
         if (L(var4, var8, var3)) {
            var2.F = (var2.F + var7 + 1) % var6.size();
            return var8;
         }
      }

      long var12 = 83202583700303L;
      int var13 = var12 < 0L ? -1 : 1;
      return (KnifeRenderer7.TypeZ)var6.getFirst();
   }

   private static KnifeRenderer7.TypeZ F(KnifeUpdater2.TypeX var0, long var1) {
      long var3 = 91900712359695L;
      List var5 = KnifeRenderer7.walks(var0.r).stream().filter(var0x -> var0x.V && !var0x.O).toList();
      return S(var5, var0.D, var3, var0);
   }

   private static KnifeRenderer7.TypeZ h(KnifeUpdater2.TypeX var0, class_1268 var1, long var2) {
      long var4;
      ArrayList var6;
      ArrayList var7;
      boolean var8;
      label182: {
         var4 = 90201015243634L;
         var6 = new ArrayList();
         var7 = new ArrayList();
         if (var0.R[0] != null) {
            long var9 = 45111953964614L;
            int var35 = var9 < 0L ? -1 : 1;
            if (var0.R[0].V && var0.R[1] != null && var0.R[1].V) {
               var8 = true;
               break label182;
            }
         }

         var8 = false;
      }

      boolean var34 = var8;
      int var10 = 0;

      while (var10 < 2) {
         long var11 = 45111953964615L;
         int var13 = var11 < 0L ? -1 : 1;
         var8 = var6.isEmpty();
         long var14 = 45111953964614L;
         int var38 = var14 < 0L ? -1 : 1;
         if (!var8) {
            break;
         }

         boolean var17 = var10 == 0;
         Iterator var18 = KnifeRenderer7.walks(var0.r).iterator();

         while (var18.hasNext()) {
            long var19 = 45111953964614L;
            int var19x = var19 < 0L ? -1 : 1;
            KnifeRenderer7.TypeZ var21 = (KnifeRenderer7.TypeZ)var18.next();
            j(var6, var7, var21, var21.G ? 1.0F : 2.0F, var0, var1, var17, var4, var34);
         }

         long var40 = 45111953964614L;
         var8 = (var40 < 0L ? -1 : 1) != 0;

         for (KnifeRenderer7.TypeZ var22 : KnifeRenderer7.fidgets(var0.r)) {
            long var23 = 45111953964615L;
            int var23x = var23 < 0L ? -1 : 1;
            j(var6, var7, var22, 1.0F, var0, var1, var17, var4, var34);
         }

         long var44 = 45111953964615L;
         int var50 = var44 < 0L ? -1 : 1;

         for (KnifeRenderer7.TypeZ var24 : KnifeRenderer7.inspects(var0.r)) {
            long var25 = 45111953964614L;
            int var25x = var25 < 0L ? -1 : 1;
            j(var6, var7, var24, 0.35F, var0, var1, var17, var4, var34);
         }

         var10++;
         long var46 = 45111953964615L;
         var8 = (var46 < 0L ? -1 : 1) != 0;
      }

      long var35 = 45111953964614L;
      var8 = (var35 < 0L ? -1 : 1) != 0;
      if (var6.isEmpty()) {
         return null;
      } else {
         float var13 = 0.0F;

         for (float var15 : var7) {
            long var16 = 45111953964614L;
            var8 = (var16 < 0L ? -1 : 1) != 0;
            var13 += var15;
         }

         ThreadLocalRandom var37 = ThreadLocalRandom.current();
         long var38 = 45111953964614L;
         int var43 = var38 < 0L ? -1 : 1;
         float var39 = var37.nextFloat() * var13;

         for (int var41 = 0; var41 < var6.size(); var41++) {
            var39 -= var7.get(var41);
            long var20 = 45111953964614L;
            int var53 = var20 < 0L ? -1 : 1;
            if (var39 <= 0.0F) {
               return (KnifeRenderer7.TypeZ)var6.get(var41);
            }
         }

         long var42 = 45111953964615L;
         var8 = (var42 < 0L ? -1 : 1) != 0;
         return (KnifeRenderer7.TypeZ)var6.getLast();
      }
   }

   private static void j(
      List var0, List var1, KnifeRenderer7.TypeZ var2, float var3, KnifeUpdater2.TypeX var4, class_1268 var5, boolean var6, long var7, boolean var9
   ) {
      long var10 = 4478982401481L;
      if (L(var10, var2, var5)) {
         if (var6) {
            boolean var12 = var4.recent(var2);
            long var13 = 90201015243634L;
            int var15 = var13 < 0L ? -1 : 1;
            if (var12) {
               return;
            }
         }

         if (!var9 || !var2.V) {
            var0.add(var2);
            var1.add(var2.V ? var3 * 0.7F : var3);
            return;
         }
      }
   }

   private static float Z(KnifeModule var0) {
      return 0.12F + ((Double)var0.q.get()).floatValue() * (0.6F + 0.8F * ThreadLocalRandom.current().nextFloat());
   }

   private static KnifeRenderer7.TypeZ S(List var0, KnifeRenderer7.TypeZ var1, long var2, KnifeUpdater2.TypeX var4) {
      if (var0.isEmpty()) {
         return null;
      } else {
         List var5 = var0.size() > 1 ? var0.stream().filter(var1xx -> var1xx != var1).toList() : var0;
         KnifeRenderer7.TypeZ var6 = (KnifeRenderer7.TypeZ)var5.get(ThreadLocalRandom.current().nextInt(var5.size()));
         var4.D = var6;
         return var6;
      }
   }

   private static KnifeRenderer7.TypeZ C(KnifeUpdater2.TypeX var0, class_1268 var1, long var2) {
      long var4 = 83202583700303L;
      var0.c = 0.0F;
      List var6 = KnifeRenderer7.fidgets(var0.r);
      return !var6.isEmpty() && ThreadLocalRandom.current().nextFloat() < 0.6F
         ? (KnifeRenderer7.TypeZ)var6.get(ThreadLocalRandom.current().nextInt(var6.size()))
         : g(var4, var0, var1);
   }

   public static void onKey(int var0) {
      boolean var1 = KnifeSupport3.item() != null && KnifeRenderer4.chosen();
      if (Z.h.matches(var0)) {
         if (var1) {
            KnifeSupport3.inspect();
         } else {
            inspect();
         }
      }

      if (Z.g.matches(var0)) {
         if (var1) {
            KnifeSupport3.slash();
         } else {
            slash();
         }
      }
   }

   public static void onSwing() {
      for (KnifeUpdater2.TypeX var3 : h) {
         var3.cancel();
      }

      KnifeSupport3.onSwing();
   }

   private static boolean I(long var0, class_1657 var2) {
      long var3 = 136059899819918L;
      int var6 = var3 < 0L ? -1 : 1;
      if (var2 != null && var2 == class_310.method_1551().field_1724) {
         boolean var6x = replaces(var2.method_6047(), class_1268.field_5808);
         long var7 = 136059899819918L;
         int var8 = var7 < 0L ? -1 : 1;
         if (var6x && !KnifeRenderer4.chosen()) {
            return true;
         }
      }

      return false;
   }

   private static boolean G(long var0, class_1657 var2, int var3) {
      if (var2 != null && var2 == class_310.method_1551().field_1724) {
         boolean var4 = replaces(var2.method_6047(), class_1268.field_5808);
         long var5 = 15675L;
         int var7 = var5 < 0L ? -1 : 1;
         if (var4 && KnifeRenderer4.chosen()) {
            return true;
         }
      }

      return false;
   }

   private static void o(float var0, char var1, int var2, short var3) {
      long var4 = System.nanoTime() + (long)(var0 * 1.0E9);
      if (b == 0L || var4 - b > 0L) {
         b = var4;
      }
   }

   public static boolean fighting() {
      return b != 0L && System.nanoTime() - b < 0L;
   }

   public static void attackStarts() {
      KnifeSupport3.attackStarts();
      class_746 var0 = class_310.method_1551().field_1724;
      H = var0 != null ? var0.method_7261(0.5F) : 1.0F;
      i = false;
   }

   public static void attackSwings() {
      long var0 = 128668044022395L;
      long var2 = var0 ^ 136793001398320L;
      long var4 = var0 ^ 16197209941493L;
      long var6 = (var0 ^ 79410665044827L) >>> 32;
      int var8 = (int)((var0 ^ 79410665044827L) << 32 >>> 32);
      long var9 = var0 ^ 130948726365588L;
      int var11 = (int)((var0 ^ 130948726365588L) >>> 48);
      int var12 = (int)((var0 ^ 130948726365588L) << 16 >>> 32);
      int var13 = (int)(var9 << 48 >>> 48);
      class_746 var14 = class_310.method_1551().field_1724;
      KnifeSupport3.attackSwings();
      if (G(var6, var14, var8)) {
         o(i ? 3.0F : 1.2F, (char)var11, var12, (short)var13);
         O = System.nanoTime();
      }

      if (!i && I(var4, var14)) {
         o(1.2F, (char)var11, var12, (short)var13);
         O = System.nanoTime();
         V(H, var2);
      }

      i = false;
   }

   public static void onAttack(class_1657 var0, class_1297 var1) {
      long var2 = 124405157580258L;
      long var4 = var2 ^ 132255237705641L;
      long var6 = var2 ^ 11664003365484L;
      long var8 = (var2 ^ 83673282782402L) >>> 32;
      int var10 = (int)((var2 ^ 83673282782402L) << 32 >>> 32);
      long var11 = var2 ^ 126686106911245L;
      int var13 = (int)((var2 ^ 126686106911245L) >>> 48);
      int var14 = (int)((var2 ^ 126686106911245L) << 16 >>> 32);
      int var15 = (int)(var11 << 48 >>> 48);
      KnifeSupport3.onAttack(var0, var1);
      if (G(var8, var0, var10)) {
         i = true;
      }

      if (I(var6, var0)) {
         i = true;
         float var16 = var0.method_7261(0.5F);
         boolean var17 = var16 > 0.9F;
         boolean var18 = var17
            && var0.field_6017 > 0.0
            && !var0.method_24828()
            && !var0.method_6101()
            && !var0.method_5799()
            && !var0.method_74025()
            && !var0.method_5765()
            && var1 instanceof class_1309
            && !var0.method_5624();
         o(3.0F, (char)var13, var14, (short)var15);
         O = System.nanoTime();
         V(var16, var4);
         KnifeSupport7.attack("hit", var17 ? 0.95F : 0.45F + 0.4F * var16, var17 && var0.method_5624() ? 0.92F : (var17 ? 1.0F : 1.1F));
         if (var18) {
            KnifeSupport7.attack("crit", 0.8F, 1.0F);
         }

         v.add(var18 ? "crit" : (var17 ? "hit" : "light hit"));
      }
   }

   private static void V(float var0, long var1) {
      KnifeSupport7.attack("swing", var0 > 0.9F ? 0.85F : 0.4F + 0.4F * var0, var0 > 0.9F ? 1.0F : 1.08F);
      v.add("swing");
   }

   public static boolean mutes(class_6880<class_3414> var0, double var1, double var3, double var5) {
      class_310 var7 = class_310.method_1551();
      KnifeModule var8 = Z;
      if (O != 0L && System.nanoTime() - O <= 1500000000L && var7.field_1724 != null && var8.enabled() && var8.B.is("Knife")) {
         boolean var9 = U.contains(var0.comp_349()) && var7.field_1724.method_5649(var1, var3, var5) < 9.0;
         if (var9) {
            m++;
         }

         return var9;
      } else {
         return false;
      }
   }

   public static boolean inspecting() {
      for (KnifeUpdater2.TypeX var3 : h) {
         if (var3.B == KnifeUpdater2.TypeY.z && var3.P != null && !var3.P.Q.equals("draw")) {
            return true;
         }
      }

      return false;
   }

   public static KnifeUpdater2.Data trick(class_1268 var0) {
      long var1 = 137623477478118L;
      long var3 = var1 ^ 32467327406135L;
      KnifeUpdater2.TypeX var5 = h[var0.ordinal()];
      return var5.B == KnifeUpdater2.TypeY.z && e == null ? new KnifeUpdater2.Data(var5.P, var5.p, A(var5, var3), var5.q, var5.M, var5.i, var5.W) : null;
   }

   private static float P() {
      return ((Double)Z.C.get()).floatValue() * I;
   }

   private static float A(KnifeUpdater2.TypeX var0, long var1) {
      return P() * (var0.T ? ((Double)Z.x.get()).floatValue() : 1.0F);
   }

   private static KnifeRenderer7.TypeZ k(KnifeUpdater2.TypeX var0, long var1, class_1268 var3, KnifeModule var4, boolean var5, boolean var6) {
      long var7 = 86946906923224L;
      long var9 = 45111953964615L;
      long var11 = 83202583700303L;
      long var13 = 38718181655846L;
      if (var0.Q) {
         return g(var11, var0, var3);
      } else if (var0.g) {
         return F(var0, var7);
      } else {
         if (var4.r.enabled()) {
            double var16;
            int var15 = (var16 = (Double)var4.q.get() - 0.05) == 0.0 ? 0 : (var16 < 0.0 ? -1 : 1);
            long var18 = 79220136332867L;
            int var24 = var18 < 0L ? -1 : 1;
            if (var15 < 0 && var5 && var6 && var3 == z(var13, class_310.method_1551())) {
               boolean var24x = true;
               return var24x ? h(var0, var3, var9) : null;
            }
         }

         boolean var21 = false;
         return var21 ? h(var0, var3, var9) : null;
      }
   }

   private static class_1268 z(long var0, class_310 var2) {
      return replaces(var2.field_1724.method_6047(), class_1268.field_5808) ? class_1268.field_5808 : class_1268.field_5810;
   }

   public static KnifeRenderer7.TypeW frame(class_1799 var0, class_1268 var1, float var2) {
      long var3 = 85936034353878L;
      long var5 = var3 ^ 51149289136135L;
      long var7 = var3 ^ 82125220482775L;
      long var9 = var3 ^ 4202066450713L;
      long var11 = var3 ^ 64206235853156L;
      long var13 = var3 ^ 1354748490254L;
      long var15 = var3 ^ 113455775394961L;
      long var17 = var3 ^ 6753481186450L;
      long var19 = var3 ^ 6067022934425L;
      long var21 = var3 ^ 119976920602608L;
      long var23 = var3 ^ 31539055942506L;
      KnifeUpdater2.TypeX var25 = h[var1.ordinal()];
      class_310 var26 = class_310.method_1551();
      KnifeModule var27 = Z;
      int var28 = KnifeModels.index((String)var27.U.get());
      long var29 = System.nanoTime();
      float var31 = var25.E == 0L ? 0.0F : Math.min(0.1F, (float)(var29 - var25.E) / 1.0E9F);
      var25.E = var29;
      if (var25.y != var0.method_7909() || var25.r != var28) {
         var25.y = var0.method_7909();
         var25.r = var28;
         var25.rest();
         if (var27.R.enabled()) {
            var25.play(KnifeRenderer7.draw(var28));
         }
      }

      if (e != null && var1 == class_1268.field_5808) {
         return e;
      } else {
         if (var26.method_1493()) {
            var31 = 0.0F;
         }

         if (J(var9, var1)) {
            return a(var11, var25, var27);
         } else {
            boolean var32 = var2 > 0.001F
               || var26.field_1724 == null
               || var26.field_1724.field_6252
               || var26.field_1724.method_6115()
               || var26.field_1755 != null
               || var26.method_18506() != null
               || !var26.method_1569();
            if (var25.B == KnifeUpdater2.TypeY.z && var25.P.O && !otherHandFree(var1)) {
               var32 = true;
            }

            if (var32) {
               var25.cancel();
            }

            double var33 = var26.field_1724 != null ? var26.field_1724.method_18798().method_37268() : 0.0;
            boolean var35 = !var32 && var33 >= 0.0016;
            boolean var36 = !fighting() && !var26.field_1690.field_1886.method_1434();
            if (var1 == z(var21, var26) && var26.field_1724 != null && var33 >= 0.0016 && !fighting()) {
               N += var31;
               f = f + (var25.B == KnifeUpdater2.TypeY.z && !var25.restingInFlow() ? var31 : 0.0F);
            }

            switch (var25.B) {
               case L:
                  boolean var37 = !var32 && var33 < 4.0E-4 && var26.field_1724.method_24828();
                  var25.C = var37 ? var25.C + var31 : 0.0F;
                  var25.c = var35 ? var25.c + var31 : Math.max(0.0F, var25.c - var31 * 0.5F);
                  if (var1 == z(var21, var26) && var36) {
                     if (var27.p.enabled() && var25.C >= (Double)var27.A.get()) {
                        var25.play(C(var25, var1, var23));
                     } else if (var27.r.enabled() && var35 && var25.c >= var25.m) {
                        KnifeRenderer7.TypeZ var43 = h(var25, var1, var15);
                        var25.c = 0.0F;
                        var25.m = Z(var27);
                        if (var43 != null) {
                           var25.play(var43, true);
                           a++;
                           T.add(var43.j);
                        }
                     }
                  }
                  break;
               case z:
                  float var38 = var25.p;
                  float var39 = var31 * A(var25, var5);
                  var25.p += var39;
                  var25.M += var39;
                  v(var25, var38, var25.p, var7);
                  if (!var25.b && var25.p >= var25.P.N && var25.p < var25.P.duration()) {
                     boolean var40 = var25.Q || var25.g;
                     KnifeRenderer7.TypeZ var41 = k(var25, var17, var1, var27, var35, var36);
                     if (var41 != null) {
                        var25.flowInto(var41, !var40);
                        if (!var40) {
                           a++;
                           T.add(var41.j);
                        }
                     } else {
                        var25.b = true;
                     }
                  }

                  if (var25.p >= var25.P.duration()) {
                     boolean var44 = var25.Q;
                     boolean var45 = var25.g;
                     var25.rest();
                     KnifeRenderer7.TypeZ var42 = var44 ? g(var19, var25, var1) : (var45 ? F(var25, var13) : null);
                     if (var42 != null) {
                        var25.play(var42);
                     }
                  } else {
                     var25.pose();
                  }
                  break;
               case d:
                  var25.p += var31;
                  var25.x.toward(var25.a, KnifeRenderer7.ease(Math.min(1.0F, var25.p / 0.24F)), var25.w);
                  if (var25.p >= 0.24F) {
                     var25.rest();
                  }
            }

            return var25.w;
         }
      }
   }

   private static void v(KnifeUpdater2.TypeX var0, float var1, float var2, long var3) {
      List var5 = var0.P.events();

      while (var0.G < var5.size()) {
         long var6 = 5058638191616L;
         int var9 = var6 < 0L ? -1 : 1;
         if (!(((KnifeRenderer7.Data2)var5.get(var0.G)).time() <= var2)) {
            break;
         }

         KnifeRenderer7.Data2 var9x = (KnifeRenderer7.Data2)var5.get(var0.G++);
         float var10;
         int var11 = (var10 = var9x.time() - (var1 - 0.05F)) == 0.0F ? 0 : (var10 < 0.0F ? -1 : 1);
         long var12 = 5058638191616L;
         int var13 = var12 < 0L ? -1 : 1;
         if (var11 >= 0) {
            KnifeSupport7.play(var9x);
         }
      }

      long var14 = 5058638191616L;
      int var16 = var14 < 0L ? -1 : 1;
   }

   public static void hold(KnifeRenderer7.TypeW var0) {
      e = var0;
   }

   public static void tempo(float var0) {
      I = var0;
   }

   public static List<KnifeRenderer7.TypeZ> upcoming(int var0) {
      long var1 = 129925408270886L;
      long var3 = var1 ^ 67647911528809L;
      KnifeUpdater2.TypeX var5 = h[class_1268.field_5808.ordinal()];
      int var6 = var5.F;
      ArrayList var7 = new ArrayList();
      if (var5.r >= 0) {
         for (int var8 = 0; var8 < var0; var8++) {
            var7.add(g(var3, var5, class_1268.field_5808));
         }
      }

      var5.F = var6;
      return var7;
   }

   public static void play(KnifeRenderer7.TypeZ var0) {
      KnifeUpdater2.TypeX var1 = h[class_1268.field_5808.ordinal()];
      if (var1.y != null) {
         var1.play(var0);
      }
   }

   public static void film(Path var0, float var1, KnifeRenderer7.TypeZ var2) {
      film(var0, var1, List.of(var2), 0.0F);
   }

   public static void film(Path var0, float var1, List<KnifeRenderer7.TypeZ> var2, float var3) {
      KnifeUpdater2.TypeX var4 = h[class_1268.field_5808.ordinal()];
      if (var4.y != null && !var2.isEmpty()) {
         var4.play(var2.getFirst(), var2.size() > 1);
         C = var0;
         R = 1.0F / var1;
         X = 0;
         y = Math.round(var1 * 0.3F);
         r = List.copyOf(var2);
         k = 1;
         Q = Math.round(var1 * var3);
         j = Q;
         ClientServices.LOGGER.info("Knife film trick: frame 0 {}", var2.getFirst().j);
      }
   }

   private static boolean J(long var0, class_1268 var2) {
      return C != null && var2 == class_1268.field_5808;
   }

   private static KnifeRenderer7.TypeW a(long var0, KnifeUpdater2.TypeX var2, KnifeModule var3) {
      if (var2.B == KnifeUpdater2.TypeY.z) {
         if (X > 0) {
            float var4 = var2.p;
            long var5 = 127876866012082L;
            int var28 = var5 < 0L ? -1 : 1;
            float var8 = R * ((Double)var3.C.get()).floatValue();
            long var9 = 127876866012082L;
            int var11 = var9 < 0L ? -1 : 1;
            float var12 = var8 * (var2.T ? ((Double)var3.x.get()).floatValue() : 1.0F);
            var2.p += var12;
            var2.M += var12;
            List var13 = var2.P.events();

            while (var2.G < var13.size()) {
               float var14;
               int var15 = (var14 = ((KnifeRenderer7.Data2)var13.get(var2.G)).time() - var2.p) == 0.0F ? 0 : (var14 < 0.0F ? -1 : 1);
               long var16 = 127876866012081L;
               int var16x = var16 < 0L ? -1 : 1;
               if (var15 > 0) {
                  break;
               }

               KnifeRenderer7.Data2 var18x = (KnifeRenderer7.Data2)var13.get(var2.G++);
               float var19;
               int var20 = (var19 = var18x.time() - (var4 - 1.0E-4F)) == 0.0F ? 0 : (var19 < 0.0F ? -1 : 1);
               long var21 = 127876866012081L;
               int var21x = var21 < 0L ? -1 : 1;
               if (var20 >= 0) {
                  ClientServices.LOGGER.info("Knife film sound: frame {} {} {} {}", new Object[]{X, var18x.sound(), var18x.volume(), var18x.pitch()});
               }
            }
         }

         long var23 = 127876866012081L;
         int var27 = var23 < 0L ? -1 : 1;
         if (Q == 0) {
            long var7 = 127876866012081L;
            int var311 = var7 < 0L ? -1 : 1;
            if (k < r.size()) {
               long var10 = 127876866012082L;
               int var36 = var10 < 0L ? -1 : 1;
               if (var2.p >= var2.P.N) {
                  KnifeRenderer7.TypeZ var30 = r.get(k++);
                  var2.flowInto(var30, true);
                  ClientServices.LOGGER.info("Knife film trick: frame {} {}", X, var30.j);
               }
            }
         }

         if (var2.p > var2.P.duration()) {
            var2.rest();
         } else {
            var2.pose();
            long var27x = 127876866012082L;
            int var33 = var27x < 0L ? -1 : 1;
         }
      } else {
         int var24 = k;
         long var26 = 127876866012082L;
         int var28 = var26 < 0L ? -1 : 1;
         var28 = r.size();
         long var29 = 127876866012082L;
         int var38 = var29 < 0L ? -1 : 1;
         if (var24 < var28) {
            if (j-- <= 0) {
               KnifeRenderer7.TypeZ var11 = r.get(k++);
               var2.play(var11);
               long var31 = 127876866012081L;
               int var41 = var31 < 0L ? -1 : 1;
               j = Q;
               ClientServices.LOGGER.info("Knife film trick: frame {} {}", X, var11.j);
            }
         } else if (y-- <= 0) {
            ClientServices.LOGGER.info("Knife film done: {} frames in {}", X, C);
            C = null;
            return var2.w;
         }
      }

      Path var25 = C.resolve(String.format(Locale.ROOT, "f%04d.png", X++));
      RenderPipelines.get().requestScreenshot(var1 -> {
         try {
            long var2x = 55942696190399L;
            int var5x = var2x < 0L ? -1 : 1;
            class_1011 var5xx = var1;

            try {
               Files.createDirectories(var25.getParent());
               long var6x = 55942696190398L;
               int var7x = var6x < 0L ? -1 : 1;
               var1.method_4314(var25);
            } catch (Throwable var9) {
               if (var1 != null) {
                  try {
                     var5xx.close();
                  } catch (Throwable var8x) {
                     var9.addSuppressed(var8x);
                  }
               }

               throw var9;
            }

            if (var1 != null) {
               var1.close();
            }
         } catch (Exception var10x) {
            ClientServices.LOGGER.error("Could not save knife film frame {}", var25, var10x);
         }
      });
      return var2.w;
   }

   public static void install(KnifeModule var0, ModuleContext var1) {
      Z = var0;
      E = var1;
   }

   public static void metric(String var0) {
      if (E != null) {
         E.metric(var0, var0.equals("drawn") ? ++c : ++F);
      }
   }

   public static void uninstall() {
      reset();
      KnifeSupport3.reset();
      KnifeCache.release();
      KnifeRenderer3.close();
      Z = null;
      E = null;
   }

   public static void ensureSourceClassInitialized() {
   }

   public record Data(KnifeRenderer7.TypeZ M, float H, float F, KnifeRenderer7.TypeD T, float K, KnifeRenderer7.TypeZ L, float v) {
      public KnifeRenderer7.TypeW at(float var1, KnifeRenderer7.TypeW var2) {
         float var3 = var1 * this.F;
         if (this.T != null && this.L != null && !(var3 <= this.K)) {
            return this.L.at(this.v - (var3 - this.K), var2);
         } else {
            this.M.at(this.H - var3, var2);
            return this.T != null ? this.T.apply(var2, this.K - var3) : var2;
         }
      }

      public KnifeRenderer7.TypeZ trick() {
         return this.M;
      }

      public float time() {
         return this.H;
      }

      public float rate() {
         return this.F;
      }

      public KnifeRenderer7.TypeD blend() {
         return this.T;
      }

      public float age() {
         return this.K;
      }

      public KnifeRenderer7.TypeZ prev() {
         return this.L;
      }

      public float prevTime() {
         return this.v;
      }

      public KnifeRenderer7.TypeZ knifeAnimationLibrary_KnifeTrickAnimationState2() {
         return this.M;
      }

      public float floatState7() {
         return this.H;
      }

      public float floatState36() {
         return this.F;
      }

      public KnifeRenderer7.TypeD knifeAnimationLibrary_KnifePoseBlendState1() {
         return this.T;
      }

      public float floatState50() {
         return this.K;
      }

      public KnifeRenderer7.TypeZ knifeAnimationLibrary_KnifeTrickAnimationState3() {
         return this.L;
      }

      public float floatState30() {
         return this.v;
      }
   }

   private static final class TypeX {
      Object y;
      int r = -1;
      long E;
      float p;
      float C;
      KnifeUpdater2.TypeY B = KnifeUpdater2.TypeY.L;
      KnifeRenderer7.TypeZ P;
      int F;
      boolean Q;
      boolean g;
      float c;
      float m = 0.3F;
      KnifeRenderer7.TypeZ D;
      final KnifeRenderer7.TypeZ[] R = new KnifeRenderer7.TypeZ[3];
      int G;
      KnifeRenderer7.TypeD q;
      float M;
      float W;
      KnifeRenderer7.TypeZ i;
      boolean T;
      boolean b;
      final KnifeRenderer7.TypeW w = new KnifeRenderer7.TypeW();
      final KnifeRenderer7.TypeW x = new KnifeRenderer7.TypeW();
      final KnifeRenderer7.TypeW a = new KnifeRenderer7.TypeW();

      void clear() {
         this.y = null;
         this.r = -1;
         this.E = 0L;
         this.F = 0;
         this.rest();
      }

      void rest() {
         this.B = KnifeUpdater2.TypeY.L;
         this.P = null;
         this.p = this.C = 0.0F;
         this.Q = this.g = false;
         if (this.r >= 0) {
            this.a.rest(this.r);
            this.w.set(this.a);
         }
      }

      void play(KnifeRenderer7.TypeZ var1) {
         this.play(var1, false);
      }

      void play(KnifeRenderer7.TypeZ var1, boolean var2) {
         this.B = KnifeUpdater2.TypeY.z;
         this.P = var1;
         this.p = 0.0F;
         this.G = 0;
         this.Q = this.g = false;
         this.q = null;
         this.i = null;
         this.T = var2;
         this.b = false;
         System.arraycopy(this.R, 0, this.R, 1, this.R.length - 1);
         this.R[0] = var1;
      }

      void flowInto(KnifeRenderer7.TypeZ var1, boolean var2) {
         this.q = KnifeRenderer7.TypeD.of(this.P, this.p, this.q, this.M, var1, var1.P);
         this.i = this.P;
         this.W = this.p;
         this.M = 0.0F;
         this.P = var1;
         this.p = var1.P;
         List var3 = var1.events();
         this.G = 0;

         while (this.G < var3.size() && ((KnifeRenderer7.Data2)var3.get(this.G)).time() < var1.P) {
            this.G++;
         }

         this.Q = this.g = false;
         this.T = var2;
         this.b = false;
         System.arraycopy(this.R, 0, this.R, 1, this.R.length - 1);
         this.R[0] = var1;
      }

      void pose() {
         this.P.at(this.p, this.w);
         if (this.q != null) {
            this.q.apply(this.w, this.M);
            if (this.M >= this.q.T + 0.5F) {
               this.q = null;
               this.i = null;
            }
         }
      }

      boolean restingInFlow() {
         return this.b && this.P != null && this.p >= this.P.N;
      }

      boolean recent(KnifeRenderer7.TypeZ var1) {
         for (KnifeRenderer7.TypeZ var5 : this.R) {
            if (var5 == var1) {
               return true;
            }
         }

         return false;
      }

      void cancel() {
         if (this.B == KnifeUpdater2.TypeY.z) {
            this.x.set(this.w);
            this.B = KnifeUpdater2.TypeY.d;
            this.p = 0.0F;
            this.Q = this.g = false;
         }
      }
   }

   private static enum TypeY {
      L,
      z,
      d;
   }
}
