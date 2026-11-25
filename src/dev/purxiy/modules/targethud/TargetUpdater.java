package dev.purxiy.modules.targethud;

import dev.purxiy.loader.fabric.module.ModuleRegistry;
import dev.purxiy.loader.fabric.module.ModuleRegistry.WeaponGroup;
import dev.purxiy.render.RenderPipelines;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.class_10590;
import net.minecraft.class_10707;
import net.minecraft.class_11890;
import net.minecraft.class_1297;
import net.minecraft.class_1309;
import net.minecraft.class_1511;
import net.minecraft.class_1531;
import net.minecraft.class_1665;
import net.minecraft.class_1675;
import net.minecraft.class_1676;
import net.minecraft.class_1683;
import net.minecraft.class_1684;
import net.minecraft.class_1686;
import net.minecraft.class_1799;
import net.minecraft.class_1802;
import net.minecraft.class_238;
import net.minecraft.class_243;
import net.minecraft.class_310;
import net.minecraft.class_3414;
import net.minecraft.class_3417;
import net.minecraft.class_3959;
import net.minecraft.class_3965;
import net.minecraft.class_3966;
import net.minecraft.class_638;
import net.minecraft.class_640;
import net.minecraft.class_6880;
import net.minecraft.class_746;
import net.minecraft.class_8110;
import net.minecraft.class_8111;
import net.minecraft.class_9236;
import net.minecraft.class_9334;
import net.minecraft.class_239.class_240;
import net.minecraft.class_3959.class_242;
import net.minecraft.class_3959.class_3960;

public final class TargetUpdater {
   private static final float Y = 0.25F;
   private static final float N = 0.6F;
   private static final float E = 6.0F;
   public static final float p = 2.4F;
   private static final float S = 0.9F;
   private static final float X = 3.0F;
   private static final int Q = 28;
   private static final float H = 300.0F;
   private static final double I = 32.0;
   private static final Map<UUID, TargetSnapshot> j = new HashMap<>();
   private static final List<TargetUpdater.ProjectileHit> s = new ArrayList<>();
   private static class_1309 F;
   private static float G = -1000.0F;
   private static float n = -1000.0F;
   private static float t = -1000.0F;
   private static class_1309 M;
   private static float m;
   private static class_638 J;
   private static float R;
   private static long W = 0L;
   private static float U = -1000.0F;
   private static float T;
   private static float w;
   private static TargetHud C;
   private static final long[] a = new long[]{7549970523405055799L, -6032581973649080163L};
   private static final Integer[] b = new Integer[2];

   private TargetUpdater() {
   }

   public static class_1309 target() {
      return F;
   }

   public static float acquiredAt() {
      return G;
   }

   public static float fightAt() {
      return t;
   }

   public static TargetSnapshot stats(class_1309 var0) {
      return j.computeIfAbsent(var0.method_5667(), TargetSnapshot::new);
   }

   private static TargetHud b() {
      return C;
   }

   private static boolean q(long var0) {
      TargetHud.ensureSourceClassInitialized();
      TargetHud var2 = b();
      return var2 != null && var2.enabled() && J != null;
   }

   private static float a() {
      return RenderPipelines.get().time();
   }

   static void tick(class_310 var0) {
      long var1 = 111590856932527L;
      long var3 = var1 ^ 120443088785798L;
      long var5 = var1 ^ 57698479579932L;
      long var7 = var1 ^ 82411243809898L;
      float var9 = a();
      float var10 = R == 0.0F ? 0.05F : Math.max(0.0F, Math.min(0.25F, var9 - R));
      R = var9;
      W++;
      if (var0.field_1687 != J) {
         a();
         J = var0.field_1687;
      }

      class_746 var11 = var0.field_1724;
      TargetHud var12 = b();
      if (var12 != null && var12.enabled() && var11 != null && J != null) {
         z(var11, var3, var9);
         u(var5, var0, var11, var12, var9);
         if (F != null) {
            p(F, var9, var10, var7);
         }

         TargetScore.tick(var10, var11, F, var12::detects, var9);
         if (var9 - w > 30.0F) {
            w = var9;
            j.values().removeIf(var1x -> {
               TargetHud.ensureSourceClassInitialized();
               return var9 - var1x.V > 300.0F && (F == null || !F.method_5667().equals(var1x.h));
            });
         }
      } else {
         F = null;
         M = null;
         s.clear();
      }
   }

   private static void u(long var0, class_310 var2, class_746 var3, TargetHud var4, float var5) {
      long var6 = 18118759023990L;
      byte var8 = 0;
      short var9 = 4218;
      int var10 = (int)(var6 << 32 >>> 32);
      byte var11 = 0;
      long var12 = 136131984648425L;
      long var14 = 66193265540829L;
      long var16 = 15060611453290L;
      long var18 = 135962708488236L;
      TargetHud.ensureSourceClassInitialized();
      class_1309 var20 = o(var2, var3, var11, var4, var12);
      if (var20 == null) {
         M = null;
      } else {
         long var21 = 89078628491187L;
         int var36 = var21 < 0L ? -1 : 1;
         if (var20 == F) {
            n = var5;
            M = null;
         } else {
            label109: {
               if (F != null) {
                  long var24 = 89078628491187L;
                  int var39 = var24 < 0L ? -1 : 1;
                  if (!o(var14, F, var5)) {
                     if (!z(var16, F, var5)) {
                        if (M != var20) {
                           M = var20;
                           m = var5;
                        }

                        long var26 = 89078628491186L;
                        int var28 = var26 < 0L ? -1 : 1;
                        if (var5 - m >= (j(var5, (char)var8, (char)var9, var10) ? 0.6F : 0.25F)) {
                           l(var20, var5, var18);
                        }
                     }
                     break label109;
                  }
               }

               l(var20, var5, var18);
            }
         }
      }

      class_1309 var35 = F;
      if (var35 != null) {
         TargetSnapshot var22 = stats(var35);
         if (var22.O >= G) {
            float var23x;
            int var37 = (var23x = var5 - var22.O - 2.4F) == 0.0F ? 0 : (var23x < 0.0F ? -1 : 1);
            long var25 = 89078628491186L;
            int var43 = var25 < 0L ? -1 : 1;
            if (var37 > 0) {
               T();
            }
         } else if (!var35.method_31481() && var35.method_73183() == var2.field_1687) {
            float var36x = var3.method_5739(var35);
            float var38 = var4.range();
            long var39 = 89078628491186L;
            int var44 = var39 < 0L ? -1 : 1;
            float var28 = var38 * 1.5F;
            long var29 = 89078628491186L;
            int var31 = var29 < 0L ? -1 : 1;
            if (var36x > var28 + 4.0F) {
               T();
            } else {
               float var31x;
               int var32 = (var31x = var5 - n - var4.holdSeconds()) == 0.0F ? 0 : (var31x < 0.0F ? -1 : 1);
               long var33 = 89078628491187L;
               int var34 = var33 < 0L ? -1 : 1;
               if (var32 > 0 && (!var4.locksInFights() || !j(var5, (char)var8, (char)var9, var10))) {
                  T();
               }
            }
         } else {
            T();
         }
      }
   }

   private static boolean o(long var0, class_1309 var2, float var3) {
      TargetHud.ensureSourceClassInitialized();
      TargetSnapshot var4 = stats(var2);
      return var4.O >= G ? var3 - var4.O > 0.9F : var2.method_31481();
   }

   private static boolean z(long var0, class_1309 var2, float var3) {
      TargetHud.ensureSourceClassInitialized();
      TargetSnapshot var4 = stats(var2);
      long var5 = 15060611453289L;
      int var7 = var5 < 0L ? -1 : 1;
      return var4.O >= G && var3 - var4.O <= 0.9F;
   }

   private static boolean j(float var0, char var1, char var2, int var3) {
      TargetHud.ensureSourceClassInitialized();
      return var0 - t < 6.0F;
   }

   private static void l(class_1309 var0, float var1, long var2) {
      TargetHud.ensureSourceClassInitialized();
      long var4 = 135962708488236L;
      int var6 = var4 < 0L ? -1 : 1;
      if (var0 != F) {
         F = var0;
         G = var1;
         t = -1000.0F;
      }

      n = var1;
      M = null;
      stats(var0).V = var1;
   }

   private static void T() {
      F = null;
      M = null;
      t = -1000.0F;
   }

   private static class_1309 o(class_310 var0, class_746 var1, short var2, TargetHud var3, long var4) {
      long var6 = 136131984648425L;
      long var8 = (var6 ^ 50609404624518L) >>> 16;
      int var10 = (int)((var6 ^ 50609404624518L) << 48 >>> 48);
      TargetHud.ensureSourceClassInitialized();
      class_1297 var11 = var0.method_1560();
      if (var11 != null && J != null) {
         class_1309 var12 = ModuleRegistry.current(WeaponGroup.MACE);
         if (var12 != null && L(var8, (short)var10, var1, var3, var12)) {
            return var12;
         } else {
            class_1309 var13 = ModuleRegistry.current(WeaponGroup.LANCE);
            if (var13 != null && L(var8, (short)var10, var1, var3, var13)) {
               return var13;
            } else {
               class_1309 var14 = ModuleRegistry.current(WeaponGroup.AURA);
               if (var14 != null && L(var8, (short)var10, var1, var3, var14)) {
                  return var14;
               } else {
                  double var15 = var3.range();
                  class_243 var17 = var11.method_5836(1.0F);
                  class_243 var18 = var11.method_5828(1.0F);
                  class_243 var19 = var17.method_1031(var18.field_1352 * var15, var18.field_1351 * var15, var18.field_1350 * var15);
                  class_3965 var20 = J.method_17742(new class_3959(var17, var19, class_3960.field_17558, class_242.field_1348, var11));
                  double var21 = var20.method_17783() == class_240.field_1333 ? var15 * var15 : var20.method_17784().method_1025(var17);
                  class_238 var23 = var11.method_5829().method_18804(var18.method_1021(var15)).method_1014(1.0);
                  class_3966 var24 = class_1675.method_18075(var11, var17, var19, var23, var2x -> {
                     long var3x = 1439226115L;
                     char var5 = '홯';
                     return L(var3x, (short)var5, var1, var3, var2x);
                  }, var21);
                  return var24 != null && var24.method_17782() instanceof class_1309 var25 ? var25 : null;
               }
            }
         }
      } else {
         return null;
      }
   }

   private static boolean L(long var0, short var2, class_746 var3, TargetHud var4, class_1297 var5) {
      TargetHud.ensureSourceClassInitialized();
      if (var5 instanceof class_1309 var6) {
         long var7 = 1439226115L;
         int var10 = var7 < 0L ? -1 : 1;
         if (var6 != var3) {
            long var10x = 1439226114L;
            int var12 = var10x < 0L ? -1 : 1;
            if (var6.method_5805()) {
               short var12x = -10641;
               if (!var6.method_7325() && !(var6 instanceof class_1531)) {
                  boolean var13 = var6.method_5756(var3);
                  long var14 = 1439226115L;
                  int var15 = var14 < 0L ? -1 : 1;
                  if (!var13 && (var4.includesMobs() || var6 instanceof class_11890)) {
                     return true;
                  }
               }
            }
         }
      }

      return false;
   }

   private static void p(class_1309 var0, float var1, float var2, long var3) {
      long var5 = 126492918999736L;
      TargetHud.ensureSourceClassInitialized();
      TargetSnapshot var7 = stats(var0);
      var7.V = var1;
      if (var0.method_29504()) {
         v(var5, var0, var1);
      } else {
         label56: {
            class_1799 var8 = var0.method_6115() ? var0.method_6030() : class_1799.field_8037;
            long var9 = 52287360372933L;
            int var12 = var9 < 0L ? -1 : 1;
            long var12x = 52287360372932L;
            int var20 = var12x < 0L ? -1 : 1;
            if (!var8.method_31574(class_1802.field_8463)) {
               boolean var14 = var8.method_31574(class_1802.field_8367);
               long var15 = 52287360372933L;
               int var18 = var15 < 0L ? -1 : 1;
               if (!var14) {
                  long var18x = 52287360372933L;
                  if (var7.k >= 28) {
                     var7.z++;
                     var7.l = var1;
                     TargetScore.gapple();
                  }

                  var7.k = 0;
                  break label56;
               }
            }

            var7.k++;
            long var20x = 52287360372933L;
            int var151 = var20x < 0L ? -1 : 1;
         }

         if (var0.method_6039()) {
            TargetScore.blocking(var2);
         }
      }
   }

   private static void v(long var0, class_1309 var2, float var3) {
      TargetSnapshot var4 = stats(var2);
      if (var3 - var4.O > 3.4F) {
         var4.died(var3);
      }
   }

   private static void z(class_746 var0, long var1, float var3) {
      TargetHud.ensureSourceClassInitialized();
      Iterator var4 = s.iterator();

      while (var4.hasNext()) {
         TargetUpdater.ProjectileHit var5 = (TargetUpdater.ProjectileHit)var4.next();
         long var6 = 9857267068201L;
         int var9 = var6 < 0L ? -1 : 1;
         long var9x;
         int var11 = (var9x = var5.tick() - W) == 0L ? 0 : (var9x < 0L ? -1 : 1);
         long var12 = 9857267068200L;
         int var43 = var12 < 0L ? -1 : 1;
         if (var11 >= 0) {
            long var14 = 9857267068200L;
            int var16 = var14 < 0L ? -1 : 1;
         } else {
            var4.remove();
            class_1676 var43x = var5.projectile();
            class_1297 var15 = var43x.method_24921();
            boolean var16 = var15 instanceof class_1309;
            long var17 = 9857267068201L;
            int var19 = var17 < 0L ? -1 : 1;
            if (var16) {
               class_1309 var19x = (class_1309)var15;
               boolean var20 = var19x.method_5858(var0) < 1024.0;
               TargetSnapshot var21 = stats(var19x);
               var21.V = var3;
               long var22 = 9857267068201L;
               int var25 = var22 < 0L ? -1 : 1;
               if (var43x instanceof class_1686 var25x) {
                  boolean var26 = TargetScore.healing(var25x.method_7495());
                  var21.C++;
                  var21.v = var3;
                  long var27 = 9857267068201L;
                  int var27x = var27 < 0L ? -1 : 1;
                  if (var26) {
                     var21.m++;
                  }

                  if (var20) {
                     TargetScore.potion(var26);
                  }

                  long var29x = 9857267068200L;
               } else {
                  long var48 = 9857267068201L;
                  int var26x = var48 < 0L ? -1 : 1;
                  var16 = var43x instanceof class_1683;
                  long var28x = 9857267068201L;
                  if (var16) {
                     var21.J++;
                     long var30 = 9857267068200L;
                     int var30x = var30 < 0L ? -1 : 1;
                     var21.v = var3;
                     long var32 = 9857267068200L;
                     int var32x = var32 < 0L ? -1 : 1;
                     if (var20) {
                        TargetScore.xpBottle();
                        long var34 = 9857267068201L;
                        int var34x = var34 < 0L ? -1 : 1;
                     }
                  } else {
                     long var49x = 9857267068201L;
                     int var58 = var49x < 0L ? -1 : 1;
                     var16 = var43x instanceof class_1684;
                     long var50 = 9857267068200L;
                     int var61 = var50 < 0L ? -1 : 1;
                     if (var16) {
                        var21.M++;
                        long var51 = 9857267068200L;
                        int var64 = var51 < 0L ? -1 : 1;
                        var21.v = var3;
                        long var36 = 9857267068200L;
                        int var36x = var36 < 0L ? -1 : 1;
                        if (var20) {
                           TargetScore.pearl();
                           long var38 = 9857267068200L;
                           int var38x = var38 < 0L ? -1 : 1;
                        }
                     } else {
                        var16 = var43x instanceof class_9236;
                        long var52 = 9857267068200L;
                        int var66 = var52 < 0L ? -1 : 1;
                        if (var16) {
                           var21.F++;
                           long var53 = 9857267068200L;
                           int var70 = var53 < 0L ? -1 : 1;
                           var21.v = var3;
                           long var55 = 9857267068201L;
                           int var73 = var55 < 0L ? -1 : 1;
                           if (var20) {
                              TargetScore.windCharge();
                              long var40 = 9857267068200L;
                              int var40x = var40 < 0L ? -1 : 1;
                           }
                        } else {
                           var16 = var43x instanceof class_1665;
                           long var54 = 9857267068200L;
                           int var72 = var54 < 0L ? -1 : 1;
                           if (var16) {
                              var21.Y++;
                           }
                        }
                     }
                  }
               }
            }
         }
      }

      long var42 = 9857267068200L;
      int var7 = var42 < 0L ? -1 : 1;
   }

   static void onEntityLoad(class_1297 var0) {
      long var1 = 60971787241183L;
      long var3 = var1 ^ 116686266686758L;
      class_746 var5 = class_310.method_1551().field_1724;
      if (q(var3) && var5 != null) {
         if (var0 instanceof class_1676 var6) {
            s.add(new TargetUpdater.ProjectileHit(var6, W));
         } else if (var0 instanceof class_1511 && var0.method_5858(var5) < 256.0) {
            TargetScore.crystalPlaced();
         }
      }
   }

   static void onAttack(class_1297 var0) {
      long var1 = 134016008764171L;
      long var3 = var1 ^ 2522226461479L;
      long var5 = var1 ^ 40369242317042L;
      long var7 = (var1 ^ 48566711840100L) >>> 16;
      int var9 = (int)((var1 ^ 48566711840100L) << 48 >>> 48);
      class_310 var10 = class_310.method_1551();
      class_746 var11 = var10.field_1724;
      TargetHud var12 = b();
      if (q(var5) && var11 != null && var0 instanceof class_1309 var13 && L(var7, (short)var9, var11, var12, var13)) {
         float var14 = a();
         U = var14;
         class_10590 var15 = (class_10590)var11.method_6047().method_58694(class_9334.field_55878);
         T = var15 != null ? var15.comp_3602() : 0.0F;
         if (var13 == F) {
            n = var14;
         } else if (var12.locksInFights()) {
            l(var13, var14, var3);
         }
      }
   }

   public static void onEntityEvent(class_1297 var0, byte var1) {
      long var2 = 111948095127576L;
      long var4 = var2 ^ 51125647049577L;
      long var6 = var2 ^ 25132537047712L;
      long var8 = var2 ^ 62142953823201L;
      if (q(var8) && var0 instanceof class_1309 var10) {
         float var11 = a();
         switch (var1) {
            case 2:
               if (K(var10, var4)) {
                  TargetScore.spearHit();
               }
               break;
            case 3:
               v(var6, var10, var11);
               break;
            case 35:
               TargetSnapshot var12 = stats(var10);
               var12.s++;
               var12.q = var11;
               var12.V = var11;
               if (K(var10, var4)) {
                  TargetScore.totemPop();
               }
               break;
            case 49:
            case 50:
            case 51:
            case 52:
               stats(var10).S[var1 - 49] = var11;
         }
      }
   }

   public static void onDamage(class_1297 var0, class_6880<class_8110> var1, int var2) {
      long var3 = 119368937693365L;
      long var5 = var3 ^ 43155082273732L;
      long var7 = var3 ^ 54720129497932L;
      long var9 = var3 ^ 31665535850373L;
      class_310 var11 = class_310.method_1551();
      class_746 var12 = var11.field_1724;
      if (q(var7) && var12 != null && J != null && var0 instanceof class_1309 var13) {
         float var14 = a();
         if (var13 == var12 || K(var13, var5)) {
            if (var1.method_40225(class_8111.field_42332) || var1.method_40225(class_8111.field_42331) || var1.method_40225(class_8111.field_42334)) {
               TargetScore.explosion();
            } else if (var1.method_40225(class_8111.field_52431)) {
               TargetScore.maceSmash();
            } else if (var1.method_40225(class_8111.field_63287)) {
               TargetScore.spearHit();
            } else if (var1.method_40225(class_8111.field_42321) || var1.method_40225(class_8111.field_42322)) {
               TargetScore.arrowHit();
            } else if (var1.method_40225(class_8111.field_42320)) {
               TargetScore.melee();
            }
         }

         class_1297 var15 = var2 >= 0 ? J.method_8469(var2) : null;
         boolean var16 = var1.method_40225(class_8111.field_42321) || var1.method_40225(class_8111.field_42322);
         if (var15 == var12 && var13 != var12) {
            TargetSnapshot var19 = stats(var13);
            var19.n++;
            if (var16) {
               var19.R++;
            } else {
               var19.U = var14 - var19.a > 3.0F ? 1 : var19.U + 1;
               var19.d = Math.max(var19.d, var19.U);
            }

            var19.a = var14;
            var19.V = var14;
            V(var12, var13, var14, var9);
         } else if (var13 == var12 && var15 instanceof class_1309 var17 && var17 != var12) {
            TargetSnapshot var18 = stats(var17);
            var18.w++;
            var18.U = 0;
            var18.o = var14;
            var18.V = var14;
            V(var12, var17, var14, var9);
         }
      }
   }

   public static void onSound(class_6880<class_3414> var0, double var1, double var3, double var5) {
      long var7 = 46517173241750L;
      long var9 = var7 ^ 69439655886761L;
      long var11 = var7 ^ 130956326853743L;
      class_1309 var13 = F;
      if (q(var11) && var13 != null && var0.comp_349() == class_3417.field_15239.comp_349() && !(var13.method_5649(var1, var3, var5) > 2.5)) {
         float var14 = a();
         float var15 = var14 - U < 1.5F && T > 0.0F ? T : 5.0F;
         class_10707 var16 = shield(var13);
         if (var16 != null) {
            var15 *= var16.comp_3587();
         }

         TargetSnapshot var17 = stats(var13);
         var17.B = var14;
         var17.e = var14 + var15 + M(var9);
         TargetScore.shieldDisabled();
      }
   }

   private static void V(class_746 var0, class_1309 var1, float var2, long var3) {
      long var5 = 18118759023990L;
      byte var7 = 0;
      short var8 = 4218;
      int var9 = (int)(var5 << 32 >>> 32);
      long var10 = 135962708488236L;
      long var12 = 1439226115L;
      char var14 = '홯';
      TargetHud.ensureSourceClassInitialized();
      TargetHud var15 = b();
      if (var1 == F) {
         t = var2;
         long var16 = 123540609496879L;
         int var22 = var16 < 0L ? -1 : 1;
      } else if (var15.locksInFights()) {
         long var24 = 123540609496879L;
         int var221 = var24 < 0L ? -1 : 1;
         if (L(var12, (short)var14, var0, var15, var1)) {
            if (F != null) {
               long var22x = 123540609496880L;
               int var20 = var22x < 0L ? -1 : 1;
               if (j(var2, (char)var7, (char)var8, var9)) {
                  return;
               }
            }

            l(var1, var2, var10);
            t = var2;
         }
      }
   }

   private static boolean K(class_1297 var0, long var1) {
      TargetHud.ensureSourceClassInitialized();
      class_746 var3 = class_310.method_1551().field_1724;
      return var3 != null && var0.method_5858(var3) < 1024.0;
   }

   public static class_10707 shield(class_1309 var0) {
      class_10707 var1 = (class_10707)var0.method_6079().method_58694(class_9334.field_56396);
      return var1 != null ? var1 : (class_10707)var0.method_6047().method_58694(class_9334.field_56396);
   }

   private static float M(long var0) {
      TargetHud.ensureSourceClassInitialized();
      class_310 var2 = class_310.method_1551();
      long var3 = 23541547530302L;
      int var6 = var3 < 0L ? -1 : 1;
      if (var2.method_1562() != null) {
         long var6x = 23541547530303L;
         int var8 = var6x < 0L ? -1 : 1;
         if (var2.field_1724 != null && !var2.method_1496()) {
            class_640 var8x = var2.method_1562().method_2871(var2.field_1724.method_5667());
            return var8x == null ? 0.0F : Math.min(0.5F, var8x.method_2959() / 2000.0F);
         }
      }

      return 0.0F;
   }

   static void install(TargetHud var0) {
      C = var0;
   }

   static void disposeOwnedState() {
      a();
      C = null;
      J = null;
      R = 0.0F;
   }

   private record ProjectileHit(class_1676 w, long L) {
      public class_1676 projectile() {
         return this.w;
      }

      public long tick() {
         return this.L;
      }

      public class_1676 minecraftObjectState1() {
         return this.w;
      }

      public long longState48() {
         return this.L;
      }
   }
}
