// Sourced By DevFFNightmare
package dev.purxiy.modules.targethud;

import java.util.Arrays;
import java.util.function.Predicate;
import net.minecraft.class_1293;
import net.minecraft.class_1294;
import net.minecraft.class_1304;
import net.minecraft.class_1309;
import net.minecraft.class_1657;
import net.minecraft.class_1661;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_1844;
import net.minecraft.class_1893;
import net.minecraft.class_3489;
import net.minecraft.class_6880;
import net.minecraft.class_9304;
import net.minecraft.class_9334;

public final class TargetUpdater2 {
   private static final PvpMode[] u = PvpMode.values();
   private static final class_1304[] n = new class_1304[]{class_1304.field_6169, class_1304.field_6174, class_1304.field_6172, class_1304.field_6166};
   private static final float P = 45.0F;
   private static final float F = 4.0F;
   private static final float J = 1.2F;
   private static final float o = 24.0F;
   private static final float[] R = new float[u.length];
   private static final float[] Z = new float[u.length];
   private static final float[] a = new float[u.length];
   private static final TargetUpdater2.TypeC k = new TargetUpdater2.TypeC();
   private static final TargetUpdater2.TypeC z = new TargetUpdater2.TypeC();
   private static PvpMode p = PvpMode.s;
   
   private static PvpMode W;
   private static float V;

   private TargetUpdater2() {
   }

   public static PvpMode mode() {
      return p;
   }

   public static float score(PvpMode var0) {
      return a[var0.ordinal()];
   }

   static void reset() {
      Arrays.fill(R, 0.0F);
      Arrays.fill(a, 0.0F);
      p = PvpMode.s;
      W = null;
   }

   private static void E(PvpMode var0, float var1) {
      int var2 = var0.ordinal();
      R[var2] = Math.min(24.0F, R[var2] + var1);
   }

   static void totemPop() {
      E(PvpMode.U, 2.0F);
      E(PvpMode.a, 1.5F);
      E(PvpMode.g, 1.5F);
      E(PvpMode.D, 1.5F);
   }

   static void explosion() {
      E(PvpMode.U, 5.0F);
   }

   static void crystalPlaced() {
      E(PvpMode.U, 3.0F);
   }

   static void maceSmash() {
      E(PvpMode.D, 6.0F);
   }

   static void windCharge() {
      E(PvpMode.D, 2.0F);
   }

   static void spearHit() {
      E(PvpMode.P, 6.0F);
   }

   static void arrowHit() {
      E(PvpMode.l, 2.0F);
      E(PvpMode.Y, 0.5F);
   }

   static void potion(boolean var0) {
      if (var0) {
         E(PvpMode.j, 2.5F);
         E(PvpMode.g, 2.5F);
      } else {
         E(PvpMode.a, 1.0F);
         E(PvpMode.g, 0.5F);
         E(PvpMode.j, 0.5F);
         E(PvpMode.D, 0.5F);
      }
   }

   static void xpBottle() {
      E(PvpMode.g, 2.0F);
      E(PvpMode.a, 2.0F);
      E(PvpMode.U, 1.0F);
   }

   static void pearl() {
      E(PvpMode.a, 1.5F);
      E(PvpMode.U, 1.0F);
      E(PvpMode.D, 1.0F);
   }

   static void gapple() {
      E(PvpMode.l, 2.0F);
      E(PvpMode.a, 1.0F);
      E(PvpMode.U, 0.5F);
      E(PvpMode.g, 0.5F);
      E(PvpMode.D, 0.5F);
   }

   static void blocking(float var0) {
      E(PvpMode.Y, 1.5F * var0);
      E(PvpMode.a, 0.4F * var0);
   }

   static void shieldDisabled() {
      E(PvpMode.Y, 4.0F);
   }

   static void melee() {
      E(PvpMode.X, 0.2F);
   }

   static void tick(float var0, class_1657 var1,  class_1309 var2, Predicate<PvpMode> var3, float var4) {
      long var5 = 13175666853938L;
      long var7 = var5 ^ 44614631423285L;
      int var9 = (int)((var5 ^ 44614631423285L) >>> 32);
      int var10 = (int)((var5 ^ 44614631423285L) << 32 >>> 48);
      int var11 = (int)(var7 << 48 >>> 48);
      float var12 = (float)Math.pow(0.5, var0 / 45.0F);

      for (int var13 = 0; var13 < R.length; var13++) {
         R[var13] = R[var13] * var12;
      }

      Arrays.fill(Z, 0.0F);
      k.clear();
      class_1661 var20 = var1.method_31548();

      for (int var14 = 0; var14 < 9; var14++) {
         k.add(var20.method_5438(var14));
      }

      k.add(var1.method_6079());
      k.wear(var1);
      V(k, var9, 1.0F, (char)var10, (short)var11, true);
      if (var2 != null) {
         z.clear();
         z.add(var2.method_6047());
         z.add(var2.method_6079());
         z.wear(var2);
         V(z, var9, 0.8F, (char)var10, (short)var11, false);
      }

      for (int var21 = 0; var21 < a.length; var21++) {
         a[var21] = Z[var21] + R[var21];
      }

      PvpMode var22 = PvpMode.s;
      float var15 = 4.0F;

      for (PvpMode var19 : u) {
         if (var19 != PvpMode.s && var3.test(var19) && a[var19.ordinal()] >= var15) {
            var22 = var19;
            var15 = a[var19.ordinal()];
         }
      }

      if (var22 == p) {
         W = null;
      } else {
         boolean var23 = p != PvpMode.s && var3.test(p) && a[p.ordinal()] >= 2.0F;
         if (p != PvpMode.s && var23) {
            if (var22 == PvpMode.s || var15 < a[p.ordinal()] * 1.2F + 1.0F) {
               W = null;
            } else if (W != var22) {
               W = var22;
               V = var4;
            } else if (var4 - V >= 1.2F) {
               p = var22;
               W = null;
            }
         } else {
            p = var22;
            W = null;
         }
      }
   }

   private static void d(PvpMode var0, float var1) {
      Z[var0.ordinal()] += var1;
   }

   private static void V(TargetUpdater2.TypeC var0, int var1, float var2, char var3, short var4, boolean var5) {
      TargetHud.ensureSourceClassInitialized();
      if (var0.Y) {
         d(PvpMode.U, 7.0F * var2);
      }

      short var6 = 9064;
      if (var0.W) {
         d(PvpMode.U, 2.0F * var2);
      }

      var6 = 5552;
      if (var0.z) {
         d(PvpMode.U, 2.0F * var2);
      }

      var6 = 9064;
      if (var0.d) {
         d(PvpMode.D, 7.0F * var2);
      }

      var6 = -19193;
      if (var0.j) {
         d(PvpMode.D, 3.0F * var2);
      }

      var6 = 5552;
      if (var0.L) {
         d(PvpMode.P, 7.0F * var2);
      }

      var6 = -19193;
      if (var0.a) {
         d(PvpMode.j, 3.5F * var2);
         d(PvpMode.g, 3.5F * var2);
      }

      var6 = 5552;
      if (var0.Q) {
         d(PvpMode.a, 1.5F * var2);
         d(PvpMode.g, var2);
         d(PvpMode.j, var2);
         d(PvpMode.D, var2);
      }

      var6 = 5552;
      if (var0.w) {
         d(PvpMode.g, 2.5F * var2);
         d(PvpMode.a, 2.5F * var2);
         d(PvpMode.U, var2);
      }

      var6 = 5552;
      if (var0.k) {
         d(PvpMode.a, 2.0F * var2);
         d(PvpMode.U, 1.5F * var2);
         d(PvpMode.D, 1.5F * var2);
      }

      var6 = 9064;
      if (var0.p) {
         d(PvpMode.Y, 3.0F * var2);
         d(PvpMode.a, 2.0F * var2);
         d(PvpMode.l, 1.5F * var2);
         d(PvpMode.D, var2);
      }

      var6 = 5552;
      if (var0.x) {
         d(PvpMode.Y, 2.5F * var2);
         d(PvpMode.a, 1.5F * var2);
         d(PvpMode.l, var2);
         d(PvpMode.D, 0.5F * var2);
      }

      var6 = 5552;
      if (var0.y) {
         d(PvpMode.l, 2.5F * var2);
         d(PvpMode.Y, 1.5F * var2);
      }

      var6 = 9064;
      if (var0.X) {
         d(PvpMode.l, 3.5F * var2);
      }

      var6 = -19193;
      if (var0.R) {
         d(PvpMode.l, 1.5F * var2);
         d(PvpMode.a, var2);
         d(PvpMode.U, var2);
         d(PvpMode.g, var2);
         d(PvpMode.D, var2);
      }

      var6 = 9064;
      if (var0.I) {
         d(PvpMode.U, 1.5F * var2);
         d(PvpMode.a, 1.5F * var2);
         d(PvpMode.g, 1.5F * var2);
         d(PvpMode.D, 1.5F * var2);
      }

      if (var0.O) {
         d(PvpMode.D, 2.0F * var2);
         d(PvpMode.U, var2);
      }

      var6 = 9064;
      if (var0.B) {
         d(PvpMode.X, 1.5F * var2);
      }

      if (var5 && var0.B) {
         boolean var7 = var0.signature();
         var6 = -19193;
         if (!var7) {
            d(PvpMode.X, 6.0F * var2);
         }
      }

      var6 = -19193;
      if (var5 && var0.a && !var0.I && !var0.w && !var0.R) {
         d(PvpMode.j, 2.0F * var2);
      }

      var6 = -19193;
      if (var0.V >= 2) {
         d(PvpMode.X, var2);
         d(PvpMode.Y, var2);
         d(PvpMode.l, var2);
         d(PvpMode.j, 1.5F * var2);
      }

      if (var0.P >= 2) {
         d(PvpMode.U, var2);
         d(PvpMode.g, 1.5F * var2);
         d(PvpMode.a, var2);
         d(PvpMode.D, var2);
      }

      if (var0.F >= 2) {
         d(PvpMode.U, 3.0F * var2);
      }
   }

   static boolean healing(class_1799 var0) {
      class_1844 var1 = var0.method_58694(class_9334.field_49651);
      if (var1 == null) {
         return false;
      } else {
         for (class_1293 var3 : var1.method_57397()) {
            if (var3.method_55654(class_1294.field_5915)) {
               return true;
            }
         }

         return false;
      }
   }

   private static final class TypeC {
      boolean Y;
      boolean W;
      boolean z;
      boolean d;
      boolean j;
      boolean L;
      boolean a;
      boolean Q;
      boolean w;
      boolean k;
      boolean p;
      boolean x;
      boolean B;
      boolean y;
      boolean X;
      boolean R;
      boolean I;
      boolean O;
      int V;
      int P;
      int F;

      void clear() {
         this.Y = this.W = this.z = this.d = this.j = this.L = this.a = this.Q = false;
         this.w = this.k = this.p = this.x = this.B = this.y = this.X = this.R = this.I = this.O = false;
         this.V = this.P = this.F = 0;
      }

      boolean signature() {
         return this.Y
            || this.W
            || this.z
            || this.d
            || this.j
            || this.L
            || this.a
            || this.Q
            || this.w
            || this.k
            || this.p
            || this.x
            || this.y
            || this.X
            || this.R
            || this.I;
      }

      void add(class_1799 var1) {
         if (!var1.method_7960()) {
            this.Y = this.Y | (var1.method_31574(class_1802.field_8301) || var1.method_31574(class_1802.field_23141));
            this.W = this.W | var1.method_31574(class_1802.field_8801);
            this.z = this.z | var1.method_31574(class_1802.field_8281);
            this.d = this.d | var1.method_31574(class_1802.field_49814);
            this.j = this.j | var1.method_31574(class_1802.field_49098);
            this.L = this.L | var1.method_31573(class_3489.field_63257);
            this.w = this.w | var1.method_31574(class_1802.field_8287);
            this.k = this.k | var1.method_31574(class_1802.field_8634);
            this.p = this.p | var1.method_57826(class_9334.field_56396);
            this.x = this.x | var1.method_31573(class_3489.field_42612);
            this.B = this.B | var1.method_31573(class_3489.field_42611);
            this.y = this.y | (var1.method_31574(class_1802.field_8102) || var1.method_31574(class_1802.field_8399));
            this.X = this.X
               | (var1.method_31574(class_1802.field_8187) || var1.method_31574(class_1802.field_8705) || var1.method_31574(class_1802.field_8786));
            this.R = this.R | (var1.method_31574(class_1802.field_8463) || var1.method_31574(class_1802.field_8367));
            this.I = this.I | var1.method_57826(class_9334.field_54274);
            if (var1.method_31574(class_1802.field_8436) || var1.method_31574(class_1802.field_8150)) {
               if (TargetUpdater2.healing(var1)) {
                  this.a = true;
               } else {
                  this.Q = true;
               }
            }
         }
      }

      void wear(class_1309 var1) {
         for (class_1304 var5 : TargetUpdater2.n) {
            class_1799 var6 = var1.method_6118(var5);
            if (!var6.method_7960()) {
               if (var6.method_57826(class_9334.field_54197)) {
                  this.O = true;
               }

               if (var6.method_31574(class_1802.field_22027)
                  || var6.method_31574(class_1802.field_22028)
                  || var6.method_31574(class_1802.field_22029)
                  || var6.method_31574(class_1802.field_22030)) {
                  this.P++;
               } else if (var6.method_31574(class_1802.field_8805)
                  || var6.method_31574(class_1802.field_8058)
                  || var6.method_31574(class_1802.field_8348)
                  || var6.method_31574(class_1802.field_8285)) {
                  this.V++;
               }

               class_9304 var7 = var6.method_58695(class_9334.field_49633, class_9304.field_49385);

               for (class_6880 var9 : var7.method_57534()) {
                  if (var9.method_40225(class_1893.field_9107)) {
                     this.F++;
                  }
               }
            }
         }
      }
   }
}
